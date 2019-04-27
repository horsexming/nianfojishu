package com.task.ServerImpl.renshi;

/*
 * 李聪   2015-05-08
 * 
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.renshi.InterviewLogServer;
import com.task.ServerImpl.ess.ProcardBlServerImpl;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Careertrack;
import com.task.entity.Users;
import com.task.entity.renshi.Inter_Family;
import com.task.entity.renshi.InterviewLog;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class InterviewLogServerImpl implements InterviewLogServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/**
	 * 添加面试单对象
	 * 
	 * @param interviewLog
	 * @return
	 */
	@Override
	public boolean addInterviewLog(InterviewLog interviewLog,
			List<Inter_Family> interFamilies) {
		// TODO Auto-generated method stub
		if (interviewLog != null) {
			if (interFamilies != null && interFamilies.size() > 0) {
				for (Inter_Family family : interFamilies) {
					// family.setAddTime(Util.getDateTime());
					family.setInterviewLog(interviewLog);// 将主表对象赋值
					totalDao.save(family);
				}
			}
			Careertrack ck = new Careertrack(interviewLog.getName(),interviewLog.getInterviewDept(), 
					interviewLog.getGereneducation(),interviewLog.getCardID(),null , 
					null, interviewLog.getBirthday(), interviewLog.getTel(),Util.getDateTime("yyyy-MM-dd HH:mm:ss")
					,interviewLog.getJob(),"面试",null,null);
			totalDao.save(ck);
			interviewLog.setInterviewAddTime(Util.getDateTime());
			interviewLog.setInter_status("待测试");
			return totalDao.save(interviewLog);
		}
		return false;
	}

	/**
	 * 通过id删除面试单对象
	 * 
	 * @param interviewLog
	 * @return
	 */
	@Override
	public boolean deleteInterviewLog(Integer id) {
		// TODO Auto-generated method stub
		InterviewLog ivl = getInterviewLogById(id);// 通过接口获取该id对象
		if (ivl != null) {
			return totalDao.delete(ivl);
		}
		return false;
	}

	/**
	 * 修改面试单对象
	 * 
	 * @param interviewLog
	 * @return
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.renshi.InterviewLogServer#updateInterviewLog(com.task
	 * .entity.renshi.InterviewLog)
	 */
	@Override
	public boolean updateInterviewLog(InterviewLog interviewLog) {
		// TODO Auto-generated method stub
		boolean b = false;
		InterviewLog ivl = getInterviewLogById(interviewLog.getId());
		if (ivl != null) {
			// BeanUtils.copyProperties(interviewLog, ivl);

			ivl.setTel(interviewLog.getTel());// 联系方式
			// ivl.setCardID(interviewLog.getCardID());//身份证号码
			/*
			 * 评测结果
			 */
			ivl.setSpecialtyScore(interviewLog.getSpecialtyScore());
			ivl.setExperienceScore(interviewLog.getExperienceScore());
			ivl.setJob_direction(interviewLog.getJob_direction());
			ivl.setNature_hobby(interviewLog.getNature_hobby());
			ivl.setIntelligence(interviewLog.getIntelligence());
			ivl.setJob_attitude(interviewLog.getJob_attitude());
			ivl.setAnalytical_skills(interviewLog.getAnalytical_skills());
			ivl.setVoice_ability(interviewLog.getVoice_ability());
			ivl.setCommunication_skills(interviewLog.getIntelligence());
			ivl.setEducation(interviewLog.getEducation());
			ivl.setExaminer_remark(interviewLog.getExaminer_remark());
			ivl.setEnroll_result(interviewLog.getEnroll_result());
			ivl.setNi_post(interviewLog.getNi_post());
			ivl.setNi_salary(interviewLog.getNi_salary());
			ivl.setFactory_opinion(interviewLog.getFactory_opinion());
			ivl.setComment(interviewLog.getComment());
			ivl.setInterviewUpDatetime(Util.getDateTime());
			String hql_ck = "from Careertrack where cardId=?";
			Careertrack ck=	(Careertrack) totalDao.getObjectByCondition(hql_ck, ivl.getCardID());
			boolean bool = false;
			if(ck == null){
				bool = true;
				ck = new Careertrack(interviewLog.getName(),interviewLog.getInterviewDept(), 
						interviewLog.getGereneducation(),interviewLog.getCardID(),null , 
						null, interviewLog.getBirthday(), interviewLog.getTel(),Util.getDateTime("yyyy-MM-dd HH:mm:ss")
						,interviewLog.getJob(),"面试",null,null);
			}
			if ("试用".equals(interviewLog.getEnroll_result())) {
				ivl.setInter_status("yes");
				ck.setStatus("待入职");
			} else {
				ivl.setInter_status("no");
				ck.setStatus("不录用");
			} 
			
			ck.setPhoneNumber(interviewLog.getTel());
			if(bool){
				totalDao.save(ck);
			}else{
				totalDao.update(ck);
			}
			b = totalDao.update(ivl);
			// 如果试用调用审批流程
			if ("yes".equals(ivl.getInter_status())&&b) {
				String processName = "入职申请";
				Integer epId = null;
				try {
					epId = CircuitRunServerImpl.createProcess(processName,
							InterviewLog.class, ivl.getId(), "interStatus",
							"id",
							"interviewLogAction_select.action?interviewLog.id="
									+ ivl.getId(), ivl.getInterviewDept()
									+ "部门 " + ivl.getName() + " 入职申请，请您审批",
							true);
					if (epId != null && epId > 0) {
						ivl.setEpId(epId);
						ivl.setInterStatus("未审批");
						totalDao.update(ivl);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return b;
	}

	/**
	 * 通过id获取面试单对象
	 * 
	 * @param interviewLog
	 * @return
	 */
	@Override
	public InterviewLog getInterviewLogById(Integer id) {
		// TODO Auto-generated method stubo
		Object o = totalDao.getObjectById(InterviewLog.class, id);
		return (InterviewLog) o;
	}

	/**
	 * 查询所有面试单对象
	 * 
	 * @param interviewLog
	 * @return
	 */
	@Override
	public List<InterviewLog> findAll() {
		// TODO Auto-generated method stub
		List all = totalDao.query("from InterviewLogById");
		if (all.size() > 0) {
			return (List<InterviewLog>) all;
		}
		return null;
	}

	/**
	 * 按条件分页查询面试单
	 * 
	 * @param interviewLog
	 *            对象
	 * @param pageNo
	 *            起始页
	 * @param pageSize
	 *            每页数量
	 * @return
	 */
	@Override
	public Map<Integer, Object> findInterviewLogsByCondition(
			InterviewLog interviewLog, int pageNo, int pageSize, String ccTag) {
		// TODO Auto-generated method stub
		if (interviewLog == null) {
			interviewLog = new InterviewLog();
		}
		String hql = null;
		if (ccTag != null && "dept".equals(ccTag)) {
			Users users = Util.getLoginUser();
			
			interviewLog.setInterviewDept(users.getDept());
			
			String sql = " interviewDept='" + users.getDept() + "'";
			
			
			
			hql = totalDao.criteriaQueries(interviewLog, sql);
		} else {
			hql = totalDao.criteriaQueries(interviewLog, null);
		}
		hql += " order by id desc";
		interviewLog = null;
		List listInt = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		// Object[] o = { list, count };
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listInt);
		map.put(2, count);
		return map;
	}

	// 条件查询（待入职）
	public Object[] findInterviewsByCondition(InterviewLog interviewLog,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (interviewLog == null) {
			interviewLog = new InterviewLog();
		}
		String sql = " inter_status='yes' and enroll_result='试用' and interStatus='同意'";
		String hql = totalDao.criteriaQueries(interviewLog, sql);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	// 查询家庭信息集合
	@Override
	public List<Inter_Family> getinterFamilyById(Integer id) {
		// TODO Auto-generated method stub
		if (id != null && id > 0) {
			String hql = "from Inter_Family where interviewLog.id=?";
			List all = totalDao.query(hql, id);
			if (all.size() > 0) {
				return (List<Inter_Family>) all;
			}
		}
		return null;
	}

	@Override
	public boolean findadmin() {
		// TODO Auto-generated method stub
		return ProcardBlServerImpl.SystemShezhi("PEBS考勤机");
	}
}
