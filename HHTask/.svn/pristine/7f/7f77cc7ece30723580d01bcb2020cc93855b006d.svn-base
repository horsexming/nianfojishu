package com.task.Server;

import java.io.File;
import java.util.List;

import com.task.entity.AskForLeave;
import com.task.entity.Attendance;
import com.task.entity.AttendanceFu;
import com.task.entity.AttendanceTow;
import com.task.entity.Overtime;
import com.task.entity.banci.BanCiTime;
import com.task.entity.menjin.AccessRecords;
import com.task.entity.menjin.RechargeZhi;

/**
 * @author 刘晓霆
 * @Date 2014-04-17
 */

@SuppressWarnings("unchecked")
public interface AttendanceServer{
	/**
	 * @author 刘晓霆 保存来自考勤信息表的汇总数据
	 */
	public boolean saveAttendance(String tag);
	
	/**
	 * @author Li_Cong
	 * @param tag
	 * @return
	 */
	public boolean saveAttendance_2(String tag);
	/**
	 * @author Li_Cong
	 * @param tag
	 * @return
	 */
	public boolean sendAttendanceThree(String tag);
	/**
	 * @author Li_Cong
	 * @param tag
	 * @return
	 */
	public boolean saveAttendance_4(String tag);
	
	public void huanhuiqu();
	
	/**
	 * 按条件查询考勤分页信息
	 * 
	 * @param attendance
	 *            实体对象
	 * @param pageNo
	 *            没有显示条数
	 * @param pageSize
	 *            总记录数
	 */
	public Object[] selectAllByAttendancePage(Attendance attendance,
			int pageNo, int pageSize, String date1, String date2, String tag);
	/**
	 * 导出考勤数据表
	 * @param attendance
	 * @param startDate
	 * @param endDate
	 */
	void exportDetailExcel(Attendance attendance, String startDate,
			String endDate);
	/**
	 * 根据ID查找出勤明细
	 * @param id
	 * @return
	 */
	Attendance getAttenceById(Integer id);
	/**
	 * 根据出勤明细查询所有加班申请
	 * @param attendance
	 * @return
	 */
	List<Overtime> getOverTimeByAtt(Attendance attendance);
	List<AskForLeave> getAskForLeaveByAtt(Attendance attendance);
	List<AttendanceTow> getAttendanceTowAtt(Attendance attendance);
	List<AccessRecords> getaccessEquipmentList(Attendance attendance);
	/**
	 * 更新个人当日考勤状态
	 * @param attendance
	 * @return
	 */
	String updateAttenceById(Attendance attendance);
	
	public Object[] selectAllByAttendancePage1(Attendance attendance,
			int parseInt, int pageSize, String startDate, String endDate);
	/**
	 * 将迟到，旷工记录发送到LED上
	 * @return
	 */
	public boolean sendKaoQin();
	/**
	 * 将迟到，旷工记录发送到LED上
	 * @return
	 */
	public void sendKaoQin_1();
	public void sendKaoQin_2();
	public void sendUserbrithday();
	public void houzongQueqin();
	
	/**
	 * 计算充值
	 * @param date 月份
	 * @param code 工号
	 * @param banci 班次ID
	 */
	public void jisianChongzhi(String date,String code, Integer banci);
	/**
	 * 显示充值
	 */
	public List showRechargeZhi(String startTime,String endTime,RechargeZhi rechargeZhi);
	public void daochuExc(String startTime,String endTime,RechargeZhi rechargeZhi);
	/**
	 * 导入考勤
	 * @param attendEx
	 */
	public String daoRukaoqin(File attendEx);

	void sendKaoQin_3();
	/**
	 * 将同一个人昨天的打卡时间 赋值给今天的打卡时间
	 */
	void linshi(String s,String s1);
	/**
	 * 
	 * 重置充值算法
	 */
	void chongshisuanfa();

	
	/**
	 * 接受考勤机上传的数据
	 * @param attenList 考勤集合
	 * @param equipmentId 
	 */
	public String addAttendances(List<Attendance> attenList, String equipmentId);

	void chongshisuanfa(String yue);
	
	/**
	 * 根据考勤汇总表查询附表信息
	 * @param attendance
	 * @return
	 */
	public List<AttendanceFu> getAttenceFu(Attendance attendance);

	/**
	 * 定时推送要签到的人信息
	 */
	public void randomPunch();

	/**
	 * 18-05-16
	 * 汇总夜班异常考勤
	 */
	void addHuizong_1130();
	/**
	 * 18-05-16
	 * 汇总白班异常考勤
	 */
	void addHuizong_2330();

	/**
	 * 申请补卡
	 * @param attendance
	 * @return
	 */
	public String updateshenqing(Attendance attendance);

	/**
	 * 班次信息放入show字段
	 * @param attendance
	 * @return
	 */
	public Attendance setAttenceshow(Attendance attendance);

	/**
	 * 重新当月出勤率汇总
	 * @param yue
	 */
	void huizong(String yue);

	/**
	 * 根据班次Id获得用户所在班次明细
	 * @param userId
	 * @return
	 */
	List<BanCiTime> getBanciTimeListByUsersId(Integer banciId);
	
}
