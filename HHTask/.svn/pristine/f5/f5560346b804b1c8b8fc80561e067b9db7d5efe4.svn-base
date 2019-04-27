package com.task.ServerImpl.dangan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.dangan.DanganServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Users;
import com.task.entity.dangan.ArchiveUnarchiverAplt;
import com.task.entity.dangan.DangAn;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class DanganServerImpl implements DanganServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public String addDangAn(DangAn dangan, List<ArchiveUnarchiverAplt> aplt) {
		// TODO Auto-generated method stub
		boolean b = false;
		Users users = null;
		if (dangan!=null&&dangan.getSqName()!=null&&dangan.getSqDept()!=null&&!"".equals(dangan.getSqName())&&!"".equals(dangan.getSqDept()))
			users = (Users) totalDao.getObjectByCondition("from Users where name = ? and dept = ?", dangan.getSqName(),dangan.getSqDept());
		else
			users = Util.getLoginUser();
		dangan.setSquserId(users.getId());
		dangan.setSqCardId(users.getCardId());
		dangan.setSqCode(users.getCode());
		dangan.setSqDept(users.getDept());
		dangan.setSqName(users.getName());
		dangan.setAddTime(Util.getDateTime());
		dangan.setStatus("未审批");
		dangan.setUseStatus("未使用");
		//保存存取档案信息
		if (aplt!=null&&aplt.size()>0) {
			for(ArchiveUnarchiverAplt aplt2 : aplt){
				if (aplt2!=null) {
					aplt2.setDangan(dangan);
					aplt2.setCardId(users.getCardId());//卡号
					aplt2.setUseType("未使用");
					aplt2.setAddTime(Util.getDateTime());
					totalDao.save(aplt2);
				}
			}
		}
		b = totalDao.save(dangan);
		
		/****************** 调用审批流程 *****************/
		String processName = "档案存取申请";
		Integer epId = null;
		//DanganAction_toselect.action?dangAn.id=" + dangan.getId()
		try {
			epId = CircuitRunServerImpl.createProcess(processName,
					DangAn.class, dangan.getId(), "status", "id",
					"DanganAction_toSelect.action?dangAn.id=" + dangan.getId(),
					dangan.getSqDept() + "部门 " + dangan.getSqName()
							+ " 档案存档，请您审批！", true);
			if (epId != null && epId > 0) {
				dangan.setEpId(epId);
				totalDao.update(dangan);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (b) {
			return "申请成功";
		}
		return "申请失败";
	}
	@Override
	public String addCwDangAn(DangAn dangan, List<ArchiveUnarchiverAplt> aplt) {
		// TODO Auto-generated method stub
		boolean b = false;
		Users users = null;
		if (dangan!=null&&dangan.getSqName()!=null&&dangan.getSqDept()!=null&&!"".equals(dangan.getSqName())&&!"".equals(dangan.getSqDept()))
			users = (Users) totalDao.getObjectByCondition("from Users where name = ? and dept = ?", dangan.getSqName(),dangan.getSqDept());
		else
			users = Util.getLoginUser();
		dangan.setSquserId(users.getId());
		dangan.setSqCardId(users.getCardId());
		dangan.setSqCode(users.getCode());
		dangan.setSqDept(users.getDept());
		dangan.setSqName(users.getName());
		dangan.setAddTime(Util.getDateTime());
		dangan.setStatus("未审批");
		dangan.setUseStatus("未使用");
		dangan.setQutype("财务档案");
		//保存存取档案信息
		if (aplt!=null&&aplt.size()>0) {
			for(ArchiveUnarchiverAplt aplt2 : aplt){
				if (aplt2!=null) {
					aplt2.setDangan(dangan);
					aplt2.setCardId(users.getCardId());//卡号
					aplt2.setUseType("未使用");
					aplt2.setAddTime(Util.getDateTime());
					totalDao.save(aplt2);
				}
			}
		}
		b = totalDao.save(dangan);
		
		/****************** 调用审批流程 *****************/
		String processName = "财务档案存取申请";
		Integer epId = null;
		//DanganAction_toselect.action?dangAn.id=" + dangan.getId()
		try {
			epId = CircuitRunServerImpl.createProcess(processName,
					DangAn.class, dangan.getId(), "status", "id",
					"DanganAction_toSelect.action?tag=pz&dangAn.id=" + dangan.getId(),
					dangan.getSqDept() + "部门 " + dangan.getSqName()
					+ " 财务档案存取申请，请您审批！", true);
			if (epId != null && epId > 0) {
				dangan.setEpId(epId);
				totalDao.update(dangan);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (b) {
			return "申请成功";
		}
		return "申请失败";
	}
	@Override
	public String addFpDangAn(DangAn dangan, List<ArchiveUnarchiverAplt> aplt) {
		// TODO Auto-generated method stub
		Users users = null;
		if (dangan!=null&&dangan.getSqName()!=null&&dangan.getSqDept()!=null&&!"".equals(dangan.getSqName())&&!"".equals(dangan.getSqDept()))
			users = (Users) totalDao.getObjectByCondition("from Users where name = ? and dept = ?", dangan.getSqName(),dangan.getSqDept());
		else
			users = Util.getLoginUser();
		dangan.setSquserId(users.getId());
		dangan.setSqCardId(users.getCardId());
		dangan.setSqCode(users.getCode());
		dangan.setSqDept(users.getDept());
		dangan.setSqName(users.getName());
		dangan.setAddTime(Util.getDateTime());
		dangan.setStatus("未审批");
		dangan.setUseStatus("未使用");
		dangan.setQutype("发票");
		//保存存取档案信息
		if (aplt!=null&&aplt.size()>0) {
			for(ArchiveUnarchiverAplt aplt2 : aplt){
				if (aplt2!=null) {
					aplt2.setDangan(dangan);
					aplt2.setCardId(users.getCardId());//卡号
					aplt2.setUseType("未使用");
					aplt2.setAddTime(Util.getDateTime());
					totalDao.save(aplt2);
				}
			}
		}
		totalDao.save(dangan);
		/****************** 调用审批流程 *****************/
		String processName = "财务发票存取申请";
		Integer epId = null;
		//DanganAction_toselect.action?dangAn.id=" + dangan.getId()
		try {
			epId = CircuitRunServerImpl.createProcess(processName,
					DangAn.class, dangan.getId(), "status", "id",
					"DanganAction_toSelect.action?tag=fp&dangAn.id=" + dangan.getId(),
					dangan.getSqDept() + "部门 " + dangan.getSqName()
					+ " 财务发票存取申请，请您审批！", true);
			if (epId != null && epId > 0) {
				dangan.setEpId(epId);
				if (totalDao.update(dangan)) {
					return "申请成功";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "申请失败";
	}

	@Override
	public DangAn byIdDangan(Integer id) {
		// TODO Auto-generated method stub
		if (id > 0 && id != null) {
			return (DangAn) totalDao.getObjectById(DangAn.class, id);
		}
		return null;
	}

	@Override
	public String deleteDangAn(Integer id) {
		// TODO Auto-generated method stub
		DangAn an = byIdDangan(id);
		if (an != null) {
			CircuitRunServerImpl.deleteCircuitRun(an.getEpId());
			if (totalDao.delete(an)) {
				return "删除成功";
			}
		}
		return "删除失败";
	}

	@Override
	public Map<Integer, Object> findDangAnByCondition(DangAn dangan,
			int pageNo, int pageSize, String tag) {
		// TODO Auto-generated method stub
		if (dangan == null) {
			dangan = new DangAn();
		}
		String hql = totalDao.criteriaQueries(dangan, null);
		if("pz".equals(tag)){
			hql += " and qutype = '财务档案' order by id desc";
		}else if("yz".equals(tag)){
			hql += " and qutype = '印章申请' order by id desc";
		}else if("fp".equals(tag)){
			hql += " and qutype = '发票' order by id desc";
		}else {
			hql += " and qutype is null order by id desc";
		}
		List listInt = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listInt);
		map.put(2, count);
		return map;
	}

	@Override
	public String updateDangAn(DangAn dangan) {
		// TODO Auto-generated method stub
		DangAn dangan1 = byIdDangan(dangan.getId());
		if (dangan1 != null) {
			BeanUtils.copyProperties(dangan, dangan1, new String[] { "id",
					"addTime", "sqName", "sqDept", "sqCardId", "sqCode",
					"squserId", "epId", "status", "useStatus", "yanzheng",
					"shTime", "inTime", "outTime", "useDate", "useDateNum" });
			dangan1.setUpdateTime(Util.getDateTime());
			if (totalDao.update(dangan1)) {
				if (dangan1.getEpId() != null && dangan1.getEpId() > 0
						&& "打回".equals(dangan1.getStatus())) {
					if (CircuitRunServerImpl
							.updateCircuitRun(dangan1.getEpId())) {
						dangan1.setStatus("未审批");
						totalDao.update(dangan1);
					}
				}
				return "修改成功";
			} else {
				return "修改失败";
			}
		}
		return "修改失败";
	}

	@Override
	public String findPriceNum() {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();
		List<String> list = totalDao.query("select distinct(fileNumber) from Price where fileNumber is not null and fileNumber <> ''");
		for (String d : list) {
			if (d!=null) {
				buffer.append(d.toString()+"|");
			}
		}
		return buffer.toString();
	}

	@Override
	public List findaceNum(String name) {
		// TODO Auto-generated method stub
		List list = totalDao.query("from AccessEquipment where equipmentName=?",name);
		return list;
	}

	@Override
	public String findcaeNameNum() {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();
		List<String> list = totalDao.query("select distinct(equipmentName) from AccessEquipment where equipmentDaoType = '档案'");
		for (String d : list) {
			if (d!=null) {
				buffer.append(d.toString()+"|");
			}
		}
		return buffer.toString();
	}

	@Override
	public List byIdFindDangan(Integer id) {
		// TODO Auto-generated method stub
		if (id != null && id > 0) {
			return totalDao.query("from ArchiveUnarchiverAplt where ta_dangan = ?", id);
		}
		return null;
	}

	@Override
	public List byIdArchiveUnarchiverAplt() {
		// TODO Auto-generated method stub
		List<ArchiveUnarchiverAplt> list = totalDao.findAllByPage("from ArchiveUnarchiverAplt where useType <> '已失效' and dangan.id in (select id from DangAn where status = '同意' and useStatus <> '已进门') order by id desc", 1, 15);
		for (ArchiveUnarchiverAplt a : list) {
			DangAn da = a.getDangan();
			a.getDangan().setId(da.getId());
		}
		return list;
	}
	
	@Override
	public ArchiveUnarchiverAplt findaceNum_1() {
		// TODO Auto-generated method stub
		ArchiveUnarchiverAplt aplt = (ArchiveUnarchiverAplt) totalDao.getObjectByCondition("from ArchiveUnarchiverAplt");
		DangAn dangan = byIdDangan(aplt.getDangan().getId());
		aplt.setDangan(dangan);
		return aplt;
		}

}
