package com.task.entity.vo.shizhivo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.task.entity.shizhi.SkillScore;
import com.task.entity.shizhi.SkillType;

public class SkillTypeVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Float minscore;
	private Float maxscore;
	private Integer scoreCount;//含有的技能系数个数页面显示合并列的时候用
	private List<SkillScore> skillScore;
	
	public SkillTypeVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SkillTypeVo(Integer id, String name, Float minscore, Float maxscore,
			List<SkillScore> skillScore) {
		super();
		this.id = id;
		this.name = name;
		this.minscore = minscore;
		this.maxscore = maxscore;
		this.skillScore = skillScore;
	}
	/**
	 * 此构造器用来转换技能分类实体类
	 * @param skillType
	 */
	public SkillTypeVo(SkillType skillType){
		if(skillType!=null){
			this.id=skillType.getId();
			this.name=skillType.getName();
			this.minscore=skillType.getMinscore();
			this.maxscore=skillType.getMaxscore();
			this.scoreCount=0;
			if(skillType.getSkillScore()!=null){
				List<SkillScore> skillScores=new ArrayList<SkillScore>();
				for(SkillScore ss:skillType.getSkillScore()){
					skillScores.add(ss);
					scoreCount++;
				}
				this.skillScore=skillScores;
			}else{
				this.skillScore=new ArrayList<SkillScore>();
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj!=null&obj instanceof SkillTypeVo){
			SkillTypeVo stVo=(SkillTypeVo)obj;
			if(this.id==stVo.getId()){
				return true;
			}
		}
		return super.equals(obj);
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

	public Float getMinscore() {
		return minscore;
	}

	public void setMinscore(Float minscore) {
		this.minscore = minscore;
	}

	public Float getMaxscore() {
		return maxscore;
	}

	public void setMaxscore(Float maxscore) {
		this.maxscore = maxscore;
	}

	public List<SkillScore> getSkillScore() {
		return skillScore;
	}

	public void setSkillScore(List<SkillScore> skillScore) {
		this.skillScore = skillScore;
	}

	public Integer getScoreCount() {
		return scoreCount;
	}

	public void setScoreCount(Integer scoreCount) {
		this.scoreCount = scoreCount;
	}
	
}
