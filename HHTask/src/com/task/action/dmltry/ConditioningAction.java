package com.task.action.dmltry;

import java.util.List;

import com.task.Server.dmltry.ConditioningServer;
import com.task.entity.Users;
import com.task.entity.dmltry.Conditioning;

public class ConditioningAction {

	private ConditioningServer conditioningServer;
	private Conditioning conditioning;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private List<Conditioning> listConditioning;
	private Users uses;
	// id
	private Integer id;

	private List<Users> userList;

	// 添加ip
	public String addConditioning() {
		errorMessage = conditioningServer.addConditioning(conditioning);
		if (errorMessage.equals("true")) {
			errorMessage = "添加成功";
		}
		return "error";
	}

	public String uadateConditioning() {

		errorMessage = conditioningServer.uadateConditioning(conditioning);
		if (errorMessage.equals("true")) {
			errorMessage = "修改成功";
		}
		return "showCondition";
	}

	public String showConditioning() {
		listConditioning = conditioningServer.list();
		if (listConditioning != null) {
			return "Conditioning_show";
		} else {
			return "error";
		}

	}

	public String conditioningDetail() {
		conditioning = conditioningServer.ConditioningDetail(id);
		userList = conditioningServer.username();

		if (conditioning != null) {
			return "Conditioning_showDetail";
		} else {
			return "showCondition";
		}

	}

	/**
	 * 尝试控制关闭空调
	 * 
	 * @return
	 */

	/*
	 * public void closecon() { listConditioning = conditioningServer.list();
	 * for (Conditioning conditionings : listConditioning) { String
	 * useridstrString = conditionings.getUserid(); int zu
	 * =useridstrString.length() - useridstrString.replaceAll(",", "").length();
	 * int count = 0; for (int i = 0; i < zu + 1; i++) { String userid =
	 * useridstrString.substring(getIndex(useridstrString, ",", i) + 1,
	 * getIndex(useridstrString, ",", i + 1));
	 * 
	 * int id =Integer.parseInt(userid);
	 * 
	 * // 根据id查询这个人 uses = conditioningServer.users(id); if(uses.getUserStatus()
	 * == null) {
	 * 
	 * } else {
	 * 
	 * if (uses.getUserStatus().equals("离开")) { count++; } if (count == zu + 1)
	 * { for (int j = 0; j < 3; j++) { UtilTong.cenl("192.168.0.199", 8888,
	 * conditionings.getConditioningip() + "3"); } }
	 * 
	 * }
	 * 
	 * } }
	 * 
	 * }
	 */

	/*
	 * public static int getIndex(String str, String s, int count) { if (count
	 * == 0) return -1; int len = s.length(); int index = -1 - len; while (count
	 * > 0 && (index = str.indexOf(s, index + len)) != -1) { count--; } if
	 * (index == -1) { index = str.length(); }
	 * 
	 * return index; }
	 */

	public Conditioning getConditioning() {
		return conditioning;
	}

	public void setConditioning(Conditioning conditioning) {
		this.conditioning = conditioning;
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

	public ConditioningServer getConditioningServer() {
		return conditioningServer;
	}

	public void setConditioningServer(ConditioningServer conditioningServer) {
		this.conditioningServer = conditioningServer;
	}

	public List<Conditioning> getListConditioning() {
		return listConditioning;
	}

	public void setListConditioning(List<Conditioning> listConditioning) {
		this.listConditioning = listConditioning;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Users> getUserList() {
		return userList;
	}

	public void setUserList(List<Users> userList) {
		this.userList = userList;
	}

}
