package com.task.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.AtCountServer;
import com.task.Server.AttendanceServer;
import com.task.entity.AttendanceCount;
import com.task.util.Util;

/**
 * @author 刘晓霆
 * @Date 2014-04-27
 * 
 */
@SuppressWarnings("serial")
public class AttendanceCountAction extends ActionSupport {
	private AttendanceServer attendanceServer;// Server接口实现层
	private AtCountServer atCountServer;// 导入接口
	private AttendanceCount attendanceCount;// 考勤统计，实体对象
	private List<AttendanceCount> listAtCount;// 考勤统计，集合列表
	private String cardDateTime;// 打卡时间
	private String startDate;// 开始时间
	private String endDate;// 结束时间
	private String banciName;//班次名称
	private List list;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private String pageStatus;// 页面状态

	// 分页
	private String cpage = "1";// 页数从一开始
	private String total;
	private String url;// 路径
	private int pageSize = 15;// 每页显示条数
	private Integer id;
	private String ids;// 饼图接受

	// 保存考勤统计信息
	public String saveAtCount() {
		return "AttendanceCountAction_saveAtCount";
	}

	// 分页显示考勤统计列表信息
	@SuppressWarnings("unchecked")
	public String findAllAttC() {
		this.pageSize = 20;
		this.setUrl("AttendanceCountAction!findAllAttC.action");
		HttpServletRequest request = ServletActionContext.getRequest();
		if (startDate != null) {
			request.getSession().setAttribute("startDate", startDate);
		} else {
			startDate = (String) request.getSession().getAttribute("startDate");
		}
		if (endDate != null) {
			request.getSession().setAttribute("endDate", endDate);
		} else {
			endDate = (String) request.getSession().getAttribute("endDate");
		}
		if (banciName != null) {
			request.getSession().setAttribute("banciName", banciName);
		} else {
			banciName = (String) request.getSession().getAttribute("banciName");
		}
		Object[] obj = atCountServer.findAllAttC(attendanceCount, Integer
				.parseInt(cpage), pageSize, startDate, endDate, banciName);
		int count = (Integer) obj[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[0];
		return "AttendanceCountAction_selectAllAttC";//attendanceCount.jsp
	}

	/**
	 * 每日考勤状态并图
	 * 
	 * @return
	 * @throws IOException
	 */
	public String findAttenDancePieChart() throws IOException {
		StringBuilder pieStr = new StringBuilder();
		HttpServletResponse response = ServletActionContext.getResponse();// 解决FusionCharts
		// 乱码
		response.setContentType("text/html; charset='GBK'");// 解决FusionCharts
		// 乱码
		response.setCharacterEncoding("GBK");// 解决FusionCharts 乱码
		PrintWriter out = response.getWriter();
		int idd = Integer.parseInt(ids + "");
		AttendanceCount attcou = atCountServer.getAttendanceCount(idd);
		if (attcou != null) {
			pieStr
					.append("<chart caption='"
							+ Util.getLoginCompanyInfo().getName()
							+ attcou.getCardDateTime()
							+ "日的考勤状态比例图' yAxisName=\"人次\"  xAxisName=\"状态\" baseFontSize=\"15\" "
							+ "showPercentValues=\"1\" "
							+ "showValues=\"0	\" decimals=\"0\" formatNumberScale=\"0\" showPercentInToolTip=\"0\">");

			String st2 = "正常";
			String st3 = "迟到";
			String st4 = "早退";
			String st5 = "请假";
			String st7 = "缺勤";
			if (attcou.getNormalDate() > 0) {
				pieStr.append("<set label=\"" + st2 + "\" value=\""
						+ attcou.getNormalDate() + "\"/>");
			}
			if (attcou.getLateCount() > 0) {
				pieStr.append("<set label=\"" + st3 + "\" value=\""
						+ attcou.getLateCount() + "\"/>");
			}
			if (attcou.getLeaveEarlyCount() > 0) {
				pieStr.append("<set label=\"" + st4 + "\" value=\""
						+ attcou.getLeaveEarlyCount() + "\"/>");
			}
			if (attcou.getAskForLeaveCount() > 0) {
				pieStr.append("<set label=\"" + st5 + "\" value=\""
						+ attcou.getAskForLeaveCount() + "\"/>");
			}
			if (attcou.getKuangGongCount() > 0) {
				pieStr.append("<set label=\"" + st7 + "\" value=\""
						+ attcou.getKuangGongCount() + "\"/>");
			}
			pieStr.append("</chart>");
			out.print(pieStr.toString());
			out.flush();
			out.close();
		}
		return null;
	}

	public String findLineChart() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();// 解决FusionCharts
		// 乱码
		response.setContentType("text/html; charset='GBK'");// 解决FusionCharts
		// 乱码
		response.setCharacterEncoding("GBK");// 解决FusionCharts 乱码
		PrintWriter out = response.getWriter();
		List sumScoreList = atCountServer.findListAcc(startDate, endDate);// 查询当月的走势图
		StringBuilder pieStr = new StringBuilder();
		if (sumScoreList != null && sumScoreList.size() > 0) {
			int listSize = sumScoreList.size();
			AttendanceCount firstAssScore = (AttendanceCount) sumScoreList
					.get(listSize - 1);
			AttendanceCount lastAssScore = (AttendanceCount) sumScoreList
					.get(0);
			pieStr
					.append("<chart caption='"
							+ Util.getLoginCompanyInfo().getName()
							+ firstAssScore.getCardDateTime()
							+ "——"
							+ lastAssScore.getCardDateTime()
							+ "出勤率走势图' yAxisName=\"出勤率(%)\"  xAxisName=\"日期\" baseFontSize=\"15\" "
							+ "yAxisMinValue=\"80\" "
							+ "showValues=\"0	\" decimals=\"0\" formatNumberScale=\"0\">");
			for (int i = listSize; i > 0; i--) {
				AttendanceCount assScore = (AttendanceCount) sumScoreList
						.get(i - 1);
				String date = assScore.getCardDateTime();// 日期
				Float sumScore = assScore.getAttendance();// 出勤率
				pieStr.append("<set label=\"" + date + "\" value=\"" + sumScore
						+ "\"/>");
			}
		}
		pieStr.append("</chart>");
		out.print(pieStr.toString());
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 当月出勤率的曲线图
	 * 
	 * @return
	 */
	public String findAttenDanceLineChart() {

		return null;
	}

	// getter和setter方法
	public AtCountServer getAtCountServer() {
		return atCountServer;
	}

	public void setAtCountServer(AtCountServer atCountServer) {
		this.atCountServer = atCountServer;
	}

	public AttendanceCount getAttendanceCount() {
		return attendanceCount;
	}

	public void setAttendanceCount(AttendanceCount attendanceCount) {
		this.attendanceCount = attendanceCount;
	}

	public List<AttendanceCount> getListAtCount() {
		return listAtCount;
	}

	public void setListAtCount(List<AttendanceCount> listAtCount) {
		this.listAtCount = listAtCount;
	}

	public String getCardDateTime() {
		return cardDateTime;
	}

	public void setCardDateTime(String cardDateTime) {
		this.cardDateTime = cardDateTime;
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

	public AttendanceServer getAttendanceServer() {
		return attendanceServer;
	}

	public void setAttendanceServer(AttendanceServer attendanceServer) {
		this.attendanceServer = attendanceServer;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getBanciName() {
		return banciName;
	}

	public void setBanciName(String banciName) {
		this.banciName = banciName;
	}
}
