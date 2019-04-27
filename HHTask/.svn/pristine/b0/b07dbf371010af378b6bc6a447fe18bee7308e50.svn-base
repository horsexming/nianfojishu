package com.task.Server;

import java.util.List;

import com.task.entity.Contract;

/**
 * 合同Server层
 * 
 * @author 刘培
 * 
 */
public interface ContractServer {

	public boolean addContract(Contract contract, String[] proContent);// 添加合同

	public String findNumber(String contractNumber); // 查询编号是否存在

	@SuppressWarnings("unchecked")
	public List findProvision(Contract contract); // 查询员工所对应的合同

	/***
	 * 根据userId查询合同
	 * 
	 * @param contractStatus
	 *            (contract 人事合同)/(wage 工资调整合同)
	 * 
	 */
	public Contract findContractById(Integer id, String contractStatus);

	public boolean updateContract(Contract contract); // 修改合同

	public Object[] findUserWageAgre(Contract contract, int pageNo, int pageSize);// 查询员工所有薪资调整协议

	public Contract findContractById(Integer id); // 根据合同id查询合同信息

	/**
	 * 自动生成合同编号 eg: 20120907028
	 * 
	 * @return String
	 */
	public String findContractNumber();

	/***
	 * 通过工号查询其协议状态不为'完成'的协议信息
	 * 
	 * @param code
	 *            工号
	 * @return
	 */
	public Contract findContractByCode(String code);

	/***
	 * 删除合同
	 * 
	 * @param contract
	 *            合同对象
	 * @return
	 */
	public boolean delContract(Contract contract);

	/***
	 * 根据工号和状态查询合同信息
	 * 
	 * @param code
	 *            工号
	 * @param isUse
	 *            状态((默认、历史)/(待调整、调整中、完成))
	 * @return
	 */
	public Contract findContractByCode(String code, String isUse);
}
