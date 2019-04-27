package com.task.ServerImpl.setupcheck;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.task.Dao.TotalDao;
import com.task.Server.setupcheck.SetupCheckServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.entity.Users;
import com.task.entity.setupcheck.SetupCheck;
import com.task.entity.setupcheck.TrackRecord;
import com.task.util.RtxUtil;
import com.task.util.Upload;
import com.task.util.Util;

public class SetupCheckServerImpl implements SetupCheckServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public String findId(String id) {
		String hql = "from Users where code='" + id + "'";
		Users u = (Users) totalDao.getObjectByCondition(hql);
		String i = u.getName();
		return i;
	}

	@Override
	public boolean save(SetupCheck setupCheck) {
		if(totalDao.save(setupCheck)){
			String allId =setupCheck.getAllId();
			String[] allIda = allId.split(";");
			for(String a :allIda){
				AlertMessagesServerImpl.addAlertMessages("体系审核(负责人)",
						"您有新的体系审核需要处理," + setupCheck.getDescription(), "1",
						a.toString());
			}
			String allId1 =setupCheck.getAllId1();
			String[] allIda1 = allId1.split(";");
			for(String b :allIda1){
				AlertMessagesServerImpl.addAlertMessages("体系审核(负责人)",
						"您有新的体系审核需要处理," + setupCheck.getDescription(), "1",
						b.toString());
			}
			return true;
		}else{
			return false;
		}

	}
	public boolean save(TrackRecord trackRecord) {
		Users user = Util.getLoginUser();
		trackRecord.setPerson(user.getName());
		trackRecord.setCode(user.getCode());
		trackRecord.setDate(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
		return totalDao.save(trackRecord);
	}

	@Override
	public boolean delete(SetupCheck setupCheck) {
		if (totalDao.delete(setupCheck)) {
			return true;
		}
		return false;
	}

	@Override
	public SetupCheck findById(Integer id) {
		return (SetupCheck) totalDao.getObjectById(SetupCheck.class, id);
	}
	
	public String updateGenzong(Integer id){
		SetupCheck s = (SetupCheck) totalDao.getObjectById(SetupCheck.class, id);
		 String name =Util.getLoginUser().getName();
		 String onePerson = s.getUploadPerson();//第一负责人
		 String[] onePersons = onePerson.split(";");
		 String twoPerson = s.getUploadPerson1();//第二负责人
		 String[] twoPersons = twoPerson.split(";");
		 List<String> list = new ArrayList<String>(Arrays.asList(onePersons));
	     list.addAll(Arrays.asList(twoPersons));
	     for(String a:list){
    		 if(name.equals(a)){
    			 s.setStauts("追踪");
    			 return "true";
    		 }
    	 }
    	 return "负责人才能开启追踪";
	}
	public String findSamename(Integer id){
		 SetupCheck s = (SetupCheck) totalDao.getObjectById(SetupCheck.class, id);
		 String name =Util.getLoginUser().getName();
		 String onePerson = s.getUploadPerson();//第一负责人
		 String[] onePersons = onePerson.split(";");
		 String twoPerson = s.getUploadPerson1();//第二负责人
		 String[] twoPersons = twoPerson.split(";");
		 List<String> list = new ArrayList<String>(Arrays.asList(onePersons));
	     list.addAll(Arrays.asList(twoPersons));
	     if(s.getLongFile()==null){
	    	 return "确认失败，无措施文件";
	     }else{
	    	 for(String a:list){
	    		 if(name.equals(a)){
	    			 return"true";
	    		 }
	    	 }
	    	 return "负责人确认";
	     }
	}
	
	@Override
	public boolean update(SetupCheck setupCheck) {
		if (setupCheck.getStauts().equals("已确认")) {
			String allId =setupCheck.getAllId();
			String[] allIda = allId.split(";");
			for(String a :allIda){
				AlertMessagesServerImpl.addAlertMessages("体系审核(负责人)", "您的体系审核已经关闭,"
						+ setupCheck.getDescription(), "1", a
						.toString());
			}
			String allId1 =setupCheck.getAllId1();
			String[] allIda1 = allId1.split(";");
			for(String b :allIda1){
				AlertMessagesServerImpl.addAlertMessages("体系审核(负责人)", "您的体系审核已经关闭,"
						+ setupCheck.getDescription(), "1", b
						.toString());
			}
		} else if (setupCheck.getStauts().equals("打回")) {
			String allId =setupCheck.getAllId();
			String[] allIda = allId.split(";");
			for(String a :allIda){
				AlertMessagesServerImpl.addAlertMessages("体系审核(负责人)",
						"您的体系审核措施被打回," + setupCheck.getDescription(), "1",
						a.toString());
			}
			String allId1 =setupCheck.getAllId1();
			String[] allIda1 = allId1.split(";");
			for(String b :allIda1){
				AlertMessagesServerImpl.addAlertMessages("体系审核(负责人)",
						"您的体系审核措施被打回," + setupCheck.getDescription(), "1",
						b.toString());
			}
		}
		return totalDao.update(setupCheck);

	}

	@Override
	public Map<Integer, Object> findAll(SetupCheck setupCheck, int pageNo,
			int pageSize) {
		if (setupCheck == null) {
			setupCheck = new SetupCheck();
		}
		String hql = totalDao.criteriaQueries(setupCheck, null);
		hql += " order by id desc";
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	//
	public List<SetupCheck> findWaitDeal(SetupCheck setupCheck, String fStatus) {
		List<SetupCheck> list = new ArrayList<SetupCheck>();
		if (setupCheck == null) {
			setupCheck = new SetupCheck();
		}
		String sql = "stauts ='" + fStatus + "' order by id desc";
		String hql = totalDao.criteriaQueries(setupCheck, sql, null);
		list = totalDao.find(hql);
		return list;
	}

	@Override
	public boolean update1(SetupCheck setupCheck) {
		if (setupCheck != null && setupCheck.getId() != null) {
			Upload u = new Upload();
			String uploadPath = "/upload/file/setupCheck";
			File file1 = new File(uploadPath);
			if (!file1.exists()) {
				file1.mkdirs();// 如果不存在文件夹就创建
			}
			SetupCheck old = findById(setupCheck.getId());
			if (old != null) {
				if (setupCheck.getJiuzhenF()!=null&&!"".equals(setupCheck.getJiuzhenFFileName())) {
					old.setJiuzhenFile(u.UploadFile(setupCheck.getJiuzhenF(), setupCheck.getJiuzhenFFileName(),null,uploadPath,null));
				} if(setupCheck.getYufangF()!=null&&!"".equals(setupCheck.getYufangFFileName())){
					old.setYufangFile(u.UploadFile(setupCheck.getYufangF(), setupCheck.getYufangFFileName(),null,uploadPath,null));
				}
				old.setReason(setupCheck.getReason());
				old.setShortWay(setupCheck.getShortWay());
				old.setShortPerson(setupCheck.getShortPerson());
				old.setShortTime(setupCheck.getShortTime());
				old.setLongWay(setupCheck.getLongWay());
				old.setLongPerson(setupCheck.getLongPerson());
				old.setLongTime(setupCheck.getLongTime());
				old.setShortId(setupCheck.getShortId());
				old.setLongId(setupCheck.getLongId());
				if (old.getStauts().equals("打回")) {
					old.setStauts("待确认");
				} else {
					old.setStauts("处理中");
				}
			}
			Users loginUser = Util.getLoginUser();
			RtxUtil.sendNotify(setupCheck.getShortId(), "您有新的体系审核需要处理:"
					+ setupCheck.getShortWay(), loginUser.getName()
					+ "给您发送了一条提醒消息:", "0", "0");
			RtxUtil.sendNotify(setupCheck.getLongId(), "您有新的体系审核需要处理:"
					+ setupCheck.getShortWay(), loginUser.getName()
					+ "给您发送了一条提醒消息:", "0", "0");
			return totalDao.update(old);
		}
		return false;
	}

	public boolean update2(SetupCheck setupCheck) {
		if (setupCheck != null && setupCheck.getId() != null) {
			SetupCheck old = findById(setupCheck.getId());
			if (old != null) {
				if (setupCheck.getLongFile() != null) {
					old.setLongFile(setupCheck.getLongFile());
					old.setStauts("待确认");
				}
			}

		}
		return false;
	}

	public Map<Integer, Object> findAllByUser(SetupCheck setupCheck,
			int pageNo, int pageSize) {
		if (setupCheck == null) {
			setupCheck = new SetupCheck();
		}
		Users user = Util.getLoginUser();
		String sql = "uploadPerson like'%" + user.getName() + "%'or shortPerson like'%"
				+ user.getName() + "%'or longPerson='" + user.getName() + "'or uploadPerson1 ='"+user.getName() + "'";
		String hql = totalDao.criteriaQueries(setupCheck, sql, null);
		hql += " order by id desc";
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}
	public Map<Integer, Object> findAllForZhuizong(SetupCheck setupCheck,
			int pageNo, int pageSize) {
		if (setupCheck == null) {
			setupCheck = new SetupCheck();
		}
		String sql ="stauts ='追踪'";
		String hql = totalDao.criteriaQueries(setupCheck, sql, null);
		hql += " order by id desc";
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}
	public List<TrackRecord> findtrackRecordByid(Integer id){
		if(id!=null&&!"".equals(id)){
			String hql = "from TrackRecord where setupCheckId=?";
			List<TrackRecord> trackRecordList =(List<TrackRecord>)totalDao.query(hql,id);
			return trackRecordList;
		}else{
			return null;
		}
	}
}
