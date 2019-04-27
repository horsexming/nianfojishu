package com.task.Server.renshi;

import java.util.List;
import java.util.Map;

import com.task.entity.renshi.DormitoryLog;


/**
 * 单身宿舍申请表
 * @author 李聪
 *
 */
public interface DormitoryLogServer {
	
	/**
	 * 添加宿舍申请表对象
	 * @param dormitoryLog
	 * @return
	 */
	public String addDormitoryLog(DormitoryLog dormitoryLog);
	
	/**
	 * 通过id宿舍申请表对象
	 * @param dormitoryLog
	 * @return
	 */
	public boolean deleteDormitoryLog(Integer id);
	
	/**
	 * 修改宿舍申请表对象
	 * @param dormitoryLog
	 * @return
	 */
	public boolean updateDormitoryLog(DormitoryLog dormitoryLog);
	
	/**
	 * 通过id查询宿舍申请表对象
	 * @param dormitoryLog
	 * @return
	 */
	public DormitoryLog getDormitoryLogById(Integer id);
	
	/**
	 * 查询所有宿舍申请表对象
	 * @param dormitoryLog
	 * @return
	 */
	public List<DormitoryLog> findAll();
	
	/**
	 * 按条件分页查询宿舍申请表
	 * @param dormitoryLog 对象
	 * @param pageNo 起始页
	 * @param pageSize 每页数量
	 * @return
	 */
	public Map<Integer, Object> findDormitoryLogsByCondition(DormitoryLog dormitoryLog, int pageNo, int pageSize);
	//查询自己提交的宿舍申请单
	public Map<Integer, Object> findDormitoryLogsBycodeCondition(DormitoryLog dormitoryLog, int pageNo, int pageSize);
	
	//根据usersId来查询当前员工信息
	public DormitoryLog findDormitoryLogByCodeId(Integer integer);
}
