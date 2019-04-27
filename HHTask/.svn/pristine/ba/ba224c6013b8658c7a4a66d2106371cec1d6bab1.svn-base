package com.task.ServerImpl.yw;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.yw.IBusinessService;
import com.task.Server.yw.IInvoiceService;
import com.task.entity.Business;
import com.task.entity.Invoice;

/**
 * @Title: InvoiceServiceImpl.java
 * @Package com.task.ServerImpl.yw
 * @Description: TODO 发票实现类
 * @author 曾建森
 * @date 2012-10-30 上午11:16:52
 * @version V1.0
 */
public class InvoiceServiceImpl implements IInvoiceService {
	
	private TotalDao totalDao;
	
	private IBusinessService bus;
	
	/**
	 * @Title: add
	 * @Description: 添加发票记录
	 * @param bu 
	 * @return void
	 * @throws
	 */
	public void add(Invoice in) {
		totalDao.save(in);
	}
	/**
	 * @Title: addInvoiceAndUpdateBusinessMoney
	 * @Description: 添加发票记录,同时修改相应的业务金额
	 * @param id 业务ID
	 * @param in 发票对象
	 * @throws
	 */
	public boolean addInvoiceAndUpdateBusinessMoney(int id,Invoice in){
		Business bu = bus.getBusinessById(id);
		bu.setMoney(bu.getMoney()+in.getMoney());
		bu.setCurrencyType(in.getCurrencyType());
		bus.update(bu);
		in.setBusiness(bu);
		return totalDao.save(in);
	}
	/**
	 * @Title: delById
	 * @Description: 根据ID删除指定记录,同时更改相应的业务金额
	 * @param id 
	 * @return void
	 * @throws
	 */
	public void delById(int id) {
		Invoice invoice = (Invoice) totalDao.getObjectById(Invoice.class, id);
		Business bu = bus.getBusinessById(invoice.getBusiness().getId());
		bu.setMoney(bu.getMoney()-invoice.getMoney());
		bus.update(bu);
		totalDao.delete(invoice);
	}
	/**
	 * @Title: getInvoiceById
	 * @Description: 根据ID查询发票
	 * @param  id
	 * @return Business
	 * @throws
	 */
	public Invoice getInvoiceById(int id) {
		return (Invoice) totalDao.getObjectById(Invoice.class, id);
	}
	/**
	 * @Title: update
	 * @Description: 修改记录,同时更改相应的业务金额
	 * @param bu 
	 * @return void
	 * @throws
	 */
	public void update(Invoice in) {
		Invoice invoice = getInvoiceById(in.getId());
		Business bu = bus.getBusinessById(invoice.getBusiness().getId());
		bu.setMoney(bu.getMoney()-invoice.getMoney());
		bu.setMoney(bu.getMoney()+in.getMoney());
		bu.setCurrencyType(in.getCurrencyType());
		bus.update(bu);
		in.setBusiness(bu);
		totalDao.update(in);
	}
	/**
	 * @Title: queryInvoiceByBusinessById
	 * @Description: 根据业务ID查询发票
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @return Object[]
	 * @throws
	 */
	public Object[] queryInvoiceByBusinessId(int id, int pageNo, int pageSize) {
		String hql ="from Invoice i where i.business.id = ?";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize,id);
		int count = totalDao.getCount(hql,id);
		return new Object[]{list,count};
	}
	
	
	public TotalDao getTotalDao() {
		return totalDao;
	}
	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	public IBusinessService getBus() {
		return bus;
	}
	public void setBus(IBusinessService bus) {
		this.bus = bus;
	}


}
