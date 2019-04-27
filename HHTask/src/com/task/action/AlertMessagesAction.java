package com.task.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.jfree.chart.title.Title;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.AlertMessagesServer;
import com.task.Server.dmltry.ImgdeskServer;
import com.task.ServerImpl.SmsServiceImpl;
import com.task.entity.AlertMessages;
import com.task.entity.dmltry.Imgdesk;
import com.task.entity.system.Logging;
import com.task.entity.system.UsersUpdateLogging;
import com.task.util.MKUtil;
import com.task.util.Upload;
import com.task.util.Util;

@SuppressWarnings("serial")
/**
 * 消息提醒Action层
 * 
 * @author 刘培
 * 
 */
public class AlertMessagesAction extends ActionSupport {

	private AlertMessagesServer alertMessagesServer;// Server层
	private AlertMessages alertMessages;// 对象
	private List<AlertMessages> alertMessagesList;// 集合
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private Integer[] ids;// id
	private boolean calendar;

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private String tag;
	private int pageSize = 15;

	private Logging logging;// 日志
	private UsersUpdateLogging updateLogging;// 修改日志
	private String date;// 日期
	private List<Logging> loggingList;
	private List<UsersUpdateLogging> updateLoggingList;
	private Imgdesk imgdesk;
	private ImgdeskServer imgdeskServer;
	
	public Imgdesk getImgdesk() {
		return imgdesk;
	}

	public void setImgdesk(Imgdesk imgdesk) {
		this.imgdesk = imgdesk;
	}
	public ImgdeskServer getImgdeskServer() {
		return imgdeskServer;
	}

	public void setImgdeskServer(ImgdeskServer imgdeskServer) {
		this.imgdeskServer = imgdeskServer;
	}

	// 查询未读消息个数
	public String findAMCountByUid() {
		int count = (Integer) alertMessagesServer.findAMByUserId("count", "no");
		MKUtil.writeJSON(count);
		return null;
	}

	// 查询个人的提醒消息(条件查询、分页)
	@SuppressWarnings("unchecked")
	public String findAlertMessages() {
		if (alertMessages != null) {
			ActionContext.getContext().getSession().put("alertMessages",
					alertMessages);
		} else {
			alertMessages = (AlertMessages) ActionContext.getContext()
					.getSession().get("alertMessages");
		}
		Object[] object = alertMessagesServer.findAlertMessagesByCondition(
				alertMessages, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			alertMessagesList = (List<AlertMessages>) object[0];
			if (alertMessagesList != null && alertMessagesList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("AlertMessagesAction!findAlertMessages.action");
				errorMessage = null;
			} else {
				errorMessage = "没有您的提醒消息!";
			}
		}
		//if (Util.chackMobileOrPc())
		//	return "mobile_alertMessages";
		return "findAlertMessages";
	}

	/**
	 * ajax
	 */
	// 查询个人的提醒消息(条件查询、分页)
	@SuppressWarnings("unchecked")
	public void findAlertMessagesajax() {
		if (alertMessages != null) {
			ActionContext.getContext().getSession().put("alertMessages",
					alertMessages);
		} else {
			alertMessages = (AlertMessages) ActionContext.getContext()
					.getSession().get("alertMessages");
		}
		Object[] object = alertMessagesServer.findAlertMessagesByCondition1(
				alertMessages, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			alertMessagesList = (List<AlertMessages>) object[0];
				MKUtil.writeJSON(alertMessagesList);
		}else {
			MKUtil.writeJSON("没有代办事项");
		}
		
		//if (Util.chackMobileOrPc())
		//	return "mobile_alertMessages";
	}

	
	
	/***
	 * 查询个人的提醒消息(条件查询、分页) android接口
	 * 
	 * @return
	 */
	public void findAlertMessages_android() {
		Object[] object = alertMessagesServer.findAMForAndroid(alertMessages,
				Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			alertMessagesList = (List<AlertMessages>) object[0];
			if (alertMessagesList != null && alertMessagesList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("AlertMessagesAction!findAlertMessages.action");

				MKUtil.writeJSON(true, "加载成功", new Object[] {
						alertMessagesList, pageCount, cpage });
			} else {
				MKUtil.writeJSON(false, "暂时没有您的个人提醒消息!", null);
			}
		} else {
			MKUtil.writeJSON(false, "加载您的个人提醒消息失败!", null);
		}
	}

	// 查询消息
	public String findAndupdateAlertMessages() {
		alertMessages = alertMessagesServer.findAlertMessagesById(id);
		if (alertMessages != null) {
			AlertMessages newalertMessages = new AlertMessages();
			newalertMessages = alertMessages;
			alertMessages = null;
			findAlertMessages();
			alertMessages = newalertMessages;
			return "findAlertMessages";
		} else {
			errorMessage = "不存在您查询的提醒消息!";
		}
		return ERROR;
	}

