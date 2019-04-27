package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.Screen;
import com.task.entity.ScreenContent;
import com.task.entity.TaSopGongwei;
import com.task.entity.printer.Printer;

public interface ScreenService {

	/**
	 * 添加
	 * 
	 * @param viewObj
	 */
	public void add(Screen viewObj);
	/**
	 * 得到当前看板数量
	 * @return
	 */
	public Integer oneScreenCount();
	/**
	 * 返回列表页
	 * 
	 * @return
	 */
	public List<Screen> list();

	public List<TaSopGongwei> getChild(Screen screen);

	/**
	 * 获取某个对象
	 * 
	 * @param id
	 * @return
	 */
	public Screen get(Integer id);

	/**
	 * 修改
	 * 
	 * @param screen
	 */
	public void update(Screen screen);

	/**
	 * 删除
	 * 
	 * @param screen
	 */
	public void delete(Screen screen);

	/**
	 * Android获取全部
	 * 
	 * @return
	 */
	public List<Screen> getAll();

	public List<Map> getUsers(Integer id);

	public List<Map> getProductionSchedule(Integer id);

	Object[] getUsers(Integer id, int page, int rows);

	/***
	 * 得到屏幕对应的生产和设备状态数据
	 * 
	 * @return
	 */
	List findSCAndSB(Integer screenId);
	
	
	
	public void addscreencontent(Screen screen);
	
	public String[] getScreenContentbyid(Integer id);
	
	public List<ScreenContent> getScreenContent(Screen screen);
	
	/**
	 *  为所有屏幕绑定屏幕内容
	 */
	public void addscreencontent2allscreen(Integer screencontentid);
	public Map<Integer, Object> findAll(Printer printer, int pageNo, int pageSize);
	public String updatePirnter(Printer printer);
	public String delPrinter(Printer printer);
	public String addPrinter(Printer printer);
	public Printer getPrinterbyId(Printer printer);
}
