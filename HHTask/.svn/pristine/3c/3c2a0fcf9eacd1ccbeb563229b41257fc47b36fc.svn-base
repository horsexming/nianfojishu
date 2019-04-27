package com.task.action.sop;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.sop.RunningWaterCardServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.entity.GoodsStore;
import com.task.entity.WarehouseNumber;
import com.task.entity.sop.Procard;
import com.task.entity.sop.RunningWaterCard;
import com.task.entity.sop.qd.LogoStickers;
import com.task.util.MKUtil;

/**
 * 生产周转卡功能Action层
 * 
 * @author 贾辉辉
 * 
 */
public class RunningWaterCardAction extends ActionSupport {
	private RunningWaterCard runningWaterCard;
	private RunningWaterCardServer runningWaterCardServer;
	private LogoStickers sticker;// 补料单
	private Procard procard;
	private GoodsStore goodsStore;
	private List list;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private Integer id;// 主键
	private String startDate;// 开始时间
	private String endDate;// 截止时间
	private String tag;// 标识
	private String message;
	private String errorMessage;
	private String viewStatus;
	private String barCode;
	private WarehouseNumber wn;

	/**
	 * 添加流水卡片信息
	 * 
	 * @return
	 */
	public String save() {
		if (runningWaterCardServer.save(runningWaterCard)) {
			return "saveOK";
		} else {
			message = "卡号已存在，或卡有问题，请核实!!!!!!";
			return INPUT;
		}
	}

