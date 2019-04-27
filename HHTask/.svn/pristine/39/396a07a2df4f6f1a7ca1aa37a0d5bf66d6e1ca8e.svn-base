package com.task.action.sop;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.sop.ProcessPeopleServer;
import com.task.entity.*;
import com.task.entity.sop.ProcessPeople;
import com.task.util.Util;

/**
 * 工序人员Action层
 * 
 * @author 刘培
 * 
 */
@SuppressWarnings("serial")
public class ProcessPeopleAction extends ActionSupport {

	private ProcessPeopleServer processPeopleServer;// Server层
	private ProcessPeople processPeople;// 对象
	private List<ProcessPeople> processPeopleList;// 集合
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private int processId;// id
	private String pageStatus;// 页面状态
	@SuppressWarnings("unchecked")
	private List list;
	private Float sum;// 提交量

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	/** 通过工序id查询人员 */
	@SuppressWarnings("unchecked")
	public String findPPByProcessId() {
		processPeopleList = processPeopleServer.findPPByProcessId(processId);
		return "processPeople";
	}

	/** 添加工序人员 */
	@SuppressWarnings("unchecked")
	public String addProcessPeople() {
		Users user = Util.getLoginUser();
		// 通过工号、添加人工号、工序id 查询人员是否已经存在
		ProcessPeople oldProcessPeople = processPeopleServer.findProPeople(
				processPeople.getCode(), user.getCode(), processPeople
						.getProcessId());
		if (oldProcessPeople == null) {
			processPeople.setAddUserid(user.getId());// 添加人id
			processPeople.setAddUserCode(user.getCode());// 添加人工号
			processPeople.setAddUserName(user.getName());// 添加人姓名

			boolean bool = processPeopleServer.addProcessPeople(processPeople);
			if (bool) {
				processPeopleList = processPeopleServer
						.findPPByProcessId(processPeople.getProcessId());
				processId = processPeople.getProcessId();
				return "addProcessPeople";
			} else {
				errorMessage = "添加失败";
			}
		} else {
			errorMessage = processPeople.getName() + "已经添加过,请勿重复添加!";
		}
		return ERROR;
	}

	/** 通过工序id删除人员 */
	public String delProcessPeople() {
		processPeople = processPeopleServer.findProPeople(id);
		if (processPeople != null) {
			if (processPeopleServer.delProcessPeople(processPeople)) {
				processId = processPeople.getProcessId();
				return "addProcessPeople";
			}
			errorMessage = "删除失败,请稍候重试!";
		} else
			errorMessage = "不存在您要删除的人员";
		return "processPeople";
	}

	/** 通过工序id更新人员 */
	public String updateProcessPeople() {
		processPeople = processPeopleServer.findProPeople(id);
		if (processPeople != null) {
			processPeople.setSum(sum);
			if (processPeopleServer.updateProcessPeople(processPeople)) {
				return "addProcessPeople";
			}
			errorMessage = "修改失败,请稍候重试!";
		} else
			errorMessage = "不存在您要修改的人员";
		return "processPeople";
	}

	/** 构造方法 */
	public ProcessPeopleServer getProcessPeopleServer() {
		return processPeopleServer;
	}

	public Float getSum() {
		return sum;
	}

	public void setSum(Float sum) {
		this.sum = sum;
	}

	public void setProcessPeopleServer(ProcessPeopleServer processPeopleServer) {
		this.processPeopleServer = processPeopleServer;
	}

	public ProcessPeople getProcessPeople() {
		return processPeople;
	}

	public void setProcessPeople(ProcessPeople processPeople) {
		this.processPeople = processPeople;
	}

	public List<ProcessPeople> getProcessPeopleList() {
		return processPeopleList;
	}

	public void setProcessPeopleList(List<ProcessPeople> processPeopleList) {
		this.processPeopleList = processPeopleList;
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

	public int getProcessId() {
		return processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

}
