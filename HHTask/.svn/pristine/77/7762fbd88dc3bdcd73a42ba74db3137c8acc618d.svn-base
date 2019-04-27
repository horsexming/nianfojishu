package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.QuestionTemplate;
import com.task.entity.Questionnaire;


public interface QuestionTemplateServer {
	/**
	 *查询所有 
	 */
	public List<QuestionTemplate> showAllqtList(int pageNo, int pageSize);
	
	/**
	 * 条件查询；
	 */
	public Map<Integer, Object> findqtListByCondition(QuestionTemplate qt,
			int pageNo, int pageSize,String statue);
	/**
	 * 添加
	 */
	public boolean add(QuestionTemplate qt);
	
	/**
	 * 修改
	 */
	public boolean update(QuestionTemplate qt);
	/**
	 * 删除
	 */
	public boolean del(QuestionTemplate qt);
	/**
	 * 根据id获得QuestionTemplate
	 */
	public QuestionTemplate findqtbyId(Integer id);
	/**
	 * 获得总条数
	 */
	public int getcont();
	
	/**
	 * 根据 调查模板Id 查询 调查问卷明细
	 */
	public Map<Integer, Object> findqeList(Integer id);
	
	/**
	 * 修改 Questionnaire 调查问卷明细
	 */
	public boolean updateqe(Questionnaire qe);
	/**
	 * 删除Questionnaire 调查问卷明细
	 */
	public boolean delqe(Questionnaire qe);
	/**
	 * 根据id 获得调查问卷明细
	 */
	public Questionnaire findqebyId(Integer id);
}
