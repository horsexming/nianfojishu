package com.task.action.unpasskp;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.unpasskp.ProductUnqualifiedServer;
import com.task.entity.GoodsStore;
import com.task.entity.unpasskp.ProductUnqualified;
import com.task.util.MKUtil;

public class ProductUnqualifiedAction {
	private ProductUnqualified productUnqualified;
	private List<ProductUnqualified> productUnqualifiedList;
	private ProductUnqualifiedServer productUnqualifiedServer;
	private GoodsStore goodsStore;// 入库表
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
		if (productUnqualified != null) {
			ActionContext.getContext().getSession().put("productUnqualified",
					productUnqualified);
		    } else {//用来保持分页时带着查询条件
		    	productUnqualified = (ProductUnqualified) ActionContext.getContext().getSession().get("productUnqualified");
		      }
		if(productUnqualified!=null&&productUnqualified.getSource()!=null&&productUnqualified.getSource().equals("全部")){
			productUnqualified.setSource(null);
		}
		Map<Integer, Object> map = productUnqualifiedServer.findProductUnqualifiedByCondition(
				productUnqualified, Integer.parseInt(cpage), pageSize);
		productUnqualifiedList = (List<ProductUnqualified>) map.get(1);// 显示页的技能系数列表
			if (productUnqualifiedList != null & productUnqualifiedList.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("productUnqualifiedAction_showList.action");
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}	
		return "unqualifiedshow";
	}
	public String toapply(){
		productUnqualified=productUnqualifiedServer.getById(productUnqualified.getId());
		return "unqualifiedtoruku";
	}
	/**
	 * 手动入库
	 * 
	 * @return
	 */
	public String addSdrk() {
		try {
			boolean b=productUnqualifiedServer.saveSgrk(goodsStore,productUnqualified.getId());
			if(b){
				MKUtil.writeJSON(true, "入库成功", goodsStore.getGoodsStoreId());
			}else{
				MKUtil.writeJSON(false, "入库失败:", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "入库失败:" + e.getMessage(), null);
		}
		return null;
	}
	
	public ProductUnqualified getProductUnqualified() {
		return productUnqualified;
	}
	public void setProductUnqualified(ProductUnqualified productUnqualified) {
		this.productUnqualified = productUnqualified;
	}
	public List<ProductUnqualified> getProductUnqualifiedList() {
		return productUnqualifiedList;
	}
	public void setProductUnqualifiedList(
			List<ProductUnqualified> productUnqualifiedList) {
		this.productUnqualifiedList = productUnqualifiedList;
	}
	public ProductUnqualifiedServer getProductUnqualifiedServer() {
		return productUnqualifiedServer;
	}
	public void setProductUnqualifiedServer(
			ProductUnqualifiedServer productUnqualifiedServer) {
		this.productUnqualifiedServer = productUnqualifiedServer;
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
	public GoodsStore getGoodsStore() {
		return goodsStore;
	}
	public void setGoodsStore(GoodsStore goodsStore) {
		this.goodsStore = goodsStore;
	}
	
	
}
