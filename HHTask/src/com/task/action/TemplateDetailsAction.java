package com.task.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.TemplateDetailsServer;
import com.task.Server.TemplateServer;
import com.task.entity.Template;
import com.task.entity.TemplateDetails;

public class TemplateDetailsAction extends ActionSupport {

	private TemplateDetailsServer templateDetailsServer;
	private TemplateServer templateServer;
	private TemplateDetails templateDetails;
	private Template template;
	private String successMessage;
	private String errorMessage;
	private int id;
	private int pageLayer;// 当前层
	private String pageOnLayer;// 上一层
	private String scoreMessage;// 分数信息
	private Float sumScore;// 最大配分

	// 添加模板明细
	@SuppressWarnings("unchecked")
	public String addTemplateDetails() {
		template = templateServer.findTemplateById(id);
		if (template != null) {
			templateDetails.setProject(templateDetails.getProject().replaceAll(
					"<", "&lt;").replaceAll(">", "&gt;"));

			boolean bool = true;
			bool = templateDetailsServer.findOldTemplateDetaild(
					templateDetails, template);// 查询是否已经存在相同的内容
			if (bool) {
				bool = templateDetailsServer.addTemplateDetails(
						templateDetails, template);// 添加
				if (bool) {
					if ("root".equals(templateDetails.getOnLayer())) {// 如果是第一层,跳到查询Template方法
						id = template.getId();
						successMessage = "添加明细 " + templateDetails.getProject()
								+ " 成功!";
						return "delTemplateDetailsSuccess";
					}
					bool = templateDetailsServer.chageTdsScore(templateDetails);// 更新上层分数
					if (bool) {
						id = Integer.parseInt(templateDetails.getOnLayer());
						ActionContext.getContext().getSession().put(
								"pageLayer", templateDetails.getLayer());
						ActionContext.getContext().getSession().put(
								"pageOnLayer", templateDetails.getOnLayer());
						successMessage = "添加明细 " + templateDetails.getProject()
								+ " 成功!";
						ActionContext.getContext().getSession().put(
								"successMessage", successMessage);
						return "addTemplateDetailsSuccess";
					} else {
						errorMessage = "更新分数出错,请检查!";
					}
				} else {
					errorMessage = "添加模板明细出错,请检查后重试!";
				}
			} else {
				errorMessage = "该内容已经存在,请勿重复添加!谢谢!";
			}
		} else {
			errorMessage = "不存在该模板,请检查后重试!";
		}
		return ERROR;
	}

	// 通过id查找模板明细
	public String findTemDetailsById() {
		templateDetails = templateDetailsServer.findTemDetailsById(id);
		if (templateDetails != null) {
			template = templateDetails.getTemplate();
			if (pageLayer == 0) {// 添加明细跳转过来
				pageLayer = (Integer) ActionContext.getContext().getSession()
						.get("pageLayer");
				pageOnLayer = (String) ActionContext.getContext().getSession()
						.get("pageOnLayer");
				ActionContext.getContext().getSession().remove("pageOnLayer");
				ActionContext.getContext().getSession().remove("pageLayer");

			} else if (pageLayer > 0) {// 查询跳转
				if (templateDetails.getLayer() == pageLayer) {// 上一层
					if (pageLayer == 1) {
						pageOnLayer = "root";
					} else {
						templateDetails = templateDetailsServer
								.findTemDetailsById(Integer
										.parseInt(templateDetails.getOnLayer()));
						pageOnLayer = String.valueOf(templateDetails.getId());
					}
				} else {// 下一层
					pageLayer = pageLayer;
					pageOnLayer = String.valueOf(templateDetails.getId());
				}
			}
			// 主管级考核
			if ("主管级".equals(template.getAssObject())) {
				return "zgfindTemDetails";
			}
			return "findTemDetailsByIdSuccess";
		} else {
			errorMessage = "不存在该模板明细数据!请检查后重试!";
		}

		return ERROR;
	}

