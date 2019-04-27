package com.task.ServerImpl.bp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.bp.TempletMonitorService;
import com.task.Server.bp.TempletService;
import com.task.entity.bp.Templet;
import com.task.entity.bp.TempletMonitor;

public class TempletServiceImpl implements TempletService {
	private TotalDao totalDao;
	private TempletMonitorService templetMonitorService ;
	
	//检查是否为空
	private boolean check(Templet templet){
		if(templet == null){
			return false;
		}
		if(templet.getPartsNumber() == null){
			templet.setPartsNumber("");
			return true;
		}

		return true;
	}

	//更新
	public boolean update(Templet templet) {
		if(!check(templet)){//检查是否为空
			return false;
		}
		Templet data = (Templet) totalDao.getObjectByCondition("from Templet where id=?", templet.getId());
		if(!("update".equals(data.getMonitorType()) || "add".equals(data.getMonitorType()) || "delete".equals(data.getMonitorType()))){
			if(!templetMonitorService.update(data)){
				return false;
			}
		}
		BeanUtils.copyProperties(templet, data, new String[]{"id","parent","parentId","parentId2","childs"});
		data.setMonitorType("update");
		return totalDao.update(data);
	}

	//查找某个对象，组成字符串
	public String findForSetlect(int id) {
		String hql = "from Templet where id=?";
		Templet templet = (Templet) totalDao.getObjectByCondition(hql, id);
		String s = templet.getId() + "|"
		+ templet.getParentId2() + "|"
		+ templet.getName() + "|"
		+ (templet.getPartsNumber()==null?"":templet.getPartsNumber()) + "|"
		+ templet.getAdvPosition() + "|"
		+ templet.getModels() + "|"
		+ templet.getDirections() + "|"
		+ (templet.getSpecification()==null?"":templet.getSpecification()) + "|"
		+ templet.getCategory() + "|"
		+ templet.getUnit() + "|"
		+ (templet.getResPerson()==null?"":templet.getResPerson()) + "|"
		+ templet.getBelongLayer() + "|"
		+ (templet.getTrademark()==null?"":templet.getTrademark()) + "|"
		+ (templet.getThickness()==null?"":templet.getThickness())  + "|"
		+ (templet.getThicknessT()==null?"":templet.getThicknessT())  + "|"
		+ (templet.getWidth()==null?"":templet.getWidth())  + "|"
		+ (templet.getWidthT()==null?"":templet.getWidthT())  + "|"
		+ (templet.getLength()==null?"":templet.getLength())  + "|"
		+ (templet.getLengthT()==null?"":templet.getLengthT())  + "|"
		+ (templet.getDiameter()==null?"":templet.getDiameter())  + "|"
		+ (templet.getStandards()==null?"":templet.getStandards());
		return  s;
	}

	//添加
	public boolean add(Templet templet) {
		if(!check(templet)){//检查是否为空
			return false;
		}
		String sql = "from Templet where id=?";
		Templet parent = (Templet) totalDao.getObjectByCondition(sql, templet.getId());
		templet.setParentId2(parent.getId());
		templet.setParent(parent);
		templet.setBelongLayer(parent.getBelongLayer()+1);
		templet.setMonitorType("add");
		return totalDao.save(templet);
	}

	//删除
	public boolean delete(Templet templet) {
		if (templet == null) {
			return false;
		}
		String hql = "from Templet where id=?";
		Templet t = (Templet) totalDao.getObjectByCondition(hql, templet.getId());
		if(t == null || t.getChilds().size() > 0){
			return false;
		}
		t.setMonitorType("delete");
		
		return totalDao.update(t);
	}

	//查找所有
	public List findAll() {
		String sql = "from Templet";
		return totalDao.query(sql);
	}
	
	@SuppressWarnings("rawtypes")
	public List findTempletTree(int id){
		String hql = "from Templet where id=?";
		Templet t = (Templet) totalDao.getObjectByCondition(hql, id);
		List l = childToList(t);
		return l;
	}
	
