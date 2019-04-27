package com.task.ServerImpl;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.task.Dao.TotalDao;
import com.task.DaoImpl.TotalDaoImpl;
import com.task.Server.SmsService;
import com.task.Server.SmsUserService;
import com.task.entity.ShortMessage;
import com.task.entity.SmsUser;
import com.task.entity.Users;

public class SmsServiceImpl implements SmsService {
	private TotalDao totalDao;
	private SmsUserService smsUserService;

	@Override
	public void sendSms(String id, String msg) {
		String[] idArr = id.split(",");
		List<SmsUser> users = new ArrayList<SmsUser>();
		for (int i = 0; i < idArr.length; i++) {
			SmsUser user = smsUserService.get(Integer.parseInt(idArr[i]));
			if (user != null) {
				users.add(user);
			}
		}
		long l = sendSms(users, msg);

		if (l == -1) {
			throw new RuntimeException("短信帐号余额不足，请通知管理员！错误代码:-1");
		} else if (l == -2) {
			throw new RuntimeException("短信帐号ID错误，请通知管理员！错误代码:-2");
		} else if (l == -3) {
			throw new RuntimeException("短信帐号密码错误，请通知管理员！错误代码:-3");
		} else if (l == -4) {
			throw new RuntimeException("参数不够或参数内容的类型错误，请通知管理员！错误代码:-4");
		} else if (l == -12) {
			throw new RuntimeException("发送短信失败，请重新尝试，如果多次失败，请联系管理员！错误代码:-12");
		} else if (l == -13) {
			throw new RuntimeException("网络连接失败！");
		}

	}

	public long sendSms(List<SmsUser> users, String msg) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < users.size(); i++) {
			sb.append(users.get(i).getPhone()
					+ (i == (users.size() - 1) ? "" : ","));
		}

		return Long.parseLong(send(sb.toString(), msg));
	}

	@Override
	public String send(String mobiles, String msg) {
		String result = "-12";
		try {
			Document doc;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputStream is = getSoapInputStream("shhh", "x346928", mobiles, msg,
					"");
			if (is != null) {
				doc = db.parse(is);
				NodeList nl = doc.getElementsByTagName("SendMessagesResult");
				Node n = nl.item(0);
				result = n.getFirstChild().getNodeValue();
				is.close();
				
				/**保存消息记录*/
				TotalDao totalDao = TotalDaoImpl.findTotalDao();
				String[] mobilesArr = mobiles.split(",");
				Date now = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String nowStr = sdf.format(now);
				String hql = "from Users where password.phoneNumber = ?";
				try {
					for (int i = 0; i < mobilesArr.length; i++) {
						Users u = (Users) totalDao.getObjectByCondition(hql, mobilesArr[i]);
						if (u != null) {
							ShortMessage sm = new ShortMessage();
							sm.setPhone(mobilesArr[i]);
							sm.setName(u.getName());
							sm.setContent(msg);
							sm.setSendDate(nowStr);
							sm.setDept(u.getDept());
							sm.setCode(u.getCode());
							totalDao.save(sm);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		} catch (Exception e) {
			System.out.print("SmsSoap.sendSms error:" + e.getMessage());
			return "-12";
		}

	}

	private InputStream getSoapInputStream(String userid, String pass,
			String mobiles, String msg, String time) throws Exception {
		URLConnection conn = null;
		InputStream is = null;
		try {
			String soap = getSoapSmssend(userid, pass, mobiles, msg, time);
			if (soap == null) {
				return null;
			}
			try {
				// /http://winic.org/Development.asp官方网站
				// http://web.900112.com/Login.html 后台管理
				URL url = new URL("http://service2.winic.org:8003/Service.asmx");

				conn = url.openConnection();
				conn.setUseCaches(false);
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestProperty("Content-Length", Integer.toString(soap
						.length()));
				conn.setRequestProperty("Content-Type",
						"text/xml; charset=utf-8");
				conn.setRequestProperty("HOST", "service2.winic.org");
				conn.setRequestProperty("SOAPAction",
						"\"http://tempuri.org/SendMessages\"");

				OutputStream os = conn.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
				osw.write(soap);
				osw.flush();
				osw.close();
			} catch (Exception ex) {
				System.out.print("SmsSoap.openUrl error:" + ex.getMessage());
			}
			try {
				is = conn.getInputStream();
			} catch (Exception ex1) {
				System.out.print("SmsSoap.getUrl error:" + ex1.getMessage());
			}

			return is;
		} catch (Exception e) {
			System.out.print("SmsSoap.InputStream error:" + e.getMessage());
			return null;
		}
	}

	private String getSoapSmssend(String userid, String pass, String mobiles,
			String msg, String time) {
		try {
			String soap = "";
			soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
					+ "<soap:Body>"
					+ "<SendMessages xmlns=\"http://tempuri.org/\">" + "<uid>"
					+ userid + "</uid>" + "<pwd>" + pass + "</pwd>" + "<tos>"
					+ mobiles + "</tos>" + "<msg>" + msg + "</msg>" + "<otime>"
					+ time + "</otime>" + "</SendMessages>" + "</soap:Body>"
					+ "</soap:Envelope>";
			return soap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public SmsUserService getSmsUserService() {
		return smsUserService;
	}

	public void setSmsUserService(SmsUserService smsUserService) {
		this.smsUserService = smsUserService;
	}

}
