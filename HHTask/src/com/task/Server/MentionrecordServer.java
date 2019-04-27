package com.task.Server;

import java.util.List;

import com.task.entity.Mentionrecord;

public interface MentionrecordServer {

	//查询出提奖记录表所有信息
	public  List findMentionrecordAll(int pageNo, int pageSize);
	//查询出提奖记录表总数
	public int countMentionrecord();
	//条件查询出提奖记录表信息
	public List conditiontMentionrecord(Mentionrecord mentionrecord,int pageNo, int pageSize);
	
	//查询出条件查询的总数()
	public int countAll(Mentionrecord mentionrecord);
	//根据ID查询提奖记录相信
	public Mentionrecord findByID(int id); 
	//修改提奖记录表信息
	public boolean updateMentionrecord(Mentionrecord mentionrecord);
	//查询所有提奖记录表的所有信息 用于显示提奖总额走势图
	public List findViewcurve();
	//查询出提奖明细 用于显示月份的型别比例 饼图
	public List mentionPrizelist(String date,String date2);
	//查询出型别 
	public List typeList();
	//查询出型别走势图
	public List findType(String type);
	
	//根据月份查询出 提奖信息
	public List findDate(String date);
	public boolean updateExamTemplate(Integer[] detailSelect, String tag);
	public Object[] findExamList(int parseInt, int pageSize);
		
}
