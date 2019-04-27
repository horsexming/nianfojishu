package com.task.ServerImpl.kq;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;

import com.task.ServerImpl.AttendanceTowServerImpl;
import com.task.util.Util;

public class YTKQToPEBSKQ extends Thread {

	@SuppressWarnings("static-access")
	public void run() {
		while (true) {
			try {
				String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 加载JDBC驱动
				String dbURL = "jdbc:sqlserver://192.168.2.93:1433;databaseName=Main_DB";
				// 连接服务器和数据库sample
				String userName = "pebs"; // 默认用户名
				String userPwd = "pebs123456"; // 密码
				Class.forName(driverName);
				Connection dbConn = DriverManager.getConnection(dbURL,
						userName, userPwd);
				Statement statement = dbConn.createStatement();

				/************ 兑取配置文件中记录的最大id ******/
				Properties prop = new Properties();
				String path = "D:\\apache-tomcat-8.0.30-windows-x64-2\\apache-tomcat-8.0.30\\webapps\\HHTask\\kaoqinjiDuijie.properties";
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
						.executeQuery("select id,userCode,dateTime from uploadForPEBS where id>"
								+ maxId + " ");
				Integer getCount = 1;
				while (rs.next()) {
					// 获取拦截到的考勤数据
					Integer id = rs.getInt(1);
					String userCode = rs.getString(2);
					if (userCode != null && userCode.length() > 0) {
						// 开始补齐工号到6位数
						int userCodeLength = userCode.length();
						if (userCodeLength == 1) {
							userCode = "00000" + userCode;
						} else if (userCodeLength == 2) {
							userCode = "0000" + userCode;
						} else if (userCodeLength == 3) {
							userCode = "000" + userCode;
						} else if (userCodeLength == 4) {
							userCode = "00" + userCode;
						} else if (userCodeLength == 5) {
							userCode = "0" + userCode;
						}
					}

					String dateTime = rs.getString(3);
					// 回传到PEBS考勤系统
					String mess = new AttendanceTowServerImpl()
							.addAttendanceTow(userCode, dateTime);
					OutputStream oFile = new FileOutputStream(path);

					// 写入配置文件记录 保存最大id
					prop.setProperty("errorMessage", mess);
					prop.setProperty("maxId", id + "");
					prop.setProperty("updateTime", Util.getDateTime());
					prop.store(oFile, "更新最大id和时间");
					oFile.close();
					// if ("true".equals(mess)) {
					// // 更新拦截考勤数据状态 sql
					// // Statement statement3 = dbConn.createStatement();
					// // statement3
					// //
					// .executeUpdate("update uploadForPEBS set uploadStatus ='yes'  where id="
					// // + id + "");
					//
					// // 写入配置文件记录 保存最大id
					// prop.setProperty("maxId", id + "");
					// prop.setProperty("updateTime", Util.getDateTime());
					// prop.store(oFile, "更新最大id和时间");
					// oFile.close();
					// } else {
					// prop.store(oFile, "更新最大id和时间");
					// oFile.close();
					// break;
					// }
					getCount = 0;
				}
				if (getCount > 0) {
					// oFile.close();
					// prop.clear();
					rs.close();
					statement.close();
					dbConn.close();
					sleep(1000 * 60 * 30);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
