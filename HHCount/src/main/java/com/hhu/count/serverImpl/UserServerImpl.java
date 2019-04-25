package com.hhu.count.serverImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhu.count.dao.UserDao;
import com.hhu.count.entity.User;
import com.hhu.count.entity.course;
import com.hhu.count.server.UserServer;
import com.hhu.count.vo.PoorUserVo;
import com.hhu.count.vo.UserReadVo;

@Service("userServer")
public class UserServerImpl implements UserServer {
	
	@Autowired
	UserDao userDao;
	/*用户注册*/
	public void InsertUser(User user) {
		user.setId(UUID.randomUUID().toString());
		user.setRole("User");
		userDao.InsertUser(user);
	}
	
	//通过用户名查找一个用户
	public String selectByUsername(String username) {
		if(userDao.selectByUsername(username)==null) {
			return "1";
		}
		return "0";
	}
	
	//通过用户名和密码查询
	public String selectByUAndPass(User user) {
		Map<String,String>map = new HashMap<>();
		map.put("username", user.getUsername());
		map.put("password", user.getPassword());
		if(userDao.selectByUAndPass(map)==null) {
			return "0";
		}
		return user.getUsername();
	}
	
	//通过用户名查找一个用户,返回一个对象
	public User selectUserByname(String username) {	
		return userDao.selectByUsername(username);
	}
	
	//更新用户
	public void updateUser(User user) {
		userDao.updateUser(user);
	}
	
	//查询阅读记录
	public List<UserReadVo>selectUserRead(String id){
		return userDao.selectUserRead(id);
	}
	//查询没有添加的阅读记录
	public List<course>selectNotUserRead(String id){
		System.err.println("userDao.selectNotUserRead(id)"+userDao.selectNotUserRead(id).size());
		return userDao.selectNotUserRead(id);
	}
	//查询手机号是否存在
	public String SelUserByPhone(String phone) {
		if(userDao.SelUserByPhone(phone)==null) {
			return "0";
		}else {			
			return "1";
		}
				
	}
	
	public String SelUserByPhone2(String phone) {

		return userDao.SelUserByPhone(phone).getUsername();	
	}
	
	public Boolean SelByPhAndNa(User user) {
		if(userDao.selectByUsername(user.getUsername())!=null) {
			return true;
		}
		if(userDao.SelUserByPhone(user.getPhone())!=null) {
			return true;
		}
		return false;
	}
	
	//无条件查询所有用户
	public List<PoorUserVo> SelAllUser(){
		
		return userDao.SelAllUser();
	}
	
	//查询权限用户名
	public List<course>SelAllCou(){
		return userDao.SelAllCou();
	}
	
	//修改阅读记录中的用户名
	public void updateRNa(String readername, String readerID) {
		Map<String,String>map = new HashMap<>();
		map.put("readername",readername);
		map.put("readerID",readerID);
		userDao.updateRNa(map);
	}
	

}
