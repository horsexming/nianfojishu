package com.task.ServerImpl.zgkh;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.zgkh.AssessPersonnelServer;
import com.task.entity.zgkh.AssessPersonnel;
import com.task.entity.Template;
import com.task.entity.Users;
import com.task.entity.UsersGroup;
import com.task.util.Util;

/***
 * 主管级考核_考核人员Server层实现类
 * 
 * @author 刘培
 * 
 */
public class AssessPersonnelServerImpl implements AssessPersonnelServer {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 添加考核人员
	@Override
	public boolean addAssessPersonnel(AssessPersonnel assessPersonnel,
			String groupName, String status) {
		if (assessPersonnel != null) {
			Users user = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);
			assessPersonnel.setAddUserId(user.getId());
			assessPersonnel.setAddUserCode(user.getCode());
			assessPersonnel.setAddTime(Util.getDateTime("yyyy-MM-dd hh:ss:mm"));
			UsersGroup up = this.findUpByName(groupName, status);
			// 如果up为null创建默认分组
			if (up == null) {
				if (groupName != null && groupName.length() > 0) {
					up = new UsersGroup();
					up.setUserId(user.getId());// 添加人id
					up.setCode(user.getCode());
					up.setGroupName(groupName);
					up.setAddDateTime(Util.getDateTime(null));// 添加时间
					totalDao.save(up);
				} else {
					up = this.findUpByName("默认", status);// 查询默认分组是否存在
					if (up == null) {
						up = new UsersGroup();
						up.setUserId(user.getId());// 添加人id
						up.setCode(user.getCode());
						up.setGroupName("默认");
						up.setAddDateTime(Util.getDateTime(null));// 添加时间
						totalDao.save(up);
					}
				}
			}
			if (status != null && status.length() > 0) {
				up.setStatus(status);
			}
			assessPersonnel.setUsersGroup(up);
			return totalDao.save(assessPersonnel);
		}
		return false;
		// // //////////处理成员分组、成员、模版关系
		// String hql2 =
		// "from AssScore where asstMouth='2013-03月' and assType='员工级'";
		// List<AssScore> scoreList = totalDao.query(hql2);
		// for (AssScore assScore : scoreList) {
		// String hql3 = "from Users where id=?";
		// Users assUser = (Users) totalDao.getObject(hql3, assScore
		// .getAddUserId());// 查询人员是否存在
		// if (assUser != null) {
		// String hql4 = "from UsersGroup where groupName=? and userId=?";
		// UsersGroup up = (UsersGroup) totalDao.getObject(hql4, "默认",
		// assScore.getAddUserId());// 查询默认分组是否存在
		// if (up == null) {
		// up = new UsersGroup();
		// up.setUserId(assUser.getId());// 添加人id
		// up.setCode(assUser.getCode());
		// up.setGroupName("默认");
		// up.setAddDateTime(totalDao.getDateTime(null));// 添加时间
		// totalDao.save(up);
		// }
		// Set<AssessPersonnel> apSet = up.getAssessPersonnel();
		// Users assUser2 = (Users) totalDao.getObject(hql3, assScore
		// .getUserId());// 查询人员是否存在
		// if (assUser2 != null) {
		// AssessPersonnel ap = new AssessPersonnel();
		// ap.setUserId(assUser2.getId());
		// ap.setCode(assUser2.getCode());
		// ap.setCardId(assUser2.getCardId());
		// ap.setUserName(assUser2.getName());
		// ap.setDept(assUser2.getDept());
		// ap.setAddUserId(assUser.getId());
		// ap.setAddUserCode(assUser.getCode());
		// ap.setAddTime(totalDao.getDateTime(null));
		// ap.setUsersGroup(up);
		// apSet.add(ap);
		// totalDao.update(up);
		// }
		//
		// }
		// }

