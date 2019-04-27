package com.task.ServerImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.IOrderManagerService;
import com.task.Server.IProductManagerService;
import com.task.ServerImpl.yw.ConvertNumber;
import com.task.entity.Goods;
import com.task.entity.InternalOrderDetail;
import com.task.entity.OrderManager;
import com.task.entity.Price;
import com.task.entity.ProductManager;
import com.task.entity.ProductZsAboutBh;

/**
 * @ClassName: ProductManagerServiceImpl
 * @Description: 产品接口实现类
 * @author 曾建森
 * @date 2012-11-23 上午09:50:41
 */
public class ProductManagerServiceImpl implements IProductManagerService {
	
	private TotalDao totalDao;
	private IOrderManagerService iom;
	/**
	 * @Title: add
	 * @Description: 添加产品
	 * @param pm 
	 * @return void  
	 * @throws
	 */
	public void add(ProductManager pm) {
		Double price = ConvertNumber.doubleSum(pm.getOrderManager().getTotalAmount(),pm.getUnitPrice());
		pm.getOrderManager().setTotalAmount(price);
		totalDao.save(pm);
	}
	/**
	 * @Title: update
	 * @Description: 修改产品
	 * @param om 
	 * @return void  
	 * @throws
	 */
	public void update(ProductManager pm) {
		totalDao.update(pm);
	}
	/**
	 * @Title: del
	 * @Description: 删除产品
	 * @param om 
	 * @return void  
	 * @throws
	 */
	public void del(ProductManager pm1) {
		ProductManager pm = getProductManagerById(pm1.getId());
		if(pm==null) return;
		Double price = ConvertNumber.doubleSubtract(pm.getOrderManager().getTotalAmount(),pm.getUnitPrice()==null?0:pm.getUnitPrice());
		pm.getOrderManager().setTotalAmount(price);
		if (pm.getOrderManager().getOrderType() != null && pm.getOrderManager().getOrderType().equals("正式")) {
			List<ProductZsAboutBh> aboutbhList =totalDao.query("from ProductZsAboutBh where zsProductId=?", pm.getId());
			if(aboutbhList!=null&&aboutbhList.size()>0){
				for(ProductZsAboutBh aboutbh:aboutbhList){
					ProductManager relatePm = (ProductManager) totalDao
					.getObjectById(ProductManager.class,
							aboutbh.getBhProductId());
					if(relatePm!=null){
						relatePm.setCxApplyCount(relatePm
								.getCxApplyCount()-aboutbh.getAboutCount());
						totalDao.update(relatePm);
					}
				}
			}
		}
		pm.getOrderManager().getProducts().remove(pm);
		totalDao.update(pm);
		InternalOrderDetail internal = (InternalOrderDetail) totalDao.getObjectByCondition(" from InternalOrderDetail where productManagerId = ?", pm.getId());
		if(internal!=null)
			totalDao.delete(internal);
		totalDao.delete(pm);
	}
	/**
	 * @Title: delProductManagerById
	 * @Description: 根据ID删除产品
	 * @param id 
	 * @return void  
	 * @throws
	 */
	public void delProductManagerById(int id) {
		ProductManager pm = (ProductManager) totalDao.getObjectById(ProductManager.class, id);
		totalDao.delete(pm);
	}
	/**
	 * @Title: getProductManagerById
	 * @Description: 根据ID获取产品
	 * @param id
	 * @return ProductManager  
	 * @throws
	 */
	public ProductManager getProductManagerById(int id) {
		return (ProductManager) totalDao.getObjectById(ProductManager.class, id);
	}
	/**
	 * @Title: queryAllProductManager
	 * @Description: 查询所有产品
	 * @param pageNo
	 * @param pageSize
	 * @return Object[]  
	 * @throws
	 */
	public Object[] queryAllProductManager(int pageNo, int pageSize) {
		String hql = "from ProductManager ";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);	
		int count =	totalDao.getCount(hql);
		return new Object[]{list,count};
	}
	/**
	 * @Title: queryProductManagerByCondition
	 * @Description: 根据条件查询
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return Object[]  
	 * @throws
	 */
	public Object[] queryProductManagerByCondition(Map map, int pageNo,
			int pageSize) {
		List list = new ArrayList();
		String hql = "from ProductManager p where 1=1";
		if (map != null) {
			if (map.get("orderNum") != null) {
				hql += " and o.orderNum = '" + map.get("orderNum") + "'";			}
			if (map.get("deliveryStatus") != null) {
				hql += " and o.deliveryStatus = '" + map.get("deliveryStatus")+ "'";
			}
			if (map.get("documentaryPeople") != null) {
				hql += " and o.documentaryPeople = '" + map.get("documentaryPeople") + "'";
			}
			if(map.get("custome") != null){
				hql += " and o.custome.id = '" + map.get("custome") + "'";
			}
			if (map.get("billingPeople") != null) {
				hql += " and o.billingPeople = '" + map.get("billingPeople")+ "'";
			}
			if (map.get("beginTime") != null && map.get("endTime") != null) {
				hql += " and (o.paymentDate between '" + map.get("beginTime") + "' and '" + map.get("endTime") + "')";
			}
		}
		list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		return new Object[]{list,count};
	}
	/**
	 * @Title: queryDetailByOrderById
	 * @Description: 根本订单ID查询订单明细
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @return Object[]  
	 * @throws
	 */
	public Object[] queryDetailByOrderById(int id, Integer pageNo, Integer pageSize) {
		List<ProductManager> list = new ArrayList();
		String remarks = "";
		if(id != 0){
			String hql = "from ProductManager p where p.orderManager.id = " + id;
			if(pageNo !=null && pageSize!=null){
				list = totalDao.query(hql);
				if(list!=null&&list.size()>0){
					for(ProductManager pm:list){
						pm.setCxCount(0f);
						if(pm.getOrderManager().getOrderType().equals("正式")){
							List<Object[]> bhNumberList = totalDao.query("select p1.orderManager.orderNum,p2.aboutCount from ProductManager p1,ProductZsAboutBh p2 " +
									"where p1.id=p2.bhProductId and  p2.zsProductId=?", pm.getId());
							if(bhNumberList!=null&&bhNumberList.size()>0){
								StringBuffer sb= new StringBuffer();
								Float aboutcount=0f;
								for(Object[] obj:bhNumberList){
									if(sb.length()==0){
										sb.append(obj[0].toString()+"("+obj[1].toString()+")");
									}else{
										sb.append("；"+obj[0].toString()+"("+obj[1].toString()+")");
									}
									aboutcount+=Float.parseFloat(obj[1].toString());
								}
								pm.setBhnumber(sb.toString());
								pm.setCxCount(aboutcount);
							}else if("计划完善".equals(pm.getStatus())){//状态为计划完善的未关联备货的正式订单
								Float count = (Float) totalDao
								.getObjectByCondition(
										"select count(*) from ProductManager where  num> (cxCount+cxApplyCount) "
												+ "and (pieceNumber=? or ywMarkId=? or pieceNumber=? or ywMarkId=?) and orderManager.orderType in('备货') and orderManager.ep_statuts='同意'",
												pm.getPieceNumber(),pm.getPieceNumber(), pm.getYwMarkId(), pm.getYwMarkId());
								if(count>0){//有可关联的数据
									pm.setBhnumber("待关联");
								}
							}
						}else if(pm.getOrderManager().getOrderType().equals("备货")){
							List<Object[]> bhNumberList = totalDao.query("select p1.orderManager.orderNum,p2.aboutCount from ProductManager p1,ProductZsAboutBh p2 " +
									"where p1.id=p2.zsProductId and  p2.bhProductId=?", pm.getId());
							if(bhNumberList!=null&&bhNumberList.size()>0){
								StringBuffer sb= new StringBuffer();
								for(Object[] obj:bhNumberList){
									if(sb.length()==0){
										sb.append(obj[0].toString()+"("+obj[1].toString()+")");
									}else{
										sb.append("；"+obj[0].toString()+"("+obj[1].toString()+")");
									}
								}
								pm.setBhnumber(sb.toString());
							}
						}
						remarks+= ";"+ pm.getRemark()==null?"":pm.getRemark();
					}
				}
				if(remarks!=null && remarks.length()>1){
					remarks = remarks.substring(1);
				}
				int count = totalDao.getCount(hql);
				return new Object[]{list,count,remarks};
			}else{
				list = totalDao.query(hql, null);
				if(list!=null&&list.size()>0){
					for(ProductManager pm:list){
						if(pm.getOrderManager().getOrderType().equals("正式")){
							List<String> bhNumberList = totalDao.query("select orderManager.orderNum from ProductManager where id in(select bhProductId from ProductZsAboutBh where zsProductId=?)", pm.getId());
							if(bhNumberList!=null&&bhNumberList.size()>0){
								StringBuffer sb= new StringBuffer();
								for(String bhnumber:bhNumberList){
									if(sb.length()==0){
										sb.append(bhnumber);
									}else{
										sb.append(";"+bhnumber);
									}
								}
								pm.setBhnumber(sb.toString());
							}else if(pm.getStatus().equals("计划完善")){//状态为计划完善的未关联备货的正式订单
								Float count = (Float) totalDao
								.getObjectByCondition(
										"select count(*) from ProductManager where  num> (cxCount+cxApplyCount) "
												+ "and (pieceNumber=? or ywMarkId=?) and orderManager.orderType in('备货','预测')",
												pm.getPieceNumber(), pm.getPieceNumber());
								if(count>0){//有可关联的数据
									pm.setBhnumber("待关联");
								}
							}
						}
						remarks+= ";"+ pm.getRemark()==null?"":pm.getRemark();
					}
				}
				if(remarks!=null && remarks.length()>1){
					remarks = remarks.substring(1);
				}
				return new Object[]{list,0,remarks};
			}
		}
		return null;
	}
	
	public TotalDao getTotalDao() {
		return totalDao;
	}
	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	public IOrderManagerService getIom() {
		return iom;
	}
	public void setIom(IOrderManagerService iom) {
		this.iom = iom;
	}
