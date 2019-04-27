package com.task.action.payment;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.payment.PaymentVoucherServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.android.pscs.Customer;
import com.task.entity.approval.Signature;
import com.task.entity.fin.BaoxiaoDetail;
import com.task.entity.fin.budget.DeptMonthBudget;
import com.task.entity.fin.budget.SubBudgetRate;
import com.task.entity.payment.PaymentDetail;
import com.task.entity.payment.PaymentVoucher;
import com.task.entity.system.ExecutionNode;
import com.task.util.MKUtil;

public class PaymentVoucherAction extends ActionSupport {
	private PaymentVoucherServer paymentVoucherServer;
	private PaymentVoucher paymentVoucher;
	private PaymentDetail paymentDetail;
	private List<PaymentDetail> listpaymentDetail;
	private Integer del_id;
	private String test;
	private String test1;
	private Integer id;
	private String pay_status;
	private Integer pay_id1;

	private String errorMessage;
	private String successMessage;

	private List<Map> maps;
	private List<Map> maps1;
	private Object[] objects;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private Integer[] detailSelect;// 选择补打数组,审批数组
	private String tag;// 手动添加记录的标记
	private List list;
	private List list1;
	private Map<String, Object> map;
	private String sal_test;
	private String username;// 标识登陆用户
	private Integer pay_id;// 借款标识id
	private String paytag;// 标识审批N级状态（A为个人借款 B为经理借款）
	private String nub;
	private String baoxiao_id;// 报销单id
	private String  myId ;
	// 查看付款申请
	/*
	 * public String findPaymentVoucher(){ if (paymentVoucher != null) {
	 * ActionContext.getContext().getSession().put("paymentVoucher",
	 * paymentVoucher); } else { paymentVoucher = (PaymentVoucher)
	 * ActionContext.getContext().getSession().get( "paymentVoucher"); }
	 * Object[] object =
	 * this.paymentVoucherServer.findPaymentVoucher(paymentVoucher,Integer
	 * .parseInt(cpage), pageSize); if (object != null && object.length > 0) {
	 * maps = (List<Map>) object[0]; if (maps != null && maps.size() > 0) { int
	 * count = (Integer) object[1]; int pageCount = (count + pageSize - 1) /
	 * pageSize; this.setTotal(pageCount + "");
	 * if(test!=null&&!"".equals(test)){
	 * this.setUrl("paymentVoucherAction_findPaymentVoucher.action?test="+test);
	 * }else{ this.setUrl("paymentVoucherAction_findPaymentVoucher.action"); }
	 * errorMessage = null; } else { errorMessage = "没有找到你要查询的内容,请检查后重试!"; } }
	 * return "findPaymentVoucher"; }
	 */

	// 查询科目
	public void findSubjectName() {
		list = paymentVoucherServer.findSubjectName();
		MKUtil.writeJSON(list);

	}

	// 添加付款申请
	public String addPaymentVoucher() {
		boolean bool = this.paymentVoucherServer
				.addPaymentVoucher(this.paymentVoucher);
		if (bool) {
			this.errorMessage = "添加成功!";
			return "addPaymentVoucher";
		} else {
			errorMessage = "请检查数据的有效性!";
			return ERROR;
		}
	}

	// 根据编号查询付款申请
	public String findPaymentVoucherById() {
		this.paymentVoucher = this.paymentVoucherServer
				.findPaymentVoucherById(paymentVoucher);
		return "findPaymentVoucherById";
	}

	// 删除付款申请
	public String delPaymentVoucher() {
		this.paymentDetail = this.paymentVoucherServer
				.findPaymentDetailById(paymentVoucher);
		if (paymentDetail != null) {
			this.paymentVoucherServer.delPaymentVoucher1(paymentVoucher,
					paymentDetail);
		} else {
			this.paymentVoucherServer.delPaymentVoucher(paymentVoucher);
		}
		return "delPaymentVoucher";
	}

	// 根据编号修改付款申请
	public String updatePaymentVoucher() {
		this.paymentVoucherServer.updatePaymentVoucher(paymentVoucher);
		this.errorMessage = "修改成功!";
		return "updatePaymentVoucher";
	}

	// 根据编号修改状态(财务确认)
	public String updatePayment() {
		this.paymentVoucherServer.updatePayment(paymentVoucher);
		return "updatePayment";
	}
	// 根据编号修改状态(财务确认)
	public String updatePayment1() {
			this.paymentVoucherServer.updatePayment1(paymentVoucher);
			 MKUtil.writeJSON(true,"确认成功","");
			 return null;
	}

	// 提交申请
	public String updatePaymentVouche1(String paytag2) {
		PaymentVoucher voucher = this.paymentVoucherServer
				.findPaymentVoucherById(paymentVoucher);
		this.paymentVoucherServer.addPaymentVoucher1(voucher, paytag2);
		// this.successMessage="提交审核成功!";
		if (test != null) {
			return "updatePaymentVouche2";
		} else {
			return "updatePaymentVouche1";
		}

	}

