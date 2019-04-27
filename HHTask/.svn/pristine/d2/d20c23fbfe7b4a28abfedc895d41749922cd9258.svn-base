package com.task.ServerImpl.caiwu.pz;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.util.FileCopyUtils;

import com.task.Dao.TotalDao;
import com.task.Server.caiwu.pz.CwCertificateServer;
import com.task.entity.Attendance;
import com.task.entity.Users;
import com.task.entity.banci.BanCi;
import com.task.entity.caiwu.pz.CwCertificate;
import com.task.entity.menjin.AccessWebcam;
import com.task.util.Util;

/***
 * 财务凭证
 * 2017-08-21
 * @author @author licong
 * 
 */
@SuppressWarnings("unchecked")
public class CwCertificateServerImpl implements CwCertificateServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public String daoRupinzheng(File pinzheng) {
		// TODO Auto-generated method stub
		String msg = "true";
		boolean flag = true;
		String fileName = "pingzheng_" + Util.getDateTime("yyyyMMddhhmmss") + ".xls";
		// 上传到服务器
		String fileRealPath = ServletActionContext.getServletContext()
				.getRealPath("/upload/file")
				+ "/" + fileName;
		File file = new File(fileRealPath);
		jxl.Workbook wk = null;
		int i = 0;
		Users u = Util.getLoginUser();
		String newTime = Util.getDateTime();
		String newDate = Util.getDateTime("yyyy-MM-dd");
		try {
			FileCopyUtils.copy(pinzheng, file);
			// 开始读取excle表格
			InputStream is = new FileInputStream(fileRealPath);// 创建文件流;
			if (is != null) {
				wk = Workbook.getWorkbook(is);// 创建workbook
			}

			Sheet st = wk.getSheet(0);// 获得第一张sheet表;
			int exclecolums = st.getRows();// 获得excle总行数
			if (exclecolums > 2) {
				StringBuffer sberror = new StringBuffer();
				int successcount = 0;
				int errorcount = 0;
				int error_index = 0;
				for (i = 2; i < exclecolums; i++) {
					try {
						if (error_index > 0) {
							continue;
						}
						Cell[] cells = st.getRow(i);// 获得每i行的所有单元格（返回的是数组）
						String number = cells[1].getContents();// 编号
						String pzDate = cells[2].getContents();// 日期
						if(pzDate!=null&&!"".equals(pzDate)){
							pzDate = pzDate.replaceAll("/", "-").replaceAll("\\.", "-");
						}
						String introduction = cells[3].getContents();// 简介
						String danganguiNum = cells[4].getContents();// 档案柜编号
						String fujianNum = cells[5].getContents();// 附件张数
						AccessWebcam accessWebcam = (AccessWebcam) totalDao.getObjectByCondition("from AccessWebcam where cabinetNum = ?", danganguiNum);
						if(accessWebcam!=null){
							CwCertificate ce = (CwCertificate) totalDao.getObjectByCondition("from CwCertificate where number = ? and pzDate = ?", number,pzDate);
							if(ce==null){//添加
								CwCertificate ce1 = new CwCertificate();
								ce1.setAddTime(newTime);
								ce1.setAddUser(u.getName());
								ce1.setAddUserId(u.getId());
								ce1.setDanganguiId(accessWebcam.getId());
								ce1.setDanganguiNum(accessWebcam.getCabinetNum());
								if(fujianNum!=null&&!"".equals(fujianNum)){
									ce1.setFujianNum(Integer.parseInt(fujianNum.replaceAll(" ", "")));//附件张数
								}
								ce1.setIntroduction(introduction);
								ce1.setNumber(number);
								ce1.setPzDate(pzDate);
								ce1.setStatus("待存入");
								ce1.setPzType("pz");
								totalDao.save(ce1);
							}else {//更新
								ce.setDanganguiId(accessWebcam.getId());
								ce.setDanganguiNum(accessWebcam.getCabinetNum());//档案柜编号
								if(fujianNum!=null&&!"".equals(fujianNum)){
									ce.setFujianNum(Integer.parseInt(fujianNum.replaceAll(" ", "")));//附件张数
								}
								ce.setIntroduction(introduction);//简介
								totalDao.update(ce);
							}
							successcount++;
						}else {
							sberror.append("第" + (i + 1) + "行,档案柜编号不存在");
							errorcount++;
							if (error_index == 0) {
								error_index = i + 1;
							}
						}
						if (i % 200 == 0) {
							totalDao.clear();
						}
					} catch (Exception e) {
						e.printStackTrace();
						sberror.append("第" + (i + 1) + "行,数据格式错误!异常:"
								+ e.getMessage());
						errorcount++;
						if (error_index == 0) {
							error_index = i + 1;
						}
						continue;
					}
				}
				is.close();// 关闭流
				wk.close();// 关闭工作薄
				String errs = "";
				if (errorcount > 0) {
					errs = "从第" + error_index + "行开始出现错误，请核对错误后，从第"
							+ error_index + "行开始重新导入(即删除excel中1-"
							+ (error_index - 1) + "行的数据)!\\n";
				}
				msg = "总条数:" + exclecolums + "\\n已成功导入" + successcount + "个,失败"
						+ errorcount + "个\\n" + errs + "失败的內容如下:\\n" + sberror;
			} else {
				msg = "没有获取到行数";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "导入失败,出现异常" + e;
		}
		return msg;
	}

