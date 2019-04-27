package com.task.Server.sys;

import com.task.entity.system.ShortLink;

public interface ShortLinkServer {

	/**
	 * 添加长连接，获取短链接
	 * @return
	 */
	String addShortLink(String longUrl,String remark);
	
	
	/**
	 * 根据短链接特征值
	 */
	ShortLink getshortByLongLink(String shortUrl);
}
