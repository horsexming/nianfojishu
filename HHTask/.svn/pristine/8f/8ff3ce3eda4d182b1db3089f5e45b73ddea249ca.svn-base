package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.AssScoreServer;
import com.task.Server.TemplateDetailsServer;
import com.task.entity.AssScore;
import com.task.entity.Careertrack;
import com.task.entity.ScoreDetails;
import com.task.entity.Template;
import com.task.entity.TemplateDetails;
import com.task.entity.Users;
import com.task.util.Util;

/**
 * 考核成绩服务层实现方法
 * 
 * @author 刘培
 * 
 */
public class AssScoreServerImpl implements AssScoreServer {

	private TotalDao totalDao;// Dao层
	private TemplateDetailsServer templateDetailsServer;// 模板层

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

	// 添加考核成绩
	public boolean addAssScore(AssScore assScore, Template template,
			int templateId[], Float score[], Float sumCustomScore) {
		if (assScore != null && template != null && templateId != null
				&& score != null) {
			Float sumScore = 0F;
			Set<TemplateDetails> temsSet = template.getTemplateDetails();// 模板明细
			Set<ScoreDetails> scoreDetailsSet = new HashSet<ScoreDetails>();// 成绩明细
			for (TemplateDetails tems : temsSet) {
				ScoreDetails scoreDetails = new ScoreDetails();
				scoreDetails.setTdsId(tems.getId());// 模板明细ID
				scoreDetails.setProject(tems.getProject());// 项目及内容
				scoreDetails.setCustomScore(tems.getCustomScore());// 定制分数
				scoreDetails.setOnLayer(tems.getOnLayer());// 上一层
				scoreDetails.setLayer(tems.getLayer());// 当前层
				scoreDetails.setIsSroce(tems.getIsSroce());// 是否打分项
				scoreDetails.setAssScore(assScore);// 所属成绩类
				for (int i = 0, len = templateId.length; i < len; i++) {
					int temId = templateId[i];
					int temsId = tems.getId();
					if (temId == temsId) {
						scoreDetails.setLastScore(score[i]);// 最终得分
						sumScore += score[i];
						break;
					}
				}
				scoreDetailsSet.add(scoreDetails);
			}
			if (sumScore.equals(sumCustomScore)) {// 如果得到满分则直接为0分
				//sumScore = 0F;
			}
			// // 查询该员工上月的打分信息
			// String hql = "from AssScore where asstMouth=? assType=?";
			// List scoreList = totalDao.query(hql, totalDao
			// .getLastMonth("yyyy-MM月"), "员工级");
			// if (scoreList != null && scoreList.size() > 0) {
			//				
			// }

			assScore.setAccScore(sumScore);// 总得分
			assScore.setPercentageScore(sumScore / sumCustomScore * 100);// 百分比成绩
			assScore.setScoreDetails(scoreDetailsSet);// 成绩明细
			assScore.setAsstMouth(totalDao.getLastMonth("yyyy-MM月"));// 考核月份(因为主管级的模版没有月份所以不能直接使用template.getMonth())
			assScore.setRateDate(totalDao.getDateTime("yyyy-MM-dd HH:mm:ss"));// 打分时间
			assScore.setTemplateId(template.getId());// 模板id
			assScore.setTemplateName(template.getName());// 模板名称
			// 更新职业轨迹的绩效时间
			Careertrack ck = (Careertrack) totalDao.getObjectByCondition(
					"from Careertrack where userId=?", assScore.getUserId());
			if (ck != null && !"转正".equals(template.getType())) {
				ck.setJixiaoTime(Util.getDateTime());
				totalDao.update(ck);
			}else if(ck != null && "转正".equals(template.getType())){
				ck.setZzkhTime(Util.getDateTime());
				totalDao.update(ck);
			}
			return totalDao.save(assScore);
		}
		return false;
	}

	// 查询是否已经打过分
	@SuppressWarnings("unchecked")
	public boolean findOldAssScore(AssScore assScore) {
		if (assScore != null) {
			String hql = "from AssScore where userId=? and asstMouth=? and addUserId=? and assType=?";
			List list = totalDao.query(hql, assScore.getUserId(), totalDao
					.getLastMonth("yyyy-MM月"), assScore.getAddUserId(), "员工级");
			if (list == null || list.size() <= 0) {
				return true;
			}
		}
		return false;

	}

