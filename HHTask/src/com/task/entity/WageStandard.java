package com.task.entity;

import java.io.Serializable;

/**
 * 工资标准表(表名:ta_fin_WageStandard)
 * 
 * @author 刘培
 * 
 */

public class WageStandard implements Serializable {
	// 员工号 姓名 部门 岗位工资 保密津贴 电话补贴 绩效考核工资 加班费 其他 应发工资
	// 病事旷等 养老保险 医疗保险 失业保险 公积金 午餐费 水电及房租扣款 补发工资 应交税金 实发工资

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer Id;
	private String code;// 工号(固)
	private String cardId;// 卡号
	private String userName;// 姓名(固)
	private String dept;// 部门(固)
	private Float gangweigongzi;// 岗位工资(固)
	private Float baomijintie;// 保密津贴(固)
	private Float dianhuabutie;// 电话补贴(动)
	private Float jixiaokaohegongzi;// 绩效考核工资(固)
	private Float jinenggongzi;// 技能工资(固)
	private Float gonglinggongzi;// 工龄工资(固)
	private Float yingfagongzi;// 应发工资[岗位工资(固)+保密津贴(固)+电话补贴(固)+绩效考核工资(固)+加班费+其他]
	
	private Float ssBase;// 社保基数
	private Float tongchoujin;// 养老保险(固)个人
	private Float yiliaobaoxian;// 医疗保险(固)个人
	private Float shiyebaoxian;// 失业保险(固)个人
	private Float gongshangbaoxian;// 工伤保险(固)个人
	private Float shengyubaoxian;// 生育保险(固)个人
	
	private Float dwtongchoujin;// 养老保险(固)单位
	private Float dwyiliaobaoxian;// 医疗保险(固)单位
	private Float dwshiyebaoxian;// 失业保险(固)单位
	private Float dwgongshangbaoxian;// 工伤保险(固)单位
	private Float dwshengyubaoxian;// 生育保险(固)单位
	
	private Float gjjBase;// 公积金基数
	private Float gongjijin;// 公积金(固)个人
	private Float dwgongjijin;// 公积金(固)单位
	
	private String localOrField;// 本地或外地
	private String cityOrCountryside;// 城市或农村
	private String inputPeople;// 录入人
	private String inputDate;// 处理时间
	private String updatePeople;// 修改人
	private String updateDate;// 修改时间(无用)
	private String standardStatus;// 工资模板状态(默认/历史)
	private String isOnWork;// 备注
	private String processStatus;// 流程状态(审核/同意/不同意/)
	private String chageStatus;// 调整状态(新增调整、基数调整,工资调整)
	private String bucha;// 补差(yes/no)
	private Double zxfjkc;//专项附加扣除总额
	// 新添字段
	private String personClass;// 人员类型
	private Float fangzufei;// 房租费(动)
	private Integer insuranceGoldId;// 保险缴纳比例id

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Float getGangweigongzi() {
		if (gangweigongzi != null) {
			return Float.parseFloat(String.format("%.2f", gangweigongzi));
		}
		return gangweigongzi;
	}

	public void setGangweigongzi(Float gangweigongzi) {
		this.gangweigongzi = gangweigongzi;
	}

	public Float getBaomijintie() {
		if (baomijintie != null) {
			return Float.parseFloat(String.format("%.2f", baomijintie));
		}
		return baomijintie;
	}

	public void setBaomijintie(Float baomijintie) {
		this.baomijintie = baomijintie;
	}

	public Float getDianhuabutie() {
		return dianhuabutie;
	}

	public void setDianhuabutie(Float dianhuabutie) {
		this.dianhuabutie = dianhuabutie;
	}

	public Float getJixiaokaohegongzi() {
		if (jixiaokaohegongzi != null) {
			return Float.parseFloat(String.format("%.2f", jixiaokaohegongzi));
		}
		return jixiaokaohegongzi;
	}

	public void setJixiaokaohegongzi(Float jixiaokaohegongzi) {
		this.jixiaokaohegongzi = jixiaokaohegongzi;
	}

	public Float getJinenggongzi() {
		if (jinenggongzi != null) {
			return Float.parseFloat(String.format("%.2f", jinenggongzi));
		}
		return jinenggongzi;
	}

	public void setJinenggongzi(Float jinenggongzi) {
		this.jinenggongzi = jinenggongzi;
	}

	public Float getGonglinggongzi() {
		return gonglinggongzi;
	}

	public void setGonglinggongzi(Float gonglinggongzi) {
		this.gonglinggongzi = gonglinggongzi;
	}

	public Float getYingfagongzi() {
		return yingfagongzi;
	}

	public void setYingfagongzi(Float yingfagongzi) {
		this.yingfagongzi = yingfagongzi;
	}

	public Float getTongchoujin() {
		return tongchoujin;
	}

