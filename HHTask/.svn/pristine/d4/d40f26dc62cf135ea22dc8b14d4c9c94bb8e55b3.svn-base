package com.task.ServerImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.util.FileCopyUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.WageStandardServer;
import com.task.entity.Contract;
import com.task.entity.InsuranceGold;
import com.task.entity.Price;
import com.task.entity.Users;
import com.task.entity.Wage;
import com.task.entity.WageStandard;
import com.task.entity.sop.ProcardTemplate;
import com.task.util.Util;
import com.tast.entity.zhaobiao.ZhUser;

/**
 * 工资模板Server层实现类
 * 
 * @author 刘培
 * 
 */
public class WageStandardServerImpl implements WageStandardServer {

	private TotalDao totalDao;// DAO层

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 根据工号和卡号查找该用户当前使用的工资信息
	@SuppressWarnings("unchecked")
	public WageStandard findWSByUser(String code) {
		String hql = "from WageStandard where code=? and standardStatus='默认'";
		List list = totalDao.query(hql, code);
		WageStandard ws = null;
		if (list != null && list.size() > 0) {
			ws = (WageStandard) list.get(0);
		}
		return ws;
	}

	// 添加工资模板
	@SuppressWarnings("static-access")
	public boolean addWageStandard(WageStandard wageStandard,
			WageStandard oldWageStandard, InsuranceGold insuranceGold) {
		if (wageStandard != null) {
			Float tongchoujin = wageStandard.getTongchoujin();// 养老保险
			Float yiliaobaoxian = wageStandard.getYiliaobaoxian();// 医疗保险
			Float shiyebaoxian = wageStandard.getShiyebaoxian();// 失业保险
			Float ssBase = wageStandard.getSsBase();// 社保基数
			Float gjjBase = wageStandard.getGjjBase();// 公积金基数
			if (gjjBase != null) {
				wageStandard.setGongjijin(gjjBase
						* insuranceGold.getHousingFund() / 100);// 住房公积金
			}
			if (tongchoujin == 1.0) {
				wageStandard.setTongchoujin(ssBase
						* insuranceGold.getOldageInsurance() / 100);// 养老保险
			}
			if (yiliaobaoxian == 1.0) {
				wageStandard.setYiliaobaoxian(ssBase
						* insuranceGold.getMedicalInsurance() / 100);// 医疗保险
			}
			if (shiyebaoxian == 1.0) {
				wageStandard.setShiyebaoxian(ssBase
						* insuranceGold.getUnemploymentInsurance() / 100);// 失业保险
			}

			if (wageStandard.getFangzufei() > 0) {
				wageStandard.setFangzufei(-wageStandard.getFangzufei());
			}

			Float yingfagongzi = wageStandard.getGangweigongzi()// 应发工资(岗位工资+保密津贴+电话补贴+技能工资+工龄工资)
					+ wageStandard.getBaomijintie()
					+ wageStandard.getDianhuabutie()
					+ wageStandard.getJinenggongzi()
					+ wageStandard.getGonglinggongzi();
			Users user = (Users) ActionContext.getContext().getSession().get(
					totalDao.users);

			wageStandard.setInputPeople(user.getName());// 录入人
			wageStandard.setInputDate(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));// 录入时间
			wageStandard.setYingfagongzi(yingfagongzi);// 应发工资
			// if (oldWageStandard != null) {
			// oldWageStandard.setUpdatePeople(user.getName());// 修改人
			// boolean bool = totalDao.update(oldWageStandard);// 修改
			// if (bool) {
			// wageStandard.setInputDate(totalDao
			// .getDateTime("yyyy-MM-dd HH:mm:ss"));// 录入时间
			// wageStandard.setProcessStatus("审核");
			// return totalDao.save(wageStandard);
			// }
			// }
			return totalDao.save(wageStandard);

		}
		return false;
	}

	/***
	 * 查询待处理工资模板(审核/不同意状态)
	 * 
	 * @param code
	 *            工号(填写工号则会根据工号查询)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findAuditWs(String code) {
		String hql = "from WageStandard where processStatus in ('审核','不同意')  and code not in (select code from Users where onwork='离职')";
		if (code != null && code.length() > 0) {
			hql += " and code='" + code + "'";
		}
		return totalDao.query(hql);
	}

	/***
	 * 查询需要调整模版的人员
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findNeedUpdateWs(String chageStatus) {
		String hql = "from WageStandard where code not in (select code from Users where onwork='离职') and code in (select code from Contract where  contractStatus='wage' and isUse ='待调整')";
		if (chageStatus != null && "gztz".equals(chageStatus)) {
			hql += " and processStatus='调整中' and chageStatus='工资调整'";
		} else if (chageStatus != null && "jstz".equals(chageStatus)) {
			hql += " and standardStatus in ('默认','调整中') ";
		}
		return totalDao.find(hql);
	}

	/***
	 * 查询需要添加模版的人员
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List findNeedAddWs(String chageStatus) {
		String hql = "from WageStandard where chageStatus = '新增调整'";
		return totalDao.find(hql);
	}

	// 查询所有工资模板(分页)
	@SuppressWarnings("unchecked")
	public List findAllWs(int pageNo, int pageSize) {
		String hql = "from WageStandard where standardStatus='默认' and code not in (select code from Users where onwork='离职') order by code";
		return totalDao.findAllByPage(hql, pageNo, pageSize);
	}

	// 获得数量 (所有)
	public int getCountByAll() {
		String hql = "from WageStandard where standardStatus='默认' and code not in (select code from Users where onwork='离职') ";
		return totalDao.getCount(hql);
	}

	// 条件查询
	@SuppressWarnings("unchecked")
	public Object[] findWSByCondition(WageStandard ws, int pageNo, int pageSize) {
		if (ws != null && (Object) pageNo != null && (Object) pageSize != null) {
			String hql = totalDao.criteriaQueries(ws, null);
			List list = totalDao.findAllByPage(hql, pageNo, pageSize);
			int count = totalDao.getCount(hql);
			Object[] o = { list, count };
			return o;
		}
		return null;
	}

	// 删除工资模板
	public boolean delWs(WageStandard wageStandard) {
		if (wageStandard != null) {
			if (wageStandard.getProcessStatus().equals("同意")) {
				String sql = "delete from ta_fin_wagestandard where ta_fin_code=? ";
				int count = totalDao.createQueryUpdate(null, sql, wageStandard
						.getCode());
				if (count > 0) {
					return true;
				}
			} else {
				boolean bool = totalDao.delete(wageStandard);
				if (bool) {
					// 更新薪资协议为"待调整"
					String sql = "update ta_hr_Contract set ta_contract_isuse='待调整' where ta_contract_isuse='调整中' and ta_contract_code=?";
					totalDao.createQueryUpdate(null, sql, wageStandard
							.getCode());
				}
				return bool;
			}
		}
		return false;
	}

	// 根据id查询工资模板
	public WageStandard findWsById(int id) {
		if ((Object) id != null) {
			return (WageStandard) totalDao
					.getObjectById(WageStandard.class, id);
		}
		return null;
	}

	// 修改工资模板
	@SuppressWarnings("static-access")
	public boolean updateWs(WageStandard wageStandard,
			WageStandard oldWageStandard) {
		if (wageStandard != null && oldWageStandard != null) {
			if (wageStandard.getFangzufei() > 0) {
				wageStandard.setFangzufei(wageStandard.getFangzufei());
			}
			Users user = (Users) ActionContext.getContext().getSession().get(
					totalDao.users);
			// 如果审核状态==同意,则该模版为调整工资
			if ("调整中".equals(oldWageStandard.getProcessStatus())) {
				// 更新工资模版为"历史"状态
				oldWageStandard.setUpdatePeople(user.getName());// 修改人
				oldWageStandard.setUpdateDate(Util.getDateTime(null));// 修改时间
				oldWageStandard.setStandardStatus("默认");// 模板状态
				boolean bool = totalDao.update(oldWageStandard);
				// 添加一条新的工资模版
				if (bool) {
					Float yingfagongzi = wageStandard.getGangweigongzi()// 应发工资
							+ wageStandard.getBaomijintie()
							+ wageStandard.getDianhuabutie()
							+ wageStandard.getJinenggongzi()
							+ wageStandard.getGonglinggongzi();
					wageStandard.setYingfagongzi(yingfagongzi);// 应发工资
					wageStandard.setInputDate(Util.getDateTime(null));// 添加时间
					wageStandard.setInputPeople(user.getName());// 添加人
					wageStandard.setStandardStatus(null);// 模板状态
					wageStandard.setProcessStatus("审核");// 审核状态
					bool = totalDao.save(wageStandard);
					if (bool) {
						// 更新薪资协议为"调整中"
						String sql = "update ta_hr_Contract set ta_contract_isuse='完成' where ta_contract_isuse='待调整' and ta_contract_code=?";
						int count = totalDao.createQueryUpdate(null, sql,
								wageStandard.getCode());
						// 改为总经理直接修改，so。。无需审批
						// 将老模版更改为历史
						oldWageStandard.setStandardStatus("历史");
						oldWageStandard.setProcessStatus("同意");
						oldWageStandard.setChageStatus("完成");

						// 新模版变为默认
						wageStandard.setChageStatus("完成");
						wageStandard.setStandardStatus("默认");
						wageStandard.setInsuranceGoldId(oldWageStandard
								.getInsuranceGoldId());// 缴纳比例id顺延
						wageStandard.setProcessStatus("同意");
						bool = totalDao.update(wageStandard);
						if (bool) {
							// 将最初的默认模版调整为历史
							String hql = "from WageStandard where code=? and standardStatus='默认' and id <>?";
							WageStandard tooOldWage = (WageStandard) totalDao
									.getObjectByCondition(hql, oldWageStandard
											.getCode(), wageStandard.getId());
							if (tooOldWage != null) {
								tooOldWage.setUpdatePeople(user.getName());// 修改人
								tooOldWage
										.setUpdateDate(Util.getDateTime(null));// 修改时间
								tooOldWage.setStandardStatus("历史");// 模板状态
								bool = totalDao.update(tooOldWage);
							}
						}

						// // 如果不存在薪资协议则为总经理直接修改
						// if (count == 0) {
						// // 将老模版更改为历史
						// oldWageStandard.setStandardStatus("历史");
						//
						// // 新模版变为默认
						// wageStandard.setStandardStatus("默认");
						// wageStandard.setProcessStatus("同意");
						// bool = totalDao.update(wageStandard);
						// } else {
						// // 发送系统提醒消息
						// AlertMessagesServerImpl.addAlertMessages("工资模版审核 ",
						// "员工:" + wageStandard.getUserName()
						// + " 的工资模版已经修改，请您审核! ", "1");
						// }

					}
				}
				return bool;

			} else {
				Float yingfagongzi = wageStandard.getGangweigongzi()// 应发工资
						+ wageStandard.getBaomijintie()
						+ wageStandard.getDianhuabutie();
				wageStandard.setId(oldWageStandard.getId());// id
				wageStandard.setInputPeople(user.getName());// 录入人
				wageStandard.setInputDate(Util
						.getDateTime("yyyy-MM-dd HH:mm:ss"));// 录入时间
				wageStandard.setYingfagongzi(yingfagongzi);// 应发工资
				wageStandard.setStandardStatus(null);// 模板状态
				wageStandard.setProcessStatus("审核");// 审核状态

				/****** 直接调整，不在审核 ********/
				// 更新其他模版为历史
				String sql = "update ta_fin_wagestandard set ta_fin_standardstatus='历史' where ta_fin_standardstatus='默认' and ta_fin_code=?";
				int count = totalDao.createQueryUpdate(null, sql, wageStandard
						.getCode());
				// 如果修改的数量大于0,则是调整工资。否则为初次添加工资
				if (count > 0) {
					// 更新薪资协议为"完成"
					String sql2 = "update ta_hr_Contract set ta_contract_isuse='完成' where ta_contract_isuse='调整中' and ta_contract_code=?";
					totalDao.createQueryUpdate(null, sql2, wageStandard
							.getCode());
				}
				wageStandard.setStandardStatus("默认");
				wageStandard.setProcessStatus("同意");

				/********** 调整后审核 ********/
				// 发送系统提醒消息
				// AlertMessagesServerImpl.addAlertMessages("工资模版审核 ", "员工:"
				// + wageStandard.getUserName() + " 的工资模版调整(直接调整)，请您审核! ",
				// "1");
				return totalDao.update(wageStandard);
			}

		}
		return false;
	}

	// 修改基数工资模板
	@SuppressWarnings("static-access")
	@Override
	public boolean updateJsWs(WageStandard wageStandard, String pageStatus) {
		if (wageStandard != null && wageStandard.getId() != null) {
			WageStandard oldWageStandard = (WageStandard) totalDao
					.getObjectById(WageStandard.class, wageStandard.getId());
			WageStandard newWageStandard = new WageStandard();
			BeanUtils.copyProperties(oldWageStandard, newWageStandard);
			newWageStandard.setIsOnWork(wageStandard.getIsOnWork());
			// 房租费
			if (wageStandard.getFangzufei() >= 0) {
				newWageStandard.setFangzufei(-wageStandard.getFangzufei());
			} else {
				newWageStandard.setFangzufei(wageStandard.getFangzufei());
			}
			// 是否补差
			newWageStandard.setBucha(wageStandard.getBucha());
			// 公积金基数
			newWageStandard.setGjjBase(wageStandard.getGjjBase());
			// 社保基数
			newWageStandard.setSsBase(wageStandard.getSsBase());
			// 根据基数和缴纳比例计算个人所需缴纳的保险金额
			InsuranceGold isg = (InsuranceGold) totalDao.getObjectById(
					InsuranceGold.class, wageStandard.getInsuranceGoldId());
			if (isg != null) {
				newWageStandard.setInsuranceGoldId(isg.getId());// 设置保险缴纳比例

				Float tongchoujin = Float.parseFloat(Math.ceil(isg
						.getOldageInsurance()
						* newWageStandard.getSsBase() / 100 * 10)
						+ "") / 10F;// 养老保险(固)
				Float yiliaobaoxian = Float.parseFloat(Math.ceil(isg
						.getMedicalInsurance()
						* newWageStandard.getSsBase() / 100 * 10)
						+ "") / 10F;// 医疗保险(固)
				Float shiyebaoxian = Float.parseFloat(Math.ceil(isg
						.getUnemploymentInsurance()
						* newWageStandard.getSsBase() / 100 * 10)
						+ "") / 10F;// 失业保险(固)

				Float gongjijin = Float.parseFloat(String.format("%.0f", isg
						.getHousingFund()
						* newWageStandard.getGjjBase() / 100));
				// 公积金(固)(取整，四舍五入)

				newWageStandard.setTongchoujin(tongchoujin);
				newWageStandard.setYiliaobaoxian(yiliaobaoxian);
				newWageStandard.setShiyebaoxian(shiyebaoxian);
				newWageStandard.setGongjijin(gongjijin);
			}

			Users user = (Users) ActionContext.getContext().getSession().get(
					totalDao.users);
			newWageStandard.setInputPeople(user.getName());// 录入人
			newWageStandard.setInputDate(Util
					.getDateTime("yyyy-MM-dd HH:mm:ss"));// 录入时间
			newWageStandard.setStandardStatus("默认");// 模板状态
			if ("no".equals(pageStatus)) {
				// 不需要调整岗位等信息
				newWageStandard.setProcessStatus("同意");// 审核状态
				newWageStandard.setChageStatus("完成");
			} else {
				if (!"调整中".equals(newWageStandard.getProcessStatus())) {
					// 发送系统提醒消息
					AlertMessagesServerImpl.addAlertMessages("薪资调整（总经理） ",
							"员工:" + newWageStandard.getUserName()
									+ " 的工资模版请您前往调整! ", "1");
				}
				newWageStandard.setProcessStatus("调整中");// 审核状态
				newWageStandard.setChageStatus("工资调整");
			}
			boolean bool = false;
			if ("新增调整".equals(oldWageStandard.getChageStatus())
					|| "调整中".equals(oldWageStandard.getProcessStatus())) {
				BeanUtils.copyProperties(newWageStandard, oldWageStandard);
				bool = totalDao.update(oldWageStandard);
			} else {
				bool = totalDao.save(newWageStandard);
			}
			if (bool && "no".equals(pageStatus)) {
				// 更新工资模版为"历史"状态
				oldWageStandard.setUpdatePeople(user.getName());// 修改人
				oldWageStandard.setUpdateDate(Util.getDateTime(null));// 修改时间
				oldWageStandard.setStandardStatus("历史");// 模板状态
				bool = totalDao.update(oldWageStandard);
				// 更新薪资协议为"调整中"
				String sql = "update ta_hr_Contract set ta_contract_isuse='完成' where ta_contract_isuse='待调整' and ta_contract_code=?";
				int count = totalDao.createQueryUpdate(null, sql,
						oldWageStandard.getCode());
			}
			return bool;
		}
		return false;
	}

	// 通过工号和卡号查询合同编号
	public Contract findContractByCode(String code) {
		if (code != null) {
			String hql = "from Contract where code=? and contractStatus='contract'";
			return (Contract) totalDao.getObjectByCondition(hql, code);
		}
		return null;
	}

	// 通过工号和卡号查询员工工资模版信息
	@SuppressWarnings("unchecked")
	public List findWageXieYiByCode(String code, String standardStatus) {
		if (code != null && standardStatus != null) {
			String hql = "from WageStandard where code=?";
			if (standardStatus.equals("协议")) {
				hql += " and standardStatus='协议'";
			} else if ("历史".equals(standardStatus)) {
				hql += " and standardStatus <> '协议' and processStatus='同意'";
			}
			return totalDao.query(hql, code);
		}
		return null;

	}

	// 根据工资模板状态(默认/历史)和流程状态(审核/同意/不同意)查询工资标准信息(所有)
	@SuppressWarnings("unchecked")
	public Object[] findWsFroAudit(String standardStatus, String processStatus,
			int pageNo, int pageSize) {
		if (standardStatus != null && processStatus != null
				&& standardStatus.length() > 0 && processStatus.length() > 0) {
			String hql = "from WageStandard where processStatus=?";
			List list = totalDao.findAllByPage(hql, pageNo, pageSize,
					processStatus);
			int count = totalDao.getCount(hql, processStatus);
			Object[] o = { list, count };
			return o;
		}
		return null;
	}

	// 审核工资模版
	public boolean auditWageStand(Integer wsId[], String auditStatus) {
		boolean bool = false;
		if (wsId != null && wsId.length > 0 && auditStatus != null
				&& auditStatus.length() > 0) {
			if ("ok".equals(auditStatus)) {
				auditStatus = "同意";
			} else {
				auditStatus = "不同意";
			}
			for (int i = 0; i < wsId.length; i++) {
				WageStandard wageStandard = (WageStandard) totalDao
						.getObjectById(WageStandard.class, wsId[i]);
				if (wageStandard != null) {
					if ("同意".equals(auditStatus)) {
						// 更新其他模版为历史
						String sql = "update ta_fin_wagestandard set ta_fin_standardstatus='历史' where ta_fin_standardstatus='默认' and ta_fin_code=?";
						int count = totalDao.createQueryUpdate(null, sql,
								wageStandard.getCode());
						// 如果修改的数量大于0,则是调整工资。否则为初次添加工资
						if (count > 0) {
							// 更新薪资协议为"调整中"
							String sql2 = "update ta_hr_Contract set ta_contract_isuse='完成' where ta_contract_isuse='调整中' and ta_contract_code=?";
							totalDao.createQueryUpdate(null, sql2, wageStandard
									.getCode());
						}
						wageStandard.setStandardStatus("默认");
					}
					wageStandard.setProcessStatus(auditStatus);
					bool = totalDao.update(wageStandard);
				}
			}
		}
		return bool;
	}

	/***
	 * 更新工资模版
	 * 
	 * @param ws
	 *            工资模版对象
	 * @return boolean
	 */
	public boolean updateWS(WageStandard ws) {
		if (ws != null) {
			return totalDao.update(ws);
		}
		return false;
	}

	/***
	 * 查询离职工资是否存在
	 * 
	 * @return
	 */
	@Override
	public Wage findLeaveWage(String code) {
		String hql = "from Wage where code=? and wageClass='离职工资'";
		return (Wage) totalDao.getObjectByCondition(hql, code);
	}

	/***
	 * 查询上个月工资是否存在
	 * 
	 * @return
	 */
	@Override
	public Wage findLastWage(String code) {
		String hql = "from Wage where code=? and mouth=?";
		return (Wage) totalDao.getObjectByCondition(hql, code, Util
				.getLastMonth("yyyy-MM月"));
	}

	/***
	 * 查询所有默认的在职人员的工资模版
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List findAllWageStandard() {
		// 查询所有在职的员工工资
		String hql = "from WageStandard where standardStatus='默认' and code in (select code from Users where onWork in ('在职','内退') and internal='是') order by gongjijin";
		List<WageStandard> list = totalDao.query(hql);
		for (WageStandard wageStandard : list) {
			// 计算年平均工资
			String nowDate = Util.getDateTime("yyyy-MM月");
			// 得到前一年的时间
			Calendar calendar = Calendar.getInstance();
			Date date = new Date(System.currentTimeMillis());
			calendar.setTime(date);
			calendar.add(Calendar.YEAR, -1);
			date = calendar.getTime();
			String oldDate = Util.DateToString(date, "yyyy-MM月");
			// 判断个人最早发放工资的时候和前一年对比
			String hql_date = "select mouth from Wage where code =? and mouth>=? order by mouth";
			oldDate = (String) totalDao.getObjectByCondition(hql_date,
					wageStandard.getCode(), oldDate);
			if (oldDate != null && oldDate.length() > 0) {
				String hql_wage = "select avg(yingfagongzi) from Wage where mouth between ? and ? and code=?";
				Float yfAvg = (Float) totalDao.getObjectByCondition(hql_wage,
						oldDate, nowDate, wageStandard.getCode());
				Float gjjBl = 0.07F;
				if (yfAvg != null) {
					// 查询个人缴纳比例
					if (wageStandard.getInsuranceGoldId() != null) {
						InsuranceGold ig = (InsuranceGold) totalDao
								.getObjectById(InsuranceGold.class,
										wageStandard.getInsuranceGoldId());
						if (ig == null)
							continue;
						gjjBl = ig.getHousingFund() / 100;
					}
					// 计算本次待调整的公积金金额(年平均工资(应发)*公积金比例)
					Float nowGjjNumber = Float.parseFloat(String.format("%.2f",
							yfAvg * gjjBl));

					wageStandard.setShiyebaoxian(nowGjjNumber);// 临时更改公积金金额
				}
				wageStandard.setGjjBase(gjjBl);// 临时作为缴纳比例
				wageStandard.setYingfagongzi(yfAvg);// 临时作为年平均数
				wageStandard.setInputDate(nowDate);// 临时存储
				wageStandard.setUpdateDate(oldDate);// 临时存储
			} else {
				wageStandard.setYingfagongzi(null);// 临时作为年平均数
			}
		}
		return list;
	}

	/***
	 * 通过工号得到历史的工资模版
	 * 
	 * @param code
	 * @return
	 */
	@Override
	public List findOldWageSByCode(String code) {
		if (code != null && code.length() > 0) {
			String hql = "from WageStandard where  code =? and isOnWork!='试用' order by inputDate desc ";
			return totalDao.query(hql, code);
		}
		return null;
	}

	/****
	 * 公积金批量调整
	 * 
	 * @param wageSList
	 * @return
	 */
	@Override
	public boolean updateWageSGjj(List<WageStandard> wageSList) {
		boolean bool = false;
		if (wageSList != null && wageSList.size() > 0) {
			for (WageStandard pageWageSt : wageSList) {
				if (pageWageSt != null && pageWageSt.getId() != null) {
					WageStandard oldWageSt = (WageStandard) totalDao
							.getObjectById(WageStandard.class, pageWageSt
									.getId());
					if (oldWageSt != null) {
						// 将老模版更改为历史
						oldWageSt.setStandardStatus("历史");
						oldWageSt.setProcessStatus("同意");
						oldWageSt.setChageStatus("完成");

						WageStandard ws = new WageStandard();
						BeanUtils.copyProperties(oldWageSt, ws,
								new String[] { "id" });
						ws.setInputDate(Util.getDateTime());
						ws.setGongjijin(pageWageSt.getGongjijin());
						// 新模版变为默认
						ws.setStandardStatus("默认");
						ws.setProcessStatus("同意");
						ws.setIsOnWork("公积金调整");
						ws.setInputPeople(Util.getLoginUser().getName());
						bool = totalDao.save(ws);
						bool = totalDao.update(oldWageSt);
					}
				}
			}
		}
		return bool;
	}

	@Override
	public String pladdWageStandard(File addFile) {

		String msg = null;
		boolean flag = true;
		String fileName = Util.getDateTime("yyyyMMddhhmmss") + ".xls";
		// 上传到服务器
		String fileRealPath = ServletActionContext.getServletContext()
				.getRealPath("/upload/file")
				+ "/" + fileName;
		File file = new File(fileRealPath);
		jxl.Workbook wk = null;
		int i = 0;
		try {
			FileCopyUtils.copy(addFile, file);
			// 开始读取excle表格
			InputStream is = new FileInputStream(fileRealPath);// 创建文件流;
			if (is != null) {
				wk = Workbook.getWorkbook(is);// 创建workbook
			}

			Sheet st = wk.getSheet(0);// 获得第一张sheet表;
			int exclecolums = st.getRows();// 获得excle总行数
			if (exclecolums > 2) {
				int count = 0;
				StringBuffer str = new StringBuffer();
				for (i = 2; i < exclecolums; i++) {
					Cell[] cells = st.getRow(i);// 获得每i行的所有单元格（返回的是数组）
					if (cells[0].getContents() != null) {
						String[] array = new String[cells.length];
						for (int j = 0; j < cells.length; j++) {
							array[j] = cells[j].getContents().replaceAll(
									" \\s*|\t|\r|\n", "");
						}
						String a = array[1];// 工号
						String w = array[2];// 姓名
						String b = array[3];// 卡号
						String c = array[4];// 部门
						String d = array[5];// 岗位工资
						String e = array[6];// 保密津贴
						String f = array[7];// 电话补贴
						String g = array[8];// 绩效考核工资
						String h = array[9];// 技能工资
						String k = array[10];// 工龄工资
						String l = array[11];// 应发工资
						String m = array[12];// 养老保险
						String n = array[13];// 医疗保险
						String o = array[14];// 失业保险
						String p = array[15];// 公积金
						String q = array[16];// 公积金基数
						String r = array[17];// 社保基数
						String s = array[18];// 本地或外地
						String t = array[19];// 城市或农村
						String u = array[20];// 人员类型
						String v = array[21];// 房租费
						if ((a == null || "".equals(a))
								&& (w == null || "".equals(w))
								&& (b == null || "".equals(b))) {
							break;
						}
						String hql_user = "from Users where code=? and cardId=? and name=?";
						Users user1 = (Users) totalDao.getObjectByCondition(
								hql_user, a, b, w);
						if (user1 == null) {
							count++;
							str.append(i + ",");
							continue;
						}
						String datetime = Util.getDateTime("yyyy-MM-dd");
						String hql = "from InsuranceGold where localOrField=? and cityOrCountryside=? and personClass=? and validityStartDate<=? and validityEndDate>=?";
						InsuranceGold insuranceGold = (InsuranceGold) totalDao
								.getObjectByCondition(hql, s, t, u, datetime,
										datetime);
						if (insuranceGold == null) {
							count++;
							str.append(i + ",");
							continue;
						}
						WageStandard wageStandard = new WageStandard();
						wageStandard.setCode(a);
						wageStandard.setCardId(b);
						wageStandard.setUserName(w);
						wageStandard.setDept(c);
						try {
							wageStandard.setGangweigongzi(Float.parseFloat(d));
							wageStandard.setBaomijintie(Float.parseFloat(e));
							wageStandard.setDianhuabutie(Float.parseFloat(f));
							wageStandard.setJixiaokaohegongzi(Float
									.parseFloat(g));
							wageStandard.setJinenggongzi(Float.parseFloat(h));
							wageStandard.setGonglinggongzi(Float.parseFloat(k));
							wageStandard.setYingfagongzi(Float.parseFloat(l));
							wageStandard.setTongchoujin(Float.parseFloat(m));
							wageStandard.setYiliaobaoxian(Float.parseFloat(n));
							wageStandard.setShiyebaoxian(Float.parseFloat(o));
							wageStandard.setGongjijin(Float.parseFloat(p));
							wageStandard.setGjjBase(Float.parseFloat(q));
							wageStandard.setSsBase(Float.parseFloat(r));
							wageStandard.setFangzufei(Float.parseFloat(v));
						} catch (Exception e1) {
							count++;
							e1.printStackTrace();
						}
						wageStandard.setLocalOrField(s);
						wageStandard.setCityOrCountryside(t);
						wageStandard.setPersonClass(u);
						Users user = Util.getLoginUser();
						wageStandard.setInputPeople(user.getName());
						wageStandard.setProcessStatus("同意");
						wageStandard.setStandardStatus("默认");
						wageStandard.setChageStatus("完成");
						wageStandard.setInsuranceGoldId(insuranceGold.getId());
						wageStandard.setBucha("yes");
						if (!totalDao.save(wageStandard)) {
							count++;
							str.append(i + ",");
						}
					} else {
						count++;
						continue;
					}
				}
				if (count == 0) {
					msg = "导入成功";
				} else {
					msg = "导入失败" + count + "行，失败的行数分别为:" + str.toString();
				}
				is.close();// 关闭流
				wk.close();// 关闭工作薄
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

}
