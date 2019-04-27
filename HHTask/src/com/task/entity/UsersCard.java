package com.task.entity;

import java.io.Serializable;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;


/**
 *产线员工卡(ta_UsersCard)
 * @author 王晓飞
 *
 */
public class UsersCard implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String cardId;//卡号
	private String ckUserName;//持卡人
	private String ckUserCode;//持卡人
	private Integer ckUserId;//持卡人Id
	private String groupcalass;//班组
	private Set<Users> usersSet;//对应Users一对多
	private String dept;//所属部门
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getCkUserName() {
		return ckUserName;
	}
	public void setCkUserName(String ckUserName) {
		this.ckUserName = ckUserName;
	}
	@JSONField(serialize = false)
	public Set<Users> getUsersSet() {
		return usersSet;
	}
	@JSONField(serialize = false)
	public void setUsersSet(Set<Users> usersSet) {
		this.usersSet = usersSet;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Integer getCkUserId() {
		return ckUserId;
	}
	public void setCkUserId(Integer ckUserId) {
		this.ckUserId = ckUserId;
	}
	public String getCkUserCode() {
		return ckUserCode;
	}
	public void setCkUserCode(String ckUserCode) {
		this.ckUserCode = ckUserCode;
	}
	public String getGroupcalass() {
		return groupcalass;
	}
	public void setGroupcalass(String groupcalass) {
		this.groupcalass = groupcalass;
	}
	
	
	
}
