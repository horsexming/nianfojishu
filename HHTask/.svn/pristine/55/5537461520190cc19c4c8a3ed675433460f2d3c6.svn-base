package com.task.ServerImpl.sop;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.task.Dao.TotalDao;
import com.task.entity.pmi.PmiManagement;
import com.task.entity.sop.ProcessInfor;
import com.task.entity.sop.ProcessInforReceiveLog;
import com.task.util.Util;

public class Pmi_processinfor implements Runnable {
	private ProcessInforReceiveLog pirlog;
	private TotalDao totalDao;
	private ProcessInfor processInfor;

	Pmi_processinfor(ProcessInfor processInfor, ProcessInforReceiveLog pirlog,
			TotalDao totalDao) {
		this.pirlog = pirlog;
		this.totalDao = totalDao;
		this.processInfor = processInfor;
	}

	@Override
	public void run() {
		// 查询PMI信息
		PmiManagement pmi = (PmiManagement) totalDao.getObjectById(
				PmiManagement.class, processInfor.getPmiId());
		if (pmi != null) {
			List<ProcessInforReceiveLog> pirlogList = new ArrayList<ProcessInforReceiveLog>();

			Socket socket;
			try {
				socket = new Socket(pmi.getPmi_ip(), Integer.parseInt(pmi
						.getPmi_port()));
				// 读取数据
				BufferedInputStream bis = new BufferedInputStream(socket
						.getInputStream());

				Float nowreceiveNumber = pirlog.getReceiveNumber();// 本次领取数量
				String firstApplyDate = pirlog.getFirstApplyDate();// 最初领取时间
				for (int i = 0; i < nowreceiveNumber; i++) {
					// 每次接收的数据
					String oneString = readBis(bis);// 先接收第一个字符
					System.out.print(oneString);

					// 如果第一个字符是 "AA",说明是正在生产中，并且是持续的传递单件产品的生产节拍
					if ("AA".equals(oneString)) {
						// AA 00 00 00 07 00 00 00 04 02 01 FF

						// 截取生产节拍 "00 00 00 07"==生产节拍
						Integer nowJiepai = 0;
						String nowJiepai_str = "";
						for (int j = 0; j < 4; j++) {
							String jiepai_data2 = readBis(bis);
							nowJiepai_str += Integer.parseInt(jiepai_data2);// 累计节拍字符
							System.out.print(jiepai_data2);
						}
						nowJiepai = Integer.parseInt(nowJiepai_str);// 生产节拍

						// 截取生产能耗 "00 00. 00 04 02 01"==能耗
						Float nowNenghao = 0F;
						String nowNenghao_str = "";
						// 先截取前两位
						for (float j = 0; j < 2; j++) {
							String nenghao_data2 = readBis(bis);
							nowNenghao_str += Integer.parseInt(nenghao_data2);// 累计节拍字符
							System.out.print(nenghao_data2);
						}
						nowNenghao_str += ".";// 添加小数点
						// 再截取后四位
						for (float j = 0; j < 4; j++) {
							String nenghao_data2 = readBis(bis);
							nowNenghao_str += Integer.parseInt(nenghao_data2);// 累计节拍字符
							System.out.print(nenghao_data2);
						}
						nowNenghao = Float.parseFloat(nowNenghao_str);

						// 读取最后的FF
						String lastString = readBis(bis);
						System.out.println(lastString);

						// 输出计算结果
						System.out.print("节拍:" + nowJiepai_str + "转换后数值:"
								+ nowJiepai);
						System.out.println("能耗" + nowNenghao_str + "转换后数值:"
								+ nowNenghao);
						// 先第一个领取的记录完成
						if (pirlog != null) {
							pirlog.setSubmitNumber(1F);
							pirlog.setBreakCount(0F);
							pirlog.setSumitApplyDate(Util.getDateTime());
							// 计算本次节拍
							pirlog.setAllJiepai(nowJiepai * 1F);
							// 本次能耗
							pirlog.setAllNenghao(nowNenghao);
							pirlog.setStatus("提交");
							// totalDao.update(pirlog);
							pirlogList.add(pirlog);

							// 创建新的领取记录
							ProcessInforReceiveLog pirlog_new = new ProcessInforReceiveLog();
							pirlog_new.setUsercodes(pirlog.getUsercodes());
							pirlog_new.setUserCardId(pirlog.getUserCardId());
							pirlog_new.setUsernames(pirlog.getUsernames());
							pirlog_new.setUserId(pirlog.getUserId());
							pirlog_new.setFirstApplyDate(Util.getDateTime());
							pirlog_new.setReceiveNumber(1F);// 本次领取数量
							pirlog_new.setStatus("领取");
							pirlog_new.setFk_processInforId(processInfor.getId());
							// pirlogList.add(pirlog_new);

							pirlog = pirlog_new;

						}

						if (lastString.equals("FF")) {
							// 当领取数量全部完成的时候，关闭电源
							if ((i + 1) == nowreceiveNumber) {
								// 打开电源
								Socket s2;
								try {
									s2 = new Socket("192.168.0.41", 8899);
									BufferedWriter bw2 = new BufferedWriter(
											new OutputStreamWriter(s2
													.getOutputStream()));
									bw2.write(0);
									bw2.newLine();
									bw2.flush();
									bw2.close();
									s2.close();
								} catch (Exception e) {
									e.printStackTrace();
								}
								// 增加循环次数，用于读取待机能耗
								i--;
							}
						}

					} else if ("CC".equals(oneString)) {
						// CC 表示已经断电，显示获取待机能耗
						// 数据 CC 00 00. 00 00 00 00 DD
						Float nowNenghao = 0F;
						String nowNenghao_str = "";
						// 先截取前两位
						for (float j = 0; j < 2; j++) {
							String nenghao_data2 = readBis(bis);
							nowNenghao_str += Integer.parseInt(nenghao_data2);// 累计节拍字符
							System.out.print(nenghao_data2);
						}
						nowNenghao_str += ".";// 添加小数点
						// 再截取后四位
						for (float j = 0; j < 4; j++) {
							String nenghao_data2 = readBis(bis);
							nowNenghao_str += Integer.parseInt(nenghao_data2);// 累计节拍字符
							System.out.print(nenghao_data2);
						}
						nowNenghao = Float.parseFloat(nowNenghao_str);
						System.out.println("电源关闭了,待机能耗:" + nowNenghao);
						// 先第一个领取的记录完成
						if (pirlog != null) {
							pirlog.setReceiveNumber(0F);
							pirlog.setSubmitNumber(0F);
							pirlog.setBreakCount(0F);
							pirlog.setFirstApplyDate(firstApplyDate);
							pirlog.setSumitApplyDate(Util.getDateTime());
							// 计算本次节拍
							pirlog.setAllJiepai(0F);
							// 本次能耗
							pirlog.setAllNenghao(nowNenghao);
							pirlog.setStatus("领取");
							pirlogList.add(pirlog);
						}

						break;
					}

				}
				bis.close();
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			for (ProcessInforReceiveLog processInforReceiveLog : pirlogList) {
				if (processInforReceiveLog.getId() != null) {
					totalDao.update2(processInforReceiveLog);
				} else {
					totalDao.save2(processInforReceiveLog);
				}
				
			}
		}
	}

	/***
	 * 读取数据
	 * 
	 * @param bis
	 * @return
	 * @throws Exception
	 */
	private static String readBis(BufferedInputStream bis) throws Exception {
		// 再截取后四位
		byte[] nenghao_data = new byte[1];// 先接收第一个字符
		bis.read(nenghao_data);// 读取数据
		return Util.byteArrayToHexString(nenghao_data);
	}

	public ProcessInforReceiveLog getPirlog() {
		return pirlog;
	}

	public void setPirlog(ProcessInforReceiveLog pirlog) {
		this.pirlog = pirlog;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public ProcessInfor getProcessInfor() {
		return processInfor;
	}

	public void setProcessInfor(ProcessInfor processInfor) {
		this.processInfor = processInfor;
	}

}
