package com.task.action.dangan;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.dangan.DanganServer;
import com.task.entity.dangan.ArchiveUnarchiverAplt;
import com.task.entity.dangan.DangAn;
import com.task.entity.menjin.AccessEquipment;
import com.task.util.MKUtil;
import com.task.util.Upload;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class DanganAction {
	private DanganServer danganServer;
	private DangAn dangAn;
	private ArchiveUnarchiverAplt archiveUnarchiverAplt;
	private List<DangAn> dangAnList;
	private List<ArchiveUnarchiverAplt> archiveUnarchiverApltList;//存取档案列表
	private String errorMessage;
	private String successMessage;

	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	private String aceName;//门禁名称，用来获取编号
	
	// 上传文件对象
	private File fujian;// 档案
	// 上传文件名
	private String fujianFileName;
	// 上传文件类型
	private String fujianContentType;

	private String taga = "";// 申请部门.所有(all/dept/code)
	private String tag = "";// cw财务

	// 添加
	public String add() {
		if (dangAn != null) {
			if (fujian != null) {
				// 文件路径
				String realFileName = Util.getDateTime("yyyyMMddHHmmss");
				// 打开存放文件的位置
				String realFilePath = "/upload/file/dangan";
				String path = ServletActionContext.getServletContext()
						.getRealPath(realFilePath);
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();// 如果不存在文件夹就新建
				}
				Upload upload = new Upload();// 文件上传工具类
				realFileName = upload.UploadFile(fujian, fujianFileName,
						null, realFilePath, null);
				dangAn.setDaFiles(realFilePath + "/"
						+ realFileName);// 文件新名称

			}
			
			errorMessage = danganServer.addDangAn(dangAn, archiveUnarchiverApltList);
			if ("申请成功".equals(errorMessage))
				url = "DanganAction_showList.action";
		}
		return "error";
	}
	
	// 添加财务档案申请
	public String addCw() {
		if (dangAn != null) {
			errorMessage = danganServer.addCwDangAn(dangAn, archiveUnarchiverApltList);
			if ("申请成功".equals(errorMessage))
				url = "DanganAction_showList.action";
		}
		return "error";
	}
	
	// 添加财务发票申请
	public String addFp() {
		if (dangAn != null) {
			errorMessage = danganServer.addFpDangAn(dangAn, archiveUnarchiverApltList);
			if ("申请成功".equals(errorMessage))
				url = "DanganAction_showList.action?tag=fp";
		}
		return "error";
	}

	// 跳转到档案存取申请
	public String toadd() {
		return "dangan_add";
	}
	// 跳转到档案存取申请
	public String toaddFp() {
		return "dangan_add_fp";
	}
	
	// 跳转到来访登记表
	public String toadd_1() {
		return "dangan_add_qu";
	}

	// 跳转查询页面
	public String showList() {
		if (dangAn != null) {
			ActionContext.getContext().getSession().put("DangAn", dangAn);
		} else {
			dangAn = (DangAn) ActionContext.getContext().getSession().get(
					"DangAn");
		}
		Map<Integer, Object> map = danganServer.findDangAnByCondition(dangAn,
				Integer.parseInt(cpage), pageSize, tag);
		dangAnList = (List<DangAn>) map.get(1);// 显示档案列表
		if (dangAnList != null && dangAnList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("DanganAction_showList.action");
			errorMessage = null;
		} else {
			errorMessage = "没有符合条件的信息,请检查后重试!";
		}
		return "dangan_show";
	}
	
	// 跳转查询页面
	public String showList_code() {
		//archiveUnarchiverAplt = danganServer.findaceNum_1();
		archiveUnarchiverApltList = danganServer.byIdArchiveUnarchiverAplt();
		return "dangan_show_1";
	}

	/**
	 * 跳转到修改方法
	 * 
	 * @return
	 */
	public String toupdate() {
		if (dangAn != null) {
			DangAn dangAn1 = danganServer.byIdDangan(dangAn.getId());
			if (dangAn1 != null) {
				dangAn = dangAn1;
				return "dangan_update";
			}
			errorMessage = "不存在该数据!请检查";
			return "error";
		}
		errorMessage = "数据为空!请检查";
		return "error";
	}

	public String toSelect() {
		if (dangAn != null) {
			DangAn dangAn1 = danganServer.byIdDangan(dangAn.getId());
			if (dangAn1 != null) {
				dangAn = dangAn1;
				archiveUnarchiverApltList = null;
				archiveUnarchiverApltList = danganServer.byIdFindDangan(dangAn
						.getId());
				return "dangan_select";
			}
			errorMessage = "不存在该数据!请检查";
			return "error";
		}
		errorMessage = "数据为空!请检查";
		return "error";
	}
	
	/**
	 * 修改方法
	 * 
	 * @return
	 */
	public String update() {
		if (fujian != null) {
			// 文件路径
			String realFileName = Util.getDateTime("yyyyMMddHHmmss");
			// 打开存放文件的位置
			String realFilePath = "/upload/file/dangan";
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
			DangAn dangan1 = danganServer
					.byIdDangan(dangAn.getId());
			if (dangan1 != null
					&& dangan1.getDaFiles() != null) {
				File oldFile = new File(path1 + "/"
						+ dangan1.getDaFiles());
				if (oldFile.exists()) {
					oldFile.delete();
				}
			}
			realFileName = upload.UploadFile(fujian, fujianFileName, null,
					realFilePath, null);
			dangAn.setDaFiles(realFilePath + "/"
					+ realFileName);// 文件新名称
		}
		
		errorMessage = danganServer.updateDangAn(dangAn);
		if ("修改成功".equals(errorMessage))
			url = "DanganAction_showList.action";
		return "error";
	}

	public String delete(){
		DangAn an = danganServer.byIdDangan(dangAn.getId());
		if (an!=null) {
			errorMessage = danganServer.deleteDangAn(dangAn.getId());
			if ("删除成功".equals(errorMessage))
				url = "DanganAction_showList.action";
		}
		return "error";
	}
	/**
	 * 查询价格表编号，下拉
	 * @return
	 */
	public String findSelectPriceNum(){
		String message = danganServer.findPriceNum();
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
	public String findSelectName(){
		String message = danganServer.findcaeNameNum();
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
	/***
	 * 通过门禁名称查编号(输出json)
	 */
	public void findacEJson() {
		List<AccessEquipment> list = danganServer.findaceNum(aceName);
		MKUtil.writeJSON(list);
	}

	
	public DanganServer getDanganServer() {
		return danganServer;
	}

	public void setDanganServer(DanganServer danganServer) {
		this.danganServer = danganServer;
	}

	public DangAn getDangAn() {
		return dangAn;
	}

	public void setDangAn(DangAn dangAn) {
		this.dangAn = dangAn;
	}

	public List<DangAn> getDangAnList() {
		return dangAnList;
	}

	public void setDangAnList(List<DangAn> dangAnList) {
		this.dangAnList = dangAnList;
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

	public String getTaga() {
		return taga;
	}

	public void setTaga(String taga) {
		this.taga = taga;
	}

	public String getAceName() {
		return aceName;
	}

	public void setAceName(String aceName) {
		this.aceName = aceName;
	}

	public List<ArchiveUnarchiverAplt> getArchiveUnarchiverApltList() {
		return archiveUnarchiverApltList;
	}

	public void setArchiveUnarchiverApltList(
			List<ArchiveUnarchiverAplt> archiveUnarchiverApltList) {
		this.archiveUnarchiverApltList = archiveUnarchiverApltList;
	}

	public ArchiveUnarchiverAplt getArchiveUnarchiverAplt() {
		return archiveUnarchiverAplt;
	}

	public void setArchiveUnarchiverAplt(ArchiveUnarchiverAplt archiveUnarchiverAplt) {
		this.archiveUnarchiverAplt = archiveUnarchiverAplt;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
