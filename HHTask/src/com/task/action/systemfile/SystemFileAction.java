package com.task.action.systemfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.FileCopyUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.sys.CircuitRunServer;
import com.task.Server.systemfile.SystemFileServer;
import com.task.entity.system.CircuitRun;
import com.task.entity.system.ExecutionNode;
import com.task.entity.systemfile.FileleixingOrdengji;
import com.task.entity.systemfile.SystemFile;
import com.task.util.MKUtil;
import com.task.util.Util;

public class SystemFileAction extends ActionSupport {
	private SystemFileServer systemFileServer;
	private SystemFile systemFile;
	private List<SystemFile> systemFileList;
	private List<SystemFile> unSystemFileList;//未添加文件的列表or 版本号记录
	private CircuitRunServer circuitRunServer;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	// 文件上传
	private File sys;
	private String sysFileName;
	private String sysContentType;
	
	//记录文件
	private File loggingFile;
	private String loggingFileFileName;
	private String loggingFileContentType;
	// 分页
	private String cpage = "1";
	private int pageSize = 15;
	private String total;
	private String url;
	private String tag;
	private String tags;
	private String level;
	private String pageStatus;
	private FileleixingOrdengji fileType;
	private List<FileleixingOrdengji> fileTypeList;
	private List<CircuitRun> circuitRunList;
	private int approvalId[];// Id数组
	private int ids[];// id数组
	private Integer id;
	private int pzId[];//批准人ID
	
	private File[] attachment;
	private String[] attachmentContentType;
	private String[] attachmentFileName;
	private String oldFiles;
	private String oldFileNames;
	private String newBanben;
	private String uidsAndLevels;
	private ExecutionNode executionNode;
	private String userName;
	private String applyCategory;//申请类别
	private List<String> deptNameList;//部门名称列表
	private String remark;
	
	public String delete() {
		systemFile = systemFileServer.findSystemFileById(systemFile.getId());
		if(systemFile==null){
			errorMessage = "数据不存在，请刷新后重试";
		}
		if (systemFileServer.delete(systemFile)) {
			errorMessage = "删除成功";
		} else {
			errorMessage = "删除失败";
		}
		url = "systemFileAction_findAllByshenpi.action?tag=" + tag + "&pageStatus=" + pageStatus;
		return "error";
	}

	public String toAdd() { 
		if (systemFile!=null&&systemFile.getId() != null) {
			systemFile = systemFileServer
					.findSystemFileById(systemFile.getId());
			ActionContext.getContext().put("systemFile", systemFile);
		}
		return "systemFile_add";
	}

	public String toupdateByshenpi() {
		
		if (systemFile!=null&&systemFile.getId() != null) {
			systemFile = systemFileServer
					.findSystemFileById(systemFile.getId());
			if(systemFile.getFileType()!=null && "合同类".equals(systemFile.getFileType())){
				tags = "hetong";
			}
			ActionContext.getContext().put("systemFile", systemFile);
		}
		return "systemFile_addshenpi";
	}
	
	public String toShengBan(){
		systemFile = systemFileServer.findSystemFileById(systemFile.getId());
		return "systemFile_shengban";
	}
	
	public String shengBan(){
		
		boolean b = false;
		String msg = "true";
		String fileName = null;
		String fileType = null;

		
		
		if (sys != null) {
			if (systemFile.getUpdateDate() == null
					|| systemFile.getUpdateDate().equals("")) {
				systemFile.setUpdateDate(Util.getDateTime());
			}
			
			//处理记录单
			if(loggingFile!=null){
				String[] names = loggingFileFileName.split("\\.");
				if (names != null && names.length >= 2) {
					fileType = names[names.length - 1];
				}
				String loggingFileName = "sysFileLogging" + Util.DateToString(Util.StringToDate(systemFile
								.getUpdateDate(), "yyyy-MM-dd HH:mm:ss"),
								"yyyyMMddHHmmss") + "." + fileType;
				msg = uploadlic(sys, "sysFile", loggingFileName, systemFile.getLoggingFile());
				
				if (msg != null && msg.equals("true")) {
					systemFile.setLoggingFile(loggingFileName);
				}
			}
			
			String[] names = sysFileName.split("\\.");
			if (names != null && names.length >= 2) {
				fileType = names[names.length - 1];
			}
			
			fileName = "sysFile"
					+ Util.DateToString(Util.StringToDate(systemFile
							.getUpdateDate(), "yyyy-MM-dd HH:mm:ss"),
							"yyyyMMddHHmmss") + "." + fileType;
			msg = uploadlic(sys, "sysFile", fileName, systemFile.getFileUrl());
			
			if (msg != null && msg.equals("true")) {
				systemFile.setFileUrl(fileName);
			}
			b = systemFileServer.addforshenpi(systemFile, uidsAndLevels);
			if (b) {
				errorMessage = "升版成功";
			} else {
				errorMessage = "升版失败";
			}
		}
		return "error";
	}
	
