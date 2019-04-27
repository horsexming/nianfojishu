package com.task.Server.yw;

import com.task.entity.BusinessSubsidiary;


/**
 * @Title: IBusinessSubsidiaryService.java
 * @Package com.task.Server.yw
 * @Description: TODO 业务明细接口
 * @author 曾建森
 * @date 2012-10-31 下午01:35:30
 * @version V1.0
 */
public interface IBusinessSubsidiaryService {
	/**
	 * @Title: getBusinessSubsidiary
	 * @Description: 根据ID查询明细
	 * @param @param id
	 * @return BusinessSubsidiary
	 * @throws
	 */
	public BusinessSubsidiary getBusinessSubsidiary(int id);
	/**
	 * @Title: add
	 * @Description: TODO 添加明细
	 * @param @param sellId
	 * @param @param businessId 
	 * @return void
	 * @throws
	 */
	public void add(int sellId,int businessId);
	/**
	 * @Title: del
	 * @Description: TODO 删除明细
	 * @param id 
	 * @return void
	 * @throws
	 */
	public void del(int id);
	/**
	 * @Title: queryBusinessSubsidiaryByBusinessId
	 * @Description: TODO 根据业务ID查询明细
	 * @param @param id
	 * @param @param pageNo
	 * @param @param pageSize
	 * @return Object[]
	 * @throws
	 */
	public Object[] queryBusinessSubsidiaryByBusinessId(int id,int pageNo,int pageSize);
}
