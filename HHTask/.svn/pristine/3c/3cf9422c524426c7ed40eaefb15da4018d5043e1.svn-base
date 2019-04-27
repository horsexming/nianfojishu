package com.task.action.unpasskp;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.unpasskp.ProductUnPassKpServer;
import com.task.entity.unpasskp.ProductUnPassKp;

public class ProductUnPassKpAction {
    private ProductUnPassKpServer productUnPassKpServer;
    private ProductUnPassKp productUnPassKp;
    private List<ProductUnPassKp> productUnPassKpList;
    //分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	private String errorMessage;// 错误消息
	private String successMessage;// 错误消息
	/**
	 * 分页显示未开票通过产品
	 * @return
	 */
	public String showList(){
		if (productUnPassKp != null) {
			ActionContext.getContext().getSession().put("productUnPassKp",
					productUnPassKp);
		    } else {//用来保持分页时带着查询条件
		    	productUnPassKp = (ProductUnPassKp) ActionContext.getContext().getSession().get("productUnPassKp");
		      }
		if(productUnPassKp!=null&&productUnPassKp.getStatus()!=null&&productUnPassKp.getStatus().equals("全部")){
			productUnPassKp.setStatus(null);
		}
		Map<Integer, Object> map = productUnPassKpServer.findProductUnPassKpByCondition(
				productUnPassKp, Integer.parseInt(cpage), pageSize);
		productUnPassKpList = (List<ProductUnPassKp>) map.get(1);// 显示页的技能系数列表
			if (productUnPassKpList != null & productUnPassKpList.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("productUnPassKpAction_showList.action");
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}	
		return "unpasskpshow";
	}
	/**
	 * 
	 * @return
	 */
	public String toupdate(){
		productUnPassKp=productUnPassKpServer.getById(productUnPassKp.getId());
		if(productUnPassKp!=null){
			return "unpasskpupdate";
		}else{
			productUnPassKp=null;
			successMessage="没有找到目标产品";
			return showList();
		}
	}
	public String update(){
	    String msg;
		try {
			msg = productUnPassKpServer.update(productUnPassKp);
			if(msg.equals("true")){
				successMessage="修改成功";
				return showList();
			}else{
				successMessage=msg;
				return toupdate();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			successMessage=e.getMessage();
			return toupdate();
		}
	}
	public String delete(){
		boolean b=productUnPassKpServer.delete(productUnPassKp);
		if(b){
			successMessage="删除成功";
		}else{
			successMessage="删除失败";
		}
		return showList();
	}
	
	public ProductUnPassKpServer getProductUnPassKpServer() {
		return productUnPassKpServer;
	}
	public void setProductUnPassKpServer(ProductUnPassKpServer productUnPassKpServer) {
		this.productUnPassKpServer = productUnPassKpServer;
	}
	public ProductUnPassKp getProductUnPassKp() {
		return productUnPassKp;
	}
	public void setProductUnPassKp(ProductUnPassKp productUnPassKp) {
		this.productUnPassKp = productUnPassKp;
	}
	public List<ProductUnPassKp> getProductUnPassKpList() {
		return productUnPassKpList;
	}
	public void setProductUnPassKpList(List<ProductUnPassKp> productUnPassKpList) {
		this.productUnPassKpList = productUnPassKpList;
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
	
	
 
}
