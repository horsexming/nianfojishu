package com.task.Server.android.processpush;

import java.util.List;

import com.task.entity.TaSopGongwei;
import com.task.entity.android.processpush.Push;

public interface PushServer {

	Object[] findPush(Push push, int parseInt, int pageSize);

	void addProcessandmeid(Push push);

	Push findPushById(Integer pushId);

	void updateProcessandmeid(Push push);

	void delPush(Integer pushId);

	List android_Push(String meid);

	List findTaSopGongwei();

	List findGwidByname(Integer push_id);

	Object[] findPush1(TaSopGongwei taSopGongwei, int parseInt, int pageSize, Integer pushId);

	void addProcessandmeid1(Push push, TaSopGongwei taSopGongwei);

	void delGongwei(Integer id,Integer push_id);

	Push findPushBymeid(String meid);

	Push addProcessandmeid1(String meid,String ipAddress,String flatNum,String flatStation);

	List findPushgongweiById(Integer id);

	List findGwidByname1(Integer id);

	boolean binDingUsers(Push push, Integer[] gwId);



 
	

}
