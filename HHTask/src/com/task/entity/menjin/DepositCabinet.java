package com.task.entity.menjin;

import java.io.Serializable;

/**
 * 存柜表 2016-06-04
 * 
 * @author Li_Cong 表名 ta_mj_DepositCabinet 将物品存入物品柜凭证表(根据入库领用物品自动生成，无法修改删除)
 */
public class DepositCabinet implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String depArticleName;// 存物品名称
	private String depArticleFormat;// 存物品规格

	private String artOACoding;// OA编码
	private String artUnit;// 物品单位
	private Integer artQuantity;// 物品数量（总）

	private String applyDept;// 申购部门
	private Integer actualDepositQuantity;// 实际存入数量
	private Integer alreadyReceivedQuantity;// 已领取数量
	private String depositStatus;// 存入状态（待入柜/入柜中/已入柜）

	private String printStatus;// 是否存柜状态（1：应该存/0：不用存）
	private Integer printUserId;// 打印人Id
	private String printName;// 打印人名称
	private String printCardId;// 打印人卡号
	private String printDate;// 打印日期

	private String addTime;// 添加时间
	private String depositTime;// 存入时间

	// get set

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepArticleName() {
		return depArticleName;
	}

	public void setDepArticleName(String depArticleName) {
		this.depArticleName = depArticleName;
	}

	public String getDepArticleFormat() {
		return depArticleFormat;
	}

	public void setDepArticleFormat(String depArticleFormat) {
		this.depArticleFormat = depArticleFormat;
	}

	public String getArtOACoding() {
		return artOACoding;
	}

	public void setArtOACoding(String artOACoding) {
		this.artOACoding = artOACoding;
	}

	public String getArtUnit() {
		return artUnit;
	}

	public void setArtUnit(String artUnit) {
		this.artUnit = artUnit;
	}

	public String getApplyDept() {
		return applyDept;
	}

	public void setApplyDept(String applyDept) {
		this.applyDept = applyDept;
	}

	public String getDepositStatus() {
		return depositStatus;
	}

	public void setDepositStatus(String depositStatus) {
		this.depositStatus = depositStatus;
	}

	public String getPrintStatus() {
		return printStatus;
	}

	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}

	public Integer getPrintUserId() {
		return printUserId;
	}

	public void setPrintUserId(Integer printUserId) {
		this.printUserId = printUserId;
	}

	public String getPrintName() {
		return printName;
	}

	public void setPrintName(String printName) {
		this.printName = printName;
	}

	public String getPrintCardId() {
		return printCardId;
	}

	public void setPrintCardId(String printCardId) {
		this.printCardId = printCardId;
	}

	public String getPrintDate() {
		return printDate;
	}

	public void setPrintDate(String printDate) {
		this.printDate = printDate;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getDepositTime() {
		return depositTime;
	}

	public void setDepositTime(String depositTime) {
		this.depositTime = depositTime;
	}

	public Integer getArtQuantity() {
		return artQuantity;
	}

	public void setArtQuantity(Integer artQuantity) {
		this.artQuantity = artQuantity;
	}

	public Integer getActualDepositQuantity() {
		return actualDepositQuantity;
	}

	public void setActualDepositQuantity(Integer actualDepositQuantity) {
		this.actualDepositQuantity = actualDepositQuantity;
	}

	public Integer getAlreadyReceivedQuantity() {
		return alreadyReceivedQuantity;
	}

	public void setAlreadyReceivedQuantity(Integer alreadyReceivedQuantity) {
		this.alreadyReceivedQuantity = alreadyReceivedQuantity;
	}

}
