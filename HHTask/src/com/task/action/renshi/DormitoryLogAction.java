package com.task.action.renshi;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.renshi.DimissionLogServer;
import com.task.Server.renshi.DormitoryLogServer;
import com.task.Server.sys.CircuitRunServer;
import com.task.entity.Contract;
import com.task.entity.Provision;
import com.task.entity.Users;
import com.task.entity.renshi.DormitoryLog;
import com.task.util.Util;

public class DormitoryLogAction {
	private DormitoryLogServer dormitoryLogServer;// 宿舍申请单服务；
	private DimissionLogServer dimissionLogServer;// 离职申请单的服务；
	private CircuitRunServer circuitRunServer;// 审批动态服务层
	private DormitoryLog dormitoryLog;// 宿舍申请单对象；
	private Contract contract;// 合同对象
	private List<Provision> provisionlist;
	private List<DormitoryLog> dormitoryLogList;
	private List<DormitoryLog> dormitoryLogcodeList;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private String pageStatus;// 页面状态

	private String tag;// (all/code)
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	private List list;// 集合 保存审批流程中的信息

	public String test() {
		return "error";
	}

	// 显示查询内容
	@SuppressWarnings("unchecked")
	public String showList() {
		tag = "all";
		if (dormitoryLog != null) {
			ActionContext.getContext().getSession().put("dormitoryLog",
					dormitoryLog);
		} else {// 用来保持分页时带着查询条件
			dormitoryLog = (DormitoryLog) ActionContext.getContext()
					.getSession().get("dormitoryLog");
		}
		Map<Integer, Object> map = dormitoryLogServer
				.findDormitoryLogsByCondition(dormitoryLog, Integer
						.parseInt(cpage), pageSize);
		dormitoryLogList = (List<DormitoryLog>) map.get(1);// 显示面试单列表
		if (dormitoryLogList != null && dormitoryLogList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("dormitoryLogAction_showList.action");
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "dormitoryLog_show";
	}

	// 显示本人查询内容
	@SuppressWarnings("unchecked")
	public String showList_code() {
		tag = "code";
		if (dormitoryLog != null) {
			ActionContext.getContext().getSession().put("dormitoryLogcode",
					dormitoryLog);
		} else {// 用来保持分页时带着查询条件
			dormitoryLog = (DormitoryLog) ActionContext.getContext()
					.getSession().get("dormitoryLogcode");
		}
		Map<Integer, Object> map = dormitoryLogServer
				.findDormitoryLogsBycodeCondition(dormitoryLog, Integer
						.parseInt(cpage), pageSize);
		dormitoryLogcodeList = (List<DormitoryLog>) map.get(1);// 显示面试单列表
		if (dormitoryLogcodeList != null && dormitoryLogcodeList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("dormitoryLogAction_showList_code.action");
			errorMessage = null;
		} else {
			errorMessage = "您当前没有添加宿舍申请单。如有需要，请添加!";
		}
		return "dormitoryLog_show_code";
	}

	// 跳转到添加页面
	public String toadd() {
		Users us = Util.getLoginUser();// 获取用户信息
		if (us != null) {
			contract = dimissionLogServer.getContractByusId(us.getId());
		}
		provisionlist = dimissionLogServer.findProvision("ddss");
		return "dormitoryLog_shenqing";
	}

	// 跳转到预览页面
	public String toselete() {
		DormitoryLog dormitoryLog2 = dormitoryLogServer
				.getDormitoryLogById(dormitoryLog.getId());
		if (dormitoryLog2 != null) {
			dormitoryLog = dormitoryLog2;
			provisionlist = dimissionLogServer.findProvision("ddss");
			if (dormitoryLog.getEpId() > 0) {
				list = circuitRunServer.findAllExNodeByEpId(dormitoryLog
						.getEpId());// 审批节点
			}
			return "dormitoryLog_selete";
		} else {
			successMessage = "seleteNot";
			return successM(tag);
		}
	}

	// 添加方法
	public String add() {
		errorMessage = dormitoryLogServer.addDormitoryLog(dormitoryLog);
		if ("true".equals(errorMessage)) {
			successMessage = "addsuccess";
			errorMessage = null;
			return successM(tag);
		} else {
			return toadd();
		}
	}

	// 跳转到修改页面的方法
	public String toupdate() {
		DormitoryLog dormitoryLog2 = dormitoryLogServer
				.getDormitoryLogById(dormitoryLog.getId());
		if (dormitoryLog2 != null) {
			dormitoryLog = dormitoryLog2;
			provisionlist = dimissionLogServer.findProvision("ddss");
			return "dormitoryLog_update";
		} else {
			successMessage = "updateNot";
			return successM(tag);
		}
	}

	// 修改方法
	public String update() {
		boolean b = dormitoryLogServer.updateDormitoryLog(dormitoryLog);
		if (b) {
			successMessage = "updatesuccess";
			dormitoryLog = null;
			return successM(tag);
		} else {
			return toupdate();
		}
	}

	// 删除方法
	public String delete() {
		boolean b = dormitoryLogServer.deleteDormitoryLog(dormitoryLog.getId());
		if (b) {
			successMessage = "deletesuccess";
			return successM(tag);
		} else {
			successMessage = "deleteNot";
			dormitoryLog = null;
			return successM(tag);
		}
	}

	private String successM(String str) {
		if ("all".equals(str)) {
			return "dor_add_succ";
		} else if ("code".equals(str)) {
			return "dor_add_succ_code";
		} else {
			return "error";
		}
	}

	// get set 方法
	public DormitoryLogServer getDormitoryLogServer() {
		return dormitoryLogServer;
	}

	public void setDormitoryLogServer(DormitoryLogServer dormitoryLogServer) {
		this.dormitoryLogServer = dormitoryLogServer;
	}

	public DormitoryLog getDormitoryLog() {
		return dormitoryLog;
	}

	public void setDormitoryLog(DormitoryLog dormitoryLog) {
		this.dormitoryLog = dormitoryLog;
	}

	public List<DormitoryLog> getDormitoryLogList() {
		return dormitoryLogList;
	}

	public void setDormitoryLogList(List<DormitoryLog> dormitoryLogList) {
		this.dormitoryLogList = dormitoryLogList;
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

	public DimissionLogServer getDimissionLogServer() {
		return dimissionLogServer;
	}

	public void setDimissionLogServer(DimissionLogServer dimissionLogServer) {
		this.dimissionLogServer = dimissionLogServer;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public List<Provision> getProvisionlist() {
		return provisionlist;
	}

	public void setProvisionlist(List<Provision> provisionlist) {
		this.provisionlist = provisionlist;
	}

	public List<DormitoryLog> getDormitoryLogcodeList() {
		return dormitoryLogcodeList;
	}

	public void setDormitoryLogcodeList(List<DormitoryLog> dormitoryLogcodeList) {
		this.dormitoryLogcodeList = dormitoryLogcodeList;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public CircuitRunServer getCircuitRunServer() {
		return circuitRunServer;
	}

	public void setCircuitRunServer(CircuitRunServer circuitRunServer) {
		this.circuitRunServer = circuitRunServer;
	}

}