	/**
	 * 查询流水卡片信息
	 * 
	 * @return
	 */
	public String findCard() {
		this.pageSize = 15;
		this.setUrl("RunningWaterCardAction!findCard.action");
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != runningWaterCard) {
			request.getSession().setAttribute("runningWaterCard",
					runningWaterCard);
		} else {
			runningWaterCard = (RunningWaterCard) request.getSession()
					.getAttribute("runningWaterCard");
		}
		Object[] obj = runningWaterCardServer.findRunningWaterCard(
				runningWaterCard, startDate, endDate, Integer.parseInt(cpage),
				pageSize, "");
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "findCardOK";

	}

	/**
	 * 使用模板添加
	 * 
	 * @return
	 */
	public String getCardById() {
		runningWaterCard = runningWaterCardServer.getCard(id);
		if ("bd".equals(tag)) {
			return "saveOK";
		}
		return "getCardOK";
	}

	/**
	 * 选择下拉选项
	 * 
	 * @return
	 */
	public String selectItem() {
		String message = runningWaterCardServer.findCardList(tag);
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

	public String findCardByCard() {
		String message = runningWaterCardServer.findCardByCard(tag);
		MKUtil.writeJSON(message);
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	// 申请入库
	public String findRukuShenqinglist() {
		this.pageSize = 15;
		this.setUrl("RunningWaterCardAction!findRukuShenqinglist.action");
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != runningWaterCard) {
			request.getSession().setAttribute("runningWaterCard",
					runningWaterCard);
		} else {
			runningWaterCard = (RunningWaterCard) request.getSession()
					.getAttribute("runningWaterCard");
			if (null == runningWaterCard) {
				runningWaterCard = new RunningWaterCard();
			}
		}
		runningWaterCard.setCardStatus("完成");

		Object[] obj = runningWaterCardServer.findRunningWaterCard(
				runningWaterCard, startDate, endDate, Integer.parseInt(cpage),
				pageSize, "inApply");
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "findRukuOK";
	}

	// 单个卡片入库信息完善,申请入库
	public String findOneCardInfor() {
		if ("byId".equals(tag)) {
			runningWaterCard = runningWaterCardServer.getCard(id);
			procard = runningWaterCardServer.getProcardById(runningWaterCard
					.getProcardId());
		} else if ("noCard".equals(tag)) {
			procard = runningWaterCardServer.getProcardById(id);
		} else {// 扫描
			String number = runningWaterCard.getCardNum();
			runningWaterCard = runningWaterCardServer.getCardBycardNum(number,
					"in");
			if (null == runningWaterCard) {
				sticker = runningWaterCardServer.getLogoSticker(number, "in");
				if (null != sticker) {
					procard = runningWaterCardServer.getProcardById(sticker
							.getProcardId());
					this.tag = "barcode";
				} else {
					this.message = "没有绑定相关工艺流水单信息，请联系信息管理员";
					return "findCardNO";
				}

			} else {
				if (number.equals(runningWaterCard.getCardNum())) {
					procard = runningWaterCardServer
							.getProcardById(runningWaterCard.getProcardId());
				} else {
					this.message = "请先入库该件号的最小批次,更换卡号为"
							+ runningWaterCard.getCardNum() + "的生产周转卡！！";
					return "findCardNO";
				}

			}
		}
		if (null != procard) {// 有对应的工艺流水单对象
			// 判断是否存在巡检记录
			// return "scanRuku";
			String mes = runningWaterCardServer.findExamTitle(procard);
			if ("true".equals(mes)) {
				if (barCode != null) {
					wn = runningWaterCardServer.findWNBybarCode(barCode);
					if (wn == null || wn.getIp() == null)
						return ERROR;
					errorMessage = runningWaterCardServer.OpenWNByWNNumber(wn);
					if ("true".equals(errorMessage)) {
						return "scanRuku";
					} else
						return ERROR;
				} else {
					return "scanRuku";// goodsStore_scanAdd.jsp
				}
			} else {
				errorMessage = mes;
				return ERROR;
			}
		} else {
			this.message = "没有绑定相关工艺流水单信息，请联系信息管理员";
			return "findCardNO";// runningWaterCard_Scanlist.jsp
		}
	}

	// 保存入库信息
	public String saveGoodsStore() {
		// 保存入库信息
		// 修改周转卡信息byCardNum

		/*
		 * RunningWaterCard runningCard = runningWaterCardServer
		 * .getCard(runningWaterCard.getId());
		 */
		Procard pro = runningWaterCardServer.getProcardById(procard.getId());
		/*
		 * if("barcode".equals(tag)){//根据生产周转卡入库 //id=sticker .getId();
		 * }else{//根据补料单入库 id=runningWaterCard.getId(); this.runningWaterCard =
		 * runningWaterCardServer.get; }
		 */
		// String msg = runningWaterCardServer.checkZaizhi(goodsStore
		// .getGoodsStoreCount(), pro);
		// if (!msg.equals("true")) {
		// this.procard = pro;
		// message = msg;
		// return "SAVEerror";
		// }
		if (pro.getRukuCount() == null) {
			pro.setRukuCount(0F);
		}
		Float rukuCount = pro.getRukuCount() + goodsStore.getGoodsStoreCount();
		if (rukuCount > pro.getTjNumber()) {
			message = "申请超额!";
			return "SAVEerror";
		}
		message = runningWaterCardServer.saveGoodsStore(goodsStore, id, pro,
				tag, barCode);

		AlertMessagesServerImpl.addAlertMessages("入库确认(总成)", "成品"
				+ goodsStore.getGoodsStoreMarkId() + "("
				+ goodsStore.getNeiorderId() + ")"
				+ goodsStore.getGoodsStoreCount() + "件申请入库，请您及时确认!谢谢", "1");
		if ("true".equals(message)) {
			if (tag != null && "noCard".equals(tag)) {
				return "findNeedRukuPro";
			}
			return "saveGoodsStoreOK";
		} else {
			this.procard = pro;
			return "SAVEerror";
		}

		/*
		 * } else { this.runningWaterCard = runningCard; this.procard = pro;
		 * message = "该流转卡绑定的生产卡片信息有误！"; return "SAVEerror"; }
		 */

	}

	public RunningWaterCard getRunningWaterCard() {
		return runningWaterCard;
	}

	public void setRunningWaterCard(RunningWaterCard runningWaterCard) {
		this.runningWaterCard = runningWaterCard;
	}

	public RunningWaterCardServer getRunningWaterCardServer() {
		return runningWaterCardServer;
	}

	public void setRunningWaterCardServer(
			RunningWaterCardServer runningWaterCardServer) {
		this.runningWaterCardServer = runningWaterCardServer;
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

	public Procard getProcard() {
		return procard;
	}

	public void setProcard(Procard procard) {
		this.procard = procard;
	}

	public GoodsStore getGoodsStore() {
		return goodsStore;
	}

	public void setGoodsStore(GoodsStore goodsStore) {
		this.goodsStore = goodsStore;
	}

	public LogoStickers getSticker() {
		return sticker;
	}

	public void setSticker(LogoStickers sticker) {
		this.sticker = sticker;
	}

	public String getViewStatus() {
		return viewStatus;
	}

	public void setViewStatus(String viewStatus) {
		this.viewStatus = viewStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public WarehouseNumber getWn() {
		return wn;
	}

	public void setWn(WarehouseNumber wn) {
		this.wn = wn;
	}

}
