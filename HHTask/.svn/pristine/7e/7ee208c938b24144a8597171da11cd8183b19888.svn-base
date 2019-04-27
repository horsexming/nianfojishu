package com.task.ServerImpl.unpasskp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.unpasskp.ProductUnqualifiedServer;
import com.task.entity.Goods;
import com.task.entity.GoodsStore;
import com.task.entity.Users;
import com.task.entity.unpasskp.ProductUnPassKp;
import com.task.entity.unpasskp.ProductUnqualified;

public class ProductUnqualifiedServerImpl implements ProductUnqualifiedServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	@Override
	public Map<Integer, Object> findProductUnqualifiedByCondition(
			ProductUnqualified productUnqualified, int cpage, int pageSize) {
		// TODO Auto-generated method stub
		if (productUnqualified == null) {
			productUnqualified = new ProductUnqualified();
		}
		String hql = totalDao.criteriaQueries(productUnqualified, null, null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, cpage, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}
	@Override
	public boolean saveSgrk(GoodsStore goodsStore,Integer id) {
		ProductUnqualified productUnqualified=(ProductUnqualified) totalDao.getObjectById(ProductUnqualified.class, id);
		if(productUnqualified==null){
			return false;
		}
		if(goodsStore.getGoodsStoreCount()!=null&&goodsStore.getGoodsStoreCount()>productUnqualified.getSurplusCount()){
			return false;
		}
		if(productUnqualified.getRukuCount()==null){
			productUnqualified.setRukuCount(goodsStore.getGoodsStoreCount());
		}else{
			productUnqualified.setRukuCount(productUnqualified.getRukuCount()+goodsStore.getGoodsStoreCount());
		}
		productUnqualified.setSurplusCount(productUnqualified.getSurplusCount()-productUnqualified.getRukuCount());
		totalDao.update(productUnqualified);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		goodsStore.setGoodsStoreTime(sdf.format(new Date()));

		Map session = ActionContext.getContext().getSession();
		Users user = (Users) session.get(TotalDao.users);

		goodsStore.setGoodsStorePlanner(user.getName());
		goodsStore.setStatus("入库");
		goodsStore.setGoodsStoreMarkId(goodsStore.getGoodsStoreMarkId()
				.replaceAll("\\s", ""));
		if (goodsStore.getGoodsStoreFormat() == null) {
			goodsStore.setGoodsStoreFormat("");
		}
		Goods g = new Goods();
		g.setGoodsMarkId(goodsStore.getGoodsStoreMarkId());// 件号
		g.setGoodsFullName(goodsStore.getGoodsStoreGoodsName());// 名称
		g.setGoodsLotId(goodsStore.getGoodsStoreLot());// 批次
		g.setGoodsFormat(goodsStore.getGoodsStoreFormat());
		g.setGoodsClass(goodsStore.getGoodsStoreWarehouse());
		String hql = "from Goods where goodsMarkId = ? and goodsLotId = ? and goodsFormat = ? and goodsClass=?";
		Goods s = (Goods) totalDao.getObjectByCondition(hql, new Object[] {
				g.getGoodsMarkId(), g.getGoodsLotId(), g.getGoodsFormat(),
				g.getGoodsClass() });
		if (s == null) {
			g.setGoodsUnit(goodsStore.getGoodsStoreUnit());// 单位
			if (goodsStore.getBeginning_num() != null) {
				g.setGoodsBeginQuantity(goodsStore.getBeginning_num());// 起初数量
				g.setGoodsCurQuantity(goodsStore.getGoodsStoreCount()
						+ goodsStore.getBeginning_num());// 数量
			} else {
				g.setGoodsBeginQuantity(0F);// 起初数量
				g.setGoodsCurQuantity(goodsStore.getGoodsStoreCount() + 0F);// 数量
			}

			g.setGoodsArtsCard(goodsStore.getGoodsStoreArtsCard());// 工艺卡号没有
			g.setGoodsProMarkId(goodsStore.getGoodsStoreProMarkId());// 总成件号没有
			g.setGoodsClass(goodsStore.getGoodsStoreWarehouse());// 所属仓库
			g.setGoodsPosition(goodsStore.getGoodsStorePosition());// 库位
			g.setGoodsPrice(goodsStore.getGoodsStorePrice().floatValue());// 价格
			g.setGoodsSupplier(goodsStore.getGoodsStoreSupplier());// 供应.
			g.setGoodsChangeTime(goodsStore.getGoodsStoreDate());// 日期
			if (goodsStore.getGoodsStoreZhishu() != null) {
				g.setGoodsZhishu(goodsStore.getGoodsStoreZhishu() == 0 ? null
						: goodsStore.getGoodsStoreZhishu());// 支数
			}
			g.setGoodsFormat(goodsStore.getGoodsStoreFormat());// 規格
			g.setGoodsCustomer(goodsStore.getGoodsStoreCompanyName());// 客户
			g.setGoodsZhishu(goodsStore.getGoodsStoreZhishu());
			totalDao.save(g);
		} else {
			s.setGoodsUnit(goodsStore.getGoodsStoreUnit());// 单位
			s.setGoodsCurQuantity(s.getGoodsCurQuantity()
					+ goodsStore.getGoodsStoreCount());
			if (goodsStore.getGoodsStoreZhishu() != null
					&& goodsStore.getGoodsStoreZhishu() > 0F) {
				s.setGoodsZhishu((s.getGoodsZhishu() == null ? 0 : s
						.getGoodsZhishu())
						+ goodsStore.getGoodsStoreZhishu());
			}
			totalDao.update(s);
		}
		return totalDao.save(goodsStore);
	}

	@Override
	public ProductUnqualified getById(Integer id) {
		// TODO Auto-generated method stub
		return (ProductUnqualified) totalDao.getObjectById(ProductUnqualified.class, id);
	}
}
