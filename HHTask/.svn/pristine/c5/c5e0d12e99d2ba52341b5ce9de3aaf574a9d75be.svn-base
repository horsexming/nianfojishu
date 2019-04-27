package com.task.ServerImpl.zgkh;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.zgkh.ScoreStatisticsServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.entity.AssScore;
import com.task.entity.WageStandard;
import com.task.entity.zgkh.ScoreStatistics;
import com.task.util.Util;

/***
 * 主管级考核总分Server层实现类
 * 
 * @author 刘培
 * 
 */
public class ScoreStatisticsServerImpl implements ScoreStatisticsServer {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 添加总分
	@Override
	public String addScoreStatistics(AssScore assScore) {
		if (assScore != null) {
			ScoreStatistics ss = findSsByUserId(assScore.getUserId(), assScore
					.getAsstMouth()); // 根据用户id查询其总分信息
			String hql = "from AssessPersonnel a where a.usersGroup.status ='zghp' ";
			int count = totalDao.getCount(hql) - 1;// 查询已经绑定了模版的人员总数-1(自己)
			// 平均分处理
			int userId = assScore.getUserId();
			String lastMonth = totalDao.getLastMonth("yyyy-MM月");
			// 查询分数总个数和总平均分
			String hql5 = "select count(*) from AssScore where userId=? and asstMouth=? and assType='主管级'";
			List soungAvgList = totalDao.query(hql5, userId, lastMonth);
			String hql_pingqu = "select avg(percentageScore) from AssScore where userId=? and asstMouth=? and assType='主管级'";
			
			//Object object[] = (Object[]) soungAvgList.get(0);
			//int sum = Integer.parseInt(object[0].toString());// 分数总个数
			Float sum =  (Float) soungAvgList.get(0);
			//Float cumScore = Float.valueOf(object[1].toString());// 平均分
			Float cumScore = (Float) totalDao.getObjectByCondition(hql_pingqu, userId, lastMonth);
			if (sum > 2) {
				String hql2 = "select avg(ta_hr_percentageScore) from ta_hr_assscore  where ta_hr_userid=?  and ta_hr_asstmouth =? "
						+ " and id <>(select top 1 id from ta_hr_assScore where ta_hr_userid=? and ta_hr_asstmouth =? order by ta_hr_percentageScore desc)"
						+ " and id <>(select top 1 id from ta_hr_assScore where ta_hr_userid=? and ta_hr_asstmouth =? order by ta_hr_percentageScore )";
				List list = totalDao.createQuerySelect(null, hql2, userId,
						lastMonth, userId, lastMonth, userId, lastMonth);
				cumScore = Float.valueOf(list.get(0).toString());// 查询平均分
			}

			String hql3 = "select count(*) from AssScore where addUserId=? and assType='主管级' and asstMouth=?";
			float asscount = (Float) totalDao.getObjectByCondition(hql3,
					assScore.getUserId(), Util.getLastMonth("yyyy-MM月"));// 已打分的人数
			String hql4 = "from WageStandard where code=? and standardStatus='默认'";
			boolean bool = false;
			if (ss == null) {
				ss = new ScoreStatistics();
				ss.setCode(assScore.getCode());// 工号
				ss.setCardId(assScore.getCardId());// 卡号
				ss.setUserId(assScore.getUserId());// 用户id
				ss.setUserName(assScore.getUserName());// 用户名
				ss.setDept(assScore.getDept());// 部门
				ss.setMouth(assScore.getAsstMouth());// 考核月份
				ss.setTotalScore(cumScore);// 平均分

				WageStandard wageStandard = (WageStandard) totalDao
						.getObjectByCondition(hql4, ss.getCode());// 根据工号查询默认工资模版
				if (wageStandard != null) {
					if (asscount == count) {
						if (wageStandard != null) {
							ss.setSdWage(wageStandard.getJixiaokaohegongzi()
									* ss.getTotalScore() / 100);// 试算工资
						}
						ss.setScoreStatus("完成");
						ss.setWsWage(wageStandard.getJixiaokaohegongzi());// 模版工资
					} else {
						ss.setScoreStatus("打分");
						ss.setSdWage(0F);
						ss.setWsWage(wageStandard.getJixiaokaohegongzi());// 模版工资
					}
				} else {
					return assScore.getUserName() + "不存在工资模版!无法评分!";
				}
				// 添加总分
				try {
					bool = totalDao.save(ss);
				} catch (Exception e) {
					totalDao.delete(assScore);
					return "添加总分出错";
				}
				// 更新成绩
				if (bool) {
					assScore.setSs(ss);
					bool = totalDao.update(assScore);
				}
			} else {
				ss.setTotalScore(cumScore);// 平均分
				if (asscount == count) {
					WageStandard wageStandard = (WageStandard) totalDao
							.getObjectByCondition(hql4, ss.getCode());// 根据工号查询默认工资模版
					if (wageStandard != null) {
						ss.setSdWage(wageStandard.getJixiaokaohegongzi()
								* ss.getTotalScore() / 100);// 试算工资
						ss.setWsWage(wageStandard.getJixiaokaohegongzi());//
					}
					ss.setScoreStatus("完成");
				} else {
					ss.setScoreStatus("打分");
					ss.setSdWage(0F);

				}
				try {
					bool = totalDao.update(ss);// 添加总分
				} catch (Exception e) {
					totalDao.delete(assScore);
					return "添加总分出错";
				}
				if (bool) {
					assScore.setSs(ss);
					bool = totalDao.update(assScore);
				}
			}

			// 更新自己的总分状态
			if (bool) {
				ScoreStatistics privateSs = findSsByUserId(assScore
						.getAddUserId(), assScore.getAsstMouth()); // 根据自己的用户id查询总分信息
				if (privateSs != null
						&& !privateSs.getScoreStatus().equals("完成")) {// 如果自己有总分信息并且没有完成则更新
					float privateAsscount = (Float) totalDao
							.getObjectByCondition(hql3,
									assScore.getAddUserId(), Util
											.getLastMonth("yyyy-MM月"));// 已打分的人数
					if (privateAsscount == count) {
						WageStandard wageStandard = (WageStandard) totalDao
								.getObjectByCondition(hql4, privateSs.getCode());// 根据工号查询默认工资模版
						if (wageStandard != null) {
							privateSs.setSdWage(wageStandard
									.getJixiaokaohegongzi()
									* ss.getTotalScore() / 100);// 试算工资
						}
						privateSs.setScoreStatus("完成");
						bool = totalDao.update(privateSs);// 更新总分状态
					}
				}
			}
			String hql6 = "from ScoreStatistics where mouth=? and scoreStatus='完成'";
			int sumCount = totalDao.getCount(hql6, assScore.getAsstMouth());
			if (sumCount == count + 1) {
				AlertMessagesServerImpl.addAlertMessages("考核明细管理（总经理审核）",
						assScore.getAsstMouth() + "的主管级考核成绩已经全部生成,请您处理工资信息!",
						"2");
			}
			return "";

		}
		return null;
	}

