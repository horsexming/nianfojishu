package com.task.ServerImpl.shizhi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.shizhi.BonusLoadServer;
import com.task.entity.Price;
import com.task.entity.project.ProjectTime;
import com.task.entity.shizhi.BonusLoad;
import com.task.entity.sop.ProcardTemplate;

public class BonusLoadServerImpl implements BonusLoadServer {
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
		BonusLoad sscore = getById(id);
		if (sscore != null) {
			return totalDao.delete(sscore);
		}
		return false;
	}

	@Override
	public BonusLoad getById(Integer id) {
		// TODO Auto-generated method stub
		if (id != null) {
			Object o = totalDao.getObjectById(BonusLoad.class, id);
			if (o != null) {
				return (BonusLoad) o;
			}
		}
		return null;
	}


	@Override
	public Map<Integer, Object> findBonusLoadsByCondition(BonusLoad bonusLoad,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (bonusLoad == null) {
			bonusLoad = new BonusLoad();
		}
		String hql = totalDao.criteriaQueries(bonusLoad, null, null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public List<BonusLoad> findAll() {
		// TODO Auto-generated method stub
		List<Object> all = totalDao.query("from BonusLoad");
		if (all.size() > 0) {
			List<BonusLoad> BonusLoads = new ArrayList<BonusLoad>();
			for (Object o : all) {
				BonusLoads.add((BonusLoad) o);
			}
			return BonusLoads;
		}

		return null;
	}

	@Override
	public boolean updateAll() {
		// TODO Auto-generated method stub
		boolean b=true;
		List<BonusLoad> all=findAll();
		if(all.size()>0){
			for(BonusLoad bload:all){
				String sql1 = "select sum(money) from ProjectTime where classNumber  in ('cl','sb','gz','fl','rg','wgww','nyxh','bzys') and money is not null and quoId in (select id from QuotedPrice where id=rootId and markId =?)";
				Float cbmoney=(Float) totalDao.getObjectByCondition(sql1, bload.getMarkId());//项目时间表的中的数据用来查询单价零件总成本
				String sql2 ="from Price where produceType='销售' and hsPrice is not null and partNumber =? order by id desc";
				Price price=(Price) totalDao.getObjectByQuery(sql2,bload.getMarkId());//价格表中的数据用来查询单件零件销售价格
				bload.setCost(cbmoney);
				if(price!=null){
					bload.setSalePrice(price.getHsPrice());
				}
				if(bload.getCost()!=null
						&&bload.getCost()!=0
						&&bload.getSalePrice()!=null
						&&bload.getSalePrice()!=0){
					float bonusLoad=(float) (bload.getCost()/bload.getSalePrice());
					bload.setBonusLoad(Float.parseFloat(String.format("%.3f", bonusLoad)));
				}else{
					bload.setBonusLoad(0f);
				}
				b=b&totalDao.update(bload);
			}
		}
		return b;
	}



//	@Override
//	public boolean addUnintoData() {
//		// TODO Auto-generated method stub
//		String sql = "from ProcardTemplate where id not in (select qpId from BonusLoad)";
//		List qlist = totalDao.query(sql);//零件表中有奖金负荷表中没有的的所有数据
//		String sql2 = "from ProjectTime where level=1 ";
//		List plist=totalDao.query(sql2);//项目时间表的中的数据用来查询单价零件总成本
//		String sql3 ="from Price where produceType='销售' order by id asc";
//		List pricelist=totalDao.query(sql3);//价格表中的数据用来查询单件零件销售价格
//		boolean b=true;
//		if(qlist.size()>0){
//			List<ProcardTemplate> qlist2=qlist;//转换类型
//			for(ProcardTemplate q:qlist2){
//			BonusLoad bload=new BonusLoad();
//			bload.setQpId(q.getId());
//			bload.setFatherId(q.getFatherId());
//			bload.setMarkId(q.getMarkId());
//			bload.setRootId(q.getRootId());
//			 if(plist.size()>0){//设置单件零件总成本
//				 List<ProjectTime> plist2=plist;//转换类型
//				 for(ProjectTime p:plist2){
//					 if(p.getQuoId()!=null&&p.getQuoId().equals(q.getId())){
//						 bload.setCost(p.getMoney());
//					 }
//				 }
//			 }
//			 if(pricelist.size()>0){//设置单件零件销售价格
//				 List<Price> pricelist2=pricelist;
//				 for(Price pr:pricelist2){
//					 if(pr.getPartNumber()!=null&&pr.getPartNumber().equals(q.getMarkId())){
//						 bload.setSalePrice(pr.getHsPrice());//循环获取最后一个同件号的价格（排序之后默认最后一个为有效价格）
//					 }
//				 }
//			 }
//			 if(bload.getCost()!=null
//					 &&bload.getCost()!=0
//					 &&bload.getSalePrice()!=null
//					 &&bload.getSalePrice()!=0){
//				 float bonusLoad=(float) (bload.getCost()/bload.getSalePrice());
//				 bload.setBonusLoad(Float.parseFloat(String.format("%.3f", bonusLoad)));
//			 }
//			 b=b&totalDao.save(bload);
//			 
//			}
//			
//		}
//		return b;
//	}
//
//	@Override
//	public boolean deleteMoreData() {
//		// TODO Auto-generated method stub
//		String sql = "from BonusLoad where qpId not in (select id from ProcardTemplate)";
//		List blist = totalDao.query(sql);//奖金负荷表中有零件表中没有的的所有数据
//		boolean bool=true;
//		if(blist.size()>0){
//			List<BonusLoad> blist2=blist;
//			for(BonusLoad b:blist2){
//				if(b!=null){
//					bool=bool&totalDao.delete(b);
//				}
//			}
//		}
//		return bool;
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

//	@Override
//	public boolean updateHadData() {
//		// TODO Auto-generated method stub
//		List<BonusLoad> blist =findAll();
//		String sql = "from ProcardTemplate ";
//		List qlist = totalDao.query(sql);//零件表中有奖金负荷表中没有的的所有数据
//		String sql2 = "from ProjectTime where level=1 ";
//		List plist=totalDao.query(sql2);//项目时间表的中的数据用来查询单价零件总成本
//		String sql3 ="from Price where produceType='销售' order by id asc";
//		List pricelist=totalDao.query(sql3);//价格表中的数据用来查询单件零件销售价格
//		boolean bool=true;
//		if(blist!=null){
//			for(BonusLoad b:blist){
//				
//				if(qlist.size()>0){
//					List<ProcardTemplate> qlist2=qlist;//转换类型
//					for(ProcardTemplate q:qlist2){
//						if(b.getQpId()!=null&&b.getQpId().equals(q.getId())){
//							b.setRootId(q.getRootId());
//							b.setFatherId(q.getFatherId());
//							b.setMarkId(q.getMarkId());
//						}
//					}
//				}
//				if(plist.size()>0){//设置单件零件总成本
//					 List<ProjectTime> plist2=plist;//转换类型
//					 for(ProjectTime p:plist2){
//						 if(p.getQuoId()!=null&&p.getQuoId().equals(b.getQpId())){
//							 b.setCost(p.getMoney());
//						 }
//					 }
//				 }
//				 if(pricelist.size()>0){//设置单件零件销售价格
//					 List<Price> pricelist2=pricelist;
//					 for(Price pr:pricelist2){
//						 if(pr.getPartNumber()!=null&&pr.getPartNumber().equals(b.getMarkId())){
//							 b.setSalePrice(pr.getHsPrice());//循环获取最后一个同件号的价格（排序之后默认最后一个为有效价格）
//						 }
//					 }
//				 }
//				 if(b.getCost()!=null
//						 &&b.getCost()!=0
//						 &&b.getSalePrice()!=null
//						 &&b.getSalePrice()!=0){
//					 float bonusLoad=(float) (b.getCost()/b.getSalePrice());
//					 b.setBonusLoad(Float.parseFloat(String.format("%.3f", bonusLoad)));
//				 }else{
//					 b.setBonusLoad(null);
//				 }
//				 bool=bool&totalDao.update(b);
//			}
//		}
//		return bool;
//
//	}
}
