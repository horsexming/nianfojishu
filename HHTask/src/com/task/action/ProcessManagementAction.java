package com.task.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.ProcessManagementService;
import com.task.entity.Templateb;
import com.task.entity.Templatenode;
import com.task.entity.UsersGroup;
import com.task.entity.zgkh.AssessPersonnel;
import com.task.util.MKUtil;

public class ProcessManagementAction extends ActionSupport {
	private Templateb templateb;
	private Templatenode templatenode;
	private ProcessManagementService processManagementService;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private List<Templateb> assessPersonnelList;// 集合
	private List<UsersGroup> UsersGroupList;
	private List list;// 集合
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String groupName;
	private UsersGroup usersGroup;// 成员组类
	private AssessPersonnel assessPersonnel;// 组成员类
	private int id;

	// 模板添加
	public String add() {
		boolean bool = processManagementService.add(templateb);
		if (bool) {
			successMessage = "模板名称添加成功";
		}
		return "templateber";

	}

	// 流程定制添加
	public String findAllprocess() {
		list = processManagementService.selectGrouping(null);
		return "otherker";
	}

	public String addprocess() {

		processManagementService.add(templatenode);
		return "prorcess";

	}

	@SuppressWarnings("unchecked")
	public String findGroupName() {
		List<AssessPersonnel> list = processManagementService
				.findPersonByGroupId(id);
		List<AssessPersonnel> usersGroupLsit = new ArrayList<AssessPersonnel>();
		for (AssessPersonnel asp : list) {
			asp.setUsersGroup(null);
			asp.setTemplate(null);
			usersGroupLsit.add(asp);
		}
		MKUtil.writeJSON(usersGroupLsit);
		return null;
	}

	// 删除
	public String delSubmit() {
		templateb = processManagementService.findAssetById(id);
		processManagementService.delete(templateb);
		return "delsubmit";
	}

	// 模板查询
	@SuppressWarnings("unchecked")
	public String findas() {
		if (templateb != null) {
			ActionContext.getContext().getSession().put("Templateb", templateb);
		} else {
			templateb = (Templateb) ActionContext.getContext().getSession()
					.get("templateb");
		}
		Object[] object = processManagementService.findTemplatebByCondition(
				templateb, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			assessPersonnelList = (List<Templateb>) object[0];
			if (assessPersonnelList != null && assessPersonnelList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("ProcessManagementAction!findas.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "addtemplatebSuccess";
	}

	public Templateb getTemplateb() {
		return templateb;
	}

	public void setTemplateb(Templateb templateb) {
		this.templateb = templateb;
	}

	public ProcessManagementService getProcessManagementService() {
		return processManagementService;
	}

	public void setProcessManagementService(
			ProcessManagementService processManagementService) {
		this.processManagementService = processManagementService;
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

	public List<Templateb> getAssessPersonnelList() {
		return assessPersonnelList;
	}

	public void setAssessPersonnelList(List<Templateb> assessPersonnelList) {
		this.assessPersonnelList = assessPersonnelList;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public UsersGroup getUsersGroup() {
		return usersGroup;
	}

	public void setUsersGroup(UsersGroup usersGroup) {
		this.usersGroup = usersGroup;
	}

	public AssessPersonnel getAssessPersonnel() {
		return assessPersonnel;
	}

	public void setAssessPersonnel(AssessPersonnel assessPersonnel) {
		this.assessPersonnel = assessPersonnel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Templatenode getTemplatenode() {
		return templatenode;
	}

	public void setTemplatenode(Templatenode templatenode) {
		this.templatenode = templatenode;
	}

	public List<UsersGroup> getUsersGroupList() {
		return UsersGroupList;
	}

	public void setUsersGroupList(List<UsersGroup> usersGroupList) {
		UsersGroupList = usersGroupList;
	}

}
