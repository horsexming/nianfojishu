package com.task.ServerImpl;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import com.sun.mail.imap.IMAPStore;
import com.task.Dao.TotalDao;
import com.task.Server.JavaMailService;
import com.task.entity.PassReal;
import com.task.entity.Password;
import com.task.util.AESEnctypeUtil;
import com.task.util.Util;

/**
 * 邮件服务类
 * @author wcy
 *
 */
public class JavaMailServiceImpl implements JavaMailService {

	private TotalDao totalDao;
	
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	private String path = Thread.currentThread().getContextClassLoader().getResource("/").getPath();

	@Override
	public String[] getJavaMailInfo() {
		int read = 0;
		int unRead = 0;
		//String pop3Server = "imap.sina.com";  
        //String protocol = "imap";
        Password pwd = Util.getLoginUser().getPassword();
        String user = pwd.getMailBox();
        String authorizationCode = pwd.getAuthorizationCode(); //授权码或密码
        String protocol = pwd.getMailPropocal();
        String host = pwd.getMailHost();
       // String user = "1435408518@qq.com";  
        //String pwd = "iccfamkcnshmbadj";  
          
        // 创建一个有具体连接信息的Properties对象  
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", protocol);
        props.setProperty("mail.imap.host", host);
        if( null ==protocol || "".equals(protocol) || null==host || "".equals(host) || 
        		null==user || "".equals(user) || null==pwd || "".equals(pwd)){
        	return null;
        }
       /* InputStream inputStream;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(path+"/javamail.properties"));
			props.load(inputStream);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
        //props.setProperty("mail.store.protocol", protocol);  //协议 使用的服务器名称
        //props.setProperty("mail.imap.host", pop3Server); 	//服务器地址
        //props.setProperty("mail.imap.auth", "true");
        //props.setProperty("mail.smtp.port", "994");//根据邮件服务器情况设定
        // 使用Properties对象获得Session对象  
        Session session = Session.getInstance(props);
        //session.setDebug(true);
        IMAPStore store = null;
        try {
        	AESEnctypeUtil util = new AESEnctypeUtil();
        	String password = util.dencype(authorizationCode);
			store = (IMAPStore) session.getStore(protocol);
			//System.out.println("创建连接成功");
			store.connect(user, password);
			//System.out.println("成功");
			 Folder folder = store.getFolder("inbox");
			/*System.out.println("收件箱中共" + folder.getMessageCount() + "封邮件!");
	        System.out.println("收件箱中共" + folder.getUnreadMessageCount() + "封未读邮件!");
	        System.out.println("收件箱中共" + folder.getNewMessageCount() + "封新邮件!");
	        System.out.println("收件箱中共" + folder.getDeletedMessageCount() + "封已删除邮件!");*/
	        read = folder.getMessageCount()-folder.getUnreadMessageCount();
	        unRead = folder.getUnreadMessageCount();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}finally{
			try {
				store.close();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		String loginPage=null;//"http://mail"+host.substring(host.indexOf("."));
		if(null!=pwd.getLoginPage()){
			loginPage = "https://exmail.qq.com/login";
		}else{
			loginPage="http://mail"+host.substring(host.indexOf("."));
		}
		
		return new String[]{read+"",unRead+"",loginPage};
	}
	
	//检查邮箱并设置邮箱登陆方式

	@Override
	public String checkEmailInfo(String mailname, String password) {
		
		String protocol = "imap";
		int firstInt = mailname.indexOf("@");
		int endInt = mailname.indexOf(".");
		String substring = mailname.substring(firstInt+1, endInt); //公司域名:如：163、qq、sina
		String host = protocol+"."+substring+".com"; //猜测域名
		
		Properties props = new Properties();
		props.setProperty("mail.imap.host", host);
		props.setProperty("mail.store.protocol", protocol);
		Session session = Session.getInstance(props);
		Store store =null;
		
		int i = 0;
		while(i<=4){
			i++;
			if(i==3){
				try {
					InputStream inputStream = new BufferedInputStream(new FileInputStream(path+"/javamail.properties"));
					props.load(inputStream);
					host = props.getProperty("mail.imap.host3");
					props.setProperty("mail.imap.host", host);
					session = Session.getInstance(props);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
    			store = session.getStore(protocol); //imap.XX.com
    			System.out.println("建立连接成功");
    			System.out.println(host);
    			try {
    				store.connect(mailname, password);
    				Password objPwd = Util.getLoginUser().getPassword();
    				objPwd.setMailBox(mailname);
    				objPwd.setMailHost(host);
    				objPwd.setMailPropocal("imap");
    				objPwd.setAuthorizationCode(password);
					AESEnctypeUtil util = new AESEnctypeUtil();
					String aeseAuth = util.enctype(password);
					//user.getPassword().setAuthorizationCode(aeseAuth);//邮箱密码
					objPwd.setAuthorizationCode(aeseAuth);
					if("imap.exmail.qq.com".equals(host)){
						objPwd.setLoginPage("https://exmail.qq.com/login");
					}else{
						objPwd.setLoginPage(null);
					}
    				totalDao.update(objPwd);
    				PassReal real = (PassReal) totalDao
    				.getObjectByCondition("from PassReal where uid=?", Util.getLoginUser().getId());
					real.setMailPass(password);
					totalDao.update(real);
    				return "邮箱验证成功！";
    			} catch (MessagingException e) {
    				System.out.println(e.toString());
    				try {
						store.close();
						System.out.println("关闭连接成功");
					} catch (MessagingException e1) {
					}
    				if(e.toString().indexOf(host)>0){//不识别的邮箱
    					//使用mail.XX.com
    					host = "mail."+substring+".com"; //猜测域名
    					props.setProperty("mail.imap.host", host);
    		    		props.setProperty("mail.store.protocol", protocol);
    					continue;
    					//return "系统不识别的邮箱，请联系管理员或换用其他邮箱";
    				}
    				if(e.toString().indexOf("LOGIN auth error")>0 ||   //判断sina邮箱 
    						e.toString().indexOf("LOGIN Login error password error")>0 ||  //判断163邮箱
    						e.toString().indexOf("Please using authorized code to login")>0 ||//判断qq邮箱 
    						e.toString().indexOf("AUTHENTICATIONFAILED")>0 ||//判断shhhes邮箱 
    						e.toString().toLowerCase().indexOf("password")>0 //其他邮箱
    					){
    					return "用户名或密码错误";
    				}
    				if(e.toString().indexOf("Connection timed out: connect")>0){
    					//使用mail.XX.com
    					host = "mail."+substring+".com"; //猜测域名
    					props.setProperty("mail.imap.host", host);
    		    		props.setProperty("mail.store.protocol", protocol);
    		    		if(i==4){
    		    			return "系统不识别的邮箱，请联系管理员或换用常用邮箱";
    		    		}
    					continue;
    				}
    				if(e.toString().indexOf("LOGIN Login error")>0){
    					//使用mail.XX.com
    					host = "mail."+substring+".com"; //猜测域名
    					props.setProperty("mail.imap.host", host);
    		    		props.setProperty("mail.store.protocol", protocol);
    		    		if(i==4){
    		    			return "邮箱不存在或者其他未知异常";
    		    		}
    					continue;
    				}
    				continue;
    			}
    		} catch (NoSuchProviderException e) {
    			System.out.println("建立连接失败");
    		}
    		try {
				if(store.isConnected()){
					store.close();
				}
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		return "邮箱有未知错误！";
	}

	
}
