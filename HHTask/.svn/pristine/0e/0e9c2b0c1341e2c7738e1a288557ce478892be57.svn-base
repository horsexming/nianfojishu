package com.task.ServerImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.Server.ContractServer;
import com.task.entity.Contract;
import com.task.entity.Provision;
import com.task.entity.Users;
import com.task.entity.WageStandard;
import com.task.util.Util;

/***
 * 合同Server实现类
 * 
 * @author 刘培
 * 
 */
public class ContractServerImpl implements ContractServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 添加合同
	public boolean addContract(Contract contract, String[] proContent) {
		if (contract != null) {
			Users user = (Users) totalDao.getObjectById(Users.class, contract
					.getUserId());
			if (user != null ) {
				String hql_ws = "from WageStandard where code=?";
				int count = totalDao.getCount(hql_ws, user.getCode());
				if ( count == 0) {
					// 查询是否已经存在工资模版
					WageStandard ws = new WageStandard();
					ws.setUserName(user.getName());
					ws.setCode(user.getCode());
					ws.setCardId(user.getCardId());
					ws.setDept(user.getDept());
					ws.setChageStatus("新增调整");
					ws.setGangweigongzi(0F);
					ws.setBaomijintie(0F);
					ws.setJixiaokaohegongzi(0F);
					ws.setLocalOrField("");
					ws.setCityOrCountryside("");
					ws.setDianhuabutie(0F);
					ws.setJinenggongzi(0F);
					ws.setGonglinggongzi(0F);
					totalDao.save(ws);
				} else if ( count > 0) {

				} 
			} else
				return false;

			// 合同条款处理
			if (proContent != null) {
				Set<Provision> provisionSet = new HashSet<Provision>();
				for (int i = 0; i < proContent.length; i++) {
					Provision provision = new Provision();
					provision.setContent(proContent[i]);// 内容
					provision.setProvisionStatus("private");// 状态
					provision.setContract(contract);// 合同
					provisionSet.add(provision);
					totalDao.save(provision);// 为了顺序不会出现混乱，因为先添加条款
				}
			}
			contract.setAddDateTime(Util.getDateTime());// 添加时间
			contract.setUid(user.getUid());
			contract.setCardId(user.getCardId());
			contract.setPhoneNumber(user.getPassword().getPhoneNumber());
			Users loginUser = Util.getLoginUser();
			contract.setAddUserId(loginUser.getId());
			contract.setAddUserName(loginUser.getName());
			return totalDao.save(contract);
		}
		return false;
	}

	// 查询编号是否存在
	@SuppressWarnings("unchecked")
	public String findNumber(String contractNumber) {
		if (contractNumber != null && contractNumber.length() > 0) {
			String hql = "from Contract where contractNumber=?";
			List list = totalDao.query(hql, contractNumber);
			if (list != null && list.size() > 0) {
				return "编号:" + contractNumber + " 已经存在!请重新生成编号!";
			} else {
				return "true";
			}
		} else {
			return "请填写合同编号!";
		}
	}

	// 根据合同Id查询所对应的合同条款
	@SuppressWarnings("unchecked")
	public List findProvision(Contract contract) {
		if (contract != null) {
			String hql = "from Provision where id in (select p.id from Provision p join p.contract c where c.id=?) order by id";
			return totalDao.query(hql, contract.getId());
		}
		return null;
	}

	/***
	 * 根据userId查询合同
	 * 
	 * @param contractStatus
	 *            (contract 人事合同)/(wage 工资调整合同)
	 * 
	 */
	public Contract findContractById(Integer id, String contractStatus) {
		if (id != null && id > 0) {
			String hql = "from Contract where userId=? and contractStatus=? and isUse='默认'";
			return (Contract) totalDao.getObjectByCondition(hql, id,
					contractStatus);
		}
		return null;
	}

	// 修改合同
	public boolean updateContract(Contract contract) {
		if (contract != null) {
			return totalDao.update(contract);
		}
		return false;
	}

	// 查询员工所有薪资调整协议
	@SuppressWarnings("unchecked")
	public Object[] findUserWageAgre(Contract contract, int pageNo, int pageSize) {
		if (contract == null) {
			contract = new Contract();
		}
		String hql = "";
		if (contract.getUserId() != null) {
			hql = totalDao.criteriaQueries(contract, " userId="
					+ contract.getUserId(), new String[] { "userId" })
					+ " order by id desc";
		} else {
			hql = totalDao.criteriaQueries(contract, null, null)
					+ " order by id desc";
		}
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	// 根据合同id查询合同信息
	public Contract findContractById(Integer id) {
		if (id != null && id > 0) {
			return (Contract) totalDao.getObjectById(Contract.class, id);
		}
		return null;
	}

	// 自动生成合同编号
	@SuppressWarnings("unchecked")
	public String findContractNumber() {
		String datetime = totalDao.getDateTime("yyyyMMdd");
		String hql = "select contractNumber from Contract where contractStatus='contract' order by id desc";
		List list = totalDao.findAllByPage(hql, 0, 1);
		String contractNumber = "";
		if (list != null && list.size() > 0) {
			contractNumber = (String) list.get(0);
		}
		if (contractNumber != null && contractNumber.length() > 0) {
			//int num1 = Integer.parseInt(contractNumber.substring(contractNumber.length() - 3)) + 1;
			int num = Integer.parseInt(contractNumber.substring(8, contractNumber.length())) + 1;
			if (num >= 1000)
				contractNumber = datetime + num;
			else if((num >= 100))
				contractNumber = datetime + "0" + num;
			else if((num >= 10))
				contractNumber = datetime + "00" + num;
			else
				contractNumber = datetime + "000" + num;
		} else {
			contractNumber = datetime + "0001";
		}
		return contractNumber;
	}

	/***
	 * 通过工号查询其协议状态不为'完成'的协议信息
	 * 
	 * @param code
	 *            工号
	 * @return
	 */
	public Contract findContractByCode(String code) {
		String hql = "from Contract where code =? and contractStatus='wage' and isUse <> '完成'";
		return (Contract) totalDao.getObjectByCondition(hql, code);
	}

	/***
	 * 删除合同
	 * 
	 * @param contract
	 *            合同对象
	 * @return
	 */
	public boolean delContract(Contract contract) {
		if (contract != null) {
			return totalDao.delete(contract);
		}
		return false;
	}

	/***
	 * 根据工号和状态查询合同信息
	 * 
	 * @param code
	 *            工号
	 * @param isUse
	 *            状态((默认、历史)/(待调整、调整中、完成))
	 * @return
	 */
	public Contract findContractByCode(String code, String isUse) {
		if (isUse != null && isUse.length() > 0) {
			if ("1".equals(isUse)) {
				isUse = "待调整";
			} else if ("2".equals(isUse)) {
				isUse = "调整中";
			} else if ("3".equals(isUse)) {
				isUse = "完成";
			} else if ("4".equals(isUse)) {
				isUse = "默认";
			} else if ("5".equals(isUse)) {
				isUse = "历史";
			}
		}
		String hql = "from Contract where code=? and isUse=? order by id desc";
		return (Contract) totalDao.getObjectByCondition(hql, code, isUse);
	}
}