	// 查询成绩(分页)
	@SuppressWarnings("unchecked")
	public List findAllAssScore(int pageNo, int pageSize, String status) {
		if (!status.isEmpty() && status.length() > 0) {
			String hql = "";
			if ("dept".equals(status)) {
				Users user = (Users) ActionContext.getContext().getSession()
						.get("Users");
				hql = "from AssScore  where addUserId=? and assType='员工级' order by rateDate desc";
				return totalDao.findAllByPage(hql, pageNo, pageSize, user
						.getId());
			} else if ("all".equals(status)) {
				hql = "from AssScore order by rateDate desc";
			}

			return totalDao.findAllByPage(hql, pageNo, pageSize);
		}
		return null;
	}

	// 获得数量 (所有成绩)
	public int getCountByAll(String status) {
		if (!status.isEmpty() && status.length() > 0) {
			String hql = "";
			if ("dept".equals(status)) {
				hql = "from AssScore where ";
				Users user = (Users) ActionContext.getContext().getSession()
						.get("Users");
				String[] deptArr = user.getDept().split("and");
				for (int i = 0; i < deptArr.length; i++) {
					if (i == 0) {
						hql += "dept='" + deptArr[i] + "'";
					} else {
						hql += " and dept='" + deptArr[i] + "'";
					}
				}
			} else if ("all".equals(status)) {
				hql = "from AssScore order by rateDate desc";
			}
			return totalDao.getCount(hql);
		}
		return 0;
	}

	// 条件查询成绩
	@SuppressWarnings("unchecked")
	public List findAssScoreByCondition(AssScore assScore, int pageNo,
			int pageSize) {
		if (assScore != null) {
			String other = "";
			String otherName = null;
			if (assScore.getAddUserId() != null) {
				other = "addUserId=" + assScore.getAddUserId();
				otherName = "addUserId";
			}
			if (assScore.getId() != null) {
				other += "id=" + assScore.getId();
				otherName = "id";
			}
			String hql = totalDao.criteriaQueries(assScore, other, "addUserId","id");
			hql += " order by rateDate  desc";
			return totalDao.findAllByPage(hql, pageNo, pageSize);
		}
		return null;
	}

	// 获得数量(条件查询成绩)
	public int getCountByCondition(AssScore assScore) {
		if (assScore != null) {
			String other = null;
			String otherName = null;
			if (assScore.getAddUserId() != null) {
				other = "addUserId=" + assScore.getAddUserId();
				otherName = "addUserId";
			}
			String hql = totalDao.criteriaQueries(assScore, other, otherName);
			return totalDao.getCount(hql);
		}
		return 0;
	}

	// 通过卡号生成用户成绩统计报表
	@SuppressWarnings("unchecked")
	public List findScoreViewByCarid(String cardId, String code) {
		if (cardId != null && cardId.length() > 0) {
			String hql = "from AssScore where cardId='" + cardId
					+ "' and code='" + code + "' order by rateDate desc";
			return totalDao.findAllByPage(hql, 1, 6);
		}
		return null;
	}

