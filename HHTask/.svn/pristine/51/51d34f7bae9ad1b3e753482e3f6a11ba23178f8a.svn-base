package com.task.Server.renshi;

import java.util.List;
import java.util.Map;

import com.task.entity.renshi.Meeting;
import com.task.entity.renshi.MeetingPerson;

public interface MeetingServer {

	String addMeeting(Meeting meeting);
	
	Map<String, Object> findMeetingByCon(Meeting meeting,Integer pageNo,Integer pageSize,String pageStatus);
	
	String addMeetingPerson(Integer meetingId,MeetingPerson person);
	
	String removeMeetingPerson(Integer meetingId,MeetingPerson person);
	
	Meeting getMeetingById(Integer meetingId,String pageStatus);
	
	String bindMeetingUsers(Integer screenId,Integer usersId);
	
	String cancelMeetingUsers(Integer screenId, Integer usersId);
	
	List<MeetingPerson> getPersonListByMeetingId(Integer id);
	
	String deleteMeetingById(Integer id);
}
