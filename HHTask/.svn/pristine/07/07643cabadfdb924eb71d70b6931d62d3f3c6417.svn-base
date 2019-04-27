package com.task.action.kvp;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.kvp.KVPAssessServer;
import com.task.entity.Users;
import com.task.entity.kvp.EightBReport;
import com.task.entity.kvp.ExecuteKVP;
import com.task.entity.kvp.ImproveKVP;
import com.task.entity.kvp.KVPAssess;
import com.task.util.MKUtil;

public class KVPAssessAction extends ActionSupport{
	private KVPAssessServer kvpAssessServer;
	private KVPAssess kvpAssess;
	private ImproveKVP improveKVP;
	private ExecuteKVP executeKVP;
	private EightBReport eightBReport;
	private List<ImproveKVP> improveKVPList;
	private File image_Path;
	private String addressFileName;
	private String addressContextType;
	private List<Map> maps;
	private List<Map> maps1;
	private List list;
	
	private String successMessage;
	private String errorMessage;
	
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	private String tag;
	private Integer[] detailSelect;// 选择补打数组,审批数组
	private Integer id;
	private String dept;
	private String name;
	private String test;
	private Float costsavings;
	private String tag1;

	
	
	//添加产品评估
	public void addKVPAssess(){
		Map<Integer,Object> map = this.kvpAssessServer.saveKVPAssess(this.kvpAssess);
		boolean bool=Boolean.parseBoolean(map.get(1).toString());
		Integer kId=null;
		if(map.get(2)!=null){
			kId=Integer.parseInt(map.get(2).toString());
		}
		MKUtil.writeJSON(bool,null,kId);
	}
	//查询kvp产品审核
	public String findExamList() {
		Object[] obj = kvpAssessServer.findExamList(Integer.parseInt(cpage),pageSize);
		if (obj != null && obj.length > 1) {
			list = (List) obj[1];
			int count = (Integer) obj[0];
			this.setUrl("kvpAssessAction_findExamList.action");
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
		}
		return "findkvpassess";
	}
	
