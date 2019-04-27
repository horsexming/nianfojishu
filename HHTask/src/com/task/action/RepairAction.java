package com.task.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.RepairService;
import com.task.Server.zgkh.AssessPersonnelServer;
import com.task.entity.DeptNumber;
import com.task.entity.Maintenance;
import com.task.entity.Repair;
import com.task.entity.Requisition;
import com.task.entity.Responsibilities;
import com.task.entity.Users;
import com.task.entity.gzbj.Gzstore;
import com.task.entity.repair.RepairType;
import com.task.entity.zgkh.AssessPersonnel;
import com.task.util.MKUtil;
import com.task.util.Util;

public class RepairAction extends ActionSupport {
	private RepairService repairService;
	private Repair repair;
	private Repair repair2;
	private String successMessage;// 成功消息
	private String errorMessage;// 成功消息
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private int id;
	private String pageStatus;
	private List<Repair> repairList;
	private List<Repair> repairList1;
	private List<Repair> repairList2;
	private List<Repair> repairList3;
	private List<Repair> repairList4;
	private List<Repair> reList;
	private List list;
	private int sum;
	private int summ;
	private int sumxiufu;
	private float sumli;
	private String  date1;
	private String  date2;
	private Responsibilities responsibilities;
	private List<Map> maps ;
	private AssessPersonnelServer assessPersonnelServer;// Server层
	private String groupName;
	private String employeenumber;
	private String repairname;
	private Integer i;
	private Integer del_id;
	private String deptname;
	
	private RepairType repairType;
	private String repairdepartment;
	private String category;
	
	private String name1;
	private String test;
	private String text;
	private String t;
	
	
	

	
	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public Repair getRepair2() {
		return repair2;
	}

