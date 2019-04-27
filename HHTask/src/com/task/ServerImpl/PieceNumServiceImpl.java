package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.IPieceNumService;
import com.task.entity.ClientManagement;
import com.task.entity.Goods;
import com.task.entity.PieceNum;
import com.task.entity.Price;
import com.task.entity.sop.ycl.YuanclAndWaigj;
import com.task.util.Util;

public class PieceNumServiceImpl implements IPieceNumService {

	private TotalDao totalDao;

	/**
	 * @Title: add
	 * @Description: 添加件号
	 * @param pm
	 * @return void
	 * @throws
	 */
	public void add(PieceNum pn) {
		totalDao.save(pn);
	}

	/**
	 * @Title: del
	 * @Description: 删除件号
	 * @param om
	 * @return void
	 * @throws
	 */
	public void del(PieceNum pn) {
		totalDao.delete(pn);
	}

	/**
	 * @Title: delPieceNumById
	 * @Description: 根据ID删除件号
	 * @param id
	 * @return void
	 * @throws
	 */
	public void delPieceNumById(int id) {
		PieceNum pn = (PieceNum) totalDao.getObjectById(PieceNum.class, id);
		totalDao.delete(pn);
	}

	/**
	 * @Title: update
	 * @Description: 修改件号
	 * @param om
	 * @return void
	 * @throws
	 */
	public void update(PieceNum pn) {
		totalDao.update(pn);
	}

	/**
	 * @Title: getPieceNumById
	 * @Description: 根据ID获取件号
	 * @param id
	 * @return PieceNum
	 * @throws
	 */
	public Price getPieceNumById(int id) {
		// return (PieceNum) totalDao.getId(PieceNum.class, id);
		return (Price) totalDao.getObjectById(Price.class, id);
	}

	/**
	 * @Title: queryAllPieceNum
	 * @Description: 查询所有的件号
	 * @param pageNo
	 * @param pageSize
	 * @return Object[]
	 * @throws
	 */
	public Object[] queryAllPieceNum(int pageNo, int pageSize, String pagestatus) {
		String hql = null;
		if ("add".equals(pagestatus)) {
			String time = Util.getDateTime();
			hql = "from Price p where p.produceType ='销售' and  '" + time
					+ "'>=p.pricePeriodStart and '" + time
					+ "' <=p.pricePeriodEnd and p.productCategory='总成' ";
		} else {
			hql = "from Price";
		}
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		return new Object[] { list, count };
	}

	/**
	 * @Title: queryPieceNumCondition
	 * @Description: 根据条件查询所有件号
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return Object[]
	 * @throws
	 */
	public Object[] queryPieceNumCondition(Map map, int pageNo, int pageSize,String ddType) {
		List list = new ArrayList();
		String hql = "from Price p where 1 = 1";
		String time = Util.getDateTime();
		String str = "";
		if (map != null && map.size() > 0) {
			if (map.get("kehuId") != null && !"shouhou".equals(ddType)) {
				str = " and p.kehuId =" + map.get("kehuId");
			}
			if (map.get("type") != null) {
				str += " and p.type = '" + map.get("type") + "'";
			}
			if (map.get("carType") != null) {
				str += " and p.carType = '" + map.get("carType") + "'";
			}
			if (map.get("name") != null) {
				str += " and p.name like '%" + map.get("name") + "%'";
			}
			if (map.get("numId") != null) {
				str += " and p.partNumber like '%" + map.get("numId")
						+ "%'";
			}
		}
		hql += str
		+ " and p.produceType ='销售' and  '"
		+ time
		+ "'>=p.pricePeriodStart and  ('"
		+ time
		+ "'"
		+ " <=p.pricePeriodEnd or p.pricePeriodEnd ='' or p.pricePeriodEnd is null) and p.productCategory='总成'"
		+ "  ";
		
		list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		if (list != null && list.size() > 0) {
			return new Object[] { list, count };
		}
		return null;
	}

