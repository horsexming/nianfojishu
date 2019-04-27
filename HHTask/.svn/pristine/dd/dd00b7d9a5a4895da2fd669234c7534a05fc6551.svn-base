package com.task.ServerImpl.jxkh;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sun.text.normalizer.UTF16;


import com.task.Dao.TotalDao;
import com.task.Server.jxkh.JiaoXiaoKaoHeServer;
import com.task.entity.Users;
import com.task.entity.jxkh.BmzZlh;
import com.task.entity.jxkh.DeptFenPeiXiShu;
import com.task.entity.jxkh.DeptLeaderPenPei;
import com.task.entity.jxkh.DeptUsersDuty;
import com.task.entity.jxkh.FenPeiQingKuang;
import com.task.entity.jxkh.InCome;
import com.task.entity.jxkh.RankingCounts;
import com.task.entity.jxkh.SalesAmountCoefficient;
import com.task.entity.jxkh.TargetAchievedMark;
import com.task.entity.jxkh.WaiWeiJieGouMuBiao;
import com.task.entity.jxkh.WeiWaiJieGou;
import com.task.entity.jxkh.WorkShopTiQu;
import com.task.entity.jxkh.WorkShopTiQuMx;
import com.task.entity.jxkh.WorkShopXiaoLvZz;
import com.task.entity.jxkh.YearImprove;
import com.task.entity.jxkh.ZbSjZk;
import com.task.entity.jxkh.ZhiZaoPingJia;
import com.task.util.Util;

public class JiaoXiaoKaoHeServerImpl implements JiaoXiaoKaoHeServer{
	private TotalDao totalDao;
	
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	
	@Override
	public String addDeptUsersDuty(DeptUsersDuty dud) {
		Users users =	Util.getLoginUser();
		if(users == null){
			return "请先登录!~";
		}
		if(dud!=null){
			dud.setAddTime(Util.getDateTime());
			dud.setAddUserName(users.getName());
			return 	totalDao.save(dud)+"";
		}
		return "eeror";
	}

	@Override
	public String addRankingCounts(RankingCounts rankCounts) {
		Users users =	Util.getLoginUser();
		if(users == null){
			return "请先登录!~";
		}
		if(rankCounts!=null){
			int count =	totalDao.getCount("from RankingCounts where groups=? and ranking =? ", rankCounts.getGroups(),
					rankCounts.getRanking());
			if(count>0){
				return  rankCounts.getGroups()+"已设置过第"+rankCounts.getRanking()+"名次，请重新设置。";
			}
			rankCounts.setAddTime(Util.getDateTime());
			rankCounts.setAddUsers(users.getName());
			return 	totalDao.save(rankCounts)+"";
		}
		return null;
	}

	@Override
	public String addTargetAchievedMark(TargetAchievedMark tam) {
		Users users =	Util.getLoginUser();
		if(users == null){
			return "请先登录!~";
		}
		if(tam!=null){
			tam.setAddTime(Util.getDateTime());
			tam.setAddUsersName(users.getName());
			return	totalDao.save(tam)+"";
		}
		return "error";
	}

	@Override
	public String addWwJgMb(WaiWeiJieGouMuBiao wwjgmb) {
		Users users =	Util.getLoginUser();
		if(users == null){
			return "请先登录!~";
		}
		if(wwjgmb!=null){
			String years = Util.getDateTime("yyyy");
			int count =	totalDao.getCount("from WaiWeiJieGouMuBiao where years=? ", years);
			if(count>0){
				return years+"年，已设置过结构比委外比目标，无需重复设置！";
			}
			wwjgmb.setYears(years);
			wwjgmb.setAddTime(Util.getDateTime());
			wwjgmb.setAddUsersName(users.getName());
			return	totalDao.save(wwjgmb)+"";
		}
		return "error";
	}

	@Override
	public String addsac(SalesAmountCoefficient sac) {
		Users users =	Util.getLoginUser();
		if(users == null){
			return "请先登录!~";
		}
		if(sac!=null){
			sac.setAddTime(Util.getDateTime());
			sac.setAddUsersName(users.getName());
			return	totalDao.save(sac)+"";
		}
		
		return "error";
	}

	@Override
	public String delDud(DeptUsersDuty dud) {
		if(dud!=null){
			dud =(DeptUsersDuty) totalDao.get(DeptUsersDuty.class, dud.getId());
			return totalDao.delete(dud)+"";
		}
		return null;
	}

	@Override
	public String delRankCounts(RankingCounts rankCounts) {
		if(rankCounts!=null){
			return totalDao.delete(rankCounts)+"";
		}
		return null;
	}

	@Override
	public String delSac(SalesAmountCoefficient sac) {
		if(sac!=null){
			sac = (SalesAmountCoefficient) totalDao.get(SalesAmountCoefficient.class, sac.getId());
			return totalDao.delete(sac)+"";
		}
		return null;
	}

	@Override
	public String delTam(TargetAchievedMark tam) {
		if(tam!=null){
			tam  = (TargetAchievedMark) totalDao.get(TargetAchievedMark.class, tam.getId());
			return totalDao.delete(tam)+"";
		}
		return null;
	}

	@Override
	public String dleWwJgMb(WaiWeiJieGouMuBiao wwjgmb) {
		if(wwjgmb!=null){
			wwjgmb =(WaiWeiJieGouMuBiao) totalDao.get(WaiWeiJieGouMuBiao.class, wwjgmb.getId());
			return totalDao.delete(wwjgmb)+"";
		}
		return null;
	}

	@Override
	public Object[] findAllDudList(DeptUsersDuty dud, Integer pageSize,
			Integer pageNo, String status) {
		if(dud==null){
			dud = new DeptUsersDuty();
		}
		String hql = totalDao.criteriaQueries(dud,null);
		List<DeptUsersDuty> dudList =	totalDao.findAllByPage(hql, pageNo, pageSize,null);
		int count = totalDao.getCount(hql);
		return new Object[]{dudList,count};
	}

	@Override
	public Object[] findAllRankCountsList(RankingCounts rankCounts,
			Integer pageSize, Integer pageNo, String status) {
		if(rankCounts==null){
			rankCounts = new RankingCounts();
		}
		String hql = totalDao.criteriaQueries(rankCounts,null);
		List<RankingCounts> rankCountsList =totalDao.findAllByPage(hql, pageNo, pageSize,null);
		int count = totalDao.getCount(hql);
		return new Object[]{rankCountsList,count};
	}

	@Override
	public Object[] findAllSacList(SalesAmountCoefficient sac,
			Integer pageSize, Integer pageNo, String status) {
		if(sac==null){
			sac = new SalesAmountCoefficient();
		}
		String hql = totalDao.criteriaQueries(sac,null);
		List<SalesAmountCoefficient> sacList =	totalDao.findAllByPage(hql, pageNo, pageSize,null);
		int count = totalDao.getCount(hql);
		return new Object[]{sacList,count};
	}

	@Override
	public Object[] findAllTamList(TargetAchievedMark tam, Integer pageSize,
			Integer pageNo, String status) {
		if(tam==null){
			tam = new TargetAchievedMark();
		}
		String hql = totalDao.criteriaQueries(tam,null);
		List<TargetAchievedMark> tamList =	totalDao.findAllByPage(hql, pageNo, pageSize,null);
		int count = totalDao.getCount(hql);
		return new Object[]{tamList,count};
	}

	@Override
	public Object[] findAllWwJgMbList(WaiWeiJieGouMuBiao wwjgmb,
			Integer pageSize, Integer pageNo, String status) {
		if(wwjgmb==null){
			wwjgmb = new WaiWeiJieGouMuBiao();
		}
		String hql = totalDao.criteriaQueries(wwjgmb,null);
		List<WaiWeiJieGouMuBiao> wwjgmbList =	totalDao.findAllByPage(hql, pageNo, pageSize,null);
		int count = totalDao.getCount(hql);
		return new Object[]{wwjgmbList,count};
	}

	@Override
	public DeptUsersDuty findDudById(Integer id) {
		return (DeptUsersDuty) totalDao.get(DeptUsersDuty.class, id);
	}

	@Override
	public List<DeptUsersDuty> findDudList(DeptUsersDuty dud, String status) {
		if(dud==null){
			dud = new DeptUsersDuty();
		}
		String hql = totalDao.criteriaQueries(dud,null);
		return totalDao.query(hql+" order by deptName");
	}

	@Override
	public RankingCounts findRankCountsBy(RankingCounts rankCounts) {
		if(rankCounts==null){
			rankCounts = new RankingCounts();
		}
		String hql = totalDao.criteriaQueries(rankCounts,null);
		return (RankingCounts) totalDao.getObjectByCondition(hql);
	}

	@Override
	public RankingCounts findRankCountsById(Integer id) {
		return (RankingCounts) totalDao.get(RankingCounts.class, id);
	}

	@Override
	public TargetAchievedMark findTamBy(TargetAchievedMark tam) {
		if(tam==null){
			tam = new TargetAchievedMark();
		}
		String hql = totalDao.criteriaQueries(tam,null);
		if(tam.getId()!=null){
			hql+= " and id ="+tam.getId();
		}
		return (TargetAchievedMark) totalDao.getObjectByCondition(hql);
	}

