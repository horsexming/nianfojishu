package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.SopGongwei;
import com.task.entity.ProductProcess;
import com.task.entity.Screen;
import com.task.entity.TaSopGongwei;

public class SopGongweiImpl implements SopGongwei {
	private TotalDao totalDao;
	 
	public TotalDao getTotalDao() {
		return totalDao;
	}
	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	//添加工位信息
	public boolean save(TaSopGongwei gongwei) {
		// TODO Auto-generated method stub
		if(null!=gongwei){		
			return totalDao.save(gongwei);
		}
		return false;
	}
	@Override
	public Object[] selectGongxu(TaSopGongwei gongwei, Integer cpage,
			Integer pageSize) {
		String hql="from TaSopGongwei order by banzu asc";
		if(null!=gongwei){
			hql=totalDao.criteriaQueries(gongwei, "", null);
		}
		Object [] productAarr=new Object[2];
		Integer count=totalDao.query(hql).size();
		List list=totalDao.findAllByPage(hql, cpage, pageSize);
		productAarr[0]=count;
		productAarr[1]=list;
		return productAarr;
	}
	@Override
	public boolean deleteGongwei(TaSopGongwei gongwei) {
		// TODO Auto-generated method stub
		return totalDao.delete(gongwei);
	}
	@Override
	public TaSopGongwei getGongwei(Integer id) {
		// TODO Auto-generated method stub
		return (TaSopGongwei)totalDao.getObjectById(TaSopGongwei.class, id);
	}
	@Override
	public boolean updateGongwei(TaSopGongwei gongwei) {
		// TODO Auto-generated method stub
		/*
		 * 后期关联工序，更改工位时修改工序参数
		*/
		TaSopGongwei sopgongwei=(TaSopGongwei)totalDao.getObjectById(TaSopGongwei.class, gongwei.getId());
		BeanUtils.copyProperties(gongwei, sopgongwei, new String[]{"productProcess"});
		Set <ProductProcess> sparr= sopgongwei.getProductProcess();
		for(Iterator <ProductProcess> iterator=sparr.iterator();iterator.hasNext();){
			if(null!=iterator.next()){
				//修改工序的参数
				ProductProcess process=iterator.next();
				process.setOPtechnologyRate(sopgongwei.getCaozJineng());
				process.setOPCouldReplaceRate(sopgongwei.getCaoztihuanrenshu());
				process.setOPnoReplaceRate(1/sopgongwei.getCaoztihuanrenshu());
				process.setGZtechnologyRate(sopgongwei.getGongzhuangJineng());
				process.setGZCouldReplaceRate(sopgongwei.getGongzhuangtihuanrenshu());
				process.setGZnoReplaceRate(1/sopgongwei.getGongzhuangtihuanrenshu());
				process.setGZfuheRate(sopgongwei.getGongzhuangFuhe());
			    totalDao.update(process);
			}
		}
		return totalDao.update(sopgongwei);
	}
	@Override
	public List getWorkStations(Integer id) {
		List list = null;
		if(id == null){
			//String sql = "select  MAX(id) id, gongweihao from ta_sop_gongwei where id is not null and id <> '' and gongweihao is not null  and gongweihao <> '' GROUP BY gongweihao  order by gongweihao";
			String hql="select id,gongweihao from TaSopGongwei where  gongweihao is not NULL AND gongweihao <>''";
			list = totalDao.find(hql);
		} else {
			List<Integer> l = new ArrayList<Integer>();
			Screen s = (Screen) totalDao.get(Screen.class, id);
			for (TaSopGongwei taSopGongwei : s.getGongweis()) {
				l.add(taSopGongwei.getId());
			}
			String hql = "select id, gongweihao from TaSopGongwei where id in(:ids) and gongweihao is not NULL AND gongweihao <>''";
			Map<String, Object> params = new HashMap<String,Object>();
			params.put("ids", l);
			list = totalDao.find(hql, params);
		}
		
		return list;
	}
	
}
