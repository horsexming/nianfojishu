package com.task.ServerImpl.gongyi.score;

import java.util.Date;
import java.util.List;
import com.task.Dao.TotalDao;
import com.task.Server.gongyi.score.GongyiGuichengScoreServer;
import com.task.entity.gongyi.GongyiGuicheng;
import com.task.entity.gongyi.gongxu.ProcessData;
import com.task.entity.gongyi.score.GongyiGuichengScore;
import com.task.entity.gongyi.score.GongyiGuichengScoreItem;
import com.task.entity.gongyi.score.GongyiGuichengScoreLeibie;
import com.task.entity.sop.fhyp.FanghuYongpin;

public class GongyiGuichengScoreServerImpl implements GongyiGuichengScoreServer {
	private TotalDao totalDao;
	
	/**
	 * 添加工艺规程打分记录
	 * @param GongyiGuichengScore
	 * @return
	 */
	public String addGongyiGuichengScore(GongyiGuichengScore gongyiGuichengScore) {
		boolean result= totalDao.save(gongyiGuichengScore);
		if(result){
			return "true";
		}else {
			return "false";
		}
	}

	/**
	 * 删除工艺规程打分记录
	 * @param GongyiGuichengScore
	 * @return
	 */
	public String deleteGongyiGuichengScore(GongyiGuichengScore gongyiGuichengScore) {
		boolean result= totalDao.delete(gongyiGuichengScore);
		if(result){
			return "true";
		}else {
			return "false";
		}
	}

