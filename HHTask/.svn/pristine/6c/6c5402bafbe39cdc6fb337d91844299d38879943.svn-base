package com.task.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.FileCopyUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;

public class MKUtil {
	/**
	 * 写出JSON
	 * 
	 * @param b
	 *            状态成功
	 * @param msg
	 *            信息
	 * @param obj
	 *            对象
	 * @return
	 */
	public static String writeJSON(boolean b, String msg, Object obj) {
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("success", b);
		maps.put("message", msg);
		maps.put("data", obj);
		// String JSONStr = gson.toJson(maps);

		String JSONStr = JSON.toJSONString(maps,
				SerializerFeature.WriteMapNullValue);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(JSONStr);
			response.getWriter().close();
		} catch (IOException e) {
			return null;
		}
		return JSONStr;
	}
	/**
	 * 写出JSON
	 * @author licong
	 * @param b
	 *            状态成功
	 * @param msg
	 *            信息
	 * @param obj
	 *            对象
	 * @return
	 */
	public static String writeJSONS(boolean b, String msg, Object obj) {
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("result", b);
		maps.put("msg", msg);
		maps.put("users", obj);
		// String JSONStr = gson.toJson(maps);
		
		String JSONStr = JSON.toJSONString(maps,
				SerializerFeature.WriteMapNullValue);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(JSONStr);
			response.getWriter().close();
		} catch (IOException e) {
			return null;
		}
		return JSONStr;
	}

	/**
	 * 写出JSON
	 * 
	 * @param b
	 *            状态成功
	 * @param msg
	 *            信息
	 * @param obj
	 *            对象
	 * @return
	 */
	public static String writeJSON(boolean b, String msg, Object obj1,
			Object obj2) {
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("success", b);
		maps.put("message", msg);
		maps.put("data1", obj1);
		maps.put("data2", obj2);
		// String JSONStr = gson.toJson(maps);

		String JSONStr = JSON.toJSONString(maps,
				SerializerFeature.WriteMapNullValue);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(JSONStr);
			response.getWriter().close();
		} catch (IOException e) {
			return null;
		}
		return JSONStr;
	}

	/** 写出Json */
	public static String writeJSON(Object obj) {
		// Gson gson = new Gson();
		// String JSONStr = gson.toJson(obj);
		String JSONStr = JSON.toJSONString(obj,
				SerializerFeature.WriteMapNullValue);
		try {
			HttpServletResponse response = ServletActionContext.getResponse(); 
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(JSONStr.replace("\t", "  "));//解析含有tab的字符串
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return JSONStr;
	}

	/**
	 * 对象转换成json字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}

	/**
	 * json字符串转成对象
	 * 
	 * @param str
	 * @param type
	 * @return
	 */
	public static <T> T fromJson(String str, Type type) {
		Gson gson = new Gson();
		return gson.fromJson(str, type);
	}

	/**
	 * json字符串转成对象
	 * 
	 * @param str
	 * @param type
	 * @return
	 */
	public static <T> T fromJson(String str, Class<T> type) {
		Gson gson = new Gson();
		return gson.fromJson(str, type);
	}

	/**
	 * 四舍五入
	 * 
	 * @param i
	 *            小数点后面保留几位小数
	 * @param d
	 *            欲操作的浮点数
	 * @return
	 */
	public static double round(int i, double d) {
		// Float.parseFloat(String.format("%.2f", gangweigongzi))
		return new BigDecimal(d).setScale(i, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
	}

	/**
	 * 保存文件,返回文件名(UUID名字)
	 * 
	 * @param attachment
	 * @param attachmentFileName
	 * @return
	 */
	public static String saveFile(File[] attachment, String[] attachmentFileName) {
		return saveFile(attachment, attachmentFileName, "projectStart");
	}

	/**
	 * 保存文件,返回文件名(UUID名字)
	 * 
	 * @param attachment
	 * @param attachmentFileName
	 * @param starStr
	 * @return
	 */
	public static String saveFile(File[] attachment,
			String[] attachmentFileName, String starStr) {
		String attachmentName = "";
		if (attachment != null && attachment.length > 0) {
			for (int i = 0; i < attachment.length; i++) {
				String fileName = UUID.randomUUID().toString().replaceAll("-","")
						+ attachmentFileName[i].substring(attachmentFileName[i]
								.lastIndexOf("."));
				if (i > 0) {
					attachmentName += "|" + fileName;
				} else {
					attachmentName += fileName;
				}
				attachmentName.trim();

				// 上传到服务器
				String fileRealPath = ServletActionContext.getServletContext()
						.getRealPath("/upload/file/" + starStr)
						+ File.separator + fileName;
				File file = new File(fileRealPath);
				try {
					FileCopyUtils.copy(attachment[i], file);
				} catch (Exception e) {
					e.printStackTrace();
				}

				File saveDirFile = new File(ServletActionContext
						.getServletContext().getRealPath(
								"/upload/file/" + starStr)
						+ File.separator);
				if (!saveDirFile.exists()) {
					saveDirFile.mkdirs();
				}
				File saveDirFile1 = new File(
						"D:/WorkSpace/HHTask/WebRoot/upload/file/" + starStr
								+ File.separator);
				if (!saveDirFile1.exists()) {
					saveDirFile1.mkdirs();
				}

				// 备份到项目
				String beiFenfileRealPath = "D:/WorkSpace/HHTask/WebRoot/upload/file/"
						+ starStr + File.separator + fileName;
				File beiFenFile = new File(beiFenfileRealPath);
				try {
					FileCopyUtils.copy(attachment[i], beiFenFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			throw new RuntimeException("请上传文件!");
		}
		return attachmentName;
	}

	/**
	 * 删除文件
	 * 
	 * @param startStr
	 * @param fileName
	 */
	public static void deleteFile(String startStr, String fileName) {
		File f = new File(ServletActionContext.getServletContext().getRealPath(
				"/upload/file/" + startStr)
				+ File.separator + fileName);
		File f1 = new File("D:/WorkSpace/HHTask/WebRoot/upload/file/"
				+ startStr + File.separator + fileName);
		f.delete();
		f1.delete();
	}

}
