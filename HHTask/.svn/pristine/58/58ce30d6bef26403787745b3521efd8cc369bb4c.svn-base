package com.task.ServerImpl;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.BonusServer;
import com.task.Server.WageServer;
import com.task.Server.sys.CircuitRunServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Bonus;
import com.task.entity.Bonusmoney;
import com.task.entity.OaMessageAlerm;
import com.task.entity.Teammembers;
import com.task.entity.Users;
import com.task.entity.project.ProjectManage;
import com.task.entity.system.CircuitRun;
import com.task.util.Util;

public class BonusServerImpl implements BonusServer {

	private TotalDao totalDao;
	private CircuitRunServer circuitRunServer;
	private WageServer wageServer;// 工资模板Server层

	public CircuitRunServer getCircuitRunServer() {
		return circuitRunServer;
	}

	public void setCircuitRunServer(CircuitRunServer circuitRunServer) {
		this.circuitRunServer = circuitRunServer;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 根据登录人的部门查询出所对应的班组成员
	public List finDept(String dept) {
		if (dept != null) {
			String hql = "from Teammembers where teammembersteam='" + dept
					+ "'";
			return this.totalDao.query(hql);
		}
		return null;
	}

	// 根据登录人的id查询出所对应的班组成员
	public List finDept(Integer addUSerId) {
		if (addUSerId != null && addUSerId > 0) {
			String hql = "from Teammembers where addUserId='" + addUSerId + "'";
			return this.totalDao.query(hql);
		}
		return null;
	}

	/***
	 * 根据登录人的工号查询出其所属成员并且该成员未添加过工资
	 */
	@SuppressWarnings("unchecked")
	public List finDept(int addUserId, String status) {
		if ((Object) addUserId != null && status != null) {
			Users user = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);
			String hql = "";
			if ("chengbao".equals(status)) {// 承包奖金分配
				hql = "from AssessPersonnel ap where ap.addUserId=? and ap.usersGroup.status is null and ap.code not in "
						+ "(select code from Wage where mouth=? and code in "
						+ "(select code from AssessPersonnel where addUserId=?))";
				if ((Object) addUserId != null && addUserId > 0) {
					hql += " and ap.usersGroup.id=?";
					return totalDao.query(hql, user.getId(), totalDao
							.getLastMonth("yyyy-MM月"), user.getId(), addUserId);
				}
			} else if ("manage".equals(status)) {// 长病假
				hql = "from WageStandard where standardStatus ='默认' and code in "
						+ "(select code from AssessPersonnel ap where ap.addUserId=? and ap.usersGroup.status is null and ap.code not in "
						+ "(select code from Wage where mouth=? and code in (select code from AssessPersonnel where addUserId=?))";
				if ((Object) addUserId != null && addUserId > 0) {
					hql += " and ap.usersGroup.id=?)";
					return totalDao.query(hql, user.getId(), totalDao
							.getLastMonth("yyyy-MM月"), user.getId(), addUserId);
				}
				hql += ")";
			} else {
				return null;
			}
			return this.totalDao.query(hql, user.getId(), totalDao
					.getLastMonth("yyyy-MM月"), user.getId());
		}
		return null;
	}

	// 根据奖金分配的时间查询出提奖的总金额
	public List finddate(String date) {
		if (date != null) {
			String hql = "select mentionshallMoney from Mentionrecord where mentionMonth='"
					+ date + "' and mentionstatus in ('可提奖','已提奖')";
			return this.totalDao.query(hql);
		}
		return null;
	}

	// 添加奖金分配明细表
	public boolean saveBonus(Bonus bonus) {
		if (bonus != null) {
			return this.totalDao.save(bonus);
		}
		return false;
	}

