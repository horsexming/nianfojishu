package com.hhu.count.dao;

import java.util.List;
import java.util.Map;

import com.hhu.count.entity.Admin;
import com.hhu.count.vo.AdminCourseVO;

public interface AdminDao {
	
	public Admin getAdminByid(String id);
	//通过用户名查找一个用户
	public Admin selectByUsername(String username);
	//用户注册
	public void InsertAdmin(Admin admin);
	//通过用户名和密码查找一个用户
	public Admin selectByUAndPass(Map<String, String>map);
	//更新用户
	public void updateAdmin(Admin admin);	
	//管理员统计
	public List<AdminCourseVO>selectAdminCour(String id);
	

}