	/**
	 * 更新工艺规程记录
	 * @param GongyiGuichengScore
	 * @return
	 */
	public String updateGongyiGuichengScore(GongyiGuichengScore gongyiGuichengScore) {
		GongyiGuichengScore gongyiGuichengScoreTemp=this.getGongyiGuichengScoreByGongyiGuichengIdAndModelAndProcessDataId(gongyiGuichengScore.getGongyiGuichengId(), gongyiGuichengScore.getModel(), gongyiGuichengScore.getProcessDataId());
		boolean result=false;
		if(gongyiGuichengScoreTemp!=null){
			//型别栏1
			Integer xingbieScoreGygc=gongyiGuichengScore.getXingbieScoreGygc();
			String xingbieScoreGygcRemark=gongyiGuichengScore.getXingbieScoreGygcRemark();
			//件号栏2
			Integer jianNumbScoreGygc=gongyiGuichengScore.getJianNumbScoreGygc();
			String jianNumbScoreGygcRemark=gongyiGuichengScore.getJianNumbScoreGygcRemark();
			//件名栏3
			Integer jianNameScoreGygc=gongyiGuichengScore.getJianNameScoreGygc();
			String jianNameScoreGygcRemark=gongyiGuichengScore.getJianNameScoreGygcRemark();
			//页码栏4
			Integer pageTotalScoreGygc=gongyiGuichengScore.getPageTotalScoreGygc();
			String pageTotalScoreGygcRemark=gongyiGuichengScore.getPageTotalScoreGygcRemark();
			//工艺规程编号栏5
			Integer numbScoreGygc=gongyiGuichengScore.getNumbScoreGygc();
			String numbScoreGygcRemark=gongyiGuichengScore.getNumbScoreGygcRemark();
			//版次栏6
			Integer banciScoreGygc=gongyiGuichengScore.getBanciScoreGygc();
			String banciScoreGygcRemark=gongyiGuichengScore.getBanciScoreGygcRemark();
			//型别栏7
			Integer xingbieScoreGycxtb=gongyiGuichengScore.getXingbieScoreGycxtb();
			String xingbieScoreGycxtbRemark=gongyiGuichengScore.getXingbieScoreGycxtbRemark();
			//件号栏8
			Integer jianNumbScoreGycxtb=gongyiGuichengScore.getJianNumbScoreGycxtb();
			String jianNumbScoreGycxtbRemark=gongyiGuichengScore.getJianNumbScoreGycxtbRemark();
			//件名栏9
			Integer jianNameScoreGycxtb=gongyiGuichengScore.getJianNameScoreGycxtb();
			String jianNameScoreGycxtbRemark=gongyiGuichengScore.getJianNameScoreGycxtbRemark();
			//页码栏10
			Integer pageTotalScoreGycxtb=gongyiGuichengScore.getPageTotalScoreGycxtb();
			String pageTotalScoreGycxtbRemark=gongyiGuichengScore.getPageTotalScoreGycxtbRemark();
			
			//检查内容11
			Integer contentScoreGxtblmfqmx=gongyiGuichengScore.getContentScoreGxtblmfqmx();
			String contentScoreGxtblmfqmxRemark=gongyiGuichengScore.getContentScoreGxtblmfqmxRemark();
			//设备栏12
			Integer shebeiScoreGxsmlq=gongyiGuichengScore.getShebeiScoreGxsmlq();
			String shebeiScoreGxsmlqRemark=gongyiGuichengScore.getShebeiScoreGxsmlqRemark();
			//工序名称13
			Integer gongxuNameScoreGxsmlq=gongyiGuichengScore.getGongxuNameScoreGxsmlq();
			String gongxuNameScoreGxsmlqRemark=gongyiGuichengScore.getGongxuNameScoreGxsmlqRemark();
			//工序号14
			Integer gongxuNumbScoreGxsmlq=gongyiGuichengScore.getGongxuNumbScoreGxsmlq();
			String gongxuNumbScoreGxsmlqRemark=gongyiGuichengScore.getGongxuNumbScoreGxsmlqRemark();
			//工装15
			Integer toolsScoreGxsmlq=gongyiGuichengScore.getToolsScoreGxsmlq();
			String toolsScoreGxsmlqRemark=gongyiGuichengScore.getToolsScoreGxsmlqRemark();
			//页码16
			Integer pageNumbScoreGxsmlq=gongyiGuichengScore.getPageNumbScoreGxsmlq();
			String pageNumbScoreGxsmlqRemark=gongyiGuichengScore.getPageNumbScoreGxsmlqRemark();
			//尺寸标注没有遗漏17
			Integer chicunBiaozhuYilouScoreGxsmlq=gongyiGuichengScore.getChicunBiaozhuYilouScoreGxsmlq();
			String chicunBiaozhuYilouScoreGxsmlqRemark=gongyiGuichengScore.getChicunBiaozhuYilouScoreGxsmlqRemark();
			//尺寸标注不会产生观察误导18
			Integer chicunBiaozhuWudaoScoreGxsmlq=gongyiGuichengScore.getChicunBiaozhuWudaoScoreGxsmlq();
			String chicunBiaozhuWudaoScoreGxsmlqRemark=gongyiGuichengScore.getChicunBiaozhuWudaoScoreGxsmlqRemark();
			//尺寸标注符合公司标准19
			Integer chicunBiaozhuBiaozhunScoreGxsmlq=gongyiGuichengScore.getChicunBiaozhuBiaozhunScoreGxsmlq();
			String chicunBiaozhuBiaozhunScoreGxsmlqRemark=gongyiGuichengScore.getChicunBiaozhuBiaozhunScoreGxsmlqRemark();
			//图形符合国标制图标准20
			Integer tuxingBiaozhunScoreGxsmlq=gongyiGuichengScore.getTuxingBiaozhunScoreGxsmlq();
			String tuxingBiaozhunScoreGxsmlqRemark=gongyiGuichengScore.getTuxingBiaozhunScoreGxsmlqRemark();
			//轴测图或图片显示示意21
			Integer zhucetuShiyiScoreGxsmlq=gongyiGuichengScore.getZhucetuShiyiScoreGxsmlq();
			String zhucetuShiyiScoreGxsmlqRemark=gongyiGuichengScore.getZhucetuShiyiScoreGxsmlqRemark();
			//图片或轴测图表示准确22
			Integer zhucetuBiaozhunScoreGxsmlq=gongyiGuichengScore.getZhucetuBiaozhunScoreGxsmlq();
			String zhucetuBiaozhunScoreGxsmlqRemark=gongyiGuichengScore.getZhucetuBiaozhunScoreGxsmlqRemark();
			//项目栏23
			Integer xiangmuScoreGxsmlq=gongyiGuichengScore.getXiangmuScoreGxsmlq();
			String xiangmuScoreGxsmlqRemark=gongyiGuichengScore.getXiangmuScoreGxsmlqRemark();
			//作业规范栏24
			Integer zuoyeGuifanScoreGxsmlq=gongyiGuichengScore.getZuoyeGuifanScoreGxsmlq();
			String zuoyeGuifanScoreGxsmlqRemark=gongyiGuichengScore.getZuoyeGuifanScoreGxsmlqRemark();
			//技术参数栏25
			Integer jishuCanshuScoreGxsmlq=gongyiGuichengScore.getJishuCanshuScoreGxsmlq();
			String jishuCanshuScoreGxsmlqRemark=gongyiGuichengScore.getJishuCanshuScoreGxsmlqRemark();
			//索引号26
			Integer suoyinScoreGxsmlq=gongyiGuichengScore.getSuoyinScoreGxsmlq();
			String suoyinScoreGxsmlqRemark=gongyiGuichengScore.getSuoyinScoreGxsmlqRemark();
			//更改单号27
			Integer danNumbScoreGxsmlq=gongyiGuichengScore.getDanNumbScoreGxsmlq();
			String danNumbScoreGxsmlqRemark=gongyiGuichengScore.getDanNumbScoreGxsmlqRemark();
			//签名28
			Integer qianmingScoreGxsmlq=gongyiGuichengScore.getQianmingScoreGxsmlq();
			String qianmingScoreGxsmlqRemark=gongyiGuichengScore.getQianmingScoreGxsmlqRemark();
			//序号栏29
			Integer xuhaoScoreJyxmlq=gongyiGuichengScore.getXuhaoScoreJyxmlq();
			String xuhaoScoreJyxmlqRemark=gongyiGuichengScore.getXuhaoScoreJyxmlqRemark();
			//检验项目栏30
			Integer xiangmuScoreJyxmlq=gongyiGuichengScore.getXiangmuScoreJyxmlq();
			String xiangmuScoreJyxmlqRemark=gongyiGuichengScore.getXiangmuScoreJyxmlqRemark();
			//测量器具31
			Integer qijuScoreJyxmlq=gongyiGuichengScore.getQijuScoreJyxmlq();
			String qijuScoreJyxmlqRemark=gongyiGuichengScore.getQijuScoreJyxmlqRemark();
			//测定频率32
			Integer cedingPinlvScoreJyxmlq=gongyiGuichengScore.getCedingPinlvScoreJyxmlq();
			String cedingPinlvScoreJyxmlqRemark=gongyiGuichengScore.getCedingPinlvScoreJyxmlqRemark();
			//辅助栏33
			Integer fuzhuScoreJyxmlq=gongyiGuichengScore.getFuzhuScoreJyxmlq();
			String fuzhuScoreJyxmlqRemark=gongyiGuichengScore.getFuzhuScoreJyxmlqRemark();
			
			
			int score=0;
			//型别栏1
			if(xingbieScoreGygc!=null){
				score+=xingbieScoreGygc;
				gongyiGuichengScoreTemp.setXingbieScoreGygc(xingbieScoreGygc);
			}
			if(xingbieScoreGygcRemark!=null && !"".equals(xingbieScoreGygcRemark)){
				gongyiGuichengScoreTemp.setXingbieScoreGygcRemark(xingbieScoreGygcRemark);
			}
			//件号栏2
			if(jianNumbScoreGygc!=null){
				score+=jianNumbScoreGygc;
				gongyiGuichengScoreTemp.setJianNumbScoreGygc(jianNumbScoreGygc);
			}
			if(jianNumbScoreGygcRemark!=null && !"".equals(jianNumbScoreGygcRemark)){
				gongyiGuichengScoreTemp.setJianNumbScoreGygcRemark(jianNumbScoreGygcRemark);
			}
			//件名栏3
			if(jianNameScoreGygc!=null){
				score+=jianNameScoreGygc;
				gongyiGuichengScoreTemp.setJianNameScoreGygc(jianNameScoreGygc);
			}
			if(jianNameScoreGygcRemark!=null && !"".equals(jianNameScoreGygcRemark)){
				gongyiGuichengScoreTemp.setJianNameScoreGygcRemark(jianNameScoreGygcRemark);
			}
			//页码栏4
			if(pageTotalScoreGygc!=null){
				score+=pageTotalScoreGygc;
				gongyiGuichengScoreTemp.setPageTotalScoreGygc(pageTotalScoreGygc);
			}
			if(pageTotalScoreGygcRemark!=null && !"".equals(pageTotalScoreGygcRemark)){
				gongyiGuichengScoreTemp.setPageTotalScoreGygcRemark(pageTotalScoreGygcRemark);
			}
			//工艺规程编号栏5
			if(numbScoreGygc!=null){
				score+=numbScoreGygc;
				gongyiGuichengScoreTemp.setNumbScoreGygc(numbScoreGygc);
			}
			if(numbScoreGygcRemark!=null && !"".equals(numbScoreGygcRemark)){
				gongyiGuichengScoreTemp.setNumbScoreGygcRemark(numbScoreGygcRemark);
			}
			//版次栏6
			if(banciScoreGygc!=null){
				score+=banciScoreGygc;
				gongyiGuichengScoreTemp.setBanciScoreGygc(banciScoreGygc);
			}
			if(banciScoreGygcRemark!=null && !"".equals(banciScoreGygcRemark)){
				gongyiGuichengScoreTemp.setBanciScoreGygcRemark(banciScoreGygcRemark);
			}
			
			//型别栏7
			if(xingbieScoreGycxtb!=null){
				score+=xingbieScoreGycxtb;
				gongyiGuichengScoreTemp.setXingbieScoreGycxtb(xingbieScoreGycxtb);
			}
			if(xingbieScoreGycxtbRemark!=null && !"".equals(xingbieScoreGycxtbRemark)){
				gongyiGuichengScoreTemp.setXingbieScoreGycxtbRemark(xingbieScoreGycxtbRemark);
			}
			//件号栏8
			if(jianNumbScoreGycxtb!=null){
				score+=jianNumbScoreGycxtb;
				gongyiGuichengScoreTemp.setJianNumbScoreGycxtb(jianNumbScoreGycxtb);
			}
			if(jianNumbScoreGycxtbRemark!=null && !"".equals(jianNumbScoreGycxtbRemark)){
				gongyiGuichengScoreTemp.setJianNumbScoreGycxtbRemark(jianNumbScoreGycxtbRemark);
			}
			//件名栏9
			if(jianNameScoreGycxtb!=null){
				score+=jianNameScoreGycxtb;
				gongyiGuichengScoreTemp.setJianNameScoreGycxtb(jianNameScoreGycxtb);
			}
			if(jianNameScoreGycxtbRemark!=null && !"".equals(jianNameScoreGycxtbRemark)){
				gongyiGuichengScoreTemp.setJianNameScoreGycxtbRemark(jianNameScoreGycxtbRemark);
			}
			//页码栏10
			if(pageTotalScoreGycxtb!=null){
				score+=pageTotalScoreGycxtb;
				gongyiGuichengScoreTemp.setPageTotalScoreGycxtb(pageTotalScoreGycxtb);
			}
			if(pageTotalScoreGycxtbRemark!=null && !"".equals(pageTotalScoreGycxtbRemark)){
				gongyiGuichengScoreTemp.setPageTotalScoreGycxtbRemark(pageTotalScoreGycxtbRemark);
			}
			//检查内容11
			if(contentScoreGxtblmfqmx!=null){
				score+=contentScoreGxtblmfqmx;
				gongyiGuichengScoreTemp.setContentScoreGxtblmfqmx(contentScoreGxtblmfqmx);
			}
			if(contentScoreGxtblmfqmxRemark!=null && !"".equals(contentScoreGxtblmfqmxRemark)){
				gongyiGuichengScoreTemp.setContentScoreGxtblmfqmxRemark(contentScoreGxtblmfqmxRemark);
			}
			//设备栏12
			if(shebeiScoreGxsmlq!=null){
				score+=shebeiScoreGxsmlq;
				gongyiGuichengScoreTemp.setShebeiScoreGxsmlq(shebeiScoreGxsmlq);
			}
			if(shebeiScoreGxsmlqRemark!=null && !"".equals(shebeiScoreGxsmlqRemark)){
				gongyiGuichengScoreTemp.setShebeiScoreGxsmlqRemark(shebeiScoreGxsmlqRemark);
			}
			//工序名称13
			if(gongxuNameScoreGxsmlq!=null){
				score+=gongxuNameScoreGxsmlq;
				gongyiGuichengScoreTemp.setGongxuNameScoreGxsmlq(gongxuNameScoreGxsmlq);
			}
			if(gongxuNameScoreGxsmlqRemark!=null && !"".equals(gongxuNameScoreGxsmlqRemark)){
				gongyiGuichengScoreTemp.setGongxuNameScoreGxsmlqRemark(gongxuNameScoreGxsmlqRemark);
			}
			//工序号14
			if(gongxuNumbScoreGxsmlq!=null){
				score+=gongxuNumbScoreGxsmlq;
				gongyiGuichengScoreTemp.setGongxuNumbScoreGxsmlq(gongxuNumbScoreGxsmlq);
			}
			if(gongxuNumbScoreGxsmlqRemark!=null && !"".equals(gongxuNumbScoreGxsmlqRemark)){
				gongyiGuichengScoreTemp.setGongxuNumbScoreGxsmlqRemark(gongxuNumbScoreGxsmlqRemark);
			}
			//工装15
			if(toolsScoreGxsmlq!=null){
				score+=toolsScoreGxsmlq;
				gongyiGuichengScoreTemp.setToolsScoreGxsmlq(toolsScoreGxsmlq);
			}
			if(toolsScoreGxsmlqRemark!=null && !"".equals(toolsScoreGxsmlqRemark)){
				gongyiGuichengScoreTemp.setToolsScoreGxsmlqRemark(toolsScoreGxsmlqRemark);
			}
			//页码16
			if(pageNumbScoreGxsmlq!=null){
				score+=pageNumbScoreGxsmlq;
				gongyiGuichengScoreTemp.setPageNumbScoreGxsmlq(pageNumbScoreGxsmlq);
			}
			if(pageNumbScoreGxsmlqRemark!=null && !"".equals(pageNumbScoreGxsmlqRemark)){
				gongyiGuichengScoreTemp.setPageNumbScoreGxsmlqRemark(pageNumbScoreGxsmlqRemark);
			}
			//尺寸标注没有遗漏17
			if(chicunBiaozhuYilouScoreGxsmlq!=null){
				score+=chicunBiaozhuYilouScoreGxsmlq;
				gongyiGuichengScoreTemp.setChicunBiaozhuYilouScoreGxsmlq(chicunBiaozhuYilouScoreGxsmlq);
			}
			if(chicunBiaozhuYilouScoreGxsmlqRemark!=null && !"".equals(chicunBiaozhuYilouScoreGxsmlqRemark)){
				gongyiGuichengScoreTemp.setChicunBiaozhuYilouScoreGxsmlqRemark(chicunBiaozhuYilouScoreGxsmlqRemark);
			}
			//尺寸标注不会产生观察误导18
			if(chicunBiaozhuWudaoScoreGxsmlq!=null){
				score+=chicunBiaozhuWudaoScoreGxsmlq;
				gongyiGuichengScoreTemp.setChicunBiaozhuWudaoScoreGxsmlq(chicunBiaozhuWudaoScoreGxsmlq);
			}
			if(chicunBiaozhuWudaoScoreGxsmlqRemark!=null && !"".equals(chicunBiaozhuWudaoScoreGxsmlqRemark)){
				gongyiGuichengScoreTemp.setChicunBiaozhuWudaoScoreGxsmlqRemark(chicunBiaozhuWudaoScoreGxsmlqRemark);
			}
			//尺寸标注符合公司标准19
			if(chicunBiaozhuBiaozhunScoreGxsmlq!=null){
				score+=chicunBiaozhuBiaozhunScoreGxsmlq;
				gongyiGuichengScoreTemp.setChicunBiaozhuBiaozhunScoreGxsmlq(chicunBiaozhuBiaozhunScoreGxsmlq);
			}
			if(chicunBiaozhuBiaozhunScoreGxsmlqRemark!=null && !"".equals(chicunBiaozhuBiaozhunScoreGxsmlqRemark)){
				gongyiGuichengScoreTemp.setChicunBiaozhuBiaozhunScoreGxsmlqRemark(chicunBiaozhuBiaozhunScoreGxsmlqRemark);
			}
			//图形符合国标制图标准20
			if(tuxingBiaozhunScoreGxsmlq!=null){
				score+=tuxingBiaozhunScoreGxsmlq;
				gongyiGuichengScoreTemp.setTuxingBiaozhunScoreGxsmlq(tuxingBiaozhunScoreGxsmlq);
			}
			if(tuxingBiaozhunScoreGxsmlqRemark!=null && !"".equals(tuxingBiaozhunScoreGxsmlqRemark)){
				gongyiGuichengScoreTemp.setTuxingBiaozhunScoreGxsmlqRemark(tuxingBiaozhunScoreGxsmlqRemark);
			}
			//轴测图或图片显示示意21
			if(zhucetuShiyiScoreGxsmlq!=null){
				score+=zhucetuShiyiScoreGxsmlq;
				gongyiGuichengScoreTemp.setZhucetuShiyiScoreGxsmlq(zhucetuShiyiScoreGxsmlq);
			}
			if(zhucetuShiyiScoreGxsmlqRemark!=null && !"".equals(zhucetuShiyiScoreGxsmlqRemark)){
				gongyiGuichengScoreTemp.setZhucetuShiyiScoreGxsmlqRemark(zhucetuShiyiScoreGxsmlqRemark);
			}
			//图片或轴测图表示准确22
			if(zhucetuBiaozhunScoreGxsmlq!=null){
				score+=zhucetuBiaozhunScoreGxsmlq;
				gongyiGuichengScoreTemp.setZhucetuBiaozhunScoreGxsmlq(zhucetuBiaozhunScoreGxsmlq);
			}
			if(zhucetuBiaozhunScoreGxsmlqRemark!=null && !"".equals(zhucetuBiaozhunScoreGxsmlqRemark)){
				gongyiGuichengScoreTemp.setZhucetuBiaozhunScoreGxsmlqRemark(zhucetuBiaozhunScoreGxsmlqRemark);
			}
			//项目栏23
			if(xiangmuScoreGxsmlq!=null){
				score+=xiangmuScoreGxsmlq;
				gongyiGuichengScoreTemp.setXiangmuScoreGxsmlq(xiangmuScoreGxsmlq);
			}
			if(xiangmuScoreGxsmlqRemark!=null && !"".equals(xiangmuScoreGxsmlqRemark)){
				gongyiGuichengScoreTemp.setXiangmuScoreGxsmlqRemark(xiangmuScoreGxsmlqRemark);
			}
			//作业规范栏24
			if(zuoyeGuifanScoreGxsmlq!=null){
				score+=zuoyeGuifanScoreGxsmlq;
				gongyiGuichengScoreTemp.setZuoyeGuifanScoreGxsmlq(zuoyeGuifanScoreGxsmlq);
			}
			if(zuoyeGuifanScoreGxsmlqRemark!=null && !"".equals(zuoyeGuifanScoreGxsmlqRemark)){
				gongyiGuichengScoreTemp.setZuoyeGuifanScoreGxsmlqRemark(zuoyeGuifanScoreGxsmlqRemark);
			}
			//技术参数栏25
			if(jishuCanshuScoreGxsmlq!=null){
				score+=jishuCanshuScoreGxsmlq;
				gongyiGuichengScoreTemp.setJishuCanshuScoreGxsmlq(jishuCanshuScoreGxsmlq);
			}
			if(jishuCanshuScoreGxsmlqRemark!=null && !"".equals(jishuCanshuScoreGxsmlqRemark)){
				gongyiGuichengScoreTemp.setJishuCanshuScoreGxsmlqRemark(jishuCanshuScoreGxsmlqRemark);
			}
			//索引号26
			if(suoyinScoreGxsmlq!=null){
				score+=suoyinScoreGxsmlq;
				gongyiGuichengScoreTemp.setSuoyinScoreGxsmlq(suoyinScoreGxsmlq);
			}
			if(suoyinScoreGxsmlqRemark!=null && !"".equals(suoyinScoreGxsmlqRemark)){
				gongyiGuichengScoreTemp.setSuoyinScoreGxsmlqRemark(suoyinScoreGxsmlqRemark);
			}
			//更改单号27
			if(danNumbScoreGxsmlq!=null){
				score+=danNumbScoreGxsmlq;
				gongyiGuichengScoreTemp.setDanNumbScoreGxsmlq(danNumbScoreGxsmlq);
			}
			if(danNumbScoreGxsmlqRemark!=null && !"".equals(danNumbScoreGxsmlqRemark)){
				gongyiGuichengScoreTemp.setDanNumbScoreGxsmlqRemark(danNumbScoreGxsmlqRemark);
			}
			//签名28
			if(qianmingScoreGxsmlq!=null){
				score+=qianmingScoreGxsmlq;
				gongyiGuichengScoreTemp.setQianmingScoreGxsmlq(qianmingScoreGxsmlq);
			}
			if(qianmingScoreGxsmlqRemark!=null && !"".equals(qianmingScoreGxsmlqRemark)){
				gongyiGuichengScoreTemp.setQianmingScoreGxsmlqRemark(qianmingScoreGxsmlqRemark);
			}
			//序号栏29
			if(xuhaoScoreJyxmlq!=null){
				score+=xuhaoScoreJyxmlq;
				gongyiGuichengScoreTemp.setXuhaoScoreJyxmlq(xuhaoScoreJyxmlq);
			}
			if(xuhaoScoreJyxmlqRemark!=null && !"".equals(xuhaoScoreJyxmlqRemark)){
				gongyiGuichengScoreTemp.setXuhaoScoreJyxmlqRemark(xuhaoScoreJyxmlqRemark);
			}
			//检验项目栏30
			if(xiangmuScoreJyxmlq!=null){
				score+=xiangmuScoreJyxmlq;
				gongyiGuichengScoreTemp.setXiangmuScoreJyxmlq(xiangmuScoreJyxmlq);
			}
			if(xiangmuScoreJyxmlqRemark!=null && !"".equals(xiangmuScoreJyxmlqRemark)){
				gongyiGuichengScoreTemp.setXiangmuScoreJyxmlqRemark(xiangmuScoreJyxmlqRemark);
			}
			//测量器具31
			if(qijuScoreJyxmlq!=null){
				score+=qijuScoreJyxmlq;
				gongyiGuichengScoreTemp.setQijuScoreJyxmlq(qijuScoreJyxmlq);
			}
			if(qijuScoreJyxmlqRemark!=null && !"".equals(qijuScoreJyxmlqRemark)){
				gongyiGuichengScoreTemp.setQijuScoreJyxmlqRemark(qijuScoreJyxmlqRemark);
			}
			//测定频率32
			if(cedingPinlvScoreJyxmlq!=null){
				score+=cedingPinlvScoreJyxmlq;
				gongyiGuichengScoreTemp.setCedingPinlvScoreJyxmlq(cedingPinlvScoreJyxmlq);
			}
			if(cedingPinlvScoreJyxmlqRemark!=null && !"".equals(cedingPinlvScoreJyxmlqRemark)){
				gongyiGuichengScoreTemp.setCedingPinlvScoreJyxmlqRemark(cedingPinlvScoreJyxmlqRemark);
			}
			//辅助栏33
			if(fuzhuScoreJyxmlq!=null){
				score+=fuzhuScoreJyxmlq;
				gongyiGuichengScoreTemp.setFuzhuScoreJyxmlq(fuzhuScoreJyxmlq);
			}
			if(fuzhuScoreJyxmlqRemark!=null && !"".equals(fuzhuScoreJyxmlqRemark)){
				gongyiGuichengScoreTemp.setFuzhuScoreJyxmlqRemark(fuzhuScoreJyxmlqRemark);
			}
			gongyiGuichengScoreTemp.setScore(score);
			
			result= totalDao.update(gongyiGuichengScoreTemp);
			//Integer score;
			//更新总的分数
			//List processDataList=this.getProcessDataList(gongyiGuichengScore.getGongyiGuichengId());
			//int gygcScore=12;
			//int gycxtbScore=8;
			//int gxtblmfqmx=45;
			//int gxsmlqScore=34;
			//int tukuangScore=32;
			//int zuoyeguifanScore=10;
			//int jyxmlqScore=10;
			//int genggailanmuquScore=6;//142
			//int totalScore=gygcScore+gycxtbScore+gxtblmfqmx+gxsmlqScore*processDataList.size()+jyxmqlScore;
			//int score=totalScore;
			//总分75
			//this.updateScore(gongyiGuichengScore.getGongyiGuichengId(), gongyiGuichengScore.getModel(), gongyiGuichengScore.getProcessDataId());
			
		}
		
		if(result){
			return "true";
		}else {
			return "false";
		}
	}
	
