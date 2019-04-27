package com.task.action.renshi;
/**
 * 离职申请单Action
 * @author licong
 */
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.sun.jndi.toolkit.url.Uri;
import com.task.Server.renshi.DimissionLogServer;
import com.task.Server.sys.CircuitRunServer;
import com.task.entity.Contract;
import com.task.entity.Provision;
import com.task.entity.Users;
import com.task.entity.renshi.DimissionLog;
import com.task.util.Util;

public class DimissionLogAction {
	private DimissionLogServer dimissionLogServer;// 面试单服务；
	private CircuitRunServer circuitRunServer;// 审批动态服务层
	private DimissionLog dimissionLog;// 面试单对象；
	private Contract contract;// 合同对象
	private Users us;// 合同对象
	private List<DimissionLog> dimissionLogList;// 查询所有对象的集合
	private List<DimissionLog> dimissionLogcodeList;// 查询登陆人的集合
	private List<DimissionLog> dimissionLogzhuguan;// 查询主管待确认离职时间的集合
	private List<Provision> provisionlist;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private String pageStatus;// 页面状态
	private String year_term;// 本厂工作年限

	@SuppressWarnings("unchecked")
	private List list;// 集合 保存审批流程中的信息

	private String tag;// (页面标识zhuguan/code/all/dai)
	// private String tag1;//(页面标识zhuguan/code/all/dai)用于判断所有浏览还是个人浏览。
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private String tages;//离职申请类型
	private int pageSize = 15;

	public String test() {
		return "error";
	}

	// 显示登陆人部门的待确认申请单信息
	@SuppressWarnings("unchecked")
	public String showList_zhuguan() {
		tag = "zhuguan";
		if (dimissionLog != null) {
			ActionContext.getContext().getSession().put("dimissionLogzhuguan",
					dimissionLog);
		} else {// 用来保持分页时带着查询条件
			dimissionLog = (DimissionLog) ActionContext.getContext()
					.getSession().get("dimissionLogzhuguan");
		}
		Map<Integer, Object> map = dimissionLogServer
				.findDimissionLogsByZhuguanCondition(dimissionLog, Integer
						.parseInt(cpage), pageSize);
		dimissionLogzhuguan = (List<DimissionLog>) map.get(1);// 显示面试单列表
		if (dimissionLogzhuguan != null && dimissionLogzhuguan.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("dimissionLogAction_showList_zhuguan.action");
			errorMessage = null;
		} else {
			errorMessage = "没有待确认离职时间的消息,请检查后重试!";
		}
		return "dimissionLog_show_zhuguan";
	}

	// 显示登陆人的申请记录
	@SuppressWarnings("unchecked")
	public String showList_code() {
		tag = "code";
		if (dimissionLog != null) {
			ActionContext.getContext().getSession().put("dimissionLogcode",
					dimissionLog);
		} else {// 用来保持分页时带着查询条件
			dimissionLog = (DimissionLog) ActionContext.getContext()
					.getSession().get("dimissionLogcode");
		}
		Map<Integer, Object> map = dimissionLogServer
				.findDimissionLogsBycodeCondition(dimissionLog, Integer
						.parseInt(cpage), pageSize);
		dimissionLogcodeList = (List<DimissionLog>) map.get(1);// 显示面试单列表
		if (dimissionLogcodeList != null && dimissionLogcodeList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("dimissionLogAction_showList_code.action");
			errorMessage = null;
		} else {
			errorMessage = "您当前没有离职申请单记录，如有需要，请先申请!";
		}
		return "dimissionLog_show_code";
	}