	// 生成成绩详细
	public String PreviewAssScore(AssScore assScore) {
		if (assScore != null) {

			Set<ScoreDetails> allSet = assScore.getScoreDetails();
			List<ScoreDetails> allSdsList = new ArrayList<ScoreDetails>();
			List<ScoreDetails> rootList = new ArrayList<ScoreDetails>();
			for (ScoreDetails scoreDetails : allSet) {
				allSdsList.add(scoreDetails);
				String onLayer = scoreDetails.getOnLayer();// 获得上一层
				if (!onLayer.isEmpty() && "root".equals(onLayer)) {
					rootList.add(scoreDetails);
				}
			}
			String str = "<table border='1' width='100%' class='table'><tr>"
					+ "<td colspan='10' align='center'>"
					+ assScore.getTemplateName()
					+ "</td></tr><tr><td align='center' colspan='2'>考核对象:("
					+ assScore.getAssType()
					+ ")&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 考核部门:("
					+ assScore.getDept()
					+ ")&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 考核月份:("
					+ assScore.getAsstMouth()
					+ ")</td></tr><tr><td colspan='2'>"
					+ "<div align='center' style='float: left;width: 50%'>项目及内容</div>"
					+ "<div align='right'style='float: left;width: 50%; padding-right: 20px'>"
					+ "成绩</div></td></tr>";
			ActionContext.getContext().getSession().remove("UL");
			for (int i = 0; i < rootList.size(); i++) {
				ScoreDetails scoreDetails = (ScoreDetails) rootList.get(i);
				allSdsList.remove(scoreDetails);
				str += "<tr><td align='center' width='10%'>"
						+ scoreDetails.getProject() + "<br>("
						+ scoreDetails.getCustomScore() + "分)</td><td>";
				String temp = findList2(allSdsList, scoreDetails);
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
		return "";
	}

	// 预览功能查询方法
	@SuppressWarnings("unchecked")
	private String findList(List<ScoreDetails> list, ScoreDetails scoreDetails) {
		List list1 = new ArrayList();
		String onLayer = String.valueOf(scoreDetails.getTdsId());
		// Map map = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			ScoreDetails tems = (ScoreDetails) list.get(i);
			String listOnLayer = tems.getOnLayer();// 明细上层
			if (!onLayer.isEmpty() && onLayer.equals(listOnLayer)) {
				list1.add(tems);
				list.remove(tems);
				i--;
			}
		}
		if (list1 != null && list1.size() > 0) {
			for (int j = 0; j < list1.size(); j++) {
				ScoreDetails sds = (ScoreDetails) list1.get(j);
				String sessionUl = (String) ActionContext.getContext()
						.getSession().get("UL");
				if (sessionUl == null) {
					sessionUl = "";
				}
				if (sessionUl.indexOf("ul") > 0) {
					if (j == 0) {
						sessionUl += "<ul class='userCenter'><li>"
								+ sds.getProject() + "(" + sds.getCustomScore()
								+ "分)<hr>";
					} else {
						sessionUl += "<li>" + sds.getProject() + "("
								+ sds.getCustomScore() + "分)<hr>";
					}
				} else {
					sessionUl += "<ul class='userCenter' style='margin: 0px; padding: 0px'><li>"
							+ sds.getProject()
							+ "("
							+ sds.getCustomScore()
							+ "分)<hr>";
				}

				ActionContext.getContext().getSession().put("UL", sessionUl);
				findList(list, sds);// 回调

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
			String sessionUl = (String) ActionContext.getContext().getSession()
					.get("UL");
			if (sessionUl == null) {
				sessionUl = "";
			}
			if (sessionUl.indexOf("ul") > 0) {
				sessionUl = sessionUl.substring(0, sessionUl
						.lastIndexOf("<li>"));
				sessionUl += "<li><div align='left' style='float: left;'>"
						+ scoreDetails.getProject()
						+ "("
						+ scoreDetails.getCustomScore()
						+ "分)</div><div align='right' style='float:right;'><font color='red'>"
						+ scoreDetails.getLastScore()
						+ "分</font> &nbsp;&nbsp;&nbsp;</div><hr style='clear: both;'>";
			} else {
				sessionUl += "<ul class='userCenter' style='margin: 0px; padding: 0px'><li><div align='right'><font color='red'>"
						+ scoreDetails.getLastScore()
						+ "分</font>&nbsp;&nbsp;&nbsp;</div><hr></li></ul>";
			}
			ActionContext.getContext().getSession().put("UL", sessionUl);
		}
		return (String) ActionContext.getContext().getSession().get("UL");
	}

	// 预览功能查询方法2
	@SuppressWarnings("unchecked")
	private String findList2(List<ScoreDetails> list, ScoreDetails scoreDetails) {
		List list1 = new ArrayList();
		String onLayer = String.valueOf(scoreDetails.getTdsId());
		// Map map = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			ScoreDetails tems = (ScoreDetails) list.get(i);
			String listOnLayer = tems.getOnLayer();// 明细上层
			if (!onLayer.isEmpty() && onLayer.equals(listOnLayer)) {
				list1.add(tems);
				list.remove(tems);
				i--;
			}
		}

		if (scoreDetails.getIsSroce() != null
				&& "yes".equals(scoreDetails.getIsSroce())) {
			String sessionUl = (String) ActionContext.getContext().getSession()
					.get("UL");
			if (sessionUl == null) {
				sessionUl = "";
			}
			sessionUl = sessionUl.substring(0, sessionUl.lastIndexOf("<hr>"));
			String select = "<select name='score' onkeydown='window.history.forward(1);if(event.keyCode == 8){this.options[0].text =\"\";}' "
					+ "onkeypress=writeSelect(this) onselect=chackScore('"
					+ scoreDetails.getCustomScore()
					+ "',this) onkeyup=chackScore('"
					+ scoreDetails.getCustomScore()
					+ "',this) ><option value='0'></option>";
			int customScore = (int) ((float) scoreDetails.getCustomScore());
			for (int j = 0; j < list1.size(); j++) {
				ScoreDetails tems = (ScoreDetails) list1.get(j);
				select += "<option value='" + tems.getCustomScore() + "'>"
						+ tems.getProject() + "(" + tems.getCustomScore()
						+ "分)</option>";
			}
			select += "</select>(<font color='red'>得分:"
					+ scoreDetails.getLastScore() + "</font>)";
			if (sessionUl.indexOf("ul") > 0) {
				sessionUl += "<div align='right' style='float:right;'>"
						+ select
						+ "<input size='5' type='hidden' name='templateId' value='"
						+ scoreDetails.getId()
						+ "'></div><hr style='clear: both;'>";
			} else {
				sessionUl += "<ul class='userCenter' style='margin: 0px; padding: 0px'><li><div align='right'>"
						+ select
						+ "<input size='5' type='hidden' name='templateId' value='"
						+ scoreDetails.getId() + "'></div><hr></li></ul>";
			}
			ActionContext.getContext().getSession().put("UL", sessionUl);
		} else {
			for (int j = 0; j < list1.size(); j++) {
				ScoreDetails sds = (ScoreDetails) list1.get(j);
				String sessionUl = (String) ActionContext.getContext()
						.getSession().get("UL");
				if (sessionUl == null) {
					sessionUl = "";
				}
				if (sessionUl.indexOf("ul") > 0) {
					if (j == 0) {
						sessionUl += "<ul class='userCenter'><li>"
								+ sds.getProject() + "(" + sds.getCustomScore()
								+ "分)<hr>";
					} else {
						sessionUl += "<li>" + sds.getProject() + "("
								+ sds.getCustomScore() + "分)<hr>";
					}
				} else {
					sessionUl += "<ul class='userCenter' style='margin: 0px; padding: 0px'><li>"
							+ sds.getProject()
							+ "("
							+ sds.getCustomScore()
							+ "分)<hr>";
				}

				ActionContext.getContext().getSession().put("UL", sessionUl);
				findList2(list, sds);// 回调

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

	// 根据id查询
	public AssScore findAssScoreById(int id) {
		if ((Object) id != null && id > 0) {
			return (AssScore) totalDao.getObjectById(AssScore.class, id);
		}
		return null;
	}

	// 删除分数以及分数详细
	public boolean delScore(AssScore assScore) {
		if (assScore != null) {
			return totalDao.delete(assScore);
		}
		return false;
	}

	/**
	 * 查询打分人员的部门部留是否存在
	 * 
	 * @return
	 */
	public boolean findContractBonus() {
		Users user = (Users) ActionContext.getContext().getSession().get(
				totalDao.users);
		String hql = "from ContractBonus where userId=? and status='bumen' and bonusMouth =''";
		List list = totalDao.query(hql, user.getId());
		if (list != null && list.size() > 0) {
			hql = "from ContractBonus where userId=? and status='bumen' and bonusStatus='部门部留' and bonusMouth =?";
			list = totalDao.query(hql, user.getId(), totalDao
					.getLastMonth("yyyy-MM月"));
			if (list != null && list.size() > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	@Override
	public AssScore findOldAssScore1(AssScore assScore) {
		if (assScore != null) {
			String hql = "from AssScore where userId=? and asstMouth=? and addUserId=? and assType=?";
			assScore = (AssScore) totalDao.getObjectByCondition(hql, assScore.getUserId(), totalDao
					.getLastMonth("yyyy-MM月"), assScore.getAddUserId(), "员工级");
			return assScore;
		}
		return null;
	}

}
