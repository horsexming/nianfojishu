package com.task.entity.project;

/**
 * 项目人员明细
 * @author wcy
 *
 */
public class ProjectManageYfpd implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private Integer userId;
	private String userCode;
	private String userName;
	private Integer proId; //项目id ProjectManageyf
	private String startTime;
	private Double money; //实际金额
	private String proportion; //项目池占比
	private Integer weight;//权重
	
	private Integer year; //年份  --页面传值
	private String poolName;//项目池名称  --页面传值
	private String poolNum; //项目池编号  --页面传值
	private Integer poolId;  //项目池Id  --页面传值
	private String proName;//项目名称  --页面传值
	private String proNum;//项目编号  --页面传值
	
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
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getProId() {
		return proId;
	}
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getProportion() {
		return proportion;
	}
	public void setProportion(String proportion) {
		this.proportion = proportion;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getPoolName() {
		return poolName;
	}
	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}
	public String getPoolNum() {
		return poolNum;
	}
	public void setPoolNum(String poolNum) {
		this.poolNum = poolNum;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProNum() {
		return proNum;
	}
	public void setProNum(String proNum) {
		this.proNum = proNum;
	}
	public Integer getPoolId() {
		return poolId;
	}
	public void setPoolId(Integer poolId) {
		this.poolId = poolId;
	}
	
	
	
}