	@SuppressWarnings("rawtypes")
	private List childToList(Templet t){
		List list = new ArrayList();
		treeChild(t, list);
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void treeChild(Templet t, List list){
		list.add(t);
		if(t.getChilds() == null){
			return ;
		}
		Set<Templet> set = t.getChilds();
		for (Templet templet : set) {
			treeChild(templet, list);
		}
	}


	//获得负责人所负责商品
	@SuppressWarnings("unchecked")
	public List<Templet> getProductTemplet(String resPerson){
			List<Object> names = null;
			boolean b = false;
			if(resPerson == null){
				names = totalDao.createQuerySelect(null, "select id,partsNumber from ta_bp_Templet where parentId=0");
			} else {
				String sql = "select id, partsNumber, resPerson from ta_bp_Templet where parentId=0 and resPerson=?";
				names = totalDao.createQuerySelect(null, sql, resPerson);
				b = true;
			}
			List<Templet> templets = new ArrayList<Templet>();
			for (int i = 0; i < names.size(); i++) {
				Object[] arr = (Object[])names.get(i);
				Templet t = new Templet();
				t.setId(Integer.parseInt(arr[0].toString()));
				t.setPartsNumber(arr[1].toString());
				if(b){
					t.setResPerson(arr[2].toString());
				}
				templets.add(t);
			}
			
			return templets;
		}
	
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	
	//添加根
	@SuppressWarnings("deprecation")
	public boolean addRoot(Templet templet) {
		Session session = totalDao.createSession();
		Connection conn = session.connection();
		String sql = "insert into ta_bp_Templet (name, partsNumber, advPosition, models, directions, specification, belongLayer, parentId, parentId2, category, unit,resPerson) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, templet.getName());
			ps.setString(2, templet.getPartsNumber());
			ps.setFloat(3, templet.getAdvPosition());
			ps.setString(4, templet.getModels());
			ps.setString(5, templet.getDirections());
			ps.setString(6, (templet.getSpecification() == null)?"":templet.getSpecification());
			ps.setInt(7, 1);
			ps.setInt(8, 0);
			ps.setInt(9, 0);
			ps.setString(10, templet.getCategory());
			ps.setString(11, templet.getUnit());
			ps.setString(12, (templet.getResPerson() == null)?"":templet.getResPerson());
			ps.execute();
			conn.commit();
			session.close();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
				try {
					if(ps != null) ps.close();
					if(conn != null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}

	//拿到某个配件的产品
	public Templet getRoot(Templet templet){
		int temp = templet.getId();
		String hql = "from Templet where id = ?" ;
		while(true){
			Templet t = (Templet) totalDao.getObjectByCondition(hql, temp);
			if(t.getParentId2() == 0){
				return t;
			}
			temp = t.getParentId2();
		}
	}

	public TempletMonitorService getTempletMonitorService() {
		return templetMonitorService;
	}

	public void setTempletMonitorService(TempletMonitorService templetMonitorService) {
		this.templetMonitorService = templetMonitorService;
	}

	@SuppressWarnings("unchecked")
	public List<Templet> findForVerify() {
		String hql = "from Templet where monitorType=? or monitorType=? or monitorType=?";
		List<Templet> templets =  totalDao.query(hql, "update", "add", "delete");
		return templets;
	}

	public boolean agree(Templet templet) {
		String hql = "from Templet where id = ?" ;
		Templet t = (Templet) totalDao.getObjectByCondition(hql, templet.getId());
		if(t.getMonitorType()!= null && t.getMonitorType().equals("update")){//如果修改同意，保留
			t.setMonitorType("");
			return totalDao.update(t);
		}
		
		if(t.getMonitorType()!= null && t.getMonitorType().equals("add")){//添加同意，保留
			t.setMonitorType("");
			return totalDao.update(t);
		}
		if(t.getMonitorType()!= null && t.getMonitorType().equals("delete")){//删除同意，删除此记录
			t.setParent(null);
			return totalDao.delete(t);
		}
		return false;
	}

	public boolean overrule(Templet templet) {
		String hql = "from Templet where id = ?" ;
		Templet t = (Templet) totalDao.getObjectByCondition(hql, templet.getId());
		if(t.getMonitorType()!= null && t.getMonitorType().equals("update")){//如果修改不同意，还原
			String hql1 = "from TempletMonitor where tId = ? order by id desc";
			TempletMonitor templetMonitor = (TempletMonitor)totalDao.getObjectByCondition(hql1, t.getId());
			t.setAdvPosition(templetMonitor.getAdvPosition());
			t.setCategory(templetMonitor.getCategory());
			t.setDirections(templetMonitor.getDirections());
			t.setModels(templetMonitor.getModels());
			t.setName(templetMonitor.getName());
			t.setPartsNumber(templetMonitor.getPartsNumber());
			t.setResPerson(templetMonitor.getResPerson());
			t.setSpecification(templetMonitor.getSpecification());
			t.setUnit(templetMonitor.getUnit());
			t.setMonitorType("");
			return totalDao.update(t);
		}
		
		if(t.getMonitorType()!= null && t.getMonitorType().equals("add")){//添加不同意，删除
			t.setParent(null);
			return totalDao.delete(t);
		}
		if(t.getMonitorType()!= null && t.getMonitorType().equals("delete")){//删除不同意，取消状态
			t.setMonitorType("");
			return totalDao.update(t);
		}
		return false;
	}
	
	@Override
	public List<Templet> searchByModel(Templet templet, String resPerson) {
		// TODO Auto-generated method stub
		if(templet == null || templet.getModels() == null){
			return null;
		}
		List<Object> names = null;
		boolean b = false;
		if(resPerson == null){
			names = totalDao.createQuerySelect(null, "select id,partsNumber from ta_bp_Templet where parentId=0 and models like ?", "%" + templet.getModels() + "%");
		} else {
			String sql = "select id, partsNumber, resPerson from ta_bp_Templet where parentId=0 and resPerson=? and models like ?";
			names = totalDao.createQuerySelect(null, sql, resPerson,"%" +  templet.getModels() + "%");
			b = true;
		}
		List<Templet> templets = new ArrayList<Templet>();
		for (int i = 0; i < names.size(); i++) {
			Object[] arr = (Object[])names.get(i);
			Templet t = new Templet();
			t.setId(Integer.parseInt(arr[0].toString()));
			t.setPartsNumber(arr[1].toString());
			if(b){
				t.setResPerson(arr[2].toString());
			}
			templets.add(t);
		}
		
		return templets;
	}

	@Override
	public List<String> getModels(String resPerson) {
		List<String> templets = null;
		if(resPerson == null){
			String sql = "select distinct models from ta_bp_Templet";
			templets = totalDao.createQuerySelect(null, sql );
		} else {
			String sql = "select distinct models from ta_bp_Templet where resPerson = ?";
			templets = totalDao.createQuerySelect(null, sql, resPerson);
		}
	
		return templets;
	}


}
