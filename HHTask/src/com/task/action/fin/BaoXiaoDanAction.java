package com.task.action.fin;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.fin.BaoXiaoDanServer;
import com.task.entity.GoodsStore;
import com.task.entity.codetranslation.CodeTranslation;
import com.task.entity.fin.BaoxiaoDan;
import com.task.entity.fin.BaoxiaoDetail;
import com.task.entity.fin.budget.DeptMonthBudget;
import com.task.entity.payee.Payee;
import com.task.util.MKUtil;

/**
 * 报销单action
 * 
 * @author jhh
 * 
 */
public class BaoXiaoDanAction extends ActionSupport {
	private BaoXiaoDanServer baoXiaoDanServer;

	private BaoxiaoDan baoxiaodan;
	private BaoxiaoDetail detail;// 报销单明细
	BaoxiaoDan baoxiao;// 条件查询
	private List<BaoxiaoDetail> listDetail;
	private List list; 
	private List<Integer> ids;
	private List listConfirm;// 需要确认的记录
	private String tag;
	private String message;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private Integer id;// 主键
	private String startDate;// 开始时间
	private String endDate;// 截止时间
	private String dept;// 按部门汇总
	private String baoxiaoStyle;// 按报销类别汇总
	private String errorMessage;// 报错信息
	private String planMonth;// 预算月份

	private Float money;
	private Float money1;// 付款金额标识
	private String money2;// 借款金额标识
	private Float oldmoney;// 之前的金额
	private String goodsStoreLots;// 批次
	private String tag1;
	// 收款账户
	private Payee payee;
	private List<Payee> payeeList;
	private File uploadFile;
	private String name;
	/**
	 * 添加报销单计算月份
	 * 
	 * @return
	 */
	public String preBaoxiandan() {
		planMonth = baoXiaoDanServer.getPlanMonth();
		if (null != planMonth) {
			Calendar week = Calendar.getInstance();
			int weekday = week.get(Calendar.DAY_OF_WEEK) - 1;
			if (weekday > 0 && weekday < 8) {
				return "preSaveBaoXiaoDan";
			} else {
				errorMessage = "当前不在财务规定的报账时限内，请于周四，周五两天进行报账！";
				return ERROR;
			}

		} else {
			errorMessage = "没有预算申报信息，无法进行报销，请确认后处理！";
			return ERROR;
		}

	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * 添加报销单
	 * 
	 * @return
	 */
	public String saveBaoXiaoDan() {
		// baoxiaodan.setBaoxiaoDetail(new HashSet(listDetail));
		if (baoXiaoDanServer.saveBaoXiaoDan(baoxiaodan, listDetail,
				this.money1, this.money2)) {
			return "saveBaoXiaoDanOK";
		} else {
			errorMessage = "报销金额超出预算金额，请核实！";
			return INPUT;
		}

	}

	/**
	 * 添加生产性报销单
	 * 
	 * @return
	 */
	public String saveBaoXiaoDan1() {
		// baoxiaodan.setBaoxiaoDetail(new HashSet(listDetail));
		if (baoXiaoDanServer.saveBaoXiaoDan1(baoxiaodan, listDetail,
				this.money1, this.money2, tag, ids)) {
			return "saveBaoXiaoDanOK1";
		} else {
			errorMessage = "数据有误，请核实！";
			return INPUT;
		}

	}

	/**
	 * 查询管理报销单
	 * 
	 * @return
	 */
	public String findBaoXiaoDan() {
		this.pageSize = 15;
		this.setUrl("BaoXiaoDanAction!findBaoXiaoDan.action?tag=" + this.tag);
		HttpServletRequest request = ServletActionContext.getRequest();
		// BaoxiaoDan baoxiao=new BaoxiaoDan();
		if (null != baoxiao) {
			/*
			 * baoxiao.setBaoxiaoBarcode(baoxiaodan.getBaoxiaoBarcode());
			 * baoxiao.setBaoxiaoren(baoxiao.getBaoxiaoren());
			 * baoxiao.setShoukuanRen(baoxiaodan.getShoukuanRen());
			 * baoxiao.setMore(baoxiaodan.getPayStyle());
			 */
			request.getSession().setAttribute("baoxiao", baoxiao);
		} else {
			baoxiao = (BaoxiaoDan) request.getSession().getAttribute("baoxiao");
		}
		// 查看借款明细用
		if (baoxiao == null || baoxiao.getPaymentVouchers() != null) {
			message = "pv";
		}
		Object[] obj = baoXiaoDanServer.findBaoXiaoDan(baoxiao, startDate,
				endDate, Integer.parseInt(cpage), pageSize, this.tag);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "findBaoxiaoOK";
	}

	// 费用报销单(财务)
	public String findBaoXiaoDan2() {
		this.pageSize = 15;
		this.setUrl("BaoXiaoDanAction!findBaoXiaoDan2.action?tag=" + this.tag);
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != baoxiao) {
			request.getSession().setAttribute("baoxiao", baoxiao);
		} else {
			baoxiao = (BaoxiaoDan) request.getSession().getAttribute("baoxiao");
		}
		Object[] obj = baoXiaoDanServer.findBaoXiaoDan2(baoxiao, startDate,
				endDate, Integer.parseInt(cpage), pageSize, this.tag);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "findBaoXiaoDan2";
	}

