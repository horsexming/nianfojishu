package com.task.ServerImpl.fin.budget;

import com.task.Dao.TotalDao;
import com.task.Server.fin.budget.DeptBudgetServer;
/**
 * 部门科目预算金额serverImpl
 * @author jhh
 *
 */
public class DeptBudgetServerImpl implements DeptBudgetServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}
	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	
}
