package com.task.Server.shizhi;

import java.util.List;
import java.util.Map;

import com.task.entity.shizhi.BonusShiZhi;

public interface BonusShiZhiServer {
	/**
	 *按条件分页查询月奖金额
	 * @param skiilScore
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findBonusShiZhisByCondition(BonusShiZhi bonusShiZhi, int pageNo, int pageSize);	
	/**
	 * 通过id获取月奖金额对象
	 * @param id
	 * @return
	 */
	public BonusShiZhi getById(Integer id);
	/**
	 * 添加月奖金额对象
	 * @param BonusShiZhi
	 * @return
	 */
	public boolean add(BonusShiZhi bonusShiZhi);
	/**
	 * 修改月奖金额
	 * @param BonusShiZhi
	 * @return
	 */
	public boolean update(BonusShiZhi bonusShiZhi);
    /**
     *通过id 删除月奖金额对象
     * @param id
     * @return
     */
	public boolean deleteById(Integer id);
	/**
	 * 查询所有月奖金额
	 * @return
	 */
	public List<BonusShiZhi> findAll();
	/**
	 * 通过月份计算该月的所有的产生项目订单的项目试制评审零件的月奖金额
	 * @return
	 */
	public boolean getTryMakeRateByMonth(String month,Float money);
	
}
