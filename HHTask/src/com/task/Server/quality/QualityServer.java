package com.task.Server.quality;

import com.task.entity.quality.Quality;

public interface QualityServer {

	Object[] findQuailty(Quality quality, int parseNo, int pageSize,Integer test);

	void saveQuality(Quality quality);

	void delQuality(Integer delId);

	Quality findQuailtyById(Integer salId);

	void updateQuality(Quality quality);
    /**
     * 通过id获取原文件的文件名
     * @param id
     * @return
     */
	String getFileName(Integer id);

}
