package com.task.action.sop;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.sop.FailureStServer;
import com.task.entity.sop.FailureSSOnWeek;
import com.task.entity.sop.FailureStatistics;
import com.task.entity.sop.FailureStatisticsDetail;
import com.task.util.MKUtil;
import com.task.util.Util;
import com.tast.entity.zhaobiao.Nianlilv;

/**
 * 周不合格提交量Action层
 * 
 * @author 刘培
 * 
 */
@SuppressWarnings("serial")
public class FailureStAction extends ActionSupport {

	private FailureStServer failureStServer;// Server层
	private FailureStatistics failureSt;// 对象
	private FailureSSOnWeek failureSSOnWeek;// 对象
	private List<FailureStatistics> failureStList;// 集合
	private List list;// 集合
	private List list2;// 集合
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private String pageStatus;// 页面状态

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String year;
	private String weekds;
	private Object obj;

	//
	private FailureStatistics failureStatistics;
	//
	private Integer page;
	private Integer rows;

	/*
	 * 不合格产品管理
	 */
	public String listFailureStatistics() {
		if (failureStatistics != null) {
			ActionContext.getContext().getSession().put("failureStatistics",
					failureStatistics);
		} else {
			failureStatistics = (FailureStatistics) ActionContext.getContext()
					.getSession().get("failureStatistics");
		}
		Object[] object = failureStServer.listFailureStatistics(
				failureStatistics, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List<FailureStatistics>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("FailureStAction!listFailureStatistics.action");
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "listFailureStatistics";//FailureStatistics_list.jsp
	}

	public String toUpdatefailureStatistics() {
		failureStatistics = failureStServer.byId(failureStatistics.getId());
		return "toUpdatefailureStatistics";
	}

	public String UpdatefailureStatistics() {
		failureStServer.UpdatefailureStatistics(failureStatistics);
		errorMessage = "修改成功！！！";
		return "UpdatefailureStatistics";
	}

	public String deletefailureStatistics() {
		failureStServer.deletefailureStatistics(failureStatistics);
		errorMessage = "删除成功";
		url = "FailureStAction!listFailureStatistics.action";
		return ERROR;
	}

	/***
	 * 添加周不合格提交量
	 * 
	 * @return
	 */
	public String addFailureSt() {
		errorMessage = failureStServer.addFailureStdetail(failureSt);
		return "error";
	}

	/***
	 * 查询每周一次提交不合格统计
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findAllFailure() {
		Object[] data = failureStServer.findAllFailure(id, weekds, page, rows);
		MKUtil.writeJSON(data);
		return null;
	}

	/***
	 * 不合格状态统计查询 FailureStAction!listFailureStatistics.action
	 * 
	 * @return
	 */
	public String findAllFailureSSOnWeek() {
		if (year != null) {
			weekds = year + weekds;
		} else {
			weekds = Util.getWeek1(null,"");
			year = weekds.substring(0, 5);
		}
		// 不合格状态统计查询
		List list = failureStServer.findAllFailureSSOnWeek(weekds, id,pageStatus);
		if (list != null && list.size() > 0) {
			cpage = "";
			Float oneWeekSc = 0F;//提交总数
			Float oneWeekFc = 0F;// 不合格总数
			for (int i = 0, len = list.size(); i < len; i++) {
				FailureSSOnWeek failureSSOnWeeks = (FailureSSOnWeek) list
						.get(i);
				cpage += "'" + failureSSOnWeeks.getId() + "',";// id集合
				oneWeekSc += failureSSOnWeeks.getOneWeekSc();// 提交总数
				oneWeekFc += failureSSOnWeeks.getOneWeekFc();// 不合格总数
			}
			cpage += "''";
			pageSize=oneWeekSc.intValue();
			Float pl = oneWeekFc / oneWeekSc * 100F;
			url = "" + String.format("%.2f", pl);
		} else {
			errorMessage = "本周无质量报告数据";
		}
		// int week = Util.getNowWeek();
		// 各件号不合格品数量统计 查询
		list2 = failureStServer.findAllFailureSSMarkId(weekds,pageStatus);
		return "showFailure2";
	}

	public String showScreen() {
		if (year != null) {
			weekds = year + weekds;
		} else {
			weekds = Util.getLastWeek();
		}
		// 各件号不合格品数量统计 查询
		Object[] data = failureStServer.findAllFailureSSMarkId(weekds, page,
				rows);
		MKUtil.writeJSON(data);
		return null;
	}

	/***
	 * 产品一次提交不合格品率趋势图 （PPM）
	 * 
	 * @return
	 */
	public String findAllFsMarkId() {
		// 不合格状态统计查询
		Object[] obj = failureStServer.findAllFsMarkId(null, null);
		MKUtil.writeJSON(obj);
		return "showFailure3";
	}

	/**
	 * 根据周不合格Id查询对应的不合格明细记录
	 */
	public void findFsdByweekId() {
		try {
			List fsdList = failureStServer
					.findFsdByweekId(cpage);
			MKUtil.writeJSON(fsdList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void exprot(){
		failureStServer.exprot(failureStatistics);
	}
	
	
	public FailureStServer getFailureStServer() {
		return failureStServer;
	}

	public void setFailureStServer(FailureStServer failureStServer) {
		this.failureStServer = failureStServer;
	}

	public FailureStatistics getFailureSt() {
		return failureSt;
	}

	public void setFailureSt(FailureStatistics failureSt) {
		this.failureSt = failureSt;
	}

	public List<FailureStatistics> getFailureStList() {
		return failureStList;
	}

	public void setFailureStList(List<FailureStatistics> failureStList) {
		this.failureStList = failureStList;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getWeekds() {
		return weekds;
	}

	public void setWeekds(String weekds) {
		this.weekds = weekds;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List getList2() {
		return list2;
	}

	public void setList2(List list2) {
		this.list2 = list2;
	}

	public FailureSSOnWeek getFailureSSOnWeek() {
		return failureSSOnWeek;
	}

	public void setFailureSSOnWeek(FailureSSOnWeek failureSSOnWeek) {
		this.failureSSOnWeek = failureSSOnWeek;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public FailureStatistics getFailureStatistics() {
		return failureStatistics;
	}

	public void setFailureStatistics(FailureStatistics failureStatistics) {
		this.failureStatistics = failureStatistics;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

}