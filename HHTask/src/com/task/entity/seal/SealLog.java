package com.task.entity.seal;

import java.io.Serializable;

import com.task.util.FieldMeta;

/**
 * 印章使用情况 (表名 ： seallog)
 * 
 * @author txb
 * 
 */
public class SealLog  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	@FieldMeta(name = "印章名称", order = 1)
	private String name;// 印章名称
	@FieldMeta(name = "申请单编号", order = 2)
	private String number;// 申请单编号
	@FieldMeta(name = "申请人", order = 3)
	private String userName;// 申请人名字
	private String userCode;// 申请人工号
	private String userDept;// 申请人部门
	private String useFor;// 用途
	private String fujian;// 附件
	private String isSave;// 是否存档（yes,no）
	private String saveNumber;// 系统自动生成的存档编号
	private String makeSure;// 确认使用(yes,no null等价no)
	private String aduitStatus;// 审核状态(未审批、审批中、同意、打回)
	private Integer epId;// 流程id

	private String isConfidential;// 是否机密 lc_20150829
	private String fujian2;// 使用完成后的附件 lc_20160829
	private String fujian2Status;// 附件上传状态(待上传/已上传/待存档/已存档) lc_20160829
	private String fujian2Time;//附件上传时间
	private String type;//(合同评审;//)
	@FieldMeta(name = "存档类型", order = 4)
	private String cunType;//存类型(电子档/原件/复印件)lc_20180604
	private String isCunType;//是否存档到文件柜(yes/no)lc_20180604
	private String isCunTime;//实际存档时间lc_20180604
	private Integer epId2;//合同评审epId
	private String aduitStatus2;//合同评审  审核状态(未审批、审批中、同意、打回)
	/**
	 * 与档案关联的信息
	 * 
	 * @return
	 */
	private Integer documentId;// 档案的id
	private String documentType;// 文件类别（销售/订单/采购合同/人事合同/其他）
	private String documentName;// 文件名称
	private String chargePerson;// 负责人
	private String inputPeople;// 录入人
	private String retime;// 预计上传时间
	private String sxtime;// 档案过期时间

	public SealLog(String userName, String userCode) {
		super();
		this.userName = userName;
		this.userCode = userCode;
	}

	public SealLog() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the sxtime
	 */
	public String getSxtime() {
		return sxtime;
	}

	/**
	 * @param sxtime the sxtime to set
	 */
	public void setSxtime(String sxtime) {
		this.sxtime = sxtime;
	}

	/**
	 * @return the cunType
	 */
	public String getCunType() {
		return cunType;
	}

	/**
	 * @param cunType the cunType to set
	 */
	public void setCunType(String cunType) {
		this.cunType = cunType;
	}

	/**
	 * @return the isCunType
	 */
	public String getIsCunType() {
		return isCunType;
	}

	/**
	 * @param isCunType the isCunType to set
	 */
	public void setIsCunType(String isCunType) {
		this.isCunType = isCunType;
	}

	/**
	 * @return the isCunTime
	 */
	public String getIsCunTime() {
		return isCunTime;
	}

	/**
	 * @param isCunTime the isCunTime to set
	 */
	public void setIsCunTime(String isCunTime) {
		this.isCunTime = isCunTime;
	}

	public String getFujian2Status() {
		return fujian2Status;
	}

	public void setFujian2Status(String fujian2Status) {
		this.fujian2Status = fujian2Status;
	}

	public String getFujian2Time() {
		return fujian2Time;
	}

	public void setFujian2Time(String fujian2Time) {
		this.fujian2Time = fujian2Time;
	}

	public String getFujian2() {
		return fujian2;
	}

	public void setFujian2(String fujian2) {
		this.fujian2 = fujian2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public String getUseFor() {
		return useFor;
	}

	public void setUseFor(String useFor) {
		this.useFor = useFor;
	}

	public String getIsSave() {
		return isSave;
	}

	public void setIsSave(String isSave) {
		this.isSave = isSave;
	}

	public String getSaveNumber() {
		return saveNumber;
	}

	public void setSaveNumber(String saveNumber) {
		this.saveNumber = saveNumber;
	}

	public String getAduitStatus() {
		return aduitStatus;
	}

	public void setAduitStatus(String aduitStatus) {
		this.aduitStatus = aduitStatus;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	public String getFujian() {
		return fujian;
	}

	public void setFujian(String fujian) {
		this.fujian = fujian;
	}

	public String getMakeSure() {
		return makeSure;
	}

	public void setMakeSure(String makeSure) {
		this.makeSure = makeSure;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getUserDept() {
		return userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

	public String getChargePerson() {
		return chargePerson;
	}

	public void setChargePerson(String chargePerson) {
		this.chargePerson = chargePerson;
	}

	public String getInputPeople() {
		return inputPeople;
	}

	public void setInputPeople(String inputPeople) {
		this.inputPeople = inputPeople;
	}

	public String getRetime() {
		return retime;
	}

	public void setRetime(String retime) {
		this.retime = retime;
	}

	public Integer getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Integer documentId) {
		this.documentId = documentId;
	}

	public String getIsConfidential() {
		return isConfidential;
	}

	public void setIsConfidential(String isConfidential) {
		this.isConfidential = isConfidential;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getEpId2() {
		return epId2;
	}

	public void setEpId2(Integer epId2) {
		this.epId2 = epId2;
	}

	public String getAduitStatus2() {
		return aduitStatus2;
	}

	public void setAduitStatus2(String aduitStatus2) {
		this.aduitStatus2 = aduitStatus2;
	}

}
