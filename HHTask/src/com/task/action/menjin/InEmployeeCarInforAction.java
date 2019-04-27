package com.task.action.menjin;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.menjin.InEmployeeCarInforServer;
import com.task.entity.menjin.InEmployeeCarInfor;
import com.task.util.Upload;
import com.task.util.Util;

/**
 * 内部车牌表 20151010_licong
 * 
 * @author Li_Cong
 * 
 */
@SuppressWarnings("unchecked")
public class InEmployeeCarInforAction {
	private InEmployeeCarInfor inEmployeeCarInfor;
	private InEmployeeCarInforServer inEmployeeCarInforServer;
	private List<InEmployeeCarInfor> inEmployeeCarInforList;
	private String errorMessage;
	private String successMessage;

	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	// 上传文件对象
	private File fujian;// 行驶证
	// 上传文件名
	private String fujianFileName;
	// 上传文件类型
	private String fujianContentType;

	private String tag = "";

	private String all = "";

	/**
	 * 跳转到添加方法
	 * 
	 * @return
	 */
	public String toadd() {
		if ("cf".equals(tag)) {
			return "oftenVisitAction_add";
		} else if ("nb".equals(tag)) {
			return "inEmployeeCarInfor_add";
		} else {
			return "error";
		}
	}

	/**
	 * 添加方法
	 * 
	 * @return
	 */
	public String add() {
		if (inEmployeeCarInfor != null) {
			if (!"nb".equals(tag) || !"cf".equals(tag)) {
				if (fujian != null) {
					// 文件路径
					String realFileName = Util.getDateTime("yyyyMMddHHmmss");
					// 打开存放文件的位置
					String realFilePath = "/upload/file/InEmployeeCar";
					String path = ServletActionContext.getServletContext()
							.getRealPath(realFilePath);
					File file = new File(path);
					if (!file.exists()) {
						file.mkdirs();// 如果不存在文件夹就新建
					}
					Upload upload = new Upload();// 文件上传工具类
					realFileName = upload.UploadFile(fujian, fujianFileName,
							null, realFilePath, null);
					inEmployeeCarInfor.setCarFiles(realFilePath + "/"
							+ realFileName);// 文件新名称

				}
				errorMessage = inEmployeeCarInforServer
						.addInEmployeeCarInfor(inEmployeeCarInfor);
				if ("true".equals(errorMessage)) {
					errorMessage = "恭喜！添加成功";
					if ("cf".equals(tag)) {
						errorMessage = "申请成功";
					}
					url = "InEmployeeCarInforAction_showList.action?tag=" + tag
							+ "&all=" + all;
					return "error";
				} else {
					return "error";
				}
			} else {
				errorMessage = "添加对象为非法类型，添加失败";
				return "error";
			}
		}
		errorMessage = "添加对象为空，添加失败";
		return "error";
	}

	/**
	 * 跳转到修改方法
	 * 
	 * @return
	 */
	public String toupdate() {
		InEmployeeCarInfor carInfor = inEmployeeCarInforServer
				.getByIdInEmployeeCarInfor(inEmployeeCarInfor.getId());
		if (carInfor != null) {
			inEmployeeCarInfor = carInfor;
			if ("nb".equals(tag)) {
				return "inEmployeeCarInfor_update";
			} else if ("cf".equals(tag)) {
				return "oftenVisitAction_update";
			} else {
				return "error";
			}
		}
		errorMessage = "不存在该数据!请检查";
		return "error";
	}