	public String toUpdateFileType(){
		fileType = systemFileServer.findFileleixingOrdengjiById(id);
		return "fileType_update";
	}
	
	//更新文件类型
	public String updateFileNameorType(){
		errorMessage = systemFileServer.updateFileNameorType(fileType);
		setUrl("systemFileAction_findAllFileNameorType.action?tag="+tag);
		return "error";
	}

	public String toDetail() {
		if (systemFile.getId() != null) {
			systemFile = systemFileServer
					.findSystemFileById(systemFile.getId());
		}
		return "systemfileShow";
	}

	public String update() {
		boolean b = false;
		SystemFile s = systemFileServer.findSystemFileById(systemFile.getId());
		String msg = "true";
		String fileName = null;
		if (systemFile.getUploadDate() == null
				|| systemFile.getUploadDate().equals("")) {
			systemFile.setUploadDate(Util.getDateTime());
		}
		if (sys != null) {
			
			String fileType = null;
			String[] names = sysFileName.split("\\.");
			if (names != null && names.length >= 2) {
				fileType = names[names.length - 1];
			}
			fileName = "sysFile"
					+ Util.DateToString(Util.StringToDate(systemFile
							.getUploadDate(), "yyyy-MM-dd HH:mm:ss"),
							"yyyyMMddHHmmss") + "." + fileType;
			msg = uploadlic(sys, "sysFile", fileName, systemFile.getFileUrl());
			if (msg != null && msg.equals("true")) {
				systemFile.setFileUrl(fileName);
			}
			if (systemFileServer.findSystemFileByNo(systemFile) == null) {
				systemFile.setPerson(Util.getLoginUser().getName());
				b = systemFileServer.update(systemFile);
			} else {
				errorMessage = "该编号文件已经导入";
				return "error";
			}
			if (b) {
				return "systemFile_toList";
			} else {
				errorMessage = "修改失败";
				return "error";
			}
		} else {
			systemFile.setFileUrl(s.getFileUrl());
			systemFile.setPerson(s.getPerson());
			if (systemFileServer.findSystemFileByNo(systemFile) == null) {
				b = systemFileServer.update(systemFile);
			} else {
				errorMessage = "该编号文件已经导入";
				return "error";
			}
			errorMessage = "文件导入成功";
			return "error";
		}

	}
	
	//根据文件编号，获得文件文件受控信息
	public void getSystemFileByFileNo(){
		systemFile = systemFileServer.findSystemFileByNo(systemFile);
		MKUtil.writeJSON(systemFile);
	}
	
