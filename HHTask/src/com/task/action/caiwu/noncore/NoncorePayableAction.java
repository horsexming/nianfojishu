package com.task.action.caiwu.noncore;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.caiwu.noncore.NoncorePaybaleServer;
import com.task.entity.Users;
import com.task.entity.caiwu.noncore.EnergyConsumption;
import com.task.entity.caiwu.noncore.NonCorePayable;
import com.task.entity.caiwu.noncore.PayableType;
import com.task.util.MKUtil;
import com.task.util.Upload;
import com.task.util.Util;
/**
 * 非主营业务应付Action
 * @addTime 2017-05-04
 * @author licong
 *
 */
@SuppressWarnings("unchecked")
public class NoncorePayableAction extends ActionSupport {
	private NoncorePaybaleServer noncorePaybaleServer;
	private NonCorePayable corePayable;
	private PayableType payableType;
	private EnergyConsumption energyConsumption;
	private List<NonCorePayable> corePayableList;
	private List<PayableType> payableTypeList;
	private List<PayableType> yiBList;
	private List<PayableType> weiBList;
	private List<EnergyConsumption> energyConsumptionList;
	private String tag;
	private Integer count;
	int[] checkboxs;
	private String message;
	private List list;
	private List listType;//存放位置
	private Users users;//存放位置
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private Integer id;// 主键
	private String startDate;// 开始时间
	private String endDate;// 截止时间
	private String errorMessage;// 错误消息
	private String successMessage;// 错误消息
	// 上传文件对象
	private File fujian;// 合同
	// 上传文件名
	private String fujianFileName;
	// 上传文件类型
	private String fujianContentType;
	/**************************应付信息管理****************************/
	public String toaddType() {
		payableTypeList = noncorePaybaleServer.findPayable();
		return "payableType_add";
	}
	
	public String addType() {
		errorMessage = noncorePaybaleServer.savePayableType(payableType);
		if ("添加成功！".equals(errorMessage))
			url = "NoncorePayableAction!toaddType.action";
		return "error";
	}
	
	public String findSelectName(){
		String message = noncorePaybaleServer.findPayableType();
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(message);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "error";
	}
	
	/**********************应付总表*************************/
	
	
	public String toadd() {
		return "nonCorePayable_add";
	}
	
