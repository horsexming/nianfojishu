package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.ClientManagement;
import com.task.entity.Price;

public interface ClientManagementServer {

	// 添加客户信息
	public boolean saveClientManagement(ClientManagement clientManagement);

	// 查询客户信息
	public List findAllClientManagement(int pageNo, int pageSize);

	// 查询客户信息总数
	public Integer clientManagementcount();

	// 根据ID查询客户信息
	public ClientManagement findByID(int id);
	// 根据UserID查询客户信息
	public ClientManagement findByUserID(int id);

	// 修改客户信息
	public boolean updateClientManagement(ClientManagement clientManagement);

	// 删除客户信息
	public boolean deleteClientManagement(ClientManagement clientManagement);

	// 条件查询
	public List findconditions(ClientManagement clientManagement, int pageNo,
			int pageSize);

	// 条件查询总数
	public Integer getcount(ClientManagement clientManagement);

	/**
	 * @Title: add
	 * @Description: 添加客户
	 * @param clientManagement
	 * @return void
	 * @throws
	 */
	public void add(ClientManagement cm);

	/**
	 * @Title: delClientManagementById
	 * @Description: 根据ID删除客户
	 * @param id
	 * @return void
	 * @throws
	 */
	public void delClientManagementById(int id);

	/**
	 * @Title: update
	 * @Description: 修改客户
	 * @param cm
	 * @return void
	 * @throws
	 */
	public void update(ClientManagement cm);

	/**
	 * @Title: getClientManagementById
	 * @Description: 根据ID获取
	 * @param id
	 * @return ClientManagement
	 * @throws
	 */
	public ClientManagement getClientManagementById(int id);

	/**
	 * @Title: queryClientManager
	 * @Description: 初始化客户信息
	 * @param pageNo
	 * @param pageSize
	 * @return Object[]
	 * @throws
	 */
	public Object[] queryClientManager(int pageNo, int pageSize);

	/**
	 * @Title: queryClientByCondition
	 * @Description: 根据条件查询
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return Object[]
	 * @throws
	 */
	public Object[] queryClientByCondition(Map map, int pageNo, int pageSize);

	/**
	 * @Title: queryAllClient
	 * @Description: 查询所有用户
	 * @return List
	 * @throws
	 */
	public List queryAllClient();

	/****
	 * 将客户信息转换为用户
	 * 
	 * @return
	 */
	boolean zhClientToUsers();
		
	
}
