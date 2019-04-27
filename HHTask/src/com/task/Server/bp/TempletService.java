package com.task.Server.bp;

import java.util.List;
import com.task.entity.bp.Templet;


public interface TempletService {

	/** 添加商品  */
	public boolean add(Templet templet);

	/** 更新商品  */
	public boolean update(Templet templet);

	/** 删除商品  */
	public boolean delete(Templet templet);
	
	/** 列出所有产品  */
	public List findAll();

	//AJAX点击
	public String findForSetlect(int i);
	
	public List findTempletTree(int id);
	
	/**
	 * 
	 * @Title: getProductTemplet
	 * @Description: TODO
	 * @param resPerson
	 * @return 
	 * @return List<Templet>  
	 * @throws
	 */
	public List<Templet> getProductTemplet(String resPerson);
	
	//添加顶级产品
	public boolean addRoot(Templet templet);
	
	
	//获得顶级产品ID
	public Templet getRoot(Templet templet);

	public List<Templet> findForVerify();

	//同意审批
	public boolean agree(Templet templet);

	//驳回
	public boolean overrule(Templet templet);


	List<Templet> searchByModel(Templet templet, String resPerson);

	/** 获取车型 */
	public List<String> getModels(String resPerson);

}