	/**
	 * @Title: querySelectedProduct
	 * @Description: 查询所有选择的产品
	 * @param selected
	 * @return List
	 * @throws
	 */
	public Object[] querySelectedProduct(int[] selected) {
		List list = new ArrayList();
		List error = new ArrayList();
		String errorStr = null;
		for (int i = 0; i < selected.length; i++) {
			// PieceNum pn = getPieceNumById(selected[i]); //之前的
			Price price = getPieceNumById(selected[i]);
			if (price != null) {
				// Price price = getPriceByPieceNum(pn.getPieceNumber());
				// if(price != null){
				// Double a = price.getHsPrice();
				// pn.setMoney(a);
				// list.add(pn);
				// }else{
				// error.add("产品："+ pn.getName() + ",件号：" + pn.getPieceNumber()
				// + ",没有相对应的价格;"+"\\n"); //之前的
				// }
				List<Goods> goodsList = totalDao
						.query(
								"from Goods where goodsMarkId=? and goodsStyle='退货入库' and (goodsLotId is null or goodsLotId='')",
								price.getPartNumber());
				price.setGoodsList(goodsList);
				list.add(price);
			} else {
				error.add("产品：" + price.getName() + ",件号："
						+ price.getPartNumber() + ",出现问题！;" + "\\n");
			}
		}
		if (error != null && error.size() > 0) {
			errorStr = error.toString();
			errorStr = errorStr.substring(errorStr.indexOf("[") + 1).substring(
					0, errorStr.indexOf("]") - 1);
		}
		return new Object[] { list, errorStr };
	}

	/**
	 * @Title: getPriceByPieceNum
	 * @Description: 根据件号查询价格
	 * @param num
	 * @return Price
	 * @throws
	 */
	public Price getPriceByPieceNum(String num) {
		String hql = "from Price p where p.partNumber = ?";
		List list = totalDao.query(hql, num);
		if (list != null && list.size() > 0) {
			return (Price) list.get(0);
		}
		return null;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public ClientManagement getClientManagementbyid(Integer id) {
	if(id!=null && id>0){
			ClientManagement cm=	(ClientManagement) totalDao.get(ClientManagement.class, id);
			return cm;
		}
		return null;
	}

	@Override
	public Price getPriceByMarkId(String markId, Map map) {
		String hql = "from Price p where 1 = 1";
		String time = Util.getDateTime();
		String str = "";
		if (map != null && map.size() > 0) {
			if (map.get("kehuId") != null) {
				str = " and p.kehuId =" + map.get("kehuId");
			}
			if (map.get("type") != null) {
				str += " and p.type = '" + map.get("type") + "'";
			}
			if (map.get("carType") != null) {
				str += " and p.carType = '" + map.get("carType") + "'";
			}
			if (map.get("name") != null) {
				str += " and p.name like '%" + map.get("name") + "%'";
			}
			if (map.get("numId") != null) {
				str += " and p.partNumber like '%" + map.get("numId")
						+ "%'";
			}
		}
		hql += str//and p.produceType ='销售'
		+ "  and p.partNumber = '"+markId+"' and  '"
		+ time
		+ "'>=p.pricePeriodStart and  ('"
		+ time
		+ "'"
		+ " <=p.pricePeriodEnd or p.pricePeriodEnd ='' or p.pricePeriodEnd is null) order by writeDate desc";
		
		Price price = (Price) totalDao.getObjectByCondition(hql);
		if(price!=null&&(price.getDanwei()==null||"".equals(price.getDanwei()))){
			upYuan(markId, price);
		}
		return price;
	}

	/**
	 * 查询外购件库
	 * @param markId
	 * @param price
	 * @return
	 */
	private YuanclAndWaigj upYuan(String markId, Price price) {
		YuanclAndWaigj yuanclAndWaigj;
		if(price.getSpecification()==null){
			yuanclAndWaigj = (YuanclAndWaigj) totalDao
				.getObjectByCondition("from YuanclAndWaigj where markId = ? ",markId);
		}else {
			yuanclAndWaigj = (YuanclAndWaigj) totalDao
				.getObjectByCondition("from YuanclAndWaigj where markId = ? and specification= ? ",markId,price.getSpecification());
		}
		if(yuanclAndWaigj==null){
			yuanclAndWaigj = (YuanclAndWaigj) totalDao
				.getObjectByCondition("from YuanclAndWaigj where markId = ? ",markId);
		}
		if(yuanclAndWaigj!=null){
			price.setDanwei(yuanclAndWaigj.getUnit());
		}
		return yuanclAndWaigj;
	}

}
