package com.task.Server.caiwu;

import java.util.List;

import com.task.entity.caiwu.BankAccounts;
import com.task.entity.caiwu.CwPingZheng;
import com.task.entity.caiwu.CwZWAndSbr;
import com.task.entity.caiwu.CwZhangWu;

/****
 * 银行账户信息
 * 
 * @author 刘培
 * 
 */
public interface BankAccountsServer {

	boolean addBankAccounts(BankAccounts bankAccounts);

	Object[] findWorkLogByCondition(BankAccounts bankAccounts, int pageNo,
			int pageSize);

	/***
	 * 凭证查询
	 */
	Object[] findPZByCondition(CwPingZheng cwPingZheng, int pageNo, int pageSize);

	/***
	 * 单个凭证查询
	 */
	CwPingZheng findPZ(Integer id);

	/***
	 * 财务报表查询
	 * 
	 * @return
	 */
	List findCwZw();

	/***
	 * 添加财务报表
	 * 
	 * @param cwZhangWu
	 *            报表
	 * @param cwZWAndSbrList
	 *            关系表集合
	 * @return
	 */
	void addCwbb(CwZhangWu cwZhangWu, List<CwZWAndSbr> cwZWAndSbrList);
}
