package com.task.ServerImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.struts2.ServletActionContext;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute.Space;
import org.springframework.util.FileCopyUtils;
import java.lang.reflect.Field;

import javax.crypto.Mac;

import com.alibaba.fastjson.JSON;
import com.mysql.fabric.xmlrpc.base.Array;
import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.ProdEquipmentService;
import com.task.entity.DJNR;
import com.task.entity.Machine;
import com.task.entity.Password;
import com.task.entity.TaSopGongwei;
import com.task.entity.Users;
import com.task.entity.bybz.BaoYangBiaoZhun;
import com.task.entity.bybz.BaoYangRecord;
import com.task.entity.sop.ProcessInfor;
import com.task.entity.sop.ProcessInforReceiveLog;
import com.task.entity.sop.ProcessSaveLog;
import com.task.entity.sop.ProcessWlqr;
import com.task.entity.sop.ProcessinforFuLiao;
import com.task.util.FieldMeta;
import com.task.util.Parent;
import com.task.util.SortableField;
import com.task.util.Upload;
import com.task.util.Util;
import com.tast.entity.zhaobiao.ZhUser;

@SuppressWarnings("unchecked")
public class ProdEquipmentServiceImpl implements ProdEquipmentService {
	private TotalDao totalDao;
	private ProdEquipmentService prodEquipmentService;
	private Machine machine;// 设备表

	public ProdEquipmentService getProdEquipmentService() {
		return prodEquipmentService;
	}

	public void setProdEquipmentService(
			ProdEquipmentService prodEquipmentService) {
		this.prodEquipmentService = prodEquipmentService;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	@Override
	public boolean add(Machine machine) {
		// List <Machine> all = totalDao.query("from Machine");
		// if(all.size()>0){
		// for(Machine m:all){
		// TaSopGongwei taSopGongwei = new TaSopGongwei();
		// taSopGongwei.setBanzu(m.getClassGroup());
		// taSopGongwei.setGongweihao(m.getWorkPosition());
		// taSopGongwei.setShebeiName(m.getName());
		// taSopGongwei.setShebeiCode(m.getNo());
		// totalDao.save(taSopGongwei);
		// }
		// }
		if (machine != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			machine.setStatus("正常");
			machine.setAddTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
					.format(new Date()));
			machine.setBarcode(machine.getWorkPosition() + machine.getNo());
			String buytime = machine.getBuytime();

			// String str1 = Timestamp.valueOf(df.format(new
			// java.util.Date())).toString();
			// String [] strdate = str1.split("-");
			// String a1 = strdate[0];
			// String a2 = strdate[1];
			// String a3 = strdate[2];
			// String [] strdate1 = a3.split(" ");
			// String b1 = strdate1[0];
			// String b2 = strdate1[1];
			Upload u = new Upload();
			String uploadPath = "/upload/file/machine";
			File file1 = new File(uploadPath);
			if (!file1.exists()) {
				file1.mkdirs();// 如果不存在文件夹就创建
			}
			if(machine.getPicF()!=null&&!"".equals(machine.getPicFFileName())){
				String end_str = u.UploadFile(machine.getPicF(), machine.getPicFFileName(),null,uploadPath,null);  
				if(!"上传文件失败!".equals(end_str)&&!"".equals(end_str)){
					machine.setPic(end_str);
				}else{
					return false;
				}
			}
			String[] str = buytime.split("-");
			String buy = str[0];
			Integer depreciationYear = machine.getDepreciationYear();// 折旧年限
			if (depreciationYear == null || depreciationYear == 0) {
				depreciationYear = 0;
			}
			Integer buy1 = Integer.parseInt(buy) + depreciationYear;
			String buy2 = buy1.toString();
			String buytime1 = buy2 + "-" + str[1] + "-" + str[2];
			machine.setEndtime(buytime1);
			Float Buyamount = (Float) machine.getBuyamount();// 购买金额
			Timestamp timestamp = Timestamp.valueOf(df
					.format(new java.util.Date()));// 取出系统当前时间
			// System.out.println(timestamp.toString());
			Long date = Util.StringToDate(timestamp.toString(), "yyyy-MM-dd")
					.getTime();// 当前日期
			Long buydate = Util.StringToDate(buytime, "yyyy-MM-dd").getTime();// 购买日期

			Long nYear = 1000 * 60 * 60 * 24 * 365L;// 转化为年
			Long newYear = date - buydate;// 已用折旧时间
			float year1 = newYear / nYear;// 转化为年
			float oldYear = depreciationYear - year1;// 剩余折旧时间(转化为年)
			float equipmentworth = 0f;
			if (depreciationYear == 0) {
				equipmentworth = 0f;
			} else {
				equipmentworth = (oldYear / depreciationYear) * Buyamount;
			}
			DecimalFormat myFormat = new DecimalFormat("0.00");// 设置float类型的小数只能为两位
			String strFloat = myFormat.format(equipmentworth);
			machine.setEquipmentworth(strFloat);
			List<BaoYangBiaoZhun> bybzList = machine.getBybzList();
			Set<BaoYangBiaoZhun> bybzSet = new HashSet<BaoYangBiaoZhun>();
			if (bybzList != null && bybzList.size() > 0) {
				for (BaoYangBiaoZhun baoYangBiaoZhun : bybzList) {
					baoYangBiaoZhun.setMachine(machine);
					bybzSet.add(baoYangBiaoZhun);
				}
				machine.setBybzSet(bybzSet);
			}
		}
		boolean bool = totalDao.save(machine);
		if (bool) {
			TaSopGongwei taSopGongwei = new TaSopGongwei();
			taSopGongwei.setBanzu(machine.getClassGroup());
			taSopGongwei.setGongweihao(machine.getWorkPosition());
			taSopGongwei.setShebeiName(machine.getName());
			taSopGongwei.setShebeiCode(machine.getNo());
			totalDao.save(taSopGongwei);
		}
		return bool;
	}

