package com.task.util;

/**
 * 车辆进出Led提示推送
 * 
 * @author Li_Cong
 * 
 */

public class LedCarPush {

	static final int COLOR_RED = 0x0000FF;// 红色
	static final int COLOR_GREEN = 0x00FF00;// 绿色
	static final int COLOR_YELLOW = 0x00FFFF;// 黄色
	static final int COLOR_dan = 65535;// 单色

	/**
	 * 
	 * @param car
	 *            车牌
	 * @param inOut
	 *            进出
	 * @param i
	 *            标记类型
	 * @param 0、已经(进门/出门)无法开门
	 * @param 1、开门
	 * @param 2、没有来访申请
	 * @param 3、工作时间，请假
	 * @param 4、非工作时间，加班
	 * @param 5、车牌识别为空
	 * @param 6、验证码 开门
	 * @param 7、车辆通过后（上海红湖 欢迎您）
	 * @param 8、确认出门（请联系被访人确认出门）
	 * @param 9、待检（请门卫检车刷卡确认后）
	 * @param 10、紧急开门
	 * @param 11、验证码 无效
	 * @param 12、验证码 发送为空
	 * @param 13、常访 已过期
	 * @param 14、常访 未审批
	 * @param 15、来访 非正常进入
	 */
	public static void ledShow(String car, String inOut, Integer i) {// 呢个地方有调用
		if (i == 1) {
			new LedSendServer(43, car + "    请" + inOut + "!", COLOR_GREEN)
					.sendLedMs();
			new LedSendServer(44, car + "    请" + inOut + "!", COLOR_GREEN)
					.sendLedMs();
			// LedSendUtil.OnAddtext(43, " " + car + "    请" + inOut + "!",
			// 25,42, 64, 32, COLOR_GREEN, 9);
			// LedSendUtil.OnAddtext(44, " " + car + "    请" + inOut + "!",
			// 25,42, 64, 32, COLOR_GREEN, 9);
		} else if (i == 0) {
			new LedSendServer(43, car + "您已" + inOut + "!无法开门", COLOR_RED)
					.sendLedMs();
			new LedSendServer(44, car + "您已" + inOut + "!无法开门", COLOR_RED)
					.sendLedMs();
			// LedSendUtil.OnAddtext(43, "" + car + "您已" + inOut + "!无法开门",
			// 25,42, 64, 32, COLOR_RED, 9);
			// LedSendUtil.OnAddtext(44, "" + car + "您已" + inOut + "!无法开门",
			// 25,42, 64, 32, COLOR_RED, 9);
		} else if (i == 2) {
			new LedSendServer(43, car + "您没有来访申请!无法" + inOut + "!", COLOR_RED)
					.sendLedMs();
			new LedSendServer(44, car + "您没有来访申请!无法" + inOut + "!", COLOR_RED)
					.sendLedMs();
			// LedSendUtil.OnAddtext(43, "" + car + "您没有来访申请!无法" + inOut + "!",
			// 25, 42, 64, 32, COLOR_RED, 9);
			// LedSendUtil.OnAddtext(44, "" + car + "您没有来访申请!无法" + inOut + "!",
			// 25, 42, 64, 32, COLOR_RED, 9);
		} else if (i == 3) {
			new LedSendServer(43, car + "工作时间!无法" + inOut + "请申请请假", COLOR_RED)
					.sendLedMs();
			new LedSendServer(44, car + "工作时间!无法" + inOut + "请申请请假", COLOR_RED)
					.sendLedMs();
			// LedSendUtil.OnAddtext(43, "" + car + "工作时间!无法" + inOut + "请申请请假",
			// 25, 42, 64, 32, COLOR_RED, 9);
			// LedSendUtil.OnAddtext(44, "" + car + "工作时间!无法" + inOut + "请申请请假",
			// 25, 42, 64, 32, COLOR_RED, 9);
		} else if (i == 4) {
			new LedSendServer(43, car + " 非工作时间!无法" + inOut + "请申请加班",
					COLOR_RED).sendLedMs();
			new LedSendServer(44, car + " 非工作时间!无法" + inOut + "请申请加班",
					COLOR_RED).sendLedMs();
			// LedSendUtil.OnAddtext(43, "" + car + " 非工作时间!无法" + inOut +
			// "请申请加班",
			// 25, 42, 64, 32, COLOR_RED, 9);
			// LedSendUtil.OnAddtext(44, "" + car + " 非工作时间!无法" + inOut +
			// "请申请加班",
			// 25, 42, 64, 32, COLOR_RED, 9);
		} else if (i == 5) {
			new LedSendServer(43, car + "车牌识别为空 请重新识别", COLOR_RED).sendLedMs();
			new LedSendServer(44, car + "车牌识别为空 请重新识别", COLOR_RED).sendLedMs();
			// LedSendUtil
			// .OnAddtext(43, "车牌识别为空 请重新识别", 25, 42, 64, 32, COLOR_RED, 9);
			// LedSendUtil
			// .OnAddtext(44, "车牌识别为空 请重新识别", 25, 42, 64, 32, COLOR_RED, 9);
		} else if (i == 6) {
			new LedSendServer(43, "验证码" + car + inOut + "!", COLOR_RED)
					.sendLedMs();
			new LedSendServer(44, "验证码" + car + inOut + "!", COLOR_RED)
					.sendLedMs();
			// LedSendUtil.OnAddtext(43, "验证码" + car + inOut + "!", 25, 42, 64,
			// 32, COLOR_GREEN, 9);
			// LedSendUtil.OnAddtext(44, "验证码" + car + inOut + "!", 25, 42, 64,
			// 32, COLOR_GREEN, 9);
		} else if (i == 7) {
			String date = Util.getDateTime("yyyy年MM月dd日EEEE");
			new LedSendServer(44, car + "上海红湖\n欢迎您！ "+ date, COLOR_RED)
					.sendLedMs();//44进门
			new LedSendServer(43, car + "欢迎再次\n光临！ "+ date, COLOR_RED)
					.sendLedMs();//43出门
			// LedSendUtil.OnAddtext(44, "上海红湖    欢迎您！     "
			// + date, 25, 42, 64, 32,
			// COLOR_RED, 9);
			// LedSendUtil.OnAddtext(43, "欢迎再次    光临！          "
			// + date, 25, 42, 64, 32,
			// COLOR_RED, 9);
		} else if (i == 8) {
			new LedSendServer(43, car + " 请联系 " + inOut + " 确认出门!", COLOR_RED)
					.sendLedMs();
			new LedSendServer(44, car + " 请联系 " + inOut + " 确认出门!", COLOR_RED)
					.sendLedMs();
			// LedSendUtil.OnAddtext(44, "" + car + " 请联系 " + inOut + " 确认出门!",
			// 25,
			// 42, 64, 32, COLOR_RED, 9);
			// LedSendUtil.OnAddtext(43, "" + car + " 请联系 " + inOut + " 确认出门!",
			// 25,
			// 42, 64, 32, COLOR_RED, 9);
		} else if (i == 9) {
			new LedSendServer(43, car + " 请门卫检车", COLOR_RED).sendLedMs();
			new LedSendServer(44, car + " 请门卫检车", COLOR_RED).sendLedMs();
			// LedSendUtil.OnAddtext(44, "" + car + " 请门卫检车",
			// 25, 42, 64, 32, COLOR_RED, 9);
			// LedSendUtil.OnAddtext(43, "" + car + " 请门卫检车",
			// 25, 42, 64, 32, COLOR_RED, 9);
		} else if (i == 10) {
			new LedSendServer(43, car + " 紧急开门 请" + inOut + "!", COLOR_RED)
					.sendLedMs();
			new LedSendServer(44, car + " 紧急开门 请" + inOut + "!", COLOR_RED)
					.sendLedMs();
			// LedSendUtil.OnAddtext(44, "" + car + " 紧急开门 请" + inOut + "!", 25,
			// 42, 64, 32, COLOR_GREEN, 9);
			// LedSendUtil.OnAddtext(43, "" + car + " 紧急开门 请" + inOut + "!", 25,
			// 42, 64, 32, COLOR_GREEN, 9);
		} else if (i == 11) {
			new LedSendServer(43, "验证码" + car + "无效!", COLOR_RED).sendLedMs();
			new LedSendServer(44, "验证码" + car + "无效!", COLOR_RED).sendLedMs();
			// LedSendUtil.OnAddtext(44, "验证码" + car + "无效!", 25, 42, 64, 32,
			// COLOR_RED, 9);
			// LedSendUtil.OnAddtext(43, "验证码" + car + "无效!", 25, 42, 64, 32,
			// COLOR_RED, 9);
		} else if (i == 12) {
			new LedSendServer(43, "验证码" + car + "为空!", COLOR_RED).sendLedMs();
			new LedSendServer(44, "验证码" + car + "为空!", COLOR_RED).sendLedMs();
			// LedSendUtil.OnAddtext(44, "验证码" + car + "为空!", 25, 42, 64, 32,
			// COLOR_RED, 9);
			// LedSendUtil.OnAddtext(43, "验证码" + car + "为空!", 25, 42, 64, 32,
			// COLOR_RED, 9);
		} else if (i == 13) {
			new LedSendServer(43, car + " 您的常访申请已过期!", COLOR_RED).sendLedMs();
			new LedSendServer(44, car + " 您的常访申请已过期!", COLOR_RED).sendLedMs();
			// LedSendUtil.OnAddtext(44, "" + car + " 您的常访申请已过期!", 25,
			// 42, 64, 32, COLOR_RED, 9);
			// LedSendUtil.OnAddtext(43, "" + car + " 您的常访申请已过期!", 25,
			// 42, 64, 32, COLOR_RED, 9);
		} else if (i == 14) {
			new LedSendServer(43, car + " 您的常访申请未审批!", COLOR_RED).sendLedMs();
			new LedSendServer(44, car + " 您的常访申请未审批!", COLOR_RED).sendLedMs();
			// LedSendUtil.OnAddtext(44, "" + car + " 您的常访申请未审批!", 25,
			// 42, 64, 32, COLOR_RED, 9);
			// LedSendUtil.OnAddtext(43, "" + car + " 您的常访申请未审批!", 25,
			// 42, 64, 32, COLOR_RED, 9);
		} else if (i == 15) {
			new LedSendServer(43, car + inOut, COLOR_RED).sendLedMs();
			new LedSendServer(44, car + inOut, COLOR_RED).sendLedMs();
			// LedSendUtil.OnAddtext(44, "" + car +"为"+ inOut, 25,
			// 42, 64, 32, COLOR_RED, 9);
			// LedSendUtil.OnAddtext(43, "" + car +"为"+ inOut, 25,
			// 42, 64, 32, COLOR_RED, 9);
		} else {
			new LedSendServer(43, "数据有误", COLOR_RED).sendLedMs();
			new LedSendServer(44, "数据有误", COLOR_RED).sendLedMs();
			// LedSendUtil.OnAddtext(43, "数据有误", 25, 42, 64, 32, COLOR_RED, 9);
			// LedSendUtil.OnAddtext(44, "数据有误", 25, 42, 64, 32, COLOR_RED, 9);
		}
	}

}
