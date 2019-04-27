package com.task.entity.menjin;

import java.io.Serializable;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.entity.Users;

/**
 * 物品柜表 2016-06-04
 * 
 * @author Li_Cong 表名 ta_mj_ToolCabine 添加物品柜(页面手动添加)
 */
public class ToolCabine implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String cabNumber;// 柜子编号
	private String cabAceIp;// 柜子对应门禁IP
	private String cabStatus;// 柜子状态（未满/已满）
	private String cabOpenOrder;// 柜子打开指令

	private String articleName;// 规定物品名称
	private String articleFormat;// 规定物品规格

	private Integer nowNumber;// 当前数量
	private String nowArticleName;// 当前物品名称
	private String nowArticleFormat;// 当前物品规格(快递柜指定规格)

	private String caType;// 柜子类型2016-09-12 加(工具/用户/衣柜/快递柜)
	private String passWord;// 衣柜固定密码

	private String addTime;// 添加时间
	private String updateTime;// 修改时间

	private Set<Users> users;// 用户（多对多）
	private String usersList;// 已经绑定的人

	private Integer resAccessId;// 使用中的申请Id
	// get set

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getResAccessId() {
		return resAccessId;
	}

	public void setResAccessId(Integer resAccessId) {
		this.resAccessId = resAccessId;
	}

	public String getUsersList() {
		return usersList;
	}

	public void setUsersList(String usersList) {
		this.usersList = usersList;
	}

	public String getCabNumber() {
		return cabNumber;
	}

	public void setCabNumber(String cabNumber) {
		this.cabNumber = cabNumber;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getCaType() {
		return caType;
	}

	public void setCaType(String caType) {
		this.caType = caType;
	}

	@JSONField(serialize = false)
	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}

	public String getCabAceIp() {
		return cabAceIp;
	}

	public void setCabAceIp(String cabAceIp) {
		this.cabAceIp = cabAceIp;
	}

	public String getCabStatus() {
		return cabStatus;
	}

	public void setCabStatus(String cabStatus) {
		this.cabStatus = cabStatus;
	}

	public String getCabOpenOrder() {
		return cabOpenOrder;
	}

	public void setCabOpenOrder(String cabOpenOrder) {
		this.cabOpenOrder = cabOpenOrder;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public String getArticleFormat() {
		return articleFormat;
	}

	public void setArticleFormat(String articleFormat) {
		this.articleFormat = articleFormat;
	}

	public String getNowArticleName() {
		return nowArticleName;
	}

	public void setNowArticleName(String nowArticleName) {
		this.nowArticleName = nowArticleName;
	}

	public String getNowArticleFormat() {
		return nowArticleFormat;
	}

	public void setNowArticleFormat(String nowArticleFormat) {
		this.nowArticleFormat = nowArticleFormat;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getNowNumber() {
		return nowNumber;
	}

	public void setNowNumber(Integer nowNumber) {
		this.nowNumber = nowNumber;
	}

}
