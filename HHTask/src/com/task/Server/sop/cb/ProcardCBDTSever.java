package com.task.Server.sop.cb;

import java.util.List;

import com.task.entity.OrderManagement;
import com.task.entity.ProductManager;
import com.task.entity.bp.Product;
import com.task.entity.sop.Procard;
import com.task.entity.sop.cb.OrderCBDT;
import com.task.entity.sop.cb.ProcardCBDT;
import com.task.entity.sop.cb.ProductCBDT;

public interface ProcardCBDTSever {

	/**
	 * 更新成本动态;
	 */
	String updateCBDT(Integer procardId,Float money,String more);
	/**
	 * 	根据procardId查询单个procard的成本动态;
	 */
	List<ProcardCBDT> findProcardCBDT(Integer procardId);
	/**
	 * 	根据productId查询单个product的成本动态;
	 */
	List<ProductCBDT> findProductCBDT(Integer productId);
	/**根据orderId查询单个order的成本动态
	 * 
	 */
	List<OrderCBDT> findOrderCBDT(Integer orderId);
	/**
	 * 根据Id获取procard信息
	 */
	Procard getprocardById(Integer id);
	/**
	 * 根据procardId获得增加成本最大的一次
	 */
	Float findMaxaddcb(Integer id);
	/**
	 * 根据Product获得Product成本增加成本最大的一次
	 */
	Float findMaxaddcb1(Integer id);
	/**
	 * 根据orderId获得增加order成本最大的一次
	 */
	Float findMaxaddcb2(Integer id);
	/**
	 * 根据ProductId获得Product信息;
	 */
	ProductManager getProductById(Integer id);
	/**
	 * 根据Order获得Order信息;
	 */
	OrderManagement getOrderById(Integer id);
	
}
