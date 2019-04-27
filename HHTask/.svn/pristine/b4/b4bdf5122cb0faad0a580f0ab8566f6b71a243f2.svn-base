package com.task.ServerImpl.sop.cb;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.sop.cb.ProcardCBDTSever;
import com.task.entity.OrderManagement;
import com.task.entity.ProductManager;
import com.task.entity.bp.Product;
import com.task.entity.sop.Procard;
import com.task.entity.sop.cb.OrderCBDT;
import com.task.entity.sop.cb.ProcardCBDT;
import com.task.entity.sop.cb.ProductCBDT;
import com.task.util.Util;

public class ProcardCBDTSeverImpl implements ProcardCBDTSever{
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	
	@Override
	public String updateCBDT(Integer procardId, Float money, String more) {
		
		if(procardId!=null && money!=null && procardId>0 && money>0){
			Integer  p_cbdt = (Integer) totalDao.getObjectByCondition(" select sum(sumCB) from ProcardCBDT where pId = ?", procardId);
			/*
			 *第一次产生某个procard的成本动态
			 */
			ProcardCBDT procardcbdt = new ProcardCBDT();
			if(p_cbdt == null || p_cbdt == 0){
				procardcbdt.setAddCB(money);
				procardcbdt.setpId(procardId);
				procardcbdt.setMore(more);
				procardcbdt.setSumCB(money);
				procardcbdt.setTimeCB(Util.getDateTime());
				totalDao.save(procardcbdt);
				Procard procard =	(Procard) totalDao.get(Procard.class, procardId);
				//同步更新product成本动态;
				String hql_pm = " from ProductManager where orderManager.id = ? and pieceNumber = ?";
				ProductManager pm = (ProductManager) totalDao.getObjectByCondition(hql_pm,procard.getOrderId(),procard.getMarkId());
				if(pm!=null){
					String hql_sumcb = " select sum(sumCB) from productcbdt where pId = ?";
					Integer pm_cbdt =	(Integer) totalDao.getObjectByCondition(hql_sumcb, pm.getId());
					//第一次产生该件号的产品的成本动态
					ProductCBDT productcbdt	 = new ProductCBDT();
					if(pm_cbdt == null || pm_cbdt == 0){
						productcbdt = new ProductCBDT();
						productcbdt.setAddCB(money);
						productcbdt.setpId(pm.getId());
						productcbdt.setMore(more);
						productcbdt.setSumCB(money);
						productcbdt.setTimeCB(Util.getDateTime());
						totalDao.save(productcbdt);
					}else{
						productcbdt.setAddCB(money);
						productcbdt.setSumCB(money+pm_cbdt);
						productcbdt.setpId(pm.getId());
						productcbdt.setMore(more);
						productcbdt.setTimeCB(Util.getDateTime());
						totalDao.save(productcbdt);
					}
				}
				//同步跟新订单的成本动态情况;
				OrderManagement order =	(OrderManagement) totalDao.get(OrderManagement.class, procard.getOrderId());
				Integer o_cbdt = (Integer) totalDao.getObjectByCondition(" from OrderCBDT where oId = ?", order.getId());
				OrderCBDT ordercbdt = new OrderCBDT();
				if(o_cbdt == null && o_cbdt == 0){
					ordercbdt.setAddCB(money);
					ordercbdt.setMore(more);
					ordercbdt.setSumCB(money);
					ordercbdt.setoId(order.getId());
					ordercbdt.setTimeCB(Util.getDateTime());
					totalDao.save(ordercbdt);
				}else{
					ordercbdt.setAddCB(money);
					ordercbdt.setMore(more);
					ordercbdt.setSumCB(money+o_cbdt);
					ordercbdt.setoId(order.getId());
					ordercbdt.setTimeCB(Util.getDateTime());
					totalDao.save(ordercbdt);
				}
			}else{
				procardcbdt.setAddCB(money);
				procardcbdt.setMore(more);
				procardcbdt.setSumCB(p_cbdt+money);
				procardcbdt.setpId(procardId);
				procardcbdt.setTimeCB(Util.getDateTime());
				totalDao.save(procardcbdt);
				Procard procard =	(Procard) totalDao.get(Procard.class, procardId);
				//同步更新product成本动态;
				String hql_pm = " from ProductManager where orderManager.id = ? and pieceNumber = ?";
				ProductManager pm = (ProductManager) totalDao.getObjectByCondition(hql_pm,procard.getOrderId(),procard.getMarkId());
				if(pm!=null){
					String hql_sumcb = " select sum(sumCB) from productcbdt where pId = ?";
					Integer pm_cbdt =	(Integer) totalDao.getObjectByCondition(hql_sumcb, pm.getId());
					ProductCBDT productcbdt	 = new ProductCBDT();
					productcbdt.setAddCB(money);
					productcbdt.setSumCB(money+pm_cbdt);
					productcbdt.setpId(pm.getId());
					productcbdt.setMore(more);
					productcbdt.setTimeCB(Util.getDateTime());
					totalDao.save(productcbdt);
				}
				//同步跟新订单的成本动态情况;
				OrderManagement order =	(OrderManagement) totalDao.get(OrderManagement.class, procard.getOrderId());
				Integer o_cbdt = (Integer) totalDao.getObjectByCondition(" from OrderCBDT where oId = ?", order.getId());
				OrderCBDT ordercbdt = new OrderCBDT();
				ordercbdt.setAddCB(money);
				ordercbdt.setMore(more);
				ordercbdt.setSumCB(money+o_cbdt);
				ordercbdt.setoId(order.getId());
				ordercbdt.setTimeCB(Util.getDateTime());
				totalDao.save(ordercbdt);
				
			}
			
			
		}
		return null;
	}

