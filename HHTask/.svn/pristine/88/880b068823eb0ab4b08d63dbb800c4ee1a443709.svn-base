package com.task.ServerImpl.kq;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.task.ServerImpl.AttendanceTowServerImpl;
import com.task.servlet.JDBCUtils;
import com.task.util.Util;

public class HHKQToPEBS extends Thread {

	@SuppressWarnings("static-access")
	public void run() {
		while (true) {
			try {
				String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 加载JDBC驱动
				String dbURL = "jdbc:sqlserver://192.168.10.229:1433;databaseName=AttDB";
				// 连接服务器和数据库sample
				String userName = "sa"; // 默认用户名
				String userPwd = "Aacv1234"; // 密码
				Class.forName(driverName);
				Connection dbConn = DriverManager.getConnection(dbURL,
						userName, userPwd);
//				Connection dbConn = JDBCUtils.getConn();
				Statement statement = dbConn.createStatement();

				/************ 兑取配置文件中记录的最大id ******/
				Properties prop = new Properties();//E:\apache-tomcat-6.0.41\webapps\HHTask
//				String path = "E:\\apache-tomcat-6.0.41\\webapps\\HHTask\\kaoqinjiDuijie2.properties";
				String path = "D:\\PEBS\\apache-tomcat-8.0.30-windows-x64\\apache-tomcat-8.0.30\\webapps\\HHTask\\kaoqinjiDuijie2.properties";
//				D:\PEBS\apache-tomcat-8.0.30-windows-x64\apache-tomcat-8.0.30\webapps\HHTask
				InputStream in = new FileInputStream(path);
				prop.load(in);
				Integer maxId = Integer.parseInt(prop.getProperty("maxId"));
				if (maxId == null) {
					maxId = 0;
				}
				in.close();
				/************ 兑取配置文件中记录的最大id ******/

				// ResultSet rs = statement
				// .executeQuery("select id,userCode,dateTime from uploadForPEBS where uploadStatus ='no' ");
				ResultSet rs = statement
						.executeQuery("SELECT user_id,ids,verify_mode,device_id,io_time FROM tbl_realtime_glog where ids >"
								+ maxId + " ORDER BY ids");
				Integer getCount = 1;
				while (rs.next()) {
					// 获取拦截到的考勤数据
					String userId = rs.getString(1);
					Integer ids = rs.getInt(2);
					String verify_mode = rs.getString(3);//打卡类型
					String device_id = rs.getString(4);//设备编号
					String io_time = rs.getString(5);//打卡时间

//					String dateTime = rs.getString(3);
					// 回传到PEBS考勤系统
					String mess = new AttendanceTowServerImpl()
							.addAttendanceTow(userId, io_time,verify_mode,device_id);
					OutputStream oFile = new FileOutputStream(path);

					// 写入配置文件记录 保存最大id
					prop.setProperty("errorMessage", mess);
					prop.setProperty("maxId", ids+"");
					prop.setProperty("updateTime", Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
					prop.store(oFile, "更新最大id和时间");
					oFile.close();
					getCount = 0;
				}
				if (getCount > 0) {
					// oFile.close();
					// prop.clear();
//					1.// 关闭事务自动提交
//					con.setAutoCommit(true); ==> con.setAutoCommit(false); 
//					2. 最后的pst.executeBatch();前要pst.addBatch();
//					3.con.commit();可以放到最后一句，用一次就行
//					rs.close();
					statement.close();
					dbConn.close();
					sleep(1000 * 10);
				}
			} catch (Exception e) {
				e.printStackTrace();
				try {
					sleep(1000 * 10 * 60);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

}
