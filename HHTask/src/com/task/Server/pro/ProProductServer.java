package com.task.Server.pro;


import java.util.Map;

import com.task.entity.pro.ProProduct;


public interface ProProductServer {
	public String addProProduct(ProProduct proProduct);//添加项目产品记录
	
	public String deleteProProduct(ProProduct proProduct);//删除项目产品记录
	
	public String updateProProduct(ProProduct proProduct);//更新项目产品记录
	
	public ProProduct getProProductById(Integer id);//获得项目产品记录
	
	public Object[] findAllProProductByproId(Map map ,int  pageNo,int pageSize);//获得项目产品记录集合
}
