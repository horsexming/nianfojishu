package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.MealService;
import com.task.entity.Mealticket;
import com.task.entity.Users;
import com.task.util.Util;

public class MealServiceImpl implements MealService {
	private TotalDao totalDao;// Dao层

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	// 添加
	public void save(Mealticket mealticket) {
		mealticket.setAddDate(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
		mealticket.setBarcode(Util.getDateTime("yyyyMMddHHmmss"));
		Users loginUser=Util.getLoginUser();
		String names = loginUser.getName();
		String code = loginUser.getCode();
		mealticket.setCode(code);
		mealticket.setManage(names);
		mealticket.setFuck("未通过");
		mealticket.setCheckname("----");
		mealticket.setCopy(0);
		totalDao.save(mealticket);
	}

	// 删除

	public void delete(Mealticket mealticket) {
		totalDao.delete(mealticket);
	}

	// 查询
	public List<Mealticket> getList(int fid) {
		String hql = "from Mealticket where id= ? ";
		List<Mealticket> list = totalDao.query(hql, fid);
		return list;
	}

	public List getAllList(int pageNo, int pageSize) {
		String hql = "from Mealticket order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		return list;
	}
	
	public List getPersonList(int pageNo, int pageSize){
		String hql = "from Mealticket where code = ? order by id desc";
		String code = Util.getLoginUser().getCode();
		List list = totalDao.findAllByPage(hql, pageNo, pageSize, code);
		return list;
	}
	//查询总数	
	public Integer getcount() {
		String hql="from Mealticket";
		return this.totalDao.getCount(hql);
	}
	public int getshu() {
		String hql="from Mealticket where fuck='通过'";
		return this.totalDao.getCount(hql);
	}
	public int getshu2(String atime,String btime){
		String hql="from Mealticket where addDate between ? and ? and fuck='通过'";
		return this.totalDao.getCount(hql,atime,btime);
	}
	//个人查询总数
	public Integer getcount1() {
		String code = Util.getLoginUser().getCode();
		String hql="from Mealticket where code = ?";
		return this.totalDao.getCount(hql,code);
	}
	public int getshu1() {
		String code = Util.getLoginUser().getCode();
		String hql="from Mealticket where fuck='通过' and code = ?";
		return this.totalDao.getCount(hql,code);
	}
	//审核总览
	public Integer getcount2() {
		String hql="from Mealticket where fuck = '未通过' ";
		return this.totalDao.getCount(hql);
	}
	public List getKanList(int pageNo, int pageSize) {
		String hql = "from Mealticket " +
			"where fuck = '未通过'  order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		return list;
	}
	//审核时间通过总览
	public Integer getcount4(String atime,String btime) {
		String hql="from Mealticket where addDate between ? and ? and fuck = '通过'";
		return this.totalDao.getCount(hql,atime,btime);
	}
	public List getKanList4(int pageNo, int pageSize,String atime,String btime) {
		String hql = "from Mealticket where " +
				"addDate between  ? and ? and fuck = '通过' order by id desc";
		List list = totalDao.findAllByPage(hql,pageNo,pageSize,atime,btime);
		return list;
	}
	
	//审核通过总览
	public List getList(String atime,String btime){
		String hql="from Mealticket where addDate between ? and ? " +
				"and fuck = '通过' order by id ";
		return this.totalDao.query(hql,atime,btime);
	}
	public List getList(){
		String hql="from Mealticket where fuck = '通过' order by id desc";
		return this.totalDao.query(hql);
	}
	public Integer getcount3() {
		String hql="from Mealticket where fuck = '通过'";
		return this.totalDao.getCount(hql);
	}
	public List getKanList3(int pageNo, int pageSize) {
		String hql = "from Mealticket " +
			"where fuck = '通过' order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		return list;
	}
	
	//修改
	public void update(Mealticket mealticket){
		mealticket.setAddDate(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
		mealticket.setBarcode(Util.getDateTime("yyyyMMddHHmmss"));
		mealticket.setFuck("未通过");
		mealticket.setCheckname("----");
		mealticket.setCopy(0);
		Users loginUser=Util.getLoginUser();
		String names = loginUser.getName();
		mealticket.setManage(names);
		String code = loginUser.getCode();
		mealticket.setCode(code);
		totalDao.update(mealticket);
	}
	//增加打印次数
	public void addCopy(Mealticket mealticket){
		mealticket.setCopy(1);
		totalDao.update(mealticket);
	}
	//打印
	public void copy(Mealticket mealticket){
		mealticket.setCopy(0);
		totalDao.update(mealticket);
	}
	//打回
	public void redown (Mealticket mealticket){
		mealticket.setFuck("拒绝");
		Users loginUser=Util.getLoginUser();
		String names = loginUser.getName();
		mealticket.setCheckname(names);
		totalDao.update(mealticket);	
	}
	//审核
	public void updatee(Mealticket mealticket){
		mealticket.setCopy(1);
		mealticket.setFuck("通过");
		Users loginUser=Util.getLoginUser();
		String names = loginUser.getName();
		mealticket.setCheckname(names);
		totalDao.update(mealticket);	
	}
	
	
	
}
