package com.task.ServerImpl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.TclaimformService;
import com.task.entity.Tclaimform;

public class TclaimformServiceImpl implements TclaimformService {

	private TotalDao totalDao;

	@Override
	public Serializable add(Tclaimform claimform) {
		claimform.setStatus("未分析");
		totalDao.save(claimform);

		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String dateStr = sdf.format(d);
		
		String hql = "select count(*) from Tclaimform where tclaimNumber like 'sp" + dateStr + "%'";
		long k = totalDao.count(hql);
		String spNumber = "sp" + dateStr + String.format("%03d" ,k+1);
		claimform.setTclaimNumber(spNumber);
		return claimform.getId();
	}
	
	@Override
	public Object[] list(int parseInt, int pageSize) {
		String hql = "from Tclaimform order by regDate desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	
	@Override
	public void update(Tclaimform claimform) {
		Tclaimform t = (Tclaimform) totalDao.get(Tclaimform.class, claimform.getId());
		BeanUtils.copyProperties(claimform, t, new String[]{"id","status","records", "username", "notification", "notificationDate","debit", "debitDate", "debitAmount"});
	}

	@Override
	public Tclaimform get(Tclaimform claimform) {
		return (Tclaimform) totalDao.get(Tclaimform.class, claimform.getId());
	}
	
	
	@Override
	public void delete(Tclaimform claimform) {
		totalDao.delete(claimform);
	}
	
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public void updateNotification(Tclaimform claimform) {
		Tclaimform t = (Tclaimform) totalDao.get(Tclaimform.class, claimform.getId());
		t.setNotification(claimform.getNotification());
		t.setNotificationDate(claimform.getNotificationDate());
		totalDao.update(t);
		
		String sql = "UPDATE ta_TclaimsRecord SET status = ? WHERE (status = ?) and f_TclaimsRecord_id = ?";
		totalDao.createQueryUpdate(null, sql, new Object[]{"未扣款", "未通知", claimform.getId()});
		
	}

	@Override
	public void updateDebit(Tclaimform claimform) {
		Tclaimform t = (Tclaimform) totalDao.get(Tclaimform.class, claimform.getId());
		t.setDebitAmount(claimform.getDebitAmount());
		t.setDebitDate(claimform.getDebitDate());
		t.setStatus("未整改");
		totalDao.update(t);
		
		String sql = "UPDATE ta_TclaimsRecord SET status = ? WHERE (status = ?) and f_TclaimsRecord_id = ?";
		totalDao.createQueryUpdate(null, sql, new Object[]{"未整改", "未扣款", claimform.getId()});
		
	}


}
