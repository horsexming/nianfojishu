package com.task.Server.yw;

import com.task.entity.Invoice;

/**
 * @Title: IInvoiceService.java
 * @Package com.task.Server.yw
 * @Description: TODO 发票
 * @author 曾建森
 * @date 2012-10-30 上午11:13:18
 * @version V1.0
 */
public interface IInvoiceService {
	/**
	 * @Title: add
	 * @Description: 添加发票记录
	 * @param bu 
	 * @return void
	 * @throws
	 */
	public void add(Invoice in);
	/**
	 * @Title: addInvoiceAndUpdateBusinessMoney
	 * @Description: 添加发票记录,同时修改相应的业务金额
	 * @param id 业务ID
	 * @param in 发票对象
	 * @throws
	 */
	public boolean addInvoiceAndUpdateBusinessMoney(int id,Invoice in);
	/**
	 * @Title: delById
	 * @Description: 根据ID删除指定记录
	 * @param id 
	 * @return void
	 * @throws
	 */
	public void delById(int id);
	/**
	 * @Title: update
	 * @Description: 修改记录
	 * @param bu 
	 * @return void
	 * @throws
	 */
	public void update(Invoice in);
	/**
	 * @Title: getInvoiceById
	 * @Description: 根据ID查询发票
	 * @param  id
	 * @return Business
	 * @throws
	 */
	public Invoice getInvoiceById(int id);
	/**
	 * @Title: queryInvoiceByBusinessById
	 * @Description: 根据业务ID查询发票
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @return Object[]
	 * @throws
	 */
	public Object[] queryInvoiceByBusinessId(int id,int pageNo,int pageSize);
}
