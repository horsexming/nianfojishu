package com.task.Server.gzbj;

import java.util.List;


import com.task.entity.android.OsScope;
import com.task.entity.gzbj.Measuring;
import com.task.entity.gzbj.ProcessAndMeasuring;
import com.task.entity.gzbj.ProcessGzstore;

public interface ProcessGzstoreServer {

	Object[] findGxAll(ProcessGzstore processGzstore, int pageNo, int pageSize);
	/**
	 * 
	 * 查询所有工序ForSelect
	 * @see com.task.Server.gzbj.ProcessGzstoreServer#findGxAll(com.task.entity.gzbj.ProcessGzstore, int, int)
	 */
	public List getProcessGzstoreListAllForSelect(ProcessGzstore processGzstore);
	
	/**
	 * 
	 * 查询工序根据ID
	 * 
	 */
	public ProcessGzstore getProcessGzstoreById(Integer id);
	
	public boolean bdOsScope(Integer id,List<OsScope> osList);
	
	public List<OsScope> getOsListbyId(Integer id);
	
	public boolean delOsScope(Integer processId,Integer osId);
	
	public boolean update(ProcessGzstore pg);
	
	public String shuaixn(String processName);
	/**
	 * 根据工序名查询工序
	 */
	public ProcessGzstore getproceGzstorebyname(String processName);
	/**
	 * 申请特殊工序
	 * @param id
	 * @return
	 */
	String applySpecial(Integer id);
	/**
	 * 根据某个工序查询所有绑定的量、检具
	 */
	List<ProcessAndMeasuring>	findAllPamList(Integer id);
	
	/**
	 * 查询所有未绑定的量、检具
	 */
	List<Measuring> findALLmMeasList(Integer id,Measuring measuring);
	
	/**
	 * 工序绑定量、检具
	 */
	String savePAM(Integer id,Integer[] ids);
	
	String 	delPAM(Integer id);
	
}
