package com.task.action.shizhi;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.shizhi.ProductivityLoadServer;
import com.task.entity.shizhi.ProductivityLoad;

/**
 * 产能负荷系数Action层
 * 
 * @author 唐晓斌
 * 
 */
public class ProductivityLoadAction {
	private ProductivityLoadServer productivityLoadServer;// 技能系数服务层
	private ProductivityLoad productivityLoad;// 技能系数对象
	private List<ProductivityLoad> pLoadList;// 技能系数列表
	

	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	private Float daytype;
	public String showList() {
		if (productivityLoad != null) {
			ActionContext.getContext().getSession().put("productivityLoad",
					productivityLoad);
		} else {// 用来保持分页时带着查询条件
			productivityLoad = (ProductivityLoad) ActionContext.getContext()
					.getSession().get("productivityLoad");
		}
		Map<Integer, Object> map = productivityLoadServer
				.findProductivityLoadsByCondition(productivityLoad, Integer
						.parseInt(cpage), pageSize);
		pLoadList = (List<ProductivityLoad>) map.get(1);// 显示页的技能系数列表
		if (pLoadList != null & pLoadList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("productivityLoadAction_showList.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "pLoad_show";
	}

	public String toupdate() {
		productivityLoad= productivityLoadServer
				.getById(productivityLoad.getId());
		if (productivityLoad != null) {
			return "pLoad_update";

		} else {
			errorMessage = "修改失败,不存在该技能系数！";
		}
		return showList();
	}

	public String update() {
		if(productivityLoad.getWorkTimeLimits()!=null&&daytype!=null){
			float wtl=productivityLoad.getWorkTimeLimits()*daytype;
			productivityLoad.setWorkTimeLimits(Float.parseFloat(String.format("%.3f", wtl)));
		}
		boolean b = productivityLoadServer.update(productivityLoad);
		if (b) {
			successMessage = "修改成功！";
			productivityLoad = null;
			return showList();
		} else {
			errorMessage = "修改失败！";
			return "pLoad_update";
		}
	}

	public String delete() {
		boolean b = productivityLoadServer.deleteById(productivityLoad.getId());
		if (b) {
			successMessage = "删除成功！";
		} else {
			errorMessage = "删除失败！";
		}
		productivityLoad = null;
		return showList();
	}

	/**
	 * 对应productivityLoad,QuotedProcessInfor表重新检索数据
	 * @return
	 */
	 public String updateAll(){
		 boolean b=productivityLoadServer.updateAll();
		 if(b){
			 successMessage="更新完成!";
		 }else{
			 errorMessage = "更新失败,请检查后重试!";
		 }
		 return showList();
	 }
	
	// get set方法

	public String getSuccessMessage() {
		return successMessage;
	}

	public ProductivityLoadServer getProductivityLoadServer() {
		return productivityLoadServer;
	}

	public void setProductivityLoadServer(
			ProductivityLoadServer productivityLoadServer) {
		this.productivityLoadServer = productivityLoadServer;
	}

	public ProductivityLoad getProductivityLoad() {
		return productivityLoad;
	}

	public void setProductivityLoad(ProductivityLoad productivityLoad) {
		this.productivityLoad = productivityLoad;
	}

	public List<ProductivityLoad> getpLoadList() {
		return pLoadList;
	}

	public void setpLoadList(List<ProductivityLoad> pLoadList) {
		this.pLoadList = pLoadList;
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

	public Float getDaytype() {
		return daytype;
	}

	public void setDaytype(Float daytype) {
		this.daytype = daytype;
	}


}
