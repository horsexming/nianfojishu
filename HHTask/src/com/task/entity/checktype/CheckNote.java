package com.task.entity.checktype;

public class CheckNote {
	private Integer id;
	private String loginCode;// 登录人工号
	private String firstPersonCode;// 负责人工号
	private String firstPerson;// 负责人名字
	private String depert;// 部门
	private String url;// 图片路径
	private CheckType checkType;// 检查类别
	private String status;// 状态
	private String describe;// 描述
	private String yesorno;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstPersonCode() {
		return firstPersonCode;
	}

	public void setFirstPersonCode(String firstPersonCode) {
		this.firstPersonCode = firstPersonCode;
	}

	public String getFirstPerson() {
		return firstPerson;
	}

	public void setFirstPerson(String firstPerson) {
		this.firstPerson = firstPerson;
	}

	public String getDepert() {
		return depert;
	}

	public void setDepert(String depert) {
		this.depert = depert;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public CheckType getCheckType() {
		return checkType;
	}

	public void setCheckType(CheckType checkType) {
		this.checkType = checkType;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getYesorno() {
		return yesorno;
	}

	public void setYesorno(String yesorno) {
		this.yesorno = yesorno;
	}

}
