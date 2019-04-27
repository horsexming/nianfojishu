package com.task.util;

import com.task.ServerImpl.SmsServiceImpl;
import com.task.entity.menjin.ResAccess;

public class SendThread extends Thread {

	public ResAccess resAccess;

	/**
	 * 发送Rtx消息
	 * @param resAccess
	 */
	public SendThread(ResAccess resAccess) {
		this.resAccess = resAccess;
	}
	
	public ResAccess getResAccess() {
		return resAccess;
	}

	public void setResAccess(ResAccess resAccess) {
		this.resAccess = resAccess;
	}

	public void run() {
		if("存取".equals(resAccess.getType())){
			if(resAccess.getQuseType()==0){
				RtxUtil.sendNotify(resAccess.getAddCode(), "快递单号："+resAccess.getCunCodes()+"已于"+resAccess.getCopenTime()+"存入。请尽快领取！取物验证码："+resAccess.getQuCodes(), 
						"快递领取提醒", "0", "0");
			}
		}else {
			if(resAccess.getQuseType()==2){//为收快递人取物操作
				RtxUtil.sendNotify(resAccess.getAddCode(), "您存放于编号为："+resAccess.getDaGuihao()+"的快件，已于"+resAccess.getQopenTime()+"取走。", 
						"快递领取提醒", "0", "0");
			}else {//寄快递人存物操作
				String send = "沪凯大厦3楼的快件已于"+resAccess.getCopenTime()+"存入"+resAccess.getDaGuihao()+"号柜。取物验证码："+resAccess.getQuCodes()+",请及时取件。谢谢！";
				String ss = new SmsServiceImpl().send(resAccess.getShouTel(),
						send);
				if(ss!=null&&ss.length()>10){
					ss = "成功";
				}else {
					ss = "失败("+ss+")";
				}
				RtxUtil.sendNotify(resAccess.getAddCode(), "您给取件人："+resAccess.getShouTel()+"的快件已于"+resAccess.getCopenTime()+"存入。取物验证码："+resAccess.getQuCodes()+"短信发送情况："+ss, 
						"快递领取提醒", "0", "0");
			}
			
		}
	}
}