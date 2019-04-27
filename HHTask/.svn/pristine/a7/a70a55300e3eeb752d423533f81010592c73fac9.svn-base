package com.task.ServerImpl.menjin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.menjin.JLMApplicationServer;
import com.task.ServerImpl.AttendanceTowServerImpl;
import com.task.ServerImpl.DownloadServerImpl;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Users;
import com.task.entity.WarehouseNumber;
import com.task.entity.android.pscs.Customer;
import com.task.entity.caiwu.noncore.PayableType;
import com.task.entity.menjin.AccessEquipment;
import com.task.entity.menjin.DoorType;
import com.task.entity.menjin.JLMApplication;
import com.task.entity.menjin.Operation;
import com.task.entity.onemark.OneLight;
import com.task.entity.parking.ParkSpaceUseInfor;
import com.task.entity.renshi.DimissionLog;
import com.task.entity.renshi.Inter_Family;
import com.task.util.Util;

public class JLMApplicationServerImpl implements JLMApplicationServer {
	private TotalDao totalDao;

	@Override
	public String addJLMApplication(JLMApplication jLMApplication,
			List<Operation> operation) {
		// TODO Auto-generated method stub
		if (jLMApplication != null) {
			Users users = Util.getLoginUser();
			jLMApplication.setProposer_id(users.getId() + "");
			jLMApplication.setProposer_name(users.getName());
			jLMApplication.setProposer_dept(users.getDept());
			jLMApplication.setAddTime(Util.getDateTime());
			jLMApplication.setStatus("未审批");
			if (operation!=null&&operation.size()>0) {
				for (Operation operations : operation) {
					if (operations!=null && operations.getDoorId()!= null) {
						AccessEquipment accessEquipment = (AccessEquipment) totalDao
						.getObjectById(AccessEquipment.class, operations
								.getDoorId());
						if(accessEquipment!=null){
							if (jLMApplication.getDoorName()==null){
								jLMApplication.setDoorName(accessEquipment.getEquipmentName());
							}else{
								jLMApplication.setDoorName(jLMApplication.getDoorName()+","+accessEquipment.getEquipmentName());
							}
							long stime = Util.getWorkTime1(operations.getStartTime()+":00", operations.getEndTime()+":00")/1000;
							int timeint = (int) stime;
							if(stime<=24*60*60){
								operations.setDoorId(accessEquipment.getId());
								operations.setDoorIP(accessEquipment.getEquipmentIP());
								operations.setDoorPort(accessEquipment.getEquipmentPort());
								operations.setJlmapplication(jLMApplication);
								operations.setDoorName(accessEquipment.getEquipmentName());
								operations.setStatus("未使用");
								operations.setTimeInt(timeint);
								totalDao.save(operations);
							}
						}else{
							continue;
						}
					}
				}
			}else {
				return "申请失败,请选择门！";
			}
			if(totalDao.save(jLMApplication)){
				// 调用离职申请审批流程
				String processName = "卷帘门申请";
				Integer epId = null;
				try {
					epId = CircuitRunServerImpl.createProcess(jLMApplication.getReason()+processName,
							JLMApplication.class, jLMApplication.getId(),
							"status", "id",
							"JLMApplicationAction_showList_o.action?ta_jlm_operation="+jLMApplication.getId()+"&tag=no", jLMApplication
									.getProposer_name()
									+ " 卷帘门申请，请您审批！", true);
					if (epId != null && epId > 0) {
						jLMApplication.setEpId(epId);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "申请成功!";
			}
		}
		return "申请失败！";
	}
	//Android 提交申请
	public String addandroid(JLMApplication jLMApplication,List<Operation> operation) {
//		addJLMApplication(jLMApplication, operation);
//		System.out.println("Android提交"
//				+"卡号："+jLMApplication.getProposer_id()
//				+"原因："+jLMApplication.getReason()
//				+"门ID："+operation.size());
		if (jLMApplication.getProposer_id() != null) {
			Users users = (Users) totalDao.getObjectByCondition("from Users where cardId = ? ", jLMApplication.getProposer_id());
			// 保存用户到session中
			ActionContext.getContext().getSession().put(
					TotalDao.users, users);//讲请假人临时存入Session
			jLMApplication.setProposer_id(users.getId() + "");
			jLMApplication.setProposer_name(users.getName());
			jLMApplication.setProposer_dept(users.getDept());
			jLMApplication.setAddTime(Util.getDateTime());
			jLMApplication.setStatus("未审批");
			if (jLMApplication.getReason().equals("1")) {
				jLMApplication.setReason("取/送货");
			}else if (jLMApplication.getReason().equals("2")) {
				jLMApplication.setReason("设备修理");
			}else if (jLMApplication.getReason().equals("3")) {
				jLMApplication.setReason("紧急");
			}
			if (operation!=null&&operation.size()>0) {
				for (Operation operations : operation) {
					if (operations!=null && operations.getDoorId()!= null) {
						AccessEquipment accessEquipment = (AccessEquipment) totalDao
						.getObjectById(AccessEquipment.class, operations
								.getDoorId());
						if(accessEquipment!=null){
							if (jLMApplication.getDoorName()==null){
								jLMApplication.setDoorName(accessEquipment.getEquipmentName());
							}else{
								jLMApplication.setDoorName(jLMApplication.getDoorName()+","+accessEquipment.getEquipmentName());
							}
							long stime = Util.getWorkTime1(operations.getStartTime()+":00", operations.getEndTime()+":00")/1000;
							int timeint = (int) stime;
							if(stime<=24*60*60){
								operations.setDoorId(accessEquipment.getId());
								operations.setDoorIP(accessEquipment.getEquipmentIP());
								operations.setDoorPort(accessEquipment.getEquipmentPort());
								operations.setJlmapplication(jLMApplication);
								operations.setDoorName(accessEquipment.getEquipmentName());
								operations.setStatus("未使用");
								operations.setTimeInt(timeint);
								totalDao.save(operations);
							}
						}else{
							continue;
						}
					}
				}
			}else {
				return "申请失败,请选择门！";
			}
			if(totalDao.save(jLMApplication)){
				String processName = "卷帘门申请";
				Integer epId = null;
				try {
					epId = CircuitRunServerImpl.createProcess(jLMApplication.getReason()+processName,
							JLMApplication.class, jLMApplication.getId(),
							"status", "id",
							"JLMApplicationAction_showList_o.action?ta_jlm_operation="+jLMApplication.getId()+"&tag=no", jLMApplication
									.getProposer_name()
									+ " 卷帘门申请，请您审批！", true);
					if (epId != null && epId > 0) {
						jLMApplication.setEpId(epId);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "申请成功!";
			}
		}
		return "申请失败！";
	}
	
	// 手机端开关门指令
	public String ColseDoorById(Integer id,String doorIp,String doorPort) {
		if(id!=null && id>0){
			Operation op = (Operation) totalDao.getObjectById(Operation.class, id);
			if(op==null) return "数据为空";
			Users users = Util.getLoginUser();
			AccessEquipment ae = (AccessEquipment) totalDao.getObjectById(AccessEquipment.class, op.getDoorId());
			Integer dport = Integer.parseInt(op.getDoorPort());
			String message = Util.OpenDoor(op.getDoorIP(), dport, 26);//关门操作
			if ("true".equals(message)) {
				op.setStatus("已关闭");
				op.setCloseTime(Util.getDateTime());
				if(ae!=null){
					if (users == null ) {
						ae.setOperationNote(ae.getOperationNote()+"<br/>"+op.getJlmapplication().getProposer_name()+"，关门，"+Util.getDateTime());
					}else{
						ae.setOperationNote(ae.getOperationNote()+"<br/>"+users.getName()+"，关门，"+Util.getDateTime());
					}
					ae.setState("关闭");
					totalDao.update(ae);
				}
				if(totalDao.update(op)){//更新库位状态
				}else totalDao.update2(op);
				return "true";
			}
			return "连接异常，关门失败！";
		}
		return "未获取到数据";
	}

	public String OpenDoorById(Integer id,String doorIp,String doorPort) {
		if(id!=null && id>0){
			Users users = Util.getLoginUser();
			Operation op = (Operation) totalDao.getObjectById(Operation.class, id);
			if(op==null) return "数据为空";
			AccessEquipment ae = (AccessEquipment) totalDao.getObjectById(AccessEquipment.class, op.getDoorId());
			Integer dport = Integer.parseInt(op.getDoorPort());
			String message = Util.OpenDoor(op.getDoorIP(), dport, 10);//开门操作
			if ("true".equals(message)) {
				op.setStatus("已开门");
				op.setOpenTime(Util.getDateTime());
				if(ae!=null){
					if (users == null ) {
						ae.setOperationNote(op.getJlmapplication().getProposer_name()+"，开门，"+Util.getDateTime());
					}else{
						ae.setOperationNote(users.getName()+"，开门，"+Util.getDateTime());
					}
					ae.setState("打开");
					totalDao.update(ae);
				}
				if(totalDao.update(op)){//更新库位状态
				}else totalDao.update2(op);
				return "true";
			}else {
				return "连接异常，开门失败！";
			}
		}
		return "未获取到数据";
	}

	@Override
	public JLMApplication byIdJLMApplication(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteJLMApplication(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, Object> findJLMApplication(
			JLMApplication jLMApplication, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateJLMApplication(JLMApplication jLMApplication) {
		// TODO Auto-generated method stub
		return null;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public List findAccessE() {
		// TODO Auto-generated method stub
		return totalDao
				.query("from AccessEquipment where equipmentDaoType = '卷闸门'");
	}
	
	@Override
	public List findAccessE(String Type) {
		// TODO Auto-generated method stub
		return totalDao
				.query("from AccessEquipment where equipmentDaoType = '卷闸门' and equipmentName = '东二门'");
	}
	
	public List selectJlmApplication(String proposer_id) {
		// TODO Auto-generated method stub
		return totalDao.query("from JLMApplication where proposer_id= ? and id in " +
				"(select jlmapplication.id from Operation where endTime > ? )",proposer_id,Util.getDateTime()); 
	}
	public List selectOperation(Integer ta_jlm_operation) {
		// TODO Auto-generated method stub
		return totalDao.query("from Operation where jlmapplication.id= ?",ta_jlm_operation);
	}
	//显示所有申请记录
	/**
	 * 分页查询门禁设备记录/
	 * 
	 * @return
	 */
	@Override
	public Map<Integer, Object> findJLMApplicationByCondition(
			JLMApplication jlmApplication, int pageNo, int pageSize,
			String tag) {
		// TODO Auto-generated method stub
		String sql = "";
		if ("code".equals(tag)) {//
			Users users = Util.getLoginUser();
			sql += " proposer_id = '" + users.getId() + "'";
		}else if ("aD".equals(tag)) {//
			Users users = Util.getLoginUser();
			sql += " proposer_id = '" + users.getId() + "'"
			+"and id in(select jlmapplication.id from Operation where endTime >'" +
			Util.getDateTime()+"')";
		}
		if (jlmApplication == null) {
			jlmApplication = new JLMApplication();
		}
		String hql = totalDao.criteriaQueries(jlmApplication, sql);
		hql += " order by id desc";
		System.out.println(hql);
		List listi = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listi);
		map.put(2, count);
		return map;
	}
	@Override
	public List findDoorType() {
		// TODO Auto-generated method stub
		return totalDao.query("from DoorType order by id desc");
	}
	//
	@Override
	public String saveDoorType(DoorType type) {
		// TODO Auto-generated method stub
		if(type!=null&&type.getType()!=null&&!"".equals(type.getType())){
			DoorType doorType = (DoorType) totalDao.getObjectByCondition("from DoorType where type = ?", type.getType());
			if(doorType==null){
				if(totalDao.save(type)){
					return "添加成功！";
				}
			}else {
				return "已有类型，请勿重新添加！";
			}
		}
		return "添加失败!";
	}
	@Override
	public String deleteEner(Integer id) {
		// TODO Auto-generated method stub
		DoorType en = getDoorTypeid(id);
		if(en!=null){
			if(totalDao.delete(en)){
				return "删除成功！";
			}
		}
		return "删除失败！";
	}
	
	public DoorType getDoorTypeid(Integer id) {
		// TODO Auto-generated method stub
		return (DoorType) totalDao.getObjectById(DoorType.class, id);
	}
	@Override
	public String findPayableType() {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();
		List<String> list = totalDao.query("select distinct(type) from DoorType");
		for (String d : list) {
			if (d!=null) {
				buffer.append(d.toString()+"|");
			}
		}
		return buffer.toString();
	}
	/**
	 * 分页查询门禁设备类型/
	 * 
	 * @return
	 */
	@Override 
	public Map<Integer, Object> finddoortype(
			DoorType doorType, int pageNo, int pageSize,
			String tag) {
		// TODO Auto-generated method stub
		String sql = "";
		if ("code".equals(tag)) {//
			Users users = Util.getLoginUser();
			sql += " proposer_id = '" + users.getId() + "'";
		}else if ("aD".equals(tag)) {//
			Users users = Util.getLoginUser();
			sql += " proposer_id = '" + users.getId() + "'"
			+"and id in(select jlmapplication.id from Operation where endTime >'" +
			Util.getDateTime()+"')";
		}
		if (doorType == null) {
			doorType = new DoorType();
		}
		String hql = totalDao.criteriaQueries(doorType, sql);
		hql += " order by id desc";
		System.out.println(hql);
		List listi = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listi);
		map.put(2, count);
		return map;
	}
}
