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
		<style>
<!--
 /* Font Definitions */
 @font-face
	{font-family:宋体;
	panose-1:2 1 6 0 3 1 1 1 1 1;}
@font-face
	{font-family:宋体;
	panose-1:2 1 6 0 3 1 1 1 1 1;}
@font-face
	{font-family:Calibri;
	panose-1:2 15 5 2 2 2 4 3 2 4;}
@font-face
	{font-family:"\@宋体";
	panose-1:2 1 6 0 3 1 1 1 1 1;}
 /* Style Definitions */
 p.MsoNormal, li.MsoNormal, div.MsoNormal
	{margin:0cm;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	font-size:10.5pt;
	font-family:"Calibri","sans-serif";}
p.MsoHeader, li.MsoHeader, div.MsoHeader
	{mso-style-link:"页眉 Char";
	margin:0cm;
	margin-bottom:.0001pt;
	text-align:center;
	layout-grid-mode:char;
	border:none;
	padding:0cm;
	font-size:9.0pt;
	font-family:"Calibri","sans-serif";}
p.MsoFooter, li.MsoFooter, div.MsoFooter
	{mso-style-link:"页脚 Char";
	margin:0cm;
	margin-bottom:.0001pt;
	layout-grid-mode:char;
	font-size:9.0pt;
	font-family:"Calibri","sans-serif";}
span.Char
	{mso-style-name:"页眉 Char";
	mso-style-link:页眉;}
span.Char0
	{mso-style-name:"页脚 Char";
	mso-style-link:页脚;}
.MsoChpDefault
	{font-family:"Calibri","sans-serif";}
 /* Page Definitions */
 @page WordSection1
	{size:21.0cm 841.95pt;
	margin:1.0cm 1.0cm 1.0cm 2.0cm;
	layout-grid:15.6pt;}
div.WordSection1
	{page:WordSection1;}
