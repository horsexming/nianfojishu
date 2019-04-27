package com.task.Server;

import java.util.List;

import com.task.entity.WareHouse;

public interface WareHouseService {
	
	/**
	 * 添加
	 * @param house
	 */
	public void add(WareHouse house);
	
	/**
	 * 获取所有
	 * @return
	 */
	public List<WareHouse> getAll();

	/**
	 * 删除
	 * @param house
	 */
	public void delete(WareHouse house);

	
}
