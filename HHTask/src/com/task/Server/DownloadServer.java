package com.task.Server;

import java.util.Map;

import com.task.entity.Download;

/**
 * @author Li_Cong
 * @Date 2015-11-24
 */

public interface DownloadServer{
	/**
	 * @author 添加
	 */
	public String addDownload(Download download);
	/**
	 * 分页查询考勤内容
	 * @param download
	 * @param pageNo
	 * @param pageSize
	 * @param tag
	 * @return
	 */
	public Map<Integer, Object> findDownLoadByCondition(Download download, int pageNo, int pageSize, String tag);
}