	public void setTongchoujin(Float tongchoujin) {
		this.tongchoujin = tongchoujin;
	}

	public Float getYiliaobaoxian() {
		return yiliaobaoxian;
	}

	public void setYiliaobaoxian(Float yiliaobaoxian) {
		this.yiliaobaoxian = yiliaobaoxian;
	}

	public Float getShiyebaoxian() {
		return shiyebaoxian;
	}

	public void setShiyebaoxian(Float shiyebaoxian) {
		this.shiyebaoxian = shiyebaoxian;
	}

	public Float getGongjijin() {
		return gongjijin;
	}

	public void setGongjijin(Float gongjijin) {
		this.gongjijin = gongjijin;
	}

	public Float getGjjBase() {
		return gjjBase;
	}

	public void setGjjBase(Float gjjBase) {
		this.gjjBase = gjjBase;
	}

	public Float getSsBase() {
		return ssBase;
	}

	public void setSsBase(Float ssBase) {
		this.ssBase = ssBase;
	}

	public String getLocalOrField() {
		return localOrField;
	}

	public void setLocalOrField(String localOrField) {
		this.localOrField = localOrField;
	}

	public String getCityOrCountryside() {
		return cityOrCountryside;
	}

	public void setCityOrCountryside(String cityOrCountryside) {
		this.cityOrCountryside = cityOrCountryside;
	}

	public String getInputPeople() {
		return inputPeople;
	}

	public void setInputPeople(String inputPeople) {
		this.inputPeople = inputPeople;
	}

	public String getInputDate() {
		return inputDate;
	}

	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}

	public String getUpdatePeople() {
		return updatePeople;
	}

	public void setUpdatePeople(String updatePeople) {
		this.updatePeople = updatePeople;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getStandardStatus() {
		return standardStatus;
	}

	public void setStandardStatus(String standardStatus) {
		this.standardStatus = standardStatus;
	}

	public String getIsOnWork() {
		return isOnWork;
	}

	public void setIsOnWork(String isOnWork) {
		this.isOnWork = isOnWork;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getBucha() {
		return bucha;
	}

	public void setBucha(String bucha) {
		this.bucha = bucha;
	}

	public String getPersonClass() {
		return personClass;
	}

	public void setPersonClass(String personClass) {
		this.personClass = personClass;
	}

	public Float getFangzufei() {
		return fangzufei;
	}

	public void setFangzufei(Float fangzufei) {
		this.fangzufei = fangzufei;
	}

	public String getChageStatus() {
		return chageStatus;
	}

	public void setChageStatus(String chageStatus) {
		this.chageStatus = chageStatus;
	}

	public Integer getInsuranceGoldId() {
		return insuranceGoldId;
	}

	public void setInsuranceGoldId(Integer insuranceGoldId) {
		this.insuranceGoldId = insuranceGoldId;
	}

	public Float getDwtongchoujin() {
		return dwtongchoujin;
	}

	public void setDwtongchoujin(Float dwtongchoujin) {
		this.dwtongchoujin = dwtongchoujin;
	}

	public Float getDwyiliaobaoxian() {
		return dwyiliaobaoxian;
	}

	public void setDwyiliaobaoxian(Float dwyiliaobaoxian) {
		this.dwyiliaobaoxian = dwyiliaobaoxian;
	}

	public Float getDwshiyebaoxian() {
		return dwshiyebaoxian;
	}

	public void setDwshiyebaoxian(Float dwshiyebaoxian) {
		this.dwshiyebaoxian = dwshiyebaoxian;
	}

	public Float getDwgongjijin() {
		return dwgongjijin;
	}

	public void setDwgongjijin(Float dwgongjijin) {
		this.dwgongjijin = dwgongjijin;
	}

	public Float getGongshangbaoxian() {
		return gongshangbaoxian;
	}

	public void setGongshangbaoxian(Float gongshangbaoxian) {
		this.gongshangbaoxian = gongshangbaoxian;
	}

	public Float getShengyubaoxian() {
		return shengyubaoxian;
	}

	public void setShengyubaoxian(Float shengyubaoxian) {
		this.shengyubaoxian = shengyubaoxian;
	}

	public Float getDwgongshangbaoxian() {
		return dwgongshangbaoxian;
	}

	public void setDwgongshangbaoxian(Float dwgongshangbaoxian) {
		this.dwgongshangbaoxian = dwgongshangbaoxian;
	}

	public Float getDwshengyubaoxian() {
		return dwshengyubaoxian;
	}

	public void setDwshengyubaoxian(Float dwshengyubaoxian) {
		this.dwshengyubaoxian = dwshengyubaoxian;
	}

	public Double getZxfjkc() {
		return zxfjkc;
	}

	public void setZxfjkc(Double zxfjkc) {
		this.zxfjkc = zxfjkc;
	}

}
