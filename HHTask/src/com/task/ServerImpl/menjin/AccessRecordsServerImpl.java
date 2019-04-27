package com.task.ServerImpl.menjin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.DaoImpl.TotalDaoImpl;
import com.task.Server.menjin.AccessRecordsServer;
import com.task.entity.Users;
import com.task.entity.menjin.AccessLogInfor;
import com.task.entity.menjin.AccessRecords;
import com.task.entity.menjin.CarInOutType;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class AccessRecordsServerImpl implements AccessRecordsServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public Map<Integer, Object> findAccessRecordsByCondition(
			AccessRecords accessRecord, int pageNo, int pageSize, String tag) {
		// TODO Auto-generated method stub
 		if (accessRecord == null) {
			accessRecord = new AccessRecords();
		}
		String sql = "";
		if ("code".equals(tag)) {
			Users users = Util.getLoginUser();
			sql = " inCode='" + users.getCode() + "' and recordStatus = '已通过'";
		}
		String hql = totalDao.criteriaQueries(accessRecord, sql);
		hql += " order by id desc";
		List listInt = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		accessRecord = null;
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listInt);
		map.put(2, count);
		return map;
	}

	// 在静态方法调用totalDao
	private static TotalDao createTotol() {
		// 获得totalDao
		TotalDao totalDao = TotalDaoImpl.findTotalDao();
		AccessServerImpl acc = new AccessServerImpl();
		acc.setTotalDao(totalDao);
		return totalDao;
	}
	
	/**
	 * @author Li_Cong
	 * @param recordType
	 *            车牌/卡号/验证码
	 * @param recordContents
	 *            内容
	 * @param recordisIn
	 *            内部/外部/紧急/常访
	 * @param InCode
	 *            工号
	 * @param InId
	 *            userID
	 * @param InDept
	 *            部门
	 * @param InName
	 *            名称
	 * @param OpenType
	 *            进门/出门
	 * @param EquipmentDaoType
	 *            车道类型
	 * @param OutOfPosition
	 *            车道位置
	 * @param AsWeam_id
	 *            摄像头Id
	 * @param AsWeam_ip
	 *            摄像头IP
	 * @param AsEqt_id
	 *            设备id
	 * @param AsEqt_ip
	 *            设备IP
	 * @param waitCheck
	 *            检车（待检查|null）
	 */
	public static AccessRecords createAccessRecordCar(String recordType,
			String recordContents, String recordisIn, String InCode,
			Integer InId, String InDept, String InName,String OpenType,String EquipmentDaoType,String OutOfPosition, Integer AsWeam_id,
			String AsWeam_ip, Integer AsEqt_id, String AsEqt_ip, String waitCheck) {
		TotalDao totalDao = createTotol();
		AccessRecords accessRecordZong = new AccessRecords();
		accessRecordZong.setRecordType(recordType);
		accessRecordZong.setRecordContents(recordContents);
		if ("车牌".equals(recordType)||("验证码".equals(recordType)&&"车行道".equals(EquipmentDaoType))) {
			accessRecordZong.setRecordStatus("已识别");
		}else {
			accessRecordZong.setRecordStatus("已开门");
		}
		accessRecordZong.setRecordisIn(recordisIn);
		accessRecordZong.setInCode(InCode);
		accessRecordZong.setInId(InId);
		accessRecordZong.setInDept(InDept);
		accessRecordZong.setInName(InName);
		accessRecordZong.setOpenType(OpenType);
		accessRecordZong.setEquipmentDaoType(EquipmentDaoType);
		accessRecordZong.setOutOfPosition(OutOfPosition);
		accessRecordZong.setAsWeam_id(AsWeam_id);
		accessRecordZong.setAsWeam_ip(AsWeam_ip);
		accessRecordZong.setAsEqt_id(AsEqt_id);
		accessRecordZong.setAsEqt_ip(AsEqt_ip);// 设备IP很重要
		accessRecordZong.setWaitCheck(waitCheck);//待检查|null
		accessRecordZong.setAddTime(Util.getDateTime());
		if (totalDao.save2(accessRecordZong)) {// 保存
			return accessRecordZong;
		}else {
			return null;
		}
	}
	
	/**
	 * @author Li_Cong 2016-06-24
	 * @param recordType
	 *            车牌/卡号/验证码
	 * @param recordContents
	 *            内容
	 * @param recordisIn
	 *            内部/外部/紧急/常访
	 * @param InCode
	 *            工号
	 * @param InId
	 *            userID
	 * @param InDept
	 *            部门
	 * @param InName
	 *            名称
	 * @param OpenType
	 *            进门/出门
	 * @param EquipmentDaoType
	 *            车道类型
	 * @param OutOfPosition
	 *            车道位置
	 * @param AsWeam_id
	 *            摄像头Id
	 * @param AsWeam_ip
	 *            摄像头IP
	 * @param AsEqt_id
	 *            设备id
	 * @param AsEqt_ip
	 *            设备IP
	 * @param waitCheck
	 *            检车（待检查|null）
	 * @param inmarkId
	 *            内部员工卡
	 * @param isKong
	 *            是否控制灯
	 */
	public static AccessRecords createAccessRecordCarCardId(String recordType,
			String recordContents, String recordisIn, String InCode,
			Integer InId, String InDept, String InName,String OpenType,String EquipmentDaoType,String OutOfPosition, Integer AsWeam_id,
			String AsWeam_ip, Integer AsEqt_id, String AsEqt_ip, String waitCheck, String inmarkId, String isKong) {
		TotalDao totalDao = createTotol();
		AccessRecords accessRecordZong = new AccessRecords();
		accessRecordZong.setRecordType(recordType);
		accessRecordZong.setRecordContents(recordContents);
		if ("车牌".equals(recordType)||("验证码".equals(recordType)&&"车行道".equals(EquipmentDaoType))) {
			accessRecordZong.setRecordStatus("已识别");
		}else {
			accessRecordZong.setRecordStatus("已开门");
		}
		accessRecordZong.setRecordisIn(recordisIn);
		accessRecordZong.setInCode(InCode);
		accessRecordZong.setInId(InId);
		accessRecordZong.setInDept(InDept);
		accessRecordZong.setInName(InName);
		accessRecordZong.setInmarkId(inmarkId);//增加卡号
		accessRecordZong.setOpenType(OpenType);
		accessRecordZong.setEquipmentDaoType(EquipmentDaoType);
		accessRecordZong.setOutOfPosition(OutOfPosition);
		accessRecordZong.setAsWeam_id(AsWeam_id);
		accessRecordZong.setAsWeam_ip(AsWeam_ip);
		accessRecordZong.setAsEqt_id(AsEqt_id);
		accessRecordZong.setAsEqt_ip(AsEqt_ip);// 设备IP很重要
		accessRecordZong.setWaitCheck(waitCheck);//待检查|null
		accessRecordZong.setIsKong(isKong);//灯控标识
		accessRecordZong.setAddTime(Util.getDateTime());
		if (totalDao.save2(accessRecordZong)) {// 保存
			return accessRecordZong;
		}else {
			return null;
		}
	}
	

	/**
	 * @author Li_Cong
	 * @param recordType
	 *            车牌/卡号/验证码
	 * @param recordContents
	 *            内容
	 * @param recordisIn
	 *            内部/外部/紧急/常访
	 * @param InCode
	 *            工号
	 * @param InId
	 *            userID
	 * @param InDept
	 *            部门
	 * @param InName
	 *            名称
	 * @param OpenType
	 *            进门/出门
	 * @param EquipmentDaoType
	 *            车道类型
	 * @param OutOfPosition
	 *            车道位置
	 * @param AsWeam_id
	 *            摄像头Id
	 * @param AsWeam_ip
	 *            摄像头IP
	 * @param AsEqt_id
	 *            设备id
	 * @param AsEqt_ip
	 *            设备IP
	 * @param waitCheck
	 *            检车（待检查|null）
	 * @param banciId 
	 * 			  是否添加考勤(有无班次id)
	 */
	public static AccessRecords createAccessRecordCarKaoQin(String recordType,
			String recordContents, String recordisIn, String InCode,
			Integer InId, String InDept, String InName,String OpenType,String EquipmentDaoType,String OutOfPosition, Integer AsWeam_id,
			String AsWeam_ip, Integer AsEqt_id, String AsEqt_ip, String waitCheck,Integer banciId) {
		TotalDao totalDao = createTotol();
		AccessRecords accessRecordZong = new AccessRecords();
		accessRecordZong.setRecordType(recordType);
		accessRecordZong.setRecordContents(recordContents);
		if ("车牌".equals(recordType)||("验证码".equals(recordType)&&"车行道".equals(EquipmentDaoType))) {
			accessRecordZong.setRecordStatus("已识别");
		}else {
			accessRecordZong.setRecordStatus("已开门");
		}
		accessRecordZong.setRecordisIn(recordisIn);
		accessRecordZong.setInCode(InCode);
		accessRecordZong.setInId(InId);
		accessRecordZong.setInDept(InDept);
		accessRecordZong.setInName(InName);
		accessRecordZong.setOpenType(OpenType);
		accessRecordZong.setEquipmentDaoType(EquipmentDaoType);
		accessRecordZong.setOutOfPosition(OutOfPosition);
		accessRecordZong.setAsWeam_id(AsWeam_id);
		accessRecordZong.setAsWeam_ip(AsWeam_ip);
		accessRecordZong.setAsEqt_id(AsEqt_id);
		accessRecordZong.setAsEqt_ip(AsEqt_ip);// 设备IP很重要
		accessRecordZong.setWaitCheck(waitCheck);//待检查|null
		accessRecordZong.setAddTime(Util.getDateTime());
		accessRecordZong.setBanciId(banciId);//班次id
		if (totalDao.save2(accessRecordZong)) {// 保存
			return accessRecordZong;
		}else {
			return null;
		}
	}
	
	/**
	 * 添加车辆进出类型表
	 * @author Li_Cong
	 * @param carPai
	 *            车牌
	 */
	public static void createCarInOutType(String carPai) {
		TotalDao totalDao = createTotol();
		CarInOutType carInOutType2 = (CarInOutType) totalDao.getObjectByCondition("from CarInOutType where carPai=?", carPai);
		if (carInOutType2==null) {
			carInOutType2 = new CarInOutType();
			carInOutType2.setAddTime(Util.getDateTime());
			carInOutType2.setCarPai(carPai);
			totalDao.save2(carInOutType2);// 添加车牌内外状态表信息（CarInOutType）
		}else {
			System.out.println("已存在");
		}
	}

	@Override
	public Map<Integer, Object> findCarInOutTypeByCondition(
			CarInOutType carInOutType, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (carInOutType == null) {
			carInOutType = new CarInOutType();
		}
		String sql = "";
		String hql = totalDao.criteriaQueries(carInOutType, sql);
		hql += " order by updateTime desc";
		List listInt = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listInt);
		map.put(2, count);
		return map;
	}

	@Override
	public String updateCarInOutType(CarInOutType carInOutType) {
		// TODO Auto-generated method stub
		CarInOutType carInOutType2 = (CarInOutType) totalDao.getObjectById(CarInOutType.class,carInOutType
				.getId());
		if (carInOutType2!=null) {
			BeanUtils.copyProperties(carInOutType, carInOutType2,
					new String[] { "id", "addTime" });
			carInOutType2.setUpdateTime(Util.getDateTime());
			if (totalDao.update(carInOutType2)) {
				return "true";
			}
		}else {
			return "不存在该对象,修改失败";
		}
		return "修改失败";
	}

	@Override
	public CarInOutType getByIdCarInOutType(Integer integer) {
		// TODO Auto-generated method stub
		if (integer>0) {
			return (CarInOutType) totalDao.getObjectById(CarInOutType.class,integer);
		}
		return null;
	}

	@Override
	public Map<Integer, Object> findAccessLogInforByCondition(
			AccessLogInfor accessLogInfor, int pageNo, int pageSize, String tag) {
		// TODO Auto-generated method stub
		String sql = "";
		if (accessLogInfor == null) {
			accessLogInfor = new AccessLogInfor();
		}else {
			if (accessLogInfor.getAceId()!=null&&accessLogInfor.getAceId()>0) {
				sql = "aceId = " + accessLogInfor.getAceId();
			}
		}
		String hql = totalDao.criteriaQueries(accessLogInfor, sql ,"aceId");
		if("shui".equals(tag)){
			hql += " and inOutType = '水阀' and cardId <> '打开' and yanzheng not in ('','0.0') and yanzheng is not null ";
		}
		hql += " and inOutType <> '' order by id desc";
		List listi = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listi);
		map.put(2, count);
		return map;
	}

	@Override
	public Map<Integer, Object> findAccessLogInforByBaoJingCondition(
			AccessLogInfor accessLogInfor, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String sql = "";
		if (accessLogInfor == null) {
			accessLogInfor = new AccessLogInfor();
		}else {
			if (accessLogInfor.getAceId()!=null&&accessLogInfor.getAceId()>0) {
				sql = "aceId = " + accessLogInfor.getAceId();
			}
		}
		String hql = totalDao.criteriaQueries(accessLogInfor, sql ,"aceId");
		hql += " and yanzheng = 'FF' order by id desc";
		List listi = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listi);
		map.put(2, count);
		return map;
	}

}