	//kvp产品审批(通过、驳回)
	public String updateExamKVP(){
		try {
			if (kvpAssessServer.updateExamBonus(detailSelect, tag)) {
				return "updateExamKVP";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		errorMessage = "审批失败!请检查数据有效性!";
		return ERROR;
	}
	
	//查看kvp产品评估
	public String findKVPAssess(){
		if (kvpAssess != null) {
			ActionContext.getContext().getSession().put("kvpAssess", kvpAssess);
		} else {
			kvpAssess = (KVPAssess) ActionContext.getContext().getSession().get(
					"kvpAssess");
		}
		Object[] object = kvpAssessServer.findBargain(kvpAssess,Integer
				.parseInt(cpage), pageSize,test);
		if (object != null && object.length > 0) {
			maps = (List<Map>) object[0];
			if (maps != null && maps.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("kvpAssessAction_findKVPAssess.action?test="+test);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "findKVPAssess1";
	}
	
	//根据编号查询kvp产品评估
	public String toupdateKVPAssess(){
		 this.kvpAssess = this.kvpAssessServer.findKVPAssessById(this.id);
		return "updateKVPAssess";
	}
	
	//修改kvp产品评估
	public  void doupdateKVPAssess(){
		boolean bool = this.kvpAssessServer.updateKVPAssess(kvpAssess);
		MKUtil.writeJSON(bool);
//		if(bool){
//			successMessage="修改成功!";
//		}else{
//			successMessage="修改失败!";
//		}
//		return "doupdateKVPAssess";
	}
	
	//删除kvp产品评估
	public String delKVPAssess(){
		 kvpAssessServer.delKVPAssess(this.id);
		return "delKVPAssess";
	}
	
	//根据编号查询kvp产品评估
	public String addExecuteKVP(){
		//根据编号查询kvp产品评估
		 this.kvpAssess = this.kvpAssessServer.findKVPAssessById(this.id);
		return "addExecuteKVP";
	}
	
	//根据编号查询kvp项目执行单
	public String findExecuteKVPById(){
		this.kvpAssess = this.kvpAssessServer.findKVPAssessById(this.id);
		 this.executeKVP = this.kvpAssessServer.findExecuteKVPById(this.id);
		 if(tag!=null&&!"".equals(tag)){
			 return "findExecuteKVPById1";
		 }else{
			 return "findExecuteKVPById";
		 }
	}
	
	//填写项目执行单
	public void saveExecuteKVP(){
		boolean bool =kvpAssessServer.saveExecuteKVP(this.kvpAssess,this.executeKVP);
		MKUtil.writeJSON(bool);
	}
	
	//查询所有部门编码
	public void selectDept(){
		list = kvpAssessServer.selectDept();
		MKUtil.writeJSON(list);
	}
	
	//通过部门查询员工
	public void selectUser(){
		list = kvpAssessServer.selectUser(dept);
		MKUtil.writeJSON(list);
	}
	//通过对应的员工编号
	public void selectUserCode(){
		String[] strs = name.split(",");
		String str1 = "";
		for (int i = 0; i < strs.length; i++) {
			if(strs[i]!=null&&!"".equals(strs[i])){
				Users users = kvpAssessServer.selectUserCode(strs[i]);
				if(str1==""&&"".equals(str1)){
					str1 = users.getCode();
				}else{
					str1=str1+","+users.getCode();
				}
			}
		}
		MKUtil.writeJSON(str1);
	}
	
	//修改执行单
	public void updateExecuteKVP(){
		boolean bool = this.kvpAssessServer.updateExecuteKVP(executeKVP);
		MKUtil.writeJSON(bool);
	}
	
	//查询项目执行单审核
	public String findExamList1() {
		Object[] obj = kvpAssessServer.findExamList1(Integer.parseInt(cpage),pageSize);
		if (obj != null && obj.length > 1) {
			list = (List) obj[1];
			int count = (Integer) obj[0];
			this.setUrl("kvpAssessAction_findExamList1.action");
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
		}
		return "findExamExecuteKVP";
	}
	
	//kvp项目执行单(通过、驳回)
	public String updateExamExecuteKVP(){
		try {
			if (kvpAssessServer.updateExamExecuteKVP(detailSelect, tag)) {
				return "updateExamExecuteKVP";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		errorMessage = "审批失败!请检查数据有效性!";
		return ERROR;
	}
	
	//查询所有项目执行单
	public String findEightBReport(){
		if (executeKVP != null) {
			ActionContext.getContext().getSession().put("executeKVP", executeKVP);
		} else {
			executeKVP = (ExecuteKVP) ActionContext.getContext().getSession().get(
					"executeKVP");
		}
		Object[] object = kvpAssessServer.findEightBReport(executeKVP,Integer
				.parseInt(cpage), pageSize,tag);
		if (object != null && object.length > 0) {
			maps = (List<Map>) object[0];
			if (maps != null && maps.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("kvpAssessAction_findEightBReport.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "findEightBReport";
	}
	
	//添加8B报告
	public String addEightBReport(){
		boolean bool = kvpAssessServer.addEightBReport(this.eightBReport,id);
		if(bool){
			this.successMessage="提交成功!";
		}else{
			this.successMessage="提交失败!";
		}
		return "addEightBReport";
	}
	
	//查询8B报告审核
	public String findExamEightBReport() {
		Object[] obj = kvpAssessServer.findExamEightBReport(Integer.parseInt(cpage),pageSize);
		if (obj != null && obj.length > 1) {
			list = (List) obj[1];
			int count = (Integer) obj[0];
			this.setUrl("kvpAssessAction_findExamEightBReport.action");
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
		}
		return "findExamEightBReport";
	}
	
	//审批(通过、驳回)
	public String updateExamEightBReport(){
		try {
			if (kvpAssessServer.updateExamEightBReport(detailSelect, tag)) {
				return "updateExamEightBReport";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		errorMessage = "审批失败!请检查数据有效性!";
		return ERROR;
	}
	
	//根据编号查询8B报告单
	public String findEightBReportById(){
		System.out.println(tag);
		 this.eightBReport = this.kvpAssessServer.findEightBReportById(this.id);
		 this.executeKVP = this.kvpAssessServer.findExecuteKVPById1(this.id);
		 if(tag1!=null){
			 return "findPrintEightBReport";
		 }else{
			 return "findEightBReportById";
		 }
	}
	
	//更新8B报告
	public String updateEightBReport(){
		boolean bool = kvpAssessServer.updateEightBReport(this.eightBReport,costsavings);
		if(bool){
			this.successMessage="更新成功!";
		}else{
			this.successMessage="更新失败!";
		}
		return "updateEightBReport";
	}
	
	//删除项目执行单
	public String delEightBReport(){
		boolean bool = kvpAssessServer.delEightBReport(id);
		if(bool){
			return "delEightBReport";
		}else{
			return ERROR;
		}
		
	}


	public KVPAssessServer getKvpAssessServer() {
		return kvpAssessServer;
	}
	public void setKvpAssessServer(KVPAssessServer kvpAssessServer) {
		this.kvpAssessServer = kvpAssessServer;
	}
	public KVPAssess getKvpAssess() {
		return kvpAssess;
	}
	public void setKvpAssess(KVPAssess kvpAssess) {
		this.kvpAssess = kvpAssess;
	}

	public ImproveKVP getImproveKVP() {
		return improveKVP;
	}

	public void setImproveKVP(ImproveKVP improveKVP) {
		this.improveKVP = improveKVP;
	}

	public String getAddressFileName() {
		return addressFileName;
	}

	public void setAddressFileName(String addressFileName) {
		this.addressFileName = addressFileName;
	}

	public String getAddressContextType() {
		return addressContextType;
	}

	public void setAddressContextType(String addressContextType) {
		this.addressContextType = addressContextType;
	}

	public void setImage_Path(File imagePath) {
		image_Path = imagePath;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public List<ImproveKVP> getImproveKVPList() {
		return improveKVPList;
	}


	public void setImproveKVPList(List<ImproveKVP> improveKVPList) {
		this.improveKVPList = improveKVPList;
	}


	public File getImage_Path() {
		return image_Path;
	}
	public List<Map> getMaps() {
		return maps;
	}
	public void setMaps(List<Map> maps) {
		this.maps = maps;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public String getCpage() {
		return cpage;
	}
	public void setCpage(String cpage) {
		this.cpage = cpage;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Integer[] getDetailSelect() {
		return detailSelect;
	}
	public void setDetailSelect(Integer[] detailSelect) {
		this.detailSelect = detailSelect;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ExecuteKVP getExecuteKVP() {
		return executeKVP;
	}
	public void setExecuteKVP(ExecuteKVP executeKVP) {
		this.executeKVP = executeKVP;
	}
	public List<Map> getMaps1() {
		return maps1;
	}
	public void setMaps1(List<Map> maps1) {
		this.maps1 = maps1;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public EightBReport getEightBReport() {
		return eightBReport;
	}
	public void setEightBReport(EightBReport eightBReport) {
		this.eightBReport = eightBReport;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public Float getCostsavings() {
		return costsavings;
	}
	public void setCostsavings(Float costsavings) {
		this.costsavings = costsavings;
	}
	public String getTag1() {
		return tag1;
	}
	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}
	

}
