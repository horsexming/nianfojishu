package com.task.action.sop.spc;

import java.io.File;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.sop.spc.SpcControlServer;
import com.task.entity.Machine;
import com.task.entity.sop.spc.SpcControl;
import com.task.entity.sop.spc.SpcControlClDetail;
import com.task.entity.sop.spc.SpcControlGroup;
import com.task.entity.sop.spc.SpcGroups;
import com.task.util.MKUtil;

public class SpcControlAction {

	private SpcControlServer spcserver;
	private SpcControl spcControl;
	private SpcGroups spcgroups;
	private SpcControlGroup scg;
	private SpcControlClDetail scd;
	private List<SpcControl> spcControlList;
	private List<SpcGroups> spcgroupsList;
	private List<SpcControlGroup> scgList;
	private List<SpcControlClDetail> scdList;
	private Machine machine;
	private List<Machine> machineList;

	private File spc_file;
	private Float max_Xbar;
	private Float min_Xbar;
	private Float max_Range;
	private Float max_Xbar_ceil;
	private Float min_Xbar_floor;
	private Float max_Range_ceil;

	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private String pageStatus;// 页面状态

	private String screen;// 屏幕id
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	// toadd
	public String toadd() {
		if (spcControl != null && spcControl.getShebeiNo() != null
				&& spcControl.getShebeiNo().length() > 0) {
			machine = spcserver.findMachineByNo(spcControl.getShebeiNo());
		}
		spcgroupsList = spcserver.findAllspcGroups();
		return "spc_add";
	}

	/**
	 * 添加spc控制信息
	 */
	public String addspcControl() {
		try {
			errorMessage = spcserver.addSpcControl(spcControl);
			if ("true".equals(errorMessage)) {
				spcserver.jisun(spcControl.getId());
			}
		} catch (Exception e) {
			errorMessage = "添加失败";
			e.printStackTrace();
		}
		return "findAllSpcControlList";
	}

