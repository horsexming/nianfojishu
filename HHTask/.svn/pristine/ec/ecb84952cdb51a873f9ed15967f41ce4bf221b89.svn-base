package com.task.ServerImpl.shizhi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.shizhi.ProductivityLoadServer;
import com.task.entity.shizhi.ProductivityLoad;
import com.task.entity.sop.ProcardTemplate;
import com.task.entity.sop.ProcessTemplate;

public class ProductivityLoadServerImpl implements ProductivityLoadServer{
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
		ProductivityLoad sscore=getById(id);
		if(sscore!=null){
			return totalDao.delete(sscore);
		}
		return false;
	}

	

	@Override
	public ProductivityLoad getById(Integer id) {
		// TODO Auto-generated method stub
		if(id!=null){
		 Object o=totalDao.getObjectById(ProductivityLoad.class, id);
		 if(o!=null){
			return (ProductivityLoad)o;
		 }
		}
		return null;
	}

	@Override
	public boolean update(ProductivityLoad productivityLoad) {
		// TODO Auto-generated method stub
		if(productivityLoad!=null){
			ProductivityLoad p=getById(productivityLoad.getId());
			p.setCurCusNeedNum(productivityLoad.getCurCusNeedNum());
			p.setCurMonInputNum(productivityLoad.getCurMonInputNum());
			p.setCusDeadline(productivityLoad.getCusDeadline());
			p.setWorkTimeLimits(productivityLoad.getWorkTimeLimits());
			if(productivityLoad.getCurCusNeedNum()!=null&&p.getUpNumber()!=null){
				float payDays=(float) Math.ceil(p.getCurCusNeedNum()/p.getUpNumber());
				p.setPayDays(payDays);
			}
			if(productivityLoad.getCusDeadline()!=null&&p.getPayDays()!=null){
				float satisfactionRate=p.getPayDays()/productivityLoad.getCusDeadline();
				p.setSatisfactionRate(Float.parseFloat(String.format("%.3f",satisfactionRate)));
			}
			if(productivityLoad.getCurCusNeedNum()!=null&&p.getSingleTime()!=null){
				float totalWorkTime=0f;
				if(productivityLoad.getCusDeadline()!=null){
					totalWorkTime=productivityLoad.getCurCusNeedNum()*p.getSingleTime();
				}else{
					totalWorkTime=productivityLoad.getCurCusNeedNum()*p.getSingleTime()-productivityLoad.getCusDeadline()*3600;
				}
				
				p.setTotalWorkTime(Float.parseFloat(String.format("%.3f",totalWorkTime)));
			}
			if(p.getCusDeadline()!=null
					&&p.getCurCusNeedNum()!=null
					&&p.getCurMonInputNum()!=null){
				float proload=0;
				if(p.getSingleTime()==null||p.getSingleTime()==0){
					proload=p.getCurMonInputNum()/p.getCurCusNeedNum();
				}else{
					proload=p.getCusDeadline()*8*3600/(p.getCusDeadline()*8*3600+p.getSingleTime()*p.getCurCusNeedNum())*p.getCurMonInputNum()/p.getCurCusNeedNum();
				}
				p.setProLoad(Float.parseFloat(String.format("%.3f", proload)));
			}else{
				p.setProLoad(null);
			}
			
			return totalDao.update(p);
		}
		
		return false;
	}

	@Override
	public Map<Integer, Object> findProductivityLoadsByCondition(ProductivityLoad productivityLoad,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (productivityLoad == null) {
			productivityLoad = new ProductivityLoad();
		}
		String hql = totalDao.criteriaQueries(productivityLoad, null, null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public List<ProductivityLoad> findAll() {
		// TODO Auto-generated method stub
		List<Object> all=totalDao.query("from ProductivityLoad");
		if(all.size()>0){
			List<ProductivityLoad> productivityLoads=new ArrayList<ProductivityLoad>();
			for(Object o:all){
				productivityLoads.add((ProductivityLoad)o);
			}
			return productivityLoads;
		}

		return null;
	}
	@Override
	public List findQpInforAll() {
		// TODO Auto-generated method stub
		String hql = "from ProcessTemplate";
		return totalDao.query(hql);
	}

	@Override
	public ProcessTemplate getQPbyQPInfoId(Integer id) {
		// TODO Auto-generated method stub
		Object o=totalDao.getObjectById(ProcessTemplate.class, id);
		if(o!=null){
			return (ProcessTemplate)o;
		}
		return null;
	}

//	@Override
//	public boolean addUnintoData() {
//		// TODO Auto-generated method stub
//		String sql = "from ProcessTemplate where id not in (select processId from ProductivityLoad)";
//		List qlist = totalDao.query(sql);
//		boolean b = true;
//		if (qlist.size() > 0) {// 添加未和ProcessTemplate表同步的值
//			for (int i = 0; i < qlist.size(); i++) {
//				ProductivityLoad pload = new ProductivityLoad();// 创建工艺负荷对象临时存储
//				ProcessTemplate qpInfo = (ProcessTemplate) qlist.get(i);
//				ProcardTemplate qp = qpInfo.getProcardTemplate();
//				if(qp.getProductStyle()!=null&&qp.getProductStyle().equals("试制")){
//					pload.setQpId(qp.getId());
//					pload.setFatherId(qp.getFatherId());
//					pload.setMarkId(qp.getMarkId());
//					pload.setRootId(qp.getRootId());
//					pload.setProcessId(qpInfo.getId());
//					pload.setProcessNO(qpInfo.getProcessNO());
//					pload.setProcessName(qpInfo.getProcessName());
//					pload.setSingleTime(qpInfo.getAllJiepai());
//					if(pload.getSingleTime()!=null&&pload.getSingleTime()!=0){
//						float upNumber=8*3600/pload.getSingleTime();
//						pload.setUpNumber(Float.parseFloat(String.format("%.3f",upNumber)));
//					}else{
//						pload.setUpNumber(null);
//					}
//					b = b & totalDao.save(pload);
//				}
//			}
//		}
//		return b;
//	}

//	@Override
//	public boolean deleteMoreData() {
//		// TODO Auto-generated method stub
//		String sql = "from ProductivityLoad where processId not in (select id from ProcessTemplate)";
//		List plist = totalDao.query(sql);
//		boolean b = true;
//		if (plist.size() > 0) {// 添加未和ProcessTemplate表同步的值
//			for (int i = 0; i < plist.size(); i++) {
//				ProductivityLoad pload = (ProductivityLoad) plist.get(i);
//				if(pload!=null){
//					b = b &totalDao.delete(pload);
//				}
//			}
//		}
//		return b;
//	}

	@Override
	public boolean updateAll() {
		// TODO Auto-generated method stub
		String sql = "from ProcessTemplate where procardTemplate.id in(select id from ProcardTemplate where productStyle='试制')";
		List qlist = totalDao.query(sql);
		List<ProductivityLoad> plist=findAll();
		boolean b = true;
		for(ProductivityLoad p:plist){
		if (qlist.size() > 0) {// 和ProcessTemplate表同步的值
			for (int i = 0; i < qlist.size(); i++) {
				ProcessTemplate qpInfo = (ProcessTemplate) qlist.get(i);
				if(p.getProcessId()!=null&&p.getProcessId().equals(qpInfo.getId())){
				ProcardTemplate qp = qpInfo.getProcardTemplate();
				if(qp.getProductStyle()!=null&&qp.getProductStyle().equals("试制")){
					p.setQpId(qp.getId());
					p.setFatherId(qp.getFatherId());
					p.setMarkId(qp.getMarkId());
					p.setRootId(qp.getRootId());
					p.setProcessId(qpInfo.getId());
					p.setProcessNO(qpInfo.getProcessNO());
					p.setProcessName(qpInfo.getProcessName());
					if(qpInfo.getAllJiepai()!=null){
						p.setSingleTime(qpInfo.getAllJiepai());
					}else if(qpInfo.getOpcaozuojiepai()!=null&&qpInfo.getOpshebeijiepai()!=null&&qpInfo.getGzzhunbeijiepai()!=null&&qpInfo.getGzzhunbeicishu()!=null){
						p.setSingleTime(qpInfo.getOpcaozuojiepai()+qpInfo.getOpshebeijiepai()+(qpInfo.getGzzhunbeijiepai()*qpInfo.getGzzhunbeicishu()));
					}
					if(p.getSingleTime()!=null&&p.getSingleTime()!=0){
						float upNumber=8*3600/p.getSingleTime();
						p.setUpNumber(Float.parseFloat(String.format("%.3f",upNumber)));
					}else{
						p.setUpNumber(null);
					}
					if(p.getCurCusNeedNum()!=null&&p.getUpNumber()!=null){
						float payDays=(float) Math.ceil(p.getCurCusNeedNum()/p.getUpNumber());
						p.setPayDays(payDays);
					}else{
						p.setPayDays(null);
					}
					if(p.getCusDeadline()!=null&&p.getPayDays()!=null){
						float satisfactionRate=p.getPayDays()/p.getCusDeadline();
						p.setSatisfactionRate(Float.parseFloat(String.format("%.3f",satisfactionRate)));
					}else{
						p.setSatisfactionRate(null);
					}
					if(p.getCurCusNeedNum()!=null&&p.getSingleTime()!=null){
						float totalWorkTime=0f;
						if(p.getCusDeadline()!=null){
							totalWorkTime=p.getCurCusNeedNum()*p.getSingleTime();
						}else{
							totalWorkTime=p.getCurCusNeedNum()*p.getSingleTime()-p.getCusDeadline()*3600;
						}
						
						p.setTotalWorkTime(Float.parseFloat(String.format("%.3f",totalWorkTime)));
					}else{
						p.setTotalWorkTime(null);
					}
					if(p.getCusDeadline()!=null
							&&p.getCurCusNeedNum()!=null
							&&p.getCurMonInputNum()!=null){
						float proload=0;
						if(p.getSingleTime()==null||p.getSingleTime()==0){
							proload=p.getCurMonInputNum()/p.getCurCusNeedNum();
						}else{
							proload=p.getCusDeadline()*8*3600/(p.getCusDeadline()*8*3600+p.getSingleTime()*p.getCurCusNeedNum())*p.getCurMonInputNum()/p.getCurCusNeedNum();
						}
						p.setProLoad(Float.parseFloat(String.format("%.3f", proload)));
					}else{
						p.setProLoad(null);
					}
					b = b & totalDao.update(p);
					break;
				}
				}
			}
		}
		}
		return b;
	}

//	@Override
//	public boolean updateHadData() {
//		// TODO Auto-generated method stub
//		String sql = "from ProcessTemplate";
//		List qlist = totalDao.query(sql);
//		List<ProductivityLoad> plist=findAll();
//		boolean b = true;
//		for(ProductivityLoad p:plist){
//		if (qlist.size() > 0) {// 和ProcessTemplate表同步的值
//			for (int i = 0; i < qlist.size(); i++) {
//				ProcessTemplate qpInfo = (ProcessTemplate) qlist.get(i);
//				if(p.getProcessId()!=null&&p.getProcessId().equals(qpInfo.getId())){
//				ProcardTemplate qp = qpInfo.getProcardTemplate();
//				if(qp.getProductStyle()!=null&&qp.getProductStyle().equals("试制")){
//					p.setQpId(qp.getId());
//					p.setFatherId(qp.getFatherId());
//					p.setMarkId(qp.getMarkId());
//					p.setRootId(qp.getRootId());
//					p.setProcessId(qpInfo.getId());
//					p.setProcessNO(qpInfo.getProcessNO());
//					p.setProcessName(qpInfo.getProcessName());
//					p.setSingleTime(qpInfo.getAllJiepai());
//					if(p.getSingleTime()!=null&&p.getSingleTime()!=0){
//						float upNumber=8*3600/p.getSingleTime();
//						p.setUpNumber(Float.parseFloat(String.format("%.3f",upNumber)));
//					}else{
//						p.setUpNumber(null);
//					}
//					if(p.getCurCusNeedNum()!=null&&p.getUpNumber()!=null){
//						float payDays=(float) Math.ceil(p.getCurCusNeedNum()/p.getUpNumber());
//						
//						p.setPayDays(payDays);
//					}else{
//						p.setPayDays(null);
//					}
//					if(p.getCusDeadline()!=null&&p.getPayDays()!=null){
//						float satisfactionRate=p.getPayDays()/p.getCusDeadline();
//						p.setSatisfactionRate(Float.parseFloat(String.format("%.3f",satisfactionRate)));
//					}else{
//						p.setSatisfactionRate(null);
//					}
//					if(p.getCurCusNeedNum()!=null&&p.getSingleTime()!=null){
//						float totalWorkTime=0f;
//						if(p.getCusDeadline()!=null){
//							totalWorkTime=p.getCurCusNeedNum()*p.getSingleTime();
//						}else{
//							totalWorkTime=p.getCurCusNeedNum()*p.getSingleTime()-p.getCusDeadline()*3600;
//						}
//						
//						p.setTotalWorkTime(Float.parseFloat(String.format("%.3f",totalWorkTime)));
//					}else{
//						p.setTotalWorkTime(null);
//					}
//					if(p.getCusDeadline()!=null
//							&&p.getCurCusNeedNum()!=null
//							&&p.getCurMonInputNum()!=null){
//						float proload=0;
//						if(p.getSingleTime()==null||p.getSingleTime()==0){
//							proload=p.getCurMonInputNum()/p.getCurCusNeedNum();
//						}else{
//							proload=p.getCusDeadline()*8*3600/(p.getCusDeadline()*8*3600+p.getSingleTime()*p.getCurCusNeedNum())*p.getCurMonInputNum()/p.getCurCusNeedNum();
//						}
//						p.setProLoad(Float.parseFloat(String.format("%.3f", proload)));
//					}else{
//						p.setProLoad(null);
//					}
//					b = b & totalDao.update(p);
//				}
//				}
//			}
//		}
//		}
//		return b;
//	}
}
