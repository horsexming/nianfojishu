package com.task.action.caiwu.core;

import java.io.File;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.InsuranceGoldServer;
import com.task.Server.caiwu.core.CorePayableServer;
import com.task.entity.InsuranceGold;
import com.task.entity.caiwu.BankAccounts;
import com.task.entity.caiwu.core.CorePayable;
import com.task.entity.caiwu.core.CorePayableMonth;
import com.task.entity.caiwu.core.MonthPayableBill;
import com.task.entity.caiwu.core.SupplierCorePayable;
import com.task.entity.paymonth.PayMonth;
import com.task.entity.paymonth.PayMonthDetail;
import com.task.util.MKUtil;
import com.task.util.Util;
import com.tast.entity.zhaobiao.PrepaymentsApplication;

/*****
 * 主营应付Action
 * 
 * @author liupei
 * 
 */
public class CorePayableAction extends ActionSupport {
	private CorePayableServer corePayableServer;// Server层
	private CorePayable corePayable;// 对象
	private CorePayableMonth corePayableMonth;// 对象
	private MonthPayableBill monthPayableBill;// 对象
	private SupplierCorePayable supplierCorePayable;// 对象
	private SupplierCorePayable supplierCorePayable2;// 对象
	private List<CorePayable> corePayableList;// 集合
	private List<CorePayableMonth> corePayableMonthList;// 集合
	private List<MonthPayableBill> monthPayableBillList;// 集合
	private List<SupplierCorePayable> supplierCorePayablerList;// 集合
	private List list;// 集合
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private Integer[] ids;// ids
	private Object[] objs;// ids
	private String pageStatus;// 页面状态
	private String qrTag;// 账单确认状态(yes/no)
	private File attachment;
	private String attachmentFileName;
	private String attachmentContentType;
	private String firstTime;
	private String endTime;
	private PayMonth payMonth;
	private List<PayMonth>  payMonthList;
	private List<PayMonthDetail> pmdList;
	private PayMonthDetail payMonthDetail;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String tag;
	private String houseName;
	private int maxsize;
	public String text() {
		// corePayableServer.chuliPir(-307.693f);
		System.out.println("000");
		return "error";
	}

