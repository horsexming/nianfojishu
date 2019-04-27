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
import com.task.Server.PromotionServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Careertrack;
import com.task.entity.Promotion;
import com.task.entity.Transfer;
import com.task.entity.Users;
import com.task.entity.system.CircuitRun;
import com.task.util.Util;

public class PromotionServerImpl implements PromotionServer{

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	
	@Override
	public List<Promotion> FindAllPromotion(int pageNo, int pageSize) {
		String hql = "from Promotion order by id desc";
		return totalDao.findAllByPage(hql, pageNo, pageSize);
	}

	@Override
	public String add(Promotion pn,File[] attachment,
			String[] attachmentFileName, String fatherPartNumber) {
		if(pn!=null){
			String beforeRank = pn.getBeforeRank();
			String rank = pn.getRank();
			if(beforeRank!=null && !"".equals(beforeRank)
					&& rank!=null && !"".equals(rank)
					&& rank.equals(beforeRank)){
				return "职级和原来一样无需申请！";
			}
			// 上传附件
			String attachmentName = "";
			if (attachment != null && attachment.length > 0) {
				for (int i = 0; i < attachment.length; i++) {
					String fileName =	 new SimpleDateFormat("yyyyMMddHHmmss")
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
						e.printStackTrace();
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
			
			pn.setFujian(attachmentName);
			Users users = Util.getLoginUser();
			pn.setSqName(users.getName());
			pn.setSqDept(users.getDept());
			pn.setSqTime(Util.getDateTime());
			totalDao.save(pn);
			 String processName = "人员晋升申请";
				Integer epId = null;
				try {
					epId = CircuitRunServerImpl.createProcess(processName,
							Promotion.class, pn.getId(), "ep_status", "id",
							"PromotionAction_findpnById.action?id=" + pn.getId()+"&status=mingxi"
									,pn.getDept() + "部门 "
									+ pn.getSqName() + "人员晋升申请，请您审批", true);
					if (epId != null && epId > 0) {
						pn.setEpId(epId);
						CircuitRun circuitRun = (CircuitRun) totalDao.get(
								CircuitRun.class, epId);
						if ("同意".equals(circuitRun.getAllStatus())
								&& "审批完成".equals(circuitRun.getAuditStatus())) {
							pn.setEp_status("同意");
//							Users users1 =  (Users) totalDao.get(Users.class, pn.getUserId());
//							users.setPost(pn.getRank());
//							//更新users表职级;
//							totalDao.update(users1);
//							//设置Promotion表 晋升时间
//							String time = Util.getDateTime("yyyy-MM-dd HH:mm:ss");
//							pn.setJsTime(time);
//							//设置Careertrack表晋升时间;
//							Careertrack ck =	(Careertrack) totalDao.getObjectByCondition("from Careertrack where userId =?",pn.getUserId() );
//							ck.setJinshengTime(time);
//							totalDao.update(ck);
							
						} else {
							pn.setEp_status("未审批");
						}
					return	totalDao.update(pn)+"";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return null;
	}

	@Override
	public boolean del(Promotion pn) {
		if(pn!=null){
			return totalDao.delete(pn);
		}
		return false;
	}

	@Override
	public Map<Integer, Object> findPromotionByCondition(Promotion pn,
			int pageNo, int pageSize) {
		Map<Integer,Object> map=new HashMap<Integer, Object>();
		if(pn==null) {
			pn = new Promotion();
		}
		String hql=	totalDao.criteriaQueries(pn, null);
		int count=totalDao.getCount(hql);
		List<Promotion> ckList=(List<Promotion>)totalDao.findAllByPage(hql, pageNo, pageSize);
		map.put(1, ckList);
		map.put(2, count);
		return map;
	}

	@Override
	public Promotion findPromotionbyId(Integer id) {
		if(id!=null && id>0){
			return (Promotion) totalDao.get(Promotion.class, id);
		}
		return null;
	}

	@Override
	public List<Promotion> findPromotionbyuserId(Integer userId) {
		String hql = "from Promotion where userId=? order by jsTime desc";
		return totalDao.query(hql, userId);
	}

	@Override
	public int getcont() {
		String hql = "from Promotion";
		
		return totalDao.getCount(hql);
	}

	@Override
	public boolean upadte(Promotion pn,File[] attachment,
			String[] attachmentFileName, String fatherPartNumber) {
			if(pn!=null && pn.getId()!=null && pn.getId()>0){
				Promotion oldpn = (Promotion) totalDao.get(Promotion.class, pn.getId());
				if ("打回".equals(oldpn.getEp_status())) {
					if (CircuitRunServerImpl.updateCircuitRun(oldpn.getEpId())) {
						oldpn.setEp_status("未审批");
					}
				}
				
				String attachmentName = "";
				if (attachment != null && attachment.length > 0) {
					for (int i = 0; i < attachment.length; i++) {
						String fileName =	 new SimpleDateFormat("yyyyMMddHHmmss")
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
							e.printStackTrace();
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
				}else{
					attachmentName = oldpn.getFujian();
				}
				
				BeanUtils.copyProperties(pn, oldpn,new String[]{"id","epId"});
				return totalDao.update(oldpn);
			}
			return false;
		
	}

}
