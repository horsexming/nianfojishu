package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.ProjectAuthService;
import com.task.entity.ProjectAuth;

public class ProjectAuthServiceImpl implements ProjectAuthService {
	private TotalDao totalDao;

	@Override
	public void add(ProjectAuth auth) {
		String hql = "from ProjectAuth where root.id = ? and user.id = ?";
		ProjectAuth a = (ProjectAuth) totalDao.getObjectByCondition(hql, auth.getRoot().getId(), auth.getUser().getId());
		if(a != null){
			totalDao.delete(a);
		}
		totalDao.save(auth);
	}

	@Override
	public void addGlobeAdmin(ProjectAuth auth) {
		auth.setRoot(null);
		auth.setType("admin");
		totalDao.save(auth);
	}

	@Override
	public List<ProjectAuth> listGlobeAdmin() {
		String hql = "from ProjectAuth where root = null and type='admin'";
		return totalDao.find(hql);
	}


	@Override
	public void delete(ProjectAuth auth) {
		totalDao.delete(auth);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public ProjectAuth get(Integer projectId, Integer userId) {
		String hql = "from ProjectAuth where root.id = ? and user.id=?";
		return (ProjectAuth) totalDao.getObjectByCondition(hql, projectId, userId);
	}

	@Override
	public boolean isLook(Integer projectId, Integer userId ,String authType) {
		boolean b = false;
		String hql1 = "from ProjectAuth where type = ? and user.id= ? ";
		ProjectAuth auth =  (ProjectAuth) totalDao.getObjectByCondition(hql1, "admin", userId);
		if(auth != null){
			return true;
		}

		String hql = "from ProjectAuth where root.id = ? and user.id= ? ";
		auth =  (ProjectAuth) totalDao.getObjectByCondition(hql, projectId, userId);
		if(auth == null){
			return false;
		}
		if(auth.getType() == null){
			return false;
		}
		for (String s : auth.getType().split(",")) {
			if(s.trim().equals(authType)){
				return true;
			}
		}
		return b;
	}
	
}