	@Override
	public String deletecwCertificate(Integer id) {
		// TODO Auto-generated method stub
		CwCertificate certificate = getCwCertificateById(id);
		if(certificate!=null){
			if(totalDao.delete(certificate)){
				return "删除成功！";
			}
		}
		return "删除失败！";
	}

	@Override
	public Object[] findCwCertificateList(CwCertificate cwCertificate,
			int pageNo, int pageSize, String tag) {
		// TODO Auto-generated method stub
		if (cwCertificate == null) {
			cwCertificate = new CwCertificate();
		}
		String sql = "";
		Users u = Util.getLoginUser();
		if ("all".equals(tag)) {
		}else if ("cw".equals(tag)||"pz".equals(tag)) {
			sql += "pzType = 'pz'";
		}else if ("fp".equals(tag)||"cw1".equals(tag)) {
			sql += "pzType = 'fp'";
		}else {
			sql += " addUserId = "+u.getId();
		}
		String hql = totalDao.criteriaQueries(cwCertificate, sql);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);// 总条数
		Object [] o = {list ,count};
		return o;
	}

	@Override
	public CwCertificate getCwCertificateById(Integer id) {
		// TODO Auto-generated method stub
		return (CwCertificate) totalDao.getObjectById(CwCertificate.class, id);
	}

	@Override
	public String saveCwCertificate(CwCertificate cwCertificate,String tag) {
		// TODO Auto-generated method stub
		cwCertificate.setAddTime(Util.getDateTime());
		Users u = Util.getLoginUser();
		cwCertificate.setAddUser(u.getName());
		cwCertificate.setAddUserId(u.getId());
		cwCertificate.setStatus("待存柜");
		if("fp".equals(tag)){
			cwCertificate.setPzType("fp");
		}else {
			cwCertificate.setPzType("pz");
		}
		if(totalDao.save(cwCertificate)){
			return "添加成功！";
		}
		return "添加失败！";
	}

	@Override
	public String updateCwCertificate(CwCertificate c) {
		// TODO Auto-generated method stub
		CwCertificate c1 = getCwCertificateById(c.getId());
		BeanUtils.copyProperties(c, c1,
				new String[] { "id", "addTime","addUser","addUserId",
				"status"});
		if(c1!=null){
			if(totalDao.update(c1)){
				return "修改成功";
			}
		}
		return "修改失败！";
	}


}
