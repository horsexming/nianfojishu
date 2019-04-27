package com.task.Server.sys;

import java.util.List;
import java.util.Map;

import com.task.entity.system.CompanyInfo;
import com.task.entity.system.LicenseMsg;

/**
 * 公司信息管理
 * 
 * @author 唐晓斌
 * 
 */
public interface CompanyInfoServer {
	/***
	 * 根据条件查询公司信息分页显示
	 * 
	 * @param companyInfo
	 *            对象
	 * @param pageNo
	 *            页数
	 * @param pageSize
	 *            每页数量
	 * @return
	 */
	public Map<Integer, Object> findCompanysByCondition(
			CompanyInfo companyInfo, int pageNo, int pageSize);

	/**
	 * 添加公司信息对象
	 * 
	 * @param companyInfo
	 *            公司对象
	 * @return
	 */
	public boolean add(CompanyInfo companyInfo);

	/**
	 * 删除公司对象
	 * 
	 * @param companyInfo
	 *            公司对象
	 * @return
	 */
	public boolean delete(CompanyInfo companyInfo);

	/**
	 * 通过id删除公司对象
	 * 
	 * @param id
	 *            公司对象id
	 * @return
	 */
	public boolean delete(Integer id);

	/**
	 * 修改公司对象
	 * 
	 * @param companyInfo
	 *            公司对象
	 * @return
	 */
	public boolean update(CompanyInfo companyInfo);

	/**
	 * 通过公司id获取公司对象
	 * 
	 * @param id
	 *            公司id
	 * @return
	 */
	public CompanyInfo getById(Integer id);

	/**
	 * 通过公司的网址获取公司的在线点数
	 * 
	 * @param companyUrl
	 * @return
	 */
	public int getOnlineCount(String companyUrl);
	/**
	 * 通过公司的网址获取公司的一体机点数
	 * 
	 * @param companyUrl
	 * @return
	 */
	public int getOneMackCount(String companyUrl);
	/**
	 * 通过公司的网址获取公司的LED点数
	 * 
	 * @param companyUrl
	 * @return
	 */
	public int getOnLEDCount(String companyUrl);
	/**
	 * 通过公司的网址获取公司的电子看板数
	 * 
	 * @param companyUrl
	 * @return
	 */
	public int getoneScreenConut(String companyUrl);
	/**
	 * 通过公司的网址获取公司对象
	 * 
	 * @param companyUrl
	 * @return
	 */
	public LicenseMsg getLicensemsg(String companyUrl);
}
