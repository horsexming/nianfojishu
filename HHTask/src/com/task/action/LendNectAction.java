package com.task.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.WareHouseAuthService;
import com.task.Server.ess.GoodsServer;
import com.task.Server.ess.LendNectServer;
import com.task.entity.ApplyScrap;
import com.task.entity.GoodHouse;
import com.task.entity.Goods;
import com.task.entity.Lend;
import com.task.entity.Nect;
import com.task.entity.Repay;
import com.task.entity.Users;
import com.task.util.MKUtil;
import com.task.util.Util;

/**
 * 库存中借领还管理功能Action层
 * 
 * @author 
 * 
 */
public class LendNectAction extends ActionSupport{
	private LendNectServer lendNectServer; 
	private GoodsServer goodsServer;
	private WareHouseAuthService wareHouseAuthService;
	private List<GoodHouse> goodHouseList;//仓区
	private Goods goods;
	private Goods newGoods;
	private Goods good;
	private List<Goods> list;
	private Integer goodsId;
	private String tag;
	private String cardNum;
	private Integer lendId;
	private Integer nectId;
	private Integer repayId;
	private Integer scrapId;
	private List   listAll;
	private int[] ids;
	private Float[] nums;
	
	private String subTag;
	private String printTag;
	private Float num;
	private Float canChangeNum;
	private String cards;//
	
	private int[] selectEdNect;
	private int[] selectedLend ;
	private int[] selectedRepay ;
	
	private File[] attachment;
	private String[] attachmentContentType;
	private String[] attachmentFileName;
	
	/**
	 * @return the selectedScrap
	 */
	public int[] getSelectedScrap() {
		return selectedScrap;
	}

	/**
	 * @param selectedScrap the selectedScrap to set
	 */
	public void setSelectedScrap(int[] selectedScrap) {
		this.selectedScrap = selectedScrap;
	}
	private int[] selectedScrap ;
	/**
	 * @return the selectedLend
	 */
	public int[] getSelectedLend() {
		return selectedLend;
	}

	/**
	 * @param selectedLend the selectedLend to set
	 */
	public void setSelectedLend(int[] selectedLend) {
		this.selectedLend = selectedLend;
	}

	/**
	 * @return the canChangeNum
	 */
	public Float getCanChangeNum() {
		return canChangeNum;
	}

	/**
	 * @param canChangeNum the canChangeNum to set
	 */
	public void setCanChangeNum(Float canChangeNum) {
		this.canChangeNum = canChangeNum;
	}

	public Float getNum() {
		return num;
	}

	public void setNum(Float num) {
		this.num = num;
	}
	// 分页
	private String cpage = "1";
	private int pageSize = 15;//分页每页条数
	private String pagestatus;// 页面状态;
	private String startDate;// 开始时间
	private String endDate;// 截止时间
	private String total;//共有多少页
	private String role;
	private double sumcount;
	private double canUseCount;
	private boolean isall;
	private String url;
	
	
	private Users user;
	private Users loginUser;
	private Lend lend;
	private Nect nect;
	private ApplyScrap scrap;
	
	
	//历史对象
	private Lend lendHistory;

	private Nect nectHistory;
	private Repay repayHistory;
	private ApplyScrap scrapHistory;
	
	
	private Nect changeNewNect;//
	private List listChange;
	private Nect newNect;

	/**
	 * @return the newNect
	 */
	public Nect getNewNect() {
		return newNect;
	}

	/**
	 * @param newNect the newNect to set
	 */
	public void setNewNect(Nect newNect) {
		this.newNect = newNect;
	}
	private String successMessage;// 消息
	private String errorMessage;
	
