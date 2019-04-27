package com.task.action.hk;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.HuikuanServer;
import com.task.entity.Sell;
import com.task.entity.TaHkBackMoney;
import com.task.entity.TaHkHkInvoice;
import com.task.entity.TaHkHuikuan;
import com.task.entity.TaHkPartBackMoney;
import com.task.entity.TaHkSellSta;
import com.task.entity.TaHkShoppingCard;
import com.task.entity.Users;
import com.task.entity.fin.budget.SubBudgetRate;
import com.task.util.MKUtil;
import com.task.util.Util;

public class HuiKuanAction extends ActionSupport {
	private HuikuanServer hkserver;
	private TaHkHuikuan taHk;
	private TaHkSellSta tahkSellSta;
	private TaHkBackMoney backMoney;
	private Sell sell;
	private String startDate;
	private String endDate;
	private List listObj;
	private List<Sell> list = new ArrayList<Sell>();
	private Integer[] sellId;
	private List<Sell> listSelect = new ArrayList<Sell>();
	private String hkNum;// 页面显示汇款编号
	private String message;// 显示页面弹出的对话框
	// private Set<TaHkSellSta> hkset=new HashSet(0);
	private List<TaHkSellSta> hkset; // 添加数组
	private List<TaHkHkInvoice> invoiceArr;// 发票数组
	private List<TaHkSellSta> listHkSellSta;
	private List<TaHkHuikuan> listhk;
	private List<TaHkHuikuan> listAlerm;
	private List<TaHkHuikuan> listcomp;
	private List<TaHkShoppingCard> listShop;
	private List<TaHkBackMoney> listbackMon;
	private List<TaHkPartBackMoney> listPartBackMon;
	private File[] attachment;
	private String[] attachmentContentType;
	private String[] attachmentFileName;
	private String[] attachmentStatus;// 文件上传状态数组
	private String tag;// 手动添加记录的标记
	private String idea;
	private Integer id;// 主键
	private String outOrderNumber;//外部订单号
	private String markId;//件号
	private String commStr;
	private String isTax;
	private Integer[] detailSelect;// 选择补打数组,审批数组
	private String errorMessage;
	private String successMessage;

	// private String fatherPartNumber = "";

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Integer[] getDetailSelect() {
		return detailSelect;
	}

	public void setDetailSelect(Integer[] detailSelect) {
		this.detailSelect = detailSelect;
	}

	public String getIsTax() {
		return isTax;
	}

	public void setIsTax(String isTax) {
		this.isTax = isTax;
	}

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 20;

	public String[] getAttachmentStatus() {
		return attachmentStatus;
	}

	public void setAttachmentStatus(String[] attachmentStatus) {
		this.attachmentStatus = attachmentStatus;
	}

	public List<TaHkShoppingCard> getListShop() {
		return listShop;
	}

	public void setListShop(List<TaHkShoppingCard> listShop) {
		this.listShop = listShop;
	}

	public TaHkBackMoney getBackMoney() {
		return backMoney;
	}

	public void setBackMoney(TaHkBackMoney backMoney) {
		this.backMoney = backMoney;
	}

	public List getListObj() {
		return listObj;
	}

	public void setListObj(List listObj) {
		this.listObj = listObj;
	}

	public List<TaHkHuikuan> getListhk() {
		return listhk;
	}

	public void setListhk(List<TaHkHuikuan> listhk) {
		this.listhk = listhk;
	}

	public TaHkSellSta getTahkSellSta() {
		return tahkSellSta;
	}