	public void setRepair2(Repair repair2) {
		this.repair2 = repair2;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getRepairdepartment() {
		return repairdepartment;
	}

	public void setRepairdepartment(String repairdepartment) {
		this.repairdepartment = repairdepartment;
	}

	public RepairType getRepairType() {
		return repairType;
	}

	public void setRepairType(RepairType repairType) {
		this.repairType = repairType;
	}

	public Integer getDel_id() {
		return del_id;
	}

	public void setDel_id(Integer delId) {
		del_id = delId;
	}

	public RepairService getRepairService() {
		return repairService;
	}

	public Repair getRepair() {
		return repair;
	}

	public void setRepairService(RepairService repairService) {
		this.repairService = repairService;
	}

	public void setRepair(Repair repair) {
		this.repair = repair;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getCpage() {
		return cpage;
	}

	public String getTotal() {
		return total;
	}

	public String getUrl() {
		return url;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public List<Repair> getRepairList() {
		return repairList;
	}

	public List<Repair> getRepairList1() {
		return repairList1;
	}

	public List<Repair> getRepairList2() {
		return repairList2;
	}

	public List<Repair> getRepairList3() {
		return repairList3;
	}

	public List<Repair> getReList() {
		return reList;
	}

	public void setRepairList(List<Repair> repairList) {
		this.repairList = repairList;
	}

	public void setRepairList1(List<Repair> repairList1) {
		this.repairList1 = repairList1;
	}

	public void setRepairList2(List<Repair> repairList2) {
		this.repairList2 = repairList2;
	}

	public void setRepairList3(List<Repair> repairList3) {
		this.repairList3 = repairList3;
	}

	public void setReList(List<Repair> reList) {
		this.reList = reList;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<Repair> getRepairList4() {
		return repairList4;
	}

	public List getList() {
		return list;
	}

	public void setRepairList4(List<Repair> repairList4) {
		this.repairList4 = repairList4;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getSumm() {
		return summ;
	}

	public void setSumm(int summ) {
		this.summ = summ;
	}

	public int getSumxiufu() {
		return sumxiufu;
	}

	public void setSumxiufu(int sumxiufu) {
		this.sumxiufu = sumxiufu;
	}

	public float getSumli() {
		return sumli;
	}

	public void setSumli(float sumli) {
		this.sumli = sumli;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

	
	
	public Integer getI() {
		return i;
	}

	public void setI(Integer i) {
		this.i = i;
	}

	public String getEmployeenumber() {
		return employeenumber;
	}

	public void setEmployeenumber(String employeenumber) {
		this.employeenumber = employeenumber;
	}

	public String getRepairname() {
		return repairname;
	}

	public void setRepairname(String repairname) {
		this.repairname = repairname;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public AssessPersonnelServer getAssessPersonnelServer() {
		return assessPersonnelServer;
	}

	public void setAssessPersonnelServer(AssessPersonnelServer assessPersonnelServer) {
		this.assessPersonnelServer = assessPersonnelServer;
	}

	public List<Map> getMaps() {
		return maps;
	}

	public void setMaps(List<Map> maps) {
		this.maps = maps;
	}

	public Responsibilities getResponsibilities() {
		return responsibilities;
	}

	public void setResponsibilities(Responsibilities responsibilities) {
		this.responsibilities = responsibilities;
	}

	// 添加
	public String addrepairsingle() {

		boolean bool = repairService.add(repair);

		return "addrepairsingle";

	}
	
	//查询所有保修类别
//	public String add
	
	// 设备故障修复率

	public String findsum() {
		sum = repairService.findAlls(date1, date2);
		sumxiufu = repairService.findAllss(date1, date2);
		sumli = Float.parseFloat(String.format("%.2f", sumxiufu * 0.1F / sum
				* 1000));

		return "findAlls";

	}
	// 修改
	public String initupRepair() {
		repair = repairService.findAssetById(id);
		if (pageStatus != null && pageStatus.length() > 0) {
			if (pageStatus.equals("zhipai") || pageStatus.equals("daizhipai")) {
				if ("待确定".equals(repair.getStatus())
						|| "待指派".equals(repair.getStatus())) {
					list = repairService.selectPeopleForZhipai(repair
							.getCategory());
					this.test="1";
					return "remark";
				}
			}
		}
		return "updaterepair";
	}

	public String updaterepair() {
		repairService.update(repair);
		return "updaterrepair";

	}

	// 明细
	public String findByclientManagement() {
		repair = repairService.findAssetById(id);
		return "findByclientManagement";

	}

	public String findrepair() {
		repairService.update(repair);
		return "findrepair";

	}

	// 删除
	public String delSubmit() {
		repair = repairService.findAssetById(id);
		repairService.delete(repair);
		return "delsubmit";

	}

	// 修复反馈
	public String remarkRepair() {
		repair = repairService.findAssetById(id);
		this.maps = this.repairService.findDept();
		this.test="2";
		return "remark";

	}

	public String upremarkRepair() {
		Repair oldrepair = repairService.findAssetById(id);
		if (oldrepair != null) {
			if ("待确定".equals(oldrepair.getStatus())
					|| "待指派".equals(oldrepair.getStatus())) {
				oldrepair.setPersonalnominee(repair.getPersonalnominee());
				oldrepair.setStatus("待确定");
				repairService.update(oldrepair);
				pageStatus = "daizhipai";
				errorMessage = "zhipai";
				return "updateZhipai";
			} else if ("修复确认".equals(oldrepair.getStatus())) {
				oldrepair.setRepairpersontime(new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss").format(new Date()));
				oldrepair.setStatus("修复完成");
				repairService.update(oldrepair);
				return "addrepairsingle";
			} else {
				if("1".equals(text)){
					boolean bool = repairService.add(repair2);
				}
				oldrepair.setTimetorepair(new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss").format(new Date()));
				oldrepair.setRepairfeedback(repair.getRepairfeedback());
				oldrepair.setCountdowntime(120);
				oldrepair.setFk_id(repair2.getId());//标识Id
				oldrepair.setStatus("修复确认");
			}
			repairService.update(oldrepair);
		}
		
		if("1".equals(text)){
			return	"addrepairsingle";
		}else{
			return "updatecondition";
		}
		

	}

	// 条件查询
	public String condition() {
		repair = repairService.findAssetById(id);
		if (repair != null) {
			if (pageStatus != null && pageStatus.length() > 0) {
				if ("daiqueding".equals(pageStatus)) {
					repair.setStatus("维修中");
				} else if ("weixiuzhong".equals(pageStatus)) {
					repair.setStatus("修复完成");

				} else {
					errorMessage = "参数异常!请检查后重试!";
					return ERROR;
				}
				repairService.update(repair);
				return "updatecondition";

			} else {
				errorMessage = "不存在您要查询的数据!";
			}
		}
		return ERROR;

	}

	// 查询所有
	@SuppressWarnings("unchecked")
	public String findAll() {
		if (repair != null) {
			ActionContext.getContext().getSession().put("repair", repair);

		} else {
			repair = (Repair) ActionContext.getContext().getSession().get(
					"repair");
		}
		Object[] object = null;
		if (pageStatus != null && pageStatus.equals("null")) {
			pageStatus = null;
		}
		if (errorMessage != null && errorMessage.equals("null")) {
			errorMessage = null;
		}
		if (pageStatus == null) {
			repairList1 = repairService.findAll("待确定");
			repairList2 = repairService.findAll("维修中");
			repairList3 = repairService.findAll("待指派");
			repairList4 = repairService.findAll("修复确认");
			if(repair.getStatus()==null||repair.getStatus().length()<=0){
			repair.setStatus("修复完成");
			}
			object = repairService.findAll(repair, Integer.parseInt(cpage),
					pageSize, pageStatus);

		} else if (pageStatus != null && "findAll".equals(pageStatus)) {
			repairList1 = repairService.findAllByStatus("待确定");
			repairList2 = repairService.findAllByStatus("维修中");
			repairList3 = repairService.findAllByStatus("待指派");
			repairList4 = repairService.findAllByStatus("修复确认");
			repair.setStatus("修复完成");
			object = repairService.findAll(repair, Integer.parseInt(cpage),
					pageSize, pageStatus);

		} else {
			if ("daiqueding".equals(pageStatus)) {
				object = repairService.findAll(repair, 0, 0, "weixiuzhong");//
				repairList1 = (List<Repair>) object[0];
			}
			if ("daizhipai".equals(pageStatus)) {
				if (errorMessage != null && "zhipai".equals(errorMessage)) {
					repairList1 = repairService.findAllByStatus("待确定");
					repair = new Repair();
					repair.setStatus("待指派");
				} else {
					object = repairService.findAll(repair, 0, 0, "daiqueding");// 
					repairList1 = (List<Repair>) object[0];
				}
			}
			object = repairService.findAll(repair, Integer.parseInt(cpage),
					pageSize, pageStatus);

		}

		if (object != null && object.length > 0) {
			repairList = (List<Repair>) object[0];
			if (repairList != null && repairList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("RepairAction!findAll.action?pageStatus="
						+ pageStatus + "&errorMessage=" + errorMessage);
			}
		}

		if (pageStatus != null && "findAll".equals(pageStatus)
				|| "findByCon".equals(pageStatus)) {
			return "findAllRe";
		}

		return "findAll";

	}

	// 查询所有部门为页面Select使用
	public String finAllPeople() throws Exception {
		pageStatus = URLDecoder.decode(pageStatus, "Utf-8");
		String message = repairService.selectpeople(pageStatus);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(message);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ERROR;
	}
	
	/******************报修人信息模块****************************/
	
	//查询所有修理人信息
	public String findAllpop() throws Exception{
		if (responsibilities != null) {
			ActionContext.getContext().getSession().put("responsibilities", responsibilities);
		} else {
			responsibilities = (Responsibilities) ActionContext.getContext().getSession().get("responsibilities");
		}
		Object[] object = null;
		if("0".equals(text) || "0".equals(t)){
			this.test = "0";
			object = repairService.findAllpop(responsibilities,Integer.parseInt(cpage), pageSize,test);
			this.setUrl("RepairAction!findAllpop.action?text="+text);
		}else{
			this.test = "1";
			object = repairService.findAllpop(responsibilities,Integer.parseInt(cpage), pageSize,test);
			this.setUrl("RepairAction!findAllpop.action");
		}
		Users loginUser=Util.getLoginUser();//获得登陆用户
		 this.deptname = loginUser.getDept();
//		 MKUtil.writeJSON(true, "",deptname );//把结果传到页面
		if (object != null && object.length > 0) {
			maps = (List<Map>) object[0];
			if (maps != null && maps.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "findAllpop";
	}
	

	//添加修理人信息
	public String addRepairpop(){
		repairService.saveRepair(this.responsibilities);
		this.errorMessage="添加成功!";
		return "addRepairpop";
	}
	
	//对部门和类别进行下拉级联
	 public String isChange(){
		 this.maps = this.repairService.findRepairByName(this.repairdepartment);
		 for (int i = 0; i < maps.size(); i++) {
			 String category =  (String) maps.get(i).get("category");
		
		}
		 	 MKUtil.writeJSON(true, "",this.maps );//把结果传到页面
		 return null;
	 }
	
	 //对类别下面的人员进行下拉
	 public String isChange1(){
		 if(!"".equals(repairdepartment)){
			 this.maps = this.repairService.findRepairByName1(this.category,this.repairdepartment);
			 for (int i = 0; i < maps.size(); i++) {
				 String repairname =  (String) maps.get(i).get("repairname");
			}
			 	 MKUtil.writeJSON(true, "",this.maps );//把结果传到页面
		 }
		 
		
		 return null;
	 }
	
	//校验工号、姓名是否存在
	@SuppressWarnings("unchecked")
	public String isOK(){
		this.i = repairService.findcadeandname(employeenumber,repairname);
		if(i==0){
			this.errorMessage="1";
		}else{
			this.errorMessage="2";
		}
		MKUtil.writeJSON(true, errorMessage,null);//把结果传到页面
		return null;
	}
	
	//删除修理人信息
	public String delRepairpop(){
		repairService.deleterepair(this.del_id);
		if("0".equals(t)){
			return "delRepair1";	
		}else{
			return "delRepair";	
		}
	}
	
	//根据编号查询
	public String salRepairpop(){
		this.responsibilities = repairService.findRepairpopById(this.del_id);
		if("0".equals(t)){
			this.text="0";
		}
		return  "salRepairpop";
	}
	
	//修改报修人信息
	public String updateRepairpop(){
		this.repairService.updateRepair(responsibilities);
		if(t!=null){
			return "toupdateRepair";
		}else{
			return "toupdateRepair1";	
		}
	}
	
/***********************报修类别管理*******************************/	
	//针对每个部门的类别进行管理
	public String findRepairtype(){
		if (this.repairType != null) {
			ActionContext.getContext().getSession().put("repairType", repairType);
		} else {
			repairType = (RepairType) ActionContext.getContext().getSession().get("repairType");
		}
		Object[] object = repairService.findRepairtype(repairType,Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			maps = (List<Map>) object[0];
			if (maps != null && maps.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("RepairAction!findRepairtype.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "findRepairtype";
		
	}
	
	//对本部门的类别进行管理
	public String findRepairtype1(){
		if (this.repairType != null) {
			ActionContext.getContext().getSession().put("repairType", repairType);
		} else {
			repairType = (RepairType) ActionContext.getContext().getSession().get("repairType");
		}
		Object[] object = repairService.findRepairtype1(repairType,Integer.parseInt(cpage), pageSize);
		Users loginUser=Util.getLoginUser();//获得登陆用户
		 this.deptname = loginUser.getDept();
		 this.name1 = "geti";
		if (object != null && object.length > 0) {
			maps = (List<Map>) object[0];
			if (maps != null && maps.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("RepairAction!findRepairtype.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "findRepairtype";
		
	}
	
	//添加报修类型
	public String saveRepairtype(){
		if(test!=null){
			repairService.saveRepairtype(this.repairType);
			//返回查询单个部门对应的类型
			return "saveRepairtype1";
		}else{
			repairService.saveRepairtype(this.repairType);
			//查询所有部门对应的类型
			return "saveRepairtype";
		}
	}
	
	//删除报修类型
	public String delRepairtype(){
		if(test!=null){
			repairService.delRepairtype(this.del_id);
			//返回查询单个部门对应的类型
			return "delRepairtype1";
		}else{
			repairService.delRepairtype(this.del_id);
			//查询所有部门对应的类型
			return "delRepairtype";
		}
		
	}

	
	//根据编号查询
	public String salRepairtypeById(){
		//返回查询单个部门对应的类型
		if(test!=null){
			repairType = this.repairService.findRepairtypeById(this.del_id);
			this.name1="geti";
			return "salRepairtypeById";
			
		}else{
			//查询所有部门对应的类型
			repairType = this.repairService.findRepairtypeById(this.del_id);
			return "salRepairtypeById";
		}
		
	}
	
	
	//修改报修类型
	public String updateRepairtype(){
		if(test!=null){
			this.repairService.updateRepairtype(this.repairType);
			return "updateRepairtype1";
		}else{
			this.repairService.updateRepairtype(this.repairType);
			return "updateRepairtype";
		}
		
	}

}
