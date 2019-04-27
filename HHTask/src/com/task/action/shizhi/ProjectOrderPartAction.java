package com.task.action.shizhi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.shizhi.ProjectOrderPartServer;
import com.task.entity.shizhi.ProjectOrder;
import com.task.entity.shizhi.ProjectOrderPart;
import com.task.entity.shizhi.TryMake;
import com.task.util.MKUtil;

/**
 * 项目订单Action层
 * 
 * @author 唐晓斌
 * 
 */
public class ProjectOrderPartAction {
	private ProjectOrderPart projectOrderPart;// 项目需求单零件对象
	private ProjectOrder projectOrder;// 项目需求单对象
	private TryMake tryMake;// 零件
	private List<ProjectOrderPart> poPartList;// 项目需求单零件列表
	private List<ProjectOrder> pOrderList;// 项目需求单列表
	private Integer projectOrderId;//项目需求单id ajax使用
	private ProjectOrderPartServer projectOrderPartServer;// 项目订单服务

	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 7;

	/**
	 * 分页显示项目订单
	 * 
	 * @return
	 */
	public String showList() {
		if (projectOrderPart != null) {
			ActionContext.getContext().getSession().put("projectOrderPart",
					projectOrderPart);
		} else {// 用来保持分页时带着查询条件
			projectOrderPart = (ProjectOrderPart) ActionContext.getContext()
					.getSession().get("projectOrderPart");
		}

		Map<Integer, Object> map = projectOrderPartServer
				.findProjectOrderPartsByCondition(projectOrderPart, Integer
						.parseInt(cpage), pageSize);
		poPartList = (List<ProjectOrderPart>) map.get(1);// 显示页的技能系数列表
		if (poPartList != null & poPartList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("projectOrderPartAction_showList.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "popart_show";
	}

	/**
	 * 跳往添加页面
	 * 
	 * @return
	 */
	public String toadd() {
		pOrderList = projectOrderPartServer.findProjectOrderAll();
		return "popart_add";
	}
	/**
	 * ajax通过项目需求单获取项目试制零件列表
	 */
	@SuppressWarnings("unchecked")
	public void getTryMakeByPorderId(){
		List<TryMake> tmList= projectOrderPartServer.finTryMakeListByPorderId(projectOrderId);
		List newList=new ArrayList();
		if(tmList.size()>0){
			for(TryMake tm:tmList){
				tm.setProTryMakeScore(null);
				newList.add(tm);
			}
		}
		MKUtil.writeJSON(newList);
	}

	public String add() {
		if (tryMake != null && tryMake.getId() != null&&tryMake.getId() !=0) {
			tryMake = projectOrderPartServer.getTryMakeById(tryMake
					.getId());
			    projectOrderPart.setTmId(tryMake.getId());
				projectOrderPart.setQpId(tryMake.getId());
				projectOrderPart.setFatherId(tryMake.getFatherId());
				projectOrderPart.setRootId(tryMake.getRootId());
				projectOrderPart.setMarkId(tryMake.getMarkId());
				projectOrderPart.setPartName(tryMake.getPartName());
			
		} else {
			this.errorMessage = "添加失败,请选择零件号";
			return toadd();
		}
		if (projectOrder != null && projectOrder.getId() != null&&projectOrder.getId() !=0) {
			projectOrder = projectOrderPartServer
					.getProjectOrderById(projectOrder.getId());
			if (projectOrder != null) {
				projectOrderPart.setProjectOrder(projectOrder);
				projectOrderPart.setProName(projectOrder.getProName());
				projectOrderPart.setMonth(projectOrder.getMonth());
			}
		}
		boolean b = projectOrderPartServer.add(projectOrderPart);
		if (b) {
			this.errorMessage = "添加成功";
			projectOrderPart.setPartNum(null);// 数字和对象不参与查询
			projectOrderPart.setFatherId(null);
			projectOrderPart.setRootId(null);
			projectOrderPart.setQpId(null);
			projectOrderPart.setProjectOrder(null);
//			return showList();
			this.setUrl("projectOrderPartAction_showList.action");
			return "error";
		} else {
			this.errorMessage = "添加失败";
			return toadd();
		}
	}

	/**
	 * 跳往修改页面
	 * 
	 * @return
	 */
	public String toupdate() {
		if (projectOrderPart != null && projectOrderPart.getId() != null) {
			projectOrderPart = projectOrderPartServer.getById(projectOrderPart
					.getId());
			if (projectOrderPart == null) {
				errorMessage = "修改失败没有该项目订单零件";
				return showList();
			}else{
				boolean b=projectOrderPartServer.canBeChange(projectOrderPart
					.getId());
				if(!b){
					errorMessage = "该零件的项目需求单为审核中或者已同意,不可修改";
					return "error";
				}
				pOrderList = projectOrderPartServer.findProjectOrderAll();
				return "popart_update";
			}
		}else{
			errorMessage = "修改失败没有改项目订单";
			return "error";
		}
		
	}

	public String update() {
		boolean b = projectOrderPartServer.update(projectOrderPart);
		if (b) {
			this.errorMessage = "修改成功";
			projectOrderPart = null;
			return "error";
		} else {
			this.errorMessage = "修改失败";
			return "error";
		}
	}

	public String delete() {
		if (projectOrderPart != null && projectOrderPart.getId() != null) {
			boolean b1=projectOrderPartServer.canBeChange(projectOrderPart
					.getId());
				if(!b1){
					errorMessage = "该零件的项目需求单为审核中或者已同意,不可删除";
					return showList();
				}
			boolean b2 = projectOrderPartServer.deleteById(projectOrderPart
					.getId());
			if (b2) {
				projectOrderPart = null;
				successMessage = "删除成功";
			} else {
				errorMessage = "删除失败没有改项目订单";
			}
		}
		return showList();
	}

	// get和set方法
	public ProjectOrder getProjectOrder() {
		return projectOrder;
	}

	public void setProjectOrder(ProjectOrder projectOrder) {
		this.projectOrder = projectOrder;
	}

	public List<ProjectOrder> getpOrderList() {
		return pOrderList;
	}

	public void setpOrderList(List<ProjectOrder> pOrderList) {
		this.pOrderList = pOrderList;
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

	public ProjectOrderPart getProjectOrderPart() {
		return projectOrderPart;
	}

	public void setProjectOrderPart(ProjectOrderPart projectOrderPart) {
		this.projectOrderPart = projectOrderPart;
	}

	public List<ProjectOrderPart> getPoPartList() {
		return poPartList;
	}

	public void setPoPartList(List<ProjectOrderPart> poPartList) {
		this.poPartList = poPartList;
	}

	public ProjectOrderPartServer getProjectOrderPartServer() {
		return projectOrderPartServer;
	}

	public void setProjectOrderPartServer(
			ProjectOrderPartServer projectOrderPartServer) {
		this.projectOrderPartServer = projectOrderPartServer;
	}

	public TryMake getTryMake() {
		return tryMake;
	}

	public void setTryMake(TryMake tryMake) {
		this.tryMake = tryMake;
	}

	public Integer getProjectOrderId() {
		return projectOrderId;
	}

	public void setProjectOrderId(Integer projectOrderId) {
		this.projectOrderId = projectOrderId;
	}

	



}
