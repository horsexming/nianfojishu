package com.task.entity.project;
/**
 * 研发项目负责人 ta_pro_ProjectManageyfUser
 * @author txb
 *
 */
public class ProjectManageyfUser implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private Integer userId;//
	private String userName;//
	private String userCode;//
	private String userDept;//
	private String usertype;//类型  、、项目主管，，参与人
	private Integer poolId;//项目池Id
	private Integer rootId;//主项目Id
	//private Integer yfId
	private String zpTime;//指派时间 
	private String yfDate; //预完成时间forecastSuccessDate
	private Integer yfUserId;//中间表id   --页面传值
	private String selfProportion;//个人占比
	private Double selfMoney;//个人所得金额
	private Integer weight;//个人权重
	//YfUser 表对应 ProjectManageyf
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public Integer getPoolId() {
		return poolId;
	}
	public void setPoolId(Integer poolId) {
		this.poolId = poolId;
	}
	public Integer getRootId() {
		return rootId;
	}
	public void setRootId(Integer rootId) {
		this.rootId = rootId;
	}
	public String getYfDate() {
		return yfDate;
	}
	public void setYfDate(String yfDate) {
		this.yfDate = yfDate;
	}
	public String getZpTime() {
		return zpTime;
	}
	public void setZpTime(String zpTime) {
		this.zpTime = zpTime;
	}
	public String getUserDept() {
		return userDept;
	}
	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}
	public Integer getYfUserId() {
		return yfUserId;
	}
	public void setYfUserId(Integer yfUserId) {
		this.yfUserId = yfUserId;
	}
	public String getSelfProportion() {
		return selfProportion;
	}
	public void setSelfProportion(String selfProportion) {
		this.selfProportion = selfProportion;
	}
	public Double getSelfMoney() {
		return selfMoney;
	}
	public void setSelfMoney(Double selfMoney) {
		this.selfMoney = selfMoney;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
}
