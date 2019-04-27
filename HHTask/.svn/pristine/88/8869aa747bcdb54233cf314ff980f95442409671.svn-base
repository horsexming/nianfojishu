package com.task.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.CalendarServer;
import com.task.Server.renshi.InDoorScreenServer;
import com.task.entity.Calendar;
import com.task.entity.Users;
import com.task.util.MKUtil;
import com.task.util.Util;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 日历Action层
 * 
 * @author 刘培
 * 
 */
@SuppressWarnings("serial")
public class CalendarAction extends ActionSupport {

	private CalendarServer calendarServer;// 日历Server层
	private Calendar calendar;// 日历对象
	private List<Calendar> calendarList;// 日历集合
	private List<Calendar> canle;// 日历集合
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private String pageStatus;// 页面状态

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String start;
	private String end;
	private String isRepeat;// 删除事件状态
	private String usersid;// 所属人多选
	private List<Object> taskWeeklyList;//任务周报集合


	private List<File> fileUpload;
	private List<String> fileUploadContentType;
	private List<String> fileUploadFileName;
	private String AttachmentPath;
	private List<Users> usersList;
	private Users users;

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public List<Users> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<Users> usersList) {
		this.usersList = usersList;
	}



	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	// 查询所有日历数据
	public String findCalendar() {
		calendarList = calendarServer.findCalendar(start, end);
		MKUtil.writeJSON(calendarList);
		return ERROR;
	}
	//
	public String findCalendargo() {
		calendarList = calendarServer.findCalendargo(start, end);
		MKUtil.writeJSON(calendarList);
		return ERROR;
	}
	
	
	//ajax时间
	@SuppressWarnings("unchecked")
	public String time(){
		users= calendarServer.tiemeing(id);
		MKUtil.writeJSON(users);
		return ERROR;
	}
	
	
	
	// 查询所有日历数据
	public String mingxi() {
		canle = calendarServer.taskCalendar(start);		
		MKUtil.writeJSON(canle);
		return ERROR;
	}
	//查询自己的任务
	public String Calendarajax() {
		canle = calendarServer.Calendarajax(userName);		
		MKUtil.writeJSON(canle);
		return ERROR;
	}

	public List<Calendar> getCanle() {
		return canle;
	}

	public void setCanle(List<Calendar> canle) {
		this.canle = canle;
	}

	public String findallCalendar() {
		if (calendar != null) {
			ActionContext.getContext().getSession().put("calendar",
					calendar);
		} else {
			calendar = new Calendar();
		}
		Object[] object= calendarServer.findCalendarByUsers(calendar,Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			calendarList = (List<Calendar>) object[0];
			if (object != null && object.length > 0) {
				calendarList = (List) object[0];
				if (calendarList != null && calendarList.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("CalendarAction!findCalendarbyPerson.action");
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "calendar_all";
	}



	// 查询所有個人日历数据
	public String findCalendarbyPerson() {
		if (calendar != null) {
			ActionContext.getContext().getSession().put("calendar",
					calendar);
		} else {
			calendar =(Calendar) ActionContext.getContext().getSession().get("calendar");
		}
		Object[] object= calendarServer.findCalendarByUsers(calendar,Integer.parseInt(cpage), pageSize);

		if (object != null && object.length > 0) {
			calendarList = (List<Calendar>) object[0];
			if (object != null && object.length > 0) {
				calendarList = (List) object[0];
				if (calendarList != null && calendarList.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("CalendarAction!findCalendarbyPerson.action");
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}

		return "calendar_person";
	}

	// 添加日历
	public String addCalendar() throws IOException {

		String Path;
		String filename = "";
		AttachmentPath = "";
		List<File> files = getFileUpload();
		if (files != null && files.size() > 0) {
			for (int i = 0; i < 1; i++) {
				Path = "/upload/file/Calendar/";
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
		calendar.setAttachmentPath(AttachmentPath);
		String message = calendarServer.addCalendars(calendar,usersid);
		if (message != null && "true".equals(message)) {
			successMessage = "添加成功";
		} else {
			errorMessage = "添加失败";
			return ERROR;
		}
		return "newIndex";
//		return "addCalendar";
	}

	// 删除日历事件
	public String delCalendar() {
		calendar = calendarServer.findCalendarById(id);
		if (calendar != null) {
			boolean bool = calendarServer.delCalendar(calendar, isRepeat);
			if (bool) {
				errorMessage = bool + "";
				return "newIndex";
			} else {
				errorMessage = "删除失败!请稍候重试!";
			}
		} else {
			errorMessage = "数据异常，请刷新后重试!";
		}
		MKUtil.writeJSON(errorMessage);
		return ERROR;
	}

	public String submitCalendarState(){
		errorMessage=calendarServer.submitCalendarState(id);
		return ERROR;
	}

	public String updateMsgAssign(){
		errorMessage=calendarServer.updateMsgAssign(id);
		return "";
	}

	/**
	 * 人员任务周报
	 * @return
	 */
	public String taskWeekly() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if(calendar!=null) {
			session.put("taskWeekly", calendar);
		}else {
			calendar = (Calendar) session.get("taskWeekly");
		}
		Map<String, Object> map = calendarServer.getTaskWeekly(calendar,Integer.parseInt(cpage),pageSize,pageStatus);
		if(map!=null && map.size()>0) {
			taskWeeklyList =(List<Object>) map.get("list");
			Integer count = (Integer) map.get("count");
			Integer pageCount=(count + pageSize - 1) / pageSize;
			setTotal(pageCount+"");
		}
		calendar = (Calendar) map.get("calendar");
		setUrl("CalendarAction!taskWeekly.action");
		return "calendar_taskWeekly";
	}

	//前往时长分析
	public String toTaskTimeAnalyze() {
		//calendar
		return "calendar_taskAnalysis";
	}

	//任务时长分析
	public void taskTimeAnalyze() {
		MKUtil.writeJSON(calendarServer.taskTimeAnalyze(calendar));
	}

	// 构造方法
	public CalendarServer getCalendarServer() {
		return calendarServer;
	}

	public void setCalendarServer(CalendarServer calendarServer) {
		this.calendarServer = calendarServer;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public List<Calendar> getCalendarList() {
		return calendarList;
	}

	public void setCalendarList(List<Calendar> calendarList) {
		this.calendarList = calendarList;
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

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getIsRepeat() {
		return isRepeat;
	}

	public void setIsRepeat(String isRepeat) {
		this.isRepeat = isRepeat;
	}

	public List<Object> getTaskWeeklyList() {
		return taskWeeklyList;
	}

	public void setTaskWeeklyList(List<Object> taskWeeklyList) {
		this.taskWeeklyList = taskWeeklyList;
	}

	public String getUsersid() {
		return usersid;
	}

	public void setUsersid(String usersid) {
		this.usersid = usersid;
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
}
