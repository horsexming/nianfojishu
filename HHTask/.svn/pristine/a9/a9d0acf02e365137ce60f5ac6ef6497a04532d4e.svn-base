package com.task.action.question;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.question.QuestionServer;
import com.task.entity.codetranslation.CodeTranslation;
import com.task.entity.question.Answer;
import com.task.entity.question.Question;

@SuppressWarnings("serial")
public class QuestionAction extends ActionSupport{
	private QuestionServer questionServer;
	private Question question;
	private Answer answer;
	private List<Question> questionList;
	private List<Answer> answerList;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private String questionId;
	private int pageSize =5;
	public String toAdd(){
		if(question.getId()!=null){
			question = questionServer.findByOne(question.getId());
		}
			return "addAnswer";
	} 
	public String add(){
		boolean b = questionServer.save(question);
		if(b){
			successMessage="添加成功";
		}else{
			errorMessage="添加失败";
			return "error";
		}
		return "question_toList";
	}
	public String findAll(){
		Map<Integer, Object> map = questionServer
		.findAll(question, Integer
				.parseInt(cpage), pageSize);
		questionList = (List<Question>) map.get(1);
		if (questionList != null & questionList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("QuestionAction_findAll.action");
		}else{
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "questionList";
	}
	public String findAnswer(){
		question = questionServer.findByOne(Integer.parseInt(questionId));
		Map<Integer, Object> map = questionServer
		.findAnswer(answer, Integer
				.parseInt(cpage), 5,questionId);
		answerList = (List<Answer>) map.get(1);
		if (answerList != null & answerList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("QuestionAction_findAnswer.action?questionId="+questionId);
		}else{
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "answerList";
	}
	public String findOneAnswer(){
		answer =questionServer.find(answer.getId());
		question = questionServer.findByOne(Integer.parseInt(answer.getAnswerId()));
		return "answerDetail";
	}
	public String findFristAnswer(){
		question = questionServer.findByOne(question.getId());
	if(!"".equals(question.getAnswer())){
			return "questionDetail";
		}else{
				errorMessage="沒有回答";
			return "error";
			}
	}
	
	public String update(){
		boolean b = questionServer.update(question);
		if(b){
			errorMessage="回答成功";
		}else{
			errorMessage="回答失败";
		}
		this.setUrl("QuestionAction_findAnswer.action?questionId="+question.getId());
		return "error";
	}
	
	public QuestionServer getQuestionServer() { 
		return questionServer;
	}
	public void setQuestionServer(QuestionServer questionServer) {
		this.questionServer = questionServer;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getCpage() {
		return cpage;
	}
	public void setCpage(String cpage) {
		this.cpage = cpage;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<Question> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	
}
