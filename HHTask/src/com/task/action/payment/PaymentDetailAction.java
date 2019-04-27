package com.task.action.payment;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.payment.PaymentDetailServer;
import com.task.entity.payment.PaymentDetail;
import com.task.entity.payment.PaymentVoucher;
import com.task.util.MKUtil;

public class PaymentDetailAction  extends ActionSupport{
	private PaymentDetailServer paymentDetailServer;
	private  PaymentDetail detail;
	private  PaymentVoucher paymentVoucher;
	
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	private List<Map> maps;
	private Object[] objects;
	
	private String errorMessage;
	private String successMessage;
	private Integer paymentid;
	private String test;
	private List list;
	private Map<String,Object> map;
	
	
	
	 
	 
	public PaymentVoucher getPaymentVoucher() {
		return paymentVoucher;
	}
	public void setPaymentVoucher(PaymentVoucher paymentVoucher) {
		this.paymentVoucher = paymentVoucher;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public Integer getPaymentid() {
		return paymentid;
	}
	public void setPaymentid(Integer paymentid) {
		this.paymentid = paymentid;
	}
	public List<Map> getMaps() {
		return maps;
	}
	public void setMaps(List<Map> maps) {
		this.maps = maps;
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

	public Object[] getObjects() {
		return objects;
	}
	public void setObjects(Object[] objects) {
		this.objects = objects;
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
	public PaymentDetailServer getPaymentDetailServer() {
		return paymentDetailServer;
	}
	public void setPaymentDetailServer(PaymentDetailServer paymentDetailServer) {
		this.paymentDetailServer = paymentDetailServer;
	}
	public PaymentDetail getDetail() {
		return detail;
	}
	public void setDetail(PaymentDetail detail) {
		this.detail = detail;
	}
	//查看付款明细
	public String findPaymentDetail(){
		if (detail != null) {
			ActionContext.getContext().getSession().put("detail", detail);
		} else {
			detail = (PaymentDetail) ActionContext.getContext().getSession().get(
					"detail");
		}
		Object[] object = this.paymentDetailServer.findPaymentVoucher(detail, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			maps = (List<Map>) object[0];
			if (maps != null && maps.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				System.out.println(test);
				if(test!=null&&!"".equals(test)){
					this.setUrl("paymentDetailAction_findPaymentDetail.action?test="+test);
				}else{
					this.setUrl("paymentDetailAction_findPaymentDetail.action");
				}
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "findPaymentDetail";
	}
	//查询合同编号
	public String findNumber(){
		 this.maps = this.paymentDetailServer.findNumber();
		 for (int i = 0; i < maps.size(); i++) {
			 String number =  (String) maps.get(i).get("number");
		}
		 	 MKUtil.writeJSON(true, "",this.maps );//把结果传到页面
		 return null;
	}
	//添加付款明细
	public String addPaymentDetail(){
		boolean bool = this.paymentDetailServer.addPaymentDetail(detail);
		if(bool){
			this.successMessage = "添加成功!";
		}
		return "addPaymentDetail1";
	}
	
	//删除付款明细
	public String delPaymentDetail(){
		this.paymentDetailServer.delPaymentDetail(detail);
		return "delPaymentDetail";
	}
	//根据编号查询付款款明细
	public String findPaymentDetailById(){
		this.detail = this.paymentDetailServer.findPaymentDetailById(detail);
		return "findPaymentDetailById";
	}
	
	//根据编号查询合同编号
	public String findNumber1(){
		 this.maps = this.paymentDetailServer.findNumber1(paymentid);
		 for (int i = 0; i < maps.size(); i++) {
			 String number =  (String) maps.get(i).get("number");
		}
		 	 MKUtil.writeJSON(true, "",this.maps );//把结果传到页面
		 return null;
	}
	
	//更新付款明细
	public String updatePaymentDetail(){
		this.paymentDetailServer.updatePaymentVoucher(this.detail);
		this.successMessage = "修改成功!";
		return "updatePaymentVoucher";
	}
	
	//查看借款明细
	public String findPaymentDetail1(){
		
		return "";
	}

	//根据外键查询收款单位
	public String findUnitname(){
		 this.maps = this.paymentDetailServer.findUnitname(this.paymentid);
		 for (int i = 0; i < maps.size(); i++) {
			 String unitname =  (String) maps.get(i).get("unitname");
		}
		 MKUtil.writeJSON(true, "",this.maps );//把结果传到页面
		 return null;
	}
	
	//查看明细
	public String salPaymentDetail(){
		this.map = this.paymentDetailServer.findDetail(this.paymentVoucher);
		this.paymentVoucher= (PaymentVoucher) map.get("voucher2");
		this.detail = (PaymentDetail) map.get("detail2");
		return "salPaymentDetail";
	}
	

}
