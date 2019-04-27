package com.task.ServerImpl.sop;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.SmsService;
import com.task.Server.sop.OSApplyServer;
import com.task.entity.InsuranceGold;
import com.task.entity.ModuleFunction;
import com.task.entity.Users;
import com.task.entity.WageStandard;
import com.task.entity.project.QuotedPrice;
import com.task.entity.project.QuotedProcessInfor;
import com.task.entity.sop.OutSourcingApp;
import com.task.util.DateUtil;
import com.task.util.RtxUtil;
import com.task.util.Upload;
import com.task.util.Util;

/***
 * 外委申请填报实现
 * 
 * @author jhh
 * 
 */
public class OSApplyServerImpl implements OSApplyServer {
	private TotalDao totalDao;
	private SmsService smsService;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/***
	 * 
	 * 
	 */
	public List listYuanCailiao() {
		String hql = "  from Zhgongxu where 1=1";
		List list = totalDao.query(hql);
		return list;
	}

	/** 添加外委申请 **/
	@Override
	public boolean saveOSApp(OutSourcingApp osa, File infor,
			String inforFileName, File machine, String machineFileName,
			File abilityLack, String abilityLackFileName, File othersLack,
			String othersLackFileName) {
		Users user = (Users) Util.getLoginUser();
		// 上传外委报价信息
		if (infor != null && inforFileName != null) {
			inforFileName = Util.getDateTime("yyyyMMddHHmmss")
					+ user.getCode()
					+ "infor"
					+ inforFileName.substring(inforFileName.lastIndexOf("."),
							inforFileName.length());
			Upload upload = new Upload();
			String fileMessage = upload.UploadFile(infor, null, inforFileName,
					"/upload/osa/infor",
					"E:/work/HHTask/WebRoot/upload/osa/infor");
			osa.setOspriceFile(inforFileName);
		}
		// 上传设备维修报价信息
		if (machine != null && machineFileName != null) {
			machineFileName = Util.getDateTime("yyyyMMddHHmmss")
					+ user.getCode()
					+ "machine"
					+ machineFileName.substring(machineFileName
							.lastIndexOf("."), machineFileName.length());
			Upload upload = new Upload();
			String fileMessage = upload.UploadFile(machine, null,
					machineFileName, "/upload/osa/machine",
					"E:/work/HHTask/WebRoot/upload/osa/machine");
			osa.setMachineFile(machineFileName);
		}
		// 上传新增设备报价信息
		if (abilityLack != null && abilityLackFileName != null) {
			abilityLackFileName = Util.getDateTime("yyyyMMddHHmmss")
					+ user.getCode()
					+ "abilityLack"
					+ abilityLackFileName.substring(abilityLackFileName
							.lastIndexOf("."), abilityLackFileName.length());
			Upload upload = new Upload();
			String fileMessage = upload.UploadFile(abilityLack, null,
					abilityLackFileName, "/upload/osa/abilityLack",
					"E:/work/HHTask/WebRoot/upload/osa/abilityLack");
			osa.setAddNewMachineFile(abilityLackFileName);
		}
		// 上传其他原因信息
		if (othersLack != null && othersLackFileName != null) {
			othersLackFileName = Util.getDateTime("yyyyMMddHHmmss")
					+ user.getCode()
					+ "othersLack"
					+ othersLackFileName.substring(othersLackFileName
							.lastIndexOf("."), othersLackFileName.length());
			Upload upload = new Upload();
			String fileMessage = upload.UploadFile(othersLack, null,
					othersLackFileName, "/upload/osa/othersLack",
					"E:/work/HHTask/WebRoot/upload/osa/othersLack");
			osa.setOthersLackFile(othersLackFileName);
		}

		// 外委申请单编号（NO+部门编码+yyyy+“001”）
		String deptNum = user.getPassword().getDeptNumber();
		String no = "OSA" + deptNum + Util.getDateTime("yyyy");
		String hqlmaxNum = "select max(osaNO) from OutSourcingApp where osaNO like'"
				+ no + "%'";
		List li = totalDao.find(hqlmaxNum);
		String maxNum = no + "001";
		if (null != li && li.size() > 0 && null != li.get(0)) {
			String maxdata = (String) li.get(0);
			maxNum = no + haoAddOne(maxdata.substring(maxdata.length() - 3));
		}
		osa.setOsaNO(maxNum);
		osa.setCode(user.getCode());
		osa.setUsername(user.getName());
		osa.setOsaSubmitTime(totalDao.getDateTime(null));
		osa.setStatus("申请");
		osa.setNumber("osa" + deptNum + user.getCode()
				+ Util.getDateTime("yyyyMMddHHmmss"));

		// 计算（外委单件合计）
		osa.setOsOneHeji(osa.getOsOneRengong() + osa.getOsOneMater()
				+ osa.getOsOneOthsers());
		// 计算累计人工成本
		// 自制计算
		// 维修预算
		if (null == osa.getRepairBudget()) {
			osa.setRepairBudget(0f);
		}
		// 新添设备成本
		if (null == osa.getAddMachineCost()) {
			osa.setAddMachineCost(0f);
		}
		// 新增辅助人工
		if (null == osa.getOneAssistWorkerMoney()) {
			osa.setOneAssistWorkerMoney(0f);
		}
		// 新增人工成本
		if (null == osa.getOneWorkerMoney()) {
			osa.setOneWorkerMoney(0f);
		}
		// 计算设备折旧
		osa.setZhejiuCost(osa.getAddMachineCost()
				/ (12 * osa.getRecoryPeriod()));
		// 计算（自制单件合计）=====================================
		Double zzOnePrice = ((osa.getSelfOneRengong() + osa.getOneWorkerMoney()
				+ osa.getOneAssistWorkerMoney() + osa.getAddMachineCost()
				/ (12 * osa.getRecoryPeriod()))
				* osa.getProcardCycle()
				/ (21.75 * osa.getDeliveryCount())
				+ osa.getOsOneMater() + osa.getOsOneOthsers() + osa
				.getRepairBudget()
				/ osa.getDeliveryCount());
		osa.setSelfOneHeji(Float.parseFloat(zzOnePrice.toString()));
		// 计算（单件差价）
		osa.setLotChae((osa.getSelfOneHeji() - osa.getOsOneHeji())
				* osa.getDeliveryCount());
		// 计算盈亏平衡点
		Double maxOS = ((osa.getSelfOneRengong() + osa.getOneWorkerMoney()
				+ osa.getOneAssistWorkerMoney() + osa.getZhejiuCost()) * osa
				.getProcardCycle())
				/ (21.75 * osa.getOsOneRengong())
				+ osa.getRepairBudget()
				/ osa.getOsOneRengong();
		float max = Float.parseFloat(maxOS.toString());
		osa.setAddChengMinBalanceCount(max);
		return totalDao.save(osa);
	}