	@Override
	public WaiWeiJieGouMuBiao findWwJgMbBy(WaiWeiJieGouMuBiao wwjgmb) {
		if(wwjgmb==null){
			wwjgmb = new WaiWeiJieGouMuBiao();
		}
		String hql = totalDao.criteriaQueries(wwjgmb,null);
		if(wwjgmb.getId()!=null){
			hql+=" and id="+wwjgmb.getId();
		}
		return (WaiWeiJieGouMuBiao) totalDao.getObjectByCondition(hql);
	}

	@Override
	public SalesAmountCoefficient getSacByValue(Float sales) {
		if(sales!=null){
			return (SalesAmountCoefficient) totalDao.getObjectByCondition("from SalesAmountCoefficient where lowAmount>=?" +
					" and (ceilingAmount<? or ceilingAmount is null)", sales,sales);
		}
		return null;
	}

	@Override
	public SalesAmountCoefficient getsacById(SalesAmountCoefficient sac) {
		if(sac==null){
			sac = new SalesAmountCoefficient();
		}
		String hql = totalDao.criteriaQueries(sac,null,"id");
		if(sac.getId()!=null){
			hql+= " and id="+sac.getId();
		}
		return (SalesAmountCoefficient) totalDao.getObjectByCondition(hql);
	}

	@Override
	public String updateDud(DeptUsersDuty dud) {
		if(dud!=null){
			DeptUsersDuty olddud =	(DeptUsersDuty) totalDao.get(DeptUsersDuty.class, dud.getId());
			olddud.setDeptName(dud.getDeptName());
			olddud.setLeader(dud.getLeader());
			olddud.setOneOrTwo(dud.getOneOrTwo());
			olddud.setRank(dud.getRank());
			olddud.setRankNo(dud.getRankNo());
			olddud.setIsJiaoXiao(dud.getIsJiaoXiao());
			olddud.setIsdeptFenPei(dud.getIsdeptFenPei());
			olddud.setIsZzPj(dud.getIsZzPj());
			olddud.setIsZzXl(dud.getIsZzXl());
			return	totalDao.update(olddud)+"";
		}
		return null;
	}

	@Override
	public String updateRankCounts(RankingCounts rankCounts) {
		if(rankCounts!=null){
			int count =	totalDao.getCount("from RankingCounts where groups=? and ranking =? ", rankCounts.getGroups(),
					rankCounts.getRanking());
			if(count>0){
				return  rankCounts.getGroups()+"已设置过第"+rankCounts.getRanking()+"名次，请重新设置。";
			}
			RankingCounts old =	(RankingCounts) totalDao.get(RankingCounts.class, rankCounts.getId());
			old.setRanking(old.getRanking());
			old.setCounts(old.getCounts());
			old.setFenPeiXIshu(old.getFenPeiXIshu());
			return totalDao.update(old)+"";
		}
		return null;
	}

	@Override
	public String updateSac(SalesAmountCoefficient sac) {
		if(sac!=null){
			SalesAmountCoefficient oldsac =	(SalesAmountCoefficient) totalDao.get(SalesAmountCoefficient.class, sac.getId());
			oldsac.setLowAmount(sac.getLowAmount());
			oldsac.setCeilingAmount(sac.getCeilingAmount());
			oldsac.setCoefficient(sac.getCoefficient());
			oldsac.setDanwei(sac.getDanwei());
			return	totalDao.update(oldsac)+"";
		}
		return null;
	}

	@Override
	public String updateTam(TargetAchievedMark tam) {
		if(tam!=null){
			TargetAchievedMark oldtam =	(TargetAchievedMark) totalDao.get(TargetAchievedMark.class, tam.getId());
			oldtam.setDeptName(tam.getDeptName());
			oldtam.setMarks(tam.getMarks());
			return	totalDao.update(oldtam)+"";
		}
		return null;
	}

	@Override
	public String updateWwJgMb(WaiWeiJieGouMuBiao wwjgmb) {
		if(wwjgmb!=null){
			WaiWeiJieGouMuBiao oldwwjgmb = 	(WaiWeiJieGouMuBiao) totalDao.get(WaiWeiJieGouMuBiao.class, wwjgmb.getId());
			oldwwjgmb.setWaiweiMuBiao(wwjgmb.getWaiweiMuBiao());
			oldwwjgmb.setJieGouMuBiao(wwjgmb.getJieGouMuBiao());
			return totalDao.update(oldwwjgmb)+"";
		}
		return null;
	}

	@Override
	public SalesAmountCoefficient updateMaxSac() {
		SalesAmountCoefficient sac = (SalesAmountCoefficient) totalDao.getObjectByCondition(" from SalesAmountCoefficient where ceilingAmount is null ");
		if(sac!=null){
			Float ceilingAmount =	sac.getLowAmount()+500;
			sac.setCeilingAmount(ceilingAmount);
			totalDao.update(sac);
			return sac;
		}else{
			sac = (SalesAmountCoefficient) totalDao.getObjectByCondition(" from SalesAmountCoefficient order by  ceilingAmount desc");
			if(sac!=null){
				return sac;
			}else{
				sac = new SalesAmountCoefficient();
				sac.setCeilingAmount(0f);
			}
		}
		return sac;
	}

	@Override
	public String addBmzZlh(BmzZlh bmzzlh) {
		Users users =	Util.getLoginUser();
		if(users == null){
			return "请先登录!~";
		}
		if(bmzzlh!=null){
			int count =totalDao.getCount(" from BmzZlh" +
					" where and dept=? and leaderName=? and months=?", 
					bmzzlh.getDept(),bmzzlh.getLeaderName(),bmzzlh.getMonths());
			if(count>0){
				return "数据重复。"+bmzzlh.getDept()+"部门的"+bmzzlh.getLeaderName()+
				bmzzlh.getMonths()+"已添加过，无需重复添加";
			}
			Integer monthsNo = Util.printFieldDate("M", bmzzlh.getMonths(), "yyyy-MM");
			int jidu =	(int) Math.ceil(monthsNo/3d);
			bmzzlh.setMonthsNo(monthsNo);
			bmzzlh.setJidu(jidu);
			bmzzlh.setScore((bmzzlh.getAddscore()==null?0:bmzzlh.getAddscore())
					-(bmzzlh.getReducescore()==null?0:bmzzlh.getReducescore())		
			);
			bmzzlh.setAddTime(Util.getDateTime());
			bmzzlh.setAddUsersName(users.getName());
			boolean bool = totalDao.save(bmzzlh);
			if(bool){
				List<BmzZlh> bmzzlhList =totalDao.query("from BmzZlh where months=? order by score desc", bmzzlh.getMonths());
				int ranking =1;
				for (BmzZlh bmzZlh2 : bmzzlhList) {
					bmzZlh2.setRanking(ranking);
					ranking++;
					bool =	totalDao.update(bmzZlh2);
				}
			}
			return bool+"";
			
		}
		return null;
	}
	@Override
	public String addBmzZlh0(int id,BmzZlh bmzzlh) {
		Users users =	Util.getLoginUser();
		if(users == null){
			return "请先登录!~";
		}
		if(id>0 && bmzzlh!=null){
			DeptUsersDuty dud =	findDudById(id);
			BmzZlh  bmzzlh0=	(BmzZlh) totalDao.getObjectByCondition(" from BmzZlh where months=? and leaderName=? and dept=?",
					bmzzlh.getMonths(),dud.getLeader(),dud.getDeptName());
			if(bmzzlh0!=null){
				if(bmzzlh.getAddscore()!=null){
					bmzzlh0.setAddscore(bmzzlh.getAddscore());
				}
				if(bmzzlh.getReducescore()!=null){
					bmzzlh0.setReducescore(bmzzlh.getReducescore());
				}
				bmzzlh0.setScore((bmzzlh0.getAddscore()==null?0:bmzzlh0.getAddscore())
						-(bmzzlh0.getReducescore()==null?0:bmzzlh0.getReducescore())		
				);
				boolean bool = 	totalDao.update(bmzzlh0);
				if(bool){
					List<BmzZlh> bmzzlhList =totalDao.query("from BmzZlh where months=? order by score desc", bmzzlh.getMonths());
					int ranking =1;
					for (BmzZlh bmzZlh2 : bmzzlhList) {
						bmzZlh2.setRanking(ranking);
						ranking++;
						bool =	totalDao.update(bmzZlh2);
					}
				}
				return bool+"";
			}else{
				Integer monthsNo = Util.printFieldDate("M", bmzzlh.getMonths(), "yyyy-MM");
				int jidu =	(int) Math.ceil(monthsNo/3d);
				bmzzlh.setMonthsNo(monthsNo);
				bmzzlh.setJidu(jidu);
				bmzzlh.setScore((bmzzlh.getAddscore()==null?0:bmzzlh.getAddscore())
						-(bmzzlh.getReducescore()==null?0:bmzzlh.getReducescore())		
				);
				bmzzlh.setDept(dud.getDeptName());
				bmzzlh.setLeaderName(dud.getLeader());
				bmzzlh.setRank(dud.getRank());
				bmzzlh.setRankNo(dud.getRankNo());
				bmzzlh.setAddTime(Util.getDateTime());
				bmzzlh.setAddUsersName(users.getName());
				boolean bool = totalDao.save(bmzzlh);
				if(bool){
					List<BmzZlh> bmzzlhList =totalDao.query("from BmzZlh where months=? order by score desc", bmzzlh.getMonths());
					int ranking =1;
					for (BmzZlh bmzZlh2 : bmzzlhList) {
						bmzZlh2.setRanking(ranking);
						ranking++;
						bool =	totalDao.update(bmzZlh2);
					}
				}
				return bool+"";
			}
		}
		return null;
	}
	@Override
	public String addWeiWaiJieGou(WeiWaiJieGou wwjg) {
		Users users =	Util.getLoginUser();
		if(users == null){
			return "请先登录!~";
		}
		if(wwjg!=null){
			int count = totalDao.getCount("from WeiWaiJieGou where months =?", wwjg.getMonths());
			if(count>0){
				return wwjg.getMonths()+"月，已添加过委外比结构比，无需重复添加!~";
			}
			int month0 = Util.printFieldDate("M", wwjg.getMonths(), "yyyy-MM");
			wwjg.setMonth0(month0);
			wwjg.setAddTime(Util.getDateTime());
			wwjg.setAddUsersName(users.getName());
			return totalDao.save(wwjg)+"";
		}
		return null;
	}

