package com.task.Server.sys;

import java.util.List;

import com.task.entity.DeptNumber;
import com.task.entity.system.AuditNode;
import com.task.entity.system.CircuitCustomize;
import com.task.entity.system.Option;

/***
 * 定制流程
 * 
 * @author 刘培
 * 
 */
public interface CircuitCustomizeServer {

	/***
	 * 添加定制流程
	 * 
	 * @param circuitCustomize
	 * @return
	 */
	boolean addCircuitCustomize(CircuitCustomize circuitCustomize);

	/***
	 * 查询定制流程
	 * 
	 * @param circuitCustomize
	 * @return
	 */
	Object[] findCCByCondition(CircuitCustomize circuitCustomize, int pageNo,
			int pageSize);

	/***
	 * 通过流程id查询对应节点
	 * 
	 * @param ccId
	 * @return
	 */
	List findAuditNodeByCcId(Integer ccId,Integer deptId);

	/***
	 * 添加节点
	 * 
	 * @param auditNode
	 * @param ccId
	 * @return
	 */
	boolean saveAuditNode(AuditNode auditNode, Integer ccId,int deptId);

	/***
	 * 删除节点
	 * 
	 * @param id
	 * @return
	 */
	boolean delAuditNode(Integer id);

	/***
	 * 删除节点(根据流程id、用户id、审批登记查询后删除)
	 * 
	 * @param id
	 * @param ccId
	 * @param auditLevel
	 * @return
	 */
	boolean delAuditNode(Integer userId, Integer ccId, Integer auditLevel,int deptId);

	/***
	 * 通过id查询定制流程
	 * 
	 * @param id
	 * @return
	 */
	CircuitCustomize findCirCusById(Integer id);
	/**
	 * 通过Id查询审批选项
	 */
	List<Option> findOptionByccId(Integer id);

	/***
	 * 删除定制流程
	 * 
	 * @param circuitCustomize
	 * @return
	 */
	boolean delCirCus(CircuitCustomize circuitCustomize);

	/***
	 * 修改定制流程
	 * 
	 * @param circuitCustomize
	 * @return
	 */
	boolean updateCirCus(CircuitCustomize circuitCustomize);
	
	/**
	 * 获得所有部门;
	 */
	List<DeptNumber> findAlldept(int ccId);
	/**
	 * 流程绑定部门
	 */
	boolean bddept(int ceId,int deptId);
	/**
	 * 流程解除绑定部门
	 */
	boolean jcbddept(int ceId,int deptId);
	/**
	 * 根据流程Id查询已绑定的部门
	 */
	List<DeptNumber> findbddept(int ccId);

}
