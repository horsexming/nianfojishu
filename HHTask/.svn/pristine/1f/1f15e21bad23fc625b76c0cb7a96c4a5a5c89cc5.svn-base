package com.task.Server.ess;
import java.io.File;
import java.util.List;
import java.util.Map;

import com.task.entity.GoodHouse;
import com.task.entity.Goods;
import com.task.entity.GoodsStore;
import com.task.entity.GoodsSummary;
import com.task.entity.OaAppDetail;
import com.task.entity.OrderManager;
import com.task.entity.PrintedOutOrder;
import com.task.entity.Sell;
import com.task.entity.android.OsRecord;
import com.task.entity.sop.Procard;
import com.task.entity.sop.ProcardVo;
import com.task.entity.sop.ycl.YuanclAndWaigj;

public interface GoodsStoreServer {

	// 根据时间查询所对应的入库的信息
	public List findDatetime(String setDate, String endDatetime, int pageNo,
			int pageSize);

	// 根据时间查询所对应的入库的信息总数量
	public Integer getCount(String setDate, String endDatetime);

	// 根据件号查询所对应的信息
	public List findMarkId(String jianhao, String setDate, String endDate,
			int pageNo, int pageSize);

	// 根据件号查询所对应的信息总数量
	public Integer getCountMarkId(String jianhao, String setDate, String endDate);

	// 根据件号才查询出单价
	public List findjianhao(String jianhao);

	/***
	 * 根据时间查询所对应的入库的信息
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Object[] findDatetime(int pageNo, int pageSize, String setDate,
			String endDatetime);

	/**
	 * 入库操作管理
	 */
	// 入库待处理记录
	public List findIngoodsStoreList(GoodsStore gs);

	// 条件查询入库记录
	/**
	 * 查询流水卡片
	 * 
	 * @param procard
	 *            (流水卡片条件查询对象)
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
	public Object[] findGoodsStore(GoodsStore goodsStore, String startDate,
			String endDate, Integer cpage, Integer PageSize);
	
	/**
	 * 查询待入库记录
	 * @param goodsStore
	 * @param cpage
	 * @param PageSize
	 * @return 
	 */
	public Object[] findGoodsStoreQueRen(GoodsStore goodsStore, Integer cpage, Integer PageSize);

	/**
	 * 获取单条入库记录
	 * 
	 * @param id
	 * @param tag
	 * @return
	 */
	public GoodsStore getOneGoodsStore(Integer id, String tag);

	/**
	 * 修改入库信息
	 * 
	 * @param goodsStore
	 * @param tag
	 * @return
	 */
	public String updateGoodsStore(GoodsStore goodsStore, String tag);

	/**
	 * 外购入库
	 * 
	 * @param goodsStore
	 * @param record
	 */
	public Goods save(GoodsStore goodsStore, OsRecord record);

	/**
	 * 手动入库
	 * 
	 * @param goodsStore
	 */
	public void saveSgrk(GoodsStore goodsStore);

	public String saveBothSgrk(List<GoodsStore> list);
	/**
	 * 批量导入
	 * 
	 */
	public String addSgrk(File addgoodsStore,GoodsStore goodsStore,String status);
	/**
	 * 从ID获取
	 * 
	 * @param goodsStore
	 */
	public GoodsStore get(GoodsStore goodsStore);

	/**
	 * 查询
	 * 
	 * @param pageSize
	 * @param parseInt
	 * @param goodsStore
	 * @param viewList
	 * @return
	 */
	public Object[] find(int pageSize, int parseInt, GoodsStore goodsStore,
			List<String> viewList,String pageStatus);

	/**
	 * 不做分页的查询
	 * 
	 * @param goodsStore
	 *            条件
	 * @param viewList
	 *            权限
	 * @return 所有符合的集合
	 */
	public List<GoodsStore> find(GoodsStore goodsStore, List<String> viewList,String pageStatus);

	/**
	 * 
	 * @param goodsStore
	 */
	public String update(GoodsStore goodsStore);

	/**
	 * 删除
	 * 
	 * @param goodsStore
	 */
	public String delete(GoodsStore goodsStore);

	/***
	 * 更新外购件
	 * 
	 * @param markId
	 *            件号
	 * @param rukuNumber
	 *            入库数量
	 * @return
	 */
	boolean updateWaiProcard(String markId, Float rukuNumber,
			String goodsStoreWarehouse, Integer wwddId);
	
	/***
	 * 根据件号和批次查询入库信息
	 * 
	 * @param markId
	 *            件号
	 * @param storeLot
	 *            批次
	 * @return
	 */
	List findGoodsStore(String markId, String storeLot);

	public List<GoodsStore> findgoodsStoreNo(GoodsStore goodsStore,
			List<String> viewList);

	public void printInfor(Integer id);

	// 添加仓区
	public boolean addgoodHouse(GoodHouse goodHouse);

	// 查询仓区
	public Object[] findgoodHouse(GoodHouse goodHouse, int parseInt,
			int pageSize);

	public List findgoodHouselist();

	public GoodHouse salgoodHouseByid(GoodHouse goodHouse);

	public boolean updategoodHouse(GoodHouse goodHouse);

	/**
	 * 查看上一批次是否有入库记录
	 * 
	 * @param goodsStoreMarkId
	 * @param goodsStoreLot
	 */
	public String getCanInput(String goodsStoreMarkId, String goodsStoreLot);

