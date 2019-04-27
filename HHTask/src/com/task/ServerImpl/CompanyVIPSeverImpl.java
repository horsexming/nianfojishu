package com.task.ServerImpl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.util.FileCopyUtils;

import com.task.Dao.TotalDao;
import com.task.Server.CompanyVIPServer;
import com.task.entity.CompanyBoss;
import com.task.entity.CompanyVIP;
import com.task.entity.sop.BuHeGePin;
import com.task.util.Util;

public class CompanyVIPSeverImpl implements CompanyVIPServer{
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	
	@Override
	public List<CompanyVIP> FindAllCompanyVIP(int pageNo, int pageSize) {
		String hql ="from  CompanyVIP";
		return (List<CompanyVIP>) totalDao.findAllByPage(hql, pageNo, pageSize);
	}

	@Override
	public boolean add(CompanyVIP companyVIP) {
		if (companyVIP != null) {
			companyVIP.setWriteTime(Util.getDateTime());//填表时间
			CompanyBoss cb=	companyVIP.getCompanyboss();
			cb.setCompanyVIP(companyVIP);
			return 	totalDao.save(companyVIP);
			}
		return false;
	}

	@Override
	public boolean del(CompanyVIP companyVIP) {
		if(companyVIP!=null){
			companyVIP = (CompanyVIP) totalDao.get(CompanyVIP.class, companyVIP.getId());
			CompanyBoss cb = companyVIP.getCompanyboss();
			cb.setCompanyVIP(companyVIP);
				totalDao.delete(cb);
		return	totalDao.delete(companyVIP);
		}
		
		return false;
	}

	@Override
	public Map<Integer, Object> findCompanyVIPCondition(
			CompanyVIP companyVIP, int pageNo, int pageSize) {
		if(companyVIP == null){
			companyVIP = new CompanyVIP();
		}
		Map<Integer,Object> map=new HashMap<Integer, Object>();
		String hql=	totalDao.criteriaQueries(companyVIP, null);
		int count=totalDao.getCount(hql);
		List<CompanyVIP> companyVIPList=(List<CompanyVIP>)totalDao.findAllByPage(hql, pageNo, pageSize);
		map.put(1, companyVIPList);
		map.put(2, count);
		return map;
	
	}

	@Override
	public boolean update(CompanyVIP companyVIP ,File[] attachment,
			String[] attachmentFileName, String fatherPartNumber) {
		if(companyVIP!=null){
			// 上传附件
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
			} 
			CompanyVIP oldcompanyVIP =	(CompanyVIP) totalDao.get(CompanyVIP.class, companyVIP.getId());
			CompanyBoss cb = companyVIP.getCompanyboss();
			CompanyBoss oldcb = (CompanyBoss) totalDao.get(CompanyBoss.class, cb.getId());
			BeanUtils.copyProperties(companyVIP, oldcompanyVIP,new String[]{"id,companyboss"});
			BeanUtils.copyProperties(cb, oldcb,new String[]{"id,companyVIP"});
			if(attachmentName!=null && !"".equals(attachmentName)){
				oldcompanyVIP.setAttachmentName(attachmentName);
			}
			oldcb.setCompanyVIP(oldcompanyVIP);
			oldcompanyVIP.setCompanyboss(oldcb);
			 return 	totalDao.update(oldcompanyVIP);
		}
		return false;
	}
	
	
	
	
	@Override
	public String findMaxvipNO() {
		String hql =" select max(vipNo) from  CompanyVIP ";
		String vipno =(String) totalDao.getObjectByCondition(hql);
		if(vipno!=null){
			Integer vipNonum = Integer.parseInt(vipno)+1000001;
			vipno =	vipNonum.toString().substring(1, 7);
		}else{
			vipno = "000001";
		}
		return vipno;
	}

	@Override
	public CompanyBoss findCompanyBossById(Integer id) {
		if(id!=null && id>0){
			return (CompanyBoss) totalDao.get(CompanyBoss.class, id);
		}
		return null;
	}

	@Override
	public CompanyVIP findCompanyVIPById(Integer id) {
		if(id!=null && id>0){
			return (CompanyVIP) totalDao.get(CompanyVIP.class, id);
		}
		return null;
	}

	//获得总数量；
	@Override
	public int getcont() {
		String hql="from CompanyVIP";
		
		 return totalDao.getCount(hql);
	}

	@Override
	public CompanyVIP showComanyVIPbyvipNO(String vipNo) {
		if(vipNo!=null &&!"".equals(vipNo) ){
			String hql = "from CompanyVIP where vipNo=?";
			return(CompanyVIP) totalDao.getObjectByCondition(hql, vipNo);
		}
		return null;
	}
}
