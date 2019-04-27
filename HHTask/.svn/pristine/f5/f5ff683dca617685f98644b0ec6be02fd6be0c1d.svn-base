package com.task.action.zhiliang;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.zhiliang.ReliabilityTestServer;
import com.task.entity.Users;
import com.task.entity.sop.WaigouDailySheet;
import com.task.entity.zhiliang.ReliabilityTestPro;
import com.task.entity.zhiliang.ReliabilityTestRecord;
import com.task.entity.zhiliang.ReliabilityTestSheet;
import com.task.util.Util;

/**
 * 可靠性测试
 * @author xs-cy
 *
 */
public class ReliabilityTestAction extends ActionSupport{

	private ReliabilityTestServer reliabilityTestServer;

	private List<ReliabilityTestPro> proList;
	private List<ReliabilityTestSheet> sheetList;
	private List<ReliabilityTestRecord> recordList;
	private ReliabilityTestPro pro;
	private ReliabilityTestSheet sheet;
	private ReliabilityTestRecord record;
	private String url;
	private String cpage = "1";
	private String total;
	private Integer pageSize=15;
	private String tag;
	private String pageStatus;
	private String errorMessage;
	private Integer id;
	private List<File> attachments;
	private List<String> attachmentsContentType;
	private List<String> attachmentsFileName;
	private List<String> listString;
	private Integer dsId;//外购件来料日报表:(WaigouDailySheet)
	private String uidsAndLevels;
	
	//前往添加页面
	public String toAddRTS() {
		Users loginUser = Util.getLoginUser();
		if(loginUser==null) {
			errorMessage = "请先登录";
			return "error";
		}
		sheet = new ReliabilityTestSheet();
		
		sheet.setAddTime(Util.getDateTime("yyyy-MM-dd"));
		if(dsId!=null && dsId>0) {
			WaigouDailySheet wds = reliabilityTestServer.findwgdSheetById(dsId);
			sheet.setCompany(loginUser.getDept()); 
			sheet.setMaterialCategory(wds.getWgType());//物料类别
			sheet.setjGname(wds.getProName());//名称
			sheet.setMaterialResource(wds.getGysName());//物料来源--供应商
			sheet.setMarkId(wds.getMarkId()); //件号
			sheet.setLotId(wds.getExamineLot());
		}
		
		Map<Integer, Object> map = reliabilityTestServer.findRTPByCondition(new ReliabilityTestPro(), 0, 0, tag);
		if(map!=null) {
			proList = (List<ReliabilityTestPro>) map.get(2);
		}
		String maxNumber = reliabilityTestServer.getMaxNumber(null);
		sheet.setNumber(maxNumber);
		return "reliabilityTestSheet_add";
	}
	
	public String toaddRTP() {
		return "reliabilityTestPro_add";
	}
	
	/**
	 * 添加测试项目
	 * @return
	 */
	public String addRTP() {
		errorMessage = reliabilityTestServer.saveRTP(pro);
		return "error";
	}
	
	/**
	 * 根据条件获取测试项目列表
	 * @return
	 */
	public String findRTPByCondition() {
		if (pro != null)
			ActionContext.getContext().getSession().put("pror", pro);
		else
			pro = (ReliabilityTestPro) ActionContext.getContext().getSession().get("pror");
		Map<Integer, Object> map = reliabilityTestServer.findRTPByCondition(pro, Integer.parseInt(cpage), pageSize, tag);
		if(map!=null) {
			proList = (List<ReliabilityTestPro>) map.get(2);
			Integer count = (Integer) map.get(1);
			if(count!=null) {
				int pageCount = (count + pageSize - 1) / pageSize;
				setUrl("ReliabilityTestAction!findRTPByCondition.action");
				total = pageCount+ "";
			}
		}
		return "reliabilityTestPro_list";
	}
	
	public String toUpdateRTP() {
		pro = reliabilityTestServer.getRTPById(id);
		return "reliabilityTestPro_update";
	}
	
	public String deleteRTP() {
		errorMessage = reliabilityTestServer.delRTPById(id);
		//setUrl("reliabilityTestAction!findRTPByCondition.action");
		return "error";
	}
	
