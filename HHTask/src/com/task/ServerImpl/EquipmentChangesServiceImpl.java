package com.task.ServerImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.EquipmentChangesService;
import com.task.entity.EquipmentChanges;
import com.task.entity.Machine;
import com.task.entity.Users;
import com.task.util.Util;

public class EquipmentChangesServiceImpl implements EquipmentChangesService {
	private TotalDao totalDao;

	@Override
	public boolean add(EquipmentChanges equipmentChanges) {
		if (equipmentChanges != null) {
			Machine machine = (Machine) totalDao.getObjectById(Machine.class,
					equipmentChanges.getMachine().getId());
			if (machine != null) {
				equipmentChanges.setStatus("审批");
				equipmentChanges.setChangesdate(Util
						.getDateTime("yyyy-MM-dd hh:mm:ss"));
				Users user = (Users) ActionContext.getContext().getSession()
						.get(TotalDao.users);
				equipmentChanges.setUserid(user.getId());
				equipmentChanges.setDapt(user.getDept());
				equipmentChanges.setName(user.getName());

				equipmentChanges.setMachine(machine);

				return totalDao.save(equipmentChanges);
			}
		}
		return false;
	}

	@Override
	public boolean delete(EquipmentChanges equipmentChanges) {
		if (equipmentChanges != null) {
			return totalDao.delete(equipmentChanges);
		}
		return false;
	}

	@Override
	public boolean update(EquipmentChanges equipmentChanges) {
		if (equipmentChanges != null) {
			if (equipmentChanges.getStatus().equals("完成")) {
				// 修改设备表的信息
				equipmentChanges.getMachine().setWorkArea(
						equipmentChanges.getNewstation());// 新工区
				equipmentChanges.getMachine().setWorkPosition(
						equipmentChanges.getNewworkarea());// 新工位
			}
			return totalDao.update(equipmentChanges);
		}
		return false;
	}

	public Machine findAssettById(int id) {

		return (Machine) totalDao.getObjectById(Machine.class, id);
	}

	@Override
	public EquipmentChanges findAssetById(int id) {
		return (EquipmentChanges) totalDao.getObjectById(
				EquipmentChanges.class, id);
	}

