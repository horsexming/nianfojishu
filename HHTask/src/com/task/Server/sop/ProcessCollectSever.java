package com.task.Server.sop;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.task.entity.Screen;
import com.task.entity.Sell;
import com.task.entity.gzbj.ProcessGzstore;
import com.task.entity.sop.ProcessCollect;

public interface ProcessCollectSever {
	/**
	 * 查询所有工序汇总信息;
	 */
	public List<ProcessCollect> findAllPcList(int pageNo ,int pageSize );
	/**
	 *条件查询工序汇总信息;车间名称
	 */
	public Map<Integer, Object> findPcCondition(ProcessCollect pc, int pageNo,
			int pageSize,String startDate,String endDate,String workshop,String productStyle_str);
	/**
	 *条件查询工序汇总信息;车间表
	 */
	public Map<Integer, Object> findPcCondition(ProcessCollect pc, int pageNo,
			int pageSize,String startDate,String endDate,Screen screen,String productStyle_str);
	 /**
	  * 导出工序工序汇总信息;
	  */
	 public String exportExcel(ProcessCollect pc);
	int getcont();
	/**
	 *导出
	 * 
	 */
	public void explorExcel(ProcessCollect pc, String startDate,
			String endDate, String status,String workShop,String productStyle_str);
	/**
	 * 导出汇总
	 */
	public void explorExcel1(ProcessCollect pc, String startDate, String endDate,String status,String workShop);



	/*
	    * @author fy
	　　* @date 2018/6/14 9:17
	　　* @Description: 工序导出  （导出）
	　　* @param [pc, startDate, endDate, status, workShop, productStyle_str]
	　　* @return void
	　　* @throws
	　　*/
    void explorExcelbyPoi(ProcessCollect pc, String startDate,
                          String endDate, String status, String workShop, String productStyle_str);

    /*
		* @author fy
	  * @date 2018/6/13 11:23
	  * @Description: 工序导出  （汇总导出）
	  * @param [pc, startDate, endDate, status, workShop]
	  * @return java.lang.String
	  * @throws
	  */
	String explorExcelbyPoi(ProcessCollect pc, String startDate,
							String endDate, String status, String workShop);

	/*
	 * 查询所有车间
	 */
	public List<String[]> findScreen();
	/**
	 * 查询所有非外委工序
	 * @return
	 */
	List<String> findAllProcessGzstore();
	
			
}
