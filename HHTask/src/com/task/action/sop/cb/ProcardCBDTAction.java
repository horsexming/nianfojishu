package com.task.action.sop.cb;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.sop.cb.ProcardCBDTSever;
import com.task.entity.OrderManagement;
import com.task.entity.ProductManager;
import com.task.entity.sop.Procard;
import com.task.entity.sop.cb.OrderCBDT;
import com.task.entity.sop.cb.ProcardCBDT;
import com.task.entity.sop.cb.ProductCBDT;
import com.task.util.MKUtil;

public class ProcardCBDTAction extends ActionSupport{
	
	private ProcardCBDT procardCBDT;
	private ProductCBDT productCBDT;
	private OrderCBDT orderCBDT;
	private ProcardCBDTSever cbdtSever;
	private List<ProcardCBDT> pdtList;
	private List<ProductCBDT> pttList;
	private List<OrderCBDT> ortList;
	private Procard procard;
	private ProductManager product;
	private OrderManagement order;
	private Integer id;
	
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private String status;// 页面状态
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	//procard生产成本动态
	public void findProcardCBDT(){
		try {
			if(id!=null && id>0){
				procard = cbdtSever.getprocardById(id);
				pdtList = cbdtSever.findProcardCBDT(id);
				Float addCB = cbdtSever.findMaxaddcb(id);
				Object[] obj = {procard,pdtList,addCB};
				MKUtil.writeJSON(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//产品成本动态
	public void findproductCBDT(){
		try {
			if(id!=null && id>0){
				product = cbdtSever.getProductById(id);
				pttList = cbdtSever.findProductCBDT(id);
				Float addCB = cbdtSever.findMaxaddcb1(id);
				Object[] obj = {product,pttList,addCB};
				MKUtil.writeJSON(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//订单成本动态
	public void findOrderCBDT(){
		try {
			if(id!=null && id>0){
				order = cbdtSever.getOrderById(id);
				ortList = cbdtSever.findOrderCBDT(id);
				Float addCB = cbdtSever.findMaxaddcb2(id);
				Object[] obj = {order,ortList,addCB};
				MKUtil.writeJSON(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ProcardCBDT getProcardCBDT() {
		return procardCBDT;
	}
	public void setProcardCBDT(ProcardCBDT procardCBDT) {
		this.procardCBDT = procardCBDT;
	}
	public ProductCBDT getProductCBDT() {
		return productCBDT;
	}
	public void setProductCBDT(ProductCBDT productCBDT) {
		this.productCBDT = productCBDT;
	}
	public OrderCBDT getOrderCBDT() {
		return orderCBDT;
	}
	public void setOrderCBDT(OrderCBDT orderCBDT) {
		this.orderCBDT = orderCBDT;
	}
	public ProcardCBDTSever getCbdtSever() {
		return cbdtSever;
	}
	public void setCbdtSever(ProcardCBDTSever cbdtSever) {
		this.cbdtSever = cbdtSever;
	}
	public List<ProcardCBDT> getPdtList() {
		return pdtList;
	}
	public void setPdtList(List<ProcardCBDT> pdtList) {
		this.pdtList = pdtList;
	}
	public List<ProductCBDT> getPttList() {
		return pttList;
	}
	public void setPttList(List<ProductCBDT> pttList) {
		this.pttList = pttList;
	}
	public List<OrderCBDT> getOrtList() {
		return ortList;
	}
	public void setOrtList(List<OrderCBDT> ortList) {
		this.ortList = ortList;
	}
	public Procard getProcard() {
		return procard;
	}
	public void setProcard(Procard procard) {
		this.procard = procard;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public ProductManager getProduct() {
		return product;
	}
	public void setProduct(ProductManager product) {
		this.product = product;
	}
	public OrderManagement getOrder() {
		return order;
	}
	public void setOrder(OrderManagement order) {
		this.order = order;
	}
	

	
	
}
