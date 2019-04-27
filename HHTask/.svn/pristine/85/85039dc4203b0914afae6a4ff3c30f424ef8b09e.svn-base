package com.task.Server;

import java.util.List;

import com.task.entity.DutyClaim;

/***
 * 职位胜任要求Server层
 * 
 * @author jhh
 * 
 */
public interface DutyClaimServer {

	/***
	 * 添加职位胜任要求
	 * 
	 * @param dutyClaim
	 *            职位胜任要求对象
	 * @return
	 */
	boolean addDutyClaim(DutyClaim dutyClaim);

	/***
	 * 查找所有职位胜任要求
	 * 
	 * @return
	 */
	Object[] findAllDutyClaim(DutyClaim dutyClaim, int pageNo, int pageSize);

	/***
	 * 根据id查询职位胜任要求
	 * 
	 * @param id
	 * @return
	 */
	DutyClaim findDutyClaimById(Integer id);

	/***
	 * 修改职位胜任要求
	 * 
	 * @param id
	 * @return
	 */
	boolean updateDutyClaim(DutyClaim dutyClaim);

	/***
	 * 通过人员信息查找所有职位
	 * 
	 * @return
	 */
	List findAllDuty();

	/***
	 * 通过职位和层数查询
	 * 
	 * @param duty
	 *            职位
	 * @param floor
	 *            层数
	 * @return
	 */
	DutyClaim findDutyClaimByFloor(String duty, Integer floor);

	/***
	 * 修改职位胜任要求
	 * 
	 * @param id
	 * @return
	 */
	boolean delDutyClaim(DutyClaim dutyClaim);

	/***
	 * 通过用户类型、用户id、所属父类检查成员是否已经存在
	 * 
	 * @param dutyClaim
	 * 
	 * @return
	 */
	DutyClaim findDutyClaimByUser(DutyClaim dutyClaim);

	/***
	 * 通过父类id和状态查询所有人员信息
	 * 
	 * @param id
	 *            父类id
	 * @param claimStatus
	 *            状态(现有人员、备选人员)
	 * @return
	 */
	List findDutyClaimByFather(Integer id, String claimStatus);

	/***
	 * 修改职位胜任要求
	 * 
	 * @param id
	 * @return
	 */
	boolean updateDutyClaim(DutyClaim dutyClaim, Integer id);
}