	public void setTahkSellSta(TaHkSellSta tahkSellSta) {
		this.tahkSellSta = tahkSellSta;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<TaHkSellSta> getListHkSellSta() {
		return listHkSellSta;
	}

	public void setListHkSellSta(List<TaHkSellSta> listHkSellSta) {
		this.listHkSellSta = listHkSellSta;
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

	// 查询出库记录
	public String querySell() throws Exception {
		this.pageSize = 30;
		this.setUrl("huikuanAction!querySell.action");
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != sell) {
			request.getSession().setAttribute("sell", sell);
		} else {
			sell = (Sell) request.getSession().getAttribute("sell");
		}
		Object[] obj = hkserver.queryNoteSell(sell, startDate, endDate, Integer
				.parseInt(cpage), pageSize);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];

		/*
		 * int count=hkserver.findCou(sell, startDate, endDate); int pageCount =
		 * (count + pageSize - 1) / pageSize; this.setTotal(pageCount + "");
		 * list=hkserver.queryNoteSell(sell, startDate, endDate, Integer
		 * .parseInt(cpage), pageSize);
		 */
		return "querySell";
	}

	// 处理和显示所选出库记录的开票明细记录

	@SuppressWarnings("unchecked")
	public String getSelectSellResult() throws Exception {
		this.listSelect = hkserver.selectSellResult(sellId);
		// ActionContext.getContext().getSession().put("listSelect",listSelect);
		ActionContext.getContext().getSession().put("sellId", sellId);
		/*
		 * this.hkNum=hkserver.returnNoteId(); taHk=new TaHkHuikuan();
		 * taHk.setHkNum(this.hkNum);
		 */
		return "getSelectSell";
	}

	// 添加送货单号
	public String addSendNum() throws Exception {
		// 保存送货单号
		if (hkserver.saveSendNum(commStr, hkset)) {
			this.message = "送货单号添加成功！！！";
			return "savaSendNumOK";
			// 添加成功
		}
		return ERROR;
	}

	// 查询送货单号记录
	public String querySta() throws Exception {
		this.pageSize = 30;
		this.setUrl("huikuanAction!querySta.action");
		int count = hkserver.findStaCou(tahkSellSta);
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		listHkSellSta = hkserver.querySta(tahkSellSta, null, null, Integer
				.parseInt(cpage), pageSize);
		return "querySta";
	}

	// 获得单个明细信息
	public String getoneSta() {
		tahkSellSta = hkserver.gethkSta(id);
		return "getoneSta";
	}

	/**
	 * 修改单个明细信息
	 * 
	 * @return
	 */
	public String updateSta() {
		if (hkserver.updateHKSta(tahkSellSta)) {
			this.tag = "LDnoteStaDetail";
			id = this.id;
			return "updateStaOK";
		}
		return null;
	}

	// 删除送货单号记录
	public String deleteSta() throws Exception {
		if (hkserver.deleteSellSta(id)) {
			return "deleteStaOK";

		}
		return ERROR;
	}

	// 选择送货单号
	public String selectSta() throws Exception {
		this.listHkSellSta = hkserver.selectStaResult(sellId);
		this.tag = tag;
		this.hkNum = hkserver.findNoteId();
		taHk = new TaHkHuikuan();
		taHk.setHkNum(this.hkNum);
		return "selectSta";
	}

	// 保存送货申请,判断是冲任务栏添加还是从送货单直接添加
	public String saveHK() throws Exception {
		/*
		 * System.out.println("=====================================444");
		 * System.out.println("=========="+hkset.size());
		 */
		// boolean b = hkserver.findPriceByPartNumber(hkset);
		this.message = hkserver.findPriceByPartNumber(hkset);
		if (message.equals("")) {
			boolean foo = hkserver.saveHk(taHk, hkset, attachment,
					attachmentFileName, tag);
			if (true == foo) {
				listHkSellSta = hkserver.addStaResult(taHk);
				return "saveSuccess";
			} else {
				return ERROR;
			}
		} else {
			// errorMessage="请检查你的零件号生产类型为销售的价格不存在，请前往存档!";
			errorMessage = message;
			return ERROR;
			// throw new RuntimeException("请检查你的零件号是否存在!");
		}

	}

