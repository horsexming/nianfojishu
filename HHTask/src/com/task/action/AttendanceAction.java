package com.task.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.AttendanceServer;
import com.task.ServerImpl.UniversalServerImpl;
import com.task.entity.AskForLeave;
import com.task.entity.Attendance;
import com.task.entity.AttendanceFu;
import com.task.entity.AttendanceTow;
import com.task.entity.Overtime;
import com.task.entity.UniversalType;
import com.task.entity.banci.BanCiTime;
import com.task.entity.menjin.AccessRecords;
import com.task.entity.menjin.RechargeZhi;
import com.task.util.MKUtil;

@SuppressWarnings({"serial","unchecked"})
public class AttendanceAction extends ActionSupport {
	private AttendanceServer attendanceServer;// Server接口实现层
	private Attendance attendance;// 实体类对象
	private List<AttendanceFu> attendanceFuList;// 考勤汇总附表
	private RechargeZhi rechargeZhi;
	private List<RechargeZhi> rechargeZhiList;// 列表集合
	private List<Attendance> attenList;// 列表集合
	private Date operationDate;// 操作时间
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private String pageStatus;// 页面状态
	private List list;
	private String tag;
	private List<Overtime> overtimeList;//加班申请集合 
	private List<AskForLeave> askForLeaveList;//加班申请集合 
	private List<AttendanceTow> attendanceTowList;//打卡记录集合 
	private List<AccessRecords> accessRecordstList;//进出记录集合 
	private File attendEx;
	// 分页
	private String cpage = "1";// 页数从一开始
	private String total;
	private String url;// 路径
	private int pageSize = 15;// 每页显示条数

	private String startDate;// 开始日期
	private String endDate;// 结束日期
	private String equipmentId;// 设备唯一标识
	private Integer id;//id
	private List<UniversalType> universalTypeList;//补卡类型
	private List<BanCiTime> banciTimeList;//班次时间段列表
	
	/**
	 * 接收考勤数据
	 */
	public void addAttendanceDatas(){
		errorMessage = attendanceServer.addAttendances(attenList,equipmentId);
		MKUtil.writeJSON(true, "提交成功!", null);
	}
	
	//导出充值数据
	public void daoOutChong(){
		attendanceServer.daochuExc(startDate,endDate,rechargeZhi);
	}
	//导入考勤数据
	public String daoInkaoqin(){
		errorMessage = attendanceServer.daoRukaoqin(attendEx);
		return "error";
	}
	//汇总推送19
	public String sendDapinmu(){
//		attendanceServer.sendKaoQin_2(); 
		attendanceServer.chongshisuanfa("2018-06"); 
		return "error";
	}
	//重算考勤
	public String jisuankaoqin(){
		attendanceServer.sendKaoQin_2();
//		attendanceServer.randomPunch();
		return "error";
	}
	//计算生日定时器
	public void sendbrithday(){
		attendanceServer.sendUserbrithday(); 
	}
	//之后同意的考勤
//	public void houzongQueqin(){
//		attendanceServer.houzongQueqin(); 
//	}
	//还回迟到早退时间
//	public void huanhuilai(){
//		attendanceServer.huanhuiqu();
//	}
	
	/*
	 * 查询所有充值记录
	 */
	public String showRechage(){
		rechargeZhiList = attendanceServer.showRechargeZhi(startDate,endDate,rechargeZhi);
		return "rechargeZhi_show_1";
	}
	
	/**
	 * 手动为指定日期所有不迟到的人充值
	 */
	public void jisuanChognzhi(){
		attendanceServer.jisianChongzhi("2017-04-07",null,7);
	}
	/**
	 * startDate 年月 或 日期
	 * tag 工号
	 * id 班次ID
	 * 手动为指定日期指定人不迟到的充值
	 */
	public void jisuanChognzhiCode(){
		attendanceServer.jisianChongzhi(startDate,tag,id);
	}
	
	public void saveAttendances1() {
		if(attendanceServer.sendAttendanceThree("huizong")){
		}
	}
	

