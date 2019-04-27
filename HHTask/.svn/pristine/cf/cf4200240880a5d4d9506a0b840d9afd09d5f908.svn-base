package com.task.Server.kq;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.task.entity.Register;

/**
 * @author 曾建森
 * @FileNam IRegisterService.java
 * @Date 2012-10-9
 */
public interface IRegisterService {
	public void add(Register rs);

	public void delById(int id);

	public void del(Register rs);

	public void update(Register rs);

	/***
	 * 查询所有登记信息(分页)
	 * 
	 * @param pageNo
	 *            第几页
	 * @param pageSize
	 *            每页数量
	 * @return Object[]
	 */
	public Object[] queryAllRegister(Map map,int pageNo, int pageSize);

	public Register getRegisterById(int id);

	public String getCardNoByNum(String num);

	public Object[] readCardNoByExcel(String resouceFile,
			float copies, String upTime, String downTime);

	public String fileUpload(File file, String fileName);

	public Object[] queryCollectByCondition(Map map,int pageNo, int pageSize);
	
	public List queryCollectByCondition(Map map);
}