	// 汇款跟踪审批
	public String hkExam() throws Exception {
		this.pageSize = 15;
		this.setUrl("huikuanAction!hkExam.action?tag=" + this.tag);
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != taHk) {
			request.getSession().setAttribute("taHk", taHk);
			request.getSession().setAttribute("tahkSellSta", tahkSellSta);
		} else {
			taHk = (TaHkHuikuan) request.getSession().getAttribute("taHk");
			tahkSellSta = (TaHkSellSta) request.getSession().getAttribute("tahkSellSta");
		}
		int count = hkserver.findNoteExamCou(taHk, tahkSellSta, startDate,
				endDate, tag);

		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		this.listhk = hkserver.findNoteExam(taHk, tahkSellSta, startDate,
				endDate, Integer.parseInt(cpage), pageSize, tag);
		if ("manager".equals(tag)) {
			listAlerm = hkserver.updateselectAlert();
			// this.listcomp=hkserver.selectComp();
		}
		return "NoteExam";
	}

	// 开票审批查询(所有)
	public String findExamList() {
		// huikuanAction!hkExam.action?tag=noteLD
		// huikuanAction!findExamList.action
		// huikuanAction!findExamList.action?tag=noteLD
		Object[] obj = hkserver.findExamList(Integer.parseInt(cpage), pageSize);
		if (obj != null && obj.length > 1) {
			list = (List) obj[1];
			int count = (Integer) obj[0];
			this.setUrl("huikuanAction!findExamList.action");
			int pageCount = (count + pageSize - 1) / pageSize;
			// this.setUrl("huikuanAction!findExamList.action?tag="+this.tag);
			this.setTotal(pageCount + "");
		}
		return "findExamList";
	}

	// 批量审批
	public String updateExamDetail() {
		try {
			if (hkserver.updateExamOADetail(detailSelect, tag)) {
				return "findExamList1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		errorMessage = "审批失败!请联系数据有效性!";
		return ERROR;
	}

	// 审批处理
	public String ExamIdea() throws Exception {
		return hkserver.updateIders(id, tag, idea);
	}

	// 查看单条回款记录的明细
	// 添加发票信息
	public String saveInvoice() throws Exception {
		int id = taHk.getId();
		TaHkHuikuan taHk2 = (TaHkHuikuan) hkserver.getObj(id);
		if(taHk.getHkPayCycle()!=null){
			taHk2.setHkPayCycle(taHk.getHkPayCycle());
		}else{
			errorMessage = "回款周期不能为空";
			url = "huikuanAction!showOneHK.action?id=" + id
			+ "&tag=invoiceStaDetail";
			return ERROR;
		}
		if(taHk.getHkBillTime()!=null){
			taHk2.setHkBillTime(taHk.getHkBillTime());
		}else{
			errorMessage = "回款倒计时开始时间不能为空";
			url = "huikuanAction!showOneHK.action?id=" + id
			+ "&tag=invoiceStaDetail";
			return ERROR;
			
		}
		taHk2.setZhekou(taHk.getZhekou());
		taHk = taHk2;
		this.message = hkserver.saveInvoice(taHk, hkset, invoiceArr,
				attachment, attachmentFileName, attachmentStatus);
		if (message.equals("")) {
			return "saveInvoiceOK";
		} else {
			errorMessage = message;
			url = "huikuanAction!showOneHK.action?id=" + id
					+ "&tag=invoiceStaDetail";
			return ERROR;
		}
		// if(hkserver.saveInvoice(taHk, hkset, invoiceArr, attachment,
		// attachmentFileName,attachmentStatus)){
		// return "saveInvoiceOK";
		// }
		// return ERROR;
	}

	// 重新开票信息添加
	public String updateInvoice() throws Exception {
		int id = taHk.getId();
		TaHkHuikuan taHk2 = (TaHkHuikuan) hkserver.getObj(id);
		taHk2.setHkPayCycle(taHk.getHkPayCycle());
		taHk2.setHkBillTime(taHk.getHkBillTime());
		taHk = taHk2;
		if (true == hkserver.updateInvoice(taHk, hkset, invoiceArr, attachment,
				attachmentFileName, attachmentStatus)) {
			return "updateInvoiceOK";
		}
		return ERROR;
	}

	// 查看单条回款的明细和添加追款人
	@SuppressWarnings("unchecked")
	public String showOneHK() throws Exception {
		taHk = (TaHkHuikuan) hkserver.getObj(id);
		this.listObj = hkserver.findMoreFile(taHk);
		if ("queryInvo".equals(tag)) {// 查看发票信息
			this.invoiceArr = hkserver.findhkSta(taHk, "TaHkHkInvoice");
		} else if ("querybackMoney".equals(tag)) {
			
			this.listbackMon = hkserver.findhkSta(taHk, "TaHkBackMoney");
		} else if ("noteStaDetail".equals(tag)) {
			listHkSellSta = hkserver.findhkSta(taHk, "TaHkSellSta");
		} else if ("LDnoteStaDetail".equals(tag)) {
			listHkSellSta = hkserver.findhkSta(taHk, "TaHkSellSta");
		} else if ("addBackMoney".equals(tag)) {
			listbackMon = hkserver.findhkSta(taHk, "TaHkBackMoney");
			listHkSellSta = hkserver.findhkSta(taHk, "TaHkSellSta");
			return "addBackMoney";
		} else if ("invoiceStaDetail".equals(tag)) {
			// listbackMon=hkserver.gethkSta(taHk,"TaHkBackMoney");
			listHkSellSta = hkserver.findhkSta(taHk, "TaHkSellSta");
			taHk = hkserver.findtahk(taHk);
			return "invoiceStaDetail";
		} else if ("RepeatInvoice".equals(tag)) {// 发票重开页面
			// listbackMon=hkserver.gethkSta(taHk,"TaHkBackMoney");
			listHkSellSta = hkserver.findhkSta(taHk, "TaHkSellSta");
			// this.invoiceArr=
			return "invoiceRepeat";
		} else if ("hkOVER".equals(tag)) {// 关闭回款记录，返回管理界面
			// listbackMon=hkserver.gethkSta(taHk,"TaHkBackMoney");
			if (hkserver.updateCompulsory(taHk, tag)) {
				return "hkOVER";
			}
			// this.invoiceArr=

		} else if ("hkRepeatInvo".equals(tag)) {// 打回重新开票，返回管理界面
			// listbackMon=hkserver.gethkSta(taHk,"TaHkBackMoney");
			if (hkserver.updateCompulsory(taHk, tag)) {
				// this.invoiceArr=
				return "hkRepeatInvo";
			}
		}
		return "showOneHK";
	}

	// 更新回款信息和添加汇款人
	public String updateHK() throws Exception {
		int id = taHk.getId();
		TaHkHuikuan taHk2 = (TaHkHuikuan) hkserver.getObj(id);
		taHk2.setHkZhuikuanren(taHk.getHkZhuikuanren());
		if ("query".equals(tag)) {
			this.tag = "manager";
		} else if ("addZKPerson".equals(tag)) {
			this.tag = "invoMD";
		}
		System.out.println("==========zhuikuanren:" + taHk.getHkZhuikuanren());
		if (hkserver.updateHk(taHk2)) {
			return "updateOK";
		}
		return ERROR;
	}

	// 删除回款记录
	public String deleteHK() throws Exception {
		if (hkserver.deleteHK(id)) {
			this.tag = tag;
			return "deleteOK";
		}
		return ERROR;
	}

	public String saveBackMoney() throws Exception {
		int id = taHk.getId();
		hkserver.calculateLastTime();
		TaHkHuikuan taHk2 = (TaHkHuikuan) hkserver.getObj(id);
		if (hkserver.saveBackMon(taHk2, backMoney, attachment,
				attachmentFileName)) {
			this.listbackMon = hkserver.findhkSta(taHk2, "TaHkBackMoney");
			this.tag = "querybackMoney";
			return "saveBackMoneyOK";
		}
		return ERROR;
	}

	public String selectPrice() throws Exception {
		try {
			String message = hkserver.selectPrice(idea, isTax);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(message);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	// 添加任务栏操作
	public String saveShopping() throws Exception {
		Integer id = this.id;

		String message = hkserver.saveShoppingCard(id);
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
	// 核对开票数量列表
	public String checkBillCountShow()  {
		this.pageSize = 15;
		this.setUrl("huikuanAction!checkBillCountShow.action");
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != taHk) {
			request.getSession().setAttribute("taHk", taHk);
		} else {
			taHk = (TaHkHuikuan) request.getSession().getAttribute("taHk");
		}
		int count = hkserver.findUncheckcount(taHk, tahkSellSta, startDate,
				endDate);

		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		this.listhk = hkserver.findUncheckList(taHk, tahkSellSta, startDate,
				endDate, Integer.parseInt(cpage), pageSize);
		return "showCheckBillCount";
	}
	/**
	 * 跳往核对开票数量
	 * @return
	 */
	public String toCheckBillCount(){
		taHk = hkserver.findtahk(taHk);
		if(!taHk.getHkStatus().equals("未核对")){
			successMessage="对不起该发票的状态不是未核对!";
			taHk=null;
			return checkBillCountShow();
		}
		listHkSellSta = hkserver.findhkSta(taHk, "TaHkSellSta");
//		return "invoiceStaDetail";
		return "checkBillCount";
	}
	/**
	 * 核对开票数量
	 * @return
	 */
	public String checkBillCount(){
		//计算回款期限
		hkserver.calculateLastTime();
		String msg=hkserver.checkBillCount(taHk,hkset, attachment,attachmentFileName);
		if(msg.equals("true")){
			taHk=null;
			successMessage="核对成功";
			return checkBillCountShow();
		}else{
			successMessage=msg;
			return toCheckBillCount();
		}
	}
	/**
	 * 通过发票的id来跳往想对应的页面
	 * @return
	 */
	public String showFaPiao(){
		if(id!=null&&tag!=null){
			listhk=hkserver.findFaPiao(id,tag);
			return "showFaPiao";
		}else{
			errorMessage="未找到目标";
			return ERROR;
		}
	}
	// 任务栏处理
	public String manageShopping() throws Exception {
		this.listShop = hkserver.queryShopping(tahkSellSta, startDate, endDate);
		return "managerShopping";
	}

	// 删除任务栏中的送货单号
	public String deleteShopping() throws Exception {
		return hkserver.deleteShopping(id);
	}
	/**
	 * 根据订单号和件号查询发票
	 */
	public void showKaiPiaoDan(){
		listhk=hkserver.showKaiPiaoDan(outOrderNumber,markId);
		if(listhk!=null&&listhk.size()>0){
			MKUtil.writeJSON(listhk);
		}else{
			MKUtil.writeJSON(null);
		}
	}
	/**
	 * 根据发票id和订单编号获取对应的发票明细
	 */
	public void showKaiPiaoDetail(){
		listHkSellSta=hkserver.showKaiPiaoDetail(id,outOrderNumber);
		if(listHkSellSta!=null&&listHkSellSta.size()>0){
			MKUtil.writeJSON(listHkSellSta);
		}else{
			MKUtil.writeJSON(null);
		}
	}
	/**
	 * 根据发票id和订单号来查询回款单
	 */
    public void showBackMoney(){
    	listbackMon=hkserver.showBackMoney(id,outOrderNumber);
    	if(listbackMon!=null&&listbackMon.size()>0){
    		MKUtil.writeJSON(listbackMon);
		}else{
			MKUtil.writeJSON(null);
		}
    }
    /**
     * 根据回款单号和订单号或者发票号和订单号和件号查询回款明细
     */
    public void showBackMoneyDatail(){
    	listPartBackMon=hkserver.showBackMoneyDatail(id,outOrderNumber,markId);
    	if(listPartBackMon!=null&&listPartBackMon.size()>0){
    		MKUtil.writeJSON(listPartBackMon);
		}else{
			MKUtil.writeJSON(null);
		}
    }
	// 导出
	public String exportExcel() {

		hkserver.exportExcel(startDate, endDate);
		return null;
	}
	//根据当前登录人查出他所绑定的银行账户;
	public void findBankSubByUsers(){
		try {
			Users user =	Util.getLoginUser();
			List<SubBudgetRate> bankSubList = hkserver.findBankSub(user);
			MKUtil.writeJSON(bankSubList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//查出所有银行账户;
	public void findBankSub(){
		try {
			List<SubBudgetRate> bankSubList = hkserver.findBankSub(null);
			MKUtil.writeJSON(bankSubList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HuikuanServer getHkserver() {
		return hkserver;
	}

	public void setHkserver(HuikuanServer hkserver) {
		this.hkserver = hkserver;
	}

	public TaHkHuikuan getTaHk() {
		return taHk;
	}

	public void setTaHk(TaHkHuikuan taHk) {
		this.taHk = taHk;
	}

	public Sell getSell() {
		return sell;
	}

	public void setSell(Sell sell) {
		this.sell = sell;
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

	public List<Sell> getList() {
		return list;
	}

	public void setList(List<Sell> list) {
		this.list = list;
	}

	public Integer[] getSellId() {
		return sellId;
	}

	public void setSellId(Integer[] sellId) {
		this.sellId = sellId;
	}

	public List<Sell> getListSelect() {
		return listSelect;
	}

	public void setListSelect(List<Sell> listSelect) {
		this.listSelect = listSelect;
	}

	public String getHkNum() {
		return hkNum;
	}

	public void setHkNum(String hkNum) {
		this.hkNum = hkNum;
	}

	/*
	 * public Set<TaHkSellSta> getHkset() { return hkset; } public void
	 * setHkset(Set<TaHkSellSta> hkset) { this.hkset = hkset; }
	 */
	public List<TaHkSellSta> getHkset() {
		return hkset;
	}

	public void setHkset(List<TaHkSellSta> hkset) {
		this.hkset = hkset;
	}

	public String[] getAttachmentContentType() {
		return attachmentContentType;
	}

	public void setAttachmentContentType(String[] attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
	}

	public String[] getAttachmentFileName() {
		return attachmentFileName;
	}

	public void setAttachmentFileName(String[] attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}

	public File[] getAttachment() {
		return attachment;
	}

	public void setAttachment(File[] attachment) {
		this.attachment = attachment;
	}

	/*
	 * public String getFatherPartNumber() { return fatherPartNumber; }
	 * 
	 * public void setFatherPartNumber(String fatherPartNumber) {
	 * this.fatherPartNumber = fatherPartNumber; }
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdea() {
		return idea;
	}

	public void setIdea(String idea) {
		this.idea = idea;
	}

	public List<TaHkHkInvoice> getInvoiceArr() {
		return invoiceArr;
	}

	public void setInvoiceArr(List<TaHkHkInvoice> invoiceArr) {
		this.invoiceArr = invoiceArr;
	}

	public List<TaHkBackMoney> getListbackMon() {
		return listbackMon;
	}

	public void setListbackMon(List<TaHkBackMoney> listbackMon) {
		this.listbackMon = listbackMon;
	}

	public List<TaHkHuikuan> getListAlerm() {
		return listAlerm;
	}

	public void setListAlerm(List<TaHkHuikuan> listAlerm) {
		this.listAlerm = listAlerm;
	}

	public List<TaHkHuikuan> getListcomp() {
		return listcomp;
	}

	public void setListcomp(List<TaHkHuikuan> listcomp) {
		this.listcomp = listcomp;
	}

	public String getCommStr() {
		return commStr;
	}

	public void setCommStr(String commStr) {
		this.commStr = commStr;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<TaHkPartBackMoney> getListPartBackMon() {
		return listPartBackMon;
	}

	public void setListPartBackMon(List<TaHkPartBackMoney> listPartBackMon) {
		this.listPartBackMon = listPartBackMon;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getOutOrderNumber() {
		return outOrderNumber;
	}

	public void setOutOrderNumber(String outOrderNumber) {
		this.outOrderNumber = outOrderNumber;
	}

	public String getMarkId() {
		return markId;
	}

	public void setMarkId(String markId) {
		this.markId = markId;
	}

}
