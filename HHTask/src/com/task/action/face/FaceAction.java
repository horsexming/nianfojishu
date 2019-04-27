package com.task.action.face;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.face.FaceServer;
import com.task.entity.Users;
import com.task.entity.face.FaceCamera;
import com.task.entity.face.FaceUsers;
import com.task.entity.face.FaceWorkTime;
import com.task.util.MKUtil;
import com.task.util.Util;

/**
 * 人脸识别
 * 
 * @author wcy
 *
 */
public class FaceAction {

	private String ids;
	private FaceServer faceServer;
	private Integer userId;
	private String pageStatus;
	private FaceUsers faceUsers;
	private List<FaceUsers> faceList;

	private FaceCamera faceCamera;
	private List<FaceCamera> cameraList;

	private File picture;
	private String pictureFileName;
	private String pictureContentType;
	private String errorMessage;
	private String url;
	private Integer id;
	private String cpage = "1";
	private Integer pageSize = 15;
	private String total;
	private FaceWorkTime workTime;
	private List<FaceWorkTime> workTimeList;
	private String userCode;
	private String startTime;
	private String endTime;

	public String gotoUsersFaceList() {
		faceList = faceServer.findFaceUsersByUserId(userId, pageStatus);
		return "face_userList";
	}

	public String gotoMultUsersFaceList() {

		return "face_multUserList";
	}

	public String toAddFaceUsers() {
		Map<String, Object> map = faceServer.findFaceCameraByCon(new FaceCamera(), 0, 0, null);
		if(map!=null && map.size()>0) {
			cameraList = (List<FaceCamera>) map.get("list");
			if(pageStatus==null || pageStatus.equals("")) {
				if(cameraList!=null && cameraList.size()>0) {
					pageStatus = "face_"+cameraList.get(0).getId()+".jpg";
				}
			}
		}
		return "face_addUsers";
	}

	/**
	 * 上传图片
	 * 
	 * @return
	 */
	public String uploadFacePicture() {
		// String basePath =ServletActionContext.getServletContext()
		// .getRealPath("/upload/file/face/");
		String fileName = "face_" + Util.getDateTime("yyyyMMddHHmmss") + ".";
		String uploadPath = "/upload/file/face/";
		if (picture != null) {
			String fileType = null;
			String[] names = pictureFileName.split("\\.");
			if (names != null && names.length >= 2) {
				fileType = names[names.length - 1];
			}
			fileName = fileName + fileType;
		} else {
			fileName = fileName + "jpg";
			picture = new File(
					ServletActionContext.getServletContext().getRealPath("/upload/file/dll/face/picture/"+pageStatus));
			pictureFileName = picture.getName();
		}
		String uploadFile = Util.UploadFile(picture, pictureFileName, fileName, uploadPath, null);
		if (fileName.equals(uploadFile)) {
			if (faceUsers == null) {
				faceUsers = new FaceUsers();
			}
			faceUsers.setPicturePath(fileName);
			Users users = faceServer.getUsersById(faceUsers.getUserId());
			if (users != null) {
				faceUsers.setCardId(users.getCardId());
				faceUsers.setCode(users.getCode());
				faceUsers.setDept(users.getDept());
				faceUsers.setGroupName("Inner");
				faceUsers.setUserName(users.getName());
				errorMessage = faceServer.addFaceUsers(faceUsers, pageStatus);
				if (errorMessage != null && errorMessage.equals("添加成功")) {
					setUrl("faceAction!gotoUsersFaceList.action?userId=" + faceUsers.getUserId());
				}
			} else {
				setErrorMessage("用户为空");
			}
			return "error";
			// return "face_pictureUpload";
		} else {
			errorMessage = uploadFile;
			return "error";
		}
	}

	/**
	 * 布防报警返回信息
	 * @return
	 */
	public String callAction() {
		
		return "face_call";
	}
	
	public String gotoPictureSubmit() {
		// faceUsers = new FaceUsers();
		// faceUsers.setPicturePath(null);
		errorMessage = faceServer.addFaceUsers(faceUsers, pageStatus);

		return "error";
	}

	/**
	 * 删除人脸识别用户
	 * 
	 * @return
	 */
	public String deleteFaceUsers() {
		errorMessage = faceServer.deleteFaceUsersById(id);
		setUrl("faceAction!gotoUsersFaceList.action?userId=" + faceUsers.getUserId()+"&pageStatus="+pageStatus);
		return "error";
	}

	/**
	 * 添加摄像头
	 * 
	 * @return
	 */
	public String addFaceCamera() {
		errorMessage = faceServer.addFaceCamera(faceCamera, pageStatus);
		if (errorMessage != null && errorMessage.equals("添加成功")) {
			this.setUrl("faceAction!findFaceCameraByCon.action?pageStatus=" + pageStatus);
		}
		return "error";
	}

	/**
	 * 查询摄像头
	 * 
	 * @return
	 */
	public String findFaceCameraByCon() {
		Map<String, Object> session = ActionContext.getContext().getSession();

		if (faceCamera != null) {
			session.put("findFaceCameraByCon", faceCamera);
		} else {
			faceCamera = (FaceCamera) session.get("findFaceCameraByCon");
		}

		Map<String, Object> map = faceServer.findFaceCameraByCon(faceCamera, Integer.parseInt(cpage), pageSize,
				pageStatus);
		if (map != null) {
			cameraList = (List<FaceCamera>) map.get("list");
			if (cameraList != null && cameraList.size() > 0) {
				Integer count = (Integer) map.get("count");
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("faceAction!findFaceCameraByCon.action?pageStatus=" + pageStatus);
			}
		}

		return "faceCamera_list";
	}

