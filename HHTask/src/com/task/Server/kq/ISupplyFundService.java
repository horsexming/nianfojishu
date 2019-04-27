package com.task.Server.kq;

import java.util.Date;
import java.util.List;

import com.task.entity.SupplyFund;


/**
 * @author 曾建森
 * @FileNam ISupplyFund.java
 * @Date 2012-10-9
 */
public interface ISupplyFundService {
	public void add(SupplyFund sf);
	public void delById(int id);
	public void del(SupplyFund sf);
	public void update(SupplyFund sf);
	public List<SupplyFund> queryAllSupplyFund();
	public boolean getCardIfSupplyFundByPersonId(int id);
	public SupplyFund getSupplyFundById(int id);
	public SupplyFund getSupplyFundByPersonId(int personId);
	public int getSupplyByDateAndId(int id,Date startTime,Date endTime);
	public int getSupplyById(int id);
	public void privateSupplyFund(int id);
}
