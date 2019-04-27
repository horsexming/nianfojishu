package com.task.Server.sop;

import java.util.List;

import com.task.entity.sop.ProcessPeople;

/***
 * 
 * @author jhh
 * 
 */
public interface ProcessPeopleServer {

	/***
	 * 添加人员
	 * 
	 * @param processPeople
	 * @return
	 */
	boolean addProcessPeople(ProcessPeople processPeople);

	/***
	 * 通过工序id查询人员
	 * 
	 * @param processId
	 * @return
	 */
	List findPPByProcessId(Integer processId);

	/***
	 * 通过id查询人员
	 * 
	 * @param id
	 * @return
	 */
	ProcessPeople findProPeople(Integer id);

	/***
	 * 通过工号、添加人工号、工序id 查询人员是否已经存在
	 * 
	 * @param code
	 *            工号
	 * @param addUserCode
	 *            添加人工号
	 * @param processId
	 *            工序id
	 * @return
	 */
	ProcessPeople findProPeople(String code, String addUserCode,
			Integer processId);

	/***
	 * 删除人员
	 * 
	 * @param processPeople
	 * @return
	 */
	boolean delProcessPeople(ProcessPeople processPeople);

	/***
	 * 修改人员
	 * 
	 * @param processPeople
	 * @return
	 */
	boolean updateProcessPeople(ProcessPeople processPeople);

}
