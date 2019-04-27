package com.task.Server;

import com.task.entity.ProjectQuotationEquipmentDepreciation;

/**
 * 项目_设备折旧
 * @author 马凯
 *
 */
public interface ProjectQuotationEquipmentDepreciationService {

	/**
	 * 添加对象
	 * @param viewObj
	 */
	public void add(ProjectQuotationEquipmentDepreciation viewObj, int quotationId);
	
	/**
	 * 修改对象
	 * @param viewObj
	 */
	public void update(ProjectQuotationEquipmentDepreciation viewObj);
	
	/**
	 * 删除对象
	 * @param viewObj
	 */
	public void delete(ProjectQuotationEquipmentDepreciation viewObj);
	
	/**
	 * 根据建议书查询
	 * @param viewObj
	 * @return
	 */
	public Object[] getByQuotation(int quotationId, int pageNo, int pageSize) ;

	/**
	 * 获取一个
	 * @param viewObj
	 * @return
	 */
	public ProjectQuotationEquipmentDepreciation get(ProjectQuotationEquipmentDepreciation viewObj);
	
}
