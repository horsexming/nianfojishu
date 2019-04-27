package com.task.action.dmltry;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.dmltry.DmlAppFileUrlServer;
import com.task.entity.dmltry.DmlAppFileUrl;
import com.task.entity.dmltry.DmltryAppFiles;

public class DmlAppFileUrlAction {

	private DmlAppFileUrl dmlAppFileUrl;
	private List<DmlAppFileUrl> list;
	private DmlAppFileUrlServer dmlAppFileUrlServer;
	private String successMessage;
	private DmlAppFileUrl dmlAppFileUr1;
	private DmltryAppFiles dmltryAppFiles;

	private String errorMessage;// 错误消息
	private List<DmlAppFileUrl> dmlAppFileUrllist;

	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	// 上传文件对象
	private File fujian;
	// 上传文件名
	private String fujianFileName;
	// 上传文件类型
	private String fujianContentType;

	// 查看明细
	public String selct() {
		dmlAppFileUrllist = dmlAppFileUrlServer.seleectDmlAppFileUrl(dmltryAppFiles.getId());
		if (dmlAppFileUrllist != null) {
			return "DmltryAppFiles_show";
		} else {
			errorMessage = "没有数据！";
			return "error";
		}
	}

	/**
	 * 查询根据id
	 * 
	 * @param id
	 * @return
	 */

	public String selidDmlAppFileUrl() {
		dmlAppFileUr1 = dmlAppFileUrlServer.selidDmlAppFileUrl(dmlAppFileUrl.getId());
		return "DmlApp_showwid";
	}

	/**
	 * 添加明细
	 * 
	 * @return
	 */
	public String mingxi() {

		dmlAppFileUr1 = dmlAppFileUrlServer.selidDmlAppFileUrl(dmltryAppFiles.getId());
		return "DmlAppFileUrl_addmx";

	}

	public String addmx() {

		dmlAppFileUr1 = dmlAppFileUrlServer.selidDmlAppFileUrl(dmlAppFileUrl.getId());

		return "dmlaps_add";
	}

	/**
	 * 修改
	 * 
	 * @return
	 */
	public String updateDmlAppFileUrl() {
		errorMessage = dmlAppFileUrlServer.updateDmlAppFileUrl(dmlAppFileUrl);
		if ("true".contentEquals(errorMessage)) {
			errorMessage = "删除成功！";
			url = "DmlAppFileUrlAction!fenyeandtj.action";
		}
		return "error";
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@SuppressWarnings("delteDmlAppFileUrl")
	public String delteDmlAppFileUrl() {
		errorMessage = dmlAppFileUrlServer.delteDmlAppFileUrl(dmlAppFileUrl.getId());
		if ("true".contentEquals(errorMessage)) {
			errorMessage = "删除成功！";
			url = "DmlAppFileUrlAction!fenyeandtj.action";
		}
		return "error";
	}

	/**
	 * 分页加条件查询
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String fenyeandtj() {
		if (dmlAppFileUrl != null) {
			ActionContext.getContext().getSession().put("dmlAppFileUrlfy", dmlAppFileUrl);
		} else {
			// 保持分页
			dmlAppFileUrl = (DmlAppFileUrl) ActionContext.getContext().getSession().get("dmlAppFileUrlfy");
		}
		/// 注意一下bject[] object
		Map<Integer, Object> map = dmlAppFileUrlServer.fenyandtj(dmlAppFileUrl, Integer.parseInt(cpage), pageSize);
		dmlAppFileUrllist = (List<DmlAppFileUrl>) map.get(1);
		if (dmlAppFileUrllist != null && dmlAppFileUrllist.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("DmlAppFileUrlAction!fenyeandtj.action");
			successMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "DmlAppFileUrl_show";
	}

	public DmlAppFileUrl getDmlAppFileUrl() {
		return dmlAppFileUrl;
	}

	public void setDmlAppFileUrl(DmlAppFileUrl dmlAppFileUrl) {
		this.dmlAppFileUrl = dmlAppFileUrl;
	}

	public List<DmlAppFileUrl> getList() {
		return list;
	}

	public void setList(List<DmlAppFileUrl> list) {
		this.list = list;
	}

	public DmlAppFileUrlServer getDmlAppFileUrlServer() {
		return dmlAppFileUrlServer;
	}

	public void setDmlAppFileUrlServer(DmlAppFileUrlServer dmlAppFileUrlServer) {
		this.dmlAppFileUrlServer = dmlAppFileUrlServer;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public DmlAppFileUrl getDmlAppFileUr1() {
		return dmlAppFileUr1;
	}

	public void setDmlAppFileUr1(DmlAppFileUrl dmlAppFileUr1) {
		this.dmlAppFileUr1 = dmlAppFileUr1;
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

	public List<DmlAppFileUrl> getDmlAppFileUrllist() {
		return dmlAppFileUrllist;
	}

	public void setDmlAppFileUrllist(List<DmlAppFileUrl> dmlAppFileUrllist) {
		this.dmlAppFileUrllist = dmlAppFileUrllist;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public DmltryAppFiles getDmltryAppFiles() {
		return dmltryAppFiles;
	}

	public void setDmltryAppFiles(DmltryAppFiles dmltryAppFiles) {
		this.dmltryAppFiles = dmltryAppFiles;
	}

}
