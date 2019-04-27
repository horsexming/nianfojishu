package com.task.action.caiwu.noncore;

import java.io.File;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.caiwu.noncore.NoncoreReceServer;
import com.task.entity.caiwu.noncore.FinancialReceipts;
import com.task.entity.caiwu.noncore.NonCoreReceivables;
import com.task.entity.caiwu.noncore.NonCoreReceivablesDetail;
import com.task.entity.menjin.InEmployeeCarInfor;
import com.task.util.MKUtil;
import com.task.util.Upload;
import com.task.util.Util;
/**
 * 非主营业务应收 Action 
 * @addTime 2017-05-04
 * @author licong
 *
 */
@SuppressWarnings("unchecked")
public class NoncoreReceAction extends ActionSupport {
	private NonCoreReceivables nonCoreReceivables;//非主营业务应收总表
	private FinancialReceipts financialReceipts;//财务应收明细收款信息
	private List<NonCoreReceivables> nonCoreReceivablesList;
	private List<FinancialReceipts> financialReceiptsList;
	private NonCoreReceivablesDetail nonCoreReceivablesDetail;//非主营业务应收总表
	private List<NonCoreReceivablesDetail> nonCoreReceivablesDetailList;//完成
	private List<NonCoreReceivablesDetail> nonCoreReceivablesDetailweiList;//未收
	private List<NonCoreReceivablesDetail> nonCoreReceivablesDetailweiqingList;//未收
	private List<NonCoreReceivablesDetail> nonCoreReceivablesDetailweiscList;//未上传
	private List<NonCoreReceivablesDetail> nonCoreReceivablesDetaildqrList;//待确认
	private NoncoreReceServer noncoreReceServer;
	private String tag;
	private String message;
	private List list;
	private List listType;//存放位置
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private Integer id;// 主键
	private String addUp;// 合计金额
	private String startDate;// 开始时间
	private String endDate;// 截止时间
	private String errorMessage;// 错误消息
	private String successMessage;// 错误消息
	
	// 上传文件对象
	private File fujian;// 合同
	// 上传文件名
	private String fujianFileName;
	// 上传文件类型
	private String fujianContentType;
	// 上传文件对象
	private File fujian1;// 附件
	// 上传文件名
	private String fujian1FileName;
	// 上传文件类型
	private String fujian1ContentType;
	
	public String toadd() {
		return "nonCoreReceivables_add";
	}
	
	public String text(){
		noncoreReceServer.Text();
		return "error";
	}
	
	public String jisuan(){
		noncoreReceServer.jisuanNCR();
		return "error";
	}
	
	/**
	 * 
	 * @return
	 */
	public String tobackFujian(){
		if (nonCoreReceivablesDetail != null) {
			nonCoreReceivablesDetail = noncoreReceServer.getNoncoreReceiveDetail(nonCoreReceivablesDetail.getId());
			if (nonCoreReceivablesDetail!=null)
				return "nonFujianseallogdetail";
		}
		errorMessage = "对象为空！";
		return "error";
	}
	
	public String backFujian(){
		String fu = "";
		if (fujian != null) {
			// 文件路径
			// 打开存放文件的位置
			String realFileName = "fujian2_" + nonCoreReceivablesDetail.getId();
			String realFilePath = "/upload/file/sealfj";
			String path = ServletActionContext.getServletContext()
					.getRealPath(realFilePath);
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();// 如果不存在文件夹就新建
			}
			Upload upload = new Upload();// 文件上传工具类 
			fu = upload.UploadFile(fujian, fujianFileName,
					null, realFilePath, null);
			nonCoreReceivablesDetail.setFujianQueren(realFilePath + "/"+fu);// 文件新名称
		}
		if (!"".equals(fu)&&!"error".equals(fu)&&!"上传文件失败!".equals(fu)) {
			errorMessage = noncoreReceServer.update2(nonCoreReceivablesDetail);
			if ("上传成功".equals(errorMessage))
				url = "NoncoreReceAction!showListDetailcaiwu.action?tag="+tag;
		}
		return "error";
	}
	
	/*
	 * 添加非主营业务应收明细
	 */
	public String toaddDetail() {
		nonCoreReceivables = noncoreReceServer.getNoncoreReveById(id);
		if(nonCoreReceivables==null){
			errorMessage = "信息为空！";
			return "error";
		}
		errorMessage = noncoreReceServer.toaddNon(id);
		if("true".equals(errorMessage)){
			return "nonCoreReceivablesDetail_add";
		}else {
			return "error";
		}
	}
	
	//前往印章申请页面
