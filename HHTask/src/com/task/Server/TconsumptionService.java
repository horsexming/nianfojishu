package com.task.Server;

import java.util.List;

import com.task.entity.Tconsumption;


/**
 * 项目管理_消耗
 * @author 马凯
 *
 */
public interface TconsumptionService {
	
	/**
	 * 批量添加消耗
	 * @param consumptions
	 */
	public void addAll(List<Tconsumption> consumptions);

}