	/**
	 * 获得工艺规程打分记录记录 根据工艺规程ID  模块  工序数据ID
	 * @param id
	 * @return
	 */
	public GongyiGuichengScore getGongyiGuichengScoreByGongyiGuichengIdAndModelAndProcessDataId(Integer gongyiGuichengId,String model,Integer processDataId) {
		String hql="";
		/*
		if("gygc".equals(model)){
			hql="from GongyiGuichengScore g where g.gongyiGuichengId=? and g.model=?";
			return (GongyiGuichengScore)this.totalDao.getObjectByCondition(hql, new Object[]{gongyiGuichengId, model});
		}else if("gycxtb".equals(model)){
			hql="from GongyiGuichengScore g where g.gongyiGuichengId=? and g.model=?";
			return (GongyiGuichengScore)this.totalDao.getObjectByCondition(hql, new Object[]{gongyiGuichengId, model});
		}else if("gxtblmfqmx".equals(model)){
			hql="from GongyiGuichengScore g where g.gongyiGuichengId=? and g.model=? and g.processDataId=?";
			return (GongyiGuichengScore)this.totalDao.getObjectByCondition(hql, new Object[]{gongyiGuichengId, model, processDataId});
		}else if("gxsmlq".equals(model)){
			hql="from GongyiGuichengScore g where g.gongyiGuichengId=? and g.model=? and g.processDataId=?";
			return (GongyiGuichengScore)this.totalDao.getObjectByCondition(hql, new Object[]{gongyiGuichengId, model, processDataId});
		}else if("jyxmlq".equals(model)){
			hql="from GongyiGuichengScore g where g.gongyiGuichengId=? and g.model=? and g.processDataId=?";
			return (GongyiGuichengScore)this.totalDao.getObjectByCondition(hql, new Object[]{gongyiGuichengId, model, processDataId});
		}else {
			return null;
		}*/
		if(gongyiGuichengId!=null && processDataId!=null){
			hql="from GongyiGuichengScore g where g.gongyiGuichengId=? and g.processDataId=?";
			return (GongyiGuichengScore)this.totalDao.getObjectByCondition(hql, new Object[]{gongyiGuichengId,processDataId});
		}else{
			hql="from GongyiGuichengScore g where g.gongyiGuichengId=?";
			return (GongyiGuichengScore)this.totalDao.getObjectByCondition(hql, new Object[]{gongyiGuichengId});
		}
	}
	
