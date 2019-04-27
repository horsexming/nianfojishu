package com.task.Server.caiwu;

import java.util.List;
import java.util.Map;

import com.task.entity.caiwu.MoneyType;

public interface MoneyTypeServer {
	/**
	 * 添加货币
	 */
	String addMoneyType(MoneyType moneyType);
	/**
	 * 禁用货币
	 * @param id
	 * @return
	 */
	boolean jinyong(Integer id);
	/**
	 * 反禁用货币(恢复使用)
	 * @param id
	 * @return
	 */
	boolean fanjinyong(Integer id);
	/**
	 * 更新货币
	 * @param moneyType
	 * @return
	 */
	boolean updateMoneyType(MoneyType moneyType);
	/**
	 * 删除货币
	 * @param moneyType
	 * @return
	 */
	boolean delMoneyType(MoneyType moneyType);
	/**
	 * 查询所有货币
	 */
	List<MoneyType> findAllMoneyType();
	/**
	 * 查询所有分页
	 */
	List<MoneyType>  findAllMoneyTypeBypage(int pageNo,
			int pageSize);
	/**
	 * 条件查询
	 */
	Map<Integer, Object> findMoneyTypeCondition(MoneyType moneyType,
			int pageNo, int pageSize);
	public int getcont();
	/**
	 * 根据Id获得货币信息
	 */
	MoneyType findMoneyTypeById(Integer id);
}
