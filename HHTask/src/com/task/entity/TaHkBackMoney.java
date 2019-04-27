package com.task.entity;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.util.FieldMeta;

/**
 * TaHkHuikuan generated by MyEclipse Persistence Tools
 * 回款单（表：ta_hk_backMoney）
 */

public class TaHkBackMoney implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private TaHkHuikuan taHkHuikuan;
	@FieldMeta(name="发票单号")
	private String hkbmNum;//发票单号
	@FieldMeta(name="回款时间")
	private String hkbmDate;
	private String hkbmPerson;
	private String hkbmClientUser;
	@FieldMeta(name="客户名称")
	private String hkbmClientCom;//客户名称
	private String hkbmResult;//说明
	private String hkbmFile;//上传文件
	private String hkbmMore;
	@FieldMeta(name="回款金额")
    private Float hkbmMoney;//回款金额
	@FieldMeta(name="回款数量")
    private Float hkbmCount;//回款数量
	@FieldMeta(name="币种")
    private String hkbmMoneyUnit;//币种
	@FieldMeta(name="银行账户")
    private String bankAccount;//银行账户
	@FieldMeta(name="所属银行")
    private String fatherSubName;//父类名字
    private String subId;//科目余额Id
    private Integer epId;
    private String epstatus;//
    private List<TaHkPartBackMoney> taHkPartBackMoneys;
	// Constructors
    
	/** default constructor */
	public TaHkBackMoney() {
	}

	public String getHkbmMoneyUnit() {
		return hkbmMoneyUnit;
	}

	public void setHkbmMoneyUnit(String hkbmMoneyUnit) {
		this.hkbmMoneyUnit = hkbmMoneyUnit;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@JSONField(serialize = false)
	public TaHkHuikuan getTaHkHuikuan() {
		return taHkHuikuan;
	}

	public void setTaHkHuikuan(TaHkHuikuan taHkHuikuan) {
		this.taHkHuikuan = taHkHuikuan;
	}

	public String getHkbmNum() {
		return hkbmNum;
	}

	public void setHkbmNum(String hkbmNum) {
		this.hkbmNum = hkbmNum;
	}

	public String getHkbmDate() {
		return hkbmDate;
	}

	public void setHkbmDate(String hkbmDate) {
		this.hkbmDate = hkbmDate;
	}

	public String getHkbmPerson() {
		return hkbmPerson;
	}

	public void setHkbmPerson(String hkbmPerson) {
		this.hkbmPerson = hkbmPerson;
	}

	public String getHkbmClientUser() {
		return hkbmClientUser;
	}

	public void setHkbmClientUser(String hkbmClientUser) {
		this.hkbmClientUser = hkbmClientUser;
	}

	public String getHkbmClientCom() {
		return hkbmClientCom;
	}

	public void setHkbmClientCom(String hkbmClientCom) {
		this.hkbmClientCom = hkbmClientCom;
	}

	public String getHkbmResult() {
		return hkbmResult;
	}

	public void setHkbmResult(String hkbmResult) {
		this.hkbmResult = hkbmResult;
	}

	public String getHkbmFile() {
		return hkbmFile;
	}

	public void setHkbmFile(String hkbmFile) {
		this.hkbmFile = hkbmFile;
	}

	public String getHkbmMore() {
		return hkbmMore;
	}

	public void setHkbmMore(String hkbmMore) {
		this.hkbmMore = hkbmMore;
	}

	public Float getHkbmMoney() {
		return hkbmMoney;
	}

	public void setHkbmMoney(Float hkbmMoney) {
		this.hkbmMoney = hkbmMoney;
	}

	public Float getHkbmCount() {
		return hkbmCount;
	}

	public void setHkbmCount(Float hkbmCount) {
		this.hkbmCount = hkbmCount;
	}

	public List<TaHkPartBackMoney> getTaHkPartBackMoneys() {
		return taHkPartBackMoneys;
	}

	public void setTaHkPartBackMoneys(List<TaHkPartBackMoney> taHkPartBackMoneys) {
		this.taHkPartBackMoneys = taHkPartBackMoneys;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public String getFatherSubName() {
		return fatherSubName;
	}

	public void setFatherSubName(String fatherSubName) {
		this.fatherSubName = fatherSubName;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	public String getEpstatus() {
		return epstatus;
	}

	public void setEpstatus(String epstatus) {
		this.epstatus = epstatus;
	}
	

}