	// 查询功能url并更新消息状态
	public String findAlertMessagesForUrl() {
		alertMessages = alertMessagesServer.findAlertMessagesById(id);
		if (alertMessages != null) {
			if (alertMessages.getReadStatus().equals("no")) {
				alertMessages.setReadStatus("yes");// 将消息状态更新为已读
				alertMessagesServer.updateAlertMessages(alertMessages);
			}
			return "findFunctionUrl";
		} else {
			errorMessage = "不存在您查询的提醒消息!";
		}
		return ERROR;
	}

	// 修改消息状态
	public String updateMessagesStatus() {
		if (ids != null) {
			for (int i = 0; i < ids.length; i++) {
				alertMessages = alertMessagesServer
						.findAlertMessagesById(ids[i]);
				if (alertMessages != null) {
					if (alertMessages.getReadStatus().equals("no")) {
						alertMessages.setReadStatus("yes");// 将消息状态更新为已读
						alertMessagesServer.updateAlertMessages(alertMessages);
					}
				}
			}
		} else {
			alertMessagesServer.updateAlertMessagesByWeidu();
		}
		errorMessage = "设为已读成功!";
		url = "AlertMessagesAction!findAlertMessages.action?alertMessages.readStatus=no";
		return ERROR;
	}

	//添加照片
	public String tianjianzhaop() 	{
		System.out.println("---------------------------------------------------------------");
		 String bool=imgdeskServer.addimg(imgdesk);
		 if(bool.equals("true")){
			 System.out.println("成功");
			 MKUtil.writeJSON(true);
		 }else{
			 System.out.println("失败");
			 MKUtil.writeJSON(false);
		 }
		 return null;
	}
	
	
	// 修改消息状态设为已读

	public void updateMessagesStatus1() {
		if (id > 0) {
			alertMessages = alertMessagesServer.findAlertMessagesById(id);
			if(alertMessages.getFunctionUrl().toString().indexOf("CircuitRunAction_findAduitPage")!=-1){
				MKUtil.writeJSON(true);
				return;
			}
			if (alertMessages != null) {
				if (alertMessages.getReadStatus().equals("no")) {
					alertMessages.setReadStatus("yes");// 将消息状态更新为已读
					boolean bool = alertMessagesServer
							.updateAlertMessages(alertMessages);
					MKUtil.writeJSON(bool);
				}
			}
		} else {
			MKUtil.writeJSON(false);
		}

	}

	// 删除提醒消息
	public String delAlertMessages() {
		alertMessages = alertMessagesServer.findAlertMessagesById(id);
		if (alertMessages != null) {
			boolean bool = alertMessagesServer.delAlertMessages(alertMessages);
			if (bool) {
				successMessage = "删除消息成功";
				alertMessages = null;
				findAlertMessages();
				if (calendar) {
					return null;
				}
				return "findAlertMessages";
			}
		} else {
			errorMessage = "不存在此消息!";
		}
		return ERROR;
	}

	/***
	 * 批量删除
	 * 
	 * @return
	 */
	public String delAlertMessagess() {
		if (ids != null) {
			for (int i = 0; i < ids.length; i++) {
				alertMessages = alertMessagesServer
						.findAlertMessagesById(ids[i]);
				if (alertMessages != null) {
					boolean bool = alertMessagesServer
							.delAlertMessages(alertMessages);
					if (bool) {
						successMessage = "删除消息成功";
						alertMessages = null;

					}
				} else {
					errorMessage = "不存在此消息!";
				}

			}
		}
		findAlertMessages();
		return "findAlertMessages";
	}

	// 清空个人提醒消息
	public String delAllAlertMessages() {
		int count = alertMessagesServer.delAllAlertMessagesByUid();
		if (count <= 0) {
			errorMessage = "您没有提醒消息,无需清空消息!谢谢!";
			return ERROR;
		}
		return "alertMessagesAction";
	}

