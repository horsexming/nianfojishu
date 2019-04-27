
package com.task.action.codetranslation;


import java.io.File;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.codetranslation.CodeTranslationServer;
import com.task.entity.codetranslation.CodeTranslation;
import com.task.util.MKUtil;

public class CodeTranslationAction extends ActionSupport{
	private String successMessage;
	private String errorMessage;
	private CodeTranslation codeTranslation;
	private List<CodeTranslation> codeTranslationList;
	private CodeTranslationServer codeTranslationServer;
	private File uploadFile;
	private File exportFile;
	private File[] attachment;
	private String[] attachmentContentType;
	private String[] attachmentFileName;
	private String cpage = "1";
	private int pageSize = 15;
	private String total;
	private String url;
	public String code;
	public String tag="all";
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	

	public String toAdd(){
		if(codeTranslation.getId()!=null){
			codeTranslation = codeTranslationServer.findById(codeTranslation.getId());
			ActionContext.getContext().put("codeTranslation",codeTranslation);
		}
		if(tag.equals("sys")){
			return "codeTranslation_sadd";
		}else if("fenmo".equals(tag)){
			return "PanelSize_updatefenmo";
		}else{
			return "codeTranslation_add";
		}
	}
	public String add(){
		boolean b = codeTranslationServer.save(codeTranslation);
		if(b){
			successMessage="添加成功";
		}else{
			errorMessage="添加失败";
			return "error";
		}
		if(tag.equals("sys")){
			return "codeTranslation_toSysList";
		}else{
			return "codeTranslation_toList";
		}
	}
	
	public String update(){
		boolean b = codeTranslationServer.update(codeTranslation);
		if(b){
			successMessage="修改成功";
		}else{
			errorMessage="修改失败";
			return "error";
		}
		if(tag.equals("sys")){
			return "codeTranslation_toSysList";
		}else{
			return "codeTranslation_toList";
		}
	}
	
	public String delete(){
		codeTranslation = codeTranslationServer.findById(codeTranslation.getId());
		codeTranslationServer.delete(codeTranslation);
		if(codeTranslation.getType().equals("sys")){
			return "codeTranslation_toSysList";	
		}else{
			return "codeTranslation_toList";
		}
	}
	public String checkName(){
		 CodeTranslation c = codeTranslationServer.findByKeyCode(code);
		 if(c!=null)
	        {
			 MKUtil.writeJSON("err");
	        }
	        else
	        {
	        	MKUtil.writeJSON("ok");
	        }
		return "codeTranslation_add";
	}
	public String findAll(){
		Map<Integer, Object> map = codeTranslationServer
		.findAll(codeTranslation, Integer
				.parseInt(cpage), pageSize,tag);
		codeTranslationList = (List<CodeTranslation>) map.get(1);
		if (codeTranslationList != null & codeTranslationList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("codeTranslationAction_findAll.action?tag="+tag);
		}else{
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		if(tag.equals("sys")){
			return "codeTranslation_sList";
		}else if("fenmo".equals(tag)){
			return "PanelSize_fenmodz";
		}else{
			return "codeTranslation_List";
		}
	}
	
	public String importFile(){
		errorMessage = codeTranslationServer.importFile(uploadFile);
		if(errorMessage.equals("true")){
			return "codeTranslation_toList";
		}
		return "error";
	}
	public String exportFile(){
		errorMessage = codeTranslationServer.exportFile(exportFile);
		if(errorMessage.equals("true")){
			return "codeTranslation_toList";
		}
		return "error";
	}
	public String QueryCode(){
		if (codeTranslation != null) {
			ActionContext.getContext().getSession().put("codeTranslation",
					codeTranslation);
		} else {
			codeTranslation = (CodeTranslation) ActionContext.getContext()
			.getSession().get("codeTranslation");
		}
		Map<Integer, Object> map = codeTranslationServer
		.QueryCode(codeTranslation, Integer
				.parseInt(cpage), pageSize,tag);
		codeTranslationList = (List<CodeTranslation>) map.get(1);
		if (codeTranslationList != null & codeTranslationList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("codeTranslationAction_QueryCode.action");
		}else{
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		if(codeTranslation.getType().equals("sys")){
			return "codeTranslation_sList";
		}else if("粉末".equals(codeTranslation.getType())){
			return "PanelSize_fenmodz";
		}else{
			return "codeTranslation_List";
		}
	}
	//
	
	public CodeTranslation getCodeTranslation() {
		return codeTranslation;
	}
	
	public void setCodeTranslation(CodeTranslation codeTranslation) {
		this.codeTranslation = codeTranslation;
	}

	public CodeTranslationServer getCodeTranslationServer() {
		return codeTranslationServer;
	}

	public void setCodeTranslationServer(CodeTranslationServer codeTranslationServer) {
		this.codeTranslationServer = codeTranslationServer;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public List<CodeTranslation> getCodeTranslationList() {
		return codeTranslationList;
	}
	public void setCodeTranslationList(List<CodeTranslation> codeTranslationList) {
		this.codeTranslationList = codeTranslationList;
	}
	public String getCpage() {
		return cpage;
	}
	public void setCpage(String cpage) {
		this.cpage = cpage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
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
	public File getExportFile() {
		return exportFile;
	}

	public void setExportFile(File exportFile) {
		this.exportFile = exportFile;
	}
	
	
}
