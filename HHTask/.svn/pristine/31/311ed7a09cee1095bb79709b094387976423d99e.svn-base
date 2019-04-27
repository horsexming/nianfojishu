package com.task.ServerImpl.fin;

import java.util.ArrayList;
import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.fin.EscrowServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Users;
import com.task.entity.fin.Escrow;
import com.task.entity.fin.EscrowMonth;
import com.task.entity.payment.FundApply;
import com.task.entity.system.CircuitRun;
import com.task.entity.system.ExecutionNode;
import com.task.entity.vo.shizhivo.ProjectOrderVo;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class EscrowServerImpl implements EscrowServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	
	@Override
	public String findEscrowType() {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();
		List<String> list = totalDao.query("select distinct(payCom) from EscrowPayCom");
		for (String d : list) {
			if (d!=null) {
				buffer.append(d.toString()+"|");
			}
		}
		return buffer.toString();
	}
	
	@Override
	public Object[] findFundApplyList(Escrow escrow, int pageNo,
			int pageSize, String pagestatus) {
		// TODO Auto-generated method stub
		if(escrow == null){
			escrow = new Escrow();
		}
		String hql =totalDao.criteriaQueries(escrow,null, null);
		if("ddy".equals(pagestatus)){
			hql+=" and (printStatus is null or printStatus!='是')";
		}
		hql +=" order by id desc";
		List<Escrow> escrowList =	totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		return   new Object[]{escrowList,count};
	}

	@Override
	public List getPrintList(int[] ids) {
		// TODO Auto-generated method stub
		if(ids!=null&&ids.length>0){
			StringBuffer sb = new StringBuffer();
			for(int id:ids){
				if(sb.length()==0){
					sb.append(id);
				}else{
					sb.append(","+id);
				}
			}
//			return totalDao.query("from Escrow where (printStatus is null or printStatus!='是') and id in("+sb.toString()+")");
			return totalDao.query("from Escrow where  id in("+sb.toString()+")");
		}
		return null;
	}

	@Override
	public void print(int[] ids) {
		// TODO Auto-generated method stub
		if(ids!=null&&ids.length>0){
			StringBuffer sb = new StringBuffer();
			for(int id:ids){
				if(sb.length()==0){
					sb.append(id);
				}else{
					sb.append(","+id);
				}
			}
			List<Escrow> list=totalDao.query("from Escrow where  id in("+sb.toString()+")");
			if(list!=null&&list.size()>0){
				for(Escrow escrow:list){
					escrow.setPrintStatus("是");
					totalDao.update(escrow);
				}
			}
		}
	}

	@Override
	public List<String> getqmList(Integer epId) {
		// TODO Auto-generated method stub
		List<String> qianmingList=new ArrayList<String>();
		if(epId!=null){
			List<ExecutionNode> list=totalDao.query("from ExecutionNode where circuitRun.id=? order by auditLevel", epId);
		    if(list.size()>0){
		    	for(ExecutionNode en:list){
		    		List<String> qmlist=totalDao.query("select signature_address from Signature where default_address='默认' and code=(select code from Users where id=?)", en.getAuditUserId());
		    	     if(qmlist.size()>0){
		    	    	 qianmingList.add(qmlist.get(0));
		    	     }
		    	}
		    }
		}
		return qianmingList;
	}
	@Override
	public List<String> getzbList() {
		// TODO Auto-generated method stub
		List<String> zhiliaoList=new ArrayList<String>();
		List<String> qmlist=totalDao.query("select signature_address from Signature where default_address='默认' and code=(select code from Users where id=?)", Util.getLoginUser().getId());
		if(qmlist.size()>0){
			zhiliaoList.add(qmlist.get(0));
		}
		return zhiliaoList;
	}

	@Override
	public List<String> getPayComByMonth(String month) {
		// TODO Auto-generated method stub
		return totalDao.query("select distinct bwtCompany from Escrow where applyTime like '"+month+"%'");
	}

	@Override
	public String applyEscrowMonth(EscrowMonth escrowMonth) {
		// TODO Auto-generated method stub
		Users user = Util.getLoginUser();
		if(user==null){
			return "请先登录!";
		}
		String thisMonth = Util.getDateTime("yyyy-MM");
		if(escrowMonth.getMonth()==null||escrowMonth.getMonth().length()==0){
			return "请选择月份!";
		}
		if(escrowMonth.getMonth().equals(thisMonth)){
			return "不能选择当前月";
		}
		if(escrowMonth.getPayCom()==null||escrowMonth.getPayCom().length()==0){
			return "请选择委托公司!";
		}
		Float hadCount = (Float) totalDao.getObjectByCondition("select count(*) from EscrowMonth where month=? and payCom =? and epStatus!='打回'", escrowMonth.getMonth(),escrowMonth.getPayCom());
		if(hadCount>0){
			return "此月份此委托公司的月度汇总已存在请勿重复申请";
		}
		escrowMonth.setApplyUserId(user.getId());//申请人id
		escrowMonth.setApplyUserName(user.getName());
		escrowMonth.setApplyUserCode(user.getCode());
		escrowMonth.setApplyTime(Util.getDateTime());//申请时间
		List<Escrow> escrowsList = totalDao.query("from Escrow where applyTime like '"+escrowMonth.getMonth()+"%' and bwtCompany = ?", escrowMonth.getPayCom());
		if(escrowsList==null||escrowsList.size()==0){
			return "没有找到对应的委托付款明细!";
		}
		totalDao.save(escrowMonth);
		for(Escrow e:escrowsList){
			e.setMonthId(escrowMonth.getId());
			totalDao.update(e);
		}
		Integer epId = null;
		String processName = "委托付款月度汇总申请";
		try {
			epId = CircuitRunServerImpl.createProcess(processName,
					EscrowMonth.class, escrowMonth.getId(), "epStatus", "id",
					"EscrowAction_escrowMonthPrint.action?id="
							+ escrowMonth.getId(),user.getName()+"申请委托给："+escrowMonth.getPayCom()+"的"+escrowMonth.getMonth()+"月份的委托付款汇总,请您审批", true);
			if (epId != null && epId > 0) {
				escrowMonth.setEpId(epId);
				CircuitRun circuitRun = (CircuitRun) totalDao.get(
						CircuitRun.class, epId);
				if ("同意".equals(circuitRun.getAllStatus())
						&& "审批完成".equals(circuitRun.getAuditStatus())) {
					escrowMonth.setEpStatus("同意");
				} else {
					escrowMonth.setEpStatus("未审批");
				}
				return totalDao.update(escrowMonth) + "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public EscrowMonth getescrowMonthForPrint(Integer id) {
		// TODO Auto-generated method stub
		EscrowMonth em= (EscrowMonth) totalDao.getObjectById(EscrowMonth.class, id);
		if(em!=null){
			List<Escrow> escrowList = totalDao.query("from Escrow where monthId=?", id);
			em.setEscrowlist(escrowList);
		}
		return em;
	}

	@Override
	public Object[] findEscrowMonthList(EscrowMonth escrowMonth, int pageNo,
			int pageSize, String pagestatus) {
		// TODO Auto-generated method stub
		if(escrowMonth == null){
			escrowMonth = new EscrowMonth();
		}
		String hql =totalDao.criteriaQueries(escrowMonth,null, null);
		hql +=" order by id desc";
		List<Escrow> escrowList =	totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		return   new Object[]{escrowList,count};
	}
	
	
	
	
}