	// 获取验证码
	public void send() {
		Random ran = new Random();
		String number = "" + ran.nextInt(10) + ran.nextInt(10)
				+ ran.nextInt(10) + ran.nextInt(10) + ran.nextInt(10)
				+ ran.nextInt(10);
		System.out.println("验证码:"+number);
		this.paymentVoucherServer.send(number, this.nub);
	}

	// 付款申请审批
	public String findExamList() {
		Object[] obj = paymentVoucherServer.findExamList(Integer
				.parseInt(cpage), pageSize);
		if (obj != null && obj.length > 1) {
			list = (List) obj[1];
			int count = (Integer) obj[0];
			this.setUrl("paymentVoucherAction_findExamList.action");
			int pageCount = (count + pageSize - 1) / pageSize;
			// this.setUrl("huikuanAction!findExamList.action?tag="+this.tag);
			this.setTotal(pageCount + "");
		}
		return "findExamList";
	}

	// 批量审批
	public String updateExamDetail() {
		try {
			if (paymentVoucherServer.updateExamDetail(detailSelect, tag)) {
				return "findExamList1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		errorMessage = "审批失败!请联系数据有效性!";
		return ERROR;
	}

	// 查看明细
	public String salPaymentDetail() {
		this.map = this.paymentVoucherServer.findDetail(this.paymentVoucher);
		this.paymentVoucher = (PaymentVoucher) map.get("voucher2");
		this.list = (List) map.get("list");
		if (test != null) {
			if ("2".equals(test)) {
				return "salPaymentDetail";// 打印
			} else {
				return "updatePaymentDetail";// 修改
			}
		} else {
			return "updatePaymentDetail";// 修改
		}

	}

	// 查询预览
	public String findPaymentDetail1() {
		if (paymentDetail != null) {
			ActionContext.getContext().getSession().put("paymentDetail",
					paymentDetail);
		} else {
			paymentDetail = (PaymentDetail) ActionContext.getContext()
					.getSession().get("paymentDetail");
		}
		Object[] object = this.paymentVoucherServer.findPaymentDetail1(
				paymentDetail, paymentVoucher, Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			maps = (List<Map>) object[0];
			if (maps != null && maps.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this
						.setUrl("paymentVoucherAction_findPaymentDetail1.action?paymentVoucher.id="
								+ paymentVoucher.getId());
			}
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "salPaymentDetail1";
	}

	// 更新打印状态
	public void updatePay_ExecutionNode() {
		this.paymentVoucherServer.updatePay_ExecutionNode(this.pay_status,
				this.pay_id1);
	}

	// 添加借款单和借款明细
	public String saveBorrowings() {
		if (paymentVoucherServer.saveBorrowings(this.paymentVoucher,
				this.listpaymentDetail)) {
			updatePaymentVouche1(this.paytag);
			this.successMessage = "申请成功!";
			return "saveBorrowings";
		} else {
			errorMessage = "请检查数据有效性！";
			return INPUT;
		}
	}

	// 查看付款申请
	public String findPaymentVoucher1() {
		if (paymentVoucher != null) {
			ActionContext.getContext().getSession().put("paymentVoucher",
					paymentVoucher);
		} else {
			paymentVoucher = (PaymentVoucher) ActionContext.getContext()
					.getSession().get("paymentVoucher");
		}
		Object[] object = this.paymentVoucherServer.findPaymentVoucher(
				paymentVoucher, Integer.parseInt(cpage), pageSize, test);
		if (object != null && object.length > 0) {
			maps = (List<Map>) object[0];
			maps1 = (List<Map>) object[1];
			Integer count = (Integer) object[2];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			if (maps != null && maps.size() > 0) {
				if (test != null && !"".equals(test) && maps != null
						&& maps.size() > 0) {
					this
							.setUrl("paymentVoucherAction_findPaymentVoucher1.action?test="
									+ test);
				} else {
					this
							.setUrl("paymentVoucherAction_findPaymentVoucher1.action");
				}
				errorMessage = null;
			}
			if (maps1 != null && maps1.size() > 0) {
				if (test != null && !"".equals(test) && maps1 != null
						&& maps1.size() > 0) {
					this
							.setUrl("paymentVoucherAction_findPaymentVoucher1.action?test="
									+ test);
				} else {
					this
							.setUrl("paymentVoucherAction_findPaymentVoucher1.action");
				}
				errorMessage = null;
			}

			else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "findPaymentVoucher1";
	}
    //借款(财务确认)
	public String findPaymentVoucher2() {
		if (paymentVoucher != null) {
			ActionContext.getContext().getSession().put("paymentVoucher",
					paymentVoucher);
		} else {
			paymentVoucher = (PaymentVoucher) ActionContext.getContext()
					.getSession().get("paymentVoucher");
		}
		Object[] object = this.paymentVoucherServer.findPaymentVoucher2(
				paymentVoucher, Integer.parseInt(cpage), pageSize, test);
		if (object != null && object.length > 0) {
			maps1 = (List<Map>) object[0];
			Integer count = (Integer) object[1];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			if (maps1 != null && maps1.size() > 0) {
				if (test != null && !"".equals(test) && maps1 != null
						&& maps1.size() > 0) {
					this
							.setUrl("paymentVoucherAction_findPaymentVoucher2.action?test="
									+ test);
				} else {
					this
							.setUrl("paymentVoucherAction_findPaymentVoucher2.action");
				}
				errorMessage = null;
			}

			else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "findPaymentVoucher2";
	}
	// 更新总的明细
	public String updateBorrowings() {
		this.paymentVoucherServer.updateBorrowings(this.paymentVoucher,
				this.listpaymentDetail);
		if (test != null && !"".equals(test)) {
			return "updateBorrowings1";// 个人模块更新
		} else {
			return "updateBorrowings";// 总的模块更新
		}

	}

	// 根据登陆用户查询相应的借款
	public void findPayVoucherByname() {
		// maps = this.paymentVoucherServer.findPayVoucherByname(this.username);
		list = this.paymentVoucherServer.findPayVoucherByname(this.username);
		if (baoxiao_id != null && !"".equals(baoxiao_id)) {
			paymentVoucher = this.paymentVoucherServer
					.findPayVoucherByname1(this.baoxiao_id);
		}
		MKUtil.writeJSON(true, "", list, paymentVoucher);// 把结果传到页面
	}

	// 查看借款审批对应审批节点人
	public void findPay_ExecutionNode() {
		Map<Integer, Object> map = this.paymentVoucherServer
				.findPay_ExecutionNode(this.pay_id);
		List<Signature> sigList = (List<Signature>) map.get(1);
		List<ExecutionNode> nodeList = (List<ExecutionNode>) map.get(2);
		System.out.println(sigList.get(0).getSignature_address());
		// list = (List) object[0];
		// list1 = (List) object[1];
		// Signature signature = (Signature) list.get(0);
		// System.out.println(signature.getSignature_address());
		// MKUtil.writeJSON(true, "",list);//把结果传到页面
		MKUtil.writeJSON(true, "", nodeList, sigList);// 把结果传到页面
	}

	public String getPaytag() {
		return paytag;
	}

	public void setPaytag(String paytag) {
		this.paytag = paytag;
	}

	public String getPay_status() {
		return pay_status;
	}

	public void setPay_status(String payStatus) {
		pay_status = payStatus;
	}

	public Integer getPay_id1() {
		return pay_id1;
	}

	public void setPay_id1(Integer payId1) {
		pay_id1 = payId1;
	}

	public List<Map> getMaps1() {
		return maps1;
	}

	public void setMaps1(List<Map> maps1) {
		this.maps1 = maps1;
	}

	public String getNub() {
		return nub;
	}

	public void setNub(String nub) {
		this.nub = nub;
	}

	public Integer getPay_id() {
		return pay_id;
	}

	public void setPay_id(Integer payId) {
		pay_id = payId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTest1() {
		return test1;
	}

	public void setTest1(String test1) {
		this.test1 = test1;
	}

	public List<PaymentDetail> getListpaymentDetail() {
		return listpaymentDetail;
	}

	public void setListpaymentDetail(List<PaymentDetail> listpaymentDetail) {
		this.listpaymentDetail = listpaymentDetail;
	}

	public String getSal_test() {
		return sal_test;
	}

	public void setSal_test(String salTest) {
		sal_test = salTest;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer[] getDetailSelect() {
		return detailSelect;
	}

	public void setDetailSelect(Integer[] detailSelect) {
		this.detailSelect = detailSelect;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public PaymentDetail getPaymentDetail() {
		return paymentDetail;
	}

	public void setPaymentDetail(PaymentDetail paymentDetail) {
		this.paymentDetail = paymentDetail;
	}

	public Integer getDel_id() {
		return del_id;
	}

	public void setDel_id(Integer delId) {
		del_id = delId;
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

	public List<Map> getMaps() {
		return maps;
	}

	public void setMaps(List<Map> maps) {
		this.maps = maps;
	}

	public Object[] getObjects() {
		return objects;
	}

	public void setObjects(Object[] objects) {
		this.objects = objects;
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

	public PaymentVoucherServer getPaymentVoucherServer() {
		return paymentVoucherServer;
	}

	public void setPaymentVoucherServer(
			PaymentVoucherServer paymentVoucherServer) {
		this.paymentVoucherServer = paymentVoucherServer;
	}

	public PaymentVoucher getPaymentVoucher() {
		return paymentVoucher;
	}

	public void setPaymentVoucher(PaymentVoucher paymentVoucher) {
		this.paymentVoucher = paymentVoucher;
	}

	public List getList1() {
		return list1;
	}

	public void setList1(List list1) {
		this.list1 = list1;
	}

	public String getBaoxiao_id() {
		return baoxiao_id;
	}

	public void setBaoxiao_id(String baoxiaoId) {
		baoxiao_id = baoxiaoId;
	}

	public String getMyId() {
		return myId;
	}

	public void setMyId(String myId) {
		this.myId = myId;
	}
    
}
