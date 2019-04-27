package com.task.action.zhuseroffer;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.zhuseroffer.NoPriceprocessServer;
import com.task.entity.zhuseroffer.NoPriceprocess;
import com.task.entity.zhuseroffer.SumProcess;
import com.task.entity.zhuseroffer.ZhuserOffer;
import com.task.util.MKUtil;
import com.task.util.Util;
import com.tast.entity.zhaobiao.ZhUser;

public class NoPriceprocessAction extends ActionSupport{
	private NoPriceprocessServer noPriceprocessServer;
	private NoPriceprocess noPriceprocess;
	private List<NoPriceprocess> noPriceprocessList;
	private List<ZhUser> zhUserList;
	private List<ZhuserOffer> zhuserOfferList;
	private SumProcess sumProcess;
	private List<SumProcess> sumProcessList;
	private ZhuserOffer zhuserOffer;
	private ZhUser zhUser;
	private Integer[] zhUserId;
	private Integer[] offerId;
	private String offerIds;
	private Integer id;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String errorMessage;
	private String pageStatus;
	private Integer noPriceprocessId;
	private String deadline;
	private String nowdate;
	private String tishi;
	public String findAll(){
			Map<Integer, Object> map = noPriceprocessServer
			.findAll(noPriceprocess, Integer
					.parseInt(cpage), pageSize,pageStatus);
			noPriceprocessList = (List<NoPriceprocess>) map.get(1);
			if (noPriceprocessList != null & noPriceprocessList.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("NoPriceprocessAction_findAll.action");
			}else{
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
			if("queren".equals(pageStatus)){//确认价格
				sumProcessList = noPriceprocessServer.findAllSumProcess("baojia");
				return "noPriceprocess_queren";
			}else if("yangpin".equals(pageStatus)){//确认样品
				sumProcessList = noPriceprocessServer.findAllSumProcess("yangpin");
				return "noPriceprocess_yangpin";
			}else if("luru".equals(pageStatus)){//价格录入
				sumProcessList = noPriceprocessServer.findAllSumProcess("luru");
				return "noPriceprocess_luru";
			}else if("baojia".equals(pageStatus)){//选择供应商
				return "noPriceprocess_all";
			}
			return null;
	}
	public String findAllBys(){
		if (noPriceprocess != null) {
			ActionContext.getContext().getSession().put("noPriceprocess",
				noPriceprocess);
		} else {// 用来保持分页时带着查询条件
			noPriceprocess = (NoPriceprocess) ActionContext.getContext()
				.getSession().get("noPriceprocess");
		}
		Map<Integer, Object> map = noPriceprocessServer
		.findAllBys(noPriceprocess, Integer
				.parseInt(cpage), pageSize);
		noPriceprocessList = (List<NoPriceprocess>) map.get(1);
		if (noPriceprocessList != null & noPriceprocessList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("NoPriceprocessAction_findAllBys.action");
		}else{
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
			return "noPriceprocess_status";
}
	public String findAllZhusers(){
		if (zhUser != null) {
			ActionContext.getContext().getSession().put("zhUser",
					zhUser);
		} else {// 用来保持分页时带着查询条件
			zhUser = (ZhUser) ActionContext.getContext()
					.getSession().get("zhUser");
		}
		nowdate = Util.getDateTime("yyyy-MM-dd");
		noPriceprocess = noPriceprocessServer.findNoPriceprocessByid(noPriceprocess.getId());
		if(noPriceprocess.getBjEndDate()!=null){
		if(nowdate.compareTo(noPriceprocess.getBjEndDate())<0||nowdate.compareTo(noPriceprocess.getBjEndDate())==0){
			Map<Integer, Object> map =noPriceprocessServer.findZhuser(zhUser, Integer
				.parseInt(cpage), pageSize);
			zhUserList = (List<ZhUser>) map.get(1);// 显示页的原材料和外购件列表
			if (zhUserList != null & zhUserList.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("NoPriceprocessAction_findAllZhusers.action?noPriceprocess.id="+noPriceprocess.getId());
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
			return "zhuser_all";
		}else{
			errorMessage="已经超过报价时间,截止日期为："+noPriceprocess.getBjEndDate();
			return "error";
		}
		}else{
			errorMessage="请点击左边的选项添加截止日期";
			return "error";
		}
	}
	//供应商查看所有页面
	public  String findAllforZhuser(){
		sumProcessList=  noPriceprocessServer.findSumProcessForZhuser();
		Map<Integer, Object> map =noPriceprocessServer.baojiaForProcess(zhuserOffer, Integer
				.parseInt(cpage), pageSize);
		zhuserOfferList = (List<ZhuserOffer>) map.get(1);
		if (zhuserOfferList != null & zhuserOfferList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("NoPriceprocessAction_findAllforZhuser.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "zhuserOfferAll_forZhuser";
	}
	//报价单详情（供应商报价）
	public  String findZhuserForSumProcessId(){
		zhuserOfferList= noPriceprocessServer.zhuserOfferFor(id);
		return "zhuserOfferAll_forZhuser";
	}
	//标价单详情（价格确认）
	public  String findZhuserForQueren(){
		noPriceprocessList= noPriceprocessServer.zhuserOfferForQr(id);
		return "noPriceprocess_queren";
	}
	//标价单详情（样品确认）
	public  String findZhuserForYangpin(){
		noPriceprocessList= noPriceprocessServer.zhuserOfferForQr(id);
		return "noPriceprocess_yangpin";
	}
	public String findZhuserForjiage(){
		noPriceprocessList= noPriceprocessServer.zhuserOfferForQr(id);
		return "noPriceprocess_luru";
	}
	//前往报价页面
	public String baojiaForzhUser(){
		if(zhuserOffer.getId()!=null&&!"".equals(zhuserOffer.getId())){
			zhuserOffer = noPriceprocessServer.findzhuserOffer(zhuserOffer.getId());
			noPriceprocess = noPriceprocessServer.getById(zhuserOffer.getProcessId());
			nowdate = Util.getDateTime("yyyy-MM-dd");
			if(nowdate.compareTo(noPriceprocess.getBjEndDate())<0||nowdate.compareTo(noPriceprocess.getBjEndDate())==0){
				return "baojiao_add";
			}else{
				errorMessage="已经超过报价时间,截止日期为："+noPriceprocess.getBjEndDate();
				return "error";
			}
		}else{
			errorMessage="没有找到该报价单！！";
			return "error";
		}
	}
	//报价操作
	public String biaojiao(){
		if(zhuserOffer!=null){
			errorMessage=noPriceprocessServer.baojiaForZhuser(zhuserOffer);
			return "error";
		}else{
			errorMessage="没有找到该报价单！！";
			return "error";
		}
	}
	//前往确认页面
	public String toQueren(){
		if(noPriceprocess.getId()!=null&&!"".equals(noPriceprocess.getId())){
			zhuserOfferList = noPriceprocessServer.querenYemian(noPriceprocess.getId());
			if("yangpin".equals(pageStatus)){//样品确认
				return "yangpin_queren";
			}else{//价格确认
				return "price_queren";
			}
		}else{
			errorMessage = "没有对应需要报价的工序";
			return "error";
		}
	}
	//前往确认页面
	public String allZhuserOffer(){
		if(noPriceprocess.getId()!=null&&!"".equals(noPriceprocess.getId())){
			zhuserOfferList = noPriceprocessServer.querenYemian(noPriceprocess.getId());
			return "price_allZhUsers";
		}else{
			errorMessage = "没有对应需要报价的工序";
			return "error";
		}
	}
	//绑定供应商
	public String bandZhusers(){
		if(zhUserId.length>0&&zhUserId!=null){
			errorMessage= noPriceprocessServer.bandZhuser(zhUserId, noPriceprocess.getId());
			return "error";
		}else{
			errorMessage="请选择供应商";
			return "error";
		}
	}
	//确认报价（打样中）
	public String querenPrice(){
		if(offerId.length>0&&offerId!=null){
			errorMessage= noPriceprocessServer.querenPrice(offerId, noPriceprocess.getId());
			return "error";
		}else{
			errorMessage="请选择报价";
			return "error";
		}
	}
	//确认样品
	public String passYangpin(){
		if(offerId.length>0||offerId.length<2){
			errorMessage= noPriceprocessServer.passYangpin(offerId,noPriceprocess.getId());
			return "error";
		}else{
			errorMessage="只能选择一家供应商进行确认";
			return "error";
		}
	}
	//查找一个需要报价工序
	public String findOne(){
		if(noPriceprocess.getId()!=null){
			noPriceprocess = noPriceprocessServer.getById(noPriceprocess.getId());
		}
		return "process_line";
	}
	//录入价格
	public String lvruPrice(){
		if(noPriceprocess.getId()!=null){
			errorMessage = noPriceprocessServer.luruPrice(noPriceprocess);
		}else{
			errorMessage ="工序号不能为空！";
		}
		return "error";
	}
	//添加期限
	public void addTime(){
		if (noPriceprocessId != null) {
			boolean b = noPriceprocessServer.addTime(noPriceprocessId,deadline);
			if (b) {
				MKUtil.writeJSON(b);
			} else{
				MKUtil.writeJSON("添加期限失败");
			}
		} else {
			MKUtil.writeJSON("无该工序");
		}
	}
	/**
	 * 根据工序名称进行整体报价
	 * @return
	 */
	public String findAllForPname(){
		if (noPriceprocess != null) {
			ActionContext.getContext().getSession().put("noPriceprocess",
				noPriceprocess);
		} else {// 用来保持分页时带着查询条件
			noPriceprocess = (NoPriceprocess) ActionContext.getContext()
				.getSession().get("noPriceprocess");
		}
		
		
		return "";
	}
	/*
	 * 根据总成件号进行报价查询所有
	 */
	public String findAllForMarkId(){
		//预备所有的状态为null的noPriceprocess
		if (noPriceprocess != null) {
			ActionContext.getContext().getSession().put("noPriceprocess",
				noPriceprocess);
		} else {// 用来保持分页时带着查询条件
			noPriceprocess = (NoPriceprocess) ActionContext.getContext()
				.getSession().get("noPriceprocess");
		}
		Map<Integer, Object> map = noPriceprocessServer
		.findAll(noPriceprocess, Integer
				.parseInt(cpage), pageSize,"chaxun");
		noPriceprocessList = (List<NoPriceprocess>) map.get(1);
		if (noPriceprocessList != null & noPriceprocessList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("NoPriceprocessAction_findAllForMarkId.action");
		}else{
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		//预备所有的 SumProcess用于显示
		sumProcessList = noPriceprocessServer.findAllSumProcess("baojia");
		if (noPriceprocessList == null & noPriceprocessList.size() == 0) {
			tishi="没有找到你要查询的内容,请检查后重试!";
		}
		return "sumProcess_all";
	}
	/**
	 * 根据总成件号报价到填写周期页面
	 * @return
	 */
	public String toZhouqi(){
		if(offerIds!=null&&!"".equals(offerIds)){
			//需要offerId中的所有noPriceprocessList
			noPriceprocessList = noPriceprocessServer.zhouqiForMarkId(offerIds);
			if(noPriceprocessList!=null){
				return "sumProcess_line";
			}else{
				errorMessage="请选择相同件号进行报价";
				return "error";
			}
		}else{
			errorMessage="请选择至少一个工序进行报价";
			return "error";
		}
		
	}
	public String delete(){
		if(sumProcess.getId()!=null){
			errorMessage = noPriceprocessServer.deleteSumProcess(sumProcess.getId());
			return "error";
		}else{
			return "数据异常";
		}
	}
	/**
	 *根据总成件号填写报价周期
	 */
	public void addTimeForMarkId(){
		if (offerIds!=null&&!"".equals(offerIds)) {
			boolean b = noPriceprocessServer.addTimeForMarkId(offerIds, deadline);
			if (b) {
				MKUtil.writeJSON(b);
			} else{
				MKUtil.writeJSON("添加期限失败");
			}
		} else {
			MKUtil.writeJSON("无该工序");
		}
	}
	public String allZhuserForMarkId(){
		if(sumProcess.getId()!=null&&!"".equals(sumProcess.getId())&&sumProcess.getId()>0){
			//获取sumProcess
			sumProcess =  noPriceprocessServer.findOneByid(sumProcess.getId());
			//预备所有的和sumProcess相关的noPriceprocess
			noPriceprocessList = noPriceprocessServer.findSumProcessById(sumProcess.getId());
			//所有供应商
			if (zhUser != null) {
				ActionContext.getContext().getSession().put("zhUser",
						zhUser);
			} else {// 用来保持分页时带着查询条件
				zhUser = (ZhUser) ActionContext.getContext()
						.getSession().get("zhUser");
			}
			nowdate = Util.getDateTime("yyyy-MM-dd");
			if(sumProcess.getBjEndDate()!=null){
			if(nowdate.compareTo(sumProcess.getBjEndDate())<0||nowdate.compareTo(sumProcess.getBjEndDate())==0){
				Map<Integer, Object> map =noPriceprocessServer.findZhuser(zhUser, Integer
					.parseInt(cpage), pageSize);
				zhUserList = (List<ZhUser>) map.get(1);
				if (zhUserList != null & zhUserList.size() > 0) {
					int count = (Integer) map.get(2);
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("NoPriceprocessAction_allZhuserForMarkId.action?sumProcess.id="+sumProcess.getId());
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
				//return "zhuser_all";
				return "allZhuser_forMarkId";
			}else{
				errorMessage="已经超过报价时间,截止日期为："+sumProcess.getBjEndDate();
				return "error";
			}
			}else{
				errorMessage="请添加截止日期";
				return "error";
			}
		}else{
			return "报价单为空";
		}
	}
	public String bindZhuserForMarkId(){
		if(zhUserId.length>0&&zhUserId!=null){
			errorMessage= noPriceprocessServer.bandZhuserForMarkId(zhUserId, sumProcess.getId());
			return "error";
		}else{
			errorMessage="请选择供应商";
			return "error";
		}
	}
	public NoPriceprocess getNoPriceprocess() {
		return noPriceprocess;
	}

	public void setNoPriceprocess(NoPriceprocess noPriceprocess) {
		this.noPriceprocess = noPriceprocess;
	}

	public List<NoPriceprocess> getNoPriceprocessList() {
		return noPriceprocessList;
	}

	public void setNoPriceprocessList(List<NoPriceprocess> noPriceprocessList) {
		this.noPriceprocessList = noPriceprocessList;
	}

	public NoPriceprocessServer getNoPriceprocessServer() {
		return noPriceprocessServer;
	}

	public void setNoPriceprocessServer(NoPriceprocessServer noPriceprocessServer) {
		this.noPriceprocessServer = noPriceprocessServer;
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
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public ZhUser getZhUser() {
		return zhUser;
	}
	public void setZhUser(ZhUser zhUser) {
		this.zhUser = zhUser;
	}
	public List<ZhUser> getZhUserList() {
		return zhUserList;
	}
	public void setZhUserList(List<ZhUser> zhUserList) {
		this.zhUserList = zhUserList;
	}
	public Integer[] getZhUserId() {
		return zhUserId;
	}
	public void setZhUserId(Integer[] zhUserId) {
		this.zhUserId = zhUserId;
	}

	public ZhuserOffer getZhuserOffer() {
		return zhuserOffer;
	}

	public void setZhuserOffer(ZhuserOffer zhuserOffer) {
		this.zhuserOffer = zhuserOffer;
	}

	public List<ZhuserOffer> getZhuserOfferList() {
		return zhuserOfferList;
	}

	public void setZhuserOfferList(List<ZhuserOffer> zhuserOfferList) {
		this.zhuserOfferList = zhuserOfferList;
	}

	public String getPageStatus() {	
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public Integer[] getOfferId() {
		return offerId;
	}

	public void setOfferId(Integer[] offerId) {
		this.offerId = offerId;
	}

	public Integer getNoPriceprocessId() {
		return noPriceprocessId;
	}

	public void setNoPriceprocessId(Integer noPriceprocessId) {
		this.noPriceprocessId = noPriceprocessId;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public SumProcess getSumProcess() {
		return sumProcess;
	}
	public void setSumProcess(SumProcess sumProcess) {
		this.sumProcess = sumProcess;
	}
	public List<SumProcess> getSumProcessList() {
		return sumProcessList;
	}
	public void setSumProcessList(List<SumProcess> sumProcessList) {
		this.sumProcessList = sumProcessList;
	}
	public String getNowdate() {
		return nowdate;
	}
	public void setNowdate(String nowdate) {
		this.nowdate = nowdate;
	}
	public String getTishi() {
		return tishi;
	}
	public void setTishi(String tishi) {
		this.tishi = tishi;
	}
	public String getOfferIds() {
		return offerIds;
	}
	public void setOfferIds(String offerIds) {
		this.offerIds = offerIds;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
