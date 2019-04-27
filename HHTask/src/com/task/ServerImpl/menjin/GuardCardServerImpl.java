package com.task.ServerImpl.menjin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.task.Dao.TotalDao;
import com.task.Server.menjin.GuardCardServer;
import com.task.entity.menjin.GuardCard;
import com.task.util.Util;

public class GuardCardServerImpl implements GuardCardServer{
     private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
 //添加
	@Override
	public boolean addGuardCard(GuardCard guardCard) {
		guardCard.setAddTime(Util.getDateTime());
		boolean b=this.totalDao.save(guardCard);
		return b;
	}
 //查询
	@Override
	public Map<Integer, Object> findGuardCardByCondition(GuardCard guardCard,
			int parseInt, int pageSize) {
		if(guardCard ==null){
			guardCard =new GuardCard();
		}
		String hql =totalDao.criteriaQueries(guardCard, null);
		hql += " order by id desc";
		List list=totalDao.findAllByPage(hql, parseInt, pageSize );
		int count= totalDao.getCount(hql);
		Map<Integer, Object> map=new HashMap<Integer, Object>();
		map.put(1,list);
		map.put(2,count);
		return map;
	}

 //删除
	@Override
	public boolean deleteGuardCard(GuardCard guardCard) {
		boolean b=false;
		GuardCard guardCard2=(GuardCard) this.totalDao.getObjectById(GuardCard.class, guardCard.getId());
		if(guardCard2!=null){
			b=this.totalDao.delete(guardCard2);
		}
		return b;
	}
	
 //id获取
	@Override
	public GuardCard getByIdGuardCard(GuardCard guardCard) {
		GuardCard guardCard2 =(GuardCard) this.totalDao.getObjectById(GuardCard.class, guardCard.getId()); 
		return guardCard2;
	}
//修改
	@Override
	public boolean updateGuardCard(GuardCard guardCard) {
		GuardCard guardCard2=(GuardCard) this.totalDao.getObjectById(GuardCard.class, guardCard.getId());
		guardCard2.setUpdateTime(Util.getDateTime());
		guardCard2.setCode(guardCard.getCode());
		guardCard2.setName(guardCard.getName());
		guardCard2.setSex(guardCard.getSex());
		guardCard2.setNation(guardCard.getNation()); 
		guardCard2.setBirthplace(guardCard.getBirthplace()); 
		guardCard2.setDept(guardCard.getDept()); 
		guardCard2.setCardId(guardCard.getCardId());
		guardCard2.setUid(guardCard.getUid());	
		boolean b=this.totalDao.update(guardCard2);
		return b;
	}
     
}
