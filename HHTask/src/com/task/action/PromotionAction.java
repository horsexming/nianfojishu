package com.task.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.PromotionServer;
import com.task.entity.Promotion;

public class PromotionAction extends ActionSupport{

	private Promotion pn;
	private List<Promotion> pnList;
	private PromotionServer pnserver;
	private Integer id;
	
	private String errorMessage;
	private String successMessage;
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String status;
	private File[] attachment;
	private String[] attachmentContentType;
	private String[] attachmentFileName;
	private String fatherPartNumber = "";
	//添加
	public String addpn(){
		if(pn!=null){

			errorMessage =	pnserver.add(pn,attachment,
					attachmentFileName, fatherPartNumber);
			if("true".equals(errorMessage)){
				errorMessage = "";
				return "showAllpnlist";
			}
		}
		return "Promotion_add";
	}
	//查询所有(分页)
	public String showAllpnlist(){
		if("del_true".equals(errorMessage)){
			errorMessage = "删除成功!";
		}else if("del_error".equals(errorMessage)){
			errorMessage = "删除失败!";
		}
		int count=pnserver.getcont();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		pnList=pnserver.FindAllPromotion(Integer.parseInt(cpage), pageSize);
		if(pnList!=null){
			this.setUrl("PromotionAction_showAllpnlist.action");
			
		}else{
			errorMessage="没有人员调动信息";
		}
		return "Promotion_List";
	
	}
	//条件查询
	public String findpnList(){
		if(pn!=null){
			ActionContext.getContext().getSession().put("pn", pn);
		}else{
			pn=(Promotion) ActionContext.getContext().getSession().get("pn");
		}
		Map<Integer, Object> map=new HashMap<Integer,Object>();
		
		map=pnserver.findPromotionByCondition(pn, Integer.parseInt(cpage), pageSize);
		pnList=(List<Promotion>) map.get(1);
		if(pnList!=null && pnList.size()>0){
			int count=(Integer)map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");  
			if(pn!=null && pn.getUserId()!=null){
				id = pn.getUserId();
			}
			this.setUrl("PromotionAction_findpnList.action?status="+status+"&id="+id);
		}else{
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "Promotion_List";
	}
	//删除
	public String delpn(){
		if(pn!=null){
			if(pnserver.del(pn)){
				errorMessage = "del_true";
			}else{
				errorMessage = "del_error";
			}
		}
		return  "showAllpnlist";	
	}
	
	public String updatepn(){
		if(pn!=null){
			if(pnserver.upadte(pn,attachment,
					attachmentFileName, fatherPartNumber)){
				errorMessage = "更新成功!";
				status = "show";
			}else{
				errorMessage = "更新失败!";
				status = "update";
			}
		}
		return "Promotion_show";
	}
	//根据userId
	public String showpnbyuserId(){
		pnList =pnserver.findPromotionbyuserId(id);
		if(pnList!=null && pnList.size()>0){
			errorMessage = "";
		}else{
			errorMessage = "该员工目前尚未有过晋升";
		}
		return "Promotion_List";
	}
	
	//根据Id
	public String findpnById(){
		if(id!=null && id>0){
			pn = pnserver.findPromotionbyId(id);
		}
		
		return "Promotion_show";
	}
	
	
	public Promotion getPn() {
		return pn;
	}
	public void setPn(Promotion pn) {
		this.pn = pn;
	}
	public List<Promotion> getPnList() {
		return pnList;
	}
	public void setPnList(List<Promotion> pnList) {
		this.pnList = pnList;
	}
	public PromotionServer getPnserver() {
		return pnserver;
	}
	public void setPnserver(PromotionServer pnserver) {
		this.pnserver = pnserver;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public File[] getAttachment() {
		return attachment;
	}
	public void setAttachment(File[] attachment) {
		this.attachment = attachment;
	}
	public String[] getAttachmentContentType() {
		return attachmentContentType;
	}
	public void setAttachmentContentType(String[] attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
	}
	public String[] getAttachmentFileName() {
		return attachmentFileName;
	}
	public void setAttachmentFileName(String[] attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}
	public String getFatherPartNumber() {
		return fatherPartNumber;
	}
	public void setFatherPartNumber(String fatherPartNumber) {
		this.fatherPartNumber = fatherPartNumber;
	}
	
	
	
	
	
	
}
