package com.task.ServerImpl.renshi;

/*
 * 李聪   2015-05-08
 * 
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.renshi.DormitoryLogServer;
import com.task.Server.sys.CircuitRunServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Users;
import com.task.entity.renshi.DormitoryLog;
import com.task.util.Util;

public class DormitoryLogServerImpl implements DormitoryLogServer {
	private TotalDao totalDao;
	private CircuitRunServer circuitRunServer;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public CircuitRunServer getCircuitRunServer() {
		return circuitRunServer;
	}

	public void setCircuitRunServer(CircuitRunServer circuitRunServer) {
		this.circuitRunServer = circuitRunServer;
	}

	@Override
	public String addDormitoryLog(DormitoryLog dormitoryLog) {
		// TODO Auto-generated method stub
		boolean bool = false;
		if (dormitoryLog != null) {
			Users loguser = Util.getLoginUser();// 获取用户信息
			DormitoryLog oldDormitorLog = findDormitoryLogByCodeId(loguser
					.getId());
			if (oldDormitorLog != null) {
				return "由于您当前有正在审核或已通过的宿舍申请单，无法重复提交！！！";
			}
			String createdate1 = Util.getDateTime("yyyyMMdd");
			// String[] a = createdate1.split("-");//将字符串以-分隔，一次放入数组a中
			// createdate1 = a[0] + a[1] + a[2];
			String hql = "select max(shenqing_number) from DormitoryLog WHERE shenqing_number LIKE "
					+ "'" + createdate1 + "%'";
			String max_number = (String) this.totalDao
					.getObjectByCondition(hql);
			if (max_number != null && !"".equals(max_number)) {
				long number2 = Long.parseLong(max_number) + 1;
				String number3 = Long.toString(number2);
				dormitoryLog.setShenqing_number(number3);
			} else {
				String number2 = createdate1 + "001";
				dormitoryLog.setShenqing_number(number2);
			}
			dormitoryLog.setStatus("未审核");
			dormitoryLog.setAddTime(Util.getDateTime());
			bool = totalDao.save(dormitoryLog);

//			String processName = dormitoryLog.getDept() + "部门单身宿舍申请";
			String processName = "单身宿舍申请";
			Integer epId = null;
			try {
				epId = CircuitRunServerImpl.createProcess(processName,
						DormitoryLog.class, dormitoryLog.getId(), "status",
						"id",
						"dormitoryLogAction_toselete.action?dormitoryLog.id="
								+ dormitoryLog.getId(), dormitoryLog.getDept()
								+ "部门 " + dormitoryLog.getName()
								+ " 单身宿舍申请，请您审批", true);
				if (epId != null && epId > 0) {
					dormitoryLog.setEpId(epId);
					totalDao.update(dormitoryLog);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (bool) {
				return "true";
			}
		}
		return "数据异常，请检查后再试！";
	}

	/**
	 * 通过id删除宿舍申请单对象
	 * 
	 * @param interviewLog
	 * @return
	 */

	@Override
	public boolean deleteDormitoryLog(Integer id) {
		// TODO Auto-generated method stub
		DormitoryLog ivl = getDormitoryLogById(id);// 通过接口获取该id对象
		if (ivl != null) {
			CircuitRunServerImpl.deleteCircuitRun(ivl.getEpId());
			return totalDao.delete(ivl);
		}
		return false;
	}

	/**
	 * 按条件分页查询宿舍申请单
	 * 
	 * @param dormitory
	 *            对象
	 * @param pageNo
	 *            起始页
	 * @param pageSize
	 *            每页数量
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, Object> findDormitoryLogsByCondition(
			DormitoryLog dormitoryLog, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (dormitoryLog == null) {
			dormitoryLog = new DormitoryLog();
		}
		String sql = null;

		String hql = totalDao.criteriaQueries(dormitoryLog, sql);
		hql += " order by id desc";
		List listInt = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);

		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listInt);
		map.put(2, count);
		return map;
	}

	/**
	 * 通过id获取宿舍申请单对象
	 * 
	 * @param interviewLog
	 * @return
	 */
	@Override
	public DormitoryLog getDormitoryLogById(Integer id) {
		// TODO Auto-generated method stub
		Object o = totalDao.getObjectById(DormitoryLog.class, id);
		if (o != null) {
			return (DormitoryLog) o;
		}
		return null;
	}

	/**
	 * 修改宿舍申请单对象
	 * 
	 * @param interviewLog
	 * @return
	 */
	@Override
	public boolean updateDormitoryLog(DormitoryLog dormitoryLog) {
		// TODO Auto-generated method stub
		DormitoryLog dor = getDormitoryLogById(dormitoryLog.getId());
		if (dor != null) {
			// 需要修改的内容
			dor.setAge(dormitoryLog.getAge());
			dor.setSex(dormitoryLog.getSex());
			dor.setIdentity_id(dormitoryLog.getIdentity_id());
			dor.setStartTime(dormitoryLog.getStartTime());
			dor.setEndTime(dormitoryLog.getEndTime());
			dor.setDept(dormitoryLog.getDept());
			dor.setCode(dormitoryLog.getCode());
			dor.setContract_number(dormitoryLog.getContract_number());
			dor.setIsAgree(dormitoryLog.getIsAgree());
			dor.setUpdateTime(Util.getDateTime());
			if ("打回".equals(dor.getStatus())) {
				CircuitRunServerImpl.updateCircuitRun(dor.getEpId());
				dor.setStatus("审批中");
			}
			return totalDao.update(dor);
		}
		return false;
	}

	/**
	 * 查询所有宿舍申请单对象
	 * 
	 * @param interviewLog
	 * @return
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<DormitoryLog> findAll() {
		// TODO Auto-generated method stub
		List all = totalDao.query("from DormitoryLogById");
		if (all.size() > 0) {
			return (List<DormitoryLog>) all;
		}
		return null;
	}

	@Override
	public DormitoryLog findDormitoryLogByCodeId(Integer integer) {
		// TODO Auto-generated method stub
		if (integer != null && integer > 0) {
			String hql = "from DormitoryLog where codeId=? and status in ('未审核','审批中','已审批')";
			return (DormitoryLog) totalDao.getObjectByCondition(hql, integer);
		}
		return null;
	}

	//根据userID查找本人的申请单
	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, Object> findDormitoryLogsBycodeCondition(
			DormitoryLog dormitoryLog, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (dormitoryLog == null) {
			dormitoryLog = new DormitoryLog();
		}
		Users users = Util.getLoginUser();
		String sql = " codeId='" + users.getId() + "'";
		String hql = totalDao.criteriaQueries(dormitoryLog,sql);
		hql += " order by id desc";
		List listInt = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);

		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listInt);
		map.put(2, count);
		return map;
	}

}
