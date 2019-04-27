package com.task.action.opinion;

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
import com.task.Server.opinion.CustomerOpinionServer;
import com.task.entity.opinion.CustomerOpinion;
import com.task.util.Util;

public class CustomerOpinionAction extends ActionSupport {
	private CustomerOpinion customerOpinion;
	private List<CustomerOpinion> customerOpinionlist;
	private List list;
	private CustomerOpinionServer customerOpinionServer;
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

	public String showList() {
		if (customerOpinion != null) {
			ActionContext.getContext().getSession().put("customerOpinion",
					customerOpinion);
		} else {// 用来保持分页时带着查询条件
			customerOpinion = (CustomerOpinion) ActionContext.getContext()
					.getSession().get("customerOpinion");
		}
		Map<Integer, Object> map = customerOpinionServer
				.findCustomerOpinionsByCondition(customerOpinion, Integer
						.parseInt(cpage), pageSize, tag);
		customerOpinionlist = (List<CustomerOpinion>) map.get(1);// 显示页的技能系数列表
		if (customerOpinionlist != null & customerOpinionlist.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("customerOpinionAction_showList.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "customerOpinion_show";

	}

	public String toadd() {
		// 客户添加投诉
		if ("client".equals(tag)) {
			// 查出客户未确认，并已出库的订单
			list = customerOpinionServer.findOrderByClient();
			if (list != null && list.size() > 0)
				return "customerOpinion_client_add";
			else {
				errorMessage = "对不起,尚未查询到您的订单信息,无法进行产品投诉!谢谢!";
				return ERROR;
			}
		}
		// 内部人员添加投诉
		return "customerOpinion_add";
	}

	public String add() {
		boolean b = customerOpinionServer.add(customerOpinion);
		if (b) {
			successMessage = "添加成功!";
		} else {
			successMessage = "添加失败!";
		}
		return showList();
	}

	public String toUpdate() {
		customerOpinion = customerOpinionServer
				.getById(customerOpinion.getId());
		if (customerOpinion == null) {
			successMessage = "没有找到对应的客户投诉!";
			return showList();
		} else {
			return "customerOpinion_update";
		}
	}

	public String update() {
		boolean b = customerOpinionServer.update(customerOpinion, tag);
		if (b) {
			successMessage = "修改成功!";
		} else {
			successMessage = "修改失败!";
		}
		return showList();
	}

	/**
	 * 跳往分析页面
	 * 
	 * @return
	 */
	public String toAnalysis() {
		customerOpinion = customerOpinionServer
				.getById(customerOpinion.getId());
		if (customerOpinion == null) {
			successMessage = "没有找到对应的客户投诉!";
			return showList();
		} else {
			return "customerOpinion_analysis";
		}
	}

	/**
	 * 分析
	 * 
	 * @return
	 */
	public String analysis() {
		String msg = "true";
		String fileName = null;
		if (customerOpinion.getAnalysisTime() == null
				|| customerOpinion.getAnalysisTime().equals("")) {
			customerOpinion.setAnalysisTime(Util.getDateTime());
		}
		if (analysis != null) {
			String fileType = null;
			String[] names = analysisFileName.split("\\.");
			if (names != null && names.length >= 2) {
				fileType = names[names.length - 1];
			}

			fileName = "analysis"
					+ Util.DateToString(Util.StringToDate(customerOpinion
							.getAnalysisTime(), "yyyy-MM-dd HH:mm:ss"),
							"yyyyMMddHHmmss") + "." + fileType;
			msg = uploadlic(analysis, "analysis", fileName, customerOpinion
					.getAnalysisFile());

		}
		if (msg != null && msg.equals("true")) {
			customerOpinion.setAnalysisFile(fileName);
			boolean b = customerOpinionServer.update(customerOpinion, tag);
			successMessage = "分析完成";
		} else {
			successMessage = msg;
		}
		return showList();
	}

	/**
	 * 跳往改进页面
	 * 
	 * @return
	 */
	public String toImprove() {
		customerOpinion = customerOpinionServer
				.getById(customerOpinion.getId());
		if (customerOpinion == null) {
			successMessage = "没有找到对应的客户投诉!";
			return showList();
		} else {
			return "customerOpinion_improve";
		}

	}

	/**
	 * 改进
	 * 
	 * @return
	 */
	public String improve() {
		String msg = "true";
		String fileName = null;
		if (improve != null) {
			String[] names = analysisFileName.split("\\.");
			String fileType = null;
			if (names != null && names.length >= 2) {
				fileType = names[names.length - 1];
			}
			fileName = "improve" + Util.getDateTime("yyyyMMddHHssmm") + "."
					+ fileType;
			msg = uploadlic(improve, "improve", fileName, customerOpinion
					.getExecutiveFile());
		}
		if (msg != null && msg.equals("true")) {
			customerOpinion.setExecutiveFile(fileName);
			boolean b = customerOpinionServer.update(customerOpinion, tag);
			successMessage = "分析完成";
		} else {
			successMessage = msg;
		}
		return showList();
	}

	/**
	 * 跳往审批页面
	 * 
	 * @return
	 */
	public String toApproval() {
		customerOpinion = customerOpinionServer
				.getById(customerOpinion.getId());
		if (customerOpinion == null) {
			successMessage = "没有找到对应的客户投诉!";
			return showList();
		} else {
			return "customerOpinion_approval";
		}
	}

	public String approval() {
		if (customerOpinion.getApprovaltime1() == null
				|| customerOpinion.getApprovaltime1().equals("")) {
			customerOpinion.setApprovaltime1(Util.getDateTime());
		}
		boolean b = customerOpinionServer.update(customerOpinion, tag);
		successMessage = "审批完成";
		return showList();
	}

	public String delete() {
		boolean b = customerOpinionServer.deleteById(customerOpinion.getId());
		successMessage = "删除成功";
		customerOpinion = null;
		return showList();
	}

	/**
	 * 上传文件
	 * 
	 * @param file
	 * @param dirName
	 *            文件夹名称
	 * @param befroeName
	 *            文件名
	 * @param deletefilename
	 *            原文件名
	 * @return
	 */
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
			// OutputStream os = null;
			try {
				is = new FileInputStream(file);
				File file2 = new File(path + "/" + fileName);
				if (file2.exists()) {
					file2.delete();// 将原证书删掉
				}
				// os = new FileOutputStream(path + "/" + licenseFileName);
				// 上传文件到服务器
				String fileRealPath = path + "/" + fileName;
				File uploadFile = new File(fileRealPath);
				try {
					FileCopyUtils.copy(file, uploadFile);
				} catch (Exception e) {
					return "上传文件失败!";
				}

				// byte[] b = new byte[1024*10];
				// int length = 0;
				// while (-1 != (length = is.read(b, 0, b.length))) {
				// os.write(b);
				// }
				successMessage = "上传成功！";
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				return "找不到文件！";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return "文件输入出错！";
			} finally {
				try {
					// os.close();
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return "true";
		}
		return "文件为空";
	}

	public CustomerOpinion getCustomerOpinion() {
		return customerOpinion;
	}

	public void setCustomerOpinion(CustomerOpinion customerOpinion) {
		this.customerOpinion = customerOpinion;
	}

	public List<CustomerOpinion> getCustomerOpinionlist() {
		return customerOpinionlist;
	}

	public void setCustomerOpinionlist(List<CustomerOpinion> customerOpinionlist) {
		this.customerOpinionlist = customerOpinionlist;
	}

	public CustomerOpinionServer getCustomerOpinionServer() {
		return customerOpinionServer;
	}

	public void setCustomerOpinionServer(
			CustomerOpinionServer customerOpinionServer) {
		this.customerOpinionServer = customerOpinionServer;
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

}
