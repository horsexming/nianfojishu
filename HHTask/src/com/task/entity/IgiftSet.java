package com.task.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 抽奖时间设置表:(ta_IgiftSet)
 * @author 王晓飞;
 *
 */
public class IgiftSet  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;///活动名称
	private String beginTime;//抽奖开始时间;
	private String endTime;//抽奖结束时间;
	private String status;//状态(可报名/已开奖);
	private Integer giftid;//礼品Id;
	private String qihao;//期号;
	private String 	giftpicture;//奖品图片
	private String randomnum;//随机码(每次活动时的 开奖随机码)
	private Set<IndianaGift> indianaGiftset;//夺宝系统表(一对多)
	private	List<IndianaGift> indianaGiftlist;
	private String type;//类型(设备稼动率、抽奖)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Set<IndianaGift> getIndianaGiftset() {
		return indianaGiftset;
	}
	public void setIndianaGiftset(Set<IndianaGift> indianaGiftset) {
		this.indianaGiftset = indianaGiftset;
	}
	public List<IndianaGift> getIndianaGiftlist() {
		return indianaGiftlist;
	}
	public void setIndianaGiftlist(List<IndianaGift> indianaGiftlist) {
		this.indianaGiftlist = indianaGiftlist;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getGiftid() {
		return giftid;
	}
	public void setGiftid(Integer giftid) {
		this.giftid = giftid;
	}
	public String getRandomnum() {
		return randomnum;
	}
	public void setRandomnum(String randomnum) {
		this.randomnum = randomnum;
	}
	public String getQihao() {
		return qihao;
	}
	public void setQihao(String qihao) {
		this.qihao = qihao;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGiftpicture() {
		return giftpicture;
	}
	public void setGiftpicture(String giftpicture) {
		this.giftpicture = giftpicture;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