	// 保存个人考勤汇总信息,处理个人汇总信息和当天出勤率(不分进出门)
	public String saveAttendances_4() {
		if(attendanceServer.saveAttendance_4(tag)){
			return "AttendanceAction_saveAttendances";
		}
		return ERROR;
	}
	
	/*
	 * 
	 * 考情日报   个人
	 */
	public String findAttenList1() {
		this.pageSize = 20;
		this.setUrl("AttendanceAction!findAttenList1.action?tag="+tag);
		HttpServletRequest request = ServletActionContext.getRequest();
		if (attendance != null) {
			request.getSession()
					.setAttribute("attendance", attendance);
		} else {
			attendance = (Attendance)request.getSession().getAttribute("attendance");
		}
		if (startDate != null) {
			request.getSession()
					.setAttribute("startDate", startDate);
		} else {
			startDate = (String)request.getSession().getAttribute("startDate");
		}
		
		if (endDate != null) {
			request.getSession()
					.setAttribute("endDate", endDate); 
		} else {
			endDate = (String)request.getSession().getAttribute("endDate");
		}
		Object[] obj = attendanceServer.selectAllByAttendancePage1(
				attendance, Integer.parseInt(cpage), pageSize, startDate,
				endDate);
		int count = (Integer) obj[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List)obj[0];
		if(null==list || list.size()<=0){
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}

		return "attendance_dept";
	}
	
	
	// 保存个人考勤汇总信息,处理个人汇总信息和当天出勤率
	public String saveAttendances() {
		if(attendanceServer.sendAttendanceThree(tag)){
			return "AttendanceAction_saveAttendances";
		}
		return ERROR;
	}

	// 分页显示考勤信息
	public String findAttenList() {
		this.pageSize = 20;
		HttpServletRequest request = ServletActionContext.getRequest();
		if (attendance != null) {
			request.getSession()
					.setAttribute("attendance", attendance);
		} else {
			attendance = (Attendance)request.getSession().getAttribute("attendance");
		}
		if (startDate != null) {
			request.getSession()
					.setAttribute("startDate", startDate);
		} else {
			startDate = (String)request.getSession().getAttribute("startDate");
		}
		
		if (endDate != null) {
			request.getSession()
					.setAttribute("endDate", endDate);
		} else {
			endDate = (String)request.getSession().getAttribute("endDate");
		}
		Object[] obj = attendanceServer.selectAllByAttendancePage(
				attendance, Integer.parseInt(cpage), pageSize, startDate,
				endDate, tag);
		int count = (Integer) obj[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setUrl("AttendanceAction!findAttenList.action?tag="+tag);
		this.setTotal(pageCount + "");
		list = (List)obj[0];
		if(null==list || list.size()<=0){
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}

		return "attendance";
	}
	/**
	 * 根据ID获取个人当日考勤对象
	 * @return
	 */
	public String getAttenctionById(){
		attendance  = attendanceServer.getAttenceById(id);
		attendanceFuList = attendanceServer.getAttenceFu(attendance);
		overtimeList  = attendanceServer.getOverTimeByAtt(attendance);
		askForLeaveList = attendanceServer.getAskForLeaveByAtt(attendance);
		attendanceTowList = attendanceServer.getAttendanceTowAtt(attendance);
		accessRecordstList = attendanceServer.getaccessEquipmentList(attendance);
		universalTypeList = UniversalServerImpl.findTypeByCategoryType("backupCardType");//补卡说明类型
		banciTimeList = attendanceServer.getBanciTimeListByUsersId(attendance.getBanci_Id());//获取打卡记录
		return "attendance_update";
	}
	/**
	 * 更新考勤状态
	 * @return
	 */
	public String updateAttenceById(){
		errorMessage = attendanceServer.updateAttenceById(attendance);
		if("更新成功".equals(errorMessage))
			return "attendance_update";
		return ERROR;
	}
	/**
	 * 更新考勤状态
	 * @return
	 */
	public String quekaShenqing(){
		attendance  = attendanceServer.getAttenceById(id);
		attendance  = attendanceServer.setAttenceshow(attendance);
		attendanceFuList = attendanceServer.getAttenceFu(attendance);
		overtimeList  = attendanceServer.getOverTimeByAtt(attendance);
		askForLeaveList = attendanceServer.getAskForLeaveByAtt(attendance);
		attendanceTowList = attendanceServer.getAttendanceTowAtt(attendance);
		accessRecordstList = attendanceServer.getaccessEquipmentList(attendance);
		return "attendance_shenqing";
	}
	/**
	 * 申请补卡
	 * @return
	 */
	public String updateShenqing(){
		try {
			errorMessage = attendanceServer.updateshenqing(attendance);
			if("申请成功！".equals(errorMessage))
				return "attendance_shenqing";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			errorMessage = e.toString();
		}
		return ERROR;
	}
	
	//导出考勤数据
	public String erporExcel(){
		attendanceServer.exportDetailExcel(attendance,startDate,
				endDate);
		return null;
	}
	//将迟到，旷工记录发送到LED上
	public String sendKaoQin(){
		boolean b=attendanceServer.sendKaoQin();
		return null;
	}
	//将迟到，旷工记录发送到LED上
	public void sendKaoQin_1(){
		attendanceServer.sendKaoQin_1();
	}
	
	// getter和setter方法
	public AttendanceServer getAttendanceServer() {
		return attendanceServer;
	}

	public void setAttendanceServer(AttendanceServer attendanceServer) {
		this.attendanceServer = attendanceServer;
	}

	public Attendance getAttendance() {
		return attendance;
	}

	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}

