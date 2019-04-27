package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.OaAppDetail;
import com.task.entity.OutLib;
import com.task.entity.Storage;
import com.task.entity.StorageForm;
import com.task.entity.Store;
import com.task.entity.VOStorage;

public interface IStorageService {
	void add(Store store);
	void add(Storage storage);

	void del(Storage storage);
	
	String update(Storage storage);

	Storage getStorageById(int id);

	Object[] queryStorage(Map map, int pageNo, int pageSize);

	void exportExcel(Map map);

	Object[] queryPurchase(String applyForNum, int pageNo, int pageSize);

	OaAppDetail getOaAppDetail(int id);
	
	OaAppDetail getOaAppDetail1(int id);

	String storageProducts(StorageForm sf, VOStorage vost);

	void clearPrint();

	Object[] queryDetailByCondition(Map map, int pageNo, int pageSize);

	Object[] getCountProcurement(int[] selected);

	void delStorageById(int id);

	Object[] queryStorageHistory(Map map, int pageNo, int pageSize);

	List printStorage(int[] selected);

	/***
	 * 通过申报编号以及状态查询其他入库信息
	 * 
	 * @param number
	 * @return
	 */
	int findStorageclassify(String number, String classify);

	/***
	 * 手动入库
	 * 
	 * @param sf
	 * @param vost
	 * @return
	 */
	String addStorage(StorageForm sf, VOStorage vost);
	
	//根据批量入库编号查对象。
	Storage getnumberId(String string);//2015-08-07_lc
	/**
	 * @author LiCong
	 * 查询已入柜的物品
	 * @param oadetail
	 * @param cpage
	 * @param pageSize
	 * @return
	 */
	Object [] findOADetail_rugui(OaAppDetail oadetail, Integer cpage,Integer pageSize);
	
}
