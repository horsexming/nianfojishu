package com.task.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.DaoImpl.TotalDaoImpl;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.entity.AlertMessages;
import com.task.entity.Users;
import com.task.entity.checktype.CheckNote;
import com.task.util.RtxUtil;
import com.task.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UploadServlet extends HttpServlet {

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 1、创建工厂类：DiskFileItemFactory
			DiskFileItemFactory facotry = new DiskFileItemFactory();
			String tempDir = getServletContext().getRealPath("/WEB-INF");
			facotry.setRepository(new File(tempDir));// 设置临时文件存放目录
			// 2、创建核心解析类：ServletFileUpload
			ServletFileUpload upload = new ServletFileUpload(facotry);
			upload.setHeaderEncoding("UTF-8");// 解决上传的文件名乱码
			upload.setFileSizeMax(1024 * 1024 * 1024);// 单个文件上传最大值是1M
			upload.setSizeMax(2048 * 1024 * 1024);// 文件上传的总大小限制

			// 3、判断用户的表单提交方式是不是multipart/form-data
			boolean bb = upload.isMultipartContent(request);
			if (!bb) {
				return;
			}
			// 4、是：解析request对象的正文内容List<FileItem>
			List<FileItem> items = upload.parseRequest(request);
			String storePath = getServletContext().getRealPath(
					"/upload/file/sixJian");// 上传的文件的存放目录
			File file = new File(storePath);
			if (!file.exists()) {
				file.mkdirs();// 如果不存在文件夹就新建
			}
			CheckNote c = new CheckNote();
			Integer id = null;
			for (FileItem item : items) {
				if (item.isFormField()) {
					// 5、判断是否是普通表单：打印看看
					String fieldName = item.getFieldName();// 请求参数名
					String fieldValue = item.getString("UTF-8");// 请求参数值
					if ("typeId".equals(fieldName)) {// 类型ID
						id = Integer.parseInt(fieldValue);
					} else if ("firstPersonCode".equals(fieldName)) {// 负责人工号
						c.setFirstPersonCode(fieldValue);
					} else if ("firstPerson".equals(fieldName)) {// 负责人
						c.setFirstPerson(fieldValue);
					} else if ("depert".equals(fieldName)) {// 部门
						c.setDepert(fieldValue);
					} else if ("code".equals(fieldName)) {// 登录人工号
						c.setLoginCode(fieldValue);
					} else if ("describe".equals(fieldName)) {// 描述
						c.setDescribe(fieldValue);
					}
				} else {
					// 6、上传表单：得到输入流，处理上传：保存到服务器的某个目录中，保存时的文件名是啥？
					String fileName = item.getName();// 得到上传文件的名称 C:\Documents
					// and
					// Settings\shc\桌面\a.txt
					// a.txt
					// 解决用户没有选择文件上传的情况
					if (fileName == null || fileName.trim().equals("")) {
						continue;
					}
					fileName = fileName
							.substring(fileName.lastIndexOf("\\") + 1);
					String newFileName = Util.getDateTime("yyyyMMddHHmmss")
							+ "_" + fileName;
					InputStream in = item.getInputStream();
					String newPath = storePath + "\\" + newFileName;
					OutputStream out = new FileOutputStream(newPath);
					c.setUrl(newFileName);
					c.setStatus("待审批");
					byte b[] = new byte[1024];
					int len = -1;
					while ((len = in.read(b)) != -1) {
						out.write(b, 0, len);
					}
					in.close();
					out.close();
					item.delete();// 删除临时文件
				}
			}
			String sql = "insert into ta_CheckNote (firstPersonCode,firstPerson,typeId,depert,url,loginCode,status,describe) values(?,?,?,?,?,?,?,?)";
			DML(sql, c, id);

			Users user = new Users();
			String sql2 = "select * from users where code=?";
			user = findUsersBycode(sql2, c.getFirstPersonCode());
			Users loginUser = new Users();
			loginUser = findUsersBycode(sql2, c.getLoginCode());
			String pebsUrl = AlertMessagesServerImpl.pebsUrl;//http://116.228.66.246:8099
			AlertMessages alertMessages = new AlertMessages();
			alertMessages.setReceiveuserId(user.getId());// 接收用户id
			alertMessages.setSendUserId(loginUser.getId());// 发送用户id
			alertMessages.setSendUserName(loginUser.getName());// 发送用户名称
			if (loginUser.getPassword() != null)// 如果存在头像，则保存
				alertMessages.setSendUserImg("upload/user/"
						+ loginUser.getPassword().getPicture());// 发送用户头像
			alertMessages.setContent("系统异常" + c.getDescribe());// 内容
			alertMessages.setFunctionUrl(pebsUrl
					+ "CheckNoteAction_findOne.action?nodeId=" + id);// 功能地址
			alertMessages.setAddTime(Util.getDateTime(null));// 添加时间
			alertMessages.setReadStatus("yes");// 阅读状态设置为未读
			alertMessages.setTitle("您有新的审核提醒消息");
			alertMessages.setContent("系统异常" + c.getDescribe() + "  发送人:"
					+ loginUser.getName());// 内容
			alertMessages.setMessageType("提醒");// 消息类型
			TotalDao totalDao = TotalDaoImpl.findTotalDao();
			boolean bool = totalDao.save(alertMessages);// 保存消息
			// 发送RTX系统消息
			if (bool) {
				RtxUtil.sendNotify(user.getCode(), "系统异常" + c.getDescribe()
						+ "  \n\t\t\t 发送人:" + loginUser.getName()
						+ " \n\tPEBS系统(点击进入审批):" 
						+ alertMessages.getFunctionUrl(), loginUser.getName()
						+ "给您发送了一条提醒消息:" + "系统异常" + c.getDescribe(), "0", "0");
			}

			// ActionContext.getContext().getSession().put(
			// TotalDao.users, user);
			// String sql1 = "select * from users where code=?";
			// Integer ids = findIdBycode(sql1,c.getFirstPersonCode());
			// Integer[] arr={ids};
			// AlertMessagesServerImpl.addAlertMessages("系统异常",
			// "系统异常"+c.getDescribe(),arr,"CheckNoteAction_findOne.action?nodeId="+id,
			// true);
		} catch (FileUploadBase.FileSizeLimitExceededException e) {
			request.setAttribute("message", "单个文件大小不能超出5M");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		} catch (FileUploadBase.SizeLimitExceededException e) {
			request.setAttribute("message", "总文件大小不能超出7M");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "上传失败");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}

	public void DML(String sql, CheckNote c, Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtils.getConn();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, c.getFirstPersonCode());
			preparedStatement.setString(2, c.getFirstPerson());
			preparedStatement.setInt(3, id);
			preparedStatement.setString(4, c.getDepert());
			preparedStatement.setString(5, c.getUrl());
			preparedStatement.setString(6, c.getLoginCode());
			preparedStatement.setString(7, c.getStatus());
			preparedStatement.setString(8, c.getDescribe());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, preparedStatement, connection);
		}
	}

	public Integer findIdBycode(String sql, String code) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Integer id = null;
		try {
			connection = JDBCUtils.getConn();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, code);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, preparedStatement, connection);
		}
		return id;
	}

	public Users findUsersBycode(String sql, String code) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Users user = new Users();
		try {
			connection = JDBCUtils.getConn();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, code);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setCode(rs.getString("code"));
				user.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, preparedStatement, connection);
		}
		return user;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
