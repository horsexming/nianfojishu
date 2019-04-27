package com.task.action.caiwu.pz;

import java.io.File;
import java.util.List;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.caiwu.pz.CwCertificateServer;
import com.task.entity.caiwu.pz.CwCertificate;
/**
 * 财务凭证Action
 * @addTime 2017-08-21
 * @author licong
 *
 */
@SuppressWarnings("unchecked")
public class CwCertificateAction extends ActionSupport {
	private CwCertificateServer cwCertificateServer;
	private CwCertificate cwCertificate;//财务凭证主表
	private List<CwCertificate> cwCertificateList;
	private String tag;
	private Integer num_1;
	private String message;
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
	
	private File pinzheng;//凭证文件
	
	//导入考勤数据
	public String daoInkaoqin(){
		errorMessage = cwCertificateServer.daoRupinzheng(pinzheng);
		if("true".equals(errorMessage)){
			errorMessage = "导入成功！";
		}
		errorMessage.replaceAll("\"", "");
		return "error";
	}
	
	public String shenqing(){
		return "cwCertificate_appli";
	}
	
	public String toadd() {
		return "cwCertificate_add";
	}
	public String toaddpl() {
		return "cwCertificate_add_pl";
	}
	
	public String addCwCertificate() {
		errorMessage = cwCertificateServer.saveCwCertificate(cwCertificate,tag);
		if ("添加成功！".equals(errorMessage))
			url = "CwCertificateAction_showList.action";
		return "error";
	}
	
	// 分页显示
	// 显示查询内容
	public String showList() {
		if (cwCertificate != null) {
			ActionContext.getContext().getSession().put("CwCertificate",
					cwCertificate);
		} else {
			cwCertificate = (CwCertificate) ActionContext
					.getContext().getSession().get("CwCertificate");
		}
		Object[] object = cwCertificateServer.findCwCertificateList(
				cwCertificate, Integer.parseInt(cpage), pageSize, tag);
		if (object != null && object.length > 0) {
			cwCertificateList = (List<CwCertificate>) object[0];
			if (cwCertificateList != null
					&& cwCertificateList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("CwCertificateAction_showList.action?tag=" + tag);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		if("cw".equals(tag)||"cw1".equals(tag)){
			this.setUrl("CwCertificateAction_showList.action?tag=" + tag +"&num_1="+num_1);
			return "cwCertificate_show_xuanz";
		}
		return "cwCertificate_show";
	}
	
	// 跳转到修改页面的方法
	public String toupdate() {
		if (cwCertificate!=null && cwCertificate.getId() != null && cwCertificate.getId() > 0) {
			cwCertificate = cwCertificateServer.getCwCertificateById(cwCertificate.getId());
			if (cwCertificate != null)
				return "cwCertificate_update";
		}
		errorMessage = "数据为空!请检查";
		return "error";
		
	}
	//修改方法
	public String updateCwCertificate() {
		if(cwCertificate!=null){
			errorMessage = cwCertificateServer.updateCwCertificate(cwCertificate);
			if ("修改成功！".equals(errorMessage))
				url = "CwCertificateAction_showList.action?tag="+tag;
		}
		return "error";
	}
	
	public String delete(){
		errorMessage = cwCertificateServer.deletecwCertificate(id);
		if("删除成功！".equals(errorMessage)){
			url = "CwCertificateAction_showList.action?tag="+tag;
		}
		return "error";
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

	public CwCertificateServer getCwCertificateServer() {
		return cwCertificateServer;
	}

	public void setCwCertificateServer(CwCertificateServer cwCertificateServer) {
		this.cwCertificateServer = cwCertificateServer;
	}

	public CwCertificate getCwCertificate() {
		return cwCertificate;
	}

	public void setCwCertificate(CwCertificate cwCertificate) {
		this.cwCertificate = cwCertificate;
	}

	public List<CwCertificate> getCwCertificateList() {
		return cwCertificateList;
	}

	public void setCwCertificateList(List<CwCertificate> cwCertificateList) {
		this.cwCertificateList = cwCertificateList;
	}

	public File getPinzheng() {
		return pinzheng;
	}

	public void setPinzheng(File pinzheng) {
		this.pinzheng = pinzheng;
	}

	public Integer getNum_1() {
		return num_1;
	}

	public void setNum_1(Integer num_1) {
		this.num_1 = num_1;
	}

}
