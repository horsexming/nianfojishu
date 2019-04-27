package com.task.ServerImpl;

import java.io.File;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import com.task.ServerImpl.sys.CircuitRunServerImpl;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.StrutsStatics;

import org.springframework.beans.BeanUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.TaskmanagerService;
import com.task.entity.Taskmanager;
import com.task.entity.Users;
import com.task.util.Util;
public class TaskmanagerServiceImpl implements TaskmanagerService {

	private TotalDao totalDao;

	@Override
	public String addTaskmanager(Taskmanager taskmanager) {
		Users users = Util.getLoginUser();
		if(users==null){
			return "请先登录!";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		taskmanager.setStartTime(sdf.format(new Date()));
		taskmanager.setApplyUsersId(users.getId());
		taskmanager.setApplyUsersName(users.getName());
		taskmanager.setApplyUsersDept(users.getDept());
		taskmanager.setApplyUsersTel(users.getPassword().getPhoneNumber());


		String messa=taskmanager.getApplyUsersDept()+ "部门 " +taskmanager.getApplyUsersName() +"提出问题点," +"请您审批！";

		String processName = "问题点审批流程";
		try {
			Integer epId;
			epId = CircuitRunServerImpl.createProcess(processName, Taskmanager.class, taskmanager.getId(), "ep_status", "id", messa, true);
			if (epId != null && epId > 0) {
				taskmanager.setEpId(epId);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		}

		if (totalDao.save(taskmanager)) {
			return "添加成功！";
		}
		return "对象为空，添加失败！";
	}


	@Override
	public void file(File add){
		int i = 0;
		jxl.Workbook wk = null;
		try {
			// 开始读取excle表格
			InputStream is = new FileInputStream(add);// 创建文件流;
			if (is != null) {
				wk = Workbook.getWorkbook(is);// 创建workbook
			}
			String sst = "";
			Sheet st = wk.getSheet(0);// 获得第一张sheet表;
			int exclecolums = st.getRows();// 获得excle总行数
			if (exclecolums > 2) {
				Users users = null;
				for (i = 2; i < exclecolums; i++) {
					Cell[] cells = st.getRow(i);// 获得每i行的所有单元格（返回的是数组）
					if (cells[0].getContents() != null
							&& cells[0].getContents().trim() != "") {
						String j = cells[4].getContents();//添加人
						if(j!=null){
							try {
								Taskmanager taskmanager = new Taskmanager();
								users = (Users) totalDao.getObjectByCondition("from Users where name = ?", j);
								if(users!=null){
									taskmanager.setStartTime(Util.getDateTime());
									taskmanager.setTaskType("问题点");
									taskmanager.setDescription(cells[1].getContents()+","+cells[2].getContents());
									taskmanager.setTaskCategory(users.getDept());
									taskmanager.setApplyUsersId(users.getId());
									taskmanager.setApplyUsersDept(users.getDept());
									taskmanager.setApplyUsersName(users.getName());
									taskmanager.setApplyUsersDept(users.getDept());
									taskmanager.setApplyUsersTel(users.getPassword().getPhoneNumber());
									String s = cells[6].getContents().trim();
									int ii = 2;
									if("重要".equals(s)){
										ii = 0;
									}else if("紧急".equals(s)){
										ii=1;
									}
									taskmanager.setUrgency(ii);
									taskmanager.setReceiver(cells[8].getContents().trim());
									String s9 = cells[9].getContents().trim();
									if("已解决".equals(s9)){
										taskmanager.setTaskState("待确认");
									}else {
										taskmanager.setTaskState("待处理");
									}
									totalDao.save2(taskmanager);
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								sst+= i+",";
							}
						}
					}
				}
			}
			System.out.println(sst);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(i+"sd");
		}
	}

	
	@Override
	public Taskmanager findTaskmanagerbyid(Integer id) {
		return (Taskmanager) totalDao.getObjectById(Taskmanager.class, id);
	}

	@Override
	public List getdept() {
		// SELECT applyUsersDept from ta_taskmanager GROUP BY applyUsersDept
		List list = totalDao
				.query(
						"select applyUsersDept from Taskmanager GROUP BY applyUsersDept",
						null);
		return list;
	}

	// 添加处理意见
	@Override
	public String addtasksuggestion(Taskmanager taskmanager) {
		Taskmanager task = findTaskmanagerbyid(taskmanager.getId());
		task.setSuggestion(taskmanager.getSuggestion());
		task.setTaskState("处理中");
		// TODO sendMessage
		if (taskmanager != null) {
			if (totalDao.update(task))
				return "修改成功！";
			else
				return "修改失败!";
		}
		totalDao.update(taskmanager);
		return "";

	}

	@Override
	public String deleteTaskmanager(Integer id) {
		Taskmanager taskmanager = findTaskmanagerbyid(id);
		if (taskmanager != null) {
			if (totalDao.delete(taskmanager))
				return "删除成功！";
			else
				return "删除失败！";
		}
		return "对象为空，删除失败！";
	}

	@Override
	public String updateTaskmanager(Taskmanager taskmanager) {

		Taskmanager task = findTaskmanagerbyid(taskmanager.getId());
		if ("完成".equals(taskmanager.getTaskState())) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			task.setFinishTime(sdf.format(new Date()));
			Users users = Util.getLoginUser();
			task.setVerifier(users.getName());
		}
		BeanUtils.copyProperties(taskmanager, task, new String[] { "id",
				"applyUsersId", "applyUsersName", "applyUsersDept",
				"taskCategory", "taskType", "urgency", "verifier",
				"attachmentPath", "note", "process", "exceptionType",
				"functionType", "repeatTime" ,"finishTime","epId","ep_status"});
		if (taskmanager != null) {
			if (totalDao.update(task)) {
				return "修改成功！";
			} else
				return "修改失败!";
		}
		return "不存在该条数据，修改失败!";
	}

	/***
	 * 查询各种状态的数据列表
	 * 
	 * @param taskmanager
	 * @param pagestatus
	 * @return
	 */
	@Override
	public List findTaskmanager(Taskmanager taskmanager, String pagestatus) {
		if (taskmanager == null) {
			taskmanager = new Taskmanager();
		}
		String hql = totalDao.criteriaQueries(taskmanager, null, "taskState");
		hql += " and taskState=?";
		return totalDao.query(hql, pagestatus);
	}

	@Override
	public Object[] findTaskmanager(Taskmanager taskmanager, int pageNo,
			int pageSize, String level, String status) {
		if (taskmanager == null) {
			taskmanager = new Taskmanager();
		}
		String hql = totalDao.criteriaQueries(taskmanager, null);
		Users users = Util.getLoginUser();
		if ("self".equals(level)) {
			hql += " and applyUsersId=" + users.getId();
		}
		if ("dept".equals(level)) {
			hql += " and applyUsersDept='" + users.getDept() + "'";
		}
		if ("dcl".equals(status)) {
			hql += " and taskState='待处理'";
		} else if ("clz".equals(status)) {
			hql += " and taskState='处理中'";
		} else if ("dqr".equals(status)) {
			hql += " and taskState='待确认'";
		} else if ("ecfk".equals(status)) {
			hql += " and taskState='反馈'";
		} else if ("wc".equals(status)) {
			hql += " and taskState='完成'";
		}
		hql += "  order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int dclcount = 0;
		int clzcount = 0;
		int dqrcount = 0;
		int fkcount = 0;
		int wccount = 0;
		int totalcount = 0;
		// SELECT taskState,count(*) as counts from ta_taskmanager GROUP BY
		// taskState;
		String counthql = "select taskState,count(*) as counts from Taskmanager where 1=1";
		if ("self".equals(level)) {
			counthql += " and applyUsersId=" + users.getId();
		}
		if ("dept".equals(level)) {
			counthql += "  and applyUsersDept='" + users.getDept() + "'";
		}
		counthql += " GROUP BY taskState";
		List<Object[]> list2 = totalDao.query(counthql, null);
		for (Object[] objects : list2) {
			if ("待处理".equals(objects[0])) {
				dclcount = Integer.valueOf(String.valueOf(objects[1]));
			}
			if ("处理中".equals(objects[0])) {
				clzcount = Integer.valueOf(String.valueOf(objects[1]));
			}
			if ("待确认".equals(objects[0])) {
				dqrcount = Integer.valueOf(String.valueOf(objects[1]));
			}
			if ("反馈".equals(objects[0])) {
				fkcount = Integer.valueOf(String.valueOf(objects[1]));
			}
			if ("完成".equals(objects[0])) {
				wccount = Integer.valueOf(String.valueOf(objects[1]));
			}
		}
		totalcount = dclcount + clzcount + dqrcount + fkcount + wccount;
		int count = totalDao.getCount(hql);// 总条数

		Object[] o = { list, count, dclcount, clzcount, dqrcount, fkcount,
				wccount, totalcount };
		return o;
	}

	@Override
	public void exportExcelTask(Taskmanager taskmanager, String status,
			String level) {
		String hql = "from Taskmanager where 1=1 ";
		if(!("all".equals(level))){
			return;
		}
		String xlsname="";
		if ("dcl".equals(status)) {
			hql += "and taskState='待处理'";
			xlsname="待处理";
		} else if ("clz".equals(status)) {
			hql += "and taskState='处理中'";
			xlsname="处理中";
		} else if ("dqr".equals(status)) {
			hql += "and taskState='待确认'";
			xlsname="待确认";
		} else if ("ecfk".equals(status)) {
			hql += "and taskState='反馈'";
			xlsname="二次反馈";
		} else if ("wc".equals(status)) {
			hql += "and taskState='完成'";
			xlsname="完成";
		} else if ("all".equals(status)) {
			xlsname="所有";
		} else {
			return;
		}
		List<Taskmanager> list = totalDao.query(hql);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(StrutsStatics.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			
			response.setHeader("Content-disposition", "attachment; filename="
							+ new String((xlsname+"问题点明细").getBytes("GB2312"), "8859_1")
							+ ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet(xlsname+"问题点明细", 0);
			ws.setColumnView(0, 16);
			ws.setColumnView(1, 16);
			ws.setColumnView(2, 18);
			ws.setColumnView(4, 24);
			ws.setColumnView(5, 20);
			ws.setColumnView(6, 12);
			ws.setColumnView(13, 16);
			ws.setColumnView(14, 16);
			ws.setColumnView(21, 16);
			ws.setColumnView(22, 16);
			ws.setColumnView(23, 16);

			int ie = 0;
			ws.addCell(new Label(ie, 0, "序号"));
			ie++;
			ws.addCell(new Label(ie, 0, "问题类型"));
			ie++;
			ws.addCell(new Label(ie, 0, "问题分类"));
			ie++;
			ws.addCell(new Label(ie, 0, "问题描述"));
			ie++;
			ws.addCell(new Label(ie, 0, "关联订单号或零件号"));
			ie++;
			ws.addCell(new Label(ie, 0, "所属流程"));
			ie++;
			ws.addCell(new Label(ie, 0, "异常类别	"));
			ie++;
			ws.addCell(new Label(ie, 0, "功能所属类"));
			ie++;
			ws.addCell(new Label(ie, 0, "重复次数"));
			ie++;
			ws.addCell(new Label(ie, 0, "开始时间"));
			ie++;
			ws.addCell(new Label(ie, 0, "申请人"));
			ie++;
			ws.addCell(new Label(ie, 0, "部门"));
			ie++;
			ws.addCell(new Label(ie, 0, "紧急程度"));
			ie++;
			ws.addCell(new Label(ie, 0, "处理意见/分析"));
			ie++;
			ws.addCell(new Label(ie, 0, "反馈意见"));
			ie++;
			ws.addCell(new Label(ie, 0, "领取人"));
			ie++;
			ws.addCell(new Label(ie, 0, "状态"));
			ie++; // 17

			for (int i = 0; i < list.size(); i++) {
				Taskmanager task = (Taskmanager) list.get(i);
				int ie1 = 0;
				ws.addCell(new Label(ie1, i + 1, i + 1 + ""));
				ie1++;
				ws.addCell(new Label(ie1, i + 1, task.getTaskType()));
				ie1++;
				ws.addCell(new Label(ie1, i + 1, task.getTaskCategory()));
				ie1++;
				ws.addCell(new Label(ie1, i + 1, task.getDescription()));
				ie1++;
				ws.addCell(new Label(ie1, i + 1, task.getNote()));
				ie1++;
				ws.addCell(new Label(ie1, i + 1, task.getProcess()));
				ie1++;
				ws.addCell(new Label(ie1, i + 1, task.getExceptionType()));
				ie1++;
				ws.addCell(new Label(ie1, i + 1, task.getFunctionType()));
				ie1++;
				ws.addCell(new Label(ie1, i + 1, String.valueOf(task
						.getRepeatTime())));
				ie1++;
				ws.addCell(new Label(ie1, i + 1, task.getStartTime()));
				ie1++;
				ws.addCell(new Label(ie1, i + 1, task.getApplyUsersName()));
				ie1++;
				ws.addCell(new Label(ie1, i + 1, task.getApplyUsersDept()));
				ie1++;
				ws.addCell(new Label(ie1, i + 1, String.valueOf(task
						.getUrgency())));
				ie1++;
				ws.addCell(new Label(ie1, i + 1, task.getSuggestion()));
				ie1++;
				ws.addCell(new Label(ie1, i + 1, task.getFeedback()));
				ie1++;
				ws.addCell(new Label(ie1, i + 1, task.getReceiver()));
				ie1++;
				ws.addCell(new Label(ie1, i + 1, task.getTaskState()));
				ie1++;
			}
			try {
				wwb.write();
				wwb.close();
			} catch (Exception e) {
				// TODO: handle exception
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