	/**
	 * 根据ID获取单个报销单
	 * 
	 * @return
	 */
	public String getBaoXiaoDan() {
		baoxiaodan = new BaoxiaoDan();
		baoxiaodan = baoXiaoDanServer.getBaoXiaoDanById(id);
		// listDetail = new ArrayList(new
		// HashSet(baoxiaodan.getBaoxiaoDetail()));
		if ("detail".equals(this.tag)) {
			Calendar week = Calendar.getInstance();
			int weekday = week.get(Calendar.DAY_OF_WEEK) - 1;
			if (weekday > 3 && weekday < 6) {
				return "getMoreDetailOK";
			} else {
				errorMessage = "当前不在财务规定的报账时限内，请于周四，周五两天进行报账！";
				return ERROR;
			}

		} else if ("update".equals(this.tag)) {
			return "getUpdateOK";
		}
		return INPUT;
	}

	/**
	 * 根据ID获取单个报销单
	 * 
	 * @return
	 */
	public String getBaoXiaoDan1() {
		baoxiaodan = new BaoxiaoDan();
		baoxiaodan = baoXiaoDanServer.getBaoXiaoDanById(id);
		if ("detail".equals(this.tag1)) {
			return "getMoreDetailOK1";
		} else if ("update".equals(this.tag1)) {
			return "getUpdateOK1";
		}
		return INPUT;
	}

