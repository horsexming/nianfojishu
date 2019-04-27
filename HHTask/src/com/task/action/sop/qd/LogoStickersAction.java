package com.task.action.sop.qd;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.sop.qd.LogoStickerServer;
import com.task.entity.Users;
import com.task.entity.android.OsRecord;
import com.task.entity.android.OsRecordScope;
import com.task.entity.android.OsTemplate;
import com.task.entity.gzbj.ProcessAndMeasuring;
import com.task.entity.sop.ProcessInfor;
import com.task.entity.sop.qd.LogoStickers;
import com.task.util.MKUtil;
import com.task.util.Util;

public class LogoStickersAction extends ActionSupport {
	private LogoStickerServer logoStickerServer;
	private LogoStickers sticker;
	private ProcessInfor processInfor;
	private OsTemplate osTemplate;
	private List listPrint;
	private List list;
	private String tag;
	private String message;
	private String content;
	private List<ProcessAndMeasuring >	pamList ;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private Integer id;// 主键
	private Integer id1;
	private Integer [] ids;
	private String startDate;// 开始时间
	private String endDate;// 截止时间
	private String errorMessage;

	private String isHege;// 是否合格（yes no）
	private List<OsRecordScope> osRecordScopeList;

	// 添加标识贴
	public String saveLogoSticker() {
		if (logoStickerServer.saveStick(sticker)) {
			return "saveOK";
		}
		return ERROR;
	}

