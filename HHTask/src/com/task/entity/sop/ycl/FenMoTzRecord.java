package com.task.entity.sop.ycl;

import java.io.Serializable;

import com.task.util.FieldMeta;

/**
 * 粉末调整记录信息:(ta_FenMoTzRecord)
 * @author wxf
 *
 */
public class FenMoTzRecord  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6600506231377541381L;
	private Integer id;
	@FieldMeta(name = "件号")
	private String markId;//件号
	@FieldMeta(name = "名称")
	private String name;//名称
	@FieldMeta(name = "每公斤喷粉面积(调整前)")
	private Float areakg0;//每公斤喷粉面积(调整前)
	@FieldMeta(name = "每公斤喷粉面积(调整后)")
	private Float areakg1;//每公斤喷粉面积(调整后)
	@FieldMeta(name = "用量标准(调整前)")
	private Float  ylbz0;//用量标准(调整前)
	@FieldMeta(name = "用量标准(调整后)")
	private Float ylbz1;//用量标准(调整后)
	@FieldMeta(name = "调整原因")
	private String tzReason;//调整原因
	@FieldMeta(name = "申请人")
	private String sqUserName;//申请人
	private String sqTime;//申请时间
	private Integer epId;//
	private String epStatus;//审批状态
	private String pzUsreName;//批准人
	private String pzTime;//批准时间
	private DosageTzDan dtd;//申请单对象 
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getAreakg0() {
		return areakg0;
	}
	public void setAreakg0(Float areakg0) {
		this.areakg0 = areakg0;
	}
	public Float getAreakg1() {
		return areakg1;
	}
	public void setAreakg1(Float areakg1) {
		this.areakg1 = areakg1;
	}
	public Float getYlbz0() {
		return ylbz0;
	}
	public void setYlbz0(Float ylbz0) {
		this.ylbz0 = ylbz0;
	}
	public Float getYlbz1() {
		return ylbz1;
	}
	public void setYlbz1(Float ylbz1) {
		this.ylbz1 = ylbz1;
	}
	public String getTzReason() {
		return tzReason;
	}
	public void setTzReason(String tzReason) {
		this.tzReason = tzReason;
	}
	public String getSqUserName() {
		return sqUserName;
	}
	public void setSqUserName(String sqUserName) {
		this.sqUserName = sqUserName;
	}
	public String getSqTime() {
		return sqTime;
	}
	public void setSqTime(String sqTime) {
		this.sqTime = sqTime;
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
	public String getPzUsreName() {
		return pzUsreName;
	}
	public void setPzUsreName(String pzUsreName) {
		this.pzUsreName = pzUsreName;
	}
	public String getPzTime() {
		return pzTime;
	}
	public void setPzTime(String pzTime) {
		this.pzTime = pzTime;
	}
	public DosageTzDan getDtd() {
		return dtd;
	}
	public void setDtd(DosageTzDan dtd) {
		this.dtd = dtd;
	}
	
	
	
}
