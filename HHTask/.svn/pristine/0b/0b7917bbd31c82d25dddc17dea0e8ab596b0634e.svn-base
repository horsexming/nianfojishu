package com.task.Server.renshi;

import java.util.List;
import java.util.Map;

import com.task.entity.Contract;
import com.task.entity.Provision;
import com.task.entity.Users;
import com.task.entity.WageStandard;
import com.task.entity.renshi.DimissionLog;
import com.task.entity.renshi.Dimission_Handover;

/**
 * 离职申请单
 * @author 李聪
 *
 */
public interface DimissionLogServer {
	
	/**
	 * 添加离职申请单对象
	 * @param DimissionLog
	 * @return
	 */
	public String addDimissionLog(DimissionLog dimissionLog);
	
	/**
	 * 通过id删除离职申请单对象
	 * @param DimissionLog
	 * @return
	 */
	public boolean deleteDimissionLog(Integer id);
	
	/**
	 * 修改离职申请单对象
	 * @param DimissionLog
	 * @return
	 */
	public boolean updateDimissionLog(DimissionLog dimissionLog, String string);

	/**
	 * 主管添加止薪日期（）
	 * @param DimissionLog
	 * @return
	 */
	public boolean updateZhugaunDimissionLog(DimissionLog dimissionLog);
	/**
	 * 通过id查询离职申请单对象
	 * @param DimissionLog
	 * @return
	 */
	public DimissionLog getDimissionLogById(Integer id);
	/**
	 * 通过UserId查询合同对象
	 * @param 
	 * @return
	 */
	public Contract getContractByusId(Integer id);
	/**
	 * 查询所有离职申请单对象
	 * @param DimissionLog
	 * @return
	 */
	public List<DimissionLog> findAll();
	
	/**
	 * 按条件分页查询离职申请单
	 * @param dimissionLog 对象
	 * @param pageNo 起始页
	 * @param pageSize 每页数量
	 * @return
	 */
	public Map<Integer, Object> findDimissionLogsByCondition(DimissionLog dimissionLog, int pageNo, int pageSize);
	//查看个人消息
	public Map<Integer, Object> findDimissionLogsBycodeCondition(DimissionLog dimissionLog, int pageNo, int pageSize);
	//主管确认信息
	public Map<Integer, Object> findDimissionLogsByZhuguanCondition(DimissionLog dimissionLog, int pageNo, int pageSize);

	//查询待填写离职争议单对象
	Map<Integer, Object> findDimissionLogs_daiByCondition(
			DimissionLog dimissionLog, int pageNo, int pageSize,String tag);
	
	//根据申请单ID查询员工工资模板对象
	public WageStandard getWageStandardByid(Integer id);//
	
	//根据申请单ID查询交接单对象
	public Dimission_Handover getDimission_HandoverByid(Integer id);//(1离职争议单》获取交接单号)（2删除申请单的同时，删除交接单）
	//获取到所有离职协议条款内容（根据内容）
	public List<Provision> findProvision(String string);
	//根据usersId来查询当前员工信息
	public DimissionLog findDimissionLogByCodeId(Integer integer);
	//根据Id返回用户信息
	public Users findUsersId(Integer integer);
}
