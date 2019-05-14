package com.hhu.count.dao;

import java.util.List;
import java.util.Map;
import com.hhu.count.entity.User;
import com.hhu.count.entity.course;
import com.hhu.count.vo.PoorUserVo;
import com.hhu.count.vo.UserReadVo;
/*
 * 普通用户常用接口
 * */
public interface UserDao {
	//添加用户
	public void InsertUser(User user);
	//通过用户名查询用户
	public User selectByUsername(String username);
	
	//通过用户名和密码查找一个用户
	public User selectByUAndPass(Map<String, String>map);
	//更新用户
	public void updateUser(User user);
	//查询阅读记录
	public List<UserReadVo>selectUserRead(String id);
	//查询没有添加的阅读记录
	public List<course>selectNotUserRead(String id);
	//通过手机号查询用户
	public User SelUserByPhone(String phone);
	//无条件查询所有用户
	public List<PoorUserVo> SelAllUser();
	//查询权限用户名
	public List<course>SelAllCou();
	//修改阅读记录中的用户名
	public void updateRNa(Map<String, String>map);
}
