package com.task.Server;

import java.util.List;

import com.task.entity.Project;
import com.task.entity.ProjectQuotationList;

public interface ProjectQuotationListService {
	
	public void add(ProjectQuotationList list);
	
	public void update(ProjectQuotationList list);
	
	public void delete(ProjectQuotationList list);
	
	public ProjectQuotationList get(ProjectQuotationList list);
	
	public ProjectQuotationList get(Project project);

	public boolean isClosedByProposal(Project project);

	public ProjectQuotationList updateAttr(ProjectQuotationList quotationList);

	/**
	 * 输入物流价格
	 * @param quotationList
	 */
	public void updateWl(ProjectQuotationList quotationList);

	/**
	 * 输入利润
	 * @param quotationList
	 */
	public void updateOi(ProjectQuotationList quotationList);

	public void addDirectory(ProjectQuotationList quotationList);

	public List<ProjectQuotationList> getChildren(ProjectQuotationList quotationList);

	public ProjectQuotationList showAll(ProjectQuotationList quotationList);
	
	public Project getProject(ProjectQuotationList list);

}
