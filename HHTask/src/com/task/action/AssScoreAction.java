package com.task.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.AssScoreServer;
import com.task.Server.TemplateDetailsServer;
import com.task.Server.TemplateServer;
import com.task.Server.UserServer;
import com.task.Server.WageServer;
import com.task.Server.zgkh.ScoreStatisticsServer;
import com.task.entity.AssScore;
import com.task.entity.Template;
import com.task.entity.Users;
import com.task.entity.Wage;
import com.task.entity.zgkh.ScoreStatistics;
import com.task.util.SendMail;

@SuppressWarnings("serial")
public class AssScoreAction extends ActionSupport {

	private AssScoreServer assScoreServer;
	private TemplateServer templateServer;
	private TemplateDetailsServer templateDetailsServer;
	private WageServer wageServer;
	private ScoreStatisticsServer ssServer;// 主管考核总分
	private ScoreStatistics ss;
	private UserServer userServer;

	private AssScore assScore;// 考核成绩
	private int templateId[];// 模板明细id
	private Float score[];// 成绩
	private int id;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private String status;// 状态
	private List<AssScore> assScorewList;// 成绩集合
	private String cardId;// 卡号
	private String userCode;// 工号

	// 分页
	private String cpage = "1";// 第几页
	private String total;// 总数量
	private String url;// 跳转地址
	private int pageSize = 15;// 每页的数量

	// 添加考核成绩(员工级)
	public String addAssScore() {
		// 检查该人员是否离职
		Users user = userServer.findUserById(assScore.getUserId());
		if (user.getOnWork() == null || user.getOnWork().length() <= 0
				|| user.getOnWork().equals("离职")) {
			errorMessage = "该人员已离职!无法打分";
			return ERROR;
		}

		// 查询打分人员的部门部留是否存在
		boolean bool = assScoreServer.findContractBonus();
		if (bool == false) {
			//errorMessage = "您本月的部门奖金部留尚未分配!请您先前往分配";
			//return ERROR;
		}
		Template template = templateServer.findTemplateById(id);// 查询模板信息
		if (template != null) {
			// 查询本人是否为其打过分
			if(assScore!=null && "转正".equals(template.getType())){
				AssScore	assScore1 = assScoreServer.findOldAssScore1(assScore);
				if(assScore1!=null && assScore1.getPercentageScore()<60){
					bool = true;
				}
			}else{
				bool = assScoreServer.findOldAssScore(assScore);
			}
			if (bool) {
				// 查询出定制总分
				Float sumCustomScore = templateDetailsServer.findSumScore(id,
						"root");
				// 添加考核成绩成功
				if (assScoreServer.addAssScore(assScore, template, templateId,
						score, sumCustomScore)) {
					if("转正".equals(template.getType())){
						return "addAssScoreSuccess";
					}
					String str = "";
					try {
						// 更新考核模板状态为"结束"
						if (templateServer.updateTemplateFinsh(template) == false) {
							str = "<font color='red'>(更新模板状态出错!)</font>";
						}

						// 添加工资信息 (固定项以及计算绩效考核成绩)
						if (wageServer.addWage(assScore) == false) {
							str += "<font color='red'>(添加绩效考核工资出错!)</font>";
						}

						if (!"".equals(str)) {
							SendMail sendMail = new SendMail();
							sendMail.sendMail(sendMail.spareMail2,
									"来自红湖作业网的错误信息", template.getDept() + str);
						}
					} catch (Exception e) {
						e.printStackTrace();
						SendMail sendMail = new SendMail();
						sendMail.sendMail(sendMail.spareMail2, "来自红湖作业网的错误信息",
								template.getDept() + "部门的模板更新状态时或者添加工资的时候出错!");
					}

					status = "finish";
					successMessage = "<h2>为员工: <font color='red'>"
							+ assScore.getUserName()
							+ "</font> 打分成功!<br><br>"
							+ assScore.getUserName()
							+ "本次考核的总成绩为 <font color='red'>"
							+ assScore.getAccScore()
							+ "</font> 分(考核满分为:"
							+ sumCustomScore
							+ "分)"
							+ "<br/><br/>"
							+ "百分比成绩为 <font color='red'>"
							+ assScore.getPercentageScore()
							+ "</font> 分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h2>"
							+ str
							+ " <a href='TemplateAction!findUsersByDept.action?status=score'>继续打分</a>";
					ActionContext.getContext().getSession().put(
							"successMessage", successMessage);
					return "addAssScoreSuccess";
				} else {
					errorMessage = "打分出错,请检查后重试!";
				}
			} else {
				errorMessage = "您已为该员工打过分,请勿重复打分!谢谢!";
			}
		} else {
			errorMessage = "该考核模板不存在!请检查后重试!";
		}
		return ERROR;
	}