	private List getProcessDataList(Integer gongyiGuichengId){
		String hql="from ProcessData p where p.gongyiGuichengId=?";
		List list=this.totalDao.find(hql, new Object[]{gongyiGuichengId});
		return list;
	}
	private List getGongyiGuichengScoreByGongyiGuichengId(Integer gongyiGuichengId) {
		String hql="from GongyiGuichengScore g where g.gongyiGuichengId=?";
		List list=this.totalDao.find(hql, new Object[]{gongyiGuichengId});
		return list;
	}
	private void updateScore(Integer gongyiGuichengId, String model, Integer processDataId ){
		GongyiGuichengScore gongyiGuichengScore=this.getGongyiGuichengScoreByGongyiGuichengIdAndModelAndProcessDataId(gongyiGuichengId,model,processDataId);
		//型别栏1
		Integer xingbieScoreGygc=gongyiGuichengScore.getXingbieScoreGygc();
		//件号栏2
		Integer jianNumbScoreGygc=gongyiGuichengScore.getJianNumbScoreGygc();
		//件名栏3
		Integer jianNameScoreGygc=gongyiGuichengScore.getJianNameScoreGygc();
		//页码栏4
		Integer pageTotalScoreGygc=gongyiGuichengScore.getPageTotalScoreGygc();
		//工艺规程编号栏5
		Integer numbScoreGygc=gongyiGuichengScore.getNumbScoreGygc();
		//版次栏6
		Integer banciScoreGygc=gongyiGuichengScore.getBanciScoreGygc();
		
		//型别栏7
		Integer xingbieScoreGycxtb=gongyiGuichengScore.getXingbieScoreGycxtb();
		//件号栏8
		Integer jianNumbScoreGycxtb=gongyiGuichengScore.getJianNumbScoreGycxtb();
		//件名栏9
		Integer jianNameScoreGycxtb=gongyiGuichengScore.getJianNameScoreGycxtb();
		//页码栏10
		Integer pageTotalScoreGycxtb=gongyiGuichengScore.getPageTotalScoreGycxtb();
		
		//检查内容11
		Integer contentScoreGxtblmfqmx=gongyiGuichengScore.getContentScoreGxtblmfqmx();
		
		//设备栏12
		Integer shebeiScoreGxsmlq=gongyiGuichengScore.getShebeiScoreGxsmlq();
		//工序名称13
		Integer gongxuNameScoreGxsmlq=gongyiGuichengScore.getGongxuNameScoreGxsmlq();
		//工序号14
		Integer gongxuNumbScoreGxsmlq=gongyiGuichengScore.getGongxuNumbScoreGxsmlq();
		//工装15
		Integer toolsScoreGxsmlq=gongyiGuichengScore.getToolsScoreGxsmlq();
		//页码16
		Integer pageNumbScoreGxsmlq=gongyiGuichengScore.getPageNumbScoreGxsmlq();
		//尺寸标注没有遗漏17
		Integer chicunBiaozhuYilouScoreGxsmlq=gongyiGuichengScore.getChicunBiaozhuYilouScoreGxsmlq();
		//尺寸标注不会产生观察误导18
		Integer chicunBiaozhuWudaoScoreGxsmlq=gongyiGuichengScore.getChicunBiaozhuWudaoScoreGxsmlq();
		//尺寸标注符合公司标准19
		Integer chicunBiaozhuBiaozhunScoreGxsmlq=gongyiGuichengScore.getChicunBiaozhuBiaozhunScoreGxsmlq();
		//图形符合国标制图标准20
		Integer tuxingBiaozhunScoreGxsmlq=gongyiGuichengScore.getTuxingBiaozhunScoreGxsmlq();
		//轴测图或图片显示示意21
		Integer zhucetuShiyiScoreGxsmlq=gongyiGuichengScore.getZhucetuShiyiScoreGxsmlq();
		//图片或轴测图表示准确22
		Integer zhucetuBiaozhunScoreGxsmlq=gongyiGuichengScore.getZhucetuBiaozhunScoreGxsmlq();
		//项目栏23
		Integer xiangmuScoreGxsmlq=gongyiGuichengScore.getXiangmuScoreGxsmlq();
		//作业规范栏24
		Integer zuoyeGuifanScoreGxsmlq=gongyiGuichengScore.getZuoyeGuifanScoreGxsmlq();
		//技术参数栏25
		Integer jishuCanshuScoreGxsmlq=gongyiGuichengScore.getJishuCanshuScoreGxsmlq();
		//索引号26
		Integer suoyinScoreGxsmlq=gongyiGuichengScore.getSuoyinScoreGxsmlq();
		//更改单号27
		Integer danNumbScoreGxsmlq=gongyiGuichengScore.getDanNumbScoreGxsmlq();
		//签名28
		Integer qianmingScoreGxsmlq=gongyiGuichengScore.getQianmingScoreGxsmlq();
		
		//序号栏29
		Integer xuhaoScoreJyxmlq=gongyiGuichengScore.getXuhaoScoreJyxmlq();
		//检验项目栏30
		Integer xiangmuScoreJyxmlq=gongyiGuichengScore.getXiangmuScoreJyxmlq();
		//测量器具31
		Integer qijuScoreJyxmlq=gongyiGuichengScore.getQijuScoreJyxmlq();
		//测定频率32
		Integer cedingPinlvScoreJyxmlq=gongyiGuichengScore.getCedingPinlvScoreJyxmlq();
		//辅助栏33
		Integer fuzhuScoreJyxmlq=gongyiGuichengScore.getFuzhuScoreJyxmlq();
		int score=0;
		if("gygc".equals(model)){
			score=
			//型别栏1
			xingbieScoreGygc+
			//件号栏2
			jianNumbScoreGygc+
			//件名栏3
			jianNameScoreGygc+
			//页码栏4
			pageTotalScoreGygc+
			//工艺规程编号栏5
			numbScoreGygc+
			//版次栏6
			banciScoreGygc;
		}else if("gycxtb".equals(model)){
			//型别栏7
			score=xingbieScoreGycxtb+
			//件号栏8
			jianNumbScoreGycxtb+
			//件名栏9
			jianNameScoreGycxtb+
			//页码栏10
			pageTotalScoreGycxtb;
		}else if("gxtblmfqmx".equals(model)){
			//检查内容11
			score=contentScoreGxtblmfqmx;
		}else if("gxsmlq".equals(model)){
			//设备栏12
			score=shebeiScoreGxsmlq+
			//工序名称13
			gongxuNameScoreGxsmlq+
			//工序号14
			gongxuNumbScoreGxsmlq+
			//工装15
			toolsScoreGxsmlq+
			//页码16
			pageNumbScoreGxsmlq+
			//尺寸标注没有遗漏17
			chicunBiaozhuYilouScoreGxsmlq+
			//尺寸标注不会产生观察误导18
			chicunBiaozhuWudaoScoreGxsmlq+
			//尺寸标注符合公司标准19
			chicunBiaozhuBiaozhunScoreGxsmlq+
			//图形符合国标制图标准20
			tuxingBiaozhunScoreGxsmlq+
			//轴测图或图片显示示意21
			zhucetuShiyiScoreGxsmlq+
			//图片或轴测图表示准确22
			zhucetuBiaozhunScoreGxsmlq+
			//项目栏23
			xiangmuScoreGxsmlq+
			//作业规范栏24
			zuoyeGuifanScoreGxsmlq+
			//技术参数栏25
			jishuCanshuScoreGxsmlq+
			//索引号26
			suoyinScoreGxsmlq+
			//更改单号27
			danNumbScoreGxsmlq+
			//签名28
			qianmingScoreGxsmlq;
		}else if("jyxmlq".equals(model)){
			//序号栏29
			score=xuhaoScoreJyxmlq+
			//检验项目栏30
			xiangmuScoreJyxmlq+
			//测量器具31
			qijuScoreJyxmlq+
			//测定频率32
			cedingPinlvScoreJyxmlq+
			//辅助栏33
			fuzhuScoreJyxmlq;
		}
		gongyiGuichengScore.setScore(score);
		this.totalDao.update(gongyiGuichengScore);
		
		//过得工艺规程下所有的得分集合
		List<GongyiGuichengScore> gongyiGuichengScoreList=(List<GongyiGuichengScore>)this.getGongyiGuichengScoreByGongyiGuichengId(gongyiGuichengId);
		int totalScore=0;
		for(GongyiGuichengScore gongyiGuichengScoreTemp : gongyiGuichengScoreList){
			totalScore+=gongyiGuichengScoreTemp.getScore();
		}
		this.totalDao.createQueryUpdate("update GongyiGuicheng g set g.score=?", null, new Object[]{totalScore});
	}
	private int countGongyiGuichengScore(GongyiGuichengScore gongyiGuichengScore){
		//型别栏1
		Integer xingbieScoreGygc=gongyiGuichengScore.getXingbieScoreGygc();
		//件号栏2
		Integer jianNumbScoreGygc=gongyiGuichengScore.getJianNumbScoreGygc();
		//件名栏3
		Integer jianNameScoreGygc=gongyiGuichengScore.getJianNameScoreGygc();
		//页码栏4
		Integer pageTotalScoreGygc=gongyiGuichengScore.getPageTotalScoreGygc();
		//工艺规程编号栏5
		Integer numbScoreGygc=gongyiGuichengScore.getNumbScoreGygc();
		//版次栏6
		Integer banciScoreGygc=gongyiGuichengScore.getBanciScoreGygc();
		
		//型别栏7
		Integer xingbieScoreGycxtb=gongyiGuichengScore.getXingbieScoreGycxtb();
		//件号栏8
		Integer jianNumbScoreGycxtb=gongyiGuichengScore.getJianNumbScoreGycxtb();
		//件名栏9
		Integer jianNameScoreGycxtb=gongyiGuichengScore.getJianNameScoreGycxtb();
		//页码栏10
		Integer pageTotalScoreGycxtb=gongyiGuichengScore.getPageTotalScoreGycxtb();
		
		//检查内容11
		Integer contentScoreGxtblmfqmx=gongyiGuichengScore.getContentScoreGxtblmfqmx();
		
		//设备栏12
		Integer shebeiScoreGxsmlq=gongyiGuichengScore.getShebeiScoreGxsmlq();
		//工序名称13
		Integer gongxuNameScoreGxsmlq=gongyiGuichengScore.getGongxuNameScoreGxsmlq();
		//工序号14
		Integer gongxuNumbScoreGxsmlq=gongyiGuichengScore.getGongxuNumbScoreGxsmlq();
		//工装15
		Integer toolsScoreGxsmlq=gongyiGuichengScore.getToolsScoreGxsmlq();
		//页码16
		Integer pageNumbScoreGxsmlq=gongyiGuichengScore.getPageNumbScoreGxsmlq();
		//尺寸标注没有遗漏17
		Integer chicunBiaozhuYilouScoreGxsmlq=gongyiGuichengScore.getChicunBiaozhuYilouScoreGxsmlq();
		//尺寸标注不会产生观察误导18
		Integer chicunBiaozhuWudaoScoreGxsmlq=gongyiGuichengScore.getChicunBiaozhuWudaoScoreGxsmlq();
		//尺寸标注符合公司标准19
		Integer chicunBiaozhuBiaozhunScoreGxsmlq=gongyiGuichengScore.getChicunBiaozhuBiaozhunScoreGxsmlq();
		//图形符合国标制图标准20
		Integer tuxingBiaozhunScoreGxsmlq=gongyiGuichengScore.getTuxingBiaozhunScoreGxsmlq();
		//轴测图或图片显示示意21
		Integer zhucetuShiyiScoreGxsmlq=gongyiGuichengScore.getZhucetuShiyiScoreGxsmlq();
		//图片或轴测图表示准确22
		Integer zhucetuBiaozhunScoreGxsmlq=gongyiGuichengScore.getZhucetuBiaozhunScoreGxsmlq();
		//项目栏23
		Integer xiangmuScoreGxsmlq=gongyiGuichengScore.getXiangmuScoreGxsmlq();
		//作业规范栏24
		Integer zuoyeGuifanScoreGxsmlq=gongyiGuichengScore.getZuoyeGuifanScoreGxsmlq();
		//技术参数栏25
		Integer jishuCanshuScoreGxsmlq=gongyiGuichengScore.getJishuCanshuScoreGxsmlq();
		//索引号26
		Integer suoyinScoreGxsmlq=gongyiGuichengScore.getSuoyinScoreGxsmlq();
		//更改单号27
		Integer danNumbScoreGxsmlq=gongyiGuichengScore.getDanNumbScoreGxsmlq();
		//签名28
		Integer qianmingScoreGxsmlq=gongyiGuichengScore.getQianmingScoreGxsmlq();
		
		//序号栏29
		Integer xuhaoScoreJyxmlq=gongyiGuichengScore.getXuhaoScoreJyxmlq();
		//检验项目栏30
		Integer xiangmuScoreJyxmlq=gongyiGuichengScore.getXiangmuScoreJyxmlq();
		//测量器具31
		Integer qijuScoreJyxmlq=gongyiGuichengScore.getQijuScoreJyxmlq();
		//测定频率32
		Integer cedingPinlvScoreJyxmlq=gongyiGuichengScore.getCedingPinlvScoreJyxmlq();
		//辅助栏33
		Integer fuzhuScoreJyxmlq=gongyiGuichengScore.getFuzhuScoreJyxmlq();
		int score=0;
		//if("gygc".equals(model)){
			score=
			//型别栏1
			xingbieScoreGygc+
			//件号栏2
			jianNumbScoreGygc+
			//件名栏3
			jianNameScoreGygc+
			//页码栏4
			pageTotalScoreGygc+
			//工艺规程编号栏5
			numbScoreGygc+
			//版次栏6
			banciScoreGygc;
		//}else if("gycxtb".equals(model)){
			//型别栏7
			score=xingbieScoreGycxtb+
			//件号栏8
			jianNumbScoreGycxtb+
			//件名栏9
			jianNameScoreGycxtb+
			//页码栏10
			pageTotalScoreGycxtb;
		//}else if("gxtblmfqmx".equals(model)){
			//检查内容11
			score=contentScoreGxtblmfqmx;
		//}else if("gxsmlq".equals(model)){
			//设备栏12
			score=shebeiScoreGxsmlq+
			//工序名称13
			gongxuNameScoreGxsmlq+
			//工序号14
			gongxuNumbScoreGxsmlq+
			//工装15
			toolsScoreGxsmlq+
			//页码16
			pageNumbScoreGxsmlq+
			//尺寸标注没有遗漏17
			chicunBiaozhuYilouScoreGxsmlq+
			//尺寸标注不会产生观察误导18
			chicunBiaozhuWudaoScoreGxsmlq+
			//尺寸标注符合公司标准19
			chicunBiaozhuBiaozhunScoreGxsmlq+
			//图形符合国标制图标准20
			tuxingBiaozhunScoreGxsmlq+
			//轴测图或图片显示示意21
			zhucetuShiyiScoreGxsmlq+
			//图片或轴测图表示准确22
			zhucetuBiaozhunScoreGxsmlq+
			//项目栏23
			xiangmuScoreGxsmlq+
			//作业规范栏24
			zuoyeGuifanScoreGxsmlq+
			//技术参数栏25
			jishuCanshuScoreGxsmlq+
			//索引号26
			suoyinScoreGxsmlq+
			//更改单号27
			danNumbScoreGxsmlq+
			//签名28
			qianmingScoreGxsmlq;
		//}else if("jyxmlq".equals(model)){
			//序号栏29
			score=xuhaoScoreJyxmlq+
			//检验项目栏30
			xiangmuScoreJyxmlq+
			//测量器具31
			qijuScoreJyxmlq+
			//测定频率32
			cedingPinlvScoreJyxmlq+
			//辅助栏33
			fuzhuScoreJyxmlq;
		//}
		return score;
	}
	
