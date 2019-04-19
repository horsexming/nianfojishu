package com.hhu.count.server;


import java.util.List;

import com.hhu.count.entity.Admin;
import com.hhu.count.entity.User;
import com.hhu.count.vo.AdminCourseVO;

public interface AdminServer {
	public Admin getAdminByid(String id);
	//通过用户名查找一个用户
	public String selectByUsername(String username);
	//用户注册
	public void InsertAdmin(Admin admin);
	/*通过用户名和密码查找用户*/
	public String selectByUAndPass(User user);
	
	//通过用户名查找一个用户(返回一个对象)
	public Admin selectAdminByname(String username);
	//更新用户
	public void updateAdmin(Admin admin);
	//管理员统计
	public List<AdminCourseVO>selectAdminCour(String writerid);

}
