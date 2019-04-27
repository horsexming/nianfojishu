package com.task.Server.kq;

import java.util.List;
import java.util.Map;

import com.task.entity.TakeFund;


/** 
 * @author 曾建森
 * @FileNam ITakFundService.java
 * @Date 2012-10-15
 */
public interface ITakeFundService {
	public void add(TakeFund take);
	public boolean addTakeFund(int id);
	public TakeFund getTakeFundById(int id);
	public List<TakeFund> queryAllTakeFund();
	public Object[] queryTakeFundByCondition(Map map,int pageNo,int pageSiz);
	/**
	 * 查看当天充值记录
	 * @return
	 */
	List findDailySupply();
}