	/**
	 * 添加可靠性测试单
	 * @return
	 */
	public String addRTS() {
		
		
		/*if(attachments!=null && attachments.size()>0){
			String fileType = null;
			String fileName = null;
			String msg = null;
			StringBuffer fileUrlBuffer = new StringBuffer();
			StringBuffer otherNameBuffer = new StringBuffer();
			for(int i=0;i<attachments.size();i++){
				if(attachments.get(i)==null){
					continue;
				}
				fileUrlBuffer.setLength(0);
				otherNameBuffer.setLength(0);
				//处理文件
				String[] names = attachmentsFileName.get(i).split("\\.");
				if (names != null && names.length >= 2) {
					fileType = names[names.length - 1];
				}
				//Date date = Util.StringToDate(systemFile.getUploadDate(), "yyyy-MM-dd HH:mm:ss SSS");
				fileName = "rts_" + Util.DateToString(new Date(), "yyyyMMddHHmmssSSS") + "." + fileType;
				msg = Util.UploadFile(attachments.get(i), "", fileName, "/upload/file/rts", null);
				//msg = uploadlic(attachments[i], "sysFile", fileName, null);
				if (msg == null || !fileName.equals(msg)) {
					errorMessage = msg;
					return "error";
				}
				if(otherNameBuffer.length()==0){
					otherNameBuffer.append(names[0].trim());
				}else{
					otherNameBuffer.append(","+names[0].trim());
				}
				//处理文件名称
				if(fileUrlBuffer.length()==0){
					fileUrlBuffer.append(fileName.trim());
				}else{
					fileUrlBuffer.append(","+fileName.trim());
				}
				
				recordList.get(i).setTestFile(fileUrlBuffer.toString());
				recordList.get(i).setOtherFileName(otherNameBuffer.toString());
				
			}
			
		}*/
		if(sheet!=null && recordList!=null && recordList.size()>0){
			Iterator<ReliabilityTestRecord> iterator = recordList.iterator();
			while (iterator.hasNext()) {
				ReliabilityTestRecord record2 = iterator.next();
				if(record2==null|| record2.getProName()==null){
					iterator.remove();
				}
			}
		}
		try {
			sheet.setUidsAndLevels(uidsAndLevels);
			errorMessage = reliabilityTestServer.addRTS(sheet, recordList, tag);
		} catch (Exception e) {
			errorMessage  = e.getMessage();
		}
		
		return "error";
	}
	
	public String updateRTS(){
		/*if(attachments!=null && attachments.size()>0){
			String fileType = null;
			String fileName = null;
			String msg = null;
			StringBuffer fileUrlBuffer = new StringBuffer();
			StringBuffer otherNameBuffer = new StringBuffer();
			for(int i=0;i<attachments.size();i++){
				if(attachments.get(i)==null){
					continue;
				}
				fileUrlBuffer.setLength(0);
				otherNameBuffer.setLength(0);
				//处理文件
				String[] names = attachmentsFileName.get(i).split("\\.");
				if (names != null && names.length >= 2) {
					fileType = names[names.length - 1];
				}
				fileName = "rts_" + Util.DateToString(new Date(), "yyyyMMddHHmmssSSS") + "." + fileType;
				msg = Util.UploadFile(attachments.get(i), "", fileName, "/upload/file/rts", null);
				if (msg == null || !fileName.equals(msg)) {
					errorMessage = msg;
					return "error";
				}
				if(otherNameBuffer.length()==0){
					otherNameBuffer.append(names[0].trim());
				}else{
					otherNameBuffer.append(","+names[0].trim());
				}
				//处理文件名称
				if(fileUrlBuffer.length()==0){
					fileUrlBuffer.append(fileName.trim());
				}else{
					fileUrlBuffer.append(","+fileName.trim());
				}
				
				recordList.get(i).setTestFile(fileUrlBuffer.toString());
				recordList.get(i).setOtherFileName(otherNameBuffer.toString());
				
			}
			
		}*/
		if(sheet!=null && recordList!=null && recordList.size()>0){
			Iterator<ReliabilityTestRecord> iterator = recordList.iterator();
			while (iterator.hasNext()) {
				ReliabilityTestRecord record2 = iterator.next();
				if(record2==null|| record2.getProName()==null){
					iterator.remove();
				}
			}
		}
		try {
			sheet.setUidsAndLevels(uidsAndLevels);
			errorMessage = reliabilityTestServer.updateRTS(sheet, recordList, tag);
		} catch (Exception e) {
			errorMessage  = e.getMessage();
		}
		return "error";
	}
	
	public String deleteRTS(){
		errorMessage = reliabilityTestServer.deleteRTS(id, tag);
		setUrl("ReliabilityTestAction!findRTSByCondition.action");
		return ERROR;
	}
	
	public String findRTSByCondition() {
		
		if(sheet!=null) {
			ActionContext.getContext().getSession().put("sheet", sheet);
		}else {
			sheet = (ReliabilityTestSheet) ActionContext.getContext().getSession().get("sheet");
		}
		
		Object[] objects = reliabilityTestServer.queryByCondition(sheet,Integer.parseInt(cpage),pageSize, tag);
		if(objects!=null) {
			Integer count = (Integer) objects[0];
			sheetList = (List<ReliabilityTestSheet>) objects[1];
			if(count!=null) {
				int pageCount = (count + pageSize - 1) / pageSize;
				setUrl("ReliabilityTestAction!findRTSByCondition.action");
				total = pageCount+ "";
			}
		}
		
		return "reliabilityTestSheet_list";
	}
	
