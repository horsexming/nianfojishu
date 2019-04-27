package com.task.ServerImpl;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.TeammembersServer;
import com.task.entity.Teammembers;
import com.task.entity.Users;

public class TeammembersServerImpl implements TeammembersServer {

	private TotalDao tatalDao;

	public TotalDao getTatalDao() {
		return tatalDao;
	}

	public void setTatalDao(TotalDao tatalDao) {
		this.tatalDao = tatalDao;
	}

	// 添加班组成员
	public boolean saveTeammembers(Teammembers teammembers) {
		if (teammembers != null) {
			Users user = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);// 获得登录用户
			Teammembers oldTteammembers = this.findTeammembersBYAddUser(
					teammembers.getTeammembersmembernumber(), user.getCode());// 查询是否已经存在该下属成员
			if (oldTteammembers == null) {
				teammembers.setAddUserId(user.getId());// 添加人的id
				teammembers.setAddUserCode(user.getCode());// 添加人的工号
				teammembers.setTeammembersteam(user.getDuty());// 班组
				teammembers.setTeammembersdatatime(tatalDao
						.getDateTime("yyyy-MM-dd HH:mm:ss"));// 时间
				return this.tatalDao.save(teammembers);
			}
		}
		return false;
	}

	// 根据ID查询出班组成员
	public Teammembers findByID(int id) {
		if (id > 0) {
			return (Teammembers) this.tatalDao.getObjectById(Teammembers.class, id);
		}
		return null;
	}

	// 删除班组成员
	public boolean deleteTeammembers(Teammembers teammembers) {
		if (teammembers != null) {
			return this.tatalDao.delete(teammembers);
		}
		return false;
	}

	// 查询出班组成员
	public List findAll(String dept, int pageNo, int pageSize) {
		if (dept != null) {
			String hql = "from Teammembers where teammembersteam='" + dept
					+ "'";
			return this.tatalDao.findAllByPage(hql, pageNo, pageSize);
		}
		return null;
	}

	// 查询出班组成员的总数
	public int getcount(String dept) {
		if (dept != null) {
			String hql = "from Teammembers where teammembersteam='" + dept
					+ "'";
			return this.tatalDao.getCount(hql);
		}
		return 0;
	}

	// 根据工号查询出 卡号和姓名
	public List findmembernumber(String membernumber) {
		if (membernumber != null) {
			String hql = "from Users where code='" + membernumber + "'";
			return this.tatalDao.query(hql);
		}
		return null;
	}

	// 根据工号查询出班组成员是否存在
	public List findtemembernumberteammembers(String gonghao) {
		if (gonghao != null) {
			String hql = "from Teammembers where teammembersmembernumber='"
					+ gonghao + "'";
			return this.tatalDao.query(hql);
		}
		return null;
	}

	/***
	 * 根据待添加人员的工号以及添加人的工号查询人员信息
	 * 
	 * @param teammembersmembernumber
	 *            待添加人员的工号
	 * @param addUserCode
	 *            添加人员的工号
	 * @return Teammembers
	 */
	public Teammembers findTeammembersBYAddUser(String teammembersmembernumber,
			String addUserCode) {
		if (teammembersmembernumber != null
				&& teammembersmembernumber.length() > 0) {
			String hql = "from Teammembers where teammembersmembernumber=? ";
			if (addUserCode != null && addUserCode.length() > 0) {
				hql += " and addUserCode=?";
				return (Teammembers) this.tatalDao.getObjectByCondition(hql,
						teammembersmembernumber, addUserCode);
			}
			return (Teammembers) this.tatalDao.getObjectByCondition(hql,
					teammembersmembernumber);
		}
		return null;
	}

	/***
	 * 根据待添加人员的工号查出该人员所有的下属成员
	 * 
	 * @param addUserId
	 *            添加人员的id
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List findTeammembersBYAddUser(int addUserId, int pageNo, int pageSize) {
		if ((Object) addUserId != null) {
			String hql = "from Teammembers where  addUserId=?";
			return tatalDao.findAllByPage(hql, pageNo, pageSize, addUserId);
		}
		return null;
	}

	/***
	 * 根据待添加人员的工号查成员的总数
	 * 
	 * @param addUserId
	 *            添加人员的id
	 * @return int
	 */
	public int getcountBYAddUser(int addUserId) {
		if ((Object) addUserId != null) {
			String hql = "from Teammembers where  addUserId=?";
			return this.tatalDao.getCount(hql, addUserId);
		}
		return 0;
	}
}
