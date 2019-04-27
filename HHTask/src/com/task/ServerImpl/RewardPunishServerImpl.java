package com.task.ServerImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.RewardPunishServer;
import com.task.entity.Careertrack;
import com.task.entity.DeptNumber;
import com.task.entity.RewardPunish;
import com.task.entity.RewardPunishCollect;
import com.task.entity.Users;
import com.task.entity.sop.Procard;
import com.task.util.Util;

public class RewardPunishServerImpl implements RewardPunishServer{
	private TotalDao totalDao;
	
	// 查询所有加工件号为页面Select使用
	@SuppressWarnings("unchecked")
	public String finAllMarkIdForSetlect() {
		String hql = "from Procard where status='领工序'";
		List list = totalDao.query(hql);
		if (list != null) {
			String message = "";
			for (int i = 0; i < list.size(); i++) {
				Procard procard = (Procard) list.get(i);
				message += procard.getMarkId() + "|";
			}
			return message;
		}
		return null;
	}
	
	//查询users为页面input
	public Users findUserByCode(String code) {
		if (code != null && code.length() > 0) {
			String hql = "from Users where code=?";
			return (Users) totalDao.getObjectByCondition(hql, code);
		}
		return null;
	}

	// 添加加班记录
	@SuppressWarnings("unchecked")
	public String addRewardPunish(RewardPunish rewardPunish) {
		if (totalDao.save(rewardPunish)) {
			return "true";
		} else {
			return "false";
		}
	}

	// 删除加班记录
	@SuppressWarnings("unchecked")
	public String deleteRewardPunish(RewardPunish rewardPunish) {
		if (totalDao.delete(rewardPunish)) {
			return "true";
		} else {
			return "false";
		}
	}
	
	//更新加班记录 
	@SuppressWarnings("unchecked")
	public String updateRewardPunish(RewardPunish rewardPunish) {
		if (totalDao.update(rewardPunish)) {
			return "true";
		} else {
			return "false";
		}
	}
	
	// 获得加班记录
	@SuppressWarnings("unchecked")
	public RewardPunish getRewardPunishById(Integer id) {
		if (id != null && id > 0) {
			return (RewardPunish) totalDao.getObjectById(RewardPunish.class, id);
		}
		return null;
	}

	//获得加班记录集合 
	@SuppressWarnings("unchecked")
	public Object[] findAllRewardPunish(Map map,int pageNo,int pageSize) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String hql = "from RewardPunish r where 1 = 1";
		if(map != null){
			if(map.get("id")!=null){
				hql+= " and r.id = " + map.get("id");
			}
			if(map.get("name")!=null){
				hql+= " and r.name like '%" + map.get("name") + "%'";
			}
			if(map.get("code")!=null){
				hql+= " and r.code = '" + map.get("code") + "'";
			}
			if(map.get("dept")!=null){
				hql+= " and r.dept like '%" + map.get("dept") + "%'";
			}
			//convert(varchar(100),a.rq,23)
			//if(map.get("startDate")!=null){
				//hql+= " and o.startDate >= " + map.get("startDate");
				//hql+= " and convert(varchar(100),r.date,23) >= '" +  formatter.format(map.get("date"))+"'";
			//}
			//if(map.get("endDate")!=null){
				//hql+= " and o.endDate <= " + map.get("endDate");
				//hql+= " and convert(varchar(100),r.date,23) <= '" + formatter.format(map.get("date"))+"'";
			//}
			if(map.get("date")!=null){
				hql+= " and convert(varchar(100),r.date,23) = '" + formatter.format(map.get("date"))+"'";
			}
			if(map.get("project")!=null){
				hql+= " and r.project like '%" + map.get("project") + "%'";
			}
			if(map.get("type")!=null){
				hql+= " and r.type like '%" + map.get("type") + "%'";
			}
			if(map.get("money")!=null){
				hql+= " and r.money = " + map.get("money");
			}
		}
		hql+=" order by r.date desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	
	//按月份统计奖扣记录
	public List collectRewardPunishByMonth(Map map) {
		List rewardPunishCollectList=new ArrayList();
		//表格头部
		RewardPunishCollect rewardPunishCollectHead=new RewardPunishCollect();
		String[] itemHead=new String[12];
		rewardPunishCollectHead.setList(itemHead);
		rewardPunishCollectList.add(rewardPunishCollectHead);
		
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		Date date=new Date();
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		int yearEnd=cal.get(Calendar.YEAR);
		int monthEnd=cal.get(Calendar.MONTH) + 1;
		cal.add(Calendar.MONTH, -11); 
		int yearStart = cal.get(Calendar.YEAR);    //获取年
		int monthStart = cal.get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
				String deptsStr=finAllDeptNumberForSetlect();
		String depts[]=deptsStr.split(",");
		for(int j=0;j<depts.length;j++){
			String dept=depts[j];
			if(!"".equals(dept)){
				RewardPunishCollect rewardPunishCollect=new RewardPunishCollect();
				rewardPunishCollect.setDept(dept);
				String[][] items=new String[12][2];
				int numTotal=0;
				double moneyTotal=0;
				int year=yearStart;
				for(int i=1;i<=12;i++){
					int month=(monthStart+i-1)%12;
					if(0==month){
						month=12;
						year++;
					}
					
					String yearStr=year+"";
					String monthStr=month<10?"0"+month:""+month;
					String dateStart=yearStr+"-"+monthStr+"-01";
					String dateEnd=yearStr+"-"+monthStr+"-32";
					if(j==0){
						itemHead[i-1]=yearStr+"-"+monthStr;
					}
					//Object[] item=new Object[2];
					String hqlMonth = "from RewardPunish r where 1 = 1 and convert(varchar(100),r.date,23) >='"+dateStart+"' and convert(varchar(100),r.date,23)<'"+dateEnd+"' and r.dept='"+dept+"'";
					int num=0;
					double money=0;
					List list=totalDao.find(hqlMonth);
					for(int m=0;m<list.size();m++){
						RewardPunish rewardPunish=(RewardPunish)list.get(m);
						if(rewardPunish!=null){
							money+=rewardPunish.getMoney();
						}
					}
					num=list.size();
					numTotal+=num;
					moneyTotal+=money;
					//item[0]=num;
					//item[1]=money;
					//items[i-1]=item;
					items[i-1][0]=num+"";
					items[i-1][1]=money+"";
				}
				rewardPunishCollect.setNum(numTotal);
				rewardPunishCollect.setMoney(moneyTotal);
				rewardPunishCollect.setList(items);
				rewardPunishCollectList.add(rewardPunishCollect);
			}
		}
		return rewardPunishCollectList;
	};
	
	// 查询所有部门为页面Select使用
	@SuppressWarnings("unchecked")
	public String finAllDeptNumberForSetlect() {
		String hql = "from DeptNumber where deptNumber is not null and deptNumber <> ''";
		List list = totalDao.query(hql);
		if (list != null) {
			String message = "";
			for (int i = 0; i < list.size(); i++) {
				DeptNumber deptNumber = (DeptNumber) list.get(i);
				message += deptNumber.getDept() + ",";
			}
			return message;
		}
		return null;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
}
