package com.task.action.bp;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.bp.ProductService;
import com.task.entity.bp.Product;
 
@SuppressWarnings("serial")
public class ProductAction extends ActionSupport {
	private ProductService productService;
	private String month;
	private List<Product> products;
	
	private String errorMessage;
	private String successMessage;// 成功信息

	
	//查看所有生产计划
	public String list(){
		if(null == month){
			return SUCCESS;
		}
		products = productService.getMonth(month);
		return SUCCESS;
	}
	

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
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


	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}

	
}
