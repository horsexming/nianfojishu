package com.task.ServerImpl.gongyi.gongxu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.task.Dao.TotalDao;
import com.task.Server.gongyi.gongxu.ProcessDataServer;
import com.task.entity.gongyi.GongyiGuichengAffix;
import com.task.entity.gongyi.gongxu.DetectionItem;
import com.task.entity.gongyi.gongxu.HanjieGuochengCanshu;
import com.task.entity.gongyi.gongxu.HanjieJianceXiangmu;
import com.task.entity.gongyi.gongxu.HanjieZuoyeGuifan;
import com.task.entity.gongyi.gongxu.MaoliaoJishuTiaojian;
import com.task.entity.gongyi.gongxu.MaoliaoParam;
import com.task.entity.gongyi.gongxu.OperationOrder;
import com.task.entity.gongyi.gongxu.OperationOrderItem;
import com.task.entity.gongyi.gongxu.OperationStandard;
import com.task.entity.gongyi.gongxu.ProcessData;
import com.task.entity.gongyi.gongxu.ProcessGuochengCanshu;
import com.task.entity.gongyi.gongxu.ProcessPart;
import com.task.entity.gongyi.gongxu.ProcessTable;
import com.task.entity.gongyi.score.GongyiGuichengScore;

public class ProcessDataServerImpl implements ProcessDataServer {
	private TotalDao totalDao;

	/**
	 * 添加工序数据记录
	 * 
	 * @param ProcessData
	 * @return
	 */
	public ProcessData addProcessData(ProcessData processData) {
		boolean result = totalDao.save(processData);
		if (result) {
			this.totalDao
					.createQueryUpdate(
							"update GongyiGuicheng g set g.totalScore=g.totalScore+34 where g.id=?",
							null, new Object[] { processData
									.getGongyiGuichengId() });
			return processData;
		} else {
			return processData;
		}
	}

