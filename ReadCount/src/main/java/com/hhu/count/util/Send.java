package com.hhu.count.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hhu.count.entity.SendMsg;
import com.hhu.count.server.CommonServer;
import com.hhu.count.serverImpl.CommonServerImpl;
public class Send {
	
	Integer num = 4;//定义一个小时内最多登录次数
	CommonServer commonServer = new CommonServerImpl();
	public Long SendMSM(String phone) {
		Long n ;
		System.out.println("123456");
		System.out.println(commonServer.SelByHour());
		if(commonServer.SelByHour()>num) {
			n=(long) -55;
			return n;
		}else {
			return send(phone);
		}
	}
	//信息发送
	public Long send(String phone) {
		HttpServletRequest request = null;
		int randomCode = (int)(Math.random()*8998)+1000+1;
		request.getSession().setAttribute("randomCode",randomCode);
		SendMassage sendMassage = new SendMassage(); 
		String msg = "尊敬用户您好，您申请的验证码为："+ randomCode + "若非本人操作请勿泄露。";
		System.out.println(msg);
		Long l = Long.parseLong(sendMassage.send(phone, msg));
		if(l >= 0){
			SendMsg sendMsg = new SendMsg();
			sendMsg.setPhone(phone);
			sendMsg.setSendcontent(msg);
			sendMsg.setSendTime(new Date());
			sendMsg.setSendnum(1);
			saveMag(sendMsg);
		} 
		return l;
	}
	
	public void saveMag(SendMsg sendMsg) {
		commonServer.InsSendMag(sendMsg);
	}
	
			
	 

}
