package com.task.Server;

import java.util.List;

import com.task.entity.Ttooling;

/**
 * 工装(项目管理)
 * @author 马凯
 *
 */
public interface TtoolingService {
	
	/**
	 * 批量添加
	 * @param toolings 
	 */
	public void addAll(List<Ttooling> toolings);

	/**
	 * 工装Selector
	 * @param tooling
	 */
	public List selector(Ttooling tooling);
}
