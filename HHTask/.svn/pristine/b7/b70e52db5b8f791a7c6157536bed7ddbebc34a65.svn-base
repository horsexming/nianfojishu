package com.task.ServerImpl.fin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.fin.FileManagerServer;
import com.task.entity.Users;
import com.task.entity.dangan.ArchiveUnarchiverAplt;
import com.task.entity.fin.BaoxiaoDan;
import com.task.entity.fin.jmwj.FileLocation;
import com.task.entity.fin.jmwj.FileManager;
import com.task.entity.fin.jmwj.FileType;
import com.task.entity.menjin.Access;
import com.task.entity.menjin.AccessEquipment;
import com.task.entity.menjin.AccessWebcam;
import com.task.util.RtxUtil;
import com.task.util.Util;

public class FileManagerserverImpl implements FileManagerServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean deleteFileManager(Integer id) {
		// TODO Auto-generated method stub
		FileManager fileManager = (FileManager) totalDao.getObjectById(
				FileManager.class, id);
		return totalDao.delete(fileManager);
	}

	@Override
	public Object[] findFile(FileManager fileManager, String startDate,
			String endDate, Integer cpage, Integer pageSize, String tag) {
		String hql = "from FileManager order by fileStoreTime desc";
		
		String[] between = new String[2];
		if (null != startDate && null != endDate && !"".equals(endDate)
				&& !"".equals(startDate)) {
			between[0] = startDate;
			between[1] = endDate;
		}
		if (null != fileManager) {
			hql = totalDao.criteriaQueries(fileManager, "fileStoreTime", between,
					"")
					+ "  order by fileStoreTime desc";
		}

		Object[] procardAarr = new Object[2];
		Integer count = totalDao.getCount(hql);
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		procardAarr[0] = count;
		procardAarr[1] = list;
		return procardAarr;
	}

	@Override
	/**
	 * 查找下拉类型文件
	 */
	public String findStyle(String tag){
		String message = "";
		if (null != tag && !"".equals(tag)) {
			String hql = "";
			if ("location".equals(tag)) {
				hql = "select distinct(fileLocation) from FileLocation";
			} else if("filetype".equals(tag)){
				hql = "select distinct(fileType) from FileType";
			} 
			List<String> list = totalDao.query(hql);
			for (String d : list) {
				message += d.toString() + "|";
			}
		}
		return message;
	}

	@Override
	public FileManager getFileManagerById(Integer id) {
		// TODO Auto-generated method stub
		return (FileManager)totalDao.getObjectById(FileManager.class, id);
	}

	@Override
	public boolean saveFileManager(FileManager fileManager) {
		boolean boo = false;
		if(null!=fileManager){
			Users user = (Users) Util.getLoginUser();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			fileManager.setFileStoreUser(user.getName());
			fileManager.setFileStoreTime(sdf.format(new Date()));
			boo=totalDao.save(fileManager);
		}
		return boo;
	}

	@Override
	public boolean updateFile(FileManager fileManager) {
		// TODO Auto-generated method stub
		boolean boo = false;
		if(null!=fileManager){
			Users user = (Users) Util.getLoginUser();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			fileManager.setFileStoreUser(user.getName());
			fileManager.setFileStoreTime(sdf.format(new Date()));
			boo=totalDao.update(fileManager);
		}
		return boo;
	}

	@Override
	public boolean addFileLocation(FileLocation fileLocation) {
		boolean boo = false;
		if(null!=fileLocation){
			boo=totalDao.save(fileLocation);
		}
		return boo;
	}

	@Override
	public boolean addFileType(FileType fileType) {
		boolean boo = false;
		if(null!=fileType){
			boo=totalDao.save(fileType);
		}
		return boo;
	}
	/**
	 * 查询所有文件类型
	 * @return
	 */
	public List findListType(){
		String hql="from FileType";
		List l=totalDao.find(hql);
		return l;
	}
	/**
	 * 查询文件位置
	 * @param cpage
	 * @param pageSize
	 * @return
	 */
	public Object[] findFileLocation(Integer cpage, Integer pageSize){
		String hql = "from FileLocation ";		
		Object[] procardAarr = new Object[2];
		Integer count = totalDao.getCount(hql);
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		procardAarr[0] = count;
		procardAarr[1] = list;
		return procardAarr;
	}
	/**
	 * 删除文件类型
	 * @param id
	 * @return
	 */
	public boolean deleteFileTypeByID(Integer id){
		FileType f=(FileType)totalDao.getObjectById(FileType.class, id);
		return totalDao.delete(f);
	}
	/**
	 * 删除文件存放位置
	 * @param id
	 * @return
	 */
	public boolean deleteFileLocationById(Integer id){
		FileLocation l=(FileLocation)totalDao.getObjectById(FileLocation.class, id);
		return totalDao.delete(l);
	}
	/**
	 * 根据id获得AccessEquipment对象
	 */
	public AccessWebcam getbyIdAccessWebcam(Integer integer) {
		// TODO Auto-generated method stub
		return (AccessWebcam) totalDao.getObjectById(AccessWebcam.class,
				integer);
	}
	@Override
	public String addYanZheng(FileManager fileManger) {
		if (fileManger != null && fileManger.getId() != null
				&& fileManger.getId() > 0) {
			FileManager fileManger1 = (FileManager) totalDao.getObjectById(FileManager.class, fileManger.getId());
			if(fileManger1!=null){
				AccessWebcam aw =getbyIdAccessWebcam(Integer.parseInt(fileManger1.getDanganId()));
				ArchiveUnarchiverAplt access = new ArchiveUnarchiverAplt();
				Users users = Util.getLoginUser();
				if (users != null) {
					String s = Util.yanNumber(6);
					access.setCardId(users.getCardId());
					access.setDaName(access.getDaName());
					access.setCqType("取档");
					access.setAce_Ip(aw.getAeqt_ip());
					access.setDaGuiposition(aw.getCabinetOpenInstruction());//开门指令
					access.setInCodes(s);
					access.setAddTime(Util.getDateTime());
					access.setUseType("未使用");
					access.setShixiaoTime(Util.getSpecifiedDayAfter(Util
							.getDateTime("yyyy-MM-dd"), 1));// 失效日期
					access.setAce_Ip(aw.getWebcamIP());
					access.setDaGuihao(aw.getCabinetNum());
					if (totalDao.save(access)) {
						// 给需要档案柜管理员发送RTX消息
						List<String> codes1 = new ArrayList<String>();
						codes1.add(users.getCode());
						// boolean b = false;
						if (codes1 != null && codes1.size() > 0) {
							RtxUtil.sendNoLoginNotify(codes1, " "
									+ aw.getCabinetNum()
									+ " 开柜验证码为 " + s + " !", "系统消息", "0", "0");
						}
						aw.setLinshi(s);
						totalDao.update(aw);
						return "验证码已生成";
					}
				}
			}
		}
		return "生成失败！";
	}
}
