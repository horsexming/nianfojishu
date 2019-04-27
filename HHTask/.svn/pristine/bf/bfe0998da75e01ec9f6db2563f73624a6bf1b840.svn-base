package com.task.Server.shizhi;

import java.util.List;
import java.util.Map;

import com.task.entity.shizhi.BonusLoad;


public interface BonusLoadServer {
	/**
	 *按条件分页查询奖金负荷系数 
	 * @param skiilScore
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findBonusLoadsByCondition(BonusLoad bonusLoad, int pageNo, int pageSize);	
	/**
	 * 通过id获取奖金负荷系数对象
	 * @param id
	 * @return
	 */
	public BonusLoad getById(Integer id);
    /**
     *通过id 删除奖金负荷系数对象
     * @param id
     * @return
     */
	public boolean deleteById(Integer id);
	/**
	 * 查询所有奖金负荷系数
	 * @return
	 */
	public List<BonusLoad> findAll();
    /**
	 * 插入QuotedPrice表中比BonusLoad表中多出的数据
	 * @return
//	 */
//	public boolean addUnintoData();
//	 /**
//	 * 删除BonusLoad表中比QuotedPrice表中多出的数据
//	 * @return
//	 */
//	public boolean deleteMoreData();
//	/**
//	 * 更新QuotedPrice表中和BonusLoad表中都有的数据
//	 * @return
//	 */
//	public boolean updateHadData();
	/**
	 * 重新校验单价和含税价
	 * @return
	 */
	public boolean updateAll();
}
