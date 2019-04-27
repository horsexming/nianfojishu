package com.task.ServerImpl.gongyi;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.entity.TaSopGongwei;
import com.task.entity.Users;
import com.task.entity.gongyi.GongyiGuicheng;
import com.task.entity.gongyi.gongxu.ProcessData;
import com.task.entity.gongyi.vo.GongyiGuichengVo;
import com.task.entity.project.QuotedPrice;
import com.task.entity.project.QuotedProcessInfor;
import com.task.entity.sop.ProcardTemplate;
import com.task.entity.sop.ProcessTemplate;
import com.task.util.Util;
import com.task.Server.gongyi.GongyiGuichengServer;

public class GongyiGuichengServerImpl implements GongyiGuichengServer {
	private TotalDao totalDao;

	/**
	 * 添加工艺规程记录
	 * 
	 * @param gongyiGuicheng
	 * @return
	 */
	public GongyiGuicheng addGongyiGuicheng(GongyiGuicheng gongyiGuicheng) {
		boolean result = totalDao.save(gongyiGuicheng);
		if (result) {
			return gongyiGuicheng;
		} else {
			return null;
		}
	}

	/**
	 * 删除工艺规程记录
	 * 
	 * @param gongyiGuicheng
	 * @return
	 */
	public boolean deleteGongyiGuicheng(GongyiGuicheng gongyiGuicheng) {
		return totalDao.delete(gongyiGuicheng);

	}

	/**
	 * 更新工艺规程记录
	 * 
	 * @param gongyiGuicheng
	 * @return
	 */
	public GongyiGuicheng updateGongyiGuicheng(GongyiGuicheng gongyiGuicheng) {
		GongyiGuicheng gongyiGuichengTemp = this
				.getGongyiGuichengById(gongyiGuicheng.getId());
		if (gongyiGuichengTemp != null) {
			// if("打回".equals(gongyiGuichengTemp.getStatus())){
			// CircuitRunServerImpl.updateCircuitRun(gongyiGuichengTemp.getEpId());
			// }
			String numb = gongyiGuicheng.getNumb();
			String xingbie = gongyiGuicheng.getXingbie();
			Integer jianId = gongyiGuicheng.getJianId();
			String jianNumb = gongyiGuicheng.getJianNumb();
			String jianName = gongyiGuicheng.getJianName();
			String procardStyle = gongyiGuicheng.getProcardStyle();
			Integer pageTotal = gongyiGuicheng.getPageTotal();

			Integer bianzhiId = gongyiGuicheng.getBianzhiId();
			Integer jiaoduiId = gongyiGuicheng.getJiaoduiId();
			Integer shenheId = gongyiGuicheng.getShenheId();
			Integer pizhunId = gongyiGuicheng.getPizhunId();

			String bianzhiName = gongyiGuicheng.getBianzhiName();
			String jiaoduiName = gongyiGuicheng.getJiaoduiName();
			String shenheName = gongyiGuicheng.getShenheName();
			String pizhunName = gongyiGuicheng.getPizhunName();

			Date bianzhiDate = gongyiGuicheng.getBianzhiDate();
			Date jiaoduiDate = gongyiGuicheng.getJiaoduiDate();
			Date shenheDate = gongyiGuicheng.getShenheDate();
			Date pizhunDate = gongyiGuicheng.getPizhunDate();

			Date fachuDate = gongyiGuicheng.getFachuDate();
			String banci = gongyiGuicheng.getBanci();
			String cuntuNumb = gongyiGuicheng.getCuntuNumb();
			String status = gongyiGuicheng.getStatus();
			Integer epId = gongyiGuicheng.getEpId();

			Integer jiagongId = gongyiGuicheng.getJiagongId();
			String jiagongName = gongyiGuicheng.getJiagongName();
			Date jiagongDate = gongyiGuicheng.getJiagongDate();
			Integer pinzhiId = gongyiGuicheng.getPinzhiId();
			String pinzhiName = gongyiGuicheng.getPinzhiName();
			Date pinzhiDate = gongyiGuicheng.getPinzhiDate();
			Integer parentId = gongyiGuicheng.getParentId();
			// 工艺规程类型 批产试纸
			String gongyiGuichengType = gongyiGuicheng.getGongyiGuichengType();
			if (numb != null && !"".equals(numb)) {
				gongyiGuichengTemp.setNumb(numb);
			}
			if (xingbie != null && !"".equals(xingbie)) {
				gongyiGuichengTemp.setXingbie(xingbie);
			}
			if (jianId != null) {
				gongyiGuichengTemp.setJianId(jianId);
			}
			if (jianNumb != null && !"".equals(jianNumb)) {
				gongyiGuichengTemp.setJianNumb(jianNumb);
			}
			if (procardStyle != null && !"".equals(procardStyle)) {
				gongyiGuichengTemp.setProcardStyle(procardStyle);
			}
			if (jianName != null && !"".equals(jianName)) {
				gongyiGuichengTemp.setJianName(jianName);
			}
			if (pageTotal != null && !"".equals(pageTotal)) {
				gongyiGuichengTemp.setPageTotal(pageTotal);
			}

			if (bianzhiId != null) {
				gongyiGuichengTemp.setBianzhiId(bianzhiId);
			}
			if (jiaoduiId != null) {
				gongyiGuichengTemp.setJiaoduiId(jiaoduiId);
			}
			if (shenheId != null) {
				gongyiGuichengTemp.setShenheId(shenheId);
			}
			if (pizhunId != null) {
				gongyiGuichengTemp.setPizhunId(pizhunId);
			}

			if (bianzhiName != null && !"".equals(bianzhiName)) {
				gongyiGuichengTemp.setBianzhiName(bianzhiName);
			}
			if (jiaoduiName != null && !"".equals(jiaoduiName)) {
				gongyiGuichengTemp.setJiaoduiName(jiaoduiName);
			}
			if (shenheName != null && !"".equals(shenheName)) {
				gongyiGuichengTemp.setShenheName(shenheName);
			}
			if (pizhunName != null && !"".equals(pizhunName)) {
				gongyiGuichengTemp.setPizhunName(pizhunName);
			}

			if (bianzhiDate != null) {
				gongyiGuichengTemp.setBianzhiDate(bianzhiDate);
			}
			if (jiaoduiDate != null) {
				gongyiGuichengTemp.setJiaoduiDate(jiaoduiDate);
			}
			if (shenheDate != null) {
				gongyiGuichengTemp.setShenheDate(shenheDate);
			}
			if (pizhunDate != null) {
				gongyiGuichengTemp.setPizhunDate(pizhunDate);
			}

			if (fachuDate != null) {
				gongyiGuichengTemp.setFachuDate(fachuDate);
			}
			if (banci != null && !"".equals(banci)) {
				gongyiGuichengTemp.setBanci(banci);
			}
			if (cuntuNumb != null && !"".equals(cuntuNumb)) {
				gongyiGuichengTemp.setCuntuNumb(cuntuNumb);
			}
			if (status != null && !"".equals(status)) {
				gongyiGuichengTemp.setStatus(status);
			}
			if (epId != null) {
				gongyiGuichengTemp.setEpId(epId);
			}

			if (jiagongId != null) {
				gongyiGuichengTemp.setJiagongId(jiagongId);
			}
			if (jiagongName != null && !"".equals(jiagongName)) {
				gongyiGuichengTemp.setJiagongName(jiagongName);
			}
			if (jiagongDate != null) {
				gongyiGuichengTemp.setJiagongDate(jiagongDate);
			}
			if (pinzhiId != null) {
				gongyiGuichengTemp.setPinzhiId(pinzhiId);
			}
			if (pinzhiName != null && !"".equals(pinzhiName)) {
				gongyiGuichengTemp.setPinzhiName(pinzhiName);
			}
			if (jiagongDate != null) {
				gongyiGuichengTemp.setPinzhiDate(pinzhiDate);
			}
			if (parentId != null) {
				gongyiGuichengTemp.setParentId(parentId);
			}
			if (gongyiGuichengType != null && !"".equals(gongyiGuichengType)) {
				gongyiGuichengTemp.setGongyiGuichengType(gongyiGuichengType);
			}
		}
		boolean result = totalDao.update(gongyiGuichengTemp);
		if (result) {
			return gongyiGuichengTemp;
		} else {
			return null;
		}
	}

