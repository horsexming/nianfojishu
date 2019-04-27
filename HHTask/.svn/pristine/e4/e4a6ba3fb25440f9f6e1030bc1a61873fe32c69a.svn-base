package com.task.ServerImpl.yw;

import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.yw.ISellService;
import com.task.entity.Sell;

/**
 * @Title: SellServiceImpl.java
 * @Package com.task.ServerImpl.yw
 * @Description: 业务明细
 * @author Damon
 * @date 2012-10-31 下午12:50:30
 * @version V1.0
 */
public class SellServiceImpl implements ISellService {

	private TotalDao totalDao;

	/**
	 * @Title: getSellById
	 * @Description: 根据ID查询出库明细
	 * @param @param id
	 * @return Sell
	 * @throws
	 */
	public Sell getSellById(int id) {
		return (Sell) totalDao.getObjectById(Sell.class, id);
	}

	/**
	 * @Title: querySellByBusinessId
	 * @Description: 查询当前业务ID中没有的业务明细
	 * @param @param id
	 * @return Object[]
	 * @throws
	 */
	public Object[] querySellByBusinessIdAndCondition(Map map, int id,
			int pageNo, int pageSize) {
		String hql = "from Sell s where sell_id not in (select b.sellId from BusinessSubsidiary b)";
		if (map != null) {
			if (map.get("lot") != null) {
				hql += " and s.sellLot = '" + map.get("lot") + "'";
			}
			if (map.get("markId") != null) {
				hql += " and s.sellMarkId = '" + map.get("markId") + "'";
			}
			if (map.get("companyName") != null) {
				hql += " and s.sellCompanyName = '" + map.get("companyName")
						+ "'";
			}
			if (map.get("warehouse") != null) {
				hql += " and s.sellWarehouse = '" + map.get("warehouse") + "'";
			}
			if (map.get("goods") != null) {
				hql += " and s.sellGoods = '" + map.get("warehouse") + "'";
			}
			if (map.get("beginTime") != null && map.get("endTime") != null) {
				hql += "and (s.sellDate between '" + map.get("beginTime")
						+ "' and '" + map.get("endTime") + "')";
			}
		}
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		return new Object[] { list, count };
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
}
