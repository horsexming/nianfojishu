package com.task.entity;
/**
 * 礼品表:(ta_Gift)
 * @author 王晓飞
 *
 */
public class Gift  implements java.io.Serializable{
	private static final long serialVersionUID =1L;

	private Integer id;
	private Integer num;//礼品数量
	private String name;//礼品名称
	private String isLuckdraw;//是否参与抽奖
	private Integer xy_Integral;//所需积分
	private String picture;//图片
	private Float zjgl;//中奖概率
	private Float gift_price;//礼品价值
	private String addusers;//添加人
	private String addTime;//添加时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsLuckdraw() {
		return isLuckdraw;
	}
	public void setIsLuckdraw(String isLuckdraw) {
		this.isLuckdraw = isLuckdraw;
	}
	public Integer getXy_Integral() {
		return xy_Integral;
	}
	public void setXy_Integral(Integer xyIntegral) {
		xy_Integral = xyIntegral;
	}
	public Float getZjgl() {
		return zjgl;
	}
	public void setZjgl(Float zjgl) {
		this.zjgl = zjgl;
	}
	public Float getGift_price() {
		return gift_price;
	}
	public void setGift_price(Float giftPrice) {
		gift_price = giftPrice;
	}
	public String getAddusers() {
		return addusers;
	}
	public void setAddusers(String addusers) {
		this.addusers = addusers;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
	
}
