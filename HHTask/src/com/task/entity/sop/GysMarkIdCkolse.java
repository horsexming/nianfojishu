package com.task.entity.sop;
/**
 * 供应商件号送货关闭情况表:(ta_GysMarkIdCkolse)
 * @author wxf
 *
 */
public class GysMarkIdCkolse implements java.io.Serializable{
	private static final long serialVersionUID =1L;

	private Integer id;
	private Integer gysId;//供应商Id
	private String gysCode;//供应商编号
	private String gysName;//供应商简称
	private String gysCmp;//供应商全称
	private String markId;//件号
	private Integer isjytx;//是否提醒检验;(0/1) 0:提醒 1:不提醒
	private Integer isClose;//是否关闭送货功能(0/1) 0::关闭 1:不关闭
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGysId() {
		return gysId;
	}
	public void setGysId(Integer gysId) {
		this.gysId = gysId;
	}
	public String getGysCode() {
		return gysCode;
	}
	public void setGysCode(String gysCode) {
		this.gysCode = gysCode;
	}
	public String getGysName() {
		return gysName;
	}
	public void setGysName(String gysName) {
		this.gysName = gysName;
	}
	public String getGysCmp() {
		return gysCmp;
	}
	public void setGysCmp(String gysCmp) {
		this.gysCmp = gysCmp;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public Integer getIsjytx() {
		return isjytx;
	}
	public void setIsjytx(Integer isjytx) {
		this.isjytx = isjytx;
	}
	public Integer getIsClose() {
		return isClose;
	}
	public void setIsClose(Integer isClose) {
		this.isClose = isClose;
	}
	
	
}
