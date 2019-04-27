package com.task.ServerImpl.payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.task.Dao.TotalDao;
import com.task.Server.payment.PaymentDetailServer;
import com.task.entity.Users;
import com.task.entity.payment.PaymentDetail;
import com.task.entity.payment.PaymentVoucher;
import com.task.util.Util;

public class PaymentDetailServerImpl implements PaymentDetailServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/*
	 * 查看付款明细(non-Javadoc)
	 * @see com.task.Server.payment.PaymentDetailServer#findPaymentVoucher(com.task.entity.payment.PaymentDetail, int, int)
	 */
	@Override
	public Object[] findPaymentVoucher(PaymentDetail detail, int parseInt,
			int pageSize) {
		if (detail == null) {
			detail = new PaymentDetail();
		}
		String hql = totalDao.criteriaQueries(detail, null);
		if (detail.getOrders_num()!= null
				&& !"".equals(detail.getOrders_num())) {
			hql += " and orders_num=" + detail.getOrders_num();
		}
		if (detail.getReceipt_num()!= null
				&& !"".equals(detail.getReceipt_num())) {
			hql += " and receipt_num=" + detail.getReceipt_num();
		}
		if (detail.getAskrequisition_num() != null
				&& !"".equals(detail.getAskrequisition_num())) {
			hql += " and askrequisition_num=" + detail.getAskrequisition_num();
		}
		if (detail.getBorrowerlist_num() != null
				&& !"".equals(detail.getBorrowerlist_num())) {
			hql += " and borrowerlist_num='" + detail.getBorrowerlist_num()+ "'";
		}
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/*
	 * 查询合同编号(non-Javadoc)
	 * @see com.task.Server.payment.PaymentDetailServer#findNumber()
	 */
	@Override
	public List<Map> findNumber() {
		// TODO Auto-generated method stub
		String sql = "select  number,id from ta_PaymentVoucher";
		List<Map> maps = this.totalDao.findBySql(sql.trim());
		return maps;
	}

	/*
	 * 添加明细(non-Javadoc)
	 * @see com.task.Server.payment.PaymentDetailServer#addPaymentDetail()
	 */
	@Override
	public boolean addPaymentDetail(PaymentDetail detail) {
		// TODO Auto-generated method stub
		PaymentVoucher paymentVoucher = (PaymentVoucher) totalDao.getObjectById(PaymentVoucher.class, detail.getPaymentid1());
		detail.setVoucher(paymentVoucher);
		String hql = "select sum(voucherMoney) voucherMoney1 from ta_PaymentDetail where paymentid1="+detail.getPaymentid1();
		List<Map> maps = this.totalDao.findBySql(hql);
		Map map = (Map) maps.get(0);
//		Set<String> keys=map.keySet();
//		if(keys!=null&&keys.size()>0){
//			for(String s:keys){
//				System.out.println(s);
//			}
//		}
		if(map.get("voucherMoney1")!=null){
		Float voucherMoney3 = Float.parseFloat(map.get("voucherMoney1").toString());
			paymentVoucher.setVoucherMoney(voucherMoney3+detail.getVoucherMoney());
		}else{
			paymentVoucher.setVoucherMoney(detail.getVoucherMoney());
		}
		detail.setNumber1(detail.getNumber1());
		detail.setUnitname1(detail.getUnitname1());
//		detail.setVoucherbasis(detail.getVoucherbasis());
		this.totalDao.update(paymentVoucher);
//		Set<PaymentDetail> set=paymentVoucher.getPaymentDetail();
//		set.add(detail);
		return this.totalDao.save(detail);
	}

	/*
	 * 删除付款明细(non-Javadoc)
	 * @see com.task.Server.payment.PaymentDetailServer#delPaymentDetail(com.task.entity.payment.PaymentDetail)
	 */
	@Override
	public void delPaymentDetail(PaymentDetail detail) {
		PaymentDetail detail2 = (PaymentDetail) this.totalDao.getObjectById(PaymentDetail.class, detail.getId());
		PaymentVoucher paymentVoucher =(PaymentVoucher) this.totalDao.getObjectById(PaymentVoucher.class, detail2.getPaymentid1());
		Float f=paymentVoucher.getVoucherMoney();
		if(paymentVoucher==null){
			paymentVoucher = new PaymentVoucher(); 
		} 
		paymentVoucher.setVoucherMoney(f-detail2.getVoucherMoney());
		this.totalDao.update(paymentVoucher);//更新主表金额
		this.totalDao.delete(detail2);
		
	}

	/*
	 * 根据编号查询付款明细(non-Javadoc)
	 * @see com.task.Server.payment.PaymentDetailServer#findPaymentDetailById(com.task.entity.payment.PaymentDetail)
	 */
	@Override
	public PaymentDetail findPaymentDetailById(PaymentDetail detail) {
		// TODO Auto-generated method stub
		PaymentDetail paymentDetail = (PaymentDetail) this.totalDao.getObjectById(PaymentDetail.class,detail.getId() );
		return paymentDetail;
	}

	/*
	 * 根据外键查找合同编号(non-Javadoc)
	 * @see com.task.Server.payment.PaymentDetailServer#findNumber1(java.lang.Integer)
	 */
	@Override
	public List<Map> findNumber1(Integer paymentid) {
		String sql = "select  number,unitname from ta_PaymentVoucher where id="+paymentid;
		List<Map> maps = this.totalDao.findBySql(sql);
		return maps;
	}

	/*
	 * 更新付款明细(non-Javadoc)
	 * @see com.task.Server.payment.PaymentDetailServer#updatePaymentVoucher(com.task.entity.payment.PaymentDetail)
	 */
	@Override
	public void updatePaymentVoucher(PaymentDetail detail) {
		PaymentDetail paymentDetail = (PaymentDetail) totalDao.getObjectById(PaymentDetail.class, detail.getId());//拿到老的数据
		PaymentVoucher paymentVoucher = (PaymentVoucher) totalDao.getObjectById(PaymentVoucher.class, paymentDetail.getPaymentid1());//拿到主表的数据
		paymentDetail.setVoucher(paymentVoucher);
		String hql = "select sum(voucherMoney) voucherMoney1 from ta_PaymentDetail where paymentid1="+detail.getPaymentid1();
		List<Map> maps = this.totalDao.findBySql(hql);
		Map map = (Map) maps.get(0);
		if(map.get("voucherMoney1")!=null){
			Float voucherMoney3 = Float.parseFloat(map.get("voucherMoney1").toString());//拿到总金额
			Float voucherMoney4 = voucherMoney3 - paymentDetail.getVoucherMoney();
			paymentVoucher.setVoucherMoney(voucherMoney4+detail.getVoucherMoney());
		}else{
			paymentVoucher.setVoucherMoney(detail.getVoucherMoney());
		}
		this.totalDao.update1(detail);
		this.totalDao.update(paymentVoucher);
	
	}

	/*
	 * 根据外键查询收款单位(non-Javadoc)
	 * @see com.task.Server.payment.PaymentDetailServer#findUnitname(java.lang.Integer)
	 */
	@Override
	public List<Map> findUnitname(Integer paymentid) {
		String sql = "select  unitname from ta_PaymentVoucher where id="+paymentid;
		List<Map> maps = this.totalDao.findBySql(sql);
		return maps;
	}

	/*
	 * 查看明细(non-Javadoc)
	 * @see com.task.Server.payment.PaymentDetailServer#findDetail(com.task.entity.payment.PaymentDetail)
	 */
	@Override
	public Map<String,Object> findDetail(PaymentVoucher paymentVoucher) {
		Map<String,Object> map=new HashMap();
		PaymentVoucher voucher =  (PaymentVoucher) this.totalDao.getObjectById(PaymentVoucher.class, paymentVoucher.getId());
		String hql = "from PaymentDetail where paymentid1="+voucher.getId();
		List list =  this.totalDao.query(hql);
		List<PaymentDetail> list1 = new ArrayList<PaymentDetail>();
		for (int i = 0; i < list.size(); i++) {
			PaymentDetail detail2 = (PaymentDetail)list.get(i);
			PaymentDetail detail3 = (PaymentDetail) this.totalDao.getObjectById(PaymentDetail.class, detail2.getId());
			list1.add(detail3);
		}
		//PaymentVoucher voucher = (PaymentVoucher) this.totalDao.getObjectById(PaymentVoucher.class, detail2.getPaymentid1());
         map.put("list", list1);
         map.put("voucher2", voucher);
		return map;
	}
	
	 

}
