package com.task.action.pro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.opensymphony.xwork2.ActionSupport;

import com.task.Server.UserServer;

import com.task.Server.pro.ProProductServer;
import com.task.entity.pro.ProProduct;


public class ProProductAction extends ActionSupport {
	private ProProduct proProduct;
	private List<ProProduct> proProductList;
	private String errorMessage;
	private String successMessage;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	private ProProductServer proProductServer;
	private UserServer userServer;
	
	//获得项目人员添加
	public String getProProductAddPage(){
		return "getProProductAddPage_success";
	}
	//添加项目记录
	public String addProProduct(){
		try {
			String result=proProductServer.addProProduct(this.proProduct);
			if("true".equals(result)){
				//MKUtil.writeJSON(true, "操作成功", null);
				return "addProProduct_success";
				//return null;
			}else{
				//MKUtil.writeJSON(false, "操作失败", null);
				return "addProProduct_failure";
			}
		} catch (Exception e) {
			//MKUtil.writeJSON(false, "操作失败", null);
			return "addProProduct_failure";
		}
	};
	
	//删除项目记录
	public String deleteProProduct(){
		try {
			this.proProduct=proProductServer.getProProductById(this.proProduct.getId());
			String result=proProductServer.deleteProProduct(this.proProduct);
			if("true".equals(result)){
				return "deleteProProduct_success";
			}else{
				return "deleteProProduct_failure";
			}
		} catch (Exception e) {
			return "deleteProProduct_failure";
		}
	};
	
	//获得更新页面
	public String getProProductUpdatePage(){
		this.proProduct=proProductServer.getProProductById(this.proProduct.getId());
		return "getProProductUpdatePage_success";
	}
	
	//更新项目记录
	public String updateProProduct(){
		try {
			ProProduct proProduct=proProductServer.getProProductById(this.proProduct.getId());
			String name=this.proProduct.getName();
			String code=this.proProduct.getCode();
			if(name!=null && !"".equals(name)){
				proProduct.setName(name);
			}
			if(code!=null && !"".equals(code)){
				proProduct.setCode(code);
			}
			String result=proProductServer.updateProProduct(proProduct);
			if("true".equals(result)){
				return "updateProProduct_success";
			}else{
				return "updateProProduct_failure";
			}
		} catch (Exception e) {
			return "updateProProduct_failure";
		}
	};
	
	//获得项目记录
	public String getProProductById(){
		this.proProduct=proProductServer.getProProductById(this.proProduct.getId());
		return null;
	};
	
	//获得项目记录集合
	public String findAllProProductByproId(){
		Map map=new HashMap();
		Integer proId=this.proProduct.getProId();
		map.put("proId", proId);
		Object[] object = proProductServer.findAllProProductByproId(map, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			proProductList = (List<ProProduct>) object[0];
			if (proProductList != null && proProductList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("proProductAction!findAllProProductByproId.action?proProduct.proId="+proId);
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else{
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "findAllProProductByproId_success";
	}
	public ProProduct getProProduct() {
		return proProduct;
	}
	public void setProProduct(ProProduct proProduct) {
		this.proProduct = proProduct;
	}
	public List<ProProduct> getProProductList() {
		return proProductList;
	}
	public void setProProductList(List<ProProduct> proProductList) {
		this.proProductList = proProductList;
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
	public UserServer getUserServer() {
		return userServer;
	}
	public void setUserServer(UserServer userServer) {
		this.userServer = userServer;
	}
	public ProProductServer getProProductServer() {
		return proProductServer;
	}
	public void setProProductServer(ProProductServer proProductServer) {
		this.proProductServer = proProductServer;
	}
	
	
}
