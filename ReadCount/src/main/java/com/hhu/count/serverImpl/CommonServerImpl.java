package com.hhu.count.serverImpl;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhu.count.dao.CommonDao;
import com.hhu.count.entity.SendMsg;
import com.hhu.count.server.CommonServer;
import com.hhu.count.util.SendMassage;
@Service("commonServer")
public class CommonServerImpl implements CommonServer {
	@Autowired
	CommonDao commonDao;
	public void DelOld(Integer num) {	
		commonDao.DelOld(num);
	}

	public List<SendMsg> SelByPhone(String phone) {
		return commonDao.SelByPhone(phone);
	}

	public void InsSendMag(SendMsg sendMsg) {
		commonDao.InsSendMag(sendMsg);
	}
	//查询一个小时内的发送记录数
	public Integer SelByHour() {
		return commonDao.SelByHour();
	}

	
	public void saveMag(SendMsg sendMsg) {
		commonDao.InsSendMag(sendMsg);
		
	}
	public Long send(String phone,int randomCode) {
		
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
			DelOld(30);
			saveMag(sendMsg);
		} 
		return l;
	}

	
	public Long SendMSM(String phone,int randomCode) {
		Long n ;
		System.out.println(commonDao.SelByHour());
		if(commonDao.SelByHour()>4) {
			n=(long) -55;
			return n;
		}else {
			return send(phone,randomCode);
		}
	}
	
	

}
