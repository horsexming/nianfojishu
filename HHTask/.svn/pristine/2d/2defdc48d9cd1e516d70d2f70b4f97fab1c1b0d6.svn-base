package com.task.ServerImpl.codetranslation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.FileCopyUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.codetranslation.CodeTranslationServer;
import com.task.entity.codetranslation.CodeTranslation;
import com.task.util.Util;

public class CodeTranslationServerImpl implements CodeTranslationServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean delete(CodeTranslation codeTranslation) {
		return totalDao.delete(codeTranslation);
	}
	
	public boolean save(CodeTranslation codeTranslation){
		if(findSame(codeTranslation)){
			return totalDao.save(codeTranslation);
		}
		return false;
	}
	public Map<Integer, Object> QueryCode(CodeTranslation codeTranslation, int pageNo, int pageSize,String tag){
		if (codeTranslation == null) {
			codeTranslation= new CodeTranslation();
		}
		String hql = null;
		String sql = null;
		if (codeTranslation.getType().equals("sys")) {//系统设置
			sql = " type ='sys' order by id desc";
		} else if("粉末".equals(codeTranslation.getType())) {
			sql = " type ='粉末' order by id desc";
		}else{
			sql = " type not in ('sys','粉末') order by id desc";
		}
		hql = totalDao.criteriaQueries(codeTranslation,sql,null);
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		map.put(1, objs);
		map.put(2, count);
		return map;
	}
	@Override
	public Map<Integer, Object> findAll(CodeTranslation codeTranslation, int pageNo, int pageSize,String tag){
		if (codeTranslation == null) {
			codeTranslation= new CodeTranslation();
		}
		String hql = null;
		String sql = null;
		if (tag.equals("sys")) {// 分析
			sql = " type ='sys' order by id desc";
		} else if("fenmo".equals(tag)){
			sql = " type = '粉末' order by id desc";
		}else{
			sql = " type not in ('sys','粉末') order by id desc";
		}
		hql = totalDao.criteriaQueries(codeTranslation,sql,null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}
	
	public boolean findSame(CodeTranslation codeTranslation) {
		CodeTranslation c = new CodeTranslation();
		if (codeTranslation.getId() != null) {// 修改的时候的判断
			c = (CodeTranslation) totalDao.getObjectByQuery(
					"from CodeTranslation where keyCode=? and id <> ?",
					codeTranslation.getKeyCode(), codeTranslation.getId());
		} else {
			c = (CodeTranslation) totalDao.getObjectByQuery(
					"from CodeTranslation where keyCode=?", codeTranslation
							.getKeyCode());
		}
		System.err.println(c);
		if (c != null) {
			return false;
		}
		return true;
	}
	public String importFile(File importFile) {
		String msg = "true";
		boolean flag = true;
		String fileName = Util.getDateTime("yyyyMMddhhmmss") + ".xls";
		// 上传到服务器
		String fileRealPath = ServletActionContext.getServletContext()
				.getRealPath("/upload/file")
				+ "/" + fileName;
		File file = new File(fileRealPath);
		jxl.Workbook wk = null;
		int i = 0;
		try {
			FileCopyUtils.copy(importFile, file);
			// 开始读取excle表格
			InputStream is = new FileInputStream(fileRealPath);// 创建文件流;
			if (is != null) {
				wk = Workbook.getWorkbook(is);// 创建workbook
			}

			Sheet st = wk.getSheet(0);// 获得第一张sheet表;
			int exclecolums = st.getRows();// 获得excle总行数
			if (exclecolums > 2) {
				List<Integer> strList = new ArrayList<Integer>();
				for (i = 2; i < exclecolums; i++) {
					Cell[] cells = st.getRow(i);// 获得每i行的所有单元格（返回的是数组）
					if (cells[2].getContents() != null
							&& cells[2].getContents() != "") {
						String a = cells[1].getContents();//类型
						String b = cells[2].getContents();//国标号
						String c = cells[3].getContents();//件号
						String d = cells[4].getContents();//名称
						CodeTranslation codeTranslation = new CodeTranslation();
						codeTranslation.setKeyCode(b);
						codeTranslation.setType(a);
						codeTranslation.setValueCode(c);
						codeTranslation.setValueName(d);
						if(findSame(codeTranslation)==false){
							strList.add(i+1);
							continue;
						}
						if(!a.equals("国标")&&!a.equals("编码")){
							strList.add(i+1);
							continue;
						} else if (b == null || b.length() <= 0) {
							strList.add(i + 1);
							continue;
						} else if (c == null || c.length() <= 0) {
							strList.add(i + 1);
							continue;
						}
						totalDao.save(codeTranslation);
					}else{
						strList.add(i+1);
					}
					
					}
				is.close();// 关闭流
				wk.close();// 关闭工作薄
				if(strList!=null && strList.size()>0){
					Integer drcount = exclecolums - 2 - strList.size();
				msg = "已成功导入" + drcount + "个，失败" + strList.size() + "个，失败的行数为：";
				for (int j = 0; j < strList.size(); j++) {
					if (j == 0) {
						msg += strList.get(j);
					} else {
						msg += "," + strList.get(j);
					}
				}
				msg += "。";
				}
			}else{
				msg="没有获取到行数";
			}
					
		} catch (Exception e) { 
			e.printStackTrace();
			
		}
		return msg;
	}
	@Override
	public CodeTranslation findById(Integer id) {
		return (CodeTranslation)totalDao.get(CodeTranslation.class,id);
	}

	@Override
	public boolean update(CodeTranslation codeTranslation) {
		if(findSame(codeTranslation)){
			return totalDao.update(codeTranslation);
		}
		return false;
	}
	public CodeTranslation findByKeyCode(String keyCode){
		return (CodeTranslation)totalDao.getObjectByQuery("from CodeTranslation where keyCode=?",keyCode);
	}
	@Override
	public String exportFile(File exportFile) {
		String hql = "from CodeTranslation where type <> 'sys'";
		hql += " order by id desc";
		List list = totalDao.query(hql);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("编码库".getBytes("GB2312"), "8859_1") + ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("编码库数据", 0);
			ws.setColumnView(0, 30);
			ws.setColumnView(1, 30);
			ws.setColumnView(2, 16);
			ws.setColumnView(3, 45);
			ws.addCell(new Label(0, 0, "国标号"));
			ws.addCell(new Label(1, 0, "件号"));
			ws.addCell(new Label(2, 0, "类型"));
			ws.addCell(new Label(3, 0, "名称"));
			for (int i = 0; i < list.size(); i++) {
				CodeTranslation c = (CodeTranslation) list.get(i);
				ws.addCell(new Label(0, i + 1,c.getKeyCode()));
				ws.addCell(new Label(1, i + 1, c.getValueCode()));
				ws.addCell(new Label(2, i + 1,c.getType()));
				ws.addCell(new Label(3, i + 1,c.getValueName()));
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	
		return null;
	}	
}
