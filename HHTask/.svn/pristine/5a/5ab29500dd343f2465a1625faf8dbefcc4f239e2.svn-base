package com.task.ServerImpl.renshi;

/*
 * 李聪   2015-06-24
 * 
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.renshi.Dimission_HandoverServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Users;
import com.task.entity.renshi.DimissionLog;
import com.task.entity.renshi.Dimission_Handover;
import com.task.util.Util;

public class Dimission_HandoverServerImpl implements Dimission_HandoverServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/**
	 * 添加离职交接单对象
	 * 
	 * @param Dimission_Handover
	 * @return
	 */

	@Override
	public boolean addDimission_Handover(Dimission_Handover dimission_Handover) {
		// TODO Auto-generated method stub
		boolean bool = false;
		if (dimission_Handover != null) {
			// Users loguser = Util.getLoginUser();//获取用户信息
			String createdate1 = Util.getDateTime("yyyyMMdd");
			// String[] a = createdate1.split("-");//将字符串以-分隔，一次放入数组a中
			// createdate1 = a[0] + a[1] + a[2];
			String hql = "select max(dimission_number) from Dimission_Handover WHERE dimission_number LIKE "
					+ "'" + createdate1 + "%'";
			String max_number = (String) this.totalDao
					.getObjectByCondition(hql);
			if (max_number != null && !"".equals(max_number)) {
				long number2 = Long.parseLong(max_number) + 1;
				String number3 = Long.toString(number2);
				dimission_Handover.setDimission_number(number3);
			} else {
				String number2 = createdate1 + "001";
				dimission_Handover.setDimission_number(number2);
			}
			dimission_Handover.setLzjj_status("未审核");
			dimission_Handover.setAddTime(Util.getDateTime());
			bool = totalDao.save(dimission_Handover);
			//成功添加交接单后将申请单状态改为已填写
			if (bool) {
				DimissionLog dimissionLog = (DimissionLog) totalDao.getObjectById(
						DimissionLog.class, Integer.parseInt(dimission_Handover
								.getTa_dimissionLog_id()));
				dimissionLog.setHand_status("已填写");
			}
			String processName = "离职交接单";
			Integer epId = null;
			try {
				epId = CircuitRunServerImpl
						.createProcess(processName, Dimission_Handover.class,
								dimission_Handover.getId(), "lzjj_status",
								"id",
								"dimission_HandoverAction_toselect.action?dimissionHandover.id="
										+ dimission_Handover.getId(),
								dimission_Handover.getDept() + "部门 "
										+ dimission_Handover.getName()
										+ " 离职交接单，请您审批！", true);
				if (epId != null && epId > 0) {
					dimission_Handover.setEpId(epId);
					totalDao.update(dimission_Handover);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bool;
	}

	/**
	 * 通过id删除离职交接单对象
	 * 
	 * @param Dimission_Handover
	 * @return
	 */
	@Override
	public boolean deleteDimission_Handover(Integer id) {
		// TODO Auto-generated method stub
		Dimission_Handover dimission_Handover = getDimission_HandoverById(id);
		if (dimission_Handover != null) {
			CircuitRunServerImpl.deleteCircuitRun(dimission_Handover.getEpId());
			DimissionLog dimissionLog = (DimissionLog) totalDao.getObjectById(
					DimissionLog.class, Integer.parseInt(dimission_Handover
							.getTa_dimissionLog_id()));
			if (dimissionLog!=null) {
				dimissionLog.setHand_status("待填写");
				return totalDao.delete(dimission_Handover);
			}
		}
		return false;
	}

	/**
	 * 修改离职交接单对象
	 * 
	 * @param Dimission_Handover
	 * @return
	 */
	@Override
	public boolean updateDimission_Handover(Dimission_Handover dimissionHandover) {
		// TODO Auto-generated method stub
		Dimission_Handover dim_H = getDimission_HandoverById(dimissionHandover
				.getId());
		if (dim_H != null) {
			dim_H.setDimission_Reason(dimissionHandover.getDimission_Reason());
			dim_H.setUpdateTime(Util.getDateTime());
			if ("打回".equals(dim_H.getLzjj_status())) {
				CircuitRunServerImpl.updateCircuitRun(dim_H.getEpId());
				dim_H.setLzjj_status("审批中");
			}
			return totalDao.update(dim_H);
		}
		return false;
	}

	/**
	 * 通过id获取离职交接单对象
	 * 
	 * @param dim_Hission_Handover
	 * @return
	 */
	@Override
	public Dimission_Handover getDimission_HandoverById(Integer id) {
		// TODO Auto-generated method stub
		Object o = totalDao.getObjectById(Dimission_Handover.class, id);
		if (o != null) {
			return (Dimission_Handover) o;
		}
		return null;
	}

	/**
	 * 按条件分页查询离职交接单
	 * 
	 * @param Dimission_Handover
	 *            对象
	 * @param pageNo
	 *            起始页
	 * @param pageSize
	 *            每页数量
	 * @return
	 */

	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, Object> findDimission_HandoversByCondition(
			Dimission_Handover dimissionHandover, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (dimissionHandover == null) {
			dimissionHandover = new Dimission_Handover();
		}
		String hql = totalDao.criteriaQueries(dimissionHandover, null);
		hql += " order by id desc";
		List listInt = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		// Object[] o = { list, count };
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listInt);
		map.put(2, count);
		return map;
	}

	// 查询所交接单对象
	@SuppressWarnings("unchecked")
	@Override
	public List<Dimission_Handover> findAll() {
		// TODO Auto-generated method stub
		List all = totalDao.query("from Dimission_HandoverById");
		if (all.size() > 0) {
			return (List<Dimission_Handover>) all;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, Object> findDimission_HandoversBycodeCondition(
			Dimission_Handover dimissionHandover, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (dimissionHandover == null) {
			dimissionHandover = new Dimission_Handover();
		}
		Users users = Util.getLoginUser();
		String sql = " codeId='"+users.getId()+"'";
		String hql = totalDao.criteriaQueries(dimissionHandover, sql);
		hql += " order by id desc";
		List listInt = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		// Object[] o = { list, count };
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listInt);
		map.put(2, count);
		return map;
	}

}
