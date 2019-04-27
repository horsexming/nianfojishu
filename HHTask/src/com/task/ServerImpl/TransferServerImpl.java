package com.task.ServerImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.TransferServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Careertrack;
import com.task.entity.Password;
import com.task.entity.Transfer;
import com.task.entity.Users;
import com.task.entity.system.CircuitRun;
import com.task.util.Util;

public class TransferServerImpl implements TransferServer{
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	
	public List<Transfer> FindAllTransfer(int pageNo, int pageSize) {
			String hql="from Transfer order by id desc";
		
		return (List<Transfer>) totalDao.findAllByPage(hql, pageNo, pageSize);
	}

	@Override
	public String add(Transfer transfer) {
		if(transfer!=null){
		String beforedeptNo =  transfer.getBefroedeptNo();
		String deptNo = transfer.getDeptNo();
		String beforedept =  transfer.getBeforeDept();
		String dept = transfer.getDept();
		if(beforedeptNo!=null && !"".equals(beforedeptNo)
				&& deptNo!=null &&!"".equals(deptNo)
				&& beforedeptNo.equals(deptNo)){
			return "所调部门与原来部门一至无需申请";
		}else if(beforedeptNo==null && "".equals(beforedeptNo)
				&& deptNo==null && "".equals(deptNo)
				&&  beforedept.equals(dept)){
			return "所调部门与原来部门一至无需申请";
		}
		Users user=	Util.getLoginUser();
			transfer.setSqName(user.getName());
			transfer.setSqdept(user.getDept());
			transfer.setSqTime(Util.getDateTime());
			 totalDao.save(transfer);
			 String processName = "人员调动申请";
				Integer epId = null;
				try {
					epId = CircuitRunServerImpl.createProcess(processName,
							Transfer.class, transfer.getId(), "ep_statuts", "id",
							"TransferAction_showtransferbyId.action?id=" + transfer.getId()
									,transfer.getSqdept() + "部门 "
									+ transfer.getSqName() + "人员调动申请，请您审批", true);
					if (epId != null && epId > 0) {
						transfer.setEpId(epId);
						CircuitRun circuitRun = (CircuitRun) totalDao.get(
								CircuitRun.class, epId);
						if ("同意".equals(circuitRun.getAllStatus())
								&& "审批完成".equals(circuitRun.getAuditStatus())) {
							transfer.setEp_statuts("同意");
//							Users users=	(Users) totalDao.get(Users.class, transfer.getUserId());
//							users.setDept(transfer.getDept());
//							Password pass = users.getPassword();
//							pass.setDeptNumber(transfer.getDeptNo());
//							users.setPassword(pass);
//							users.setDeptId(transfer.getDeptId());
//							//更新users表部门
//							totalDao.update(users);
//							String time = Util.getDateTime("yyyy-MM-dd HH:mm:ss");
//							transfer.setDgTime(time);
//							//设置Careertrack表调动时间;
//							Careertrack ck =	(Careertrack) totalDao.getObjectByCondition("from Careertrack where userId =?",transfer.getUserId() );
//							ck.setDept(transfer.getDept());
//							ck.setDiaodongTime(time);
//							totalDao.update(ck);
						} else {
							transfer.setEp_statuts("未审批");
						}
					return	totalDao.update(transfer)+"";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return "添加失败";
	}

	@Override
	public boolean del(Transfer transfer) {
		if(transfer!=null){
			return totalDao.delete(transfer);
		}
		return false;
	}

	@Override
	public Map<Integer, Object> findTransferByCondition(Transfer transfer,
			int pageNo, int pageSize) {
		Map<Integer,Object> map=new HashMap<Integer, Object>();
		String hql=	totalDao.criteriaQueries(transfer, null);
		int count=totalDao.getCount(hql);
		List<Transfer> ckList=(List<Transfer>)totalDao.findAllByPage(hql, pageNo, pageSize);
		map.put(1, ckList);
		map.put(2, count);
		return map;
	}

	@Override
	public List<Transfer> findTransferbyuserId(Integer userId) {
		String hql = "from Transfer where userId=? order by dgTime desc";
		return totalDao.query(hql, userId);
	}

	@Override
	public int getcont() {
		String hql="from Transfer";
		 return totalDao.getCount(hql);
	}

	@Override
	public boolean update(Transfer transfer) {
		if(transfer!=null && transfer.getId()!=null && transfer.getId()>0){
			Transfer oldtransfer = (Transfer) totalDao.get(Transfer.class, transfer.getId());
			if ("打回".equals(oldtransfer.getEp_statuts())) {
				if (CircuitRunServerImpl.updateCircuitRun(oldtransfer.getEpId())) {
					oldtransfer.setEp_statuts("未审批");
				}
			}
			
			BeanUtils.copyProperties(transfer, oldtransfer,new String[]{"id","epId"});
			return totalDao.update(oldtransfer);
		}
		return false;
	}

	@Override
	public Transfer findTransferbyId(Integer id) {
		if(id!=null && id>0){
			return (Transfer) totalDao.get(Transfer.class, id);
		}
		return null;
	}

}
