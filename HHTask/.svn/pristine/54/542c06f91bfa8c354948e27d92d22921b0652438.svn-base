package com.task.ServerImpl.caiwu;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.caiwu.BankAccountsServer;
import com.task.entity.Users;
import com.task.entity.caiwu.BankAccounts;
import com.task.entity.caiwu.CwPingZheng;
import com.task.entity.caiwu.CwZWAndSbr;
import com.task.entity.caiwu.CwZhangWu;
import com.task.util.Util;

/***
 * 银行账户实现类
 * 
 * @author 刘培
 * 
 */
public class BankAccountsServerImpl implements BankAccountsServer {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/***
	 * 添加银行账户
	 */
	@Override
	public boolean addBankAccounts(BankAccounts bankAccounts) {
		if (bankAccounts != null) {
			return totalDao.save(bankAccounts);
		}
		return false;
	}

	/***
	 * 查询银行账户
	 */
	@Override
	public Object[] findWorkLogByCondition(BankAccounts bankAccounts,
			int pageNo, int pageSize) {
		if (bankAccounts == null) {
			bankAccounts = new BankAccounts();
		}
		String hql = totalDao.criteriaQueries(bankAccounts, null, null);
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/***
	 * 凭证查询
	 */
	@Override
	public Object[] findPZByCondition(CwPingZheng cwPingZheng, int pageNo,
			int pageSize) {
		if (cwPingZheng == null) {
			cwPingZheng = new CwPingZheng();
		}
		String hql = totalDao.criteriaQueries(cwPingZheng, null);
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/***
	 * 单个凭证查询
	 */
	@Override
	public CwPingZheng findPZ(Integer id) {
		return (CwPingZheng) totalDao.getObjectById(CwPingZheng.class, id);
	}

	/***
	 * 财务报表查询
	 * 
	 * @return
	 */
	@Override
	public List findCwZw() {
		String hql = "from CwZhangWu order by addDateTime";
		List<CwZhangWu> list = totalDao.query(hql);
		for (CwZhangWu cwZhangWu : list) {
			String hql_cwsbr = "from CwZWAndSbr where fk_zwId=?";
			List<CwZWAndSbr> list_cwsbr = totalDao.query(hql_cwsbr, cwZhangWu
					.getId());
			String hql_all = "select 0 ";
			for (CwZWAndSbr cwZWAndSbr : list_cwsbr) {
				hql_all += cwZWAndSbr.getJiaOrJian() + "(select "
						+ cwZWAndSbr.getDateType()
						+ " from SubBudgetRate where id ="
						+ cwZWAndSbr.getFk_sbrId() + ")";
			}
			hql_all += " from CwZWAndSbr  where fk_zwId=?";
			Object allmoney = (Object) totalDao.getObjectByCondition(hql_all,
					cwZhangWu.getId());
			if (allmoney == null) {
				allmoney = 0;
			}
			cwZhangWu.setMoney(Double.parseDouble(allmoney.toString()));
		}
		return list;
	}

	/***
	 * 添加财务报表
	 * 
	 * @param cwZhangWu
	 *            报表
	 * @param cwZWAndSbrList
	 *            关系表集合
	 * @return
	 */
	@Override
	public void addCwbb(CwZhangWu cwZhangWu, List<CwZWAndSbr> cwZWAndSbrList) {
		if (cwZhangWu != null && cwZWAndSbrList != null
				&& cwZWAndSbrList.size() > 0) {
			Users loginUser = Util.getLoginUser();
			cwZhangWu.setAddUsers(loginUser.getName());
			cwZhangWu.setAddDateTime(Util.getDateTime());
			boolean bool = totalDao.save(cwZhangWu);
			if (bool) {
				for (CwZWAndSbr cwZWAndSbr : cwZWAndSbrList) {
					cwZWAndSbr.setFk_zwId(cwZhangWu.getId());
					bool = totalDao.save(cwZWAndSbr);
				}
			}
		}
	}
}