//	@Override
//	public Map<String, Integer> getGoodsCount(Integer[] goodsIds) {
//		// TODO Auto-generated method stub
//		Map<String, Integer> map=new HashMap<String, Integer>();
//		if(goodsIds!=null&&goodsIds.length>0){
//			for(Integer goodsId:goodsIds){
//				Goods goods=(Goods) totalDao.getObjectById(Goods.class, goodsId);
//				if(goods.getGoodsStyle().equals("退货入库")&&(goods.getGoodsLotId()==null||goods.getGoodsLotId().equals(""))){
//					if(goods.getGoodsCurQuantity()!=null&&goods.getGoodsCurQuantity()>0){
//						if(map.containsKey(goods.getGoodsMarkId())){
//							Integer num= Integer.parseInt((map.get(goods.getGoodsMarkId())+goods.getGoodsCurQuantity())+"");
//							map.put(goods.getGoodsMarkId(),num);
//						}else{
//							map.put(goods.getGoodsMarkId(),Integer.parseInt(goods.getGoodsCurQuantity()+""));
//							
//						}
//					}
//				}
//			}
//		}
//		return map;
//	}
	@Override
	public boolean addProduct(int[] selected, Float[] num, int id,Integer[] goodsIds) {
		// TODO Auto-generated method stub
		boolean b=true;
		Map<String, ProductManager> countMap = new HashMap<String, ProductManager>();
		OrderManager om = (OrderManager) totalDao.getObjectById(OrderManager.class, id);
		if (om != null) {
			if (om.getProducts() != null) {
				for (Iterator<ProductManager> it = om.getProducts()
						.iterator(); it.hasNext();) {
					ProductManager pm = it.next();
					countMap.put(pm.getPieceNumber(), pm);
				}
			}
		}
		List<Goods> goodsList=null;
		if(goodsIds!=null&&goodsIds.length>0){
			StringBuffer sb=new StringBuffer();
			for(int i=0;i<goodsIds.length;i++ ){
				if(i==0){
					sb.append(goodsIds[i]);
				}else{
					sb.append(","+goodsIds[i]);
				}
			}
			goodsList=totalDao.query("from Goods where goodsStyle='退货入库' and (goodsLotId is null or goodsLotId='') and goodsCurQuantity>0 and goodsId in ("+sb.toString()+")");
		}
		for (int i = 0; i < selected.length; i++) {
			Price price =(Price) totalDao.getObjectById(Price.class, selected[i]);
			if (countMap.containsKey(price.getPartNumber())) {
				ProductManager old = countMap.get(price.getPartNumber());
				Float otherNum=0f;
				if(goodsList!=null&&goodsList.size()>0){
					for(Goods goods:goodsList){
						if(goods.getGoodsMarkId().equals(price.getPartNumber())){
							otherNum+=goods.getGoodsCurQuantity();
							goods.setGoodsLotId(findMaxSelfCard(price.getPartNumber()));
							totalDao.update(goods);
						}
					}
				}
				Float newNum = old.getNum() + num[i]+otherNum;
				old.setNum(newNum);
				old.setAllocationsNum(otherNum);
				// old.setAllocationsNum(newNum);
				Double sub = ConvertNumber.doubleSubtract(old
						.getOrderManager().getTotalAmount(), old
						.getUnitPrice());// 订单总价-产品原来总价
				Double money = ConvertNumber.multiply(old.getUnit(), old
						.getNum());
				old.setUnitPrice(money);
				Double amount = ConvertNumber.doubleSum(sub, money);// 现在订单总价
				old.getOrderManager().setTotalAmount(amount);
				update(old);
			} else {
				ProductManager pm = new ProductManager();
				pm.setNum(num[i]);
				// pm.setAllocationsNum(num[i]);
				pm.setUnit(price.getHsPrice());
				Double money = ConvertNumber.multiply(price.getHsPrice(),
						pm.getNum());
				pm.setUnitPrice(money);
				pm.setName(price.getName());
				pm.setPieceNumber(price.getPartNumber());
				pm.setType(price.getType());
				pm.setOrderManager(om);
				add(pm);
			}
		}
		return b;
	}
	// 查询该件号最大批次号
	public String findMaxSelfCard(String markId) {
		String mouth = new SimpleDateFormat("yyyyMMdd").format(new Date());
		int yy = Integer.parseInt(mouth.substring(0, 4));
		int mm = Integer.parseInt(mouth.substring(4, 6));
		int dd = Integer.parseInt(mouth.substring(6, 8));
		if (dd >= 26) {
			if (mm == 12) {
				mm = 1;
				yy++;
			} else {
				mm++;
			}
		}
		if (mm < 10) {
			mouth = yy + "0" + mm;
		} else {
			mouth = yy + "" + mm;
		}
		String hql = "select max(selfCard) from Procard where markId=? and selfCard like '%"
				+ mouth + "%'";
		Object object = (Object) totalDao.getObjectByCondition(hql, markId);
		if (object != null) {
			Long selfCard = Long.parseLong(object.toString()) + 1;// 当前最大流水卡片
			return selfCard.toString();
		} else {
			return mouth + "00001";
		}
	}
	@Override
	public String checkCanChangeNum(int id, String orderNum) {
		// TODO Auto-generated method stub
		Float num = 0f;
		if(orderNum!=null&&!orderNum.equals("")){
			try {
				num = Float.valueOf(orderNum);
				if(num<=0){
					return "修改数量不能小于0！";
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				return "请输入正确数量！";
			}
		}
		ProductManager pm=getProductManagerById(id);
		if(pm==null)return "订单明细为空，修改失败！";
		if(pm.getNum().floatValue()!=0&&pm.getNum().floatValue()!=num.floatValue()){
//			Float count =(Float) totalDao.getObjectByCondition("select count(*) from InternalOrder io join io.outerOrders os where os.id =(select orderManager.id from ProductManager where id=?)", id);
			InternalOrderDetail internalOrderDetail = (InternalOrderDetail) totalDao.getObjectByCondition("from InternalOrderDetail where productManagerId = ?", pm.getId());
		    if(internalOrderDetail!=null&&internalOrderDetail.getTurnCount()!=null){
		    	Float count = internalOrderDetail.getTurnCount();
		    	if(count.floatValue() > num.floatValue()){
		    		return "此产品的订单明细数量不允许小于已转换内部计划数量："+count;
		    	}else {
			    	if("已转完".equals(pm.getStatus())&&num>count){
			    		pm.setStatus("未转完");
			    	}
			    	pm.setHasTurn(num);
			    	totalDao.update(pm);
			    	internalOrderDetail.setNum(num);
			    	totalDao.update(internalOrderDetail);
				}
		    }
		}
		return "true";
	}
}
