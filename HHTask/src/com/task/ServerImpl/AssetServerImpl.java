package com.task.ServerImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.task.Dao.TotalDao;
import com.task.Server.AssetServer;
import com.task.entity.Asset;
import com.task.util.Upload;
import com.task.util.Util;

public class AssetServerImpl implements AssetServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 添加 固定资产表中的数据
	public boolean addAsset(Asset ass) {
		if (ass != null) {
			return this.totalDao.save(ass);
		}
		return false;
	}

	// 查询固定资产表中的所有数据 分页
	public List findShouList(int pageNo, int pageSize) {
		String hql = "from Asset order by tadatetime desc";
		return this.totalDao.findAllByPage(hql, pageNo, pageSize);
	}

	// 获得所有数量
	public Integer getCount() {
		String hql = "from Asset";
		return totalDao.getCount(hql);
	}

	// 根据ID查询所有信息
	public Asset findAssetById(int id) {
		return (Asset) this.totalDao.getObjectById(Asset.class, id);
	}

	// 模糊查询
	public List findUsersByCondition(Asset asset, int pageNo, int pageSize) {
		String hql = this.totalDao.criteriaQueries(asset, null, null);
		return this.totalDao.findAllByPage(hql, pageNo, pageSize);
	}

	// 获得模糊查询的总数量
	public int findUsersByConditioncount(Asset asset) {
		String hql = this.totalDao.criteriaQueries(asset, null, null);
		return this.totalDao.getCount(hql);
	}

	// 修改
	public boolean updateAsset(Asset asset) {
		if (asset != null) {
			return this.totalDao.update(asset);
		}
		return false;
	}

	@Override
	public boolean uploadFile(Integer id, File fileUpload,
			String fileUploadFileName, String fileUploadContentType) {
		try {
			String filename = "";
			String Path;
			Path = "/upload/file/aesset/" + id + "/";
			String realFilePath = ServletActionContext.getServletContext()
					.getRealPath(Path);

			// 如果不存在文件夹就创建
			File file = new File(realFilePath);
			
			String[] content = file.list();// 取得当前目录下所有文件和文件夹
			for (String string : content) {
				File old_file = new File(realFilePath+"\\"+string);
				boolean tf=old_file.delete();
			}
			if (!file.exists()) {
				file.mkdirs();
			}
			filename = Util.getDateTime("yyyyMMddHHmmss_") + fileUploadFileName;
			FileOutputStream fos = new FileOutputStream(realFilePath + "\\"
					+ filename);
			// 建立上传文件的输入流
			FileInputStream fis = new FileInputStream(fileUpload);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			fis.close();

		} catch (Exception e) {
			return false;
		}
		return true;

	}

}