	// 删除成绩后更新总分信息
	public boolean delScore(AssScore assScore) {
		boolean bool = false;
		if (assScore != null) {
			// 更新被删除成绩人员的平均分
			ScoreStatistics ss = findSsByUserId(assScore.getUserId(), assScore
					.getAsstMouth()); // 根据用户id查询其总分信息
			String hql2 = "select avg(percentageScore) from AssScore where userId=?";
			Object object = totalDao.getObjectByCondition(hql2, assScore
					.getUserId());
			Float cumScore = 0F;
			if (object != null) {
				cumScore = Float.valueOf(object.toString());// 查询平均分
			}
			if (ss != null) {
				ss.setTotalScore(cumScore);// 重新计算平均分
				bool = totalDao.update(ss);
			}

			// 更新自己的总分状态
			ScoreStatistics privateSs = findSsByUserId(assScore.getAddUserId(),
					assScore.getAsstMouth()); // 根据用户id查询其总分信息
			if (privateSs != null) {
				privateSs.setScoreStatus("打分");
				bool = totalDao.update(privateSs);
			}
		}
		return bool;

	}

	// 根据用户id和考核月份查询其总分信息
	@Override
	public ScoreStatistics findSsByUserId(int userId, String mouth) {
		if ((Object) userId != null && userId > 0) {
			String hql = "from ScoreStatistics where userId=? and mouth=?";
			return (ScoreStatistics) totalDao.getObjectByCondition(hql, userId,
					mouth);
		}
		return null;
	}

	// 查询所有总分信息
	@SuppressWarnings("unchecked")
	@Override
	public Object[] findAllSS(ScoreStatistics ss, int pageNo, int pageSize) {
		if (ss == null) {
			ss = new ScoreStatistics();
			ss.setScoreStatus("完成");
		}
		// ss.setScoreStatus("完成");

		String hql = totalDao.criteriaQueries(ss, null, null)
				+ " order by mouth desc,totalScore desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	// 通过总分id查询所有成绩
	@SuppressWarnings("unchecked")
	@Override
	public List<AssScore> findScoreBySSId(int ssId) {
		if ((Object) ssId != null && ssId > 0) {
			String hql = "from AssScore a where a.ss.id =?";
			return totalDao.query(hql, ssId);
		}
		return null;
	}

}
