package com.task.Server.fin;

import java.util.List;

import com.task.entity.dangan.ArchiveUnarchiverAplt;
import com.task.entity.fin.jmwj.FileLocation;
import com.task.entity.fin.jmwj.FileManager;
import com.task.entity.fin.jmwj.FileType;
import com.task.entity.menjin.AccessEquipment;
import com.task.entity.menjin.AccessWebcam;
import com.task.entity.menjin.JLMApplication;
import com.task.entity.menjin.Operation;

/**
 * 机密文档管理server
 * @author jhh
 *
 */
public interface FileManagerServer {
	/**
	 * 添加机密文档
	 * @param baoxiaodan
	 * @return
	 */
	public boolean saveFileManager(FileManager fileManger);
	/**
	 * 添加查机密文档
	 * 
	 */
	public Object[] findFile(FileManager fileManager, String startDate, String endDate,
			Integer cpage, Integer pageSize,String tag);
	/**
	 * 更新机密文档
	 * 
	 */
	public boolean updateFile(FileManager fileManager);
	/**
	 * 根据ID获取机密文档对象
	 * @param id
	 * @return
	 */
	public FileManager getFileManagerById(Integer id);
	/**
	 * 删除机密文档
	 * @param id
	 * @return
	 */
	public boolean deleteFileManager(Integer id);
	/**
	 * 查找文件类型列表
	 */
	public String findStyle(String tag);
	/**
	 * 添加文件类型
	 * @return
	 */
	public boolean addFileType(FileType fileType);
	
	public boolean addFileLocation(FileLocation fileLocation);
	
	/**
	 * 查询所有文件类型
	 * @return
	 */
	public List findListType();
	/**
	 * 查询文件位置
	 * @param cpage
	 * @param pageSize
	 * @return
	 */
	public Object[] findFileLocation(Integer cpage, Integer pageSize);
	/**
	 * 删除文件类型
	 * @param id
	 * @return
	 */
	public boolean deleteFileTypeByID(Integer id);
	/**
	 * 删除文件存放位置
	 * @param id
	 * @return
	 */
	public boolean deleteFileLocationById(Integer id);
	/**
	 * 根据id获得AccessEquipment对象
	 */
	public AccessWebcam getbyIdAccessWebcam(Integer integer);
	/**
	 * 给取文件生成验证码
	 * 
	 * @param archiveUnarchiverAplt
	 * @return
	 */
	public String addYanZheng(FileManager fileManger);
	
	
}
