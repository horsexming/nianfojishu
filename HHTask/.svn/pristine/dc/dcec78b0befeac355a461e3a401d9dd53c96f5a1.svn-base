package com.task.Server.yw;

import java.util.Map;

import com.task.entity.Business;

/**
 * @Title: IBusinessServer.java
 * @Package com.task.Server.yw
 * @Description: TODO 业务表接口
 * @author 曾建森
 * @date 2012-10-29 上午09:50:22
 * @version V1.0
 */
public interface IBusinessService {
	/**
	 * @Title: add
	 * @Description: 添加业务记录
	 * @param @param bu 
	 * @return void
	 * @throws
	 */
	public void add(Business bu);
	/**
	 * @Title: delById
	 * @Description: 根据ID删除指定记录
	 * @param id 
	 * @return void
	 * @throws
	 */
	public void delById(int id);
	/**
	 * @Title: update
	 * @Description: 修改记录
	 * @param bu 
	 * @return void
	 * @throws
	 */
	public boolean update(Business bu);
	/**
	 * @Title: getBusinessById
	 * @Description: 根据ID查询业务
	 * @param  id
	 * @return Business
	 * @throws
	 */
	public Business getBusinessById(int id);
	/**
	 * @Title: queryAllBusiness
	 * @Description: 查询所有业务
	 * @param  
	 * @return void
	 * @throws
	 */
	public Object[] queryAllBusiness(int pageNo,int pageSize,String flow);
	/**
	 * @Title: queryByCondition
	 * @Description: 根据条件查询业务
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return Object[]
	 * @throws
	 */
	public Object[] queryByCondition(Map map,int pageNo,int pageSize,String flow);
	/**
	 * @Title: auditBusiness
	 * @Description: 更改业务审核完的结果
	 * @param id
	 * @param flow
	 * @return boolean
	 * @throws
	 */
	public boolean auditBusiness(int id,String flow,int ifAgree,String dept);
	/**
	 * @Title: queryByPass
	 * @Description: 查询所有通过的业务
	 * @param @param pageNo
	 * @param @param pageSize
	 * @return Object[]
	 * @throws
	 */
	public Object[] queryByPass(int pageNo,int pageSize);
}
