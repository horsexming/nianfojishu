package com.task.action.pmi;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.pmi.PmiManagementServer;
import com.task.entity.Machine;
import com.task.entity.TaSopGongwei;
import com.task.entity.approval.Signature;
import com.task.entity.pmi.PmiManagement;
import com.task.entity.sop.Procard;
import com.task.entity.sop.ProcessInfor;
import com.task.util.MKUtil;

public class PmiManagementAction extends ActionSupport {
	private PmiManagement pmiManagement;
	private PmiManagementServer pmiManagementServer;
	private List<Map> maps;
	private List<Map> proceList;
	private Machine machine;
	private Integer pmi_id;
	private List list;
	private Integer id1;
	private String workPosition;
	private Integer machine_id;

	private ProcessInfor processInfor;// 关联流水卡片工序信息表 只供查询使用20150811——lc加
	private Integer infor_rootid;// 通过infor获取rootID
	private String errorMessage;
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private Procard procard;

	// 查询所有的pmi
	public String findPmi() {
		if (pmiManagement != null) {
			ActionContext.getContext().getSession().put("pmiManagement",
					pmiManagement);
		} else {
			pmiManagement = (PmiManagement) ActionContext.getContext()
					.getSession().get("pmiManagement");
		}
		if (machine != null) {
			ActionContext.getContext().getSession().put("machine", machine);
		} else{
			machine = (Machine) ActionContext.getContext().getSession().get("machine");
		}
		Object[] object = this.pmiManagementServer.findPmi(pmiManagement, Integer
				.parseInt(cpage), pageSize,machine);
		if (object != null && object.length > 0) {
			maps = (List<Map>) object[0];
			if (maps != null && maps.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("pmiManagementAction_findPmi.action");
			}
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "findPmi";
	}

	// 添加PMI
	public String addPmi() {
		boolean bool = this.pmiManagementServer.addPmi(pmiManagement);
		if (bool) {
			errorMessage = "添加成功!";
		} else {
			errorMessage = "数据异常，请重新添加!";
		}
		return "addPmi";
	}

	// 根据id查询PMI
	public String salPmiByid() {
		pmiManagement = this.pmiManagementServer.salPmiByid(pmiManagement);
		return "salPmiByid";
	}

	// 更新PMI
	public String updatePmi() {
		boolean bool = this.pmiManagementServer.updatePmi(pmiManagement);
		if (bool) {
			this.errorMessage = "修改成功!";
		} else {
			this.errorMessage = "修改失败!";
		}
		return "updatePmi";
	}

	// 删除PMI
	public String delPmi() {
		boolean b = this.pmiManagementServer.delPmi(pmiManagement);
		if (b) {
			return "delPmi";
		} else {
			return ERROR;
		}
	}
   
	
	// 查询对应设备
	public String findMachineByid() {
		if (this.machine != null) {
			ActionContext.getContext().getSession().put("machine", machine);
		} else {
			machine = (Machine) ActionContext.getContext().getSession().get(
					"machine");
		}
		Object[] object = pmiManagementServer.findMachineByid(machine, Integer
				.parseInt(cpage), pageSize, this.pmi_id);
		if (object != null && object.length > 0) {
			maps = (List<Map>) object[0];
			if (maps != null && maps.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this
						.setUrl("pmiManagementAction_findMachineByid.action?pmi_id="
								+ pmi_id);
			}
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "findMachineByid";
	}
	/**
	 * 根据pmi_id得到procard.rootId
	 * @return
	 */
	public String findRoodId() {
		infor_rootid = pmiManagementServer.findRoodId(pmi_id);
		return "pmi_rootId";
	}

	/**
	 * 查询对应生产工序 2015-0811——lc加
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findProcessInforBypmi_id() {
		if (this.processInfor != null) {
			ActionContext.getContext().getSession().put("processInfor",
					processInfor);
		} else {
			processInfor = (ProcessInfor) ActionContext.getContext()
					.getSession().get("processInfor");
		}
		Object[] object = pmiManagementServer.findProcessInforByid(
				processInfor, Integer.parseInt(cpage), pageSize, this.pmi_id);
		
		if (object != null && object.length > 0) {
			proceList = (List<Map>) object[0];
			if (proceList != null && proceList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("pmiManagementAction_findProcessInforBypmi_id.action?pmi_id="
								+ pmi_id);
				this.pmiManagement=(PmiManagement) object[2];
			} else {
				errorMessage = "该pmi尚未开始使用,暂无数据显示!谢谢.";
			}
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "findProcessInforBypmi_id";
	}

	// 查询所有设备工位
	public String salMachine() {
		this.list = this.pmiManagementServer.findMachineworkPosition();
		return "addMachine";
	}

	// 添加设备信息
	public String addMachine() {
		boolean bool = this.pmiManagementServer.addMachine(this.machine,
				this.pmi_id);
		if (bool) {
			this.errorMessage = "添加成功!";
			return "addMachine";
		} else {
			this.errorMessage = "数据异常，请重新添加!";
			return ERROR;
		}
	}

	// 根据工位查询设备
	public void findMachineByworkPosition() {
		this.list = this.pmiManagementServer
				.findMachineByworkPosition(this.workPosition);
		MKUtil.writeJSON(list);
	}

	// 删除相应的设备
	public String delMachine() {
		boolean bool = this.pmiManagementServer.delMachine(machine);
		if (bool) {
			this.errorMessage = "删除成功!";
			return "delMachine";
		} else {
			return ERROR;
		}

	}

	public PmiManagement getPmiManagement() {
		return pmiManagement;
	}

	public void setPmiManagement(PmiManagement pmiManagement) {
		this.pmiManagement = pmiManagement;
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

	public PmiManagementServer getPmiManagementServer() {
		return pmiManagementServer;
	}

	public void setPmiManagementServer(PmiManagementServer pmiManagementServer) {
		this.pmiManagementServer = pmiManagementServer;
	}

	public List<Map> getMaps() {
		return maps;
	}

	public void setMaps(List<Map> maps) {
		this.maps = maps;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public Integer getPmi_id() {
		return pmi_id;
	}

	public void setPmi_id(Integer pmiId) {
		pmi_id = pmiId;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Integer getId1() {
		return id1;
	}

	public void setId1(Integer id1) {
		this.id1 = id1;
	}

	public String getWorkPosition() {
		return workPosition;
	}

	public void setWorkPosition(String workPosition) {
		this.workPosition = workPosition;
	}

	public Integer getMachine_id() {
		return machine_id;
	}

	public void setMachine_id(Integer machineId) {
		machine_id = machineId;
	}

	public ProcessInfor getProcessInfor() {
		return processInfor;
	}

	public void setProcessInfor(ProcessInfor processInfor) {
		this.processInfor = processInfor;
	}

	public List<Map> getProceList() {
		return proceList;
	}

	public void setProceList(List<Map> proceList) {
		this.proceList = proceList;
	}

	public Procard getProcard() {
		return procard;
	}

	public void setProcard(Procard procard) {
		this.procard = procard;
	}

	public Integer getInfor_rootid() {
		return infor_rootid;
	}

	public void setInfor_rootid(Integer inforRootid) {
		infor_rootid = inforRootid;
	}

}
