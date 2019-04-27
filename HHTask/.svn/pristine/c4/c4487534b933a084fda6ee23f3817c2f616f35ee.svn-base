package com.task.Server.sop;

import com.task.entity.GoodsStore;
import com.task.entity.Users;
import com.task.entity.WarehouseNumber;
import com.task.entity.sop.Procard;
import com.task.entity.sop.RunningWaterCard;
import com.task.entity.sop.qd.LogoStickers;

/**
 * 流水卡片server层
 * 
 * @author jhh
 * 
 */
public interface RunningWaterCardServer {
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
	public Object[] findRunningWaterCard(RunningWaterCard runningWaterCard,
			String startDate, String endDate, Integer cpage, Integer PageSize,
			String tag);

	/**
	 * 添加流水卡片信息
	 * 
	 * @param runningWaterCard
	 * @return
	 */
	public boolean save(RunningWaterCard runningWaterCard);

	/**
	 * 根据ID获取流水卡片
	 * 
	 * @param id
	 * @return
	 */

	public RunningWaterCard getCard(Integer id);

	/**
	 * 下拉选项
	 * 
	 * @param tag
	 */
	public String findCardList(String tag);

	/**
	 * 判断卡号是否存在
	 * 
	 * @param tag
	 * @return
	 */
	public String findCardByCard(String tag);

	/**
	 * 根据流转卡查找对应的流水卡片
	 * 
	 * @param runningWaterCard
	 * @return
	 */
	public Procard getProcard(RunningWaterCard runningWaterCard, String tag);

	/**
	 * 刷卡入库
	 * 
	 * @return
	 */
	public String saveGoodsStore(GoodsStore goodsStore, Integer id,
			Procard procard, String tag,String barCode);

	/***
	 * 通过卡号查询卡信息
	 * 
	 * @param cardNumber
	 * @return
	 */
	RunningWaterCard findRunWC(String cardNumber);

	/***
	 * 通过卡号查询卡信息并绑定卡
	 * 
	 * @param cardNumber
	 *            卡号
	 * @param procard
	 *            流水卡片
	 * @return
	 */
	String saveRunWC(String cardNumber, Procard procard);

	public RunningWaterCard getCardBycardNum(String num, String tag);

	/**
	 * 根据补料单获取流水卡片对象
	 * 
	 * @param barcode
	 * @param tag
	 * @return
	 */
	public Procard getProcardBySticker(String barcode, String tag);

	/**
	 * 根据条件查找补料单
	 * 
	 * @param barcode
	 * @param tag
	 * @return
	 */
	public LogoStickers getLogoSticker(String barcode, String tag);

	/** 根据ID查找Procard 对象 **/
	public Procard getProcardById(Integer id);

	/** 根据工艺流水单查找三检和巡检记录 **/
	String findExamTitle(Procard procard);

	/***
	 * 发卡(只发总成的卡片)
	 * 
	 * @param cardNumber
	 *            卡号
	 * @param procard
	 *            流水卡片
	 * @return
	 */
	String sendRunCard(String cardNumber, Procard procard);

	/***
	 * 无卡激活
	 * 
	 * @param procard
	 *            流水卡片
	 * @return
	 */
	String sendRunCard(Procard procard);

	/***
	 * 查询是否是最小批次
	 * 
	 * @param procard
	 *            流水单
	 * @return
	 */

	String findMinSelfCard(Procard procard);

	/***
	 * 更具卡号差选个人信息
	 * 
	 * @param procard
	 * 
	 * @return
	 */

	public Users getNameByCard(String tag);

	/**
	 * 总成入库时计算下面的自制品的在制品数量是否足够
	 * 
	 * @param pro
	 * @return
	 */
	public String checkZaizhi(Float rkCount, Procard pro);
	/**
	 * 根据库位码得到库位信息;
	 */
	public WarehouseNumber findWNBybarCode(String barCode);
	/**
	 * 开门
	 * @param number
	 * @return
	 */
	public String OpenWNByWNNumber(WarehouseNumber number);
}
