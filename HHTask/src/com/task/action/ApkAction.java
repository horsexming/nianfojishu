package com.task.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.android.ApkServer;
import com.task.Server.dmltry.DmlAppFileUrlServer;
import com.task.Server.dmltry.DmltryAppFilesServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.entity.dmltry.DmlAppFileUrl;
import com.task.entity.dmltry.DmltryAppFiles;
import com.task.entity.rtx.RtxMsg;
import com.task.util.MKUtil;

public class ApkAction extends ActionSupport {
	
	private ApkServer apkServer;
	private String versionCode;
	private String appFilename;//字母
	private DmlAppFileUrl dmlAppFileUrl;
	private DmltryAppFiles dmltryAppFiles;
	private DmlAppFileUrlServer dmlAppFileUrlServer;
	private DmltryAppFilesServer dmltryAppFilesServer;
	private List<DmlAppFileUrl> dmlAppFileUrllist;
	private String appfileurlfj;
	
	public void compareApk() {
		dmlAppFileUrl = dmlAppFileUrlServer.search(appFilename);

		Map<String, Object> map = new HashMap<String, Object>();
		if (dmlAppFileUrl == null) {
			String latLong = apkServer.findJWD_1();
			map.put("istrue", true);
			map.put("url", latLong);
		} else {
			appfileurlfj = dmlAppFileUrl.getAppfileurlfj();
			if (versionCode == null || "".equals(versionCode)
					|| (!"".equals(versionCode) && dmlAppFileUrl.getCoide() > Integer.parseInt(versionCode))) {
				String url = AlertMessagesServerImpl.pebsUrl + "/upload/file/appFile" + appfileurlfj;
				map.put("istrue", false);
				map.put("url", url);
			}
		}
		MKUtil.writeJSON(map);
	}
	
	public void shouJianApk() {
		Properties prop = new Properties();
		String path = ServletActionContext.getServletContext().getRealPath(
		"/apkVersion1.properties");
		InputStream in = null;
		try {
			in = new FileInputStream(path);
			prop.load(in);
			String versionCode1 = prop.getProperty("versionCode");
			Map<String, Object> map = new HashMap<String, Object>();
			if (versionCode == null || !versionCode.equals(versionCode1)) {
				String url = prop.getProperty("url");
				map.put("istrue", false);
				map.put("url", url);
			} else {
				map.put("istrue", true);
				map.put("url", null);
			}
			MKUtil.writeJSON(map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//安卓读取经纬度
	public void findJWD() {
		List list = apkServer.findJWD();
		if (list != null && list.size() > 0) {
			MKUtil.writeJSON(true, "查询成功", list);
		} else {
			MKUtil.writeJSON(false, "没有提交的数据!", list);
		}
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public ApkServer getApkServer() {
		return apkServer;
	}

	public void setApkServer(ApkServer apkServer) {
		this.apkServer = apkServer;
	}

	public DmlAppFileUrl getDmlAppFileUrl() {
		return dmlAppFileUrl;
	}

	public void setDmlAppFileUrl(DmlAppFileUrl dmlAppFileUrl) {
		this.dmlAppFileUrl = dmlAppFileUrl;
	}

	public DmltryAppFiles getDmltryAppFiles() {
		return dmltryAppFiles;
	}

	public void setDmltryAppFiles(DmltryAppFiles dmltryAppFiles) {
		this.dmltryAppFiles = dmltryAppFiles;
	}

	public DmlAppFileUrlServer getDmlAppFileUrlServer() {
		return dmlAppFileUrlServer;
	}

	public void setDmlAppFileUrlServer(DmlAppFileUrlServer dmlAppFileUrlServer) {
		this.dmlAppFileUrlServer = dmlAppFileUrlServer;
	}

	public DmltryAppFilesServer getDmltryAppFilesServer() {
		return dmltryAppFilesServer;
	}

	public void setDmltryAppFilesServer(DmltryAppFilesServer dmltryAppFilesServer) {
		this.dmltryAppFilesServer = dmltryAppFilesServer;
	}

	public List<DmlAppFileUrl> getDmlAppFileUrllist() {
		return dmlAppFileUrllist;
	}

	public void setDmlAppFileUrllist(List<DmlAppFileUrl> dmlAppFileUrllist) {
		this.dmlAppFileUrllist = dmlAppFileUrllist;
	}

	public String getAppfileurlfj() {
		return appfileurlfj;
	}

	public void setAppfileurlfj(String appfileurlfj) {
		this.appfileurlfj = appfileurlfj;
	}

	public String getAppFilename() {
		return appFilename;
	}

	public void setAppFilename(String appFilename) {
		this.appFilename = appFilename;
	}

}
