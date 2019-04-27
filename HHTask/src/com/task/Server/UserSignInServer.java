package com.task.Server;

import java.util.Map;

import com.task.entity.UserSignIn;

/**
 * @author Li_Cong
 * @Date 2016-01-02
 */

public interface UserSignInServer{
	/**
	 * 添加
	 * @param userSignIn
	 * @return
	 */
	public String add(UserSignIn userSignIn);
	/**
	 * 分页查询打卡记录
	 * @return
	 */
	public Map<Integer, Object> findUserSignIn(UserSignIn userSignIn, int pageNo, int pageSize);
	/**
	 * 分页查询打卡汇总记录
	 * @return
	 */
	//public Map<Integer, Object> findAttendanceTowCollect(AttendanceTowCollect attendanceTowCollect, int pageNo, int pageSize);
	
}
