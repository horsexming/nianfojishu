package com.task.entity;

import java.io.Serializable;

/**
 * 用户附属表 表名：ta_password
 * 
 * @author 刘培
 */
@SuppressWarnings("serial")
public class Password implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String password;// 密码
	private String deptNumber;// 部门编码
	private String mailBox;// 邮箱
	private String phoneNumber;// 手机号
	private String telephone;// 电话
	private String qqNumber;// QQ号码
	private String wxNumber;// 微信号码
	private String rili;// 阳历/阴历
	private String shijiBirthDay;// 实际生日日期
	private String presentAddress;// 现住址
	private String picture;// 照片
	private String resume;// 简历附件
	private String contract;// 合同附件
	private String staffNature;// 员工性质
	private String censusNature;// 户籍性质
	private Users user;// 用户
	private String userStatus;// 用户状态(上传简历、签订合同、薪资处理)
	private String authorizationCode;// 邮箱密码
	private String mailHost; // 邮箱域名
	private String mailPropocal; // 邮箱协议
	private String loginPage;// 登录页面
	private String ssAccount;// 社保账号
	private String gjjAccount;// 公积金账号
	private String politicalAspects;//政治面貌
	private String degree;//学位
	private String graduationInstitutions;//毕业院校
	private String jobtitleGetTime;//职称取得时间
	private String speciality;//所学专业
	private String dateOfGraduation;//毕业日期
	private String maritalStatus;//婚姻状况
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQqNumber() {
		return qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	public String getWxNumber() {
		return wxNumber;
	}

	public void setWxNumber(String wxNumber) {
		this.wxNumber = wxNumber;
	}

	public String getRili() {
		return rili;
	}

	public void setRili(String rili) {
		this.rili = rili;
	}

	public String getShijiBirthDay() {
		return shijiBirthDay;
	}

	public void setShijiBirthDay(String shijiBirthDay) {
		this.shijiBirthDay = shijiBirthDay;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDeptNumber() {
		return deptNumber;
	}

	public void setDeptNumber(String deptNumber) {
		this.deptNumber = deptNumber;
	}

	public String getMailBox() {
		return mailBox;
	}

	public void setMailBox(String mailBox) {
		this.mailBox = mailBox;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPresentAddress() {
		return presentAddress;
	}

	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getStaffNature() {
		return staffNature;
	}

	public void setStaffNature(String staffNature) {
		this.staffNature = staffNature;
	}

	public String getCensusNature() {
		return censusNature;
	}

	public void setCensusNature(String censusNature) {
		this.censusNature = censusNature;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getAuthorizationCode() {
		return authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	public String getMailHost() {
		return mailHost;
	}

	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}

	public String getMailPropocal() {
		return mailPropocal;
	}

	public void setMailPropocal(String mailPropocal) {
		this.mailPropocal = mailPropocal;
	}

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public String getSsAccount() {
		return ssAccount;
	}

	public void setSsAccount(String ssAccount) {
		this.ssAccount = ssAccount;
	}

	public String getGjjAccount() {
		return gjjAccount;
	}

	public void setGjjAccount(String gjjAccount) {
		this.gjjAccount = gjjAccount;
	}

	public String getPoliticalAspects() {
		return politicalAspects;
	}

	public void setPoliticalAspects(String politicalAspects) {
		this.politicalAspects = politicalAspects;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getGraduationInstitutions() {
		return graduationInstitutions;
	}

	public void setGraduationInstitutions(String graduationInstitutions) {
		this.graduationInstitutions = graduationInstitutions;
	}

	public String getJobtitleGetTime() {
		return jobtitleGetTime;
	}

	public void setJobtitleGetTime(String jobtitleGetTime) {
		this.jobtitleGetTime = jobtitleGetTime;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getDateOfGraduation() {
		return dateOfGraduation;
	}

	public void setDateOfGraduation(String dateOfGraduation) {
		this.dateOfGraduation = dateOfGraduation;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}


}
