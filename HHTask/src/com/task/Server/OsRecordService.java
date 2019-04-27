package com.task.Server;

import java.util.List;

import com.task.entity.WarehouseNumber;
import com.task.entity.android.OsRecord;
import com.task.entity.android.OsRecordScope;
import com.task.entity.sop.WaigouDeliveryDetail;

public interface OsRecordService {
	/**
	 * 添加
	 * 
	 * @param list
	 */
	public void add(List<OsRecord> list);

	public Object[] list(int pageSize, int parseInt, OsRecord record,
			String status,String fisttime,String endtime);

	public List<OsRecord> get(OsRecord record);

	public OsRecord get(int id);

	public List<OsRecordScope> getScope(OsRecord record);

	/***
	 * 删除抽检记录
	 * 
	 * @param id
	 * @return
	 */
	boolean deleteOsRecord(Integer id);

	/****
	 * 查询送货单明细
	 * 
	 * @param id
	 * @return
	 */
	WaigouDeliveryDetail findWwDDById(int id);

	/***
	 * 根据件号、版本、供货属性查询历史入库信息(库位)
	 * 
	 * @param markId
	 * @param banbenNumber
	 * @param kgliao
	 * @return
	 */
	List findHitoryKw(String markId, String banbenNumber, String kgliao);

	/***
	 * 查询所有仓区
	 * 
	 * @return
	 */
	List findgoodHouselist(String kubie);

	/***
	 * 根据库位码查询对应的仓区、库别
	 * 
	 * @return
	 */
	WarehouseNumber findGoodHouseByKuwei(String kuwei);
	/**
	 * 获取检验批次 生成规则由原生产批次+“_”+0000;
	 * 	 */
	String	getMaxJcpc(String markId,String selfCard);
}
