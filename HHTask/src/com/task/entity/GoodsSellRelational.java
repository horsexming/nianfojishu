package com.task.entity;

public class GoodsSellRelational implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private Integer goodsId_old;//原库存表id
	private Integer goodsId_new;//现库存表id
	private Integer sellId;//入库表id;
	private String out_Time;//转出时间
	private String in_Time;//转入时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGoodsId_old() {
		return goodsId_old;
	}
	public void setGoodsId_old(Integer goodsIdOld) {
		goodsId_old = goodsIdOld;
	}
	public Integer getGoodsId_new() {
		return goodsId_new;
	}
	public void setGoodsId_new(Integer goodsIdNew) {
		goodsId_new = goodsIdNew;
	}
	public Integer getSellId() {
		return sellId;
	}
	public void setSellId(Integer sellId) {
		this.sellId = sellId;
	}
	public String getOut_Time() {
		return out_Time;
	}
	public void setOut_Time(String outTime) {
		out_Time = outTime;
	}
	public String getIn_Time() {
		return in_Time;
	}
	public void setIn_Time(String inTime) {
		in_Time = inTime;
	}
	
	
	

}
