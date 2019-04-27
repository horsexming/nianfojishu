package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;

import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.TemplateDetailsServer;
import com.task.Server.TemplateServer;
import com.task.Server.sys.CircuitRunServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.ContractBonus;
import com.task.entity.OaAppDetail;
import com.task.entity.OaMessageAlerm;
import com.task.entity.TaHkHuikuan;
import com.task.entity.Template;
import com.task.entity.TemplateDetails;
import com.task.entity.Users;
import com.task.entity.system.CircuitRun;
import com.task.entity.zgkh.AssessPersonnel;
import com.task.util.Util;

public class TemplateServerImpl implements TemplateServer {
	private TotalDao totalDao;
	private TemplateDetailsServer templateDetailsServer;
	private CircuitRunServer circuitRunServer;

	public CircuitRunServer getCircuitRunServer() {
		return circuitRunServer;
	}

	public void setCircuitRunServer(CircuitRunServer circuitRunServer) {
		this.circuitRunServer = circuitRunServer;
	}

	public TemplateDetailsServer getTemplateDetailsServer() {
		return templateDetailsServer;
	}

	public void setTemplateDetailsServer(
			TemplateDetailsServer templateDetailsServer) {
		this.templateDetailsServer = templateDetailsServer;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 添加模板
	public boolean addTemplate(Template template) {
		if (template != null) {
			Users user = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);
			template.setCustomDate(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));// 定制时间
			template.setCustomProple(user.getName());// 定制人
			template.setCardId(user.getCardId());// 卡号
			template.setCustomUserId(user.getId());
			if (template.getType() != null && "转正".equals(template.getType())) {
				template.setAsstMouth(null);
				template.setDept(null);
			}
			// template.setAsstMouth(totalDao.getDateTime("yyyy-MM月"));// 考核月份
			// template.setStatus("添加明细");// 模板状态
			boolean bool = totalDao.save(template);
			if (bool) {
				// try {
				// Integer epId = CircuitRunServerImpl.createProcess(18,
				// Template.class, template.getId(), "status", "id",
				// user.getDept() + "部门的绩效考核模板请审核!", false);
				// if (epId != null && epId > 0) {
				// template.setEpId(epId);
				// totalDao.update(template);
				// }
				// } catch (Exception e) {
				// e.printStackTrace();
				// }
			}
			return bool;
		}
		return false;
	}

	// 根据id查找模板
	public Template findTemplateById(int id) {
		if ((Object) id != null && id != 0) {
			return (Template) totalDao.getObjectById(Template.class, id);
		}
		return null;
	}

	// 修改模板状态
	public boolean updateTemplate(Template template) {
		if (template != null) {
			if (template.getStatus() != null
					&& "同意".equals(template.getStatus())
					|| "打回".equals(template.getStatus())) {
				Users user = (Users) ActionContext.getContext().getSession()
						.get(totalDao.users);

				template.setAuditPeople(user.getName());
			}
			if ("打回".equals(template.getStatus())) {
				// CircuitRunServerImpl.updateCircuitRun(template.getEpId());
				template.setStatus("审批中");
			}
			boolean bool = totalDao.update(template);
			if (bool) {
				try {

					// 暂时不在审批
					// Users user = (Users) ActionContext.getContext()
					// .getSession().get(totalDao.users);
					// String processName = "员工级绩效考核模版审核";
					// Integer epId =
					// CircuitRunServerImpl.createProcess(processName,
					// Template.class, template.getId(), "status",
					// "id",user.getDept() + "部门的月度绩效考核模板请您审核!", true,"打分");
					// if (epId != null && epId > 0) {
					// template.setEpId(epId);
					// totalDao.update(template);
					// }
				} catch (Exception e) {
					e.printStackTrace();
				}
				return bool;
			}

		}
		return false;
	}

	// 查询模板(分页)
	@SuppressWarnings("unchecked")
	public Object[] findAllTemplate(int pageNo, int pageSize, String status) {
		if (!status.isEmpty() && status.length() > 0) {
			Users user = (Users) ActionContext.getContext().getSession().get(
					"Users");
			String hql = "";
			if ("history".equals(status)) {// 所有历史记录
				hql = "from Template where assObject='员工级' and customUserId="
						+ user.getId();
				hql += " order by customDate desc";
			} else if ("userHistory".equals(status)) {// 添加明细或者提交审核
				hql = "from Template where status not in ('添加明细','审核','打回') and customUserId="
						+ user.getId();
				hql += " order by customDate desc";
			} else if ("audit".equals(status)) {// 待审核
				hql = "from Template where status = '审核' order by customDate desc";
			} else if ("byBack".equals(status)) {// 审核历史
				hql = "from Template where status <> '添加明细' and status <> '审核' order by customDate desc";
			} else if ("zhuguan".equals(status)) {// 审核历史
				hql = "from Template where assObject='主管级' and customUserId ="
						+ user.getId() + " ";
			} else if ("groupTemplate".equals(status)) {// 所属成员历史模版
				hql = "from Template where assObject='员工级' and status not in ('添加明细','审核','打回') and id in "
						+ "(select t.id from Template t join t.assessPersonnel a  where a.id in "
						+ "(select id from AssessPersonnel where userId in (select userId from AssessPersonnel where addUserId ="
						+ user.getId() + " )))";
			} else {
				return null;
			}
			List list = totalDao.findAllByPage(hql, pageNo, pageSize);
			int count = totalDao.getCount(hql);
			return new Object[] { list, count };
		}
		return null;
	}

	// 预览模板
	public String PreviewTemplate(Template template) {
		// Set<TemplateDetails> allSet = template.getTemplateDetails();
		// List allTemsList = new ArrayList();
		// List rootList = new ArrayList();
		// for (TemplateDetails templateDetails : allSet) {
		// allTemsList.add(templateDetails);
		// String onLayer = templateDetails.getOnLayer();
		// if (!onLayer.isEmpty() && "root".equals(onLayer)) {
		// rootList.add(templateDetails);
		// }
		// }
		String hql = "from TemplateDetails ts where ts.template.id=? order by customScore desc";
		String hql2 = "from TemplateDetails ts where ts.template.id=? and onLayer='root'";
		String hql3 = "select sum(customScore) from TemplateDetails ts where ts.template.id=? and customScore>=0 and onLayer='root'";
		List allTemsList = totalDao.query(hql, template.getId());
		List rootList = totalDao.query(hql2, template.getId());
		Float customScore_sum = (Float) totalDao.getObjectByCondition(hql3,
				template.getId());
		String str = "<input id='customScore_sum' type='hidden'  value='"
				+ customScore_sum + "' />";
		if (template.getAssObject().equals("主管级")) {
			str += "<table style='margin:10 10 10 0' class='table' border='1' width='95%'><tr>"
					+ "<td colspan='10' align='center'>"
					+ template.getName()
					+ "</td></tr><tr><td align='center' colspan='2'>考核对象:("
					+ template.getAssObject()
					+ ")&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr><tr><td colspan='2'>"
					+ "<div align='center' style='float: left;width: 50%'>项目及内容</div>"
					+ "<div align='right'style='float: left;width: 50%; padding-right: 20px'>"
					+ "打分</div></td></tr>";
		} else {
			str += "<table style='margin:10 10 10 0' class='table' border='1' width='95%'><tr>"
					+ "<td colspan='10' align='center'>"
					+ template.getName()
					+ "</td></tr><tr><td align='center' colspan='2'>考核对象:("
					+ template.getAssObject()
					+ ")&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 考核部门:("
					+ template.getDept()
					+ ")&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 考核月份:("
					+ template.getAsstMouth()
					+ ")</td></tr><tr><td colspan='2'>"
					+ "<div align='center' style='float: left;width: 50%'>项目及内容</div>"
					+ "<div align='right'style='float: left;width: 50%; padding-right: 20px'>"
					+ "打分</div></td></tr>";
		}
		ActionContext.getContext().getSession().remove("UL");
		for (int i = 0; i < rootList.size(); i++) {
			TemplateDetails tems = (TemplateDetails) rootList.get(i);
			allTemsList.remove(tems);
			str += "<tr><td align='center' width='10%'>" + tems.getProject()
					+ "<br>(" + tems.getCustomScore() + "分)</td><td>";
			String temp = "";
			if (template.getAssObject().equals("主管级")) {
				temp = findList(allTemsList, tems);
			} else {
				temp = findList2(allTemsList, tems);
			}
			if (temp == null || "null".equals(temp)) {
				temp = "";
			}
			str += temp;
			str += "</td></tr>";
			ActionContext.getContext().getSession().remove("UL");
		}
		str += "</table>";
		return str;
	}

	// 预览功能查询方法
	@SuppressWarnings("unchecked")
	private String findList(List<TemplateDetails> list, TemplateDetails temDs) {
		List list1 = new ArrayList();
		String onLayer = String.valueOf(temDs.getId());
		// Map map = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			TemplateDetails tems = (TemplateDetails) list.get(i);
			String listOnLayer = tems.getOnLayer();// 明细上层
			if (!onLayer.isEmpty() && onLayer.equals(listOnLayer)) {
				list1.add(tems);
				list.remove(tems);
				i--;
			}
		}
		if (list1 != null && list1.size() > 0) {
			for (int j = 0; j < list1.size(); j++) {
				TemplateDetails tems = (TemplateDetails) list1.get(j);
				String sessionUl = (String) ActionContext.getContext()
						.getSession().get("UL");
				if (sessionUl == null) {
					sessionUl = "";
				}
				if (sessionUl.indexOf("ul") > 0) {
					if (j == 0) {
						sessionUl += "<ul class='userCenter'><li>"
								+ tems.getProject() + "("
								+ tems.getCustomScore() + "分)<hr>";
					} else {
						sessionUl += "<li>" + tems.getProject() + "("
								+ tems.getCustomScore() + "分)<hr>";
					}
				} else {
					sessionUl += "<ul class='userCenter' style='margin: 0px; padding: 0px'><li>"
							+ tems.getProject()
							+ "("
							+ tems.getCustomScore()
							+ "分)<hr>";
				}

				ActionContext.getContext().getSession().put("UL", sessionUl);
				findList(list, tems);// 回调

				String sessionUl2 = (String) ActionContext.getContext()
						.getSession().get("UL");
				sessionUl2 += "</li>";
				ActionContext.getContext().getSession().put("UL", sessionUl2);
			}
			String sessionUl = (String) ActionContext.getContext().getSession()
					.get("UL");
			sessionUl += "</ul>";
			ActionContext.getContext().getSession().put("UL", sessionUl);
		} else {

			String select = "<select name='score' onkeydown='window.history.forward(1);if(event.keyCode == 8){this.options[0].text =\"\";}' "
					+ "onkeypress=writeSelect(this) onchange=chackScore('"
					+ temDs.getCustomScore()
					+ "',this) onkeyup=chackScore('"
					+ temDs.getCustomScore() + "',this) >";
			if (temDs.getIsJida() != null && "yes".equals(temDs.getIsJida())) {
				// 订单完成率
				if ("order".equals(temDs.getJidaClasss())) {
					String hql = "select sum(o.completionrate)/count(*) from OrderManager o  where o.paymentDate like '%"
							+ Util.getLastMonth("yyyy-MM") + "%'";
					Float dingdanLv = (Float) totalDao.query(hql).get(0);
					Float score = temDs.getCustomScore() * dingdanLv / 100;
					select += "<option value='" + score + "'>" + score
							+ "</option>";
				}
				// 项目核算完成率
				if ("project".equals(temDs.getJidaClasss())) {
					Float score = 100F;
					select += "<option value='" + score + "'>" + score
							+ "</option>";
				}
			} else {
				select += "<option value='0'></option>";
				int customScore = (int) ((float) temDs.getCustomScore());
				for (int i = customScore; i >= 0; i--) {
					select += "<option value='" + i + "'>" + i + "</option>";
				}
			}
			select += "</select>";
			String sessionUl = (String) ActionContext.getContext().getSession()
					.get("UL");
			if (sessionUl == null) {
				sessionUl = "";
			}
			if (sessionUl.indexOf("ul") > 0) {
				sessionUl = sessionUl.substring(0, sessionUl
						.lastIndexOf("<li>"));
				sessionUl += "<li><div align='left' style='float: left;'>"
						+ temDs.getProject()
						+ "("
						+ temDs.getCustomScore()
						+ "分)</div><div align='right' style='float:right;'>"
						+ select
						+ "<input size='5' type='hidden' name='templateId' value='"
						+ temDs.getId() + "'></div><hr style='clear: both;'>";
			} else {
				sessionUl += "<ul class='userCenter' style='margin: 0px; padding: 0px'><li><div align='right'>"
						+ select
						+ "<input size='5' type='hidden' name='templateId' value='"
						+ temDs.getId() + "'></div><hr></li></ul>";
			}
			ActionContext.getContext().getSession().put("UL", sessionUl);
		}
		return (String) ActionContext.getContext().getSession().get("UL");
	}

	// 预览功能查询方法2
	@SuppressWarnings( { "unused", "unchecked" })
	private String findList2(List<TemplateDetails> list, TemplateDetails temDs) {
		List list1 = new ArrayList();
		String onLayer = String.valueOf(temDs.getId());
		// Map map = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			TemplateDetails tems = (TemplateDetails) list.get(i);
			String listOnLayer = tems.getOnLayer();// 明细上层
			if (!onLayer.isEmpty() && onLayer.equals(listOnLayer)) {
				list1.add(tems);
				list.remove(tems);
				i--;
			}
		}

		if (temDs.getIsSroce() != null && "yes".equals(temDs.getIsSroce())) {
			String sessionUl = (String) ActionContext.getContext().getSession()
					.get("UL");
			if (sessionUl == null) {
				sessionUl = "";
			}
			sessionUl = sessionUl.substring(0, sessionUl.lastIndexOf("<hr>"));
			String select = "<select name='score' data='"
					+ temDs.getCustomScore()
					+ "' onkeydown='window.history.forward(1);if(event.keyCode == 8){this.options[0].text =\"\";}' "
					+ "onkeypress=writeSelect(this) onchange=chackScore('"
					+ temDs.getCustomScore() + "',this) onkeyup=chackScore('"
					+ temDs.getCustomScore() + "',this) >";
			if (temDs.getIsJida() != null && "yes".equals(temDs.getIsJida())) {
				// 订单完成率
				if ("order".equals(temDs.getJidaClasss())) {
					String hql = "select sum(o.completionrate)/count(*) from OrderManager o  where o.paymentDate like '%"
							+ Util.getLastMonth("yyyy-MM") + "%'";
					Float dingdanLv = (Float) totalDao.query(hql).get(0);
					Float score = temDs.getCustomScore() * dingdanLv / 100;
					select += "<option value='" + score + "'>" + score
							+ "</option>";
				}
				// 项目核算完成率
				if ("project".equals(temDs.getJidaClasss())) {
					Float score = 100F;
					select += "<option value='" + score + "'>" + score
							+ "</option>";
				}
				// 6s
				if ("6s".equals(temDs.getJidaClasss())) {
					// String hql =
					// "select sum(o.completionrate)/count(*) from OrderManager o  where o.paymentDate like '%"
					// + Util.getLastMonth("yyyy-MM") + "%'";
					// Float dingdanLv = (Float) totalDao.query(hql).get(0);
					Float dingdanLv = 0F;
					Float score = temDs.getCustomScore() * dingdanLv / 100;
					select += "<option value='" + score + "'>" + score
							+ "</option>";
				}
			} else {
				select += "<option value='0'></option>";
				int customScore = (int) ((float) temDs.getCustomScore());
				for (int j = 0; j < list1.size(); j++) {
					TemplateDetails tems = (TemplateDetails) list1.get(j);
					select += "<option value='" + tems.getCustomScore() + "'>"
							+ tems.getProject() + "(" + tems.getCustomScore()
							+ "分)</option>";
				}
			}

			select += "</select>";
			if (sessionUl.indexOf("ul") > 0) {
				sessionUl += "<div align='right' style='float:right;'>"
						+ select
						+ "<input size='5' type='hidden' name='templateId' value='"
						+ temDs.getId() + "'></div><hr style='clear: both;'>";
			} else {
				sessionUl += "<ul class='userCenter' style='margin: 0px; padding: 0px'><li><div align='right'>"
						+ select
						+ "<input size='5' type='hidden' name='templateId' value='"
						+ temDs.getId() + "'></div><hr></li></ul>";
			}
			ActionContext.getContext().getSession().put("UL", sessionUl);
		} else {
			for (int j = 0; j < list1.size(); j++) {
				TemplateDetails tems = (TemplateDetails) list1.get(j);
				String sessionUl = (String) ActionContext.getContext()
						.getSession().get("UL");
				if (sessionUl == null) {
					sessionUl = "";
				}
				if (sessionUl.indexOf("ul") > 0) {
					if (j == 0) {
						sessionUl += "<ul ><li>" + tems.getProject() + "("
								+ tems.getCustomScore() + "分)<hr>";
					} else {
						sessionUl += "<li>" + tems.getProject() + "("
								+ tems.getCustomScore() + "分)<hr>";
					}
				} else {
					sessionUl += "<ul  style='margin: 0px; padding: 0px'><li>"
							+ tems.getProject() + "(" + tems.getCustomScore()
							+ "分)<hr>";
				}

				ActionContext.getContext().getSession().put("UL", sessionUl);

				findList2(list, tems);// 回调

				String sessionUl2 = (String) ActionContext.getContext()
						.getSession().get("UL");
				sessionUl2 += "</li>";
				ActionContext.getContext().getSession().put("UL", sessionUl2);
			}
			String sessionUl = (String) ActionContext.getContext().getSession()
					.get("UL");
			sessionUl += "</ul>";
			ActionContext.getContext().getSession().put("UL", sessionUl);
		}
		return (String) ActionContext.getContext().getSession().get("UL");
	}

	// 删除模板
	public boolean delTemplate(Template template) {
		if (template != null) {
			template = (Template) totalDao.getObjectById(Template.class,
					template.getId());
			Set<AssessPersonnel> ap = template.getAssessPersonnel();
			for (AssessPersonnel assessPersonnel : ap) {
				assessPersonnel.getTemplate().remove(template);
			}
			return totalDao.delete(template);
		}
		return false;
	}

	// 根据模板部门名称查询部门所有人员(查询人除外)
	public List findUsersByDept(Template template) {
		if (template != null) {
			String hql = "from Users where onWork <> '离职' and id not in "
					+ "(select distinct u.id from Users u join u.template t1  where t1.id in "
					+ "(select u2.id from u.template u2 where u2.asstMouth=?)) and dept=? and id <>?";
			Users user = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);
			return totalDao.query(hql, template.getAsstMouth(), template
					.getDept(), user.getId());
		}
		return null;
	}

	// 根据模板部门名称查询部门所有人员(查询人除外)
	public List findUsersByDept() {
		Users user = (Users) ActionContext.getContext().getSession().get(
				"Users");
		String[] deptArr = user.getDept().split("and");
		String dept = "";
		for (int i = 0; i < deptArr.length; i++) {
			if (i < deptArr.length - 1) {
				dept += "'" + deptArr[i] + "',";
			} else {
				dept += " '" + deptArr[i] + "'";
			}
		}
		String hql = "from Users where id <>? and onWork <> '离职' and dept in ("
				+ dept
				+ ") and code not in (select code from AssScore where dept in ("
				+ dept
				+ ") and asstMouth=?"
				+ ") and cardId not in (select cardId from AssScore where dept in ("
				+ dept + ")  and asstMouth=?" + ")";
		return totalDao.query(hql, user.getId(), totalDao
				.getLastMonth("yyyy-MM月"), totalDao.getLastMonth("yyyy-MM月"));
	}

	// 条件查询模板明细
	public List findTDsByCondition(Template template, int pageNo, int pageSize,
			String status) {
		if (template != null && (Object) pageNo != null
				&& (Object) pageSize != null) {
			String other = null;
			String[] otherNames = null;
			String hql = totalDao.criteriaQueries(template, other, otherNames);
			if ("byBack".equals(status)) {
				hql += " and status<>'添加明细' and status<>'审核'";
			}
			hql += " order by customDate desc";
			return totalDao.findAllByPage(hql, pageNo, pageSize);
		}
		return null;
	}

	// 通过条件得到数量
	public Integer getCountBtCondition(Template template, String status) {
		if (template != null) {
			String other = null;
			String[] otherNames = null;
			String hql = totalDao.criteriaQueries(template, other, otherNames);
			if ("byBack".equals(status)) {
				hql += " and status<>'添加明细' and status<>'审核'";
			}
			return totalDao.getCount(hql);
		}
		return 0;
	}

	/**
	 * 查询定制人是否存在状态为审核的模版
	 * 
	 * @return 数量
	 */
	public Integer getCountBtCondition() {
		String hql = "from Template where status='审核' and customUserId=?";
		Users user = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);
		return totalDao.getCount(hql, user.getId());
	}

	// // 绑定审核人
	// public boolean updateTemplate(Template template, Set<Users> users) {
	// if (template != null) {
	// template.setUsers(users);
	// return totalDao.update(template);
	// }
	// return false;
	// }

	// 通过用户id查询用户绑定模板
	@Override
	public List findTemplateListByUserId(AssessPersonnel ap) {
		if (ap != null) {
			String mouth = totalDao.getLastMonth("yyyy-MM月");
			String hql = "from Template where id in (select t.id from Template t join t.assessPersonnel ap  where ap.id =?)"
					+ " and asstMouth=? and id not in (select templateId from AssScore where userId=?)";
			return totalDao.query(hql, ap.getId(), mouth, ap.getUserId());
		}
		return null;
	}

	// 更新模板转状态为结束
	public boolean updateTemplateFinsh(Template template) {
		Set<AssessPersonnel> apSet = template.getAssessPersonnel();
		Object[] object = apSet.toArray();
		String cardId = "";
		String code = "";
		for (int i = 0; i < object.length; i++) {
			AssessPersonnel ap = (AssessPersonnel) object[i];
			cardId += "'" + ap.getCardId() + "',";
			code += "'" + ap.getCode() + "',";
		}
		String hql1 = "from AssScore where code in ("
				+ code.substring(0, code.lastIndexOf(","))
				+ ") and asstMouth=?";
		List list = totalDao.query(hql1, template.getAsstMouth());
		if (list != null && list.size() > 0) {
			if (list.size() == object.length) {
				template.setStatus("结束");
				return totalDao.update(template);
			}
			return true;
		}
		return false;
	}

	// 使用历史模板(直接使用或修改使用)
	@SuppressWarnings("unchecked")
	public boolean useHirstory(Template template, String status) {
		if (template != null && status != null && status.length() > 0) {
			if ("direct".equals(status)) {
				template.setStatus("打分");// 考核状态直接为打分 去除审核
			} else if ("update".equals(status)) {
				template.setStatus("添加明细");// 可修改该模板
			} else {
				return false;
			}
			Set<TemplateDetails> set = template.getTemplateDetails();// 模版明细
			Set<Template> templateSet = new HashSet<Template>();// template集合
			templateSet.add(template);
			Set<AssessPersonnel> apSet = template.getAssessPersonnel();// 成员
			// 为成员封装template集合,用于更新绑定关系
			for (AssessPersonnel assessPersonnel : apSet) {
				assessPersonnel.setTemplate(templateSet);
			}

			template.setTemplateDetails(null);
			Users user = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);
			template.setAsstMouth(Util.getDateTime("yyyy-MM月"));// 考核月份(当前月份)
			template.setCustomProple(user.getName());// 定制人
			template.setCustomUserId(user.getId());// 定制人id
			template.setCardId(user.getCardId());// 卡号
			template.setCustomDate(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));

			boolean bool = totalDao.save(template);
			if (bool) {
				// 添加数据
				for (TemplateDetails templateDetails : set) {
					TemplateDetails newTemplateDs = new TemplateDetails();
					BeanUtils.copyProperties(templateDetails, newTemplateDs,
							new String[] { "id", "template" });
					newTemplateDs.setTemplate(template);
					newTemplateDs.setOldOnLayer(templateDetails.getId() + "");
					bool = totalDao.save(newTemplateDs);
				}
				String hql = "from TemplateDetails ts where ts.template.id=?";
				String hql2 = "select id from TemplateDetails ts where ts.template.id=? and oldOnLayer=?";
				List<TemplateDetails> list = totalDao.query(hql, template
						.getId());
				for (TemplateDetails newTemplateDs : list) {
					if (!"root".equals(newTemplateDs.getOnLayer())) {
						Integer oldId = (Integer) totalDao
								.getObjectByCondition(hql2, template.getId(),
										newTemplateDs.getOnLayer());
						newTemplateDs.setOnLayer(oldId.toString());
						totalDao.update(newTemplateDs);
					}
				}
			}
			return bool;
		}
		return false;
	}

	@Override
	public boolean updateExamTemplate(Integer[] detailSelect, String tag) {
		boolean bool = false;
		if (null != detailSelect && detailSelect.length > 0) {
			Users user = Util.getLoginUser();
			for (int i = 0; i < detailSelect.length; i++) {// 遍历处理状态
				Template detail = (Template) totalDao.getObjectById(
						Template.class, detailSelect[i]);
				if ("ok".equals(tag)) {// 同意

					// 处理审批流程
					String audit = circuitRunServer.updateExeNodeByCirId(detail
							.getEpId(), true, "", false, null, false);
					if ("审批成功".equals(audit)) {
						CircuitRun circuitRun = circuitRunServer
								.findCircuitRunById(detail.getEpId());
						if ("同意".equals(circuitRun.getAllStatus())) {
							detail.setStatus("打分");
						}
					}
				} else {// 打回
					// 处理审批流程
					// 处理审批流程
					circuitRunServer.updateExeNodeByCirId(detail.getEpId(),
							false, "", false, null, false);
					detail.setStatus("打回");
					// detail.setDetailNextSign(user.getName());
					totalDao.update(detail);
				}
				bool = true;
			}
		}
		return bool;
	}

	@Override
	public Object[] findAllTemplate1(int parseInt, int pageSize) {
		// 返回条件 明细ID
		/**
		 * false:未审批 true：已审批
		 * 
		 */
		Map<String, Object> map = CircuitRunServerImpl.findAuditExeNode(
				Template.class, false);
		if (map != null) {
			String hql = "from Template where id in (:entityId)";
			List list = totalDao.find(hql, map, parseInt, pageSize);
			Object[] exam = new Object[2];
			Long countLong = totalDao.count("select count(*) " + hql, map);
			int count = Integer.parseInt(countLong.toString());
			exam[0] = count;
			exam[1] = list;
			return exam;
		}
		return null;
	}
}
