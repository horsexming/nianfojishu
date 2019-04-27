package com.task.Server;

import java.util.List;
import java.util.Map;
import com.task.entity.sop.ProcessTemplateFile;
import com.task.entity.android.InsTemplate;
import com.task.entity.android.OsTemplate;
import com.task.entity.sop.ProcardTemplate;
import com.task.entity.sop.ProcessTemplate;

/**
 * 
 * @author 马凯
 *
 */
public interface InsTemplateService {
	
	/**
	 * 添加
	 * @param t
	 */
	public boolean add(InsTemplate t);


	/**
	 * 列表
	 * @param t
	 * @return
	 */
	public Object[] list(OsTemplate t,Integer rows, Integer size,String status);

	/**
	 * 条件查询；
	 * 
	 */

	public Map<Integer, Object> findInsTemplateByCondition(InsTemplate t,
			int pageNo, int pageSize);

	/**
	 * Android获取数据
	 * @param dept 
	 * @return
	 */
	public List<InsTemplate> getData();


	/**
	 * 获得
	 * @param t
	 * @return
	 */
	public InsTemplate get(InsTemplate t);


	/**
	 * 修改
	 * @param t
	 */
	public void update(InsTemplate t);


	public void updateTest();


	public void delete(InsTemplate t);
	
	
	public List<String> findAllmarkidlist(String zhonglei);
	
	public ProcardTemplate findprocardBymarkId(String markId);
	/**
	 * 根据件号获得未添加质量检验模板的工序号
	 */
	public List<String>  findprocessNoList(String markId);
	/**
	 * 根据件号和工序号获得检验图纸
	 */
	public List<ProcessTemplateFile>  getjytuzhi(String markId,String gongxuNum);
	/**
	 * 根据件号和获得外购件检验图纸
	 */
	public List<ProcessTemplateFile>  getjytuzhi1(String markId);
	/**
	 * 根据件号工序号得到工序名
	 */
	public String getgongxuName(String markId,String gongxuNum);
	
}