	public String updateForShenpi(){
		boolean b = false;
		String msg = "true";
		String fileName = null;
		String fileType = null;
		String type = systemFile.getFileType();
		if(type!=null && "合同类".equals(type)){
			StringBuffer fileUrlBuffer = new StringBuffer();
			StringBuffer otherNameBuffer = new StringBuffer();
			
			if(oldFiles!=null && oldFileNames!=null){
				//处理旧文件空格问题
				String[] oldFilesplit = oldFiles.split(",");
				String[] oldFileNamesplit = oldFileNames.split(",");
				for (int i=0;i<oldFilesplit.length;i++) {
					if(i==0){
						fileUrlBuffer.append(oldFilesplit[i].trim());
						otherNameBuffer.append(oldFileNamesplit[i].trim());
					}else{
						fileUrlBuffer.append(","+oldFilesplit[i].trim());
						otherNameBuffer.append(","+oldFileNamesplit[i].trim());
					}
				}
			}else {
				fileUrlBuffer.append("");
				otherNameBuffer.append("");
			}
			
			if(attachment!=null && attachment.length>0){
				for(int i=0;i<attachment.length;i++){
					
					//处理文件
					String[] names = attachmentFileName[i].split("\\.");
					if (names != null && names.length >= 2) {
						fileType = names[names.length - 1];
					}
					//Date date = Util.StringToDate(systemFile.getUploadDate(), "yyyy-MM-dd HH:mm:ss SSS");
					fileName = "sysFile" + Util.DateToString(new Date(), "yyyyMMddHHmmssSSS") + "." + fileType;
					msg = uploadlic(attachment[i], "sysFile", fileName, null);
					if (msg == null || !"true".equals(msg)) {
						errorMessage = msg;
						return "error";
					}
					
					//处理文件名称
					if(i==0 && "".equals(fileUrlBuffer.toString())){
						fileUrlBuffer.append(fileName);
					}else{
						fileUrlBuffer.append(","+fileName);
					}
					if(i==0 && "".equals(otherNameBuffer.toString())){
						otherNameBuffer.append(names[0]);
					}else{
						otherNameBuffer.append(","+names[0]);
					}
				}
			}
			if (msg != null && msg.equals("true")) {
				systemFile.setFileUrl(fileUrlBuffer.toString());
				systemFile.setOtherName(otherNameBuffer.toString());
				if (systemFileServer.findSystemFileByNo(systemFile) == null) {
					b = systemFileServer.updateforshenpi(systemFile, uidsAndLevels);
					
				} else {
					errorMessage = "该编号文件已经导入";
					return "error";
				}
				if (b == true)
					errorMessage = "申请成功";
				return "error";
			} else {
				errorMessage = "申请失败";
				return "error";
			}
		}else{
			if (sys != null) {
				
				String[] names = sysFileName.split("\\.");
				if (names != null && names.length >= 2) {
					fileType = names[names.length - 1];
				}
				if (systemFile.getUpdateDate() == null
						|| systemFile.getUpdateDate().equals("")) {
					systemFile.setUpdateDate(Util.getDateTime());
				}
				fileName = "sysFile"
						+ Util.DateToString(Util.StringToDate(systemFile
								.getUpdateDate(), "yyyy-MM-dd HH:mm:ss"),
								"yyyyMMddHHmmss") + "." + fileType;
				msg = uploadlic(sys, "sysFile", fileName, systemFile.getFileUrl());
				
				if (msg != null && msg.equals("true")) {
					systemFile.setFileUrl(fileName);
				}
				if (systemFileServer.findSystemFileByNo(systemFile) == null) {
					b = systemFileServer.updateforshenpi(systemFile, uidsAndLevels);
				} else {
					errorMessage = "该编号文件已经导入";
					return "error";
				}
				if (b) {
					errorMessage = "修改成功";
				} else {
					errorMessage = "修改失败";
				}
				return "error";
			} else {
				
				SystemFile s = systemFileServer.findSystemFileById(systemFile.getId());
				systemFile.setFileUrl(s.getFileUrl());
				systemFile.setPerson(s.getPerson());
				systemFile.setUpdateDate(Util.getDateTime());
				
				if (systemFileServer.findSystemFileByNo(systemFile) == null) {
					b = systemFileServer.updateforshenpi(systemFile,uidsAndLevels);
				} else {
					errorMessage = "该编号文件已经导入";
					return "error";
				}
				if (b) {
					errorMessage = "修改成功";
				} else {
					errorMessage = "修改失败";
				}
				return "error";
			}
		}
		
	}

	public String add() {
		boolean b = false;
		String msg = "true";
		String fileName = null;
		if (systemFile.getUploadDate() == null
				|| systemFile.getUploadDate().equals("")) {
			systemFile.setUploadDate(Util.getDateTime());
		}
		if (sys != null) {
			String fileType = null;
			String[] names = sysFileName.split("\\.");
			if (names != null && names.length >= 2) {
				fileType = names[names.length - 1];
			}
			fileName = "sysFile"
					+ Util.DateToString(Util.StringToDate(systemFile
							.getUploadDate(), "yyyy-MM-dd HH:mm:ss"),
							"yyyyMMddHHmmss") + "." + fileType;
			msg = uploadlic(sys, "sysFile", fileName, systemFile.getFileUrl());
		}
		if (msg != null && msg.equals("true")) {
			systemFile.setFileUrl(fileName);
			if (systemFileServer.findSystemFileByNo(systemFile) == null) {
				b = systemFileServer.add(systemFile);
			} else {
				errorMessage = "该编号文件已经导入";
				return "error";
			}
			if (b == true)
				successMessage = msg;
			return "systemFile_toList";
		} else {
			errorMessage = "导入错误";
			return "error";
		}
	}

