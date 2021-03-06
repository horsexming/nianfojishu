package com.task.entity.caiwu.baobiao.laozi;

import java.io.Serializable;

/**
 * 
 * @author 王晓飞
 *	各类人员情况表(ta_baobiao_laozi_PersonneQk)
 */
public class PersonneQk implements Serializable{
	private Integer id;
	
	private Integer lastmontNum;//上月月末人数
	private Integer monthadd;//本月增加
	private Integer montdec;//本月减少
	private Integer monthNum;//本月月末人数
	private Double dygz;//当月工资/劳动报酬
	private Double avgLastYearNum;//上年平均人数
	private Double avgYearNum;//本年平均人
	private Double gzzr;//工资总额/劳动报酬
	private PersonnelType personnelType;//多对一
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLastmontNum() {
		return lastmontNum;
	}
	public void setLastmontNum(Integer lastmontNum) {
		this.lastmontNum = lastmontNum;
	}
	public Integer getMonthadd() {
		return monthadd;
	}
	public void setMonthadd(Integer monthadd) {
		this.monthadd = monthadd;
	}
	public Integer getMontdec() {
		return montdec;
	}
	public void setMontdec(Integer montdec) {
		this.montdec = montdec;
	}
	public Integer getMonthNum() {
		return monthNum;
	}
	public void setMonthNum(Integer monthNum) {
		this.monthNum = monthNum;
	}
	public Double getDygz() {
		return dygz;
	}
	public void setDygz(Double dygz) {
		this.dygz = dygz;
	}
	public Double getAvgLastYearNum() {
		return avgLastYearNum;
	}
	public void setAvgLastYearNum(Double avgLastYearNum) {
		this.avgLastYearNum = avgLastYearNum;
	}
	public Double getAvgYearNum() {
		return avgYearNum;
	}
	public void setAvgYearNum(Double avgYearNum) {
		this.avgYearNum = avgYearNum;
	}
	public Double getGzzr() {
		return gzzr;
	}
	public void setGzzr(Double gzzr) {
		this.gzzr = gzzr;
	}
	public PersonnelType getPersonnelType() {
		return personnelType;
	}
	public void setPersonnelType(PersonnelType personnelType) {
		this.personnelType = personnelType;
	}
	
	
	

}
