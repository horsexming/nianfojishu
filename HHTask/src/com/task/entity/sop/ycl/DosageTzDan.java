package com.task.entity.sop.ycl;

import java.io.Serializable;
import java.util.Set;

import com.task.util.FieldMeta;

/**
 * 用量调整单:(ta_DosageTzDan)
 * @author wxf
 *
 */
public class DosageTzDan implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8429038062839106623L;
	private Integer id;
	@FieldMeta(name = "申请人")
	private String  sqUsersName;//
	@FieldMeta(name = "申请时间")
	private String  sqTime;//
	@FieldMeta(name = "申请类型")
	private String  type;//类型
	private String pzUserName;//批准人
	private String pzTime;//批准时间
	private Integer epId;
	private String epStatus;//审批状态
	private Set<FenMoTzRecord> fmtrSet;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSqUsersName() {
		return sqUsersName;
	}
	public void setSqUsersName(String sqUsersName) {
		this.sqUsersName = sqUsersName;
	}
	public String getSqTime() {
		return sqTime;
	}
	public void setSqTime(String sqTime) {
		this.sqTime = sqTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPzUserName() {
		return pzUserName;
	}
	public void setPzUserName(String pzUserName) {
		this.pzUserName = pzUserName;
	}
	public String getPzTime() {
		return pzTime;
	}
	public void setPzTime(String pzTime) {
		this.pzTime = pzTime;
	}
	
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	public String getEpStatus() {
		return epStatus;
	}
	public void setEpStatus(String epStatus) {
		this.epStatus = epStatus;
	}
	public Set<FenMoTzRecord> getFmtrSet() {
		return fmtrSet;
	}
	public void setFmtrSet(Set<FenMoTzRecord> fmtrSet) {
		this.fmtrSet = fmtrSet;
	}
	
	
	
	
}
