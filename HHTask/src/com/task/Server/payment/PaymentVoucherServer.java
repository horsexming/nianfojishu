package com.task.Server.payment;

import java.util.List;
import java.util.Map;

import com.task.entity.fin.budget.SubBudgetRate;
import com.task.entity.payment.PaymentDetail;
import com.task.entity.payment.PaymentVoucher;

public interface PaymentVoucherServer {

	/**
	 * 查询所有付款申请
	 * @param paymentVoucher
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	Object[] findPaymentVoucher(PaymentVoucher paymentVoucher, int parseInt,
			int pageSize,String test);

	/**
	 * 添加付款申请
	 * @param paymentVoucher
	 */
	boolean addPaymentVoucher(PaymentVoucher paymentVoucher);

	/**
	 * 删除付款申请
	 * @param paymentVoucher
	 */
	void delPaymentVoucher(PaymentVoucher paymentVoucher);

	/***
	 * 根据编号查询付款申请
	 * @param id
	 * @return
	 */
	
	PaymentVoucher findPaymentVoucherById(PaymentVoucher paymentVoucher);

	/***
	 * 根据主键id查询付款明细
	 * @param paymentVoucher
	 * @return
	 */
	PaymentDetail findPaymentDetailById(PaymentVoucher paymentVoucher);

	 /***
	  * 删除付款申请及明细
	  * @param paymentVoucher
	  * @param paymentDetail
	  */
	void delPaymentVoucher1(PaymentVoucher paymentVoucher,
			PaymentDetail paymentDetail);

	/***
	 * 根据编号修改付款申请
	 * @param paymentVoucher
	 */
	void updatePaymentVoucher(PaymentVoucher paymentVoucher);

	/***
	 * 提交付款申请
	 * @param voucher
	 */
	void addPaymentVoucher1(PaymentVoucher voucher,String paytag2);

	/***
	 * 审批付款申请
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	Object[] findExamList(int parseInt, int pageSize);

	/***
	 * 批量审批付款申请
	 * @param detailSelect
	 * @param tag
	 * @return
	 */
	boolean updateExamDetail(Integer[] detailSelect, String tag);

	/***
	 * 查看明细
	 * @param paymentVoucher
	 * @return
	 */
	Map<String, Object> findDetail(PaymentVoucher paymentVoucher);

	/***
	 * 查看预览
	 * @param paymentVoucher
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	Object[] findPaymentDetail1(PaymentDetail detail,PaymentVoucher paymentVoucher, int parseInt,
			int pageSize);

	/***
	 * 添加借款单和借款明细
	 * @param paymentVoucher
	 * @param paymentDetail
	 * @return
	 */
	boolean saveBorrowings(PaymentVoucher paymentVoucher,
			List<PaymentDetail> listpaymentDetail);

	/***
	 * 更新总的明细
	 * @param paymentVoucher
	 * @param listpaymentDetail
	 */
	void updateBorrowings(PaymentVoucher paymentVoucher,
			List<PaymentDetail> listpaymentDetail);

	/***
	 * 根据登陆用户查找对应的借款
	 * @param username
	 */
	List  findPayVoucherByname(String username);

	/***
	 * 查看借款审批对应审批节点人
	 * @param payId
	 * @return
	 */
	Map<Integer,Object> findPay_ExecutionNode(Integer payId);

	/****
	 * 发送验证码到RTX
	 */
	void send(String number,String nub);

	/**
	 * 更新打印状态
	 * @param payStatus
	 * @param payId1
	 */
	void updatePay_ExecutionNode(String payStatus, Integer payId1);

	/***
	 * 查询科目
	 * @return
	 */
	List<SubBudgetRate> findSubjectName();

	/***
	 * 查询报销单对应的借款
	 * @param baoxiaoId
	 * @return
	 */
	PaymentVoucher findPayVoucherByname1(String baoxiaoId);

	/***
	 * 财务确认
	 * @param paymentVoucher
	 */
	void updatePayment(PaymentVoucher paymentVoucher);

	/***
	 * 查询借款信息
	 * @param id
	 * @return
	 */
	PaymentVoucher findPaymentVoucher(int id);
  /***
   * 借款财务确认
   * @param paymentVoucher
   * @param parseInt
   * @param pageSize
   * @param test
   * @return
   */
	Object[] findPaymentVoucher2(PaymentVoucher paymentVoucher, int parseInt,
			int pageSize, String test);

 void updatePayment1(PaymentVoucher paymentVoucher);

 

}
