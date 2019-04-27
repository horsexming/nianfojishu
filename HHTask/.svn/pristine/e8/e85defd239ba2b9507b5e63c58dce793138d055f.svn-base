package com.task.ServerImpl.bp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.bp.DetailService;
import com.task.Server.bp.ProductService;
import com.task.entity.bp.Detail;
import com.task.entity.bp.Product;
import com.task.entity.bp.Templet;

public class ProductServiceImpl implements ProductService {
	private TotalDao totalDao;
	private DetailService detailService;

	// 查询
	public Map<String, List<Map<String, Templet>>> getProduct(String detailNumbe) {

		Map<String, Templet> datas = new HashMap<String, Templet>();
		Map<String, List<Map<String, Templet>>> result = new HashMap<String, List<Map<String, Templet>>>();
		List<Detail> details = new ArrayList<Detail>();

		Map<String, Map<String, Templet>> datas2 = new HashMap<String, Map<String, Templet>>();// 接收所有叶子件
		Map<String, Map<String, Templet>> datas3 = new HashMap<String, Map<String, Templet>>();// 接收所有组合件
		Map<String, Templet> datas31 = new HashMap<String, Templet>();// 组合件的中转。

		details = detailService.getDetailsFromDetailNumber(detailNumbe);
		// 把他们叶子件和组合件都拿出来。
		for (Detail dd : details) {
			Detail detailGet = detailService.get(dd.getId());
			if (datas2.get(detailGet.getMonth()) == null) {
				datas = new HashMap<String, Templet>();
			} else {
				datas = datas2.get(detailGet.getMonth());
			}

			if (datas3.get(detailGet.getMonth()) == null) {
				datas31 = new HashMap<String, Templet>();
			} else {
				datas31 = datas3.get(detailGet.getMonth());
			}

			detailService.getTempletLeafs(detailGet, datas, datas31);
			datas2.put(detailGet.getMonth(), datas);
			datas3.put(detailGet.getMonth(), datas31);
		}

		// 把叶子件MAP扔到顶层里，前台遍历
		for (String s : datas2.keySet()) {
			ArrayList<Map<String, Templet>> list = new ArrayList<Map<String, Templet>>(
					2);
			list.add(0, datas2.get(s));
			result.put(s, list);
		}
		// 组合件的MAP扔到顶层里，前台遍历。
		for (String s : datas3.keySet()) {
			ArrayList<Map<String, Templet>> list = null;
			if (result.get(s) == null) {
				list = new ArrayList<Map<String, Templet>>(2);
				result.put(s, list);
			}
			result.get(s).add(1, datas3.get(s));
		}
		return result;
	}

	// 保存
	public boolean saveProduct(String month, Map<String, Templet> leafs,
			Map<String, Templet> disLeafs) {
		for (Templet t : leafs.values()) {
			Product p = new Product();
			p.setName(t.getName());
			p.setPartsNumber(t.getPartsNumber());
			p.setSpecification(t.getSpecification());
			p.setQuantity(t.getAdvPosition());
			p.setMonth(month);
			p.setType(t.getCategory());
			p.setUnit(t.getUnit());
			p.setDirections(t.getDirections());
			if (!totalDao.save(p)) {
				return false;
			}
		}
		return true;
	}

	// 查看
	public Object[] getList(int pageNo, int pageSize) {
		String hql = "from Product";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	// 查看某个计划单编号的配件和组合件
	@SuppressWarnings("unchecked")
	public List<Product> getSingleProduct(String number) {
		String hql = "from Product where pruchaseNum = ?";
		List<Product> products = totalDao.query(hql, number);
		return products;
	}

	@Override
	public Product get(Integer id) {
		return (Product) totalDao.getObjectByCondition(
				"from Product where id = ?", id);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public DetailService getDetailService() {
		return detailService;
	}

	public void setDetailService(DetailService detailService) {
		this.detailService = detailService;
	}

	public List<String> getPruchaseNumber() {
		String hql = "select distinct pruchaseNum from Product";
		return totalDao.query(hql);
	}

	@SuppressWarnings("unchecked")
	public boolean delete(String string) {
		String hql = "from Product where pruchaseNum = ?";
		List<Product> products = totalDao.query(hql, string);
		for (Product product : products) {
			if (!totalDao.delete(product)) {
				return false;
			}
		}
		return true;
	}

	public List<Product> getMonth(String month) {
		String hql = "from Product where month = ?";
		List<Product> products = totalDao.query(hql, month);
		return products;
	}

}
