package com.task.ServerImpl.caiwu.noncore;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.caiwu.noncore.NoncorePaybaleServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Users;
import com.task.entity.caiwu.noncore.EnergyConsumption;
import com.task.entity.caiwu.noncore.NonCorePayable;
import com.task.entity.caiwu.noncore.PayableType;
import com.task.util.Util;
/**
 * 非主营
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
public class NoncorePaybaleServerImpl implements NoncorePaybaleServer {

	private TotalDao totalDao;
	
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public String deleteNoncorePayable(Integer id) {
		// TODO Auto-generated method stub
		NonCorePayable corePayable = getNoncroePayableById(id);
		if(corePayable!=null){
			// 删附件
			if (corePayable.getFujian() != null) {
				File oldFile = new File(ServletActionContext
						.getServletContext().getRealPath("")
						+ "/upload/file/feiZY/" + corePayable.getFujian());
				if (oldFile.exists()) {
					oldFile.delete();
				}
			}
			if(totalDao.delete(corePayable)){
				return "删除成功！";
			}
		}
		return "删除失败！";
	}

	@Override
	public Object[] findNoncorePayableList(NonCorePayable nonCorePayable,
			String startDate, String endDate, Integer cpage, Integer pageSize,
			String tag) {
		// TODO Auto-generated method stub
		if (nonCorePayable == null) {
			nonCorePayable = new NonCorePayable();
		}
		String sql = "";
		Users u = Util.getLoginUser();
		if("dept".equals(tag)){
			sql += " saveDept = '"+u.getDept()+"'";
		}else if ("all".equals(tag)) {
		}else if ("admin".equals(tag)) {
		}else if ("yifu".equals(tag)) {
			sql += " realfukuanJIne>0";
		}else {
			sql += " saveUserId = "+u.getId();
		}
		String hql = totalDao.criteriaQueries(nonCorePayable, sql);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		int count = totalDao.getCount(hql);// 总条数
		Object [] o = {list ,count};
		return o;
	}

	@Override
	public NonCorePayable getNoncroePayableById(Integer id) {
		// TODO Auto-generated method stub
		return (NonCorePayable) totalDao.getObjectById(NonCorePayable.class, id);
	}

	@Override
	public String saveNoncorePayable(NonCorePayable n) {
		// TODO Auto-generated method stub
		if(n!=null){
			n.setSaveTime(Util.getDateTime());
			Users u = Util.getLoginUser();
			if(u!=null){
				n.setSaveUserId(u.getId());
				n.setSaveUser(u.getName());
				n.setSaveDept(u.getDept());
			}
			n.setRealfukuanJIne(0f);
//			if(n.getRealfukuanJIne()>n.getYingfukuanJIne()){
//				return "实付金额不能大于应付金额";
//			}
			n.setStatus("未审批");
			totalDao.save(n);
//			if(){
//				return "添加成功！";
//			}
			
			String processName = "非主营业务应付申请";
			Integer epId = null;
			try {
				epId = CircuitRunServerImpl.createProcess(processName,
						NonCorePayable.class, n.getId(),
						"status", "id",
						"NoncorePayableAction!toselect.action?corePayable.id="
								+ n.getId(), u.getDept() + "部门 "
								+ u.getName()
								+ " 非主营业务应付申请，请您审批！", true);
				if (epId != null && epId > 0) {
					n.setEpId(epId);
					if(totalDao.update(n)){
						return "添加成功！";
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "添加失败！";
	}

	@Override
	public String updateNoncore(NonCorePayable nonCorePayable) {
		// TODO Auto-generated method stub
		NonCorePayable corePayable2 = getNoncroePayableById(nonCorePayable.getId());
		if(corePayable2!=null){
			BeanUtils.copyProperties(nonCorePayable, corePayable2,
					new String[] { "id", "saveTime","saveDept", "saveUser", "epId", "status"});
			if(corePayable2.getRealfukuanJIne()>corePayable2.getYingfukuanJIne()){
				return "实付金额不能大于应付金额";
			}
			if(totalDao.update(corePayable2)){
				return "修改成功！";
			}
		}
		return "修改失败！";
	}

	@Override
	public EnergyConsumption saveEnergyConsumption(EnergyConsumption consumption) {
		// TODO Auto-generated method stub
		if(consumption!=null){
			Users u = Util.getLoginUser();
			consumption.setSaveUser(u.getName());
			consumption.setSaveTime(Util.getDateTime());
			if(totalDao.save(consumption)){
				return consumption;
			}
		}
		return null;
	}

	@Override
	public Object[] findEnergyConsumptionList(
			EnergyConsumption energyConsumption, int parseInt, int pageSize) {
		// TODO Auto-generated method stub
		if (energyConsumption == null) {
			energyConsumption = new EnergyConsumption();
		}
		String hql = totalDao.criteriaQueries(energyConsumption, null);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);// 总条数
		Object [] o = {list ,count};
		return o;
	}

	@Override
	public String deleteEner(Integer id) {
		// TODO Auto-generated method stub
		PayableType en = getPayableTypeid(id);
		if(en!=null){
			en.setUsersList(null);
			if(totalDao.delete(en)){
				return "删除成功！";
			}
		}
		return "删除失败！";
	}

	@Override
	public EnergyConsumption getEnerByid(Integer id) {
		// TODO Auto-generated method stub
		return (EnergyConsumption) totalDao.getObjectById(EnergyConsumption.class, id);
	}

	@Override
	public String updateEner(EnergyConsumption consumption) {
		// TODO Auto-generated method stub
		EnergyConsumption en = getEnerByid(consumption.getId());
		BeanUtils.copyProperties(consumption, en,
				new String[] { "id", "saveUser",
				"saveTime"});
		if(en!=null){
			if(totalDao.update(en)){
				return "修改成功";
			}
		}
		return "修改失败！";
	}

	@Override
	public String savePayableType(PayableType type) {
		// TODO Auto-generated method stub
		if(type!=null&&type.getType()!=null&&!"".equals(type.getType())){
			PayableType payableType = (PayableType) totalDao.getObjectByCondition("from PayableType where type = ?", type.getType());
			if(payableType==null){
				if(totalDao.save(type)){
					return "添加成功！";
				}
			}else {
				return "已有类型，请勿重新添加！";
			}
		}
		return "添加失败!";
	}

	@Override
	public List findPayable() {
		// TODO Auto-generated method stub
		return totalDao.query("from PayableType order by id desc");
	}

	@Override
	public String findPayableType() {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();
		List<String> list = totalDao.query("select distinct(type) from PayableType where id in (select p.id from PayableType p join p.usersList u where u.id =?)",Util.getLoginUser().getId());
		for (String d : list) {
			if (d!=null) {
				buffer.append(d.toString()+"|");
			}
		}
		return buffer.toString();
	}

	@Override
	public String querenNoncore(NonCorePayable nonCorePayable) {
		// TODO Auto-generated method stub
		NonCorePayable corePayable = getNoncroePayableById(nonCorePayable.getId());
		if(corePayable!=null){
			corePayable.setRealfukuanJIne(nonCorePayable.getRealfukuanJIne());
			corePayable.setFukuaiDate(nonCorePayable.getFukuaiDate());
			if(totalDao.update(corePayable)){
				return "确认成功！";
			}
		}
		return "确认失败！";
	}

	@Override
	public PayableType getPayableTypeid(Integer id) {
		// TODO Auto-generated method stub
		return (PayableType) totalDao.getObjectById(PayableType.class, id);
	}
	
	@Override
	public Map<Integer, Object> getUserPaybaleMap(Integer id, int cpage,
			int pageSize) {
		// TODO Auto-generated method stub
		Object o = totalDao.getObjectById(Users.class, id);
		if (o != null) {
			Users users = (Users) o;
			Set<PayableType> pgSet = users.getPayableTypes();
			List<PayableType> hadList = new ArrayList<PayableType>();// 存放已绑定的类型
			List<PayableType> unHadList = new ArrayList<PayableType>();// 存放未绑定的类型
			List<PayableType> pgAll = new ArrayList<PayableType>();// 存放所有类型
			for (PayableType pg : pgSet) {
				hadList.add(pg);
			}
			List<PayableType> all = totalDao.query("from PayableType");// 获取所有的技能系数
			if (all.size() > 0) {
				for (PayableType o2 : all) {
					pgAll.add(o2);
				}
			}
			pgAll.removeAll(hadList);// 所有的技能系数减去已绑定的技能系数就是未绑定的技能系数

			// 对未绑定的技能系数进行分页
			int start = (cpage - 1) * pageSize;
			int end = cpage * pageSize - 1;
			int totalpage = (pgAll.size() + pageSize - 1) / pageSize;
			for (int i = start; i < pgAll.size() & i <= end; i++) {
				unHadList.add(pgAll.get(i));
			}
			Map<Integer, Object> map = new HashMap<Integer, Object>();
			map.put(1, hadList);
			map.put(2, unHadList);
			map.put(3, users);
			map.put(4, totalpage);
			return map;
		}
		return null;
	}

	@Override
	public String linkUserPapy(Integer id, int[] checkboxs) {
		// TODO Auto-generated method stub
		Users users = (Users) totalDao.getObjectById(Users.class, id);
		if (users != null) {
			boolean b = true;
			List<Integer> ids = new ArrayList<Integer>();
			if (checkboxs != null) {
				for (Integer pgId : checkboxs) {
					ids.add(pgId);
					Object o1 = totalDao.getObjectById(PayableType.class,pgId);
					if (o1 != null) {
						PayableType pro = (PayableType) o1;
						Set<Users> userSet = pro.getUsersList();
						userSet.add(users);
						pro.setUsersList(userSet);
						b = b & totalDao.update(pro);
					}
				}
			}
			Set<PayableType> pSet = users.getPayableTypes();
			if (pSet.size() > 0) {
				for (PayableType p : pSet) {
					if (!ids.contains(p.getId())) {
						Object o2 = totalDao.getObjectById(
								PayableType.class, p.getId());
						if (o2 != null) {
							PayableType pro = (PayableType) o2;
							Set<Users> userSet = pro.getUsersList();
							userSet.remove(users);
							pro.setUsersList(userSet);
							b = b & totalDao.update(pro);
						}
					}
				}
			}
			if(b)
				return "绑定成功";
		}
		return "绑定失败！";
	}
	
	
	
}