	/** 查看外委申请 **/
	public Object[] findOSAppList(OutSourcingApp osa, String startDate,
			String endDate, Integer cpage, Integer PageSize, String tag) {
		Users user = (Users) Util.getLoginUser();
		String hql = "from OutSourcingApp where 1=1";
		if (null != osa) {
			if (null != osa.getDept() && !"".equals(osa.getDept())) {
				hql += " and dept='" + osa.getDeliveryCount() + "'";
			}
			if (null != osa.getCustomer() && !"".equals(osa.getCustomer())) {
				hql += " and customer='" + osa.getCustomer() + "'";
			}
			if (null != osa.getMarkID() && !"".equals(osa.getMarkID())) {
				hql += " and markId='" + osa.getMarkID() + "'";
			}
			if (null != osa.getProcessNO() && !"".equals(osa.getProcessNO())) {
				hql += " and processNO='" + osa.getProcessNO() + "'";
			}
			if (null != osa.getIsJiaji() && !"".equals(osa.getIsJiaji())) {
				hql += " and isJiaji='" + osa.getIsJiaji() + "'";
			}
			if (null != osa.getTimeLimit() && !"".equals(osa.getTimeLimit())) {
				hql += " and timeLimit='" + osa.getTimeLimit() + "'";
			}
			if (null != osa.getOsaNO() && !"".equals(osa.getOsaNO())) {
				hql += " and osaNO='" + osa.getOsaNO() + "'";
			}
			if (null != osa.getNumber() && !"".equals(osa.getNumber())) {
				hql += " and number='" + osa.getNumber() + "'";
			}
			if (null != osa.getUsername() && !"".equals(osa.getUsername())) {
				hql += " and username='" + osa.getUsername() + "'";
			}
			if (null != osa.getStatus() && !"".equals(osa.getStatus())) {
				hql += " and status='" + osa.getStatus() + "'";
			}
		}
		if (null != startDate && !"".equals(startDate) && null != endDate
				&& !"".equals(endDate)) {
			hql += " and osaSubmitTime between '" + startDate + "' and '"
					+ endDate + "'";
		}
		if ("self".equals(tag)) {
			hql += " and username='"
					+ user.getName()
					+ "' and procardCycle!=null and osOneRengong!=null and repairCycle!=null";
		}
		// if ("manager".equals(tag)) {
		// hql +=
		// " and procardCycle!=null and osOneRengong!=null and repairCycle!=null";
		// }
		if ("dept5".equals(tag)) {
			// hql +=
			// " and procardCycle!=null and osOneRengong!=null and repairCycle!=null";
		}
		hql += "  order by id desc";
		Object[] osaArr = new Object[3];
		Integer count = totalDao.getCount(hql);
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		List list_need = null;
		if (tag != null && tag.length() > 0) {
			String executeStatus = "";
			if ("dept1".equals(tag)) {
				executeStatus = "";
			} else if ("dept2".equals(tag)) {
				executeStatus = "周期录入";
			} else if ("dept3".equals(tag)) {
				executeStatus = "原因录入";
			} else if ("dept4".equals(tag)) {
				executeStatus = "成本核算录入";
			} else if ("dept5".equals(tag)) {
				executeStatus = "自制新增成本录入";
			}
			String hql_need = "from OutSourcingApp where executeStatus=? ";
			list_need = totalDao.query(hql_need, executeStatus);
		}

		osaArr[0] = count;
		osaArr[1] = list;
		osaArr[2] = list_need;
		return osaArr;
	}

	// 字符串拼接
	public String haoAddOne(String liuShuiHao) {
		Integer intHao = Integer.parseInt(liuShuiHao);
		intHao++;
		String strHao = intHao.toString();
		while (strHao.length() < 3)
			strHao = "0" + strHao;
		return strHao;
	}

	@Override
	public OutSourcingApp getOSAById(Integer id) {
		return (OutSourcingApp) totalDao
				.getObjectById(OutSourcingApp.class, id);
	}

