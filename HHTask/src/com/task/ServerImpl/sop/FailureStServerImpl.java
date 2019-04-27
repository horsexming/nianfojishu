package com.task.ServerImpl.sop;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.sop.FailureStServer;
import com.task.entity.sop.BuHeGePin;
import com.task.entity.sop.FailureSSMarkId;
import com.task.entity.sop.FailureSSOnDay;
import com.task.entity.sop.FailureSSOnMonth;
import com.task.entity.sop.FailureSSOnWeek;
import com.task.entity.sop.FailureStatistics;
import com.task.entity.sop.FailureStatisticsDetail;
import com.task.entity.sop.WaigouOrder;
import com.task.util.Util;
import com.tast.entity.zhaobiao.Nianlilv;
import com.tast.entity.zhaobiao.Zhmoban;

/***
 * 周不合格提交量统计Server实现类
 * 
 * @author 刘培
 * 
 */
public class FailureStServerImpl implements FailureStServer {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public void UpdatefailureStatistics(FailureStatistics failureSt) {
		FailureStatistics newfa = (FailureStatistics) totalDao.getObjectById(
				FailureStatistics.class, failureSt.getId());
		newfa.setDateTime(failureSt.getDateTime().replaceAll(" ", ""));
		newfa.setMarkId(failureSt.getMarkId());
		newfa.setClient(failureSt.getClient());

		newfa.setSubmitCount(failureSt.getSubmitCount());
		newfa.setFailureCount(failureSt.getFailureCount());

		newfa.setWeldingDefects(failureSt.getWeldingDefects());
		newfa.setStrikeSize(failureSt.getStrikeSize());
		newfa.setFlangeFlatness(failureSt.getFlangeFlatness());
		newfa.setTfb(failureSt.getTfb());

		newfa.setAirtight(failureSt.getAirtight());
		newfa.setExterior(failureSt.getExterior());
		newfa.setOther(failureSt.getOther());