	/**
	 * 获得工艺规程记录
	 * 
	 * @param id
	 * @return
	 */
	public GongyiGuicheng getGongyiGuichengById(Integer id) {
		if (id != null) {
			return (GongyiGuicheng) totalDao.getObjectById(
					GongyiGuicheng.class, id);
		}
		return null;
	}

	/**
	 * 获得工艺规程记录集合 根据件号
	 * 
	 * @param jianhao
	 * @return
	 */
	public List getGongyiGuichengListByJianNumb(String jianNumb) {
		String hql = "from GongyiGuicheng g where g.jianNumb=?";
		List list = totalDao.find(hql, new Object[] { jianNumb });
		return list;
	}

	/**
	 * 获得工艺规程记录集合
	 * 
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Object[] findAllGongyiGuicheng(GongyiGuicheng gongyiGuicheng,
			int pageNo, int pageSize) {
		String hql = "from GongyiGuicheng g where 1 = 1";
		hql += " order by g.id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/**
	 * 获得工艺规程记录集合 待提交
	 * 
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	/*
	 * public List findAllGongyiGuichengForDtj(GongyiGuicheng gongyiGuicheng){
	 * Users user=(Users)
	 * ActionContext.getContext().getSession().get(TotalDao.users);// 获得登录用户信息
	 * String hql="from GongyiGuicheng g where 1=1";
	 * hql+=" and g.createUserId='"+user.getId()+"'";
	 * hql+=" and g.status = '未审批' or g.status = '打回'";
	 * hql+=" order by g.id desc"; List list=totalDao.find(hql); return list; }
	 */

	/**
	 * 获得工艺规程记录集合 已提交
	 * 
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	/*
	 * public Object[] findAllGongyiGuichengForYtj(GongyiGuicheng
	 * gongyiGuicheng,int pageNo,int pageSize ){ Users user=(Users)
	 * ActionContext.getContext().getSession().get(TotalDao.users);// 获得登录用户信息
	 * String hql = "from GongyiGuicheng g where 1 = 1";
	 * hql+=" and g.createUserId='"+user.getId()+"'";
	 * hql+=" and g.status != '未审批' and g.status != '打回'";
	 * hql+=" order by g.id desc"; List list = totalDao.findAllByPage(hql,
	 * pageNo, pageSize); int count = totalDao.getCount(hql); Object[] o = {
	 * list, count }; return o; }
	 */

	/**
	 * 获得工艺规程记录集合 待审批
	 * 
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	/*
	 * public List findAllGongyiGuichengForDsp(GongyiGuicheng gongyiGuicheng){
	 * Map<String, Object> resultMap =
	 * CircuitRunServerImpl.findAuditExeNode(GongyiGuicheng.class, false); List
	 * gongyiGuichengListForDsp = null; if (resultMap != null) { List<Integer>
	 * gongyiGuichengIdListForDsp = (List<Integer>) resultMap.get("entityId");
	 * gongyiGuichengListForDsp = new ArrayList(); int
	 * gongyiGuichengIdListForDspSize = gongyiGuichengIdListForDsp.size(); for
	 * (int i = 0; i < gongyiGuichengIdListForDspSize; i++) { Integer
	 * gongyiGuichengId = gongyiGuichengIdListForDsp.get(i); GongyiGuicheng
	 * gongyiGuichengTemp = this.getGongyiGuichengById(gongyiGuichengId);
	 * gongyiGuichengListForDsp.add(gongyiGuichengTemp); } } return
	 * gongyiGuichengListForDsp; }
	 */

