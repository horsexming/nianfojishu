package com.task.action.claim;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.FileCopyUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.claim.CustomerClaimServer;
import com.task.entity.claim.CustomerClaim;
import com.task.util.Util;


public class CustomerClaimAction extends ActionSupport{
	private CustomerClaim customerClaim;
	private List<CustomerClaim> customerClaimlist;
	private List<CustomerClaim> analysisList;
	private List list;
	private CustomerClaimServer customerClaimServer;
	private String tag = "all";// 标记
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	// 上传文件
	private File analysis;
	private String analysisFileName;
	private String analysisContentType;
	private File improve;
	private String improveFileName;
	private String improveContentType;
	public String toadd() {
		// 客户添加投诉
		if ("client".equals(tag)) {
			// 查出客户未确认，并已出库的订单
			list = customerClaimServer.findOrderByClient();
			if (list != null && list.size() > 0)
				return "customerClaim_client_add";
			else {
				errorMessage = "对不起,尚未查询到您的订单信息,无法进行产品索赔!谢谢!";
				return ERROR;	
			}
		}
		// 内部人员添加投诉
		return "customerClaim_add";
	}

	public String add() {
		boolean b = customerClaimServer.add(customerClaim);
		if (b) {
			successMessage = "添加成功!";
		} else {
			successMessage = "添加失败!";
		}
		return "customerClaim_toList";
	}
	
