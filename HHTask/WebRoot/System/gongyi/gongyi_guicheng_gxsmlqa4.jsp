<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@include file="/util/sonHead.jsp"%>
		<style type="text/css">
			.table{
				border:0px solid #999;
				border-width: 1px;
				border-collapse:collapse;
				width: 756px;
			}
			.table th,.table td {
				border-width: 1px;
				padding: 0px;
			}
			
			.subTable{
				text-align: center;
				border-collapse:collapse;
				width: 100%;
				border-width:1px; 
				border-style:hidden;
			}
		</style>
	</head>
	<body style="text-align: center;">
			<div align="center">
				<!-- A4页面开始 height:1123px  height:1086px-->
				<div id="printDiv" style="width:794px;height:1123px;border:1px solid #000000;">
					<!-- 页边距内开始 -->
					<!-- 打印页边距设定为 5mm 时，网页内最大元素的分辨率：756×1086   5mm/18.89-->
					<div style="width:756px;height:1086px;border:1px solid #000000;position: relative;top:19px;"> 
					<input type="hidden" id="gygcId" value="${gongyiGuicheng.id}"/>
					<input type="hidden" id="processDataId" value="${processData.id}"/>
					<table border="0" width="100%" class="table" cellspacing="0" cellpadding="0">
						<!-- 1 -->
						<tr align="center">
							<td align="center" colspan="4">
								<table class="subTable">
									<tr><td rowspan="2" style="width: 13%;"><img style="width: 80px;" src="<%=basePath%>${companyInfo.shhhjpg}"/></td><td style="width: 20%;font-size: 25px;" rowspan="2">工序图表</td><td style="width: 13%">型别</td><td style="width: 20%">件号</td><td style="width: 20%">件名</td><td style="width: 4%">第</td><td style="width: 4%"><%--<input type="text" id="threePage" name="processData.threePage"/>--%>1</td><td style="width: 4%">页</td></tr>
									<tr><td>${gongyiGuicheng.xingbie}</td><td>${gongyiGuicheng.jianNumb}</td><td>${gongyiGuicheng.jianName}</td><td>共</td><td>1</td><td>页</td></tr>
								</table>
							</td>
						</tr>
						<!-- 2 -->
						<tr align="center">
							<td style="width: 15%;">材料</td><td style="width: 25%;"><input type="text" id="" name="processData.cailiao"/></td><td style="width: 15%;">工序名称</td><td style="width: 25%;"><input type="text" name="processData.gongxuName" disabled="disabled"/></td>
						</tr>
						<tr align="center">
							<td style="width: 15%;">设备</td><td style="width: 25%;"><input type="text" name="processData.shebeiName" disabled="disabled"/></td><td style="width: 15%;" rowspan="2">夹/模具</td><td style="width: 25%;"><input type="text" name="processData.jiaOrMoju" disabled="disabled"/></td>
						</tr>
						<tr align="center">
							<td style="width: 15%;">基准</td><td style="width: 25%;"><input type="text" name="processData.jizhun"/></td><td style="width: 25%;"></td>
						</tr>
						<!-- 3 -->
						<tr align="center">
							<td colspan="4">
								
								<table id="partTable" class="subTable" style="width: 80%;margin-bottom: 20px;border: 1px;">
									<tr>
										<td><img id="processImg" style="width: 500px;height: 400px;" src="${processData.processImg}"/></td>
									</tr>
								</table>
							</td>
						</tr>
						<!-- 4-->
						<tr align="center">
							<td colspan="4">
								<table id="operationStandardTable" class="subTable">
									<tr>
										<td style="width: 5%;">项目</td><td style="width: 89%;">作业规范</td>
									</tr>
									<tr>
										<td><input type="hidden" name="id"/><input type="hidden" name="processDataId"/><input type="text" name="numb"  style="width: 20px;"/></td><td><input type="text" name="content" style="width: 100%;"/></td>
									</tr>
									<tr>
										<td><input type="hidden" name="id"/><input type="hidden" name="processDataId"/><input type="text" name="numb"  style="width: 20px;"/></td><td><input type="text" name="content" style="width: 100%;"/></td>
									</tr>
									<tr>
										<td><input type="hidden" name="id"/><input type="hidden" name="processDataId"/><input type="text" name="numb"  style="width: 20px;"/></td><td><input type="text" name="content" style="width: 100%;"/></td>
									</tr>
									<tr>
										<td><input type="hidden" name="id"/><input type="hidden" name="processDataId"/><input type="text" name="numb"  style="width: 20px;"/></td><td><input type="text" name="content" style="width: 100%;"/></td>
									</tr>
								</table>
							</td>
						</tr>
						<!-- 5 -->
						<tr align="center">
							<td colspan="4">
								<table id="detectionItemTable" class="subTable">
									<tr>
										<td style="width: 5%;">序号</td>
										<td style="width: 12%;">检测项目</td>
										<td style="width: 12%;">测量器具</td>
										<td style="width: 12%;">操作者测定频次</td>
										<td style="width: 12%;">巡检磁动频次</td>
										<td rowspan="5" style="width: 3%;">过程参数</td>
										<td style="">&nbsp;</td>
									</tr>
									<tr>
										<td><input type="hidden" name="id"/><input type="hidden" name="processDataId"/><input type="text" name="numb" style="width: 20px;"/></td><td><input type="text" name="xiangmu"/></td><td><input type="text" name="qiju"/></td><td><input type="text" name="caozuoPinci"/></td><td><input type="text" name="xunjianPinci"/></td><td></td>
									</tr>
									<tr>
										<td><input type="hidden" name="id"/><input type="hidden" name="processDataId"/><input type="text" name="numb" style="width: 20px;"/></td><td><input type="text" name="xiangmu"/></td><td><input type="text" name="qiju"/></td><td><input type="text" name="caozuoPinci"/></td><td><input type="text" name="xunjianPinci"/></td><td></td>
									</tr>
									<tr>
										<td><input type="hidden" name="id"/><input type="hidden" name="processDataId"/><input type="text" name="numb" style="width: 20px;"/></td><td><input type="text" name="xiangmu"/></td><td><input type="text" name="qiju"/></td><td><input type="text" name="caozuoPinci"/></td><td><input type="text" name="xunjianPinci"/></td><td></td>
									</tr>
									<tr>
										<td><input type="hidden" name="id"/><input type="hidden" name="processDataId"/><input type="text" name="numb" style="width: 20px;"/></td><td><input type="text" name="xiangmu"/></td><td><input type="text" name="qiju"/></td><td><input type="text" name="caozuoPinci"/></td><td><input type="text" name="xunjianPinci"/></td><td></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr align="center">
							<td colspan="4">
								<table class="subTable">
									<tr>
										<td rowspan="3" style="width: 8%;">更改</td>
										<td style="width: 15%;">索引号</td>
										<td style="width: 15%;"><%--<input type="text" name="processData.suoyinNumb"/>--%></td>
										<td style="width: 15%;">&nbsp;</td>
										<td style="width: 15%;">&nbsp;</td>
										<td style="width: 15%;">&nbsp;</td>
										<td style="width: 15%;">&nbsp;</td>
									</tr>
									<tr>
										<td style="width: 15%;">更改单号</td>
										<td style="width: 15%;"><%--<input type="text" name="processData.danNumb"/>--%></td>
										<td style="width: 15%;">&nbsp;</td>
										<td style="width: 15%;">&nbsp;</td>
										<td style="width: 15%;">&nbsp;</td>
										<td style="width: 15%;">&nbsp;</td>
									</tr>
									<tr>
										<td>签名、日期</td>
										<td><%--<input type="text" name="processData.qianming"/>--%></td>
										<td><%--<input class="Wdate" type="text" name="processData.qianmingDate"
												onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />--%></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
						<!-- 35 -->
						<tr align="center">
							<td align="center" colspan="4" >
								<table class="subTable">
									<tr>
										<td style="width: 8%;">编制</td>
										<td style="width: 8%;">${gongyiGuicheng.bianzhiName}</td>
										<td style="width: 8%;"><s:date name="gongyiGuicheng.bianzhiDate"  format="yyyy-MM-dd"/></td>
										<td style="width: 8%;">校对</td>
										<td style="width: 8%;">${gongyiGuicheng.jiaoduiName}</td>
										<td style="width: 8%;"><s:date name="gongyiGuicheng.jiaoduiDate"  format="yyyy-MM-dd"/></td>
										<td style="width: 8%;">审核</td>
										<td style="width: 8%;">${gongyiGuicheng.shenheName}</td>
										<td style="width: 8%;"><s:date name="gongyiGuicheng.shenheDate"  format="yyyy-MM-dd"/></td>
										<td style="width: 8%;">批准</td>
										<td style="width: 8%;">${gongyiGuicheng.pizhunName}</td>
										<td style="width: 8%;"><s:date name="gongyiGuicheng.pizhunDate"  format="yyyy-MM-dd"/></td>
									</tr>
									<tr>
										<td>会签</td>
										<td colspan="2">
											生产加工
										</td>
										<td colspan="2">
											${gongyiGuicheng.jiagongName}
										</td>
										<td>
											<s:date name="gongyiGuicheng.jiagongDate"  format="yyyy-MM-dd"/>
										</td>
										<td colspan="3">技术质保部品质</td>
										<td colspan="2">
											${gongyiGuicheng.pinzhiName}
										</td>
										<td>
											<s:date name="gongyiGuicheng.pinzhiDate"  format="yyyy-MM-dd"/>
										</td>
									</tr>	
								</table>
							</td>
						</tr>
						
					</table>
					</div>
					<!-- 页边距内结束 -->
				</div>
				<!-- A4页面打印结束 -->
			</div>
			<div style="width: 794px;text-align: center;margin: 0 auto;">
				<form id="markingForm" method="post"
					style="">
					<input type="hidden" name="gongyiGuichengScore.gongyiGuichengId" value="${gongyiGuicheng.id}"/>
					<input type="hidden" name="gongyiGuichengScore.model" value="gxsmlq"/>
					<input type="hidden" name="gongyiGuichengScore.processDataId" value="${processData.id}"/>
				<!-- 工序说明栏区 -->
				<table id="gxsmlq" style="display: block;">
					<tr>
						<td><span>设备栏：</span></td>
						<td>
							<label for="">正确</label>
							<input type="radio" id="xingbieScore1" name="gongyiGuichengScore.shebeiScoreGxsmlq" value="2" checked="checked"/>
							<label for="">错误</label>
							<input type="radio" id="xingbieScore2" name="gongyiGuichengScore.shebeiScoreGxsmlq" value="0"/>
							<label for="">漏填</label>
							<input type="radio" id="xingbieScore3" name="gongyiGuichengScore.shebeiScoreGxsmlq" value="-2"/>
						</td>
					</tr>
					<tr>
						<td><span>工序名称：</span></td>
						<td>
							<label for="">正确</label>
							<input type="radio" id="jianNumbScore1" name="gongyiGuichengScore.gongxuNameScoreGxsmlq" value="2" checked="checked"/>
							<label for="">错误</label>
							<input type="radio" id="jianNumbScore2" name="gongyiGuichengScore.gongxuNameScoreGxsmlq" value="0"/>
							<label for="">漏填</label>
							<input type="radio" id="jianNumbScore3" name="gongyiGuichengScore.gongxuNameScoreGxsmlq" value="-2"/>
						</td>
					</tr>
					<tr>
						<td><span>工序号：</span></td>
						<td>
							<label for="">正确</label>
							<input type="radio" id="jianNameScore1" name="gongyiGuichengScore.gongxuNumbScoreGxsmlq" value="2" checked="checked"/>
							<label for="">错误</label>
							<input type="radio" id="jianNameScore2" name="gongyiGuichengScore.gongxuNumbScoreGxsmlq" value="0"/>
							<label for="">漏填</label>
							<input type="radio" id="jianNameScore3" name="gongyiGuichengScore.gongxuNumbScoreGxsmlq" value="-2"/>
						</td>
					</tr>
					<tr>
						<td><span>工装：</span></td>
						<td>
							<label for="">正确</label>
							<input type="radio" id="pageTotalScore1" name="gongyiGuichengScore.toolsScoreGxsmlq" value="2" checked="checked"/>
							<label for="">错误</label>
							<input type="radio" id="pageTotalScore2" name="gongyiGuichengScore.toolsScoreGxsmlq" value="0"/>
							<label for="">漏填</label>
							<input type="radio" id="pageTotalScore3" name="gongyiGuichengScore.toolsScoreGxsmlq" value="-2"/>
						</td>
					</tr>
					<tr>
						<td><span>页码：</span></td>
						<td>
							<label for="">正确</label>
							<input type="radio" id="pageTotalScore1" name="gongyiGuichengScore.pageNumbScoreGxsmlq" value="2" checked="checked"/>
							<label for="">错误</label>
							<input type="radio" id="pageTotalScore2" name="gongyiGuichengScore.pageNumbScoreGxsmlq" value="0"/>
							<label for="">漏填</label>
							<input type="radio" id="pageTotalScore3" name="gongyiGuichengScore.pageNumbScoreGxsmlq" value="-2"/>
						</td>
					</tr>
					<tr>
						<td><span>尺寸标注没有遗漏：</span></td>
						<td>
							<label for="">是</label>
							<input type="radio" id="pageTotalScore1" name="gongyiGuichengScore.chicunBiaozhuYilouScoreGxsmlq" value="2" checked="checked"/>
							<label for="">否</label>
							<input type="radio" id="pageTotalScore2" name="gongyiGuichengScore.chicunBiaozhuYilouScoreGxsmlq" value="0"/>
						</td>
					</tr>
					<tr>
						<td><span>尺寸标注不会产生观察误导：</span></td>
						<td>
							<label for="">是</label>
							<input type="radio" id="pageTotalScore1" name="gongyiGuichengScore.chicunBiaozhuWudaoScoreGxsmlq" value="2" checked="checked"/>
							<label for="">否</label>
							<input type="radio" id="pageTotalScore2" name="gongyiGuichengScore.chicunBiaozhuWudaoScoreGxsmlq" value="0"/>
						</td>
					</tr>
					<tr>
						<td><span>尺寸标注符合公司标准：</span></td>
						<td>
							<label for="">是</label>
							<input type="radio" id="pageTotalScore1" name="gongyiGuichengScore.chicunBiaozhuBiaozhunScoreGxsmlq" value="2" checked="checked"/>
							<label for="">否</label>
							<input type="radio" id="pageTotalScore2" name="gongyiGuichengScore.chicunBiaozhuBiaozhunScoreGxsmlq" value="0"/>
						</td>
					</tr>
					<tr>
						<td><span>图形符合国标制图标准：</span></td>
						<td>
							<label for="">是</label>
							<input type="radio" id="pageTotalScore1" name="gongyiGuichengScore.tuxingBiaozhunScoreGxsmlq" value="2" checked="checked"/>
							<label for="">否</label>
							<input type="radio" id="pageTotalScore2" name="gongyiGuichengScore.tuxingBiaozhunScoreGxsmlq" value="0"/>
						</td>
					</tr>
					<tr>
						<td><span>轴测图或图片显示示意：</span></td>
						<td>
							<label for="">是</label>
							<input type="radio" id="pageTotalScore1" name="gongyiGuichengScore.zhucetuShiyiScoreGxsmlq" value="2" checked="checked"/>
							<label for="">否</label>
							<input type="radio" id="pageTotalScore2" name="gongyiGuichengScore.zhucetuShiyiScoreGxsmlq" value="0"/>
						</td>
					</tr>
					<tr>
						<td><span>图片或轴测图表示准确：</span></td>
						<td>
							<label for="">是</label>
							<input type="radio" id="pageTotalScore1" name="gongyiGuichengScore.zhucetuBiaozhunScoreGxsmlq" value="2" checked="checked"/>
							<label for="">否</label>
							<input type="radio" id="pageTotalScore2" name="gongyiGuichengScore.zhucetuBiaozhunScoreGxsmlq" value="0"/>
						</td>
					</tr>
					<tr>
						<td><span>项目栏：</span></td>
						<td>
							<label for="">正确</label>
							<input type="radio" id="pageTotalScore1" name="gongyiGuichengScore.xiangmuScoreGxsmlq" value="2" checked="checked"/>
							<label for="">错误</label>
							<input type="radio" id="pageTotalScore2" name="gongyiGuichengScore.xiangmuScoreGxsmlq" value="0"/>
							<label for="">漏填</label>
							<input type="radio" id="pageTotalScore3" name="gongyiGuichengScore.xiangmuScoreGxsmlq" value="-2"/>
						</td>
					</tr>
					<tr>
						<td><span>作业规范栏：</span></td>
						<td>
							<label for="">正确</label>
							<input type="radio" id="pageTotalScore1" name="gongyiGuichengScore.zuoyeGuifanScoreGxsmlq" value="2" checked="checked"/>
							<label for="">错误</label>
							<input type="radio" id="pageTotalScore2" name="gongyiGuichengScore.zuoyeGuifanScoreGxsmlq" value="0"/>
							<label for="">漏填</label>
							<input type="radio" id="pageTotalScore3" name="gongyiGuichengScore.zuoyeGuifanScoreGxsmlq" value="-2"/>
						</td>
					</tr>
					<tr>
						<td><span>技术参数栏：</span></td>
						<td>
							<label for="">正确</label>
							<input type="radio" id="pageTotalScore1" name="gongyiGuichengScore.jishuCanshuScoreGxsmlq" value="2" checked="checked"/>
							<label for="">错误</label>
							<input type="radio" id="pageTotalScore2" name="gongyiGuichengScore.jishuCanshuScoreGxsmlq" value="0"/>
							<label for="">漏填</label>
							<input type="radio" id="pageTotalScore3" name="gongyiGuichengScore.jishuCanshuScoreGxsmlq" value="-2"/>
						</td>
					</tr>
					<tr>
						<td><span>索引号：</span></td>
						<td>
							<label for="">正确</label>
							<input type="radio" id="xingbieScore1" name="gongyiGuichengScore.suoyinScoreGxsmlq" value="2" checked="checked"/>
							<label for="">错误</label>
							<input type="radio" id="xingbieScore2" name="gongyiGuichengScore.suoyinScoreGxsmlq" value="0"/>
							<label for="">漏填</label>
							<input type="radio" id="xingbieScore3" name="gongyiGuichengScore.suoyinScoreGxsmlq" value="-2"/>
						</td>
					</tr>
					<tr>
						<td><span>更改单号：</span></td>
						<td>
							<label for="">正确</label>
							<input type="radio" id="xingbieScore1" name="gongyiGuichengScore.danNumbScoreGxsmlq" value="2" checked="checked"/>
							<label for="">错误</label>
							<input type="radio" id="xingbieScore2" name="gongyiGuichengScore.danNumbScoreGxsmlq" value="0"/>
							<label for="">漏填</label>
							<input type="radio" id="xingbieScore3" name="gongyiGuichengScore.danNumbScoreGxsmlq" value="-2"/>
						</td>
					</tr>
					<tr>
						<td><span>签名：</span></td>
						<td>
							<label for="">正确</label>
							<input type="radio" id="xingbieScore1" name="gongyiGuichengScore.qianmingScoreGxsmlq" value="2" checked="checked"/>
							<label for="">错误</label>
							<input type="radio" id="xingbieScore2" name="gongyiGuichengScore.qianmingScoreGxsmlq" value="0"/>
							<label for="">漏填</label>
							<input type="radio" id="xingbieScore3" name="gongyiGuichengScore.qianmingScoreGxsmlq" value="-2"/>
						</td>
					</tr>
				</table>
				</form>
				<table style="width: 100%;text-align: center;border: 0px;">
					<tr>
						<td><input type="button" id="saveButton" value="保存" class="input" /><input type="button" id="markingButton" value="打分" class="input"  /><input type="button" id="printButton" value="打印" class="input" /></td>
					</tr>
				</table>
				
			</div>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
