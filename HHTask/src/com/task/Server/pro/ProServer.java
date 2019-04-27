package com.task.Server.pro;

import java.util.List;
import java.util.Map;

import com.task.entity.pro.Pro;

public interface ProServer {
	public String addPro(Pro pro);// 添加项目记录

	public String deletePro(Pro pro);// 删除项目记录

	public String updatePro(Pro pro);// 更新项目记录

	public Pro getProById(Integer id);// 获得项目记录

	public Object[] findAllPro(Map map, int pageNo, int pageSize);// 获得项目记录集合

	public Object[] listpro(Pro pro, Integer parseInt, Integer pageSize);

	public List listPro();

	public List listKVP(String name);

	/***
	 * 查询启动中的所有项目
	 * 
	 * @param pro
	 * @return
	 */
	List findCgPRo(Pro pro);
}
