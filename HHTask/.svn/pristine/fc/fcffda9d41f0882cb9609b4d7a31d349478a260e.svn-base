package com.task.ServerImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.FileCopyUtils;

import com.task.Dao.TotalDao;
import com.task.DaoImpl.TotalDaoImpl;
import com.task.Server.AddUserServer;
import com.task.Server.UserServer;
import com.task.entity.DeptNumber;
import com.task.entity.Password;
import com.task.entity.Users;
import com.task.util.DateUtil;
import com.task.util.MD5;
import com.task.util.Util;

public class AddUserServerImpl implements AddUserServer {
	private TotalDao totalDao;
	private UserServer userServer;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public String addUser(File chageWageFile) {
		String filName = Util.getDateTime("yyyyMMddhhmmss") + ".xls";
		// 上传到服务器
		String fileRealPath = ServletActionContext.getServletContext()
				.getRealPath("/upload/file")
				+ "/" + filName;
		File file = new File(fileRealPath);
		int i = 0;
		String msg = "";
		List<Integer> intList = new ArrayList<Integer>();
		Integer cfCount = 0;
		int succsscount = 0;
		StringBuffer errormsg = new StringBuffer();
		try {
			FileCopyUtils.copy(chageWageFile, file);
			// 开始读取excel表格
			InputStream is = null;
			jxl.Workbook rwb = null;
			Sheet rs = null;
			try {
				is = new FileInputStream(fileRealPath);// 创建文件流
				rwb = Workbook.getWorkbook(is);// 创建workBook
				rs = rwb.getSheet(0);// 获得第一张Sheet表

			} catch (Exception e) {
				// TODO: handle exception
				return msg = "导入失败，请查看表格式是否为2003版!";
			}
			int excelcolumns = rs.getRows();// 获得总行数
			if (excelcolumns > 1) {
				List<String> code = new ArrayList<String>();
				for (i = 1; i < excelcolumns; i++) {
					Users user = new Users();
					Cell[] cells = rs.getRow(i);// 获得每i行的所有单元格（返回的是数组）
					String fuck1 = cells[1].getContents();// 获得 姓名
					if (fuck1 == null || fuck1.length() <= 0) {
						cfCount++;
						errormsg.append("第" + (i + 1) + "行 名称不能为空\\n");
						continue;
					} else {
						fuck1 = fuck1.replaceAll(" ", "");
					}

					String a = cells[2].getContents();// 获得 工号
					String fuck2 = cells[3].getContents();// 获得 卡号
					if (fuck2 != null && fuck2.length() > 0
							&& code.contains(fuck2)) {
						intList.add(i);
						cfCount++;
						errormsg.append("第" + (i + 1) + "行 卡号不能为空\\n");
						continue;

					}
					code.add(fuck2);
					String b = cells[4].getContents();// 获得 性别
					String c = cells[5].getContents();// 获得 学历
					String d = cells[6].getContents();// 获得 身份证号
					String e = cells[7].getContents();// 获得 手机号
					String e1 = cells[8].getContents();// 获得 电话号
					String f = cells[9].getContents();// 获得 住宅
					DateCell dc;
					Date g = null;
					try {
						dc = (DateCell) cells[10];
						g = dc.getDate();// 获得入职日期
					} catch (Exception e2) {
						e2.printStackTrace();
						cfCount++;
						errormsg.append("第" + (i + 1) + "行 入职日期不能为空\\n");
						continue;
					}
					String h = cells[11].getContents();// 获得 职务
					String k = cells[12].getContents();
					k = k.replaceAll(" ", "");
					String l;
					try {
						l = cells[13].getContents();
						l = l.replaceAll(" ", "");
					} catch (Exception e2) {
						l = "";
						// e2.printStackTrace();
						// continue;

					}
					String m = k + "_" + l;
					if (l == null || "".equals(l.replaceAll(" ", ""))) {

						l = "";
						m = k;
					}
					String a1 = cells[14].getContents();// 获得民族
					String a2 = cells[15].getContents();// 获得籍贯
					String a3 = cells[16].getContents();// 获得户籍

					DateCell dc1;
					Date a4 = null;
					try {
						dc1 = (DateCell) cells[17];
						a4 = dc1.getDate();// 获得出生年月
					} catch (Exception e2) {
						e2.printStackTrace();
						a4 = Util.StringToDate(d.substring(6, 14), "yyyyMMdd");
					}
					String a5 = null;// 获得户籍性质
					String a6 = null;// 获得职级
					String a7 = null;// 获得状态
					DateCell dc2;
					Date a8 = null;
					String a9 = null;// 获得是否属于内部人员
					String a10 = null;
					String a11 = null;
					String a12 = null;
					try {
						a5 = cells[18].getContents();// 获得户籍性质
					} catch (Exception e2) {
					}
					try {
						a6 = cells[19].getContents();// 获得职级
					} catch (Exception e2) {
					}
					try {
						a7 = cells[20].getContents();// 获得状态
					} catch (Exception e2) {
					}
					try {
						dc2 = (DateCell) cells[21];
						a8 = dc2.getDate();// 使用到期时间
					} catch (Exception e2) {
					}
					try {
						a9 = cells[22].getContents();// 获得是否属于内部人员
					} catch (Exception e2) {
					}
					try {
						a10 = cells[23].getContents();// 获得邮箱
					} catch (Exception e2) {
					}
					try {
						a11 = cells[24].getContents();// 获得职称
					} catch (Exception e2) {
					}
					try {
						a12 = cells[25].getContents();// 获得职称级别
					} catch (Exception e2) {
					}
					String jobtitleGetTime = "";//职称取得时间
					String politicalAspects = "";//政治面貌
					String degree = "";//学位
					String graduationInstitutions = "";//毕业院校
					String speciality = "";//所学专业
					String dateOfGraduation = "";//毕业日期
					String staffNature = "";//员工性质
					String post = "";//职级
					String maritalStatus = "";//婚姻状况
					try {
						jobtitleGetTime = cells[26].getContents();
					} catch (Exception e2) {
					}
					try {
						politicalAspects = cells[27].getContents();
					} catch (Exception e2) {
					}
					try {
						degree = cells[28].getContents();
					} catch (Exception e2) {
					}
					try {
						graduationInstitutions = cells[29].getContents();
					} catch (Exception e2) {
					}
					try {
						speciality = cells[30].getContents();
					} catch (Exception e2) {
					}
					try {
						dateOfGraduation = cells[31].getContents();
					} catch (Exception e2) {
					}
					try {
						staffNature = cells[32].getContents();
					} catch (Exception e2) {
					}
					try {
						post = cells[33].getContents();
					} catch (Exception e2) {
					}
					try {
						maritalStatus = cells[34].getContents();
					} catch (Exception e2) {
					}
					
					

					Users user_find = (Users) totalDao.getObjectByCondition(
							"from Users where code=?", a);
					Password pa = new Password();
					if (user_find != null) {
						user = user_find;
						pa = user_find.getPassword();
					}
					user.setCode(a);
					user.setName(fuck1);
					user.setCardId(fuck2);
					user.setSex(b);
					user.setEducation(c);
					user.setUid(d);

					pa.setPhoneNumber(e);
					pa.setTelephone(e1);
					pa.setPresentAddress(f);
					pa.setCensusNature(a5);
					pa.setMailBox(a10);
					pa.setJobtitleGetTime(jobtitleGetTime);
					pa.setPoliticalAspects(politicalAspects);
					pa.setDegree(degree);
					pa.setGraduationInstitutions(graduationInstitutions);
					pa.setSpeciality(speciality);
					pa.setDateOfGraduation(dateOfGraduation);
					pa.setStaffNature(staffNature);
					pa.setMaritalStatus(maritalStatus);
					user.setPassword(pa);
					user.setDuty(h);
					user.setJoined(g);
					user.setBirthplace(a2);
					user.setResidence(a3);
					user.setNation(a1);
					user.setDept(m);// 部门
					user.setBothday(a4);
					user.setPost(a6);
					if (a7 == null || a7.length() == 0) {
						a7 = "在职";
					}
					user.setOnWork(a7);
					user.setTryDays(a8);
					user.setPower("yes");// 可以领用和借用物品
					if (a9 == null || a9.length() == 0) {
						a9 = "是";
					}
					user.setInternal(a9);// 内部人员
					user.setJobtitle(a11);
					user.setLeveltitles(a12);
					user.setPost(post);
					String str = "";
					if (user_find != null) {
						str = "" + totalDao.update(user);
					} else {
						str = userServer.addUser(user, null, null, null);
					}
					if (!"true".equals(str)) {
						cfCount++;
						errormsg.append("第" + (i + 1) + "行" + str + "\\n");
						continue;
					} else {
						succsscount++;
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return msg = "导入失败，数据异常!";

		}
		msg = "已成功导入" + succsscount + "行，失败" + cfCount + "行\\n"
				+ errormsg.toString();
		return msg;
	}

	public void setUserServer(UserServer userServer) {
		this.userServer = userServer;
	}

	public UserServer getUserServer() {
		return userServer;
	}
}
