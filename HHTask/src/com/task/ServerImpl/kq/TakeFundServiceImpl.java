package com.task.ServerImpl.kq;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.kq.ICardService;
import com.task.Server.kq.IConsumeService;
import com.task.Server.kq.ISupplyFundService;
import com.task.Server.kq.ITakeFundService;
import com.task.entity.Card;
import com.task.entity.Collect;
import com.task.entity.TakeFund;
import com.task.util.Util;

/**
 * @author Administrator
 * @FileNam TakeFund.java
 * @Date 2012-10-15
 */
public class TakeFundServiceImpl implements ITakeFundService {

	private TotalDao totalDao;

	private ICardService cs;

	private IConsumeService ics;

	private ISupplyFundService sfs;

	public void add(TakeFund take) {
		totalDao.save(take);
	}

	public boolean addTakeFund(int id) {
		Card card = cs.getCardById(id);
		float balance = card.getBalance() - card.getBalance();
		TakeFund take = new TakeFund(id, new Date(), card.getBalance(),
				balance, "Admin");
		boolean bool=totalDao.save(take);
		if(bool){
			bool = cs.updateBalance(id, balance);
		}
		return bool;
	}

	public TakeFund getTakeFundById(int id) {
		return (TakeFund) totalDao.getObjectById(TakeFund.class, id);
	}

	@SuppressWarnings( { "rawtypes", "unchecked" })
	public List queryAllTakeFund() {
		List<TakeFund> list = totalDao.query("from TakeFund", null);
		return list;
	}

	@SuppressWarnings( { "rawtypes", "unchecked" })
	public Object[] queryTakeFundByCondition(Map map, int pageNo, int pageSize) {
		if(map == null){
			map = new HashMap();
		}
		List<Collect> collectList = new ArrayList();
		String hql = "select p.id,p.name,d.name,p.cardNo from Person p,Department d where p.deptId = d.id and p.id in (select id from Card c where c.balance <> 0) ";
		if (map.get("deptID") != null) {
			hql += " and d.id = '" + map.get("deptID") + "'";
		}
		if (map.get("cardNo") != null) {
			hql += " and p.cardNo = '" + map.get("cardNo") + "'";
		}
		if (map.get("personName") != null) {
			hql += " and p.name like '%" + map.get("personName") + "%'";
		}
		if (map.get("personName") == null && map.get("deptID") == null) {
			hql += " order by d.name";
		}
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			int id = (Integer) obj[0];
			String name = (String) obj[1];
			String deptName = (String) obj[2];
			String cardNo = (String) obj[3];
			int balance = cs.getCardBalanceById(id);
			int refund = balance * 5;
			Collect cl = new Collect(id, name, deptName, cardNo, balance, refund);
			collectList.add(cl);
		}
		Object[] o = { collectList, count };
		return o;
	}
	
	public List findDailySupply(){
		List list=new ArrayList();
		String time=Util.getDateTime("yyyy-MM-dd 00:00:00");
		String time2=Util.getDateTime("yyyy-MM-dd 23:59:59");
		Date startDate=null;
		Date endDate=null;
		try {
			startDate = Util.StringToDate(time, "yyyy-MM-dd HH:mm:ss");
			endDate = Util.StringToDate(time2, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String hql="select p.name,d.name,p.cardNo,s.supplyDate,s.supplyFund,s.cardBalance from Person p,Department d,SupplyFund s where p.deptId = d.id and p.id=s.personId and s.supplyDate > ? and s.supplyDate < ? order by p.deptId";
		list=totalDao.query(hql, startDate,endDate );
		return list;
	}
	public void setCs(ICardService cs) {
		this.cs = cs;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public void setIcs(IConsumeService ics) {
		this.ics = ics;
	}

	public void setSfs(ISupplyFundService sfs) {
		this.sfs = sfs;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

}
