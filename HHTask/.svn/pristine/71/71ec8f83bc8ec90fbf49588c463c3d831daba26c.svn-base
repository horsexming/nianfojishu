package com.task.entity.sop;

import java.util.HashSet;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.ServerImpl.sop.ProcardTemplateGyServerImpl;
import com.task.entity.Users;

/**
 * 设变之后资料更新日志（ta_ProcardTemplateChangeLog）
 * @author txb
 *
 */
public class ProcardTemplatesbChangeLog  implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private Integer ptbbApplyId;//设变单对应明细Id
	private String sbNumber;//设变单号;
	private Integer sbPtId;//设变零件Id
	private String sbMarkId;//设变零件件号
	private Integer sbbanci;//设变零件版次
	private Integer entityId;//实体类Id
	private String entityData;//子件件号,工序号 
	private String entityData2;//工序名称
	private String entityProcardStyle;//子件零件类型
	private String xiaohao;//子件消耗 上层:下层
	private String entityBanben;//子件版本
	private String oldFileName;//原图纸名称 （实体类类别为图纸时为真实删除不能记录实体类Id记录图纸名称）
	private String realFileName;//图纸存储名称
	private String month;//图纸存储月份文件夹
	private String entityType;//实体类类别（本身,子件,工序,图纸）
	private String optype;//操作类别（增加,删除,修改）
	private String addTime;//
	private Integer addUserId;//
	private String addUsername;//
	private String addUsercode;//
	private String dataStatus;//正常,删除
	private Set<ProcardTemplatesbChangeLogDetail> changeLogDetailSet;//字表
	
	//页面传值
	private ProcardTemplate procardTemplate;
	private ProcessTemplate processTemplate;
	private ProcessTemplateFile processTemplateFile;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public Integer getPtbbApplyId() {
		return ptbbApplyId;
	}
	public void setPtbbApplyId(Integer ptbbApplyId) {
		this.ptbbApplyId = ptbbApplyId;
	}
	public Integer getSbPtId() {
		return sbPtId;
	}
	public void setSbPtId(Integer sbPtId) {
		this.sbPtId = sbPtId;
	}
	public Integer getEntityId() {
		return entityId;
	}
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public String getOptype() {
		return optype;
	}
	public void setOptype(String optype) {
		this.optype = optype;
	}
	
	public Set<ProcardTemplatesbChangeLogDetail> getChangeLogDetailSet() {
		return changeLogDetailSet;
	}
	public void setChangeLogDetailSet(
			Set<ProcardTemplatesbChangeLogDetail> changeLogDetailSet) {
		this.changeLogDetailSet = changeLogDetailSet;
	}
	public ProcardTemplate getProcardTemplate() {
		return procardTemplate;
	}
	public void setProcardTemplate(ProcardTemplate procardTemplate) {
		this.procardTemplate = procardTemplate;
	}
	public ProcessTemplate getProcessTemplate() {
		return processTemplate;
	}
	public void setProcessTemplate(ProcessTemplate processTemplate) {
		this.processTemplate = processTemplate;
	}
	public ProcessTemplateFile getProcessTemplateFile() {
		return processTemplateFile;
	}
	public void setProcessTemplateFile(ProcessTemplateFile processTemplateFile) {
		this.processTemplateFile = processTemplateFile;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getAddUsername() {
		return addUsername;
	}
	public void setAddUsername(String addUsername) {
		this.addUsername = addUsername;
	}
	public String getAddUsercode() {
		return addUsercode;
	}
	public void setAddUsercode(String addUsercode) {
		this.addUsercode = addUsercode;
	}
	public String getDataStatus() {
		return dataStatus;
	}
	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}
	
	public Integer getAddUserId() {
		return addUserId;
	}
	public void setAddUserId(Integer addUserId) {
		this.addUserId = addUserId;
	}
	public String getSbMarkId() {
		return sbMarkId;
	}
	public void setSbMarkId(String sbMarkId) {
		this.sbMarkId = sbMarkId;
	}
	public Integer getSbbanci() {
		return sbbanci;
	}
	public void setSbbanci(Integer sbbanci) {
		this.sbbanci = sbbanci;
	}
	public String getRealFileName() {
		return realFileName;
	}
	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
	public String getOldFileName() {
		return oldFileName;
	}
	public void setOldFileName(String oldFileName) {
		this.oldFileName = oldFileName;
	}
	/**
	 * 
	 * @param totalDao
	 * @param pt 对此零件发生改变
	 * @param entityId 实际操作的实体Id
	 * @param entityData 子件件号,工序号:工序名称
	 * @param entityType 操作对象的类型
	 * @param opType 操作类型
	 * @param user 操作人
	 * @param nowtime 操作时间
	 * @return
	 */
	public static String addchangeLog(TotalDao totalDao,ProcardTemplatesb pt,Object entity,String entityType,String opType,Users user,String nowtime){
		//添加修改日志
		ProcardTemplatesbChangeLog changeLog = new ProcardTemplatesbChangeLog();
		Integer banci = pt.getBanci();
		if(banci ==null){
			banci=0;
		}
		changeLog.setPtbbApplyId(pt.getSbApplyId());
		changeLog.setSbNumber(pt.getSbNumber());
		changeLog.setSbPtId(pt.getId());//设变零件Id
		changeLog.setSbMarkId(pt.getMarkId());//设变零件件号
		changeLog.setSbbanci(pt.getBanci());//设变零件版次
		if(entityType.equals("工序")){
			ProcessTemplatesb process = (ProcessTemplatesb) entity;
			changeLog.setEntityId(process.getId());//实体类Id
			changeLog.setEntityData(process.getProcessNO()+"");
			changeLog.setEntityData2(process.getProcessName());
		}else if(entityType.equals("子件")){
			ProcardTemplatesb son = (ProcardTemplatesb) entity;
			changeLog.setEntityId(son.getId());//实体类Id
			if(opType.equals("件号替换")){
				changeLog.setEntityData2(son.getHwdrMarkId());
			}
			changeLog.setEntityData(son.getMarkId());
			if(son.getProcardStyle().equals("外购")){
				changeLog.setXiaohao(son.getQuanzi1()+":"+son.getQuanzi2());
			}else{
				changeLog.setXiaohao("1:"+son.getCorrCount());
			}
			changeLog.setEntityProcardStyle(son.getProcardStyle());
			changeLog.setEntityBanben(son.getBanBenNumber());
			
		}else if( entityType.equals("本身")){
			changeLog.setEntityId(pt.getId());//实体类Id
		}else{
			ProcessTemplateFilesb file = (ProcessTemplateFilesb) entity;
			changeLog.setEntityId(file.getId());//实体类Id
			changeLog.setOldFileName(file.getOldfileName());
			if(file.getType().equals("3D模型")){
				changeLog.setMonth("3Dmodel/"+pt.getMarkId()+"-"+banci);
			}else{
				changeLog.setMonth(file.getMonth());
			}
			changeLog.setRealFileName(file.getFileName());
			changeLog.setEntityData(pt.getMarkId());
			changeLog.setEntityData2(file.getProcessNO()+"");
		}
		changeLog.setEntityType(entityType);//实体类类别（本身,子件,工序,图纸）
		changeLog.setOptype(opType);//操作类别（增加,删除,修改）
		changeLog.setAddTime(nowtime);//
		changeLog.setAddUserId(user.getId());//
		changeLog.setAddUsername(user.getName());//
		changeLog.setAddUsercode(user.getCode());//
		changeLog.setDataStatus("正常");//正常,删除
		totalDao.save2(changeLog);
		return "true";
	}
	/**
	 * 
	 * @param totalDao
	 * @param oldProcardTem
	 * @param procardTemplate2
	 * @param user
	 * @param nowtime
	 */
	public static String addchangeLog(TotalDao totalDao,
			ProcardTemplatesb oldProcardTem, ProcardTemplatesb procardTemplate2,
			Users user, String nowtime) {
		// TODO Auto-generated method stub
		//添加修改日志
		String banciSql = null;
		Integer banci = oldProcardTem.getBanci();
		if(banci ==null){
			banci=0;
			banciSql = " and (sbbanci is null or sbbanci=0 )";
		}else{
			banciSql = " and sbbanci="+banci;
		}
		ProcardTemplatesbChangeLog changeLog = (ProcardTemplatesbChangeLog) totalDao.getObjectByCondition("from ProcardTemplatesbChangeLog where ptbbApplyId=? and sbMarkId=? and entityType='本身' " +
				"and optype='修改'  "+banciSql ,oldProcardTem.getSbApplyId(), oldProcardTem.getMarkId());
		if(changeLog==null){
			changeLog= new ProcardTemplatesbChangeLog();
			changeLog.setPtbbApplyId(oldProcardTem.getSbApplyId());
			changeLog.setSbNumber(oldProcardTem.getSbNumber());
			changeLog.setSbPtId(oldProcardTem.getId());//设变零件Id
			changeLog.setSbMarkId(oldProcardTem.getMarkId());//设变零件件号
			changeLog.setSbbanci(banci);//设变零件版次
			changeLog.setEntityId(oldProcardTem.getId());//实体类Id
			changeLog.setEntityType("本身");//实体类类别（本身,子件,工序,图纸）
			changeLog.setOptype("修改");//操作类别（增加,删除,修改）
			changeLog.setAddTime(nowtime);//
			changeLog.setAddUserId(user.getId());//
			changeLog.setAddUsername(user.getName());//
			changeLog.setAddUsercode(user.getCode());//
			changeLog.setDataStatus("正常");//正常,删除
			Set<ProcardTemplatesbChangeLogDetail> detailSet = ProcardTemplatesb.diffrentTwoPt(oldProcardTem, procardTemplate2, changeLog);
			changeLog.setChangeLogDetailSet(detailSet);
			totalDao.save2(changeLog);
		}else{
			Set<ProcardTemplatesbChangeLogDetail> detailSet = ProcardTemplatesb.diffrentTwoPt(oldProcardTem, procardTemplate2, changeLog);
			Set<ProcardTemplatesbChangeLogDetail> olddetailSet =  changeLog.getChangeLogDetailSet();
			olddetailSet.addAll(detailSet);
			changeLog.setChangeLogDetailSet(olddetailSet);
			totalDao.update(changeLog);
		}
		return "true";
		
	}
	public static String addchangeLog(TotalDao totalDao,
			ProcessTemplatesb oldProcessTem, ProcessTemplatesb processTempalte,
			Users user, String nowtime) {
		// TODO Auto-generated method stub
		ProcardTemplatesb pt = oldProcessTem.getProcardTemplatesb();
		//添加修改日志
		String sbSql = null;
		String banciSql = null;
		String banciSql2 = null;
		Integer banci = pt.getBanci();
		if(banci ==null){
			banci=0;
			banciSql = " and (sbbanci is null or sbbanci=0 )";
			banciSql2 = " and (procardTemplate.banci is null or procardTemplate.banci=0 )";
		}else{
			banciSql = " and sbbanci="+banci;
			banciSql2 = " and procardTemplate.banci="+banci;
		}
		ProcardTemplatesbChangeLog changeLog = (ProcardTemplatesbChangeLog) totalDao.getObjectByCondition("from ProcardTemplatesbChangeLog where sbMarkId=?" +
				" and entityType='工序' and optype='修改' and entityId in (select id from ProcessTemplate where processNO =? and procardTemplate.markId=? " +banciSql2 + ")"
				+banciSql , pt.getMarkId(),oldProcessTem.getProcessNO(),pt.getMarkId());
		if(changeLog==null){
			changeLog= new ProcardTemplatesbChangeLog();
			changeLog.setPtbbApplyId(pt.getSbApplyId());
			changeLog.setSbNumber(pt.getSbNumber());
			changeLog.setSbPtId(pt.getId());//设变零件Id
			changeLog.setSbMarkId(pt.getMarkId());//设变零件件号
			changeLog.setSbbanci(banci);//设变零件版次
			changeLog.setEntityId(pt.getId());//实体类Id
			changeLog.setEntityType("工序");//实体类类别（本身,子件,工序,图纸）
			changeLog.setOptype("修改");//操作类别（增加,删除,修改）
			changeLog.setAddTime(nowtime);//
			changeLog.setAddUserId(user.getId());//
			changeLog.setAddUsername(user.getName());//
			changeLog.setAddUsercode(user.getCode());//
			changeLog.setDataStatus("正常");//正常,删除
			Set<ProcardTemplatesbChangeLogDetail> detailSet = ProcessTemplatesb.diffrentTwoPt(oldProcessTem, processTempalte, changeLog);
			changeLog.setChangeLogDetailSet(detailSet);
			totalDao.save2(changeLog);
		}else{
			Set<ProcardTemplatesbChangeLogDetail> detailSet = ProcessTemplatesb.diffrentTwoPt(oldProcessTem, processTempalte, changeLog);
			Set<ProcardTemplatesbChangeLogDetail> olddetailSet =  changeLog.getChangeLogDetailSet();
			olddetailSet.addAll(detailSet);
			changeLog.setChangeLogDetailSet(olddetailSet);
			totalDao.update(changeLog);
		}
		return "true";
		
	}
	public String getSbNumber() {
		return sbNumber;
	}
	public void setSbNumber(String sbNumber) {
		this.sbNumber = sbNumber;
	}
	public String getEntityData() {
		return entityData;
	}
	public void setEntityData(String entityData) {
		this.entityData = entityData;
	}
	public String getXiaohao() {
		return xiaohao;
	}
	public void setXiaohao(String xiaohao) {
		this.xiaohao = xiaohao;
	}
	public String getEntityBanben() {
		return entityBanben;
	}
	public void setEntityBanben(String entityBanben) {
		this.entityBanben = entityBanben;
	}
	public String getEntityData2() {
		return entityData2;
	}
	public void setEntityData2(String entityData2) {
		this.entityData2 = entityData2;
	}
	public String getEntityProcardStyle() {
		return entityProcardStyle;
	}
	public void setEntityProcardStyle(String entityProcardStyle) {
		this.entityProcardStyle = entityProcardStyle;
	}
	
	
	
	
	
	
}
