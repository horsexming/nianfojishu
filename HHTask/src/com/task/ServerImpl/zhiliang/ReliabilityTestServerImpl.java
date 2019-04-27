package com.task.ServerImpl.zhiliang;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.Server.zhiliang.ReliabilityTestServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Users;
import com.task.entity.android.OsRecordScope;
import com.task.entity.sop.WaigouDailySheet;
import com.task.entity.system.CircuitRun;
import com.task.entity.systemfile.SystemFile;
import com.task.entity.zhiliang.ReliabilityTestPro;
import com.task.entity.zhiliang.ReliabilityTestRecord;
import com.task.entity.zhiliang.ReliabilityTestSheet;
import com.task.util.Util;

import sun.org.mozilla.javascript.internal.IdScriptableObject;

public class ReliabilityTestServerImpl implements ReliabilityTestServer{

	private TotalDao totalDao;
	
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public ReliabilityTestSheet getRTSById(Integer id) {
		ReliabilityTestSheet sheet = (ReliabilityTestSheet) totalDao.getObjectById(ReliabilityTestSheet.class, id);
		System.out.println(sheet.getRecordSet());
		return sheet;
	}

	@Override
	public ReliabilityTestPro getRTPById(Integer id) {
		ReliabilityTestPro pro = (ReliabilityTestPro) totalDao.getObjectById(ReliabilityTestPro.class, id);
		return pro;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] queryByCondition(ReliabilityTestSheet sheet,Integer pageNo,Integer pageSize, String tag) {
		
		if(sheet==null) {
			sheet = new ReliabilityTestSheet();
		}
		String hql = totalDao.criteriaQueries(sheet, null,null);
		if(sheet.getjGname()!=null && sheet.getjGname()!="null" && !sheet.getjGname().equals("")){
			hql +=" and jGname like '%"+sheet.getjGname()+"%'";
		}
		if(tag!=null && tag.equals("submit")) {
			hql+=" and personToLook like '%"+Util.getLoginUser().getName()+"%'";// and epStatus is not null
		}
		Integer count = totalDao.getCount(hql);
		List<ReliabilityTestSheet> list = totalDao.findAllByPage(hql+"order by id desc", pageNo, pageSize);
		
		
		Object[] obj = new Object[] {count,list};
		return obj;
	}

	@Override
	public String addRTS(ReliabilityTestSheet sheet,List<ReliabilityTestRecord> recordList,String tag){
		Users loginUser = Util.getLoginUser();
		if(sheet!=null &&  recordList!=null && recordList.size()>0) {
			String maxNumber = getMaxNumber(null);
			if(sheet.getNumber()==null || sheet.getNumber().equals("")){
				return "流水号不能为空";
			}else if(!sheet.getNumber().equals(maxNumber)){
				return "手速慢了些，当前的流水号已被使用，请刷新后重试";
			}
			if(sheet.getAddUserName()==null ) {
				sheet.setAddUserName(loginUser.getName());
			}
			if(sheet.getAddTime().equals(loginUser.getName())) {
				sheet.setAddUserCode(loginUser.getCode());
				sheet.setAddUserId(loginUser.getId());
			}
			Set<ReliabilityTestRecord> recordsSet = new HashSet<ReliabilityTestRecord>(recordList);
			for (ReliabilityTestRecord reliabilityTestRecord : recordsSet) {
				reliabilityTestRecord.setTestSheet(sheet);
				totalDao.save(reliabilityTestRecord);
			}
			sheet.setRecordSet(recordsSet);
			try {
				boolean save = totalDao.save(sheet);
				if(save) {
					String personToLookId = sheet.getPersonToLookId();
					if(personToLookId!=null && personToLookId!=""){
						try {
							String processName = "可靠性测试申请";
							String[] idSz = personToLookId.split(";");
							Integer[] userIds =new Integer[idSz.length]; 
							for (int i = 0; i < idSz.length; i++) {
								userIds[i] = Integer.parseInt(idSz[i]);
							}
							
							AlertMessagesServerImpl.addAlertMessages(processName,
									"单位:"+sheet.getCompany()+",申请人:"+sheet.getAddUserName()
									+",添加可靠性测试申请,请您前往测试.",userIds,
									"ReliabilityTestAction!toSubmitTestPro.action?id="+sheet.getId(),true);
							
						} catch (Exception e) {
						}
					}
					
					return "添加成功";
				}else {
					return "保存失败!";
				}
				
			}catch (Exception e) {
				throw new RuntimeException(e.toString());
			}
			
		}
		
		return "参数错误,测试项目必选";
	}
	
