package com.task.ServerImpl.menjin;

import gnu.io.PortInUseException;
import gnu.io.SerialPort;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.menjin.AccessEquipmentServer;
import com.task.ServerImpl.AttendanceTowServerImpl;
import com.task.entity.Price;
import com.task.entity.UserFacialFeatures;
import com.task.entity.UserFacialInfor;
import com.task.entity.Users;
import com.task.entity.caiwu.pz.CwCertificate;
import com.task.entity.menjin.Access;
import com.task.entity.menjin.AccessEquipment;
import com.task.entity.menjin.AccessLogInfor;
import com.task.entity.menjin.AccessRecords;
import com.task.entity.menjin.AccessTime;
import com.task.entity.menjin.AccessWebcam;
import com.task.entity.menjin.AccessWebcamType;
import com.task.entity.menjin.DoorBangDing;
import com.task.entity.menjin.GuardCard;
import com.task.entity.menjin.ResAccess;
import com.task.entity.menjin.SerialPtReceive;
import com.task.entity.menjin.ToolCabine;
import com.task.entity.menjin.XungengRecord;
import com.task.entity.menjin.XungengTime;
import com.task.entity.onemark.OneLight;
import com.task.util.MKUtil;
import com.task.util.RtxUtil;
import com.task.util.Util;
import com.task.util.serialPort.ByteUtils;
import com.task.util.serialPort.SerialPortManager;

@SuppressWarnings("unchecked")
public class AccessEquipmentServerImpl implements AccessEquipmentServer {
	private TotalDao totalDao;

	/**
	 * 添加门禁设备记录
	 * 
	 * @return
	 */
	@Override
	public String addAccessEquipment(AccessEquipment accessEquipment) {
		// TODO Auto-generated method stub
		accessEquipment.setAddTime(Util.getDateTime());
		accessEquipment.setAdminCardId("");
		accessEquipment.setAdminStatus("待添加");
		int acc2 = totalDao.getCount(
				"from AccessEquipment where equipmentIP = ?", accessEquipment
						.getEquipmentIP());
		if (acc2 > 0)
			return "设备ip已存在，添加失败！";
		if("面部识别".equals(accessEquipment.getEquipmentDaoType())){
			//关联面部特征信息
			Set<UserFacialFeatures> facialF = new HashSet<UserFacialFeatures>();
			List<UserFacialFeatures> ff = totalDao.query("from UserFacialFeatures");
			for (UserFacialFeatures userFF : ff) {
				Set<AccessEquipment> Infors = new HashSet<AccessEquipment>();
				if(!userFF.getAccessEquipments().isEmpty()){
					Infors = userFF.getAccessEquipments();
				}
				Infors.add(accessEquipment);
				userFF.setAccessEquipments(Infors);
			}
			facialF.addAll(ff);
			accessEquipment.setFacialFeatures(facialF);
			//关联用户对接信息
			Set<UserFacialInfor> infor = new HashSet<UserFacialInfor>();
			List<UserFacialInfor> ff1 = totalDao.query("from UserFacialInfor");
			for (UserFacialInfor useriF : ff1) {
				Set<AccessEquipment> Infors = new HashSet<AccessEquipment>();
				if(!useriF.getAccessEquipments().isEmpty()){
					Infors = useriF.getAccessEquipments();
				}
				Infors.add(accessEquipment);
				useriF.setAccessEquipments(Infors);
			}
			infor.addAll(ff1);
			accessEquipment.setFacialInfors(infor);
		}
		if (totalDao.save(accessEquipment))
			return "true";
		else
			return "添加失败";
	}

	/**
	 * 删除门禁设备记录
	 * 
	 * @return
	 */
	@Override
	public String deleteAccessEquipment(AccessEquipment accessEquipment) {
		// TODO Auto-generated method stub
		AccessEquipment accessEquipment1 = getbyIdAccessEquipment(accessEquipment
				.getId());
		if (accessEquipment1 != null) {
			List<DoorBangDing> bangDings = totalDao.query(
					"from DoorBangDing where fk_acEq_id = ?", accessEquipment1
							.getId());
			if (bangDings != null && bangDings.size() > 0) {
				for (DoorBangDing doorBangDing : bangDings) {
					totalDao.delete(doorBangDing);
				}
			}
			if (totalDao.delete(accessEquipment1))
				return "删除成功";
			else
				return "删除失败";
			
		}
		return "不存在该信息，删除失败!";
	}

