package com.task.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
import com.task.entity.caiwu.noncore.EnergyConsumption;
import com.task.entity.caiwu.noncore.NonCoreReceivables;
import com.task.entity.caiwu.noncore.NonCoreReceivablesDetail;
import com.task.entity.checktype.CheckNote;
import com.task.util.MKUtil;
import com.task.util.RtxUtil;
import com.task.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * 水费电费收取表
 * @author licong
 *
 */
public class WaterDianServlet extends HttpServlet {

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
				return ;
			}
			// 4、是：解析request对象的正文内容List<FileItem>
			List<FileItem> items = upload.parseRequest(request);
			
//			String realFilePath = "/upload/file/shuiDian";// 上传照片存放目录
//			String storePath = ServletActionContext.getServletContext()
//					.getRealPath(realFilePath);
//			File file = new File(storePath);
//			if (!file.exists()) {
//				file.mkdirs();// 如果不存在文件夹就新建
//			}
			String storePath = getServletContext().getRealPath(
					"/upload/file/shuiDian");// 上传的文件的存放目录
			NonCoreReceivablesDetail n = new NonCoreReceivablesDetail();
			Integer id = null;
			for (FileItem item : items) {
				if (item.isFormField()) {
					// 5、判断是否是普通表单：打印看看
					String fieldName = item.getFieldName();// 请求参数名
					String fieldValue = item.getString("UTF-8");// 请求参数值
					if ("id".equals(fieldName)) {// 类型ID
						id = Integer.parseInt(fieldValue);
						String sql1 = "select * from ta_fin_caiwu_NonCoreReceivables where id = ?";
						if(findNonCoreReceivablesById(sql1, id)==null){
							id = null;
						}
					} else if ("thisbiaoshu".equals(fieldName)) {// 本次读表数
						BigDecimal b = new BigDecimal(Float.valueOf(fieldValue)); 
						float f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue(); 
						n.setThisbiaoshu(f1);
					} else if ("saveUser".equals(fieldName)) {// 提交人名称
						n.setSaveUser(fieldValue);
					} else if ("saveCode".equals(fieldName)) {// 提交人工号
						n.setSaveCode(fieldValue);
					} else if ("type".equals(fieldName)) {// 提交类型
						if("1".equals(fieldValue)){
							n.setKemu("水费");
						}else {
							n.setKemu("电费");
						}
						NonCoreReceivablesDetail nonC = null;
						String sql2 = "select max(id) from ta_fin_caiwu_nonCoreReceivablesDetail where nonCoreReceivablesDetail_id = ? and kemu = ?";
						nonC = findNonCoreReceivablesDetailById(sql2, id, n.getKemu());
						if(nonC!=null){
							n.setLastbiaoshu(nonC.getThisbiaoshu());//上次读表数
							if(n.getThisbiaoshu()-n.getLastbiaoshu()<=0){
								PrintWriter out = response.getWriter();
								out.write("1:"+n.getLastbiaoshu());
//								request.getRequestDispatcher("/message.jsp").forward(request,
//										response);
								return;
							}
							EnergyConsumption ener = null;
							ener = findEnergyConsumptionById("select * from ta_EnergyConsumption where type = ? and isTimeLimit = ?", n.getKemu(), "否");
							if(ener!=null){
								n.setEffectivePrice(ener.getUnitPrice());
								n.setBiaoshu(n.getThisbiaoshu()-n.getLastbiaoshu());
								n.setYingfuJine(n.getBiaoshu()*n.getEffectivePrice());
							}
						}
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
					n.setPhotoPath(newFileName);
					n.setZhuangtai("未收");
					n.setShangchuanTime(Util.getDateTime());
					n.setJiluTime(Util.getDateTime("yyyy-MM-dd"));
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
			String sql = "insert into ta_fin_caiwu_nonCoreReceivablesDetail (thisbiaoshu,saveCode,saveUser,photoPath,kemu,nonCoreReceivablesDetail_id,lastbiaoshu,effectivePrice,biaoshu,yingfuJine,zhuangtai,shangchuanTime,jiluTime,realfuJine) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			if(DML(sql, n, id)){
				PrintWriter out = response.getWriter();
			    out.write("5:");return;
			}else {
				PrintWriter out = response.getWriter();
			    out.write("6:");return;
			}
		} catch (FileUploadBase.FileSizeLimitExceededException e) {
//			request.setAttribute("message", "单个文件大小不能超出5M");
//			request.getRequestDispatcher("/message.jsp").forward(request,
//					response);
			PrintWriter out = response.getWriter();
		    out.write("2:");return;
		} catch (FileUploadBase.SizeLimitExceededException e) {
//			request.setAttribute("message", "总文件大小不能超出7M");
//			request.getRequestDispatcher("/message.jsp").forward(request,
//					response);
			PrintWriter out = response.getWriter();
		    out.write("3:");return;
		} catch (Exception e) {
			e.printStackTrace();
//			request.setAttribute("message", "上传失败");
//			request.getRequestDispatcher("/message.jsp").forward(request,
//					response);
			PrintWriter out = response.getWriter();
		    out.write("4:");return;
		} finally {
		}
//		request.setAttribute("message", "添加成功！");
//		request.getRequestDispatcher("/message.jsp").forward(request,
//				response);
	}

	public boolean DML(String sql, NonCoreReceivablesDetail n, Integer id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtils.getConn();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setFloat(1, n.getThisbiaoshu());
			preparedStatement.setString(2, n.getSaveCode());
			preparedStatement.setString(3, n.getSaveUser());
			preparedStatement.setString(4, n.getPhotoPath());
			preparedStatement.setString(5, n.getKemu());
			preparedStatement.setInt(6, id);
			preparedStatement.setFloat(7, n.getLastbiaoshu());//上次读表数
			preparedStatement.setFloat(8, n.getEffectivePrice());//实效单价
			preparedStatement.setFloat(9, n.getBiaoshu());//读表数
			preparedStatement.setFloat(10, n.getYingfuJine());//应付金额
			preparedStatement.setString(11, n.getZhuangtai());//付款状态
			preparedStatement.setString(12, n.getShangchuanTime());//上传时间
			preparedStatement.setString(13, n.getJiluTime());//抄表日期
			preparedStatement.setFloat(14, 0f);//已付金额
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtils.close(null, preparedStatement, connection);
		}
		return true;
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

	/**
	 * 非主营业务应收总表
	 * @author licong
	 * @param sql
	 * @param i
	 * @return
	 */
	public NonCoreReceivables findNonCoreReceivablesById(String sql, Integer i) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		NonCoreReceivables non = null;
		try {
			connection = JDBCUtils.getConn();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, i);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				non = new NonCoreReceivables();
				non.setReceiveType(rs.getString("receiveType"));
				non.setChengzufang(rs.getString("chengzufang"));
				non.setId(rs.getInt("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, preparedStatement, connection);
		}
		return non;
	}
	/**
	 * 非主营业务应收明细
	 * @author licong
	 * @param sql 
	 * @param i 总表ID
	 * @param s 水、电费，类型
	 * @return
	 */
	public NonCoreReceivablesDetail findNonCoreReceivablesDetailById(String sql, Integer i,String s) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		NonCoreReceivablesDetail nonC = null;
		try {
			connection = JDBCUtils.getConn();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, i);
			preparedStatement.setString(2, s);
			rs = preparedStatement.executeQuery();
			int iii = 0;
			while (rs.next()) {
				iii = rs.getInt(1);
			}
			if(iii>0){
				preparedStatement = connection.prepareStatement("select * from ta_fin_caiwu_nonCoreReceivablesDetail where id = ?");
				preparedStatement.setInt(1, iii);
				rs = preparedStatement.executeQuery();
				while (rs.next()) {
					nonC = new NonCoreReceivablesDetail();
					nonC.setId(rs.getInt("id"));
					nonC.setThisbiaoshu(rs.getFloat("thisbiaoshu"));//上次度表数
					nonC.setKemu(rs.getString("kemu"));//科目
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, preparedStatement, connection);
		}
		return nonC;
	}
	
	/**
	 * 能耗价格表
	 * @author licong
	 * @param sql 
	 * @param s 水电类型
	 * @param s1 是否限时
	 * @return
	 */
	public EnergyConsumption findEnergyConsumptionById(String sql, String s,String s1) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		EnergyConsumption ener = null;
		try {
			connection = JDBCUtils.getConn();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, s);
			preparedStatement.setString(2, s1);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				ener = new EnergyConsumption();
				ener.setId(rs.getInt("id"));
				ener.setUnitPrice(rs.getFloat("unitPrice"));//价格
				ener.setName(rs.getString("name"));//名称
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, preparedStatement, connection);
		}
		return ener;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		//返回值
	}
	
	//处理float类型数据保留1位小数
//	public Float fla(){
////		BigDecimal b = new BigDecimal(Float.valueOf(fieldValue)); 
//		float f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue(); 
//		return f1;
//	}
}