	public String addPayable() {
		if (fujian != null) {
			// 文件路径
			String realFileName = Util.getDateTime("yyyyMMddHHmmss");
			// 打开存放文件的位置
			String realFilePath = "/upload/file/feiZY";
			String path = ServletActionContext.getServletContext()
					.getRealPath(realFilePath);
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();// 如果不存在文件夹就新建
			}
			Upload upload = new Upload();// 文件上传工具类
			realFileName = upload.UploadFile(fujian, fujianFileName,
					null, realFilePath, null);
			corePayable.setFujian(
					realFileName);// 文件新名称
		}
		errorMessage = noncorePaybaleServer.saveNoncorePayable(corePayable);
		if ("添加成功！".equals(errorMessage))
			url = "NoncorePayableAction!showList.action?tag="+tag;
		return "error";
	}
	
	// 分页显示
	// 显示查询内容
	public String showList() {
		if (corePayable != null) {
			ActionContext.getContext().getSession().put("NonCorePayable",
					corePayable);
		} else {
			corePayable = (NonCorePayable) ActionContext
					.getContext().getSession().get("NonCorePayable");
		}
		Object[] object = noncorePaybaleServer.findNoncorePayableList(
				corePayable, startDate, endDate,
				Integer.parseInt(cpage), pageSize, tag);
		if (object != null && object.length > 0) {
			corePayableList = (List<NonCorePayable>) object[0];
			if (corePayableList != null
					&& corePayableList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("NoncorePayableAction!showList.action?tag=" + tag);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "findAllNoncorePayable";
	}
	
	// 跳转到修改页面的方法
	public String toupdate() {
		if (corePayable!=null && corePayable.getId() != null && corePayable.getId() > 0) {
			corePayable = noncorePaybaleServer.getNoncroePayableById(corePayable.getId());
			if (corePayable != null)
				return "nonCorePayable_update";
		}
		errorMessage = "数据为空!请检查";
		return "error";
		
	}
	// 跳转到查看页面的方法
	public String toselect() {
		if (corePayable!=null && corePayable.getId() != null && corePayable.getId() > 0) {
			corePayable = noncorePaybaleServer.getNoncroePayableById(corePayable.getId());
			if (corePayable != null)
				return "nonCorePayable_select";
		}
		errorMessage = "数据为空!请检查";
		return "error";
		
	}
	
	//修改方法
	public String update() {
		if(corePayable!=null){
			if (fujian != null) {
				// 文件路径
				String realFileName = Util.getDateTime("yyyyMMddHHmmss");
				// 打开存放文件的位置
				String realFilePath = "/upload/file/feiZY";
				String path = ServletActionContext.getServletContext()
						.getRealPath(realFilePath);
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();// 如果不存在文件夹就新建
				}
				Upload upload = new Upload();// 文件上传工具类

				// 删除原文件
				NonCorePayable corePayable1 = noncorePaybaleServer
						.getNoncroePayableById(corePayable.getId());
				if (corePayable1.getFujian() != null) {
					File oldFile = new File(ServletActionContext
							.getServletContext().getRealPath("")
							+ "/upload/file/feiZY/" + corePayable1.getFujian());
					if (oldFile.exists()) {
						oldFile.delete();
					}
				}
				realFileName = upload.UploadFile(fujian, fujianFileName,
						null, realFilePath, null);
				corePayable.setFujian(
						realFileName);// 文件新名称
			}
			errorMessage = noncorePaybaleServer.updateNoncore(corePayable);
			if ("修改成功！".equals(errorMessage))
				url = "NoncorePayableAction!showList.action?tag="+tag;
		}
		return "error";
	}
	
	public String delete(){
		errorMessage = noncorePaybaleServer.deleteNoncorePayable(id);
		if("删除成功！".equals(errorMessage)){
			url = "NoncorePayableAction!showList.action?tag="+tag;
		}
		return "error";
	}
	
	public String querenPay(){
		errorMessage = noncorePaybaleServer.querenNoncore(corePayable);
		if ("确认成功！".equals(errorMessage))
			url = "NoncorePayableAction!showList.action?tag="+tag;
		return "error";
	}
	
	/**
	 * 查看人员绑定应付类型
	 */
	public String userBangpyType() {
		if (id != null) {
			users = new Users();
			users.setId(id);
		}
		Map<Integer, Object> map = noncorePaybaleServer.getUserPaybaleMap(users
				.getId(), Integer.parseInt(cpage), pageSize);
		if (map != null) {	
			yiBList = (List<PayableType>) map.get(1);
			weiBList = (List<PayableType>) map.get(2);
			this.users = (Users) map.get(3);
			this.count = yiBList.size();
			this.total = map.get(4).toString();
			this.url = "NoncorePayableAction!userBangpyType.action?id="
					+ users.getId();
		} else {
			errorMessage = "查询失败,不存在该人员！";
		}
		return "userpapy";

	}
	
	/**
	 * 给人员绑定应付类型
	 * 
	 * @return
	 */
	public String linkUserProcess() {
		errorMessage = noncorePaybaleServer.linkUserPapy(users.getId(), checkboxs);
		return userBangpyType();
	}
	
	
	/********************************能耗表管理**********************************/
	public void addEner() {
		EnergyConsumption energyConsumption1 = noncorePaybaleServer.saveEnergyConsumption(energyConsumption);
		if(energyConsumption1!=null){
			MKUtil.writeJSON(energyConsumption1);
		}else {
			MKUtil.writeJSON(null);
		}
	}
	
	public String showListEner() {
		if (energyConsumption != null) {
			ActionContext.getContext().getSession().put("EnergyConsumption",
					energyConsumption);
		} else {
			energyConsumption = (EnergyConsumption) ActionContext
			.getContext().getSession().get("EnergyConsumption");
		}
		Object[] object = noncorePaybaleServer.findEnergyConsumptionList(
				energyConsumption,
				Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			energyConsumptionList = (List<EnergyConsumption>) object[0];
			if (energyConsumptionList != null
					&& energyConsumptionList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("NoncorePayableAction!showListEner.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "energyConsumption_show";
	}
	
	public String deleteEner(){
		errorMessage = noncorePaybaleServer.deleteEner(id);
		if("删除成功！".equals(errorMessage)){
			url = "NoncorePayableAction!toaddType.action";
		}
		return "error";
	}
	
	// 跳转到修改页面的方法
	public String toupdateEner() {
		if (energyConsumption!=null && energyConsumption.getId() != null && energyConsumption.getId() > 0) {
			energyConsumption = noncorePaybaleServer.getEnerByid(energyConsumption.getId());
			if (energyConsumption != null)
				return "energyConsumption_update";
		}
		errorMessage = "数据为空!请检查";
		return "error";
		
	}
	
	public String updateEner(){
		errorMessage = noncorePaybaleServer.updateEner(energyConsumption);
		if("修改成功".equals(errorMessage)){
			return "energyConsumption_update";
		}
		return "error";
	}
	
	public EnergyConsumption getEnergyConsumption() {
		return energyConsumption;
	}

	public void setEnergyConsumption(EnergyConsumption energyConsumption) {
		this.energyConsumption = energyConsumption;
	}

	public List<EnergyConsumption> getEnergyConsumptionList() {
		return energyConsumptionList;
	}

	public void setEnergyConsumptionList(
			List<EnergyConsumption> energyConsumptionList) {
		this.energyConsumptionList = energyConsumptionList;
	}

	public NoncorePaybaleServer getNoncorePaybaleServer() {
		return noncorePaybaleServer;
	}
	public void setNoncorePaybaleServer(NoncorePaybaleServer noncorePaybaleServer) {
		this.noncorePaybaleServer = noncorePaybaleServer;
	}
	public NonCorePayable getCorePayable() {
		return corePayable;
	}
	public void setCorePayable(NonCorePayable corePayable) {
		this.corePayable = corePayable;
	}
	public List<NonCorePayable> getCorePayableList() {
		return corePayableList;
	}
	public void setCorePayableList(List<NonCorePayable> corePayableList) {
		this.corePayableList = corePayableList;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public List getListType() {
		return listType;
	}
	public void setListType(List listType) {
		this.listType = listType;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public File getFujian() {
		return fujian;
	}

	public void setFujian(File fujian) {
		this.fujian = fujian;
	}

	public String getFujianFileName() {
		return fujianFileName;
	}

	public void setFujianFileName(String fujianFileName) {
		this.fujianFileName = fujianFileName;
	}

	public String getFujianContentType() {
		return fujianContentType;
	}

	public void setFujianContentType(String fujianContentType) {
		this.fujianContentType = fujianContentType;
	}

	public PayableType getPayableType() {
		return payableType;
	}

	public void setPayableType(PayableType payableType) {
		this.payableType = payableType;
	}

	public List<PayableType> getPayableTypeList() {
		return payableTypeList;
	}

	public void setPayableTypeList(List<PayableType> payableTypeList) {
		this.payableTypeList = payableTypeList;
	}

	public List<PayableType> getYiBList() {
		return yiBList;
	}

	public void setYiBList(List<PayableType> yiBList) {
		this.yiBList = yiBList;
	}

	public List<PayableType> getWeiBList() {
		return weiBList;
	}

	public void setWeiBList(List<PayableType> weiBList) {
		this.weiBList = weiBList;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public int[] getCheckboxs() {
		return checkboxs;
	}

	public void setCheckboxs(int[] checkboxs) {
		this.checkboxs = checkboxs;
	}
}
