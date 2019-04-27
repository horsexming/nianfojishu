package com.task.action.ess;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.WareHouseAuthService;
import com.task.Server.ess.GoodsServer;
import com.task.Server.ess.WasteDisponseService;
import com.task.Server.payment.FundApplyServer;
import com.task.entity.BaoFeiGoods;
import com.task.entity.GoodHouse;
import com.task.entity.Goods;
import com.task.entity.Goods_bzsq;
import com.task.entity.Users;
import com.task.entity.WareHouse;
import com.task.entity.YuLiaoApply;
import com.task.entity.approval.Signature;
import com.task.entity.ess.WasteDisponsal;
import com.task.entity.ess.WasteDisponsalTotal;
import com.task.entity.system.ExecutionNode;
import com.task.util.MKUtil;

/**
 * 报废品处理Action
 * @author wcy
 *
 */
public class WasteDisposeAction extends ActionSupport{
	
	private Integer totalId;//报废单id
	private List<Integer> wdId;
	private List<Float> reducedNum;
	private List<Float> reducedPrice;
	private WareHouseAuthService wareHouseAuthService;
	private GoodsServer goodsServer;
	private BaoFeiGoods baofeigoods;// 报废库存表;
	private Goods_bzsq goodsBzsq;// 包装申请表;
	private Goods goods;
	private List<YuLiaoApply> yuLiaoApplyList;
	private List<Goods> list;
	private List<WareHouse> warehouseList;
	private List<GoodHouse> goodHouseList;
	private Integer id;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String startDate;// 开始时间
	private String endDate;// 截止时间
	private String tag;// 标识
	private String message;
	private String role;
	private List<String> orderNumberList;// 订单号
	private String pagestatus;// 页面状态;
	private double sumcount;
	private boolean isall;
	private String username;//
	private String errorMessage;// 错误消息
	private String successMessage;// 错误消息
	private String firsttime;
	private String endtime;
	private String pageStatus;// 页面状态
	private String proposer;  //申请人
	private WasteDisponsalTotal wasteDisponsalTotal;//报废品单
	private WasteDisponsal wasteDisponsal;//报废详细单
	private List<WasteDisponsal> wasteDisponsalList;//报废详细列表
	private List<WasteDisponsalTotal> showwdtList; //报废单列表
	private FundApplyServer fundApplyServer;;
	private Map<Integer, Object> executionNode;
	private Integer goodsId;
	private File[] attachment;
	private String[] attachmentContentType;
	private String[] attachmentFileName;
	private List<WasteDisponsalTotal> notPriceAndEp;
	private List<WasteDisponsalTotal> bePriceNotEp;
	/**
	 * 根据多个条件查询报废品
	 * 分页
	 * @return
	 */
	public String findWhereWaste(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != startDate && !"".equals(startDate)) {
			request.getSession().setAttribute("startDate", startDate);
		} else {
			startDate = (String) request.getSession().getAttribute("startDate");
		}
		
		if (null != endDate && !"".equals(endDate)) {
			request.getSession().setAttribute("endDate", endDate);
		} else {
			endDate = (String) request.getSession().getAttribute("endDate");
		}
		
		if(null!=proposer && !"".equals(proposer)){
			request.getSession().setAttribute("proposer", proposer);
		}else{
			proposer = (String) request.getSession().getAttribute("proposer");
		}
		
