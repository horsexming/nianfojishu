package com.task.ServerImpl.SingleCar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection.Request;

import com.task.Dao.TotalDao;
import com.task.Server.SingleCar.SingleCarServer;
import com.task.Server.sys.CircuitRunServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Card;
import com.task.entity.Users;
import com.task.entity.approval.Signature;
import com.task.entity.kvp.ExecuteKVP;
import com.task.entity.kvp.KVPAssess;
import com.task.entity.menjin.Visit;
import com.task.entity.payment.PaymentVoucher;
import com.task.entity.singlecar.SingleCar;
import com.task.entity.singlecar.SingleCarAll;
import com.task.entity.system.CircuitRun;
import com.task.entity.system.ExecutionNode;
import com.task.util.Util;

public class SingleCarServerImpl implements SingleCarServer {
	private TotalDao totalDao;
	private CircuitRunServer circuitRunServer;
	private String finishtime;
	private String pilotname;
	private List qianmingList;

	public String getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(String finishtime) {
		this.finishtime = finishtime;
	}

	public String getPilotname() {
		return pilotname;
	}

	public void setPilotname(String pilotname) {
		this.pilotname = pilotname;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public CircuitRunServer getCircuitRunServer() {
		return circuitRunServer;
	}

	public void setCircuitRunServer(CircuitRunServer circuitRunServer) {
		this.circuitRunServer = circuitRunServer;
	}

	public List getQianmingList() {
		return qianmingList;
	}

	public void setQianmingList(List qianmingList) {
		this.qianmingList = qianmingList;
	}

	/*
	 * 申请用户单(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.SingleCar.SingleCarServer#addSingleCar(com.task.entity
	 * .singlecar.SingleCar)
	 */
	@Override
	public boolean addSingleCar(SingleCar singleCar) {
		Users loginUser = Util.getLoginUser();// 登陆用户信息
		String createDate = Util.getDateTime("yyyy-MM-dd  HH:ss:mm");
		singleCar.setProcessingstatus("未处理");
		singleCar.setStatus("未审核");
		singleCar.setCreate_date(createDate);
		singleCar.setCar_usename(loginUser.getName());
		boolean bool = this.totalDao.save(singleCar);
		if (bool) {
			Integer epId;
			String processName = "";
			String dept = loginUser.getDept();
			if ("省内".equals(singleCar.getSinglecarType())) {
				processName = dept + "省内用车审核";
			} else {
				processName = dept + "省外用车审核";
			}
			try {
				epId = CircuitRunServerImpl.createProcess(processName,
						SingleCar.class, singleCar.getId(), "status", "id",
						"singleCarAction_findSingleCarById.action?id="
								+ singleCar.getId() + "&test=1", loginUser
								.getDept()
								+ "部门的用车单请您审核!", true);
				if (epId != null && epId > 0) {
					singleCar.setEpId(epId);
					totalDao.update(singleCar);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bool;
	}

	/*
	 * 查询用车单审核(non-Javadoc)
	 * 
	 * @see com.task.Server.SingleCar.SingleCarServer#findExamList(int, int)
	 */
	@Override
	public Object[] findExamListA(int parseInt, int pageSize) {
		/**
		 * false:未审批 true：已审批
		 * 
		 */
		Map<String, Object> map = CircuitRunServerImpl.findAuditExeNode(
				SingleCar.class, false);
		if (map != null) {
			String hql = "from SingleCar where id in (:entityId)";
			List list = totalDao.find(hql, map, parseInt, pageSize);
			Object[] exam = new Object[2];
			Long countLong = totalDao.count("select count(*) " + hql, map);
			int count = Integer.parseInt(countLong.toString());
			exam[0] = count;
			exam[1] = list;
			return exam;
		}
		return null;
	}

	/*
	 * 查询省外用车单审核(non-Javadoc)
	 * 
	 * @see com.task.Server.SingleCar.SingleCarServer#findExamListB(int, int)
	 */
	@Override
	public Object[] findExamListB(int parseInt, int pageSize) {
		/**
		 * false:未审批 true：已审批
		 * 
		 */
		Map<String, Object> map = CircuitRunServerImpl.findAuditExeNode(
				SingleCar.class, false);
		if (map != null) {
			String hql = "from SingleCar where id in (:entityId)";
			List list = totalDao.find(hql, map, parseInt, pageSize);
			Object[] exam = new Object[2];
			Long countLong = totalDao.count("select count(*) " + hql, map);
			int count = Integer.parseInt(countLong.toString());
			exam[0] = count;
			exam[1] = list;
			return exam;
		}
		return null;
	}

	/*
	 * 省内用车单(通过、驳回)(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.SingleCar.SingleCarServer#updateExamListA(java.lang.Integer
	 * [], java.lang.String)
	 */
	@Override
	public boolean updateExamListA(Integer[] detailSelect, String tag) {
		boolean bool = false;
		if (null != detailSelect && detailSelect.length > 0) {
			Users user = Util.getLoginUser();
			for (int i = 0; i < detailSelect.length; i++) {// 遍历处理状态
				SingleCar detail = (SingleCar) totalDao.getObjectById(
						SingleCar.class, detailSelect[i]);
				CircuitRun circuitRun = circuitRunServer
						.findCircuitRunById(detail.getEpId());
				if ("ok".equals(tag)) {// 同意
					// 处理审批流程
					String audit = circuitRunServer.updateExeNodeByCirId(detail
							.getEpId(), true, "", false, null, false);
					if ("同意".equals(circuitRun.getAllStatus())) {
						// 审批同意后做后续操作
					}
					// 更改明细状态
				} else {// 打回
					circuitRunServer.updateExeNodeByCirId(detail.getEpId(),
							false, "", false, null, true);
					totalDao.update(detail);
				}
				bool = true;
			}
		}
		return bool;
	}

	/*
	 * 省外用车单(通过、驳回)(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.SingleCar.SingleCarServer#updateExamListB(java.lang.Integer
	 * [], java.lang.String)
	 */
	@Override
	public boolean updateExamListB(Integer[] detailSelect, String tag) {
		boolean bool = false;
		if (null != detailSelect && detailSelect.length > 0) {
			Users user = Util.getLoginUser();
			for (int i = 0; i < detailSelect.length; i++) {// 遍历处理状态
				SingleCar detail = (SingleCar) totalDao.getObjectById(
						SingleCar.class, detailSelect[i]);
				CircuitRun circuitRun = circuitRunServer
						.findCircuitRunById(detail.getEpId());
				if ("ok".equals(tag)) {// 同意
					// 处理审批流程
					String audit = circuitRunServer.updateExeNodeByCirId(detail
							.getEpId(), true, "", false, null, false);
					if ("同意".equals(circuitRun.getAllStatus())) {
						// 审批同意后做后续操作
					}
					// 更改明细状态
				} else {// 打回
					circuitRunServer.updateExeNodeByCirId(detail.getEpId(),
							false, "", false, null, true);
					totalDao.update(detail);
				}
				bool = true;
			}
		}
		return bool;
	}

	/*
	 * 查询用车单(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.SingleCar.SingleCarServer#findSingleCar(com.task.entity
	 * .singlecar.SingleCar, int, int, java.lang.String)
	 */
	@Override
	public Object[] findSingleCar(SingleCar singleCar, int parseInt,
			int pageSize, String tag) {
		if (singleCar == null) {
			singleCar = new SingleCar();
		}
		Users loginUser = Util.getLoginUser();// 获得登陆用户
		String hql = totalDao.criteriaQueries(singleCar, null);
		if (singleCar.getVar_dept() != null
				&& !"".equals(singleCar.getVar_dept())) {
			hql += " and var_dept like '%" + singleCar.getVar_dept() + "%'";
		}
		if (singleCar.getCar_usename() != null
				&& !"".equals(singleCar.getCar_usename())) {
			hql += " and car_usename like '%" + singleCar.getCar_usename()
					+ "%'";
		}
		if (tag != null && "self".equals(tag)) {
			hql += " and car_usename='" + loginUser.getName() + "'";
		}
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/*
	 * 根据编号查询用车单(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.SingleCar.SingleCarServer#findSingleCarById(java.lang
	 * .Integer)
	 */
	@Override
	public SingleCar findSingleCarById(Integer id) {
		// TODO Auto-generated method stub
		SingleCar singleCar = (SingleCar) this.totalDao.getObjectById(
				SingleCar.class, id);
		return singleCar;
	}

	/*
	 * 更新用车单(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.SingleCar.SingleCarServer#updateSingleCar(com.task.entity
	 * .singlecar.SingleCar)
	 */
	@Override
	public boolean updateSingleCar(SingleCar singleCar) {
		boolean bool = false;
		Users loginUser = Util.getLoginUser();// 登陆用户信息
		String createDate = Util.getDateTime("yyyy-MM-dd  HH:ss:mm");
		SingleCar singleCar2 = (SingleCar) this.totalDao.getObjectById(
				SingleCar.class, singleCar.getId());
		String type = singleCar2.getSinglecarType();
		String type1 = singleCar.getSinglecarType();
		if (type == type1 || type.equals(type1)) {
			bool = true;
		} else {
			bool = false;
		}
		singleCar2.setVar_dept(singleCar.getVar_dept());
		singleCar2.setCar_date(singleCar.getCar_date());
		singleCar2.setCar_content(singleCar.getCar_content());
		singleCar2.setCar_amount(singleCar.getCar_amount());
		singleCar2.setCar_place(singleCar.getCar_place());
		singleCar2.setCharges(singleCar.getCharges());
		singleCar2.setComeoroutdate(singleCar.getComeoroutdate());
		singleCar2.setBeforeodometer(singleCar.getBeforeodometer());
		singleCar2.setEndodometer(singleCar.getEndodometer());
		singleCar2.setKilometers(singleCar.getKilometers());

		singleCar2.setPilotname(singleCar.getPilotname());
		singleCar2.setCar_number(singleCar.getCar_number());
		singleCar2.setUnit_leading(singleCar.getUnit_leading());

		singleCar2.setSinglecarType(singleCar.getSinglecarType());
		singleCar2.setRemark(singleCar.getRemark());
		singleCar2.setZcwg(singleCar.getZcwg());
		singleCar2.setZmcx(singleCar.getZmcx());
		this.totalDao.update(singleCar2);

		if (bool) {// 出车类型没有变化的情况
			if (singleCar2.getStatus() != null
					&& "打回".equals(singleCar2.getStatus())) {// 打回作修改操作时更新其状态
				CircuitRunServerImpl.updateCircuitRun(singleCar2.getEpId());
			}
		} else {// 出车类型变化的情况
			CircuitRun circuitRun = (CircuitRun) this.totalDao.getObjectById(
					CircuitRun.class, singleCar2.getEpId());
			if (circuitRun != null) {
				bool = this.totalDao.delete(circuitRun);
			}
			if (bool) {// 重新创建审批流程
				Integer epId;
				String processName = "";
				String dept = loginUser.getDept();
				if ("省内".equals(singleCar.getSinglecarType())) {
					processName = dept + "省内用车审核";
				} else {
					processName = dept + "省外用车审核";
				}
				try {
					epId = CircuitRunServerImpl.createProcess(processName,
							SingleCar.class, singleCar2.getId(), "status",
							"id", loginUser.getDept() + "部门的用车单请您审核!", true,
							null);
					if (epId != null && epId > 0) {
						singleCar2.setEpId(epId);
						totalDao.update(singleCar2);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return bool;
	}

	/*
	 * 删除用车信息(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.SingleCar.SingleCarServer#delSingleCarById(java.lang.
	 * Integer)
	 */
	@Override
	public boolean delSingleCarById(Integer id) {
		// TODO Auto-generated method stub
		boolean b = false;
		SingleCar singleCar = (SingleCar) this.totalDao.getObjectById(
				SingleCar.class, id);
		if (singleCar != null) {
			CircuitRun circuitRun = (CircuitRun) this.totalDao.getObjectById(
					CircuitRun.class, singleCar.getEpId());
			b = this.totalDao.delete(circuitRun);
		}
		b = this.totalDao.delete(singleCar);
		return b;
	}

	/*
	 * 总经办确认(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.SingleCar.SingleCarServer#updateSingleCarStatus(java.
	 * lang.Integer)
	 */
	@Override
	public boolean updateSingleCarStatus(Integer id) {
		// TODO Auto-generated method stub
		boolean b = false;
		SingleCar singleCar = (SingleCar) this.totalDao.getObjectById(
				SingleCar.class, id);
		singleCar.setProcessingstatus("已处理");
		b = this.totalDao.update(singleCar);
		return b;
	}

	/*
	 * 查询各审批节点人(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.SingleCar.SingleCarServer#findPay_ExecutionNode(java.
	 * lang.Integer)
	 */
	@Override
	public Map<Integer, Object> findPay_ExecutionNode(Integer id) {
		SingleCar singleCar = (SingleCar) this.totalDao.getObjectById(
				SingleCar.class, id);
		String hql = "from ExecutionNode where auditStatus='同意' and  circuitRun.id=? order by auditLevel desc";
		List<ExecutionNode> list1 = this.totalDao.query(hql, singleCar
				.getEpId());
		List<Signature> list = new ArrayList<Signature>();
		for (int i = 0; i < list1.size(); i++) {
			ExecutionNode executionNode = (ExecutionNode) list1.get(i);
			String username = executionNode.getAuditUserName();
			String hql1 = "from Signature where name='" + username
					+ "' and default_address='默认' ";
			Signature signature = (Signature) this.totalDao
					.getObjectByCondition(hql1);
			list.add(signature);
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, list);
		map.put(2, list1);
		return map;
	}

	/**
	 * 根据驾驶员来统计里程数（出车前里程表，回车后里程表）
	 */
	@SuppressWarnings("unchecked")
	public SingleCarAll getCountkilometers(String pilotname, String firsttime,
			String finishtime, String car_number) {
		SingleCarAll singCarAll = new SingleCarAll();
		singCarAll.setFirsttime(firsttime);
		singCarAll.setFinishtime(finishtime);
		singCarAll.setCar_number(car_number);
		List<SingleCar> list = null;
		if (finishtime == null || finishtime.equals("")) {
			finishtime = Util.getDateTime("yyyy-MM");
		}

		if ((firsttime == null || firsttime.equals("")) && finishtime != null) {
			String hql = "from SingleCar where pilotname=? and car_date<=?";
			list = this.totalDao.query(hql, pilotname, finishtime);
		} else if ((finishtime == null || finishtime.equals(""))
				&& firsttime != null) {
			String hql = " from SingleCar where pilotname=? and car_date>=?";
			list = this.totalDao.query(hql, pilotname, firsttime);
		} else if (car_number == null || car_number.equals("")) {
			String hql = " from SingleCar where pilotname=?";
			list = this.totalDao.query(hql, pilotname);
		} else if (car_number != null) {
			String hql = "from SingleCar where pilotname=? and car_number=?";
			list = this.totalDao.query(hql, pilotname, car_number);
		} else if ((firsttime == null || firsttime.equals(""))
				&& (finishtime == null || finishtime.equals(""))) {
			String hql = "from SingleCar where pilotname=?";
			list = this.totalDao.query(hql, pilotname);
		} else if (firsttime != null && finishtime != null) {
			String hql = "from SingleCar where pilotname=? and car_date between ? and ?";
			list = this.totalDao.query(hql, pilotname, firsttime, finishtime);
		}

		if (list != null && list.size() > 0) {
			Float all = 0f;// 合计公里
			Float all2 = 0f;// 早出晚归
			Float all1 = 0f;// 长途公里
			Float all3 = 0f;// 周末及厂休次数
			StringBuffer sb = new StringBuffer();
			List<String> ChePailist = new ArrayList<String>();
			for (int i = 0; i < list.size(); i++) {
				SingleCar singleCar = list.get(i);
				String zw = singleCar.getZcwg(); // 早出晚归(yes/no)
				if ("yes".equals(zw)) {
					all2 = all2 + 1;
				}
				Float before1 = singleCar.getBeforeodometer();// 长途公里
				Float end1 = singleCar.getEndodometer();
				Float k1 = singleCar.getKilometers();
				if (before1 != null && end1 != null) {
					k1 = end1 - before1;
					all1 += k1;
				}

				if (null == before1 || null == end1) {
					if (null == k1) {
						k1 = 0f;
					}
					all1 += k1;
				}

				String zc = singleCar.getZmcx();// 周末及厂休次数
				if ("yes".equals(zc)) {
					all3 = all3 + 1;
				}

				Float before = singleCar.getBeforeodometer();// 合计公里
				Float end = singleCar.getEndodometer();
				Float k = singleCar.getKilometers();
				if (before != null && end != null) {
					k = end - before;
					all += k;
				}

				else if (null == before || null == end) {
					if (null == k) {
						k = 0f;
					}
					all += k;
				}
				String cn = singleCar.getCar_number();
				if (cn != null && !cn.equals("") && !ChePailist.contains(cn)) {
					sb.append(cn);
					ChePailist.add(cn);
				}
			}

			String permoney = pilotname;
			float zcmoney = all3 * 3;// 周末及厂休金额
			Float ctmoney = all1 / (6.67f);// 长途金额
			float zcwgmoney = all2 * 10;// 早出晚归金额

			Float hjmoney = all * 0.1f;// 合计金额
			Float allmoney = (hjmoney + ctmoney + zcwgmoney + zcmoney);// 总金额
			singCarAll.setHjkilometers(all);
			singCarAll.setHjmoney(hjmoney);
			singCarAll.setCtkilometers(all1);
			singCarAll.setCtmoney(ctmoney);
			singCarAll.setZcwgcs(all2);
			singCarAll.setZcwgmoney(zcwgmoney);
			singCarAll.setZccs(all3);
			singCarAll.setZcmoney(zcmoney);
			singCarAll.setAllmoney(allmoney);
			singCarAll.setPermoney(pilotname);
			singCarAll.setPilotname(pilotname);
			singCarAll.setCar_number(sb.toString());
		}
		return singCarAll;
	}

	// 申请审批里程数
	public boolean updateExamListC(String pilotname, String firsttime,
			String finishtime, String car_number) {
		if (finishtime == null || finishtime.equals("")) {
			finishtime = Util.getDateTime("yyyy-MM");
		}
		if (firsttime == null || firsttime.equals("")) {
			totalDao.query("from SingleCarAll where('" + finishtime
					+ "'>firsttime)");
		} else {
			List list = totalDao
					.query("from SingleCarAll where ((firsttime <='"
							+ firsttime + "' and '" + finishtime
							+ "' <=finishtime ) " + " or" + "(firsttime <='"
							+ firsttime + "' and  finishtime <='" + finishtime
							+ "' ) " + " or" + "('" + firsttime
							+ "' <= firsttime and  '" + finishtime
							+ "' <=finishtime )	" + " or" + "('" + firsttime
							+ "'<=firsttime and finishtime<='" + finishtime
							+ "'))");
		}
		SingleCarAll singleCarAll = getCountkilometers(pilotname, firsttime,
				finishtime, car_number);
		totalDao.save(singleCarAll);
		Integer sc = singleCarAll.getId();
		try {
			Integer epId = CircuitRunServerImpl.createProcess("用车补助评审流程",
					SingleCarAll.class, sc, "status", "id",
					"singleCarAction_findSingleCarAllById.action?"
							+ "singleCarAll.id=" + singleCarAll.getId(),
					"用车补助申请审批", true);
			singleCarAll.setStatus("未审批");
			if (epId != null) {
				singleCarAll.setEpId(epId);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return totalDao.update(singleCarAll);
	}

	// 查询里程数
	@Override
	public Object[] findExamListC(SingleCarAll singleCarAll, int parseInt,
			int pageSize, String status) {
		if (singleCarAll == null) {
			singleCarAll = new SingleCarAll();
		}
		Users user = Util.getLoginUser();
		pilotname = user.getName();
		// pilotname = "王青育";
		String finishtime = null;
		String firsttime = null;
		if (singleCarAll.getFinishtime() == null
				|| singleCarAll.getFinishtime().equals("")) {
			finishtime = Util.getDateTime("yyyy-MM");
		} else {
			finishtime = singleCarAll.getFinishtime();
			singleCarAll.setFinishtime(null);
		}
		if (singleCarAll.getFirsttime() == null
				|| singleCarAll.getFirsttime().equals("")) {
			firsttime = singleCarAll.getFirsttime();
			singleCarAll.setFirsttime(null);
		}
		if (singleCarAll.getCar_number() == null
				|| singleCarAll.getCar_number().equals("")) {
			singleCarAll.setCar_number(singleCarAll.getCar_number());
		}
		Users loginUser = Util.getLoginUser();// 获得登陆用户
		String hql = totalDao.criteriaQueries(singleCarAll, null);
		if (singleCarAll.getFirsttime() != null
				&& singleCarAll.getFirsttime().equals("")) {
			hql += " and (firsttime>='" + firsttime + "' or firsttime is null)";
		}
		if (singleCarAll.getCar_number() != null
				&& singleCarAll.getCar_number().equals("")) {
			hql += " and car_number like '%" + singleCarAll.getCar_number()
					+ "%'";
		}

		hql += " and finishtime<='" + finishtime + "'";
		// if (status != null && "per".equals(status)) {
		// hql += " and pilotname='" + "王青育" + "'";
		// }
		if (status != null && "per".equals(status)) {
			hql += " and pilotname='" + loginUser.getName() + "'";
		}

		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	// 通过ID查询统计补贴
	public SingleCarAll findSingleCarAllById(Integer id) {
		// TODO Auto-generated method stub
		SingleCarAll singleCarAll = (SingleCarAll) this.totalDao.getObjectById(
				SingleCarAll.class, id);
		List<String> qianmingList = new ArrayList<String>();
		if (singleCarAll.getEpId() != null) {
			List<ExecutionNode> list = totalDao
					.query(
							"from ExecutionNode where circuitRun.id=? order by auditLevel",
							singleCarAll.getEpId());
			if (list.size() > 0) {
				for (ExecutionNode en : list) {
					List<String> qmlist = totalDao
							.query(
									"select signature_address from Signature where  code=(select code from Users where id=?)",
									en.getAuditUserId());
					if (qmlist.size() > 0) {
						qianmingList.add(qmlist.get(0));
					}
				}
			}
			singleCarAll.setQianmingList(qianmingList);
		}

		return singleCarAll;
	}

	// //确办
	// public boolean updateSingleCarAllStatus(Integer id) {
	// // TODO Auto-generated method stub
	// boolean b = false;
	// SingleCarAll singleCarAll = (SingleCarAll) this.totalDao.getObjectById(
	// SingleCarAll.class, id);
	// singleCarAll.setStatus("已处理");
	// b = this.totalDao.update(singleCarAll);
	// return b;
	// }

	@SuppressWarnings("unchecked")
	@Override
	public List findSingleCarAllNN(String s, String s1) {
		// TODO Auto-generated method stub
		// String str =
		// "from SingleCarAll where (firsttime <=? and ?<=finishtime ) or (firsttime <=? and  finishtime <=?) or (? <= firsttime and ? <=finishtime ) or (?<=firsttime and finishtime<=?)";
		String str = "from SingleCarAll where (firsttime >=? and finishtime<=? ) or (firsttime <=? and  finishtime >=?) or (firsttime <=? and finishtime >= ? )";
		// String str =
		// "from SingleCarAll where (firsttime <=? and finishtime<=? ) or (firsttime <=? and  finishtime >=?) or (firsttime <=? and finishtime >= ? )";
		List list = totalDao.query(str, s, s1, s, s, s1, s1);
		return list;
	}
}