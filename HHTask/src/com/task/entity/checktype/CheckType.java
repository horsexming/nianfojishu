package com.task.entity.checktype;

import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

public class CheckType implements java.io.Serializable{
	private Integer id ;
	private String name;//检查名称
	private String type;//类别（生产/办公/公共卫生）
	private String maxScore;// 最大扣除分数
	private Set<CheckNote> checkNote;
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
	public String getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(String maxScore) {
		this.maxScore = maxScore;
	}
	@JSONField(serialize = false)
	public Set<CheckNote> getCheckNote() {
		return checkNote;
	}
	public void setCheckNote(Set<CheckNote> checkNote) {
		this.checkNote = checkNote;
	}
	
}
