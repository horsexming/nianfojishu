package com.task.entity.sop;
/**
 * 生产件占用在制品关系(ta_sop_w_ProcardProductRelation)
 * @author txb
 *
 */
public class ProcardProductRelation  implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private Integer procardId;//生产件ID
	private Integer goodsId;//在制品Id
	private float zyCount;//占用数量
	private float ylCount;//已领数量(余额在制品使用)
	private float ckCount;//出库数量(在制品使用)
	private String addTime;//添加时间
	private String flagType;//标识(本批在制品,余额在制品,余料)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProcardId() {
		return procardId;
	}
	public void setProcardId(Integer procardId) {
		this.procardId = procardId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public float getZyCount() {
		return zyCount;
	}
	public void setZyCount(float zyCount) {
		this.zyCount = zyCount;
	}
	public float getYlCount() {
		return ylCount;
	}
	public void setYlCount(float ylCount) {
		this.ylCount = ylCount;
	}
	public String getFlagType() {
		return flagType;
	}
	public void setFlagType(String flagType) {
		this.flagType = flagType;
	}
	public float getCkCount() {
		return ckCount;
	}
	public void setCkCount(float ckCount) {
		this.ckCount = ckCount;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	
	

}