	/**
	 * 获得工艺规程记录集合 已审批
	 * 
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	/*
	 * public Object[] findAllGongyiGuichengForYsp(GongyiGuicheng
	 * gongyiGuicheng,int pageNo,int pageSize ){
	 * 
	 * Map<String, Object> resultMap =
	 * CircuitRunServerImpl.findAuditExeNode(GongyiGuicheng.class, true); List
	 * gongyiGuichengListForYsp = null; int gongyiGuichengIdListForYspSize=0; if
	 * (resultMap != null) { List<Integer> gongyiGuichengIdListForYsp =
	 * (List<Integer>) resultMap.get("entityId"); gongyiGuichengListForYsp = new
	 * ArrayList(); gongyiGuichengIdListForYspSize =
	 * gongyiGuichengIdListForYsp.size(); // 分页处理 if (pageNo < 1) { pageNo = 1;
	 * } int start = (pageNo - 1) * pageSize; int end = start + pageSize; for
	 * (int i = 0; i < gongyiGuichengIdListForYspSize; i++) { if (i >= start &&
	 * i < end) { Integer gongyiGuichengId = gongyiGuichengIdListForYsp.get(i);
	 * GongyiGuicheng gongyiGuichengTemp =
	 * this.getGongyiGuichengById(gongyiGuichengId);
	 * gongyiGuichengListForYsp.add(gongyiGuichengTemp); } } } // int count =
	 * totalDao.getCount(null); Object[] o = { gongyiGuichengListForYsp,
	 * gongyiGuichengIdListForYspSize }; return o; }
	 */

	/**
	 * 获得工艺规程记录集合 待编制
	 * 
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List findAllGongyiGuichengForDbz(GongyiGuicheng gongyiGuicheng) {
		Users user = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);// 获得登录用户信息
		String hql = "from GongyiGuicheng g where 1=1";
		hql += " and g.bianzhiId='" + user.getId() + "'";
		hql += " and (g.status = '待编制' or g.status = '打回')";
		hql += " and g.parentId is null";
		hql += " order by g.id desc";
		List list = totalDao.find(hql);
		return list;
	}

	/**
	 * 获得工艺规程记录集合 已编制
	 * 
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Object[] findAllGongyiGuichengForYbz(GongyiGuicheng gongyiGuicheng,
			int pageNo, int pageSize) {
		Users user = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);// 获得登录用户信息
		String hql = "from GongyiGuicheng g where 1 = 1";
		hql += " and g.bianzhiId='" + user.getId() + "'";
		hql += " and (g.status = '已编制' or g.status = '已校对' or g.status = '已审核' or g.status = '已批准')";
		hql += " and g.parentId is null";
		hql += " order by g.id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/**
	 * 获得工艺规程记录集合 待校对
	 * 
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List findAllGongyiGuichengForDjd(GongyiGuicheng gongyiGuicheng) {
		Users user = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);// 获得登录用户信息
		String hql = "from GongyiGuicheng g where 1=1";
		hql += " and g.jiaoduiId='" + user.getId() + "'";
		hql += " and g.status = '已编制'";
		hql += " and g.parentId is null";
		hql += " order by g.id desc";
		List list = totalDao.find(hql);
		return list;
	}

	/**
	 * 获得工艺规程记录集合 已校对
	 * 
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Object[] findAllGongyiGuichengForYjd(GongyiGuicheng gongyiGuicheng,
			int pageNo, int pageSize) {
		Users user = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);// 获得登录用户信息
		String hql = "from GongyiGuicheng g where 1 = 1";
		hql += " and g.jiaoduiId='" + user.getId() + "'";
		hql += " and (g.status = '已校对' or g.status = '已审核' or g.status = '已批准')";
		hql += " and g.parentId is null";
		hql += " order by g.id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/**
	 * 获得工艺规程记录集合 待审核
	 * 
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List findAllGongyiGuichengForDsh(GongyiGuicheng gongyiGuicheng) {
		Users user = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);// 获得登录用户信息
		String hql = "from GongyiGuicheng g where 1=1";
		hql += " and g.shenheId='" + user.getId() + "'";
		hql += " and g.status = '已校对'";
		hql += " and g.parentId is null";
		hql += " order by g.id desc";
		List list = totalDao.find(hql);
		return list;
	}

	/**
	 * 获得工艺规程记录集合 已审核
	 * 
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Object[] findAllGongyiGuichengForYsh(GongyiGuicheng gongyiGuicheng,
			int pageNo, int pageSize) {
		Users user = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);// 获得登录用户信息
		String hql = "from GongyiGuicheng g where 1 = 1";
		hql += " and g.shenheId='" + user.getId() + "'";
		hql += " and (g.status = '已审核' or g.status = '已批准')";
		hql += " and g.parentId is null";
		hql += " order by g.id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/**
	 * 获得工艺规程记录集合 待批准
	 * 
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List findAllGongyiGuichengForDpz(GongyiGuicheng gongyiGuicheng) {
		Users user = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);// 获得登录用户信息
		String hql = "from GongyiGuicheng g where 1=1";
		hql += " and g.pizhunId='" + user.getId() + "'";
		hql += " and g.status = '已审核'";
		hql += " and g.parentId is null";
		hql += " order by g.id desc";
		List list = totalDao.find(hql);
		return list;
	}

	/**
	 * 获得工艺规程记录集合 已批准
	 * 
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Object[] findAllGongyiGuichengForYpz(GongyiGuicheng gongyiGuicheng,
			int pageNo, int pageSize) {
		Users user = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);// 获得登录用户信息
		String hql = "from GongyiGuicheng g where 1 = 1";
		hql += " and g.pizhunId='" + user.getId() + "'";
		hql += " and (g.status = '已批准')";
		hql += " and g.parentId is null";
		hql += " order by g.id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/**
	 * 获得所有的工艺规程记录
	 * 
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Object[] findAllGongyiGuichengForAll(GongyiGuicheng gongyiGuicheng,
			int pageNo, int pageSize) {
		// Users user=(Users)
		// ActionContext.getContext().getSession().get(TotalDao.users);//
		// 获得登录用户信息
		if (gongyiGuicheng == null) {
			gongyiGuicheng = new GongyiGuicheng();
		}
		String hql = totalDao.criteriaQueries(gongyiGuicheng, null);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/**
	 * 获得所有的工艺规程记录
	 * 
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Object[] findAllGongyiGuichengForAll(GongyiGuicheng gongyiGuicheng,
			int pageNo, int pageSize, String sql) {
		// Users user=(Users)
		// ActionContext.getContext().getSession().get(TotalDao.users);//
		// 获得登录用户信息
		if (gongyiGuicheng == null) {
			gongyiGuicheng = new GongyiGuicheng();
		}
		String hql = totalDao.criteriaQueries(gongyiGuicheng, sql);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/**
	 * 获得所有的工艺规程记录 for 看板
	 * 
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Object[] findAllGongyiGuichengForKanban(
			GongyiGuicheng gongyiGuicheng, int pageNo, int pageSize) {
		// Users user=(Users)
		// ActionContext.getContext().getSession().get(TotalDao.users);//
		// 获得登录用户信息
		String hql = "from GongyiGuicheng g where 1 = 1";
		if (gongyiGuicheng != null) {
			String jianName = gongyiGuicheng.getJianName();
			String jianNumb = gongyiGuicheng.getJianNumb();
			String status = gongyiGuicheng.getStatus();
			if (jianName != null && !"".equals(jianName)) {
				hql += " and g.jianName='" + jianName + "'";
			}
			if (jianNumb != null && !"".equals(jianNumb)) {
				hql += " and g.jianNumb like '%" + jianNumb + "%'";
			}
			if (status != null && !"".equals(status)) {
				hql += " and g.status='" + status + "'";
			}
		}
		hql += " and g.parentId is null";
		hql += " order by g.id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/**
	 * 获得工艺流水卡片集合
	 * 
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List findJianNumbForSelect() {
		String hql = "select new map(p.id as id,p.markId as jianNumb,p.proName as jianName) from ProcardTemplate p";
		List list = totalDao.query(hql);
		return list;
	}

	/******************************************* 查询历史版本 ********************************************************/
	/**
	 * 查看历史版本
	 */
	public List getGongyiGuichengListForHistory(GongyiGuicheng gongyiGuicheng) {
		Integer id = gongyiGuicheng.getId();
		// Users user=(Users)
		// ActionContext.getContext().getSession().get(TotalDao.users);//
		// 获得登录用户信息
		String hql = "from GongyiGuicheng g where 1=1";
		// hql+=" and g.shenheId='"+user.getId()+"'";
		// hql+=" and g.status = '已校对'";
		hql += " and g.parentId =" + id;
		hql += " order by g.id desc";
		List list = totalDao.find(hql);
		return list;
	}