	@Override
	public boolean updateOSAById(OutSourcingApp osa, File infor,
			String inforFileName, File machine, String machineFileName,
			File abilityLack, String abilityLackFileName, File othersLack,
			String othersLackFileName) {
		Users user = (Users) Util.getLoginUser();
		OutSourcingApp osDataBase = (OutSourcingApp) totalDao.getObjectById(
				OutSourcingApp.class, osa.getId());
		// 计算（外委单件合计）
		osa.setOsOneHeji(osa.getOsOneRengong() + osa.getOsOneMater()
				+ osa.getOsOneOthsers());
		// 计算累计人工成本
		// 自制计算
		// 维修预算
		if (null == osa.getRepairBudget()) {
			osa.setRepairBudget(0f);
		}
		// 新添设备成本
		if (null == osa.getAddMachineCost()) {
			osa.setAddMachineCost(0f);
		}
		// 新增辅助人工
		if (null == osa.getOneAssistWorkerMoney()) {
			osa.setOneAssistWorkerMoney(0f);
		}
		// 新增人工成本
		if (null == osa.getOneWorkerMoney()) {
			osa.setOneWorkerMoney(0f);
		}
		// 计算设备折旧
		osa.setZhejiuCost(osa.getAddMachineCost()
				/ (12 * osa.getRecoryPeriod()));
		// 计算（自制单件合计）
		Double zzOnePrice = ((osa.getSelfOneRengong() + osa.getOneWorkerMoney()
				+ osa.getOneAssistWorkerMoney() + osa.getZhejiuCost())
				* osa.getProcardCycle()
				/ (21.75 * osa.getDeliveryCount())
				+ osa.getOsOneMater() + osa.getOsOneOthsers() + osa
				.getRepairBudget()
				/ osa.getDeliveryCount());
		osa.setSelfOneHeji(Float.parseFloat(zzOnePrice.toString()));
		// 计算（单件差价）
		osa.setLotChae((osa.getSelfOneHeji() - osa.getOsOneHeji())
				* osa.getDeliveryCount());
		// 计算盈亏平衡点
		Double maxOS = ((osa.getSelfOneRengong() + osa.getOneWorkerMoney()
				+ osa.getOneAssistWorkerMoney() + osa.getZhejiuCost()) * osa
				.getProcardCycle())
				/ (21.75 * osa.getOsOneRengong())
				+ osa.getRepairBudget()
				/ osa.getOsOneRengong();
		float max = Float.parseFloat(maxOS.toString());
		osa.setAddChengMinBalanceCount(max);
		// 上传基本信息
		if (infor != null && inforFileName != null) {
			inforFileName = Util.getDateTime("yyyyMMddHHmmss")
					+ user.getCode()
					+ "infor"
					+ inforFileName.substring(inforFileName.lastIndexOf("."),
							inforFileName.length());
			Upload upload = new Upload();
			String fileMessage = upload.UploadFile(infor, null, inforFileName,
					"/upload/osa/infor",
					"E:/work/HHTask/WebRoot/upload/osa/infor");
			osa.setOspriceFile(inforFileName);
		} else {
			osa.setInforFile(osDataBase.getOspriceFile());
		}
		// 上传维修报价信息
		if (machine != null && machineFileName != null) {
			machineFileName = Util.getDateTime("yyyyMMddHHmmss")
					+ user.getCode()
					+ "machine"
					+ inforFileName.substring(machineFileName.lastIndexOf("."),
							machineFileName.length());
			Upload upload = new Upload();
			String fileMessage = upload.UploadFile(infor, null,
					machineFileName, "/upload/osa/machine",
					"E:/work/HHTask/WebRoot/upload/osa/machine");
			osa.setMachineFile(machineFileName);
		} else {
			osa.setMachineFile(osDataBase.getMachineFile());
		}
		// 上传新增设备报价信息
		if (abilityLack != null && abilityLackFileName != null) {
			abilityLackFileName = Util.getDateTime("yyyyMMddHHmmss")
					+ user.getCode()
					+ "abilityLack"
					+ abilityLackFileName.substring(abilityLackFileName
							.lastIndexOf("."), abilityLackFileName.length());
			Upload upload = new Upload();
			String fileMessage = upload.UploadFile(abilityLack, null,
					abilityLackFileName, "/upload/osa/abilityLack",
					"E:/work/HHTask/WebRoot/upload/osa/abilityLack");
			osa.setAddNewMachineFile(abilityLackFileName);
		} else {
			osa.setAddNewMachineFile(osDataBase.getAddNewMachineFile());
		}
		// 上传其他原因信息
		if (othersLack != null && othersLackFileName != null) {
			othersLackFileName = Util.getDateTime("yyyyMMddHHmmss")
					+ user.getCode()
					+ "othersLack"
					+ inforFileName.substring(othersLackFileName
							.lastIndexOf("."), othersLackFileName.length());
			Upload upload = new Upload();
			String fileMessage = upload.UploadFile(othersLack, null,
					inforFileName, "/upload/osa/othersLack",
					"E:/work/HHTask/WebRoot/upload/osa/othersLack");
			osa.setOthersLackFile(othersLackFileName);
		} else {
			osa.setOthersLackFile(osDataBase.getOthersLackFile());
		}
		BeanUtils.copyProperties(osa, osDataBase, new String[] { "osaNO",
				"code", "username", "number", "osaSubmitTime", "status" });
		return totalDao.update(osDataBase);
	}

	@Override
	public String findSelectList(String tag) {
		String message = "";
		if (null != tag && !"".equals(tag)) {
			String hql = "select distinct(" + tag + ") from OutSourcingApp";
			List<String> list = totalDao.query(hql);
			for (String d : list) {
				message += d.toString() + "|";
			}
		}
		return message;
	}

	/***
	 * 获得所有订单号
	 * 
	 * @return
	 */
	@Override
	public Object[] findOrderNum() {
		String hql = "select orderNum from OrderManager";
		List list = totalDao.find(hql);
		return list.toArray();
	}

	/***
	 * 获得所有报警单号
	 * 
	 * @return
	 */
	@Override
	public Object[] findMaintenance() {
		String hql = "select barcode from Maintenance  where status not in ('修复待验证','正常')";
		List list = totalDao.find(hql);
		return list.toArray();
	}

	/***
	 * 计算所需工资总额
	 */
	@Override
	public Object[] getWage(String selfCode) {
		if (selfCode != null) {
			selfCode = selfCode.replaceAll("，", ",");
			String[] codes = selfCode.split(",");
			Float allWage = 0F;
			String userName = "";
			int renshu = 0;
			Users loginUser = Util.getLoginUser();
			String message = "";
			String pageCodes = "";
			for (int i = 0; i < codes.length; i++) {
				String code = codes[i];
				if (code == null || code.length() <= 0) {
					continue;
				}
				String hql = "from WageStandard where code=? and standardStatus='默认' and processStatus='同意'";
				WageStandard wageStandard = (WageStandard) totalDao
						.getObjectByCondition(hql, code);
				if (wageStandard != null) {
					// if (!wageStandard.getDept().equals(loginUser.getDept()))
					// {
					// message += "工号:" + code + "不是本部门成员,请勿添加!\n";
					// continue;
					// }
					String dateTime = Util.getDateTime("yyyy-MM-dd");
					String hql2 = "from InsuranceGold where localOrField=? and cityOrCountryside=? and personClass=? and validityStartDate<=? and validityEndDate>=?";
					InsuranceGold insuranceGold = (InsuranceGold) totalDao
							.getObjectByCondition(hql2, wageStandard
									.getLocalOrField(), wageStandard
									.getCityOrCountryside(), wageStandard
									.getPersonClass(), dateTime, dateTime);
					renshu++;
					userName += wageStandard.getUserName() + ",";
					allWage += wageStandard.getGangweigongzi()
							+ wageStandard.getSsBase()
							* (insuranceGold.getQYoldageInsurance()
									+ insuranceGold.getQYmedicalInsurance()
									+ insuranceGold
											.getQYunemploymentInsurance()
									+ insuranceGold.getQYinjuryInsurance() + insuranceGold
									.getQYmaternityInsurance()) / 100
							+ wageStandard.getGjjBase()
							* insuranceGold.getQYhousingFund() / 100;

					pageCodes += code + ",";
				} else {
					message += "工号:" + code + "工号信息不存在,请勿添加!\n";
				}
			}
			return new Object[] { userName, allWage, renshu, pageCodes, message };

		}
		return null;
	}