	@Override
	public String updateRTS(ReliabilityTestSheet sheet,
			List<ReliabilityTestRecord> recordList, String tag) {
		Users loginUser = Util.getLoginUser();
		if(sheet!=null &&  recordList!=null && recordList.size()>0) {
			if(sheet.getId()==null){
				return "参数不正确";
			}
			if(sheet.getAddUserName()==null ) {
				sheet.setAddUserName(loginUser.getName());
			}
			if(sheet.getAddTime().equals(loginUser.getName())) {
				sheet.setAddUserCode(loginUser.getCode());
				sheet.setAddUserId(loginUser.getId());
			}
			Set<ReliabilityTestRecord> recordsSet = new HashSet<ReliabilityTestRecord>();
			for (ReliabilityTestRecord testRecord : recordList) {
				if(testRecord.getId()!=null){//修改
					ReliabilityTestRecord record = (ReliabilityTestRecord) totalDao.getObjectById(ReliabilityTestRecord.class, testRecord.getId());
					record.setTestRecord(testRecord.getTestRecord());//测试记录
					if(testRecord.getTestFile()!=null){
						record.setTestFile(testRecord.getTestFile());//文件
						record.setOtherFileName(testRecord.getOtherFileName());//别名
					}
					totalDao.update(record);
					recordsSet.add(record);
				}else{//新增
					testRecord.setTestSheet(sheet);
					totalDao.save(testRecord);
					recordsSet.add(testRecord);
				}
			}
			sheet.setRecordSet(recordsSet);
			try {
				boolean update = totalDao.update(sheet);
				if(update) {
					String processName = "可靠性测试申请";
					String[] idSz = sheet.getPersonToLook().split(";");
					
					Integer[] userIds =new Integer[idSz.length]; 
					for (int i = 0; i < idSz.length; i++) {
						Integer userId = (Integer) 
								totalDao.getObjectByCondition("select id from Users where name=?", idSz[i]);
						userIds[i] = userId;
					}
					
					AlertMessagesServerImpl.addAlertMessages(processName,
							"单位:"+sheet.getCompany()+",申请人:"+sheet.getAddUserName()
							+",添加可靠性测试申请,请您前往测试.",userIds,
							"ReliabilityTestAction!toSubmitTestPro.action?id="+sheet.getId(),true);
				}
				//ReliabilityTestSheet rts = getRTSById(sheet.getId());
				
				/*sheet.setEpStatus(rts.getEpStatus());
				sheet.setEpId(rts.getEpId());
				boolean update1 = totalDao.update1(sheet);
				if(update1) {
					boolean update = true;
					if(!sheet.getEpStatus().equals("未审批")){
						Integer epId = CircuitRunServerImpl.createProcess("可靠性测试申请", 
								ReliabilityTestSheet.class, sheet.getId(), "epStatus", "id", 
								"ReliabilityTestAction!getRTSById.action?id="+sheet.getId(),
								"单位:"+sheet.getCompany()+",申请人:"+sheet.getAddUserName()
								+",添加可靠性测试申请,请您审批.", true);
						if(epId!=null&& epId>0) {
							sheet.setEpId(epId);
							CircuitRun circuitRun = (CircuitRun) totalDao.getObjectById(CircuitRun.class, epId);
							if ("同意".equals(circuitRun.getAllStatus())
									&& "审批完成".equals(circuitRun.getAuditStatus())) {
								sheet.setEpStatus("同意");
							} else {
								sheet.setEpStatus("未审批");
							}
						}
						update = totalDao.update(sheet);
					}
					
					if(update) {*/
						return "修改成功";
//					}else {
//						return "修改失败";
//					}
//				}else {
//					return "修改失败!";
//				}
				
			}catch (Exception e) {
				throw new RuntimeException(e.toString());
			}
			
		}
		
		return "参数错误,测试项目必选";
	}

	@Override
	public String saveRTP(ReliabilityTestPro pro) {
		if(pro!=null&& pro.getProName()!=null&& !pro.getProName().equals("")) {
			ReliabilityTestPro testPro = (ReliabilityTestPro) totalDao.getObjectByCondition("from ReliabilityTestPro where proName =?", pro.getProName());
			if(testPro!=null) {
				return "测试项目已经存在,无需添加";
			}
			boolean save = totalDao.save(pro);
			if(save) {
				return "添加成功";
			}else {
				return "添加失败";
			}
		}
		
		return "参数错误";
	}

