package com.task.action.sys;

import java.io.File;
import java.util.List;

import com.task.Server.sys.SystemDemandServer;
import com.task.entity.system.SystemDemand;
import com.task.entity.system.SystemDemandDetail;
import com.task.util.MKUtil;

/**
 * 系统需求
 * @author 王传运
 *
 */
public class SystemDemandAction {

	private SystemDemand systemDemand;
	private List<SystemDemand> sdList;
	private SystemDemandDetail systemDemandDetail;
	private List<SystemDemandDetail> sddList;
	private SystemDemandServer sdServer;
	private Integer id;
	private String sdShortName;
	private File demandFile;
	private String demandFileContentType;// 文件类型
	private String demandFileFileName;// 文件名称
	
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private int count = 0;
	
	private String pageStatus;
	private String errorMessage;
	private String flag;//ajax
	/**
	 * 添加系统需求
	 * @return
	 */
	public String addSystemDemand(){
		errorMessage = sdServer.addSystemDemand(systemDemand,demandFile,demandFileFileName);
		if(flag!=null && "ajax".equals(flag)){
			MKUtil.writeJSON(errorMessage);
			setSystemDemand(null);
			return null;
		}
		setUrl("SystemDemandAction_showsdByConditon.action");
		return "error";
		
	}
	
	/**
	 * 根据信息查询系统需求
	 * @return
	 */
	public String showsdByConditon(){
		if(systemDemand==null){
			systemDemand = new SystemDemand();
		}
		if(pageStatus!=null && "all".equals(pageStatus)){
			systemDemand = null;
		}
		Object[] object = sdServer.showSystemDemands(systemDemand, pageStatus, pageSize, Integer.parseInt(cpage));
		sdList = (List<SystemDemand>) object[0];
		if (sdList != null && sdList.size() > 0) {
			int count = (Integer) object[1];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("SystemDemandAction_showsdByConditon.action?pageStatus="+pageStatus);
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "systemDemand_list";
	}
	
	/**
	 * 删除系统需求
	 * @return
	 */
	public String delSystemDemand(){
		errorMessage = sdServer.delSystemDemand(id);
		this.setUrl("SystemDemandAction_showsdByConditon.action?pageStatus="+pageStatus);
		return "error";
	}
	
	/**
	 * 根据id获得系统需求
	 * @return
	 */
	public String getSystemDemandById() {
		systemDemand = sdServer.getSystemDemandById(id);
		if(pageStatus!=null && "update".equals(pageStatus)){
			return "systemDemand_update";
		}
		return "systemDemand_show";
	}

	/**
	 * 根据需求简称获得需求信息
	 */
	public void getsdBysdShortName(){
		systemDemand =sdServer.getsdBysdShortName(sdShortName);
		if(systemDemand!=null){
			MKUtil.writeJSON("该项已经存在！");
		}
	}
	
	
	public void setSystemDemand(SystemDemand systemDemand) {
		this.systemDemand = systemDemand;
	}
	public List<SystemDemand> getSdList() {
		return sdList;
	}
	public void setSdList(List<SystemDemand> sdList) {
		this.sdList = sdList;
	}
	public SystemDemandDetail getSystemDemandDetail() {
		return systemDemandDetail;
	}
	public void setSystemDemandDetail(SystemDemandDetail systemDemandDetail) {
		this.systemDemandDetail = systemDemandDetail;
	}
	public List<SystemDemandDetail> getSddList() {
		return sddList;
	}
	public void setSddList(List<SystemDemandDetail> sddList) {
		this.sddList = sddList;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getPageStatus() {
		return pageStatus;
	}
	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public SystemDemandServer getSdServer() {
		return sdServer;
	}

	public void setSdServer(SystemDemandServer sdServer) {
		this.sdServer = sdServer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSdShortName() {
		return sdShortName;
	}

	public void setSdShortName(String sdShortName) {
		this.sdShortName = sdShortName;
	}

	public SystemDemand getSystemDemand() {
		return systemDemand;
	}

	public File getDemandFile() {
		return demandFile;
	}

	public void setDemandFile(File demandFile) {
		this.demandFile = demandFile;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getDemandFileContentType() {
		return demandFileContentType;
	}

	public void setDemandFileContentType(String demandFileContentType) {
		this.demandFileContentType = demandFileContentType;
	}

	public String getDemandFileFileName() {
		return demandFileFileName;
	}

	public void setDemandFileFileName(String demandFileFileName) {
		this.demandFileFileName = demandFileFileName;
	}

	
	
	
	
	
}