//	public String toaddSeal(){
//		errorMessage = noncoreReceServer.toaddNon();
//		if ("true".equals(errorMessage)) {
//			return "seallog_add";
//		}else if ("公章已被借出，请等待归还后再申请！".equals(errorMessage)) {
//			return "seallog_add_1";
//		}else {
//			return "error";
//		}
//	}
	
	// 分页显示
	// 显示查询内容
	public String showList() {
		if (nonCoreReceivables != null) {
			ActionContext.getContext().getSession().put("NonCoreReceivables",
					nonCoreReceivables);
		} else {
			nonCoreReceivables = (NonCoreReceivables) ActionContext
					.getContext().getSession().get("NonCoreReceivables");
		}
		Object[] object = noncoreReceServer.findNoncoreReceiveList(
				nonCoreReceivables, startDate, endDate,
				Integer.parseInt(cpage), pageSize, tag);
		if (object != null && object.length > 0) {
			nonCoreReceivablesList = (List<NonCoreReceivables>) object[0];
			if (nonCoreReceivablesList != null
					&& nonCoreReceivablesList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("NoncoreReceAction!showList.action?tag=" + tag);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "findAllNoncoreRece";
	}
	
	/**
	 * 非主营应收明细
	 * @return
	 */
	public String showListDetail() {
		if (nonCoreReceivablesDetail != null) {
			ActionContext.getContext().getSession().put("NonCoreReceivablesDetail",
					nonCoreReceivablesDetail);
		} else {
			nonCoreReceivablesDetail = (NonCoreReceivablesDetail) ActionContext
			.getContext().getSession().get("NonCoreReceivablesDetail");
		}
		Object[] object = noncoreReceServer.findNonCoreReceivablesDetail(
				nonCoreReceivablesDetail,id, Integer.parseInt(cpage), pageSize, tag);
		if (object != null && object.length > 0) {
			nonCoreReceivables = noncoreReceServer.getNoncoreReveById(id);
			nonCoreReceivablesDetailList = (List<NonCoreReceivablesDetail>) object[0];
			if (nonCoreReceivablesDetailList != null
					&& nonCoreReceivablesDetailList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("NoncoreReceAction!showListDetail.action?tag=" + tag +"&id="+id);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "nonCoreReceivablesDetail_show";
	}

	public String showListDetailcaiwu() {
		if (nonCoreReceivablesDetail != null) {
			ActionContext.getContext().getSession().put("NonCoreReceivablesDetailc",
					nonCoreReceivablesDetail);
		} else {
			nonCoreReceivablesDetail = (NonCoreReceivablesDetail) ActionContext
			.getContext().getSession().get("NonCoreReceivablesDetailc");
		}
		nonCoreReceivablesDetailweiList = noncoreReceServer.findNonCoreReceivablesDetailCai("未收");
		nonCoreReceivablesDetailweiqingList = noncoreReceServer.findNonCoreReceivablesDetailCai("未收清");
		nonCoreReceivablesDetailweiscList = noncoreReceServer.findNonCoreReceivablesDetailwsc();
		Object[] object = noncoreReceServer.findNonCoreReceivablesDetail(
				nonCoreReceivablesDetail,null, Integer.parseInt(cpage), pageSize, tag);
		if (object != null && object.length > 0) {
			nonCoreReceivablesDetailList = (List<NonCoreReceivablesDetail>) object[0];
			if (nonCoreReceivablesDetailList != null
					&& nonCoreReceivablesDetailList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("NoncoreReceAction!showListDetailcaiwu.action?tag=" + tag);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "nonCoreReceivablesDetail_show";
	}
	public String showListDetailDqr() {
		if (nonCoreReceivablesDetail != null) {
			ActionContext.getContext().getSession().put("NonCoreReceivablesDetaildqr",
					nonCoreReceivablesDetail);
		} else {
			nonCoreReceivablesDetail = (NonCoreReceivablesDetail) ActionContext
			.getContext().getSession().get("NonCoreReceivablesDetaildqr");
		}
//		nonCoreReceivablesDetaildqrList = noncoreReceServer.findNonCoreReceivablesDetailDqr();
		Object[] object = noncoreReceServer.findNonCoreReceivablesDetailDqr(
				nonCoreReceivablesDetail, Integer.parseInt(cpage), pageSize, tag);
		if (object != null && object.length > 0) {
			nonCoreReceivablesDetaildqrList = (List<NonCoreReceivablesDetail>) object[0];
			if (nonCoreReceivablesDetaildqrList != null && nonCoreReceivablesDetaildqrList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("NoncoreReceAction!showListDetailDqr.action?tag=" + tag);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "nonCoreReceivablesDetaildqr_show";
	}
	
	/**
	 * 财务收款信息
	 * @return
	 */
	public String showListDetailcaiwuShou() {
		if (financialReceipts != null) {
			ActionContext.getContext().getSession().put("FinancialReceipts",
					financialReceipts);
		} else {
			financialReceipts = (FinancialReceipts) ActionContext
			.getContext().getSession().get("FinancialReceipts");
		}
		Object[] object = noncoreReceServer.findFinancialReceipts(financialReceipts, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			financialReceiptsList = (List<FinancialReceipts>) object[0];
			if (financialReceiptsList != null
					&& financialReceiptsList.size() > 0) {
				addUp = (String) object[2];
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("NoncoreReceAction!showListDetailcaiwuShou.action?tag=" + tag);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "financialReceipts_show";
	}
	
	/**
	 * 确认已收金额
	 * @return
	 */
	public String querenDetail() {
		if(nonCoreReceivablesDetail!=null&&nonCoreReceivablesDetail.getRealfuJine()!=null){
			errorMessage = noncoreReceServer.updateReceiveDetailCai(nonCoreReceivablesDetail);
			if("确认成功！".equals(errorMessage))
				url = "NoncoreReceAction!showListDetailcaiwu.action?tag=" + tag;
		}
		return "error";
	}
	
	/**
	 * 确认应收金额
	 * @return
	 */
	public String querenDetailYingshou() {
		if(nonCoreReceivablesDetail!=null&&nonCoreReceivablesDetail.getYingfuJine()!=null){
			errorMessage = noncoreReceServer.updateReceiveDetailqr(nonCoreReceivablesDetail);
			if("确认成功！".equals(errorMessage))
				url = "NoncoreReceAction!showListDetailDqr.action?tag=" + tag;
		}
		return "error";
	}
	//取消
	public String querenDetailqux() {
		if(nonCoreReceivablesDetail!=null){
			errorMessage = noncoreReceServer.updateReceiveDetailqx(nonCoreReceivablesDetail);
			if("取消成功！".equals(errorMessage))
				url = "NoncoreReceAction!showListDetailDqr.action?tag=" + tag;
		}
		return "error";
	}
	
	// 添加方法
	public String addDetail() {
		if (nonCoreReceivables != null&&nonCoreReceivablesDetail!=null) {
			if (fujian1 != null) {
				// 文件路径
				String realFileName = Util.getDateTime("yyyyMMddHHmmss");
				// 打开存放文件的位置
				String realFilePath = "/upload/file/shuiDian";//水电附件
				String path = ServletActionContext.getServletContext()
						.getRealPath(realFilePath);
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();// 如果不存在文件夹就新建
				}
				Upload upload = new Upload();// 文件上传工具类
				realFileName = upload.UploadFile(fujian1, fujian1FileName,
						null, realFilePath, null);
				nonCoreReceivablesDetail.setPhotoPath(
//						realFilePath + "/" + 
						realFileName);// 文件新名称

			}
			errorMessage = noncoreReceServer.saveReceveDetail(nonCoreReceivables,nonCoreReceivablesDetail);
			if ("添加成功！".equals(errorMessage))
				url = "NoncoreReceAction!showList.action?tag="+tag;
			return "error";
		}
		errorMessage = "数据为空，添加失败！";
		return "error";
	}
	
	// 添加方法
	public String add() {
		if (nonCoreReceivables != null) {
			if (fujian != null) {
				// 文件路径
				String realFileName = Util.getDateTime("yyyyMMddHHmmss");
				// 打开存放文件的位置
				String realFilePath = "/upload/file/feiZY";
				String path = ServletActionContext.getServletContext()
						.getRealPath(realFilePath);
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();// 如果不存在文件夹就新建
				}
				Upload upload = new Upload();// 文件上传工具类
				realFileName = upload.UploadFile(fujian, fujianFileName,
						null, realFilePath, null);
				nonCoreReceivables.setHetongfujian(
//						realFilePath + "/" + 
						realFileName);// 文件新名称

			}
			errorMessage = noncoreReceServer.saveNoncoreReceve(nonCoreReceivables);
			if ("添加成功！".equals(errorMessage))
				url = "NoncoreReceAction!showList.action?tag="+tag;
			return "error";
		}
		errorMessage = "数据为空，添加失败！";
		return "error";
	}
	
	//查询所有总表给手机端下拉信息
	public void findnonCoreRece(){
		MKUtil.writeJSON(true,"",noncoreReceServer.findNonCoreReceivables(tag));
	}

	// 跳转到修改页面的方法
	public String toupdate() {
		if (nonCoreReceivables!=null && nonCoreReceivables.getId() != null && nonCoreReceivables.getId() > 0
				&& nonCoreReceivables != null) {
			nonCoreReceivables = noncoreReceServer.getNoncoreReveById(nonCoreReceivables.getId());
			if (nonCoreReceivables != null)
				return "nonCoreReceivables_update";
		}
		errorMessage = "数据为空!请检查";
		return "error";
		
	}
	
	// 跳转到修改页面的方法
	public String toselect() {
		if (nonCoreReceivables!=null && nonCoreReceivables.getId() != null && nonCoreReceivables.getId() > 0) {
			nonCoreReceivables = noncoreReceServer.getNoncoreReveById(nonCoreReceivables.getId());
			if (nonCoreReceivables != null)
				return "nonCoreReceivables_select";
		}
		errorMessage = "数据为空!请检查";
		return "error";
		
	}
	
	// 跳转到修改页面的方法
	public String toselectDe() {
		if (nonCoreReceivablesDetail!=null && nonCoreReceivablesDetail.getId() != null && nonCoreReceivablesDetail.getId() > 0) {
			nonCoreReceivablesDetail = noncoreReceServer.getNoncoreReceiveDetail(nonCoreReceivablesDetail.getId());
			if (nonCoreReceivablesDetail != null)
				return "nonCoreReceivables_selectDe";
		}
		errorMessage = "数据为空!请检查";
		return "error";
		
	}
	
	//修改方法
	public String update() {
		if(nonCoreReceivables!=null){
			if (fujian != null) {
				// 文件路径
				String realFileName = Util.getDateTime("yyyyMMddHHmmss");
				// 打开存放文件的位置
				String realFilePath = "/upload/file/feiZY";
				String path = ServletActionContext.getServletContext()
						.getRealPath(realFilePath);
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();// 如果不存在文件夹就新建
				}
				Upload upload = new Upload();// 文件上传工具类

				// 删除原文件
				NonCoreReceivables coreReceivables1 = noncoreReceServer
						.getNoncoreReveById(nonCoreReceivables.getId());
				if (coreReceivables1.getHetongfujian() != null) {
					File oldFile = new File(ServletActionContext
							.getServletContext().getRealPath("")
							+ "/upload/file/feiZY/" + coreReceivables1.getHetongfujian());
					if (oldFile.exists()) {
						oldFile.delete();
					}
				}
				realFileName = upload.UploadFile(fujian, fujianFileName,
						null, realFilePath, null);
				nonCoreReceivables.setHetongfujian(
						realFileName);// 文件新名称

			}
			errorMessage = noncoreReceServer.updateNoncoreRecevc(nonCoreReceivables);
			if ("修改成功！".equals(errorMessage))
				url = "NoncoreReceAction!showList.action?tag="+tag;
		}
		return "error";
	}

	// 删除方法
	public String delete() {
		if (id != null && id > 0) {
			errorMessage = noncoreReceServer.deleteNoncorereceiveById(id);
			if ("删除成功！".equals(errorMessage))
				url = "NoncoreReceAction!showList.action?tag="+tag;
			return "error";
		}
		errorMessage = "不存在该对象！删除失败！";
		return "error";
	}
	// 删除方法
	public String deleteDetail() {
		if (nonCoreReceivables!=null && nonCoreReceivables.getId()!=null && id != null && id > 0) {
			errorMessage = noncoreReceServer.deleteNoncoreReceiveDetailById(id);
			if ("删除成功！".equals(errorMessage))
				url = "NoncoreReceAction!showListDetail.action?id="+nonCoreReceivables.getId()+"&tag="+tag;
			return "error";
		}
		errorMessage = "不存在该对象！删除失败！";
		return "error";
	}

	public NonCoreReceivables getNonCoreReceivables() {
		return nonCoreReceivables;
	}

	public void setNonCoreReceivables(NonCoreReceivables nonCoreReceivables) {
		this.nonCoreReceivables = nonCoreReceivables;
	}

	public NonCoreReceivablesDetail getNonCoreReceivablesDetail() {
		return nonCoreReceivablesDetail;
	}

	public void setNonCoreReceivablesDetail(
			NonCoreReceivablesDetail nonCoreReceivablesDetail) {
		this.nonCoreReceivablesDetail = nonCoreReceivablesDetail;
	}

	public NoncoreReceServer getNoncoreReceServer() {
		return noncoreReceServer;
	}

	public void setNoncoreReceServer(NoncoreReceServer noncoreReceServer) {
		this.noncoreReceServer = noncoreReceServer;
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

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List getListType() {
		return listType;
	}

	public void setListType(List listType) {
		this.listType = listType;
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

	public List<NonCoreReceivables> getNonCoreReceivablesList() {
		return nonCoreReceivablesList;
	}

	public void setNonCoreReceivablesList(
			List<NonCoreReceivables> nonCoreReceivablesList) {
		this.nonCoreReceivablesList = nonCoreReceivablesList;
	}

	public List<NonCoreReceivablesDetail> getNonCoreReceivablesDetailList() {
		return nonCoreReceivablesDetailList;
	}

	public void setNonCoreReceivablesDetailList(
			List<NonCoreReceivablesDetail> nonCoreReceivablesDetailList) {
		this.nonCoreReceivablesDetailList = nonCoreReceivablesDetailList;
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
	public File getFujian1() {
		return fujian1;
	}
	public void setFujian1(File fujian1) {
		this.fujian1 = fujian1;
	}
	public String getFujian1FileName() {
		return fujian1FileName;
	}
	public void setFujian1FileName(String fujian1FileName) {
		this.fujian1FileName = fujian1FileName;
	}
	public String getFujian1ContentType() {
		return fujian1ContentType;
	}
	public void setFujian1ContentType(String fujian1ContentType) {
		this.fujian1ContentType = fujian1ContentType;
	}
	public List<NonCoreReceivablesDetail> getNonCoreReceivablesDetailweiList() {
		return nonCoreReceivablesDetailweiList;
	}
	public void setNonCoreReceivablesDetailweiList(
			List<NonCoreReceivablesDetail> nonCoreReceivablesDetailweiList) {
		this.nonCoreReceivablesDetailweiList = nonCoreReceivablesDetailweiList;
	}
	public List<NonCoreReceivablesDetail> getNonCoreReceivablesDetailweiqingList() {
		return nonCoreReceivablesDetailweiqingList;
	}
	public void setNonCoreReceivablesDetailweiqingList(
			List<NonCoreReceivablesDetail> nonCoreReceivablesDetailweiqingList) {
		this.nonCoreReceivablesDetailweiqingList = nonCoreReceivablesDetailweiqingList;
	}
	public FinancialReceipts getFinancialReceipts() {
		return financialReceipts;
	}
	public void setFinancialReceipts(FinancialReceipts financialReceipts) {
		this.financialReceipts = financialReceipts;
	}
	public List<FinancialReceipts> getFinancialReceiptsList() {
		return financialReceiptsList;
	}
	public void setFinancialReceiptsList(
			List<FinancialReceipts> financialReceiptsList) {
		this.financialReceiptsList = financialReceiptsList;
	}
	public String getAddUp() {
		return addUp;
	}
	public void setAddUp(String addUp) {
		this.addUp = addUp;
	}

	public List<NonCoreReceivablesDetail> getNonCoreReceivablesDetailweiscList() {
		return nonCoreReceivablesDetailweiscList;
	}

	public void setNonCoreReceivablesDetailweiscList(
			List<NonCoreReceivablesDetail> nonCoreReceivablesDetailweiscList) {
		this.nonCoreReceivablesDetailweiscList = nonCoreReceivablesDetailweiscList;
	}

	public List<NonCoreReceivablesDetail> getNonCoreReceivablesDetaildqrList() {
		return nonCoreReceivablesDetaildqrList;
	}

	public void setNonCoreReceivablesDetaildqrList(
			List<NonCoreReceivablesDetail> nonCoreReceivablesDetaildqrList) {
		this.nonCoreReceivablesDetaildqrList = nonCoreReceivablesDetaildqrList;
	}
	
}
