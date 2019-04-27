package com.task.ServerImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Alignment;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.springframework.util.FileCopyUtils;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.AttendanceServer;
import com.task.ServerImpl.banci.BanCiServerImpl;
import com.task.ServerImpl.menjin.DoorBangDingServerImpl;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.AskForLeave;
import com.task.entity.Attendance;
import com.task.entity.AttendanceCount;
import com.task.entity.AttendanceFu;
import com.task.entity.AttendancePersonInformation;
import com.task.entity.AttendanceTow;
import com.task.entity.AttendanceTowCollect;
import com.task.entity.BrushCard;
import com.task.entity.Department;
import com.task.entity.Goods;
import com.task.entity.GoodsStore;
import com.task.entity.KQDate;
import com.task.entity.Overtime;
import com.task.entity.Person;
import com.task.entity.Sell;
import com.task.entity.Users;
import com.task.entity.banci.BanCi;
import com.task.entity.banci.BanCiTime;
import com.task.entity.menjin.AccessEquipment;
import com.task.entity.menjin.AccessRecords;
import com.task.entity.menjin.RandomPunch;
import com.task.entity.menjin.RechargeAll;
import com.task.entity.menjin.RechargeZhi;
import com.task.entity.menjin.SpecialDate;
import com.task.entity.sop.Procard;
import com.task.entity.sop.ycl.YuanclAndWaigj;
import com.task.entity.system.CircuitRun;
import com.task.util.GongliZhuanNongliNice;
import com.task.util.LedSendUtil;
import com.task.util.RtxUtil;
import com.task.util.Util;

@SuppressWarnings({"unchecked","deprecation"})
public class AttendanceServerImpl implements AttendanceServer {
	private TotalDao totalDao;

