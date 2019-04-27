package com.task.Server.caiwu;

import java.util.List;
import java.util.Map;

import com.task.entity.caiwu.DocumentWord;

public interface DocumentWordServer {

	/**
	 * 添加凭证字
	 */
	String add(DocumentWord dw);
	/**
	 * 禁用凭证字
	 * @param id
	 * @return
	 */
	boolean jinyong(Integer id);
	/**
	 * 反禁用凭证字(恢复使用)
	 * @param id
	 * @return
	 */
	boolean fanjinyong(Integer id);
	/**
	 * 更新凭证字
	 * @param moneyType
	 * @return
	 */
	boolean update(DocumentWord dw);
	/**
	 * 删除凭证字
	 * @param moneyType
	 * @return
	 */
	boolean del(DocumentWord dw);
	/**
	 * 查询所有凭证字
	 */
	List<DocumentWord> findAll();
	/**
	 * 查询所有凭证字
	 */
	List<DocumentWord>  findAllBypage(int pageNo,
			int pageSize);
	/**
	 * 条件查询
	 */
	Map<Integer, Object> findByCondition(DocumentWord dw,
			int pageNo, int pageSize);
	public int getcont();
	/**
	 * 根据Id获得凭证字信息
	 */
	DocumentWord findMoneyTypeById(Integer id);

}
