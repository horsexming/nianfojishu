package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.UnitManagerService;
import com.task.entity.UnitManager;
import com.task.entity.android.pscs.Customer;
import com.task.util.Util;

public class UnitManagerServiceImpl implements UnitManagerService {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/*
	 * 
	 * 查询所有单位信息(non-Javadoc)
	 * 
	 * @seecom.task.Server.UnitManagerService#findCustomerAll(com.task.entity.
	 * UnitManager, int, int)
	 */
	@Override
	public Object[] findCustomerAll(UnitManager manager, int parseInt,
			int pageSize) {
		if (manager == null) {
			manager = new UnitManager();
		}
		String sql = "select * from ta_unitmanager  where 1=1 and (type is null or type='' or type='unit')";
		if (!"".equals(manager.getUnitname()) && manager.getUnitname() != null) {
			sql += " and unitname like '%" + manager.getUnitname() + "%'";
		}
		String sql1 = sql + "order by id desc";
		List list1 = totalDao.findBySql(sql1, parseInt, pageSize);
		int count = totalDao.findBySql(sql).size();// 总行数
		Object[] o = { list1, count };
		return o;
	}

	@Override
	public Object[] findCaizhiAll(UnitManager manager, int parseInt,
			int pageSize) {
		if (manager == null) {
			manager = new UnitManager();
		}
		String sql = "select * from ta_unitmanager  where type='material' ";
		if (!"".equals(manager.getUnitname()) && manager.getUnitname() != null) {
			sql += " and unitname like '%" + manager.getUnitname() + "%'";
		}
		String sql1 = sql + "order by id desc";
		List list1 = totalDao.findBySql(sql1, parseInt, pageSize);
		int count = totalDao.findBySql(sql).size();// 总行数
		Object[] o = { list1, count };
		return o;
	}

	/*
	 * 添加单位信息 (non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.UnitManagerService#addUnitManager(com.task.entity.UnitManager
	 * )
	 */
	@Override
	public void addUnitManager(UnitManager manager) {
		// TODO Auto-generated method stub
		String date1 = Util.getDateTime();
		manager.setUnitdate(date1);
		this.totalDao.save(manager);

	}

	/*
	 * 
	 * 删除单位信息(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.UnitManagerService#delUnitManager(com.task.entity.UnitManager
	 * )
	 */
	@Override
	public void delUnitManager(UnitManager manager) {
		// TODO Auto-generated method stub
		UnitManager manager2 = (UnitManager) this.totalDao.getObjectById(
				UnitManager.class, manager.getId());
		this.totalDao.delete(manager2);

	}

	/*
	 * 
	 * 根据编号查询(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.UnitManagerService#findUnitManagerById(java.lang.Integer)
	 */
	@Override
	public UnitManager findUnitManagerById(Integer unitId) {
		// TODO Auto-generated method stub
		UnitManager manager = (UnitManager) this.totalDao.getObjectById(
				UnitManager.class, unitId);
		return manager;
	}

	/*
	 * 
	 * 修改单位信息(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.UnitManagerService#updateUnitManager(com.task.entity.
	 * UnitManager)
	 */
	@Override
	public void updateUnitManager(UnitManager manager) {
		String date1 = Util.getDateTime();
		UnitManager manager2 = (UnitManager) this.totalDao.getObjectById(
				UnitManager.class, manager.getId());
		manager2.setUnitname(date1);
		manager2.setUnitname(manager.getUnitname());
		this.totalDao.update(manager2);
	}

	/*
	 * 
	 * 查询所有单位(non-Javadoc)
	 * 
	 * @see com.task.Server.UnitManagerService#findUninAll()
	 */
	@Override
	public List findUninAll(String pageStatus) {
		// TODO Auto-generated method stub
		String hql = "from UnitManager where unitname is not null and unitname <> ''";
		if (pageStatus == null || pageStatus.length() == 0) {
			hql += " and (type is null or type = '' or type = 'unit') order by unitname";
			return totalDao.query(hql);
		} else {
			hql += " and  type = ? order by unitname";
			return totalDao.query(hql, pageStatus);
		}
	}

}
