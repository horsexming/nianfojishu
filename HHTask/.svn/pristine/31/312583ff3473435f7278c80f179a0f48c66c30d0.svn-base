package com.task.ServerImpl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Alignment;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.morph.wrap.Bean;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.OvertimeServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.AnnualLeave;
import com.task.entity.AskForLeave;
import com.task.entity.AttendanceTow;
import com.task.entity.AttendanceTowCollect;
import com.task.entity.InsuranceGold;
import com.task.entity.Overtime;
import com.task.entity.OvertimeDetail;
import com.task.entity.Users;
import com.task.entity.WageStandard;
import com.task.entity.banci.BanCi;
import com.task.entity.sop.Procard;
import com.task.entity.system.CircuitRun;
import com.task.util.Util;


@SuppressWarnings("unchecked")
public class OvertimeServerImpl implements OvertimeServer {
	private TotalDao totalDao;

	public AnnualLeave ByCodeAnnualLeave(String code) {
		if (code != null && code.length() > 0) {
			String hql = "from AnnualLeave where jobNum=?";
			return (AnnualLeave) totalDao.getObjectByCondition(hql, code);
		}
		return null;

	}

	public void addAnnualLeave(AnnualLeave a) {
		totalDao.save(a);
	}

	public void updateAnnualLeave(AnnualLeave a) {
		totalDao.update(a);
	}

	// 查询所有加工件号为页面Select使用
	public String finAllMarkIdForSetlect() {
		String hql = "from Procard where status='领工序'";
		List list = totalDao.query(hql);
		if (list != null) {
			String message = "";
			for (int i = 0; i < list.size(); i++) {
				Procard procard = (Procard) list.get(i);
				message += procard.getMarkId() + "|";
			}
			return message;
		}
		return null;
	}

