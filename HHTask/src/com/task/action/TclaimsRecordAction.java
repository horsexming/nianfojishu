package com.task.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.TclaimsRecordService;
import com.task.entity.TclaimsRecord;
import com.task.entity.Users;
import com.task.util.MKUtil;

public class TclaimsRecordAction extends ActionSupport {
	private List<TclaimsRecord> tclaimsRecords;
	private TclaimsRecord tclaimsRecord;
	
	private File[] attachment;
	private String[] attachmentContentType;
	private String[] attachmentFileName;
	private Integer[] ids; 
	
	private TclaimsRecordService tclaimsRecordService;

	/**
	 * 列出某个详细
	 * @return
	 */
	public String list(){
		tclaimsRecords = tclaimsRecordService.get(tclaimsRecord.getRoot());
		return "list";
	}

	/**
	 * 分析
	 * @return
	 */
	public String analysisInput() {
		tclaimsRecords = tclaimsRecordService.get(tclaimsRecord.getRoot());
		return "analysisInput";
	}
	
	/**
	 * 文件上传
	 * @return
	 */
	public String fileUpload(){
		try {
			String str = MKUtil.saveFile(attachment, attachmentFileName, "abc");
			MKUtil.writeJSON(true, "上传成功!", str);
		} catch (Exception e) {
			MKUtil.writeJSON(false, "上传失败:" + e.getMessage() + ".请重新上传!", null);
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 分析提交
	 * @return
	 */
	public String update(){
		Map session = ActionContext.getContext().getSession();
		Users user = (Users) session.get(TotalDao.users);
		
		try {
			tclaimsRecordService.updateAll(ids, tclaimsRecord.getReason(), user.getName(), tclaimsRecord.getReasonFilename(), tclaimsRecord.getResponsibility());
			MKUtil.writeJSON(true, "提交成功", null);
		} catch (Exception e) {
			MKUtil.writeJSON(false, "提交失败:" + e.getMessage(), null);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通知
	 * @return
	 */
	public String notificationInput(){
		tclaimsRecords = tclaimsRecordService.getNotification(tclaimsRecord.getRoot());
		return "notificationInput";
	}
	
	public String handle(){
		String fileName = MKUtil.saveFile(attachment, attachmentFileName, "tclaimsrecord");
		System.out.println(fileName);
		
		Map session = ActionContext.getContext().getSession();
		Users user = (Users) session.get(TotalDao.users);
		
		tclaimsRecordService.updateHandle(ids, tclaimsRecord.getHandle(),user.getName(), fileName);
		tclaimsRecord = tclaimsRecordService.get(ids[0]);
		return "rhandleInput";
	}
	
	/**
	 * 整改
	 * @return
	 */
	public String handleInput() {
		tclaimsRecords = tclaimsRecordService.get(tclaimsRecord.getRoot());
		return "handleInput";
	}
	
	public TclaimsRecordService getTclaimsRecordService() {
		return tclaimsRecordService;
	}

	public void setTclaimsRecordService(TclaimsRecordService tclaimsRecordService) {
		this.tclaimsRecordService = tclaimsRecordService;
	}

	public List<TclaimsRecord> getTclaimsRecords() {
		return tclaimsRecords;
	}

	public void setTclaimsRecords(List<TclaimsRecord> tclaimsRecords) {
		this.tclaimsRecords = tclaimsRecords;
	}


	public TclaimsRecord getTclaimsRecord() {
		return tclaimsRecord;
	}


	public void setTclaimsRecord(TclaimsRecord tclaimsRecord) {
		this.tclaimsRecord = tclaimsRecord;
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

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

}
