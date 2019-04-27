package com.task.action.sop.ycl;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.sop.ycl.PanelSizeServer;
import com.task.entity.codetranslation.CodeTranslation;
import com.task.entity.sop.ycl.PanelSize;

public class PanelSizeAction {

	private PanelSize panelSize;
	private List<PanelSize> panelSizeList;
	private PanelSizeServer panelSzieServer;
	private Float houdu; 
	private Integer id;
	private String status;// 

	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String tag;
	
	public String toaddpanelSize(){
		return "PanelSize_add";
	}
	
	public String addpanelSize(){
		if(panelSize!=null){
			panelSzieServer.addPanelSize(panelSize);
		}
		return "findAllpanelSizeList";
	}
	public String findAllpanelSizeList(){
		if (panelSize != null) {
			ActionContext.getContext().getSession().put("panelSize",
					panelSize);
		} else {// 用来保持分页时带着查询条件
			panelSize = (PanelSize) ActionContext.getContext()
					.getSession().get("panelSize");
		}
		Object[] obj = panelSzieServer.findAllPanelSize(panelSize, Integer.parseInt(cpage), pageSize, status,houdu);
			panelSizeList = (List<PanelSize>) obj[0];
			int count = (Integer) obj[1];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("PanelSizeAction_findAllpanelSizeList.action?status="+status);
		return "PanelSize_List";
	}
	public String findPanelSizeById(){
		if(id!=null){
			panelSize = panelSzieServer.findPanelSizeById(id);
		}
		return "PanelSize_show";
	}
	public String delPanelSize(){
		if(panelSize!=null){
			panelSzieServer.delPanelSize(panelSize);
		}
		return "findAllpanelSizeList";
	}
	public String updatepanelSize(){
		panelSzieServer.updatePanelSize(panelSize);
		return "findAllpanelSizeList";
	}
	
	
	
	
	public PanelSize getPanelSize() {
		return panelSize;
	}
	public void setPanelSize(PanelSize panelSize) {
		this.panelSize = panelSize;
	}
	public List<PanelSize> getPanelSizeList() {
		return panelSizeList;
	}
	public void setPanelSizeList(List<PanelSize> panelSizeList) {
		this.panelSizeList = panelSizeList;
	}
	public PanelSizeServer getPanelSzieServer() {
		return panelSzieServer;
	}
	public void setPanelSzieServer(PanelSizeServer panelSzieServer) {
		this.panelSzieServer = panelSzieServer;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Float getHoudu() {
		return houdu;
	}
	public void setHoudu(Float houdu) {
		this.houdu = houdu;
	}

}