	/**
	 * 前往添加和修改摄像头信息
	 */
	public String toFaceCameraUpdatePage() {
		if (id != null) {
			faceCamera = faceServer.getFaceCameraById(id);
		}
		return "faceCamera_addOrupdate";
	}

	/**
	 * 修改摄像头信息
	 * 
	 * @return
	 */
	public String updateFaceCamera() {
		errorMessage = faceServer.updateFaceCamera(faceCamera, pageStatus);
		if (errorMessage != null && errorMessage.equals("修改成功")) {
			this.setUrl("faceAction!findFaceCameraByCon.action?pageStatus=" + pageStatus);
		}
		return "error";
	}

	/**
	 * 删除摄像头
	 * 
	 * @return
	 */
	public String deleteFaceCamera() {
		errorMessage = faceServer.deleteFaceCamera(id);
		if (errorMessage != null && errorMessage.equals("删除成功")) {
			this.setUrl("faceAction!findFaceCameraByCon.action?pageStatus=" + pageStatus);
		}
		return "error";
	}
	
	public String toWorkTimeLonghz() {
		Map<String, Object> map = faceServer.findWorkTimeLongByCon(workTime, Integer.parseInt(cpage), pageSize, pageStatus);
		if(!map.isEmpty()) {
			workTimeList = (List<FaceWorkTime>) map.get("list");
			if(!workTimeList.isEmpty()) {
				Integer count = (Integer) map.get("count");
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("faceAction!toWorkTimeLongList.action?pageStatus=" + pageStatus);
			}
		}
		
		return "face_workTimeLonghz";
	}
	
	/**
	 * 工作时长分析
	 */
	public String toWorkTimeLongList() {
		Map<String, Object> map = faceServer.findWorkTimeLongByCon(workTime, Integer.parseInt(cpage), pageSize, pageStatus);
		if(!map.isEmpty()) {
			workTimeList = (List<FaceWorkTime>) map.get("list");
			if(workTimeList !=null && !workTimeList.isEmpty()) {
				Integer count = (Integer) map.get("count");
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("faceAction!toWorkTimeLongList.action?pageStatus=" + pageStatus);
			}
			workTime = (FaceWorkTime) map.get("workTime");
		}
		
		return "face_workTimeLongList";
	}
	
	/**
	 * 查看上班时长明细
	 */
	public String toWorkTimeDetail() {
		workTimeList = faceServer.getFaceWorkTimeByIds(ids);
		return "face_workTimeDetail";
	}
	
	/**
	 * 根据工号、开始时间、结束时间查询员工工作时长折线图
	 * @return
	 */
	public String toSearchLineChart() {
		
		return "face_workTimeLineChart";
	}
	/**
	 * 根据工号、开始时间、结束时间查询员工工作时长折线图
	 * @return
	 */
	public void searchLineChart() {
		MKUtil.writeJSON(faceServer.searchLineChart(userCode, startTime, endTime));
	}
	
	public String toAddAlarm() {
		
		return "face_addAlarm";
	}
	
	//添加报警人员
	public String uploadAlarmFacePicture() {
		String fileName = "face_" + Util.getDateTime("yyyyMMddHHmmss") + ".";
		String uploadPath = "/upload/file/face/";
		if (picture != null) {
			String fileType = null;
			String[] names = pictureFileName.split("\\.");
			if (names != null && names.length >= 2) {
				fileType = names[names.length - 1];
			}
			fileName = fileName + fileType;
		}else {
			setErrorMessage("请选择图片");
			return "error";
		}
		String uploadFile = Util.UploadFile(picture, pictureFileName, fileName, uploadPath, null);
		if (fileName.equals(uploadFile)) {
			if (faceUsers == null) {
				faceUsers = new FaceUsers();
			}
			faceUsers.setPicturePath(fileName);
			faceUsers.setGroupName("alarm");
			errorMessage = faceServer.addAlarmFaceUsers(faceUsers, pageStatus);
			if (errorMessage != null && errorMessage.equals("添加成功")) {
				setUrl("faceAction!gotoUsersFaceList.action?pageStatus=alarm");
			}
			return "error";
		} else {
			errorMessage = uploadFile;
			return "error";
		}
	}
	
	/**
	 * 查询报警播报信息
	 */
	public void findSxtFaceAlarm(){
		MKUtil.writeJSON(faceServer.findSxtFaceAlarm(id));
	}
	

	public FaceServer getFaceServer() {
		return faceServer;
	}

	public void setFaceServer(FaceServer faceServer) {
		this.faceServer = faceServer;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public List<FaceUsers> getFaceList() {
		return faceList;
	}

	public void setFaceList(List<FaceUsers> faceList) {
		this.faceList = faceList;
	}

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	public String getPictureContentType() {
		return pictureContentType;
	}

	public void setPictureContentType(String pictureContentType) {
		this.pictureContentType = pictureContentType;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public FaceUsers getFaceUsers() {
		return faceUsers;
	}

	public void setFaceUsers(FaceUsers faceUsers) {
		this.faceUsers = faceUsers;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public FaceCamera getFaceCamera() {
		return faceCamera;
	}
	public void setFaceCamera(FaceCamera faceCamera) {
		this.faceCamera = faceCamera;
	}
	public List<FaceCamera> getCameraList() {
		return cameraList;
	}
	public void setCameraList(List<FaceCamera> cameraList) {
		this.cameraList = cameraList;
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
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public FaceWorkTime getWorkTime() {
		return workTime;
	}
	public void setWorkTime(FaceWorkTime workTime) {
		this.workTime = workTime;
	}
	public List<FaceWorkTime> getWorkTimeList() {
		return workTimeList;
	}
	public void setWorkTimeList(List<FaceWorkTime> workTimeList) {
		this.workTimeList = workTimeList;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}