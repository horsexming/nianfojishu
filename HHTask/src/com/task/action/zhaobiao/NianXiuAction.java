package com.task.action.zhaobiao;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.zhaobiao.NianXiuServer;
import com.task.util.Util;
import com.tast.entity.zhaobiao.KaoQin;
import com.tast.entity.zhaobiao.NianXiu;

@SuppressWarnings("unchecked")
public class NianXiuAction {
	private NianXiuServer nianXiuServer;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private List list;
	private List listAll;
	//
	private NianXiu nianXiu;
	private KaoQin kaoQin;
	
	public NianXiuServer getNianXiuServer() {
		return nianXiuServer;
	}

	public void setNianXiuServer(NianXiuServer nianXiuServer) {
		this.nianXiuServer = nianXiuServer;
	}
	
//	public void oneyuefen(){
//		nianXiuServer.addKaoqin_3("2017-01", "吴媛");
//	}
	
	/**
	 * 修改汇总记录
	 */
	public String toupdate(){
		kaoQin = nianXiuServer.ById(kaoQin.getId());
		if (kaoQin==null) {
			errorMessage = "信息不存在，请刷新后重试！";
			return "error";
		}
		return "KaoQin_update";
	}
	
	public String updatekq(){
		errorMessage = nianXiuServer.updateKaoqin(kaoQin);
		if ("修改成功".equals(errorMessage))
			url = "nianXiuAction!listnianxiu.action";
		return "error";
	}
	
	public String yuebao(){
		try {
			String yue = Util.getLastMonth("yyyy-MM");
//			String yue = "2018-02"; 
			if(kaoQin!=null&&kaoQin.getYuefen()!=null){
				yue = kaoQin.getYuefen();
			}
			nianXiuServer.addKaoqin_2(yue);
			kaoQin = new KaoQin();
			kaoQin.setYuefen(yue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorMessage = "数据异常： "+e.toString().replaceAll("\"", "\'");
			return "error";
		}
		if (kaoQin != null) {
			ActionContext.getContext().getSession().put("kaoQin", kaoQin);
		} else {
			kaoQin = (KaoQin) ActionContext.getContext().getSession().get(
					"kaoQin");
		}
		Object[] object = nianXiuServer.listnianxiu(kaoQin, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List<KaoQin>) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("nianXiuAction!listnianxiu.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "KaoQin_list";
	}
	
	public String listnianxiu_1() {
		if (kaoQin==null) {
			//String now=Util.getDateTime();
			//Date  timeDate=Util.StringToDate(now, "yyyy-MM");
			String time=Util.getLastMonth("yyyy-MM");
			kaoQin=new KaoQin();
			kaoQin.setYuefen(time);
		}
		//判断有木有记录
		//kaoQin.setYuefen("2014-07");
		listAll=nianXiuServer.Byyufen(kaoQin.getYuefen());
		if (listAll == null || listAll.size() <= 0) {
			nianXiuServer.addKaoqin_2(kaoQin.getYuefen());
		}
		if (kaoQin != null) {
			ActionContext.getContext().getSession().put("kaoQin", kaoQin);
		} else {
			kaoQin = (KaoQin) ActionContext.getContext().getSession().get(
			"kaoQin");
		}
		Object[] object = nianXiuServer.listnianxiu(kaoQin, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List<KaoQin>) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("nianXiuAction!listnianxiu.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "KaoQin_list";
	}
	public String listnianxiu() {
		if (kaoQin != null) {
			ActionContext.getContext().getSession().put("KaoQins", kaoQin);
		} else {
			kaoQin = (KaoQin) ActionContext.getContext().getSession().get(
					"KaoQins");
		}
		if (kaoQin==null) {
			//String now=Util.getDateTime();
			//Date  timeDate=Util.StringToDate(now, "yyyy-MM");
			String time=Util.getLastMonth("yyyy-MM");
			kaoQin=new KaoQin();
			kaoQin.setYuefen(time);
		}else if(kaoQin!=null&&(kaoQin.getYuefen()==null||kaoQin.getYuefen().length()<7)){
			String time=Util.getLastMonth("yyyy-MM");
			kaoQin.setYuefen(time);
		}
		//判断有木有记录
		//kaoQin.setYuefen("2014-07");
		listAll=nianXiuServer.Byyufen(kaoQin.getYuefen());
		if (listAll == null || listAll.size() <= 0) {
			nianXiuServer.addKaoqin_2(kaoQin.getYuefen());
		}
		Object[] object = nianXiuServer.listnianxiu(kaoQin, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List<KaoQin>) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("nianXiuAction!listnianxiu.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "KaoQin_list";
	}
	public String exportExcel() {
		if (kaoQin!=null) {
			nianXiuServer.exportExcel(kaoQin.getYuefen());
		}
		return null;
	}
	public String mingxi() {
		kaoQin=nianXiuServer.ById(kaoQin.getId());
		if (kaoQin==null) {
			errorMessage = "信息不存在，请刷新后重试！";
			return "error";
		}
		return "mingxi";//KaoQin_mingxi.jsp
	}
	
	public String shanchuById(){
		errorMessage = nianXiuServer.shanchuById(kaoQin);
		if("删除成功！".equals(errorMessage))
			url = "nianXiuAction!listnianxiu.action?cpage="+cpage;
		return "error";
	}
	
	public String jiSuanById(){
		errorMessage = nianXiuServer.jiSuanById(kaoQin);
		if("更新成功！".equals(errorMessage))
			url = "nianXiuAction!listnianxiu.action?cpage="+cpage;
		return "error";
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List getListAll() {
		return listAll;
	}

	public void setListAll(List listAll) {
		this.listAll = listAll;
	}

	public NianXiu getNianXiu() {
		return nianXiu;
	}

	public void setNianXiu(NianXiu nianXiu) {
		this.nianXiu = nianXiu;
	}

	public KaoQin getKaoQin() {
		return kaoQin;
	}

	public void setKaoQin(KaoQin kaoQin) {
		this.kaoQin = kaoQin;
	}
	
	

}
