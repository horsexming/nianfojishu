package com.task.entity.peb;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 生产效率简报（人事档案）
 * ta_subTeam
 * @author xs-cy
 *
 */
public class SubTeam implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String subName;
	private String principals;//负责人
	private String isBanzu;
	
	private Integer chuqinDays2015;//2015年出勤天数
	private Integer personCount2015;//2015年人数
	private Float ccCount2015;//2015年实际产出（仓）
	private Float avg2015;//2015年平均日人均产出
	private Float mbCount2015;//2015年目标日人均
	
	
	private Integer chuqinDays2016;//2016年出勤天数
	private Integer personCount2016;
	private Float ccCount2016;//2016年产出数量
	private Float avg2016;
	private Float mbCount2016;//2016年目标日人均
	
	private Integer chuqinDays2017;//2017年出勤天数
	private Integer personCount2017;
	private Float ccCount2017;//2017年产出数量
	private Float avg2017;
	private Float mbCount2017;//2017年目标日人均
	
	private Integer fatherId;//父厂的Id
	private String remarks;
	
	
//	private String w
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public String getPrincipals() {
		return principals;
	}
	public void setPrincipals(String principals) {
		this.principals = principals;
	}
	public String getIsBanzu() {
		return isBanzu;
	}
	public void setIsBanzu(String isBanzu) {
		this.isBanzu = isBanzu;
	}
	public Float getAvg2015() {
		if(avg2015!=null) {
			BigDecimal bigDecimal = new BigDecimal(avg2015);
			return bigDecimal.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
		}else {
			return avg2015;
		}
	}
	public void setAvg2015(Float avg2015) {
		this.avg2015 = avg2015;
	}
	public Float getAvg2016() {
		if(avg2016!=null) {
			BigDecimal bigDecimal = new BigDecimal(avg2016);
			return bigDecimal.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
		}else {
			return avg2016;
		}
			
	}
	public void setAvg2016(Float avg2016) {
		this.avg2016 = avg2016;
	}
	public Float getAvg2017() {
		if(avg2017!=null) {
			BigDecimal bigDecimal = new BigDecimal(avg2017);
			return bigDecimal.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
		}else {
			return avg2017;
		}
			
	}
	public void setAvg2017(Float avg2017) {
		this.avg2017 = avg2017;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getFatherId() {
		return fatherId;
	}
	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}
	public Float getCcCount2015() {
		return ccCount2015;
	}
	public void setCcCount2015(Float ccCount2015) {
		this.ccCount2015 = ccCount2015;
	}
	public Float getCcCount2016() {
		return ccCount2016;
	}
	public void setCcCount2016(Float ccCount2016) {
		this.ccCount2016 = ccCount2016;
	}
	public Float getCcCount2017() {
		return ccCount2017;
	}
	public void setCcCount2017(Float ccCount2017) {
		this.ccCount2017 = ccCount2017;
	}
	public Float getMbCount2015() {
		return mbCount2015;
	}
	public void setMbCount2015(Float mbCount2015) {
		this.mbCount2015 = mbCount2015;
	}
	public Float getMbCount2016() {
		return mbCount2016;
	}
	public void setMbCount2016(Float mbCount2016) {
		this.mbCount2016 = mbCount2016;
	}
	public Float getMbCount2017() {
		return mbCount2017;
	}
	public void setMbCount2017(Float mbCount2017) {
		this.mbCount2017 = mbCount2017;
	}
	public Integer getPersonCount2015() {
		return personCount2015;
	}
	public void setPersonCount2015(Integer personCount2015) {
		this.personCount2015 = personCount2015;
	}
	public Integer getPersonCount2016() {
		return personCount2016;
	}
	public void setPersonCount2016(Integer personCount2016) {
		this.personCount2016 = personCount2016;
	}
	public Integer getPersonCount2017() {
		return personCount2017;
	}
	public void setPersonCount2017(Integer personCount2017) {
		this.personCount2017 = personCount2017;
	}
	public Integer getChuqinDays2015() {
		return chuqinDays2015;
	}
	public void setChuqinDays2015(Integer chuqinDays2015) {
		this.chuqinDays2015 = chuqinDays2015;
	}
	public Integer getChuqinDays2016() {
		return chuqinDays2016;
	}
	public void setChuqinDays2016(Integer chuqinDays2016) {
		this.chuqinDays2016 = chuqinDays2016;
	}
	public Integer getChuqinDays2017() {
		return chuqinDays2017;
	}
	public void setChuqinDays2017(Integer chuqinDays2017) {
		this.chuqinDays2017 = chuqinDays2017;
	}

	
}
