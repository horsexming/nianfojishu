package com.task.action.led;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.led.LEDServer;
import com.task.action.UsersAction;
import com.task.entity.led.LED;
import com.task.entity.led.LEDLog;
import com.task.util.HttpRequest;
import com.task.util.HttpResponse;
import com.task.util.LedSendUtil;
import com.task.util.MKUtil;

public class LEDAction {
	private LEDServer lEDServer;// LED分类服务层
	private LED lED;// LED对象
	private List<LED> lEDList;// LED列表
	private LEDLog lEDLog;// LEDLog对象
	private List<LEDLog> lEDLogList;// lEDLog列表

	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private String sendcontext;// 发送内容
	private Integer sendColor;// 发送内容颜色

	private int count;// 绑定技能系数数量
	private int[] checkboxs;// 将要绑定的技能系数的id;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private String cpage2 = "1";
	private String total2;
	private String url2;
	private int pageSize = 15;
	private String pageStatus = "show";

	/**
	 * 分页显示LED分类
	 * 
	 * @return
	 */
	public String showList() {
		if (lED != null) {
			ActionContext.getContext().getSession().put("lED", lED);
		} else {// 用来保持分页时带着查询条件
			lED = (LED) ActionContext.getContext().getSession().get("lED");
		}
		Map<Integer, Object> map = lEDServer.findLEDsByCondition(lED, Integer
				.parseInt(cpage), pageSize);
		lEDList = (List<LED>) map.get(1);
		if (lEDList != null & lEDList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("lEDAction_showList.action?pageStatus=" + pageStatus);
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "led_show";
	}
	

	/**
	 * 验证LED数
	 * 
	 * @param selfUrl
	 * @return
	 */
	public String validateCount(String selfUrl) {
		int count = lEDServer.oneLEDCount();
		HttpRequest httpRequest = new HttpRequest();
		Map<String, String> map = new HashMap<String, String>();
		map.put("companyUrl", selfUrl);
		String result = null;
		try {
			HttpResponse httpResponse = httpRequest.sendHttpPost(UsersAction.mainUrl//"http://192.168.0.161:8080/HHTask"
					+ "/companyInfoAction_getOnLEDCount.action", map);
			result = httpResponse.getDataString();
		} catch (IOException e1) {
			e1.printStackTrace();
			// return "服务器连接失败,请稍候重试!";
			return "电子看板添加数目前已达到上限，目前不能添加！";
		}
		if (result == null) {
			return "通过";
		}else {
			Integer onlineCount = Integer.parseInt(result);
			if (onlineCount <= count) {
				return "对不起！LED添加数目前已达到上限"+onlineCount+"，目前不能添加！";
			} else {
				return "通过";
			}
		}
	}
	

	public String add() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String selfUrl = UsersAction.getSelfUrl(request);
		boolean b1 = UsersAction.validateLic(selfUrl);
		if (b1) {
			errorMessage = validateCount(selfUrl);
			if (!"通过".equals(errorMessage)) {
				url = "lEDAction_showList.action?pageStatus=manage";
				return "error";
			}
		}
//		boolean b = lEDServer.add(lED);
//		if (b) {
//			successMessage = "添加成功";
//		} else {
//			successMessage = "添加失败";
//		}
		return showList();
	}
	
	public String copyAdd() {
		lEDServer.copyAdd(lED.getId());lED=null;
		return showList();
	}

	public String toupdate() {
		lED = lEDServer.getLEDById(lED.getId());
		if (lED != null) {
			if (pageStatus != null && pageStatus.equals("manage")) {
				return "led_update";
			} else {
				return "led_update2";
			}
		} else {
			return showList();
		}
	}

	public String update() {
		boolean b = lEDServer.update(lED, pageStatus);
		return showList();
	}

	public String delete() {
		boolean b = lEDServer.deleteLEDById(lED.getId());
		lED = null;
		return showList();
	}

	public String lEDLogView() {
		if (lEDLog != null) {
			ActionContext.getContext().getSession().put("lEDLog", lEDLog);
		} else {// 用来保持分页时带着查询条件
			lEDLog = (LEDLog) ActionContext.getContext().getSession().get(
					"lEDLog");
		}
		lED = lEDServer.getLEDById(lED.getId());
		Map<Integer, Object> map = lEDServer.findLEDLogsByCondition(lEDLog,
				Integer.parseInt(cpage2), pageSize, " led.id=" + lED.getId());
		lEDLogList = (List<LEDLog>) map.get(1);// 显示页的技能系数列表
		if (lEDLogList != null & lEDLogList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal2(pageCount + "");
			this.setUrl2("lEDAction_lEDLogView.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "led_ledlogview";
	}

	/***
	 * 推送默认工位
	 * 
	 * @return
	 */
	public void sendMRGongWei() {
		lEDServer.sendGongWeiMs(lED.getId());
	}

	/***
	 * 推送默认工位
	 * 
	 * @return
	 */
	public void sendGongWeiMs() {
		if (lED != null && lED.getId() != null && lED.getId() > 0) {
			lEDServer.sendGongWeiMs(lED.getId());
		} else if (lED.getNumber() != null) {
			if (sendcontext != null && sendcontext.length() > 0) {
				try {
					sendcontext = URLDecoder.decode(sendcontext, "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			lEDServer.sendOtherMs(lED.getNumber(), sendcontext, sendColor);
		}
	}

	public LEDServer getlEDServer() {
		return lEDServer;
	}

	public void setlEDServer(LEDServer lEDServer) {
		this.lEDServer = lEDServer;
	}

	public LED getlED() {
		return lED;
	}

	public void setlED(LED lED) {
		this.lED = lED;
	}

	public List<LED> getlEDList() {
		return lEDList;
	}

	public void setlEDList(List<LED> lEDList) {
		this.lEDList = lEDList;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int[] getCheckboxs() {
		return checkboxs;
	}

	public void setCheckboxs(int[] checkboxs) {
		this.checkboxs = checkboxs;
	}

	public String getCpage() {
		return cpage;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<LEDLog> getlEDLogList() {
		return lEDLogList;
	}

	public void setlEDLogList(List<LEDLog> lEDLogList) {
		this.lEDLogList = lEDLogList;
	}

	public String getCpage2() {
		return cpage2;
	}

	public void setCpage2(String cpage2) {
		this.cpage2 = cpage2;
	}

	public String getTotal2() {
		return total2;
	}

	public void setTotal2(String total2) {
		this.total2 = total2;
	}

	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public LEDLog getlEDLog() {
		return lEDLog;
	}

	public void setlEDLog(LEDLog lEDLog) {
		this.lEDLog = lEDLog;
	}

	public String getSendcontext() {
		return sendcontext;
	}

	public void setSendcontext(String sendcontext) {
		this.sendcontext = sendcontext;
	}

	public Integer getSendColor() {
		return sendColor;
	}

	public void setSendColor(Integer sendColor) {
		this.sendColor = sendColor;
	}

}