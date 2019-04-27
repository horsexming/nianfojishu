package com.task.entity.project;
/**
 * 投资名单 ta_pro_Investor
 * @author txb
 *
 */
public class Investor implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;//
	private Integer userId;
	private String name;//名字
	private String code;//工号
	private String cardNumber;//卡号
	private String dept;//部门
	private String type;//类型(正常,黑名单)
	private Float hasMoney;//账号资金
	private Float djMoney;//冻结金额
	private Float kyMoney;//可用资金
	private Integer zanCount;//点赞次数
	private Integer tocaoCount;//吐槽次数
	private Integer zzId;//本人推选的组长Id(页面使用)
	private String isSelected;//是否被选中(yes,no null表示no)（页面使用）
	
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Float getHasMoney() {
		return hasMoney;
	}
	public void setHasMoney(Float hasMoney) {
		this.hasMoney = hasMoney;
	}
	public Float getDjMoney() {
		return djMoney;
	}
	public void setDjMoney(Float djMoney) {
		this.djMoney = djMoney;
	}
	public Float getKyMoney() {
		return kyMoney;
	}
	public void setKyMoney(Float kyMoney) {
		this.kyMoney = kyMoney;
	}
	public Integer getZanCount() {
		return zanCount;
	}
	public void setZanCount(Integer zanCount) {
		this.zanCount = zanCount;
	}
	public Integer getTocaoCount() {
		return tocaoCount;
	}
	public void setTocaoCount(Integer tocaoCount) {
		this.tocaoCount = tocaoCount;
	}
	public Integer getZzId() {
		return zzId;
	}
	public void setZzId(Integer zzId) {
		this.zzId = zzId;
	}
	public String getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}
	
	
}
