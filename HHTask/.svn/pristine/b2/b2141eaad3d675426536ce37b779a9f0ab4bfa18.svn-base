package com.task.util;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.entity.Machine;
import com.task.entity.led.LED;
import com.task.entity.led.LEDLog;
import com.task.entity.sop.ProcessInfor;

public class LedSubmitServer extends Thread {

	TotalDao totalDao;
	ProcessInfor process;
	Float processNumbers;
	String name;
	String message;

	/****
	 * 有参的构造函数
	 */
	public LedSubmitServer(TotalDao totalDao, ProcessInfor process,
			Float processNumbers, String name, String message) {
		this.totalDao = totalDao;
		this.process = process;
		this.processNumbers = processNumbers;
		this.name = name;
		this.message = message;
	}

	@SuppressWarnings("unchecked")
	public String addLEDLog(Integer lEDId, LEDLog pageLog) {
		totalDao.createQueryUpdate(
				"update LEDLog set status='历史' where led.id=" + lEDId, null);
		StringBuffer sb = new StringBuffer();
		if (pageLog.getProcardId() != null && pageLog.getProcessNo() != null) {
			totalDao.createQueryUpdate(
					"update LEDLog set productStatus='完成' where led.id="
							+ lEDId + " and procardId ="
							+ pageLog.getProcardId() + " and processNo="
							+ pageLog.getProcessNo(), null);
			List<String> contextList = totalDao
					.query(
							"select context from LEDLog where led.id=? and productStatus='生产' ",
							lEDId);
			if (contextList.size() > 0) {
				for (String context : contextList) {
					sb.append(context + "\n");
				}
			}
			if (pageLog.getProductStatus() != null
					&& pageLog.getProductStatus().equals("生产")) {
				sb.append(pageLog.getContext());
			}
		}
		pageLog.setAddTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
		pageLog.setStatus("默认");
		LED led = (LED) totalDao.getObjectById(LED.class, lEDId);
		pageLog.setLed(led);
		totalDao.save(pageLog);
		return sb.toString();
	}

	public boolean addLEDLog(Integer lEDId, String context) {
		totalDao.createQueryUpdate(
				"update LEDLog set status='历史' where led.id=" + lEDId, null);
		LEDLog lEDLog = new LEDLog();
		lEDLog.setContext(context);
		lEDLog.setAddTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
		// lEDLog.setProductStatus("生产");
		lEDLog.setStatus("默认");
		LED led = (LED) totalDao.getObjectById(LED.class, lEDId);
		if (led != null) {
			// Set<LEDLog> ledLogSet = led.getlEDLog();
			// ledLogSet.add(lEDLog);
			// led.setlEDLog(ledLogSet);
			lEDLog.setLed(led);
			return totalDao.save(lEDLog);
		}
		return false;
	}

	/***
	 * 线程运行的主体
	 */
	public void run() {
		String hql_Led = "from LED where stations =?";
		LED ledManagement = (LED) totalDao.getObjectByCondition(hql_Led,
				process.getGongwei());
		if (ledManagement != null) {
			// 本次信息推送内容
			float num = processNumbers + process.getApplyCount()
					- process.getSubmmitCount() - process.getBreakCount();
			int number = (int) num;// 数量取整
			// 推送产品工序领取信息
			if (name == null || name.length() <= 0) {
				name = process.getUsernames();
			}
			if (name == null || name.length() <= 0) {
				name = "";
			}
			Integer proCardId = process.getProcard().getId();// proCardId
			Integer processNo = process.getProcessNO();// 工序号

			String sendMessage = message + process.getGongwei() + "\n"
					+ process.getProcard().getMarkId() + "\n"
					+ process.getProcard().getSelfCard() + "\n第" + processNo
					+ "工序\n" + process.getProcessName() + "\n数量:" + (number)
					+ "\n" + name;
			// 图纸位置信息
			// 暂时根据工位设置为固定的平板
			// ADM-04-06的对应平板位置在PTP-04 002
			// ADM-07-09的对应平板位置在PTP-07 003
			// ADM-10的对应平板位置在PTP-09 004
			if ("ASM-01".equals(process.getGongwei())
					|| "ASM-02".equals(process.getGongwei())
					|| "ASM-03".equals(process.getGongwei())) {
				sendMessage += "\n您需要查看的图纸已经推送至PTP-01工位的001号平板上,请您前往查看!";
			} else if ("ASM-04".equals(process.getGongwei())
					|| "ASM-05".equals(process.getGongwei())
					|| "ASM-06".equals(process.getGongwei())) {
				sendMessage += "\n您需要查看的图纸已经推送至PTP-04工位的002号平板上,请您前往查看!";
			} else if ("ASM-07".equals(process.getGongwei())
					|| "ASM-08".equals(process.getGongwei())
					|| "ASM-09".equals(process.getGongwei())) {
				sendMessage += "\n您需要查看的图纸已经推送至PTP-07工位的003号平板上,请您前往查看!";
			} else if ("ASM-10".equals(process.getGongwei())) {
				sendMessage += "\n您需要查看的图纸已经推送至PTP-09工位的004号平板上,请您前往查看!";
			}

			// 穿戴规范信息
			sendMessage += "\n" + process.getGongwei() + "\n穿戴规范:\n"
					+ ledManagement.getDress();

			// 设备状态
			if (process.getShebeiNo() != null
					&& process.getShebeiNo().length() > 0) {
				String hql_machine = "from Machine where no=?";
				List<Machine> list = totalDao.query(hql_machine, process
						.getShebeiNo());
				if (list != null && list.size() > 0) {
					sendMessage += "\n" + process.getGongwei() + "\n设备状态:\n";
					for (Machine machine2 : list) {
						sendMessage += machine2.getNo() + ":"
								+ machine2.getStatus();
					}
				}
			}
			// 将本产品、工序的推送信息发送，用于累计多个工序
			LEDLog ledLog = new LEDLog();
			ledLog.setProcardId(proCardId);
			ledLog.setProcessNo(processNo);
			ledLog.setContext(sendMessage);
			ledLog.setProductStatus("生产");
			sendMessage = addLEDLog(ledManagement.getId(), ledLog);

			// 发送LED
			String sendStatus = LedSendUtil.OnAddtext(
					ledManagement.getNumber(), sendMessage, ledManagement
							.getId());

			if (!"true".equals(sendStatus)) {
			} else {
				addLEDLog(ledManagement.getId(), sendMessage);
			}
		}
	}

}
