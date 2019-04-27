package com.task.Server;

import com.task.entity.ProjectQuotationSubContract;

/**
 * 项目_外购外协
 * @author 马凯
 *
 */
public interface ProjectQuotationSubContractService {

	/**
	 * 添加对象
	 * @param viewObj
	 */
	public void add(ProjectQuotationSubContract viewObj, int quotationId);
	
	/**
	 * 修改对象
	 * @param viewObj
	 */
	public void update(ProjectQuotationSubContract viewObj);
	
	/**
	 * 删除对象
	 * @param viewObj
	 */
	public void delete(ProjectQuotationSubContract viewObj);
	
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
	public ProjectQuotationSubContract get(ProjectQuotationSubContract viewObj);
	
}
