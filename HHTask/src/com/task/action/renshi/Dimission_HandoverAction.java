package com.task.action.renshi;
/**
 * 离职交接单Action
 * @author licong
 */
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.renshi.DimissionLogServer;
import com.task.Server.renshi.Dimission_HandoverServer;
import com.task.Server.sys.CircuitRunServer;
import com.task.entity.renshi.DimissionLog;
import com.task.entity.renshi.Dimission_Handover;

@SuppressWarnings("unchecked")
public class Dimission_HandoverAction {
	private Dimission_HandoverServer dimission_HandoverServer;// 交接单服务；
	private DimissionLogServer dimissionLogServer;// 申请单服务
	private CircuitRunServer circuitRunServer;// 审批动态服务层
	private Dimission_Handover dimissionHandover;// 交接单对象；
	private DimissionLog dimissionLog;// 申请单对象
	private List<Dimission_Handover> dimissionHandoverList;// 查询所有交接单对象
	private List<Dimission_Handover> dimissionHandovercodeList;// 查询登录人交接单对象
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private Integer id;// id
	private String pageStatus;// 页面状态

	private List list;// 集合 保存审批流程中的信息

	private String tag;// 标识（code/all）
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	public String test() {
		return "error";
	}

	// 显示登陆人交接单
	public String showList_code() {
		tag = "code";
		if (dimissionHandover != null) {
			ActionContext.getContext().getSession().put(
					"dimissionHandovercode", dimissionHandover);
		} else {// 用来保持分页时带着查询条件
			dimissionHandover = (Dimission_Handover) ActionContext.getContext()
					.getSession().get("dimissionHandovercode");
		}
		Map<Integer, Object> map = dimission_HandoverServer
				.findDimission_HandoversBycodeCondition(dimissionHandover,
						Integer.parseInt(cpage), pageSize);
		dimissionHandovercodeList = (List<Dimission_Handover>) map.get(1);// 显示面试单列表
		if (dimissionHandovercodeList != null
				&& dimissionHandovercodeList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("dimission_HandoverAction_showList_code.action");
			errorMessage = null;
		} else {
			errorMessage = "您当前没有离职交接单，如有需要，请先添加离职申请单!";
		}
		return "dimission_Handover_show_code";
	}

	// 显示查询内容 唐
	public String showList() {
		tag = "all";
		if (dimissionHandover != null) {
			ActionContext.getContext().getSession().put("dimissionHandover",
					dimissionHandover);
		} else {// 用来保持分页时带着查询条件
			dimissionHandover = (Dimission_Handover) ActionContext.getContext()
					.getSession().get("dimissionHandover");
		}
		Map<Integer, Object> map = dimission_HandoverServer
				.findDimission_HandoversByCondition(dimissionHandover, Integer
						.parseInt(cpage), pageSize);
		dimissionHandoverList = (List<Dimission_Handover>) map.get(1);// 显示面试单列表
		if (dimissionHandoverList != null && dimissionHandoverList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("dimission_HandoverAction_showList.action");
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "dimission_Handover_show";
	}

	// 跳转到添加页面
	public String toadd() {
		return "dimission_Handover_add";
	}

	// 添加方法
	public String add() {
		boolean b = dimission_HandoverServer
				.addDimission_Handover(dimissionHandover);
		if (b) {
			successMessage = "addsuccess";
			return successM(tag);
		} else {
			errorMessage = "添加失败！";
			return toadd();
		}
	}

	// 跳转到预览打印页面
	public String toselect() {
		Dimission_Handover dimission_h2 = dimission_HandoverServer
				.getDimission_HandoverById(dimissionHandover.getId());
		if (dimission_h2 != null) {
			dimissionHandover = dimission_h2;
			dimissionLog = dimissionLogServer.getDimissionLogById(Integer
					.parseInt(dimissionHandover.getTa_dimissionLog_id()));
			if (id != null && id > 0) {
				list = circuitRunServer.findAllExNodeByEpId(id);// 审批节点
			}
			return "dimission_Handover_select";
		} else {
			successMessage = "seleteNot";
			return successM(tag);
		}
	}

	// 跳转到修改页面的方法
	public String toupdate() {
		Dimission_Handover dimission_h2 = dimission_HandoverServer
				.getDimission_HandoverById(dimissionHandover.getId());
		if (dimission_h2 != null) {
			dimissionHandover = dimission_h2;
			return "dimission_Handover_update";
		} else {
			successMessage = "upadteNet";
			return successM(tag);
		}
	}

	// 修改方法
	public String update() {
		boolean b = dimission_HandoverServer
				.updateDimission_Handover(dimissionHandover);
		if (b) {
			successMessage = "updatesuccess";
			dimissionHandover = null;
			return successM(tag);
		} else {
			return toupdate();
		}
	}

	// 删除方法
	public String delete() {
		boolean b = dimission_HandoverServer
				.deleteDimission_Handover(dimissionHandover.getId());
		if (b) {
			successMessage = "deletesuccess";
			return successM(tag);
		} else {
			successMessage = "deleteNot";
			dimissionHandover = null;
			return successM(tag);
		}
	}

	private String successM(String str) {
		if ("all".equals(str)) {
			return "dim_H_add_succ";
		} else if ("code".equals(str)) {
			return "dim_H_add_succ_code";
		} else {
			return "error";
		}
	}

	// get set 方法
	public Dimission_HandoverServer getDimission_HandoverServer() {
		return dimission_HandoverServer;
	}

	public void setDimission_HandoverServer(
			Dimission_HandoverServer dimissionHandoverServer) {
		dimission_HandoverServer = dimissionHandoverServer;
	}

	public Dimission_Handover getDimissionHandover() {
		return dimissionHandover;
	}

	public void setDimissionHandover(Dimission_Handover dimissionHandover) {
		this.dimissionHandover = dimissionHandover;
	}

	public List<Dimission_Handover> getDimissionHandoverList() {
		return dimissionHandoverList;
	}

	public void setDimissionHandoverList(
			List<Dimission_Handover> dimissionHandoverList) {
		this.dimissionHandoverList = dimissionHandoverList;
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

	public DimissionLog getDimissionLog() {
		return dimissionLog;
	}

	public void setDimissionLog(DimissionLog dimissionLog) {
		this.dimissionLog = dimissionLog;
	}

	public List<Dimission_Handover> getDimissionHandovercodeList() {
		return dimissionHandovercodeList;
	}

	public void setDimissionHandovercodeList(
			List<Dimission_Handover> dimissionHandovercodeList) {
		this.dimissionHandovercodeList = dimissionHandovercodeList;
	}

	public DimissionLogServer getDimissionLogServer() {
		return dimissionLogServer;
	}

	public void setDimissionLogServer(DimissionLogServer dimissionLogServer) {
		this.dimissionLogServer = dimissionLogServer;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
