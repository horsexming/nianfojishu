package com.task.ServerImpl.pro;

import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.pro.ProServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.pro.Pro;
import com.task.entity.sop.ManualOrderPlanDetail;
import com.task.entity.sop.YcWaiGouProcrd;
import com.task.entity.system.CircuitRun;
import com.tast.entity.zhaobiao.Nianlilv;

public class ProServerImpl implements ProServer {
	private TotalDao totalDao;

	public List listPro() {
		String hql = "from Pro";
		return totalDao.query(hql);
	}

	public List finAllMarkIdForSetlectAll() {
		String hql = "select markId,selfCard from Procard where status!='完成' order by markId";
		List list = totalDao.query(hql);
		return list;
	}

	public List listKVP(String name) {//
		// String hql =
		// " from KVPAssess s where s.executeKVP.status='同意' and   s.executeKVP.improve_username  like '%"
		// + name+"%' ";
		// return totalDao.query(hql);
		// String hql = "from KVPAssess ";
		String sqlString = "select s.kvp_number,s.part_name  from ExecuteKVP e join e.kvpAssess s where  e.status='同意' and   e.improve_username  like '%"
				+ name + "%' ";
		List list = null;
		try {
			list = totalDao.query(sqlString);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public Object[] listpro(Pro pro, Integer cpage, Integer PageSize) {
		if (pro == null) {
			pro = new Pro();
		}
		String hql = totalDao.criteriaQueries(pro, null);
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/***
	 * 查询启动中的所有项目
	 * 
	 * @param pro
	 * @return
	 */
	@Override
	public List findCgPRo(Pro pro) {
		if (pro == null) {
			pro = new Pro();
		}
		String hql = "from Pro where status='启动'";
		return totalDao.query(hql);
	}

	/*
	 * 
	 * 
	 * //更新项目记录 public Object[] findAllPro(Map map, int pageNo, int pageSize) {
	 * String hql = "from Pro p where 1 = 1"; if(map!=null){
	 * 
	 * } hql+=" order by p.createDate asc"; List list =
	 * totalDao.findAllByPage(hql, pageNo, pageSize); int count =
	 * totalDao.getCount(hql); Object[] o = { list, count }; return o;
	 * }(non-Javadoc)
	 * 
	 * @see com.task.Server.pro.ProServer#addPro(com.task.entity.pro.Pro)
	 */
	// 添加项目记录
	public String addPro(Pro pro) {
		boolean result = totalDao.save(pro);
		if (result) {
			// 提交审批
			String processName = "项目立项申请";
			Integer epId = null;
			try {
				epId = CircuitRunServerImpl.createProcess(processName,
						Pro.class, pro.getId(), "epStatus", "id", "",
						"物料需求申请，请您审批", true);
				if (epId != null && epId > 0) {
					pro.setEpId(epId);
					CircuitRun circuitRun = (CircuitRun) totalDao.get(
							CircuitRun.class, epId);
					if ("同意".equals(circuitRun.getAllStatus())
							&& "审批完成".equals(circuitRun.getAuditStatus())) {
						pro.setEpStatus("同意");
					} else {
						pro.setEpStatus("未审批");
					}
					return totalDao.update(pro) + "";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "true";
		} else {
			return "false";
		}
	}

	// 删除项目记录
	public String deletePro(Pro pro) {
		boolean result = totalDao.delete(pro);
		if (result) {
			return "true";
		} else {
			return "false";
		}
	}

	// 更新项目记录
	public String updatePro(Pro pro) {
		boolean result = totalDao.update(pro);
		if (result) {
			return "true";
		} else {
			return "false";
		}
	}

	// 获得项目记录
	public Pro getProById(Integer id) {
		if (id != null) {
			return (Pro) totalDao.getObjectById(Pro.class, id);
		}
		return null;
	}

	// 更新项目记录
	public Object[] findAllPro(Map map, int pageNo, int pageSize) {
		String hql = "from Pro p where 1 = 1";
		if (map != null) {

		}
		hql += " order by p.createDate asc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
