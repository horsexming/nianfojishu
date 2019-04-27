package com.task.entity;

/*
 * 提奖计价表
 * @ 表名 tijiang
 *  钟永林
 */
public class Tijiang implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer tjid; // 序号 int 4 no
	private String tjstyle; // 型别 varchar 50 yes
	private String tjmarkId; // 件号 varchar 50 yes
	private String tjformat; // 规格 varchar 50 yes
	private String tjmonth; // 月份 varchar 50 yes
	private Float tjprice; // 单价 float 8 yes
	private Float tjdinge; // 工时定额 float 8 yes
	private Float tjcount; // 总数量 float 8 yes
	private Float tjmoney; // 总金额 float 8 yes
	private String tjtimer;// 时间 varchar 50 no
	private String tjmore; // 状态 varchar 100 yes
	private Integer epId;// 审批流程Id

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	public Integer getTjid() {
		return tjid;
	}

	public void setTjid(Integer tjid) {
		this.tjid = tjid;
	}

	public String getTjstyle() {
		return tjstyle;
	}

	public void setTjstyle(String tjstyle) {
		this.tjstyle = tjstyle;
	}

	public String getTjmarkId() {
		return tjmarkId;
	}

	public void setTjmarkId(String tjmarkId) {
		this.tjmarkId = tjmarkId;
	}

	public String getTjformat() {
		return tjformat;
	}

	public void setTjformat(String tjformat) {
		this.tjformat = tjformat;
	}

	public String getTjmonth() {
		return tjmonth;
	}

	public void setTjmonth(String tjmonth) {
		this.tjmonth = tjmonth;
	}

	public Float getTjprice() {
		return tjprice;
	}

	public void setTjprice(Float tjprice) {
		this.tjprice = tjprice;
	}

	public Float getTjdinge() {
		return tjdinge;
	}

	public void setTjdinge(Float tjdinge) {
		this.tjdinge = tjdinge;
	}

	public Float getTjcount() {
		return tjcount;
	}

	public void setTjcount(Float tjcount) {
		this.tjcount = tjcount;
	}

	public Float getTjmoney() {
		return tjmoney;
	}

	public void setTjmoney(Float tjmoney) {
		this.tjmoney = tjmoney;
	}

	public String getTjtimer() {
		return tjtimer;
	}

	public void setTjtimer(String tjtimer) {
		this.tjtimer = tjtimer;
	}

	public String getTjmore() {
		return tjmore;
	}

	public void setTjmore(String tjmore) {
		this.tjmore = tjmore;
	}

	@Override
	public boolean equals(Object obj) { // 覆写equals方法
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Tijiang)) {
			return false;
		}
		Tijiang stu = (Tijiang) obj;
		if (stu.tjmarkId.equals(this.tjmarkId) && stu.tjcount == this.tjcount) {
			return true;
		} else {
			return false;
		}
	}
}
