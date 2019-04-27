package com.task.ServerImpl.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.sys.LicenseMsgServer;
import com.task.entity.system.LicenseMsg;

public class LicenseMsgServerImpl implements LicenseMsgServer {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean add(LicenseMsg licenseMsg) {
		// TODO Auto-generated method stub

		return totalDao.save(licenseMsg);
	}

	@Override
	public boolean delete(LicenseMsg licenseMsg) {
		// TODO Auto-generated method stub
		return totalDao.delete(licenseMsg);
	}

	@Override
	public Map<Integer, Object> findCompanysByCondition(
			LicenseMsg licenseMsg, int pageNo, int pageSize) {
		if (licenseMsg == null) {
			licenseMsg = new LicenseMsg();
		}
		String hql = totalDao.criteriaQueries(licenseMsg, null, null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		List objs2=totalDao.query("from LicenseMsg where notAfter-getDate()<30 and notAfter-getDate()>=0");
		List objs3=totalDao.query("from LicenseMsg where notAfter-getDate()<0");
			
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		map.put(3, objs2);
		map.put(4, objs3);
		return map;
	}

	@Override
	public LicenseMsg getById(Integer id) {
		// TODO Auto-generated method stub
		Object o = totalDao.getObjectById(LicenseMsg.class, id);
		if (o != null) {
			LicenseMsg licenseMsg = (LicenseMsg) o;
			return licenseMsg;
		}
		return null;
	}

	@Override
	public boolean update(LicenseMsg licenseMsg) {
		// TODO Auto-generated method stub
		if (licenseMsg != null) {
			return totalDao.update(licenseMsg);
		}
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		Object o = totalDao.getObjectById(LicenseMsg.class, id);
		if (o != null) {
			LicenseMsg licenseMsg = (LicenseMsg) o;
			return totalDao.delete(licenseMsg);
		}
		return false;
	}


}
