package com.task.entity.zgkh;

import java.io.Serializable;
import java.util.Set;

import com.task.entity.AssScore;

/***
 * 主管级考核_总分统计 (表名:ta_hr_zgkh_ScoreStatistics)
 * 
 * @author 刘培
 * 
 */
public class ScoreStatistics  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String code;// 工号
	private String cardId;// 卡号
	private Integer userId;// 用户id
	private String userName;// 用户名
	private String dept;// 用户部门
	private Float totalScore;// 总分(平均分)
	private String mouth;// 月份
	private String scoreStatus;// 打分状态(打分/完成)
	private Float sdWage;// 试算工资
	private Float wsWage;// 模版工资
	private Set<AssScore> assScore;// 打分明细(一对多)

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Float getTotalScore() {
		if (totalScore != null) {
			return Float.parseFloat(String.format("%.2f", totalScore));
		}
		return totalScore;
	}

	public void setTotalScore(Float totalScore) {
		this.totalScore = totalScore;
	}

	public String getMouth() {
		return mouth;
	}

	public void setMouth(String mouth) {
		this.mouth = mouth;
	}

	public String getScoreStatus() {
		return scoreStatus;
	}

	public void setScoreStatus(String scoreStatus) {
		this.scoreStatus = scoreStatus;
	}

	public Set<AssScore> getAssScore() {
		return assScore;
	}

	public void setAssScore(Set<AssScore> assScore) {
		this.assScore = assScore;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Float getSdWage() {
		return sdWage;
	}

	public void setSdWage(Float sdWage) {
		this.sdWage = sdWage;
	}

	public Float getWsWage() {
		return wsWage;
	}

	public void setWsWage(Float wsWage) {
		this.wsWage = wsWage;
	}

}
