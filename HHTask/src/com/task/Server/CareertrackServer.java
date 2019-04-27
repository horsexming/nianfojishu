package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.Careertrack;
import com.task.entity.Users;
import com.task.entity.sop.BuHeGePin;

public interface CareertrackServer {

	/**
	 * 添加
	 */
	public boolean add(Careertrack ck);
	/**
	 * 把之前users表的内部人员数据全部更新过来
	 */
	public boolean addMore();
	
	/**
	 * 删除
	 */
	public boolean del(Careertrack ck);
	/**
	 * 修改
	 */
	public boolean  update(Careertrack ck);
	/**
	 * 查询 按userid查
	 */
	public Careertrack findCareertrackByuId(Integer id);
	/**
	 * 根据 职业轨迹 Id 查询Users
	 */
	public Users findUsersByckId(Integer id);
		
		
	
	
	/**
	 * 根据Id 查询 Users 获得相关的一系列Id
	 */
	public Map<String, Object> findUsersById(Integer id);
	
	/**
	 * 根据Id 查询 Careertrack
	 * 
	 */
	public Careertrack showCareertrackByid(Integer id);
	/**
	 * 查询所有员工的职业轨迹（分页）
	 * 
	 */
	List<Careertrack> FindAllCareertrack( int pageNo, int pageSize);
	
	/**
	 * 条件查询；
	 * 
	 */
	public Map<Integer, Object> findCareertrackByCondition(Careertrack ck,
			int pageNo, int pageSize,String statue);
		
	/**
	 * 获得总条数
	 */
	public int getcont();
	
	public void xiufuUserId();
	/**
	 * 刷新
	 */
	public void shuaixin();
	
	
	
}
