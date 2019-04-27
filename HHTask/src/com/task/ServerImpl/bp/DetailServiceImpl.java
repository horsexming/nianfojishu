package com.task.ServerImpl.bp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.Server.bp.DetailService;
import com.task.entity.bp.Detail;
import com.task.entity.bp.Product;
import com.task.entity.bp.Templet;

public class DetailServiceImpl implements DetailService {
	private TotalDao totalDao;
	
	@SuppressWarnings( "rawtypes" )
	public Object[] getList(int pageNo, int pageSize) {
		
		String hql = "from Detail order by detailNumber";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public boolean add(Templet templet, Detail detail) {
		String hql = "from Detail where templet.id = ? and month = ? and detailNumber = ?";
		Detail d = (Detail) totalDao.getObjectByCondition(hql, templet.getId(), detail.getMonth(), detail.getDetailNumber());
		if(d != null){
			return false;
		}
		Templet t = (Templet) totalDao.getObjectById(Templet.class, templet.getId());
		if(null == detail){
			return false;
		}
		if(!isVerify(templet)){
			return false;
		}
		detail.setTemplet(t);
		detail.setPurchase("未审核");
		return totalDao.save(detail);
	}

	public Detail get(Integer id) {
		return (Detail) totalDao.getObjectById(Detail.class, id);
	}

	public boolean update(Detail detail,Templet templet) {
		Detail data = (Detail) totalDao.getObjectById(Detail.class, detail.getId());
		data.setMonth(detail.getMonth());
		data.setQuantity(detail.getQuantity());
		data.setExplanation(detail.getExplanation());
		Templet t = (Templet) totalDao.getObjectById(Templet.class, templet.getId());
		data.setTemplet(t);
		return totalDao.update(data);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}


	@SuppressWarnings("unchecked")
	public List<Templet> getAllRoots() {
		String hql = "from Templet where parentId=0";
		return totalDao.query(hql);
	}

	public boolean delete(Detail detail) {
		detail = (Detail) totalDao.getObjectById(Detail.class, detail.getId());
		return totalDao.delete(detail);
	}

	//获取某个配件
	public void getTempletLeafs(Detail detail,Map<String, Templet> leafs,Map<String, Templet> zhongjianjian) {
		
		Detail result = (Detail) totalDao.getObjectById(Detail.class, detail.getId());
		queryTree(result.getTemplet(), leafs,zhongjianjian, result.getQuantity());
	}

	//递归树，用来获取配件和组合件
	private void queryTree(Templet templet, Map<String, Templet> leafs,Map<String, Templet> zhongjianjian, float advPosition) {

		if (templet.getChilds() == null || templet.getChilds().size() < 1){ 
			templet.setAdvPosition(templet.getAdvPosition() * advPosition );
			Templet temp = leafs.get(templet.getPartsNumber());
			if (temp == null) {
				String ss = (templet.getPartsNumber()==null?"":templet.getPartsNumber()) + (templet.getSpecification()==null?"":templet.getSpecification());
				leafs.put(ss, templet);
			} else {
				temp.setAdvPosition(temp.getAdvPosition()+ templet.getAdvPosition());
			}
		}else {
			templet.setAdvPosition(templet.getAdvPosition() * advPosition );
			if(templet.getBelongLayer() > 1){
				Templet temp = zhongjianjian.get(templet.getPartsNumber());
				if(temp == null){
					String ss = (templet.getPartsNumber()==null?"":templet.getPartsNumber()) + (templet.getSpecification()==null?"":templet.getSpecification());
					zhongjianjian.put(ss, templet);
				} else {
					temp.setAdvPosition(temp.getAdvPosition()+ templet.getAdvPosition());
				}
			}
			for (Templet product2 : templet.getChilds()) {
				queryTree(product2, leafs,zhongjianjian, templet.getAdvPosition());
			}
		}
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getDetailsFromDetailNumber(String s) {
		String hql = "from Detail where detailNumber = ?";
		return totalDao.query(hql, s.toString());
	}

	public boolean isVerify(Templet templet){
		return findTempletTree(templet.getId());
	}
	
	private boolean findTempletTree(int id){
		String hql = "from Templet where id=?";
		Templet t = (Templet) totalDao.getObjectByCondition(hql, id);
		return childToList(t);
	}
	
	private boolean childToList(Templet t){
		if(!treeChild(t)){
			return false;
		}
		return true;
	}

	private boolean treeChild(Templet t){
		if("add".equals(t.getMonitorType()) || "update".equals(t.getMonitorType()) || "delete".equals(t.getMonitorType())){
			return false;
		}
		if(t.getChilds() == null){
			return true;
		}
		Set<Templet> set = t.getChilds();
		for (Templet templet : set) {
			if(!treeChild(templet)){
				return false;
			}
		}
		return true;
	}

	public List<Detail> listByMonth(String month) {
		String hql = "from Detail where month=?";
		List s = totalDao.query(hql, month);
		return s;
	}

	public List<Detail> getDetailsById(List<Detail> details) {
		List<Detail> ds = new ArrayList<Detail>();
		for (Detail detail : details) {
			ds.add(this.get(detail.getId()));
		}
		return ds;
	}

	public boolean beginProduct(List<Detail> details) {
		List<Detail> data = details;
		details = new ArrayList<Detail>();
		for (Detail detail : data) {
			details.add(this.get(detail.getId()));
		}
		for (Detail detail : details) {//判断是否有审核中的
			if(!isVerify(detail.getTemplet())){
				return false;
			}
		}

		for (Detail detail : details) {
			detail.setPurchase("审核中");
			totalDao.update(detail);
		}

		return true;
	}

	public List<Detail> listVerify() {
		String hql = "from Detail where purchase = '审核中'";
		List<Detail> list = totalDao.query(hql);
		return list;
	}

	public boolean updateArgee(List<Detail> details) {
		String month = null;
		List<Detail> list = new ArrayList<Detail>();
		for (Detail detail : details) {
			list.add(this.get(detail.getId()));
		}
		
		for (Detail detail : list) {
			String hql = "from Detail where purchase = '审核中' and id = ?";
			detail.setPurchase("已审核");
			month = detail.getMonth();
			totalDao.update(detail);
		}
		Map<String, Templet> leafs = new HashMap<String, Templet>();
		Map<String, Templet> disLeafs = new HashMap<String, Templet>();
		for (Detail detail : list) {
			getTempletLeafs(detail, leafs, disLeafs);
		}
		saveProduct(month, leafs, disLeafs);
		return true;
	}
	
	//保存
	public boolean saveProduct(String month, Map<String, Templet> leafs, Map<String, Templet> disLeafs) {
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
			if(!totalDao.save(p)){
				return false;
			}
		}
		return true;
	}


	public boolean updateDisArgee(List<Detail> details) {
		for (Detail detail : details) {
			detail = this.get(detail.getId());
			detail.setPurchase("未审核");
			if(!totalDao.update(detail)){
				return false;
			}
		}
		return true;
	}

}
