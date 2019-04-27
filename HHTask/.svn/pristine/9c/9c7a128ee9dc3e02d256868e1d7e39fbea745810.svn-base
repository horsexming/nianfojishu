package com.task.ServerImpl.question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.question.QuestionServer;
import com.task.entity.question.Answer;
import com.task.entity.question.Question;
import com.task.entity.setupcheck.SetupCheck;
import com.task.util.Util;

public class QuestionServerImpl implements QuestionServer{
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}
	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	@Override
	public boolean save(Question question) {
		if(question!=null){
			question.setQuestionPerson(Util.getLoginUser().getName());
			question.setQuestionTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
			return totalDao.save(question);
		}
		return false;
	}

	@Override
	public boolean delete(Question question) {
		return totalDao.delete(question);
	}

	@Override
	public Question findByOne(Integer id) {
		return (Question)totalDao.get(Question.class,id);
	}

	@Override
	public boolean update(Question question) {
		Question q = findByOne(question.getId());
			q.setAnswer(question.getAnswer());
			q.setAnswerPerson(Util.getLoginUser().getName());
			q.setAnswerTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
			addAnswer(q);
		return totalDao.update(q);
	}


	@Override
	public Map<Integer, Object> findAll(Question question, int pageNo,
			int pageSize) {
		if (question == null) {
			question = new Question();
		}
		String hql = totalDao.criteriaQueries(question, null);
		hql += " order by id desc";
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}
	public boolean addAnswer(Question question){
		Answer a = new Answer();
		a.setAnswer(question.getAnswer());
		a.setPerson(question.getAnswerPerson());
		a.setTime(question.getAnswerTime());
		a.setAnswerId(question.getId().toString());
		return totalDao.save(a);
	}
	
	
	
	
	public Map<Integer, Object> findAnswer(Answer answer, int pageNo,
			int pageSize,String id) {
		if (answer == null) {
			answer = new Answer();
		}
		String sql="answerId='"+id+ "' order by id desc";
		String hql = totalDao.criteriaQueries(answer,sql,null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}
	@Override
	public Answer find(Integer id) {
		return (Answer)totalDao.get(Answer.class,id);
	}

	
	
}
