package com.task.Server;

import java.util.List;

import com.task.entity.TaSopGongwei;

public interface SopGongwei {
	//添加工位信息
	public boolean save(TaSopGongwei gongwei);
	//条件查询工序信息表
	public Object[] selectGongxu(TaSopGongwei gongwei,Integer cpage,Integer pageSize);
	//获取单个工位信息
	public TaSopGongwei getGongwei(Integer id);
	//修改工位信息
	public boolean updateGongwei(TaSopGongwei gongwei);
	//删除工位信息
	public boolean deleteGongwei(TaSopGongwei gongwei);
	
	/**
	 * 获取工位和ID
	 * @param id 
	 * @return
	 */
	public List getWorkStations(Integer id);
}
