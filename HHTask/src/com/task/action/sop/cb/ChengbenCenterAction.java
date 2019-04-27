package com.task.action.sop.cb;

import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.sop.ProcardServer;
import com.task.Server.sop.cb.ProcardCBDTSever;
import com.task.entity.sop.Procard;
import com.task.entity.sop.cb.ProcardCBDT;
import com.task.util.DateUtil;

public class ChengbenCenterAction extends ActionSupport {

	private ProcardCBDTSever procardCBDTSever;// Server层
	private ProcardCBDT procardCBDT;// 对象
	private Procard procard;
	private ProcardServer procardServer;// 流水卡片对象
	private List<ProcardCBDT> procardCBDTList;// 集合
	private List<Procard> procardList;// 集合
	private List<Object> list;// 集合
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private String pageStatus;// 页面状态
	private String startDate;// 开始时间
	private String endDate;// 截止时间

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	public String showProcardPcCbDt() {
		return "";
	}

	/***************************************************************************
	 * 查询所有总成流水卡片(分页)
	 * 
	 * @param procardTemplate
	 * @return
	 */
	public String findAllProcards() {
		if (procard != null) {
			ActionContext.getContext().getSession().put("procard_cb", procard);
		} else {
			procard = (Procard) ActionContext.getContext().getSession().get(
					"procard_cb");
		}
		if (procard == null) {
			procard = new Procard();
			procard.setProcardStyle("总成"); // 只查询第一层的总成模板
		}
		if (startDate != null) {
			ActionContext.getContext().getSession().put("startDate", startDate);
		} else {
			startDate = (String) ActionContext.getContext().getSession().get(
					"startDate");
		}
		if (endDate != null) {
			ActionContext.getContext().getSession().put("endDate", endDate);
		} else {
			endDate = (String) ActionContext.getContext().getSession().get(
					"endDate");
		}
		if (startDate != null && endDate != null && !startDate.equals("")
				&& !endDate.equals("")) {
			Date starttime = DateUtil.parseDate(startDate,
					"yyyy-MM-dd HH:mm:ss");
			Date endtime = DateUtil.parseDate(endDate, "yyyy-MM-dd HH:mm:ss");
			if ((endtime.getTime() - starttime.getTime()) < 0) {
				errorMessage = "结束时间需要在起始时间之后";
				return "Procard_findProcardList";
			}
		}
		Object[] object = null;
		// 所有产品
		object = procardServer.findAllProcard(procard, Integer.parseInt(cpage),
				pageSize, startDate, endDate, pageStatus);
		if (object != null && object.length > 0) {
			procardList = (List<Procard>) object[0];
		}
		if (procardList != null && procardList.size() > 0) {
			int count = (Integer) object[1];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("ChengbenCenterAction!findAllProcards.action");
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "Procard_findProcardList";
	}

	public ProcardCBDTSever getProcardCBDTSever() {
		return procardCBDTSever;
	}

	public void setProcardCBDTSever(ProcardCBDTSever procardCBDTSever) {
		this.procardCBDTSever = procardCBDTSever;
	}

	public ProcardCBDT getProcardCBDT() {
		return procardCBDT;
	}

	public void setProcardCBDT(ProcardCBDT procardCBDT) {
		this.procardCBDT = procardCBDT;
	}

	public List<ProcardCBDT> getProcardCBDTList() {
		return procardCBDTList;
	}

	public void setProcardCBDTList(List<ProcardCBDT> procardCBDTList) {
		this.procardCBDTList = procardCBDTList;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
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

	public Procard getProcard() {
		return procard;
	}

	public void setProcard(Procard procard) {
		this.procard = procard;
	}

	public ProcardServer getProcardServer() {
		return procardServer;
	}

	public void setProcardServer(ProcardServer procardServer) {
		this.procardServer = procardServer;
	}

	public List<Procard> getProcardList() {
		return procardList;
	}

	public void setProcardList(List<Procard> procardList) {
		this.procardList = procardList;
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

}
