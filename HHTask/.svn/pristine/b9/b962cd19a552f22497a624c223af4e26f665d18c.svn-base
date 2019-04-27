package com.task.Server;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.task.entity.GoodHouse;
import com.task.entity.WareHouse;
import com.task.entity.WarehouseArea;
import com.task.entity.WarehouseNumber;
import com.task.entity.menjin.WareBangGoogs;

public interface WarehouseAreaServerDao {
	
	/**
	 * 添加仓区
	 */
	public  String addWarehouseArea(List<GoodHouse> waList,String wareHouseName);
	/**
	 * 添加库位
	 */
	public String addWarehouseNumber(List<WarehouseNumber> wnList,String cangqu,String wareHouseName);
	
	/**
	 * 根据 库别编号 查询 仓区;
	 */
	public List<GoodHouse> findwaListBywareHouseNo(String NO);
	/**
	 * 根据 库别编号 和 仓区 名 查询 库位
	 */
	public List<WarehouseNumber> findwnList(String NO,String warehouseArea);
	/**
	 * 显示所有仓区（分页）
	 */
	public List<WarehouseArea> findAllWAByPage(int pageNo, int pageSize);
	/**
	 * 条件查询仓区
	 */
	public Map<Integer, Object> findWAByCondition(GoodHouse warehouseArea,int pageNo, int pageSize);
	/**
	 * 根据仓库名，仓区名 查询仓区是否存在
	 */
	public boolean goodHouseCountByWG(GoodHouse warehouseArea);
	/**
	 * 显示所有库位（分页）
	 */
	public List<WarehouseNumber> findAllWNByPage(int pageNo, int pageSize);
	/**
	 * 条件查询库位
	 */
	public Map<Integer, Object> findWNByCondition(WarehouseNumber warehouserNumber,int pageNo, int pageSize,String tag);
	
	/**
	 * 删除仓区;
	 */
	public boolean delWarehouseArea(GoodHouse warehouseArea);
	/**
	 * 删除库位
	 */
	public boolean delWarehouseNumber(WarehouseNumber warehouserNumber);
	
	/**
	 * 修改仓区
	 */
	public String updateWarehouseArea(GoodHouse warehouseArea);
	/**
	 * 修改库位
	 */
	public String updateWarehouseNumber(WarehouseNumber warehouserNumber);
	/**
	 * 得到所有仓区条数;
	 */
	int getcountWarehouseArea();
	int getcountWarehouseNumber();
	/**
	 * 查询所有仓库
	 */
	List<WareHouse> findAllWareHouse();
	/**
	 * 根据Id查询库位
	 */
	WarehouseNumber getwarehouserNumberById(int id);
	/**
	 * 查询所有库位信息
	 */
	List<WarehouseNumber> findAllWNList();
	/**
	 * 查询所有库位信息
	 */
	List<WarehouseNumber> findAllWNList_1();
	/**
	 * 批量导入库位
	 */
	String  addplwarehouseNumber(File addwarehouseNumber);
	
	Object[] findIpList();
	/**
	 * 打开库位门
	 */
	String OpenWNById(Integer id);
	/**
	 * 关闭库位们
	 */
	String ColseWNById(Integer id);
	/**
	 * 重置灯信号
	 * @param id
	 */
	public String CongzhiLight(Integer id);

	/**
	 * 重置屏幕信息
	 * @param id
	 */
	public String Congzhipinmu(Integer id);
	public String sendKuWei(Integer id);
	public String sendZhuYe(Integer id);
	public String shansuo(Integer id);
	/**
	 * 查询所有仓区
	 */
	List<GoodHouse> getAllWalist();
	/**
	 * 查询库位下的所有物品
	 */
	public String getWarebgnding(Integer id);
	
	/**
	 * 唵嘛呢叭咪吽
	 */
	public void addGoodsBandDing();
	
	
	/**
	 * 根据仓库名和仓区名 查询仓区对象并验证面积
	 */
	public String cangquIsExistArea(String wareHouseName,String cangquName);
	
	
	
}
