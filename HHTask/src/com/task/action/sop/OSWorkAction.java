package com.task.action.sop;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.sop.OSWorkServer;
import com.task.entity.sop.OutSourcingWorkList;
import com.task.util.MKUtil;

public class OSWorkAction extends ActionSupport {
	private OSWorkServer osWorkServer;
	private OutSourcingWorkList osWork;
	private List list;
	private List listJiesuan;
	private Integer[] osJiesuan;
	private Integer id;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private String pageStatus;// 页面状态
	private String tag;// 标签
	private Integer[] processIds;// 工序id
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	private String startDate;// 开始时间
	private String endDate;// 截止时间
	private String message;// 提示消息
	private String barcode;// 条码

	private int[] waigouWaiweiPlanIds;
	private String	 WaigouWaiweiPlanId;
	private String[] mrkIds;//
	/***
	 * 添加外委工序
	 */
	public String addOswGongxu() {
		list=osWorkServer.listaddOswGongxu(processIds);
		try {
			Object[] obj = osWorkServer.addOswGongxu(osWork, processIds,WaigouWaiweiPlanId);
			Boolean bool = (Boolean) obj[0];
			errorMessage = (String) obj[1];
			if (bool) {
				return "addOswGongxu";
			}
		} catch (Exception e) {
			// TODO: handle exception
			errorMessage = e.getMessage();
		}
		return ERROR;
	}
	/** 查找管理所有外委单 **/
	public String findAllOSW() {
		this.pageSize = 15;
		this.setUrl("OSWorkAction!findAllOSW.action");
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != osWork) {
			request.getSession().setAttribute("osWork", osWork);
		} else {
			osWork = (OutSourcingWorkList) request.getSession().getAttribute(
					"osWork");
		}
		if (null != startDate) {
			request.getSession().setAttribute("startDate", startDate);
		} else {
			startDate = (String) request.getSession().getAttribute("startDate");
		}
		if (null != endDate) {
			request.getSession().setAttribute("endDate", endDate);
		} else {
			endDate = (String) request.getSession().getAttribute("endDate");
		}
		Object[] obj = osWorkServer.findOSWorkList(osWork, startDate, endDate,
				Integer.parseInt(cpage), pageSize, "");
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "findOSWOK";
	}

	/** 导出EXCEL **/
	public String exportEXCEL() {
		osWorkServer.explorExcel(osWork, startDate, endDate, tag);
		return null;
	}

	/** 财务处理外委单 **/
	public String findJIezhagnList() {
		this.pageSize = 15;
		this.setUrl("OSWorkAction!findJIezhagnList.action");
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != osWork) {
			request.getSession().setAttribute("osWork", osWork);
		} else {
			osWork = (OutSourcingWorkList) request.getSession().getAttribute(
					"osWork");
		}
		if (null != startDate) {
			request.getSession().setAttribute("startDate", startDate);
		} else {
			startDate = (String) request.getSession().getAttribute("startDate");
		}
		if (null != endDate) {
			request.getSession().setAttribute("endDate", endDate);
		} else {
			endDate = (String) request.getSession().getAttribute("endDate");
		}
		Object[] obj = osWorkServer.findOSWorkList(osWork, startDate, endDate,
				Integer.parseInt(cpage), pageSize, "jiesuan");
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "caiwuJIesuanList";
	}

	/** 选择打印条目 **/

	public String findJiezhangPrint() {
		if (null != osJiesuan) {
			list = (List) (osWorkServer.JIesuan(osJiesuan)[0]);
			return "selectJIeSuanOK";
		}
		return ERROR;
	}

	/** 打印后处理对应的外委单状态 **/
	public String updateWaiweiList() {
		osWorkServer.updateJIesuan(osJiesuan);
		Map<String, String> map = new HashMap<String, String>();
		map.put("content", "");
		map.put("message", "");
		MKUtil.writeJSON(map);
		return null;
	}

	/** 根据ID获取单个外委工作单 **/
	public String getOneOSWorkById() {
		osWork = osWorkServer.getOSWorkByID(id, tag);
		list = osWorkServer.getProcessListByOswId(id);
		return "findOneOSWOK";
	}

	/** 根据条码查找外委工作单 **/
	public String getOSWorkByBarcode() {
		if (null != barcode && !"".equals(barcode)) {
			osWork = osWorkServer.getOSWorkByByBarcode(barcode, tag);
			if ("外委".equals(osWork.getStatus()) && "out".equals(tag)) {
				message = osWorkServer.updateReceiveOSW(osWork, "外委");
			} else if ("出厂".equals(osWork.getStatus()) && "in".equals(tag)) {
				message = osWorkServer.updateReceiveOSW(osWork, "出厂");
			} else if ("入厂".equals(osWork.getStatus()) && "rece".equals(tag)) {// 跳转页面，提交action
				list = osWorkServer.getProcessListByOswId(osWork.getId());
				message = "";
				// osWorkServer.updateReceiveOSW(osWork, "入厂");
			} else {
				tag = "";
				message = "该外委工作单状态为：" + osWork.getStatus() + "不能处理，请确认后再扫描";
			}
		} else {
			tag = "";
			message = "请扫描条码";
		}
		return "getOSWorkOK";
	}

	/** 删除外委工作单 **/
	public String deleteOSWwork() {
		if (osWorkServer.deleteOSW(id)) {
			return "deleteOK";
		}
		return ERROR;
	}

	/** 根据属性名查找下拉列表 **/
	public String selectItem() {
		String message = osWorkServer.findSelectList(tag);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(message);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	/** 接收外委工作单 **/

	/***
	 * 添加外委工作单
	 * 
	 * @return
	 */
	public String addOsw() {
		Object[] obj = osWorkServer.saveOsWork(osWork, processIds);
		list = osWorkServer.getProcessListByOswId(osWork.getId());
		Boolean bool = (Boolean) obj[0];
		errorMessage = (String) obj[1];
		if (bool) {
			return "OSWork_print";
		}
		return ERROR;
	}



	/***
	 * 入厂接收
	 * 
	 * @return
	 */
	public String updateReceiveOSW() {
		try {
			message = osWorkServer.updateReceiveOSW(osWork, "入厂");
		} catch (Exception e) {
			// TODO: handle exception
			message = e.getMessage();
		}
		return "getOSWorkOK";

	}

	public OSWorkServer getOsWorkServer() {
		return osWorkServer;
	}

	public void setOsWorkServer(OSWorkServer osWorkServer) {
		this.osWorkServer = osWorkServer;
	}

	public OutSourcingWorkList getOsWork() {
		return osWork;
	}

	public void setOsWork(OutSourcingWorkList osWork) {
		this.osWork = osWork;
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer[] getProcessIds() {
		return processIds;
	}

	public void setProcessIds(Integer[] processIds) {
		this.processIds = processIds;
	}

	public List getListJiesuan() {
		return listJiesuan;
	}

	public void setListJiesuan(List listJiesuan) {
		this.listJiesuan = listJiesuan;
	}

	public Integer[] getOsJiesuan() {
		return osJiesuan;
	}

	public void setOsJiesuan(Integer[] osJiesuan) {
		this.osJiesuan = osJiesuan;
	}
	public int[] getWaigouWaiweiPlanIds() {
		return waigouWaiweiPlanIds;
	}
	public void setWaigouWaiweiPlanIds(int[] waigouWaiweiPlanIds) {
		this.waigouWaiweiPlanIds = waigouWaiweiPlanIds;
	}
	public String getWaigouWaiweiPlanId() {
		return WaigouWaiweiPlanId;
	}
	public void setWaigouWaiweiPlanId(String waigouWaiweiPlanId) {
		WaigouWaiweiPlanId = waigouWaiweiPlanId;
	}

}
