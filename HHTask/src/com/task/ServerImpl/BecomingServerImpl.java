package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.aspectj.weaver.ast.HasAnnotation;
import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;

import com.task.Server.BecomingServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Becoming;
import com.task.entity.Careertrack;
import com.task.entity.Promotion;
import com.task.entity.Template;
import com.task.entity.TemplateDetails;
import com.task.entity.Users;
import com.task.entity.system.CircuitRun;
import com.task.util.Util;

public class BecomingServerImpl implements BecomingServer{
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	
	@Override
	public String add(Becoming becoming) {
		if(becoming!=null){
			Users user = Util.getLoginUser();
			becoming.setSqdate(Util.getDateTime());
			becoming.setSqdept(user.getDept());
			becoming.setSqname(user.getName());
			totalDao.save(becoming);
			 String processName = "员工转正申请";
				Integer epId = null;
				try {
					epId = CircuitRunServerImpl.createProcess(processName,
							Becoming.class, becoming.getId(), "ep_status", "id",
							"BecomingAction_getBecomingByid.action?id=" + becoming.getId()+"&status=mingxi"
									,becoming.getDept() + "部门 "
									+ becoming.getName() + "员工转正申请，请您审批", true);
					if (epId != null && epId > 0) {
						becoming.setEp_Id(epId);
						CircuitRun circuitRun = (CircuitRun) totalDao.get(
								CircuitRun.class, epId);
						if ("同意".equals(circuitRun.getAllStatus())
								&& "审批完成".equals(circuitRun.getAuditStatus())) {
							becoming.setEp_status("同意");
							//更新users状态;
//							Users users =  (Users) totalDao.get(Users.class, becoming.getUserId());
//							users.setOnWork(becoming.getStatus());
//							String time = Util.getDateTime("yyyy-MM-dd HH:mm:ss");
//							users.setZhuanzhengtime(time);
//							totalDao.update(users);
//							becoming.setZzdate(time);
//							//设置Careertrack表转正时间;
//							Careertrack ck =	(Careertrack) totalDao.getObjectByCondition("from Careertrack where userId =?",becoming.getUserId() );
//							ck.setZhuanzhengTime(Util.getDateTime());
//							totalDao.update(ck);
						} else {
							becoming.setEp_status("未审批");
						}
						return totalDao.update(becoming)+"";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return "error";
	}

	@Override
	public boolean del(Becoming becoming) {
		if(becoming!=null){
		return	totalDao.delete(becoming);
		}
		return false;
	}

	@Override
	public Map<Integer, Object> findBecomingByCondition(Becoming bg,
			int pageNo, int pageSize,String status) {
		if(bg == null){
			bg = new Becoming();
		}
		Map<Integer,Object> map=new HashMap<Integer, Object>();
		String hql=	totalDao.criteriaQueries(bg, null);
		if("person".equals(status)){
			Users user = Util.getLoginUser();
			hql += " and  sqname='"+user.getName()+"'";
		}
		int count=totalDao.getCount(hql);
		List<Becoming> ckList=(List<Becoming>)totalDao.findAllByPage(hql+" order by id desc", pageNo, pageSize);
		map.put(1, ckList);
		map.put(2, count);
		return map;
	}

	@Override
	public Becoming findBecomingbyId(Integer id) {
		if(id!=null && id>0){
			return	(Becoming) totalDao.get(Becoming.class, id);
		}
		return null;
	}

	@Override
	public int getcont() {
		String hql = "from Becoming";
		return totalDao.getCount(hql);
	}

	@Override
	public List<Becoming> showAllBecomingList(int pageNo, int pageSize) {
		String hql = "from Becoming order by id desc";
		return totalDao.findAllByPage(hql, pageNo, pageSize);
	}

	@Override
	public boolean update(Becoming becoming) {
		if(becoming!=null){
			Becoming oldbecoming =	(Becoming) totalDao.get(Becoming.class, becoming.getId());
			if ("打回".equals(oldbecoming.getEp_status())) {
				if (CircuitRunServerImpl.updateCircuitRun(oldbecoming.getEp_Id())) {
					oldbecoming.setEp_status("未审批");
				}
			}
			BeanUtils.copyProperties(becoming, oldbecoming,new String[]{"id","ep_Id"});
			return totalDao.update(oldbecoming);
			
		}
		return false;
	}
	
	
	@Override
	public  List<TemplateDetails> gettdlist() {
		String hql_root = "from TemplateDetails where template in( select id from Template where name = '员工转正考核')  and  onLayer = 'root'";
//		String hql_all =  "from TemplateDetails where template in( select id from Template where name = '员工转正考核') ";
//		 List<TemplateDetails> rootList = totalDao.find(hql_root);
//		 Map<String, Map<String, List<TemplateDetails>>> map = new HashMap<String, Map<String,List<TemplateDetails>>>();
//		 for (TemplateDetails t : rootList) {
//			String hql2 = " from TemplateDetails where onLayer = '"+t.getId()+"'";
//			 List<TemplateDetails> list2 = totalDao.find(hql2);
//			 Map<String, List<TemplateDetails>> map2 =  new HashMap<String, List<TemplateDetails>>();
//			 for (TemplateDetails t2 : list2) {
//				String hql3 =  "from TemplateDetails where onLayer= '"+t2.getId()+"'";
//				 List<TemplateDetails> list3 =	totalDao.find(hql3);
//				map2.put(t2.getProject(),list3);
//			}
//			 map.put(t.getProject(), map2);
//		}
		 
		  
		
		return totalDao.find(hql_root);
	}

	@Override
	public List<String> gettdlistbyId(Integer id) {
		String hql = " from TemplateDetails where onLayer = '"+id+"'";
		List<TemplateDetails> tslist =totalDao.find(hql);
		List<String> strlist = new ArrayList<String>();
		for (TemplateDetails ts : tslist) {
			strlist.add(ts.getId()+"&"+ts.getProject()+"&"+ts.getCustomScore());
		}
		return strlist;
	}

	@Override
	public Users getUsersById(Integer id) {
		return (Users) totalDao.get(Users.class, id);
	}

	@Override
	public Template gettemplateBy() {
		String hql = "from Template where name = ?";
		
		return (Template) totalDao.getObjectByCondition(hql, "员工转正考核");
	}

}
