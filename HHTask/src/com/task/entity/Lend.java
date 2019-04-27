package com.task.entity;

import java.util.Date;


/**
 * 库存借用记录表
 * @author Administrator
 *
 */
public class Lend  implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private String goodsMarkId;//件号(编号)
	private String goodsLotId;// 批次
	private String goodsFullName;// 名称
	private Float num;//借用数量
	private Float giveBackNum;// 未归还数量
	private String unit;// 单位
	private String format;// 规格
	private String wgType;//物料类别
	private String storehouse; // 仓库名称
	private String goodHouse;//仓区名称
	private String wareHouse; // 库位名称
	private String date;// 日期
	private String time;//具体
	private String processPieceNum;// 加工件号
	private String remark; // 备注
	private String cardNum;// 卡号
	private String peopleName; // 借主	
	private String useingCode;//借用人工号
	private String dept;//借用人 部门
	private String number;// 编号
	private String mix;// 合成
	private String state; // 状态（已还/未还）
	private String cqStatus; // 存取状态(待取，已取，取中)16-12-08 LC
	private Integer ware_id; // 操作的库位ID 16-12-08 LC
	private Integer oa_id; // 对应Oa采购信息ID 16-12-08 LC
	private Integer goodsId; // 库存Id
	private Integer adminId;//管理员iD
	private String admin;//管理员
	private String fileName;//文件名
	private String place;//工位或供应商
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the peopleName
	 */
	public String getPeopleName() {
		return peopleName;
	}
	/**
	 * @param peopleName the peopleName to set
	 */
	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}
	/**
	 * @return the date
	 */
	
	
	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}
	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	/**
	 * @return the num
	 */
	public Float getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(Float num) {
		this.num = num;
	}
	/**
	 * @return the storehouse
	 */
	public String getStorehouse() {
		return storehouse;
	}
	/**
	 * @param storehouse the storehouse to set
	 */
	public void setStorehouse(String storehouse) {
		this.storehouse = storehouse;
	}
	/**
	 * @return the goodHouse
	 */
	public String getGoodHouse() {
		return goodHouse;
	}
	/**
	 * @param goodHouse the goodHouse to set
	 */
	public void setGoodHouse(String goodHouse) {
		this.goodHouse = goodHouse;
	}
	/**
	 * @return the wareHouse
	 */
	public String getWareHouse() {
		return wareHouse;
	}
	/**
	 * @param wareHouse the wareHouse to set
	 */
	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the cardNum
	 */
	public String getCardNum() {
		return cardNum;
	}
	/**
	 * @param cardNum the cardNum to set
	 */
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	/**
	 * @return the dept
	 */
	public String getDept() {
		return dept;
	}
	/**
	 * @param dept the dept to set
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * @return the mix
	 */
	public String getMix() {
		return mix;
	}
	/**
	 * @param mix the mix to set
	 */
	public void setMix(String mix) {
		this.mix = mix;
	}
	/**
	 * @return the processPieceNum
	 */
	public String getProcessPieceNum() {
		return processPieceNum;
	}
	/**
	 * @param processPieceNum the processPieceNum to set
	 */
	public void setProcessPieceNum(String processPieceNum) {
		this.processPieceNum = processPieceNum;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the giveBackNum
	 */
	public Float getGiveBackNum() {
		return giveBackNum;
	}
	/**
	 * @param giveBackNum the giveBackNum to set
	 */
	public void setGiveBackNum(Float giveBackNum) {
		this.giveBackNum = giveBackNum;
	}
	
	public String getCqStatus() {
		return cqStatus;
	}
	/**
	 * @param cqStatus the cqStatus to set
	 */
	public void setCqStatus(String cqStatus) {
		this.cqStatus = cqStatus;
	}
	/**
	 * @return the ware_id
	 */
	public Integer getWare_id() {
		return ware_id;
	}
	/**
	 * @param wareId the ware_id to set
	 */
	public void setWare_id(Integer wareId) {
		ware_id = wareId;
	}
	/**
	 * @return the oa_id
	 */
	public Integer getOa_id() {
		return oa_id;
	}
	/**
	 * @param oaId the oa_id to set
	 */
	public void setOa_id(Integer oaId) {
		oa_id = oaId;
	}
	/**
	 * @return the goodsId
	 */
	
	
	public Lend(Integer id, String peopleName, String date,
			String format, Float num, String storehouse, String goodHouse,
			String wareHouse, String remark, String cardNum, String dept,
			String number, String unit, String mix, String processPieceNum,
			String state, Float giveBackNum, String cqStatus,
			Integer wareId, Integer oaId, Integer goodsId) {
		super();
		this.id = id;
		this.peopleName = peopleName;
		this.setDate(date);
		
		this.format = format;
		this.num = num;
		this.storehouse = storehouse;
		this.goodHouse = goodHouse;
		this.wareHouse = wareHouse;
		this.remark = remark;
		this.cardNum = cardNum;
		this.dept = dept;
		this.number = number;
		this.unit = unit;
		this.mix = mix;
		this.processPieceNum = processPieceNum;
		this.state = state;
		this.giveBackNum = giveBackNum;
		
		this.cqStatus = cqStatus;
		ware_id = wareId;
		oa_id = oaId;
		this.setGoodsId(goodsId);
	}
	/**
	 * @return the goodsMarkId
	 */
	public String getGoodsMarkId() {
		return goodsMarkId;
	}
	/**
	 * @param goodsMarkId the goodsMarkId to set
	 */
	public void setGoodsMarkId(String goodsMarkId) {
		this.goodsMarkId = goodsMarkId;
	}
	/**
	 * @return the goodsFullName
	 */
	public String getGoodsFullName() {
		return goodsFullName;
	}
	/**
	 * @param goodsFullName the goodsFullName to set
	 */
	public void setGoodsFullName(String goodsFullName) {
		this.goodsFullName = goodsFullName;
	}
	/**
	 * @return the wgType
	 */
	public String getWgType() {
		return wgType;
	}
	/**
	 * @param wgType the wgType to set
	 */
	public void setWgType(String wgType) {
		this.wgType = wgType;
	}
	public Lend(){
		
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getAdmin() {
		return admin;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDate() {
		return date;
	}
	public void setGoodsLotId(String goodsLotId) {
		this.goodsLotId = goodsLotId;
	}
	public String getGoodsLotId() {
		return goodsLotId;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTime() {
		return time;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getUseingCode() {
		return useingCode;
	}
	public void setUseingCode(String useingCode) {
		this.useingCode = useingCode;
	}
	
	
	
}
