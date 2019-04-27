package com.task.entity.gongyi.vo;

import java.io.Serializable;

import com.task.entity.gongyi.GongyiGuicheng;

public class GongyiGuichengVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//命名id numb name date type status  remarks 是基本的 其他用
	//自组树形用的id
	private Integer id;
	//工艺规程的id
	private Integer gid;
	private Integer jianId;//件ID
	private String jianNumb;//件号
	private String jianName;//件名
	private String procardStyle;//生产类型
	private Integer rootId;//根ID
	private Integer fatherId;//上层ID
	private Integer belongLayer;//层数
	public GongyiGuichengVo(){
		
	}
	public GongyiGuichengVo(GongyiGuicheng gg){
		this.gid=gg.getId();
		this.jianId=gg.getJianId();
		this.jianNumb=gg.getJianNumb();
		this.jianName=gg.getJianName();
		this.procardStyle=gg.getProcardStyle();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getJianId() {
		return jianId;
	}
	public void setJianId(Integer jianId) {
		this.jianId = jianId;
	}
	public String getJianNumb() {
		return jianNumb;
	}
	public void setJianNumb(String jianNumb) {
		this.jianNumb = jianNumb;
	}
	public String getJianName() {
		return jianName;
	}
	public void setJianName(String jianName) {
		this.jianName = jianName;
	}
	public Integer getRootId() {
		return rootId;
	}
	public void setRootId(Integer rootId) {
		this.rootId = rootId;
	}
	public Integer getFatherId() {
		return fatherId;
	}
	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}
	public Integer getBelongLayer() {
		return belongLayer;
	}
	public void setBelongLayer(Integer belongLayer) {
		this.belongLayer = belongLayer;
	}
	public String getProcardStyle() {
		return procardStyle;
	}
	public void setProcardStyle(String procardStyle) {
		this.procardStyle = procardStyle;
	}
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	
	
	
}
