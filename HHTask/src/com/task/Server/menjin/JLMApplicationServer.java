package com.task.Server.menjin;

import java.util.List;
import java.util.Map;

import com.task.entity.Users;
import com.task.entity.caiwu.noncore.PayableType;
import com.task.entity.menjin.AccessEquipment;
import com.task.entity.menjin.DoorType;
import com.task.entity.menjin.FingerprintMg;
import com.task.entity.menjin.JLMApplication;
import com.task.entity.menjin.Operation;

/**
 * 
 * @author LiCong 2017-02-09
 *
 */
@SuppressWarnings("unchecked")
public interface JLMApplicationServer {
	/**
	 * 添加
	 * @author Li_Cong
	 * @param jLMApplication 卷帘门申请表
	 * @param operation 操作表list
	 * @return
	 */
	public String addJLMApplication(JLMApplication jLMApplication, List<Operation> operation);// 添加
	/**
	 * 添加
	 * @author YYHB
	 * @return
	 */
	public String addandroid(JLMApplication jLMApplication, List<Operation> operation);// 添加

	/**
	 * 修改
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public String updateJLMApplication(JLMApplication jLMApplication);// 修改

	/**
	 * 删除
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public String deleteJLMApplication(Integer id);// 删除

	/**
	 * 根据id查询对象
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public JLMApplication byIdJLMApplication(Integer id);// 根据id得到对象

	/**
	 * 分页查询
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public Map<Integer, Object> findJLMApplication(JLMApplication jLMApplication,
			int pageNo, int pageSize);// 分页查询对象
	/**
	 * 查询申请记录
	 * 
	 * @author YYHB
	 * @return
	 */
	public Map<Integer, Object> findJLMApplicationByCondition(
			JLMApplication application, int pageNo, int pageSize,
			String tag);
	/**
	 * 查询门禁类型
	 * 
	 * @author YYHB
	 * @return
	 */
	public Map<Integer, Object> finddoortype(
			DoorType doortype, int pageNo, int pageSize,
			String tag);
	/**
	 * 获取所有卷帘门数据
	 * @return
	 */
	public List findAccessE();
	public List selectJlmApplication(String proposer_id);
	public List selectOperation(Integer ta_jlm_operation);
	/**
	 * 打开库位门
	 */
	String OpenDoorById(Integer id,String doorIp,String doorPort);
	/**
	 * 关闭库位们
	 */
	String ColseDoorById(Integer id,String doorIp,String doorPort);
	public List findDoorType();
	/**
	 * 添加门禁类型
	 * @param startDate
	 * @return
	 */
	public String saveDoorType(DoorType startDate);
	/**
	 * 删除门禁类型
	 * @param id
	 * @return
	 */
	public String deleteEner(Integer id);
	public String findPayableType();
	/**
	 * 什么类型的门
	 * @param Type
	 * @return
	 */
	public List findAccessE(String Type);
	
}
