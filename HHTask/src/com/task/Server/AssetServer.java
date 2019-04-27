package com.task.Server;

import java.io.File;
import java.util.List;

import com.task.entity.Asset;

public interface AssetServer {
	// 添加固定资产表中的数据
	public boolean addAsset(Asset ass);
	// 查询所有固定资产数据
	public List findShouList(int pageNo, int pageSize);
	// 获得所有数量
	public Integer getCount();
	
	//根据ID查询所有信息
	public Asset findAssetById(int id);
	//模糊查询
	public List findUsersByCondition(Asset asset,int pageNo, int pageSize);
	
	//获得模糊查询的总数量
	public int findUsersByConditioncount(Asset asset);
	
	//修改 
	public  boolean updateAsset(Asset asset);
	
	
	boolean uploadFile(Integer id, File fileUpload, String fileUploadFileName,
			String fileUploadContentType);
	
	

}
