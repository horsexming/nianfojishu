package com.task.Server;

/**
 * 邮件服务类
 * @author wcy
 *
 */
public interface JavaMailService {

	/**
	 * 获得已读未读邮件
	 * 0：总数
	 * 1：未读
	 * @return
	 */
	String [] getJavaMailInfo();
	
	/**
	 * 校验邮箱
	 * @return
	 */
	String checkEmailInfo(String mailname,String password);
}
