package com.task.ServerImpl.seal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.seal.SealLogServer;
import com.task.ServerImpl.SmsServiceImpl;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.action.UsersAction;
import com.task.action.seal.SealLogAction;
import com.task.action.xinxi.TwoDimensionCode;
import com.task.entity.Price;
import com.task.entity.Users;
import com.task.entity.dangan.ArchiveUnarchiverAplt;
import com.task.entity.dangan.DangAn;
import com.task.entity.menjin.AccessEquipment;
import com.task.entity.menjin.JLMApplication;
import com.task.entity.renshi.Dimission_Handover;
import com.task.entity.seal.BorrowSeal;
import com.task.entity.seal.SealLog;
import com.task.entity.seal.SealLogType;
import com.task.entity.system.CircuitRun;
import com.task.util.RtxUtil;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class SealLogServerImpl implements SealLogServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}
	
	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public SealLog add(SealLog sealLog) throws Exception {
		// TODO Auto-generated method stub

		sealLog.setAduitStatus("未审批");
		if(sealLog.getName()!=null&&sealLog.getName().indexOf("章")<0){
			sealLog.setFujian2Status("无");
		}else {
			sealLog.setFujian2Status("待上传");
		}
		if(!"电子档".equals(sealLog.getCunType())){//不等于电子档统统要放入档案柜
			sealLog.setIsCunType("yes");
		}else {
			sealLog.setIsCunType("no");
		}
		String dateString = Util.getDateTime("yyyyMMdd");
		List<String> numberList = totalDao
				.query("select number from SealLog where number like '%seal"
						+ dateString + "%'");
		Integer nextNo1 = 0;
		if (numberList.size() > 0) {
			for (String number : numberList) {
				String[] numbers = number.split("_");
				if (numbers != null && numbers.length == 2) {
					Integer nextNo = Integer.parseInt(numbers[1]);
					if (nextNo > nextNo1) {
						nextNo1 = nextNo;
					}
				}
			}
			nextNo1++;
		}

		sealLog.setNumber("seal" + dateString + "_" + nextNo1);// 申请编号
		if (sealLog.getIsSave() != null && sealLog.getIsSave().equals("yes")) {// 存档编号
			List<String> saveNumberList = totalDao
					.query("select saveNumber from SealLog where saveNumber like '%save"
							+ dateString + "%'");
			Integer nextNo2 = 0;
			if (saveNumberList.size() > 0) {
				for (String number : saveNumberList) {
					String[] numbers = number.split("_");
					if (numbers != null && numbers.length == 2) {
						Integer nextNo = Integer.parseInt(numbers[1]);
						if (nextNo > nextNo2) {
							nextNo2 = nextNo;
						}
					}
				}
			}

			nextNo2++;
			sealLog.setSaveNumber("save" + dateString + "_" + nextNo2);
			// 创建档案
			int p = addPrice(sealLog);
			if (p>0)
				sealLog.setDocumentId(p);
		}
		boolean b = totalDao.save(sealLog);
		if (b) {
			String yinzhangType = "";
			String[] nam = sealLog.getName().split(",");
			if (nam.length > 0) {
				for (int i = 0; i < nam.length; i++) {
					if ("公章".equals(nam[i])) {
						yinzhangType = "公章";
					} else if ("法人章".equals(nam[i])) {
						yinzhangType = "法人章";
					} else if ("合同章".equals(nam[i])) {
						yinzhangType = "合同章";
					} else if ("发票章".equals(nam[i])) {
						yinzhangType = "发票章";
					} else if ("财务章".equals(nam[i])) {
						yinzhangType = "财务章";
					} else if ("名章".equals(nam[i])) {
						yinzhangType = "名章";
					}
				}
			}
			
			String processName = yinzhangType+"使用申请审核";//sealLog.getUserDept()+ 
			String aduitStatus = "aduitStatus";
			if ("是".equals(sealLog.getIsConfidential())) {
				processName = "机密印章申请审核";
			}else if(sealLog.getType()!=null && "合同评审".equals(sealLog.getType())){
				processName="合同评审审核";
				aduitStatus = "aduitStatus2";
				sealLog.setAduitStatus2("未审批");
			}
			Integer epId = CircuitRunServerImpl.createProcess(processName,
					SealLog.class, sealLog.getId(), aduitStatus, "id",
					"sealLogAction_sealLogdetail.action?sealLog.id="
							+ sealLog.getId(), sealLog.getUserName() + " "
							+ processName, true);
			if (epId != null) {
				if(sealLog.getType()!=null && "合同评审".equals(sealLog.getType())){
					sealLog.setEpId2(epId);
				}else{
					sealLog.setEpId(epId);
				}
				if (sealLog.getFujian() != null) {
					sealLog.setFujian("upload/file/sealfj/fujian"
							+ sealLog.getId() + "." + sealLog.getFujian());
				}
				b = totalDao.update(sealLog);
				if (b) {
					return sealLog;
				}
			}
		}
		return null;
	}

	/**
	 * 添加档案信息对象
	 * @param sealLog
	 * @param price
	 * @return
	 */
	private int addPrice(SealLog sealLog) {
		Price price = new Price();
		price.setName(sealLog.getDocumentName());
		price.setFileNumber(sealLog.getSaveNumber());
		price.setProduceType(sealLog.getDocumentType());
		price.setWriteDate(Util.getDateTime());//添加时间
		price.setPricePeriodStart(Util.getDateTime("yyyy-MM-dd"));//添加时间
		price.setChargePerson(sealLog.getChargePerson());
		price.setInputPeople(sealLog.getInputPeople());
		price.setHsPrice(0d);
		price.setBhsPrice(0d);
		price.setReTime(sealLog.getRetime());
		price.setPricePeriodEnd(sealLog.getSxtime());//价格失效时间
		price.setIsGuiDang("no");
		price.setCunType(sealLog.getCunType());
//		price.setIsCunType(sealLog.getIsCunType());//改为确认加章附件之后改状态
		if(totalDao.save(price))
			return price.getId();
		else
			return 0;
	}

	@Override
	public SealLog getSealLog(Integer id) {
		// TODO Auto-generated method stub
		return (SealLog) totalDao.getObjectById(SealLog.class, id);
	}

	@Override
	public Map<Integer, Object> findSealLogsByCondition(SealLog sealLog,
			int pageNo, int pageSize, String pageStatus,String tag) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if (sealLog == null) {
			sealLog = new SealLog();
		}
		String hql = null;
		if (pageStatus != null && pageStatus.equals("single")) {
			Users users = Util.getLoginUser();
			String sql = " userCode='" + users.getCode() + "' and userName='"
					+ users.getName() + "'";
			if(tag!=null && tag.equals("htda")){
				sql += " and  type = '合同评审' ";
			}
			hql = totalDao.criteriaQueries(sealLog, sql, null);
		} else if (pageStatus != null && pageStatus.equals("dept")) {
			Users users = Util.getLoginUser();
			String sql = " userDept='" + users.getDept() + "' ";
			hql = totalDao.criteriaQueries(sealLog, sql, null);
		} else if (pageStatus != null && pageStatus.equals("gongzhang")) {
			String sql = " name like '%公章%'";
			hql = totalDao.criteriaQueries(sealLog, sql, null);
		} else if (pageStatus != null && pageStatus.equals("hetong")) {
			String sql = " name like '%合同章%'";
			hql = totalDao.criteriaQueries(sealLog, sql, null);
		} else if (pageStatus != null && pageStatus.equals("fapiao")) {
			String sql = " name like '%发票章%'";
			hql = totalDao.criteriaQueries(sealLog, sql, null);
		}else if(pageStatus != null && pageStatus.equals("htda")){
			String	sql = "   type = '合同评审' ";
			hql = totalDao.criteriaQueries(sealLog, sql, null);
		}else {
			hql = totalDao.criteriaQueries(sealLog, null, null);
		}
		int count = totalDao.getCount(hql);
		hql += " order by id desc";
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		SealLog sealLog = getSealLog(id);
		if (sealLog != null) {
			if (sealLog.getDocumentId() != null) {
				Price p = (Price) totalDao.getObjectById(Price.class, sealLog
						.getDocumentId());
				if (p != null) {
					totalDao.delete(p);
				}
			}
			if (sealLog.getEpId() != null) {
				CircuitRun cr = (CircuitRun) totalDao.getObjectById(
						CircuitRun.class, sealLog.getEpId());
				if (cr != null) {
					totalDao.delete(cr);
				}
			}
			return totalDao.delete(sealLog);
		}
		return false;
	}

	@Override
	public boolean update(SealLog sealLog) {
		// TODO Auto-generated method stub
		SealLog oldSealLog = getSealLog(sealLog.getId());
		if (oldSealLog != null) {
			oldSealLog.setName(sealLog.getName());
			if (sealLog.getIsSave() != null && sealLog.getIsSave().equals("no")) {
				oldSealLog.setSaveNumber(null);
				oldSealLog.setDocumentName(null);
				oldSealLog.setDocumentType(null);
				oldSealLog.setChargePerson(null);
				oldSealLog.setInputPeople(null);
				oldSealLog.setRetime(null);
				if (oldSealLog.getDocumentId() != null) {
					Price p = (Price) totalDao.getObjectById(Price.class,
							oldSealLog.getDocumentId());
					if (p != null) {
						totalDao.delete(p);
						oldSealLog.setDocumentId(null);
					}
				}
			} else if (sealLog.getIsSave() != null
					&& sealLog.getIsSave().equals("yes")
					&& oldSealLog.getIsSave() != null
					&& oldSealLog.getIsSave().equals("no")) {
				String dateString = Util.DateToString(new Date(), "yyyyMMdd");
				List<String> saveNumberList = totalDao
						.query("select saveNumber from SealLog where saveNumber like '%save"
								+ dateString + "%'");
				Integer nextNo2 = 0;
				if (saveNumberList.size() > 0) {
					for (String number : saveNumberList) {
						String[] numbers = number.split("_");
						if (numbers != null && numbers.length == 2) {
							Integer nextNo = Integer.parseInt(numbers[1]);
							if (nextNo > nextNo2) {
								nextNo2 = nextNo;
							}
						}
					}
				}
				nextNo2++;
				oldSealLog.setSaveNumber("save" + dateString + "_" + nextNo2);
				oldSealLog.setDocumentName(sealLog.getDocumentName());
				oldSealLog.setDocumentType(sealLog.getDocumentType());
				oldSealLog.setChargePerson(sealLog.getChargePerson());
				oldSealLog.setInputPeople(sealLog.getInputPeople());
				oldSealLog.setRetime(sealLog.getRetime());
				// 创建档案
				Price price = new Price();
				price.setName(sealLog.getDocumentName());
				price.setFileNumber(sealLog.getSaveNumber());
				price.setProduceType(sealLog.getDocumentType());
				price.setWriteDate(Util.DateToString(new Date(), "yyyy-MM-dd"));
				price.setChargePerson(sealLog.getChargePerson());
				price.setInputPeople(sealLog.getInputPeople());
				price.setHsPrice(0d);
				price.setBhsPrice(0d);
				price.setReTime(sealLog.getRetime());
				boolean b = totalDao.save(price);
				if (b) {
					oldSealLog.setDocumentId(price.getId());
				}
			}
			oldSealLog.setIsSave(sealLog.getIsSave());
			oldSealLog.setUseFor(sealLog.getUseFor());
			oldSealLog.setUserCode(sealLog.getUserCode());
			oldSealLog.setUserName(sealLog.getUserName());
			oldSealLog.setUserDept(sealLog.getUserDept());
			if (oldSealLog.getAduitStatus() != null
					&& oldSealLog.getAduitStatus().equals("打回")) {
				boolean b = CircuitRunServerImpl.updateCircuitRun(oldSealLog
						.getEpId());
				if (b) {
					oldSealLog.setAduitStatus("未审批");
				} else {
					return false;
				}

			}
			if(oldSealLog.getEpId()==null){
				String processName = "印章申请审核";//sealLog.getUserDept()+ 
				if ("是".equals(sealLog.getIsConfidential())) {
					processName = "机密印章申请审核";
				}
				Integer epId =null;
				try {
					epId = CircuitRunServerImpl.createProcess(processName,
							SealLog.class, sealLog.getId(), "aduitStatus", "id",
							"sealLogAction_sealLogdetail.action?sealLog.id="
									+ sealLog.getId(), sealLog.getUserName() + " "
									+ processName, true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (epId != null) {
					oldSealLog.setEpId(epId);
				}
			}
			return totalDao.update(oldSealLog);
		}
		return false;
	}

	@Override
	public boolean makeSure(Integer id, String pageStatus) {
		// TODO Auto-generated method stub
		SealLog sealLog = getSealLog(id);
		if (sealLog != null) {
			String makesure = sealLog.getMakeSure();
			if (pageStatus != null && pageStatus.equals("gongzhang")) {
				if (makesure != null) {
					makesure += ",公章";
				} else {
					makesure = "公章";
				}
			} else if (pageStatus != null && pageStatus.equals("hetong")) {
				if (makesure != null) {
					makesure += ",合同章";
				} else {
					makesure = "合同章";
				}
			} else if (pageStatus != null && pageStatus.equals("fapiao")) {
				if (makesure != null) {
					makesure += ",发票章";
				} else {
					makesure = "发票章";
				}
			} else if (pageStatus != null && pageStatus.equals("all")) {
				makesure = sealLog.getName();
			}

			sealLog.setMakeSure(makesure);
			return totalDao.update(sealLog);
		}
		return false;
	}

	@Override
	public Users getUser(SealLog sealLog) {
		// TODO Auto-generated method stub
		List<Users> users = new ArrayList<Users>();
		if (sealLog != null && sealLog.getUserCode() != null) {
			users = totalDao.query("from Users where code=?", sealLog
					.getUserCode());
		} else if (sealLog != null && sealLog.getUserName() != null) {
			users = totalDao.query("from Users where name=?", sealLog
					.getUserName());
		}
		if (users.size() > 0) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public String update2(SealLog sealLog) {
		// TODO Auto-generated method stub
		if (sealLog != null) {
			SealLog log = getSealLog(sealLog.getId());
			if (log != null) {
				if(log.getDocumentId()!=null){
					Price price = (Price) totalDao.getObjectById(Price.class, log.getDocumentId());
					if(price!=null){
						price.setAttachmentName(sealLog.getFujian2());
						if("yes".equals(log.getIsCunType())){
							price.setIsCunType("yes");
						}else {
							price.setIsGuiDang("yes");
						}
						totalDao.update(price);
					}
				}
				if("yes".equals(log.getIsCunType())){
					log.setFujian2Status("待存档");
				}else {
					log.setFujian2Status("已上传");
				}
				log.setFujian2(sealLog.getFujian2());
				log.setFujian2Time(Util.getDateTime());
				if (totalDao.update(log)){
					if("yes".equals(log.getIsCunType())){
						//将要存档的给印章管理员发消息
						String[] nam = log.getName().split(",");
						String zhang = "";
						if (nam.length > 0) {
							for (int i = 0; i < nam.length; i++) {
								if ("公章".equals(nam[i])) {
									zhang = nam[i];
									break;
								}
								if ("法人章".equals(nam[i])) {
									zhang = nam[i];
									break;
								}
								if ("合同章".equals(nam[i])) {
									zhang = nam[i];
									break;
								}
							}
						}
						SealLogType logType = (SealLogType) totalDao
						.getObjectByCondition(
								"from SealLogType where slname = ?",zhang);
						if (logType != null) {
							Users users = (Users) totalDao
							.getObjectByCondition(
									"from Users where code = ? and name = ?",
									logType.getBgCode(),
									logType.getBgName());
							if (users != null) {
								RtxUtil.sendNotify(logType.getBgCode(),
										"您有需要存档的档案 ，请前往《未归档文件管理》为档案："+log.getDocumentName()+"选择柜号存档", "系统消息", "0", "0");
							}
						}
					}
					return "上传成功";
				}
			}
		}
		return "上传失败";
	}

	@Override
	public String toaddSeal() {
		// TODO Auto-generated method stub
		List<SealLogType> selTy = totalDao
				.query("from SealLogType where yzZGStatus = '在柜' and slname = '公章' ");
		if (selTy != null && selTy.size() > 0) {
			Users users = Util.getLoginUser();
			if (users != null) {
				List<SealLog> sealLogs = totalDao
						.query(
								"from SealLog where userCode = ? and userName = ? and fujian2Status in ('待上传','待存档') and aduitStatus = '同意'",
								users.getCode(), users.getName());
				if (sealLogs!=null&&sealLogs.size() > 0) {
					boolean b = false;
					String date = Util.getDateTime("");
					for (SealLog sealLog : sealLogs) {
						ArchiveUnarchiverAplt aplt = (ArchiveUnarchiverAplt) totalDao.getObjectByCondition("from ArchiveUnarchiverAplt where sealId = ? order by id desc", sealLog.getId());
						if(aplt!=null){
							if(aplt.getUseType().equals("已使用")||date.compareTo(aplt.getShixiaoTime())>0){
								b = true;break;
							}
						}
					}
					if(b){
						return "您有未上传加章文件或 待存档 的申请记录，请先处理！";
					}else {
						return "true";
					}
				} else {
					return "true";
				}
			}
			return "请先登录！";
		}
		return "公章已被借出，请等待归还后再申请！";
	}

	@Override
	public String toborrowSeal() {
		// TODO Auto-generated method stub
		List<SealLogType> selTy = totalDao
				.query("from SealLogType where slname = '公章' ");
		if (selTy != null && selTy.size() > 0) {
			Users users = Util.getLoginUser();
			if (users != null) {
				int sealLogs = totalDao
						.getCount("from SealLog where fujian2Status = '待上传' and aduitStatus = '同意'");
				if (sealLogs > 0) {
					return "您有未上传加章文件的申请记录，请先上传！";
				} else {
					// 查看印章状态是否正常
					if ("在柜".equals(selTy.get(0).getYzZGStatus())) {
						return "true";
					} else {
						return "印章当前已被借用，无法出借";
					}
				}
			}
			return "请先登录！";
		}
		return "尚未添加公章信息，请联系保管人员添加后再申请！";
	}

	@Override
	public String addBorrowSeal(BorrowSeal borrowSeal) {
		// TODO Auto-generated method stub
		if (borrowSeal != null) {
			borrowSeal.setStatus("未审批");
			borrowSeal.setAddTime(Util.getDateTime());
			totalDao.save(borrowSeal);

			String processName = "印章借用";
			Integer epId = null;
			try {
				epId = CircuitRunServerImpl.createProcess(processName,
						BorrowSeal.class, borrowSeal.getId(), "status", "id",
						"" + borrowSeal.getId(), borrowSeal.getJyDept() + "部门 "
								+ borrowSeal.getJyName() + " 印章借用申请，请您审批！",
						true);
				if (epId != null && epId > 0) {
					borrowSeal.setEpId(epId);
					if (totalDao.update(borrowSeal))
						return "申请成功！";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Map<Integer, Object> findSealLogTypeByCondition(
			SealLogType sealLogType, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		if (sealLogType == null) {
			sealLogType = new SealLogType();
		}
		String hql = totalDao.criteriaQueries(sealLogType,null,null);
		hql += " order by id desc";
		List listi = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listi);
		map.put(2, count);
		return map;
	}
	
	public String addsealLogtype(SealLogType sealLogType) {
		// TODO Auto-generated method stub
		sealLogType.setSlname("");
//		sealLogtype.setAddTime(Util.getDateTime());
//		accessEquipment.setAdminCardId("");
//		accessEquipment.setAdminStatus("待添加");
//		int acc2 = totalDao.getCount("from AccessEquipment where equipmentIP = ?",accessEquipment.getEquipmentIP());
//		if (acc2 > 0)
//			return "设备ip已存在，添加失败！";
//		if (totalDao.save(accessEquipment))
//			return "true";
//		else
			return "添加失败";
	}

	@Override
	public void updatelinshi() {
		// TODO Auto-generated method stub
		SealLog sealLog = (SealLog) totalDao.getObjectById(SealLog.class, 1367);
		if (sealLog==null) return;
		String yinzhangType = "";
		String[] nam = sealLog.getName().split(",");
		if (nam.length > 0) {
			for (int i = 0; i < nam.length; i++) {
				if ("公章".equals(nam[i])) {
					yinzhangType = "公章";
				} else if ("法人章".equals(nam[i])) {
					yinzhangType = "法人章";
				} else if ("合同章".equals(nam[i])) {
					yinzhangType = "合同章";
				} else if ("发票章".equals(nam[i])) {
					yinzhangType = "发票章";
				} else if ("财务章".equals(nam[i])) {
					yinzhangType = "财务章";
				} else if ("名章".equals(nam[i])) {
					yinzhangType = "名章";
				}
			}
		}
		Users users = (Users) totalDao.getObjectByCondition("from Users where code = ? and name = ?", sealLog.getUserCode(),sealLog.getUserName());
		if(users!=null){
			ActionContext.getContext().getSession().put(
					TotalDao.users, users);//讲请假人临时存入Session
		}
		String processName = yinzhangType+"使用申请审核";//sealLog.getUserDept()+ 
		String aduitStatus = "aduitStatus";
		if ("是".equals(sealLog.getIsConfidential())) {
			processName = "机密印章申请审核";
		}else if(sealLog.getType()!=null && "合同评审".equals(sealLog.getType())){
			processName="合同评审审核";
			aduitStatus = "aduitStatus2";
			sealLog.setAduitStatus2("未审批");
		}
		Integer epId;
		try {
			epId = CircuitRunServerImpl.createProcess(processName,
					SealLog.class, sealLog.getId(), aduitStatus, "id",
					"sealLogAction_sealLogdetail.action?sealLog.id="
							+ sealLog.getId(), sealLog.getUserName() + " "
							+ processName, true);
			if (epId != null) {
				if(sealLog.getType()!=null && "合同评审".equals(sealLog.getType())){
					sealLog.setEpId2(epId);
				}else{
					sealLog.setEpId(epId);
				}
				if (sealLog.getFujian() != null) {
					sealLog.setFujian("upload/file/sealfj/fujian"
							+ sealLog.getId() + "." + sealLog.getFujian());
				}
				totalDao.update(sealLog);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
