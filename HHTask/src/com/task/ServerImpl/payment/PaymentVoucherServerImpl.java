package com.task.ServerImpl.payment;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.SmsService;
import com.task.Server.payment.PaymentVoucherServer;
import com.task.Server.sys.CircuitRunServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.AskForLeave;
import com.task.entity.Bonusmoney;
import com.task.entity.OaMessageAlerm;
import com.task.entity.Password;
import com.task.entity.TaHkHuikuan;
import com.task.entity.Users;
import com.task.entity.android.pscs.Customer;
import com.task.entity.approval.Sequences;
import com.task.entity.approval.Signature;
import com.task.entity.bargain.BarContract;
import com.task.entity.fin.BaoxiaoDetail;
import com.task.entity.fin.budget.SubBudgetRate;
import com.task.entity.payment.PaymentDetail;
import com.task.entity.payment.PaymentVoucher;
import com.task.entity.quality.Quality;
import com.task.entity.system.CircuitRun;
import com.task.entity.system.ExecutionNode;
import com.task.util.RtxUtil;
import com.task.util.Util;

public class PaymentVoucherServerImpl implements PaymentVoucherServer {
	private TotalDao totalDao;
	private CircuitRunServer circuitRunServer;
	private SmsService smsService;

	public CircuitRunServer getCircuitRunServer() {
		return circuitRunServer;
	}

