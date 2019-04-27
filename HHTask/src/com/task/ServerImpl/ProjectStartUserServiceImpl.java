package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.ProjectStartUserService;
import com.task.entity.ProjectStart;
import com.task.entity.ProjectStartUser;

public class ProjectStartUserServiceImpl implements ProjectStartUserService {
	private TotalDao totalDao;

	@Override
	public void add(ProjectStartUser u) {
		//检测是否添加过该人
		String hql = "select count(*) from ProjectStartUser u where u.userId = ? and u.root.id = ?";
		if(totalDao.count(hql, new Object[]{u.getUserId(), u.getRoot().getId()}) > 0) {
			throw new RuntimeException("已添加过该人员");
		}
		
		//检测是否有项目负责人
		String hql1 = "select count(*) from ProjectStartUser u where u.pGroup = ? and u.root.id = ?";
		if(totalDao.count(hql1, new Object[]{"项目负责人", u.getRoot().getId()}) > 0 && (u.getpGroup().equals("项目负责人"))){
			throw new RuntimeException("已经有项目负责人,项目负责人只能有一个.");
		}
		totalDao.save(u);
	}

	@Override
	public ProjectStartUser get(ProjectStartUser p) {
		return (ProjectStartUser) totalDao.getObjectById(p.getClass(), p.getId());
	}

	@Override
	public Object[] getByStart(ProjectStartUser p, int pageNo, int pageSize) {
		String hql = "from ProjectStartUser p where p.root.id = ?";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize, p.getRoot().getId());
		int count = totalDao.getCount(hql,p.getRoot().getId());
		Object[] o = { list, count };
		return o;
	}

	@Override
	public void update(ProjectStartUser u) {
//		ProjectStartUser p1 = (ProjectStartUser) totalDao.getObjectById(p.getClass(), p.getId());
//		BeanUtils.copyProperties(p, p1, new String[]{"id", "root", "userId"});
//		if(p1.getpGroup().equals("项目负责人")){
//			String hql = "from ProjectStartUser where id != ? and pGroup = ?";
//			int k = totalDao.getCount(hql, p1.getId(), p1.getpGroup());
//			if(k > 0){
//				throw new RuntimeException("项目负责人超过一个!");
//			}
//		}
//		totalDao.update(p1);
		
		//检测是否添加过该人
		String hql = "select count(*) from ProjectStartUser u where u.userId = ? and u.root.id = ?";
		if(totalDao.count(hql, new Object[]{u.getUserId(), u.getRoot().getId()}) > 0) {
			throw new RuntimeException("已添加过该人员");
		}
		
		//检测是否有项目负责人
		String hql1 = "select count(*) from ProjectStartUser u where u.pGroup = ? and u.root.id = ?";
		if(totalDao.count(hql1, new Object[]{"项目负责人", u.getRoot().getId()}) > 0 && (u.getpGroup().equals("项目负责人"))){
			throw new RuntimeException("已经有项目负责人,项目负责人只能有一个.");
		}
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public int getCount(ProjectStart projectStart) {
		String hql = "from ProjectStartUser p where p.root.id = ? ";
		return totalDao.getCount(hql, projectStart.getId());
	}

	@Override
	public ProjectStartUser getBoss(ProjectStart p) {
		String hql = "from ProjectStartUser where root.id = ? and pGroup = ?";
		return (ProjectStartUser) totalDao.getObjectByCondition(hql, p.getRoot().getId(), "项目负责人");
	}

	@Override
	public void delete(ProjectStartUser p) {
		totalDao.delete(p);
	}

}
