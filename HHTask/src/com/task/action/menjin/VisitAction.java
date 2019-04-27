package com.task.action.menjin;

import java.text.ParseException;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.menjin.VisitServer;
import com.task.entity.menjin.Visit;
import com.task.entity.menjin.VisitCo;
import com.task.util.Util;

@SuppressWarnings("serial")
public class VisitAction extends ActionSupport {
	private VisitServer visitServer;
	private Visit visit;
	private List<Visit> visitList;
	private List<String> visitListTow;
	private List<VisitCo> visitCoList;
	private String errorMessage;
	private String successMessage;

	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String apply; 

	private String taga = "";// 来访申请部门.所有

	// 添加来访登记表
	public String addVisit() {
		if ("".equals(visit.getApplyName()) || "".equals(visit.getVisitstime())) {
			errorMessage = "申请人和申请来访时间不能为空!";
			return ERROR;
		}
		try {// 2-1
			if ((Util.getDateDiff(visit.getVisitstime(), "yyyy-MM-dd", Util
					.getDateTime("yyyy-MM-dd"), "yyyy-MM-dd")) > 0) {
				errorMessage = "申请来访日期不能早于今天!";
				return ERROR;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//判断申请车牌不能为内部车辆
		if ("车牌".equals(visit.getVerifyManner())&&!"".equals(visit.getVisitsLic())) {
			int nei = visitServer.visitCarpai(visit.getVisitsLic());
			if (nei > 0) {
				errorMessage = "该车牌号为内部车辆，无需申请来访!";
				return ERROR;
			}
		}
		
		//判断申请手机号码不能重复
		int ie = visitServer.visitTel(visit);
		if (ie > 0) {
			errorMessage = "该手机号已申请来访，无法再次申请!";
			return ERROR;
		}
		
		
		boolean bool = visitServer.addVisit(visit);
		if (bool == true) {
			errorMessage = "申请成功!";
			url = "VisitAction_toadd.action";
			return ERROR;
		}
		errorMessage = "申请失败!请重试";
		return ERROR;
	}
	
	// 添加来访登记(免申请流程添加)
	public String addVisitWithoutApply() {

		if ("".equals(visit.getApplyName()) || "".equals(visit.getVisitstime())) {
			errorMessage = "申请人和申请来访时间不能为空!";
			return ERROR;
		}
		try {// 2-1
			if ((Util.getDateDiff(visit.getVisitstime(), "yyyy-MM-dd", Util
					.getDateTime("yyyy-MM-dd"), "yyyy-MM-dd")) > 0) {
				errorMessage = "申请来访日期不能早于今天!";
				return ERROR;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//判断申请车牌不能为内部车辆
		if ("车牌".equals(visit.getVerifyManner())&&!"".equals(visit.getVisitsLic())) {
			int nei = visitServer.visitCarpai(visit.getVisitsLic());
			if (nei > 0) {
				errorMessage = "该车牌号为内部车辆，无需申请来访!";
				return ERROR;
			}
		}
		
		//判断申请手机号码不能重复
		int ie = visitServer.visitTel(visit);
		if (ie > 0) {
			errorMessage = "该手机号已申请来访，无法再次申请!";
			return ERROR;
		}
		
		
		boolean bool = visitServer.addVisitwithoutApply(visit);
//		boolean bool = false;
		if (bool == true) {
			errorMessage = "申请成功!";
			url = "VisitAction_toadd.action?apply=false";
			return ERROR;
		}
		errorMessage = "申请失败!请重试";
		return ERROR;
	}

	public String toshenqingOut(){
		return "ineShen_add";
	}
	public String shenqingOut(){
		errorMessage = visitServer.addVisitOut(visit);
		if ("true".equals(errorMessage)) {
			errorMessage = "申请成功";
		}
		return "ineShen_add";
	}
	
	// 跳转到来访登记表
	@SuppressWarnings("unchecked")
	public String toadd() {
		visitListTow = visitServer.allWeisb();
		try {
			if (Util.chackMobileOrPc())// 移动端
				return "Visit_add_mobile";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if("false".equals(apply)){//免审批
			visitCoList =visitServer.findvisitColist();
			return "Visit_addwithoutApply";
		}
		return "Visit_add";
	}
	

	// 跳转查询页面
	@SuppressWarnings("unchecked")
	public String tofind() {
		if (visit != null) {
			ActionContext.getContext().getSession().put("visit", visit);
		} else {
			visit = (Visit) ActionContext.getContext().getSession()
					.get("visit");
		}
		Object[] object = this.visitServer.findVisitByCondition(visit, Integer
				.parseInt(cpage), pageSize, taga);
		if (object != null && object.length > 0) {
			visitList = (List<Visit>) object[0];
			if (visitList != null && visitList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("VisitAction_tofind.action?taga=" + taga);
			}
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "Visit_find";
	}

	// 根据id查询来访登记
	public String salVisitByid() {
		visit = this.visitServer.salvisitByid(visit.getId());
		return "salVisitByid";
	}

	// 确认出门
	public String backOut() {
		if (visit != null) {
			Visit visit1 = visitServer.salvisitByid(visit.getId());
			if (visit1 != null) {
				errorMessage = visitServer.backOut(visit1);
				if ("来访已结束".equals(errorMessage)) {
					if ("手机".equals(visit1.getVerifyManner())) {
						errorMessage = "验证码："+visit1.getVisitsCode()+"已生成，有效期30分钟。";
					}else {
						errorMessage = "车牌："+visit1.getVisitsCode()+"已 确认出门，有效期30分钟。";
					}
					url = "VisitAction_tofind.action?taga=" + taga;
					return "error";
				}
			}
		}
		errorMessage = "来访记录不存在，出门失败！请检查";
		return "error";
	}

	//再次申请
	public String agreen(){
		if (visit!=null && visit.getId()!=null && visit.getId()>0 && visit.getVisitsTel()!=null && visit.getVisitsTel().length()>10) {
			Visit visit1 = this.visitServer.salvisitByid(visit.getId());
			if (visit1!=null) {
				visit1.setVisitsTel(visit.getVisitsTel());
				errorMessage = visitServer.agreen(visit1);
				if ("true".equals(errorMessage)) {
					errorMessage = "成功再次申请！";
					url = "VisitAction_tofind.action?taga=" + taga;
					return "error";
				}
			}
		}
		return "error";
	}
	
	public VisitServer getVisitServer() {
		return visitServer;
	}

	public void setVisitServer(VisitServer visitServer) {
		this.visitServer = visitServer;
	}

	public List<Visit> getVisitList() {
		return visitList;
	}

	public void setVisitList(List<Visit> visitList) {
		this.visitList = visitList;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getCpage() {
		return cpage;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	public String getTaga() {
		return taga;
	}

	public void setTaga(String taga) {
		this.taga = taga;
	}

	public List<String> getVisitListTow() {
		return visitListTow;
	}

	public void setVisitListTow(List<String> visitListTow) {
		this.visitListTow = visitListTow;
	}

	public String getApply() {
		return apply;
	}

	public void setApply(String apply) {
		this.apply = apply;
	}

	public List<VisitCo> getVisitCoList() {
		return visitCoList;
	}

	public void setVisitCoList(List<VisitCo> visitCoList) {
		this.visitCoList = visitCoList;
	}


}