	public void setCircuitRunServer(CircuitRunServer circuitRunServer) {
		this.circuitRunServer = circuitRunServer;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/**
	 * 查询所有付款申请
	 * 
	 */
	@Override
	public Object[] findPaymentVoucher(PaymentVoucher paymentVoucher,
			int parseInt, int pageSize, String test) {
		Object[] pre = new Object[3];
		List listP = new ArrayList();// 待打印申报信息
		if (paymentVoucher == null) {
			paymentVoucher = new PaymentVoucher();
		}
		Users loginUser = Util.getLoginUser();// 获得登陆用户
		// 未打印
		String hql = totalDao.criteriaQueries(paymentVoucher, null);
		hql += " and printStatus='未打印'";
		if (paymentVoucher.getNumber() != null
				&& !"".equals(paymentVoucher.getNumber())) {
			hql += " and number='" + paymentVoucher.getNumber() + "'";
		}
		if (paymentVoucher.getContractdate() != null
				&& !"".equals(paymentVoucher.getContractdate())) {
			hql += " and contractdate='" + paymentVoucher.getContractdate()
					+ "'";
		}
		if (paymentVoucher.getUnitname() != null
				&& !"".equals(paymentVoucher.getUnitname())) {
			hql += " and unitname='" + paymentVoucher.getUnitname() + "'";
		}
		if (paymentVoucher.getCategory() != null
				&& !"".equals(paymentVoucher.getCategory())) {
			hql += " and category='" + paymentVoucher.getCategory() + "'";
		}
		if (test != null && !"".equals(test)) {
			hql += " and approvalApplier='" + loginUser.getName() + "'";
		}
		hql += " order by id desc";
		List list = totalDao.query(hql);
		// 已打印
		String hql2 = totalDao.criteriaQueries(paymentVoucher, null);
		if (test != null && !"".equals(test)) {
			hql2 += " and approvalApplier='" + loginUser.getName() + "'";
		}
		hql2 += " and printStatus='已打印'";
		hql2 += " order by id desc";
		List list2 = totalDao.findAllByPage(hql2, parseInt, pageSize);
		int count = totalDao.getCount(hql2);
		Object[] o = { list, list2, count };
		return o;
	}

	/*
	 * 
	 * 添加付款申请(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.payment.PaymentVoucherServer#addPaymentVoucher(com.task
	 * .entity.payment.PaymentVoucher)
	 */
	@Override
	public boolean addPaymentVoucher(PaymentVoucher paymentVoucher) {
		// TODO Auto-generated method stub
		String createdate = Util.getDateTime();
		String createdate1 = Util.getDateTime("yyyy");
		String hql = "select max(number) from PaymentVoucher";
		String max_numbere = (String) this.totalDao.getObjectByCondition(hql);
		if (max_numbere != null && !"".equals(max_numbere)) {
			// String number1 = paymentVoucher2.getNumber();
			long number2 = Long.parseLong(max_numbere) + 1;
			String number3 = Long.toString(number2);
			paymentVoucher.setNumber(number3);
		} else {
			String number2 = createdate1 + "001";
			paymentVoucher.setNumber(number2);
		}
		paymentVoucher.setContractdate(createdate);
		paymentVoucher.setApprovalStatus("待审核");
		return this.totalDao.save(paymentVoucher);
	}

	/*
	 * 根据id查询(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.payment.PaymentVoucherServer#findPaymentVoucherById(com
	 * .task.entity.payment.PaymentVoucher)
	 */
	@Override
	public PaymentVoucher findPaymentVoucherById(PaymentVoucher paymentVoucher) {
		PaymentVoucher paymentVoucher2 = (PaymentVoucher) this.totalDao
				.getObjectById(PaymentVoucher.class, paymentVoucher.getId());
		return paymentVoucher2;
	}

	/*
	 * 
	 * 删除付款申请(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.payment.PaymentVoucherServer#delPaymentVoucher(com.task
	 * .entity.payment.PaymentVoucher)
	 */
	@Override
	public void delPaymentVoucher(PaymentVoucher paymentVoucher) {
		PaymentVoucher paymentVoucher2 = (PaymentVoucher) this.totalDao
				.getObjectById(PaymentVoucher.class, paymentVoucher.getId());
		this.totalDao.delete(paymentVoucher2);
	}

	/*
	 * 根据主键id查询付款明细 (non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.payment.PaymentVoucherServer#findPaymentDetailById(com
	 * .task.entity.payment.PaymentVoucher)
	 */
	@Override
	public PaymentDetail findPaymentDetailById(PaymentVoucher paymentVoucher) {
		String hql = "from PaymentDetail where paymentid=?";
		PaymentDetail paymentDetail = (PaymentDetail) this.totalDao
				.getObjectByCondition(hql, paymentVoucher.getId());
		return paymentDetail;
	}

	/*
	 * 删除付款申请及明细(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.payment.PaymentVoucherServer#delPaymentVoucher1(com.task
	 * .entity.payment.PaymentVoucher, com.task.entity.payment.PaymentDetail)
	 */
	@Override
	public void delPaymentVoucher1(PaymentVoucher paymentVoucher,
			PaymentDetail paymentDetail) {
		paymentVoucher = (PaymentVoucher) totalDao.getObjectById(
				PaymentVoucher.class, paymentVoucher.getId());
		// PaymentVoucher paymentVoucher2 = (PaymentVoucher)
		// this.totalDao.getObjectById(PaymentVoucher.class,
		// paymentDetail.getPaymentid1());
		// PaymentDetail paymentDetail2 = (PaymentDetail)
		// this.totalDao.getObjectById(PaymentDetail.class,
		// paymentDetail.getId());
		// this.totalDao.delete(paymentDetail2);// 删除明细
		// this.totalDao.delete(paymentVoucher2);//删除付款申请
		this.totalDao.delete(paymentVoucher);
	}

	/*
	 * 
	 * 根据编号修改付款申请(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.payment.PaymentVoucherServer#updatePaymentVoucher(com
	 * .task.entity.payment.PaymentVoucher)
	 */
	@Override
	public void updatePaymentVoucher(PaymentVoucher paymentVoucher) {
		// TODO Auto-generated method stub
		this.totalDao.update(paymentVoucher);
	}

	/*
	 * 提交付款申请(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.payment.PaymentVoucherServer#addPaymentVoucher1(com.task
	 * .entity.payment.PaymentVoucher)
	 */
	@Override
	public void addPaymentVoucher1(PaymentVoucher voucher, String paytag2) {
		try {
			Users loginUser = Util.getLoginUser();// 登陆用户信息获得
			// Integer epId = CircuitRunServerImpl.createProcess(192,
			// PaymentVoucher.class, voucher.getId(), "approvalStatus",
			// "id", voucher.getUnitname() + " 的借款"
			// + voucher.getContractnum() + " 借款金额: "
			// + voucher.getVoucherMoney() + "元 ,请您审核!", false);
			Integer epId;
			String dept = loginUser.getDept();
			String processName = "";
			if ("A".equals(paytag2)) {
//				processName = dept + "个人借款审批";
				processName = "个人借款审批";
			} else {
//				processName = dept + "经理借款审批";
				processName = "经理借款审批";
			}

			epId = CircuitRunServerImpl.createProcess(processName,
					PaymentVoucher.class, voucher.getId(), "approvalStatus",
					"id",
					"paymentVoucherAction_salPaymentDetail.action?test=2&paymentVoucher.id="
							+ voucher.getId(), loginUser.getDept() + "部门"
							+ voucher.getUnitname() + "的借款"
							+ voucher.getContractnum() + " 借款金额: "
							+ voucher.getVoucherMoney() + "元 ,请您审核!", true);

			// epId = CircuitRunServerImpl.createProcess(processName,
			// PaymentVoucher.class, voucher.getId(), "approvalStatus",
			// "id", voucher.getUnitname() + " 的借款"
			// + voucher.getContractnum() + " 借款金额: "
			// + voucher.getVoucherMoney() + "元 ,请您审核!", true,
			// null);
			// RtxUtil.sendNotify(code, msg, title, type, time);rtx短息接口

			if (epId != null && epId > 0) {
				voucher.setEpId(epId);
				voucher.setApprovalStatus("未审核");
				totalDao.update(voucher);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 审批付款申请明细(non-Javadoc)
	 * 
	 * @see com.task.Server.payment.PaymentVoucherServer#findExamList(int, int)
	 */
	@Override
	public Object[] findExamList(int parseInt, int pageSize) {
		/**
		 * false:未审批 true：已审批
		 * 
		 */
		Map<String, Object> map = CircuitRunServerImpl.findAuditExeNode(
				PaymentVoucher.class, false);
		if (map != null) {
			String hql = "from PaymentVoucher where id in (:entityId)";
			List list = totalDao.find(hql, map, parseInt, pageSize);
			Object[] exam = new Object[2];
			Long countLong = totalDao.count("select count(*) " + hql, map);
			int count = Integer.parseInt(countLong.toString());
			exam[0] = count;
			exam[1] = list;
			return exam;
		}
		return null;
	}

	/*
	 * 批量付款申请(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.payment.PaymentVoucherServer#updateExamDetail(java.lang
	 * .Integer[], java.lang.String)
	 */
	@Override
	public boolean updateExamDetail(Integer[] detailSelect, String tag) {
		boolean bool = false;
		if (null != detailSelect && detailSelect.length > 0) {
			Users user = Util.getLoginUser();
			String createdate1 = Util.getDateTime("yyyy");
			String createdate2 = Util.getDateTime("yyyy-MM-dd");
			for (int i = 0; i < detailSelect.length; i++) {// 遍历处理状态
				PaymentVoucher detail = (PaymentVoucher) totalDao
						.getObjectById(PaymentVoucher.class, detailSelect[i]);
				if ("ok".equals(tag)) {// 同意
					// 处理审批流程
					String audit = circuitRunServer.updateExeNodeByCirId(detail
							.getEpId(), true, "", false, null, false);

					CircuitRun circuitRun = circuitRunServer
							.findCircuitRunById(detail.getEpId());
					if ("同意".equals(circuitRun.getAllStatus())) {
						// 审批同意后做后续操作
//						String createdate = Util.getDateTime("yyyy");
//						String createdate3 = Util.getDateTime("yyyy-MM-dd");
//						String module_name1 = circuitRun.getEntityName();// 获得模块名称数
//						String hql = "from Password where user.id=?";
//						Password password = (Password) this.totalDao
//								.getObjectByCondition(hql, Util.getLoginUser()
//										.getId());
//						String deptNumber = password.getDeptNumber();// 获得部门编号
//						Users users = (Users) this.totalDao.getObjectById(
//								Users.class, Util.getLoginUser().getId());
//						String post = users.getPost();// 获得职称
//						String hql1 = "select max(se_number) from Sequences where code="
//								+ users.getCode();
//						String max_number = (String) this.totalDao
//								.getObjectByCondition(hql1);
//						String hql2 = "from Sequences";
//						Sequences sequences = (Sequences) this.totalDao
//								.getObjectByCondition(hql2, null);
//						String se_number = "";
//						if (sequences == null) {
//							se_number = deptNumber + "-" + post + "-"
//									+ createdate + "001";
//						} else {
//							if (max_number != null && !"".equals(max_number)) {
//								String[] numberList = max_number.split("-");
//								long number2 = Long.parseLong(numberList[2]) + 1;
//								String number3 = number2 + "";
//								se_number = deptNumber + "-" + post + "-"
//										+ number3;
//							}
//						}
//						sequences = new Sequences();
//						sequences.setCode(Util.getLoginUser().getCode());
//						sequences.setModule_name(module_name1);
//						sequences.setSe_number(se_number);
//						sequences.setSe_date(createdate3);
//						this.totalDao.save(sequences);
					}
					// 更改明细状态
				} else {// 打回
					// 处理审批流程
					// 处理审批流程
					circuitRunServer.updateExeNodeByCirId(detail.getEpId(),
							false, "", false, null, false);
					detail.setApprovalStatus("打回");
					detail.setApprovalApplier(user.getName());
					totalDao.update(detail);
				}
				bool = true;
			}
		}
		return bool;
	}

	/*
	 * 
	 * 查看明细(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.payment.PaymentVoucherServer#findDetail(com.task.entity
	 * .payment.PaymentVoucher)
	 */
	@Override
	public Map<String, Object> findDetail(PaymentVoucher paymentVoucher) {
		Map<String, Object> map = new HashMap();
		PaymentVoucher voucher = (PaymentVoucher) this.totalDao.getObjectById(
				PaymentVoucher.class, paymentVoucher.getId());
		String hql = "from PaymentDetail where voucher.id=" + voucher.getId();
		List list = this.totalDao.query(hql);
		List<PaymentDetail> list1 = new ArrayList<PaymentDetail>();
		for (int i = 0; i < list.size(); i++) {
			PaymentDetail detail2 = (PaymentDetail) list.get(i);
			PaymentDetail detail3 = (PaymentDetail) this.totalDao
					.getObjectById(PaymentDetail.class, detail2.getId());
			list1.add(detail3);
		}
		// PaymentVoucher voucher = (PaymentVoucher)
		// this.totalDao.getObjectById(PaymentVoucher.class,
		// detail2.getPaymentid1());
		map.put("list", list1);
		map.put("voucher2", voucher);
		return map;
	}

	/*
	 * 查看预览(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.payment.PaymentVoucherServer#findPaymentDetail1(com.task
	 * .entity.payment.PaymentVoucher, int, int)
	 */
	@Override
	public Object[] findPaymentDetail1(PaymentDetail detail,
			PaymentVoucher paymentVoucher, int parseInt, int pageSize) {
		if (detail == null) {
			detail = new PaymentDetail();
		}
		PaymentVoucher voucher = (PaymentVoucher) this.totalDao.getObjectById(
				PaymentVoucher.class, paymentVoucher.getId());
		String hql = totalDao.criteriaQueries(detail, null);
		hql += "and paymentid1=" + voucher.getId();
		if (detail.getOrders_num() != null
				&& !"".equals(detail.getOrders_num())) {
			hql += " and orders_num=" + detail.getOrders_num();
		}
		if (detail.getReceipt_num() != null
				&& !"".equals(detail.getReceipt_num())) {
			hql += " and receipt_num=" + detail.getReceipt_num();
		}
		if (detail.getAskrequisition_num() != null
				&& !"".equals(detail.getAskrequisition_num())) {
			hql += " and askrequisition_num=" + detail.getAskrequisition_num();
		}
		if (detail.getBorrowerlist_num() != null
				&& !"".equals(detail.getBorrowerlist_num())) {
			hql += " and borrowerlist_num='" + detail.getBorrowerlist_num()
					+ "'";
		}
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/*
	 * 
	 * 添加借款单个借款明细(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.payment.PaymentVoucherServer#saveBorrowings(com.task.
	 * entity.payment.PaymentVoucher, com.task.entity.payment.PaymentDetail)
	 */
	@Override
	public boolean saveBorrowings(PaymentVoucher paymentVoucher,
			List<PaymentDetail> listpaymentDetail) {
		boolean boo = true;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Users user = (Users) Util.getLoginUser();
		String createdate = Util.getDateTime("yyyy-MM-dd");
		String createdate1 = Util.getDateTime("yyyy-MM");
		String[] a = createdate1.split("-");
		createdate1 = a[0] + a[1];
		String hql = "select max(number) from PaymentVoucher";
		String max_numbere = (String) this.totalDao.getObjectByCondition(hql);
		if (max_numbere != null && !"".equals(max_numbere)) {
			// String number1 = paymentVoucher2.getNumber();
			long number2 = Long.parseLong(max_numbere) + 1;
			String number3 = Long.toString(number2);
			paymentVoucher.setNumber(number3);
		} else {
			String number2 = createdate1 + "001";
			paymentVoucher.setNumber(number2);
		}
		paymentVoucher.setApprovalApplier(user.getName());
		paymentVoucher.setContractdate(createdate);
		paymentVoucher.setPaymentStatus("未付款");// 付款状态
		// paymentVoucher.setApprovalStatus("未审核");
		// boo = this.totalDao.save(paymentVoucher);
		// String sql="select max(id) from PaymentVoucher";
		// String id1 = (String) this.totalDao.getObjectByCondition(sql);
		// 添加明细
		if (listpaymentDetail != null) {
			Set<PaymentDetail> details = new HashSet<PaymentDetail>();
			for (int i = 0; i < listpaymentDetail.size(); i++) {
				PaymentDetail detail = new PaymentDetail();
				detail = listpaymentDetail.get(i);
				detail.setDetailStatus("未付款");
				detail.setUnitname1(paymentVoucher.getUnitname());
				detail.setNumber1(paymentVoucher.getNumber());
				details.add(detail);
			}
			paymentVoucher.setPaymentDetail(details);// 把明细对象添加到主表
			paymentVoucher.setPrepaidMoney(0F);// 把明细对象添加到主表
			paymentVoucher.setPrintStatus("未打印");
			paymentVoucher.setApprovaldept(user.getDept());// 申请人所在部门
			this.totalDao.save(paymentVoucher);
		}
		return boo;

	}

	/*
	 * 更新总的明细(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.payment.PaymentVoucherServer#updateBorrowings(com.task
	 * .entity.payment.PaymentVoucher, java.util.List)
	 */
	@Override
	public void updateBorrowings(PaymentVoucher paymentVoucher,
			List<PaymentDetail> listpaymentDetail) {
		PaymentVoucher oldpaymentVoucher = (PaymentVoucher) this.totalDao
				.getObjectById(PaymentVoucher.class, paymentVoucher.getId());
		if ("打回".equals(oldpaymentVoucher.getApprovalStatus())) {
			CircuitRunServerImpl.updateCircuitRun(paymentVoucher.getEpId());
		}
		Set<PaymentDetail> details = oldpaymentVoucher.getPaymentDetail();
		// Set<PaymentDetail> details2 = new HashSet<PaymentDetail>();
		List<Integer> idlist = new ArrayList<Integer>();
		if (listpaymentDetail != null && listpaymentDetail.size() > 0) {
			for (int i = 0; i < listpaymentDetail.size(); i++) {
				PaymentDetail detail = new PaymentDetail();
				detail = listpaymentDetail.get(i);
				if (detail.getId() == null) {
					// 做添加操作
					detail.setVoucher(oldpaymentVoucher);
					detail.setNumber1(oldpaymentVoucher.getNumber());
					detail.setUnitname1(paymentVoucher.getUnitname());
					detail.setDetailStatus("未付款");
					details.add(detail);
				} else {
					idlist.add(detail.getId());
					// 做修改操作
					for (PaymentDetail detail2 : details) {
						if (detail2.getId().equals(detail.getId())) {
							try {
								// 把页面传过来的明细赋给detail2
								BeanUtils.copyProperties(detail, detail2,
										new String[] { "voucher", "number1",
												"unitname1", "detailStatus" });
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		// 做删除操作
		Set<PaymentDetail> details2 = new HashSet<PaymentDetail>();
		for (PaymentDetail detail3 : details) {
			if (detail3.getId() != null && !idlist.contains(detail3.getId())) {
				detail3.setVoucher(null);
				details2.add(detail3);
				totalDao.delete(detail3);
			}
		}
		details.removeAll(details2);
		// 把页面传过来的借款赋给oldpaymentVoucher
		BeanUtils.copyProperties(paymentVoucher, oldpaymentVoucher,
				new String[] { "number", "contractnum", "approvalStatus",
						"approvalApplier", "contractdate", "paymentDetail",
						"epId" });
		oldpaymentVoucher.setPaymentDetail(details);// 把明细对象添加到主表
		if ("打回".equals(oldpaymentVoucher.getApprovalStatus())) {
			oldpaymentVoucher.setApprovalStatus("未审核");
		}
		this.totalDao.update(oldpaymentVoucher);
		// paymentVoucher.setPaymentStatus(oldpaymentVoucher.getPaymentStatus());
	}

	/*
	 * 根据登陆用户查找对应的借款(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.payment.PaymentVoucherServer#findPayVoucherByname(java
	 * .lang.String)
	 */
	@Override
	public List findPayVoucherByname(String username) {
		String hql = "from PaymentVoucher where paymentStatus in ('未付款','未付清') and approvalStatus in ('已领取') and  approvalApplier=? ";
		List maps = this.totalDao.query(hql, username);
		return maps;
	}

	/*
	 * 
	 * 查看借款审批对应审批节点人(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.payment.PaymentVoucherServer#findPay_ExecutionNode(java
	 * .lang.Integer)
	 */
	@Override
	public Map<Integer, Object> findPay_ExecutionNode(Integer payId) {
		PaymentVoucher paymentVoucher = (PaymentVoucher) this.totalDao
				.getObjectById(PaymentVoucher.class, payId);
		String hql = "from ExecutionNode where auditStatus='同意' and  circuitRun.id=? order by auditLevel desc";
		List<ExecutionNode> list1 = this.totalDao.query(hql, paymentVoucher
				.getEpId());
		List<Signature> list = new ArrayList<Signature>();
		for (int i = 0; i < list1.size(); i++) {
			ExecutionNode executionNode = (ExecutionNode) list1.get(i);
			String username = executionNode.getAuditUserName();
			String hql1 = "from Signature where name='" + username
					+ "' and default_address='默认' ";
			Signature signature = (Signature) this.totalDao
					.getObjectByCondition(hql1);
			list.add(signature);
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, list);
		map.put(2, list1);
		return map;
	}

	/*
	 * 发送验证码到RTX(non-Javadoc)
	 * 
	 * @see com.task.Server.payment.PaymentVoucherServer#send()
	 */
	@Override
	public void send(String number, String nub) {
		HttpServletResponse response = ServletActionContext.getResponse();
		Users user = (Users) Util.getLoginUser();
		Cookie numberCookie = new Cookie(user.getCode() + "_yzm", number);
		numberCookie.setMaxAge(60 * 10);// 存入cookie十分钟
		response.addCookie(numberCookie);
		// user.getPassword().getPhoneNumber()
		// smsService.send(user.getPassword().getPhoneNumber(),
		// "序列号:"+nub+"  验证码为："+number+"(借款凭证验证依据，切勿告知他人),请在文本框中输入已完成验证，有问题请致电021-59567057【PEBS】");
		RtxUtil.sendNotify(user.getCode(), "验证码为：【" + number + "】序号:(" + nub
				+ ")(系统审批验证依据，切勿告知他人),有问题请致电021-59567057【PEBS】", "系统消息", "0",
				"0");
	}

	/*
	 * 更新打印状态(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.payment.PaymentVoucherServer#updatePay_ExecutionNode(
	 * java.lang.String, java.lang.Integer)
	 */
	@Override
	public void updatePay_ExecutionNode(String payStatus, Integer payId1) {
		PaymentVoucher paymentVoucher = (PaymentVoucher) this.totalDao
				.getObjectById(PaymentVoucher.class, payId1);
		paymentVoucher.setPrintStatus(payStatus);
		this.totalDao.update(paymentVoucher);
	}

	/*
	 * 查询科目(non-Javadoc)
	 * 
	 * @see com.task.Server.payment.PaymentVoucherServer#findSubjectName()
	 */
	@Override
	public List<SubBudgetRate> findSubjectName() {
		String hql = "select distinct(name) from SubBudgetRate";
		List<SubBudgetRate> list = this.totalDao.query(hql, null);
		return list;
	}

	public SmsService getSmsService() {
		return smsService;
	}

	public void setSmsService(SmsService smsService) {
		this.smsService = smsService;
	}

	/*
	 * 查看报销单对应的借款单(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.payment.PaymentVoucherServer#findPayVoucherByname1(java
	 * .lang.String)
	 */
	@Override
	public PaymentVoucher findPayVoucherByname1(String baoxiaoId) {
		PaymentVoucher paymentVoucher = (PaymentVoucher) this.totalDao
				.getObjectById(PaymentVoucher.class, Integer
						.parseInt(baoxiaoId));
		return paymentVoucher;
	}

	/*
	 * 财务确认(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.payment.PaymentVoucherServer#updatePayment(com.task.entity
	 * .payment.PaymentVoucher)
	 */
	@Override
	public void updatePayment(PaymentVoucher paymentVoucher) {
		PaymentVoucher paymentVoucher2 = (PaymentVoucher) this.totalDao
				.getObjectById(PaymentVoucher.class, paymentVoucher.getId());
		paymentVoucher2.setApprovalStatus("已领取");
		this.totalDao.update(paymentVoucher2);
	}

	/*
	 * 查询借款信息(non-Javadoc)
	 * 
	 * @see com.task.Server.payment.PaymentVoucherServer#findPaymentVoucher(int)
	 */
	@Override
	public PaymentVoucher findPaymentVoucher(int id) {
		String hql = "from PaymentVoucher where epId=?";
		PaymentVoucher paymentVoucher = (PaymentVoucher) this.totalDao
				.getObjectByCondition(hql, id);
		return paymentVoucher;
	}

	@Override
	public Object[] findPaymentVoucher2(PaymentVoucher paymentVoucher,
			int parseInt, int pageSize, String test) {
		if (paymentVoucher == null) {
			paymentVoucher = new PaymentVoucher();
		}
		Users loginUser = Util.getLoginUser();// 获得登陆用户
		// 已打印
		String hql2 = totalDao.criteriaQueries(paymentVoucher, null);
		if (test != null && !"".equals(test)) {
			hql2 += " and approvalApplier='" + loginUser.getName() + "'";
		}
		hql2 += " and printStatus='已打印'";
		hql2 += " and approvalStatus='同意'";
		hql2 += " order by id desc";
		List list2 = totalDao.findAllByPage(hql2, parseInt, pageSize);
		int count = totalDao.getCount(hql2);
		Object[] o = { list2, count };
		return o;
	}

	@Override
	public void updatePayment1(PaymentVoucher paymentVoucher) {
		PaymentVoucher paymentVoucher2 = (PaymentVoucher) this.totalDao
		.getObjectById(PaymentVoucher.class, paymentVoucher.getId());
        paymentVoucher2.setApprovalStatus("已领取");
         this.totalDao.update(paymentVoucher2);  		
	}

}