	@Override
	public String addWorkShopXiaoLvZz(WorkShopXiaoLvZz wsxlz) {
		Users users =	Util.getLoginUser();
		if(users == null){
			return "请先登录!~";
		}
		if(wsxlz!=null){
			WorkShopXiaoLvZz wsxlz0 = (WorkShopXiaoLvZz) totalDao.getObjectByCondition(" from WorkShopXiaoLvZz where deptId =? and months=?",
					wsxlz.getDeptId(),wsxlz.getMonths());
			if(wsxlz0!=null){
				wsxlz0.setXiaolv(wsxlz.getXiaolv());
				return	totalDao.update(wsxlz0)+"";
			}
			DeptUsersDuty  dud =	findDudById(wsxlz.getDeptId());
			wsxlz.setDept(dud.getDeptName());
			wsxlz.setAddTime(Util.getDateTime());
			wsxlz.setAddUsersName(users.getName());
			return	totalDao.save(wsxlz)+"";
		}
		return null;
	}

	@Override
	public String addZhiZaoPingJia(ZhiZaoPingJia zzpj) {
		Users users =	Util.getLoginUser();
		if(users == null){
			return "请先登录!~";
		}
		if(zzpj!=null){
			int count = totalDao.getCount(" from ZhiZaoPingJia where dept =? and months=?", zzpj.getDept(),zzpj.getMonths());
			if(count >0){
				int month0 = Util.printFieldDate("M", zzpj.getMonths(), "yyyy-MM");
				zzpj.setYears(Util.getDateTime("yyyy"));
				zzpj.setMonth0(month0);
				zzpj.setAddTime(Util.getDateTime());
				zzpj.setAddUsersName(users.getName());
				totalDao.save(zzpj);
				return	totalDao.save(zzpj)+"";
			}
		}
		return null;
	}

	@Override
	public String delBmzZlh(BmzZlh bmzzlh) {
		if(bmzzlh!=null){
			bmzzlh =  (BmzZlh) totalDao.get(BmzZlh.class, bmzzlh.getId());
			return	totalDao.delete(bmzzlh)+"";
		}
		return null;
	}

	@Override
	public String delWsxlz(WorkShopXiaoLvZz wsxlz) {
		if(wsxlz!=null){
			return totalDao.delete(wsxlz)+"";
		}
		return null;
	}

	@Override
	public String delWwjg(WeiWaiJieGou wwjg) {
		if(wwjg!=null){
			return totalDao.delete(wwjg)+"";
		}
		return null;
	}

	@Override
	public String delZzpj(ZhiZaoPingJia zzpj) {
		if(zzpj!=null){
			return	totalDao.delete(zzpj)+"";
		}
		return null;
	}

	@Override
	public Object[] findAllBmzZlhList(BmzZlh bmzzlh, Integer pageSize,
			Integer pageNo, String status) {
		if(bmzzlh==null){
			bmzzlh = new BmzZlh();
		}
		String hql =totalDao.criteriaQueries(bmzzlh, null);
		hql+=" and months like '"+Util.getDateTime("yyyy")+"%' order by months ";
		List<BmzZlh> bmzzlhList =totalDao.findAllByPage(hql, pageNo, pageSize);
		int count =	totalDao.getCount(hql);
		Integer monthsNo =1;
		if(bmzzlhList!=null && bmzzlhList.size()>0){
			BmzZlh bmzzlh0 =	bmzzlhList.get(0);
			monthsNo = bmzzlh0.getMonthsNo();
		}
		return new Object[]{bmzzlhList,count};
	}

	@Override
	public Object[] findAllWsxlzList(WorkShopXiaoLvZz wsxlz, Integer pageSize,
			Integer pageNo, String status) {
		if(wsxlz!=null){
			wsxlz = new WorkShopXiaoLvZz();
		}
		String hql =totalDao.criteriaQueries(wsxlz, null);
		List<WorkShopXiaoLvZz> wsxlzList =totalDao.findAllByPage(hql, pageNo, pageSize);
		int count =	totalDao.getCount(hql);
		return new Object[]{wsxlzList,count};
	}

	@Override
	public Object[] findAllWwjgList(WeiWaiJieGou wwjg, Integer pageSize,
			Integer pageNo, String status) {
		if(wwjg!=null){
			wwjg = new WeiWaiJieGou();
		}
		String hql =totalDao.criteriaQueries(wwjg, null);
		List<WeiWaiJieGou> wwjgList =totalDao.findAllByPage(hql, pageNo, pageSize);
		int count =	totalDao.getCount(hql);
		return new Object[]{wwjgList,count};
	}

	@Override
	public Object[] findAllZzpjList(ZhiZaoPingJia zzpj, Integer pageSize,
			Integer pageNo, String status) {
		if(zzpj!=null){
			zzpj = new ZhiZaoPingJia();
		}
		String hql =totalDao.criteriaQueries(zzpj, null);
		List<ZhiZaoPingJia> zzpjList =totalDao.findAllByPage(hql, pageNo, pageSize);
		int count =	totalDao.getCount(hql);
		return new Object[]{zzpjList,count};
	}

	@Override
	public Object[] findWsxlzBy(WorkShopXiaoLvZz wsxlz) {
		if(wsxlz==null){
			wsxlz = new WorkShopXiaoLvZz();
		}
		String hql =totalDao.criteriaQueries(wsxlz, null);
		List<WorkShopXiaoLvZz> wsxlzList = totalDao.query(hql);
		Integer monthsNo0 = 13;
		Integer monthsNo1 =12;
		if(wsxlzList!=null && wsxlzList.size() >0){
			monthsNo0 = wsxlzList.get(0).getMonth0();
			monthsNo1 = wsxlzList.get(wsxlzList.size()-1).getMonth0();
		}
		return new Object[]{wsxlzList,monthsNo0,monthsNo1};
	}

	@Override
	public WorkShopXiaoLvZz findWsxlzById(Integer id) {
		if(id!=null){
			return (WorkShopXiaoLvZz) totalDao.get(WorkShopXiaoLvZz.class, id);
		}
		return null;
	}

	@Override
	public Object[] findWwjgBy(WeiWaiJieGou wwjg) {
		if(wwjg==null){
			wwjg = new WeiWaiJieGou();
		}
		String hql =totalDao.criteriaQueries(wwjg, null);
		hql+=" order by months ";
		List<WeiWaiJieGou> wwjgList =	 totalDao.query(hql);
		Integer monthsNo0 = 13;
		Integer monthsNo1 =12;
		if(wwjgList!=null && wwjgList.size() >0){
			monthsNo0 = wwjgList.get(0).getMonth0();
			monthsNo1 = wwjgList.get(wwjgList.size()-1).getMonth0();
		}
		 return new Object[]{wwjgList,monthsNo0,monthsNo1};
	}

	@Override
	public WeiWaiJieGou findWwjgById(Integer id) {
		if(id!=null){
			return (WeiWaiJieGou) totalDao.get(WeiWaiJieGou.class, id);
		}
		return null;
	}

	@Override
	public Object[] findZzpjBy(ZhiZaoPingJia zzpj) {
		if(zzpj==null){
			zzpj = new ZhiZaoPingJia();
		}
		String hql =totalDao.criteriaQueries(zzpj, null);
		List<ZhiZaoPingJia> zzpjList =	totalDao.query(hql);
		Integer monthsNo0 = 13;
		Integer monthsNo1 =12;
		if(zzpjList!=null && zzpjList.size() >0){
			monthsNo0 = zzpjList.get(0).getMonth0();
			monthsNo1 = zzpjList.get(zzpjList.size()-1).getMonth0();
		}
		 return  new Object[]{zzpjList,monthsNo0,monthsNo1};
	}

