package com.task.ServerImpl.sop.spc;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.FileCopyUtils;

import com.task.Dao.TotalDao;
import com.task.Server.sop.spc.SpcControlServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.entity.Machine;
import com.task.entity.ModuleFunction;
import com.task.entity.Screen;
import com.task.entity.TaSopGongwei;
import com.task.entity.Users;
import com.task.entity.sop.spc.SpcControl;
import com.task.entity.sop.spc.SpcControlClDetail;
import com.task.entity.sop.spc.SpcControlGroup;
import com.task.entity.sop.spc.SpcGroups;
import com.task.util.Util;

public class SpcControlServerImpl implements SpcControlServer {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public String addSpcControl(SpcControl spcControl) {
		if (spcControl != null) {
			int size = 0;
			if (spcControl.getId() == null) {
				if (spcControl.getShebeiNo() != null
						&& spcControl.getShebeiNo().length() > 0) {
					SpcControl old = (SpcControl) totalDao
							.getObjectByCondition(
									" from SpcControl where shebeiNo = ? order by id desc",
									spcControl.getShebeiNo());
					if (old != null) {
						List<SpcControlGroup> scgList = old.getSpcControlGroupList();
						if(scgList!=null && scgList.size()>0){
							size = scgList.size();
						} 
						old.setStatus("历史");
						totalDao.update(old);
					}
				}
				spcControl.setYears(Util.getDateTime("yyyy年"));
				totalDao.save(spcControl);
			}else{
				SpcControl old =  (SpcControl) totalDao.get(SpcControl.class, spcControl.getId());
				old.setClcontent(spcControl.getClcontent());
				old.setClinstrument(spcControl.getClinstrument());
				old.setClunit(spcControl.getClunit());
				totalDao.update(old);
			}
			List<SpcControlGroup> spcControlGroupList = spcControl
					.getSpcControlGroupList();
			for (SpcControlGroup group : spcControlGroupList) {
				if(size == 25){
					break;
				}
				Float[] clvalues = group.getClvalues();
				int count = 0;
				Float xBar = 0f;
				Float sum = 0f;
				try {
					boolean bool = true;
					Float[] oldArrays = new Float[clvalues.length];
					for (int i = 0; i < oldArrays.length; i++) {
						oldArrays[i] = clvalues[i];
						if(clvalues[i] == null){
							bool = false;
						}
					}
					if(bool){
						Arrays.sort(clvalues);
					}
					List<SpcControlClDetail> detailList = group.getDetailList();
					for (int i = 0; i < clvalues.length; i++) {
						if (clvalues[i] != null) {
							SpcControlClDetail detail = null;
							if (detailList != null && detailList.size() > 0) {
								detail = detailList.get(i);
							}
							if (detail != null && detail.getId() != null) {
								SpcControlClDetail old = (SpcControlClDetail) totalDao
										.get(SpcControlClDetail.class, detail
												.getId());
								old.setClValue(clvalues[i]);// 测量值
								totalDao.update(old);
							} else {
								int j = 0;
								for (j = 0; j < oldArrays.length; j++) {
									if (clvalues[i] == oldArrays[j]) {
										break;
									}
								}
								detail = new SpcControlClDetail();
								detail.setClValue(clvalues[i]);// 测量值
								detail.setClDate(group.getCldate());// 测量日期
								detail.setGroupsNO(group.getGroupsNO());// 组数
								detail.setSpcControlId(spcControl.getId());
								detail.setAddTime(Util.getDateTime());
								detail.setXuhao(j + 1);
								detail.setSpcControlGroup(group);
								totalDao.save(detail);
							}
							sum += clvalues[i];
						} else {
							count++;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					count++;
				}
				if (count == 0) {
					DecimalFormat df = new DecimalFormat("#.00");
					xBar = sum / clvalues.length;
					xBar = Float.parseFloat(df.format(xBar));
					Float range = clvalues[clvalues.length - 1] - clvalues[0];
					range = Float.parseFloat(df.format(range));
					group.setXbar(xBar);// 群组均值
					group.setRange(range);// 群组极差
					group.setSpcControl(spcControl);
					if (group.getId() == null) {
						totalDao.save(group);
					} else {
						SpcControlGroup old = (SpcControlGroup) totalDao.get(
								SpcControlGroup.class, group.getId());
						old.setXbar(xBar);// 群组均值
						old.setRange(range);// 群组极差
						totalDao.update(old);
					}
					size++;
				}
			}
			return "true";
		}
		return null;
	}

	@Override
	public String addspcGroups(List<SpcGroups> spcGroupsList) {
		if (spcGroupsList != null || spcGroupsList.size() > 0) {
			for (SpcGroups spcGroups : spcGroupsList) {
				totalDao.save(spcGroups);
			}
		}
		return null;
	}

	@Override
	public Object[] findAllSpcControlList(SpcControl spcControl,
			Integer pageNo, Integer pageSize, String status) {
		if (spcControl == null) {
			spcControl = new SpcControl();
		}
		String hql = totalDao.criteriaQueries(spcControl, null);
		List<SpcControl> List = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		return new Object[] { List, count };
	}

	@Override
	public List<SpcGroups> findAllspcGroups() {

		return totalDao.query(" from SpcGroups order by gropsSize ");
	}

	@Override
	public Object[] findSpcControlById(Integer id) {
		if (id != null) {
			SpcControl spcc = (SpcControl) totalDao.get(SpcControl.class, id);
			List<SpcControlGroup> groupsList = totalDao.query(
					" from SpcControlGroup where spcControl.id =? ", id);
			for (SpcControlGroup spcControlGroup : groupsList) {
				List<SpcControlClDetail> detailList = totalDao
						.query(
								" from SpcControlClDetail where spcControlGroup.id = ? order by xuhao ",
								spcControlGroup.getId());
				spcControlGroup.setDetailList(detailList);
			}
			Float max_Xbar = (Float) totalDao
					.getObjectByCondition(
							" select max(xbar) from SpcControlGroup where spcControl.id =? ",
							id);
			Float min_Xbar = (Float) totalDao
					.getObjectByCondition(
							" select min(xbar) from SpcControlGroup where spcControl.id =? ",
							id);
			Float max_Range = (Float) totalDao
					.getObjectByCondition(
							" select max(range) from SpcControlGroup where spcControl.id =? ",
							id);
			// Float min_Range = (Float)
			// totalDao.getObjectByCondition(" select min(range) from SpcControlGroup where spcControl.id =? ",
			// id);
			
			Float max_Xbar_ceil = (float) Math.ceil((max_Xbar==null)?0f:max_Xbar);
			Float min_Xbar_floor = (float) Math.floor((min_Xbar==null)?0f:min_Xbar);
			Float max_Range_ceil = 0f;
			if(max_Range!=null){
				 max_Range_ceil = (float) (Math.ceil(max_Range * 10) / 10);
			}
			return new Object[] { spcc, groupsList, max_Xbar, min_Xbar,
					max_Range, max_Xbar_ceil, min_Xbar_floor, max_Range_ceil };
		}
		return null;
	}

	@Override
	public String jisun(Integer id) {
		if (id != null) {
			List<SpcControlGroup> groupsList = totalDao.query(
					" from SpcControlGroup where spcControl.id =? ", id);
			if (groupsList != null && groupsList.size() >=2) {
				SpcControl spcc = (SpcControl) totalDao.get(SpcControl.class,
						id);
				SpcGroups g = (SpcGroups) totalDao.getObjectByCondition(
						" from SpcGroups where gropsSize = ? ", spcc
								.getGroupsize());
				Float A2 = g.getA2();
				Float D4 = g.getD4();
				Float sum = 0f;
				Float jcsum = 0f;
				DecimalFormat df = new DecimalFormat("#.00");
				float[] aa = new float[groupsList.size() * spcc.getGroupsize()];
				int count = 0;
				for (SpcControlGroup group : groupsList) {
					Set<SpcControlClDetail> setSpcControlClDetail = group
							.getSetSpcControlClDetail();
					for (SpcControlClDetail scd : setSpcControlClDetail) {
						aa[count] = scd.getClValue();
						count++;
					}
					sum += group.getXbar();
					jcsum += group.getRange();
				}
				Float xDBar = sum / groupsList.size();
				xDBar = Util.FomartFloat(xDBar, 2);
				Float rBar = jcsum / groupsList.size();
				rBar = Util.FomartFloat(rBar, 2);
				Float uCLxbar = xDBar + (rBar * A2);
				uCLxbar = Util.FomartFloat(uCLxbar, 2);
				Float lCLxbar = xDBar - (rBar * A2);
				lCLxbar = Util.FomartFloat(lCLxbar, 2);
				Float uCLr = rBar * D4;
				uCLr = Util.FomartFloat(uCLr, 2);
				Float lCLr = 0f;
				Float stdev = StDev(aa);// 标准偏差
				stdev = Util.FomartFloat(stdev, 3);
				Float cp = (spcc.getCeilValue() - spcc.getFloorValue())
						/ (6 * stdev);//
				cp = Util.FomartFloat(cp, 3);
				Float ca = Math.abs(xDBar
						- ((spcc.getCeilValue() + spcc.getFloorValue()) / 2))
						/ ((spcc.getCeilValue() - spcc.getFloorValue()) / 2);//
				ca = Util.FomartFloat(ca, 3);
				Float cpk = (1 - ca) * cp;// 
				cpk = Util.FomartFloat(cpk, 3);

				for (SpcControlGroup group0 : groupsList) {
					group0.setXdbar(xDBar);// 总均值
					group0.setRbar(rBar);// 极差均值
					group0.setUclr(uCLr);// 极差最大值
					group0.setLclr(lCLr);// 极差最小值
					group0.setUclxbar(uCLxbar);// 上限均值
					group0.setLclxbar(lCLxbar);// 下限均值
					group0.setStdev(stdev);// 标准偏差
					group0.setCp(cp);//
					group0.setCa(ca);//
					totalDao.update(group0);
				}
				spcc.setXdbar(xDBar);
				spcc.setRbar(rBar);
				spcc.setUclr(uCLr);
				spcc.setLclr(lCLr);
				spcc.setUclxbar(uCLxbar);
				spcc.setLclxbar(lCLxbar);
				spcc.setStdev(stdev);
				spcc.setCp(cp);
				spcc.setCa(ca);
				spcc.setCpk(cpk);
				totalDao.update(spcc);
				Machine machine = null;
				if (spcc.getShebeiNo() != null
						&& spcc.getShebeiNo().length() > 0) {
					machine = (Machine) totalDao.getObjectByCondition(
							" from Machine where no = ? ", spcc.getShebeiNo());
				}
				if (machine != null) {
					machine.setStdev(stdev);
					machine.setCp(cp);
					machine.setCa(ca);
					machine.setCpk(cpk);
					totalDao.update(machine);
					if(cpk<1){//如果cpk小于1推送消息给相关人员;
						String hql = "from ModuleFunction where functionName=?";
						ModuleFunction mf = (ModuleFunction) totalDao.getObjectByQuery(hql, "SPC控制查看");// 根据功能名称查询所属功能
						if(mf!=null){
							String hql2 = "from Users where id in (select u.id from Users u join u.moduleFunction f  where f.id =?)";
							List<Users> list = totalDao.query(hql2, mf.getId());
							if(list!=null && list.size()>0){
								Integer[] ids =	new Integer[list.size()];
								for (int i = 0; i < ids.length; i++) {
									ids[i]=list.get(i).getId();
								}
								AlertMessagesServerImpl.addAlertMessages("设备cpk提醒", "工位:"+machine.getWorkPosition()+"设备编号:"
										+machine.getNo()+"的设备的CPK为"+cpk+"，小于1.0。", ids, "SpcControlAction_findAllMachine.action?machine.id="+machine.getId(),true);
							}
						}
						
						
						
						
					}
				}

			}
		}
		return null;
	}

	public float StDev(float[] arrData) { // 计算标准偏差

		float xSum = 0F;
		float xAvg = 0F;
		float sSum = 0F;
		float tmpStDev = 0F;
		int arrNum = arrData.length;
		if (arrNum >= 2) {
			for (int i = 0; i < arrNum; i++) {
				xSum += arrData[i];
			}
			xAvg = xSum / arrNum;
			for (int j = 0; j < arrNum; j++) {
				sSum += ((arrData[j] - xAvg) * (arrData[j] - xAvg));
			}
			tmpStDev = (float) Math.sqrt(sSum / (arrNum - 1));
		}
		return tmpStDev;

	}

	@Override
	public Object[] findAllMachine(Machine machine, Integer pageNo,
			Integer pageSize, String status) {
		if (machine == null) {
			machine = new Machine();
		}
		String hql = totalDao.criteriaQueries(machine, null);
		List<Machine> List = totalDao.findAllByPage(hql
				+ " order by stdev desc ", pageNo, pageSize);
		int count = totalDao.getCount(hql);
		return new Object[] { List, count };
	}

	@Override
	public Machine findMachineByNo(String shebeiNo) {
		if (shebeiNo != null && shebeiNo.length() > 0) {
			return (Machine) totalDao.getObjectByCondition(
					" from Machine where no =? ", shebeiNo);
		}
		return null;
	}

	@Override
	public String updatespcGroups(List<SpcGroups> spcGroupsList) {
		if (spcGroupsList != null && spcGroupsList.size() > 0) {
			for (SpcGroups spcGroups : spcGroupsList) {
				if (spcGroups.getId() != null) {
					SpcGroups old = (SpcGroups) totalDao.get(SpcGroups.class,
							spcGroups.getId());
					old.setGropsSize(spcGroups.getGropsSize());
					old.setA2(spcGroups.getA2());
					old.setD3(spcGroups.getD3());
					old.setD4(spcGroups.getD4());
					if (!totalDao.update(old)) {
						return "error";
					}
				} else {
					if (!totalDao.save(spcGroups)) {
						return "error";
					}
				}
			}
			return "true";
		}
		return null;
	}

	@Override
	public List<SpcControl> findSpcControlByScreenId(Integer id) {
		List<SpcControl> spcControlList = new ArrayList<SpcControl>();
		if (id != null && id > 0) {
			Screen screen = (Screen) totalDao
					.get(Screen.class, id);
			Set<TaSopGongwei> gongweis = screen.getGongweis();
			for (TaSopGongwei taSopGongwei : gongweis) {
				SpcControl spcControl = (SpcControl) totalDao.getObjectByCondition(
						" from SpcControl where shebeiNo =? and (status='默认' or status is null or status='') ", taSopGongwei
								.getShebeiCode());
				if (spcControl != null) {
					spcControlList.add(spcControl);
				}
			}
			
			Collections.sort(spcControlList, new Comparator<SpcControl>(){
				@Override
				public int compare(SpcControl o1, SpcControl o2) {
					SpcControl s1 = (SpcControl) o1;  
					SpcControl s2 = (SpcControl) o2;  
		                if (s1.getId() > s2.getId()) {  
		                    return 1;  
		                }  
		                if (s1.getId() < s2.getId()) {  
		                    return -1;  
		                }  
					return 0;
				}
			});
			
		}
		return spcControlList;
	}

	@Override
	public String daoru(File spc_file, Integer id) {
		if (id != null && id > 0) {
			SpcControl spcControl = (SpcControl) totalDao.get(SpcControl.class,
					id);
			List<SpcControlGroup> scgList = new ArrayList<SpcControlGroup>();
			String msg = "true";
			String fileName = Util.getDateTime("yyyyMMddhhmmss") + ".xls";
			// 上传到服务器
			String fileRealPath = ServletActionContext.getServletContext()
					.getRealPath("/upload/file")
					+ "/" + fileName;
			File file = new File(fileRealPath);
			jxl.Workbook wk = null;
			int i = 0;
			int groupsize = spcControl.getGroupsize();
			try {
				FileCopyUtils.copy(spc_file, file);
				// 开始读取excle表格
				InputStream is = new FileInputStream(fileRealPath);// 创建文件流;
				if (is != null) {
					wk = Workbook.getWorkbook(is);// 创建workbook
				}
				Sheet st = wk.getSheet(0);// 获得第一张sheet表;
				int exclecolums = st.getRows();// 获得excle总行数
				if (exclecolums > 2) {
					StringBuffer strb = new StringBuffer();
					Integer errorCount = 0;// 错误数量
					Integer cfCount = 0;// 重复数量
					Integer successCount = 0;// 成功数量
					List<Integer> int_List = new ArrayList<Integer>();
					for (i = 2; i < exclecolums; i++) {
						Cell[] cells = st.getRow(i);// 获得每i行的所有单元格（返回的是数组）
						if(cells.length<2){
							continue;
						}
						String a = cells[1].getContents();// 日期
						String b = null;
						if(cells.length>=3){
							b =cells[2].getContents();// X1
						}
						String c = null;
						if(cells.length>=4){
							c =	cells[3].getContents();// X2
						}
						String d = null;
						if(cells.length>=5){
							d =cells[4].getContents();// X3
						}
						
						String e =null;
						if(cells.length>=6){
							e = cells[5].getContents();// X4
						}
						String f = null;
						if(cells.length>=7){
							 f = cells[6].getContents();// X5
						}
						Integer groupsNO = i-1;
						if (!int_List.contains(groupsNO)) {
							SpcControlGroup scg = new SpcControlGroup();
							scg.setGroupsNO(groupsNO);
							scg.setCldate(a);
							Float x1=null;
							Float x2=null;
							Float x3=null;
							Float x4=null;
							Float x5=null;
							try {
								x1 = (b != null && b.length() > 0) ? Float.parseFloat(b)
										: null;
								x2 = (c != null && c.length() > 0) ? Float.parseFloat(c)
										: null;
								x3 = (d != null && d.length() > 0) ? Float.parseFloat(d)
										: null;
								x4 = (e != null && b.length() > 0) ? Float.parseFloat(e)
										: null;
								x5 = (f != null && b.length() > 0) ? Float.parseFloat(e)
										:null;
							} catch (Exception e1) {
								e1.printStackTrace();
								strb.append("第"+(i+1)+"行，数据异常");
								errorCount++;
								continue;
								
							}
							Float[] float_arrays = { x1, x2, x3, x4, x5 };
							Float[] clvalues = new Float[groupsize];
							for (int j = 0; j < clvalues.length; j++) {
								clvalues[j] = float_arrays[j];
							}
							scg.setClvalues(clvalues);
							scgList.add(scg);
							if(scgList.size()==25){
								break;
							}
						}
					}
					is.close();// 关闭流
					wk.close();// 关闭工作薄
					if(scgList!=null && scgList.size()>0){
						spcControl.setSpcControlGroupList(scgList);
						msg =	addSpcControl(spcControl);
					}
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return msg;
		} else {
			return "数据异常，请刷新后重试!";
		}
	}

	@Override
	public String delSPC(Integer id) {
		if(id!=null && id>0){
			SpcControl spcControl = (SpcControl) totalDao.get(SpcControl.class,
					id);
			return	totalDao.delete(spcControl)+"";
		}
		return null;
	}
}
