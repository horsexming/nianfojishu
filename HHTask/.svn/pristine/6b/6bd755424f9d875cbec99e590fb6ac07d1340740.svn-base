package com.task.action.menjin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.menjin.FingerprintMgServer;
import com.task.Server.menjin.JLMApplicationServer;
import com.task.entity.Users;
import com.task.entity.caiwu.noncore.NonCorePayable;
import com.task.entity.caiwu.noncore.PayableType;
import com.task.entity.menjin.AccessEquipment;
import com.task.entity.menjin.DoorType;
import com.task.entity.menjin.FingerprintMg;
import com.task.entity.menjin.JLMApplication;
import com.task.entity.menjin.Operation;
import com.task.util.MKUtil;

/**
 * 卷帘门申请Action层 2017-02-09
 * 
 * @author Li_Cong
 * 
 */
@SuppressWarnings("unchecked")
public class JLMApplicationAction {
	private JLMApplicationServer jlmApplicationServer;// Server层
	private JLMApplication jlmApplication;// 卷帘门对象
	private List<JLMApplication> jlmApplicationList;// 卷帘门对象集合
	private List<Operation> operationList;// 操作对象集合
	private List<AccessEquipment> accessEquipmentList;// 所有卷帘门的list
	private DoorType doorType;
	private List<DoorType> doorTypeList;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private Integer id;// id
	private String proposer_id;
	private String pageStatus;// 页面状态// 绑定功能
	private Integer ta_jlm_operation;
	private String doorIp;
	private String doorPort;
	private String uid,jreson,jid,startTime,endTime;
	
	// 分页
	private String tag;// 标识（dag）
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	public String Test() {
		return "error";
	}

	//门类型添加
	public String toaddType() {
		doorTypeList = jlmApplicationServer.findDoorType();
		return "doorType_add";
	}
	//显示所有门类型
	public String showdoortype() {
		if (doorType != null) {
			ActionContext.getContext().getSession().put("DoorType",
					doorType);
		} else {
			doorType = (DoorType) ActionContext.getContext()
					.getSession().get("JLMApplication");
		}
		Map<Integer, Object> map = jlmApplicationServer
				.finddoortype(doorType, Integer
						.parseInt(cpage), pageSize, tag);
		doorTypeList = (List<DoorType>) map.get(1);// 显示记录列表
		if (doorTypeList != null && doorTypeList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("JLMApplicationAction_showdoortype.action?tag=" + tag);
			errorMessage = null;
		} else {
			errorMessage = "没有符合条件的信息,请检查后重试!";
		}
		return "doorType_add";

	}
	
	public String addType() {
		errorMessage = jlmApplicationServer.saveDoorType(doorType);
		if ("添加成功！".equals(errorMessage))
			url = "JLMApplicationAction_showdoortype.action";
		return "error";
	}
	public String deleteEner(){
		errorMessage = jlmApplicationServer.deleteEner(id);
		if("删除成功！".equals(errorMessage)){
			url = "JLMApplicationAction_showdoortype.action";
		}
		return "error";
	}
	public String findSelectName(){
		String message = jlmApplicationServer.findPayableType();
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(message);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "error";
	}
	//
	
	public String toadd() {
		accessEquipmentList = jlmApplicationServer.findAccessE();
		return "jlm_add";
	}
	//卷帘门名称下拉
	public void list_jlmname() {
		MKUtil.writeJSON(true,"查询成功",jlmApplicationServer.findAccessE());
	}
	
	//管理员管理卷帘门
	public String jlmBoss() {
		accessEquipmentList = jlmApplicationServer.findAccessE();
		return "jlm_boss";
	}
	
	
	//绑定之后才可以卷帘门
	public String jlmBangding() {
		accessEquipmentList = jlmApplicationServer.findAccessE(tag);
		return "jlm_boss";
	}
	//显示所有申请记录
	public String showApplication() {
		if (jlmApplication != null) {
			ActionContext.getContext().getSession().put("JLMApplication",
					jlmApplication);
		} else {
			jlmApplication = (JLMApplication) ActionContext.getContext()
					.getSession().get("JLMApplication");
		}
		Map<Integer, Object> map = jlmApplicationServer
				.findJLMApplicationByCondition(jlmApplication, Integer
						.parseInt(cpage), pageSize, tag);
		jlmApplicationList = (List<JLMApplication>) map.get(1);// 显示记录列表
		if (jlmApplicationList != null && jlmApplicationList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("JLMApplication_showApplication.action?tag=" + tag);
			errorMessage = null;
		} else {
			errorMessage = "没有符合条件的信息,请检查后重试!";
		}
		return "jlmApplication_show";

	}
	// 分页显示
	// 显示查询内容
	public void showList1() {
		List list = jlmApplicationServer.selectJlmApplication(proposer_id);
		if (list != null && list.size() > 0) {
			MKUtil.writeJSON(true, "查询成功", list);
		} else {
			MKUtil.writeJSON(false, "没有提交的数据!", list);
		}
	}
	//查询可操作门
	public void showList2(){
		List list = jlmApplicationServer.selectOperation(ta_jlm_operation);
		if (list != null && list.size() > 0) {
			MKUtil.writeJSON(true, "查询成功", list);
		} else {
			MKUtil.writeJSON(false, "没有提交的数据!", list);
		}
	}
	//查询可操作门
	public String showList_o(){
		operationList = jlmApplicationServer.selectOperation(ta_jlm_operation);
		return "jlmOperation_show";
	}

