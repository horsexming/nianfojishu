package com.task.entity.android.processpush;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.task.entity.TaSopGongwei;

/****
 * 推送表  表名:ta_Push
 * @author mxl
 * 
 */
 
public class Push implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	private Integer id;
	private String meid;//android meid唯一标识
	private String station;//工位
	private String sysdate;//时间
	private String ipAddress;//ip地址
	private String flatNum;//平板编号
	private String flatStation;//平板工位
	private Set<TaSopGongwei> taSopGongweis;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMeid() {
		return meid;
	}
	public void setMeid(String meid) {
		this.meid = meid;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getSysdate() {
		return sysdate;
	}
	public void setSysdate(String sysdate) {
		this.sysdate = sysdate;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Set<TaSopGongwei> getTaSopGongweis() {
		return taSopGongweis;
	}
	public void setTaSopGongweis(Set<TaSopGongwei> taSopGongweis) {
		this.taSopGongweis = taSopGongweis;
	}
	public String getFlatNum() {
		return flatNum;
	}
	public void setFlatNum(String flatNum) {
		this.flatNum = flatNum;
	}
	public String getFlatStation() {
		return flatStation;
	}
	public void setFlatStation(String flatStation) {
		this.flatStation = flatStation;
	}
  
	
	

}