	/**
	 * 删除工序数据记录
	 * 
	 * @param ProcessData
	 * @return
	 */
	public String deleteProcessData(ProcessData processData) {
		ProcessData processDataTemp = this.getProcessDataById(processData
				.getId());
		boolean result = totalDao.delete(processDataTemp);
		if (result) {
			this.totalDao
					.createQueryUpdate(
							"update GongyiGuicheng g set g.totalScore=g.totalScore-34 where g.id=?",
							null, new Object[] { processDataTemp
									.getGongyiGuichengId() });
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * 更新工序数据记录
	 * 
	 * @param ProcessData
	 * @return
	 */
	public String updateProcessData(ProcessData processData) {

		ProcessData processDataTemp = this.getProcessDataById(processData
				.getId());

		if (processDataTemp != null) {
			String suoyinNumb = processData.getSuoyinNumb();
			String danNumb = processData.getDanNumb();
			String qianming = processData.getQianming();
			Date qianmingDate = processData.getQianmingDate();
			String processImg = processData.getProcessImg();
			String processVideo = processData.getProcessVideo();
			String hanjieImg = processData.getHanjieImg();
			String hanjieVideo = processData.getHanjieVideo();
			String jizhun = processData.getJizhun();
			String jiaOrMoju = processData.getJiaOrMoju();
			String cailiao = processData.getCailiao();
			// 分别对应 工序图表栏目分区明细 工序说明栏区A3 A4
			Integer onePage = processData.getOnePage();
			Integer twoPage = processData.getTwoPage();
			Integer threePage = processData.getThreePage();
			// 检验图表字段
			String jianyanZhidaoImg = processData.getJianyanZhidaoImg();
			String yichangYaoling = processData.getYichangYaoling();

			// 工序 设备 工装
			Integer gongxuId = processData.getGongxuId();
			Integer gongxuNo = processData.getGongxuNo();
			String gongxuName = processData.getGongxuName();
			Integer shebeiId = processData.getShebeiId();
			String shebeiNo = processData.getShebeiNo();
			String shebeiName = processData.getShebeiName();
			Integer gongzhuangId = processData.getGongzhuangId();
			String gongzhuangNo = processData.getGongzhuangNo();
			String gongzhuangName = processData.getGongzhuangName();
			// 量具
			Integer liangjuId = processData.getLiangjuId();
			String liangjuNo = processData.getLiangjuNo();
			String liangjuName = processData.getLiangjuName();
			String liangjuNoForCompany = processData.getLiangjuNoForCompany();
			String pici = processData.getPici();
			String remark = processData.getRemark();
			// 编辑状态
			String editStatus = processData.getEditStatus();
			// 毛料图表
			String maoliaoType = processData.getMaoliaoType();
			String paihao = processData.getPaihao();
			String houdu = processData.getHoudu();
			String fenlei = processData.getFenlei();
			String jishuTiaojian = processData.getJishuTiaojian();
			String qieliaoShebei = processData.getQieliaoShebei();
			// 共同标记 用于分页
			Long commonMark = processData.getCommonMark();
			if (suoyinNumb != null && !"".equals(suoyinNumb)) {
				processDataTemp.setSuoyinNumb(suoyinNumb);
			}
			if (danNumb != null && !"".equals(danNumb)) {
				processDataTemp.setDanNumb(danNumb);
			}
			if (qianming != null && !"".equals(qianming)) {
				processDataTemp.setQianming(qianming);
			}
			if (qianmingDate != null && !"".equals(qianmingDate)) {
				processDataTemp.setQianmingDate(qianmingDate);
			}
			if (processImg != null && !"".equals(processImg)) {
				processDataTemp.setProcessImg(processImg);
			}
			if (processVideo != null && !"".equals(processVideo)) {
				processDataTemp.setProcessVideo(processVideo);
			}
			if (hanjieImg != null && !"".equals(hanjieImg)) {
				processDataTemp.setHanjieImg(hanjieImg);
			}
			if (hanjieVideo != null && !"".equals(hanjieVideo)) {
				processDataTemp.setHanjieVideo(hanjieVideo);
			}
			if (jizhun != null && !"".equals(jizhun)) {
				processDataTemp.setJizhun(jizhun);
			}
			if (jiaOrMoju != null && !"".equals(jiaOrMoju)) {
				processDataTemp.setJiaOrMoju(jiaOrMoju);
			}
			if (cailiao != null && !"".equals(cailiao)) {
				processDataTemp.setCailiao(cailiao);
			}
			if (gongxuName != null && !"".equals(gongxuName)) {
				processDataTemp.setGongxuName(gongxuName);
			}
			if (onePage != null) {
				processDataTemp.setOnePage(onePage);
			}
			if (twoPage != null) {
				processDataTemp.setTwoPage(twoPage);
			}
			if (threePage != null) {
				processDataTemp.setThreePage(threePage);
			}
			if (jianyanZhidaoImg != null && !"".equals(jianyanZhidaoImg)) {
				processDataTemp.setJianyanZhidaoImg(jianyanZhidaoImg);
			}
			if (yichangYaoling != null && !"".equals(yichangYaoling)) {
				processDataTemp.setYichangYaoling(yichangYaoling);
			}

			// 工序 设备 工装
			if (gongxuId != null) {
				processDataTemp.setGongxuId(gongxuId);
			}
			if (gongxuNo != null && !"".equals(gongxuNo)) {
				processDataTemp.setGongxuNo(gongxuNo);
			}
			if (gongxuName != null && !"".equals(gongxuName)) {
				processDataTemp.setGongxuName(gongxuName);
			}
			if (shebeiId != null) {
				processDataTemp.setShebeiId(shebeiId);
			}
			if (shebeiNo != null && !"".equals(shebeiNo)) {
				processDataTemp.setShebeiNo(shebeiNo);
			}
			if (shebeiName != null && !"".equals(shebeiName)) {
				processDataTemp.setShebeiName(shebeiName);
			}
			if (gongzhuangId != null) {
				processDataTemp.setGongzhuangId(gongzhuangId);
			}
			if (gongzhuangNo != null && !"".equals(gongzhuangNo)) {
				processDataTemp.setGongzhuangNo(gongzhuangNo);
			}
			if (gongzhuangName != null && !"".equals(gongzhuangName)) {
				processDataTemp.setGongzhuangName(gongzhuangName);
			}
			if (liangjuId != null) {
				processDataTemp.setLiangjuId(liangjuId);
			}
			if (liangjuNo != null && !"".equals(liangjuNo)) {
				processDataTemp.setLiangjuNo(liangjuNo);
			}
			if (liangjuName != null && !"".equals(liangjuName)) {
				processDataTemp.setLiangjuName(liangjuName);
			}
			if (liangjuNoForCompany != null && !"".equals(liangjuNoForCompany)) {
				processDataTemp.setLiangjuNoForCompany(liangjuNoForCompany);
			}
			if (pici != null && !"".equals(pici)) {
				processDataTemp.setPici(pici);
			}
			if (remark != null ) {
				processDataTemp.setRemark(remark);
			}
			if (editStatus != null && !"".equals(editStatus)) {
				processDataTemp.setEditStatus(editStatus);
			}
			if (maoliaoType != null && !"".equals(maoliaoType)) {
				processDataTemp.setMaoliaoType(maoliaoType);
			}
			if (paihao != null && !"".equals(paihao)) {
				processDataTemp.setPaihao(paihao);
			}
			if (houdu != null && !"".equals(houdu)) {
				processDataTemp.setHoudu(houdu);
			}
			if (fenlei != null && !"".equals(fenlei)) {
				processDataTemp.setFenlei(fenlei);
			}
			if (jishuTiaojian != null && !"".equals(jishuTiaojian)) {
				processDataTemp.setJishuTiaojian(jishuTiaojian);
			}
			if (qieliaoShebei != null && !"".equals(qieliaoShebei)) {
				processDataTemp.setQieliaoShebei(qieliaoShebei);
			}
			// 分页
			if (commonMark != null) {
				processDataTemp.setCommonMark(commonMark);
			}
			boolean result = totalDao.update(processDataTemp);
			if (result) {
				return "true";
			} else {
				return "false";
			}
		}
		return "false";
	}

	/**
	 * 获得工序数据记录
	 * 
	 * @param id
	 * @return
	 */
	public ProcessData getProcessDataById(Integer id) {
		if (id != null) {
			return (ProcessData) totalDao.getObjectById(ProcessData.class, id);
		}
		return null;
	}

	/**
	 * 获得工序数据记录 根据工艺规程ID 工序ID
	 * 
	 * @param id
	 * @return
	 */
	public ProcessData getProcessDataBygongyiGuichengIdandprocessId(
			Integer gongyiGuichengId, Integer processId) {
		// String
		// hql="from ProcessData p where p.gongyiGuichengId=? and p.processId=?";
		// ProcessData
		// processData=(ProcessData)totalDao.getObjectByCondition(hql, new
		// Object[]{gongyiGuichengId,processId});

		String hql = "from ProcessData p where p.gongyiGuichengId=? and p.id=?";
		ProcessData processData = (ProcessData) totalDao.getObjectByCondition(
				hql, new Object[] { gongyiGuichengId, processId });
		return processData;
	}

	/**
	 * 获得工序数据记录 根据工艺规程ID
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getProcessDataListBygongyiGuichengId(Integer gongyiGuichengId) {
		String hql = "from ProcessData p where p.gongyiGuichengId=? order by gongxuNo";
		List list = totalDao.find(hql, new Object[] { gongyiGuichengId });
		return list;
	}

	/**
	 * 获得在干工序信息 根据工艺规程ID
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public List getProcessBygGygcId(Integer gongyiGuichengId) {
		// 查询在干件号的在干工序
		String hql = "from ProcessData p where p.gongyiGuichengId=? and gongxuNo in (select distinct processNO from ProcessInfor where procard.id in "
				+ "( select id from Procard where markId="
				+ "(select jianNumb from GongyiGuicheng where id=? )) and status='已领')";
		List list = totalDao.query(hql, gongyiGuichengId, gongyiGuichengId);
		return list;
	}

	/**
	 * 判断是否存在工序数据记录
	 * 
	 * @param
	 * @return
	 */
	public ProcessData isExistProcessData(Integer gongyiGuichengId,
			Integer processId) {
		// String
		// hql="from ProcessData p where p.gongyiGuichengId=? and p.processId=?";
		// ProcessData
		// processData=(ProcessData)totalDao.getObjectByCondition(hql, new
		// Object[]{gongyiGuichengId,processId});
		String hql = "from ProcessData p where p.gongyiGuichengId=? and p.id=?";
		ProcessData processData = (ProcessData) totalDao.getObjectByCondition(
				hql, new Object[] { gongyiGuichengId, processId });
		return processData;
	}

	/********************************* 作业规范 *************************************/
	/**
	 * 添加作业规程
	 * 
	 * @param
	 * @return
	 */
	public String addOperationStandard(OperationStandard operationStandard) {
		boolean result = totalDao.save(operationStandard);
		if (result) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * 删除作业规程
	 * 
	 * @param
	 * @return
	 */
	public String deleteOperationStandard(OperationStandard operationStandard) {
		boolean result = totalDao.delete(operationStandard);
		if (result) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * 修改作业规程
	 * 
	 * @param
	 * @return
	 */
	public String updateOperationStandard(OperationStandard operationStandard) {
		OperationStandard operationStandardTemp = this
				.getOperationStandardById(operationStandard.getId());
		if (operationStandardTemp != null) {
			String numb = operationStandard.getNumb();
			String content = operationStandard.getContent();
			if (numb != null && !"".equals(numb)) {
				operationStandardTemp.setNumb(numb);
			}
			if (content != null && !"".equals(content)) {
				operationStandardTemp.setContent(content);
			}
			boolean result = totalDao.update(operationStandardTemp);
			if (result) {
				return "true";
			} else {
				return "false";
			}
		}
		return "false";
	}

	/**
	 * 获得作业规程
	 * 
	 * @param
	 * @return
	 */
	public OperationStandard getOperationStandardById(Integer id) {
		if (id != null) {
			return (OperationStandard) totalDao.getObjectById(
					OperationStandard.class, id);
		}
		return null;
	}

	/**
	 * 获得作业规范集合
	 * 
	 * @param processDataId
	 * @return
	 */
	public List findAllOperationStandardListByprocessDataId(
			Integer processDataId) {
		String hql = "from OperationStandard o where o.processDataId=? order by o.numb asc";
		List list = totalDao.find(hql, new Object[] { processDataId });
		return list;
	}

	/********************************** 过程参数 **********************************/
	/**
	 * 添加过程参数记录
	 * 
	 * @param ProcessData
	 * @return
	 */
	public ProcessGuochengCanshu addProcessGuochengCanshu(
			ProcessGuochengCanshu processGuochengCanshu) {
		boolean result = totalDao.save(processGuochengCanshu);
		if (result) {
			return processGuochengCanshu;
		} else {
			return null;
		}
	}

	/**
	 * 更新过程参数记录
	 * 
	 * @param ProcessData
	 * @return
	 */
	public ProcessGuochengCanshu updateProcessGuochengCanshu(
			ProcessGuochengCanshu processGuochengCanshu) {
		ProcessGuochengCanshu processGuochengCanshuTemp = this
				.getProcessGuochengCanshuById(processGuochengCanshu.getId());
		if (processGuochengCanshuTemp != null) {
			String content = processGuochengCanshu.getContent();
			if (content != null && !"".equals(content)) {
				processGuochengCanshuTemp.setContent(content);
			}
			boolean result = totalDao.update(processGuochengCanshuTemp);
			if (result) {
				return processGuochengCanshuTemp;
			} else {
				return null;
			}
		}
		return null;
	}

	/**
	 * 获得过程参数记录
	 * 
	 * @param id
	 * @return
	 */
	public ProcessGuochengCanshu getProcessGuochengCanshuById(Integer id) {
		if (id != null) {
			return (ProcessGuochengCanshu) totalDao.getObjectById(
					ProcessGuochengCanshu.class, id);
		}
		return null;
	}

	/**
	 * 判断是否存在过程参数记录
	 * 
	 * @param id
	 * @return
	 */
	public List getProcessGuochengCanshuByprocessDataId(Integer procesDataId) {
		String hql = "from ProcessGuochengCanshu p where p.processDataId=?";
		List list = totalDao.find(hql, new Object[] { procesDataId });
		return list;
	}

	/*********************************** 检测项目 ***********************************/
	/**
	 * 添加检测项目
	 * 
	 * @param
	 * @return
	 */
	public String addDetectionItem(DetectionItem detectionItem) {
		boolean result = totalDao.save(detectionItem);
		if (result) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * 删除检测项目
	 * 
	 * @param
	 * @return
	 */
	public String deleteDetectionItem(DetectionItem detectionItem) {
		boolean result = totalDao.delete(detectionItem);
		if (result) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * 修改检测项目
	 * 
	 * @param
	 * @return
	 */
	public String updateDetectionItem(DetectionItem detectionItem) {
		DetectionItem detectionItemTemp = this
				.getDetectionItemById(detectionItem.getId());
		if (detectionItemTemp != null) {
			/** 序号 */
			String numb = detectionItem.getNumb();
			/** 检测项目 */
			String xiangmu = detectionItem.getXiangmu();
			/** 测量器具 */
			String qiju = detectionItem.getQiju();
			/** 操作者测定频次 */
			String caozuoPinci = detectionItem.getCaozuoPinci();
			/** 巡检测定频次 */
			String xunjianPinci = detectionItem.getXunjianPinci();
			if (numb != null && !"".equals(numb)) {
				detectionItemTemp.setNumb(numb);
			}
			if (xiangmu != null && !"".equals(xiangmu)) {
				detectionItemTemp.setXiangmu(xiangmu);
			}
			if (qiju != null && !"".equals(qiju)) {
				detectionItemTemp.setQiju(qiju);
			}
			if (caozuoPinci != null && !"".equals(caozuoPinci)) {
				detectionItemTemp.setCaozuoPinci(caozuoPinci);
			}
			if (xunjianPinci != null && !"".equals(xunjianPinci)) {
				detectionItemTemp.setXunjianPinci(xunjianPinci);
			}
			boolean result = totalDao.update(detectionItemTemp);
			if (result) {
				return "true";
			} else {
				return "false";
			}
		}
		return "false";
	}

	/**
	 * 获得检测项目
	 * 
	 * @param
	 * @return
	 */
	public DetectionItem getDetectionItemById(Integer id) {
		if (id != null) {
			return (DetectionItem) totalDao.getObjectById(DetectionItem.class,
					id);
		}
		return null;
	}

	/**
	 * 获得检测项目集合
	 * 
	 * @param processDataId
	 * @return
	 */
	public List findAllDetectionItemListByprocessDataId(Integer processDataId) {
		String hql = "from DetectionItem d where d.processDataId=? order by d.numb asc";
		List list = totalDao.find(hql, new Object[] { processDataId });
		return list;
	}

	/********************************** 工艺程序图表 **********************************/
	/**
	 * 添加工艺程序图表
	 * 
	 * @param
	 * @return
	 */
	public ProcessTable addProcessTable(ProcessTable processTable) {
		boolean result = totalDao.save(processTable);
		if (result) {
			return processTable;
		} else {
			return null;
		}
	}

	/**
	 * 删除工艺程序图表
	 * 
	 * @param
	 * @return
	 */
	public String deleteProcessTable(ProcessTable processTable) {
		boolean result = totalDao.delete(processTable);
		if (result) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * 修改工艺程序图表
	 * 
	 * @param
	 * @return
	 */
	public ProcessTable updateProcessTable(ProcessTable processTable) {
		ProcessTable processTableTemp = this.getProcessTableById(processTable
				.getId());
		if (processTableTemp != null) {
			/** 第几页 */
			Integer diPage = processTable.getDiPage();
			/** 共几页 */
			Integer gongPage = processTable.getGongPage();
			/** 索引号 */
			String suoyinNumb = processTable.getSuoyinNumb();
			/** 更改单号 */
			String danNumb = processTable.getDanNumb();
			/** 签名 */
			String qianming = processTable.getQianming();
			/** 日期 */
			Date qianmingDate = processTable.getQianmingDate();
			if (diPage != null) {
				processTableTemp.setDiPage(diPage);
			}
			if (gongPage != null && !"".equals(gongPage)) {
				processTableTemp.setGongPage(gongPage);
			}
			if (suoyinNumb != null && !"".equals(suoyinNumb)) {
				processTableTemp.setSuoyinNumb(suoyinNumb);
			}
			if (danNumb != null && !"".equals(danNumb)) {
				processTableTemp.setDanNumb(danNumb);
			}
			if (qianming != null && !"".equals(qianming)) {
				processTableTemp.setQianming(qianming);
			}
			if (qianmingDate != null) {
				processTableTemp.setQianmingDate(qianmingDate);
			}
			boolean result = totalDao.update(processTableTemp);
			if (result) {
				return processTableTemp;
			} else {
				return null;
			}
		}
		return null;
	}

	/**
	 * 获得工艺程序图表 根据ID
	 * 
	 * @param
	 * @return
	 */

	public ProcessTable getProcessTableById(Integer id) {
		if (id != null) {
			return (ProcessTable) totalDao
					.getObjectById(ProcessTable.class, id);
		}
		return null;
	}

	/**
	 * 获得工艺程序图表 更具工艺规程ID
	 * 
	 * @param
	 * @return
	 */

	public ProcessTable getProcessTableBygongyiGuichengId(
			Integer gongyiGuichengId) {
		String hql = "from ProcessTable p where p.gongyiGuichengId=?";
		ProcessTable processTable = (ProcessTable) totalDao
				.getObjectByCondition(hql, new Object[] { gongyiGuichengId });
		return processTable;
	}

	/********************************** 零件 **********************************/
	/**
	 * 添加零件
	 * 
	 * @param
	 * @return
	 */
	public ProcessPart addProcessPart(ProcessPart processPart) {
		boolean result = totalDao.save(processPart);
		if (result) {
			return processPart;
		} else {
			return null;
		}
	}

	/**
	 * 删除零件
	 * 
	 * @param
	 * @return
	 */
	public String deleteProcessPart(ProcessPart processPart) {
		boolean result = totalDao.delete(processPart);
		if (result) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * 修改零件
	 * 
	 * @param
	 * @return
	 */
	public ProcessPart updateProcessPart(ProcessPart processPart) {
		ProcessPart processPartTemp = this.getProcessPartById(processPart
				.getId());
		if (processPartTemp != null) {
			/** 序号 */
			String numb = processPart.getNumb();
			/** 件号 */
			String jianNumb = processPart.getJianNumb();
			/** 件名 */
			String jianName = processPart.getJianNumb();
			/** 单台数量 */
			String danNumb = processPart.getDanNumb();
			/** 材料 标准 规格 */
			String cailiao = processPart.getCailiao();
			/** 备注 */
			String remark = processPart.getRemark();

			if (numb != null && !"".equals(numb)) {
				processPartTemp.setNumb(numb);
			}
			if (jianNumb != null && !"".equals(jianNumb)) {
				processPartTemp.setJianNumb(jianNumb);
			}
			if (jianName != null && !"".equals(jianName)) {
				processPartTemp.setJianName(jianName);
			}
			if (danNumb != null && !"".equals(danNumb)) {
				processPartTemp.setDanNumb(danNumb);
			}
			if (cailiao != null && !"".equals(cailiao)) {
				processPartTemp.setCailiao(cailiao);
			}
			if (remark != null && !"".equals(remark)) {
				processPartTemp.setRemark(remark);
			}
			boolean result = totalDao.update(processPartTemp);
			if (result) {
				return processPartTemp;
			} else {
				return null;
			}
		}
		return null;
	}

	/**
	 * 获得工艺程序图表 根据ID
	 * 
	 * @param
	 * @return
	 */

	public ProcessPart getProcessPartById(Integer id) {
		if (id != null) {
			return (ProcessPart) totalDao.getObjectById(ProcessPart.class, id);
		}
		return null;
	}

	/**
	 * 获得工艺程序图表 更具工艺规程ID
	 * 
	 * @param
	 * @return
	 */

	public List getProcessPartListByprocessDataId(Integer processDataId) {
		String hql = "from ProcessPart p where p.processDataId=?";
		List list = totalDao.find(hql, new Object[] { processDataId });
		return list;
	}

	/***************************************** 操作顺序 *****************************************************/
	/**
	 * 添加操作顺序
	 * 
	 * @param
	 * @return
	 */
	public OperationOrder addOperationOrder(OperationOrder operationOrder) {
		boolean result = totalDao.save(operationOrder);
		if (result) {
			return operationOrder;
		} else {
			return null;
		}
	}

	/**
	 * 删除操作顺序
	 * 
	 * @param
	 * @return
	 */
	public String deleteOperationOrder(OperationOrder operationOrder) {
		boolean result = totalDao.delete(operationOrder);
		if (result) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * 修改操作顺序
	 * 
	 * @param
	 * @return
	 */
	public OperationOrder updateOperationOrder(OperationOrder operationOrder) {
		OperationOrder operationOrderTemp = this
				.getOperationOrderById(operationOrder.getId());
		if (operationOrderTemp != null) {
			/** 操作序号 */
			String numb = operationOrder.getNumb();
			/** 操作顺序内容 */
			String content = operationOrder.getContent();
			if (numb != null && !"".equals(numb)) {
				operationOrderTemp.setNumb(numb);
			}
			if (content != null && !"".equals(content)) {
				operationOrderTemp.setContent(content);
			}
			boolean result = totalDao.update(operationOrderTemp);
			if (result) {
				return operationOrderTemp;
			} else {
				return null;
			}
		}
		return null;
	}

	/**
	 * 获得操作顺序 根据ID
	 * 
	 * @param
	 * @return
	 */

	public OperationOrder getOperationOrderById(Integer id) {
		if (id != null) {
			return (OperationOrder) totalDao.getObjectById(
					OperationOrder.class, id);
		}
		return null;
	}

	/**
	 * 获得操作顺序 更具工艺数据ID
	 * 
	 * @param
	 * @return
	 */

	public List getOperationOrderListByprocessDataId(Integer processDataId) {
		String hql = "from OperationOrder o where o.processDataId=?";
		List list = totalDao.find(hql, new Object[] { processDataId });
		return list;
	}

	/***************************************** 操作顺序下检查项目 ********************************************/
	/**
	 * 添加操作顺序下检查项目
	 * 
	 * @param
	 * @return
	 */
	public OperationOrderItem addOperationOrderItem(
			OperationOrderItem operationOrderItem) {
		boolean result = totalDao.save(operationOrderItem);
		if (result) {
			return operationOrderItem;
		} else {
			return null;
		}
	}

	/**
	 * 删除操作顺序下检查项目
	 * 
	 * @param
	 * @return
	 */
	public String deleteOperationOrderItem(OperationOrderItem operationOrderItem) {
		boolean result = totalDao.delete(operationOrderItem);
		if (result) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * 修改操作顺序下检查项目
	 * 
	 * @param
	 * @return
	 */
	public OperationOrderItem updateOperationOrderItem(
			OperationOrderItem operationOrderItem) {
		OperationOrderItem operationOrderItemTemp = this
				.getOperationOrderItemById(operationOrderItem.getId());
		if (operationOrderItemTemp != null) {
			/** 检测项目 */
			String xiangmu = operationOrderItem.getXiangmu();
			/** 测量器具 */
			String qiju = operationOrderItem.getQiju();
			/** 判定标准 */
			String pandingBiaozhun = operationOrderItem.getPandingBiaozhun();
			/** 检查频次 */
			String jianchaPinci = operationOrderItem.getJianchaPinci();
			if (xiangmu != null && !"".equals(xiangmu)) {
				operationOrderItemTemp.setXiangmu(xiangmu);
			}
			if (qiju != null && "".equals(qiju)) {
				operationOrderItemTemp.setQiju(qiju);
			}
			if (pandingBiaozhun != null && !"".equals(pandingBiaozhun)) {
				operationOrderItemTemp.setPandingBiaozhun(pandingBiaozhun);
			}
			if (jianchaPinci != null && "".equals(jianchaPinci)) {
				operationOrderItemTemp.setJianchaPinci(jianchaPinci);
			}
			boolean result = totalDao.update(operationOrderItemTemp);
			if (result) {
				return operationOrderItemTemp;
			} else {
				return null;
			}
		}
		return null;
	}

	/**
	 * 获得操作顺序下检查项目 根据ID
	 * 
	 * @param
	 * @return
	 */

	public OperationOrderItem getOperationOrderItemById(Integer id) {
		if (id != null) {
			return (OperationOrderItem) totalDao.getObjectById(
					OperationOrderItem.class, id);
		}
		return null;
	}

	/**
	 * 获得操作顺序下检查项目 根据操作顺序ID
	 * 
	 * @param
	 * @return
	 */

	public List getOperationOrderItemListByoperationOrderId(
			Integer operationOrderId) {
		String hql = "from OperationOrderItem o where o.operationOrderId=?";
		List list = totalDao.find(hql, new Object[] { operationOrderId });
		return list;
	}

	/***************************************** 毛料参数 ********************************************/
	/**
	 * 添加毛料参数
	 * 
	 * @param
	 * @return
	 */
	public MaoliaoParam addMaoliaoParam(MaoliaoParam maoliaoParam) {
		boolean result = totalDao.save(maoliaoParam);
		if (result) {
			return maoliaoParam;
		} else {
			return null;
		}
	}

	/**
	 * 删除毛料参数
	 * 
	 * @param
	 * @return
	 */
	public String deleteMaoliaoParam(MaoliaoParam maoliaoParam) {
		boolean result = totalDao.delete(maoliaoParam);
		if (result) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * 修改毛料参数
	 * 
	 * @param
	 * @return
	 */
	public MaoliaoParam updateMaoliaoParam(MaoliaoParam maoliaoParam) {
		MaoliaoParam maoliaoParamTemp = this.getMaoliaoParamById(maoliaoParam
				.getId());
		if (maoliaoParamTemp != null) {
			/** 毛料参数 */
			String numb = maoliaoParam.getNumb();
			String content = maoliaoParam.getContent();
			String danwei = maoliaoParam.getDanwei();
			String shuzhi = maoliaoParam.getShuzhi();
			String remark = maoliaoParam.getRemark();
			if (numb != null && !"".equals(numb)) {
				maoliaoParamTemp.setNumb(numb);
			}
			if (content != null && !"".equals(content)) {
				maoliaoParamTemp.setContent(content);
			}
			if (danwei != null && !"".equals(danwei)) {
				maoliaoParamTemp.setDanwei(danwei);
			}
			if (shuzhi != null && !"".equals(shuzhi)) {
				maoliaoParamTemp.setShuzhi(shuzhi);
			}
			if (remark != null && !"".equals(remark)) {
				maoliaoParamTemp.setRemark(remark);
			}
			boolean result = totalDao.update(maoliaoParamTemp);
			if (result) {
				return maoliaoParamTemp;
			} else {
				return null;
			}
		}
		return null;
	}

	/**
	 * 获得毛料参数
	 * 
	 * @param
	 * @return
	 */

	public MaoliaoParam getMaoliaoParamById(Integer id) {
		if (id != null) {
			return (MaoliaoParam) totalDao
					.getObjectById(MaoliaoParam.class, id);
		}
		return null;
	}

	/**
	 * 获得毛料参数 根据工序数据ID
	 * 
	 * @param
	 * @return
	 */

	public List getMaoliaoParamListByProcessDataId(Integer processDataId) {
		String hql = "from MaoliaoParam m where m.processDataId=?";
		List list = totalDao.find(hql, new Object[] { processDataId });
		return list;
	}

	/***************************************** 毛料技术条件 ********************************************/
	/**
	 * 添加毛料技术条件
	 * 
	 * @param
	 * @return
	 */
	public MaoliaoJishuTiaojian addMaoliaoJishuTiaojian(
			MaoliaoJishuTiaojian maoliaoJishuTiaojian) {
		boolean result = totalDao.save(maoliaoJishuTiaojian);
		if (result) {
			return maoliaoJishuTiaojian;
		} else {
			return null;
		}
	}

	/**
	 * 删除毛料技术条件
	 * 
	 * @param
	 * @return
	 */
	public String deleteMaoliaoJishuTiaojian(
			MaoliaoJishuTiaojian maoliaoJishuTiaojian) {
		boolean result = totalDao.delete(maoliaoJishuTiaojian);
		if (result) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * 修改毛料技术条件
	 * 
	 * @param
	 * @return
	 */
	public MaoliaoJishuTiaojian updateMaoliaoJishuTiaojian(
			MaoliaoJishuTiaojian maoliaoJishuTiaojian) {
		MaoliaoJishuTiaojian maoliaoJishuTiaojianTemp = this
				.getMaoliaoJishuTiaojianById(maoliaoJishuTiaojian.getId());
		if (maoliaoJishuTiaojianTemp != null) {
			/** 毛料技术条件 */

			String content = maoliaoJishuTiaojian.getContent();
			if (content != null && !"".equals(content)) {
				maoliaoJishuTiaojianTemp.setContent(content);
			}
			boolean result = totalDao.update(maoliaoJishuTiaojianTemp);
			if (result) {
				return maoliaoJishuTiaojianTemp;
			} else {
				return null;
			}
		}
		return null;
	}

	/**
	 * 获得毛料技术条件
	 * 
	 * @param
	 * @return
	 */

	public MaoliaoJishuTiaojian getMaoliaoJishuTiaojianById(Integer id) {
		if (id != null) {
			return (MaoliaoJishuTiaojian) totalDao.getObjectById(
					MaoliaoJishuTiaojian.class, id);
		}
		return null;
	}

	/**
	 * 获得毛料技术条件 根据工序数据ID
	 * 
	 * @param
	 * @return
	 */

	public List getMaoliaoJishuTiaojianListByProcessDataId(Integer processDataId) {
		String hql = "from MaoliaoJishuTiaojian m where m.processDataId=?";
		List list = totalDao.find(hql, new Object[] { processDataId });
		return list;
	}

	/***************************************** 焊接作业规范 ********************************************/
	/**
	 * 添加焊接作业规范
	 * 
	 * @param
	 * @return
	 */
	public HanjieZuoyeGuifan addHanjieZuoyeGuifan(
			HanjieZuoyeGuifan hanjieZuoyeGuifan) {
		boolean result = totalDao.save(hanjieZuoyeGuifan);
		if (result) {
			return hanjieZuoyeGuifan;
		} else {
			return null;
		}
	}

	/**
	 * 删除焊接作业规范
	 * 
	 * @param
	 * @return
	 */
	public String deleteHanjieZuoyeGuifan(HanjieZuoyeGuifan hanjieZuoyeGuifan) {
		boolean result = totalDao.delete(hanjieZuoyeGuifan);
		if (result) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * 修改焊接作业规范
	 * 
	 * @param
	 * @return
	 */
	public HanjieZuoyeGuifan updateHanjieZuoyeGuifan(
			HanjieZuoyeGuifan hanjieZuoyeGuifan) {
		HanjieZuoyeGuifan hanjieZuoyeGuifanTemp = this
				.getHanjieZuoyeGuifanById(hanjieZuoyeGuifan.getId());
		if (hanjieZuoyeGuifanTemp != null) {
			/** 焊接作业规范 */
			String numb = hanjieZuoyeGuifan.getNumb();
			String content = hanjieZuoyeGuifan.getContent();
			if (numb != null && !"".equals(numb)) {
				hanjieZuoyeGuifanTemp.setNumb(numb);
			}
			if (content != null && !"".equals(content)) {
				hanjieZuoyeGuifanTemp.setContent(content);
			}
			boolean result = totalDao.update(hanjieZuoyeGuifanTemp);
			if (result) {
				return hanjieZuoyeGuifanTemp;
			} else {
				return null;
			}
		}
		return null;
	}

	/**
	 * 获得焊接作业规范
	 * 
	 * @param
	 * @return
	 */

	public HanjieZuoyeGuifan getHanjieZuoyeGuifanById(Integer id) {
		if (id != null) {
			return (HanjieZuoyeGuifan) totalDao.getObjectById(
					HanjieZuoyeGuifan.class, id);
		}
		return null;
	}

	/**
	 * 获得焊接作业规范 根据工序数据ID
	 * 
	 * @param
	 * @return
	 */

	public List getHanjieZuoyeGuifanListByProcessDataId(Integer processDataId) {
		String hql = "from HanjieZuoyeGuifan h where h.processDataId=?";
		List list = totalDao.find(hql, new Object[] { processDataId });
		return list;
	}

	/***************************************** 焊接过程参数 ********************************************/
	/**
	 * 添加焊接过程参数*
	 * 
	 * @param
	 * @return
	 */
	public HanjieGuochengCanshu addHanjieGuochengCanshu(
			HanjieGuochengCanshu hanjieGuochengCanshu) {
		boolean result = totalDao.save(hanjieGuochengCanshu);
		if (result) {
			return hanjieGuochengCanshu;
		} else {
			return null;
		}
	}

	/**
	 * 删除焊接过程参数*
	 * 
	 * @param
	 * @return
	 */
	public String deleteHanjieGuochengCanshu(
			HanjieGuochengCanshu hanjieGuochengCanshu) {
		boolean result = totalDao.delete(hanjieGuochengCanshu);
		if (result) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * 修改焊接过程参数*
	 * 
	 * @param
	 * @return
	 */
	public HanjieGuochengCanshu updateHanjieGuochengCanshu(
			HanjieGuochengCanshu hanjieGuochengCanshu) {
		HanjieGuochengCanshu hanjieGuochengCanshuTemp = this
				.getHanjieGuochengCanshuById(hanjieGuochengCanshu.getId());
		if (hanjieGuochengCanshuTemp != null) {
			/** 焊接过程参数 **/
			/** 材料牌号及厚度 */
			String cailiaoPaihaoHoudu = hanjieGuochengCanshu
					.getCailiaoPaihaoHoudu();
			/** 焊料名称直径 */
			String hanliaoMingchengZhijing = hanjieGuochengCanshu
					.getHanliaoMingchengZhijing();
			/** 气体流量 */
			String qitiLiuliang = hanjieGuochengCanshu.getQitiLiuliang();
			/** 电流强度 */
			String dianliuQiangdu = hanjieGuochengCanshu.getDianliuQiangdu();
			/** 保护气体 */
			String baohuQiti = hanjieGuochengCanshu.getBaohuQiti();
			/** 钨丝直径 */
			String wusiZhijing = hanjieGuochengCanshu.getWusiZhijing();
			/** 送丝速度 */
			String songsiSudu = hanjieGuochengCanshu.getSongsiSudu();
			/** 电弧电压 */
			String dianhuDianya = hanjieGuochengCanshu.getDianhuDianya();
			/** 附注 */
			String remark = hanjieGuochengCanshu.getRemark();
			if (cailiaoPaihaoHoudu != null && !"".equals(cailiaoPaihaoHoudu)) {
				hanjieGuochengCanshuTemp
						.setCailiaoPaihaoHoudu(cailiaoPaihaoHoudu);
			}
			if (hanliaoMingchengZhijing != null
					&& !"".equals(hanliaoMingchengZhijing)) {
				hanjieGuochengCanshuTemp
						.setHanliaoMingchengZhijing(hanliaoMingchengZhijing);
			}
			if (qitiLiuliang != null && !"".equals(qitiLiuliang)) {
				hanjieGuochengCanshuTemp.setQitiLiuliang(qitiLiuliang);
			}
			if (dianliuQiangdu != null && !"".equals(dianliuQiangdu)) {
				hanjieGuochengCanshuTemp.setDianliuQiangdu(dianliuQiangdu);
			}
			if (baohuQiti != null && !"".equals(baohuQiti)) {
				hanjieGuochengCanshuTemp.setBaohuQiti(baohuQiti);
			}
			if (wusiZhijing != null && !"".equals(wusiZhijing)) {
				hanjieGuochengCanshuTemp.setWusiZhijing(wusiZhijing);
			}
			if (songsiSudu != null && !"".equals(songsiSudu)) {
				hanjieGuochengCanshuTemp.setSongsiSudu(songsiSudu);
			}
			if (dianhuDianya != null && !"".equals(dianhuDianya)) {
				hanjieGuochengCanshuTemp.setDianhuDianya(dianhuDianya);
			}
			if (remark != null && !"".equals(remark)) {
				hanjieGuochengCanshuTemp.setRemark(remark);
			}
			boolean result = totalDao.update(hanjieGuochengCanshuTemp);
			if (result) {
				return hanjieGuochengCanshuTemp;
			} else {
				return null;
			}
		}
		return null;
	}

	/**
	 * 获得焊接过程参数*
	 * 
	 * @param
	 * @return
	 */

	public HanjieGuochengCanshu getHanjieGuochengCanshuById(Integer id) {
		if (id != null) {
			return (HanjieGuochengCanshu) totalDao.getObjectById(
					HanjieGuochengCanshu.class, id);
		}
		return null;
	}

	/**
	 * 获得焊接过程参数 根据工序数据ID
	 * 
	 * @param
	 * @return
	 */

	public HanjieGuochengCanshu getHanjieGuochengCanshuByProcessDataId(
			Integer processDataId) {
		String hql = "from HanjieGuochengCanshu h where h.processDataId=?";
		HanjieGuochengCanshu hanjieGuochengCanshu = (HanjieGuochengCanshu) totalDao
				.getObjectByCondition(hql, new Object[] { processDataId });
		return hanjieGuochengCanshu;
	}

	/***************************************** 焊接检测项目 ********************************************/
	/**
	 * 添加焊接检测项目
	 * 
	 * @param
	 * @return
	 */
	public HanjieJianceXiangmu addHanjieJianceXiangmu(
			HanjieJianceXiangmu hanjieJianceXiangmu) {
		boolean result = totalDao.save(hanjieJianceXiangmu);
		if (result) {
			return hanjieJianceXiangmu;
		} else {
			return null;
		}
	}

	/**
	 * 删除焊接检测项目
	 * 
	 * @param
	 * @return
	 */
	public String deleteHanjieJianceXiangmu(
			HanjieJianceXiangmu hanjieJianceXiangmu) {
		boolean result = totalDao.delete(hanjieJianceXiangmu);
		if (result) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * 修改焊焊接检测项目
	 * 
	 * @param
	 * @return
	 */
	public HanjieJianceXiangmu updateHanjieJianceXiangmu(
			HanjieJianceXiangmu hanjieJianceXiangmu) {
		HanjieJianceXiangmu hanjieJianceXiangmuTemp = this
				.getHanjieJianceXiangmuById(hanjieJianceXiangmu.getId());
		if (hanjieJianceXiangmuTemp != null) {
			/** 焊接检测项目 **/
			/** 序号 */
			String numb = hanjieJianceXiangmu.getNumb();
			/** 检测项目 */
			String xiangmu = hanjieJianceXiangmu.getXiangmu();
			/** 测量器具 */
			String qiju = hanjieJianceXiangmu.getQiju();
			/** 操作者测定频次 */
			String caozuoPinci = hanjieJianceXiangmu.getCaozuoPinci();
			/** 巡检测定频次 */
			String xunjianPinci = hanjieJianceXiangmu.getXunjianPinci();
			if (numb != null && !"".equals(numb)) {
				hanjieJianceXiangmuTemp.setNumb(numb);
			}
			if (xiangmu != null && !"".equals(xiangmu)) {
				hanjieJianceXiangmuTemp.setXiangmu(xiangmu);
			}
			if (qiju != null && !"".equals(qiju)) {
				hanjieJianceXiangmuTemp.setQiju(qiju);
			}
			if (caozuoPinci != null && !"".equals(caozuoPinci)) {
				hanjieJianceXiangmuTemp.setCaozuoPinci(caozuoPinci);
			}
			if (xunjianPinci != null && !"".equals(xunjianPinci)) {
				hanjieJianceXiangmuTemp.setXunjianPinci(xunjianPinci);
			}
			boolean result = totalDao.update(hanjieJianceXiangmuTemp);
			if (result) {
				return hanjieJianceXiangmuTemp;
			} else {
				return null;
			}
		}
		return null;
	}

	/**
	 * 获得焊接检测项目
	 * 
	 * @param
	 * @return
	 */

	public HanjieJianceXiangmu getHanjieJianceXiangmuById(Integer id) {
		if (id != null) {
			return (HanjieJianceXiangmu) totalDao.getObjectById(
					HanjieJianceXiangmu.class, id);
		}
		return null;
	}

	/**
	 * 获得焊接检测项目 根据工序数据ID
	 * 
	 * @param
	 * @return
	 */

	public List getHanjieJianceXiangmuListByProcessDataId(Integer processDataId) {
		String hql = "from HanjieJianceXiangmu h where h.processDataId=?";
		List list = totalDao.find(hql, new Object[] { processDataId });
		return list;
	}

	/************************************** 工艺规程附件 *******************************************************/
	/**
	 * 添加工艺规程附件
	 * 
	 * @param
	 * @return
	 */
	public GongyiGuichengAffix addGongyiGuichengAffix(
			GongyiGuichengAffix gongyiGuichengAffix) {
		boolean result = totalDao.save(gongyiGuichengAffix);
		if (result) {
			return gongyiGuichengAffix;
		} else {
			return null;
		}
	}

	/**
	 * 删除工艺规程附件
	 * 
	 * @param
	 * @return
	 */
	public String deleteGongyiGuichengAffix(
			GongyiGuichengAffix gongyiGuichengAffix) {
		boolean result = totalDao.delete(gongyiGuichengAffix);
		if (result) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * 修改工艺规程附件
	 * 
	 * @param
	 * @return
	 */
	public GongyiGuichengAffix updateGongyiGuichengAffix(
			GongyiGuichengAffix gongyiGuichengAffix) {
		GongyiGuichengAffix gongyiGuichengAffixTemp = this
				.getGongyiGuichengAffixById(gongyiGuichengAffix.getId());
		if (gongyiGuichengAffixTemp != null) {
			String url = gongyiGuichengAffix.getUrl();
			String fileName = gongyiGuichengAffix.getFileName();
			String affixType = gongyiGuichengAffix.getAffixType();
			String fileType = gongyiGuichengAffix.getFileType();
			String weizhi = gongyiGuichengAffix.getWeizhi();
			if (url != null && !"".equals(url)) {
				gongyiGuichengAffixTemp.setUrl(url);
			}
			if (fileName != null && !"".equals(fileName)) {
				gongyiGuichengAffixTemp.setFileName(fileName);
			}
			if (affixType != null && !"".equals(affixType)) {
				gongyiGuichengAffixTemp.setAffixType(affixType);
			}
			if (fileType != null && !"".equals(fileType)) {
				gongyiGuichengAffixTemp.setFileType(fileType);
			}
			if (weizhi != null && !"".equals(weizhi)) {
				gongyiGuichengAffixTemp.setWeizhi(weizhi);
			}
			boolean result = totalDao.update(gongyiGuichengAffixTemp);
			if (result) {
				return gongyiGuichengAffixTemp;
			} else {
				return null;
			}
		}
		return null;
	}

	/**
	 * 获得工艺规程附件
	 * 
	 * @param
	 * @return
	 */

	public GongyiGuichengAffix getGongyiGuichengAffixById(Integer id) {
		if (id != null) {
			return (GongyiGuichengAffix) totalDao.getObjectById(
					GongyiGuichengAffix.class, id);
		}
		return null;
	}

	/**
	 * 获得工艺规程附件集合
	 * 
	 * @param
	 * @return
	 */

	public List getGongyiGuichengAffixListByProcessDataId(
			Integer processDataId, String affixType) {
		String hql = "from GongyiGuichengAffix g where g.processDataId=? and g.affixType=?";
		List list = totalDao.find(hql,
				new Object[] { processDataId, affixType });
		return list;
	}

	/**
	 * 获得工艺规程附件集合 工艺规程ID工序ID
	 * 
	 * @param
	 * @return
	 */

	public List getGongyiGuichengAffixListByGuiyiGuichengIdAndProcessDataId(
			Integer gongyiGuichengId, Integer processDataId) {
		List list = null;
		if (processDataId == null) {
			String hql = "from GongyiGuichengAffix g where g.gongyiGuichengId=? and g.processDataId is null";
			list = totalDao.find(hql, new Object[] { gongyiGuichengId });
		} else {
			String hql = "from GongyiGuichengAffix g where g.gongyiGuichengId=? and g.processDataId=?";
			list = totalDao.find(hql, new Object[] { gongyiGuichengId,
					processDataId });
		}
		return list;
	}

	/**
	 * 获得工艺规程附件集合 for Select 工艺规程看板 工艺ID 工序ID 附件类型图片
	 * 
	 * @param gongyiGuichengAffix
	 * @return
	 */
	public List getGongyiGuichengAffixListForTupianForSelectByGongyiGuichengIdAndProcessDataId(
			GongyiGuichengAffix gongyiGuichengAffix) {
		List list = null;
		Integer gongyiGuichengId = gongyiGuichengAffix.getGongyiGuichengId();
		Integer processDataId = gongyiGuichengAffix.getProcessDataId();
		if (processDataId == null) {
			String hql = "from GongyiGuichengAffix g where g.gongyiGuichengId=? and g.processDataId is null and g.affixType='tupian'";
			list = totalDao.find(hql, new Object[] { gongyiGuichengId });
		} else {
			String hql = "from GongyiGuichengAffix g where g.gongyiGuichengId=? and g.processDataId=? and g.affixType='tupian'";
			list = totalDao.find(hql, new Object[] { gongyiGuichengId,
					processDataId });
		}
		return list;
	}

	/**
	 * 获得工艺规程附件集合 工艺规程ID工序ID
	 * 
	 * @param
	 * @return
	 */

	public List getGongyiGuichengAffixListByGongyiGuichengAffix(
			GongyiGuichengAffix gongyiGuichengAffix) {
		Integer gongyiGuichengId = gongyiGuichengAffix.getGongyiGuichengId();
		Integer processDataId = gongyiGuichengAffix.getProcessDataId();
		String affixType = gongyiGuichengAffix.getAffixType();
		String weizhi = gongyiGuichengAffix.getWeizhi();
		List list = null;
		if (processDataId == null) {
			String hql = "from GongyiGuichengAffix g where g.gongyiGuichengId=? and g.processDataId is null and g.affixType=? and g.weizhi=?";
			list = totalDao.find(hql, new Object[] { gongyiGuichengId,
					affixType, weizhi });
		} else {
			String hql = "from GongyiGuichengAffix g where g.gongyiGuichengId=? and g.processDataId=? and g.affixType=? and g.weizhi=?";
			list = totalDao.find(hql, new Object[] { gongyiGuichengId,
					processDataId, affixType, weizhi });
		}
		return list;
	}

	/**************************************** 工装设备 **************************************************/
	/**
	 * 获得设备集合
	 * 
	 * @return
	 */
	public List getMachineList() {
		String hql = "select new map(m.id as id,m.no as no,m.name as name) from Machine m order by m.name";
		List list = this.totalDao.query(hql);
		return list;
	}

	/**
	 * 获得工装集合
	 * 
	 * @return
	 */
	public List getGzstoreList() {
		String hql = "select new map(g.id as id,g.number as no,g.matetag as name)from Gzstore g order by g.matetag";
		List list = this.totalDao.find(hql);
		return list;
	}

	/**
	 * 查询附件集合 根据工艺规程ID
	 * 
	 * @param gongyiGuichengId
	 * @return
	 */
	public List getGongyiGuichengAffixListByGongyiGuichengId(
			Integer gongyiGuichengId) {
		String hql = "from GongyiGuichengAffix g where g.gongyiGuichengId=?";
		List list = this.totalDao.find(hql, new Object[] { gongyiGuichengId });
		return list;
	}

	/***************************************** dao *****************************************************/
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
