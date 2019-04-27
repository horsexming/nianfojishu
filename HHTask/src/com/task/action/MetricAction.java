package com.task.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.FlowService;
import com.task.Server.MetricService;
import com.task.Server.bp.ProductService;
import com.task.entity.ZhFlow;
import com.task.entity.ZhMetric;
import com.task.entity.bp.Product;

public class MetricAction extends ActionSupport {
	private Product product;
	private ZhFlow flow;
	private ZhMetric metric;
	private List<ZhMetric> metrics;

	private ProductService productService;
	private FlowService flowService;
	private MetricService metricService;

	//提示信息
	private String errorMessage;
	private String successMessage;

	//添加的页面
	public String addInput(){
		if(product == null||product.getId() == null){
			Map<String, Object> session = ActionContext.getContext().getSession();
			product = (Product) session.get("productSess");
		} else {
			product = productService.get(product.getId());
		}
		if(product == null){
			errorMessage = "配件没有获取到，请关闭后重新打开";
			return SUCCESS;
		}	
		flow = flowService.getFid(flow.getFid());
		if( flow == null){
			errorMessage = "流程没有获取到，请关闭后重新打开";
			return SUCCESS;
		}
		metric = new ZhMetric();
		metric.setXh(metricService.generatedXh(flow));
		metric.setFid(flow.getFid());
		
		metrics = metricService.queryByFid(metric.getFid());
		return INPUT;
	}

	public String add(){
		String writeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString();
		metric.setDatetime(writeDate);
		metricService.save(metric);
		return "toAddInput";
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ZhFlow getFlow() {
		return flow;
	}

	public void setFlow(ZhFlow flow) {
		this.flow = flow;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
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

	public MetricService getMetricService() {
		return metricService;
	}

	public void setMetricService(MetricService metricService) {
		this.metricService = metricService;
	}

	public ZhMetric getMetric() {
		return metric;
	}

	public void setMetric(ZhMetric metric) {
		this.metric = metric;
	}

	public List<ZhMetric> getMetrics() {
		return metrics;
	}

	public void setMetrics(List<ZhMetric> metrics) {
		this.metrics = metrics;
	}
	
	
}
