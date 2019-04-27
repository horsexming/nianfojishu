package com.task.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.*;
import com.task.entity.*;
import com.task.entity.seal.SealLog;
import com.task.util.MKUtil;
import com.task.util.Util;

/**
 * WorkLogAction层
 * 
 * @author 刘培
 * 
 */
public class WorkLogAction extends ActionSupport {

	private WorkLogServer workLogServer;// 日志Server层
	private WorkLogClassServer workLogClassServer;// 日志类别Server层
	private WorkLog workLog;// 日志
	private WorkLogClass workLogClass;// 日志类别
	private List<WorkLog> workLogList;// 集合
	private List<Users>  logStatusDeList;//个人工作记录待办情况
	private List<Users>  logStatusBiList;//个人工作记录办理中情况
	private List<WorkLog> LogStatusCao;//个人超时未完成情况;
	private List<WorkLog> wqrList;//个人未确认工作记录情况;
	
	private List<WorkLog> zpList;//个人所已指派的工作记录;
	private List<WorkLog> dzpList;//个人所待指派的工作记录;
	private List<WorkLog> qrList;//个人所指派的工作纪录任务人已确认记录;
	
	
	private List<Users>  logStatusDeList2;//部门工作记录待办情况
	private List<Users>  logStatusBiList2;//部门工作记录办理中情况
	private List<String> deptList;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private Integer id;// id
	private String pageStatus;// 状态

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	//状态
	

	// 添加日志
	public String addWorkLog() {
		boolean bool=false;
		WorkLogClass oldWorkLogClass = workLogClassServer
				.findWlcByClassName(workLogClass.getName());
		if (oldWorkLogClass == null) {
			workLogClassServer.addWorkLogClass(workLogClass);
			workLog.setWorkLogClass(workLogClass);
		} else {
			workLog.setWorkLogClass(oldWorkLogClass);
		}
		if("zhipai".equals(pageStatus)){
			if(workLog.getZptime()!=null&&workLog.getZptime().length()>0&&Util.compareTime(Util.getDateTime(), "yyyy-MM-dd HH:mm:ss", workLog.getZptime(), "yyyy-MM-dd HH:mm:ss")){
				errorMessage="规定完成日期不能比当前日期早!";
				return ERROR;
			}
			bool=workLogServer.addWorkLog1(workLog);
			if(bool){
				return "addWorkLog1";
			}
		}else{
			 bool = workLogServer.addWorkLog(workLog);
		}
		
		if (bool) {
			workLog = null;
			pageStatus = "single";
			findWorkLogByCondition();
			return "addWorkLogSuccess";
		}
		return ERROR;
	}

