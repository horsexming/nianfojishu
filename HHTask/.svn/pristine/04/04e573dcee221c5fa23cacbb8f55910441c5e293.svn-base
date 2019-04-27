package com.task.Server.sys;

import java.util.Map;

import com.task.entity.system.LicenseMsg;

/**
 * License管理
 * 
 * @author 唐晓斌
 * 
 */
public interface LicenseMsgServer {
	/***
	 * 根据条件查询License信息分页显示
	 * 
	 * @param LicenseMsg
	 *            对象
	 * @param pageNo
	 *            页数
	 * @param pageSize
	 *            每页数量
	 * @return
	 */
	public Map<Integer, Object> findCompanysByCondition(
			LicenseMsg licenseMsg, int pageNo, int pageSize);

	/**
	 * 添加License信息对象
	 * @param LicenseMsg License对象
	 * @return
	 */
	public boolean add(LicenseMsg licenseMsg);

	/**
	 * 删除License对象
	 * @param LicenseMsg License对象
	 * @return
	 */
	public boolean delete(LicenseMsg licenseMsg);

	/**
	 * 通过id删除License对象
	 * @param id License对象id
	 * @return
	 */
	public boolean delete(Integer id);

	/**
	 * 修改License对象
	 * @param LicenseMsg License对象
	 * @return
	 */
	public boolean update(LicenseMsg licenseMsg);

	/**
	 * 通过License id获取License对象
	 * @param id License id
	 * @return
	 */
	public LicenseMsg getById(Integer id);
   
}