	/**********************************工艺规程打分类别管理**********************************************/
	/**
	 * 添加打分类别
	 * @param gongyiGuicheng
	 * @return
	 */
	public GongyiGuichengScoreLeibie addGongyiGuichengScoreLeibie(GongyiGuichengScoreLeibie gongyiGuichengScoreLeibie) {
		boolean result= totalDao.save(gongyiGuichengScoreLeibie);
		if(result){
			return gongyiGuichengScoreLeibie;
		}else {
			return null;
		}
	}

	/**
	 * 删除打分类别
	 * @param gongyiGuicheng
	 * @return
	 */
	public GongyiGuichengScoreLeibie deleteGongyiGuichengScoreLeibie(GongyiGuichengScoreLeibie gongyiGuichengScoreLeibie) {
		boolean result= totalDao.delete(gongyiGuichengScoreLeibie);
		if(result){
			return gongyiGuichengScoreLeibie;
		}else {
			return null;
		}
	}

	/**
	 * 更新打分类别
	 * @param gongyiGuicheng
	 * @return
	 */
	public GongyiGuichengScoreLeibie updateGongyiGuichengScoreLeibie(GongyiGuichengScoreLeibie gongyiGuichengScoreLeibie) {
		GongyiGuichengScoreLeibie gongyiGuichengScoreLeibieTemp=this.getGongyiGuichengScoreLeibieById(gongyiGuichengScoreLeibie.getId());
		if(gongyiGuichengScoreLeibieTemp!=null){
			String name=gongyiGuichengScoreLeibie.getName();
			String biaoshi=gongyiGuichengScoreLeibie.getBiaoshi();
			Integer score=gongyiGuichengScoreLeibie.getScore();
			Integer orderNumb=gongyiGuichengScoreLeibie.getOrderNumb();
			if(name!=null && !"".equals(name)){
				gongyiGuichengScoreLeibieTemp.setName(name);
			}
			if(biaoshi!=null && !"".equals(biaoshi)){
				gongyiGuichengScoreLeibieTemp.setBiaoshi(biaoshi);
			}
			if(score!=null){
				gongyiGuichengScoreLeibieTemp.setScore(score);
			}
			if(orderNumb!=null){
				gongyiGuichengScoreLeibieTemp.setOrderNumb(orderNumb);
			}
		}
		boolean result= totalDao.update(gongyiGuichengScoreLeibieTemp);
		if(result){
			return gongyiGuichengScoreLeibieTemp;
		}else {
			return null;
		}
	}
	
