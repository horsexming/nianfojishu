package com.task.Server.gzbj;

import java.io.File;
import java.util.List;

import com.task.entity.Scrap;
import com.task.entity.gzbj.Checkrecord;
import com.task.entity.gzbj.Measuring;
import com.task.entity.caiwu.baobiao.*;
import com.tast.entity.zhaobiao.ZhUser;

public interface MeasuringServer {

	Object[] saveMeasuring(Measuring measuring, int pageNo, int pageSize,String status,String tag);
	public List getMeasuringList( ) ;
	Measuring findMeasuringById(Integer measuringId);

	void updateMeasuringById(Measuring measuring);

	List<Measuring> updatedMeasuring(String status,Measuring measuring,String tag);//待校检

	List<Measuring> findzMeasuring(String status,Measuring measuring,String tag);//校检中

	List<Measuring> findbMeasuring(String status,Measuring measuring,String tag);//报废

	void updateandsaveMeasuring(Measuring measuring, String empname,String empno,String fileName);//更新量具状态及添加报检记录

	Checkrecord findCheckrecordById(Integer measuringId);

	void updateandsaveMeasuring1(Measuring measuring, String empname,String empno,String lasttime,Integer period,String fileName) throws Exception;

	Measuring findMeasuringdetail(Integer measuringId);//查看量具明细

	void updateMeasuringandstoreById(Integer MeasuringId,Float num,String empno);

	List<Checkrecord> findCheckcorddetail(Integer measuringId);
	Object[] saveMeasuring1(Measuring measuring, int pageNo, int pageSize);
	List<Scrap> findScrapdetail(Integer measuringId);
	void updateandsaveMeasuring2(Measuring measuring);
	/**
	 * 添加
	 * @param measuring
	 * @return
	 */
	String addmeasuring(Measuring measuring);
	/**
	 * 导入
	 * @param measuringfile
	 * @return
	 */
	String daorumeasuring(File measuringfile);
	/**
	 * 导出
	 */
	public void exportExcel(Measuring measuring);
	
	boolean delmeasuring(Measuring measuring);
	void sendmsgjiaoyan();
	
	void caiwujisun(String months);
	/**
	 * 判断编号是不是唯一编号
	 */
	boolean  isOneMeasuring_no(String measuring_no);
	/**
	 * 添加AccountsDate
	 */
	boolean addAccountsDate(AccountsDate accountsDate);
	/**
	 * 查询AccountsDate
	 */
	List<AccountsDate>  FindAccountsDate();
	/**
	 * 删除AccountsDate
	 */
	boolean delAccountsDate(AccountsDate accountsDate);
	/**
	 * 查询所有检验明细
	 */
	Object[] findALlCheckrecord(Checkrecord checkrecord ,int pageNo, int pageSize,String tag);
	Measuring getMeaById(Integer id);
}
