package com.task.ServerImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import com.task.Dao.TotalDao;
import com.task.DaoImpl.TotalDaoImpl;
import com.task.Server.UserFacialInforServer;
import com.task.entity.DetectResult;
import com.task.entity.UserFacialFeatures;
import com.task.entity.UserFacialInfor;
import com.task.entity.Users;
import com.task.entity.UsersHSKao;
import com.task.entity.menjin.AccessEquipment;
import com.task.servlet.JDBCUtilHS;
import com.task.servlet.JDBCUtils;
import com.task.util.FeatureCompareUtil;
import com.task.util.MyUtil;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class UserFacialInforServerImpl implements UserFacialInforServer {
	public static List<UserFacialFeatures> fF;
	
	private TotalDao totalDao;


	public TotalDao getTotalDao() {
		return totalDao;
	}
	
	// 在静态方法调用totalDao
	private static TotalDao createTotol() {
		// 获得totalDao
		TotalDao totalDao = TotalDaoImpl.findTotalDao();
		UserFacialInforServerImpl acc = new UserFacialInforServerImpl();
		acc.setTotalDao(totalDao);
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public void addUser() {
		// TODO Auto-generated method stub
		List<Users> list = totalDao.query("from Users where onWork not in ('离职','内退','病休') and dept = '信息' and code <> '' and code is not null");
		if(list!=null&&list.size()>0){
			for (Users users : list) {
				addUserFacialInfor(users);
			}
		}
	}

	public static String addUserFacialInfor(Users users) {
		// TODO Auto-generated method stub
		TotalDao totalDao = createTotol();
		UserFacialInfor facialInfor = (UserFacialInfor) totalDao.getObjectByCondition("from UserFacialInfor where userNo = ?", users.getCode());
		if(facialInfor==null){
			facialInfor = new UserFacialInfor();
			facialInfor.setId(users.getId());
			facialInfor.setUserNo(users.getCode());
			facialInfor.setUserName(users.getName());
			String pass = (String) totalDao.getObjectByCondition("select realPass from PassReal where uid = ?", users.getId());
			if(pass!=null){
				facialInfor.setUserPassword(pass);
			}else {
				facialInfor.setUserPassword("111111");
			}
			facialInfor.setAddTime(Util.getDateTime());
			totalDao.save(facialInfor);
		}else {
			facialInfor.setUserNo(users.getCode());
			facialInfor.setUserName(users.getName());
			String pass = (String) totalDao.getObjectByCondition("select realPass from PassReal where uid = ?", users.getId());
			if(pass!=null){
				facialInfor.setUserPassword(pass);
			}else {
				facialInfor.setUserPassword("111111");
			}
			totalDao.update(facialInfor);
		}
		List<AccessEquipment> accessEquipments = totalDao.query("from AccessEquipment where equipmentDaoType = '面部识别'");
		if(accessEquipments!=null&&accessEquipments.size()>0){
//			for (AccessEquipment acE : accessEquipments) {
//				Set<AccessEquipment> Infor = new HashSet<AccessEquipment>();
//				Infor =	facialInfor.getAccessEquipments();
//				Infor.add(acE);
//				facialInfor.setAccessEquipments(Infor);
//				Set<UserFacialInfor> user = new HashSet<UserFacialInfor>();
//				user = acE.getFacialInfors();
//				user.add(facialInfor);
//				acE.setFacialInfors(user);
////				totalDao.update(facialInfor);
//				totalDao.update(acE);
//			}
		}
		return "true";
	}

	@Override
	public Object[] findUserFacialInfor(UserFacialInfor infor,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (infor == null) {
			infor = new UserFacialInfor();
		}
		String hql = totalDao.criteriaQueries(infor, null);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);// 总条数
		Object [] o = {list ,count};
		return o;
	}

	@Override
	public String updateUserFacialInfor(Users users) {
		// TODO Auto-generated method stub
		return "true";
	}

	@Override
	public UserFacialInfor ByCodeUserFacialInfor(String code) {
		// TODO Auto-generated method stub
		if(code==null||"".equals(code)) return null;
		return (UserFacialInfor) totalDao.getObjectByCondition("from UserFacialInfor where userNo = ?", code);
	}
	
	@Override
	public UserFacialInfor ByCodeUserFacialInfor(Integer id) {
		// TODO Auto-generated method stub
		if(id==null) return null;
		return (UserFacialInfor) totalDao.getObjectById(UserFacialInfor.class, id);
	}

	//如果没有就添加
	@Override
	public String selectBinAdd(String code){
		Users users = (Users) totalDao.getObjectByCondition("from Users where code = ?", code);
		if(users!=null){
			addUserFacialInfor(users);
		}else {
			return "用户不存在!";
		}
		return "true";
	}

	
	
	
	@Override
	public String addUserFacialFeatures(UserFacialFeatures facialFeatures) {
		// TODO Auto-generated method stub
		if(facialFeatures!=null){
			UserFacialInfor infor = ByCodeUserFacialInfor(facialFeatures.getUserNo());
			if(infor!=null){
				String[] features = facialFeatures.getUserFeatures().split("\\|");
				if(features!=null&&features.length>0){
					for (String ms : features) {
						int tt = totalDao.getCount("from UserFacialFeatures where userNo = ? and userFeatures like '%"+facialFeatures.getUserFeatures()+"%'", facialFeatures.getUserNo());
						if(tt > 0) continue;
						UserFacialFeatures facial = new UserFacialFeatures();
						facial.setAddTime(Util.getDateTime());
						facial.setUserFeatures(ms);
						facial.setUserNo(infor.getUserNo());
						facial.setUserName(infor.getUserName());
						totalDao.save2(facial);
//						List<AccessEquipment> accessEquipments = totalDao.query("from AccessEquipment where equipmentDaoType = '面部识别'");
//						if(!accessEquipments.isEmpty()){
//							for (AccessEquipment acE : accessEquipments) {
////								Set<AccessEquipment> Infor = new HashSet<AccessEquipment>();
////								Infor =	facial.getAccessEquipments();
////								Infor.add(acE);
////								facial.setAccessEquipments(Infor);
//								Set<UserFacialFeatures> facialF = new HashSet<UserFacialFeatures>();
//								List<UserFacialFeatures> ff = totalDao.query("from UserFacialFeatures where userNo = ?", infor.getUserNo());
//								for (UserFacialFeatures userFF : ff) {
//									Set<AccessEquipment> Infors = new HashSet<AccessEquipment>();
//									if(userFF.getAccessEquipments()!=null&&userFF.getAccessEquipments().size()>0){
//										Infors = userFF.getAccessEquipments();
//									}
//									Infors.add(acE);
//									userFF.setAccessEquipments(Infors);
//								}
//								facialF.addAll(ff);
//								acE.setFacialFeatures(facialF);
//								totalDao.update(acE);
//							}
//						}
					}
					List<UserFacialFeatures> ff = totalDao.query("from UserFacialFeatures");
					for (UserFacialFeatures userFacialFeatures : ff) {
						FeatureCompareUtil.dataStrToDoubleArray(userFacialFeatures, userFacialFeatures.getUserFeatures());
					}
					UserFacialInforServerImpl.fF = ff;
				}
			}else {
				return "用户不存在，添加失败！";
			}
		}
		return "true";
	}

	@Override
	public UserFacialFeatures byIdUserFacialFeatures(Integer id) {
		// TODO Auto-generated method stub
		return (UserFacialFeatures) totalDao.getObjectById(UserFacialFeatures.class, id);
	}

	@Override
	public String deleteUserFacialFeatures(Integer id) {
		// TODO Auto-generated method stub
		UserFacialFeatures obje = byIdUserFacialFeatures(id);
		if (obje != null) {
			Set<AccessEquipment> ace = new HashSet<AccessEquipment>();
			ace = obje.getAccessEquipments();
			if(!ace.isEmpty()){
				for (AccessEquipment accessEquipment : ace) {
					Set<UserFacialFeatures> facialF = new HashSet<UserFacialFeatures>();
					facialF = accessEquipment.getFacialFeatures();
					facialF.remove(obje);
					accessEquipment.setFacialFeatures(facialF);
					totalDao.update(accessEquipment);
				}
			}
			obje.setAccessEquipments(null);
			if (totalDao.delete(obje)) return "删除成功！";
			else return "删除失败！";
		}
		return "对象为空，删除失败！";
	}

	@Override
	public Object[] findUserFacialFeatures(UserFacialFeatures facialFeatures,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (facialFeatures == null) {
			facialFeatures = new UserFacialFeatures();
		}
		String hql = totalDao.criteriaQueries(facialFeatures, null);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);// 总条数
		Object [] o = {list ,count};
		return o;
	}

	@Override
	public String updateUserFacialFeatures(UserFacialFeatures facialFeatures) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addAttendanceInfor(String userNo, String openDoorType,
			String equipmentId) {
		// TODO Auto-generated method stub
		if(userNo==null||"".equals(userNo)) return "工号为空!";
		Users us = (Users) totalDao.getObjectByCondition("from Users where code = ?", userNo);
		if(us!=null){
			return AttendanceTowServerImpl.addAttendanceTow(us.getCardId(), userNo, us.getName(), us.getDept(),
					us.getId(), "正常", equipmentId, openDoorType, null);
		}
		return null;
	}

	@Override
	public Map<String, Object> pindList(String aceId, String type) {
		// TODO Auto-generated method stub
		if(aceId==null||"".equals(aceId)) return null;
		Map<String, Object> map = new HashMap<String, Object>();
		AccessEquipment accessEqu = (AccessEquipment) totalDao.getObjectByCondition("" +
				"from AccessEquipment where equipmentDaoType = '面部识别' and equipmentNum = ?",aceId);
		if(accessEqu != null){
			if("s".equals(type)){
				map.put("msg", "获取成功");
				map.put("result", true);
				map.put("loginType", "8c160649-4333-44db-bfa9-6fdf8992eccf");
				List<UserFacialInfor> infors = totalDao.query("from UserFacialInfor where id in (select u.id from UserFacialInfor u join u.accessEquipments a where a.id = ?)",accessEqu.getId());// 
				map.put("users", infors);
				List<String> list = totalDao.query("select distinct userNo from UserFacialFeatures where id in (select u.id from UserFacialFeatures u join u.accessEquipments a where a.id = ?)",accessEqu.getId());
				map.put("deleteFacials", list);
				List<UserFacialFeatures> features = totalDao.query("from UserFacialFeatures where id in (select u.id from UserFacialFeatures u join u.accessEquipments a where a.id = ?)",accessEqu.getId());
				map.put("features", features);
			}else if("d".equals(type)){
				map.put("msg", "删除成功");
				map.put("result", true);
				List<UserFacialInfor> infors = totalDao.query("from UserFacialInfor where id in (select u.id from UserFacialInfor u join u.accessEquipments a where a.id = ?)",accessEqu.getId());// 
				for (UserFacialInfor userFacialInfor : infors) {
					Set<AccessEquipment> equipments = userFacialInfor.getAccessEquipments();
					equipments.remove(accessEqu);
					userFacialInfor.setAccessEquipments(equipments);
					totalDao.update(userFacialInfor);
				}
				List<UserFacialFeatures> features = totalDao.query("from UserFacialFeatures where id in (select u.id from UserFacialFeatures u join u.accessEquipments a where a.id = ?)",accessEqu.getId());
				for (UserFacialFeatures userFacialInfor : features) {
					Set<AccessEquipment> equipments = userFacialInfor.getAccessEquipments();
					equipments.remove(accessEqu);
					userFacialInfor.setAccessEquipments(equipments);
					totalDao.update2(userFacialInfor);
				}
//				accessEqu.setFacialFeatures(null);
//				accessEqu.setFacialInfors(null);
//				totalDao.update(accessEqu);
			}else {
				map.put("result", false);
				map.put("msg", "类型不能为空");
			}
		}else {
			map.put("result", false);
			map.put("msg", "设备不存在，请添加。");
		}
		return map;
	}

	@Override
	public String deleteUserFacialInfor(Integer id) {
		// TODO Auto-generated method stub
		UserFacialInfor obje = ByCodeUserFacialInfor(id);
		if (obje != null) {
			if (totalDao.delete(obje)) return "删除成功！";
			else return "删除失败！";
		}
		return "对象为空，删除失败！";
	}

	@Override
	public UserFacialFeatures addAttendanceInfor(String userFeatures, String equipmentId) {
		// TODO Auto-generated method stub
		if(userFeatures==null||"".equals(userFeatures)) return null;
		if(fF!=null){
			DetectResult detectResult = FeatureCompareUtil.isCompareFeatureresult(fF, userFeatures);
			if(detectResult.getResult()){
//				UserFacialFeatures features = (UserFacialFeatures) totalDao.getObjectById(UserFacialFeatures.class, detectResult.getUserFeatureIndex());
				return fF.get(detectResult.getUserFeatureIndex());
//				AttendanceTowServerImpl.addAttendanceTow(us.getCardId(), userNo, us.getName(), us.getDept(),
//						us.getId(), "正常", equipmentId, openDoorType, null);
			}
		}
		return null;
	}

	@Override
	public void addHSUser() {
		// TODO Auto-generated method stub
		List<Users> list = totalDao.query("from Users where onWork not in ('离职','内退','病休','退休') and dept not in ('供应商','客户') and name in ('') and code is not null");
		if(list!=null&&list.size()>0){
			for (Users users : list) {
//				users.setUser_privilege("MANAGER");
				addUserCode(users);
			}
		}
	}
	
	/**
	 * 
	 * @param users
	 * @return
	 */
	public static String addUserCode(Users users) {
		TotalDao totalDao = createTotol();
		int i = totalDao.getCount("from UsersHSKao where code = ?", users.getCode());
//		byte [] hs1 = CreateBSCommBufferFromString(null);
		if(i==0){
			UsersHSKao hsKao = newUserHSkao(users);
			if(totalDao.save(hsKao)){
				byte [] hs = CreateBSCommBufferFromString(hsKao);
//				String dongtai = "FKDATAHS100";
//				addDongtai(totalDao, hsKao, hs, dongtai);
				String dongtai1 = "FKDataHS101";
				addDongtai(totalDao, hsKao, hs, dongtai1);
			}
		}else {
			updateHhKao(users, totalDao);
		}
		return "true";
	}

	private static UsersHSKao newUserHSkao(Users users) {
		UsersHSKao hsKao = new UsersHSKao();
		hsKao.setAddTime(Util.getDateTime());
		hsKao.setUserId(users.getId());
		hsKao.setHscode(addUserHscode());
		hsKao.setCode(users.getCode());
		hsKao.setStatus(0);
		hsKao.setSendStatus(1);
		hsKao.setSendStatus2(1);
		hsKao.setName(users.getName());
		hsKao.setUser_privilege(users.getUser_privilege()==null ? "USER":users.getUser_privilege());
		//3 - DevName=MOOM, DevTime=20180304000227, DevInfo={"firmware_filename":"Fc601HS","supported_enroll_data":["FACE","FP","PASSWORD","IDCARD"],"fk_bin_data_lib":"FKDATAHS100","face_data_ver":100,"firmware":"Fc601HS","fp_data_ver":112}
		return hsKao;
	}

	private static void updateHhKao(Users users, TotalDao totalDao) {
		UsersHSKao hsKao = (UsersHSKao) totalDao.getObjectByCondition("from UsersHSKao where code = ?", users.getCode());
		hsKao.setStatus(0);
		hsKao.setSendStatus2(0);
		hsKao.setUpdateTime(Util.getDateTime());
		hsKao.setUser_privilege(users.getUser_privilege()==null ? "USER":users.getUser_privilege());
		//3 - DevName=MOOM, DevTime=20180304000227, DevInfo={"firmware_filename":"Fc601HS","supported_enroll_data":["FACE","FP","PASSWORD","IDCARD"],"fk_bin_data_lib":"FKDATAHS100","face_data_ver":100,"firmware":"Fc601HS","fp_data_ver":112}
		if(totalDao.update(hsKao)){
			byte [] hs = CreateBSCommBufferFromString(hsKao);
//				String sql = "INSERT INTO tbl_fkcmd_users(addTime, hscode, status, code, userId, name, dongtai, user_date)" +
//						" VALUES(?,?,?,?,?,?,?,?)";
//				try {
//					Object[] user = new Object[]{Util.getDateTime(),hsKao.getHscode()+"",hsKao.getStatus(),hsKao.getCode(),hsKao.getUserId(),hsKao.getName(),"FKDATAHS100",hs};
//					if(JDBCUtilHS.update(sql, user)){
//						hsKao.setSendStatus(0);
//						totalDao.update(hsKao);
//						String select = "SELECT device_id FROM tbl_fkdevice_status where dongtai = 'FKDATAHS100' and collection = 1";
//						List<Map<String, Object>> list = JDBCUtilHS.select(select);
//						if(list!=null&&list.size()>0){
//							String mDevId = list.get(0).get("device_id").toString();
//							Thread.sleep(300);
//							String mTransID = GetNewTransId();
//							String addtbl_fkcmd_trans_cmd_param = "insert into tbl_fkcmd_trans_cmd_param (trans_id, device_id, cmd_param, addTime)values(?, ?, ?, ?)";
//							Thread.sleep(100);
//							JDBCUtilHS.update(addtbl_fkcmd_trans_cmd_param, new Object[]{mTransID, mDevId, hs, MyUtil.getDateTime()});
//							String addtbl_fkcmd_trans = "insert into tbl_fkcmd_trans (trans_id,device_id,cmd_code,status,update_time)values(?,?,?,'WAIT',?)";
//							Thread.sleep(100);
//							JDBCUtilHS.update(addtbl_fkcmd_trans, new Object[]{mTransID, mDevId, "SET_USER_INFO", MyUtil.getDateTime()});
//						}
//					}
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				String sql1 = "INSERT INTO tbl_fkcmd_users(addTime, hscode, status, code, userId, name, dongtai, user_date)" +
//						" VALUES(?,?,?,?,?,?,?,?)";
			try {
				
				String dongtai1 = "FKDataHS101";
				String select1 = "SELECT hscode FROM tbl_fkcmd_users where dongtai = '"+dongtai1+"' and hscode = '"+hsKao.getHscode()+"'";
				Thread.sleep(100);
				List<Map<String, Object>> list1 = JDBCUtilHS.select(select1);
				if(list1!=null&&list1.size()>0){
				}else {
					String sql1 = "INSERT INTO tbl_fkcmd_users(addTime, hscode, status, code, userId, name, dongtai, user_date)" +
					" VALUES(?,?,?,?,?,?,?,?)";
					Object[] user1 = new Object[]{Util.getDateTime(),hsKao.getHscode()+"",hsKao.getStatus(),hsKao.getCode(),hsKao.getUserId(),hsKao.getName(),dongtai1,hs};
					Thread.sleep(200);
					JDBCUtilHS.update(sql1, user1);
				}
//					Object[] user1 = new Object[]{Util.getDateTime(),hsKao.getHscode()+"",hsKao.getStatus(),hsKao.getCode(),hsKao.getUserId(),hsKao.getName(),"FKDataHS101",hs};
//					Thread.sleep(100);
//					if(JDBCUtilHS.update(sql1, user1)){
//						hsKao.setSendStatus2(0);
//						totalDao.update(hsKao);
				String select = "SELECT device_id FROM tbl_fkdevice_status where dongtai = 'FKDataHS101' and collection = 1";
				Thread.sleep(100);
				List<Map<String, Object>> list = JDBCUtilHS.select(select);
				if(list!=null&&list.size()>0){
					String mDevId = list.get(0).get("device_id").toString();
					Thread.sleep(300);
					String mTransID = GetNewTransId();
					String addtbl_fkcmd_trans_cmd_param = "insert into tbl_fkcmd_trans_cmd_param (trans_id, device_id, cmd_param, addTime)values(?, ?, ?, ?)";
					Thread.sleep(100);
					JDBCUtilHS.update(addtbl_fkcmd_trans_cmd_param, new Object[]{mTransID, mDevId, hs, MyUtil.getDateTime()});
					String addtbl_fkcmd_trans = "insert into tbl_fkcmd_trans (trans_id,device_id,cmd_code,status,update_time)values(?,?,?,'WAIT',?)";
					Thread.sleep(100);
					JDBCUtilHS.update(addtbl_fkcmd_trans, new Object[]{mTransID, mDevId, "SET_USER_INFO", MyUtil.getDateTime()});
				}
//					}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void addDongtai(TotalDao totalDao, UsersHSKao hsKao,
			byte[] hs, String dongtai1) {
		String sql1 = "INSERT INTO tbl_fkcmd_users(addTime, hscode, status, code, userId, name, dongtai, user_date)" +
				" VALUES(?,?,?,?,?,?,?,?)";
		try {
			Object[] user1 = new Object[]{Util.getDateTime(),hsKao.getHscode()+"",hsKao.getStatus(),hsKao.getCode(),hsKao.getUserId(),hsKao.getName(),dongtai1,hs};
			Thread.sleep(200);
			if(JDBCUtilHS.update(sql1, user1)){
				hsKao.setSendStatus2(0);
				totalDao.update(hsKao);
				String select = "SELECT device_id FROM tbl_fkdevice_status where dongtai = 'FKDataHS101' and collection = 1";
				Thread.sleep(200);
				List<Map<String, Object>> list = JDBCUtilHS.select(select);
				if(list!=null&&list.size()>0){
					String mDevId = list.get(0).get("device_id").toString();
					Thread.sleep(400);
					String mTransID = GetNewTransId();
					String addtbl_fkcmd_trans_cmd_param = "insert into tbl_fkcmd_trans_cmd_param (trans_id, device_id, cmd_param, addTime)values(?, ?, ?, ?)";
					Thread.sleep(200);
					JDBCUtilHS.update(addtbl_fkcmd_trans_cmd_param, new Object[]{mTransID, mDevId, hs, MyUtil.getDateTime()});
					String addtbl_fkcmd_trans = "insert into tbl_fkcmd_trans (trans_id,device_id,cmd_code,status,update_time)values(?,?,?,'WAIT',?)";
					Thread.sleep(400);
					JDBCUtilHS.update(addtbl_fkcmd_trans, new Object[]{mTransID, mDevId, "SET_USER_INFO", MyUtil.getDateTime()});
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获得浩顺考勤机唯一工号
	 * @return
	 */
	public static Integer addUserHscode(){
		TotalDao totalDao = createTotol();
		Integer hsCode = (Integer) totalDao.getObjectByCondition("select max(hscode) from UsersHSKao");
		if(hsCode==null||"".equals(hsCode)){
			hsCode = 1;
		}else {
			hsCode += 1;
		}
		System.out.println("hsCode==>"+hsCode);
		return hsCode;
	}
	

	/**
	 * 创建缓冲字符串，存入cmd中
	 * @param sCmdParam
	 * @param cmd
	 * @return
	 */
	public static byte[] CreateBSCommBufferFromString(UsersHSKao hsKao) {
		byte[] cmd = new byte[0];
		try
		{
			StringBuffer stb = new StringBuffer();
			stb.append("{");
			stb.append("\"user_id\":");
			stb.append("\""+hsKao.getHscode()+"\"");
//			stb.append("\"3\"");
			stb.append(",");
			stb.append("\"user_name\":");
			stb.append("\""+hsKao.getName()+"\",");
//			stb.append("\"李惠民\",");
			stb.append("\"user_privilege\":");
			stb.append("\""+hsKao.getUser_privilege()+"\"");
//			stb.append("\"USER\"");
			stb.append("}");
			if (stb.toString().length() == 0)
				return cmd;
			byte[] bytText = stb.toString().getBytes("UTF-8");//字符长度
			byte[] bytTextLen = MyUtil.int2byte(bytText.length + 1);
			cmd=new byte[4 + bytText.length + 1];
			System.arraycopy(bytTextLen,0,cmd,0,bytTextLen.length);//0~4
			System.arraycopy(bytText,0,cmd,4,bytText.length);//4~最后
			cmd[4 + bytText.length] = 0;
			return cmd;
		}
		catch(Exception e)
		{
			return cmd;
		}
	}
	
	/**
     * 生成最大的TransId，如果没有从200开始
     * @return
     */
	public static String GetNewTransId()
    {
        try {
			int nTransId;
			String sTransId = "";
			String sSql;
			sSql = "select max(trans_id) as trans_id from tbl_fkcmd_trans";
			List<Map<String, Object>> list = JDBCUtilHS.select(sSql);
			if (list.get(0).get("trans_id") != null)
			{
				sTransId = list.get(0).get("trans_id").toString();
			}
			if (sTransId == "")
			    sTransId = "200";
			nTransId = Integer.parseInt(sTransId) + 1;
			System.out.println("nTransId===:>"+nTransId);
			return String.valueOf(nTransId);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return GetNewTransId();
		}
    }
	
}
