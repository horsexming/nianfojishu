package com.task.Server.question;

import java.util.Map;

import com.task.entity.question.Answer;
import com.task.entity.question.Question;

public interface QuestionServer {
	public boolean save(Question question);
	public boolean update(Question question);
	public Question findByOne(Integer id);
	public Answer find(Integer id);
	public boolean delete(Question question);
	public Map<Integer, Object> findAll(Question question, int pageNo, int pageSize);
	public Map<Integer, Object> findAnswer(Answer answer, int pageNo,
			int pageSize,String id);
	
}
