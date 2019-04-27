package com.task.entity;

import java.io.Serializable;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

/****
 * 角色表
 * @author 冯杨
 * @表名 ta_UserRole
 *
 */
public class UserRole implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;//角色名
	private String description;//描述
	private Set<Users> users;// 用户(多对多)
	private Set<ModuleFunction> moduleFunction;// 模块功能(多对多)
	
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
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@JSONField(serialize = false)
	public Set<Users> getUsers() {
		return users;
	}
	public void setUsers(Set<Users> users) {
		this.users = users;
	}
	
	@JSONField(serialize = false)
	public Set<ModuleFunction> getModuleFunction() {
		return moduleFunction;
	}
	public void setModuleFunction(Set<ModuleFunction> moduleFunction) {
		this.moduleFunction = moduleFunction;
	}

}
