package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.ProjectUserService;
import com.task.entity.Project;
import com.task.entity.ProjectAuth;
import com.task.entity.ProjectUser;
import com.task.entity.Users;

public class ProjectUserServiceImpl implements ProjectUserService {

	private TotalDao totalDao;
	
	@Override
	public void add(ProjectUser user) {
		check(user);
		totalDao.save(user);
	}

	private void check(ProjectUser user) {
		String hql = "select count(*) from ProjectUser u where u.root.id = ? and u.user.id = ? ";
		long count = totalDao.count(hql, new Object[]{user.getRoot().getId(), user.getUser().getId()});
		if(count > 0){
			throw new RuntimeException("该项目已经添加过该人员!");
		}
		if(user.getpGroup().trim().equals("项目负责人")) {
			String hql1 = "select count(*) from ProjectUser u where u.root.id = ? and u.pGroup = ?";
			long count1 = totalDao.count(hql1, new Object[]{ user.getRoot().getId(), user.getpGroup()});
			if(count1 > 0){
				throw new RuntimeException("该项目已经有一位负责人!");
			}
		}
	}

	@Override
	public void delete(ProjectUser user) {
		totalDao.delete(user);
	}

	@Override
	public List<ProjectUser> list(Project project) {
		String hql = "from ProjectUser where root.id = :rootid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rootid", project.getId());
		return totalDao.find(hql, params);
	}

	@Override
	public void update(ProjectUser user) {
		ProjectUser u = (ProjectUser) totalDao.getObjectById(ProjectUser.class, user.getId());
		BeanUtils.copyProperties(user, u, new String[]{"id","root"});
		if(user.getpGroup().trim().equals("项目负责人")) {
			String hql1 = "select count(*) from ProjectUser u where u.root.id = ? and u.pGroup = ? and id != ?";
			long count1 = totalDao.count(hql1, new Object[]{ user.getRoot().getId(), user.getpGroup(), user.getId()});
			if(count1 > 0){
				throw new RuntimeException("该项目已经有一位负责人!");
			}
		}
		totalDao.update(user);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public ProjectUser get(ProjectUser user) {
		return (ProjectUser) totalDao.getObjectById(user.getClass(), user.getId());
	}

	@Override
	public List<Users> toSelector(Integer projectId) {
		String hql = "select user.id, user.name from ProjectUser where root.id = :rootid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rootid", projectId);
		List<Object[]> a = totalDao.find(hql, params);
		List<Users> list = new ArrayList<Users>();
		for (int i = 0; i < a.size(); i++) {
			Users u = new Users();
			u.setId((Integer)a.get(i)[0]);
			u.setName(a.get(i)[1].toString());
			list.add(u);
		}
		String hql1 = "from ProjectAuth where type='admin'";
		List<ProjectAuth> auths = totalDao.find(hql1);
		for (ProjectAuth projectAuth : auths) {
			Users u = new Users();
			u.setId(projectAuth.getUser().getId());
			u.setName(projectAuth.getUser().getName());
			list.add(u);
		}
		return list;
	}

}
