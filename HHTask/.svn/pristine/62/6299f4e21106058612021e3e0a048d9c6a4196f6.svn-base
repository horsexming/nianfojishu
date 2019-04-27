package com.task.Server.fin.budget;

import java.util.List;

import com.task.entity.Price;
import com.task.entity.fin.budget.SaleBudget;
import com.task.entity.fin.budget.SaleBudgetDetail;

/**
 * 销售收入server
 * @author jhh
 *
 */
public interface SaleBudgetServer {
	Object[] SaveplanMonth();
	/**
	 * 查找销售收入预算信息
	 * @param saleBudget
	 * @param cpage
	 * @param pageSize
	 * @param tag
	 * @return
	 */
	Object[] findSaleBudget(SaleBudget saleBudget,Integer cpage, Integer pageSize,String tag);
	/**
	 * 读取产品信息
	 * @param price
	 * @param cpage
	 * @param pageSize
	 * @param id
	 * @return
	 */
	Object[] findSaleBudgetProduct(Price price,Integer cpage, Integer pageSize,Integer id);
	/**
	 * 选择下拉菜单
	 * @param tag
	 * @return
	 */
	String findbudgetStyle(String tag);
	/**
	 * 根据预算ID查找明细
	 * @param id
	 * @return
	 */
	List findBudgetDetailBysbt(SaleBudgetDetail sbdetail,Integer id);
	/**
	 * 根据ID查找预算对象
	 * @param id
	 * @return
	 */
	SaleBudget getSaleBudgetById(Integer id);
	/**
	 * 选择产品
	 * @param priceSelect
	 * @return
	 */
	Object[] querySelectedProduct(Integer[] priceSelect);
	/**
	 * 保存明细
	 * @param priceSelect
	 * @param count
	 * @param id
	 * @return
	 */
	public boolean saveDetail(Integer[] priceSelect, Float[] count, String[] isInclud,Integer id);
	/**
	 * 根据ID查找预算明细
	 * @param id
	 * @param tag
	 * @return
	 */
	SaleBudgetDetail getDetailById(Integer id,String tag);
	/**
	 * 更新单条预算明细
	 * @param sbd
	 * @return
	 */
	boolean updateDetailById(SaleBudgetDetail sbd);
	/**
	 * 根据ID删除单条明细
	 * @param id
	 * @return
	 */
	boolean deleteDetailById(SaleBudgetDetail sbd);
	/**
	 * 月度销售月算总额汇总
	 * @param saleBudget
	 * @return
	 */
	boolean subSaleBudget(SaleBudget saleBudget);
	/***
	 * 审批月度销售收入预算
	 * @param id
	 * @param tag
	 * @return
	 */
	boolean updateSaleBudgetById(Integer id,String tag);
	/***
	 * 月度预算销售审核列表
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	Object[] findExamList(int parseInt, int pageSize);
	/***
	 * 审核(通过\驳回)
	 * @param detailSelect
	 * @param tag
	 * @return
	 */
	boolean updateExamBonus(Integer[] detailSelect, String tag);
		
}
