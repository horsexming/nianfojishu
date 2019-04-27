package com.task.ServerImpl.peb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.springframework.util.FileCopyUtils;

import com.opensymphony.xwork2.ActionContext;
import com.sun.org.apache.commons.beanutils.BeanUtils;
import com.task.Dao.TotalDao;
import com.task.Server.peb.ProductEBServer;
import com.task.Server.sys.CircuitRunServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Screen;
import com.task.entity.Users;
import com.task.entity.peb.PebBanzuJiegou;
import com.task.entity.peb.PebBorrowAndLendLog;
import com.task.entity.peb.PebProduction;
import com.task.entity.peb.PebProductionBanjin;
import com.task.entity.peb.PebUsers;
import com.task.entity.peb.SubTeam;
import com.task.entity.system.CircuitRun;
import com.task.util.Util;

public class ProductEBServerImpl implements ProductEBServer {

	public List<String> monthList;//月份列表
	private String decimalFormatPlace="0.000";//保留小数位(3位)
	private class PebJianBao {
		 String bcolorStyle;
		 String banzu;
		 String fuzeren;
		 Float avg2015;
		 Float avg2016;
		 Float avg2017;
		 List<Float> historyYearList;
		 Float lastYearTargetreach;//上一年的目标达成
		 BigDecimal lastYearJishu;//上一年的年基数
		 Float yearjishu;
		 Float yearTarget;
		 Float yearAvg;
		 Float yearsjZengZhang;//年实际增长
		 Float yearZengZhang;//年效率增长
		 Integer yearbanzuPersonCount;
		 Integer yearallPersonCount;
		 Float yearZhanbi;
		 String yearOrder;
		 Float monthAvg;
		 Float dayAvg;
		 Float monthZengZhang;
		 Integer monthBanZuPersonCount;
		 Integer monthAllPersonCount;
		 Float monthZhanbi;
		 String monthOrder;
		 Float UPPH;
		 String remark;
	}
	private TotalDao totalDao;
	private CircuitRunServer circuitRunServer;
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	public CircuitRunServer getCircuitRunServer() {
		return circuitRunServer;
	}

	public void setCircuitRunServer(CircuitRunServer circuitRunServer) {
		this.circuitRunServer = circuitRunServer;
	}

