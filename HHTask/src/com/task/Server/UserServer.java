package com.task.Server;

import java.io.File;
import java.util.List;

import com.task.entity.DeptNumber;
import com.task.entity.Password;
import com.task.entity.Users;
import com.task.entity.UsersCard;
import com.task.entity.UsersLoginLog;
import com.task.entity.renshi.InterviewLog;
import com.task.entity.renshi.Rank;

@SuppressWarnings("unchecked")
public interface UserServer {
	public boolean login(String usercode, String password);

	public Users logins(String usercode, String password);

	public String login(Users users, Password password, String autoLogin);// 登录(检查部门工号、编码、密码)

	public Users findUserByCode(String code);// 根据工号查询员工信息

	public Users findUserByCardId(String cardId);// 根据卡号查询员工信息

	public Password findPasswordByUid(Users user); // 根据uId查询密码表信息

	public boolean updatePassword(Password password, String realPassword); // 修改密码

	public String updateUsersW(Users user,File picture,String pictureFileName); // 修改个人信息

    /*
        * @author fy
      * @date 2018/8/21 11:19
      * @Description:// 通过功能id 分页查找所有已绑定用户
      * @param [object, functionId]
      * @return java.util.List
      * @throws
      */
    Object[] findAllBangUserByPage(String object, int functionId, int pageNo, int pageSize);

    public Users findUserById(int id);// 通过id查找用户

	public Object[] findAllUser(String object, int functionId, int pageNo,
			int pageSize);// 通过功能id查找所有用户

	public Object[] findUsersByCondition(Users user, int pageNo, int pageSize,
			String pageStyle, Integer id);// 用户条件查询

    // 已绑定用户条件查询
    Object[] findHadBingdingUsersByCondition(Users user, int pageNo, int pageSize,
                                             String pageStyle, Integer id);

    public boolean updateUser(Users user);// 修改用户

	public String addUser(Users user, File picture, String pictureFileName,
			InterviewLog interviewLog); // 添加新员工 15-7-8李聪改。

	public boolean uploadResume(Users user, File file, String fileFileName,
			String pictureOrResume); // 上传照片或者简历

	public List findAllProvision(String provisionStatus);// 查询所有的条款

	public boolean delUser(Users user); // 删除用户

	public List findTryDaysEnd();// 试用期到期以及过期了仍未处理提醒

	public List findContractEnd(); // 合同到期以及过期了仍未处理提醒

	public List findAllBangUser(String object, int functionId); // 通过功能id查找所有已绑定用户

	/***
	 * 通过部门查询人员
	 * 
	 * @param deptName
	 *            部门名称
	 * @return Users集合
	 */
	public List findUsersByDept(String deptName);

	/***
	 * 根据部门名称查询部门信息
	 * 
	 * @param dept
	 *            部门名称
	 * @return DeptNumber
	 */
	public DeptNumber findDeptNumberByDept(String dept);

	/***
	 * 登录(检查卡号、密码)
	 * 
	 * @param user
	 * @param password
	 * @return
	 */
	public String login(Users user, Password password);

	public Users findUserByCode1(String trim);

	/**
	 * 获取所有的拥有指派权限的用户
	 * 
	 * @return
	 */
	public List getDispatchUsers();

	/**
	 * 通过用户id修改该用户的指派权限
	 * 
	 * @param userid
	 * @param backStage
	 * @param deptIds
	 * @return
	 */
	public boolean upadteDispatch(Integer userid, Integer backStage,
			String deptIds);

	/***
	 * 查看个人工作记录
	 * 
	 * @param id
	 * @return
	 */
	public Object[] findWorkrecords(int id, int parseInt, int pageSize);

	/***
	 * 查看所有个人工作记录
	 * 
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	public Object[] findAllWorkrecords(String name, String code,
			String gongwei, String markId, String firstApplyDate,
			String submitDate, int parseInt, int pageSize);

	/***
	 * 查询所有件号
	 * 
	 * @return
	 */
	public List getProcardAllNames(String markId);

	/***
	 * 查询所有工位
	 * 
	 * @param gongwei
	 * @return
	 */
	public List getGongweiAllNames(String gongwei);

	public List getProcardAllNames1();

