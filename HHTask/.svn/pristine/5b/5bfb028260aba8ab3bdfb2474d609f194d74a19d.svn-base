package com.task.Server.ess;

import java.util.List;

import com.task.entity.PrintedOut;
import com.task.entity.PrintedOutOrder;
import com.task.entity.Sell;

public interface PrintedOutServer {
	/**
	 * 查询出需要打印的集合
	 * @return
	 */
	public Object[] findprintList(Object obj,int pageNo, int pageSize,String status
			,String statrtTime,String endTime,String tag);
	/**
	 * 打印
	 */
	PrintedOutOrder addprint(PrintedOutOrder poor);
	/**
	 * 查询所有打印记录
	 */
	Object[] findAllPrintedOutList(PrintedOut printedOut,int pageNo, int pageSize,String status
			,String statrtTime,String endTime);
	/**
	 * 补打功能
	 */
	PrintedOut findPrintedOutById(Integer id);
	/**
	 * 打印后修改状态
	 */
	boolean printUpdata(PrintedOutOrder poor);
	/**
	 * 根据Id查询出库信息
	 * 
	 */
	Sell findSellById(Integer Id);
	/**
	 * 查询所有打印单
	 */
	Object[] findAllPrintOrder(PrintedOutOrder printeedOuteOrder,int pageNo, int pageSize,String status);
	/**
	 * 根据打印Id查询
	 */
	Object[] chaXunPoorandPo(Integer id);
	
	/**
	 * 删除打印单明细
	 * 
	 */
	
	boolean delPrintedOut(PrintedOut printedOut);
	/**
	 * 根据已打印过的实体类Id查出对应的打印单据查询
	 * @param id
	 * @param entiyName
	 * @return
	 */
	public Object[] findPoorandPoByEntiyId(Integer id, String entiyName);
	public boolean getAuth1(String code);
	
	/**
	 * 更新打印次数
	 */
	String	updatePrintCount(Integer id);
	
	void	qingchu();
}