	/*
	 * 
	 */
	public Object[] selectAllByAttendancePage1(Attendance attendance,
			int pageNo, int pageSize, String startDate, String endDate) {
		// *
		Users user = Util.getLoginUser();
		//
		// String hql ="from Attendance where deptName = "+user.getDept();
		String hql = "from Attendance where 1=1";
		// 条件查询
		if (null != attendance) {
			if ("cd".equals(attendance.getMorningStatus())) {
				attendance.setMorningStatus("迟到");
			}
			if ("zt".equals(attendance.getAfternoonStatus())) {
				attendance.setAfternoonStatus("早退");
			}
			if ("qj".equals(attendance.getAttendanceStatus())) {
				attendance.setAttendanceStatus("请假");
			}
			if ("wsk".equals(attendance.getAttendanceStatus())) {
				attendance.setAttendanceStatus("未刷卡");
			}
			if ("kg".equals(attendance.getAttendanceStatus())) {
				attendance.setAttendanceStatus("旷工");
			}
			if (null != attendance.getPersonName()
					&& !"".equals(attendance.getPersonName())) {
				hql += " and personName like '%" + attendance.getPersonName()
						+ "%'";
			}
			if (null != attendance.getCode()
					&& !"".equals(attendance.getCode())) {
				hql += " and code like '%" + attendance.getCode()
				+ "%'";
			}
			if (null != attendance.getDeptName()
					&& !"".equals(attendance.getDeptName())) {
				hql += " and deptName='" + attendance.getDeptName() + "'";
			}
			if (null != attendance.getAfternoonStatus()
					&& !"".equals(attendance.getAfternoonStatus())) {
				hql += " and afternoonStatus='"
						+ attendance.getAfternoonStatus() + "'";
			}
			if (null != attendance.getDateTime()
					&& !"".equals(attendance.getDateTime())) {
				hql += " and dateTime='" + attendance.getDateTime() + "'";
			}
			if (null != attendance.getMorningStatus()
					&& !"".equals(attendance.getMorningStatus())) {
				hql += " and morningStatus='" + attendance.getMorningStatus()
						+ "'";
			}
			if (null != attendance.getAttendanceStatus()
					&& !"".equals(attendance.getAttendanceStatus())) {
				hql += " and (afternoonStatus like'%"
						+ attendance.getAttendanceStatus()
						+ "%' or morningStatus like'%"
						+ attendance.getAttendanceStatus() + "%')";
			}
		}
		if (null != startDate && !"".equals(startDate) && null != endDate
				&& !"".equals(endDate)) {
			hql += " and dateTime between '" + startDate + "' and '" + endDate
					+ "'";
		}
		hql += "and deptName='" + user.getDept()
				+ "' order by dateTime desc,deptName desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	// 保存考勤汇总信息
	/**
	 * tag(chuqinlv,huizong)
	 */
	@Override
	public boolean saveAttendance(String tag) {
		boolean bool = false;
		// 处理有刷卡记录（刷卡表与汇总表对比）
		/**
		 * 获取为同步到汇总表的日期 遍历刷卡表 刷卡数据为同步到汇总表总的记录 存储过程添加排班时间（） 没有同步到，按日期同步
		 * 同步过的按日期差异化补充
		 * 
		 */
		// 没有同步过的刷卡日期
		String hqlSro = "from KQDate where synchroTag=" + 0;
		List listSyo = totalDao.query(hqlSro);
		String curTime = Util.getDateTime();// 当前时间
		if (null != listSyo && listSyo.size() > 0) {// 有需要同步遍历的日期
			// 遍历考勤日期信息表
			for (int i = 0; i < listSyo.size(); i++) {
				int countRen = 0;// 当日总人数
				try {
					KQDate kqd = (KQDate) listSyo.get(i);// 考勤日期对象
					int zhixingCS = kqd.getZhixingCS();// 执行次数
					String syoDate = kqd.getKqDate();// 获得需遍历的日期
					String curDate = Util.getDateTime("yyyy-MM-dd");// 当天日期
					// String downLoadTime = curDate + " 08:10:00";// 下载考勤数据时间
					String amTime = curDate + " " + Util.wxStartTime() + ":00";// 上午节点时间
					String pmTime = curDate + " " + Util.xiabanTime() + ":00";// 下午节点时间
					if (zhixingCS > 0 && syoDate.equals(curDate)) {
						bool = true;
					} else {
						// 考勤人员信息表
						String hqlKQ_person = "from AttendancePersonInformation where atPerInforId not in(select id from Person where deptId >20)";
						List list_kqp = totalDao.query(hqlKQ_person);
						// 遍历考勤人员
						for (int j = 0; j < list_kqp.size(); j++) {
							AttendancePersonInformation kq_person = (AttendancePersonInformation) list_kqp
									.get(j);
							Person st_person = (Person) totalDao.getObjectById(
									Person.class, kq_person.getAtPerInforId());// ST_person对象
							if (st_person == null)// 不存在不计算
								continue;
							Department department = null;
							try {
								department = st_person.getDepartment();// 此处会出现空指针
							} catch (Exception e) {
								e.printStackTrace();
							}
							String hqlBrush = " from BrushCard where brushDate=? and personId=?";
							String hqlAttence = " from Attendance where dateTime=? and atPersonId=? ";
							List list_hqlBrush = totalDao.query(hqlBrush,
									syoDate, kq_person.getAtPerInforId());
							List list_hqlAttence = totalDao.query(hqlAttence,
									syoDate, kq_person.getAtPerInforId());
							int brushCount = 0;// 刷卡记录标识
							int attenceCount = 0;// 汇总激励标识
							if (null != list_hqlBrush
									&& list_hqlBrush.size() > 0) {
								brushCount = list_hqlBrush.size();
							}
							if (list_hqlAttence.size() > 0
									&& null != list_hqlAttence) {
								attenceCount = list_hqlAttence.size();
							}
							String hqlAskLeave = " from AskForLeave where leavePerson=? and  leaveStartDate>=? and leaveEndDate<=? and approvalStatus='同意'";
							// 上午
							List listAskLeavem = totalDao.query(hqlAskLeave,
									st_person.getName(), syoDate + " "
											+ Util.shangbanTime(), syoDate
											+ " " + Util.wxStartTime());
							// 下午
							List listAskLeavea = totalDao.query(hqlAskLeave,
									st_person.getName(), syoDate + " "
											+ Util.wxEndTime(), syoDate
											+ " 23:59:00");
//							if ("唐忠郑".equals(st_person.getName())) {
//								System.out.println(123);
//							}
							if (brushCount == 0 && attenceCount == 0) {// 既无刷卡记录，也无汇总记录（没有汇总过，也没刷卡，请假，旷工，未刷卡）
								Attendance attence = new Attendance();
//								attence.setAtPersonId(st_person.getId());// 人员ID
								attence.setPersonName(st_person.getName());// 人员姓名
								attence.setDeptName(department.getName());// 部门
								attence.setCardNo(st_person.getCardNo());// 人员卡号
								attence.setDateTime(syoDate);// 打卡日期
								attence.setOperationDate(curTime);// 当前操作时间
								attence.setWorkDateTime(null);
								attence.setClosingDateTime(null);

								/*
								 * //添加汇总记录 String hqlAskLeave =
								 * " from AskForLeave where leavePerson=? and  leaveStartDate<=? and leaveEndDate>=? and approvalResult=?"
								 * ; //上午 List
								 * listAskLeavem=totalDao.query(hqlAskLeave,
								 * st_person
								 * .getName(),syoDate+" 07:30",syoDate+" 11:30"
								 * ,"同意"); //下午 List
								 * listAskLeavea=totalDao.query(hqlAskLeave,
								 * st_person
								 * .getName(),syoDate+" 12:15",syoDate+" 16:15"
								 * ,"同意");
								 */
								if (curDate.equals(syoDate)) {// 当天的记录
									// int result =
									// curTime.compareTo(downLoadTime);//
									// >0
									int result = 1;// >0
									// 则curTime>downLoadTime
									if (result > 0) {// 数据已经下载
										int amcom = amTime.compareTo(curTime);// 上午同步
										int pmcom = curTime.compareTo(pmTime);// 下午同步
										// 只处理上午的状态，下午的状态下次处理
										if (listAskLeavem.size() > 0
												&& null != listAskLeavem) {// 上午状态
											attence.setMorningStatus("请假");
										} else {
											attence.setMorningStatus("未刷卡");
										}
									} else {// 考勤数据未下载
										attence.setMorningStatus("数据未下载");
										attence.setMorningStatus("数据未下载");
									}
								} else {// 不是当天的记录,封闭考勤日期状态 KQDate
									// 请假记录
									if (listAskLeavem.size() > 0
											&& null != listAskLeavem) {// 上午状态
										attence.setMorningStatus("请假");
									} else {
										attence.setMorningStatus("旷工");
									}
									if (listAskLeavea.size() > 0
											&& null != listAskLeavea) {// 下午状态
										attence.setAfternoonStatus("请假");
									} else {
										attence.setAfternoonStatus("旷工");
									}
									attence.setAttendanceStatus(attence
											.getMorningStatus()
											+ ";"
											+ attence.getAfternoonStatus());

								}
								bool = totalDao.save(attence);// 保存汇总信息
							} else if (brushCount > 0 && attenceCount == 0) {// 有刷卡记录，无汇总记录
								// 获取刷卡记录
								BrushCard brushCard = (BrushCard) list_hqlBrush
										.get(0);
								// 刷卡时间
								String brushTime = brushCard.getClockTime();
								String[] brushArr = brushTime.split(" ");
								Attendance attence = new Attendance();
								//attence.setAtPersonId(st_person.getId());// 人员ID
								attence.setPersonName(st_person.getName());// 人员姓名
								attence.setDeptName(department.getName());// 部门
								attence.setCardNo(st_person.getCardNo());// 人员卡号
								attence.setDateTime(syoDate);// 打卡日期
								attence.setOperationDate(curTime);// 当前操作时间
								if (curDate.equals(syoDate)) {// 当天的记录
									if (brushArr.length > 1) {// 刷多次卡
										String morningTime = brushArr[0];// 上班打卡时间
										String eveningTime = brushArr[brushArr.length - 1];// 下班打卡时间
										attence.setWorkDateTime(morningTime);
										attence.setClosingDateTime(eveningTime);
										int morningStatus = Util.shangbanTime()
												.compareTo(morningTime);// 正常上班
										if (morningStatus > 0) {
											attence.setMorningStatus("正常");
										} else {
											attence.setMorningStatus("迟到");
										}
										int eneningStatus = eveningTime
												.compareTo(Util.xiabanTime());
										if (eneningStatus >= 0) {
											attence.setAfternoonStatus("正常");
										} else {
											attence.setAfternoonStatus("早退");
										}
										attence.setAttendanceStatus(attence
												.getMorningStatus()
												+ ";"
												+ attence.getAfternoonStatus());
									} else {// 只刷一次
										String oneTime = brushArr[0];// 上班打卡时间
										// 与上班时间对比
										int workStatus = Util.shangbanTime()
												.compareTo(oneTime);
										if (workStatus > 0) {
											attence.setWorkDateTime(oneTime);
											attence.setMorningStatus("正常");
										} else {
											int lateStatus = Util.wxStartTime()
													.compareTo(oneTime);// 7:30<oneTime<11:30
											if (lateStatus > 0) {
												attence
														.setWorkDateTime(oneTime);
												attence.setMorningStatus("迟到");
											} else {
												// 判断请假
												int zaotuiStatus = Util
														.xiabanTime()
														.compareTo(oneTime);// 下班前打卡
												if (zaotuiStatus > 0) {
													if (listAskLeavem.size() > 0
															&& null != listAskLeavem) {// 上午状态
														attence
																.setMorningStatus("请假");
													} else {
														attence
																.setMorningStatus("未刷卡");
													}
													attence
															.setClosingDateTime(oneTime);
												} else {
													if (listAskLeavem.size() > 0
															&& null != listAskLeavem) {// 上午状态
														attence
																.setMorningStatus("请假");
													} else {
														attence
																.setMorningStatus("未刷卡");
													}
													attence
															.setAfternoonStatus("正常");
													attence
															.setClosingDateTime(oneTime);
												}
											}
										}
									}
								} else {// 不是当天的记录
									if (brushArr.length > 1) {// 刷多次卡
										String morningTime = brushArr[0];// 上班打卡时间
										String eveningTime = brushArr[brushArr.length - 1];// 下班打卡时间
										attence.setWorkDateTime(morningTime);
										attence.setClosingDateTime(eveningTime);
										int morningStatus = Util.shangbanTime()
												.compareTo(morningTime);// 正常上班
										if (morningStatus > 0) {
											attence.setMorningStatus("正常");
										} else {
											if (listAskLeavem.size() > 0
													&& null != listAskLeavem) {// 上午状态
												attence.setMorningStatus("请假");
											} else {
												attence.setMorningStatus("迟到");
											}
										}

										int eneningStatus = eveningTime
												.compareTo(Util.xiabanTime());
										if (eneningStatus >= 0) {
											attence.setAfternoonStatus("正常");
										} else {
											if (listAskLeavea.size() > 0
													&& null != listAskLeavea) {// 下午状态
												attence
														.setAfternoonStatus("请假");
											} else {
												attence
														.setAfternoonStatus("早退");
											}
										}
										attence.setAttendanceStatus(attence
												.getMorningStatus()
												+ ";"
												+ attence.getAfternoonStatus());
									} else {// 只刷一次
										String oneTime = brushArr[0];// 上班打卡时间
										// 与上班时间对比
										int workStatus = Util.shangbanTime()
												.compareTo(oneTime);
										if (workStatus > 0) {
											attence.setWorkDateTime(oneTime);
											attence.setMorningStatus("正常");
											if (listAskLeavea.size() > 0
													&& null != listAskLeavea) {// 下午状态
												attence
														.setAfternoonStatus("请假");
											} else {
												attence
														.setAfternoonStatus("未刷卡");
											}
										} else {
											int lateStatus = Util.wxStartTime()
													.compareTo(oneTime);// 7:30<oneTime<11:30
											if (lateStatus > 0) {
												attence
														.setWorkDateTime(oneTime);
												attence.setMorningStatus("迟到");
												if (listAskLeavea.size() > 0
														&& null != listAskLeavea) {// 下午状态
													attence
															.setAfternoonStatus("请假");
												} else {
													attence
															.setAfternoonStatus("未刷卡");
												}
											} else {
												// 判断请假

												int zaotuiStatus = Util
														.xiabanTime()
														.compareTo(oneTime);// 下班前打卡
												if (zaotuiStatus > 0) {
													if (listAskLeavem.size() > 0
															&& null != listAskLeavem) {// 上午状态
														attence
																.setMorningStatus("请假");
													} else {
														attence
																.setMorningStatus("未刷卡");
													}
													attence
															.setClosingDateTime(oneTime);
												} else {
													if (listAskLeavem.size() > 0
															&& null != listAskLeavem) {// 上午状态
														attence
																.setMorningStatus("请假");
													} else {
														attence
																.setMorningStatus("未刷卡");
													}
													attence
															.setAfternoonStatus("正常");
													attence
															.setClosingDateTime(oneTime);
												}
											}
										}
										attence.setAttendanceStatus(attence
												.getMorningStatus()
												+ ";"
												+ attence.getAfternoonStatus());
									}
								}
								bool = totalDao.save(attence);
							} else if (brushCount > 0 && attenceCount > 0) {// 有刷卡记录，有汇总记录
								// 获取刷卡记录
								BrushCard brushCard = (BrushCard) list_hqlBrush
										.get(0);
								Attendance attence = (Attendance) list_hqlAttence
										.get(0);// 汇总对象
								// 刷卡时间
								String brushTime = brushCard.getClockTime();
								String[] brushArr = brushTime.split(" ");

								if (curDate.equals(syoDate)) {
									// 当天的记录
									if (brushArr.length > 1) {// 刷多次卡
										String morningTime = brushArr[0];// 上班打卡时间
										String eveningTime = brushArr[brushArr.length - 1];// 下班打卡时间
										attence.setWorkDateTime(morningTime);
										attence.setClosingDateTime(eveningTime);
										int morningStatus = Util.shangbanTime()
												.compareTo(morningTime);// 正常上班
										if (morningStatus > 0) {
											attence.setMorningStatus("正常");
										} else {
											attence.setMorningStatus("迟到");
										}
										int eneningStatus = eveningTime
												.compareTo(Util.xiabanTime());
										if (eneningStatus >= 0) {
											attence.setAfternoonStatus("正常");
										} else {
											attence.setAfternoonStatus("早退");
										}
										attence.setAttendanceStatus(attence
												.getMorningStatus()
												+ ";"
												+ attence.getAfternoonStatus());
									} else {// 只刷一次
										String oneTime = brushArr[0];// 上班打卡时间
										// 与上班时间对比
										int workStatus = Util.shangbanTime()
												.compareTo(oneTime);
										if (workStatus > 0) {
											attence.setWorkDateTime(oneTime);
											attence.setMorningStatus("正常");
										} else {
											int lateStatus = Util.wxStartTime()
													.compareTo(oneTime);// 7:30<oneTime<11:30
											if (lateStatus > 0) {
												attence
														.setWorkDateTime(oneTime);
												attence.setMorningStatus("迟到");
											} else {
												// 判断请假
												int zaotuiStatus = Util
														.shangbanTime()
														.compareTo(oneTime);// 下班前打卡
												if (zaotuiStatus > 0) {
													if (listAskLeavem.size() > 0
															&& null != listAskLeavem) {// 上午状态
														attence
																.setMorningStatus("请假");
													} else {
														attence
																.setMorningStatus("未刷卡");
													}
													attence
															.setClosingDateTime(oneTime);
												} else {
													if (listAskLeavem.size() > 0
															&& null != listAskLeavem) {// 上午状态
														attence
																.setMorningStatus("请假");
													} else {
														attence
																.setMorningStatus("未刷卡");
													}
													attence
															.setAfternoonStatus("正常");
													attence
															.setClosingDateTime(oneTime);
												}
											}
										}
									}
								} else {// 不是当天的记录
									if (brushArr.length > 1) {// 刷多次卡
										String morningTime = brushArr[0];// 上班打卡时间
										String eveningTime = brushArr[brushArr.length - 1];// 下班打卡时间
										attence.setWorkDateTime(morningTime);
										attence.setClosingDateTime(eveningTime);
										int morningStatus = Util.shangbanTime()
												.compareTo(morningTime);// 正常上班
										if (morningStatus > 0) {
											attence.setMorningStatus("正常");
										} else {
											if (listAskLeavem.size() > 0
													&& null != listAskLeavem) {// 上午状态
												attence.setMorningStatus("请假");
											} else {
												attence.setMorningStatus("迟到");
											}
										}
										int eneningStatus = eveningTime
												.compareTo(Util.xiabanTime());
										if (eneningStatus >= 0) {
											attence.setAfternoonStatus("正常");
										} else {
											if (listAskLeavea.size() > 0
													&& null != listAskLeavea) {// 下午状态
												attence
														.setAfternoonStatus("请假");
											} else {
												attence
														.setAfternoonStatus("早退");
											}
										}
										attence.setAttendanceStatus(attence
												.getMorningStatus()
												+ ";"
												+ attence.getAfternoonStatus());
									} else {// 只刷一次
										String oneTime = brushArr[0];// 上班打卡时间
										// 与上班时间对比
										int workStatus = Util.shangbanTime()
												.compareTo(oneTime);
										if (workStatus > 0) {
											attence.setWorkDateTime(oneTime);
											attence.setMorningStatus("正常");
											if (listAskLeavea.size() > 0
													&& null != listAskLeavea) {// 下午状态
												attence
														.setAfternoonStatus("请假");
											} else {
												// 判断是否存在状态
												if (null != attence
														.getAfternoonStatus()) {
												} else {
													attence
															.setAfternoonStatus("未刷卡");
												}

											}
										} else {
											int lateStatus = Util.wxStartTime()
													.compareTo(oneTime);// 7:30<oneTime<11:30
											if (lateStatus > 0) {
												attence
														.setWorkDateTime(oneTime);
												attence.setMorningStatus("迟到");
												if (listAskLeavea.size() > 0
														&& null != listAskLeavea) {// 下午状态
													attence
															.setAfternoonStatus("请假");
												} else {
													// 判断是否存在状态
													if (null != attence
															.getAfternoonStatus()) {
													} else {
														attence
																.setAfternoonStatus("未刷卡");
													}
												}
											} else {
												// 判断请假
												int zaotuiStatus = Util
														.xiabanTime()
														.compareTo(oneTime);// 下班前打卡
												if (zaotuiStatus > 0) {
													if (listAskLeavem.size() > 0
															&& null != listAskLeavem) {// 上午状态
														attence
																.setMorningStatus("请假");
													} else {
														// 判断是否存在状态
														if (null != attence
																.getMorningStatus()) {
														} else {
															attence
																	.setMorningStatus("未刷卡");
														}
													}
													attence
															.setClosingDateTime(oneTime);
												} else {
													if (listAskLeavem.size() > 0
															&& null != listAskLeavem) {// 上午状态
														attence
																.setMorningStatus("请假");
													} else {
														// 判断是否存在状态
														if (null != attence
																.getMorningStatus()) {
														} else {
															attence
																	.setMorningStatus("未刷卡");
														}
													}
													attence
															.setAfternoonStatus("正常");
													attence
															.setClosingDateTime(oneTime);
												}
											}
										}
										attence.setAttendanceStatus(attence
												.getMorningStatus()
												+ ";"
												+ attence.getAfternoonStatus());
									}
								}
								bool = totalDao.update(attence);
							} else if (brushCount == 0 && attenceCount > 0) {// 无刷卡记录，有汇总记录
								Attendance attence = (Attendance) list_hqlAttence
										.get(0);
								// 请假或旷工
								if (curDate.equals(syoDate)) {// 当天的记录
									// int result =
									// curTime.compareTo(downLoadTime);//
									// >0
									int result = 1;// >0
									// 则curTime>downLoadTime
									if (result > 0) {// 数据已经下载
										int amcom = amTime.compareTo(curTime);// 上午同步
										int pmcom = curTime.compareTo(pmTime);// 下午同步
										// 只处理上午的状态，下午的状态下次处理
										if (listAskLeavem.size() > 0
												&& null != listAskLeavem) {// 上午状态
											attence.setMorningStatus("请假");
										} else {
											attence.setMorningStatus("未刷卡");
										}
									} else {// 考勤数据未下载
										attence.setMorningStatus("数据未下载");
										attence.setMorningStatus("数据未下载");
									}
								} else {// 不是当天的记录
									// 请假记录
									if (listAskLeavem.size() > 0
											&& null != listAskLeavem) {// 上午状态
										attence.setMorningStatus("请假");
									} else {
										// 判断是否存在状态
										if ("请假".equals(attence
												.getMorningStatus())) {
										} else {
											attence.setMorningStatus("旷工");
										}

									}
									if (listAskLeavea.size() > 0
											&& null != listAskLeavea) {// 下午状态
										attence.setAfternoonStatus("请假");
									} else {
										// 判断是否存在状态
										if ("请假".equals(attence
												.getAfternoonStatus())) {
										} else {
											attence.setAfternoonStatus("旷工");
										}

									}
									attence.setAttendanceStatus(attence
											.getMorningStatus()
											+ ";"
											+ attence.getAfternoonStatus());
								}
								bool = totalDao.update(attence);
							}
							countRen++;
						}
					}
					if (zhixingCS > 0 && syoDate.equals(curDate)) {

					} else {

						// 处理出勤率
						String hql = "from AttendanceCount where cardDateTime=?";
						List listAttenCount = totalDao.query(hql, syoDate);
						int nomAM = 0;// 上午正常人数
						int nomPM = 0;// 下午正常人数
						int lastCount = 0;// 迟到人数
						int leaveEar = 0;// 早退人数
						int askCount = 0;// 请假人数
						int noCardCount = 0;// 未刷卡人数
						int kuanggong = 0;// 旷工人数
						// 统计上午正常打卡人数
						String hql_nomAM = " from Attendance where morningStatus=? and dateTime=?";
						nomAM = totalDao.getCount(hql_nomAM, "正常", syoDate);
						// 统计下午正常打卡人数
						String hql_nomPM = " from Attendance where afternoonStatus=? and dateTime=?";
						nomPM = totalDao.getCount(hql_nomPM, "正常", syoDate);
						// 统计迟到打卡人数
						String hql_lastCount = " from Attendance where morningStatus=? and dateTime=?";
						lastCount = totalDao.getCount(hql_lastCount, "迟到",
								syoDate);
						// 统计早退打卡人数
						String hql_leaveEar = " from Attendance where afternoonStatus=? and dateTime=?";
						leaveEar = totalDao.getCount(hql_leaveEar, "早退",
								syoDate);
						// 统计旷工人数
						String hql_kuanggongA = " from Attendance where morningStatus=? and dateTime=?";
						String hql_kuanggongP = " from Attendance where afternoonStatus=? and dateTime=?";
						kuanggong = totalDao.getCount(hql_kuanggongA, "旷工",
								syoDate)
								+ totalDao.getCount(hql_kuanggongP, "旷工",
										syoDate);
						// 统计请假人数
						String hql_askCountA = " from Attendance where morningStatus=? and dateTime=?";
						String hql_askCountP = " from Attendance where afternoonStatus=? and dateTime=?";
						askCount = totalDao.getCount(hql_askCountA, "请假",
								syoDate)
								+ totalDao.getCount(hql_askCountP, "请假",
										syoDate);
						// 统计未刷卡人数
						String hql_noCardCountAM = " from Attendance where morningStatus=? and dateTime=?";
						String hql_noCardCountPM = " from Attendance where afternoonStatus=? and dateTime=?";
						noCardCount = totalDao.getCount(hql_noCardCountAM,
								"未刷卡", syoDate)
								+ totalDao.getCount(hql_noCardCountPM, "未刷卡",
										syoDate);
						if (listAttenCount.size() > 0 && null != listAttenCount) {
							// 存在出勤率记录
							AttendanceCount attcou = (AttendanceCount) listAttenCount
									.get(0);
							attcou.setNormalAM(nomAM);
							attcou.setNormalPM(nomPM);
							attcou.setLateCount(lastCount);
							attcou.setLeaveEarlyCount(leaveEar);
							attcou.setAskForLeaveCount(askCount);
							attcou.setNotCardCount(noCardCount);
							attcou.setKuangGongCount(kuanggong);

							float chuqin = 100 * (float) nomAM / countRen;
							if (null != attcou.getTotalAttendance()
									&& attcou.getTotalAttendance() > 0) {
								chuqin = 100 * (float) nomAM
										/ attcou.getTotalAttendance();
							}
							attcou.setAttendance(chuqin);
							bool = totalDao.update(attcou);
						} else {// 没有出勤率记录
							AttendanceCount attcou = new AttendanceCount();
							attcou.setNormalAM(nomAM);
							attcou.setNormalPM(nomPM);
							attcou.setLateCount(lastCount);
							attcou.setLeaveEarlyCount(leaveEar);
							attcou.setAskForLeaveCount(askCount);
							attcou.setNotCardCount(noCardCount);
							attcou.setKuangGongCount(kuanggong);
							float chuqin = 100 * (float) nomAM / countRen;
							attcou.setAttendance(chuqin);
							attcou.setTotalAttendance(countRen);
							attcou.setCardDateTime(syoDate);
							attcou.setOperationDate(Util.getDateTime());
							bool = totalDao.save(attcou);
						}
						/**
						 * 处理考勤日程表 考勤数据全部同步完成，且状态更改
						 */
						String hqlOverAtt = "from Attendance where dateTime=? and attendanceStatus!=null";
						int countOver = totalDao.getCount(hqlOverAtt, syoDate);
						// 同步完成人数等于当日考勤人总数
						if (countOver >= countRen) {
							kqd.setSynchroTag(1);
							kqd.setSynchroTime(Util.getDateTime());
							kqd.setZhixingCS(kqd.getZhixingCS() + 1);
						} else {
							kqd.setZhixingCS(kqd.getZhixingCS() + 1);
						}

						bool = totalDao.update(kqd);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {// 无需要同步到日期，直接返回查案记录
			bool = true;
		}
		return bool;
	}

	private void noandyes(Attendance attence, String kqDate, String nowDate,
			String nowTime, BanCi banci, AskForLeave askLeaveUp,
			AskForLeave askLeaveDown, boolean b) {
		// 请假或旷工
		if (nowDate.equals(kqDate)) {// 当天的记录
			if (askLeaveUp != null) {// 上午状态
				attence.setMorningStatus("请假");
			} else {
				attence.setMorningStatus("未刷卡");
			}
		} else {// 不是当天的记录
			// 请假记录
			if (askLeaveUp != null) {// 上午状态
				attence.setMorningStatus("请假");
			} else {
				// 判断是否存在状态
				if ("请假".equals(attence.getMorningStatus())) {
				} else {
					attence.setMorningStatus("旷工");
				}
			}
			if (askLeaveDown != null) {// 下午状态
				attence.setAfternoonStatus("请假");
			} else {
				// 判断是否存在状态
				if ("请假".equals(attence.getAfternoonStatus())) {
				} else {
					attence.setAfternoonStatus("旷工");
				}
			}
			attence.setAttendanceStatus(attence.getMorningStatus() + ";"
					+ attence.getAfternoonStatus());
		}
		b = totalDao.update(attence);
	}

	private void yesandyes(Users users,
			AttendanceTowCollect attendanceTowCollect, Attendance attence,
			String kqDate, String nowDate, String nowTime, BanCi banci,
			AskForLeave askLeaveUp, AskForLeave askLeaveDown, boolean b) {
		String sbTime = banci.getFirsttime();// 上班时间
		String xbTime = banci.getFinishtime();// 下班时间
		// 刷卡时间
		String brushTime = attendanceTowCollect.getTime();
		String[] brushArr = brushTime.split(" ");
		if (users.getName().equals("唐晓斌")) {
			System.out.println("*************************");
		}
		if (nowDate.equals(kqDate)) {
			// 当天的记录
			// if (brushArr.length > 1) {// 刷多次卡
			// String morningTime = brushArr[0];// 上班打卡时间
			// String eveningTime = brushArr[brushArr.length - 1];// 下班打卡时间
			// attence.setWorkDateTime(morningTime);
			// attence.setClosingDateTime(eveningTime);
			// int morningStatus = sbTime.compareTo(morningTime);// 正常上班
			// if (morningStatus > 0) {
			// attence.setMorningStatus("正常");
			// } else {
			// attence.setMorningStatus("迟到");
			// }
			// int eneningStatus = eveningTime.compareTo(xbTime);
			// if (eneningStatus >= 0) {
			// attence.setAfternoonStatus("正常");
			// } else {
			// attence.setAfternoonStatus("早退");
			// }
			// attence.setAttendanceStatus(attence.getMorningStatus() + ";"
			// + attence.getAfternoonStatus());
			// } else {// 只刷一次
			String oneTime = brushArr[0];// 上班打卡时间
			// 与上班时间对比
			int workStatus = sbTime.compareTo(oneTime);
			if (workStatus >= 0) {
				attence.setWorkDateTime(oneTime);
				attence.setMorningStatus("正常");
			} else {
				int lateStatus = banci.getWxstarttime().compareTo(oneTime);// 7:30<oneTime<11:30
				if (lateStatus > 0) {
					attence.setWorkDateTime(oneTime);
					attence.setMorningStatus("迟到");
				} else {
					// 判断请假
					int zaotuiStatus = sbTime.compareTo(oneTime);// 下班前打卡
					if (zaotuiStatus > 0) {
						if (askLeaveUp != null) {// 上午状态
							attence.setMorningStatus("请假");
						} else {
							attence.setMorningStatus("未刷卡");
						}
						attence.setClosingDateTime(oneTime);
					} else {
						if (askLeaveUp != null) {// 上午状态
							attence.setMorningStatus("请假");
						} else {
							attence.setMorningStatus("未刷卡");
						}
						attence.setAfternoonStatus("正常");
						attence.setClosingDateTime(oneTime);
					}
				}
			}
			// }
		} else {// 不是当天的记录
			if (brushArr.length > 1) {// 刷多次卡
				String morningTime = brushArr[0];// 上班打卡时间
				String eveningTime = brushArr[brushArr.length - 1];// 下班打卡时间
				attence.setWorkDateTime(morningTime);
				attence.setClosingDateTime(eveningTime);
				int morningStatus = sbTime.compareTo(morningTime);// 正常上班
				if (morningStatus >= 0) {
					attence.setMorningStatus("正常");
				} else {
					if (askLeaveUp != null) {// 上午状态
						attence.setMorningStatus("请假");
					} else {
						attence.setMorningStatus("迟到");
					}
				}
				int eneningStatus = eveningTime.compareTo(xbTime);
				if (eneningStatus >= 0) {
					attence.setAfternoonStatus("正常");
				} else {
					if (askLeaveDown != null) {// 下午状态
						attence.setMorningStatus("请假");
					} else {
						attence.setMorningStatus("早退");
					}
				}
				attence.setAttendanceStatus(attence.getMorningStatus() + ";"
						+ attence.getAfternoonStatus());
			} else {// 只刷一次
				String oneTime = brushArr[0];// 上班打卡时间
				// 与上班时间对比
				int workStatus = sbTime.compareTo(oneTime);
				if (workStatus >= 0) {
					attence.setWorkDateTime(oneTime);
					attence.setMorningStatus("正常");
					if (askLeaveDown != null) {// 下午状态
						attence.setAfternoonStatus("请假");
					} else {
						// 判断是否存在状态
						if (null != attence.getAfternoonStatus()) {
						} else {
							attence.setAfternoonStatus("未刷卡");
						}

					}
				} else {
					int lateStatus = banci.getWxstarttime().compareTo(oneTime);// 7:30<oneTime<11:30
					if (lateStatus > 0) {
						attence.setWorkDateTime(oneTime);
						attence.setMorningStatus("迟到");
						if (askLeaveDown != null) {// 下午状态
							attence.setAfternoonStatus("请假");
						} else {
							// 判断是否存在状态
							if (null != attence.getAfternoonStatus()) {
							} else {
								attence.setAfternoonStatus("未刷卡");
							}
						}
					} else {
						// 判断请假
						int zaotuiStatus = xbTime.compareTo(oneTime);// 下班前打卡
						if (zaotuiStatus > 0) {
							if (askLeaveUp != null) {// 上午状态
								attence.setMorningStatus("请假");
							} else {
								// 判断是否存在状态
								if (null != attence.getMorningStatus()) {
								} else {
									attence.setMorningStatus("未刷卡");
								}
							}
							attence.setClosingDateTime(oneTime);
						} else {
							if (askLeaveUp != null) {// 上午状态
								attence.setMorningStatus("请假");
							} else {
								// 判断是否存在状态
								if (null != attence.getMorningStatus()) {
								} else {
									attence.setMorningStatus("未刷卡");
								}
							}
							attence.setAfternoonStatus("正常");
							attence.setClosingDateTime(oneTime);
						}
					}
				}
				attence.setAttendanceStatus(attence.getMorningStatus() + ";"
						+ attence.getAfternoonStatus());
			}
		}
		b = totalDao.update(attence);
	}

	private void yesandno(Users users,
			AttendanceTowCollect attendanceTowCollect, String kqDate,
			String nowDate, String nowTime, BanCi banci,
			AskForLeave askLeaveUp, AskForLeave askLeaveDown, boolean b) {
		String sbTime = banci.getFirsttime();// 上班时间
		String xbTime = banci.getFinishtime();// 下班时间
		// 刷卡时间
		String brushTime = attendanceTowCollect.getTime();
		String[] brushArr = brushTime.split(" ");
		Attendance attence = new Attendance();
		attence.setUserId(users.getId());// userID
		attence.setPersonName(users.getName());// 人员姓名
		attence.setDeptName(users.getDept());// 部门
		attence.setCardNo(users.getCardId());// 人员卡号
		attence.setDateTime(kqDate);// 打卡日期
		attence.setOperationDate(nowTime);// 当前操作时间
		if (nowDate.equals(kqDate)) {// 当天的记录
			// if (brushArr.length > 1) {// 刷多次卡
			// String morningTime = brushArr[0];// 上班打卡时间
			// String eveningTime = brushArr[brushArr.length - 1];// 下班打卡时间
			// attence.setWorkDateTime(morningTime);
			// attence.setClosingDateTime(eveningTime);
			// int morningStatus = sbTime.compareTo(morningTime);// 正常上班
			// if (morningStatus >= 0) {
			// attence.setMorningStatus("正常");
			// } else {
			// if (askLeaveUp != null) {// 上午状态
			// attence.setMorningStatus("请假");
			// } else {
			// attence.setMorningStatus("迟到");
			// }
			// }
			// int eneningStatus = eveningTime.compareTo(xbTime);
			// if (eneningStatus >= 0) {
			// attence.setAfternoonStatus("正常");
			// } else {
			// if (askLeaveDown != null) {// 下午状态
			// attence.setAfternoonStatus("请假");
			// } else {
			// attence.setAfternoonStatus("早退");
			// }
			// }
			// attence.setAttendanceStatus(attence.getMorningStatus() + ";"
			// + attence.getAfternoonStatus());
			// } else { // 只刷一次
			String oneTime = brushArr[0];// 上班打卡时间
			// 与上班时间对比
			int workStatus = sbTime.compareTo(oneTime);
			if (workStatus >= 0) {
				attence.setWorkDateTime(oneTime);
				attence.setMorningStatus("正常");
			} else {
				int lateStatus = banci.getWxstarttime().compareTo(oneTime);// 7:30<oneTime<11:30
				if (lateStatus > 0) {
					attence.setWorkDateTime(oneTime);
					attence.setMorningStatus("迟到");
				} else {
					// 判断请假
					int zaotuiStatus = xbTime.compareTo(oneTime);// 下班前打卡
					if (zaotuiStatus > 0) {
						if (askLeaveUp != null) {// 上午状态
							attence.setMorningStatus("请假");
						} else {
							attence.setMorningStatus("未刷卡");
						}
						attence.setClosingDateTime(oneTime);
					} else {
						if (askLeaveUp != null) {// 上午状态
							attence.setMorningStatus("请假");
						} else {
							attence.setMorningStatus("未刷卡");
						}
						attence.setAfternoonStatus("正常");
						attence.setClosingDateTime(oneTime);
					}
				}
			}
			// }
		} else {// 不是当天的记录
			if (brushArr.length > 1) {// 刷多次卡
				String morningTime = brushArr[0];// 上班打卡时间
				String eveningTime = brushArr[brushArr.length - 1];// 下班打卡时间
				attence.setWorkDateTime(morningTime);
				attence.setClosingDateTime(eveningTime);
				int morningStatus = sbTime.compareTo(morningTime);// 正常上班
				if (morningStatus >= 0) {
					attence.setMorningStatus("正常");
				} else {
					attence.setMorningStatus("迟到");
				}
				int eneningStatus = eveningTime.compareTo(xbTime);
				if (eneningStatus >= 0) {
					attence.setAfternoonStatus("正常");
				} else {
					attence.setAfternoonStatus("早退");
				}
				attence.setAttendanceStatus(attence.getMorningStatus() + ";"
						+ attence.getAfternoonStatus());
			} else {// 只刷一次
				String oneTime = brushArr[0];// 上班打卡时间
				// 与上班时间对比
				int workStatus = sbTime.compareTo(oneTime);
				if (workStatus >= 0) {
					attence.setWorkDateTime(oneTime);
					attence.setMorningStatus("正常");
					if (askLeaveDown != null) {// 下午状态
						attence.setAfternoonStatus("请假");
					} else {
						attence.setAfternoonStatus("未刷卡");
					}
				} else {
					int lateStatus = banci.getWxstarttime().compareTo(oneTime);// 7:30<oneTime<11:30
					if (lateStatus > 0) {
						attence.setWorkDateTime(oneTime);
						attence.setMorningStatus("迟到");
						if (askLeaveDown != null) {// 下午状态
							attence.setAfternoonStatus("请假");
						} else {
							attence.setAfternoonStatus("未刷卡");
						}
					} else {
						// 判断请假
						int zaotuiStatus = xbTime.compareTo(oneTime);// 下班前打卡
						if (zaotuiStatus > 0) {
							if (askLeaveUp != null) {// 上午状态
								attence.setMorningStatus("请假");
							} else {
								attence.setMorningStatus("未刷卡");
							}
							attence.setClosingDateTime(oneTime);
						} else {
							if (askLeaveUp != null) {// 上午状态
								attence.setMorningStatus("请假");
							} else {
								attence.setMorningStatus("未刷卡");
							}
							attence.setAfternoonStatus("正常");
							attence.setClosingDateTime(oneTime);
						}
					}
				}
				attence.setAttendanceStatus(attence.getMorningStatus() + ";"
						+ attence.getAfternoonStatus());
			}
		}
		b = totalDao.save(attence);
	}

	private void noandno(Users users, String kqDate, String nowDate,
			String nowTime, BanCi banci, AskForLeave askLeaveUp,
			AskForLeave askLeaveDown, boolean b, int j) {
		Attendance attence = new Attendance();
		attence.setUserId(users.getId());// 人员ID
		attence.setPersonName(users.getName());// 人员姓名
		attence.setDeptName(users.getDept());// 部门
		attence.setCardNo(users.getCardId());// 人员卡号
		attence.setDateTime(kqDate);// 打卡日期时间 kqDtae中的日期
		attence.setOperationDate(Util.getDateTime());// 当前操作时间
		attence.setWorkDateTime(null);// 上班时间
		attence.setClosingDateTime(null);// 下班时间
		if (nowDate.equals(kqDate)) {// 当天的记录
			// String amTime = nowDate + " " +
			// banci.getWxstarttime()+":00";//当前用户午休时间
			// String pmTime = nowDate + " " +
			// banci.getFinishtime()+":00";//当前用户下班时间
			// int result = 1;// >0
			// 则curTime>downLoadTime
			// if (result > 0) {// 数据已经下载
			// int amcom = amTime.compareTo(nowTime);// 上午同步
			// int pmcom = nowTime.compareTo(pmTime);// 下午同步
			// 只处理上午的状态，下午的状态下次处理
			if (askLeaveUp != null) {// 上午状态
				attence.setMorningStatus("请假");
			} else {
				attence.setMorningStatus("未刷卡");
			}
			// } else {// 考勤数据未下载
			// attence.setMorningStatus("数据未下载");
			// attence.setMorningStatus("数据未下载");
			// }
		} else {// 不是当天的记录,封闭考勤日期状态 KQDate
			// 请假记录
			if (askLeaveUp != null) {// 上午状态
				attence.setMorningStatus("请假");
			} else {
				attence.setMorningStatus("旷工");
			}
			if (askLeaveDown != null) {// 下午状态
				attence.setAfternoonStatus("请假");
			} else {
				attence.setAfternoonStatus("旷工");
			}
			attence.setAttendanceStatus(attence.getMorningStatus() + ";"
					+ attence.getAfternoonStatus());
		}
		b = totalDao.save(attence);// 保存汇总信息
		System.out.println("+++++++++++++++++j" + j);
	}

	// 按条件查询考勤分页信息
	@Override
	public Object[] selectAllByAttendancePage(Attendance attendance,
			int pageNo, int pageSize, String startDate, String endDate, String tag) {
		String hql = "from Attendance where 1=1";
		// 条件查询
		if (null != attendance) {
			String status = attendance.getAttendanceStatus();
			if ("cd".equals(status)) {
				attendance.setAttendanceStatus("迟到");
			} else if ("zt".equals(status)) {
				attendance.setAttendanceStatus("早退");
			} else if ("qj".equals(status)) {
				attendance.setAttendanceStatus("请假");
			} else if ("kg".equals(status)) {
				attendance.setAttendanceStatus("缺勤");
			} else if ("jb".equals(status)) {
				attendance.setAttendanceStatus("加班");
			}
			if (null != attendance.getPersonName()
					&& !"".equals(attendance.getPersonName())) {
				String replace = attendance.getPersonName().replace('，', ',');//中文“，”替换
				StringBuffer nameBuffer = new StringBuffer();
				String[] persons = replace.split(",");
				for (int i = 0; i < persons.length; i++) {
					String person = persons[i];
					if(person!=null && !person.equals("")) {
						if(i==0) 
							nameBuffer.append(" personName like '%"+person+"%'");
						else
							nameBuffer.append(" or personName like '%"+person+"%'");
					}
				}
				hql+=" and ("+nameBuffer.toString()+")";
			}
			if (null != attendance.getDeptName()
					&& !"".equals(attendance.getDeptName())) {
				hql += " and deptName='" + attendance.getDeptName() + "'";
			}
			if (null != attendance.getCode()
					&& !"".equals(attendance.getCode())) {
				String replace = attendance.getCode().replace("，",",");
				StringBuffer codeBuffer = new StringBuffer();
				String[] split = replace.split(",");
				for (int i = 0; i < split.length; i++) {
					String code = split[i];
					if(code!=null && !code.equals("")) {
						if(i==0) 
							codeBuffer.append(" code like '%"+code+"%'");
						else
							codeBuffer.append(" or code like '%"+code+"%'");
					}
				}
				hql+=" and ("+codeBuffer.toString()+")";
			}
			if (null != attendance.getDateTime()
					&& !"".equals(attendance.getDateTime())) {
				hql += " and dateTime='" + attendance.getDateTime() + "'";
			}
			if (null != attendance.getAttendanceStatus()
					&& !"".equals(attendance.getAttendanceStatus())) {
				if ("cd".equals(status) || "迟到".equals(status)) {
					hql += " and attendanceStatus like '%"
							+ attendance.getAttendanceStatus() + "%'";
				} else if ("zt".equals(status) || "早退".equals(status)) {
					hql += " and attendanceStatus like '%"
							+ attendance.getAttendanceStatus() + "%'";
				} else if ("qj".equals(status) || "请假".equals(status)) {
					hql += " and qijiaTime > 0";
				} else if ("kg".equals(status) || "缺勤".equals(status)) {
					hql += " and attendanceStatus = '缺勤' and workDateTime is null and closingDateTime is null";
				} else if ("缺勤无卡".equals(status)) {
					hql += " and attendanceStatus = '缺勤' and timeAll is null";
				} else if ("缺勤有卡".equals(status)) {
					hql += " and attendanceStatus = '缺勤' and (timeAll is not null or timeAll <> '')";
				} else if ("正常".equals(status)) {
					hql += " and attendanceStatus = '正常'";
				} else if ("wsk".equals(status) || "未刷卡".equals(status)) {
					hql += " and attendanceStatus = '未刷卡'";
				} else if ("yc".equals(status) || "异常".equals(status)) {
					hql += " and (lateTime+earlyTime+qijiaTime+workTime+queqinTime != workBiaoTime or workTime < 0) and attendanceStatus <> '加班'";
				} else if ("jb".equals(status) || "加班".equals(status)) {
					hql += " and (attendanceStatus = '加班' or jiaBTime > 0)";
				}
			}
		}
		if (null != startDate && !"".equals(startDate) && null != endDate
				&& !"".equals(endDate)) {
			hql += " and dateTime between '" + startDate + "' and '" + endDate
					+ "'";
		}
		Users u = Util.getLoginUser();
		if(u==null){
			return null;
		}
		if("code".equals(tag)){
			hql += " and (code = '"+u.getCode()+"' or userId = "+u.getId()+")";
		}else if("dept".equals(tag)){
			hql += "and deptName='" + u.getDept()+ "'";
		}
		hql += " order by dateTime desc,deptName desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		for (Object object : list) {
			Attendance at2 = (Attendance) object;
			List<AttendanceFu> fu = totalDao.query("from AttendanceFu where attendanceId = ? order by duan asc", at2.getAttendanceId());
			if(fu!=null&&fu.size()>0){
				at2.setFu(fu);
//				setshow(at2, fu);
				setTow(at2);
			}
		}
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	private void setTow(Attendance at2) {
		String date = "'"+at2.getDateTime()+"'";
		date += ",'"+Util.getSpecifiedDayAfter(at2.getDateTime(), 1)+"'";
		at2.setTow(totalDao.query("from AttendanceTow where userId = ? and dateTime in ("+date+")", at2.getUserId()));
	}

	private void setshow(Attendance at2, List<AttendanceFu> fu) {
		StringBuffer show = new StringBuffer();
		for (int i = 0; i < fu.size(); i++) {
			AttendanceFu attendanceFu = fu.get(i);
			if(attendanceFu.getWorkDateTime()!=null){
				show.append("第"+(i+1)+"段上班时间："+attendanceFu.getWorkDateTime()+" ");
			}else {
				show.append("第"+(i+1)+"段上班时间：未刷卡  ");
			}
			if(attendanceFu.getClosingDateTime()!=null){
				show.append("第"+(i+1)+"段下班时间："+attendanceFu.getClosingDateTime()+" ");
			}else {
				show.append("第"+(i+1)+"段下班时间：未刷卡  ");
			}
		}
		at2.setShow(show.toString());
	}

	/**
	 * 根据ID查找出勤明细
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Attendance getAttenceById(Integer id) {
		if (null != id) {
			return (Attendance) totalDao.getObjectById(Attendance.class, id);
		} else {
			return null;
		}
	}

	/**
	 * 更新个人当日考勤状态
	 * 
	 * @param attendance
	 * @return
	 */
	@Override
	public String updateAttenceById(Attendance attendance) {
		Attendance atten = (Attendance) totalDao.getObjectById(
				Attendance.class, attendance.getAttendanceId());
		if (atten != null) {
			//处理附表信息start
			if(attendance.getFu()!=null&&!attendance.getFu().isEmpty()){
				for (AttendanceFu fu : attendance.getFu()) {
					if(fu==null) continue;
					AttendanceFu fu1 = (AttendanceFu) totalDao.getObjectById(AttendanceFu.class, fu.getId());
					if(fu1!=null){
						if(fu1.getDuan()!=null&&fu1.getDuan()==1)//充值调整
							attendance.setWorkDateTime(fu.getWorkDateTime());
						fu1.setWorkDateTime(fu.getWorkDateTime());
						fu1.setClosingDateTime(fu.getClosingDateTime());
						fu1.setExceptTags("2");
						fu1.setExceptTagsNei("已调整");
						totalDao.update(fu1);
					}
				}
			}//处理附表信息end
			if (attendance.getLateTime() + attendance.getEarlyTime()
					+ attendance.getQijiaTime() + attendance.getWorkTime()
					+ attendance.getQueqinTime() == atten.getWorkBiaoTime()) {
				atten.setAttendanceStatus(attendance.getAttendanceStatus());
				atten.setMore(attendance.getMore());
				atten.setMingXiJi(attendance.getMingXiJi());
				atten.setMingXiLiu(attendance.getMingXiLiu());
				atten.setLateTime(attendance.getLateTime());
				atten.setEarlyTime(attendance.getEarlyTime());
				atten.setQijiaTime(attendance.getQijiaTime());
				atten.setWorkTime(attendance.getWorkTime());
				atten.setQueqinTime(attendance.getQueqinTime());
				atten.setJiaBTime(attendance.getJiaBTime());
				atten.setWorkDateTime(attendance.getWorkDateTime());
//				atten.setClosingDateTime(attendance.getClosingDateTime());
				atten.setExceptTags("2");
				atten.setExceptTagsNei("已调整");
				boolean b = totalDao.update(atten);
				// 充值
				BanCi banCi = (BanCi) totalDao.getObjectById(BanCi.class,
						atten.getBanci_Id());
				if (b && ("正常".equals(atten.getAttendanceStatus()) || "公出".equals(atten.getAttendanceStatus()))
						&& atten.getWorkDateTime() != null
						&& atten.getBanci_Id() != null) {
					if(banCi != null){
						BanCiTime banCiTime = (BanCiTime) totalDao.getObjectByCondition("from BanCiTime where banCi.id = ? and duan = 1", atten.getBanci_Id());
						if(banCiTime!=null&&banCiTime.getStartTime()!=null){
							if ((atten.getWorkDateTime().compareTo(
									banCiTime.getStartTime().substring(0, 5)) <= 0)
									||(atten.getWorkBiaoTime()==banCi.getGzTime().intValue())
									||("公出".equals(atten.getAttendanceStatus())
											&&atten.getWorkBiaoTime()+atten.getQijiaTime()==banCi.getGzTime().intValue())) {
								Users users = (Users) totalDao.getObjectById(
										Users.class, atten.getUserId());
								if (users != null)
									AttendanceTowServerImpl.addRechargeZhi(users, atten
											.getDateTime());
							}
						}
					}
				}
				// 处理出勤率
				chuqinLv(atten.getDateTime(), banCi);
				if (b)
					return "更新成功";
			} else {
				return "更新失败！  迟到、早退、请假、缺勤、实际工作，累计时长之和应等于"
						+ atten.getWorkBiaoTime() + "分钟";
			}
		}
		return "消息不存在,更新失败";

	}


	/**
	 * 导出考勤数据表
	 * 
	 * @param attendance
	 * @param startDate
	 * @param endDate
	 */
	@Override
	public void exportDetailExcel(Attendance attendance, String startDate,
			String endDate) {
		String hql = "from Attendance where 1=1";
		// 条件查询
		if (null != attendance) {
			if (null != attendance.getPersonName()
					&& !"".equals(attendance.getPersonName())) {
				hql += " and personName='" + attendance.getPersonName() + "'";
			}
			if (null != attendance.getDeptName()
					&& !"".equals(attendance.getDeptName())) {
				hql += " and deptName='" + attendance.getDeptName() + "'";
			}
			if (null != attendance.getAfternoonStatus()
					&& !"".equals(attendance.getAfternoonStatus())) {
				hql += " and afternoonStatus='"
						+ attendance.getAfternoonStatus() + "'";
			}
			if (null != attendance.getMorningStatus()
					&& !"".equals(attendance.getMorningStatus())) {
				hql += " and morningStatus='" + attendance.getMorningStatus()
						+ "'";
			}
		}
		if (null != startDate && !"".equals(startDate) && null != endDate
				&& !"".equals(endDate)) {
			hql += " and dateTime between '" + startDate + "' and '" + endDate
					+ "'";
		}
		hql += " order by dateTime desc,deptName desc";
		List list = totalDao.find(hql);
		try {
			//打卡时段
			int maxTimeInterval = 0;
			for (Object object : list) {
				Attendance att = (Attendance) object;
				List<AttendanceFu> fu = totalDao.query("from AttendanceFu where attendanceId = ? order by duan asc", att.getAttendanceId());
				if(fu!=null&&fu.size()>0){
					att.setFu(fu);
				}
				if(null!=att.getFu() && att.getFu().size()>maxTimeInterval){
					maxTimeInterval = att.getFu().size();
				}
			}
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("kaoqinHuizong".getBytes("GB2312"), "8859_1")
					+ ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("考勤汇总信息", 0);
			
			//设置列宽
			ws.setColumnView(1, 12);//姓名
			ws.setColumnView(2, 10);//部门
			ws.setColumnView(3, 8);//工号
			ws.setColumnView(4, 15);//卡号
			ws.setColumnView(5, 14);//考勤日期
			
			
			for (int i=0;i<maxTimeInterval;i++) {
				ws.setColumnView(6+i, 12);
				ws.setColumnView(7+i, 12);
			}
			
			ws.setColumnView(7+maxTimeInterval+1, 12);//打卡记录
			ws.setColumnView(7+maxTimeInterval+2, 12);//当天打开状态
			ws.setColumnView(7+maxTimeInterval+3, 12);//工作时长
			ws.setColumnView(7+maxTimeInterval+4, 12);//迟到时长
			ws.setColumnView(7+maxTimeInterval+5, 12);//早退时长
			ws.setColumnView(7+maxTimeInterval+6, 12);//请假时长
			ws.setColumnView(7+maxTimeInterval+7, 12);//缺勤时长
			ws.setColumnView(7+maxTimeInterval+8, 12);//加班时长
			
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 16,
					WritableFont.BOLD, false);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf.setAlignment(Alignment.CENTRE);
			Label label0 = new Label(0, 0, "考勤汇总信息", wcf);
			ws.mergeCells(0, 0, 17, 0);
			ws.addCell(label0);
			
			wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD,false);
			
			WritableCellFormat wc = new WritableCellFormat(wf);
			wc.setVerticalAlignment(VerticalAlignment.CENTRE);
			wc.setAlignment(Alignment.CENTRE);
			
			ws.mergeCells(0, 1, 0, 2);//序号
			ws.addCell(new jxl.write.Label(0, 1, "序号", wc));
			ws.mergeCells(1, 1, 1, 2);//姓名
			ws.addCell(new jxl.write.Label(1, 1, "姓名", wc));
			ws.mergeCells(2, 1, 2, 2);//部门
			ws.addCell(new jxl.write.Label(2, 1, "部门", wc));
			ws.mergeCells(3, 1, 3, 2);//工号
			ws.addCell(new jxl.write.Label(3, 1, "工号", wc));
			ws.mergeCells(4, 1, 4, 2);//卡号
			ws.addCell(new jxl.write.Label(4, 1, "卡号", wc));
			ws.mergeCells(5, 1, 5, 2);//考勤日期
			ws.addCell(new jxl.write.Label(5, 1, "考勤日期", wc));
			String str = "";
			for (int i=0;i<maxTimeInterval;i++) {
				switch (i) {
				case 0:
					str = "第一段考勤时间";
					break;
				case 1:
					str = "第二段考勤时间";
					break;
				case 2:
					str = "第三段考勤时间";
					break;
				case 3:
					str = "第四段考勤时间";
					break;
				case 4:
					str = "第五段考勤时间";
					break;
				}
				ws.mergeCells(6+2*i, 1, 7+2*i, 1);//第一段考勤时间
				ws.addCell(new Label(6+2*i, 1, str, wc));
				
				ws.mergeCells(6+2*i, 2, 6+2*i, 2);
				ws.addCell(new Label(6+2*i, 2, "上班时间", wc));
				
				ws.mergeCells(7+2*i, 2, 7+2*i, 2);
				ws.addCell(new Label(7+2*i, 2, "下班时间", wc));
			}
			int showMax = -1;
			if(maxTimeInterval>0){ showMax = maxTimeInterval+1;}
			ws.mergeCells(6+showMax+1, 1,6+showMax+1, 2);
			ws.addCell(new Label(6+showMax+1, 1, "打卡记录", wc));
			ws.mergeCells(6+showMax+2, 1,6+showMax+2, 2);
			ws.addCell(new Label(6+showMax+2, 1, "当天打开状态", wc));
			ws.mergeCells(6+showMax+3, 1,6+showMax+3, 2);
			ws.addCell(new Label(6+showMax+3, 1, "工作时长", wc));
			ws.mergeCells(6+showMax+4, 1,6+showMax+4, 2);
			ws.addCell(new Label(6+showMax+4, 1, "迟到时长", wc));
			ws.mergeCells(6+showMax+5, 1,6+showMax+5, 2);
			ws.addCell(new Label(6+showMax+5, 1, "早退时长", wc));
			ws.mergeCells(6+showMax+6, 1,6+showMax+6, 2);
			ws.addCell(new Label(6+showMax+6, 1, "请假时长", wc));
			ws.mergeCells(6+showMax+7, 1,6+showMax+7, 2);
			ws.addCell(new Label(6+showMax+7, 1, "缺勤时长", wc));
			ws.mergeCells(6+showMax+8, 1,6+showMax+8, 2);
			ws.addCell(new Label(6+showMax+8, 1, "加班时长", wc));
			wf = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD,false);
			
			WritableCellFormat wcData = new WritableCellFormat(wf);
			wcData.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcData.setAlignment(Alignment.CENTRE);
			
			for (int i = 0; i < list.size(); i++) {
				Attendance atten = (Attendance) list.get(i);
				ws.addCell(new jxl.write.Number(0, i + 3, i + 1, wcData));
				ws.addCell(new Label(1, i + 3, atten.getPersonName(), wcData));
				ws.addCell(new Label(2, i + 3, atten.getDeptName(), wcData));
				ws.addCell(new Label(3, i + 3, atten.getCode(), wcData));
				ws.addCell(new Label(4, i + 3, atten.getCardNo(), wcData));
				ws.addCell(new Label(5, i + 3, atten.getDateTime(), wcData));//考勤日期
				//分段考勤时间
				List<AttendanceFu> fuList = atten.getFu();
				if(null!=fuList && fuList.size()>0){
					for (int y=0;y<fuList.size();y++) {
						ws.addCell(new Label(6+y*2, i + 3, fuList.get(y).getWorkDateTime(), wcData));
						ws.addCell(new Label(6+y*2+1, i + 3, fuList.get(y).getClosingDateTime(), wcData));
//						if(maxTimeInterval-y>0){
//							ws.addCell(new Label(6+y, i + 3, "-", wcData));
//							ws.addCell(new Label(7+y, i + 3, "-", wcData));
//						}else{
//						}
					}
				}else{
					for(int y=0;y<maxTimeInterval;y++){
						ws.addCell(new Label(6+y*2, i + 3, "-", wcData));
						ws.addCell(new Label(6+y*2+1, i + 3, "-", wcData));
					}
				}
				ws.addCell(new Label(6+showMax+1, i + 3, atten.getTimeAll(), wcData));
				ws.addCell(new Label(6+showMax+2, i + 3, atten.getAttendanceStatus(), wcData));
				if(null!=atten.getWorkTime()){
					ws.addCell(new Number(6+showMax+3, i + 3, atten.getWorkTime(), wcData));
				}
				if(null!=atten.getLateTime()){
					ws.addCell(new Number(6+showMax+4, i + 3, atten.getLateTime(), wcData));
				}
				if(null!=atten.getEarlyTime()){
					ws.addCell(new Number(6+showMax+5, i + 3, atten.getEarlyTime(), wcData));
				}
				if(null!=atten.getQijiaTime()){
					ws.addCell(new Number(6+showMax+6, i + 3, atten.getQijiaTime(), wcData));
				}
				if(null!=atten.getQueqinTime()){
					ws.addCell(new Number(6+showMax+7, i + 3, atten.getQueqinTime(), wcData));
				}
				if(null!=atten.getJiaBTime()){
					ws.addCell(new Number(6+showMax+8, i + 3, atten.getJiaBTime(), wcData));
				}
			}
			wwb.write();
			wwb.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	// getter和setter方法
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean sendKaoQin() {
		saveAttendance("huizong");

		Date date1 = new Date();
		Long time1 = date1.getTime();
		Long time2 = time1 - 24 * 60 * 60 * 1000;
		Date date2 = new Date(time2);
		String dateString1 = Util.DateToString(date1, "yyyy-MM-dd");// 今天
		String dateString2 = Util.DateToString(date2, "yyyy-MM-dd");// 昨天
		// 判断是否为双休
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		int week = cal.get(Calendar.DAY_OF_WEEK);
		if (week == 2) {// 星期一
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 3);
			dateString2 = Util.DateToString(cal.getTime(), "yyyy-MM-dd");// 星期五
		}

		// 昨天整天旷工
		List<Attendance> YdayList1 = totalDao
				.query(
						"from Attendance where morningStatus='旷工' and afternoonStatus='旷工' and dateTime=?",
						dateString2);
		// 昨天上午旷工
		List<Attendance> YdayList2 = totalDao
				.query(
						"from Attendance where morningStatus in('未刷卡','迟到') and afternoonStatus not in('未刷卡','旷工','早退') and dateTime=?",
						dateString2);
		// 昨天下午早退
		List<Attendance> YdayList3 = totalDao
				.query(
						"from Attendance where morningStatus !='旷工' and afternoonStatus ='早退' and dateTime=?",
						dateString2);
		// 昨天下午旷工
		List<Attendance> YdayList4 = totalDao
				.query(
						"from Attendance where morningStatus not in('未刷卡','旷工') and afternoonStatus ='未刷卡' and dateTime=?",
						dateString2);
		// 今天未打卡
		List<Attendance> todayList1 = totalDao
				.query(
						"from Attendance where morningStatus ='迟到'  and dateTime=? order by morningStatus",
						dateString1);
		List<Attendance> todayList2 = totalDao
				.query(
						"from Attendance where morningStatus ='未刷卡'  and dateTime=? order by morningStatus",
						dateString1);
		StringBuffer sb = new StringBuffer();
		int n = 1;
		String end = "";
		sb.append("\n\t" + dateString2 + "考勤报表\n");
		sb.append("1.缺勤\n");
		if (YdayList1.size() > 0) {
			n = 1;
		}
		for (Attendance at : YdayList1) {
			if (n % 4 != 0) {
				sb.append(at.getPersonName() + "\t");
				end = "t";
				n++;
			} else {
				sb.append(at.getPersonName() + "\n");
				end = "n";
				n++;

			}
			// //
			// System.out.println(at.getPersonName() + "~" + at.getDateTime()
			// + "~" + at.getMorningStatus() + "~"
			// + at.getAfternoonStatus());
		}
		if (!end.equals("n")) {
			sb.append("\n");
			end = "";
		}
		sb.append("2.迟到(上班未刷卡)\n");
		if (YdayList2.size() > 0) {
			n = 1;
		}
		for (Attendance at : YdayList2) {
			if (n % 4 != 0) {
				sb.append(at.getPersonName() + "\t");
				end = "t";
				n++;
			} else {
				sb.append(at.getPersonName() + "\n");
				end = "n";
				n++;

			}
			// //
			// System.out.println(at.getPersonName() + "~" + at.getDateTime()
			// + "~" + at.getMorningStatus() + "~"
			// + at.getAfternoonStatus());
		}
		if (!end.equals("n")) {
			sb.append("\n");
			end = "";
		}
		sb.append("3.早退(请16:30以后打卡)\n");
		if (YdayList3.size() > 0) {
			n = 1;
		}
		for (Attendance at : YdayList3) {
			if (n % 4 != 0) {
				sb.append(at.getPersonName() + "\t");
				end = "t";
				n++;
			} else {
				sb.append(at.getPersonName() + "\n");
				end = "n";
				n++;

			}

			// //
			// System.out.println(at.getPersonName() + "~" + at.getDateTime()
			// + "~" + at.getMorningStatus() + "~"
			// + at.getAfternoonStatus());
		}
		if (!end.equals("n")) {
			sb.append("\n");
			end = "";
		}
		sb.append("4.下班未刷卡\n");
		if (YdayList4.size() > 0) {
			n = 1;
		}
		for (Attendance at : YdayList4) {
			if (n % 4 != 0) {
				sb.append(at.getPersonName() + "\t");
				end = "t";
				n++;
			} else {
				sb.append(at.getPersonName() + "\n");
				end = "n";
				n++;

			}

			// //
			// System.out.println(at.getPersonName() + "~" + at.getDateTime()
			// + "~" + at.getMorningStatus() + "~"
			// + at.getAfternoonStatus());
		}
		if (!end.equals("n")) {
			sb.append("\n");
			end = "";
		}

		sb.append("\t\n" + dateString1 + "考勤报表");

		if (!end.equals("n")) {
			sb.append("\n");
			end = "";
		}
		sb.append("1.迟到 \n");
		// n = 1;
		// if (todayList1.size() > 0) {
		// }
		for (Attendance at : todayList1) {
			if (n % 4 != 0) {
				sb.append(at.getPersonName() + "\t");
				n++;
			} else {
				sb.append(at.getPersonName() + "\n");
				n++;

			}

			// System.out.println(at.getPersonName()+"~"+at.getDateTime()+"~"+at.getMorningStatus()+"~"+at.getAfternoonStatus());
		}
		if (!end.equals("n")) {
			sb.append("\n");
			end = "";
		}
		sb.append("\n2.上班未刷卡\n");
		if (todayList2.size() > 0) {
			n = 1;
		}

		for (Attendance at : todayList2) {
			if (n % 4 != 0) {
				sb.append(at.getPersonName() + "\t");
				n++;
			} else {
				sb.append(at.getPersonName() + "\n");
				n++;
			}
			// System.out.println(at.getPersonName()+"~"+at.getDateTime()+"~"+at.getMorningStatus()+"~"+at.getAfternoonStatus());
		}

		System.out.println(sb.toString()
				+ "\n\n 说明:由于考勤报表推送试运行并且由系统自动推送,如若数据错误,请您反馈!谢谢!"
				+ "\n\n环境方针\n遵守环保法规  全员参与实施  清洁安全生产" + "节能降耗减废  有效控制风险  不断持续改进"
				+ "\n\n质量方针\n精益生产，一流质量，用户放心");

		System.out.println(LedSendUtil.OnAddtext(99, sb.toString()
				+ "\n\n 说明:由于考勤报表推送试运行并且由系统自动推送,如若数据错误,请您反馈!谢谢!"
				+ "\n\n环境方针\n遵守环保法规  全员参与实施  清洁安全生产" + "节能降耗减废  有效控制风险  不断持续改进"
				+ "\n\n质量方针\n精益生产，一流质量，用户放心", 5, 0, 192, 144));
		return false;
	}
	
	@Override
	public void sendKaoQin_2() {
		// TODO Auto-generated method stub
		//chengeTongGuo();
		//linshi("2017-09-13","2017-09-14");
//		sendAttendanceThree("huizong");
//		AttendanceTowServerImpl.pilaing("2018-05-22","2018-05-23");
		ceshi();
//		chongzhisuanfa();
		// System.out.println("\n\n 说明:由于考勤报表推送试运行并且由系统自动推送,如若数据错误,请您反馈!谢谢!"
		// + "\n\n环境方针\n遵守环保法规  全员参与实施  清洁安全生产" + "节能降耗减废  有效控制风险  不断持续改进"
		// + "\n\n质量方针\n精益生产，一流质量，用户放心");
		// System.out.println(LedSendUtil.OnAddtext(99,"\n元旦放假通知 " +
		// "\n\n根据上海市政府对2017年“元旦”期间放假的统一安排，" +
		// "我公司具体的放假时间为：12月31日至1月2日放假，共3天；" +
		// "1月1日（星期日）为国定假，1月2日（星期一）补休，" +
		// "1月3日(星期二)正常上班。望各部门根据放假时间，安排好工作。" +
		// "公司原则上国家法定假日不安排加班，如有特殊情况，" +
		// "需报由总经理审批，并报人事备案。特此通知！" +
		// "上海红湖排气系统有限公司 " +
		// "\n\t\t2016.12.28", 5, 0, 192, 144));

//		System.out.println(LedSendUtil.OnAddtext(99,"放假通知： " +
//				"\n根据安亭镇政府对2017年“国庆节”放假的通知，并根据公司实际情况，具体的放假时间为：" +
//				"\n    国庆节、中秋节：9月25日（周一）至10月8日（周日）放假，共计14天，10月9日（周一）上班。"
//				+"\n    其中9月25日—9月30日统一计入个人年休，年休不足的人员将从后续年休中补充扣除。10月1日—10月8日按照国家规定放假。10月9日正常上班。"
//				+"\n    望各部门根据放假时间，安排好工作。公司原则上国家法定假日不安排加班，如有特殊情况，需报由总经理审批，并报人事备案。"
//				+"\n    注：根据公司业务情况，信息部门人员不在本次统一放假范围，届时由部门根据工作实际情况安排调休。"
//				+ "\n特此通知。"
//				+ "\n假期间如遇有事，请联系值班人员："
//				+"\n张春毅  电话：18918391711"
//				+"\n张海平  电话：18918391701"
//				+ "\n上海红湖排气系统有限公司" 
//				+"\n\t2017.9.22"
//				, 5, 0, 192, 144));//iFontSize=14
//		System.out.println(LedSendUtil.OnAddtext(99,"通知： " +
//				"\n加工，物流部门人员：\n" +
//				"由于高温天气即将结束，公司决定，自2017年8月14日(下周一)起开始恢复作息时间：8:00（早）至17:00（下午)； 特此通知！"
//				+ "\n\n上海红湖排气系统有限公司"
//				+ "\n\t2017.8.11"
//				, 5, 0, 192, 144));//iFontSize=14
		
//		System.out.println(LedSendUtil.OnAddtext(99,"上海红湖排气系统有限" +
//				"\n\t公司欢迎您\n\n\n环境方针\n遵守环保法规  全员参与实施  清洁安全生产  节能降耗减废  有效控制风险  不断持续改进"
//				+ "\n\n质量方针\n精益生产，一流质量，用户放心\n\n"
//				, 5, 0, 192, 144));//iFontSize=14
//		System.out.println(LedSendUtil.OnAddtext(99,
//				"放 假 通 知 各单位：" +
//				"\n根据安亭镇政府2018年五一劳动节放假的通知，并根据公司实际情况，" +
//				"\n具体的放假时间：4月29日至5月1日放假，共计3天。4月28日（ 星期六）正常上班。5月2日(星期三)正式上班。" +
//				"\n望各部门根据放假时间，安排好工作。公司原则上放假期间不安排加班，如有特殊情况，需报由总经理审批，并报人事备案。 " +
//				"\n特此通知 " +
//				"\n上海红湖排气系统有限公司 " +
//				"\n2018.4.27 正式发布\n\n"
//				, 5, 0, 192, 144));//iFontSize=14
//		通知 ：
//		为庆祝三八妇女节，公司决定于今天下午，全体女职工放假半天，请各部门做好工作安排。
//		在此，公司向所有女职工表示节日的问候。
		
		
		
//		System.out.println(LedSendUtil.OnAddtext(99,"通知：" +
//				"\n兹定于2018年3月26日即下周一在红湖办公大楼四楼会议室召开全厂职工大会，此次会议重要，不许请假！特此通知!望请相互转告！" +
//				"\n上海红湖排气系统有限公司" +
//				"\n\n2018年3月23日"
//				, 5, 0, 192, 144));//iFontSize=14
		
		
		
		
//		System.out.println(LedSendUtil.OnAddtext(99,"通知：" +
//				"\n\t2017年8月1日起，所有公司内部员工和外部人员的车辆一律不允许停放在工厂消防通道，一经发现每次每辆车罚款50元，由公司门卫进行监督，巡查和记录，收到门卫开具罚单的车辆车主需主动到红湖财务部交付罚款，财务开具收据后车辆方可正常进出公司大门，拒不缴款者门卫不得放行，同时上报违规停放车辆的车牌进入黑名单登记系统。公司将所罚款项的50%奖励给值班负责的门卫作为激励。一旦发现有违规停放车辆，门卫要及时阻止，不听劝阻门卫可开具罚单，若是门卫没有发现或故意放任，每次扣除门卫25元。同时鼓励公司全体员工相互监督，及时上报违规停放车辆。"
//				+"\n\n上海红湖排气系统有限公司"
//				+"\n\t2017年7月28日"
//				, 5, 0, 192, 144));//iFontSize=14
//		通知：2017年8月1日起，所有公司内部员工和外部人员的车辆一律不允许停放在工厂消防通道，一经发现每次每辆车罚款50元，由公司门卫进行监督，巡查和记录，收到门卫开具罚单的车辆车主需主动到红湖财务部交付罚款，财务开具收据后车辆方可正常进出公司大门，拒不缴款者门卫不得放行，同时上报违规停放车辆的车牌进入黑名单登记系统。公司将所罚款项的50%奖励给值班负责的门卫作为激励。一旦发现有违规停放车辆，门卫要及时阻止，不听劝阻门卫可开具罚单，若是门卫没有发现或故意放任，每次扣除门卫25元。同时鼓励公司全体员工相互监督，及时上报违规停放车辆。

//		System.out.println(LedSendUtil.OnAddtext(99,"根据目前公司业务和未来发展需求现对以下岗位和人员做如下调整：" +
//				"\n物流部门负责：张媛" +
//				"\n技术工艺负责：杨辉" +
//				"\n订单计划以及测量分析：孟幻想" +
//				"\n生产计划（生产型外委，生产型外购）和内部采购&研发试验及分析：蒲传明" +
//				"\n各部门采购申请通过系统自动汇总，并由部门负责人负责联系卖家送货，办理入库和领用手续。" +
//				"\n采购部门人员只负责大宗商品和原材料采购。特此通知，望各部门知悉。即日起生效。"
//				, 5, 0, 192, 144));
		
		// System.out.println(LedSendUtil.OnAddtext(99,
		// "\n\n 说明:由于考勤报表推送试运行并且由系统自动推送,如若数据错误,请您反馈!谢谢!"
		// + "\n\n环境方针\n遵守环保法规  全员参与实施  清洁安全生产" + "节能降耗减废  有效控制风险  不断持续改进"
		// + "\n\n质量方针\n精益生产，一流质量，用户放心", 5, 0, 192, 144));
//		 System.out.println(LedSendUtil.OnAddtext(99,
//		 "\n \t放  假 通  知\n\n\n\n 各单位：根据安亭镇政府2017年清明节放假的通知，并根据公司实际情况，具体的放假时间：4月2日至4月4日放假，共计3天。4月1日（星期六）调换4月3日（星期一），4月5日（ 星期三）正常上班。 \n\n望各部门根据放假时间，安排好工作。公司原则上放假期间不安排加班，如有特殊情况，需报由总经理审批，并报人事备案。\n\n             特此通知 \n\n上海红湖排气系统有限公司\n\n             2017.3.29", 5, 0, 192, 144));
//		 System.out.println(LedSendUtil.OnAddtext(99,
//		 "\n \t放  假 通  知\n\n\n\n 各单位：为了欢度端午节，经公司领导研究就做好节日期间的安全工作和放假时间以及有关注意事项作以下规定。\n一、放假时间：" +
//		 "2017年5月28日至5月30日，放假3天。6月30日（星期二，农历端午节当日）为国家法定节假日、5月28日（星期日）公休、5月27日（星期六）公休调至5月29日（星期一）、5月31日（星期三）照常上班。" +
//		 "\n二、注意事项：" +
//		 "\n1、公司原则上国家法定假日不安排加班，如有特殊情况，需报由总经理审批，并报人事备案；" +
//		 "\n2、放假前各单位要认真组织一次全面的安全卫生检查,尤其是对各库房和重点部位作重点检查,将发现的问题和存在的不安全因素在假期前及时进行整改；" +
//		 "\n3、各生产班组和工段要认真维护机床设备、各种专用模具要清点入库,各种检查测具,图纸要妥善保管,工位器具要摆放整齐,生产现场要保持整洁；" +
//		 "\n4、各单位要利用假前有限时间做好生产准备和各项保障工作，确保上班后及时开工投产；" +
//		 "\n5、要积极做好防火、防盗、防重大事故、防矛盾激化的工作，确保公司和职工家庭以及自身的安全，如遇突发情况应及时采取有效措施，与门岗保安人员联系，或向公司领导和有关部门的领导报告。" +
//		 "\n\t门卫值班电话：59571809" +
//		 "\n\n上海红湖排气系统有限公司\n\n             2017.3.29", 5, 0, 192, 144));
	}

	@Override
	public void addHuizong_1130(){
		sendAttendance_four(Util.getDateTime("yyyy-MM-dd"), "是");
	}
	@Override
	public void addHuizong_2330(){
		sendAttendance_four(Util.getDateTime("yyyy-MM-dd"), "否");
	}
	
	@Override
	public void randomPunch(){
		String path = "";
		String path1 = "";
		try {
//			Properties prop = new Properties();
//			path = ServletActionContext.getServletContext().getRealPath("/randomPunch.properties");//
//			path1 = "E:\\apache-tomcat-6.0.41_menjin\\webapps\\HHTask\\randomPunch.properties";
//			InputStream in = null;
//			in = new FileInputStream(path1);
//			prop.load(in);
//			String codeList = prop.getProperty("codeList");
//			if(codeList==null||codeList.equals(""))return;
//			String num = prop.getProperty("num");
//			if(num==null)num="2";
//			String mm = prop.getProperty("mm");
//			if(mm==null)mm="10";
//			System.out.println("codeList:"+codeList);
//			String [] str = codeList.split(";");
//			int num1 = str.length;
//			if(num1>Integer.parseInt(num)){
//				num1 = Integer.parseInt(num);
//			}
//			Random ran = new Random();
//			Set<Integer> integers = new HashSet<Integer>();
//			while (integers.size()<num1) {
//				integers.add(ran.nextInt(str.length));
//			}
//			for (Integer integer : integers) {
//				Users ss = (Users) totalDao.getObjectByCondition("from Users where code = ? and onWork <> '离职'", str[integer]);
//				if(ss!=null){
//					addRanP(ss,Integer.parseInt(mm));
//					RtxUtil.sendNotify(ss.getCode(), ss.getName()+"您好！请您到综合办公室面部考勤机签到，"+Util.getDateTime(mm)+"前有效，逾期视为缺勤", "随机签到", "1", "0");
//				}
//			}
//			DoorBangDingServerImpl.caeLogInfor(new StringBuffer(path+path1), null, null, null, null, null, null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DoorBangDingServerImpl.caeLogInfor(new StringBuffer(path+path1), null, null, null, null, null, null, null);
		}
	}

	/**
	 * 生成随机签到记录
	 * @param ss
	 */
	private void addRanP(Users ss,int num) {
		RandomPunch punch = new RandomPunch();
		punch.setAddTime(Util.getDateTime());
		punch.setPunchDate(Util.getDateTime("yyyy-MM-dd"));
		punch.setPunchShiTime(Util.getDateTime(num));
		punch.setUserCode(ss.getCode());
		punch.setUserName(ss.getName());
		punch.setUsersId(ss.getId());
		punch.setStatus("未签到");
		totalDao.save2(punch);
	}
	
	@Override
	public void huizong(String yue){
		List<AttendanceCount> attendanceCounts = totalDao.query("from AttendanceCount where cardDateTime like '%"+yue+"%'");
		for (AttendanceCount a : attendanceCounts) {
			BanCi banCi = (BanCi) totalDao.getObjectById(BanCi.class, a.getBanci_id());
			if(banCi!=null){
				chuqinLv(a.getCardDateTime(),banCi);
			}
		}
	}
	
	private void ceshi() {
		List<String> list = new ArrayList<String>();
		list.add("2018-06-01");
		list.add("2018-06-02");
		list.add("2018-06-03");
		list.add("2018-06-04");
		list.add("2018-06-05");
		list.add("2018-06-06");
		list.add("2018-06-07");
		list.add("2018-06-08");
		list.add("2018-06-09");
		list.add("2018-06-10");
		list.add("2018-06-11");
		list.add("2018-06-12");
		list.add("2018-06-13");
		list.add("2018-06-14");
		list.add("2018-06-15");
		list.add("2018-06-16");
		list.add("2018-06-17");
		list.add("2018-06-18");
		list.add("2018-06-19");
		list.add("2018-06-20");
		list.add("2018-06-21");
		list.add("2018-06-22");
		list.add("2018-06-23");
		list.add("2018-06-24");
		list.add("2018-06-25");
		list.add("2018-06-26");
		list.add("2018-06-27");
		list.add("2018-06-28");
		list.add("2018-06-29");
		list.add("2018-06-30");
		for (String s : list) {
			sendAttendance_four(s, "否");
		}
		return ;
	}

	//修改
	public void chengeTongGuo(){
		List<AccessRecords> records = totalDao.query("from AccessRecords where recordisIn = '内部' and recordStatus = '已开门' " +
				"and addTime > '2017-03-01' and inmarkId <> '-1'");
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
//	public void sendKaoQin_3() {
//		Statement sql;
//		ResultSet rs;
//		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 加载JDBC驱动
//		String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=toolsManager_yt"; // 连接服务器和数据库sample
//		String userName = "sa"; // 默认用户名
//		String userPwd = "pebs_2014"; // 密码
//		Connection dbConn;
//		try {
//			Class.forName(driverName);
//			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
//			sql = dbConn.createStatement();
//			rs = sql
//					.executeQuery("select id,name,sex,phone from sendmsTest where phone is not null and phone <>''  order by id");
//			String id = "";
//			String name = "";
//			String sex = "";
//			String phone = "";
//			while (rs.next()) {
//				id = rs.getString(1);
//				name = rs.getString(2);
//				sex = rs.getString(3);
//				phone = rs.getString(4);
//				ShortMessageServiceImpl sms = new ShortMessageServiceImpl();
//				sms.send(phone, "尊敬的" + name + sex
//						+ "假期结束回岗位，咱们还得努努力！新年要有新目标，咱们的业绩攀新高！"
//						+ "新年要有好心态，撸起袖子加油干！祝愿您新年新状态，好运鸿福精神旺！");
//				System.out.println(id + "___________" + phone + name + sex);
//			}
//			dbConn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		// ShortMessageServiceImpl sms = new ShortMessageServiceImpl();
//		// sms
//		// .send(
//		// "15921533206",
//		// "尊敬的XXX先生/女士：  感谢您在2016年为公司做出了骄人成绩。公司领导 蒯总、毛总、同总 在此向您表示衷心的感谢和敬意！"
//		// + "值此新春佳节之际，谨代表 中国航发东莞阳天电子科技有限公司（http://www.i-brights.com/） "
//		// + "祝福您及您的家人：新年快乐！阖家幸福！万事顺意！");
//	}


	
	
	
	public void sendUserbrithday() {
		// TODO Auto-generated method stub
		String nowDate = Util.getDateTime("yyyy-MM-dd");
		String nowRiQi = nowDate.substring(5, 10);
		String linliDate = GongliZhuanNongliNice.solarTolunar(nowDate);
		// List<Users> usersList =
		// totalDao.query("from Users where internal = '是' and " +
		// "(((password.rili = '阳历' and SUBSTRING(password.shijiBirthDay,5,10) = ?) or (password.rili = '阴历' and SUBSTRING(password.shijiBirthDay,5,10) = ?)) "
		// //+
		// "or ((password.shijiBirthDay is null or password.shijiBirthDay = '') and left(convert(varchar, bothday, 10),5) = ?))"
		// , nowRiQi, linliDate.substring(5, 10));//, nowRiQi
		List<Users> usersList = totalDao
				.query(
						"from Users where internal = '是' and "
								+ "(((password.rili = '阳历' and SUBSTRING(password.shijiBirthDay,5,10) = ?) or (password.rili = '阴历' and SUBSTRING(password.shijiBirthDay,5,10) = ?)) "
								+ "or ((password.shijiBirthDay is null or password.shijiBirthDay = '') and convert(varchar, bothday, 10) like '%"
								+ nowRiQi + "%'))", nowRiQi, linliDate
								.substring(5, 10));
		if (usersList != null && usersList.size() > 0) {
			StringBuffer buf = new StringBuffer();
			for (Users users : usersList) {
				buf.append(users.getName() + " ");
				List<String> list = totalDao
						.query(
								"select code from Users where dept = ? and onWork = '在职'",
								users.getDept());
				if (list != null && list.size() > 0)
					RtxUtil.sendNotify(list, "+" + users.getName() + "text！",
							"系统消息", "0", "0");
			}
			System.out.println("祝我公司以下员工 生日快乐！：" + buf.toString());
		}
	}

	@Override
	public void sendKaoQin_1() {
		// TODO Auto-generated method stub
		String dateString2 = Util.getDateTime("yyyy-MM-dd");// 今天
		String dateString = Util.getSpecifiedDayAfter(dateString2, -1);// 昨天
		// 判断是否为双休
		// Calendar cal = Calendar.getInstance();
		// cal.setTime(date1);
		// int week = cal.get(Calendar.DAY_OF_WEEK);
		// if (week == 2) {// 星期一
		// cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 3);
		// dateString2 = Util.DateToString(cal.getTime(), "yyyy-MM-dd");// 星期五
		// }
		// 昨天整天旷工
		List<Attendance> YdayList1 = totalDao.query(
				"from Attendance where attendanceStatus='缺勤' and dateTime=?",
				dateString);
		// 昨天早退
		List<Attendance> YdayList3 = totalDao
				.query(
						"from Attendance where attendanceStatus like '%早退%' and dateTime=?",
						dateString);
		// 昨天迟到
		List<Attendance> ydayListLate = totalDao
				.query(
						"from Attendance where attendanceStatus like '%迟到%' and dateTime=?",
						dateString);
		// List<Attendance> todayList2 = totalDao
		// .query(
		// "from Attendance where morningStatus ='未刷卡'  and dateTime=?",
		// dateString1);
		StringBuffer sb = new StringBuffer();
		int n = 1;
		String end = "";
		sb.append("\n\t" + dateString + "考勤报表\n");
		sb.append("1.缺勤\n");
		if (YdayList1.size() > 0) {
			n = 1;
		}
		for (Attendance at : YdayList1) {
			if (n % 4 != 0) {
				sb.append(at.getPersonName() + "\t");
				end = "t";
				n++;
			} else {
				sb.append(at.getPersonName() + "\n");
				end = "n";
				n++;

			}
		}
		if (!end.equals("n")) {
			sb.append("\n");
			end = "";
		}
		sb.append("2.迟到(上班未刷卡)\n");
		if (ydayListLate.size() > 0) {
			n = 1;
		}
		for (Attendance at : ydayListLate) {
			if (n % 4 != 0) {
				sb.append(at.getPersonName() + "\t");
				end = "t";
				n++;
			} else {
				sb.append(at.getPersonName() + "\n");
				end = "n";
				n++;

			}
		}
		if (!end.equals("n")) {
			sb.append("\n");
			end = "";
		}
		sb.append("3.早退(请17:00以后打卡)\n");
		if (YdayList3.size() > 0) {
			n = 1;
		}
		for (Attendance at : YdayList3) {
			if (n % 4 != 0) {
				sb.append(at.getPersonName() + "\t");
				end = "t";
				n++;
			} else {
				sb.append(at.getPersonName() + "\n");
				end = "n";
				n++;

			}
		}
		if (!end.equals("n")) {
			sb.append("\n");
			end = "";
		}
		sb.append("4.下班未刷卡\n");
		// if (YdayList4.size() > 0) {
		// n = 1;
		// }
		// for (Attendance at : YdayList4) {
		// if (n % 4 != 0) {
		// sb.append(at.getPersonName() + "\t");
		// end = "t";
		// n++;
		// } else {
		// sb.append(at.getPersonName() + "\n");
		// end = "n";
		// n++;
		//
		// }
		// }
		// if (!end.equals("n")) {
		// sb.append("\n");
		// end = "";
		// }
		//
		// sb.append("\t\n" + dateString1 + "考勤报表");

		// if (!end.equals("n")) {
		// sb.append("\n");
		// end = "";
		// }
		// sb.append("1.迟到 \n");
		// for (Attendance at : todayList1) {
		// if (n % 4 != 0) {
		// sb.append(at.getPersonName() + "\t");
		// n++;
		// } else {
		// sb.append(at.getPersonName() + "\n");
		// n++;
		// }
		// }
		if (!end.equals("n")) {
			sb.append("\n");
			end = "";
		}
		// sb.append("\n2.上班未刷卡\n");
		// if (todayList2.size() > 0) {
		// n = 1;
		// }

		// for (Attendance at : todayList2) {
		// if (n % 4 != 0) {
		// sb.append(at.getPersonName() + "\t");
		// n++;
		// } else {
		// sb.append(at.getPersonName() + "\n");
		// n++;
		// }
		// }

		System.out.println(sb.toString()
				+ "\n\n 说明:由于考勤报表推送试运行并且由系统自动推送,如若数据错误,请您反馈!谢谢!"
				+ "\n\n环境方针\n遵守环保法规  全员参与实施  清洁安全生产" + "节能降耗减废  有效控制风险  不断持续改进"
				+ "\n\n质量方针\n精益生产，一流质量，用户放心");

		// System.out.println(LedSendUtil.OnAddtext(99, sb.toString()
		// + "\n\n 说明:由于考勤报表推送试运行并且由系统自动推送,如若数据错误,请您反馈!谢谢!"
		// + "\n\n环境方针\n遵守环保法规  全员参与实施  清洁安全生产" + "节能降耗减废  有效控制风险  不断持续改进"
		// + "\n\n质量方针\n精益生产，一流质量，用户放心", 5, 0, 192, 144));
	}

	public void TestInter(KQDate kd) {
		kd.setId(kd.getId() + 100);
		System.out.println(kd.getId() + "TestInter");
	}

	public void T1(KQDate kd) {
		kd.setId(kd.getId() + 100);
		System.out.println(kd.getId() + "T1");
		TestInter(kd);
	}

	public static void main(String[] args) {
		AttendanceServerImpl ad = new AttendanceServerImpl();
		KQDate kd = new KQDate();
		kd.setId(300);
		Integer a = 100;
		ad.T1(kd);
		System.out.println(kd.getId());
	}

	@Override
	public boolean saveAttendance_2(String tag) {
		// TODO Auto-generated method stub
		boolean bool = false;
		// 处理有刷卡记录（刷卡表与汇总表对比）
		/**
		 * 获取为同步到汇总表的日期 遍历刷卡表 刷卡数据为同步到汇总表总的记录 存储过程添加排班时间（） 没有同步到，按日期同步
		 * 同步过的按日期差异化补充
		 * 
		 */
		// 没有同步过的刷卡日期
		String hqlSro = "from KQDate where synchroTag=" + 0;
		List listSyo = totalDao.query(hqlSro);
		String nowTime = Util.getDateTime();// 当前时间
		if (listSyo.size() > 0 && null != listSyo) {// 有需要同步遍历的日期
			// 考勤人员信息表
			String hqlusers = "from Users where onWork not in ('离职','内退','退休','病休') and dept not in('內退','病休','供应商','物业','上海庆霆不锈钢制品有限公司','昆山惠明','上海彤庆') and internal = '是' and kaoQinIs = '是' and len(cardId) = 10";
			int countRen = totalDao.getCount(hqlusers);// 当日总人数
			List listusers = totalDao.query(hqlusers);
			// 遍历考勤日期信息表
			for (int i = 0; i < listSyo.size(); i++) {
				KQDate kqd = (KQDate) listSyo.get(i);// 考勤日期对象
				int zhixingCS = kqd.getZhixingCS();// 执行次数
				String syoDate = kqd.getKqDate();// 获得需遍历的日期
				String curDate = Util.getDateTime("yyyy-MM-dd");// 当天日期
				// String downLoadTime = curDate + " 08:10:00";// 下载考勤数据时间
				// String amTime = curDate + " " + Util.wxStartTime() + ":00";//
				// 上午节点时间
				// String pmTime = curDate + " " + Util.xiabanTime() + ":00";//
				// 下午节点时间
				if (zhixingCS > 0 && syoDate.equals(curDate)) {
					bool = true;
				} else {
					// 遍历考勤人员
					for (int j = 0; j < listusers.size(); j++) {// 考勤人员信息表
						// 查询User表
						Users userss = (Users) listusers.get(j);
						if (userss != null) {
							if (userss.getBanci_id() != null
									&& userss.getBanci_id() > 0) {
								BanCi banCi = (BanCi) totalDao.getObjectById(
										BanCi.class, userss.getBanci_id());
								if (banCi != null && banCi.getSbdate() != null) {
									String week = Util.getDateTime("EEEE");// 当前星期几中文
									String[] sbdate = banCi.getSbdate().split(
											",");// 上班日期(星期几);
									boolean bool1 = false;
									for (int l = 0; l < sbdate.length; l++) {
										if (week.equals(sbdate[l].trim())) {
											bool1 = true;
										}
									}
									if (bool1) {
										String sbTime = syoDate + " "
												+ banCi.getFirsttime() + ":00";// 上班时间
										String wxStartTime = syoDate + " "
												+ banCi.getWxstarttime()
												+ ":00";// 午休开始时间
										String wxEndTime = syoDate + " "
												+ banCi.getWxendtime() + ":00";// 午休结束时间
										String xbTime = syoDate + " "
												+ banCi.getFinishtime() + ":00";// 下班时间
										// List list =
										// totalDao.query("from AttendanceTowCollect where cardId=? and dateTime=?",
										// cardId,date);
										// from BrushCard where brushDate=? and
										// personId=?
										String hqlBrush = " from AttendanceTowCollect where cardId=? and dateTime=? and userId = ?";// 打卡汇总表
										String hqlAttence = " from Attendance where dateTime=? and userId=? ";// 考勤汇总表
										AttendanceTowCollect atCollect = (AttendanceTowCollect) totalDao
												.getObjectByCondition(hqlBrush,
														userss.getCardId(),
														syoDate, userss.getId());// 查询是否有打卡信息
										Attendance attence = (Attendance) totalDao
												.getObjectByCondition(
														hqlAttence, syoDate,
														userss.getId());// 查询是否有汇总信息from
										// AskForLeave
										// where
										// leavePerson=?
										// and
										// leaveStartDate>=?
										// "+ "and
										// leaveEndDate<=?
										// and
										// approvalStatus='同意'
										String hqlAskLeave = " from AskForLeave where leaveUserCardId = ? and leavePerson = ? and leavePersonCode = ? and (? < leaveStartDate and leaveStartDate < ?) or (? < leaveEndDate and leaveEndDate < ?) and approvalStatus in ('审批中','同意')";
										if (userss.getName().equals("李聪")) {
											System.out
													.println("*************************");
										}
										// 上午
										AskForLeave askLeaveUp = (AskForLeave) totalDao
												.getObjectByCondition(
														hqlAskLeave, userss
																.getCardId(),
														userss.getName(),
														userss.getCode(),
														sbTime, wxStartTime,
														sbTime, wxStartTime);
										// 下午
										AskForLeave askLeaveDown = (AskForLeave) totalDao
												.getObjectByCondition(
														hqlAskLeave, userss
																.getCardId(),
														userss.getName(),
														userss.getCode(),
														wxEndTime, xbTime,
														wxEndTime, xbTime);
										if (atCollect == null
												&& attence == null) {// 既无刷卡记录，也无汇总记录（没有汇总过，也没刷卡，请假，旷工，未刷卡）
											noandno(userss, syoDate, syoDate,
													nowTime, banCi, askLeaveUp,
													askLeaveDown, bool, j);
										} else if (atCollect != null
												&& attence == null) {// 有刷卡记录，无汇总记录
											// 获取刷卡记录
											yesandno(userss, atCollect,
													syoDate, curDate, nowTime,
													banCi, askLeaveUp,
													askLeaveDown, bool);
										} else if (atCollect != null
												&& attence != null) {// 有刷卡记录，有汇总记录
											// 获取刷卡记录
											yesandyes(userss, atCollect,
													attence, syoDate, curDate,
													nowTime, banCi, askLeaveUp,
													askLeaveDown, bool);
										} else if (atCollect == null
												&& attence != null) {// 无刷卡记录，有汇总记录
											noandyes(attence, syoDate, curDate,
													nowTime, banCi, askLeaveUp,
													askLeaveDown, bool);
										}
									} else {
										// 不在班次已内的处理
									}
								} else {
									// 班次为空或班次日期为空
								}
							} else {
								// 人员未绑定班次
							}
						} else {
							// users表中不存在
						}
					}
				}
				if (zhixingCS > 0 && syoDate.equals(curDate)) {
				} else {
					// 处理出勤率
					String hql = "from AttendanceCount where cardDateTime=?";
					List listAttenCount = totalDao.query(hql, syoDate);
					int nomAM = 0;// 上午正常人数
					int nomPM = 0;// 下午正常人数
					int lastCount = 0;// 迟到人数
					int leaveEar = 0;// 早退人数
					int askCount = 0;// 请假人数
					int noCardCount = 0;// 未刷卡人数
					int kuanggong = 0;// 旷工人数
					// 统计上午正常打卡人数
					String hql_nomAM = " from Attendance where morningStatus=? and dateTime=?";
					nomAM = totalDao.getCount(hql_nomAM, "正常", syoDate);
					// 统计下午正常打卡人数
					String hql_nomPM = " from Attendance where afternoonStatus=? and dateTime=?";
					nomPM = totalDao.getCount(hql_nomPM, "正常", syoDate);
					// 统计迟到打卡人数
					String hql_lastCount = " from Attendance where morningStatus=? and dateTime=?";
					lastCount = totalDao.getCount(hql_lastCount, "迟到", syoDate);
					// 统计早退打卡人数
					String hql_leaveEar = " from Attendance where afternoonStatus=? and dateTime=?";
					leaveEar = totalDao.getCount(hql_leaveEar, "早退", syoDate);
					// 统计旷工人数
					String hql_kuanggongA = " from Attendance where morningStatus=? and dateTime=?";
					String hql_kuanggongP = " from Attendance where afternoonStatus=? and dateTime=?";
					kuanggong = totalDao
							.getCount(hql_kuanggongA, "旷工", syoDate)
							+ totalDao.getCount(hql_kuanggongP, "旷工", syoDate);
					// 统计请假人数
					String hql_askCountA = " from Attendance where morningStatus=? and dateTime=?";
					String hql_askCountP = " from Attendance where afternoonStatus=? and dateTime=?";
					askCount = totalDao.getCount(hql_askCountA, "请假", syoDate)
							+ totalDao.getCount(hql_askCountP, "请假", syoDate);
					// 统计未刷卡人数
					String hql_noCardCountAM = " from Attendance where morningStatus=? and dateTime=?";
					String hql_noCardCountPM = " from Attendance where afternoonStatus=? and dateTime=?";
					noCardCount = totalDao.getCount(hql_noCardCountAM, "未刷卡",
							syoDate)
							+ totalDao.getCount(hql_noCardCountPM, "未刷卡",
									syoDate);
					if (listAttenCount.size() > 0 && null != listAttenCount) {
						// 存在出勤率记录
						AttendanceCount attcou = (AttendanceCount) listAttenCount
								.get(0);
						attcou.setNormalAM(nomAM);
						attcou.setNormalPM(nomPM);
						attcou.setLateCount(lastCount);
						attcou.setLeaveEarlyCount(leaveEar);
						attcou.setAskForLeaveCount(askCount);
						attcou.setNotCardCount(noCardCount);
						attcou.setKuangGongCount(kuanggong);

						float chuqin = 100 * (float) nomAM / countRen;
						if (null != attcou.getTotalAttendance()
								&& attcou.getTotalAttendance() > 0) {
							chuqin = 100 * (float) nomAM
									/ attcou.getTotalAttendance();
						}
						attcou.setAttendance(chuqin);
						bool = totalDao.update(attcou);
					} else {// 没有出勤率记录
						AttendanceCount attcou = new AttendanceCount();
						attcou.setNormalAM(nomAM);
						attcou.setNormalPM(nomPM);
						attcou.setLateCount(lastCount);
						attcou.setLeaveEarlyCount(leaveEar);
						attcou.setAskForLeaveCount(askCount);
						attcou.setNotCardCount(noCardCount);
						attcou.setKuangGongCount(kuanggong);
						float chuqin = 100 * (float) nomAM / countRen;
						attcou.setAttendance(chuqin);
						attcou.setTotalAttendance(countRen);
						attcou.setCardDateTime(syoDate);
						attcou.setOperationDate(Util.getDateTime());
						bool = totalDao.save(attcou);
					}
					/**
					 * 处理考勤日程表 考勤数据全部同步完成，且状态更改
					 */
					String hqlOverAtt = "from Attendance where dateTime=? and attendanceStatus!=null";
					int countOver = totalDao.getCount(hqlOverAtt, syoDate);
					// 同步完成人数等于当日考勤人总数
					if (countOver == countRen) {
						kqd.setSynchroTag(1);
						kqd.setSynchroTime(Util.getDateTime());
						kqd.setZhixingCS(kqd.getZhixingCS() + 1);
					} else if (kqd.getZhixingCS() >= 2) {
						kqd.setSynchroTag(1);
						kqd.setSynchroTime(Util.getDateTime());
						kqd.setZhixingCS(kqd.getZhixingCS() + 1);
					} else {
						kqd.setZhixingCS(kqd.getZhixingCS() + 1);
					}
					bool = totalDao.update(kqd);
				}
			}
		} else {// 无需要同步到日期，直接返回查案记录
			bool = true;
		}
		return bool;
	}

	
	@Override
	public boolean sendAttendanceThree(String tag) {
		// TODO Auto-generated method stub
		// String hqlSro =
		// "from KQDate where kqDate > '2016-08-06' and kqDate < '2016-09-01'";//
		// and kqDate < '2016-08-06'
		// List<KQDate> listSyo = totalDao.query(hqlSro);
		// for (KQDate kQDate : listSyo) {

		// String nowDate = kQDate.getKqDate();
		String nowDate = Util.getDateTime("yyyy-MM-dd");
//		 String nowDate = Util.getDateTime("2017-01-18");
		String nowTime = Util.getDateTime("HH:mm:ss");
		String nowDateTime = nowDate + " " + nowTime;
		String statuss = "";// 总状态
		String hqlBrush = " from AttendanceTowCollect where cardId=? and dateTime=? and userId = ?";// 打卡汇总表
		String hqlAttence = " from Attendance where dateTime=? and userId=? ";// 考勤汇总表
		// 查询班次
		List<BanCi> banCis = totalDao.query("from BanCi ");// where firsttime >
		// ? or finishtime <
		// ? ,
		// nowTime,nowTime
		if (banCis != null && banCis.size() > 0) {
			for (BanCi banCi : banCis) {
				if (banCi.getFinishtime().compareTo(nowTime) < 0
						|| "no".equals(tag)) {
					// 判断是否为上班时间
					boolean b = false;// 默认不计算
					boolean b1 = false;// 不在班次内，但特殊时间表中需要上班的日期
					boolean b2 = false;// 不在上班时间
					if(banCi==null||banCi.getFirsttime()==null||banCi.getWxstarttime()==null||banCi.getWxendtime()==null||banCi.getFinishtime()==null)
						return false;
					String sbTime = banCi.getFirsttime();// 上班时间
					String wxStartTime = banCi.getWxstarttime();// 午休开始时间
					String wxEndTime = banCi.getWxendtime();// 午休结束时间
					String xbTime = banCi.getFinishtime();// 下班时间
					Integer wxmTime = (int) (Util.getWorkTime2(wxStartTime,
							wxEndTime) / 60000);// 午休时长
					Integer workTime = (int) (Util.getWorkTime2(banCi
							.getFirsttime(), banCi.getFinishtime()) / 60000 - wxmTime);
					// 实际工作时长
					if (banCi != null && banCi.getFirsttime() != null
							&& banCi.getFinishtime() != null
							&& banCi.getWxstarttime() != null
							&& banCi.getWxendtime() != null) {
						// 查询特殊时间表
						List<SpecialDate> listSpecil = totalDao
								.query(
										"from SpecialDate where banciId = ? and date = ?",
										banCi.getId(), nowDate);
						if (listSpecil != null && listSpecil.size() > 0) {
							if ("上班".equals(listSpecil.get(0).getSpecialType())) {
								addKQDate(banCi.getId());
								b = true;
								if (isbanci(banCi))// 在班次内，特殊时间表中需要上班的日期
									b1 = true;// 基本不可能
								else
									// 不在班次内的日期，特殊时间表中需要上班的日期，也汇总考勤
									b2 = true;// 查询加班表
							}
						} else {
							b = isbanci(banCi);
						}
						Integer peopleNum = 0;// 需考勤总人数
						if (b) {// 只汇总需要汇总的班次
							List<Users> userslist = totalDao
									.query(
											"from Users where banci_id = ? and onWork not in ('离职','退休','病休','内退') and dept not in('內退','病休','供应商','物业','上海庆霆不锈钢制品有限公司','昆山惠明','上海彤庆') and internal = '是' and len(cardId) = 10",
											banCi.getId());
							if (userslist != null && userslist.size() > 0) {
								peopleNum = userslist.size();
								for (Users users : userslist) {
									Integer lateTime = 0;// 迟到时长
									Integer earlyTime = 0;// 早退时长
									Integer qijiaTime = 0;// 请假时长
									Integer jiaBTime = 0;// 加班时长
									Integer queqinTime = 0;// 缺勤时间
									AttendanceTowCollect atCollect = (AttendanceTowCollect) totalDao
											.getObjectByCondition(hqlBrush,
													users.getCardId(), nowDate,
													users.getId());// 查询是否有打卡信息
									Attendance attence = (Attendance) totalDao
											.getObjectByCondition(hqlAttence,
													nowDate, users.getId());// 查询是否有汇总信息from
									String hqlAskLeave = "from AskForLeave where leaveUserCardId = ? and leavePerson = ? and leavePersonCode = ? and (leaveStartDate like '"
											+ nowDate
											+ "%' or leaveEndDate like '"
											+ nowDate
											+ "%' or (? < leaveEndDate and leaveStartDate < ?)) and approvalStatus in ('同意')";
									// 请假记录
									List<AskForLeave> askLeaveUp = totalDao
											.query(hqlAskLeave, users
													.getCardId(), users
													.getName(),
													users.getCode(), nowDate,
													nowDate);
									boolean askStatus = false;// 没有请假记录
									if (askLeaveUp != null
											&& askLeaveUp.size() > 0)
										askStatus = true;
									/**
									 * 既无刷卡记录，也无汇总记录
									 */
									if (atCollect == null && attence == null) {// 既无刷卡记录，也无汇总记录（没有汇总过，也没刷卡，请假，旷工，未刷卡）
										boolean isnotWX = false;// 是否减去午休时间
										attence = addAttendance(users, nowDate,
												nowDateTime, workTime);// 得到新对象
										// 请假记录
										if (askStatus) {
											Askforless(attence, askLeaveUp,
													nowDate, sbTime, xbTime,
													isnotWX, wxStartTime,
													wxEndTime, qijiaTime,
													workTime, wxmTime,
													queqinTime);
										} else {
											statuss = "缺勤";
											attence
													.setAttendanceStatus(statuss);
											queqinTime = workTime - qijiaTime;
											attence.setQueqinTime(queqinTime);
										}
										attence.setWorkTime(workTime
												- attence.getLateTime()
												- attence.getEarlyTime()
												- attence.getQijiaTime()
												- attence.getQueqinTime());
										totalDao.save(attence);
									}
									/**
									 * 有刷卡记录，无汇总记录
									 */
									else if (atCollect != null
											&& attence == null) {// 有刷卡记录，无汇总记录
										// 获取刷卡记录
										boolean isnotWX = false;// 是否减去午休时间
										boolean inS = false;// 进门刷卡值为空 为true
										boolean outS = false;// 出门刷卡值为空 为true
										// 进门刷卡时间
										String inTime = "";
										String[] brushIn = null;//进门时间
										if (atCollect.getInTime() != null&&atCollect.getInTime().length()>3) {
											brushIn = atCollect.getInTime().split(" ");
											inTime = brushIn[0];
										} else {
											inS = true;
										}

										// 出门刷卡时间
										String outTime = "";
										String[] brushOut = null;//出门时间
										if (atCollect.getOutTime() != null) {
											brushOut = atCollect.getOutTime().split(" ");
											outTime = brushOut[brushOut.length - 1];
										} else {
											outS = true;
										}
										attence = addAttendance(users, nowDate,
												nowDateTime, workTime);
										/**
										 * 如果进出门时间为空，查询Time字段
										 * 2017-09-12
										 * **/
										if(inS&&outS){
											if (atCollect.getTime() != null&&atCollect.getTime().length() > 3) {//不为空格
												inS = false;
												brushIn = atCollect.getTime().split(" ");
												inTime = brushIn[0];
												if (brushIn.length > 1){//打卡多次
													outS = false;
													outTime = brushIn[brushIn.length - 1];
												}
											}
										}
										/**兼容纯手机打卡记录**/
										if (!inS)
											attence.setWorkDateTime(inTime);
										if (!outS)
											attence.setClosingDateTime(outTime);
										/**兼容纯手机打卡方式**/
										if (inS) {
											// 进门为空的情况，准备查询请假表
											// 请假记录
											if (askStatus) {
												Askforless(attence, askLeaveUp,
														nowDate, sbTime,
														xbTime, isnotWX,
														wxStartTime, wxEndTime,
														qijiaTime, workTime,
														wxmTime);
											} else {
												statuss = "缺勤";
												attence
														.setAttendanceStatus(statuss);
												queqinTime = workTime
														- qijiaTime;
												attence
														.setQueqinTime(queqinTime);
											}
										} else {
											if (sbTime.substring(0, 5)
													.compareTo(inTime) >= 0) {// 第一次进门时间
												AttendanceTowServerImpl
														.addRechargeZhi(users);// 充值
												statuss = "正常";
												attence
														.setAttendanceStatus(statuss);
											} else {
												// 是否有请假记录
												if (askStatus) {
													Askforless(attence,
															askLeaveUp,
															nowDate, sbTime,
															xbTime, isnotWX,
															wxStartTime,
															wxEndTime,
															qijiaTime,
															workTime, wxmTime);
												} else {
													LateLess(attence, statuss,
															sbTime, xbTime,
															inTime,
															wxStartTime,
															wxEndTime,
															lateTime, workTime,
															wxmTime, queqinTime);
												}
											}
											if (outS) {
												// 出门未刷卡，判定为异常考情
												// 是否有请假记录
												if (askStatus)
													Askforless(attence,
															askLeaveUp,
															nowDate, sbTime,
															xbTime, isnotWX,
															wxStartTime,
															wxEndTime,
															qijiaTime,
															workTime, wxmTime);
												else {
													attence.setExceptTags("1");
													attence.setWorkTime(0);
													attence
															.setAttendanceStatus("未刷卡");
													attence
															.setExceptTagsNei("出门时间为空");
												}
											} else {
												if (xbTime.substring(0, 5)
														.compareTo(outTime) <= 0) {// 最后一次出门时间大于等于下班时间
												} else {
													// 查询是否有请假申请
													// 是否有请假记录
													if (askStatus) {
														Askforless(attence,
																askLeaveUp,
																nowDate,
																sbTime, xbTime,
																isnotWX,
																wxStartTime,
																wxEndTime,
																qijiaTime,
																workTime,
																wxmTime);
													} else {
														EarlyLess(
																attence,
																attence.getAttendanceStatus(),
																sbTime, xbTime,
																outTime,
																wxStartTime,
																wxEndTime,
																earlyTime,
																workTime,
																wxmTime,
																queqinTime);
													}
												}
											}
										}
										if ("1".equals(attence.getExceptTags())) {
										} else
											attence.setWorkTime(workTime
													- attence.getLateTime()
													- attence.getEarlyTime()
													- attence.getQijiaTime()
													- attence.getQueqinTime());
										totalDao.save(attence);
									}
									/**
									 * 有刷卡记录，有汇总记录
									 */
									else if (atCollect != null
											&& attence != null) {// 有刷卡记录，有汇总记录
										if (!"2"
												.equals(attence.getExceptTags())) {// 已经手动调整过的
											attence.setLateTime(lateTime);
											attence.setEarlyTime(earlyTime);
											attence.setJiaBTime(jiaBTime);
											attence.setWorkTime(workTime);
											attence.setWorkBiaoTime(workTime);//*
											attence.setQueqinTime(queqinTime);
											attence.setQijiaTime(qijiaTime);
											attence.setAttendanceStatus(statuss);// 当天状态
											attence.setBanci_Id(banCi.getId());// *
											attence.setBanci_Name(banCi.getName());//*
											// *//
											// 此种情况无需重新计算迟到时间。如果请假和加班时长为1分钟，需重新计算。早退需重新计算*/
											boolean inS = false;// 进门刷卡值为空 为true
											boolean outS = false;// 出门刷卡值为空
											// 为true
											boolean isnotWX = false;// 是否减去午休时间
											// 进门刷卡时间
											String inTime = "";
											String[] brushIn = null;
											if (atCollect.getInTime() != null) {
												inTime = atCollect.getInTime();
												brushIn = inTime.split(" ");
											} else {
												inS = true;
											}
											// 出门刷卡时间
											String outTime = "";
											String[] brushOut = null;
											if (atCollect.getOutTime() != null) {
												outTime = atCollect
														.getOutTime();
												brushOut = outTime.split(" ");
											} else {
												outS = true;
											}
											if (!inS)
												attence
														.setWorkDateTime(brushIn[0]);
											if (!outS)
												attence
														.setClosingDateTime(brushOut[brushOut.length - 1]);
											if (inS) {
												// 进门为空的情况，准备查询请假表
												if (askStatus) {
													Askforless(attence,
															askLeaveUp,
															nowDate, sbTime,
															xbTime, isnotWX,
															wxStartTime,
															wxEndTime,
															qijiaTime,
															workTime, wxmTime);
												} else {
													statuss = "缺勤";
													attence
															.setAttendanceStatus(statuss);
													queqinTime = workTime
															- qijiaTime;
													attence
															.setQueqinTime(queqinTime);
												}
											} else {
												if (sbTime.substring(0, 5)
														.compareTo(brushIn[0]) >= 0) {// 第一次进门时间
													statuss = "正常";
													attence
															.setAttendanceStatus(statuss);
												} else {
													// 是否有请假记录
													if (askStatus) {
														Askforless(attence,
																askLeaveUp,
																nowDate,
																sbTime, xbTime,
																isnotWX,
																wxStartTime,
																wxEndTime,
																qijiaTime,
																workTime,
																wxmTime);
													} else {
														LateLess(attence,
																statuss,
																sbTime, xbTime,
																brushIn[0],
																wxStartTime,
																wxEndTime,
																lateTime,
																workTime,
																wxmTime,
																queqinTime);
													}
												}
												if (outS) {
													// 出门未刷卡，判定为异常考情
													// 查询是否有请假申请
													if (askStatus)
														Askforless(attence,
																askLeaveUp,
																nowDate,
																sbTime, xbTime,
																isnotWX,
																wxStartTime,
																wxEndTime,
																qijiaTime,
																workTime,
																wxmTime);
													else {
														attence
																.setExceptTags("1");
														attence.setWorkTime(0);
														attence
																.setAttendanceStatus("未刷卡");
														attence
																.setExceptTagsNei("出门时间为空");
													}
												} else {
													attence.setExceptTags(null);
													attence
															.setExceptTagsNei(null);
													String lastTime = brushOut[brushOut.length - 1];// 最后一次出门时间
													if (lastTime
															.compareTo(xbTime
																	.substring(
																			0,
																			5)) >= 0) {// 最后一次出门时间
														// 大于下班时间
														// 状态为正常
													} else {
														// 查询是否有请假申请
														// 是否有请假记录
														if (askStatus) {
															Askforless(
																	attence,
																	askLeaveUp,
																	nowDate,
																	sbTime,
																	xbTime,
																	isnotWX,
																	wxStartTime,
																	wxEndTime,
																	qijiaTime,
																	workTime,
																	wxmTime);
														} else {
															EarlyLess(
																	attence,
																	attence.getAttendanceStatus(),
																	sbTime,
																	xbTime,
																	lastTime,
																	wxStartTime,
																	wxEndTime,
																	earlyTime,
																	workTime,
																	wxmTime,
																	queqinTime);
														}
													}
												}
											}
											if ("1".equals(attence
													.getExceptTags())) {
											} else
												attence
														.setWorkTime(workTime
																- attence
																		.getLateTime()
																- attence
																		.getEarlyTime()
																- attence
																		.getQijiaTime()
																- attence
																		.getQueqinTime());
											totalDao.update(attence);
										}
									}
									/**
									 * 无刷卡记录，有汇总记录
									 */
									else if (atCollect == null
											&& attence != null) {// 无刷卡记录，有汇总记录
										boolean isnotWX = false;// 是否减去午休时间
										attence.setWorkTime(0);
										// 请假记录
										if (askStatus) {
											Askforless(attence, askLeaveUp,
													nowDate, sbTime, xbTime,
													isnotWX, wxStartTime,
													wxEndTime, qijiaTime,
													workTime, wxmTime,
													queqinTime);
										} else {
											// 判断是否存在状态
											attence.setQueqinTime(workTime);// 当天状态
											attence.setAttendanceStatus("缺勤");// 当天状态
											attence.setWorkTime(0);
										}
										attence.setBanci_Id(banCi.getId());
										attence.setBanci_Name(banCi.getName());
										attence.setWorkBiaoTime(workTime);
										totalDao.update(attence);
									}

									// if (zhixingCS > 0 &&
									// syoDate.equals(curDate)) {
									// } else {
									//								
									// /**
									// * 处理考勤日程表 考勤数据全部同步完成，且状态更改
									// */
									// String hqlOverAtt =
									// "from Attendance where dateTime=? and attendanceStatus!=null";
									// int countOver =
									// totalDao.getCount(hqlOverAtt, syoDate);
									// // 同步完成人数等于当日考勤人总数
									// if (countOver == countRen) {
									// kqd.setSynchroTag(1);
									// kqd.setSynchroTime(Util.getDateTime());
									// kqd.setZhixingCS(kqd.getZhixingCS() + 1);
									// } else if (kqd.getZhixingCS() >= 2) {
									// kqd.setSynchroTag(1);
									// kqd.setSynchroTime(Util.getDateTime());
									// kqd.setZhixingCS(kqd.getZhixingCS() + 1);
									// } else {
									// kqd.setZhixingCS(kqd.getZhixingCS() + 1);
									// }
									// bool = totalDao.update(kqd);
									// }
								}

								// 处理出勤率
								chuqinLv(nowDate, banCi);
							}
						}
						// if (b2) {
						// 查询加班申请
						// 只汇总有加班申请对影班次的用户
						// }
						// 每天都要查询加班申请
						List<Overtime> overtimes = totalDao
								.query("from Overtime where convert(varchar,endDate,120) like '"
										+ nowDate
										+ "%' and status not in ('未审批','审批中')");
						if (overtimes != null && overtimes.size() > 0) {
							for (Overtime overtime : overtimes) {
								// Date startDate = overtime.getStartDate();
								// Date endDate = overtime.getEndDate();
								// Date startDate = null;
								// Date endDate = null;
								// if (overtime.getFilnalStartDate()==null)
								// startDate = overtime.getStartDate();
								// else{
								// if
								// (overtime.getStartDate().getTime()-overtime.getFilnalStartDate().getTime()>0)
								// startDate = overtime.getStartDate();
								// else
								// startDate = overtime.getFilnalStartDate();
								// }
								// if (overtime.getFilnalEndDate()==null)
								// endDate = overtime.getEndDate();
								// else{
								// if
								// (overtime.getFilnalEndDate().getTime()-overtime.getEndDate().getTime()>0)
								// endDate = overtime.getEndDate();
								// else
								// endDate = overtime.getFilnalEndDate();
								// }
								Long datetime = (Util.getWorkTime1(overtime
										.getStartDate(), overtime.getEndDate()) / 60000);
								Integer dtime = datetime.intValue();
								//根据加班信息查到汇总记录
								Attendance attendances = (Attendance) totalDao.getObjectByCondition("from Attendance where userId = ? and dateTime = ?", overtime.getOvertimeId(),nowDate);
								if (attendances!=null) {
									attendances.setJiaBTime(dtime);
									totalDao.update2(attendances);
								}else {
									Attendance attence = new Attendance();
//									attence.setUserId(overtime.getOvertimeId());// 人员ID
//									attence.setPersonName(overtime.getOvertimeName());// 人员姓名
//									attence.setDeptName(overtime.getOvertimeDept());// 部门
//									attence.setCardNo(overtime.getOvertimeCardId());// 人员卡号
//									attence.setDateTime(nowDate);// 当天时间
//									attence.setOperationDate(nowDateTime);// 当前操作时间
//									attence.setTags("1");// 新算法标识
//									attence.setWorkBiaoTime(workTime);
//									attence.setJiaBTime(dtime);
//									attence.setAttendanceStatus("加班");
									attence = addAttendance(overtime, nowDate, nowDateTime, workTime, dtime);
									totalDao.save(attence);
								}
							}
						}
					}
				}
			}
		}
		//关闭卷帘门
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
		return true;
	}

	/**
	 * 计算班次当天出勤率
	 * @param nowDate
	 * @param banCi
	 * @param peopleNum
	 */
	private void chuqinLv(String nowDate, BanCi banCi) {
		int peopleNum = totalDao.getCount("from Users where banci_id = ? and onWork not in ('离职','退休','病休','内退') and dept not in('內退','病休','供应商','物业','上海庆霆不锈钢制品有限公司','昆山惠明','上海彤庆') and internal = '是'", banCi.getId());
		String hql = "from AttendanceCount where cardDateTime=? and banci_id = ?";
		List listAttenCount = totalDao.query(hql,
				nowDate, banCi.getId());
		int nomdate = 0;// 当天正常人数
		int lastCount = 0;// 迟到人数
		int leaveEar = 0;// 早退人数
		int askCount = 0;// 请假人数
		int kuanggong = 0;// 旷工人数
		// 统计当天正常打卡人数
		String hql_nomAM = " from Attendance where attendanceStatus=? and dateTime=? and workTime = workBiaoTime and banci_Id = ?";
		nomdate = totalDao.getCount(hql_nomAM, "正常",
				nowDate, banCi.getId());
		// 统计迟到人数
		String hql_lastCount = " from Attendance where attendanceStatus like '%迟到%' and dateTime=? and banci_Id = ?";
		lastCount = totalDao.getCount(hql_lastCount,
				nowDate, banCi.getId());
		// 统计早退人数
		String hql_leaveEar = " from Attendance where attendanceStatus like '%早退%' and dateTime=? and banci_Id = ?";
		leaveEar = totalDao.getCount(hql_leaveEar,
				nowDate, banCi.getId());
		// 统计旷工人数
		String hql_kuanggong = " from Attendance where attendanceStatus=? and dateTime=? and banci_Id = ?";
		kuanggong = totalDao.getCount(hql_kuanggong,
				"缺勤", nowDate, banCi.getId());
		// 统计请假人数
		String hql_askCount = " from Attendance where qijiaTime>0 and dateTime=? and banci_Id = ?";
		askCount = totalDao.getCount(hql_askCount,
				nowDate, banCi.getId());
		// 统计未刷卡人数
		float chuqin = 0f;
		chuqin = 100 * (float) nomdate / peopleNum;
		if (listAttenCount.size() > 0
				&& null != listAttenCount) {
			// 存在出勤率记录
			AttendanceCount attcou = (AttendanceCount) listAttenCount
					.get(0);
			attcou.setNormalDate(nomdate);// 正常
			attcou.setLateCount(lastCount);// 迟到
			attcou.setLeaveEarlyCount(leaveEar);// 早退
			attcou.setAskForLeaveCount(askCount);// 请假
			attcou.setKuangGongCount(kuanggong);// 旷工
			attcou.setTotalAttendance(peopleNum);
			chuqin = 100 * (float) nomdate / attcou.getTotalAttendance();
			attcou.setAttendance(chuqin);
			totalDao.update(attcou);
		} else {// 没有出勤率记录
			AttendanceCount attcou = new AttendanceCount();
			attcou.setNormalDate(nomdate);
			attcou.setLateCount(lastCount);
			attcou.setLeaveEarlyCount(leaveEar);
			attcou.setAskForLeaveCount(askCount);
			attcou.setKuangGongCount(kuanggong);
			attcou.setAttendance(chuqin);
			attcou.setTotalAttendance(peopleNum);
			attcou.setCardDateTime(nowDate);
			attcou.setOperationDate(Util.getDateTime());
			attcou.setBanci_id(banCi.getId());
			attcou.setBanci_name(banCi.getName());
			totalDao.save(attcou);
		}
	}
	
	/**
	 * @author licong 2017-11-28
	 * @param date 日期
	 * 定时汇总没刷卡的人员
	 * @return
	 */
	public boolean sendAttendance_four(String date,String isfou) {
		// TODO Auto-generated method stub
		String nowDate = date;
		if(date==null||"".equals(date))
			nowDate = Util.getDateTime("yyyy-MM-dd");
		String nowTime = Util.getDateTime("HH:mm");
//		String nowDateTime = nowDate + " " + nowTime;
		// 查询班次
		String hqiBan = "from BanCi ";
		boolean bb = true;//不隔夜班次
		if("是".equals(isfou)){//汇总晚班
			hqiBan += " where isOvernight = '是'";
			bb = false;
		}else{
			hqiBan += " where isOvernight <> '是' or isOvernight is null";
		}
		List<BanCi> banCis = totalDao.query(hqiBan);
		if (banCis != null && banCis.size() > 0) {
			for (BanCi banCi : banCis) {
				if(bb){//判断今天是否为考勤日
				}else {
					nowDate = Util.getSpecifiedDayAfter(nowDate, -1);
				}
				Map<String, Object> map = BanCiServerImpl.updateORsearchIfyouReWork(null, banCi.getId(), nowDate,totalDao);
				if(map!=null && map.get("banciId")!=null) {//当前日期班次在考勤日
					
//				if(AttendanceTowServerImpl.isbanci1(, nowDate)){
					addKQDate(banCi.getId(),nowDate);
					//查询当前班次内无刷卡记录的用户=>创建汇总信息
					List<Users> userslist = totalDao.query(
						"from Users where banci_id = ? and onWork not in ('离职','退休','病休','内退') and dept not in('內退','病休','供应商','物业','上海庆霆不锈钢制品有限公司','昆山惠明','上海彤庆') and internal = '是'" +
						" and id not in (select userId from Attendance where dateTime = ?)",banCi.getId(),nowDate);
					if (userslist != null && userslist.size() > 0) {
						for (Users users : userslist) {
							AttendanceTowServerImpl.addAttendAndFu(users, nowDate, nowTime, banCi);
						}
					}
					//查询当前缺勤时间大于0且有请假记录的用户
					String hqlAskLeave = "select leavePersonCode from AskForLeave where approvalStatus in ('同意','未审批','审批中') and (leaveStartDate like '"
						+ nowDate
						+ "%' or leaveEndDate like '"
						+ nowDate
						+ "%' or (? < leaveEndDate and leaveStartDate < ?))";
					List<String> we = totalDao.query(hqlAskLeave, nowDate, nowDate);
					if(we!=null&&we.size()>0){
						for (String code : we) {
							if(code!=null){
								Attendance attence = (Attendance) totalDao.getObjectByCondition("from Attendance where dateTime = ? and code = ?", 
										nowDate, code);
								boolean askStatus = false;// 没有请假记录
								List<AskForLeave> askLeaveUp = null;
								if(attence!=null){
									if("2".equals(attence.getExceptTags())){//已修改过
										continue;
									}
									String hqlAskLeave1 = "from AskForLeave where leaveUserCardId = ? and leavePerson = ? and leavePersonCode = ? and (leaveStartDate like '"
										+ nowDate
										+ "%' or leaveEndDate like '"
										+ nowDate
										+ "%' or (? < leaveEndDate and leaveStartDate < ?)) and approvalStatus in ('同意')";
									// 请假记录
									askLeaveUp = totalDao
									.query(hqlAskLeave1, attence
											.getCardNo(), attence
											.getPersonName(),
											attence.getCode(), nowDate,
											nowDate);
									if (askLeaveUp != null
											&& askLeaveUp.size() > 0)
										askStatus = true;
								}else{
									Users us = (Users) totalDao.getObjectByCondition("from Users where code = ? and onWork not in ('离职','退休','病休','内退') and dept not in('內退','病休','供应商','物业','上海庆霆不锈钢制品有限公司','昆山惠明','上海彤庆') and internal = '是'", code);
									if(us!=null){
										attence = AttendanceTowServerImpl.addAttendAndFu(us, date, nowTime, banCi);
									}
								}
								boolean isnotWX = false;// 是否减去午休时间
								attence.setWorkTime(0);
								List<BanCiTime> banCiTimes = new ArrayList<BanCiTime>();
								banCiTimes.addAll(banCi.getBanCiTime());
								// 请假记录
								if (askStatus) {
									Askforless(attence, askLeaveUp,
											nowDate, banCiTimes.get(0).getStartTime(), banCiTimes.get(banCiTimes.size()-1).getEndTime(),
											isnotWX, banCiTimes.get(0).getEndTime(),
											banCiTimes.get(banCiTimes.size()-1).getStartTime(), 0,
											banCi.getGzTime(), banCi.getXxTime()==null?60:banCi.getXxTime(),
											attence.getQueqinTime());
								}
								attence.setBanci_Id(banCi.getId());
								attence.setBanci_Name(banCi.getName());
//								attence.setWorkBiaoTime(480);
								totalDao.update(attence);
								
								
//								if(attence.getQueqinTime()>0){
//									Integer que = attence.getQueqinTime();
//									attence.setQijiaTime(que);
//									attence.setQueqinTime(0);
//									attence.setAttendanceStatus(ask.getLeaveTypeOf());
//									attence.setMingXiJi(ask.getLeaveTypeOf()+":"+que+";");
//									attence.setMingXiLiu(ask.getLeaveTypeOf() + ":" + nowDate
//											+ " " + Util.formatDuring(que * 60000) + " |");
//									/***处理请假之后的流程，包括年休换休的计算。***/
//								}else {
//									attence.setAttendanceStatus("缺勤");
//								}
								totalDao.update(attence);
							}
						}
					}
				}
				//计算班次当天出勤率
				chuqinLv(nowDate, banCi);
				//查询加班记录
				List<Overtime> overtimese = totalDao
					.query("from Overtime where startDate like '" + nowDate
						+ "%' and status not in ('未审批','打回')");
				if (overtimese != null && overtimese.size() > 0) {
					for (Overtime overtime : overtimese) {
						boolean b = false;
						if(overtime!=null){
							Attendance attence = (Attendance) totalDao.getObjectByCondition("from Attendance where dateTime = ? and code = ?", 
									nowDate, overtime.getOvertimeCode());
							if(attence==null){
								b = true;
								Users us = (Users) totalDao.getObjectByCondition("from Users where code = ? and onWork not in ('离职','退休','病休','内退')", overtime.getOvertimeCode());
								if(us!=null){
									attence = AttendanceTowServerImpl.addAttendAndFu(us, date, nowTime, banCi);
								}
							}else {
								if("2".equals(attence.getExceptTags())){//已修改过
									continue;
								}
							}
							if(attence.getJiaBTime()==0){
								if(b){
									attence.setQueqinTime(0);
									attence.setAttendanceStatus("加班");
								}
								attence.setJiaBTime(overtime.getOverTimeLong().intValue()*60);
								/***处理请假之后的流程，包括年休换休的计算。***/
								totalDao.update(attence);
							}
						}
					}
				}
			}
		}
		//关闭卷帘门
//		List<AccessEquipment> ace = totalDao.query("from AccessEquipment where equipmentDaoType = '卷闸门'");
//		for (AccessEquipment a : ace) {
//			if(a!=null&&a.getState()!=null&&"打开".equals(a.getState())){
//				Integer dport = Integer.parseInt(a.getEquipmentPort());
//				String message = Util.OpenDoor(a.getEquipmentIP(), dport, 26);//开门操作
//				if ("true".equals(message)) {
//					a.setState("关闭");
//					a.setOperationNote(a.getOperationNote()+"<br/>"+"系统，关门，"+Util.getDateTime());
//					if(totalDao.update(a)){//更新库位状态
//					}else totalDao.update2(a);
//				}
//			}
//		}
		return true;
	}

	@Override
	public boolean saveAttendance_4(String tag) {
		return true;
	}

	/**
	 * 
	 * @param users
	 *            用户信息
	 * @param nowDate
	 *            当前日期
	 * @param nowDateTime
	 *            当前时间
	 * @param workTime
	 *            班次工作时长
	 * @return
	 */
	private Attendance addAttendance(Users users, String nowDate,
			String nowDateTime, Integer workTime) {
		Attendance attence = new Attendance();
		attence.setUserId(users.getId());// 人员ID
		attence.setPersonName(users.getName());// 人员姓名
		attence.setDeptName(users.getDept());// 部门
		attence.setCardNo(users.getCardId());// 人员卡号
		attence.setDateTime(nowDate);// 当天时间
		attence.setOperationDate(nowDateTime);// 当前操作时间
		attence.setWorkDateTime(null);// 上班时间
		attence.setClosingDateTime(null);// 下班时间
		attence.setTags("1");// 新算法标识
		attence.setWorkTime(workTime);
		attence.setWorkBiaoTime(workTime);
		attence.setBanci_Id(users.getBanci_id());
		attence.setBanci_Name(users.getBanci_name());
		attence.setLateTime(0);
		attence.setEarlyTime(0);
		attence.setJiaBTime(0);
		attence.setQueqinTime(0);
		attence.setQijiaTime(0);
		attence.setAttendanceStatus("");
		return attence;
	}

	/**
	 * 
	 * @param users
	 *            用户信息
	 * @param nowDate
	 *            当前日期
	 * @param nowDateTime
	 *            当前时间
	 * @param workTime
	 *            班次工作时长
	 * @return
	 */
	private Attendance addAttendance(Overtime overtime, String nowDate,
			String nowDateTime, Integer workTime, Integer jiaban) {
		Attendance attence = new Attendance();
		attence.setUserId(overtime.getOvertimeId());// 人员ID
		attence.setPersonName(overtime.getOvertimeName());// 人员姓名
		attence.setDeptName(overtime.getOvertimeDept());// 部门
		attence.setCardNo(overtime.getOvertimeCardId());// 人员卡号
		attence.setDateTime(nowDate);// 当天时间
		attence.setOperationDate(nowDateTime);// 当前操作时间
		attence.setTags("1");// 新算法标识
		attence.setWorkBiaoTime(workTime);
		attence.setJiaBTime(jiaban);
		attence.setLateTime(0);
		attence.setEarlyTime(0);
		attence.setJiaBTime(0);
		attence.setQueqinTime(0);
		attence.setQijiaTime(0);
		attence.setAttendanceStatus("加班");
		return attence;
	}

	/**
	 * 查询当前用户今天是否上班
	 * 
	 * @param users
	 * @return
	 */
	public boolean isbanci(BanCi banCi) {
		boolean bool1 = false;
		// String week = "星期四";// 当前星期几中文
		String dateTime = Util.getDateTime("yyyy-MM-dd");
		Map<String, Object> map = BanCiServerImpl.updateORsearchIfyouReWork(null, banCi.getId(), dateTime,totalDao);
		if(map!=null && map.get("banciId")!=null) {
			bool1 = true;
		}
//		String week = Util.getDateTime("E");// 当前星期几中文
//		String[] sbdate = banCi.getSbdate().split(",");// 上班日期(星期几);
//		for (int l = 0; l < sbdate.length; l++) {
//			if (week.equals(sbdate[l].trim())) {
//				bool1 = true;
//			}
//		}
		if (bool1)
			addKQDate(banCi.getId());
		return bool1;
	}

	public void addKQDate(Integer banciId) {
		String nowDate = Util.getDateTime("yyyy-MM-dd");
		int i = totalDao.getCount(
				"from KQDate where kqDate = ? and banci_Id = ?", nowDate,
				banciId);
		if (i == 0) {
			KQDate date = new KQDate();
			date.setSynchroTag(1);
			date.setKqDate(nowDate);
			date.setBanci_Id(banciId);
			totalDao.save2(date);
		}
	}
	public void addKQDate(Integer banciId,String newdate) {
		String nowDate = newdate;
		if(newdate==null||"".equals(newdate))
			nowDate = Util.getDateTime("yyyy-MM-dd");
		int i = totalDao.getCount(
				"from KQDate where kqDate = ? and banci_Id = ?", nowDate,
				banciId);
		if (i == 0) {
			KQDate date = new KQDate();
			date.setSynchroTag(1);
			date.setKqDate(nowDate);
			date.setBanci_Id(banciId);
			totalDao.save2(date);
		}
	}

	/**
	 * 有刷卡记录
	 * 
	 * @param statuss
	 *            状态为请假
	 * @param askLeaveUp
	 *            请假记录
	 * @param nowDate
	 *            当前日期
	 * @param sbTime
	 *            上班时间
	 * @param xbTime
	 *            下班时间
	 * @param isnotWX
	 *            是否减去午休时间
	 * @param wxStartTime
	 *            午休开始时间
	 * @param wxEndTime
	 *            午休结束时间
	 * @param qijiaTime
	 *            请假时长
	 * @param workTime
	 *            工作时长
	 * @param wxmTime
	 *            午休时长
	 * @param firstTi
	 *            第一次进门打卡时间
	 * @param lastTi
	 *            最后一次出门打卡时间
	 */
	public void Askforless(Attendance attence, List<AskForLeave> askLeaveUp,
			String nowDate, String sbTime, String xbTime, boolean isnotWX,
			String wxStartTime, String wxEndTime, Integer qijiaTime,
			Integer workTime, Integer wxmTime, String firstTi, String lastTi) {
		// 状态
		String statuss = "";// 请假类型
		StringBuffer buffer = new StringBuffer();// 请假明细
		StringBuffer buffer1 = new StringBuffer();// 请假计算
		// 计算请假时长
		for (AskForLeave askForLeave : askLeaveUp) {
			isnotWX = false;
			Integer qijia = 0;
			String startS = "";// 有效请假开始时间
			String shiJistart = "";// 实际请假开始时间
			String endS = "";// 有效请假结束时间
			String shijiEnd = "";// 实际请假结束时间
			// boolean isnotWX = false;//是否减去午休时间
			if (askForLeave.getLeaveEndDate().compareTo(nowDate + " " + sbTime) <= 0
					|| (nowDate + " " + xbTime).compareTo(askForLeave
							.getLeaveStartDate()) <= 0) {// 请假结束时间小于等于上班时间或请假开始时间大于等于下班时间
				continue;// 此申请无效
			} else {
				// 确定开始时间
				if (askForLeave.getExitTime() != null
						&& !"".equals(askForLeave.getExitTime())) {
					shiJistart = askForLeave.getExitTime();
				} else {
					shiJistart = askForLeave.getLeaveStartDate();
				}
				if (shiJistart.compareTo(nowDate + " " + sbTime) <= 0) {// 请假开始时间小于等于上班时间
					startS = nowDate + " " + sbTime;
					isnotWX = true;
				} else if (shiJistart.compareTo(nowDate + " " + wxStartTime) <= 0) {// 请假开始时间小于等于午休时间
					startS = shiJistart;
					isnotWX = true;
				} else if (shiJistart.compareTo(nowDate + " " + wxEndTime) <= 0) {// 请假开始时间小于等于午休时间
					startS = nowDate + " " + wxEndTime;
				} else if (shiJistart.compareTo(nowDate + " " + xbTime) < 0) {// 请假开始时间小于等于午休时间
					startS = shiJistart;
				} else {
					continue;
				}
				// 确定结束时间
				if (askForLeave.getReturnTime() != null
						&& !"".equals(askForLeave.getReturnTime())) {
					shijiEnd = askForLeave.getReturnTime();
				} else {
					shijiEnd = askForLeave.getLeaveEndDate();
				}
				if ((nowDate + " " + xbTime).compareTo(shijiEnd) <= 0) {// 请假结束时间大于等于下班时间
					endS = nowDate + " " + xbTime;
				} else if ((nowDate + " " + wxEndTime).compareTo(shijiEnd) <= 0) {// 请假结束时间大于等于午休结束时间
					endS = shijiEnd;
				} else if ((nowDate + " " + wxStartTime).compareTo(shijiEnd) <= 0) {// 请假结束时间大于等于午休开始时间
					endS = nowDate + " " + wxStartTime;
					isnotWX = false;
				} else if ((nowDate + " " + sbTime).compareTo(shijiEnd) < 0) {// 请假结束时间大于等于上班时间
					endS = shijiEnd;
					isnotWX = false;
				} else {
					continue;
				}
				// 实际请假时间endS-startS isnotWX为true 减去午休时间
				if (isnotWX)
					qijia = (int) ((Util.getWorkTime1(startS, endS)) / 60000 - wxmTime);
				else
					qijia = (int) ((Util.getWorkTime1(startS, endS)) / 60000);
				if (workTime < qijia)
					qijia = workTime;
				qijiaTime += qijia;
				workTime -= qijia;
				attence.setQijiaTime(qijiaTime);
				if ("".equals(statuss))
					statuss = askForLeave.getLeaveTypeOf();
				else if (!statuss.equals(askForLeave.getLeaveTypeOf()))
					statuss = statuss + "," + askForLeave.getLeaveTypeOf();
				buffer.append(askForLeave.getLeaveTypeOf() + ":" + nowDate
						+ " " + Util.formatDuring(qijia.longValue() * 60000)
						+ " ");
				buffer1
						.append(askForLeave.getLeaveTypeOf() + ":" + qijia
								+ ";");
			}
		}
		attence.setMingXiLiu(buffer.toString() + " | ");
		attence.setMingXiJi(buffer1.toString());
		attence.setAttendanceStatus(statuss);
	}

	/**
	 * 有刷卡记录
	 * 
	 * @param statuss
	 *            状态为请假
	 * @param askLeaveUp
	 *            请假记录
	 * @param nowDate
	 *            当前日期
	 * @param sbTime
	 *            上班时间
	 * @param xbTime
	 *            下班时间
	 * @param isnotWX
	 *            是否减去午休时间
	 * @param wxStartTime
	 *            午休开始时间
	 * @param wxEndTime
	 *            午休结束时间
	 * @param qijiaTime
	 *            请假时长
	 * @param workTime
	 *            工作时长
	 * @param wxmTime
	 *            午休时长
	 */
	public void Askforless(Attendance attence, List<AskForLeave> askLeaveUp,
			String nowDate, String sbTime, String xbTime, boolean isnotWX,
			String wxStartTime, String wxEndTime, Integer qijiaTime,
			Integer workTime, Integer wxmTime) {
		// 状态
		String statuss = "";// 请假类型
		StringBuffer buffer = new StringBuffer();// 请假明细
		StringBuffer buffer1 = new StringBuffer();// 请假计算
		// 计算请假时长
		for (AskForLeave askForLeave : askLeaveUp) {
			isnotWX = false;
			Integer qijia = 0;
			String startS = "";// 有效请假开始时间
			String shiJistart = "";// 实际请假开始时间
			String endS = "";// 有效请假结束时间
			String shijiEnd = "";// 实际请假结束时间
			// boolean isnotWX = false;//是否减去午休时间
			if (askForLeave.getLeaveEndDate().compareTo(nowDate + " " + sbTime) <= 0
					|| (nowDate + " " + xbTime).compareTo(askForLeave
							.getLeaveStartDate()) <= 0) {// 请假结束时间小于等于上班时间或请假开始时间大于等于下班时间
				continue;// 此申请无效
			} else {
				// 确定开始时间
				if (askForLeave.getExitTime() != null
						&& !"".equals(askForLeave.getExitTime())) {
					shiJistart = askForLeave.getExitTime();
				} else {
					shiJistart = askForLeave.getLeaveStartDate();
				}
				if (shiJistart.compareTo(nowDate + " " + sbTime) <= 0) {// 请假开始时间小于等于上班时间
					startS = nowDate + " " + sbTime;
					isnotWX = true;
				} else if (shiJistart.compareTo(nowDate + " " + wxStartTime) <= 0) {// 请假开始时间小于等于午休时间
					startS = shiJistart;
					isnotWX = true;
				} else if (shiJistart.compareTo(nowDate + " " + wxEndTime) <= 0) {// 请假开始时间小于等于午休时间
					startS = nowDate + " " + wxEndTime;
				} else if (shiJistart.compareTo(nowDate + " " + xbTime) < 0) {// 请假开始时间小于等于午休时间
					startS = shiJistart;
				} else {
					continue;
				}
				// 确定结束时间
				if (askForLeave.getReturnTime() != null
						&& !"".equals(askForLeave.getReturnTime())) {
					shijiEnd = askForLeave.getReturnTime();
				} else {
					shijiEnd = askForLeave.getLeaveEndDate();
				}
				if ((nowDate + " " + xbTime).compareTo(shijiEnd) <= 0) {// 请假结束时间大于等于下班时间
					endS = nowDate + " " + xbTime;
				} else if ((nowDate + " " + wxEndTime).compareTo(shijiEnd) <= 0) {// 请假结束时间大于等于午休结束时间
					endS = shijiEnd;
				} else if ((nowDate + " " + wxStartTime).compareTo(shijiEnd) <= 0) {// 请假结束时间大于等于午休开始时间
					endS = nowDate + " " + wxStartTime;
					isnotWX = false;
				} else if ((nowDate + " " + sbTime).compareTo(shijiEnd) < 0) {// 请假结束时间大于等于上班时间
					endS = shijiEnd;
					isnotWX = false;
				} else {
					continue;
				}
				// 实际请假时间endS-startS isnotWX为true 减去午休时间
				if (isnotWX)
					qijia = (int) ((Util.getWorkTime1(startS, endS)) / 60000 - wxmTime);
				else
					qijia = (int) ((Util.getWorkTime1(startS, endS)) / 60000);
				if (workTime < qijia)
					qijia = workTime;
				qijiaTime += qijia;
				workTime -= qijia;
				attence.setQijiaTime(qijiaTime);
				if ("".equals(statuss))
					statuss = askForLeave.getLeaveTypeOf();
				else if (!statuss.equals(askForLeave.getLeaveTypeOf()))
					statuss = statuss + "," + askForLeave.getLeaveTypeOf();
				buffer.append(askForLeave.getLeaveTypeOf() + ":" + nowDate
						+ " " + Util.formatDuring(qijia.longValue() * 60000)
						+ " ");
				buffer1
						.append(askForLeave.getLeaveTypeOf() + ":" + qijia
								+ ";");
			}
		}
		attence.setMingXiLiu(buffer.toString() + " | ");
		attence.setMingXiJi(buffer1.toString());
		attence.setAttendanceStatus(statuss);
	}

	/**
	 * 没有刷卡记录
	 * 
	 * @param statuss
	 *            状态为请假
	 * @param askLeaveUp
	 *            请假记录
	 * @param nowDate
	 *            当前日期
	 * @param sbTime
	 *            上班时间
	 * @param xbTime
	 *            下班时间
	 * @param isnotWX
	 *            是否减去午休时间
	 * @param wxStartTime
	 *            午休开始时间
	 * @param wxEndTime
	 *            午休结束时间
	 * @param qijiaTime
	 *            请假时长
	 * @param workTime
	 *            工作时长
	 * @param wxmTime
	 *            午休时长
	 * @param queqinTime
	 *            缺勤时长
	 */
	public void Askforless(Attendance attence, List<AskForLeave> askLeaveUp,
			String nowDate, String sbTime, String xbTime, boolean isnotWX,
			String wxStartTime, String wxEndTime, Integer qijiaTime,
			Integer workTime, Integer wxmTime, Integer queqinTime) {
		// 状态
		String statuss = "";// 请假类型
		StringBuffer buffer = new StringBuffer();// 请假明细
		StringBuffer buffer1 = new StringBuffer();// 请假计算
		// 计算请假时长
		for (AskForLeave askForLeave : askLeaveUp) {
			isnotWX = false;
			Integer qijia = 0;
			String startS = "";// 有效请假开始时间
			String shiJistart = "";// 实际请假开始时间
			String endS = "";// 有效请假结束时间
			String shijiEnd = "";// 实际请假结束时间
			// boolean isnotWX = false;//是否减去午休时间
			if (askForLeave.getLeaveEndDate().compareTo(nowDate + " " + sbTime) <= 0
					|| (nowDate + " " + xbTime).compareTo(askForLeave
							.getLeaveStartDate()) <= 0) {// 请假结束时间小于等于上班时间或请假开始时间大于等于下班时间
				continue;// 此申请无效
			} else {
				// 确定开始时间
				if (askForLeave.getExitTime() != null
						&& !"".equals(askForLeave.getExitTime())) {
					shiJistart = askForLeave.getExitTime();
				} else {
					shiJistart = askForLeave.getLeaveStartDate();
				}
				if (shiJistart.compareTo(nowDate + " " + sbTime) <= 0) {// 请假开始时间小于等于上班时间
					startS = nowDate + " " + sbTime;
					isnotWX = true;
				} else if (shiJistart.compareTo(nowDate + " " + wxStartTime) <= 0) {// 请假开始时间小于等于午休时间
					startS = shiJistart;
					isnotWX = true;
				} else if (shiJistart.compareTo(nowDate + " " + wxEndTime) <= 0) {// 请假开始时间小于等于午休时间
					startS = nowDate + " " + wxEndTime;
				} else if (shiJistart.compareTo(nowDate + " " + xbTime) < 0) {// 请假开始时间小于等于午休时间
					startS = shiJistart;
				} else {
					continue;
				}
				// 确定结束时间
				if (askForLeave.getReturnTime() != null
						&& !"".equals(askForLeave.getReturnTime())) {
					shijiEnd = askForLeave.getReturnTime();
				} else {
					shijiEnd = askForLeave.getLeaveEndDate();
				}
				if ((nowDate + " " + xbTime).compareTo(shijiEnd) <= 0) {// 请假结束时间大于等于下班时间
					endS = nowDate + " " + xbTime;
				} else if ((nowDate + " " + wxEndTime).compareTo(shijiEnd) <= 0) {// 请假结束时间大于等于午休结束时间
					endS = shijiEnd;
				} else if ((nowDate + " " + wxStartTime).compareTo(shijiEnd) <= 0) {// 请假结束时间大于等于午休开始时间
					endS = nowDate + " " + wxStartTime;
					isnotWX = false;
				} else if ((nowDate + " " + sbTime).compareTo(shijiEnd) < 0) {// 请假结束时间大于等于上班时间
					endS = shijiEnd;
					isnotWX = false;
				} else {
					continue;
				}
				// 实际请假时间endS-startS isnotWX为true 减去午休时间
				if (isnotWX)
					qijia = (int) ((Util.getWorkTime1(startS, endS)) / 60000 - wxmTime);
				else
					qijia = (int) ((Util.getWorkTime1(startS, endS)) / 60000);
				if (workTime < qijia)
					qijia = workTime;
				qijiaTime += qijia;
				workTime -= qijia;
				if ("".equals(statuss))
					statuss = askForLeave.getLeaveTypeOf();
				else if (!statuss.equals(askForLeave.getLeaveTypeOf()))
					statuss = statuss + "," + askForLeave.getLeaveTypeOf();
				buffer.append(askForLeave.getLeaveTypeOf() + ":" + nowDate
						+ " " + Util.formatDuring(qijia.longValue() * 60000)
						+ " ");
				buffer1
						.append(askForLeave.getLeaveTypeOf() + ":" + qijia
								+ ";");
			}
		}
		attence.setQijiaTime(qijiaTime);
		attence.setMingXiLiu(buffer.toString() + "| ");
		attence.setMingXiJi(buffer1.toString());
		attence.setQueqinTime(workTime);
		attence.setAttendanceStatus(statuss);
	}

	/**
	 * 
	 * @param statuss
	 *            状态为早退
	 * @param sbTime
	 * @param xbTime
	 * @param lastTime
	 * @param wxStartTime
	 * @param wxEndTime
	 * @param earlyTime
	 * @param workTime
	 * @param wxmTime
	 * @param queqinTime
	 */
	public void EarlyLess(Attendance attence, String statuss, String sbTime,
			String xbTime, String lastTime, String wxStartTime,
			String wxEndTime, Integer earlyTime, Integer workTime,
			Integer wxmTime, Integer queqinTime) {
		if ("正常".equals(statuss)) {
			statuss = "早退";
		} else {
			statuss = statuss + ",早退";
		}
		// 计算早退时间
		// 如果打卡时间为上午。早退时间要减去午休时间
		if (wxEndTime.substring(0, 5).compareTo(lastTime) <= 0) {// 最后一次出门时间大于等于午休结束时间
			earlyTime = (int) ((Util.getWorkTime2(lastTime, xbTime)) / 60000);
		} else if (wxStartTime.substring(0, 5).compareTo(lastTime) <= 0) {// 最后一次出门时间大于等于午休开始时间
			earlyTime = (int) ((Util.getWorkTime2(wxEndTime, xbTime)) / 60000);
		} else if (sbTime.substring(0, 5).compareTo(lastTime) <= 0) {// 最后一次出门时间大于等于上班开始时间
			earlyTime = (int) ((Util.getWorkTime2(lastTime, xbTime)) / 60000 - wxmTime);
		} else {
			statuss = "缺勤";
			queqinTime = workTime;
		}
		attence.setEarlyTime(earlyTime);
		attence.setQueqinTime(queqinTime);
		attence.setAttendanceStatus(statuss);
	}

	/**
	 * 迟到
	 * 
	 * @param statuss
	 * @param sbTime
	 * @param xbTime
	 * @param brushIn
	 * @param wxStartTime
	 * @param wxEndTime
	 * @param lateTime
	 * @param workTime
	 * @param wxmTime
	 * @param queqinTime
	 */
	public void LateLess(Attendance attence, String statuss, String sbTime,
			String xbTime, String brushIn, String wxStartTime,
			String wxEndTime, Integer lateTime, Integer workTime,
			Integer wxmTime, Integer queqinTime) {
		statuss = "迟到";
		// 计算迟到时间
		// 如果打卡时间为下午。迟到时间要减去午休时间
		if (brushIn.compareTo(wxStartTime) <= 0) {// 大于等于午休开始时间
			lateTime = (int) ((Util.getWorkTime2(sbTime, brushIn)) / 60000);
			workTime -= lateTime;
		} else if (brushIn.compareTo(wxEndTime) <= 0) {// 大于等于午休结束时间
			lateTime = (int) ((Util.getWorkTime2(sbTime, wxStartTime)) / 60000);
			workTime -= lateTime;
		} else if (brushIn.compareTo(xbTime) <= 0) {// 大于等于午休结束时间
			lateTime = (int) ((Util.getWorkTime2(sbTime, brushIn)) / 60000 - wxmTime);
			workTime -= lateTime;
		} else {
			statuss = "缺勤";
			queqinTime = workTime;
			workTime = 0;
		}
		attence.setWorkTime(workTime);
		attence.setLateTime(lateTime);
		attence.setQueqinTime(queqinTime);
		attence.setAttendanceStatus(statuss);
	}

	@Override
	public void jisianChongzhi(String date, String code, Integer banci) {
		// TODO Auto-generated method stub
		List<KQDate> dates = totalDao.query(
				"from KQDate where banci_Id = ? and kqDate like '%" + date
						+ "%'", banci);
		if (code != null && !"".equals(code)) {
			for (KQDate kqDate : dates) {
				AttendanceTowServerImpl.addRechargeAttend(kqDate.getKqDate(),
						code);
			}
		} else {
			for (KQDate kqDate : dates) {
				AttendanceTowServerImpl.addRechargeAttend(kqDate.getKqDate());
			}
		}
		//上一天的充值重复
//		List<RechargeZhi> list = totalDao.query("from RechargeZhi where dateTime = '2017-04-26'");
//		if(list!=null&&list.size()>0){
//			for (RechargeZhi rechargeZhi : list) {
//				Users u = (Users) totalDao.getObjectById(Users.class, rechargeZhi.getUserId());
//				if(u!=null){
//					AttendanceTowServerImpl.addRechargeZhi(u, "2017-04-27");
//				}
//			}
//		}
	}

	
	@Override
	public List showRechargeZhi(String startDate, String endDate,
			RechargeZhi rechargeZhi) {
		// TODO Auto-generated method stub
		String sql = "from RechargeZhi where allId = ?";
		String sql1 = "from RechargeAll where 1=1";
		if (startDate != null && !"".equals(startDate) && endDate != null
				&& !"".equals(endDate) && endDate.compareTo(startDate) >= 0) {
			sql += " and dateTime between '" + startDate + "' and '" + endDate
					+ "'";
		} else {
			sql += " and dateTime like '%"
					+ Util.getLastMonth(Util.getDateTime("yyyy-MM")) + "%'";
		}
		if (rechargeZhi != null) {
			if (rechargeZhi.getDept() != null
					&& !"".equals(rechargeZhi.getDept())) {
				sql += " and dept = '" + rechargeZhi.getDept() + "'";
				sql1 += " and dept = '" + rechargeZhi.getDept() + "'";
			}
			if (rechargeZhi.getName() != null
					&& !"".equals(rechargeZhi.getName())) {
				sql += " and name = '" + rechargeZhi.getName() + "'";
				sql1 += " and name = '" + rechargeZhi.getName() + "'";
			}
		}
		List<RechargeZhi> rechargeZhis = new ArrayList<RechargeZhi>();
		sql1 += " order by dept";
		List<RechargeAll> alls = totalDao.query(sql1);
		for (RechargeAll rechargeAll : alls) {
			int zhi = totalDao.getCount(sql, rechargeAll.getId());
			List<Float> zhiz = totalDao.query("select sum(balanceMoney) "+sql, rechargeAll.getId());
			Float ff = 0f;
			if(zhiz.size()>0){
				ff = zhiz.get(0);
			}else
				continue;
			RechargeZhi rechargeZhi1 = new RechargeZhi();
			rechargeZhi1.setSumNumber(zhi);
			rechargeZhi1.setName(rechargeAll.getName());
			rechargeZhi1.setCode(rechargeAll.getCode());
			rechargeZhi1.setDept(rechargeAll.getDept());
			rechargeZhi1.setCardId(rechargeAll.getCardId());
			rechargeZhi1.setSumNumberZong(ff);
			rechargeZhis.add(rechargeZhi1);
		}
		return rechargeZhis;
	}

	@Override
	public void daochuExc(String startTime, String endTime,
			RechargeZhi rechargeZhi) {
		// TODO Auto-generated method stub
		List list = showRechargeZhi(startTime, endTime, rechargeZhi);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("充值汇总".getBytes("GB2312"), "8859_1") + ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("汇总数据", 0);
			ws.setColumnView(1, 15);
			ws.setColumnView(2, 10);
			ws.setColumnView(4, 18);
			ws.setColumnView(5, 15);
			ws.setColumnView(6, 20);
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 14,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLACK);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf.setAlignment(Alignment.CENTRE);

			jxl.write.Label label0 = new Label(0, 0, "汇总数据", wcf);
			ws.addCell(label0);
			ws.mergeCells(0, 0, 6, 0);

			wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD,
					false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			WritableCellFormat wc = new WritableCellFormat(wf);
			wc.setAlignment(Alignment.CENTRE);
			ws.addCell(new jxl.write.Label(0, 1, "序号", wc));
			ws.addCell(new jxl.write.Label(1, 1, "姓名", wc));
			ws.addCell(new jxl.write.Label(2, 1, "工号", wc));
			ws.addCell(new jxl.write.Label(3, 1, "部门", wc));
			ws.addCell(new jxl.write.Label(4, 1, "卡号", wc));
			ws.addCell(new jxl.write.Label(5, 1, "充值数(份)", wc));
			ws.addCell(new jxl.write.Label(6, 1, "当月充值金额(元)", wc));

			for (int i = 0; i < list.size(); i++) {
				RechargeZhi go = (RechargeZhi) list.get(i);
				ws.addCell(new jxl.write.Number(0, i + 2, i + 1, wc));
				ws.addCell(new Label(1, i + 2, go.getName(), wc));
				ws.addCell(new Label(2, i + 2, go.getCode(), wc));
				ws.addCell(new Label(3, i + 2, go.getDept(), wc));
				ws.addCell(new Label(4, i + 2, go.getCardId(), wc));
				ws
						.addCell(new jxl.write.Number(5, i + 2, go
								.getSumNumber(), wc));
				ws.addCell(new jxl.write.Number(6, i + 2,
						go.getSumNumberZong(), wc));
			}
			wwb.write();
			wwb.close();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<Overtime> getOverTimeByAtt(Attendance attendance) {

		String hql = "from Overtime where overtimeId = '"
				+ attendance.getUserId() + "' and (startDate < '"
				+ attendance.getDateTime() + "' and endDate > '"
				+ attendance.getDateTime() + "' or startDate like '%"
				+ attendance.getDateTime() + "%' or endDate like '%"
				+ attendance.getDateTime() + "%')";
		List<Overtime> oTimeList = totalDao.query(hql);
		return oTimeList;
	}

	@Override
	public List<AskForLeave> getAskForLeaveByAtt(Attendance attendance) {
		Users u = (Users) totalDao.get(Users.class, attendance.getUserId());
		String hql = "from AskForLeave where leavePersonCode = '" + u.getCode()
				+ "' and (leaveStartDate < '" + attendance.getDateTime()
				+ "' and leaveEndDate > '" + attendance.getDateTime()
				+ "' or leaveStartDate like '%" + attendance.getDateTime()
				+ "%' or leaveEndDate like '%" + attendance.getDateTime()
				+ "%')";
		List<AskForLeave> aForLeaveList = totalDao.query(hql);
		return aForLeaveList;
	}
	
	@Override
	public List<AttendanceTow> getAttendanceTowAtt(Attendance attendance) {
		// TODO Auto-generated method stub
		String date = "'"+attendance.getDateTime()+"'";
		if(attendance.getBanci_Id()!=null){
//			BanCi banCi = (BanCi) totalDao.getObjectById(BanCi.class, attendance.getBanci_Id());
//			if("门卫班次".equals(banCi.getBanCiType())){
//				date += ",'"+Util.getSpecifiedDayAfter(attendance.getDateTime(), 1)+"'";
//				date += ",'"+Util.getSpecifiedDayAfter(attendance.getDateTime(), -1)+"'";
//			}
			date += ",'"+Util.getSpecifiedDayAfter(attendance.getDateTime(), 1)+"'";
			date += ",'"+Util.getSpecifiedDayAfter(attendance.getDateTime(), -1)+"'";
		}
		return totalDao.query("from AttendanceTow where userId = ? and dateTime in ("+date+")", attendance.getUserId());
	}
	

	@Override
	public List<AccessRecords> getaccessEquipmentList(Attendance attendance) {
		// TODO Auto-generated method stub
		return totalDao.query("from AccessRecords where inId = ? and addTime like '%"+attendance.getDateTime()+"%'", attendance.getUserId());
	}
	
	/**
	 * 后同意
	 */
	public void houzongQueqin(){
		List<Attendance> attendances = totalDao.query(" from Attendance where dateTime like '%2016-12%' and attendanceStatus = '缺勤'");
		for (Attendance attendance : attendances) {
			String nowDate = attendance.getDateTime();
			String hqlAskLeave = "from AskForLeave where leaveUserCardId = ? and leavePerson = ? and (leaveStartDate like '"+nowDate+"%' or leaveEndDate like '"+nowDate+"%' or (? < leaveEndDate and leaveStartDate < ?)) and approvalStatus in ('审批中')";
			List<AskForLeave> askLeaveUp = totalDao.query(hqlAskLeave,attendance.getCardNo(), attendance.getPersonName(),nowDate, nowDate);
			if(askLeaveUp!=null&&askLeaveUp.size()>0){
				String hqlBrush = " from AttendanceTowCollect where cardId=? and dateTime=? and userId = ?";// 打卡汇总表
				AttendanceTowCollect atCollect = (AttendanceTowCollect) totalDao.getObjectByCondition(hqlBrush,attendance.getCardNo(),nowDate,attendance.getUserId());// 查询是否有打卡信息
				Integer lateTime = 0;//迟到时长
				Integer earlyTime = 0;//早退时长
				Integer qijiaTime = 0;//请假时长
				Integer jiaBTime = 0;//加班时长
				Integer queqinTime = 0;//缺勤时间
				Integer workTime = 480;//时间
				Integer wxmTime = 60;//时间
				String sbTime = "08:00:00";// 上班时间
				String wxStartTime = "12:00:00";// 午休开始时间
				String wxEndTime = "13:00:00";// 午休结束时间
				String xbTime = "17:00:00";// 下班时间
				String statuss = "";
				if (atCollect != null) {// 有刷卡记录，有汇总记录
					if (!"2".equals(attendance.getExceptTags())) {//已经手动调整过的
						attendance.setLateTime(lateTime);
						attendance.setEarlyTime(earlyTime);
						attendance.setJiaBTime(jiaBTime);
						attendance.setWorkTime(workTime);
						attendance.setWorkBiaoTime(workTime);
						attendance.setQueqinTime(queqinTime);
						attendance.setQijiaTime(qijiaTime);
						attendance.setAttendanceStatus(statuss);//当天状态
						//*// 此种情况无需重新计算迟到时间。如果请假和加班时长为1分钟，需重新计算。早退需重新计算*/
						boolean inS = false;//进门刷卡值为空 为true
						boolean outS = false;//出门刷卡值为空 为true
						boolean isnotWX = false;//是否减去午休时间
						// 进门刷卡时间
						String inTime = "";
						String[] brushIn = null;
						if (atCollect.getInTime()!=null) {
							inTime = atCollect.getInTime();
							brushIn = inTime.split(" ");
						}else {
							inS = true;
						}
						// 出门刷卡时间
						String outTime = "";
						String[] brushOut = null;
						if (atCollect.getOutTime()!=null) {
							outTime = atCollect.getOutTime();
							brushOut = outTime.split(" ");
						}else {
							outS = true;
						}
						if (!inS)
							attendance.setWorkDateTime(brushIn[0]);
						if (!outS)
							attendance.setClosingDateTime(brushOut[brushOut.length-1]);
						if (inS) {
							//进门为空的情况，准备查询请假表
								Askforless(attendance, askLeaveUp, nowDate, sbTime, xbTime, isnotWX, wxStartTime, wxEndTime, qijiaTime, workTime, wxmTime);
						}else {
							if (sbTime.substring(0, 5).compareTo(brushIn[0]) >= 0) {//第一次进门时间
								statuss = "正常";
								attendance.setAttendanceStatus(statuss);
							}else {
								//是否有请假记录
								Askforless(attendance, askLeaveUp, nowDate, sbTime, xbTime, isnotWX, wxStartTime, wxEndTime, qijiaTime, workTime, wxmTime);
							}
							if (outS) {
								//出门未刷卡，判定为异常考情
								//查询是否有请假申请
								Askforless(attendance, askLeaveUp, nowDate, sbTime, xbTime, isnotWX, wxStartTime, wxEndTime, qijiaTime, workTime, wxmTime);
							}else {
								attendance.setExceptTags(null);
								attendance.setExceptTagsNei(null);
								String lastTime = brushOut[brushOut.length - 1];//最后一次出门时间
								if (lastTime.compareTo(xbTime.substring(0, 5)) >= 0) {//最后一次出门时间 大于下班时间 状态为正常 
								}else {
									//查询是否有请假申请
									//是否有请假记录
									Askforless(attendance, askLeaveUp, nowDate, sbTime, xbTime, isnotWX, wxStartTime, wxEndTime, qijiaTime, workTime, wxmTime);
								}
							}
						}
						if ("1".equals(attendance.getExceptTags())) {}
						else attendance.setWorkTime(workTime-attendance.getLateTime()-attendance.getEarlyTime()-attendance.getQijiaTime()-attendance.getQueqinTime());
						totalDao.update(attendance);
					}
				}
				/**
				 * 无刷卡记录，有汇总记录
				 */
				else if (atCollect == null) {// 无刷卡记录，有汇总记录
					boolean isnotWX = false;//是否减去午休时间
					// 请假记录
					Askforless(attendance, askLeaveUp, nowDate, sbTime, xbTime, isnotWX, wxStartTime, wxEndTime, qijiaTime, workTime, wxmTime, queqinTime);
					totalDao.update(attendance);
				}
			}
		}
	}

	@Override
	public void sendKaoQin_3() {// 计算指定日期的考勤
		// TODO Auto-generated method stub
		String hqlSro = "from KQDate where kqDate > '2017-03-01' and kqDate < '2017-04-01' and banci_id = 7";//
		// and kqDate < '2016-08-06'
		List<KQDate> listSyo = totalDao.query(hqlSro);
		for (KQDate kQDate : listSyo) {
			String nowDate = kQDate.getKqDate();
			// String nowDate = Util.getDateTime("yyyy-MM-dd");
			// String nowDate = Util.getDateTime("2017-01-18");
			String nowTime = Util.getDateTime("HH:mm:ss");
			String nowDateTime = nowDate + " " + nowTime;
			String statuss = "";// 总状态
			String hqlBrush = " from AttendanceTowCollect where cardId=? and dateTime=? and userId = ?";// 打卡汇总表
			String hqlAttence = " from Attendance where dateTime=? and userId=? ";// 考勤汇总表
			// 查询班次
			List<BanCi> banCis = totalDao.query("from BanCi ");// where
																// firsttime >
			// ? or finishtime <
			// ? ,
			// nowTime,nowTime
			if (banCis != null && banCis.size() > 0) {
				for (BanCi banCi : banCis) {
					if (banCi.getFinishtime().compareTo(nowTime) < 0) {
						// 判断是否为上班时间
						boolean b = false;// 默认不计算
						boolean b1 = false;// 不在班次内，但特殊时间表中需要上班的日期
						boolean b2 = false;// 不在上班时间
						if (banCi == null || banCi.getFirsttime() == null
								|| banCi.getWxstarttime() == null
								|| banCi.getWxendtime() == null
								|| banCi.getFinishtime() == null)
							return;
						String sbTime = banCi.getFirsttime();// 上班时间
						String wxStartTime = banCi.getWxstarttime();// 午休开始时间
						String wxEndTime = banCi.getWxendtime();// 午休结束时间
						String xbTime = banCi.getFinishtime();// 下班时间
						Integer wxmTime = (int) (Util.getWorkTime2(wxStartTime,
								wxEndTime) / 60000);// 午休时长
						Integer workTime = (int) (Util.getWorkTime2(banCi
								.getFirsttime(), banCi.getFinishtime()) / 60000 - wxmTime);
						// 实际工作时长
						if (banCi != null && banCi.getFirsttime() != null
								&& banCi.getFinishtime() != null
								&& banCi.getWxstarttime() != null
								&& banCi.getWxendtime() != null) {
							// 查询特殊时间表
							List<SpecialDate> listSpecil = totalDao
									.query(
											"from SpecialDate where banciId = ? and date = ?",
											banCi.getId(), nowDate);
							if (listSpecil != null && listSpecil.size() > 0) {
								if ("上班".equals(listSpecil.get(0)
										.getSpecialType())) {
									addKQDate(banCi.getId());
									b = true;
									if (isbanci(banCi))// 在班次内，特殊时间表中需要上班的日期
										b1 = true;// 基本不可能
									else
										// 不在班次内的日期，特殊时间表中需要上班的日期，也汇总考勤
										b2 = true;// 查询加班表
								}
							} else {
								b = isbanci(banCi);
							}
							Integer peopleNum = 0;// 需考勤总人数
							if (b) {// 只汇总需要汇总的班次
								List<Users> userslist = totalDao
										.query(
												"from Users where banci_id = ? and onWork not in ('离职','退休','病休','内退') and dept not in('內退','病休','供应商','物业','上海庆霆不锈钢制品有限公司','昆山惠明','上海彤庆') and internal = '是' and len(cardId) = 10",
												banCi.getId());
								if (userslist != null && userslist.size() > 0) {
									peopleNum = userslist.size();
									for (Users users : userslist) {
										Integer lateTime = 0;// 迟到时长
										Integer earlyTime = 0;// 早退时长
										Integer qijiaTime = 0;// 请假时长
										Integer jiaBTime = 0;// 加班时长
										Integer queqinTime = 0;// 缺勤时间
										AttendanceTowCollect atCollect = (AttendanceTowCollect) totalDao
												.getObjectByCondition(hqlBrush,
														users.getCardId(),
														nowDate, users.getId());// 查询是否有打卡信息
										Attendance attence = (Attendance) totalDao
												.getObjectByCondition(
														hqlAttence, nowDate,
														users.getId());// 查询是否有汇总信息from
										String hqlAskLeave = "from AskForLeave where leaveUserCardId = ? and leavePerson = ? and leavePersonCode = ? and (leaveStartDate like '"
												+ nowDate
												+ "%' or leaveEndDate like '"
												+ nowDate
												+ "%' or (? < leaveEndDate and leaveStartDate < ?)) and approvalStatus in ('同意')";
										// 请假记录
										List<AskForLeave> askLeaveUp = totalDao
												.query(hqlAskLeave, users
														.getCardId(), users
														.getName(), users
														.getCode(), nowDate,
														nowDate);
										boolean askStatus = false;// 没有请假记录
										if (askLeaveUp != null
												&& askLeaveUp.size() > 0)
											askStatus = true;
										/**
										 * 既无刷卡记录，也无汇总记录
										 */
										if (atCollect == null
												&& attence == null) {// 既无刷卡记录，也无汇总记录（没有汇总过，也没刷卡，请假，旷工，未刷卡）
											boolean isnotWX = false;// 是否减去午休时间
											attence = addAttendance(users,
													nowDate, nowDateTime,
													workTime);// 得到新对象
											// 请假记录
											if (askStatus) {
												Askforless(attence, askLeaveUp,
														nowDate, sbTime,
														xbTime, isnotWX,
														wxStartTime, wxEndTime,
														qijiaTime, workTime,
														wxmTime, queqinTime);
											} else {
												statuss = "缺勤";
												attence
														.setAttendanceStatus(statuss);
												queqinTime = workTime
														- qijiaTime;
												attence
														.setQueqinTime(queqinTime);
											}
											attence.setWorkTime(workTime
													- attence.getLateTime()
													- attence.getEarlyTime()
													- attence.getQijiaTime()
													- attence.getQueqinTime());
											totalDao.save(attence);
										}
										/**
										 * 有刷卡记录，无汇总记录
										 */
										else if (atCollect != null
												&& attence == null) {// 有刷卡记录，无汇总记录
											// 获取刷卡记录
											boolean isnotWX = false;// 是否减去午休时间
											boolean inS = false;// 进门刷卡值为空 为true
											boolean outS = false;// 出门刷卡值为空
																	// 为true
											// 进门刷卡时间
											String inTime = "";
											String[] brushIn = null;
											if (atCollect.getInTime() != null) {
												inTime = atCollect.getInTime();
												brushIn = inTime.split(" ");
											} else {
												inS = true;
											}

											// 出门刷卡时间
											String outTime = "";
											String[] brushOut = null;
											if (atCollect.getOutTime() != null) {
												outTime = atCollect
														.getOutTime();
												brushOut = outTime.split(" ");
											} else {
												outS = true;
											}
											attence = addAttendance(users,
													nowDate, nowDateTime,
													workTime);
											if (!inS)
												attence
														.setWorkDateTime(brushIn[0]);
											if (!outS)
												attence
														.setClosingDateTime(brushOut[brushOut.length - 1]);
											if (inS) {
												// 进门为空的情况，准备查询请假表
												// 请假记录
												if (askStatus) {
													Askforless(attence,
															askLeaveUp,
															nowDate, sbTime,
															xbTime, isnotWX,
															wxStartTime,
															wxEndTime,
															qijiaTime,
															workTime, wxmTime);
												} else {
													statuss = "缺勤";
													attence
															.setAttendanceStatus(statuss);
													queqinTime = workTime
															- qijiaTime;
													attence
															.setQueqinTime(queqinTime);
												}
											} else {
												if (sbTime.substring(0, 5)
														.compareTo(brushIn[0]) >= 0) {// 第一次进门时间
													AttendanceTowServerImpl
															.addRechargeZhi(users);// 充值
													statuss = "正常";
													attence
															.setAttendanceStatus(statuss);
												} else {
													// 是否有请假记录
													if (askStatus) {
														Askforless(attence,
																askLeaveUp,
																nowDate,
																sbTime, xbTime,
																isnotWX,
																wxStartTime,
																wxEndTime,
																qijiaTime,
																workTime,
																wxmTime);
													} else {
														LateLess(attence,
																statuss,
																sbTime, xbTime,
																brushIn[0],
																wxStartTime,
																wxEndTime,
																lateTime,
																workTime,
																wxmTime,
																queqinTime);
													}
												}
												if (outS) {
													// 出门未刷卡，判定为异常考情
													// 是否有请假记录
													if (askStatus)
														Askforless(attence,
																askLeaveUp,
																nowDate,
																sbTime, xbTime,
																isnotWX,
																wxStartTime,
																wxEndTime,
																qijiaTime,
																workTime,
																wxmTime);
													else {
														attence
																.setExceptTags("1");
														attence.setWorkTime(0);
														attence
																.setAttendanceStatus("未刷卡");
														attence
																.setExceptTagsNei("出门时间为空");
													}
												} else {
													String lastTime = brushOut[brushOut.length - 1];// 最后一次出门时间
													if (xbTime
															.substring(0, 5)
															.compareTo(lastTime) <= 0) {// 最后一次出门时间大于等于下班时间
													} else {
														// 查询是否有请假申请
														// 是否有请假记录
														if (askStatus) {
															Askforless(
																	attence,
																	askLeaveUp,
																	nowDate,
																	sbTime,
																	xbTime,
																	isnotWX,
																	wxStartTime,
																	wxEndTime,
																	qijiaTime,
																	workTime,
																	wxmTime);
														} else {
															EarlyLess(
																	attence,
																	attence
																			.getAttendanceStatus(),
																	sbTime,
																	xbTime,
																	lastTime,
																	wxStartTime,
																	wxEndTime,
																	earlyTime,
																	workTime,
																	wxmTime,
																	queqinTime);
														}
													}
												}
											}
											if ("1".equals(attence
													.getExceptTags())) {
											} else
												attence
														.setWorkTime(workTime
																- attence
																		.getLateTime()
																- attence
																		.getEarlyTime()
																- attence
																		.getQijiaTime()
																- attence
																		.getQueqinTime());
											totalDao.save(attence);
										}
										/**
										 * 有刷卡记录，有汇总记录
										 */
										else if (atCollect != null
												&& attence != null) {// 有刷卡记录，有汇总记录
											if (!"2".equals(attence
													.getExceptTags())) {// 已经手动调整过的
												attence.setLateTime(lateTime);
												attence.setEarlyTime(earlyTime);
												attence.setJiaBTime(jiaBTime);
												attence.setWorkTime(workTime);
												attence
														.setWorkBiaoTime(workTime);
												attence
														.setQueqinTime(queqinTime);
												attence.setQijiaTime(qijiaTime);
												attence
														.setAttendanceStatus(statuss);// 当天状态
												// *//
												// 此种情况无需重新计算迟到时间。如果请假和加班时长为1分钟，需重新计算。早退需重新计算*/
												boolean inS = false;// 进门刷卡值为空
																	// 为true
												boolean outS = false;// 出门刷卡值为空
												// 为true
												boolean isnotWX = false;// 是否减去午休时间
												// 进门刷卡时间
												String inTime = "";
												String[] brushIn = null;
												if (atCollect.getInTime() != null) {
													inTime = atCollect
															.getInTime();
													brushIn = inTime.split(" ");
												} else {
													inS = true;
												}
												// 出门刷卡时间
												String outTime = "";
												String[] brushOut = null;
												if (atCollect.getOutTime() != null) {
													outTime = atCollect
															.getOutTime();
													brushOut = outTime
															.split(" ");
												} else {
													outS = true;
												}
												if (!inS)
													attence
															.setWorkDateTime(brushIn[0]);
												if (!outS)
													attence
															.setClosingDateTime(brushOut[brushOut.length - 1]);
												if (inS) {
													// 进门为空的情况，准备查询请假表
													if (askStatus) {
														Askforless(attence,
																askLeaveUp,
																nowDate,
																sbTime, xbTime,
																isnotWX,
																wxStartTime,
																wxEndTime,
																qijiaTime,
																workTime,
																wxmTime);
													} else {
														statuss = "缺勤";
														attence
																.setAttendanceStatus(statuss);
														queqinTime = workTime
																- qijiaTime;
														attence
																.setQueqinTime(queqinTime);
													}
												} else {
													if (sbTime.substring(0, 5)
															.compareTo(
																	brushIn[0]) >= 0) {// 第一次进门时间
														statuss = "正常";
														attence
																.setAttendanceStatus(statuss);
													} else {
														// 是否有请假记录
														if (askStatus) {
															Askforless(
																	attence,
																	askLeaveUp,
																	nowDate,
																	sbTime,
																	xbTime,
																	isnotWX,
																	wxStartTime,
																	wxEndTime,
																	qijiaTime,
																	workTime,
																	wxmTime);
														} else {
															LateLess(
																	attence,
																	statuss,
																	sbTime,
																	xbTime,
																	brushIn[0],
																	wxStartTime,
																	wxEndTime,
																	lateTime,
																	workTime,
																	wxmTime,
																	queqinTime);
														}
													}
													if (outS) {
														// 出门未刷卡，判定为异常考情
														// 查询是否有请假申请
														if (askStatus)
															Askforless(
																	attence,
																	askLeaveUp,
																	nowDate,
																	sbTime,
																	xbTime,
																	isnotWX,
																	wxStartTime,
																	wxEndTime,
																	qijiaTime,
																	workTime,
																	wxmTime);
														else {
															attence
																	.setExceptTags("1");
															attence
																	.setWorkTime(0);
															attence
																	.setAttendanceStatus("未刷卡");
															attence
																	.setExceptTagsNei("出门时间为空");
														}
													} else {
														attence
																.setExceptTags(null);
														attence
																.setExceptTagsNei(null);
														String lastTime = brushOut[brushOut.length - 1];// 最后一次出门时间
														if (lastTime
																.compareTo(xbTime
																		.substring(
																				0,
																				5)) >= 0) {// 最后一次出门时间
															// 大于下班时间
															// 状态为正常
														} else {
															// 查询是否有请假申请
															// 是否有请假记录
															if (askStatus) {
																Askforless(
																		attence,
																		askLeaveUp,
																		nowDate,
																		sbTime,
																		xbTime,
																		isnotWX,
																		wxStartTime,
																		wxEndTime,
																		qijiaTime,
																		workTime,
																		wxmTime);
															} else {
																EarlyLess(
																		attence,
																		attence
																				.getAttendanceStatus(),
																		sbTime,
																		xbTime,
																		lastTime,
																		wxStartTime,
																		wxEndTime,
																		earlyTime,
																		workTime,
																		wxmTime,
																		queqinTime);
															}
														}
													}
												}
												if ("1".equals(attence
														.getExceptTags())) {
												} else
													attence
															.setWorkTime(workTime
																	- attence
																			.getLateTime()
																	- attence
																			.getEarlyTime()
																	- attence
																			.getQijiaTime()
																	- attence
																			.getQueqinTime());
												totalDao.update(attence);
											}
										}
										/**
										 * 无刷卡记录，有汇总记录
										 */
										else if (atCollect == null
												&& attence != null) {// 无刷卡记录，有汇总记录
											boolean isnotWX = false;// 是否减去午休时间
											// 请假记录
											if (askStatus) {
												Askforless(attence, askLeaveUp,
														nowDate, sbTime,
														xbTime, isnotWX,
														wxStartTime, wxEndTime,
														qijiaTime, workTime,
														wxmTime, queqinTime);
											} else {
												// 判断是否存在状态
												attence.setQueqinTime(workTime);// 当天状态
												attence
														.setAttendanceStatus("缺勤");// 当天状态
												attence.setWorkTime(0);
											}
											totalDao.update(attence);
										}

										// if (zhixingCS > 0 &&
										// syoDate.equals(curDate)) {
										// } else {
										//								
										// /**
										// * 处理考勤日程表 考勤数据全部同步完成，且状态更改
										// */
										// String hqlOverAtt =
										// "from Attendance where dateTime=? and attendanceStatus!=null";
										// int countOver =
										// totalDao.getCount(hqlOverAtt,
										// syoDate);
										// // 同步完成人数等于当日考勤人总数
										// if (countOver == countRen) {
										// kqd.setSynchroTag(1);
										// kqd.setSynchroTime(Util.getDateTime());
										// kqd.setZhixingCS(kqd.getZhixingCS() +
										// 1);
										// } else if (kqd.getZhixingCS() >= 2) {
										// kqd.setSynchroTag(1);
										// kqd.setSynchroTime(Util.getDateTime());
										// kqd.setZhixingCS(kqd.getZhixingCS() +
										// 1);
										// } else {
										// kqd.setZhixingCS(kqd.getZhixingCS() +
										// 1);
										// }
										// bool = totalDao.update(kqd);
										// }
									}

									chuqinLv(nowDate, banCi);
								}
							}
							// if (b2) {
							// 查询加班申请
							// 只汇总有加班申请对影班次的用户
							// }
							// 每天都要查询加班申请
							List<Overtime> overtimes = totalDao
									.query("from Overtime where convert(varchar,endDate,120) like '"
											+ nowDate
											+ "%' and status not in ('未审批','打回')");
							if (overtimes != null && overtimes.size() > 0) {
								for (Overtime overtime : overtimes) {
									// Date startDate = overtime.getStartDate();
									// Date endDate = overtime.getEndDate();
									// Date startDate = null;
									// Date endDate = null;
									// if (overtime.getFilnalStartDate()==null)
									// startDate = overtime.getStartDate();
									// else{
									// if
									// (overtime.getStartDate().getTime()-overtime.getFilnalStartDate().getTime()>0)
									// startDate = overtime.getStartDate();
									// else
									// startDate =
									// overtime.getFilnalStartDate();
									// }
									// if (overtime.getFilnalEndDate()==null)
									// endDate = overtime.getEndDate();
									// else{
									// if
									// (overtime.getFilnalEndDate().getTime()-overtime.getEndDate().getTime()>0)
									// endDate = overtime.getEndDate();
									// else
									// endDate = overtime.getFilnalEndDate();
									// }
									Long datetime = (Util.getWorkTime1(overtime
											.getStartDate(), overtime
											.getEndDate()) / 60000);
									Integer dtime = datetime.intValue();
									// 根据加班信息查到汇总记录
									Attendance attendances = (Attendance) totalDao
											.getObjectByCondition(
													"from Attendance where userId = ? and dateTime = ?",
													overtime.getOvertimeId(),
													nowDate);
									if (attendances != null) {
										attendances.setJiaBTime(dtime);
										totalDao.update2(attendances);
									} else {
										Attendance attence = new Attendance();
										attence.setUserId(overtime
												.getOvertimeId());// 人员ID
										attence.setPersonName(overtime
												.getOvertimeName());// 人员姓名
										attence.setDeptName(overtime
												.getOvertimeDept());// 部门
										attence.setCardNo(overtime
												.getOvertimeCardId());// 人员卡号
										attence.setDateTime(nowDate);// 当天时间
										attence.setOperationDate(nowDateTime);// 当前操作时间
										attence.setTags("1");// 新算法标识
										attence.setWorkBiaoTime(workTime);
										attence.setJiaBTime(dtime);
										attence.setAttendanceStatus("加班");
										// attendances = addAttendance(overtime,
										// nowDate, nowDateTime, workTime,
										// dtime);
										totalDao.save(attence);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void huanhuiqu() {
		// TODO Auto-generated method stub
//		List<KaoQin> kaoQins = totalDao.query("from KaoQin where yuefen like '2017-01%'");
//		for (KaoQin kaoQin : kaoQins) {
//			kaoQin.setChuqintianshu(kaoQin.getChuqintianshu()+kaoQin.getLateTime()+kaoQin.getEarlyTime());
//			kaoQin.setLateTime(0f);
//			kaoQin.setEarlyTime(0f);
//			totalDao.update2(kaoQin);
//		}
	}

	@Override
	public String daoRukaoqin(File attendEx) {
		// TODO Auto-generated method stub
		String msg = "true";
		boolean flag = true;
		String fileName = "kaoqin_" + Util.getDateTime("yyyyMMddhhmmss") + ".xls";
		// 上传到服务器
		String fileRealPath = ServletActionContext.getServletContext()
				.getRealPath("/upload/file")
				+ "/" + fileName;
		File file = new File(fileRealPath);
		jxl.Workbook wk = null;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf2.format(new Date());
		String date = sdf1.format(new Date());
		int i = 0;
		try {
			FileCopyUtils.copy(attendEx, file);
			// 开始读取excle表格
			InputStream is = new FileInputStream(fileRealPath);// 创建文件流;
			if (is != null) {
				wk = Workbook.getWorkbook(is);// 创建workbook
			}

			Sheet st = wk.getSheet(0);// 获得第一张sheet表;
			int exclecolums = st.getRows();// 获得excle总行数
			if (exclecolums > 2) {
				StringBuffer sberror = new StringBuffer();
				int successcount = 0;
				int errorcount = 0;
				int error_index = 0;
				for (i = 2; i < exclecolums; i++) {
					try {
						Cell[] cells = st.getRow(i);// 获得每i行的所有单元格（返回的是数组）
						String name = cells[1].getContents();// 姓名
						String dept = cells[2].getContents();// 部门
						String cardNo = cells[3].getContents();// 卡号
						String dateTime = cells[4].getContents();// 打卡日期
						String workDateTime = cells[5].getContents();// 上班时间
						String closingDateTime = cells[6].getContents();// 下班时间
						String banci_Name = cells[7].getContents();// 班次名称
						if (error_index > 0) {
							continue;
						}
						Users users = (Users) totalDao.getObjectByCondition("from Users where name = ? and dept = ? and cardId = ?", name,dept,cardNo);
						if(users==null)
							users = (Users) totalDao.getObjectByCondition("from Users where name = ? and dept = ?", name,dept);
						if(users!=null){
							int ii = totalDao.getCount("from Attendance where userId = ? and dateTime = ?", users.getId(),dateTime);
							if(ii>0){
								Attendance attendance = (Attendance) totalDao.getObjectByCondition("from Attendance where userId = ? and dateTime = ?", users.getId(),dateTime);
								if(attendance!=null){
									attendance = updateAttendance(attendance,users,workDateTime,closingDateTime);
									//totalDao.update(attendance);
								}
							}
							BanCi banCi = null;
							if(banci_Name==null||"".equals(banci_Name)){
								if(users.getBanci_name()==null||"".equals(users.getBanci_name())){
									errorcount++;
									sberror.append("第" + (i + 1) + "行,用户"+users.getName()+"没有班次!");
									continue ;
								}else {
									banCi = (BanCi) totalDao.getObjectById(BanCi.class, users.getBanci_id());
								}
							}else{
								banCi = (BanCi) totalDao.getObjectByCondition("from BanCi where name = ?", banci_Name);
							}
							if(banCi!=null){
								//保存当日考勤记录
								//AttendanceTowCollect attendanceTowCollect = new AttendanceTowCollect();
								//汇总考勤记录
								Attendance attendance = saveAttendancedan(users,banCi,dateTime,workDateTime,closingDateTime);
								totalDao.save(attendance);
								successcount++;
							}else {
								errorcount++;
								sberror.append("第" + (i + 1) + "行,用户"+users.getName()+"填写的班次不存在!");
							}
						}else{
							errorcount++;
							sberror.append("第" + (i + 1) + "行,用户"+name+"不存在!");
						}
						
						if (i % 200 == 0) {
							totalDao.clear();
						}
					} catch (Exception e) {
						e.printStackTrace();
						sberror.append("第" + (i + 1) + "行,数据格式错误!异常:"
								+ e.getMessage());
						errorcount++;
						if (error_index == 0) {
							error_index = i + 1;
						}
						continue;
					}
				}

				is.close();// 关闭流
				wk.close();// 关闭工作薄
				String errs = "";
				if (errorcount > 0) {
					errs = "从第" + error_index + "行开始出现错误，请核对错误后，从第"
							+ error_index + "行开始重新导入(即删除excel中1-"
							+ (error_index - 1) + "行的数据)!\\n";
				}
				msg = "总条数:" + exclecolums + "\\n已成功导入" + successcount + "个,失败"
						+ errorcount + "个\\n" + errs + "失败的內容如下:\\n" + sberror;
			} else {
				msg = "没有获取到行数";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "导入失败,出现异常" + e;

		}
		return msg;
	}

	private Attendance saveAttendancedan(Users users, BanCi banCi, String dateTime, String workDateTime,
			String closingDateTime) {
		// s
		boolean inS = false;// 进门刷卡值为空 为true
		boolean outS = false;// 出门刷卡值为空 为true
		String sbTime = banCi.getFirsttime();// 上班时间
		String wxStartTime = banCi.getWxstarttime();// 午休开始时间
		String wxEndTime = banCi.getWxendtime();// 午休结束时间
		String xbTime = banCi.getFinishtime();// 下班时间
		Integer wxmTime = (int) (Util.getWorkTime2(wxStartTime,
				wxEndTime) / 60000);// 午休时长
		Integer workTime = (int) (Util.getWorkTime2(banCi
				.getFirsttime(), banCi.getFinishtime()) / 60000 - wxmTime);
		//String nowDate = Util.getDateTime("yyyy-MM-dd");
		String nowDateTime = Util.getDateTime();
		//添加汇总对象
		Attendance a = addAttendance(users, dateTime,
				nowDateTime, workTime);
		Integer lateTime = 0;// 迟到时长
		Integer earlyTime = 0;// 早退时长
		Integer qijiaTime = 0;// 请假时长
		Integer jiaBTime = 0;// 加班时长
		Integer queqinTime = 0;// 缺勤时间
		String hqlAskLeave = "from AskForLeave where leaveUserCardId = ? and leavePerson = ? and leavePersonCode = ? and (leaveStartDate like '"
			+ dateTime
			+ "%' or leaveEndDate like '"
			+ dateTime
			+ "%' or (? < leaveEndDate and leaveStartDate < ?)) and approvalStatus in ('同意')";
		if("是".equals(banCi.getIsOvernight())){
			a.setExceptTags("3");
			/***********************************************************************/
			// a.setClosingDateTime(closingDateTime);
			// 将第一次打卡时间设置为前一晚晚班下班时间
			Attendance attendance = (Attendance) totalDao
					.getObjectByCondition(
							"from Attendance where userId = ? and dateTime = ? and ExceptTags = '3'",
							users.getId(), Util.getSpecifiedDayAfter(dateTime, -1));
			if (workDateTime != null && !"".equals(workDateTime)) {// 上班时间(晚下班时间)不为空
				if (attendance != null) {// 昨天有晚班卡记录，有下班记录
					if (!"".equals(attendance.getWorkDateTime())) {
						if (attendance.getWorkDateTime().compareTo(
								xbTime.substring(0, 5)) >= 0) {// 最后一次出门时间
							// 大于下班时间 状态为正常
							attendance.setExceptTags("0");
						} else {
							// 查询是否有请假申请
							List<AskForLeave> askLeaveUp = totalDao.query(
									hqlAskLeave, users.getCardId(),
									users.getName(), users.getCode(), dateTime,
									dateTime);
							boolean askStatus = false;// 没有请假记录
							// 是否有请假记录
							if (askStatus) {
								Askforless(attendance, askLeaveUp, dateTime,
										sbTime, xbTime, true, wxStartTime,
										wxEndTime, qijiaTime, workTime, wxmTime);
								/************请假结束时间早于下班时间？*************/
							} else {
								EarlyLess(attendance, attendance
										.getAttendanceStatus(), sbTime, xbTime,
										workDateTime, wxStartTime, wxEndTime,
										earlyTime, workTime, wxmTime, queqinTime);
								attendance.setExceptTags("0");
							}
						}
					} else {
						// 是否有请假记录
						List<AskForLeave> askLeaveUp = totalDao.query(hqlAskLeave,
								users.getCardId(), users.getName(),
								users.getCode(), dateTime, dateTime);
						boolean askStatus = false;// 没有请假记录
						if (askLeaveUp != null && askLeaveUp.size() > 0)
							askStatus = true;
						if (askStatus) {
							Askforless(attendance, askLeaveUp, dateTime, sbTime,
									xbTime, true, wxStartTime, wxEndTime,
									qijiaTime, workTime, wxmTime);
							if (attendance.getQijiaTime() > 0) {
								attendance.setExceptTags("0");
								attendance.setQueqinTime(workTime
										- attendance.getQijiaTime()
										- attendance.getLateTime());
							}else {
								attendance.setExceptTags("1");
								attendance.setAttendanceStatus("未刷卡");
								attendance.setQueqinTime(workTime);
								attendance.setWorkTime(workTime
										- attendance.getLateTime()
										- attendance.getEarlyTime()
										- attendance.getQijiaTime()
										- attendance.getQueqinTime());
							}
						} else {
							attendance.setExceptTags("1");
							attendance.setAttendanceStatus("未刷卡");
							attendance.setQueqinTime(workTime);
							attendance.setWorkTime(workTime
									- attendance.getLateTime()
									- attendance.getEarlyTime()
									- attendance.getQijiaTime()
									- attendance.getQueqinTime());
						}
					}
					totalDao.update(attendance);
				} else {// 有下班记录，但昨天无上班卡记录，上班未刷卡
					// 添加昨天上班记录，
					Attendance aa = addAttendance(users, Util.getSpecifiedDayAfter(
							dateTime, -1), nowDateTime, workTime);
					aa.setClosingDateTime(workDateTime);
					aa.setAttendanceStatus("未刷卡");
					aa.setExceptTags("2");
					aa.setExceptTagsNei("晚班上班未刷卡|");
					aa.setQueqinTime(workTime);
					aa.setWorkTime(workTime - aa.getLateTime() - aa.getEarlyTime()
							- aa.getQijiaTime() - aa.getQueqinTime());
					totalDao.save(aa);
				}
			} else {
				if (attendance != null) {// 昨天有晚班卡记录，但是今天没有下班记录，下班未刷卡
					// 是否有请假记录
					List<AskForLeave> askLeaveUp = totalDao.query(hqlAskLeave,
							users.getCardId(), users.getName(), users.getCode(),
							dateTime, dateTime);
					boolean askStatus = false;// 没有请假记录
					if (askLeaveUp != null && askLeaveUp.size() > 0)
						askStatus = true;
					if (askStatus) {
						Askforless(attendance, askLeaveUp, dateTime, sbTime,
								xbTime, true, wxStartTime, wxEndTime, qijiaTime,
								workTime, wxmTime);
						if (attendance.getQijiaTime() > 0) {
							attendance.setQueqinTime(workTime
									- attendance.getQijiaTime()
									- attendance.getLateTime());
						}
					} else {
						attendance.setAttendanceStatus("未刷卡");
						attendance.setWorkTime(workTime - attendance.getLateTime()
								- attendance.getEarlyTime()
								- attendance.getQijiaTime()
								- attendance.getQueqinTime());
						totalDao.update(attendance);
					}
				} else {// 昨天无上班卡记录，上班未刷卡，今天没有下班记录，下班未刷卡
				}
			}
	
			if (workDateTime != null && !"".equals(workDateTime)) {// 下班时间(晚上班时间)不为空
				// 添加不为空的记录
				a.setWorkDateTime(closingDateTime);
				a.setExceptTags("3");
				// 判断是否迟到
				if (sbTime.substring(0, 5).compareTo(workDateTime) >= 0) {// 第一次进门时间
					AttendanceTowServerImpl.addRechargeZhi(users);// 充值
					a.setAttendanceStatus("正常");
				} else {
					// 是否有请假记录
					List<AskForLeave> askLeaveUp = totalDao.query(hqlAskLeave,
							users.getCardId(), users.getName(), users.getCode(),
							dateTime, dateTime);
					boolean askStatus = false;// 没有请假记录
					if (askLeaveUp != null && askLeaveUp.size() > 0)
						askStatus = true;
					if (askStatus) {
						Askforless(a, askLeaveUp, dateTime, sbTime, xbTime, true,
								wxStartTime, wxEndTime, qijiaTime, workTime,
								wxmTime);
					} else {
						LateLess(a, a.getAttendanceStatus(), sbTime, xbTime,
								workDateTime, wxStartTime, wxEndTime, lateTime,
								workTime, wxmTime, queqinTime);
					}
				}
			} else {
				// 添加为空的记录
				a.setAttendanceStatus("未刷卡");
				a.setExceptTags("3");
				a.setExceptTagsNei("晚班上班未刷卡|");
			}
		}else {
			a.setWorkDateTime(workDateTime);
			a.setClosingDateTime(closingDateTime);
			if(workDateTime!=null&&!"".equals(workDateTime)){
				if (sbTime.substring(0, 5)
						.compareTo(workDateTime) >= 0) {// 第一次进门时间
					AttendanceTowServerImpl
					.addRechargeZhi(users);// 充值
					a.setAttendanceStatus("正常");
				} else {
					// 是否有请假记录
					List<AskForLeave> askLeaveUp = totalDao
					.query(hqlAskLeave, users
							.getCardId(), users
							.getName(),
							users.getCode(), dateTime,
							dateTime);
					boolean askStatus = false;// 没有请假记录
					if (askLeaveUp != null
							&& askLeaveUp.size() > 0)
						askStatus = true;
					if (askStatus) {
						Askforless(a,
								askLeaveUp,
								dateTime, sbTime,
								xbTime, true,
								wxStartTime,
								wxEndTime,
								qijiaTime,
								workTime, wxmTime);
					} else {
						LateLess(a, a.getAttendanceStatus(),
								sbTime, xbTime,
								workDateTime,
								wxStartTime,
								wxEndTime,
								lateTime, workTime,
								wxmTime, queqinTime);
					}
				}
			}else {
				inS = true;
			}
			if(closingDateTime!=null&&!"".equals(closingDateTime)){
				if (closingDateTime
						.compareTo(xbTime
								.substring(
										0,
										5)) >= 0) {// 最后一次出门时间
					if(inS){
						a.setAttendanceStatus("缺勤");
						a.setQueqinTime(workTime);
						a.setWorkTime(workTime - a.getLateTime() - a.getEarlyTime()
								- a.getQijiaTime() - a.getQueqinTime());
					}
					// 大于下班时间
					// 状态为正常
				} else {
					// 查询是否有请假申请
					List<AskForLeave> askLeaveUp = totalDao
					.query(hqlAskLeave, users
							.getCardId(), users
							.getName(),
							users.getCode(), dateTime,
							dateTime);
					boolean askStatus = false;// 没有请假记录
					// 是否有请假记录
					if (askStatus) {
						Askforless(a,
								askLeaveUp,
								dateTime,
								sbTime,
								xbTime,
								true,
								wxStartTime,
								wxEndTime,
								qijiaTime,
								workTime,
								wxmTime);
					} else {
						if(inS){
							a.setAttendanceStatus("缺勤");
							a.setQueqinTime(workTime);
							a.setWorkTime(workTime - a.getLateTime() - a.getEarlyTime()
									- a.getQijiaTime() - a.getQueqinTime());
						}else {
							EarlyLess(
									a,
									a.getAttendanceStatus(),
									sbTime,
									xbTime,
									closingDateTime,
									wxStartTime,
									wxEndTime,
									earlyTime,
									workTime,
									wxmTime,
									queqinTime);
						}
					}
				}
			}else {
				if(inS){
					a.setAttendanceStatus("缺勤");
					a.setQueqinTime(workTime);
					a.setWorkTime(workTime - a.getLateTime() - a.getEarlyTime()
							- a.getQijiaTime() - a.getQueqinTime());
				} else {
					// 是否有请假记录
					List<AskForLeave> askLeaveUp = totalDao
					.query(hqlAskLeave, users
							.getCardId(), users
							.getName(),
							users.getCode(), dateTime,
							dateTime);
					boolean askStatus = false;// 没有请假记录
					if (askLeaveUp != null
							&& askLeaveUp.size() > 0)
						askStatus = true;
					if (askStatus) {
						Askforless(a,
								askLeaveUp,
								dateTime, sbTime,
								xbTime, true,
								wxStartTime,
								wxEndTime,
								qijiaTime,
								workTime, wxmTime);
						if(a.getQijiaTime()>0){
							a.setAttendanceStatus("请假");
							a.setQueqinTime(workTime-a.getQijiaTime()-a.getLateTime());
						}
					} else {
						a.setAttendanceStatus("未刷卡");
						a.setWorkTime(workTime - a.getLateTime() - a.getEarlyTime()
								- a.getQijiaTime() - a.getQueqinTime());
					}
				}
			}
		}
		return a;
	}

	private Attendance updateAttendance(Attendance attendance,Users users,
			String workDateTime, String closingDateTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void linshi(String s,String s1) {
		// TODO Auto-generated method stub
		List<AttendanceTowCollect> list = totalDao.query("from AttendanceTowCollect where dateTime = ? order by userId",s);
		List<AttendanceTowCollect> list1 = totalDao.query("from AttendanceTowCollect where dateTime = ? order by userId",s1);
//		if(list.contains(attendanceTowCollect)){}
		String sw = Util.getDateTime("yyyy-MM-dd HH:mm:ss:SSS");
		for (AttendanceTowCollect a : list1) {
			for (AttendanceTowCollect a1 : list) {	
				if(a.getName().equals(a1.getName())){
					a.setInTime(a1.getInTime());
					totalDao.update(a);
				}
			}
		}
		System.out.println(sw+"+"+Util.getDateTime("yyyy-MM-dd HH:mm:ss:SSS"));
	}

	@Override
	public void chongshisuanfa(String yue) {
		// TODO Auto-generated method stub
		List<Attendance> att = totalDao.query("from Attendance where dateTime like '"+yue+"%' and (workTime > 0 or attendanceStatus = '公出')");
		for (Attendance c : att) {
			Integer canTime = 0;
			if("公出".equals(c.getAttendanceStatus())){
				canTime = (c.getQijiaTime()+c.getWorkTime())/60;
			}else { 
				canTime = c.getWorkTime()/60;
			}
			
			RechargeZhi rechargeZhi = (RechargeZhi) totalDao.getObjectByCondition("from RechargeZhi where userId = ? and dateTime = ? and useWhat = '午餐费'", c.getUserId(),c.getDateTime());
			if(rechargeZhi!=null){
				rechargeZhi.setBalanceMoney(canTime+0f);
				totalDao.update(rechargeZhi);
			}else {
				String nowDateTime = Util.getDateTime();
				rechargeZhi = new RechargeZhi();
				rechargeZhi.setAddTime(nowDateTime);
				rechargeZhi.setName(c.getPersonName());
				rechargeZhi.setDept(c.getDeptName());
				rechargeZhi.setCode(c.getCode());
				rechargeZhi.setCardId(c.getCardNo());
				rechargeZhi.setUseWhat("午餐费");
				rechargeZhi.setUserId(c.getUserId());
				rechargeZhi.setBalance(1);
				rechargeZhi.setBalanceMoney(canTime+0f);
				rechargeZhi.setDateTime(c.getDateTime());
				RechargeAll all = (RechargeAll) totalDao.getObjectByCondition("from RechargeAll where userId = ?", c.getUserId());
				if (all!=null) {
					all.setUpdateTime(nowDateTime);
					all.setBalance(all.getBalance()+rechargeZhi.getBalance());
					if (totalDao.update(all))
						rechargeZhi.setAllId(all.getId());
					totalDao.save(rechargeZhi);
				}else {
					all = new RechargeAll();
					all.setAddTime(nowDateTime);
					all.setBalance(rechargeZhi.getBalance()+0f);
					all.setName(c.getPersonName());
					all.setDept(c.getDeptName());
					all.setCode(c.getCode());
					all.setCode("");
					all.setUserId(c.getUserId());
					all.setCardId(c.getCardNo());
					if (totalDao.save(all))
						rechargeZhi.setAllId(all.getId());
					totalDao.save(rechargeZhi);
				}
			}
		}
		return;
	}

	@Override
	public String addAttendances(List attenList, String equipmentId) {
		// TODO Auto-generated method stub
		if(attenList!=null){
			for (Object attendance : attenList) {
				if(attendance!=null){
//					System.out.println("考勤Id"+attendance.getAttendanceId());
					String ss = "";
					try {
						ss = JSON.toJSONString(attendance);
						ss+= "|"+attendance.getClass().getSimpleName();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DoorBangDingServerImpl.caeLogInfor(new StringBuffer().append(ss+"**"), equipmentId, "", "", "考勤测试", "", 0, "");
				}
			}
		}
		return "true";
	}

	@Override
	public void chongshisuanfa() {
		// TODO Auto-generated method stub
		//重新计算缺勤人数
		List<String> list = totalDao.query("select distinct(dateTime) from Attendance where dateTime like '2018-06%' and attendanceStatus = '缺勤'");
		for (String attendance : list) {
			AttendanceTowServerImpl.chongsuan(attendance, "缺勤");
		}
	}
	public void chongzhisuanfa() {
		// TODO Auto-generated method stub
		//重新计算人数
		AttendanceTowServerImpl.chongsuan("2018-06-11", "正常");
	}

//	@Override
//	public String addAttendances(List<Attendance> attenList, String equipmentId) {
//		// TODO Auto-generated method stub
//		return "";
//	}

	@Override
	public List<AttendanceFu> getAttenceFu(Attendance attendance) {
		// TODO Auto-generated method stub
		return totalDao.query("from AttendanceFu where attendanceId = ?", attendance.getAttendanceId());
	}

	@Override
	public String updateshenqing(Attendance attendance) {
		// TODO Auto-generated method stub
		if(attendance!=null){
			Attendance attendance2 = (Attendance) totalDao.getObjectById(Attendance.class, attendance.getAttendanceId());
			if(attendance2!=null){
				if("".equals(attendance.getWorkDateTime())||"".equals(attendance.getClosingDateTime())){
					return "开始或结束时间不能为空";
				}
				attendance2.setWorkDateTime(attendance.getWorkDateTime());
				attendance2.setClosingDateTime(attendance.getClosingDateTime());
				int i = totalDao.getCount("from Attendance where dateTime like '"+attendance2.getDateTime().substring(0, 7)+"%' and code = ? and status = '同意'", attendance2.getCode());
				if(i>0){
					String ss = "当月已同意补卡申请"+i+"次";
					if(i>=2){
						ss="<font color='red'>"+ss+"</font>";
					}
					attendance2.setMoreJiesi(ss);
				}else {
					String ss = "当月首次补卡申请";
					attendance2.setMoreJiesi(ss);
				}
				String processName = "考勤日报补卡申请";
				Integer epId = null;
				try {
					epId = CircuitRunServerImpl.createProcess(processName,
							Attendance.class, attendance2.getAttendanceId(), "status",
							"id", null, attendance2.getDeptName()
							+ "部门  " + attendance2.getPersonName()+" "+attendance2.getDateTime()
							+ " 考勤日报补卡申请，请您审批！", true, attendance2.getDeptName());
					if (epId != null && epId > 0) {
						attendance2.setEpId(epId);
						CircuitRun circuitRun = (CircuitRun) totalDao.get(
								CircuitRun.class, epId);
						if ("同意".equals(circuitRun.getAllStatus())
								&& "审批完成".equals(circuitRun.getAuditStatus())) {
							attendance2.setStatus("同意");
						} else {
							attendance2.setStatus("未审批");
						}
						if(totalDao.update(attendance2))
							return "申请成功！";
					} else {
						return "考勤日报补卡申请不存在，申请失败！";
					}
				} catch (Exception e) {
					return e.toString();
				}
			}
		}
		return "申请失败！";
	}

	@Override
	public Attendance setAttenceshow(Attendance attendance) {
		// TODO Auto-generated method stub
		if(attendance.getBanci_Id()!=null){
			BanCi banCi = (BanCi) totalDao.getObjectById(BanCi.class, attendance.getBanci_Id());
			if(banCi!=null){
//				Set<BanCiTime> banciTime = banCi.getBanCiTime();
//				String show = "";
//				for (BanCiTime banCiTime2 : banciTime) {
//					show += "第"+banCiTime2.getDuan()+"段开始："+banCiTime2.getStartTime() + " 结束："+banCiTime2.getEndTime()+" /n";
//				}
				attendance.setShow(banCi.getName()+" &nbsp;&nbsp;"+banCi.getBanCiTimeShow());
			}
		}
		return attendance;
	}

	//根据班次Id获得班次明细
	@Override
	public List<BanCiTime> getBanciTimeListByUsersId(Integer banciId) {
		if(banciId!=null) {
			List<BanCiTime> list = totalDao.query("from BanCiTime where banCi.id=?", banciId);
			return list;
		}
		return null;
	}
}
