package com.task.ServerImpl.sys;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.FileCopyUtils;

import com.task.Dao.TotalDao;
import com.task.Server.sys.SystemDemandServer;
import com.task.entity.Users;
import com.task.entity.sop.ManualOrderPlanDetail;
import com.task.entity.sop.YcWaiGouProcrd;
import com.task.entity.system.CircuitRun;
import com.task.entity.system.SystemDemand;
import com.task.util.Upload;
import com.task.util.Util;

/**
 * 系统需求
 * @author 王传运
 *
 */
public class SystemDemandServerImpl implements SystemDemandServer{

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	//添加需求
	@Override
	public String addSystemDemand(SystemDemand systemDemand,File file ,String fileName) {
		Users users = Util.getLoginUser();
		if(users==null){
			return "请先登录";
		}
		
		String attachmentName = null;
		if (file != null) {
			attachmentName =Util.getDateTime("yyyyMMddHHmmss") + (fileName.substring(fileName.lastIndexOf(".")));
			// 上传到服务器
			String fileRealPath = ServletActionContext .getServletContext().getRealPath("/upload/file")
					+ "\\" + attachmentName;
			File saveFile = new File(fileRealPath);
			try {
				FileCopyUtils.copy(file, saveFile);
			} catch (Exception e) {
				return "文件出错!";
			}

			// 备份到项目
			String beiFenfileRealPath = ServletActionContext.getServletContext().getRealPath("/upload/file/"+attachmentName);
			File beiFenFile = new File(beiFenfileRealPath);
			try {
				FileCopyUtils.copy(file, beiFenFile);
				systemDemand.setDemandFile(attachmentName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		String dateTime = Util.getDateTime("yyyyMMdd");
	//	String maxnumber = (String) totalDao.getObjectByCondition("select max(poolNumber) from ProjectPool where poolNumber like '"+ before + "%'");
		String maxSdNum  = (String) totalDao.getObjectByCondition("select max(sdNum) from SystemDemand where sdNum like '"+dateTime+"%'");
		DecimalFormat format = new DecimalFormat("000");
		if(maxSdNum!=null && !"".equals(maxSdNum)){
			String oldNum = maxSdNum.split(dateTime)[1];
			Integer number = Integer.parseInt(oldNum)+1;
			
			systemDemand.setSdNum(dateTime+format.format(number));
		}else{
			systemDemand.setSdNum(dateTime+"001");
		}
		systemDemand.setDemandFile(attachmentName);//文件
		systemDemand.setUserId(users.getId());
		systemDemand.setUserName(users.getName());
		systemDemand.setUserDept(users.getDept());
		systemDemand.setAddTime(Util.getDateTime());
		systemDemand.setStatus("审核中");
		boolean flag = totalDao.save(systemDemand);
		
		if(flag){
			String processName = "系统需求申请";
			if("系统问题".equals(systemDemand.getSdType())){
				processName="系统问题申请";
			}else if("系统新需求".equals(systemDemand.getSdType())){
				processName="系统新需求申请";
			}
			Integer epId = null;
			try {
				epId = CircuitRunServerImpl.createProcess(processName,
						SystemDemand.class,systemDemand.getId(),
						"epStatus", "id", "SystemDemand_getSystemDemandById.action?id="+systemDemand.getId(), users.getDept() + "部门的 "
								+ users.getName() + "的系统需求申请，请您审批", true);
				if (epId != null && epId > 0) {
					systemDemand.setEpId(epId);
					CircuitRun circuitRun = (CircuitRun) totalDao.get(
							CircuitRun.class, epId);
					if ("同意".equals(circuitRun.getAllStatus()) && "审批完成".equals(circuitRun.getAuditStatus())) {
						systemDemand.setEpStatus("同意");
					} else {
						systemDemand.setEpStatus("未审批");
					}
					totalDao.update(systemDemand);
					return "添加成功";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "添加失败";
		}else{
			return "添加失败";
		}
	}

	@Override
	public String delSystemDemand(Integer id) {
		SystemDemand systemDemand = (SystemDemand) totalDao.getObjectById(SystemDemand.class, id);
		
		boolean flag = totalDao.delete(systemDemand);
		if(flag){
			return "删除成功";
		}else{
			return "删除失败";
		}
	}

	@Override
	public SystemDemand getSystemDemandById(Integer id) {
		SystemDemand systemDemand = (SystemDemand) totalDao.getObjectById(SystemDemand.class, id);
		return systemDemand;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] showSystemDemands(SystemDemand systemDemand,
			String pageStatus, Integer pageSize, Integer pageNo) {
		if(systemDemand==null){
			systemDemand = new SystemDemand();
		}
		String hql = totalDao.criteriaQueries(systemDemand,null, null);
		hql+=" order by id desc";
		Integer count = totalDao.getCount(hql);
		List list = totalDao.findAllByPage(hql, pageNo, pageSize, null);
		return new Object[]{list,count};
	}

	@Override
	public String updateSystemDemand(SystemDemand systemDemand) {
		SystemDemand demand = getSystemDemandById(systemDemand.getId());
		demand.setSdDesc(systemDemand.getSdDesc());//问题描述
		demand.setFunctionName(systemDemand.getFunctionName());
		demand.setSdShortName(systemDemand.getSdShortName());
		demand.setSdType(systemDemand.getSdType());
		
		boolean flag = totalDao.update(demand);
		if(flag){
			return "修改成功";
		}else{
			return "修改失败";
		}
	}

	//根据简称获得需求信息
	@Override
	public SystemDemand getsdBysdShortName(String sdShortName) {
		SystemDemand systemDemand = (SystemDemand) totalDao.getObjectByCondition("from SystemDemand where sdShortName = ?",sdShortName);
		return systemDemand;
	}
	
	
}
