package com.task.ServerImpl.Pfmeas;


import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.task.Dao.TotalDao;
import com.task.Server.Pfmeas.PfmeasServer;
import com.task.entity.Users;
import com.task.entity.Pfmeas.Pfmeas;
import com.task.entity.quality.Quality;
import com.task.util.Util;

public class PfmeasServerImpl implements PfmeasServer{
     
	   
	TotalDao totalDao;

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
	public Object[] findPfmeas(Pfmeas pfmeas, int parseNo, int pageSize,Integer test) {
		// TODO Auto-generated method stub
		if (pfmeas == null) {
			pfmeas = new Pfmeas();
		}
		
		Users loginUser=Util.getLoginUser();//获得登陆用户
		String deptname = loginUser.getDept();
		String username = loginUser.getName();
		String hql = totalDao.criteriaQueries(pfmeas, null);
		if(test!=null){
			if(test==2){
				hql = totalDao.criteriaQueries(pfmeas, null);
			}  
			if(test==1){
				hql=hql+" and pfmeas_name='"+deptname+"'";
			}
		}else{
			hql=hql+" and pfmeas_name='"+deptname+"' and pfmeas_pop='"+username+"'";
		}
//		if(test==null){
//			hql=hql+" and quality_name='"+deptname+"' and quality_pop='"+username+"'";
//		}
//		if(test==null){
//			hql=hql+" and quality_name='"+deptname+"' and quality_pop='"+username+"'";
//		}else{
//			hql=hql+" and quality_name='"+deptname+"'";
//		}
		hql+=" order by pfmeas_time";
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
	public void savePfmeas(Pfmeas pfmeas) {
		//获取系统当前时间
		if(pfmeas.getPfmeas_time()==null||pfmeas.getPfmeas_time().equals("")){
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
			Timestamp timestamp = Timestamp.valueOf(df.format(new java.util.Date())) ;//获取系统当前时间
		
		}
		this.totalDao.save(pfmeas);
	}

	/*
	 * 
	 * 删除(non-Javadoc)
	 * @see com.task.Server.quality.QualityServer#delQuality(java.lang.Integer)
	 */
	@Override
	public void delPfmeas(Integer delId) {
		// TODO Auto-generated method stub
		Pfmeas pfmeas = (Pfmeas) this.totalDao.getObjectById(Pfmeas.class, delId);
		if(pfmeas.getPfmeas_time()!=null&&!"".equals(pfmeas.getPfmeas_time())){
			String fileRealPath = ServletActionContext.getServletContext().getRealPath("/upload/pinzhi")+ "/" + pfmeas.getPfmeas_time();
			File uploadFile = new File(fileRealPath);
			uploadFile.delete();
		}
		this.totalDao.delete(pfmeas);
		
	}

	/*
	 * 
	 * 根据编号查询(non-Javadoc)
	 * @see com.task.Server.quality.QualityServer#findQuailtyById(java.lang.Integer)
	 */
	@Override
	public Pfmeas findPfmeasById(Integer salId) {
		Pfmeas pfmeas = (Pfmeas) this.totalDao.getObjectById(Pfmeas.class, salId);
		return pfmeas;
	}

	/*
	 * 
	 * 修改(non-Javadoc)
	 * @see com.task.Server.quality.QualityServer#updateQuality(com.task.entity.quality.Quality)
	 */
	@Override
	public void updatePfmeas(Pfmeas pfmeas) {
		// TODO Auto-generated method stub
		if(pfmeas.getPfmeas_time()==null||pfmeas.getPfmeas_time().equals("")){
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
			Timestamp timestamp = Timestamp.valueOf(df.format(new java.util.Date())) ;//获取系统当前时间
			
		}
		Pfmeas pfmeas1 = (Pfmeas) this.totalDao.getObjectById(Pfmeas.class, pfmeas.getId());
		if(pfmeas.getPfmeas_time()!=null){
//			if(quality1.getQuality_file()!=null){
//				//先删除原有的文件
//				String fileRealPath = ServletActionContext.getServletContext().getRealPath("/upload/pinzhi")+ "/" + quality1.getQuality_file();
//				File uploadFile = new File(fileRealPath);
//				uploadFile.delete();
//			}
			//再进行替换更新
			pfmeas1.setPfmeas_file(pfmeas.getPfmeas_time());
		}
		pfmeas1.setPfmeas_pop(pfmeas.getPfmeas_pop());
		pfmeas1.setPfmeas_title(pfmeas.getPfmeas_title());
		pfmeas1.setPfmeas_context(pfmeas.getPfmeas_context());
		pfmeas1.setPfmeas_name(pfmeas.getPfmeas_name());
		
			this.totalDao.update(pfmeas1);
	}

	@Override
	public String getFileName(Integer id) {
		// TODO Auto-generated method stub
		List<String> list=totalDao.query("select pfmeas_file from Pfmeas where id=?", id);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	
	
	   
}
