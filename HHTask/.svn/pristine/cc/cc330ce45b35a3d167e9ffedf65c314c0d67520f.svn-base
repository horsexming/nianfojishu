package com.task.ServerImpl.quality;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.task.Dao.TotalDao;
import com.task.Server.quality.QualityServer;
import com.task.entity.Users;
import com.task.entity.quality.Quality;
import com.task.util.Util;

public class QualityServerImpl implements QualityServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/*
	 * 查询所有(non-Javadoc)
	 * @see com.task.Server.quality.QualityServer#saveGzbjstroe(com.task.entity.quality.Quality, int, int)
	 */
	@Override
	public Object[] findQuailty(Quality quality, int parseNo, int pageSize,Integer test) {
		// TODO Auto-generated method stub
		if (quality == null) {
			quality = new Quality();
		}
		if(quality.getQuality_type()!=null&&quality.getQuality_type().equals("所有")){
			quality.setQuality_type(null);
		}
		Users loginUser=Util.getLoginUser();//获得登陆用户
		String deptname = loginUser.getDept();
		String username = loginUser.getName();
		String hql = totalDao.criteriaQueries(quality, null);
		if(test!=null){
			if(test==2){
				hql = totalDao.criteriaQueries(quality, null);
			}  
			if(test==1){
				hql=hql+" and quality_name='"+deptname+"'";
			}
		}else{
			hql=hql+" and quality_name='"+deptname+"' and quality_pop='"+username+"'";
		}
//		if(test==null){
//			hql=hql+" and quality_name='"+deptname+"' and quality_pop='"+username+"'";
//		}
//		if(test==null){
//			hql=hql+" and quality_name='"+deptname+"' and quality_pop='"+username+"'";
//		}else{
//			hql=hql+" and quality_name='"+deptname+"'";
//		}
		hql+=" order by quality_time";
		List list = totalDao.findAllByPage(hql, parseNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/*
	 * 
	 * 添加(non-Javadoc)
	 * @see com.task.Server.quality.QualityServer#saveQuality(com.task.entity.quality.Quality)
	 */
	@Override
	public void saveQuality(Quality quality) {
		//获取系统当前时间
		if(quality.getQuality_time()==null||quality.getQuality_time().equals("")){
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
			Timestamp timestamp = Timestamp.valueOf(df.format(new java.util.Date())) ;//获取系统当前时间
			quality.setQuality_time(timestamp.toString());
		}
		this.totalDao.save(quality);
	}

	/*
	 * 
	 * 删除(non-Javadoc)
	 * @see com.task.Server.quality.QualityServer#delQuality(java.lang.Integer)
	 */
	@Override
	public void delQuality(Integer delId) {
		// TODO Auto-generated method stub
		Quality quality = (Quality) this.totalDao.getObjectById(Quality.class, delId);
		if(quality.getQuality_file()!=null&&!"".equals(quality.getQuality_file())){
			String fileRealPath = ServletActionContext.getServletContext().getRealPath("/upload/pinzhi")+ "/" + quality.getQuality_file();
			File uploadFile = new File(fileRealPath);
			uploadFile.delete();
		}
		this.totalDao.delete(quality);
		
	}

	/*
	 * 
	 * 根据编号查询(non-Javadoc)
	 * @see com.task.Server.quality.QualityServer#findQuailtyById(java.lang.Integer)
	 */
	@Override
	public Quality findQuailtyById(Integer salId) {
		Quality quality = (Quality) this.totalDao.getObjectById(Quality.class, salId);
		return quality;
	}

	/*
	 * 
	 * 修改(non-Javadoc)
	 * @see com.task.Server.quality.QualityServer#updateQuality(com.task.entity.quality.Quality)
	 */
	@Override
	public void updateQuality(Quality quality) {
		// TODO Auto-generated method stub
		if(quality.getQuality_time()==null||quality.getQuality_time().equals("")){
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
			Timestamp timestamp = Timestamp.valueOf(df.format(new java.util.Date())) ;//获取系统当前时间
			quality.setQuality_time(timestamp.toString());
		}
		Quality quality1 = (Quality) this.totalDao.getObjectById(Quality.class, quality.getId());
		if(quality.getQuality_file()!=null){
//			if(quality1.getQuality_file()!=null){
//				//先删除原有的文件
//				String fileRealPath = ServletActionContext.getServletContext().getRealPath("/upload/pinzhi")+ "/" + quality1.getQuality_file();
//				File uploadFile = new File(fileRealPath);
//				uploadFile.delete();
//			}
			//再经行替换更新
			quality1.setQuality_file(quality.getQuality_file());
		}
			quality1.setQuality_pop(quality.getQuality_pop());
			quality1.setQuality_title(quality.getQuality_title());
			quality1.setQuality_context(quality.getQuality_context());
			quality1.setQuality_name(quality.getQuality_name());
			quality1.setQuality_type(quality.getQuality_type());
			this.totalDao.update(quality1);
	}

	@Override
	public String getFileName(Integer id) {
		// TODO Auto-generated method stub
		List<String> list=totalDao.query("select quality_file from Quality where id=?", id);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
