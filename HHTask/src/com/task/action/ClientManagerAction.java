package com.task.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.ClientManagementServer;
import com.task.Server.IOrderManagerService;
import com.task.ServerImpl.yw.FileUtil;
import com.task.ServerImpl.yw.ResponseUtil;
import com.task.entity.ClientManagement;

/**
 * @ClassName: ClientManagerAction
 * @Description: 客户管理Action
 * @author 曾建森
 * @date 2012-11-20 下午04:31:46
 */
public class ClientManagerAction extends ActionSupport implements
		ServletResponseAware {

	private IOrderManagerService iom;
	private ClientManagementServer cms;
	private ClientManagement cl;
	// private List<File> clientFile;
	// private List<String> clientFileFileName;
	private File businessLicenseFile;
	private File organizationFile;
	private File logoFile;
	private String businessLicenseFileFileName;
	private String organizationFileFileName;
	private String logoFileFileName;
	private List<ClientManagement> list;
	private String errorMessage;
	private String peopleName;
	private String companyName;
	private String nature;
	private int id;
	private HttpServletResponse response;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String pageStatus;
	/**
	 * @Title: initClientManager
	 * @Description: 初始化所有客户
	 * @return String
	 * @throws
	 */
	public String initClientManager() {
		boolean bool = cms.zhClientToUsers();
		if (!bool) {
			errorMessage = "客户信息无法转换为用户登录信息,请注意检查'客户'部门是否存在!";
			return ERROR;
		}
		Object[] object = cms.queryClientManager(Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("clientManager_initClientManager.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "clientManager_index";
	}

	/**
	 * @Title: queryClientByCondition
	 * @Description: 根据条件查询
	 * @return String
	 * @throws
	 */
	private String number;

	public String queryClientByCondition() {
		Map map = new HashMap();
		if (number != null && !number.equals("")) {
			map.put("number", number);
		}
		if (companyName != null && !companyName.equals("")) {
			map.put("companyName", companyName);
		}
		if (companyName != null && !companyName.equals("")) {
			map.put("companyName", companyName);
		}
		if (nature != null && !nature.equals("") && !nature.equals("选择性质")) {
			map.put("nature", nature);
		}
		if (map.size() > 0) {
			ActionContext.getContext().getSession().put("condition", map);
		} else {
			if (errorMessage == null || !errorMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get(
						"condition");
			} else
				ActionContext.getContext().getSession().remove("condition");
		}
		Object[] object = cms.queryClientByCondition(map, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("clientManager_initClientManagers.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "clientManager_index";
	}

	/**
	 * @Title: add
	 * @Description: 添加客户
	 * @return String
	 * @throws
	 */
	public String add() {
		String location = "upload/companyLogo";
		String str = "D:/WorkSpace/HHTask/WebRoot/upload/companyLogo";
		// String str =
		// "D:/Workspaces/MyEclipse 8.6/test/HHTask/WebRoot/upload/companyLogo";
		if (businessLicenseFile != null && businessLicenseFileFileName != null) {
			String fileName = FileUtil.uploadFile(businessLicenseFile,
					businessLicenseFileFileName, location, str);
			cl.setBusinessLicense(fileName);
		}
		if (organizationFile != null && organizationFileFileName != null) {
			String fileName = FileUtil.uploadFile(organizationFile,
					organizationFileFileName, location, str);
			cl.setOrganization(fileName);
		}
		if (logoFile != null && logoFileFileName != null) {
			String fileName = FileUtil.uploadFile(logoFile, logoFileFileName,
					location, str);
			cl.setLogo(fileName);
		}
		cms.add(cl);
		return "clientManager_add";
	}

	/***
	 * @Title: initUpdate
	 * @Description: 初始化修改客户信息
	 * @return String
	 * @throws
	 */
	public String initUpdate() {
		cl = cms.getClientManagementById(id);
		return "clientManager_update";
	}

	/**
	 * @Title: update
	 * @Description: 修改信息
	 * @return String
	 * @throws
	 */
	public String update() {
		if (cl != null) {
			ClientManagement clm = cms.getClientManagementById(cl.getId());
			BeanUtils.copyProperties(cl, clm, new String[] { "id", "number",
					"logo", "orders", "businessLicense", "organization" });
			cms.update(clm);
			return "clientManager_add";
		}
		return null;
	}

	/**
	 * @Title: del
	 * @Description:
	 * @return String
	 * @throws
	 */
	public String del() {
		if (id != 0) {
			cms.delClientManagementById(id);
			ResponseUtil.write(response, "修改成功!",
					"clientManager_initClientManager.action", null);
		}
		ResponseUtil.write(response, "修改失败!",
				"clientManager_initClientManager.action", null);
		return null;
	}

	/**
	 * @Title: infor
	 * @Description: 查看客户详细信息
	 * @return String
	 * @throws
	 */
	public String infor() {
		if (id != 0) {
			cl = cms.getClientManagementById(id);
			return "clientManager_infor";
		}
		return null;
	}

	/**
	 * @Title: detail
	 * @Description: 查询客户订单
	 * @return String
	 * @throws
	 */
	public String detail() {
		if (id != 0) {
			Object[] object = iom.queryOrderByClientById(id, Integer
					.parseInt(cpage), pageSize);
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("clientManager_detail.action");
					errorMessage = null;
				} else
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			return "clientManager_detail";
		}
		return null;
	}

	public ClientManagement getCl() {
		return cl;
	}

	public void setCl(ClientManagement cl) {
		this.cl = cl;
	}

	public ClientManagementServer getCms() {
		return cms;
	}

	public void setCms(ClientManagementServer cms) {
		this.cms = cms;
	}

	public List<ClientManagement> getList() {
		return list;
	}

	public void setList(List<ClientManagement> list) {
		this.list = list;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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

	public String getPeopleName() {
		return peopleName;
	}

	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public IOrderManagerService getIom() {
		return iom;
	}

	public void setIom(IOrderManagerService iom) {
		this.iom = iom;
	}

	public File getBusinessLicenseFile() {
		return businessLicenseFile;
	}

	public void setBusinessLicenseFile(File businessLicenseFile) {
		this.businessLicenseFile = businessLicenseFile;
	}

	public File getOrganizationFile() {
		return organizationFile;
	}

	public void setOrganizationFile(File organizationFile) {
		this.organizationFile = organizationFile;
	}

	public File getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(File logoFile) {
		this.logoFile = logoFile;
	}

	public String getBusinessLicenseFileFileName() {
		return businessLicenseFileFileName;
	}

	public void setBusinessLicenseFileFileName(
			String businessLicenseFileFileName) {
		this.businessLicenseFileFileName = businessLicenseFileFileName;
	}

	public String getOrganizationFileFileName() {
		return organizationFileFileName;
	}

	public void setOrganizationFileFileName(String organizationFileFileName) {
		this.organizationFileFileName = organizationFileFileName;
	}

	public String getLogoFileFileName() {
		return logoFileFileName;
	}

	public void setLogoFileFileName(String logoFileFileName) {
		this.logoFileFileName = logoFileFileName;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

}
