package com.task.Server.sop;

import java.util.List;

import com.task.entity.sop.OutSourcingWorkList;

/**
 * 外委工作单server层
 * 
 * @author 贾辉辉
 * 
 */
public interface OSWorkServer {
	/**
	 * 查询流水卡片
	 * 
	 * @param OSWork
	 *            (外委工作单条件查询对象)
	 * @param startDate
	 *            (制卡开始时间)
	 * @param endDate
	 *            (制卡结束时间)
	 * @param cpage
	 *            (当前页)
	 * @param PageSize
	 *            (页显示条数)
	 * @return
	 */
	Object[] findOSWorkList(OutSourcingWorkList OSWork, String startDate,
			String endDate, Integer cpage, Integer PageSize,String tag);
	/**导出EXCEL**/
	void explorExcel(OutSourcingWorkList OSWork, String startDate,
			String endDate,String tag);
	/***
	 * 添加外委工作单
	 * 
	 * @param OSWork
	 * @return
	 */
	Object[] saveOsWork(OutSourcingWorkList oSWork, Integer[] processIds);
	/**查找下拉列表数据**/
	String findSelectList(String tag);
	/**根据ID获取外委工作单对象**/
	OutSourcingWorkList getOSWorkByID(Integer id,String tag);
	/**根据条码查找外委工作单对象**/
	OutSourcingWorkList getOSWorkByByBarcode(String barcode,String tag);
	/**接收外委工作单**/
	String updateReceiveOSW(OutSourcingWorkList OSWork,String tag);
	/**财务待结算的历史记录**/
	List findJiesuanList();
	/**外委计算**/
	Object[] JIesuan(Integer[] osjisuan);
	/***结算后状态处理**/
	boolean updateJIesuan(Integer[] osjisuan);
	/**删除记录**/
	boolean deleteOSW(Integer id);
	
	void addLogisticker(OutSourcingWorkList OSWork);
	void addOswGongxu(OutSourcingWorkList OSWork,int[] waigouWaiweiPlanIds);
	List listwaigouWaiweiPlanId(Integer id, int[] waigouWaiweiPlanIds);
	Object[] addOswGongxu(OutSourcingWorkList osWork, Integer[] processIds,String WaigouWaiweiPlanId);
	List listwaigouWaiweiPlanId(String waigouWaiweiPlanId);
	List listaddOswGongxu(Integer[] processIds);
	/**
	 * 通过外委单获取工序列表
	 * @param id
	 * @return
	 */
	List getProcessListByOswId(Integer id);

}
