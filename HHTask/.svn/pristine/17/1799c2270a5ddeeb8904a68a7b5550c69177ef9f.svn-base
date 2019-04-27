package com.task.Server.checktype;


import java.util.List;
import java.util.Map;

import com.task.entity.Integral;
import com.task.entity.Users;
import com.task.entity.checktype.CheckNote;

public interface CheckNoteServer {
	public Map<Integer, Object> findAll(CheckNote checkNote,int pageNo,int pageSize);
	//系统异常所有
	public Map<Integer, Object> findSysAll(CheckNote checkNote,int pageNo,int pageSize);
	//系统异常个人(负责人)
	public Map<Integer, Object> findbyfuze(CheckNote checkNote,int pageNo,int pageSize);
	//系统异常个人（提交人）
	//开始处理
	public boolean startHandle(Integer id,String status);
	public Map<Integer, Object> findbytijiao(CheckNote checkNote,int pageNo,int pageSize);
	public boolean Userbind(Integer id,int[] arrayId);
	public boolean dock(CheckNote checkNote,Integral i);
	public List<Users> findUser(String  id);
	public boolean update(CheckNote checkNote);
	public CheckNote findById(Integer id);
	public List<Integer> findDept(Integer userId);
	public Integral findIntegral(String id);
	public Map<Integer, Object> findUserByfuze(CheckNote checkNote,int pageNo,int pageSize,String id,String leader);
}
 