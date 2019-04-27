package com.task.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.MachineSparePartServer;
import com.task.entity.Machine;
import com.task.entity.MachineSparePart;
import com.task.entity.MachineSparePartVo;
import com.task.entity.OaAppDetail;
import com.task.util.MKUtil;

public class MachineSparePartAction {
	private MachineSparePart machineSparePart;// 关键备件
	private List<MachineSparePart> mspList;// 关键备件列表
	private MachineSparePartVo machineSparePartVo;// 关键备件视图
	private List<MachineSparePartVo> mspVoList;// 关键备件视图列表
	private MachineSparePartServer machineSparePartServer;// 关键备件服务
	private List<Machine> machinelist;// 设备列表
	private OaAppDetail oadetail;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	private String errorMessage;
	private String successMessage;

	/**
	 * 备件展示
	 * 
	 * @return
	 */
	public String showList() {
		if (machineSparePart != null) {
			ActionContext.getContext().getSession().put("machineSparePart",
					machineSparePart);
		} else {// 用来保持分页时带着查询条件
			machineSparePart = (MachineSparePart) ActionContext.getContext()
					.getSession().get("machineSparePart");
		}
		String safeStatus=null;
		if(machineSparePart!=null){
			safeStatus=machineSparePart.getSafeStatus();
		}
		//更新库存量
		machineSparePartServer.updateCurrCount();
		if(machineSparePart!=null&&machineSparePart.getMachine()!=null&&machineSparePart.getMachine().getName()!=null&&!machineSparePart.getMachine().getName().equals("")){
			machineSparePartVo=new MachineSparePartVo();
			machineSparePartVo.setMachineName(machineSparePart.getMachine().getName());
		}
		Map<Integer, Object> map = machineSparePartServer
				.findMachineSparePartsByCondition(machineSparePart, Integer
						.parseInt(cpage), pageSize);
		mspVoList = (List<MachineSparePartVo>) map.get(1);// 显示页的技能系数列表
		if(machineSparePart!=null){
			machineSparePart.setSafeStatus(safeStatus);
		}
		if (mspVoList != null & mspVoList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("machineSparePartAction_showList.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "machineSparePart_show";
	}

	/**
	 * 添加关键备件
	 * 
	 * @return
	 */
	public String add() {
		machineSparePart.setStorehouse("设备库");
		boolean b = machineSparePartServer.add(machineSparePart);
		if (b) {
			successMessage = "添加成功！";
			return showList();
		} else {
			errorMessage = "添加失败！";
			return "machineSparePart_add";
		}
	}

	/**
	 * 跳往修改关键备件页面
	 * 
	 * @return
	 */
	public String toUpdate() {
		machineSparePartVo = machineSparePartServer.getByVoId(machineSparePart
				.getId());
		machinelist = machineSparePartServer.getAllKeyMachines();
		return "machineSparePart_update";
	}

	/**
	 * 修改关键备件
	 * 
	 * @return
	 */
	public String update() {
		machineSparePart.setStorehouse("设备库");
		boolean b = machineSparePartServer.update(machineSparePart);
		if (b) {
			successMessage = "修改成功！";
			return showList();
		} else {
			errorMessage = "修改失败！";
			return "machineSparePart_update";
		}
	}

	public String delete() {
		boolean b = machineSparePartServer.delete(machineSparePart.getId());
		if (b) {
			successMessage = "删除成功！";
			machineSparePart = null;
		} else {
			errorMessage = "删除失败！";
		}
		return showList();
	}
	public String toNextMonthOa(){
		if (machineSparePartServer.preSaveOa()) {
		} else {
			errorMessage = "没有预算申报信息或申报未通过，无法进行申购，请确认后处理！";
		}
		return "machineSparePart_oa";
		
	}
	public String nextMonthOa(){
		String msg=null;
		try {
			msg = machineSparePartServer.nextMonthOa(oadetail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			successMessage="申报异常,请联系管理员!";
			return "machineSparePart_oa";
		}
		if(msg!=null&&msg.equals("true")){
			successMessage="申报成功!";
		}else{
			successMessage=msg;
		}
		return "machineSparePart_oa";
	}

	/**
	 * 动态获取设备列表
	 */
	public void getMachines() {
		machinelist = machineSparePartServer.getAllKeyMachines();
		MKUtil.writeJSON(machinelist);
	}

	public MachineSparePart getMachineSparePart() {
		return machineSparePart;
	}

	public void setMachineSparePart(MachineSparePart machineSparePart) {
		this.machineSparePart = machineSparePart;
	}

	public List<MachineSparePart> getMspList() {
		return mspList;
	}

	public void setMspList(List<MachineSparePart> mspList) {
		this.mspList = mspList;
	}

	public MachineSparePartServer getMachineSparePartServer() {
		return machineSparePartServer;
	}

	public void setMachineSparePartServer(
			MachineSparePartServer machineSparePartServer) {
		this.machineSparePartServer = machineSparePartServer;
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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public List<Machine> getMachinelist() {
		return machinelist;
	}

	public void setMachinelist(List<Machine> machinelist) {
		this.machinelist = machinelist;
	}

	public MachineSparePartVo getMachineSparePartVo() {
		return machineSparePartVo;
	}

	public void setMachineSparePartVo(MachineSparePartVo machineSparePartVo) {
		this.machineSparePartVo = machineSparePartVo;
	}

	public List<MachineSparePartVo> getMspVoList() {
		return mspVoList;
	}

	public void setMspVoList(List<MachineSparePartVo> mspVoList) {
		this.mspVoList = mspVoList;
	}

	public OaAppDetail getOadetail() {
		return oadetail;
	}

	public void setOadetail(OaAppDetail oadetail) {
		this.oadetail = oadetail;
	}


}
