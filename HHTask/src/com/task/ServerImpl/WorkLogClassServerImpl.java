package com.task.ServerImpl;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.WorkLogClassServer;
import com.task.entity.Users;
import com.task.entity.WorkLogClass;

public class WorkLogClassServerImpl implements WorkLogClassServer {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 添加日志类别
	public boolean addWorkLogClass(WorkLogClass workLogClass) {
		if (workLogClass != null) {
			Users user = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);
			workLogClass.setCode(user.getCode());// 工号
			workLogClass.setCardId(user.getCardId());// 卡号
			workLogClass.setAddDateTime(totalDao
					.getDateTime("yyyy-MM-dd HH:mm:ss"));// 添加时间
			workLogClass.setUser(user);// 所属用户
			workLogClass.setRemarks(workLogClass.getRemarks());//备注
			
			
			return totalDao.save(workLogClass);
		}
		return false;
	}

	// 查询个人的类别
	public List<WorkLogClass> findPersonWorkLogClass() {
		Users user = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);
		String hql = "from WorkLogClass w where w.user.id=?";
		return totalDao.query(hql, user.getId());
	}

	// 查询个人日志类别下是否存在该类别
	public WorkLogClass findWlcByClassName(String className) {
		if (className != null && className.length() > 0) {
			Users user = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);
			String hql = "from WorkLogClass where code=? and cardId=? and name=?";
			List list = totalDao.query(hql, user.getCode(), user.getCardId(),
					className);
			if (list != null && list.size() > 0) {
				return (WorkLogClass) list.get(0);
			}
		}
		return null;
	}

}
