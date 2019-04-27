package com.task.Server;

import com.task.entity.Project;
import com.task.entity.ProjectProposal;

public interface ProjectProposalService {
	
	public void add(ProjectProposal pp);
	
	public ProjectProposal list(ProjectProposal proposal);

	public boolean isLook(ProjectProposal proposal, Integer id);

	public void getUser(ProjectProposal proposal);

	public ProjectProposal get(ProjectProposal proposal);

	public ProjectProposal get(Project root);

	public boolean isCheck(ProjectProposal projectProposal);

	public void update(ProjectProposal proposal);

}