	// 查询标识贴
	public String findLogoSticker2() {
		this.pageSize = 15;
		this.setUrl("LogoStickerAction!findLogoSticker2.action?tag=" + this.tag);
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != sticker) {
			request.getSession().setAttribute("sticker", sticker);
		} else {
			sticker = (LogoStickers) request.getSession().getAttribute(
					"sticker");
		}
		Object[] obj = logoStickerServer.findSticck(sticker, startDate,
				endDate, Integer.parseInt(cpage), pageSize, this.tag);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "findSticker";

	}

	// 领料标识贴查询
	public String findLingliaoSticker() {

		listPrint = logoStickerServer.findListPrint(tag);
		this.pageSize = 15;
		this.setUrl("LogoStickerAction!findLingliaoSticker.action");
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != sticker) {
			request.getSession().setAttribute("sticker", sticker);
		} else {
			sticker = (LogoStickers) request.getSession().getAttribute(
					"sticker");
		}

		Object[] obj = logoStickerServer.findNOOKList(sticker, startDate,
				endDate, Integer.parseInt(cpage), pageSize, this.tag);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "findLingLliaoOK";
	}

	// 根据ID查询标识贴
	public String findLogoStickerById() {
		sticker = logoStickerServer.getStickById(id);
		if ("model".equals(tag)) {
			return "saveByModel";
		} else if ("print".equals(tag)) {
			return "updateOK";
		}
		return "findOK";
	}

	// 更新标识贴
	public String updateLogoSticker() {
		if (logoStickerServer.updateStick(sticker)) {
			sticker = logoStickerServer.getStickById(sticker.getId());
			return "updateOK";
		}
		return null;
	}

	// 删除标识贴
	public String deleteLogoSticker() {
		this.cpage = cpage;
		this.tag = "manger";
		if (logoStickerServer.deleteStickById(id)) {
			return "deleteOK";
		}
		return null;
	}

	// 数据汇总导出
	public String findSum() {
		this.pageSize = 10;
		this.setUrl("LogoStickerAction!findSum.action");
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != sticker) {
			request.getSession().setAttribute("sticker", sticker);
		} else {
			sticker = (LogoStickers) request.getSession().getAttribute(
					"sticker");
		}
		Object[] obj = logoStickerServer.findSum(sticker, startDate, endDate,
				Integer.parseInt(cpage), pageSize);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "sumResult";
	}

	// 根据工号或姓名查询信息
	public String findUserInfor() {
		// 通过JSON获取对象
		String[] obj = logoStickerServer.findUserInfor(tag, content);
		message = obj[0];
		String con = obj[1];
		Map<String, String> map = new HashMap<String, String>();
		map.put("content", con);
		map.put("message", message);
		MKUtil.writeJSON(map);
		return null;
	}

	// 打印状态管理
	public String print() {
		logoStickerServer.printInfor(id);
		Map<String, String> map = new HashMap<String, String>();
		map.put("content", "");
		map.put("message", "");
		MKUtil.writeJSON(map);
		return null;
	}

	// 下拉选择
	public String selectItem() {
		String message = logoStickerServer.selectItem(tag);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(message);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	public void getsjMarkId() {
		list = logoStickerServer.getsjMarkId();
		MKUtil.writeJSON(list);
	}

	public void getsjSelfCard() {
		list = logoStickerServer.getsjSelfCard(sticker.getMarkId());
		MKUtil.writeJSON(list);
	}

	public void getsjProcessNo() {
		list = logoStickerServer.getsjProcessNo(sticker.getMarkId(), sticker
				.getLotId());
		MKUtil.writeJSON(list);
	}

	public void getStickerForCheck() {
		sticker = logoStickerServer.getStickerForCheck(sticker.getMarkId(),
				sticker.getLotId(), sticker.getProcessNO());
		MKUtil.writeJSON(sticker);
	}

	public void getcheckList() {
		list = logoStickerServer.getcheckList(id);
		MKUtil.writeJSON(list);
	}

	/***
	 * APP首检列表查询方法
	 * 
	 * @return
	 */
	public String findSjList() {
		Users user = Util.getLoginUser();
		if (user == null) {
			errorMessage = "请先登录。谢谢!";
			url = "/sjLogin.jsp";
			return ERROR;
		}
		this.pageSize = 15;
		this.setUrl("LogoStickerAction!findSjList.action");
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != sticker) {
			request.getSession().setAttribute("sticker", sticker);
		} else {
			sticker = (LogoStickers) request.getSession().getAttribute(
					"sticker");
		}
		Object[] obj = logoStickerServer.findSjList(sticker, startDate,
				endDate, Integer.parseInt(cpage), pageSize, this.tag);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "findStickertocheck";
	}

	/***
	 * APP首检明细界面
	 * 
	 * @return
	 */
	public String getcheckList2() {
		if (id != null && id > 0) {
			sticker = logoStickerServer.getStickById(id);
		} else if (content != null && content.length() > 0) {
			sticker = logoStickerServer.getStickBynumber(content);
		}
		if (sticker != null && "NO".equals(sticker.getIsPrint())) {
			Object[] obj = logoStickerServer.updateOsTemplate(sticker.getId());
			osTemplate = (OsTemplate) obj[0];
			list = (List) obj[1];
			pamList = (List<ProcessAndMeasuring>) obj[2];
			return "checkSticK";
		} else {
			errorMessage = "您好,该件号已经检验完成,无需重复检验。谢谢!";
			url = "LogoStickerAction!findSjList.action";
			return ERROR;
		}
	}

	// 更新标识贴
	public String updateLogoSticker2() {
		String msg = logoStickerServer.updateStick2(sticker, osRecordScopeList,
				tag);
		if (msg.equals("true")) {
			message = logoStickerServer.printMes(sticker.getId());
			return "app_printStickertocheck";
		} else {
			errorMessage = msg;
		}
		return "error";
	}

	// 数据导出EXCEL
	public String export() {
		logoStickerServer.export(sticker, startDate, endDate);
		return null;
	}
	//选择量、检具信息
	public void xuanzeMea(){
		try {
			errorMessage = logoStickerServer.xuanzeMea(ids, id,id1);
			MKUtil.writeJSON(errorMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public LogoStickerServer getLogoStickerServer() {
		return logoStickerServer;
	}

	public void setLogoStickerServer(LogoStickerServer logoStickerServer) {
		this.logoStickerServer = logoStickerServer;
	}

	public LogoStickers getSticker() {
		return sticker;
	}

	public void setSticker(LogoStickers sticker) {
		this.sticker = sticker;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List getListPrint() {
		return listPrint;
	}

	public void setListPrint(List listPrint) {
		this.listPrint = listPrint;
	}

	public ProcessInfor getProcessInfor() {
		return processInfor;
	}

	public void setProcessInfor(ProcessInfor processInfor) {
		this.processInfor = processInfor;
	}

	public String getIsHege() {
		return isHege;
	}

	public void setIsHege(String isHege) {
		this.isHege = isHege;
	}

	public List<OsRecordScope> getOsRecordScopeList() {
		return osRecordScopeList;
	}

	public void setOsRecordScopeList(List<OsRecordScope> osRecordScopeList) {
		this.osRecordScopeList = osRecordScopeList;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public OsTemplate getOsTemplate() {
		return osTemplate;
	}

	public void setOsTemplate(OsTemplate osTemplate) {
		this.osTemplate = osTemplate;
	}

	public List<ProcessAndMeasuring> getPamList() {
		return pamList;
	}

	public void setPamList(List<ProcessAndMeasuring> pamList) {
		this.pamList = pamList;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public Integer getId1() {
		return id1;
	}

	public void setId1(Integer id1) {
		this.id1 = id1;
	}

}