	/**
	 * 更新报销单
	 * 
	 * @return
	 */
	public String updateBaoXiaoDan() {
		baoxiaodan.setBaoxiaoDetail(new HashSet(listDetail));
		try {
			if (baoXiaoDanServer.updateBaoXiaoDan(baoxiaodan, listDetail,
					money2, money1, oldmoney)) {
				Calendar week = Calendar.getInstance();
				int weekday = week.get(Calendar.DAY_OF_WEEK) - 1;
				if (weekday > 3 && weekday < 6) {
					return "updateOK";
				} else {
					errorMessage = "当前不在财务规定的报账时限内，请于周四，周五两天进行报账！";
					return ERROR;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	/**
	 * 更新报销单(生产)
	 * 
	 * @return
	 */
	public String updateBaoXiaoDan1() {
		baoxiaodan.setBaoxiaoDetail(new HashSet(listDetail));
		try {
			if (baoXiaoDanServer.updateBaoXiaoDan1(baoxiaodan, listDetail,
					money2, money1, oldmoney)) {
				return "updateOK1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}

	// 财务确认
	public String updateXiaoDan() {
		baoXiaoDanServer.updateXiaoDan(id);
		return "updateXiaoDan";
	}

	// 财务确认
	public String updateXiaoDan1() {
		baoXiaoDanServer.updateXiaoDan(id);
		return "updateXiaoDan1";
	}

	// 财务确认
	public String updateXiaoDan2() {
		baoXiaoDanServer.updateXiaoDan2(id);
		MKUtil.writeJSON(true, "确认成功", "");
		return null;
	}

	/**
	 * 删除报销单
	 * 
	 * @return
	 */
	public String deleteBaoXiaoDanById() {
		this.cpage = cpage;
		if (baoXiaoDanServer.deleteBaoXiaoDan(id)) {
			return "deleteBXDOK";
		}
		return INPUT;
	}

	/**
	 * 报销数据汇总
	 * 
	 * @return
	 */
	public String findSumBaoXiaoDan() {
		this.pageSize = 15;
		this.setUrl("BaoXiaoDanAction!findSumBaoXiaoDan.action");
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != baoxiaodan) {
			request.getSession().setAttribute("baoxiaodan", baoxiaodan);
		} else {
			baoxiaodan = (BaoxiaoDan) request.getSession().getAttribute(
					"baoxiaodan");
		}
		Object[] obj = baoXiaoDanServer.findSumBaoXiaoDan(baoxiaodan,
				startDate, endDate, Integer.parseInt(cpage), pageSize);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "findSumOK";
	}

	/**
	 * 查找所有报销类别，做下拉
	 * 
	 * @return
	 */
	public String findBaoXiaoStyle() {
		String message = baoXiaoDanServer.findBaoXiaoStyle(tag);
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
	 * 查找下拉部门确认下拉
	 */
	public String finddeptConfirm() {
		String message = baoXiaoDanServer.findDeptConfirm(tag);
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

	/** 查找子科目 */
	public String findchildClass() {
		String message = baoXiaoDanServer.findchildClass(tag);
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

	/** 查找报销部门 */
	public String findchildDept() {
		String message = baoXiaoDanServer.findchildDept(tag, planMonth);
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

	/** 查找入库表件号 */
	public String findjianhao() {
		String message = baoXiaoDanServer.findjianhao();
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
		List<DeptMonthBudget> list = baoXiaoDanServer.findchildSubjects(tag,
				planMonth);
		MKUtil.writeJSON(list);
		return null;
	}
	
	public String findkmTree(){
		list = baoXiaoDanServer.findkmTree();
		MKUtil.writeJSON(list);
		return null;
	}
	
	public void findchildSubjects2() {
		List objs = baoXiaoDanServer.findchildSubjects2(tag);
		MKUtil.writeJSON(objs);
	}

	public void findchildSubjects3() {
		List objs = baoXiaoDanServer.findchildSubjects3(tag);
		MKUtil.writeJSON(objs);
	}

	public void findjianhao2() {
		List objs = baoXiaoDanServer.findjianhao2(tag);
		MKUtil.writeJSON(objs);
	}

	/** 根据件号查询批次 */
	public String findpici() {
		List list1 = null;
		if (!"".equals(tag)) {
			list1 = baoXiaoDanServer.findpici(tag);
		}
		MKUtil.writeJSON(list1);
		return null;
	}

	/**
	 * 查询外委供应商
	 */
	public void findWaiweiGys() {
		List objs = baoXiaoDanServer.findWaiweiGys(tag);
		MKUtil.writeJSON(objs);
	}

	/**
	 * 查询外购供应商
	 */
	public void findWaigouGys() {
		List objs = baoXiaoDanServer.findWaigouGys(tag);
		MKUtil.writeJSON(objs);
	}

	/**
	 * 查询非主营业务
	 */
	public void findfzyBusiness() {
		List objs = baoXiaoDanServer.findfzyBusiness(tag);
		MKUtil.writeJSON(objs);
	}

	/**
	 * 通过科目查询非主营业务
	 */
	public void findfzykm() {
		List objs = baoXiaoDanServer.findfzykm(tag);
		MKUtil.writeJSON(objs);
	}

	public void findpiWaiwei() {
		List objs = baoXiaoDanServer.findpiWaiwei(tag);
		MKUtil.writeJSON(objs);
	}

	public void findpiWaigou() {
		List objs = baoXiaoDanServer.findpiWaigou(tag);
		MKUtil.writeJSON(objs);
	}

	/** 根据件号和批次查询数量 */
	public String findshuliang() {
		// String count = "0";
		GoodsStore goodsStore = null;
		if (!"".equals(tag) && !"".equals(goodsStoreLots)) {
			goodsStore = baoXiaoDanServer.findshuliang(tag, goodsStoreLots);
		}
		// Float count1 = Float.parseFloat(count);
		MKUtil.writeJSON(goodsStore);
		return null;
	}

	/**
	 * 查询管理报销单(生产)
	 * 
	 * @return
	 */
	public String findBaoXiaoDan1() {
		this.pageSize = 15;
		this.setUrl("BaoXiaoDanAction!findBaoXiaoDan1.action?tag=" + this.tag);
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != baoxiao) {
			request.getSession().setAttribute("baoxiao", baoxiao);
		} else {
			baoxiao = (BaoxiaoDan) request.getSession().getAttribute("baoxiao");
		}
		Object[] obj = baoXiaoDanServer.findBaoXiaoDan1(baoxiao, startDate,
				endDate, Integer.parseInt(cpage), pageSize, this.tag);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "findBaoXiaoDan1";
	}

	public String compareBudgetCount() {
		String message = baoXiaoDanServer.compareBudgetCount(id, money);
		MKUtil.writeJSON(message);
		return null;
	}

	/****
	 * 查找所有明细
	 * 
	 * @return
	 */
	public String findBaoXiaoDanDetail() {
		this.pageSize = 15;
		this.setUrl("BaoXiaoDanAction!findBaoXiaoDanDetail.action");
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != baoxiaodan) {
			request.getSession().setAttribute("baoxiaodan", baoxiaodan);
		} else {
			baoxiaodan = (BaoxiaoDan) request.getSession().getAttribute(
					"baoxiaodan");
		}
		if (null != detail) {
			request.getSession().setAttribute("detail", detail);
		} else {
			detail = (BaoxiaoDetail) request.getSession()
					.getAttribute("detail");
		}
		Object[] obj = baoXiaoDanServer.findBaoXiaoDanDetail(baoxiaodan,
				detail, startDate, endDate, Integer.parseInt(cpage), pageSize,
				this.tag);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "findBaoxiaoDanDetailOK";
	}

	/**
	 * 导出报销单数据
	 * 
	 * @return
	 */
	public String exportEXCEL() {
		baoXiaoDanServer.exportExcel(baoxiaodan, startDate, endDate, tag);
		return null;
	}

	/**
	 * 导出报销单数据
	 * 
	 * @return
	 */
	public String exportEXCEL1() {
		baoXiaoDanServer.exportExcel1(baoxiaodan, startDate, endDate, tag);
		return null;
	}

	/**
	 * 导出报销单数据(财务)
	 * 
	 * @return
	 */
	public String exportEXCEL2() {
		baoXiaoDanServer.exportExcel2(baoxiaodan, startDate, endDate, tag);
		return null;
	}

	/**
	 * 导出明细数据
	 */
	public String exportDetailEXCEL() {
		baoXiaoDanServer.exportDetailExcel(baoxiaodan, detail, startDate,
				endDate, tag);
		return null;
	}

	/**
	 * 跨部门费用报销确认
	 * 
	 * @return
	 */
	public String findOtherDeptBXD() {
		this.pageSize = 15;
		this.setUrl("BaoXiaoDanAction!findOtherDeptBXD.action");
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != baoxiaodan) {
			request.getSession().setAttribute("baoxiaodan", baoxiaodan);
		} else {
			baoxiaodan = (BaoxiaoDan) request.getSession().getAttribute(
					"baoxiaodan");
		}
		if (null != detail) {
			request.getSession().setAttribute("detail", detail);
		} else {
			detail = (BaoxiaoDetail) request.getSession()
					.getAttribute("detail");
		}
		Object[] obj = baoXiaoDanServer
				.findOtherDeptBXD(baoxiaodan, detail, startDate, endDate,
						Integer.parseInt(cpage), pageSize, this.tag);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		listConfirm = (List) obj[2];
		return "findOtherDetail";
	}

	/**
	 * 确认单条明细
	 * 
	 * @return
	 */
	public String updateDetailById() {
		if (baoXiaoDanServer.updateDetailById(id, tag)) {
			return "confirmOK";
		}
		return ERROR;
	}

	public String findAll() {
		Map<Integer, Object> map = baoXiaoDanServer.findAll(payee, Integer
				.parseInt(cpage), pageSize);
		payeeList = (List<Payee>) map.get(1);
		if (payeeList != null & payeeList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("BaoXiaoDanAction!findAll.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "payee_List";
	}
	public void checkName(){
		Payee p =baoXiaoDanServer.findByName(name);
		 if(p!=null)
	        {
			 MKUtil.writeJSON("err");
	        }
	        else
	        {
	        	MKUtil.writeJSON("ok");
	        }
	}
	public String addPayee(){
		String b = baoXiaoDanServer.save(payee);
		if ("true".equals(b)) {
			errorMessage = "添加成功";
		} else {
			errorMessage = "添加失败";
		}
		return "error";
	}

	public String findoneById() {
		if (id != null) {
			payee = baoXiaoDanServer.findOneById(id);
			return "payee_xiangxi";
		} else {
			return "error";
		}

	}

	public String importFile() {
		errorMessage = baoXiaoDanServer.importFile(uploadFile);
		if (errorMessage.equals("true")) {
			errorMessage = "添加成功";
			return "error";
		}
		return "error";
	}

	public BaoXiaoDanServer getBaoXiaoDanServer() {
		return baoXiaoDanServer;
	}

	public void setBaoXiaoDanServer(BaoXiaoDanServer baoXiaoDanServer) {
		this.baoXiaoDanServer = baoXiaoDanServer;
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

	public BaoxiaoDan getBaoxiaodan() {
		return baoxiaodan;
	}

	public void setBaoxiaodan(BaoxiaoDan baoxiaodan) {
		this.baoxiaodan = baoxiaodan;
	}

	public List<BaoxiaoDetail> getListDetail() {
		return listDetail;
	}

	public void setListDetail(List<BaoxiaoDetail> listDetail) {
		this.listDetail = listDetail;
	}

	public BaoxiaoDan getBaoxiao() {
		return baoxiao;
	}

	public void setBaoxiao(BaoxiaoDan baoxiao) {
		this.baoxiao = baoxiao;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getBaoxiaoStyle() {
		return baoxiaoStyle;
	}

	public void setBaoxiaoStyle(String baoxiaoStyle) {
		this.baoxiaoStyle = baoxiaoStyle;
	}

	public BaoxiaoDetail getDetail() {
		return detail;
	}

	public void setDetail(BaoxiaoDetail detail) {
		this.detail = detail;
	}

	public String getPlanMonth() {
		return planMonth;
	}

	public void setPlanMonth(String planMonth) {
		this.planMonth = planMonth;
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public List getListConfirm() {
		return listConfirm;
	}

	public void setListConfirm(List listConfirm) {
		this.listConfirm = listConfirm;
	}

	public Float getMoney1() {
		return money1;
	}

	public void setMoney1(Float money1) {
		this.money1 = money1;
	}

	public String getMoney2() {
		return money2;
	}

	public void setMoney2(String money2) {
		this.money2 = money2;
	}

	public Float getOldmoney() {
		return oldmoney;
	}

	public void setOldmoney(Float oldmoney) {
		this.oldmoney = oldmoney;
	}

	public String getGoodsStoreLots() {
		return goodsStoreLots;
	}

	public void setGoodsStoreLots(String goodsStoreLots) {
		this.goodsStoreLots = goodsStoreLots;
	}

	public String getTag1() {
		return tag1;
	}

	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public Payee getPayee() {
		return payee;
	}

	public void setPayee(Payee payee) {
		this.payee = payee;
	}

	public List<Payee> getPayeeList() {
		return payeeList;
	}

	public void setPayeeList(List<Payee> payeeList) {
		this.payeeList = payeeList;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
