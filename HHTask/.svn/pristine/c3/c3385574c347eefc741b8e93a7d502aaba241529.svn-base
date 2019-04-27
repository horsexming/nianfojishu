package com.task.ServerImpl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.FileCopyUtils;

import com.task.Dao.TotalDao;
import com.task.Server.KeHuManYiDiaoChaServer;
import com.task.entity.ClientManagement;
import com.task.entity.KeHuManYiDiaoCha;
import com.task.entity.QuestionnairePerson;
import com.task.util.Util;

public class KeHuManYiDiaoChaServerImpl implements KeHuManYiDiaoChaServer{

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	@Override
	public boolean add(KeHuManYiDiaoCha khmydc,File[] attachment,
			String[] attachmentFileName,String status) {
		if(khmydc!=null){
			// 上传附件
			String attachmentName = "";
			if (attachment != null && attachment.length > 0) {
				for (int i = 0; i < attachment.length; i++) {
					String fileName =  Util.getDateTime("yyyyMMddHHmmss")+i+ (attachmentFileName[i]
					                                       									.substring(attachmentFileName[i]
					                                       																.lastIndexOf(".")));
							
					if (i > 0) {
						attachmentName += "#" + fileName;
					} else {
						attachmentName += fileName;
					}
					attachmentName.trim();
					// 上传到服务器
					String fileRealPath = ServletActionContext
							.getServletContext().getRealPath("/upload/file")
							+ "/" + fileName;
					File file = new File(fileRealPath);
					try {
						FileCopyUtils.copy(attachment[i], file);
					} catch (Exception e) {
						return false;
					}

					// 备份到项目
					String beiFenfileRealPath = "D:/WorkSpace/HHTask/WebRoot/upload/file"
							+ "/" + fileName;
					File beiFenFile = new File(beiFenfileRealPath);
					try {
						FileCopyUtils.copy(attachment[i], beiFenFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				String[] strarray = attachmentName.split("#");
				if(strarray!=null && strarray.length >0){
					if("month".equals(status)){
						khmydc.setMonthdcb(strarray[0]);
					}else if("jd".equals(status)){
						khmydc.setQuarterbg(strarray[0]);
					}else if("year".equals(status)){
						khmydc.setYearbg(strarray[0]);
					}
				}
			} else{
				return false;
			}
			return	totalDao.save(khmydc);
		}
		return false;
	}

	@Override
	public boolean del(KeHuManYiDiaoCha khmydc) {
		if(khmydc!=null){
			// 删除文件
			String fileRealPath = "D:/WorkSpace/HHTask/WebRoot/upload/file";
			String fileRealPath2 = ServletActionContext.getServletContext()
					.getRealPath("/upload/file")
					+ "/";
			String resume  = "";
			String[] arraystr =null;
			arraystr =	resume.split("#");
			boolean bool = false;
					fileRealPath +=khmydc.getMonthdcb();
					fileRealPath2 += khmydc.getMonthdcb();
					File oldFile = new File(fileRealPath);
					File oldFile2 = new File(fileRealPath2);
					if (!oldFile.isDirectory()) {
						oldFile.delete();
						if (!oldFile2.isDirectory()) {
							oldFile2.delete();
							bool = true;
						}
					}
					fileRealPath +=khmydc.getQuarterbg();
					fileRealPath2 += khmydc.getQuarterbg();
					File oldFile3 = new File(fileRealPath);
					File oldFile4 = new File(fileRealPath2);
					if (!oldFile3.isDirectory()) {
						oldFile.delete();
						if (!oldFile4.isDirectory()) {
							oldFile4.delete();
							bool = true;
						}
					}
					fileRealPath +=khmydc.getYearbg();
					fileRealPath2 += khmydc.getYearbg();
					File oldFile5 = new File(fileRealPath);
					File oldFile6 = new File(fileRealPath2);
					if (!oldFile5.isDirectory()) {
						oldFile.delete();
						if (!oldFile6.isDirectory()) {
							oldFile6.delete();
							bool = true;
						}
					}
				
			return		totalDao.delete(khmydc);
		}
		return false;
	}
	public List queryAllClient() {
		String hql = "from ClientManagement";
		List<ClientManagement> list = totalDao.query(hql, null);
		return list;
	}
	
	@Override
	public Map<Integer, Object> findAllkhmydclist(KeHuManYiDiaoCha khmydc,
			int pageNo, int pageSize, String statue) {
		if(khmydc == null){
			khmydc = new KeHuManYiDiaoCha();
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		String hql = totalDao.criteriaQueries(khmydc, null);
		int count = totalDao.getCount(hql);
		List<KeHuManYiDiaoCha> qpList = (List<KeHuManYiDiaoCha>) totalDao
				.findAllByPage(hql+" order by id desc", pageNo, pageSize);
		map.put(1, qpList);
		map.put(2, count);
		return map;
	}

	@Override
	public boolean update(KeHuManYiDiaoCha khmydc,File[] attachment,
			String[] attachmentFileName) {
		 if(khmydc!=null){
				String attachmentName = "";
				if (attachment != null && attachment.length > 0) {
					for (int i = 0; i < attachment.length; i++) {
						String fileName =  new SimpleDateFormat("yyyyMMddHHmmss")
										.format(new Date())
								+ (attachmentFileName[i]
										.substring(attachmentFileName[i]
												.lastIndexOf(".")));
						if (i > 0) {
							attachmentName += "|" + fileName;
						} else {
							attachmentName += fileName;
						}
						attachmentName.trim();
						// 上传到服务器
						String fileRealPath = ServletActionContext
								.getServletContext().getRealPath("/upload/file")
								+ "/" + fileName;
						File file = new File(fileRealPath);
						try {
							FileCopyUtils.copy(attachment[i], file);
						} catch (Exception e) {
							return false;
						}

						// 备份到项目
						String beiFenfileRealPath = "D:/WorkSpace/HHTask/WebRoot/upload/file"
								+ "/" + fileName;
						File beiFenFile = new File(beiFenfileRealPath);
						try {
							FileCopyUtils.copy(attachment[i], beiFenFile);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					String[] strarray = attachmentName.split("|");
					if(strarray!=null && strarray.length ==3){
						khmydc.setMonthdcb(strarray[0]);
						khmydc.setQuarterbg(strarray[1]);
						khmydc.setYearbg(strarray[2]);
					}
				}
			return	totalDao.update(khmydc);
		 }
		return false;
	}

}
