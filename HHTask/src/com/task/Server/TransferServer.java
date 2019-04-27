package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.Transfer;

public interface TransferServer {
	/**
	 * 添加
	 * @param transfer
	 * @return
	 */
	public String add(Transfer transfer);
	/**
	 * 删除
	 */
	public boolean del(Transfer transfer);
	/**
	 * 修改
	 */
	public boolean update(Transfer transfer);
	
	/**
	 * 根据userId查询
	 */ 
	public List<Transfer> findTransferbyuserId(Integer userId);
	/**
	 * 查询所有（分页）
	 * 
	 */
	List<Transfer> FindAllTransfer( int pageNo, int pageSize);
	
	/**
	 * 条件查询；
	 * 
	 */
	public Map<Integer, Object> findTransferByCondition(Transfer transfer,
			int pageNo, int pageSize);
		
	/**
	 * 获得总条数
	 */
	public int getcont();
	/**\
	 * 根据Id查询
	 */
	Transfer findTransferbyId(Integer id);
	

}
