package com.task.action.payment;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.payment.FundApplyServer;
import com.task.entity.Maintenance;
import com.task.entity.Users;
import com.task.entity.approval.Signature;
import com.task.entity.payment.FundApply;
import com.task.entity.payment.FundApplyDetailed;
import com.task.entity.payment.InvoiceCheck;
import com.task.entity.payment.InvoiceCheckRecording;
import com.task.entity.sop.WaigouOrder;
import com.task.entity.system.ExecutionNode;
import com.task.util.MKUtil;
import com.task.util.Upload;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class FundApplyAction extends ActionSupport{

	private FundApplyServer fundserver;
	private FundApply fundApply;
	private InvoiceCheck invoiceCheck;
	private List<FundApply> fundList;
	private List<InvoiceCheck> invoiceCheckList;//发票记录
	private List<InvoiceCheck> invoiceCheckList1;//发票记录
	private InvoiceCheckRecording invoiceCheckRecording;
	private List<InvoiceCheckRecording> invoiceCheckRecordingList;//发票察验记录
	private FundApplyDetailed fundApplyDetailed;
	private List<FundApplyDetailed> fundDetailedList;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private Set<FundApplyDetailed> fundSet;
	private String password;
	private String wwNumber;
	private String about;
	private File fj;// 上传附件
	private String fjContentType;// 附件类型
	private String fjFileName;// 文件名称
	private List list;
	
	// 上传文件对象
	private File fujian;//发票
	// 上传文件名
	private String fujianFileName;
	// 上传文件类型
	private String fujianContentType;
	
	private String style;
	private String pagestatus;
	private String firstTime;
	private String endTime;
	private String errorMessage;
	private String successMessage;
	private String bwtCompany;
	private String fapiaohao;
	private Integer id;
	private int[] ids;
	private Double backMoney;
	
	public String text(){
		fundserver.bushuju(id);
		return "error";
	}
	
	public String text1(){
		return "invoiceCheck_text";
	}
	
	//查询资金使用申请单
	public String findfundList(){
		Users user = Util.getLoginUser();
		if(user==null){
			errorMessage="请先登录";
			return "error";
		}else if(pagestatus!=null&&pagestatus.equals("cw")){
//			if(!user.getDept().equals("财务部")){
//				errorMessage="您非财务人员,此功能仅供财务人员使用!";
//				return "error";
//			}
		}
		if (fundApply != null) {
			ActionContext.getContext().getSession().put("fundApply", fundApply);
		} else {
			fundApply = (FundApply) ActionContext.getContext().getSession().get(
					"fundApply");
		}
		Object[] obj = fundserver.findFundApplyList(fundApply, Integer
				.parseInt(cpage), pageSize, pagestatus, firstTime, endTime);
		if (obj != null && obj.length > 0) {
			fundList = (List<FundApply>) obj[0];
			if (fundList != null && fundList.size() > 0) {
				int count = (Integer) obj[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
					this.setUrl("FundApplyAction_findfundList.action?pagestatus="+pagestatus);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		
		return "fundList_show";
	}
	//跳往添加资金使用申请页面
	public String toAddfundApply(){
//		return "addfundApply";
		if(fundserver.selectInvoice()){
			//
			backMoney=fundserver.getbackmoney(Util.getLoginUser().getId());
			return "addfundApply4";
		}else {
			return "addfundApply2";
		}
	}
	
	//添加资金使用申请单
	public String addfundApply(){
		if(fundApply!=null){
			try {
				
//				if(fundApply!=null&&fundApply.getAbout().equals("支付")){
//					Calendar week = Calendar.getInstance();
//					int weekday = week.get(Calendar.DAY_OF_WEEK) - 1;
//					if (weekday > 3 && weekday < 6) {
//					} else {
//						errorMessage = "当前不在财务规定的报销时限内，请于周四，周五两天进行报账！";
//						return "error";
//					}
//				}
				if (fj != null) {
					String fileName = fjFileName;
					int index = fileName.lastIndexOf(".");
					String fileType=fileName.substring(index);
					/* set upload path*/
					String realFileName = Util.getDateTime("yyyyMMddHHmmssSSS")+fileType;
					String realFilePath = "/upload/file/fundApply/";
					String path = ServletActionContext.getServletContext().getRealPath(
							realFilePath);
					File dir = new File(path);
					if (!dir.exists()) {
						dir.mkdirs();// 如果不存在文件夹就创建
					}
					//保存文件
					Upload upload = new Upload();
					upload.UploadFile(fj, fileName, realFileName,
							realFilePath, null);
					fundApply.setAttachmentsFile(realFileName);
					fundApply.setOldFileName(fileName);
				}
				
				errorMessage = fundserver.addFundApply(fundApply);
				if("true".equals(errorMessage)){
					errorMessage="应付登记单申请成功!";
					MKUtil.writeJSON(true,errorMessage,fundApply.getId());
					//url = "FundApplyAction_findfundDetailedList.action?id="+fundApply.getId();
				}else{
//					errorMessage = "资金使用申请单申请失败!";
					MKUtil.writeJSON(false,errorMessage,null);
				}
			} catch (Exception e) {
				e.printStackTrace();
				errorMessage = e.getMessage();
				MKUtil.writeJSON(false,errorMessage,null);
			}
		}
		return null;
		//return "error";
	}
	//
	public String toupdatefundApply(){
		fundApply = fundserver.getFundApplyById(id);
		fundDetailedList =	fundserver.findfadListByid(id);
		return "updatefundApply";
	}
	public String delfundApply(){
		if(fundApply!=null){
			errorMessage = fundserver.delFundApply(fundApply);
			if("true".equals(errorMessage)){
				errorMessage="应付登记单删除成功!";
			}else{
				errorMessage = "应付登记单删除失败!";
			}
		}
		url = "FundApplyAction_findfundList.action?pagestatus="+pagestatus+"&cpage"+cpage;
		return "error";
	}
	public String updatefundApply(){
		if(fundApply!=null){
			errorMessage = fundserver.updateFundApply(fundApply);
			if("true".equals(errorMessage)){
				errorMessage="申请支付成功!";
			}else{
				errorMessage = "申请支付失败!";
			}
		}
		url = "FundApplyAction_findfundList.action?pagestatus="+pagestatus+"&cpage"+cpage;
		return "error";
	}
	//获得设备报修编号或者KVP号
	public void getzjStyleMx(){
		
		try {
			
			if("设备维修费".equals(style)){
				List<Maintenance> list = fundserver.getzjStyleMx(style);
				MKUtil.writeJSON(list);
			}else if("差旅、招待费".equals(style)){
				List<String> list = fundserver.getzjStyleMx(style);
				MKUtil.writeJSON(list);
			}else if("零件采购费".equals(style)){
				List<WaigouOrder> list = fundserver.getzjStyleMx(style);
				MKUtil.writeJSON(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//查看资金使用明细根据资金使用单Id
	public String findfundDetailedList(){
//		fundDetailedList =	fundserver.findfadListByid(id);
		fundApply = fundserver.getFundApplyAndDetailById(id);
		if(fundApply.getZhifuoryufu().equals("支付")){//支付
			if(fundApply.getAbout()!=null){
				if(fundApply.getAbout().equals("非主营业务应付")||fundApply.getAbout().equals("主营应付")){
					return "fundApply_fzy";
				}
				if(fundApply.getAbout().equals("外购(历史)")){
					return "fundApply_sc";
				}
				if(fundApply.getAbout().equals("外购")||fundApply.getAbout().equals("外委")){
					return "fundApply_ww";
				}
			}
			return "fundApply_zhifu1";
		}else{//预付
			if(fundApply.getAbout()!=null&&fundApply.getAbout().equals("外委")){
				return "fundApply_ww";
			}
			return "fundApply_yufu";
		}
	}
	/**
	 * 同意申请
	 * @return
	 */
	public String agreeApply(){
		String msg = fundserver.agreeApply(id);
		if(msg.equals("true")){
			errorMessage ="审批成功!";
		}else{
			errorMessage =msg;
		}
		url="FundApplyAction_findfundList.action?pagestatus=cw&cpage="+cpage;
		return "error";
	}
	/**
	 * 打回申请
	 * @return
	 */
	public String backApply(){
		String msg = fundserver.backApply(id);
		if(msg.equals("true")){
			errorMessage ="审批成功!";
		}else{
			errorMessage =msg;
		}
		url="FundApplyAction_findfundList.action?pagestatus=cw&cpage="+cpage;
		return "error";
	}
	
	// 查看借款审批对应审批节点人
	public void findPay_ExecutionNode() {
		Map<Integer, Object> map = this.fundserver
				.findPay_ExecutionNode(this.id);
		List<Signature> sigList = (List<Signature>) map.get(1);
		List<ExecutionNode> nodeList = (List<ExecutionNode>) map.get(2);
//		System.out.println(sigList.get(0).getSignature_address());
		// list = (List) object[0];
		// list1 = (List) object[1];
		// Signature signature = (Signature) list.get(0);
		// System.out.println(signature.getSignature_address());
		// MKUtil.writeJSON(true, "",list);//把结果传到页面
		MKUtil.writeJSON(true, "", nodeList, sigList);// 把结果传到页面
	}
	
	public void sureSelf(){
		String msg = fundserver.sureSelf(id,password);
		MKUtil.writeJSON(msg);
	}
	/**
	 * 委托
	 * @return
	 */
	public String weituo(){
		try {
			String msg = fundserver.weituo(ids,bwtCompany);
			if(msg.equals("true")){
				errorMessage="委托成功!";
			}else{
				errorMessage=msg;
			}
		} catch (Exception e) {
			// TODO: handle exception
			errorMessage=e.getMessage();
		}
		return  "error";
	}
	
	public String findwwOrder(){
		 id = fundserver.findwwOrder(wwNumber);
		 if(about.equals("ww")){
			 return "fundApply_wworder";
		 }else{
			 return "fundApply_wgorder";
		 }
	}
	
	public void zyjl(){
		fundserver.zyjl();
	}
	
	public void getskdw(){
		list = fundserver.getskdw();
		MKUtil.writeJSON(list);
	}
	
	/**
	 * 手动录入发票信息
	 * @return
	 */
	public String toAddInvoce(){
		return "invoiceCheck_add";
	}
	/**
	 * 上传文件录入发票信息
	 * @return
	 */
	public String toAddInvoceFile(){
		return "invoiceCheck_addFile";
	}
	
	/**
	 * 定额发票录入
	 * @return
	 */
	public String toAddInvoceQuota() {
		return "invoiceCheck_addQuota";
	}
	/**
	 * 发票文件上传
	 */
	public void uploadInvoiceCheckFile() {
		String fileName = Util.getDateTime("yyyyMMddHHmmss");
		try {
			if (fujian != null) {
				// 文件路径
				// 打开存放文件的位置
				String realFilePath = "/upload/file/invoiceChe";
				String path = ServletActionContext.getServletContext()
						.getRealPath(realFilePath);
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();// 如果不存在文件夹就新建
				}
				Upload upload = new Upload();// 文件上传工具类
				fileName = upload.UploadFile(fujian, fujianFileName,
						null, realFilePath, null);
				invoiceCheck.setFile_path(fileName+"");// 文件新名称
			}
			errorMessage = fundserver.uploadInvoiceCheckFile(invoiceCheck);
			MKUtil.writeJSON(errorMessage);
		} catch (Exception e) {
			MKUtil.writeJSON("系统异常："+e.toString());
		}
	}
	public String toSelInvoce(){
		invoiceCheck = fundserver.findInvoiceCheckbyId(id);
		return "invoiceCheck_select";
	}
	public String addInvoce(){
		errorMessage = fundserver.addInvoiceCheckRecording(invoiceCheckRecording);
		if("查验成功".equals(errorMessage)){
			url = "FundApplyAction_findInvoceCheck.action?pagestatus="+pagestatus;
		}
		return "error";
	}
	public String addInvoceFile(){
		String realFileName = Util.getDateTime("yyyyMMddHHmmss");
		if (fujian != null) {
			// 文件路径
			// 打开存放文件的位置
			String realFilePath = "/upload/file/invoiceChe";
			String path = ServletActionContext.getServletContext()
					.getRealPath(realFilePath);
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();// 如果不存在文件夹就新建
			}
			Upload upload = new Upload();// 文件上传工具类
			realFileName = upload.UploadFile(fujian, fujianFileName,
					null, realFilePath, null);
			invoiceCheckRecording.setInvoiceFilees(realFileName+"");// 文件新名称
		}
		errorMessage = fundserver.addInvoiceCheckRecording(invoiceCheckRecording);
		if("查验成功".equals(errorMessage)){
			url = "FundApplyAction_findInvoceCheck.action?pagestatus="+pagestatus;
		}
		return "error";
	}
	/**
	 * 定额发票录入
	 * @return
	 */
	public String addInvoceQuota(){
		errorMessage = fundserver.addInvoiceQuota(invoiceCheck);
		if("添加成功".equals(errorMessage)){
			url = "FundApplyAction_findInvoceCheck.action?pagestatus="+pagestatus;
		}
		return "error";
	}
	/**
	 * 查询
	 * @return
	 */
	public String findInvoceCheck(){
		Users user = Util.getLoginUser();
		if(user==null){
			errorMessage="请先登录";
			return "error";
		}
		if (invoiceCheck != null) {
			ActionContext.getContext().getSession().put("InvoiceCheck", invoiceCheck);
		} else {
			invoiceCheck = (InvoiceCheck) ActionContext.getContext().getSession().get(
					"InvoiceCheck");
		}
		Object[] obj = fundserver.findInvoiceCheckList(invoiceCheck, Integer
				.parseInt(cpage), pageSize, pagestatus,fapiaohao);
		if (obj != null && obj.length > 0) {
			invoiceCheckList = (List<InvoiceCheck>) obj[0];
			if (invoiceCheckList != null && invoiceCheckList.size() > 0) {
				int count = (Integer) obj[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
					this.setUrl("FundApplyAction_findInvoceCheck.action?pagestatus="+pagestatus);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "invoiceCheck_show";
	}
	/**
	 * 查询
	 * @return
	 */
	public String findInvoceCheckXuan(){
		Users user = Util.getLoginUser();
		if(user==null){
			errorMessage="请先登录";
			return "error";
		}
		if (invoiceCheck != null) {
			ActionContext.getContext().getSession().put("InvoiceCheck1", invoiceCheck);
		} else {
			invoiceCheck = (InvoiceCheck) ActionContext.getContext().getSession().get(
			"InvoiceCheck1");
		}
		Object[] obj = fundserver.findInvoiceCheckList(invoiceCheck, Integer
				.parseInt(cpage), pageSize, pagestatus,fapiaohao);
		if (obj != null && obj.length > 0) {
			invoiceCheckList1 = (List<InvoiceCheck>) obj[0];
			if (invoiceCheckList1 != null && invoiceCheckList1.size() > 0) {
				int count = (Integer) obj[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("FundApplyAction_findInvoceCheckXuan.action?pagestatus="+pagestatus+"&fapiaohao="+fapiaohao);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "invoiceCheck_xuan";
	}
	
	public String findInvoiceCheckByfpdm() {
		invoiceCheck = fundserver.findInvoickCheckByFpdm(wwNumber);
		if(invoiceCheck!=null){
			wwNumber="/upload/file/invoiceChe/"+invoiceCheck.getFile_path();
			return "fapiao";
		}else{
			errorMessage="未查到发票信息!";
		}
		return "error";
	}
	
	public FundApplyServer getFundserver() {
		return fundserver;
	}
	public void setFundserver(FundApplyServer fundserver) {
		this.fundserver = fundserver;
	}
	public FundApply getFundApply() {
		return fundApply;
	}
	public void setFundApply(FundApply fundApply) {
		this.fundApply = fundApply;
	}
	public List<FundApply> getFundList() {
		return fundList;
	}
	public void setFundList(List<FundApply> fundList) {
		this.fundList = fundList;
	}
	public FundApplyDetailed getFundApplyDetailed() {
		return fundApplyDetailed;
	}
	public void setFundApplyDetailed(FundApplyDetailed fundApplyDetailed) {
		this.fundApplyDetailed = fundApplyDetailed;
	}
	public List<FundApplyDetailed> getFundDetailedList() {
		return fundDetailedList;
	}
	public void setFundDetailedList(List<FundApplyDetailed> fundDetailedList) {
		this.fundDetailedList = fundDetailedList;
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
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getPagestatus() {
		return pagestatus;
	}

	public void setPagestatus(String pagestatus) {
		this.pagestatus = pagestatus;
	}

	public String getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(String firstTime) {
		this.firstTime = firstTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public Set<FundApplyDetailed> getFundSet() {
		return fundSet;
	}
	public void setFundSet(Set<FundApplyDetailed> fundSet) {
		this.fundSet = fundSet;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int[] getIds() {
		return ids;
	}
	public void setIds(int[] ids) {
		this.ids = ids;
	}
	public String getBwtCompany() {
		return bwtCompany;
	}
	public void setBwtCompany(String bwtCompany) {
		this.bwtCompany = bwtCompany;
	}
	public String getWwNumber() {
		return wwNumber;
	}
	public void setWwNumber(String wwNumber) {
		this.wwNumber = wwNumber;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public File getFj() {
		return fj;
	}
	public void setFj(File fj) {
		this.fj = fj;
	}
	public String getFjContentType() {
		return fjContentType;
	}
	public void setFjContentType(String fjContentType) {
		this.fjContentType = fjContentType;
	}
	public String getFjFileName() {
		return fjFileName;
	}
	public void setFjFileName(String fjFileName) {
		this.fjFileName = fjFileName;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}

	public List<InvoiceCheck> getInvoiceCheckList() {
		return invoiceCheckList;
	}

	public void setInvoiceCheckList(List<InvoiceCheck> invoiceCheckList) {
		this.invoiceCheckList = invoiceCheckList;
	}

	public InvoiceCheckRecording getInvoiceCheckRecording() {
		return invoiceCheckRecording;
	}

	public void setInvoiceCheckRecording(InvoiceCheckRecording invoiceCheckRecording) {
		this.invoiceCheckRecording = invoiceCheckRecording;
	}

	public List<InvoiceCheckRecording> getInvoiceCheckRecordingList() {
		return invoiceCheckRecordingList;
	}

	public void setInvoiceCheckRecordingList(
			List<InvoiceCheckRecording> invoiceCheckRecordingList) {
		this.invoiceCheckRecordingList = invoiceCheckRecordingList;
	}

	public InvoiceCheck getInvoiceCheck() {
		return invoiceCheck;
	}

	public void setInvoiceCheck(InvoiceCheck invoiceCheck) {
		this.invoiceCheck = invoiceCheck;
	}

	public List<InvoiceCheck> getInvoiceCheckList1() {
		return invoiceCheckList1;
	}

	public void setInvoiceCheckList1(List<InvoiceCheck> invoiceCheckList1) {
		this.invoiceCheckList1 = invoiceCheckList1;
	}

	public String getFapiaohao() {
		return fapiaohao;
	}

	public void setFapiaohao(String fapiaohao) {
		this.fapiaohao = fapiaohao;
	}

	public File getFujian() {
		return fujian;
	}

	public void setFujian(File fujian) {
		this.fujian = fujian;
	}

	public String getFujianFileName() {
		return fujianFileName;
	}

	public void setFujianFileName(String fujianFileName) {
		this.fujianFileName = fujianFileName;
	}

	public String getFujianContentType() {
		return fujianContentType;
	}

	public void setFujianContentType(String fujianContentType) {
		this.fujianContentType = fujianContentType;
	}

	public Double getBackMoney() {
		return backMoney;
	}

	public void setBackMoney(Double backMoney) {
		this.backMoney = backMoney;
	}
	
}
