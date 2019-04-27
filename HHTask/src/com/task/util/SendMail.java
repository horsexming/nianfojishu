package com.task.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.task.Dao.TotalDao;
import com.task.DaoImpl.TotalDaoImpl;

public class SendMail {
	
	public static String spareMail;
	public String spareMail1;
	public String spareMail2 = "15921533206@163.com";

	public SendMail() {

	}

	/***
	 * 有参构造方法(创建对象的时候可以直接调用发送邮件功能)
	 * 
	 * @param toReceiveMail
	 *            接收邮箱地址
	 * @param title
	 *            邮件标题
	 * @param content
	 *            邮件内容
	 * @throws MessagingException
	 */
	public SendMail(String toReceiveMail, String title, String content) {
		if (toReceiveMail == null || toReceiveMail.length() < 0) {
			toReceiveMail = this.getSpareMail1();
		}
		sendMail(toReceiveMail, title, content);
	}

	/**
	 * 发送邮件
	 * 
	 * @param toReceiveMail
	 *            接收邮箱地址
	 * @param title
	 *            邮件标题
	 * @param content
	 *            邮件内容
	 * @throws MessagingException
	 */
	public void sendMail(String toReceiveMail, String title, String content) {
		try {
			WebApplicationContext wac = ContextLoader
					.getCurrentWebApplicationContext();
			JavaMailSender sender = (JavaMailSender) wac.getBean("mailSender");
			MimeMessage mimeMsg = sender.createMimeMessage();
			MimeMessageHelper helper;
			helper = new MimeMessageHelper(mimeMsg, true, "UTF-8");
			helper.setTo(toReceiveMail);
			helper.setFrom(spareMail);
			helper.setSubject(title);
			helper.setText(content, true);
			sender.send(mimeMsg);
		} catch (MessagingException e) {
			System.out.println("发送邮件失败错误!");
		}
	}

	public String getSpareMail1() {
		String ip = "";
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		// if ("192.168.0.114".equals(ip)) {
		// spareMail1 = "15921533206@163.com";
		// } else if ("192.168.0.246".equals(ip)) {
		// spareMail1 = "cms-74@vip.163.com";
		// }

		return spareMail1;
	}
	

	public void setSpareMail1(String spareMail1) {
		this.spareMail1 = spareMail1;
	}

	public String getSpareMail2() {
		return spareMail2;
	}

	public void setSpareMail2(String spareMail2) {
		this.spareMail2 = spareMail2;
	}

}
