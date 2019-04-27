package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.RewardPunish;
import com.task.entity.Users;

public interface RewardPunishServer {
	public Users findUserByCode(String code);//查询Users为input
	
	public String finAllMarkIdForSetlect();//查询加工单号(状态为领工序)为select
	
	public String addRewardPunish(RewardPunish rewardPunish);//添加奖扣记录
	
	public String deleteRewardPunish(RewardPunish rewardPunish);//删除奖扣记录
	
	public String updateRewardPunish(RewardPunish rewardPunish);//更新奖扣记录
	
	public RewardPunish getRewardPunishById(Integer id);//获得奖扣记录
	
	public Object[] findAllRewardPunish(Map map,int pageNo,int pageSize );//获得奖扣记录集合
	public List collectRewardPunishByMonth(Map map);//获得奖扣记录集合
}
