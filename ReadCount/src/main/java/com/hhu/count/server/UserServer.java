package com.hhu.count.server;

import java.util.List;
import java.util.Map;

import com.hhu.count.entity.User;
import com.hhu.count.entity.course;
import com.hhu.count.vo.PoorUserVo;
import com.hhu.count.vo.UserReadVo;

public interface UserServer {
	
	//添加用户
	public void InsertUser(User user);
	//通过用户名查找一个用户
	public String selectByUsername(String username);
	/*通过用户名和密码查找用户*/
	public String selectByUAndPass(User user);
	//通过用户名查找一个用户,返回一个对象
	public User selectUserByname(String username);
	//更新用户
	public void updateUser(User user);
	//查询阅读记录
	public List<UserReadVo>selectUserRead(String id);
	//查询没有添加的阅读记录
	public List<course>selectNotUserRead(String id);
	//查询手机号是否存在
	public String SelUserByPhone(String phone);
	public String SelUserByPhone2(String phone);
	//无条件查询所有用户
	public List<PoorUserVo> SelAllUser();
	public Boolean SelByPhAndNa(User user);
	//查询权限用户名
	public List<course>SelAllCou();
	//修改阅读记录中的用户名
	public void updateRNa(String readername, String readerID);

}