	@Override
	public List finAllMarkIdForSetlectAll(Integer pageNo,Integer pageSize) {
		String hql = "select markId,selfCard from Procard where status not in ('完成','待入库','入库','取消') order by markId";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize, null);
//		List list = totalDao.query(hql);
		return list;
	}

	// 查询users为页面input
	public Users findUserByCode(String code) {
		if (code != null && code.length() > 0) {
			String hql = "from Users where code=?";
			return (Users) totalDao.getObjectByCondition(hql, code);
		}
		return null;
	}


	//批量加班申请记录
	public String addMoneyOvertime(Overtime overtime, List<Users> list) {
		String falseaName = "";
		for (Users users : list) {
			Overtime overtime2 = new Overtime();
			BeanUtils.copyProperties(overtime, overtime2,new String[] {"id"});
			overtime2.setCreateDate(Util.getDateTime());
			overtime2.setStatus("未审批");
			overtime2.setAccessStatus("未生成");
			if (users.getCode()!=null) {
				WageStandard wageStandard = findWSByUser(users.getCode()); // 根据工号查询工资模板
				if(wageStandard!=null){
					InsuranceGold insuranceGold = findInsuranceGoldBylc(wageStandard
							.getLocalOrField(), wageStandard
							.getCityOrCountryside(), wageStandard
							.getPersonClass()); // 福利系数（计算公司缴纳的保险成本）
					if(insuranceGold==null){
					}else{
						// 当月个人人力成本
						Float workingHoursWages = (wageStandard.getGangweigongzi()
								+ wageStandard.getSsBase()
								* (insuranceGold.getQYoldageInsurance()
										+ insuranceGold.getQYmedicalInsurance()
										+ insuranceGold
										.getQYunemploymentInsurance()
										+ insuranceGold.getQYinjuryInsurance() + insuranceGold
										.getQYmaternityInsurance()) / 100 + wageStandard
										.getGjjBase()
										* insuranceGold.getQYhousingFund() / 100);
						Float basicWorkingHoursWages = workingHoursWages / 619200f; // 工序中基本工时工资(秒工资=单工序总成本/21.5天)
						Float time=0f;
						long timelong = (Util.getWorkTime1(overtime.getEndDate(),overtime.getStartDate()))/1000;
						time=(float)timelong;
						float reCost=basicWorkingHoursWages*time*2;
						overtime2.setReCost(reCost);
					}
				}
			}
			if (totalDao.save(overtime2)) {
				String workFlowMark = users.getDept() + "加班审批流程";// 流程标记
				try {
					Integer epId = CircuitRunServerImpl.createProcess(workFlowMark,
							Overtime.class, overtime2.getId(), "status", "id", users.getDept()
									+ "部门的" + users.getName() + "加班审核,请您审核!",
							true);
					overtime2.setEpId(epId);
					totalDao.update(overtime2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else falseaName += users.getName()+",";
		}
		if ("".equals(falseaName)) return "true";
		return falseaName+"批量加班失败！";
	}

	public void overc(){
//		List<Overtime> list = totalDao.query("from Overtime where createDate like '%2017-01-13%'");
//		for (Overtime overtime : list) {
//			String workFlowMark = overtime.getOvertimeDept() + "加班审批流程";// 流程标记
//			try {
//				Integer epId = CircuitRunServerImpl.createProcess(workFlowMark,
//						Overtime.class, overtime.getId(), "status", "id", overtime
//								.getOvertimeDept()
//								+ "部门的" + overtime.getOvertimeName() + "加班审核,请您审核!",
//						true);
//				overtime.setEpId(epId);
//				totalDao.update(overtime);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	

	@Override
	public String addOvertimeList(Overtime overtime2, Integer[] usersId) {
		// TODO Auto-generated method stub
		List<Users> list = null;
		if(usersId!=null&&usersId.length>0){
			list = totalDao.query("from Users where id in ("+StringUtils.join(usersId, ",")+") and onWork <> '离职'");
		}
		if(list.isEmpty())
			return "请选择加班人";
			overtime2.setAccessStatus("未生成");//Lc_add
//		if(overtime2.getOverTimeLong()!=null && overtime2.getOverTimeLong()>12) {
//			return "申请总时长不能大于12小时";
//		}
		
		for (Users use : list) {
			Overtime overtime = new Overtime();
			Set<OvertimeDetail> set = new HashSet<OvertimeDetail>();
			BeanUtils.copyProperties(overtime2, overtime);
			
			List<OvertimeDetail> overtimeDetailList = new ArrayList<OvertimeDetail>();
			overtimeDetailList.addAll(overtime2.getOvertimeDetails());
			Integer xiuxi = 0;
			for (OvertimeDetail overtimeDetail2 : overtimeDetailList) {
				OvertimeDetail overtimeDetail = new OvertimeDetail();
				BeanUtils.copyProperties(overtimeDetail2, overtimeDetail);
				overtimeDetail.setId(null);
				overtimeDetail.setOvertime(null);
				if(overtimeDetail.getXiuxi()!=null) {
					xiuxi+=overtimeDetail.getXiuxi();
				}
				Integer count = checkOverTimeDetailByuserId(overtimeDetail, use.getId());
				if(count>0) {
					throw new RuntimeException(use.getName()+"的加班申请在（"+overtimeDetail.getStartTime()
									+"-"+overtimeDetail.getEndTime()+"）内已经存在记录，申请失败！请检查后重试  \n");
				}
				boolean save = totalDao.save(overtimeDetail);
				set.add(overtimeDetail);
				if(!save) {
					throw new RuntimeException("保存失败");
				}
			}
			
			overtime.setOvertimeId(use.getId());
			overtime.setOvertimeName(use.getName());
			overtime.setOvertimeCode(use.getCode());
			overtime.setOvertimeCardId(use.getCardId());
			overtime.setOvertimeDept(use.getDept());	
			overtime.setOvertimeDetails(set);
			
			//加班时长设置
			if(overtime.getOverTimeMinutes()!=null && overtime.getOverTimeMinutes()>0) {
				overtime.setOverTimeLong(overtime.getOverTimeLong()+(overtime.getOverTimeMinutes()/60f));
				if(overtime.getHuanxiu()!=null && overtime.getHuanxiu().equals("是")) {
					overtime.setUsablehxTime(overtime.getOverTimeLong());
				}
			}
			totalDao.save(overtime);
			
			
			overtime.setXiuxi(xiuxi);//分钟
			Float time= overtime.getOverTimeLong();//小时
			if(overtime.getHuanxiu()!=null && overtime.getHuanxiu().equals("是")) {
				overtime.setUsablehxTime(time);
			}
//			if(overtime.getXiuxi()!=null){
//				time -= (overtime.getXiuxi())/60;
//				//time -= overtime.getXiuxi()/60;
//			}
			
			WageStandard wageStandard = findWSByUser(use.getCode()); // 根据工号查询工资模板
			if(wageStandard!=null){
//				return "您还没有工资模板，无法计算根加工成本，请先联系认识部门添加工资模板!";
				InsuranceGold insuranceGold = findInsuranceGoldBylc(wageStandard
						.getLocalOrField(), wageStandard
						.getCityOrCountryside(), wageStandard
						.getPersonClass()); // 福利系数（计算公司缴纳的保险成本）
				if(insuranceGold==null){
//					AlertMessagesServerImpl.addAlertMessages("保险缴纳比例管理", "本年度福利系数尚未填写,请及时填写", "1");
//					return "本年度福利系数尚未填写，已通知相关责任人!";
				}else{
					// 当月个人人力成本
					Float workingHoursWages = (wageStandard.getGangweigongzi()
							+ wageStandard.getSsBase()
							* (insuranceGold.getQYoldageInsurance()
									+ insuranceGold.getQYmedicalInsurance()
									+ insuranceGold
									.getQYunemploymentInsurance()
									+ insuranceGold.getQYinjuryInsurance() + insuranceGold
									.getQYmaternityInsurance()) / 100 + wageStandard
									.getGjjBase()
									* insuranceGold.getQYhousingFund() / 100);
					Float basicWorkingHoursWages = workingHoursWages / 619200f; // 工序中基本工时工资(秒工资=单工序总成本/21.5天)
					float reCost=basicWorkingHoursWages*time*2;
					overtime.setReCost(reCost);
				}
			}
//				BeanUtils.copyProperties(overtime, overtime2, 
//						new String[] { "id", "reCost","overtimeId", "overtimeCode", "overtimeName", "overtimeCardId", "overtimeDept"});
//				overtime2.setOvertimeId(use.getId());
//				overtime2.setOvertimeName(use.getName());
//				overtime2.setOvertimeCode(use.getCode());
//				//overtime2.setOvertimeCode(use.getCode());
//				overtime2.setOvertimeCardId(use.getCardId());
//				overtime2.setOvertimeDept(use.getDept());	
//				totalDao.save(overtime2);
			//overTimesess(overtime2);
			
			Integer epId;
			try {
				if(use!=null&&("DM".equals(use.getPost())||("DGM".equals(use.getPost())))){
					String processName = "主管加班审批流程";
					String messa = overtime.getOvertimeDept() + "部门 " + overtime.getOvertimeName()+ "加班审核，请您审核！";
					epId = CircuitRunServerImpl.createProcess(processName,
							Overtime.class, overtime.getId(),
							"status", "id","overtimeAction!findOvertimeIdByDetail.action?id="+overtime.getId()
							, messa, true,overtime.getOvertimeDept());
				}else {
					String workFlowMark = "加班审批流程";// 流程标记
					epId = CircuitRunServerImpl.createProcess(workFlowMark,
							Overtime.class, overtime.getId(), "status", "id",
							"overtimeAction!findOvertimeIdByDetail.action?id="+overtime.getId(),overtime.getOvertimeDept()
							+ "部门的" + overtime.getOvertimeName() + "加班审核,请您审核!",
							true,overtime.getOvertimeDept());
				}
			} catch (Exception e) {
				throw new RuntimeException(e.toString());
			}
			if (epId != null && epId > 0) {
				overtime.setEpId(epId);
				CircuitRun circuitRun = (CircuitRun) totalDao.get(CircuitRun.class, epId);
				if ("同意".equals(circuitRun.getAllStatus())
						&& "审批完成".equals(circuitRun
								.getAuditStatus())) {
					overtime.setStatus("同意");
				} else {
					overtime.setStatus("未审批");
				}
				totalDao.update(overtime);
			}
		}
		//overTimesessBank(u);
		return "申请成功";
	}
	
	/**
	 * 
	 * @param overtime
	 */
	public void overTimesess(Overtime overtime){
		// 提交之前把用户存在session会话中
		String hql2 = "from Users where code=?";
		Users loginUser = (Users) totalDao.getObjectByCondition(hql2,
				overtime.getOvertimeCode());
		// 保存用户到session中
		ActionContext.getContext().getSession().put(TotalDao.users,
				loginUser);
	}
	public void overTimesessBank(Users u){
		// 保存用户到session中
		ActionContext.getContext().getSession().put(TotalDao.users, u);
	}
	
	// 添加加班记录
	public String addOvertime(Overtime overtime) {
		if(overtime!=null){
			Users u = Util.getLoginUser();
			overTimesess(overtime);
			WageStandard wageStandard = findWSByUser(overtime.getOvertimeCode()); // 根据工号查询工资模板
			if(wageStandard!=null){
//			return "您还没有工资模板，无法计算根加工成本，请先联系认识部门添加工资模板!";
				InsuranceGold insuranceGold = findInsuranceGoldBylc(wageStandard
						.getLocalOrField(), wageStandard
						.getCityOrCountryside(), wageStandard
						.getPersonClass()); // 福利系数（计算公司缴纳的保险成本）
				if(insuranceGold==null){
//				AlertMessagesServerImpl.addAlertMessages("保险缴纳比例管理", "本年度福利系数尚未填写,请及时填写", "1");
//				return "本年度福利系数尚未填写，已通知相关责任人!";
				}else{
					// 当月个人人力成本
					Float workingHoursWages = (wageStandard.getGangweigongzi()
							+ wageStandard.getSsBase()
							* (insuranceGold.getQYoldageInsurance()
									+ insuranceGold.getQYmedicalInsurance()
									+ insuranceGold
									.getQYunemploymentInsurance()
									+ insuranceGold.getQYinjuryInsurance() + insuranceGold
									.getQYmaternityInsurance()) / 100 + wageStandard
									.getGjjBase()
									* insuranceGold.getQYhousingFund() / 100);
					Float basicWorkingHoursWages = workingHoursWages / 619200f; // 工序中基本工时工资(秒工资=单工序总成本/21.5天)
					Float time=0f;
					long timelong = (Util.getWorkTime1(overtime.getStartDate(),overtime.getEndDate()))/(1000*60*60);
					time = (float)timelong;
					float reCost=basicWorkingHoursWages*time*2;
					overtime.setReCost(reCost);
				}
			}
			long timelong = (Util.getWorkTime1(overtime.getStartDate(),overtime.getEndDate()))/(1000*60*60);
			overtime.setOverTimeLong((float)timelong);
			if(overtime.getHuanxiu()!=null && overtime.getHuanxiu().equals("是")) {
				overtime.setUsablehxTime(overtime.getOverTimeLong());
			}
			overtime.setStatus("未审批");
			overtime.setAccessStatus("未生成");//Lc_add
			totalDao.save(overtime);
			
			//判断是不是主管申请加班
			Users ss= Util.getLoginUser();
			if(ss!=null&&("DM".equals(ss.getPost())||("DGM".equals(ss.getPost())))){
				String processName = "主管加班审批流程";
				String messa = overtime.getOvertimeDept() + "部门 "
						+ overtime.getOvertimeName()+ "加班审核，请您审核！";
				Integer epId;
				try {
					epId = CircuitRunServerImpl.createProcess(processName,
							Overtime.class, overtime.getId(),
							"status", "id", messa, true);
					if (epId != null && epId > 0) {
						overtime.setEpId(epId);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					overTimesessBank(u);
				}
			}else {
				String workFlowMark = "加班审批流程";// 流程标记
				try {
					Integer epId = CircuitRunServerImpl.createProcess(workFlowMark,
							Overtime.class, overtime.getId(), "status", "id", overtime
							.getOvertimeDept()
							+ "部门的" + overtime.getApplyName() + "加班审核,请您审核!",
							true);
					overtime.setEpId(epId);
					// overtime.setStatus("审批中");
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					overTimesessBank(u);
				}
			}
			return totalDao.update(overtime)+"";
		}
		return	"添加失败！";
	}

	// 删除加班记录
	public String deleteOvertime(Overtime overtime) {
		if (overtime!=null) {
			if(overtime.getEpId()!=null){
				CircuitRunServerImpl.deleteCircuitRun(overtime.getEpId());
			}
			if (totalDao.delete(overtime)) {
				return "删除成功";
			}
		}
		return "删除失败";
	}
	
	// 更新加班记录
	public String updateOvertime(Overtime overtime) {
		// CircuitRunAction_findAduitPage.action?id=
		if (totalDao.update(overtime)) {
			if (overtime.getStatus() != null
					&& "打回".equals(overtime.getStatus())) {
				CircuitRunServerImpl.updateCircuitRun(overtime.getEpId());
			}
			return "true";
		} else {
			return "false";
		}
	}

	// 更新加班记录
	public String updateOvertime(Overtime overtime,List<OvertimeDetail> overtimeDetails) {
		// CircuitRunAction_findAduitPage.action?id=
		Overtime saveOvertime = (Overtime) totalDao.getObjectById(Overtime.class, overtime.getId());
		if(saveOvertime!=null) {
			Float overTimeMinutes = overtime.getOverTimeMinutes();
			if(overTimeMinutes!=null && overTimeMinutes>0) {
				saveOvertime.setOverTimeLong(overtime.getOverTimeLong()+ overTimeMinutes/60f);
			}else {
				saveOvertime.setOverTimeLong(overtime.getOverTimeLong());
			}
			if(overtime.getHuanxiu()!=null && overtime.getHuanxiu().equals("是")) {
				overtime.setUsablehxTime(overtime.getOverTimeLong());
			}
			if(overtime.getEndDate()!=null && !overtime.getEndDate().equals("")) {
				saveOvertime.setEndDate(overtime.getEndDate());
			}
			if(overtime.getHuanxiu()!=null && !overtime.getHuanxiu().equals("")) {
				saveOvertime.setHuanxiu(overtime.getHuanxiu());
			}
			if(overtime.getOvertimeType()!=null && !overtime.getOvertimeType().equals("")) {
				saveOvertime.setOvertimeType(overtime.getOvertimeType());
			}
			if(overtime.getOvertimeNeirong()!=null && !overtime.getOvertimeNeirong().equals("")) {
				saveOvertime.setOvertimeNeirong(overtime.getOvertimeNeirong());
			}
			saveOvertime.setShuoming(overtime.getShuoming());
			Integer xiuxi = 0;
			Iterator<OvertimeDetail> iterator = overtimeDetails.iterator();
			while (iterator.hasNext()) {
				OvertimeDetail next = iterator.next();
				if(next==null) {
					iterator.remove();
				}
			}
			for (OvertimeDetail overtimeDetail : overtimeDetails) {
				if(overtimeDetail==null) {
					continue;
				}
				if(overtimeDetail.getId()!=null) {
					//更新
					overtimeDetail.setOvertime(saveOvertime);
					totalDao.update(overtimeDetail);
				}else {
					overtimeDetail.setOvertime(saveOvertime);
					totalDao.save(overtimeDetail);
				}
				if(overtimeDetail.getXiuxi()!=null) {
					xiuxi+=overtimeDetail.getXiuxi();
				}
			}
			saveOvertime.setXiuxi(xiuxi);
			saveOvertime.setOvertimeDetails(new HashSet<OvertimeDetail>(overtimeDetails));
			boolean update = totalDao.update(saveOvertime);
			if(update) {
				if (overtime.getStatus() != null
						&& "打回".equals(overtime.getStatus())) {
					CircuitRunServerImpl.updateCircuitRun(overtime.getEpId());
					return "修改成功";
				}
			}
			
		}else {
			return "id为空，保存失败";
		}
		return  "更新失败";
	}

	// 获得加班记录
	public Overtime getOvertimeById(Integer id) {
		if (id != null && id > 0) {
			return (Overtime) totalDao.getObjectById(Overtime.class, id);
		}
		return null;
	}

	// 获得加班记录集合 加班 待提交
	public Object[] findAllOvertimeForJbForDtj(Map map) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String hql = "from Overtime o where 1 = 1";
		if (map != null) {
			if (map.get("applyId") != null) {
				hql += " and o.applyId = " + map.get("applyId");
			}
			hql += " and (o.status = '未审批' or o.status='打回')";
		}
		hql += " order by o.startDate desc";
		List list = totalDao.find(hql);

		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	// 获得加班记录集合 加班 已提交
	public Object[] findAllOvertimeForJbForYtj(Map map, int pageNo, int pageSize) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String hql = "from Overtime o where 1 = 1";
		if (map != null) {
			if (map.get("applyId") != null) {
				hql += " and o.overtimeId = " + map.get("applyId");
			}
			hql += " and o.status != '未审批' and o.status != '打回'";
		}
		hql += " order by o.startDate desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	// 获得加班待审批记录集合
	public Object[] findAllOvertimeForSpForDsp(Map map) {
		Map<String, Object> resultMap = CircuitRunServerImpl.findAuditExeNode(
				Overtime.class, false);
		List overtimeListForSpForDsp = null;
		if (resultMap != null) {
			List<Integer> overtiemIdList = (List<Integer>) resultMap
					.get("entityId");
			overtimeListForSpForDsp = new ArrayList();
			int overtiemIdListSize = overtiemIdList.size();
			for (int i = 0; i < overtiemIdListSize; i++) {
				Integer overtiemId = overtiemIdList.get(i);
				Overtime overtime = this.getOvertimeById(overtiemId);
				overtimeListForSpForDsp.add(overtime);
			}
		}
		// int count = totalDao.getCount(null);
		Object[] o = { overtimeListForSpForDsp, 0 };
		return o;
	}

	// 获得加班待审批记录集合
	public Object[] findAllOvertimeForSpForYsp(Map map, int pageNo, int pageSize) {
		Map<String, Object> resultMap = CircuitRunServerImpl.findAuditExeNode(
				Overtime.class, true);
		List overtimeListForSpForYsp = null;
		int overtiemIdListSize = 0;
		if (resultMap != null) {
			List<Integer> overtiemIdList = (List<Integer>) resultMap
					.get("entityId");
			overtimeListForSpForYsp = new ArrayList();
			overtiemIdListSize = overtiemIdList.size();
			// 分页处理
			if (pageNo < 1) {
				pageNo = 1;
			}
			int start = (pageNo - 1) * pageSize;
			int end = start + pageSize;
			for (int i = 0; i < overtiemIdListSize; i++) {
				if (i >= start && i < end) {
					Integer overtiemId = overtiemIdList.get(i);
					Overtime overtime = this.getOvertimeById(overtiemId);
					overtimeListForSpForYsp.add(overtime);
				}
			}
		}
		// int count = totalDao.getCount(null);
		Object[] o = { overtimeListForSpForYsp, overtiemIdListSize };
		return o;
	}

	// 获得待确认加班集合
	public Object[] findAllOvertimeForRsForDQr(Map map) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String hql = "from Overtime o where 1 = 1";
		if (map != null) {
			hql += " and o.status = '同意'";
		}
		hql += " order by o.startDate desc";
		List list = totalDao.find(hql);
		// int count = totalDao.getCount(hql);
		Object[] o = { list, 0 };
		return o;
	}

	// 获得已确认加班集合集合
	public Object[] findAllOvertimeForRsForYQr(Map map, int pageNo, int pageSize) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String hql = "from Overtime o where 1 = 1";
		if (map != null) {
			hql += " and o.status = '已确认'";
		}
		hql += " order by o.startDate desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}


	/**
	 * 管理所有的加班记录
	 * 
	 * @return
	 */
	public Object[] findOvertimeListForAll(String startDate,String endDate, Overtime overtime, int pageNo,
			int pageSize, String tag) {
		if (overtime == null) overtime = new Overtime();
		String hql = totalDao.criteriaQueries(overtime, null);
		if (startDate != null && !"".equals(startDate) && endDate != null
				&& !"".equals(endDate) && endDate.compareTo(startDate) >= 0) {
			hql += " and startDate between '" + startDate + "' and '" + endDate
					+ "' or startDate like '"+startDate+"%' or startDate like '"+endDate+"%'";
		}
		if("dai".equals(tag)){
			hql += " and applyId = " + Util.getLoginUser().getId();
		}
 		hql += " order by startDate desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/**
	 * 根据条件导出加班记录
	 * 
	 * @param overtime
	 * @return
	 */
	public List exportExcelOvertimeListForAll(Overtime overtime) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String hql = "from Overtime o where 1 = 1";
		if (overtime != null) {
			String applyName = overtime.getApplyName();
			String applyCode = overtime.getApplyCode();
			String appleDept = overtime.getApplyDept();
			String overtimeName = overtime.getOvertimeName();
			String overtimeCode = overtime.getOvertimeCode();
			String overtimeDept = overtime.getOvertimeDept();
			String startDate = overtime.getStartDate();
			String endDate = overtime.getEndDate();
			String markId = overtime.getMarkId();
			String status = overtime.getStatus();
			if (applyName != null && !"".equals(applyName)) {
				hql += " and o.applyName like '%" + applyName + "%'";
			}
			if (applyCode != null && !"".equals(applyCode)) {
				hql += " and o.applyCode = '" + applyCode + "'";
			}
			if (appleDept != null && !"".equals(appleDept)) {
				hql += " and o.appleDept like '%" + appleDept + "%'";
			}
			if (overtimeName != null && !"".equals(overtimeName)) {
				hql += " and o.overtimeName like '%" + overtimeName + "%'";
			}
			if (overtimeCode != null && !"".equals(overtimeCode)) {
				hql += " and o.overtimeCode like '" + overtimeCode + "'";
			}
			if (overtimeDept != null && !"".equals(overtimeDept)) {
				hql += " and o.overtimeDept like '%" + overtimeDept + "%'";
			}
			if (startDate != null) {
				hql += " and o.startDate >= '"
						+ startDate + "'";
			}
			if (endDate != null) {
				hql += " and o.endDate <= '"
						+ endDate + "'";
			}
			if (markId != null && !"".equals(markId)) {
				hql += " and o.markId like '" + markId + "'";
			}
			if (status != null && !"".equals(status)) {
				hql += " and o.status like '%" + status + "%'";
			}
		}
		hql += " order by o.startDate desc";
		List list = totalDao.find(hql);
		return list;
	}

	@Override
	public String addOvertime1(Overtime overtime) {
		if (overtime != null) {
			// 提交之前把登陆对象存在session会话中
			String hql2 = "from Users where code=?";
			Users loginUser = (Users) totalDao.getObjectByCondition(hql2,
					overtime.getOvertimeCode());
			// 保存用户到session中
			ActionContext.getContext().getSession().put(TotalDao.users,
					loginUser);
		}
		if (totalDao.save(overtime)) {
			return "true";
		} else {
			return "false";
		}

	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	// 查找百分比通过户籍
	public InsuranceGold findInsuranceGoldBylc(String localOrField,
			String cityOrCountryside, String personClass) {
		if (localOrField != null && cityOrCountryside != null) {
			String dateTime = totalDao.getDateTime("yyyy-MM-dd");
			String hql = "from InsuranceGold where localOrField=? and cityOrCountryside=? and personClass=? and validityStartDate<=? and validityEndDate>=?";
			List list = totalDao.query(hql, localOrField, cityOrCountryside,
					personClass, dateTime, dateTime);
			if (list != null && list.size() > 0) {
				return (InsuranceGold) list.get(0);
			}
		}
		return null;
	}
	// 根据工号和卡号查找该用户当前使用的工资信息
	public WageStandard findWSByUser(String code) {
		String hql = "from WageStandard where code=? and standardStatus='默认'";
		List list = totalDao.query(hql, code);
		WageStandard ws = null;
		if (list != null && list.size() > 0) {
			ws = (WageStandard) list.get(0);
		}
		return ws;
	}

	@Override
	public String isbancisc(Integer userId, String startDate, String endDate,
			Integer xiuxi) {
		Users user = (Users) totalDao.get(Users.class, userId);
		if (startDate != null && !"".equals(startDate) && endDate != null
				&& !"".equals(endDate)) {
			if (user.getBanci_id() != null && user.getBanci_id() > 0) {
				BanCi banci = (BanCi) totalDao.get(BanCi.class, user
						.getBanci_id());
				long sc = Util.getWorkTime(startDate, endDate);
				float xiuxizhong = 0f;
				if(xiuxi!=null)
					xiuxizhong = xiuxi/60f;
				float sc_hour = (sc / 1000 / 60 / 60f)-xiuxizhong;
				Float gzTime = banci.getGzTime() / 60f;
				if (gzTime == null || gzTime == 0) {
					gzTime = 8f;
				}
				if (sc_hour > gzTime+0.01) {
					return "申请时长"+sc_hour+"。每次加班时长不能超过" + gzTime + "小时，请重新填写。";
				}
				try {
					String qiyi1 = Util.dayForWeek(startDate,
							"yyyy-MM-dd HH:mm:ss");
					String qiyi2 = Util.dayForWeek(endDate,
							"yyyy-MM-dd HH:mm:ss");
					if (banci.getSbdate().indexOf(qiyi1) == -1
							&& banci.getSbdate().indexOf(qiyi2) == -1) {
						return "true";
					} else if (banci.getSbdate().indexOf(qiyi1) != -1
							&& banci.getSbdate().indexOf(qiyi2) == -1) {
						boolean bool = Util.betweenTime(Util.StringToDate(banci
								.getFirsttime(), "HH:mm:ss"),
								Util.StringToDate(banci.getFinishtime(),
										"HH:mm:ss"), Util.StringToDate(
										startDate.substring(11), "HH:mm:ss"));
						if (bool) {
							return "加班开始时间在正常上班时间内，请重新申请。";
						}
					} else if (banci.getSbdate().indexOf(qiyi1) == -1
							&& banci.getSbdate().indexOf(qiyi2) != -1) {
						boolean bool = Util.betweenTime(Util.StringToDate(banci
								.getFirsttime(), "HH:mm:ss"),
								Util.StringToDate(banci.getFinishtime(),
										"HH:mm:ss"), Util.StringToDate(endDate
										.substring(11), "HH:mm:ss"));
						if (bool) {
							return "加班结束时间在正常上班时间内，请重新申请。";
						}
					} else {
						boolean bool = Util.betweenTime(Util.StringToDate(banci
								.getFirsttime(), "HH:mm:ss"),
								Util.StringToDate(banci.getFinishtime(),
										"HH:mm:ss"), Util.StringToDate(
										startDate.substring(11), "HH:mm:ss"));
						if (bool) {
							return "加班开始时间在正常上班时间内，请重新申请。";
						}
						bool = Util.betweenTime(Util.StringToDate(banci
								.getFirsttime(), "HH:mm:ss"),
								Util.StringToDate(banci.getFinishtime(),
										"HH:mm:ss"), Util.StringToDate(endDate
										.substring(11), "HH:mm:ss"));
						if (bool) {
							return "加班结束时间在正常上班时间内，请重新申请。";
						}
						bool = Util.betweenTime( Util.StringToDate(startDate
								.substring(11), "HH:mm:ss"), Util.StringToDate(endDate
										.substring(11), "HH:mm:ss"),Util.StringToDate(banci
								.getFirsttime(), "HH:mm:ss"));
						if (bool) {
							return "加班时间包含工作时间"+banci
							.getFirsttime()+"-"+banci.getFinishtime()+"，请重新选择。";
						}
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "true";
	}

	@Override
	public String systemOverTime(String date) {
		// TODO Auto-generated method stub
		List<Overtime> list = totalDao.query("from Overtime where startDate like '"+date+"%' and huanxiu = '否' and status = '同意' order by overtimeCode asc");
		if(list!=null&&list.size()>0){
			List list2 = new ArrayList<Overtime>();
			Overtime overtime = null;
			Overtime overtime1 = null;
			Long da = 0l;//小时
			for (int j = 0; j < list.size(); j++) {
				if(j>0){
					overtime1 = list.get(j);
					if(overtime.getOvertimeCode().equals(overtime1.getOvertimeCode())){
						da += Util.getWorkTime1(overtime1.getStartDate(), overtime1.getEndDate())/60000;
					}else {
						overtime.setOverTimeLong(da/60f);
						list2.add(overtime);
						overtime = overtime1;
						da = (Util.getWorkTime1(overtime.getStartDate(), overtime.getEndDate())/60000);
					}
				}else {
					overtime = list.get(j);
					da = Util.getWorkTime1(overtime.getStartDate(), overtime.getEndDate())/60000;
				}
			}
			overtime.setOverTimeLong(da/60f);
			list2.add(overtime);
			try {
				HttpServletResponse response = (HttpServletResponse) ActionContext
						.getContext().get(ServletActionContext.HTTP_RESPONSE);
				OutputStream os = response.getOutputStream();
				response.reset();
				response.setHeader("Content-disposition", "attachment; filename="
						+ new String((date+"加班不换休时长汇总").getBytes("GB2312"), "8859_1") + ".xls");
				response.setContentType("application/msexcel");
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				WritableSheet ws = wwb.createSheet(date+"加班不换休时长汇总", 0);
				ws.setColumnView(1, 15);
				ws.setColumnView(3, 10);
				WritableFont wf = new WritableFont(WritableFont.ARIAL, 14,
						WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
						Colour.BLACK);
				WritableCellFormat wcf = new WritableCellFormat(wf);
				wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
				wcf.setAlignment(Alignment.CENTRE);

				jxl.write.Label label0 = new Label(0, 0, date+"加班不换休时长数据", wcf);
				ws.addCell(label0);
				ws.mergeCells(0, 0, 3, 0);

				wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD,
						false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
				WritableCellFormat wc = new WritableCellFormat(wf);
				wc.setAlignment(Alignment.CENTRE);
				ws.addCell(new jxl.write.Label(0, 1, "序号", wc));
				ws.addCell(new jxl.write.Label(1, 1, "姓名", wc));
				ws.addCell(new jxl.write.Label(2, 1, "工号", wc));
				ws.addCell(new jxl.write.Label(3, 1, "加班时长", wc));

				for (int i = 0; i < list2.size(); i++) {
					Overtime go = (Overtime) list2.get(i);
					ws.addCell(new jxl.write.Number(0, i + 2, i + 1, wc));
					ws.addCell(new Label(1, i + 2, go.getOvertimeName(), wc));
					ws.addCell(new Label(2, i + 2, go.getOvertimeCode(), wc));
					ws.addCell(new jxl.write.Number(3, i + 2, go.getOverTimeLong(), wc));
				}
				wwb.write();
				wwb.close();
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "true";
		}else {
			return "当月无加班不换休信息";
		}
	}

	//加班后补功能
	@Override
	public String backupOvertime(Overtime overtime) {
		Overtime ot = (Overtime) totalDao.getObjectById(Overtime.class, overtime.getId());

		if(ot.getStatus()!=null && ot.getStatus().equals("同意")){
			Integer totalMinutes = 0;
			
			Set<OvertimeDetail> overtimeDetails = overtime.getOvertimeDetails();
			Set<OvertimeDetail> overtimeDetails2 = new HashSet<OvertimeDetail>();
			for (OvertimeDetail overtimeDetail : overtimeDetails) {
				OvertimeDetail detail = (OvertimeDetail) totalDao.getObjectById(OvertimeDetail.class, overtimeDetail.getId());
				
				detail.setOldStart(detail.getStartTime());
				detail.setOldEnd(detail.getEndTime());
				detail.setStartTime(null);
				detail.setEndTime(null);
				totalDao.update(detail);//更新旧的历史记录
				overtimeDetails2.add(detail);
				
				overtimeDetail.setId(null);
				//设置新的记录
				overtimeDetail.setOvertime(ot);
				if(overtimeDetail.getHour()==null) {
					overtimeDetail.setHour(0);
				}
				if(overtimeDetail.getMinutes()==null) {
					overtimeDetail.setMinutes(0);
				}
				totalDao.save(overtimeDetail);
				totalMinutes+=overtimeDetail.getHour()*60+overtimeDetail.getMinutes();
				overtimeDetails2.add(overtimeDetail);
			}
			ot.setOvertimeDetails(overtimeDetails2);
			if(overtime.getOvertimeNeirong()!=null && overtime.getOvertimeNeirong().length()>0) {
				ot.setOvertimeNeirong(overtime.getOvertimeNeirong());
			}
			if(overtime.getShuoming()!=null && overtime.getShuoming().length()>0) {
				ot.setShuoming(overtime.getShuoming());
			}
//			overtime.setOverTimeLong(totalMinutes/60f);//后补审批同意后更新时长！！！
			Users use = (Users) totalDao.getObjectByCondition("from Users where code = ?",ot.getOvertimeCode());
			
			String workFlowMark = "加班审批流程";// 流程标记
			if(use!=null&&("DM".equals(use.getPost())||("DGM".equals(use.getPost())))){
				workFlowMark = "主管加班审批流程";
			}
			try {
//				 CircuitRunServerImpl.createProcess(workFlowMark,
//						Overtime.class, ot.getId(), "actualEpStatus", "id","", null,ot
//						.getOvertimeDept()
//						+ "部门的" + ot.getOvertimeName() + "补加班申请,请您审核!",
//						true,ot.getOvertimeDept());
				Integer epId=CircuitRunServerImpl.createProcess(workFlowMark, Overtime.class, ot.getId(), "actualEpStatus",  "id",
						"overtimeAction!findOvertimeIdByDetail.action?id="+overtime.getId(), ot.getOvertimeDept()
						+ "部门的" + ot.getOvertimeName() + "补加班申请,请您审核!", true, ot.getOvertimeDept());
				ot.setActualEpId(epId);
				ot.setActualEpStatus("未审批");
				CircuitRun circuitRun=(CircuitRun) totalDao.getObjectById(CircuitRun.class, epId);
				if(circuitRun!=null &&circuitRun.getAllStatus()!=null&& circuitRun.getAllStatus().equals("同意")){
					ot.setAccessStatus("同意");
				}
				totalDao.update(ot);
				return "申请成功！";
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}else{
			return "申请加班记录还没有审批同意，不能进行后补";
		}
	}

	
	@Override
	public List<AttendanceTow> getAttendanceTow(Overtime overtime) {
		if(overtime!=null && overtime.getId()!=null) {
			List<OvertimeDetail> list = totalDao.query("from OvertimeDetail where overtime.id=?", overtime.getId());
			overtime = (Overtime) totalDao.getObjectById(Overtime.class, overtime.getId());
			if(list!=null && list.size()>0) {
				StringBuffer buffer = new StringBuffer();
				for (OvertimeDetail overtimeDetail : list) {
					String startTime = overtimeDetail.getStartTime();
					//String endTime = overtimeDetail.getEndTime();
					if(startTime!=null) {
						if(!buffer.toString().equals("")) {
							buffer.append(",");
						}
						buffer.append("'");
						buffer.append(startTime.substring(0,10));
						buffer.append("'");
					}
				}
				
				List<AttendanceTow> attendancelist =totalDao.query("from AttendanceTow where code=? and dateTime in ("
						+buffer.toString()+") order by addTime"
						,overtime.getOvertimeCode());
				return attendancelist;
			}
		}
		return null;
//		overtime = (Overtime) totalDao.getObjectById(Overtime.class, overtime.getId());
//		
//		Calendar calendar = Calendar.getInstance();
//		
//		SimpleDateFormat startformat = new SimpleDateFormat(
//		"yyyy-MM-dd");
//		SimpleDateFormat endformat = new SimpleDateFormat(
//		"yyyy-MM-dd HH:mm:ss");
//		try {
//			calendar.setTime(startformat.parse(overtime.getStartDate()));
//			calendar.add(Calendar.DAY_OF_YEAR, 0);
//			String startDate = startformat.format(calendar.getTime());
//		
//			calendar.setTime(startformat.parse(overtime.getEndDate()));
//			calendar.add(Calendar.DAY_OF_YEAR, 1);
//			String endTime = endformat.format(calendar.getTime().getTime()+1000*60*60*8);
//			
//			List<AttendanceTow> list = totalDao.query("from AttendanceTow where code=? and addTime >= '"+startDate+"' " +
//					" and addTime <= '"+endTime+"' order by addTime",
//					overtime.getOvertimeCode());
//			return list; 
//		} catch (ParseException e) {
//			return null;
//		}
		
	}

	@Override
	public String isbancisc(String usersId, String startDate,
			String endDate, Integer xiuxi) {
		if(usersId==null||"".equals(usersId))
			return "请选择加班人！";
		usersId = usersId.substring(0, usersId.length()-1);
		List<Integer> banciId = totalDao.query("select DISTINCT(banci_id) from Users where id in ("+usersId+")");
		if(banciId==null||banciId.size()<=0)
			return "选择的加班人班次为空！请绑定班次";
		for (Integer integer : banciId) {
			if (startDate != null && !"".equals(startDate) && endDate != null
					&& !"".equals(endDate)) {
				if (integer != null && integer > 0) {
					List<String> nameList = totalDao.query("select name from Users where banci_id = ? and id in ("+usersId+")", integer);
					StringBuffer buffer = new StringBuffer();
					for (String string : nameList) {
						buffer.append(string+",");
					}
					BanCi banci = (BanCi) totalDao.get(BanCi.class, integer);
					if(banci==null)
						return buffer+"班次不存在！请重新绑定班次。";
					long sc = Util.getWorkTime(startDate, endDate);
					float xiuxizhong = 0f;
					if(xiuxi!=null)
						xiuxizhong = xiuxi/60f;
					float sc_hour = (sc / 1000 / 60 / 60f)-xiuxizhong;
					Float gzTime = banci.getGzTime() / 60f;
					if (gzTime == null || gzTime == 0) {
						gzTime = 8f;
					}
					if (sc_hour > gzTime+0.01) {
						return buffer+"申请时长"+sc_hour+"。单次加班时长不能超过" + gzTime + "小时，请重新填写。";
					}
					try {
						String qiyi1 = Util.dayForWeek(startDate,"yyyy-MM-dd HH:mm:ss");//判断开始日期是星期几
						String qiyi2 = Util.dayForWeek(endDate,"yyyy-MM-dd HH:mm:ss");//判断结束日期是星期几
						if (banci.getSbdate().indexOf(qiyi1) == -1
								&& banci.getSbdate().indexOf(qiyi2) == -1) {
							return "true";//不在工作日期
						} else if (banci.getSbdate().indexOf(qiyi1) != -1
								&& banci.getSbdate().indexOf(qiyi2) == -1) {
							boolean bool = Util.betweenTime(Util.StringToDate(banci
									.getFirsttime(), "HH:mm:ss"),
									Util.StringToDate(banci.getFinishtime(),
									"HH:mm:ss"), Util.StringToDate(
											startDate.substring(11), "HH:mm:ss"));
							if (bool) {
								return buffer+"加班开始时间在正常上班时间内，请重新选择。";
							}
						} else if (banci.getSbdate().indexOf(qiyi1) == -1
								&& banci.getSbdate().indexOf(qiyi2) != -1) {
							boolean bool = Util.betweenTime(Util.StringToDate(banci
									.getFirsttime(), "HH:mm:ss"),
									Util.StringToDate(banci.getFinishtime(),
									"HH:mm:ss"), Util.StringToDate(endDate
											.substring(11), "HH:mm:ss"));
							if (bool) {
								return buffer+"加班结束时间在正常上班时间内，请重新选择。";
							}
						} else {
							boolean bool = Util.betweenTime(Util.StringToDate(banci
									.getFirsttime(), "HH:mm:ss"),
									Util.StringToDate(banci.getFinishtime(),
									"HH:mm:ss"), Util.StringToDate(
											startDate.substring(11), "HH:mm:ss"));
							if (bool) {
								return buffer+"加班开始时间在正常上班时间内，请重新选择。";
							}
							bool = Util.betweenTime(Util.StringToDate(banci
									.getFirsttime(), "HH:mm:ss"),
									Util.StringToDate(banci.getFinishtime(),
									"HH:mm:ss"), Util.StringToDate(endDate
											.substring(11), "HH:mm:ss"));
							if (bool) {
								return buffer+"加班结束时间在正常上班时间内，请重新选择。";
							}
							bool = Util.betweenTime( Util.StringToDate(startDate
									.substring(11), "HH:mm:ss"), Util.StringToDate(endDate
											.substring(11), "HH:mm:ss"),Util.StringToDate(banci
									.getFirsttime(), "HH:mm:ss"));
							if (bool) {
								return buffer+"加班时间包含工作时间"+banci
								.getFirsttime()+"-"+banci.getFinishtime()+"，请重新选择。";
							}
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else {
				return "开始或结束时间不能为空";
			}
		}
		return "true";
	}

	@Override
	public List<OvertimeDetail> findOvertimeIdByDetail(Integer id,String pageStatus) {
		String hql = "from OvertimeDetail where overtime.id=?";
		if(pageStatus==null || pageStatus.equals("new")) {
			hql+=" and startTime is not null and endTime is not null";
		}else {
			hql+=" and startTime is null and endTime is null";

		}
		@SuppressWarnings("rawtypes")
		List list = totalDao.query(hql, id);
		return list;
	}

	@Override
	public Integer checkOverTimeDetailByuserId(OvertimeDetail detail, Integer usersId) {
		String sql = "from OvertimeDetail where ((startTime between ? and ? ) or (endDate between ? and ? ))"
				+ " and overtime.overtimeId=? and overtime.status is not null and overtime.status <> '打回' ";
		Integer count = totalDao.getCount(sql, detail.getStartTime(),detail.getEndTime(),
				detail.getStartTime(),detail.getEndTime(),usersId);
		return count;
	}

}
