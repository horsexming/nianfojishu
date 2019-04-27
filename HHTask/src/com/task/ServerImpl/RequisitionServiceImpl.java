package com.task.ServerImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.RequisitionService;
import com.task.entity.Requisition;
import com.task.entity.Users;

public class RequisitionServiceImpl implements RequisitionService {
	private TotalDao totalDao;

	public boolean add(Requisition requisition) {
		if (requisition != null) {
			String writeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(new Date());
			Users user = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);

			int id = user.getId();
			requisition.setUserid(id);
			requisition.setManagerdate(writeDate);
			requisition.setManager("部门经理审批");
			if (requisition.getDepartment().equals("采购")) {
				requisition.setManager("企划副总审批");
			}
			return totalDao.save(requisition);
		}
		return false;
	}

	public boolean delete(Requisition requisition) {
		return totalDao.delete(requisition);

	}

	public boolean update(Requisition requisition) {

		return totalDao.update(requisition);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public Requisition findAssetById(int id) {

		return (Requisition) this.totalDao.getObjectById(Requisition.class, id);
	}

	// 查询所有审核/打回信息
	public List findAll(String manager) {
		if (manager != null && manager.length() > 0) {
			Users user = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);
			String hql = "from Requisition where manager like '%" + manager
					+ "%' and userid=?";
			return totalDao.query(hql, user.getId());
		}
		return null;
	}

	// 查询所有+条件查询(分页)+审批
	public Object[] findAll(Requisition requisition, int pageNo, int pageSize,
			String status) {

		if (requisition == null) {
			requisition = new Requisition();
		}
		String message = "";
		if (status != null) {
			if ("jingli".equals(status)) {
				Users user = (Users) ActionContext.getContext().getSession()
						.get(TotalDao.users);
				String userDept = user.getDept();
				requisition.setDepartment(userDept);
				requisition.setManager("经理审批");
			} else if ("daifuzong".equals(status)) {
				requisition.setManager("生产副总审批");
			} else if ("zhongfuzong".equals(status)) {
				requisition.setManager("企划副总审批");
			} else if ("zongjingli".equals(status)) {
				requisition.setManager("总经理审批");
			} else if ("tongyi".equals(status)) {
				requisition.setManager("同意");
			} else if ("zuzxzong".equals(status)) {
				requisition.setManager("任务执行中");
			} else if ("zuzxzong".equals(status)) {
				requisition.setManager("完成");
			} else {
				message = "findById";
			}
		} else {
			message = "findById";
		}
		String hql = totalDao.criteriaQueries(requisition, null, null);
		if ("findById".equals(message)) {
			Users user = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);
			if (hql.indexOf(" where ") > 0) {
				hql += " and userid=" + user.getId();
			} else {
				hql += " where userid=" + user.getId();
			}
		}

		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
}
