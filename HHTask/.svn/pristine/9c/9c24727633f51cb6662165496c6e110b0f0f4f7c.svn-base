package com.task.ServerImpl.systemfile;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.sys.CircuitRunServer;
import com.task.Server.systemfile.SystemFileServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.AlertMessages;
import com.task.entity.DeptNumber;
import com.task.entity.Machine;
import com.task.entity.Users;
import com.task.entity.project.BaomiOperateLog;
import com.task.entity.project.QuotedPricejyDetailFile;
import com.task.entity.sop.ManualOrderPlanTotal;
import com.task.entity.sop.ProcardWxTuiLiao;
import com.task.entity.system.CircuitRun;
import com.task.entity.systemfile.FileleixingOrdengji;
import com.task.entity.systemfile.SystemFile;
import com.task.util.Util;

public class SystemFileServerImpl implements SystemFileServer{
	private TotalDao totalDao;
	private CircuitRunServer circuitRunServer;
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	
	public CircuitRunServer getCircuitRunServer() {
		return circuitRunServer;
	}

	public void setCircuitRunServer(CircuitRunServer circuitRunServer) {
		this.circuitRunServer = circuitRunServer;
	}

	public String shenhe(){
		return "";
	}
	@Override
	public boolean add(SystemFile systemFile) {
		if (systemFile.getUploadDate() == null
				|| systemFile.getUploadDate().equals("")){
			systemFile.setUploadDate(Util.getDateTime());
		}
		Users loginUser = Util.getLoginUser();
		systemFile.setPerson(loginUser.getName());
		if(systemFile.getBaomi()!=null&&systemFile.getBaomi().equals("保密")){
			BaomiOperateLog log = new BaomiOperateLog();
			log.setOperateType("增加");//操作类型
			log.setOperateObject("文件");//操作对象
			log.setOperateRemark("文件类型:"+systemFile.getFileType()+",文件名称："+systemFile.getFileName());//
			log.setOperateTime(Util.getDateTime());//
			log.setOperateUserId(loginUser.getId());
			log.setOperateUsername(loginUser.getName());//
			log.setOperateCode(loginUser.getCode());//
			log.setOperateDept(loginUser.getDept());//
			totalDao.save2(log);
		}
		return totalDao.save(systemFile);
	
	}
	/**
	 * 文件添加(待审批)
	 * @param systemFile
	 * @return
	 */
	public boolean addforshenpi(SystemFile systemFile,String uIds) {
		if (systemFile.getUploadDate() == null
				|| systemFile.getUploadDate().equals("")){
			systemFile.setUploadDate(Util.getDateTime());
		}
		Users loginUser = Util.getLoginUser();
		systemFile.setPerson(loginUser.getName());
		systemFile.setUploadDate(Util.getDateTime());
		if(totalDao.save(systemFile)){
			String processName = "文件管控审批流程";
			Integer epId = null;
			try {
				epId = CircuitRunServerImpl.createProcessbf(processName,
						SystemFile.class, systemFile.getId(), "status",
						"id",
						"systemFileAction_xiangxi.action?id="+systemFile.getId(), 
						systemFile.getFileType()+"/"+systemFile.getFileLevel()+"文件管控审批流程，请您审批", true, uIds, "3");
				if (epId != null && epId > 0) {
					systemFile.setEpId(epId);
					CircuitRun circuitRun = (CircuitRun) totalDao.get(
							CircuitRun.class, epId);
					if ("同意".equals(circuitRun.getAllStatus())
							&& "审批完成".equals(circuitRun.getAuditStatus())) {
						systemFile.setStatus("同意");
					} else {
							systemFile.setStatus("未审批");
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
				return false;
			}
//			String allId =systemFile.getPersonToLookId();
//			String[] allIda = allId.split(";");
//			for(String a :allIda){
//				AlertMessagesServerImpl.addAlertMessages("你又新的文件可以查看",
//						"/systemFileAction_findAllByUser.action", "1",
//						a.toString());
//			}
			return true;
		}else{
			return false;
		}
	}
	public Map<Integer, Object> QueryByshenpi(SystemFile systemFile, int pageNo,
			int pageSize,String tag) {	
		if (systemFile == null) {
			systemFile= new SystemFile();
		}
		String hql = totalDao.criteriaQueries(systemFile,null);
		hql+=" and epId <>null and source is null and id in (select max(id) from SystemFile group by fileNo)";
		if(tag!=null && "hetong".equals(tag)){
			hql+=" and fileType ='合同类'";
		}
		if(tag!=null && "noshenpi".equals(tag)) {
			hql+=" and epId in (select c.id from ExecutionNode e join e.circuitRun c  " + 
					"where c.allStatus not in ('同意','打回') and e.auditLevel=c.auditLevel  and e.auditStatus='未审批' "
					+ "and e.auditUserId="+Util.getLoginUser().getId()+" ) ";
		}
		hql +=" order by id desc";
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		List<SystemFile> objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		map.put(1, objs);
		map.put(2, count);
		return map;
	}
	
	
	public Map<Integer, Object> QueryByshenpiforporson(SystemFile systemFile, int pageNo,
			int pageSize) {	
		if (systemFile == null) {
			systemFile= new SystemFile();
		}
		String hql = totalDao.criteriaQueries(systemFile,null);
		Users user = Util.getLoginUser();
		hql+=" and epId <>null and personToLook like '%"+user.getName()+";%'";
		hql +=" order by id desc";
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		List<SystemFile> objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		map.put(1, objs);
		map.put(2, count);
		return map;
	}
	public Map<Integer, Object> QueryByupload(SystemFile systemFile, int pageNo,
			int pageSize) {	
		if (systemFile == null) {
			systemFile= new SystemFile();
		}
		String hql = totalDao.criteriaQueries(systemFile,null);
		Users user = Util.getLoginUser();
		hql+=" and epId <>null and person = '"+Util.getLoginUser().getName()+"' and status <> '作废'";
		hql +=" order by id desc";
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		List<SystemFile> objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		map.put(1, objs);
		map.put(2, count);
		return map;
	}
	
	public List<SystemFile> Querylishibanben(List<SystemFile> oldSystemFileList,SystemFile systemFile) {	
		if(systemFile!=null && systemFile.getId()!=null){
			SystemFile systemFile_old = (SystemFile)totalDao.get(SystemFile.class, systemFile.getId());
			oldSystemFileList.add(systemFile_old);
			if(systemFile_old.getBanben_old()!=null){
				String hql = "from SystemFile where banben = ? and fileNo = ?";
				SystemFile sF = (SystemFile)
						totalDao.getObjectByConditionforDouble(hql,systemFile_old.getBanben_old(),systemFile_old.getFileNo());
				Querylishibanben(oldSystemFileList, sF);
			}
//			String banben_old = systemFile_old.getBanben_old();
//			if(banben_old!=null && !"".equals(banben_old)){
//				String[] banbenhao =banben_old.split(";");
//				for(String s : banbenhao){
//					String hql = "from SystemFile where banben = ? and fileNo = ?";
//					SystemFile sF = (SystemFile)
//							totalDao.getObjectByConditionforDouble(hql,s,systemFile_old.getFileNo());
//					sysList.add(sF);
//					List<SystemFile> list = Querylishibanben(sF);
//					if(list!=null && list.size()>0){
//						sysList.addAll(list);
//					}
//					//sysList.add(sF);
//				}
//				
//			}else{
////				List<SystemFile> list = totalDao.query("from SystemFile where fileNo=? and banben <> ?",
////							systemFile_old.getFileNo(),systemFile_old.getBanben());
////				return list;
//				
//			}
		}
		return oldSystemFileList;
	}
	@Override
	public boolean delete(SystemFile systemFile) {
		if("保密".equals(systemFile.getBaomi())){
			Users loginUser = Util.getLoginUser();
			BaomiOperateLog log = new BaomiOperateLog();
			log.setOperateType("删除");//操作类型
			log.setOperateObject("文件");//操作对象
			log.setOperateRemark("文件类型:"+systemFile.getFileType()+",文件名称："+systemFile.getFileName()+"的文件");//
			log.setOperateTime(Util.getDateTime());//
			log.setOperateUserId(loginUser.getId());
			log.setOperateUsername(loginUser.getName());//
			log.setOperateCode(loginUser.getCode());//
			log.setOperateDept(loginUser.getDept());//
			totalDao.save2(log);
		}
		return totalDao.delete(systemFile);
	}
	public boolean delete(FileleixingOrdengji fileType) {
		FileleixingOrdengji ft = (FileleixingOrdengji)totalDao.get(FileleixingOrdengji.class, fileType.getId());
		return totalDao.delete(ft);
	}
	@Override
	public SystemFile findSystemFileById(Integer id) {
		return (SystemFile)totalDao.get(SystemFile.class, id);
	}
	
	@Override
	public boolean update(SystemFile systemFile) {
		return totalDao.update(systemFile);
	}
	public String guidang(SystemFile systemFile) {
		if(systemFile.getId()!=null){
			SystemFile systemFile_old = (SystemFile)totalDao.get(SystemFile.class, systemFile.getId());
			systemFile_old.setStatus("归档");
			totalDao.update(systemFile_old);
			return "修改成功";
		}
		return "失败";
	}
	@Override
	public Map<Integer, Object> Query(SystemFile systemFile, int pageNo,
			int pageSize,String tag) {	
		if (systemFile == null) {
			systemFile= new SystemFile();
		}
		String hql = null;
		hql = totalDao.criteriaQueries(systemFile,null);
		if("gjb".equals(tag)){
			hql+=" and fileType in( '国军标','国标(gb)','军标','项目文件') ";
		}else {
			hql+=" and fileType not  in( '国军标','国标(gb)','军标','项目文件') ";
		}
		hql +=" order by id desc";
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		List<SystemFile> objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		
		//查找所有列表中的部门
		List<String> deptNameList = totalDao.query("select distinct(department) "+hql);
		map.put(1, objs);
		map.put(2, count);
		map.put(3, deptNameList);
		return map;
	}

	@Override
	public SystemFile findSystemFileByNo(SystemFile systemFile) {
		if(systemFile.getId()==null){
			String oldBanben = "";
			if(systemFile.getBanben_old()!=null && !"".equals(systemFile.getBanben_old())){
				oldBanben = " and banben = '"+systemFile.getBanben_old()+"'";
			}
			return (SystemFile)totalDao.getObjectByQuery("from SystemFile where fileNo=? "+oldBanben+" order by id desc",systemFile.getFileNo());
		}else{
			return (SystemFile)totalDao.getObjectByQuery("from SystemFile where fileNo=? and id <> ?",systemFile.getFileNo(),systemFile.getId());
		}
	}

	@Override
	public Map<Integer, Object> QueryByLevel(SystemFile systemFile, int pageNo,
			int pageSize, String level,String pageStatus ) {
		if (systemFile == null) {
			systemFile= new SystemFile();
		} 
		String hql = null;
		if("1".equals(level)){
			hql ="fileLevel = '一级文件'  "; 
		}else if("2".equals(level)){
			hql ="fileLevel = '二级文件'  "; 
		}else if("3".equals(level)){
			hql ="fileLevel = '三级文件'  ";
		}else if("4".equals(level)){
			hql ="fileLevel = '四级表单' ";
		}else if("skill".equals(level)){
			hql ="fileLevel = '技术规范'  "; 
		}else if("law".equals(level)){
			hql ="fileLevel = '法律法规'  ";
		}else if("sop".equals(level)){
			hql ="fileLevel = '作业SOP'  ";
		}else if("sip".equals(level)){
			hql ="fileLevel = '检验SIP'  ";
		}else if("out".equals(level)){
			hql ="fileLevel = '外来文件'  ";
		}else if("other".equals(level)){
			hql ="fileLevel = '其他文件'  ";
		}else if("peixun".equals(level)){
			hql ="fileLevel = '培训资料' ";
		}else if("gb".equals(level)){
			hql ="fileLevel = '国标' and  fileType = '国标(gb)' ";
		}else if("jb".equals(level)){
			hql ="fileLevel = '军标'  and  fileType = '军标' ";
		}else if("gjb".equals(level)){
			hql ="fileLevel = '国军标'  and  fileType = '国军标' ";
		}
		hql = totalDao.criteriaQueries(systemFile,hql,null);
		if("gjb".equals(pageStatus)){
			hql+=" and fileType in( '国军标','国标(gb)','军标','项目文件') and status is null  order by id desc";
		}
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		List<SystemFile> objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public void addBaomiLog(String fileName) {
		// TODO Auto-generated method stub
		SystemFile systemFile =(SystemFile) totalDao.getObjectByCondition("from SystemFile where fileUrl=? and baomi='保密'", fileName);
		if(systemFile!=null){
			Users loginUser = Util.getLoginUser();
			BaomiOperateLog log = new BaomiOperateLog();
			log.setOperateType("查看");//操作类型
			log.setOperateObject("文件");//操作对象
			log.setOperateRemark("文件类型:"+systemFile.getFileType()+",文件名称："+systemFile.getFileName()+"的原文件资料");//
			log.setOperateTime(Util.getDateTime());//
			log.setOperateUserId(loginUser.getId());
			log.setOperateUsername(loginUser.getName());//
			log.setOperateCode(loginUser.getCode());//
			log.setOperateDept(loginUser.getDept());//
			totalDao.save2(log);
		}else{
			QuotedPricejyDetailFile jydFile=(QuotedPricejyDetailFile) totalDao.getObjectByCondition("from SystemFile where fileUrl=? and baomi='保密'", fileName);
			if(jydFile!=null){
				Users loginUser = Util.getLoginUser();
				BaomiOperateLog log = new BaomiOperateLog();
				log.setOperateType("查看");//操作类型
				log.setOperateObject("进度纪要文件");//操作对象
				log.setOperateRemark("文件类型:项目进度纪要文件,文件名称："+jydFile.getOldFileName()+"的原文件资料");//
				log.setOperateTime(Util.getDateTime());//
				log.setOperateUserId(loginUser.getId());
				log.setOperateUsername(loginUser.getName());//
				log.setOperateCode(loginUser.getCode());//
				log.setOperateDept(loginUser.getDept());//
				totalDao.save2(log);
			}
		}
	}
	/**
	 * 添加文件类型 文件等级
	 */
	public String addFileNameorType(FileleixingOrdengji fileType){
		if(fileType!=null){
			if(totalDao.save(fileType)){
				return "添加成功";
			}else{
				return "添加失败";
			}
		}else{
			return "重新添加";
		}
	}
	/**
	 * 查询全部文件类型 文件等级
	 * @param fileType
	 * @param pageNo
	 * @param pageSize
	 * @param tag
	 * @return
	 */
	public Map<Integer, Object> QueryFileType(FileleixingOrdengji fileType, int pageNo,
			int pageSize,String tag) {	
		if (fileType == null) {
			fileType= new FileleixingOrdengji();
		}
		String hql=null;
		if("dengji".equals(tag)){
			hql ="typeforit = '文件等级'  "; 
		}else if("leixing".equals(tag)){
			hql ="typeforit = '文件类型'  "; 
		}else if("laiyuan".equals(tag)){
			hql ="typeforit = '文件来源'  "; 
		}else if("category".equals(tag)){
			hql ="typeforit = '文件类别'  "; 
		}else{
			hql="";
		}
		hql = totalDao.criteriaQueries(fileType,hql,null);
		hql +=" order by id desc";
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		List<SystemFile> objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		map.put(1, objs);
		map.put(2, count);
		return map;
	}
	public List<FileleixingOrdengji> QueryFileType(String tag) {	
		String hql = "from FileleixingOrdengji where typeforit =?";
		List<FileleixingOrdengji> list = totalDao.query(hql,tag);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SystemFile> findSystemFile(SystemFile systemFile, String tag) {
		if(systemFile==null){
			systemFile=new SystemFile();
		}
		String hql = totalDao.criteriaQueries(systemFile, null, null);
		String type =systemFile.getFileType(); 
		if(type !=null && "合同类".equals(type)){
			hql+=" and (status ='同意' or status = '归档')";
		}
		List<SystemFile> list = totalDao.query(hql);
		return list;
	}
	
	
	
	/**
	 * 通过部门id递归获取该部门下所有的下级部门id
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getUnderDeptIdById(Integer deptId) {
		List<Integer> deptIds = new ArrayList<Integer>();
		List<Integer> list = totalDao.query("select id from DeptNumber where fatherId="
				+ deptId);
		if (list.size() != 0) {
			List<Integer> ids = list;
			deptIds.addAll(ids);
			for (Integer id : ids) {
				deptId = id;
				getUnderDeptIdById(deptId);
			}
		}
		return deptIds;
	}
	
	/**
	 * 判断员工是否属于某个部门
	 * @return
	 */
	public boolean estimateIsDept(Integer userId ,String deptName){
		DeptNumber deptNumber = (DeptNumber) totalDao.getObjectByCondition("from DeptNumber from dept=? ",deptName);
		Users loginUser = Util.getLoginUser();
		Integer deptId = loginUser.getDeptId();
		List<Integer> deptIdList = getUnderDeptIdById(deptNumber.getId());
		boolean  flag = false;//判断用户是否在这个部门中
		for (Integer integer : deptIdList) {
			if(integer!=null && deptId !=null){
				if((int)deptId == (int)integer){
					flag = true;
					break;
				}
			}
		}
		
		return flag;
	}

	@Override
	public FileleixingOrdengji findFileleixingOrdengjiById(Integer id) {
		FileleixingOrdengji fileleixingOrdengji = (FileleixingOrdengji) totalDao.getObjectById(FileleixingOrdengji.class, id);
		return fileleixingOrdengji;
	}

	@Override
	public String updateFileNameorType(FileleixingOrdengji fileType) {
		FileleixingOrdengji fileleixingOrdengji = findFileleixingOrdengjiById(fileType.getId());
		
		fileleixingOrdengji.setTypeName(fileType.getTypeName());
		if(fileType.getCode()!=null){
			fileleixingOrdengji.setCode(fileType.getCode());
		}
		totalDao.update(fileleixingOrdengji);
		
		return "修改成功";
	}

	@Override
	public SystemFile generatorFileNo(SystemFile systemFile)throws Exception {
		
		Integer count = totalDao.getCount("from SystemFile where fileName=? and category is not null",systemFile.getFileName());
		if(count>0){
			throw new Exception("该文件名称已经存在");
		}
		
		
		String source = systemFile.getSource();//文件来源
		String category = systemFile.getCategory();//文件类别
		
		if(source!=null && !"".equals(source) && category!=null && !"".equals(category)){
			if(source.equals("项目")){
				String proCode =systemFile.getProCode();//项目编号
				//取出项目编号 
				if(proCode!=null && proCode!=""){
					String split[] =proCode.split("-");
					String proSuffix = split[2];
					if(proSuffix.length()==2){
						proSuffix = "0"+proSuffix;
					}
					source = split[1]+proSuffix;
				}
			}else{
				source = Util.getDateTime("yyyy")+"000";
			}
			String perfix = source+"-"+category+"-";
			String oldmaxFileNo = (String) totalDao.getObjectByCondition(
					"select max(fileNo) from SystemFile where fileNo like '"+perfix+"%'");
			if(oldmaxFileNo!=null){
					
				//生成最大编号
				String[] split = oldmaxFileNo.split(perfix);
				String number = split[1];
				DecimalFormat df = new DecimalFormat("0000");
				String format = df.format(Integer.parseInt(number)+1);
				systemFile.setFileNo(perfix+format);
			}else{
				systemFile.setFileNo(perfix+"0001");
			}
			systemFile.setBanben("A1");
			return systemFile;
		}
		return null;
	}
	
	
	@Override
	public Map<Integer,Object> findCodeManager(SystemFile systemFile, int pageNo,
			int pageSize,String tag,String pageStatus){
		Users loginUser = Util.getLoginUser();
		if (systemFile == null) {
			systemFile= new SystemFile();
		}
		String hql = totalDao.criteriaQueries(systemFile,null);
		if(tag!=null && "jsb".equals(tag)){
			hql+=" and id in (" +
			"select max(id) from SystemFile group by fileNo) " +
			"and source is not null and fileUrl is not null " ;
		}
		if(pageStatus==null || !"all".equals(pageStatus)){
			hql+="and (personToLook like '%"+loginUser.getName()+"%'  or fileLevel ='公开' or person ='"+loginUser.getName()+"') ";
			//"and personToLookId like '%"+loginUser.getId()+"%' " +
		}
		hql +=" order by id desc";
		if("banben".equals(tag)){
			hql="from SystemFile where fileNo='"+systemFile.getFileNo()+"' order by banben desc";
		}
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		List<SystemFile> objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		map.put(1, objs);
		map.put(2, count);
		//获取编号未提交的
		List<SystemFile> list = totalDao.query("from SystemFile where epId is null and fileUrl is null and person='"+loginUser.getName()+"'");
		
		map.put(3, list);
		return map;
	}

	@Override
	public String saveOrCancalCode(String tags,SystemFile systemFile) {
		
		if("save".equals(tags) || "saveLaterGetId".equals(tags)){
			Users loginUser = Util.getLoginUser();
			
			Integer count = totalDao.getCount("from SystemFile where fileNo = ? ", systemFile.getFileNo());
			if(count>0){
				return "此编号已经被使用，请重新获取。";
			}
			systemFile.setPerson(loginUser.getName());
			if (systemFile.getUploadDate() == null
					|| systemFile.getUploadDate().equals("")){
				systemFile.setUploadDate(Util.getDateTime());
			}
//			if(systemFile.getBaomi()!=null&&systemFile.getBaomi().equals("保密")){
//				BaomiOperateLog log = new BaomiOperateLog();
//				log.setOperateType("增加");//操作类型
//				log.setOperateObject("文件");//操作对象
//				log.setOperateRemark("文件类型:"+systemFile.getFileType()+",文件名称："+systemFile.getFileName());//
//				log.setOperateTime(Util.getDateTime());//
//				log.setOperateUserId(loginUser.getId());
//				log.setOperateUsername(loginUser.getName());//
//				log.setOperateCode(loginUser.getCode());//
//				log.setOperateDept(loginUser.getDept());//
//				totalDao.save2(log);
//			}
			boolean save = totalDao.save(systemFile);
			if(save){
				if("saveLaterGetId".equals(tags)){
					return systemFile.getId()+"";
				}
				return "保存成功";
			}
		
		}else if("cancal".equals(tags)){
			SystemFile systemFile2 = (SystemFile) totalDao.getObjectByCondition("from SystemFile where fileNo = ? ", systemFile.getFileNo());
			boolean delete = totalDao.delete(systemFile2);
			if(delete){
				return "取消成功";
			}
		}
		return null;
	}

	@Override
	public SystemFile submitOrUpGrade(String tags, SystemFile systemFile,int pzIds[],
			int userIds[]) {
		
		SystemFile systemFile2 = (SystemFile) totalDao.getObjectById(SystemFile.class, systemFile.getId());
		
		//设置审批人顺序及等级
		StringBuffer buffer  = new StringBuffer();
		int leave = 1;
		//审核人
		if(userIds!=null){
			for(int i=0;i<userIds.length;i++){
				if(i==0){
					buffer.append(leave+":"+userIds[i]);
				}else{
					buffer.append(","+leave+":"+userIds[i]);
				}
			}
		}
		
		if(userIds!=null){
			if(pzIds!=null){
				for(int i=0;i<pzIds.length;i++){
					leave++;
					buffer.append(","+leave+":"+pzIds[i]);
				}
			}
			
		}else{
			if(pzIds!=null){
				leave = 0;
				for(int i=0;i<pzIds.length;i++){
					leave++;
					if(i==0){
						buffer.append(leave+":"+pzIds[i]);
					}else{
						buffer.append(","+leave+":"+pzIds[i]);
					}
				}
			}
		}
		
		String uIds=buffer.toString();
		if("submit".equals(tags)){
			//删除审批动态消息
			if(systemFile2.getEpId()!=null){
				CircuitRunServerImpl.deleteCircuitRun(systemFile2.getEpId());
			}
			if (systemFile2.getUploadDate() == null
					|| systemFile2.getUploadDate().equals("")) {
				systemFile2.setUploadDate(Util.getDateTime());
			}
			if(systemFile.getFileUrl()!=null){
				systemFile2.setFileUrl(systemFile.getFileUrl());
				systemFile2.setOtherName(systemFile.getOtherName());
			}
			String personToLookId = systemFile.getPersonToLookId();
			if(personToLookId!=null && personToLookId!=""){
				try {
					String[] idSz = personToLookId.split(";");
					for (String userId : idSz) {
						Users users = (Users) totalDao.getObjectById(Users.class, Integer.parseInt(userId));
						AlertMessagesServerImpl.addAlertMessages("文件受控查看·技术部", systemFile2.getPerson()+
								"上传文件受控，您被选择为推送人员，如有需要请查看文件。", "您有新的推送消息提醒",users.getCode());
					}
				} catch (Exception e) {
				}
			}
			systemFile2.setUpdateDate(Util.getDateTime());
			systemFile2.setFileName(systemFile.getFileName());
			systemFile2.setDepartment(systemFile.getDepartment());
			systemFile2.setDescription(systemFile.getDescription());
			systemFile2.setPersonToLook(systemFile.getPersonToLook());
			systemFile2.setPersonToLookId(systemFile.getPersonToLookId());
			systemFile2.setOtherName(systemFile.getOtherName());
			if(totalDao.update(systemFile2)){
				String processName = "文件管控审批流程";
				Integer epId = null;
				try {
					epId = CircuitRunServerImpl.createProcessbf(processName,
							SystemFile.class, systemFile2.getId(), "status",
							"id",
							"systemFileAction_xiangxi.action?id="+systemFile2.getId(), 
							"技术部文件管控审批流程，请您审批", true, uIds,"3");
					if (epId != null && epId > 0) {
						systemFile2.setEpId(epId);
						CircuitRun circuitRun = (CircuitRun) totalDao.get(
								CircuitRun.class, epId);
						if ("同意".equals(circuitRun.getAllStatus())
								&& "审批完成".equals(circuitRun.getAuditStatus())) {
							systemFile2.setStatus("同意");
						} else {
								systemFile2.setStatus("未审批");
						}
						totalDao.update(systemFile2);
						return systemFile2;
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}else if("upgrade".equals(tags)){//升版
			Users loginUser = Util.getLoginUser();
			if(systemFile!=null && systemFile.getUploadDate()==null){
				systemFile.setUploadDate(Util.getDateTime());
			}
			systemFile.setPerson(loginUser.getName());
			systemFile.setApplyDate(systemFile2.getApplyDate());
			systemFile.setCategory(systemFile2.getCategory());
			String personToLookId = systemFile.getPersonToLookId();
			if(personToLookId!=null && personToLookId!=""){
				try {
					String[] idSz = personToLookId.split(";");
					for (String userId : idSz) {
						Users users = (Users) totalDao.getObjectById(Users.class, Integer.parseInt(userId));
						AlertMessagesServerImpl.addAlertMessages("文件受控查看·技术部", systemFile.getPerson()+
								"上传文件受控，您被选择为推送人员，如有需要请查看文件。", "您有新的推送消息提醒",users.getCode());
					}
				} catch (Exception e) {
				}
			}else{
				try {
					if(systemFile.getPersonToLook()!=null && systemFile.getPersonToLook()!=null){
						String[] personToLook = systemFile.getPersonToLook().split(";");
						for (String userName : personToLook) {
							Users users = (Users) totalDao.getObjectByCondition("from Users where name=?", userName);
							AlertMessagesServerImpl.addAlertMessages("文件受控查看·技术部", systemFile.getPerson()+
									"上传文件受控，您被选择为推送人员，如有需要请查看文件。", "您有新的推送消息提醒",users.getCode());
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			systemFile.setId(null);
			if(totalDao.save(systemFile)){
				String processName = "文件管控审批流程";
				Integer epId = null;
				try {
					epId = CircuitRunServerImpl.createProcessbf(processName,
							SystemFile.class, systemFile.getId(), "status",
							"id",
							"systemFileAction_xiangxi.action?id="+systemFile.getId(), 
							"技术部文件升版流程，请您审批", true, uIds,"3");
					if (epId != null && epId > 0) {
						systemFile.setEpId(epId);
						CircuitRun circuitRun = (CircuitRun) totalDao.get(
								CircuitRun.class, epId);
						if ("同意".equals(circuitRun.getAllStatus())
								&& "审批完成".equals(circuitRun.getAuditStatus())) {
							systemFile.setStatus("同意");
						} else {
								systemFile.setStatus("未审批");
						}
						totalDao.update(systemFile);
						return systemFile;
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else if("cancellation".equals(tags)){//文件作废
			Integer epId = null;
			
			String personToLookId = systemFile2.getPersonToLookId();
			if(personToLookId!=null && personToLookId!=""){
				try {
					String[] idSz = personToLookId.split(";");
					for (String userId : idSz) {
						Users users = (Users) totalDao.getObjectById(Users.class, Integer.parseInt(userId));
						AlertMessagesServerImpl.addAlertMessages("文件受控查看·技术部", systemFile2.getPerson()+
								"上传文件受控，您被选择为推送人员，如有需要请查看文件。", "您有新的推送消息提醒",users.getCode());
					}
				} catch (Exception e) {
				}
			}else{
				try {
					if(systemFile2.getPersonToLook()!=null && systemFile2.getPersonToLook()!=null){
						String[] personToLook = systemFile2.getPersonToLook().split(";");
						for (String userName : personToLook) {
							Users users = (Users) totalDao.getObjectByCondition("from Users where name=?", userName);
							AlertMessagesServerImpl.addAlertMessages("文件受控查看·技术部", systemFile2.getPerson()+
									"文件受控作废，您被选择为推送人员，如有需要请查看文件。", "您有新的推送消息提醒",users.getCode());
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			
			try {
				epId = CircuitRunServerImpl.createProcessbf("文件编号作废审批流程", SystemFile.class,
						systemFile2.getId(), "canStatus", "id","systemFileAction_xiangxi.action?id="+systemFile.getId(), 
						"技术部文件编号"+systemFile2.getFileNo()
						+"作废申请，请您审批",true, uIds,"3");
				if(epId!=null&& epId>0){
					systemFile2.setCanEpId(epId);
					CircuitRun circuitRun = (CircuitRun) totalDao.get(
							CircuitRun.class, epId);
					if ("同意".equals(circuitRun.getAllStatus())
							&& "审批完成".equals(circuitRun.getAuditStatus())) {
						systemFile2.setStatus("归档");
						systemFile2.setCanStatus("同意");
						//systemFile2.setStatus("作废");
					} else {
							systemFile2.setCanStatus("预申请");
							//systemFile2.setStatus("作废中");
					}
					systemFile2.setStatus("作废审批中");
					if(!totalDao.update(systemFile2)){
						return null;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return systemFile;

		}
		return null;
	}

	@Override
	public boolean updateforshenpi(SystemFile systemFile,String uIds) {
		if (systemFile.getUploadDate() == null
				|| systemFile.getUploadDate().equals("")){
			systemFile.setUploadDate(Util.getDateTime());
		}
		Users loginUser = Util.getLoginUser();
		systemFile.setPerson(loginUser.getName());
		Integer cancalEpId = (Integer) totalDao.getObjectByCondition(
				"select epId from SystemFile where id=?", systemFile.getId());
		if(cancalEpId!=null){
			AlertMessages alertMessages = (AlertMessages) totalDao.getObjectByCondition(
				"from AlertMessages where functionUrl=?", "CircuitRunAction_findAduitPage.action?id="+cancalEpId);
			if(alertMessages!=null){
				totalDao.delete(alertMessages);
			}
			CircuitRun cancalCir = (CircuitRun) totalDao.getObjectById(CircuitRun.class, cancalEpId);
			if(cancalCir!=null) {
				totalDao.delete(cancalCir);
			}
		}
		if(totalDao.update(systemFile)){
			String processName = "文件管控审批流程";
			Integer epId = null;
			try {
				epId = CircuitRunServerImpl.createProcessbf(processName,
						SystemFile.class, systemFile.getId(), "status",
						"id",
						"systemFileAction_xiangxi.action?id="+systemFile.getId(), 
						systemFile.getFileType()+"/"+systemFile.getFileLevel()+"文件管控审批流程，请您审批", true, uIds,"3");
				if (epId != null && epId > 0) {
					systemFile.setEpId(epId);
					CircuitRun circuitRun = (CircuitRun) totalDao.get(
							CircuitRun.class, epId);
					if ("同意".equals(circuitRun.getAllStatus())
							&& "审批完成".equals(circuitRun.getAuditStatus())) {
						systemFile.setStatus("同意");
					} else {
							systemFile.setStatus("未审批");
					}
				}
				return true;
			}catch (Exception e) {
				e.printStackTrace();
			}
//			String allId =systemFile.getPersonToLookId();
//			String[] allIda = allId.split(";");
//			for(String a :allIda){
//				AlertMessagesServerImpl.addAlertMessages("你又新的文件可以查看",
//						"/systemFileAction_findAllByUser.action", "1",
//						a.toString());
//			}
			return false;
		}else{
			return false;
		}
	}

	//根据旧版本号，获取新的版本号
	@Override
	public String getNewBanBenByoldBanBen(String oldBanBen) {
		String newBanben = null;
		String numberStr = oldBanBen.substring(1);
		int number = Integer.parseInt(numberStr);
		String eng = oldBanBen.substring(0,1);
		if(number<5){
			newBanben = eng+(number+1);
		}else{
			byte bytes = oldBanBen.substring(0,1).getBytes()[0];
			int acsii = Integer.parseInt(bytes+"");
			if(acsii==73 || acsii==79 || acsii == 90){
				acsii+=2;
			}else{
				acsii+=1;
			}
			newBanben  = (char)acsii+"1";
		}
		return newBanben;
	}

	//清除以获取编号未上传文件的信息0:00
	@Override
	public void removeFileNo() {
		List<SystemFile> list = totalDao.query("from SystemFile where fileNo is not null and fileUrl is null");
		for (SystemFile systemFile : list) {
			totalDao.delete(systemFile);
		}
		//设置昨天没有领取过工序的设备的稼动率设置为0.
		try {
			List<Machine> macList =	totalDao.query(" from Machine where id not in ( selcet machine_id  from MachineDayYZSJ where machineday=? )", Util.DateToString(Util.getCalendarDate(new Date(), -1), "yyyy-mm-dd"));
			for (Machine machine : macList) {
				machine.setJiadonglv(0f);
				totalDao.update(machine);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Object[] getListByOldBanben(Integer id, String tag) {
		
		List<SystemFile> sysList = new ArrayList<SystemFile>();
		
		if(id!=null ){
			SystemFile systemFile = (SystemFile) totalDao.getObjectById(SystemFile.class, id);
			String banben_old = systemFile.getBanben_old();
			if(banben_old!=null && !"".equals(banben_old)){
				String[] banbenhao =banben_old.split(";");
				for(String s : banbenhao){
					String hql = "from SystemFile where banben = ? and fileNo = ?";
					SystemFile sF = (SystemFile)
							totalDao.getObjectByConditionforDouble(hql,s,systemFile.getFileNo());
					sysList.add(sF);
				}
			}
		}
		Object[] objects  = new Object[]{sysList};
		return objects;
	}

	@Override
	public List<Users> getUsersByCondition(Integer deptId, String userLike) {
		
		return null;
	}

	//根据文件编号和版本号作废文件
	@Override
	public String zuoFeiSystemFile(SystemFile systemFile, String tag,String uIds) {
		if(systemFile!=null ){
			String fileNo = systemFile.getFileNo();
			String banben = systemFile.getBanben_old();
			String banbenAppend = "";
			if(banben!=null && !"".equals(banben)){
				banbenAppend = " and banben = '"+banben+"'";
			}
			SystemFile systemFile2 = (SystemFile) totalDao.getObjectByCondition("from SystemFile where fileNo = ? "+banbenAppend, fileNo);
			//systemFile2.setStatus("作废");
			
			
			try {
				Integer epId = CircuitRunServerImpl.createProcessbf("文件编号作废审批流程", SystemFile.class,
						systemFile2.getId(), "canStatus", "id","systemFileAction_xiangxi.action?id="+systemFile2.getId(), 
						"文件编号"+systemFile2.getFileNo()
						+"作废申请，请您审批",true, uIds,"3");
				if(epId!=null&& epId>0){
					systemFile2.setCanEpId(epId);
					CircuitRun circuitRun = (CircuitRun) totalDao.get(
							CircuitRun.class, epId);
					if ("同意".equals(circuitRun.getAllStatus())
							&& "审批完成".equals(circuitRun.getAuditStatus())) {
						systemFile2.setStatus("归档");
						systemFile2.setCanStatus("同意");
						//systemFile2.setStatus("作废");
					} else {
							systemFile2.setCanStatus("预申请");
							//systemFile2.setStatus("作废中");
							systemFile2.setStatus("作废审批中");
					}
					systemFile2.setCanRemark(systemFile.getDepartment());//作废描述
					systemFile2.setCanPersonCode(Util.getLoginUser().getCode());//作废人
					totalDao.update(systemFile2);
				}
			} catch (Exception e) {
				return "作废失败，审批流程有误";
			}
			return "作废提交成功";
		}
		
		return "参数获取失败";
	}

	//批量审批
	@Override
	public String auditAllShenPi(int[] ids, String tag) {
		String mess = "";
		if (ids != null && ids.length > 0) {
			SystemFile systemFile=null;
			for (Integer id : ids) {
				systemFile = (SystemFile) totalDao.getObjectById(SystemFile.class, id);
				if (systemFile != null) {
					if ("ok".equals(tag)) {// 同意
						mess = circuitRunServer.updateExeNodeByCirId(systemFile
								.getEpId(), true, "", true, null, true);
					} else if ("no".equals(tag)) {// 打回
						mess = circuitRunServer.updateExeNodeByCirId(systemFile
								.getEpId(), false, "", true, null, true);
					} else {
						return "数据异常!";
					}
					CircuitRun circuitRun = (CircuitRun) totalDao
							.getObjectById(CircuitRun.class, systemFile.getEpId());
					if ("同意".equals(circuitRun.getAllStatus())) {
						systemFile.setStatus("同意");
					} else {
						systemFile.setStatus("打回");
					}
					totalDao.update(systemFile);
				}
			}
		} else {
			return "数据异常!";
		}
		return mess;
	}

	@Override
	public String applyReaudit(Integer id, String remark) {
		
		SystemFile systemFile = (SystemFile) totalDao.getObjectById(SystemFile.class, id);
		if(systemFile!=null) {
			String processName ="文件受控反审流程";
			try {
				Integer epId =CircuitRunServerImpl.createProcess(processName, SystemFile.class, id,
						"status", "id", "systemFileAction_xiangxi.action?id="+id,""+processName+ remark, true);
				epId = CircuitRunServerImpl.createProcess(processName,
						SystemFile.class, id,"status", "id","systemFileAction_xiangxi.action?id="+id,
						"文件受控反审流程：文件名称：	"+systemFile.getFileName()+",请您审批。备注："+remark, true);
						
				if(epId!=null&& epId>0){
					systemFile.setEpId(epId);
					systemFile.setStatus("反审中");
					CircuitRun circuitRun = (CircuitRun) totalDao.get(
							CircuitRun.class, epId);
					if ("同意".equals(circuitRun.getAllStatus())
							&& "审批完成".equals(circuitRun.getAuditStatus())) {
						systemFile.setStatus("反审成功");
					} else {
						systemFile.setStatus("反审中");
					}
					boolean update = totalDao.update(systemFile);
					if(update) {
						return "申请成功";
					}else {
						return "申请失败";
					}
				}else {
					return "系统异常";
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.toString());
			}
			
		}else {
			return "参数错误请联系管理员。";
		}
	}
}
