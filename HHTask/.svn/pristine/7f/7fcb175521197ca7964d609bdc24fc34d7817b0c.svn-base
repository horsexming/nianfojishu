package com.task.Server.zgkh;

import java.util.List;

import com.task.entity.AssScore;
import com.task.entity.zgkh.ScoreStatistics;

/***
 * 主管级考核总分Server层
 * 
 * @author 刘培
 * 
 */
public interface ScoreStatisticsServer {

	/***
	 * 添加总分
	 * 
	 * @param assScore
	 *            考核成绩类
	 * @return String
	 */
	public String addScoreStatistics(AssScore assScore);

	/***
	 * 根据用户id查询其总分表
	 * 
	 * @param userId
	 *            用户id
	 * @return ScoreStatistics对象
	 */
	public ScoreStatistics findSsByUserId(int userId, String mouth);

	/***
	 * 查询所有总分信息
	 * 
	 * @param pageNo
	 *            页数
	 * @param pageSize
	 *            每页个数
	 * @return Object[]
	 */
	public Object[] findAllSS(ScoreStatistics ss, int pageNo, int pageSize);

	/***
	 * 通过总分id查询所有成绩
	 * 
	 * @param ssId
	 * @return List<AssScore>
	 */
	public List<AssScore> findScoreBySSId(int ssId);

	/***
	 * 删除成绩后更新总分信息
	 * 
	 * @param assScore
	 *            考核成绩类
	 * @return boolean
	 */
	public boolean delScore(AssScore assScore);
}
