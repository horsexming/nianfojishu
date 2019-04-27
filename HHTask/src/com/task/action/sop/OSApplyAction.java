package com.task.action.sop;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.sop.OSApplyServer;
import com.task.entity.sop.OutSourcingApp;
import com.task.util.MKUtil;

public class OSApplyAction extends ActionSupport {
	private OutSourcingApp osa;
	private OSApplyServer osaServer;
	private List list;
	private Integer id;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private String pageStatus;// 页面状态
	private String tag;// 标签
	private String crudTag;// 操作标签
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	private String startDate;// 开始时间
	private String endDate;// 截止时间
	private String message;// 提示消息
	private String barcode;// 条码
	// 上传文件
	private File infor;// 外委报价信息文件
	private String inforContentType;// 文件类型
	private String inforFileName;// 文件名称
	private File machine;// 设备故维修报价文件
	private String machineContentType;// 文件类型
	private String machineFileName;// 文件名称
	private File abilityLack;// 新增设备报价文件
	private String abilityLackContentType;// 文件类型
	private String abilityLackFileName;// 文件名称
	private File othersLack;// 其他故障文件
	private String othersLackContentType;// 文件类型
	private String othersLackFileName;// 文件名称
	private String selfCode;// 人员工号
	private List listAll;
	private Integer rootId;// bom的根目录

	/*
	 * 去添加外委申报
	 */
	public void tosaveOSApp() {
		listAll = osaServer.listYuanCailiao();
		MKUtil.writeJSON(listAll);
	}

	/** 添加外委申报 **/
	// /System/SOP/OSApp/osa_add.jsp
	public String saveOSApp() {
		if (osaServer.saveOSApp(osa, infor, inforFileName, machine,
				machineFileName, abilityLack, abilityLackFileName, othersLack,
				othersLackFileName)) {
			return "saveOK";
		}
		return ERROR;
	}

	/** 添加产品基本信息 **/
	public String saveOSApp1() {
		if (osaServer.saveOSApp1(osa)) {
			return "saveOK1";
			// return "saveOK";
		}
		return ERROR;
	}

	/**
	 * 添加一整个bom的外委外购评审单
	 */
	public void saveBomOSApp() {
		if (rootId != null) {
			boolean b = osaServer.saveBomOSApp(rootId);
			if (b) {
				MKUtil.writeJSON(true, "添加外委外购评审成功！", null);
			} else {
				MKUtil.writeJSON(false, "添加外委外购评审失败！", null);
			}
		}
	}
   public void updateUnOsAppFromBom(){
	   String msg = osaServer.updateUnOsAppFromBom();
	   MKUtil.writeJSON(msg);
   }
	/** 查询外委申报 **/
	public String findOSAppList() {
		this.pageSize = 15;
		this.setUrl("osaAction!findOSAppList.action?tag=" + tag);
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != osa) {
			request.getSession().setAttribute("osa", osa);
		} else {
			osa = (OutSourcingApp) request.getSession().getAttribute("osa");
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
		Object[] obj = osaServer.findOSAppList(osa, startDate, endDate, Integer
				.parseInt(cpage), pageSize, tag);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		listAll = (List) obj[1];
		list = (List) obj[2];
		return "findOK";
	}

	/** 根据ID获取外委申报单 **/
	public String getOSAById() {
		osa = osaServer.getOSAById(id);
		if ("update".equals(crudTag)) {
			return "updateShowOSA";
		}
		if ("update1".equals(crudTag)) {
			return "updateShowOSA1";
		}
		if ("update2".equals(crudTag)) {
			return "updateShowOSA2";
		}
		if ("update3".equals(crudTag)) {
			return "updateShowOSA3";
		}
		if ("update4".equals(crudTag)) {
			return "updateShowOSA4";
		}
		if ("update5".equals(crudTag)) {
			return "updateShowOSA5";
		} else {
			return "printOSA";
		}
	}

	/** 根据ID修改产品基本信息 **/
	public String updateOSAById0() {
		osaServer.updateOSAById0(osa);
		this.successMessage = "修改成功!";
		return "updateOSAById0";
	}

	/** 根据ID修改产品周期信息 **/
	public String updateOSAById1() {
		osaServer.updateOSAById(osa);
		this.successMessage = "修改成功!";
		return "updateOSAById1";
	}

	/** 根据ID修改申报原因 **/
	public String updateOSAById2() {
		osaServer.updateOSAById2(osa, machine, machineFileName, othersLack,
				othersLackFileName);
		this.successMessage = "修改成功!";
		return "updateOSAById2";
	}

