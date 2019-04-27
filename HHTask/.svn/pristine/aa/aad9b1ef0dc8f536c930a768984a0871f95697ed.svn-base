package com.task.action.sys;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.sys.CircuitCustomizeServer;
import com.task.entity.DeptNumber;
import com.task.entity.system.AuditNode;
import com.task.entity.system.CircuitCustomize;
import com.task.entity.system.Option;
import com.task.util.MKUtil;

/**
 * CircuitCustomizeAction层
 * 
 * @author 刘培
 * 
 */
@SuppressWarnings("serial")
public class CircuitCustomizeAction extends ActionSupport {

	private CircuitCustomizeServer circuitCustomizeServer;// Server层
	private CircuitCustomize circuitCustomize;// 对象
	private AuditNode auditNode;// 流程节点
	private List<CircuitCustomize> circuitCustomizeList;// 集合
	@SuppressWarnings("unchecked")
	private List list;// 集合
	private List<DeptNumber> deptlist;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private String pageStatus;// 页面状态
	private Integer ccId;// 流程id
	private Integer auditLevel;// 审批等级
	private	List<Option> listoption;
	
	private int deptId;
	
	
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	/***
	 * 添加定制流程
	 * 
	 * @return
	 */
	public String addCircuitCustomize() {
		boolean bool = circuitCustomizeServer
				.addCircuitCustomize(circuitCustomize);
		if (bool) {
			errorMessage = "添加流程成功";
			url = "CircuitCustomize_findCCByCondition.action?circuitCustomize.name=";
		} else {
			errorMessage = "添加流程失败";
		}
		return ERROR;
	}

	/***
	 * 删除定制流程
	 * 
	 * @return
	 */
	public String delCirCus() {
		circuitCustomize = circuitCustomizeServer.findCirCusById(id);
		if (circuitCustomize != null) {
			circuitCustomizeServer.delCirCus(circuitCustomize);
			errorMessage = "删除流程成功";
			url = "CircuitCustomize_findCCByCondition.action?circuitCustomize.name=";
		} else {
			errorMessage = "删除流程失败";
		}
		return ERROR;
	}

	/***
	 * 查询定制流程
	 * 
	 * @return
	 */
	public String findCirCus() {
		circuitCustomize = circuitCustomizeServer.findCirCusById(id);
		listoption = circuitCustomizeServer.findOptionByccId(id);
		if (circuitCustomize != null) {
			return "circuitCustomize_update";
		} else {
			errorMessage = "不存在您要修改的流程信息";
		}
		return ERROR;
	}

	/***
	 * 修改定制流程
	 * 
	 * @return
	 */
	public String updateCirCus() {
		CircuitCustomize oldCirCus = circuitCustomizeServer.findCirCusById(id);
		if (oldCirCus != null) {
			BeanUtils.copyProperties(circuitCustomize, oldCirCus, new String[] {
					"id", "addUserName", "addDateTime", "auditNodeSet","deptset","setoption" });
			circuitCustomizeServer.updateCirCus(oldCirCus);
			errorMessage = "修改流程成功";
			url = "CircuitCustomize_findCirCus.action?id=" + id;
		} else {
			errorMessage = "不存在您要修改的流程";
		}
		return ERROR;
	}

	/***
	 * 查询定制流程
	 * 
	 * @param circuitCustomize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findCCByCondition() {
		pageSize = 30;
		if (circuitCustomize != null) {
			ActionContext.getContext().getSession().put("circuitCustomize",
					circuitCustomize);
		} else {
			circuitCustomize = (CircuitCustomize) ActionContext.getContext()
					.getSession().get("circuitCustomize");
		}
		Object[] object = circuitCustomizeServer.findCCByCondition(
				circuitCustomize, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			circuitCustomizeList = (List<CircuitCustomize>) object[0];
			if (circuitCustomizeList != null && circuitCustomizeList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("CircuitCustomize_findCCByCondition.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "circuitCustomize_List";
	}

	/***
	 * 通过流程id查询对应节点
	 * 
	 * @param ccId
	 * @return
	 */
	public String findAuditNodeByCcId() {
		circuitCustomize = circuitCustomizeServer.findCirCusById(id);
		return "circuitCustomize_node";
	}

