package com.task.Server.kvp;

import java.util.List;
import java.util.Map;

import com.task.entity.Users;
import com.task.entity.kvp.EightBReport;
import com.task.entity.kvp.ExecuteKVP;
import com.task.entity.kvp.ImproveKVP;
import com.task.entity.kvp.KVPAssess;

public interface KVPAssessServer {

	/***
	 * 添加产品评估
	 * @param kvpAssess
	 * @param improveKVPList
	 * @return
	 */
	Map<Integer,Object> saveKVPAssess(KVPAssess kvpAssess);

	/***
	 * 查询kvp产品审核
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	Object[] findExamList(int parseInt, int pageSize);
	
	/***
	 * kvp产品审批(通过、驳回)
	 * @param detailSelect
	 * @param tag
	 * @return
	 */
	boolean updateExamBonus(Integer[] detailSelect, String tag);

	/***
	 * 查看kvp产品评估
	 * @param kvpAssess
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	Object[] findBargain(KVPAssess kvpAssess, int parseInt, int pageSize,String test);

	KVPAssess findKVPAssessById(Integer id);

	/***
	 * 修改kvp产品评估
	 * @param kvpAssess
	 * @return
	 */
	boolean updateKVPAssess(KVPAssess kvpAssess);

	/***
	 * 根据编号删除kvp产品评估
	 * @param id
	 * @return
	 */
	void delKVPAssess(Integer id);

	/***
	 * 填写项目执行单
	 * @param kvpAssess
	 * @param executeKVP
	 * @return
	 */
	boolean saveExecuteKVP(KVPAssess kvpAssess, ExecuteKVP executeKVP);

	/***
	 * 查询所有部门编码
	 * @return
	 */
	List selectDept();

	/***
	 * 通过部门查询员工
	 * @param dept
	 * @return
	 */
	List selectUser(String dept);

	/***
	 * 查询对应的员工号
	 * @param name
	 * @return
	 */
	Users selectUserCode(String name);

	/**
	 * 查询执行单
	 * @param id
	 * @return
	 */
	ExecuteKVP findExecuteKVPById(Integer id);

	/**
	 * 修改执行单
	 * @param executeKVP
	 * @return
	 */
	boolean updateExecuteKVP(ExecuteKVP executeKVP);

	/***
	 * 查询项目执行单审核
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	Object[] findExamList1(int parseInt, int pageSize);

	/***
	 * 审核项目执行单
	 * @param detailSelect
	 * @param tag
	 * @return
	 */
	boolean updateExamExecuteKVP(Integer[] detailSelect, String tag);

	/***
	 * 查询所有项目执行单
	 * @param executeKVP
	 * @param parseInt
	 * @param pageSize
	 * @param tag
	 * @return
	 */
	Object[] findEightBReport(ExecuteKVP executeKVP, int parseInt,
			int pageSize, String tag);

	/***
	 * 添加8B报告
	 * @param eightBReport
	 * @return
	 */
	boolean addEightBReport(EightBReport eightBReport,Integer id);

	/***
	 * 查询8B报告审核
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	Object[] findExamEightBReport(int parseInt, int pageSize);

	/***
	 * 8B审核
	 * @param detailSelect
	 * @param tag
	 * @return
	 */
	boolean updateExamEightBReport(Integer[] detailSelect, String tag);

	/***
	 * 根据编号查询8B报告单
	 * @param id
	 * @return
	 */
	EightBReport findEightBReportById(Integer id);

	/***
	 * 根据id查询项目执行单
	 * @param id
	 * @return
	 */
	ExecuteKVP findExecuteKVPById1(Integer id);

	/***
	 * 更新8B报告
	 * @param eightBReport
	 * @return
	 */
	boolean updateEightBReport(EightBReport eightBReport,Float costsavings);

	/**
	 * 删除项目执行单
	 * @param id
	 * @return
	 */
	boolean delEightBReport(Integer id);
	

}
