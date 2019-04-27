package com.task.ServerImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.ProjectProposalFlowService;
import com.task.comparator.ProjectProposalFlowComparator;
import com.task.entity.ProjectProposal;
import com.task.entity.ProjectProposalFlow;

public class ProjectProposalFlowServiceImpl implements ProjectProposalFlowService {
	private TotalDao totalDao;


	@SuppressWarnings("unchecked")
	public List<ProjectProposalFlow> list(Integer id) {
		String hql = "from ProjectProposalFlow as f  where f.projectProposal.id = ? order by f.level";
		return totalDao.query(hql, id);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public void update(ProjectProposalFlow flow) {
		ProjectProposalFlow temp = (ProjectProposalFlow) totalDao.getObjectById(flow.getClass(), flow.getId());
		BeanUtils.copyProperties(flow, temp, new String[]{"level","projectProposal","checks","name", "id"});
		temp.setChecks(true);
		totalDao.update(temp);
	}

	
	public ProjectProposalFlow get(int id) {
		return (ProjectProposalFlow) totalDao.getObjectById(ProjectProposalFlow.class, id);
	}

	@Override
	public ProjectProposalFlow get(Integer integer, String name) {
		String hql = "from ProjectProposalFlow where name = ? and projectProposal.id = ?";
		return (ProjectProposalFlow) totalDao.getObjectByCondition(hql, name, integer);
	}
	
	@Override
	public boolean isLook(Integer id, String deptNo) {
		ProjectProposalFlow flow = this.get(id, deptNo);
		String hql = "from ProjectProposalFlow where projectProposal.id = ? and level < ? and checks = false";
		List<ProjectProposalFlow> list1 = totalDao.query(hql, id,flow.getLevel());
		if(list1 == null || list1.size() == 0){
			return true;
		}
		return false;
	}

	@Override
	public Set<ProjectProposalFlow> getFlows(List<String> nodes, Map<String, ProjectProposalFlow> flows) {
		Set<ProjectProposalFlow> set = new HashSet<ProjectProposalFlow>();
		for (String s : nodes) {
			set.add(flows.get(s));
		}
		return set;
	}

	@Override
	public List<ProjectProposalFlow> getFlowsByProjectProposal(ProjectProposal projectProposal) {
		String hql = "from ProjectProposalFlow where projectProposal.id = :id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", projectProposal.getId());
		return totalDao.find(hql, map);
	}

	@Override
	public Set<ProjectProposalFlow> getFlow(ProjectProposal proposal) {
		ProjectProposal p = (ProjectProposal) totalDao.getObjectById(proposal.getClass(), proposal.getId());
		Set<ProjectProposalFlow> flows = p.getCheck();
		Set<ProjectProposalFlow> set = new TreeSet<ProjectProposalFlow>(new ProjectProposalFlowComparator());
		set.addAll(flows);
		return set;
	}
	
	@Override
	public Set<ProjectProposalFlow> getUser(Set<ProjectProposalFlow> set) {
		for (ProjectProposalFlow f: set) {
			String s = (String) totalDao.getObjectByCondition("select name from Users where id = ?",Integer.parseInt(f.getName()));
			f.setUsername(s);
		} 
		return set;
	}
	
	
}
