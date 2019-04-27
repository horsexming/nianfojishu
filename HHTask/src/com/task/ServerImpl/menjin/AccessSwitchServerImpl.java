package com.task.ServerImpl.menjin;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.menjin.AccessSwitchServer;
import com.task.entity.Users;
import com.task.entity.menjin.AccessSwitch;
import com.task.util.Util;

public class AccessSwitchServerImpl implements AccessSwitchServer {

	private TotalDao totalDao;

	@Override
	public String addAccessSwitch(AccessSwitch accessSwitch) {
		// TODO Auto-generated method stub
		Users users = Util.getLoginUser();
		if(users!=null){
			accessSwitch.setName(users.getName());
		}
		accessSwitch.setAddTime(Util.getDateTime());
		if(totalDao.save(accessSwitch)){
			return "添加成功！";
		}
		return "对象为空，添加失败！";
		
	}

	@Override
	public AccessSwitch byIdAccessSwitch(Integer id) {
		// TODO Auto-generated method stub
		return (AccessSwitch) totalDao.getObjectById(AccessSwitch.class, id);
	}

	@Override
	public String deleteAccessSwitch(Integer id) {
		// TODO Auto-generated method stub
		AccessSwitch obje = byIdAccessSwitch(id);
		if (obje != null) {
//			if (totalDao.delete(obje)) return "删除成功！";
//			else return "删除失败！";
		}
		return "对象为空，删除失败！";
	}

	@Override
	public Object[] findAccessSwitch(AccessSwitch accessSwitch, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		if (accessSwitch == null) {
			accessSwitch = new AccessSwitch();
		}
		String hql = totalDao.criteriaQueries(accessSwitch, null);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);// 总条数
		Object [] o = {list ,count};
		return o;
	}

	@Override
	public String updateAccessSwitch(AccessSwitch fingerprintMg) {
		// TODO Auto-generated method stub
		return null;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}


}
