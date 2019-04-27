package com.task.ServerImpl;

import java.util.List;
import java.util.Map;


import com.task.Dao.TotalDao;
import com.task.Server.MentionrecordServer;
import com.task.Server.sys.CircuitRunServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Mentionrecord;
import com.task.entity.OaAppDetail;
import com.task.entity.OaMessageAlerm;
import com.task.entity.Template;
import com.task.entity.Users;
import com.task.entity.system.CircuitRun;
import com.task.util.Util;

public class MentionrecordServerImpl implements MentionrecordServer {

	private TotalDao totalDao;
	private CircuitRunServer circuitRunServer;
	

	public CircuitRunServer getCircuitRunServer() {
		return circuitRunServer;
	}

	public void setCircuitRunServer(CircuitRunServer circuitRunServer) {
		this.circuitRunServer = circuitRunServer;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 查询出提奖记录表所有信息
	public List findMentionrecordAll(int pageNo, int pageSize) {
		String hql = "from Mentionrecord order by  mentiondatetime  desc";
		return this.totalDao.findAllByPage(hql, pageNo, pageSize);
	}
	

	// 查询出提奖记录表总数
	public int countMentionrecord() {
		String hql = "from Mentionrecord";
		return this.totalDao.getCount(hql);
	}

	// 条件查询出提奖记录表信息
	public List conditiontMentionrecord(Mentionrecord mentionrecord,int pageNo, int pageSize) {
		if (mentionrecord != null) {
			String tjmone = String
					.valueOf(mentionrecord.getMentionshallMoney());
			String other = "";
			if (tjmone != null&&!"null".equals(tjmone)) {
				other = " mentionshallMoney like '%"
						+ tjmone.substring(0, tjmone.indexOf(".")) + "%'";
				String[] name = { "mentionshallMoney" };
				String hql = this.totalDao.criteriaQueries(mentionrecord,
						other, name);
				return totalDao.findAllByPage(hql, pageNo, pageSize);
			}
			else{
				String hql = this.totalDao.criteriaQueries(mentionrecord,null,null);
				return totalDao.findAllByPage(hql, pageNo, pageSize);
			}
		}
		return null;
	}

	// 查询出条件查询的总数
	public int countAll(Mentionrecord mentionrecord) {
		if(mentionrecord!=null){
			String hql = this.totalDao.criteriaQueries(mentionrecord, null, null);
			return this.totalDao.getCount(hql);
		}
		return 0;
	}

	//根据ID查询提奖记录相信
	public Mentionrecord findByID(int id) {
		return (Mentionrecord) this.totalDao.getObjectById(Mentionrecord.class, id);
	}
	//修改提奖记录表信息
	public boolean updateMentionrecord(Mentionrecord mentionrecord) {
		if(mentionrecord!=null){
			return this.totalDao.update(mentionrecord);
		}
		return false;
	}

	//查询所有提奖记录表的所有信息 用于显示提奖总额走势图
	public List findViewcurve() {
		String hql="from Mentionrecord";
		return this.totalDao.findAllByPage(hql, 1,12);
	}

	//查询出提奖明细 用于显示月份的型别比例 饼图
	public List mentionPrizelist(String date, String date2) {
		if(date!=null&&date2!=null){
			String hql="select tjstyle,sum(tjmoney) from  Tijiang where tjtimer between '"+date+"' and '"+date2+"'group by tjstyle";
			return this.totalDao.query(hql);
		}
		return null;
	}

	//查询出型别 
	public List typeList() {
		String hql="select distinct tjstyle from Tijiang ";
		return this.totalDao.query(hql);
	}
	//查询出型别走势图
	public List findType(String type) {
		if(type!=null){
			String hql="select tjtimer,sum(tjmoney) from Tijiang where tjstyle='"+type+"' group by tjtimer";
			return this.totalDao.findAllByPage(hql, 1, 12);
		}
		return null;
	}
	//根据月份查询出 提奖信息
	public List findDate(String date) {
		if(date!=null){
			String hql="from Mentionrecord where mentionMonth='"+date+"'";
			return this.totalDao.query(hql);
		}
		return null;
	}

	@Override
	public boolean updateExamTemplate(Integer[] detailSelect, String tag) {
		boolean bool = false;
		if (null != detailSelect && detailSelect.length > 0) {
			Users user = Util.getLoginUser();
			for (int i = 0; i < detailSelect.length; i++) {// 遍历处理状态
				Mentionrecord detail = (Mentionrecord) totalDao.getObjectById(
						Mentionrecord.class, detailSelect[i]);
				if ("ok".equals(tag)) {// 同意
					// 处理审批流程
					String audit = circuitRunServer.updateExeNodeByCirId(detail
							.getEpId(), true, "", false, null, false);
					// 更改明细状态
				} else {// 打回
					// 处理审批流程
					// 处理审批流程
					circuitRunServer.updateExeNodeByCirId(detail.getEpId(),
							false, "", false, null, false);
					detail.setMentionstatus("打回");
//					detail.setDetailNextSign(user.getName());
					totalDao.update(detail);
				}
				bool = true;
			}
		}
		return bool;
	}

	@Override
	public Object[] findExamList(int parseInt, int pageSize) {
		// 返回条件 明细ID
		/**
		 * false:未审批 true：已审批
		 * 
		 */
		Map<String, Object> map = CircuitRunServerImpl.findAuditExeNode(
				Mentionrecord.class, false);
		if (map != null) {
			String hql = "from Mentionrecord where id in (:entityId)";
			List list = totalDao.find(hql, map, parseInt, pageSize);
			Object[] exam = new Object[2];
			Long countLong = totalDao.count("select count(*) " + hql, map);
			int count = Integer.parseInt(countLong.toString());
			exam[0] = count;
			exam[1] = list;
			return exam;
		}
		return null;
	}


}
