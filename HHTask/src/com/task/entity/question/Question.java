package com.task.entity.question;

import java.io.Serializable;

public class Question  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;//标题
	private String type;//类型
	private String detail;// 详情
	private String questionPerson;//提问人
	private String questionTime;//提问时间
	private String answer;//答案
	private String answerPerson;//回答人
	private String answerTime;//回答时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getQuestionPerson() {
		return questionPerson;
	}
	public void setQuestionPerson(String questionPerson) {
		this.questionPerson = questionPerson;
	}
	public String getQuestionTime() {
		return questionTime;
	}
	public void setQuestionTime(String questionTime) {
		this.questionTime = questionTime;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnswerPerson() {
		return answerPerson;
	}
	public void setAnswerPerson(String answerPerson) {
		this.answerPerson = answerPerson;
	}
	public String getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(String answerTime) {
		this.answerTime = answerTime;
	}
	
}