	/******************************************* 查询出所有在干件号 ********************************************************/
	/**
	 * 查询出所有在干的件号
	 */
	public List<Map> getJianNumbForZaigan(Integer screenId) {
		String sql = "select distinct(p.markid) as jianNumb from ta_sop_w_ProcessInfor c inner join ta_sop_w_procard p on  c.fk_procardid=p.id and "
				+ " c.status='已领' where c.gongwei in (select gongweihao from ta_sop_gongwei where id in(select gongwei_id from ta_screen_sopgongwei "
				+ "where screen_id=:screenId))";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("screenId", screenId);
		List<Map> jianNumbList = this.totalDao.findBySql(sql, params);
		return jianNumbList;
	}

	/******************************************* 结尾 ********************************************************/
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean addCraftBom(Integer rootId) {
		// TODO Auto-generated method stub
		QuotedPrice qptotal = (QuotedPrice) totalDao.getObjectById(
				QuotedPrice.class, rootId);
		if (qptotal.getStatus() == null || !qptotal.getStatus().equals("项目跟踪")) {
			return false;
		}
		List<QuotedPrice> qpList = totalDao
				.query("from QuotedPrice where rootId=" + rootId);
		if (qpList.size() > 0) {
			Users user = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);// 获得登录用户信息
			boolean b = true;
			for (QuotedPrice qp : qpList) {
				if (qp.getProcardStyle() != null
						&& !qp.getProcardStyle().equals("外购")) {
					List<GongyiGuicheng> ggList = totalDao
							.query("from GongyiGuicheng where jianNumb='"
									+ qp.getMarkId() + "'");
					if (ggList.size() == 0) {// 表示没有同件号
						GongyiGuicheng gongyiGuicheng = new GongyiGuicheng();
						gongyiGuicheng.setStatus("待编制");
						gongyiGuicheng.setCreateUserId(user.getId());
						gongyiGuicheng.setCreateUserName(user.getName());
						gongyiGuicheng.setCreateDate(new Date());
						gongyiGuicheng.setScore(0);
						gongyiGuicheng.setTotalScore(75);
						gongyiGuicheng.setXingbie(qp.getXingbie());
						gongyiGuicheng.setJianId(qp.getId());
						gongyiGuicheng.setJianName(qp.getProName());
						gongyiGuicheng.setJianNumb(qp.getMarkId());
						gongyiGuicheng.setQpRootId(rootId + "");
						gongyiGuicheng.setProcardStyle(qp.getProcardStyle());
						b = b & totalDao.save(gongyiGuicheng);
						Set<QuotedProcessInfor> qpInfoSet = qp.getQpinfor();
						if (b && qpInfoSet != null && qpInfoSet.size() > 0) {
							for (QuotedProcessInfor qpInfo : qpInfoSet) {
								ProcessData pd = new ProcessData();
								pd.setGongxuId(qpInfo.getId());
								pd.setGongxuNo(qpInfo.getProcessNO());
								pd.setGongxuName(qpInfo.getProcessName());
								pd.setShebeiId(qpInfo.getShebeiId());
								pd.setShebeiNo(qpInfo.getShebeiNo());
								pd.setShebeiName(qpInfo.getShebeiName());
								pd.setGongzhuangId(qpInfo.getGongzhuangId());
								pd.setGongzhuangNo(qpInfo
										.getOldgongzhuangNumber());
								pd.setGongzhuangName(qpInfo.getOldgongzhuang());
								pd.setGongyiGuichengId(gongyiGuicheng.getId());
								b = b & totalDao.save(pd);
							}
						}

					}
				}

			}
			if (b) {
				qptotal.setStatus("项目编制");
				return totalDao.update(qptotal);
			}
		}
		return false;
	}

	@Override
	public List<String> findJianNumbsAll() {
		// TODO Auto-generated method stub
		List<String> jianNumbs = totalDao
				.query("select jianNumb from GongyiGuicheng");
		return jianNumbs;
	}

	@Override
	public Map<Integer, Object> getGongyiGuichengByJianNumb(String markId,
			Integer rootId) {
		// TODO Auto-generated method stub
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		List<GongyiGuicheng> ggs = totalDao
				.query("from GongyiGuicheng where jianNumb='" + markId + "'");
		if (ggs.size() > 0) {
			GongyiGuicheng gg = ggs.get(0);
			map.put(1, gg);
			List<Float> countList = totalDao
					.query("select count(*) and jianNumb in (select markId from QuotedPrice where  rootId="
							+ rootId + ")");
			String status = gg.getStatus();
			Users user = Util.getLoginUser();
			if (countList.get(0) > 0) {
				map.put(2, 1);
				// if(status!=null&&status.equals("待编制")&&(gg.getBianzhiId()==null||gg.getBianzhiId().equals(user.getId()))){
				// map.put(2,1);//1表示bom中含有未审批完成的工艺规程，并有权限
				// }else
				// if(status!=null&&status.equals("已编制")&&(gg.getJiaoduiId()==null||gg.getJiaoduiId().equals(user.getId()))){
				// map.put(2,1);//1表示bom中含有未审批完成的工艺规程，并有权限
				// }else
				// if(status!=null&&status.equals("已校对")&&(gg.getShenheId()==null||gg.getShenheId().equals(user.getId()))){
				// map.put(2,1);//1表示bom中含有未审批完成的工艺规程，并有权限
				// }else
				// if(status!=null&&status.equals("已审核")&&(gg.getPizhunId()==null||gg.getPizhunId().equals(user.getId()))){
				// map.put(2,1);//1表示bom中含有未审批完成的工艺规程，并有权限
				// }else
				// if(status!=null&&status.equals("已批准")&&(gg.getPizhunId()==null||gg.getPizhunId().equals(user.getId()))){
				// map.put(2,1);//1表示bom中含有未审批完成的工艺规程，并有权限
				// }else{
				// map.put(2,2);//表示目前状态无权查看
				// }

			} else {
				map.put(2, 0);// 表示bom中的工艺规程已全部审批完成
				// if(status!=null&&status.equals("已批准")&&(gg.getPizhunId()==null||gg.getPizhunId().equals(user.getId()))){
				// map.put(2,0);//0表示bom中的工艺规程已全部审批完成，并有权限
				// }else{
				// map.put(2,2);//表示目前状态无权查看
				// }

			}
			return map;
		}
		return null;
	}

	@Override
	public Integer getBomRootIdById(Integer id) {
		// TODO Auto-generated method stub
		GongyiGuicheng gg = (GongyiGuicheng) totalDao.getObjectById(
				GongyiGuicheng.class, id);
		if (gg != null) {
			if (gg.getQpRootId() != null) {// 如果自身有rootId就用自己的
				return Integer.parseInt(gg.getQpRootId());
			} else {// 如果没有，就查报价bom中状态为工艺控制的同件号的markId的rootId
				List<Integer> rootIdList = totalDao
						.query("select rootId from QuotedPrice where status ='项目编制' and markId='"
								+ gg.getJianNumb() + "'");
				if (rootIdList.size() > 0) {
					return rootIdList.get(0);
				} else {// 如果还没有，就查报价bom中任一同件号的markId的rootId
					List<Integer> rootIdList2 = totalDao
							.query("select rootId from QuotedPrice where markId='"
									+ gg.getJianNumb() + "'");
					if (rootIdList2.size() > 0) {
						return rootIdList2.get(0);
					}
				}
			}
		}
		return null;
	}

	@Override
	public Map<Integer, Object> createSopOrLpBomByRootId(Integer rootId,
			String productStyle) {
		// TODO Auto-generated method stub
		if (rootId == null) {
			return null;
		}
		String enproductStyle = null;
		if ("试制".equals(productStyle)) {
			enproductStyle = "sop";
		} else {
			enproductStyle = "lp";
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		boolean b = true;
		String msg = null;
		List<Integer> sameIdList = totalDao
				.query("select id from ProcardTemplate where markId = (select markId from QuotedPrice where id="
						+ rootId + ")");
		if (sameIdList.size() > 0) {
			b = false;
			msg = "生成" + enproductStyle + "bom失败,已存在同样的bom树";
		} else {
			// 获得报价的总成零件
			QuotedPrice qp = (QuotedPrice) totalDao.getObjectById(
					QuotedPrice.class, rootId);
			if (qp != null) {
				b = copyGongYiGuiChengToProcard(qp, productStyle, null, null, 1);
				if (b) {
					qp.setStatus("完成");
					b = b & totalDao.update(qp);
					msg = "进入" + enproductStyle + "成功";
				}
			} else {
				b = false;
				msg = "此工艺规范没有树形结构不能进入" + enproductStyle + "!";
			}
		}
		map.put(1, b);
		map.put(2, msg);
		return map;
	}

	/**
	 * 通过递归已报价系统为模型将工艺规范转化为sop或者lp的bom
	 * 
	 * @param qp
	 *            报价零件
	 * @param productStyle
	 *            生成类型（sop,lp）
	 * @param rootId
	 *            生成的procardTemplate记录的根Id
	 * @param fatherId
	 *            生成的procardTemplate记录的父Id
	 * @param belongLayer
	 *            生成的procardTemplate记录的层数
	 * @return
	 */
	public boolean copyGongYiGuiChengToProcard(QuotedPrice qp,
			String productStyle, Integer rootId, Integer fatherId,
			Integer belongLayer) {
		boolean b = true;
		if (qp != null && qp.getMarkId() != null) {
			ProcardTemplate fpt = null;
			if (fatherId != null) {
				fpt = (ProcardTemplate) totalDao.getObjectById(
						ProcardTemplate.class, fatherId);
			}
			Integer newrootId = rootId;
			Integer newbelongLayer = belongLayer;
			Integer newfatherId = null;
			if (qp.getProcardStyle() != null
					&& qp.getProcardStyle().equals("外购")) {
				ProcardTemplate pt = new ProcardTemplate();
				// 设置零件属性
				pt.setMarkId(qp.getMarkId());
				pt.setProName(qp.getProName());
				pt.setProcardStyle(qp.getProcardStyle());
				pt.setUnit(qp.getUnit());
				pt.setCarStyle(qp.getCarStyle());
				pt.setStatus(qp.getYucailiaostatus());
				pt.setYuanUnit(qp.getUnit());
				pt.setQuanzi1(qp.getQuanzi1());
				pt.setQuanzi2(qp.getQuanzi2());
				pt.setTrademark(qp.getTrademark());
				pt.setSpecification(qp.getSpecification());
				pt.setCorrCount(qp.getCorrCount());
				pt.setBelongLayer(belongLayer);
				pt.setRootId(newrootId);
				pt.setFatherId(fatherId);
				pt.setProductStyle(productStyle);
				pt.setProcardTemplate(fpt);
				b = b & totalDao.save(pt);
			} else {
				List<GongyiGuicheng> ggList = (List<GongyiGuicheng>) totalDao
						.query("from GongyiGuicheng where parentId is null and jianNumb='"
								+ qp.getMarkId() + "'");
				if (ggList.size() > 0) {
					GongyiGuicheng gg = ggList.get(0);
					List<ProcessData> pdList = totalDao
							.query("from ProcessData where gongyiGuichengId="
									+ gg.getId());
					ProcardTemplate pt = new ProcardTemplate();
					// 设置零件属性
					pt.setMarkId(gg.getJianNumb());
					pt.setProName(gg.getJianName());
					pt.setProcardStyle(gg.getProcardStyle());
					pt.setUnit(qp.getUnit());
					pt.setCarStyle(qp.getCarStyle());
					pt.setStatus(qp.getYucailiaostatus());
					pt.setYuanUnit(qp.getUnit());
					pt.setQuanzi1(qp.getQuanzi1());
					pt.setQuanzi2(qp.getQuanzi2());
					pt.setTrademark(qp.getTrademark());
					pt.setSpecification(qp.getSpecification());
					pt.setCorrCount(qp.getCorrCount());
					pt.setBelongLayer(belongLayer);
					pt.setRootId(newrootId);
					pt.setFatherId(fatherId);
					pt.setProductStyle(productStyle);
					pt.setProcardTemplate(fpt);
					b = b & totalDao.save(pt);
					if (pdList.size() > 0) {
						Set<QuotedProcessInfor> qpInfoSet = qp.getQpinfor();
						Set<ProcessTemplate> processtSet = new HashSet<ProcessTemplate>();
						for (ProcessData pdata : pdList) {
							ProcessTemplate processt = new ProcessTemplate();
							// 设置在工艺规程中找的到的属性
							processt.setProcessNO(pdata.getGongxuNo());
							processt.setProcessName(pdata.getGongxuName());
							if (pdata.getShebeiId() != null
									&& pdata.getShebeiNo() != null) {
								processt.setShebeiId(pdata.getShebeiId());
								processt.setShebeiNo(pdata.getShebeiNo());
								processt.setShebeiName(pdata.getShebeiName());
								processt.setShebeistatus("是");// 设备验证
								List<TaSopGongwei> shebList = totalDao.query(
										"from TaSopGongwei where shebeiCode=?",
										pdata.getShebeiNo());
								if (shebList.size() > 0) {
									TaSopGongwei tsg = shebList.get(0);
									processt.setOptechnologyRate(tsg
											.getCaozJineng());
									processt.setOpCouldReplaceRate(tsg
											.getCaoztihuanrenshu());
									processt.setGzfuheRate(tsg
											.getGongzhuangFuhe());
									processt.setGztechnologyRate(tsg
											.getGongzhuangJineng());
									processt.setGzCouldReplaceRate(tsg
											.getGongzhuangtihuanrenshu());
									processt.setGongwei(tsg.getGongweihao());
								}
							} else {
								processt.setShebeistatus("否");// 设备验证
							}
							if (pdata.getGongzhuangId() != null) {
								processt.setGzstoreId(pdata.getGongzhuangId());
								processt.setNumber(pdata.getGongzhuangNo());
								processt.setMatetag(pdata.getGongzhuangName());
								processt.setGongzhuangstatus("是");// 工装验证
							} else {
								processt.setGongzhuangstatus("否");// 工装验证

							}
							if (pdata.getLiangjuId() != null) {
								processt.setMeasuringId(pdata.getLiangjuId());
								processt.setMeasuringNumber(pdata
										.getLiangjuNo());
								processt.setMeasuringMatetag(pdata
										.getLiangjuName());
								processt.setMeasuring_no(pdata
										.getLiangjuNoForCompany());
								processt.setLiangjustatus("是");// 量具验证
							} else {
								processt.setLiangjustatus("否");// 量具验证
							}
							processt.setProductStyle("自制");// 默认为自制
							processt.setZjStatus("yes");// 默认首检
							processt.setProcessStatus("yes");// 默认并行
							processt.setIsPrice("yes");// 默认参与
							processt.setKaoqingstatus("是");// 默认考勤
							processt.setGuifanstatus("是");// 默认规范
							if (qpInfoSet.size() > 0) {
								for (QuotedProcessInfor qpInfo : qpInfoSet) {
									if (pdata.getGongxuName() != null
											&& qpInfo.getProcessName() != null
											&& pdata.getGongxuName().equals(
													qpInfo.getProcessName())) {
										// 设置需要在报价bom中找的属性
										processt.setOpcaozuojiepai(qpInfo
												.getOpcaozuojiepai());
										processt.setOpshebeijiepai(qpInfo
												.getOpshebeijiepai());
										processt.setGzzhunbeijiepai(qpInfo
												.getGzzhunbeijiepai());
										processt.setGzzhunbeicishu(qpInfo
												.getGzzhunbeicishu());
										processt.setAllJiepai(qpInfo
												.getJiagongTime());
										processt.setProductStyle(qpInfo
												.getProductStyle());
										break;
									}
								}
							}
							// 将工序放入集合中
							processtSet.add(processt);
						}
						// 将集合放入零件中
						pt.setProcessTemplate(processtSet);
					}
					b = b & totalDao.save(pt);
					if (belongLayer == 1) {
						newrootId = pt.getId();
						pt.setRootId(newrootId);
						b = b & totalDao.update(pt);
					}
					newfatherId = pt.getId();
				}
				Set<QuotedPrice> qpList = qp.getQuotedPriceSet();
				if (qpList.size() > 0) {
					newbelongLayer++;
					for (QuotedPrice qp1 : qpList) {
						b = copyGongYiGuiChengToProcard(qp1, productStyle,
								newrootId, newfatherId, newbelongLayer);
					}
				}
			}

		}
		return b;
	}

	@Override
	public Map<Integer, Object> checkBuildById(Integer id) {
		// TODO Auto-generated method stub
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		boolean b = false;
		String msg = "您选中的零件不存在！";
		if (id != null) {
			GongyiGuicheng gg = (GongyiGuicheng) totalDao.getObjectById(
					GongyiGuicheng.class, id);
			if (gg != null) {
				if (gg.getProcardStyle() != null
						&& gg.getProcardStyle().equals("总成")) {
					if (gg.getStatus() != null && gg.getStatus().equals("已批准")) {
						List<Integer> idList = totalDao
								.query(
										"select id from ProcardTemplate where markId=?",
										gg.getJianNumb());
						if (idList.size() > 0) {
							msg = "您选中的零件已经入sop或者lp，不能引领组装BOM";
						} else {
							b = true;
							msg = "您选中的零件可以引领组装BOM";
							map.put(3, gg.getId());
						}
					} else {
						msg = "您选中的零件还未通过审批！不能引领组装BOM";
					}

				} else {
					msg = "您选中的零件不是总成零件！不能引领组装BOM";
				}
			}
		}
		map.put(1, b);
		map.put(2, msg);
		return map;
	}

	@Override
	public Map<Integer, Object> buildBomtoProcard(
			List<GongyiGuichengVo> ggVoList, String productStyle) {
		// TODO Auto-generated method stub
		String enproductStyle = null;
		if ("试制".equals(productStyle)) {
			enproductStyle = "sop";
		} else {
			enproductStyle = "lp";
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		boolean b = true;
		String msg = null;
		GongyiGuicheng totalgg = null;
		if (ggVoList != null && ggVoList.size() > 0) {
			for (GongyiGuichengVo ggVo : ggVoList) {
				if (ggVo.getRootId().equals(ggVo.getGid())) {
					totalgg = (GongyiGuicheng) totalDao.getObjectById(
							GongyiGuicheng.class, ggVo.getGid());
					break;
				}
			}
		}
		if (totalgg != null) {
			List<Integer> sameIdList = totalDao.query(
					"select id from ProcardTemplate where markId =?", totalgg
							.getJianNumb());
			if (sameIdList.size() > 0) {
				b = false;
				msg = "生成" + enproductStyle + "bom失败,已存在同样的bom树";
			} else {
				// 获得报价的总成零件
				b = buildBomtoProcard2(totalgg, productStyle, ggVoList, null,
						null, 1);
				if (b) {
					msg = "进入" + enproductStyle + "成功";
				}
			}
		}
		map.put(1, b);
		map.put(2, msg);
		return map;
	}

	public boolean buildBomtoProcard2(GongyiGuicheng gg, String productStyle,
			List<GongyiGuichengVo> list, Integer rootId, Integer fatherId,
			Integer belongLayer) {
		boolean b = true;
		if (gg != null && gg.getJianNumb() != null) {
			ProcardTemplate fpt = null;
			if (fatherId != null) {
				fpt = (ProcardTemplate) totalDao.getObjectById(
						ProcardTemplate.class, fatherId);
			}
			Integer newrootId = rootId;
			Integer newbelongLayer = belongLayer;
			Integer newfatherId = null;
			if (gg.getProcardStyle() != null
					&& gg.getProcardStyle().equals("外购")) {
				ProcardTemplate pt = new ProcardTemplate();
				// 设置零件属性
				pt.setMarkId(gg.getJianNumb());
				pt.setProName(gg.getJianName());
				pt.setProcardStyle(gg.getProcardStyle());
				// pt.setUnit(gg.getUnit());
				// pt.setCarStyle(gg.getCarStyle());
				// pt.setStatus(gg.getYucailiaostatus());
				// pt.setYuanUnit(gg.getUnit());
				// pt.setQuanzi1(gg.getQuanzi1());
				// pt.setQuanzi2(gg.getQuanzi2());
				// pt.setTrademark(gg.getTrademark());
				// pt.setSpecification(gg.getSpecification());
				// pt.setCorrCount(gg.getCorrCount());
				pt.setBelongLayer(belongLayer);
				pt.setRootId(newrootId);
				pt.setFatherId(fatherId);
				pt.setProductStyle(productStyle);
				pt.setProcardTemplate(fpt);
				b = b & totalDao.save(pt);
			} else {
				List<ProcessData> pdList = totalDao
						.query("from ProcessData where gongyiGuichengId="
								+ gg.getId());
				ProcardTemplate pt = new ProcardTemplate();
				// 设置零件属性
				pt.setMarkId(gg.getJianNumb());
				pt.setProName(gg.getJianName());
				pt.setProcardStyle(gg.getProcardStyle());
				// pt.setUnit(gg.getUnit());
				// pt.setCarStyle(gg.getCarStyle());
				// pt.setStatus(gg.getYucailiaostatus());
				// pt.setYuanUnit(gg.getUnit());
				// pt.setQuanzi1(gg.getQuanzi1());
				// pt.setQuanzi2(gg.getQuanzi2());
				// pt.setTrademark(gg.getTrademark());
				// pt.setSpecification(gg.getSpecification());
				// pt.setCorrCount(gg.getCorrCount());
				pt.setBelongLayer(belongLayer);
				pt.setRootId(newrootId);
				pt.setFatherId(fatherId);
				pt.setProductStyle(productStyle);
				pt.setProcardTemplate(fpt);
				b = b & totalDao.save(pt);
				if (pdList.size() > 0) {
					Set<ProcessTemplate> processtSet = new HashSet<ProcessTemplate>();
					for (ProcessData pdata : pdList) {
						ProcessTemplate processt = new ProcessTemplate();
						// 设置在工艺规程中找的到的属性
						processt.setProcessNO(pdata.getGongxuNo());
						processt.setProcessName(pdata.getGongxuName());
						if (pdata.getShebeiId() != null
								&& pdata.getShebeiNo() != null) {
							processt.setShebeiId(pdata.getShebeiId());
							processt.setShebeiNo(pdata.getShebeiNo());
							processt.setShebeiName(pdata.getShebeiName());
							processt.setShebeistatus("是");// 设备验证
							List<TaSopGongwei> shebList = totalDao.query(
									"from TaSopGongwei where shebeiCode=?",
									pdata.getShebeiNo());
							if (shebList.size() > 0) {
								TaSopGongwei tsg = shebList.get(0);
								processt.setOptechnologyRate(tsg
										.getCaozJineng());
								processt.setOpCouldReplaceRate(tsg
										.getCaoztihuanrenshu());
								processt.setGzfuheRate(tsg.getGongzhuangFuhe());
								processt.setGztechnologyRate(tsg
										.getGongzhuangJineng());
								processt.setGzCouldReplaceRate(tsg
										.getGongzhuangtihuanrenshu());
								processt.setGongwei(tsg.getGongweihao());
							}
						} else {
							processt.setShebeistatus("否");// 设备验证
						}
						if (pdata.getGongzhuangId() != null) {
							processt.setGzstoreId(pdata.getGongzhuangId());
							processt.setNumber(pdata.getGongzhuangNo());
							processt.setMatetag(pdata.getGongzhuangName());
							processt.setGongzhuangstatus("是");// 工装验证
						} else {
							processt.setGongzhuangstatus("否");// 工装验证

						}
						if (pdata.getLiangjuId() != null) {
							processt.setMeasuringId(pdata.getLiangjuId());
							processt.setMeasuringNumber(pdata.getLiangjuNo());
							processt
									.setMeasuringMatetag(pdata.getLiangjuName());
							processt.setMeasuring_no(pdata
									.getLiangjuNoForCompany());
							processt.setLiangjustatus("是");// 量具验证
						} else {
							processt.setLiangjustatus("否");// 量具验证
						}
						processt.setProductStyle("自制");// 默认为自制
						processt.setZjStatus("yes");// 默认首检
						processt.setProcessStatus("yes");// 默认并行
						processt.setIsPrice("yes");// 默认参与
						processt.setKaoqingstatus("是");// 默认考勤
						processt.setGuifanstatus("是");// 默认规范
						// 将工序放入集合中
						processtSet.add(processt);
					}
					// 将集合放入零件中
					pt.setProcessTemplate(processtSet);
				}
				b = b & totalDao.save(pt);
				if (belongLayer == 1) {
					newrootId = pt.getId();
					pt.setRootId(newrootId);
					b = b & totalDao.update(pt);
				}
				newfatherId = pt.getId();
				List<GongyiGuicheng> ggSonList = new ArrayList<GongyiGuicheng>();
				for (GongyiGuichengVo ggVo : list) {
					if (ggVo.getGid().equals(gg.getId())) {
						for (GongyiGuichengVo ggVo2 : list) {
							if (ggVo2.getFatherId() != null
									&& ggVo2.getFatherId().equals(ggVo.getId())) {
								GongyiGuicheng ggSon = (GongyiGuicheng) totalDao
										.getObjectById(GongyiGuicheng.class,
												ggVo2.getGid());
								if (ggSon != null) {
									ggSonList.add(ggSon);
								}
							}
						}
						break;
					}
				}

				if (ggSonList.size() > 0) {
					newbelongLayer++;
					for (GongyiGuicheng gg3 : ggSonList) {
						b = buildBomtoProcard2(gg3, productStyle, list,
								newrootId, newfatherId, newbelongLayer);
					}
				}
			}

		}
		return b;
	}

	@Override
	public Object[] findGygcToSopOrLp(GongyiGuicheng gongyiGuicheng,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (gongyiGuicheng == null) {
			gongyiGuicheng = new GongyiGuicheng();
		}
		gongyiGuicheng.setProcardStyle("总成");
		String hql = totalDao.criteriaQueries(gongyiGuicheng, null);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	@Override
	public List findBomForReview(Integer id) {
		// TODO Auto-generated method stub
		List<QuotedPrice> list= totalDao.query("from QuotedPrice where rootId=?", id);
		if(list!=null&&list.size()>0){
			for(QuotedPrice qp:list){
				String procardStyle=(String) totalDao.getObjectByCondition("select procardStyle from GongyiGuicheng where jianNumb=?", qp.getMarkId());
			    if(procardStyle!=null){
			    	qp.setProcardStyle(procardStyle);
			    }
			}
		}
		return list;
	}

}
