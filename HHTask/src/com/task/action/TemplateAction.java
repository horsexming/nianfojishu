package com.task.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.TemplateDetailsServer;
import com.task.Server.TemplateServer;
import com.task.Server.UserServer;
import com.task.Server.zgkh.AssessPersonnelServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Template;
import com.task.entity.TemplateDetails;
import com.task.entity.Users;
import com.task.entity.zgkh.AssessPersonnel;

@SuppressWarnings("serial")
public class TemplateAction extends ActionSupport {

	private TemplateServer templateServer;
	private TemplateDetailsServer templateDetailsServer;
	private AssessPersonnelServer assessPersonnelServer;// Server层
	private UserServer userServer;//

	private Template template;
	private TemplateDetails templateDetails;

	private int id;
	private int pageLayer;
	private String pageOnLayer;
	private String errorMessage;
	private String successMessage;
	private String scoreMessage;// 分数信息
	private String status;// 模板状态
	private List<Template> templateList;// 模板集合
	private List<Users> usersList;// 部门用户集合
	private List<AssessPersonnel> assessPersonnelList;// 集合
	private String ulMessage;// 预览
	private int count;// 已绑定个数
	private int[] usersId;// 用户Id
	private String pageStatus;
	private AssessPersonnel ap;
	private Users pageUser;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String tag;
	private Integer[] detailSelect;// 选择补打数组,审批数组
	private List list;

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer[] getDetailSelect() {
		return detailSelect;
	}

	public void setDetailSelect(Integer[] detailSelect) {
		this.detailSelect = detailSelect;
	}

	// 添加模板
	@SuppressWarnings("unchecked")
	public String addTemplate() {
		if (templateServer.addTemplate(template)) {
			successMessage = "添加模板:" + template.getName() + "成功";
			ActionContext.getContext().getSession().put("successMessage",
					successMessage);
			return "addTemplateSuccess";
		}
		errorMessage = "添加模板:" + template.getName() + "失败";
		return ERROR;
	}

	// 通过id查询模板
	public String findTemplateById() {
		template = templateServer.findTemplateById(id);
		if (template != null) {
			// 主管级考核
			if ("主管级".equals(template.getAssObject())) {
				pageLayer = 1;
				pageOnLayer = "root";
				return "zgAddTemplateSuccess";
			}
			// 员工级考核
			if ("添加明细".equals(template.getStatus())
					|| "打回".equals(template.getStatus())) {
				pageLayer = 1;
				pageOnLayer = "root";
			} else if ("同意".equals(template.getStatus())
					|| "打分".equals(template.getStatus())) {
				return "ygPeople";
			} else if ("结束".equals(template.getStatus())) {
				int today = Integer.parseInt(new SimpleDateFormat("dd")
						.format(new Date()));
				if (today >= 1 && today <= 7) {
					return "ygPeople";
				} else {
					errorMessage = "该模板已结束!";
					return ERROR;
				}
			}
			return "findTemplateSuccess";
		} else {
			errorMessage = "不存在该模板";
		}
		return ERROR;
	}

	// 修改模板状态(提交审核)
	@SuppressWarnings("unchecked")
	public String updateTemplate() {
		if ("1".equals(status)) {
			status = "打分";
			// int today = Integer.parseInt(new SimpleDateFormat("dd")
			// .format(new Date()));
			// if (today >= 1 && today <= 10) {
			// errorMessage = "今天无法提交审核!审核时间为每月的11号到月底!谢谢!";
			// return ERROR;
			// }
		} else if ("2".equals(status)) {
			status = "同意";
		} else if ("3".equals(status)) {
			status = "打回";
		} else {
			errorMessage = "非法字符!请检查后重试!";
			return ERROR;
		}

		template = templateServer.findTemplateById(id);
		if (template != null) {
			Float sumScore = 100F;
			if (sumScore != null && sumScore == 100) {
				template.setStatus(status);
				if (templateServer.updateTemplate(template)) {
					successMessage = "模板" + template.getName() + "已经提交!";
					ActionContext.getContext().getSession().put(
							"successMessage", successMessage);
					if (!"审核".equals(status)) {
						return "updateTemplateByBack";// 通过或者打回的模板
					} else {
						// AlertMessagesServerImpl.addAlertMessages("模板审核（总经理审批）",
						// template.getDept() + "部门"
						// + template.getAsstMouth()
						// + "份部门的绩效考核表请您审核! ", "1");
						// 这里不在提醒审批，由添加人手动提醒

						return "addTemplateSuccess";// 提交审核的模板
					}
				}
				errorMessage = "模板" + template.getName() + "提交失败,请检查后重试!";
			} else {
				errorMessage = "该模板总分不足100分,请检查后重试!";
			}
		} else {
			errorMessage = "该模板不存在请检查后重试!";
		}

		return ERROR;
	}