	public String getRTSById() {
		sheet= reliabilityTestServer.getRTSById(id);
		if(tag!=null && tag.equals("update")){
			//获取所有项目
			Map<Integer, Object> map = reliabilityTestServer.findRTPByCondition(new ReliabilityTestPro(), 0, 0, tag);
			if(map!=null) {
				proList = (List<ReliabilityTestPro>) map.get(2);
				Set<ReliabilityTestRecord> recordSet = sheet.getRecordSet();
				listString = new ArrayList<String>();
				for (ReliabilityTestRecord reliabilityTestRecord : recordSet) {
					listString.add(reliabilityTestRecord.getProName());
				}
			}
			return "reliabilityTestSheet_update";
		}
		return "reliabilityTestSheet_detail";
	}
	
	public String toSubmitTestPro() {
		sheet = reliabilityTestServer.getRTSById(id);
		return "rts_submitTestPro";
	}
	
	public String submitTestResult() {
		if(attachments!=null && attachments.size()>0){
			String fileType = null;
			String fileName = null;
			String msg = null;
			StringBuffer fileUrlBuffer = new StringBuffer();
			StringBuffer otherNameBuffer = new StringBuffer();
			for(int i=0;i<attachments.size();i++){
				if(attachments.get(i)==null){
					continue;
				}
				fileUrlBuffer.setLength(0);
				otherNameBuffer.setLength(0);
				//处理文件
				String[] names = attachmentsFileName.get(i).split("\\.");
				if (names != null && names.length >= 2) {
					fileType = names[names.length - 1];
				}
				//Date date = Util.StringToDate(systemFile.getUploadDate(), "yyyy-MM-dd HH:mm:ss SSS");
				fileName = "rts_" + Util.DateToString(new Date(), "yyyyMMddHHmmssSSS") + "." + fileType;
				msg = Util.UploadFile(attachments.get(i), "", fileName, "/upload/file/rts", null);
				//msg = uploadlic(attachments[i], "sysFile", fileName, null);
				if (msg == null || !fileName.equals(msg)) {
					errorMessage = msg;
					return "error";
				}
				if(otherNameBuffer.length()==0){
					otherNameBuffer.append(names[0].trim());
				}else{
					otherNameBuffer.append(","+names[0].trim());
				}
				//处理文件名称
				if(fileUrlBuffer.length()==0){
					fileUrlBuffer.append(fileName.trim());
				}else{
					fileUrlBuffer.append(","+fileName.trim());
				}
				
				recordList.get(i).setTestFile(fileUrlBuffer.toString());
				recordList.get(i).setOtherFileName(otherNameBuffer.toString());
				
			}
			
		}
		errorMessage = reliabilityTestServer.submitTestResult(sheet, recordList, tag);
		return "error";
	}
	
	public ReliabilityTestServer getReliabilityTestServer() {
		return reliabilityTestServer;
	}

	public void setReliabilityTestServer(ReliabilityTestServer reliabilityTestServer) {
		this.reliabilityTestServer = reliabilityTestServer;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCpage() {
		return cpage;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ReliabilityTestPro getPro() {
		return pro;
	}

	public void setPro(ReliabilityTestPro pro) {
		this.pro = pro;
	}

	public ReliabilityTestSheet getSheet() {
		return sheet;
	}

	public void setSheet(ReliabilityTestSheet sheet) {
		this.sheet = sheet;
	}

	public ReliabilityTestRecord getRecord() {
		return record;
	}

	public void setRecord(ReliabilityTestRecord record) {
		this.record = record;
	}

	public List<ReliabilityTestPro> getProList() {
		return proList;
	}

	public void setProList(List<ReliabilityTestPro> proList) {
		this.proList = proList;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ReliabilityTestSheet> getSheetList() {
		return sheetList;
	}

	public void setSheetList(List<ReliabilityTestSheet> sheetList) {
		this.sheetList = sheetList;
	}

	public List<ReliabilityTestRecord> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<ReliabilityTestRecord> recordList) {
		this.recordList = recordList;
	}

	public List<File> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<File> attachments) {
		this.attachments = attachments;
	}

	public List<String> getAttachmentsContentType() {
		return attachmentsContentType;
	}

	public void setAttachmentsContentType(List<String> attachmentsContentType) {
		this.attachmentsContentType = attachmentsContentType;
	}

	public List<String> getAttachmentsFileName() {
		return attachmentsFileName;
	}

	public void setAttachmentsFileName(List<String> attachmentsFileName) {
		this.attachmentsFileName = attachmentsFileName;
	}

	public List<String> getListString() {
		return listString;
	}

	public void setListString(List<String> listString) {
		this.listString = listString;
	}

	public Integer getDsId() {
		return dsId;
	}

	public void setDsId(Integer dsId) {
		this.dsId = dsId;
	}

	public String getUidsAndLevels() {
		return uidsAndLevels;
	}

	public void setUidsAndLevels(String uidsAndLevels) {
		this.uidsAndLevels = uidsAndLevels;
	}

	
	
}
