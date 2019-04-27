package com.task.ServerImpl;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.ProjectRecordServer;
import com.task.entity.ProjectLogin;
import com.task.entity.ProjectRecord;
import com.task.entity.Users;

/***
 * 项目登录记录Server层实现类
 * 
 * @author 刘培
 * 
 */
public class ProjectRecordServerImpl implements ProjectRecordServer {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 添加项目登录记录
	public boolean addProRecord(ProjectRecord proRecord) {
		if (proRecord != null) {
			proRecord.setLoginField(proRecord.getLoginField().replaceAll("，",
					","));
			proRecord.setLoginFieldName(proRecord.getLoginFieldName().replace(
					"，", ","));
			return totalDao.save(proRecord);
		}
		return false;
	}

	// 查询所有登录网站记录信息(分页、条件查询)
	@SuppressWarnings("unchecked")
	public Object[] findAllProRecord(ProjectRecord proRecord, int pageNo,
			int pageSize) {
		if ((Object) pageNo != null && (Object) pageSize != null) {
			if (proRecord == null) {
				proRecord = new ProjectRecord();
			}
			String hql = totalDao.criteriaQueries(proRecord, null, null);
			List list = totalDao.findAllByPage(hql, pageNo, pageSize);
			int count = totalDao.getCount(hql);
			Object[] o = { list, count };
			return o;
		}
		return null;
	}

	// 查询项目名称是否重复
	public ProjectRecord findPrByProjectName(String projectName) {
		if (projectName != null && projectName.length() > 0) {
			String hql = "from ProjectRecord where projectName=?";
			return (ProjectRecord) totalDao.getObjectByCondition(hql, projectName);
		}
		return null;
	}

	// 删除登录网站
	public boolean delProRecord(ProjectRecord proRecord) {
		if (proRecord != null) {
			return totalDao.delete(proRecord);
		}
		return false;
	}

	// 根据id查询登录网站
	public ProjectRecord findProRecordById(Integer id) {
		if (id != null && id > 0) {
			return (ProjectRecord) totalDao.getObjectById(ProjectRecord.class, id);
		}
		return null;
	}

	// 修改登录网站
	public boolean updateProRecord(ProjectRecord proRecord) {
		if (proRecord != null) {
			ProjectRecord oldProRecord=(ProjectRecord) totalDao.getObjectById(ProjectRecord.class, proRecord.getId());
			proRecord.setProjectLogin(oldProRecord.getProjectLogin());
			return totalDao.update(proRecord);
		}
		return false;
	}

	// 查询所有的登录网站信息
	@SuppressWarnings("unchecked")
	public List<ProjectRecord> findAllProRecord() {
		String hql = "from ProjectRecord";
		return totalDao.query(hql);
	}

	// 通过登录网站以及登录用户信息查询登录信息
	public ProjectLogin findProjectLogin(Integer userId, ProjectRecord proRecord) {
		if (userId != null && userId > 0 && proRecord != null) {
			String hql = "from ProjectLogin p where p.projectRecord.id=? and userId=?";
			return (ProjectLogin) totalDao.getObjectByCondition(hql, proRecord.getId(),
					userId);
		}
		return null;
	}

	// 添加网站登录信息
	public boolean addProjectLogin(ProjectLogin projectLogin) {
		Users user = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);
		if (projectLogin != null && user != null) {
			projectLogin.setUserId(user.getId());
			projectLogin.setCode(user.getCode());
			projectLogin.setCardId(user.getCardId());
			return totalDao.save(projectLogin);
		}
		return false;
	}

	// 查询用户已绑定网站登录信息
	@SuppressWarnings("unchecked")
	public List<ProjectLogin> findProjectLoginByUser(Users user) {
		if (user != null) {
			String hql = "from ProjectLogin where userId=?";
			return totalDao.query(hql, user.getId());
		}
		return null;
	}

	// 删除用户已绑定网站登录信息
	public boolean delProjectLogin(ProjectLogin projectLogin) {
		if (projectLogin != null) {
			return totalDao.delete(projectLogin);
		}
		return false;
	}

	// 修改用户已绑定网站登录信息
	public boolean updateProjectLogin(ProjectLogin projectLogin) {
		if (projectLogin != null) {
			return totalDao.update(projectLogin);
		}
		return false;
	}

	// 根据ID查询登录信息
	public ProjectLogin findProjectLoginById(Integer id) {
		if (id != null && id > 0) {
			return (ProjectLogin) totalDao.getObjectById(ProjectLogin.class, id);
		}
		return null;
	}

}
