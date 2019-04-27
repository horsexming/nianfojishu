package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.Integral;
import com.task.entity.Integralsource;

public interface IntegralsourceServerDao {

	/**
	 * 删除积分来源项；
	 */
	public boolean delIntegralsource(Integralsource is);
	/**
	 * 查看积分来源项(个人);
	 * 
	 */
	public Map<Integer, Object> findIntegralByCondition(Integralsource is,int pageNo, int pageSize);
			
	/**
	 * 查看积分来源项(所有);
	 */
	public List<Integralsource> findAllByPage(int pageNo, int pageSize);
	/**
	 * 修改积分来源项;
	 */
	public boolean updataIs(Integralsource is);
	/**
	 * 获得总数量
	 */
	public int getcont();
	/**
	 * 添加积分项，
	 */
	public boolean addIntegral(Integer integral);
	
}
