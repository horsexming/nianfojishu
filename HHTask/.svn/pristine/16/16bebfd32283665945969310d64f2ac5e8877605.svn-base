package com.task.Server.fin;

import java.util.List;

import com.task.entity.fin.Escrow;
import com.task.entity.fin.EscrowMonth;

/**
 * 委托付款接口
 * @author jhh
 *
 */
public interface EscrowServer {

	Object[] findFundApplyList(Escrow escrow, int parseInt, int pageSize,
			String pagestatus);

	List getPrintList(int[] ids);

	void print(int[] ids);

	List<String> getqmList(Integer epId);
	/**
	 * 获得登录人的电子签名
	 * @return
	 */
	List<String> getzbList();
	/**
	 * 通过月份来获取所有付款方
	 * @param month
	 * @return
	 */
	List<String> getPayComByMonth(String month);
	/**
	 * 添加月度委托汇总
	 * @param escrowMonth
	 * @return
	 */
	String applyEscrowMonth(EscrowMonth escrowMonth);
	/**
	 * 获取某月的被委托方
	 * @param id
	 * @return
	 */
	EscrowMonth getescrowMonthForPrint(Integer id);
	/**
	 * 查询月度委托汇总
	 * @param escrowMonth
	 * @param parseInt
	 * @param pageSize
	 * @param pagestatus
	 * @return
	 */
	Object[] findEscrowMonthList(EscrowMonth escrowMonth, int parseInt,
			int pageSize, String pagestatus);

	String findEscrowType();

}
