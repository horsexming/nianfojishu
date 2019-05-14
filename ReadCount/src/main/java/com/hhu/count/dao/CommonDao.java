package com.hhu.count.dao;
/*
 * 
 * 公共接口
 * 
 * */

import java.util.List;
import com.hhu.count.entity.SendMsg;
public interface CommonDao {
	//删除发送过早信息记录
	public void DelOld(Integer num);
	//通过手机号查询登短信发送记录
	public List<SendMsg> SelByPhone(String phone);
	//添加阅读记录
	public void InsSendMag(SendMsg sendMsg);
	//查询一个小时内的发送记录数
	public Integer SelByHour();

}
