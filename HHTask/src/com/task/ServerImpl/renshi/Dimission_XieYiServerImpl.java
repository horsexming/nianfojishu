package com.task.ServerImpl.renshi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.renshi.Dimission_XieYiServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Provision;
import com.task.entity.Users;
import com.task.entity.renshi.DimissionLog;
import com.task.entity.renshi.DimissionNotice;
import com.task.entity.renshi.Dimission_XieYi;
import com.task.util.Util;

@SuppressWarnings( { "unchecked", "deprecation" })
public class Dimission_XieYiServerImpl implements Dimission_XieYiServer {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 增
	@Override
	public String addDimission_XieYi(DimissionLog dimissionLog,Users users,
			Dimission_XieYi dimissionXieYi, String[] proContent, String number) {

		boolean bool = false;
		if (dimissionLog.getId() != null || users.getId() != null) {
			int ii = 0;
			String dept = "";
			String name = "";
			if (dimissionLog.getId() != null) {
				dimissionLog = (DimissionLog) totalDao.getObjectById(
						DimissionLog.class, dimissionLog.getId());
				ii = dimissionLog.getCodeId();
				dept = dimissionLog.getDept();
				name = dimissionLog.getName();
			} else if (users.getId() != null) {
				users = getUsersById(users.getId());
				ii = users.getId();
				dept = users.getDept();
				name = users.getName();
			} else {
				return "添加对象为空";
			}

			if (dimissionXieYi != null) {
				Dimission_XieYi olddimissionXieYi = (Dimission_XieYi) totalDao
						.getObjectByQuery(
								"from Dimission_XieYi where codeId=? and xieyi_Status in ('未审核','审批中','同意')",
								ii);//申请人用户Id
				if (olddimissionXieYi != null) {
					return "由于您当前有正在审核或已通过的劳动关系终止协议，无法重复提交！！！";
				}
				/*String createdate1 = Util.getDateTime("yyyyMMdd");//生成合同编号
				 String[] a = createdate1.split("-");//将字符串以-分隔，一次放入数组a中
				 createdate1 = a[0] + a[1] + a[2];
				String hql = "select max(xieyi_number) from Dimission_XieYi WHERE xieyi_number LIKE "
						+ "'" + createdate1 + "%'";
				String max_number = (String) this.totalDao
						.getObjectByCondition(hql);
				if (max_number != null && !"".equals(max_number)) {
					long number2 = Long.parseLong(max_number) + 1;
					String number3 = Long.toString(number2);
					dimissionXieYi.setXieyi_number(number3);
				} else {
					String number2 = createdate1 + "001";
					dimissionXieYi.setXieyi_number(number2);
				}*/
				if (proContent != null) {
					for (int i = 0; i < proContent.length; i++) {
						Provision provision = new Provision();
						provision.setContent(proContent[i]);// 内容
						provision.setProvisionStatus("lz_private");// 状态
						provision.setDimissionXieYi(dimissionXieYi);// 合同
						totalDao.save(provision);// 为了顺序不会出现混乱，因为先添加条款
					}
				}
//				String number = findIncrementNumber("xieyi");//重新生成一次协议编号
				dimissionXieYi.setXieyi_number(number);
				if(dimissionLog.getId()!=null){
					dimissionXieYi.setCodeId(dimissionLog.getCodeId());
					dimissionXieYi.setDim_xieyi_id(dimissionLog.getId());
				}else {
					dimissionXieYi.setCodeId(users.getId());
				}
				dimissionXieYi.setXieyi_Status("未审核");
				dimissionXieYi.setAddTime(Util.getDateTime());
				bool = totalDao.save(dimissionXieYi);// 添加入数据库
				if (bool&&dimissionLog.getId()!=null) {
					dimissionLog.setXieyi_status("已填写");
				}
			}
		
			// 调用审批流程
			String processName = "终止劳动关系审核";
			Integer epId = null;
			try {
				epId = CircuitRunServerImpl.createProcess(processName,
						Dimission_XieYi.class, dimissionXieYi.getId(),
						"xieyi_status", "id",
						"dimission_XieYiAction_toselect.action?dimissionXieYi.id="
								+ dimissionXieYi.getId(), dept
								+ "部门  "
								+ name
								+ " 劳动关系终止协议，请您审批！", true);
				if (epId != null && epId > 0) {
					dimissionXieYi.setEpId(epId);
					totalDao.update(dimissionXieYi);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return "不存在该条信息，请确认后重试！";
		}
		if (bool) {
			return "true";
		}
		return "数据异常，添加失败。请检查后再试！";
	}

	// 删
	@Override
	public boolean deleteDimission_XieYi(Integer id) {
		Dimission_XieYi dimissionXieYi = getDimission_XieYiById(id);
		if (dimissionXieYi != null) {
			CircuitRunServerImpl.deleteCircuitRun(dimissionXieYi.getEpId());
			DimissionLog dimissionLog = (DimissionLog) totalDao.getObjectById(
					DimissionLog.class, dimissionXieYi.getDim_xieyi_id());
			if (dimissionLog != null) {
				dimissionLog.setXieyi_status("待填写");
			}
			return totalDao.delete(dimissionXieYi);
		}
		return false;
	}

	@Override
	public List<Dimission_XieYi> findAll() {

		return null;
	}

	// 查
	@Override
	public Map<Integer, Object> findDimission_XieYisByCondition(
			Dimission_XieYi dimissionXieYi, int pageNo, int pageSize) {

		if (dimissionXieYi == null) {
			dimissionXieYi = new Dimission_XieYi();
		}
		String hql = totalDao.criteriaQueries(dimissionXieYi, null);
		hql += " order by id desc";
		List listInt = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		// Object[] o = { list, count };
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listInt);
		map.put(2, count);
		return map;
	}

	// 根据id获取对象
	@Override
	public Dimission_XieYi getDimission_XieYiById(Integer id) {

		Object o = totalDao.getObjectById(Dimission_XieYi.class, id);
		if (o != null) {
			return (Dimission_XieYi) o;
		}
		return null;
	}

	// 修改
	@Override
	public boolean updateDimission_XieYi(Dimission_XieYi dimissionXieYi) {

		Dimission_XieYi dimissionXieYi2 = getDimission_XieYiById(dimissionXieYi
				.getId());
		if (dimissionXieYi2 != null) {

			return totalDao.update(dimissionXieYi2);
		}
		return false;
	}

	// 根据离职协议Id查询所对应的离职协议条款
	@Override
	public List findProvision(Dimission_XieYi dimissionXieYi) {

		if (dimissionXieYi != null) {
			String hql = "from Provision where ta_provision_xieyiId=?";
			return totalDao.query(hql, dimissionXieYi.getId());
		}
		return null;
	}

	@Override
	public String addDimissionNotice(DimissionLog dimissionLog,
			DimissionNotice dimissionNotice) {
		if (dimissionLog != null) {
			dimissionLog = (DimissionLog) totalDao.getObjectById(
					DimissionLog.class, dimissionLog.getId());
			if (dimissionLog != null) {
				if (dimissionNotice != null) {
					String number = findIncrementNumber("notice");//重新生成一次协议编号
					dimissionNotice.setNoticeNumber(number);
					dimissionNotice.setAddTime(Util.getDateTime());
					dimissionNotice.setDim_tongzhi_id(dimissionLog.getId());
					if (totalDao.save(dimissionNotice)) {
						dimissionLog.setTongzhi_status("已填写");
						dimissionLog.setTongzhi_addTime(Util.getDateTime());
						return "true";
					} else {
						return "离职通知单添加失败，请检查后添加！";
					}
				} else {
					return "离职通知单对象为空";
				}
			} else {
				return "离职申请单对象为空";
			}
		}
		return "对象为空！添加失败。";
	}

	// 分页查询通知单对象 所有
	@Override
	public Map<Integer, Object> findDimissionNoticesByCondition(
			DimissionNotice dimissionNotice, int pageNo, int pageSize) {
		if (dimissionNotice == null) {
			dimissionNotice = new DimissionNotice();
		}
		String hql = totalDao.criteriaQueries(dimissionNotice, null);
		hql += " order by id desc";
		List listInt = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		// Object[] o = { list, count };
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listInt);
		map.put(2, count);
		return map;
	}

