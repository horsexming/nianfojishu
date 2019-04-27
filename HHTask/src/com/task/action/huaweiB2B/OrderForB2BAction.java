package com.task.action.huaweiB2B;

import java.io.IOException;
import java.util.List;

import com.huawei.openapi.openaipexample.client.http.HttpRestClient;
import com.huawei.openapi.openaipexample.client.http.OrderForB2B;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.b2b.HttpRestClientServer;
import com.task.ServerImpl.b2b.HttpRestClientServerImpl;

@SuppressWarnings("serial")
public class OrderForB2BAction extends ActionSupport {

	private HttpRestClientServer hrcServer;
	private OrderForB2B orderForB2B;
	private List list;// 集合
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private String pageStatus;// 页面状态
	private String fbStartDate;// 发布开始时间
	private String fbEndDate;// 发布截止时间
	private String cnStartDate;// 承诺开始时间
	private String cnEndDate;// 承诺截止时间
	private String operateType;// 接收/遣返(y/n)

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 100;

	/***
	 * 所有PO信息
	 */
	public String findAllPo() {
		try {
			if (orderForB2B == null) {
				orderForB2B = new OrderForB2B();
				orderForB2B.setPoStatus("all");
				orderForB2B.setColTaskOrPoStatus("all");
				orderForB2B.setShipmentStatus("all");
				orderForB2B.setPoSubType("P");
			}
			if (orderForB2B != null) {
				ActionContext.getContext().getSession().put("orderForB2B",
						orderForB2B);
			} else {
				orderForB2B = (OrderForB2B) ActionContext.getContext()
						.getSession().get("orderForB2B");
			}

			if (fbStartDate != null) {
				ActionContext.getContext().getSession().put("fbStartDate",
						fbStartDate);
			} else {
				fbStartDate = (String) ActionContext.getContext().getSession()
						.get("fbStartDate");
			}
			if (fbEndDate != null) {
				ActionContext.getContext().getSession().put("fbEndDate",
						fbEndDate);
			} else {
				fbEndDate = (String) ActionContext.getContext().getSession()
						.get("fbEndDate");
			}
			if (cnStartDate != null) {
				ActionContext.getContext().getSession().put("cnStartDate",
						cnStartDate);
			} else {
				cnStartDate = (String) ActionContext.getContext().getSession()
						.get("cnStartDate");
			}
			if (cnEndDate != null) {
				ActionContext.getContext().getSession().put("cnEndDate",
						cnEndDate);
			} else {
				cnEndDate = (String) ActionContext.getContext().getSession()
						.get("cnEndDate");
			}

			Object[] obj = hrcServer
					.getList(Integer.parseInt(cpage), pageSize, orderForB2B,
							fbStartDate, fbEndDate, cnStartDate, cnEndDate);
			list = (List) obj[0];
			int count = (Integer) obj[1];
			pageSize = (Integer) obj[2];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("OrderForB2BAction!findAllPo.action?pageStatus="
					+ pageStatus);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "findPoLineList_all";
	}

	/****
	 * 通过id查询PO明细
	 * 
	 * @return
	 */
	public String findPOById() {
		orderForB2B = hrcServer.findPOById(orderForB2B);
		if (orderForB2B != null) {
			return "showOrderForB2BDetail";
		}
		return ERROR;
	}

	/***
	 * 接受/遣返订单
	 * 
	 * @return
	 */
	public String signBackPOList() {
		try {
			errorMessage = hrcServer.addPrice(orderForB2B);
			if ("true".equals(errorMessage)) {
				errorMessage = hrcServer.signBackPOList(orderForB2B,
						operateType);
			}
			url = "OrderForB2BAction!findPOById.action?orderForB2B.poHeaderId="
					+ orderForB2B.getPoHeaderId() + "&orderForB2B.poReleaseId="
					+ orderForB2B.getPoReleaseId()+ "&orderForB2B.poLineId="
					+ orderForB2B.getPoLineId();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	/***
	 * 修改订单
	 * 
	 * @return
	 */
	public String updateOrderForB2B() {
		try {
			errorMessage = hrcServer.updateOrderForB2B(orderForB2B);
			url = "OrderForB2BAction!findPOById.action?orderForB2B.poHeaderId="
					+ orderForB2B.getPoHeaderId() + "&orderForB2B.poReleaseId="
					+ orderForB2B.getPoReleaseId()+ "&orderForB2B.poLineId="
					+ orderForB2B.getPoLineId();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
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

	public OrderForB2B getOrderForB2B() {
		return orderForB2B;
	}

	public void setOrderForB2B(OrderForB2B orderForB2B) {
		this.orderForB2B = orderForB2B;
	}

	public String getFbStartDate() {
		return fbStartDate;
	}

	public void setFbStartDate(String fbStartDate) {
		this.fbStartDate = fbStartDate;
	}

	public String getFbEndDate() {
		return fbEndDate;
	}

	public void setFbEndDate(String fbEndDate) {
		this.fbEndDate = fbEndDate;
	}

	public String getCnStartDate() {
		return cnStartDate;
	}

	public void setCnStartDate(String cnStartDate) {
		this.cnStartDate = cnStartDate;
	}

	public String getCnEndDate() {
		return cnEndDate;
	}

	public void setCnEndDate(String cnEndDate) {
		this.cnEndDate = cnEndDate;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public HttpRestClientServer getHrcServer() {
		return hrcServer;
	}

	public void setHrcServer(HttpRestClientServer hrcServer) {
		this.hrcServer = hrcServer;
	}

}
