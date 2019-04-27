package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.Users;
import com.task.entity.WorkLog;

/**
 * 日志Server层
 * 
 * @author 刘培
 * 
 */

public interface WorkLogServer {
	public boolean addWorkLog1(WorkLog workLog);//添加日志 (指派)
	public boolean addWorkLog(WorkLog workLog);// 添加日志

	public Object[] findWorkLogByCondition(WorkLog workLog, int pageNo,
			int pageSize,String pageStatus); // 查询个人日志条件查询(分页)

	public boolean updateWorkLog(WorkLog workLog); // 修改

	public WorkLog findWorkLogById(Integer id);// 通过Id查询日志

	public boolean delWorkLog(WorkLog workLog); // 删除日志

	public List<WorkLog> findLogStatus();//个人完成情况
	
	public List<WorkLog> findLogStatusCao();//个人超时未完成情况;
	
	public List<Users> findLogStatusDe();//个人工作记录办理中情况
	public List<Users> findLogStatusDeUser();//个人工作记录待办情况

	public List<Users> findlogStatusBi();//部门工作记录办理中情况
	public List<Users> findlogStatusBiUser();//部门工作记录待办情况

	public List<String> findDeptList();//所有部门查看
	
	public List<WorkLog> finddzpStatus();//查看个人待指派的任务;
	
	public List<WorkLog> findzpStatus();//查看个人指派的任务;
	
	public List<WorkLog> findqrStatus();//查看个人所指派的任务，任务人已确认情况;
	
	public List<WorkLog> findwqrStatus();//查看个人未确认任务;
	
	public List<Users> findLogStatusDeUser0();
}