	/***
	 * 系统日志查询
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findLoggingByCondition() {
		pageSize = 30;
		if (logging != null) {
			ActionContext.getContext().getSession().put("logging", logging);
		} else {
			logging = (Logging) ActionContext.getContext().getSession().get(
					"logging");
		}
		if (date != null) {
			ActionContext.getContext().getSession().put("date", date);
		} else {
			date = (String) ActionContext.getContext().getSession().get("date");
		}

		String dates[] = null;
		if (date != null && date.length() > 0) {
			dates = date.split(",");
			dates[1] = dates[1].replace(" ", "");
		}
		Object[] object = alertMessagesServer.findLoggingByCondition(logging,
				dates, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			loggingList = (List<Logging>) object[0];
			if (loggingList != null && loggingList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this
						.setUrl("AlertMessagesAction!findLoggingByCondition.action");
				errorMessage = null;
			} else {
				errorMessage = "没有您的提醒消息!";
			}
		}
		return "logging_List";
	}

	/**
	 * 个人修改记录
	 * 
	 * @return
	 */
	public String findUserUpdateLoggin() {
		pageSize = 30;
		if (updateLogging != null) {
			ActionContext.getContext().getSession().put("UsersUpdateLogging",
					updateLogging);
		} else {
			updateLogging = (UsersUpdateLogging) ActionContext.getContext()
					.getSession().get("UsersUpdateLogging");
		}
		if (date != null) {
			ActionContext.getContext().getSession().put("date", date);
		} else {
			date = (String) ActionContext.getContext().getSession().get("date");
		}

		String dates[] = null;
		if (date != null && date.length() > 0) {
			dates = date.split(",");
			dates[1] = dates[1].replace(" ", "");
		}
		Object[] object = alertMessagesServer.findUserUpdateLogging(
				updateLogging, dates, Integer.parseInt(cpage), pageSize, tag);
		if (object != null && object.length > 0) {
			updateLoggingList = (List<UsersUpdateLogging>) object[0];
			if (updateLoggingList != null && updateLoggingList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this
						.setUrl("AlertMessagesAction!findUserUpdateLoggin.action?tag="
								+ tag);
				errorMessage = null;
			} else {
				errorMessage = "没有您的提醒消息!";
			}
		}
		return "userUpdateLogging_List";
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public String findSelectType() {
		String message = alertMessagesServer.serUpdateLoggingType();
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(message);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "error";
	}

	/***
	 * 根据id查询日志
	 * 
	 * @param id
	 * @return
	 */
	public void findLogging() {
		Logging logging = alertMessagesServer.findLogging(id);
		MKUtil.writeJSON(logging);
	}

	/***
	 * 日历查询个人消息
	 * 
	 * @param user
	 * @return
	 */
	public String findAlertMessages4calendar() {
		List<AlertMessages> list = (List<AlertMessages>) alertMessagesServer
				.findAMByUserId("list", "no");
		// List<AlertMessages> list2=(List<AlertMessages>)
		// alertMessagesServer.findAMByUserId("list", "yes");
		class Cal {
			public String title;
			public String start;
			public String url;
			public String id;
			public String content;
			public String sendUserId;// 发送用户id
			public String sendUserName;// 发送用户名称
			public String sendUserImg;// 发送用户头像
			public String addTime;
			public String color;
			public String functionUrl;
			public String functionId;
		}
		List<Cal> cals = new ArrayList<Cal>();
		for (AlertMessages alertMessages : list) {
			Cal cal = new Cal();
			cal.title = "提醒消息\n" + alertMessages.getTitle();
			cal.start = alertMessages.getAddTime();
			cal.url = "javascript:void(0)";
			cal.id = "" + alertMessages.getId();
			cal.content = "" + alertMessages.getContent();
			cal.sendUserId = "" + alertMessages.getSendUserId();
			cal.sendUserName = "" + alertMessages.getSendUserName();
			cal.sendUserImg = "" + alertMessages.getSendUserImg();
			cal.addTime = "" + alertMessages.getAddTime();
			cal.functionUrl = "" + alertMessages.getFunctionUrl();
			cal.functionId = "" + alertMessages.getFunctionId();

			if (alertMessages.getReadStatus().equals("no")) {
				// cal.color="#CC6699";
				cal.color = "#257e4a";
			}

			cals.add(cal);
		}
		MKUtil.writeJSON(cals);
		return null;
	}

	public AlertMessagesServer getAlertMessagesServer() {
		return alertMessagesServer;
	}

	public void setAlertMessagesServer(AlertMessagesServer alertMessagesServer) {
		this.alertMessagesServer = alertMessagesServer;
	}

	public AlertMessages getAlertMessages() {
		return alertMessages;
	}

	public void setAlertMessages(AlertMessages alertMessages) {
		this.alertMessages = alertMessages;
	}

	public List<AlertMessages> getAlertMessagesList() {
		return alertMessagesList;
	}

	public void setAlertMessagesList(List<AlertMessages> alertMessagesList) {
		this.alertMessagesList = alertMessagesList;
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

	public Logging getLogging() {
		return logging;
	}

	public void setLogging(Logging logging) {
		this.logging = logging;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Logging> getLoggingList() {
		return loggingList;
	}

	public void setLoggingList(List<Logging> loggingList) {
		this.loggingList = loggingList;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public Integer[] getIds() {
		return ids;
	}

	public UsersUpdateLogging getUpdateLogging() {
		return updateLogging;
	}

	public void setUpdateLogging(UsersUpdateLogging updateLogging) {
		this.updateLogging = updateLogging;
	}

	public List<UsersUpdateLogging> getUpdateLoggingList() {
		return updateLoggingList;
	}

	public void setUpdateLoggingList(List<UsersUpdateLogging> updateLoggingList) {
		this.updateLoggingList = updateLoggingList;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isCalendar() {
		return calendar;
	}

	public void setCalendar(boolean calendar) {
		this.calendar = calendar;
	}

}