	/***
	 * 查询流程所属审批节点信息(输出Json)
	 */
	public void findAuditNode() {
		list = circuitCustomizeServer.findAuditNodeByCcId(id,deptId);
		List<AuditNode> newList = new ArrayList<AuditNode>();
		for (int i = 0; i < list.size(); i++) {
			AuditNode auditNode = (AuditNode) list.get(i);
			auditNode.setCircuitCustomize(null);
			newList.add(auditNode);
		}
		MKUtil.writeJSON(newList);
	}

	/***
	 * 添加节点
	 * 
	 * @return
	 */
	public void addAuditNode() {
		try {
			boolean bool = circuitCustomizeServer.saveAuditNode(auditNode, id,deptId);
			
			MKUtil.writeJSON(bool, "添加成功!", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, e.getMessage(), null);
		}
	}

	/***
	 * 删除节点
	 * 
	 * @return
	 */
	public void delAuditNode() {
		try {
			boolean bool = circuitCustomizeServer.delAuditNode(id, ccId,
					auditLevel,deptId);
			MKUtil.writeJSON(bool, "删除成功!", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, e.getMessage(), null);
		}
	}

	//查询所有未绑定的部门；
	
	public void findAlldept(){
		deptlist = circuitCustomizeServer.findAlldept(ccId);
		try {
			MKUtil.writeJSON(deptlist);
		} catch (Exception e) {
			MKUtil.writeJSON(e);
			e.printStackTrace();
		}
	}
	//绑定部门
	public void bddept(){
		boolean bool = circuitCustomizeServer.bddept(ccId, deptId);
		try {
			if(bool){ 
				MKUtil.writeJSON("true");
			}
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON("error");
		}
	}
	//解除绑定部门
	public void jcbddept(){
		boolean bool = circuitCustomizeServer.jcbddept(ccId, deptId);
		try {
			if(bool){
				MKUtil.writeJSON("true");
			}
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON("error");
		}
	}
	
	//
	public void findbddept(){ 
		list = circuitCustomizeServer.findbddept(ccId);
		try {
			MKUtil.writeJSON(list);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(e);
		}
	}
	public CircuitCustomizeServer getCircuitCustomizeServer() {
		return circuitCustomizeServer;
	}

	public void setCircuitCustomizeServer(
			CircuitCustomizeServer circuitCustomizeServer) {
		this.circuitCustomizeServer = circuitCustomizeServer;
	}

	public CircuitCustomize getCircuitCustomize() {
		return circuitCustomize;
	}

	public void setCircuitCustomize(CircuitCustomize circuitCustomize) {
		this.circuitCustomize = circuitCustomize;
	}

	public AuditNode getAuditNode() {
		return auditNode;
	}

	public void setAuditNode(AuditNode auditNode) {
		this.auditNode = auditNode;
	}

	public List<CircuitCustomize> getCircuitCustomizeList() {
		return circuitCustomizeList;
	}

	public void setCircuitCustomizeList(
			List<CircuitCustomize> circuitCustomizeList) {
		this.circuitCustomizeList = circuitCustomizeList;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
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

	public Integer getCcId() {
		return ccId;
	}

	public void setCcId(Integer ccId) {
		this.ccId = ccId;
	}

	public Integer getAuditLevel() {
		return auditLevel;
	}

	public void setAuditLevel(Integer auditLevel) {
		this.auditLevel = auditLevel;
	}

	public List<DeptNumber> getDeptlist() {
		return deptlist;
	}

	public void setDeptlist(List<DeptNumber> deptlist) {
		this.deptlist = deptlist;
	}


	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public List<Option> getListoption() {
		return listoption;
	}

	public void setListoption(List<Option> listoption) {
		this.listoption = listoption;
	}

}
