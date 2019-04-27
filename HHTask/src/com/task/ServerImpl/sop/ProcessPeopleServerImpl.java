package com.task.ServerImpl.sop;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.sop.ProcessPeopleServer;
import com.task.entity.sop.ProcessPeople;

public class ProcessPeopleServerImpl implements ProcessPeopleServer {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/***
	 * 添加人员
	 * 
	 * @param processPeople
	 * @return
	 */
	public boolean addProcessPeople(ProcessPeople processPeople) {
		return totalDao.save(processPeople);
	}

	/***
	 * 通过工序id查询人员
	 * 
	 * @param processId
	 * @return
	 */
	public List findPPByProcessId(Integer processId) {
		if (processId != null) {
			String hql = "from ProcessPeople where processId=? order by status";
			return totalDao.query(hql, processId);
		}
		return null;
	}

	/***
	 * 删除人员
	 * 
	 * @param processPeople
	 * @return
	 */
	@Override
	public boolean delProcessPeople(ProcessPeople processPeople) {
		if (processPeople != null)
			return totalDao.delete(processPeople);
		return false;
	}

	/***
	 * 通过id查询人员
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ProcessPeople findProPeople(Integer id) {
		if (id != null && id > 0)
			return (ProcessPeople) totalDao.getObjectById(ProcessPeople.class,
					id);
		return null;
	}

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
	@Override
	public ProcessPeople findProPeople(String code, String addUserCode,
			Integer processId) {
		if (code != null && addUserCode != null && processId != null) {
			String hql = "from ProcessPeople where code=? and addUserCode=? and processId=?";
			return (ProcessPeople) totalDao.getObjectByCondition(hql, code,
					addUserCode, processId);
		}
		return null;
	}

	/***
	 * 删除人员
	 * 
	 * @param processPeople
	 * @return
	 */
	@Override
	public boolean updateProcessPeople(ProcessPeople processPeople) {
		if (processPeople != null)
			return totalDao.update(processPeople);
		return false;
	}

}
