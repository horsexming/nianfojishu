package com.task.Server;

import java.util.Map;

import com.task.entity.Also;


public interface IAlsoService {
	void add(Also al);
	void del(Also al);
	boolean update(Also al);
	Also getAlsoById(int id);
	Object[] queryAlso(Map map, int pageNo, int pageSize);
	void exportExcel(Map map);
	String alsoGoods(Also al);
	/**
	 * 查询所有待入库的还工装数据
	 * @param al
	 * @param parseInt
	 * @param pageSize
	 * @param tag
	 * @return
	 */
	Object[] showCodeAlso(Also al, int parseInt, int pageSize, String tag);
}
