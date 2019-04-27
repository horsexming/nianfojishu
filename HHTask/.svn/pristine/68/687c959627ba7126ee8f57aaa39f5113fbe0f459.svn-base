package com.task.action;

import java.io.File;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.AddUserServer;
import com.task.entity.Users;

public class AddUserAction extends ActionSupport {
	private Users users;
	private AddUserServer addUserServer;
	private File addusers;
	private String addusersContentType;// 文件类型
	private String addusersFileName;// 文件名称
	private String errorMessage;

	public String addUser() {
		errorMessage = addUserServer.addUser(addusers); ;
		return ERROR;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Users getUsers() {
		return users;
	}

	public void setAddUserServer(AddUserServer addUserServer) {
		this.addUserServer = addUserServer;
	}

	public AddUserServer getAddUserServer() {
		return addUserServer;
	}

	public File getAddusers() {
		return addusers;
	}

	public void setAddusers(File addusers) {
		this.addusers = addusers;
	}

	public String getAddusersContentType() {
		return addusersContentType;
	}

	public void setAddusersContentType(String addusersContentType) {
		this.addusersContentType = addusersContentType;
	}

	public String getAddusersFileName() {
		return addusersFileName;
	}

	public void setAddusersFileName(String addusersFileName) {
		this.addusersFileName = addusersFileName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
