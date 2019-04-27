package com.task.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.FlowService;
import com.task.Server.bp.ProductService;
import com.task.entity.ZhFlow;
import com.task.entity.bp.Product;

public class FlowAction extends ActionSupport {
	private FlowService flowService;
	private List<ZhFlow> flows;//实体类有些不规范，直接用别人的
	private Product product;
	private ProductService productService;
	
	//提示信息
	private String errorMessage;
	private String successMessage;
	
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	public String listInput() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if(product == null && session.get("productSess") == null){
			errorMessage = "不存在要采购的材料";
			return SUCCESS;
		}
		if(session.get("productSess") == null){
			session.put("productSess", productService.get(product.getId()));
		}
		Object[] object = flowService.getList(Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			flows = (List<ZhFlow>) object[0];
			if (flows != null && flows.size() > 0) {
				int sum = (Integer) object[1];
				int pageCount = (sum + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("Flow_listInput.action");
				errorMessage = null;
			} else {
				errorMessage = "抱歉!您查询的计划不存在!";
			}
		} else {
			errorMessage = "抱歉!没有您查询的计划信息!";
		}
		return INPUT;
	}

	public FlowService getFlowService() {
		return flowService;
	}

	public void setFlowService(FlowService flowService) {
		this.flowService = flowService;
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

	public List<ZhFlow> getFlows() {
		return flows;
	}

	public void setFlows(List<ZhFlow> flows) {
		this.flows = flows;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	
}
