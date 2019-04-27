package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.Consuming;

public interface IConsumingService {
	public void add(Consuming con);
	public Consuming getConsumingById(int id);
	public Object[] queryConsuming(Map map, int pageNo, int pageSize);
	public void exportExcel(Map map);
	void delConsumingById(Integer id);
	/**
	 * 根据storId 和卡号查询最后一次领取记录
	 * @param storeId
	 * @param cardNum
	 * @return
	 */
	public Consuming getConsumingByStoreId(Integer storeId,String  cardNum);
	public List printStorage(int[] selected);
}
