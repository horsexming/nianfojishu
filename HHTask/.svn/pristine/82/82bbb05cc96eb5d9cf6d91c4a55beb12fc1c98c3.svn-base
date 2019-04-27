package com.task.ServerImpl.face;

import java.util.ArrayList;
import java.util.List;

import com.task.Dao.TotalDao;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.ServerImpl.AttendanceTowServerImpl;
import com.task.entity.face.FaceCamera;
import com.task.entity.face.FaceUsers;
import com.task.util.Util;

 /**
 * 执行打卡等操作
 * 
 * @author admin
 *
 */
public class FaceRunClock extends Thread {
	private String userCode;
	static List<String> userCodeList = new ArrayList<String>();
	private FaceCamera faceCamera;
	private TotalDao totalDao;

	public FaceRunClock(String userCode) {
		this.userCode = userCode;
	}
	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	public void setFaceCamera(FaceCamera faceCamera) {
		this.faceCamera = faceCamera;
	}

	@Override
	public void run() {
		String dateTime = Util.getDateTime("yyyy-MM-dd HH:mm:ss");
		System.out.println("打卡线程："+Thread.currentThread().getName());
		FaceUsers faceUsers = (FaceUsers) totalDao.getObjectByQuery("from FaceUsers where faceCode = ?", userCode);
		if (faceUsers != null) {
			String accessname = "人脸识别摄像头";// 设备名称
			if (faceCamera != null) {
				accessname = accessname + faceCamera.getName();
			}
			//synchronized (userCodeList) {
				String retuatten = AttendanceTowServerImpl.addAttendanceTow(faceUsers.getCardId(), faceUsers.getCode(),
						faceUsers.getUserName(), faceUsers.getDept(), faceUsers.getUserId(), "指纹", accessname, "考勤机",
						null, totalDao);
				if ("true".equals(retuatten)) {
					String sendmessage = "您与" + dateTime + "打卡成功，祝您工作愉快！";
					// 发送消息提醒
					Integer uids[] = new Integer[1];
					uids[0] = faceUsers.getUserId();
					AlertMessagesServerImpl.addAlertMessages("打卡成功提示", sendmessage, uids, null, true, true,totalDao);
					System.out.println(faceUsers.getUserName() + ",打卡成功");
				} else if ("exists".equals(retuatten)) {
					System.out.println(faceUsers.getUserName() + ",已存在打卡记录");
				} else {
					System.out.println(faceUsers.getUserName() + ",打卡失败" + retuatten);
				}

				if ("true".equals(retuatten) || "exists".equals(retuatten)) {
					// 开门
					// try {
					// Socket s = new Socket("192.168.0.197", 8877);
					// PrintStream p = new PrintStream(s.getOutputStream());
					// p.write(2);
					// p.flush();
					// p.close();
					// s.close();
					// System.out.println("开门了");
					// } catch (UnknownHostException e) {
					// e.printStackTrace();
					// } catch (IOException e) {
					// e.printStackTrace();
					// }
				}
			//}
		}
	}

}