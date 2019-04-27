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
				text-align: center;
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
		<script type="text/javascript">
		var processImg='';
		function uploadProcessDataAffixResend(data){
			var processData=JSON.parse(data);
			var processImg=processData.processImg==null? '': processData.processImg;
			var processVideo=processData.processVideo==null? '':processData.processVideo;
			document.getElementById("processImg").src=processImg;
		}
		</script>
	</head>
	<body style="text-align: center;">
			<div align="center" style="width: 100%;">
				<!-- A4页面开始 -->
				<div id="printDiv" style="width:1587px;height:1122px;border:1px solid #000000;">
					<!-- 页边距内开始 -->
					<!-- 打印页边距设定为 5mm 时，网页内最大元素的分辨率：1549×1084   5mm/18.89  19-->
					<div style="width:1549px;height:1084px;border:1px solid #000000;position: relative;top:19px;"> 
					<input type="hidden" id="gygcId" value="${gongyiGuicheng.id}"/>
					<input type="hidden" id="processDataId" value="${processData.id}"/>
					<table border="0" class="table" cellspacing="0" cellpadding="0" style="width: 100%">
						<!-- 1  19 9-->
						<tr>
							<td rowspan="2" style="width: 5%">
							<img style="height: 80px;" src="<%=basePath%>${companyInfo.shhhjpg}"/>
							</td>
							<td rowspan="2" colspan="7" style="width: 20%;font-size: 25px;">工序图表</td><td colspan="2" style="width: 5%">型别</td><td colspan="5" style="width: 15%">件号</td><td colspan="4" style="width: 15%">件名</td>
							<td colspan="3" style="width: 15%">工序名称</td><td colspan="2" style="width: 5%">序号</td><td colspan="3" style="width: 15%">材料</td><td style="width: 5%">第<%--<input type="text" id="twoPage" name="processData.twoPage" style="width: 20px;"/>--%>1页</td>
						</tr>
						<!-- 2 -->
						<tr>
							<td colspan="2">${gongyiGuicheng.xingbie}</td><td colspan="5">${gongyiGuicheng.jianNumb}</td><td colspan="4">${gongyiGuicheng.jianName}</td>
							<td colspan="3">${processData.gongxuName}</td><td colspan="2">${processData.gongxuNo}</td><td colspan="3"><input type="text" name="processData.cailiao" style="width: 100%;"/></td><td>共1页</td>
						</tr>
						<!-- 3 -->
						<tr>
							<td colspan="2">设备</td><td colspan="17"><input type="text" name="processData.shebeiName" disabled="disabled" style="width: 100%;"/></td>
							<td style="width: 50px;">项目</td><td colspan="8">作业规范</td>
						</tr>
						<!-- 4 -->
						<tr>
							<td colspan="2">基准</td><td colspan="17"><input type="text" name="processData.jizhun" style="width: 100%;"/></td>
							<td><input type="hidden" name="operationStandardTable.id"/><input type="text" name="operationStandardTable.numb" style="width: 50px;"/></td><td colspan="8"><input type="text" name="operationStandardTable.content" style="width: 100%;"/></td>
						</tr>
						<!-- 5 -->
						<tr>
							<td colspan="2">夹\模具</td><td colspan="17"><input type="text" name="processData.jiaOrMoju" disabled="disabled" style="width: 100%;"/></td>
							<td><input type="hidden" name="operationStandardTable.id"/><input type="text" name="operationStandardTable.numb" style="width: 50px;"/></td><td colspan="8"><input type="text" name="operationStandardTable.content" style="width: 100%;"/></td>
						</tr>
						<!-- 6 -->
						<tr>
							<td colspan="19" rowspan="27">
								<p><img id="processImg" style="width: 500px;height: 600px;" src="${processData.processImg}"/></p>
								<p>
								    <form action="gongyiGuichengAction!uploadProcessDataAffix.action?affixType=processImg" enctype="multipart/form-data" method="post" target="hidden_iframe">  
								      <div style="position:relative;display:none;">  
								        <input type="hidden" name="processData.id" value="${processData.id}" />  
								        <iframe name="hidden_iframe" id="hidden_iframe"></iframe>  
								      </div>  
							    	  <input type="file" name="processDataImgFile" />  
									  <input type="button" id="upload" value=" 上 传 " onclick="this.form.submit();"  />
								    </form>  
								</p>
							</td>
							<td><input type="hidden" name="operationStandardTable.id"/><input type="text" name="operationStandardTable.numb" style="width: 50px;"/></td><td colspan="8"><input type="text" name="operationStandardTable.content" style="width: 100%;"/></td>
						</tr>
						<!-- 7 -->
						<tr>
							<td><input type="hidden" name="operationStandardTable.id"/><input type="text" name="operationStandardTable.numb" style="width: 50px;"/></td><td colspan="8"><input type="text" name="operationStandardTable.content" style="width: 100%;"/></td>
						</tr>
						<!-- 8 -->
						<tr>
							<td><input type="hidden" name="operationStandardTable.id"/><input type="text" name="operationStandardTable.numb" style="width: 50px;"/></td><td colspan="8"><input type="text" name="operationStandardTable.content" style="width: 100%;"/></td>
						</tr>
						<!-- 9 -->
						<tr>
							<td><input type="hidden" name="operationStandardTable.id"/><input type="text" name="operationStandardTable.numb" style="width: 50px;"/></td><td colspan="8"><input type="text" name="operationStandardTable.content" style="width: 100%;"/></td>
						</tr>
						<!-- 10 -->
						<tr>
							<td><input type="hidden" name="operationStandardTable.id"/><input type="text" name="operationStandardTable.numb" style="width: 50px;"/></td><td colspan="8"><input type="text" name="operationStandardTable.content" style="width: 100%;"/></td>
						</tr>
						<!-- 11 -->
						<tr>
							<td><input type="hidden" name="operationStandardTable.id"/><input type="text" name="operationStandardTable.numb" style="width: 50px;"/></td><td colspan="8"><input type="text" name="operationStandardTable.content" style="width: 100%;"/></td>
						</tr>
						<!-- 12 -->
						<tr>
							<td><input type="hidden" name="operationStandardTable.id"/><input type="text" name="operationStandardTable.numb" style="width: 50px;"/></td><td colspan="8"><input type="text" name="operationStandardTable.content" style="width: 100%;"/></td>
						</tr>
						<!-- 13 -->
						<tr>
							<td><input type="hidden" name="operationStandardTable.id"/><input type="text" name="operationStandardTable.numb" style="width: 50px;"/></td><td colspan="8"><input type="text" name="operationStandardTable.content" style="width: 100%;"/></td>
						</tr>
						<!-- 14 -->
						<tr>
							<td><input type="hidden" name="operationStandardTable.id"/><input type="text" name="operationStandardTable.numb" style="width: 50px;"/></td><td colspan="8"><input type="text" name="operationStandardTable.content" style="width: 100%;"/></td>
						</tr>
						<!-- 15 -->
						<tr>
							<td><input type="hidden" name="operationStandardTable.id"/><input type="text" name="operationStandardTable.numb" style="width: 50px;"/></td><td colspan="8"><input type="text" name="operationStandardTable.content" style="width: 100%;"/></td>
						</tr>
						<!-- 16 -->
						<tr>
							<td><input type="hidden" name="operationStandardTable.id"/><input type="text" name="operationStandardTable.numb" style="width: 50px;"/></td><td colspan="8"><input type="text" name="operationStandardTable.content" style="width: 100%;"/></td>
						</tr>
						<!-- 17 -->
						<tr>
							<td><input type="hidden" name="operationStandardTable.id"/><input type="text" name="operationStandardTable.numb" style="width: 50px;"/></td><td colspan="8"><input type="text" name="operationStandardTable.content" style="width: 100%;"/></td>
						</tr>
						<!-- 18 -->
						<tr>
							<td><input type="hidden" name="operationStandardTable.id"/><input type="text" name="operationStandardTable.numb" style="width: 50px;"/></td><td colspan="8"><input type="text" name="operationStandardTable.content" style="width: 100%;"/></td>
						</tr>
						<!-- 18 -->
						<tr>
							<td><input type="hidden" name="operationStandardTable.id"/><input type="text" name="operationStandardTable.numb" style="width: 50px;"/></td><td colspan="8"><input type="text" name="operationStandardTable.content" style="width: 100%;"/></td>
						</tr>
						<!-- 19 -->
						<tr align="center">
							<td rowspan="4">过 <br/>程<br/>参<br/>数</td><td colspan="8"><input type="hidden" name="processGuochengCanshu.id"/><input type="text" name="processGuochengCanshu.content" style="width: 100%;"/></td>
						</tr>
						<!-- 20 -->
						<tr>
							<td colspan="8"><input type="hidden" name="processGuochengCanshu.id"/><input type="text" name="processGuochengCanshu.content" style="width: 100%;"/></td>
						</tr>
						<!-- 21 -->
						<tr>
							<td colspan="8"><input type="hidden" name="processGuochengCanshu.id"/><input type="text" name="processGuochengCanshu.content" style="width: 100%;"/></td>
						</tr>
						<!-- 22 -->
						<tr>
							<td colspan="8"><input type="hidden" name="processGuochengCanshu.id"/><input type="text" name="processGuochengCanshu.content" style="width: 100%;"/></td>
						</tr>
						<!-- 23 -->
						<tr>
							<td>序号</td><td colspan="2">检测项目</td><td colspan="2">测量器具</td><td colspan="2">操作者测定频次</td><td colspan="2">巡检测定频次</td>
						</tr>
						<!-- 24 -->
						<tr>
							<td><input type="hidden" name="detectionItemTable.id"/><input type="text" name="detectionItemTable.numb" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.xiangmu" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.qiju" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.caozuoPinci" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.xunjianPinci" style="width: 100%;"/></td>
						</tr>
						<!-- 25 -->
						<tr>
							<td><input type="hidden" name="detectionItemTable.id"/><input type="text" name="detectionItemTable.numb" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.xiangmu" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.qiju" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.caozuoPinci" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.xunjianPinci" style="width: 100%;"/></td>
						</tr>
						<!-- 26 -->
						<tr>
							<td><input type="hidden" name="detectionItemTable.id"/><input type="text" name="detectionItemTable.numb" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.xiangmu" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.qiju" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.caozuoPinci" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.xunjianPinci" style="width: 100%;"/></td>
						</tr>
						<!-- 27 -->
						<tr>
							<td><input type="hidden" name="detectionItemTable.id"/><input type="text" name="detectionItemTable.numb" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.xiangmu" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.qiju" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.caozuoPinci" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.xunjianPinci" style="width: 100%;"/></td>
						</tr>
						<!-- 28 -->
						<tr>
							<td><input type="hidden" name="detectionItemTable.id"/><input type="text" name="detectionItemTable.numb" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.xiangmu" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.qiju" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.caozuoPinci" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.xunjianPinci" style="width: 100%;"/></td>
						</tr>
						<!-- 29 -->
						<tr>
							<td><input type="hidden" name="detectionItemTable.id"/><input type="text" name="detectionItemTable.numb" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.xiangmu" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.qiju" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.caozuoPinci" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.xunjianPinci" style="width: 100%;"/></td>
						</tr>
						<!-- 30 -->
						<tr>
							<td><input type="hidden" name="detectionItemTable.id"/><input type="text" name="detectionItemTable.numb" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.xiangmu" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.qiju" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.caozuoPinci" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.xunjianPinci" style="width: 100%;"/></td>
						</tr>
						<!-- 31 -->
						<tr>
							<td><input type="hidden" name="detectionItemTable.id"/><input type="text" name="detectionItemTable.numb" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.xiangmu" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.qiju" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.caozuoPinci" style="width: 100%;"/></td><td colspan="2"><input type="text" name="detectionItemTable.xunjianPinci" style="width: 100%;"/></td>
						</tr>
						<!-- 32 
						<tr>
							
						</tr>-->
						<!-- 33 -->
						<tr>
							<td rowspan="3">更改</td><td colspan="2" >索引号</td><td colspan="2"><%--<input type="text" name="processData.suoyinNumb"/>--%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td>
							<td colspan="2">编制</td><td>${gongyiGuicheng.bianzhiName}</td><td><s:date name="gongyiGuicheng.bianzhiDate"  format="yyyy-MM-dd"/></td><td colspan="3">批准</td><td>${gongyiGuicheng.pizhunName}</td><td><s:date name="gongyiGuicheng.pizhunDate"  format="yyyy-MM-dd"/></td>
						</tr>
						<!-- 34 -->
						<tr>
							<td colspan="2" >更改单号</td><td colspan="2"><%--<input type="text" name="processData.danNumb"/>--%>&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td>
							<td colspan="2">校队</td><td>${gongyiGuicheng.jiaoduiName}</td><td><s:date name="gongyiGuicheng.jiaoduiDate"  format="yyyy-MM-dd"/></td><td rowspan="2">会签</td><td colspan="2">生产部加工</td><td>${gongyiGuicheng.jiagongName}</td><td><s:date name="gongyiGuicheng.jiagongDate"  format="yyyy-MM-dd"/></td>
						</tr>
						<!-- 35 -->
						<tr>
							<td colspan="2" >签名、日期</td><td colspan="2"><%--<input type="text" name="processData.qianming"/>--%>&nbsp;</td><td colspan="2"><%--<input class="Wdate" type="text" name="processData.qianmingDate"
												onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />--%></td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td><td colspan="2">&nbsp;</td>
							<td colspan="2">审核</td><td>${gongyiGuicheng.shenheName}</td><td><s:date name="gongyiGuicheng.shenheDate"  format="yyyy-MM-dd"/></td><td colspan="2">技术质保部品质</td><td>${gongyiGuicheng.pinzhiName}</td><td><s:date name="gongyiGuicheng.pinzhiDate"  format="yyyy-MM-dd"/></td>
						</tr>
					</table>
					</div>
				</div>
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
						<td><input type="button" id="saveButton" value="保存" class="input" /><input type="button" id="markingButton" value="打分" class="input"  /><input type="button" id="printButton" value="打印" class="input" /><input type="button" id="finishButton" value="完成工序编辑" class="input" style="width: 120px;"/></td>
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
		window.location="gongyiGuichengAction!getGongyiGuiChengGxsmlqPage.action?gongyiGuicheng.id="+gygcId+"&processData.gongyiGuichengId="+gygcId+"&processData.id="+processDataId+"&print=print";
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
				var gongzhuangName=processData.gongzhuangName;
				var jizhun=processData.jizhun;
				var jiaOrMoju=processData.jiaOrMoju;
				//var suoyinNumb=processData.suoyinNumb;
				//var danNumb=processData.danNumb;
				//var qianming=processData.qianming;
				//var qianmingDate=processData.qianmingDate==null? new Date(): processData.qianmingDate;
				//qianmingDate=new Date(qianmingDate).format('yyyy-MM-dd');
				//var twoPage=processData.twoPage;
				var $cailiao=$("input[name='processData.cailiao']");
				var $shebeiName=$("input[name='processData.shebeiName']");
				var $jizhun=$("input[name='processData.jizhun']");
				var $jiaOrMoju=$("input[name='processData.jiaOrMoju']");
				//var $suoyinNumb=$("input[name='processData.suoyinNumb']");
				//var $danNumb=$("input[name='processData.danNumb']");
				//var $qianming=$("input[name='processData.qianming']");
				//var $qianmingDate=$("input[name='processData.qianmingDate']");
				//var $twoPage=$("input[name='processData.twoPage']");
				$cailiao.val(cailiao);
				$shebeiName.val(shebeiName);
				$jizhun.val(jizhun);
				$jiaOrMoju.val(gongzhuangName);
				//$suoyinNumb.val(suoyinNumb);
				//$danNumb.val(danNumb);
				//$qianming.val(qianming);
				//$qianmingDate.val(qianmingDate);
				//$twoPage.val(twoPage);
			}
		}
	});
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
				var $id=$("input[name='operationStandardTable.id']");
				var $numb=$("input[name='operationStandardTable.numb']");
				var $content=$("input[name='operationStandardTable.content']");
				var operationStandardArr=data.data;
				$(operationStandardArr).each(function(i,n){
					//$('<td><input type="hidden" name="operationStandardTable.id" value=""/><input type="text" name="operationStandardTable.numb"/></td><td colspan="8"><input type="text" name="operationStandardTable.content" style="width: 100%;"/></td>').appendTo($($id[i]).parent());
					$($id[i]).val(this.id);
					$($numb[i]).val(this.numb);
					$($content[i]).val(this.content);
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
				var $idArr=$("input[name='detectionItemTable.id']");
				var $numbArr=$("input[name='detectionItemTable.numb']");
				var $xiangmuArr=$("input[name='detectionItemTable.xiangmu']");
				var $qijuArr=$("input[name='detectionItemTable.qiju']");
				var $caozuoPinciArr=$("input[name='detectionItemTable.caozuoPinci']");
				var $xunjianPinciArr=$("input[name='detectionItemTable.xunjianPinci']");
				var detectionItemArr=data.data;
				$(detectionItemArr).each(function(i,n){
					$($idArr[i]).val(this.id);
					$($numbArr[i]).val(this.numb);
					$($xiangmuArr[i]).val(this.xiangmu);
					$($qijuArr[i]).val(this.qiju);
					$($caozuoPinciArr[i]).val(this.caozuoPinci);
					$($xunjianPinciArr[i]).val(this.xunjianPinci);
				});
			}
		}
	});
	
	//初始化过程参数
	$.ajax({
		type: "post",
		dataType: "json",
        url: "gongyiGuichengAction!getProcessGuochengCanshuByprocessDataId.action",
        data:{
			'processGuochengCanshu.processDataId': $('#processDataId').val()
		},
		async: false,
		success: function(data){
			var success=data.success;
			if(success){
				processGuochengCanshuList=data.data;
				var $id=$("input[name='processGuochengCanshu.id']");
				var $content=$("input[name='processGuochengCanshu.content']");
				$(processGuochengCanshuList).each(function(i,n){
					$id.eq(i).val(this.id);
					$content.eq(i).val(this.content);
				});
			}
		}
	});
	
	//添加工序数据Data jQuery数组取第一个值 select不适用这种方法
	function getProcessDataParams(){
		var $cailiao=$("input[name='processData.cailiao']");
		//var $shebeiName=$("input[name='processData.shebeiName']");
		var $jizhun=$("input[name='processData.jizhun']");
		//var $jiaOrMoju=$("input[name='processData.jiaOrMoju']");
		//var $suoyinNumb=$("input[name='processData.suoyinNumb']");
		//var $danNumb=$("input[name='processData.danNumb']");
		//var $qianming=$("input[name='processData.qianming']");
		//var $qianmingDate=$("input[name='processData.qianmingDate']");
		//var $twoPage=$("input[name='processData.twoPage']");
		var processData={
			'id': $("#processDataId").val(),
			'cailiao':$cailiao.val(),
			//'shebeiName':$shebeiName.val(),
			'jizhun':$jizhun.val()
			//'jiaOrMoju':$jiaOrMoju.val(),
			//'suoyinNumb':$suoyinNumb.val(),
			//'danNumb':$danNumb.val(),
			//'qianming':$qianming.val(),
			//'qianmingDate':$qianmingDate.val(),
			//'twoPage':$twoPage.val()
		};
		return JSON.stringify(processData);
	}
	
	//添加作业规范
	function getOperationStandardParams(){
		var $idArr=$("input[name='operationStandardTable.id']");
		var $numbArr=$("input[name='operationStandardTable.numb']");
		var $contentArr=$("input[name='operationStandardTable.content']");
		var $processDataIdArr=$("input[name='operationStandardTable.processDataId']");
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
		var $idArr=$("input[name='detectionItemTable.id']");
		var $numbArr=$("input[name='detectionItemTable.numb']");
		var $xiangmuArr=$("input[name='detectionItemTable.xiangmu']");
		var $qijuArr=$("input[name='detectionItemTable.qiju']");
		var $caozuoPinciArr=$("input[name='detectionItemTable.caozuoPinci']");
		var $xunjianPinciArr=$("input[name='detectionItemTable.xunjianPinci']");
		var $processDataIdArr=$("input[name='detectionItemTable.processDataId']");
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
	
	//添加过程参数
	function getProcessGuochengCanshuParams(){
		var $idArr=$("input[name='processGuochengCanshu.id']");
		var $contentArr=$("input[name='processGuochengCanshu.content']");
		var processGuochengCanshuArr=new Array();
		for(var i=0;i<$idArr.length;i++){
			processGuochengCanshuArr[i]={	
							'id': $($idArr[i]).val(),
							'content':$($contentArr[i]).val(),
							'processDataId':$("#processDataId").val()
						  };
		}
		return JSON.stringify(processGuochengCanshuArr);
		
	}
	//********************************保存页面数据******************************************************
	$("#saveButton").bind("click",function(){
		var processDataParams=getProcessDataParams();
		var operationStandardParams=getOperationStandardParams();
		var detectionItemParams=getDetectionItemParams();
		var processGuochengCanshuParams=getProcessGuochengCanshuParams();
		$.ajax({
			type: "post",
			dataType: "json",
	        url: "gongyiGuichengAction!updateGongyiGuiChengGxsmlqPage.action",
	        data:{
				'processData.params': processDataParams,
				'operationStandard.params': operationStandardParams,
				'detectionItem.params': detectionItemParams,
				'processGuochengCanshu.params': processGuochengCanshuParams
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
	//*************************************************完成工序编辑****************************************************
	$('#finishButton').bind('click',function(){
		$.ajax({
			type: "post",
			dataType: "json",
	        url: "gongyiGuichengAction!finishProcessData.action",
	        data:{
				'processData.id': $('#processDataId').val()
			},
			async: false,
			success: function(data){
				var success=data.success;
				if(success){
					alert("完成工序编辑");
					window.location.reload();
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
});
</script>
