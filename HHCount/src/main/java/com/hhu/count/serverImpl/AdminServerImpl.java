package com.hhu.count.serverImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhu.count.dao.AdminDao;
import com.hhu.count.entity.Admin;
import com.hhu.count.entity.User;
import com.hhu.count.server.AdminServer;
import com.hhu.count.vo.AdminCourseVO;

@Service("adminServer")
public class AdminServerImpl implements AdminServer {
	
	@Autowired
	AdminDao admindao;

	public Admin getAdminByid(String id) {
		return admindao.getAdminByid(id);
	}
	
	//通过用户名查找一个用户
	public String selectByUsername(String username) {
		System.out.println(admindao.selectByUsername(username));
		if(admindao.selectByUsername(username)==null) {
			return "1";
		}
		return "0";
	}
	//用户注册
	public void InsertAdmin(Admin admin) {
		admin.setId(UUID.randomUUID().toString());
		admin.setRole("Admin");
		admindao.InsertAdmin(admin);
	}
	//通过用户名和密码查询
	public String selectByUAndPass(User user) {
		Map<String,String>map = new HashMap<>();
		map.put("username", user.getUsername());
		map.put("password", user.getPassword());
		if(admindao.selectByUAndPass(map)==null) {
			return "0";
		}
		return user.getUsername();
	}
	
	//通过用户名查找一个用户(返回一个对象)
	public Admin selectAdminByname(String username) {			
		return admindao.selectByUsername(username);
	}
	//更新用户
	public void updateAdmin(Admin admin) {
		
		admindao.updateAdmin(admin);
	}
	//管理员统计
	public List<AdminCourseVO>selectAdminCour(String writerid){
		
		return admindao.selectAdminCour(writerid);
	}
	

}