	@Override
	public boolean updateTaSopGongwei(Machine machine) {
		boolean bolean = false;
		String hql = "from TaSopGongwei where shebeiCode=?";
		TaSopGongwei taSopGongwei = (TaSopGongwei) totalDao
				.getObjectByCondition(hql, machine.getNo());
		if (taSopGongwei != null) {
			taSopGongwei.setBanzu(machine.getClassGroup());
			taSopGongwei.setGongweihao(machine.getWorkPosition());
			taSopGongwei.setShebeiName(machine.getName());
			bolean = totalDao.update(taSopGongwei);
		}
		return bolean;
	}

	@Override
	public boolean delete(Machine machine) {
		Machine oldMachine = (Machine) totalDao.getObjectById(Machine.class,
				machine.getId());
		oldMachine.setUserset(null);
		oldMachine.setPmiManagements(null);// 清除pmi的关系
		return totalDao.delete(oldMachine);
	}

	@Override
	public Machine findAssetById(int id) {
		return (Machine) totalDao.getObjectById(Machine.class, id);
	}

	@Override
	public boolean update(Machine machine) {
		Machine oldMachine = (Machine) totalDao.getObjectById(Machine.class,
				machine.getId());
		if (oldMachine != null) {
			String oldNo = oldMachine.getNo();// 老设备编号
			oldMachine.setIsManual(machine.getIsManual());
			oldMachine.setWorkArea(machine.getWorkArea());
			oldMachine.setWorkPosition(machine.getWorkPosition());
			oldMachine.setNo(machine.getNo());
			oldMachine.setType(machine.getType());
			oldMachine.setName(machine.getName());
			oldMachine.setClassGroup(machine.getClassGroup());
			oldMachine.setDepreciationYear(machine.getDepreciationYear());
			oldMachine.setBuytime(machine.getBuytime());
			oldMachine.setBuyamount(machine.getBuyamount());
			oldMachine.setIsKey(machine.getIsKey());
			oldMachine.setIsdj(machine.getIsdj());
			oldMachine.setMore(machine.getMore());
			oldMachine.setJiadonglv(machine.getJiadonglv());
			oldMachine.setUseStatus(machine.getUseStatus());
			oldMachine.setIslsxgw(machine.getIslsxgw());
			oldMachine.setUnitType(machine.getUnitType());//设备型号
			oldMachine.setBrand(machine.getBrand());//设备品牌
			oldMachine.setBaoyangType(machine.getBaoyangType());
			if(machine.getPicF()!=null&&!"".equals(machine.getPicFFileName())){
				//上传文件
				Upload u = new Upload();
				String uploadPath = "/upload/file/machine";
				File file1 = new File(uploadPath);
				if (!file1.exists()) {
					file1.mkdirs();// 如果不存在文件夹就创建
				}
				String end_str = u.UploadFile(machine.getPicF(), machine.getPicFFileName(),null,uploadPath,null);  
				if(!"上传文件失败!".equals(end_str)&&!"".equals(end_str)){
					oldMachine.setPic(end_str);
				}else{
					return false;
				}
			}else{
				if(machine.getPic()!=null){
					oldMachine.setPic(machine.getPic());
				}
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			// machine.setAddTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
			// .format(new Date()));
			if ((oldMachine.getBuytime() != null && !oldMachine.getBuytime()
					.equals(""))
					|| (machine.getBuytime() != null && !machine.getBuytime()
							.equals(""))) {
				String buytime = oldMachine.getBuytime();
				String[] str = buytime.split("-");
				String buy = str[0];
				Integer buy1 =null;
				buy1 = Integer.parseInt(buy)
							+ oldMachine.getDepreciationYear();
				String buy2 = buy1.toString();
				String buytime1 = buy2 + "-" + str[1] + "-" + str[2];
				oldMachine.setEndtime(buytime1);

				Float Buyamount = (Float) oldMachine.getBuyamount();// 购买金额
				Timestamp timestamp = Timestamp.valueOf(df
						.format(new java.util.Date()));// 取出系统当前时间
				Long date = Util.StringToDate(timestamp.toString(),
						"yyyy-MM-dd").getTime();// 当前日期
				Long buydate = Util.StringToDate(buytime, "yyyy-MM-dd")
						.getTime();// 购买日期
				Integer depreciationYear = oldMachine.getDepreciationYear();// 折旧年限
				Long nYear = 1000 * 60 * 60 * 24 * 365L;// 转化为年
				Long newYear = date - buydate;// 已用折旧时间
				float year1 = newYear / nYear;// 转化为年
				float oldYear = depreciationYear - year1;// 剩余折旧时间(转化为年)
				float equipmentworth = (oldYear / depreciationYear) * Buyamount;
				DecimalFormat myFormat = new DecimalFormat("0.00");// 设置float类型的小数只能为两位
				String strFloat = myFormat.format(equipmentworth);
				oldMachine.setEquipmentworth(strFloat);
			}
			if (oldMachine.getIsKey() == null
					|| oldMachine.getIsKey().equals("no")) {
				oldMachine.setMachineSpareParts(null);
			} else {
			}
			List<BaoYangBiaoZhun> listbybz = machine.getBybzList();
			Set<BaoYangBiaoZhun> bybzSet = new HashSet<BaoYangBiaoZhun>();;
			if (listbybz != null && listbybz.size() > 0) {
				for (BaoYangBiaoZhun baoYangBiaoZhun : listbybz) {
					bybzSet.add(baoYangBiaoZhun);
				}
			}
			oldMachine.setBybzSet(bybzSet);
			oldMachine.setBarcode(oldMachine.getWorkPosition()
					+ oldMachine.getNo());
			boolean bool = totalDao.update(oldMachine);
			if (bool) {
				// 同步工序模版上面绑定的设备信息(根据设备id同步)
				String hql_updatePro = "update ProcessTemplate set gongwei=?,shebeiNo=?,shebeiName=? where shebeiNo=?";
				totalDao.createQueryUpdate(hql_updatePro, null, oldMachine
						.getWorkPosition(), oldMachine.getNo(), oldMachine
						.getName(), oldNo);

				// 同步对应工位信息
				String hql = "from TaSopGongwei where shebeiCode=?";
				TaSopGongwei taSopGongwei = (TaSopGongwei) totalDao
						.getObjectByCondition(hql, oldNo);
				if (taSopGongwei != null) {
					taSopGongwei.setShebeiCode(oldMachine.getNo());
					taSopGongwei.setBanzu(oldMachine.getClassGroup());
					taSopGongwei.setGongweihao(oldMachine.getWorkPosition());
					taSopGongwei.setShebeiName(oldMachine.getName());
					bool = totalDao.update(taSopGongwei);
				}
			}
		}
		return false;
	} 
	/**
	 * 删除保养标准
	 * @param id
	 * @return
	 */
	public String deletebybz(Integer id){
		if(id!=null){
			BaoYangBiaoZhun bybz = (BaoYangBiaoZhun)totalDao.get(BaoYangBiaoZhun.class, id);
			if(bybz!=null){
				if(totalDao.delete(bybz)){
					return "删除成功";
				}
			}else{
				return "数据有误";
			}
		}
		return "删除失败"; 
		}
	/*
	 * 添加保养记录
	 */
	public String addBaoYangRecord(List<BaoYangRecord> byRecordList,Machine machine){
		if(machine.getId()!=null){
			machine = (Machine)totalDao.get(Machine.class, machine.getId());
			if(byRecordList!=null&&byRecordList.size()>0){
				String title ="该设备:";//提示
				for(BaoYangRecord byr : byRecordList ){
					if("Yes".equals(byr.getResult())){
						String hql_check = "select nextTime from BaoYangRecord where BaoYangBiaoZhunId =? and machineId =? order by id DESC ";
						String nextBytime = (String)totalDao.getObjectByCondition(hql_check, byr.getBaoYangBiaoZhunId(),machine.getId());
						String nowdate = Util.getDateTime("yyyy-MM-dd");
						if(nextBytime==null||(nowdate.compareTo(nextBytime)>0||nowdate.compareTo(nextBytime)==0)){
							byr.setMachineId(machine.getId());
							byr.setAddTime(nowdate);
							Users user = Util.getLoginUser();
							byr.setPerson(user.getName());
							byr.setNextTime(Util.getSpecifiedDayAfter(nowdate,
									byr.getBaoyangCycle()));//根据保养周期推算下次保养时间
							totalDao.save(byr);
						}else{
							title =title+byr.getBaoyangCondition()+",";
						}
					}
				}
				if(!"该设备:".equals(title)){
					return title+"未过保养周期";
				}else{
					return "成功添加保养记录"; 
				}
			}else{
				return "未填写保养记录";
			}
		}else{
			return "查找不到该设备";
		}
	}
	@Override
	public Object[] findMachineByCondition(Machine machine, int pageNo,
			int pageSize, String pageStatus, Integer userId) {
		if (machine == null) {
			machine = new Machine();
		}
		ZhUser zhUser = Util.getCurrzhUser();
		String hql1 = null;
		if (zhUser != null) {
			hql1 = " isGys='" + zhUser.getId() + "'";
		} else {
			hql1 = " (isGys is null or isGys='no')";
		}
		String hql = "  from Machine where 1=1   ";
		if (("bdgw".equals(pageStatus) || "ybdgw".equals(pageStatus) || "wbdgw"
				.equals(pageStatus))
				&& (machine.getWorkPosition() != null && !"".equals(machine
						.getWorkPosition()))) {
			hql += " and workPosition like '%" + machine.getWorkPosition()
					+ "%'";
		}
		if ("bdgw".equals(pageStatus)) {
			hql += "  and workPosition is not null and workPosition <> ''";
		} else if ("ybdgw".equals(pageStatus) && userId > 0) {
			hql += " and id in( select m.id from Machine m  join m.userset u where u.id="
					+ userId + ")";
		} else if ("wbdgw".equals(pageStatus)) {
			hql += " and workPosition is not null and workPosition <> ''"
					+ " and workPosition not in( select m.workPosition from Machine m join m.userset u where u.id="
					+ userId + ") ";
		} else {
			hql = totalDao.criteriaQueries(machine, hql1);
		}

		hql += "  order by workPosition";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	
	@Override
	public Object[] findMachineByCondition1(Machine machine, int pageNo,
			int pageSize, String pageStatus, Integer userId,String showCloumn) {
		if (machine == null) {
			machine = new Machine();
		}
		ZhUser zhUser = Util.getCurrzhUser();
		String hql1 = null;
		if (zhUser != null) {
			hql1 = " isGys='" + zhUser.getId() + "'";
		} else {
			hql1 = " (isGys is null or isGys='no')";
		}
		String hql = "  from Machine where 1=1   ";
		if (("bdgw".equals(pageStatus) || "ybdgw".equals(pageStatus) || "wbdgw"
				.equals(pageStatus))
				&& (machine.getWorkPosition() != null && !"".equals(machine
						.getWorkPosition()))) {
			hql += " and workPosition like '%" + machine.getWorkPosition()
					+ "%'";
		}
		if ("bdgw".equals(pageStatus)) {
			hql += "  and workPosition is not null and workPosition <> ''";
		} else if ("ybdgw".equals(pageStatus) && userId > 0) {
			hql += " and id in( select m.id from Machine m  join m.userset u where u.id="
					+ userId + ")";
		} else if ("wbdgw".equals(pageStatus)) {
			hql += " and workPosition is not null and workPosition <> ''"
					+ " and workPosition not in( select m.workPosition from Machine m join m.userset u where u.id="
					+ userId + ") ";
		} else {
			hql = totalDao.criteriaQueries(machine, hql1);
		}

		hql += "  order by workPosition";
		//List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		List<Object[]> list = totalDao.findAllByPageShowCloumn(hql, pageNo, pageSize, showCloumn);
		int count = totalDao.getCount(hql);
		String jsonList = resultToJSON(list, showCloumn,Machine.class);
		Object[] o = { jsonList, count };
		return o;
	}
	
	
	private String  resultToJSON(List<Object[]> list,String showCloumn,Class c){
		List<SortableField[]> sfList = new ArrayList<SortableField[]>();
		Field[] fields =  c.getDeclaredFields();
			String[] cloumnArray = showCloumn.split(",");
			for (Object[] objs : list) {
				SortableField[] sfs = new SortableField[objs.length];
				for (int i = 0; i < objs.length; i++) {
					for (Field f : fields) {
						FieldMeta meta = f.getAnnotation(FieldMeta.class);
						if(meta!=null && cloumnArray[i].equals(f.getName())){
							if(objs[i]==null){
								SortableField 	sf = new SortableField(meta, f, "");
								sfs[i] =sf;
							}else{
								SortableField sf = new SortableField(meta, f, objs[i].toString());
								sfs[i] =sf;
							}
						}
					}
				}
				sfList .add(sfs);
			}
		return JSON.toJSONString(sfList);
	}
	
	@Override
	public int findAlly(Machine machine) {
		if (machine != null) {
			String hql = "from Machine where workArea=? and workPosition=? and no=?";
			return totalDao.getCount(hql, machine.getWorkArea(), machine
					.getWorkPosition(), machine.getNo());
		}
		return 0;
	}

	@Override
	public List findAll(String status) {
		if (status != null && status.length() > 0) {
			Users user = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);
			String hql = "from  Machine where status =? and userid=?";
			return totalDao.query(hql, status, user.getId());

		}
		return null;
	}

	@Override
	public List findAllByStatuss(String status) {
		if (status != null && status.length() > 0) {

			String hql = "from Machine where status =? ";
			return totalDao.query(hql, status);

		}
		return null;

	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public List printStorage(int[] selected) {
		List<Machine> l = new ArrayList<Machine>();
		for (int id : selected) {
			Machine sh = findAssetById(id);
			l.add(sh);
		}
		return l;
	}

	public static void main(String[] args) {
		List list = new ArrayList();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");

		for (int i = 0; i < list.size(); i++) {
			String thisId = (String) list.get(i);
			System.out.println(thisId);
		}

	}

	/*
	 * 
	 * 查询设备表(non-Javadoc)
	 * 
	 * @see com.task.Server.ProdEquipmentService#findtoAll()
	 */
	@Override
	public List<Machine> updatedomachine() throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String hql = "from Machine where buytime is not null";
		List<Machine> list = this.totalDao.find(hql);
		for (int i = 0; i < list.size(); i++) {
			Machine machine = list.get(i);
			String buytime = machine.getBuytime();// 购买时间
			Float Buyamount = machine.getBuyamount();// 购买金额
			Long buydate;
			Timestamp timestamp = Timestamp.valueOf(df
					.format(new java.util.Date()));// 取出系统当前时间
			Long date = Util.StringToDate(timestamp.toString(), "yyyy-MM-dd")
					.getTime();// 当前日期
			if (buytime == null) {
				continue;
			} else {
				buydate = Util.StringToDate(buytime, "yyyy-MM-dd").getTime();// 购买日期
				if (Buyamount != null && !"".equals(buytime)) {
					Integer depreciationYear = machine.getDepreciationYear();// 折旧年限
					Long nYear = 1000 * 60 * 60 * 24 * 365L;// 转化为年
					Long newYear = date - buydate;// 已用折旧时间
					float year1 = newYear / nYear;// 转化为年
					float oldYear = depreciationYear - year1;// 剩余折旧时间(转化为年)
					float equipmentworth = (oldYear / depreciationYear)
							* Buyamount;
					DecimalFormat myFormat = new DecimalFormat("0.00");// 设置float类型的小数只能为两位
					String strFloat = myFormat.format(equipmentworth);
					machine.setEquipmentworth(strFloat);
					this.totalDao.update(machine);
				}
			}

		}
		return list;
	}

	/****
	 * 根据编号、工位 查询设备
	 * 
	 * @param machine
	 * @return
	 */
	@Override
	public Machine findMachineByNum(Machine machine) {
		if (machine != null) {
			String hql = "from Machine where  no=?";
			machine = (Machine) totalDao.getObjectByCondition(hql, machine
					.getNo());
			// String hql = "from Machine where workPosition=? and no=?";
			// machine = (Machine) totalDao.getObjectByCondition(hql, machine
			// .getWorkPosition(), machine.getNo());
			return machine;
		}
		return null;
	}

	/****
	 * 根据编号、工区、工位 查询设备报修记录
	 * 
	 * @param machine
	 * @return
	 */
	@Override
	public List findMaintenanceByNum(Machine machine) {
		if (machine != null) {
			String hql = "from Maintenance where  no=? order by id desc";
			return totalDao.query(hql, machine.getNo());
		}
		return null;
	}

	/***
	 * 根据工序信息查询工序领取记录
	 * 
	 * @param processInforId
	 * @return
	 */
	@Override
	public List findPIRLogByProId(Integer processInforId) {
		if (processInforId != null) {
			String hql = "from ProcessInforReceiveLog where fk_processInforId=? and fk_pirLId is null";
			List<ProcessInforReceiveLog> proLogList = totalDao.query(hql,
					processInforId);
			if (proLogList != null && proLogList.size() > 0) {
				for (ProcessInforReceiveLog pro : proLogList) {
					String[] usercodes = pro.getUsercodes().split(",");
					if (usercodes != null && usercodes.length > 4) {
						String codes = "";
						for (int i = 0; i < usercodes.length; i++) {
							if (i != 0 && i % 4 == 0) {
								codes += "<br>" + usercodes[i];
							} else {
								codes += "," + usercodes[i];
							}
						}
						codes = codes.substring(1);
						pro.setUsercodes(codes);
					}

				}
			}
			return proLogList;
		}
		return null;
	}

	/***
	 * 根据工序信息查询本次设备能耗信息
	 * 
	 * @param processInforId
	 * @return
	 */
	@Override
	public Float findSbNhByProId(Integer processInforId) {
		if (processInforId != null) {
			String hql = "select sum(allNenghao) from ProcessInforReceiveLog where fk_processInforId=? and fk_pirLId is not null";
			return (Float) totalDao.getObjectByCondition(hql, processInforId);
		}
		return null;
	}

	/***
	 * 根据领取id查询工序领取记录明细
	 * 
	 * @param fk_pirLId
	 * @return
	 */
	@Override
	public List findPIRLogByFk_pirLId(Integer fk_pirLId) {
		if (fk_pirLId != null) {
			String hql = "from ProcessInforReceiveLog where fk_pirLId=?";
			return totalDao.query(hql, fk_pirLId);
		}
		return null;
	}

	/***
	 * 根据工序信息查询气密记录
	 * 
	 * @param processInforId
	 * @return
	 */
	@Override
	public List findAirByProId(Integer processInforId) {
		if (processInforId != null) {
			ProcessInfor processInfor = (ProcessInfor) totalDao.getObjectById(
					ProcessInfor.class, processInforId);
			if (processInfor != null
					&& "气密测试".equals(processInfor.getProcessName())) {
				String markid = processInfor.getProcard().getMarkId();
				String hql = "from AirtightLog where markId like '%" + markid
						+ "%' order by addtime desc ";
				return totalDao.findAllByPage(hql, 1, (int) processInfor
						.getSubmmitCount());
			}
		}
		return null;
	}

	// 批量导入
	public void addMachine(File chageWageFile) {
		String filName = Util.getDateTime("yyyyMMddhhmmss") + ".xls";
		// 上传到服务器
		String fileRealPath = ServletActionContext.getServletContext()
				.getRealPath("/upload/file")
				+ "/" + filName;
		File file = new File(fileRealPath);
		try {
			FileCopyUtils.copy(chageWageFile, file);
			// 开始读取excel表格
			InputStream is = new FileInputStream(fileRealPath);// 创建文件流
			jxl.Workbook rwb = Workbook.getWorkbook(is);// 创建workBook
			Sheet rs = rwb.getSheet(0);// 获得第一张Sheet表
			int excelcolumns = rs.getRows();// 获得总行数
			if (excelcolumns > 2) {
				for (int i = 2; i < excelcolumns; i++) {
					Machine ma = new Machine();
					Cell[] cells = rs.getRow(i);// 获得每i行的所有单元格（返回的是数组）
					String a = cells[1].getContents();// 获得工区
					String b = cells[2].getContents();// 获得工位
					String c = cells[3].getContents();// 获得设备编号
					String d = cells[4].getContents();// 获得设备类型
					String k = cells[5].getContents();// 设备名称
					// String e = cells[6].getContents();// 所在班组
					String f = cells[6].getContents();// 折旧年限
					String g = cells[7].getContents();// 购买设备时间
					try {
						g = g.replaceAll("/", "-");
						String[] array = g.split("-");
						if (array != null && array.length == 3) {
							for (int j = 0; j < array.length; j++) {
								Integer.parseInt(array[i]);
							}
						} else {
							new RuntimeException("第" + i
									+ "行购买时间日期格式错误!请保持年-月-日的格式");
							break;
						}
					} catch (Exception e) {
						new RuntimeException("第" + i
								+ "行购买时间日期格式错误!请保持年-月-日的格式");
						break;
					}
					String h = cells[8].getContents();// 购买金额
					String m = cells[9].getContents();// 制造厂家
					String j = cells[10].getContents();// 是否关键设备
					ma.setWorkArea(a);
					ma.setWorkPosition(b);
					ma.setNo(c);
					ma.setType(d);
					ma.setName(k);
					// ma.setClassGroup("admin");
					ma.setDepreciationYear(Integer.parseInt(f));
					ma.setStatus("正常");
					ma.setBarcode(ma.getWorkPosition() + ma.getNo());
					ma.setEquipmentworth("0.00");
					ma.setBuytime(g);
					ma.setBuyamount(1f);
					ma.setManufacturer(m);
					ma.setIsdj("否");
					if (totalDao.save(ma)) {
						TaSopGongwei taSopGongwei = new TaSopGongwei();
						taSopGongwei.setBanzu(ma.getClassGroup());
						taSopGongwei.setGongweihao(ma.getWorkPosition());
						taSopGongwei.setShebeiName(ma.getName());
						taSopGongwei.setShebeiCode(ma.getNo());
						totalDao.save(taSopGongwei);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean bddjnr(Machine machine, List<DJNR> djnrList) {
		if (machine != null && machine.getId() != null && machine.getId() > 0) {
			Machine oldmachine = (Machine) totalDao.get(Machine.class, machine
					.getId());
			Set<DJNR> djnr = oldmachine.getDjnr();
			if (djnrList != null && djnrList.size() > 0) {
				for (int i = 0; i < djnrList.size(); i++) {
					djnr.add(djnrList.get(i));
					totalDao.save(djnrList.get(i));
				}
				oldmachine.setDjnr(djnr);
				oldmachine.setIsdj("是");
			}
			return totalDao.update(oldmachine);
		}
		return false;
	}

	@Override
	public Users findUserById(Integer id) {
		if (id != null && id > 0) {
			return (Users) totalDao.get(Users.class, id);
		}
		return null;
	}

	@Override
	public boolean Userbdgw(Integer id, int[] arrayId) {
		if (id != null) {
			Users user = (Users) totalDao.get(Users.class, id);
			if (user != null && arrayId != null && arrayId.length > 0) {
				Set<Machine> machineSet = user.getMachine();
				for (int i = 0; i < arrayId.length; i++) {
					Machine machine = (Machine) totalDao.get(Machine.class,
							arrayId[i]);
					machineSet.add(machine);
				}
				user.setMachine(machineSet);
				return totalDao.update(user);
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean Userjcbdgw(int[] arrayId, Integer id) {
		if (arrayId != null && arrayId.length > 0) {
			Users user = (Users) totalDao.get(Users.class, id);
			Set<Machine> machineSet = user.getMachine();
			for (int i = 0; i < arrayId.length; i++) {
				Machine machine = (Machine) totalDao.get(Machine.class,
						arrayId[i]);
				machineSet.remove(machine);
			}
			user.setMachine(machineSet);
			return totalDao.update(user);
		}
		return false;
	}

	@Override
	public Integer getdeptId(String deptNumber) {
		String hql = "select id  from DeptNumber where deptNumber = ?";
		Integer deptId = (Integer) totalDao.getObjectByCondition(hql,
				deptNumber);
		return deptId;
	}

	@Override
	public List<ProcessinforFuLiao> findProcessFlByProId(int id) {
		// TODO Auto-generated method stub
		return totalDao.query(
				"from ProcessinforFuLiao where processInfor.id=?", id);
	}

	@Override
	public List<ProcessSaveLog> findProcessSaveLogByProId(int id) {
		// TODO Auto-generated method stub
		return totalDao.query("from ProcessSaveLog where processId=?", id);
	}

	@Override
	public List<TaSopGongwei> findTaSopGongwei(Machine machine) {
		// TODO Auto-generated method stub
		return totalDao.query("from TaSopGongwei where gongweihao = ?", machine
				.getWorkPosition());
	}
	
	@Override
	public List<TaSopGongwei> updateTaSopGongwei(List<TaSopGongwei> gongwei) {
		// TODO Auto-generated method stub
		if(gongwei!=null&&gongwei.size()>0){
			for (TaSopGongwei taSopGongwei : gongwei) {
				if(taSopGongwei!=null&&taSopGongwei.getShebeiId()==null){
					Machine machine = null;
					if(taSopGongwei.getShebeiCode()!=null){
						machine = (Machine) totalDao.getObjectByCondition("from Machine where workPosition = ? and no = ?", 
								taSopGongwei.getGongweihao(),taSopGongwei.getShebeiCode());
					}else if (taSopGongwei.getShebeiName()!=null) {
						machine = (Machine) totalDao.getObjectByCondition("from Machine where name = ? and no = ?", 
								taSopGongwei.getShebeiName(),taSopGongwei.getShebeiCode());
					}
					if(machine!=null){
						taSopGongwei.setShebeiId(machine.getId());
						totalDao.update(taSopGongwei);
					}
				}
			}
		}
		return gongwei;
	}

	@Override
	public List<BaoYangBiaoZhun> findListbybz(Integer id) {
		if (id != null) {
			return totalDao.query(" from BaoYangBiaoZhun where machine.id = ?",
					id);
		}
		return null;
	}
	/**
	 * 查询保养记录
	 * @param id
	 * @return
	 */
	public List<BaoYangRecord> findbyrList(Integer id){
		if (id != null) {
			return totalDao.query(" from BaoYangRecord where machineId = ?",
					id);
		}
		return null;
	}
	@Override
	public List<Machine> findjjdqby() {
		String hql = " from Machine  ";
		return null;
	}

	@Override
	public List<ProcessWlqr> findProcessWlqrById(Integer id) {
		if(id!=null){
			List<ProcessWlqr> listProcessWlqr =	totalDao.query(" from ProcessWlqr where processId = ? ", id);
			return listProcessWlqr;
		}
		return null;
	}
	public Machine getmachine(Integer id) {
		if(id!=null && id>0){
			return  (Machine) totalDao.get(Machine.class,id); 
		}
		return null;
	}
	/*
	 * 
	 */
	public List getdjnrbyId(Integer id) {
		if(id!=null&&id>0){
			Machine machine=(Machine) totalDao.get(Machine.class, id);
			Set<DJNR> djnrSet=machine.getDjnr();
			List djnrList= new ArrayList();
			for (DJNR djnr : djnrSet) {
				djnrList.add(djnr);
			}
			return djnrList;
		}
		return null;
	}

}