	/**
	 * 分页查询门禁设备记录
	 * 
	 * @return
	 */
	@Override
	public Map<Integer, Object> findAccessEquipmentByCondition(
			AccessEquipment accessEquipment, int pageNo, int pageSize,
			String tag) {
		// TODO Auto-generated method stub
		String sql = "";
		if ("aD".equals(tag)) {
			Users users = Util.getLoginUser();
			sql += " adminCardId = '" + users.getCardId() + "'";
		}else if("deng".equals(tag)){
			sql += " equipmentDaoType = '灯'";
		}else if("shui".equals(tag)){
			sql += " equipmentDaoType = '水阀'";
		}
		if (accessEquipment == null) {
			accessEquipment = new AccessEquipment();
		}
		String hql = totalDao.criteriaQueries(accessEquipment, sql);
		hql += " order by id desc";
		List listi = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listi);
		map.put(2, count);
		return map;
	}

	/**
	 * 修改门禁设备记录
	 * 
	 * @return
	 */
	@Override
	public String updateAccessEquipment(AccessEquipment accessEquipment) {
		// TODO Auto-generated method stub
		AccessEquipment accessEquipment2 = getbyIdAccessEquipment(accessEquipment
				.getId());
		if (accessEquipment2 != null) {
			if (!accessEquipment2.getEquipmentIP().equals(
					accessEquipment.getEquipmentIP())) {
				int acc2 = totalDao.getCount(
						"from AccessEquipment where equipmentIP = ?",
						accessEquipment.getEquipmentIP());
				if (acc2 > 0)
					return "设备ip已存在，修改失败！";
			}
			BeanUtils.copyProperties(accessEquipment, accessEquipment2,
					new String[] { "id", "addTime", "users", "adminCardId",
							"oneLights", "accessTimes", "adminStatus", "state", 
							"operationNote", "fingId", "facialInfors", "facialFeatures"});
			accessEquipment2.setUpdateTime(Util.getDateTime());
			if (totalDao.update(accessEquipment2))
				return "true";
			else
				return "修改失败!";
		} else
			return "不存在该条数据，修改失败";
	}

	/**
	 * 根据id获得AccessEquipment对象
	 */
	@Override
	public AccessEquipment getbyIdAccessEquipment(Integer integer) {
		// TODO Auto-generated method stub
		return (AccessEquipment) totalDao.getObjectById(AccessEquipment.class,
				integer);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/****************** 管理设备对应的摄像头 /档案柜 *******************/
	@Override
	public String addAccessWebcam(AccessWebcam accessWebcam,
			AccessEquipment accessEquipment) {
		// TODO Auto-generated method stub
		if (accessEquipment != null) {
			accessEquipment = getbyIdAccessEquipment(accessEquipment.getId());
			if (accessEquipment != null) {
				if (accessWebcam != null) {
					accessWebcam.setAeqt_id(accessEquipment.getId());
					accessWebcam.setAeqt_ip(accessEquipment.getEquipmentIP());
					accessWebcam.setAddTime(Util.getDateTime());
					if (totalDao.save(accessWebcam)) {
						return "true";
					} else {
						return "添加失败";
					}
				} else {
					return "摄像头对象为空，添加失败";
				}
			} else {
				return "对应设备对象为空，添加失败";
			}
		}
		return "对应设备对象为空，添加失败";
	}

	@Override
	public String deleteAccessWebcam(AccessWebcam accessWebcam) {
		// TODO Auto-generated method stub
		AccessWebcam accessWebcam1 = getbyIdAccessWebcam(accessWebcam.getId());
		if (accessWebcam1 != null) {
			if (totalDao.delete(accessWebcam1))
				return "删除成功";
			else
				return "删除失败";
		}
		return "不存在该信息，删除失败!";
	}

	@Override
	public Map<Integer, Object> findAccessWebcamByCondition(
			AccessEquipment accessEquipment, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String sql = "";
		if (accessEquipment.getId() != null && accessEquipment.getId() > 0) {
			sql = " and Aeqt_id=" + accessEquipment.getId();
		} else {
			sql = " and Aeqt_id=" + 0;
		}
		String hql = "from AccessWebcam where 1=1 " + sql;
		// hql += " order by id desc";
		List listi = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listi);
		map.put(2, count);
		return map;
	}

	@Override
	public AccessWebcam getbyIdAccessWebcam(Integer integer) {
		// TODO Auto-generated method stub
		AccessWebcam accessWebcam = (AccessWebcam) totalDao.getObjectById(
				AccessWebcam.class, integer);
		return accessWebcam;
	}

	@Override
	public String updateAccessWebcam(AccessWebcam accessWebcam, String tag) {
		// TODO Auto-generated method stub
		boolean b = false;
		AccessWebcam accessWebcam1 = getbyIdAccessWebcam(accessWebcam.getId());
		if (accessWebcam1 != null) {
			if(!"".equals(accessWebcam.getCabinetType())&&!accessWebcam.getCabinetType().equals(accessWebcam1.getCabinetType())){//柜子类型修改之后
				addAcceWeType(accessWebcam);
			}
			if ("dag".equals(tag)
					&& !"".equals(accessWebcam.getCabinetNum())
					&& accessWebcam.getCabinetNum() != null
					&& !accessWebcam1.getCabinetNum().equals(
							accessWebcam.getCabinetNum())) {
				b = true;
			}
			BeanUtils.copyProperties(accessWebcam, accessWebcam1,
					new String[] { "id", "addTime", "aeqt_id", "aeqt_ip",
							 "actualNum" });
			accessWebcam1.setUpdateTime(Util.getDateTime());
			if (totalDao.update(accessWebcam1)) {
				if (b) {
					// 将档案柜中的档案编号也修改
					List<Price> list = totalDao.query(
							"from Price where danganId = ?", accessWebcam1
									.getId().toString());
					if (list != null && list.size() > 0) {
						for (Price price : list) {
							price
									.setDanganWeizhi(accessWebcam1
											.getCabinetNum());
							totalDao.update(price);
						}
					}
				}
				return "true";
			}
		} else {
			return "不存在该条数据，修改失败";
		}
		return "修改失败";
	}
	
	/**
	 * 巡更定时推送
	 */
	@Override
	public void xungengTongzhi() {
		String nowTime = Util.getDateTime("HH:mm:ss");// 获取当前时间
		// 判断当前时间属于哪个巡更时间段
		String hql_xungengTime = "from XungengTime where startTime <= ? and endTime > ?";
		XungengTime xt = (XungengTime) totalDao.getObjectByCondition(
				hql_xungengTime, nowTime, nowTime);
		if (xt == null) {
			xt = (XungengTime) totalDao.getObjectByCondition(
					"from XungengTime where startTime > endTime and (startTime <= ? or endTime >= ?)", nowTime, nowTime);
		}
		if(xt != null){
			String date_now = Util.getDateTime("yyyy-MM-dd");
			// 查看是否已经生成需要巡更的记录
			String xungengjilv_hql = "from XungengRecord where xungengTimeId = ? and dateNow = ?";
			List<XungengRecord> xungengList = totalDao.query(xungengjilv_hql,
					xt.getId(),date_now);
			if (xungengList != null && xungengList.size() > 0) {// 已经生成记录的 （状态未巡更的记录发送通知）
				// 状态未巡更的记录
				List<XungengRecord> noxungengList = totalDao
						.query(
								"from XungengRecord where xungengTimeId = ? and dateNow = ? and status='未巡更'",
								xt.getId(),date_now);
				if(noxungengList!=null&&noxungengList.size()>0){
					// 获取设备名称
					String shebeiName = "";
					for (XungengRecord noXunGeng : noxungengList) {
						if ("".equals(shebeiName)) {
							shebeiName += noXunGeng.getEquipmentName();
						} else {
							shebeiName += "," + noXunGeng.getEquipmentName();
						}
					}
					if(!"".equals(shebeiName)){
						// 获取所有门卫信息
						List<String> menwei_code = totalDao
						.query("select distinct code from GuardCard");
						
						List<String> codes1 = new ArrayList<String>();
						String ss = "时间段"+ xt.getStartTime() + "--" + xt.getEndTime()+ " 巡更的地点为：" + shebeiName;
						if (menwei_code.size() > 0 && menwei_code != null) {
							for (String code : menwei_code) {// 发送信息
								//AlertMessagesServerImpl.addAlertMessages("巡更提醒", ss, "1", code);
								codes1.add(code);
							}
						}
						if (codes1 != null && codes1.size() > 0) {
							RtxUtil.sendNoLoginNotify(codes1, ss,
									"系统消息", "0", "0");
						}
					}
				}
			} else {// 没有生成记录（生成记录）
				// 需要巡更的设备
				List<AccessEquipment> shebeiList = totalDao.query(
						"from AccessEquipment where isXungeng = ?", "是");
				String shebeiName = "";
				for (AccessEquipment ae : shebeiList) {
					// 生成记录
					XungengRecord xungengRecord = new XungengRecord();
					xungengRecord.setEquipmentId(ae.getId());
					xungengRecord.setEquipmentName(ae.getEquipmentName());
					xungengRecord.setEquipmentNum(ae.getEquipmentNum());
					xungengRecord.setAddTime(Util.getDateTime());//添加时间
					xungengRecord.setXungengTimeId(xt.getId());
					xungengRecord.setXungengTimeName(xt.getName());
					xungengRecord.setStatus("未巡更");
					xungengRecord.setDateNow(Util.getDateTime("yyyy-MM-dd"));
					totalDao.save2(xungengRecord);
					// 获取设备名称
					if ("".equals(shebeiName)) {
						shebeiName += ae.getEquipmentName();
					} else {
						shebeiName += "," + ae.getEquipmentName();
					}
				}
				if(!"".equals(shebeiName)){
					// 获取所有门卫信息
					List<String> menwei_code = totalDao
					.query("select distinct code from GuardCard");
					List<String> codes1 = new ArrayList<String>();
					String ss = "时间段"+ xt.getStartTime() + "--" + xt.getEndTime()+ " 巡更的地点为：" + shebeiName;
					if (menwei_code.size() > 0 && menwei_code != null) {
						for (String code : menwei_code) {// 发送信息
//							AlertMessagesServerImpl.addAlertMessages("巡更提醒", ss, "1", code);
							codes1.add(code);
						}
					}
					if (codes1 != null && codes1.size() > 0) {
						RtxUtil.sendNoLoginNotify(codes1, ss,
								"系统消息", "0", "0");
					}
				}
			}
		}
	}
	
//	@Override
	public void huifuXunGeng(){
		List<String> list = new ArrayList<String>();
		list.add("2018-02-12");
		list.add("2018-02-13");
		list.add("2018-02-14");
		list.add("2018-02-15");
		list.add("2018-02-16");
		list.add("2018-02-17");
		list.add("2018-02-18");
		list.add("2018-02-19");
		list.add("2018-02-20");
		list.add("2018-02-21");
		list.add("2018-02-22");
		for (String ss : list) {
			List<XungengTime> times = totalDao.query("from XungengTime");
			for (XungengTime xt : times) {
				List<AccessEquipment> listE = totalDao.query("from AccessEquipment where isXungeng = '是'");
				for (AccessEquipment ae : listE) {
					XungengRecord record = new XungengRecord();
					record.setAddTime(ss+" "+xt.getStartTime());
					record.setEquipmentId(ae.getId());
					record.setEquipmentName(ae.getEquipmentName());
					record.setEquipmentNum(ae.getEquipmentNum());
					record.setXungengTimeId(xt.getId());
					record.setXungengTimeName(xt.getName());
					record.setStatus("未巡更");
					record.setDateNow(ss);
					totalDao.save2(record);
				}
			}
		}
	}
	