	public List getProcessInforName();

	public Object[] findAllWorkrecords1(String name, String code,
			String markid, int parseInt, int pageSize);

	/***
	 * 查询员工明细
	 * 
	 * @param name
	 * @param code
	 * @param gongwei
	 * @param markId
	 * @param firstApplyDate
	 * @param submitDate
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	public Object[] findAllWorkrecords1(String name, String code,
			String gongwei, String markId, String processNO,
			String firstApplyDate, String submitDate, int parseInt, int pageSize);

	/***
	 * 单个员工工作记录
	 * 
	 * @param id
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	public Object[] findAllWorkrecords1(int id, String markId,
			String processName, int parseInt, int pageSize);

	/***
	 * 查询所有工序
	 * 
	 * @param processNO
	 * @return
	 */
	public List getprocessNONames(Integer id, String processName);

	public Object[] findAllWorkrecords3(String name, String code, int parseInt,
			int pageSize);

	/***
	 * 根据员工号查询已干的工序
	 * 
	 * @param code
	 * @return
	 */
	public List getproNameByCode(String code);

	/***
	 * 导出
	 * 
	 * @param user
	 */
	public void exportExcel(Users user, String external);

	String findImageByCode(String code);

	/**
	 * 获取最大工号
	 * 
	 * @return
	 */
	public Integer getMaxCode();

	/**
	 * 根据卡号查询对象，返回list<objects> obj 实体类名称 objCode 对象名codenumber的字段名称 codeNmuber
	 * 值
	 */
	/*
	 * public void getcodeNumber(String obj, String objCode, String codeNmuber,
	 * String newNmuber);
	 */
	public void updatecodeNumber(String codeNmuber, String newNmuber);

	/***
	 * 根据用户名称查询用户信息
	 * 
	 * @param name
	 * @return
	 */
	Users findUserByName(String name);

	// 查询所有班次信息
	public List findBanCi();

	public boolean grupdateZL(Users user);

	// 更新职业的状态
	public boolean updateCareetrack(Users user);

	// 删除简历
	public String updateResume(String fileFileName, Users user);

	/****
	 * 检验登录(工号和密码登录)
	 * 
	 * @param code
	 *            工号
	 * @param password
	 *            密码
	 * @param autoLogin
	 *            自动登录
	 * @return
	 */
	String login(String code, Password password, String autoLogin);

	/**
	 * 生产卡绑定员工
	 */
	String UsersCardbangUser(UsersCard usercard, Integer[] userId,
			String pageStatus);

	/**
	 * 查询所有人员;
	 */
	Object[] findAllUsers(Integer id, Users user, int parseInt, int pageSize,
			String pageStatus);

	/**
	 * 查询所有产线小组卡
	 */
	Object[] findAllUsersCard(UsersCard userscard, int parseInt, int pageSize,
			String pageStatus);

	/**
	 * 根据卡号查询产线小组卡
	 */
	UsersCard findUsersCardByCardId(String cardId);

	boolean BangCard(Integer id, String cardId);

	/**
	 * 删除产线小组卡
	 */
	String delUsersCard(Integer id);

	/***
	 * 送货码登录
	 * 
	 * @param phone
	 * @return
	 */
	String loginByPhone(String phone,Integer id);
	/**
	 * 修改产线小组卡
	 */
	boolean updateUsersCard(UsersCard userscard);
	
	void daoruLizhi(File lizhiUsers);
	
	/**
	 * 按天记录人员的登录日志，并且更新登录次数;
	 * @param user
	 */
	void addUsersLoginLog(Users user);
	/**
	 * ajxa每五分钟访问一次更新在线时长
	 */
	boolean	updateOnlineLong(Integer id);

	public List<Users> getgetAllName(String name);

	/**
	 * 查询是否使用PEBS考勤机
	 * @return
	 */
	public boolean findadmin();
	public boolean updateHSKaoqin(Users user);
	
	
	/**
	 * 添加职级
	 */
	boolean addRank(Rank rank);
	
	/**
	 * 查询职级
	 */
	List<Rank> findRankByCond(Rank rank,String pageStatus);
	
	/**
	 * 删除职级
	 */
	boolean deleteRank(Integer id);
}
