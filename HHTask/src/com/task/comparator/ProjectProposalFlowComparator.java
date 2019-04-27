package com.task.comparator;

import java.util.Comparator;

import com.task.entity.ProjectProposalFlow;

public class ProjectProposalFlowComparator implements Comparator<ProjectProposalFlow> {

	@Override
	public int compare(ProjectProposalFlow o1, ProjectProposalFlow o2) {
		int i1 = o1.getLevel();
		int i2 = o2.getLevel();
		return i1 - i2;
	}


}
