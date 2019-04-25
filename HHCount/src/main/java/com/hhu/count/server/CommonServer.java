package com.hhu.count.server;

import java.util.List;

import com.hhu.count.entity.SendMsg;

public interface CommonServer {
	//删除发送过早信息记录
		public void DelOld(Integer num);
		//通过手机号查询登短信发送记录
		public List<SendMsg> SelByPhone(String phone);
		//添加阅读记录
		public void InsSendMag(SendMsg sendMsg);
		//查询一个小时内的发送记录数
		public Integer SelByHour();
		//保存发送记录
		public void saveMag(SendMsg sendMsg);
		//发送信息
		public Long send(String phone,int randomCode);
		//发送
		public Long SendMSM(String phone,int randomCode);

}
