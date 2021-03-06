package com.task.entity.gongyi.score;

import java.io.Serializable;
import java.util.List;

public class GongyiGuichengScoreLeibie implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String biaoshi;
	private Integer score;
	private Integer orderNumb;
	private Integer parentId;
	private List<GongyiGuichengScoreLeibie>  gongyiGuichengScoreXuanxiangList;
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
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getOrderNumb() {
		return orderNumb;
	}
	public void setOrderNumb(Integer orderNumb) {
		this.orderNumb = orderNumb;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getBiaoshi() {
		return biaoshi;
	}
	public void setBiaoshi(String biaoshi) {
		this.biaoshi = biaoshi;
	}
	public List<GongyiGuichengScoreLeibie> getGongyiGuichengScoreXuanxiangList() {
		return gongyiGuichengScoreXuanxiangList;
	}
	public void setGongyiGuichengScoreXuanxiangList(List<GongyiGuichengScoreLeibie> gongyiGuichengScoreXuanxiangList) {
		this.gongyiGuichengScoreXuanxiangList = gongyiGuichengScoreXuanxiangList;
	}
	
}
