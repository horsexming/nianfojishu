package com.task.entity.shizhi;

import java.io.Serializable;
import java.util.Set;

import com.task.entity.project.QuotedProcessInfor;

/**
 * 项目加工难点系数
 * 
 * @表名：ta_sk_ProProcessDifficulty
 * @author 唐晓斌
 */
public class ProProcessDifficulty implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;

	private Set<SkillType> skillType;// 分类

	public ProProcessDifficulty() {
		super();
		// TODO Auto-generated constructor stub
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

	public Set<SkillType> getSkillType() {
		return skillType;
	}

	public void setSkillType(Set<SkillType> skillType) {
		this.skillType = skillType;
	}





}
