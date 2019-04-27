package com.task.Server.bybz;

import java.util.List;

import com.task.entity.Users;
import com.task.entity.bybz.BaoYangBiaoZhun;
import com.task.entity.bybz.CheckoutAndGages;
import com.task.entity.bybz.LjuCheckRecord;
import com.task.entity.bybz.LjuCheckRecordMx;

public interface CheckoutAndGagesServer {

	/**
	 *添加量、检具 
	 * @param cag
	 * @return
	 */
	public String addCheckoutAndGage(CheckoutAndGages cag);
	/**
	 * 分页查询所有未超出校验期的量、检具
	 * @param cag
	 * @param pageNo
	 * @param pageSize
	 * @param status
	 * @return
	 */
	public Object[] findAllcagList(CheckoutAndGages cag ,int pageNo, int pageSize ,String status);
	/**
	 * 删除量、检具
	 * @param cag
	 * @return
	 */
	public String delCheckoutAndGage(CheckoutAndGages cag);
	/**
	 * 修改量、检具
	 * @param cag
	 * @return
	 */
	 public String updateCag(CheckoutAndGages cag);
	
	/**
	 * 根据量、检具Id查询出所有相对应的校验项
	 */
	public List<BaoYangBiaoZhun> findbybzListBycagId(Integer id);
	/**
	 * 根据Id查询量、检具
	 */
	public CheckoutAndGages findCagById(Integer id);
	
	/**
	 * 根据校验记录Id查询出所有相对应的校验明细
	 */
	public List<LjuCheckRecordMx> findLcrMxList(Integer id);
	/**
	 * 根据Id查询校验记录
	 */
	public LjuCheckRecord findLcrById(Integer id);
	/**
	 * 条件（分页）查询出所有校验记录
	 */
	public Object[] findAllLcrList(LjuCheckRecord lcr,int pageNo, int pageSize ,String status);
	/**
	 * 量、检具校验;
	 */
	public String jYCag(LjuCheckRecord lcr);
	/**
	 * 根据工号查询Users
	 */
	public Users findUsersByCode(String code);
	
	/**
	 * 查询所有量、检具校验记录
	 */
	public Object[] findLcrList(LjuCheckRecord lcr ,int pageNo, int pageSize ,String status);
	
	
	
	
}
