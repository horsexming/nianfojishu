package com.task.ServerImpl.yw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

/**
 * @Title: UploadUtil.java
 * @Package com.task.ServerImpl.yw
 * @Description: TODO 上传文件工具类
 * @author 曾建森
 * @date 2012-10-29 下午02:02:16
 * @version V1.0
 */
public class FileUtil {

	public static final String num = "num";

	/**
	 * @Title: uploadFile
	 * @Description: 上传单个文件文件
	 * @param file
	 *            文件
	 * @param fileName
	 *            文件名称
	 * @param fileLocation
	 * @return String 返回文件名称
	 * @throws
	 */
	
	public static String uploadFile(File file, String fileName,String fileLocation,String copyLocation) {
		String resorceFile = null;
		if (file.exists()) {
			InputStream is = null;
			OutputStream os = null;
			String path = ServletActionContext.getServletContext().getRealPath(
					fileLocation);
			String newFileName = System.currentTimeMillis()
					+ fileName.substring(fileName.indexOf("."));
			resorceFile = path + File.separator + newFileName;
			String copyFile = copyLocation + File.separator + newFileName;
			for(int j = 0;j < 2;j++){
				try {
					if(j == 0){
						os = new FileOutputStream(resorceFile);
					}else{
						os = new FileOutputStream(copyFile);
					}
					is = new FileInputStream(file);
					byte[] buf = new byte[1024];
					int length = 0;
					while ((length = is.read(buf)) != -1) {
						os.write(buf, 0, length);
						os.flush();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (os != null) {
							os.close();
						}
						if (is != null) {
							is.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return resorceFile;
	}
	/**
	 * @Title: copyFiles
	 * @Description: 上传多个文件
	 * @param files 文件集合
	 * @param fileNames 文件名字集合
	 * @param fileLocation 文件保存位置
	 * @return String[]  
	 * @throws
	 */
	public static String[] copyFiles(List<File> files, List<String> fileNames,
			String fileLocation,String copyLocation) {
		InputStream in = null;
		OutputStream out = null;
		String path = ServletActionContext.getServletContext().getRealPath(
				fileLocation);
		String[] newFileNames = new String[files.size()];
		for (int i = 0; i<files.size();i++) {
			String fileName = fileNames.get(i);
			String newFileName = System.currentTimeMillis()
			+ fileName.substring(fileName.indexOf("."));
			String resorceFile = path + File.separator + newFileName;
			String copyFile = copyLocation + File.separator + newFileName;
			for(int j = 0; j < 2;j++){
				try {
					if(j == 0){
						out = new FileOutputStream(resorceFile);
					}else{
						out = new FileOutputStream(copyFile);
					}
					in = new FileInputStream(files.get(i));
					byte[] buf = new byte[1024];
					int length = 0;
					while ((length = in.read(buf)) != -1) {
						out.write(buf, 0, length);
						out.flush();
					}
					newFileNames[i] = resorceFile;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (out != null) {
							out.close();
						}
						if (in != null) {
							in.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return newFileNames;
	}
	/**
	 * @Title: getNum
	 * @Description: 付款凭证编号
	 * @param @param deptId 部门编号
	 * @return String
	 * @throws
	 */
	public static synchronized String getNum(String deptId) {
		String a= ServletActionContext.getServletContext()
			.getRealPath("/WEB-INF/classes/proof_number.properties");
		StringBuffer str = new StringBuffer();
		Calendar date = Calendar.getInstance();
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH) + 1;
		str.append(year + deptId + month);
		Properties prop = new Properties();
		int val = 0;
		try {
			InputStream in = new FileInputStream(a);
			prop.load(in);
			Set set = prop.entrySet();
			for(Object o:set){
				System.out.println(o);
			}
			val = Integer.parseInt(prop.getProperty(num));
			val++;
			if (val < 10) {
				str.append("00").append(val);
			} else {
				str.append(val);
			}
			prop.setProperty(num, val + "");
			OutputStream out = new FileOutputStream(a);
			prop.store(out, "");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str.toString();
	}
}