	@Override
	public String importData(File attachment,String attachmentFileName, String pageStatus) {
		StringBuffer importMessage = new StringBuffer();
		Users user = Util.getLoginUser();
		if (user == null) {
			return "请先登录";
		}
		String fileName = "peb-"+Util.getDateTime("yyyyMMddhhmmss") + ".xls";
		// 上传到服务器
		String fileRealPath = ServletActionContext.getServletContext()
				.getRealPath("/upload/file/peb")
				+ "/" + fileName;
		File file = new File(fileRealPath);
		try {
			FileCopyUtils.copy(attachment, file);
			// 开始读取excle表格
			InputStream is = new FileInputStream(fileRealPath);// 创建文件流;
			if (is != null) {
				String name = attachmentFileName.split(".xls")[0];
				if(name==null || name.equals("")) {
					name = attachmentFileName.split(".xlsx")[0];
				}
				int year=0;
				int month=0;
				if(name.indexOf("年")>0 && name.indexOf("月")>0) {
					String yearStr = name.substring(0,name.indexOf("年"));
					String monthStr = name.substring(name.indexOf("年")+1, name.indexOf("月"));
					year = Integer.parseInt(yearStr);
					month = Integer.parseInt(monthStr);
				}
				
				Workbook workbook = null;
				if(attachmentFileName.endsWith(".xls")) {
					 workbook = new HSSFWorkbook(is);
		        } else if(attachmentFileName.endsWith(".xlsx")) {
		        	workbook = new XSSFWorkbook(is);
		        } 
				if(pageStatus!=null && pageStatus.equals("importBanZuJieGou")) {	//解析班组结构并保存
					Sheet sheet = workbook.getSheet("班组信息");
					String message = analysisSaveBanZuJieGou(sheet, pageStatus);
					importMessage.append(message);
				}else if(pageStatus!=null && pageStatus.equals("importBanZuprincipals")) {//解析导入的班组负责人并更新对象
					Sheet sheet = workbook.getSheet("在职人员");
					String message = analysisUpdateBanZuJieGou(sheet, pageStatus);
					importMessage.append(message);
				}else if(pageStatus==null || !pageStatus.equals("importBJ")) {
					Sheet productionSheet = workbook.getSheet("产品下线");
					if(productionSheet==null) {
						importMessage.append("没有找到工作薄为产品下线的文件");
					}else {
						List<PebProduction> productionList = analysisProduction(productionSheet, pageStatus);
						String message =null;
						for (int i = 0; i < productionList.size(); i++) {
							productionList.get(i).setYear(year);
							productionList.get(i).setMonth(month);
							message = addOrUpdatePebProduction(productionList.get(i), pageStatus);
							if(message!=null && !message.equals("添加成功")) {
								importMessage.append("产品下线：第"+(i+2)+"行，错误："+message+"\n");
							}
						}
					}
					Sheet userSheet = workbook.getSheet("人数");
					if(userSheet==null) {
						importMessage.append("没有找到工作簿为人数的文件");
					}else {
						List<PebUsers> analysisUsers = analysisUsersCount(userSheet, pageStatus);
						String message = null;
						for (int i=0;i<analysisUsers.size();i++) {
							PebUsers pebUsers = analysisUsers.get(i);
							pebUsers.setYear(year);
							pebUsers.setMonth(month);
							message = addOrUpdatePebUsers(pebUsers, pageStatus);
							if(message!=null) {
								importMessage.append("人数：第"+(i+2)+"行，错误："+message+"\n");
							}
						}
					}
				}else {
					//导入钣金工序
					
					Sheet amountOfFinish = workbook.getSheet("加工量输入");
					Sheet rawData = workbook.getSheet("钣金原始数据");
					if(amountOfFinish==null) {
						importMessage.append("没有找到工作簿为加工量输入");
					}else {
						if(rawData==null) {
							importMessage.append("没有找到工作簿为钣金原始数据");
						}else {
							String message = importBanJinProduction(amountOfFinish, rawData,year,month);
							importMessage.append(message);
						}
						
					}
					
				}
				
				
				workbook.close();
			}
			return importMessage.toString();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/*
	 * 解析负责人修改班组结构负责人
	 */
	private String analysisUpdateBanZuJieGou(Sheet sheet,String pageStatus) {
		PebBanzuJiegou pebBanzuJiegou=null;
		for (Row row : sheet) {
			if(row.getRowNum()==0) {
				continue;
			}
			short lastCellNum = row.getLastCellNum();
			String lastCellStr = row.getCell(lastCellNum-1).getStringCellValue();
			String cell2 = row.getCell(lastCellNum-2).getStringCellValue();
			if(lastCellStr!=null && cell2!=null) {
				pebBanzuJiegou =(PebBanzuJiegou) totalDao.getObjectByCondition("from PebBanzuJiegou where name='"+lastCellStr+"'"
						+ " and fatherId in (select id from PebBanzuJiegou where name='"+cell2+"')");
				if(pebBanzuJiegou!=null) {
					row.getCell(0).setCellType(CellType.STRING);
					String code = row.getCell(0).getStringCellValue();
					Users users = (Users) totalDao.getObjectByCondition("from Users where code=?", code);
					if(users==null) {
						throw new RuntimeException("工号："+code+",在系统中未找到");
					}
					addPrincipals(pebBanzuJiegou, users);
				}else {
					throw new RuntimeException("没有找到负责人对应的班组结构："+lastCellStr+",父结构："+cell2);
				}
			}
			
		}
		return "修改成功";
	}
	
	private String analysisSaveBanZuJieGou(Sheet sheet,String pageStatus) {
		PebBanzuJiegou pebBanzuJiegou=null;
		String name=null;
		for (Row row : sheet) {
			if(row.getRowNum()==0) {
				continue;
			}
			int i=0;
			for (Cell cell : row) {
				if(cell!=null) {
					name = cell.getStringCellValue();
					if(name!=null) {
						String hql = "from PebBanzuJiegou where name=? and belongLayer=?";
						if(i!=0) {
							String fatherName = row.getCell(i-1).getStringCellValue();
							hql+=" and fatherId in(select id from PebBanzuJiegou where name='"+fatherName+"') ";
						}
						PebBanzuJiegou banzuJieGou =(PebBanzuJiegou) totalDao.getObjectByCondition(hql, name,i);
						if(banzuJieGou==null) {
							pebBanzuJiegou = new PebBanzuJiegou();
							pebBanzuJiegou.setName(name);
							pebBanzuJiegou.setBelongLayer(i);
							if(i!=0){
								String fatherName = row.getCell(i-1).getStringCellValue();
								PebBanzuJiegou fatherBanzu = (PebBanzuJiegou) totalDao.getObjectByCondition(
										"from PebBanzuJiegou where name=? and belongLayer=?", fatherName,i-1);
								if(fatherBanzu!=null) {
									pebBanzuJiegou.setFatherId(fatherBanzu.getId());
								}
							}
							totalDao.save(pebBanzuJiegou);
						}
					}
				}
				
				
				i++;
			}
		}
		return "添加成功";
	}
	
	private String importBanJinProduction(Sheet amountOfFinish,Sheet rawData,Integer year,Integer month){
		BigDecimal bigDecimal = null; 
		PebProductionBanjin banjin = null;
		for (Row row : amountOfFinish) {
			if(row.getRowNum()==0) {
				continue;
			}
			banjin = new PebProductionBanjin();
			for (Cell cell : row) {
				switch (cell.getColumnIndex()) {
				case 0:
					cell.setCellType(CellType.STRING);
					banjin.setProcessName(cell.getStringCellValue());
					break;
				case 1:
					cell.setCellType(CellType.NUMERIC);
					bigDecimal = new BigDecimal(cell.getNumericCellValue());
					if(bigDecimal.intValue()>0) {
						banjin.setDay(bigDecimal.intValue());
					}
					break;
				case 2:
					cell.setCellType(CellType.NUMERIC);
					bigDecimal = new BigDecimal(cell.getNumericCellValue());
					banjin.setCuNumber(bigDecimal.floatValue());
					break;
				case 3:
					cell.setCellType(CellType.NUMERIC);
					bigDecimal = new BigDecimal(cell.getNumericCellValue());
					banjin.setPEBSNumber(bigDecimal.floatValue());
					break;
				case 4:
					cell.setCellType(CellType.NUMERIC);
					bigDecimal = new BigDecimal(cell.getNumericCellValue());
					banjin.setK3Number(bigDecimal.floatValue());
					break;
				case 5:
					cell.setCellType(CellType.NUMERIC);
					bigDecimal = new BigDecimal(cell.getNumericCellValue());
					if(bigDecimal.intValue()>0) {
						banjin.setYear(bigDecimal.intValue());
					}
					break;
				case 6 :
					cell.setCellType(CellType.NUMERIC);
					bigDecimal = new BigDecimal(cell.getNumericCellValue());
					if(bigDecimal.intValue()>0) {
						banjin.setMonth(bigDecimal.intValue());
					}
					break;
				case 7:
					if(cell!=null ) {
						cell.setCellType(CellType.NUMERIC);
						bigDecimal = new BigDecimal(cell.getNumericCellValue());
						if(bigDecimal.floatValue()>0) {
							banjin.setXiShu(bigDecimal.floatValue());
						}
					}
					break;
				case 8:
					if(cell!=null) {
						cell.setCellType(CellType.STRING);
						banjin.setRemarks(cell.getStringCellValue());
					}
					break;
				default:
					break;
				}
				
			}
			if(banjin.getDay()!=null && banjin.getProcessName()!=null && banjin.getYear()==null && banjin.getMonth()==null) {
				banjin.setYear(year);
				banjin.setMonth(month);
			}
			if(banjin.getYear()==null || banjin.getYear()==0) {
				throw new RuntimeException("第"+row.getRowNum()+"行：年份不能为空");
			}
			if(banjin.getMonth()==null || banjin.getMonth()==0) {
				throw new RuntimeException("第"+row.getRowNum()+"行：月份不能为空");
			}
			if(banjin.getDay()==null || banjin.getDay()==0) {
				throw new RuntimeException("第"+row.getRowNum()+"行：日（天）不能为空");
			}
			addOrUpdatePebProBanjin(banjin, null);
		}
		
		PebUsers pebUsers = null;
		Float xishu = null;
		if(rawData!=null) {
			for(Row row: rawData){
				if(row.getRowNum()==0) {
					continue;
				}
				pebUsers = new PebUsers();
				for (Cell cell : row) {
					
					switch (cell.getColumnIndex()) {
					case 1:
						pebUsers.setYear(year);
						pebUsers.setMonth(month);
						cell.setCellType(CellType.NUMERIC);
						bigDecimal = new BigDecimal(cell.getNumericCellValue());
						if(bigDecimal.intValue()>0) {
							pebUsers.setDay(bigDecimal.intValue());
						}
						break;
					case 2:
						cell.setCellType(CellType.STRING);
						pebUsers.setBanZu(cell.getStringCellValue());
						break;
					case 3:
						//原始数量
						break;
					case 4:
						cell.setCellType(CellType.NUMERIC);
						bigDecimal = new BigDecimal(cell.getNumericCellValue());
						xishu = bigDecimal.floatValue();
						break;
					case 5:
						//转换后的数量
//						cell.setCellType(CellType.NUMERIC);
//						bigDecimal = new BigDecimal(cell.getNumericCellValue());
//						pebUsers.setZsNumber(bigDecimal.floatValue());
						break;
					case 6:
						
						break;
					case 7:
						
						break;
					case 8:
						
						break;
					case 9:
						//人事档案数
						cell.setCellType(CellType.NUMERIC);
						bigDecimal = new BigDecimal(cell.getNumericCellValue());
						pebUsers.setDangAnNum(bigDecimal.intValue());
						break;
					case 10:
						cell.setCellType(CellType.NUMERIC);
						bigDecimal = new BigDecimal(cell.getNumericCellValue());
						if(bigDecimal.intValue()>0) {
							pebUsers.setBorrowNum(bigDecimal.intValue());
						}
						break;
					case 11:
						cell.setCellType(CellType.NUMERIC);
						bigDecimal = new BigDecimal(cell.getNumericCellValue());
						if(bigDecimal.intValue()>0) {
							pebUsers.setLendNum(bigDecimal.intValue());
						}
						break;
					case 12:
						cell.setCellType(CellType.NUMERIC);
						bigDecimal = new BigDecimal(cell.getNumericCellValue());
						if(bigDecimal.floatValue()>0) {
							pebUsers.setNoChuQinNum(bigDecimal.floatValue());
						}
						break;
					case 13:
						cell.setCellType(CellType.NUMERIC);
						bigDecimal = new BigDecimal(cell.getNumericCellValue());
						if(bigDecimal.floatValue()>0) {
							pebUsers.setActualNum(bigDecimal.floatValue());
						}
						break;
					case 19:
						cell.setCellType(CellType.STRING);
						pebUsers.setRemarks(cell.getStringCellValue());
						break;
					default:
						break;
					}
					
				}
				if(pebUsers.getDay()!=null && pebUsers.getBanZu()!=null) {
					pebUsers.setCategory("bj");
					banjin = (PebProductionBanjin) totalDao.getObjectByCondition("from PebProductionBanjin where year=?"
							+ " and month=? and day=? and processName=?", year,month,pebUsers.getDay(),pebUsers.getBanZu());
					if(banjin!=null) {
						banjin.setXiShu(xishu);
						if(banjin.getCuNumber()!=null && pebUsers.getActualNum()!=null) {
							float zsNumber = new BigDecimal(banjin.getCuNumber()).divide(new BigDecimal(xishu)).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
							banjin.setZsNumber(zsNumber);
							totalDao.update(banjin);
							pebUsers.setZsNumber(zsNumber);
							
							pebUsers.setAvgNumber(new BigDecimal(zsNumber).divide(new BigDecimal(pebUsers.getActualNum()),3,BigDecimal.ROUND_HALF_UP).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue());
							
						}
					}
					totalDao.save(pebUsers);
				}
				
			}
		}
		return "导入成功";
	}
	
	private List<PebProduction> analysisProduction(Sheet sheet,String pageStatus) {
		List<PebProduction> list  = new ArrayList<PebProduction>();
		PebProduction pebProduction = null;
		BigDecimal bigDecimal=null;
		for (Row row : sheet) {
			if(row.getRowNum()==0) {
				continue;
			}
			if(row.getCell(3)==null || row.getCell(3).getNumericCellValue()==0) {
				continue;
			}
			pebProduction = new PebProduction();
			for (Cell cell : row) {
				switch (cell.getColumnIndex()) {
					case 0:
						cell.setCellType(CellType.STRING);
						pebProduction.setBanzu(cell.getStringCellValue());
						break;
					case 1:
						cell.setCellType(CellType.NUMERIC);
						bigDecimal = new BigDecimal(cell.getNumericCellValue());
						if(bigDecimal.intValue()>0) {
							pebProduction.setDay(bigDecimal.intValue());
						}
						break;
					case 2:
						cell.setCellType(CellType.STRING);
						pebProduction.setMarkId(cell.getStringCellValue());
//						RichTextString richStringCellValue = cell.getRichStringCellValue();
//						richStringCellValue.getString();
						break;
					case 3:
						cell.setCellType(CellType.NUMERIC);
						bigDecimal = new BigDecimal(cell.getNumericCellValue());
						pebProduction.setCuNumber(bigDecimal.intValue());
						break;
					case 4:
						cell.setCellType(CellType.STRING);
						pebProduction.setMessage(cell.getStringCellValue());
						break;
					case 5:
						cell.setCellType(CellType.STRING);
						pebProduction.setMeasure(cell.getStringCellValue());
						break;
					case 6:
						cell.setCellType(CellType.STRING);
						pebProduction.setZrComp(cell.getStringCellValue());
						break;
					case 7:
						cell.setCellType(CellType.STRING);
						pebProduction.setZrCompMeasure(cell.getStringCellValue());
						break;
					case 8:
						cell.setCellType(CellType.NUMERIC);
						bigDecimal = new BigDecimal(cell.getNumericCellValue());
						pebProduction.setXiShu(bigDecimal.floatValue());
						break;
					case 9:
						if(cell.getCellTypeEnum()!=CellType.ERROR && cell.getCellTypeEnum()!=CellType.FORMULA) {
							cell.setCellType(CellType.NUMERIC);
							bigDecimal = new BigDecimal(cell.getNumericCellValue());
							if(bigDecimal.floatValue()>0) {
								pebProduction.setZsNumber(bigDecimal.floatValue());
							}
						}
						break;
					case 10:
						cell.setCellType(CellType.STRING);
							pebProduction.setRemarks(cell.getStringCellValue());
						break;
					case 11:
						cell.setCellType(CellType.NUMERIC);
						bigDecimal = new BigDecimal(cell.getNumericCellValue());
						if(bigDecimal.intValue()>0) {
							pebProduction.setYear(bigDecimal.intValue());
						}
						break;
					case 12:
						cell.setCellType(CellType.NUMERIC);
						bigDecimal = new BigDecimal(cell.getNumericCellValue());
						if(bigDecimal.intValue()>0) {
							pebProduction.setMonth(bigDecimal.intValue());
						}
					default:
						break;
				}
			}
			list.add(pebProduction);
		}
		
		return list;
	}

	private Float getProductionDayByzsNum(String banzu,Integer year,Integer month,Integer day) {
		
		String hql = "select sum(zsNumber) from PebProduction where zsNumber is not null and banZu=? ";
		if(year!=null) {
			hql+=" and year= "+year;
		}
		if(month!=null) {
			hql+=" and month= "+month;
		}
		if(day!=null) {
			hql+=" and day= "+day;
		}
		Float zuCount =(Float) totalDao.getObjectByCondition(hql,banzu);
		
		return zuCount;
	}
	
	private void saveBeforDayData(String objectName,String banzu,String dateTime,int currentNum) {
		if(currentNum<=0) {
			return;
		}
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parse;
		try {
			parse = format.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("日期转换异常:"+e);
		}
		calendar.setTime(parse);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		Date date = calendar.getTime();
		format = new SimpleDateFormat("yyyy");
		Integer year = Integer.parseInt(format.format(date));
		format = new SimpleDateFormat("MM");
		Integer month = Integer.parseInt(format.format(date));
		format = new SimpleDateFormat("dd");
		Integer day = Integer.parseInt(format.format(date)); 
		if((day==7 || day==8 ) && banzu.equals("MINI")) {
			System.out.println();
		}
		if(objectName.equals("PebProduction")) {
			PebProduction pebpro =(PebProduction) totalDao.getObjectByCondition(
						"from PebProduction where banzu=? and year=? and month=? and day=?",banzu,year,month,day);
			if(pebpro==null) {
				pebpro = new PebProduction();
				pebpro.setYear(year);
				pebpro.setMonth(month);
				pebpro.setDay(day);
				pebpro.setBanzu(banzu);
				totalDao.save(pebpro);
				saveBeforDayData(objectName, banzu, year+"-"+month+"-"+day,--currentNum);
			}else {
				return ;
			}
		}else if("PebUsers".equals(objectName)){
			
			PebUsers pebUser =(PebUsers) totalDao.getObjectByCondition(
						"from PebUsers where banzu=? and year=? and month=? and day=?",banzu,year,month,day);
			if(pebUser==null) {
				pebUser = new PebUsers();
				pebUser.setYear(year);
				pebUser.setMonth(month);
				pebUser.setDay(day);
				pebUser.setBanZu(banzu);
				totalDao.save(pebUser);
				saveBeforDayData(objectName, banzu, year+"-"+month+"-"+day,--currentNum);

			}else {
				return;
			}
		}else if("PebProductionBanjin".equals(objectName)) {
			PebProductionBanjin banjin = (PebProductionBanjin) totalDao.getObjectByCondition("from PebProductionBanjin "
					+ "where processName=? and year=? and month=? and day=? ",banzu,year,month,day );
			if(banjin==null) {
				banjin = new PebProductionBanjin();
				banjin.setYear(year);
				banjin.setMonth(month);
				banjin.setDay(day);
				banjin.setProcessName(banzu);
				totalDao.save(banjin);
				saveBeforDayData(objectName, banzu, year+"-"+month+"-"+day,--currentNum);
			}else {
				return ;
			}
		}
	}

	@Override
	public Map<String, Object> findProductionByCon(PebProduction pebProduction, Integer pageNo, Integer pageSize,
			String pageStatus) {
		if(pebProduction==null) {
			pebProduction = new PebProduction();
		}
		
		String hql = totalDao.criteriaQueries(pebProduction, null);
		if(pebProduction.getMonth()!=null && pebProduction.getMonth()<10) {
			hql+=" and month = "+pebProduction.getMonth();
		}
		if(pebProduction.getDay()!=null && pebProduction.getDay()<10) {
			hql+=" and day="+pebProduction.getDay();
		}
		if(pageStatus!=null && pageStatus.equals("noxishu")) {
			hql+=" and xiShu is null and markId is not null";
		}
//		if(pageStatus!=null && pageStatus.equals("bj")) {
//			hql+=" and category='"+pageStatus+"'";
//		}else {
//			hql+=" and (category is null or category <>'"+pageStatus+"')";
//		}
		hql+=" order by year desc,month desc,day desc";
		List<PebProduction> list = totalDao.findAllByPage(hql, pageNo, pageSize, null);
		Integer count = totalDao.getCount(hql);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("count", count);
		map.put("list", list);
		return map;
	}
	
	private List<PebUsers> analysisUsersCount(Sheet sheet,String pageStatus){
		List<PebUsers> list = new ArrayList<PebUsers>();
		PebUsers pebUsers = null;
		BigDecimal bigDecimal = null;
		for (Row row : sheet) {
			if (row.getRowNum()==0) {
				continue;
			}
			if(row.getCell(6)==null || row.getCell(6).getNumericCellValue()==0) {
				continue;
			}
			pebUsers = new PebUsers();
			for (Cell cell : row) {
				switch (cell.getColumnIndex()) {
				case 0:
					cell.setCellType(CellType.STRING);
					pebUsers.setBanZu(cell.getStringCellValue());;
					break;
				case 1:
					cell.setCellType(CellType.NUMERIC);
					bigDecimal = new BigDecimal(cell.getNumericCellValue());
					pebUsers.setDay(bigDecimal.intValue());
					break;
				case 2:
					cell.setCellType(CellType.NUMERIC);
					bigDecimal = new BigDecimal(cell.getNumericCellValue());
					pebUsers.setDangAnNum(bigDecimal.intValue());
					break;
				case 3:
					cell.setCellType(CellType.NUMERIC);
					bigDecimal = new BigDecimal(cell.getNumericCellValue());
					if(bigDecimal.intValue()>0) {
						pebUsers.setBorrowNum(bigDecimal.intValue());
					}
					break;
				case 4:
					cell.setCellType(CellType.NUMERIC);
					bigDecimal = new BigDecimal(cell.getNumericCellValue());
					if(bigDecimal.intValue()>0) {
						pebUsers.setLendNum(bigDecimal.intValue());
					}
					break;
				case 5:
					cell.setCellType(CellType.NUMERIC);
					bigDecimal = new BigDecimal(cell.getNumericCellValue());
					if(bigDecimal.intValue()>0) {
						pebUsers.setNoChuQinNum(bigDecimal.floatValue());
					}
					break;
				case 6:
					cell.setCellType(CellType.NUMERIC);
					bigDecimal = new BigDecimal(cell.getNumericCellValue());
					if(bigDecimal.intValue()>0) {
						pebUsers.setActualNum(bigDecimal.floatValue());
					}
					break;
				case 7:
					cell.setCellType(CellType.NUMERIC);
					bigDecimal = new BigDecimal(cell.getNumericCellValue());
					if(bigDecimal.floatValue()>0) {
						pebUsers.setZsNumber(bigDecimal.floatValue());
					}
					break;
				case 8:
					//人均台数，不读取了
//					if(cell.getCellTypeEnum()!=CellType.ERROR && cell.getCellTypeEnum()!=CellType.FORMULA) {
//						cell.setCellType(CellType.NUMERIC);
//						bigDecimal = new BigDecimal(cell.getNumericCellValue());
//						if(bigDecimal.floatValue()>0) {
//							pebUsers.setAvgNumber(bigDecimal.floatValue());
//						}
//					}
					break;
				case 9:
					if(cell.getCellTypeEnum()!=CellType.ERROR) {
						cell.setCellType(CellType.NUMERIC);
						bigDecimal = new BigDecimal(cell.getNumericCellValue());
						if(bigDecimal.floatValue()>0) {
							pebUsers.setGzHour(bigDecimal.floatValue());
						}
					}
					break;
				case 10:
					if(cell.getCellTypeEnum()!=CellType.ERROR && cell.getCellTypeEnum()!=CellType.FORMULA) {
						cell.setCellType(CellType.NUMERIC);
						bigDecimal = new BigDecimal(cell.getNumericCellValue());
						if(bigDecimal.floatValue()>0) {
							pebUsers.setUPPH(bigDecimal.floatValue());
						}
					}
					break;
				case 11:
					cell.setCellType(CellType.STRING);
					pebUsers.setRemarks(cell.getStringCellValue());
					break;
				default:
					break;
				}
			}
			list.add(pebUsers);
		}
		
		return list;
	}

//	@Override
//	public String addPebUsers(PebUsers pebUsers, String pageStatus) {
//		String banzu = pebUsers.getBanZu();
//		PebUsers pebUsers2 = (PebUsers) totalDao.getObjectByCondition("from PebUsers where banZu = ? and year=? and month=? and day=?"
//				,banzu,pebUsers.getYear(),pebUsers.getMonth(),pebUsers.getDay());
//		if(pebUsers2!=null) {
//			pebUsers2.setDangAnNum(pebUsers.getDangAnNum());
//			pebUsers2.setBorrowNum(pebUsers.getBorrowNum());
//			pebUsers2.setLendNum(pebUsers.getLendNum());
//			pebUsers2.setNoChuQinNum(pebUsers.getNoChuQinNum());
//			pebUsers2.setActualNum(pebUsers.getActualNum());
//			pebUsers2.setGzHour(pebUsers.getGzHour());
//			Float zsNumber = (Float) totalDao.getObjectByCondition("select sum(zsNumber) from PebProduction where banzu=? and year=? and month=? and day=?",
//					pebUsers2.getBanZu(),pebUsers.getYear(),pebUsers.getMonth(),pebUsers.getDay());
//			pebUsers2.setZsNumber(zsNumber);
//			if(zsNumber!=null && pebUsers.getActualNum()!=null) {
//				BigDecimal bigDecimal = new BigDecimal(zsNumber);
//				BigDecimal bigDecimal2 = new BigDecimal(pebUsers.getActualNum());
//				float avgNumber = bigDecimal.divide(bigDecimal2,3,BigDecimal.ROUND_HALF_UP).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
//				pebUsers2.setAvgNumber(avgNumber);
//				if(pebUsers.getGzHour()!=null) {
//					bigDecimal = new BigDecimal(avgNumber);
//					bigDecimal2 = new BigDecimal(pebUsers.getGzHour());
//					float upph = bigDecimal.divide(bigDecimal2,3,BigDecimal.ROUND_HALF_UP).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
//					pebUsers2.setUPPH(upph);
//				}
//			}
//			totalDao.update(pebUsers2);
//		}else {
//			if(pebUsers.getAvgNumber()==null) {
//				if(pebUsers.getActualNum()!=null && pebUsers.getActualNum()!=0) {
//					if(pebUsers.getZsNumber()!=null && pebUsers.getActualNum()!=null) {
//						BigDecimal bigDecimal = new BigDecimal(pebUsers.getZsNumber());
//						BigDecimal bigDecimal2 = new BigDecimal(pebUsers.getActualNum());
//						float avgNumber = bigDecimal.divide(bigDecimal2,3,BigDecimal.ROUND_HALF_UP).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
//						pebUsers.setAvgNumber(avgNumber);
//						if(pebUsers.getGzHour()!=null) {
//							bigDecimal = new BigDecimal(avgNumber);
//							bigDecimal2 = new BigDecimal(pebUsers.getGzHour());
//							float upph = bigDecimal.divide(bigDecimal2,3,BigDecimal.ROUND_HALF_UP).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
//							pebUsers.setUPPH(upph);
//						}
//					}
//				}
//			}
//			totalDao.save(pebUsers);
//		}
//		return null;
//	}

	@Override
	public Map<String, Object> findPebUsersByCon(PebUsers pebUsers, Integer pageNo, Integer pageSize,
			String pageStatus) {
		if(pebUsers==null) {
			pebUsers = new PebUsers();
		}
		String hql = totalDao.criteriaQueries(pebUsers,null);
		if(pageStatus!=null && pageStatus.equals("noChuQinApply")) {
			hql+=" and epId is not null";
		}else {
			if(pebUsers.getMonth()!=null && pebUsers.getMonth()<10) {
				hql+=" and month="+pebUsers.getMonth();
			}
			if(pebUsers.getDay()!=null && pebUsers.getDay()<10) {
				hql+=" and day="+pebUsers.getDay();
			}
			
			Integer year = Integer.parseInt(Util.getDateTime("yyyy"));
			Integer month = Integer.parseInt(Util.getDateTime("MM"));
			Integer day = Integer.parseInt(Util.getDateTime("dd"));
			if(pebUsers.getYear()!=null||pebUsers.getMonth()!=null || pebUsers.getDay()!=null) {
				month = 13;
				day = 32;
			}
			hql+=" and year<="+year+" and month<="+month+" and day<="+day;
		}
		List<PebUsers> list = totalDao.findAllByPage(hql+" order by year desc,month desc,day desc", pageNo, pageSize,null);
		Integer count = totalDao.getCount(hql);
		Map<String, Object> map = new HashMap<String,Object>();
		if(pageStatus==null || !pageStatus.equals("noChuQinApply")) {
			BigDecimal chuqinPersonCount = new BigDecimal(0);
			BigDecimal zsNumber = new BigDecimal(0);
			BigDecimal avgNumber = new BigDecimal(0);
			for (PebUsers pebUsers2 : list) {
				if(pebUsers2.getActualNum()!=null) {
					chuqinPersonCount = chuqinPersonCount.add(new BigDecimal(pebUsers2.getActualNum()));
				}
				if(pebUsers2.getAvgNumber()!=null) {
					avgNumber = avgNumber.add(new BigDecimal(pebUsers2.getAvgNumber()));
				}
				if(pebUsers2.getZsNumber()!=null) {
					zsNumber = zsNumber.add(new BigDecimal(pebUsers2.getZsNumber()));
				}
			}
			map.put("chuqinPersonCount", chuqinPersonCount.floatValue());
			map.put("zsNumber", zsNumber.floatValue());
			map.put("avgNumber", avgNumber.floatValue());
		}
		map.put("count", count);
		map.put("list", list);
		return map;
	}
	
	/*
	 * 根据班组、年月日获取实际人数
	 */
	private Float getPersionCountByBanzu(String banzu,Integer year,Integer month,Integer day) {
		String hql=null;
		if(banzu.equals("数据机房")) {//数据机房没有天数，只计算一天的人数
			hql = "select sum(actualNum) from PebUsers where actualNum is not null and actualNum>0 and banZu=? ";   
		}else {
			hql = "select sum(actualNum) from PebUsers where actualNum is not null and actualNum>0 and banZu=? ";
		}
		if(year!=null) {
			hql+=" and year= "+year;
		}
		if(month!=null) {
			hql+=" and month= "+month;
		}
		if(day!=null) {
			hql+=" and day= "+day;
		}
		Float personCount =(Float) totalDao.getObjectByCondition(hql,banzu);
		
		return personCount;
	}
	
	/**
	 * 获取出勤天数
	 * @return
	 */
	private Integer getChuQinDays(String banzu, Integer year,Integer month,Integer day) {
		StringBuffer buffer =new StringBuffer("from PebUsers where banZu = ? and actualNum is not null and actualNum > 0 ");
		if(year!=null) {
			buffer.append(" and year="+year);
		}
		if(month!=null) {
			buffer.append(" and month="+month);
			
			if(day!=null) {
				buffer.append(" and day="+day);
			}else {
				buffer.append(" group by month,day");
			}
		}else {
			buffer.append(" group by year,month,day");
		}
		List list = totalDao.query(buffer.toString(), banzu);
		return list.size();
//		Integer cqDays =Integer.parseInt(totalDao.getObjectByCondition(buffer.toString(), banzu).toString());
//		return cqDays;
	}
	
	/**
	 * 获取实际产出总数
	 */
	private BigDecimal getzsNumber(String banzu,Integer year,Integer month,Integer day) {
		StringBuffer buffer = new StringBuffer("select sum(zsNumber) from PebUsers where zsNumber is not null"
				+ " and actualNum is not null and actualNum>0 and banZu=? ");
		if(year!=null) {
			buffer.append(" and year="+year);
			
			if(month!=null) {
				buffer.append(" and month="+month);
				
				if(day!=null) {
					buffer.append(" and day="+day);
				}
			}
		}
		Float zsTotalNumber = (Float) totalDao.getObjectByCondition(buffer.toString(), banzu);
		return new BigDecimal(zsTotalNumber);
	}
	
	/**
	 * 获取目标
	 */
	private Float getMbTarget(String banzu,Integer year,Integer month,Integer day) {
		StringBuffer buffer = new StringBuffer("select mbTarget from PebUsers where banZu=? ");
		if(year!=null) {
			buffer.append(" and year="+year);
			
			if(month!=null) {
				buffer.append(" and month="+month);
				
				if(day!=null) {
					buffer.append(" and day="+day);
				}
			}
		}
		List<Float> mbList = totalDao.query(buffer.toString(), banzu);
		if(mbList!=null && mbList.size()>0) {
			Float mbSum = 0f;
			int mbCount = 0;
			for (Float mb : mbList) {
				if(mb!=null) {
					mbSum+=mb;
					mbCount++;
				}
			}
			if(mbSum!=0 && mbCount!=0) {
				BigDecimal divide = new BigDecimal(mbSum).divide(new BigDecimal(mbCount),3,BigDecimal.ROUND_HALF_UP);
				return divide.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
			}
		}
		return 0f;
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List showCheJian(String banzu, String pageStatus) {
		
		int showMaxLeng=300;//最长显示长度
		if(pageStatus!=null && pageStatus.equals("ajaxPost")) {
			
		}else {
			showMaxLeng = 30;
		}
		List<List> showList = new ArrayList<List>();
		//根据班组获取所有的年份
		List<Integer> yearList = totalDao.query("select distinct year from PebUsers where banzu=? and"
				+ " actualNum is not null and zsNumber is not null order by year ", banzu);
		if(yearList==null || yearList.size()<=0) {
			return null;
		}
		Integer currentYear = Integer.parseInt(Util.getDateTime("yyyy"));
		List<Integer> monthList = totalDao.query("select distinct month from PebUsers where banZu=? "
					+ "and year=? and actualNum is not null and zsNumber is not null order by year,month", banzu,currentYear);
		Integer currentMonth = Integer.parseInt(Util.getDateTime("MM"));
		Integer minMonth = currentMonth-6;
		if(minMonth<=0) {
			minMonth= 12+minMonth;
		}
		List<Integer> dayList = totalDao.query("select distinct day from PebUsers where banzu=? "
				+ "and year=? and month=? and actualNum is not null and zsNumber is not null order by year,month,day", banzu,currentYear,currentMonth);
		
		List detailList = null;
		BigDecimal bigDecimal = null;
		SubTeam subTeam  = (SubTeam) totalDao.getObjectByCondition("from SubTeam where subName = ?",banzu);
		if(subTeam!=null) {
			detailList = new ArrayList();
			detailList.add("2015年");
			detailList.add(subTeam.getChuqinDays2015());
			detailList.add(subTeam.getPersonCount2015());
			if(subTeam.getCcCount2015()!=null && subTeam.getCcCount2015()>99) {
				detailList.add(subTeam.getCcCount2015().intValue());
			}else if(subTeam.getCcCount2015()!=null){
				detailList.add(new DecimalFormat("#.###").format(subTeam.getCcCount2015()));
			}else {
				detailList.add(null);
			}
			if(subTeam.getAvg2015()!=null && subTeam.getAvg2015()>99) {
				detailList.add(subTeam.getAvg2015().intValue());
			}else if(subTeam.getAvg2015()!=null){
				detailList.add(new DecimalFormat("#.###").format(subTeam.getAvg2015()));
			}else {
				detailList.add(null);
			}
			detailList.add(subTeam.getMbCount2015());
			showList.add(detailList);
			
			detailList = new ArrayList();
			detailList.add("2016年");
			detailList.add(subTeam.getChuqinDays2016());
			detailList.add(subTeam.getPersonCount2016());
			if(subTeam.getCcCount2016()!=null && subTeam.getCcCount2016()>99) {
				detailList.add(subTeam.getCcCount2016().intValue());
			}else if(subTeam.getCcCount2016()!=null){
				detailList.add(new DecimalFormat("#.###").format(subTeam.getCcCount2016()));
			}else {
				detailList.add(null);
			}
			if(subTeam.getAvg2016()!=null && subTeam.getAvg2016()>99) {
				detailList.add(subTeam.getAvg2016().intValue());
			}else if(subTeam.getAvg2016()!=null ){
				detailList.add(new DecimalFormat("#.###").format(subTeam.getAvg2016()));
			}else {
				detailList.add(null);
			}
			detailList.add(subTeam.getMbCount2016());
			showList.add(detailList);
			
			detailList = new ArrayList();
			detailList.add("2017年");
			detailList.add(subTeam.getChuqinDays2017());
			detailList.add(subTeam.getPersonCount2017());
			if(subTeam.getCcCount2017()!=null && subTeam.getCcCount2017()>99) {
				detailList.add(subTeam.getCcCount2017().intValue());
			}else if(subTeam.getCcCount2017()!=null){
				detailList.add(new DecimalFormat("#.###").format(subTeam.getCcCount2017()));
			}else {
				detailList.add(null);
			}
			if(subTeam.getAvg2017()!=null && subTeam.getAvg2017()>99) {
				detailList.add(subTeam.getAvg2017().intValue());
			}else if(subTeam.getAvg2017()!=null){
				detailList.add(new DecimalFormat("#.###").format(subTeam.getAvg2017()));
			}else {
				detailList.add(null);
			}
			detailList.add(subTeam.getMbCount2017());
			showList.add(detailList);
		}
		showMaxLeng=showMaxLeng-3;
		for (Integer year : yearList) {
			detailList = new ArrayList();
			detailList.add(year+"年");
			
			//出勤天数
			Integer chuQinDays = getChuQinDays(banzu, year, null, null);
			if(chuQinDays==0) {
				chuQinDays = 1;
				detailList.add("");
			}else {
				if(year==currentYear) {
					Integer monthLength = monthList.size();
					if(monthLength==0) {
						monthLength=1;
					}
					detailList.add(chuQinDays/monthLength);
				}else {
					detailList.add(chuQinDays);
				}
			}
			//detailList.add(chuQinDays);
			//人数
			Float personTotalCount = getPersionCountByBanzu(banzu, year, null, null);
			Float personCount = new BigDecimal(personTotalCount).divide(new BigDecimal(chuQinDays),3,BigDecimal.ROUND_HALF_UP)
								.setScale(1,BigDecimal.ROUND_HALF_UP).floatValue();
			if(personCount==0) {
				personCount = 1f;
			}
			detailList.add(new DecimalFormat("#.###").format(personCount));
			//实际产出(仓)
			BigDecimal zsTotalNumber = getzsNumber(banzu, year, null, null);
			Float zsNumber =zsTotalNumber.divide(new BigDecimal(chuQinDays),3,BigDecimal.ROUND_HALF_UP) 
								.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
			if(zsNumber>99) {
				detailList.add(zsNumber.intValue());
			}else {
				detailList.add(new DecimalFormat("#.###").format(zsNumber));
			}
			
			//实际日人均仓
			if(personCount!=null && personCount!=0) {
				bigDecimal =new BigDecimal(zsNumber).divide(new BigDecimal(personTotalCount).divide(new BigDecimal(chuQinDays)
						,3,BigDecimal.ROUND_HALF_UP),3,BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
				if(bigDecimal!=null && bigDecimal.floatValue()>99) {
					detailList.add(bigDecimal.intValue());
				}else {
					detailList.add(new DecimalFormat("#.###").format(bigDecimal.floatValue()));
				}
			}else{
				detailList.add(null);
			}
			
			//目标日人均仓
			Float mbTarget = getMbTarget(banzu, year, null, null);
			if(mbTarget!=0) {
				if(mbTarget!=null && mbTarget>99) {
					detailList.add(mbTarget.intValue());
				}else {
					detailList.add(mbTarget);
				}
			}
			showList.add(detailList);
			showMaxLeng=showMaxLeng-1;
		}
		for(Integer month : monthList) {
			detailList = new ArrayList();
			detailList.add(currentYear+"<br>年"+month+"月");
			
			//出勤天数
			Integer chuQinDays = getChuQinDays(banzu, currentYear, month,null);
			if(banzu.equals("数据机房")) {
				chuQinDays = 1;
			}
			detailList.add(chuQinDays);
			
			if(chuQinDays==0) {
				continue;
			}
			Float personTotalCount = getPersionCountByBanzu(banzu, currentYear, month, null);
			Float personCount = new BigDecimal(personTotalCount).divide(new BigDecimal(chuQinDays),2,BigDecimal.ROUND_HALF_UP)
								.setScale(0,BigDecimal.ROUND_HALF_UP).floatValue();
			if(personCount==0) {
				continue;
			}
			detailList.add(new DecimalFormat("#.###").format(personCount));
			
			//实际产出(仓)
			BigDecimal zsTotalNumber = getzsNumber(banzu, currentYear, month, null);
			Float zsNumber = zsTotalNumber.divide(new BigDecimal(chuQinDays),2,BigDecimal.ROUND_HALF_UP)
							.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
			if(zsNumber.floatValue()>99) {
				detailList.add(zsNumber.intValue());
			}else {
				detailList.add(new DecimalFormat("#.###").format(zsNumber));
			}
			
			//实际日人均仓
			bigDecimal =new BigDecimal(zsNumber).divide(new BigDecimal(personTotalCount).divide(new BigDecimal(chuQinDays),2,BigDecimal.ROUND_HALF_UP)
					,2,BigDecimal.ROUND_HALF_UP).setScale(3, BigDecimal.ROUND_HALF_UP);
			if(bigDecimal!=null && bigDecimal.floatValue()>99) {
				detailList.add(bigDecimal.intValue());
			}else if(bigDecimal!=null){
				detailList.add(new DecimalFormat("#.###").format(bigDecimal.floatValue()));
			}else {
				detailList.add(null);
			}
			
			//目标日人均仓
			Float mbTarget = getMbTarget(banzu, currentYear, month, null);
			if(mbTarget!=0) {
				if(mbTarget!=null && mbTarget>99) {
					detailList.add(mbTarget.intValue());
				}else {
					detailList.add(mbTarget);
				}
			}
			
			showList.add(detailList);
			showMaxLeng=showMaxLeng-1;
		}
		
		if(pageStatus==null || !pageStatus.equals("ajaxPost")) {
			Float zsAvg = (Float) totalDao.getObjectByCondition(
					"select avg(zsNumber) from PebUsers where year=? and banZu=?", currentYear,banzu);
			if(zsAvg!=null) {
				if(zsAvg>100000) {
					showMaxLeng=showMaxLeng-7;
				}else if(zsAvg>10000){
					showMaxLeng=showMaxLeng-5;
				}else if(zsAvg>1000) {
					showMaxLeng= showMaxLeng-3;
				}
			}
		}
		
		//int showDaymaxLength = showMaxLeng;
		int currentDay = Integer.parseInt(Util.getDateTime("dd"));
		for(Integer day: dayList) {
			if(day<=currentDay-showMaxLeng) {
				continue;
			}
			detailList = new ArrayList();
			detailList.add(day);
			detailList.add(null);

			//人数
			Float personCount =getPersionCountByBanzu(banzu, currentYear, currentMonth, day);
			if(personCount!=null && personCount!=0) {
				detailList.add(new DecimalFormat("#.###").format(personCount));
			}else {
				detailList.add(null);
			}
			
			//实际产出(仓)
			BigDecimal zsTotalNumber = getzsNumber(banzu, currentYear, currentMonth, day).setScale(2,BigDecimal.ROUND_HALF_UP);
			if(zsTotalNumber!=null && zsTotalNumber.floatValue()>0) {
				if(zsTotalNumber.floatValue()>99) {
					detailList.add(zsTotalNumber.setScale(0,BigDecimal.ROUND_HALF_UP).intValue());
				}else {
					detailList.add(new DecimalFormat("#.###").format(zsTotalNumber));
				}
			}else {
				detailList.add(null);
			}
			
			
			//实际日人均仓
			if(personCount!=0) {
				bigDecimal =zsTotalNumber.divide(new BigDecimal(personCount),3,BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
				if(bigDecimal!=null && bigDecimal.floatValue()>99) {
					detailList.add(bigDecimal.intValue());
				}else{
					detailList.add(new DecimalFormat("#.###").format(bigDecimal.floatValue()));
				}
			}else {
				detailList.add(null);
			}
			
			//目标日人均仓
			Float mbTarget = getMbTarget(banzu, currentYear, currentMonth, day);
			if(mbTarget!=0) {
				if(mbTarget!=null && mbTarget>99) {
					detailList.add(mbTarget.intValue());
				}else {
					detailList.add(mbTarget);
				}
			}
			showList.add(detailList);
			if(currentDay==day) {
				break;
			}
		}
		return showList;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<PebJianBao> iteratorSearchJianBao(SubTeam fatherTeam,List<Integer> yearList,
			Integer currentYear,Integer currentMonth,Integer currentDay,Integer belongLayer) {
		List<PebJianBao> jianbaoList = new ArrayList<PebJianBao>();
		List<SubTeam> subTeamList = totalDao.query("from SubTeam where fatherId =?", fatherTeam.getId());
		if(subTeamList!=null && subTeamList.size()>0 ) {
			List<PebJianBao> jianbaoSubList = new ArrayList<PebJianBao>();
			BigDecimal yearAllPersonCount = new BigDecimal(0);
			BigDecimal monthAllPersonCount = new BigDecimal(0);
			for (SubTeam subTeam2 : subTeamList) {
				 List<PebJianBao> jianbaos = iteratorSearchJianBao(subTeam2, yearList, currentYear, currentMonth, currentDay,belongLayer+1);
				 if(jianbaos!=null) {
					 for (PebJianBao pebJianBao : jianbaos) {
						if(pebJianBao!=null) {
							if(subTeam2.getSubName().equals(pebJianBao.banzu)) {//同一层的
								jianbaoSubList.add(pebJianBao);
								
								if(pebJianBao.yearbanzuPersonCount!=null) {
									yearAllPersonCount = yearAllPersonCount.add(new BigDecimal(pebJianBao.yearbanzuPersonCount));
								}
								if(pebJianBao.monthBanZuPersonCount!=null) {
									monthAllPersonCount= monthAllPersonCount.add(new BigDecimal(pebJianBao.monthBanZuPersonCount));
								}
								
							}else {//保存其他的
								if(!jianbaoList.contains(pebJianBao)) {
									jianbaoList.add(pebJianBao);
								}
							}
						}
					}
				 }
				
				
			}
			
			for (PebJianBao pebJianBao : jianbaoSubList) {
				pebJianBao.yearallPersonCount = yearAllPersonCount.intValue();
				pebJianBao.monthAllPersonCount = monthAllPersonCount.intValue();
				
				if(pebJianBao.yearZengZhang!=null && pebJianBao.yearbanzuPersonCount!=null) {
					pebJianBao.yearZhanbi = new BigDecimal(pebJianBao.yearZengZhang).multiply(
							new BigDecimal(pebJianBao.yearbanzuPersonCount).divide(
									new BigDecimal(pebJianBao.yearallPersonCount),4,BigDecimal.ROUND_HALF_UP))
							.setScale(4, BigDecimal.ROUND_HALF_UP).floatValue();
				}
				
				if(pebJianBao.monthBanZuPersonCount!=null && pebJianBao.monthZengZhang!=null ) {
					
					Float zhanbi = new BigDecimal(pebJianBao.monthZengZhang).multiply(new BigDecimal(pebJianBao.monthBanZuPersonCount)
							.divide(new BigDecimal(pebJianBao.monthAllPersonCount),4,BigDecimal.ROUND_HALF_UP))
							.setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
					pebJianBao.monthZhanbi = zhanbi;
				}
//				pebJianBao.yearZhanbi = new BigDecimal(pebJianBao.yearZengZhang).multiply(
//						yearAllPersonCount.divide(yearAllPersonCount,3,BigDecimal.ROUND_HALF_UP)).
//						setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
//				pebJianBao.monthZhanbi = new BigDecimal(pebJianBao.monthZengZhang).multiply(
//						monthAllPersonCount.divide(monthAllPersonCount,3,BigDecimal.ROUND_HALF_UP)).
//						setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
			}
			
			if( jianbaoSubList.size()==1 || fatherTeam.getIsBanzu().equals("工序")) {
				jianbaoSubList.get(0).bcolorStyle = "display:none;";
			}else {
//					//月度排行
				jianbaoSubList = maoPaoOrder(jianbaoSubList, "month");
				jianbaoSubList = getPaiHang(jianbaoSubList, "month");

//					//年度排行
				jianbaoSubList = maoPaoOrder(jianbaoSubList, "year");
				jianbaoSubList = getPaiHang(jianbaoSubList, "year");
			}
			jianbaoList.addAll(jianbaoSubList);
			PebJianBao fenChangData = getFenChangData(fatherTeam, yearList, currentYear, currentMonth, currentDay, jianbaoList);
			if(belongLayer==1) {
				fenChangData.bcolorStyle = "background-color:rgb(153,204,0);";
			}else  if(belongLayer==2){
				fenChangData.bcolorStyle = "background-color:rgb(216,228,188);";
			}else {
				
			}
			jianbaoList.add(fenChangData);
		}else {
			PebJianBao jianBaoData = getJianBaoData(fatherTeam, yearList, currentYear, currentMonth, currentDay);
			jianbaoList.add(jianBaoData);
		}
		return jianbaoList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List getJianBaoTopList(List<Integer> yearList,int currentYear,int currentMonth,int currentDay) {
		List topDetailList =new ArrayList();
		topDetailList.add(null);
		topDetailList.add("车间班组");
		topDetailList.add("负责人");
		topDetailList.add("2015<br>年平均");
		topDetailList.add("2016<br>年平均");
		topDetailList.add("2017<br>年平均");
		for (int year : yearList) {
			if(year==currentYear) {
				topDetailList.add(year+"<br>年基数");
				topDetailList.add(year+"<br>年目标");
			}
			topDetailList.add(year+"<br>年平均");
			if(year==currentYear) {
				topDetailList.add(year+"年<br>实际<br>年增长");
				topDetailList.add("累计年<br>效率增长");
			}
			if(year==currentYear-1) {
				topDetailList.add(year+"年<br>目标达成");
			}
		}
		topDetailList.add("班组<br>人数");
		topDetailList.add("全体<br>人数");
		topDetailList.add("占比");
		topDetailList.add("年度<br>排名");
		topDetailList.add(currentYear+"年"+currentMonth+"月<br>平均");
		topDetailList.add(currentDay+"日");
		topDetailList.add("月效率<br>增长("+currentYear+"<br>年"+currentMonth+"月)");
		topDetailList.add("班组<br>人数");
		topDetailList.add("全体<br>人数");
		topDetailList.add("月占比");
		topDetailList.add("月度<br>排名");
		topDetailList.add("UPPH");
		topDetailList.add("备注");
		return topDetailList;
	}

	/**
	 * 这里显示的是总生产效率简报
	 * 因为显示的逻辑问题不好直接用showJianBao方法调用
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List showPebJianBao(String pageStatus) {
		List<List> showList = new ArrayList<List>();
		List<Integer> yearList = totalDao.query("select distinct year from PebUsers"
				+ " where zsNumber is not null and actualNum is not null order by year");
		if(yearList.size()<=0) {
			return null;
		}
		List<PebJianBao> pebJianBaoList = new ArrayList<PebJianBao>();
		int currentYear = Integer.parseInt(Util.getDateTime("yyyy"));
		int currentMonth =Integer.parseInt( Util.getDateTime("MM"));
		int currentDay = Integer.parseInt(Util.getDateTime("dd"));
		currentDay = getCurrentDay(currentYear, currentMonth, currentDay,null);
		if(!yearList.contains(currentYear)) {
			yearList.add(currentYear);
		}
		showList.add(getJianBaoTopList(yearList,currentYear,currentMonth,currentDay));
		
//		SubTeam rootSubTeam = (SubTeam) totalDao.getObjectByCondition("from SubTeam where subName=?", pageStatus);
//		List<PebJianBao> jianBao2 = iteratorSearchJianBao(rootSubTeam, yearList, currentYear, currentMonth, currentDay);
		
		//-----------------------------------------------车间信息
		List<PebJianBao> chejianPebJianBao = new ArrayList<PebJianBao>();
		List<SubTeam> chejianList = totalDao.query("from SubTeam where isBanzu ='车间'");
		for (SubTeam subTeam : chejianList) {
			PebJianBao pebJianBao=null;
			if(subTeam.getSubName().equals("数据机房")) {
				pebJianBao = getShuJuJFData(subTeam,yearList,currentYear,currentMonth,currentDay);
			}else {
				List<SubTeam> subTeamList = totalDao.query("from SubTeam where fatherId = ?", subTeam.getId());
				if(subTeamList!=null && subTeamList.size()>0) {
					List<PebJianBao> pebJianBaos = new ArrayList<PebJianBao>();
					Integer yearPersonAllCount = 0;
					Integer monthPersonAllCount = 0;
					List<SubTeam> list = totalDao.query("from SubTeam where isBanzu = '工序'");
					for (SubTeam subTeam2 : list) {
						Float chejianCount = getPersionCountByBanzu(subTeam2.getSubName(), currentYear,null, null);
						Integer chejianCqCount =getChuQinDays(subTeam2.getSubName(), currentYear, null, null);
						if(chejianCqCount==0) {
							continue;
						}
						yearPersonAllCount+= new BigDecimal(chejianCount).divide(new BigDecimal(chejianCqCount),0,BigDecimal.ROUND_HALF_UP).intValue();
					}
					for (SubTeam subTeam2 : list) {
						Float chejianCount = getPersionCountByBanzu(subTeam2.getSubName(), currentYear,currentMonth, null);
						Integer chejianCqCount =getChuQinDays(subTeam2.getSubName(), currentYear, currentMonth, null);
						if(chejianCqCount==0) {
							continue;
						}
						monthPersonAllCount+= new BigDecimal(chejianCount).divide(new BigDecimal(chejianCqCount),0,BigDecimal.ROUND_HALF_UP).intValue();
					}
					for (SubTeam st : subTeamList) {
						PebJianBao jianBaoData = getJianBaoData(st, yearList, currentYear, currentMonth, currentDay);
						pebJianBaos.add(jianBaoData);
					}
					pebJianBao = getFenChangData(subTeam, yearList, currentYear, currentMonth, currentDay, pebJianBaos);
					//处理年份
					pebJianBao.yearallPersonCount = yearPersonAllCount;
					if(pebJianBao.yearZengZhang!=null && yearPersonAllCount!=0) {
						pebJianBao.yearZhanbi = new BigDecimal(pebJianBao.yearZengZhang).multiply(
								new BigDecimal(pebJianBao.yearbanzuPersonCount).divide(new BigDecimal(yearPersonAllCount),3,BigDecimal.ROUND_HALF_UP)).
								setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
						
					}
					//处理月份
					pebJianBao.monthAllPersonCount = monthPersonAllCount;
					if(pebJianBao.monthZengZhang!=null && pebJianBao.monthBanZuPersonCount!=null) {
						pebJianBao.monthZhanbi = new BigDecimal(pebJianBao.monthZengZhang).multiply(
								new BigDecimal(pebJianBao.monthBanZuPersonCount).divide(new BigDecimal(monthPersonAllCount),3,BigDecimal.ROUND_HALF_UP)).
								setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
					}
				}else {
					pebJianBao = getJianBaoData(subTeam, yearList, currentYear, currentMonth, currentDay);
				}
			}
			chejianPebJianBao.add(pebJianBao);
		}
//		//月度排行
		chejianPebJianBao = maoPaoOrder(chejianPebJianBao, "month");
		chejianPebJianBao = getPaiHang(chejianPebJianBao, "month");

//		//年度排行
		chejianPebJianBao = maoPaoOrder(chejianPebJianBao, "year");
		chejianPebJianBao = getPaiHang(chejianPebJianBao, "year");
		pebJianBaoList.addAll(chejianPebJianBao);
		//----------------------------------------------分厂信息
		List<PebJianBao> fenChangPebJianBao = new ArrayList();
		List<SubTeam> fenchangList = totalDao.query("from SubTeam where isBanzu ='分厂'");
		for (SubTeam subTeam : fenchangList) {
			List<PebJianBao> subPebJianBao = new ArrayList<PebJianBao>();
			List<SubTeam> subCheJianTeamList = totalDao.query("from SubTeam where fatherId =?", subTeam.getId());
			for (SubTeam subCheJianTeam : subCheJianTeamList) {
				for (PebJianBao chejian : chejianPebJianBao) {
					if(chejian.banzu.equals(subCheJianTeam.getSubName())) {
						subPebJianBao.add(chejian);
					}
				}
			}
			PebJianBao fenChangData = getFenChangData(subTeam, yearList, currentYear, currentMonth, currentDay, subPebJianBao);
			fenChangData.bcolorStyle = "background-color:rgb(216,228,188);";
			fenChangPebJianBao.add(fenChangData);
		}
		
		//月度排行
		fenChangPebJianBao = maoPaoOrder(fenChangPebJianBao, "month");
		fenChangPebJianBao = getPaiHang(fenChangPebJianBao, "month");
		
		
		//年度排行
		fenChangPebJianBao = maoPaoOrder(fenChangPebJianBao, "year");
		fenChangPebJianBao = getPaiHang(fenChangPebJianBao, "year");
		
		pebJianBaoList.addAll(fenChangPebJianBao);
		
		//制造部
		PebJianBao pbj = new PebJianBao();
		pbj.banzu = "制造部";
		pbj.fuzeren ="王志伟";
		BigDecimal yearZengzhangDecimal = new BigDecimal(0);
		BigDecimal monthZengZhangDecimal = new BigDecimal(0);
		BigDecimal yearAvgDecmal = new BigDecimal(0);
		BigDecimal yearZengZhangDecimal = new BigDecimal(0);
		BigDecimal yearBanzuPersonCount = new BigDecimal(0);
		
		BigDecimal monthAvgDecimal = new BigDecimal(0);
		BigDecimal dayAvgDecimal = new BigDecimal(0);
		BigDecimal monthBanZuPersonCount = new BigDecimal(0);
		BigDecimal monthAllPersonCount = new BigDecimal(0);
//		BigDecimal avg2015Decimal = new BigDecimal(0);
//		BigDecimal avg2016Decimal = new BigDecimal(0);
//		BigDecimal avg2017Decimal = new BigDecimal(0);
		for (PebJianBao jianbao : fenChangPebJianBao) {
			if(jianbao.yearZhanbi!=null) {
				yearZengzhangDecimal = yearZengzhangDecimal.add(new BigDecimal(jianbao.yearZhanbi));
			}
			if(jianbao.monthZhanbi!=null) {
				monthZengZhangDecimal =monthZengZhangDecimal.add(new BigDecimal(jianbao.monthZhanbi));
			}
//			
//			if(jianbao.avg2015!=null) {
//				avg2015Decimal = avg2015Decimal.add(new BigDecimal(jianbao.avg2015));
//			}
//			if(jianbao.avg2016!=null) {
//				avg2016Decimal = avg2016Decimal.add(new BigDecimal(jianbao.avg2016));
//			}
//			if(jianbao.avg2017!=null) {
//				avg2017Decimal = avg2017Decimal.add(new BigDecimal(jianbao.avg2017));
//			}
			if(jianbao.yearbanzuPersonCount!=null ) {
				yearBanzuPersonCount = yearBanzuPersonCount.add(new BigDecimal(jianbao.yearbanzuPersonCount));
			}
			if(jianbao.monthBanZuPersonCount!=null) {
				monthBanZuPersonCount = monthBanZuPersonCount.add(new BigDecimal(jianbao.monthBanZuPersonCount));
			}
			if(jianbao.yearAvg!=null) {
				yearAvgDecmal = yearAvgDecmal.add(new BigDecimal(jianbao.yearAvg));
			}
			if(jianbao.yearZhanbi!=null) {
				yearZengZhangDecimal = yearZengZhangDecimal.add(new BigDecimal(jianbao.yearZhanbi));
			}
			if(jianbao.monthAvg!=null && jianbao.monthZhanbi!=null) {
				monthAvgDecimal = monthAvgDecimal.add(new BigDecimal(jianbao.monthAvg));
			}
			if(jianbao.dayAvg!=null) {
				dayAvgDecimal = dayAvgDecimal.add(new BigDecimal(jianbao.dayAvg));
			}
		}
		pbj.yearAvg = yearAvgDecmal.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
		
		//年效率增长
		pbj.yearZengZhang = yearZengZhangDecimal.floatValue();
		
		pbj.yearbanzuPersonCount = yearBanzuPersonCount.intValue();
		pbj.yearallPersonCount = pbj.yearbanzuPersonCount;
		
//		//年占比
//		if(yearBanzuPersonCount.intValue()!=0 && pbj.yearallPersonCount!=0) {
//			pbj.yearZhanbi = yearZengZhangDecimal.multiply(
//					yearBanzuPersonCount.divide(new BigDecimal(pbj.yearallPersonCount),3,BigDecimal.ROUND_HALF_UP)).
//					setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
//		}
		
		//月效率增长
		pbj.monthZengZhang = monthZengZhangDecimal.floatValue();
		
		
		//月平均
		pbj.monthAvg = monthAvgDecimal.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
		
		//日平均
		pbj.dayAvg = dayAvgDecimal.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
		//月班组人数
		pbj.monthBanZuPersonCount = monthBanZuPersonCount.intValue();
		//月全体人数
		pbj.monthAllPersonCount = pbj.monthBanZuPersonCount;
		
		//月占比
		if(monthAllPersonCount.intValue()!=0 && monthBanZuPersonCount.intValue()!=0) {
			pbj.monthZhanbi = monthZengZhangDecimal.multiply(
					monthBanZuPersonCount.divide(monthAllPersonCount,3,BigDecimal.ROUND_HALF_UP))
					.setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
		}
		pbj.yearZengZhang = yearZengzhangDecimal.setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
		pbj.monthZengZhang = monthZengZhangDecimal.setScale(4,BigDecimal.ROUND_HALF_UP).floatValue() ;
		
//		pbj.avg2015 = avg2015Decimal.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
//		pbj.avg2016 = avg2016Decimal.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
//		pbj.avg2017 = avg2017Decimal.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
//		pbj.avg2015 = ;
//		pbj.avg2016 = ;
//		pbj.avg2017 = ;
		pbj.historyYearList=new ArrayList<Float>();
		pbj.historyYearList.add(null);
		pbj.lastYearTargetreach = 0.3274f;
		//上一年目标达成
//		if(pbj.avg2017!=null && pbj.avg2016!=null && pbj.avg2017!=0) {
//			//目标达成2017年平均/2016年平均-1
//			pbj.lastYearTargetreach = new BigDecimal(pbj.avg2017).divide(
//					new BigDecimal(pbj.avg2016),4,BigDecimal.ROUND_HALF_UP)
//					.subtract(new BigDecimal(1)).setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
//		}
		//实际年增长（今年年效率增长-上一年目标达成）+0.3
		if(pbj.yearZengZhang!=null && pbj.lastYearTargetreach!=null) {
			pbj.yearsjZengZhang = new BigDecimal(pbj.yearZengZhang)
					.subtract(new BigDecimal(pbj.lastYearTargetreach))
					.add(new BigDecimal(0.3)).setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
		}
		pbj.bcolorStyle = "background-color:rgb(153,204,0);";
		
		pebJianBaoList.add(pbj);
		
		showList.addAll(formatJianBao(pebJianBaoList));
		
//		List detailList = null;
//		for (PebJianBao pebJianBao : pebJianBaoList) {
//			detailList = new ArrayList();
//			detailList.add(pebJianBao.bcolorStyle);
//			if (pebJianBao.bcolorStyle!=null) {
//				detailList.add(pebJianBao.banzu);
//			}else {
//				detailList.add("<span class='subBanzu'>"+pebJianBao.banzu+"</span>");
//			}
//		
//			detailList.add(pebJianBao.fuzeren);
//			if(pebJianBao.avg2015!=null && pebJianBao.avg2015>99) {
//				detailList.add(new BigDecimal(pebJianBao.avg2015).setScale(0,BigDecimal.ROUND_HALF_UP).intValue());
//			}else {
//				if(pebJianBao.avg2015!=null) {
//					detailList.add(new DecimalFormat(decimalFormatPlace).format(pebJianBao.avg2015));
//				}else {
//					detailList.add(null);
//				}
//			}
//			if(pebJianBao.avg2016!=null && pebJianBao.avg2016>99) {
//				detailList.add(new BigDecimal(pebJianBao.avg2016).setScale(0,BigDecimal.ROUND_HALF_UP).intValue());
//			}else {
//				if(pebJianBao.avg2016!=null) {
//					detailList.add(new DecimalFormat(decimalFormatPlace).format(pebJianBao.avg2016));
//				}else {
//					detailList.add(null);
//				}
//			}
//			if(pebJianBao.avg2017 !=null &&pebJianBao.avg2017>99) {
//				detailList.add(new BigDecimal(pebJianBao.avg2017).setScale(0,BigDecimal.ROUND_HALF_UP).intValue());
//			}else {
//				if(pebJianBao.avg2017!=null) {
//					detailList.add(new DecimalFormat(decimalFormatPlace).format(pebJianBao.avg2017));
//				}else {
//					detailList.add(null);
//				}
//			}
//			//上一年目标达成
//			if(pebJianBao.lastYearTargetreach!=null && pebJianBao.lastYearTargetreach!=null) {
//				detailList.add(new BigDecimal(pebJianBao.lastYearTargetreach).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()+"%");
//			}else {
//				detailList.add(null);
//			}
//			if(pebJianBao.yearjishu !=null && pebJianBao.yearjishu>99) {
//				detailList.add(new BigDecimal(pebJianBao.yearjishu).setScale(0,BigDecimal.ROUND_HALF_UP).intValue());
//			}else {
//				if(pebJianBao.yearjishu!=null) {
//					detailList.add(new DecimalFormat(decimalFormatPlace).format(pebJianBao.yearjishu));
//				}else {
//					detailList.add(null);
//				}
//			}
//			if(pebJianBao.yearTarget !=null && pebJianBao.yearTarget>99) {
//				detailList.add(new BigDecimal(pebJianBao.yearTarget).setScale(0,BigDecimal.ROUND_HALF_UP).intValue());
//			}else {
//				if(pebJianBao.yearTarget!=null) {
//					detailList.add(new DecimalFormat(decimalFormatPlace).format(pebJianBao.yearTarget));
//				}else {
//					detailList.add(null);
//				}
//			}
//			if(pebJianBao.yearAvg !=null && pebJianBao.yearAvg>99) {
//				detailList.add(new BigDecimal(pebJianBao.yearAvg).setScale(0,BigDecimal.ROUND_HALF_UP).intValue());
//			}else {
//				if(pebJianBao.yearAvg!=null) {
//					detailList.add(new DecimalFormat(decimalFormatPlace).format(pebJianBao.yearAvg));
//				}else {
//					detailList.add(null);
//				}
//			}
//			if(pebJianBao.yearsjZengZhang!=null) {
//				detailList.add(new BigDecimal(pebJianBao.yearsjZengZhang).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()+"%");
//			}else {
//				detailList.add(null);
//			}
//			if(pebJianBao.yearZengZhang!=null) {
//				detailList.add(new BigDecimal(pebJianBao.yearZengZhang).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()+"%");
//			}else {
//				detailList.add(null);
//			}
//			detailList.add(pebJianBao.yearbanzuPersonCount);
//			detailList.add(pebJianBao.yearallPersonCount);
//			if(pebJianBao.yearZhanbi!=null) {
//				detailList.add(new BigDecimal(pebJianBao.yearZhanbi).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue()+"%");
//			}else {
//				detailList.add(null);
//			}
//			detailList.add(pebJianBao.yearOrder);
//			if(pebJianBao.monthAvg!=null && pebJianBao.monthAvg>99) {
//				detailList.add(new BigDecimal(pebJianBao.monthAvg).setScale(0,BigDecimal.ROUND_HALF_UP).intValue());
//			}else {
//				if(pebJianBao.monthAvg!=null) {
//					detailList.add(new DecimalFormat(decimalFormatPlace).format(pebJianBao.monthAvg));
//				}else {
//					detailList.add(0);
//				}
//			}
//			if(pebJianBao.dayAvg !=null && pebJianBao.dayAvg>99) {
//				detailList.add(new BigDecimal(pebJianBao.dayAvg).setScale(0,BigDecimal.ROUND_HALF_UP).intValue());
//			}else if(pebJianBao.dayAvg!=null){
//				detailList.add(new DecimalFormat(decimalFormatPlace).format(pebJianBao.dayAvg));
//			}else {
//				detailList.add(0);
//			}
//			if(pebJianBao.monthZengZhang!=null) {
//				detailList.add(new BigDecimal(pebJianBao.monthZengZhang).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()+"%");
//			}else {
//				detailList.add(null);
//			}
//			detailList.add(pebJianBao.monthBanZuPersonCount);
//			detailList.add(pebJianBao.monthAllPersonCount);
//			if(pebJianBao.monthZhanbi!=null) {
//				detailList.add(new BigDecimal(pebJianBao.monthZhanbi).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue()+"%");
//			}else {
//				detailList.add(null);
//			}
//			detailList.add(pebJianBao.monthOrder);
//			detailList.add(pebJianBao.UPPH);
//			detailList.add(pebJianBao.remark);
//			showList.add(detailList);
//		}
		return showList;
	}
	
	@SuppressWarnings("unchecked")
	private List<List> formatJianBao(List<PebJianBao> pebJianBaoList){
		List detailList = null;
		List<List> showList = new ArrayList<List>();
		for (PebJianBao pebJianBao : pebJianBaoList) {
			detailList = new ArrayList();
			detailList.add(pebJianBao.bcolorStyle);
			if (pebJianBao.bcolorStyle!=null) {
				detailList.add(pebJianBao.banzu);
			}else {
				detailList.add("<span class='subBanzu'>"+pebJianBao.banzu+"</span>");
			}
			detailList.add(pebJianBao.fuzeren);
			if(pebJianBao.avg2015!=null && pebJianBao.avg2015>99) {
				detailList.add(new BigDecimal(pebJianBao.avg2015).setScale(0,BigDecimal.ROUND_HALF_UP).intValue());
			}else {
				if(pebJianBao.avg2015!=null) {
					detailList.add(new DecimalFormat(decimalFormatPlace).format(pebJianBao.avg2015));
				}else {
					detailList.add(null);
				}
			}
			if(pebJianBao.avg2016!=null && pebJianBao.avg2016>99) {
				detailList.add(new BigDecimal(pebJianBao.avg2016).setScale(0,BigDecimal.ROUND_HALF_UP).intValue());
			}else {
				if(pebJianBao.avg2016!=null) {
					detailList.add(new DecimalFormat(decimalFormatPlace).format(pebJianBao.avg2016));
				}else {
					detailList.add(null);
				}
			}
			if(pebJianBao.avg2017 !=null &&pebJianBao.avg2017>99) {
				detailList.add(new BigDecimal(pebJianBao.avg2017).setScale(0,BigDecimal.ROUND_HALF_UP).intValue());
			}else {
				if(pebJianBao.avg2017!=null) {
					detailList.add(new DecimalFormat(decimalFormatPlace).format(pebJianBao.avg2017));
				}else {
					detailList.add(null);
				}
			}
			if(pebJianBao.historyYearList!=null && pebJianBao.historyYearList.size()>0) {
				for (Float historyYear : pebJianBao.historyYearList) {
					if(historyYear!=null) {
						if(historyYear>99) {
							detailList.add(new BigDecimal(historyYear).setScale(0,BigDecimal.ROUND_HALF_UP).intValue());
						}else {
							detailList.add(new DecimalFormat(decimalFormatPlace).format(historyYear));
						}
					}else {
						detailList.add(null);
					}
				}
			}
			//上一年目标达成
			if(pebJianBao.lastYearTargetreach!=null && pebJianBao.lastYearTargetreach!=null) {
				detailList.add(new BigDecimal(pebJianBao.lastYearTargetreach).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()+"%");
			}else {
				detailList.add(null);
			}
			if(pebJianBao.yearjishu !=null && pebJianBao.yearjishu>99) {
				detailList.add(new BigDecimal(pebJianBao.yearjishu).setScale(0,BigDecimal.ROUND_HALF_UP).intValue());
			}else {
				if(pebJianBao.yearjishu!=null && pebJianBao.yearjishu>0) {
					detailList.add(new DecimalFormat(decimalFormatPlace).format(pebJianBao.yearjishu));
				}else {
					detailList.add(null);
				}
			}
			if(pebJianBao.yearTarget !=null && pebJianBao.yearTarget>99) {
				detailList.add(new BigDecimal(pebJianBao.yearTarget).setScale(0,BigDecimal.ROUND_HALF_UP).intValue());
			}else {
				if(pebJianBao.yearTarget!=null) {
					detailList.add(new DecimalFormat(decimalFormatPlace).format(pebJianBao.yearTarget));
				}else {
					detailList.add(null);
				}
			}
			if(pebJianBao.yearAvg !=null && pebJianBao.yearAvg>99) {
				detailList.add(new BigDecimal(pebJianBao.yearAvg).setScale(0,BigDecimal.ROUND_HALF_UP).intValue());
			}else {
				if(pebJianBao.yearAvg!=null && pebJianBao.yearAvg>0) {
					detailList.add(new DecimalFormat(decimalFormatPlace).format(pebJianBao.yearAvg));
				}else {
					detailList.add(null);
				}
			}
			if(pebJianBao.yearsjZengZhang!=null) {
				detailList.add(new BigDecimal(pebJianBao.yearsjZengZhang).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()+"%");
			}else {
				detailList.add(null);
			}
			if(pebJianBao.yearZengZhang!=null) {
				detailList.add(new BigDecimal(pebJianBao.yearZengZhang).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()+"%");
			}else {
				detailList.add(null);
			}
			detailList.add(pebJianBao.yearbanzuPersonCount);
			detailList.add(pebJianBao.yearallPersonCount);
			if(pebJianBao.yearZhanbi!=null) {
				detailList.add(new BigDecimal(pebJianBao.yearZhanbi).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue()+"%");
			}else {
				detailList.add(null);
			}
			detailList.add(pebJianBao.yearOrder);
			if(pebJianBao.monthAvg!=null && pebJianBao.monthAvg>99) {
				detailList.add(new BigDecimal(pebJianBao.monthAvg).setScale(0,BigDecimal.ROUND_HALF_UP).intValue());
			}else {
				if(pebJianBao.monthAvg!=null) {
					detailList.add(new DecimalFormat(decimalFormatPlace).format(pebJianBao.monthAvg));
				}else {
					detailList.add(0);
				}
			}
			if(pebJianBao.dayAvg !=null && pebJianBao.dayAvg>99) {
				detailList.add(new BigDecimal(pebJianBao.dayAvg).setScale(0,BigDecimal.ROUND_HALF_UP).intValue());
			}else if(pebJianBao.dayAvg!=null){
				detailList.add(new DecimalFormat(decimalFormatPlace).format(pebJianBao.dayAvg));
			}else {
				detailList.add(0);
			}
			if(pebJianBao.monthZengZhang!=null) {
				detailList.add(new BigDecimal(pebJianBao.monthZengZhang).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()+"%");
			}else {
				detailList.add(null);
			}
			detailList.add(pebJianBao.monthBanZuPersonCount);
			detailList.add(pebJianBao.monthAllPersonCount);
			if(pebJianBao.monthZhanbi!=null) {
				detailList.add(new BigDecimal(pebJianBao.monthZhanbi).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue()+"%");
			}else {
				detailList.add(null);
			}
			detailList.add(pebJianBao.monthOrder);
			detailList.add(pebJianBao.UPPH);
			detailList.add(pebJianBao.remark);
			showList.add(detailList);
		}
		return showList;
	}
	//数据机房以月为单位计算
	private PebJianBao getShuJuJFData(SubTeam subTeam,List<Integer> yearList, int currentYear, int currentMonth, int currentDay) {
		PebJianBao pebJianBao = new PebJianBao();
		BigDecimal bigDecimal = null;//通用的Bigdecimal
		pebJianBao.banzu = subTeam.getSubName();
		pebJianBao.fuzeren = subTeam.getPrincipals();
		pebJianBao.avg2015= subTeam.getAvg2015();
		pebJianBao.avg2016 = subTeam.getAvg2016();
		pebJianBao.avg2017 = subTeam.getAvg2017();
		for (int year : yearList) {
			Float personCount =getPersionCountByBanzu(subTeam.getSubName(), year, null, null);
			Float yearSumNumber = (Float) totalDao.getObjectByCondition("select sum(zsNumber) from PebUsers "
					+ " where zsNumber is not null and banZu=? and year=? ",subTeam.getSubName(),year);
			if(year==currentYear){
				pebJianBao.lastYearJishu = new BigDecimal(pebJianBao.avg2017);//数据机房的比较特殊，2018年基数为2017年平均
				//2019年基数为2018年目标，2019年基数为2018年目标，2018年目标为2017*1.3
				pebJianBao.yearjishu = pebJianBao.lastYearJishu.multiply(new BigDecimal(1.3)).floatValue();
				pebJianBao.yearTarget = pebJianBao.lastYearJishu.multiply(new BigDecimal(1.3)).multiply(new BigDecimal(1.3)).floatValue();
			}
			if(personCount>0 && yearSumNumber>0) {
				if(pebJianBao.historyYearList==null) {
					pebJianBao.historyYearList = new ArrayList<Float>();
				}
				
				
				if(year==currentYear-1) {
					//每年年平均
					Float yearAvg = new BigDecimal(yearSumNumber).divide(new BigDecimal(personCount),5,BigDecimal.ROUND_HALF_UP)
							.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
					pebJianBao.historyYearList.add(yearAvg);
					//去年年目标达成
					Float lastYearAvg = pebJianBao.historyYearList.get(pebJianBao.historyYearList.size()-1);
					Float beforeYearAvg = pebJianBao.avg2017;
					pebJianBao.lastYearTargetreach = new BigDecimal(lastYearAvg).divide(
							new BigDecimal(beforeYearAvg),4,BigDecimal.ROUND_HALF_UP)
							.subtract(new BigDecimal(1)).setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
					
				}else if(year==currentYear){
					//年平均
					if(personCount>0 && yearSumNumber>0) {
						bigDecimal = new BigDecimal(yearSumNumber).divide(new BigDecimal(personCount),5,BigDecimal.ROUND_HALF_UP)
								.setScale(3, BigDecimal.ROUND_HALF_UP);
						pebJianBao.yearAvg=bigDecimal.floatValue();
						//年效率增长
						pebJianBao.yearZengZhang = bigDecimal.divide(new BigDecimal(pebJianBao.yearjishu),4,BigDecimal.ROUND_CEILING)
								.subtract(new BigDecimal(1)).setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
						//实际年增长（今年年效率增长-上一年目标达成）+0.3
						if(pebJianBao.yearZengZhang!=null && pebJianBao.lastYearTargetreach!=null) {
							pebJianBao.yearsjZengZhang = new BigDecimal(pebJianBao.yearZengZhang)
									.subtract(new BigDecimal(pebJianBao.lastYearTargetreach))
									.add(new BigDecimal(0.3)).setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
						}
					}
					
					
					//年班组人数
					Float banzuPersonCount = getPersionCountByBanzu(subTeam.getSubName(), currentYear, null, null);
					pebJianBao.yearbanzuPersonCount = new BigDecimal(banzuPersonCount).intValue();
					pebJianBao.yearallPersonCount = pebJianBao.yearbanzuPersonCount;
					
					pebJianBao.yearZhanbi = pebJianBao.yearZengZhang;
					
					//月平均
					Float personMonthCount =getPersionCountByBanzu(subTeam.getSubName(), currentYear, currentMonth, null);
					Float yearMonthSumNumber = (Float) totalDao.getObjectByCondition("select sum(zsNumber) from PebUsers "
							+ " where zsNumber is not null and banZu=? and year=? and month=?",subTeam.getSubName(),currentYear,currentMonth);
					if(personCount!=0 && yearMonthSumNumber!=0) {
						bigDecimal = new BigDecimal(yearMonthSumNumber).divide(new BigDecimal(personMonthCount),5,BigDecimal.ROUND_HALF_UP)
								.setScale(3, BigDecimal.ROUND_HALF_UP);
						pebJianBao.monthAvg=bigDecimal.floatValue();
					}
					
					//日平均
					Float personDayCount =getPersionCountByBanzu(subTeam.getSubName(), currentYear, currentMonth, currentDay);
					Float yearDaySumNumber = (Float) totalDao.getObjectByCondition("select sum(zsNumber) from PebUsers "
							+ " where zsNumber is not null and banZu=? and year=? and month=? and day=?",
							subTeam.getSubName(),currentYear,currentMonth,currentDay);
					if(personDayCount!=0 && yearDaySumNumber!=0) {
						bigDecimal = new BigDecimal(yearDaySumNumber).divide(new BigDecimal(personDayCount),5,BigDecimal.ROUND_HALF_UP)
								.setScale(3, BigDecimal.ROUND_HALF_UP);
						pebJianBao.dayAvg=bigDecimal.floatValue();
					}
					
					//月效率增长--这里的数据机房的数据，按照月份为单位，简化为一天计算。
					if(pebJianBao.yearTarget!=null && bigDecimal!=null) {
						pebJianBao.monthZengZhang = bigDecimal.divide(new BigDecimal(pebJianBao.yearTarget),4,BigDecimal.ROUND_CEILING)
								.subtract(new BigDecimal(1)).setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
					}
					
					//月班组人数
					Float banzuPersonCountMonth = getPersionCountByBanzu(subTeam.getSubName(), currentYear, currentMonth, null);
					pebJianBao.monthBanZuPersonCount = new BigDecimal(banzuPersonCountMonth).intValue();
					pebJianBao.monthAllPersonCount = pebJianBao.monthBanZuPersonCount;
					

					pebJianBao.monthZhanbi = pebJianBao.monthZengZhang;
				}
			}
		}
		
		
		
		return pebJianBao;
		
		
	}

	@SuppressWarnings("unchecked")
	private PebJianBao getBanjinData(SubTeam subTeam,Integer currentYear,Integer currentMonth,Integer currentDay) {
		PebJianBao pebJianBao = new PebJianBao();
		pebJianBao.banzu = subTeam.getSubName();
		pebJianBao.fuzeren = subTeam.getPrincipals();
		List<SubTeam> processList = totalDao.query("from SubTeam where isBanzu = '工序' and fatherId = ?", subTeam.getId());
		List<SubTeam> chejianList = totalDao.query("from SubTeam where fatherId = (select fatherId from SubTeam where id =?)", subTeam.getId());
		//年效率增长
		List<PebJianBao> chejianPebJianBao = new ArrayList<PebJianBao>();
		for (SubTeam cjl : processList) {
			List<Integer> yearList = totalDao.query("select distinct year from PebUsers where banzu=? and"
					+ " actualNum is not null and zsNumber is not null order by year ", cjl.getSubName());
			PebJianBao pbj=null;
			pbj = getJianBaoData(cjl, yearList, currentYear, currentMonth, currentDay);
			chejianPebJianBao.add(pbj);
		}
		BigDecimal yearZengZhang = new BigDecimal(0);
		BigDecimal avg2015Decimal = new BigDecimal(0);
		BigDecimal avg2016Decimal = new BigDecimal(0);
		BigDecimal avg2017Decimal = new BigDecimal(0);
		for (PebJianBao pjb : chejianPebJianBao) {
			if(pjb.yearZhanbi!=null) {
				yearZengZhang = yearZengZhang.add(new BigDecimal(pjb.yearZhanbi));
			}
			if(pjb.avg2015!=null) {
				avg2015Decimal = avg2015Decimal.add(new BigDecimal(pjb.avg2015));
			}
			if(pjb.avg2016!=null) {
				avg2016Decimal = avg2016Decimal.add(new BigDecimal(pjb.avg2016));
			}
			if(pjb.avg2017!=null) {
				avg2017Decimal = avg2017Decimal.add(new BigDecimal(pjb.avg2017));
			}
		}
		pebJianBao.yearZengZhang = yearZengZhang.floatValue();
		pebJianBao.avg2015 = avg2015Decimal.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
		pebJianBao.avg2016 = avg2016Decimal.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
		pebJianBao.avg2017 = avg2017Decimal.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
		//上一年目标达成
		if(pebJianBao.historyYearList!=null && pebJianBao.historyYearList.size()>0) {
			int lastYear = pebJianBao.historyYearList.size()-1;
			Float lastYearAvg = pebJianBao.historyYearList.get(lastYear);
			//int beforeYear = pebJianBao.historyYearList.size()-2;  //2017年的数据为手动指定，所以使用手动指定的，2020年需要改变
			if(pebJianBao.avg2017!=null) {
				//上一年目标达成=去年年平均/前年年平均-1
				pebJianBao.lastYearTargetreach = new BigDecimal(lastYearAvg).divide(
						new BigDecimal(pebJianBao.avg2017),4,BigDecimal.ROUND_HALF_UP)
						.subtract(new BigDecimal(1)).setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
			}
			
		}else {
			if(pebJianBao.avg2017!=null && pebJianBao.avg2016!=null) {
				//目标达成2017年平均/2016年平均-1
				pebJianBao.lastYearTargetreach = new BigDecimal(pebJianBao.avg2017).divide(
						new BigDecimal(pebJianBao.avg2016),4,BigDecimal.ROUND_HALF_UP)
						.subtract(new BigDecimal(1)).setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
			}
		}
		//实际年增长（今年年效率增长-上一年目标达成）+0.3
		if(pebJianBao.yearZengZhang!=null && pebJianBao.lastYearTargetreach!=null) {
			pebJianBao.yearsjZengZhang = new BigDecimal(pebJianBao.yearZengZhang)
					.subtract(new BigDecimal(pebJianBao.lastYearTargetreach))
					.add(new BigDecimal(0.3)).setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
		}
		
		//班组人数
		BigDecimal banzuPersonCount = new BigDecimal(0);
		for (SubTeam st : processList) {
			Float personCount = getPersionCountByBanzu(st.getSubName(), currentYear,null,null);
			Integer chuQinDays = getChuQinDays(st.getSubName(), currentYear, null, null);
			banzuPersonCount = banzuPersonCount.add(new BigDecimal(personCount).divide(
					new BigDecimal(chuQinDays),5,BigDecimal.ROUND_HALF_UP));
		}
		pebJianBao.yearbanzuPersonCount = banzuPersonCount.intValue();
		
		
		//全体人数
		BigDecimal bigDecimal = new BigDecimal(0);
		for (SubTeam iteam : chejianList) {
			if(iteam.getSubName().equals(subTeam.getSubName())) {
				bigDecimal = bigDecimal.add(banzuPersonCount);
			}else {
				List<SubTeam> list = totalDao.query("from SubTeam where fatherId = ? ",subTeam.getId());
				for (SubTeam subTeam2 : list) {
					Float chejianCount = getPersionCountByBanzu(subTeam2.getSubName(), currentYear,null, null);
					Integer chejianCqCount =getChuQinDays(subTeam2.getSubName(), currentYear, null, null);
					if(chejianCqCount==0) {
						continue;
					}
					bigDecimal =bigDecimal.add( new BigDecimal(chejianCount).divide(new BigDecimal(chejianCqCount),3,BigDecimal.ROUND_HALF_UP));
				}
				
			}
			
		}
		pebJianBao.yearallPersonCount = bigDecimal.setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
		
		//年占比
		if(pebJianBao.yearZengZhang!=null && pebJianBao.yearbanzuPersonCount!=null) {
			pebJianBao.yearZhanbi = new BigDecimal(pebJianBao.yearZengZhang).multiply(
					new BigDecimal(pebJianBao.yearbanzuPersonCount).divide(
							new BigDecimal(pebJianBao.yearallPersonCount),4,BigDecimal.ROUND_HALF_UP))
					.setScale(4, BigDecimal.ROUND_HALF_UP).floatValue();
		}
		
		
		//..年。。月份
		//currentMonth
		Integer cqCount =getChuQinDays(subTeam.getSubName(), currentYear, currentMonth, null);
		if(cqCount!=0 &&pebJianBao.yearjishu!=null) {
			BigDecimal monthTotalzsNumber = getzsNumber(subTeam.getSubName(), currentYear, currentMonth, null);
			Float persionMonthCount = getPersionCountByBanzu(subTeam.getSubName(), currentYear, currentMonth, null);
			BigDecimal zsAvgNumber = monthTotalzsNumber.divide(new BigDecimal(persionMonthCount),3,BigDecimal.ROUND_HALF_UP);
			pebJianBao.monthAvg = zsAvgNumber.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();//月平均
			//月效率增长
			pebJianBao.monthZengZhang = zsAvgNumber.divide(new BigDecimal(pebJianBao.yearjishu),4,BigDecimal.ROUND_HALF_UP)
					.subtract(new BigDecimal(1)).setScale(4, BigDecimal.ROUND_HALF_UP).floatValue();
		}
		
//		detailList.add(bigDecimal.floatValue());//月平均	
		
		//每日
		Float avgNumber = (Float) totalDao.getObjectByCondition("select avgNumber from PebUsers where banZu=? "
				+ "and year=? and month=? and day=? and avgNumber is not null", subTeam.getSubName(),currentYear,currentMonth,currentDay);
		if(avgNumber!=null) {
			pebJianBao.dayAvg =new BigDecimal(avgNumber).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue(); 
		}
		
		//月效率增长
		BigDecimal monthZengZhang = new BigDecimal(0);
		for (PebJianBao pjb : chejianPebJianBao) {
			if(pjb.monthZhanbi!=null) {
				monthZengZhang = monthZengZhang.add(new BigDecimal(pjb.monthZhanbi));
			}
		}
		pebJianBao.monthZengZhang = monthZengZhang.floatValue();
		
		
		//月班组人数
		BigDecimal banzuPersonMonthCount = new BigDecimal(0);
		for (SubTeam st : processList) {
			Float personCount = getPersionCountByBanzu(st.getSubName(), currentYear,currentMonth,null);
			Integer chuQinDays = getChuQinDays(st.getSubName(), currentYear, currentMonth, null);
			banzuPersonMonthCount = banzuPersonMonthCount.add(new BigDecimal(personCount).divide(
					new BigDecimal(chuQinDays),3,BigDecimal.ROUND_HALF_UP)
					.setScale(0,BigDecimal.ROUND_HALF_UP));
		}
		pebJianBao.monthBanZuPersonCount = banzuPersonMonthCount.intValue();
		
		//月全体人数
		bigDecimal = new BigDecimal(0);
		for (SubTeam iteam : chejianList) {
			if(iteam.getSubName().equals(subTeam.getSubName())) {
				bigDecimal = bigDecimal.add(banzuPersonMonthCount);
			}else {
				List<SubTeam> list = totalDao.query("from SubTeam where fatherId = ? ",subTeam.getId());
				for (SubTeam subTeam2 : list) {
					Float chejianCount = getPersionCountByBanzu(subTeam2.getSubName(), currentYear,currentMonth, null);
					Integer chejianCqCount =getChuQinDays(subTeam2.getSubName(), currentYear, currentMonth, null);
					if(chejianCqCount==0) {
						continue;
					}
					bigDecimal =bigDecimal.add( new BigDecimal(chejianCount).divide(new BigDecimal(chejianCqCount),3,BigDecimal.ROUND_HALF_UP));
				}
				
			}
		}
		Integer allMonthNumber = bigDecimal.setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
		pebJianBao.monthAllPersonCount = allMonthNumber;
		
		//月占比
		if(pebJianBao.monthBanZuPersonCount!=null && pebJianBao.monthZengZhang!=null ) {
			
			Float zhanbi = new BigDecimal(pebJianBao.monthZengZhang).multiply(new BigDecimal(pebJianBao.monthBanZuPersonCount)
					.divide(new BigDecimal(allMonthNumber),4,BigDecimal.ROUND_HALF_UP))
					.setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
			pebJianBao.monthZhanbi = zhanbi;
		}
		
		Float UPPH = (Float) totalDao.getObjectByCondition("select UPPH from PebUsers where banzu=? and year=? and month=? and day=?",
					subTeam.getSubName(),currentYear,currentMonth,currentDay);
		pebJianBao.UPPH = UPPH;
		pebJianBao.remark = subTeam.getRemarks();
		
		return pebJianBao;
	}
	
	@SuppressWarnings({"unchecked" })
	private PebJianBao getJianBaoData(SubTeam subTeam,List<Integer> yearList,int currentYear,int currentMonth,int currentDay) {
		String banzu = subTeam.getSubName();
		PebJianBao pebJianBao = new PebJianBao();
		BigDecimal bigDecimal = null;//通用的Bigdecimal
		pebJianBao.banzu = subTeam.getSubName();
		pebJianBao.fuzeren = subTeam.getPrincipals();
		pebJianBao.avg2015= subTeam.getAvg2015();
		pebJianBao.avg2016 = subTeam.getAvg2016();
		pebJianBao.avg2017 = subTeam.getAvg2017();
		
		for (int year : yearList) {
			
			if(year==currentYear) {
				//----------年基数
				if(pebJianBao.avg2016!=null) {
					
//						if(pebJianBao.avg2017!=null) {
//							//目标达成2017年平均/2016年平均-1
//							pebJianBao.lastYearTargetreach = new BigDecimal(pebJianBao.avg2017).divide(
//									new BigDecimal(pebJianBao.avg2016),4,BigDecimal.ROUND_HALF_UP)
//									.subtract(new BigDecimal(1)).setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
//						}
					//2018年年基数为2016年的1.3倍 、2018年年目标为2018年基数的1.3倍，2019年基数为2018年目标，2019年目标为为2018年目标*1.3
					pebJianBao.lastYearJishu = new BigDecimal(subTeam.getAvg2016()).multiply(new BigDecimal(1.3));//数据机房的比较特殊，2018年基数为2017年平均
					//2019年基数为2018年目标，2019年基数为2018年目标，2018年目标为2017*1.3
					pebJianBao.yearjishu = pebJianBao.lastYearJishu.multiply(new BigDecimal(1.3)).floatValue();
					pebJianBao.yearTarget = pebJianBao.lastYearJishu.multiply(new BigDecimal(1.3)).multiply(new BigDecimal(1.3)).floatValue();
					
//						float jishu = new BigDecimal(subTeam.getAvg2016()).multiply(new BigDecimal(1.3))
//									.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
////						//如果前年没有达成2016年平均*1.3 则先完成前年的目标
////						if(subTeam.getAvg2017()<jishu) {
////							pebJianBao.yearjishu = subTeam.getAvg2017();
////						}else {
//							pebJianBao.yearjishu = jishu;
//						}
					//年目标为基数的1.3倍
				}else {
					return pebJianBao;
				}
				pebJianBao.yearTarget = new BigDecimal(pebJianBao.yearjishu)
						.multiply(new BigDecimal(1.3)).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
			}
			
			//出勤天数
			Integer cqCount =getChuQinDays(banzu, year, null, null);
			
			
			
			//-----------年平均 (实际日人均仓)
			Float personCount =getPersionCountByBanzu(banzu, year, null, null);
			BigDecimal yearSumNumber = getzsNumber(banzu, year, null, null);
			if(yearSumNumber.floatValue()>0 && personCount>0) {
				bigDecimal = yearSumNumber.divide(new BigDecimal(personCount),5,BigDecimal.ROUND_HALF_UP)
						.setScale(3, BigDecimal.ROUND_HALF_UP);
			}
			
			if(year==currentYear) {
				if(cqCount!=null && cqCount>0) {
					pebJianBao.yearAvg=bigDecimal.floatValue();
					//-------------------年效率增长
					if(pebJianBao.yearAvg!=null && pebJianBao.yearjishu!=null) {
						//（（年平均/年基数）-1）
						pebJianBao.yearZengZhang = bigDecimal.divide(
								new BigDecimal(pebJianBao.yearjishu),5,BigDecimal.ROUND_HALF_UP)
								.subtract(new BigDecimal(1)).setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
						
						
						//实际年增长（今年年效率增长-上一年目标达成）+0.3
						if(pebJianBao.yearZengZhang!=null && pebJianBao.lastYearTargetreach!=null) {
							pebJianBao.yearsjZengZhang = new BigDecimal(pebJianBao.yearZengZhang)
									.subtract(new BigDecimal(pebJianBao.lastYearTargetreach))
									.add(new BigDecimal(0.3)).setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
						}
					}
					
					//人数
					bigDecimal = new BigDecimal(personCount).divide(new BigDecimal(cqCount),3,BigDecimal.ROUND_HALF_UP);
					pebJianBao.yearbanzuPersonCount = bigDecimal.setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
					
					//全体人数
					String allPersonHql = "from SubTeam where fatherId =(select fatherId from SubTeam where subName=?) and subName <>'数据机房'";
					List<SubTeam> chejianlist = totalDao.query(allPersonHql,subTeam.getSubName());
					bigDecimal = new BigDecimal(0);
					for (SubTeam iteam : chejianlist) {
						Float chejianCount = getPersionCountByBanzu(iteam.getSubName(), year,null, null);
						Integer chejianCqCount =getChuQinDays(iteam.getSubName(), year, null, null);
						if(chejianCqCount==0) {
							continue;
						}
						bigDecimal =bigDecimal.add( new BigDecimal(chejianCount).divide(new BigDecimal(chejianCqCount),3,BigDecimal.ROUND_HALF_UP));
					}
					pebJianBao.yearallPersonCount = bigDecimal.setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
					
					//年占比
					if(pebJianBao.yearZengZhang!=null && pebJianBao.yearbanzuPersonCount!=null) {
						pebJianBao.yearZhanbi = new BigDecimal(pebJianBao.yearZengZhang).multiply(
								new BigDecimal(pebJianBao.yearbanzuPersonCount).divide(
										new BigDecimal(pebJianBao.yearallPersonCount),4,BigDecimal.ROUND_HALF_UP))
								.setScale(4, BigDecimal.ROUND_HALF_UP).floatValue();
					}
					
					//..年。。月份
					//currentMonth
					cqCount =getChuQinDays(banzu, currentYear, currentMonth, null);
					if(cqCount!=0) {
						BigDecimal monthTotalzsNumber = getzsNumber(banzu, currentYear, currentMonth, null);
						Float persionMonthCount = getPersionCountByBanzu(banzu, currentYear, currentMonth, null);
						BigDecimal zsAvgNumber = monthTotalzsNumber.divide(new BigDecimal(persionMonthCount),3,BigDecimal.ROUND_HALF_UP).setScale(3,BigDecimal.ROUND_HALF_UP);
						pebJianBao.monthAvg = zsAvgNumber.floatValue();//月平均
						//月效率增长
						pebJianBao.monthZengZhang = zsAvgNumber.divide(new BigDecimal(pebJianBao.yearjishu),4,BigDecimal.ROUND_HALF_UP)
								.subtract(new BigDecimal(1)).setScale(4, BigDecimal.ROUND_HALF_UP).floatValue();
					}
					
//					detailList.add(bigDecimal.floatValue());//月平均	
					
					//每日
					Float avgNumber = (Float) totalDao.getObjectByCondition("select avgNumber from PebUsers where banZu=? "
							+ "and year=? and month=? and day=? and avgNumber is not null", banzu,currentYear,currentMonth,currentDay);
					if(avgNumber!=null) {
						pebJianBao.dayAvg =new BigDecimal(avgNumber).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue(); 
					}
					
					
					//班组人数
					personCount =getPersionCountByBanzu(banzu, currentYear, currentMonth, null);
					if(cqCount!=0) {
						Integer banzuMonthCount = new BigDecimal(personCount).divide(new BigDecimal(cqCount),3,BigDecimal.ROUND_HALF_UP).setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
						pebJianBao.monthBanZuPersonCount = banzuMonthCount;
					}
					
					
					//全体人数
					bigDecimal = new BigDecimal(0);
					for (SubTeam iteam : chejianlist) {
						Float chejianCount = getPersionCountByBanzu(iteam.getSubName(), currentYear,currentMonth, null);
						Integer chejianCqCount =getChuQinDays(iteam.getSubName(), currentYear, currentMonth, null);
						if(chejianCqCount==0) {
								continue;
						}
						bigDecimal =bigDecimal.add( new BigDecimal(chejianCount).divide(new BigDecimal(chejianCqCount),3,BigDecimal.ROUND_HALF_UP));
					}
					Integer allMonthNumber = bigDecimal.setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
					pebJianBao.monthAllPersonCount = allMonthNumber;
					
					//月占比
					if(pebJianBao.monthBanZuPersonCount!=null && pebJianBao.monthZengZhang!=null ) {
						
						Float zhanbi = new BigDecimal(pebJianBao.monthZengZhang).multiply(new BigDecimal(pebJianBao.monthBanZuPersonCount)
								.divide(new BigDecimal(allMonthNumber),4,BigDecimal.ROUND_HALF_UP))
								.setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
						pebJianBao.monthZhanbi = zhanbi;
					}
					
					Float UPPH = (Float) totalDao.getObjectByCondition("select UPPH from PebUsers where banzu=? and year=? and month=? and day=?", banzu,currentYear,currentMonth,currentDay);
					pebJianBao.UPPH = UPPH;
					pebJianBao.remark = subTeam.getRemarks();
				}
			}else if(year==currentYear-1){
				if(pebJianBao.historyYearList==null) {
					List<Float> yearAvgList = new ArrayList<Float>();
					if(bigDecimal!=null) {
						yearAvgList.add(bigDecimal.floatValue());
					}else {
						yearAvgList.add(null);
					}
					pebJianBao.historyYearList = yearAvgList;
				}else {
					pebJianBao.historyYearList.add(bigDecimal.floatValue());
				}
				//去年年目标达成     =去年年平均/前年年平均-1
				if(pebJianBao.avg2017!=null && bigDecimal!=null && pebJianBao.avg2017!=null) {
					pebJianBao.lastYearTargetreach = bigDecimal.divide(new BigDecimal(pebJianBao.avg2017),4,BigDecimal.ROUND_HALF_UP)
					.subtract(new BigDecimal(1)).setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
					
				}
				
			}
			
			
		}
		
		return pebJianBao;
	}

	

	@SuppressWarnings("unchecked")
	private PebJianBao getFenChangData(SubTeam subTeam,List<Integer> yearList,int currentYear,
					int currentMonth,int currentDay,List<PebJianBao> chejianListInfo) {
//		List detailList = new ArrayList();
		PebJianBao pebJianBao = new PebJianBao();
		pebJianBao.banzu = subTeam.getSubName();
		pebJianBao.fuzeren = subTeam.getPrincipals();
		List<SubTeam> subTeamList = totalDao.query("from SubTeam where fatherId=?", subTeam.getId());
		
//		BigDecimal yearJishuDecimal = new BigDecimal(0);//年基数
//		BigDecimal yearTargetDecimal = new BigDecimal(0);// 年目标
//		BigDecimal bigDecimal2 = new BigDecimal(0);//2015年平均
//		BigDecimal bigDecimal3 = new BigDecimal(0);//2016年平均
//		BigDecimal bigDecimal4 = new BigDecimal(0);//2017年平均
//		for (SubTeam subTeam2 : subTeamList) {
//			if(subTeam2.getAvg2015()!=null) {
//				bigDecimal2 = bigDecimal2.add(new BigDecimal(subTeam2.getAvg2015()));
//			}
//			if(subTeam2.getAvg2016()!=null) {
//				BigDecimal avg2016 = new BigDecimal(subTeam2.getAvg2016());
//				bigDecimal3 = bigDecimal3.add(avg2016);
//				yearJishuDecimal =yearJishuDecimal.add(avg2016.multiply(new BigDecimal(1.3)).setScale(3,BigDecimal.ROUND_HALF_UP));
//				yearTargetDecimal = yearTargetDecimal.add( avg2016.multiply(new BigDecimal(1.3))
//						.multiply(new BigDecimal(1.3)).setScale(3,BigDecimal.ROUND_HALF_UP));
//			}
//			if(subTeam2.getAvg2017()!=null) {
//				bigDecimal4 =bigDecimal4.add(new BigDecimal(subTeam2.getAvg2017()));
//			}
//		}
		if(subTeam.getAvg2015()!=null ) {
			pebJianBao.avg2015 = subTeam.getAvg2015();
		}
//		else {
//			pebJianBao.avg2015 = bigDecimal2.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
//		}
		if(subTeam.getAvg2016()!=null) {
			pebJianBao.avg2016 = subTeam.getAvg2016();
		}
//		else {
//			pebJianBao.avg2016 =bigDecimal3.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
//		}
		if(subTeam.getAvg2017()!=null) {
			pebJianBao.avg2017= subTeam.getAvg2017();
		}
//		else {
//			pebJianBao.avg2017 = bigDecimal4.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
//		}
		//历史年份平均
		BigDecimal hisYearDecimal = new BigDecimal(0);
		for (int i=0;i<yearList.size();i++) {
			int year = yearList.get(i);
			if(year>=currentYear) {
				continue;
			}
			for (PebJianBao pjb : chejianListInfo) {
				if(pjb!=null && pjb.historyYearList!=null && pjb.historyYearList.size()>0 &&pjb.historyYearList.get(i)!=null) {
					hisYearDecimal = hisYearDecimal.add(new BigDecimal(pjb.historyYearList.get(i)));
				}
			}
			if(pebJianBao.historyYearList==null) {
				pebJianBao.historyYearList = new ArrayList<Float>(); 
			}
			pebJianBao.historyYearList.add(hisYearDecimal.floatValue());//2018年平均不显示了
//			pebJianBao.historyYearList.add(null);
			
			if(yearList.get(i)==currentYear-1) {
				//去年年目标达成
				if(hisYearDecimal.floatValue()>0 && pebJianBao.avg2017!=null && pebJianBao.avg2017!=0) {
					pebJianBao.lastYearTargetreach = hisYearDecimal.divide(
							new BigDecimal(pebJianBao.avg2017),4,BigDecimal.ROUND_HALF_UP)
							.subtract(new BigDecimal(1)).setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
				}
			}
		}
		
		//2019年基数为2018年目标，2018年目标为2018年基数*1.3 ，2018年基数为2016年平均的1.3倍
		if(pebJianBao.avg2016!=null) {
			pebJianBao.lastYearJishu =new BigDecimal(subTeam.getAvg2016()).multiply(new BigDecimal(1.3));//2018年基数
			pebJianBao.yearjishu = pebJianBao.lastYearJishu.multiply(new BigDecimal(1.3)).floatValue();//2018年目标
			pebJianBao.yearTarget = pebJianBao.lastYearJishu.multiply(new BigDecimal(1.3)).multiply(new BigDecimal(1.3)).floatValue();//
//			float jishu = new BigDecimal(subTeam.getAvg2016()).multiply(new BigDecimal(1.3))
//						.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
//			pebJianBao.yearjishu = jishu;
//			//年目标为基数的1.3倍
//			pebJianBao.yearTarget = new BigDecimal(pebJianBao.yearjishu)
//					.multiply(new BigDecimal(1.3)).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
		}
		//年平均
		BigDecimal yearAvgDecmal = new BigDecimal(0);
		BigDecimal yearZengZhangDecimal = new BigDecimal(0);
		BigDecimal yearBanzuPersonCount = new BigDecimal(0);
		BigDecimal yearAllPersonCount = new BigDecimal(0);
		
		BigDecimal monthAvgDecimal = new BigDecimal(0);
		BigDecimal dayAvgDecimal = new BigDecimal(0);
		BigDecimal monthZengZhangDecimal = new BigDecimal(0);
		BigDecimal monthBanZuPersonCount = new BigDecimal(0);
		BigDecimal monthAllPersonCount = new BigDecimal(0);
		for (PebJianBao pjb : chejianListInfo) {
			if(pjb.banzu.equals("数据机房")) {
				continue;
			}
			if(pjb.yearbanzuPersonCount!=null) {
				yearAllPersonCount = yearAllPersonCount.add(new BigDecimal(pjb.yearbanzuPersonCount));
			}
			if(pjb.monthBanZuPersonCount !=null) {
				monthAllPersonCount = monthAllPersonCount.add(new BigDecimal(pjb.monthBanZuPersonCount));
			}
		}
		for (SubTeam subTeam2 : subTeamList) {
			if(subTeam2.getSubName().equals("数据机房")) {
				continue;
			}
			for (PebJianBao pjb : chejianListInfo) {
				
				if(subTeam2.getSubName()!=null && subTeam2.getSubName().equals(pjb.banzu)) {
					if(pjb.yearbanzuPersonCount!=null ) {
						yearBanzuPersonCount = yearBanzuPersonCount.add(new BigDecimal(pjb.yearbanzuPersonCount));
					}
					if(pjb.monthBanZuPersonCount!=null) {
						monthBanZuPersonCount = monthBanZuPersonCount.add(new BigDecimal(pjb.monthBanZuPersonCount));
					}
					if(pjb.yearAvg!=null) {
						yearAvgDecmal = yearAvgDecmal.add(new BigDecimal(pjb.yearAvg));
					}
					if(pjb.yearZhanbi!=null) {
						yearZengZhangDecimal = yearZengZhangDecimal.add(new BigDecimal(pjb.yearZhanbi));
					}
					if(pjb.monthAvg!=null && pjb.monthZhanbi!=null) {
						monthAvgDecimal = monthAvgDecimal.add(new BigDecimal(pjb.monthAvg));
						monthZengZhangDecimal = monthZengZhangDecimal.add(new BigDecimal(pjb.monthZhanbi));
					}
					if(pjb.dayAvg!=null) {
						dayAvgDecimal = dayAvgDecimal.add(new BigDecimal(pjb.dayAvg));
					}
				}
			}
		}
		pebJianBao.yearAvg = yearAvgDecmal.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
		
		//年效率增长
		pebJianBao.yearZengZhang = yearZengZhangDecimal.floatValue();
		
		//实际年增长（今年年效率增长-上一年目标达成）+0.3
		if(pebJianBao.yearZengZhang!=null && pebJianBao.yearZengZhang!=0 && pebJianBao.lastYearTargetreach!=null) {
			pebJianBao.yearsjZengZhang = new BigDecimal(pebJianBao.yearZengZhang)
					.subtract(new BigDecimal(pebJianBao.lastYearTargetreach))
					.add(new BigDecimal(0.3)).setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
		}
		
		//班组人数
		if(yearBanzuPersonCount.intValue()==0) {
			return pebJianBao;
		}
		pebJianBao.yearbanzuPersonCount = yearBanzuPersonCount.intValue();
		
		//全体人数
		pebJianBao.yearallPersonCount = yearAllPersonCount.intValue();
		
		//年占比
		pebJianBao.yearZhanbi = yearZengZhangDecimal.multiply(
				yearBanzuPersonCount.divide(yearAllPersonCount,3,BigDecimal.ROUND_HALF_UP)).
				setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
		
		//月效率增长
		pebJianBao.monthZengZhang = monthZengZhangDecimal.floatValue();
		
		if(monthBanZuPersonCount.intValue()==0) {
			return pebJianBao;
		}
		
		//月平均
		pebJianBao.monthAvg = monthAvgDecimal.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
		
		//日平均
		pebJianBao.dayAvg = dayAvgDecimal.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
		//月班组人数
		pebJianBao.monthBanZuPersonCount = monthBanZuPersonCount.intValue();
		
		//月全体人数
		pebJianBao.monthAllPersonCount = monthAllPersonCount.intValue();
		
		//月占比
		pebJianBao.monthZhanbi = monthZengZhangDecimal.multiply(
				monthBanZuPersonCount.divide(monthAllPersonCount,3,BigDecimal.ROUND_HALF_UP))
				.setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
		if(pebJianBao.banzu.equals("钣金厂")) {
			pebJianBao.lastYearTargetreach = 0.3002f;
			pebJianBao.yearsjZengZhang = 0.1462f;
		}
		return pebJianBao;
		
	}
	
	@SuppressWarnings("unchecked")
	private PebJianBao getBanjinProcessData(SubTeam subTeam,Integer currentYear,Integer currentMonth,Integer currentDay) {
		PebJianBao pebJianBao = new PebJianBao();
		pebJianBao.banzu = subTeam.getSubName();
		pebJianBao.fuzeren = subTeam.getPrincipals();
		pebJianBao.avg2015 = subTeam.getAvg2015();
		pebJianBao.avg2016 = subTeam.getAvg2016();
		pebJianBao.avg2017 = subTeam.getAvg2017();
		
		List<Integer> yearList = totalDao.query("select distinct year from PebUsers where banzu=? and"
				+ " actualNum is not null and zsNumber is not null order by year ", subTeam.getSubName());
		if(yearList!=null && yearList.size()>0) {
			for (Integer year : yearList) {
				Integer chuQinDays = getChuQinDays(subTeam.getSubName(), year, null, null);
				Float yearSumNumber = (Float) totalDao.getObjectByCondition("select sum(zsNumber) from PebUsers "
						+ " where zsNumber is not null and banZu=? and year=? ",subTeam.getSubName(),year);
				Float personBanzuCount =getPersionCountByBanzu(subTeam.getSubName(), currentYear, null, null);
				BigDecimal yearAvgDecimal = new BigDecimal(yearSumNumber).divide(new BigDecimal(personBanzuCount),5,BigDecimal.ROUND_HALF_UP)
						.setScale(3, BigDecimal.ROUND_HALF_UP);
				if(currentYear==year) {
					if(pebJianBao.avg2017!=null && pebJianBao.avg2016!=null) {
						BigDecimal jishuDecimal = new BigDecimal(pebJianBao.avg2016).multiply(new BigDecimal(1.3));
						if(pebJianBao.avg2017<jishuDecimal.floatValue()) {
							pebJianBao.yearjishu = pebJianBao.avg2017;
						}else {
							pebJianBao.yearjishu = jishuDecimal.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
						}
						
						pebJianBao.yearTarget = new BigDecimal(pebJianBao.yearjishu).multiply(new BigDecimal(1.3))
								.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
						
						pebJianBao.yearAvg = yearAvgDecimal.floatValue();
					}
					
					//年效率增长
					pebJianBao.yearZengZhang = yearAvgDecimal.divide(new BigDecimal(pebJianBao.yearjishu),4,BigDecimal.ROUND_HALF_UP)
							.subtract(new BigDecimal(1)).setScale(4,BigDecimal.ROUND_HALF_UP).floatValue();
					
					
					//班组人数
					if(chuQinDays!=0) {
						Integer banzuMonthCount = new BigDecimal(personBanzuCount).divide(new BigDecimal(chuQinDays)
								,3,BigDecimal.ROUND_HALF_UP).setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
						pebJianBao.monthBanZuPersonCount = banzuMonthCount;
					}
					
					//全体人数
					//totalDao.query("from SubTeam where ", agr)
					
					
				}else {
					pebJianBao.yearAvg=yearAvgDecimal.floatValue();
				}
				
			}
			
		}
		return pebJianBao;
	}
	

	/**
	 * 对排行冒泡排序
	 * @param showList
	 * @return
	 */
	private List<PebJianBao> maoPaoOrder(List<PebJianBao> showList,String orderStr) {
//		fore
		for(int i=0;i<showList.size();i++) {
			for(int j=1+i;j<showList.size();j++) {
				if(orderStr!=null && orderStr.equals("year")) {
					float yearZengZhang1 = 0f;
					float yearZengZhang2 = 0f;
					if(showList.get(i).yearZengZhang!=null) {
						yearZengZhang1 = showList.get(i).yearZengZhang;
					}
					if(showList.get(j).yearZengZhang!=null) {
						yearZengZhang2 = showList.get(j).yearZengZhang;
					}
					if(yearZengZhang1<yearZengZhang2) {
						PebJianBao pebJianBao1 = showList.get(i);
						PebJianBao pebJianBao2 = showList.get(j);
						showList.remove(i);
						showList.add(i,pebJianBao2);
						showList.remove(j);
						showList.add(j, pebJianBao1);
					}
				}else if(orderStr!=null && orderStr.equals("month")) {
					float monthZengZhang1 = 0f;
					float monthZengZhang2 = 0f;
					if(showList.get(i).monthZengZhang!=null) {
						monthZengZhang1 = showList.get(i).monthZengZhang;
					}
					if(showList.get(j).monthZengZhang!=null) {
						monthZengZhang2 = showList.get(j).monthZengZhang;
					}
					if(monthZengZhang1<monthZengZhang2) {
						PebJianBao pebJianBao1 = showList.get(i);
						PebJianBao pebJianBao2 = showList.get(j);
						
						showList.remove(i);
						showList.add(i,pebJianBao2);
						showList.remove(j);
						showList.add(j,pebJianBao1);
					}
				}
			}
			
		}
		
		return showList;
	}
	
	@SuppressWarnings({ "rawtypes"})
	private List getPaiHang(List<PebJianBao> paiHangList,String orderStr) {
		Integer index = -1;
		for(int i=0;i<paiHangList.size();i++) {
			if(paiHangList.get(i).bcolorStyle!=null && paiHangList.get(i).bcolorStyle.indexOf("none")>0) {
			}else {
				index++;
			}
			if(orderStr!=null && orderStr.equals("year")) {
				if(i==0) {
					paiHangList.get(i).yearOrder = "<strong>"+(index+1)+"</strong>";
				}else if(i==paiHangList.size()-1) {
					paiHangList.get(i).yearOrder = "<b style=''>"+(index+1)+"</b>";
				}else {
					paiHangList.get(i).yearOrder ="<span>"+(index+1)+"</span>";
				}
			}else if(orderStr!=null && orderStr.equals("month")){
				if(i==0) {
					paiHangList.get(i).monthOrder = "<strong>"+(index+1)+"</strong>";
				}else if(i==paiHangList.size()-1) {
					paiHangList.get(i).monthOrder = "<b style=''>"+(index+1)+"</b>";
				}else {
					paiHangList.get(i).monthOrder ="<span>"+(index+1)+"</span>";
				}
			}
		}
		return paiHangList;
	}
	@Override
	public String addSubTeam(SubTeam subTeam) {
		SubTeam subTeam2 = (SubTeam) totalDao.getObjectByCondition("from SubTeam where subName =?", subTeam.getSubName());
		if(subTeam2!=null) {
			return "当前信息已经存在";
		}
		boolean save = totalDao.save(subTeam);
		if(save) {
			return "添加成功";
		}
		return null;
	}

	@Override
	public Map<String, Object> findSubTeamByCon(SubTeam subTeam, Integer pageNo, Integer pageSize, String pageStatus) {
		if(subTeam==null) {
			subTeam = new SubTeam();
		}
		
		String hql = totalDao.criteriaQueries(subTeam, null, null);
		if(subTeam.getFatherId()!=null) {
			hql+=" and fatherId = "+subTeam.getFatherId();
		}else {
			hql+=" and fatherId is null";
		}
		hql+=" order by id desc";
	
		List<SubTeam> list = totalDao.findAllByPage(hql, pageNo, pageSize, null);
		Integer count = totalDao.getCount(hql);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("count", count);
		map.put("list", list);
		return map;
	}

	@Override
	public String deleteSubTeam(SubTeam subTeam, String pageStatus) {
		if(subTeam!=null && subTeam.getId()!=null) {
			boolean delete = totalDao.delete(subTeam);
			if(delete) {
				return "删除成功";
			}
		}
		
		return null;
	}

	@Override
	public PebProduction getPebProductionById(Integer id) {
		PebProduction pebProduction =(PebProduction) totalDao.getObjectById(PebProduction.class,id ); 
		return pebProduction;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PebUsers getPebUsersById(Integer id) {
		PebUsers pebUsers = (PebUsers) totalDao.getObjectById(PebUsers.class, id);
		
		List<PebBorrowAndLendLog> borrowLog = totalDao.query("from PebBorrowAndLendLog "
				+ "where pebUsersId=? and borrowNum!=null and (epStatus='同意' or epStatus is null)", pebUsers.getId());
		pebUsers.setBorrowLogList(borrowLog);
		
		List<PebBorrowAndLendLog> lendLog = totalDao.query("from PebBorrowAndLendLog "
				+ "where pebUsersId=? and lendNum!=null and (epStatus='同意' or epStatus is null)", pebUsers.getId());
		pebUsers.setLendLogList(lendLog);
		return pebUsers;
	}

	@Override
	public String addOrUpdatePebProduction(PebProduction pebProduction, String pageStatus) {
		Integer year = pebProduction.getYear();
		Integer month = pebProduction.getMonth();
		Integer day = pebProduction.getDay();
		String banzu = pebProduction.getBanzu();
		if(year!=null && month!=null && day!=null && banzu!=null && month>0 && year>0) {
			//修改
			//添加
			
			PebProduction pebPro=null;
			if(banzu.equals("数据机房")) {
				pebPro = (PebProduction) totalDao.getObjectByCondition("from PebProduction where banzu = ? and year=? and month=? "
						+ "and (markId is null or markId='') and cuNumber is null",banzu,year,month);
			}else {
				saveBeforDayData("PebProduction", pebProduction.getBanzu(), year+"-"+month+"-"+day,5);//如果昨天没有记录则添加基数为空的记录
				pebPro = (PebProduction) totalDao.getObjectByCondition("from PebProduction where banzu = ? and year=? and month=? and day=? "
						+ "and (markId is null or markId='') and cuNumber is null",banzu,year,month,day);
			}
			if(pebPro!=null) {
				pebProduction.setId(pebPro.getId());
				totalDao.clear();
			}
			
			//新数据覆盖和旧数据相同不保存
			StringBuffer buffer = new StringBuffer("from PebProduction where banzu = ? and year=?"
					+ " and month=? and day=?  and markId =? and cuNumber=? ");
			if(pebProduction.getMessage()!=null && !pebProduction.getMessage().equals("")) {
				buffer.append(" and message = '"+pebProduction.getMessage()+"'");
			}
			if(pebProduction.getMeasure()!=null && !pebProduction.getMeasure().equals("")) {
				buffer.append(" and measure ='"+pebProduction.getMeasure()+"'");
			}
			if(pebProduction.getZrComp()!=null && !pebProduction.getZrComp().equals("")) {
				buffer.append(" and zsComp = '"+pebProduction.getZrComp()+"'");
			}
			if(pebProduction.getZrCompMeasure()!=null && !pebProduction.getZrCompMeasure().equals("")) {
				buffer.append(" and zrCompMeasure = '"+pebProduction.getZrCompMeasure()+"'");
			}
			if(pebProduction.getXiShu()!=null ) {
				buffer.append(" and xiShu like '%"+pebProduction.getXiShu()+"%'");
			}
			PebProduction production = (PebProduction) totalDao.getObjectByCondition(buffer.toString()
				, banzu,year,month,day,pebProduction.getMarkId(),pebProduction.getCuNumber());
			if(production!=null && (pageStatus==null || !pageStatus.equals("update")) ) {
				return "该数据存在日期，编码，数量，系数，异常等相同";//说明是相同 的数据啦，不保存(添加的时候)
			}else {
				totalDao.clear();
				if(pebProduction.getCuNumber()!=null && pebProduction.getXiShu()!=null) {
					BigDecimal xishu = new BigDecimal(Float.toString(pebProduction.getXiShu()));
					BigDecimal cuNumber = new BigDecimal(Float.toString(pebProduction.getCuNumber()));
					float zsNumber = cuNumber.multiply(xishu).floatValue();
					pebProduction.setZsNumber(zsNumber);
				}
				boolean save=false;
				if(pebProduction.getId()==null) {
					save = totalDao.save(pebProduction);
				}else {
					save = totalDao.update(pebProduction);
				}
					
				if(save) {
					PebUsers pebUsers=null;
					if(banzu.equals("数据机房")) {
						pebUsers = (PebUsers) totalDao.getObjectByCondition("from PebUsers where banZu=? and year=? and month=? ",
								pebProduction.getBanzu(),pebProduction.getYear(), pebProduction.getMonth());
					}else {
						pebUsers = (PebUsers) totalDao.getObjectByCondition("from PebUsers where banZu=? and year=? and month=? and day=?",
								pebProduction.getBanzu(),pebProduction.getYear(), pebProduction.getMonth(),pebProduction.getDay());
					}
					if(pebUsers==null) {
						pebUsers = new PebUsers();
						pebUsers.setBanZu(banzu);
						pebUsers.setYear(year);
						pebUsers.setMonth(month);
						pebUsers.setDay(day);
						pebUsers.setZsNumber(pebProduction.getZsNumber());
						
					}
					return addOrUpdatePebUsers(pebUsers, pageStatus);
				}else {
					return "添加失败";
				}
			}
		}else {
			return "参数错误";
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String addOrUpdatePebUsers(PebUsers pebUsers, String pageStatus) {
		Users loginUser = Util.getLoginUser();
		if (loginUser==null) {
			throw new RuntimeException("请先登录");
		}
		if(pebUsers!=null) {
			if(pebUsers.getYear()==null || pebUsers.getYear()==0 || pebUsers.getMonth()==null || pebUsers.getMonth()==0) {
				return "年份或月份为0";
			}
			String hql = "";
			if(pebUsers.getCategory()==null) {
				String isBanzu= (String) totalDao.getObjectByCondition("select isBanzu from SubTeam where subName = ?", pebUsers.getBanZu());
				if(isBanzu!=null) {
					if(isBanzu.equals("工序")) {
						pebUsers.setCategory("bj");
					}
				}
			}
			if(pebUsers.getCategory()!=null && pebUsers.getCategory().equals("bj")) {
				hql="select sum(zsNumber) from PebProductionBanjin where processName=?";
			}else {
				hql="select sum(zsNumber) from PebProduction where banzu=?";
			}
			Float zsNum=null;
			if (pebUsers.getBanZu().equals("数据机房")) {
				zsNum = (Float) totalDao.getObjectByCondition(hql
						+" and zsNumber is not null and year=? and month=?",
						pebUsers.getBanZu(),pebUsers.getYear(),pebUsers.getMonth());
			}else {
				zsNum = (Float) totalDao.getObjectByCondition(hql
						+" and zsNumber is not null and year=? and month=? and day=?",
						pebUsers.getBanZu(),pebUsers.getYear(),pebUsers.getMonth(),pebUsers.getDay());
			}
			pebUsers.setZsNumber(zsNum);
			
			if(pebUsers.getId()!=null) {
				List<PebBorrowAndLendLog> borrowAndLendLogList = totalDao.query("from PebBorrowAndLendLog "
						+ " where pebUsersId =? and (epStatus is null or epStatus='同意')", pebUsers.getId());
				Integer borrowCount= 0;
				Integer lendCount = 0;
				for (PebBorrowAndLendLog pebBorrowAndLendLog : borrowAndLendLogList) {
					if(pebBorrowAndLendLog.getLendBanzu()!=null && pebBorrowAndLendLog.getLendNum()!=null) {
						lendCount+=pebBorrowAndLendLog.getLendNum();
					}
					if(pebBorrowAndLendLog.getBorrowBanzu()!=null && pebBorrowAndLendLog.getBorrowNum()!=null) {
						borrowCount+=pebBorrowAndLendLog.getBorrowNum();
					}
				}
				pebUsers.setBorrowNum(borrowCount);
				pebUsers.setLendNum(lendCount);
				if(pebUsers.getDangAnNum()!=null) {
					float noChuQin = 0;
					if(pebUsers.getNoChuQinNum()!=null) {
						noChuQin=pebUsers.getNoChuQinNum();
					}
					pebUsers.setActualNum(pebUsers.getDangAnNum()+borrowCount-lendCount-noChuQin);
				}
			}
			
			if(pebUsers.getDangAnNum()!=null && pebUsers.getDangAnNum()!=0) {
				
				BigDecimal szBigDecimal = new BigDecimal(zsNum);
				Float actualNum = pebUsers.getActualNum();
				if(actualNum!=null && actualNum!=0) {
					BigDecimal actualBigdecimal = new BigDecimal(pebUsers.getActualNum());
					float renjun = szBigDecimal.divide(actualBigdecimal,5,BigDecimal.ROUND_HALF_UP).floatValue();
					pebUsers.setAvgNumber(renjun);
					if(pebUsers.getGzHour()!=null) {
						BigDecimal bigDecimal = new BigDecimal(renjun);
						BigDecimal bigDecimal2 = new BigDecimal(pebUsers.getGzHour());
						float UPPH = bigDecimal.divide(bigDecimal2,5,BigDecimal.ROUND_HALF_UP).floatValue();
						pebUsers.setUPPH(UPPH);
					}
				}
			}
			boolean update=false;
			if(pebUsers.getId()==null) {
				update = totalDao.save(pebUsers);
			}else {
				update = totalDao.update(pebUsers);
			}
			if(update){
				return "保存成功";
			}else {
				return "保存失败";
			}
		}else {
			return "参数错误";
		}
	}

	@Override
	public String settingTarget(PebUsers pebUsers,Integer goYear,Integer goMonth,Integer goDay,
					Integer toYear,Integer toMonth,Integer toDay){
		
		PebUsers users = (PebUsers) totalDao.getObjectByCondition(
				"from PebUsers where banZu=? and year=? and month=? and day=?", pebUsers.getBanZu(),goYear,goMonth,goDay);
		if(users!=null) {
			users.setMbTarget(pebUsers.getMbTarget());
			totalDao.update(users);
		}else {
			users = new PebUsers();
			users.setYear(goYear);
			users.setMonth(goMonth);
			users.setDay(goDay);
			users.setMbTarget(pebUsers.getMbTarget());
			users.setBanZu(pebUsers.getBanZu());
			totalDao.save(users);
		}
		
		if((int)goYear==(int)toYear && (int)goMonth==(int)toMonth && (int)goDay==(int)toDay) {
			return "设置成功";
		}
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parse;
		try {
			parse = format.parse(goYear+"-"+goMonth+"-"+goDay);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("日期转换异常:"+e);
		}
		calendar.setTime(parse);
		calendar.add(Calendar.DAY_OF_YEAR, +1);
		Date time = calendar.getTime();
		String currentDate = format.format(time);
		if(currentDate.equals(toYear+"-"+toMonth+"-"+toDay)) {
			return settingTarget(pebUsers, toYear, toMonth, toDay, toYear, toMonth, toDay);
		}else {
			format = new SimpleDateFormat("yyyy");
			String year = format.format(time);
			format = new SimpleDateFormat("MM");
			String month = format.format(time);
			format = new SimpleDateFormat("dd");
			String day = format.format(time);
			int currentYear = Integer.parseInt(year);
			int currentMonth = Integer.parseInt(month);
			int currentDay = Integer.parseInt(day);
			
			return settingTarget(pebUsers, currentYear, currentMonth, currentDay, toYear, toMonth, toDay);
		}
	}

	@Override
	public SubTeam getSubTeamById(Integer id) {
		
		SubTeam subTeam = (SubTeam) totalDao.getObjectById(SubTeam.class, id);
		return subTeam;
	}

	@Override
	public String updateSubTeam(SubTeam	subTeam, String pageStatus) {
//		SubTeam subTeam2 = getSubTeamById(subTeam.getId());
		if(subTeam.getId()!=null) {
			SubTeam st = (SubTeam) totalDao.getObjectById(SubTeam.class,subTeam.getId());
			if(st!=null) {
				Integer fatherId = st.getFatherId();
				totalDao.clear();
				subTeam.setFatherId(fatherId);
				boolean update = totalDao.update(subTeam);
				if(update) {
					return "更新成功";
				}
			}else {
				return "没有找到要修改的信息，修改失败！";
			}
			
		}else {
			return  "没有找到该信息";
		}
		return null;
	}

	@Override
	public List findWorkShopList(String pageStatus) {
		List list=null;
		if(pageStatus!=null && pageStatus.equals("bjProcess")) {
			list = totalDao.query("select distinct processName from PebProductionBanjin");
		}else {
			list = totalDao.query("select distinct banzu from PebProduction");
		}
		return list;
	}

	@Override
	public String addPebBorrowAndLendLog(PebBorrowAndLendLog log, String pageStatus) {
		Users loginUser = Util.getLoginUser();
		if(loginUser!=null) {
			log.setAddTime(Util.getDateTime());
			log.setAddUserId(loginUser.getId());
			log.setAddUserName(loginUser.getName());
			totalDao.save(log);
		}
		return null;
	}

	@Override
	public Map<String, Object> findProductionBanjin(PebProductionBanjin banjin, Integer pageNo, Integer pageSize,
			String pageStatus) {
		if(banjin==null) {
			banjin = new PebProductionBanjin();
		}
		String hql = totalDao.criteriaQueries(banjin, null);
		if(banjin.getYear()!=null) {
			hql+=" and year="+banjin.getYear();
		}
		if(banjin.getMonth()!=null) {
			hql+= " and month="+banjin.getMonth();
		}
		if(banjin.getDay()!=null) {
			hql+=" and day="+banjin.getDay();
		}
		hql+=" order by year desc,month desc,day desc";
		List<PebProductionBanjin> list = totalDao.findAllByPage(hql, pageNo, pageSize, null);
		Integer count = totalDao.getCount(hql);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("count", count);
		map.put("list", list);
		return map;
	}

	@Override
	public PebProductionBanjin findBanjinById(Integer id, String pageStatus) {
		PebProductionBanjin banjin = (PebProductionBanjin) totalDao.getObjectById(PebProductionBanjin.class, id);
		return banjin;
	}

	@Override
	public String addOrUpdatePebProBanjin(PebProductionBanjin banjin, String pageStatus) {
		if(banjin==null || banjin.getProcessName()==null ) {
			return "没有找到正确数据或工序名称为空";
		}
		Integer year = banjin.getYear();
		Integer month = banjin.getMonth();
		Integer day = banjin.getDay();
		String processName = banjin.getProcessName();
		saveBeforDayData("PebProductionBanjin", processName, year+"-"+month+"-"+day,5);//如果昨天没有记录则添加基数为空的记录
		
		//判断是否存在当天当班组的数据，如果存在则覆盖之前的数据
		if(banjin.getId()==null) {
			PebProductionBanjin pebProductionBanjin = (PebProductionBanjin)totalDao.getObjectByCondition("from PebProductionBanjin"
					+ " where processName=? and year=? and month=? and day=?",processName,year,month,day);
			if(pebProductionBanjin!=null) {
				banjin.setId(pebProductionBanjin.getId());
				if(banjin.getRemarks()==null) {
					banjin.setRemarks(pebProductionBanjin.getRemarks());
				}
				if(banjin.getXiShu()==null) {
					banjin.setXiShu(pebProductionBanjin.getXiShu());
				}
				totalDao.clear();
			}
		}
		
		//计算折算数量
		if(banjin.getCuNumber()!=null) {
			if(banjin.getXiShu()==null || banjin.getXiShu()==1) {
				banjin.setZsNumber(banjin.getCuNumber());
			}else {
				BigDecimal xishu = new BigDecimal(Float.toString(banjin.getXiShu()));
				BigDecimal cuNumber = new BigDecimal(Float.toString(banjin.getCuNumber()));
				float zsNumber = cuNumber.multiply(xishu).floatValue();
				banjin.setZsNumber(zsNumber);
			}
		}else {
			if(banjin.getPEBSNumber()!=null || banjin.getK3Number()!=null) {
				float cuNumber = 0;
				if(banjin.getPEBSNumber()!=null) {
					cuNumber+=banjin.getPEBSNumber();
				}
				if(banjin.getK3Number()!=null) {
					cuNumber+=banjin.getK3Number();
				}
				if(banjin.getXiShu()==null || banjin.getXiShu()==1) {
					banjin.setZsNumber(cuNumber);
				}else {
					BigDecimal xishu = new BigDecimal(Float.toString(banjin.getXiShu()));
					BigDecimal cuNumberDecimal = new BigDecimal(Float.toString(cuNumber));
					float zsNumber = cuNumberDecimal.multiply(xishu).floatValue();
					banjin.setZsNumber(zsNumber);
				}
			}
		}
		boolean save=false;
		if(banjin.getId()==null) {
			save = totalDao.save(banjin);
		}else {
			save = totalDao.update(banjin);
		}
		if(save) {
			PebUsers pebUsers = (PebUsers) totalDao.getObjectByCondition("from PebUsers where banZu=? and year=?"
					+ " and month=? and day=?", processName,year,month,day);
			if(pebUsers==null) {
				pebUsers = new PebUsers();
				pebUsers.setBanZu(processName);
				pebUsers.setCategory("bj");
				pebUsers.setYear(year);
				pebUsers.setMonth(month);
				pebUsers.setDay(day);
			}
			return addOrUpdatePebUsers(pebUsers, null);
		}else {
			return "保存失败";
		}
//		if(save) {
//			PebUsers pebUsers = (PebUsers) totalDao.getObjectByCondition("from PebUsers where banZu=? and year=?"
//					+ " and month=? and day=?",processName,year,month,day);
//			
//			String updatePebUsers = addOrUpdatePebUsers(pebUsers, null);
//			return updatePebUsers;
//			if(pebUsers!=null) {
//				pebUsers.setZsNumber(zsCount);
//				if(zsCount!=null && zsCount!=0 && pebUsers.getActualNum()!=null) {
//					BigDecimal zsDecimal = new BigDecimal(zsCount);
//					BigDecimal sjDecimal = new BigDecimal(pebUsers.getActualNum());
//					float avgNum = zsDecimal.divide(sjDecimal,3,BigDecimal.ROUND_HALF_UP).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
//					pebUsers.setAvgNumber(avgNum);
//					BigDecimal avgNumDecimal = new BigDecimal(avgNum);
//					BigDecimal shDecimal = new BigDecimal(pebUsers.getGzHour());
//					pebUsers.setUPPH(avgNumDecimal.divide(shDecimal).floatValue());
//					totalDao.update(pebUsers);
//				}
//			}else {
//				pebUsers = new PebUsers();
//				pebUsers.setBanZu(banjin.getProcessName());
//				pebUsers.setYear(year);
//				pebUsers.setMonth(month);
//				pebUsers.setDay(day);
//				pebUsers.setZsNumber(zsCount);
//				pebUsers.setCategory("bj");
//				totalDao.save(pebUsers);
//			}
//		}
//		return "保存成功";
	}

	//获取点评列表
	@Override
	public List<String> getDianPingList(String banzu) {
		List<String> dianpingList = new ArrayList<String>();
		dianpingList.add("2018年目标今年目标是日产出总量提升10%,日人均产出在去年基础上提升30%。");
		String yearZongLiangQushi = "上升";//总量趋势
		Float yearZongLiangNum = 0f;
		String yearDayQushi = "上升";
		Float yeardayNumber = 0f;
		
		String monthZongLiangQushi = "上升";//月份总量趋势
		Float monthZongLiangNum = 0f;
		String monthDayQushi = "上升";
		Float monthDayNumber = 0f;
		int currentYear = Integer.parseInt(Util.getDateTime("yyyy"));
		int currentMonth = Integer.parseInt(Util.getDateTime("MM"));
		
		int lastYear = 2017;
//		Integer maxYear = (Integer) totalDao.getObjectByCondition("select max(year) from PebUsers");
		SubTeam subTeam =(SubTeam) totalDao.getObjectByCondition("from SubTeam where subName=?", banzu);
		if(subTeam==null) {
			return null;
		}
		BigDecimal totalZsNumber = getzsNumber(banzu, currentYear, null, null);
		Integer chuQinDays = getChuQinDays(banzu, currentYear, null, null);
		if(chuQinDays==0) {
			chuQinDays=1;
		}
		BigDecimal zsNumber = totalZsNumber.divide(new BigDecimal(chuQinDays),3,BigDecimal.ROUND_HALF_UP).setScale(3,BigDecimal.ROUND_HALF_UP);
		if(2018 == currentYear && subTeam!=null && subTeam.getAvg2017()!=null) {
			lastYear = 2017;
			Float avg2017 = subTeam.getAvg2017();
//			Float personCount = getPersionCountByBanzu(banzu, currentYear,null, null);
//			Float avg2018 = zsNumber.divide(new BigDecimal(personCount),3,BigDecimal.ROUND_HALF_UP).setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
			float zsNumber2017 = 0;
			if(subTeam.getCcCount2017()!=null) {
				zsNumber2017 = subTeam.getCcCount2017();
			}else {
				if(subTeam.getPersonCount2017()!=null && avg2017!=null) {
					zsNumber2017 = new BigDecimal(subTeam.getPersonCount2017()).multiply(new BigDecimal(avg2017)).floatValue();
				}
			}
			//实际产出仓比较
			if(zsNumber2017>zsNumber.floatValue()) {
				yearZongLiangQushi = "下降";
				yearZongLiangNum = new BigDecimal(1).subtract(zsNumber.divide(new BigDecimal(zsNumber2017),
						3,BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100)).floatValue();
			}else {
				if(zsNumber2017!=0) {
					yearZongLiangNum = zsNumber.divide(new BigDecimal(zsNumber2017),3,BigDecimal.ROUND_HALF_UP)
							.subtract(new BigDecimal(1)).multiply(new BigDecimal(100)).floatValue();
				}
				
			}
			
			//日人均仓比较
			Float personCount = getPersionCountByBanzu(banzu, currentYear, null, null);
			if(personCount==0) {
				personCount=1f;
			}
			float currentAvgNumber = totalZsNumber.divide(new BigDecimal(personCount),3,BigDecimal.ROUND_HALF_UP)
					.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
			if(subTeam.getAvg2017()!=null) {
				if(subTeam.getAvg2017()>currentAvgNumber) {
					yearDayQushi= "下降";
					yeardayNumber = new BigDecimal(1).subtract(new BigDecimal(currentAvgNumber)
							.divide(new BigDecimal(subTeam.getAvg2017())
							,3,BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100)).floatValue();
				}else {
					yeardayNumber = new BigDecimal(currentAvgNumber)
							.divide(new BigDecimal(subTeam.getAvg2017())
							,3,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(1)).multiply(new BigDecimal(100)).floatValue();
				}
			}
		}else {
			lastYear = currentYear-1;
			BigDecimal lastYeartotalZsNumber = getzsNumber(banzu, currentYear-1, null, null);
			Integer lastYearchuQinDays = getChuQinDays(banzu, currentYear-1, null, null);
			if(lastYearchuQinDays==0) {
				lastYearchuQinDays=1;
			}
			BigDecimal lastYearzsNumber = lastYeartotalZsNumber.divide(new BigDecimal(lastYearchuQinDays),3,BigDecimal.ROUND_HALF_UP).setScale(3,BigDecimal.ROUND_HALF_UP);
			
			if(lastYearzsNumber.floatValue()>zsNumber.floatValue()) {
				yearZongLiangQushi = "下降";
			}
			
			if(yearZongLiangQushi.equals("下降")) {
				yearZongLiangNum = new BigDecimal(1).subtract(zsNumber.divide(lastYearzsNumber,3,BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100)).floatValue();
			}else {
				if(lastYearzsNumber.floatValue()!=0) {
					yearZongLiangNum = zsNumber.divide(lastYearzsNumber,3,BigDecimal.ROUND_HALF_UP)
							.subtract(new BigDecimal(1)).multiply(new BigDecimal(100)).floatValue();
				}
			}
			
			
			//日人均仓比较
			Float personCount = getPersionCountByBanzu(banzu, currentYear, null, null);
			if(personCount==0) {
				personCount=1f;
			}
			float currentAvgNumber = totalZsNumber.divide(new BigDecimal(personCount),3,BigDecimal.ROUND_HALF_UP)
					.setScale(3,BigDecimal.ROUND_HALF_UP).floatValue();
			
			if(subTeam.getAvg2017()!=null) {
				if(subTeam.getAvg2017()>currentAvgNumber) {
					yearDayQushi= "下降";
					yeardayNumber = new BigDecimal(1).subtract(new BigDecimal(currentAvgNumber)
							.divide(new BigDecimal(subTeam.getAvg2017())
							,3,BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100)).floatValue();
				}else {
					yeardayNumber = new BigDecimal(currentAvgNumber)
							.divide(new BigDecimal(subTeam.getAvg2017())
							,3,BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(1)).multiply(new BigDecimal(100)).floatValue();
				}
			}
		}
		dianpingList.add(currentYear+"年较"+lastYear+"年日产出总量"+yearZongLiangQushi+yearZongLiangNum+"%,日人均产量"+yearDayQushi+yeardayNumber+"%！");
		
		
		Integer lastMonth = 0;//上个月
		//月份比较
		Integer lastMonthYear= 2018;
		if(currentMonth==1) {
			lastMonthYear = currentYear-1;
			lastMonth = 12;
		}else {
			lastMonthYear = currentYear;
			lastMonth = currentMonth-1;
		}
		
		//上个月的zsNumber
		BigDecimal lastMonthZsNumber = getzsNumber(banzu, lastMonthYear, lastMonth, null);
		Integer lastMonthChuQinDays = getChuQinDays(banzu, lastMonthYear, lastMonth, null);
		if(lastMonthZsNumber!=null && lastMonthChuQinDays!=null && lastMonthChuQinDays!=0) {
			BigDecimal lastAvgzsNumber = lastMonthZsNumber.divide(new BigDecimal(lastMonthChuQinDays)
					,3,BigDecimal.ROUND_HALF_UP).setScale(3,BigDecimal.ROUND_HALF_UP);
			Float lastPersonCount = getPersionCountByBanzu(banzu, lastMonthYear, lastMonth, null);
			
			//本月的zsNumber
			BigDecimal monthZsNumber = getzsNumber(banzu, currentYear, currentMonth, null);
			Integer monthChuQinDays = getChuQinDays(banzu, currentYear, currentMonth, null);
			if(monthChuQinDays==0) {
				monthChuQinDays = 1;
			}
			BigDecimal monthzsAvgNumber = monthZsNumber.divide(new BigDecimal(monthChuQinDays)
					,3,BigDecimal.ROUND_HALF_UP).setScale(3,BigDecimal.ROUND_HALF_UP);
			Float monthPersonCount = getPersionCountByBanzu(banzu, currentYear, currentMonth, null);
			if(lastAvgzsNumber.floatValue()>monthzsAvgNumber.floatValue()) {
				monthZongLiangQushi = "下降";
				monthZongLiangNum = new BigDecimal(1).subtract(monthzsAvgNumber.divide(
						lastAvgzsNumber,3,BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100)).floatValue();
			}else {
				if(lastAvgzsNumber.floatValue()!=0) {
					monthZongLiangNum = monthzsAvgNumber.divide(lastAvgzsNumber,3,BigDecimal.ROUND_HALF_UP)
							.subtract(new BigDecimal(1)).multiply(new BigDecimal(100)).floatValue();
				}
			}
			
			BigDecimal lastPersonAvgNumber = lastMonthZsNumber.divide(new BigDecimal(lastPersonCount),3,BigDecimal.ROUND_HALF_UP);
			
			BigDecimal currPersonAvgNumber;
			if(monthPersonCount==0) {
				currPersonAvgNumber = new BigDecimal(0);
			}else {
				currPersonAvgNumber = monthZsNumber.divide(new BigDecimal(monthPersonCount),3,BigDecimal.ROUND_HALF_UP);
			}
			if(lastPersonAvgNumber.floatValue()>currPersonAvgNumber.floatValue()) {
				monthDayQushi = "下降";
				monthDayNumber = new BigDecimal(1).subtract(currPersonAvgNumber.divide(
						lastPersonAvgNumber,3,BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100)).floatValue();
			}else {
				if(lastPersonAvgNumber.floatValue()!=0) {
					monthDayNumber = currPersonAvgNumber.divide(lastPersonAvgNumber,3,BigDecimal.ROUND_HALF_UP)
							.subtract(new BigDecimal(1)).multiply(new BigDecimal(100)).floatValue();
				}
			}
		}
		
		
		
		
		dianpingList.add(currentMonth+"月较"+lastMonth+"月日产出总量"+monthZongLiangQushi+monthZongLiangNum+"%,日人均产量"+monthDayQushi+monthDayNumber+"%！");
		return dianpingList;
	}

	@Override
	public String deletePebProduction(Integer id, String pageStatus) {
		if(id!=null) {
			PebProduction pebProduction = (PebProduction) totalDao.getObjectById(PebProduction.class, id);
			if(pebProduction!=null) {
				String banzu = pebProduction.getBanzu();
				Integer year = pebProduction.getYear();
				Integer month = pebProduction.getMonth();
				Integer day = pebProduction.getDay();
				boolean delete = totalDao.delete(pebProduction);
				if(delete) {
					PebUsers pebUsers = (PebUsers) totalDao.getObjectByCondition("from PebUsers "
							+ "	where banZu = ? and year=? and month=? and day=?", banzu,year,month,day);
					if(pebUsers!=null) {
						
						String hql="select sum(zsNumber) from PebProduction where banzu=?";
						Float zsNum=null;
						if (pebUsers.getBanZu().equals("数据机房")) {
							zsNum = (Float) totalDao.getObjectByCondition(hql
									+" and zsNumber is not null and year=? and month=?",
									pebUsers.getBanZu(),pebUsers.getYear(),pebUsers.getMonth());
						}else {
							zsNum = (Float) totalDao.getObjectByCondition(hql
									+" and zsNumber is not null and year=? and month=? and day=?",
									pebUsers.getBanZu(),pebUsers.getYear(),pebUsers.getMonth(),pebUsers.getDay());
						}
						
						
						if(zsNum!=null && zsNum!=0) {
							pebUsers.setZsNumber(zsNum);
							
							BigDecimal szBigDecimal = new BigDecimal(zsNum);
							BigDecimal actualBigdecimal = new BigDecimal(pebUsers.getActualNum());
							float renjun = szBigDecimal.divide(actualBigdecimal,5,BigDecimal.ROUND_HALF_UP).floatValue();
							pebUsers.setAvgNumber(renjun);
							if(pebUsers.getGzHour()!=null) {
								BigDecimal bigDecimal = new BigDecimal(renjun);
								BigDecimal bigDecimal2 = new BigDecimal(pebUsers.getGzHour());
								float UPPH = bigDecimal.divide(bigDecimal2,5,BigDecimal.ROUND_HALF_UP).floatValue();
								pebUsers.setUPPH(UPPH);
							}
							
							//totalDao.update(pebUsers);
						}else {
							//totalDao.delete(pebUsers);
						}
						totalDao.update(pebUsers);
						return "删除成功";
						
					}else {
						return "删除成功";
					}
				}
				
			}
		}
		return "没有找到对应信息，删除失败";
	}

	@Override
	public String deletePebProBanjin(Integer id, String pageStatus) {
		if(id!=null) {
			PebProductionBanjin pebProductionBanjin = (PebProductionBanjin) totalDao.getObjectById(PebProductionBanjin.class, id);
			if(pebProductionBanjin!=null) {
				String banzu = pebProductionBanjin.getProcessName();
				Integer year = pebProductionBanjin.getYear();
				Integer month = pebProductionBanjin.getMonth();
				Integer day = pebProductionBanjin.getDay();
				boolean delete = totalDao.delete(pebProductionBanjin);
				if(delete) {
					PebUsers pebUsers = (PebUsers) totalDao.getObjectByCondition("from PebUsers "
							+ "	where banZu = ? and year=? and month=? and day=?", banzu,year,month,day);
					if(pebUsers!=null) {
						
						String hql ="select sum(zsNumber) from PebProductionBanjin where processName=?";
						Float zsNum= (Float) totalDao.getObjectByCondition(hql
									+" and zsNumber is not null and year=? and month=? and day=?",banzu,year,month,day);
						if(zsNum!=null && zsNum!=0 && pebUsers.getActualNum()!=null) {
							pebUsers.setZsNumber(zsNum);
							
							BigDecimal szBigDecimal = new BigDecimal(zsNum);
							BigDecimal actualBigdecimal = new BigDecimal(pebUsers.getActualNum());
							float renjun = szBigDecimal.divide(actualBigdecimal,5,BigDecimal.ROUND_HALF_UP).floatValue();
							pebUsers.setAvgNumber(renjun);
							if(pebUsers.getGzHour()!=null) {
								BigDecimal bigDecimal = new BigDecimal(renjun);
								BigDecimal bigDecimal2 = new BigDecimal(pebUsers.getGzHour());
								float UPPH = bigDecimal.divide(bigDecimal2,5,BigDecimal.ROUND_HALF_UP).floatValue();
								pebUsers.setUPPH(UPPH);
							}
							totalDao.update(pebUsers);
						}else {
							totalDao.delete(pebUsers);
						}
						return "删除成功";
						
					}else {
						return "删除成功";
					}
				}
			}
		}
		return "没有找到对应信息，删除失败";
	}
	

	@Override
	public String deletePebUsers(Integer id, String pageStatus) {
		if(id!=null && id>0) {
			PebUsers pebUsers = (PebUsers) totalDao.getObjectById(PebUsers.class, id);
			if(pebUsers!=null) {
				if((pebUsers.getBorrowNum()!=null && pebUsers.getBorrowNum()>0) || 
						(pebUsers.getLendNum()!=null && pebUsers.getLendNum()>0) ||
						(pebUsers.getDangAnNum()!=null && pebUsers.getDangAnNum()>0)) {
					return "请将借入、借出记录和人事档案数删除掉，才能删除人事档案信息。";
				}
				boolean delete = totalDao.delete(pebUsers);
				if(delete) {
					return "删除成功";
				}
				
			}
		}
		return "没有找到删除信息";
	}

	@Override
	public String updatePebProMultpart(PebProductionBanjin banjin,
			Integer goYear,Integer goMonth,Integer goDay,Integer toYear,Integer toMonth,Integer toDay) {
		
		PebProductionBanjin pebProductionBanjin = (PebProductionBanjin) totalDao.getObjectByCondition(
				"from PebProductionBanjin where processName=? and year=? and month=? and day=?",
				banjin.getProcessName(),goYear,goMonth,goDay);
		if(pebProductionBanjin!=null) {
			pebProductionBanjin.setXiShu(banjin.getXiShu());
			String update = addOrUpdatePebProBanjin(pebProductionBanjin, null);
			if(!"更新成功".equals(update)) {
				return "更新失败";
			}
		}
		
		if((int)goYear==(int)toYear && (int)goMonth==(int)toMonth && (int)goDay==(int)toDay) {
			return "更新成功";
		}
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parse;
		try {
			parse = format.parse(goYear+"-"+goMonth+"-"+goDay);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("日期转换异常:"+e);
		}
		calendar.setTime(parse);
		calendar.add(Calendar.DAY_OF_YEAR, +1);
		Date time = calendar.getTime();
		String currentDate = format.format(time);
		if(currentDate.equals(toYear+"-"+toMonth+"-"+toDay)) {
			return updatePebProMultpart(banjin, goYear, goMonth, goDay, toYear, toMonth, toDay);
		}else {
			format = new SimpleDateFormat("yyyy");
			String year = format.format(time);
			format = new SimpleDateFormat("MM");
			String month = format.format(time);
			format = new SimpleDateFormat("dd");
			String day = format.format(time);
			int currentYear = Integer.parseInt(year);
			int currentMonth = Integer.parseInt(month);
			int currentDay = Integer.parseInt(day);
			return updatePebProMultpart(banjin, currentYear, currentMonth, currentDay, toYear, toMonth, toDay);
//			return settingTarget(pebUsers, currentYear, currentMonth, currentDay, toYear, toMonth, toDay);
		}
		
	}
	
	
	//获取离最近一天有效的信息(日期：天)
	private Integer getCurrentDay(Integer currentYear,Integer currentMonth,Integer currentDay,String category) {
//		int currentDay = Integer.parseInt(Util.getDateTime("dd"));
		String append = "";
		if(category!=null && category.length()>0) {
			append = " and category = '"+category+"'";
		}
		List list = totalDao.query("from PebUsers where actualNum is not null and actualNum>0 and year=? and month=? and day=?"+append,
				currentYear,currentMonth,currentDay);
		if((list!=null && list.size()>0) || currentDay==1) {
			return currentDay;
		}
		
		return getCurrentDay(currentYear, currentMonth,currentDay-1,category);
	}

	@Override
	public SubTeam getSubTeamByFuzeren(String userName) {
		
		if(userName!=null && userName.length()>0) {
			return (SubTeam) totalDao.getObjectByCondition("from SubTeam where principals =?", userName);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubTeam> getminSubTeam() {
		List<Integer> fatherIdList = totalDao.query("select distinct fatherId from SubTeam");
		StringBuffer buffer = new StringBuffer("0");
		for (Integer integer : fatherIdList) {
			if(integer!=null) {
				buffer.append(","+integer);
			}
		}
		List<SubTeam> list = totalDao.query("from SubTeam where id not in ("+buffer.toString()+")");
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String applyBorrowPerson(PebBorrowAndLendLog log,List<PebBorrowAndLendLog> logList, String pageStatus) {
		Users loginUser = Util.getLoginUser();
		if(loginUser==null) {
			return "请先登录";
		}
		if(log==null) {
			return "不要随意提交哦";
		}
		if(log.getBorrowNum()==null) {
			return "借入人数不能为空";
		}
		if(log.getGzHour()==null) {
			return "借入时间不能为空";
		}
		PebBanzuJiegou pebBanzuJiegou = (PebBanzuJiegou) totalDao.getObjectById(PebBanzuJiegou.class, log.getBorrowJiegouId());
		if(pebBanzuJiegou!=null) {
			//查找班组负责人
			String principals = pebBanzuJiegou.getPrincipals();
			if(principals==null || principals.equals("")) {
				Integer fatherId = pebBanzuJiegou.getFatherId();
				PebBanzuJiegou jiegou=null;
				for(int i=0;i<6;i++) { //如果负责人不存在，向上查找6层
					if(fatherId==null) {
						return "没有找到借入班组负责人";
					}
					jiegou = (PebBanzuJiegou) totalDao.getObjectById(PebBanzuJiegou.class,fatherId );
					fatherId = jiegou.getFatherId();
					if(jiegou!=null && jiegou.getPrincipals()!=null && !jiegou.getPrincipals().equals("")) {
						principals = jiegou.getPrincipals();
						break;
					}
				}
			}
			
			//注释的为借出班组人审批的。
//			PebBanzuJiegou lendBanZuJiegou = (PebBanzuJiegou)totalDao.getObjectById(PebBanzuJiegou.class, log.getLendJiegouId());
//			if(lendBanZuJiegou!=null) {
//				String fuzeren = lendBanZuJiegou.getPrincipals();
//				if(fuzeren==null || fuzeren.equals("")) {
//					Integer fatherId = lendBanZuJiegou.getFatherId();
//					PebBanzuJiegou jiegou=null;
//					for(int i=0;i<6;i++) { //如果负责人不存在，向上查找6层
//						if(fatherId==null) {
//							return "没有找到借出班组负责人";
//						}
//						jiegou = (PebBanzuJiegou) totalDao.getObjectById(PebBanzuJiegou.class,fatherId );
//						fatherId = jiegou.getFatherId();
//						if(jiegou!=null && jiegou.getPrincipals()!=null && !jiegou.getPrincipals().equals("")) {
//							fuzeren = jiegou.getPrincipals();
//							break;
//						}
//					}
//				}
//				if(fuzeren!=null) {
//					principals= principals+";"+fuzeren;
//				}
//			}
			String[] split = principals.split(";");
			StringBuffer buffer = new StringBuffer();
			for (String string : split) {
				Integer usersId = Integer.parseInt(string);
//				if((int)usersId==(int)loginUser.getId()) {
//					continue;
//				}
				Users users = (Users) totalDao.getObjectById(Users.class, usersId);
				if(users!=null) {
					if(buffer.toString().equals("")) {
						buffer.append("1:"+string);
					}else {
						buffer.append(",1:"+string);
					}
				}
			}
			//查找班组负责人结束
			
			//查询班组是否生成简报--如果是则添加
			try {
				SubTeam subTeam = getSubTeamByBZJGname(log.getSqBanzu(),totalDao);
				
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String pebUserHql = "from PebUsers where banZu = ? and year=? "
						+ " and month=? and day=?";
				String processName = "人员借入申请";
				boolean update = false;
				if(logList!=null) {
					for (PebBorrowAndLendLog pebBorrowAndLendLog : logList) {
						if(pebBorrowAndLendLog==null || pebBorrowAndLendLog.getSqTime()==null || pebBorrowAndLendLog.getSqTime().equals("")) {
							continue;
						}
						pebBorrowAndLendLog.setSqBanzu(log.getSqBanzu());
						pebBorrowAndLendLog.setBorrowBanzu(log.getBorrowBanzu());
						pebBorrowAndLendLog.setGzHour(log.getGzHour());
						pebBorrowAndLendLog.setBorrowNum(log.getBorrowNum());
						pebBorrowAndLendLog.setRemarks(log.getRemarks());
						
						pebBorrowAndLendLog.setAddTime(Util.getDateTime());
						pebBorrowAndLendLog.setAddUserId(loginUser.getId());
						pebBorrowAndLendLog.setAddUserName(loginUser.getName());
//							log.setBorrowBanzu(log.getSqBanzu());
						pebBorrowAndLendLog.setLendBanzu(pebBorrowAndLendLog.getSqBanzu());
						//保存pebUsers并获得Id
						PebUsers pebUsers=null;
						String sqTime = pebBorrowAndLendLog.getSqTime();
						Date date = simpleDateFormat.parse(sqTime);
						Integer year = Integer.parseInt((new SimpleDateFormat("yyyy")).format(date));
						Integer month = Integer.parseInt((new SimpleDateFormat("MM")).format(date));
						Integer day = Integer.parseInt((new SimpleDateFormat("dd")).format(date));
						if(subTeam!=null) {
							
							
							pebUsers = (PebUsers) totalDao.getObjectByCondition(pebUserHql, subTeam.getSubName(),year,month,day);
							if(pebUsers==null) {
								pebUsers = new PebUsers();
								pebUsers.setBanZu(subTeam.getSubName());
								pebUsers.setYear(year);
								pebUsers.setMonth(month);
								pebUsers.setDay(day);
								totalDao.save(pebUsers);
								pebBorrowAndLendLog.setPebUsersId(pebUsers.getId());
							}else {
								pebBorrowAndLendLog.setPebUsersId(pebUsers.getId());
							}
							
							
						}
						//保存pebUsers并获得Id结束
						
						update = totalDao.save(pebBorrowAndLendLog);
						
						//校验借出班组实际出勤人数是否合法
						SubTeam lendSubTeam = getSubTeamByBZJGname(pebBorrowAndLendLog.getBorrowBanzu(),totalDao);
						if(lendSubTeam!=null) {
							PebUsers lendPebUsers= (PebUsers) totalDao.getObjectByCondition(pebUserHql,
									lendSubTeam.getSubName(),year,month,day);
							if(lendPebUsers!=null) {
								List<PebBorrowAndLendLog> borrowAndLendLogList = totalDao.query("from PebBorrowAndLendLog "
										+ " where pebUsersId =? and (epStatus is null or (epStatus <>'已取消' and epStatus <>'打回'))",
										lendPebUsers.getId());
								Integer borrowCount= 0;
								Integer lendCount = 0;
								for (PebBorrowAndLendLog pebLog : borrowAndLendLogList) {
									if(pebLog.getLendBanzu()!=null && pebLog.getLendNum()!=null) {
										lendCount+=pebLog.getLendNum();
									}
									if(pebLog.getBorrowBanzu()!=null && pebLog.getBorrowNum()!=null) {
										borrowCount+=pebLog.getBorrowNum();
									}
								}
								if(lendPebUsers.getDangAnNum()!=null) {
									float noChuQin = 0;
									if(lendPebUsers.getNoChuQinNum()!=null) {
										noChuQin=lendPebUsers.getNoChuQinNum();
									}
									float actualNum = lendPebUsers.getDangAnNum()+borrowCount-lendCount-noChuQin-log.getBorrowNum();
									if(actualNum<0) {
										throw new RuntimeException("申请失败：借出班组实际出勤人数将小于0");
									}
								}
							}
							
						}
						
						
						String remarks = "";
						if(pebBorrowAndLendLog.getRemarks()!=null && pebBorrowAndLendLog.getRemarks().length()>0) {
							remarks = "，备注:"+pebBorrowAndLendLog.getRemarks();
						}
						if(update) {
							Integer epId = CircuitRunServerImpl.createProcessbf(processName, PebBorrowAndLendLog.class,pebBorrowAndLendLog.getId(),
									"epStatus", "id", null, "班组："+pebBorrowAndLendLog.getSqBanzu()+",申请人："+pebBorrowAndLendLog.getAddUserName()+"，申请借出人数："+
											pebBorrowAndLendLog.getBorrowNum()+ "人"+remarks+"。请您审批。", true,buffer.toString(),"3");
							if (epId != null && epId > 0) {
								pebBorrowAndLendLog.setEpId(epId);
								CircuitRun circuitRun = (CircuitRun) totalDao.get(CircuitRun.class, epId);
								if ("同意".equals(circuitRun.getAllStatus())
										&& "审批完成".equals(circuitRun
												.getAuditStatus())) {
									pebBorrowAndLendLog.setEpStatus("同意");
								} else {
									pebBorrowAndLendLog.setEpStatus("未审批");
								}
								update= totalDao.update(pebBorrowAndLendLog);
							}
						
						}
						
						if(!update) {
							return "申请失败";
						}
					}
					
				}
				if(update) {
					return "申请成功";
				}else {
					return "没有记录";
				}
			} catch (ParseException e) {
				e.printStackTrace();
				return "日期转换失败";
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.toString());
			}
			
		}else {
			return "没有找到要借出的班组";
		}
	}

	@Override
	public Map<String, Object> findborrowLogByCon(PebBorrowAndLendLog log, int pageNo, Integer pageSize,
			String pageStatus) {
		if(log==null) {
			log = new PebBorrowAndLendLog();
		}
		
		String hql = totalDao.criteriaQueries(log, null);
		if(pageStatus!=null && pageStatus.equals("audit")) {
			hql+=" and epId in (select c.id from ExecutionNode e join e.circuitRun c  " + 
					"where c.allStatus not in ('同意','打回') and e.auditLevel=c.auditLevel  and e.auditStatus='未审批' "
					+ "and e.auditUserId="+Util.getLoginUser().getId()+" ) ";
			pageNo=0;
			pageSize=0;
		}
		
		hql+=" and epId is not null order by sqTime desc";
		List<PebBorrowAndLendLog> list = totalDao.findAllByPage(hql, pageNo, pageSize, null);
		Integer count = totalDao.getCount(hql);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("count", count);
		map.put("list", list);
		return map;
	}

	@Override
	public String importOtherData(File attachment, String attachmentFileName, String pageStatus) {
		StringBuffer importMessage = new StringBuffer();
		Users user = Util.getLoginUser();
		if (user == null) {
			return "请先登录";
		}
		String fileName = "peb-"+Util.getDateTime("yyyyMMddhhmmss") + ".xls";
		// 上传到服务器
		String fileRealPath = ServletActionContext.getServletContext()
				.getRealPath("/upload/file/peb")
				+ "/" + fileName;
		File file = new File(fileRealPath);
		try {
			FileCopyUtils.copy(attachment, file);
			// 开始读取excle表格
			InputStream is = new FileInputStream(fileRealPath);// 创建文件流;
			Workbook workbook = null;
			 if(attachmentFileName.endsWith(".xls")) {
				 workbook = new HSSFWorkbook(is);
	        } else if(attachmentFileName.endsWith(".xlsx")) {
	        	workbook = new XSSFWorkbook(is);
	        } 
			if(pageStatus!=null && pageStatus.equals("importProduction")) {//装配产品下线
				 Sheet sheet = workbook.getSheet("产品下线");
				if(sheet==null) {
					importMessage.append("没有找到工作薄为产品下线的文件");
				}else {
					List<PebProduction> productionList = analysisProduction(sheet, pageStatus);
					String message =null;
					for (int i = 0; i < productionList.size(); i++) {
						message = addOrUpdatePebProduction(productionList.get(i), pageStatus);
						if(message!=null && !message.equals("保存成功")) {
							importMessage.append("产品下线：第"+(i+2)+"行，错误："+message+"\n");
						}
					}
				}
				
			}else if(pageStatus!=null && pageStatus.equals("importBanjinProduction")){//钣金下线
				Sheet amountOfFinish = workbook.getSheet("加工量");
				if(amountOfFinish==null) {
					importMessage.append("没有找到工作簿为加工量输入的文件");
				}else {
					String message = importBanJinProduction(amountOfFinish, null,0,0);
					importMessage.append(message);
				}
			}else if(pageStatus!=null && pageStatus.equals("importpebUsers")) {//人事档案
				Sheet sheet = workbook.getSheet("人事档案");
				if(sheet==null) {
					importMessage.append("没有找到工作簿为人事档案的文件");
				}else {
					List<PebUsers> pebUsersList = analysisUsersAndLogSave(sheet, pageStatus);
					if(pebUsersList!=null && pebUsersList.size()>0) {
						String message = null;
						for (int i = 0; i < pebUsersList.size(); i++) {
							PebUsers pebUsers = pebUsersList.get(i);
							if(pebUsers.getBanZu()==null || pebUsers.getBanZu().equals("")) {
								continue;
							}
							PebUsers users = (PebUsers) totalDao.getObjectByCondition("from PebUsers where banZu =? and year=? and month=? and day=?",
									pebUsers.getBanZu(),pebUsers.getYear(),pebUsers.getMonth(),pebUsers.getDay());
							if(users!=null) {
								pebUsers.setId(users.getId());
								if(pebUsers.getMbTarget()==null) {
									pebUsers.setMbTarget(users.getMbTarget());
								}
								if(pebUsers.getGzHour()==null) {
									pebUsers.setGzHour(users.getGzHour());
								}
								if(pebUsers.getNoChuQinNum()==null) {
									pebUsers.setNoChuQinNum(users.getNoChuQinNum());
								}
								if(pebUsers.getDangAnNum()==null) {
									pebUsers.setDangAnNum(users.getDangAnNum());
								}
								totalDao.clear();
							}else {
								totalDao.save(pebUsers);
							}
							Integer dangAnNum = pebUsers.getDangAnNum();
							Float noChuQinNum = pebUsers.getNoChuQinNum();
							if(dangAnNum==null) {
								importMessage.append("第"+(i+2)+"行，错误：没有人事档案数。\n");
							}
							if(noChuQinNum==null) {
								noChuQinNum=0f;
							}
							Integer borrowNum = 0;
							Integer lendNum = 0;
							for (PebBorrowAndLendLog borrowLog : pebUsers.getBorrowLogList()) {
								if(borrowLog!=null && borrowLog.getBorrowNum()!=null) {
									borrowNum+=borrowLog.getBorrowNum();
									borrowLog.setPebUsersId(pebUsers.getId());
									PebBorrowAndLendLog log = (PebBorrowAndLendLog) totalDao.getObjectByCondition("from PebBorrowAndLendLog"
											+ " where pebUsersId=? and borrowBanzu=?  and borrowNum=?", 
											borrowLog.getPebUsersId(),borrowLog.getBorrowBanzu(),borrowLog.getBorrowNum());
									if(log==null) {
										totalDao.save(borrowLog);
									}
								}
							}
							for (PebBorrowAndLendLog lendLog : pebUsers.getLendLogList()) {
								if(lendLog!=null && lendLog.getLendNum()!=null) {
									lendNum+=lendLog.getLendNum();
									lendLog.setPebUsersId(pebUsers.getId());
									PebBorrowAndLendLog log = (PebBorrowAndLendLog) totalDao.getObjectByCondition("from PebBorrowAndLendLog"
											+ " where pebUsersId=? and lendBanzu=?  and lendNum=?", 
											lendLog.getPebUsersId(),lendLog.getLendBanzu(),lendLog.getLendNum());
									if(log==null) {
										totalDao.save(lendLog);
									}
								}
							}
							message = addOrUpdatePebUsers(pebUsers, "noDelete");
							if(message!=null && !message.equals("保存成功")) {
								importMessage.append("第"+(i+2)+"行，错误："+message+"\n");
							}
						}
					}else {
						importMessage.append("没有获取人事档案到记录");
					}
				}
			}
			
			workbook.close();
			is.close();
			return importMessage.toString();
		}catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}
	private List<PebUsers> analysisUsersAndLogSave(Sheet sheet,String pageStatus){
		List<PebUsers> list = new ArrayList<PebUsers>();
		PebUsers pebUsers = null;
		BigDecimal bigDecimal = null;
		for (Row row : sheet) {
			if (row.getRowNum()==0) {
				continue;
			}
			pebUsers = new PebUsers();
			List<PebBorrowAndLendLog> borrowLogList = new ArrayList<PebBorrowAndLendLog>();
			List<PebBorrowAndLendLog> lendLogList = new ArrayList<PebBorrowAndLendLog>();
			for (Cell cell : row) {
				switch (cell.getColumnIndex()) {
				case 0:
					cell.setCellType(CellType.STRING);
					pebUsers.setBanZu(cell.getStringCellValue());
					break;
				case 1:
					cell.setCellType(CellType.NUMERIC);
					bigDecimal = new BigDecimal(cell.getNumericCellValue());
					pebUsers.setYear(bigDecimal.intValue());
					
					break;
				case 2:
					cell.setCellType(CellType.NUMERIC);
					bigDecimal = new BigDecimal(cell.getNumericCellValue());
					pebUsers.setMonth(bigDecimal.intValue());
					break;
				case 3:
					cell.setCellType(CellType.NUMERIC);
					bigDecimal = new BigDecimal(cell.getNumericCellValue());
					pebUsers.setDay(bigDecimal.intValue());
					break;
				case 4:
					cell.setCellType(CellType.NUMERIC);
					bigDecimal = new BigDecimal(cell.getNumericCellValue());
					pebUsers.setDangAnNum(bigDecimal.intValue());
					
					break;
				case 5:
					cell.setCellType(CellType.NUMERIC);
					bigDecimal = new BigDecimal(cell.getNumericCellValue());
					if(bigDecimal.floatValue()>0) {
						pebUsers.setNoChuQinNum(bigDecimal.floatValue());
					}
					break;
				case 6:
					if(cell.getCellTypeEnum()!=CellType.ERROR) {
						cell.setCellType(CellType.NUMERIC);
						bigDecimal = new BigDecimal(cell.getNumericCellValue());
						if(bigDecimal.floatValue()>0) {
							pebUsers.setGzHour(bigDecimal.floatValue());
						}
					}
					break;
				default:
					break;
				}
			}
			PebBorrowAndLendLog log = null;
			for(int i=7;i<=100;i=i+4) {
				log = new PebBorrowAndLendLog();
				if((row.getCell(i)!=null || row.getCell(i+1)!=null ) && row.getCell(i+2)!=null) {
					
					row.getCell(i+2).setCellType(CellType.NUMERIC);
					bigDecimal = new BigDecimal(row.getCell(i+2).getNumericCellValue());
					if(row.getCell(i+3)!=null) {
						log.setRemarks(row.getCell(i+3).getStringCellValue());
					}
					
					if(row.getCell(i)!=null && !row.getCell(i).getStringCellValue().equals("")) {
						log.setBorrowNum(bigDecimal.intValue());
						log.setBorrowBanzu(row.getCell(i).getStringCellValue());
						borrowLogList.add(log);
					}
					if(row.getCell(i+1)!=null&&!row.getCell(i+1).getStringCellValue().equals("")) {
						log.setLendNum(bigDecimal.intValue());
						log.setLendBanzu(row.getCell(i+1).getStringCellValue());
						lendLogList.add(log);
					}
				}
			}
			pebUsers.setBorrowLogList(borrowLogList);
			pebUsers.setLendLogList(lendLogList);
			list.add(pebUsers);
		}
		
		return list;
	}

	@Override
	public List<PebBanzuJiegou> findAllPebBanzuJiegou() {
		List<PebBanzuJiegou> list = totalDao.query("from PebBanzuJiegou");
		return list;
	}

	@Override
	public String addPebBanzuJiegou(PebBanzuJiegou jiegou, String pageStatus) {
		boolean save = totalDao.save(jiegou);
		
		return save+"";
	}

	@Override
	public String updatePebBanzuJiegou(PebBanzuJiegou jiegou, String pageStatus) {
		if(jiegou!=null && jiegou.getId()!=null &&jiegou.getName()!=null) {
			String name = jiegou.getName();
			Character se = jiegou.getSe();
			jiegou = (PebBanzuJiegou) totalDao.getObjectById(PebBanzuJiegou.class, jiegou.getId());
			jiegou.setName(name);
			jiegou.setSe(se);
			boolean update = totalDao.update(jiegou);
			return update+"";
		}else {
			return "参数错误";
		}
	}

	@Override
	public String deletePebBanzuJiegou(Integer id, String pageStatus) {
		PebBanzuJiegou pebBanzuJiegou = (PebBanzuJiegou) totalDao.getObjectById(PebBanzuJiegou.class, id);
		if(pebBanzuJiegou!=null) {
			List list = totalDao.query("from PebBanzuJiegou where fatherId =?", pebBanzuJiegou.getId());
			if(list!=null && list.size()>0) {
				return "请先删除子对象";
			}
			return totalDao.delete(pebBanzuJiegou)+"";
		}else {
			return "未找到删除对象";
		}
	}

	@Override
	public PebBanzuJiegou getBanzuJiegouById(Integer id) {
		PebBanzuJiegou pebBanzuJiegou =(PebBanzuJiegou) totalDao.getObjectById(PebBanzuJiegou.class, id);
		
		//获取负责人列表
		if(pebBanzuJiegou!=null && pebBanzuJiegou.getPrincipals()!=null && !pebBanzuJiegou.getPrincipals().equals("")) {
			List<Users> usersList = new ArrayList<Users>();
			String principals = pebBanzuJiegou.getPrincipals();
			String[] split = principals.split(";");
			for (String string : split) {
				if(string!=null && !string.equals("")) {
					Users users = (Users) totalDao.getObjectById(Users.class, Integer.parseInt(string));
					if(users!=null) {
						usersList.add(users);
					}
				}
			}
			pebBanzuJiegou.setUserList(usersList);
		}
		return pebBanzuJiegou;
	}

	@Override
	public String deletePrincipals(PebBanzuJiegou pebBanzuJiegou,Integer id) {
		pebBanzuJiegou = (PebBanzuJiegou) totalDao.getObjectById(PebBanzuJiegou.class, pebBanzuJiegou.getId());
		String principals = pebBanzuJiegou.getPrincipals();
		if(principals==null || principals.equals("")) {
			return "false";
		}else {
			String[] split = principals.split(";");
			StringBuffer newPrincipals = new StringBuffer();
			for (String string : split) {
				if(!string.equals(id+"")) {
					if(newPrincipals.toString().equals("")) {
						newPrincipals.append(string);
					}else {
						newPrincipals.append(";"+string);
					}
				}
			}
			pebBanzuJiegou.setPrincipals(newPrincipals.toString());
			boolean update = totalDao.update(pebBanzuJiegou);
			return String.valueOf(update);
		}
	}


	@Override
	public String addPrincipals(PebBanzuJiegou pebBanzuJiegou, Users users) {
		pebBanzuJiegou = (PebBanzuJiegou) totalDao.getObjectById(PebBanzuJiegou.class, pebBanzuJiegou.getId());
		String principals = pebBanzuJiegou.getPrincipals();
		if(principals==null || principals.equals("")) {
			pebBanzuJiegou.setPrincipals(String.valueOf(users.getId()));
		}else {
			pebBanzuJiegou.setPrincipals(principals+";"+users.getId());
		}
		boolean update = totalDao.update(pebBanzuJiegou);
		return String.valueOf(update);
	}

	@Override
	public Users getUsersByCode(String code) {
		
		return (Users) totalDao.getObjectByCondition("from Users where code=?", code);
	}

	@Override
	public String deleteBorrowLog(Integer id) {
		PebBorrowAndLendLog log = (PebBorrowAndLendLog) totalDao.getObjectById(PebBorrowAndLendLog.class, id);
		String lendBanzu =log.getBorrowBanzu(); 
		boolean delete = totalDao.delete(log);
		if(delete) {
			if(log.getPebUsersId()==null) {
				return "删除成功";
			}
			//修改借入人数
			PebUsers pebUsers = (PebUsers) totalDao.getObjectById(PebUsers.class, log.getPebUsersId());
			String message = addOrUpdatePebUsers(pebUsers, null);
			
			if(message.equals("保存成功")) {
				
				if(log.getEpStatus()!=null && log.getEpStatus().equals("同意")) {
					//删除借出记录
					PebUsers lendPebUsers = (PebUsers) totalDao.getObjectByCondition("from PebUsers where banZu = ?"
							+ " and year=? and month=? and day=?", lendBanzu,pebUsers.getYear(),
							pebUsers.getMonth(),pebUsers.getDay());
					if(lendPebUsers!=null) {
						PebBorrowAndLendLog lendLog = (PebBorrowAndLendLog) totalDao.getObjectByCondition(
								"from PebBorrowAndLendLog where lendBanzu=? and pebUsersId=?",
								lendBanzu,lendPebUsers.getId());
						delete = totalDao.delete(lendLog);
						if(delete) {
							return addOrUpdatePebUsers(lendPebUsers, null);
						}else {
							throw new RuntimeException("删除失败");
						}
						
					}else {
						return "删除成功";
					}
				}else {
					return "删除成功";
				}
			}
			throw new RuntimeException("删除失败");
		}else {
			return "删除失败";
		}
	}

	@Override
	public String getScreenNameById(Integer id, String pageStatus) {
		Screen screen = (Screen) totalDao.getObjectById(Screen.class, id);
		String name = screen.getName();
		if(name.equals("焊接车间")) {
			name="焊接";
		}else if(name.equals("喷涂车间")) {
			name = "喷涂";
		}else if(name.equals("MiNi车间")) {
			name= "MINI车间";
		}else if(name.equals("逆变器车间")) {
			name="逆变器（总装）";
		}
		return name;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List showJianBao(String teamName) {
		List<Integer> yearList = totalDao.query("select distinct year from PebUsers"
				+ " where zsNumber is not null and actualNum is not null order by year");
		if(yearList.size()<=0) {
			return null;
		}
		int currentYear = Integer.parseInt(Util.getDateTime("yyyy"));
		int currentMonth =Integer.parseInt( Util.getDateTime("MM"));
		int currentDay = Integer.parseInt(Util.getDateTime("dd"));
		currentDay = getCurrentDay(currentYear, currentMonth, currentDay,null);
		if(!yearList.contains(currentYear)) {
			yearList.add(currentYear);
		}
		List topDetailList = getJianBaoTopList(yearList, currentYear, currentMonth, currentDay);
		List<List> showList = new ArrayList<List>();
		showList.add(topDetailList);
		
		SubTeam fatherTeam = (SubTeam) totalDao.getObjectByCondition("from SubTeam where subName = ?",teamName);
		List<PebJianBao> pebJianBaoList = iteratorSearchJianBao(fatherTeam, yearList, currentYear, currentMonth, currentDay,1);
		
		//下面是数据格式化
		showList.addAll(formatJianBao(pebJianBaoList));
		
		return showList;
	}

	@Override
	public String noChuQinApplyAudit(PebUsers pus) {
		if(pus!=null && pus.getBanZu()!=null) {
			String banzu = pus.getBanZu();
			SubTeam subTeam = (SubTeam) totalDao.getObjectByCondition("from SubTeam where subName=?",banzu);
			if(subTeam!=null) {
				PebUsers pebUsers = (PebUsers)totalDao.getObjectByCondition("from PebUsers where banZu=? and year=? and month=? and day=?",
						banzu,pus.getYear(),pus.getMonth(),pus.getDay());
				if(pebUsers!=null) {
					pebUsers.setApplyNum(pus.getApplyNum());
				}else {
					try {
						pebUsers = new PebUsers();
						BeanUtils.copyProperties(pebUsers,pus);
						pebUsers.setId(null);
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					} 
				}
				String addOrUpdatePebUsers = addOrUpdatePebUsers(pebUsers, null);
				if(addOrUpdatePebUsers.equals("保存成功")) {
					try {
						String processName = "未出勤人数填报";
						Integer epId = CircuitRunServerImpl.createProcess(processName,PebUsers.class,
								pebUsers.getId(),"epStatus", "epId",
								null, "生产效率简报 ，班组："+banzu+",姓名："+Util.getLoginUser().getName()+",提交了未出勤人数:"
										+pebUsers.getApplyNum()+"人，请您审批。" , true);
						if (epId != null && epId > 0) {
							pebUsers.setEpId(epId);
							CircuitRun circuitRun = (CircuitRun) totalDao.get(
									CircuitRun.class, epId);

							pebUsers.setEpStatus("未审批");
							if ("同意".equals(circuitRun.getAllStatus())
									&& "审批完成".equals(circuitRun
											.getAuditStatus())) {
								pebUsers.setEpStatus("同意");
								String message = addOrUpdatePebUsers(pebUsers, null);
								return message;
							}else {
								totalDao.update(pebUsers);
								return "提交成功";
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
					
				}else {
					return addOrUpdatePebUsers;
				}
			}else {
				return "班组名称不存在";
			}
			
			
		}else {
			return "班组名称不能为空";
		}
		
		return "申请失败";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllMonthList() {
		Integer currentYear = Integer.parseInt(Util.getDateTime("yyyy"));
		if(monthList==null) {
			monthList = new ArrayList<String>();
		}
		boolean contains = monthList.contains(currentYear+"年"+Util.getDateTime("MM")+"月");
		
		if((monthList!=null && monthList.size()>0) || contains) {
		}else {
			//今年的所有月份
			List<Integer> currentYearMonthList = totalDao.query("select distinct month from PebUsers where year=? and "
					+ " avgNumber is not null and avgNumber>0 order by month",currentYear);
			
			List<Integer> oldYearMonthList=null;
			if(currentYearMonthList!=null && currentYearMonthList.size()<12) {
				//要获取去年的月份
				oldYearMonthList = totalDao.query("select distinct month from PebUsers where year=? and "
						+ " avgNumber is not null and avgNumber>0 order by month", currentYear-1);
			}
			if(oldYearMonthList==null) {
				oldYearMonthList = new ArrayList<Integer>();
			}
			if(monthList.size()+oldYearMonthList.size()+currentYearMonthList.size()>12) {  ///说明有重复的，最多显示12个月的数据
				return monthList;
			}
			if(oldYearMonthList!=null) {
				for (Integer month : oldYearMonthList) {
					monthList.add(currentYear-1+"年"+month+"月");
				}
			}
			if(currentYearMonthList!=null) {
				for (Integer month : currentYearMonthList) {
					monthList.add(currentYear+"年"+month+"月");
				}
			}
		}
		return monthList;
	}

	@Override
	public List<String> getMonthZengZhang(String banzu, String pageStatus) {
		List<String> allMonthList = getAllMonthList();
		List<String> data  =  new ArrayList<String>();
		for (String month : allMonthList) {
			String[] yearAndMonth = month.split("年");
			Integer currentYear = Integer.parseInt(yearAndMonth[0]);
			Integer currentMonth =Integer.parseInt(yearAndMonth[1].split("月")[0]);
			BigDecimal monthTotalzsNumber = getzsNumber(banzu, currentYear, currentMonth, null);
			Float persionMonthCount = getPersionCountByBanzu(banzu, currentYear, currentMonth, null);
			if(monthTotalzsNumber !=null && persionMonthCount!=null && monthTotalzsNumber.floatValue()>0 && persionMonthCount>0) {
				//月平均
				BigDecimal zsAvgNumber = monthTotalzsNumber.divide(new BigDecimal(persionMonthCount),3,BigDecimal.ROUND_HALF_UP).setScale(3,BigDecimal.ROUND_HALF_UP);
				
				//计算年基数
				SubTeam subTeam = (SubTeam) totalDao.getObjectByCondition("from SubTeam where subName = ? ", banzu);
				if(subTeam!=null && subTeam.getAvg2016()!=null) {
					BigDecimal avg2016bigDecimal = new BigDecimal(subTeam.getAvg2016());
					BigDecimal yearJiShu = avg2016bigDecimal.multiply(new BigDecimal(1.3));
					//月效率增长
					 float monthZengZhang = zsAvgNumber.divide(yearJiShu,4,BigDecimal.ROUND_HALF_UP)
							.subtract(new BigDecimal(1)).multiply(new BigDecimal(100)).setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
					 data.add(monthZengZhang+"");
				}
			}
			
		}
		return data;
	}

	@Override
	public String exportPebProduction(List<PebProduction> list) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("产品下线列表");
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("班组");
		row.createCell(1).setCellValue("日（天）");
		row.createCell(2).setCellValue("产品编码");
		row.createCell(3).setCellValue("产出下线台数");
		row.createCell(4).setCellValue("异常");
		row.createCell(5).setCellValue("采取措施");
		row.createCell(6).setCellValue("责任单位");
		row.createCell(7).setCellValue("责任单位措施");
		row.createCell(8).setCellValue("系数");
		row.createCell(9).setCellValue("折算台数");
		row.createCell(10).setCellValue("备注");
		row.createCell(11).setCellValue("年份");
		row.createCell(12).setCellValue("月份");
		if(list!=null) {
			int index = 0;
			for(int i=0;i<list.size();i++) {
				PebProduction pebProduction = list.get(i);
				if(pebProduction.getCuNumber()!=null && pebProduction.getCuNumber()>0) {
					index++;
					HSSFRow createRow = sheet.createRow(index);
					createRow.createCell(0).setCellValue(pebProduction.getBanzu());
					createRow.createCell(1).setCellValue(pebProduction.getDay());
					createRow.createCell(2).setCellValue(pebProduction.getMarkId());
					createRow.createCell(3).setCellValue(pebProduction.getCuNumber());
					createRow.createCell(4).setCellValue(pebProduction.getMessage());
					createRow.createCell(5).setCellValue(pebProduction.getMeasure());
					createRow.createCell(6).setCellValue(pebProduction.getZrComp());
					createRow.createCell(7).setCellValue(pebProduction.getZrCompMeasure());
					createRow.createCell(8).setCellValue(pebProduction.getXiShu());
					createRow.createCell(9).setCellValue(pebProduction.getZsNumber());
					createRow.createCell(10).setCellValue(pebProduction.getRemarks());
					createRow.createCell(11).setCellValue(pebProduction.getYear());
					createRow.createCell(12).setCellValue(pebProduction.getMonth());
				}
			}
		}

	    HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(StrutsStatics.HTTP_RESPONSE);
		OutputStream os;
		try {
			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("产品下线信息".getBytes("GB2312"), "8859_1")
					+ ".xls");
			response.setContentType("application/msexcel");
			workbook.write(os);
			workbook.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
			return e.toString();
		}
		
		return null;
	}

	@Override
	public String exportPebProductionBanjin(List<PebProductionBanjin> list) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("工序下线列表");
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("计件工序");
		row.createCell(1).setCellValue("日（天）");
		row.createCell(2).setCellValue("加工总量");
		row.createCell(3).setCellValue("PEBS产出");
		row.createCell(4).setCellValue("K3产出");
		row.createCell(5).setCellValue("年份");
		row.createCell(6).setCellValue("月份");
		
		if(list!=null) {
			int index = 0;
			for(int i=0;i<list.size();i++) {
				PebProductionBanjin banjin = list.get(i);
				if(banjin.getCuNumber()!=null && banjin.getCuNumber()>0) {
					index++;
					HSSFRow createRow = sheet.createRow(index);
					createRow.createCell(0).setCellValue(banjin.getProcessName());
					createRow.createCell(1).setCellValue(banjin.getDay());
					createRow.createCell(2).setCellValue(banjin.getCuNumber());
					if(banjin.getPEBSNumber()==null) {
						banjin.setPEBSNumber(0f);
					}
					if(banjin.getK3Number()==null) {
						banjin.setK3Number(0f);
					}
					createRow.createCell(3).setCellValue(banjin.getPEBSNumber());
					createRow.createCell(4).setCellValue(banjin.getK3Number());
					createRow.createCell(5).setCellValue(banjin.getYear());
					createRow.createCell(6).setCellValue(banjin.getMonth());
				}
			}
		}

	    HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(StrutsStatics.HTTP_RESPONSE);
		OutputStream os;
		try {
			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("工序下线信息".getBytes("GB2312"), "8859_1")
					+ ".xls");
			response.setContentType("application/msexcel");
			workbook.write(os);
			workbook.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
			return e.toString();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubTeam> getSubTeamsByBanzu(String banzu) {
		
		List<SubTeam> list = totalDao.query("from SubTeam where fatherId in(select id from SubTeam where subName = ? )", banzu);
		List<SubTeam> subList = new ArrayList<SubTeam>();
		if(list!=null && list.size()>0) {
			for (SubTeam subTeam : list) {
				List<SubTeam> searchList = getSubTeamsByBanzu(subTeam.getSubName());
				subList.addAll(searchList);
			}
			if(subList.size()>0) {
				list.addAll(subList);
			}
		}
		return list;
	}

	@Override
	public String exportBorrowLog(List<PebBorrowAndLendLog> logList) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("人员借用申请记录");
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("申请时间");
		row.createCell(1).setCellValue("借入班组");
		row.createCell(2).setCellValue("借出班组");
		row.createCell(3).setCellValue("申请人数");
		row.createCell(4).setCellValue("借调工时");
		row.createCell(5).setCellValue("申请人");
		row.createCell(6).setCellValue("添加时间");
		row.createCell(7).setCellValue("备注");
		row.createCell(8).setCellValue("审批状态");
		if(logList!=null) {
			for(int i=0;i<logList.size();i++) {
				PebBorrowAndLendLog log = logList.get(i);
				HSSFRow createRow = sheet.createRow(i+1);
				createRow.createCell(0).setCellValue(log.getSqTime());
				createRow.createCell(1).setCellValue(log.getSqBanzu());
				createRow.createCell(2).setCellValue(log.getBorrowBanzu());
				if(log.getBorrowNum()!=null) 
					createRow.createCell(3).setCellValue(log.getBorrowNum());
				if(log.getGzHour()!=null) 
					createRow.createCell(4).setCellValue(log.getGzHour());
				createRow.createCell(5).setCellValue(log.getAddUserName());
				createRow.createCell(6).setCellValue(log.getAddTime());
				createRow.createCell(7).setCellValue(log.getEpStatus());
				createRow.createCell(8).setCellValue(log.getRemarks());
			}
		}

	    HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(StrutsStatics.HTTP_RESPONSE);
		OutputStream os;
		try {
			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("人员借用申请记录".getBytes("GB2312"), "8859_1")
					+ ".xls");
			response.setContentType("application/msexcel");
			workbook.write(os);
			workbook.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
			return e.toString();
		}
		return null;
	}

	@Override
	public String exportPebUsers(List<PebUsers> pebUsersList) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("人事档案信息");
		
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("班组");
		row.createCell(1).setCellValue("年份");
		row.createCell(2).setCellValue("月份");
		row.createCell(3).setCellValue("日（天）");
		row.createCell(4).setCellValue("人事档案数");
		row.createCell(5).setCellValue("未出勤人数");
		row.createCell(6).setCellValue("上班小时数");
		if(pebUsersList!=null) {
			for(int i=0;i<pebUsersList.size();i++) {
				PebUsers pebUsers = pebUsersList.get(i);
				HSSFRow createRow = sheet.createRow(i+1);
				createRow.createCell(0).setCellValue(pebUsers.getBanZu());
				createRow.createCell(1).setCellValue(pebUsers.getYear());
				createRow.createCell(2).setCellValue(pebUsers.getMonth());
				createRow.createCell(3).setCellValue(pebUsers.getDay());
				if(pebUsers.getDangAnNum()!=null)
					createRow.createCell(4).setCellValue(pebUsers.getDangAnNum());
				if(pebUsers.getNoChuQinNum()!=null) 
					createRow.createCell(5).setCellValue(pebUsers.getNoChuQinNum());
				if(pebUsers.getGzHour()!=null) 
					createRow.createCell(6).setCellValue(pebUsers.getGzHour());
			}
		}

	    HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(StrutsStatics.HTTP_RESPONSE);
		OutputStream os;
		try {
			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("人事档案信息".getBytes("GB2312"), "8859_1")
					+ ".xls");
			response.setContentType("application/msexcel");
			workbook.write(os);
			workbook.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
			return e.toString();
		}
		return null;
	}

	@Override
	public String auditMultLogList(Integer[] ids, String tag) {
		String mess = "审批成功";
		if (ids != null && ids.length > 0) {
			PebBorrowAndLendLog log = null;
			for (Integer id : ids) {
				log = (PebBorrowAndLendLog) totalDao.getObjectById(PebBorrowAndLendLog.class, id);
				if (log != null) {
					
					if ("ok".equals(tag)) {// 同意
						mess = circuitRunServer.updateExeNodeByCirId(log
								.getEpId(), true, "", true, null, true);
					} else if ("no".equals(tag)) {// 打回
						mess = circuitRunServer.updateExeNodeByCirId(log
								.getEpId(), false, "", true, null, true);
					} else {
						return "数据异常!";
					}
					CircuitRun circuitRun = (CircuitRun) totalDao
							.getObjectById(CircuitRun.class, log.getEpId());
					if ("同意".equals(circuitRun.getAllStatus())) {
						log.setEpStatus("同意");
					} else {
						log.setEpStatus("打回");
					}
					totalDao.update(log);
				}
			}
		} else {
			return "数据异常!";
		}
		return mess;
	}

	@Override
	public String unApplyBorrow(Integer id, String remark) {
		PebBorrowAndLendLog log = (PebBorrowAndLendLog) totalDao.getObjectById(PebBorrowAndLendLog.class, id);
		if(log!=null) {
			try {
				boolean update = false;
					
				//获得之前的审批人
				Integer epId = log.getEpId();
				
				Integer[] userIds = circuitRunServer.findAuditUserIds(epId, 1);
				StringBuffer buffer = new StringBuffer();
				for (Integer userId : userIds) {
					if(buffer.toString().equals("")) {
						buffer.append("1:"+userId);
					}else {
						buffer.append(",1:"+userId);
					}
				}
				
				
				String processName = "人员借入取消申请";
				Integer cancalEpId	 = CircuitRunServerImpl.createProcessbf(processName, PebBorrowAndLendLog.class,
						log.getId(),
						"cancalEpStatus", "id", null,"申请人:"+Util.getLoginUser().getName()+",申请取消人员借入，借入班组："+
						log.getSqBanzu()+",借出班组："+log.getLendBanzu()
						+"，备注："+remark+"。请您审批。", true,buffer.toString(),"3");
				if (cancalEpId != null && cancalEpId > 0) {
					log.setCancalEpId(cancalEpId);
//					log.setEpStatus("申请取消中");
					CircuitRun circuitRun = (CircuitRun) totalDao.get(CircuitRun.class, cancalEpId);
					if ("同意".equals(circuitRun.getAllStatus())
							&& "审批完成".equals(circuitRun
									.getAuditStatus())) {
						log.setCancalEpStatus("同意");
					} else {
						log.setCancalEpStatus("未审批");
					}
					
					update= totalDao.update(log);
					
					if(update) {
						return "申请成功";
					}else {
						return "申请失败";
					}
						
				}
				
				throw new RuntimeException("申请失败");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.toString());
			}
		}else {
			return "不存在的数据";
		}
	}
	
	//根据班组结构名称查找SubTeam
	public static SubTeam getSubTeamByBZJGname(String banzu,TotalDao totalDao) {
		Integer fatherId = null;
		for (int i = 0; i < 6; i++) {
			String hql = "from PebBanzuJiegou where (se is null or se<>'否' ) and name=? ";
			if(fatherId !=null) {
				hql +=" and id="+fatherId;
			}
			PebBanzuJiegou pebBanzuJiegou = (PebBanzuJiegou) 
					totalDao.getObjectByQuery(hql, banzu);
			if(pebBanzuJiegou==null) { //如果设置为不统计效率的直接返回
				return null;
			}else {
				SubTeam subTeam = (SubTeam) totalDao.getObjectByQuery("from SubTeam where subName =?", banzu);
				if(subTeam!=null) {
					return subTeam;
				}else {
					//查找父层的名称
					banzu = (String) totalDao.getObjectByQuery("select name from PebBanzuJiegou where id=?", pebBanzuJiegou.getFatherId());
					fatherId = pebBanzuJiegou.getFatherId();
				}
			}
			
		}
//		SubTeam subTeam = (SubTeam) totalDao.getObjectByCondition("from SubTeam where subName =?", banzu);
//		if(subTeam==null) {
//			//当前登录人在负责人列表中
//			banzu = (String) totalDao.getObjectByCondition("select name from PebBanzuJiegou where id in"
//					+ "(select fatherId from PebBanzuJiegou where name=?)", banzu);
//			if(banzu!=null) {
//				subTeam = (SubTeam) totalDao.getObjectByCondition("from SubTeam where subName =?", banzu);
//				if(subTeam==null) {
//					banzu = (String) totalDao.getObjectByCondition("select name from PebBanzuJiegou where id in"
//							+ "(select fatherId from PebBanzuJiegou where name=?)", banzu);
//					if(banzu!=null) {
//						subTeam = (SubTeam) totalDao.getObjectByCondition("from SubTeam where subName =?", banzu);
//					}
//				}
//				
//			}
//		}
		return null;
	}

	@Override
	public PebBorrowAndLendLog getLogByCriteria(PebBorrowAndLendLog log, String pageStatus) {
		if(log!=null) {
			String hql =  totalDao.criteriaQueries(log, null, null);
			return (PebBorrowAndLendLog) totalDao.getObjectByCondition(hql, null);
		}
		return null;
	}
}
