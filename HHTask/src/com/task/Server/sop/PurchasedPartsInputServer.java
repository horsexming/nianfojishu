package com.task.Server.sop;

import java.util.List;
import java.util.Map;

import com.task.entity.sop.PurchasedPartsInput;
import com.task.entity.sop.WaigouWaiweiPlan;

public interface PurchasedPartsInputServer {
    /**
     * 外购件批量入库
     * @param wwPlanList
     * @return
     */
	String add(List<WaigouWaiweiPlan> wwPlanList,String acArrivalTime);
   /**
    * 分页获取外购件入库申请列表
    * @param purchasedPartsInput
    * @param parseInt
    * @param pageSize
    * @return
    */
	Map<Integer, Object> findPurchasedPartsInputsByCondition(
			PurchasedPartsInput purchasedPartsInput, int parseInt, int pageSize);
	/**
	 * 通过id获取对象
	 * @param id
	 * @return
	 */
    PurchasedPartsInput getById(Integer id);
    /**
     * 修改入库申请数量
     * @param purchasedPartsInput
     * @return
     */
	boolean updateCount(PurchasedPartsInput purchasedPartsInput);
	/**
	 * 删除入库申请记录
	 * @param purchasedPartsInput
	 * @return
	 */
	boolean delete(PurchasedPartsInput purchasedPartsInput);

}
