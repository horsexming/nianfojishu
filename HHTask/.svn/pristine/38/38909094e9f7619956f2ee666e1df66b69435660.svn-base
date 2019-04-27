package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.AnnualLeave;
import com.task.entity.AskForLeave;
import com.task.entity.QxAskForLeave;
import com.task.entity.singlecar.SingleCar;

public interface LeaveServer {
	// 保存请假
	public String  saveLeave(AskForLeave askForLeave,int deptIds[],int userIds[])throws Exception;
	
	/**
	 * 一个月累计请假天数是否达到N天
	 * @param code 员工号
	 * @param month 月份（yyyy-mm）
	 * @param outday 达标天数
	 * @return
	 */
    public boolean oneMonthOut(String code,String month,int outday);
	// 查询所有请假信息
	public List<AskForLeave> selectAll();

	// 根据Id查询请假信息
	public AskForLeave selectOne(int id);

	// 根据Id删除请假信息
	public void deleteLeave(Integer id)throws Exception;

	/***
	 * 修改请假信息
	 * 
	 * @param askForLeave
	 *            页面新的请假对象
	 * @param oldAskForLeave
	 *            数据库中老的请假对象
	 */
	public void updateLeave(AskForLeave askForLeave, AskForLeave oldAskForLeave);

	/***
	 * 按条件查询分页
	 * 
	 * @param askForLeave
	 *            实体类对象
	 * @param pageNo
	 *            每页显示条数
	 * @param pageSize
	 *            总记录数
	 * @param pageStatus
	 *            (audit：审批查询，findALL：查找全部，self：个人)
	 */
	public Object[] selectAllByLeavePage(AskForLeave askForLeave, int pageNo,
			int pageSize, String pageStatus, String startDate, String endDate);

	/***
	 * 审核查询
	 * 
	 * @param approvalId
	 *            根据Id查询数组
	 * @param status
	 *            审核状态
	 */
	public String updateLeaveApproval(Integer id, String status);

	/**
	 * 门卫待出门当天记录
	 * 
	 * @param cpage
	 *            当前页
	 * @param pageSize
	 * @return
	 */
	Object[] findExitList(Integer cpage, Integer pageSize);

	/**
	 * 出门刷卡
	 * 
	 * @param barcode
	 *            员工卡号
	 * @return
	 */
	String updateExit(String barcode);

	/**
	 * 个人打印队列
	 */
	List findPrintList();

	/**
	 * 根据ID获取请假对象
	 * 
	 * @param id
	 * @return
	 */
	AskForLeave findAskLeaveById(Integer id);

	/**
	 * 根据工号获取年休记录
	 * 
	 * @param string
	 * @param
	 * @return
	 */
	AnnualLeave BynameNianxiu(String string);

	public AnnualLeave Bynamehuanxiu(String leavePersonCode);

	public void exportExcel(AskForLeave askforleave);

	public List listhuanxiuxieyi(String nams);

	boolean saveLeave1(AskForLeave askForLeave);
	//核对时间
	public String checkTime(String leaveStartDate, String leaveEndDate,
			String leavePersonCode,Integer LeaveId,String tag);
	/**
	 * 显示个人借车记录
	 * @param singleCar
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findSingleCarsByCondition(SingleCar singleCar,
			int parseInt, int pageSize,String pageStatus);
	/**
	 * 获取借车记录
	 * @param id
	 * @return
	 */
	public SingleCar getSingleCarById(int id);
	/**
	 * 归还借车
	 * @param singleCar
	 * @return
	 */
	public String backCar(SingleCar singleCar);
	/**
	 * 通过车辆信息获取请假信息
	 * @param id
	 * @return
	 */
	public AskForLeave getLeaveByCarId(int id);
	/**
	 * 获取部门负责人
	 * @param id
	 * @return
	 */
	public List getDeptUsers(int id);
	/**
	 * 获取车牌
	 * @return
	 */
	public List getCarNumber();
	/**
	 * 申请销假;
	 */
	public boolean addqxAskForLeave(QxAskForLeave qxAskForLeave)throws Exception;
	/**
	 * 根据请假Id和登录人Id 查询销假记录;
	 */
	public List<QxAskForLeave> showQxAskForLeave(Integer leaveId);
	/**
	 * 根据Id查询
	 */
	public QxAskForLeave QxAskForLeaveByid(Integer id);
	/**
	 * 删除销假记录
	 */
	boolean delQxAskForLeave(QxAskForLeave qxAskForLeave);
	
	/**
	 * 获取工作时长（单位：小时）
	 * @return
	 */
	Float banciGzTime(String  userCode);
	
	/**
	 * 根据请假开始时间和结束时间获得天数和小时数
	 * @param startTime
	 * @param endTime
	 */
	float[] computeDayAndHourByTime(String startTime,String endTime,Integer banciId)throws Exception;
	
}
