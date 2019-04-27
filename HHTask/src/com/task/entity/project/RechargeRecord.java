package com.task.entity.project;
/**
 * 充值记录表 ta_pro_RechargeRecord
 * @author txb
 *
 */
public class RechargeRecord implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private String time;
	private Float money;
	private String type;
	private Integer investorId;//投资人Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Float getMoney() {
		return money;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getInvestorId() {
		return investorId;
	}
	public void setInvestorId(Integer investorId) {
		this.investorId = investorId;
	}
	
	
	
	
}
