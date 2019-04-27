package com.task.action.sop;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.sop.PurchasedPartsInputServer;
import com.task.entity.sop.PurchasedPartsInput;
import com.task.entity.sop.WaigouWaiweiPlan;

public class PurchasedPartsInputAction {
	private PurchasedPartsInputServer purchasedPartsInputServer;
	private PurchasedPartsInput purchasedPartsInput;
	private List<PurchasedPartsInput> purchasedPartsInputList;
	private WaigouWaiweiPlan wwPlan;
	private List<WaigouWaiweiPlan> wwPlanList;// 集合
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private String pageStatus;// 页面标记
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	public String showList() {
		if (purchasedPartsInput != null) {
			ActionContext.getContext().getSession().put("purchasedPartsInput",
					purchasedPartsInput);
		} else {// 用来保持分页时带着查询条件
			purchasedPartsInput = (PurchasedPartsInput) ActionContext
					.getContext().getSession().get("purchasedPartsInput");
		}
		Map<Integer, Object> map = purchasedPartsInputServer
				.findPurchasedPartsInputsByCondition(purchasedPartsInput,
						Integer.parseInt(cpage), pageSize);
		purchasedPartsInputList = (List<PurchasedPartsInput>) map.get(1);// 显示页的技能系数列表
		if (purchasedPartsInputList != null
				& purchasedPartsInputList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("purchasedPartsInputAction_showList.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "purchasedPartsInput_show";
	}

	/**
	 * 批量添加外购件入库申请
	 * 
	 * @return
	 */
	public String addSome() {
		String acArrivalTime = null;
		if (purchasedPartsInput != null
				&& purchasedPartsInput.getTime() != null) {
			acArrivalTime = purchasedPartsInput.getTime();
		}
		String msg = purchasedPartsInputServer.add(wwPlanList, acArrivalTime);
		if (msg != null && !msg.equals("true")) {
			successMessage = msg;
		} else {
			successMessage = "入库申请成功!";
		}
		return "addSomeOk";
	}

	/**
	 * 跳往修改页面（只能修改数量）
	 * 
	 * @return
	 */
	public String toupdate() {
		purchasedPartsInput = purchasedPartsInputServer
				.getById(purchasedPartsInput.getId());
		return "purchasedPartsInput_update";
	}

	/**
	 * 修改申请单数量
	 * 
	 * @return
	 */
	public String update() {
		boolean b = purchasedPartsInputServer.updateCount(purchasedPartsInput);
		return showList();
	}

	public String delete() {
		boolean b = purchasedPartsInputServer.delete(purchasedPartsInput);
		purchasedPartsInput = null;
		return showList();
	}

	public PurchasedPartsInputServer getPurchasedPartsInputServer() {
		return purchasedPartsInputServer;
	}

	public void setPurchasedPartsInputServer(
			PurchasedPartsInputServer purchasedPartsInputServer) {
		this.purchasedPartsInputServer = purchasedPartsInputServer;
	}

	public PurchasedPartsInput getPurchasedPartsInput() {
		return purchasedPartsInput;
	}

	public void setPurchasedPartsInput(PurchasedPartsInput purchasedPartsInput) {
		this.purchasedPartsInput = purchasedPartsInput;
	}

	public WaigouWaiweiPlan getWwPlan() {
		return wwPlan;
	}

	public void setWwPlan(WaigouWaiweiPlan wwPlan) {
		this.wwPlan = wwPlan;
	}

	public List<WaigouWaiweiPlan> getWwPlanList() {
		return wwPlanList;
	}

	public void setWwPlanList(List<WaigouWaiweiPlan> wwPlanList) {
		this.wwPlanList = wwPlanList;
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

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public List<PurchasedPartsInput> getPurchasedPartsInputList() {
		return purchasedPartsInputList;
	}

	public void setPurchasedPartsInputList(
			List<PurchasedPartsInput> purchasedPartsInputList) {
		this.purchasedPartsInputList = purchasedPartsInputList;
	}

}
