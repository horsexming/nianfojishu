package com.task.ServerImpl.pro;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.task.Dao.TotalDao;
import com.task.DaoImpl.TotalDaoImpl;
import com.task.Server.pro.ProjectManageServer;
import com.task.ServerImpl.ShortMessageServiceImpl;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.AskForLeave;
import com.task.entity.OaAppDetail;
import com.task.entity.Users;
import com.task.entity.fin.budget.DeptMonthBudget;
import com.task.entity.project.BaomiOperateLog;
import com.task.entity.project.ProjectManage;
import com.task.entity.project.ProjectTime;
import com.task.entity.project.ProjectWenJian;
import com.task.entity.project.QuotedPrice;
import com.task.entity.project.QuotedProcessInfor;
import com.task.entity.shizhi.SkillScore;
import com.task.entity.systemfile.SystemFile;
import com.task.util.RtxUtil;
import com.task.util.Upload;
import com.task.util.Util;

/***
 * 项目管理(立项)
 * 
 * @author 刘培
 * 
 */
public class ProjectManageServerImpl implements ProjectManageServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/***
	 * 添加立项信息
	 * 
	 * @param projectManage
	 * @throws Exception
	 */
	@Override
	public void addProjectManage(ProjectManage projectManage,
			File[] attachment, String[] attachmentFileName, String[] otherName,
			int[] userIds) throws Exception {
		if (projectManage != null) {
			// 填报人信息
			Users loginUser = Util.getLoginUser();
			projectManage.setUserId(loginUser.getId());
			projectManage.setCode(loginUser.getCode());
			projectManage.setUserName(loginUser.getName());
			// 添加时间
			projectManage.setDateTime(Util.getDateTime());
			// 审批状态
			projectManage.setAduitStatus("未审批");
			// 流程状态
			projectManage.setStatus("立项");
			// 附件处理
			Set<ProjectWenJian> projectWenjianSet = new HashSet<ProjectWenJian>();
			if (attachment != null && attachment.length > 0) {
				for (int i = 0; i < attachment.length; i++) {
					ProjectWenJian projectWenjian = new ProjectWenJian();
					File file = attachment[i];
					String fileName = attachmentFileName[i];
					Upload upload = new Upload();
					String newFileName = upload.UploadFile(file, fileName,
							null, "/upload/file/project",
							"D:/WorkSpace/HHTask/WebRoot/upload/file");
					projectManage.setFileName(newFileName);
					projectWenjian.setFileName(newFileName);
					String othersName = otherName[i];
					if (othersName == null || "".equals(othersName)) {
						othersName = fileName;
					}
					projectWenjian.setOtherName(othersName);
					projectWenjianSet.add(projectWenjian);
				}
			}
			// 立项编号处理
			String num = null;
			if (projectManage.getProjectNum() == null
					|| projectManage.getProjectNum().length() == 0) {
				num = "XM-" + Util.getDateTime("yyyy");
			} else {
				num = projectManage.getProjectNum();
			}
			// if(projectManage.getProjectNum()!=null &&
			// projectManage.getProjectNum().length()>0){
			// num = projectManage.getProjectNum()+Util.getDateTime("yyyy");
			// }
			String hql = "select max(projectNum) from ProjectManage  where projectNum like '%"
					+ num + "%'";
			Object obj = totalDao.getObjectByCondition(hql);
			if (obj == null) {
				num += "-001";
			} else {
				String maxNum = (String) obj;
				int number = Integer.parseInt(maxNum.substring(
						maxNum.lastIndexOf("-") + 1, maxNum.length())) + 1;
				if (number < 10) {
					num += "-00" + number;
				} else if (number < 100) {
					num += "-0" + number;
				} else {
					num += "-" + number;
				}
			}
			projectManage.setProjectNum(num);

			String maxNumber = (String) totalDao
					.getObjectByCondition("select max(projectNumber) from ProjectManage");
			DecimalFormat format = new DecimalFormat("00000");
			if (maxNumber != null && maxNumber != "") {
				Integer num1 = Integer.parseInt(maxNumber);
				Integer num2 = num1 + 1;
				String num3 = format.format(num2);
				projectManage.setProjectNumber(num3);
			} else {
				projectManage.setProjectNumber("00001");
			}
			// 添加立项信息
			projectManage.setProjectWenJianSet(projectWenjianSet);
			totalDao.save(projectManage);
			if (projectManage.getIsbaomi() != null
					&& projectManage.getIsbaomi().equals("是")) {
				BaomiOperateLog log = new BaomiOperateLog();
				log.setOperateType("增加");// 操作类型
				log.setOperateObject("项目");// 操作对象
				log.setOperateRemark("项目名称:" + projectManage.getProjectName()
						+ ",项目编号：" + projectManage.getProjectNum());//
				log.setOperateTime(Util.getDateTime());//
				log.setOperateUserId(loginUser.getId());
				log.setOperateUsername(loginUser.getName());//
				log.setOperateCode(loginUser.getCode());//
				log.setOperateDept(loginUser.getDept());//
				totalDao.save2(log);
			}

			// 添加审批流程
			String processName = "项目立项审核";
			StringBuffer userIdSb = new StringBuffer();
			for (int n = 0; n < userIds.length; n++) {
				if (userIds != null) {
					if (n == 0) {
						userIdSb.append(userIds[n]);
					} else {
						userIdSb.append("," + userIds[n]);
					}
				}
			}
			Integer epId = CircuitRunServerImpl.createProcessbf(processName,
					ProjectManage.class, projectManage.getId(), "aduitStatus",
					"id", "ProjectManage_findProjectManage.action?id="
							+ projectManage.getId(),
					projectManage.getProjectName() + "项目立项信息请您审核!", true,
					userIdSb.toString());

			// Integer epId = CircuitRunServerImpl.createProcess(processName,
			// ProjectManage.class, projectManage.getId(), "aduitStatus",
			// "id", projectManage.getProjectName() + "项目立项信息请您审核!", true,
			// null);
			// Integer epId = CircuitRunServerImpl.createProcess(20,
			// ProjectManage.class, projectManage.getId(), "aduitStatus",
			// "id", projectManage.getProjectName() + "项目立项信息请您审核!", true,
			// null);
			if (epId != null && epId > 0) {
				projectManage.setEpId(epId);
				totalDao.update(projectManage);
			}

			// 添加时间表
			String datetime = Util.getDateTime();
			ProjectTime projectTime = new ProjectTime(null, "立项", "lx",
					loginUser.getDept(), loginUser.getId(),
					loginUser.getName(), "no", datetime, datetime, "",
					datetime, loginUser.getName(), 0, projectManage.getId(),
					null, projectManage);

			totalDao.save(projectTime);
		}
	}

	/***
	 * 删除立项信息
	 * 
	 * @param projectManage
	 */
	@Override
	public void delProjectManage(Integer id) {
		ProjectManage projectManage = (ProjectManage) totalDao.getObjectById(
				ProjectManage.class, id);
		if (projectManage != null && projectManage.getIsbaomi() != null
				&& projectManage.getIsbaomi().equals("是")) {
			Users loginUser = Util.getLoginUser();
			BaomiOperateLog log = new BaomiOperateLog();
			log.setOperateType("删除");// 操作类型
			log.setOperateObject("项目");// 操作对象
			log.setOperateRemark("项目名称:" + projectManage.getProjectName()
					+ ",项目编号：" + projectManage.getProjectNum());//
			log.setOperateTime(Util.getDateTime());//
			log.setOperateUserId(loginUser.getId());
			log.setOperateUsername(loginUser.getName());//
			log.setOperateCode(loginUser.getCode());//
			log.setOperateDept(loginUser.getDept());//
			totalDao.save2(log);
		}
		totalDao.delete(projectManage);
	}

	/***
	 * 查询立项信息(条件查询、分页)
	 * 
	 * @param projectManage
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object[] findPMByCondition(ProjectManage projectManage, int pageNo,
			int pageSize, String status) {
		Users user = Util.getLoginUser();
		if (projectManage == null) {
			projectManage = new ProjectManage();
		}
		String hql = totalDao.criteriaQueries(projectManage, null);
		if ("baomiAll".equals(status)) {
			hql += " and isbaomi = '是' ";
		} else if ("baomi".equals(status)) {
			Integer uid = (Integer) totalDao
					.getObjectByCondition("select u.id from Users u join u.moduleFunction f  where u.id='"
							+ user.getId()
							+ "' and f.functionName in('项目核算填报(所有保密)','核算管理(所有保密)'	)");
			if (uid != null) {
				hql += " and isbaomi ='是' ";
			} else {

				hql += " and isbaomi = '是' and ((id in(select proId from ProjectTime where userId='"
						+ user.getId()
						+ "')) or userId='"
						+ user.getId()
						+ "')";
			}
		} else {
			hql += " and (isbaomi is null or isbaomi !='是')";
		}

		if ("proson".equals(status)) {
			hql += " and userId = " + Util.getLoginUser().getId() + " ";
		}

		List list = totalDao.findAllByPage(hql + " order by id desc", pageNo,
				pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/***
	 * 项目立项信息审批
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Override
	public Object[] findPMForAudit(int pageNo, int pageSize) {

		Map<String, Object> map = CircuitRunServerImpl.findAuditExeNode(
				ProjectManage.class, false);

		if (map != null) {
			String hql = "from ProjectManage where id in (:entityId)";
			List list = totalDao.find(hql, map, pageNo, pageSize);
			Object[] exam = new Object[2];
			Long countLong = totalDao.count("select count(*) " + hql, map);
			int count = Integer.parseInt(countLong.toString());
			exam[0] = list;
			exam[1] = count;
			return exam;
		}
		return null;
	}

	/***
	 * 查询立项信息
	 * 
	 * @param id
	 *            主键id
	 */
	@Override
	public ProjectManage afindProjectManage(Integer id) {
		ProjectManage projectManage = (ProjectManage) totalDao.getObjectById(
				ProjectManage.class, id);
		if (projectManage != null && projectManage.getIsbaomi() != null
				&& projectManage.getIsbaomi().equals("是")) {
			Users loginUser = Util.getLoginUser();
			BaomiOperateLog log = new BaomiOperateLog();
			log.setOperateType("查看");// 操作类型
			log.setOperateObject("项目");// 操作对象
			log.setOperateRemark("项目名称:" + projectManage.getProjectName()
					+ ",项目编号：" + projectManage.getProjectNum() + "的明细");//
			log.setOperateTime(Util.getDateTime());//
			log.setOperateUserId(loginUser.getId());
			log.setOperateUsername(loginUser.getName());//
			log.setOperateCode(loginUser.getCode());//
			log.setOperateDept(loginUser.getDept());//
			totalDao.save2(log);
		}
		return projectManage;
	}

	/***
	 * 修改立项信息
	 * 
	 * @param projectManage
	 */
	@Override
	public void updateProjectManage(ProjectManage projectManage,
			File[] attachment, String[] attachmentFileName, String[] otherName,
			int[] userIds) {
		// // 添加时间
		// projectManage.setDateTime(Util.getDateTime());
		// // 流程状态
		// projectManage.setStatus("立项");
		Users loginUser = Util.getLoginUser();

		ProjectManage old = (ProjectManage) totalDao.getObjectById(
				ProjectManage.class, projectManage.getId());
		Set<ProjectWenJian> projectWenjianSet = old.getProjectWenJianSet();
		// BeanUtils.copyProperties(projectManage, old,new
		// String[]{"userId","code","userName","dateTime",
		// "aduitStatus","status","fileName","isbaomi","epId",""});
		old.setAduitStatus("未审批");

		// 附件处理
		if (attachment != null && attachment.length > 0) {
			if (projectWenjianSet == null) {
				projectWenjianSet = new HashSet<ProjectWenJian>();
			} else {
				// projectWenjianSet
			}
			for (int i = 0; i < attachment.length; i++) {
				ProjectWenJian projectWenjian = new ProjectWenJian();
				File file = attachment[i];
				String fileName = attachmentFileName[i];
				Upload upload = new Upload();
				String newFileName = upload.UploadFile(file, fileName, null,
						"/upload/file/project",
						"D:/WorkSpace/HHTask/WebRoot/upload/file");
				projectManage.setFileName(newFileName);
				projectWenjian.setFileName(newFileName);
				projectWenjian.setOtherName(otherName[i]);
				projectWenjianSet.add(projectWenjian);
			}
		}
		old.setUserId(loginUser.getId());
		old.setUserName(loginUser.getName());
		old.setCode(loginUser.getCode());
		old.setProjectWenJianSet(projectWenjianSet);

		old.setProType(projectManage.getProType());
		old.setProjectName(projectManage.getProjectName());
		old.setIsbaomi(projectManage.getIsbaomi());
		old.setClient(projectManage.getClient());
		old.setYuMoney(projectManage.getYuMoney());
		old.setContent(projectManage.getContent());
		boolean udpate = totalDao.update(old);
		if (udpate) {
			String processName = "项目立项审核";
			// 添加审批流程
			StringBuffer userIdSb = new StringBuffer();
			for (int n = 0; n < userIds.length; n++) {
				if (userIds != null) {
					if (n == 0) {
						userIdSb.append(userIds[n]);
					} else {
						userIdSb.append("," + userIds[n]);
					}
				}
			}
			try {
				Integer epId = CircuitRunServerImpl.createProcessbf(
						processName, ProjectManage.class,
						projectManage.getId(), "aduitStatus", "id",
						"ProjectManage_findProjectManage.action?id="
								+ projectManage.getId(),
						projectManage.getProjectName() + "项目立项信息请您审核!", true,
						userIdSb.toString());
				if (epId != null && epId > 0) {
					projectManage.setEpId(epId);
					totalDao.update(projectManage);
				}
				// 添加时间表
				String datetime = Util.getDateTime();
				ProjectTime projectTime = new ProjectTime(null, "立项", "lx",
						loginUser.getDept(), loginUser.getId(),
						loginUser.getName(), "no", datetime, datetime, "",
						datetime, loginUser.getName(), 0,
						projectManage.getId(), null, projectManage);

				totalDao.save(projectTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/***
	 * 修改
	 * 
	 * @param projectManage
	 */
	@Override
	public void update(ProjectManage projectManage) {
		totalDao.update(projectManage);
	}

	/***
	 * 查询所有待核算的项目
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectManage> findHsPro() {
		String hql = "from ProjectManage where aduitStatus='同意' and status='立项'";
		return totalDao.query(hql);
	}

	/***
	 * 查询项目对应的时间表
	 * 
	 * @param proId
	 * @return
	 */
	@Override
	public List afindDeptProTime(Integer quoId) {
		if (quoId != null) {
			QuotedPrice qp = (QuotedPrice) totalDao.getObjectById(
					QuotedPrice.class, quoId);
			if (qp != null && qp.getIsbaomi() != null
					&& qp.getIsbaomi().equals("是")) {
				ProjectManage projectManage = (ProjectManage) totalDao
						.getObjectById(ProjectManage.class, qp.getProId());
				if (projectManage != null && projectManage.getIsbaomi() != null
						&& projectManage.getIsbaomi().equals("是")) {
					Users loginUser = Util.getLoginUser();
					BaomiOperateLog log = new BaomiOperateLog();
					log.setOperateType("查看");// 操作类型
					log.setOperateObject("BOM");// 操作对象
					log.setOperateRemark("对项目名称:"
							+ projectManage.getProjectName() + ",项目编号："
							+ projectManage.getProjectNum() + "的项目下的编码为："
							+ qp.getMarkId() + "的BOM指派进度进行查看");//
					log.setOperateTime(Util.getDateTime());//
					log.setOperateUserId(loginUser.getId());
					log.setOperateUsername(loginUser.getName());//
					log.setOperateCode(loginUser.getCode());//
					log.setOperateDept(loginUser.getDept());//
					totalDao.save2(log);
				}
			}
			String hql = "from ProjectTime where quoId=? and classNumber not in ('lx','hstb','sb','gzf') order by level";
			return totalDao.query(hql, quoId);
		}
		return null;
	}

	/**
	 * 指派各部门填报录入时间
	 * 
	 * @param proTime
	 */
	@Override
	public void updateProTime(ProjectTime proTime) {
		if (proTime != null && proTime.getId() != null && proTime.getId() > 0) {
			ProjectTime oldProTime = (ProjectTime) totalDao.getObjectById(
					ProjectTime.class, proTime.getId());
			// 实际时间已有则表示已完成，不再修改
			if (oldProTime != null && oldProTime.getRealTime() == null) {
				oldProTime.setDept(proTime.getDept());
				oldProTime.setUserId(proTime.getUserId());
				Users sendUser = (Users) totalDao.getObjectById(Users.class,
						oldProTime.getUserId());
				oldProTime.setUserName(sendUser.getName());
				oldProTime.setIsSendSms(proTime.getIsSendSms());
				oldProTime.setProvisionTime(proTime.getProvisionTime());
				totalDao.update(oldProTime);

				// 发送短信提醒
				if ("yes".equals(oldProTime.getIsSendSms())) {
					// 获得hibernateTemplate对象，并赋值给totalDao
					RtxUtil.sendNotify(sendUser.getCode(), "您有需要填写"
							+ oldProTime.getClassName() + "的新项目,截止时间"
							+ oldProTime.getProvisionTime() + ".请及时填报，谢谢!",
							"时间指派", "0", "0");
					// HibernateTemplate ht =
					// TotalDaoImpl.findHibernateTemplate();
					// TotalDao totalDao = new TotalDaoImpl();
					// ((HibernateDaoSupport)
					// totalDao).setHibernateTemplate(ht);
					// ShortMessageServiceImpl sms = new
					// ShortMessageServiceImpl();
					// sms.setTotalDao(totalDao);
					// sms.send(sendUser.getPassword().getPhoneNumber(),
					// "您有需要填写"
					// + oldProTime.getClassName() + "的新项目,截止时间"
					// + oldProTime.getProvisionTime() + ".请及时填报，谢谢!");
				}

				// 更新核价状态
				QuotedPrice quo = (QuotedPrice) totalDao.getObjectById(
						QuotedPrice.class, oldProTime.getQuoId());
				if (oldProTime.getClassNumber().equals("bom")) {
					quo.setStatus("BOM录入");
					totalDao.update(quo);
				}
				if (quo != null && quo.getIsbaomi() != null
						&& quo.getIsbaomi().equals("是")) {
					ProjectManage projectManage = (ProjectManage) totalDao
							.getObjectById(ProjectManage.class,
									oldProTime.getProId());
					if (projectManage != null
							&& projectManage.getIsbaomi() != null
							&& projectManage.getIsbaomi().equals("是")) {
						Users loginUser = Util.getLoginUser();
						BaomiOperateLog log = new BaomiOperateLog();
						log.setOperateType("修改");// 操作类型
						log.setOperateObject("BOM");// 操作对象
						log.setOperateRemark("对项目名称:"
								+ projectManage.getProjectName() + ",项目编号："
								+ projectManage.getProjectNum() + "的项目下的编码为"
								+ quo.getMarkId() + "的BOM进行指派");//
						log.setOperateTime(Util.getDateTime());//
						log.setOperateUserId(loginUser.getId());
						log.setOperateUsername(loginUser.getName());//
						log.setOperateCode(loginUser.getCode());//
						log.setOperateDept(loginUser.getDept());//
						totalDao.save2(log);
					}
				}
			}
		}
	}

	/***
	 * 根据项目id以及类别编号查询时间表并完成该类别
	 * 
	 * @param proId
	 *            项目id
	 * @param pageStatus
	 *            类别编号
	 */
	@Override
	public Map<Integer, Object> updateProTimeForFinal(Integer qpId,
			String pageStatus) {
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		boolean b = true;
		String msg = null;
		if (qpId != null && pageStatus != null) {
			// 查找核算信息
			QuotedPrice qp = (QuotedPrice) totalDao.getObjectById(
					QuotedPrice.class, qpId);
			List<QuotedPrice> qpList = totalDao.query(
					"from QuotedPrice where rootId=?", qpId);
			if (qp != null) {
				String hql = "from ProjectTime where quoId=? and classNumber=?";
				ProjectTime oldProTime = (ProjectTime) totalDao
						.getObjectByCondition(hql, qp.getId(), pageStatus);
				if (oldProTime != null) {
					oldProTime.setRealTime(Util.getDateTime());// 完成时间
					// 计算超出时间
					Long date1 = Util.StringToDate(
							oldProTime.getProvisionTime(),
							"yyyy-MM-dd HH:mm:ss").getTime();
					Long date2 = Util.StringToDate(oldProTime.getRealTime(),
							"yyyy-MM-dd HH:mm:ss").getTime();
					Long moreDate = date2 - date1;
					String mes = "";
					if (moreDate < 0) {
						moreDate = -moreDate;
						mes = "提前";
					}
					Long hourTime = 1000 * 60 * 60L;// 转化为小时
					Long day = moreDate / (hourTime * 24);// 超出天数
					Long hour = (moreDate % (hourTime * 24)) / hourTime;
					oldProTime.setExceedTime(mes + day + "天" + hour + "小时");// 超出时间
					// totalDao.update(oldProTime);

					Float money = 0F;
					Float moneysz = 0F;
					Float moneysj = 0F;
					if ("bom".equals(pageStatus)) {// BOM录入完成后更新核算状态为"集合报价"
						qp.setStatus("集合报价");
						b = totalDao.update(qp);
					} else if ("cl".equals(pageStatus)) {// 材料费
						// 材料费
						for (QuotedPrice qp1 : qpList) {
							if (qp1.getTrademark() != null
									&& qp1.getLastMonthPrice() == null) {
								b = false;
								msg = "材料录入未全部完成提交失败(件号：" + qp1.getMarkId()
										+ ")！";
								break;
							}
						}
						if (b) {
							String hql2 = "select sum(materialPrice) from QuotedPrice where rootId=?";
							Object obj = totalDao.getObjectByCondition(hql2,
									qp.getId());
							if (obj == null) {
								obj = 0;
							}
							money = Float.parseFloat(obj.toString());
							moneysj = (Float) totalDao
									.getObjectByCondition(
											"select sum(materialPricesj) from QuotedPrice where rootId=?",
											qp.getId());
							moneysz = (Float) totalDao
									.getObjectByCondition(
											"select sum(materialPricesz) from QuotedPrice where rootId=?",
											qp.getId());
						}

					} else if ("sb".equals(pageStatus)) {// 设备折旧
						int flag = 0;
						for (QuotedPrice qp1 : qpList) {
							Set<QuotedProcessInfor> qpInfoSet = qp1
									.getQpinfor();
							if (qpInfoSet != null && qpInfoSet.size() > 0) {
								for (QuotedProcessInfor qpInfo : qpInfoSet) {
									if (qpInfo.getShebeiId() != null
											&& (qpInfo.getShebeiDateTime() == null
													|| qpInfo
															.getShebeiJingzhi() == null || qpInfo
													.getShebeiZjYear() == null)) {
										b = false;
										msg = "设备录入未全部完成，提交失败(件号："
												+ qp1.getMarkId() + ",工序号："
												+ qpInfo.getProcessNO() + ")!";
										flag = 1;
										break;
									}
								}
							}
							if (flag == 1) {
								break;
							}
						}
						if (b) {
							String hql2 = "select sum(shebeiZhejiu) from QuotedProcessInfor where quotedPrice.id in "
									+ "(select id from QuotedPrice where rootId=?)";
							Object obj = totalDao.getObjectByCondition(hql2,
									qp.getId());
							if (obj == null) {
								obj = 0;
							}
							money = Float.parseFloat(obj.toString());
						}
					} else if ("gz".equals(pageStatus)) {// 自制工装
						// 工装分摊
						int flag = 0;
						for (QuotedPrice qp1 : qpList) {
							Set<QuotedProcessInfor> qpInfoSet = qp1
									.getQpinfor();
							if (qpInfoSet != null && qpInfoSet.size() > 0) {
								for (QuotedProcessInfor qpInfo : qpInfoSet) {
									if (qpInfo.getGongzhuangId() == null
											&& ((qpInfo.getGongzhuangNumber() != null && !qpInfo
													.getGongzhuangNumber()
													.equals("")) || (qpInfo
													.getGongzhuang() != null && !qpInfo
													.getGongzhuang().equals("")))
											&& qpInfo.getGongzhuangPrice() == null) {
										b = false;
										msg = "自制工装录入未全部完成，提交失败(件号："
												+ qp1.getMarkId() + ",工序号："
												+ qpInfo.getProcessNO() + ")!";
										flag = 1;
										break;
									}
								}
							}
							if (flag == 1) {
								break;
							}
						}
						if (b) {
							String hql2 = "select sum(gongzhuangFt) from QuotedProcessInfor where quotedPrice.id in "
									+ "(select id from QuotedPrice where rootId=?)";
							Object obj = totalDao.getObjectByCondition(hql2,
									qp.getId());
							if (obj == null) {
								obj = 0;
							}
							money = Float.parseFloat(obj.toString());
							/** 直接工装处理开始 **/
							// 获得自制直接工装费用
							String hql3 = "select sum(gongzhuangFy) from QuotedProcessInfor where quotedPrice.id in "
									+ "(select id from QuotedPrice where rootId=?)";
							Object obj2 = totalDao.getObjectByCondition(hql3,
									qp.getId());
							// 获得外购件直接工装费用
							String hql4 = "select sum(gongzhuangFei) from QuotedPrice where rootId=?";
							Object obj4 = totalDao.getObjectByCondition(hql4,
									qp.getId());
							Float zjGongzhuaFei = 0F;
							if (obj2 != null) {
								zjGongzhuaFei += Float.parseFloat(obj2
										.toString());
							}
							if (obj4 != null) {
								zjGongzhuaFei += Float.parseFloat(obj4
										.toString());
							}
							// 直接工装费用(自制)
							ProjectTime gzfProTime = (ProjectTime) totalDao
									.getObjectByCondition(hql, qp.getId(),
											"gzf");
							gzfProTime.setMoney(zjGongzhuaFei);
							totalDao.update(gzfProTime);
							/** 直接工装处理结束 **/
						}
					} else if ("fl".equals(pageStatus)) {// 辅料
						int flag = 0;
						// for (QuotedPrice qp1 : qpList) {
						// Set<QuotedProcessInfor> qpInfoSet = qp1
						// .getQpinfor();
						// if (qpInfoSet != null && qpInfoSet.size() > 0) {
						// for (QuotedProcessInfor qpInfo : qpInfoSet) {
						// if (qpInfo.getFuliao() == null
						// || qpInfo.getFuliaoxh() == null
						// || qpInfo.getFuliaoMoney() == null) {
						// b = false;
						// msg = "辅料录入未全部完成，提交失败!";
						// flag = 1;
						// break;
						// }
						// }
						// }
						// if (flag == 1) {
						// break;
						// }
						// }
						if (b) {
							String hql2 = "select sum(fuliaoMoney) from QuotedProcessInfor where quotedPrice.id in "
									+ "(select id from QuotedPrice where rootId=?)";
							Object obj = totalDao.getObjectByCondition(hql2,
									qp.getId());
							if (obj == null) {
								obj = 0;
							}
							money = Float.parseFloat(obj.toString());
							moneysj = (Float) totalDao
									.getObjectByCondition(
											"select sum(fuliaoMoneysj) from QuotedProcessInfor where quotedPrice.id in "
													+ "(select id from QuotedPrice where rootId=?)",
											qp.getId());
							moneysz = (Float) totalDao
									.getObjectByCondition(
											"select sum(fuliaoMoneysz) from QuotedProcessInfor where quotedPrice.id in "
													+ "(select id from QuotedPrice where rootId=?)",
											qp.getId());
						}
					} else if ("rg".equals(pageStatus)) {// 人工
						String hql2 = "select sum(rengongfei) from QuotedProcessInfor where quotedPrice.id in "
								+ "(select id from QuotedPrice where rootId=?)";
						Object obj = totalDao.getObjectByCondition(hql2,
								qp.getId());
						if (obj == null) {
							obj = 0;
						}
						money = Float.parseFloat(obj.toString());

						/** 设备折旧处理 **/
						String hql3 = "select sum(shebeiZhejiu) from QuotedProcessInfor where quotedPrice.id in "
								+ "(select id from QuotedPrice where rootId=?)";
						Object obj3 = totalDao.getObjectByCondition(hql3,
								qp.getId());
						if (obj3 != null) {
							Float money2 = Float.parseFloat(obj3.toString());
							// 直接工装费用(自制)
							ProjectTime sbfProTime = (ProjectTime) totalDao
									.getObjectByCondition(hql, qp.getId(), "sb");
							sbfProTime.setMoney(money2);
							totalDao.update(sbfProTime);
						}
						/** 设备折旧处理 **/
					} else if ("wgww".equals(pageStatus)) {// 外购外委
						// 工序外委价格
						int flag = 0;
						for (QuotedPrice qp1 : qpList) {
							boolean isneedPrice = true;
							if (qp1.getQuotedPrice() != null
									&& qp1.getQuotedPrice()
											.getYucailiaostatus() != null
									&& qp1.getQuotedPrice()
											.getYucailiaostatus().equals("是")) {
								isneedPrice = false;// 如果上层是外购状态则不需要验证本层是否外委外购录入
							}
							if (isneedPrice) {
								if (qp1.getProcardStyle() != null
										&& qp1.getProcardStyle().equals("外购")) {
									if (qp1.getWaigouPrice() == null
											|| qp1.getGongzhuangFei() == null) {
										b = false;
										msg = "外购外委录入未全部完成，提交失败(件号："
												+ qp1.getMarkId() + ")!";
										break;
									}
								} else if (qp1.getProcardStyle() != null
										&& qp1.getProcardStyle().equals("自制")
										&& qp1.getYucailiaostatus() != null
										&& qp1.getYucailiaostatus().equals("是")) {
									if (qp1.getWaigouPrice() == null
											|| qp1.getGongzhuangFei() == null) {
										b = false;
										msg = "外购外委录入未全部完成，提交失败(件号："
												+ qp1.getMarkId() + ")!";
										break;
									}
								} else {
									boolean isneedPrice2 = true;
									if (qp1.getProcardStyle() != null
											&& qp1.getProcardStyle().equals(
													"组合")
											&& qp1.getYucailiaostatus() != null
											&& qp1.getYucailiaostatus().equals(
													"是")) {
										isneedPrice2 = false;// 组合是外购的情况下不需要验证工序外委外购录入
									}
									Set<QuotedProcessInfor> qpInfoSet = qp1
											.getQpinfor();
									if (isneedPrice2) {
										if (qpInfoSet != null
												&& qpInfoSet.size() > 0) {
											for (QuotedProcessInfor qpInfo : qpInfoSet) {
												if (qpInfo.getProductStyle() != null
														&& qpInfo
																.getProductStyle()
																.equals("外委")
														&& qpInfo.getWwPrice() == null) {
													b = false;
													msg = "外购外委录入未全部完成，提交失败(件号："
															+ qp1.getMarkId()
															+ ",工序号："
															+ qpInfo.getProcessNO()
															+ ")!";
													flag = 1;
													break;
												}
											}
										}
										if (flag == 1) {
											break;
										}
									}
								}
							}
						}
						if (b) {
							String hql2 = "select sum(wwPrice) from QuotedProcessInfor where quotedPrice.id in "
									+ "(select id from QuotedPrice where rootId=?)";
							Object obj = totalDao.getObjectByCondition(hql2,
									qp.getId());
							if (obj != null) {
								money = Float.parseFloat(obj.toString());
							}
							moneysz = (Float) totalDao
									.getObjectByCondition(
											"select sum(wwPricesz) from QuotedProcessInfor where quotedPrice.id in "
													+ "(select id from QuotedPrice where rootId=?)",
											qp.getId());
							moneysj = (Float) totalDao
									.getObjectByCondition(
											"select sum(wwPricesj) from QuotedProcessInfor where quotedPrice.id in "
													+ "(select id from QuotedPrice where rootId=?)",
											qp.getId());
							// 外购价格
							String hql3 = "select sum(waigouPrice) from QuotedPrice where rootId=?";
							Object obj2 = totalDao.getObjectByCondition(hql3,
									qp.getId());
							if (obj2 != null) {
								money += Float.parseFloat(obj2.toString());
							}
							Float moneysz2 = (Float) totalDao
									.getObjectByCondition(
											"select sum(waigouPricesz) from QuotedPrice where rootId=?",
											qp.getId());
							Float moneysj2 = (Float) totalDao
									.getObjectByCondition(
											"select sum(waigouPricesj) from QuotedPrice where rootId=?",
											qp.getId());
							if (moneysz2 != null) {
								moneysz += moneysz2;
							}
							if (moneysj2 != null) {
								moneysj += moneysj2;
							}
							/** 直接工装处理开始 **/
							// 获得自制直接工装费用
							String hql5 = "select sum(gongzhuangFy) from QuotedProcessInfor where quotedPrice.id in "
									+ "(select id from QuotedPrice where rootId=?)";
							Object obj5 = totalDao.getObjectByCondition(hql5,
									qp.getId());
							// 获得外购件直接工装费用
							String hql4 = "select sum(gongzhuangFei) from QuotedPrice where rootId=?";
							Object obj4 = totalDao.getObjectByCondition(hql4,
									qp.getId());
							Float zjGongzhuaFei = 0F;
							if (obj5 != null) {
								zjGongzhuaFei += Float.parseFloat(obj5
										.toString());
							}
							if (obj4 != null) {
								zjGongzhuaFei += Float.parseFloat(obj4
										.toString());
							}
							// 直接工装费用(自制)
							ProjectTime gzfProTime = (ProjectTime) totalDao
									.getObjectByCondition(hql, qp.getId(),
											"gzf");
							gzfProTime.setMoney(zjGongzhuaFei);
							totalDao.update(gzfProTime);
							/** 直接工装处理结束 **/
						}
					} else if ("nyxh".equals(pageStatus)) {// 能源消耗
						for (QuotedPrice qp1 : qpList) {
							if (qp1.getProductStyle() != null
									&& !qp1.getProcardStyle().equals("外购")
									&& (qp1.getShuiFei() == null
											|| qp1.getDianFei() == null
											|| qp1.getFengFei() == null || qp1
											.getQitiFei() == null)) {
								b = false;
								msg = "能源消耗录入未全部完成，提交失败(件号：" + qp1.getMarkId()
										+ ")!";
								break;
							}
						}
						if (b) {
							String hql2 = "select sum(shuiFei+dianFei+fengFei+qitiFei) from QuotedPrice where rootId=?";
							Object obj = totalDao.getObjectByCondition(hql2,
									qp.getId());
							if (obj == null) {
								obj = 0;
							}
							money = Float.parseFloat(obj.toString());
							moneysz = (Float) totalDao
									.getObjectByCondition(
											"select sum(shuiFeisz+dianFeisz+fengFeisz+qitiFeisz) from QuotedPrice where rootId=?",
											qp.getId());
							moneysj = (Float) totalDao
									.getObjectByCondition(
											"select sum(shuiFeisj+dianFeisj+fengFeisj+qitiFeisj) from QuotedPrice where rootId=?",
											qp.getId());
						}
					} else if ("bzys".equals(pageStatus)) {// 包装运输
						if (qp.getBaozhuangFei() == null
								|| qp.getYunshuFei() == null) {
							b = false;
							msg = "包装运输录入未全部完成，提交失败(件号：" + qp.getMarkId()
									+ ")!";
						}
						if (b) {
							String hql2 = "select sum(baozhuangFei+yunshuFei) from QuotedPrice where rootId=?";
							Object obj = totalDao.getObjectByCondition(hql2,
									qp.getId());
							if (obj == null) {
								obj = 0;
							}
							money = Float.parseFloat(obj.toString());
						}
					} else if ("cwgl".equals(pageStatus)) {// 管理、财务费
						String hql2 = "select sum(caiwuFei+manageFei) from QuotedPrice where rootId=?";
						Object obj = totalDao.getObjectByCondition(hql2,
								qp.getId());
						if (obj == null) {
							obj = 0;
						}
						money = Float.parseFloat(obj.toString());
					} else if ("gzf".equals(pageStatus)) {// 工装费
						String hql2 = "select sum(gongzhuangFei) from QuotedPrice where rootId=?";
						Object obj = totalDao.getObjectByCondition(hql2,
								qp.getId());
						if (obj == null) {
							obj = 0;
						}
						money = Float.parseFloat(obj.toString());
					}
					if (b) {
						money = Float.parseFloat(String.format("%.2f", money));
						moneysz = Float.parseFloat(String.format("%.2f",
								moneysz));
						moneysj = Float.parseFloat(String.format("%.2f",
								moneysj));
						oldProTime.setMoney(money);
						oldProTime.setMoneysz(moneysz);
						oldProTime.setMoneysj(moneysj);
						b = b & totalDao.update(oldProTime);
						// System.out.println(oldProTime.getMoney());
					}
				}
			}
		} else {
			b = false;
			msg = "您选中的零件不存在";
		}
		map.put(1, b);
		map.put(2, msg);
		return map;
	}

	/***
	 * 查询所有的项目信息
	 */
	@Override
	public List findAllProMan() {
		String hql = "from ProjectManage order by projectNum desc";
		return totalDao.query(hql);

	}

	@Override
	public boolean delwenjian(Integer id) {
		ProjectWenJian prowenjian = (ProjectWenJian) totalDao.get(
				ProjectWenJian.class, id);
		prowenjian.setProjectManage(null);
		boolean delete = totalDao.delete(prowenjian);
		return delete;
	}

	@Override
	public Map<Integer, Object> findBmlogByCondition(
			BaomiOperateLog baomiOperateLog, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (baomiOperateLog == null) {
			baomiOperateLog = new BaomiOperateLog();
		}
		String hql = totalDao.criteriaQueries(baomiOperateLog, null, null);
		hql += " order by operateTime desc";
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public void mibuBaomi() {
		// TODO Auto-generated method stub
		// List<SystemFile> list
		// =totalDao.query("from SystemFile where baomi='保密'");
		// for (SystemFile systemFile:list ){
		// if(systemFile!=null){
		// BaomiOperateLog log = new BaomiOperateLog();
		// log.setOperateType("查看");//操作类型
		// log.setOperateObject("文件");//操作对象
		// log.setOperateRemark("文件类型:"+systemFile.getFileType()+",文件名称："+systemFile.getFileName()+"的原文件资料");//
		// log.setOperateTime(systemFile.getUploadDate());//
		// log.setOperateUserId(9486);
		// log.setOperateUsername("姚锦汉");//
		// log.setOperateCode("029808");//
		// log.setOperateDept("研发部");//
		// totalDao.save(log);
		// }
		// }
	}
}
