package com.task.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.AlertMessagesServer;
import com.task.Server.TaskmanagerService;
import com.task.Server.UserServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.entity.Taskmanager;
import com.task.entity.Users;
import com.task.util.MKUtil;
import com.task.util.Util;

public class TaskmanagerAction extends ActionSupport {

	private TaskmanagerService taskmanagerService;
	private AlertMessagesServer alertMessagesServer;
	private UserServer userService;
	private Integer id;
	private Taskmanager taskmanager;
	private List<Taskmanager> taskmanagers;
	private List<Taskmanager> taskmanagers2;
	private List<Taskmanager> taskmanagers3;
	private List<Taskmanager> taskmanagers4;
	private List<File> fileUpload;
	private List<String> fileUploadContentType;
	private List<String> fileUploadFileName;
	private String AttachmentPath;
	private String level;
	private String[] Filenames;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String errorMessage;
	private boolean confirm = false;
	private String status;
	private int sum;
	private int dclcount;
	private int clzcount;
	private int dqrcount;
	private int fkcount;
	private int wccount;
	private File addfil;
	private int totalcount;

	public String add() throws IOException {
		String Path;
		String filename = "";
		AttachmentPath = "";
		List<File> files = getFileUpload();
		if (files != null && files.size() > 0) {
			for (int i = 0; i < files.size(); i++) {
				// String timePathString = String.valueOf(System
				// .currentTimeMillis());
				// Path = "/upload/file/Taskmanager/" + timePathString + "/";
				Path = "/upload/file/Taskmanager/";
				String realFilePath = ServletActionContext.getServletContext()
						.getRealPath(Path);

				// 如果不存在文件夹就创建
				File file = new File(realFilePath);
				if (!file.exists()) {
					file.mkdirs();
				}
				filename = Util.getDateTime("yyyyMMddHHmmss_") + i + "_"
						+ fileUploadFileName.get(i);
				FileOutputStream fos = new FileOutputStream(realFilePath + "\\"
						+ filename);
				AttachmentPath += filename;
				AttachmentPath += "/";
				// 建立上传文件的输入流
				FileInputStream fis = new FileInputStream(files.get(i));
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				fis.close();
			}
		}

		taskmanager.setEp_status("未审批");
		taskmanager.setTaskState("待处理");
		taskmanager.setAttachmentPath(AttachmentPath);
		taskmanagerService.addTaskmanager(taskmanager);
		MKUtil.writeJSON(true,"成功",null);
		return "success";
	}
	public String daoru(){
		taskmanagerService.file(addfil);
		return "error";
	}
	public String list() {
		if (taskmanager != null) {
			ActionContext.getContext().getSession().put("taskmanager",
					taskmanager);
		} else {
			taskmanager = (Taskmanager) ActionContext.getContext().getSession()
					.get("taskmanager");
		}
		// 查询处理中
		// taskmanagers2 = taskmanagerService.findTaskmanager(taskmanager,
		// "处理中");
		// 查询待确认
		// taskmanagers3 = taskmanagerService.findTaskmanager(taskmanager,
		// "待确认");
		// 查询二次反馈
		// taskmanagers4 = taskmanagerService.findTaskmanager(taskmanager,
		// "反馈");
		Object[] object = taskmanagerService.findTaskmanager(taskmanager,
				Integer.parseInt(cpage), pageSize, level, status);
		if (object != null && object.length > 0) {
			sum = (Integer) object[1];
			dclcount = (Integer) object[2];
			clzcount = (Integer) object[3];
			dqrcount = (Integer) object[4];
			fkcount = (Integer) object[5];
			wccount = (Integer) object[6];
			totalcount = (Integer) object[7];
			taskmanagers = (List<Taskmanager>) object[0];
			sum = (Integer) object[1];
			dclcount = (Integer) object[2];
			clzcount = (Integer) object[3];
			dqrcount = (Integer) object[4];
			fkcount = (Integer) object[5];
			wccount = (Integer) object[6];
			totalcount = (Integer) object[7];
			if (taskmanagers != null && taskmanagers.size() > 0) {
				int pageCount = (sum + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				if (level == null) {
					level = "";
				}
				this.setUrl("TaskmanagerAction_list.action?level=" + level
						+ "&status=" + status);
				errorMessage = null;
			} else {
				errorMessage = "抱歉!您查询的计划不存在!";
			}

		} else {
			errorMessage = "抱歉!没有您查询的信息!";
		}
		return "TaskmanagerList";
	}

	public String getdept() {
		List list = taskmanagerService.getdept();
		try {
			MKUtil.writeJSON(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String updatepage() {
		taskmanager = taskmanagerService.findTaskmanagerbyid(id);
		return "Taskmanager_update";
	}

	public String addtasksuggestion() {
		taskmanager.setTaskState("处理中");
		errorMessage = taskmanagerService.updateTaskmanager(taskmanager);
		url = "TaskmanagerAction_list.action?level=" + level + "&status="
				+ status;
		return ERROR;
	}

	// 添加处理意见 及领取人页面
	public String suggestion() {
		taskmanager = taskmanagerService.findTaskmanagerbyid(id);
		return "Taskmanager_suggestion";
	}

	// 申请人确认问题反馈页面
	public String confirmpage() {
		taskmanager = taskmanagerService.findTaskmanagerbyid(id);
		confirm = true;
		return "Taskmanager_suggestion";
	}

	// 查看附件页面
	public String attachment() {
		taskmanager = taskmanagerService.findTaskmanagerbyid(id);
		if (null != taskmanager.getAttachmentPath()) {
			Filenames = taskmanager.getAttachmentPath().split("/");
		} else {
			errorMessage = "无附件";
		}
		return "Taskmanager_attachment";
	}

	public String addpage() {
		return "Taskmanager_add";
	}

	public String update() {
		errorMessage = taskmanagerService.updateTaskmanager(taskmanager);
		taskmanager = taskmanagerService.findTaskmanagerbyid(taskmanager
				.getId());
		if ("待确认".equals(taskmanager.getTaskState())
				&& "修改成功！".equals(errorMessage)) {
			Users users = userService.findUserById(taskmanager
					.getApplyUsersId());
			String code = users.getCode();
			AlertMessagesServerImpl.addAlertMessages("问题与需求汇总(个人)", taskmanager
					.getDescription()
					+ "已完成待确认", "任务反馈通知", code);
		}
		return "success";
	}

	public String delete() {
		errorMessage = taskmanagerService.deleteTaskmanager(id);
		return "success";
	}
	
	//导出问题点
	public String exportExcelTask() {
		taskmanagerService.exportExcelTask(taskmanager, status, level);
		return "error";
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

	public TaskmanagerService getTaskmanagerService() {
		return taskmanagerService;
	}

	public void setTaskmanagerService(TaskmanagerService taskmanagerService) {
		this.taskmanagerService = taskmanagerService;
	}

	public Taskmanager getTaskmanager() {
		return taskmanager;
	}

	public void setTaskmanager(Taskmanager taskmanager) {
		this.taskmanager = taskmanager;
	}

	public List<Taskmanager> getTaskmanagers() {
		return taskmanagers;
	}

	public void setTaskmanagers(List<Taskmanager> taskmanagers) {
		this.taskmanagers = taskmanagers;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AlertMessagesServer getAlertMessagesServer() {
		return alertMessagesServer;
	}

	public void setAlertMessagesServer(AlertMessagesServer alertMessagesServer) {
		this.alertMessagesServer = alertMessagesServer;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	public UserServer getUserService() {
		return userService;
	}

	public void setUserService(UserServer userService) {
		this.userService = userService;
	}

	public List<File> getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(List<File> fileUpload) {
		this.fileUpload = fileUpload;
	}

	public List<String> getFileUploadContentType() {
		return fileUploadContentType;
	}

	public void setFileUploadContentType(List<String> fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}

	public List<String> getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(List<String> fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	public String getAttachmentPath() {
		return AttachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		AttachmentPath = attachmentPath;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public List<Taskmanager> getTaskmanagers2() {
		return taskmanagers2;
	}

	public void setTaskmanagers2(List<Taskmanager> taskmanagers2) {
		this.taskmanagers2 = taskmanagers2;
	}

	public List<Taskmanager> getTaskmanagers3() {
		return taskmanagers3;
	}

	public void setTaskmanagers3(List<Taskmanager> taskmanagers3) {
		this.taskmanagers3 = taskmanagers3;
	}

	public List<Taskmanager> getTaskmanagers4() {
		return taskmanagers4;
	}

	public void setTaskmanagers4(List<Taskmanager> taskmanagers4) {
		this.taskmanagers4 = taskmanagers4;
	}

	public String[] getFilenames() {
		return Filenames;
	}

	public void setFilenames(String[] filenames) {
		Filenames = filenames;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getDclcount() {
		return dclcount;
	}

	public void setDclcount(int dclcount) {
		this.dclcount = dclcount;
	}

	public int getClzcount() {
		return clzcount;
	}

	public void setClzcount(int clzcount) {
		this.clzcount = clzcount;
	}

	public int getDqrcount() {
		return dqrcount;
	}

	public void setDqrcount(int dqrcount) {
		this.dqrcount = dqrcount;
	}

	public int getFkcount() {
		return fkcount;
	}

	public void setFkcount(int fkcount) {
		this.fkcount = fkcount;
	}

	public int getWccount() {
		return wccount;
	}


	public File getAddfil() {
		return addfil;
	}

	public void setAddfil(File addfil) {
		this.addfil = addfil;
	}
	public void setWccount(int wccount) {
		this.wccount = wccount;
	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

}