	/** 根据ID修改成本核算 **/
	public String updateOSAById3() {
		osaServer.updateOSAById3(osa, infor, inforFileName);
		this.successMessage = "修改成功!";
		return "updateOSAById3";
	}

	/** 根据ID修改自制新增成本 **/
	public String updateOSAById4() {
		osaServer.updateOSAById4(osa, abilityLack, abilityLackFileName);
		this.successMessage = "修改成功!";
		return "updateOSAById4";
	}

	/** 修改外委申报单 ***/
	public String updateOSAById() {
		if (null != osa) {
			if (osaServer.updateOSAById(osa, infor, inforFileName, machine,
					machineFileName, abilityLack, abilityLackFileName,
					othersLack, othersLackFileName)) {
				return "printOSA";
			}
		}
		return ERROR;
	}

	/***
	 * 查询所有的订单号,页面下拉选择
	 * 
	 * @return
	 */
	public String findOrderNum() {
		Object[] orderNum = osaServer.findOrderNum();
		MKUtil.writeJSON(orderNum);
		return null;
	}

	/***
	 * 查询所有的报警号,页面下拉选择
	 * 
	 * @return
	 */
	public String findMaintenance() {
		Object[] maintenanceNum = osaServer.findMaintenance();
		MKUtil.writeJSON(maintenanceNum);
		return null;
	}

	/***
	 * 计算所需工资总额
	 * 
	 * @return
	 */
	public String jsWage() {
		Object[] userAndwage = osaServer.getWage(selfCode);
		MKUtil.writeJSON(userAndwage);
		return null;
	}

	/** 导出EXCEL **/
	public String exportEXCEL() {
		// osWorkServer.explorExcel(osWork, startDate, endDate, tag);
		return null;
	}

	/** 根据属性名查找下拉列表 **/
	public String selectItem() {
		String message = osaServer.findSelectList(tag);
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

	public OutSourcingApp getOsa() {
		return osa;
	}

	public void setOsa(OutSourcingApp osa) {
		this.osa = osa;
	}

	public OSApplyServer getOsaServer() {
		return osaServer;
	}

	public void setOsaServer(OSApplyServer osaServer) {
		this.osaServer = osaServer;
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

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public File getInfor() {
		return infor;
	}

	public void setInfor(File infor) {
		this.infor = infor;
	}

	public String getInforContentType() {
		return inforContentType;
	}

	public void setInforContentType(String inforContentType) {
		this.inforContentType = inforContentType;
	}

	public String getInforFileName() {
		return inforFileName;
	}

	public void setInforFileName(String inforFileName) {
		this.inforFileName = inforFileName;
	}

	public File getMachine() {
		return machine;
	}

	public void setMachine(File machine) {
		this.machine = machine;
	}

	public String getMachineContentType() {
		return machineContentType;
	}

	public void setMachineContentType(String machineContentType) {
		this.machineContentType = machineContentType;
	}

	public String getMachineFileName() {
		return machineFileName;
	}

	public void setMachineFileName(String machineFileName) {
		this.machineFileName = machineFileName;
	}

	public File getAbilityLack() {
		return abilityLack;
	}

	public void setAbilityLack(File abilityLack) {
		this.abilityLack = abilityLack;
	}

	public String getAbilityLackContentType() {
		return abilityLackContentType;
	}

	public void setAbilityLackContentType(String abilityLackContentType) {
		this.abilityLackContentType = abilityLackContentType;
	}

	public String getAbilityLackFileName() {
		return abilityLackFileName;
	}

	public void setAbilityLackFileName(String abilityLackFileName) {
		this.abilityLackFileName = abilityLackFileName;
	}

	public File getOthersLack() {
		return othersLack;
	}

	public void setOthersLack(File othersLack) {
		this.othersLack = othersLack;
	}

	public String getOthersLackContentType() {
		return othersLackContentType;
	}

	public void setOthersLackContentType(String othersLackContentType) {
		this.othersLackContentType = othersLackContentType;
	}

	public String getOthersLackFileName() {
		return othersLackFileName;
	}

	public void setOthersLackFileName(String othersLackFileName) {
		this.othersLackFileName = othersLackFileName;
	}

	public String getCrudTag() {
		return crudTag;
	}

	public void setCrudTag(String crudTag) {
		this.crudTag = crudTag;
	}

	public String getSelfCode() {
		return selfCode;
	}

	public void setSelfCode(String selfCode) {
		this.selfCode = selfCode;
	}

	public List getListAll() {
		return listAll;
	}

	public void setListAll(List listAll) {
		this.listAll = listAll;
	}

	public Integer getRootId() {
		return rootId;
	}

	public void setRootId(Integer rootId) {
		this.rootId = rootId;
	}

}
