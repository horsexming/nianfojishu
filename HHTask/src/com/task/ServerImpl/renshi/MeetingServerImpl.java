package com.task.ServerImpl.renshi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.Server.renshi.MeetingServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.AlertMessages;
import com.task.entity.Users;
import com.task.entity.renshi.InDoorScreen;
import com.task.entity.renshi.InDoorScreenUsers;
import com.task.entity.renshi.Meeting;
import com.task.entity.renshi.MeetingPerson;
import com.task.entity.system.CircuitRun;
import com.task.util.Util;

public class MeetingServerImpl implements MeetingServer{

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public String addMeeting(Meeting meeting) {

		String processName = "会议申请";
		if(meeting!=null) {
			if(meeting.getStartDate()!=null ) {
				String idAppend = "";
				if(meeting.getId()!=null) {
					idAppend=" and id <>"+meeting.getId();
				}
				Integer count =  totalDao.getCount("from Meeting where startDate<=? and startDate>=? and endDate<=? and endDate>=?"+idAppend
						, meeting.getStartDate(),meeting.getEndDate(), meeting.getStartDate(),meeting.getEndDate());
				if(count>0) {
					return "当前时间段已经有人申请了会议，不能重复申请，谢谢。";
				}
				totalDao.clear();
			}else {
				return "会议开始时间和结束时间不能为空";
			}
			if (meeting.getId()!=null) {
				
				Meeting meetingById = getMeetingById(meeting.getId(), null);
				if(meetingById.getEpId()!=null){
					AlertMessages alertMessages = (AlertMessages) totalDao.getObjectByCondition(
							"from AlertMessages where functionUrl=?", "CircuitRunAction_findAduitPage.action?id="+meetingById.getEpId());
					if(alertMessages!=null){
						totalDao.delete(alertMessages);
					}
					CircuitRun cancalCir = (CircuitRun) totalDao.getObjectById(CircuitRun.class, meetingById.getEpId());
					if(cancalCir!=null) {
						totalDao.delete(cancalCir);
					}
				}
				if(meetingById!=null) {
					meetingById.setAddTime(Util.getDateTime());
					meetingById.setContent(meeting.getContent());
					meetingById.setEndDate(meeting.getEndDate());
					meetingById.setPosition(meeting.getPosition());
					meetingById.setStartDate(meeting.getStartDate());
					meetingById.setTitle(meeting.getTitle());
					meetingById.setUserCode(Util.getLoginUser().getCode());
					meetingById.setUserId(Util.getLoginUser().getId());
					meetingById.setUserName(Util.getLoginUser().getName());
					try {
						Integer epId = CircuitRunServerImpl.createProcess(processName, Meeting.class, meetingById.getId(), "epStatus", "id",
								meetingById.getUserName()+"申请预定会议，请您审批。", true);
						if(epId!=null && epId>0) {
							meetingById.setEpId(epId);
							CircuitRun circuitRun = (CircuitRun) totalDao.getObjectById(CircuitRun.class, epId);
							if(circuitRun.getAllStatus()!=null &&circuitRun.getAllStatus().equals("同意")) {
								meetingById.setEpStatus("同意");
							}else {
								meetingById.setEpStatus("未审批");
							}
							totalDao.update(meetingById);
							return "修改成功";
						}
					} catch (Exception e) {
						throw new RuntimeException(e.getMessage());
					}
				}
			}
			meeting.setUserId(Util.getLoginUser().getId());
			meeting.setUserCode(Util.getLoginUser().getCode());
			meeting.setUserName(Util.getLoginUser().getName());
			meeting.setAddTime(Util.getDateTime());
			boolean save = totalDao.save(meeting);
			if(save) {
				try {
					Integer epId = CircuitRunServerImpl.createProcess(processName, Meeting.class, meeting.getId(), "epStatus", "id",
							meeting.getUserName()+"申请预定会议，请您审批。", true);
					if(epId!=null && epId>0) {
						meeting.setEpId(epId);
						CircuitRun circuitRun = (CircuitRun) totalDao.getObjectById(CircuitRun.class, epId);
						if(circuitRun.getAllStatus()!=null &&circuitRun.getAllStatus().equals("同意")) {
							meeting.setEpStatus("同意");
						}else {
							meeting.setEpStatus("未审批");
						}
						totalDao.update(meeting);
						return "添加成功";
					}
				} catch (Exception e) {
					throw new RuntimeException(e.getMessage());
				}
			}
		}
		return null;
	}

