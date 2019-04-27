package com.task.ServerImpl.menjin;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.menjin.InEmployeeCarInforServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Users;
import com.task.entity.menjin.InEmployeeCarInfor;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class InEmployeeCarInforServerImpl implements InEmployeeCarInforServer {
	private TotalDao totalDao;

	/**
	 * 添加车牌记录
	 * 
	 * @return 添加结果
	 */
	@Override
	public String addInEmployeeCarInfor(InEmployeeCarInfor inEmployeeCarInfor) {
		// TODO Auto-generated method stub
		if (("常访".equals(inEmployeeCarInfor.getCarInCangType()) && inEmployeeCarInfor
				.getOftenname() != null)
				|| "内部".equals(inEmployeeCarInfor.getCarInCangType())) {
			// 添加常访信息
			Users usersAdd = Util.getLoginUser();
			if (usersAdd != null) {
				Users users = (Users) totalDao.getObjectByCondition(
						"from Users where code=? and name=? and onWork <>'离职'",
						inEmployeeCarInfor.getNcode(), inEmployeeCarInfor
								.getName());
				if (users != null) {
					InEmployeeCarInfor carInfor = (InEmployeeCarInfor) totalDao
							.getObjectByCondition(
									"from InEmployeeCarInfor where nplates=?",
									inEmployeeCarInfor.getNplates());
					if (carInfor == null) {
						// 添加人信息
						inEmployeeCarInfor.setAddName(usersAdd.getName());
						inEmployeeCarInfor.setAddCode(usersAdd.getCode());
						// 内部员工信息
						inEmployeeCarInfor.setCar_User_Id(users.getId());
						inEmployeeCarInfor.setNcode(users.getCode());
						inEmployeeCarInfor.setNcardId(users.getCardId());
						inEmployeeCarInfor.setName(users.getName());
						inEmployeeCarInfor.setNdept(users.getDept());
						inEmployeeCarInfor.setAddTime(Util.getDateTime());
						boolean bo = false;
						if ("常访".equals(inEmployeeCarInfor.getCarInCangType())
								&& inEmployeeCarInfor.getOftenname() != null)
							bo = true;
						else
							inEmployeeCarInfor.setBorrowStatus("正常");

						// inEmployeeCarInfor.setEffectiveDate(Util
						// .getNextMonth3(Util.getDateTime("yyyy-MM")));
						if (totalDao.save(inEmployeeCarInfor)) {
							if (bo) {
								// 常访加上审批流程
								return shenpi(inEmployeeCarInfor, "");
							} else {
								return "true";
							}
						} else {
							return "申请失败！";
						}
					} else {
						return inEmployeeCarInfor.getNplates() + " 车牌号已存在，为"
								+ carInfor.getCarInCangType() + "车辆";
					}
				} else {
					return "不存在与此人信息对应的内部员工";
				}
			} else {
				return "添加人信息为空";
			}
		} else {
			return "添加类型错误";
		}
	}

	/**
	 * 删除车牌记录
	 * 
	 * @return
	 */
	@Override
	public boolean deleteInEmployeeCarInfor(
			InEmployeeCarInfor inEmployeeCarInfor1) {
		boolean b = false;
		if (inEmployeeCarInfor1 != null) {
			b = totalDao.delete(inEmployeeCarInfor1);
			if (b) {
				// 删消息
				if (inEmployeeCarInfor1.getEpId() != null
						&& inEmployeeCarInfor1.getEpId() > 0) {
					CircuitRunServerImpl.deleteCircuitRun(inEmployeeCarInfor1
							.getEpId());
				}
				// 删附件
				if (inEmployeeCarInfor1.getCarFiles() != null) {
					File oldFile = new File(ServletActionContext
							.getServletContext().getRealPath("")
							+ "/" + inEmployeeCarInfor1.getCarFiles());
					if (oldFile.exists()) {
						oldFile.delete();
					}
				}
			}
			return b;
		} else {
			return false;
		}
	}

	/**
	 * 分页查询车牌记录
	 * 
	 * @return
	 */
	@Override
	public Map<Integer, Object> findInEmployeeCarInforByCondition(
			InEmployeeCarInfor inEmployeeCarInfor, int pageNo, int pageSize,
			String tag, String all) {
		// TODO Auto-generated method stub
		if (inEmployeeCarInfor == null) {
			inEmployeeCarInfor = new InEmployeeCarInfor();
		}
		String hql = "";
		if ("cf".equals(tag)) {
			String sql = "";
			if ("dept".equals(all)) {
				Users users = Util.getLoginUser();
				sql = "a";
				sql = " carInCangType = '常访' and ndept = '" + users.getDept()
						+ "' ";
			} else if ("all".equals(all)) {
				sql = "a";
				sql = " carInCangType = '常访'";
			} else {
				sql = " carInCangType = 'a'";
			}
			hql = totalDao.criteriaQueries(inEmployeeCarInfor, sql);
		} else if ("nb".equals(tag)) {
			String sql = " carInCangType = '内部'";

			hql = totalDao.criteriaQueries(inEmployeeCarInfor, sql);
		} else {
			hql = totalDao.criteriaQueries(inEmployeeCarInfor, null);
		}
		hql += " order by id desc";
		List listi = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listi);
		map.put(2, count);
		return map;
	}

	/**
	 * 修改车牌记录
	 * 
	 * @return
	 */
	@Override
	public String updateInEmployeeCarInfor(InEmployeeCarInfor inEmployeeCarInfor) {
		InEmployeeCarInfor inEmployeeCarInfor2 = getByIdInEmployeeCarInfor(inEmployeeCarInfor
				.getId());
		if (inEmployeeCarInfor2 != null) {
			if (!"".equals(inEmployeeCarInfor.getNplates().trim())
					&& !inEmployeeCarInfor.getNplates().equals(
							inEmployeeCarInfor2.getNplates())) {
				InEmployeeCarInfor carInfor = (InEmployeeCarInfor) totalDao
						.getObjectByCondition(
								"from InEmployeeCarInfor where nplates=?",
								inEmployeeCarInfor.getNplates());
				if (carInfor != null) {
					return inEmployeeCarInfor.getNplates() + " 车牌号已存在，为"
							+ carInfor.getCarInCangType() + "车辆,无法重复。";
				}
			}

			BeanUtils.copyProperties(inEmployeeCarInfor, inEmployeeCarInfor2,
					new String[] { "id", "addTime", "addCode", "addName",
							"ncardId", "ndept", "car_User_Id", "carInCangType",
							"borrowStatus", "effectiveDate", "oftenStatus",
							"epId" });
			inEmployeeCarInfor2.setUpdateTime(Util.getDateTime());
			if (totalDao.update(inEmployeeCarInfor2)) {
				if ("打回".equals(inEmployeeCarInfor2.getOftenStatus())
						&& inEmployeeCarInfor2.getEpId() != null
						&& inEmployeeCarInfor2.getEpId() > 0) {
					if (CircuitRunServerImpl
							.updateCircuitRun(inEmployeeCarInfor2.getEpId())) {
						inEmployeeCarInfor2.setOftenStatus("未审批");
						totalDao.update(inEmployeeCarInfor2);
					}
				}
				return "true";
			} else {
				return "修改失败";
			}
		} else {  
			return "不存在该对象,修改失败";
		}
	} 

	/**
	 * 根据id获得InEmployeeCarInfor对象
	 */
	@Override
	public InEmployeeCarInfor getByIdInEmployeeCarInfor(Integer integer) {
		// TODO Auto-generated method stub
		InEmployeeCarInfor inEmployeeCarInfor = (InEmployeeCarInfor) totalDao
				.getObjectById(InEmployeeCarInfor.class, integer);
		return inEmployeeCarInfor;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/**
	 * 再次申请
	 */
	@Override
	public String agreen(InEmployeeCarInfor inEmployeeCarInfor) {
		// TODO Auto-generated method stub
		if (inEmployeeCarInfor != null) {
			if ("常访".equals(inEmployeeCarInfor.getCarInCangType())) {
				inEmployeeCarInfor.setEffectiveDate("");
				inEmployeeCarInfor.setUpdateTime(Util.getDateTime());
				Users users = Util.getLoginUser();
				inEmployeeCarInfor.setAddCode(users.getCode());
				inEmployeeCarInfor.setAddName(users.getName());
				if (totalDao.update(inEmployeeCarInfor)) {
					// 删消息
					if (inEmployeeCarInfor.getEpId() > 0
							&& inEmployeeCarInfor.getEpId() != null) {
						CircuitRunServerImpl
								.deleteCircuitRun(inEmployeeCarInfor.getEpId());
					}
					return shenpi(inEmployeeCarInfor, "(再次)");
				}
			}
		}
		return "重新申请失败！请重试";
	}

	private String shenpi(InEmployeeCarInfor inEmployeeCarInfor, String ageen) {
		// 调用常访申请审批流程
//		String processName = inEmployeeCarInfor.getNdept() + "部门常访申请";
		String processName = "常访申请";
		Integer epId = null;
		try {
			epId = CircuitRunServerImpl.createProcess(processName,
					InEmployeeCarInfor.class, inEmployeeCarInfor.getId(),
					"oftenStatus", "id", "", inEmployeeCarInfor.getNdept()
							+ "部门    " + inEmployeeCarInfor.getOftenname()
							+ " 常访 " + inEmployeeCarInfor.getName() + " "
							+ ageen + "申请，请您审批！", true,inEmployeeCarInfor.getNdept());
			if (epId != null && epId > 0) {
				inEmployeeCarInfor.setEpId(epId);
				inEmployeeCarInfor.setOftenStatus("未审批");
				if (totalDao.update(inEmployeeCarInfor)) {
					return "true";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "常访申请添加失败";
	}

	@Override
	public void exportExcel(InEmployeeCarInfor inEmployeeCarInfor) {
		

		if (inEmployeeCarInfor == null) {
			inEmployeeCarInfor = new InEmployeeCarInfor();
		}
		inEmployeeCarInfor.setCarInCangType("常访");
		String hql = totalDao.criteriaQueries(inEmployeeCarInfor, null);
		List<InEmployeeCarInfor> list = totalDao.query(hql);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(StrutsStatics.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("常访车辆信息".getBytes("GB2312"), "8859_1") + ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("常访车辆信息", 0);
			ws.setColumnView(0, 6);
			ws.setColumnView(1, 16);
			ws.setColumnView(2, 18);
			ws.setColumnView(4, 14);
			ws.setColumnView(5, 20);
			ws.setColumnView(6, 12);
			ws.setColumnView(7, 20);
			ws.addCell(new Label(0, 0, "序号"));
			ws.addCell(new Label(1, 0, "被访人姓名"));
			ws.addCell(new Label(2, 0, "常访人姓名"));
			ws.addCell(new Label(3, 0, "常访人性别"));
			ws.addCell(new Label(4, 0, "常访人车牌"));
			ws.addCell(new Label(5, 0, "车型"));
			ws.addCell(new Label(6, 0, "常访原因"));
			ws.addCell(new Label(7, 0, "常访失效日期"));
			ws.addCell(new Label(8, 0, "审批状态"));
			
			for (int i = 0; i < list.size(); i++) {
				InEmployeeCarInfor inEmployeeCarInfor1 = (InEmployeeCarInfor) list.get(i);
				ws.addCell(new Label(0, i + 1, i + 1 + ""));
				ws.addCell(new Label(1, i + 1,inEmployeeCarInfor1.getName()));
				ws.addCell(new Label(2, i + 1, inEmployeeCarInfor1.getOftenname()));
				ws.addCell(new Label(3, i + 1,inEmployeeCarInfor1.getOftenSex()));
				ws.addCell(new Label(4, i + 1, inEmployeeCarInfor1.getNplates()));
				ws.addCell(new Label(5, i + 1, inEmployeeCarInfor1.getCarModels()));
				ws.addCell(new Label(6, i + 1, inEmployeeCarInfor1.getOftenInfor()));
				ws.addCell(new Label(7, i + 1, inEmployeeCarInfor1.getEffectiveDate()));
				ws.addCell(new Label(8, i + 1, inEmployeeCarInfor1.getOftenStatus()));
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}

	
	}

	@Override
	public void exportExcel_1(InEmployeeCarInfor inEmployeeCarInfor) {
		// TODO Auto-generated method stub

		if (inEmployeeCarInfor == null) {
			inEmployeeCarInfor = new InEmployeeCarInfor();
		}
		inEmployeeCarInfor.setCarInCangType("内部");
		String hql = totalDao.criteriaQueries(inEmployeeCarInfor, null);
		List<InEmployeeCarInfor> list = totalDao.query(hql);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(StrutsStatics.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("内部员工车辆信息".getBytes("GB2312"), "8859_1") + ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("内部员工车辆信息", 0);
			ws.setColumnView(0, 6);
			ws.setColumnView(1, 16);
			ws.setColumnView(2, 18);
			ws.addCell(new Label(0, 0, "序号"));
			ws.addCell(new Label(1, 0, "员工姓名"));
			ws.addCell(new Label(2, 0, "员工车牌"));
			ws.addCell(new Label(3, 0, "车型"));
			
			for (int i = 0; i < list.size(); i++) {
				InEmployeeCarInfor inEmployeeCarInfor1 = (InEmployeeCarInfor) list.get(i);
				ws.addCell(new Label(0, i + 1, i + 1 + ""));
				ws.addCell(new Label(1, i + 1,inEmployeeCarInfor1.getName()));
				ws.addCell(new Label(2, i + 1, inEmployeeCarInfor1.getNplates()));
				ws.addCell(new Label(3, i + 1, inEmployeeCarInfor1.getCarModels()));
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}

	}
}
