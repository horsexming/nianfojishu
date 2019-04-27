package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.Category;

public interface CategoryServer {
	
	String add(Category category,String tag);
	/**
	 * 添加类别
	 * @param category
	 * @return
	 */
	String add(List<Category> cyList,String type);
	/**
	 * 修改类别
	 * @param category
	 * @return
	 */
	String update(Category category,String status);
	/**
	 * 根据类型来查询类别
	 * @param type
	 * @return
	 */
	List<Category> findCategoryByType(String type);
	/**
	 * 
	 * @param warehouserNumber
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	 Map<Integer, Object> findByCategoryCondition(Category category,int pageNo, int pageSize);
	 /**
	  * 根据id查询
	  * @param id
	  * @return
	  */
	 Category getcategoryById(Integer id);
	 /**
	  * 删除Category
	  * @param category
	  * @return
	  */
	 String delCategory(Category category);
	 /**
	  * 根据rootId查找所有的物料类别;
	  */
	 List<Category>  findCategoryByrootId(Integer rootId,String status,String tag);
	 /**
	  * 添加第一个;
	  */
	 Category addfrist(String pageStaus);
	 /**
	  * 绑定员工 物料类别
	  */
	 boolean updateUserCate(Integer userId,Integer cateId); 
	 /**
	  * 查看已绑定内容
	  */
	 void xiugaiUserCateByUser(Integer userId);
	 
	 public String isdelCategory(Category category);
	 
	 String	test(Integer id);
	 /**
	  * 判断是否为最低层
	  */
	 boolean islow(Integer id);
	 /**
	  * 同步修改外购件库上的生产节拍和配送时长
	  */
	 String updateYclAndWgj(Category category);
	 public String yclround();
	List<Category> findCategoryByType(String type, Integer userid);
}
