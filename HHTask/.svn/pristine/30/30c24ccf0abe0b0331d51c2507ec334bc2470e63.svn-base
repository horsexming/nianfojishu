package com.task.ServerImpl.yw;

import java.util.List;
import com.task.Dao.TotalDao;
import com.task.Server.yw.IExamineFlowService;
import com.task.entity.ExamineFlow;

/**
 * @Title: FlowUtil.java
 * @Package com.task.ServerImpl.yw
 * @Description: 审核流程判断
 * @author 曾建森
 * @date 2012-11-14 上午09:46:20
 * @version V1.0
 */
public class ExamineFlowServiceImpl implements IExamineFlowService{

	private TotalDao totalDao;
	
	/**
	 * @Title: getNextExamine
	 * @Description: 查询审核人
	 * @param map
	 * @return String
	 * @throws
	 */
	public String getNextExamine(String flow, String dept) {
		if (dept != null && !dept.equals("")) {
			String hql = "from ExamineFlow e where e.dept = ?";
			List list = totalDao.query(hql, dept);
			if (list != null && list.size() > 0) {
				ExamineFlow ef = (ExamineFlow) list.get(0);
				String[] strFlow = ef.getFlow().split("-");
				if (flow != null && !flow.equals("")) {
					for (int i = 0; i < strFlow.length; i++) {
						if (flow.equals(strFlow[i].toString())) {
							if (i != (strFlow.length - 1)) {
								return strFlow[i+1].toString();
							} else {
								return "通过";
							}
						}
					}
				}
			}
		}
		return null;
	}
	/**
	 * @Title: getBackExamine
	 * @Description: 返回第一个审核
	 * @param @param flow
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String getFirstExamine(String dept) {
		if(dept != null){
			List list = totalDao.query("from ExamineFlow where dept = ?",dept);
			if(list != null && list.size() > 0){
				ExamineFlow ef = (ExamineFlow) list.get(0);
				String[] strFlow = ef.getFlow().split("-");
				return strFlow[0].toString();
			}
		}
		return null;
	}
	/**
	 * @Title: add
	 * @Description: 添加审核流程
	 * @param ef 
	 * @return void
	 * @throws
	 */
	public void add(ExamineFlow ef) {
		totalDao.save(ef);
	}
	/**
	 * @Title: del
	 * @Description: 根据ID删除审核流程
	 * @param id 
	 * @return void
	 * @throws
	 */
	public void del(int id) {
		ExamineFlow ef = (ExamineFlow) totalDao.getObjectById(ExamineFlow.class, id);
		totalDao.delete(ef);
	}
	/**
	 * @Title: update
	 * @Description: 修改审核流程
	 * @param ef 
	 * @return void
	 * @throws
	 */
	public void update(ExamineFlow ef) {
		totalDao.update(ef);
	}
	/**
	 * @Title: getExamineFlowById
	 * @Description: 根据ID获取审核流程
	 * @param id
	 * @return ExamineFlow
	 * @throws
	 */
	public ExamineFlow getExamineFlowById(int id) {
		return (ExamineFlow) totalDao.getObjectById(ExamineFlow.class, id);
	}
	/**
	 * @Title: queryAllExamineFlow
	 * @Description: 查询所有审核流程
	 * @param @param pageNo
	 * @param @param pageSize
	 * @param @return 
	 * @return Object[]
	 * @throws
	 */
	public Object[] queryAllExamineFlow(int pageNo, int pageSize) {
		String hql = "from ExamineFlow";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);	
		int count =	totalDao.getCount(hql);
		return new Object[]{list,count};
	}
	
	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	
}
