package com.task.ServerImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.Server.BonusmoneyServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Bonus;
import com.task.entity.Bonusmoney;
import com.task.entity.Integral;
import com.task.entity.Integralsource;
import com.task.entity.Teammembers;
import com.task.entity.Users;
import com.task.entity.Wage;
import com.task.entity.WageStandard;
import com.task.entity.fin.UserMonthMoney;
import com.task.util.Util;

public class BonusmoneyServerImpl implements BonusmoneyServer {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 根据登入人的部门查询出奖金总额表所有信息
	public List finddept(String dept, int pageNo, int pageSize) {
		if (dept != null) {
			String hql = "from Bonusmoney where bonusmoneyteam='" + dept + "'";
			return this.totalDao.findAllByPage(hql, pageNo, pageSize);
		}
		return null;
	}

	/****
	 * 批产奖金申请转入奖金分配
	 */
	@Override
	public boolean lpToFp(Integer[] ids) {
		boolean bool = false;
		if (ids != null) {
			// 添加奖金分配总额表
			Bonusmoney bonusmoney = new Bonusmoney();
			bonusmoney.setBonusmoneyteam("sys");// 班组
			bonusmoney.setBonusmoneystatus("审核中");// 状态
			bonusmoney.setBonusmoneydatatime(Util.getDateTime());// 时间
			bonusmoney.setBonusmoneyname("system");// 添加的用户名
			bonusmoney.setBonusmoneytotalmoney(0F);// 总金额
			bonusmoney.setAddUserId(14);// 添加人id
			bonusmoney.setBonusmoneymonth("2016-08");// 月份
			for (Integer id : ids) {
				UserMonthMoney umm = (UserMonthMoney) totalDao.getObjectById(
						UserMonthMoney.class, id);
				if (umm != null) {
					Bonus newbonus = new Bonus();
					newbonus.setBonusdata("2016-08"); // 月份
					newbonus.setBonusmembernumber(umm.getCode());// 成员工号
					newbonus.setBonusovertimemealmoney(0F);// 加班费及饭贴
					newbonus.setBonusmembermoney(umm.getMoney()); // 成员奖金
					newbonus.setBonusteamname(umm.getUsername());// 成员姓名
					newbonus.setBonusteam("sys");// 班组
					newbonus.setBonusdatatime(Util.getDateTime());// 时间锥
					bool = totalDao.save(newbonus);

					bonusmoney.setBonusmoneytotalmoney(umm.getMoney()
							+ bonusmoney.getBonusmoneytotalmoney());// 总金额
				}
			}
			if (bool) {
				bool = totalDao.save(bonusmoney);
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
		}
		return bool;
	}

	@Override
	public boolean lpToWage(Integer[] ids) {
		boolean bool = false;
		if (ids != null) {
			for (Integer id : ids) {
				UserMonthMoney umm = (UserMonthMoney) totalDao.getObjectById(
						UserMonthMoney.class, id);
				if (umm != null) {
					String message = "";
					String successMessage = "";
					String hql5 = "from Wage where code=?  and mouth=?";
					String hql6 = "from Users where code=?";
					// 查询该成员信息所对应的User
					Users teamUser = (Users) totalDao.getObjectByCondition(
							hql6, umm.getCode());
					if (teamUser == null) {
						message += umm.getUsername() + "不存在,无法处理工资<br/>";
						continue;
					}
					if (teamUser.getOnWork() == null
							|| teamUser.getOnWork().length() <= 0
							|| teamUser.getOnWork().equals("离职")
							|| teamUser.getOnWork().equals("离职中")) {
						message += teamUser.getName() + teamUser.getOnWork()
								+ ",无法处理工资<br/>";
						continue;
					}
					// 查询员工默认的工资模板
					String hql8 = "from WageStandard where code=? and standardStatus='默认' and processStatus='同意'";
					List list = totalDao.query(hql8, teamUser.getCode());
					WageStandard ws = null;
					if (list != null && list.size() > 0) {
						ws = (WageStandard) list.get(0);
					}
					if (ws != null) {
						Float jiangjin = umm.getMoney();// 奖金
						Float jiabanfei = 0f;// 加班费
						// 根据工号查询该员工上个月的工资是否存在
						Wage wage = new Wage();
						Wage oldWage = (Wage) totalDao.getObjectByCondition(
								hql5, teamUser.getCode(), umm.getMonth());
						Float yingfaWage = ws.getYingfagongzi() + 0F + jiangjin
								+ jiabanfei;// 应发工资((岗位工资+保密津贴+电话补贴+技能工资+特殊补贴)+绩效考核+奖金+加班费)
						if (oldWage != null) {
							if (oldWage.getWageClass().equals("离职工资")) {
								wage.setJiangjin(jiangjin);// 奖金
								bool = totalDao.update(wage);
								continue;
							}
							wage = oldWage;
						}
						wage.setCode(ws.getCode());// 工号
						wage.setCardId(ws.getCardId());// 卡号
						wage.setUserName(ws.getUserName());// 员工名称
						wage.setDept(teamUser.getDept());// 所属部门
						wage.setMouth(umm.getMonth());// 发放月份
						wage.setGangweigongzi(ws.getGangweigongzi());// 岗位工资(固)
						wage.setBaomijintie(ws.getBaomijintie());// 保密津贴(固)
						wage.setDianhuabutie(ws.getDianhuabutie());// 电话补贴(固)
						wage.setTongchoujin(-ws.getTongchoujin());// 养老保险(固)
						wage.setYiliaobaoxian(-ws.getYiliaobaoxian());// 医疗保险(固)
						wage.setShiyebaoxian(-ws.getShiyebaoxian());// 失业保险(固)
						wage.setGongjijin(-ws.getGongjijin());// 公积金(固)
						wage.setJinenggongzi(ws.getJinenggongzi());// 技能工资
						wage.setGonglinggongzi(ws.getGonglinggongzi());// 特殊补贴
						wage.setYingfagongzi(yingfaWage);// 应发工资
						wage.setWageStatus("添加变动");// 工资状态
						wage
								.setJixiaokaohegongzi(wage
										.getJixiaokaohegongzi() == null ? 0F
										: wage.getJixiaokaohegongzi());// 绩效考核(如果绩效工资为空则设为0，不为空则不覆盖)
						wage.setJiangjin(jiangjin);// 奖金(如果奖金为空则为当前奖金,否则则为原有奖金加当前奖金)
						wage.setJiabanfei(jiabanfei);// 加班费(同奖金一样)
						wage.setFangzufei(ws.getFangzufei());// 房租费
						wage.setIsBucha(ws.getBucha());// 是否补差

						// 为别的项设置为0
						wage.setOther(0F);// 其他
						wage.setBingshikangdeng(0F);// 病事旷等
						wage.setWucanfei(0F);// 午餐费
						wage.setShuidianfei(0F);// 水电费
						wage.setBfgongzi(0F);// 补发工资
						wage.setYingjiaoshuijin(0F);// 应交税金
						wage.setShifagongzi(0F);// 实发工资
						wage.setAddDateTime(Util
								.getDateTime("yyyy-MM-dd HH:mm:ss"));// 添加时间

						wage.setAddUserName("sys");// 添加人员名称
						wage.setAddUserId(0);// 添加人id
						wage.setWageClass("奖金分配");// 工资类别
						wage.setWageStatue("正常");// 人员状态
						wage.setUserId(teamUser.getId());// 工资所属人id

						if (oldWage == null) {// 不存在工资则新添加
							bool = totalDao.save(wage);
						} else {
							bool = totalDao.update(wage);
						}

						if (bool == false) {
							message += "处理员工" + teamUser.getName()
									+ "的工资信息时出错了!,请核查!<br/>";
						} else {
							umm.setSqstatus("已发放");
							successMessage += "员工: " + teamUser.getName()
									+ " 的工资信息处理成功!<br/>";

							String hql_1 = "from Integral where integrcode=? ";
							Integral integral = (Integral) totalDao
									.getObjectByCondition(hql_1, teamUser
											.getCode());
							if (integral == null) {
								integral = new Integral();
							}
							integral.setIntegralName(teamUser.getName());// 积分所属人姓名
							integral.setIntegrcode(teamUser.getCode());// 积分所属人工号
							integral.setIntegrdept(teamUser.getDept());// 积分所属人部门
							integral.setUserId(teamUser.getId());
							Integralsource is = new Integralsource();
							Set<Integralsource> isSet = new HashSet<Integralsource>();
							is.setAddintegral(wage.getJiangjin().intValue());// 添加的积分
							is.setAddtime(Util.getDateTime());// 添加时间
							is.setLaiyuan("奖金分配");
							isSet.add(is);
							integral.setIs(isSet);
							IntegralServerDaoImpl.addIntegral1(integral);
						}
					} else {
						message += "不存在员工" + teamUser.getName()
								+ "的工资模板信息,请添加!<br/>";
					}
				}
			}
		}
		return bool;
	}

	// 根据登入人的部门查询出奖金总额的所有信息总数
	public Integer getcount(String dept) {
		if (dept != null) {
			String hql = "from Bonusmoney where bonusmoneyteam='" + dept + "'";
			return this.totalDao.getCount(hql);
		}
		return 0;
	}

	// 查询出所有的奖金额表的总数
	public List findAll(int pageNo, int pageSize) {
		String hql = "from Bonusmoney   order by bonusmoneydatatime desc";
		return this.totalDao.findAllByPage(hql, pageNo, pageSize);
	}

	// 查询所有的奖金总额表信息
	public Integer bonusmoneyCount() {
		String hql = "from Bonusmoney";
		return this.totalDao.getCount(hql);
	}

	// 根据月份和部门查询出奖金明细表信息
	public List findBonusAll(String data, String dept, int pageNo, int pageSize) {
		if (data != null) {
			String hql = "from Bonus where bonusdata='" + data
					+ "' and bonusteam='" + dept + "' ";
			return this.totalDao.findAllByPage(hql, pageNo, pageSize);
		}
		return null;
	}

	// 根据月份查询出奖金明细表的总数
	public Integer countBonus(String data, String dept) {
		if (data != null) {
			String hql = "from Bonus where bonusdata='" + data
					+ "' and bonusteam='" + dept + "'";
			return this.totalDao.getCount(hql);
		}
		return null;
	}

	// 条件查询
	public List conditionFindAll(Bonusmoney bonusmoney, int pageNo, int pageSize) {
		if (bonusmoney != null) {
			String tjmone = String
					.valueOf(bonusmoney.getBonusmoneytotalmoney());
			String other = "";
			if (tjmone != null && !"null".equals(tjmone)) {
				other = " bonusmoneytotalmoney like '%"
						+ tjmone.substring(0, tjmone.indexOf(".")) + "%'";
				String[] name = { "bonusmoneytotalmoney" };
				String hql = this.totalDao.criteriaQueries(bonusmoney, other,
						name);
				return totalDao.findAllByPage(hql, pageNo, pageSize);
			} else {
				String hql = this.totalDao.criteriaQueries(bonusmoney, null,
						null);
				return this.totalDao.findAllByPage(hql, pageNo, pageSize);
			}
		}
		return null;
	}

	// 条件查询总数
	public Integer countbonusmoney(Bonusmoney bonusmoney) {
		if (bonusmoney != null) {
			String hql = this.totalDao.criteriaQueries(bonusmoney, null, null);
			return this.totalDao.getCount(hql);
		}
		return null;
	}

	// 根据ID查询出所对应的信息
	public Bonusmoney findByID(int id) {
		if (id > 0) {
			return (Bonusmoney) this.totalDao.getObjectById(Bonusmoney.class,
					id);
		}
		return null;
	}

	// 修改奖金总额表信息
	public boolean update(Bonusmoney bonusmoney) {
		if (bonusmoney != null) {
			return this.totalDao.update(bonusmoney);
		}
		return false;
	}

	// 根据月份和班组查询出奖金分配明细 打印预览
	public List print(String yuefen, String dept) {
		if (yuefen != null && dept != null) {
			String hql = "from Bonus where bonusdata='" + yuefen
					+ "' and bonusteam='" + dept + "'";
			return this.totalDao.query(hql);
		}
		return null;
	}

	// 修改奖金明细表
	public boolean updatebonus(Bonus bonus) {
		if (bonus != null) {
			return this.totalDao.update(bonus);
		}
		return false;
	}

	// 根据成员卡号和工号和月份查询出奖金明细表
	public List findghkh(String gongnumber, String kanumber, String yuefen,
			String dept) {
		if (gongnumber != null && kanumber != null) {
			String hql = "from Bonus where bonusmembernumber='" + gongnumber
					+ "' and bonuscardnumber='" + kanumber
					+ "' and bonusdata='" + yuefen + "' and bonusteam='" + dept
					+ "' ";
			return this.totalDao.query(hql);
		}
		return null;
	}

	// 根据月份查询出奖金总额
	public List findmoney(String yuefen, String banzu) {
		if (yuefen != null) {
			String hql = "select sum(bonusmoneytotalmoney) from Bonusmoney where bonusmoneymonth='"
					+ yuefen + "' and bonusmoneyteam <> '" + banzu + "'";
			return this.totalDao.query(hql);
		}
		return null;
	}

	// 根据月份查询出提奖记录表总额
	public List findtijiang(String date) {
		if (date != null) {
			String hql = "select mentionshallMoney from Mentionrecord where mentionMonth='"
					+ date + "' and mentionstatus in ('可提奖','已提奖')";
			return this.totalDao.query(hql);
		}
		return null;
	}

	// 根据月份和班组查询出奖金总额表信息
	public List finddatedept(String date, String banzu) {
		if (date != null && banzu != null) {
			String hql = "from Bonusmoney where bonusmoneymonth='" + date
					+ "' and bonusmoneyteam='" + banzu + "' ";
			return this.totalDao.query(hql);
		}
		return null;
	}

	// 根据月份和班组查询出奖金明细表部留
	public List findddeptlu(String date, String banzu) {
		if (date != null && banzu != null) {
			String hql = "from Bonus where bonusdata='" + date
					+ "' and bonusteam='" + banzu + "' and bonusteamname='部留'";
			return this.totalDao.query(hql);
		}
		return null;
	}

	// 根据月份查询出奖金分配总额
	public List findDate(String date, String date2) {
		if (date != null) {
			String hql = "select bonusmoneyteam,sum(bonusmoneytotalmoney) from Bonusmoney where bonusmoneymonth  between '"
					+ date + "' and '" + date2 + "' group by bonusmoneyteam";
			return this.totalDao.query(hql);
		}
		return null;
	}

	// 查询出班组
	public List findDept() {
		String hql = "select distinct bonusmoneyteam from Bonusmoney";
		return this.totalDao.query(hql);
	}

	// 根据班组查询出所对的信息 走势图
	public List findTeam(String dept) {
		if (dept != null) {
			String hql = "select bonusmoneymonth,bonusmoneytotalmoney from Bonusmoney where bonusmoneyteam='"
					+ dept + "'";
			return this.totalDao.findAllByPage(hql, 1, 12);
		}
		return null;
	}

	// 根据月份查询出奖金分配总额
	public List findDateMoney(String date) {
		if (date != null) {
			String hql = "select  sum(bonusmoneytotalmoney) from Bonusmoney where bonusmoneymonth='"
					+ date + "'";
			return this.totalDao.query(hql);
		}
		return null;
	}

	// 查询出班组 用于显示班组环比走势图
	public List findDepthbi() {
		String hql = "select distinct bonusmoneyteam from Bonusmoney";
		return this.totalDao.query(hql);
	}

	// 根据月份和班组查询出 奖金分配总金额
	public List findDateMoney(String date, String dept) {
		if (date != null && dept != null) {
			String hql = "select  sum(bonusmoneytotalmoney) from Bonusmoney where bonusmoneymonth='"
					+ date + "' and bonusmoneyteam='" + dept + "'";
			return this.totalDao.findAllByPage(hql, 1, 12);
		}
		return null;
	}

	// 根据月份查询出奖金总额表 状态为 加工经理同意
	public List findMoney(String date) {
		if (date != null && date.length() > 0) {
			String hql = "from Bonusmoney where bonusmoneystatus='加工经理同意' and  bonusmoneymonth=?";
			return this.totalDao.query(hql, date);
		}
		return null;
	}

	// 根据月份查询出奖金总额表状态为 生产副总同意
	public List findDate(String date) {
		if (date != null && date.length() > 0) {
			String hql = "from Bonusmoney where bonusmoneystatus='生产副总同意' and  bonusmoneymonth=?";
			return this.totalDao.query(hql, date);
		}
		return null;
	}

}