		boolean bool = totalDao.update(newfa);
		if (bool) {
			// 统计不合格状态统计(周)
			String hql2 = "select sum(submitCount),sum(weldingDefects),sum(strikeSize),sum(flangeFlatness),sum(tfb),"
					+ "sum(airtight),sum(exterior),sum(other) from FailureStatistics where weekds=?";
			List list = totalDao.query(hql2, newfa.getWeekds());
			Object[] sums = list.toArray();
			Object[] obj = (Object[]) sums[0];
			// 查询是否存在本周的不合格状态统计
			String hql = "from FailureSSOnWeek where weekds=?";
			FailureSSOnWeek fssOnWeek = (FailureSSOnWeek) totalDao
					.getObjectByCondition(hql, newfa.getWeekds());
			if (fssOnWeek == null) {
				fssOnWeek = new FailureSSOnWeek();
			}
			// 赋值
			fssOnWeek.setOneWeekSc(Float.parseFloat(obj[0].toString()));
			fssOnWeek.setWeldingDefects(Float.parseFloat(obj[1].toString()));
			fssOnWeek.setStrikeSize(Float.parseFloat(obj[2].toString()));
			fssOnWeek.setFlangeFlatness(Float.parseFloat(obj[3].toString()));
			fssOnWeek.setTfb(Float.parseFloat(obj[4].toString()));
			fssOnWeek.setAirtight(Float.parseFloat(obj[5].toString()));
			fssOnWeek.setExterior(Float.parseFloat(obj[6].toString()));
			fssOnWeek.setOther(Float.parseFloat(obj[7].toString()));
			fssOnWeek.setWeekds(newfa.getWeekds());
			fssOnWeek.setAddTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
			if (fssOnWeek.getId() != null) {
				totalDao.update(fssOnWeek);
			} else {
				totalDao.save(fssOnWeek);
			}
			// 各件号不合格品数量统计
			String hql3 = "select markId,sum(submitCount),sum(failureCount) from FailureStatistics where weekds=? and markId=?  group by markId";
			List list2 = null;
			try {
				list2 = totalDao.query(hql3, newfa.getWeekds(), failureSt
						.getMarkId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			Object[] sums2 = list2.toArray();
			Object[] obj2 = (Object[]) sums2[0];
			String markid = obj2[0].toString();
			Float submitCount2 = Float.parseFloat(obj2[1].toString());
			Float failurecount2 = Float.parseFloat(obj2[2].toString());

			// 统计不合格状态统计(周)
			String hql4 = "from FailureSSMarkId where weekds=? and markId=?";
			FailureSSMarkId fsSMarkId = (FailureSSMarkId) totalDao
					.getObjectByCondition(hql4, newfa.getWeekds(), failureSt
							.getMarkId());
			if (fsSMarkId == null) {
				fsSMarkId = new FailureSSMarkId();
			}
			fsSMarkId.setMarkId(markid);
			fsSMarkId.setClient(failureSt.getClient());
			fsSMarkId.setOneWeekSc(submitCount2);
			fsSMarkId.setOneWeekFc(failurecount2);
			fsSMarkId.setFrequency(failurecount2 / submitCount2 * 1000000);
			fsSMarkId.setTargetPPM(failureSt.getTargetPPM());
			fsSMarkId.setWeekds(newfa.getWeekds());
			fsSMarkId.setAddTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
			if (fsSMarkId.getId() != null) {
				totalDao.update(fsSMarkId);
			} else {
				totalDao.save(fsSMarkId);
			}

		}
	}

	public void deletefailureStatistics(FailureStatistics failureSt) {
		failureSt = (FailureStatistics) totalDao.getObjectById(
				FailureStatistics.class, failureSt.getId());
		boolean bool = totalDao.delete(failureSt);
		if (bool) {
			// 统计不合格状态统计(周)
			String hql2 = "select sum(submitCount),sum(weldingDefects),sum(strikeSize),sum(flangeFlatness),sum(tfb),"
					+ "sum(airtight),sum(exterior),sum(other) from FailureStatistics where weekds=?";
			List list = totalDao.query(hql2, failureSt.getWeekds());

			Object[] sums = list.toArray();
			Object[] obj = (Object[]) sums[0];
			if (obj[0] != null && !"null".equals(obj[0])) {
				// 查询是否存在本周的不合格状态统计
				String hql = "from FailureSSOnWeek where weekds=?";
				FailureSSOnWeek fssOnWeek = (FailureSSOnWeek) totalDao
						.getObjectByCondition(hql, failureSt.getWeekds());
				if (fssOnWeek == null) {
					fssOnWeek = new FailureSSOnWeek();
				}
				// 赋值
				fssOnWeek.setOneWeekSc(Float.parseFloat(obj[0].toString()));
				fssOnWeek
						.setWeldingDefects(Float.parseFloat(obj[1].toString()));
				fssOnWeek.setStrikeSize(Float.parseFloat(obj[2].toString()));
				fssOnWeek
						.setFlangeFlatness(Float.parseFloat(obj[3].toString()));
				fssOnWeek.setTfb(Float.parseFloat(obj[4].toString()));
				fssOnWeek.setAirtight(Float.parseFloat(obj[5].toString()));
				fssOnWeek.setExterior(Float.parseFloat(obj[6].toString()));
				fssOnWeek.setOther(Float.parseFloat(obj[7].toString()));
				fssOnWeek.setWeekds(failureSt.getWeekds());
				fssOnWeek.setAddTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
				if (fssOnWeek.getId() != null) {
					totalDao.update(fssOnWeek);
				} else {
					totalDao.save(fssOnWeek);
				}
			} else {
				String hql = "from FailureSSOnWeek where weekds=?";
				FailureSSOnWeek fssOnWeek2 = (FailureSSOnWeek) totalDao
						.getObjectByCondition(hql, failureSt.getWeekds());
				totalDao.delete(fssOnWeek2);
			}

			// 各件号不合格品数量统计
			String hql3 = "select markId,sum(submitCount),sum(failureCount) from FailureStatistics where weekds=? and markId=?  group by markId";
			List list2 = null;
			try {
				list2 = totalDao.query(hql3, failureSt.getWeekds(), failureSt
						.getMarkId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (list2 != null && list2.size() > 0) {
				Object[] sums2 = list2.toArray();
				Object[] obj2 = (Object[]) sums2[0];
				/*
				 * 
				 */
				if (obj2[0] != null && !"null".equals(obj2[0])) {
					String markid = obj2[0].toString();
					Float submitCount2 = Float.parseFloat(obj2[1].toString());
					Float failurecount2 = Float.parseFloat(obj2[2].toString());

					// 统计不合格状态统计(周)
					String hql4 = "from FailureSSMarkId where weekds=? and markId=?";
					FailureSSMarkId fsSMarkId = (FailureSSMarkId) totalDao
							.getObjectByCondition(hql4, failureSt.getWeekds(),
									failureSt.getMarkId());
					if (fsSMarkId == null) {
						fsSMarkId = new FailureSSMarkId();
					}
					fsSMarkId.setMarkId(markid);
					fsSMarkId.setClient(failureSt.getClient());
					fsSMarkId.setOneWeekSc(submitCount2);
					fsSMarkId.setOneWeekFc(failurecount2);
					fsSMarkId.setFrequency(failurecount2 / submitCount2
							* 1000000);
					fsSMarkId.setTargetPPM(failureSt.getTargetPPM());
					fsSMarkId.setWeekds(failureSt.getWeekds());
					fsSMarkId.setAddTime(Util
							.getDateTime("yyyy-MM-dd HH:mm:ss"));
					if (fsSMarkId.getId() != null) {
						totalDao.update(fsSMarkId);
					} else {
						totalDao.save(fsSMarkId);
					}
				} else {
					String hql4 = "from FailureSSMarkId where weekds=? and markId=?";
					FailureSSMarkId fsSMarkId2 = (FailureSSMarkId) totalDao
							.getObjectByCondition(hql4, failureSt.getWeekds(),
									failureSt.getMarkId());
					totalDao.delete(fsSMarkId2);
				}
			} else {
				String hql4 = "from FailureSSMarkId where weekds=? and markId=?";
				FailureSSMarkId fsSMarkId2 = (FailureSSMarkId) totalDao
						.getObjectByCondition(hql4, failureSt.getWeekds(),
								failureSt.getMarkId());
				totalDao.delete(fsSMarkId2);
			}
		}

	}

	/***
	 * 手动添加质量不良
	 * 
	 * @param failureSt
	 * @return
	 */
	@Override
	public String addFailureStdetail(FailureStatistics failureSt) { // 统计不合格报表
		int week = 0;
		try {
			week = Util.getNowWeek(Util.StringToDate(failureSt.getDateTime(),
					"yyyy年MM月dd日"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String weeks = Util.getDateTime("yyyy") + "年" + week + "周";
		failureSt.setWeekds(weeks);
		failureSt.setAddTime(Util.getDateTime());
		Set<FailureStatisticsDetail> fsdSet = new HashSet<FailureStatisticsDetail>();
		if (failureSt.getBuhegeId() != null && failureSt.getBuhegeId() > 0) {
			BuHeGePin bhgp = (BuHeGePin) totalDao.getObjectById(
					BuHeGePin.class, failureSt.getBuhegeId());
			if (bhgp != null) {
				String code = failureSt.getBuhegeType() + bhgp.getCode()
						+ failureSt.getBuhegeTypeClass();
				code = code.replaceAll(" ", "");
				// 不良品类型明细
				FailureStatisticsDetail fsd = new FailureStatisticsDetail();
				fsd.setBuhegeId(bhgp.getId());
				fsd.setCode(code);
				fsd.setType(bhgp.getType());
				fsd.setBadNumber(failureSt.getFailureCount());
				fsd.setFailureStatistics(failureSt);
				fsdSet.add(fsd);
			}

		}
		String days = Util.getDateTime("yyyy年MM月dd日");
		String months = Util.getDateTime("yyyy年MM月");
		// failureSt.setFailureCount(1f);
		failureSt.setAdddays(days);
		failureSt.setAddmonths(months);
		failureSt.setFsdSet(fsdSet);
		failureSt.setType("自制");
		failureSt.setNowberakcount(1f);// 记录当前时间节点该批次件号不合格品总数
		totalDao.save(failureSt);
		Set<FailureStatisticsDetail> fsdSet1 = failureSt.getFsdSet();
		// 统计日报表
		FailureSSOnDay fsday = (FailureSSOnDay) totalDao.getObjectByCondition(
				"  from FailureSSOnDay where gongwei = ? and days = ? ",
				failureSt.getGongwei(), days);
		if (fsday != null) {
			Set<FailureStatisticsDetail> fsddaySet = fsday.getFsdSet();
			for (FailureStatisticsDetail f : fsdSet1) {
				FailureStatisticsDetail fsd = (FailureStatisticsDetail) totalDao
						.getObjectByCondition(
								" from FailureStatisticsDetail where failureSSOnDay.id =? and buhegeId = ?",
								fsday.getId(), f.getBuhegeId());
				if (fsd != null) {
					fsd.setBadNumber(fsd.getBadNumber() + f.getBadNumber());
					totalDao.update(fsd);
				} else {
					if (fsddaySet == null) {
						fsddaySet = new HashSet<FailureStatisticsDetail>();
					}
					fsd = new FailureStatisticsDetail();
					fsd.setBadNumber(f.getBadNumber());
					fsd.setBuhegeId(f.getBuhegeId());
					fsd.setCode(f.getCode());
					fsd.setType(f.getType());
					fsd.setFailureSSOnDay(fsday);
					fsddaySet.add(fsd);
				}
			}
			fsday
					.setOneDayFc(fsday.getOneDaySc()
							+ failureSt.getFailureCount());// 每日不合格总数 某工位
			fsday
					.setOneDaySc(fsday.getOneDaySc()
							+ failureSt.getFailureCount());// 每日检验总数
			// 某工位
			fsday.setFsdSet(fsddaySet);
			totalDao.update(fsday);
		} else {
			fsday = new FailureSSOnDay();
			fsday.setOneDayFc(failureSt.getFailureCount());// 每日不合格总数 某工位
			fsday.setOneDaySc(failureSt.getSubmitCount());// 每日检验总数 某工位
			fsday.setAddTime(Util.getDateTime());
			fsday.setGongwei(failureSt.getGongwei());
			fsday.setDays(days);
			fsday.setMonths(months);
			fsday.setWeekds(weeks);
			Set<FailureStatisticsDetail> fsddaySet = fsday.getFsdSet();
			for (FailureStatisticsDetail f : fsdSet1) {
				FailureStatisticsDetail fsd = new FailureStatisticsDetail();
				fsd.setBadNumber(f.getBadNumber());
				fsd.setBuhegeId(f.getBuhegeId());
				fsd.setCode(f.getCode());
				fsd.setType(f.getType());
				fsd.setFailureSSOnDay(fsday);
				if (fsddaySet == null) {
					fsddaySet = new HashSet<FailureStatisticsDetail>();
				}
				fsddaySet.add(fsd);
			}
			fsday.setFsdSet(fsddaySet);
			totalDao.save(fsday);
		}
		// 统计周报表
		FailureSSOnWeek fsweek = (FailureSSOnWeek) totalDao
				.getObjectByCondition(
						"  from FailureSSOnWeek where gongwei = ? and weekds = ? ",
						failureSt.getGongwei(), weeks);
		if (fsweek != null) {
			Set<FailureStatisticsDetail> fsdweekSet = fsweek.getFsdSet();
			for (FailureStatisticsDetail f : fsdSet1) {
				FailureStatisticsDetail fsd = (FailureStatisticsDetail) totalDao
						.getObjectByCondition(
								" from FailureStatisticsDetail where failureSSOnWeek.id =? and buhegeId = ?",
								fsweek.getId(), f.getBuhegeId());
				if (fsd != null) {
					fsd.setBadNumber(fsd.getBadNumber() + f.getBadNumber());
					totalDao.update(fsd);
				} else {
					if (fsdweekSet == null) {
						fsdweekSet = new HashSet<FailureStatisticsDetail>();
					}
					fsd = new FailureStatisticsDetail();
					fsd.setBadNumber(f.getBadNumber());
					fsd.setBuhegeId(f.getBuhegeId());
					fsd.setCode(f.getCode());
					fsd.setType(f.getType());
					fsd.setFailureSSOnWeek(fsweek);
					fsdweekSet.add(fsd);
				}
			}
			fsweek.setOneWeekFc(fsweek.getOneWeekFc()
					+ failureSt.getFailureCount());// 每周不合格总数 某工位
			fsweek.setOneWeekSc(fsweek.getOneWeekSc()
					+ failureSt.getSubmitCount());// 每周检验总数 某工位
			fsweek.setFsdSet(fsdweekSet);
			totalDao.update(fsweek);
		} else {
			fsweek = new FailureSSOnWeek();
			fsweek.setOneWeekFc(failureSt.getFailureCount());// 每周不合格总数 某工位
			fsweek.setOneWeekSc(failureSt.getSubmitCount());// 每周检验总数 某工位
			fsweek.setAddTime(Util.getDateTime());
			fsweek.setGongwei(failureSt.getGongwei());
			fsweek.setWeekds(weeks);
			Set<FailureStatisticsDetail> fsdweekSet = fsweek.getFsdSet();
			for (FailureStatisticsDetail f : fsdSet1) {
				FailureStatisticsDetail fsd = new FailureStatisticsDetail();
				fsd.setBadNumber(f.getBadNumber());
				fsd.setBuhegeId(f.getBuhegeId());
				fsd.setCode(f.getCode());
				fsd.setType(f.getType());
				fsd.setFailureSSOnWeek(fsweek);
				if (fsdweekSet == null) {
					fsdweekSet = new HashSet<FailureStatisticsDetail>();
				}
				fsdweekSet.add(fsd);
			}
			fsweek.setFsdSet(fsdweekSet);
			totalDao.save(fsweek);
		}
		// 统计周报表
		FailureSSOnMonth fsdmonth = (FailureSSOnMonth) totalDao
				.getObjectByCondition(
						"  from FailureSSOnMonth where gongwei = ? and months = ? ",
						failureSt.getGongwei(), months);
		if (fsdmonth != null) {
			Set<FailureStatisticsDetail> fsdmonthSet = fsweek.getFsdSet();
			for (FailureStatisticsDetail f : fsdSet1) {
				FailureStatisticsDetail fsd = (FailureStatisticsDetail) totalDao
						.getObjectByCondition(
								" from FailureStatisticsDetail where failureSSOnWeek.id =? and buhegeId = ?",
								fsweek.getId(), f.getBuhegeId());
				if (fsd != null) {
					fsd.setBadNumber(fsd.getBadNumber() + f.getBadNumber());
					totalDao.update(fsd);
				} else {
					if (fsdmonthSet == null) {
						fsdmonthSet = new HashSet<FailureStatisticsDetail>();
					}
					fsd = new FailureStatisticsDetail();
					fsd.setBadNumber(f.getBadNumber());
					fsd.setBuhegeId(f.getBuhegeId());
					fsd.setCode(f.getCode());
					fsd.setType(f.getType());
					fsd.setFailureSSOnMonth(fsdmonth);
					fsdmonthSet.add(fsd);
				}
			}
			fsdmonth.setOneMonthSc(fsdmonth.getOneMonthSc()
					+ failureSt.getFailureCount());// 每月不合格总数 某工位
			fsdmonth.setOneMonthFc(fsdmonth.getOneMonthFc()
					+ failureSt.getSubmitCount());// 每月检验总数 某工位
			fsdmonth.setFsdSet(fsdmonthSet);
			totalDao.update(fsdmonth);
		} else {
			fsdmonth = new FailureSSOnMonth();
			fsdmonth.setOneMonthSc(failureSt.getFailureCount());// 每月不合格总数
			// 某工位
			fsdmonth.setOneMonthFc(failureSt.getSubmitCount());// 每月检验总数 某工位
			fsdmonth.setAddTime(Util.getDateTime());
			fsdmonth.setGongwei(failureSt.getGongwei());
			fsdmonth.setWeekds(weeks);
			Set<FailureStatisticsDetail> fsdmonthSet = fsdmonth.getFsdSet();
			for (FailureStatisticsDetail f : fsdSet1) {
				FailureStatisticsDetail fsd = new FailureStatisticsDetail();
				fsd.setBadNumber(f.getBadNumber());
				fsd.setBuhegeId(f.getBuhegeId());
				fsd.setCode(f.getCode());
				fsd.setType(f.getType());
				fsd.setFailureSSOnMonth(fsdmonth);
				if (fsdmonthSet == null) {
					fsdmonthSet = new HashSet<FailureStatisticsDetail>();
				}
				fsdmonthSet.add(fsd);
			}
			fsdmonth.setFsdSet(fsdmonthSet);
			totalDao.save(fsdmonth);
		}
		return "添加完成";
	}

	/***
	 * 添加零件周不合格量(统计周数据)
	 * 
	 * @param FailureSt
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String addFailureSt(FailureStatistics failureSt) {
		if (failureSt != null) {
			// 计算第几周
			// int week = Util.getNowWeek();
			failureSt.setMarkId(failureSt.getMarkId().replaceAll(" ", ""));
			int week = 0;
			try {
				week = Util.getNowWeek(Util.StringToDate(failureSt
						.getDateTime(), "yyyy年MM月dd日"));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			String weeks = Util.getDateTime("yyyy") + "年" + week + "周";
			failureSt.setWeekds(weeks);
			failureSt.setAddTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
			boolean bool = totalDao.save(failureSt);

			if (bool) {
				// 统计不合格状态统计(周)
				String hql2 = "select sum(submitCount),sum(weldingDefects),sum(strikeSize),sum(flangeFlatness),sum(tfb),"
						+ "sum(airtight),sum(exterior),sum(other) from FailureStatistics where weekds=?";
				List list = totalDao.query(hql2, weeks);
				Object[] sums = list.toArray();
				Object[] obj = (Object[]) sums[0];
				// 查询是否存在本周的不合格状态统计
				String hql = "from FailureSSOnWeek where weekds=?";
				FailureSSOnWeek fssOnWeek = (FailureSSOnWeek) totalDao
						.getObjectByCondition(hql, weeks);
				if (fssOnWeek == null) {
					fssOnWeek = new FailureSSOnWeek();
				}
				// 赋值
				fssOnWeek.setOneWeekSc(Float.parseFloat(obj[0].toString()));
				fssOnWeek
						.setWeldingDefects(Float.parseFloat(obj[1].toString()));
				fssOnWeek.setStrikeSize(Float.parseFloat(obj[2].toString()));
				fssOnWeek
						.setFlangeFlatness(Float.parseFloat(obj[3].toString()));
				fssOnWeek.setTfb(Float.parseFloat(obj[4].toString()));
				fssOnWeek.setAirtight(Float.parseFloat(obj[5].toString()));
				fssOnWeek.setExterior(Float.parseFloat(obj[6].toString()));
				fssOnWeek.setOther(Float.parseFloat(obj[7].toString()));
				fssOnWeek.setWeekds(weeks);
				fssOnWeek.setAddTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
				if (fssOnWeek.getId() != null) {
					totalDao.update(fssOnWeek);
				} else {
					totalDao.save(fssOnWeek);
				}

				// 各件号不合格品数量统计
				String hql3 = "select markId,sum(submitCount),sum(failureCount) from FailureStatistics where weekds=? and markId=?  group by markId";
				List list2 = null;
				try {
					list2 = totalDao.query(hql3, weeks, failureSt.getMarkId());
				} catch (Exception e) {
					e.printStackTrace();
				}
				Object[] sums2 = list2.toArray();
				Object[] obj2 = (Object[]) sums2[0];
				String markid = obj2[0].toString();
				Float submitCount2 = Float.parseFloat(obj2[1].toString());
				Float failurecount2 = Float.parseFloat(obj2[2].toString());

				// 统计不合格状态统计(周)
				String hql4 = "from FailureSSMarkId where weekds=? and markId=?";
				FailureSSMarkId fsSMarkId = (FailureSSMarkId) totalDao
						.getObjectByCondition(hql4, weeks, failureSt
								.getMarkId());
				if (fsSMarkId == null) {
					fsSMarkId = new FailureSSMarkId();
				}
				fsSMarkId.setMarkId(markid);
				fsSMarkId.setClient(failureSt.getClient());
				fsSMarkId.setOneWeekSc(submitCount2);
				fsSMarkId.setOneWeekFc(failurecount2);
				fsSMarkId.setFrequency(failurecount2 / submitCount2 * 1000000);
				fsSMarkId.setTargetPPM(failureSt.getTargetPPM());
				fsSMarkId.setWeekds(weeks);
				fsSMarkId.setAddTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
				if (fsSMarkId.getId() != null) {
					totalDao.update(fsSMarkId);
				} else {
					totalDao.save(fsSMarkId);
				}
				return "添加成功!";
			}
		}
		return null;
	}

	private void saveMarkId() {

	}

	/***
	 * 每周一次提交不合格统计
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object[] findAllFailure(Integer id, String weekds, int page, int rows) {
		if (weekds == null || weekds.length() <= 0) {
			weekds = Util.getLastWeek();
		}
		String str = "";
		if (id != null && id>0) {
			str = " and gongwei in (select g.gongweihao from Screen s join s.gongweis g where s.id="
					+ id + " )";
		}
		String hql = "from FailureStatistics where weekds=? " + str;

		// String hql =
		// "from FailureStatistics where dateTime=? order by client,dateTime";
		String hql_bhg = " select type from FailureStatisticsDetail where failureStatistics.id in ( select id "
				+ hql + ")  group by type";
		List<String> bhgList = totalDao.query(hql_bhg, weekds);
		List<FailureStatistics> fssList = totalDao.findAllByPage(hql
				+ "  order by client,dateTime", page, rows, weekds);
		for (FailureStatistics failureStatistics : fssList) {
			List<FailureStatisticsDetail> list1 = new ArrayList<FailureStatisticsDetail>();
			list1.addAll(failureStatistics.getFsdSet());
			failureStatistics.setFsdList(list1);
		}
		int count = totalDao.getCount(hql, weekds);
		// return totalDao.query(hql, weekds);
		return new Object[] { fssList, count, bhgList };
	}

	/***
	 * 不合格状态统计查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List findAllFailureSSOnWeek(String weekds, Integer id,String pageStatus) {
		if (weekds == null || weekds.length() <= 0) {
			weekds = Util.getWeek(null,"");
		}
		String str = "";
		if (id != null && id>0) {
			str = " and gongwei in (select g.gongweihao from Screen s join s.gongweis g where s.id="
					+ id + " )";
		}
		String hql = "from FailureSSOnWeek where weekds=?";
		hql += str;
		if("ww".equals(pageStatus)){
			hql+= " and type = '外委'";
		}else if("wg".equals(pageStatus)){
			hql+= " and type = '外购'";
		}	
		return totalDao.query(hql, weekds);
	}

	/***
	 * 不合格状态统计查询(当前周)
	 */
	@Override
	public FailureSSOnWeek findAllFailureSSOnWeek() {
		int week = Util.getNowWeek();
		String weekds = Util.getDateTime("yyyy") + "年" + week + "周";
		String hql = "from FailureSSOnWeek where weekds=?";
		return (FailureSSOnWeek) totalDao.getObjectByCondition(hql, weekds);
	}

	/***
	 * 各件号不合格品数量统计 查询
	 */
	@Override
	public List findAllFailureSSMarkId(String weekds,String type) {
		if (weekds == null || weekds.length() <= 0) {
			int week = Util.getNowWeek();
			weekds = Util.getDateTime("yyyy") + "年" + week + "周";
		}
		String str="";
		if("ww".equals(type)){
			str=" and type = '外委'";
		}else if("wg".equals(type)){
			str=" and type = '外购'";
		}
		String hql = "select markId,sum(submitCount),sum(failureCount) from FailureStatistics where weekds=? "+str+" group by markId order by sum(failureCount) desc";
		List<Object[]> list = totalDao.query(hql, weekds);

		List lists = new ArrayList();
		if (list != null) {
			for (Object[] objects : list) {
				FailureSSMarkId failureSSMarkId = new FailureSSMarkId();
				failureSSMarkId.setMarkId(objects[0].toString());
				failureSSMarkId.setOneWeekSc(Float.valueOf(objects[1]
						.toString()));
				failureSSMarkId.setOneWeekFc(Float.valueOf(objects[2]
						.toString()));
				failureSSMarkId.setFrequency(failureSSMarkId.getOneWeekFc()
						/ failureSSMarkId.getOneWeekSc() * 1000000);
				failureSSMarkId.setTargetPPM(3500F);
				lists.add(failureSSMarkId);
			}
		}
		return lists;

		// String hql2 = "from FailureSSMarkId where weekds=?";
		// return totalDao.query(hql2, weekds);
	}

	public Object[] findAllFailureSSMarkId(String weekds, int page, int rows) {
		if (weekds == null || weekds.length() <= 0) {
			int week = Util.getNowWeek();
			weekds = Util.getDateTime("yyyy") + "年" + week + "周";
		}
		weekds="2018年11周";
		//String hql = "from FailureSSMarkId where weekds=?";
		//List list = totalDao.findAllByPage(hql, page, rows, weekds);
		
		String hql = "select markId,sum(submitCount),sum(failureCount) from FailureStatistics where weekds=? group by markId order by sum(failureCount) desc";
		List<Object[]> list = totalDao.query(hql, page, rows,weekds);

		List lists = new ArrayList();
		if (list != null) {
			for (Object[] objects : list) {
				FailureSSMarkId failureSSMarkId = new FailureSSMarkId();
				failureSSMarkId.setMarkId(objects[0].toString());
				failureSSMarkId.setOneWeekSc(Float.valueOf(objects[1]
						.toString()));
				failureSSMarkId.setOneWeekFc(Float.valueOf(objects[2]
						.toString()));
				failureSSMarkId.setFrequency(failureSSMarkId.getOneWeekFc()
						/ failureSSMarkId.getOneWeekSc() * 1000000);
				failureSSMarkId.setTargetPPM(3500F);
				lists.add(failureSSMarkId);
			}
		}
		
		return new Object[] { lists };
	}

	/***
	 * 产品一次提交不合格品率趋势图 （PPM）
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object[] findAllFsMarkId(String weekds, String clients) {
		if (weekds == null || weekds.length() <= 0) {
			int week = Util.getNowWeek();
			weekds = Util.getDateTime("yyyy") + "年" + week + "周";
		}
		String year = Util.getDateTime("yyyy") + "年";
		// 查询当前年份的所有周信息
		String weekdsHql = "select weekds from FailureSSMarkId where  weekds like '%"
				+ year + "%' group by weekds order by weekds";
		List weekdslist = totalDao.query(weekdsHql);
		Object weekdss = weekdslist.toArray();

		// 查询当前年份的所有客户信息并分类
		String hql = "select client from FailureSSMarkId where weekds like '%"
				+ year + "%' group by client order by client desc ";
		List clientList = totalDao.query(hql);
		List objectList = new ArrayList();
		// 根据客户分类查询对应件号
		for (int i = 0; i < clientList.size(); i++) {
			String client = (String) clientList.get(i);
			String markIdHql = "select markId from FailureSSMarkId where client=? and weekds like '%"
					+ year + "%' group by markId ";
			List markidList = totalDao.query(markIdHql, client);
			List markIdsList = new ArrayList();
			List mfList = new ArrayList();
			// 遍历件号
			for (int j = 0; j < markidList.size(); j++) {
				// 查询件号对应周数的数量
				String markId = (String) markidList.get(j);// 件号
				List list = new ArrayList();
				List list2 = new ArrayList();
				list2.add(markId);
				for (int k = 0; k < weekdslist.size(); k++) {
					String weekd = (String) weekdslist.get(k);// 第几周
					// 平率
					String frequencyHql = "select frequency from FailureSSMarkId where markid=? and weekds =? order by weekds ";
					Object frequency = totalDao.getObjectByCondition(
							frequencyHql, markId, weekd);
					if (frequency == null) {
						frequency = null;
					} else {
						float frequencyF = (Float) frequency;
						int frequencyInt = (int) frequencyF;
						frequency = frequencyInt;
					}
					list.add(frequency);
					list2.add(frequency);
				}
				markIdsList.add(list);
				mfList.add(list2);
			}
			Map<String, Object> clientMap = new HashMap<String, Object>();
			clientMap.put("client", client);
			clientMap.put("frequency", markIdsList);
			clientMap.put("mf", mfList);
			objectList.add(clientMap);
		}
		return new Object[] { objectList.toArray(), weekdss };
	}

	@Override
	public Object[] listFailureStatistics(FailureStatistics failureStatistics,
			Integer parseInt, Integer pageSize) {
		// TODO Auto-generated method stub
		if (failureStatistics == null) {
			failureStatistics = new FailureStatistics();
		}
		String hql = totalDao.criteriaQueries(failureStatistics, null, null)
				+ "  order by addTime desc ";
		// String hql = "from Zhaobiao where 1=1 order by  fabushijian desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	@Override
	public FailureStatistics byId(Integer id) {
		// TODO Auto-generated method stub
		return (FailureStatistics) totalDao.getObjectById(
				FailureStatistics.class, id);
	}

	@Override
	public List<FailureStatisticsDetail> findFsdByweekId(Integer id) {
		if (id != null) {
			return totalDao
					.query(
							" from FailureStatisticsDetail where failureSSOnWeek.id = ? ",
							id);
		}
		return null;
	}

	@Override
	public List findFsdByweekId(String ids) {
		if (ids != null) {
			return totalDao
					.findAllByPage(
							"select type,sum(badNumber) from FailureStatisticsDetail where failureSSOnWeek.id in ("
									+ ids
									+ ") group by type order by sum(badNumber) desc ",
							1, 6);
		}
		return null;
	}

	@Override
	public void exprot(FailureStatistics failureStatistics) {
		Object[] obj =	listFailureStatistics(failureStatistics, 0, 0);
		List<FailureStatistics> fstList = (List<FailureStatistics>) obj[0];
		if(fstList!=null && fstList.size()>0){
			try {
				HttpServletResponse response = (HttpServletResponse) ActionContext
						.getContext().get(ServletActionContext.HTTP_RESPONSE);
				OutputStream os = response.getOutputStream();
				response.reset();
				response
						.setHeader("Content-disposition", "attachment; filename="
								+ new String("不合格品统计".getBytes("GB2312"), "8859_1")
								+ ".xls");
				response.setContentType("application/msexcel");
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				WritableSheet ws = wwb.createSheet("不合格品统计", 0);
				WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 11,
						WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
						jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
				WritableCellFormat wcf_title = new WritableCellFormat(wf_title); // 单元格定义
				wcf_title.setBackground(jxl.format.Colour.WHITE); // 设置单元格的背景颜色
				wcf_title.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
				wcf_title.setBorder(jxl.format.Border.ALL,
						jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK);
				ws.addCell(new Label(0, 0, "不合格品统计", wcf_title));
				ws.mergeCells(0, 0, 8, 0);
				ws.addCell(new Label(0, 1, "序号"));
				ws.addCell(new Label(1, 1, "日期"));
				ws.addCell(new Label(2, 1, "零件号"));
				ws.addCell(new Label(3, 1, "提交数量"));
				ws.addCell(new Label(4, 1, "不合格数量"));
				ws.addCell(new Label(5, 1, "目标值PPM"));
				ws.addCell(new Label(6, 1, "周"));
				ws.addCell(new Label(7, 1, "添加时间"));
				for (int i = 0; i < fstList.size(); i++) {
					FailureStatistics fst = fstList.get(i);
					ws.addCell(new Label(0, i + 2, i + 1 + ""));
					ws.addCell(new Label(1, i + 2, fst.getDateTime()));
					ws.addCell(new Label(2, i + 2, fst.getMarkId()));
					ws.addCell(new jxl.write.Number(3, i + 2, fst.getSubmitCount()==null?0:fst.getSubmitCount()));
					ws.addCell(new jxl.write.Number(4, i + 2,fst.getFailureCount()==null?0:fst.getFailureCount()));
					ws.addCell(new jxl.write.Number(5, i + 2, fst.getTargetPPM()==null?0:fst.getTargetPPM()));
					ws.addCell(new Label(6, i + 2, fst.getWeekds()));
					ws.addCell(new Label(7, i + 2, fst.getAddTime()));
				}
				wwb.write();
				wwb.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}
		
	}

}