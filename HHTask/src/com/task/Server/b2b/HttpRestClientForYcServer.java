/**
 * 
 */
package com.task.Server.b2b;

import java.io.IOException;

public interface HttpRestClientForYcServer {
	public Object[] getList(Integer pageIndex, Integer pageSize, String itemCode, String suppItemCode, String orgId,
			String startTime, String endTime, String buyerName, String purchaseMode, String version) throws IOException;

	public boolean getet(String data) throws IOException;

	public void get(Integer pageIndex, Integer pageSize, String itemCode, String suppItemCode, String orgId,
			String startTime, String endTime, String buyerName, String purchaseMode, String version, String zhuangtai,
			String taday, String nexttoday, String twonextday, String threeday, String forday, String friveday,
			String sixday, String sevenday, String eitday, String nineday, String tenday, String elevenday)
			throws IOException;

	// 查询DN行
	public Object[] songhuo(Integer pageSize, Integer curPage, String validityPeriod) throws IOException;

	// 填写ASN信息
	public Object[] selASN(String itemCode, String itemRevision, String quantityShipped, String invOrgId)
			throws IOException;

	// 查询标签
	public Object[] selbaioqian(Integer pageSize, Integer curPage, String vendorCode) throws IOException;

	// 导入excel查询数据
	public Object[] excel(Integer pageSize, Integer curPage, String vendorCode) throws IOException;
}
