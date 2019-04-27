package com.task.ServerImpl.yw;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.yw.IBusinessSubsidiaryService;
import com.task.entity.BusinessSubsidiary;

/**
 * @Title: BusinessSubsidiaryServiceImpl.java
 * @Package com.task.ServerImpl.yw
 * @Description: TODO 业务明细实现类
 * @author 曾建森
 * @date 2012-10-31 下午01:37:50
 * @version V1.0
 */
public class BusinessSubsidiaryServiceImpl implements IBusinessSubsidiaryService {
	
	private TotalDao totalDao;
	/**
	 * @Title: add
	 * @Description: TODO 添加明细
	 * @param @param sellId
	 * @param @param businessId 
	 * @return void
	 * @throws
	 */
	public void add(int sellId,int businessId) {
		BusinessSubsidiary bu = new BusinessSubsidiary(sellId,businessId);
		totalDao.save(bu);
	}
	/**
	 * @Title: del
	 * @Description: 删除明细
	 * @param id 
	 * @return void
	 * @throws
	 */
	public void del(int id) {
		BusinessSubsidiary bs =  (BusinessSubsidiary) totalDao.getObjectById(BusinessSubsidiary.class, id);
		totalDao.delete(bs);
	}
	/**
	 * @Title: getBusinessSubsidiary
	 * @Description: 根据ID查询明细
	 * @param  id
	 * @return BusinessSubsidiary
	 * @throws
	 */
	public BusinessSubsidiary getBusinessSubsidiary(int id) {
		return (BusinessSubsidiary) totalDao.getObjectById(BusinessSubsidiary.class, id);
	}
	/**
	 * @Title: queryBusinessSubsidiaryByBusinessId
	 * @Description: TODO 根据业务ID查询明细
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @return Object[]
	 * @throws
	 */
	public Object[] queryBusinessSubsidiaryByBusinessId(int id,int pageNo,int pageSize) {
		String hql ="from BusinessSubsidiary b where b.businessId = ?";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize,id);
		int count = totalDao.getCount(hql,id);
		return new Object[]{list,count};
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	
}