	public String showList() {
		if (customerClaim != null) {
			ActionContext.getContext().getSession().put("customerClaim",
					customerClaim);
		} else {// 用来保持分页时带着查询条件
			customerClaim = (CustomerClaim) ActionContext.getContext()
					.getSession().get("customerClaim");
		}
		Map<Integer, Object> map = customerClaimServer
				.findCustomerClaimsByCondition(customerClaim, Integer
						.parseInt(cpage), pageSize, tag);
		customerClaimlist = (List<CustomerClaim>) map.get(1);// 显示页的技能系数列表
		if (customerClaimlist != null & customerClaimlist.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("customerClaimAction_showList.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "customerClaim_show";
	}
	
	public String detail(){
		customerClaim = customerClaimServer.getById(customerClaim.getId());
		return "customerClaim_cdetail";
	}
	
	public String showAnalysis() {
		if (customerClaim != null) {
			ActionContext.getContext().getSession().put("customerClaim",
					customerClaim);
		} else {
			customerClaim = (CustomerClaim) ActionContext.getContext()
					.getSession().get("customerClaim");
		}
		Map<Integer, Object> map = customerClaimServer
				.findCustomerClaimsByCondition(customerClaim, Integer
						.parseInt(cpage), pageSize, tag);
		customerClaimlist = (List<CustomerClaim>) map.get(1);
		if (customerClaimlist != null & customerClaimlist.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("customerClaimAction_showList.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		analysisList = customerClaimServer.finddaifenxiList();
		return "customerClaim_showAnalysis";
	}
	
	public String delete() {
		boolean b = customerClaimServer.deleteById(customerClaim.getId());
		successMessage = "删除成功";
		customerClaim = null;
		return "customerClaim_toList";
	}
	
	public String toUpdate(){
		customerClaim = customerClaimServer
		.getById(customerClaim.getId());
		if (customerClaim == null) {
			successMessage = "没有找到对应的客户索赔!";
			return showList();
		} else {
			return "customerClaim_update";
		}
	}
	
	public String updateAnalysis(){
		customerClaim = customerClaimServer
		.getById(customerClaim.getId());
		if (customerClaim == null) {
			successMessage = "没有找到对应的客户索赔!";
			return showList();
		} else {
			return "customerClaim_updateAnalysis";
		}
	}
	public String update() {
		
		boolean b = customerClaimServer.update(customerClaim, tag);
		if (b) {
			successMessage = "修改成功!";
		} else {
			successMessage = "修改失败!";
		}
		
		return "customerClaim_toList";
	}
	public  String toUpdateA(){
		customerClaim = customerClaimServer
		.getById(customerClaim.getId());
		if (customerClaim == null) {
			successMessage = "没有找到对应的客户索赔!";
			return showList();
		} else {
			return "customerClaim_updateA";
		}
	}
	public String updateApproval(){
		if(customerClaimServer.updateApproval(customerClaim)){
			successMessage="修改成功!";
		}else{
			successMessage="修改失败!";
		}
		return "customerClaim_toAnalysisList";
	}
//	public String toApproval() throws Exception{
	//String msg=customerClaimServer.addapproval(customerClaim);
//		if(msg.equals("提交审批成功")){
//			successMessage="成功提交申请！";
//			return "customerClaim_toApproval";
//		}
//	errorMessage=msg;
//	return "customerClaim_toApproval";
//}
	/**
	 * 
	 * 分析
	 * 
	 */
	public String toAnalysis() {
		customerClaim = customerClaimServer
				.getById(customerClaim.getId());
		if (customerClaim == null) {
			successMessage = "没有找到对应的客户投诉!";
			return "customerClaim_toList";
		} else {
			return "customerClaim_analysis";
		}
	}
	
	public String analysis() {
		String msg = "true";
		String fileName = null;
		if (customerClaim.getAnalysisTime() == null
				|| customerClaim.getAnalysisTime().equals("")) {
			customerClaim.setAnalysisTime(Util.getDateTime());
		}
		if (analysis != null) {
			String fileType = null;
			String[] names = analysisFileName.split("\\.");
			if (names != null && names.length >= 2) {
				fileType = names[names.length - 1];
			}
			fileName = "analysis"
					+ Util.DateToString(Util.StringToDate(customerClaim
							.getAnalysisTime(), "yyyy-MM-dd HH:mm:ss"),
							"yyyyMMddHHmmss") + "." + fileType;
			msg = uploadlic(analysis, "analysis", fileName, customerClaim
					.getAnalysisFile());
		}
		if (msg != null && msg.equals("true")) {
			customerClaim.setAnalysisFile(fileName);
			boolean b = customerClaimServer.update(customerClaim, tag);
			if(b){
				String msg1=customerClaimServer.addapproval(customerClaim);
			}
			
		} else {
			successMessage = msg;
		}
		return "customerClaim_toAnalysisList";
	}
	public String uploadlic(File file, String dirName, String fileName,
			String deletefilename) {
		if (file != null) {
			// 打开存放上传文件的位置
			String path = ServletActionContext.getServletContext().getRealPath(
					"/upload/file/" + dirName);
			File file1 = new File(path);
			if (!file1.exists()) {
				file1.mkdirs();// 如果不存在文件夹就创建
			}
			// 删除源文件
			if (deletefilename != null) {
				File old = new File(path + "/" + deletefilename);
				if (old != null) {
					old.delete();
				}
			}
			// 将证书写入文件夹中
			InputStream is = null;
			try {
				is = new FileInputStream(file);
				File file2 = new File(path + "/" + fileName);
				if (file2.exists()) {
					file2.delete();// 将原证书删掉
				}
				// 上传文件到服务器
				String fileRealPath = path + "/" + fileName;
				File uploadFile = new File(fileRealPath);
				try {
					FileCopyUtils.copy(file, uploadFile);
				} catch (Exception e) {
					return "上传文件失败!";
				}
				successMessage = "上传成功！";
			} catch (FileNotFoundException e) {
				return "找不到文件！";
			} catch (IOException e) {
				return "文件输入出错！";
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return "true";
		}
		return "文件为空";
	}
	
	public CustomerClaim getCustomerClaim() {
		return customerClaim;
	}
	public void setCustomerClaim(CustomerClaim customerClaim) {
		this.customerClaim = customerClaim;
	}
	public List<CustomerClaim> getCustomerClaimlist() {
		return customerClaimlist;
	}
	public void setCustomerClaimlist(List<CustomerClaim> customerClaimlist) {
		this.customerClaimlist = customerClaimlist;
	}
	
	public List<CustomerClaim> getAnalysisList() {
		return analysisList;
	}

	public void setAnalysisList(List<CustomerClaim> analysisList) {
		this.analysisList = analysisList;
	}

	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
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
	public File getAnalysis() {
		return analysis;
	}
	public void setAnalysis(File analysis) {
		this.analysis = analysis;
	}
	public String getAnalysisFileName() {
		return analysisFileName;
	}
	public void setAnalysisFileName(String analysisFileName) {
		this.analysisFileName = analysisFileName;
	}
	public String getAnalysisContentType() {
		return analysisContentType;
	}
	public void setAnalysisContentType(String analysisContentType) {
		this.analysisContentType = analysisContentType;
	}
	public File getImprove() {
		return improve;
	}
	public void setImprove(File improve) {
		this.improve = improve;
	}
	public String getImproveFileName() {
		return improveFileName;
	}
	public void setImproveFileName(String improveFileName) {
		this.improveFileName = improveFileName;
	}
	public String getImproveContentType() {
		return improveContentType;
	}
	public void setImproveContentType(String improveContentType) {
		this.improveContentType = improveContentType;
	}
	public CustomerClaimServer getCustomerClaimServer() {
		return customerClaimServer;
	}
	public void setCustomerClaimServer(CustomerClaimServer customerClaimServer) {
		this.customerClaimServer = customerClaimServer;
	}
}
