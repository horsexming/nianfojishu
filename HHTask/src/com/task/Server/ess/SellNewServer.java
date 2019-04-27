package com.task.Server.ess;

import java.util.List;
import java.util.Map;

import com.task.entity.Goods;
import com.task.entity.sop.Procard;

public interface SellNewServer {

	/**
	 * 根据卡片查找需要出库的库存记录
	 * 
	 * @param runningWaterCard
	 * @param ckType 登陆用户拥有出库权限的仓库
	 * @return
	 */
	public Map<Integer,Object> findSellOutlist(Procard procard,String ckType,String status);
	/**
	 * 根据流水卡扫描出库
	 * 
	 * @param rw
	 * @param pro
	 * @param li
	 * @param ckType
	 * @param selected 
	 * @return
	 */
	public List saveSellList(Integer id, Procard pro, List<Goods> li, String tag, float getCount,String ckType, int[] selected,List<Goods> goodsList, String tags);
	/**
	 * 将procard上的原材料下移
	 */
	public void changeycl();
	/**
	 * 弥补新在制品
	 */
	public void newzaizhipin();
}
