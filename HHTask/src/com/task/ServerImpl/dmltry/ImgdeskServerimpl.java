package com.task.ServerImpl.dmltry;

import java.io.File;
import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.dmltry.ImgdeskServer;
import com.task.entity.dmltry.Imgdesk;
import com.task.util.Upload;
import com.task.util.Util;

public class ImgdeskServerimpl implements ImgdeskServer {

	private TotalDao totalDao;
	
	public TotalDao getTotalDao() {
		return totalDao;
	}
	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	/**
	 * 添加图片
	 */
	@Override
	public String addimg(Imgdesk im) {
		Upload u = new Upload();
		String uploadPath = "/upload/file/img";//路径
		File file1 = new File(uploadPath);
		if (!file1.exists()) {
			file1.mkdirs();// 如果不存在文件夹就创建
		}
		//文件不为空和文件不为空
		if(im.getImgF()!=null &&  !"".equals(im.getImgFFileName())){			
			im.setImg(u.UploadFile(im.getImgF(), im.getImgFFileName(),null,uploadPath,null));
		}
		im.setAddtime(Util.getDateTime());
		im.setUpdatetime(Util.getDateTime());
		//获取当前登录人ID
		boolean pandaun=totalDao.save(im);
		if(pandaun==true){
			return "true";
		}else {
			return "false";
		}
	}
	
	/**
	 * 查看图片
	 */
	@Override
	public List<Imgdesk> img_show() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 删除图片
	 */
	@Override
	public String imgdelte(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
