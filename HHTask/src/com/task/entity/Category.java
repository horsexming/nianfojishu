package com.task.entity;

import java.io.Serializable;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.tast.entity.zhaobiao.ZhUser;

/**
 * 系统类别表 :(ta_Category)
 * @author wxf
 *
 */
public class Category implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;//类别名称;
	private String type;//类型(物料、){目前只有物料类别};
	private String code;//物料编码；
	private Integer fatherId;
	private Integer rootId;
	private Set<Users> userSet;
	private Set<ZhUser> zhuserSet;
	private Float round;//检验周期
	private Integer avgProductionTakt;//生产节拍
	private Float avgDeliveryTime;//配送时长
	private Integer sunhaoType;// 损耗值类型(0为百分比类型  ；1为固定值类型)
	private Float sunhao;//损耗值
	private Float rangeOfReceipt;//收货浮动范围
	
	private Boolean checked;// 是否选中(true/false)
	private Integer belongLayer;// 当前层

	// 自身树形结构
	//private Set<Category> categorySet;
	//private Category category;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getFatherId() {
		return fatherId;
	}
	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}
	public Integer getRootId() {
		return rootId;
	}
	public void setRootId(Integer rootId) {
		this.rootId = rootId;
	}
	@JSONField(serialize = false)
	public Set<Users> getUserSet() {
		return userSet;
	}
	public void setUserSet(Set<Users> userSet) {
		this.userSet = userSet;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public Integer getBelongLayer() {
		return belongLayer;
	}
	public void setBelongLayer(Integer belongLayer) {
		this.belongLayer = belongLayer;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@JSONField(serialize = false)
	public Set<ZhUser> getZhuserSet() {
		return zhuserSet;
	}
	public void setZhuserSet(Set<ZhUser> zhuserSet) {
		this.zhuserSet = zhuserSet;
	}
	public Integer getAvgProductionTakt() {
		return avgProductionTakt;
	}
	public void setAvgProductionTakt(Integer avgProductionTakt) {
		this.avgProductionTakt = avgProductionTakt;
	}
	public Float getAvgDeliveryTime() {
		return avgDeliveryTime;
	}
	public void setAvgDeliveryTime(Float avgDeliveryTime) {
		this.avgDeliveryTime = avgDeliveryTime;
	}
	
	
	public Integer getSunhaoType() {
		return sunhaoType;
	}
	public void setSunhaoType(Integer sunhaoType) {
		this.sunhaoType = sunhaoType;
	}
	public Float getSunhao() {
		return sunhao;
	}
	public void setSunhao(Float sunhao) {
		this.sunhao = sunhao;
	}
	public Float getRangeOfReceipt() {
		return rangeOfReceipt;
	}
	public void setRangeOfReceipt(Float rangeOfReceipt) {
		this.rangeOfReceipt = rangeOfReceipt;
	}
	public Float getRound() {
		return round;
	}
	public void setRound(Float round) {
		this.round = round;
	}
	
	
}
