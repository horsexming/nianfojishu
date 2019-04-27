package com.task.ServerImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.RepairService;
import com.task.entity.Repair;
import com.task.entity.Responsibilities;
import com.task.entity.Users;
import com.task.entity.gzbj.Gzstore;
import com.task.entity.repair.RepairType;
import com.task.util.Util;

public class RepairServiceImpl implements RepairService {
	private TotalDao totalDao;

	public boolean add(Repair repair) {
		if (repair != null) {
			if (repair.getPersonalnominee() != null
					&& repair.getPersonalnominee().length() > 0) {
				repair.setStatus("待确定");
			} else {
				repair.setStatus("待指派");
			}
			repair.setRepairtime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
					.format(new Date()));// 保修时间
			Users user = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);
			repair.setUserid(user.getId());// 添加保修人员id
			boolean bool = totalDao.save(repair);
			if (bool) {
				// 如果报修成功，则处理提醒消息
				if (bool) {
					if (repair.getPersonalnominee() != null
							&& repair.getPersonalnominee().length() > 0) {
						String hql = "from Responsibilities where repairname=?";
						Responsibilities rb = (Responsibilities) totalDao
								.getObjectByCondition(hql, repair
										.getPersonalnominee());
						if (rb != null) {
							// 发送给指定人员
							AlertMessagesServerImpl
									.addAlertMessages("信息维修人员管理  ", user
											.getDept()
											+ "部门有报修设备,请及时维修设备！", "1", rb
											.getEmployeenumber(), rb.getPhone());
						}
					} else {
						// 发送给指派人员
						AlertMessagesServerImpl.addAlertMessages("信息指派人员管理 ",
								user.getDept() + "部门有报修设备，请及时指派维修人员维修！", "1");
					}
				}
			}
		} else {
			return false;
		}
		return false;

	}

	@Override
	public int findAlls(String date1, String date2) {
		if (date1 == null && date2 == null) {
			String hql = "from Repair ";
			return totalDao.getCount(hql);
		} else {
			String hql = "from Repair where repairtime>=? and repairtime<=?";
			try {
				return totalDao.getCount(hql, date1, date2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
	}

	@Override
	public int findAllss(String date1, String date2) {
		if (date1 == null && date2 == null) {
			String hql = "from Repair where status in ('修复完成','修复确认')";
			return totalDao.getCount(hql);
		} else {
			String hql = "from Repair where status in ('修复完成','修复确认') and repairtime>=? and repairtime<=?";
			try {
				return totalDao.getCount(hql, date1, date2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
	}

	public boolean delete(Repair repair) {
		return totalDao.delete(repair);
	}

	public Repair findAssetById(int id) {

		return (Repair) this.totalDao.getObjectById(Repair.class, id);
	}

	public boolean update(Repair repair) {
		repair.setRepairtime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
				.format(new Date()));
		return totalDao.update(repair);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public Object[] findAll(Repair repair, int pageNo, int pageSize,
			String status) {
		if (repair == null) {
			repair = new Repair();
		}
		String message = "";
		Users user = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);
		if (status != null) {
			if ("daiqueding".equals(status)) {
				repair.setStatus("待确定");
				repair.setPersonalnominee(user.getName());
			} else if ("daizhipai".equals(status)) {
				repair.setStatus("待指派");
			} else if ("weixiuzhong".equals(status)) {
				repair.setStatus("维修中");
			} else if ("weixiuzhong".equals(status)) {
				repair.setStatus("修复完成");
			} else if ("findAll".equals(status)) {
				repair.setStatus("修复完成");
			} else if ("findByCon".equals(status)) {
			} else {
				message = "findById";
			}
		} else {
			message = "findById";
		}
		String hql = totalDao.criteriaQueries(repair, null, null);
		if ("findById".equals(message)) {
			if (hql.indexOf(" where ") > 0) {
				hql += " and userid=" + user.getId();
			} else {
				hql += " where userid=" + user.getId();
			}
		}
		hql += " order by repairtime desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public List findAll(String status) {
		if (status != null && status.length() > 0) {
			Users user = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);
			String hql = "from Repair where status like '%" + status
					+ "%' and userid=? order by repairtime desc";
			return totalDao.query(hql, user.getId());
		}
		return null;
	}

	@Override
	public List findAllByStatus(String status) {
		if (status != null && status.length() > 0) {
			String hql = "from Repair where status like '%" + status
					+ "%' order by repairtime desc ";
			return totalDao.query(hql);
		}
		return null;
	}

	@Override
	public String selectpeople(String category) {
		String hql = "from Responsibilities where repairresponsibilitiesl like '%"
				+ category + "%'";
		List list = totalDao.query(hql);
		if (list != null) {
			String message = "";
			for (int i = 0; i < list.size(); i++) {
				Responsibilities responsibilities = (Responsibilities) list
						.get(i);
				message += responsibilities.getRepairname() + "|";
			}
			return message;
		}

		return null;
	}

	@Override
	public List selectPeopleForZhipai(String category) {
		String hql = "from Responsibilities where repairresponsibilitiesl like '%"
				+ category + "%'";
		return totalDao.query(hql);
	}

	/*
	 * 
	 * 查询所有修理人信息(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.RepairService#findAllpop(com.task.entity.Responsibilities
	 * , int, int)
	 */
	@Override
	public Object[] findAllpop(Responsibilities responsibilities, int pageNo,
			int pageSize, String test) {
		if (responsibilities == null) {
			responsibilities = new Responsibilities();
		}
		Users loginUser = Util.getLoginUser();// 获得登陆用户
		String deptname = loginUser.getDept();
		String sql = "";
		if ("0".equals(test)) {
			sql = "select * from ta_xinxi_dutylist where 1=1";
		} else {
			sql = "select * from ta_xinxi_dutylist where 1=1 and repairdepartment like '%"
					+ deptname + "%'";// 原态sql语句repairname repaircategory
		}

		if (!"".equals(responsibilities.getRepairname())
				&& responsibilities.getRepairname() != null) {
			sql += " and repairname like '%" + responsibilities.getRepairname()
					+ "%'";
		}
		if (!"".equals(responsibilities.getRepaircategory())
				&& responsibilities.getRepaircategory() != null) {
			sql += " and repaircategory like '%"
					+ responsibilities.getRepaircategory() + "%'";
		}
		List list1 = totalDao.findBySql(sql, pageNo, pageSize);
		int count = totalDao.findBySql(sql).size();// 总行数
		Object[] o = { list1, count };
		return o;
	}

	/*
	 * 
	 * 校验工号和姓名是否对应(non-Javadoc)
	 * 
	 * @see com.task.Server.RepairService#findcadeandname(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Integer findcadeandname(String employeenumber, String repairname) {
		// TODO Auto-generated method stub
		String sql = "select count(*) as numb from users where code='"
				+ employeenumber + "' and name='" + repairname + "'";
		List<Map> list = totalDao.findBySql(sql);
		Map map = list.get(0);
		Integer i = Integer.parseInt(map.get("numb").toString());
		return i;
	}

	/*
	 * 
	 * 查询所有部门(non-Javadoc)
	 * 
	 * @see com.task.Server.RepairService#findDept()
	 */
	@Override
	public List<Map> findDept() {
		String sql = "select ta_dept from ta_deptNumber";
		List<Map> maps = totalDao.findBySql(sql);
		maps.get(0).get("ta_dept");
		return maps;
	}

	/*
	 * 保存修改人信息(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.RepairService#saveRepair(com.task.entity.Responsibilities
	 * )
	 */
	@Override
	public void saveRepair(Responsibilities responsibilities) {
		// TODO Auto-generated method stub
		this.totalDao.save(responsibilities);
	}

	/*
	 * 
	 * 删除报修人信息(non-Javadoc)
	 * 
	 * @see com.task.Server.RepairService#deleterepair(com.task.entity.Repair)
	 */
	@Override
	public boolean deleterepair(Integer id) {
		// TODO Auto-generated method stub
		Responsibilities responsibilities = new Responsibilities();
		responsibilities.setId(id);
		boolean bool = this.totalDao.delete(responsibilities);
		return bool;
	}

	/*
	 * 
	 * 根据编号查询(non-Javadoc)
	 * 
	 * @see com.task.Server.RepairService#findRepairpopById(java.lang.Integer)
	 */
	@Override
	public Responsibilities findRepairpopById(Integer delId) {
		Responsibilities responsibilities = (Responsibilities) this.totalDao
				.getObjectById(Responsibilities.class, delId);
		return responsibilities;
	}

	/*
	 * 
	 * 修改报修人信息(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.RepairService#updateRepair(com.task.entity.Responsibilities
	 * )
	 */
	@Override
	public void updateRepair(Responsibilities responsibilities) {
		this.totalDao.update(responsibilities);
	}

	/*
	 * 
	 * 查询所有保修类型信息(non-Javadoc)
	 * 
	 * @seecom.task.Server.RepairService#findRepairtype(com.task.entity.repair.
	 * RepairType, int, int)
	 */
	@Override
	public Object[] findRepairtype(RepairType repairType, int pageNo,
			int pageSize) {
		if (repairType == null) {
			repairType = new RepairType();
		}
		String sql = "select  * from ta_repairtype where 1=1";// 原态sql语句
		if (!"".equals(repairType.getDepartment())
				&& repairType.getDepartment() != null) {
			sql += " and department like '%" + repairType.getDepartment()
					+ "%'";
		}
		if (!"".equals(repairType.getCategory())
				&& repairType.getCategory() != null) {
			sql += " and category like '%" + repairType.getCategory() + "%'";
		}
		List list1 = totalDao.findBySql(sql, pageNo, pageSize);
		int count = totalDao.findBySql(sql).size();// 总行数
		Object[] o = { list1, count };
		return o;
	}

	/*
	 * 
	 * 添加报修类型(non-Javadoc)
	 * 
	 * @seecom.task.Server.RepairService#saveRepairtype(com.task.entity.repair.
	 * RepairType)
	 */
	@Override
	public void saveRepairtype(RepairType repairType) {
		// TODO Auto-generated method stub
		this.totalDao.save(repairType);

	}

	/*
	 * 
	 * 删除报修类型信息(non-Javadoc)
	 * 
	 * @see com.task.Server.RepairService#delRepairtype(java.lang.Integer)
	 */
	@Override
	public void delRepairtype(Integer delId) {
		// TODO Auto-generated method stub
		RepairType repairType = new RepairType();
		repairType.setId(delId);
		this.totalDao.delete(repairType);
	}

	/*
	 * 
	 * 根据编号查询(non-Javadoc)
	 * 
	 * @see com.task.Server.RepairService#findRepairtypeById(java.lang.Integer)
	 */
	@Override
	public RepairType findRepairtypeById(Integer delId) {
		RepairType repairType = (RepairType) this.totalDao.getObjectById(
				RepairType.class, delId);
		return repairType;
	}

	/*
	 * 
	 * 修改报修类型信息(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.RepairService#updateRepairtype(com.task.entity.repair
	 * .RepairType)
	 */
	@Override
	public void updateRepairtype(RepairType repairType) {
		// TODO Auto-generated method stub
		this.totalDao.update(repairType);
	}

	/*
	 * 
	 * 根据部门查类别(non-Javadoc)
	 * 
	 * @see com.task.Server.RepairService#findRepairByName(java.lang.String)
	 */
	@Override
	public List<Map> findRepairByName(String repairdepartment) {
		String sql = "select category from ta_repairtype where department like '%"+repairdepartment.trim()+ "%'";
		List<Map> maps = this.totalDao.findBySql(sql.trim());
		return maps;
	}

	/*
	 * 
	 * 根据类别查人员(non-Javadoc)
	 * 
	 * @see com.task.Server.RepairService#findRepairByName1(java.lang.String)
	 */
	@Override
	public List<Map> findRepairByName1(String category, String repairdepartment) {
		String sql = "select repairname from ta_xinxi_dutylist where repaircategory ='"
				+ category.trim()
				+ "' and repairdepartment='"
				+ repairdepartment.trim() + "'";
		List<Map> maps = this.totalDao.findBySql(sql.trim());
		return maps;
	}

	/*
	 * 
	 * 查询本部门的所有类别(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.RepairService#findRepairtype1(com.task.entity.repair.
	 * RepairType, int, int)
	 */
	@Override
	public Object[] findRepairtype1(RepairType repairType, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		if (repairType == null) {
			repairType = new RepairType();
		}
		Users loginUser = Util.getLoginUser();// 获得登陆用户
		String deptname = loginUser.getDept();
		String sql = "select  * from ta_repairtype where 1=1 and department='"
				+ deptname + "'";// 原态sql语句
		if (!"".equals(repairType.getDepartment())
				&& repairType.getDepartment() != null) {
			sql += " and department like '%" + repairType.getDepartment()
					+ "%'";
		}
		if (!"".equals(repairType.getCategory())
				&& repairType.getCategory() != null) {
			sql += " and category like '%" + repairType.getCategory() + "%'";
		}
		List list1 = totalDao.findBySql(sql, pageNo, pageSize);
		int count = totalDao.findBySql(sql).size();// 总行数
		Object[] o = { list1, count };
		return o;
	}

}
