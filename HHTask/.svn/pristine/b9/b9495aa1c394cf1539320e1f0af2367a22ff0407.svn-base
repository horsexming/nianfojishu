package com.task.Server;

import java.util.List;

import com.task.entity.Project;
import com.task.entity.ProjectQuotation;
import com.task.entity.ProjectQuotationList;

/**
 * 询价包
 * @author 马凯
 *
 */
public interface ProjectQuotationService {
	
	/**
	 * 添加
	 * @param p
	 */
	public void add(ProjectQuotation p);
	
	/**
	 * 查
	 * @return
	 */
	public ProjectQuotation getRoot(ProjectQuotation p);
	public ProjectQuotation get(ProjectQuotation p);
	
	
	/**
	 * 是否可以关闭项目建议书
	 * @param p
	 * @return
	 */
	public boolean isClosed(ProjectQuotation p);

	public ProjectQuotation update(ProjectQuotation quotation) ;

	public void updateOther(ProjectQuotation quotation);

	public List<ProjectQuotation> get(ProjectQuotationList quotationList);

	public void addChild(ProjectQuotation quotation);

	public Project getProject(ProjectQuotationList root);
	public Project getProject(ProjectQuotation root);

}
