package com.task.ServerImpl.bybz;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.Server.bybz.CheckoutAndGagesServer;
import com.task.entity.Users;
import com.task.entity.bybz.BaoYangBiaoZhun;
import com.task.entity.bybz.CheckoutAndGages;
import com.task.entity.bybz.LjuCheckRecord;
import com.task.entity.bybz.LjuCheckRecordMx;
import com.task.util.Util;

public class CheckoutAndGagesServerImpl implements CheckoutAndGagesServer{
	
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	@Override
	public String addCheckoutAndGage(CheckoutAndGages cag) {
		if(cag!=null){
			Users liableuser = null;
			if(cag.getCodeLiable()!=null && cag.getCodeLiable().length()>0){
				liableuser = (Users) totalDao.getObjectByCondition(" from Users where code = ? ",cag.getCodeLiable() );
			}else{
				liableuser = Util.getLoginUser();
			}
			Users user = Util.getLoginUser();
			cag.setAddTime(Util.getDateTime());//添加时间
			cag.setAddUsers(user.getName());//添加人
			cag.setAddUsersCode(user.getCode());//添加人工号
			cag.setAddUsesId(user.getId());//添加人Id
			cag.setPersonLiable(liableuser.getName());//责任人
			cag.setUserIdLiable(liableuser.getId());//责任人Id
			String nextTime ="";
			if(cag.getJyCycle()!=null ){
				nextTime =	Util.getSpecifiedDayAfter(Util.getDateTime(), cag.getJyCycle());
				 cag.setNexJYTime(nextTime);
			}
			List<BaoYangBiaoZhun> bybzList = cag.getBybzList();
			Set<BaoYangBiaoZhun> bybzSet = new HashSet<BaoYangBiaoZhun>();
			if(bybzList!=null && bybzList.size()>0){
				for (BaoYangBiaoZhun baoYangBiaoZhun : bybzList) {
					baoYangBiaoZhun.setCheckoutAndGages(cag);
					bybzSet.add(baoYangBiaoZhun);
				}
			}
			cag.setBybzSet(bybzSet);
			return	totalDao.save(cag)+"";
			
		}
		return null;
	}

	@Override
	public String delCheckoutAndGage(CheckoutAndGages cag) {
		if(cag!=null && cag.getId()!=null ){
			return totalDao.delete(cag)+"";
		}
		return null;
	}

	@Override
	public Object[] findAllLcrList(LjuCheckRecord lcr, int pageNo,
			int pageSize, String status) {
		if(lcr == null){
			lcr = new	LjuCheckRecord();
		}
		String hql =	totalDao.criteriaQueries(lcr, null);
		List<LjuCheckRecord> Listlcr =	totalDao.findAllByPage(hql, pageNo, pageSize, null);
		int count = totalDao.getCount(hql, null);
		return new Object[]{Listlcr,count};
	}