	/** 添加产品基本信息 **/
	@Override
	public boolean saveOSApp1(OutSourcingApp osa) {
		Users user = (Users) Util.getLoginUser();
		// 外委申请单编号（NO+部门编码+yyyy+“001”）
		String deptNum = user.getPassword().getDeptNumber();
		String no = "OSA" + deptNum + Util.getDateTime("yyyy");
		String hqlmaxNum = "select max(osaNO) from OutSourcingApp where osaNO like'"
				+ no + "%'";
		List li = totalDao.find(hqlmaxNum);
		String maxNum = no + "001";
		if (null != li && li.size() > 0 && null != li.get(0)) {
			String maxdata = (String) li.get(0);
			maxNum = no + haoAddOne(maxdata.substring(maxdata.length() - 3));
		}
		osa.setOsaNO(maxNum);
		osa.setCode(user.getCode());
		osa.setUsername(user.getName());
		osa.setOsaSubmitTime(totalDao.getDateTime(null));
		osa.setStatus("申请");
		osa.setNumber("osa" + deptNum + user.getCode()
				+ Util.getDateTime("yyyyMMddHHmmss"));
		osa.setExecuteStatus("产品录入");

		String hql = "from ModuleFunction where functionName='产品周期信息'";
		ModuleFunction moduleFunction = (ModuleFunction) this.totalDao
				.getObjectByCondition(hql);
		String sql = "select ta_userid  from ta_usersMF where ta_mfId=?";
		List list = this.totalDao.createQuerySelect(null, sql, moduleFunction
				.getId());
		List<String> codes = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			Users users = (Users) this.totalDao.getObjectByCondition(
					"from Users where id=?", list.get(i));
			codes.add(users.getCode());
		}
		if (codes != null && codes.size() > 0) {
			RtxUtil.sendNotify(codes, "有未录入的产品周期信息，请前往录入!", "系统消息", "0", "0");
		}
		return totalDao.save(osa);
	}

	/***
	 * 根据ID修改产品周期信息
	 */
	@Override
	public void updateOSAById(OutSourcingApp osa) {

		// OutSourcingApp osa1 = (OutSourcingApp) totalDao.getObjectById(
		// OutSourcingApp.class, osa.getId());
		String hql1 = "from OutSourcingApp  where markID=? and username=?";
		List list1 = totalDao.query(hql1, osa.getMarkID(), osa.getUsername());
		for (int i = 0; i < list1.size(); i++) {
			OutSourcingApp osa1 = (OutSourcingApp) list1.get(i);
			if (osa.getDeliveryDate() != null) {
				osa1.setDeliveryDate(osa.getDeliveryDate());
			}
			osa1.setOrderId(osa.getOrderId());
			osa1.setYuceCount(osa.getYuceCount());
			osa1.setProcardCycle(osa.getProcardCycle());
			osa1.setProductCysle(osa.getProductCysle());
			osa1.setExecuteStatus("原因录入");
			this.totalDao.update(osa1);
		}
		String hql = "from ModuleFunction where functionName='申报原因'";
		ModuleFunction moduleFunction = (ModuleFunction) this.totalDao
				.getObjectByCondition(hql);
		String sql = "select ta_userid  from ta_usersMF where ta_mfId=?";
		List list = this.totalDao.createQuerySelect(null, sql, moduleFunction
				.getId());
		List<String> codes = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			Users users = (Users) this.totalDao.getObjectByCondition(
					"from Users where id=?", list.get(i));
			codes.add(users.getCode());
		}
		if (codes != null && codes.size() > 0) {
			RtxUtil.sendNotify(codes, "有未录入的申报原因信息，请前往录入!", "系统消息", "0", "0");
		}

	}

	/***
	 * 根据ID修改申报原因
	 */
	@Override
	public void updateOSAById2(OutSourcingApp osa, File machine,
			String machineFileName, File othersLack, String othersLackFileName) {
		// TODO Auto-generated method stub
		Users user = (Users) Util.getLoginUser();
		OutSourcingApp osa1 = (OutSourcingApp) totalDao.getObjectById(
				OutSourcingApp.class, osa.getId());
		osa1.setTimeLimit(osa.getTimeLimit());
		osa1.setAlertNum(osa.getAlertNum());
		osa1.setRepairCycle(osa.getRepairCycle());
		osa1.setMachineFail(osa.getMachineFail());
		if (null == osa.getRepairBudget()) {
			osa1.setRepairBudget(0f);
		} else {
			osa1.setRepairBudget(osa.getRepairBudget());
		}
		// 上传设备维修报价信息
		if (machine != null && machineFileName != null) {
			machineFileName = Util.getDateTime("yyyyMMddHHmmss")
					+ user.getCode()
					+ "machine"
					+ machineFileName.substring(machineFileName
							.lastIndexOf("."), machineFileName.length());
			Upload upload = new Upload();
			String fileMessage = upload.UploadFile(machine, null,
					machineFileName, "/upload/osa/machine",
					"E:/work/HHTask/WebRoot/upload/osa/machine");
			osa1.setMachineFile(machineFileName);
		}

		osa1.setAbilityLack(osa.getAbilityLack());

		osa1.setOthersLack(osa.getOthersLack());
		// 上传其他原因信息
		if (othersLack != null && othersLackFileName != null) {
			othersLackFileName = Util.getDateTime("yyyyMMddHHmmss")
					+ user.getCode()
					+ "othersLack"
					+ othersLackFileName.substring(othersLackFileName
							.lastIndexOf("."), othersLackFileName.length());
			Upload upload = new Upload();
			String fileMessage = upload.UploadFile(othersLack, null,
					othersLackFileName, "/upload/osa/othersLack",
					"E:/work/HHTask/WebRoot/upload/osa/othersLack");
			osa1.setOthersLackFile(othersLackFileName);
		}
		if (osa1.getAbilityLack() != null
				&& (osa1.getAbilityLack().equals("工艺限制")||osa.getAbilityLack().equals("表面处理")||osa.getAbilityLack().equals("热处理") || osa1
						.getAbilityLack().equals("设备限制"))) {
			osa1.setExecuteStatus("评审完成");
			boolean b = this.totalDao.update(osa1);
			String markId = osa1.getMarkID();// 得到零件号
			String processNO = osa1.getProcessNO();// 零件号
			if (markId != null) {
				// 所有含有该markId的外购外委评审的bom的rootId

				String rootIdSql = "select rootId from QuotedPrice where rootId in(select rootId from QuotedPrice where markId='"
						+ markId + "') and rootId=id and status='外购外委评审'";
				List<Integer> rootIdList = totalDao.query(rootIdSql);
				if (rootIdList.size() > 0) {// 如果bom下不存在没有完成评审的就将总成的状态改成评审完成
					for (Integer rootId : rootIdList) {
						List<Integer> osaIdList = totalDao
								.query("select id from OutSourcingApp where (status!='评审完成' or status is null) and markID in(select markId from QuotedPrice where rootId="
										+ rootId + ")");
						if (osaIdList.size() > 0) {
							QuotedPrice qp = (QuotedPrice) totalDao
									.getObjectById(QuotedPrice.class, rootId);
							qp.setStatus("集合报价");
							b = b & totalDao.update(qp);
						}
					}
				}

			}
		} else {
			String hql = "from ModuleFunction where functionName='成本核算'";
			ModuleFunction moduleFunction = (ModuleFunction) this.totalDao
					.getObjectByCondition(hql);
			String sql = "select ta_userid  from ta_usersMF where ta_mfId=?";
			List list = this.totalDao.createQuerySelect(null, sql,
					moduleFunction.getId());
			List<String> codes = new ArrayList<String>();
			for (int i = 0; i < list.size(); i++) {
				Users users = (Users) this.totalDao.getObjectByCondition(
						"from Users where id=?", list.get(i));
				codes.add(users.getCode());
			}
			if (codes != null && codes.size() > 0) {
				RtxUtil.sendNotify(codes, "有未录入的成本核算信息，请前往录入!", "系统消息", "0",
						"0");
			}
			this.totalDao.update(osa1);
		}

	}

	/*
	 * 
	 * 成本核算(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.sop.OSApplyServer#updateOSAById3(com.task.entity.sop.
	 * OutSourcingApp, java.io.File, java.lang.String)
	 */
	@Override
	public void updateOSAById3(OutSourcingApp osa, File infor,
			String inforFileName) {
		Users user = (Users) Util.getLoginUser();
		OutSourcingApp osa1 = (OutSourcingApp) totalDao.getObjectById(
				OutSourcingApp.class, osa.getId());
		osa1.setTimeLimit(osa.getTimeLimit());
		osa1.setShuliang(osa.getShuliang());
		if (osa.getPaihao() != null) {
			osa1.setPaihao(osa.getPaihao());
		}
		if (osa.getGuige() != null) {
			osa1.setGuige(osa.getGuige());
		}
		if (osa.getJiage() != null) {
			osa1.setJiage(osa.getJiage());
		}
		osa1.setDanwei(osa.getDanwei());
		osa1.setOsOneRengong(osa.getOsOneRengong());
		osa1.setOsOneMater(osa.getOsOneMater());
		osa1.setOsOneOthsers(osa.getOsOneOthsers());
		// osa1.setSelfCode(osa.getSelfCode());
		// osa1.setSelfRenshu(osa.getSelfRenshu());
		// osa1.setSelfOneRengong(osa.getSelfOneRengong());
		// 计算（外委单件合计）
		osa1.setOsOneHeji(osa.getOsOneRengong() + osa.getOsOneMater()
				+ osa.getOsOneOthsers());
		// 上传外委报价信息
		if (infor != null && inforFileName != null) {
			inforFileName = Util.getDateTime("yyyyMMddHHmmss")
					+ user.getCode()
					+ "infor"
					+ inforFileName.substring(inforFileName.lastIndexOf("."),
							inforFileName.length());
			Upload upload = new Upload();
			String fileMessage = upload.UploadFile(infor, null, inforFileName,
					"/upload/osa/infor",
					"E:/work/HHTask/WebRoot/upload/osa/infor");
			osa1.setOspriceFile(inforFileName);
		}
		osa1.setExecuteStatus("自制新增成本录入");
		String hql = "from ModuleFunction where functionName='自制新增成本'";
		ModuleFunction moduleFunction = (ModuleFunction) this.totalDao
				.getObjectByCondition(hql);
		String sql = "select ta_userid  from ta_usersMF where ta_mfId=?";
		List list = this.totalDao.createQuerySelect(null, sql, moduleFunction
				.getId());
		List<String> codes = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			Users users = (Users) this.totalDao.getObjectByCondition(
					"from Users where id=?", list.get(i));
			codes.add(users.getCode());
		}
		if (codes != null && codes.size() > 0) {
			RtxUtil.sendNotify(codes, "有未录入的自制新增成本信息，请前往录入!", "系统消息", "0", "0");
		}
		this.totalDao.update(osa1);
	}

	/*
	 * 根据ID修改自制新增成本(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.sop.OSApplyServer#updateOSAById4(com.task.entity.sop.
	 * OutSourcingApp, java.io.File, java.lang.String)
	 */
	@Override
	public void updateOSAById4(OutSourcingApp osa, File abilityLack,
			String abilityLackFileName) {
		String markId = null;// 零件号
		String processNO = null;// 工序号
		// Integer qpId=null;//bom的零件id
		// Integer qpInfoId=null;//bom的工序id
		boolean bool = false;// 是否符合外购外委
		Users user = (Users) Util.getLoginUser();
		OutSourcingApp osa1 = (OutSourcingApp) totalDao.getObjectById(
				OutSourcingApp.class, osa.getId());
		osa1.setTimeLimit(osa.getTimeLimit());
		osa1.setAddWorker(osa.getAddWorker());
		osa1.setRecoryPeriod(osa.getRecoryPeriod());
		osa1.setAddAssistWorker(osa.getAddAssistWorker());
		if (osa.getSelfCode() != null || "".equals(osa.getSelfCode())) {
			osa1.setSelfCode(osa.getSelfCode());
		}
		osa1.setSelfRenshu(osa.getSelfRenshu());
		osa1.setSelfOneRengong(osa.getSelfOneRengong());
		// 上传新增设备报价信息
		if (abilityLack != null && abilityLackFileName != null) {
			abilityLackFileName = Util.getDateTime("yyyyMMddHHmmss")
					+ user.getCode()
					+ "abilityLack"
					+ abilityLackFileName.substring(abilityLackFileName
							.lastIndexOf("."), abilityLackFileName.length());
			Upload upload = new Upload();
			String fileMessage = upload.UploadFile(abilityLack, null,
					abilityLackFileName, "/upload/osa/abilityLack",
					"E:/work/HHTask/WebRoot/upload/osa/abilityLack");
			osa1.setAddNewMachineFile(abilityLackFileName);
		}

		if (null == osa.getAddMachineCost()) {
			osa1.setAddMachineCost(0f);
		} else {
			osa1.setAddMachineCost(osa.getAddMachineCost());
		}
		// 新增辅助人工
		if (null == osa.getOneAssistWorkerMoney()) {
			osa1.setOneAssistWorkerMoney(0f);
		} else {
			osa1.setOneAssistWorkerMoney(osa.getOneAssistWorkerMoney());
		}
		// 新增人工成本
		if (null == osa.getOneWorkerMoney()) {
			osa1.setOneWorkerMoney(0f);
		} else {
			osa1.setOneWorkerMoney(osa.getOneWorkerMoney());
		}
		// 计算设备折旧
		float ZhejiuCost1 = osa.getAddMachineCost()
				/ (12 * osa.getRecoryPeriod());// 把折旧的值存放在变量中
		osa1.setZhejiuCost(osa.getAddMachineCost()
				/ (12 * osa.getRecoryPeriod()));
		// 计算（自制单件合计）
		Double zzOnePrice = ((osa1.getSelfOneRengong()
				+ osa.getOneWorkerMoney() + osa.getOneAssistWorkerMoney() + osa
				.getAddMachineCost()
				/ (12 * osa.getRecoryPeriod()))
				* osa1.getProcardCycle()
				/ (21.75 * osa1.getDeliveryCount())
				+ osa1.getOsOneMater() + osa1.getOsOneOthsers() + osa1
				.getRepairBudget()
				/ osa1.getDeliveryCount());
		osa1.setSelfOneHeji(Float.parseFloat(zzOnePrice.toString()));
		// 计算（单件差价）
		osa1.setLotChae((Float.parseFloat(zzOnePrice.toString()) - osa1
				.getOsOneHeji())
				* osa1.getDeliveryCount());
		// 计算盈亏平衡点
		Double maxOS = ((osa1.getSelfOneRengong() + osa.getOneWorkerMoney()
				+ osa.getOneAssistWorkerMoney() + ZhejiuCost1) * osa1
				.getProcardCycle())
				/ (21.75 * osa1.getOsOneRengong())
				+ osa1.getRepairBudget()
				/ osa1.getOsOneRengong();
		float max = Float.parseFloat(maxOS.toString());
		osa1.setAddChengMinBalanceCount(max);
		markId = osa1.getMarkID();// 得到零件号
		processNO = osa1.getProcessNO();// 零件号
		// qpId=osa1.getQpId();//得到bom的零件Id
		// qpInfoId=osa1.getQpinfoId();//得到bom的工序id
		osa1.setRootId(null);
		if (osa1.getAddChengMinBalanceCount() != null
				&& osa1.getDeliveryCount() != null
				&& (osa1.getDeliveryCount() - osa1.getAddChengMinBalanceCount()) > 0) {
			bool = true;
		} else if (osa1.getAddChengMinBalanceCount() == null
				&& osa1.getDeliveryCount() != null) {
			bool = true;
		}
		boolean bool2 = false;
		if (osa1.getAbilityLack() != null
				&& (osa1.getAbilityLack().equals("工艺限制")||osa.getAbilityLack().equals("表面处理")||osa.getAbilityLack().equals("热处理") || osa1
						.getAbilityLack().equals("设备限制"))) {
			bool2 = true;// 产能不足的原因是工艺限制或者设备限制
		}
		osa1.setExecuteStatus("评审完成");
		boolean b = this.totalDao.update(osa1);

		if (b && markId != null) {
			// 所有含有该markId的外购外委评审的bom的rootId
			String rootIdSql = "select rootId from QuotedPrice where rootId in(select rootId from QuotedPrice where markId='"
					+ markId + "') and rootId=id and status='外购外委评审'";
			List<Integer> rootIdList = totalDao.query(rootIdSql);
			if (rootIdList.size() > 0) {// 如果bom下不存在没有完成评审的就将总成的状态改成评审完成
				for (Integer rootId : rootIdList) {
					List<Integer> osaIdList = totalDao
							.query("select id from OutSourcingApp where (status!='评审完成' or status is null) and markID in(select markId from QuotedPrice where rootId="
									+ rootId + ")");
					if (osaIdList.size() > 0) {
						QuotedPrice qp = (QuotedPrice) totalDao.getObjectById(
								QuotedPrice.class, rootId);
						qp.setStatus("集合报价");
						b = b & totalDao.update(qp);
					}
					if (bool&&!bool2) {// 如果外委交付数超过外委盈亏平衡数量并且产能不足的原因不是工艺限制或者设备限制，则外委外购变为自制
						List<QuotedPrice> qpList = (List<QuotedPrice>) totalDao
								.query("from QuotedPrice where rootId ="
										+ rootId + " and markId='" + markId
										+ "'");
						if (qpList.size() > 0) {
							for (QuotedPrice qp1 : qpList) {
								if (processNO != null && processNO.equals("外购")) {
									qp1.setProcardStyle("自制");
									totalDao.update(qp1);
								} else if (processNO != null
										&& !processNO.equals("外购")) {
									Set<QuotedProcessInfor> qpinfoSet = qp1
											.getQpinfor();
									if (qpinfoSet != null) {
										for (QuotedProcessInfor qpinfo1 : qpinfoSet) {
											if (qpinfo1.getProcessNO() != null
													&& qpinfo1.getProcessNO()
															.equals(processNO)) {
												qpinfo1.setProductStyle("自制");
												totalDao.update(qpinfo1);
											}
										}
									}
								}

							}

						}

					}
				}
			}

		}
	}

	/*
	 * 
	 * 根据ID修改产品基本信息 (non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.sop.OSApplyServer#updateOSAById0(com.task.entity.sop.
	 * OutSourcingApp)
	 */
	@Override
	public void updateOSAById0(OutSourcingApp osa) {
		Users user = (Users) Util.getLoginUser();
		// 外委申请单编号（NO+部门编码+yyyy+“001”）
		OutSourcingApp osa1 = (OutSourcingApp) totalDao.getObjectById(
				OutSourcingApp.class, osa.getId());
		osa1.setCustomer(osa.getCustomer());
		osa1.setIsJiaji(osa.getIsJiaji());
		osa1.setMarkID(osa.getMarkID());
		osa1.setProcessNO(osa.getProcessNO());
		osa1.setDeliveryCount(osa.getDeliveryCount());
		osa1.setExecuteStatus("周期录入");
		this.totalDao.update(osa1);

	}

	@Override
	public boolean saveBomOSApp(Integer rootId) {
		// TODO Auto-generated method stub
		List<QuotedPrice> qpList = totalDao
				.query("from QuotedPrice where rootId =" + rootId);
		List<String> osaMarkIds = new ArrayList<String>();// 存储所有markId
		List<String> markIdProcessNos = new ArrayList<String>();// 存储所有markId+ProcessNO字符串
		List<OutSourcingApp> osaList = totalDao.query("from OutSourcingApp");
		if (osaList.size() > 0) {
			for (OutSourcingApp osa : osaList) {
				String markIdProcessNo = null;
				if (osa.getMarkID() != null) {
					markIdProcessNo = osa.getMarkID();
					osaMarkIds.add(osa.getMarkID());
				}
				if (osa.getProcessNO() != null) {
					markIdProcessNo += osa.getProcessNO();
				}
				markIdProcessNos.add(markIdProcessNo);
			}
		}
		if (qpList.size() > 0) {
			Users loginUser = Util.getLoginUser();
			if (loginUser == null) {
				return false;
			}
			boolean b = true;
			int count = 0;
			QuotedPrice zc = null;
			for (QuotedPrice qp : qpList) {
				if (qp.getProcardStyle() != null
						&& !qp.getProcardStyle().equals("外购")) {
					if (qp.getProcardStyle().equals("总成")) {
						zc = qp;
					}
					Set<QuotedProcessInfor> qpinfoSet = qp.getQpinfor();
					if (qpinfoSet.size() > 0) {
						for (QuotedProcessInfor qpInfo : qpinfoSet) {
							if (qpInfo.getProductStyle() != null
									&& qpInfo.getProductStyle().equals("外委")) {
								String markprocess = qp.getMarkId()
										+ qpInfo.getProcessNO();
								if (!markIdProcessNos.contains(markprocess)) {// 同件号下同工序号未被评审过
									OutSourcingApp osApp = new OutSourcingApp();
									osApp.setDept(loginUser.getDept());
									osApp.setIsJiaji("否");
									osApp.setMarkID(qp.getMarkId());
									osApp.setProcessNO(qpInfo.getProcessNO()
											+ "");
									osApp.setProcessName(qpInfo
											.getProcessName());
									osApp.setDeliveryCount(qp.getFilnalCount());
									osApp.setRootId(rootId);
									osApp.setQpinfoId(qpInfo.getId());
									osApp.setDeliveryDate(DateUtil.formatDate(
											new Date(), "yyyy-MM-dd"));
									Users user = (Users) Util.getLoginUser();
									// 外委申请单编号（NO+部门编码+yyyy+“001”）
									String deptNum = user.getPassword()
											.getDeptNumber();
									String no = "OSA" + deptNum
											+ Util.getDateTime("yyyy");
									String hqlmaxNum = "select max(osaNO) from OutSourcingApp where osaNO like'"
											+ no + "%'";
									List li = totalDao.find(hqlmaxNum);
									String maxNum = no + "001";
									if (null != li && li.size() > 0
											&& null != li.get(0)) {
										String maxdata = (String) li.get(0);
										maxNum = no
												+ haoAddOne(maxdata
														.substring(maxdata
																.length() - 3));
									}
									osApp.setOsaNO(maxNum);
									osApp.setCode(user.getCode());
									osApp.setUsername(user.getName());
									osApp.setOsaSubmitTime(totalDao
											.getDateTime(null));
									osApp.setStatus("申请");
									osApp
											.setNumber("osa"
													+ deptNum
													+ user.getCode()
													+ Util
															.getDateTime("yyyyMMddHHmmss"));
									osApp.setExecuteStatus("周期录入");
									b = b & totalDao.save(osApp);
									if (b) {
										count++;
									}
								} else {// 同件号下同工序号已被评审过
									List<OutSourcingApp> osaList2 = totalDao
											.query("from OutSourcingApp where markId='"
													+ qp.getMarkId()
													+ "' and processNO='"
													+ qpInfo.getProcessNO()
													+ "'");
									if (osaList2.size() > 0) {
										OutSourcingApp osa = osaList2.get(0);
										if ((osa.getStatus()==null||!osa.getStatus().equals("评审完成"))||(osa.getAbilityLack() != null
												&& (osa.getAbilityLack().equals("工艺限制") ||osa.getAbilityLack().equals("表面处理")||osa.getAbilityLack().equals("热处理") || osa
														.getAbilityLack().equals("设备限制")))) {//没有评审完成或者产能不足的原因是上述之一
//											qp.setProcardStyle("外购");
//											b = b & totalDao.update(qp);
										}else{
											boolean bool = false;
											if (osa.getAddChengMinBalanceCount() != null
													&& osa.getDeliveryCount() != null
													&& (osa.getDeliveryCount() - osa
															.getAddChengMinBalanceCount()) > 0) {
												bool = true;
											} else if (osa
													.getAddChengMinBalanceCount() == null
													&& osa.getDeliveryCount() != null) {
												bool = true;
											}
											if (bool) {// 如果外委交付数超过外委盈亏平衡数量，则外委外购变为自制
												qpInfo.setProductStyle("自制");
												b = b & totalDao.update(qpInfo);
											}
										}
									}
								}
							}
						}
					}
				} else if (qp.getProcardStyle() != null
						&& qp.getProcardStyle().equals("外购")) {
//					System.out.println(qp.getMarkId());
					if(qp.getWaigouPrice()==null){//自动注入外购价格
						String toDay = Util.getDateTime("yyyy-MM-dd");
						Object wgPrice =totalDao.getObjectByCondition("select hsPrice from Price where partNumber = ? and pricePeriodStart<=? and pricePeriodEnd>=? order by hsPrice", qp.getMarkId(),toDay,toDay);
						if(wgPrice!=null){
							qp.setWaigouPrice((float)Double.parseDouble(wgPrice.toString()));
							totalDao.update(qp);
						}
					}
					if (qp.getMarkId() != null
							&& !osaMarkIds.contains(qp.getMarkId())) {
						OutSourcingApp osApp = new OutSourcingApp();
						osApp.setDept(loginUser.getDept());
						osApp.setIsJiaji("否");
						osApp.setMarkID(qp.getMarkId());
						osApp.setProcessNO("外购");
						osApp.setDeliveryCount(qp.getFilnalCount());
						osApp.setQpId(qp.getId());
						osApp.setRootId(rootId);
						osApp.setExecuteStatus("周期录入");
						osApp.setStatus("申请");
						osApp.setDeliveryDate(DateUtil.formatDate(new Date(),
								"yyyy-MM-dd"));
						if (this.saveOSApp1(osApp)) {
							b = b & true;
							count++;
						}
					} else {// 同件号已被评审过
						List<OutSourcingApp> osaList3 = totalDao
								.query("from OutSourcingApp where markId='"
										+ qp.getMarkId() + "'");
						if (osaList3.size() > 0) {
							OutSourcingApp osa = osaList3.get(0);
							if ((osa.getStatus()==null||!osa.getStatus().equals("评审完成"))||(osa.getAbilityLack() != null
									&& (osa.getAbilityLack().equals("工艺限制")||osa.getAbilityLack().equals("表面处理")||osa.getAbilityLack().equals("热处理") || osa
											.getAbilityLack().equals("设备限制")))) {//没有评审完成或者产能不足的原因是上述之一
//								qp.setProcardStyle("外购");
//								b = b & totalDao.update(qp);
							} else {
								boolean bool = false;
								if (osa.getAddChengMinBalanceCount() != null
										&& osa.getDeliveryCount() != null
										&& (osa.getDeliveryCount() - osa
												.getAddChengMinBalanceCount()) > 0) {
									bool = true;
								} else if (osa.getAddChengMinBalanceCount() == null
										&& osa.getDeliveryCount() != null) {
									bool = true;
								}
								if (bool) {// 如果外委交付数超过外委盈亏平衡数量，则外委外购变为自制
									qp.setProcardStyle("自制");
									b = b & totalDao.update(qp);
								}
							}

						}
					}
				}
			}
			if (b && count > 0) {
				zc.setStatus("外购外委评审");
				b = b & totalDao.update(zc);
			} else if (b && count == 0) {
				zc.setStatus("集合报价");
				b = b & totalDao.update(zc);
			}
			return b;
		}
		return false;
	}

	public SmsService getSmsService() {
		return smsService;
	}

	public void setSmsService(SmsService smsService) {
		this.smsService = smsService;
	}

	@Override
	public String updateUnOsAppFromBom() { 
		// TODO Auto-generated method stub
		Users loginUser=Util.getLoginUser();
		int i=0;
		int j=0;
		//外委工序
		List<QuotedProcessInfor> qpProcessList=totalDao.query("from QuotedProcessInfor where productStyle ='外委' " 
				+"and quotedPrice.rootId in(select id from QuotedPrice where status is not null and status not in('初始','BOM录入')) "
				+"and quotedPrice.markId+convert(varchar(100),processNO) not in(select (markID+processNO) from OutSourcingApp)"); 
		if(qpProcessList.size()>0){
			List<String> markIdProcessNos = new ArrayList<String>();
			for(QuotedProcessInfor process:qpProcessList){
				QuotedPrice qp=process.getQuotedPrice();
				if(qp==null){
					continue;
				}
				String  markIdProcessNo =qp.getMarkId()+process.getProcessNO();
				if(!markIdProcessNos.contains(markIdProcessNo)){
					markIdProcessNos.add(qp.getMarkId()+process.getProcessNO());
					OutSourcingApp osApp = new OutSourcingApp();
					osApp.setDept(loginUser.getDept());
					osApp.setIsJiaji("否");
					osApp.setMarkID(qp.getMarkId());
					osApp.setProcessNO(process.getProcessNO()
							+ "");
					osApp.setProcessName(process
							.getProcessName());
					osApp.setDeliveryCount(qp.getFilnalCount());
					osApp.setRootId(qp.getRootId());
					osApp.setQpinfoId(process.getId());
					osApp.setDeliveryDate(DateUtil.formatDate(
							new Date(), "yyyy-MM-dd"));
					Users user = (Users) Util.getLoginUser();
					// 外委申请单编号（NO+部门编码+yyyy+“001”）
					String deptNum = user.getPassword()
					.getDeptNumber();
					String no = "OSA" + deptNum
					+ Util.getDateTime("yyyy");
					String hqlmaxNum = "select max(osaNO) from OutSourcingApp where osaNO like'"
						+ no + "%'";
					List li = totalDao.find(hqlmaxNum);
					String maxNum = no + "001";
					if (null != li && li.size() > 0
							&& null != li.get(0)) {
						String maxdata = (String) li.get(0);
						maxNum = no
						+ haoAddOne(maxdata
								.substring(maxdata
										.length() - 3));
					}
					osApp.setOsaNO(maxNum);
					osApp.setCode(user.getCode());
					osApp.setUsername(user.getName());
					osApp.setOsaSubmitTime(totalDao
							.getDateTime(null));
					osApp.setStatus("申请");
					osApp
					.setNumber("osa"
							+ deptNum
							+ user.getCode()
							+ Util
							.getDateTime("yyyyMMddHHmmss"));
					osApp.setExecuteStatus("周期录入");
					totalDao.save(osApp);
					j++;
				}
			}
		}
		//外购件
		List<QuotedPrice> qpList=totalDao.query("from QuotedPrice where procardStyle='外购' and  rootId in(select id from QuotedPrice where status is not null and status not in('初始','BOM录入')) and markId not in(select markID from OutSourcingApp where processNO='外购')");
		if(qpList.size()>0){
			List<String> markIdList = new ArrayList<String>();
			for(QuotedPrice qp:qpList){
				if(!markIdList.contains(qp.getMarkId())){
					markIdList.add(qp.getMarkId());
					OutSourcingApp osApp = new OutSourcingApp();
					osApp.setDept(loginUser.getDept());
					osApp.setIsJiaji("否");
					osApp.setMarkID(qp.getMarkId());
					osApp.setProcessNO("外购");
					osApp.setDeliveryCount(qp.getFilnalCount());
					osApp.setQpId(qp.getId());
					osApp.setRootId(qp.getRootId());
					osApp.setDeliveryDate(DateUtil.formatDate(new Date(),
					"yyyy-MM-dd"));
					saveOSApp1(osApp);
					i++;
				}
			}
		}
		
		System.out.println(i+"::"+j);
		return "true";
	}

}
