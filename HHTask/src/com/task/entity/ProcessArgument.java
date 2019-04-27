package com.task.entity;

import java.io.Serializable;

public class ProcessArgument implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 操作过程
	 */
	Double OPSkillIndex = 0.0; // 操作技能指数
	Double OPNotReplaceCoefficient = 0.0; // 不可替换系数
	Double OPLoadIndex = 0.0; // 负荷指数
	Double OPLabourBeat = 0.0; // 人工节拍
	/**
	 * 准备过程
	 */
	Double PRSkillIndex = 0.0;// 技能指数
	Double PRNotReplaceCoefficient = 0.0; // 不可替换系数
	Double PRLoadIndex = 0.0; // 负荷指数
	Double PRLabourBeat = 0.0; // 人工节拍

	public ProcessArgument() {
		super();
	}

	public ProcessArgument(Double oPSkillIndex, Double oPNotReplaceCoefficient, Double oPLoadIndex, Double oPLabourBeat,
			Double pRSkillIndex, Double pRNotReplaceCoefficient, Double pRLoadIndex, Double pRLabourBeat) {
		if (oPSkillIndex != null)
			OPSkillIndex = oPSkillIndex;
		if (oPNotReplaceCoefficient != null)
			OPNotReplaceCoefficient = oPNotReplaceCoefficient;
		if (oPLoadIndex != null)
			OPLoadIndex = oPLoadIndex;
		if (oPLabourBeat != null)
			OPLabourBeat = oPLabourBeat;
		if (pRSkillIndex != null)
			PRSkillIndex = pRSkillIndex;
		if (pRNotReplaceCoefficient != null)
			PRNotReplaceCoefficient = pRNotReplaceCoefficient;
		if (pRLoadIndex != null)
			PRLoadIndex = pRLoadIndex;
		if (pRLabourBeat != null)
			PRLabourBeat = pRLabourBeat;
	}

	public Double getOPSkillIndex() {
		return OPSkillIndex;
	}

	public void setOPSkillIndex(Double oPSkillIndex) {
		OPSkillIndex = oPSkillIndex;
	}

	public Double getOPNotReplaceCoefficient() {
		return OPNotReplaceCoefficient;
	}

	public void setOPNotReplaceCoefficient(Double oPNotReplaceCoefficient) {
		OPNotReplaceCoefficient = oPNotReplaceCoefficient;
	}

	public Double getOPLoadIndex() {
		return OPLoadIndex;
	}

	public void setOPLoadIndex(Double oPLoadIndex) {
		OPLoadIndex = oPLoadIndex;
	}

	public Double getOPLabourBeat() {
		return OPLabourBeat;
	}

	public void setOPLabourBeat(Double oPLabourBeat) {
		OPLabourBeat = oPLabourBeat;
	}

	public Double getPRSkillIndex() {
		return PRSkillIndex;
	}

	public void setPRSkillIndex(Double pRSkillIndex) {
		PRSkillIndex = pRSkillIndex;
	}

	public Double getPRNotReplaceCoefficient() {
		return PRNotReplaceCoefficient;
	}

	public void setPRNotReplaceCoefficient(Double pRNotReplaceCoefficient) {
		PRNotReplaceCoefficient = pRNotReplaceCoefficient;
	}

	public Double getPRLoadIndex() {
		return PRLoadIndex;
	}

	public void setPRLoadIndex(Double pRLoadIndex) {
		PRLoadIndex = pRLoadIndex;
	}

	public Double getPRLabourBeat() {
		return PRLabourBeat;
	}

	public void setPRLabourBeat(Double pRLabourBeat) {
		PRLabourBeat = pRLabourBeat;
	}
	// public String toString(){
	// return "OPSkillIndex："+OPSkillIndex + ",OPNotReplaceCoefficient: " +
	// OPNotReplaceCoefficient+ ",OPLoadIndex: " + OPLoadIndex +",OPLabourBeat:
	// "+OPLabourBeat+
	// "PRSkillIndex: " + PRSkillIndex + "PRNotReplaceCoefficient: " +
	// PRNotReplaceCoefficient + "PRLoadIndex : " + PRLoadIndex + "PRLabourBeat:
	// " + PRLabourBeat;
	// }

}
