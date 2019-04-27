package com.task.ServerImpl.shizhi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.shizhi.CusimportanceServer;
import com.task.entity.ClientManagement;
import com.task.entity.shizhi.Cusimportance;

public class CusimportanceServerImpl implements CusimportanceServer{
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		Cusimportance cusimportance=getById(id);
		if(cusimportance!=null){
			return totalDao.delete(cusimportance);
		}
		return false;
	}

	

	@Override
	public Cusimportance getById(Integer id) {
		// TODO Auto-generated method stub
		if(id!=null){
		 Object o=totalDao.getObjectById(Cusimportance.class, id);
		 if(o!=null){
			return (Cusimportance)o;
		 }
		}
		return null;
	}

	@Override
	public boolean update(Cusimportance cusimportance) {
		// TODO Auto-generated method stub
		if(cusimportance!=null){
			Cusimportance c=getById(cusimportance.getId());
			if(c!=null){
				//设置普通属性
				c.setCuMonthSale(cusimportance.getCuMonthSale());
				c.setCuYearSale(cusimportance.getCuYearSale());
				c.setThreeYearsExSale(cusimportance.getThreeYearsExSale());
				if(cusimportance.getCuMonthSale()!=null
						&cusimportance.getCuYearSale()!=null
						&cusimportance.getThreeYearsExSale()!=null){
					float improtance=cusimportance.getCuMonthSale()+cusimportance.getCuYearSale()+cusimportance.getThreeYearsExSale();
					c.setImprotance(Float.parseFloat(String.format("%.3f", improtance)));
					
				}else{
					c.setImprotance(0f);
				}
				return totalDao.update(c);
			}
		}
		return false;
	}

	@Override
	public Map<Integer, Object> findCusimportancesByCondition(Cusimportance cusimportance,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (cusimportance == null) {
			cusimportance = new Cusimportance();
		}
		String hql = totalDao.criteriaQueries(cusimportance, null, null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public List<Cusimportance> findAll() {
		// TODO Auto-generated method stub
		List<Object> all=totalDao.query("from Cusimportance");
		if(all.size()>0){
			List<Cusimportance> skillScores=new ArrayList<Cusimportance>();
			for(Object o:all){
				skillScores.add((Cusimportance)o);
			}
			return skillScores;
		}

		return null;
	}




//	@Override
//	public boolean addUnintoData() {
//		// TODO Auto-generated method stub
//		String sql = "from ClientManagement where id not in (select cuId from Cusimportance)";
//		List clist = totalDao.query(sql);
//		boolean b = true;
//		if (clist!=null) {//添加未和QuotedProcessInfor表同步的值
//			for (int i = 0; i < clist.size(); i++) {
//				Cusimportance cus=new Cusimportance();
//				ClientManagement client=(ClientManagement) clist.get(i);
//				cus.setCuId(client.getId());
//				if(client.getCompanyAbbreviation()!=null&&!"".equals(client.getCompanyAbbreviation())){//公司简称为空时剔除
//				cus.setCuName(client.getCompanyAbbreviation());
//			    b=b&totalDao.save(cus);
//				}
//			}
//		}
//		return b;
//	}
//	@Override
//	public boolean updateAll() {
//		// TODO Auto-generated method stub
//		boolean b=true;
//		b=b&this.deleteMoreData();
//		b=b&this.addUnintoData();
//		b=b&this.updateHadData();
//		return b;
//	}
//
//	@Override
//	public boolean deleteMoreData() {
//		// TODO Auto-generated method stub
//		String sql="from Cusimportance where cuId not in (select id from ClientManagement)";
//		List list = totalDao.query(sql);
//		boolean b=true;
//		if(list.size()>0){
//		for (int i = 0; i < list.size(); i++) {
//			Cusimportance c=(Cusimportance) list.get(i);
//			b=b&totalDao.delete(c);
//		 }
//		}
//
//		return b;
//	}

//	@Override
//	public boolean updateHadData() {
//		// TODO Auto-generated method stub
//
//		
//		String sql1="from ClientManagement";
//		List list1 = totalDao.query(sql1);
//		List<Cusimportance> list2 = findAll();
//		List<ClientManagement> cmlist=new ArrayList<ClientManagement>();
//		List<Cusimportance> cilist=new ArrayList<Cusimportance>();
//		if(list1.size()>0&list2!=null){
//			cmlist=list1;
//			cilist=list2;
//			boolean b=true;
//			for(ClientManagement cm:cmlist){
//				for(Cusimportance ci:cilist){
//					if(cm.getId().equals(ci.getCuId())){
//						if(cm.getCompanyAbbreviation()==null||"".equals(cm.getCompanyAbbreviation())){//当公司简称没有时剔除
//							 b=b&totalDao.delete(ci);
//						}else{
//						    ci.setCuName(cm.getCompanyAbbreviation());
//						    b=b&totalDao.update(ci);
//						}
//					}
//				}
//			}
//			return b;
//		}
//		
//		return false;
//	}

}
