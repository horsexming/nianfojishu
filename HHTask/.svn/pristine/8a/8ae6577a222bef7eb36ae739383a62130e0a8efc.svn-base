package com.task.ServerImpl.shizhi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.Server.shizhi.BonusShiZhiServer;
import com.task.entity.shizhi.BonusShiZhi;
import com.task.entity.shizhi.ProTryMakeScore;
import com.task.entity.shizhi.TryMake;

public class BonusShiZhiServerImpl implements BonusShiZhiServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean add(BonusShiZhi bonusShiZhi) {
		// TODO Auto-generated method stub
		boolean b=true;
		String month=bonusShiZhi.getMonth();
		Float money=bonusShiZhi.getBonus();
		b=b&totalDao.save(bonusShiZhi);
		b=b&getTryMakeRateByMonth(month, money);
		return b;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		boolean b=true;
		BonusShiZhi bonus = getById(id);
		if (bonus != null) {
			String month=bonus.getMonth();
			b=b& totalDao.delete(bonus);
			b=b&getTryMakeRateByMonth(month, null);
			return b;
		}
		return false;
	}

	@Override
	public BonusShiZhi getById(Integer id) {
		// TODO Auto-generated method stub
		if (id != null) {
			Object o = totalDao.getObjectById(BonusShiZhi.class, id);
			if (o != null) {
				return (BonusShiZhi) o;
			}
		}
		return null;
	}

	@Override
	public boolean update(BonusShiZhi bonusShiZhi) {
		// TODO Auto-generated method stub
		boolean b=true;
		String month=bonusShiZhi.getMonth();
		Float money=bonusShiZhi.getBonus();
		b=b&totalDao.update(bonusShiZhi);
		b=b&getTryMakeRateByMonth(month, money);
		return b;
	}

	@Override
	public Map<Integer, Object> findBonusShiZhisByCondition(
			BonusShiZhi bonusShiZhi, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (bonusShiZhi == null) {
			bonusShiZhi = new BonusShiZhi();
		}
		String hql = totalDao.criteriaQueries(bonusShiZhi, null, null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public List<BonusShiZhi> findAll() {
		// TODO Auto-generated method stub
		List<Object> all = totalDao.query("from BonusShiZhi");
		if (all.size() > 0) {
			List<BonusShiZhi> bonusShiZhis = new ArrayList<BonusShiZhi>();
			for (Object o : all) {
				bonusShiZhis.add((BonusShiZhi) o);
			}
			return bonusShiZhis;
		}

		return null;
	}
	@Override
	public boolean getTryMakeRateByMonth(String month,Float money) {
		// TODO Auto-generated method stub
		boolean b=true;
		String sql="from ProTryMakeScore where poSize > 0 and month = '"+month+"'";
		List list=totalDao.query(sql);//获取所选的月的所有有订单的项目试制评审对象
		if(list.size()>0){
			List<ProTryMakeScore> ptmsList=list;
			List<TryMake> tmList=new ArrayList<TryMake>();//用来存放所有该月需要参与计算占比的项目试制零件
			for(ProTryMakeScore ptms:ptmsList){
				Set<TryMake> tmSet=ptms.getTryMake();
				if(tmSet.size()>0){
					for(TryMake t:tmSet){
						if(t.getInputNum()!=null&&t.getInputNum()>0){
							tmList.add(t);//如果有入库数量则表示该项目试制零件参与计算月奖金额
						}
					}
				}
				
			}
			
			if(tmList.size()>0){
				for(TryMake tm:tmList){
					Float totalScore=tm.getAllTotalRate();
					Float totalRate=tm.getTotalRate();
					if(money!=null){
					if(totalScore!=null&&totalScore!=0&&totalRate!=null){
						tm.setBonus(Float.parseFloat(String.format(
							"%.4f", money*totalRate/totalScore)));
					}else{
						tm.setBonus(0f);
					}
				}else{
					tm.setBonus(null);
				}
					b=b&totalDao.update(tm);
				}
			}
		}
		return b;
	}

	
}
