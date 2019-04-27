package com.task.Server.yw;

import com.task.entity.ExamineFlow;

/**
 * @Title: IExamineFlowService.java
 * @Package com.task.Server.yw
 * @Description: 审核流程接口
 * @author 曾建森
 * @date 2012-11-14 下午03:29:03
 * @version V1.0
 */
public interface IExamineFlowService {
	/**
	 * @Title: add
	 * @Description: 添加审核流程
	 * @param ef 
	 * @return void
	 * @throws
	 */
	public void add(ExamineFlow ef);
	/**
	 * @Title: del
	 * @Description: 根据ID删除审核流程
	 * @param id 
	 * @return void
	 * @throws
	 */
	public void del(int id);
	/**
	 * @Title: update
	 * @Description: 修改审核流程
	 * @param ef 
	 * @return void
	 * @throws
	 */
	public void update(ExamineFlow ef);
	/**
	 * @Title: getExamineFlowById
	 * @Description: 根据ID获取审核流程
	 * @param id
	 * @return ExamineFlow
	 * @throws
	 */
	public ExamineFlow getExamineFlowById(int id);
	/**
	 * @Title: getNextExamine
	 * @Description: 查询审核人
	 * @param map
	 * @return String
	 * @throws
	 */
	public String getNextExamine(String flow,String dept);
	/**
	 * @Title: getBackExamine
	 * @Description: 返回第一个审核
	 * @param @param flow
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String getFirstExamine(String dept);
	/**
	 * @Title: queryAllExamineFlow
	 * @Description: 查询所有审核流程
	 * @param @param pageNo
	 * @param @param pageSize
	 * @param @return 
	 * @return Object[]
	 * @throws
	 */
	public Object[] queryAllExamineFlow(int pageNo,int pageSize);
}
