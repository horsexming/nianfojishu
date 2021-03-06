package com.task.entity.shizhi;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 技能系数
 * 
 * @表名：ta_sk_SkillScore
 * @author 唐晓斌
 */
public class SkillScore implements Serializable{
	private static final long serialVersionUID = 1L;
 private Integer id;
 private String name;
 private Float score;//技能系数
 private Float totalscore;//总体技能分值或系数
 private Float difficultScore;//强度或复杂系数
 private Float total;//综合系数
 private SkillType skillType;//技能分类
 
  public SkillScore() {
	super();
	// TODO Auto-generated constructor stub
  }


public SkillScore(Integer id, String name, Float score, Float totalscore,
		Float difficultScore, Float total, SkillType skillType) {
	super();
	this.id = id;
	this.name = name;
	this.score = score;
	this.totalscore = totalscore;
	this.difficultScore = difficultScore;
	this.total = total;
	this.skillType = skillType;
}


public Float getTotal() {
	return total;
}

public void setTotal(Float total) {
	this.total = total;
}


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

public Float getScore() {
	return score;
}

public void setScore(Float score) {
	this.score = score;
}

@JSONField(serialize = false)
public SkillType getSkillType() {
	return skillType;
}


public void setSkillType(SkillType skillType) {
	this.skillType = skillType;
}


public Float getTotalscore() {
	return totalscore;
}


public void setTotalscore(Float totalscore) {
	this.totalscore = totalscore;
}


public Float getDifficultScore() {
	return difficultScore;
}


public void setDifficultScore(Float difficultScore) {
	this.difficultScore = difficultScore;
}
  

 
}
