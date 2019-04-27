package com.task.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.corba.se.spi.orb.StringPair;
import com.task.Server.AssetServer;
import com.task.entity.Asset;
import com.task.util.Util;

public class AssetAction extends ActionSupport {

	private Asset aesset;// 固定资产表
	private AssetServer aessetdao;
	
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	//
	private String errorMessage;// 错误消息
	
	private String aessetFilePath;//合同路径
	private File assetAttachment;
	private String assetAttachmentContentType;
	private String assetAttachmentFileName;

	public AssetServer getAessetdao() {
		return aessetdao;
	}

	public void setAessetdao(AssetServer aessetdao) {
		this.aessetdao = aessetdao;
	}

	public Asset getAesset() {
		return aesset;
	}

	public void setAesset(Asset aesset) {
		this.aesset = aesset;
	}

	// 增加 固定资产信息
	private String assetname;
	private String tem;

	@Override
	public String execute() throws Exception {

		String writeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		aesset.setTadatetime(writeDate);
		if(assetAttachment!=null){
			aessetdao.uploadFile(aesset.getId(),assetAttachment,assetAttachmentFileName,assetAttachmentContentType);
		}
		aessetdao.addAsset(aesset);
		assetname = aesset.getTaassetsname(); // 资产名称
		tem = "123";
		return super.execute();
	}

	// 查询固定资产的所有信息 分页
	private List aessetlist;

	public String findShouList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		aessetlist = aessetdao.findShouList(Integer.parseInt(cpage), pageSize);
		this.setUrl("AssetAction!findShouList.action");
		this.cpage = request.getParameter("cpage");
		if ("".equals(cpage) || null == cpage) {
			cpage = 1 + "";
		}
		int count = aessetdao.getCount();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		
		return "findShouList";//shouAssetList.jsp
	}

	// 条件查询固定资产中的信息
	private Asset assets;

	public String mohufindShoList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		aessetlist = aessetdao.findUsersByCondition(assets, Integer
				.parseInt(cpage), pageSize);// 条件查询所有用户
		this.setUrl("AssetAction!mohufindShoList.action");
		this.cpage = request.getParameter("cpage");
		if ("".equals(cpage) || null == cpage) {
			cpage = 1 + "";
		}
		int count = aessetdao.findUsersByConditioncount(assets);
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		return "findShouList";
	}

	// 查看固定资产详细信息
	private int id;

	public String findByID() {
		//合同路径
		String realFilePath = ServletActionContext.getServletContext()
				.getRealPath("/upload/file/aesset/" + id + "/");
		File f=new File(realFilePath);
		String[] filelist=f.list();
		aessetFilePath="/upload/file/aesset/"+id+"/"+filelist[0];
		//
		aesset = aessetdao.findAssetById(id);
		return "findByID";
	}

	// 获得选中需要报废的信息
	public String updateAssetstatus() {
		aesset = aessetdao.findAssetById(id);
		return "updateAssetstatus";
	}

	// 修改需要报废的信息和状态

	public String updateAssetscrappedSingle() {
		aesset.setTastatus("报废");
		aessetdao.updateAsset(aesset);
		return "updateAssetscrappedSingle";
	}

	// 打印 报废单
	private String shebeinumber; // 设备报废移交验收单编号

	public String scrappedSingle() {
		String number = "QR/SH.";
		String date = new SimpleDateFormat("yyyy.MM-dd").format(new Date());
		shebeinumber = number + date;
		aesset = aessetdao.findAssetById(id);
		return "scrappedSingle";
	}

	// 修改 前
	public String updateAssetFindID() {
		aesset = aessetdao.findAssetById(id);
		return "updateAssetFindID";
	}

	// 修改
	public String updateAsset() {
		if(assetAttachment!=null){
			aessetdao.uploadFile(aesset.getId(),assetAttachment,assetAttachmentFileName,assetAttachmentContentType);
		}
		aessetdao.updateAsset(aesset);
		return "updateAsset";
	}

	// 构造方法
	public List getAessetlist() {
		return aessetlist;
	}

	public void setAessetlist(List aessetlist) {
		this.aessetlist = aessetlist;
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

	public String getAssetname() {
		return assetname;
	}

	public void setAssetname(String assetname) {
		this.assetname = assetname;
	}

	public String getTem() {
		return tem;
	}

	public void setTem(String tem) {
		this.tem = tem;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShebeinumber() {
		return shebeinumber;
	}

	public void setShebeinumber(String shebeinumber) {
		this.shebeinumber = shebeinumber;
	}

	public Asset getAssets() {
		return assets;
	}

	public void setAssets(Asset assets) {
		this.assets = assets;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public File getAssetAttachment() {
		return assetAttachment;
	}

	public void setAssetAttachment(File assetAttachment) {
		this.assetAttachment = assetAttachment;
	}

	public String getAssetAttachmentContentType() {
		return assetAttachmentContentType;
	}

	public void setAssetAttachmentContentType(String assetAttachmentContentType) {
		this.assetAttachmentContentType = assetAttachmentContentType;
	}

	public String getAssetAttachmentFileName() {
		return assetAttachmentFileName;
	}

	public void setAssetAttachmentFileName(String assetAttachmentFileName) {
		this.assetAttachmentFileName = assetAttachmentFileName;
	}

	public String getAessetFilePath() {
		return aessetFilePath;
	}

	public void setAessetFilePath(String aessetFilePath) {
		this.aessetFilePath = aessetFilePath;
	}


}
