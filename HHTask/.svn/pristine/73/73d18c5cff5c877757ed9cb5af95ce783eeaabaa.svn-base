package com.task.action.setupcheck;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.FileCopyUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.UserServer;
import com.task.Server.setupcheck.SetupCheckServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.ServerImpl.setupcheck.SetupCheckServerImpl;
import com.task.entity.setupcheck.SetupCheck;
import com.task.entity.setupcheck.TrackRecord;
import com.task.util.RtxUtil;
import com.task.util.Util;

@SuppressWarnings("serial")
public class SetupCheckAction extends ActionSupport{
	private SetupCheckServer setupCheckServer;
	private SetupCheck setupCheck;
	private List<SetupCheck> setupCheckList;
	private List<SetupCheck> setupCheckList1;
	private List<SetupCheck> setupCheckList2;
	private List<SetupCheck> setupCheckList3;
	private List<SetupCheck> setupCheckList4;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private String tag;
	private String nopass;
	private String nooperate="yes";
	private TrackRecord trackRecord;
	private List<TrackRecord> trackRecordList;
	//分页
	private String cpage = "1"; 
	private int pageSize = 15;
	private String total;
	private String url;
	//文件上传
	private File longFile;
	private String longFileFileName;
	private String longFileContentType;
	
	private File shortFile;
	private String shortFileFileName;
	private String shortFileContentType;
	public String toAdd(){
		if(setupCheck.getId()!=null){
			setupCheck = setupCheckServer.findById(setupCheck.getId());
		}
			return "setupCheck_add";
	}
	public String add(){
		String msg = "true";
		String fileName = null;
		if(shortFile!=null){
			String fileType = null;
			String[] names = shortFileFileName.split("\\.");
			if (names != null && names.length >= 2) {
				fileType = names[names.length - 1];
			}

			fileName = "detail"
					+ Util.DateToString(Util.StringToDate(Util.getDateTime(), "yyyy-MM-dd HH:mm:ss"),
							"yyyyMMddHHmmss") + "." + fileType;
			msg = uploadlic(shortFile, "detail", fileName, setupCheck.getShortFile());

		}
		if (msg != null && msg.equals("true")) {
			setupCheck.setShortFile(fileName);
			setupCheck.setStauts("待处理");
			boolean b = setupCheckServer.save(setupCheck);
			return "setupCheck_toList";	
		} else {
			successMessage = msg;
		}
		errorMessage="添加失败"; 
		return "error";
}
	public String toUpdate(){
		if(setupCheck.getId()!=null){
			setupCheck = setupCheckServer.findById(setupCheck.getId());
		}
		return "setupCheck_add";
	}
	