	// 通过id获取对象
	@Override
	public DimissionNotice getDimissionNoticeById(Integer id) {
		if (id != null && id > 0) {
			return (DimissionNotice) totalDao.getObjectById(
					DimissionNotice.class, id);
		}
		return null;
	}

	// 修改
	@Override
	public boolean updateDimissionNotice(DimissionNotice dimissionNotice) {

		DimissionNotice dimissionNotice1 = getDimissionNoticeById(dimissionNotice
				.getId());
		if (dimissionNotice1 != null) {
			dimissionNotice1.setChaosong(dimissionNotice.getChaosong());
			dimissionNotice1.setLizhi(dimissionNotice.getLizhi());
			dimissionNotice1.setBanli_time(dimissionNotice.getBanli_time());
			dimissionNotice1.setZhixin_time(dimissionNotice.getZhixin_time());
			dimissionNotice1.setShebao_time(dimissionNotice.getShebao_time());
			dimissionNotice1.setUpdateTime(Util.getDateTime());
			return totalDao.update(dimissionNotice1);
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteDimissionNotice(Integer id) {
		DimissionNotice dimissionNotice1 = getDimissionNoticeById(id);
		if (dimissionNotice1 != null) {
			DimissionLog dimissionLog1 = (DimissionLog) totalDao.getObjectById(
					DimissionLog.class, dimissionNotice1.getDim_tongzhi_id());
			if (dimissionLog1 != null) {
				dimissionLog1.setTongzhi_status("待填写");
				return totalDao.delete(dimissionNotice1);
			}
		}
		return false;
	}

	// 自动生成合同编号
	@Override
	public String findIncrementNumber(String numberType) {
		String datetime = Util.getDateTime("yyyyMMdd");
		String hql = "";
		if ("notice".equals(numberType)) {// notion为通知单标识，标记跳转页面和编号类型
			hql = "select noticeNumber from DimissionNotice order by id desc";
		} else {
			hql = "select xieyi_number from Dimission_XieYi order by id desc";
		}
		List list = totalDao.findAllByPage(hql, 0, 1);
		String contractNumber = "";
		if (list != null && list.size() > 0) {
			contractNumber = (String) list.get(0);
		}
		if (contractNumber != null && contractNumber.length() > 0) {
			int num = Integer.parseInt(contractNumber.substring(8)) + 1;
			if (num >= 100) {
				contractNumber = datetime + num;
			} else if (num >= 10) {
				contractNumber = datetime + "0" + num;
			} else {
				contractNumber = datetime + "00" + num;
			}
		} else {
			contractNumber = datetime + "001";
		}
		return contractNumber;
	}


	@Override
	public Users getUsersById(int id) {
		// TODO Auto-generated method stub
		return (Users) totalDao.getObjectById(Users.class, id);
	}
	
}
