package com.task.ServerImpl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.sun.jna.platform.win32.Netapi32Util.User;
import com.task.Dao.TotalDao;
import com.task.Server.WorkLogServer;
import com.task.entity.Users;
import com.task.entity.WorkLog;
import com.task.util.RtxUtil;
import com.task.util.Util;

public class WorkLogServerImpl implements WorkLogServer {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 添加日志
	public boolean addWorkLog(WorkLog workLog) {
		if (workLog != null) {
			Users user = Util.getLoginUser();
			workLog.setUserId(user.getId());
			workLog.setUserName(user.getName());
			workLog.setCode(user.getCode());// 工号
			workLog.setCardId(user.getCardId());// 卡号
			workLog.setDept(user.getDept());// 部门
			workLog.setMouth(Util.getDateTime("yyyy-MM月"));// 月份
			workLog.setAddDateTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));// 添加时间
			workLog.setContent(workLog.getContent().replaceAll("<", " &lt; ")
					.replaceAll(">", " &gt; "));// 内容
			return totalDao.save(workLog);
		}
		return false;
	}

	// 通过Id查询日志
	public WorkLog findWorkLogById(Integer id) {
		if (id != null && id > 0) {
			return (WorkLog) totalDao.getObjectById(WorkLog.class, id);
		}
		return null;
	}

	// 查询个人日志 条件查询(分页)
	public Object[] findWorkLogByCondition(WorkLog workLog, int pageNo,
			int pageSize, String pageStatus) {
		String hql = "";
		if (workLog == null) {
			workLog = new WorkLog();
		}
		Users loginUser = Util.getLoginUser();
		String pageString = " and userId=" + loginUser.getId();
		if (workLog.getWorkLogClass() != null) {
			hql = "from WorkLog w where w.workLogClass.id="
					+ workLog.getWorkLogClass().getId();

		} else {
			hql = totalDao.criteriaQueries(workLog, null);

		}

		if (pageStatus != null && pageStatus.length() > 0) {
			if ("dept".equals(pageStatus)) {
				pageString = "and dept='" + loginUser.getDept() + "'";

				hql += pageString + " order by addDateTime desc";
			} else if ("all".equals(pageStatus)) {
				pageString = "";
				hql += " order by addDateTime desc";

			} else if ("single".equals(pageStatus)) {
				pageString = " and userId=" + loginUser.getId()
						+ " ";
				hql += pageString + " order by addDateTime desc";
			} else if ("zhipai".equals(pageStatus)) {
				pageString = " and zpuserId=" + loginUser.getId()
						+ " and zpStatus='确认'";
				hql += pageString + " order by addDateTime desc";
			}
		}

		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	// 修改(处理事件 (办理中、已完成))
	public boolean updateWorkLog(WorkLog workLog) {
		if (workLog != null) {
			WorkLog oldWorkLog = (WorkLog) totalDao.get(WorkLog.class, workLog
					.getId());
			if (workLog.getJindu() != null && workLog.getJindu() > 0) {
				oldWorkLog.setJindu(workLog.getJindu());// 进度
			}
			if (workLog.getZptime() != null && workLog.getZptime().length() > 0) {
				oldWorkLog.setZptime(workLog.getZptime());// 规定任务完成日期
			}
			if (workLog.getContent() != null
					&& workLog.getContent().length() > 0) {
				oldWorkLog.setContent(workLog.getContent());// 内容
			}
			if (workLog.getTitle() != null && workLog.getTitle().length() > 0) {
				oldWorkLog.setTitle(workLog.getTitle());// 标题
			}
			if (workLog.getZpStatus() != null
					&& workLog.getZpStatus().length() > 0) {
				oldWorkLog.setZpStatus(workLog.getZpStatus());// 指派状态
			}
			if (workLog.getQrStatus() != null
					&& workLog.getQrStatus().length() > 0) {
				oldWorkLog.setQrStatus(workLog.getQrStatus());// 是否同意;
			}
			if (workLog.getLogStatus() != null
					&& workLog.getLogStatus().length() > 0) {
				oldWorkLog.setLogStatus(workLog.getLogStatus());// 办理状态;
			}
			if (workLog.getYjtime() != null && workLog.getYjtime().length() > 0) {
				oldWorkLog.setYjtime(workLog.getYjtime());// 预计交期
			}
			if ("已完成".equals(oldWorkLog.getLogStatus())) {
				String submitdatetime1 = oldWorkLog.getSubmitDateTime();
				int weekday = Util.getNowday();
				String beforedate = Util.getSpecifiedDayAfter(Util
						.getDateTime(), -1);
				if (weekday == 1) {
					beforedate = Util.getSpecifiedDayAfter(Util.getDateTime(),
							-3);
				}
				if (submitdatetime1 != null) {
					String submitdatetime2 = submitdatetime1.substring(0, 10);
					if (!submitdatetime2.equals(Util.getDateTime("yyyy-MM-dd"))
							&& !beforedate.equals(submitdatetime1.substring(0,
									10))) {
						if (oldWorkLog.getWgcount() == null) {
							oldWorkLog.setWgcount(1);
						} else {
							oldWorkLog.setWgcount(oldWorkLog.getWgcount() + 1);
						}
					}

				} else {
					String adddatetime = oldWorkLog.getAddDateTime().substring(
							0, 10);
					if (!beforedate.equals(adddatetime)
							&& !adddatetime.equals(Util
									.getDateTime("yyyy-MM-dd"))) {
						oldWorkLog.setWgcount(1);
					}
				}
				oldWorkLog.setSubmitDateTime(Util.getDateTime());// 提交时间;
				oldWorkLog.setEndDateTime(Util.getDateTime());// 结束时间;
			} else if ("办理中".equals(oldWorkLog.getLogStatus())) {

				String submitdatetime1 = oldWorkLog.getSubmitDateTime();
				int weekday = Util.getNowday();

				String beforedate = Util.getSpecifiedDayAfter(Util
						.getDateTime(), -1);
				if (weekday == 1) {
					beforedate = Util.getSpecifiedDayAfter(Util.getDateTime(),
							-3);
				}
				if (submitdatetime1 != null) {
					String submitdatetime2 = submitdatetime1.substring(0, 10);
					if (!submitdatetime2.equals(Util.getDateTime("yyyy-MM-dd"))
							&& !beforedate.equals(submitdatetime1.substring(0,
									10))) {
						if (oldWorkLog.getWgcount() == null) {
							oldWorkLog.setWgcount(1);
						} else {
							oldWorkLog.setWgcount(oldWorkLog.getWgcount() + 1);
						}
					}

				} else {
					String adddatetime = oldWorkLog.getAddDateTime().substring(
							0, 10);
					if (!beforedate.equals(adddatetime)
							&& !adddatetime.equals(Util
									.getDateTime("yyyy-MM-dd"))) {
						oldWorkLog.setWgcount(1);
					}
				}
				oldWorkLog.setSubmitDateTime(Util.getDateTime());
			}
			if ("指派".equals(oldWorkLog.getZpStatus())) {
				oldWorkLog.setUserId(workLog.getUserId());// 提交人id
				oldWorkLog.setUserName(workLog.getUserName());// 提交人姓名
				oldWorkLog.setDept(workLog.getDept());// 提交人部门
				oldWorkLog.setCode(workLog.getCode());
				;// 提交人工号;
				oldWorkLog.setCardId(workLog.getCardId());// 提交人工号ID;
				RtxUtil.sendNotify(oldWorkLog.getCode(), oldWorkLog
						.getContent(), oldWorkLog.getTitle(), "0", "1000*60*3");
			} else if ("确认".equals(oldWorkLog.getZpStatus())
					&& !"同意".equals(oldWorkLog.getQrStatus())) {
				System.out.println(oldWorkLog.getYjtime() + " "
						+ oldWorkLog.getZptime());
				if (oldWorkLog.getYjtime().equals(oldWorkLog.getZptime())) {
					oldWorkLog.setQrStatus("同意");
				}
				oldWorkLog.setAddDateTime(Util.getDateTime());
			}

			if (workLog.getRemarks() != null) {
				oldWorkLog.setRemarks(workLog.getRemarks().replaceAll("<",
						"&lt;").replaceAll(">", "&gt;"));// 备注
			}
			return totalDao.update(oldWorkLog);
		}

		return false;
	}

	// 删除日志
	public boolean delWorkLog(WorkLog workLog) {
		if (workLog != null) {
			return totalDao.delete(workLog);
		}
		return false;

	}

	// 个人完成情况
	@Override
	public List<WorkLog> findLogStatus() {
		String hql = "from WorkLog where userId=? and logStatus in ('办理中','待办') and zpStatus='确认'";
		Users loginUser = Util.getLoginUser();// 获得当前登录用户信息
		return totalDao.query(hql, loginUser.getId());

	}

	@Override
	public List<Users> findLogStatusDe() {
		String hql = "from WorkLog where userId=? and logStatus='办理中' and zpStatus='确认'";// 个人工作记录办理中情况
		Users loginUser = Util.getLoginUser();// 获得当前登录用户信息
		return totalDao.query(hql, loginUser.getId());
	}

	public List<Users> findLogStatusDeUser() {
		String hql = "from WorkLog where userId=? and logStatus='待办' and zpStatus='确认'";// 个人工作记录待办情况
		Users loginUser = Util.getLoginUser();// 获得当前登录用户信息

		return totalDao.query(hql, loginUser.getId());
	}
	public List<Users> findLogStatusDeUser0() {
		Users loginUser = Util.getLoginUser();// 获得当前登录用户信息
		String hql = "from WorkLog where userId="+loginUser.getId()+" and logStatus='待办' and zpStatus='确认'";// 个人工作记录待办情况
		return totalDao.findAllByPage(hql, 1, 3);
	}

	// 工作记录办理中情况
	@Override
	public List<Users> findlogStatusBi() {
		String hql = "from WorkLog where dept=? and logStatus='办理中'";// 部门工作记录办理中情况
		Users loginUser = Util.getLoginUser();// 获得当前登录用户信息
		return totalDao.query(hql, loginUser.getDept());
	}

	public List<Users> findlogStatusBiUser() {
		String hql = "from WorkLog where dept=? and logStatus='待办'";// 部门工作记录待办情况
		Users loginUser = Util.getLoginUser();// 获得当前登录用户信息
		return totalDao.query(hql, loginUser.getDept());
	}

	@Override
	public List<String> findDeptList() {
		// TODO Auto-generated method stub
		return totalDao
				.query("select  dept from  DeptNumber where dept is not null");
	}

	@Override
	public boolean addWorkLog1(WorkLog workLog) {
		if (workLog != null) {
			Users user = Util.getLoginUser();
			workLog.setZpname(user.getName());
			workLog.setZpdept(user.getDept());
			workLog.setZpuserId(user.getId());
			workLog.setAddZpDataTime(Util.getDateTime());// 添加时间
			workLog.setMouth(Util.getDateTime("yyyy-MM月"));// 月份
			workLog.setContent(workLog.getContent().replaceAll("<", " &lt; ")
					.replaceAll(">", " &gt; "));// 内容
			if ("指派".equals(workLog.getZpStatus())) {
				RtxUtil.sendNotify(workLog.getCode(), workLog.getContent(),
						workLog.getTitle(), "0", "1000*60*3");
			}

			return totalDao.save(workLog);
		}
		return false;
	}

	@Override
	public List<WorkLog> findLogStatusCao() {
		List<WorkLog> list = findLogStatus();
		List<WorkLog> list2 = new ArrayList<WorkLog>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String endtime = list.get(i).getSubmitDateTime();
				String startime = list.get(i).getAddDateTime();
				String zptime = list.get(i).getZptime();
				String submittime = list.get(i).getSubmitDateTime();
				if (submittime != null && zptime != null) {
					if (Util.compareTime(submittime, "yyyy-MM-dd HH:mm:ss",
							zptime, "yyyy-MM-dd HH:mm:ss")) {
						list2.add(list.get(i));
					}
				}
			}
		}
		return list2;
	}

	@Override
	public List<WorkLog> finddzpStatus() {
		Users user = Util.getLoginUser();
		String hql = "from WorkLog where zpuserId=? and zpStatus='待指派' order by addDateTime desc";
		return (List<WorkLog>) totalDao.query(hql, user.getId());
	}

	@Override
	public List<WorkLog> findwqrStatus() {
		Users user = Util.getLoginUser();
		String hql = "from WorkLog where userId=? and zpStatus='指派' order by addDateTime desc";
		return (List<WorkLog>) totalDao.query(hql, user.getId());
	}

	@Override
	public List<WorkLog> findzpStatus() {
		Users user = Util.getLoginUser();
		String hql = "from WorkLog where zpuserId=? and zpStatus='指派' order by addDateTime desc";
		return (List<WorkLog>) totalDao.query(hql, user.getId());
	}

	@Override
	public List<WorkLog> findqrStatus() {
		Users user = Util.getLoginUser();
		String hql = "from WorkLog where zpuserId=? and zpStatus='确认' order by addDateTime desc";
		return (List<WorkLog>) totalDao.query(hql, user.getId());
	}

}