	/**
	 * 获得工艺规程记录
	 * @param id
	 * @return
	 */
	public GongyiGuichengScoreLeibie getGongyiGuichengScoreLeibieById(Integer id) {
		if(id != null){
			return (GongyiGuichengScoreLeibie)totalDao.getObjectById(GongyiGuichengScoreLeibie.class, id);
		}
		return null;
	}
	/**
	 * 获得工艺规程记录 根据标识
	 * @param id
	 * @return
	 */
	public GongyiGuichengScoreLeibie getGongyiGuichengScoreLeibieByBiaoshi(String biaoshi) {
		if(biaoshi != null){
			return (GongyiGuichengScoreLeibie)totalDao.getObjectByCondition("from GongyiGuichengScoreLeibie g where g.biaoshi=?", biaoshi);
		}
		return null;
	}
	
	/**
	 * 获得打分类别集合
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Object[] getGongyiGuichengScoreLeibieListAll(GongyiGuichengScoreLeibie gongyiGuichengScoreLeibie, int pageNo, int pageSize) {
		String hql = "from GongyiGuichengScoreLeibie g where 1 = 1 and g.parentId is null";
		hql+=" order by g.orderNumb";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	/**
	 * 获得打分类别集合For select
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List getGongyiGuichengScoreLeibieListAllForSelect(GongyiGuichengScoreLeibie gongyiGuichengScoreLeibie) {
		String hql = "from GongyiGuichengScoreLeibie g where 1 = 1 and g.parentId is null";
		hql+=" order by g.orderNumb";
		List list = totalDao.find(hql);
		return list;
	}
	
	/**
	 * 获得打分类别下检查条目
	 * @param 
	 * @return
	 */
	public List getGongyiGuichengScoreLeibieListByParentId(Integer parentId){
		String hql="from GongyiGuichengScoreLeibie g where 1=1 and g.parentId=?";
		return this.totalDao.find(hql, new Object[]{parentId});
	}
	