	// 分页查询模板（审核或历史）
	@SuppressWarnings("unchecked")
	public String findAllTemplate() {
		Object[] object = templateServer.findAllTemplate(Integer
				.parseInt(cpage), pageSize, status);
		if (object != null && object.length > 0) {
			templateList = (List<Template>) object[0];
			if (templateList != null && templateList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("TemplateAction!findAllTemplate.action?status="
						+ status);
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		ActionContext.getContext().put("pageStatus", status);
		if ("userHistory".equals(status) || "history".equals(status)
				|| "groupTemplate".equals(status)) {
			return "findTemplateSuccess";
		} else if ("audit".equals(status) || "byBack".equals(status)) {
			return "findTemplateBYBackSuccess";
		} else if ("zhuguan".equals(status)) {// 主管级模版
			this.pageStatus = pageStatus;
			return "zgAddTemplateSuccess";
		} else {
			errorMessage = "非法字符,请检查后重试!";
			return "error";
		}
	}

	// 分页查询模板（审核或历史）
	@SuppressWarnings("unchecked")
	public String findAllTemplate1() {
		Object[] obj = templateServer.findAllTemplate1(Integer.parseInt(cpage),
				pageSize);
		if (obj != null && obj.length > 1) {
			list = (List) obj[1];
			int count = (Integer) obj[0];
			this.setUrl("TemplateAction!findAllTemplate1.action?status=audit");
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
		}
		return "findTemplateBYBackSuccess";
	}

	// 预览模版
	public String PreviewTemplate() throws IOException {
		template = templateServer.findTemplateById(id);
		if (template != null) {
			ulMessage = templateServer.PreviewTemplate(template);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(ulMessage);
			response.getWriter().close();
			return null;
		}
		errorMessage = "不存在该模板";
		return ERROR;
	}

	// 批量审批
	public String updateExamTemplate() {
		try {
			if (templateServer.updateExamTemplate(detailSelect, tag)) {
				return "updateExamTemplate";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		errorMessage = "审批失败!请检查数据有效性!";
		return ERROR;
	}

	// 删除模板
	@SuppressWarnings("unchecked")
	public String delTemplate() {
		template = templateServer.findTemplateById(id);
		if (template != null) {
			boolean bool = templateServer.delTemplate(template);
			if (bool) {
				successMessage = "删除模板" + template.getName() + "成功";
				ActionContext.getContext().getSession().put("successMessage",
						successMessage);
				return "delTemplateSuccess";
			} else {
				errorMessage = "删除模板" + template.getName() + "失败!请检查后重试!";
				return "error";
			}
		}
		errorMessage = "不存在该模板";
		return ERROR;
	}

	// ! 条件查询明细(分页)
	@SuppressWarnings("unchecked")
	public String findTDsByCondition() {
		if (template != null) {
			ActionContext.getContext().getSession().put("ConditionTemplate",
					template);
		} else {
			template = (Template) ActionContext.getContext().getSession().get(
					"ConditionTemplate");
		}
		if ("byBack".equals(status) && "审核".equals(template.getStatus())) {
			template.setStatus(null);
		}
		templateList = templateServer.findTDsByCondition(template, Integer
				.parseInt(cpage), pageSize, status);

		if (templateList != null && templateList.size() > 0) {
			int count = templateServer.getCountBtCondition(template, status);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("TemplateAction!findTDsByCondition.action?status="
					+ status);
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		if ("history".equals(status)) {
			return "findTemplateSuccess";
		} else if ("byBack".equals(status)) {
			return "findTemplateBYBackSuccess";
		}
		return ERROR;
	}

	// 绑定用户
	public String bingdingUsers() {
		template = templateServer.findTemplateById(id);
		if (template != null) {
			Set<AssessPersonnel> apSet = new HashSet<AssessPersonnel>();
			if (usersId != null) {
				for (int i = 0; i < usersId.length; i++) {
					AssessPersonnel assessPersonnel = assessPersonnelServer
							.findAPById(usersId[i]);
					if (assessPersonnel != null) {
						apSet.add(assessPersonnel);
					} else {
						errorMessage = "不存在该用户id(" + usersId[i];
						return ERROR;
					}
				}
			}
			template.setAssessPersonnel(apSet);
			if (templateServer.updateTemplate(template)) {
				successMessage = "绑定用户成功!";
				return "addTemplateSuccess";
			}

		} else {
			errorMessage = "不存在该模板";
		}
		return ERROR;
	}

	// 查询部门里所有用户(打分-选人)
	public String findUsersByDept() {
		int today = Integer.parseInt(new SimpleDateFormat("dd")
				.format(new Date()));
		// if (today >= 1 && today <= 7) {
		if (today == today) {
			assessPersonnelList = assessPersonnelServer
					.findAssessPersonnelForScore("员工级");
			if (assessPersonnelList == null || assessPersonnelList.size() <= 0) {
				errorMessage = "所有人员都已打分或您没有为成员绑定考核模版,请您检查!";
			}
			return "findScoreSuccess";
		} else {
			errorMessage = "今天不是评分时间,评分时间为每个月的1号到7号!谢谢!";
			return ERROR;
		}
	}

	// 根据用户id查询已绑定模板
	public String findTemplateListByUserId() {
		if (usersId != null && usersId.length > 0) {
			ap = assessPersonnelServer.findAPById(usersId[0]);
			templateList = templateServer.findTemplateListByUserId(ap);
			if (templateList == null || templateList.size() <= 0) {
				errorMessage = "该用户尚未绑定模板或者本月已经为该用户打过分,请检查后重试!";
			}
			return "findScoreSuccess";
		}
		return ERROR;
	}

	// 查询模板以及用户(打分-选择模板)
	public String findTemplateAndUser() {
		AssessPersonnel ap = assessPersonnelServer.findAPById(id);
		pageUser = userServer.findUserById(ap.getUserId());
		if (status != null && status.length() > 0 && "zhuguan".equals(status)) {
			template = assessPersonnelServer.findAPAndTemplateById(id);
			status = "scoring";
			return "zgkhScore";
		} else if (status != null && status.length() > 0
				&& "yuangong".equals(status)) {
			template = assessPersonnelServer.findAPAndTemplateById(id, null);// 通过Id和月份查询该人员以及模版
			status = "scoring";
			return "findScoreSuccess";
		}
		return ERROR;
	}

	// 发送邮件提醒
	public String sendMailAgin() {
		int count = templateServer.getCountBtCondition();
		if (count > 0) {
			Users user = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);
			AlertMessagesServerImpl.addAlertMessages("模板审核（总经理审批）", user
					.getDept()
					+ "部门有" + count + "份绩效考核模版请您审核! ", "1");
			status = "history";
			return "delTemplateSuccess";
		}
		errorMessage = "您没有需要审核的考核模版!无需发送短信提醒!谢谢!";
		return ERROR;
	}

	// 生成修改样式
	public String generateTemplate() throws IOException {
		template = templateServer.findTemplateById(id);
		String templateMessage = "<form action='TemplateAction!updateTem.action' method='post'><hr color='#BFEFFF'>"
				+ "<input name='id' value='"
				+ template.getId()
				+ "' type='hidden'>"
				+ "<table class='table'>"
				+ "<tr>"
				+ "<td colspan='2' align='center'>修改考核模板 ——"
				+ template.getName()
				+ "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td align='right'>模板名称:</td>"
				+ "<td><input type='text' name='template.name' value='"
				+ template.getName()
				+ "'></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td align='right'>考核月份:</td>"
				+ "<td><input type='text' name='template.asstMouth' class='Wdate' onClick=WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'}) value='"
				+ template.getAsstMouth()
				+ "'></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td align='right'>备注:</td>"
				+ "<td><textarea rows='5' cols='40' name='template.remarks'>"
				+ template.getRemarks()
				+ "</textarea></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td colspan='2' align='center'>"
				+ "<input type='submit' value='修改'style='width: 80px; height: 50px'>&nbsp;&nbsp;&nbsp;&nbsp;"
				+ "<input type='reset' value='重置'style='width: 80px; height: 50px'></td>"
				+ "</tr>" + "</table></form>";

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(templateMessage);
		response.getWriter().close();
		return null;
	}

	// 修改模板
	@SuppressWarnings("unchecked")
	public String updateTem() {
		Template oldTemplate = templateServer.findTemplateById(id);
		if (oldTemplate != null) {
			oldTemplate.setName(template.getName());
			oldTemplate.setRemarks(template.getRemarks());
			oldTemplate.setAsstMouth(template.getAsstMouth());
			if (templateServer.updateTemplate(oldTemplate)) {
				template = oldTemplate;
				successMessage = "修改模板:" + template.getName() + "成功!";
				ActionContext.getContext().getSession().put("successMessage",
						successMessage);
				return "addTemplateSuccess";
			} else {
				errorMessage = "修改模板失败,请检查后重试!";
			}
		} else {
			errorMessage = "不存在该模板,请检查后重试!";
		}
		return ERROR;
	}

	// 使用历史模板
	@SuppressWarnings("unchecked")
	public String useHistry() {
		template = templateServer.findTemplateById(id);
		if (template != null) {
			if (templateServer.useHirstory(template, status)) {
				successMessage = "使用模板:" + template.getName() + "成功!";
				ActionContext.getContext().getSession().put("successMessage",
						successMessage);
				return "addTemplateSuccess";
			} else {
				errorMessage = "使用该模板失败!请检查后重试!";
			}
		} else {
			errorMessage = "不存在该模板!请检查后重试!";
		}
		return ERROR;
	}

	public TemplateServer getTemplateServer() {
		return templateServer;
	}

	public void setTemplateServer(TemplateServer templateServer) {
		this.templateServer = templateServer;
	}

	public TemplateDetailsServer getTemplateDetailsServer() {
		return templateDetailsServer;
	}

	public void setTemplateDetailsServer(
			TemplateDetailsServer templateDetailsServer) {
		this.templateDetailsServer = templateDetailsServer;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public TemplateDetails getTemplateDetails() {
		return templateDetails;
	}

	public void setTemplateDetails(TemplateDetails templateDetails) {
		this.templateDetails = templateDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPageLayer() {
		return pageLayer;
	}

	public void setPageLayer(int pageLayer) {
		this.pageLayer = pageLayer;
	}

	public String getPageOnLayer() {
		return pageOnLayer;
	}

	public void setPageOnLayer(String pageOnLayer) {
		this.pageOnLayer = pageOnLayer;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getScoreMessage() {
		return scoreMessage;
	}

	public void setScoreMessage(String scoreMessage) {
		this.scoreMessage = scoreMessage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Template> getTemplateList() {
		return templateList;
	}

	public void setTemplateList(List<Template> templateList) {
		this.templateList = templateList;
	}

	public List<Users> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<Users> usersList) {
		this.usersList = usersList;
	}

	public String getUlMessage() {
		return ulMessage;
	}

	public void setUlMessage(String ulMessage) {
		this.ulMessage = ulMessage;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int[] getUsersId() {
		return usersId;
	}

	public void setUsersId(int[] usersId) {
		this.usersId = usersId;
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

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public AssessPersonnelServer getAssessPersonnelServer() {
		return assessPersonnelServer;
	}

	public void setAssessPersonnelServer(
			AssessPersonnelServer assessPersonnelServer) {
		this.assessPersonnelServer = assessPersonnelServer;
	}

	public List<AssessPersonnel> getAssessPersonnelList() {
		return assessPersonnelList;
	}

	public void setAssessPersonnelList(List<AssessPersonnel> assessPersonnelList) {
		this.assessPersonnelList = assessPersonnelList;
	}

	public AssessPersonnel getAp() {
		return ap;
	}

	public void setAp(AssessPersonnel ap) {
		this.ap = ap;
	}

	public Users getPageUser() {
		return pageUser;
	}

	public void setPageUser(Users pageUser) {
		this.pageUser = pageUser;
	}

	public UserServer getUserServer() {
		return userServer;
	}

	public void setUserServer(UserServer userServer) {
		this.userServer = userServer;
	}

}