	// 添加奖金总金额表
	public boolean saveBonusmoney(Bonusmoney bonusmoney) {
		if (bonusmoney != null) {
			boolean bool = false;
			if (bonusmoney.getId() == null) {
				bool = this.totalDao.save(bonusmoney);
			} else {
				try {
					String processName = "批产奖金自动分配审批";
					Integer epId = CircuitRunServerImpl.createProcess(
							processName, Bonusmoney.class, bonusmoney.getId(),
							"bonusmoneystatus", "id", bonusmoney
									.getBonusmoneymonth()
									+ "的批产奖金自动分配明细请您审批!", true, "总经理同意");
					if (epId != null && epId > 0) {
						bonusmoney.setEpId(epId);
						bool = totalDao.update(bonusmoney);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return bool;
		}
		return false;
	}

	// 根据时间查询出所对应的奖金总额表的总金额
	public List findbonusmoney(String date) {
		if (date != null) {
			String hql = "select sum(bonusmoneytotalmoney) from Bonusmoney where bonusmoneymonth='"
					+ date + "'";
			return this.totalDao.query(hql);
		}
		return null;
	}

	// 根据时间和登入人的部门查询出奖金总额表中是否存在
	public List finddatabonusmoney(String date, String dept) {
		if (date != null) {
			String hql = "from Bonusmoney where bonusmoneymonth='" + date
					+ "' and bonusmoneyteam='" + dept
					+ "'  and bonusmoneystatus <>'打回'";
			return this.totalDao.query(hql);
		}
		return null;
	}

	// 根据ID查询出 所对的成员信息
	public Teammembers findByID(int id) {
		if (id > 0) {
			return (Teammembers) this.totalDao.getObjectById(Teammembers.class,
					id);
		}
		return null;
	}

	// 根据月份和登入人的职位查询出奖金分配详细信息
	public List findzhiwei(String yuefen, String dept) {
		if (yuefen != null && dept != null) {
			String hql = "from Bonus where bonusdata='" + yuefen
					+ "' and bonusteam='" + dept + "'";
			return this.totalDao.query(hql);
		}
		return null;
	}

	// 根据月份显示 和班组显示相应的奖金分配信息
	public List findDateDept(String date, String dept) {
		if (date != null && dept != null) {
			String hql = "from Bonus where bonusdata='" + date
					+ "' and bonusteam='" + dept + "'";
			return this.totalDao.query(hql);
		}
		return null;
	}

	// 根据工号 月份查询出奖金明细
	public List fidnghdate(String gonghao, String yuefen, String dept) {
		if (gonghao != null && yuefen != null) {
			String hql = "from Bonus where bonusmembernumber='" + gonghao
					+ "' and bonusdata='" + yuefen + "' and bonusteam='" + dept
					+ "'";
			return this.totalDao.query(hql);
		}
		return null;
	}

	// 修改 奖金明细表
	public boolean updateBonus(Bonus bonus) {
		if (bonus != null) {
			return this.totalDao.update(bonus);
		}
		return false;
	}

	// 根据月份和班组查询出奖金明细表部留
	public List findDpetbuliuMoney(String date, String banzu) {
		if (date != null && banzu != null) {
			String hql = "from Bonus where bonusdata='" + date
					+ "' and bonusteam='" + banzu + "'and bonusteamname='部留'";
			return this.totalDao.query(hql);
		}
		return null;
	}

	// 根据月份和班组查询出奖金总额表信息
	public List findBonusmoney(String date, String banzu) {
		if (date != null && banzu != null) {
			String hql = "from Bonusmoney where bonusmoneymonth='" + date
					+ "' and bonusmoneyteam='" + banzu + "'";
			return this.totalDao.query(hql);
		}
		return null;
	}

	// 修改 奖金分配总额表
	public boolean updateBonusmoney(Bonusmoney bonusmoney) {
		if (bonusmoney != null) {
			return this.totalDao.update(bonusmoney);
		}
		return false;
	}

	// 根据月份查询出奖金分配的汇总
	public List findSummary(String date, int pageNo, int pageSize) {
		if (date != null && date.length() > 0) {
			String hql = "select  bonusmembernumber ,bonusteamname,sum(bonusmembermoney+bonusovertimemealmoney) from Bonus where bonusdata=? group by bonusmembernumber,bonusteamname";
			return this.totalDao.findAllByPage(hql, pageNo, pageSize, date);
		}
		return null;
	}

	// 根据月份查询出奖金分配的汇总总数
	public int summaryCount(String date) {
		if (date != null) {
			String hql = "select  bonusmembernumber ,bonusteamname,sum(bonusmembermoney+bonusovertimemealmoney) from Bonus where bonusdata='"
					+ date + "'  group by bonusmembernumber,bonusteamname";
			return this.totalDao.query(hql).size();
		}
		return 0;
	}

	// 跟据月份查询出奖金分配汇总信息 用于导出EXCEL
	public List findexcelsummary(String date) {
		if (date != null && date.length() > 0) {
			String hql = "select  bonusmembernumber ,bonusteamname,sum(bonusmembermoney+bonusovertimemealmoney),bonusdata from Bonus where bonusdata='"
					+ date
					+ "'  group by bonusmembernumber,bonusteamname,bonusdata";
			return this.totalDao.query(hql);
		}
		return null;
	}

	// 根据月份和班组 状态为审核中的
	public List findStatus(String date, String dept) {
		if (date != null && date.length() > 0 && dept != null
				&& dept.length() > 0) {
			String hql = "from Bonusmoney where bonusmoneymonth='"
					+ date
					+ "' and bonusmoneyteam='"
					+ dept
					+ "' and bonusmoneystatus in ('加工经理同意','生产副总同意','总经理同意','财务同意')";
			return this.totalDao.query(hql);
		}
		return null;
	}

	// 根据月份查询出奖金总额表状态为 总经理同意或者财务同意
	public List findzongjiaji(String date) {
		if (date != null && date.length() > 0) {
			String hql = "from Bonusmoney where bonusmoneymonth='" + date
					+ "' and bonusmoneystatus in ('总经理同意','财务同意')";
			return this.totalDao.query(hql);
		}
		return null;
	}

	// 根据月份查询奖金总额表
	public List findDate(String date) {
		if (date != null && date.length() > 0) {
			String hql = "from Bonusmoney where  bonusmoneymonth=?";
			return this.totalDao.query(hql, date);
		}
		return null;
	}

	@Override
	public Object[] findExamList(int parseInt, int pageSize) {
		// 返回条件 明细ID
		/**
		 * false:为审批 true：已审批
		 * 
		 */
		Map<String, Object> map = CircuitRunServerImpl.findAuditExeNode(
				Bonusmoney.class, false);
		if (map != null) {
			String hql = "from Bonusmoney where id in (:entityId)";
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

	@Override
	public boolean updateExamBonus(Integer[] detailSelect, String tag) {
		boolean bool = false;
		if (null != detailSelect && detailSelect.length > 0) {
			Users user = Util.getLoginUser();
			for (int i = 0; i < detailSelect.length; i++) {// 遍历处理状态
				Bonusmoney detail = (Bonusmoney) totalDao.getObjectById(
						Bonusmoney.class, detailSelect[i]);
				if ("ok".equals(tag)) {// 同意
					// 处理审批流程
					String audit = circuitRunServer.updateExeNodeByCirId(detail
							.getEpId(), true, "", false, null, false);
					if ("审批成功".equals(audit)) {
						CircuitRun circuitRun = circuitRunServer
								.findCircuitRunById(detail.getEpId());
						if (circuitRun.getAllStatus().equals("同意")) {
							detail.setBonusmoneystatus("可提奖");
						}
					}
					wageServer.wageBalance(detail.getAddUserId());
				} else {// 打回
					circuitRunServer.updateExeNodeByCirId(detail.getEpId(),
							false, "", false, null, false);
					detail.setBonusmoneystatus("打回");
					totalDao.update(detail);
				}
				bool = true;
			}
		}
		return bool;
	}

	public WageServer getWageServer() {
		return wageServer;
	}

	public void setWageServer(WageServer wageServer) {
		this.wageServer = wageServer;
	}

	/*
	 * 
	 * 查询奖金总额(non-Javadoc)
	 * 
	 * @see com.task.Server.BonusServer#findbonusmoney1(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Bonusmoney findbonusmoney1(String dateyuefen, String duty) {
		String hql = "from Bonusmoney where bonusmoneymonth='" + dateyuefen
				+ "' and bonusmoneyteam='" + duty + "'";
		return (Bonusmoney) this.totalDao.getObjectByCondition(hql);
	}

	@Override
	public void updateBus(Integer bonusId) {
		// TODO Auto-generated method stub
		Bonusmoney bonusmoney = (Bonusmoney) this.totalDao.getObjectById(
				Bonusmoney.class, bonusId);
		if ("打回".equals(bonusmoney.getBonusmoneystatus())) {
			CircuitRunServerImpl.updateCircuitRun(bonusmoney.getEpId());
			bonusmoney.setBonusmoneystatus("审批中");
		}
		this.totalDao.update(bonusmoney);
	}

}
