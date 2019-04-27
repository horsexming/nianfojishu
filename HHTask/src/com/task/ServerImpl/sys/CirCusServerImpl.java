package com.task.ServerImpl.sys;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.Server.sys.CircuitCustomizeServer;
import com.task.entity.DeptNumber;
import com.task.entity.Users;
import com.task.entity.system.AuditNode;
import com.task.entity.system.CircuitCustomize;
import com.task.entity.system.Option;
import com.task.util.Util;

/***
 * 定制流程
 * 
 * @author 刘培
 * 
 */
public class CirCusServerImpl implements CircuitCustomizeServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/***
	 * 添加定制流程
	 * 
	 * @param circuitCustomize
	 * @return
	 */
	@Override
	public boolean addCircuitCustomize(CircuitCustomize circuitCustomize) {
		if (circuitCustomize != null) {
			Users loginUser = Util.getLoginUser();
			circuitCustomize.setAddUserName(loginUser.getName());// 添加人
			circuitCustomize.setAddDateTime(Util.getDateTime());// 添加时间
			if(circuitCustomize.getListoption()!=null){
				Set<Option> setoption = new HashSet<Option>();
				for (Option option : circuitCustomize.getListoption()) {
					option.setCircuitCustomize(circuitCustomize);
					setoption.add(option);
				}
				circuitCustomize.setSetoption(setoption);
			}
			return totalDao.save(circuitCustomize);
		}
		return false;
	}

	/***
	 * 通过id查询定制流程
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public CircuitCustomize findCirCusById(Integer id) {
		if (id != null && id > 0) {
			return (CircuitCustomize) totalDao.getObjectById(
					CircuitCustomize.class, id);
		}
		return null;
	}

	/***
	 * 删除定制流程
	 * 
	 * @param circuitCustomize
	 * @return
	 */
	@Override
	public boolean delCirCus(CircuitCustomize circuitCustomize) {
		if (circuitCustomize != null) {
			circuitCustomize.setDeptset(null);
			return totalDao.delete(circuitCustomize);
		}
		return false;
	}

	/***
	 * 修改定制流程
	 * 
	 * @param circuitCustomize
	 * @return
	 */
	@Override
	public boolean updateCirCus(CircuitCustomize circuitCustomize) {
		if (circuitCustomize != null) {
			List<Option> listoption = circuitCustomize.getListoption();
			Set<Option> setoption = new HashSet<Option>();
			if(listoption!=null && listoption.size()>0){
				for (Option option : listoption) {
					option.setCircuitCustomize(circuitCustomize);
					setoption.add(option);
				}
			}
			circuitCustomize.setSetoption(setoption);
			return totalDao.update(circuitCustomize);
		}
		return false;
	}

	/***
	 * 查询定制流程
	 * 
	 * @param circuitCustomize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object[] findCCByCondition(CircuitCustomize circuitCustomize,
			int pageNo, int pageSize) {
		if (circuitCustomize == null) {
			circuitCustomize = new CircuitCustomize();
		}
		String hql = totalDao.criteriaQueries(circuitCustomize, null)+" order by addDateTime desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/***
	 * 通过流程id查询对应节点
	 * 
	 * @param ccId
	 * @return
	 */
	@Override
	public List findAuditNodeByCcId(Integer ccId,Integer deptId) {
		if (ccId != null) {
			CircuitCustomize cc = (CircuitCustomize) totalDao.get(CircuitCustomize.class,ccId);
			String hql = "";
			if(cc.getDeptset() == null || cc.getDeptset().size() == 0){
				hql = "from AuditNode where circuitCustomize.id=? order by auditLevel";
			}
			
			
			if(deptId!=null && deptId >0){
				hql = " from AuditNode where id in (select a.id from AuditNode a join a.deptSet b where b.id= "+deptId+") and circuitCustomize.id=? order by auditLevel  ";
			}
			if(hql.length()>0){
				return totalDao.query(hql, ccId);
			}
		}
		return null;
	}

	/***
	 * 添加节点
	 * 
	 * @param auditNode
	 * @param ccId
	 * @return
	 */
	@Override
	public boolean saveAuditNode(AuditNode auditNode, Integer ccId,int deptId) {
		if (auditNode != null) {
			// 检查人员是否已经在同级别的审核中存在
			String hql = "from AuditNode where auditLevel=? and auditUserId=? and circuitCustomize.id=?";
			DeptNumber dept =	null;
			if(deptId>0){
				dept =	(DeptNumber) totalDao.get(DeptNumber.class, deptId);
				hql = " from AuditNode where auditLevel=? and auditUserId=? and circuitCustomize.id=? and id in ( select a.id from AuditNode a join a.deptSet b where b.id = "+deptId+") ";
			}
			
			AuditNode oldAuditNode = (AuditNode) totalDao.getObjectByCondition(
					hql, auditNode.getAuditLevel(), auditNode.getAuditUserId(),
					ccId);
			if (oldAuditNode == null) {
				CircuitCustomize cc = (CircuitCustomize) totalDao
						.getObjectById(CircuitCustomize.class, ccId);
				if (cc != null) {
					auditNode.setCircuitCustomize(cc);
					if(dept!=null && totalDao.save(auditNode)){
						Set<AuditNode> auditNodeSet =	dept.getAuditNodeSet();
						auditNodeSet.add(auditNode);
						return totalDao.update(dept);
					}else{
						return totalDao.save(auditNode);
					}
					
					
				}
			}
		}
		return false;
	}

	/***
	 * 删除节点
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public boolean delAuditNode(Integer id) {
		if (id != null && id > 0) {
			AuditNode auditNode = (AuditNode) totalDao.getObjectById(
					AuditNode.class, id);
			return totalDao.delete(auditNode);
		}
		return false;
	}

	/***
	 * 删除节点(根据流程id、用户id、审批登记查询后删除)
	 * 
	 * @param id
	 * @param ccId
	 * @param auditLevel
	 * @return
	 */
	@Override
	public boolean delAuditNode(Integer userId, Integer ccId, Integer auditLevel,int deptId) {
		if (userId != null && userId > 0 && ccId != null && ccId > 0) {
			AuditNode auditNode = (AuditNode) totalDao.getObjectByCondition(" from AuditNode where auditLevel=? and auditUserId=? and circuitCustomize.id=? ",  auditLevel, userId, ccId);
			if(deptId>0){
				String hql = "from AuditNode   where auditLevel=? and auditUserId=? and circuitCustomize.id=? and id in (select a.id from AuditNode a join a.deptSet d where d.id=?) ";
				 auditNode = (AuditNode) totalDao.getObjectByCondition(
						hql, auditLevel, userId, ccId,deptId);
				DeptNumber dept =	(DeptNumber) totalDao.get(DeptNumber.class, deptId);
				Set<AuditNode>  auditNodeSet =	dept.getAuditNodeSet();
				auditNodeSet.remove(auditNode);
				totalDao.update(dept);
				auditNode.setDeptSet(null);
			}
			return totalDao.delete(auditNode);
		}
		return false;
	}

	@Override
	public List<DeptNumber> findAlldept(int ccId) {
		String hql = "from DeptNumber where id not in ( select d.id from DeptNumber d  join d.CircuitCustomizeSet c where c.id="+ccId+") and deptNumber <> '' and deptNumber is not null ";
		return totalDao.find(hql);
	}

	@Override
	public boolean bddept(int ceId, int deptId) {
		if(ceId>0 && deptId >0){
			CircuitCustomize ce =	(CircuitCustomize) totalDao.get(CircuitCustomize.class, ceId);
			DeptNumber dept = (DeptNumber) totalDao.get(DeptNumber.class, deptId);
			Set<DeptNumber> deptset = ce.getDeptset();
			if(deptset.contains(dept)){
				return false;
			}
			deptset.add(dept);
			ce.setDeptset(deptset);
			return  	totalDao.update(ce);
		}
		return false;
	}

	@Override
	public boolean jcbddept(int ceId, int deptId) {
		if(ceId>0 && deptId >0){
			CircuitCustomize ce =	(CircuitCustomize) totalDao.get(CircuitCustomize.class, ceId);
			DeptNumber dept = (DeptNumber) totalDao.get(DeptNumber.class, deptId);
			String hql = "from AuditNode where circuitCustomize.id =? and id in(select A.id from AuditNode A join A.deptSet D  where D.id =?) ";
			List<AuditNode> auditnode =	 totalDao.query(hql, ceId ,deptId);
			Set<AuditNode> auditNodeSet = ce.getAuditNodeSet();
			if(auditnode!=null){
				auditNodeSet.removeAll(auditnode);
			}
			Set<DeptNumber> deptset = ce.getDeptset();
			deptset.remove(dept);
			ce.setDeptset(deptset);
			ce.setAuditNodeSet(auditNodeSet);
			return  	totalDao.update(ce);
		}
		return false;
	}

	@Override
	public List<DeptNumber> findbddept(int ccId) {//select m.id from Machine m  join m.userset u where u.id=" + userId + ")
		
		String hql = "select d.id,d.dept from DeptNumber d  join d.CircuitCustomizeSet c where c.id="+ccId;
		return totalDao.find(hql);
	}

	@Override
	public List<Option> findOptionByccId(Integer id) {
		if(id!=null && id>0){
			return totalDao.query(" from Option where circuitCustomize.id = ?", id);
		}
		return null;
	}

}