		// ///////////////////////////处理模版关系
		// String sql = "select * from ta_hr_usersTemplate";
		// List<Object[]> list = totalDao.createQuerySelect(null, sql);
		// String hql = "from Template where id=?";
		// String hql2 = "from AssessPersonnel where userId=?";
		// for (Object[] object : list) {
		// AssessPersonnel ap = (AssessPersonnel) totalDao.getObject(hql2,
		// object[0]);
		// Template template = (Template) totalDao.getObject(hql, object[1]);
		// if (ap != null && template != null) {
		// Set<Template> templateSet = new HashSet<Template>();
		// Set<AssessPersonnel> apSet = template.getAssessPersonnel();
		// apSet.add(ap);
		// templateSet.add(template);
		//				
		// ap.setTemplate(templateSet);
		// // delUserTemplate(template.getId());
		// template.setAssessPersonnel(apSet);
		// totalDao.update(template);
		// }
		// }

		// // ///////////////////////处理张经理的固定组成员
		// UsersGroup usersGroup = (UsersGroup) totalDao.getObjectById(
		// UsersGroup.class, 18);
		// if (usersGroup != null) {
		// String hql = "from Teammembers where addUserId=219";
		// List<Teammembers> list = totalDao.query(hql);
		// for (Teammembers teammembers : list) {
		// String hql2 = "from Users where code=?";
		// Users user = (Users) totalDao.getObjectByCondition(hql2,
		// teammembers.getTeammembersmembernumber());
		// if (user != null) {
		// AssessPersonnel ap = new AssessPersonnel();
		// ap.setUserId(user.getId());
		// ap.setCode(user.getCode());
		// ap.setCardId(user.getCardId());
		// ap.setUserName(user.getName());
		// ap.setDept(user.getDept());
		// ap.setAddUserId(772);
		// ap.setAddUserCode("009");
		// ap.setAddTime(totalDao.getDateTime(null));
		// ap.setUsersGroup(usersGroup);
		// totalDao.save(ap);
		// }
		// }
		//
		// }

	}

	// 通过userId查询该人员是否存在
	@Override
	public AssessPersonnel findAPByUserId(int userId) {
		if ((Object) userId != null && userId > 0) {
			String hql = "from AssessPersonnel where userId=?";
			return (AssessPersonnel) totalDao.getObjectByCondition(hql, userId);
		}
		return null;
	}

	// 通过userId查询该人员是否存在
	@Override
	public AssessPersonnel findAPByUserId(int userId, String groupName,
			String status) {
		if (userId > 0) {
			if (groupName == null || groupName.length() < 1) {
				groupName = "默认";
			}
			UsersGroup up = this.findUpByName(groupName, status);
			if (up != null) {
				Users user = (Users) ActionContext.getContext().getSession()
						.get(TotalDao.users);
				String hql = "from AssessPersonnel ap where ap.userId=? and ap.addUserId=? and ap.usersGroup.id=?";
				return (AssessPersonnel) totalDao.getObjectByCondition(hql,
						userId, user.getId(), up.getId());
			}
		}
		return null;
	}

	// 通过Id查询该人员
	@Override
	public AssessPersonnel findAPById(int id) {
		if ((Object) id != null && id > 0) {
			return (AssessPersonnel) totalDao.getObjectById(
					AssessPersonnel.class, id);
		}
		return null;
	}

	// 通过Id查询该人员以及模版
	@Override
	public Template findAPAndTemplateById(int id) {
		if ((Object) id != null && id > 0) {
			String hql = "from Template where id in (select t.id from Template t join t.assessPersonnel ap  where ap.id =?)";
			return (Template) totalDao.getObjectByCondition(hql, id);
		}
		return null;
	}

	// 通过Id查询该人员以及模版
	@Override
	public Template findAPAndTemplateById(int id, String month) {
		if (month == null || month.length() <= 0) {
			month = Util.getLastMonth("yyyy-MM月");
		}
		if ((Object) id != null && id > 0) {
			String hql = "from Template where id in (select t.id from Template t join t.assessPersonnel ap  where ap.id =? ) and asstMouth=?";
			return (Template) totalDao.getObjectByCondition(hql, id, month);
		}
		return null;
	}

	// 查询所有考核人员信息(分页)
	@SuppressWarnings("unchecked")
	@Override
	public Object[] findAllAssessPersonnel(int pageNo, int pageSize) {
		String hql = "from AssessPersonnel ap where ap.addUserId=? and usersGroup.id in (select id from UsersGroup where status is null) order by ap.addTime";
		Users user = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);
		List list = totalDao.findAllByPage(hql, pageNo, pageSize, user.getId());
		int count = totalDao.getCount(hql, user.getId());
		Object[] o = { list, count };
		return o;

	}

	/***
	 * 查询审核组成员
	 */
	@SuppressWarnings("unchecked")
	public Object[] findshAssessPersonnel(AssessPersonnel assessPersonnel,
			int pageNo, int pageSize, String pageStatus, String groupName) {
		if (assessPersonnel == null) {
			assessPersonnel = new AssessPersonnel();
		}
		String sql1 = "";
		if (groupName != null && !"".equals(groupName)) {
			sql1 = "usersGroup.groupName like '%" + groupName + "%' and";
		} else {
			sql1 = "";
		}
		String sql2 = "usersGroup.id in (select id from UsersGroup where status= ?)";
		String sql = sql1 + " " + sql2;
		String hql = totalDao.criteriaQueries(assessPersonnel, sql, null);
		List list = totalDao.findAllByPage(hql, pageNo, pageSize, pageStatus);
		int count = totalDao.getCount(hql, pageStatus);
		System.out.println(hql);
		Object[] o = { list, count };
		return o;
	}

	/***
	 * 查询审核组
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List findUsersGroup(String pageStatus) {
		String hql = " from UsersGroup where status=?";
		return totalDao.query(hql, pageStatus);
	}

	/***
	 * 查询审核组成员
	 */
	@SuppressWarnings("unchecked")
	public List findshAssessPersonnel(Integer groupId, String pageStatus) {
		String hql = "from AssessPersonnel a where  a.usersGroup.id in (select id from UsersGroup where status='"
				+ pageStatus
				+ "' and a.userId not in (select id from Users where id = a.userId and onWork = '离职'))";
		if (groupId != null && groupId > 0) {
			hql = "from AssessPersonnel a where a.userId not in (select id from Users where id = a.userId and onWork = '离职') and a.usersGroup.id="
					+ groupId;
		}
		return totalDao.query(hql);
	}

	// 删除考核人员
	@Override
	public boolean delAssessPersonnel(AssessPersonnel assessPersonnel) {
		if (assessPersonnel != null) {
			return totalDao.delete(assessPersonnel);
		}
		return false;
	}

	// 查询没有绑定过考核模版的所有人员
	@SuppressWarnings("unchecked")
	@Override
	public List<AssessPersonnel> findAssessPersonnelForbb(Template template,
			Integer userGroupId) {
		if (template != null) {
			Users user = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);
			if ("主管级".equals(template.getAssObject())) {
				String hql = "from AssessPersonnel ap where ap.addUserId=?  and ap.id not in "
						+ "(select distinct a.id from AssessPersonnel a join a.template t1  where t1.id in "
						+ "(select u2.id from a.template u2 where u2.assObject='主管级' and u2.customUserId=?))";
				if (userGroupId != null && userGroupId > 0) {
					hql += " and ap.usersGroup.id=?";
					return totalDao.query(hql, user.getId(), user.getId(),
							userGroupId);
				}
				return totalDao.query(hql, user.getId(), user.getId());
			} else if ("员工级".equals(template.getAssObject())) {
				String hql = "from AssessPersonnel ap where ap.addUserId=? and ap.id not in "
						+ "(select distinct a.id from AssessPersonnel a join a.template t1  where t1.id in "
						+ "(select t2.id from a.template t2 where t2.asstMouth=? and t2.customUserId=? and t2.assObject='员工级'))";
				if (userGroupId != null && userGroupId > 0) {
					hql += " and ap.usersGroup.id=?";
					return totalDao.query(hql, user.getId(), template
							.getAsstMouth(), user.getId(), userGroupId);
				}
				return totalDao.query(hql, user.getId(), template
						.getAsstMouth(), user.getId());
			} else {
				return null;
			}
		}
		return null;
	}

	// 查询未打分的考核人员
	@SuppressWarnings("unchecked")
	@Override
	public List<AssessPersonnel> findAssessPersonnelForScore(String status) {
		Users user = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);
		if (status != null && status.length() > 0 && "员工级".equals(status)) {
			String lastMonth = Util.getLastMonth("yyyy-MM月");
			String hql = "from AssessPersonnel ap where "
					// + "ap.userId <> ? "// 排除自己
					+ " ap.userId not in (select userId from AssScore where asstMouth =? and addUserId = ? and assType='员工级')  "// 登录人没有打过分
					+ "and ap.addUserId = ? "// 登录人的成员
					+ "and ap.userId not in (select id from Users where onWork in ('离职')) "// 离职人员不在显示
					+ "and ap.id in (select distinct a.id from AssessPersonnel a join a.template t1  where t1.id in "
					+ "(select t2.id from a.template t2 where t2.asstMouth=? and t2.customUserId=? and t2.assObject='员工级' and t2.status in ('通过','打分','结束')))";// 绑定过模版的成员
			return totalDao.query(hql, lastMonth, user.getId(), user.getId(),
					lastMonth, user.getId());
		} else {
			String lastMonth = Util.getLastMonth("yyyy-MM月");
			String hql = "from AssessPersonnel ap where ap.userId <> ? and"
					+ " ap.userId not in (select userId from AssScore where asstMouth =? and addUserId = ? and assType='主管级')"
					+ " and ap.addUserId in (select addUserId from AssessPersonnel where userId=?)"
					+ " and ap.id in (select distinct a.id from AssessPersonnel a join a.template t1  where t1.id in "
					+ " (select t2.id from a.template t2 where t2.assObject='主管级'))";
			return totalDao.query(hql, user.getId(), lastMonth, user.getId(),
					user.getId());
		}
	}

	/***
	 * 清空绑定关系
	 */
	public int delUserTemplate(int templateId) {
		String sql = "delete from ta_sys_usersTemplate where templateid=?";
		return totalDao.createQueryUpdate(null, sql, templateId);
	}

	/**
	 * 查询登录人的所有成员分组
	 * 
	 * @return List<UsersGroup>
	 */
	@SuppressWarnings("unchecked")
	public List<UsersGroup> findUsersGroupByUid() {
		Users user = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);
		String hql = "from UsersGroup where userId=? and status is null";
		return totalDao.query(hql, user.getId());
	}

	/**
	 * 通过id查询成员分组
	 * 
	 * @param id
	 * @return UsersGroup
	 */
	public UsersGroup findUsersGroupById(int id) {
		String hql = "from UsersGroup where id=?";
		return (UsersGroup) totalDao.getObjectByCondition(hql, id);
	}

	/***
	 * 通过分组名称查询个人所属分组(无类别状态)
	 * 
	 * @param groupName
	 * @return
	 */
	// public UsersGroup findUpByName(String groupName) {
	// if (groupName != null && groupName.length() > 0) {
	// Users user = (Users) ActionContext.getContext().getSession().get(
	// TotalDao.users);
	// String hql = "from UsersGroup where groupName=? and userId=?";
	// return (UsersGroup) totalDao.getObjectByCondition(hql, groupName,
	// user.getId());
	// }
	// return null;
	// }

	/***
	 * 通过分组名称查询个人所属分组(是否属于审核组状态)
	 * 
	 * @param groupName
	 * @return
	 */
	@Override
	public UsersGroup findUpByName(String groupName, String status) {
		if (groupName == null || groupName.length() < 1) {
			groupName = "默认";
		}
		String hql = "from UsersGroup where groupName=?";
		if (status != null && status.length() > 0) {
			hql += " and status = '" + status + "'";
		} else {
			hql += " and status is null ";
		}
		return (UsersGroup) totalDao.getObjectByCondition(hql, groupName);
	}

	/***
	 * 删除成员分组
	 * 
	 * @param usersGroup
	 *            分组对象
	 * @return boolean
	 */
	public boolean delUsersGroup(UsersGroup usersGroup) {
		if (usersGroup != null) {
			return totalDao.delete(usersGroup);
		}
		return false;
	}

}