	// 显示查询内容
	@SuppressWarnings("unchecked")
	public String showList() {
//		tag = "all";
		if (dimissionLog != null) {
			ActionContext.getContext().getSession().put("dimissionLog",
					dimissionLog);
		} else {// 用来保持分页时带着查询条件
			dimissionLog = (DimissionLog) ActionContext.getContext()
					.getSession().get("dimissionLog");
		}
		Map<Integer, Object> map = dimissionLogServer
				.findDimissionLogsByCondition(dimissionLog, Integer
						.parseInt(cpage), pageSize);
		dimissionLogList = (List<DimissionLog>) map.get(1);// 显示面试单列表
		if (dimissionLogList != null && dimissionLogList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("dimissionLogAction_showList.action?tag="+tag);
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "dimissionLog_show";
	}

	// 显示查询内容_待填写争议单
	@SuppressWarnings("unchecked")
	public String showList_dai() {
		if ("tongzhi".equals(tag)) {
			if (dimissionLog != null) {
				ActionContext.getContext().getSession().put(
						"dimissionLog_tongzhi", dimissionLog);
			} else {// 用来保持分页时带着查询条件
				dimissionLog = (DimissionLog) ActionContext.getContext()
						.getSession().get("dimissionLog_tongzhi");
			}
			Map<Integer, Object> map = dimissionLogServer
					.findDimissionLogs_daiByCondition(dimissionLog, Integer
							.parseInt(cpage), pageSize, tag);
			dimissionLogList = (List<DimissionLog>) map.get(1);// 显示面试单列表
			if (dimissionLogList != null && dimissionLogList.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this
						.setUrl("dimissionLogAction_showList_dai.action?tag=tongzhi");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
			return "dimissionLog_show_tongzhi";
		} else if ("xieyi".equals(tag)) {
			if (dimissionLog != null) {
				ActionContext.getContext().getSession().put(
						"dimissionLog_xieyi", dimissionLog);
			} else {// 用来保持分页时带着查询条件
				dimissionLog = (DimissionLog) ActionContext.getContext()
						.getSession().get("dimissionLog_xieyi");
			}
			Map<Integer, Object> map = dimissionLogServer
					.findDimissionLogs_daiByCondition(dimissionLog, Integer
							.parseInt(cpage), pageSize, tag);
			dimissionLogList = (List<DimissionLog>) map.get(1);// 显示面试单列表
			if (dimissionLogList != null && dimissionLogList.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("dimissionLogAction_showList_dai.action?tag=xieyi");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
			return "dimissionLog_show_xieyi";
		} else {
			tag = "dai";
			if (dimissionLog != null) {
				ActionContext.getContext().getSession().put("dimissionLog_dai",
						dimissionLog);
			} else {// 用来保持分页时带着查询条件
				dimissionLog = (DimissionLog) ActionContext.getContext()
						.getSession().get("dimissionLog_dai");
			}
			Map<Integer, Object> map = dimissionLogServer
					.findDimissionLogs_daiByCondition(dimissionLog, Integer
							.parseInt(cpage), pageSize, tag);
			dimissionLogList = (List<DimissionLog>) map.get(1);// 显示面试单列表
			if (dimissionLogList != null && dimissionLogList.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("dimissionLogAction_showList_dai.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
			return "dimissionLog_show_dai";
		}
	}

	// 跳转到添加页面
	public String toadd() {
		if(id>0){
			tages = "dai";//代理添加离职
			us = dimissionLogServer.findUsersId(id);// 获取用户信息
		}else {
			us = Util.getLoginUser();// 获取用户信息
		}
		if(us==null){
			url = "用户信息为空，请检查";
			return "error";
		}
		if (us != null) {
			contract = dimissionLogServer.getContractByusId(us.getId());
		}
		/* 计算两个日期的时间间隔 */
		long time = (new Date().getTime() - us.getJoined().getTime());
		long i = time / (365l * 24 * 60 * 60 * 1000);
		if (i == 0)
			year_term = ((time % (365l * 24 * 60 * 60 * 1000))
					/ (31l * 24 * 60 * 60 * 1000) + 1)
					+ "个月";
		else
			year_term = i
					+ "年零"
					+ ((time % (365l * 24 * 60 * 60 * 1000))
							/ (31l * 24 * 60 * 60 * 1000) + 1) + "个月";
		provisionlist = dimissionLogServer.findProvision("lzsq");
		return "dimissionLog_add";
	}

	// 添加方法
	public String add() {
		errorMessage = dimissionLogServer.addDimissionLog(dimissionLog);
		if ("true".equals(errorMessage)) {
			successMessage = "addsuccess";
			if("dai".equals(tages)){
				tag = "all";
			}
			return successM(tag);
		} else {
			return "error";
		}
	}

	// 跳转到预览打印页面
	public String toselect() {
		DimissionLog dimissionLog2 = dimissionLogServer
				.getDimissionLogById(dimissionLog.getId());
		if (dimissionLog2 != null) {
			dimissionLog = dimissionLog2;
			provisionlist = dimissionLogServer.findProvision("lzsq");
			if (id > 0) {
				list = circuitRunServer.findAllExNodeByEpId(id);// 审批节点
			}
			return "dimissionLog_select";
		} else {
			successMessage = "seleteNot";
			return successM(tag);
		}
	}

	// 跳转到修改页面的方法
	public String toupdate() {
		DimissionLog dimissionLog2 = dimissionLogServer
				.getDimissionLogById(dimissionLog.getId());
		if (dimissionLog2 != null) {
			dimissionLog = dimissionLog2;
			provisionlist = dimissionLogServer.findProvision("lzsq");
			return "dimissionLog_update";
		} else {
			successMessage = "updateNot";
			return successM(tag);
		}
	}

	// 修改方法
	public String update() {
		boolean b = dimissionLogServer.updateDimissionLog(dimissionLog, tag);
		if (b) {
			successMessage = "updatesuccess";
			dimissionLog = null;
			return successM(tag);
		} else {
			return toupdate();
		}
	}

	// 跳转到主管确认时间页面的方法
	public String toupdateZhuguan() {
		if (dimissionLog != null) {
			dimissionLog = dimissionLogServer
					.getDimissionLogById(dimissionLog.getId());
			if (dimissionLog != null) {
				provisionlist = dimissionLogServer.findProvision("lzsq");
				return "dimissionLog_zhuguan_up";
			} else {
				successMessage = "updateNot";
				return successM(tag);
			}
		}
		return "error";
	}

	// 主管确认时间方法
	public String updateZhuguan() {
		if (dimissionLog.getId() != null && dimissionLog.getId() > 0) {
			boolean b = dimissionLogServer
					.updateZhugaunDimissionLog(dimissionLog);
			if (b) {
				successMessage = "updatesuccess";
				dimissionLog = null;
				return successM(tag);
			} else {
				return toupdateZhuguan();
			}
		}
		errorMessage = "不存在该对象！查找失败！";
		return "error";
	}

	// 删除方法
	public String delete() {
		if (dimissionLog.getId() != null && dimissionLog.getId() > 0) {
			boolean b = dimissionLogServer.deleteDimissionLog(dimissionLog
					.getId());
			if (b) {
				successMessage = "deletesuccess";
				return successM(tag);
			} else {
				successMessage = "deleteNot";
				return successM(tag);
			}
		}
		errorMessage = "不存在该对象！删除失败！";
		return "error";
	}

	private String successM(String str) {
		if ("all".equals(str)) {
			return "dim_add_succ";
		} else if ("code".equals(str)) {
			return "dim_add_code_succ";
		} else if ("zhuguan".equals(str)) {
			return "dim_add_zg_succ";
		} else if ("dai".equals(str)) {
			return "dim_add_dai_succ";
		} else if ("admin".equals(str)) {
			return "dim_add_code_succ";
		} else {
			return "error";
		}
	}

	// get set 方法

	public String getSuccessMessage() {
		return successMessage;
	}

	public DimissionLogServer getDimissionLogServer() {
		return dimissionLogServer;
	}

	public void setDimissionLogServer(DimissionLogServer dimissionLogServer) {
		this.dimissionLogServer = dimissionLogServer;
	}

	public DimissionLog getDimissionLog() {
		return dimissionLog;
	}

	public void setDimissionLog(DimissionLog dimissionLog) {
		this.dimissionLog = dimissionLog;
	}

	public List<DimissionLog> getDimissionLogList() {
		return dimissionLogList;
	}

	public void setDimissionLogList(List<DimissionLog> dimissionLogList) {
		this.dimissionLogList = dimissionLogList;
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

	public List<DimissionLog> getDimissionLogcodeList() {
		return dimissionLogcodeList;
	}

	public void setDimissionLogcodeList(List<DimissionLog> dimissionLogcodeList) {
		this.dimissionLogcodeList = dimissionLogcodeList;
	}

	public List<DimissionLog> getDimissionLogzhuguan() {
		return dimissionLogzhuguan;
	}

	public void setDimissionLogzhuguan(List<DimissionLog> dimissionLogzhuguan) {
		this.dimissionLogzhuguan = dimissionLogzhuguan;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public CircuitRunServer getCircuitRunServer() {
		return circuitRunServer;
	}

	public void setCircuitRunServer(CircuitRunServer circuitRunServer) {
		this.circuitRunServer = circuitRunServer;
	}

	@SuppressWarnings("unchecked")
	public List getList() {
		return list;
	}

	@SuppressWarnings("unchecked")
	public void setList(List list) {
		this.list = list;
	}

	public String getYear_term() {
		return year_term;
	}

	public void setYear_term(String yearTerm) {
		year_term = yearTerm;
	}

	/**
	 * @return the tages
	 */
	public String getTages() {
		return tages;
	}

	/**
	 * @param tages the tages to set
	 */
	public void setTages(String tages) {
		this.tages = tages;
	}

	public Users getUs() {
		return us;
	}

	public void setUs(Users us) {
		this.us = us;
	}

	
}