	public String update(){
		String msg = "true";
		String fileName = null;
		if(shortFile!=null){
			String fileType = null;
			String[] names = shortFileFileName.split("\\.");
			if (names != null && names.length >= 2) {
				fileType = names[names.length - 1];
			}

			fileName = "detail"
					+ Util.DateToString(Util.StringToDate(Util.getDateTime(), "yyyy-MM-dd HH:mm:ss"),
							"yyyyMMddHHmmss") + "." + fileType;
			msg = uploadlic(shortFile, "detail", fileName, setupCheck.getShortFile());

		}
		if (msg != null && msg.equals("true")) {
			setupCheck.setShortFile(fileName);
		} else {
			successMessage = msg;
		}
		if(setupCheck.getShortFile()==null){
			SetupCheck s= setupCheckServer.findById(setupCheck.getId());
			setupCheck.setShortFile(s.getShortFile());
		}
		setupCheck.setStauts("待处理");
		boolean b = setupCheckServer.update(setupCheck);
		return "setupCheck_toList";	
	}
	public String delete(){
		if(setupCheckServer.delete(setupCheckServer.findById(setupCheck.getId()))){
				return "setupCheck_toList";
			
		}else{
			errorMessage="删除失败";
			return "error";
		}
		
		
	}
	 public String findAll(){
		   Map<Integer, Object> map = setupCheckServer
			.findAll(setupCheck, Integer
					.parseInt(cpage), pageSize);
		   setupCheckList = (List<SetupCheck>) map.get(1);
			if (setupCheckList != null & setupCheckList.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("setupCheckAction_findAll.action");
			}else{
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
			setupCheckList1 = setupCheckServer.findWaitDeal(setupCheck, "待处理");
			setupCheckList2 = setupCheckServer.findWaitDeal(setupCheck, "处理中");
			setupCheckList3 = setupCheckServer.findWaitDeal(setupCheck, "待确认");
			setupCheckList4 = setupCheckServer.findWaitDeal(setupCheck, "打回");
			return "setupCheck_List";//setupCheckList.jsp
	   }
	 	/*
	 	 * 查看措施
	 	 * */
	 public String toDetail(){
		 if(setupCheck.getId()!=null){
				setupCheck = setupCheckServer.findById(setupCheck.getId());
			}
		 if(setupCheck.getStauts().equals("待处理")){
			 return "setupCheck_addW";
		 }
		 else{
			 return "setupCheck_toShow";
			 }
	 }
	 public String toUpdateFile(){
		 if(setupCheck.getId()!=null){
				setupCheck = setupCheckServer.findById(setupCheck.getId());
			}
		 if(setupCheck.getShortPerson()==null&&setupCheck.getLongPerson()==null){
			errorMessage="无措施详情";
			 return "error"; 
		 }
		 else{
			 return "setupCheck_toFile";
			 }
	 }
		/*
		 * 添加措施
		 *
		 * 
		 * */
	 public String toAddWay() {
			setupCheck = setupCheckServer
					.findById(setupCheck.getId());
			if (setupCheck == null) {
				successMessage = "没有找到!";
			if(tag.equals("self")){
				return "setupCheck_tosList";
			}else{
				return "setupCheck_toList";
			}
			} else {
				return "setupCheck_addW";
			}

		}
	 public String updateWay(){
		 boolean b = setupCheckServer.update1(setupCheck);
		 if(tag.equals("self")){
				return "setupCheck_tosList";
			}else{
			 return "setupCheck_toList";
			 }
	 }
	 public String addWay(){
		 if(setupCheck.getShortPerson()==null&&setupCheck.getLongPerson()==null){
		String msg = "true";
		String fileName = null;
		if(longFile!=null){
			String fileType = null;
			String[] names = longFileFileName.split("\\.");
			if (names != null && names.length >= 2) {
				fileType = names[names.length - 1];
			}

			fileName = "longWay"
					+ Util.DateToString(Util.StringToDate(Util.getDateTime(), "yyyy-MM-dd HH:mm:ss"),
							"yyyyMMddHHmmss") + "." + fileType;
			msg = uploadlic(longFile, "longWay", fileName, setupCheck.getLongFile());

		}
		if (msg != null && msg.equals("true")) {
			setupCheck.setLongFile(fileName);
			boolean b = setupCheckServer.update2(setupCheck);
			
		} else {
			successMessage = msg;
		}
		 }else{
			 errorMessage="请添加措施详情";
			 return "error";
		 }
		if(tag.equals("self")){
			return "setupCheck_tosList";
		}else{
		 return "setupCheck_toList";
		 }
	 }
	 /*
	  * 添加追踪记录页面
	  */
	 public String toAddTrackRecord(){
		 if(setupCheck.getId()!=null&&!"".equals(setupCheck.getId())){
			 setupCheck = setupCheckServer.findById(setupCheck.getId());
			 return "trackRecord_add";
		 }else{
			 errorMessage="没有找到该体系审核";
		 }
		 return "error";
	 }
	 /*
	  * 添加追踪记录
	  */
	 public String addTrackRecord(){
		 String msg = "true";
			String fileName = null;
			if(shortFile!=null){
				String fileType = null;
				String[] names = shortFileFileName.split("\\.");
				if (names != null && names.length >= 2) {
					fileType = names[names.length - 1];
				}

				fileName = "trackRecord"
						+ Util.getDateTime("yyyyMMdd") + "." + fileType;
				msg = uploadlic(shortFile, "trackRecord", fileName, trackRecord.getFileName());

			}
			if (msg != null && msg.equals("true")) {
				trackRecord.setFileName(fileName);
				if(setupCheckServer.save(trackRecord)){
					errorMessage="添加成功";
					this.setUrl("SetupCheckAction_findAllForZhuizong.action");
				}else{
					errorMessage="添加失败";
				}
				
			} else {
				errorMessage = msg;
			}
		 
		 return "error";
	 }
	 /*
	  * 关闭处理
	  */
	 public String close(){
		 String news = setupCheckServer.findSamename(setupCheck.getId());
		 if("true".equals(news)){
			 setupCheck = setupCheckServer
				.findById(setupCheck.getId());
			 setupCheck.setStauts("已确认");
			 if(setupCheckServer.update(setupCheck)){
				return "setupCheck_toList"; 
			 }else{
				 errorMessage = "更新失败";
				 return "error";
			 }
		 }else{
			 errorMessage = news;
			 return "error";
		 }
	 }
	 
	 public String back(){
		 SetupCheck s = setupCheckServer.findById(setupCheck.getId());
		 if(s.getLongFile()!=null){
			 s.setStauts("打回");
			 if(setupCheckServer.update(s)){
				return "setupCheck_toList"; 
			 }
		 }else{
			 errorMessage="确认失败，无措施文件";
			 return "error"; 
			 }
		 return "error";
	 }
	 public String findAllForZhuizong(){
		   Map<Integer, Object> map = setupCheckServer
			.findAllForZhuizong(setupCheck, Integer
					.parseInt(cpage), pageSize);
		   setupCheckList = (List<SetupCheck>) map.get(1);
			if (setupCheckList != null & setupCheckList.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("SetupCheckAction_findAllForZhuizong.action");
			}else{
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
			return "setupCheck_zhuizong";
	 }
	 public String findtrackRecordByid(){
		 if(setupCheck.getId()!=null&&!"".equals(setupCheck.getId())){
			 trackRecordList = setupCheckServer.findtrackRecordByid(setupCheck.getId());
			 return "trackRecord_List";
		 }else{
			 errorMessage="找不到该体系审核";
		 }
		 return "error";
	 }
	 public String findAllByName(){
		   Map<Integer, Object> map = setupCheckServer
			.findAllByUser(setupCheck, Integer
					.parseInt(cpage), pageSize);
		   setupCheckList = (List<SetupCheck>) map.get(1);
			if (setupCheckList != null & setupCheckList.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("SetupCheckAction_findAllByName.action");
			}else{
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
			if(nooperate.equals("nooperate"))
			{
				return "setupCheck_noSelfList";
			}else{
				return "setupCheck_selfList";
			}
	   }
	 /*
	  * 处理跟踪
	  */
	 public String genzong(){
		 if(setupCheck.getId()!=null&&!"".equals(setupCheck.getId())){
			 String news = setupCheckServer.updateGenzong(setupCheck.getId());
			 if("true".equals(news)){
				 return "setupCheck_tosList";
			 }else{
				 errorMessage = news;
			 }
		 }else{
			 errorMessage = "未找到该体系审核";
		 }
		 return "error";
	 }
	//文件上传 
	 public String uploadlic(File file, String dirName, String fileName,
				String deletefilename) {
			if (file != null) {
				// 打开存放上传文件的位置
				String path = ServletActionContext.getServletContext().getRealPath(
						"/upload/file/" + dirName);
				File file1 = new File(path);
				if (!file1.exists()) {
					file1.mkdirs();// 如果不存在文件夹就创建
				}
				// 删除源文件
				if (deletefilename != null) {
					File old = new File(path + "/" + deletefilename);
					if (old != null) {
						old.delete();
					}
				}
				// 将证书写入文件夹中
				InputStream is = null;
				// OutputStream os = null;
				try {
					is = new FileInputStream(file);
					File file2 = new File(path + "/" + fileName);
					if (file2.exists()) {
						file2.delete();// 将原证书删掉
					}
					// os = new FileOutputStream(path + "/" + licenseFileName);
					// 上传文件到服务器
					String fileRealPath = path + "/" + fileName;
					File uploadFile = new File(fileRealPath);
					try {
						FileCopyUtils.copy(file, uploadFile);
					} catch (Exception e) {
						return "上传文件失败!";
					}

					// byte[] b = new byte[1024*10];
					// int length = 0;
					// while (-1 != (length = is.read(b, 0, b.length))) {
					// os.write(b);
					// }
					successMessage = "上传成功！";
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					return "找不到文件！";
				} catch (IOException e) {
					// TODO Auto-generated catch block
					return "文件输入出错！";
				} finally {
					try {
						// os.close();
						is.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return "true";
			}
			return "文件为空";
		}
	public SetupCheckServer getSetupCheckServer() {
		return setupCheckServer;
	}

	public void setSetupCheckServer(SetupCheckServer setupCheckServer) {
		this.setupCheckServer = setupCheckServer;
	}

	public SetupCheck getSetupCheck() {
		return setupCheck;
	}

	public void setSetupCheck(SetupCheck setupCheck) {
		this.setupCheck = setupCheck;
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
	public List<SetupCheck> getSetupCheckList() {
		return setupCheckList;
	}
	public void setSetupCheckList(List<SetupCheck> setupCheckList) {
		this.setupCheckList = setupCheckList;
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
	public File getLongFile() {
		return longFile;
	}
	public void setLongFile(File longFile) {
		this.longFile = longFile;
	}
	public String getLongFileFileName() {
		return longFileFileName;
	}
	public void setLongFileFileName(String longFileFileName) {
		this.longFileFileName = longFileFileName;
	}
	public String getLongFileContentType() {
		return longFileContentType;
	}
	public void setLongFileContentType(String longFileContentType) {
		this.longFileContentType = longFileContentType;
	}
	public List<SetupCheck> getSetupCheckList1() {
		return setupCheckList1;
	}
	public void setSetupCheckList1(List<SetupCheck> setupCheckList1) {
		this.setupCheckList1 = setupCheckList1;
	}
	public List<SetupCheck> getSetupCheckList2() {
		return setupCheckList2;
	}
	public void setSetupCheckList2(List<SetupCheck> setupCheckList2) {
		this.setupCheckList2 = setupCheckList2;
	}
	public List<SetupCheck> getSetupCheckList3() {
		return setupCheckList3;
	}
	public void setSetupCheckList3(List<SetupCheck> setupCheckList3) {
		this.setupCheckList3 = setupCheckList3;
	}
	public List<SetupCheck> getSetupCheckList4() {
		return setupCheckList4;
	}
	public void setSetupCheckList4(List<SetupCheck> setupCheckList4) {
		this.setupCheckList4 = setupCheckList4;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getNopass() {
		return nopass;
	}
	public void setNopass(String nopass) {
		this.nopass = nopass;
	}
	public File getShortFile() {
		return shortFile;
	}
	public void setShortFile(File shortFile) {
		this.shortFile = shortFile;
	}
	public String getShortFileFileName() {
		return shortFileFileName;
	}
	public void setShortFileFileName(String shortFileFileName) {
		this.shortFileFileName = shortFileFileName;
	}
	public String getShortFileContentType() {
		return shortFileContentType;
	}
	public void setShortFileContentType(String shortFileContentType) {
		this.shortFileContentType = shortFileContentType;
	}
	public String getNooperate() {
		return nooperate;
	}
	public void setNooperate(String nooperate) {
		this.nooperate = nooperate;
	}
	public TrackRecord getTrackRecord() {
		return trackRecord;
	}
	public void setTrackRecord(TrackRecord trackRecord) {
		this.trackRecord = trackRecord;
	}
	public List<TrackRecord> getTrackRecordList() {
		return trackRecordList;
	}
	public void setTrackRecordList(List<TrackRecord> trackRecordList) {
		this.trackRecordList = trackRecordList;
	}
	
	
}
