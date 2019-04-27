package com.task.entity.shizhi;

import java.io.Serializable;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 技能分类
 * 
 * @表名：ta_sk_SkillType
 * @author 唐晓斌
 */
public class SkillType implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Float minscore;
	private Float maxscore;
	private Set<SkillScore> skillScore;
	private ProProcessDifficulty proProcessDifficulty;//项目加工难点
	private CraftComplexity craftComplexity;//工艺复杂系数
	public SkillType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SkillType(Integer id, String name, Float minscore, Float maxscore,
			Set<SkillScore> skillScore,
			ProProcessDifficulty proProcessDifficulty,
			CraftComplexity craftComplexity) {
		super();
		this.id = id;
		this.name = name;
		this.minscore = minscore;
		this.maxscore = maxscore;
		this.skillScore = skillScore;
		this.proProcessDifficulty = proProcessDifficulty;
		this.craftComplexity = craftComplexity;
	}


	@JSONField(serialize = false)
	public ProProcessDifficulty getProProcessDifficulty() {
		return proProcessDifficulty;
	}

	public void setProProcessDifficulty(ProProcessDifficulty proProcessDifficulty) {
		this.proProcessDifficulty = proProcessDifficulty;
	}
	@JSONField(serialize = false)
	public CraftComplexity getCraftComplexity() {
		return craftComplexity;
	}

	public void setCraftComplexity(CraftComplexity craftComplexity) {
		this.craftComplexity = craftComplexity;
	}
	@JSONField(serialize = false)
	public Set<SkillScore> getSkillScore() {
		return skillScore;
	}

	public void setSkillScore(Set<SkillScore> skillScore) {
		this.skillScore = skillScore;
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
	public Float getMaxscore() {
		return maxscore;
	}
	
	public Float getMinscore() {
		return minscore;
	}
	public void setMinscore(Float minscore) {
		this.minscore = minscore;
	}
	public void setMaxscore(Float maxscore) {
		this.maxscore = maxscore;
	}


	
	

}
