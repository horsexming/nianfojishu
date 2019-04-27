package com.task.Server;

import java.io.File;
import java.util.List;

import com.task.entity.Taskmanager;

public interface TaskmanagerService {

	String addTaskmanager(Taskmanager taskmanager);

	Taskmanager findTaskmanagerbyid(Integer id);

	String deleteTaskmanager(Integer id);

	String updateTaskmanager(Taskmanager taskmanager);


	String addtasksuggestion(Taskmanager taskmanager);

	Object[] findTaskmanager(Taskmanager taskmanager, int pageNo, int pageSize,
			String level,String status);
	/***
	 * 查询各种状态的数据列表
	 * @param taskmanager
	 * @param pagestatus
	 * @return
	 */
	List findTaskmanager(Taskmanager taskmanager, String pagestatus);

	void file(File add);
	List getdept();

	void exportExcelTask(Taskmanager taskmanager, String status, String level);


}
