package com.task.Server;

import java.util.List;

import com.task.entity.DeptNumber;
import com.task.entity.Users;
import com.task.entity.sop.ProcardTemplate;

/**
 * 部门编码 Server层
 * 
 * @author 刘培
 * 
 */
public interface DeptNumberServer {

	public String finAllDeptNumberForSetlect(Integer belongLayer);// 查询所有部门为页面Select使用

	@SuppressWarnings("unchecked")
	public List findAllDept(); // 查询所有部门信息

	public DeptNumber findDeptNumberById(Integer id); // 根据ID查询部门信息

	public String addDeptNumber(DeptNumber deptNumber,
			DeptNumber fatherDeptNumber); // 添加部门

	public boolean delDept(DeptNumber deptNumber);// 删除部门

	public String updateDept(DeptNumber deptNumber, DeptNumber oldDeptNumber); // 修改部门

	public List<DeptNumber> findLowerDeptNumber(Integer fatherId); // 查询下层是否存在部门

	public List<Users> findAllUsersByDept(Integer dept); // 查询部门下所有用户
	public List<DeptNumber> getDept();
		



	// 查询所有部门(for json)
	@SuppressWarnings("unchecked")
	public List findAllDeptForJson();
	/**
	 * 添加第一个
	 */
	boolean addfirst();
	
	/**
	 * 递归排序;
	 */
	public String paixu(DeptNumber dept);
	/**判断是否可以拖动
	 * @param id 拖动的节点Id
	 * @param id1 拖到某个节点 的节点Id
	 * @param moveType 拖动类型
	 * @return
	 */
	
	boolean istuozhuai(Integer id, Integer id1,String moveType);
	/**
	 * 拖拽后更新数据
	 * @param id  拖动的节点Id
	 * @param id1 拖到某个节点 的节点Id
	 * @param xuihao1  拖动节点的序号
	 * @param xuhao2 拖动到某节点的序号
	 * @param moveType  拖动类型
	 * @return
	 */
	String	tuozhuaiAfterUpdate(Integer id, Integer id1,Integer xuihao1,Integer xuhao2,String moveType);
	
	/**
	 * 根据部门名称查询人员信息；
	 */
	List<Users> findUsersBydept(String deptName);
	public List findAllDeptNogongyingshang();
	
}
