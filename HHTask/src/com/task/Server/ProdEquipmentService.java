package com.task.Server;

import java.io.File;
import java.util.List;

import com.task.entity.DJNR;
import com.task.entity.Machine;
import com.task.entity.Maintenance;
import com.task.entity.TaSopGongwei;
import com.task.entity.Users;
import com.task.entity.bybz.BaoYangBiaoZhun;
import com.task.entity.bybz.BaoYangRecord;
import com.task.entity.sop.ProcessSaveLog;
import com.task.entity.sop.ProcessWlqr;
import com.task.entity.sop.ProcessinforFuLiao;

public interface ProdEquipmentService {
	public boolean add(Machine machine);// 增加

	public boolean delete(Machine machine);// 删除

	public boolean update(Machine machine);// 修改

	public Machine findAssetById(int id);// 根据ID查询

	public int findAlly(Machine machine);

	public List findAll(String status);

	public List findAllByStatuss(String status);

	public Object[] findMachineByCondition(Machine machine, int pageNo,
			int pageSize,String pageStatus,Integer userId);
	
	public Object[] findMachineByCondition1(Machine machine, int pageNo,
			int pageSize,String pageStatus,Integer userId,String showCloumn);

	public List printStorage(int[] selected);

	boolean updateTaSopGongwei(Machine machine);// 设备编号

	public List<Machine> updatedomachine() throws Exception;

	/****
	 * 根据编号、工区、工位 查询设备
	 * 
	 * @param machine
	 * @return
	 */
	Machine findMachineByNum(Machine machine);

	/****
	 * 根据编号、工区、工位 查询设备报修记录
	 * 
	 * @param machine
	 * @return
	 */
	List findMaintenanceByNum(Machine machine);

	/***
	 * 根据工序信息查询工序领取记录
	 * 
	 * @param processInforId
	 * @return
	 */
	List findPIRLogByProId(Integer processInforId);

	/***
	 * 根据领取id查询工序领取记录明细
	 * 
	 * @param fk_pirLId
	 * @return
	 */
	List findPIRLogByFk_pirLId(Integer fkPirLId);

	/***
	 * 根据工序信息查询气密记录
	 * 
	 * @param processInforId
	 * @return
	 */
	List findAirByProId(Integer processInforId);

	public void addMachine(File addmachine);

	public boolean bddjnr(Machine machine, List<DJNR> djnrList);

	/***
	 * 根据工序信息查询本次设备能耗信息
	 * 
	 * @param processInforId
	 * @return
	 */
	Float findSbNhByProId(Integer processInforId);
	/**
	 * 根据id 获得 users
	 */
	public Users findUserById(Integer id);
	
	/**
	 * 人员绑定工位设备
	 */
	
	public boolean Userbdgw(Integer id,int[] arrayId);
	/**
	 * 解除人员绑定工位
	 */
	
	public boolean Userjcbdgw(int[] arrayId,Integer id);
	/**
	 * 根据user 查询deptId
	 */
	public Integer getdeptId(String deptNumber);
	/**
	 * 查询辅料使用情况
	 * @param id
	 * @return
	 */
	public List<ProcessinforFuLiao> findProcessFlByProId(int id);
	/**
	 * 查询临时存放信息
	 * @param id
	 * @return
	 */
	public List<ProcessSaveLog> findProcessSaveLogByProId(int id);
	/**
	 * 
	 * @param machine 工位号得到工位信息
	 * @return
	 */
	public List<TaSopGongwei> findTaSopGongwei(Machine machine);
	
	public List<TaSopGongwei> updateTaSopGongwei(List<TaSopGongwei> gongwei);
	
	/**
	 * 根据设备Id查询该设备的保养标准
	 */
	public List<BaoYangBiaoZhun> findListbybz(Integer id);
	
	/**
	 * 查询所有即将到期需要保养得设备
	 */
	public List<Machine> findjjdqby();
	
	/**
	 * 查询物料确认记录
	 */
	
	public List<ProcessWlqr> findProcessWlqrById(Integer id);
	/**
	 * 删除保养标准
	 * @param id
	 * @return
	 */
	public String deletebybz(Integer id);
	/**
	 * 添加保养记录
	 * @param byRecordList
	 * @param machine
	 * @return
	 */
	public String addBaoYangRecord(List<BaoYangRecord> byRecordList,Machine machine);
	/**
	 * 查询保养记录
	 * @param id
	 * @return
	 */
	public List<BaoYangRecord> findbyrList(Integer id);
	public Machine getmachine(Integer id);
	public List getdjnrbyId(Integer id);
}
