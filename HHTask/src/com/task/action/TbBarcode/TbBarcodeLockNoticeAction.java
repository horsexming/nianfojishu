package com.task.action.TbBarcode;

import java.io.File;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.TbBarcode.TbBarcodeLockNoticeServer;
import com.task.entity.TbBarcode.TbBarcodeLockNotice;
import com.task.util.MKUtil;

public class TbBarcodeLockNoticeAction {


	private TbBarcodeLockNoticeServer tblnserver;// Server层
	private TbBarcodeLockNotice tbln;// 对象
	private List<TbBarcodeLockNotice> tblnList;// 集合
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private String pageStatus;// 页面状态

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	private File[] attachment;
	private String[] attachmentContentType;
	private String[] attachmentFileName;
	
	public String findAlltbln(){
		if (tbln != null) {
			ActionContext.getContext().getSession().put("tbln", tbln);
		} else {
			tbln = (TbBarcodeLockNotice) ActionContext.getContext().getSession()
					.get("tbln");
		}
		Object[] obj =	tblnserver.findAlltblnList(tbln, Integer.parseInt(cpage), pageSize, pageStatus);
		tblnList =	(List<TbBarcodeLockNotice>) obj[0];
		int count = (Integer) obj[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		this.setUrl("TbBarcodeLockNoticeAction_findAlltbln.action?statue=find");
		return "tbln_showList";
	}
	public String findtblnById(){
		tbln =	tblnserver.findtblnById(id);
		return "tbln_show";
	}
	public String updatetbln(){
		String fileName =	MKUtil.saveFile(attachment, attachmentFileName, "TbBarcodeLockNotice");
		tbln.setFileName(fileName);
		tblnserver.updatetbln(tbln);
		return "findAlltbln";
	}
	
	public TbBarcodeLockNoticeServer getTblnserver() {
		return tblnserver;
	}
	public void setTblnserver(TbBarcodeLockNoticeServer tblnserver) {
		this.tblnserver = tblnserver;
	}
	public TbBarcodeLockNotice getTbln() {
		return tbln;
	}
	public void setTbln(TbBarcodeLockNotice tbln) {
		this.tbln = tbln;
	}
	public List<TbBarcodeLockNotice> getTblnList() {
		return tblnList;
	}
	public void setTblnList(List<TbBarcodeLockNotice> tblnList) {
		this.tblnList = tblnList;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPageStatus() {
		return pageStatus;
	}
	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
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
	
	
	
}
