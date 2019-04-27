package com.task.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.MachineDayYZSJServer;
import com.task.entity.Machine;
import com.task.entity.MachineDayDj;
import com.task.entity.MachineDayYZSJ;
import com.task.util.MKUtil;

public class MachineDayYZSJAction extends ActionSupport {
	private Machine machine;
	private MachineDayYZSJ mdy;// 设备日运行时长表;
	private List<MachineDayYZSJ> mdyList;
	private List<MachineDayDj> mddListl;
	private MachineDayYZSJServer mdyServer;
	private Integer id;
	private String month;

	private float sc;

	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private String pageStatus;// 页面状态

	public String add() {

		if (mdyServer.add(mdy)) {
			successMessage = "点检完成";
			return "addmdy";
		}
		errorMessage = "添加失败";
		return ERROR;
	}
	public String add1() {

		if (mdyServer.add(mdy)) {
			errorMessage = "点检完成";
		}else{
		errorMessage = "添加失败";
		}
		return ERROR;
	}
	public String del() {
		if (mdy != null) {
			if (mdyServer.del(mdy)) {
				errorMessage = "删除成功";
				return "addmdy";
			}
		}
		errorMessage = "删除失败";
		return ERROR;
	}

	public String update() {
		if (mdy != null) {
			System.out.println(mdy.getId());
			if (mdyServer.update(mdy, sc, pageStatus)) {
				errorMessage = "更新成功";
				// return "addmdy";
			}
		}
		errorMessage = "更新失败";
		return ERROR;
	}

	public void getdjnrofmonth() {
		List<String> strList = new ArrayList<String>();
		strList = mdyServer.getdjnrofmonth(id, month);
		try {
			MKUtil.writeJSON(strList);
		} catch (Exception e) {
			MKUtil.writeJSON(e);
		}
	}

	public void findmdyofmonth() {
		mdyList = mdyServer.findmdyofmonth(id, month);
		try {
			MKUtil.writeJSON(mdyList);
		} catch (Exception e) {
			MKUtil.writeJSON(e);
		}

	}

	public void getmdybymachineId() {
		mdy = mdyServer.getmdybymachineId(id, null);
		String msg = "";
		if (mdy != null && mdy.getId() > 0) {
			msg = mdy.getDj_status();
		}
		try {
			MKUtil.writeJSON(msg);
		} catch (Exception e) {
			MKUtil.writeJSON(e);
		}
	}

	public void isdj() {
		machine = mdyServer.getMachine(id);
		successMessage = "";
		if (machine != null) {
			successMessage = machine.getIsdj();
		}
		try {
			MKUtil.writeJSON(successMessage);
		} catch (Exception e) {
			MKUtil.writeJSON(e);
		}
	}

	public void getMachinebyproessId() {
		try {
			machine = mdyServer.getMachinebyproessId(id);
			if (machine != null && "是".equals(machine.getIsdj())) {
				mdy = mdyServer.getmdybymachineId(machine.getId(), null);
				if (mdy == null) {
					mdy = new MachineDayYZSJ();
					mdy.setMachine_id(machine.getId());
				}
				MKUtil.writeJSON(true, "是", mdy);
			} else
				MKUtil.writeJSON(true, "否", null);
		} catch (Exception e) {
			MKUtil.writeJSON(e);
		}
	}

	public MachineDayYZSJ getMdy() {
		return mdy;
	}

	public void setMdy(MachineDayYZSJ mdy) {
		this.mdy = mdy;
	}

	public List<MachineDayYZSJ> getMdyList() {
		return mdyList;
	}

	public void setMdyList(List<MachineDayYZSJ> mdyList) {
		this.mdyList = mdyList;
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

	public List<MachineDayDj> getMddListl() {
		return mddListl;
	}

	public void setMddListl(List<MachineDayDj> mddListl) {
		this.mddListl = mddListl;
	}

	public float getSc() {
		return sc;
	}

	public void setSc(float sc) {
		this.sc = sc;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

}
