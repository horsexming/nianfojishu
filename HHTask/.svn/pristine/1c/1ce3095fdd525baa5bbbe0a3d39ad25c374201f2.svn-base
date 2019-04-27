package com.task.Server;

import java.util.List;

import com.task.entity.ProjectQuotationDirectLaborCost;

/**
 * 项目_外购外协
 * @author 马凯
 *
 */
public interface ProjectQuotationDirectLaborCostService {

	/**
	 * 添加对象
	 * @param viewObj
	 */
	public void add(ProjectQuotationDirectLaborCost viewObj, int quotationId);
	
	/**
	 * 修改对象
	 * @param viewObj
	 */
	public void update(ProjectQuotationDirectLaborCost viewObj);
	
	/**
	 * 删除对象
	 * @param viewObj
	 */
	public void delete(ProjectQuotationDirectLaborCost viewObj);
	
	/**
	 * 根据建议书查询
	 * @param viewObj
	 * @return
	 */
	public Object[] getByQuotation(int quotationId, int pageNo, int pageSize) ;

	/**
	 * 获取一个
	 * @param rawMaterial
	 * @return
	 */
	public ProjectQuotationDirectLaborCost get(ProjectQuotationDirectLaborCost viewObj);

	/**
	 * 获取某个报价单下所有的数据.供下拉框选择
	 * @param id
	 * @return
	 */
	public List<ProjectQuotationDirectLaborCost> toSelector(Integer id);
	
}
