package com.task.entity.menjin;

import java.io.Serializable;

import com.task.util.FieldMeta;

/**
 * 内部员工车辆信息表
 * 
 * @author Li_Cong 表名 ta_mj_InEmployeeCarInfor 管理内部员工/常访车辆，绑定员工信息
 */
public class InEmployeeCarInfor implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String addCode;// 添加人工号
	private String addName;// 添加人名称

	private String ncode;// 内部员工工号
	@FieldMeta(name = "被访人姓名")
	private String name;// 内部员工姓名
	@FieldMeta(name = "被访人部门")
	private String ndept;// 内部员工部门
	private String ncardId;// 内部员卡号

	@FieldMeta(name = "常访人姓名")
	private String oftenname;// 常访人姓名
	@FieldMeta(name = "常访人性别")
	private String oftenSex;// 常访人性别
	@FieldMeta(name = "常访人手机号码")
	private String oftenTel;// 常访人手机号码
	@FieldMeta(name = "常访原因")
	private String oftenInfor;// 常访原因

	@FieldMeta(name = "常访人车牌号")
	private String nplates;// 车牌号
	private String carType;// 车辆类型（个人/公车）
	private String carModels;// 车型品牌
	private String carColor;// 车颜色
	private Integer car_User_Id;// 对应用户codeId
	private String rtxMessage;// 车辆进出是否发送RTX消息（下拉）
	private String carFiles;// 汽车行驶证附件(表中存文件地址)
	private String whiteCar;// 白名单车辆(是/否)
	private String carInCangType;// 车辆类型（内部/常访）
	@FieldMeta(name = "添加时间")
	private String addTime;// 添加时间
	private String updateTime;// 修改时间
	@FieldMeta(name = "失效日期")
	private String effectiveDate;// 失效日期

	private String borrowStatus;// 出街状态(出借/正常)
	private String nbdaijian;// 内部待检车辆（是/n）
	private String nbdaiSQ;// 内部待检车辆（是/否/n）坑爹的
	private String isKong;// 是否控制灯
	@FieldMeta(name = "申请状态")
	private String oftenStatus;// 常访申请状态
	private Integer epId;//

	private String temporaryName;// 临时使用人

	// 折旧费（公车）
	private Float price;// 价格（元）
	private Float zjDistance;// 折旧公里数
	private Float driveDistance;// 行驶公里数
	private Float drivePrice;// 每公里价钱（不含折旧费）
	// get set

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsKong() {
		return isKong;
	}

	public void setIsKong(String isKong) {
		this.isKong = isKong;
	}

	public String getNbdaiSQ() {
		return nbdaiSQ;
	}

	public void setNbdaiSQ(String nbdaiSQ) {
		this.nbdaiSQ = nbdaiSQ;
	}

	public String getNcode() {
		return ncode;
	}

	public void setNcode(String ncode) {
		this.ncode = ncode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getCarModels() {
		return carModels;
	}

	public void setCarModels(String carModels) {
		this.carModels = carModels;
	}

	public String getCarColor() {
		return carColor;
	}

	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getCar_User_Id() {
		return car_User_Id;
	}

	public void setCar_User_Id(Integer carUserId) {
		car_User_Id = carUserId;
	}

	public String getRtxMessage() {
		return rtxMessage;
	}

	public void setRtxMessage(String rtxMessage) {
		this.rtxMessage = rtxMessage;
	}

	public String getCarFiles() {
		return carFiles;
	}

	public void setCarFiles(String carFiles) {
		this.carFiles = carFiles;
	}

	public String getWhiteCar() {
		return whiteCar;
	}

	public void setWhiteCar(String whiteCar) {
		this.whiteCar = whiteCar;
	}

	public String getNplates() {
		return nplates;
	}

	public void setNplates(String nplates) {
		this.nplates = nplates;
	}

	public String getCarInCangType() {
		return carInCangType;
	}

	public void setCarInCangType(String carInCangType) {
		this.carInCangType = carInCangType;
	}

	public String getAddCode() {
		return addCode;
	}

	public void setAddCode(String addCode) {
		this.addCode = addCode;
	}

	public String getAddName() {
		return addName;
	}

	public void setAddName(String addName) {
		this.addName = addName;
	}

	public String getOftenname() {
		return oftenname;
	}

	public void setOftenname(String oftenname) {
		this.oftenname = oftenname;
	}

	public String getOftenSex() {
		return oftenSex;
	}

	public void setOftenSex(String oftenSex) {
		this.oftenSex = oftenSex;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getNdept() {
		return ndept;
	}

	public void setNdept(String ndept) {
		this.ndept = ndept;
	}

	public String getOftenInfor() {
		return oftenInfor;
	}

	public void setOftenInfor(String oftenInfor) {
		this.oftenInfor = oftenInfor;
	}

	public String getNcardId() {
		return ncardId;
	}

	public void setNcardId(String ncardId) {
		this.ncardId = ncardId;
	}

	public String getBorrowStatus() {
		return borrowStatus;
	}

	public void setBorrowStatus(String borrowStatus) {
		this.borrowStatus = borrowStatus;
	}

	public String getOftenTel() {
		return oftenTel;
	}

	public void setOftenTel(String oftenTel) {
		this.oftenTel = oftenTel;
	}

	public String getOftenStatus() {
		return oftenStatus;
	}

	public void setOftenStatus(String oftenStatus) {
		this.oftenStatus = oftenStatus;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	public String getNbdaijian() {
		return nbdaijian;
	}

	public void setNbdaijian(String nbdaijian) {
		this.nbdaijian = nbdaijian;
	}

	public String getTemporaryName() {
		return temporaryName;
	}

	public void setTemporaryName(String temporaryName) {
		this.temporaryName = temporaryName;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getZjDistance() {
		return zjDistance;
	}

	public void setZjDistance(Float zjDistance) {
		this.zjDistance = zjDistance;
	}

	public Float getDriveDistance() {
		return driveDistance;
	}

	public void setDriveDistance(Float driveDistance) {
		this.driveDistance = driveDistance;
	}

	public Float getDrivePrice() {
		return drivePrice;
	}

	public void setDrivePrice(Float drivePrice) {
		this.drivePrice = drivePrice;
	}

}
