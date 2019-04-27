package com.task.entity;

import java.io.File;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.util.FieldMeta;

/**
 * 表名MachineSparePart
 * @author txb
 *
 */
public class MachineSparePart  implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;// 主键
	private String number;// 编号
	private String matetag;// 名称
	private Float safeCount;//安全库存
	private Float curCount;//当前库存
	private String unit;// 单位
	private String format;// 规格
	private String storehouse;// 仓库()
	private String parClass;// 　分类
	private String place;// 位置
	private String remake;// 　备注
	private Float price;// 价格
	private String carModel;// 车型
	private String addtime;//添加时间
	private Machine machine;//设备
	private String safeStatus;//库存状态（不存数据库）
	//图纸 
	@FieldMeta(name="图纸 ")
	private String pic;
	private File picF;
	private String  picFContentType;
	private String picFFileName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getMatetag() {
		return matetag;
	}
	public void setMatetag(String matetag) {
		this.matetag = matetag;
	}
	
	public Float getSafeCount() {
		return safeCount;
	}
	public void setSafeCount(Float safeCount) {
		this.safeCount = safeCount;
	}
	public Float getCurCount() {
		return curCount;
	}
	public void setCurCount(Float curCount) {
		this.curCount = curCount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getStorehouse() {
		return storehouse;
	}
	public void setStorehouse(String storehouse) {
		this.storehouse = storehouse;
	}
	public String getParClass() {
		return parClass;
	}
	public void setParClass(String parClass) {
		this.parClass = parClass;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getRemake() {
		return remake;
	}
	public void setRemake(String remake) {
		this.remake = remake;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	@JSONField(serialize = false)
	public Machine getMachine() {
		return machine;
	}
	public void setMachine(Machine machine) {
		this.machine = machine;
	}
	
	public String getSafeStatus() {
		return safeStatus;
	}
	public void setSafeStatus(String safeStatus) {
		this.safeStatus = safeStatus;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj!=null&&obj instanceof MachineSparePart){
			MachineSparePart m=(MachineSparePart)obj;
		}
		return false;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public File getPicF() {
		return picF;
	}
	public void setPicF(File picF) {
		this.picF = picF;
	}
	public String getPicFContentType() {
		return picFContentType;
	}
	public void setPicFContentType(String picFContentType) {
		this.picFContentType = picFContentType;
	}
	public String getPicFFileName() {
		return picFFileName;
	}
	public void setPicFFileName(String picFFileName) {
		this.picFFileName = picFFileName;
	}
	
	
}
