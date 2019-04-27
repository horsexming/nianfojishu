package com.task.Server;

import java.io.File;
import java.util.Map;

import com.task.entity.Credentials;


public interface CredentialsServer {
	
	/**
	 * 分页查询所有内容,按工号查询
	 * 
	 * @author 于勇鸿斌
	 * @return
	 */
	public Map<Integer, Object> findcredentials(Credentials credentials,
			int pageNo, int pageSize,String tage);
	/**
	 * 通过id查看证件对象
	 * 
	 * @param id
	 *            证件对象id
	 * @return
	 */
	public Credentials findcredentialsById(Integer id);
	/**
	 * 通过id删除证件对象
	 * 
	 * @param id
	 *            证件对象id
	 * @return
	 */
	public boolean delete(Integer id);
	/**
	 * 添加证件对象
	 * 
	 * @param credentials
	 *            证件对象
	 * @param fujian
	 *            附件对象
	 * @return
	 */
	String addCredentials(Credentials credentials, String ps);
	/**
	 * 修改证件对象
	 * 
	 * @param credentials
	 *            证件对象
	 * @return
	 */
	public String updateCredentials(Credentials credentials);
	/**
	 * 申请转公车
	 * @param credentials
	 * @return
	 */
	public String shenqing(Credentials credentials);

}
