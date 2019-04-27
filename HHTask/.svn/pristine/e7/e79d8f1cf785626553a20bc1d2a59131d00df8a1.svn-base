package com.task.Server.systemfile;

import java.util.List;
import java.util.Map;

import com.task.entity.Users;
import com.task.entity.fin.jmwj.FileType;
import com.task.entity.systemfile.FileleixingOrdengji;
import com.task.entity.systemfile.SystemFile;

public interface SystemFileServer {
	public boolean add(SystemFile systemFile);
	
	public SystemFile findSystemFileById(Integer id);
	
	public boolean delete(SystemFile systemFile);
	
	public boolean update(SystemFile systemFile);
	
	public Map<Integer, Object> Query(SystemFile systemFile, int pageNo, int pageSize,String tag);

	public Map<Integer, Object> QueryByLevel(SystemFile systemFile, int pageNo, int pageSize,String level, String pageStatus);
	public SystemFile findSystemFileByNo(SystemFile systemFile);
	/**
	 * 添加保密日志
	 * @param fileName
	 */
	public void addBaomiLog(String fileName);
	/**
	 * 文件添加(待审批)
	 * @param systemFile
	 * @return
	 */
	public boolean addforshenpi(SystemFile systemFile,String uIds);
	public Map<Integer, Object> QueryByshenpi(SystemFile systemFile, int pageNo,
			int pageSize, String tag);
	public List<SystemFile> Querylishibanben(List<SystemFile> oldSystemFileList,SystemFile systemFile);
	public String guidang(SystemFile systemFile);
	public Map<Integer, Object> QueryByshenpiforporson(SystemFile systemFile, int pageNo,
			int pageSize);
	/**
	 * 添加文件类型 文件等级
	 */
	public String addFileNameorType(FileleixingOrdengji fileType);
	/**
	 * 查询全部文件类型 文件等级
	 * @param fileType
	 * @param pageNo
	 * @param pageSize
	 * @param tag
	 * @return
	 */
	public Map<Integer, Object> QueryFileType(FileleixingOrdengji fileType, int pageNo,
			int pageSize,String tag);
	public List<FileleixingOrdengji> QueryFileType(String tag);
	public boolean delete(FileleixingOrdengji fileType);
	public Map<Integer, Object> QueryByupload(SystemFile systemFile, int pageNo,
			int pageSize) ;
	
	public List<SystemFile> findSystemFile(SystemFile systemFile,String tag);
	//根据文件类型id查找文件类型
	FileleixingOrdengji findFileleixingOrdengjiById(Integer id);
	//修改文件类型/等级
	String updateFileNameorType(FileleixingOrdengji fileType);
	
	//生成编号并保存
	SystemFile generatorFileNo(SystemFile systemFile)throws Exception;

	//按条件获取列表
	Map<Integer, Object> findCodeManager(SystemFile systemFile, int pageNo,
			int pageSize, String tag,String pageStatus);
	
	//保存或取消项目编号
	String saveOrCancalCode(String tags,SystemFile systemFile);
	
	//上传文件或文件升版
	SystemFile submitOrUpGrade(String tags,SystemFile systemFile,int deptIds[],
			int userIds[]);
	
	/**
	 * 修改添加(待审批)
	 * @param systemFile
	 * @return
	 */
	public boolean updateforshenpi(SystemFile systemFile,String uids);
	
	//根据旧版本号，获取新的版本号
	public String getNewBanBenByoldBanBen(String oldBanBen);
	
	//清除文件编号信息
	public void removeFileNo();
	
	
	/**
	 * 文件受控查看历史版本非技术部
	 */
	public Object[] getListByOldBanben(Integer id, String tag);
	
	/**
	 * 根据某些信息获取users
	 * @return
	 */
	public List<Users> getUsersByCondition(Integer deptId,String userLike);
	
	/**
	 * 根据文件编号和版本号作废文件
	 * @param systemFile
	 * @param tag
	 * @return
	 */
	public String zuoFeiSystemFile(SystemFile systemFile , String tag,String uIds);
	
	/**
	 * 批量审批
	 * @param ids
	 * @param tag
	 * @return
	 */
	String auditAllShenPi(int[] pzId, String tag);
	
	
	String applyReaudit(Integer id,String remark);
}