	@Override
	public List<ProcardCBDT> findProcardCBDT(Integer procardId) {
		if(procardId!=null && procardId>0){
			return totalDao.query(" from ProcardCBDT where pId =? ", procardId);
		}
		return null;
	}

	@Override
	public List<ProductCBDT> findProductCBDT(Integer productId) {
		if(productId!=null && productId>0){
			return totalDao.query(" from ProductCBDT where pId =? ", productId);
		}
		return null;
	}

	@Override
	public List<OrderCBDT> findOrderCBDT(Integer orderId) {
		if(orderId!=null && orderId>0){
			return totalDao.query(" from OrderCBDT where oId   =? ", orderId);
		}
		return null;
	}

	@Override
	public Procard getprocardById(Integer id) {
		if(id!=null && id>0){
			return (Procard) totalDao.get(Procard.class, id);
		}
		return null;
	}
	
	@Override
	public Float findMaxaddcb(Integer id) {
		if(id!=null && id>0){
			return 	(Float) totalDao.getObjectByCondition(" select max(addCB) from  ProcardCBDT where pId =?", id);
		} 
		return null;
	}

	@Override
	public Float findMaxaddcb1(Integer id) {
		if(id!=null && id>0){
			return 	(Float) totalDao.getObjectByCondition(" select max(addCB) from  ProductCBDT where pId =?", id);
		}
		return null;
	}

	@Override
	public Float findMaxaddcb2(Integer id) {
		if(id!=null && id>0){
			return 	(Float) totalDao.getObjectByCondition(" select max(addCB) from  OrderCBDT where oId =?", id);
		}
		return null;
	}

	@Override
	public OrderManagement getOrderById(Integer id) {
		if(id!=null && id>0){
			return (OrderManagement) totalDao.get(OrderManagement.class, id);
		}
		return null;
	}

	@Override
	public ProductManager getProductById(Integer id) {
		if(id!=null && id>0){
			return (ProductManager) totalDao.get(ProductManager.class, id);
		}
		return null;
	}
	
	
	
}	
