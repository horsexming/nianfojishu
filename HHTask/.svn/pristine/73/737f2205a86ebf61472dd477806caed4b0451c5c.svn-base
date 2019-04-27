package com.task.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.ScreenContentServer;
import com.task.Server.ScreenService;
import com.task.entity.Screen;
import com.task.entity.ScreenContent;
import com.task.util.MKUtil;

@SuppressWarnings("serial")
public class ScreenContentAction extends ActionSupport {
	private ScreenContent screencontent;
	private ScreenService screenService;
	private ScreenContentServer screenContentServer;
	private String errorMessage;
	private String name;
	private String code;
	private Screen screen;
	private List<ScreenContent> screencontents;
	private String[] o;
	private Integer id;
	private String sortableSequence;
	private String companyenglishname;

	private String cpage = "1";
	private int pageSize = 15;

	// public String execute() {
	// Users users = Util.getLoginUser();
	// name = users.getName();
	// code = users.getCode();
	//
	// return "show";
	// }

	public String add() {
		try {
			String mes = screenContentServer.addScreenContent(screencontent);
			screenService.addscreencontent2allscreen(screenContentServer
					.findlastscreencontentId());
			// 绑定所有屏幕
		} catch (Exception e) {
			e.printStackTrace();
		}
//		MKUtil.writeJSON(true, "成功", null);
		return "addScreenContentSuccess";
		
	}

	public String update() {
		try {
			String[] Sequence = sortableSequence.split("\\,");
			for (int i = 0; i < Sequence.length; i++) {
				ScreenContent scrget = screencontents.get((Integer
						.parseInt(Sequence[i])) - 1);
				scrget.setNumber(i + 1);
				screencontents.set((Integer.parseInt(Sequence[i])) - 1, scrget);
			}
			screenContentServer.updateScreenContents(screencontents);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(true, "失败", null);
		}
//		MKUtil.writeJSON(true, "成功", null);
		return "updateScreenContentSuccess";

	}

	// 显示管理列表
	public String showContent() {
		try {
			Object[] o = screenContentServer.ScreenContentList();
			screencontents = (List<ScreenContent>) o[0];

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "screenContent_show";

	}

	// 轮播父页面
	public String showScreen() {
		try {
			name = screenContentServer.findScreenName(screen.getId());
			o = screenContentServer.findScreenContent(screen.getId());
			companyenglishname=screenContentServer.findCompanyEnglishName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "totalscreen";
	}
	
	// 刷新公告及轮播列表
	public String findScreenContent() {
		try {
			o = screenContentServer.findScreenContent(id);
			MKUtil.writeJSON(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String delete() {
		try {
			screencontent = screenContentServer.getscreencontent(id);
			screenContentServer.deleteScreenContent(screencontent);
			MKUtil.writeJSON(true, "删除成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "删除失败：" + e.getMessage(), null);
		}
		return null;
	}

	public ScreenContent getScreencontent() {
		return screencontent;
	}

	public void setScreencontent(ScreenContent screencontent) {
		this.screencontent = screencontent;
	}

	public ScreenContentServer getScreenContentServer() {
		return screenContentServer;
	}

	public void setScreenContentServer(ScreenContentServer screenContentServer) {
		this.screenContentServer = screenContentServer;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public List<ScreenContent> getScreencontents() {
		return screencontents;
	}

	public void setScreencontents(List<ScreenContent> screencontents) {
		this.screencontents = screencontents;
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

	public String[] getO() {
		return o;
	}

	public void setO(String[] o) {
		this.o = o;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ScreenService getScreenService() {
		return screenService;
	}

	public void setScreenService(ScreenService screenService) {
		this.screenService = screenService;
	}

	public String getSortableSequence() {
		return sortableSequence;
	}

	public void setSortableSequence(String sortableSequence) {
		this.sortableSequence = sortableSequence;
	}


	public String getCompanyenglishname() {
		return companyenglishname;
	}

	public void setCompanyenglishname(String companyenglishname) {
		this.companyenglishname = companyenglishname;
	}

}
