package com.task.Server;

import com.task.entity.TaHkSellSta;

public interface HKSellStaServer {
	/**
	 * 管理送货申请单信息
	 * @param sta
	 * @param startDate
	 * @param endDate
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Object[] findHKSellSta(TaHkSellSta sta, String startDate, String endDate,
			int pageNo, int pageSize);
	/**
	 * 获取送货申请单信息
	 * @param id
	 * @return
	 */
	public TaHkSellSta getHKSellSta(Integer id);
	/**
	 * 更新送货申请单信息
	 * @param sta
	 * @return
	 */
	public boolean updateHKSellSta(TaHkSellSta sta);
	/**
	 * 删除送货申请单信息
	 * @param id
	 * @return
	 */
	public boolean deleteHKSellSta(Integer id);
	/**
	 * 返回下拉菜单
	 * @param tag
	 * @return
	 */
	public String selectItem(String tag);
}
