package com.task.Server.sop.muju;

import java.io.File;
import java.util.List;

import com.task.entity.Price;
import com.task.entity.sop.muju.MouldApplyOrder;
import com.task.entity.sop.muju.MouldDetail;

/**
 * 
 * @author 王晓飞
 *
 */
public interface MouldApplyOrderServer {

	/**
	 * 添加开模申请单
	 */
	public String addMoa(MouldApplyOrder moa,File[] attachment, String[] attachmentFileName,String [] otherNames);
	/**
	 * 查询开模申请单
	 */
	public Object[] findMoaList(MouldApplyOrder moa ,Integer pageNo ,Integer pageSize,String status);
	/**
	 * 根据Id查询申请单开模明细
	 */
	public Object[] findMoaAndmdList(Integer Id,Integer pgId);
	/**
	 * 根据Id查询开模申请单
	 */
	public MouldApplyOrder findMoaById(Integer id);
	/**
	 * 查询开模明细
	 */
	public Object[] findMdList(MouldDetail md ,Integer pageNo ,Integer pageSize,String status);
	/**
	 * 修改开模申请单
	 */
	public String updateMoa(MouldApplyOrder moa,File[] attachment, String[] attachmentFileName,String [] otherNames,String pageStatus);
	/**
	 * 删除开模申请单
	 */
	public String delMoa(MouldApplyOrder moa);
	/**
	 * 删除开模明细
	 */
	public String delmd(MouldDetail md);
	/**
	 * 查询最大申请单号
	 */
	String	findMaxNo();
	/**
	 * 查出所有已同意还没价格的申请单
	 */
	List<MouldApplyOrder> findmaoListNoPrice();
	/**
	 * 根据申请单号查询磨具申请单;
	 */
	MouldApplyOrder findMaoOne(String planNumber);
	/**
	 * 根据开模申请单号，查询价格;
	 */
	List<Price> findPriceByNo(String planNumber);
	/**
	 * 采购下单下发供应商(添加采购单)
	 */
	String caigou(MouldApplyOrder moa ,Integer id);
	/**
	 * 
	 * @param markIdS
	 * @return
	 */
	Integer getWtcNumber(String markIdS);
}
