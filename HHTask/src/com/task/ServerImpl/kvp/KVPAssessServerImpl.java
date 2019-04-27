package com.task.ServerImpl.kvp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import com.task.Dao.TotalDao;
import com.task.Server.kvp.KVPAssessServer;
import com.task.Server.sys.CircuitRunServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.DeptNumber;
import com.task.entity.Password;
import com.task.entity.Users;
import com.task.entity.bargain.Bargain;
import com.task.entity.bargain.BargainingDetails;
import com.task.entity.kvp.EightBReport;
import com.task.entity.kvp.ExecuteKVP;
import com.task.entity.kvp.ImproveKVP;
import com.task.entity.kvp.KVPAssess;
import com.task.entity.project.ProjectMaterialOrder;
import com.task.entity.system.CircuitRun;
import com.task.util.Util;
import org.apache.commons.collections4.map.HashedMap;

public class KVPAssessServerImpl implements KVPAssessServer {
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

	private TotalDao totalDao;
	private CircuitRunServer circuitRunServer;

	
	/*
	 * 添加产品评估(non-Javadoc)
	 * @see com.task.Server.kvp.KVPAssessServer#saveKVPAssess(com.task.entity.kvp.KVPAssess, java.util.List)
	 */
	@Override
	public Map<Integer,Object> saveKVPAssess(KVPAssess kvpAssess) {
		Map<Integer,Object>map=new HashedMap();
		boolean bool = false;
		Users loginUser = Util.getLoginUser();// 登陆用户信息
		KVPAssess kvpAssess2 = new KVPAssess();
		String createdate = Util.getDateTime("yyyy");
		String kvpDate =Util.getDateTime("yyyy-MM-dd");
		String num = "KVP-Assess-"+createdate+"-101";
		
		//String hql = "select max(kvp_number) from KVPAssess";
		String hql = "select kvp_number from KVPAssess where id=(select max(id) from KVPAssess) ";
		String max_number = (String) this.totalDao.getObjectByCondition(hql);
		if (max_number != null && !"".equals(max_number)) {
			Integer beginIndex = max_number.lastIndexOf("-");
			String max_number1 =  max_number.substring(beginIndex+1, max_number.length());
			//String max_number1 = max_number.substring(beginIndex+1);
			Integer num2 = Integer.parseInt(max_number1)+1;
			String num3 = num2.toString();
			String num1 = "KVP-"+loginUser.getPassword().getDeptNumber()+"-"+createdate+"-"+num3;
//			String num1 = "KVP-Assess-"+createdate+"-"+num3;
			kvpAssess2.setKvp_number(num1);
		} else {
			String number2 =  "KVP-"+loginUser.getPassword().getDeptNumber()+"-"+createdate+"-101";
//			String number2 =  "KVP-Assess-"+createdate+"-101";
			kvpAssess2.setKvp_number(number2);
		}
		kvpAssess2.setPart_name(kvpAssess.getPart_name());
		kvpAssess2.setPart_number(kvpAssess.getPart_number());
		kvpAssess2.setProcess_name(kvpAssess.getProcess_name());
		kvpAssess2.setQuality_assessment(kvpAssess.getQuality_assessment());
		kvpAssess2.setAssessment_findings(kvpAssess.getAssessment_findings());
		kvpAssess2.setKvp_date(kvpDate);
		kvpAssess2.setStatus("未审批");
		kvpAssess2.setKvp_username(loginUser.getName());
		kvpAssess2.setImproved_beforeproblems(kvpAssess.getImproved_beforeproblems());
		kvpAssess2.setImproved_endproblems(kvpAssess.getImproved_endproblems());
		
//		Set<ImproveKVP> newSet = new HashSet<ImproveKVP>();
//		if(improveKVPList.size()>0){
//			for (int i = 0; i < improveKVPList.size(); i++) {
//				ImproveKVP improveKVP1 = improveKVPList .get(i);
//				if(improveKVP1!=null){
//					improveKVP1.setKvpAssess(kvpAssess2);
//					newSet.add(improveKVP1);
//				}
//			}
//			kvpAssess2.setImproveKVPs(newSet);
//		}
		try {
			bool = this.totalDao.save(kvpAssess2);
			map.put(2,kvpAssess2.getId());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
//		if (bool) {
//			Integer epId;
//			String dept = loginUser.getDept();
//			String processName = dept + "KVP产品评估审核";
//			try {
//				epId = CircuitRunServerImpl.createProcess(processName,
//						KVPAssess.class,kvpAssess2.getId(), "status",
//						"id",loginUser.getDept() + "部门的产品持续改进项目质量风险评估单请您审核!", true,null);
//				if (epId != null && epId > 0) {
//					kvpAssess2.setEpId(epId);
//					totalDao.update(kvpAssess2);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		map.put(1, bool);
		return map;
	}

	/*
	 * 查询kvp产品审核(non-Javadoc)
	 * @see com.task.Server.kvp.KVPAssessServer#findExamList(int, int)
	 */
	@Override
	public Object[] findExamList(int parseInt, int pageSize) {
		/**
		 * false:未审批 true：已审批
		 * 
		 */
		Map<String, Object> map = CircuitRunServerImpl.findAuditExeNode(
				KVPAssess.class, false);
		if (map != null) {
			String hql = "from KVPAssess where id in (:entityId)";
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
	 * kvp产品审批(通过、驳回)(non-Javadoc)
	 * @see com.task.Server.kvp.KVPAssessServer#updateExamBonus(java.lang.Integer[], java.lang.String)
	 */
	@Override
	public boolean updateExamBonus(Integer[] detailSelect, String tag) {
		boolean bool = false;
		if (null != detailSelect && detailSelect.length > 0) {
			Users user = Util.getLoginUser();
			for (int i = 0; i < detailSelect.length; i++) {// 遍历处理状态
				KVPAssess detail = (KVPAssess) totalDao.getObjectById(
						KVPAssess.class, detailSelect[i]);
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
	 * 
	 * 查看kvp产品评估(non-Javadoc)
	 * @see com.task.Server.kvp.KVPAssessServer#findBargain(com.task.entity.kvp.KVPAssess, int, int)
	 */
	@Override
	public Object[] findBargain(KVPAssess kvpAssess, int parseInt, int pageSize,String test) {
		if (kvpAssess == null) {
			kvpAssess = new KVPAssess();
		}
		Users loginUser = Util.getLoginUser();// 获得登陆用户
		String hql = totalDao.criteriaQueries(kvpAssess, null);
		if(kvpAssess.getKvp_number()!=null&&!"".equals(kvpAssess.getKvp_number())){
			hql += " and kvp_number like '%"+kvpAssess.getKvp_number()+"%'";
		}
		if(kvpAssess.getKvp_username()!=null&&!"".equals(kvpAssess.getKvp_username())){
			hql += " and kvp_username like '%"+kvpAssess.getKvp_username()+"%'";
		}
		if(kvpAssess.getProcess_name()!=null&&!"".equals(kvpAssess.getProcess_name())){
			hql += " and process_name like '%"+kvpAssess.getProcess_name()+"%'";
		}
		if(kvpAssess.getKvp_date()!=null&&!"".equals(kvpAssess.getKvp_date())){
			hql += " and kvp_date='"+kvpAssess.getKvp_date()+"'";
		}
		
		if(test!=null&&"self".equals(test)){
			hql += " and kvp_username='"+loginUser.getName()+"'";
		}
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		//List newList = new ArrayList();
//		for (int i = 0; i < list.size(); i++) {
//			KVPAssess kvpAssess2 =  (KVPAssess) list.get(i);
//			ExecuteKVP executeKVP=kvpAssess2.getExecuteKVP();
//			if(executeKVP!=null){
//				executeKVP.getId();
//			}
//		}
		int count = totalDao.getCount(hql);
		Object[] o = { list, count};
		return o;
	}

	/*
	 * 根据编号查询kvp产品评估(non-Javadoc)
	 * @see com.task.Server.kvp.KVPAssessServer#findKVPAssessById(java.lang.Integer)
	 */
	@Override
	public KVPAssess findKVPAssessById(Integer id) {
		KVPAssess kvpAssess = (KVPAssess) this.totalDao.getObjectById(KVPAssess.class, id);
		return kvpAssess;
	}

	/*
	 * 修改kvp产品评估(non-Javadoc)
	 * @see com.task.Server.kvp.KVPAssessServer#updateKVPAssess(com.task.entity.kvp.KVPAssess)
	 */
	@Override
	public boolean updateKVPAssess(KVPAssess kvpAssess) {
		Users loginUser = Util.getLoginUser();// 获得登陆用户
		String kvpDate =Util.getDateTime("yyyy-MM-dd");
		KVPAssess kvpAssess2 = (KVPAssess) this.totalDao.getObjectById(KVPAssess.class, kvpAssess.getId());
		kvpAssess2.setPart_name(kvpAssess.getPart_name());
		kvpAssess2.setPart_number(kvpAssess.getPart_number());
		kvpAssess2.setProcess_name(kvpAssess.getProcess_name());
		kvpAssess2.setQuality_assessment(kvpAssess.getQuality_assessment());
		kvpAssess2.setImproved_beforeproblems(kvpAssess.getImproved_beforeproblems());
		kvpAssess2.setImproved_endproblems(kvpAssess.getImproved_endproblems());
		kvpAssess2.setAssessment_findings(kvpAssess.getAssessment_findings());
		kvpAssess2.setKvp_date(kvpDate);
		boolean bool = this.totalDao.update(kvpAssess2);
		if("打回".equals(kvpAssess.getStatus())){
			CircuitRunServerImpl.updateCircuitRun(kvpAssess2.getEpId());
		}
		return bool;
	}

	/*
	 * 根据编号删除kvp(non-Javadoc)
	 * @see com.task.Server.kvp.KVPAssessServer#delKVPAssess(java.lang.Integer)
	 */
	@Override
	public void delKVPAssess(Integer id) {
		KVPAssess kvpAssess = (KVPAssess) this.totalDao.getObjectById(KVPAssess.class, id);
		///String hql = "from ExecuteKVP where kvpAssess.id="+kvpAssess.getId();
		//CircuitRun circuitRun = (CircuitRun) this.totalDao.getObjectById(CircuitRun.class, kvpAssess.getEpId());
		ExecuteKVP executeKVP = kvpAssess.getExecuteKVP();
		if(executeKVP!=null){
			kvpAssess.setExecuteKVP(executeKVP);
			CircuitRun circuitRun1 = (CircuitRun) this.totalDao.getObjectById(CircuitRun.class, executeKVP.getEpId());
			this.totalDao.delete(circuitRun1);
		}
		//this.totalDao.delete(circuitRun);
		this.totalDao.delete(kvpAssess);
	}

	/*
	 * 填写项目执行单(non-Javadoc)
	 * @see com.task.Server.kvp.KVPAssessServer#saveExecuteKVP(com.task.entity.kvp.KVPAssess, com.task.entity.kvp.ExecuteKVP)
	 */
	@Override
	public boolean saveExecuteKVP(KVPAssess kvpAssess, ExecuteKVP executeKVP) {
		boolean bool = false;
		Users loginUser = Util.getLoginUser();// 登陆用户信息
		String ExecuteDate =Util.getDateTime("yyyy-MM-dd");
		String hql2 = "from Password where user.id=?";
		Password password = (Password) this.totalDao.getObjectByCondition(hql2, loginUser.getId());
		ExecuteKVP executeKVP2 = new ExecuteKVP();
		String createdate = Util.getDateTime("yyyy");//获取年份
		String executeNumber = "KVP-"+password.getDeptNumber()+"-"+createdate+"-101";
		
		//String hql = "select max(executeNumber) from ExecuteKVP";
		String hql = "select executeNumber from ExecuteKVP where id=(select max(id) from ExecuteKVP) ";
		String max_number = (String) this.totalDao.getObjectByCondition(hql);
		if (max_number != null && !"".equals(max_number)) {
			Integer beginIndex = max_number.lastIndexOf("-");
			String max_number1 = max_number.substring(beginIndex+1, max_number.length());
			//String max_number1 = max_number.substring(beginIndex+1);
			Integer num2 = Integer.parseInt(max_number1)+1;
			String num3 = num2.toString();
			String num1 = "KVP-"+password.getDeptNumber()+"-"+createdate+"-"+num3;
			executeKVP2.setExecuteNumber(num1);
		} else {
			String number2 =  "KVP-"+password.getDeptNumber()+"-"+createdate+"-101";
			executeKVP2.setExecuteNumber(number2);
		}
		// 改进部门
		executeKVP2.setImprove_deptNum(executeKVP.getImprove_deptNum());
		executeKVP2.setImprove_username(executeKVP.getImprove_username());
		executeKVP2.setImprove_usercode(executeKVP.getImprove_usercode());
		//责任部门
		executeKVP2.setRes_deptNum(executeKVP.getRes_deptNum());
		executeKVP2.setRes_username(executeKVP.getRes_username());
		executeKVP2.setRes_usercode(executeKVP.getRes_usercode());
		//成本分析(改进前)
		executeKVP2.setMaterialcosts(executeKVP.getMaterialcosts());//单套周期
		executeKVP2.setLaborcosts(executeKVP.getLaborcosts());//每月单位人工成本
		
		//成本分析(改进后)
		executeKVP2.setMaterialcosts1(executeKVP.getMaterialcosts1());//单套周期
		executeKVP2.setLaborcosts1(executeKVP.getLaborcosts1());//每月单位人工成本
		//成本分析(两种算法:1、改进前的单套周期和每月单位人工成本相加-改进后的单套周期和每月单位人工成本相加
		//2、改进前的单套周期和每月单位人工成本相乘- 改进后的单套周期和每月单位人工成本相乘 )
		float Materialcosts = executeKVP.getMaterialcosts();//改进前的单套周期
		float Laborcosts = executeKVP.getLaborcosts();//每月单位人工成本
		float Materialcosts1 = executeKVP.getMaterialcosts1();////改进后的单套周期
		float Laborcosts1 = executeKVP.getLaborcosts1();//每月单位人工成本
		double Costsavings = (Materialcosts+Laborcosts)-(Materialcosts1+Laborcosts1);//相加的算法
		//float Costsavings = (Materialcosts*Laborcosts)-(Materialcosts1*Laborcosts1);//相乘的算法
		executeKVP2.setCostsavings(Costsavings);//成本结余
		KVPAssess kvpAssess2 = (KVPAssess) this.totalDao.getObjectById(KVPAssess.class, kvpAssess.getId());
		executeKVP2.setKvpAssess(kvpAssess2);
		executeKVP2.setStatus("未审批");
		executeKVP2.setImproved_beforeproblems(executeKVP.getImproved_beforeproblems());
		executeKVP2.setImproved_endproblems(executeKVP.getImproved_endproblems());
		executeKVP2.setExecuteDate(ExecuteDate);
		bool = this.totalDao.save(executeKVP2);
		//做项目执行审批操作
		if(bool){
			Integer epId;
			String dept = loginUser.getDept();
//			String processName = dept + "KVP项目执行审核";
			String processName = "KVP项目执行审核";
			try { 
//				epId = CircuitRunServerImpl.createProcess(processName,
//						ExecuteKVP.class,executeKVP2.getId(), "status",
//						"id",loginUser.getDept() + "部门的产品持续改进项目执行单请您审核!", true,null);
				 epId = CircuitRunServerImpl.createProcess(processName,
							ExecuteKVP.class,executeKVP2.getId(),"status", "id",
						"kvpAssessAction_findExecuteKVPById.action?tag=1&id="
								+ executeKVP2.getKvpAssess().getId(), loginUser.getDept() + "部门的产品持续改进项目执行单请您审核!",
								true);
				
				
				if (epId != null && epId > 0) {
					executeKVP2.setEpId(epId);
					totalDao.update(executeKVP2);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bool;
	}

	/*
	 * 查询所有部门编码(non-Javadoc)
	 * @see com.task.Server.kvp.KVPAssessServer#selectDept()
	 */
	@Override
	public List selectDept() {
		// TODO Auto-generated method stub
		String hql = "from DeptNumber where deptNumber is  not null and  deptNumber!=''";
		List list = totalDao.query(hql);
		return list;
	}

	/*
	 * 通过部门查询员工(non-Javadoc)
	 * @see com.task.Server.kvp.KVPAssessServer#selectUser(java.lang.String)
	 */
	@Override
	public List selectUser(String dept) {
//		String hql ="from Users where dept=(select dept from DeptNumber where" +
//				" deptNumber='"+dept+"') and onWork='在职'";
		String hql ="from Users where dept=(select dept from DeptNumber where" +
		" deptNumber='"+dept+"') and onWork!='离职' ";
		List list = this.totalDao.query(hql);
		return list;
	}

	/*
	 * 
	 * 查询对应的员工号(non-Javadoc)
	 * @see com.task.Server.kvp.KVPAssessServer#selectUserCode(java.lang.String)
	 */
	@Override
	public Users selectUserCode(String name) {
		// TODO Auto-generated method stub
		String hql ="from Users where name=?";
		Users users = (Users) this.totalDao.getObjectByCondition(hql, name);
		return users;
	}

	/*
	 * 查询执行单(non-Javadoc)
	 * @see com.task.Server.kvp.KVPAssessServer#findExecuteKVPById(java.lang.Integer)
	 */
	@Override
	public ExecuteKVP findExecuteKVPById(Integer id) {
		String hql = "from ExecuteKVP where kvpAssess.id=?";
		ExecuteKVP executeKVP = (ExecuteKVP) this.totalDao.getObjectByCondition(hql, id);
		return executeKVP;
	}

	/*
	 * 修改执行单(non-Javadoc)
	 * @see com.task.Server.kvp.KVPAssessServer#updateExecuteKVP(com.task.entity.kvp.ExecuteKVP)
	 */
	@Override
	public boolean updateExecuteKVP(ExecuteKVP executeKVP) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Users loginUser = Util.getLoginUser();// 登陆用户信息
		ExecuteKVP executeKVP2 = (ExecuteKVP) this.totalDao.getObjectById(ExecuteKVP.class, executeKVP.getId());
		// 改进部门
		executeKVP2.setImprove_deptNum(executeKVP.getImprove_deptNum());
		executeKVP2.setImprove_username(executeKVP.getImprove_username());
		executeKVP2.setImprove_usercode(executeKVP.getImprove_usercode());
		//责任部门
		executeKVP2.setRes_deptNum(executeKVP.getRes_deptNum());
		executeKVP2.setRes_username(executeKVP.getRes_username());
		executeKVP2.setRes_usercode(executeKVP.getRes_usercode());
		//成本分析(改进前)
		executeKVP2.setMaterialcosts(executeKVP.getMaterialcosts());//单套周期
		executeKVP2.setLaborcosts(executeKVP.getLaborcosts());//每月单位人工成本
		
		//成本分析(改进后)
		executeKVP2.setMaterialcosts1(executeKVP.getMaterialcosts1());//单套周期
		executeKVP2.setLaborcosts1(executeKVP.getLaborcosts1());//每月单位人工成本
		//成本分析(两种算法:1、改进前的单套周期和每月单位人工成本相加-改进后的单套周期和每月单位人工成本相加
		//2、改进前的单套周期和每月单位人工成本相乘- 改进后的单套周期和每月单位人工成本相乘 )
		float Materialcosts = executeKVP.getMaterialcosts();//改进前的单套周期
		float Laborcosts = executeKVP.getLaborcosts();//每月单位人工成本
		float Materialcosts1 = executeKVP.getMaterialcosts1();////改进后的单套周期
		float Laborcosts1 = executeKVP.getLaborcosts1();//每月单位人工成本
		double Costsavings = (Materialcosts+Laborcosts)-(Materialcosts1+Laborcosts1);//相加的算法
		//float Costsavings = (Materialcosts*Laborcosts)-(Materialcosts1*Laborcosts1);//相乘的算法
		executeKVP2.setCostsavings(Costsavings);//成本结余
		executeKVP2.setImproved_beforeproblems(executeKVP.getImproved_beforeproblems());
		executeKVP2.setImproved_endproblems(executeKVP.getImproved_endproblems());
		bool = this.totalDao.update(executeKVP2);
		if(executeKVP!=null&&"打回".equals(executeKVP.getStatus())){
			CircuitRunServerImpl.updateCircuitRun(executeKVP2.getEpId());
		}
		return bool;
	}

	
	/*
	 *  查询项目执行单审核(non-Javadoc)
	 * @see com.task.Server.kvp.KVPAssessServer#findExamList1(int, int)
	 */
	@Override
	public Object[] findExamList1(int parseInt, int pageSize) {
		/**
		 * false:未审批 true：已审批
		 * 
		 */
		Map<String, Object> map = CircuitRunServerImpl.findAuditExeNode(
				ExecuteKVP.class, false);
		if (map != null) {
			String hql = "from ExecuteKVP where id in (:entityId)";
			List list = totalDao.find(hql, map, parseInt, pageSize);
			for (int i = 0; i < list.size(); i++) {
				//get主表信息，显示kvp评估人
				ExecuteKVP executeKVP = (ExecuteKVP) list.get(i);
				executeKVP.getKvpAssess().getKvp_username();
			}
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
	 * 审核项目执行单(non-Javadoc)
	 * @see com.task.Server.kvp.KVPAssessServer#updateExamExecuteKVP(java.lang.Integer[], java.lang.String)
	 */
	@Override
	public boolean updateExamExecuteKVP(Integer[] detailSelect, String tag) {
		boolean bool = false;
		if (null != detailSelect && detailSelect.length > 0) {
			Users user = Util.getLoginUser();
			for (int i = 0; i < detailSelect.length; i++) {// 遍历处理状态
				ExecuteKVP detail = (ExecuteKVP) totalDao.getObjectById(
						ExecuteKVP.class, detailSelect[i]);
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
	 * 查询所有项目执行单(non-Javadoc)
	 * @see com.task.Server.kvp.KVPAssessServer#findEightBReport(com.task.entity.kvp.ExecuteKVP, int, int, java.lang.String)
	 */
	@Override
	public Object[] findEightBReport(ExecuteKVP executeKVP, int parseInt,
			int pageSize, String tag) {
		if (executeKVP == null) {
			executeKVP = new ExecuteKVP();
		}
		Users loginUser = Util.getLoginUser();// 获得登陆用户
		String hql = totalDao.criteriaQueries(executeKVP, null);
		if(executeKVP.getExecuteNumber()!=null&&!"".equals(executeKVP.getExecuteNumber())){
			hql += " and executeNumber like '%"+executeKVP.getExecuteNumber()+"%'";
		}
		if(executeKVP.getExecuteDate()!=null&&!"".equals(executeKVP.getExecuteDate())){
			hql += " and executeDate = '"+executeKVP.getExecuteDate()+"'";
		}
		if(tag!=null&&"self".equals(tag)){
			hql +="and kvpAssess.kvp_username='"+loginUser.getName()+"'";
		}
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count};
		return o;
	}

	/*
	 * 添加8B报告(non-Javadoc)
	 * @see com.task.Server.kvp.KVPAssessServer#addEightBReport(com.task.entity.kvp.EightBReport)
	 */
	@Override
	public boolean addEightBReport(EightBReport eightBReport,Integer id) {
		boolean bool = false;
		Users loginUser = Util.getLoginUser();// 登陆用户信息
		ExecuteKVP executeKVP  = (ExecuteKVP) this.totalDao.getObjectById(ExecuteKVP.class, id);
		
		EightBReport eightBReport2 = new EightBReport();
		String createdate = Util.getDateTime("yyyy");
		String kvpDate =Util.getDateTime("yyyy-MM-dd");
		eightBReport.setReportdate(kvpDate);
		eightBReport.setSavings(executeKVP.getCostsavings());
		eightBReport.setExecuteKVP(executeKVP);
		
//		eightBReport2.setStatus("未审批");
//		eightBReport2.setFactor(eightBReport.getFactor());
//		eightBReport2.setUnit(eightBReport.getUnit());
//		eightBReport2.setName(eightBReport.getName());
//		eightBReport2.setSummary(eightBReport.getSummary());
//		eightBReport2.setImproveoutcomes(eightBReport.getImproveoutcomes());
//		eightBReport2.setProblemstatement(eightBReport.getProblemstatement());
//		eightBReport2.setGoal(eightBReport.getGoal());
//		eightBReport2.setExecuteKVP(executeKVP);
		try {
			bool = this.totalDao.save(eightBReport);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		if (bool) {
			Integer epId;
			String dept = loginUser.getDept();
			String processName = dept + "8B报告审核";
			try {
				epId = CircuitRunServerImpl.createProcess(processName,
						EightBReport.class,eightBReport.getId(), "status",
						"id","kvpAssessAction_findEightBReportById.action?id="+eightBReport.getId()+"&tag1=1",loginUser.getDept() + "部门的8B报告单请您审核!", true);
				if (epId != null && epId > 0) {
					eightBReport.setEpId(epId);
					eightBReport.setStatus("未审批");
					totalDao.update(eightBReport);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bool;
	}

	/*
	 * 查询8B报告审核(non-Javadoc)
	 * @see com.task.Server.kvp.KVPAssessServer#findExamEightBReport(int, int)
	 */
	@Override
	public Object[] findExamEightBReport(int parseInt, int pageSize) {
		/**
		 * false:未审批 true：已审批
		 * 
		 */
		Map<String, Object> map = CircuitRunServerImpl.findAuditExeNode(
				EightBReport.class, false);
		if (map != null) {
			String hql = "from EightBReport where id in (:entityId)";
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
	 * 8B审核(non-Javadoc)
	 * @see com.task.Server.kvp.KVPAssessServer#updateExamEightBReport(java.lang.Integer[], java.lang.String)
	 */
	@Override
	public boolean updateExamEightBReport(Integer[] detailSelect, String tag) {
		boolean bool = false;
		if (null != detailSelect && detailSelect.length > 0) {
			Users user = Util.getLoginUser();
			for (int i = 0; i < detailSelect.length; i++) {// 遍历处理状态
				EightBReport detail = (EightBReport) totalDao.getObjectById(
						EightBReport.class, detailSelect[i]);
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
	 * 根据编号查询8B报告单(non-Javadoc)
	 * @see com.task.Server.kvp.KVPAssessServer#findEightBReportById(java.lang.Integer)
	 */
	@Override
	public EightBReport findEightBReportById(Integer id) {
		String hql ="from EightBReport where executeKVP.id=?";
		EightBReport eightBReport = (EightBReport) this.totalDao.getObjectByCondition(hql, id);
		return eightBReport;
	}

	/*
	 * 根据id查询项目执行(non-Javadoc)
	 * @see com.task.Server.kvp.KVPAssessServer#findExecuteKVPById1(java.lang.Integer)
	 */
	@Override
	public ExecuteKVP findExecuteKVPById1(Integer id) {
		ExecuteKVP executeKVP = (ExecuteKVP) this.totalDao.getObjectById(ExecuteKVP.class, id);
		return executeKVP;
	}

	/*
	 * 更新8B报告(non-Javadoc)
	 * @see com.task.Server.kvp.KVPAssessServer#updateEightBReport(com.task.entity.kvp.EightBReport)
	 */
	@Override
	public boolean updateEightBReport(EightBReport eightBReport,Float costsavings) {
		// TODO Auto-generated method stub
		boolean bool = false;
		EightBReport eightBReport2 = (EightBReport) this.totalDao.getObjectById(EightBReport.class, eightBReport.getId());
		eightBReport2.setProjectname(eightBReport.getProjectname());
		eightBReport2.setUnit(eightBReport.getUnit());
		eightBReport2.setName(eightBReport.getName());
		eightBReport2.setSummary(eightBReport.getSummary());
		eightBReport2.setImproveoutcomes(eightBReport.getImproveoutcomes());
		eightBReport2.setProblemstatement(eightBReport.getProblemstatement());
		eightBReport2.setGoal(eightBReport.getGoal());
		eightBReport2.setFactor(eightBReport.getFactor());
		bool = this.totalDao.update(eightBReport2);
		if(bool){
			String hql = "from ExecuteKVP where eightBReport.id=?";
			ExecuteKVP executeKVP = (ExecuteKVP) this.totalDao.getObjectByCondition(hql, eightBReport2.getId());
			if(executeKVP!=null){
				executeKVP.setCostsavings((double)costsavings);
				this.totalDao.update(executeKVP);
			}
		}
		if("打回".equals(eightBReport.getStatus())){
			//更新审批状态
			CircuitRunServerImpl.updateCircuitRun(eightBReport2.getEpId());
		}
		return bool;
	}

	/*
	 * 删除项目执行单(non-Javadoc)
	 * @see com.task.Server.kvp.KVPAssessServer#delEightBReport(java.lang.Integer)
	 */
	@Override
	public boolean delEightBReport(Integer id) {
		boolean bool = false;
		ExecuteKVP executeKVP = (ExecuteKVP) this.totalDao.getObjectById(ExecuteKVP.class, id);
//		String hql ="from KVPAssess where executeKVP.id=?";
//		KVPAssess kvpAssess =(KVPAssess) this.totalDao.getObjectByCondition(hql, executeKVP.getId());
		CircuitRun circuitRun = (CircuitRun) this.totalDao.getObjectById(CircuitRun.class, executeKVP.getEpId());
		EightBReport eightBReport = executeKVP.getEightBReport();
		if(eightBReport!=null){
			eightBReport.setExecuteKVP(executeKVP);
			CircuitRun circuitRun1 = (CircuitRun) this.totalDao.getObjectById(CircuitRun.class, eightBReport.getEpId());
			bool = this.totalDao.delete(circuitRun1);
		}
		try {
			if(circuitRun!=null){
				bool = this.totalDao.delete(circuitRun);
			}
			executeKVP.setKvpAssess(null);
			bool = this.totalDao.delete(executeKVP);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bool;
		
	}

	 

	
	
	
}
