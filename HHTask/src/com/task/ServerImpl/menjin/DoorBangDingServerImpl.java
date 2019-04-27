package com.task.ServerImpl.menjin;

import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.DaoImpl.TotalDaoImpl;
import com.task.Server.menjin.DoorBangDingServer;
import com.task.entity.Users;
import com.task.entity.menjin.AccessLogInfor;
import com.task.entity.menjin.DoorBangDing;
import com.task.entity.menjin.DoorUseRecording;
import com.task.util.Util;

@SuppressWarnings( { "unchecked" })
public class DoorBangDingServerImpl implements DoorBangDingServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 在静态方法调用totalDao
	private static TotalDao createTotol() {
		// 获得totalDao
		TotalDao totalDao = TotalDaoImpl.findTotalDao();
		DoorBangDingServerImpl dbd = new DoorBangDingServerImpl();
		dbd.setTotalDao(totalDao);
		return totalDao;
	}

	// 添加
	public static boolean addDoorBangDing(Integer integer, Integer integer2) {
		// TODO Auto-generated method stub
		TotalDao totalDao = createTotol();
		DoorBangDing bangDing = new DoorBangDing();
		bangDing.setFk_user_id(integer);
		bangDing.setFk_security_id(integer2);
		return totalDao.save2(bangDing);
	}

	// 删除
	public static boolean deleteDoorBangDing(Integer integer, Integer integer2) {
		// TODO Auto-generated method stub
		TotalDao totalDao = createTotol();
		List doorBangDing = totalDao.query(
				"from DoorBangDing where user_id = ? and acEq_id = ?", integer,
				integer2);
		if (doorBangDing != null && doorBangDing.size() > 0) {
			DoorBangDing bangDing = (DoorBangDing) doorBangDing.get(0);
			return totalDao.delete(bangDing);
		}
		return false;
	}

	@Override
	public Map<Integer, Object> findGuardCardByCondition(
			DoorBangDing doorBangDing, int parseInt, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoorBangDing getByIdDoorBangDing(DoorBangDing doorBangDing) {
		// TODO Auto-generated method stub
		doorBangDing = (DoorBangDing) totalDao.getObjectById(
				DoorBangDing.class, doorBangDing.getId());
		return doorBangDing;
	}

	@Override
	public boolean updateDoorBangDing(DoorBangDing doorBangDing) {
		// TODO Auto-generated method stub
		return false;
	}

	/******************************* 门使用记录表 **********************************/
	public static boolean addDoorUseRecording(String accessName,
			String accessNum, String cardNum, String name) {
		// TODO Auto-generated method stub
		TotalDao totalDao = createTotol();
		DoorUseRecording ur = new DoorUseRecording();
		// ur.setAccessName(accessName);// 门禁名
		// ur.setAccessNum(accessNum);// 门禁编号
		ur.setCardNum(cardNum);// 卡号
		ur.setName(name);// 姓名
		ur.setCardNum(Util.yanNumber(6));// 验证码
		ur.setUseStatus("使用中");// 使用状态
		ur.setStartTime(Util.getDateTime());
		if (totalDao.save2(ur)) {
			List user = totalDao.query(
					"from Users where name = ? and cardId = ?", name, cardNum);
			if (user != null && user.size() > 0) {
				Users users = (Users) user.get(0);
				// users.setDoorStatus(ur.getAccessName() + "-" +
				// ur.getAccessNum());// 将门禁名称和编号保存至users表
				if (totalDao.update2(users)) {
					// 将记录表Id保存到中间表DoorBangDing中
					List listdbd = totalDao
							.query(
									"from DoorBangDing where user_id = ? and acEq_id = (select id from AccessEquipment where equipmentName = ? and equipmentNum = ?)",
									users.getId(), accessName, accessNum);
					if (listdbd != null && listdbd.size() > 0) {
						DoorBangDing dbd = (DoorBangDing) listdbd.get(0);
						dbd.setFk_security_id(ur.getId());
						return totalDao.update2(dbd);
					}
				}
			}
		}
		return false;
	}

	// 修改
	public static boolean updateDoorUseRecording(Integer in) {
		// TODO Auto-generated method stub
		TotalDao totalDao = createTotol();
		String nowTime = Util.getDateTime();
		List dooruse = totalDao.query("from DoorUseRecording where id = ?", in);
		if (dooruse != null && dooruse.size() > 0) {
			DoorUseRecording doorUseRecording = (DoorUseRecording) dooruse
					.get(0);
			doorUseRecording.setEndTime(nowTime);
			doorUseRecording.setUseStatus("结束");
			long l = Util
					.getWorkTime1(doorUseRecording.getStartTime(), nowTime);// 计算使用时间
			doorUseRecording.setUseDateNum(l + "");// 间隔时间毫秒数
			doorUseRecording.setUseDate(Util.formatDuring(l));
			if (totalDao.update2(doorUseRecording)) {
				List list1 = totalDao.query(
						"from DoorBangDing where security_id = ?",
						doorUseRecording.getId());
				if (list1 != null && list1.size() > 0) {
					DoorBangDing bangDing = (DoorBangDing) list1.get(0);
					bangDing.setFk_security_id(null);
					return totalDao.update2(bangDing);
				}
			}
		}
		return false;
	}

	/**
	 * 添加使用记录表，更新中间表状态和记录ID
	 * 
	 * @param i
	 * @param ding
	 * @return
	 */
	public static boolean addInDoorUseRecording(Users i, DoorBangDing ding)
			throws Exception {
		if (i != null && ding != null) {
			TotalDao totalDao = createTotol();
			DoorUseRecording doorUseRecording = new DoorUseRecording();
			doorUseRecording.setAccessId(ding.getFk_acEq_id());
			doorUseRecording.setName(i.getName());// 刷卡人姓名
			doorUseRecording.setCardNum(i.getCardId());// 卡号
			doorUseRecording.setStartTime(Util.getDateTime());// 开始时间
			doorUseRecording.setUseStatus("使用中");// 使用状态
			if (totalDao.save2(doorUseRecording)) {
				ding.setFk_security_id(doorUseRecording.getId());
				ding.setStatus("内");
				if (totalDao.update2(ding))
					return true;
				else
					throw new Exception("进门回调失败！请重新刷卡");
			}
		}
		return false;
	}

	/**
	 * 添加使用记录表，更新中间表状态和记录ID
	 * 
	 * @param i
	 * @param ding
	 * @param inTime
	 *            进入时间
	 * @return
	 */
	public static boolean addOutDoorUseRecording(Users i, DoorBangDing ding,
			String inTime) throws Exception {
		if (i != null && ding != null && inTime != null && !"".equals(inTime)) {
			TotalDao totalDao = createTotol();
			DoorUseRecording doorUseRecording = new DoorUseRecording();
			doorUseRecording.setAccessId(ding.getFk_acEq_id());// 设备Id
			doorUseRecording.setName(i.getName());// 刷卡人姓名
			doorUseRecording.setCardNum(i.getCardId());// 卡号
			doorUseRecording.setStartTime(inTime);// 开始时间
			doorUseRecording.setEndTime(Util.getDateTime());// 结束时间
			Long longs = Util.getWorkTime1(inTime, Util.getDateTime());
			doorUseRecording.setUseDateNum(longs.toString());// 使用时长毫秒
			doorUseRecording.setUseDate(Util.formatDuring(longs));// 使用时长
			doorUseRecording.setUseStatus("空闲");// 使用状态
			if (totalDao.save2(doorUseRecording))
				return true;
			else
				throw new Exception("进门回调失败！请重新刷卡");
		}
		return false;
	}

	/**
	 * 添加使用记录表，更新中间表状态和记录ID
	 * 
	 * @param i
	 * @param ding
	 * @param inTime
	 *            进入时间
	 * @return
	 */
	public static Long addOutDoorUseRecording_1(Users i, DoorBangDing ding,
			String inTime) throws Exception {
		if (i != null && ding != null && inTime != null && !"".equals(inTime)) {
			TotalDao totalDao = createTotol();
			DoorUseRecording doorUseRecording = new DoorUseRecording();
			doorUseRecording.setAccessId(ding.getFk_acEq_id());// 设备Id
			doorUseRecording.setName(i.getName());// 刷卡人姓名
			doorUseRecording.setCardNum(i.getCardId());// 卡号
			doorUseRecording.setStartTime(inTime);// 开始时间
			doorUseRecording.setEndTime(Util.getDateTime());// 结束时间
			Long longs = Util.getWorkTime1(inTime, Util.getDateTime());
			doorUseRecording.setUseDateNum(longs.toString());// 使用时长毫秒
			doorUseRecording.setUseDate(Util.formatDuring(longs));// 使用时长
			doorUseRecording.setUseStatus("空闲");// 使用状态
			if (totalDao.save2(doorUseRecording))
				return longs;
			else
				throw new Exception("进门回调失败！请重新刷卡");
		}
		return null;
	}

	/**
	 * 修改使用记录表，更新中间表状态和记录ID
	 * 
	 * @param i
	 * @param ding
	 * @return
	 */
	public static boolean addOutDoorUseRecording(Users i, DoorBangDing ding)
			throws Exception {
		TotalDao totalDao = createTotol();
		if (i != null && ding != null && ding.getFk_security_id() != null) {
			List doorUseRecording = totalDao.query(
					"from DoorUseRecording where name = ? and id = ?", i
							.getName(), ding.getFk_security_id());
			if (doorUseRecording != null && doorUseRecording.size() > 0) {
				DoorUseRecording doorUseRecording2 = (DoorUseRecording) doorUseRecording
						.get(0);
				String endTime = Util.getDateTime();
				doorUseRecording2.setEndTime(endTime);// 结束时间
				Long longs = Util.getWorkTime1(
						doorUseRecording2.getStartTime(), endTime);
				doorUseRecording2.setUseDateNum(longs.toString());// 使用时长毫秒
				doorUseRecording2.setUseDate(Util.formatDuring(longs));// 使用时长
				doorUseRecording2.setUseStatus("空闲");// 使用状态
				if (totalDao.update2(doorUseRecording2)) {
					ding.setFk_security_id(null);
					ding.setStatus("外");
				}
			} else {// 次情况为DoorBangDing记录id不为空，但是查找不到记录数据，证明卡已进门，允许出门
				ding.setFk_security_id(null);
				ding.setStatus("外");
			}
			if (totalDao.update2(ding))
				return true;
			else
				throw new Exception("出门回调失败！请重新刷卡");
		}
		return false;
	}

	/**
	 * 修改使用记录表，更新中间表状态和记录ID
	 * 
	 * @param i
	 * @param ding
	 * @return
	 */
	public static Long addOutDoorUseRecording_1(Users i, DoorBangDing ding)
			throws Exception {
		TotalDao totalDao = createTotol();
		Long longs = null;
		if (i != null && ding != null && ding.getFk_security_id() != null) {
			List doorUseRecording = totalDao.query(
					"from DoorUseRecording where name = ? and id = ?", i
							.getName(), ding.getFk_security_id());
			if (doorUseRecording != null && doorUseRecording.size() > 0) {
				DoorUseRecording doorUseRecording2 = (DoorUseRecording) doorUseRecording
						.get(0);
				String nowTime = Util.getDateTime();
				String endTime = "";
				if (nowTime.substring(0, 10).equals(doorUseRecording2.getStartTime().substring(0,10)))// 为当天记录
					endTime = nowTime;// 默认出门时间为下一次进门刷卡时间
				else// 不为当天记录
					endTime = doorUseRecording2.getStartTime().substring(0,10)+ " 23:59:59";// 默认结束时间为当天凌晨
				doorUseRecording2.setEndTime(nowTime);// 结束时间
				longs = Util.getWorkTime1(doorUseRecording2.getStartTime(),
						endTime);
				doorUseRecording2.setUseDateNum(longs.toString());// 使用时长毫秒
				doorUseRecording2.setUseDate(Util.formatDuring(longs));// 使用时长
				doorUseRecording2.setUseStatus("空闲");// 使用状态
				if (totalDao.update2(doorUseRecording2)) {
					ding.setFk_security_id(null);
					ding.setStatus("外");
				}
			} else {// 次情况为DoorBangDing记录id不为空，但是查找不到记录数据，证明卡已进门，允许出门
				ding.setFk_security_id(null);
				ding.setStatus("外");
			}
			if (totalDao.update2(ding))
				return longs;
			else
				throw new Exception("出门回调失败！请重新刷卡");
		}
		return longs;
	}

	/**
	 * 门禁设备连接日志记录
	 * 
	 * @param builder
	 *            记录信息
	 * @param cardIds
	 *            卡号
	 * @param yanzheng
	 *            验证码
	 * @param username
	 *            姓名
	 * @param inOutType
	 *            进出类型
	 * @param accessname
	 *            设备名称
	 * @param accessId
	 *            设备ID
	 * @param accessIP
	 *            设备IP
	 */
	public static void caeLogInfor(StringBuffer builder, String cardIds,
			String yanzheng, String username, String inOutType,
			String accessname, Integer accessId, String accessIP) {
		if ("0000000000".equals(cardIds)) return;
		TotalDao totalDao = createTotol();
		AccessLogInfor accessLogInfor = new AccessLogInfor();
		accessLogInfor.setInfor(builder.toString());
		accessLogInfor.setCardId(cardIds);
		accessLogInfor.setYanzheng(yanzheng);
		accessLogInfor.setUsername(username);
		accessLogInfor.setInOutType(inOutType);
		accessLogInfor.setAddTime(Util.getDateTime());
		accessLogInfor.setAceName(accessname);
		accessLogInfor.setAceId(accessId);
		accessLogInfor.setAceIp(accessIP);
		totalDao.save2(accessLogInfor);
	}

}
