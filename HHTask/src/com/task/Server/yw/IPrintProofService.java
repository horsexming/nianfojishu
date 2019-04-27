package com.task.Server.yw;

import com.task.entity.PrintProof;

/**
 * @Title: IPrintProof.java
 * @Package com.task.Server.yw
 * @Description: 付款凭证接口
 * @author 曾建森
 * @date 2012-11-8 下午04:13:14
 * @version V1.0
 */
public interface IPrintProofService {
	/**
	 * @Title: add
	 * @Description: 添加付款凭证
	 * @param @param p 
	 * @return void
	 * @throws
	 */
	public void add(PrintProof p);
	/**
	 * @Title: update
	 * @Description: 修改付款凭证
	 * @param @param p 
	 * @return void
	 * @throws
	 */
	public void update(PrintProof p );
	/**
	 * @Title: getPrintProofById
	 * @Description: 根据ID查找付款凭证
	 * @param @param id
	 * @param @return 
	 * @return PrintProof
	 * @throws
	 */
	public PrintProof getPrintProofById(int id);
	/**
	 * @Title: queryAllPrintProof
	 * @Description: 查询所有总经理审核的付款凭证
	 * @param @param pageNo
	 * @param @param pageSize
	 * @return Object[]
	 * @throws
	 */
	public Object[] queryAllPrintProof(int pageNo,int pageSize);
	/**
	 * @Title: queryAgreeProof
	 * @Description: 查询所有审核通过的付款凭证
	 * @param @param pageNo
	 * @param @param pageSize
	 * @return Object[]
	 * @throws
	 */
	public Object[] queryAgreeProof(int pageNo,int pageSize);
	/**
	 * @Title: queryGoBackProof
	 * @Description: 查询所有审核不通过的付款凭证
	 * @param @param pageNo
	 * @param @param pageSize
	 * @return Object[]
	 * @throws
	 */
	public Object[] queryGoBackProof(int pageNo,int pageSize);
	/**
	 * @Title: getDeptNumber
	 * @Description: 返回编号
	 * @return String
	 * @throws
	 */
	public String getDeptNumber(String deptId);
}