	// 级联删除模板明细
	public String delTemplateDetails() {
		templateDetails = templateDetailsServer.findTemDetailsById(id);
		if (templateDetails != null) {
			template = templateDetailsServer
					.delTemplateDetails(templateDetails);
			if (template != null) {
				if (templateServer.updateTemplate(template)) {
					templateDetailsServer.chageTdsScore(templateDetails);// 更新上层分数
					boolean bool = templateDetailsServer
							.chageTdsScore(templateDetails);// 更新上层分数
					if (bool) {
						id = template.getId();
						successMessage = "删除考核明细:"
								+ templateDetails.getProject() + "成功!";
						ActionContext.getContext().getSession().put(
								"successMessage", successMessage);
						return "delTemplateDetailsSuccess";
					} else {
						errorMessage = "更新分数出错,请检查!";
					}
				}
			}
			errorMessage = "删除出错,请检查后重试!";

		} else {
			errorMessage = "不存在该明细!";
		}
		return ERROR;
	}

	// 生成修改明细样式
	public String generateTds() throws IOException {
		templateDetails = templateDetailsServer.findTemDetailsById(id);
		String templateMessage = "<form action='TemplateDetailsAction!updateTemplateDetails.action' method='post'>"
				+ "<hr color='#BFEFFF'><input type='hidden' name='id' value='"
				+ templateDetails.getId()
				+ "'>"
				+ "<table class='table'>"
				+ "<tr>"
				+ "<td colspan='4' align='center'>修改模板明细  ——"
				+ templateDetails.getProject()
				+ "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td align='right'>内容:</td>"
				+ "<td>"
				+ "<textarea rows='5' cols='60' name='templateDetails.project' id='project' style='padding: 0px; margin: 0px'>"
				+ templateDetails.getProject() + "</textarea>" + "</td>";
		// 第一层或者打分项去除配分项
		if ("no".equals(templateDetails.getIsSroce())) {
			templateMessage += "<td align='right'>配分:</td>"
					+ "<td>"
					+ "<input type='text' readonly='readonly' name='templateDetails.customScore' onkeyup=if(isNaN(value))execCommand('undo') value='"
					+ templateDetails.getCustomScore() + "'>" + "</td></tr>";
		} else {
			templateMessage += "<td align='right'>配分:</td>"
					+ "<td>"
					+ "<input type='text' name='templateDetails.customScore' onkeyup=if(isNaN(value))execCommand('undo') value='"
					+ templateDetails.getCustomScore() + "'>" + "</td>"
					+ "</tr>";
		}
		String yesOrno = templateDetails.getIsJida() != null
				&& templateDetails.getIsJida().equals("yes") ? "是" : "否";
		templateMessage += "<tr><th align='right'>是否机打:</th><td><select name='templateDetails.isJida' id='isJidaSelect2' onchange='showJidaClass(2)'>"
				+ "<option value='"
				+ (templateDetails.getIsJida() != null
						&& templateDetails.getIsJida().equals("yes") ? "yes"
						: "no")
				+ "'>"
				+ yesOrno
				+ "</option><option value='no'>否</option><option value='yes'>是</option></select></td>";

		templateMessage += "<th align='right' id='jidaClasssTh2'  style='display: none;'>机打类别:</th><td id='jidaClasssTd2'  style='display: none;'>"
				+ "<select name='templateDetails.jidaClasss' id='jidaClasssselect2'><option></option><option value='order'>订单完成率</option></select></td></tr>";

		String isScore = "<tr>" + "<td align='right'>打分项</td>" + "<td>";

		if ("root".equals(templateDetails.getOnLayer())) {// 如果是第一层,隐藏打分项
			templateMessage += "<input type='hidden' name='templateDetails.isSroce' value='no' checked='checked'>否";
		} else {
			templateMessage += isScore;
			// 是打分项，找到当前打分状态
			if ("yes".equals(templateDetails.getIsSroce())) {
				templateMessage += "<input type='radio' name='templateDetails.isSroce' value='yes' checked='checked'>是<input type='radio' name='templateDetails.isSroce' value='no' >否";
			} else {
				templateMessage += "<input type='radio' name='templateDetails.isSroce' value='yes' >是<input type='radio' name='templateDetails.isSroce' value='no' checked='checked'>否";
			}
		}
		templateMessage += "</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td colspan='4' align='center'>"
				+ "<input type='submit' value='修改'style='width: 80px; height: 50px;'>&nbsp;&nbsp;&nbsp;&nbsp;"
				+ "<input type='button' value='重置'style='width: 80px; height: 50px;'>"
				+ "</td>" + "</tr>" + "</table>" + "</form>";

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(templateMessage);
		response.getWriter().close();
		return null;
	}

