package com.task.Server.opinion;

import java.util.List;
import java.util.Map;

import com.task.entity.opinion.CustomerOpinion;

public interface CustomerOpinionServer {
	/**
	 * 分页查询客户投诉信息
	 * 
	 * @param customerOpinion
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	Map<Integer, Object> findCustomerOpinionsByCondition(
			CustomerOpinion customerOpinion, int parseInt, int pageSize,
			String tag);

	/**
	 * 添加客户投诉
	 * 
	 * @param customerOpinion
	 * @return
	 */
	boolean add(CustomerOpinion customerOpinion);

	/**
	 * 通过id获取客户投诉对象
	 * 
	 * @param id
	 * @return
	 */
	CustomerOpinion getById(Integer id);

	/**
	 * 修改，分析，改进，审批
	 * 
	 * @param customerOpinion
	 * @param tag
	 * @return
	 */
	boolean update(CustomerOpinion customerOpinion, String tag);

	/**
	 * 通过id删除对象
	 * 
	 * @param id
	 * @return
	 */
	boolean deleteById(Integer id);

	/****
	 * 根据登录用户查询对应客户，并查询其对应订单、产品等信息
	 * 
	 * @return
	 */
	List findOrderByClient();

}