	// 添加方法
	public String add() {
		if (jlmApplication != null) {
			errorMessage = jlmApplicationServer.addJLMApplication(
					jlmApplication, operationList);
			if ("申请成功！".equals(errorMessage))
				url = "JLMApplicationAction_showList.action"; 
			return "error";
		}
		errorMessage = "数据为空，添加失败！";
		return "error";
	}
	//Android 添加方法
	public void androidadd() {
		if (jlmApplication != null) {
			errorMessage = jlmApplicationServer.addandroid(jlmApplication, operationList);
			if ("申请成功!".equals(errorMessage)) {
				MKUtil.writeJSON(true, "申请成功！", null);
			} else {
				MKUtil.writeJSON(false, "申请失败！", null);
			}
		} else {
			MKUtil.writeJSON(false, "申请失败！", null);
		}
	}

	// 跳转到修改页面的方法
	public String toupdate() {
		if (jlmApplication.getId() != null && jlmApplication.getId() > 0
				&& jlmApplication != null) {
			jlmApplication = jlmApplicationServer
					.byIdJLMApplication(jlmApplication.getId());
			if (jlmApplication != null)
				return "fingerprintMg_update";
		}
		errorMessage = "数据为空!请检查";
		return "error";
	}

	// 修改方法
	public String update() {
		errorMessage = jlmApplicationServer
				.updateJLMApplication(jlmApplication);
		if ("修改成功！".equals(errorMessage))
			url = "JLMApplicationAction_showList.action";
		return "error";
	}

	// 删除方法
	public String delete() {
		if (id != null && id > 0) {
			errorMessage = jlmApplicationServer.deleteJLMApplication(id);
			if ("删除成功！".equals(errorMessage))
				url = "JLMApplicationAction_showList.action";
			return "error";
		}
		errorMessage = "不存在该对象！删除失败！";
		return "error";
	}
	/**
	 * 打开库位门
	 * @return
	 */
	public void OpenDoorById(){
		errorMessage =	jlmApplicationServer.OpenDoorById(id,doorIp,doorPort);
		if("true".equals(errorMessage)){
			MKUtil.writeJSON(true, "操作成功", null);
		}else {
			MKUtil.writeJSON(false, "开门失败", null);
		}
	}
	/**
	 * 关闭库位门
	 * @return
	 */
	public void ColseDoorById(){
		errorMessage =	jlmApplicationServer.ColseDoorById(id,doorIp,doorPort);
		if("true".equals(errorMessage)){
			MKUtil.writeJSON(true, "操作成功", null);
		}else {
			MKUtil.writeJSON(false, "关门失败", null);
		}
	}

	public String getDoorIp() {
		return doorIp;
	}

	public void setDoorIp(String doorIp) {
		this.doorIp = doorIp;
	}

	public String getDoorPort() {
		return doorPort;
	}

	public void setDoorPort(String doorPort) {
		this.doorPort = doorPort;
	}

	// 构造方法
	public JLMApplicationServer getJlmApplicationServer() {
		return jlmApplicationServer;
	}

	public void setJlmApplicationServer(
			JLMApplicationServer jlmApplicationServer) {
		this.jlmApplicationServer = jlmApplicationServer;
	}

	public JLMApplication getJlmApplication() {
		return jlmApplication;
	}

	public void setJlmApplication(JLMApplication jlmApplication) {
		this.jlmApplication = jlmApplication;
	}

	public List<JLMApplication> getJlmApplicationList() {
		return jlmApplicationList;
	}

	public void setJlmApplicationList(List<JLMApplication> jlmApplicationList) {
		this.jlmApplicationList = jlmApplicationList;
	}

	public List<Operation> getOperationList() {
		return operationList;
	}

	public void setOperationList(List<Operation> operationList) {
		this.operationList = operationList;
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

	public String getProposer_id() {
		return proposer_id;
	}

	public void setProposer_id(String proposerId) {
		proposer_id = proposerId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getTa_jlm_operation() {
		return ta_jlm_operation;
	}

	public void setTa_jlm_operation(Integer taJlmOperation) {
		ta_jlm_operation = taJlmOperation;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<AccessEquipment> getAccessEquipmentList() {
		return accessEquipmentList;
	}

	public void setAccessEquipmentList(List<AccessEquipment> accessEquipmentList) {
		this.accessEquipmentList = accessEquipmentList;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public DoorType getDoorType() {
		return doorType;
	}

	public void setDoorType(DoorType doorType) {
		this.doorType = doorType;
	}

	public List<DoorType> getDoorTypeList() {
		return doorTypeList;
	}

	public void setDoorTypeList(List<DoorType> doorTypeList) {
		this.doorTypeList = doorTypeList;
	}
	
	
	
}
