package com.task.entity;

import java.io.Serializable;
import java.util.Set;

public class JiMiLeiXing  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private	Integer id;
	private	String type;//机密类型；
	private	Set<Users> users;//所对应的人员机密类型；（多对多）
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Set<Users> getUsers() {
		return users;
	}
	public void setUsers(Set<Users> users) {
		this.users = users;
	}

}
