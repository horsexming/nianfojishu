package com.task.Server;

import java.util.List;

import com.task.entity.JiMiLeiXing;
import com.task.entity.Users;

public interface JiMiLeiXingServerDao {
	
	/**
	 * 显示所有机密等级；
	 */
	public List<JiMiLeiXing> showJiMiList();
	/**
	 * 添加机密等级;
	 */
	public boolean addJiMi(JiMiLeiXing jimi);
	/**
	 * 删除机密等级；
	 * 
	 */
	public boolean delJiMi(JiMiLeiXing jimi);
	/**
	 * 修改机密等级
	 */
	public boolean updateJiMi(JiMiLeiXing jimi);
	/**
	 * 根据id查询机密等级;
	 */
	public JiMiLeiXing  showJiMiListByid(Integer id);
	/**
	 * 绑定人员机密等级;
	 */
	public boolean BDJiMi(JiMiLeiXing jimi,Integer[] uid);
	/**
	 * 查询所有人员信息
	 */
	public String finAllJiMiForSetlect();
	/**
	 * 显示所有机密类型（分页）
	 */
	public List<JiMiLeiXing> findAllByPage(int pageNo, int pageSize);
	/**
	 * 获得总数量
	 */
	public int getcont();
	
	/**
	 * 查询所有部门为页面Select使用
	 */
	@SuppressWarnings("unchecked")
	public String finAllDeptNumberForSetlect();
}