	// 添加考核成绩(主管级)
	public String addZgkhAssScore() {
		Template template = templateServer.findTemplateById(id);// 查询模板信息
		if (template != null) {
			boolean bool = assScoreServer.findOldAssScore(assScore);
			if (bool) {
				// 查询出定制总分
				Float sumCustomScore = templateDetailsServer.findSumScore(id,
						"root");
				// 添加考核成绩成功
				if (assScoreServer.addAssScore(assScore, template, templateId,
						score, sumCustomScore)) {
					errorMessage = ssServer.addScoreStatistics(assScore);// 添加总分
					status = "finish";
					successMessage = "<h2>为员工: <font color='red'>"
							+ assScore.getUserName()
							+ "</font> 打分成功!<br><br>"
							+ assScore.getUserName()
							+ "本次考核的总成绩为 <font color='red'>"
							+ assScore.getAccScore()
							+ "</font> 分(考核满分为:"
							+ sumCustomScore
							+ "分)"
							+ "<br/><br/>"
							+ "百分比成绩为 <font color='red'>"
							+ assScore.getPercentageScore()
							+ "</font> 分</h2>"
							+ errorMessage
							+ " <a href='AssessPersonnelAction!findApForScore.action'>继续打分</a>";
					ActionContext.getContext().getSession().put(
							"successMessage", successMessage);
					return "addZgkhAssScore";
				} else {
					errorMessage = "打分出错,请检查后重试!";
				}
			} else {
				errorMessage = "该员工成绩已存在,请勿重复打分!谢谢!";
			}
		} else {
			errorMessage = "该考核模板不存在!请检查后重试!";
		}
		return ERROR;
	}

	// 分页查询所有成绩
	@SuppressWarnings("unchecked")
	public String findAllAccScore() {
		assScorewList = assScoreServer.findAllAssScore(Integer.parseInt(cpage),
				pageSize, status);
		if (assScorewList != null && assScorewList.size() > 0) {
			int count = assScoreServer.getCountByAll(status);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("AssScoreAction!findAllAccScore.action?status="
					+ status);
		} else {
			errorMessage = "没有找到你要查询的内容!";
		}
		ActionContext.getContext().put("pageStatus", status);
		return "deptAssScoreSuccess";
	}

	// 条件查询成绩 (分页)
	@SuppressWarnings("unchecked")
	public String findAssScoreByCondition() {
		if (assScore == null) {
			assScore = (AssScore) ActionContext.getContext().getSession().get(
					"ASSSCORE");
		} else {
			ActionContext.getContext().getSession().put("ASSSCORE", assScore);
		}
		if (status != null && "zg".equals(status)) {
			if (assScore == null) {
				assScore = new AssScore();
			}
			Users user = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);
			assScore.setAddUserId(user.getId());// 添加人id
			assScore.setAssType("主管级");
		}

		assScorewList = assScoreServer.findAssScoreByCondition(assScore,
				Integer.parseInt(cpage), pageSize);
		if (assScorewList != null && assScorewList.size() > 0) {
			int count = assScoreServer.getCountByCondition(assScore);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("AssScoreAction!findAssScoreByCondition.action?status="
					+ status);
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		ActionContext.getContext().put("pageStatus", status);
		if ("zg".equals(status)) {
			return "zgAssScoreSuccess";
		}
		return "deptAssScoreSuccess";

	}

	// 成绩明细
	public String scoreDetail() throws IOException {
		assScore = assScoreServer.findAssScoreById(id);
		String assScoreDetails = assScoreServer.PreviewAssScore(assScore);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(assScoreDetails);
		response.getWriter().close();
		return null;
	}

