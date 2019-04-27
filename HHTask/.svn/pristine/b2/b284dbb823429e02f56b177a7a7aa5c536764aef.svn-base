package com.huawei.openapi.openaipexample.client.http;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.task.Dao.TotalDao;
import com.task.DaoImpl.TotalDaoImpl;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.entity.TbBarcode.TbBarcodeLockNotice;
import com.task.util.Util;

public class RwlBarcodeWebService extends Thread {
	public static void main(String[] args) {
		// GetBarcodeInfo("030LKT10A6000028");
		// Get_Info_Frmbarcode_EMSBarcodeLock("030LKT10A6000028");
		// Get_Info_Frmbarcode_EMSBarcodeUnLock("", "");
		RwlBarcodeWebService rb = new RwlBarcodeWebService();
		// rb.findAllTbBarcodeLockList(null, 1, 15);
		rb.Get_Info_Frmbarcode_EMSBarcodeLock("2101073872ESJ7000062", null, null);
	}

	private static Connection dbConn;

	// private static Statement statement;

	// private static ResultSet rs;

	public RwlBarcodeWebService() {
		if (dbConn == null) {
			try {
				String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 加载JDBC驱动
				String dbURL = "jdbc:sqlserver://192.168.2.37:1433;databaseName=yt_BarcodeTrace";
				// 连接服务器和数据库sample
				String userName = "tiaomao"; // 默认用户名
				String userPwd = "a123456"; // 密码
				Class.forName(driverName);
				dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
				// statement = dbConn.createStatement();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void run() {
		try {
			while (true) {
				Statement statement = dbConn.createStatement();
				ResultSet rs = statement
						.executeQuery("select snBarCode from Pebs_HW_BTS_SnBarcode where isFind ='no'");
				String snBarCode = "";
				try {
					while (rs.next()) {
						// // 获取SN码
						snBarCode = rs.getString(1);
						// System.out.println(snBarCode);
						// // 调用华为接口查询
						boolean bool = Get_Info_Frmbarcode_EMSBarcodeLock(
								snBarCode, null, null);
						if (bool) {
							Statement statement2 = dbConn.createStatement();
							// 抛转信息
							ResultSet rs2 = statement2
									.executeQuery("select segment5 from uploadHwRelationHistory where son_bar_code='"
											+ snBarCode + "'");
							Integer check_message = 0;
							while (rs2.next()) {
								check_message = rs2.getInt(1);
							}
							if (check_message==0) {
								// 抛转成功后，更新SN条码状态为已查询
								Statement statement3 = dbConn.createStatement();
								statement3
										.executeUpdate("update Pebs_HW_BTS_SnBarcode set isFind ='yes'  where snBarCode='"
												+ snBarCode + "'");
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			// dbConn.close();
			// GetNoFindSnBarcode();// 循环调用
		} catch (Exception e) {
			e.printStackTrace();
			// GetNoFindSnBarcode();// 循环调用
		}
	}

	public static boolean Get_Info_Frmbarcode_EMSBarcodeLock(String tosn,
			String unLockReason, Boolean autolock) {
		if (unLockReason == null) {
			unLockReason = "";
		}
		URLConnection conn = null;
		InputStream is = null;
		try {
			// if (tosn == null || tosn == "") {
			// tosn = "030LKT10A6000028";
			// }

			/**** Get_Info_Frmbarcod ****/
			String sImport = "&lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;"
					+ "&lt;EMSBarcodeLock&gt;"
					+ "&lt;Import&gt;"
					+ "&lt;SN&gt;"
					+ tosn
					+ ";&lt;/SN&gt;"
					+ "&lt;VendorID&gt;62282&lt;/VendorID&gt;"
					+ "&lt;ProcNo&gt;noProcNo&lt;/ProcNo&gt;"
					+ "&lt;/Import&gt;" + "&lt;/EMSBarcodeLock&gt;";
			// sImport=sImport.replaceAll("<", "");
			// sImport=sImport.replaceAll("<", "");
			String soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
					+ "<soap:Body>"
					+ "<Get_Info_Frmbarcode xmlns=\"http://Auto.huawei.com.cn/\">"
					+ "<sTaskType>EMSBarcodeLock</sTaskType>" + "<sImport>"
					+ sImport + "</sImport>" + "</Get_Info_Frmbarcode>"
					+ "</soap:Body>" + "</soap:Envelope>";
			URL url = new URL(
					"http://cmes-ems01.huawei.com/hw_manufacture/MidLayer.asmx");
			conn = url.openConnection();
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Length", Integer.toString(soap
					.length()));
			conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			conn.setRequestProperty("HOST", "cmes-ems01.huawei.com");
			conn.setRequestProperty("SOAPAction",
					"\"http://Auto.huawei.com.cn/Get_Info_Frmbarcode\"");

			OutputStream os = conn.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
			osw.write(soap);
			osw.flush();
			osw.close();
			String result = "-12";
			is = conn.getInputStream();

			Document doc;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			if (is != null) {
				doc = db.parse(is);
				NodeList nl = doc.getElementsByTagName("sExport");
				Node n = nl.item(0);
				result = n.getFirstChild().getNodeValue();
				is.close();
			}
			// System.out.println(result);
			// 解析得到的XML
			StringReader sr = new StringReader(result);
			InputSource is_out = new InputSource(sr);
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc_out = builder.parse(is_out);

			// System.out.println("ErrorCode: "
			// + doc_out.getElementsByTagName("ErrorCode").item(0)
			// .getFirstChild().getNodeValue()
			// + " ===(0表示查询成功/1表示查询失败)");

			// System.out.println("ErrorMsg:"
			// + doc_out.getElementsByTagName("ErrorMsg").item(0)
			// .getFirstChild().getNodeValue());
			//
			NodeList nl_out3 = doc_out.getElementsByTagName("LineRow");

			/************* 创建数据库链接 ********************/
			// Statement statement;
			// ResultSet rs;
			// String driverName =
			// "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 加载JDBC驱动
			// String dbURL =
			// "jdbc:sqlserver://192.168.2.37:1433;databaseName=yt_BarcodeTrace";
			// // 连接服务器和数据库sample
			// String userName = "tiaomao"; // 默认用户名
			// String userPwd = "a123456"; // 密码
			// Class.forName(driverName);
			// Connection dbConn = DriverManager.getConnection(dbURL, userName,
			// userPwd);
			// statement = dbConn.createStatement();
			/************* 创建数据库链接完成 ********************/
			for (int i = 0, len = nl_out3.getLength(); i < len; i++) {
				Node n_out3 = nl_out3.item(i);
				NamedNodeMap nnm = n_out3.getAttributes();
				String sn = nnm.getNamedItem("SN").getFirstChild()
						.getNodeValue();
				String isLocked = nnm.getNamedItem("IsLocked").getFirstChild()
						.getNodeValue();
				String hw_lockNO = nnm.getNamedItem("LockNO").getFirstChild()
						.getNodeValue();
				if (hw_lockNO == null) {
					hw_lockNO = "";
				}
				// isLocked = "1";
				// hw_lockNO = "test6;test7;";

				// System.out.println("序号:" + (i + 1));
				System.out.println("SN(条码):" + sn);
				System.out.println("IsLocked: " + isLocked
						+ " ===(是否隔离：0：未隔离/1：已隔离/-1:未找到该条码的隔离记录)");
				System.out.println("LockNO(隔离单号):" + hw_lockNO);

				/******* 开始插入BTS条码锁定记录表的逻辑处理 ********/
				if (!"-1".equals(isLocked)) {
					String hw_fLocked = "Y";
					if ("0".equals(isLocked)) {
						hw_fLocked = "N";
					}
					if (autolock == null) {
						autolock = false;// 自动锁定
					}
					/********* 1、先查询BTS中是否已经存在该隔离条码和隔离单号 ******/
					Statement statement4 = dbConn.createStatement();
					ResultSet rs = statement4
							.executeQuery("select FLocked,LockNO from TbBarcodeLockList "
									+ "where FBarcode ='" + sn + "'");
					String bts_FLoked = "";
					String bts_lockNO = "";
					while (rs.next()) {
						bts_FLoked = rs.getString(1);
						bts_lockNO = rs.getString(2);
						if (bts_lockNO == null) {
							bts_lockNO = "";
						}
					}
					String bts_FOpReason = "";
					String resume_isLocked = "锁定";
					if ("0".equals(isLocked)) {
						resume_isLocked = "解锁";
					}
					int sql_result_count = 0;
					String FLockUser = "PEBS系统";
					if (Util.getLoginUser() != null) {
						FLockUser = Util.getLoginUser().getName();
					}
					if (bts_FLoked != null && bts_FLoked.length() > 0) {
						// 更新条码状态
						String sql_update = "";
						if ("Y".equals(hw_fLocked)) {
							/*** 判断隔离单号增加或减少 ***/
							// hw_lockNO>bts_lockNO:隔离单号增加，则表示该条码新增了锁定隔离单号
							// hw_lockNO<bts_lockNO:隔离单号减少，则表示该条码解锁了减少的隔离单号
							if (hw_lockNO.length() > bts_lockNO.length()) {
								if (!autolock && "N".equals(bts_FLoked)) {
									hw_fLocked = "N";
								}
								// 筛选出增加的隔离单号是什么
								String[] a = hw_lockNO.split(";");
								String[] b = bts_lockNO.split(";");
								for (int j = 0; j < a.length; j++) {
									for (int k = 0; k < b.length; k++) {
										if (a[j].equals(b[k])) {
											a[j] = null;
											b[k] = null;
											break;
										}
									}
								}
								for (int j = 0; j < a.length; j++) {
									if (a[j] != null) {
										bts_FOpReason += a[j].toString();
									}
								}
								String FUnLockReason = "";
								if ("N".equals(bts_FLoked)) {
									FUnLockReason = "FUnLockReason='',";
								}
								sql_update = "UPDATE TbBarcodeLockList "
										+ "SET FLocked = '" + hw_fLocked
										+ "' ,FLockReason='" + hw_lockNO
										+ " 华为接口锁定'," + FUnLockReason
										+ "FLockUser='" + FLockUser + "'"
										+ ",FLockDate=getdate(),LockNo='"
										+ hw_lockNO + "' WHERE FBarcode ='"
										+ sn + "'";

								// 锁定原因
								bts_FOpReason += " 华为接口锁定";
								// //////////增加鎖定單
								String item = GetBarcodeInfo(sn);// 华为接口提供条码明细接口
								TotalDao totaldao = TotalDaoImpl.findTotalDao();
								TbBarcodeLockNotice tbln = new TbBarcodeLockNotice();
								tbln.setMatCode(item);// 料号
								tbln.setMatName("");// 名称
								tbln.setBarcode(sn);// 条码
								tbln.setLocked("Y");// 锁定状态
								tbln.setLockReason(hw_lockNO + " 华为接口锁定");// 锁定原因
								tbln.setLockUser(FLockUser);// 锁定人
								tbln.setLockDate(Util.getDateTime());// 锁定时间
								tbln.setLockNo(hw_lockNO);// 隔离单号
								totaldao.save(tbln);
								AlertMessagesServerImpl.addAlertMessages(
										"条码锁定通知单", "有新的条码锁定请及时处理", "条码锁定通知");

							} else if (hw_lockNO.length() < bts_lockNO.length()) {
								resume_isLocked = "解锁";
								// 筛选出减少的隔离单号是什么
								String[] a = bts_lockNO.split(";");
								String[] b = hw_lockNO.split(";");
								for (int j = 0; j < a.length; j++) {
									for (int k = 0; k < b.length; k++) {
										if (a[j].equals(b[k])) {
											a[j] = null;
											b[k] = null;
											break;
										}
									}
								}
								for (int j = 0; j < a.length; j++) {
									if (a[j] != null) {
										bts_FOpReason += a[j].toString();
									}
								}

								sql_update = "UPDATE TbBarcodeLockList  SET "
										+ "FLocked = '" + hw_fLocked
										+ "',FLockReason='" + hw_lockNO
										+ " 华为接口锁定',FunLockReason='"
										+ bts_FOpReason + " 华为接口解锁("
										+ unLockReason + ")',FLockUser='"
										+ FLockUser + "'"
										+ ",FLockDate=getdate(),LockNo='"
										+ hw_lockNO + "' WHERE FBarcode ='"
										+ sn + "'";
								bts_FOpReason += " 华为接口解锁(" + unLockReason
										+ ")";
							} else {
								continue;
							}
						} else if ("N".equals(hw_fLocked)
								&& !"N".equals(bts_FLoked)) {
							resume_isLocked = "解锁";
							bts_FOpReason = bts_lockNO + " 华为接口解锁("
									+ unLockReason + ")";
							// 条码被解锁，基本上隔离单只剩下一个，则直接解锁
							sql_update = "UPDATE TbBarcodeLockList SET "
									+ "FLocked = '" + hw_fLocked + "',"
									+ "FunLockReason='" + bts_lockNO
									+ " 华为接口解锁(" + unLockReason + ")',"
									+ "FLockUser='" + FLockUser + "',"
									+ "FLockDate=getdate(),LockNo='"
									+ hw_lockNO + "'  WHERE FBarcode ='" + sn
									+ "'";
							AlertMessagesServerImpl.addAlertMessages("条码锁定通知单",
									sn+",华为已经解锁,请及时处理", "条码解锁提醒");
						}
						Statement statement5 = dbConn.createStatement();
						sql_result_count = statement5.executeUpdate(sql_update);
					} else if ("Y".equals(hw_fLocked)) {
						if (!autolock) {
							hw_fLocked = "N";
						}
						// 添加新
						// insert into TbBarcodeLockList
						// values('','','','','','','PEBS',getdate())

						bts_FOpReason = hw_lockNO;
						String sql_insert = "insert into TbBarcodeLockList  values(?,?,?,?,?,?,?,?,?)";
						PreparedStatement pstmt;
						pstmt = (PreparedStatement) dbConn
								.prepareStatement(sql_insert);
						pstmt.setString(1, sn);// sn码
						String item = GetBarcodeInfo(sn);// 华为接口提供条码明细接口
						pstmt.setString(2, item);// 料号考虑从BTS获取
						pstmt.setString(3, "");// 名称考虑从BTS获取
						pstmt.setString(4, hw_fLocked);// 锁定状态(Y/N)
						pstmt
								.setString(5, hw_lockNO + " 华为接口锁定"
										+ unLockReason);// 锁定原因
						pstmt.setString(6, "");// 解锁
						pstmt.setString(7, FLockUser);// 锁定人
						pstmt.setString(8, Util.getDateTime());// 锁定时间
						pstmt.setString(9, hw_lockNO);// 隔离单号
						sql_result_count = pstmt.executeUpdate();
						pstmt.close();
						bts_FOpReason = hw_lockNO + " 华为接口" + resume_isLocked;

						// 条码锁定通知单 （通知相关负责人-->上传解决结果-->解锁-->关闭）
						TotalDao totaldao = TotalDaoImpl.findTotalDao();
						TbBarcodeLockNotice tbln = new TbBarcodeLockNotice();
						tbln.setMatCode(item);// 料号
						tbln.setBarcode(sn);// 条码
						tbln.setMatName("");// 名称
						tbln.setLocked("Y");// 锁定状态
						tbln.setLockReason(hw_lockNO + " 华为接口锁定");// 锁定原因
						tbln.setLockUser(FLockUser);// 锁定人
						tbln.setLockDate(Util.getDateTime());// 锁定时间
						tbln.setLockNo(hw_lockNO);// 隔离单号
						totaldao.save(tbln);
						AlertMessagesServerImpl.addAlertMessages("条码锁定通知单",
								"有新的条码锁定请及时处理", "条码锁定通知");
					} else {
						return false;
					}
					if (sql_result_count > 0) {
						System.out.println("更新/新增 成功!开始添加履历表");
						// 添加锁定履历
						String sql_insert = "insert into TbBarcodeLockListResume  values(?,?,?,?,?)";
						PreparedStatement pstmt;
						pstmt = (PreparedStatement) dbConn
								.prepareStatement(sql_insert);
						pstmt.setString(1, sn);// sn码
						pstmt.setString(2, resume_isLocked);// 锁定状态
						pstmt.setString(3, bts_FOpReason);// 添加原因
						pstmt.setString(4, FLockUser);// 添加人
						pstmt.setString(5, Util.getDateTime());// 添加时间
						sql_result_count = pstmt.executeUpdate();
						pstmt.close();
						// if (sql_result_count > 0)
						// System.out.println("添加条码履历表成功!");
						// else
						// System.out.println("添加条码履历表失败!");
					}
				} else {
					return false;
				}
			}
			// statement.close();
			// dbConn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("SmsSoap.InputStream error:" + e.getMessage());
			return false;
		}
	}

	public static String Get_Info_Frmbarcode_EMSBarcodeUnLock(String tosn,
			String toLockNO, String unLockReason) {
		URLConnection conn = null;
		InputStream is = null;
		try {
			// if (tosn == null || tosn == "") {
			// tosn = "030LKT10A6000028";
			// }
			/**** Get_Info_Frmbarcod ****/
			String sImport = "&lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;"
					+ "&lt;EMSBarcodeUnLock&gt;"
					+ "&lt;Import&gt;"
					+ "&lt;SN&gt;"
					+ tosn
					+ ";&lt;/SN&gt;"
					+ "&lt;LockNO&gt;"
					+ toLockNO
					+ "&lt;/LockNO&gt;"
					+ "&lt;VendorId&gt;62282&lt;/VendorId&gt;"
					+ "&lt;/Import&gt;" + "&lt;/EMSBarcodeUnLock&gt;";
			String soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
					+ "<soap:Body>"
					+ "<Get_Info_Frmbarcode xmlns=\"http://Auto.huawei.com.cn/\">"
					+ "<sTaskType>EMSBarcodeUnLock</sTaskType>" + "<sImport>"
					+ sImport + "</sImport>" + "</Get_Info_Frmbarcode>"
					+ "</soap:Body>" + "</soap:Envelope>";

			URL url = new URL(
					"http://cmes-ems01.huawei.com/hw_manufacture/MidLayer.asmx");
			conn = url.openConnection();
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Length", Integer.toString(soap
					.length()));
			conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			conn.setRequestProperty("HOST", "cmes-ems01.huawei.com");
			conn.setRequestProperty("SOAPAction",
					"\"http://Auto.huawei.com.cn/Get_Info_Frmbarcode\"");

			OutputStream os = conn.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
			osw.write(soap);
			osw.flush();
			osw.close();
			String result = "-12";
			is = conn.getInputStream();

			Document doc;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			if (is != null) {
				doc = db.parse(is);
				NodeList nl = doc.getElementsByTagName("sExport");
				Node n = nl.item(0);
				result = n.getFirstChild().getNodeValue();
				is.close();
			}
			System.out.println(result);
			// 解析得到的XML
			StringReader sr = new StringReader(result);
			InputSource is_out = new InputSource(sr);
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc_out = builder.parse(is_out);

			System.out.println("ErrorCode: "
					+ doc_out.getElementsByTagName("ErrorCode").item(0)
							.getFirstChild().getNodeValue()
					+ " ===(0表示查询成功/1表示查询失败)");

			System.out.println("ErrorMsg:"
					+ doc_out.getElementsByTagName("ErrorMsg").item(0)
							.getFirstChild().getNodeValue());

			NodeList nl_out3 = doc_out.getElementsByTagName("LineRow");
			for (int i = 0, len = nl_out3.getLength(); i < len; i++) {
				Node n_out3 = nl_out3.item(i);
				NamedNodeMap nnm = n_out3.getAttributes();
				String sn = nnm.getNamedItem("SN").getFirstChild()
						.getNodeValue();
				String lockNO = nnm.getNamedItem("LockNO").getFirstChild()
						.getNodeValue();
				String lockResult = nnm.getNamedItem("Result").getFirstChild()
						.getNodeValue();
				String remark = nnm.getNamedItem("Remark").getFirstChild()
						.getNodeValue();

				System.out.println("序号:" + (i + 1));
				System.out.println("SN(条码):" + sn);
				System.out.println("LockNO(隔离单号):" + lockNO);
				System.out
						.println("Result: "
								+ lockResult
								+ " ===(解锁结果：0:解锁成功/1:解锁异常/3:未找到该条码的隔离记录/4:该条码有多个子条码、父条码被隔离/其它:失败)");
				System.out.println("Remark: " + remark);
				String mess = "";
				if ("0".equals(lockResult)) {
					Get_Info_Frmbarcode_EMSBarcodeLock(tosn, unLockReason, null);
					mess = "解锁成功!";
				} else if ("1".equals(lockResult)) {
					mess = "解锁失败,解锁异常!";
				} else if ("3".equals(lockResult)) {
					mess = "解锁失败,未找到该条码的隔离记录!";
				} else if ("4".equals(lockResult)) {
					mess = "解锁失败,该条码有多个子条码、父条码被隔离!";
				} else {
					mess = "解锁失败!";
				}
				return mess;
			}
		} catch (Exception e) {
			System.out.print("SmsSoap.InputStream error:" + e.getMessage());
		}
		return "数据异常!";
	}

	public static String GetBarcodeInfo(String tosn) {
		URLConnection conn = null;
		InputStream is = null;
		try {
			if (tosn == null || tosn == "") {
				tosn = "030LKT10A6000028";
			}
			String soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
					+ "<soap:Body>"
					+ "<GetBarcodeInfo xmlns=\"http://Auto.huawei.com.cn/\">"
					+ "<sBarcode>" + tosn + "</sBarcode>" + "</GetBarcodeInfo>"
					+ "</soap:Body>" + "</soap:Envelope>";

			URL url = new URL(
					"http://cmes-ems01.huawei.com/hw_manufacture/MidLayer.asmx");
			conn = url.openConnection();
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Length", Integer.toString(soap
					.length()));
			conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			conn.setRequestProperty("HOST", "cmes-ems01.huawei.com");
			conn.setRequestProperty("SOAPAction",
					"\"http://Auto.huawei.com.cn/GetBarcodeInfo\"");

			OutputStream os = conn.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
			osw.write(soap);
			osw.flush();
			osw.close();
			String result = "-12";
			is = conn.getInputStream();

			Document doc;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			if (is != null) {
				doc = db.parse(is);
				String sItem = doc.getElementsByTagName("sItem").item(0)
						.getFirstChild().getNodeValue();
				String sSN = doc.getElementsByTagName("sSN").item(0)
						.getFirstChild().getNodeValue();
				String sRev = "";
				try {
					sRev = doc.getElementsByTagName("sRev").item(0)
							.getFirstChild().getNodeValue();
				} catch (Exception e) {
					e.printStackTrace();
				}
				String iFlag = doc.getElementsByTagName("iFlag").item(0)
						.getFirstChild().getNodeValue();
				String strMsg = doc.getElementsByTagName("strMsg").item(0)
						.getFirstChild().getNodeValue();
				is.close();
				System.out.println(sItem);
				System.out.println(sSN);
				System.out.println(sRev);
				System.out.println(iFlag);
				System.out.println(strMsg);
				return sItem;
			}
		} catch (Exception e) {
			System.out.print("SmsSoap.InputStream error:" + e.getMessage());
		}
		return null;
	}

	public Object[] findAllTbBarcodeLockList(TbBarcodeLock tbBarcodeLock,
			int pageNo, int pageSize) {
		List<TbBarcodeLock> list = new ArrayList<TbBarcodeLock>();
		try {
			String sql = "select top "
					+ pageSize
					+ " * from TbBarcodeLockList where FBarcode not in (select top "
					+ (pageNo - 1) * pageSize
					+ " FBarcode from TbBarcodeLockList where 1=1";
			String sql2 = "";
			if (tbBarcodeLock != null) {
				if (tbBarcodeLock.getFBarcode() != null
						&& tbBarcodeLock.getFBarcode().length() > 0) {
					sql2 += " and fbarcode='" + tbBarcodeLock.getFBarcode()
							+ "'";
				}
				if (tbBarcodeLock.getFLocked() != null
						&& tbBarcodeLock.getFLocked().length() > 0) {
					sql2 += " and FLocked='" + tbBarcodeLock.getFLocked() + "'";
				}
				if (tbBarcodeLock.getLockNo() != null
						&& tbBarcodeLock.getLockNo().length() > 0) {
					sql2 += " and lockNo='" + tbBarcodeLock.getLockNo() + "'";
				}
				if (tbBarcodeLock.getFMatName() != null
						&& "yes".equals(tbBarcodeLock.getFMatName())) {
					sql2 += " and lockNo is not null";
				}
				if (tbBarcodeLock.getFMatCode() != null
						&& tbBarcodeLock.getFMatCode().length() > 0) {
					sql2 += " and FBarcode in ("
							+ "select fmatbarcode from tbAsm_mats where FasmproduceId in ("
							+ "select FasmproduceId from  tbAsm_products where FpdBarcode='"
							+ tbBarcodeLock.getFMatCode() + "'))";
				}
			}

			sql += sql2 + " order by FLockDate desc)" + sql2
					+ " order by FLockDate desc";
			Statement statement6 = dbConn.createStatement();
			ResultSet rs = statement6.executeQuery(sql);
			while (rs.next()) {
				TbBarcodeLock tbLock = new TbBarcodeLock(rs.getString(1), rs
						.getString(2), rs.getString(3), rs.getString(4), rs
						.getString(5), rs.getString(6), rs.getString(7), rs
						.getString(8), rs.getString(9));
				list.add(tbLock);
			}
			String sql_count = "select count(*) from TbBarcodeLockList where 1=1"
					+ sql2;
			Statement statement7 = dbConn.createStatement();
			ResultSet rs2 = statement7.executeQuery(sql_count);
			Integer count = 0;
			while (rs2.next()) {
				count = rs2.getInt(1);
			}
			return new Object[] { list, count };
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * 手动增加条码锁定
	 * 
	 * @param tbBarcodeLock
	 * @return
	 */
	public String sdAddFLock(TbBarcodeLock tbBarcodeLock) {
		try {
			if (tbBarcodeLock != null && tbBarcodeLock.getFBarcode() != null
					&& tbBarcodeLock.getFBarcode().length() > 0) {
				String sql = "update TbBarcodeLockList set FLocked='"
						+ tbBarcodeLock.getFLocked() + "' where Fbarcode='"
						+ tbBarcodeLock.getFBarcode() + "' ";
				Statement statement7 = dbConn.createStatement();
				int cont = statement7.executeUpdate(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/****
	 * 手动锁定 查询华为系统
	 * 
	 * @param tbBarcodeLock
	 * @return
	 */
	public String sdLockForHw(TbBarcodeLock tbBarcodeLock) {
		if (tbBarcodeLock != null) {
			tbBarcodeLock.setFLocked("Y");
			boolean bool = Get_Info_Frmbarcode_EMSBarcodeLock(tbBarcodeLock
					.getFBarcode(), tbBarcodeLock.getFUnlockReason(),true);
			if (!bool) {
				sdUnFLock(tbBarcodeLock);
			}
			return "添加条码锁定完成!";
		} else {
			return "添加条码锁定失败!请刷新后重试!";
		}
	}

	/***
	 * 手动解锁/手动锁定
	 * 
	 * @param tbBarcodeLock
	 * @return
	 */
	public String sdUnFLock(TbBarcodeLock tbBarcodeLock) {
		try {
			if (tbBarcodeLock != null && tbBarcodeLock.getFBarcode() != null
					&& tbBarcodeLock.getFBarcode().length() > 0) {
				String sq_fLocked = tbBarcodeLock.getFLocked();// 申请锁定状态
				/********* 1、先查询BTS中是否已经存在该隔离条码 ******/
				Statement statement4 = dbConn.createStatement();
				ResultSet rs = statement4
						.executeQuery("select FLocked,LockNO from TbBarcodeLockList "
								+ "where FBarcode ='"
								+ tbBarcodeLock.getFBarcode() + "'");
				String bts_FLoked = "";
				String bts_lockNO = "";
				while (rs.next()) {
					bts_FLoked = rs.getString(1);
					bts_lockNO = rs.getString(2);
					if (bts_lockNO == null) {
						bts_lockNO = "";
					}
				}
				String bts_FOpReason = "";
				String resume_isLocked = "锁定";
				if ("N".equals(tbBarcodeLock.getFLocked())) {
					resume_isLocked = "解锁";
				}
				int sql_result_count = 0;
				String FLockUser = "PEBS系统";
				if (Util.getLoginUser() != null) {
					FLockUser = Util.getLoginUser().getName();
				}
				String lockmes = "";
				if (bts_FLoked != null && bts_FLoked.length() > 0) {
					// 更新条码状态
					String sql_update = "";
					if ("Y".equals(sq_fLocked) && !"Y".equals(bts_FLoked)) {
						sql_update = "UPDATE TbBarcodeLockList  SET "
								+ "FLocked = '"
								+ sq_fLocked
								+ "',FLockReason='"
								+ tbBarcodeLock.getFUnlockReason()
								+ "',FunLockReason='',FLockUser='"
								+ FLockUser
								+ "'"
								+ ",FLockDate=getdate(),LockNo='' WHERE FBarcode ='"
								+ tbBarcodeLock.getFBarcode() + "'";
						bts_FOpReason += tbBarcodeLock.getFUnlockReason();
						lockmes = "锁定成功!";
					} else if ("N".equals(sq_fLocked)
							&& !"N".equals(bts_FLoked)) {
						resume_isLocked = "解锁";
						bts_FOpReason = tbBarcodeLock.getFUnlockReason();
						sql_update = "UPDATE TbBarcodeLockList SET "
								+ "FLocked = '"
								+ sq_fLocked
								+ "',"
								+ "FunLockReason='"
								+ tbBarcodeLock.getFUnlockReason()
								+ "',"
								+ "FLockUser='"
								+ FLockUser
								+ "',"
								+ "FLockDate=getdate(),LockNo=''  WHERE FBarcode ='"
								+ tbBarcodeLock.getFBarcode() + "'";
						lockmes = "解锁成功!";
					}
					Statement statement5 = dbConn.createStatement();
					sql_result_count = statement5.executeUpdate(sql_update);
				} else {
					// 添加新
					// insert into TbBarcodeLockList
					// values('','','','','','','PEBS',getdate())
					bts_FOpReason = tbBarcodeLock.getFUnlockReason();
					String sql_insert = "insert into TbBarcodeLockList  values(?,?,?,?,?,?,?,?,?)";
					PreparedStatement pstmt;
					pstmt = (PreparedStatement) dbConn
							.prepareStatement(sql_insert);
					pstmt.setString(1, tbBarcodeLock.getFBarcode());// sn码
					pstmt.setString(2, "");// 料号考虑从BTS获取
					pstmt.setString(3, "");// 名称考虑从BTS获取
					pstmt.setString(4, sq_fLocked);// 锁定状态(Y/N)
					pstmt.setString(5, tbBarcodeLock.getFUnlockReason());// 锁定原因
					pstmt.setString(6, "");// 解锁原因
					pstmt.setString(7, FLockUser);// 锁定人
					pstmt.setString(8, Util.getDateTime());// 锁定时间
					pstmt.setString(9, "");// 隔离单号
					sql_result_count = pstmt.executeUpdate();
					pstmt.close();
					lockmes = "添加锁定成功!";
				}
				if (sql_result_count > 0) {
					System.out.println("更新/新增 成功!开始添加履历表");
					// 添加锁定履历
					String sql_insert = "insert into TbBarcodeLockListResume  values(?,?,?,?,?)";
					PreparedStatement pstmt;
					pstmt = (PreparedStatement) dbConn
							.prepareStatement(sql_insert);
					pstmt.setString(1, tbBarcodeLock.getFBarcode());// sn码
					pstmt.setString(2, resume_isLocked);// 锁定状态
					pstmt.setString(3, bts_FOpReason);// 添加原因
					pstmt.setString(4, FLockUser);// 添加人
					pstmt.setString(5, Util.getDateTime());// 添加时间
					sql_result_count = pstmt.executeUpdate();
					pstmt.close();
					// if (sql_result_count > 0)
					// System.out.println("添加条码履历表成功!");
					// else
					// System.out.println("添加条码履历表失败!");
				}
				return lockmes;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