//	@Override
	public void fuzhiXunGeng(){
		List<AccessLogInfor> infors = totalDao.query("from AccessLogInfor where cardId in (select cardId from GuardCard where cardId <> '0001500930') " +
				"and aceIp in (select equipmentIP from AccessEquipment where isXungeng = ?) and addTime > '2018-02-23' and aceId = 20 order by id desc","是");
		for (AccessLogInfor accessLogInfor : infors) {
			String date = accessLogInfor.getAddTime().substring(0, 10);
			String time = accessLogInfor.getAddTime().substring(11, 16);
			String nowTime = time;//获取当前时间
			List<XungengTime> xungengTime = totalDao.query("from XungengTime where startTime <= ? and endTime > ?",nowTime,nowTime);//所有巡更时段
			if(xungengTime==null||(xungengTime!=null&&xungengTime.size()==0)){
				xungengTime = totalDao.query("from XungengTime where startTime > endTime and (startTime <= ? or endTime >= ?)", nowTime, nowTime);
			}
			if(xungengTime!=null&&xungengTime.size()>0){
			if(xungengTime.get(0)!=null){
				//查询该设备在该时间段的未巡更的记录
				String hql_xungeng = "from XungengRecord where xungengTimeId =? and equipmentId = ? and status = ? and dateNow = ?";
				List<XungengRecord> xungengList = totalDao.query(hql_xungeng, xungengTime.get(0).getId(),accessLogInfor.getAceId(),"未巡更",date);
					if(xungengList!=null&&xungengList.size()>0){
						for(XungengRecord xr : xungengList){
//							xr.setGuardCardid(guardCard.getId());
							List<GuardCard> guardCards = totalDao.query("from GuardCard where cardId = ?", accessLogInfor.getCardId());
							if(guardCards!=null&&guardCards.size()>0){
								xr.setGuardCardid(guardCards.get(0).getId());
								xr.setUserName(guardCards.get(0).getName());
								xr.setUserCode(guardCards.get(0).getCode());
								xr.setDakaTime(accessLogInfor.getAddTime());
								xr.setStatus("已巡更");
								totalDao.update2(xr);
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	public String updateDanganGui(AccessWebcam accessWebcam) {
		// TODO Auto-generated method stub
		AccessWebcam accessWebcam1 = getbyIdAccessWebcam(accessWebcam.getId());
		if (accessWebcam1 != null) {
			BeanUtils.copyProperties(accessWebcam, accessWebcam1,
					new String[] { "id", "addTime", "aeqt_id", "aeqt_ip",
							"type", "actualNum" });
			accessWebcam1.setUpdateTime(Util.getDateTime());
			if (totalDao.update(accessWebcam1)) {
				return "true";
			}
		} else {
			return "不存在该条数据，修改失败";
		}
		return "修改失败";
	}

	@Override
	public String openZj(String ip, Integer port, int openOrClose) {
		// TODO Auto-generated method stub
		Socket s;
		try {
			s = new Socket(ip, port);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s
					.getOutputStream()));
			bw.write(openOrClose);
			// bw.newLine();
			bw.flush();
			bw.close();
			s.close();
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "操作失败!";
		}
	}

	/************************************* 绑定门禁权限接口 ***************************************************/
	// 已绑定人员处理
	@Override
	public List<Users> findAllBangUser(String object, Integer id) {
		// TODO Auto-generated method stub
		if (object.length() > 0 && id > 0) {
			Users user = (Users) ActionContext.getContext().getSession().get(
					"adminusers");
			String hql = null;
			if (user != null) {
				List<String> deptNames = totalDao
						.query("select deptName from UserDept where userId ="
								+ user.getId());
				StringBuffer sb = new StringBuffer();
				if (deptNames.size() > 0) {
					for (int i = 0; i < deptNames.size(); i++) {
						if (i == 0) {
							sb.append("'");
							sb.append(deptNames.get(i));
							sb.append("'");
						} else {
							sb.append(",'");
							sb.append(deptNames.get(i));
							sb.append("'");
						}
					}
				}
				hql = "from Users where id in (select fk_user_id from DoorBangDing where fk_acEq_id =?) and onWork not in ('离职','退休','病休') and dept not in('內退','病休') and dept in ("
						+ sb.toString() + ")" + " order by dept";
			} else {
				hql = "from Users where id in (select fk_user_id from DoorBangDing where fk_acEq_id =? and (timeIsTrue is null or timeIsTrue = '')) and onWork not in ('离职','退休','病休') and dept not in ('內退','病休') order by dept";
			}
			return totalDao.query(hql, id);
		}
		return null;
	}

	@Override
	public List<Users> findAllBangTimeUser(Integer id) {
		// TODO Auto-generated method stub
		if (id != null && id > 0) {
			String hql = "from Users where id in (select fk_user_id from DoorBangDing where fk_acEq_id =? and timeIsTrue = '是' ) and onWork not in ('离职','退休','病休') and dept not in ('內退','病休') order by dept";
			return totalDao.query(hql, id);
		}
		return null;
	}

	// 条件查询所有用户
	@Override
	public Object[] findUsersByCondition(Users user, int pageNo, int pageSize,
			Integer id) {
		// TODO Auto-generated method stub
		if (user == null) {
			user = new Users();
		}
		String hql = "";
		hql = totalDao.criteriaQueries(user, null);
		hql += " and id not in (select fk_user_id from DoorBangDing where fk_acEq_id =?) and onWork not in ('离职','退休','病休') and dept not in ('內退','病休')";
		// 根据不同的类型显示不同的人
		if (id != null && id > 0) {
			AccessEquipment accessEquipment = (AccessEquipment) totalDao
					.getObjectById(AccessEquipment.class, id);
			if (accessEquipment != null
					&& "男".equals(accessEquipment.getEquipmentDaoType())) {
				hql += " and sex = '男' order by dept";
			} else if (accessEquipment != null
					&& "女".equals(accessEquipment.getEquipmentDaoType())) {
				hql += " and sex = '女' order by dept";
			} else {
				hql += " order by dept";
			}
		}
		List list = totalDao.findAllByPage(hql, pageNo, pageSize, id);
		int count = totalDao.getCount(hql, id);
		Object[] o = { list, count };
		return o;
	}

	@Override
	public Object[] findAllUser(String object, Integer functionId,
			int parseInt, int pageSize) {
		// TODO Auto-generated method stub
		if (object.length() > 0 && functionId > 0) {
			Users user = (Users) ActionContext.getContext().getSession().get(
					"users");
			String hql = null;
			if (user != null) {
				List<String> deptNames = totalDao
						.query("select deptName from UserDept where userId ="
								+ user.getId());
				StringBuffer sb = new StringBuffer();
				if (deptNames.size() > 0) {
					for (int i = 0; i < deptNames.size(); i++) {
						if (i == 0) {
							sb.append("'");
							sb.append(deptNames.get(i));
							sb.append("'");
						} else {
							sb.append(",'");
							sb.append(deptNames.get(i));
							sb.append("'");
						}
					}
				}
				hql = "from Users where id not in (select fk_user_id from DoorBangDing where fk_acEq_id =?) and onWork not in ('离职','退休','病休') and dept not in('內退','病休')";
			} else {
				hql = "from Users where id not in (select fk_user_id from DoorBangDing where fk_acEq_id =?) and onWork not in ('离职','退休','病休') and dept not in ('內退','病休')";
			}
			// 根据不同的类型显示不同的人
			if (functionId != null && functionId > 0) {
				AccessEquipment accessEquipment = (AccessEquipment) totalDao
						.getObjectById(AccessEquipment.class, functionId);
				if (accessEquipment != null
						&& "男".equals(accessEquipment.getEquipmentDaoType())) {
					hql += " and sex = '男' order by dept";
				} else if (accessEquipment != null
						&& "女".equals(accessEquipment.getEquipmentDaoType())) {
					hql += " and sex = '女' order by dept";
				} else {
					hql += " order by dept";
				}
			}
			List list = totalDao.findAllByPage(hql, parseInt, pageSize,
					functionId);
			int count = totalDao.getCount(hql, functionId);
			Object[] o = { list, count };
			return o;
		}
		return null;
	}

	@Override
	public String binDingUsers(AccessEquipment accessEquipment,
			Integer[] usersId) {
		// TODO Auto-generated method stub
		boolean b = true;
		if (accessEquipment != null) {
			List<Integer> usersSet = new ArrayList();// 用来存储最终要的绑定用户ID;
			List<Integer> moreSet = new ArrayList();// 用来存储相对之前增加的绑定用户ID;
			List<Integer> lessSet = new ArrayList();// 用来存储相对之前减少的绑定用户ID;
			AccessEquipment accessEquipment2 = (AccessEquipment) totalDao
					.getObjectById(AccessEquipment.class, accessEquipment
							.getId());
			if (usersId != null && usersId.length > 0) {
				for (int i = 0; i < usersId.length; i++) {
					Users user = (Users) totalDao.getObjectById(Users.class,
							usersId[i]);// 查询用户
					if (user != null) {
						usersSet.add(user.getId());
					} else {
						System.out.println("null");
					}
				}
			}
			// 得到门现有的usersID
			List<Integer> haduserSet = totalDao
					.query(
							"select id from Users where id in (select fk_user_id from DoorBangDing where fk_acEq_id = ?) ",
							accessEquipment2.getId());// 数据库中绑定的对象
			for (Integer u4 : haduserSet) {
				if (!usersSet.contains(u4)) {// 页面传过来的不包含说明页面新减少了这个对象的绑定
					lessSet.add(u4);
				}
			}
			for (Integer u5 : usersSet) {
				if (!haduserSet.contains(u5)) {// 原来的的不包含说明页面新增加了这个对象的绑定
					moreSet.add(u5);
				}
			}
			if (lessSet.size() == 0 && moreSet.size() == 0) {
				return "没有可更改的绑定";// 没有可更改的绑定
			}
			// 绑定新对象
			if (moreSet != null && moreSet.size() > 0) {
				for (int i = 0; i < moreSet.size(); i++) {
					DoorBangDing doorBangDing = new DoorBangDing();
					doorBangDing.setFk_user_id(moreSet.get(i));
					doorBangDing.setFk_acEq_id(accessEquipment2.getId());
					b = b & totalDao.save(doorBangDing);
				}
			}
			if (moreSet.size() > 0 && !b) {
				return "绑定新用户出错，请检查后重试！";// 绑定出错的时候
			}
			String nof = "";// 无法解除绑定的
			// 解绑对象
			if (lessSet != null && lessSet.size() > 0) {
				for (int i = 0; i < lessSet.size(); i++) {
					DoorBangDing bangDing = (DoorBangDing) totalDao
							.getObjectByCondition(
									"from DoorBangDing where fk_user_id = ? and fk_acEq_id = ?",
									lessSet.get(i), accessEquipment2.getId());
					if (bangDing != null) {
						if ("内".equals(bangDing.getStatus())) {
							Users user1 = (Users) totalDao.getObjectById(
									Users.class, lessSet.get(i));// 查询用户
							nof += user1.getName() + " ";
						} else {
							b = b & totalDao.delete(bangDing);
						}
					}
				}
			}
			if (b) {
				if ("".equals(nof)) {
					nof = "绑定成功";
				} else {
					nof = "请等待:" + nof + "出门后再解除绑定！";
				}
				return nof;
			}
		}
		return "绑定失败";
	}

	@Override
	public String addDanganGui(AccessWebcam accessWebcam) {
		// TODO Auto-generated method stub
		if (accessWebcam != null) {
			accessWebcam.setActualNum(0);
			accessWebcam.setAddTime(Util.getDateTime());
			if(!"".equals(accessWebcam.getCabinetType())){//柜子类型
				addAcceWeType(accessWebcam);
			}
			if (totalDao.save(accessWebcam)) {
				return "档案柜添加成功!";
			} else {
				return "档案柜添加失败";
			}
		} else {
			return "档案柜对象为空，添加失败";
		}
	}

	/**
	 * 添加新的柜子类型
	 * @param accessWebcam
	 */
	private void addAcceWeType(AccessWebcam accessWebcam) {
		int I = totalDao.getCount("from AccessWebcamType where type = ? and name = ?", accessWebcam.getType(), accessWebcam.getCabinetType());
		if(I==0){
			totalDao.save(new AccessWebcamType(accessWebcam.getType(), accessWebcam.getCabinetType()));
		}
	}

	@Override
	public Map<Integer, Object> findDanganGuiByCondition(
			AccessWebcam accessWebcam, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (accessWebcam == null) {
			accessWebcam = new AccessWebcam();
		}
		String sql = "";
		sql = " type is not null";
		String hql = totalDao.criteriaQueries(accessWebcam, sql, "aeqt_id",
				"maxNum", "actualNum");
		hql += " order by id desc";
		List listi = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		if(listi!=null&&listi.size()>0){
			for (Object object : listi) {
				AccessWebcam a = (AccessWebcam) object;
				if("dag".equals(a.getType()))
					a.setType("档案柜");
				else if("pz".equals(a.getType()))
					a.setType("凭证柜");
				else if("fp".equals(a.getType()))
					a.setType("发票柜");
				else if("yz".equals(a.getType()))
					a.setType("印章柜");
			}
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listi);
		map.put(2, count);
		return map;
	}

	@Override
	public List<AccessWebcam> findDanganGui(String tag) {
		// TODO Auto-generated method stub
		if(tag==null||"".equals(tag))
			tag = "dag";
		return totalDao
				.query("from AccessWebcam where type = ? and maxNum > actualNum order by id desc",tag);
	}
	@Override
	public List<AccessWebcam> findDanganGui(String tag,String type) {
		// TODO Auto-generated method stub
		if(tag==null||"".equals(tag))
			tag = "dag";
		return totalDao.query("from AccessWebcam where type = ? cabinetType and = ? and maxNum > actualNum order by id desc",tag,type);
	}

	@Override
	public String BindingAdmin(AccessEquipment accessEquipment) {
		// TODO Auto-generated method stub
		if (accessEquipment != null && accessEquipment.getAdminCardId() != null) {
			AccessEquipment accessEquipment2 = getbyIdAccessEquipment(accessEquipment
					.getId());
			if (!(accessEquipment.getAdminCardId()).equals(accessEquipment2
					.getAdminCardId())
					&& accessEquipment.getAdminCardId() != null
					&& !"".equals(accessEquipment.getAdminCardId())) {
				accessEquipment2.setAdminCardId(accessEquipment
						.getAdminCardId());
				accessEquipment2.setAdminStatus("待刷卡");
				if (totalDao.update2(accessEquipment2)) {
					return "设置成功，请前往对应设备刷卡";
				}
			}
			return "卡号为空或与之前卡号相等，设置失败";
		}
		return "设置卡号为空";
	}

	@Override
	public String binDingPrice(AccessWebcam accessWebcam, Integer[] priceId) {
		// TODO Auto-generated method stub
		boolean b = true;
		if (accessWebcam != null) {
			List<Integer> usersSet = new ArrayList();// 用来存储最终要的绑定用户ID;
			List<Integer> moreSet = new ArrayList();// 用来存储相对之前增加的绑定用户ID;
			List<Integer> lessSet = new ArrayList();// 用来存储相对之前减少的绑定用户ID;
			AccessWebcam accessWebcam1 = (AccessWebcam) totalDao.getObjectById(
					AccessWebcam.class, accessWebcam.getId());
			if (priceId != null && priceId.length > 0) {
				for (int i = 0; i < priceId.length; i++) {
					Price price1 = (Price) totalDao.getObjectById(Price.class,
							priceId[i]);// 查询用户
					if (price1 != null) {
						usersSet.add(price1.getId());
					} else {
						System.out.println("null");
					}
				}
			}
			if (accessWebcam1.getMaxNum() != null
					&& usersSet.size() > accessWebcam1.getMaxNum()) {
				return "选择档案数量大于柜子最大存放数量" + accessWebcam1.getMaxNum()
						+ "。请重新选择！";// 
			}
			// 得到档案柜现有的priceId
			List<Integer> haduserSet = totalDao.query(
					"select id from Price where danganId = ?", accessWebcam1
							.getId().toString());// 数据库中已绑定的对象
			for (Integer u4 : haduserSet) {
				if (!usersSet.contains(u4)) {// 页面传过来的不包含说明页面新减少了这个对象的绑定
					lessSet.add(u4);
				}
			}
			for (Integer u5 : usersSet) {
				if (!haduserSet.contains(u5)) {// 原来的的不包含说明页面新增加了这个对象的绑定
					moreSet.add(u5);
				}
			}
			if (lessSet.size() == 0 && moreSet.size() == 0) {
				accessWebcam1.setActualNum(usersSet.size());
				totalDao.update(accessWebcam1);
				return "没有可更改的绑定";// 没有可更改的绑定
			}
			// 绑定新对象
			if (moreSet != null && moreSet.size() > 0) {
				for (int i = 0; i < moreSet.size(); i++) {
					Price price = (Price) totalDao.getObjectById(Price.class,
							moreSet.get(i));
					price.setDanganId(accessWebcam1.getId().toString());
					price.setDanganWeizhi(accessWebcam1.getCabinetNum());
					b = b & totalDao.update(price);
				}
			}
			if (moreSet.size() > 0 && !b)
				return "绑定新档案出错，请检查后重试！";// 绑定出错的时候
			// 解绑对象
			if (lessSet != null && lessSet.size() > 0) {
				for (int i = 0; i < lessSet.size(); i++) {
					Price price = (Price) totalDao.getObjectById(Price.class,
							lessSet.get(i));
					price.setDanganId(null);
					price.setDanganWeizhi(null);
					b = b & totalDao.update(price);
				}
			}
			if (b) {
				accessWebcam1.setActualNum(usersSet.size());
				totalDao.update(accessWebcam1);
				return "绑定成功";
			}
		}
		return "绑定失败";
	}

	@Override
	public List<Price> findAllBangPrice(Integer id) {
		// TODO Auto-generated method stub
		if (id != null && id > 0) {
			List<Price> list = totalDao.query("from Price where danganId = ?",
					id.toString());
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return null;
	}
	
	@Override
	public List findAllBangXinxi(Integer id) {
		// TODO Auto-generated method stub
		List list = null;
		if (id != null && id > 0) {
			list = totalDao.query("from SealLogType where guiId = ?", id);
			if(list != null && list.size() > 0){
				return list;
			}
		}
		return null;
	}

	
	@Override
	public Object[] findAllPrice(Integer id, int parseInt, int pageSize) {
		// TODO Auto-generated method stub
		String hql = "";
		hql = "from Price where danganId is null and danganWeizhi is null order by id desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	@Override
	public Object[] findPriceByCondition(Price price, int parseInt, int pageSize) {
		// TODO Auto-generated method stub
		if (price == null) {
			price = new Price();
		}
		String hql = "";
		hql = totalDao.criteriaQueries(price, null);
		hql += " and (danganId is null or danganId = '') and (danganWeizhi is null or danganWeizhi = '')";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	@Override
	public void add1AndLess1(String oldaw, String aw, int i) {
		// TODO Auto-generated method stub
		if (i == 1) {
			AccessWebcam oldaccessWebcam = getbyIdAccessWebcam(Integer
					.parseInt(oldaw));
			AccessWebcam accessWebcam = getbyIdAccessWebcam(Integer
					.parseInt(aw));
			if (oldaccessWebcam != null) {
				if (oldaccessWebcam.getActualNum() != null
						&& oldaccessWebcam.getActualNum() > 1) {
					oldaccessWebcam
							.setActualNum(oldaccessWebcam.getActualNum() - 1);
				} else {
					oldaccessWebcam.setActualNum(0);
				}
				totalDao.update(oldaccessWebcam);
				if (accessWebcam != null) {
					if (accessWebcam.getActualNum() != null
							&& accessWebcam.getActualNum() > 0) {
						accessWebcam
								.setActualNum(accessWebcam.getActualNum() + 1);
					} else {
						accessWebcam.setActualNum(1);
					}
					totalDao.update(accessWebcam);
				}
			}
		} else if (i == 2 && aw != null && aw.length() > 0) {

			AccessWebcam accessWebcam = getbyIdAccessWebcam(Integer
					.parseInt(aw));
			if (accessWebcam != null) {
				if (accessWebcam.getActualNum() != null
						&& accessWebcam.getActualNum() > 0) {
					accessWebcam.setActualNum(accessWebcam.getActualNum() + 1);
				} else {
					accessWebcam.setActualNum(1);
				}
				totalDao.update(accessWebcam);
			}
		}
	}

	@Override
	public String addAccessTime(AccessTime accessTime,
			AccessEquipment accessEquipment) {
		// TODO Auto-generated method stub
		if (accessTime != null && accessEquipment != null) {
			accessTime.setAddTime(Util.getDateTime());
			accessTime.setAccessEquipment(accessEquipment);
			if (totalDao.save(accessTime))
				return "添加成功";
			else
				return "添加失败";
		}
		return "对象为空，添加失败";
	}

	@Override
	public String deleteAccessTime(Integer integer) {
		// TODO Auto-generated method stub
		if (integer != null && integer > 0) {
			AccessTime accessTime = getbyIdAccessTime(integer);
			if (accessTime != null) {
				if (totalDao.delete(accessTime))
					return "删除成功";
				else
					return "删除失败";
			}
		}
		return "对象为空，删除失败";
	}

	@Override
	public List<AccessTime> findAccessTimeByCondition(Integer accessEquipment) {
		// TODO Auto-generated method stub
		return totalDao.query(
				"from AccessTime where ta_acTime =? order by id desc",
				accessEquipment);
	}

	@Override
	public AccessTime getbyIdAccessTime(Integer integer) {
		// TODO Auto-generated method stub
		return (AccessTime) totalDao.getObjectById(AccessTime.class, integer);
	}

	@Override
	public String updateAccessTime(AccessTime accessTime) {
		// TODO Auto-generated method stub
		AccessTime accessTime2 = getbyIdAccessTime(accessTime.getId());
		if (accessTime2 != null) {
			// BeanUtils.copyProperties(accessTime, accessTime2, new String
			// []{"id","accessEquipment","addTime","accessEquipment"});
			accessTime2.setStartTime(accessTime.getStartTime());
			accessTime2.setEndTime(accessTime.getEndTime());
			accessTime2.setUpdateTime(Util.getDateTime());
			if (totalDao.update(accessTime2))
				return "修改成功";
			else
				return "修改失败";
		}
		return "对象为空，修改失败";
	}

	@Override
	public boolean updateDoorBangDing(Integer accessEquipment, Integer usersId,
			Integer id) {
		// TODO Auto-generated method stub
		DoorBangDing doorBangDing = (DoorBangDing) totalDao
				.getObjectByCondition(
						"from DoorBangDing where fk_acEq_id = ? and fk_user_id = ?",
						accessEquipment, usersId);
		if (doorBangDing != null) {
			if (id != null && id == 1) {// 绑定
				doorBangDing.setTimeIsTrue("是");
				return totalDao.update2(doorBangDing);
			} else if (id != null && id == 2) {// 取消绑定
				doorBangDing.setTimeIsTrue(null);
				return totalDao.update2(doorBangDing);
			}
		}
		return false;
	}

	@Override
	public String addAceyanZ(AccessEquipment accessEquipment) {
		if (accessEquipment != null && accessEquipment.getId() != null
				&& accessEquipment.getId() > 0) {
			AccessEquipment accessEquipment2 = getbyIdAccessEquipment(accessEquipment
					.getId());
			Access access = new Access();
			Users users = Util.getLoginUser();
			if (users != null) {
				String s = Util.yanNumber(6);
				access.setOutInName(users.getName());
				access.setOutInDoor("进出");
				access.setYanzhengnum(s);
				access.setAddTime(Util.getDateTime());
				access.setUseSend("未使用");
				access.setEquipmentDaoType(accessEquipment2.getEquipmentName());// 设备名称
				access.setFailtime(Util.getSpecifiedDayAfter(Util
						.getDateTime("yyyy-MM-dd"), 1));// 失效日期
				access.setEquipmentIP(accessEquipment2.getEquipmentIP());
				access.setEquipmentID(accessEquipment2.getId());
				if (totalDao.save(access)) {
					// 给需要发消息的车主发送RTX消息
					List<String> codes1 = new ArrayList<String>();
					codes1.add(users.getCode());
					// boolean b = false;
					if (codes1 != null && codes1.size() > 0) {
						RtxUtil.sendNoLoginNotify(codes1, " "
								+ accessEquipment2.getEquipmentName()
								+ " 开门验证码为 " + s + " 出门后失效!", "系统消息", "0", "0");
					}
					accessEquipment2.setLinYanZ(s);
					totalDao.update(accessEquipment2);
					return "验证码已生成";
				}
			}
		}
		return "生成失败！";
	}

	@Override
	public List findallAce() {
		// TODO Auto-generated method stub
		return totalDao.query("from AccessEquipment order by id desc");
	}
	
	@Override
	public String findDescription(String type) {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();
		List<String> list = totalDao.query("select distinct(name) from AccessWebcamType where type = ?",type);
		for (String d : list) {
			if (d!=null) {
				buffer.append(d.toString()+"|");
			}
		}
		return buffer.toString();
	}
	@Override
	public List findDescription(Integer id) {
		// TODO Auto-generated method stub
		List<AccessWebcam> list = null;
		Price price = (Price) totalDao.getObjectById(Price.class, id);
		if(price!=null){
			if(price.getProduceType()!=null){
				list = totalDao.query("from AccessWebcam where type = 'dag' and cabinetType = ? and cabinetAccessSim <> '' and cabinetAccessSim is not null ",price.getProduceType());
			}else {
				list = totalDao.query("from AccessWebcam where type = 'dag' and cabinetType = '其他' and cabinetAccessSim <> '' and cabinetAccessSim is not null ");
			}
		}
		return list;
	}

	@Override
	public String binDingOneLight(Integer ace, Integer[] taOLId) {
		// TODO Auto-generated method stub
		if (ace != null && ace > 0) {
			Set<OneLight> LightSet = new HashSet<OneLight>();// 用来存储最终要绑定的灯
			AccessEquipment accessEquipment = (AccessEquipment) totalDao
					.getObjectById(AccessEquipment.class, ace);
			if (taOLId != null && taOLId.length > 0) {
				for (int i = 0; i < taOLId.length; i++) {
					if (taOLId[i] != null) {
						OneLight OneLight1 = (OneLight) totalDao.getObjectById(
								OneLight.class, taOLId[i]);// 查询灯
						if (OneLight1 != null)
							LightSet.add(OneLight1);
					}
				}
			}
			accessEquipment.setOneLights(LightSet);
			if (totalDao.update(accessEquipment)) {
				return "绑定成功！";
			}
		}
		return "绑定失败！";
	}

	@Override
	public List<OneLight> findAllBangLight(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from OneLight where accessEquipment.id = ? order by id desc";
		return totalDao.query(hql, id);
	}

	@Override
	public Object[] findAllOneLight(OneLight oneLight, int parseInt,
			int pageSize) {
		// TODO Auto-generated method stub
		if (oneLight == null) {
			oneLight = new OneLight();
		}
		String hql = totalDao.criteriaQueries(oneLight, null);
		hql += " and accessEquipment.id = null and machiness.id = null and aceIs = '是'";// and
																						// aceIs
																						// =
																						// '是'
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	// Boss操作卷帘门（打开）
	public String BossOpenDoorById(Integer id, String doorIp, String doorPort) {
		if (id != null && id > 0) {
			Users users = Util.getLoginUser();
			AccessEquipment ae = (AccessEquipment) totalDao.getObjectById(
					AccessEquipment.class, id);
			if (ae == null)
				return "数据为空";
			Integer dport = Integer.parseInt(ae.getEquipmentPort());
			String message = Util.OpenDoor(ae.getEquipmentIP(), dport, 10);// 开门操作
			if ("true".equals(message)) {
				ae.setState("打开");
				ae.setOperationNote(users.getName() + "，开门，"
						+ Util.getDateTime());
				if (totalDao.update(ae)) {// 更新库位状态
				} else
					totalDao.update2(ae);
				return "true";
			} else {
				return "连接异常，开门失败！";
			}
		}
		return "未获取到数据";
	}

	// Boss操作卷帘门（关闭）
	public String BossColseDoorById(Integer id, String doorIp, String doorPort) {
		if (id != null && id > 0) {
			Users users = Util.getLoginUser();
			AccessEquipment ae = (AccessEquipment) totalDao.getObjectById(
					AccessEquipment.class, id);
			if (ae == null)
				return "数据为空";
			Integer dport = Integer.parseInt(ae.getEquipmentPort());
			String message = Util.OpenDoor(ae.getEquipmentIP(), dport, 26);// 关门操作
			if ("true".equals(message)) {
				ae.setState("关闭");
				ae.setOperationNote(ae.getOperationNote() + "<br/>"
						+ users.getName() + "，关门，" + Util.getDateTime());
				if (totalDao.update(ae)) {// 更新库位状态
				} else
					totalDao.update2(ae);
				return "true";
			}
			return "连接异常，关门失败！";
		}
		return "未获取到数据";
	}
	//
	// @Override
	// public String BossColseDoorById(Integer id) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public String BossOpenDoorById(Integer id) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	@Override
	public String closeShuiFa(Integer id,String type){
		AccessEquipment a = getbyIdAccessEquipment(id);
		if(a!=null){
			if("open".equals(type)){//打开
				Integer dport = Integer.parseInt(a.getEquipmentPort());
				Socket s = null;
				try {
					s = new Socket(a.getEquipmentIP(), dport);
					s.setSoTimeout(15000);
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s
							.getOutputStream()));
					bw.write(02);
					bw.flush();
					InputStream in = s.getInputStream();
					BufferedInputStream bis = new BufferedInputStream(in);
					byte[] shuiZhi = new byte[1];// 
					bis.read(shuiZhi);// 读取数据
					String mes = Util.byteArrayToHexString(shuiZhi)
							.replaceAll(" ", "");
					System.out.println("[Client " + mes + "]: " + mes);
					StringBuffer buffer1 = new StringBuffer();
					DoorBangDingServerImpl.caeLogInfor(buffer1.append("打开"), "打开", "", a.getEquipmentName(), a.getEquipmentDaoType(), a.getEquipmentLocation(), a.getId(), a.getEquipmentIP());
					a.setState("打开");
					totalDao.update2(a);//更新状态
					bw.close();
					s.close();
					return "操作成功！";
				} catch (Exception e) {
					try {
						if(s!=null)
							s.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();
					DoorBangDingServerImpl.caeLogInfor(new StringBuffer().append("状态为开,操作无效！"), "打开", "", a.getEquipmentName(), a.getEquipmentDaoType(), a.getEquipmentLocation(), a.getId(), a.getEquipmentIP());
				}
			}else {//关闭
				Integer dport = Integer.parseInt(a.getEquipmentPort());
				Socket s = null;
				try {
					s = new Socket(a.getEquipmentIP(), dport);
					s.setSoTimeout(15000);
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s
							.getOutputStream()));
					bw.write(04);
					bw.flush();
					InputStream in = s.getInputStream();
					BufferedInputStream bis = new BufferedInputStream(in);
					byte[] shuiZhi = new byte[5];// 开始接受5位16进制的水流量
					bis.read(shuiZhi);// 读取数据
					String mes = Util.byteArrayToHexString(shuiZhi)
							.replaceAll(" ", "");
					System.out.println("[Client " + mes + "]: " + mes);
					Float zhi = 0f;
					StringBuffer buffer1 = new StringBuffer();
					for (int i = 0; i < shuiZhi.length; i++) {buffer1.append(shuiZhi[i]);}
					String shuiZhis = buffer1.toString();
					try {
						zhi = Float.valueOf(shuiZhis);
					} catch (Exception e) {
					}
					DoorBangDingServerImpl.caeLogInfor(buffer1.append("本次使用水量："+zhi/10+"升 "+mes), "关闭", zhi/10+"", a.getEquipmentName(), a.getEquipmentDaoType(), a.getEquipmentLocation(), a.getId(), a.getEquipmentIP());
					Float ff = 0f;
					if(a.getOperationNote()==null||"".equals(a.getOperationNote())){
						a.setOperationNote(zhi/10+"");
					}else {
						try {
							ff = zhi/10 + Float.valueOf(a.getOperationNote().replaceAll(" ", ""));
							a.setOperationNote(ff+"");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					a.setState("关闭");
					totalDao.update2(a);//更新库位状态
					bw.close();
					s.close();
					return "操作成功！";
				} catch (Exception e) {
					try {
						if(s!=null)
							s.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();
					DoorBangDingServerImpl.caeLogInfor(new StringBuffer().append("状态为关,操作无效！"), "关闭", "", a.getEquipmentName(), a.getEquipmentDaoType(), a.getEquipmentLocation(), a.getId(), a.getEquipmentIP());
				}
			}
		}
		return "数据异常，操作失败！";
	}
	
	@Override
	public void closeJuanlianmen() {
		// TODO Auto-generated method stub
		List<AccessEquipment> ace = totalDao.query("from AccessEquipment where equipmentDaoType = '卷闸门'");
		for (AccessEquipment a : ace) {
			if(a!=null&&a.getState()!=null&&"打开".equals(a.getState())){
				Integer dport = Integer.parseInt(a.getEquipmentPort());
				String message = Util.OpenDoor(a.getEquipmentIP(), dport, 26);//开门操作
				if ("true".equals(message)) {
					a.setState("关闭");
					a.setOperationNote(a.getOperationNote()+"<br/>"+"系统，关门，"+Util.getDateTime());
					if(totalDao.update(a)){//更新库位状态
					}else totalDao.update2(a);
				}
			}
		}
		chuliCheliang();
		//重置时间
//		List<AttendanceTowCollect> attendance = totalDao.query("from AttendanceTowCollect where dateTime > '2017-09-30'");
//		for (AttendanceTowCollect attendanceTowCollect : attendance) {
//			attendanceTowCollect.setTime(Util.paixu1(attendanceTowCollect.getTime()));
//			totalDao.update2(attendanceTowCollect);
//		}
		//chuliShuifa();
		
		//周五上午下午定时提醒扫地
//		String times = Util.getDateTime("HH:mm");
//		if(totalDao.getCount("from CompanyInfo where name = '上海红湖排气系统有限公司'")>0){
//			if("星期五".equals(Util.getDateTime("E"))){
//				List<String> codess = new ArrayList<String>();
//				if("09:31".compareTo(times)>0&&times.compareTo("09:29")>0){
//					codess = totalDao.query("select code from Users where dept = '信息' and onWork = '在职' and name not in ('贾辉辉') and dept = '信息'");
//					RtxUtil.sendNoLoginNotify(codess, "周五了，小刘。你带着他们几个把办公室卫生打扫一下。                                                \t\t\n----才总。", "系统消息", "0", "0");
//				}else if("14:01".compareTo(times)>0&&times.compareTo("13:59")>0){
//					codess = totalDao.query("select code from Users where dept = '信息' and onWork = '在职' and name not in ('贾辉辉') and dept = '信息' ");
//					RtxUtil.sendNoLoginNotify(codess, "想开黑？先把办公室打扫了！想放假？先把办公室打扫了！想睡懒觉？先把办公室打扫了！", "系统消息", "0", "0");
//				}
//			}
//		}
		
	}

	/**
	 * 处理水阀
	 */
	private void chuliShuifa() {
		String times = Util.getDateTime("HH:mm");
		if("06:01".compareTo(times)>0&&times.compareTo("05:59")>0){
			List<AccessEquipment> aces = totalDao.query("from AccessEquipment where equipmentDaoType = '水阀'");
			for (AccessEquipment a : aces) {
				if(a!=null){
					Integer dport = Integer.parseInt(a.getEquipmentPort());
					Socket s = null;
					try {
						s = new Socket(a.getEquipmentIP(), dport);
						s.setSoTimeout(15000);
						BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s
								.getOutputStream()));
						bw.write(02);
						bw.flush();
						InputStream in = s.getInputStream();
						BufferedInputStream bis = new BufferedInputStream(in);
						byte[] shuiZhi = new byte[1];// 开始接受5位16进制的水流量
						bis.read(shuiZhi);// 读取数据
						String mes = Util.byteArrayToHexString(shuiZhi)
								.replaceAll(" ", "");
						System.out.println("[Client " + mes + "]: " + mes);
						StringBuffer buffer1 = new StringBuffer();
						DoorBangDingServerImpl.caeLogInfor(buffer1.append("打开"), "打开", "", a.getEquipmentName(), a.getEquipmentDaoType(), a.getEquipmentLocation(), a.getId(), a.getEquipmentIP());
						a.setState("打开");
						totalDao.update2(a);//更新状态
						bw.close();
						s.close();
					} catch (Exception e) {
						try {
							if(s!=null)
								s.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						e.printStackTrace();
						DoorBangDingServerImpl.caeLogInfor(new StringBuffer().append("状态为开,操作无效！"), "打开", "", a.getEquipmentName(), a.getEquipmentDaoType(), a.getEquipmentLocation(), a.getId(), a.getEquipmentIP());
					}
				}
			}
		}else if("18:01".compareTo(times)>0&&times.compareTo("17:59")>0){
			List<AccessEquipment> aces = totalDao.query("from AccessEquipment where equipmentDaoType = '水阀'");
			for (AccessEquipment a : aces) {
				if(a!=null){
					Integer dport = Integer.parseInt(a.getEquipmentPort());
					Socket s = null;
					try {
						s = new Socket(a.getEquipmentIP(), dport);
						s.setSoTimeout(15000);
						BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s
								.getOutputStream()));
						bw.write(04);
						bw.flush();
						InputStream in = s.getInputStream();
						BufferedInputStream bis = new BufferedInputStream(in);
						byte[] shuiZhi = new byte[5];// 开始接受5位16进制的水流量
						bis.read(shuiZhi);// 读取数据
						String mes = Util.byteArrayToHexString(shuiZhi)
								.replaceAll(" ", "");
						System.out.println("[Client " + mes + "]: " + mes);
						Float zhi = 0f;
						StringBuffer buffer1 = new StringBuffer();
						for (int i = 0; i < shuiZhi.length; i++) {buffer1.append(shuiZhi[i]);}
						String shuiZhis = buffer1.toString();
						try {
							zhi = Float.valueOf(shuiZhis);
						} catch (Exception e) {
						}
						DoorBangDingServerImpl.caeLogInfor(buffer1.append("本次使用水量："+zhi/10+"升 "+mes), "关闭", zhi/10+"", a.getEquipmentName(), a.getEquipmentDaoType(), a.getEquipmentLocation(), a.getId(), a.getEquipmentIP());
						Float ff = 0f;
						if(a.getOperationNote()==null||"".equals(a.getOperationNote())){
							a.setOperationNote(zhi/10+"");
						}else {
							try {
								ff = zhi/10 + Float.valueOf(a.getOperationNote().replaceAll(" ", ""));
								a.setOperationNote(ff+"");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						a.setState("关闭");
						totalDao.update2(a);//更新库位状态
						bw.close();
						s.close();
					} catch (Exception e) {
						try {
							if(s!=null)
								s.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						e.printStackTrace();
						DoorBangDingServerImpl.caeLogInfor(new StringBuffer().append("状态为关,操作无效！"), "关闭", "", a.getEquipmentName(), a.getEquipmentDaoType(), a.getEquipmentLocation(), a.getId(), a.getEquipmentIP());
					}
				}
			}
		}else if("17:31".compareTo(times)>0&&times.compareTo("06:59")>0){
			List<AccessEquipment> aces = totalDao.query("from AccessEquipment where equipmentDaoType = '水阀'");
			for (AccessEquipment a : aces) {
				if(a!=null){
					Integer dport = Integer.parseInt(a.getEquipmentPort());
					Socket s = null;
					try {
						s = new Socket(a.getEquipmentIP(), dport);
						s.setSoTimeout(15000);
						BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s
								.getOutputStream()));
						bw.write(04);
						bw.flush();
						InputStream in = s.getInputStream();
						BufferedInputStream bis = new BufferedInputStream(in);
						byte[] shuiZhi = new byte[5];// 开始接受5位16进制的水流量
						bis.read(shuiZhi);// 读取数据
						String mes = Util.byteArrayToHexString(shuiZhi)
								.replaceAll(" ", "");
						System.out.println("[Client " + mes + "]: " + mes);
						Float zhi = 0f;
						StringBuffer buffer1 = new StringBuffer();
						for (int i = 0; i < shuiZhi.length; i++) {buffer1.append(shuiZhi[i]);}
						String shuiZhis = buffer1.toString();
						try {
							zhi = Float.valueOf(shuiZhis);
						} catch (Exception e) {
						}
						Float ff = 0f;
						if(a.getOperationNote()==null||"".equals(a.getOperationNote())){
							a.setOperationNote(zhi/10+"");
						}else {
							try {
								ff = zhi/10 + Float.valueOf(a.getOperationNote().replaceAll(" ", ""));
								a.setOperationNote(ff+"");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						totalDao.update2(a);//更新库位状态
						DoorBangDingServerImpl.caeLogInfor(buffer1.append("本次使用水量："+zhi/10+"升。 "+"mes:"+mes), "系统采集", zhi/10+"", a.getEquipmentName(), a.getEquipmentDaoType(), a.getEquipmentLocation(), a.getId(), a.getEquipmentIP());
						Thread.sleep(500);
						bw.write(02);
						bw.flush();
						bw.close();
						s.close();
					} catch (Exception e) {
						e.printStackTrace();
						try {
							if(s!=null)
								s.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println("超时异常");
					}
//					if ("true".equals(message)) {
//						a.setState("关闭");
//						a.setOperationNote(a.getOperationNote()+"<br/>"+"系统，关门，"+Util.getDateTime());
//						if(totalDao.update(a)){//更新库位状态
//						}else totalDao.update2(a);
//					}
				}
			}
		}
	}

	/**
	 * 处理进门车辆
	 */
	private void chuliCheliang() {
		List<AccessRecords> records = totalDao.query("from AccessRecords where recordisIn = '内部' and recordStatus = '已开门' " +
				"and addTime > '2018-04-01' and inmarkId <> '-1'");
		if (records!=null&&records.size()>0) {
			for (AccessRecords aR : records) {
				if(AttendanceTowServerImpl.addAttendanceTow(aR.getInmarkId(), aR.getAddTime().substring(0, 10), aR.getAddTime().substring(11, 16), "正常","大门",aR.getOpenType()
						).equals("true")){
					aR.setRecordStatus("已通过");
					aR.setEnterTime(Util.getDateTime());
					totalDao.update2(aR);
					
				}
			}
		}
	}

	@Override
	public void closeShuiFa(){
		List<AccessEquipment> ace = totalDao.query("from AccessEquipment where equipmentDaoType = '水阀'");
		for (AccessEquipment a : ace) {
			if(a!=null){
				Integer dport = Integer.parseInt(a.getEquipmentPort());
				Socket s = null;
				try {
					s = new Socket(a.getEquipmentIP(), dport);
					s.setSoTimeout(15000);
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s
							.getOutputStream()));
					bw.write(04);
					bw.flush();
					InputStream in = s.getInputStream();
					BufferedInputStream bis = new BufferedInputStream(in);
					byte[] shuiZhi = new byte[5];// 开始接受5位16进制的水流量
					bis.read(shuiZhi);// 读取数据
					String mes = Util.byteArrayToHexString(shuiZhi)
							.replaceAll(" ", "");
					System.out.println("[Client " + mes + "]: " + mes);
					Float zhi = 0f;
					StringBuffer buffer1 = new StringBuffer();
					for (int i = 0; i < shuiZhi.length; i++) {buffer1.append(shuiZhi[i]);}
					String shuiZhis = buffer1.toString();
					try {
						zhi = Float.valueOf(shuiZhis);
					} catch (Exception e) {
					}
					DoorBangDingServerImpl.caeLogInfor(buffer1.append("本次使用水量："+zhi/10+"升"), "系统", zhi/10+"", a.getEquipmentName(), a.getEquipmentDaoType(), a.getEquipmentName(), a.getId(), a.getEquipmentIP());
					Thread.sleep(500);
					bw.write(02);
					bw.flush();
					bw.close();
					s.close();
				} catch (Exception e) {
					e.printStackTrace();
					try {
						if(s!=null)
							s.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("超时异常");
				}
//				if ("true".equals(message)) {
//					a.setState("关闭");
//					a.setOperationNote(a.getOperationNote()+"<br/>"+"系统，关门，"+Util.getDateTime());
//					if(totalDao.update(a)){//更新库位状态
//					}else totalDao.update2(a);
//				}
			}
		}
	}
	
	@Override
	public String closeDeng(AccessEquipment accessEquipment) {
		// TODO Auto-generated method stub
		if(accessEquipment!=null){
			AccessEquipment ac2 = getbyIdAccessEquipment(accessEquipment.getId());
			if(ac2!=null){
				List<OneLight> lights = totalDao.query("from OneLight where lightIP = ?", ac2.getEquipmentIP());
				if(lights!=null&&lights.size()>0){
					int [] lit = new int[lights.size()];
					for (int i = 0; i < lit.length; i++) {
						OneLight oneLight = lights.get(i);
						lit[i] = Util.swiInstruction(oneLight.getLightZhiLing(),0);
					}
					String mess = Util.SendZ(ac2.getEquipmentIP(), Integer.parseInt(ac2.getEquipmentPort()), lit);
					if("true".equals(mess)){
						for (OneLight oneLight : lights) {
							oneLight.setLightStatus("关闭");
							totalDao.update(oneLight);
						}
						return "关闭成功！";
					}else {
						return "连接异常，关闭失败！";
					}
				}else {
					return "没有可关闭的灯！";
				}
			}
		}
		return null;
	}

	@Override
	public boolean dangQuan(Integer id) {
		// TODO Auto-generated method stub
		int i = totalDao.getCount("from ModuleFunction where functionLink = 'AccessEquipmentAction_selectPrice.action' and id in (select m.id from ModuleFunction m join m.users u where u.id = ?)",id);
		if(i>0){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List findAllManager(Integer id) {
		// TODO Auto-generated method stub
		List list = null;
		if (id != null && id > 0) {
			list = totalDao.query("from FileManager where danganId = ?", id+"");
			if(list != null && list.size() > 0){
				return list;
			}
		}
		return null;
	}

	@Override
	public List findCwCertificate(CwCertificate cwCertificate,Integer id) {
		// TODO Auto-generated method stub
		List list = null;
		if (id != null && id > 0) {
			if(cwCertificate==null){
				cwCertificate = new CwCertificate();
			}
			String hql = totalDao.criteriaQueries(cwCertificate, " danganguiId = "+id);
			list = totalDao.query(hql);
			if(list != null && list.size() > 0){
				return list;
			}
		}
		return null;
	}

	@Override
	public List findAllAceShui(String s) {
		// TODO Auto-generated method stub
		return totalDao.query("from AccessEquipment where equipmentDaoType = ? order by id desc", s);
	}
	/**
	 * 查询所有巡更时段
	 * @param xungengTime
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findAllXungengTime(XungengTime xungengTime,int pageNo, int pageSize) {
		if (xungengTime == null) {
			xungengTime= new XungengTime();
		}
		String hql = totalDao.criteriaQueries(xungengTime,null,null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}
	/**
	 * 查询所有巡更记录
	 * @param xungengRecord
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findAllXungengRecord(XungengRecord xungengRecord,int pageNo, int pageSize) {
		if (xungengRecord == null) {
			xungengRecord= new XungengRecord();
		}
		String hql = totalDao.criteriaQueries(xungengRecord,null);
		int count = totalDao.getCount(hql);
		hql+=" order by dateNow desc,id desc";
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}
	/**
	 *  添加巡更时段
	 * @param xungengTime
	 * @return
	 */
	public String addXungengTime(XungengTime xungengTime){
		if(totalDao.save(xungengTime)){
			return "添加成功";
		}else{
			return "添加失败";
		}
	}
	/**
	 * 删除巡更时段
	 * @param xungengTimeid
	 * @return
	 */
	public String delet(Integer xungengTimeid){
		XungengTime xungengTime = (XungengTime)totalDao.get(XungengTime.class, xungengTimeid);
		if(totalDao.delete(xungengTime)){
			return "删除成功";
		}else{
			return "删除失败";
		}
	}

	@Override
	public String addResAccess(ResAccess resAccess) {
		// TODO Auto-generated method stub
		if(resAccess!=null){
			ToolCabine cabine = null;
			if("存取".equals(resAccess.getType())){
				if(resAccess.getCunCodes()!=null){
					int i = totalDao.getCount("from ResAccess where cunCodes = ? and yxType = 0", resAccess.getCunCodes());
					if(i>0){
						return "快递单添加重复！";
					}
				}
			}else if ("寄取".equals(resAccess.getType())) {
				resAccess.setCunCodes(Util.yanNumber(6));
				if(resAccess.getDaGuiId()!=null){
					cabine = (ToolCabine) totalDao.getObjectById(ToolCabine.class, resAccess.getDaGuiId());
					if(cabine==null)
						return "选中柜子异常";
					resAccess.setDaGuiId(cabine.getId());
					resAccess.setDaGuihao(cabine.getCabNumber());
					resAccess.setDaGuiposition(Integer.parseInt(cabine.getCabOpenOrder()));//开柜编号
					resAccess.setAce_Ip(cabine.getCabAceIp());
					cabine.setCabStatus("已满");
				}
			}
			resAccess.setAddTime(Util.getDateTime());//添加时间
			resAccess.setShixiaoTime(Util.getDateTime(10086));//得到7天后的时间
			resAccess.setQuCodes(Util.yanNumber(6));
			resAccess.setYxType(0);
			resAccess.setCuseType(0);
			resAccess.setQuseType(0);
			Users s = Util.getLoginUser();
			if(s!=null){
				resAccess.setAddName(s.getName());
				resAccess.setAddCode(s.getCode());
				resAccess.setAddUserId(s.getId());
			}
			if(totalDao.save(resAccess)){
				if(cabine!=null){
					cabine.setResAccessId(resAccess.getId());
					totalDao.update(cabine);
					//给存件人发送消息
					RtxUtil.sendNotify(resAccess.getAddCode(), "您存与："+resAccess.getDaGuihao()+"号柜的快件, 存物验证码："+resAccess.getCunCodes(), 
							"快递存柜提醒", "0", "0");
				}
				return "添加成功！";
			}else{
				return "添加失败！";
			}
		}
		return null;
	}

	@Override
	public Map<Integer, Object> findResAccess(ResAccess resAccess, int pageNo,
			int pageSize, String tag) {
		// TODO Auto-generated method stub
		String sql = "";
		if ("aD".equals(tag)) {
			Users users = Util.getLoginUser();
			sql += " addUserId = " + users.getId();
		}else if("all".equals(tag)){
		}else {
			sql += " addUserId = 0 ";
		}
		if (resAccess == null) {
			resAccess = new ResAccess();
		}
		String hql = totalDao.criteriaQueries(resAccess, sql);
		hql += " order by id desc";
		List listi = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listi);
		map.put(2, count);
		return map;
	}

	@Override
	public ResAccess findResAccessbyId(Integer id) {
		// TODO Auto-generated method stub
		return (ResAccess) totalDao.getObjectById(ResAccess.class, id);
	}

	@Override
	public String updateResAccess(ResAccess resAccess) {
		// TODO Auto-generated method stub
		if(resAccess.getCuseType()!=0){
			return "快递已存，取消失败！";
		}
		resAccess.setYxType(3);
		if("寄取".equals(resAccess.getType())){
			ToolCabine cabine = (ToolCabine) totalDao.getObjectById(ToolCabine.class, resAccess.getDaGuiId());
			if(cabine!=null){
				cabine.setResAccessId(null);
				cabine.setCabStatus("未满");
				totalDao.update(cabine);
			}
		}
		if(totalDao.update(resAccess)){
			return "取消成功！";
		}else {
			return "操作失败！";
		}
	}

	@Override
	public List<String> findCHuankou() {
		// TODO Auto-generated method stub
		return SerialPortManager.findPorts();
	}
	
	@Override
	public SerialPort openSp() throws PortInUseException {
		// TODO Auto-generated method stub
		return SerialPortManager.openPort("",12334);
	}
	
	@Override
	public SerialPort openAndListener(String commName,Integer baudrate,final String sendtype) throws PortInUseException {
		final SerialPort mSerialport = SerialPortManager.openPort(commName, baudrate);
		if (mSerialport != null) {
			// 添加串口监听
			SerialPortManager.addListener(mSerialport, new SerialPortManager.DataAvailableListener() {
				@Override
				public void dataAvailable() {
					byte[] data = null;
					try {
						// 读取串口数据
						data = SerialPortManager.readFromPort(mSerialport);
//								System.out.println(ByteUtils.byteArrayToHexString(data) + "\r\n");
						// 以字符串的形式接收数据 
						if ("ASCII".equals(sendtype)) {
							System.out.println(new String(data) + "\r\n");
						}
						// 以十六进制的形式接收数据
						if ("Hex".equals(sendtype)) {
							System.out.println(ByteUtils.byteArrayToHexString(data) + "\r\n");
						}
					} catch (Exception e) {
//							ShowUtils.errorMessage(e.toString());
//							// 发生读取错误时显示错误信息后退出系统
//							System.exit(0);
					}
				}
			});
			return mSerialport;
		}else {
			return null;
		}
	}

	@Override
	public void addSerialSend(byte[] data, SerialPort mSerialport) {
		// TODO Auto-generated method stub
		if(mSerialport!=null){
			String com = mSerialport.getName().substring(7, 8);
			SerialPtReceive ptReceive = new SerialPtReceive();
			ptReceive.setInfor(new String(data));
			ptReceive.setInforHex(new String(ByteUtils.byteArrayToHexString(data)));
			ptReceive.setAddTime(Util.getDateTime());
			if(!"00".equals(ptReceive.getInforHex())){
				ptReceive.setStatus(0);
				ptReceive.setComNum(Integer.parseInt(com));
				totalDao.save2(ptReceive);
			}
		}
	}

	@Override
	public String updateShow(String receiveType, SerialPort mSerialport) {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();
		int i = Integer.parseInt(mSerialport.getName().substring(7, 8));
		String sel = "from SerialPtReceive where status = 0 and comNum = ? and addTime > ?";
		List<SerialPtReceive> ptReceiveList =  totalDao.query(sel, i,Util.getDateTime("yyyy-MM-dd"));
		if(ptReceiveList!=null&&ptReceiveList.size()>0){
			if ("ASCII".equals(receiveType)) {
				for (SerialPtReceive sp : ptReceiveList) {
					buffer.append(sp.getInfor()+"|");
					sp.setStatus(1);
					totalDao.update2(sp);
				}
			}else if ("Hex".equals(receiveType)) {
				for (SerialPtReceive sp : ptReceiveList) {
					buffer.append(sp.getInforHex()+"|");
					sp.setStatus(1);
					totalDao.update2(sp);
				}
			}
		}
		return buffer.toString();
	}
	
}
