package com.task.Server.led;

import java.util.Map;

import com.task.entity.led.LED;
import com.task.entity.led.LEDLog;

public interface LEDServer {
	/**
	 * 得到LED数量
	 * @return
	 */
	public Integer oneLEDCount();
	/**
	 * 分页显示LED
	 * 
	 * @param lED
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findLEDsByCondition(LED lED, int pageNo,
			int pageSize);

	/**
	 * 添加LED
	 * 
	 * @param lED
	 * @return
	 */
	public boolean add(LED lED);
	
	/**
	 * 复制LED
	 * 
	 * @param lED
	 * @return
	 */
	public void copyAdd(Integer i);

	/**
	 * 修改
	 * 
	 * @param lED
	 * @return
	 */
	public boolean update(LED lED, String pageStatus);

	/**
	 * 通过id获取LED
	 * 
	 * @param id
	 * @return
	 */
	public LED getLEDById(Integer id);

	/**
	 * 通过id删除LED
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteLEDById(Integer id);

	/**
	 * 通过LED id获取LEDLog
	 * 
	 * @param lED
	 * @param parseInt
	 * @param pageSize
	 * @param string
	 * @return
	 */
	public Map<Integer, Object> findLEDLogsByCondition(LEDLog lEDLog,
			int parseInt, int pageSize, String string);

	public String addLEDLog(Integer lEDId, LEDLog pageLog);

	boolean addLEDLog(Integer lEDId, String context);

	/****
	 * 推送led信息
	 * 
	 * @param led
	 * @return
	 */
	void sendGongWeiMs(Integer ledId);

	/****
	 * 门禁led推送服务
	 * 
	 * @param number
	 *            led编号
	 * @param context
	 *            发送内容
	 * @param color
	 *            颜色
	 * @return
	 */
	void sendOtherMs(Integer number, String context, Integer color);

}
