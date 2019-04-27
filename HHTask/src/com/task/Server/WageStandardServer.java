package com.task.Server;

import java.io.File;
import java.util.List;

import com.task.entity.Contract;
import com.task.entity.InsuranceGold;
import com.task.entity.Wage;
import com.task.entity.WageStandard;

/**
 * 工资模板Server层
 * 
 * @author 刘培
 * 
 */
public interface WageStandardServer {

	/***
	 * 根据工号查找该用户当前默认工资信息
	 * 
	 * @param code
	 *            工号
	 * @return
	 */
	public WageStandard findWSByUser(String code);

	public boolean addWageStandard(WageStandard wageStandard,
			WageStandard oldWageStandard, InsuranceGold insuranceGold);// 添加工资模板

	public List<WageStandard> findAllWs(int pageNo, int pageSize);// 查询所有工资模板(分页)

	public int getCountByAll();// 获得数量 (所有)

	public boolean delWs(WageStandard wageStandard);// 删除工资模板

	public WageStandard findWsById(int id);// 根据id查询工资模板

	public boolean updateWs(WageStandard wageStandard,
			WageStandard oldWageStandard);// 修改工资模板

	public Object[] findWSByCondition(WageStandard ws, int pageNo, int pageSize); // 条件查询

	public Contract findContractByCode(String code); // 通过工号和卡号查询合同编号

	@SuppressWarnings("unchecked")
	public List findWageXieYiByCode(String code, String standardStatus); // 通过工号和卡号查询薪资协议单

	/**
	 * 根据工资模板状态(默认/历史)和流程状态(审核/同意/不同意)查询工资标准信息
	 * 
	 * @author 刘培
	 * 
	 * @param standardStatus
	 *            工资模板状态(默认/历史)
	 * @param processStatus
	 *            流程状态(审核/同意/不同意)
	 * @param pageNo
	 *            页数
	 * @param pageSize
	 *            每页数量
	 * @return
	 */
	public Object[] findWsFroAudit(String standardStatus, String processStatus,
			int pageNo, int pageSize);

	/**
	 * 审核工资模版
	 * 
	 * @param wsId
	 *            [] 模版id数组
	 * @param auditStatus
	 *            审核状态(ok/back)(同意/不同意)
	 * @return boolean
	 */
	public boolean auditWageStand(Integer wsId[], String auditStatus);

	/***
	 * 更新工资模版
	 * 
	 * @param ws
	 *            工资模版对象
	 * @return boolean
	 */
	public boolean updateWS(WageStandard ws);

	/***
	 * 查询待处理工资模板(审核/不同意状态)
	 * 
	 * @param code
	 *            工号(填写工号则会根据工号查询)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findAuditWs(String code);

	/***
	 * 查询需要调整模版的人员
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findNeedUpdateWs(String chageStatus);

	/***
	 * 查询离职工资是否存在
	 * 
	 * @return
	 */
	Wage findLeaveWage(String code);

	/***
	 * 查询上个月工资是否存在
	 * 
	 * @return
	 */
	Wage findLastWage(String code);

	boolean updateJsWs(WageStandard wageStandard,String pageStatus);

	/***
	 * 查询所有默认的在职人员的工资模版
	 * 
	 * @return
	 */
	List findAllWageStandard();

	/***
	 * 通过工号得到历史的工资模版
	 * 
	 * @param code
	 * @return
	 */
	List findOldWageSByCode(String code);

	/****
	 * 公积金批量调整
	 * 
	 * @param wageSList
	 * @return
	 */
	boolean updateWageSGjj(List<WageStandard> wageSList);

	/***
	 * 查询需要添加模版的人员
	 * 
	 * @return
	 */
	List findNeedAddWs(String chageStatus);
	/**
	 * 工资模板导入
	 */
	String pladdWageStandard(File addFile);
}
