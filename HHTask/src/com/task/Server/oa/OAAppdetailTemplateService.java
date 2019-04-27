package com.task.Server.oa;

import java.io.File;
import java.util.List;

import com.task.entity.OaAppDetailTemplate;

/**
 * 申购
 * @author wcy
 *
 */
public interface OAAppdetailTemplateService {

	/**
	 * 添加申购物品
	 * @param template
	 * @return
	 */
	String saveOaAppDetailTemp(OaAppDetailTemplate template);
	
	/**
	 * 批量添加申购
	 * @param file
	 * @return
	 */
	String batchSaveOaappdetailTemp(File file);
	
	/**
	 * 删除申购
	 * @param id
	 * @return
	 */
	boolean deleteTemplate(Integer id);
	
	/**
	 * 更新申购
	 * @param template
	 * @return
	 */
	boolean updateTemplate(OaAppDetailTemplate template);

    //查询分页，筛选
    @SuppressWarnings("unchecked")
	void findListExportExcel(OaAppDetailTemplate template, String startDate,
                                 String endDate, Integer cpage, Integer pageSize);

    /**
	 * 根据id获得申购信息
	 * @param id
	 * @return
	 */
	OaAppDetailTemplate getOADTById(Integer id);
	
	/**
	 * 根据条件查询申购列表
	 * @param template
	 * @return
	 */
	List<OaAppDetailTemplate> findOADTListByCondition(OaAppDetailTemplate template,Integer cpage,Integer pageSize);
	
	/**
	 * 根据条件查询申购的总数
	 * @param template
	 * @return
	 */
	Integer findOADTCountByCondition(OaAppDetailTemplate template);
	
	/**
	 * 查询申购分页，模糊查询
	 */
	Object[] findList(OaAppDetailTemplate template ,String startDate,String endDate,Integer cpage,Integer pageSize);
	
	/**
	 * 查询所有的待审批记录
	 */
	Object[] findExamineList(OaAppDetailTemplate template ,String startDate,String endDate,Integer cpage,Integer pageSize);
	/**
	 * ajax 下拉查找物品名称
	 * @param tag
	 * @param powerTag
	 * @return
	 */
	 String findchildClass(String tag, String powerTag);
	
	/**
	 * 根据物品名称查找申购信息
	 * @param appName
	 * @return
	 */
	 OaAppDetailTemplate getTempByAppName(String appName);
	
	 OaAppDetailTemplate getTempByCode(String wlcode);
	/**
	 * 物品检索
	 * @param search
	 * @param detailClass
	 * @return
	 */
	 List<OaAppDetailTemplate> searchAppName(String search,String detailClass);

	/**
	 * 查找检索类型字段，做下拉
	 * @param tag
	 * @param powerTag
	 * @return
	 */
	String findOASelect(String tag, String powerTag);
	
	/**
	 * 批量审批
	 * @param ids
	 * @param tag
	 * @return
	 */
	public String auditAllShenPi(int[] ids, String tag);
}