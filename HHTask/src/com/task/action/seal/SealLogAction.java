package com.task.action.seal;

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
import com.task.Server.seal.SealLogServer;
import com.task.entity.Users;
import com.task.entity.seal.SealLog;
import com.task.entity.seal.SealLogType;
import com.task.util.MKUtil;
import com.task.util.Upload;
import com.task.util.Util;

public class SealLogAction extends ActionSupport {
	private SealLogType sealLogType;
	private List<SealLogType> sealLogTypeList;
	private SealLog sealLog;
	private SealLogServer sealLogServer;
	private List<SealLog> sealLogList;
	// 上传文件对象,用来上传license证书
	private File fujian;
	// 上传文件名
	private String fujianFileName;
	// 上传文件类型
	private String fujianContentType;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	// 分页
	private String tag;// 标识（dag）
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	private String pageStatus;

	public String toborrowSeal(){
		errorMessage = sealLogServer.toborrowSeal();
		if ("true".equals(errorMessage))
			return "seallog_borrow";
		return "error";
	}
	
	public String updateborrwSeal(){
		sealLogServer.updatelinshi();
		return "error";
	}
	
	//前往印章申请页面
	public String toaddSeal(){
		errorMessage = sealLogServer.toaddSeal();
		if ("true".equals(errorMessage)) {
			return "seallog_add";
		}else if ("公章已被借出，请等待归还后再申请！".equals(errorMessage)) {
			return "seallog_add_1";
		}else {
			return "error";
		}
	}
	
