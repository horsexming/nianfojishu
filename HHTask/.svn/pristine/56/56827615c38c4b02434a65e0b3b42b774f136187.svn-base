package com.task.Server.gzbj;

import java.util.List;
import java.util.Map;

import com.task.entity.DeptNumber;
import com.task.entity.Machine;
import com.task.entity.Store;
import com.task.entity.gzbj.Gzstore;
import com.task.entity.gzbj.GzstoreUseLog;
import com.task.entity.gzbj.ProcessGzstore;

//工装使用记录
public interface GzstoreUseLogServer {
/**
 * 分页显示工装使用记录
 * @param gzstoreUseLog
 * @param parseInt
 * @param pageSize
 * @return
 */
	Map<Integer, Object> findGzstoreUseLogsByCondition(
			GzstoreUseLog gzstoreUseLog, int parseInt, int pageSize);
/**
 * 通过id获取对象
 * @param id
 * @return
 */
GzstoreUseLog getById(Integer id);
/**
 * 通过id删除对象
 * @param id
 * @return
 */
boolean deleteById(Integer id);


}
