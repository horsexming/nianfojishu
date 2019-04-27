package com.task.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.MachineDayYZSJServer;
import com.task.Server.MachineMonthDjServer;
import com.task.entity.Machine;
import com.task.entity.MachineDayDj;
import com.task.entity.MachineDayYZSJ;
import com.task.entity.MachineMonthDj;
import com.task.entity.sop.ProcessInfor;
import com.task.util.MKUtil;
import com.task.util.Util;

public class MachineMonthDjAction extends ActionSupport {

	private MachineMonthDj mmd;// 设备点检月汇总表
	private Integer id;
	private String month;
	private Machine machine;
	private List<MachineMonthDj> mmdList;
	private MachineMonthDjServer mmdServer;
	private MachineDayYZSJServer mdyServer;
	private List<MachineDayYZSJ> mdyList;
	private List<Integer> monthdayList;
	private int monthday;
	private String names="";
	private List<ProcessInfor> proList;
	private Integer firstday;
	private Integer lastday;
	
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private String pageStatus;// 页面状态

	public String add() {
		if (mmd != null) {
			if (mmdServer.add(mmd)) {
				errorMessage = "添加成功";
				return "addmmd";
			}
		}
		errorMessage = "添加失败";
		return ERROR;
	}

	public String del() {
		if (mmd != null) {
			if (mmdServer.del(mmd)) {
				errorMessage = "删除成功";
				return "addmmd";
			}
		}
		errorMessage = "删除失败";
		return ERROR;
	}

	public String update() {
		if (mmd != null) {
			if (mmdServer.update(mmd)) {
				errorMessage = "更新成功";
				return "addmmd";
			}
		}
		errorMessage = "更新失败";
		return ERROR;
	}

	public String findallbyid() {
		mmd = mmdServer.findallbyid(id, month);
		mdyList = mdyServer.findmdy(id, month);
		machine=mmdServer.getmachine(id);
		if (mmd != null && mmd.getId() > 0  
				 && mdyList != null && mdyList.size() > 0) {
			monthday = Util.getMonthofday(null);
			monthdayList=new ArrayList<Integer>();
			for (int i = 0; i <monthday; i++) {
				monthdayList.add(i);
			}
			firstday=Integer.parseInt(mdyList.get(0).getMachineday().substring(8, 10));
			lastday=Integer.parseInt(mdyList.get(mdyList.size()-1).getMachineday().substring(8, 10));
			//month=Util.getDateTime("yyyy年MM月");
			return "findallbyid";//Process_showmonthdj.jsp
		}
		errorMessage = "没有找到你要查询的内容，请检查后重试";
		return ERROR;
	}
	public void getcaozuoze(){
		proList=mmdServer.getprocess(id, month);
		if(proList!=null&&proList.size()>0){
			try {
				MKUtil.writeJSON(proList);
			} catch (Exception e) {
				MKUtil.writeJSON(e);
			}
		}
	}
	
	public MachineMonthDj getMmd() {
		return mmd;
	}

	public void setMmd(MachineMonthDj mmd) {
		this.mmd = mmd;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public List<MachineMonthDj> getMmdList() {
		return mmdList;
	}

	public void setMmdList(List<MachineMonthDj> mmdList) {
		this.mmdList = mmdList;
	}

	public MachineMonthDjServer getMmdServer() {
		return mmdServer;
	}

	public void setMmdServer(MachineMonthDjServer mmdServer) {
		this.mmdServer = mmdServer;
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

	public MachineDayYZSJServer getMdyServer() {
		return mdyServer;
	}

	public void setMdyServer(MachineDayYZSJServer mdyServer) {
		this.mdyServer = mdyServer;
	}
	public List<MachineDayYZSJ> getMdyList() {
		return mdyList;
	}

	public void setMdyList(List<MachineDayYZSJ> mdyList) {
		this.mdyList = mdyList;
	}

	public int getMonthday() {
		return monthday;
	}

	public void setMonthday(int monthday) {
		this.monthday = monthday;
	}

	

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}
	
	



	public List<Integer> getMonthdayList() {
		return monthdayList;
	}

	public void setMonthdayList(List<Integer> monthdayList) {
		this.monthdayList = monthdayList;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public List<ProcessInfor> getProList() {
		return proList;
	}

	public void setProList(List<ProcessInfor> proList) {
		this.proList = proList;
	}

	public Integer getFirstday() {
		return firstday;
	}

	public void setFirstday(Integer firstday) {
		this.firstday = firstday;
	}

	public Integer getLastday() {
		return lastday;
	}

	public void setLastday(Integer lastday) {
		this.lastday = lastday;
	}

	
	
	
	
	 
	
}