	public String addforShenpi() {
		if(Util.getLoginUser()==null){
			errorMessage = "请先登录！";
			return "error";
		}
		if(applyCategory!=null && "作废".equals(applyCategory)){
			errorMessage = systemFileServer.zuoFeiSystemFile(systemFile, tag, uidsAndLevels);
			return "error";
		}else{
			boolean b = false;
			String msg = "true";
			String fileName = null;
			String fileType = null;
			if (systemFile.getUploadDate() == null
					|| systemFile.getUploadDate().equals("")) {
				systemFile.setUploadDate(Util.getDateTime());
			}
			String type = systemFile.getFileType();
			if(type!=null && ("合同类".equals(type))){
				StringBuffer fileUrlBuffer = new StringBuffer();
				StringBuffer otherNameBuffer = new StringBuffer();
				
				if(attachment!=null && attachment.length>0){
					for(int i=0;i<attachment.length;i++){
						
						//处理文件
						String[] names = attachmentFileName[i].split("\\.");
						if (names != null && names.length >= 2) {
							fileType = names[names.length - 1];
						}
						//Date date = Util.StringToDate(systemFile.getUploadDate(), "yyyy-MM-dd HH:mm:ss SSS");
						fileName = "sysFile" + Util.DateToString(new Date(), "yyyyMMddHHmmssSSS") + "." + fileType;
						msg = uploadlic(attachment[i], "sysFile", fileName, null);
						if (msg == null || !"true".equals(msg)) {
							errorMessage = msg;
							return "error";
						}
						if(i==0){
							otherNameBuffer.append(names[0].trim());
						}else{
							otherNameBuffer.append(","+names[0].trim());
						}
						//处理文件名称
						if(i==0){
							fileUrlBuffer.append(fileName.trim());
						}else{
							fileUrlBuffer.append(","+fileName.trim());
						}
					}
				}
				if (msg != null && msg.equals("true")) {
					systemFile.setFileUrl(fileUrlBuffer.toString());
					systemFile.setOtherName(otherNameBuffer.toString());
					if (systemFileServer.findSystemFileByNo(systemFile) == null) {
						b = systemFileServer.addforshenpi(systemFile, uidsAndLevels);
						
					} else {
						errorMessage = "该编号文件已经导入";
						return "error";
					}
					if (b == true)
						errorMessage = "申请成功";
					return "error";
				} else {
					errorMessage = "申请失败";
					return "error";
				}
			}else{
				if (sys != null) {
					String[] names = sysFileName.split("\\.");
					if (names != null && names.length >= 2) {
						fileType = names[names.length - 1];
					}
					fileName = "sysFile"
							+ Util.DateToString(Util.StringToDate(systemFile
									.getUploadDate(), "yyyy-MM-dd HH:mm:ss"),
									"yyyyMMddHHmmss") + "." + fileType;
					msg = uploadlic(sys, "sysFile", fileName, systemFile.getFileUrl());
				}
				
				if (msg != null && msg.equals("true")) {
					systemFile.setFileUrl(fileName);
					b = systemFileServer.addforshenpi(systemFile, uidsAndLevels);
					if (b == true)
						errorMessage = "申请成功";
					return "error";
				} else {
					errorMessage = "申请失败";
					return "error";
				}
			}
		}
		
		
		
	}
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
			try {
				is = new FileInputStream(file);
				File file2 = new File(path + "/" + fileName);
				if (file2.exists()) {
					file2.delete();// 将原证书删掉
				}
				// 上传文件到服务器
				String fileRealPath = path + "/" + fileName;
				File uploadFile = new File(fileRealPath);
				try {
					FileCopyUtils.copy(file, uploadFile);
				} catch (Exception e) {
					return "上传文件失败!";
				}
				successMessage = "上传成功！";
			} catch (FileNotFoundException e) {
				return "找不到文件！";
			} catch (IOException e) {
				return "文件输入出错！";
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return "true";
		}
		return "文件为空";
	}

	public String findAll() {
		if (systemFile != null) {
			ActionContext.getContext().getSession().put("systemFile",
					systemFile);
		} else {
			systemFile = (SystemFile) ActionContext.getContext().getSession()
					.get("systemFile");
		}
		Map<Integer, Object> map = systemFileServer.Query(systemFile, Integer
				.parseInt(cpage), pageSize, pageStatus);
		systemFileList = (List<SystemFile>) map.get(1);
		if (systemFileList != null & systemFileList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			
			this.deptNameList = (List<String>) map.get(3);
			this.setUrl("systemFileAction_findAll.action?tag=" + tag
					+ "&pageStatus=" + pageStatus);
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		if ("nolook".equals(tag)) {
			return "noSystemFileList";
		} else {
			return "systemFile_List";// systemFileList.jsp
		}
	}

	public String findAllByshenpi() {
		if (systemFile != null) {
			ActionContext.getContext().getSession().put("systemFile",
					systemFile);
		} else {
			systemFile = (SystemFile) ActionContext.getContext().getSession()
					.get("systemFile");
		}
		Map<Integer, Object> map = systemFileServer.QueryByshenpi(systemFile,
				Integer.parseInt(cpage), pageSize,tags);
		systemFileList = (List<SystemFile>) map.get(1);
		if (systemFileList != null & systemFileList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("systemFileAction_findAllByshenpi.action?tags="+tags);
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		if(tag==null && !"".equals(tag)){
			tag = "all";
		}
		return "sysFile_ListByshenpi";

	}
	
	public String findAllnoshenpi() {
		if (systemFile != null) {
			ActionContext.getContext().getSession().put("findAllnoshenpi",
					systemFile);
		} else {
			systemFile = (SystemFile) ActionContext.getContext().getSession()
					.get("findAllnoshenpi");
		}
		Map<Integer, Object> map = systemFileServer.QueryByshenpi(systemFile,
				0, 0,"noshenpi");
		systemFileList = (List<SystemFile>) map.get(1);
		if (systemFileList != null & systemFileList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("systemFileAction_findAllnoshenpi.action?tags="+tags);
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		if(tag==null && !"".equals(tag)){
			tag = "all";
		}
		return "sysFile_ListBynoshenpi";

	}
	
	public String auditAllShenPi() {
		errorMessage = systemFileServer.auditAllShenPi(ids, tag);
		return "error";
	}
	
	//技术部申请文件编号
	public void applyForJsb(){
		
	}
	
	//技术部查找编号管理
	public String findCodeManager(){
		if (systemFile != null) {
			ActionContext.getContext().getSession().put("systemFile",
					systemFile);
		} else {
			systemFile = (SystemFile) ActionContext.getContext().getSession()
					.get("systemFile");
		}
		Map<Integer, Object> map = systemFileServer.findCodeManager(systemFile,
				Integer.parseInt(cpage), pageSize,tag,pageStatus);
		systemFileList = (List<SystemFile>) map.get(1);
		unSystemFileList = (List<SystemFile>) map.get(3);
		if (systemFileList != null & systemFileList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("systemFileAction_findCodeManager.action?tag="+tag+"&pageStatus="+pageStatus);
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		
		return "systemFile_search";
	}
	
	@SuppressWarnings("unchecked")
	public String getSystemFileDetail(){
		systemFile = systemFileServer.findSystemFileById(id);
		if(tags!=null&& tags.equals("get")){
			MKUtil.writeJSON(systemFile);
		}	
		setPageSize(10);
		userName = Util.getLoginUser().getName();
		//查询版本历史记录
		Map<Integer, Object> map = systemFileServer.findCodeManager(systemFile, Integer.parseInt(cpage), 10, "banben",pageStatus);
		systemFileList = (List<SystemFile>) map.get(1);
		if (systemFileList != null & systemFileList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("systemFileAction_getSystemFileDetail.action?tag="+tag+"&pageStatus="+pageStatus+"&id="+id);
			
			newBanben =systemFileServer.getNewBanBenByoldBanBen(systemFileList.get(0).getBanben());
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		//查询审批节点
		if(systemFile.getCanEpId()!=null){
			circuitRunList = circuitRunServer.findAllExNodeByEpId(systemFile.getCanEpId());
			executionNode = circuitRunServer.findAuditExeNode(systemFile.getCanEpId());// 待审批节点
		}else{
			circuitRunList = circuitRunServer.findAllExNodeByEpId(systemFile.getEpId());
			executionNode = circuitRunServer.findAuditExeNode(systemFile.getEpId());// 待审批节点
		}
		return "systemFile_detail";
	}

	public String findAllByUser() {
		if (systemFile != null) {
			ActionContext.getContext().getSession().put("systemFile",
					systemFile);
		} else {
			systemFile = (SystemFile) ActionContext.getContext().getSession()
					.get("systemFile");
		}
		Map<Integer, Object> map = systemFileServer.QueryByshenpiforporson(
				systemFile, Integer.parseInt(cpage), pageSize);
		systemFileList = (List<SystemFile>) map.get(1);
		if (systemFileList != null & systemFileList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("systemFileAction_findAllByUser.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		tag = "no";
		return "sysFile_ListByshenpi";

	}

	public String findAllByupload() {
		if (systemFile != null) {
			ActionContext.getContext().getSession().put("systemFile",
					systemFile);
		} else {
			systemFile = (SystemFile) ActionContext.getContext().getSession()
					.get("systemFile");
		}
		Map<Integer, Object> map = systemFileServer.QueryByupload(systemFile,
				Integer.parseInt(cpage), pageSize);
		systemFileList = (List<SystemFile>) map.get(1);
		if (systemFileList != null & systemFileList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("systemFileAction_findAllByupload.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "sysFile_ListByshenpi";

	}

	public String Querylishibanben() {
		systemFileList = systemFileServer.Querylishibanben(new ArrayList<SystemFile>(),systemFile);
		if (systemFileList != null) {
			return "sysFile_ListByshenpi";
		} else {
			errorMessage = "无历史记录";
			return "error";
		}
	}

	public String findAllBylevel() {
		if (systemFile != null) {
			ActionContext.getContext().getSession().put("systemFile",
					systemFile);
		} else {
			systemFile = (SystemFile) ActionContext.getContext().getSession()
					.get("systemFile");
		}
		Map<Integer, Object> map = systemFileServer.QueryByLevel(systemFile,
				Integer.parseInt(cpage), pageSize, level, pageStatus);
		systemFileList = (List<SystemFile>) map.get(1);
		if (systemFileList != null & systemFileList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("systemFileAction_findAllBylevel.action?tag=" + tag
					+ "&level=" + level + "&pageStatus=" + pageStatus);
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		if ("nolook".equals(tag)) {
			return "noSystemFileList";// noSystemFileList.jsp
		} else {
			return "systemFile_List";// systemFileList.jsp
		}
	}

	/**
	 * 添加文件类型名称
	 * 
	 * @return
	 */
	public String addFileNameorType() {
		errorMessage = systemFileServer.addFileNameorType(fileType);
		return "error";
	}

	public String findAllFileNameorType() {
		if (fileType != null) {
			ActionContext.getContext().getSession().put("fileType", fileType);
		} else {
			fileType = (FileleixingOrdengji) ActionContext.getContext()
					.getSession().get("fileType");
		}
		Map<Integer, Object> map = systemFileServer.QueryFileType(fileType,
				Integer.parseInt(cpage), pageSize, tag);
		fileTypeList = (List<FileleixingOrdengji>) map.get(1);
		if (fileTypeList != null & fileTypeList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("systemFileAction_findAllFileNameorType.action?tag="
					+ tag);
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "Filetype_List";
	}

	public String findall() {
		if ("dengji".equals(tag)) {
			tag = "文件等级";
		} else if ("leixing".equals(tag)) {
			tag = "文件类型";
		}else if("laiyuan".equals(tag)){
			tag = "文件来源";
		}else if("category".equals(tag)){
			tag = "文件类别";
		}
		List<FileleixingOrdengji> list = systemFileServer.QueryFileType(tag);
		MKUtil.writeJSON(list);
		return null;
	}

	public String deleteType() {
		if (systemFileServer.delete(fileType)) {
			errorMessage = "删除成功";
		} else {
			errorMessage = "删除失败";
		}
		if ("dengji".equals(tag)) {
			url = "systemFileAction_findAllFileNameorType.action?tag=dengji";
		} else if ("dengji".equals(tag)) {
			url = "systemFileAction_findAllFileNameorType.action?tag=leixing";
		}
		return "error";
	}
	
	//生成编号
	public void generatorFileNoAndSave(){
		 SystemFile fileCode;
		try {
			fileCode = systemFileServer.generatorFileNo(systemFile);
			MKUtil.writeJSON(true,null,fileCode);
		} catch (Exception e) {
			MKUtil.writeJSON(false,e.getMessage(),null);
		}
	}

	public String guidang() {
		errorMessage = systemFileServer.guidang(systemFile);
		if(tag!=null&&tag.equals("jsb")){
			setUrl("systemFileAction_getSystemFileDetail.action?tag="+tag+"&id="+systemFile.getId());
		}
		return "error";
	}
	
	public void saveOrCancalCode(){
		errorMessage = systemFileServer.saveOrCancalCode(tags, systemFile);
		MKUtil.writeJSON(errorMessage);
	}
	
	//前往提交文件并保存
	public void toSubmitUnionSave(){
		//保存后获得ID
		errorMessage = systemFileServer.saveOrCancalCode("saveLaterGetId", systemFile);
		try {
			id = Integer.parseInt(errorMessage);
			MKUtil.writeJSON(true, errorMessage, null);
		} catch (Exception e) {
			MKUtil.writeJSON(false, errorMessage, null);
		}
	}
	
	public void submitFile(){
		String msg = "true";
		String fileName = null;
		if (sys != null) {
			String fileType = null;
			String[] names = sysFileName.split("\\.");
			if (names != null && names.length >= 2) {
				fileType = names[names.length - 1];
			}
			if(systemFile.getUploadDate()==null){
				systemFile.setUploadDate(Util.getDateTime());
			}
			fileName = "sysFile"
					+ Util.DateToString(Util.StringToDate(systemFile
							.getUploadDate(), "yyyy-MM-dd HH:mm:ss"),
							"yyyyMMddHHmmss") + "." + fileType;
			msg = uploadlic(sys, "sysFile", fileName, systemFile.getFileUrl());
		}
		if (msg != null && msg.equals("true")) {
			systemFile.setFileUrl(fileName);
			systemFile.setOtherName(sysFileName);
			SystemFile systemFile2 = systemFileServer.submitOrUpGrade(tags, systemFile, pzId, ids);
			MKUtil.writeJSON(systemFile2);
		} else {
			MKUtil.writeJSON("导入文件出错");
		}
	}

	public String xiangxi() {
		systemFile = systemFileServer.findSystemFileById(id);
		
		if(systemFile!=null && systemFile.getSource()!=null){
			//查询版本历史记录
			Map<Integer, Object> map = systemFileServer.findCodeManager(systemFile, Integer.parseInt(cpage), 10, "banben",pageStatus);
			systemFileList = (List<SystemFile>) map.get(1);
			if (systemFileList != null & systemFileList.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("systemFileAction_xiangxi.action?tag="+tag+"&pageStatus="+pageStatus+"&id="+id);
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		
		return "systemFile_forLook";
	}
	
	public void getSystemFileByFileType(){
		List<SystemFile> list = systemFileServer.findSystemFile(systemFile, null);
		if(tag!=null && tag.equals("get")){
			List<SystemFile> returnList = new ArrayList<SystemFile>(); 
			for (SystemFile systemFile : list) {
				SystemFile sysFile = new SystemFile();
				sysFile.setId(systemFile.getId());
				sysFile.setFileName(systemFile.getFileName());
				sysFile.setFileNo(systemFile.getFileNo());
				returnList.add(sysFile);
			}
			MKUtil.writeJSON(returnList);
		}else{
			MKUtil.writeJSON(list);
		}
	}
	
	//判断员工是否在本部门中
	public void estimateIsDept(){
		
	}
	
	
	
	public String getListByOldBanben(){
		
		Object[] obj = systemFileServer.getListByOldBanben(id, tag);
		
		if(obj!=null && obj[0]!=null){
			systemFileList = (List<SystemFile>) obj[0];
			return "sysFile_ListByshenpi";
		}else{
			errorMessage = "无历史记录";
			return "error";
		}
	}

	
	public void getUsersByCondition(){
		
		
	}
	
	//反审功能
	public void applyReAudit() {
		try {
			errorMessage = systemFileServer.applyReaudit(id, remark);
		} catch (Exception e) {
			errorMessage = e.getMessage();
		}
		MKUtil.writeJSON(errorMessage);
	}
	public SystemFileServer getSystemFileServer() {
		return systemFileServer;
	}

	public void setSystemFileServer(SystemFileServer systemFileServer) {
		this.systemFileServer = systemFileServer;
	}

	public SystemFile getSystemFile() {
		return systemFile;
	}

	public void setSystemFile(SystemFile systemFile) {
		this.systemFile = systemFile;
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

	public File getSys() {
		return sys;
	}

	public void setSys(File sys) {
		this.sys = sys;
	}

	public String getSysFileName() {
		return sysFileName;
	}

	public void setSysFileName(String sysFileName) {
		this.sysFileName = sysFileName;
	}

	public String getSysContentType() {
		return sysContentType;
	}

	public void setSysContentType(String sysContentType) {
		this.sysContentType = sysContentType;
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

	public List<SystemFile> getSystemFileList() {
		return systemFileList;
	}

	public void setSystemFileList(List<SystemFile> systemFileList) {
		this.systemFileList = systemFileList;
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public FileleixingOrdengji getFileType() {
		return fileType;
	}

	public void setFileType(FileleixingOrdengji fileType) {
		this.fileType = fileType;
	}

	public List<FileleixingOrdengji> getFileTypeList() {
		return fileTypeList;
	}

	public void setFileTypeList(List<FileleixingOrdengji> fileTypeList) {
		this.fileTypeList = fileTypeList;
	}

	public int[] getApprovalId() {
		return approvalId;
	}

	public void setApprovalId(int[] approvalId) {
		this.approvalId = approvalId;
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<SystemFile> getUnSystemFileList() {
		return unSystemFileList;
	}

	public void setUnSystemFileList(List<SystemFile> unSystemFileList) {
		this.unSystemFileList = unSystemFileList;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
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

	public File[] getAttachment() {
		return attachment;
	}

	public void setAttachment(File[] attachment) {
		this.attachment = attachment;
	}

	public String getNewBanben() {
		return newBanben;
	}

	public void setNewBanben(String newBanben) {
		this.newBanben = newBanben;
	}

	public int[] getPzId() {
		return pzId;
	}

	public void setPzId(int[] pzId) {
		this.pzId = pzId;
	}

	public CircuitRunServer getCircuitRunServer() {
		return circuitRunServer;
	}

	public void setCircuitRunServer(CircuitRunServer circuitRunServer) {
		this.circuitRunServer = circuitRunServer;
	}

	public List<CircuitRun> getCircuitRunList() {
		return circuitRunList;
	}

	public void setCircuitRunList(List<CircuitRun> circuitRunList) {
		this.circuitRunList = circuitRunList;
	}

	public String getOldFiles() {
		return oldFiles;
	}

	public void setOldFiles(String oldFiles) {
		this.oldFiles = oldFiles;
	}

	public String getOldFileNames() {
		return oldFileNames;
	}

	public void setOldFileNames(String oldFileNames) {
		this.oldFileNames = oldFileNames;
	}

	public ExecutionNode getExecutionNode() {
		return executionNode;
	}

	public void setExecutionNode(ExecutionNode executionNode) {
		this.executionNode = executionNode;
	}

	public File getLoggingFile() {
		return loggingFile;
	}

	public void setLoggingFile(File loggingFile) {
		this.loggingFile = loggingFile;
	}

	

	public String getLoggingFileFileName() {
		return loggingFileFileName;
	}

	public void setLoggingFileFileName(String loggingFileFileName) {
		this.loggingFileFileName = loggingFileFileName;
	}

	public String getLoggingFileContentType() {
		return loggingFileContentType;
	}

	public void setLoggingFileContentType(String loggingFileContentType) {
		this.loggingFileContentType = loggingFileContentType;
	}

	public String getUidsAndLevels() {
		return uidsAndLevels;
	}

	public void setUidsAndLevels(String uidsAndLevels) {
		this.uidsAndLevels = uidsAndLevels;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getApplyCategory() {
		return applyCategory;
	}

	public void setApplyCategory(String applyCategory) {
		this.applyCategory = applyCategory;
	}

	public List<String> getDeptNameList() {
		return deptNameList;
	}

	public void setDeptNameList(List<String> deptNameList) {
		this.deptNameList = deptNameList;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}



}
