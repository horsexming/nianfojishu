package com.task.entity.bybz;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author 王晓飞 ta_bybz_CheckoutAndGages(检具、量具表) 和保养/校验标准表 （BaoYangBiaoZhun） 一对多
 *
 */

public class CheckoutAndGages implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;// 名称
	private String useStatus;// 使用状态
	private String personLiable;// 责任人
	private String codeLiable;// 责任人工号
	private Integer userIdLiable;// 责任人Id
	private String addUsers;// 添加人
	private Integer addUsesId;// 添加人Id
	private String addUsersCode;// 添加人工号
	private String addTime;// 添加时间
	private Integer jyCycle;// 校验周期
	private String nexJYTime;// 下次校验时间
	private Integer nextjyday;// 距离下次检验天数(页面传值)
	private Set<BaoYangBiaoZhun> bybzSet;// 保养标准（一对多关系）
	private List<BaoYangBiaoZhun> bybzList;// 页面传值使用

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getPersonLiable() {
		return personLiable;
	}

	public void setPersonLiable(String personLiable) {
		this.personLiable = personLiable;
	}

	public Integer getJyCycle() {
		return jyCycle;
	}

	public void setJyCycle(Integer jyCycle) {
		this.jyCycle = jyCycle;
	}

	public String getNexJYTime() {
		return nexJYTime;
	}

	public void setNexJYTime(String nexJYTime) {
		this.nexJYTime = nexJYTime;
	}

	@JSONField(serialize = false)
	public Set<BaoYangBiaoZhun> getBybzSet() {
		return bybzSet;
	}

	public void setBybzSet(Set<BaoYangBiaoZhun> bybzSet) {
		this.bybzSet = bybzSet;
	}

	public List<BaoYangBiaoZhun> getBybzList() {
		return bybzList;
	}

	public void setBybzList(List<BaoYangBiaoZhun> bybzList) {
		this.bybzList = bybzList;
	}

	public String getCodeLiable() {
		return codeLiable;
	}

	public void setCodeLiable(String codeLiable) {
		this.codeLiable = codeLiable;
	}

	public Integer getUserIdLiable() {
		return userIdLiable;
	}

	public void setUserIdLiable(Integer userIdLiable) {
		this.userIdLiable = userIdLiable;
	}

	public String getAddUsers() {
		return addUsers;
	}

	public void setAddUsers(String addUsers) {
		this.addUsers = addUsers;
	}

	public Integer getAddUsesId() {
		return addUsesId;
	}

	public void setAddUsesId(Integer addUsesId) {
		this.addUsesId = addUsesId;
	}

	public String getAddUsersCode() {
		return addUsersCode;
	}

	public void setAddUsersCode(String addUsersCode) {
		this.addUsersCode = addUsersCode;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getNextjyday() {
		return nextjyday;
	}

	public void setNextjyday(Integer nextjyday) {
		this.nextjyday = nextjyday;
	}

}
