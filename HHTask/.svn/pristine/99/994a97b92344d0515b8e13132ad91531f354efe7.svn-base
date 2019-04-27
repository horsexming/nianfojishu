package com.task.Server;

import java.util.ArrayList;
import java.util.List;

import com.task.entity.ShortMessage;
import com.task.entity.Users;

public interface ShortMessageService {

	/**
	 * 拿到所有有手机号的人
	 * 
	 * @param usersInput
	 */
	ArrayList<Users> queryUsers(Users usersInput);

	/** 根据用户,获取到手机号，发送短信。 */
	String send(List<Users> usersSend, String msg);

	/** 根据用户,获取到手机号，发送短信。 */
	String send(String msg, Users... usersSend);

	/** 根据手机号发送短信。多个手机号用 ","（逗号）分开 */
	String send(String mobiles, String msg);

	/** 根据手机号发送短信。如果只有一个，下标为0就行了。 */
	String send(String[] mobiles, String msg);

	/** 根据工号拿到用户 */
	ArrayList<Users> get(List<Users> usersSend);

	/** 列出所有的发送记录 */
	ArrayList<ShortMessage> list();

	/** 条件查询 */
	ArrayList<ShortMessage> query(ShortMessage shortMessage);

	String dsSendMes();

}