	// 修改模版明细
	public String updateTemplateDetails() {
		TemplateDetails oldTemplateDetails = templateDetailsServer
				.findTemDetailsById(id);// 查询老的模板明细

		oldTemplateDetails.setProject(templateDetails.getProject().replaceAll(
				"<", "&lt;").replaceAll(">", "&gt;"));

//		// 第一层或者打分项去除配分项
//		if (templateDetails.getCustomScore() != null
//				&& templateDetails.getCustomScore() > 0F) {
//			oldTemplateDetails.setIsSroce("yes");
//		} else {
//			Float sumScore = templateDetailsServer.findSumScore(
//					oldTemplateDetails.getTemplate().getId(), String
//							.valueOf(oldTemplateDetails.getId()));// 查询该模板下一层的总分
//			// 最后一层更改分数
//			if (sumScore == 0) {
//				oldTemplateDetails.setCustomScore(templateDetails
//						.getCustomScore());
//			}
//			oldTemplateDetails.setIsSroce(templateDetails.getIsSroce());
//		}
		Float sumScore = templateDetailsServer.findSumScore(
				oldTemplateDetails.getTemplate().getId(), String
						.valueOf(oldTemplateDetails.getId()));// 查询该模板下一层的总分
		// 最后一层更改分数
		if (sumScore == 0) {
			oldTemplateDetails.setIsSroce("yes");
			oldTemplateDetails.setCustomScore(templateDetails
					.getCustomScore());
		}
		if (templateDetailsServer.updateTems(oldTemplateDetails)) {// 更新
			boolean bool = templateDetailsServer
					.chageTdsScore(oldTemplateDetails);// 更新上层分数
			if (bool) {
				if ("root".equals(oldTemplateDetails.getOnLayer())) {
					id = oldTemplateDetails.getTemplate().getId();
					return "delTemplateDetailsSuccess";
				} else {

					id = Integer.parseInt(oldTemplateDetails.getOnLayer());
					ActionContext.getContext().getSession().put("pageLayer",
							oldTemplateDetails.getLayer());
					ActionContext.getContext().getSession().put("pageOnLayer",
							oldTemplateDetails.getOnLayer());
					return "addTemplateDetailsSuccess";

				}
			} else {
				errorMessage = "更新分数出错,请检查!";
			}
		}
		return ERROR;
	}

	public TemplateDetailsServer getTemplateDetailsServer() {
		return templateDetailsServer;
	}

	public void setTemplateDetailsServer(
			TemplateDetailsServer templateDetailsServer) {
		this.templateDetailsServer = templateDetailsServer;
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

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public TemplateServer getTemplateServer() {
		return templateServer;
	}

	public void setTemplateServer(TemplateServer templateServer) {
		this.templateServer = templateServer;
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

	public String getScoreMessage() {
		return scoreMessage;
	}

	public void setScoreMessage(String scoreMessage) {
		this.scoreMessage = scoreMessage;
	}

	public Float getSumScore() {
		return sumScore;
	}

	public void setSumScore(Float sumScore) {
		this.sumScore = sumScore;
	}

}