	// 分数统计
	@SuppressWarnings("unchecked")
	public String showScoreView() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();// 解决FusionCharts
		// 乱码
		response.setContentType("text/html; charset='GBK'");// 解决FusionCharts
		// 乱码
		response.setCharacterEncoding("GBK");// 解决FusionCharts 乱码
		PrintWriter out = response.getWriter();
		List sumScoreList = assScoreServer.findScoreViewByCarid(cardId,
				userCode);// 查询出前六个月成绩
		StringBuilder pieStr = new StringBuilder();
		if (sumScoreList != null && sumScoreList.size() > 0) {
			int listSize = sumScoreList.size();
			AssScore firstAssScore = (AssScore) sumScoreList.get(listSize - 1);
			AssScore lastAssScore = (AssScore) sumScoreList.get(0);

			pieStr
					.append("<chart caption='"
							+ firstAssScore.getUserName()
							+ " "
							+ firstAssScore.getAsstMouth()
							+ "——"
							+ lastAssScore.getAsstMouth()
							+ "份绩效考核成绩柱状图' yAxisName=\"分数(分)\" baseFontSize=\"15\" "
							+ "showValues=\"0	\" decimals=\"0\" formatNumberScale=\"0\">");
			for (int i = listSize; i > 0; i--) {
				AssScore assScore = (AssScore) sumScoreList.get(i - 1);
				String mouth = assScore.getAsstMouth();// 考核月份
				Float sumScore = assScore.getPercentageScore();// 考核成绩
				pieStr.append("<set label=\"" + mouth + "\" value=\""
						+ sumScore + "\"/>");
			}
		}
		pieStr.append("</chart>");
		out.print(pieStr.toString());
		out.flush();
		out.close();
		return null;
	}

	// 删除分数以及分数详细
	@SuppressWarnings("unchecked")
	public String delScore() {
		int today = Integer.parseInt(new SimpleDateFormat("dd")
				.format(new Date()));
		// if (today >= 1 && today <= 7) {
		if (today == today) {
			assScore = assScoreServer.findAssScoreById(id);
			if (assScore != null) {
				boolean bool = assScoreServer.delScore(assScore);// 删除成绩
				if (bool) {
					ActionContext.getContext().getSession().put(
							"successMessage", successMessage);
					if (status != null && "zg".equals(status)) {
						bool = ssServer.delScore(assScore);// 处理主管总分信息
						return "delZgScore";
					} else {
						Wage wage = wageServer.findWageByCodeAndCardId(assScore
								.getCode(), assScore.getCardId(), assScore
								.getAsstMouth());// 查询工资
						bool = wageServer.delWage(wage);
						successMessage = "删除" + assScore.getUserName()
								+ assScore.getAsstMouth() + "的成绩成功!";
						if (bool == false) {
							successMessage += "(删除工资信息出错!请与管理员联系!)";
						}
						ActionContext.getContext().getSession().put(
								"successMessage", successMessage);
						return "delScoreSuccess";
					}

				} else {
					errorMessage = "删除" + assScore.getUserName()
							+ assScore.getAsstMouth() + "的成绩失败!";
				}
			} else {
				errorMessage = "不存在该成绩,请检查后重试!";
			}

			return ERROR;
		} else {
			errorMessage = "今天无法删除成绩!删除时间为每月的1号到7号!谢谢!";
			return ERROR;
		}
	}

	// 查询登录人所打的分数(主管级)
	@SuppressWarnings("unchecked")
	public String findZgScoreByLoginUser() {
		if (assScore == null) {
			assScore = (AssScore) ActionContext.getContext().getSession().get(
					"AssScore");
		} else {
			ActionContext.getContext().getSession().put("AssScore", assScore);
		}

		return ERROR;
	}
	

	public AssScoreServer getAssScoreServer() {
		return assScoreServer;
	}

	public void setAssScoreServer(AssScoreServer assScoreServer) {
		this.assScoreServer = assScoreServer;
	}

	public TemplateServer getTemplateServer() {
		return templateServer;
	}

	public void setTemplateServer(TemplateServer templateServer) {
		this.templateServer = templateServer;
	}

	public WageServer getWageServer() {
		return wageServer;
	}

	public void setWageServer(WageServer wageServer) {
		this.wageServer = wageServer;
	}

	public AssScore getAssScore() {
		return assScore;
	}

	public void setAssScore(AssScore assScore) {
		this.assScore = assScore;
	}

	public int[] getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int[] templateId) {
		this.templateId = templateId;
	}

	public Float[] getScore() {
		return score;
	}

	public void setScore(Float[] score) {
		this.score = score;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public List<AssScore> getAssScorewList() {
		return assScorewList;
	}

	public void setAssScorewList(List<AssScore> assScorewList) {
		this.assScorewList = assScorewList;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public TemplateDetailsServer getTemplateDetailsServer() {
		return templateDetailsServer;
	}

	public void setTemplateDetailsServer(
			TemplateDetailsServer templateDetailsServer) {
		this.templateDetailsServer = templateDetailsServer;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public ScoreStatistics getSs() {
		return ss;
	}

	public void setSs(ScoreStatistics ss) {
		this.ss = ss;
	}

	public ScoreStatisticsServer getSsServer() {
		return ssServer;
	}

	public void setSsServer(ScoreStatisticsServer ssServer) {
		this.ssServer = ssServer;
	}

	public UserServer getUserServer() {
		return userServer;
	}

	public void setUserServer(UserServer userServer) {
		this.userServer = userServer;
	}

}
