package com.task.Server;

import java.util.Date;
import java.util.List;

import com.task.entity.EquipmentChanges;
import com.task.entity.Machine;

public interface EquipmentChangesService {
	public boolean add(EquipmentChanges equipmentChanges);// 增加

	public boolean delete(EquipmentChanges equipmentChanges);// 删除

	public boolean update(EquipmentChanges equipmentChanges);// 修改

	public EquipmentChanges findAssetById(int id);// 根据ID查询

	public Object[] findAll(EquipmentChanges equipmentChanges, int pageNo,
			int pageSize, String status);
	public Machine findAssettById(int id);
	/***
	 * 查询所有审核/打回/完成信息
	 * 
	 * @param status
	 * @return
	 */
	public List findAll(String status);

	public Object[] findMachineByCondition(Machine machine, int pageNo,
			int pageSize);

	 public Object[] findMachineBCondition(EquipmentChanges equipmentChanges,
			int pageNo, int pageSize, Date date1, Date date2);
	 public String exportExcel(Date date1, Date date2);
}
