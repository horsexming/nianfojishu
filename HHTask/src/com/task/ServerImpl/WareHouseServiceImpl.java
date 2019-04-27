package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.WareHouseService;
import com.task.entity.WareHouse;
import com.task.entity.WareHouseAuth;

public class WareHouseServiceImpl implements WareHouseService {
	private TotalDao totalDao;

	@Override
	public void add(WareHouse house) {
		totalDao.save(house);
	}

	@Override
	public List<WareHouse> getAll() {
		String hql = "from WareHouse";
		return totalDao.find(hql);
	}

	@Override
	public void delete(WareHouse house) {
		WareHouse h = (WareHouse) totalDao.get(house.getClass(), house.getId());
		List<WareHouseAuth> list = totalDao.find("from WareHouseAuth where auth like ?", new Object[]{h.getCode()});
		for (WareHouseAuth wareHouseAuth : list) {
			String s = wareHouseAuth.getAuth();
			s = s.replaceAll(h.getCode()+ "_[a-zA-Z]+", "");
			if(s.startsWith(",")){
				s = s.substring(1);
			}
			if(s.endsWith(",")){
				s = s.substring(0,s.length()-1);
			}
			s = s.replaceAll(",+", ",");
			
			wareHouseAuth.setAuth(s);
		}
		totalDao.delete(h);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
