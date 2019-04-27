package com.task.Server;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.task.entity.CompanyBoss;
import com.task.entity.CompanyVIP;
public interface CompanyVIPServer {
	/**
	 * 查找最大会员编号
	 * @return
	 */
	public String findMaxvipNO();
	
	/**
	 * 添加企业会员基本资料
	 * @param companyVIP
	 * @param attachment
	 * @param attachmentFileName
	 * @param fatherPartNumber
	 * @return
	 */
	public boolean add(CompanyVIP companyVIP);
	
	/**
	 * 删除企业会员
	 * @param companyVIP
	 * @return
	 */
	public boolean del(CompanyVIP companyVIP);
	
	/**
	 * 修改企业会员
	 * @param companyVIP
	 * @return
	 */
	public boolean update(CompanyVIP companyVIP,File[] attachment,
			String[] attachmentFileName, String fatherPartNumber);
	/**
	 * 分页查找所有企业会员
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<CompanyVIP> FindAllCompanyVIP( int pageNo, int pageSize);
	/**
	 * 分页 按条件查找企业会员
	 * @param buhecompanyVIP
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findCompanyVIPCondition(CompanyVIP buhecompanyVIP,
			int pageNo, int pageSize);
	/**
	 * 根据id查找CompanyVIP
	 * @param id
	 * @return
	 */
	public CompanyVIP findCompanyVIPById(Integer id);
	
	/**
	 * 根据id查找CompanyBoss
	 * @param id
	 * @return
	 */
	public CompanyBoss findCompanyBossById(Integer id);
	public int getcont();
	
	/**
	 * 根据会员查找企业会员
	 * @param vipNo
	 * @return
	 */
	public CompanyVIP showComanyVIPbyvipNO(String vipNo);
}