	@Override
	public Map<Integer,Object> findRTPByCondition(ReliabilityTestPro pro,Integer pageNo,Integer pageSize,String tag) {
		if(pro==null) {
			pro = new ReliabilityTestPro();
		}
		String hql = totalDao.criteriaQueries(pro, null);
		Map<Integer, Object> map = new HashMap<Integer, Object>(); 
		Integer count = totalDao.getCount(hql);
		List<ReliabilityTestPro> list = totalDao.findAllByPage(hql, pageNo, pageSize);
		map.put(1, count);
		map.put(2, list);
		
		return map;
	}

	@Override
	public String delRTPById(Integer id) {
		if(id!=null) {
			ReliabilityTestPro pro = getRTPById(id);
			boolean delete = totalDao.delete(pro);
			if(delete) {
				return "删除成功";
			}
		}
		
		
		return "删除失败";
	}

	/**
	 * 删除可靠性测试单
	 */
	@Override
	public String deleteRTS(Integer id, String tag) {
		boolean flag = false;
		ReliabilityTestSheet sheet = (ReliabilityTestSheet) totalDao.getObjectById(ReliabilityTestSheet.class, id);
		Set<ReliabilityTestRecord> recordSet = sheet.getRecordSet();
		for (ReliabilityTestRecord reliabilityTestRecord : recordSet) {
			flag = totalDao.delete(reliabilityTestRecord);
		}
		
		boolean delete = totalDao.delete(sheet);
		if(delete && flag){
			return "删除成功";
		}
		return "删除失败";
	}

	//获取最大的流水号
	@Override
	public String getMaxNumber(String tag) {
		String before = "KKX-" + Util.getDateTime("yyyyMM-");
		String maxNumber = (String) totalDao.getObjectByCondition("select max(number) from ReliabilityTestSheet " +
				"where number like '"+before+"%'");
		String newNumber = null;
		if(maxNumber==null || maxNumber.equals("")){
			newNumber = before+"0001";
		}else{
			DecimalFormat df = new DecimalFormat("0000");
			String nowNumber = maxNumber.split(before)[1];
			Integer number1 = Integer.parseInt(nowNumber)+1;
			String number2 = df.format(number1);
			newNumber = before + number2;
		}
		return newNumber;
	}

	@Override
	public WaigouDailySheet findwgdSheetById(Integer id) {
		if (id != null) {
			WaigouDailySheet wds = (WaigouDailySheet) totalDao.get(
					WaigouDailySheet.class, id);
			return wds;
		}
		return null;
	}

	//提交测试结果
	@Override
	public String submitTestResult(ReliabilityTestSheet sheet, List<ReliabilityTestRecord> recordList, String tag) {
		ReliabilityTestSheet oldSheet = (ReliabilityTestSheet) totalDao.getObjectById(
				ReliabilityTestSheet.class, sheet.getId());
		oldSheet.setJlPerson(sheet.getJlPerson());//记录人
		oldSheet.setJlAddTime(sheet.getJlAddTime());//记录时间
		
		oldSheet.setDecideBasis(sheet.getDecideBasis());//判定依据
		oldSheet.setDecideResult(sheet.getDecideResult());//判定结果
		
		Set<ReliabilityTestRecord> recordSet = new HashSet<ReliabilityTestRecord>(recordList);
		oldSheet.setRecordSet(recordSet);
		
		try {
			String processName = "可靠性测试申请";
			Integer epId = CircuitRunServerImpl.createProcessbf(processName,
			SystemFile.class, sheet.getId(), "epStatus",
			"id",
			"ReliabilityTestAction!getRTSById.action?id="+oldSheet.getId(), 
			"单位:"+oldSheet.getCompany()+",申请人:"+oldSheet.getAddUserName()
			+",添加可靠性测试申请,请您审批.", true, oldSheet.getUidsAndLevels(), "3");
			if(epId!=null&& epId>0) {
				oldSheet.setEpId(epId);
				CircuitRun circuitRun = (CircuitRun) totalDao.getObjectById(CircuitRun.class, epId);
				if ("同意".equals(circuitRun.getAllStatus())
						&& "审批完成".equals(circuitRun.getAuditStatus())) {
					oldSheet.setEpStatus("同意");
				} else {
					oldSheet.setEpStatus("未审批");
				}
				boolean update = totalDao.update(oldSheet);
				if(update) {
					return "提交成功";
				}
			}
		} catch (Exception e) {
			return "异常:"+e.toString();
		}
		return "提交失败";
	}


}
