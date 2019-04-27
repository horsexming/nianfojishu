package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.DeptNumber;
import com.task.entity.Repair;
import com.task.entity.Responsibilities;
import com.task.entity.Users;
import com.task.entity.repair.RepairType;

public interface RepairService {

	public boolean add(Repair repair);// 增加

	public boolean delete(Repair repair);// 删除

	public boolean update(Repair repair);// 修改

	public Repair findAssetById(int id);// 根据ID查询

	public Object[] findAll(Repair repair, int pageNo, int pageSize,
			String status);

	public List findAll(String status);

	public String selectpeople(String category);

	List selectPeopleForZhipai(String category);

	List findAllByStatus(String status);

	public int findAlls(String date1, String date2);// 查询设备总故障次数

	public int findAllss(String date1, String date2);// 查询设备故障排除次数

	public Object[] findAllpop(Responsibilities responsibilities, int pageNo,
			int pageSize,String test);

	public Integer findcadeandname(String employeenumber, String repairname);

	public List<Map> findDept();

	public void saveRepair(Responsibilities responsibilities);
	
	public boolean deleterepair(Integer  id);// 删除

	public Responsibilities findRepairpopById(Integer delId);

	public void updateRepair(Responsibilities responsibilities);
/************************报修类型**************************************************/
	public Object[] findRepairtype(RepairType repairType, int pageNo, int pageSize);

	public void saveRepairtype(RepairType repairType);

	public void delRepairtype(Integer delId);

	public RepairType findRepairtypeById(Integer delId);

	public void updateRepairtype(RepairType repairType);

	public List<Map> findRepairByName(String repairdepartment);

	public List<Map> findRepairByName1(String category,String repairdepartment);

	public Object[] findRepairtype1(RepairType repairType, int pageNo, int pageSize);
}
