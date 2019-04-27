package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.DutyClaimServer;
import com.task.entity.DutyClaim;

/***
 * 职位胜任要求Server层实现类
 * 
 * @author jhh
 * 
 */
public class DutyClaimServerImpl implements DutyClaimServer {
	private TotalDao totalDao;// Dao层

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/***
	 * 添加职位胜任要求
	 * 
	 * @param dutyClaim
	 *            职位胜任要求对象
	 * @return
	 */
	@Override
	public boolean addDutyClaim(DutyClaim dutyClaim) {
		if (dutyClaim != null) {
			return totalDao.save(dutyClaim);
		}
		return false;
	}

	/***
	 * 查找所有职位胜任要求
	 * 
	 * @return
	 */
	@Override
	public Object[] findAllDutyClaim(DutyClaim dutyClaim, int pageNo,
			int pageSize) {
		if ((Object) pageNo != null && (Object) pageSize != null) {
			if (dutyClaim == null) {
				dutyClaim = new DutyClaim();
			}
			dutyClaim.setFloor(1);
			String hql = totalDao.criteriaQueries(dutyClaim, null);
			List list = totalDao.findAllByPage(hql, pageNo, pageSize);
			int count = totalDao.getCount(hql);
			Object[] o = { list, count };
			return o;
		}
		return null;
	}

	/***
	 * 通过人员信息查找所有职位
	 * 
	 * @return
	 */
	@Override
	public List findAllDuty() {
		String hql = "select distinct duty from Users where duty is not null and duty <>'' order by duty";
		return totalDao.find(hql);
	}

	/***
	 * 通过职位和层数查询
	 * 
	 * @param duty
	 *            职位
	 * @param floor
	 *            层数
	 * @return
	 */
	@Override
	public DutyClaim findDutyClaimByFloor(String duty, Integer floor) {
		String hql = "from DutyClaim where duty=? and floor=?";
		return (DutyClaim) totalDao.getObjectByCondition(hql, duty, floor);
	}

	/***
	 * 通过父类id和状态查询所有人员信息
	 * 
	 * @param id
	 *            父类id
	 * @param claimStatus
	 *            状态(现有人员、备选人员)
	 * @return
	 */
	@Override
	public List findDutyClaimByFather(Integer id, String claimStatus) {
		String hql = "from DutyClaim d where d.claimStatus=? and d.dutyClaim.id=?";
		return totalDao.query(hql, claimStatus, id);
	}

	/***
	 * 通过用户类型、用户id、所属父类检查成员是否已经存在
	 * 
	 * @param dutyClaim
	 *            对象
	 * @return
	 */
	@Override
	public DutyClaim findDutyClaimByUser(DutyClaim dutyClaim) {
		if (dutyClaim != null) {
			String hql = "from DutyClaim d where d.claimStatus=? and d.userId=? and d.dutyClaim.id=?";
			return (DutyClaim) totalDao.getObjectByCondition(hql, dutyClaim
					.getClaimStatus(), dutyClaim.getUserId(), dutyClaim
					.getDutyClaim().getId());
		}
		return null;
	}

	/***
	 * 根据id查询职位胜任要求
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public DutyClaim findDutyClaimById(Integer id) {
		return (DutyClaim) totalDao.getObjectById(DutyClaim.class, id);
	}

	/***
	 * 修改职位胜任要求
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public boolean updateDutyClaim(DutyClaim dutyClaim) {
		if (dutyClaim != null) {
			return totalDao.update(dutyClaim);
		}
		return false;
	}

	/***
	 * 修改职位胜任要求
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public boolean updateDutyClaim(DutyClaim dutyClaim, Integer id) {
//		DutyClaim oldDutyClaim = (DutyClaim) totalDao.getObjectById(
//				DutyClaim.class, id);
		if (dutyClaim != null) {
//			dutyClaim.setDutyClaimSet(oldDutyClaim.getDutyClaimSet());
			return totalDao.update(dutyClaim);
		}
		return false;
	}

	/***
	 * 修改职位胜任要求
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public boolean delDutyClaim(DutyClaim dutyClaim) {
		if (dutyClaim != null) {
			return totalDao.delete(dutyClaim);
		}
		return false;
	}

}
