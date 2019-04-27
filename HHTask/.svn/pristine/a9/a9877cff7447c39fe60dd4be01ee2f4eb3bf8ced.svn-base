package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.ScreenFiles;

public interface ScreenFilesService {

	/**
	 * 跟据Screen类的id查出他一共可以生成几个文件
	 * @param id
	 */
	public List<Map> autoFile(Integer id);

	/**
	 * 添加文档
	 * @param files
	 */
	public void add(List<ScreenFiles> files);

	public List<ScreenFiles> getFilesByScreen(Integer id);

	public List<ScreenFiles> getFiles(Integer id);

	public void delete(Integer id);

	public List<ScreenFiles> getFilesForAndroid(Integer id);
	
}