	@Override
	public Object[] findAllcagList(CheckoutAndGages cag, int pageNo,
			int pageSize, String status) {
		if(cag==null){
			cag = new CheckoutAndGages();
		}
		String hql = totalDao.criteriaQueries(cag, null);
		if("jdq".equals(status)){
			hql+= " and DATEDIFF(DAY,GETDATE() , nexJYTime)<jyCycle/10  and nexJYTime-getDate()>0 ";
		}else if("ydq".equals(status)){
			hql+= " and DATEDIFF(DAY,GETDATE(),nexJYTime)<0 " ; 
		}
		List<CheckoutAndGages> cagList =	totalDao.findAllByPage(hql+" order by id desc", pageNo, pageSize, null);
		int count =	totalDao.getCount(hql);
		for (CheckoutAndGages checkoutAndGages : cagList) {
			try {
				Long sec = Util.getDateDiff(Util.getDateTime("yyyy-MM-dd"), "yyyy-MM-dd", checkoutAndGages.getNexJYTime(),  "yyyy-MM-dd");
			Long nextjyday =	sec/60/60/24;
			checkoutAndGages.setNextjyday(nextjyday.intValue());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return new Object[]{cagList,count} ;
	}

	@Override
	public CheckoutAndGages findCagById(Integer id) {
		if(id!=null && id>0){
			return (CheckoutAndGages) totalDao.get(CheckoutAndGages.class, id);
		}
		return null;
	}

	@Override
	public LjuCheckRecord findLcrById(Integer id) {
		if(id!=null && id>0){
			return (LjuCheckRecord) totalDao.get(LjuCheckRecord.class, id);
		}
		return null;
	}

	@Override
	public List<LjuCheckRecordMx> findLcrMxList(Integer id) {
		if(id!=null && id>0){
			return totalDao.query(" from LjuCheckRecordMx where ljuCR.id =?", id);
		}
		return null;
	}

	@Override
	public List<BaoYangBiaoZhun> findbybzListBycagId(Integer id) {
		if(id!=null && id>0){
			return totalDao.query(" from BaoYangBiaoZhun where checkoutAndGages.id =?", id);
		}
		return null;
	}

	@Override
	public String updateCag(CheckoutAndGages cag) {
		if(cag!=null){
			CheckoutAndGages oldcag=	(CheckoutAndGages) totalDao.get(CheckoutAndGages.class, cag.getId());
			oldcag.setName(cag.getName());
			oldcag.setJyCycle(cag.getJyCycle());
			if(cag.getCodeLiable()!=null && cag.getCodeLiable().length()>0 &&
					!cag.getCodeLiable().equals(oldcag.getCodeLiable())){
				oldcag.setCodeLiable(cag.getCodeLiable());
				Users users = (Users) totalDao.getObjectByCondition(" from Users where code = ?",cag.getCodeLiable() );
				oldcag.setUserIdLiable(users.getId());
				oldcag.setPersonLiable(users.getName());
			}
			List<BaoYangBiaoZhun> bybzList = cag.getBybzList();
			Set<BaoYangBiaoZhun> bybzSet = new HashSet<BaoYangBiaoZhun>();
			if(bybzList!=null && bybzList.size()>0){
				bybzSet.addAll(bybzList);
			}
			oldcag.setBybzSet(bybzSet);
			return totalDao.update(oldcag)+"";
			
		}
		return null;
	}

	@Override
	public String jYCag(LjuCheckRecord lcr) {
		if(lcr!=null){
			//记录校验信息;
			String nowTime = Util.getDateTime();
			lcr.setJyTime(nowTime);//校验时间
			Users user =	Util.getLoginUser();
			lcr.setJyUsers(user.getName());//校验人
			lcr.setJyUsresCode(user.getCode());//校验人工号
			lcr.setJyUsresId(user.getId());//校验人Id
			List<LjuCheckRecordMx> ljuCRMList = lcr.getLjuCRMList();
			Set<LjuCheckRecordMx> ljuCRMSet = new HashSet<LjuCheckRecordMx>();
			if(ljuCRMList!=null && ljuCRMList.size()>0){
				for (LjuCheckRecordMx ljuCheckRecordMx : ljuCRMList) {
					ljuCheckRecordMx.setLjuCR(lcr);
					ljuCRMSet.add(ljuCheckRecordMx);
				}
			}
			lcr.setLjuCRMSet(ljuCRMSet);
			boolean bool =	totalDao.save(lcr);
			if(bool){
				//计算下次检验时间;
				String nextTime ="";
				if(lcr.getCagId()!=null && lcr.getCagId()>0){
					CheckoutAndGages  cag =	(CheckoutAndGages) totalDao.get(CheckoutAndGages.class,lcr.getCagId());
				  nextTime =	Util.getSpecifiedDayAfter(nowTime, cag.getJyCycle());
				  cag.setNexJYTime(nextTime);
				  bool = totalDao.update(cag);
				}
			}
			 return bool+"";
			
		}
		return null;
	}

	@Override
	public Users findUsersByCode(String code) {
		if(code!=null && code.length()>0){
			return (Users) totalDao.getObjectByCondition(" from Users where code = ? ", code);
		}
		return null;
	}

	@Override
	public Object[] findLcrList(LjuCheckRecord lcr, int pageNo, int pageSize,
			String status) {
		if(lcr == null){
			lcr = new LjuCheckRecord();
		}
		String hql = totalDao.criteriaQueries(lcr,null);
		List<LjuCheckRecord> lcrList =	totalDao.findAllByPage(hql, pageNo, pageSize,null);
		int count =	totalDao.getCount(hql, null);
		return new Object[]{lcrList,count};
	}

}