	@Override
	public ZhiZaoPingJia findZzpjById(Integer id) {
		if(id!=null){
			return (ZhiZaoPingJia) totalDao.get(ZhiZaoPingJia.class, id);
		}
		return null;
	}

	@Override
	public Object[] findbmzzlList(BmzZlh bmzzlh) {
		if(bmzzlh==null){
			bmzzlh = new BmzZlh();
		}
		String hql =totalDao.criteriaQueries(bmzzlh, null);
		hql+=" order by months ";
		List<BmzZlh> bmzzlhList = totalDao.query(hql);
		Integer monthsNo = 13;
		Integer monthsNo0 =12;
		if(bmzzlhList!=null && bmzzlhList.size()>0){
			monthsNo = bmzzlhList.get(0).getMonthsNo();
			monthsNo0 =bmzzlhList.get(bmzzlhList.size()-1).getMonthsNo();
		}
		return new Object[]{bmzzlhList,monthsNo,monthsNo0};
	}

	@Override
	public BmzZlh findbmzzlhById(Integer id) {
		if(id!=null){
			return (BmzZlh) totalDao.get(BmzZlh.class, id);
		}
		return null;
	}

	@Override
	public String updateBmzZlh(BmzZlh bmzzlh) {
		if(bmzzlh!=null){
			BmzZlh old  =(BmzZlh) totalDao.get(BmzZlh.class, bmzzlh.getId());
			old.setAddscore(bmzzlh.getAddscore());
			old.setReducescore(bmzzlh.getReducescore());
			return	totalDao.update(old)+"";
		}
		return null;
	}

	@Override
	public String updateWsxlz(WorkShopXiaoLvZz wsxlz) {
		if(wsxlz!=null){
			WorkShopXiaoLvZz old =	(WorkShopXiaoLvZz) totalDao.get(WorkShopXiaoLvZz.class, wsxlz.getId());
			old.setXiaolv(wsxlz.getXiaolv());
			return	totalDao.update(old)+"";
		}
		return null;
	}

	@Override
	public String updateWwjg(WeiWaiJieGou wwjg) {
		if(wwjg!=null){
			WeiWaiJieGou old =(WeiWaiJieGou) totalDao.get(WeiWaiJieGou.class, wwjg.getId());
			old.setChangzhi(wwjg.getChangzhi());
			old.setBgbl(wwjg.getBgbl());
			old.setWwgj(wwjg.getWwgj());
			old.setWwb(wwjg.getWwb());
			old.setWwxs(wwjg.getWwxs());
			old.setRkcz(wwjg.getRkcz());
			old.setScBOM(wwjg.getScBOM());
			old.setJgb(wwjg.getJgb());
			old.setJgxs(wwjg.getJgxs());
			return totalDao.update(old)+"";
		}
		return null;
	}

	@Override
	public String updateZzpj(ZhiZaoPingJia zzpj) {
		if(zzpj!=null){
			ZhiZaoPingJia old= (ZhiZaoPingJia) totalDao.get(ZhiZaoPingJia.class, zzpj.getClass());
			old.setXiShu(zzpj.getXiShu());
			return	totalDao.update(old)+"";
		}
		return null;
	}

	@Override
	public String addWeiWaiJieGou0(WeiWaiJieGou wwjg,String status) {
		Users users =	Util.getLoginUser();
		if(users == null){
			return "请先登录!~";
		}
		if(wwjg!=null){
			WeiWaiJieGou wwjg0 = (WeiWaiJieGou) totalDao.getObjectByCondition(" from WeiWaiJieGou where months=?", wwjg.getMonths());
			if(wwjg0==null){
				wwjg.setAddTime(Util.getDateTime());
				wwjg.setAddUsersName(users.getName());
				if("changzhi".equals(status)){
					wwjg.setChangzhi(wwjg.getStr());	
				}else if("bgbl".equals(status)){
					wwjg.setBgbl(wwjg.getStr());
				}else if("gxww".equals(status)){
					wwjg.setGxww(wwjg.getStr());
				}else if("wwxs".equals(status)){
					wwjg.setWwxs(wwjg.getStr());
				}else if("rkcz".equals(status)){
					wwjg.setRkcz(wwjg.getStr());
				}else if("scBOM".equals(status)){
					wwjg.setScBOM(wwjg.getStr());
				}else if("jgxs".equals(status)){
					wwjg.setJgxs(wwjg.getStr());
				}
				return	totalDao.save(wwjg)+"";
			}else{
				if("changzhi".equals(status)){
					wwjg0.setChangzhi(wwjg.getStr());	
				}else if("bgbl".equals(status)){
					wwjg0.setBgbl(wwjg.getStr());
				}else if("gxww".equals(status)){
					wwjg0.setGxww(wwjg.getStr());
				}else if("wwxs".equals(status)){
					wwjg0.setWwxs(wwjg.getStr());
				}else if("rkcz".equals(status)){
					wwjg0.setRkcz(wwjg.getStr());
				}else if("scBOM".equals(status)){
					wwjg0.setScBOM(wwjg.getStr());
				}else if("jgxs".equals(status)){
					wwjg0.setJgxs(wwjg.getStr());
				}
				if(wwjg0.getChangzhi()!=null
						&& wwjg0.getBgbl()!=null
						&& wwjg0.getGxww()!=null){
					wwjg0.setWwgj(wwjg0.getBgbl()+wwjg0.getGxww());
					wwjg0.setWwb(Util.FomartFloat(wwjg0.getWwgj()/wwjg0.getChangzhi(), 4));
				}
				if(wwjg0.getRkcz()!=null
						&& wwjg0.getScBOM()!=null){
					wwjg0.setJgb(Util.FomartFloat(wwjg0.getScBOM()/wwjg0.getRkcz(), 4));
				}
				return	totalDao.update(wwjg0)+"";
			}
		}
		return null;
	}

	@Override
	public String addZzpj(ZhiZaoPingJia zzpj) {
		Users users =	Util.getLoginUser();
		if(users == null){
			return "请先登录!~";
		}
		if(zzpj!=null){
			ZhiZaoPingJia zzpj0 =(ZhiZaoPingJia) totalDao.getObjectByCondition(" from ZhiZaoPingJia where dudId=? and months =? ",zzpj.getDudId(),zzpj.getMonths());
			if(zzpj0 == null){
				DeptUsersDuty dud =	findDudById(zzpj.getDudId());
				if(dud!=null){
					zzpj.setDept(dud.getDeptName());
					zzpj.setName(dud.getLeader());
					zzpj.setZhiwei(dud.getRank());
				}
				zzpj.setAddUsersName(users.getName());
				zzpj.setAddTime(Util.getDateTime());
				return	totalDao.save(zzpj)+"";
			}else{
				zzpj0.setXiShu(zzpj.getXiShu());
				return	totalDao.update(zzpj0)+"";
			}
		}
		return null;
	}

