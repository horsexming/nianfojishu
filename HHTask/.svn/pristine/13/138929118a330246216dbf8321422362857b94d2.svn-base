package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.Machine;
import com.task.entity.MachineSparePart;
import com.task.entity.MachineSparePartVo;
import com.task.entity.OaAppDetail;

public interface MachineSparePartServer {
    /**
     * 获取关键备件的分页信息
     * @param machineSparePart
     * @param parseInt
     * @param pageSize
     * @return
     */
	Map<Integer, Object> findMachineSparePartsByCondition(
			MachineSparePart machineSparePart, int parseInt, int pageSize);
    /**
     * 添加设备备件
     * @param machineSparePart
     * @return
     */
	boolean add(MachineSparePart machineSparePart);
    /**
     * 获取所有关键设备
     * @return
     */
	List<Machine> getAllKeyMachines();
	/**
	 * 通过id获取关键备件
	 * @param id
	 * @return
	 */
	MachineSparePart getById(Integer id);
	/**
	 * 修改关键备件
	 * @param machineSparePart
	 * @return
	 */
	boolean update(MachineSparePart machineSparePart);
	/**
	 * 通过id删除关键备件
	 * @param id
	 * @return
	 */
	boolean delete(Integer id);
	MachineSparePartVo getByVoId(Integer id);
	/**
	 * 修改当前库存量
	 * @return
	 */
	boolean  updateCurrCount();
	/**
	 * 生成下个月采购计划
	 * @return
	 */
	String nextMonthOa(OaAppDetail oadetail) throws Exception;
	/**
	 * 
	 * 判断有无预算记录
	 * 
	 * @return
	 */
	boolean preSaveOa();

}