<script type="text/javascript">

$(function(){
	//全局工艺规程状态
	var dengluId='${sessionScope.Users.id}';
	var status='';
	var bianzhiId='';
	var jiaoduiId='';
	var shenheId='';
	var pizhunId='';
	var gygcId=$("#gygcId").val();
	var processDataId=$("#processDataId").val();
	$("#printButton").bind("click",function() {
		window.location="gongyiGuichengAction!getGongyiGuiChengGxsmlqa4Page.action?gongyiGuicheng.id="+gygcId+"&processData.gongyiGuichengId="+gygcId+"&processData.id="+processDataId+"&print=print";
	});
	//添加零件
	/*
	function getProcessPartParams(){
		var $idArr=$("#partTable input[name='id']");
		var $numbArr=$("#partTable input[name='numb']");
		var $jianNumbArr=$("#partTable input[name='jianNumb']");
		var $jianNameArr=$("#partTable input[name='jianName']");
		var $danNumbArr=$("#partTable input[name='danNumb']");
		var $cailiaoArr=$("#partTable input[name='cailiao']");
		var $remarkArr=$("#partTable input[name='remark']");
		var $processDataIdArr=$("#partTable input[name='processDataId']");
		//var ids,numbs,jianNumbs,jianNames,danNumbs,cailiaos,remarks,processDataIds;
		//return {'ids':ids,'numbs':numbs,'jianNumbs':jianNumbs,'jianNames':jianNames,'danNumbs':danNumbs,'cailiaos':cailiaos,'remarks':remarks,'processDataIds':processDataIds};
		//return ids+';'+numbs+';'+jianNumbs+';'+jianNames+';'+danNumbs+';'+cailiaos+';'+remarks+';'+processDataIds;
		var processPartArr=new Array();
		for(var i=0;i<$idArr.length;i++){
			processPartArr[i]={	
							'id': $($idArr[i]).val(),
							'numb':$($numbArr[i]).val(),
							'jianNumb':$($jianNumbArr[i]).val(),
							'jianName':$($jianNameArr[i]).val(),
							'danNumb':$($danNumbArr[i]).val(),
							'cailiao':$($cailiaoArr[i]).val(),
							'remark':$($remarkArr[i]).val(),
							'processDataId':$("#processDataId").val()
						  };
		}
		return JSON.stringify(processPartArr);
	}*/
	//添加作业规范
	function getOperationStandardParams(){
		var $idArr=$("#operationStandardTable input[name='id']");
		var $numbArr=$("#operationStandardTable input[name='numb']");
		var $contentArr=$("#operationStandardTable input[name='content']");
		var $processDataIdArr=$("#partTable input[name='processDataId']");
		var operationStandardArr=new Array();
		for(var i=0;i<$idArr.length;i++){
			operationStandardArr[i]={	
							'id': $($idArr[i]).val(),
							'numb':$($numbArr[i]).val(),
							'content':$($contentArr[i]).val(),
							'processDataId':$("#processDataId").val()
						  };
		}
		return JSON.stringify(operationStandardArr);
	}
	//添加检查项目
	function getDetectionItemParams(){
		var $idArr=$("#detectionItemTable input[name='id']");
		var $numbArr=$("#detectionItemTable input[name='numb']");
		var $xiangmuArr=$("#detectionItemTable input[name='xiangmu']");
		var $qijuArr=$("#detectionItemTable input[name='qiju']");
		var $caozuoPinciArr=$("#detectionItemTable input[name='caozuoPinci']");
		var $xunjianPinciArr=$("#detectionItemTable input[name='xunjianPinci']");
		var $processDataIdArr=$("#detectionItemTable input[name='processDataId']");
		var detectionItemArr=new Array();
		for(var i=0;i<$idArr.length;i++){
			detectionItemArr[i]={	
							'id': $($idArr[i]).val(),
							'numb':$($numbArr[i]).val(),
							'xiangmu':$($xiangmuArr[i]).val(),
							'qiju':$($qijuArr[i]).val(),
							'caozuoPinci':$($caozuoPinciArr[i]).val(),
							'xunjianPinci':$($xunjianPinciArr[i]).val(),
							'processDataId':$("#processDataId").val()
						  };
		}
		return JSON.stringify(detectionItemArr);
	}
	
	//添加工序数据Data jQuery数组取第一个值
	function getProcessDataParams(){
		var $cailiao=$("input[name='processData.cailiao']");
		//var $shebeiName=$("input[name='processData.shebeiName']");
		var $jizhun=$("input[name='processData.jizhun']");
		//var $gongxuName=$("input[name='processData.gongxuName']");
		//var $jiaOrMoju=$("input[name='processData.jiaOrMoju']");
		//var $suoyinNumb=$("input[name='processData.suoyinNumb']");
		//var $danNumb=$("input[name='processData.danNumb']");
		//var $qianming=$("input[name='processData.qianming']");
		//var $qianmingDate=$("input[name='processData.qianmingDate']");
		//var $threePage=$("input[name='processData.threePage']");
		var processData={
			'id': $("#processDataId").val(),
			'cailiao':$cailiao.val(),
			//'shebeiName':$shebeiName.val(),
			'jizhun':$jizhun.val()
			//'gongxuName':$gongxuName.val(),
			//'jiaOrMoju':$jiaOrMoju.val(),
			//'suoyinNumb':$suoyinNumb.val(),
			//'danNumb':$danNumb.val(),
			//'qianming':$qianming.val(),
			//'qianmingDate':$qianmingDate.val(),
			//'threePage':$threePage.val()
		};
		return JSON.stringify(processData);
	}
	
	
	//*************************************以上获取参数******************************************************
	//*************************************下边初始化表格******************************************************
	//初始化零件
	//$("#partTable tr:last").remove();
	/*
	$.ajax({
		type: "post",
		dataType: "json",
        url: "gongyiGuichengAction!getProcessPartListByprocessDataId.action",
        data:{
			'processPart.processDataId': $('#processDataId').val()
		},
		async: false,
		success: function(data){
			var success=data.success;
			if(success){
				var processPartArr=data.data;
				$(processPartArr).each(function(i,n){
					$('<tr><td><input type="hidden" id="" name="id" value="'+this.id+'"/><input type="hidden" id="" name="processDataId" value="'+this.processDataId+'"/><input type="text" id="" name="numb" style="width: 50px;" value="'+this.numb+'"/></td><td><input type="text" id="" name="jianNumb" style="width: 50px;" value="'+this.jianNumb+'"/></td><td><input type="text" id="" name="jianName" style="width: 50px;" value="'+this.jianName+'"/></td><td><input type="text" id="" name="danNumb" style="width: 50px;" value="'+this.danNumb+'"/></td><td><input type="text" id="" name="cailiao" style="width: 100px;" value="'+this.cailiao+'"/></td><td><input type="text" id="" name="remark" style="width: 50px;" value="'+this.remark+'"/></td></tr>').appendTo('#partTable');
				});
			}else{
				$('<tr><td><input type="hidden" id="" name="id" value=""/><input type="hidden" id="" name="processDataId" value=""/><input type="text" id="" name="numb" style="width: 50px;" value=""/></td><td><input type="text" id="" name="jianNumb" style="width: 50px;" value=""/></td><td><input type="text" id="" name="jianName" style="width: 50px;" value=""/></td><td><input type="text" id="" name="danNumb" style="width: 50px;" value=""/></td><td><input type="text" id="" name="cailiao" style="width: 100px;" value=""/></td><td><input type="text" id="" name="remark" style="width: 50px;" value=""/></td></tr>').appendTo('#partTable');
			}
		}
	});*/
	
	//初始化操作规范
	
	$.ajax({
		type: "post",
		dataType: "json",
        url: "gongyiGuichengAction!getOperationStandardListByprocessDataId.action",
        data:{
			'operationStandard.processDataId': $('#processDataId').val()
		},
		async: false,
		success: function(data){
			var success=data.success;
			if(success){
				//$("#operationStandardTable tr:gt(0)").remove();
				var operationStandardArr=data.data;
				$(operationStandardArr).each(function(i,n){
					$('<tr><td><input type="hidden" name="id" value="'+this.id+'"/><input type="hidden" name="processDataId" value="'+this.processDataId+'"/><input type="text" name="numb"  style="width: 20px;" value="'+this.numb+'"/></td><td><input type="text" name="content" style="width: 100%;" value="'+this.content+'"/></td></tr>').replaceAll('#operationStandardTable tr:eq('+(i+1)+')');
				});
			}
		}
	});
	//初始化检查项目
	$.ajax({
		type: "post",
		dataType: "json",
        url: "gongyiGuichengAction!getDetectionItemListByprocessDataId.action",
        data:{
			'detectionItem.processDataId': $('#processDataId').val()
		},
		async: false,
		success: function(data){
			var success=data.success;
			if(success){
				//$("#operationStandardTable tr:gt(0)").remove();
				var detectionItemArr=data.data;
				$(detectionItemArr).each(function(i,n){
					$('<tr><td><input type="hidden" name="id" value="'+this.id+'"/><input type="hidden" name="processDataId" value="'+this.processDataId+'"/><input type="text" name="numb" style="width: 20px;" value="'+this.numb+'"/></td><td><input type="text" name="xiangmu" value="'+this.xiangmu+'"/></td><td><input type="text" name="qiju" value="'+this.qiju+'"/></td><td><input type="text" name="caozuoPinci" value="'+this.caozuoPinci+'"/></td><td><input type="text" name="xunjianPinci" value="'+this.xunjianPinci+'"/></td><td></td></tr>').replaceAll('#detectionItemTable tr:eq('+(i+1)+')');
				});
			}
		}
	});
	
	//初始化工序数据
	$.ajax({
		type: "post",
		dataType: "json",
        url: "gongyiGuichengAction!getProcessDataId.action",
        data:{
			'processData.id': $('#processDataId').val()
		},
		async: false,
		success: function(data){
			var success=data.success;
			if(success){
				var processData=data.data;
				var cailiao=processData.cailiao;
				var shebeiName=processData.shebeiName;
				var jizhun=processData.jizhun;
				var gongxuName=processData.gongxuName;
				var gongzhuangName=processData.gongzhuangName;
				var jiaOrMoju=processData.jiaOrMoju;
				//var suoyinNumb=processData.suoyinNumb;
				//var danNumb=processData.danNumb;
				//var qianming=processData.qianming;
				//var qianmingDate=processData.qianmingDate==null? new Date(): processData.qianmingDate;
				//qianmingDate=new Date(qianmingDate).format('yyyy-MM-dd');
				//var threePage=processData.threePage;
				
				var $cailiao=$("input[name='processData.cailiao']");
				var $shebeiName=$("input[name='processData.shebeiName']");
				var $jizhun=$("input[name='processData.jizhun']");
				var $gongxuName=$("input[name='processData.gongxuName']");
				var $jiaOrMoju=$("input[name='processData.jiaOrMoju']");
				//var $suoyinNumb=$("input[name='processData.suoyinNumb']");
				//var $danNumb=$("input[name='processData.danNumb']");
				//var $qianming=$("input[name='processData.qianming']");
				//var $qianmingDate=$("input[name='processData.qianmingDate']");
				//var $threePage=$("input[name='processData.threePage']");
				$cailiao.val(cailiao);
				$shebeiName.val(shebeiName);
				$jizhun.val(jizhun);
				$gongxuName.val(gongxuName);
				$jiaOrMoju.val(gongzhuangName);
				//$suoyinNumb.val(suoyinNumb);
				//$danNumb.val(danNumb);
				//$qianming.val(qianming);
				//$qianmingDate.val(qianmingDate);
				//$threePage.val(threePage);
			}
		}
	});
	//添加 删除零
	$("#addPartButton").bind("click",function(){
		var addPartTable=$("#partTable");
		var tr=$("#partTable tr:last").clone(false);
		addPartTable.append(tr);
	});
	$("#deletePartButton").bind("click",function(){
		var addPartTable=$("#partTable");
		var subTr=$("#partTable tr");
		if(subTr.length==2){
			alert('不能再删了!');
			return;
		}
		$("#partTable tr:last").remove();
	});
	//********************************保存页面数据******************************************************
	$("#saveButton").bind("click",function(){
		var operationStandardParams=getOperationStandardParams();
		var detectionItemParams=getDetectionItemParams();
		var processDataParams=getProcessDataParams();
		$.ajax({
			type: "post",
			dataType: "json",
	        url: "gongyiGuichengAction!updateGongyiGuiChengGxsmlqa4Page.action",
	        data:{
				//'processPart.params': processPartParams,
				'operationStandard.params': operationStandardParams,
				'detectionItem.params': detectionItemParams,
				'processData.params': processDataParams
			},
			async: false,
			success: function(data){
				var success=data.success;
				if(success){
					alert("保存成功");
					window.location.reload();
				}
			}
		});
	});
	//********************************保存页面数据******************************************************
//****************************************************打分区*******************************************************
	//$("form table:visible").css('display','none');
	$.ajax({
		type: "get",
		dataType: "json",
        url: "gongyiGuichengScoreAction!getGongyiGuichengScoreByGongyiGuichengIdAndModelAndProcessDataId.action",
        data:{
			'gongyiGuichengScore.gongyiGuichengId': $("#gygcId").val(),
			'gongyiGuichengScore.model': "gxsmlq",
			'gongyiGuichengScore.processDataId': $('#processDataId').val()
		},
		async: true,
		success: function(data){
			var success=data.success;
			if(success){
				var gongyiGuichengScore=data.data;
				$("input[name='gongyiGuichengScore.shebeiScoreGxsmlq'][value="+gongyiGuichengScore.shebeiScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.gongxuNameScoreGxsmlq'][value="+gongyiGuichengScore.gongxuNameScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.gongxuNumbScoreGxsmlq'][value="+gongyiGuichengScore.gongxuNumbScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.toolsScoreGxsmlq'][value="+gongyiGuichengScore.toolsScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.pageNumbScoreGxsmlq'][value="+gongyiGuichengScore.pageNumbScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.chicunBiaozhuYilouScoreGxsmlq'][value="+gongyiGuichengScore.chicunBiaozhuYilouScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.chicunBiaozhuWudaoScoreGxsmlq'][value="+gongyiGuichengScore.chicunBiaozhuWudaoScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.chicunBiaozhuBiaozhunScoreGxsmlq'][value="+gongyiGuichengScore.chicunBiaozhuBiaozhunScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.tuxingBiaozhunScoreGxsmlq'][value="+gongyiGuichengScore.tuxingBiaozhunScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.zhucetuShiyiScoreGxsmlq'][value="+gongyiGuichengScore.zhucetuShiyiScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.zhucetuBiaozhunScoreGxsmlq'][value="+gongyiGuichengScore.zhucetuBiaozhunScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.xiangmuScoreGxsmlq'][value="+gongyiGuichengScore.xiangmuScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.zuoyeGuifanScoreGxsmlq'][value="+gongyiGuichengScore.zuoyeGuifanScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.jishuCanshuScoreGxsmlq'][value="+gongyiGuichengScore.jishuCanshuScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.suoyinScoreGxsmlq'][value="+gongyiGuichengScore.suoyinScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.danNumbScoreGxsmlq'][value="+gongyiGuichengScore.danNumbScoreGxsmlq+"]").attr("checked",true);
				$("input[name='gongyiGuichengScore.qianmingScoreGxsmlq'][value="+gongyiGuichengScore.qianmingScoreGxsmlq+"]").attr("checked",true);
			
				
				
			}
		}
	});
	$('#markingButton').bind('click',function(){
		$.ajax({
			type: "get",
			dataType: "json",
	        url: "gongyiGuichengScoreAction!updateGongyiGuichengScore.action",
	        data:$('#markingForm').serialize(),
			async: false,
			success: function(data){
				var success=data.success;
				if(success){
					var gongyiGuichengScore=data.data;
					alert('打分成功!');
				}else{
					alert('打分失败!');
				}
			}
		});
	});
	
	//****************************************添加权限******************************************************
$.ajax({
		type: "post",
		dataType: "json",
        url: "gongyiGuichengAction!getGongyiGuichengById.action",
        data:{
			'gongyiGuicheng.id': $('#gygcId').val()
		},
		async: false,
		success: function(data){
			var success=data.success;
			if(success){
				var data=data.data;
				
				bianzhiId=data.bianzhiId;
				jiaoduiId=data.jiaoduiId;
				shenheId=data.shenheId;
				pizhunId=data.pizhunId;
				
				status=data.status;
				//$('#button').attr('disabled',"true");添加disabled属性
				//$('#button').removeAttr("disabled"); 移除disabled属性 
				
				//markingForm
				//markingButton
				//saveButton
				//presentButton
				//printButton
				switch(status){
					case '打回':
					case '待编制':
						$("#markingForm input").attr('disabled',true);
						$("#markingButton").attr('disabled',true);
						$("#backButton").attr('disabled',true);
						
						break;
					case '已编制':
						if(jiaoduiId==dengluId){
							$(":enabled").attr('disabled',true);
						
							$("#markingForm input").removeAttr("disabled");
							$("#markingButton").removeAttr('disabled');
							$("#printButton").removeAttr("disabled");
						}else{
							$(":enabled").attr('disabled',true);
						}
						
						break;
					case '已校对':
						if(shenheId==dengluId){
							$(":enabled").attr('disabled',true);
						
							$("#markingForm input").removeAttr("disabled");
							$("#markingButton").removeAttr('disabled');
							$("#printButton").removeAttr("disabled");
						}else{
							$(":enabled").attr('disabled',true);
						}
						
						break;
					case '已审核':
						if(pizhunId==dengluId){
							$(":enabled").attr('disabled',true);
						
							$("#markingForm input").removeAttr("disabled");
							$("#markingButton").removeAttr('disabled');
							$("#printButton").removeAttr("disabled");
						}else{
							$(":enabled").attr('disabled',true);
						}
						break;
					case '已批准':
						$(":enabled").attr('disabled',true);
						
						$("#printButton").removeAttr("disabled");
						break;
					default :
						
						break;
				}
			}
		}
	});
//*************************末尾***************************
});
</script>
