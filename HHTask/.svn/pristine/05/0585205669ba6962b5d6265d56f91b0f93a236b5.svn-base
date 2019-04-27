package com.task.ServerImpl.kq;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.kq.ICardService;
import com.task.Server.kq.ISupplyFundService;
import com.task.entity.Card;
import com.task.entity.SupplyFund;

/**
 * @author Administrator
 * @FileNam SupplyFundServiceImpl.java
 * @Date 2012-10-9
 */
public class SupplyFundServiceImpl implements ISupplyFundService {

	private TotalDao totalDao;
	private float copies = 1;

	private ICardService ics;

	public void add(SupplyFund sf) {
		totalDao.save(sf);
		ics.updateBalance(sf.getPersonId(), sf.getCardBalance());
	}

	public void delById(int id) {
		SupplyFund sf = (SupplyFund) totalDao.getObjectById(SupplyFund.class, id);
		totalDao.delete(sf);
	}

	public void del(SupplyFund sf) {
		totalDao.delete(sf);
	}

	public void update(SupplyFund sf) {
		totalDao.update(sf);
	}

	@SuppressWarnings("unchecked")
	public List<SupplyFund> queryAllSupplyFund() {
		return totalDao.query("from SupplyFund", null);
	}

	public boolean getCardIfSupplyFundByPersonId(int id) {
		Date begin = new Date();
		Calendar rightNow1 = Calendar.getInstance();
		rightNow1.setTime(begin);
		rightNow1.set(Calendar.HOUR_OF_DAY, 0);
		rightNow1.set(Calendar.MINUTE, 0);
		rightNow1.set(Calendar.SECOND, 0);
		begin = rightNow1.getTime();
		Date end = new Date();
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(end);
		rightNow.set(Calendar.HOUR_OF_DAY, 23);
		rightNow.set(Calendar.MINUTE, 59);
		rightNow.set(Calendar.SECOND, 59);
		end = rightNow.getTime();
		List list = totalDao
				.query(
						"from SupplyFund s where s.personId = ? and (s.supplyDate between ? and ?)",
						id, begin, end);
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	public SupplyFund getSupplyFundById(int id) {
		return (SupplyFund) totalDao.query(
				"from SupplyFund sf where sf.id = ?", id).get(0);
	}

	public SupplyFund getSupplyFundByPersonId(int personId) {
		return (SupplyFund) totalDao.query(
				"from SupplyFund sf where sf.personId = ?", personId).get(0);
	}

	public int getSupplyByDateAndId(int id, Date startTime, Date endTime) {
		int sum = 0;
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(endTime);
		rightNow.set(Calendar.HOUR_OF_DAY, 23);
		rightNow.set(Calendar.MINUTE, 59);
		rightNow.set(Calendar.SECOND, 59);
		endTime = rightNow.getTime();
		List list = totalDao
				.query(
						"select sum(s.supplyFund) from SupplyFund s where s.personId = ? and (s.supplyDate between ? and ?)",
						id, startTime, endTime);
		if (list != null && list.size() > 0 && list.get(0) != null) {
			float num = (Float) list.get(0);
			sum = (int) num;
		}
		return sum;
	}

	public int getSupplyById(int id) {
		int sum = 0;
		List list = totalDao
				.query(
						"select sum(s.supplyFund) from SupplyFund s where s.personId = ?",
						id);
		if (list != null && list.size() > 0 && list.get(0) != null) {
			float num = (Float) list.get(0);
			sum = (int) num;
		}
		return sum;
	}
	public void privateSupplyFund(int id) {
		Card card = ics.getCardById(id);
		if (card != null) {
			float cardBlance = card.getBalance()
					+ copies;
			Date date = new Date();
			SupplyFund sf = new SupplyFund(id,
					date, copies, cardBlance, 0,
					"Admin");
			add(sf);
		}
	}
	
	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public void setIcs(ICardService ics) {
		this.ics = ics;
	}

	

}