	/**
	 * 分页查询
	 * 
	 * @return
	 */
	public String showList() {
		if (sealLog != null) {
			ActionContext.getContext().getSession().put("sealLog", sealLog);
		} else {// 用来保持分页时带着查询条件
			sealLog = (SealLog) ActionContext.getContext().getSession().get(
					"sealLog");
		}
		Map<Integer, Object> map = sealLogServer.findSealLogsByCondition(
				sealLog, Integer.parseInt(cpage), pageSize, pageStatus,tag);
		sealLogList = (List<SealLog>) map.get(1);// 显示页的技能系数列表
		if (sealLogList != null & sealLogList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("sealLogAction_showList.action?pageStatus="
					+ pageStatus);
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "seallog_show";
	}

	/**
	 * 添加并申请
	 */
	public void add() {
		boolean b = false;
		String mess = "";
		String end = null;

		try {
			if (fujian != null) {
				String[] fileNames = fujianFileName.split("\\.");
				if (fileNames != null && fileNames.length >= 2) {
					end = fileNames[fileNames.length - 1];
					sealLog.setFujian(end);
				}
				// sealLog.setFujian("upload/file/sealfj/"+newName);
			}
			sealLog = sealLogServer.add(sealLog);
			if (sealLog != null) {
				b = true;
				mess = "添加成功";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MKUtil.writeJSON(false);
			return;
		}
		if (b) {
			if (fujian != null) {
				// 打开存放上传文件的位置
				String newName = "fujian" + sealLog.getId() + "." + end;
				String path = ServletActionContext.getServletContext()
						.getRealPath("/upload/file/sealfj");
				File file1 = new File(path);
				if (!file1.exists()) {
					file1.mkdirs();// 如果不存在文件夹就创建
				}
				// 将合同写入文件夹中
				InputStream is = null;
				// OutputStream os = null;
				try {
					is = new FileInputStream(fujian);
					File file2 = new File(path + "/" + newName);
					if (file2.exists()) {
						file2.delete();// 将合同删掉
					}
					// os = new FileOutputStream(path + "/" + fujianFileName);
					// 上传文件到服务器
					String fileRealPath = path + "/" + newName;
					File uploadFile = new File(fileRealPath);
					try {
						FileCopyUtils.copy(fujian, uploadFile);
					} catch (Exception e) {
						b = false;
					}

					// byte[] b = new byte[1024*10];
					// int length = 0;
					// while (-1 != (length = is.read(b, 0, b.length))) {
					// os.write(b);
					// }
					b = true;
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					b = false;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					b = false;
				} finally {
					try {
						// os.close();
						is.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		MKUtil.writeJSON(b);
	}

	public String tobackFujian(){
		if (sealLog != null) {
			sealLog = sealLogServer.getSealLog(sealLog.getId());
			if (sealLog!=null)
				return "updateFujianseallogdetail";
		}
		errorMessage = "对象为空！";
		return "error";
	}

	public String backFujian(){
		String fu = "";
		if (fujian != null) {
			// 文件路径
			// 打开存放文件的位置
			String realFileName = "fujian2_" + sealLog.getId();
			String realFilePath = "/upload/file/sealfj";
			String path = ServletActionContext.getServletContext()
					.getRealPath(realFilePath);
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();// 如果不存在文件夹就新建
			}
			Upload upload = new Upload();// 文件上传工具类 
			fu = upload.UploadFile(fujian, fujianFileName,
					null, realFilePath, null);
			sealLog.setFujian2(realFilePath + "/"+fu);// 文件新名称
		}
		if (!"".equals(fu)&&!"error".equals(fu)&&!"上传文件失败!".equals(fu)) {
			errorMessage = sealLogServer.update2(sealLog);
			if ("上传成功".equals(errorMessage))
				url = "sealLogAction_showList.action?pageStatus="+pageStatus;
		}
		return "error";
	}
	/**
	 * 确认使用
	 * 
	 * @return
	 */
	public String makeSure() {
		boolean b = sealLogServer.makeSure(sealLog.getId(), pageStatus);
		if (b) {
			successMessage = "确认成功!";
		} else {
			successMessage = "确认失败!";
		}
		return "sealLog_makeSure";
	}

	/**
	 * 查看明细
	 * 
	 * @return
	 */
	public String sealLogdetail() {
		if (sealLog != null) {
			sealLog = sealLogServer.getSealLog(sealLog.getId());
			return "seallogdetail";
		}
		return null;
	}

	/**
	 * 跳往修改页面
	 * 
	 * @return
	 */
	public String toupdate() {
		if (sealLog != null) {
			sealLog = sealLogServer.getSealLog(sealLog.getId());
			return "sealLog_update";
		} else {
			successMessage = "没有找到目标！";
			sealLog = null;
			return showList();
		}
	}

	/**
	 * 修改
	 */
	public void update() {
		if (sealLog != null) {
			boolean b = sealLogServer.update(sealLog);
			MKUtil.writeJSON(b);
		}
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		if (sealLog != null) {
			boolean b = sealLogServer.delete(sealLog.getId());
			if (b) {
				successMessage = "删除成功!";
			} else {
				successMessage = "删除失败!";
			}
			sealLog = null;
		}
		return showList();
	}

	/**
	 * 通过工号或者名字查询用户对象
	 */
	public void getUser() {
		Users user = sealLogServer.getUser(sealLog);
		if (user != null) {
			MKUtil.writeJSON(true, null, user);
		} else {
			MKUtil.writeJSON(false, null, null);

		}

	}
	/**
	 * 显示所有印章
	 */
	public String showSealLogTypeList() {
		if (sealLogType != null) {
			ActionContext.getContext().getSession().put("sealLogType",
					sealLogType);
		} else {
			sealLogType = (SealLogType) ActionContext.getContext()
			.getSession().get("sealLogType");
		}
		Map<Integer, Object> map = sealLogServer.findSealLogTypeByCondition(
				sealLogType, Integer.parseInt(cpage), pageSize);
		sealLogTypeList = (List<SealLogType>) map.get(1);// 显示页的技能系数列表
		if (sealLogTypeList != null & sealLogTypeList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("sealLogType_showSealLogTypeList.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "seallogtype_show";
	}
	/**
	 * 跳转添加方法
	 */
	public String toaddsealLogtype() {
		return "sealLogtype_add";
	}
	/**
	 * 添加印章
	 */
	public String addsealLogtype() {
		errorMessage = sealLogServer.addsealLogtype(sealLogType);
		if ("true".equals(errorMessage)) {
			errorMessage = "添加成功";
			url = "sealLogType_showSealLogTypeList.action";
			return "error";
		}
		return "error";
	}

	public SealLogType getSealLogType() {
		return sealLogType;
	}

	public void setSealLogType(SealLogType sealLogType) {
		this.sealLogType = sealLogType;
	}

	public List<SealLogType> getSealLogTypeList() {
		return sealLogTypeList;
	}

	public void setSealLogTypeList(List<SealLogType> sealLogTypeList) {
		this.sealLogTypeList = sealLogTypeList;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public SealLog getSealLog() {
		return sealLog;
	}

	public void setSealLog(SealLog sealLog) {
		this.sealLog = sealLog;
	}

	public SealLogServer getSealLogServer() {
		return sealLogServer;
	}

	public void setSealLogServer(SealLogServer sealLogServer) {
		this.sealLogServer = sealLogServer;
	}

	public List<SealLog> getSealLogList() {
		return sealLogList;
	}

	public void setSealLogList(List<SealLog> sealLogList) {
		this.sealLogList = sealLogList;
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

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

}
