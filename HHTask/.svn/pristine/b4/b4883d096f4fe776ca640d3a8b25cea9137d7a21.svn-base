package com.task.ServerImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.IRenewService;
import com.task.entity.Renew;
import com.task.entity.Store;

public class RenewServiceImpl implements IRenewService {
	
	private TotalDao totalDao;
	
	@Override
	public void add(Renew re) {
		// TODO Auto-generated method stub
		totalDao.save(re);
	}

	@Override
	public void del(Renew re) {
		// TODO Auto-generated method stub
		totalDao.delete(re);
	}

	@Override
	public Renew getRenewById(int id) {
		// TODO Auto-generated method stub
		return (Renew) totalDao.getObjectById(Renew.class, id);
	}

	@Override
	public Object[] queryRenew(Map map, int pageNo, int pageSize) {
		String hql = "from Renew r where 1=1 ";
		if (map != null) {
			if (map.get("number") != null) 
				hql += " and r.exAppNumber like '%" + map.get("number") + "%'";
			if(map.get("standard") != null)
				hql += " and r.exFormat like '%" + map.get("standard") + "%'";
			if (map.get("name") != null) 
				hql += " and r.exMetatag like '%" + map.get("name") + "%'";
			if(map.get("storehouse") != null)
				hql += " and r.exStore like '%" +map.get("storehouse") + "%'";
			if(map.get("jobNum")!=null)
				hql += " and r.exUserJobNum like '%" + map.get("jobNum") + "%'";
			if(map.get("jobName") !=null)
				hql += " and r.exUser like '%" + map.get("jobName") + "%'";
			if(map.get("devicename") != null)
				hql += " and r.exObj like '%" + map.get("devicename") + "%'";
			if(map.get("place") !=null)
				hql += " and r.exPosition like '%" + map.get("place") + "%'";
			if(map.get("category") !=null)
				hql += " and r.exClass like '%" + map.get("category") + "%'";
			if(map.get("startTime") != null && map.get("endTime") != null)
				hql += " and (r.exDate between '" + map.get("startTime") + "' and '" + map.get("endTime") + "')";
		}
		hql += " order by r.id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		return new Object[]{list,count};
	}

	@Override
	public String update(Renew re) {
		String msg = "";
		Renew oldRe = getRenewById(re.getId());
		Store sto = oldRe.getStore();
		float amount = sto.getCurAmount()+oldRe.getExCount();
		if(amount >= re.getExCount()){
			sto.setCurAmount(amount - re.getExCount());
			BeanUtils.copyProperties(re, oldRe, new String[] {"id","store"});
			msg = "修改成功!";
			totalDao.update(oldRe);
		}else{
			msg = "修改失败!修改数量大于当前库存数量!当前库存量为:" + sto.getCurAmount();
		}
		return msg;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public String add(Renew re, Integer storeId) {
		Store store = getStoreById(storeId);
		String msg = "";
		if(store.getCurAmount() >= re.getExCount()){
			store.setCurAmount(store.getCurAmount()-re.getExCount());
			re.setStore(store);
			store.getRenews().add(re);
			totalDao.save(re);
			totalDao.update(store);
		}else{
			msg = "更换失败!当前可更换数量为: " + store.getCurAmount();
		}
		return msg;
	}

	@Override
	public Store getStoreById(Integer id) {
		return (Store) totalDao.getObjectById(Store.class, id);
	}

	@Override
	public void delRenewById(Integer id) {
		String msg = "";
		Renew re = getRenewById(id);
		Store sto = re.getStore();
		sto.setCurAmount(sto.getCurAmount()+re.getExCount());
		sto.getMaintains().remove(re);
		re.setStore(null);
		del(re);
	}

	


}