	/**
	 * 修改方法
	 * 
	 * @return
	 */
	public String update() {
		if (inEmployeeCarInfor != null) {
			if (fujian != null) {
				// 文件路径
				String realFileName = Util.getDateTime("yyyyMMddHHmmss");
				// 打开存放文件的位置
				String realFilePath = "/upload/file/InEmployeeCar";
				String path = ServletActionContext.getServletContext()
						.getRealPath(realFilePath);
				String path1 = ServletActionContext.getServletContext()
						.getRealPath("");
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();// 如果不存在文件夹就新建
				}
				Upload upload = new Upload();// 文件上传工具类

				// 删除原文件
				InEmployeeCarInfor inEmployeeCarInfor1 = inEmployeeCarInforServer
						.getByIdInEmployeeCarInfor(inEmployeeCarInfor.getId());
				if (inEmployeeCarInfor1 != null
						&& inEmployeeCarInfor1.getCarFiles() != null) {
					File oldFile = new File(path1 + "/"
							+ inEmployeeCarInfor1.getCarFiles());
					if (oldFile.exists()) {
						oldFile.delete();
					}
				}
				realFileName = upload.UploadFile(fujian, fujianFileName, null,
						realFilePath, null);
				inEmployeeCarInfor.setCarFiles(realFilePath + "/"
						+ realFileName);// 文件新名称
			}
			errorMessage = inEmployeeCarInforServer
					.updateInEmployeeCarInfor(inEmployeeCarInfor);
			if ("true".equals(errorMessage)) {
				errorMessage = "修改成功";
				url = "InEmployeeCarInforAction_showList.action?tag=" + tag
						+ "&all=" + all;
				return "error";
			}
			return "error";
		}
		errorMessage = "修改对象为空";
		return "error";
		// 文件路径
	}

	/**
	 * 删除方法
	 * 
	 * @return
	 */
	public String delete() {
		InEmployeeCarInfor carInfor = inEmployeeCarInforServer
				.getByIdInEmployeeCarInfor(inEmployeeCarInfor.getId());
		if (carInfor != null) {
			boolean b = inEmployeeCarInforServer
					.deleteInEmployeeCarInfor(carInfor);
			if (b) {
				errorMessage = "删除成功";
				url = "InEmployeeCarInforAction_showList.action?tag=" + tag
						+ "&all=" + all;
				return "error";
			}
		}
		errorMessage = "";
		return "error";
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 */
	public String showList() {
		if (inEmployeeCarInfor != null) {
			ActionContext.getContext().getSession().put("InEmployeeCarInfor",
					inEmployeeCarInfor);
		} else {
			inEmployeeCarInfor = (InEmployeeCarInfor) ActionContext
					.getContext().getSession().get("InEmployeeCarInfor");
		}
		Map<Integer, Object> map = inEmployeeCarInforServer
				.findInEmployeeCarInforByCondition(inEmployeeCarInfor, Integer
						.parseInt(cpage), pageSize, tag, all);
		inEmployeeCarInforList = (List<InEmployeeCarInfor>) map.get(1);// 显示面试单列表
		if (inEmployeeCarInforList != null && inEmployeeCarInforList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("InEmployeeCarInforAction_showList.action?tag=" + tag + "&all=" + all);
			errorMessage = null;
		} else {
			errorMessage = "没有符合条件的信息,请检查后重试!";
		}
		if ("cf".equals(tag)) {
			return "oftenVisitAction_show";
		} else if ("nb".equals(tag)) {
			return "inEmployeeCarInfor_show";
		} else {
			errorMessage = "没有符合条件的类型,请检查后重试!";
			return "error";
		}
	}

	public String agreen() {
		InEmployeeCarInfor carInfor = inEmployeeCarInforServer
				.getByIdInEmployeeCarInfor(inEmployeeCarInfor.getId());
		if (carInfor!=null) {
			errorMessage = inEmployeeCarInforServer.agreen(carInfor);
			if ("true".equals(errorMessage)) {
				errorMessage = "成功再次申请！";
				url = "InEmployeeCarInforAction_showList.action?tag=" + tag
				+ "&all=" + all;
				return "error";
			}
		}
		return "error";
	}
	public String exportExcel(){
		inEmployeeCarInforServer.exportExcel(inEmployeeCarInfor);
		return "error";
	}
	public String exportExcel_1(){
		inEmployeeCarInforServer.exportExcel_1(inEmployeeCarInfor);
		return "error";
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

	public InEmployeeCarInfor getInEmployeeCarInfor() {
		return inEmployeeCarInfor;
	}

	public void setInEmployeeCarInfor(InEmployeeCarInfor inEmployeeCarInfor) {
		this.inEmployeeCarInfor = inEmployeeCarInfor;
	}

	public InEmployeeCarInforServer getInEmployeeCarInforServer() {
		return inEmployeeCarInforServer;
	}

	public void setInEmployeeCarInforServer(
			InEmployeeCarInforServer inEmployeeCarInforServer) {
		this.inEmployeeCarInforServer = inEmployeeCarInforServer;
	}

	public List<InEmployeeCarInfor> getInEmployeeCarInforList() {
		return inEmployeeCarInforList;
	}

	public void setInEmployeeCarInforList(
			List<InEmployeeCarInfor> inEmployeeCarInforList) {
		this.inEmployeeCarInforList = inEmployeeCarInforList;
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getAll() {
		return all;
	}

	public void setAll(String all) {
		this.all = all;
	}
}
