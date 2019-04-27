package com.task.action.renshi;

import java.util.List;
import java.util.Map;


import com.opensymphony.xwork2.ActionContext;
import com.task.Server.renshi.InterviewLogServer;
import com.task.entity.renshi.Inter_Family;
import com.task.entity.renshi.InterviewLog;

public class InterviewLogAction {
	private InterviewLogServer interviewLogServer;// 面试单服务；
	private InterviewLog interviewLog;// 面试单对象；
	private List<InterviewLog> interviewLogList;
	private Inter_Family family;//家庭信息对象
	private List<Inter_Family> familiesList;//家庭信息集合
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private boolean bool;//
	private String pageStatus;// 页面状态

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	private String ccTag;
	public String test() {
		return "error";
	}

	//跳转到入职页面
	public String to_add_ruzhi(){
		if(interviewLog==null){
			bool = interviewLogServer.findadmin();
			return "hr_addUser";
		}else {
			if (interviewLog.getId()!=null) {
				InterviewLog interviewLog_1 = interviewLogServer.getInterviewLogById(interviewLog.getId());
				if (interviewLog_1 != null) {
					interviewLog = interviewLog_1;
					return "hr_addUser";
				}
				errorMessage = "不存在该面试单对象";
			}
		}
		return "error";
	}
	
	// 显示查询内容 待入职人员 人事
	@SuppressWarnings("unchecked")
	public String showList1() {
		if (interviewLog != null) {
			ActionContext.getContext().getSession().put("interviewLog1",
					interviewLog);
		} else {// 用来保持分页时带着查询条件
			interviewLog = (InterviewLog) ActionContext.getContext()
					.getSession().get("interviewLog1");
		}
		Object[] object = interviewLogServer.findInterviewsByCondition(
				interviewLog, Integer.parseInt(cpage), pageSize);// 条件查询所有用户
		if (object != null && object.length > 0) {
			interviewLogList = (List<InterviewLog>) object[0];
			if (interviewLogList != null && interviewLogList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("interviewLogAction_showList1.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}else {
			errorMessage = "抱歉!没有您查询的员工信息!";
		}
		return "hr_adddai_show";
	}

	// 显示查询内容  唐
	@SuppressWarnings("unchecked")
	public String showList() {
		if (interviewLog != null) {
			ActionContext.getContext().getSession().put("interviewLog",
					interviewLog);
		} else {// 用来保持分页时带着查询条件
			interviewLog = (InterviewLog) ActionContext.getContext()
					.getSession().get("interviewLog");
		}
		 Map<Integer, Object> map = interviewLogServer
		 .findInterviewLogsByCondition(interviewLog, Integer
		 .parseInt(cpage), pageSize, ccTag);
		 interviewLogList = (List<InterviewLog>) map.get(1);// 显示面试单列表
			if (interviewLogList != null && interviewLogList.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("interviewLogAction_showList.action?ccTag"+ccTag);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		return "interview_show";
	}
	
	// 跳转到添加页面
	public String toadd() {
		return "interview_add";
	}

	// 添加方法
	public String add() {
		boolean b = interviewLogServer.addInterviewLog(interviewLog ,familiesList);
		if (b) {
			successMessage = "addsuccess";
			return "interviewQuizzes_1";
		} else {
			errorMessage = "添加失败！";
			return "error";
		}
	}

	// 跳转到修改页面的方法
	public String toupdate() {
		InterviewLog interviewLog2 = interviewLogServer
				.getInterviewLogById(interviewLog.getId());
		if (interviewLog2 != null) {
			interviewLog = interviewLog2;
			familiesList = interviewLogServer.getinterFamilyById(interviewLog.getId());
			if (familiesList!=null&&familiesList.size()>0) {
				errorMessage = null;
				return "interview_update";
			}else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
			return "int_add_succ";
		} else {
			errorMessage = "修改失败，不存在该面试单";
		}
		return "int_add_succ";
	}

	// 修改(评价)方法
	public String update() {
		boolean b = interviewLogServer.updateInterviewLog(interviewLog);
		if (b) {
			successMessage = "updatesuccess";
			interviewLog = null;
			return "int_add_succ";
		} else {
			errorMessage = "修改失败";
			return toupdate();
		}
	}

	public String select(){
		InterviewLog interviewLog2 = interviewLogServer
		.getInterviewLogById(interviewLog.getId());
		if (interviewLog2!= null) {
			interviewLog = interviewLog2;
			return "interview_select";
		}
		return "error";
	}
	
	// 删除方法
	public String delete() {
		boolean b = interviewLogServer.deleteInterviewLog(interviewLog.getId());
		if (b) {
			successMessage = "deletesuccess";
		} else {
			errorMessage = "删除失败";
		}
		interviewLog = null;
		return "int_add_succ";
	}

	// get set 方法
	public InterviewLogServer getInterviewLogServer() {
		return interviewLogServer;
	}

	public void setInterviewLogServer(InterviewLogServer interviewLogServer) {
		this.interviewLogServer = interviewLogServer;
	}

	public InterviewLog getInterviewLog() {
		return interviewLog;
	}

	public void setInterviewLog(InterviewLog interviewLog) {
		this.interviewLog = interviewLog;
	}

	public List<InterviewLog> getInterviewLogList() {
		return interviewLogList;
	}

	public void setInterviewLogList(List<InterviewLog> interviewLogList) {
		this.interviewLogList = interviewLogList;
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

	public List<Inter_Family> getFamiliesList() {
		return familiesList;
	}

	public void setFamiliesList(List<Inter_Family> familiesList) {
		this.familiesList = familiesList;
	}

	public Inter_Family getFamily() {
		return family;
	}

	public void setFamily(Inter_Family family) {
		this.family = family;
	}

	public String getCcTag() {
		return ccTag;
	}

	public void setCcTag(String ccTag) {
		this.ccTag = ccTag;
	}

	public boolean isBool() {
		return bool;
	}
	
	public void setBool(boolean bool) {
		this.bool = bool;
	}

}
