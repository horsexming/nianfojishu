package com.task.action;

import java.util.List;
import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.NoticeServer;
import com.task.entity.Notice;
import com.task.util.MKUtil;

public class NoticeAction extends ActionSupport {
	private NoticeServer noticeServer;
	private Notice notice;
	private List list;
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String errorMessage;
	private String pageStatus;
	public String addPage(){
		return "addpage";
	}
	//查询公告列表
	public String Search(){
//		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
//		// 设定mail server
//		senderImpl.setHost("smtp.163.com");
//
//		// 建立邮件消息
//		SimpleMailMessage mailMessage = new SimpleMailMessage();
//		// 设置收件人，寄件人 用数组发送多个邮件
//		// String[] array = new String[] {"sun111@163.com","sun222@sohu.com"};
//		// mailMessage.setTo(array);
//		mailMessage.setTo("940080073@qq.com");
//		mailMessage.setFrom("liupei_yx@163.com");
//		mailMessage.setSubject("测试简单文本邮件发送！");
//		mailMessage.setText(" 测试我的简单邮件发送机制！！ ");
//
//		senderImpl.setUsername("liupei_yx@163.com"); // 根据自己的情况,设置username
//		senderImpl.setPassword("WOwo1314aini"); // 根据自己的情况, 设置password
//
//		Properties prop = new Properties();
//		prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
//		prop.put("mail.smtp.timeout", "25000");
//		senderImpl.setJavaMailProperties(prop);
//		// 发送邮件
//		senderImpl.send(mailMessage);
//
//		System.out.println(" 邮件发送成功.. ");
		list = noticeServer.find(Integer.parseInt(cpage), pageSize);
		this.setUrl("NoticeAction!Search.action");
		int count =noticeServer.getcount();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		return "search";
	}
	//保存公告信息
	public String save(){
		noticeServer.save(notice);
		return "save";
	}
	//查看公告内容
	public String look(){
		notice = noticeServer.look(notice.getId());
		return "addpage";
	}
	//修改公告内容
	public String update(){
		Notice a =noticeServer.look(notice.getId());
		a.setContent(notice.getContent());
		a.setTitle(notice.getTitle());
		noticeServer.update(a);
		return "save";
	}
	//修改公告状态
	public String alter(){
		notice = noticeServer.look(notice.getId());
		if(notice.getStatus().equals("隐藏")){
			notice.setStatus("显示");
		}else{
			notice.setStatus("隐藏");
		}
		noticeServer.update(notice);
		return "save";
	}
	//删除公告
	public String delete(){
		noticeServer.delete(notice);
		return "save";
	}
	//显示公告
	public String show(){
		list=noticeServer.seek();
		MKUtil.writeJSON(list);
		return null;
	}

	public void setNoticeServer(NoticeServer noticeServer) {
		this.noticeServer = noticeServer;
	}
	public NoticeServer getNoticeServer() {
		return noticeServer;
	}
	public void setNotice(Notice notice) {
		this.notice = notice;
	}
	public Notice getNotice() {
		return notice;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getTotal() {
		return total;
	}
	public void setList(List list) {
		this.list = list;
	}
	public List getList() {
		return list;
	}
	public void setCpage(String cpage) {
		this.cpage = cpage;
	}
	public String getCpage() {
		return cpage;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}
	public String getPageStatus() {
		return pageStatus;
	}
}
