package com.task.ServerImpl.kq;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.struts2.ServletActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.kq.ICardService;
import com.task.Server.kq.IConsumeService;
import com.task.Server.kq.IPersonService;
import com.task.Server.kq.IRegisterService;
import com.task.Server.kq.ISupplyFundService;
import com.task.entity.Card;
import com.task.entity.Collect;
import com.task.entity.Register;
import com.task.entity.SupplyFund;
import com.task.entity.SupplyResult;

/**
 * @author Administrator
 * @FileNam RegisterServiceImpl.java
 * @Date 2012-10-9
 */
public class RegisterServiceImpl implements IRegisterService {

	private TotalDao totalDao;

	private ISupplyFundService sfs;

	private ICardService cs;

	private IPersonService ps;

	private IConsumeService ics;

	public void add(Register rs) {
		boolean bol = totalDao.save(rs);
		System.out.println(bol);
	}

	public void delById(int id) {
		Register rs = (Register) totalDao.getObjectById(Register.class, id);
		totalDao.delete(rs);
	}

	public void del(Register rs) {
		totalDao.delete(rs);
	}

	public void update(Register rs) {
		totalDao.update(rs);
	}

	/***
	 * 查询所有登记信息(分页)
	 * 
	 * @param pageNo
	 *            第几页
	 * @param pageSize
	 *            每页数量
	 * @return Object[]
	 */
	public Object[] queryAllRegister(Map map,int pageNo, int pageSize) {
		String hql = "from Register r where 1 = 1";
		if(map != null){
			if(map.get("mark")!=null){
				hql+= " and r.cardNo = '" + map.get("mark") + "'";
			}
			if(map.get("people")!= null){
				hql+= " and r.personName like '%" + map.get("people") + "%'";
			}
		}
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public Register getRegisterById(int id) {
		return (Register) totalDao.query("from Register r where r.id = ?", id)
				.get(0);
	}

	@SuppressWarnings("unchecked")
	public String getCardNoByNum(String num) {
		List<String> list = totalDao.query(
				"select cardNo from Register r where r.badgenumber = ?", num);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings( { "unchecked", "rawtypes", "deprecation", "unused" })
	public Object[] readCardNoByExcel(String resouceFile, float copies,
			String upTime, String downTime) {
		StringBuffer message = new StringBuffer();
		List<SupplyResult> list = new ArrayList();
		int count = 0;
		jxl.Workbook rwb = null;
		try {
			InputStream is = new FileInputStream(resouceFile);
			rwb = Workbook.getWorkbook(is);
			jxl.Sheet rs = rwb.getSheet(0);
			Cell[] nameCell = rs.getColumn(1);
			Cell[] cardCell = rs.getColumn(2);
			Cell[] timeCell = rs.getColumn(3);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String[] upSet = upTime.split(":");
			String[] downSet = downTime.split(":");
			String upTimeStr = upSet[0] + upSet[1];
			String downTimeStr = downSet[0] + downSet[1];
			int startTime = Integer.parseInt(upTimeStr);
			int endTime = Integer.parseInt(downTimeStr);
			for (int i = 1; i < cardCell.length; i++) {
				String num = cardCell[i].getContents();
				String timeStr = timeCell[i].getContents();
				Date time = sdf.parse(timeStr);
				int hour = time.getHours();
				int minute = time.getMinutes();
				String minutes = "";
				if (minute < 10) {
					minutes = "0" + minute;
				} else {
					minutes = minute + "";
				}
				String excelTimeStr = time.getHours() + "" + minutes;
				int excelTime = Integer.parseInt(excelTimeStr);
				if (excelTime >= startTime && excelTime <= endTime) {
					String cardNo = getCardNoByNum(num);
					if (cardNo != null) {
						cardNo = Integer.parseInt(cardNo) + "";
						String perId = ps.getPersonIdBycardNo(cardNo);
						if (perId != null) {
							int personId = Integer.parseInt(perId);
							boolean ifSupply = sfs
									.getCardIfSupplyFundByPersonId(personId);
							if (!ifSupply) {
								Card card = cs.getCardById(personId);
								if (card != null) {
									float cardBlance = card.getBalance()
											+ copies;									
									Date date = new Date();
									SupplyFund sf = new SupplyFund(personId,
											date, copies, cardBlance, 0,
											"Admin");
									sfs.add(sf);
									
									Object[] obj = ps
											.getPersonNameAndDept(personId);
									SimpleDateFormat sd = new SimpleDateFormat(
											"yyyy-MM-dd HH:mm:ss");
									String supplyStr = sdf.format(date);
									SupplyResult s = new SupplyResult(obj[0]
											.toString(), obj[1].toString(),
											copies + "", supplyStr);
									list.add(s);
								} else {
									message.append("名字："
											+ nameCell[i].getContents()
											+ "    错误原因：   人员ID " + personId
											+ ",没有对应的卡;" + "<br/>");
								}
							} else {
								count++;
							}
						} else {
							message.append("名字：" + nameCell[i].getContents()
									+ "    错误原因：卡号  " + cardNo + ",没有对应的人员；"
									+ "<br/>");
						}
					} else {
						message.append("名字：" + nameCell[i].getContents()
								+ "    错误原因：登记号码  " + cardNo + ",没有对应的卡号;"
								+ "<br/>");
					}
				} else {
					message.append("名字：" + nameCell[i].getContents()
							+ "    错误原因：超过考勤时间人员登记号码" + num + "<br/>");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			rwb.close();
		}
		return new Object[] { list, message.toString(), count };
	}

	public String fileUpload(File file, String fileName) {
		String resorceFile = null;
		if (file.exists()) {
			InputStream is = null;
			OutputStream os = null;
			String path = ServletActionContext.getServletContext().getRealPath(
					"upload");
			String newFileName = System.currentTimeMillis()
					+ fileName.substring(fileName.indexOf("."));
			resorceFile = path + File.separator + newFileName;
			try {
				os = new FileOutputStream(resorceFile);
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
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (os != null) {
					try {
						os.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return resorceFile;
	}

	@SuppressWarnings( { "unchecked", "rawtypes" })
	public Object[] queryCollectByCondition(Map map, int pageNo, int pageSize) {
		List collectList = new ArrayList();
		if (map != null && map.size() > 0) {
			Date startTime = java.sql.Date.valueOf(map.get("startStr")
					.toString());
			Date endTime = java.sql.Date.valueOf(map.get("endStr").toString());
			SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月");
			String time = sf.format(startTime);
			String hql = "select p.id,p.name,d.name,p.cardNo from Person p,Department d where p.deptId = d.id";
			if (map.get("deptId") != null) {
				hql += " and d.id = '" + map.get("deptId") + "'";
			}
			if (map.get("personId") != null) {
				hql += " and p.id = '" + map.get("personId") + "'";
			} else {
				hql += " order by d.name";
			}
			List list = totalDao.findAllByPage(hql, pageNo, pageSize);
			int count = totalDao.getCount(hql);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Object[] obj = (Object[]) list.get(i);
					int id = (Integer) obj[0];
					String name = (String) obj[1];
					String deptName = (String) obj[2];
					String cardNo = (String) obj[3];
					int supplySum = sfs.getSupplyByDateAndId(id, startTime,
							endTime);
					int consumeSum = ics.getConsumebeByDateAndId(id, startTime,
							endTime);
					int balance = cs.getCardBalanceById(id);
					int refund = balance * 5;
					Collect cl = new Collect(id, name, deptName, cardNo,
							supplySum, consumeSum, balance, refund, time);
					collectList.add(cl);
				}
				Object[] o = { collectList, count };
				return o;
			}
		}
		return null;
	}

	public List queryCollectByCondition(Map map) {
		List collectList = new ArrayList();
		Date startTime = java.sql.Date.valueOf(map.get("startStr").toString());
		Date endTime = java.sql.Date.valueOf(map.get("endStr").toString());
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月");
		String time = sf.format(startTime);
		String hql = "select p.id,p.name,d.name,p.cardNo from Person p,Department d where p.deptId = d.id";
		if (map.get("deptId") != null) {
			hql += " and d.id = '" + map.get("deptId") + "'";
		}
		if (map.get("personId") != null) {
			hql += " and p.id = '" + map.get("personId") + "'";
		} else {
			hql += " order by d.name";
		}
		List list = totalDao.query(hql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				int id = (Integer) obj[0];
				String name = (String) obj[1];
				String deptName = (String) obj[2];
				String cardNo = (String) obj[3];
				int supplySum = sfs
						.getSupplyByDateAndId(id, startTime, endTime);
				int consumeSum = ics.getConsumebeByDateAndId(id, startTime,
						endTime);
				int balance = cs.getCardBalanceById(id);
				int refund = balance * 5;
				Collect cl = new Collect(id, name, deptName, cardNo, supplySum,
						consumeSum, balance, refund, time);
				collectList.add(cl);
			}
			return collectList;
		}
		return null;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public void setSfs(ISupplyFundService sfs) {
		this.sfs = sfs;
	}

	public void setCs(ICardService cs) {
		this.cs = cs;
	}

	public void setPs(IPersonService ps) {
		this.ps = ps;
	}

	public void setIcs(IConsumeService ics) {
		this.ics = ics;
	}
}
