package com.task.entity.barandqr;

import java.io.Serializable;

public class AirMachine implements Serializable{
 private static final long serialVersionUID = 1L;
 private Integer id;
 private String switchIp;//开关Ip
 private String switchPort;//开关端口
 private String valueIp;//传值Ip
 private String valuePort;//传值端口
 private String name;//机器名称
 private String addTime;//添加时间
 private String addUser;//添加人
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getSwitchIp() {
	return switchIp;
}
public void setSwitchIp(String switchIp) {
	this.switchIp = switchIp;
}
public String getValueIp() {
	return valueIp;
}
public void setValueIp(String valueIp) {
	this.valueIp = valueIp;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSwitchPort() {
	return switchPort;
}
public void setSwitchPort(String switchPort) {
	this.switchPort = switchPort;
}
public String getValuePort() {
	return valuePort;
}
public void setValuePort(String valuePort) {
	this.valuePort = valuePort;
}
public String getAddTime() {
	return addTime;
}
public void setAddTime(String addTime) {
	this.addTime = addTime;
}
public String getAddUser() {
	return addUser;
}
public void setAddUser(String addUser) {
	this.addUser = addUser;
}
 
}
