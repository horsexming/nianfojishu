package com.task.ServerImpl;

import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.ProjectProposalService;
import com.task.entity.Project;
import com.task.entity.ProjectProposal;
import com.task.entity.ProjectProposalFlow;

public class ProjectProposalServiceImpl implements ProjectProposalService {
	private TotalDao totalDao;

	@Override
	public void add(ProjectProposal pp) {
		Project p = (Project) totalDao.getObjectById(Project.class, pp.getRoot().getId());
		p.setStaring("项目建议书审核中");
		totalDao.save(pp);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public ProjectProposal list(ProjectProposal proposal) {
		String hql = "from ProjectProposal p where p.root.id = ?";
		return (ProjectProposal) totalDao.getObjectByCondition(hql, proposal.getRoot().getId());
	}

	@Override
	public boolean isLook(ProjectProposal proposal, Integer id) {
		String hql = "from ProjectProposal p where p.id = ?";
		proposal = (ProjectProposal) totalDao.getObjectByCondition(hql, proposal.getId());
		Set<ProjectProposalFlow> set = proposal.getCheck();
		for (ProjectProposalFlow f: set) {
			if(f.getName().equals(id+"")){
				return true;
			}
		}  
		return false;
	}

	@Override
	public void getUser(ProjectProposal proposal) {
		Set<ProjectProposalFlow> set = proposal.getCheck();
		for (ProjectProposalFlow f: set) {
			String s = (String) totalDao.getObjectByCondition("select name from Users where id = ?",Integer.parseInt(f.getName()));
			f.setUsername(s);
		} 
	}

	@Override
	public ProjectProposal get(ProjectProposal proposal) {
		return (ProjectProposal) totalDao.getObjectByCondition("from ProjectProposal where root.id = ?", proposal.getRoot().getId());
	}

	@Override
	public ProjectProposal get(Project root) {
		return (ProjectProposal) totalDao.getObjectByCondition("from ProjectProposal where root.id = ?", root.getId());
	}

	@Override
	public boolean isCheck(ProjectProposal projectProposal) {
		ProjectProposal p = (ProjectProposal) totalDao.getObjectById(ProjectProposal.class, projectProposal.getId());
		for (ProjectProposalFlow flow : p.getCheck()) {
			if(flow.isChecks()){
				return true;
			}
		}
		return false;
	}

	@Override
	public void update(ProjectProposal proposal) {
		ProjectProposal p = (ProjectProposal) totalDao.getObjectById(ProjectProposal.class, proposal.getId());
		BeanUtils.copyProperties(proposal, p, new String[]{"id", "root"});
	}

}
