/**
 * 
 */
package com.task.Server.b2b;

import java.io.IOException;

import com.huawei.openapi.openaipexample.client.http.OrderForB2B;

public interface HttpRestClientServer {

	/***
	 * 查询PO列表
	 * 
	 * @param colTaskOrPoStatus
	 * @param pageIndex
	 * @param pageSize
	 * @param pageOrderForB2B
	 * @param fbStartDate
	 * @param fbEndDate
	 * @param cnStartDate
	 * @param cnEndDate
	 * @return
	 * @throws IOException
	 * @throws IOException
	 */
	public Object[] getList(Integer pageIndex, Integer pageSize,
			OrderForB2B pageOrderForB2B, String fbStartDate, String fbEndDate,
			String cnStartDate, String cnEndDate) throws IOException;

	/****
	 * 通过id获得PO明细
	 * 
	 * @param pageOrderForB2B
	 * @return
	 * @throws IOException
	 */
	OrderForB2B findPOById(OrderForB2B pageOrderForB2B);

	/****
	 * 接受/遣返订单
	 * 
	 * @param pageOrderForB2B
	 * @param operateType
	 * @return
	 * @throws IOException
	 */
	String signBackPOList(OrderForB2B pageOrderForB2B, String operateType)
			throws IOException;

	/****
	 * 修改订单
	 * 
	 * @param pageOrderForB2B
	 * @throws IOException
	 */
	String updateOrderForB2B(OrderForB2B pageOrderForB2B) throws IOException;

	/***
	 * 添加价格
	 * 
	 * @param pageOrderForB2B
	 * @return
	 */
	String addPrice(OrderForB2B pageOrderForB2B);

}
