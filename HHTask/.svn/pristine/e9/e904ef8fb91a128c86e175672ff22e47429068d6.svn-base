package com.task.Server;

import java.util.List;

import com.task.entity.android.OsScope;
import com.task.entity.android.OsTemplate;
import com.task.entity.gzbj.ProcessGzstore;
import com.task.entity.sop.ProcardTemplate;
import com.task.entity.sop.ProcessTemplate;
import com.task.entity.sop.PurchasedPartsInput;

/**
 * 外购件检查
 * @author 马凯
 *
 */
public interface OsTemplateService {
	
	/**
	 * 添加
	 * @param template
	 */
	public boolean add(OsTemplate template);
	
	/**
	 * 获取所有数据.更新到pad上
	 * @return
	 */
	public List<OsTemplate> getAll();

	public Object[] list(OsTemplate t, Integer parseInt, Integer pageSize,String status);

	public List<OsTemplate> getData();

	public List<OsScope> getScopes(OsTemplate t);

	public void deleteOsTemplate(OsTemplate os);

	public OsTemplate byIdOsTemplate(Integer id);

	public boolean updateOsTemplate(OsTemplate t);

	public void deleteOsScope(OsScope os);

	public OsScope byIdOsScope(Integer id);

	public boolean UpdateOsScope(OsScope tt);

	public void addOsScope(OsScope tt, OsTemplate t);
    /**
     * 核对可检测的外购件
     * @param markIds
     * @return
     */
	public void addOsScope1(List<OsScope> listOsScope,OsTemplate t);
	public boolean shuaxinsScope(List<OsScope> listOsScope,List<OsTemplate> ts);
	public List<PurchasedPartsInput> cheackMarkId(String markIds);
   /**
    * 获取需要检验的件号
    * @return
    */
	public List<OsTemplate> getApplyMarkId();
	/**
	 * 根据工序id查找工件信息；
	 * 
	 */
	public ProcardTemplate  getGongjianxinxi(Integer id);
	/**
	 * 
	 */
	public  OsTemplate getOsTemplate1(String partNumber,String gongxuNum);
	/**
	 * 通过件号，和工序号 获取所有的抽检记录
	 * 
	 * @param partNumber
	 * @param gongxuNum
	 * @return
	 */
	public  List<OsTemplate> getOsTemplate(String partNumber,String gongxuNum);
	
	public ProcessGzstore getProcessGzstorebyid(Integer id);
	/**
	 * 只从 OsTemplate 表中 删除 不删除数据;
	 */
	public boolean deleteOsScope1(OsTemplate ost, OsScope os);
	
	/**
	 * 把之前InsTemplate表上的数据更新到OsTemplate这个表上去;
	 */
	 
	public boolean addAll();
	
	public List<String> findAllmarkidlist();
	public void delOsRecord(Integer osId);
	
	public String updatetuzhi();
	/**
	 * /根据件号查询没有模板的版本号;
	 */
	List<ProcardTemplate>	findbanbenBymarkId(String markId);
	/**
	 * 
	 */
	String updateXjbz(Integer id,Integer xjbzId);
	
}
