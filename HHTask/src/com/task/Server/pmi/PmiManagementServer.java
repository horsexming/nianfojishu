package com.task.Server.pmi;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.entity.Machine;
import com.task.entity.pmi.PmiManagement;
import com.task.entity.sop.ProcessInfor;

public interface PmiManagementServer {

	/***
	 * 查询所有的pmi
	 * @param pmiManagement
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	Object[] findPmi(PmiManagement pmiManagement, int parseInt, int pageSize, Machine achine);

	/***
	 * 添加pmi
	 * @param pmiManagement
	 * @return
	 */
	boolean addPmi(PmiManagement pmiManagement);

	/***
	 * 根据id查询PMI
	 * @param pmiManagement
	 * @return
	 */
	PmiManagement salPmiByid(PmiManagement pmiManagement);

	/***
	 * 更新PMI
	 * @param pmiManagement
	 * @return
	 */
	boolean updatePmi(PmiManagement pmiManagement);

	/***
	 * 删除PMI
	 * @param pmiManagement
	 * @return
	 */
	boolean delPmi(PmiManagement pmiManagement);

	/***
	 * 查询对应设备
	 * @param machine
	 * @param parseInt
	 * @param pageSize
	 * @param pmiId
	 * @return
	 */
	Object[] findMachineByid(Machine machine, int parseInt, int pageSize,
			Integer pmiId);
	
	/***
	 * 查询对应生产工序
	 * @param processInfor
	 * @param parseInt
	 * @param pageSize
	 * @param pmiId
	 * @return
	 */
	Object[] findProcessInforByid(ProcessInfor processInfor, int parseInt, int pageSize,
			Integer pmiId);

	/***
	 * 查询所有设备
	 * @return
	 */
	List findMachineworkPosition();

	/***
	 * 添加对应设备信息
	 * @param machine
	 * @param pmiId
	 * @return
	 */
	boolean addMachine(Machine machine, Integer pmiId);

	/***
	 * 根据工位查询设备
	 * @param id1
	 * @return
	 */
	List findMachineByworkPosition(String workPosition);

	/***
	 * 删除相应的设备
	 * @param machine
	 */
	boolean delMachine(Machine machine);
	/**
	 * 通過inforID获取roodID
	 * @param id
	 * @return
	 */
	Integer findRoodId(Integer id);
	/**
	 * 通过pmi_id来获取	ip
	 */
	
}