	public List<Attendance> getAttenList() {
		return attenList;
	}

	public void setAttenList(List<Attendance> attenList) {
		this.attenList = attenList;
	}

	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public String getCpage() {
		return cpage;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
    
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<RechargeZhi> getRechargeZhiList() {
		return rechargeZhiList;
	}

	public void setRechargeZhiList(List<RechargeZhi> rechargeZhiList) {
		this.rechargeZhiList = rechargeZhiList;
	}

	public RechargeZhi getRechargeZhi() {
		return rechargeZhi;
	}

	public void setRechargeZhi(RechargeZhi rechargeZhi) {
		this.rechargeZhi = rechargeZhi;
	}

	public List<Overtime> getOvertimeList() {
		return overtimeList;
	}

	public void setOvertimeList(List<Overtime> overtimeList) {
		this.overtimeList = overtimeList;
	}

	public List<AskForLeave> getAskForLeaveList() {
		return askForLeaveList;
	}

	public void setAskForLeaveList(List<AskForLeave> askForLeaveList) {
		this.askForLeaveList = askForLeaveList;
	}
	public List<AttendanceTow> getAttendanceTowList() {
		return attendanceTowList;
	}
	public void setAttendanceTowList(List<AttendanceTow> attendanceTowList) {
		this.attendanceTowList = attendanceTowList;
	}
	public List<AccessRecords> getAccessRecordstList() {
		return accessRecordstList;
	}
	public void setAccessRecordstList(List<AccessRecords> accessRecordstList) {
		this.accessRecordstList = accessRecordstList;
	}
	public File getAttendEx() {
		return attendEx;
	}
	public void setAttendEx(File attendEx) {
		this.attendEx = attendEx;
	}

	/**
	 * @return the attendanceFuList
	 */
	public List<AttendanceFu> getAttendanceFuList() {
		return attendanceFuList;
	}

	/**
	 * @param attendanceFuList the attendanceFuList to set
	 */
	public void setAttendanceFuList(List<AttendanceFu> attendanceFuList) {
		this.attendanceFuList = attendanceFuList;
	}

	/**
	 * @return the equipmentId
	 */
	public String getEquipmentId() {
		return equipmentId;
	}

	/**
	 * @param equipmentId the equipmentId to set
	 */
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public List<UniversalType> getUniversalTypeList() {
		return universalTypeList;
	}

	public void setUniversalTypeList(List<UniversalType> universalTypeList) {
		this.universalTypeList = universalTypeList;
	}

	public List<BanCiTime> getBanciTimeList() {
		return banciTimeList;
	}

	public void setBanciTimeList(List<BanCiTime> banciTimeList) {
		this.banciTimeList = banciTimeList;
	}
	
	
}
