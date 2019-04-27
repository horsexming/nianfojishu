package com.task.Server.sop.spc;

import java.io.File;
import java.util.List;

import com.task.entity.Machine;
import com.task.entity.sop.spc.SpcControl;
import com.task.entity.sop.spc.SpcControlGroup;
import com.task.entity.sop.spc.SpcGroups;

/**
 * 
 * @author 王晓飞
 *
 */
public interface SpcControlServer {
	/**
	* 添加spc控制表表头
	*/
	String	addSpcControl(SpcControl  spcControl);
	/**
	 * 计算总均值,极差均值等；
	 */
	String jisun(Integer id);
	/**
	 * 查询所有spc控制表表头
	 */
	Object[] findAllSpcControlList(SpcControl  spcControl,Integer pageNo,Integer pageSize,String status);
	/**
	 * 根据Id查询spc控制表表头
	 */
	Object[] findSpcControlById(Integer id);
	/**
	 * 添加spc组数控制;
	 */
	String addspcGroups(List<SpcGroups> spcGroupsList);
	/**
	 * 修改spc组数控制;
	 */
	String updatespcGroups(List<SpcGroups> spcGroupsList);
	/**
	 * 查询spc组数控制
	 */
	List<SpcGroups> findAllspcGroups();
	/**
	 * 查询所有machine
	 */
	Object[] findAllMachine(Machine machine,Integer pageNo,Integer pageSize,String status);
	/**
	 * 根据设备编号查询machine；
	 */
	Machine findMachineByNo(String shebeiNo);
	/**
	 * 通过屏幕ID获取SPC（默认版本）信息;
	 */
	List<SpcControl> findSpcControlByScreenId(Integer id);
	/**
	 * 导入某个spc控制表头的数据
	 */
	String daoru(File file ,Integer id);
	/**
	 * 删除spc数据
	 */
	String	delSPC(Integer id);
}
