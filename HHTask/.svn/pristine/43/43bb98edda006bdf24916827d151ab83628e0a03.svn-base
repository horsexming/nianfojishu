package com.task.Server;

import java.io.File;
import java.util.List;

import com.task.entity.ModuleFunction;
import com.task.entity.Users;
import com.task.entity.system.CompanyInfo;

/**
 * 模块功能Server层
 * 
 * @author 刘培
 * 
 */
@SuppressWarnings("unchecked")
public interface ModuleFunctionServer {
	public List findAllMF();// 查询所有模块功能3

	/***
	 * 查询当前登录用所拥有的所有功能
	 * 
	 * @return
	 */
	public List findUserMF();

	public ModuleFunction findMfById(Integer id); // 通过Id查询模块功能

	public List findSonMfById(Integer id); // 通过Id查询其所有子类

	public List findSonMfById(Users user, Integer fatherId); // 通过父类ID查询用户已绑定子层功能

	public List findSonMfByIdPhone_one(); // 查询用户手机端的功能的第一层

	public List findSonMfByIdPhone(Integer fatherId); // 查询用户已绑定手机端的功能

	public String updateMf(ModuleFunction moduleFunction, Integer id,
			File qxImage, String qxImageFileName, File xkImage,
			String xkImageFileName, File smallImage, String smallImageFileName,
			File mrImage, String mrImageFileName, File bsImage,
			String bsImageFileName); // 修改模块功能

	public String delMf(ModuleFunction moduleFunction); // 删除模块功能

	public String addMf(ModuleFunction moduleFunction,
			ModuleFunction oldModuleFunction, String pageStatus); // 添加模块功能

	public List findMfByName(String functionName); // 通过功能名称查询功能

	public boolean binDingUsers(ModuleFunction moduleFunction, Integer[] usersId); // 绑定用户

	boolean AddbinDingUsers(ModuleFunction moduleFunction, Integer[] usersId);

	boolean DeletebinDingUsers(ModuleFunction moduleFunction, Integer[] usersId);

	public List findMfByUser(Users user); // 查询用户所对应第一层模块

	public String findMfNameForNavigation(ModuleFunction moduleFunction,
			String pageStauts); // 通过父类ID查询其所有功能名称(页面导航)

	public List searchModuleFunction(ModuleFunction moduleFunction, Users user); // 搜索功能

	void updateRootId(Integer fatherId);

	/****
	 * 获得整个网站的配置信息
	 * 
	 * @return
	 */
	CompanyInfo findCompanyInfo();

	public boolean updateMf1(ModuleFunction moduleFunction, Integer id);

	public List<ModuleFunction> findMfById1(Integer id);

	public boolean updateMfById1(Integer[] detailSelect, Integer[] detailSelect1);

	public void updateMfById2(Integer id, Integer seId);

	public void updateMfById3(Integer id, Integer seId);

	public void saveHome(ModuleFunction moduleFunction);

	/**
	 * 查询用户所拥有的所有的功能
	 * 
	 * @param user
	 * @return
	 */

	public List findAllMfByUser(Users user);

	/***
	 * 通过父类id和登录用户获得对应功能列表
	 * 
	 * @param fatherId
	 * @return
	 */
	List findMfByUserAndFId(Integer fatherId);

	/***
	 * 根据rootId 查询用户对应的目录功能
	 * 
	 * @param rootId
	 * @return
	 */
	List findUserMFByRootId(Integer rootId);

	/**
	 * 根据员工Id查询所绑定的功能
	 */
	Object[] findMfByUserId(Integer userId, ModuleFunction Mf, int pageNo,
			int pageSize);

	/**
	 * 用户绑定功能
	 */
	public boolean binDingModuleFunction(Integer userId, Integer[] detailSelect);

	Boolean copyModuleFunction(Integer userId, Integer otheruserId);

	List<ModuleFunction> findSubModule(Integer id);

	/***
	 * 
	 * @param mfid
	 *            收缩模块ID
	 * @param fatherMfname
	 *            父模块名
	 */
	Boolean chang2SubModule(Integer mfid, String fatherMfname);

	int delModulByUsers(Users user);

	/**
	 * 导出功能模块下的用户权限
	 * 
	 * @param name
	 * @return
	 */
	String exportExcel(String name);

	/***
	 * 查询同层功能
	 * 
	 * @param id
	 * @return
	 */
	List<ModuleFunction> findLayerModule(Integer id);

	/***
	 * 通过Id查询用户对应的功能
	 * 
	 * @param id
	 * @return
	 */
	ModuleFunction findMfByIdAndUser(Integer id);
	
	
}
