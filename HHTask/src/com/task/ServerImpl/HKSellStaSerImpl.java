package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.HKSellStaServer;
import com.task.entity.TaHkSellSta;

public class HKSellStaSerImpl implements HKSellStaServer {
	private TotalDao totalDao;		
	public TotalDao getTotalDao() {
		return totalDao;
	}
	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	@Override
	public boolean deleteHKSellSta(Integer id) {
		// TODO Auto-generated method stub
		TaHkSellSta sta =(TaHkSellSta)totalDao.getObjectById(TaHkSellSta.class, id);
		String markId=sta.getHkSellMarkId();
		String sendID=sta.getHkSellSendId();
		String sql="update honghuESS.dbo.sell set sell_sendnum = null where sell_sendnum=? and sell_markId=?";
		try {
			totalDao.createQueryUpdate(null, sql,sendID,markId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalDao.delete(sta);
	}

	@Override
	public Object[] findHKSellSta(TaHkSellSta sta, String startDate,
			String endDate, int pageNo, int pageSize) {
		String hql="from TaHkSellSta ts where ts.taHkHuikuan.id is null ";
		if(null!=sta){
			if(null!=sta.getHkSellCumpanyName() && !"".equals(sta.getHkSellCumpanyName())){
				hql+=" and hkSellCumpanyName='"+sta.getHkSellCumpanyName()+"'";
			}
			if(null!=sta.getHkSellSendId() && !"".equals(sta.getHkSellSendId())){
				hql+=" and hkSellSendId='"+sta.getHkSellSendId()+"'";
			}
			if(null!=sta.getHkSellMarkId() && !"".equals(sta.getHkSellMarkId())){
				hql+=" and hkSellMarkId='"+sta.getHkSellMarkId()+"'";
			}
			if(null!=sta.getHkSellOrderId() && !"".equals(sta.getHkSellOrderId())){
				hql+=" and hkSellOrderId='"+sta.getHkSellOrderId()+"'";
			}
		}		
		if(null!=startDate && !"".equals(startDate) && null!=endDate && !"".equals(endDate)){
			hql+=" and hksellTime between '"+startDate+"' and '"+endDate+"'";
		}
		hql+=" order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count=totalDao.getCount(hql);
		Object[] hkSellStaArr = new Object[2];
		hkSellStaArr[0] = count;
		hkSellStaArr[1] = list;
		return hkSellStaArr;
	}

	@Override
	public TaHkSellSta getHKSellSta(Integer id) {
		// TODO Auto-generated method stub
		return (TaHkSellSta)totalDao.getObjectById(TaHkSellSta.class, id);
	}

	@Override
	public boolean updateHKSellSta(TaHkSellSta sta) {
		// TODO Auto-generated method stub
		TaHkSellSta st=(TaHkSellSta)totalDao.getObjectById(TaHkSellSta.class, sta.getId());
		st.setHkSellCumpanyName(sta.getHkSellCumpanyName());
		st.setHkSellCount(sta.getHkSellCount());
		st.setHkSellSendId(sta.getHkSellSendId());		
		return totalDao.update(st);
	}
	public String selectItem(String tag){
		String message="";
		if(null!=tag && !"".equals(tag)){
			String hql="select distinct(ts."+tag+") from TaHkSellSta ts where ts.taHkHuikuan.id is null";
			List<String> list = totalDao.query(hql);	
		    for(String d:list){
		    	message+=d.toString()+"|";
		    }
		}
		return message;
	}
}
