package com.task.Server;

import java.util.Map;

import com.task.entity.FanghuOutLib;
import com.task.entity.Store;
import com.task.entity.VOStore;
import com.task.entity.VOUser;

public interface IStoreService {
	void add(Store store);

	void del(Store store);

	boolean update(Store store);

	Store getStoreById(int id);

	Object[] queryStore(Map map, int pageNo, int pageSize);

	void exportExcel(Map map);

	String lend(VOStore vos, Integer fhid);

	VOUser getUserByCardId(String cardId);

	void delStoreById(Integer id);

	Object[] queryLoanByCondition(Map map, int pageNo, int pageSize,
			String classification);

	Object[] monthlyStatistics(Map map, int pageNo, int pageSize);

	void exportStatisticsExcel(Map map);

	int updateStorage(Store store);

	/***
	 * 查询防护零用信息
	 * 
	 * @param matetag
	 *            物品名称
	 * @param format
	 *            物品规格
	 * @param userid
	 *            用户id
	 * @return
	 */
	FanghuOutLib findFanghuOutLib(String matetag, String format, Integer userid);
}
