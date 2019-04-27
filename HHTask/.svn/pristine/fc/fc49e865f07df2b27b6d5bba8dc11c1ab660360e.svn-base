package com.task.action.renshi;

import java.util.List;

import com.task.Server.renshi.InterviewQuizzesServer;
import com.task.entity.renshi.InterviewLog;
import com.task.entity.renshi.InterviewQuizzes;

public class InterviewQuizzesAction{
	private InterviewQuizzesServer interviewQuizzesServer;// 测试单服务；
	private InterviewQuizzes interviewQuizzes;// 测试单对象；
	private InterviewLog interviewLog;//面试登记单对象
	private List<InterviewQuizzes> interviewQuizzeslist;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private String pageStatus;// 页面状态

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	public String test() {
		return "error";
	}
	//添加方法
	public String add(){
		boolean b = interviewQuizzesServer.addInterviewQuizzes(interviewQuizzeslist, interviewLog);
		if (b) { 
			successMessage = "恭喜，添加成功！！！";
			return "interviewLog_add";
		} else{
			errorMessage = "提交失败！";
			return "interviewQuizzes_1";
		}
	}
	
	//根据登记表id来查询单个测试单
	public String selectId(){
		if (interviewLog != null) {
			
//			if ("待评价".equals(interviewLog.getInter_status())) {
				interviewQuizzeslist = interviewQuizzesServer.getinterviewQuizzesById(interviewLog.getId());
				if (interviewQuizzeslist!=null&&interviewQuizzeslist.size()>0) {
					errorMessage = null;
					return "interviewQuizzes_select";
				}else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
//			}else {
//				errorMessage = "此人还未经过过测试，请点击添加测试";
//			}
		}else {
			errorMessage = "不存在该面试单,请检查后重试!";
		}
		return "interviewLog_show_select";
	}
	public InterviewLog getInterviewLog() {
		return interviewLog;
	}
	public void setInterviewLog(InterviewLog interviewLog) {
		this.interviewLog = interviewLog;
	}
	public InterviewQuizzesServer getInterviewQuizzesServer() {
		return interviewQuizzesServer;
	}
	public void setInterviewQuizzesServer(
			InterviewQuizzesServer interviewQuizzesServer) {
		this.interviewQuizzesServer = interviewQuizzesServer;
	}
	public InterviewQuizzes getInterviewQuizzes() {
		return interviewQuizzes;
	}
	public void setInterviewQuizzes(InterviewQuizzes interviewQuizzes) {
		this.interviewQuizzes = interviewQuizzes;
	}
	public List<InterviewQuizzes> getInterviewQuizzeslist() {
		return interviewQuizzeslist;
	}
	public void setInterviewQuizzeslist(List<InterviewQuizzes> interviewQuizzeslist) {
		this.interviewQuizzeslist = interviewQuizzeslist;
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
}