	// 查询个人日志 条件查询(分页)
	public String findWorkLogByCondition() {
		if (workLog != null) {
			ActionContext.getContext().getSession().put("workLog", workLog);
		} else {
			workLog = (WorkLog) ActionContext.getContext().getSession().get(
					"workLog");
		}
		if (workLog != null && workLog.getWorkLogClass() != null) {
			if (workLog.getWorkLogClass().getName() != null
					&& workLog.getWorkLogClass().getName().length() > 0) {
		 		workLog.setWorkLogClass(workLogClassServer
								.findWlcByClassName(workLog.getWorkLogClass()
										.getName()));
			} else if (workLog.getWorkLogClass() != null
					&& workLog.getWorkLogClass().getId() != null) {
			} else {
				workLog.setWorkLogClass(null);
			}
			
		}
		
		if("single".equals(pageStatus)){
			logStatusDeList=workLogServer.findLogStatusDeUser();//个人工作记录待办情况
			logStatusBiList=workLogServer.findLogStatusDe();//个人工作记录办理中情况
			LogStatusCao=workLogServer.findLogStatusCao();//个人超时未完成情况;
			wqrList=workLogServer.findwqrStatus();//个人未确认工作记录情况;
		}
		else if("dept".equals(pageStatus)){
			logStatusDeList2=workLogServer.findlogStatusBiUser();//部门工作记录待办情况
			logStatusBiList2=workLogServer.findlogStatusBi();//部门工作记录办理中情况
		}
		Object[] object = workLogServer.findWorkLogByCondition(workLog, 
				Integer
				.parseInt(cpage),  pageSize,pageStatus);
		if (object != null && object.length > 0) {
			workLogList = (List<WorkLog>) object[0];
			if (workLogList != null && workLogList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
			this.setUrl("WorkLogAction!findWorkLogByCondition.action?pageStatus="+pageStatus);
				errorMessage = null;
			} else {
				if(wqrList==null||wqrList.size()==0){
					
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
				
			}
		}	
		deptList=workLogServer.findDeptList();//所有部门查看
		return "findWorkLogByCondition";
	}
  
	// 处理事件 (办理中、已完成)
	public String updateWorkLog() {
		if(workLog!=null){
			boolean bool = workLogServer.updateWorkLog(workLog);// 修改
			if (bool) {
				workLog = null;
				if("zhipai".equals(pageStatus)){
					successMessage="成功!";
					return "findWorkLogById";
				}else if("queren".equals(pageStatus)){
					errorMessage="成功!";
					MKUtil.writeJSON(errorMessage);
					return ERROR;
				}
				findWorkLogByCondition();
				return "addWorkLogSuccess";
			} else {
				errorMessage = "处理事件 " + workLog.getTitle() + "失败,请检查后重试!";
			}
		} else {
			errorMessage = "不存在该日志,请检查后重试!";
		}
		return ERROR;
	}

	// 删除日志事件
	public String delWorkLog() {
		workLog = workLogServer.findWorkLogById(id);
		if (workLog != null) {
			boolean bool = workLogServer.delWorkLog(workLog);
			if (bool) {
				successMessage = "删除事件 " + workLog.getTitle() + "成功!";
				if("zhipai".equals(pageStatus)){
					return "addWorkLog1";
				}
				workLog = null;
				findWorkLogByCondition();
				return "addWorkLogSuccess";
			} else {
				errorMessage = "删除事件 " + workLog.getTitle() + " 失败!请检查后重试!";
			}
		} else {
			
		}
		return ERROR;
	}
	//通过ID查询日志;
	public String findWorkLogById(){
		if(id!=null){
		 workLog=workLogServer.findWorkLogById(id);
		 if(workLog!=null){
			 if("queren".equals(pageStatus)){
				 return "findWorkLogById1";
			 }
			 return "findWorkLogById";
		 }
		}
		errorMessage = "不存在该日志!请检查后重试!";
		return ERROR;
	}
	//查询个人所指派的日至任务;
	public String showzplist(){
		pageStatus="zhipai";
		if (workLog != null) {
			ActionContext.getContext().getSession().put("workLog", workLog);
		} else {
			workLog = (WorkLog) ActionContext.getContext().getSession().get(
					"workLog");
		}
		if (workLog != null && workLog.getWorkLogClass() != null) {
			if (workLog.getWorkLogClass().getName() != null
					&& workLog.getWorkLogClass().getName().length() > 0) {
		 		workLog.setWorkLogClass(workLogClassServer
								.findWlcByClassName(workLog.getWorkLogClass()
										.getName()));
			} else if (workLog.getWorkLogClass() != null
					&& workLog.getWorkLogClass().getId() != null) {
			} else {
				workLog.setWorkLogClass(null);
			}
			
		}
		dzpList=workLogServer.finddzpStatus();
		zpList=workLogServer.findzpStatus();
		Object[] object = workLogServer.findWorkLogByCondition(workLog, 
				Integer
				.parseInt(cpage),  pageSize,pageStatus);
		if (object != null && object.length > 0) {
			workLogList = (List<WorkLog>) object[0];
			if (workLogList != null && workLogList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
			this.setUrl("WorkLogAction!showzplist.action?pageStatus="+pageStatus);
				errorMessage = null;
			} else {
				if((dzpList==null||dzpList.size()==0)&&(zpList.size()==0||zpList==null)){
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
				
			}
		}	
		
		return "showzplist";
	}
	//校验规定任务完成日期;
	public void jyzptime(){
		if(workLog.getZptime()!=null&&workLog.getZptime().length()>0&&Util.compareTime(Util.getDateTime(), "yyyy-MM-dd HH:mm:ss", workLog.getZptime(), "yyyy-MM-dd HH:mm:ss")){
			errorMessage="规定完成日期不能比当前日期早!";
			MKUtil.writeJSON(errorMessage);
			return;
		}
		WorkLog	oldworkLog	=workLogServer.findWorkLogById(workLog.getId());
		if(oldworkLog.getAddDateTime()!=null&&oldworkLog.getAddDateTime().length()>0&&workLog.getZptime()!=null&&workLog.getZptime().length()>0){
			if(Util.compareTime(oldworkLog.getAddDateTime(), "yyyy-MM-dd HH:mm:ss", workLog.getZptime(), "yyyy-MM-dd HH:mm:ss")){
				errorMessage="规定完成日期不能比开始日期早!";
				MKUtil.writeJSON(errorMessage);
				return;
			}
		}
		
	}
	
	public void ajax_findLogStatusDeUser(){
		try {
			logStatusDeList=workLogServer.findLogStatusDeUser0();
			MKUtil.writeJSON(logStatusDeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 构造方法
	public WorkLogServer getWorkLogServer() {
		return workLogServer;
	}

	public void setWorkLogServer(WorkLogServer workLogServer) {
		this.workLogServer = workLogServer;
	}

	public WorkLog getWorkLog() {
		return workLog;
	}

	public void setWorkLog(WorkLog workLog) {
		this.workLog = workLog;
	}

	public List<WorkLog> getWorkLogList() {
		return workLogList;
	}

	public void setWorkLogList(List<WorkLog> workLogList) {
		this.workLogList = workLogList;
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


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public WorkLogClassServer getWorkLogClassServer() {
		return workLogClassServer;
	}

	public void setWorkLogClassServer(WorkLogClassServer workLogClassServer) {
		this.workLogClassServer = workLogClassServer;
	}

	public WorkLogClass getWorkLogClass() {
		return workLogClass;
	}

	public void setWorkLogClass(WorkLogClass workLogClass) {
		this.workLogClass = workLogClass;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}


	public List<Users> getLogStatusDeList() {
		return logStatusDeList;
	}

	public void setLogStatusDeList(List<Users> logStatusDeList) {
		this.logStatusDeList = logStatusDeList;
	}

	public List<Users> getLogStatusBiList() {
		return logStatusBiList;
	}

	public void setLogStatusBiList(List<Users> logStatusBiList) {
		this.logStatusBiList = logStatusBiList;
	}

	public List<Users> getLogStatusDeList2() {
		return logStatusDeList2;
	}

	public void setLogStatusDeList2(List<Users> logStatusDeList2) {
		this.logStatusDeList2 = logStatusDeList2;
	}

	public List<Users> getLogStatusBiList2() {
		return logStatusBiList2;
	}

	public void setLogStatusBiList2(List<Users> logStatusBiList2) {
		this.logStatusBiList2 = logStatusBiList2;
	}

	public List<String> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<String> deptList) {
		this.deptList = deptList;
	}

	public List<WorkLog> getLogStatusCao() {
		return LogStatusCao;
	}

	public void setLogStatusCao(List<WorkLog> logStatusCao) {
		LogStatusCao = logStatusCao;
	}

	public List<WorkLog> getZpList() {
		return zpList;
	}

	public void setZpList(List<WorkLog> zpList) {
		this.zpList = zpList;
	}

	public List<WorkLog> getDzpList() {
		return dzpList;
	}

	public void setDzpList(List<WorkLog> dzpList) {
		this.dzpList = dzpList;
	}

	public List<WorkLog> getQrList() {
		return qrList;
	}

	public void setQrList(List<WorkLog> qrList) {
		this.qrList = qrList;
	}

	public List<WorkLog> getWqrList() {
		return wqrList;
	}

	public void setWqrList(List<WorkLog> wqrList) {
		this.wqrList = wqrList;
	}
	
	
}
