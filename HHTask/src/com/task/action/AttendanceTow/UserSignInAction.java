package com.task.action.AttendanceTow;

import java.util.List;

import com.task.Server.UserSignInServer;
import com.task.entity.UserSignIn;
import com.task.util.MKUtil;

public class UserSignInAction {
	private UserSignIn userSignIn;
	private UserSignInServer userSignInServer;
	private List<UserSignIn> attendanceTowList;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private String tag;
	private String pageStatus;// 页面状态
	// 分页
	private String cpage = "1";// 页数从一开始
	private String total;
	private String url;// 路径
	private int pageSize = 15;// 每页显示条数

	public String showListAttendance() {
		return "attendanceTow_show";
	}

	public String showListAttendanceCollect() {
		return "attendanceTowCollect_show";// loginType=8c160649-4333-44db-bfa9-6fdf8992eccf
	}

	public void addSingIn() {
		if (userSignIn != null && userSignIn.getUserId() != null
				&& userSignIn.getCode() != null) {
			errorMessage = userSignInServer.add(userSignIn);
			if ("true".equals(errorMessage)) {
				MKUtil.writeJSON(true, "签到成功！", null);
			} else {
				MKUtil.writeJSON(false, errorMessage, null);
			}
		} else {
			MKUtil.writeJSON(false, "信息有误。签到失败！", null);
		}
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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

	public UserSignIn getUserSignIn() {
		return userSignIn;
	}

	public void setUserSignIn(UserSignIn userSignIn) {
		this.userSignIn = userSignIn;
	}

	public UserSignInServer getUserSignInServer() {
		return userSignInServer;
	}

	public void setUserSignInServer(UserSignInServer userSignInServer) {
		this.userSignInServer = userSignInServer;
	}

	public List<UserSignIn> getAttendanceTowList() {
		return attendanceTowList;
	}

	public void setAttendanceTowList(List<UserSignIn> attendanceTowList) {
		this.attendanceTowList = attendanceTowList;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

}
