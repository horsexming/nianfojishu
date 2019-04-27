package com.tast.entity.zhaobiao;

import java.io.Serializable;

/*
 * 考勤汇总表
 */

public class KaoQin implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer userId;//用户ID
	private String carId;// 卡号
	private String name;// 姓名
	private String dept;// 部门
	private String code;// 工号
	private Float chuqintianshu;// 出勤天数(为工作时间+公出时间)
	private Float chuqintianshuYouxiao;// 出勤有效天数(为工作时间+公出时间+年休+换休)
	private Float yingchuqin;//应该出勤天数

	private Float shijia;// 事假
	private Float kuanggong;// 矿工
	private Float bingjia;// 病假
	private Float nianxiujia;// 年休
	private Float tiaoxiu; // 调休.换休

	private Float gongxiu; // 公休
	private Float chanjia; // 产假/陪护假
	private Float huncangjia; // 婚/丧假
	private Float qita; // 其他
	private Float jiaban; // 加班天数
	private Float lateTime; // 迟到
	private Float earlyTime; // 早退
	private Float qingjiaTime; // 请假时长

	private Float yeban;// 夜班
	private Float gongchu;// 公出
	private String shuoming;// 说明
	private String beizhu;// 备注
	private String yuefen;//月份
	private String status;//状态
	private String addTime;//最后更新时间
	private Integer statusInt;//调整状态(0：自动/1：调整后)
	private Float bijiao;// 对比天数（正常情况下应该等于应出勤天数）

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getBijiao() {
		return bijiao;
	}

	public void setBijiao(Float bijiao) {
		this.bijiao = bijiao;
	}

	public Float getChuqintianshuYouxiao() {
		return chuqintianshuYouxiao;
	}

	public void setChuqintianshuYouxiao(Float chuqintianshuYouxiao) {
		this.chuqintianshuYouxiao = chuqintianshuYouxiao;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Float getQingjiaTime() {
		return qingjiaTime;
	}

	public void setQingjiaTime(Float qingjiaTime) {
		this.qingjiaTime = qingjiaTime;
	}

	public Float getLateTime() {
		return lateTime;
	}

	public void setLateTime(Float lateTime) {
		this.lateTime = lateTime;
	}

	public Float getEarlyTime() {
		return earlyTime;
	}

	public void setEarlyTime(Float earlyTime) {
		this.earlyTime = earlyTime;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Float getChuqintianshu() {
		return chuqintianshu;
	}

	public void setChuqintianshu(Float chuqintianshu) {
		this.chuqintianshu = chuqintianshu;
	}

	public Float getShijia() {
		return shijia;
	}

	public void setShijia(Float shijia) {
		this.shijia = shijia;
	}

	public Float getKuanggong() {
		return kuanggong;
	}

	public void setKuanggong(Float kuanggong) {
		this.kuanggong = kuanggong;
	}

	public Float getBingjia() {
		return bingjia;
	}

	public void setBingjia(Float bingjia) {
		this.bingjia = bingjia;
	}

	public Float getNianxiujia() {
		return nianxiujia;
	}

	public void setNianxiujia(Float nianxiujia) {
		this.nianxiujia = nianxiujia;
	}

	public Float getTiaoxiu() {
		return tiaoxiu;
	}

	public void setTiaoxiu(Float tiaoxiu) {
		this.tiaoxiu = tiaoxiu;
	}

	public Float getGongxiu() {
		return gongxiu;
	}

	public void setGongxiu(Float gongxiu) {
		this.gongxiu = gongxiu;
	}

	public Float getChanjia() {
		return chanjia;
	}

	public void setChanjia(Float chanjia) {
		this.chanjia = chanjia;
	}

	public Float getHuncangjia() {
		return huncangjia;
	}

	public void setHuncangjia(Float huncangjia) {
		this.huncangjia = huncangjia;
	}

	public Float getQita() {
		return qita;
	}

	public void setQita(Float qita) {
		this.qita = qita;
	}

	public Float getJiaban() {
		return jiaban;
	}

	public void setJiaban(Float jiaban) {
		this.jiaban = jiaban;
	}

	public Float getYeban() {
		return yeban;
	}

	public void setYeban(Float yeban) {
		this.yeban = yeban;
	}

	public Float getGongchu() {
		return gongchu;
	}

	public void setGongchu(Float gongchu) {
		this.gongchu = gongchu;
	}

	public String getShuoming() {
		return shuoming;
	}

	public void setShuoming(String shuoming) {
		this.shuoming = shuoming;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getYuefen() {
		return yuefen;
	}

	public void setYuefen(String yuefen) {
		this.yuefen = yuefen;
	}

	public Float getYingchuqin() {
		return yingchuqin;
	}

	public void setYingchuqin(Float yingchuqin) {
		this.yingchuqin = yingchuqin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStatusInt() {
		return statusInt;
	}

	public void setStatusInt(Integer statusInt) {
		this.statusInt = statusInt;
	}


}
