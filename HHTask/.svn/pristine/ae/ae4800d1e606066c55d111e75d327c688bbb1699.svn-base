package com.task.Server.renshi;

import java.util.List;
import java.util.Map;

import com.task.entity.renshi.Inter_Family;
import com.task.entity.renshi.InterviewLog;

/**
 * 人事面试单
 * @author 李聪
 *
 */
public interface InterviewLogServer {
	
	/**
	 * 添加面试单对象
	 * @param interviewLog
	 * interFamilies family信息
	 * @return
	 */
	public boolean addInterviewLog(InterviewLog interviewLog, List<Inter_Family> interFamilies);
	
	/**
	 * 通过id删除面试单对象
	 * @param interviewLog
	 * @return
	 */
	public boolean deleteInterviewLog(Integer id);
	
	/**
	 * 修改面试单对象
	 * @param interviewLog
	 * @return
	 */
	public boolean updateInterviewLog(InterviewLog interviewLog);
	
	/**
	 * 通过id查询面试单对象
	 * @param interviewLog
	 * @return
	 */
	public InterviewLog getInterviewLogById(Integer id);
	
	/**
	 * 查询所有面试单对象
	 * @param interviewLog
	 * @return
	 */
	public List<InterviewLog> findAll();
	
	/**
	 * 按条件分页查询面试单
	 * @param interviewLog 对象
	 * @param pageNo 起始页
	 * @param pageSize 每页数量
	 * @return
	 */
	public Map<Integer, Object> findInterviewLogsByCondition(InterviewLog interviewLog, int pageNo, int pageSize, String ccTag);
	
	/**
	 * 通过条件查找用户(bai)
	 * 
	 */
	public Object[] findInterviewsByCondition(InterviewLog interviewLog, int pageNo, int pageSize);// 条件查询
	//通过面试单ID查找家庭信息集合
	public List<Inter_Family> getinterFamilyById(Integer id);
	/**
	 * 查询是否使用PEBS考勤机
	 * @return
	 */
	public boolean findadmin();
}
