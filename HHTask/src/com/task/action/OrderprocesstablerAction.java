package com.task.action;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.OrderprocesstablerServer;
import com.task.entity.Orderprocesstabler;

public class OrderprocesstablerAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Orderprocesstabler orderprocesstabler;// 订单流程表
	private String errorMessage;// 错误消息
	private OrderprocesstablerServer orderprocesstablerServer;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	//添加订单流程
	@Override
	public String execute() throws Exception {

		orderprocesstablerServer.saveOrderprocesstabler(orderprocesstabler);
		return super.execute();
	}

	// 构造方法
	public Orderprocesstabler getOrderprocesstabler() {
		return orderprocesstabler;
	}

	public void setOrderprocesstabler(Orderprocesstabler orderprocesstabler) {
		this.orderprocesstabler = orderprocesstabler;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public OrderprocesstablerServer getOrderprocesstablerServer() {
		return orderprocesstablerServer;
	}

	public void setOrderprocesstablerServer(
			OrderprocesstablerServer orderprocesstablerServer) {
		this.orderprocesstablerServer = orderprocesstablerServer;
	}

}
