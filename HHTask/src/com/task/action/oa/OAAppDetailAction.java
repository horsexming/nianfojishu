 package com.task.action.oa;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.oa.OAAppDetailServer;
import com.task.Server.sys.CircuitRunServer;

import com.task.entity.OaAppDetail;
import com.task.entity.OaPrepareApply;
import com.task.entity.Users;
import com.task.entity.WarehouseNumber;
import com.task.entity.system.CircuitRun;
import com.task.entity.system.ExecutionNode;
import com.task.util.MKUtil;
import com.task.entity.GoodsStore;

/**
 * OA采购明细action
 * 
 * @author jhh
 * 
 */
@SuppressWarnings({ "unchecked", "serial" })
public class OAAppDetailAction extends ActionSupport {
	private OAAppDetailServer oaappDetailServer;
	private OaAppDetail oadetail;
	private List<OaAppDetail> oaDList;//oa采购
	private WarehouseNumber warehouseNumber;//库位对象
	private List<WarehouseNumber> wnList;
	private OaPrepareApply opApply;// 月度部门申请信息
	private List list;
	private List listPrint;
	private String tag;//采购物品类型（gz）
	private String message;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private Integer id;// 主键
	private String startDate;// 开始时间
	private String endDate;// 截止时间
	private String baoxiaoStyle;// 按报销类别汇总
	private String errorMessage;// 报错信息
	private String planMonth;// 预算月份
	private String powerTag;// 权限标识
	private File uploadDetail;// 上传申购文件
	private String uploadDetailContentType;// 文件类型
	private String uploadDetailFileName;// 文件名称
	private Integer[] detailSelect;// 选择补打数组,审批数组
	private Float money;// 预算金额
	private Users users;
	private boolean bool;
	private String opinion;// 审批意见
	private CircuitRun circuitRun;// 对象
	private CircuitRunServer circuitRunServer;// Server层
	private String successMessage;
	private String code;
	private String cus_id;
	private Integer id1;
	private String bacode;// 条码
	private String mxId;// OA采购明细ID
	private File oadetailFile;
	private String storageWay;	//入库方式
	private GoodsStore gStore;//入库表
	/***
	 * 更据无凭类别查选物品名称
	 */
	public void getNameBymingcheng() {
		if (tag != null) {
			// procardTemplateList = procardTemplateServer.getAllNames(markId);
			list = oaappDetailServer.getNameBymingcheng(tag, powerTag);
			try {
				MKUtil.writeJSON(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * OA申报，判断有无预算记录
	 * 
	 * @return
	 */
	public String preSaveOADetail() {
//		if (oaappDetailServer.preSaveDetail()) {
			if (this.tag != null && !"".equals(tag)) {
				return "preSaveOADetail1";//oaAppDetail_save1
			} else {
				return "preSaveOADetail";
			}
//		} else {
//			errorMessage = "没有预算申报信息或申报未通过，无法进行申购，请确认后处理！";
//			return ERROR;
//		}
	}

	/**
	 * 添加物品名称和规格
	 * 
	 * @return
	 */
	public String saveKemu() {
		if (oaappDetailServer.saveKemu(oadetail)) {
			message = "添加成功！";
			return "saveKemuOK";
		}
		return ERROR;
	}

	/**
	 * 添加申报记录
	 * 
	 * @return
	 */
	public String saveOADetail() {
		String flag  = oaappDetailServer.saveOADetail(oadetail);
		if ("true".equals(flag) || flag=="true") {
			return "saveDetailOK";
		}else{
			setMessage(flag);
			return "preSaveOADetail";
		}
	}

	// 批量上传保存
	public String saveLotUpload() {
		if (null != oadetailFile && null != oadetail) {
			//message = oaappDetailServer.saveLotUpload(uploadDetail, oadetail);
			message = oaappDetailServer.saveBatchOADetail(oadetail, oadetailFile);
			return "preSaveOADetail1";
		}
		message = "未知异常，请联系管理员！";
		return "saveDetailFaile";
	}

	/**
	 * 查询管理报销单
	 * 
	 * @return
	 */
	public String findOADetail() {
		this.pageSize = 15;
		this.setUrl("oaAppDetailAction!findOADetail.action?powerTag="
				+ this.powerTag);
		HttpServletRequest request = ServletActionContext.getRequest();
		// BaoxiaoDan baoxiao=new BaoxiaoDan();
		if (null != oadetail) {
			request.getSession().setAttribute("oadetail", oadetail);
		} else {
			oadetail = (OaAppDetail) request.getSession().getAttribute("oadetail");
		}
		if("oaAppDetail_selectDetail".equals(tag)){
			if(null==oadetail ){
				oadetail = new OaAppDetail();
			}
			if(null==oadetail.getDetailStatus() || "".equals(oadetail.getDetailStatus())){
				oadetail.setDetailStatus("待入库");
			}
		}
		if(null!=startDate){
			request.getSession().setAttribute("startDate", startDate);
		}else{
			startDate = (String) request.getSession().getAttribute("startDate");
		}
		if(null!=startDate)
			request.getSession().setAttribute("endDate", endDate);
		else
			endDate = (String) request.getSession().getAttribute("endDate");
		
		Object[] obj = oaappDetailServer.findOADetail(oadetail, startDate,
				endDate, Integer.parseInt(cpage), pageSize, this.powerTag);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		if("oaAppDetail_selectDetail".equals(tag)){
			this.setUrl("oaAppDetailAction!findOADetail.action?powerTag="
					+ this.powerTag+"&tag="+this.tag);
			return "oaAppDetail_selectDetail"; //入库操作
		}
		
		return "findOADetailOK";//oaAppDetail_list.jsp
	}
	
	/**
	 * 查询待存柜的信息
	 * 
	 * @return
	 */
	public String findOADetail_1() {
		if (oadetail != null) {
			ActionContext.getContext().getSession().put("oadetails", oadetail);
		} else {
			oadetail = (OaAppDetail) ActionContext
					.getContext().getSession().get("oadetails");
		}
		Object[] object = oaappDetailServer.findOADetail_1(oadetail, Integer.parseInt(cpage), pageSize, powerTag,tag);
		if (object != null && object.length > 0) {
			list = (List<OaAppDetail>) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("oaAppDetailAction!findOADetail_1.action?powerTag="+powerTag+"&tag="+tag);
			}
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		if ("gz".equals(tag)) return "oaAppDetail_list_gz";
		return "oaAppDetail_list_daohuo";
	}
	
	/****
	 * 存柜扫描库位码存柜=》跳转到确认数量页面
	 * 
	 * @return
	 */
	public String findDeliveryRuGuiBacode() {
		try {
			if(bacode!=null&&mxId!=null){
				Object[] object = oaappDetailServer.upfindOACaiGouRuGuiBacode(bacode, mxId, tag);
				if (object != null && object.length > 0) {
					errorMessage = (String) object[0];
					if("true".equals(errorMessage)) {
						oaDList = (List) object[1];
						warehouseNumber = (WarehouseNumber) object[2];
						if (oaDList!=null&&oaDList.size()>0) errorMessage = null;
						return "oaAppDetail_GzQueRenList";
					}else return ERROR;
				}
			}
			return "error";
		} catch (Exception e) {
			errorMessage=e.getMessage();
			return ERROR;
		}
	}
	

	/****
	 * 入柜=》确认数量
	 * 
	 * @return
	 */
	public String updateGZToRG() {
		if (warehouseNumber==null) return ERROR;
		warehouseNumber = oaappDetailServer.byIdWN(warehouseNumber.getId());
		if (warehouseNumber!=null) {
			errorMessage = oaappDetailServer.updateGZToRG(oaDList,warehouseNumber);
			if ("true".equals(errorMessage)) {
				errorMessage = "确认完成!";
				url = "oaAppDetailAction!findOADetail_1.action?powerTag=all&tag=gz";
			}
		}
		return ERROR;
	}

	/**
	 * 选择打印
	 * 
	 * @return
	 */
	public String findPrintList() {
		// tag(print:直接打印，select：选择打印)
		if (null != id) {
			list = oaappDetailServer.findPrintList(id, tag);
			opApply = oaappDetailServer.getPreById(id);
			if ("print".equals(tag)) {
				return "printPre";
			} else if ("select".equals(tag)) {
				return "selectPreDetail";
			}
		}
		return ERROR;
	}

	/**
	 * 打印更改状态和创建采购form
	 */
	public String updatePrintStatus() {
		String message = oaappDetailServer.updatePrintStatus(id, tag);
		MKUtil.writeJSON(message);
		return null;
	}

	/**
	 * 根据ID获取单个报销单
	 * 
	 * @return
	 */
	public String getOADetailById() {
		oadetail = new OaAppDetail();
		oadetail = oaappDetailServer.getOADetaailById(id);
		if ("update".equals(this.tag)) {
			return "getOADetailOK";
		} else if ("buy".equals(this.tag)) {// 采购修改名称规格
			return "getOADetailBuy";
		}
		return ERROR;
	}

	/**
	 * 修改申请明细
	 * 
	 * @return
	 */
	public String updateOADetail() {
		if (oaappDetailServer.updateOADetail(oadetail, "update")) {
			if("bijiaUpdate".equals(tag)){
				message = "success";
				return "getOADetailBuy";
			}
			return "updateOADetailOK";
		}
		return ERROR;
	}

	/**
	 * 根据ID删除申请明细
	 * 
	 * @return
	 */
	public String deleteOADetailById() {
		oadetail = new OaAppDetail();
		oadetail = oaappDetailServer.getOADetaailById(id);
		if (oaappDetailServer.deleteOADetailById(oadetail)) {
			return "deleteOADOK";
		}
		return ERROR;
	}

	/**
	 * 查找检索类型字段，做下拉
	 * 
	 * @return
	 */
	public String findOASelect() {
		String message = oaappDetailServer.findOASelect(tag, powerTag);
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

	/**
	 * 查找下拉预算月份
	 */
	public String findSelectMon() {
		String message = oaappDetailServer.findSelectMon(tag);
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

	/** 根据类别查找物品名称 */
	public String findchildClass() {
		try {
			tag = java.net.URLDecoder.decode(java.net.URLDecoder.decode(tag,
					"UTF-8"), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String message = oaappDetailServer.findchildClass(tag, powerTag);
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

	/** 根据物品的类别和名称查找规格 */
	public String findFormat() {
		try {
			tag = java.net.URLDecoder.decode(java.net.URLDecoder.decode(tag,
					"UTF-8"), "UTF-8");
			powerTag = java.net.URLDecoder.decode(java.net.URLDecoder.decode(
					powerTag, "UTF-8"), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String message = oaappDetailServer.findFormat(tag, powerTag);
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

	/** 根据物品的类别和名称查找规格 */
	public String findFormatByProject() {
		try {
			// 项目编号
			tag = java.net.URLDecoder.decode(java.net.URLDecoder.decode(tag,
					"UTF-8"), "UTF-8");
			// 物品名称
			powerTag = java.net.URLDecoder.decode(java.net.URLDecoder.decode(
					powerTag, "UTF-8"), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String message = oaappDetailServer.findFormatByProject(tag, powerTag);
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

	/** 根据部门查找科目 */
	public String findchildSubjects() {
		List newList = new ArrayList();
		if ("planMonth".equals(tag)) {
			newList = oaappDetailServer.findchildSubjects(tag, planMonth);
		} else if ("projectNO".equals(tag)) {
			newList = oaappDetailServer.findchildSubjects(tag, planMonth);
		}
		MKUtil.writeJSON(newList);
		return null;
	}

	public String compareBudgetCount() {
		String message = oaappDetailServer.compareBudgetCount(id, money, id1);
		MKUtil.writeJSON(message);
		return null;
	}

	/**
	 * =====================================操作预申请表
	 */
	public String findPreApp() {
		this.pageSize = 15;
		// powerTag="buy" 采购 powerTag="self" 个人 powerTag="manager" 管理
		this.setUrl("oaAppDetailAction!findPreApp.action?tag=" + this.tag
				+ "&powerTag=" + this.powerTag);
		HttpServletRequest request = ServletActionContext.getRequest();
		// BaoxiaoDan baoxiao=new BaoxiaoDan();
		if (null != opApply) {
			request.getSession().setAttribute("opApply", opApply);
		} else {
			opApply = (OaPrepareApply) request.getSession().getAttribute(
					"opApply");
		}
		Object[] obj = oaappDetailServer.findPreApp(opApply, startDate,
				endDate, Integer.parseInt(cpage), pageSize, this.tag);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		listPrint = (List) obj[2];
		return "findOAPreAppOK";
	}

	// 查看预申请记录
	public String findPreDetailList() {
		this.pageSize = 15;
		// powerTag="buy" 采购 powerTag="self" 个人 powerTag="manager" 管理
		this.setUrl("oaAppDetailAction!findPreDetailList.action?tag=" + tag
				+ "&powerTag=" + powerTag);
		HttpServletRequest request = ServletActionContext.getRequest();
		// BaoxiaoDan baoxiao=new BaoxiaoDan();
		if (null != id) {
			request.getSession().setAttribute("id", id);
		} else {
			id = (Integer) request.getSession().getAttribute("id");
		}
		if (null != oadetail) {
			request.getSession().setAttribute("oadetail", oadetail);
		} else {
			oadetail = (OaAppDetail) request.getSession().getAttribute(
					"oadetail");
		}

		Object[] obj = oaappDetailServer.findPreAppDetail(oadetail, id,
				startDate, endDate, Integer.parseInt(cpage), pageSize, tag,
				powerTag);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "findPreDetailListOK";
	}

	// 选择打印(补打)
	public String findSelectDetail() {
		if (null != detailSelect && detailSelect.length > 0) {
			opApply = oaappDetailServer.getPreById(id);
			list = oaappDetailServer.findSelectedDetail(detailSelect, tag);
			return "printPre";
		}
		return ERROR;
	}

	// 审批查询
	public String findExamList() {
		Object[] obj = oaappDetailServer.findExamList(Integer.parseInt(cpage),
				pageSize);
		if (obj != null && obj.length > 1) {
			list = (List) obj[1];
			int count = (Integer) obj[0];
			this.setUrl("oaAppDetailAction!findExamList.action");
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
		}
		return "findExamListOK";
	}

	// 批量审批
	public String updateExamDetail() {
		try {
			if (oaappDetailServer.updateExamOADetail(detailSelect, tag)) {
				return "updateExamDetailOK";
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "审批失败!请联系数据有效性!";
		}
		return "";
	}

	// 查看审批历史记录
	public String findExamHistoryList() {
		this.pageSize = 15;
		this.setUrl("oaAppDetailAction!findExamHistoryList.action");
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != oadetail) {
			request.getSession().setAttribute("oadetail", oadetail);
		} else {
			oadetail = (OaAppDetail) request.getSession().getAttribute(
					"oadetail");
		}
		Object[] obj = oaappDetailServer.findExamHistoryList(oadetail,
				startDate, endDate, Integer.parseInt(cpage), pageSize, tag);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "findExamHistoryListOK";
	}

	/**
	 * 导出明细数据
	 */
	public String exportDetailEXCEL() {
		// baoXiaoDanServer.exportDetailExcel(baoxiaodan, detail,
		// startDate,endDate, tag);
		return null;
	}

	/*
	 * 
	 * 查询当前用户未审批和审批历史的接口
	 */
	public void findNoApproval() {
		list = oaappDetailServer.findNoApproval(users, bool);
		// 作为json输出时,要清空对象属性关系
		List<CircuitRun> newList = new ArrayList<CircuitRun>();
		for (int i = 0; i < list.size(); i++) {
			CircuitRun circuitRun = (CircuitRun) list.get(i);
			circuitRun.setExNodeSet(null);
			newList.add(circuitRun);
		}
		MKUtil.writeJSON(true, "", newList);
	}

	/*
	 * 
	 * 审批接口（通过、驳回）
	 */
	public void updateApproval() {
		try {
			if (!"".equals(tag) && detailSelect != null && users != null) {
				if (oaappDetailServer.updateExamOADetail1(detailSelect, tag,
						opinion, users)) {
					if (bool = true) {
						MKUtil.writeJSON(bool, "审批通过!", "");
					} else {
						MKUtil.writeJSON(bool, "审批已打回!", "");
					}
				}
			} else {
				errorMessage = "审批失败!请联系数据有效性!";
			}

		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "审批失败!请联系数据有效性!";
		}
		MKUtil.writeJSON(false, errorMessage, "");
	}

	/***
	 * 审核页面接口
	 * 
	 * @return
	 */
	public void findApprovalPage() {
		if (id != null) {
			circuitRun = circuitRunServer.findCircuitRunById(id);
			list = circuitRunServer.findAllExNodeByEpId(id);
			List<ExecutionNode> newList = new ArrayList<ExecutionNode>();
			for (int i = 0; i < list.size(); i++) {
				ExecutionNode executionNode = (ExecutionNode) list.get(i);
				executionNode.setCircuitRun(null);
				newList.add(executionNode);
			}
			circuitRun.setExNodeSet(null);
			MKUtil.writeJSON(true, "", new Object[] { circuitRun, list });
		} else {
			MKUtil.writeJSON(false, "审核不存在!", null);
		}

	}

	/*
	 * 审批明细
	 */
	public void findApprovalDetail() {
		successMessage = circuitRunServer.findEntityByCcId(id);// 审批明细
		if (!"".equals(successMessage)) {
			MKUtil.writeJSON(true, "", successMessage);
		} else {
			MKUtil.writeJSON(false, "检查数据的有效性!", "");
		}

	}

	public String toaddWarehouseApp(){
		oadetail = oaappDetailServer.getOADetaailById(id);
		oadetail.setClassNames(oadetail.getClass().getSimpleName());//类名称
		wnList = oaappDetailServer.findAllWNList_1();
		if (oadetail!=null) return "warehouseApplication_add";
		return "error";
	}
	
	// 获取验证码
	public void send() {
		Random ran = new Random();
		String number = "" + ran.nextInt(10) + ran.nextInt(10)
				+ ran.nextInt(10) + ran.nextInt(10) + ran.nextInt(10)
				+ ran.nextInt(10);
		boolean b = this.oaappDetailServer.send(number, code, cus_id);
		if (b) {
			MKUtil.writeJSON(true, "获取成功", number);
		} else {
			MKUtil.writeJSON(false, "获取失败", null);
		}
	}
	
	//导入申购物品名称
	public String Addoadetail(){
		message =	oaappDetailServer.Addoadetail(oadetailFile);
		return "saveKemuOK";//oaAppDetail_saveKemu.jsp
	}
	
	public void scfuliao (){
	String msg=	oaappDetailServer.scfuliaoOaApply();
	}
	
	
	// 查询进入库详细
	public String getOaAppDetail() {
		if(oadetail !=null && oadetail.getId()!=null){
			oadetail = oaappDetailServer.getOADetaailById(oadetail.getId());
			//扫描条码入库
			if("scanning".equals(storageWay)){
				
			//批量入库
			}else if("select".equals(storageWay)){
				return "storage_warehousing";
			//单条入库
			}else if("single".equals(storageWay)){
				//查询最近一条入库历史
				gStore = oaappDetailServer.getRecentlyGoodsStore(oadetail.getId());
				return "storage_warehousing";
			}
		}
		return null;
	}
	
	
	public void settingOaUploadTemplate(){
		String msg = oaappDetailServer.settingOaUploadTemplate();
		MKUtil.writeJSON(msg);
	}

	public OAAppDetailServer getOaappDetailServer() {
		return oaappDetailServer;
	}

	public void setOaappDetailServer(OAAppDetailServer oaappDetailServer) {
		this.oaappDetailServer = oaappDetailServer;
	}

	public OaAppDetail getOadetail() {
		return oadetail;
	}

	public void setOadetail(OaAppDetail oadetail) {
		this.oadetail = oadetail;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
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

	public OaPrepareApply getOpApply() {
		return opApply;
	}

	public void setOpApply(OaPrepareApply opApply) {
		this.opApply = opApply;
	}

	public String getPowerTag() {
		return powerTag;
	}

	public void setPowerTag(String powerTag) {
		this.powerTag = powerTag;
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

	public String getBaoxiaoStyle() {
		return baoxiaoStyle;
	}

	public void setBaoxiaoStyle(String baoxiaoStyle) {
		this.baoxiaoStyle = baoxiaoStyle;
	}

	public String getPlanMonth() {
		return planMonth;
	}

	public void setPlanMonth(String planMonth) {
		this.planMonth = planMonth;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public File getUploadDetail() {
		return uploadDetail;
	}

	public void setUploadDetail(File uploadDetail) {
		this.uploadDetail = uploadDetail;
	}

	public String getUploadDetailContentType() {
		return uploadDetailContentType;
	}

	public void setUploadDetailContentType(String uploadDetailContentType) {
		this.uploadDetailContentType = uploadDetailContentType;
	}

	public String getUploadDetailFileName() {
		return uploadDetailFileName;
	}

	public void setUploadDetailFileName(String uploadDetailFileName) {
		this.uploadDetailFileName = uploadDetailFileName;
	}

	public List getListPrint() {
		return listPrint;
	}

	public void setListPrint(List listPrint) {
		this.listPrint = listPrint;
	}

	public Integer[] getDetailSelect() {
		return detailSelect;
	}

	public void setDetailSelect(Integer[] detailSelect) {
		this.detailSelect = detailSelect;
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public boolean isBool() {
		return bool;
	}

	public void setBool(boolean bool) {
		this.bool = bool;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public CircuitRun getCircuitRun() {
		return circuitRun;
	}

	public void setCircuitRun(CircuitRun circuitRun) {
		this.circuitRun = circuitRun;
	}

	public CircuitRunServer getCircuitRunServer() {
		return circuitRunServer;
	}

	public void setCircuitRunServer(CircuitRunServer circuitRunServer) {
		this.circuitRunServer = circuitRunServer;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCus_id() {
		return cus_id;
	}

	public void setCus_id(String cusId) {
		cus_id = cusId;
	}

	public Integer getId1() {
		return id1;
	}

	public void setId1(Integer id1) {
		this.id1 = id1;
	}

	public List<WarehouseNumber> getWnList() {
		return wnList;
	}

	public void setWnList(List<WarehouseNumber> wnList) {
		this.wnList = wnList;
	}

	public String getBacode() {
		return bacode;
	}

	public void setBacode(String bacode) {
		this.bacode = bacode;
	}

	public String getMxId() {
		return mxId;
	}

	public void setMxId(String mxId) {
		this.mxId = mxId;
	}

	public WarehouseNumber getWarehouseNumber() {
		return warehouseNumber;
	}

	public void setWarehouseNumber(WarehouseNumber warehouseNumber) {
		this.warehouseNumber = warehouseNumber;
	}

	public List<OaAppDetail> getOaDList() {
		return oaDList;
	}

	public void setOaDList(List<OaAppDetail> oaDList) {
		this.oaDList = oaDList;
	}

	public File getOadetailFile() {
		return oadetailFile;
	}

	public void setOadetailFile(File oadetailFile) {
		this.oadetailFile = oadetailFile;
	}

	public String getStorageWay() {
		return storageWay;
	}

	public void setStorageWay(String storageWay) {
		this.storageWay = storageWay;
	}

	public GoodsStore getgStore() {
		return gStore;
	}

	public void setgStore(GoodsStore gStore) {
		this.gStore = gStore;
	}

	

}
