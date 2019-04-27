package com.task.ServerImpl.renshi;

/*
 * 李聪   2015-07-16
 * 
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.renshi.Dimission_ZhengYiServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Users;
import com.task.entity.renshi.DimissionLog;
import com.task.entity.renshi.Dimission_ZhengYi;
import com.task.util.Util;

public class Dimission_ZhengYiServerImpl implements Dimission_ZhengYiServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public String addDimission_ZhengYi(DimissionLog dimissionLog,
			Dimission_ZhengYi dimission_ZhengYi) {
		// TODO Auto-generated method stub
		boolean bool = false;
		if (dimissionLog != null) {
			dimissionLog = (DimissionLog) totalDao.getObjectById(
					DimissionLog.class, dimissionLog.getId());
			if (dimissionLog != null) {
				if (dimission_ZhengYi != null) {
					Dimission_ZhengYi olddimissionZhengYi = (Dimission_ZhengYi) totalDao
							.getObjectByCondition(
									"from Dimission_ZhengYi where codeId=? and zhengyi_Status in ('未审核','审批中')",
									dimissionLog.getCodeId());
					if (olddimissionZhengYi != null) {
						return "由于您当前有正在审核或已通过的离职工资单，无法重复提交！！！";
					}
					Users addUserName = Util.getLoginUser();
					if (addUserName!=null) {
						dimission_ZhengYi.setAddUserName(addUserName.getName());
						dimission_ZhengYi.setAddUserId(addUserName.getId());
					}
					dimission_ZhengYi.setCodeId(dimissionLog.getCodeId());
					dimission_ZhengYi.setZhengyi_Status("未审核");
					dimission_ZhengYi.setAddTime(Util.getDateTime());
					dimission_ZhengYi.setDimissionLogs(dimissionLog);
					bool = totalDao.save(dimission_ZhengYi);// 添加入数据库
					if (bool) {
						dimissionLog.setZhengyi_Status("已填写");
						totalDao.update(dimissionLog);
					}
				}
			} else {
				return "添加对象为空";
			}

			// 调用审批流程
			String processName = "离职工资单";
			Integer epId = null;
			try {
				epId = CircuitRunServerImpl.createProcess(processName,
						Dimission_ZhengYi.class, dimission_ZhengYi.getId(),
						"zhengyi_Status", "id",
						"dimission_ZhengYiAction_toselect.action?dimissionZhengYi.id="
								+ dimission_ZhengYi.getId(), dimissionLog
								.getDept()
								+ "部门  "
								+ dimissionLog.getName()
								+ " 离职工资单，请您审批！", true);
				if (epId != null && epId > 0) {
					dimission_ZhengYi.setEpId(epId);
					totalDao.update(dimission_ZhengYi);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			return "不存在该条信息，请确认后重试！";
		}
		if (bool) {
			return "true";
		}
		return "数据异常，添加失败。请检查后再试！";
	}

	@Override
	public boolean deleteDimission_ZhengYi(Integer id) {
		// TODO Auto-generated method stub
		Dimission_ZhengYi dimissionZhengYi = getDimission_ZhengYiById(id);
		if (dimissionZhengYi != null) {
			CircuitRunServerImpl.deleteCircuitRun(dimissionZhengYi.getEpId());
			return totalDao.delete(dimissionZhengYi);
		}
		return false;
	}

	// 分页查询所有
	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, Object> findDimission_ZhengYisByCondition(
			Dimission_ZhengYi dimissionZhengYi, int pageNo, int pageSize) {
		if (dimissionZhengYi == null) {
			dimissionZhengYi = new Dimission_ZhengYi();
		}

		String hql = totalDao.criteriaQueries(dimissionZhengYi, null);
		hql += " order by id desc";
		List listInt = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		// Object[] o = { list, count };
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listInt);
		map.put(2, count);
		return map;
	}

	@Override
	public Dimission_ZhengYi getDimission_ZhengYiById(Integer id) {
		Object o = totalDao.getObjectById(Dimission_ZhengYi.class, id);
		if (o != null) {
			return (Dimission_ZhengYi) o;
		}
		return null;
	}

	@Override
	public boolean updateDimission_ZhengYi(Dimission_ZhengYi dimissionZhengYi) {
		Dimission_ZhengYi dimissionZhengYi2 = getDimission_ZhengYiById(dimissionZhengYi
				.getId());
		if (dimissionZhengYi2 != null) {
			dimissionZhengYi2.setJz_Time(dimissionZhengYi.getJz_Time());
			dimissionZhengYi2.setJxr_Time(dimissionZhengYi.getJxr_Time());
			dimissionZhengYi2.setJx_Money(dimissionZhengYi.getJx_Money());
			dimissionZhengYi2.setGw_Money(dimissionZhengYi.getGw_Money());
			dimissionZhengYi2.setJiangj_Money(dimissionZhengYi
					.getJiangj_Money());
			dimissionZhengYi2.setAdd_up_number(dimissionZhengYi
					.getAdd_up_number());
			dimissionZhengYi2.setBc_Money(dimissionZhengYi.getBc_Money());
			dimissionZhengYi2.setDaxie(dimissionZhengYi.getDaxie());
			dimissionZhengYi2.setAddup_yf(dimissionZhengYi.getAddup_yf());
			dimissionZhengYi2.setButie(dimissionZhengYi.getButie());
			dimissionZhengYi2.setButie_shuoming(dimissionZhengYi
					.getButie_shuoming());
			dimissionZhengYi2.setSbjf_jzTime(dimissionZhengYi.getSbjf_jzTime());
			dimissionZhengYi2.setBuzu_min(dimissionZhengYi.getBuzu_min());
			dimissionZhengYi2.setPension(dimissionZhengYi.getPension());
			dimissionZhengYi2.setYiliao(dimissionZhengYi.getYiliao());
			dimissionZhengYi2.setShiye(dimissionZhengYi.getShiye());
			dimissionZhengYi2.setFund(dimissionZhengYi.getFund());
			dimissionZhengYi2.setRent(dimissionZhengYi.getRent());
			dimissionZhengYi2.setOther(dimissionZhengYi.getOther());
			dimissionZhengYi2.setReal_money(dimissionZhengYi.getReal_money());
			dimissionZhengYi2.setRemark(dimissionZhengYi.getRemark());
			dimissionZhengYi2.setCanfei(dimissionZhengYi.getCanfei());
			dimissionZhengYi2.setUpdateTime(Util.getDateTime());
			if ("打回".equals(dimissionZhengYi2.getZhengyi_Status())) {
				CircuitRunServerImpl.updateCircuitRun(dimissionZhengYi2
						.getEpId());
				dimissionZhengYi2.setZhengyi_Status("审批中");
			}
			return totalDao.update(dimissionZhengYi2);
		}
		return false;
	}

	@Override
	public List<Dimission_ZhengYi> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
