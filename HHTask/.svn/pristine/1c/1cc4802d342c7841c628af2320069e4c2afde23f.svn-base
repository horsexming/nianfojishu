package com.task.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.ScreenContentServer;
import com.task.Server.ScreenService;
import com.task.entity.Screen;
import com.task.entity.ScreenContent;
import com.task.entity.TaSopGongwei;
import com.task.entity.codetranslation.CodeTranslation;
import com.task.entity.printer.Printer;
import com.task.util.HttpRequest;
import com.task.util.HttpResponse;
import com.task.util.MKUtil;

public class ScreenAction extends ActionSupport {
	private Screen screen;
	private List<Screen> screens;
	private List<TaSopGongwei> gongweis;
	private List<ScreenContent> screencontents;
	private List<ScreenContent> screencontentList;
	private List scList;
	private Integer id;
	private Integer page;
	private Integer rows;
	private Integer count;
	private Printer printer;
	private List<Printer> printerList;
	private ScreenService screenService;
	private ScreenContentServer screenContentServer;
	private String errorMessage;
	private String cpage = "1";
	private int pageSize = 15;
	private String total;
	private String url;

	public String addPage() {
		return "addPage";
	}

	/**
	 * 验证电子看板点数
	 * 
	 * @param selfUrl
	 * @return
	 */
	public String validateCount(String selfUrl) {
		int count = screenService.oneScreenCount();
		HttpRequest httpRequest = new HttpRequest();
		Map<String, String> map = new HashMap<String, String>();
		map.put("companyUrl", selfUrl);
		String result = null;
		try {
			HttpResponse httpResponse = httpRequest.sendHttpPost(
					UsersAction.mainUrl// "http://192.168.0.161:8080/HHTask"
							+ "/companyInfoAction_getoneScreenCount.action",
					map);
			result = httpResponse.getDataString();
		} catch (IOException e1) {
			e1.printStackTrace();
			// return "服务器连接失败,请稍候重试!";
			return "电子看板添加数目前已达到上限，目前不能添加！";
		}
		if (result == null) {
			return "通过";
		} else {
			Integer onlineCount = Integer.parseInt(result);
			if (onlineCount <= count) {
				return "对不起！电子看板添加数目前已达到上限 " + onlineCount + "，目前不能添加！";
			} else {
				return "通过";
			}
		}
	}

	public String add() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String selfUrl = UsersAction.getSelfUrl(request);
		boolean b = UsersAction.validateLic(selfUrl);
		if (b) {
			errorMessage = validateCount(selfUrl);
			if (!"通过".equals(errorMessage)) {
				MKUtil.writeJSON(false, "添加失败：" + errorMessage, null);
				return null;
			}
		}
		try {
			screen.setGongweis(new HashSet<TaSopGongwei>(screen
					.getGongweiList()));
			screenService.add(screen);
			MKUtil.writeJSON(true, "添加成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "添加失败：" + e.getMessage(), null);
		}
		return null;
	}

