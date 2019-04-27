package com.task.action.checktype;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.checktype.CheckNoteServer;
import com.task.entity.Integral;
import com.task.entity.Users;
import com.task.entity.checktype.CheckNote;
import com.task.util.MKUtil;
import com.task.util.Util;

public class CheckNoteAction extends ActionSupport {
	private String successMessage;
	private String errorMessage;
	private String cpage = "1";
	private int pageSize = 4;
	private String total;
	private String url;
	private CheckNoteServer checkNoteServer;
	private List<CheckNote> checkNoteList;
	private CheckNote checkNote;
	private int id;
	private Integer nodeId;
	private int[] deptId;
	private String userId;
	private String pass;
	private String leader;
	private String status;// 前端传值（状态）

	public String findAll() {
		Map<Integer, Object> map = checkNoteServer.findAll(checkNote, Integer
				.parseInt(cpage), pageSize);
		checkNoteList = (List<CheckNote>) map.get(1);
		if (checkNoteList != null & checkNoteList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("CheckNoteAction_findAll.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "checkNoteList";
	}

	public String findSysAll() {
		Map<Integer, Object> map = checkNoteServer.findSysAll(checkNote,
				Integer.parseInt(cpage), pageSize);
		checkNoteList = (List<CheckNote>) map.get(1);
		if (checkNoteList != null & checkNoteList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("CheckNoteAction_findSysAll.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "checkNoteListBysys";
	}

	public String findSysAllshuaxin() {
		Map<Integer, Object> map = checkNoteServer.findSysAll(checkNote,
				Integer.parseInt(cpage), pageSize);
		checkNoteList = (List<CheckNote>) map.get(1);
		if (checkNoteList != null & checkNoteList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("CheckNoteAction_findSysAllshuaxin.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "shuaxinCheckNote";
	}

	// public String findSysAllbyfuze(){
	// Map<Integer, Object> map = checkNoteServer
	// .findbyfuze(checkNote, Integer
	// .parseInt(cpage), pageSize);
	// checkNoteList = (List<CheckNote>) map.get(1);
	// if (checkNoteList != null & checkNoteList.size() > 0) {
	// int count = (Integer) map.get(2);
	// int pageCount = (count + pageSize - 1) / pageSize;
	// this.setTotal(pageCount + "");
	// this.setUrl("CheckNoteAction_findSysAllbyfuze.action");
	// }else{
	// errorMessage = "没有找到你要查询的内容,请检查后重试!";
	// }
	// return "checkNoteListBysys";
	// }
	public String findSysAllbytijiao() {
		Map<Integer, Object> map = checkNoteServer.findbytijiao(checkNote,
				Integer.parseInt(cpage), pageSize);
		checkNoteList = (List<CheckNote>) map.get(1);
		if (checkNoteList != null & checkNoteList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("CheckNoteAction_findSysAllbytijiao.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "checkNotequeren";
	}

	public String findOne() {
		checkNote = checkNoteServer.findById(nodeId);
		return "checkNoteOne";
	}

	public String Usersbinding() {
		if (checkNoteServer.Userbind(id, deptId)) {
			return "bindDept";
		}
		return ERROR;
	}

	public void findUserJson() {
		List<Users> list = checkNoteServer.findUser(userId);
		MKUtil.writeJSON(true, "获取成功", list);
	}

	public void findDept() {
		List<Integer> list = checkNoteServer.findDept(Integer.parseInt(userId));
		try {
			MKUtil.writeJSON(list);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(e);
		}
	}

	public String startHandle() {
		checkNoteServer.startHandle(id, status);
		this.url = "CheckNoteAction_findSysAll.action";
		if ("kaishi".equals(status)) {
			errorMessage = "处理中";
		} else if ("wancheng".equals(status)) {
			errorMessage = "处理完成";
		}
		return "error";
	}

	public String findUserByfuze() {
		String id = Util.getLoginUser().getCode();
		Map<Integer, Object> map = checkNoteServer.findUserByfuze(checkNote,
				Integer.parseInt(cpage), pageSize, id, leader);
		checkNoteList = (List<CheckNote>) map.get(1);
		if (checkNoteList != null & checkNoteList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			if ("leader".equals(leader)) {
				this
						.setUrl("CheckNoteAction_findUserByfuze.action?leader=leader");
			} else {
				this.setUrl("CheckNoteAction_findUserByfuze.action");
			}
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "checkNoteList";
	}

	public String update() {
		if (checkNote.getId() != null) {
			checkNote = checkNoteServer.findById(checkNote.getId());
		}
		if (("待审批").equals(checkNote.getStatus())) {
			if ("yes".equals(pass)) {
				Integral i = checkNoteServer.findIntegral(checkNote
						.getFirstPersonCode());
				if (i != null) {
					checkNoteServer.dock(checkNote, i);
				} else {
					errorMessage = "无用户积分";
					return "error";
				}
				checkNote.setStatus("已扣分");
			} else if ("no".equals(pass)) {
				checkNote.setStatus("驳回");
			}
			checkNoteServer.update(checkNote);
			return "checkNote_toList";
		} else {
			errorMessage = "该信息已经审批完成";
			return "error";
		}
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

	public String getCpage() {
		return cpage;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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

	public CheckNoteServer getCheckNoteServer() {
		return checkNoteServer;
	}

	public void setCheckNoteServer(CheckNoteServer checkNoteServer) {
		this.checkNoteServer = checkNoteServer;
	}

	public List<CheckNote> getCheckNoteList() {
		return checkNoteList;
	}

	public void setCheckNoteList(List<CheckNote> checkNoteList) {
		this.checkNoteList = checkNoteList;
	}

	public CheckNote getCheckNote() {
		return checkNote;
	}

	public void setCheckNote(CheckNote checkNote) {
		this.checkNote = checkNote;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int[] getDeptId() {
		return deptId;
	}

	public void setDeptId(int[] deptId) {
		this.deptId = deptId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public Integer getNodeId() {
		return nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
