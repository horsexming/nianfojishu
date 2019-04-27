package com.task.Server;

import java.util.List;

import com.task.entity.AssScore;
import com.task.entity.Template;

/**
 * 考核成绩服务层
 * 
 * @author 刘培
 * 
 */
public interface AssScoreServer {

	public boolean addAssScore(AssScore assScore, Template template,
			int templateId[], Float score[], Float sumCustomScore);// 添加考核成绩

	public List findAllAssScore(int pageNo, int pageSize, String status);// 查询成绩

	public int getCountByAll(String status);// 获得数量 (所有成绩)

	public List findAssScoreByCondition(AssScore assScore, int pageNo,
			int pageSize);// 条件查询成绩

	public int getCountByCondition(AssScore assScore);// 获得数量(条件查询成绩)

	public List findScoreViewByCarid(String cardId, String code);// 通过卡号以及工号生成用户成绩统计报表

	public String PreviewAssScore(AssScore assScore);// 生成成绩详细

	public AssScore findAssScoreById(int id);// 根据id查询

	public boolean delScore(AssScore assScore);// 删除分数以及分数详细

	public boolean findOldAssScore(AssScore assScore); // 查询是否已经打过分
	
	/**
	 * 
	 */
	public AssScore findOldAssScore1(AssScore assScore);
	/**
	 * 查询打分人员的部门部留是否存在
	 * 
	 * @return
	 */
	public boolean findContractBonus();
	
	/**
	 * 根据userId 查询
	 */

}
