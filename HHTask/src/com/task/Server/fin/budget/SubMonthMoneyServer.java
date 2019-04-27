package com.task.Server.fin.budget;

import java.util.List;

import com.task.entity.fin.budget.DeptMonthBudget;
import com.task.entity.fin.budget.SubMonthMoney;

/**
 * 科目预算金额server
 * 
 * @author 刘培
 * 
 */
public interface SubMonthMoneyServer {

	/***
	 * 查询所有月度科目预算总金额(根层)
	 * 
	 * @return
	 */
	Object[] saveSmmByCondition(SubMonthMoney smm, int pageNo, int pageSize);

	/***
	 * 根据rootId查询科目结构
	 * 
	 * @param rootId
	 * @return
	 */
	List<SubMonthMoney> findSmmByRootId(Integer rootId, String pageStatus);

	/***
	 * 根据id查询月度科目总额
	 * 
	 * @param id
	 * @return
	 */
	SubMonthMoney findSmmById(Integer id);

	/***
	 * 添加部门填报明细
	 * 
	 * @param deptMonthBudget
	 * @return
	 */
	boolean addDeptMonthBudget(DeptMonthBudget deptMonthBudget, Integer fatherId);

	/***
	 * 查询该月份填报明细(计划内是否存在)
	 * 
	 * @param budgetMonth
	 * @param fatherId
	 * @param jhStatus
	 * @return
	 */
	List<DeptMonthBudget> findDeptMonthBu(String budgetMonth, Integer fatherId,
			String jhStatus);

	/***
	 * 查询待审核的部门填报信息
	 * 
	 * @return
	 */
	Object[] findAuditDmB(DeptMonthBudget deptMonthBudget, int pageNo,
			int pageSize, Integer rootId, String pageStatus);

	/***
	 * 审批操作
	 * 
	 * @param submmIds
	 * @param pageStatus
	 * @return
	 */
	boolean updateAudit(Integer[] submmIds, String pageStatus);

	/***
	 * 通过id查询部门填报明细信息
	 * 
	 * @param id
	 * @return
	 */
	DeptMonthBudget findDeptMonthBudgetById(Integer id);

	/***
	 * 修改部门填报明细信息
	 * 
	 * @param dmBudget
	 * @return
	 */
	boolean updateDeptMonthBudget(DeptMonthBudget dmBudget);

	/***
	 * 删除部门填报明细信息
	 * 
	 * @param dmBudget
	 * @return
	 */
	boolean delDeptMonthBudget(DeptMonthBudget dmBudget);

	/***
	 * 修改部门填报明细信息
	 * 
	 * @param dmBudget
	 * @return
	 */
	boolean updateDeptMonthBudget(DeptMonthBudget dmBudget,
			DeptMonthBudget oldDmBudget);

	void exportExcel(String budgetMonth);

	void exportExcel1(String budgetMonth);
	/***
	 * 月度预算审核列表
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	Object[] findExamList(int parseInt, int pageSize,DeptMonthBudget dmb);

	/***
	 * 
	 * @param detailSelect
	 * @param tag
	 * @return
	 */
	boolean updateExamBonus(Integer[] detailSelect, String tag);

	/***
	 * 月度预算审核列表(计划外)
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	Object[] findExamList1(int parseInt, int pageSize);

}
