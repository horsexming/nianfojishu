package com.task.entity;
/**
 * 调查问卷明细表:(ta_Questionnaire)
 * @author 王晓飞
 *
 */
public class Questionnaire implements java.io.Serializable{
	private static final long serialVersionUID =1L;

	private Integer id;
	private String content;
	private QuestionTemplate questionTemplate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public QuestionTemplate getQuestionTemplate() {
		return questionTemplate;
	}
	public void setQuestionTemplate(QuestionTemplate questionTemplate) {
		this.questionTemplate = questionTemplate;
	}
	
	
}
