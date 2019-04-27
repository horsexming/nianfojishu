package com.task.Server;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.task.entity.ProjectProposal;
import com.task.entity.ProjectProposalFlow;

public interface ProjectProposalFlowService {
	
	
	/**
	 * 根据建议书的ID和部门编号来查询是否能看
	 * @param id
	 * @param deptNo
	 * @return
	 */
	public boolean isLook(Integer id, String deptNo);
	
	/**
	 * 根据字符串拿到处理流程..
	 * @param nodes
	 * @param flows
	 * @return
	 */
	public Set<ProjectProposalFlow> getFlows(List<String> nodes, Map<String, ProjectProposalFlow> flows);

	/**
	 * 根据建议书拿到处理流..
	 * @param projectProposal
	 * @return
	 */
	public List<ProjectProposalFlow> getFlowsByProjectProposal(ProjectProposal projectProposal);
	
	/**
	 * 
	 * @param integer
	 * @param name
	 * @return
	 */
	public ProjectProposalFlow get(Integer integer, String name);

	public void update(ProjectProposalFlow flow);

	public Set<ProjectProposalFlow> getFlow(ProjectProposal proposal);

	public Set<ProjectProposalFlow> getUser(Set<ProjectProposalFlow> flows);
}
