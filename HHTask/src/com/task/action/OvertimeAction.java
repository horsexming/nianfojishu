package com.task.action;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.ObjectUtils.Null;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.OvertimeServer;
import com.task.Server.RewardPunishServer;
import com.task.Server.sys.CircuitRunServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.AttendanceTow;
import com.task.entity.Overtime;
import com.task.entity.OvertimeDetail;
import com.task.entity.RewardPunish;
import com.task.entity.Users;
import com.task.entity.system.CircuitRun;
import com.task.util.DateUtil;
import com.task.util.MKUtil;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class OvertimeAction extends ActionSupport {

	private Integer id;
	private String code;
	private String status;
	private String role;// 加班jb 人事rs 审批sp
	private Overtime overtime;
	private List<Overtime> overtimeListDai;// 代理加班

	// 加班人 待提交 已提交
	private List<Overtime> overtimeListForJbForDtj;
	private List<Overtime> overtimeListForJbForYtj;

	// 领导审批 待审批 已审批
	private List<Overtime> overtimeListForSpForDsp;
	private List<Overtime> overtimeListForSpForYsp;

	// 人事加班审批
	private List<Overtime> overtimeListForRsForDqr;
	private List<Overtime> overtimeListForRsForYqr;

	// 查询所有的加班记录
	private List<Overtime> overtimeListForAll;

	private List<Users> usList;

	private String errorMessage;
	private String successMessage;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String tag;
	private Integer[] usersId;//代理加班人列表
	
	private String usersIds;//加班人列表
	
	private OvertimeServer overtimeServer;
	private RewardPunishServer rewardPunishServer;
	private CircuitRunServer circuitRunServer;

	private String startDate;// 开始日期
	private String endDate;// 结束日期
	private Integer xiuxi;// 中途休息时长
	private List<AttendanceTow> attendanceTowList;
	
	private List<OvertimeDetail> overtimeDetailList;
	private OvertimeDetail overtimeDetail;
	private List<OvertimeDetail> overtimeDetailList2;

	public String toOverTime() {
		return "hr_overtime_addApply";
	}

	public String showOverTimeshi() {
		errorMessage = overtimeServer.systemOverTime(tag);
		if ("true".equals(errorMessage)) {
			url = "overtimeAction!findOvertimeListForAll.action";
		}
		return "error";
	}

	// 提交审核
	public String submitOvertime() {
		// Users loginUser = Util.getLoginUser();
		// Integer id = loginUser.getId();
		// String dept = loginUser.getDept();

		Overtime overtime = this.overtimeServer.getOvertimeById(this.overtime
				.getId());
		// String workFlowMark = overtime.getOvertimeDept() + "加班审批流程";// 流程标记
		String workFlowMark = "加班审批流程";// 流程标记
		try {
			Integer epId = CircuitRunServerImpl.createProcess(workFlowMark,
					Overtime.class, overtime.getId(), "status", "id", "加班审核",
					false);
			overtime.setEpId(epId);
			overtime.setStatus("审批中");
			this.overtimeServer.updateOvertime(overtime);
			return "submitOvertime_success_jb";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 批量审批
	public String shenpiBatch() {
		String idsStr = this.overtime.getParams();
		String status = this.overtime.getStatus();
		List<Integer> idList = new ArrayList<Integer>();
		if (idsStr != null && !"".equals(idsStr)) {
			String[] idArrStr = idsStr.split(",");
			for (int i = 0, len = idArrStr.length; i < len; i++) {
				String idStr = idArrStr[i];
				if (idStr != null && !"".equals(idStr)) {
					idList.add(Integer.parseInt(idStr));
				}
			}
		}
		// this.overtimeServer.shenpiBatch(idList);
		if (idList != null && !idList.isEmpty()) {
			for (int i = 0, len = idList.size(); i < len; i++) {
				Integer id = idList.get(i);
				Overtime overtime = this.overtimeServer.getOvertimeById(id);
				// 处理审批流程
				if ("ty".equals(status)) {// 同意审批
					String audit = circuitRunServer.updateExeNodeByCirId(
							overtime.getEpId(), true, "", false, null, false);
					CircuitRun circuitRun = circuitRunServer
							.findCircuitRunById(overtime.getEpId());
					// ----------------------------加入换休记录----------------------------------------
					/*
					 * if (circuitRun != null &&
					 * "同意".equals(circuitRun.getAllStatus())) { // 查询当前加班人的换休记录
					 * AnnualLeave a = overtimeServer
					 * .ByCodeAnnualLeave(overtime.getOvertimeCode()); if (a !=
					 * null) { a.setSurplus(a.getSurplus() +
					 * overtime.getOverTimeLong());
					 * overtimeServer.updateAnnualLeave(a); } else {
					 * a.setJobNum(overtime.getOvertimeCode());
					 * a.setName(overtime.getOvertimeName());
					 * a.setDept(overtime.getOvertimeDept());
					 * a.setStandardAnnualLeave(0);
					 * a.setSurplus(overtime.getOverTimeLong());
					 * a.setStatus("换休"); overtimeServer.addAnnualLeave(a); } }
					 */
					// --------------------------------------------------------------------
				} else {
					String audit = circuitRunServer.updateExeNodeByCirId(
							overtime.getEpId(), false, "", false, null, false);
				}
			}
		}

		MKUtil.writeJSON(true, "操作成功", null);
		return null;
	}

	// 添加加班记录
	public String addOvertime() {
		
		if(overtimeDetailList!=null && overtimeDetailList.size()>0) {
			Iterator<OvertimeDetail> iterator = overtimeDetailList.iterator();
			while(iterator.hasNext()) {
				OvertimeDetail next = iterator.next();
				if(next==null || next.getStartTime()==null || next.getStartTime().equals("")) {
					iterator.remove();
				}
			}
			HashSet<OvertimeDetail> hashSet = new HashSet<OvertimeDetail>();
			hashSet.addAll(overtimeDetailList);
			overtime.setOvertimeDetails(hashSet);
			
		}else {
			errorMessage = "加班的时间不能为空";
			return "hr_overtime_add";
		}
		String nowTime = Util.getDateTime();
//		if (!overtime.getStartDate().isEmpty() && !overtime.getEndDate().isEmpty()) {
//			if (!"noShi".equals(tag)&&!"overList".equals(tag)) {
//				if (Util.getWorkTime1(overtime.getStartDate(), nowTime) > 0) {
//					errorMessage = "加班申请的初始时间不能比当前时间早！";
//					return "hr_overtime_add";
//				} else {
//					if (Util.getWorkTime1(overtime.getEndDate(), overtime.getStartDate()) > 0) {
//						errorMessage = "加班结束时间不能早于开始时间！";
//						return "hr_overtime_add";
//					}
//				}
//			}
//		} else {
//			errorMessage = "加班的时间不能为空";
//			if("overList".equals(tag)){
//				return "hr_overtime_addApply";
//			}
//			return "hr_overtime_add";
//		}
		overtime.setCreateDate(nowTime);
		if ("overList".equals(tag)) {
			//代理请假
			url = "overtimeAction!findOvertimeListForAll.action?tag=dai";
		} else {
			//个人请假
			usersId = new Integer[]{overtime.getApplyId()};
			url = "overtimeAction!findAllOvertime.action?role=jb";
		}
		try {
			errorMessage = overtimeServer.addOvertimeList(overtime,usersId);
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = e.toString();
			setUrl("overtimeAction!toAddOvertime.action?tag="+tag);
		}
		return "error";
		
	}
	
	
	public String toAddOvertime() {
		return "hr_overtime_add";
	}
	
	public void checkOverTimeDetail() {
		Integer count = overtimeServer.checkOverTimeDetailByuserId(overtimeDetail, id);
		MKUtil.writeJSON(count);
	}

	/*
	 * 
	 * 安卓端加班申请接口
	 */
//	public void addOvertime1() {
//		try {
//			String nowTime = Util.getDateTime();
//			if (overtime.getStartDate() != null
//					&& overtime.getEndDate() != null) {
//				if (Util.getWorkTime1(overtime.getStartDate(), nowTime) > 0) {
//					MKUtil.writeJSON(false, "加班申请失败，加班开始时间不能早于当前时间!", "");
//				} else {
//					if (Util.getWorkTime1(overtime.getEndDate(), overtime
//							.getStartDate()) > 0) {
//						MKUtil.writeJSON(false, "加班申请失败，加班结束时间不能早于开始时间！", "");
//					}
//				}
//			} else {
//				MKUtil.writeJSON(false, "加班申请失败,请假的初始时间不能为空!", "");
//			}
//			overtime.setCreateDate(nowTime);
//			overtime.setStatus("未审批");
//			overtime.setAccessStatus("未生成");// Lc_add
//			String message = overtimeServer.addOvertime1(overtime);
//			Overtime overtime1 = overtimeServer.getOvertimeById(overtime
//					.getId());
//			if (message.equals("true")) {
//				// String workFlowMark = overtime1.getOvertimeDept() +
//				// "加班审批流程";// 流程标记
//				String workFlowMark = "加班审批流程";// 流程标记
//				Integer epId = CircuitRunServerImpl.createProcess(workFlowMark,
//						Overtime.class, overtime1.getId(), "status", "id",
//						overtime.getOvertimeDept() + "部门的"
//								+ overtime.getApplyName() + "加班审核,请您审核!", true);
//				overtime1.setEpId(epId);
//				// overtime1.setStatus("审批中");
//				overtimeServer.updateOvertime(overtime1);
//				MKUtil.writeJSON(true, "加班申请成功!", "");
//			} else {
//				MKUtil.writeJSON(false, "加班申请失败!", "");
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	// 删除加班记录
	public String deleteOvertime() {
		Overtime overtime = overtimeServer.getOvertimeById(id);
		if (overtime != null) {
			errorMessage = overtimeServer.deleteOvertime(overtime);
			if ("删除成功".equals(errorMessage)) {
				url = "overtimeAction!findAllOvertime.action?role="+role;
			}
		}
		return "error";
	}

	// 更新加班记录
	public String updateOvertime() {
//		String nowTime = Util.getDateTime();
//		Overtime overtime = overtimeServer.getOvertimeById(this.overtime
//				.getId());

		// BeanUtils.copyProperties(this.overtime, overtime,new
		// String[]{"id","status","filnalStartDate","filnalEndDate"});
//		String overtimeName = this.overtime.getOvertimeName(); // 姓名
//		Integer overtimeId = this.overtime.getOvertimeId(); // 加班人ID
//		String overtimeCode = this.overtime.getOvertimeCode(); // 工号
//		String overtimeDept = this.overtime.getOvertimeDept();// 申请人部门
//		String startDate = this.overtime.getStartDate();// 开始加班时间
//		String endDate = this.overtime.getEndDate();// 结束时间
		// Date filnalStartDate=this.overtime.getFilnalStartDate();//实际开始加班时间
		// Date filnalEndDate=this.overtime.getEndDate();//实际结束时间
//		String markId = this.overtime.getMarkId();// 加工件号
//		Integer amount = this.overtime.getAmount();// 数量
//		String status = this.overtime.getStatus(); // 状态
//		if (id != null && id != 0) {
//			// overtime.setI;
//		}
//		if (overtimeId != null && overtimeId != 0) {
//			overtime.setOvertimeId(overtimeId);
//		}
//		if (overtimeName != null && !overtimeName.equals("")) {
//			overtime.setOvertimeName(overtimeName);
//		}
//		if (overtimeCode != null && !overtimeCode.equals("")) {
//			overtime.setOvertimeCode(overtimeCode);
//		}
//		if (overtimeDept != null && !overtimeDept.equals("")) {
//			overtime.setOvertimeDept(overtimeDept);
//		}
//		if (startDate != null && !startDate.equals("")) {
//			overtime.setStartDate(startDate);
//		}
//		if (endDate != null && !endDate.equals("")) {
//			overtime.setEndDate(endDate);
//		}
//		if (markId != null && !markId.equals("")) {
//			overtime.setMarkId(markId);
//		}
//		if (amount != null && !amount.equals("")) {
//			overtime.setAmount(amount);
//		}
//		if (status != null && !status.equals("")) {
//			overtime.setStatus(status);
//		}
		overtimeServer.updateOvertime(overtime,overtimeDetailList);
		return "updaeOvertime_success_jb";
	}

	// 确认加班
//	public String confirmOvertime() {
//		Overtime overtime = overtimeServer.getOvertimeById(this.overtime
//				.getId());
//		overtime.setStatus("已确认");
//		overtimeServer.updateOvertime(overtime);
//
//		// 奖惩更新
//		RewardPunish rewardPunish = new RewardPunish();
//		rewardPunish.setUserId(overtime.getOvertimeId());
//		rewardPunish.setCode(overtime.getOvertimeCode());
//		rewardPunish.setName(overtime.getOvertimeName());
//		rewardPunish.setDept(overtime.getOvertimeDept());
//		Date startDate = Util.StringToDate(overtime.getStartDate(), null);
//		Date endDate = Util.StringToDate(overtime.getEndDate(), null);
//		rewardPunish.setDate(startDate);
//		rewardPunish.setProject(overtime.getMarkId());
//		rewardPunish.setType("加班");
//		Double money = DateUtil.getOvertimeWage(startDate, endDate, 0);
//		rewardPunish.setMoney(money);
//		rewardPunishServer.addRewardPunish(rewardPunish);
//		return "confirmOvertime_success_rs";
//	}

	// 获得修改页面
	public String getUpdatePage() {
		this.overtime = overtimeServer.getOvertimeById(this.getId());
		Integer overTimeFenzhong = (int) (overtime.getOverTimeLong() * 60);
		overtime.setOverTimeLong(Float.parseFloat(overTimeFenzhong/60+""));
		overtime.setOverTimeMinutes(Float.parseFloat(overTimeFenzhong%60+""));
		overtimeDetailList = overtimeServer.findOvertimeIdByDetail(id,status);
		if(tag!=null && tag.equals("backup")){//进入加班后补功能
			//查看打卡记录
			attendanceTowList = overtimeServer.getAttendanceTow(overtime);
			return "hr_overtime_backup";
		}
		return "getUpdatePage";
	}

	// 获得加班记录
	public Overtime getOvertimeById() {
		return overtimeServer.getOvertimeById(overtime.getId());
	}

	// 获得加班记录集合
	public String findAllOvertime() {
		// 权限管理 查看登陆人角色
		if ("jb".equals(role)) {
			findAllOvertimeForJb();
			return "hr_overtime_list_jb";
		}
		if ("rs".equals(role)) {
			Map mapRsForDQr = new HashMap();
			Object[] objectForRsForDQr = this.overtimeServer
					.findAllOvertimeForRsForDQr(mapRsForDQr);
			if (objectForRsForDQr != null && objectForRsForDQr.length > 0) {
				this.overtimeListForRsForDqr = (List<Overtime>) objectForRsForDQr[0];
			}
			Map mapForYQr = new HashMap();
			Object[] objectForRsForYQr = this.overtimeServer
					.findAllOvertimeForRsForYQr(mapForYQr, Integer
							.parseInt(cpage), pageSize);
			if (objectForRsForYQr != null && objectForRsForYQr.length > 0) {
				this.overtimeListForRsForYqr = (List<Overtime>) objectForRsForYQr[0];
				if (this.overtimeListForRsForYqr != null
						&& this.overtimeListForRsForYqr.size() > 0) {
					int count = (Integer) objectForRsForYQr[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this
							.setUrl("overtimeAction!findAllOvertime.action?role=rs");
					errorMessage = null;
				} else {
					errorMessage = "";
				}
			} else {
				errorMessage = "";
			}
			return "hr_overtime_list_rs";
		} else {
			Object[] objectForSpForDsp = this.overtimeServer
					.findAllOvertimeForSpForDsp(null);
			if (objectForSpForDsp != null && objectForSpForDsp.length > 0) {
				this.overtimeListForSpForDsp = (List<Overtime>) objectForSpForDsp[0];
			}

			Object[] objectForSpForYsp = this.overtimeServer
					.findAllOvertimeForSpForYsp(null, Integer.parseInt(cpage),
							pageSize);
			if (objectForSpForYsp != null && objectForSpForYsp.length > 0) {
				this.overtimeListForSpForYsp = (List<Overtime>) objectForSpForYsp[0];
				if (this.overtimeListForSpForYsp != null
						&& this.overtimeListForSpForYsp.size() > 0) {
					int count = (Integer) objectForSpForYsp[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this
							.setUrl("overtimeAction!findAllOvertime.action?role=sp");
					errorMessage = null;
				} else {
					errorMessage = "";
				}
			} else {
				errorMessage = "";
			}
			return "hr_overtime_list_sp";
		}
	}

	// 加班人显示页面
	public void findAllOvertimeForJb() {
		Users loginUser = Util.getLoginUser();
		Integer id = loginUser.getId();

		Map mapForDtj = new HashMap();
		mapForDtj.put("applyId", id);
		Object[] objectForDtj = overtimeServer
				.findAllOvertimeForJbForDtj(mapForDtj);
		if (objectForDtj != null && objectForDtj.length > 0) {
			overtimeListForJbForDtj = (List<Overtime>) objectForDtj[0];
		}
		Map mapForYtj = new HashMap();
		mapForYtj.put("applyId", id);
		Object[] objectForYtj = overtimeServer.findAllOvertimeForJbForYtj(
				mapForYtj, Integer.parseInt(cpage), pageSize);
		if (objectForYtj != null && objectForYtj.length > 0) {
			overtimeListForJbForYtj = (List<Overtime>) objectForYtj[0];
			if (overtimeListForJbForYtj != null
					&& overtimeListForJbForYtj.size() > 0) {
				int count = (Integer) objectForYtj[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("overtimeAction!findAllOvertime.action?role=jb");
				errorMessage = null;
			} else
				errorMessage = "";
		} else {
			errorMessage = "";
		}
	}


	/**
	 * 查询加班汇总记录
	 * 
	 * @return
	 */
	public String findOvertimeListForAll() {
		if (this.overtime != null) {
			ActionContext.getContext().getSession().put("overtimes",
					this.overtime);
		} else {
			this.overtime = (Overtime) ActionContext.getContext().getSession()
					.get("overtimes");
		}
		Object[] objectForAll = overtimeServer.findOvertimeListForAll(
				startDate, endDate, this.overtime, Integer.parseInt(cpage),
				pageSize, tag);
		if (objectForAll != null && objectForAll.length > 0) {
			this.overtimeListForAll = (List<Overtime>) objectForAll[0];
			if (this.overtimeListForAll != null
					&& this.overtimeListForAll.size() > 0) {
				int count = (Integer) objectForAll[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("overtimeAction!findOvertimeListForAll.action?tag="
						+ tag);
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "hr_overtime_list_all";
	}

	public String exportExcelOvertimeListForAll() {
		List<Overtime> overtimeList = this.overtimeServer
				.exportExcelOvertimeListForAll(this.overtime);

		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(StrutsStatics.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("加班记录".getBytes("GB2312"), "8859_1") + ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("加班记录", 0);
			ws.setColumnView(1, 20);
			ws.setColumnView(2, 20);
			ws.setColumnView(3, 20);
			ws.setColumnView(4, 20);
			ws.setColumnView(5, 20);
			ws.setColumnView(6, 20);
			ws.setColumnView(7, 20);
			ws.setColumnView(8, 20);
			ws.setColumnView(9, 20);
			ws.setColumnView(10, 20);
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 14,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			wcf.setAlignment(jxl.format.Alignment.CENTRE);
			jxl.write.Label label0 = new Label(0, 0, "加班记录", wcf);
			ws.addCell(label0);
			ws.mergeCells(0, 0, 11, 0);

			wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD,
					false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
			WritableCellFormat wc = new WritableCellFormat(wf);
			wc.setAlignment(jxl.format.Alignment.CENTRE);
			ws.addCell(new jxl.write.Label(0, 1, "序号", wc));
			ws.addCell(new jxl.write.Label(1, 1, "加班人工号", wc));
			ws.addCell(new jxl.write.Label(2, 1, "加班人姓名", wc));
			ws.addCell(new jxl.write.Label(3, 1, "加工件号", wc));
			ws.addCell(new jxl.write.Label(4, 1, "数量", wc));
			ws.addCell(new jxl.write.Label(5, 1, "申请开始时间", wc));
			ws.addCell(new jxl.write.Label(6, 1, "申请结束时间", wc));
			ws.addCell(new jxl.write.Label(7, 1, "加班时长", wc));
			ws.addCell(new jxl.write.Label(8, 1, "创建时间", wc));
			ws.addCell(new jxl.write.Label(9, 1, "状态", wc));
			if (overtimeList != null) {
				for (int i = 0; i < overtimeList.size(); i++) {
					Overtime o = overtimeList.get(i);
					ws.addCell(new jxl.write.Number(0, i + 2, i + 1, wc));
					ws.addCell(new Label(1, i + 2, o.getOvertimeCode(), wc));
					ws.addCell(new Label(2, i + 2, o.getOvertimeName(), wc));
					ws.addCell(new Label(3, i + 2, o.getMarkId(), wc));
					ws.addCell(new jxl.write.Number(4, i + 2, o.getAmount()== null ? 0:o.getAmount(), wc));
					ws.addCell(new Label(5, i + 2, o.getStartDate(), wc));
					ws.addCell(new Label(6, i + 2, o.getEndDate(), wc));
					ws.addCell(new Label(7, i + 2, o.getOverTimeLong()== null ? "":o.getOverTimeLong()+"", wc));
					ws.addCell(new Label(8, i + 2, o.getCreateDate(), wc));
					ws.addCell(new Label(9, i + 2, o.getStatus(), wc));
				}
			}
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 查询所有部门为页面Select使用
	public String finAllMarkIdForSetlect() {
		String message = overtimeServer.finAllMarkIdForSetlect();
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(message);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void finAllMarkIdForSetlectAll() {
		Integer pageNo = 0;
		Integer pageSize = 0;
		if(tag!=null && tag.equals("fenye")) {
			pageNo = Integer.parseInt(cpage);
			pageSize = this.pageSize;
		}
		overtimeListForJbForDtj = overtimeServer.finAllMarkIdForSetlectAll(pageNo,pageSize);
		MKUtil.writeJSON(overtimeListForJbForDtj);
	}

	// 查询用户为页面input使用
	public String findUserByCode() {
		Users u = overtimeServer.findUserByCode(code);
		if (u != null) {
			Users u1 = new Users();
			BeanUtils.copyProperties(u, u1, new String[] { "moduleFunction",
					"worklogClass", "template" });
			u1.getPassword().setUser(null);
			MKUtil.writeJSON(true, "查询成功", u1);
		}
		return null;
	}

	/*
	 * 
	 * 安卓端查询所有部门为页面Select使用的接口
	 */
	public void finAllMarkIdForSetlect1() {
		String message = overtimeServer.finAllMarkIdForSetlect();
		MKUtil.writeJSON(true, "", message);
	}

	// 判断加班时间在不在班次内，且每次加班时长最多不能超过八个小时;
	public void isbandcisc() {
		try {
			errorMessage = overtimeServer.isbancisc(id, startDate, endDate, xiuxi);
			MKUtil.writeJSON(errorMessage);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void isbandciscList() {
		try {
			errorMessage = overtimeServer.isbancisc(usersIds, startDate, endDate, xiuxi);
			MKUtil.writeJSON(errorMessage);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 加班后补功能
	 * @return
	 */
	public String backupOvertime(){
		try {
			if(overtimeDetailList!=null && overtimeDetailList.size()>0) {
				Iterator<OvertimeDetail> iterator = overtimeDetailList.iterator();
				while(iterator.hasNext()) {
					OvertimeDetail next = iterator.next();
					if(next==null ) {
						iterator.remove();
					}
				}
				overtime.setOvertimeDetails(new HashSet<OvertimeDetail>(overtimeDetailList));
			}
			errorMessage = overtimeServer.backupOvertime(overtime);
		} catch (Exception e) {
			errorMessage = e.toString();
		}
		
		return ERROR;
	}
	
	public String findOvertimeIdByDetail() {
		overtimeDetailList = overtimeServer.findOvertimeIdByDetail(id,status);
		if(tag!=null && tag.equals("backup")) {
			overtimeDetailList2 = overtimeServer.findOvertimeIdByDetail(id,"old");
		}
		return "hr_overtime_detail";
	}
	public Overtime getOvertime() {
		return overtime;
	}

	public void setOvertime(Overtime overtime) {
		this.overtime = overtime;
	}

	public OvertimeServer getOvertimeServer() {
		return overtimeServer;
	}

	public void setOvertimeServer(OvertimeServer overtimeServer) {
		this.overtimeServer = overtimeServer;
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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Overtime> getOvertimeListForJbForDtj() {
		return overtimeListForJbForDtj;
	}

	public void setOvertimeListForJbForDtj(
			List<Overtime> overtimeListForJbForDtj) {
		this.overtimeListForJbForDtj = overtimeListForJbForDtj;
	}

	public List<Overtime> getOvertimeListForJbForYtj() {
		return overtimeListForJbForYtj;
	}

	public void setOvertimeListForJbForYtj(
			List<Overtime> overtimeListForJbForYtj) {
		this.overtimeListForJbForYtj = overtimeListForJbForYtj;
	}

	public List<Overtime> getOvertimeListForSpForDsp() {
		return overtimeListForSpForDsp;
	}

	public void setOvertimeListForSpForDsp(
			List<Overtime> overtimeListForSpForDsp) {
		this.overtimeListForSpForDsp = overtimeListForSpForDsp;
	}

	public List<Overtime> getOvertimeListForSpForYsp() {
		return overtimeListForSpForYsp;
	}

	public void setOvertimeListForSpForYsp(
			List<Overtime> overtimeListForSpForYsp) {
		this.overtimeListForSpForYsp = overtimeListForSpForYsp;
	}

	public RewardPunishServer getRewardPunishServer() {
		return rewardPunishServer;
	}

	public void setRewardPunishServer(RewardPunishServer rewardPunishServer) {
		this.rewardPunishServer = rewardPunishServer;
	}

	public List<Overtime> getOvertimeListForRsForDqr() {
		return overtimeListForRsForDqr;
	}

	public void setOvertimeListForRsForDqr(
			List<Overtime> overtimeListForRsForDqr) {
		this.overtimeListForRsForDqr = overtimeListForRsForDqr;
	}

	public List<Overtime> getOvertimeListForRsForYqr() {
		return overtimeListForRsForYqr;
	}

	public void setOvertimeListForRsForYqr(
			List<Overtime> overtimeListForRsForYqr) {
		this.overtimeListForRsForYqr = overtimeListForRsForYqr;
	}

	public List<Overtime> getOvertimeListForAll() {
		return overtimeListForAll;
	}

	public void setOvertimeListForAll(List<Overtime> overtimeListForAll) {
		this.overtimeListForAll = overtimeListForAll;
	}

	public CircuitRunServer getCircuitRunServer() {
		return circuitRunServer;
	}

	public void setCircuitRunServer(CircuitRunServer circuitRunServer) {
		this.circuitRunServer = circuitRunServer;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<Users> getUsList() {
		return usList;
	}

	public void setUsList(List<Users> usList) {
		this.usList = usList;
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

	// public void sse(){
	// overtimeServer.overc();
	// }
	public List<Overtime> getOvertimeListDai() {
		return overtimeListDai;
	}

	public void setOvertimeListDai(List<Overtime> overtimeListDai) {
		this.overtimeListDai = overtimeListDai;
	}

	public Integer[] getUsersId() {
		return usersId;
	}

	public void setUsersId(Integer[] usersId) {
		this.usersId = usersId;
	}

	public Integer getXiuxi() {
		return xiuxi;
	}

	public void setXiuxi(Integer xiuxi) {
		this.xiuxi = xiuxi;
	}

	public List<AttendanceTow> getAttendanceTowList() {
		return attendanceTowList;
	}

	public void setAttendanceTowList(List<AttendanceTow> attendanceTowList) {
		this.attendanceTowList = attendanceTowList;
	}

	public String getUsersIds() {
		return usersIds;
	}

	public void setUsersIds(String usersIds) {
		this.usersIds = usersIds;
	}

	public List<OvertimeDetail> getOvertimeDetailList() {
		return overtimeDetailList;
	}

	public void setOvertimeDetailList(List<OvertimeDetail> overtimeDetailList) {
		this.overtimeDetailList = overtimeDetailList;
	}

	public OvertimeDetail getOvertimeDetail() {
		return overtimeDetail;
	}

	public void setOvertimeDetail(OvertimeDetail overtimeDetail) {
		this.overtimeDetail = overtimeDetail;
	}

	public List<OvertimeDetail> getOvertimeDetailList2() {
		return overtimeDetailList2;
	}

	public void setOvertimeDetailList2(List<OvertimeDetail> overtimeDetailList2) {
		this.overtimeDetailList2 = overtimeDetailList2;
	}
	
}
