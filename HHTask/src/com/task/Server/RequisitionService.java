package com.task.Server;

import java.util.List;

import com.task.entity.Requisition;

public interface RequisitionService {
	public boolean add(Requisition requisition);

	public boolean delete(Requisition requisition);

	public boolean update(Requisition requisition);

	public Requisition findAssetById(int id);

	/***
	 * 查询所有+条件查询(分页)
	 * 
	 * @param requisition
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Object[] findAll(Requisition requisition, int pageNo, int pageSize,
			String status);

	/***
	 * 查询所有审核/打回/完成信息
	 * 
	 * @param manager
	 * @return
	 */
	public List findAll(String manager);

}
