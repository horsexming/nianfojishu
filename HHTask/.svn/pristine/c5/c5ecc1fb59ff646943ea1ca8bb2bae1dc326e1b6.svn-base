package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.QuestionnairePerson;


public interface QuestionnairePersonServer {
	/**
	 * 查询所有
	 */
	public List<QuestionnairePerson> findAllQUlist(int pageNo, int pageSize );
	/**
	 * 条件查询
	 */
	public Map<Integer, Object> findQUlistByCondition(QuestionnairePerson qp,
			int pageNo, int pageSize,String statue);
	/**
	 * 添加
	 */
	public String add(QuestionnairePerson qp);
	/**
	 * 修改
	 */
	public boolean update(QuestionnairePerson qp);
	/**
	 * 删除
	 */
	public boolean del(QuestionnairePerson qp);
	/**
	 * 根据id获得
	 */
	public Map<Integer, Object> findQpByid(Integer id);
	
	/**
	 * 
	 */
	public int getcont();
	
}
