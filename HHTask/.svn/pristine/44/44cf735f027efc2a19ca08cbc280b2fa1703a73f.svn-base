package com.task.Server.sys;

import java.io.File;
import java.util.List;

import com.task.entity.system.SystemDemand;

/**
 * 系统需求server
 * @author 王传运
 *
 */
public interface SystemDemandServer {

	/**
	 * 添加需求
	 * @param systemDemand
	 * @param demandFileFileName 
	 * @return
	 */
	String addSystemDemand(SystemDemand systemDemand,File demandFile, String demandFileFileName);
	
	/**
	 * 显示系统需求
	 * @param systemDemand
	 * @param pageStatus
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	Object[] showSystemDemands(SystemDemand systemDemand,String pageStatus,Integer pageSize,Integer pageIndex);
	
	/**
	 * 更新系统需求
	 * @param systemDemand
	 * @return
	 */
	String updateSystemDemand(SystemDemand systemDemand);
	
	/**
	 * 删除系统需求
	 * @param id
	 * @return
	 */
	String delSystemDemand(Integer id);
	
	/**
	 * 根据id 查找系统需求
	 * @param id
	 * @return
	 */
	SystemDemand getSystemDemandById(Integer id);

	/**
	 * 根据需求简称获得需求信息
	 * @param functionName
	 * @return
	 */
	SystemDemand getsdBysdShortName(String sdShortName);
	
	
	
}
