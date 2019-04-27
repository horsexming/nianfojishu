package com.task.entity.vo.shizhivo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.task.entity.shizhi.ProProcessDifficulty;
import com.task.entity.shizhi.SkillType;

public class ProProcessDifficultyVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Integer scoreCount;//含有的技能系数个数页面显示合并列的时候用
	private List<SkillTypeVo> skillTypeVo;// 分类

	public ProProcessDifficultyVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public ProProcessDifficultyVo(Integer id, String name,
			List<SkillTypeVo> skillTypeVo) {
		super();
		this.id = id;
		this.name = name;
		this.skillTypeVo = skillTypeVo;
	}


	/**
	 * 此构造器用来转换加工难点系数实体类
	 * @param ppd
	 */
    public ProProcessDifficultyVo(ProProcessDifficulty ppd){
    	if(ppd!=null){
    		this.id=ppd.getId();
    		this.name=ppd.getName();
    		this.scoreCount=0;
    		if(ppd.getSkillType()!=null){
    			List<SkillTypeVo> skillTypeVos=new ArrayList<SkillTypeVo>();
    			for(SkillType st:ppd.getSkillType()){
    				SkillTypeVo stVo=new SkillTypeVo(st);
    				skillTypeVos.add(stVo);
    				scoreCount=stVo.getScoreCount()+scoreCount;
    			}
    			this.skillTypeVo=skillTypeVos;
    		}else{
    			this.skillTypeVo=new ArrayList<SkillTypeVo>();
    		}
    	}
    	
    }
    
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj!=null){
			ProProcessDifficultyVo ppdVo=(ProProcessDifficultyVo)obj;
			if(this.id==ppdVo.getId()){
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


	public List<SkillTypeVo> getSkillTypeVo() {
		return skillTypeVo;
	}


	public void setSkillTypeVo(List<SkillTypeVo> skillTypeVo) {
		this.skillTypeVo = skillTypeVo;
	}


	public Integer getScoreCount() {
		return scoreCount;
	}


	public void setScoreCount(Integer scoreCount) {
		this.scoreCount = scoreCount;
	}


	


}