	// 查询所有审核/打回信息
	public List findAll(String status) {
		if (status != null && status.length() > 0) {
			Users user = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);
			String hql = "from EquipmentChanges where status like '%" + status
					+ "%' and userid=?";
			return totalDao.query(hql, user.getId());
		}
		return null;
	}

	@Override
	public Object[] findMachineByCondition(Machine machine, int pageNo,
			int pageSize) {
		if (machine == null) {
			machine = new Machine();
		}
		Users loginUser = Util.getLoginUser();
		String other = "classGroup='" + loginUser.getDept() + "'";
		String hql = totalDao.criteriaQueries(machine, other, "classGroup");
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	@Override
	public Object[] findMachineBCondition(EquipmentChanges equipmentChanges,
			int pageNo, int pageSize, Date date1, Date date2) {
		if (equipmentChanges == null) {
			equipmentChanges = new EquipmentChanges();
		}
		String hql = totalDao.criteriaQueries(equipmentChanges, "changesdate",
				new String[] { Util.DateToString(date1, "yyyy-MM-dd HH:mm:ss"),
						Util.DateToString(date2, "yyyy-MM-dd HH:mm:ss") }, "");
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	// 查询所有+条件查询(分页)+审批
	@SuppressWarnings("unchecked")
	public Object[] findAll(EquipmentChanges equipmentChanges, int pageNo,
			int pageSize, String status) {
		if (equipmentChanges == null) {
			equipmentChanges = new EquipmentChanges();
		}
		String message = "";
		if (status != null) {
			if ("fuzong".equals(status)) {
				equipmentChanges.setStatus("审批");

			} else if ("zongjingli".equals(status)) {
				equipmentChanges.setStatus("批准");

			} else if ("tongyi".equals(status)) {
				equipmentChanges.setStatus("同意");

			} else {
				message = "findById";
			}
		} else {
			message = "findById";
		}
		String hql = totalDao.criteriaQueries(equipmentChanges, null, null);
		if ("findById".equals(message)) {
			Users user = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);
			if (hql.indexOf(" where ") > 0) {
				hql += " and userid=" + user.getId();
			} else {
				hql += " where userid=" + user.getId();
			}
		}

		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public String exportExcel(Date date1, Date date2) {
		try {
			String fileName = "" + Util.getDateTime("yyyyMMddhhmmss") + ".xls";
			String excelRealPath = ServletActionContext.getServletContext()
					.getRealPath("/upload/sheet")
					+ "/equipment/" + fileName;
			File target2 = new File(excelRealPath);// 创建文件流获得EXCEL的路径
			// // 生成excel表
			WritableWorkbook book = Workbook.createWorkbook(target2);
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("设备变动明细汇总 ", 0);
			Label label = new Label(0, 0, "申请人");
			Label label1 = new Label(1, 0, "申请部门");
			Label label2 = new Label(2, 0, "申请时间");
			Label label3 = new Label(3, 0, "设备名称");
			Label label4 = new Label(4, 0, "设备编号");
			Label label5 = new Label(5, 0, "新工区");
			Label label6 = new Label(6, 0, "新工位");
			Label label7 = new Label(7, 0, "旧工区");
			Label label8 = new Label(8, 0, "旧工位");
			Label label9 = new Label(9, 0, "变动原因说明");
			Label label10 = new Label(10, 0, "设备移动人员");
			Label label11 = new Label(11, 0, "移动完成时间");
			Label label12 = new Label(12, 0, "状态");
			sheet.setColumnView(2, 20);// 设置第2行的宽度
			sheet.setColumnView(3, 15);// 设置第3行的宽度
			sheet.setColumnView(9, 30);// 设置第9行的宽度
			sheet.setColumnView(10, 15);// 设置第10行的宽度
			sheet.setColumnView(11, 18);// 设置第11行的宽度
			sheet.addCell(label);
			sheet.addCell(label1);
			sheet.addCell(label2);
			sheet.addCell(label3);
			sheet.addCell(label4);
			sheet.addCell(label5);
			sheet.addCell(label6);
			sheet.addCell(label7);
			sheet.addCell(label8);
			sheet.addCell(label9);
			sheet.addCell(label10);
			sheet.addCell(label11);
			sheet.addCell(label12);
			List excellist = new ArrayList();
			if (date1 == null && date1 == null) {
				String hql = "from EquipmentChanges ";
				excellist = totalDao.query(hql);
			} else {

				String hql = "from EquipmentChanges  where changesdate>=? and changesdate<=? order by changesdate";
				// String hql =
				// "from Borrow  where date between ? and ? and state = '未归还'  order by date desc ";
				excellist = totalDao.query(hql,Util.DateToString(date1,
						 "yyyy-MM-dd HH:mm:ss "), Util.DateToString(date2,
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (excellist != null && excellist.size() > 0) {
				for (int i = 0; i < excellist.size(); i++) {
					EquipmentChanges equipmentChanges = (EquipmentChanges) excellist
							.get(i);
					Label label13 = new Label(0, i + 1, equipmentChanges
							.getName());
					Label label14 = new Label(1, i + 1, equipmentChanges
							.getDapt());
					Label label15 = new Label(2, i + 1, equipmentChanges
							.getChangesdate());
					Label label16 = new Label(3, i + 1, equipmentChanges
							.getEquipmentName());
					Label label17 = new Label(4, i + 1, equipmentChanges
							.getDevicenumber());
					Label label18 = new Label(5, i + 1, equipmentChanges
							.getNewstation());
					Label label19 = new Label(6, i + 1, equipmentChanges
							.getNewworkarea());
					Label label20 = new Label(7, i + 1, equipmentChanges
							.getOldstation());
					Label label21 = new Label(8, i + 1, equipmentChanges
							.getOldworkarea());
					Label label22 = new Label(9, i + 1, equipmentChanges
							.getReason());
					Label label23 = new Label(10, i + 1, equipmentChanges
							.getChangesname());
					Label label24 = new Label(11, i + 1, equipmentChanges
							.getCompletiontime());
					Label label25 = new Label(12, i + 1, equipmentChanges
							.getStatus());

					sheet.addCell(label13);
					sheet.addCell(label14);
					sheet.addCell(label15);
					sheet.addCell(label16);
					sheet.addCell(label17);
					sheet.addCell(label18);
					sheet.addCell(label19);
					sheet.addCell(label20);
					sheet.addCell(label21);
					sheet.addCell(label22);
					sheet.addCell(label23);
					sheet.addCell(label24);
					sheet.addCell(label25);
				}
			}

			book.write();// 写入数据
			book.close();// 关闭文件
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "no";

	}
}