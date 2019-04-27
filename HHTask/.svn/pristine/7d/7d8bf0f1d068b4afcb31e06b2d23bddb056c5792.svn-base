package com.task.util;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.DaoImpl.TotalDaoImpl;
import com.task.ServerImpl.UserFacialInforServerImpl;
import com.task.entity.codetranslation.CodeTranslation;

public class LedSendServer extends Thread {
	private int number;
	int CardNum;
	String chContent;
	String[] chContents;
	Integer ledId;
	String url;
	int iActionType;
	int iHeight;// 高度
	int iWidth;// 宽度
	int fontSize;// 宽度
	int color = 0x00FF00;// 颜色
	String sendStatus;
	
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 在静态方法调用totalDao
	private static TotalDao createTotol() {
		// 获得totalDao
		TotalDao totalDao = TotalDaoImpl.findTotalDao();
		LedSendServer acc = new LedSendServer();
		acc.setTotalDao(totalDao);
		return totalDao;
	}

	public LedSendServer() {
	}

	/****
	 * 有参的构造函数(推送服务器)
	 */
	public LedSendServer(int ledId, String url) {
		this.ledId = ledId;
		this.url = url;
	}

	/****
	 * 有参的构造函数(推送服务器)
	 */
	public LedSendServer(int CardNum, String chContent, int color) {
		this.CardNum = CardNum;
		this.chContent = chContent;
		this.color = color;
	}

	/****
	 * 有参的构造函数(单条数据)
	 */
	public LedSendServer(int CardNum, String chContent, int iActionType,
			int ledId, int width, int height, int color, int fontSize) {
		this.CardNum = CardNum;
		this.chContent = chContent;
		this.ledId = ledId;
		this.iActionType = iActionType;
		this.iHeight = height;
		this.iWidth = width;
		this.color = color;
		this.fontSize = fontSize;
	}

	/****
	 * 有参的构造函数(单条数据)
	 */
	public LedSendServer(int CardNum, String chContent, int iActionType,
			int ledId, int width, int height, int color, int fontSize,
			String sendStatus) {
		this.CardNum = CardNum;
		this.chContent = chContent;
		this.ledId = ledId;
		this.iActionType = iActionType;
		this.iHeight = height;
		this.iWidth = width;
		this.sendStatus = sendStatus;
		this.color = color;
		this.fontSize = fontSize;
	}

	/****
	 * 有参的构造函数(多条数据)
	 */
	public LedSendServer(int CardNum, String[] chContents, int iActionType,
			int ledId, int width, int height, int color, int fontSize,
			String sendStatus) {
		this.CardNum = CardNum;
		this.chContents = chContents;
		this.ledId = ledId;
		this.iActionType = iActionType;
		this.iHeight = height;
		this.iWidth = width;
		this.sendStatus = sendStatus;
		this.color = color;
		this.fontSize = fontSize;
	}

	/****
	 * 有参的构造函数(多条数据)
	 */
	public LedSendServer(int CardNum, String chContent, String[] chContents,
			int iActionType, int ledId, int width, int height, int fontSize,
			String sendStatus) {
		this.CardNum = CardNum;
		this.chContents = chContents;
		this.ledId = ledId;
		this.iActionType = iActionType;
		this.iHeight = height;
		this.iWidth = width;
		this.sendStatus = sendStatus;
		this.fontSize = fontSize;

	}

	@SuppressWarnings("unchecked")
	public void sendLedMs() {
		try {
			// String serverIp = "192.168.0.113";
			// String serverPort = "6060/SendMsServer";
			// url = "http://"+serverIp+"/"+serverPort;
			// String serverPort = "8080/HHTask";
			// TotalDao totalDao = createTotol();
			// CodeTranslation translation = null;
			// List<CodeTranslation> ll =
			// totalDao.query("from CodeTranslation where keyCode = 'LEDSERVER'");
			// if(!ll.isEmpty()){
			// translation = ll.get(0);
			// }
			// if(translation!=null)
			// url = translation.getValueCode();
			// else {
			// url = "http://192.168.0.113:6060/SendMsServer";
			// }
			HttpRequest req = new HttpRequest();
			Map<String, Object> map = new HashMap<String, Object>();
			if (chContent != null && chContent.length() > 0) {
				// 编码
				chContent = URLEncoder.encode(URLEncoder.encode(chContent,
						"utf-8"), "utf-8");
			}
			if (ledId != null && ledId > 0) {
				map.put("lED.id", ledId);
			}
			map.put("lED.number", CardNum);
			map.put("sendcontext", chContent);
			map.put("sendColor", color);
			req.sendHttpPosts(url + "/lEDAction_sendGongWeiMs.action", map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/***
	 * 线程运行的主体
	 */
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// synchronized (this) {
		// while (0 != number) {
		// try {
		// wait();
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
		/***************
		 * 样式(1、64*32 居中左移(64*25) 2、64*32 两条左移 3、64*32 连续上移 4、192*64 倒计时&连续上移
		 * 5、192*64 倒计时&单条左移 )
		 ***********************/
		// if ("1".equals(sendStatus)) {
		// LedSendUtil.OnAddtext(CardNum, chContent, iActionType, ledId,
		// iWidth, iHeight, color, fontSize);
		// } else if ("2".equals(sendStatus)) {
		// // 2、64*32 两条左移
		// } else if ("3".equals(sendStatus)) {
		// // 3、64*32 连续上移
		// LedSendUtil.OnAddtextUp(CardNum, chContent, 5, ledId, iWidth,
		// iHeight, color, fontSize);
		// } else if ("4".equals(sendStatus)) {
		// // 4、192*64* 倒计时&连续上移
		// LedSendUtil.OnAddtextUp(CardNum, chContents, 5, ledId, iWidth,
		// iHeight, color, fontSize);
		// } else if ("5".equals(sendStatus)) {
		// // 5、192*64 倒计时&单条左移
		// LedSendUtil.OnAddtextLeft(CardNum, chContents, iActionType,
		// ledId, iWidth, iHeight, color, fontSize);
		// }

		// 和推送服务器通讯
		sendLedMs();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// number++;
		// notify();
		// }
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