	// 绑定screencontent
	public String addscreencontent() {
		try {
			screen.setScreencontents(new HashSet<ScreenContent>(screen
					.getScreencontentList()));
			screenService.addscreencontent(screen);
			MKUtil.writeJSON(true, "添加成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "添加失败：" + e.getMessage(), null);
		}
		return null;
	}

	// screencontent列表
	public String showscreencontent() {
		Object[] o = screenContentServer.ScreenContentAllList();
		screencontentList = (List<ScreenContent>) o[0];
		return "screenShowContent";
	}

	public String showbindscreencontentbyid() {
		String[] o = screenService.getScreenContentbyid(screen.getId());
		MKUtil.writeJSON(o);
		return null;
	}

	public String listPage() {
		screens = screenService.list();
		return "listPage";
	}

	public String getWorkStation() {
		gongweis = screenService.getChild(screen);
		return "getWorkStation";
	}

	public String editPage() {
		screen = screenService.get(screen.getId());
		screen.setDesc(htmlspecialchars(screen.getDesc()));
		return "editPage";
	}

	public String edit() {
		try {
			screen.setGongweis(new HashSet<TaSopGongwei>(screen
					.getGongweiList()));
			screenService.update(screen);
			MKUtil.writeJSON(true, "修改成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "修改失败：" + e.getMessage(), null);
		}
		return null;
	}

	public String delete() {
		try {
			screenService.delete(screen);
			MKUtil.writeJSON(true, "删除成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "删除失败：" + e.getMessage(), null);
		}
		return null;
	}

	/**
	 * for android
	 * 
	 * @return
	 */
	public String getItem() {
		screens = screenService.getAll();
		for (int i = 0; i < screens.size(); i++) {
			screens.get(i).setGongweiList(null);
			screens.get(i).setGongweis(null);
			screens.get(i).setDesc(null);
		}
		MKUtil.writeJSON(screens);
		return null;
	}

	public String showScreen() {
		Object[] data = screenService.getUsers(screen.getId(), page, rows);
		MKUtil.writeJSON(data);
		return null;
	}

	public String printScreen() {
		screen = screenService.get(screen.getId());
		return "printScreen";
	}

	public String printScreen2() {
		// screen = screenService.get(screen.getId());
		// return "printScreen2";
		screen = screenService.get(screen.getId());
		scList = screenService.findSCAndSB(screen.getId());
		return "showScreen3_gongwei";

	}

	public String printScreen3() {
		// scList = screenService.findSCAndSB(screen.getId());
		// return "showScreen3_gongwei";
		screen = screenService.get(screen.getId());
		return "printScreen2";// showScreen2.jsp
	}

	private String htmlspecialchars(String str) {
		if (str == null) {
			return "";
		}
		return str.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
				">", "&gt;").replaceAll("\"", "&quot;");
	}

	public String getProductionSchedule() {
		List<Map> list = screenService.getProductionSchedule(screen.getId());
		MKUtil.writeJSON(list);
		return null;
	}

	public String findAllPrinter() {
		Map<Integer, Object> map = screenService.findAll(printer, Integer
				.parseInt(cpage), pageSize);
		printerList = (List<Printer>) map.get(1);
		if (printerList != null & printerList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("screen_findAllPrinter.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "printer_List";
	}

	public String addPrinter() {
		if (printer != null) {
			// 添加方法
			errorMessage = screenService.addPrinter(printer);
		} else {
			errorMessage = "没有详细信息，请重新添加!";
		}
		return "error";
	}

	public String updatePrinter() {
		if (printer != null) {
			// 修改方法
			errorMessage = screenService.updatePirnter(printer);
		} else {
			errorMessage = "没有详细信息，请重新添加!";
		}
		return "error";
	}

	public String delPrinter() {
		if (printer != null) {
			// 删除方法
			errorMessage = screenService.delPrinter(printer);
		} else {
			errorMessage = "没有详细信息";
		}
		return "error";
	}

	public String toadd() {
		if (printer != null) {
			printer=screenService.getPrinterbyId(printer);
			 return "printer_add";
		} else {
			errorMessage = "没有详细信息";
			return "error";
		}
	}

	public ScreenService getScreenService() {
		return screenService;
	}

	public void setScreenService(ScreenService screenService) {
		this.screenService = screenService;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public List<Screen> getScreens() {
		return screens;
	}

	public void setScreens(List<Screen> screens) {
		this.screens = screens;
	}

	public List<TaSopGongwei> getGongweis() {
		return gongweis;
	}

	public void setGongweis(List<TaSopGongwei> gongweis) {
		this.gongweis = gongweis;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List getScList() {
		return scList;
	}

	public void setScList(List scList) {
		this.scList = scList;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<ScreenContent> getScreencontents() {
		return screencontents;
	}

	public void setScreencontents(List<ScreenContent> screencontents) {
		this.screencontents = screencontents;
	}

	public List<ScreenContent> getScreencontentList() {
		return screencontentList;
	}

	public void setScreencontentList(List<ScreenContent> screencontentList) {
		this.screencontentList = screencontentList;
	}

	public ScreenContentServer getScreenContentServer() {
		return screenContentServer;
	}

	public void setScreenContentServer(ScreenContentServer screenContentServer) {
		this.screenContentServer = screenContentServer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Printer getPrinter() {
		return printer;
	}

	public void setPrinter(Printer printer) {
		this.printer = printer;
	}

	public List<Printer> getPrinterList() {
		return printerList;
	}

	public void setPrinterList(List<Printer> printerList) {
		this.printerList = printerList;
	}

	public String getCpage() {
		return cpage;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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

}
