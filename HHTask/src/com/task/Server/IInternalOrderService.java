package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.InternalOrder;
import com.task.entity.InternalOrderDetail;
import com.task.entity.bp.Templet;

public interface IInternalOrderService {
	public void add(InternalOrder io);

	public void delInternalObjectById(int id);

	public void del(InternalOrder io);

	public boolean update(InternalOrder io);

	public InternalOrder getInternalOrderById(int id);

	public void conversion(int id);

	public Object[] queryAll(int pageNo, int pageSize, String pageStatus);

	public Object[] queryInternalOrderByCondition(Map map, int pageNo,
			int pageSize, String pageStatus);

	public Object[] integrationOrderDetail(int[] selected);

	public void batchConversionOrder(String[] pieceNum, Float[] num,
			String[] remark, String orderIdStr, String monthStr, String tag);

	public Object[] queryOrderManagerByinnerOrderId(int innelOrderId);

	public boolean auditOrder(int id, boolean ifAgree, String pageStatus);

	public String validateNum(String ids, String pieceNum, int num,
			String ioIdStr);

	public Object[] queryExaminePass(Map map, int pageNo, int pageSize);

	public String countPurchaseAmount(int[] select);

	public Templet queryTempletByPieceNumber(String pieceNumber);

	public String delById(int id);

	public void validateComplete();

	/***
	 * 查询所有已同意的内部生产计划(用于生成流水卡片)
	 * 
	 * @param internalOrder
	 * @param pageNo
	 * @param pageSize
	 * @author 刘培
	 * @return Object[]
	 */
	Object[] findAllAgreeOrder(InternalOrder internalOrder, int pageNo,
			int pageSize, String status);

	public List<InternalOrder> findNoStatus(InternalOrder internalOrder,
			String status);

	/****
	 * 要货计划直接转换为生产计划
	 * 
	 * @param orderId
	 * @param iodList
	 * @param tag
	 */
	void yaohuoPlanForIod(Integer orderId, List<InternalOrderDetail> iodList,
			String tag);

	/***
	 * 根据订单明细产品id查找生产计划
	 * 
	 * @param productId
	 * @return
	 */
	List findIodByProductId(Integer productId);

	/***
	 * 查询所有生产计划
	 * 
	 * @param iod
	 * @param pageStatus
	 * @return
	 */
	List findAllIod(InternalOrderDetail iod, String pageStatus);
}
