package com.task.Server.gys;

import java.util.List;
import java.util.Map;

import com.task.entity.gys.ProcardGys;
import com.task.entity.gys.ProcessGysInfor;
import com.task.entity.gzbj.Gzstore;
import com.task.entity.sop.Procard;
import com.task.entity.sop.ProcessInfor;

public interface GyslgxServer {

/**
 * 通过条件查询供应商卡片
 * @param procardGys
 * @param parseInt
 * @param pageSize
 * @return
 */
	public Map<Integer, Object> findProcardGysByCondition(ProcardGys procardGys, int pageNo,String pageStatus, int pageSize,String sql);
/**
 * 激活供应商流水卡
 * @param id
 * @return
 */
public boolean jihuoProcardGys(Integer id);
/**
 * 根据Id获取供应商流水卡对象
 * @param id
 * @return
 */
public ProcardGys findProcardGysById(Integer id);
/***
 * 查询最大层数
 * 
 * @param rootId
 * @return
 */
public Integer findMaxbelongLayer(Integer rootId);
/***
 * 根据fatherId查询显示的节点
 * 
 * @param fatherId
 *            Id
 * @return
 */
public List<ProcardGys> findProByBel(Integer fatherId, Integer maxBelongLayer);
/***
 * 通过流水卡id查询对应工艺卡片
 * 
 * @param cardId
 * @return
 */
Object[] findProcardByRunCard(Integer cardId);
public List listProvisionByMarkId(String markId);

/***
 * 添加自检表
 * 
 * @param contentList
 *            自检项
 * @param isQualifiedList
 *            是否合格
 * @param processId
 *            工序id
 * @return
 */
public boolean saveZj(List contentList, List isQualifiedList, int processId);
/***
 * 根据id查询工序(判断如何领取工序)
 * 
 * @param id
 * @return
 */
Object[] findProcess(Integer id);
/***
 * 领取工序
 * 
 * @param processIds
 * @param processNumbers
 * @param processCards
 * @return
 */
String collorProcess(Integer[] processIds, Float[] processNumbers,
		List processCards);
ProcessGysInfor getObjectByIdProcessInfor(Integer id);
Gzstore getObjectByIdGzstore(Integer gzstoreId);
/***
 * 提交工序
 * 
 * @return
 */
String updateProcess(ProcessGysInfor process);
/***
 * 提交激活
 * 
 * @param processId
 * @return
 */
String updateJihuo(Integer processId, Integer id);
/**
 * 根据rootId删除整个流水卡片树并返还内部计划数量
 * 
 * @param id
 * @return
 */
boolean deleteprocardtree(Integer id);
}
