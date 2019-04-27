package com.task.Server.fin.budget;

import java.awt.BufferCapabilities.FlipContents;
import java.io.File;
import java.util.List;

import com.task.entity.caiwu.baobiao.BalanceSheet;
import com.task.entity.caiwu.baobiao.CashFlow;
import com.task.entity.caiwu.baobiao.Management;
import com.task.entity.caiwu.baobiao.ProfitSheet;
import com.task.entity.caiwu.baobiao.XsfyjCwfyMx;
import com.task.entity.fin.budget.SubBudgetRate;
import com.task.entity.fin.budget.SubBudgetRateMonth;

/**
 * 科目预算金额server
 * 
 * @author jhh
 * 
 */
public interface SubjectBudgetServer {

	/***
	 * 科目预算查询
	 * 
	 * @param subBudgetRate
	 * @param cpage
	 * @param pageSize
	 * @return
	 */
	Object[] findSubBudget(SubBudgetRate subBudgetRate, Integer cpage,
			Integer pageSize);

	/***
	 * 查询所有科目信息
	 * 
	 * @return
	 */
	List<SubBudgetRate> findAllSubBudget();
	/**** 查询所有科目信息
	* 
	* @return
	*/
	List<SubBudgetRate> findAllSubBudgetByUser();

	/***
	 * 更新科目选中状态根据部门id
	 * 
	 * @return
	 */
	void updateSBRateFroDept(Integer deptId);

	/***
	 * 通过id查询科目信息
	 * 
	 * @param id
	 * @return
	 */
	SubBudgetRate findSubBudgetRateById(Integer id);

	/**
	 * 添加科目
	 * 
	 * @param subBudgetRate
	 * @return
	 */
	boolean addSubBudgetRate(SubBudgetRate subBudgetRate);

	/**
	 * 删除科目
	 * 
	 * @param subBudgetRate
	 * @return
	 */
	boolean delSubBudgetRate(SubBudgetRate subBudgetRate);

	/**
	 * 修改科目
	 * 
	 * @param subBudgetRate
	 * @return
	 */
	boolean updateSubBudgetRate(SubBudgetRate subBudgetRate);

	Object[] updateSubBudgetRate(SubBudgetRate subBudgetRate, Integer id);

	/***
	 * 科目与部门绑定
	 * 
	 * @return
	 */
	boolean updateSubDept(Integer deptId, Integer subId);
	/**
	 * 按月份 查询资产负债表信息;
	 */
	BalanceSheet findbalanceByMonths(String months);
	/**
	 * 按月份 查询利润表信息;
	 */
	ProfitSheet findprofitByMonths(String months);
	/**
	 * 按月份 查询资金流动表信息;
	 */
	CashFlow findcashflowByMonths(String months);
	/**
	 *按月份 查询管理费用明细表信息;
	 * @param id
	 * @return
	 */
	Management findMaByMonths(String months);
	
	
	List findAllSubDpetjsp(Integer id);

	Boolean bdAndqxUser(Integer id, Integer id2);
	/**
	 * 导入月度科目余额表
	 * @param file
	 * @param months
	 * @return
	 */
	String daoru(File file,String months);
	/**
	 * 列出计算公式和计算科目明细;
	 */
	Object[] getjisunGS(String binahao,String month);
	/**
	 *查询 销售费用及财务费用明细表
	 * @param months
	 * @return
	 */
	XsfyjCwfyMx findXcmByMonths(String months);
	/**
	 * 月末结转，本期末改为下月期初;发生额改为0；
	 * jiezhuan
	 */
	String jiezhuang();
	/**
	 * 科目余额月份记录显示;
	 */
	List<SubBudgetRateMonth> findSubMonths(String months);
	
	/**
	 * getNextSubNumber
	 */
	String getNextSubNumber(Integer id);
	
	/**
	 * 科目余额与用户绑定或解绑
	 * 
	 */
	Boolean	SubBangUsers(Integer userId,Integer[] deptId);
	
	void updateSBRateFroUsers(Integer deptId);

	/**** 查询符合条件的科目信息
	 * 
	 * @param tag
	 * @return
	 */
	List<SubBudgetRate> findAllSubBudgetByUser(String tag);
	
}

