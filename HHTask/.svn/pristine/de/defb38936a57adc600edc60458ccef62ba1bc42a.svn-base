package com.task.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.KeHuManYiDiaoChaServer;
import com.task.entity.ClientManagement;
import com.task.entity.KeHuManYiDiaoCha;
import com.task.entity.QuestionnairePerson;

public class KeHuManYiDiaoChaAction {

	private KeHuManYiDiaoCha khmydc;
	private KeHuManYiDiaoChaServer khmydcserver;
	private List<KeHuManYiDiaoCha> khmydcList;
	private List<ClientManagement> cmList;
	
	private String errorMessage;
	private String successMessage;
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String status;
	private String tag;
	
	private File[] attachment;
	private String[] attachmentContentType;
	private String[] attachmentFileName;
	
	public String initadd(){
		cmList = khmydcserver.queryAllClient();
		return "kehumanyi";
	}
	public String addkhmydc(){
		boolean bool =	khmydcserver.add(khmydc, attachment, attachmentFileName,status);
		errorMessage = "添加失败!";
		if(bool){
			errorMessage = "添加成功!";
			return "findAllkhmydcList";
		}
		return "kehumanyi";
	}
	public String findAllkhmydcList(){
		if(khmydc!=null){
			ActionContext.getContext().getSession().put("khmydc", khmydc);
		}else{
			khmydc=(KeHuManYiDiaoCha) ActionContext.getContext().getSession().get("qp");
		}
		cmList = khmydcserver.queryAllClient();
		Map<Integer, Object> map=new HashMap<Integer,Object>();
		map=khmydcserver.findAllkhmydclist(khmydc, Integer.parseInt(cpage), pageSize,status);
		khmydcList=(List<KeHuManYiDiaoCha>) map.get(1);
		if(khmydcList!=null && khmydcList.size()>0){
			int count=(Integer)map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");  
			this.setUrl("KeHuManYiDiaoChaAction_findAllkhmydcList.action?status="+status);
			
		}else{
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "kehumanyi";
	}
	public KeHuManYiDiaoCha getKhmydc() {
		return khmydc;
	}
	public void setKhmydc(KeHuManYiDiaoCha khmydc) {
		this.khmydc = khmydc;
	}
	public KeHuManYiDiaoChaServer getKhmydcserver() {
		return khmydcserver;
	}
	public void setKhmydcserver(KeHuManYiDiaoChaServer khmydcserver) {
		this.khmydcserver = khmydcserver;
	}
	public List<KeHuManYiDiaoCha> getKhmydcList() {
		return khmydcList;
	}
	public void setKhmydcList(List<KeHuManYiDiaoCha> khmydcList) {
		this.khmydcList = khmydcList;
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
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
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
	public List<ClientManagement> getCmList() {
		return cmList;
	}
	public void setCmList(List<ClientManagement> cmList) {
		this.cmList = cmList;
	}
	
	
}
