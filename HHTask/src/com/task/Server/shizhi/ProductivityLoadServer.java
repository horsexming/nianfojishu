package com.task.Server.shizhi;

import java.util.List;
import java.util.Map;

import com.task.entity.shizhi.ProductivityLoad;
import com.task.entity.sop.ProcessTemplate;

public interface ProductivityLoadServer {
	/**
	 *按条件分页查询产能负荷系数 
	 * @param skiilScore
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findProductivityLoadsByCondition(ProductivityLoad productivityLoad, int pageNo, int pageSize);	
	/**
	 * 通过id获取产能负荷系数对象
	 * @param id
	 * @return
	 */
	public ProductivityLoad getById(Integer id);
	/**
	 * 修改产能负荷系数
	 * @param ProductivityLoad
	 * @return
	 */
	public boolean update(ProductivityLoad productivityLoad);
    /**
     *通过id 删除产能负荷系数对象
     * @param id
     * @return
     */
	public boolean deleteById(Integer id);
	/**
	 * 查询所有产能负荷系数
	 * @return
	 */
	public List<ProductivityLoad> findAll();
	/**
	 * 获取所有的工序
	 * @return
	 */
    List findQpInforAll();
    /**
     * 通过工序id获取工序
     * @param id
     * @return
     */
    ProcessTemplate getQPbyQPInfoId(Integer id);
//    /**
//	 * 插入QuotedProcessInfor表中比BonusLoad表中多出的数据
//	 * @return
//	 */
//	public boolean addUnintoData();
//	 /**
//	 * 删除BonusLoad表中比QuotedProcessInfor表中多出的数据
//	 * @return
//	 */
//	public boolean deleteMoreData();
//	/**
//	 * 更新QuotedProcessInfor表中和BonusLoad表中都有的数据
//	 * @return
//	 */
//	public boolean updateHadData();
	/**
	 * BonusLoad表中数据与ProcessTemplate表中数据进行对比
	 * 已有的进行update
	 * @return
	 */
	public boolean updateAll();
}