	/**
	 * 获得打分类别下打分记录
	 * @param 
	 * @return
	 */
	public List getGongyiGuichengScoreJiluListByParentId(Integer parentId){
		String hql="from GongyiGuichengScoreLeibie g where 1=1 and g.parentId=?";
		return this.totalDao.find(hql, new Object[]{parentId});
	}
	
	/**
	 * 获得打分类别下打分记录
	 * @param 
	 * @return
	 */
	public List getGongyiGuichengScoreXuanxiangListByParentId(Integer parentId){
		String hql="from GongyiGuichengScoreLeibie g where 1=1 and g.parentId=?";
		return this.totalDao.find(hql, new Object[]{parentId});
	}
	
	
	/*****************************************工艺规程打分***************************************/
	/**
	 * 添加打分类别
	 * @param gongyiGuicheng
	 * @return
	 */
	public GongyiGuichengScoreItem addGongyiGuichengScoreItem(GongyiGuichengScoreItem gongyiGuichengScoreItem) {
		boolean result= totalDao.save(gongyiGuichengScoreItem);
		if(result){
			return gongyiGuichengScoreItem;
		}else {
			return null;
		}
	}

	/**
	 * 删除打分类别
	 * @param gongyiGuicheng
	 * @return
	 */
	public GongyiGuichengScoreItem deleteGongyiGuichengScoreItem(GongyiGuichengScoreItem gongyiGuichengScoreItem) {
		boolean result= totalDao.delete(gongyiGuichengScoreItem);
		if(result){
			return gongyiGuichengScoreItem;
		}else {
			return null;
		}
	}

