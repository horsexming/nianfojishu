package com.task.ServerImpl.shizhi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.Server.shizhi.ProjectOrderPartServer;
import com.task.entity.shizhi.ProjectOrder;
import com.task.entity.shizhi.ProjectOrderPart;
import com.task.entity.shizhi.TryMake;
import com.task.entity.sop.ProcardTemplate;
import com.task.entity.sop.ProcessTemplate;
import com.task.util.RtxUtil;

public class ProjectOrderPartServerImpl implements ProjectOrderPartServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean add(ProjectOrderPart projectOrderPart) {
		// TODO Auto-generated method stub
		boolean b=true;
		if(projectOrderPart.getProjectOrder()!=null){
			ProjectOrder po=getProjectOrderById(projectOrderPart.getProjectOrder().getId());
			projectOrderPart.setProjectOrder(po);
			Set<ProjectOrderPart> popSet=po.getProjectOrderPart();
			popSet.add(projectOrderPart);
			po.setProjectOrderPart(popSet);
			b=b& totalDao.save(projectOrderPart);//添加项目需求零件
			TryMake tryMake =getTryMakeById(projectOrderPart.getTmId());
			if(tryMake!=null){
				if(projectOrderPart.getPartNum()==null){
					return false;
				}
				if(tryMake.getApprovalNum()==null){
					tryMake.setApprovalNum(projectOrderPart.getPartNum());
				}else{
					tryMake.setApprovalNum(projectOrderPart.getPartNum()+projectOrderPart.getPartNum());
				}
			}else{
				return false;
			}
			if(b){
				String user1=po.getTechnicalEngineer();
				String user2=po.getProductEngineer();
				List<String> usecode=totalDao.query("select code from Users where name in(?,?)", user1,user2);
			    if(usecode.size()>0){
			    	String markId=projectOrderPart.getMarkId();
			    	if(markId!=null){
			    		b=RtxUtil.sendNotify(usecode, "请去绑定件号为"+markId+"的零件和其下零件的工序的相关的技能复杂系数、加工难点系数和RPN评分", "项目试制", "1","0");
//			    		List<ProcardTemplate> procardtmpList=totalDao.query("from ProcardTemplate where markId=?", markId);
//			    	    if(procardtmpList.size()>0){
//			    	    	ProcardTemplate p=procardtmpList.get(0);
//			    	    	Set<ProcessTemplate> processSet=p.getProcessTemplate();
//			    	    	if(processSet!=null){
//			    	    		StringBuffer msg=new StringBuffer("请去绑定工序：(");
//			    	    		int n=0;
//			    	    		for(ProcessTemplate process:processSet){
//			    	    			if(n==0){
//			    	    				msg.append(process.getProcessName());
//			    	    			}else{
//			    	    				msg.append(","+process.getProcessName());
//			    	    			}
//			    	    			n++;
//			    	    		}
//			    	    		msg.append(")相关的技能复杂系数、加工难点系数和RPN评分");
//			    	    		b=RtxUtil.sendNotify(usecode, msg.toString(), "项目试制", "1","0");
//			    	    	}
//			    	    }
			    	}
			    }
			}
			return b;
			
		}else{
			return false;
		}
		
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		ProjectOrderPart poPart = getById(id);
		if (poPart != null) {
			poPart.setProjectOrder(null);
			return totalDao.delete(poPart);
		}
		return false;
	}

	@Override
	public ProjectOrderPart getById(Integer id) {
		// TODO Auto-generated method stub
		if (id != null) {
			Object o = totalDao.getObjectById(ProjectOrderPart.class, id);
			if (o != null) {
				return (ProjectOrderPart) o;
			}
		}
		return null;
	}

	@Override
	public boolean update(ProjectOrderPart projectOrderPart) {
		// TODO Auto-generated method stub
		if(projectOrderPart!=null&&projectOrderPart.getId()!=null){
			ProjectOrderPart p=getById(projectOrderPart.getId());
			if(p!=null){
				p.setPartNum(projectOrderPart.getPartNum());
				p.setRemark(projectOrderPart.getRemark());
				
		           return totalDao.update(p);
				
			}
		}
		return false;
	}

	@Override
	public Map<Integer, Object> findProjectOrderPartsByCondition(
			ProjectOrderPart projectOrderPart, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (projectOrderPart == null) {
			projectOrderPart = new ProjectOrderPart();
		}
		String hql = totalDao.criteriaQueries(projectOrderPart, null, null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public List<ProjectOrderPart> findAll() {
		// TODO Auto-generated method stub
		List<Object> all = totalDao.query("from ProjectOrderPart");
		if (all.size() > 0) {
			List<ProjectOrderPart> projectOrderParts = new ArrayList<ProjectOrderPart>();
			for (Object o : all) {
				projectOrderParts.add((ProjectOrderPart) o);
			}
			return projectOrderParts;
		}

		return null;
	}

	@Override
	public List<ProjectOrder> findProjectOrderAll() {
		// TODO Auto-generated method stub
		List<Object> all = totalDao.query("from ProjectOrder where status = '未申请' or status = '未审批' or status = '打回'");
		if (all.size() > 0) {
			List<ProjectOrder> projectOrders = new ArrayList<ProjectOrder>();
			for (Object o : all) {
				projectOrders.add((ProjectOrder) o);
			}
			return projectOrders;
		}
		return null;
	}

	@Override
	public ProjectOrder getProjectOrderById(Integer id) {
		// TODO Auto-generated method stub
		Object o=totalDao.getObjectById(ProjectOrder.class, id);
		if(o!=null){
			return (ProjectOrder)o;
		}
		return null;
	}

	@Override
	public TryMake getTryMakeById(Integer id) {
		// TODO Auto-generated method stub
		Object o=totalDao.getObjectById(TryMake.class, id);
		if(o!=null){
			return (TryMake)o;
		}
		return null;
	}

	@Override
	public List<TryMake> finTryMakeListByPorderId(Integer id) {
		// TODO Auto-generated method stub
		ProjectOrder p=getProjectOrderById(id);
		List<TryMake> tmList=new ArrayList<TryMake>();
		if(p!=null&&p.getProTryMakeScore()!=null){
			Set<TryMake> tmSet=p.getProTryMakeScore().getTryMake();
			if(tmSet.size()>0){
				for(TryMake tm:tmSet){
					tmList.add(tm);
				}
				
			}
		}
		return tmList;
	}

	@Override
	public boolean canBeChange(Integer id) {
		// TODO Auto-generated method stub
		ProjectOrderPart pop=getById(id);
		if(pop!=null){
			ProjectOrder po=pop.getProjectOrder();
			if(po!=null&&po.getStatus()!=null
					&&(po.getStatus().equals("审批中")
							||po.getStatus().equals("同意"))){
				return false;
			}
				return true;
		}
		return false;
	}

	
}