	@Override
	public String addZbSjZk(ZbSjZk zbSjZk) {
		Users users =	Util.getLoginUser();
		if(users == null){
			return "请先登录!~";
		}
		if(zbSjZk!=null){
			zbSjZk.setAddTime(Util.getDateTime());
			zbSjZk.setAddUsersName(users.getName());
			int month0 = Util.printFieldDate("M", zbSjZk.getMonths(), "yyyy-MM");
			zbSjZk.setMonth0(month0);
			String hql_tam = "from TargetAchievedMark where targetName =? and deptName =? and marks is not null and marks>0";
			//LAR
			char[] chars0 =	zbSjZk.getLarzhimubiao().toCharArray();
			char[] chars1 =	zbSjZk.getLarzhibiao().toCharArray();
			int a =chars0[0];
			int b =chars1[0];
			TargetAchievedMark tam_lar =(TargetAchievedMark) totalDao.getObjectByCondition(hql_tam, "LAR",zbSjZk.getDept());
			if(tam_lar!=null){
				if(a>=b){
					zbSjZk.setLarscore(tam_lar.getMarks());
				}else{
					zbSjZk.setLarscore(-tam_lar.getMarks());
				}
			}else{
				return "未设置部门:"+zbSjZk.getDept()+"目标项:LAR的加减分值。";
			}
			//RID
			TargetAchievedMark tam_rid =(TargetAchievedMark) totalDao.getObjectByCondition(hql_tam, "RID",zbSjZk.getDept());
			if(tam_rid!=null){
				if(zbSjZk.getRidmubiao()<=zbSjZk.getRidzhibiao()){
					zbSjZk.setRidscore(tam_rid.getMarks());
				}else{
					zbSjZk.setRidscore(-tam_rid.getMarks());
				}
			}else{
				return "未设置部门:"+zbSjZk.getDept()+"目标项:RID的加减分值。";
			}
			//总销售产值(亿)
			TargetAchievedMark tam_zxscz =(TargetAchievedMark) totalDao.getObjectByCondition(hql_tam, "总销售产值(亿)",zbSjZk.getDept());
			if(tam_zxscz!=null){
				if(zbSjZk.getZczmubiao()<=zbSjZk.getZczzhibiao()){
					zbSjZk.setZczscore(tam_zxscz.getMarks());
				}else{
					zbSjZk.setZczscore(-tam_zxscz.getMarks());
				}
			}else{
				return "未设置部门:"+zbSjZk.getDept()+"目标项:总销售产值(亿)的加减分值。";
			}
			//人均销售(万)
			TargetAchievedMark tam_rjxs =(TargetAchievedMark) totalDao.getObjectByCondition(hql_tam, "人均销售(万)",zbSjZk.getDept());
			if(tam_rjxs!=null){
				if(zbSjZk.getRjxsmubiao()<=zbSjZk.getRjxszhibiao()){
					zbSjZk.setRjxsscore(tam_rjxs.getMarks());
				}else{
					zbSjZk.setRjxsscore(-tam_rjxs.getMarks());
				}
			}else{
				return "未设置部门:"+zbSjZk.getDept()+"目标项:人均销售(万)的加减分值。";
			}
			//实际人数
			TargetAchievedMark tam_sjrs =(TargetAchievedMark) totalDao.getObjectByCondition(hql_tam, "实际人数",zbSjZk.getDept());
			if(tam_sjrs!=null){
				if(zbSjZk.getSjrsmubiao()<=zbSjZk.getSjrszhibiao()){
					zbSjZk.setSjrsscore(tam_sjrs.getMarks());
				}else{
					zbSjZk.setSjrsscore(-tam_sjrs.getMarks());
				}
			}else{
				return "未设置部门:"+zbSjZk.getDept()+"目标项:实际人数的加减分值。";
			}
			//三按两遵守
			TargetAchievedMark tam_salzs =(TargetAchievedMark) totalDao.getObjectByCondition(hql_tam, "三按两遵守",zbSjZk.getDept());
			if(tam_salzs!=null){
				if(zbSjZk.getSalzsmubiao()<=zbSjZk.getSalzszhibiao()){
					zbSjZk.setSalzsscore(tam_salzs.getMarks());
				}else{
					zbSjZk.setSalzsscore(-tam_salzs.getMarks());
				}
			}else{
				return "未设置部门:"+zbSjZk.getDept()+"目标项:三按两遵守的加减分值。";
			}
			//变动费用(万)
			TargetAchievedMark tam_bdfy =(TargetAchievedMark) totalDao.getObjectByCondition(hql_tam, "变动费用(万)",zbSjZk.getDept());
			if(tam_bdfy!=null){
				if(zbSjZk.getBdfymubiao()<=zbSjZk.getBdfyzhibiao()){
					zbSjZk.setBdfyscore(tam_bdfy.getMarks());
				}else{
					zbSjZk.setBdfyscore(-tam_bdfy.getMarks());
				}
			}else{
				return "未设置部门:"+zbSjZk.getDept()+"目标项:变动费用(万)的加减分值。";
			}
			
			Integer	sumscocer = zbSjZk.getLarscore()+zbSjZk.getRidscore()+
				zbSjZk.getZczscore()+zbSjZk.getRjxsscore()+zbSjZk.getSjrsscore()+
				zbSjZk.getSalzsscore()+zbSjZk.getBdfyscore();
			zbSjZk.setSumscocer(sumscocer);
			boolean bool  =	totalDao.save(zbSjZk);
			//计算名次. 副厂长(6)，厂长(7)，总助 (8)一起排名。 总监(5)，经理(4) 一起排名
			String hql_paiming = "from ZbSjZk where months=? ";
			if(zbSjZk.getRankNo()>5 && zbSjZk.getRankNo()<=8){
				hql_paiming+=" and rankNo>=6 and rankNo<=8";
			}else{
				hql_paiming+=" and rankNo>=4 and rankNo<=5";
			}
			hql_paiming+=" group by sumscocer desc";
			List<ZbSjZk> zbsjzkList =	totalDao.query(hql_paiming, zbSjZk.getMonths());
			for(int i=0;i<zbsjzkList.size();i++){
				ZbSjZk zbSjZk0 = zbsjzkList.get(i);
				zbSjZk0.setRanking(i+1);
				bool =	totalDao.update(zbSjZk0);
			}
			return bool+"";
		}
		return null;
	}

	@Override
	public String delZbSjZk(ZbSjZk zbSjZk) {
		if(zbSjZk!=null){
			 zbSjZk =(ZbSjZk) totalDao.get(ZbSjZk.class, zbSjZk.getId());
			return 	totalDao.delete(zbSjZk)+"";
		}
		return null;
	}

	@Override
	public Object[] findAllZbSjZk(ZbSjZk zbSjZk,Integer pageSize,Integer pageNo,String status) {
		if(zbSjZk == null){
			zbSjZk = new ZbSjZk();
		}
		if(zbSjZk.getMonths()==null || "".equals(zbSjZk.getMonths())){
			zbSjZk.setMonths(Util.getDateTime("yyyy-MM"));
		}
		String hql =totalDao.criteriaQueries(zbSjZk,null);
		List<ZbSjZk> zbSjZkList =	totalDao.findAllByPage(hql, pageNo, pageSize, null);
		int count =	totalDao.getCount(hql);
		
		return new Object[]{zbSjZkList,count};
	}

	@Override
	public String updateZbSjZk(ZbSjZk zbSjZk) {
		if(zbSjZk!=null){
			ZbSjZk oldZbSjZk =	(ZbSjZk) totalDao.get(ZbSjZk.class, zbSjZk.getId());
			oldZbSjZk.setLardyDept(zbSjZk.getLardyDept());
			oldZbSjZk.setLarzhibiao(zbSjZk.getLarzhibiao());
			oldZbSjZk.setRiddyDept(zbSjZk.getRiddyDept());
			oldZbSjZk.setRidzhibiao(zbSjZk.getRidzhibiao());
			oldZbSjZk.setZczdyDept(zbSjZk.getZczdyDept());
			oldZbSjZk.setZczzhibiao(zbSjZk.getZczzhibiao());
			oldZbSjZk.setRjxsdyDept(zbSjZk.getRjxsdyDept());
			oldZbSjZk.setRjxszhibiao(zbSjZk.getRjxszhibiao());
			oldZbSjZk.setSjrsdyDept(zbSjZk.getSjrsdyDept());
			oldZbSjZk.setSjrszhibiao(zbSjZk.getSjrszhibiao());
			oldZbSjZk.setSalzsdyDept(zbSjZk.getSalzsdyDept());
			oldZbSjZk.setSalzszhibiao(zbSjZk.getSalzszhibiao());
			oldZbSjZk.setBdfydyDept(zbSjZk.getBdfydyDept());
			oldZbSjZk.setBdfyzhibiao(zbSjZk.getBdfyzhibiao());
			
			String hql_tam = "from TargetAchievedMark where targetName =? and deptName =? and marks is not null and marks>0";
			//LAR
			char[] chars0 =	oldZbSjZk.getLarzhimubiao().toCharArray();
			char[] chars1 =	oldZbSjZk.getLarzhibiao().toCharArray();
			int a =chars0[0];
			int b =chars1[0];
			TargetAchievedMark tam_lar =(TargetAchievedMark) totalDao.getObjectByCondition(hql_tam, "LAR",oldZbSjZk.getDept());
			if(tam_lar!=null){
				if(a>=b){
					oldZbSjZk.setLarscore(tam_lar.getMarks());
				}else{
					oldZbSjZk.setLarscore(-tam_lar.getMarks());
				}
			}else{
				return "未设置部门:"+zbSjZk.getDept()+"目标项:LAR的加减分值。";
			}
			//RID
			TargetAchievedMark tam_rid =(TargetAchievedMark) totalDao.getObjectByCondition(hql_tam, "RID",oldZbSjZk.getDept());
			if(tam_rid!=null){
				if(oldZbSjZk.getRidmubiao()<=oldZbSjZk.getRidzhibiao()){
					oldZbSjZk.setRidscore(tam_rid.getMarks());
				}else{
					oldZbSjZk.setRidscore(-tam_rid.getMarks());
				}
			}else{
				return "未设置部门:"+oldZbSjZk.getDept()+"目标项:RID的加减分值。";
			}
			//总销售产值(亿)
			TargetAchievedMark tam_zxscz =(TargetAchievedMark) totalDao.getObjectByCondition(hql_tam, "总销售产值(亿)",oldZbSjZk.getDept());
			if(tam_zxscz!=null){
				if(oldZbSjZk.getZczmubiao()<=oldZbSjZk.getZczzhibiao()){
					oldZbSjZk.setZczscore(tam_zxscz.getMarks());
				}else{
					oldZbSjZk.setZczscore(-tam_zxscz.getMarks());
				}
			}else{
				return "未设置部门:"+oldZbSjZk.getDept()+"目标项:总销售产值(亿)的加减分值。";
			}
			//人均销售(万)
			TargetAchievedMark tam_rjxs =(TargetAchievedMark) totalDao.getObjectByCondition(hql_tam, "人均销售(万)",oldZbSjZk.getDept());
			if(tam_rjxs!=null){
				if(oldZbSjZk.getRjxsmubiao()<=zbSjZk.getRjxszhibiao()){
					oldZbSjZk.setRjxsscore(tam_rjxs.getMarks());
				}else{
					oldZbSjZk.setRjxsscore(-tam_rjxs.getMarks());
				}
			}else{
				return "未设置部门:"+zbSjZk.getDept()+"目标项:人均销售(万)的加减分值。";
			}
			//实际人数
			TargetAchievedMark tam_sjrs =(TargetAchievedMark) totalDao.getObjectByCondition(hql_tam, "实际人数",oldZbSjZk.getDept());
			if(tam_sjrs!=null){
				if(oldZbSjZk.getSjrsmubiao()<=oldZbSjZk.getSjrszhibiao()){
					oldZbSjZk.setSjrsscore(tam_sjrs.getMarks());
				}else{
					oldZbSjZk.setSjrsscore(-tam_sjrs.getMarks());
				}
			}else{
				return "未设置部门:"+oldZbSjZk.getDept()+"目标项:实际人数的加减分值。";
			}
			//三按两遵守
			TargetAchievedMark tam_salzs =(TargetAchievedMark) totalDao.getObjectByCondition(hql_tam, "三按两遵守",oldZbSjZk.getDept());
			if(tam_salzs!=null){
				if(oldZbSjZk.getSalzsmubiao()<=oldZbSjZk.getSalzszhibiao()){
					oldZbSjZk.setSalzsscore(tam_salzs.getMarks());
				}else{
					oldZbSjZk.setSalzsscore(-tam_salzs.getMarks());
				}
			}else{
				return "未设置部门:"+oldZbSjZk.getDept()+"目标项:三按两遵守的加减分值。";
			}
			//变动费用(万)
			TargetAchievedMark tam_bdfy =(TargetAchievedMark) totalDao.getObjectByCondition(hql_tam, "变动费用(万)",oldZbSjZk.getDept());
			if(tam_bdfy!=null){
				if(oldZbSjZk.getBdfymubiao()<=oldZbSjZk.getBdfyzhibiao()){
					oldZbSjZk.setBdfyscore(tam_bdfy.getMarks());
				}else{
					oldZbSjZk.setBdfyscore(-tam_bdfy.getMarks());
				}
			}else{
				return "未设置部门:"+oldZbSjZk.getDept()+"目标项:变动费用(万)的加减分值。";
			}
			
			Integer	sumscocer = oldZbSjZk.getLarscore()+oldZbSjZk.getRidscore()+
			oldZbSjZk.getZczscore()+oldZbSjZk.getRjxsscore()+oldZbSjZk.getSjrsscore()+
			oldZbSjZk.getSalzsscore()+oldZbSjZk.getBdfyscore();
			oldZbSjZk.setSumscocer(sumscocer);
			boolean bool  =	totalDao.update(oldZbSjZk);
			//计算名次. 副厂长(6)，厂长(7)，总助 (8)一起排名。 总监(5)，经理(4) 一起排名
			String hql_paiming = "from ZbSjZk where months=? ";
			if(oldZbSjZk.getRankNo()>5 && oldZbSjZk.getRankNo()<=8){
				hql_paiming+=" and rankNo>=6 and rankNo<=8";
			}else{
				hql_paiming+=" and rankNo>=4 and rankNo<=5";
			}
			hql_paiming+=" group by sumscocer desc";
			List<ZbSjZk> zbsjzkList =	totalDao.query(hql_paiming, oldZbSjZk.getMonths());
			for(int i=0;i<zbsjzkList.size();i++){
				ZbSjZk zbSjZk0 = zbsjzkList.get(i);
				zbSjZk0.setRanking(i+1);
				bool =	totalDao.update(zbSjZk0);
			}
			return bool+"";
		}
		return null;
	}