		int count = wasteDisponseService.showWDCount(proposer, startDate, endDate,tag);
		int pageCount = (count+pageSize-1)/pageSize;
		this.setTotal(pageCount+"");
		 showwdtList = wasteDisponseService.showwdtList(Integer.parseInt(cpage), pageSize, proposer, startDate, endDate,tag);
		if(showwdtList!=null){
			this.setUrl("wasteDisposeAction!findWhereWaste.action?tag="+tag);
			request.getSession().setAttribute("showwdtList", showwdtList);
			if("caiwu".equals(tag)){
				bePriceNotEp =wasteDisponseService.findBePriceNotEp();
				notPriceAndEp = wasteDisponseService.findNotPriceAndEP();
				return "waste_affirmList";
			}
			return "showWasteCOK";
			
		}
		return ERROR;
	}
	
	/**
	 * 根据废品单查询废品详细
	 * @return
	 */
	public String selectDetail(){
		String msg = "selectDetailOK";
		wasteDisponsalTotal = wasteDisponseService.getwdtById(wasteDisponsalTotal.getTotalId());
		executionNode = fundApplyServer.findPay_ExecutionNode(wasteDisponsalTotal.getEpId());
		
		wasteDisponsalList = wasteDisponseService.findwdListByTotalId(wasteDisponsalTotal.getTotalId());
		if(null==wasteDisponsalList || wasteDisponsalList.size()==0){
			msg = ERROR;
		}
		return msg;
	}
	
	/**
	 * 根据报废单id显示报废单详细
	 * @return
	 */
	public String selectWDDetail(){
//		wasteDisponseService.findwdListByTotalId(wasteDisponsalTotal.getEpId())
		wasteDisponsalTotal = wasteDisponseService.getwdtById(wasteDisponsalTotal.getTotalId());
		wasteDisponsalList = wasteDisponseService.findwdListByTotalId(wasteDisponsalTotal.getTotalId());
		if(null == wasteDisponsalList || wasteDisponsalList.size()<=0){
			return ERROR;
		}
		return "selectWDDetail";
	}
	/**
	 * 弹出详细信息框
	 * @return
	 */
	public String showGoodsDetail(){
		goods = wasteDisponseService.getGoodsByGoodsId(goodsId);
		return "showDetailTanChu";//waste_tanchuGoodsDetail.jsp
	}
	/**
	 * 删除废品数据
	 * @return
	 */
	public String deletewdt(){
		boolean flag = wasteDisponseService.deletewdt(wasteDisponsalTotal.getTotalId());
		if(!flag){
			setMessage("删除失败");
			return ERROR;
		}
		setMessage("删除成功");
		return "deletewdt";
	}
	
	//进入重新申请报废处理页面  使用wasteDisponseTotal.totalId
	public String reapplywd(){
		wasteDisponsalList = wasteDisponseService.findwdListByTotalId(wasteDisponsalTotal.getTotalId());
		//刷新库存量
		for (WasteDisponsal wd : wasteDisponsalList) {
			Float curquantity = wasteDisponseService.getGoodsCurquantity(wd.getGoodsId());
			wd.setGoodsCurQuantity(curquantity);
		}
		wasteDisponsalTotal = wasteDisponseService.getwdtById(wasteDisponsalTotal.getTotalId());
		return "reapplywd";
	}
	
	//条件查询报废库
	@SuppressWarnings("unchecked")
	public String findGoodsByWasteProduct(){
		
		String msg = "findGoodsOK";
		if (pagestatus != null && "zz".equals(pagestatus)) {
			msg = "findGoodsZZ";
		}
		this.pageSize = 15;
		this.setUrl("wasteDisposeAction!findConditionGoods.action?pagestatus=" + pagestatus);
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != goods) {
			request.getSession().setAttribute("goods", goods);
		} else {
			goods = (Goods) request.getSession().getAttribute("goods");
			if (pagestatus != null && "zz".equals(pagestatus)) {
				request.getSession().removeAttribute("goods");
			} else if ("bhg".equals(pagestatus)) {
				request.getSession().removeAttribute("goods");
			}
		}
		if(null == goods){
			goods = new Goods();
		}
		goods.setGoodsClass("废品库");
		if (null != startDate) {
			request.getSession().setAttribute("startDate", startDate);
		} else {
			startDate = (String) request.getSession().getAttribute("startDate");
		}
		if (null != endDate) {
			request.getSession().setAttribute("endDate", endDate);
		} else {
			endDate = (String) request.getSession().getAttribute("endDate");
		}
		goodsServer.pushkc1();
		Object[] obj = goodsServer.findGoods(goods, startDate, endDate, Integer
				.parseInt(cpage), pageSize, role, pagestatus,null);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		sumcount = (Double) obj[2];
		isall = (Boolean) obj[3];
		goodHouseList = wareHouseAuthService.findgoodHouselist();
		Map session = ActionContext.getContext().getSession();
		Users user = (Users) session.get(TotalDao.users);
		String code = user.getCode();

		for (Goods g : list) {
			String warehouse = g.getGoodsClass();
			if (wareHouseAuthService.getOut(code).contains(warehouse)) {
				g.setBout(true);
			}
			if (wareHouseAuthService.getEdit(code).contains(warehouse)) {
				g.setBedit(true);
			}
		}
			//findGoodsZZ  goods_bflistfind.jsp
		return msg; // findGoodsOK goods_waste.jsp
	}
	
	public String addWasteDisponse(){
		//wasteDisponsal  goodsId  判断有没有相同的goodsId
		
		if(null!= wasteDisponsal && null!= goodsId && !"".equals(goodsId)){
			String disponse;
			try {
				disponse = wasteDisponseService.addWasteDisponse(wasteDisponsal, goodsId);
				if(disponse == "true"){
					errorMessage = "申请成功";
					return "addWaseDisponse";
				}else {
					errorMessage =disponse;
					return "error";
				}
			} catch (Exception e) {
				e.printStackTrace();
				errorMessage = e.getMessage();
			}
			
		}
		return ERROR;
	}
	
	//查找所有的报废库
	@SuppressWarnings("unchecked")
	public String findConditionGoods(){
		String msg = "findGoodsOK";
		if (pagestatus != null && "zz".equals(pagestatus)) {
			msg = "findGoodsZZ";
		}
		this.pageSize = 15;
		this.setUrl("wasteDisposeAction!findConditionGoods.action?pagestatus=" + pagestatus);
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != goods) {
			goods.setGoodsClass("废品库");
			request.getSession().setAttribute("goods", goods);
		} else {
			goods = (Goods) request.getSession().getAttribute("goods");
			if (pagestatus != null && "zz".equals(pagestatus)) {
				request.getSession().removeAttribute("goods");
			} else if ("bhg".equals(pagestatus)) {
				request.getSession().removeAttribute("goods");
			}
		}
		if(goods==null){
			goods = new Goods();
		}
		goods.setGoodsClass("废品库");
		if (null != startDate) {
			request.getSession().setAttribute("startDate", startDate);
		} else {
			startDate = (String) request.getSession().getAttribute("startDate");
		}
		if (null != endDate) {
			request.getSession().setAttribute("endDate", endDate);
		} else {
			endDate = (String) request.getSession().getAttribute("endDate");
		}
		goodsServer.pushkc1();
		Object[] obj = goodsServer.findGoods(goods, startDate, endDate, Integer
				.parseInt(cpage), pageSize, role, pagestatus,null);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		sumcount = (Double) obj[2];
		isall = (Boolean) obj[3];
		goodHouseList = wareHouseAuthService.findgoodHouselist();
		Map session = ActionContext.getContext().getSession();
		Users user = (Users) session.get(TotalDao.users);
		String code = user.getCode();

		for (Goods g : list) {
			String warehouse = g.getGoodsClass();
			if (wareHouseAuthService.getOut(code).contains(warehouse)) {
				g.setBout(true);
			}
			if (wareHouseAuthService.getEdit(code).contains(warehouse)) {
				g.setBedit(true);
			}
		}

		return msg; // findGoodsOK goods_listfind.jsp
	}

	//生成报废处理单
	public String generatorManage(){
		String msg = "wasteManage_apply";
		if(null!=wasteDisponsalList){
			Iterator<WasteDisponsal> iterator = wasteDisponsalList.iterator();
			while(iterator.hasNext()){
				WasteDisponsal temp = iterator.next();
				if(null== temp|| null==temp.getGoodsId() || "".equals(temp.getGoodsId())){
					iterator.remove();
				}
			}
			boolean flag = wasteDisponseService.createGeneratorManage(wasteDisponsalList,wasteDisponsalTotal);
			if(!flag){
				msg = "waste_err";
			}
		}else{
			setRole(role);
			msg = "waste_err";
		}
		return msg;
	}
	
	//再次申请提交
	public String reapplywdCommit(){
		String msg = "wasteManage_apply";
		setMessage("申请失败");
		WasteDisponsal wmTemp = null;
		wasteDisponsalList = new ArrayList<WasteDisponsal>();
		for (int i = 0; i < wdId.size(); i++) {
			wmTemp = new WasteDisponsal(wdId.get(i), reducedNum.get(i), reducedPrice.get(i));
			wasteDisponsalList.add(wmTemp);
		}
		if(null!=wasteDisponsalList){
			boolean flag = wasteDisponseService.reapplywd(wasteDisponsalList,wasteDisponsalTotal);
			if(flag){
				setMessage("申请成功");
				return "wasteManage_apply";
			}
		}else{
			setRole(role);
			msg = "reapplywd_err";
		}
		return msg;
	}
	
	//签名和打印或者确认价格
	public String signatureAndPrint(){
		
		wasteDisponsalTotal =wasteDisponseService.getwdtById(totalId);
		wasteDisponsalList = wasteDisponseService.findWasteDisponsals(totalId);
		/*Map<Integer, Object> map = wasteDisponseService.findPayExecutionNode(totalId);
		System.out.println(map);*/
		if(null!= tag && tag.equals("confirmPrice")){
			return "confirmPrice";//waste_confirmPrice.jsp
		}else{
			return "signture";
		}
	}
	
	// 查看借款审批对应审批节点人
	@SuppressWarnings("unchecked")
	public void findPayExecutionNode() {
		Map<Integer, Object> map = wasteDisponseService.findPayExecutionNode(totalId);
		List<Signature> sigList = (List<Signature>) map.get(1);
		List<ExecutionNode> nodeList = (List<ExecutionNode>) map.get(2);
		MKUtil.writeJSON(true, "", nodeList, sigList);// 把结果传到页面
	}
	
	//查看本人申请未提交的报销单详细
	public String showWasteApply(){
		wasteDisponsalList =wasteDisponseService.showWasteApply();
		return "showWasteApply";//waste_apply.jsp
	}
	
	//提交申请的废品单
	public String wasteApplyCommit(){
		String msg = "wasteManage_apply";
		setMessage("申请失败");
		WasteDisponsal wmTemp = null;
		wasteDisponsalList = new ArrayList<WasteDisponsal>();
		for (int i = 0; i < wdId.size(); i++) {
			wmTemp = new WasteDisponsal(wdId.get(i), reducedNum.get(i), reducedPrice.get(i));
			wasteDisponsalList.add(wmTemp);
		}
		if(null!=wasteDisponsalList){
			boolean flag;
			try {
				flag = wasteDisponseService.createGeneratorManage(wasteDisponsalList, wasteDisponsalTotal);
				if(flag){
					setMessage("申请成功");
					return "wasteManage_apply";
				}
			} catch (Exception e) {
				e.printStackTrace();
				setErrorMessage(e.getMessage());
				return ERROR;
			}
			
		}else{
			setRole(role);
			msg = "reapplywd_err";
		}
		return msg;
	}
	
	public void delateDispose(){
		boolean flag = wasteDisponseService.deleteDisposal(id);
		if(!flag){
			setMessage("删除失败");
		}else{
			setMessage("删除成功");
		}
		MKUtil.writeJSON(message);
	}
	
	/**
	 * 确定价格
	 * @return
	 */
	public String updatePrice(){
		try {
			errorMessage = wasteDisponseService.updatePrice(wasteDisponsalList, attachment, attachmentFileName);//true
			setUrl("wasteDisposeAction!findWhereWaste.action?tag=caiwu");
		} catch (Exception e) {
			errorMessage = e.getMessage();
		}
		
		return "error";
	}
	
	public WareHouseAuthService getWareHouseAuthService() {
		return wareHouseAuthService;
	}


	public void setWareHouseAuthService(WareHouseAuthService wareHouseAuthService) {
		this.wareHouseAuthService = wareHouseAuthService;
	}


	public GoodsServer getGoodsServer() {
		return goodsServer;
	}


	public void setGoodsServer(GoodsServer goodsServer) {
		this.goodsServer = goodsServer;
	}


	public BaoFeiGoods getBaofeigoods() {
		return baofeigoods;
	}


	public void setBaofeigoods(BaoFeiGoods baofeigoods) {
		this.baofeigoods = baofeigoods;
	}


	public Goods_bzsq getGoodsBzsq() {
		return goodsBzsq;
	}


	public void setGoodsBzsq(Goods_bzsq goodsBzsq) {
		this.goodsBzsq = goodsBzsq;
	}
	public Goods getGoods() {
		return goods;
	}


	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public List<YuLiaoApply> getYuLiaoApplyList() {
		return yuLiaoApplyList;
	}


	public void setYuLiaoApplyList(List<YuLiaoApply> yuLiaoApplyList) {
		this.yuLiaoApplyList = yuLiaoApplyList;
	}


	public List<Goods> getList() {
		return list;
	}


	public void setList(List<Goods> list) {
		this.list = list;
	}

	public List<WareHouse> getWarehouseList() {
		return warehouseList;
	}


	public void setWarehouseList(List<WareHouse> warehouseList) {
		this.warehouseList = warehouseList;
	}


	public List<GoodHouse> getGoodHouseList() {
		return goodHouseList;
	}


	public void setGoodHouseList(List<GoodHouse> goodHouseList) {
		this.goodHouseList = goodHouseList;
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


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}

	public List<String> getOrderNumberList() {
		return orderNumberList;
	}

	public void setOrderNumberList(List<String> orderNumberList) {
		this.orderNumberList = orderNumberList;
	}

	public String getPagestatus() {
		return pagestatus;
	}
	public void setPagestatus(String pagestatus) {
		this.pagestatus = pagestatus;
	}


	public double getSumcount() {
		return sumcount;
	}


	public void setSumcount(double sumcount) {
		this.sumcount = sumcount;
	}
	public boolean isIsall() {
		return isall;
	}
	public void setIsall(boolean isall) {
		this.isall = isall;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}


	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getFirsttime() {
		return firsttime;
	}


	public void setFirsttime(String firsttime) {
		this.firsttime = firsttime;
	}

	public String getEndtime() {
		return endtime;
	}


	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	private WasteDisponseService wasteDisponseService;
	
	public WasteDisponseService getWasteDisponseService() {
		return wasteDisponseService;
	}

	public void setWasteDisponseService(WasteDisponseService wasteDisponseService) {
		this.wasteDisponseService = wasteDisponseService;
	}

	public Integer getTotalId() {
		return totalId;
	}

	public void setTotalId(Integer totalId) {
		this.totalId = totalId;
	}

	public List<Integer> getWdId() {
		return wdId;
	}

	public void setWdId(List<Integer> wdId) {
		this.wdId = wdId;
	}

	public List<Float> getReducedNum() {
		return reducedNum;
	}

	public void setReducedNum(List<Float> reducedNum) {
		this.reducedNum = reducedNum;
	}

	public List<Float> getReducedPrice() {
		return reducedPrice;
	}

	public void setReducedPrice(List<Float> reducedPrice) {
		this.reducedPrice = reducedPrice;
	}

	public String getProposer() {
		return proposer;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
	}

	public WasteDisponsalTotal getWasteDisponsalTotal() {
		return wasteDisponsalTotal;
	}

	public void setWasteDisponsalTotal(WasteDisponsalTotal wasteDisponsalTotal) {
		this.wasteDisponsalTotal = wasteDisponsalTotal;
	}

	public List<WasteDisponsal> getWasteDisponsalList() {
		return wasteDisponsalList;
	}

	public void setWasteDisponsalList(List<WasteDisponsal> wasteDisponsalList) {
		this.wasteDisponsalList = wasteDisponsalList;
	}

	public List<WasteDisponsalTotal> getShowwdtList() {
		return showwdtList;
	}

	public void setShowwdtList(List<WasteDisponsalTotal> showwdtList) {
		this.showwdtList = showwdtList;
	}

	public FundApplyServer getFundApplyServer() {
		return fundApplyServer;
	}

	public void setFundApplyServer(FundApplyServer fundApplyServer) {
		this.fundApplyServer = fundApplyServer;
	}

	public Map<Integer, Object> getExecutionNode() {
		return executionNode;
	}

	public void setExecutionNode(Map<Integer, Object> executionNode) {
		this.executionNode = executionNode;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public WasteDisponsal getWasteDisponsal() {
		return wasteDisponsal;
	}

	public void setWasteDisponsal(WasteDisponsal wasteDisponsal) {
		this.wasteDisponsal = wasteDisponsal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public File[] getAttachment() {
		return attachment;
	}

	public void setAttachment(File[] attachment) {
		this.attachment = attachment;
	}

	public String[] getAttachmentFileName() {
		return attachmentFileName;
	}

	public void setAttachmentFileName(String[] attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}

	public String[] getAttachmentContentType() {
		return attachmentContentType;
	}

	public void setAttachmentContentType(String[] attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
	}

	public List<WasteDisponsalTotal> getNotPriceAndEp() {
		return notPriceAndEp;
	}

	public void setNotPriceAndEp(List<WasteDisponsalTotal> notPriceAndEp) {
		this.notPriceAndEp = notPriceAndEp;
	}

	public List<WasteDisponsalTotal> getBePriceNotEp() {
		return bePriceNotEp;
	}

	public void setBePriceNotEp(List<WasteDisponsalTotal> bePriceNotEp) {
		this.bePriceNotEp = bePriceNotEp;
	}

	
}