	/**
	 * 更新打分类别
	 * @param gongyiGuicheng
	 * @return
	 */
	public GongyiGuichengScoreItem updateGongyiGuichengScoreItem(GongyiGuichengScoreItem gongyiGuichengScoreItem) {
		GongyiGuichengScoreItem gongyiGuichengScoreItemTemp=this.getGongyiGuichengScoreItemById(gongyiGuichengScoreItem.getId());
		if(gongyiGuichengScoreItemTemp!=null){
			String model=gongyiGuichengScoreItem.getModel();
			String biaoshi=gongyiGuichengScoreItem.getBiaoshi();
			Integer score=gongyiGuichengScoreItem.getScore();
			String remark=gongyiGuichengScoreItem.getRemark();
			if(model!=null && !"".equals(model)){
				gongyiGuichengScoreItemTemp.setModel(model);
			}
			if(biaoshi!=null && "".equals(biaoshi)){
				gongyiGuichengScoreItemTemp.setBiaoshi(biaoshi);
			}
			if(score!=null){
				gongyiGuichengScoreItemTemp.setScore(score);
			}
			if(remark!=null && !"".equals(remark)){
				gongyiGuichengScoreItemTemp.setRemark(remark);
			}
		}
		boolean result= totalDao.update(gongyiGuichengScoreItemTemp);
		if(result){
			return gongyiGuichengScoreItemTemp;
		}else {
			return null;
		}
	}
	
	/**
	 * 获得工艺规程记录
	 * @param id
	 * @return
	 */
	public GongyiGuichengScoreItem getGongyiGuichengScoreItemById(Integer id) {
		if(id != null){
			return (GongyiGuichengScoreItem)totalDao.getObjectById(GongyiGuichengScoreItem.class, id);
		}
		return null;
	}
	/**
	 * 获得打分类别集合
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List getGongyiGuichengScoreItemListByGongyiGuichengIdAndProcessDataId(Integer gongyiGuichengId, Integer processDataId) {
		String hql=null;
		List list=null;
		if(gongyiGuichengId!=null && processDataId!=null){
			hql = "from GongyiGuichengScoreItem g where g.gongyiGuichengId=? and g.processDataId=?";
			list = totalDao.find(hql,new Object[]{gongyiGuichengId, processDataId});
		}else{
			hql = "from GongyiGuichengScoreItem g where g.gongyiGuichengId=? and g.processDataId is null";
			list = totalDao.find(hql,new Object[]{gongyiGuichengId});
		}
		hql+=" order by id";
		return list;
	}
	/**
	 * 获得打分类别集合
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List getGongyiGuichengScoreItemListByGongyiGuichengIdAndProcessDataId(GongyiGuichengScoreItem gongyiGuichengScoreItem) {
		String hql=null;
		List list=null;
		Integer gongyiGuichengId=gongyiGuichengScoreItem.getGongyiGuichengId();
		Integer processDataId=gongyiGuichengScoreItem.getProcessDataId();
		String model=gongyiGuichengScoreItem.getModel();
		if(gongyiGuichengId!=null && processDataId!=null){
			hql = "from GongyiGuichengScoreItem g where g.gongyiGuichengId=? and g.processDataId=? and g.model=?";
			list = totalDao.find(hql,new Object[]{gongyiGuichengId, processDataId, model});
		}else{
			hql = "from GongyiGuichengScoreItem g where g.gongyiGuichengId=? and g.model=?";
			list = totalDao.find(hql,new Object[]{gongyiGuichengId, model});
		}
		hql+=" order by id";
		return list;
	}
	
	/**
	 * 获得打分唯一记录 根据 工艺规程ID 工序ID Model
	 * @param gongyiGuichengScoreItem
	 * @return
	 */
	public List getGongyiGuichengScoreItemUniqueListByGongyiGuichengIdAndProcessDataIdAndModel(GongyiGuichengScoreItem gongyiGuichengScoreItem){
		String hql=null;
		Integer gongyiGuichengId=gongyiGuichengScoreItem.getGongyiGuichengId();
		hql="from GongyiGuichengScoreItem gg where gg.id in (select max(g.id) from GongyiGuichengScoreItem g where g.gongyiGuichengId=? group by g.model,g.processDataId)";
		List list=totalDao.find(hql,new Object[]{gongyiGuichengId});
		return list;
	}
	/**
	 * 获得打分记录集合 根据 工艺规程ID 工序ID Model
	 * @param gongyiGuichengScoreItem
	 * @return
	 */
	public List getGongyiGuichengScoreItemListByProcessDataIdAndModel(GongyiGuichengScoreItem gongyiGuichengScoreItem){
		String hql=null;
		Integer gongyiGuichengId=gongyiGuichengScoreItem.getGongyiGuichengId();
		Integer processDataId=gongyiGuichengScoreItem.getProcessDataId();
		String model=gongyiGuichengScoreItem.getModel();
		if(processDataId==null){
			hql="from GongyiGuichengScoreItem g where g.gongyiGuichengId=? and g.model=? and g.processDataId is null";
			return totalDao.find(hql,new Object[]{gongyiGuichengId, model});
		}else{
			hql="from GongyiGuichengScoreItem g where g.gongyiGuichengId=? and g.model=? and g.processDataId=?";
			return totalDao.find(hql,new Object[]{gongyiGuichengId, model, processDataId});
		}
		
	}
	
	/**
	 * 获得工序数据记录
	 * @param id
	 * @return
	 */
	public ProcessData getProcessDataById(Integer id) {
		if(id != null){
			return (ProcessData)totalDao.getObjectById(ProcessData.class, id);
		}
		return null;
	}
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
		
}