	@Override
	public ZbSjZk findzbSjZkbyId(Integer id) {
		if(id!=null){
			return (ZbSjZk) totalDao.get(ZbSjZk.class, id);
		}
		return null;
	}

	@Override
	public String addWstqmx(WorkShopTiQuMx wstqmx) {
		Users users =	Util.getLoginUser();
		if(users == null){
			return "请先登录!~";
		}
		if(wstqmx!=null){
			wstqmx.setAddUsersName(users.getName());
			wstqmx.setAddTime(Util.getDateTime());
			return	totalDao.save(wstqmx)+"";
		}
		return null;
	}

	@Override
	public String delWstqmx(WorkShopTiQuMx wstqmx) {
		Users users =	Util.getLoginUser();
		if(users == null){
			return "请先登录!~";
		}
		if(wstqmx!=null){
			return totalDao.delete(wstqmx)+"";
		}
		return null;
	}

	@Override
	public Object[] findAllWstqmx(WorkShopTiQuMx wstqmx, Integer pageSize,
			Integer pageNo, String status) {
		if(wstqmx == null){
			wstqmx = new WorkShopTiQuMx();
		}
		String hql =	totalDao.criteriaQueries(wstqmx, null);
		List<WorkShopTiQuMx> wstqmxList =	totalDao.findAllByPage(hql, pageNo, pageSize, null);
		int count =	totalDao.getCount(hql);
		return new Object[]{wstqmxList,count};
	}

	@Override
	public WorkShopTiQuMx findWstqmxById(Integer id) {
		if(id!=null){
			return (WorkShopTiQuMx) totalDao.get(WorkShopTiQuMx.class, id);
		}
		return null;
	}

	@Override
	public String updateWstqmx(WorkShopTiQuMx wstqmx) {
		Users users =	Util.getLoginUser();
		if(users == null){
			return "请先登录!~";
		}
		if(wstqmx!=null){
			WorkShopTiQuMx old =findWstqmxById(wstqmx.getId());
			old.setTiquMoney(wstqmx.getTiquMoney());
			return	totalDao.update(old)+"";
		}
		return null;
	}

	@Override
	public Object[] huoquFenPeiQingkuang(String months) {
		List<FenPeiQingKuang> fpqkList =	 totalDao.query(" from FenPeiQingKuang where months =?", months);
		if(fpqkList == null || fpqkList.size()==0){
			fpqkList = new ArrayList<FenPeiQingKuang>();
			List<DeptUsersDuty> dudList =	totalDao.query("from DeptUsersDuty where isdeptFenPei =1");
			if(dudList!=null && dudList.size()>0){
				for (DeptUsersDuty dud : dudList) {
					FenPeiQingKuang fpqk = new FenPeiQingKuang();
					fpqk.setMonths(months);
					fpqk.setDept(dud.getDeptName());
					//分配系数 K;
					Double k = (Double) totalDao.getObjectByConditionforDouble("select fenpeiXiShu from DeptFenPeiXiShu where dept =?", dud.getDeptName());
					if(k!=null && k>0){
						fpqk.setK(k);
					}else{
						throw new RuntimeException("未获取到"+dud.getDeptName()+"部门"+months+"月的分配系数");
					}
					//定岗人数 C; 实际人数 B
					ZbSjZk	 zbsjzk = (ZbSjZk) totalDao.getObjectByCondition("from ZbSjZk where months =? and dept =?",months,dud.getDeptName() );
					if(zbsjzk!=null){
						fpqk.setC(zbsjzk.getSjrsmubiao());
						fpqk.setB(zbsjzk.getSjrszhibiao());
					}else{
						throw new RuntimeException("未获取到"+dud.getDeptName()+"部门"+months+"月的指标实际情况信息");
					}
					//产值系数  d1
					fpqk.setD1(1.0d);
					//结构比 d2  ; 委外比d3
					WeiWaiJieGou wwjg = (WeiWaiJieGou) totalDao.getObjectByCondition("from WeiWaiJieGou where months =?",months);
					if(wwjg!=null){
						if(wwjg.getJgb()<=wwjg.getJgbmb()){
							fpqk.setD2(1.1d);
						}else{
							fpqk.setD2(0.9d);
						}
						if(wwjg.getWwb()<=wwjg.getWwbmb()){
							fpqk.setD3(1.1d);
						}else{
							fpqk.setD3(0.9d);
						}
					}else{
						throw new RuntimeException("未获取到"+months+"月的结构比委外比信息");
					}
					//制造评价 z
					ZhiZaoPingJia zzpj =	(ZhiZaoPingJia) totalDao.getObjectByCondition("from ZhiZaoPingJia where dept =? and months =? ", dud.getDeptName(),months);
					if(zzpj!=null){
						fpqk.setZ(zzpj.getXiShu().doubleValue());
					}else{
						throw new RuntimeException("未获取到"+dud.getDeptName()+"部门"+months+"月的制造评价信息");
					}
					//本月销售额 salesAmount 
					ZbSjZk zbsjzk0 =	(ZbSjZk) totalDao.getObjectByCondition("from ZbSjZk where dept = '总经办' and months = ?", months);
					if(zbsjzk0!=null && zbsjzk0.getZczzhibiao()!=null && zbsjzk0.getZczzhibiao()>0){
						fpqk.setSalesAmount(zbsjzk0.getZczzhibiao().doubleValue());
					}else{
						throw new RuntimeException("未获取到总经办部门"+months+"月的实际总销售产值信息");
					}
					// 各部门从各车间提取情况表  wstq WorkShopTiQu;
					Set<WorkShopTiQu> wstqset = new HashSet<WorkShopTiQu>();
					List<DeptUsersDuty> dudList0 =	totalDao.query("from DeptUsersDuty where oneOrTwo =1 and isZzXl = 1 order by deptName");
					Double fenpeizjx =0d;
					if(dudList0!=null&&dudList0.size()>0){
						for (DeptUsersDuty dud0 : dudList0) {
							WorkShopTiQu wstq = new WorkShopTiQu();
							wstq.setDept(dud0.getDeptName());
							wstq.setMonths(months);
							//xiaolv （由 WorkShopXiaoLvZz:xiaolv 获取）
							WorkShopXiaoLvZz wsxlzz =	(WorkShopXiaoLvZz) totalDao.getObjectByCondition("from WorkShopXiaoLvZz where dept =? and months =? ", dud0.getDeptName(),months);
							if(wsxlzz!=null){
								wstq.setXiaolv(wsxlzz.getXiaolv().doubleValue());
							}else{
								throw	new RuntimeException("未获取到"+dud0.getDeptName()+months+"月的车间增长效率信息");
							}
							//绩效包（由 WorkShopTiQuMx : tiquMoney 获取）
							WorkShopTiQuMx wstqmx =	(WorkShopTiQuMx) totalDao.getObjectByCondition("from WorkShopTiQuMx where months=? and dept=? ", months,dud0.getDeptName());
							if(wstqmx!=null){
								wstq.setA(wstqmx.getTiquMoney());
							}else{
								throw new RuntimeException("未获取到"+dud0.getDeptName()+months+"月的从车间工资提取明细信息");
							}
							// buMenTqMoney (a*xiaolv*k*(b/c)*d1*d2*d3*z );
							Double buMenTqMoney = wstq.getA()*wstq.getXiaolv()*(fpqk.getB()/fpqk.getC())
												  *fpqk.getD1()*fpqk.getD2()*fpqk.getD3()*fpqk.getZ();
							fenpeizjx+=buMenTqMoney;
							wstqset.add(wstq);
						}
					}
					fpqk.setFenpeizjx(fpqk.getSalesAmount()>=5000?fenpeizjx:0d);
					fpqk.setWstq(wstqset);
					totalDao.save(fpqk);
					List<WorkShopTiQu> wstqList =	 totalDao.query("from WorkShopTiQu where fpqk.id=? order by dept", fpqk.getId());
					fpqk.setWstqList(wstqList);
					fpqkList.add(fpqk);
				}
				return new Object[]{fpqkList,"true"};
			}else{
				return new Object[]{fpqkList,"未获取到相关部门分配的部门"};
			}
		}else{
			for (FenPeiQingKuang fenPeiQingKuang : fpqkList) {
				List<WorkShopTiQu> wstqList =	 totalDao.query("from WorkShopTiQu where fpqk.id=? order by dept", fenPeiQingKuang.getId());
				fenPeiQingKuang.setWstqList(wstqList);
			}
			return new Object[]{fpqkList,"true"};
		}
	}

