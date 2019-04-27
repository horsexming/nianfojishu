package com.task.Server.seal;

import java.util.Map;

import com.task.entity.Users;
import com.task.entity.seal.BorrowSeal;
import com.task.entity.seal.SealLog;
import com.task.entity.seal.SealLogType;

public interface SealLogServer {
/**
 * 添加申请
 * @param sealLog
 * @return
 */
public SealLog add(SealLog sealLog) throws Exception ;
/**
 * 通过id获取印章申请记录
 * @param id
 * @return
 */
public SealLog getSealLog(Integer id);
/**
 * 分页获取印章申请情况
 * @param sealLog
 * @param parseInt
 * @param pageSize
 * @param pageStatus 
 * @return
 */
public Map<Integer, Object> findSealLogsByCondition(SealLog sealLog,
		int parseInt, int pageSize, String pageStatus,String tag);
/**
 * 分页获取印章
 * @param sealLogType
 * @param parseInt
 * @param pageSize
 * @param pageStatus 
 * @return
 */
public Map<Integer, Object> findSealLogTypeByCondition(SealLogType sealLogType,
		int parseInt, int pageSize);
/**
 * 添加印章
 * @param SealLogType
 * @return
 */
public String addsealLogtype(SealLogType sealLogType);
/**
 * 根据id删除对象
 * @param id
 * @return
 */
public boolean delete(Integer id);
/**
 * 修改，如果是打回状态就重新申请
 * @param sealLog
 * @return
 */
public boolean update(SealLog sealLog);
/**
 * 确认使用印章
 * @param id
 * @return
 */
public boolean makeSure(Integer id,String pageStatus);
/**
 * 通过code 或者 name 获取用户对象
 * @param sealLog
 * @return
 */
public Users getUser(SealLog sealLog);

/**
 * 上传返回附件
 * @param sealLog
 * @return
 */
public String update2(SealLog sealLog);

/**
 * 判断是否有未上传的印章申请
 * @return
 */
public String toaddSeal();
/**
 * 借用印章申请
 * @return
 */
public String toborrowSeal();
public String addBorrowSeal(BorrowSeal borrowSeal);
public void updatelinshi();
}
