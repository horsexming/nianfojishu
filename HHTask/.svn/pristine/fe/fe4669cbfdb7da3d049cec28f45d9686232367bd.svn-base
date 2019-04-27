package com.task.Server.payment;

import java.util.List;
import java.util.Map;

import com.task.entity.payment.PaymentDetail;
import com.task.entity.payment.PaymentVoucher;

public interface PaymentDetailServer {

	/***
	 * 查看付款明细
	 * @param detail
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	Object[] findPaymentVoucher(PaymentDetail detail, int parseInt, int pageSize);

	/***
	 * 查询合同编号
	 * @return
	 */
	List<Map> findNumber();

	/***
	 * 添加明细
	 * @return
	 */
	boolean addPaymentDetail(PaymentDetail detail);

	/***
	 * 删除付款明细
	 * @param detail
	 */
	void delPaymentDetail(PaymentDetail detail);

	/***
	 * 根据编号查询付款明细
	 * @param detail
	 * @return
	 */
	PaymentDetail findPaymentDetailById(PaymentDetail detail);

	/***
	 * 根据外键查找合同编号
	 * @param paymentid
	 * @return
	 */
	List<Map> findNumber1(Integer paymentid);

	/***
	 * 更新付款明细
	 * @param detail
	 */
	void updatePaymentVoucher(PaymentDetail detail);

	/***
	 * 根据外键查询收款单位
	 * @param paymentid
	 * @return
	 */
	List<Map> findUnitname(Integer paymentid);

	/***
	 * 查看明细
	 * @param detail
	 */
	Map<String,Object> findDetail(PaymentVoucher paymentVoucher);
	

}