	@Override
	public String adddfpxs(DeptFenPeiXiShu dppxs) {
		Users users =	Util.getLoginUser();
		if(users == null){
			return "请先登录!~";
		}
		if(dppxs!=null){
			int count =	totalDao.getCount("from  DeptFenPeiXiShu where dept=?", dppxs.getDept());
			if(count == 0){
				dppxs.setAddTime(Util.getDateTime());
				dppxs.setAddUsersName(users.getName());
				return	totalDao.save(dppxs)+"";
			}else{
				return dppxs.getDept()+"部门，已添加过分配系数，无需重复添加!";
			}
			
		}
		return null;
	}

	@Override
	public Object[] findAlldfpxs(DeptFenPeiXiShu dppxs, Integer pageSize,
			Integer pageNo, String status) {
			if(dppxs == null){
				dppxs = new DeptFenPeiXiShu();
			}
			String hql =totalDao.criteriaQueries(dppxs,null);
			List<DeptFenPeiXiShu> 	dppxsList = totalDao.findAllByPage(hql, pageNo, pageSize,null);
			int count = totalDao.getCount(hql);
		return new Object[]{dppxsList,count};
	}

	@Override
	public String deldfpxs(DeptFenPeiXiShu dppxs) {
		Users users =	Util.getLoginUser();
		if(users == null){
			return "请先登录!~";
		}
		if(dppxs!=null){
			return totalDao.delete(dppxs)+"";
		}
		return null;
	}

	@Override
	public DeptFenPeiXiShu getdppxsById(Integer id) {
		if(id!=null){
			return (DeptFenPeiXiShu) totalDao.get(DeptFenPeiXiShu.class, id);
		}
		return null;
	}

	@Override
	public String updatedfpxs(DeptFenPeiXiShu dppxs) {
		Users users =	Util.getLoginUser();
		if(users == null){
			return "请先登录!~";
		}
		if(dppxs!=null){
			DeptFenPeiXiShu old =	(DeptFenPeiXiShu) totalDao.get(DeptFenPeiXiShu.class, dppxs.getId());
			old.setFenpeiXiShu(dppxs.getFenpeiXiShu());
			return	totalDao.update(old)+"";
		}
		return null;
	}

