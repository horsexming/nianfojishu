package com.task.ServerImpl.unpasskp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.Server.ess.GoodsStoreServer;
import com.task.Server.unpasskp.ProductUnPassKpServer;
import com.task.ServerImpl.yw.ConvertNumber;
import com.task.entity.ClientManagement;
import com.task.entity.GoodsStore;
import com.task.entity.OrderManager;
import com.task.entity.Price;
import com.task.entity.ProductManager;
import com.task.entity.sop.Procard;
import com.task.entity.unpasskp.ProductUnPassKp;
import com.task.entity.unpasskp.ProductUnqualified;
import com.task.util.Util;

public class ProductUnPassKpServerImpl implements ProductUnPassKpServer{
	private GoodsStoreServer goodsStoreServer;
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	
	public GoodsStoreServer getGoodsStoreServer() {
		return goodsStoreServer;
	}

	public void setGoodsStoreServer(GoodsStoreServer goodsStoreServer) {
		this.goodsStoreServer = goodsStoreServer;
	}

	@Override
	public Map<Integer, Object> findProductUnPassKpByCondition(
			ProductUnPassKp productUnPassKp, int cpage, int pageSize) {
		// TODO Auto-generated method stub
		if (productUnPassKp == null) {
			productUnPassKp = new ProductUnPassKp();
		}
		String hql = totalDao.criteriaQueries(productUnPassKp, null, null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, cpage, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public boolean delete(ProductUnPassKp productUnPassKp) {
		// TODO Auto-generated method stub
		if(productUnPassKp!=null){
			return totalDao.delete(productUnPassKp);
		}
		return false;
	}

	@Override
	public ProductUnPassKp getById(Integer id) {
		// TODO Auto-generated method stub
		return (ProductUnPassKp) totalDao.getObjectById(ProductUnPassKp.class, id);
	}

	@Override
	public String update(ProductUnPassKp productUnPassKp) throws Exception{
		// TODO Auto-generated method stub
		if(productUnPassKp.getReworkCount()==null){
			productUnPassKp.setReworkCount(0f);
		}
		if(productUnPassKp.getRebuildCount()==null){
			productUnPassKp.setRebuildCount(0f);
		}
		if(productUnPassKp.getCutCount()==null){
			productUnPassKp.setCutCount(0f);
		}
		if(productUnPassKp.getOkCount()==null){
			productUnPassKp.setOkCount(0f);
		}
		ProductUnPassKp old=getById(productUnPassKp.getId());
		old.setReworkCount(productUnPassKp.getReworkCount());
		old.setRebuildCount(productUnPassKp.getRebuildCount());
		old.setCutCount(productUnPassKp.getCutCount());
		old.setOkCount(productUnPassKp.getOkCount());
		
		if((old.getTotalCount()-old.getReworkCount()-old.getRebuildCount()-old.getCutCount()-old.getOkCount())!=0){
			throw new Exception("总数量必须等于返修,重新生成,废单和合格品的和值!");
		}else{
			if(old.getReworkCount()>0){//有返修
				ProductUnqualified productUnqualified=new ProductUnqualified();
				productUnqualified.setCusName(old.getCusName());
				productUnqualified.setMarkId(old.getMarkId());
				productUnqualified.setProName(old.getProName());
				productUnqualified.setTotalCount(old.getReworkCount());
				productUnqualified.setSurplusCount(old.getReworkCount());
				productUnqualified.setSource("开票");
				productUnqualified.setAddTime(Util.getDateTime());
				totalDao.save(productUnqualified);
				// 入到不合格品库;
				GoodsStore gt = new GoodsStore();
				gt.setGoodsStoreMarkId(old.getMarkId());// 件号
				gt.setGoodsStoreWarehouse("不合格品库");// 库别
				gt.setGoodsStoreLot(old.getSelfCard());// 批次
				gt.setGoodsStoreGoodsName(old.getProName());// 名称
				gt.setGoodsStoreCount(old.getReworkCount());// 数量
//				if (t != null) {
//					gt.setGoodHouseName(t.getGoodHouseName());// 仓区
//					gt.setGoodsStorePosition(t.getGoodsStorePosition());// 库位
//				}
				Procard procard=(Procard) totalDao.getObjectByCondition("from Procard where markId=? and selfCard=?",old.getMarkId(), old.getSelfCard()) ;
				if(procard!=null){
					gt.setGoodsStoreGoodsName(procard.getProName());// 名称
					gt.setBanBenNumber(procard.getBanBenNumber());// 版本
					gt.setGoodsStoreFormat(procard.getSpecification());// 规格
					gt.setGoodsStoreUnit(procard.getUnit());
					ProductManager pd= (ProductManager) totalDao.getObjectByCondition("from ProductManager where pieceNumber=? and  id in(select productId from Product_Internal where allcount>hasruku and status='同意' and " +
							"ioDetailId in(select id from InternalOrderDetail where pieceNumber=? and internalOrder.id=? ))",procard.getMarkId(),procard.getMarkId(), procard.getPlanOrderId());
					if(pd!=null){
						gt.setHsPrice(pd.getUnitPrice());// 含税单价
						gt.setTaxprice(pd.getTaxprice());// 税率
						gt.setGoodsStorePrice(pd.getUnitPrice()*pd.getTaxprice());// 不含税单价
						gt.setPriceId(pd.getPriceId());// 价格Id
						gt.setNeiorderId(pd.getOrderManager().getOrderNum());
					}
					gt.setGoodsStoreUnit(procard.getUnit());// 单位
				}
				gt.setGoodsStoreDate(Util.getDateTime("yyyy-MM-dd"));
				gt.setGoodsStoreTime(Util.getDateTime());
				gt.setStyle("不合格品入库");// 入库类型
				gt.setPrintStatus("YES");
//				totalDao.save(gt);
				goodsStoreServer.saveSgrk(gt);
				//原订单的产品的申请开票数除去没有通过申请开票的数量
				ProductManager productManager=(ProductManager) totalDao.getObjectByQuery("from ProductManager where orderManager.outOrderNumber=? and pieceNumber=?", old.getOdrerNumber(),old.getMarkId());
				if(productManager!=null&&productManager.getApplyNumber()!=null&&productManager.getApplyNumber().floatValue()>old.getRebuildCount().floatValue()){
					productManager.setApplyNumber((productManager.getApplyNumber()-old.getRebuildCount()));
				}else if(productManager!=null){
					productManager.setApplyNumber(0f);
				}
			}
			if(old.getRebuildCount()>0){//有重新生产
				OrderManager om=null;
				boolean has=true;
				if(old.getOdrerNumber()!=null){//外部订单号（使用原来的）
					om=(OrderManager) totalDao.getObjectByQuery("from OrderManager where outOrderNumber=? and type ='补单'", old.getOdrerNumber()); 
					if(om==null){
						has=false;
						om=new OrderManager();
						om.setOutOrderNumber(old.getOdrerNumber());
						OrderManager om2=(OrderManager) totalDao.getObjectByQuery("from OrderManager where outOrderNumber=? and (type !='补单' or type is null)", old.getOdrerNumber());
						om.setDocumentaryPeople(om2.getDocumentaryPeople());
						om.setDept(om2.getDept());
						om.setType("补单");
						if(old.getCusName()!=null){
							ClientManagement cus=(ClientManagement) totalDao.getObjectByQuery("from ClientManagement where clientcompanyname=?", old.getCusName());
							om.setCustome(cus);
						}
						//内部订单号（补单特有订单号）
						String beforNumber="budan_";
						List<String> numberList=totalDao.query("select orderNum from OrderManager where orderNum like '"+beforNumber+"%'");
						Integer maxNumebr=0;
						if(numberList.size()>0){
							for(String noString:numberList){
								String[] strs=noString.split("_");
								try {
									Integer no=Integer.parseInt(strs[1]);
									if(no>maxNumebr){
										maxNumebr=no;
									}
								} catch (Exception e) {
									// TODO: handle exception
								}
							}
						}
						maxNumebr++;
						om.setOrderNum(beforNumber+maxNumebr);
					}
				}else{
					throw new Exception("对不起该产品未找到订单号!");
				}
				boolean flag=true;
				Set<ProductManager> products=om.getProducts();
				if(products!=null&&products.size()>0){
					for(ProductManager product:products){
						if(product.getPieceNumber()!=null&&product.getPieceNumber().equals(old.getMarkId())){
							flag=false;
							Float newNum = product.getNum() + old.getRebuildCount();
							product.setNum(newNum);
							// old.setAllocationsNum(newNum);
							Double sub = ConvertNumber.doubleSubtract(product
									.getOrderManager().getTotalAmount(), product
									.getUnitPrice());// 订单总价-产品原来总价
							Double money = ConvertNumber.multiply(product.getUnit(), product
									.getNum());
							product.setUnitPrice(money);
							Double amount = ConvertNumber.doubleSum(sub, money);// 现在订单总价
							product.getOrderManager().setTotalAmount(amount);
							totalDao.update(product);
							break;
						}
					}
				}else{
					ProductManager product = new ProductManager();
					product.setNum((old.getRebuildCount()+0f));
					// pm.setAllocationsNum(num[i]);
					Price price=(Price) totalDao.getObjectByQuery("from Price where partNumber=?", old.getMarkId());
					product.setUnit(price.getHsPrice());
					Double money = ConvertNumber.multiply(price.getHsPrice(),
							product.getNum());
					product.setUnitPrice(money);
					product.setName(price.getName());
					product.setPieceNumber(price.getPartNumber());
					product.setType(price.getType());
					product.setOrderManager(om);
					products.add(product);
					totalDao.save(product);
				}
				om.setProducts(products);
				if(has){
					totalDao.update(om);
				}else{
					totalDao.save(om);
				}
				//原订单的产品的申请开票数除去没有通过申请开票的数量
				ProductManager productManager=(ProductManager) totalDao.getObjectByQuery("from ProductManager where orderManager.outOrderNumber=? and pieceNumber=?", old.getOdrerNumber(),old.getMarkId());
				if(productManager!=null&&productManager.getApplyNumber()!=null&&productManager.getApplyNumber().floatValue()>old.getRebuildCount().floatValue()){
					productManager.setApplyNumber((productManager.getApplyNumber()-old.getRebuildCount()));
				}else if(productManager!=null){
					productManager.setApplyNumber(0f);
				}
			}
			if(old.getCutCount()>0){//有废单数量
				if(old.getOdrerNumber()!=null){//外部订单号（使用原来的）
					ProductManager product=(ProductManager) totalDao.getObjectByQuery("from ProductManager where pieceNumber=? and orderManager.id in (select id from OrderManager where outOrderNumber=? and (type is null or type ='正常'))",old.getMarkId(), old.getOdrerNumber()); 
				    if(product!=null){
				    	if(product.getCutNum()==null){
				    		product.setCutNum(old.getCutCount());
				    	}else{
				    		product.setCutNum(product.getCutNum()+old.getCutCount());
				    	}
				    	totalDao.update(product);
				    }else{
				    	throw new Exception("对不起该订单号下没有的产品!");
				    }
				}else{
					throw new Exception("对不起该产品未找到订单号!");
				}
				//原订单的产品的申请开票数除去没有通过申请开票的数量
				ProductManager productManager=(ProductManager) totalDao.getObjectByQuery("from ProductManager where orderManager.outOrderNumber=? and pieceNumber=?", old.getOdrerNumber(),old.getMarkId());
				if(productManager!=null&&productManager.getApplyNumber()!=null&&productManager.getApplyNumber().floatValue()>old.getRebuildCount().floatValue()){
					productManager.setApplyNumber(productManager.getApplyNumber()-old.getRebuildCount());
				}else if(productManager!=null){
					productManager.setApplyNumber(0f);
				}
			}if(old.getOkCount()>0){//有合格品
				
			}
		}
		old.setStatus("已确认");
		totalDao.update(old);
		return "true";
	}

}
