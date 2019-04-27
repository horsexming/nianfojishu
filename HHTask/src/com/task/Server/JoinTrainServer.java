package com.task.Server;

import com.task.entity.JoinTrain;
import com.task.entity.JoinTrainDetails;

import java.util.List;

/***
 * 入职培训Server层
 * 
 * @author 刘培
 * 
 */
public interface JoinTrainServer {

	public boolean addJoinTrain(JoinTrain jointrain, JoinTrainDetails jtDetails);// 添加入职培训

	public List<JoinTrain> findJoinTrainByUid(Integer userId);// 根据用户id查询入职培训

	public boolean delJoinTrain(JoinTrain jointrain);// 删除入职培训记录以及明细

	public JoinTrain findJoinTrainById(Integer id);// 根据id查询培训记录
}
