package com.task.ServerImpl.renshi;

/**
 * 李聪   2015-06-23
 * 离职申请单ServerImpl
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.renshi.DimissionLogServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.action.UsersAction;
import com.task.entity.Contract;
import com.task.entity.ModuleFunction;
import com.task.entity.Provision;
import com.task.entity.Users;
import com.task.entity.WageStandard;
import com.task.entity.renshi.DimissionLog;
import com.task.entity.renshi.Dimission_Handover;
import com.task.entity.renshi.Dimission_ZhengYi;
import com.task.entity.rtx.RtxConnect;
import com.task.util.RtxUtil;
import com.task.util.Util;

@SuppressWarnings( { "unchecked" })
public class DimissionLogServerImpl implements DimissionLogServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/**
	 * 添加离职申请单对象
	 * 
	 * @param DimissionLog
	 * @return
	 */

	@Override
	public String addDimissionLog(DimissionLog dimissionLog) {
		// TODO Auto-generated method stub
		boolean bool = false;
		if (dimissionLog != null) {
			Users loguser1 = Util.getLoginUser();// 获取用户信息
			if(loguser1==null) return "请先登录！";
			dimissionLog.setAddUsersName(loguser1.getName());
			dimissionLog.setAddTime(Util.getDateTime());
			Users loguser = (Users) totalDao.getObjectById(Users.class, dimissionLog.getCodeId());
			if(loguser==null) return "该离职人员不存在，请刷新后重试";
			DimissionLog oldDimissionLog = findDimissionLogByCodeId(loguser
					.getId());
			if (oldDimissionLog != null) {
				return "由于"+dimissionLog.getName()+"当前有正在审核或已通过的离职申请单，无法重复提交！！！";
			}
			dimissNum(dimissionLog);
			/*
			 * dimissionLog.setNaowuzhengyi("否"); if
			 * ("是".equals(dimissionLog.getNaowuzhengyi())) {
			 * dimissionLog.setZhengyi_Status("待填写"); } else { }
			 */
			dimissionLog.setRuzhi_time(loguser.getJoined().toString());
			dimissionLog.setUid(loguser.getUid());
			dimissionLog.setCardId(loguser.getCardId());
			dimissionLog.setTel(loguser.getPassword().getPhoneNumber());
			dimissionLog.setAddress(loguser.getPassword().getPresentAddress());
			dimissionLog.setZhengyi_Status("无");
			dimissionLog.setLzsq_status("待确认");
			dimissionLog.setHand_status("无");
			dimissionLog.setAdd_dimissTime_status("待填写");
			dimissionLog.setHr_status("待填写");
			dimissionLog.setTongzhi_status("无");
			bool = totalDao.save(dimissionLog);

			/*
			 * String processName = dimissionLog.getDept() + "部门离职申请";//申请单审批流程
			 * Integer epId = null; try { epId =
			 * CircuitRunServerImpl.createProcess(processName,
			 * DimissionLog.class, dimissionLog.getId(), "lzsq_status", "id",
			 * "dimissionLogAction_toselect.action?dimissionLog.id=" +
			 * dimissionLog.getId(), dimissionLog.getDept() + "部门  " +
			 * dimissionLog.getName() + " 离职申请，请您审批！", true); if (epId != null
			 * && epId > 0) { dimissionLog.setEpId(epId);
			 * totalDao.update(dimissionLog); } } catch (Exception e) {
			 * e.printStackTrace(); }
			 */
			/*
			 * if ("总成班".equals(dimissionLog.getDept())) {
			 * dimissionLog.setDept("生产"); }
			 */
			// 给主管发消息
			String hql1 = "from ModuleFunction where functionName='主管确认离职申请单'";
			ModuleFunction moduleFunction = (ModuleFunction) this.totalDao
					.getObjectByCondition(hql1);
			if (moduleFunction != null) {
				String sql = "select ta_userid from ta_usersMF where ta_mfId=?";
				List list = this.totalDao.createQuerySelect(null, sql,
						moduleFunction.getId());
				List<String> codess = new ArrayList<String>();
				for (int i = 0; i < list.size(); i++) {
					Users userss = (Users) this.totalDao.getObjectByCondition(
							"from Users where id=?", list.get(i));
					if (userss.getDept().equals(dimissionLog.getDept())) {
						codess.add(userss.getCode());
					}
				}
				//给部门负责人发消息
				List<Integer> user_id = totalDao.query(
						"select userId from UserDept where deptName =?", loguser.getDept());
				if(user_id!=null&&user_id.size()>0){
					for (Integer i : user_id) {
						Users users = (Users) totalDao.getObjectById(Users.class, i);
						codess.add(users.getCode());
					}
				}
				if (codess != null && codess.size() > 0) {
					RtxUtil.sendNotify(codess, dimissionLog.getDept() + "部门  "
							+ dimissionLog.getName() + " 有待确定的离职消息，请前往 人事管理=>公共职业管理=>主管确认离职申请单 进行确认离职时间操作!谢谢。\n操作地址："+AlertMessagesServerImpl.pebsUrl+"/dimissionLogAction_showList_zhuguan.action",
							"系统消息", "0", "0");
				}
			}
			if (bool) {
				return "true";
			}
		}
		return "数据异常，添加失败。请检查后再试！";
	}

	/**
	 * 离职申请编号
	 * @param dimissionLog
	 */
	private void dimissNum(DimissionLog dimissionLog) {
		String createdate1 = Util.getDateTime("yyyyMMdd");
		// String[] a = createdate1.split("-");//将字符串以-分隔，一次放入数组a中
		// createdate1 = a[0] + a[1] + a[2];
		String hql = "select max(shenqing_number) from DimissionLog WHERE shenqing_number LIKE "
				+ "'" + createdate1 + "%'";
		String max_number = (String) this.totalDao
				.getObjectByCondition(hql);
		if (max_number != null && !"".equals(max_number)) {
			long number2 = Long.parseLong(max_number) + 1;
			String number3 = Long.toString(number2);
			dimissionLog.setShenqing_number(number3);
		} else {
			String number2 = createdate1 + "001";
			dimissionLog.setShenqing_number(number2);
		}
	}

	/**
	 * 通过id删除离职申请单对象
	 * 
	 * @param DimissionLog
	 * @return
	 */
	@Override
	public boolean deleteDimissionLog(Integer id) {
		// TODO Auto-generated method stub
		boolean b = false;
		Dimission_Handover olddimissionHandover = getDimission_HandoverByid(id);
		if (olddimissionHandover != null&&olddimissionHandover.getEpId()>0) {
			CircuitRunServerImpl.deleteCircuitRun(olddimissionHandover
					.getEpId());//删除离职交接单审批流程
			b = totalDao.delete(olddimissionHandover);//删除交接单
		}
		Dimission_ZhengYi olddimissionZhengYi = getDimission_ZhengYiByid(id);//删除离职工资单审批流程
		if (olddimissionZhengYi != null&&olddimissionZhengYi.getEpId()>0) {
			CircuitRunServerImpl
					.deleteCircuitRun(olddimissionZhengYi.getEpId());
		}
		DimissionLog dimissionLog = getDimissionLogById(id);//删除离职申请单审批流程
		if (dimissionLog != null) {
			CircuitRunServerImpl.deleteCircuitRun(dimissionLog.getEpId());
			b = totalDao.delete(dimissionLog);// 删除申请单
		}
		return b;
	}

	private Dimission_ZhengYi getDimission_ZhengYiByid(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from Dimission_ZhengYi WHERE dimi_log = " + id
				+ " order by id desc ";
		Dimission_ZhengYi dimissionZhengYi = (Dimission_ZhengYi) this.totalDao
				.getObjectByCondition(hql);
		if (dimissionZhengYi != null) {
			return dimissionZhengYi;
		}
		return null;
	}

	/**
	 * 修改离职申请单对象
	 * 
	 * @param DimissionLog
	 * @return
	 */
	@Override
	public boolean updateDimissionLog(DimissionLog dimissionLog, String string) {
		// TODO Auto-generated method stub
		DimissionLog dim = getDimissionLogById(dimissionLog.getId());
		if (dim != null) {
			if ("all".equals(string)) {
				dim.setTijian(dimissionLog.getTijian());
				dim.setPeixunxieyi(dimissionLog.getPeixunxieyi());
				dim.setBaomi(dimissionLog.getBaomi());
				dim.setBuchong(dimissionLog.getBuchong());
				dim.setNaowuzhengyi(dimissionLog.getNaowuzhengyi());
				if ("否".equals(dim.getNaowuzhengyi())) {
					dim.setXieyi_status("无");
				} else if ("是".equals(dim.getNaowuzhengyi())) {
					dim.setXieyi_status("待填写");
				}
				dim.setZhengyi_content(dimissionLog.getZhengyi_content());
				dim.setHr_addTime(Util.getDateTime());
				dim.setHr_status("已填写");
			} else {
				dim.setYear_term(dimissionLog.getYear_term());
				dim.setDimission_Reason(dimissionLog.getDimission_Reason());// 离职原因
				dim.setDimission_laterGo(dimissionLog.getDimission_laterGo());// 离职后去向
				/*
				 * dim.setNaowuzhengyi(dimissionLog.getNaowuzhengyi());
				 * dim.setZhengyi_content(dimissionLog.getZhengyi_content());
				 */
				dim.setConfirm(dimissionLog.getConfirm());// 本人确定

				if ("打回".equals(dim.getLzsq_status())) {
					CircuitRunServerImpl.updateCircuitRun(dim.getEpId());
					dim.setLzsq_status("审批中");
				}
				dim.setUpdateTime(Util.getDateTime());
			}
			return totalDao.update(dim);
		}
		return false;
	}

	/**
	 * 主管添加止薪日期（20150804 Lc添加）
	 * 
	 * @param DimissionLog
	 * @return
	 */
	@Override
	public boolean updateZhugaunDimissionLog(DimissionLog dimissionLog) {
		// TODO Auto-generated method stub
		boolean bool = false;
		if (dimissionLog != null) {
			DimissionLog dimission2 = getDimissionLogById(dimissionLog.getId());

			if (dimission2 != null) {
				/*
				 * dimission2.setNaowuzhengyi("是"); if
				 * ("否".equals(dimission2.getNaowuzhengyi())) {
				 * dimission2.setZhengyi_Status("无"); } else if
				 * ("是".equals(dimission2.getNaowuzhengyi())) { }
				 */
				dimission2.setLzsq_status("未审批");
				dimission2.setZhengyi_Status("待填写");
				dimission2.setHand_status("未填写");
				dimission2.setTongzhi_status("待填写");
				dimission2.setApp_time(dimissionLog.getApp_time());
				dimission2.setAdd_dimissTime_status("已填写");
				dimission2.setAdd_add_dimiss_Time(Util.getDateTime());// 主管确认申请单离职时间的时间
				bool = totalDao.update(dimission2);
				// 调用离职申请审批流程
//				String processName = dimission2.getDept() + "部门离职申请";
				String processName = "离职申请";
				Integer epId = null;
				try {
					epId = CircuitRunServerImpl.createProcess(processName,
							DimissionLog.class, dimission2.getId(),
							"lzsq_status", "id",
							"dimissionLogAction_toselect.action?dimissionLog.id="
									+ dimissionLog.getId(), dimission2
									.getDept()
									+ "部门  "
									+ dimission2.getName()
									+ " 离职申请，请您审批！", true,dimission2.getDept());
					if (epId != null && epId > 0) {
						dimission2.setEpId(epId);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 给人事发消息
				String hql1 = "from ModuleFunction where functionName='离职工资单添加'";
				ModuleFunction moduleFunction = (ModuleFunction) this.totalDao
						.getObjectByCondition(hql1);
				if (moduleFunction != null) {
					String sql = "select ta_userid  from ta_usersMF where ta_mfId=?";
					List list = this.totalDao.createQuerySelect(null, sql,
							moduleFunction.getId());
					List<String> codess = new ArrayList<String>();
					for (int i = 0; i < list.size(); i++) {
						Users userss = (Users) this.totalDao
								.getObjectByCondition("from Users where id=?",
										list.get(i));
						codess.add(userss.getCode());
					}
					if (codess != null && codess.size() > 0) {
						RtxUtil.sendNotify(codess, dimission2.getDept() + "部门 "
								+ dimission2.getName() + " 有待添加的离职工资单，请前往操作!",
								"人事系统消息", "0", "0");
					}
				}
				// 给申请人发消息
//				List<String> codes1 = new ArrayList<String>();
//				codes1.add(dimission2.getCode());
//				if (codes1 != null && codes1.size() > 0) {
//					RtxUtil.sendNotify(codes1, "您的离职申请消息已确认，请添加离职交接单!", "系统消息", "0", "0");
//				}
				//将离职人Users状态设置为离职中
				Users users = (Users) totalDao.getObjectByCondition("from Users where code = ? and dept = ? and name = ?", dimission2.getCode(),dimission2.getDept(),dimission2.getName());
				if (users!=null) {
					users.setOnWork("离职中");
					totalDao.update(users);
				}
				//自动生成离职交接单
				boolean bools = false;
				if (bool) {
					Dimission_Handover dimission_Handover = new Dimission_Handover();
					// Users loguser = Util.getLoginUser();//获取用户信息
					String createdate1 = Util.getDateTime("yyyyMMdd");
					// String[] a = createdate1.split("-");//将字符串以-分隔，一次放入数组a中
					// createdate1 = a[0] + a[1] + a[2];
					String hql = "select max(dimission_number) from Dimission_Handover WHERE dimission_number LIKE "
							+ "'" + createdate1 + "%'";
					String max_number = (String) this.totalDao
							.getObjectByCondition(hql);
					if (max_number != null && !"".equals(max_number)) {
						long number2 = Long.parseLong(max_number) + 1;
						String number3 = Long.toString(number2);
						dimission_Handover.setDimission_number(number3);
					} else {
						String number2 = createdate1 + "001";
						dimission_Handover.setDimission_number(number2);
					}
					dimission_Handover.setName(dimission2.getName());//离职人名称
					dimission_Handover.setDept(dimission2.getDept());//离职人部门
					dimission_Handover.setCodeId(dimission2.getCodeId());//离职人ID
					dimission_Handover.setDimission_Reason(dimission2.getDimission_Reason());//离职原因
					dimission_Handover.setTa_dimissionLog_id(dimission2.getId()+"");
					dimission_Handover.setLzjj_status("未审核");
					dimission_Handover.setAddTime(Util.getDateTime());
					bools = totalDao.save(dimission_Handover);
					//成功添加交接单后将申请单状态改为已填写
					if (bools) {
						dimission2.setHand_status("已填写");
						totalDao.update(dimission2);
					}
					String processName1 = "离职交接单";
					Integer epId1 = null;
					try {
						epId1 = CircuitRunServerImpl
								.createProcess(processName1, Dimission_Handover.class,
										dimission_Handover.getId(), "lzjj_status",
										"id",
										"dimission_HandoverAction_toselect.action?dimissionHandover.id="
												+ dimission_Handover.getId(),
										dimission_Handover.getDept() + "部门 "
												+ dimission_Handover.getName()
												+ " 离职交接单，请您审批！", true,dimission2.getDept());
						if (epId1 != null && epId1 > 0) {
							dimission_Handover.setEpId(epId1);
							totalDao.update(dimission_Handover);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return bools;
			}
		}
		return bool;
	}

	/**
	 * 通过id获取离职申请单对象
	 * 
	 * @param DimissionLog
	 * @return
	 */
	@Override
	public DimissionLog getDimissionLogById(Integer id) {
		// TODO Auto-generated method stub
		Object o = totalDao.getObjectById(DimissionLog.class, id);
		if (o != null) {
			return (DimissionLog) o;
		}
		return null;
	}

	/**
	 * 按条件分页查询离职申请单
	 * 
	 * @param DimissionLog
	 *            对象
	 * @param pageNo
	 *            起始页
	 * @param pageSize
	 *            每页数量
	 * @return
	 */

	@Override
	public Map<Integer, Object> findDimissionLogsByCondition(
			DimissionLog dimissionLog, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (dimissionLog == null) {
			dimissionLog = new DimissionLog();
		}
		String hql = totalDao.criteriaQueries(dimissionLog, null);
		hql += " order by id desc";
		List listInt = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		// Object[] o = { list, count };
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listInt);
		map.put(2, count);
		return map;
	}

	/**
	 * 查询有离职争议的离职申请单
	 * 
	 * @param DimissionLog
	 *            对象
	 * @param pageNo
	 *            起始页
	 * @param pageSize
	 *            每页数量
	 * @return
	 */

	@Override
	public Map<Integer, Object> findDimissionLogs_daiByCondition(
			DimissionLog dimissionLog, int pageNo, int pageSize, String tag) {
		// TODO Auto-generated method stub
		if (dimissionLog == null) {
			dimissionLog = new DimissionLog();
		}
		String hql = null;
		if (tag != null && "tongzhi".equals(tag)) {
			String sql = " tongzhi_status='待填写'";
			hql = totalDao.criteriaQueries(dimissionLog, sql);
		} else if (tag != null && "xieyi".equals(tag)) {
			String sql = " xieyi_status='待填写'";
			hql = totalDao.criteriaQueries(dimissionLog, sql);
		} else {
			String sql = " zhengyi_status='待填写'";
			hql = totalDao.criteriaQueries(dimissionLog, sql);
		}                           
		hql += " and lzsq_status not in ('打回') order by id desc";
		dimissionLog = null;
		List listInt = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		// Object[] o = { list, count };
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listInt);
		map.put(2, count);
		return map;
	}

	// 查询所有离职申请单对象
	@Override
	public List<DimissionLog> findAll() {
		// TODO Auto-generated method stub
		List all = totalDao.query("from DimissionLogById");
		if (all.size() > 0) {
			return (List<DimissionLog>) all;
		}
		return null;
	}

	/**
	 * 通过UserId查询合同对象
	 * 
	 * @param
	 * @return
	 */
	@Override
	public Contract getContractByusId(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from Contract WHERE ta_contract_userId = " + id
				+ " and ta_contract_isUse = '默认' order by id desc ";
		Contract contract1 = (Contract) this.totalDao.getObjectByCondition(hql);
		if (contract1 != null) {
			return contract1;
		}
		return null;
	}

	@Override
	public Dimission_Handover getDimission_HandoverByid(Integer id) {
		String hql = "from Dimission_Handover WHERE ta_dimissionLog_id = " + id
				+ " order by id desc ";
		Dimission_Handover dimissionHandover = (Dimission_Handover) this.totalDao
				.getObjectByCondition(hql);
		if (dimissionHandover != null) {
			return dimissionHandover;
		}
		return null;
	}

	@Override
	public List<Provision> findProvision(String string) {
		// TODO Auto-generated method stub
		if (!"".equals(string) && string != null) {
			List all = totalDao.query(
					"from Provision WHERE provisionStatus=?", string);
			if (all.size() > 0) {
				return (List<Provision>) all;
			}
		}
		return null;
	}

	// 查询本人记录
	@Override
	public Map<Integer, Object> findDimissionLogsBycodeCondition(
			DimissionLog dimissionLog, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (dimissionLog == null) {
			dimissionLog = new DimissionLog();
		}
		Users users = Util.getLoginUser();
		String sql = " codeId='" + users.getId() + "'";
//		dimissionLog.setCodeId(users.getId());
		String hql = totalDao.criteriaQueries(dimissionLog,sql);
		hql += " order by id desc";
		List listInt = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listInt);
		map.put(2, count);
		return map;
	}

	// 验证是否重复提交
	@Override
	public DimissionLog findDimissionLogByCodeId(Integer integer) {
		// TODO Auto-generated method stub
		if (integer != null && integer > 0) {
			String hql = "from DimissionLog where codeId=? and lzsq_status in ('待确认','未审核','审批中')";
			return (DimissionLog) totalDao.getObjectByQuery(hql, integer);
		}
		return null;
	}

	// 查询待主管确认时间的申请单
	@Override
	public Map<Integer, Object> findDimissionLogsByZhuguanCondition(
			DimissionLog dimissionLog, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (dimissionLog == null) {
			dimissionLog = new DimissionLog();
		}
		String hql = "";
		String sql = " add_dimissTime_status='待填写'";
		hql = totalDao.criteriaQueries(dimissionLog,sql, "dept");

		Users users = Util.getLoginUser();
		String dept_name = totalDao.query(
				"select deptName from UserDept where userId =?",
				users.getId()).toString();
		dept_name += "," + users.getDept();
		/*
		 * StringBuffer deptBuffer = new StringBuffer(); for (int i = 0; i <
		 * list.size(); i++) { DeptNumber dept = (DeptNumber) list.get(i);
		 * if (i==0) { deptBuffer.append(dept.getDept()); }else {
		 * deptBuffer.append(","+dept.getDept()); } }
		 * deptBuffer.append(","+deptSelect.getDept());
		 */

		if (!"".equals(dept_name) && dept_name != null) {
			dept_name = "'"
					+ dept_name.replaceAll(" ", "").replaceAll(",", "','").replaceAll("\\[",
							"").replaceAll("\\]", "") + "'";
			hql += " and dept in (" + dept_name + ")";
		}
		hql += " order by id desc";
		List listInt = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listInt);
		map.put(2, count);
		return map;
	}


	@Override
	public WageStandard getWageStandardByid(Integer id) {
		// TODO Auto-generated method stub
		DimissionLog dimissionLog = (DimissionLog) totalDao.getObjectById(DimissionLog.class, id);
		if (dimissionLog!=null) {
			WageStandard standard = (WageStandard) totalDao.getObjectByCondition("from WageStandard where userName = ? and code = ? order by id desc", dimissionLog.getName(),dimissionLog.getCode());
			return standard;
		}
		return null;
	}

	@Override
	public Users findUsersId(Integer integer) {
		// TODO Auto-generated method stub
		return (Users) totalDao.getObjectById(Users.class, integer);
	}

}