-->
</style>
	</head>
	<body style="text-align: center;">
			<div align="center">
				<!-- A4页面开始 height:1123px  height:1086px-->
				<div  style="width:794px;height:1123px;border:1px solid #000000;">
					<!-- 页边距内开始 -->
					<!-- 打印页边距设定为 5mm 时，网页内最大元素的分辨率：756×1086   5mm/18.89-->
					<div id="printDiv" style="width:756px;border:0px solid #000000;position: relative;top:19px;"> 
					<input type="hidden" id="gygcId" value="${gongyiGuicheng.id}"/>
					<input type="hidden" id="processDataId" value="${processData.id}"/>
					<table class=MsoTableGrid border=1 cellspacing=0 cellpadding=0
					 style='border-collapse:collapse;border:none'>
					 <tr style='height:19.85pt'>
					  <td width=87 colspan=4 rowspan=2 style='width:65.1pt;border:solid windowtext 1.0pt;
					  height:39.7pt'>
					  <img style="width: 100%;height: 100%;" src="<%=basePath%>${companyInfo.shhhjpg}"/>
					  </td>
					  <td width=128 colspan=5 rowspan=2 style='width:96.25pt;border:solid windowtext 1.0pt;
					  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-size:16.0pt;font-family:宋体'>工序图表</span></p>
					  </td>
					  <td width=76 colspan=5 style='width:2.0cm;border:solid windowtext 1.0pt;
					  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>型别</span></p>
					  </td>
					  <td width=104 colspan=5 style='width:77.95pt;border:solid windowtext 1.0pt;
					  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>件号</span></p>
					  </td>
					  <td width=95 colspan=5 style='width:70.9pt;border:solid windowtext 1.0pt;
					  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>件名</span></p>
					  </td>
					  <td width=113 colspan=5 style='width:85.0pt;border:solid windowtext 1.0pt;
					  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>工序号</span></p>
					  </td>
					  <td width=28 colspan=2 style='width:21.3pt;border:solid windowtext 1.0pt;
					  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>第</span></p>
					  </td>
					  <td width=35 colspan=2 style='width:26.6pt;border:solid windowtext 1.0pt;
					  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>1</span></p>
					  </td>
					  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-left:
					  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>页</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=76 colspan=5 style='width:2.0cm;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.xingbie}</span></p>
					  </td>
					  <td width=104 colspan=5 style='width:77.95pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.jianNumb}</span></p>
					  </td>
					  <td width=95 colspan=5 style='width:70.9pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.jianName}</span></p>
					  </td>
					  <td width=113 colspan=5 style='width:85.0pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${processData.gongxuNo}</span></p>
					  </td>
					  <td width=28 colspan=2 style='width:21.3pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>共</span></p>
					  </td>
					  <td width=35 colspan=2 style='width:26.6pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>1</span></p>
					  </td>
					  <td width=28 style='width:21.3pt;border-top:none;border-left:none;border-bottom:
					  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
					  height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>页</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=64 colspan=3 style='width:47.95pt;border:solid windowtext 1.0pt;
					  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>材料</span></p>
					  </td>
					  <td width=255 colspan=13 style='width:191.35pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${processData.cailiao}</span></p>
					  </td>
					  <td width=170 colspan=8 style='width:127.6pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>工序名称</span></p>
					  </td>
					  <td width=206 colspan=10 style='width:154.2pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${processData.gongxuName}</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=64 colspan=3 style='width:47.95pt;border:solid windowtext 1.0pt;
					  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>设备</span></p>
					  </td>
					  <td width=255 colspan=13 style='width:191.35pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${processData.shebeiName}</span></p>
					  </td>
					  <td width=170 colspan=8 rowspan=2 style='width:127.6pt;border-top:none;
					  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>夹</span><span lang=EN-US>/</span><span
					  style='font-family:宋体'>模具</span></p>
					  </td>
					  <td width=206 colspan=10 style='width:154.2pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${processData.gongzhuangName}</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=64 colspan=3 style='width:47.95pt;border:solid windowtext 1.0pt;
					  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>基准</span></p>
					  </td>
					  <td width=255 colspan=13 style='width:191.35pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${processData.jizhun}</span></p>
					  </td>
					  <td width=206 colspan=10 style='width:154.2pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:297.85pt'>
					  <td width=695 colspan=34 style='width:521.1pt;border:solid windowtext 1.0pt;
					  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:297.85pt'>
					  		<img id="processImg" style="width: 100%;height: 100%;" src="${processData.processImg}"/>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=45 style='width:33.75pt;border:solid windowtext 1.0pt;border-top:
					  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>项目</span></p>
					  </td>
					  <td width=650 colspan=33 style='width:487.35pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>作业规范</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=45 style='width:33.75pt;border:solid windowtext 1.0pt;border-top:
					  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationStandard.numb">&nbsp;</span></p>
					  </td>
					  <td width=650 colspan=33 style='width:487.35pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationStandard.content">&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=45 style='width:33.75pt;border:solid windowtext 1.0pt;border-top:
					  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationStandard.numb">&nbsp;</span></p>
					  </td>
					  <td width=650 colspan=33 style='width:487.35pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationStandard.content">&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=45 style='width:33.75pt;border:solid windowtext 1.0pt;border-top:
					  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationStandard.numb">&nbsp;</span></p>
					  </td>
					  <td width=650 colspan=33 style='width:487.35pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationStandard.content">&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=45 style='width:33.75pt;border:solid windowtext 1.0pt;border-top:
					  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationStandard.numb">&nbsp;</span></p>
					  </td>
					  <td width=650 colspan=33 style='width:487.35pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationStandard.content">&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=45 style='width:33.75pt;border:solid windowtext 1.0pt;border-top:
					  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>序号</span></p>
					  </td>
					  <td width=104 colspan=5 style='width:78.0pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>检测项目</span></p>
					  </td>
					  <td width=94 colspan=5 style='width:70.85pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>测量器具</span></p>
					  </td>
					  <td width=123 colspan=7 style='width:92.15pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>操作者测定频次</span></p>
					  </td>
					  <td width=113 colspan=5 style='width:3.0cm;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>巡检测定频次</span></p>
					  </td>
					  <td width=47 colspan=4 rowspan=5 style='width:35.45pt;border-top:none;
					  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;layout-flow:vertical-ideographic;height:19.85pt'>
					  <p class=MsoNormal align=center style='margin-top:0cm;margin-right:5.65pt;
					  margin-bottom:0cm;margin-left:5.65pt;margin-bottom:.0001pt;text-align:center'><span
					  style='font-family:宋体'>过程参数</span></p>
					  </td>
					  <td width=168 colspan=7 style='width:125.85pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=45 style='width:33.75pt;border:solid windowtext 1.0pt;border-top:
					  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.numb"></span></p>
					  </td>
					  <td width=104 colspan=5 style='width:78.0pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.xiangmu">&nbsp;</span></p>
					  </td>
					  <td width=94 colspan=5 style='width:70.85pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.qiju">&nbsp;</span></p>
					  </td>
					  <td width=123 colspan=7 style='width:92.15pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.caozuoPinci">&nbsp;</span></p>
					  </td>
					  <td width=113 colspan=5 style='width:3.0cm;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.xunjianPinci">&nbsp;</span></p>
					  </td>
					  <td width=168 colspan=7 style='width:125.85pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=45 style='width:33.75pt;border:solid windowtext 1.0pt;border-top:
					  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.numb"></span></p>
					  </td>
					  <td width=104 colspan=5 style='width:78.0pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.xiangmu">&nbsp;</span></p>
					  </td>
					  <td width=94 colspan=5 style='width:70.85pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.qiju">&nbsp;</span></p>
					  </td>
					  <td width=123 colspan=7 style='width:92.15pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.caozuoPinci">&nbsp;</span></p>
					  </td>
					  <td width=113 colspan=5 style='width:3.0cm;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.xunjianPinci">&nbsp;</span></p>
					  </td>
					  <td width=168 colspan=7 style='width:125.85pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=45 style='width:33.75pt;border:solid windowtext 1.0pt;border-top:
					  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.numb"></span></p>
					  </td>
					  <td width=104 colspan=5 style='width:78.0pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.xiangmu">&nbsp;</span></p>
					  </td>
					  <td width=94 colspan=5 style='width:70.85pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.qiju">&nbsp;</span></p>
					  </td>
					  <td width=123 colspan=7 style='width:92.15pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.caozuoPinci">&nbsp;</span></p>
					  </td>
					  <td width=113 colspan=5 style='width:3.0cm;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.xunjianPinci">&nbsp;</span></p>
					  </td>
					  <td width=168 colspan=7 style='width:125.85pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=45 style='width:33.75pt;border:solid windowtext 1.0pt;border-top:
					  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.numb"></span></p>
					  </td>
					  <td width=104 colspan=5 style='width:78.0pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.xiangmu">&nbsp;</span></p>
					  </td>
					  <td width=94 colspan=5 style='width:70.85pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.qiju">&nbsp;</span></p>
					  </td>
					  <td width=123 colspan=7 style='width:92.15pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.caozuoPinci">&nbsp;</span></p>
					  </td>
					  <td width=113 colspan=5 style='width:3.0cm;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.xunjianPinci">&nbsp;</span></p>
					  </td>
					  <td width=168 colspan=7 style='width:125.85pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=45 rowspan=3 style='width:33.75pt;border:solid windowtext 1.0pt;
					  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>更改</span></p>
					  </td>
					  <td width=161 colspan=7 style='width:120.5pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>索引号</span></p>
					  </td>
					  <td width=104 colspan=7 style='width:77.95pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
					  </td>
					  <td width=104 colspan=6 style='width:78.0pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
					  </td>
					  <td width=94 colspan=4 style='width:70.85pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
					  </td>
					  <td width=94 colspan=4 style='width:70.85pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
					  </td>
					  <td width=92 colspan=5 style='width:69.2pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=161 colspan=7 style='width:120.5pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>更改单号</span></p>
					  </td>
					  <td width=104 colspan=7 style='width:77.95pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
					  </td>
					  <td width=104 colspan=6 style='width:78.0pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
					  </td>
					  <td width=94 colspan=4 style='width:70.85pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
					  </td>
					  <td width=94 colspan=4 style='width:70.85pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
					  </td>
					  <td width=92 colspan=5 style='width:69.2pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=161 colspan=7 style='width:120.5pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>签名、日期</span></p>
					  </td>
					  <td width=104 colspan=7 style='width:77.95pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
					  </td>
					  <td width=104 colspan=6 style='width:78.0pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
					  </td>
					  <td width=94 colspan=4 style='width:70.85pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
					  </td>
					  <td width=94 colspan=4 style='width:70.85pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
					  </td>
					  <td width=92 colspan=5 style='width:69.2pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=58 colspan=2 style='width:43.4pt;border:solid windowtext 1.0pt;
					  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>编制</span></p>
					  </td>
					  <td width=58 colspan=3 style='width:43.45pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.bianzhiName}</span></p>
					  </td>
					  <td width=58 colspan=2 style='width:43.4pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US><s:date name="gongyiGuicheng.bianzhiDate"  format="yyyy-MM-dd"/></span></p>
					  </td>
					  <td width=58 colspan=3 style='width:43.45pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>校对</span></p>
					  </td>
					  <td width=58 colspan=3 style='width:43.4pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.jiaoduiName}</span></p>
					  </td>
					  <td width=58 colspan=4 style='width:43.45pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US><s:date name="gongyiGuicheng.jiaoduiDate"  format="yyyy-MM-dd"/></span></p>
					  </td>
					  <td width=58 colspan=3 style='width:43.4pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>审核</span></p>
					  </td>
					  <td width=58 colspan=2 style='width:43.45pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.shenheName}</span></p>
					  </td>
					  <td width=58 colspan=4 style='width:43.4pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US><s:date name="gongyiGuicheng.shenheDate"  format="yyyy-MM-dd"/></span></p>
					  </td>
					  <td width=58 colspan=2 style='width:43.45pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>批准</span></p>
					  </td>
					  <td width=58 colspan=4 style='width:43.4pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.pizhunName}</span></p>
					  </td>
					  <td width=58 colspan=2 style='width:43.45pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US><s:date name="gongyiGuicheng.pizhunDate"  format="yyyy-MM-dd"/></span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=58 colspan=2 style='width:43.4pt;border:solid windowtext 1.0pt;
					  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>会签</span></p>
					  </td>
					  <td width=116 colspan=5 style='width:86.85pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>生产部加工</span></p>
					  </td>
					  <td width=87 colspan=5 style='width:65.15pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.jiagongName}</span></p>
					  </td>
					  <td width=87 colspan=5 style='width:65.15pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US><s:date name="gongyiGuicheng.jiagongDate"  format="yyyy-MM-dd"/></span></p>
					  </td>
					  <td width=174 colspan=9 style='width:130.25pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>技术质保部品质</span></p>
					  </td>
					  <td width=87 colspan=4 style='width:65.15pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.pinzhiName}</span></p>
					  </td>
					  <td width=87 colspan=4 style='width:65.15pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US><s:date name="gongyiGuicheng.pinzhiDate "  format="yyyy-MM-dd"/></span></p>
					  </td>
					 </tr>
					 <tr height=0>
					  <td width=45 style='border:none'></td>
					  <td width=13 style='border:none'></td>
					  <td width=6 style='border:none'></td>
					  <td width=23 style='border:none'></td>
					  <td width=29 style='border:none'></td>
					  <td width=33 style='border:none'></td>
					  <td width=25 style='border:none'></td>
					  <td width=32 style='border:none'></td>
					  <td width=9 style='border:none'></td>
					  <td width=16 style='border:none'></td>
					  <td width=12 style='border:none'></td>
					  <td width=17 style='border:none'></td>
					  <td width=29 style='border:none'></td>
					  <td width=1 style='border:none'></td>
					  <td width=19 style='border:none'></td>
					  <td width=9 style='border:none'></td>
					  <td width=28 style='border:none'></td>
					  <td width=19 style='border:none'></td>
					  <td width=28 style='border:none'></td>
					  <td width=11 style='border:none'></td>
					  <td width=8 style='border:none'></td>
					  <td width=50 style='border:none'></td>
					  <td width=17 style='border:none'></td>
					  <td width=9 style='border:none'></td>
					  <td width=19 style='border:none'></td>
					  <td width=13 style='border:none'></td>
					  <td width=6 style='border:none'></td>
					  <td width=52 style='border:none'></td>
					  <td width=24 style='border:none'></td>
					  <td width=5 style='border:none'></td>
					  <td width=23 style='border:none'></td>
					  <td width=6 style='border:none'></td>
					  <td width=30 style='border:none'></td>
					  <td width=28 style='border:none'></td>
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
				
				</form>
				<table style="width: 100%;text-align: center;border: 0px;">
					<tr>
						<td><input type="button" id="printButton" value="打印" class="input" onclick="pagePrint('printDiv')" /></td>
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
	//*************************************下边初始化表格******************************************************
	
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
				var operationStandardArr=data.data;
				var $numbArr=$("span[name='operationStandard.numb']");
				var $contentArr=$("span[name='operationStandard.content']");
				$(operationStandardArr).each(function(i,n){
					$numbArr.eq(i).text(this.numb);
					$contentArr.eq(i).text(this.content);
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
				var detectionItemArr=data.data;
				var $numbArr=$("span[name='detectionItem.numb']");
				var $xiangmuArr=$("span[name='detectionItem.xiangmu']");
				var $qijuArr=$("span[name='detectionItem.qiju']");
				var $caozuoPinciArr=$("span[name='detectionItem.caozuoPinci']");
				var $xunjianPinciArr=$("span[name='detectionItem.xunjianPinci']");
				$(detectionItemArr).each(function(i,n){
					$numbArr.eq(i).text(this.numb);
					$xiangmuArr.eq(i).text(this.xiangmu);
					$qijuArr.eq(i).text(this.qiju);
					$caozuoPinciArr.eq(i).text(this.caozuoPinci);
					$xunjianPinciArr.eq(i).text(this.xunjianPinci);
				});
			}
		}
	});
//*************************末尾***************************
});
</script>