	/**
	 * 查出质检即将到期的入库记录（出库完不查。10天即为即将到期）;
	 */
	public List<GoodsStore> findgoodsStoreDQ();

	/**
	 * 根据件号得到内部外部订单号和订单Id;
	 */
	public List<OrderManager> getorderbymarkId(String markId);
	
	/**
	 * 总成入库确认数量发送二维码
	 * @param goodsStore
	 * @return
	 */
	public String ZCsendTow(GoodsStore goodsStore);

	/**
	 * 总成入库确认数量接受二维码操作
	 * @param barCode
	 * @return
	 */
	public String ZcRuKuiBacode(String barCode);
	/**
	 * 弥补
	 * @return
	 */
	public String updatezaitumistake();
	/**
	 * 扫描库位码，开门。
	 */
	String OpenWNByWNNumber(String barCode);
	/**
	 * 扫描订单号申请入库
	 * @param barCode
	 * @return
	 */
	public List findZaizhiApplyInput(String barCode,Procard procard);
	/**
	 * 在制品入库申请明细
	 * @param id
	 * @return
	 */
	public ProcardVo getZaizhiApplyInputDtial(Integer id,Integer processNo);
	/**
	 * 在制品入库申请
	 * @param goodsStore
	 * @param id
	 * @return
	 */
	public String applyzaizhiinput(GoodsStore goodsStore, Integer id);
	/**
	 * 查询在制品待入库列表
	 * @return
	 */
	public List findzzIngoodsStoreList(GoodsStore goodsStore);
	/**
	 * 查询在制品入库历史
	 * @param goodsStore
	 * @param startDate
	 * @param endDate
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	public Object[] findzzGoodsStore(GoodsStore goodsStore, String startDate,String endDate, Integer cpage, Integer PageSize);
	/**
	 * 查询所有成品库备货库入库历史记录
	 * @param tag 排序规则
	 * 
	 */
	Object[] findCPGoodsStoreList(GoodsStore goodsStore,int parseInt,
			int pageSiz,String status, String tag );
	public void updateYmrarkId();
	/**
	 * 设变报废入库
	 * @param id
	 * @param goodsStore
	 * @return
	 */
	public String sbbf(Integer id, GoodsStore goodsStore);
	/**
	 * 设变退库
	 * @param id
	 * @param goodsStore
	 * @return
	 */
	public String sbtk(Integer id, GoodsStore goodsStore);
	/***
	 * 遍历所有入库出库记录生产库存汇总信息
	 */
	public String goodshuizong(String months);
	/***
	 * 遍历所有入库出库记录生产库存汇总信息
	 */
	public String goodshuizong0(String status,GoodsStore gs,Sell sell);
	/**
	 * 查询所有库存汇总信息
	 */
	public  Object[]  findGoodsSum(GoodsSummary goodsSum,Integer pageNo,Integer pagesize,String pagestatus);
	/**
	 * 
	 */
	public void test();
	//存储入库并添加入库记录
	public String storageProducts(GoodsStore goodsStore, OaAppDetail oadetail);
	
	/**
	 * 根据编码查询入库历史记录
	 * @param id
	 * @param tag
	 * @return
	 */
	public List<GoodsStore> findSameProductPrice(Integer id, String tag);
	
	String[] findHouseNameByMarkId(String markId);
	/**
	 * 外购件（待入库）查询
	 * @param goodsStore
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> waigouWaiting(GoodsStore goodsStore, int pageNo, int pageSize);

	void chuli();
	public String waigouQueren(GoodsStore gs_page);
	public String changeWareHouse(Goods goods,Sell sell_page);
	public Object[] findFromchengToWai(GoodsStore goodsStore, int parseInt,
			int pageSiz);
	
	/**
	 * 查询所有外协退料待入库的数据
	 */
	List<GoodsStore> findAllWxDrk(GoodsStore goodsStore);
	/**
	 * 查询所有外协退料待入库的数据
	 */
	Object[] findAllWxRk(GoodsStore goodsStore,String startDate,String endDate,Integer pageNo,Integer pageSize);
	
	/**
	 * 调拨单打印(入库)
	 */
	Map<String, Object> findChangeGoods(GoodsStore goodsStore,Integer pageNo,Integer pageSize,String tag);
	
	/**
	 * 根据GoodsStoreID查询调拨单打印
	 */
	List<GoodsStore> findChangeGoodsBygsId(int[] selected,String tag);
	
	/**
	 * 更新调拨单打印记录
	 */
	String updateChangeGoods(String flag,String tag,PrintedOutOrder printedOutOrder);
	
	/**
	 * 获取库存调拨单打印信息
	 */
	Map<String, Object> getCGByPrintedOutOrder(List<GoodsStore> gsList, String tag);
	/**
	 * 半成品快速入库
	 * @param goodsStore
	 * @return
	 */
	public String bcpquickreceive(GoodsStore goodsStore);

	public GoodsStore getGoodsStoreById(Integer goodsStoreId);
	/**
	 * 修改入库记录单价，同时修改同批次，同件号的库存，出库单价
	 */
	public boolean updatePrice(Integer id,Float hsPrice,Float bhsPrice,Float taxPrice);
	/**
	 * 是否可以手动申请委外库
	 * @return
	 */
	public String getwwrk();
	/**
	 * 
	 */
	void save_xiufu();
}
