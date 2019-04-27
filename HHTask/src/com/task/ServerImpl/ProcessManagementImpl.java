package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.ProcessManagementService;
import com.task.entity.Templateb;
import com.task.entity.Templatenode;

public class ProcessManagementImpl implements ProcessManagementService {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public Templateb findAssetById(int id) {

		return (Templateb) totalDao.getObjectById(Templateb.class, id);
	}

	public boolean add(Templateb templateb) {
		if (templateb != null) {
			return totalDao.save(templateb);
		}
		return false;
	}

	@Override
	public boolean delete(Templateb templateb) {
		if (templateb != null) {
			return totalDao.delete(templateb);
		}
		return false;
	}

	public boolean update(Templateb templateb) {

		return totalDao.update(templateb);
	}

	@Override
	public List selectGrouping(String status) {
		String hql = "from UsersGroup where status='审核'";
		return totalDao.query(hql);
	}

	@Override
	public Object[] findTemplatebByCondition(Templateb templateb, int pageNo,
			int pageSize) {
		if (templateb == null) {
			templateb = new Templateb();
		}
		String hql = totalDao.criteriaQueries(templateb, null, null);
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	@Override
	public boolean add(Templatenode templatenode) {
		if (templatenode != null) {
			return totalDao.save(templatenode);
		}
		return false;
	}

	/***
	 * 根据分组id查询对应成员
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List findPersonByGroupId(Integer id) {
		if (id == null || id > 1) {
			String hql = "from AssessPersonnel a where  a.usersGroup.id=? ";
			return totalDao.query(hql, id);
		}
		return null;
	}
}
