package com.task.Server.shizhi;

import java.util.List;
import java.util.Map;

import com.task.entity.project.QuotedProcessInfor;
import com.task.entity.shizhi.Cusimportance;

public interface CusimportanceServer {
	/**
	 *按条件分页查询客户重要系数 
	 * @param skiilScore
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findCusimportancesByCondition(Cusimportance Cusimportance, int pageNo, int pageSize);	
	/**
	 * 通过id获取客户重要系数对象
	 * @param id
	 * @return
	 */
	public Cusimportance getById(Integer id);
	/**
	 * 修改客户重要系数
	 * @param Cusimportance
	 * @return
	 */
	public boolean update(Cusimportance Cusimportance);
    /**
     *通过id 删除客户重要系数对象
     * @param id
     * @return
     */
	public boolean deleteById(Integer id);
	/**
	 * 查询所有客户重要系数
	 * @return
	 */
	public List<Cusimportance> findAll();
//    /**
//	 * 插入ClientManagement表中比cusimportance表中多出的数据
//	 * @return
//	 */
//	public boolean addUnintoData();
//	/**
//	 * 删除cusimportance表中比ClientManagement表中多出的数据
//	 * @return
//	 */
//	public boolean deleteMoreData();
//	/**
//	 * 更新ClientManagement表中和cusimportance表中都有的数据
//	 * @return
//	 */
//	public boolean updateHadData();
//	/**
//	 * cusimportance表中数据与ClientManagement表中数据进行对比
//	 * 已有的进行update,没有进行save，多出的进行delete
//	 * @return
//	 */
//	public boolean updateAll();

}