	/****
	 * 查询对账单
	 * 
	 * @return
	 */
	public String findCorePayableMonthList() {
		if (corePayableMonth != null) {
			ActionContext.getContext().getSession().put("corePayableMonth",
					corePayableMonth);
		} else {
			corePayableMonth = (CorePayableMonth) ActionContext.getContext()
					.getSession().get("corePayableMonth");
		}
		Object[] object = corePayableServer
				.findCorePayableMonthList(corePayableMonth, Integer
						.parseInt(cpage), pageSize, pageStatus);
		if (object != null && object.length > 0) {
			corePayableMonthList = (List<CorePayableMonth>) object[0];
			if (corePayableMonthList != null && corePayableMonthList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				if (pageStatus == null) {
					pageStatus = "";
				}
				this
						.setUrl("CorePayableAction!findCorePayableMonthList.action?pageStatus="
								+ pageStatus);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "corePayableMonth_showList";
	}

	/****
	 * 查询主营应付列表明细
	 * 
	 * @return
	 */
	public String findCorePaybleList() {
		if (corePayable != null) {
			ActionContext.getContext().getSession().put("corePayable",
					corePayable);
		} else {
			corePayable = (CorePayable) ActionContext.getContext().getSession()
					.get("corePayable");
		}
		Object[] object = corePayableServer.findCorePaybleList(corePayable,
				Integer.parseInt(cpage), pageSize, pageStatus,firstTime,endTime);
		if (corePayable != null && corePayable.getJzMonth() != null
				&& corePayable.getJzMonth().length() > 0) {
			qrTag = Util.getDateTime("yyyy-MM")
					.equals(corePayable.getJzMonth())
					+ "";
		}
		if (object != null && object.length > 0) {
			corePayableList = (List<CorePayable>) object[0];
			if (corePayableList != null && corePayableList.size() > 0) {
				if ("dkp".equals(pageStatus)) {
					int count = (Integer) object[1];
					this.setTotal(1 + "");
				} else {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
				}

				if (pageStatus == null) {
					pageStatus = "";
				}
				this
						.setUrl("CorePayableAction!findCorePaybleList.action?pageStatus="
								+ pageStatus);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		if ("fksp".equals(pageStatus)) {
			return "corePayable_showfkspList";
		}
		return "corePayable_showList";
	}
	public void findPayMonthDetail(){
		if(payMonth!=null){
			MKUtil.writeJSON(corePayableServer.findPayMonthDetail(payMonth, firstTime, endTime,houseName,maxsize));
		}else{
			MKUtil.writeJSON("未填写数据");
		}
	}
	/****
	 * 查询月度应收应付账单
	 * 
	 * @return
	 */
	public String findMonthPayableBillList() {
		if (monthPayableBill != null) {
			ActionContext.getContext().getSession().put("monthPayableBill",
					monthPayableBill);
		} else {
			monthPayableBill = (MonthPayableBill) ActionContext.getContext()
					.getSession().get("monthPayableBill");
		}
		Object[] object = corePayableServer
				.findMonthPayableBillList(monthPayableBill, Integer
						.parseInt(cpage), pageSize, pageStatus);
		if (object != null && object.length > 0) {
			monthPayableBillList = (List<MonthPayableBill>) object[0];
			if (monthPayableBillList != null && monthPayableBillList.size() > 0) {
				int count = (Integer) object[1];
				monthPayableBill = (MonthPayableBill) object[2];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				if (pageStatus == null) {
					pageStatus = "";
				}
				this
						.setUrl("CorePayableAction!findMonthPayableBillList.action?pageStatus="
								+ pageStatus);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "monthPayableBill_showList";
	}

	/****
	 * 应付明细补充导入
	 * 
	 * @param attachment
	 * @return
	 */
	public String addBcCorePayable() {
		errorMessage = corePayableServer.addBcCorePayable(attachment);
		return ERROR;
	}

	/****
	 * 供应商核对账单
	 * 
	 * @return
	 */
	public String gyshdzd() {
		boolean bool = false;
		if (ids != null && ids.length > 0) {
			bool = corePayableServer.gyshdzd(ids, qrTag);
			if (bool) {
				errorMessage = "确认完成!";
				url = "CorePayableAction!findCorePaybleList.action?pageStatus="
						+ pageStatus;
			}
		} else {
			errorMessage = "请至少选择一项!";
		}
		return ERROR;
	}

	/****
	 * 上传发票
	 * 
	 * @return
	 */
	public String uploadFapiao() {
		boolean bool = false;
		if (ids != null && ids.length > 0) {
			bool = corePayableServer.uploadFapiao(ids, corePayable
					.getFapiaoNum(), attachment, attachmentFileName);
		} else {
			bool = corePayableServer.uploadFapiao(corePayable, attachment,
					attachmentFileName);
		}
		if (bool) {
			errorMessage = "上传成功!";
			url = "CorePayableAction!findCorePaybleList.action?pageStatus="
					+ pageStatus;
		}
		return ERROR;
	}

	/****
	 * 发票复核
	 * 
	 * @return
	 */
	public String fpfh() {
		boolean bool = false;
		bool = corePayableServer.fpfh(ids, qrTag);
		if (bool) {
			errorMessage = "复核成功!";
			url = "CorePayableAction!findCorePaybleList.action?pageStatus="
					+ pageStatus;
		}
		return ERROR;
	}

	/****
	 * 查询应付汇总表
	 * 
	 * @return
	 */
	public String findSupplierCorePayableList() {
		if (supplierCorePayable != null) {
			ActionContext.getContext().getSession().put("supplierCorePayable",
					supplierCorePayable);
		} else {
			supplierCorePayable = (SupplierCorePayable) ActionContext
					.getContext().getSession().get("supplierCorePayable");
		}
		Object[] object = corePayableServer.findSupplierCorePayableList(
				supplierCorePayable, Integer.parseInt(cpage), pageSize,
				pageStatus);
		if (object != null && object.length > 0) {
			supplierCorePayablerList = (List<SupplierCorePayable>) object[0];
			if (supplierCorePayablerList != null
					&& supplierCorePayablerList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				if (pageStatus == null) {
					pageStatus = "";
				}
				this
						.setUrl("CorePayableAction!findSupplierCorePayableList.action?pageStatus="
								+ pageStatus);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		if ("shoukuan".equals(pageStatus)) {
			return "SupplierCorePayable_showList_sk";
		}
		return "SupplierCorePayable_showList";
	}

	/****
	 * 付款申请
	 * 
	 * @return
	 */
	public String shenqingfukuan() {
		boolean bool = false;
		bool = corePayableServer.shenqingfukuan(ids, qrTag);
		if (bool) {
			errorMessage = "申请成功!";
			url = "CorePayableAction!findCorePaybleList.action?pageStatus="
					+ pageStatus;
		}
		return ERROR;
	}

	/****
	 * 付款申请审批及调额
	 * 
	 * @return
	 */
	public String fukuanAudit() {
		boolean bool = false;
		bool = corePayableServer.fukuanAudit(corePayableList);
		if (bool) {
			errorMessage = "审批完成!";
			url = "CorePayableAction!findCorePaybleList.action?pageStatus="
					+ pageStatus;
		}
		return ERROR;
	}

	/***
	 * 补充生成已记账的应付的月度账单以及凭证
	 * 
	 * @return
	 */
	public String addOldCorePayable() {
		corePayableServer.addOldCorePayable();
		errorMessage = "补充计算完成";
		return ERROR;
	}

	/****
	 * 查询个人财务中心
	 * 
	 * @return
	 */
	public String findSupplierCorePayablePerson() {
		Object[] object = corePayableServer.findSupplierCorePayablePerson(
				supplierCorePayable, Integer.parseInt(cpage), pageSize,
				pageStatus);
		if (object != null && object.length > 0) {
			supplierCorePayable = (SupplierCorePayable) object[0];// 付款==我的负债
			supplierCorePayable2 = (SupplierCorePayable) object[1];// 收款==我的资产
			objs = (Object[]) object[2];// 借领还
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "SupplierCorePayable_showPerson";
	}
	public String exprotCorePayable(){
		try {
			corePayableServer.exprotCorePayable(corePayable,firstTime,endTime);
		} catch (Exception e) {
			errorMessage = e.getMessage();
			e.printStackTrace();
		}
		return "error";
	}
	public String addPayMonth(){
		errorMessage = corePayableServer.addPayMonth(payMonth, pmdList, firstTime, endTime);
		return "error";
	}
	public String printOutpm(){
		payMonth = corePayableServer.findPMbyId(id);
		if(payMonth!=null){
			pmdList=corePayableServer.findPMDByid(payMonth);
			return "printPayMonth";
		}
		errorMessage="数据信息错误";
		return "error";
	}
	public String pmmx(){
		payMonth = corePayableServer.findPMbyId(id);
		if(payMonth!=null){
			pmdList=corePayableServer.findPMDByid(payMonth);
			return "pmmx";
		}
		errorMessage="数据信息错误";
		return "error";
	}
	/*
	 * 删除 月结单
	 * */
	public String delcp(){
		errorMessage=corePayableServer.delcp(payMonth);
		return "error";
	}
	public String showpmList() {
        if (payMonth != null){
            ActionContext.getContext().getSession().put(
                    "PayMonth", payMonth);
        }
        else{
        	payMonth = (PayMonth) ActionContext.getContext()
                    .getSession().get("payMonth");
        }
       
        Object[] object = corePayableServer.findPayMonth(payMonth, Integer
                .parseInt(cpage), pageSize, tag);
        if (object != null && object.length > 0) {
        	payMonthList = (List<PayMonth>) object[0];
            if (payMonthList != null && payMonthList.size() > 0) {
                int count = (Integer) object[1];
                int pageCount = (count + pageSize - 1) / pageSize;
                this.setTotal(pageCount + "");
                this.setUrl("CorePayableAction!showpmList.action?tag="+tag);
            }
            errorMessage = null;
        } else
            errorMessage = "没有找到你要查询的内容,请检查后重试!";
        return "pm_List";
    }
	public CorePayableServer getCorePayableServer() {
		return corePayableServer;
	}

	public void setCorePayableServer(CorePayableServer corePayableServer) {
		this.corePayableServer = corePayableServer;
	}

	public CorePayable getCorePayable() {
		return corePayable;
	}

	public void setCorePayable(CorePayable corePayable) {
		this.corePayable = corePayable;
	}

	public List<CorePayable> getCorePayableList() {
		return corePayableList;
	}

	public void setCorePayableList(List<CorePayable> corePayableList) {
		this.corePayableList = corePayableList;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
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

	public File getAttachment() {
		return attachment;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

	public String getAttachmentFileName() {
		return attachmentFileName;
	}

	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}

	public String getAttachmentContentType() {
		return attachmentContentType;
	}

	public void setAttachmentContentType(String attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public String getQrTag() {
		return qrTag;
	}

	public void setQrTag(String qrTag) {
		this.qrTag = qrTag;
	}

	public SupplierCorePayable getSupplierCorePayable() {
		return supplierCorePayable;
	}

	public void setSupplierCorePayable(SupplierCorePayable supplierCorePayable) {
		this.supplierCorePayable = supplierCorePayable;
	}

	public List<SupplierCorePayable> getSupplierCorePayablerList() {
		return supplierCorePayablerList;
	}

	public void setSupplierCorePayablerList(
			List<SupplierCorePayable> supplierCorePayablerList) {
		this.supplierCorePayablerList = supplierCorePayablerList;
	}

	public MonthPayableBill getMonthPayableBill() {
		return monthPayableBill;
	}

	public void setMonthPayableBill(MonthPayableBill monthPayableBill) {
		this.monthPayableBill = monthPayableBill;
	}

	public List<MonthPayableBill> getMonthPayableBillList() {
		return monthPayableBillList;
	}

	public void setMonthPayableBillList(
			List<MonthPayableBill> monthPayableBillList) {
		this.monthPayableBillList = monthPayableBillList;
	}

	public CorePayableMonth getCorePayableMonth() {
		return corePayableMonth;
	}

	public void setCorePayableMonth(CorePayableMonth corePayableMonth) {
		this.corePayableMonth = corePayableMonth;
	}

	public List<CorePayableMonth> getCorePayableMonthList() {
		return corePayableMonthList;
	}

	public void setCorePayableMonthList(
			List<CorePayableMonth> corePayableMonthList) {
		this.corePayableMonthList = corePayableMonthList;
	}

	public SupplierCorePayable getSupplierCorePayable2() {
		return supplierCorePayable2;
	}

	public void setSupplierCorePayable2(SupplierCorePayable supplierCorePayable2) {
		this.supplierCorePayable2 = supplierCorePayable2;
	}

	public Object[] getObjs() {
		return objs;
	}

	public void setObjs(Object[] objs) {
		this.objs = objs;
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

	public PayMonth getPayMonth() {
		return payMonth;
	}

	public void setPayMonth(PayMonth payMonth) {
		this.payMonth = payMonth;
	}

	public List<PayMonthDetail> getPmdList() {
		return pmdList;
	}

	public void setPmdList(List<PayMonthDetail> pmdList) {
		this.pmdList = pmdList;
	}

	public PayMonthDetail getPayMonthDetail() {
		return payMonthDetail;
	}

	public void setPayMonthDetail(PayMonthDetail payMonthDetail) {
		this.payMonthDetail = payMonthDetail;
	}

	public List<PayMonth> getPayMonthList() {
		return payMonthList;
	}

	public void setPayMonthList(List<PayMonth> payMonthList) {
		this.payMonthList = payMonthList;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public int getMaxsize() {
		return maxsize;
	}

	public void setMaxsize(int maxsize) {
		this.maxsize = maxsize;
	}

	
	

}
