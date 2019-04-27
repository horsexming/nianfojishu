package com.task.ServerImpl.kq;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.kq.ICardService;
import com.task.Server.kq.IConsumeService;
import com.task.entity.Consume;

/**
 * @author 曾建森
 * @FileNam ConsumeServiceImpl.java
 * @Date 2012-10-9
 */
public class ConsumeServiceImpl implements IConsumeService {

	private TotalDao totalDao;

	private ICardService ics;

	public void add(Consume cs) {
		totalDao.save(cs);
		ics.updateBalance(cs.getPersonId(), cs.getCardBalance());
	}

	public void delById(int id) {
		Consume co = (Consume) totalDao.getObjectById(Consume.class, id);
		totalDao.delete(co);
	}

	public void del(Consume cs) {
		totalDao.delete(cs);
	}

	public void update(Consume cs) {
		totalDao.update(cs);
	}

	@SuppressWarnings("unchecked")
	public List<Consume> queryAllConsume() {
		return totalDao.query("from Consume", null);
	}

	public Consume getConsumeById(int id) {
		return (Consume) totalDao.query("from Consume c where c.id = ?", id);
	}

	public Consume getConsumeByPersonId(int personId) {
		return (Consume) totalDao.query("from Consume c where c.personId = ?",
				personId).get(0);
	}

	public int getConsumebeByDateAndId(int id, Date startTime, Date endTime) {
		int sum = 0;
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(endTime);
		rightNow.set(Calendar.HOUR_OF_DAY, 23);
		rightNow.set(Calendar.MINUTE, 59);
		rightNow.set(Calendar.SECOND, 59);
		endTime = rightNow.getTime();
		List list = totalDao
				.query(
						"select sum(c.consumeFund) from Consume c where c.personId = ? and (c.consumeTime between ? and ?)",
						id, startTime, endTime);
		if (list != null && list.size() > 0 && list.get(0) != null) {
			float num = (Float) list.get(0);
			sum = (int) num;
		}
		return sum;
	}

	public int getConsumebeById(int id) {
		int sum = 0;
		List list = totalDao
				.query(
						"select sum(c.consumeFund) from Consume c where c.personId = ?",
						id);
		if (list != null && list.size() > 0) {
			float num = (Float) list.get(0);
			sum = (int) num;
		}
		return sum;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public void setIcs(ICardService ics) {
		this.ics = ics;
	}

}