	// 查询所有spc控制信息
	public String findAllSpcControlList() {
		if (spcControl != null) {
			ActionContext.getContext().getSession().put("spcControl",
					spcControl);
		} else {
			spcControl = (SpcControl) ActionContext.getContext().getSession()
					.get("spcControl");
		}
		Object[] obj = spcserver.findAllSpcControlList(spcControl, Integer
				.parseInt(cpage), pageSize, pageStatus);
		spcControlList = (List<SpcControl>) obj[0];
		if (spcControl != null && spcControl.getShebeiNo() != null
				&& spcControl.getShebeiNo().length() > 0) {
			machine = spcserver.findMachineByNo(spcControl.getShebeiNo());
		}
		spcgroupsList = spcserver.findAllspcGroups();
		int count = (Integer) obj[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		this.setUrl("SpcControlAction_findAllSpcControlList.action?pageStatus="
				+ pageStatus);
		return "spc_showList";
	}

	// 根据Id查询spc控制信息 findSpcControlById
	public String findSpcControlById(){
		
		if(screen!=null)
		{
			int currentid =id;
			try {
				spcControlList = spcserver.findSpcControlByScreenId(Integer.valueOf(screen));
//				for (SpcControl spcControl : spcControlList) {
//					if(currentid==spcControl.getId()){
//						id=spcControlList.get(spcControlList.indexOf(spcControl)+1).getId();
//					}
//				}
				for(int i=0;i<spcControlList.size();i++)
				{
					if(currentid==spcControlList.get(i).getId()){
						id=spcControlList.get(i+1).getId();
						
					}
				}
			} catch (Exception e) {
				
			}
			
		}
		Object[] obj =	spcserver.findSpcControlById(id);
		spcControl = (SpcControl) obj[0];
		scgList = (List<SpcControlGroup>) obj[1];
		max_Xbar = (Float) obj[2];
		min_Xbar = (Float) obj[3];
		max_Range = (Float) obj[4];
		max_Xbar_ceil = (Float) obj[5];
		min_Xbar_floor = (Float) obj[6];
		max_Range_ceil = (Float) obj[7];
		spcgroupsList = spcserver.findAllspcGroups();
		return "spc_show";
	}

	public void findSpcControlById0() {
		try {
			Object[] obj = spcserver.findSpcControlById(id);
			scgList = (List<SpcControlGroup>) obj[1];
			MKUtil.writeJSON(scgList);
		} catch (Exception e) {
			MKUtil.writeJSON("error");
			e.printStackTrace();
		}
	}

	// 添加spc群组数表
	public String addspcGroups() {
		spcserver.addspcGroups(spcgroupsList);
		return "toadd";
	}

	// 添加spc群组数表
	public String updatespcGroups() {
		errorMessage = spcserver.updatespcGroups(spcgroupsList);
		return "spc_groups";
	}

	// 查询所有群组数表
	public String findAllspcGroups() {
		spcgroupsList = spcserver.findAllspcGroups();
		return "spc_groups";
	}

	// 查询所有设备;
	public String findAllMachine() {
		if (machine != null) {
			ActionContext.getContext().getSession().put("machine", machine);
		} else {
			machine = (Machine) ActionContext.getContext().getSession().get(
					"machine");
		}
		Object[] obj = spcserver.findAllMachine(machine, Integer
				.parseInt(cpage), pageSize, pageStatus);
		machineList = (List<Machine>) obj[0];
		int count = (Integer) obj[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		this.setUrl("SpcControlAction_findAllMachine.action?pageStatus="
				+ pageStatus);
		return "spc_machine";
	}

	// 根据屏幕ID查询spc图表信息;
	public String findSpcControlByScreenId() {
		spcControlList = spcserver.findSpcControlByScreenId(id);
		screen=id+"";
		if (spcControlList != null && spcControlList.size() > 0) {
			spcControl = spcControlList.get(0);
			Object[] obj = spcserver.findSpcControlById(spcControl.getId());
			spcControl = (SpcControl) obj[0];
			scgList = (List<SpcControlGroup>) obj[1];
			max_Xbar = (Float) obj[2];
			min_Xbar = (Float) obj[3];
			max_Range = (Float) obj[4];
			max_Xbar_ceil = (Float) obj[5];
			min_Xbar_floor = (Float) obj[6];
			max_Range_ceil = (Float) obj[7];
			spcgroupsList = spcserver.findAllspcGroups();
		}
		return "spc_show";
	}

	//导入数据
	public String daoru(){
		errorMessage =	spcserver.daoru(spc_file, id);
		if("true".equals(errorMessage)){
			spcserver.jisun(id);
			return "findSpcControlById";
		}
		return "error";
	}
	//删除spc数据
	public String delSPC(){
		errorMessage =spcserver.delSPC(id);
		if("true".equals(errorMessage)){
			errorMessage = "删除成功";
		}
		return "error";
	}
	public SpcControlServer getSpcserver() {
		return spcserver;
	}

	public void setSpcserver(SpcControlServer spcserver) {
		this.spcserver = spcserver;
	}

	public SpcControl getSpcControl() {
		return spcControl;
	}

	public void setSpcControl(SpcControl spcControl) {
		this.spcControl = spcControl;
	}

	public SpcGroups getSpcgroups() {
		return spcgroups;
	}

	public void setSpcgroups(SpcGroups spcgroups) {
		this.spcgroups = spcgroups;
	}

	public SpcControlGroup getScg() {
		return scg;
	}

	public void setScg(SpcControlGroup scg) {
		this.scg = scg;
	}

	public SpcControlClDetail getScd() {
		return scd;
	}

	public void setScd(SpcControlClDetail scd) {
		this.scd = scd;
	}

	public List<SpcControl> getSpcControlList() {
		return spcControlList;
	}

	public void setSpcControlList(List<SpcControl> spcControlList) {
		this.spcControlList = spcControlList;
	}

	public List<SpcGroups> getSpcgroupsList() {
		return spcgroupsList;
	}

	public void setSpcgroupsList(List<SpcGroups> spcgroupsList) {
		this.spcgroupsList = spcgroupsList;
	}

	public List<SpcControlGroup> getScgList() {
		return scgList;
	}

	public void setScgList(List<SpcControlGroup> scgList) {
		this.scgList = scgList;
	}

	public List<SpcControlClDetail> getScdList() {
		return scdList;
	}

	public void setScdList(List<SpcControlClDetail> scdList) {
		this.scdList = scdList;
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

	public Float getMax_Xbar() {
		return max_Xbar;
	}

	public void setMax_Xbar(Float maxXbar) {
		max_Xbar = maxXbar;
	}

	public Float getMin_Xbar() {
		return min_Xbar;
	}

	public void setMin_Xbar(Float minXbar) {
		min_Xbar = minXbar;
	}

	public Float getMax_Range() {
		return max_Range;
	}

	public void setMax_Range(Float maxRange) {
		max_Range = maxRange;
	}

	public Float getMax_Xbar_ceil() {
		return max_Xbar_ceil;
	}

	public void setMax_Xbar_ceil(Float maxXbarCeil) {
		max_Xbar_ceil = maxXbarCeil;
	}

	public Float getMin_Xbar_floor() {
		return min_Xbar_floor;
	}

	public void setMin_Xbar_floor(Float minXbarFloor) {
		min_Xbar_floor = minXbarFloor;
	}

	public Float getMax_Range_ceil() {
		return max_Range_ceil;
	}

	public void setMax_Range_ceil(Float maxRangeCeil) {
		max_Range_ceil = maxRangeCeil;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public List<Machine> getMachineList() {
		return machineList;
	}

	public void setMachineList(List<Machine> machineList) {
		this.machineList = machineList;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public File getSpc_file() {
		return spc_file;
	}
	public void setSpc_file(File spcFile) {
		spc_file = spcFile;
	}
	
	
	
	
}
