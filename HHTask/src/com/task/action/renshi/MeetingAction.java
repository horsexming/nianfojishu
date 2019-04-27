package com.task.action.renshi;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.renshi.MeetingServer;
import com.task.entity.renshi.Meeting;
import com.task.entity.renshi.MeetingPerson;
import com.task.util.MKUtil;

public class MeetingAction {

	private Integer id;
	
	private String pageStatus;
	private String cpage="1";
	private Integer pageSize = 15;
	private String url;
	private String total;
	private String tag;
	private String errorMessage;
	private Integer usersId;
	private MeetingServer meetingServer;
	private Meeting meeting;
	private List<Meeting> meetingList;
	private List<MeetingPerson> personList;
	
	
	public String findMeetingByCon() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if(meeting!=null) {
			session.put("findMeetingByCon", meeting);
		}else {
			meeting =(Meeting) session.get("findMeetingByCon");
		}
		Map<String, Object> map = meetingServer.findMeetingByCon(meeting,Integer.parseInt(cpage), pageSize, pageStatus);
		if(map!=null&& map.size()>0) {
			meetingList = (List<Meeting>) map.get("list");
			Integer count = (Integer) map.get("count");
			setUrl("meetingAction!findMeetingByCon.action?pageStatus="+pageStatus+"&tag="+tag);
			Integer pageCount=(count + pageSize - 1) / pageSize;
			setTotal(pageCount+"");
		}
		return "meeting_list";
	}
	
	
	
	public String toAddMeeting() {
		return "meeting_add";
	}
	
	public String toUpdateMeeting() {
		meeting = meetingServer.getMeetingById(id, pageStatus);
		return "meeting_add";
	}
	public String toBindUsers() {
		personList = meetingServer.getPersonListByMeetingId(id);
		return "meeting_bindUsers";
	}
	
	/**
	 * 绑定
	 */
	public void bindMeetingUsers() {
		errorMessage = meetingServer.bindMeetingUsers(id, usersId);
		MKUtil.writeJSON(errorMessage);
	}
	
	/**
	 * 取消绑定
	 */
	public void cancelMeetingUsers() {
		errorMessage = meetingServer.cancelMeetingUsers(id, usersId);
		MKUtil.writeJSON(errorMessage);
	}
	
	public String addMeeting() {
		try {
			errorMessage = meetingServer.addMeeting(meeting);
			setUrl("meetingAction!findMeetingByCon.action?pageStatus="+pageStatus+"&tag="+tag);
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = e.toString();
		}

		return "error";
	}
	
	public String deleteMeeting() {
		errorMessage = meetingServer.deleteMeetingById(id);
		setUrl("meetingAction!findMeetingByCon.action?pageStatus="+pageStatus+"&tag="+tag);
		return "error";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPageStatus() {
		return pageStatus;
	}
	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}
	public String getCpage() {
		return cpage;
	}
	public void setCpage(String cpage) {
		this.cpage = cpage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Integer getUsersId() {
		return usersId;
	}
	public void setUsersId(Integer usersId) {
		this.usersId = usersId;
	}
	public MeetingServer getMeetingServer() {
		return meetingServer;
	}
	public void setMeetingServer(MeetingServer meetingServer) {
		this.meetingServer = meetingServer;
	}
	public Meeting getMeeting() {
		return meeting;
	}
	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}
	public List<MeetingPerson> getPersonList() {
		return personList;
	}
	public void setPersonList(List<MeetingPerson> personList) {
		this.personList = personList;
	}



	public List<Meeting> getMeetingList() {
		return meetingList;
	}



	public void setMeetingList(List<Meeting> meetingList) {
		this.meetingList = meetingList;
	}
	
	

}