	@Override
	public List<DeptLeaderPenPei> huoqudlppList(String months) {
		if(months == null || "".equals(months)){
			months = Util.getDateTime("yyyy-MM");
		}
		List<DeptLeaderPenPei> dlppList = totalDao.query("from DeptLeaderPenPei where months =? ", months);
		if(dlppList == null || dlppList.size() ==0){
			dlppList = new ArrayList<DeptLeaderPenPei>();
			List<DeptUsersDuty> dudList = 	totalDao.query("from DeptUsersDuty where isJiaoXiao =1");
			if(dudList!=null && dudList.size()>0){
				StringBuffer errormsg = new StringBuffer();
				for (DeptUsersDuty dud : dudList) {
					DeptLeaderPenPei dlpp = new DeptLeaderPenPei();
					dlpp.setDept(dud.getDeptName());//部门
					dlpp.setName(dud.getLeader());//部门长姓名
					// 部门人数(定岗人数) FenPeiQingKuang(b) 获取 deptPeopelNum
					FenPeiQingKuang fpqk =	(FenPeiQingKuang) totalDao.getObjectByCondition("from FenPeiQingKuang where dept =? and months =? ", dud.getDeptName(),months);
					if(fpqk!=null){
						dlpp.setDeptPeopelNum(fpqk.getC());
						//deptAvgjx;//部门人均绩效 = 部门分配(可分配情况总绩效)/部门实际人数.
						//= FenPeiQingKuang(fenpeizjx)/FenPeiQingKuang(b)
						Double leaderjx =fpqk.getFenpeizjx()/fpqk.getB();
						dlpp.setLeaderjx(leaderjx);
					}else{
						errormsg.append("未获取到"+months+"月，"+dud.getDeptName()+"部门，部门情况信息。");
					}
					//部门长目标
					ZbSjZk zbsjzk =	(ZbSjZk) totalDao.getObjectByCondition("from ZbSjZk where dept=? and months =? ",dud.getDeptName(), months);
					if(zbsjzk!=null){
						dlpp.setDeptMubiaoScore(zbsjzk.getSumscocer());
					}else{
						errormsg.append("未获取到"+months+"月，"+dud.getDeptName()+"部门，部门长目标。");
					}
					//部门长周列会 
					BmzZlh  bmzzlh = (BmzZlh) totalDao.getObjectByCondition("from BmzZlh where dept=? and months=?  ",dud.getDeptName(),months );
					if(bmzzlh!=null){
						dlpp.setDeptZlhAddScore(bmzzlh.getAddscore());
						dlpp.setDeptZlhReduceScore(bmzzlh.getReducescore());
						dlpp.setDeptZlhScore(bmzzlh.getScore());
					}else{
						errormsg.append("未获取到"+months+"月，"+dud.getDeptName()+"部门，部门长周列会 。");
					}
					//年度改造自选
					YearImprove yearImprove =	(YearImprove) totalDao.getObjectByCondition("from YearImprove where months =? and name =? ", months,dud.getLeader());
					if(yearImprove!=null){
						dlpp.setNdgzScore(yearImprove.getNdxs()*yearImprove.getTzd());
					}else{
						errormsg.append("未获取到"+months+"月，"+dud.getLeader()+"部门，部门长周列会 。");
					}
					try {
						Integer xiShuSum = dlpp.getDeptMubiaoXiShu()
						 		+dlpp.getDeptZlhXiShu()+dlpp.getNdgzXiShu();
						dlpp.setXiShuSum(xiShuSum);
						 totalDao.save(dlpp);
						 dlppList.add(dlpp);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				
			}
		}
		return dlppList;
	}
	@SuppressWarnings("unchecked")
	public	List<DeptLeaderPenPei> updatedlppList(List<DeptLeaderPenPei> dlppList,String months){
		if(dlppList!=null && dlppList.size()>0){
			Users user = Util.getLoginUser();
			List<DeptLeaderPenPei> list = new ArrayList<DeptLeaderPenPei>();
			for (DeptLeaderPenPei dlpp : dlppList) {
				DeptLeaderPenPei olddlpp =	(DeptLeaderPenPei) totalDao.get(dlpp.getClass(), dlpp.getId());
				olddlpp.setDeptMubiaoXiShu(dlpp.getDeptMubiaoXiShu());
				olddlpp.setDeptZlhXiShu(dlpp.getDeptZlhXiShu());
				olddlpp.setNdgzXiShu(dlpp.getNdgzXiShu());
				olddlpp.setXiShuSum(olddlpp.getDeptMubiaoXiShu()+olddlpp.getDeptZlhXiShu()+
						olddlpp.getNdgzXiShu());
				totalDao.update(olddlpp);
			}
			list = totalDao.query("from DeptLeaderPenPei where months =? order by xiShuSum asc ,deptPeopelNum DESC ", months);
			Double sumrage = 0d;
			for (int i = 0; i < list.size(); i++) {
				DeptLeaderPenPei dlpp = list.get(0);
				RankingCounts rankingCounts =(RankingCounts) totalDao.getObjectByCondition("from RankingCounts where ranking =? and groups = ? ", i+1,"部门长分配");
				dlpp.setPaiMing(i+1);
				if(rankingCounts!=null){
					dlpp.setJiXiaoXiShu(rankingCounts.getFenPeiXIshu());
					dlpp.setLeaderjx(dlpp.getJiXiaoXiShu()*dlpp.getDeptAvgjx());
					//添加收入情况信息
					addInCome(dlpp,sumrage,user);
				}
				totalDao.update(dlpp);
			}
			Double avgrage =	sumrage/list.size();
			List<DeptUsersDuty> dudList = totalDao.query("from DeptUsersDuty where  isJiaoXiao =1 and isdeptFenPei = 0");
			for (DeptUsersDuty dud : dudList) {
				addInCome2(dud, avgrage, months, user);
			}
			return list;
		}
		return null;
	}
	
	@Override
	public String addyearimprove(YearImprove yearimprove) {
		Users users =	Util.getLoginUser();
		if(users == null){
			return "请先登录!~";
		}
		if(yearimprove!=null){
			yearimprove.setAddTime(Util.getDateTime());
			yearimprove.setAddUsersName(users.getName());
			return	totalDao.save(yearimprove)+"";
		}
		return null;
	}

	@Override
	public String delyearimprove(YearImprove yearimprove) {
		Users users =	Util.getLoginUser();
		if(users == null){
			return "请先登录!~";
		}
		if(yearimprove!=null){
			return	totalDao.delete(yearimprove)+"";
		}
		return null;
	}

	@Override
	public Object[] findAllyiveList(YearImprove yearimprove, String status,
			Integer pageSize, Integer pageNO) {
		if(yearimprove == null){
			yearimprove = new YearImprove();
		}
		String hql =totalDao.criteriaQueries(yearimprove,null);
		List<YearImprove> yimveList =	totalDao.findAllByPage(hql, pageNO, pageSize);
		int count =	totalDao.getCount(hql);
		return new Object[]{yimveList,count};
	}

	@Override
	public YearImprove findyearimproveById(Integer id) {
		if(id!=null){
			YearImprove yearimprove =	(YearImprove) totalDao.get(YearImprove.class, id);
			return yearimprove;
		}
		return null;
	}

	@Override
	public String updateyearimprove(YearImprove yearimprove) {
		if(yearimprove!=null){
			YearImprove old =	(YearImprove) totalDao.get(YearImprove.class, yearimprove.getId());
			old.setNdxs(yearimprove.getNdxs());
			old.setJyx(yearimprove.getJyx());
			old.setTzd(yearimprove.getTzd());
			return totalDao.update(old)+"";
		}
		return null;
	}

	@Override
	public void addInCome(DeptLeaderPenPei dlpp,Double sumrage,Users user) {
		if(dlpp!=null){
			InCome inCome = new InCome();
			inCome.setDept(dlpp.getDept());
			inCome.setName(dlpp.getName());
			//职务
			DeptUsersDuty  dud = (DeptUsersDuty) totalDao.getObjectByCondition("from DeptUsersDuty" +
					" where  deptName =? and leader =? ", inCome.getDept(),inCome.getName());
			if(dud!=null){
				inCome.setPost(dud.getRank());//职务
				inCome.setPostfactor(dud.getPostCoefficient());//岗位系数
				//结构比 委外比
				if(dud.getIsWeiWaiJieGou()==1){
					WeiWaiJieGou wwjg =	(WeiWaiJieGou) totalDao.getObjectByCondition("from WeiWaiJieGou where  months =? ",dlpp.getMonths());
					inCome.setJieGouBi(wwjg.getJgb().doubleValue());
					inCome.setWeiwaibi(wwjg.getWwb().doubleValue());
				}else{
					inCome.setJieGouBi(1d);
					inCome.setWeiwaibi(1d);
				}
				sumrage += inCome.getDeptaverage();
			}
			inCome.setAddTime(Util.getDateTime());
			inCome.setAddUsersName(user.getName());
			inCome.setMonths(dlpp.getMonths());
			inCome.setDeptaverage(dlpp.getDeptAvgjx());//部门人均
			inCome.setJixiao(dlpp.getJiXiaoXiShu());//绩效系数
			totalDao.save(inCome);
			
		}
	}

	@Override
	public void addInCome2(DeptUsersDuty dud, Double average,String months,Users user) {
		if(dud!=null){
			InCome inCome = new InCome(); 
			inCome.setDept(dud.getDeptName());
			inCome.setName(dud.getLeader());
			inCome.setPost(dud.getRank());//职务
			inCome.setPostfactor(dud.getPostCoefficient());//岗位系数
			//结构比 委外比
			if(dud.getIsWeiWaiJieGou()==1){
				WeiWaiJieGou wwjg =	(WeiWaiJieGou) totalDao.getObjectByCondition("from WeiWaiJieGou where  months =? ",months);
				inCome.setJieGouBi(wwjg.getJgb().doubleValue());
				inCome.setWeiwaibi(wwjg.getWwb().doubleValue());
			}else{
				inCome.setJieGouBi(1d);
				inCome.setWeiwaibi(1d);
			}
			inCome.setAddTime(Util.getDateTime());
			inCome.setAddUsersName(user.getName());
			inCome.setMonths(months);
			inCome.setDeptaverage(average);//部门人均
			totalDao.save(inCome);
		}
	}

	@Override
	public Object[] findAllInComeList(InCome inCome, String months,Integer pageSize, Integer pageNO) {
		if(inCome == null){
			inCome = new InCome();
		}
		if(months == null || "".equals(months)){
			months = Util.getDateTime("yyyy-MM");
		}
		String hql =totalDao.criteriaQueries(inCome,null);
		hql+= " and months = '"+months+"'";
		List<InCome> inComeList =	totalDao.findAllByPage(hql, pageNO, pageSize);
		int count =	totalDao.getCount(hql);
		return new Object[]{inComeList,count};
	}

	@Override
	public String wanShaInCome(InCome inCome) {
		if(inCome!=null){
			InCome old =	(InCome) totalDao.get(InCome.class, inCome.getId());
			old.setNowsalary(inCome.getNowsalary());
			old.setJixiao(inCome.getJixiao());
			old.setPostfactor(inCome.getPostfactor());
			old.setHeji(inCome.getHeji());
			old.setD_value(inCome.getD_value());
			old.setWagesCoefficient(inCome.getWagesCoefficient());
			Float zczzhibiao =	(Float) totalDao.getObjectByCondition("select zczzhibiao from ZbSjZk where months =? and zczdyDept = '全厂' ",old.getMonths());
			if(zczzhibiao!=null && zczzhibiao>0){
				Double zcz =	zczzhibiao*10000d;
				if(zcz>=5000){
					Float coefficient =	(Float) totalDao.getObjectByCondition("select coefficient from SalesAmountCoefficient where  lowAmount<=? and ceilingAmount>?", zcz.floatValue(),zcz.floatValue());
					if(coefficient!=null && coefficient>0){
						Double zzIncome =	old.getD_value()*coefficient*old.getWagesCoefficient()*
						old.getJieGouBi()*old.getWeiwaibi();
						old.setZzIncome(zzIncome);
						Double shouruHeJi = old.getNowsalary()+(old.getDeptaverage()*old.getPostfactor()*old.getJixiao());
						old.setShouruHeJi(shouruHeJi);
							return totalDao.update(old)+"";
					}else{
						return "未获取到"+zcz+"万的总销售产值的绩效提取系数，请添加!~";
					}
				}else{
					return  old.getMonths()+"月份，总销售产值低于5000万，未达到计算绩效标准!~";
				}
			}
		}
		return "error";
	}

	@Override
	public InCome getInComeById(Integer id) {
		if(id!=null){
			return (InCome) totalDao.get(InCome.class, id);
		}
		return null;
	}

	

	

}
