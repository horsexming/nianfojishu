package com.task.Server;

import java.io.Serializable;
import java.util.List;

import com.task.entity.Manufacturing;

public interface ManufacturingService {
	
	/**
	 * 添加
	 * @param m
	 * @return
	 */
	public Serializable add(Manufacturing m);
	
	public void update(Manufacturing m);
	
	/**
	 * 列表
	 * @param rows
	 * @param size
	 * @return
	 */
	public Object[] find(int rows, int size);

	/**
	 * 从服务器端下载数据到客户端
	 * @param id
	 * @return
	 */
	public List<Manufacturing> getData(Integer id);

}
