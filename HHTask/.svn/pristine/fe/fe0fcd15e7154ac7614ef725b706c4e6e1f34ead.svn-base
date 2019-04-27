package com.task.entity.caiwu.noncore;

import java.io.Serializable;

import com.task.util.FieldMeta;

/**
 * 非主营业务应收明细
 * @author 贾辉辉
 * 表名：ta_fin_caiwu_nonCoreReceivablesDetail
 */

public class NonCoreReceivablesDetail implements Serializable{
	private Integer id;
	private NonCoreReceivables nonCoreReceivables;
	@FieldMeta(name = "科目")
	private String kemu;//科目（水，电，设备，房租，管理）
	private String zhaiyao;//摘要
	@FieldMeta(name = "账期")
	private String zhangqi;//账期 月份
	@FieldMeta(name = "收款状态")
	private String zhuangtai;//待确认/取消/未收/未收清/已收(财务确认)
	private String jiluTime;//抄表日期
	@FieldMeta(name = "添加时间")
	private String shangchuanTime;//上传时间
	private String photoPath;//附件照片
	private String chaobiaoren;//抄表人  房产租赁
	private String shiduan;//使用时段
	
	private Float effectivePrice;//实效单价   房产租赁
	private Float lastbiaoshu;//上次读表数     房产租赁
	private Float thisbiaoshu;//本次读表数    房产租赁
	private Float biaoshu;//记录读表数               房产租赁

	@FieldMeta(name = "应收金额")
	private Float yingfuJine;//应收金额 （房产租赁/设备租赁）
	@FieldMeta(name = "实收金额")
	private Float realfuJine;//实收金额 （房产租赁/设备租赁）
	
	private String saveCode;//提交人工号
	@FieldMeta(name = "提交人")
	private String saveUser;//提交人
	private String more;//备注
	private Integer epId;//流程id
	private String status;//审批状态
	private String queren;//确认收款人
	private String querenTime;//确认时间
	private String fujianQueren;//加章后确认附件
	private String fujian2Status;//加章后确认附件状态(待上传、已上传)

	private Integer scpId;//收付款管理汇总表 应收ID
	
	private String showNonCoreReceivables;//显示主表信息
	private String chengzufang;//承租方(查询)
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getChengzufang() {
		return chengzufang;
	}
	public void setChengzufang(String chengzufang) {
		this.chengzufang = chengzufang;
	}
	public String getShowNonCoreReceivables() {
		return showNonCoreReceivables;
	}
	public void setShowNonCoreReceivables(String showNonCoreReceivables) {
		this.showNonCoreReceivables = showNonCoreReceivables;
	}
	/**
	 * @return the scpId
	 */
	public Integer getScpId() {
		return scpId;
	}
	/**
	 * @param scpId the scpId to set
	 */
	public void setScpId(Integer scpId) {
		this.scpId = scpId;
	}
	public String getFujianQueren() {
		return fujianQueren;
	}
	public void setFujianQueren(String fujianQueren) {
		this.fujianQueren = fujianQueren;
	}
	public String getFujian2Status() {
		return fujian2Status;
	}
	public void setFujian2Status(String fujian2Status) {
		this.fujian2Status = fujian2Status;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getQueren() {
		return queren;
	}
	public void setQueren(String queren) {
		this.queren = queren;
	}
	public String getQuerenTime() {
		return querenTime;
	}
	public void setQuerenTime(String querenTime) {
		this.querenTime = querenTime;
	}
	public String getSaveCode() {
		return saveCode;
	}
	public void setSaveCode(String saveCode) {
		this.saveCode = saveCode;
	}
	public NonCoreReceivables getNonCoreReceivables() {
		return nonCoreReceivables;
	}
	public void setNonCoreReceivables(NonCoreReceivables nonCoreReceivables) {
		this.nonCoreReceivables = nonCoreReceivables;
	}
	public String getKemu() {
		return kemu;
	}
	public void setKemu(String kemu) {
		this.kemu = kemu;
	}
	public String getZhaiyao() {
		return zhaiyao;
	}
	public void setZhaiyao(String zhaiyao) {
		this.zhaiyao = zhaiyao;
	}
	public String getZhangqi() {
		return zhangqi;
	}
	public void setZhangqi(String zhangqi) {
		this.zhangqi = zhangqi;
	}
	public String getZhuangtai() {
		return zhuangtai;
	}
	public void setZhuangtai(String zhuangtai) {
		this.zhuangtai = zhuangtai;
	}
	public String getJiluTime() {
		return jiluTime;
	}
	public void setJiluTime(String jiluTime) {
		this.jiluTime = jiluTime;
	}
	public String getShangchuanTime() {
		return shangchuanTime;
	}
	public void setShangchuanTime(String shangchuanTime) {
		this.shangchuanTime = shangchuanTime;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public String getChaobiaoren() {
		return chaobiaoren;
	}
	public void setChaobiaoren(String chaobiaoren) {
		this.chaobiaoren = chaobiaoren;
	}
	public String getShiduan() {
		return shiduan;
	}
	public void setShiduan(String shiduan) {
		this.shiduan = shiduan;
	}
	public Float getEffectivePrice() {
		return effectivePrice;
	}
	public void setEffectivePrice(Float effectivePrice) {
		this.effectivePrice = effectivePrice;
	}
	public Float getLastbiaoshu() {
		if(null!=lastbiaoshu){
			return Float.parseFloat(String.format("%.2f", lastbiaoshu));
		}
		return lastbiaoshu;
	}
	public void setLastbiaoshu(Float lastbiaoshu) {
		this.lastbiaoshu = lastbiaoshu;
	}
	public Float getThisbiaoshu() {
		if(null!=thisbiaoshu){
			return Float.parseFloat(String.format("%.2f", thisbiaoshu));
		}
		return thisbiaoshu;
	}
	public void setThisbiaoshu(Float thisbiaoshu) {
		this.thisbiaoshu = thisbiaoshu;
	}
	public Float getBiaoshu() {
		if(null!=biaoshu){
			return Float.parseFloat(String.format("%.2f", biaoshu));
		}
		return biaoshu;
	}
	public void setBiaoshu(Float biaoshu) {
		this.biaoshu = biaoshu;
	}
	public Float getYingfuJine() {
		if(null!=yingfuJine){
			return Float.parseFloat(String.format("%.2f", yingfuJine));
		}
		return yingfuJine;
	}
	public void setYingfuJine(Float yingfuJine) {
		this.yingfuJine = yingfuJine;
	}
	public Float getRealfuJine() {
		if(null!=realfuJine){
			return Float.parseFloat(String.format("%.2f", realfuJine));
		}
		return realfuJine;
	}
	public void setRealfuJine(Float realfuJine) {
		this.realfuJine = realfuJine;
	}
	public String getSaveUser() {
		return saveUser;
	}
	public void setSaveUser(String saveUser) {
		this.saveUser = saveUser;
	}
	public String getMore() {
		return more;
	}
	public void setMore(String more) {
		this.more = more;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	
}

