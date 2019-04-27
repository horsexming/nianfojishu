package com.task.ServerImpl;

import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.ContractBonusServer;
import com.task.Server.sys.CircuitRunServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Bonusmoney;
import com.task.entity.ContractBonus;
import com.task.entity.ContractBonusReceive;
import com.task.entity.OaAppDetail;
import com.task.entity.OaMessageAlerm;
import com.task.entity.Users;
import com.task.entity.singlecar.SingleCar;
import com.task.entity.system.CircuitRun;
import com.task.util.Util;

/***
 * 承包奖金总额Server层实现类
 * 
 * @author 刘培
 * 
 */
public class ContractBonusServerImpl implements ContractBonusServer {

	private TotalDao totalDao;// DAO层
	private CircuitRunServer circuitRunServer;

	public CircuitRunServer getCircuitRunServer() {
		return circuitRunServer;
	}

	public void setCircuitRunServer(CircuitRunServer circuitRunServer) {
		this.circuitRunServer = circuitRunServer;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/***
	 * 添加承包奖金总额信息
	 * 
	 * @param ContractBonus
	 *            承包奖金总额对象
	 * @return true/false
	 */
	public boolean addContractBonus(ContractBonus contractBonus, int userId) {
		if (contractBonus != null && (Object) userId != null && userId > 0) {
			Users user = (Users) totalDao.getObjectById(Users.class, userId);// 通过id查询用户信息
			if (user != null) {
				contractBonus.setUserId(user.getId());// 用户id
				contractBonus.setCode(user.getCode());// 工号
				contractBonus.setCardId(user.getCardId());// 卡号
				contractBonus.setDeptName(user.getDept());// 部门
				contractBonus.setUserName(user.getName());// 用户名称
				contractBonus.setAddDate(Util
						.getDateTime("yyyy-MM-dd hh:ss:mm"));// 添加时间
				boolean bool = totalDao.save(contractBonus);
				if (bool) {
					try {
						String processName = "提奖总额审核";
						Integer epId = CircuitRunServerImpl.createProcess(processName,
								ContractBonus.class,contractBonus.getId(), "bonusStatus",
								"id",contractBonus
								.getUserName()
								+ " 的 "
								+ contractBonus.getBonusMouth()
								+ " 承包奖金总额: "
								+ contractBonus.getTotalMoney()
								+ "元 ,请您审核!", true,null);
//						Integer epId = CircuitRunServerImpl.createProcess(191,
//								ContractBonus.class, contractBonus.getId(),
//								"bonusStatus", "id", contractBonus
//										.getUserName()
//										+ " 的 "
//										+ contractBonus.getBonusMouth()
//										+ " 承包奖金总额: "
//										+ contractBonus.getTotalMoney()
//										+ "元 ,请您审核!", true,null);
						if (epId != null && epId > 0) {
							contractBonus.setEpId(epId);
							totalDao.update(contractBonus);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					return bool;
				}
			}
		}
		return false;
	}

	/***
	 * 根据用户id和月份查询某用户某月是否存在承包奖金总额信息
	 * 
	 * @param userId
	 *            用户id
	 * @param bonusMouth
	 *            月份(如果为空则默认是上个月)
	 * @param bonusStatus
	 *            承包总额状态(为空则为查询所有)
	 * @return ContractBonus
	 */
	public ContractBonus findCBByMouthAndDept(int userId, String bonusMouth,
			String bonusStatus) {
		if ((Object) userId != null && userId > 0) {
			String hql = "from ContractBonus where userId=? and bonusMouth=?";
			if (bonusMouth == null) {// 如果月份为空,则默认为上个月
				bonusMouth = Util.getLastMonth("yyyy-MM月");
			}
			if (bonusStatus != null && bonusStatus.length() > 0) {
				hql += " and bonusStatus=?";
				return (ContractBonus) totalDao.getObjectByCondition(hql,
						userId, bonusMouth, bonusStatus);
			}
			return (ContractBonus) totalDao.getObjectByCondition(hql, userId,
					bonusMouth);

		}
		return null;
	}

	/***
	 * 查询所有+条件查询+分页
	 * 
	 * @param contractBonus
	 *            查询对象
	 * @param pageNo
	 *            页数
	 * @param pageSize
	 *            每页的大小
	 * @param status
	 *            是否审核
	 * @return 封装了集合和数量的数组
	 */
	@SuppressWarnings("unchecked")
	public Object[] findAllContractBonus(ContractBonus contractBonus,
			int pageNo, int pageSize, String status) {
		if (contractBonus == null) {
			contractBonus = new ContractBonus();
		}
		if (status != null && status.length() > 0) {
			if ("audit".equals(status)) {
				contractBonus.setBonusStatus("审核");
			} else if ("buliu".equals(status)) {
			} else if ("allBuLiu".equals(status)) {
				contractBonus.setBonusStatus("部留");
			} else {
				contractBonus.setStatus(status);// 区分部门奖金、承包奖金
			}
		}
		String hql = totalDao.criteriaQueries(contractBonus, null, null);
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] object = { list, count };
		return object;
	}

	/***
	 * 审核承包奖金总额
	 * 
	 * @param auditStatus
	 *            审核状态(agree/back)即同意/打回
	 * @param contractBonusId
	 *            所需审核的总额id 数组
	 * @return String类型 审核消息
	 */
	public String auditContractBonus(String auditStatus, int[] contractBonusId) {
		String message = "";
		if (auditStatus != null && auditStatus.length() > 0) {
			for (int i = 0; i < contractBonusId.length; i++) {
				ContractBonus contractBonus = (ContractBonus) totalDao
						.getObjectById(ContractBonus.class, contractBonusId[i]);
				if (contractBonus != null) {
					if ("agree".equals(auditStatus)) {
						contractBonus.setBonusStatus("同意");
					} else if ("back".equals(auditStatus)) {
						contractBonus.setBonusStatus("打回");
					}
					boolean bool = totalDao.update(contractBonus);
					if (bool) {
						message += contractBonus.getBonusStatus() + " "
								+ contractBonus.getDeptName() + "部门 "
								+ contractBonus.getBonusMouth()
								+ "的承包奖金总额成功!<br/>";
					} else {
						message += "<font color='red'> "
								+ contractBonus.getBonusStatus()
								+ contractBonus.getDeptName() + "部门 "
								+ contractBonus.getBonusMouth()
								+ "的承包奖金总额失败!</font><br/>";
					}
				} else {
					message += "<font color='red'>不存在第" + (i + 1)
							+ "条承包奖金总额数据!请检查!</font><br/>";
				}
			}
		} else {
			message = "<font color='red'>数据异常!请检查后重试!</font>";
		}
		return message;
	}

	/***
	 * 根据id查询承包奖金总额信息
	 * 
	 * @param id
	 *            承包奖金总额id
	 * @return ContractBonus 承包奖金总额对象
	 */
	public ContractBonus findContractBonusById(int id) {
		if ((Object) id != null && id > 0) {
			return (ContractBonus) totalDao.getObjectById(ContractBonus.class,
					id);
		}
		return null;
	}

	/***
	 * 修改承包奖金分配
	 * 
	 * @param contractBonus
	 *            奖金分配类
	 * @return boolean
	 */
	public boolean updateContractBonus(ContractBonus contractBonus) {
		if (contractBonus != null) {
			return totalDao.update(contractBonus);
		}
		return false;
	}

	/***
	 * 删除承包奖金分配
	 * 
	 * @param contractBonus
	 *            奖金分配类
	 * @return boolean
	 */
	public boolean delContractBonus(ContractBonus contractBonus) {
		if (contractBonus != null) {
			return totalDao.delete(contractBonus);
		}
		return false;
	}

	/***
	 * 根据功能名称查询其所绑定的人员
	 * 
	 * @return 用户集合
	 */
	@SuppressWarnings("unchecked")
	public List findUserBuFunction(String functiongName) {
		String hql = "";
		//
		if (functiongName != null && "部门奖金分配".equals(functiongName)) {
			hql = "from Users where id in (select u.id from Users u join u.moduleFunction m where m.functionName=? and u.id not in "
					+ "(select userId from ContractBonus where status='bumen'))";
		} else if (functiongName != null && "承包奖金分配".equals(functiongName)) {
			hql = "from Users where id in (select u.id from Users u join u.moduleFunction m where m.functionName=? and u.id not in "
					+ "(select userId from ContractBonus where status='chengbao' and bonusMouth='"
					+ totalDao.getLastMonth("yyyy-MM月") + "'))";
		}
		return totalDao.query(hql, functiongName);
	}

	/***
	 * 根据登录用户id查询出其所属成员
	 * 
	 * @param userId
	 * @return
	 */
	public List findTeammembersByUserId(int userId) {
		String hql = "from WageStandard where code in ( select code from AssessPersonnel where addUserId=?) and standardStatus='默认' and processStatus='同意'";
		return totalDao.query(hql, userId);
	}

	/***
	 * 根据登录用户id以及所属分组查询出其所属成员
	 * 
	 * @param userId
	 * @return
	 */
	public List findTeammembersByUserId(int userId, int groupId) {
		String hql = "from WageStandard where code in ( select code from AssessPersonnel where addUserId=? and usersGroup.id=?) and standardStatus='默认'";
		return totalDao.query(hql, userId, groupId);
	}

	/***
	 * 添加领取信息
	 * 
	 * @param cbr
	 * @return
	 */
	public boolean addReceiveMessage(ContractBonusReceive cbr) {
		if (cbr != null) {

			cbr.setReceiveTime(totalDao.getDateTime(null));
			return totalDao.save(cbr);
		}
		return false;
	}

	/***
	 * 通过部留信息Id查询领取信息
	 * 
	 * @param cbId
	 * @return
	 */
	public ContractBonusReceive findCbr(int cbId) {
		String hql = "from ContractBonusReceive where cbId=?";
		return (ContractBonusReceive) totalDao.getObjectByCondition(hql, cbId);

	}

	@Override
	public boolean updateExamBonus(Integer[] detailSelect, String tag) {
		boolean bool = false;
		if (null != detailSelect && detailSelect.length > 0) {
			Users user = Util.getLoginUser();
			for (int i = 0; i < detailSelect.length; i++) {// 遍历处理状态
				ContractBonus detail = (ContractBonus) totalDao.getObjectById(
						ContractBonus.class, detailSelect[i]);
				if ("ok".equals(tag)) {// 同意
					CircuitRun circuitRun = circuitRunServer
							.findCircuitRunById(detail.getEpId());
					// 处理审批流程
					String audit = circuitRunServer.updateExeNodeByCirId(detail
							.getEpId(), true, "", false, null, false);
					if ("审批成功".equals(audit)) {// 判断发送提醒消息
						Integer userIds[] = circuitRunServer.findAuditUserIds(
								detail.getEpId(),
								circuitRun.getAuditLevel() + 1);
						if (userIds != null && userIds.length > 0) {
							for (int j = 0; j < userIds.length; j++) {
								Integer userId = userIds[j];
								Users acessUser = (Users) totalDao
										.getObjectById(Users.class, userId);
								// 判断发送提醒消息
								String sendDate = Util
										.getDateTime("yyyy-MM-dd");
								// String hqlMessage =
								// "from OaMessageAlerm where oaUserName='"
								// + user.getName()
								// + "' and sendDate='"
								// + sendDate + "' and accessPhone=?";
								String phone = acessUser.getPassword()
										.getPhoneNumber();
								// if (totalDao.query(hqlMessage, phone).size()
								// <= 0) {
								String msg = user.getDept()
										+ "部门申购计划请您审批！--上海红湖排气系统有限公司";
								// 发送短信提醒
								OaMessageAlerm mess = new OaMessageAlerm();
								mess.setOaUserName(user.getName());
								mess.setContent(msg);
								mess.setAccessPhone(phone);
								mess.setRealName(user.getName());
								mess.setSendDate(sendDate);
								totalDao.save(mess);
								AlertMessagesServerImpl.addAlertMessages(
										"奖金审批", msg, "奖金审批", acessUser
												.getCode(), phone);
							}
						}
					}
					// 更改明细状态
				} else {// 打回
					// 处理审批流程
					// 处理审批流程
					circuitRunServer.updateExeNodeByCirId(detail.getEpId(),
							false, "", false, null, false);
					detail.setBonusStatus("打回");
					// detail.setDetailNextSign(user.getName());
					totalDao.update(detail);
				}
				bool = true;
			}
		}
		return bool;
	}

	@Override
	public Object[] findExamList(int parseInt, int pageSize) {
		// 返回条件 明细ID
		/**
		 * false:未审批 true：已审批
		 * 
		 */
		Map<String, Object> map = CircuitRunServerImpl.findAuditExeNode(
				ContractBonus.class, false);
		if (map != null) {
			String hql = "from ContractBonus where id in (:entityId)";
			List list = totalDao.find(hql, map, parseInt, pageSize);
			Object[] exam = new Object[2];
			Long countLong = totalDao.count("select count(*) " + hql, map);
			int count = Integer.parseInt(countLong.toString());
			exam[0] = count;
			exam[1] = list;
			return exam;
		}
		return null;
	}
}
