package com.task.Server;

import java.util.Date;
import java.util.List;

import com.task.entity.Econdition;
import com.task.entity.Machine;
import com.task.entity.Maintenance;
import com.task.entity.Parts;
import com.task.entity.ProcardEss;
import com.task.entity.Responsibilities;

public interface EquipmentService {
	public boolean add(Maintenance maintenance, String pageStatus);// 增加

	public boolean delete(Maintenance maintenance);// 删除

	public boolean update(ProcardEss procard);// 修改

	public List findAllparts(String barcode);

	public Maintenance findAssetById(int id);// 根据ID查询

	public ProcardEss findAssetBId(int id);// 根据ID查询

	public Object[] findAll(Maintenance maintenance, int pageNo, int pageSize,
			String status, Date date1, Date date2);

	public List findAll(String status);

	public int findAlls(Date date1, Date date2);// 查询设备总故障次数

	public int findAllss(Date date1, Date date2);// 查询设备故障排除次数

	public Object[] findAllys(Econdition econdition, String month1,
			String month2, int pageNo, int pageSize, String status);// 查询设备总数量

	List selectPeopleForZhipai(String repairMan);

	List findAllByStatus(String barcode);
	Machine findAllByStatus(Integer id);

	List findAllwarehousing(String barcode);

	public List findAllByStatuss(String status);

	public int findAlly(Maintenance maintenance);

	public boolean addParts(Parts parts);// 零件表

	List findAllbarcoder(String barcode);

	public Object[] findPartsByCondition(Parts parts, Date date1, Date date2,
			int pageNo, int pageSize);

	public String excelMaintenance(Date date1, Date date2);// 导出Excel月度统计

	public boolean update(Maintenance maintenance);// 修改

	/***
	 * 更新申报明细状态为"采购中"
	 * 
	 * @param barcode
	 * @return
	 */
	boolean updateOaAppDetail(String barcode);

	public Object[] listresponsibilities(Responsibilities responsibilities,
			int parseInt, int pageSize);

	public void addresponsibilities(Responsibilities responsibilities);

	public void deleteresponsibilities(Responsibilities responsibilities);

	public Responsibilities ByIdResponsibilities(Integer id);

	public void updateresponsibilities(Responsibilities responsibilities);

	public String calculateUpdateTime();

}