	/**
	 * 查找库存所有可借，领还记录
	 * @return
	 */
	public String findAllIsBent(){
			HttpServletRequest request = ServletActionContext.getRequest();
			//存取goods,换页得到上一页面goods信息
			if (null != goods) {
				request.getSession().setAttribute("goods", goods);
			} else {
				goods = (Goods) request.getSession().getAttribute("goods");				
			}
			
			if("lend".equals(tag)){
				if(goods!=null){
					goods.setNectCanChangeStatus("");
				}
			}
 			Object[] obj = lendNectServer.findAAllLNGoods(goods, startDate, endDate, Integer
					.parseInt(cpage), pageSize, tag, pagestatus);
  			int count = (Integer) obj[0];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			list = (List) obj[1];
			sumcount = (Double) obj[2];
			isall = (Boolean) obj[3];
			Map session = ActionContext.getContext().getSession();
			Users user = (Users) session.get(TotalDao.users);
			this.setUrl("LendNectAction!findAllIsBent.action?tag="+tag);//分页跳转地址
			if(tag != null && "lend".equals(tag)){
				return "goods_listfindLend"; // 
			}else if(tag != null && "nect".equals(tag)){
				return "goods_listfindNect"; // 
			}
			return null;
	}
	/**
	 * 查找出借历史记录
	 */
	public String  queryLendHistory(){
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			if(lendHistory!=null){
				request.getSession().setAttribute("lendHistory", lendHistory);
			}else{
				lendHistory=(Lend)request.getSession().getAttribute("lendHistory");
			}
			if(startDate!=null){
				request.getSession().setAttribute("startDate", startDate);
			}else{
				startDate=(String) request.getSession().getAttribute("startDate");
			}
			
			if(endDate!=null){
				request.getSession().setAttribute("endDate", endDate);
			}else{
				endDate=(String) request.getSession().getAttribute("endDate");
			}
			Object[] obj = lendNectServer.findLendHistory(lendHistory, startDate, endDate, Integer
					.parseInt(cpage), pageSize);	
			int count = (Integer) obj[0];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			list = (List) obj[1];
			sumcount = (Double) obj[2];
			this.setUrl("LendNectAction!queryLendHistory.action");//分页跳转地址
			return "goods_findHistoryLend";
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	/**
	 * 查找领用历史记录
	 */
	public String queryNectHistory(){
			HttpServletRequest request = ServletActionContext.getRequest();
			if(nectHistory!=null){
				request.getSession().setAttribute("nectHistory", nectHistory);
			}else{
				nectHistory=(Nect)request.getSession().getAttribute("nectHistory");
			}
			if(startDate!=null){
				request.getSession().setAttribute("startDate", startDate);
			}else{
				startDate=(String) request.getSession().getAttribute("startDate");
			}
			
			if(endDate!=null){
				request.getSession().setAttribute("endDate", endDate);
			}else{
				endDate=(String) request.getSession().getAttribute("endDate");
			}
			Object[] obj = lendNectServer.findNectHistory(nectHistory, startDate, endDate, Integer
					.parseInt(cpage), pageSize,null);	
			int count = (Integer) obj[0];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			list = (List) obj[1];
			sumcount = (Double) obj[2];
			this.setUrl("LendNectAction!queryNectHistory.action");//分页跳转地址
			return "goods_findHistoryNect";
		
		
	}
	/**
	 * 查找归还历史记录
	 */
	public String queryRepayHistory(){
		
			HttpServletRequest request = ServletActionContext.getRequest();
			if(repayHistory!=null){
				request.getSession().setAttribute("repayHistory", repayHistory);
			}else{
				repayHistory=(Repay)request.getSession().getAttribute("repayHistory");
			}
			if(startDate!=null){
				request.getSession().setAttribute("startDate", startDate);
			}else{
				startDate=(String) request.getSession().getAttribute("startDate");
			}
			
			if(endDate!=null){
				request.getSession().setAttribute("endDate", endDate);
			}else{
				endDate=(String) request.getSession().getAttribute("endDate");
			}
			Object[] obj = lendNectServer.findRepayHistory(repayHistory, startDate, endDate, Integer
					.parseInt(cpage), pageSize);	
			int count = (Integer) obj[0];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			list = (List) obj[1];
			sumcount = (Double) obj[2];
			this.setUrl("LendNectAction!queryRepayHistory.action");//分页跳转地址
			return "goods_findHistoryRepay";
		
		
	}
	/**
	 * 查找报废历史记录
	 */
	
	public String queryScrapHistory(){
		
			HttpServletRequest request = ServletActionContext.getRequest();
			if(scrapHistory!=null){
				request.getSession().setAttribute("scrapHistory", scrapHistory);
			}else{
				scrapHistory=(ApplyScrap)request.getSession().getAttribute("scrapHistory");
			}
			if(startDate!=null){
				request.getSession().setAttribute("startDate", startDate);
			}else{
				startDate=(String) request.getSession().getAttribute("startDate");
			}
			
			if(endDate!=null){
				request.getSession().setAttribute("endDate", endDate);
			}else{
				endDate=(String) request.getSession().getAttribute("endDate");
			}
			Object[] obj = lendNectServer.findScrapHistory(scrapHistory, startDate, endDate, Integer
					.parseInt(cpage), pageSize);	
			int count = (Integer) obj[0];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			list = (List) obj[1];
			sumcount = (Double) obj[2];
			this.setUrl("LendNectAction!queryScrapHistory.action");//分页跳转地址
			return "goods_findHistoryScrap";
		
	}
	
	
	
	/**
	 * 办理库存外借记录
	 * @return
	 */
	public String createOneLend(){
		good=goodsServer.getGoodsById(goodsId);
		return "goods_initLend";//
	}
	/**
	 * 查找用户
	 */
	public String ajaxGetUser(){
		if(cardNum!=null){
			user=lendNectServer.findUserByCardNum(cardNum);
			if(user!=null){
				good=goodsServer.getGoodsById(goodsId);
				if(goods!=null && goods.getDemanddept()!=null
						&& !goods.getDemanddept().equals(user.getDept())){
					MKUtil.writeJSON(false, user.getName()+"的部门:"+user.getDept()
							+"与件号:"+goods.getGoodsMarkId()+"的需求部门:"+goods.getDemanddept()+"不一致", user, loginUser);
				}
				Map session = ActionContext.getContext().getSession();
				loginUser = (Users) session.get(TotalDao.users);		
				if("允许".equals(user.getPower())){
					MKUtil.writeJSON(true, "", user, loginUser);
				}else{
					MKUtil.writeJSON(false, user.getName()+"无领用权限!~", user, loginUser);
				}
			}else{
				return null;
			}
			
		}
		return null;
	}
	/***
	 * 库存外借/领用插值
	 * @return
	 */
	public void lend(){
		if("lend".equals(subTag)){
			if(lend!=null && good!=null){
				if(attachment!=null && attachment.length>0){
					String fileName=	MKUtil.saveFile(attachment, attachmentFileName, "Lend");
					lend.setFileName(fileName);
				}
				boolean flag=lendNectServer.insertLend(lend,good);
				String  lendId=lend.getId()+"";
				MKUtil.writeJSON(flag+","+lendId);
			}
		}else if("nect".equals(subTag)){
			if(nect!=null && good!=null){
				boolean flag=lendNectServer.insertNect(nect,good);
				String  nectId=nect.getId()+"";
				MKUtil.writeJSON(flag+","+nectId);
				
			}
		}
	}
	public String lend0(){
		if(lend!=null && good!=null){
			if(attachment!=null && attachment.length>0){
				String fileName=	MKUtil.saveFile(attachment, attachmentFileName, "Lend");
				lend.setFileName(fileName);
			}
			boolean flag=lendNectServer.insertLend(lend,good);
			if(flag){
				return "findAllIsBent";
			}else{
				return "goods_initLend";
			}
		}
		return "goods_initLend";
	}
	
	/**
	 * 打印库存外借/领用单printTag
	 * @return
	 */
	public String printLend(){
		if(lendId!=null){
			lend=lendNectServer.findLendById(lendId);
			return "goodsLend_print";
		}
//		else if(nectId!=null && "nect".equals(printTag)){
//			nect=lendNectServer.findNectById(nectId);
//			return "goodsNect_print";
//		}
		return null;
	}
	
	
	/**
	 *  打印库存领用单
	 */
	public String printNect(){
		if(nectId!=null){
			nect=lendNectServer.findNectById(nectId);
			return "goodsNect_print";
		}
		return null;
	}
	
	//*******************************************归还管理

	/**
	 * 根据卡号查询未还出借历史记录
	 */
	public String findIsLendBycardNum(){
		try {
			if(lend!=null){
				if(lend.getCardNum()=="" || lend.getCardNum().length()<=0){
					setErrorMessage("请输入卡号!");
					listAll=null;
					return "goods_initGiveBack";
				}
				HttpServletRequest request = ServletActionContext.getRequest();
				//存取listAll,换页得到上一页面分页条件信息
				if (null != lend) {
					request.getSession().setAttribute("lend", lend);
				} else {
					lend = (Lend) request.getSession().getAttribute("lend");				
				}
	 			Object[] obj = lendNectServer.findIsLentByCNum(lend,pageSize,Integer.parseInt(cpage));
	  			int count = (Integer) obj[0];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				listAll = (List) obj[1];
				if(listAll==null||listAll.size()<1){
					setErrorMessage("没有找到你要归还的东西,谢谢合作!");
					listAll=null;
					return "goods_initGiveBack";
				}
				sumcount = (Double) obj[2];
				isall = (Boolean) obj[3];
				this.setUrl("LendNectAction!findIsLendBycardNum.action");//分页跳转地址
				errorMessage="";
				return "goods_initGiveBack";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 根据卡号查找可以旧换新的领用历史记录
	 * 
	 */
	public String findNectedBycardNum0(){
		return "goods_initChangeNew";
	}
	public String findNectedBycardNum(){
		
//		if(changeNewNect==null){
//			return "goods_initChangeNew";
//		}else{
			HttpServletRequest request = ServletActionContext.getRequest();
			//存取listAll,换页得到上一页面分页条件信息
			
			//if(changeNewNect!=null){
				
			if (null != changeNewNect) {
				request.getSession().setAttribute("changeNewNect", changeNewNect);
			} else {
				changeNewNect = (Nect) request.getSession().getAttribute("changeNewNect");				
			}	
				
				if(changeNewNect.getCardNum()=="" || changeNewNect.getCardNum().length()<=0){
					errorMessage="请输入卡号!";
					listAll=null;
					return "goods_initChangeNew";
				}
				
				Object[] obj=lendNectServer.findNectHistory(changeNewNect, startDate, endDate,Integer.parseInt(cpage) , pageSize,"canChange");
				listAll = (List) obj[1];
				if(listAll==null||listAll.size()<1){
					setErrorMessage("没有找到可以以旧换新的东西,谢谢合作!");
					//listAll=null;
					return "goods_initChangeNew";
				}
				int count = (Integer) obj[0];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				sumcount = (Double) obj[2];
				this.setUrl("LendNectAction!findNectedBycardNum.action");//分页跳转地址
				errorMessage="";
				return "goods_initChangeNew";
				
			//}
			
//		}
		
		//return ERROR;
		
	}
	
	/**
	 * 显示某条归还记录
	 * @return
	 */
	public String showOneGiveBack(){
		if(lend!=null){
			lend=lendNectServer.findLendById(lend.getId());
		}
		return "goods_oneGiveBack";
		
	}
	public String showOneScrap(){
		if(lend!=null){
			lend=lendNectServer.findLendById(lend.getId());
			loginUser=Util.getLoginUser();
		}
		return "goods_oneScrap";
	}
	/**
	 * 插入某条归还记录
	 * @return
	 */
	public void ajaxSaveOneRepay(){
		if(repay!=null){
			String msg=lendNectServer.insertOneRepay(repay);
			if("归还成功".equals(msg)){
				successMessage="归还成功,生成归还清单中!";
				MKUtil.writeJSON(true+","+repay.getId());
				//return "goodsRepay_print";
				//return "goods_initGiveBack";
			}
			//errorMessage=msg;
			//return "goods_oneGiveBack";
		}
		//return "goods_oneGiveBack";
	}
	/**
	 * 插入某条报废记录
	 */
	public void ajaxSaveOneScrap(){
		if(scrap!=null){
			String msg=lendNectServer.insertOneScrap(scrap);
			if("申请报废成功".equals(msg)){
				successMessage="申请报废成功,已成功进入废品库!";
				MKUtil.writeJSON(true+","+scrap.getId());
			}else{
				MKUtil.writeJSON(false+","+msg);
			}
			
		}
	}
	/**
	 * 打印归还记录
	 */
	public String printRepay(){
		if(repayId!=null){
			
			repay=lendNectServer.findRepayById(repayId);
			return "goodsRepay_print";
		}
		return null;
	}
	/**
	 * 打印库存中申请报废记录
	 * 
	 */
	public String printScrap(){
		if(scrapId!=null){
			scrap=lendNectServer.findScrapById(scrapId);
			return "goodsScrap_print";
		}
		return null;
	}
	/**
	 * 打印报废入库记录
	 * 
	 */
	//*******************************领用管理LendNectAction!createOneLend.action?goodsId=${gs.goodsId}
	public String createOneNect(){
		good=goodsServer.getGoodsById(goodsId);
		return "goods_initNect";
	}
	
	
	
	/**
	 * 删除某条领用历史记录
	 */
	public String deleteOneNectHistory(){
		if(nect!=null){
			boolean  flag=lendNectServer.delOneNect(nect);
			errorMessage=flag+"";
		}
		//String url=queryNectHistory();
		return "queryNectHistory";
	}
	/**
	 * 删除某条借用历史记录
	 * @return
	 */
	public String deleteOneLendHistory(){
		if(lend!=null){
			boolean  flag=lendNectServer.delOneLend(lend);
			errorMessage=flag+"";
		}
//		String url=queryLendHistory();
		return "queryLendHistory";
	}
	/**
	 * 删除某条归还历史记录
	 * @return
	 */
	public String deleteOneRepayHistory(){
		if(repay!=null){
			boolean  flag=lendNectServer.delOneRepay(repay);
			if(flag){
				errorMessage=flag+"";
			}
		}
		
		return "queryRepayHistory";
	}
	
	/**
	 * 删除某条报废历史记录
	 * @return
	 */

	public void deleteOneScrapHistory(){
		if(scrapId!=null){
			ApplyScrap scrap=new ApplyScrap();
			scrap.setId(scrapId);
			boolean  flag=lendNectServer.delOneScrap(scrap);
			MKUtil.writeJSON(flag);
		}
	}
	/**
	 * 查询库存是否存在可以旧换新的东西
	 * @return
	 */
	public void ajaxQueryGoodsCanChange(){
		Nect nect=lendNectServer.findNectById(nectId);
		if(nect!=null){
			String jianhao=nect.getGoodsMarkId();//
			goods=new Goods();
			goods.setGoodsMarkId(jianhao);
			goods.setGoodsClass("综合库");
			Object[] obj=lendNectServer.findAAllLNGoods(goods, null,null, Integer.parseInt(cpage), pageSize, "canChange", "");
  			int count = (Integer) obj[0];
  			MKUtil.writeJSON(count);
		}

	}
	/**
	 * 根据卡号和领用记录办理以旧换新业务
	 * @return
	 */
	public String  gainOneChangeNew(){
		if(nectId!=null){
			nect=lendNectServer.findNectById(nectId);
			if(nect!=null){
				//查找库存可以旧 换新
				String jianhao=nect.getGoodsMarkId();
				goods=new Goods();
				goods.setGoodsMarkId(jianhao);
				goods.setGoodsClass("综合库");
				Object[] obj=lendNectServer.findAAllLNGoods(goods, null,null,Integer.parseInt(cpage), pageSize, "canChange", "");
				int count=(Integer) obj[0];
				list = (List) obj[1];
				sumcount = (Double) obj[2];
//				listChange =null;
//				canChangeNum=null;
				return "goods_submitChangeNew";
			}
		}
		return null;
	}
	//获取库存可以旧换新数量并且只显示
	public String getGoodsCanNectOrder(){
		if(nect!=null){	
			nect=lendNectServer.findNectById(nect.getId());
			listChange=lendNectServer.findGoodsNectOrder(nect,Integer.parseInt(cpage), pageSize,canChangeNum);
			
			return "goods_submitChangeNew";
		}
		//nect=lendNectServer.findNectById(nect.getId());
		return "ERROR";
	}
	/**
	 * 插入以旧换新
	 * @return
	 */
	public void ajaxInsertChangeNew(){
		if(goodsId!=null && nectId!=null && num!=null){
			nect=lendNectServer.findNectById(nectId);
			newGoods=lendNectServer.findGoodsById(goodsId);
			//办理人
			//Users user=Util.getLoginUser();错
			Map session = ActionContext.getContext().getSession();
			user = (Users) session.get(TotalDao.users);
			Object[] obj=lendNectServer.insertOneChangeNew(nect,newGoods,user,num,newNect);
			newNect = (Nect) obj[0];
			errorMessage=(String) obj[1];
			if("以旧换新领用成功".equals(errorMessage)){
				//return "goodsChangeNewNect_print";
				MKUtil.writeJSON(true, errorMessage, newNect.getId());
				
			}else{
				MKUtil.writeJSON(false, errorMessage, newNect);
			}
		}
	}
	/**
	 * 打印以旧换新记录
	 * @return
	 */
	public String printChangeNew(){
		if(nectId!=null){
			newNect=lendNectServer.findNectById(nectId);
			return "goodsChangeNewNect_print";
		}
		return "ERROR";
	}
	
	/**
	 * 导出领用历史记录表格
	 * @return
	 */
	public String exportNectHis(){
		if(nectHistory!=null){
			lendNectServer.exportNectHis(nectHistory,startDate,endDate);
		}
		
		return null;
	}
	
	/**
	 * 导出外借用历史记录表格
	 * @return
	 */
	public String exportLendHis(){
		if(lendHistory!=null){
			lendNectServer.exportLendHis(lendHistory,startDate,endDate);
		}
		
		return null;
	}
	/**
	 * 导出归还用历史记录表格
	 * @return
	 */
	public String exportRepayHis(){
		if(repayHistory!=null){	
			lendNectServer.exportRepayHis(repayHistory, startDate, endDate);
		}
		
		return null;
	}
	/**
	 * 导出报废用历史记录表格
	 * @return
	 */
	public String exportScrapHis(){
		if(scrapHistory!=null ){
			lendNectServer.exportScrapHis(scrapHistory,startDate, endDate);
		}
		
		return null;
	}
	/**
	 * 批量领用
	 * @return
	 */
	public String pladdNect(){
		try {
			errorMessage =lendNectServer.pladdNect(ids, nums, cards);
		} catch (RuntimeException e) {
			errorMessage = e.getMessage();
		}
		return "queryNectHistory";
	}
	
	public Integer getGoodsId() {
		return goodsId;
	}
	/**
	 * @param goodsId the goodsId to set
	 */
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Goods getGoods() {
		return goods;
	}
	


	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	
	public void setTag(String tag){
		this.tag=tag;
	}
	public String getTag(){
		return tag;
	}

	public List<Goods> getList() {
		return list;
	}


	public void setList(List<Goods> list) {
		this.list = list;
	}

	public List<GoodHouse> getGoodHouseList() {
		return goodHouseList;
	}

	public void setGoodHouseList(List<GoodHouse> goodHouseList) {
		this.goodHouseList = goodHouseList;
	}

	public WareHouseAuthService getWareHouseAuthService() {
		return wareHouseAuthService;
	}

	public void setWareHouseAuthService(WareHouseAuthService wareHouseAuthService) {
		this.wareHouseAuthService = wareHouseAuthService;
	}


	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
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

	public LendNectServer getLendNectServer() {
		return lendNectServer;
	}





	public void setLendNectServer(LendNectServer lendNectServer) {
		this.lendNectServer = lendNectServer;
	}





	public GoodsServer getGoodsServer() {
		return goodsServer;
	}





	public void setGoodsServer(GoodsServer goodsServer) {
		this.goodsServer = goodsServer;
	}




	public String getCpage() {
		return cpage;
	}
	public Integer getLendId() {
		return lendId;
	}
	public void setLendId(Integer lendId) {
		this.lendId = lendId;
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




	public String getPagestatus() {
		return pagestatus;
	}





	public void setPagestatus(String pagestatus) {
		this.pagestatus = pagestatus;
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





	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getUrl() {
		return url;
	}
	public void setGood(Goods good) {
		this.good = good;
	}
	public Goods getGood() {
		return good;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Users getUser() {
		return user;
	}
	public void setLoginUser(Users loginUser) {
		this.loginUser = loginUser;
	}
	public Users getLoginUser() {
		return loginUser;
	}
	public void setLend(Lend lend) {
		this.lend = lend;
	}
	public Lend getLend() {
		return lend;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setCanUseCount(double canUseCount) {
		this.canUseCount = canUseCount;
	}
	public double getCanUseCount() {
		return canUseCount;
	}
	public void setListAll(List listAll) {
		this.listAll = listAll;
	}
	public List getListAll() {
		return listAll;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @return the nect
	 */
	public Nect getNect() {
		return nect;
	}
	/**
	 * @param nect the nect to set
	 */
	public void setNect(Nect nect) {
		this.nect = nect;
	}
	private Repay repay;
	/**
	 * @return the repay
	 */
	public Repay getRepay() {
		return repay;
	}
	/**
	 * @param repay the repay to set
	 */
	public void setRepay(Repay repay) {
		this.repay = repay;
	}

	/**
	 * @return the subTag
	 */
	public String getSubTag() {
		return subTag;
	}
	/**
	 * @param subTag the subTag to set
	 */
	public void setSubTag(String subTag) {
		this.subTag = subTag;
	}
	/**
	 * @return the nectId
	 */
	public Integer getNectId() {
		return nectId;
	}
	/**
	 * @param nectId the nectId to set
	 */
	public void setNectId(Integer nectId) {
		this.nectId = nectId;
	}
	
	public void setNectHistory(Nect nectHistory) {
		this.nectHistory = nectHistory;
	}
	public Nect getNectHistory() {
		return nectHistory;
	}
	/**
	 * @return the scrapId
	 */
	public Integer getScrapId() {
		return scrapId;
	}
	/**
	 * @param scrapId the scrapId to set
	 */
	public void setScrapId(Integer scrapId) {
		this.scrapId = scrapId;
	}
	/**
	 * @return the repayId
	 */
	public Integer getRepayId() {
		return repayId;
	}
	/**
	 * @param repayId the repayId to set
	 */
	public void setRepayId(Integer repayId) {
		this.repayId = repayId;
	}
	public void setPrintTag(String printTag) {
		this.printTag = printTag;
	}
	public String getPrintTag() {
		return printTag;
	}
	/**
	 * @return the scrap
	 */
	public ApplyScrap getScrap() {
		return scrap;
	}
	/**
	 * @param scrap the scrap to set
	 */
	public void setScrap(ApplyScrap scrap) {
		this.scrap = scrap;
	}
	/**
	 * @return the repayHistory
	 */
	public Repay getRepayHistory() {
		return repayHistory;
	}
	/**
	 * @param repayHistory the repayHistory to set
	 */
	public void setRepayHistory(Repay repayHistory) {
		this.repayHistory = repayHistory;
	}
	/**
	 * @return the scrapHistory
	 */
	public ApplyScrap getScrapHistory() {
		return scrapHistory;
	}
	/**
	 * @param scrapHistory the scrapHistory to set
	 */
	public void setScrapHistory(ApplyScrap scrapHistory) {
		this.scrapHistory = scrapHistory;
	}
	

	public void setChangeNewNect(Nect changeNewNect) {
		this.changeNewNect = changeNewNect;
	}
	public Nect getChangeNewNect() {
		return changeNewNect;
	}
	public void setListChange(List listChange) {
		this.listChange = listChange;
	}
	public List getListChange() {
		return listChange;
	}

	public void setNewGoods(Goods newGoods) {
		this.newGoods = newGoods;
	}

	public Goods getNewGoods() {
		return newGoods;
	}

	public void setSelectEdNect(int[] selectEdNect) {
		this.selectEdNect = selectEdNect;
	}

	public int[] getSelectEdNect() {
		return selectEdNect;
	}

	public void setSelectedRepay(int[] selectedRepay) {
		this.selectedRepay = selectedRepay;
	}

	public int[] getSelectedRepay() {
		return selectedRepay;
	}

	public Lend getLendHistory() {
		return lendHistory;
	}

	public void setLendHistory(Lend lendHistory) {
		this.lendHistory = lendHistory;
	}
	public LendNectAction(){
		
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}

	

	public Float[] getNums() {
		return nums;
	}

	public void setNums(Float[] nums) {
		this.nums = nums;
	}

	public String getCards() {
		return cards;
	}

	public void setCards(String cards) {
		this.cards = cards;
	}

	public File[] getAttachment() {
		return attachment;
	}

	public void setAttachment(File[] attachment) {
		this.attachment = attachment;
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
	
}