	private void getObjectById(Class<CircuitRun> class1, Integer epId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> findMeetingByCon(Meeting meeting, Integer pageNo, Integer pageSize, String pageStatus) {
		if(meeting==null) {
			meeting = new Meeting();
		}
		String hql = totalDao.criteriaQueries(meeting, null);
		
		List<Meeting> list = totalDao.findAllByPage(hql, pageNo, pageSize,null);
		Map<String, Object> map = new HashMap<String, Object>();
		if(list!=null && list.size()>0) {
			map.put("list", list);
			Integer count = totalDao.getCount(hql);
			map.put("count", count);
		}
		return map;
	}

	@Override
	public String addMeetingPerson(Integer meetingId, MeetingPerson person) {
		if(meetingId!=null && person!=null) {
			MeetingPerson meetingPerson = (MeetingPerson) totalDao.getObjectByCondition("from MeetingPerson where userId =? and meeting.id=?", person.getUserId(),meetingId);
			if(meetingPerson ==null) {
				Meeting meeting = (Meeting) totalDao.getObjectById(Meeting.class, meetingId);
				Users users = (Users) totalDao.getObjectById(Users.class, person.getId());
				if(meeting!=null && users!=null) {
					meetingPerson = new MeetingPerson();
					meetingPerson.setMeeting(meeting);
					meetingPerson.setUserCode(users.getCode());
					meetingPerson.setUserName(users.getName());
					meetingPerson.setUserId(person.getId());
					totalDao.save(meetingPerson);
				}
				
			}
			return "添加成功";
		}
		return null;
	}

	@Override
	public String removeMeetingPerson(Integer meetingId, MeetingPerson person) {
		if(meetingId!=null && person!=null) {
			MeetingPerson meetingPerson = (MeetingPerson) totalDao.getObjectByCondition("from MeetingPerson where userId =? and meeting.id=?", person.getUserId(),meetingId);
			if(meetingPerson !=null) {
				totalDao.delete(meetingPerson);
				return "删除成功";
			}
		}
		return null;
	}

	@Override
	public Meeting getMeetingById(Integer meetingId, String pageStatus) {
		if(meetingId!=null) {
			Meeting meeting = (Meeting) totalDao.getObjectById(Meeting.class, meetingId);
			return meeting;
		}
		return null;
	}

	@Override
	public String bindMeetingUsers(Integer id, Integer usersId) {
		Users users = (Users) totalDao.getObjectById(Users.class, usersId);
		if(users!=null && id!=null) {
			MeetingPerson meetingPerson = (MeetingPerson) totalDao.getObjectByCondition(
					"from MeetingPerson where userId = ? and meeting.id=?", usersId,id);
			if(meetingPerson==null) {
				Meeting meeting = (Meeting) totalDao.getObjectById(Meeting.class, id);
				meetingPerson = new MeetingPerson();
				meetingPerson.setUserId(usersId);
				meetingPerson.setMeeting(meeting);
				meetingPerson.setUserName(users.getName());
				totalDao.save(meetingPerson);
			}
			return "success";
		}
		return "error";
	}

	@Override
	public String cancelMeetingUsers(Integer screenId, Integer usersId) {
		Users users = (Users) totalDao.getObjectById(Users.class, usersId);
		if(users!=null && screenId!=null) {
			MeetingPerson meetingPerson = (MeetingPerson) totalDao.getObjectByCondition(
					"from MeetingPerson where userId = ? and meeting.id=?", usersId,screenId);
			if(meetingPerson!=null) {
				totalDao.delete(meetingPerson);
			}
			return "success";
		}
		return "error";
	}

	@Override
	public List<MeetingPerson> getPersonListByMeetingId(Integer id) {
		if(id!=null) {
			List<MeetingPerson> list = totalDao.query("from MeetingPerson where meeting.id=?", id);
			return list;
		}
		return null;
	}

	@Override
	public String deleteMeetingById(Integer id) {
		if(id!=null) {
			Meeting meeting = (Meeting) totalDao.getObjectById(Meeting.class, id);
			if(meeting!=null) {
				if(meeting.getEpId()!=null){
					AlertMessages alertMessages = (AlertMessages) totalDao.getObjectByCondition(
							"from AlertMessages where functionUrl=?", "CircuitRunAction_findAduitPage.action?id="+meeting.getEpId());
					if(alertMessages!=null){
						totalDao.delete(alertMessages);
					}
					CircuitRun cancalCir = (CircuitRun) totalDao.getObjectById(CircuitRun.class, meeting.getEpId());
					if(cancalCir!=null) {
						totalDao.delete(cancalCir);
					}
				}
				Set<MeetingPerson> personSet = meeting.getPersonSet();
				if(personSet!=null) {
					for (MeetingPerson meetingPerson : personSet) {
						if(meetingPerson!=null) {
							totalDao.delete(meetingPerson);
						}
					}
				}
				boolean delete = totalDao.delete(meeting);
				if(delete) {
					return "删除成功";
				}else {
					return "删除失败";
				}
				
			}
			return "没有查询到要删除的信息。";
		}
		return "id不能为空";
	}

	
}
