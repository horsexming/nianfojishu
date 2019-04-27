package com.task.ServerImpl.caiwu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.caiwu.MoneyTypeServer;
import com.task.entity.CompanyVIP;
import com.task.entity.caiwu.MoneyType;

public class MoneyTypeServerImpl implements MoneyTypeServer{
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	@Override
	public String addMoneyType(MoneyType moneyType) {
		if(moneyType!=null){
			String hql = "from MoneyType where name =?";
			MoneyType moneyType1 =	(MoneyType) totalDao.getObjectByCondition(hql, moneyType.getName());
			if(moneyType1 ==null){
				return totalDao.save(moneyType)+"";
			}
			return moneyType.getName()+"已添加过，无需重复添加!";
		}
		return "error";
	}

	@Override
	public boolean delMoneyType(MoneyType moneyType) {
		if(moneyType!=null){
			return totalDao.delete(moneyType);
		}
		return false;
	}

	@Override
	public boolean fanjinyong(Integer id) {
		if(id!=null && id>0){
			MoneyType moneyType =	(MoneyType) totalDao.get(MoneyType.class, id);
			moneyType.setStatus("使用");
			return totalDao.update(moneyType);
		}
			return false;	
	}

	@Override
	public List<MoneyType> findAllMoneyType() {
		
		return totalDao.find("from MoneyType where status <> '禁用'");
	}

	@Override
	public List<MoneyType> findAllMoneyTypeBypage(int pageNo, int pageSize) {
	
		return totalDao.findAllByPage("from MoneyType ", pageNo, pageSize);
	}

	@Override
	public MoneyType findMoneyTypeById(Integer id) {
		if(id!=null && id>0){
			return 	(MoneyType) totalDao.get(MoneyType.class, id);
		}
		return null;
	}

	@Override
	public Map<Integer, Object> findMoneyTypeCondition(MoneyType moneyType,
			int pageNo, int pageSize) {
		if(moneyType == null){
			moneyType = new MoneyType();
		}
		Map<Integer,Object> map=new HashMap<Integer, Object>();
		String hql=	totalDao.criteriaQueries(moneyType, null);
		int count=totalDao.getCount(hql);
		List<MoneyType> moneyTypeList=(List<MoneyType>)totalDao.findAllByPage(hql, pageNo, pageSize);
		map.put(1, moneyTypeList);
		map.put(2, count);
		return map;
	}

	@Override
	public int getcont() {
		
		return totalDao.getCount("from MoneyType");
	}

	@Override
	public boolean jinyong(Integer id) {
		if(id!=null && id>0){
			MoneyType moneyType =	(MoneyType) totalDao.get(MoneyType.class, id);
			moneyType.setStatus("禁用");
			totalDao.update(moneyType);
		}
		return false;
	}
  
	@Override
	public boolean updateMoneyType(MoneyType moneyType) {
		if(moneyType!=null){
			return	totalDao.update(moneyType);
		}
		return false;
	}

}
