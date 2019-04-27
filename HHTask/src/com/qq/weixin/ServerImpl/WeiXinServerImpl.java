package com.qq.weixin.ServerImpl;

import com.qq.weixin.Server.WeiXinServer;
import com.task.Dao.TotalDao;

public class WeiXinServerImpl implements WeiXinServer{
   private TotalDao totalDao;

public TotalDao getTotalDao() {
	return totalDao;
}

public void setTotalDao(TotalDao totalDao) {
	this.totalDao = totalDao;
}
   
}
