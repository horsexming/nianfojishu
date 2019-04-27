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
	{size:42.0cm 841.95pt;
	margin:1.0cm 1.0cm 1.0cm 2.0cm;
	layout-grid:15.6pt;}
div.WordSection1
	{page:WordSection1;}
-->
</style>
		
	</head>
	<body style="text-align: center;">
			<div align="center" style="width: 100%;">
				<!-- A4页面开始 -->
				<div  style="width:1587px;height:1122px;border:1px solid #000000;">
					<!-- 页边距内开始 -->
					<!-- 打印页边距设定为 5mm 时，网页内最大元素的分辨率：1549×1084   5mm/18.89  19-->
					<div id="printDiv" style="width:1549px;border:0px solid #000000;position: relative;top:19px;"> 
					<input type="hidden" id="gygcId" value="${gongyiGuicheng.id}"/>
					<input type="hidden" id="processDataId" value="${processData.id}"/>
					<table class=MsoTableGrid border=1 cellspacing=0 cellpadding=0 width=1474
						 style='width:39.0cm;border-collapse:collapse;border:none'>
						 <tr style='height:19.85pt'>
						  <td width=66 colspan=2 rowspan=2 style='width:49.75pt;border:solid windowtext 1.0pt;
						  height:39.7pt'>
						  <img style="height: 80px;" src="<%=basePath%>${companyInfo.shhhjpg}"/>
						  </td>
						  <td width=222 colspan=4 rowspan=2 style='width:166.4pt;border:solid windowtext 1.0pt;
						  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-size:24.0pt;font-family:宋体'>工序图表</span></p>
						  </td>
						  <td width=150 colspan=3 style='width:112.55pt;border:solid windowtext 1.0pt;
						  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>型别</span></p>
						  </td>
						  <td width=150 colspan=3 style='width:112.35pt;border:solid windowtext 1.0pt;
						  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>件号</span></p>
						  </td>
						  <td width=269 colspan=3 style='width:201.45pt;border:solid windowtext 1.0pt;
						  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>件名</span></p>
						  </td>
						  <td width=169 colspan=3 style='width:126.6pt;border:solid windowtext 1.0pt;
						  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>工序名称</span></p>
						  </td>
						  <td width=136 colspan=5 style='width:101.9pt;border:solid windowtext 1.0pt;
						  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>工序号</span></p>
						  </td>
						  <td width=197 colspan=3 style='width:147.5pt;border:solid windowtext 1.0pt;
						  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>材料</span></p>
						  </td>
						  <td width=38 colspan=2 style='width:28.5pt;border:solid windowtext 1.0pt;
						  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>第</span></p>
						  </td>
						  <td width=39 style='width:29.45pt;border:solid windowtext 1.0pt;border-left:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>1</span></p>
						  </td>
						  <td width=39 style='width:29.2pt;border:solid windowtext 1.0pt;border-left:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>页</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=150 colspan=3 style='width:112.55pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.xingbie}</span></p>
						  </td>
						  <td width=150 colspan=3 style='width:112.35pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.jianNumb}</span></p>
						  </td>
						  <td width=269 colspan=3 style='width:201.45pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.jianName}</span></p>
						  </td>
						  <td width=169 colspan=3 style='width:126.6pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${processData.gongxuName}</span></p>
						  </td>
						  <td width=136 colspan=5 style='width:101.9pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${processData.gongxuNo}</span></p>
						  </td>
						  <td width=197 colspan=3 style='width:147.5pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${processData.cailiao}</span></p>
						  </td>
						  <td width=38 colspan=2 style='width:28.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>共</span></p>
						  </td>
						  <td width=39 style='width:29.45pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>1</span></p>
						  </td>
						  <td width=39 style='width:29.2pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>页</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=143 colspan=4 style='width:107.4pt;border:solid windowtext 1.0pt;
						  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>设备</span></p>
						  </td>
						  <td width=713 colspan=11 style='width:535.1pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal><span lang=EN-US>${processData.shebeiName}</span></p>
						  </td>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>项目</span></p>
						  </td>
						  <td width=568 colspan=14 style='width:425.75pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>作业规范</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=143 colspan=4 style='width:107.4pt;border:solid windowtext 1.0pt;
						  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>基准</span></p>
						  </td>
						  <td width=713 colspan=11 style='width:535.1pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal><span lang=EN-US>${processData.jizhun}</span></p>
						  </td>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationStandard.numb"></span></p>
						  </td>
						  <td width=568 colspan=14 style='width:425.75pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal><span lang=EN-US name="operationStandard.content"></span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=143 colspan=4 style='width:107.4pt;border:solid windowtext 1.0pt;
						  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>夹</span><span lang=EN-US>/</span><span
						  style='font-family:宋体'>模具</span></p>
						  </td>
						  <td width=713 colspan=11 style='width:535.1pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal><span lang=EN-US>${processData.gongzhuangName}</span></p>
						  </td>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationStandard.numb"></span></p>
						  </td>
						  <td width=568 colspan=14 style='width:425.75pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal><span lang=EN-US name="operationStandard.content">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr >
						  <td width=857 colspan=15 rowspan=27 style='width:642.5pt;border:solid windowtext 1.0pt;
						  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:534.6pt'>
						  <img id="processImg" style="width: 100%;height: 100%;" src="${processData.processImg}"/>
						  </td>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationStandard.numb"></span></p>
						  </td>
						  <td width=568 colspan=14 style='width:425.75pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal><span lang=EN-US name="operationStandard.content">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationStandard.numb"></span></p>
						  </td>
						  <td width=568 colspan=14 style='width:425.75pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal><span lang=EN-US name="operationStandard.content">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationStandard.numb"></span></p>
						  </td>
						  <td width=568 colspan=14 style='width:425.75pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal><span lang=EN-US name="operationStandard.content">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationStandard.numb"></span></p>
						  </td>
						  <td width=568 colspan=14 style='width:425.75pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal><span lang=EN-US name="operationStandard.content">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationStandard.numb"></span></p>
						  </td>
						  <td width=568 colspan=14 style='width:425.75pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal><span lang=EN-US name="operationStandard.content">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationStandard.numb"></span></p>
						  </td>
						  <td width=568 colspan=14 style='width:425.75pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal><span lang=EN-US name="operationStandard.content">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationStandard.numb"></span></p>
						  </td>
						  <td width=568 colspan=14 style='width:425.75pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal><span lang=EN-US name="operationStandard.content">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationStandard.numb"></span></p>
						  </td>
						  <td width=568 colspan=14 style='width:425.75pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal><span lang=EN-US name="operationStandard.content">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationStandard.numb"></span></p>
						  </td>
						  <td width=568 colspan=14 style='width:425.75pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal><span lang=EN-US name="operationStandard.content">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationStandard.numb"></span></p>
						  </td>
						  <td width=568 colspan=14 style='width:425.75pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal><span lang=EN-US name="operationStandard.content">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationStandard.numb"></span></p>
						  </td>
						  <td width=568 colspan=14 style='width:425.75pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal><span lang=EN-US name="operationStandard.content">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationStandard.numb"></span></p>
						  </td>
						  <td width=568 colspan=14 style='width:425.75pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal><span lang=EN-US name="operationStandard.content">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationStandard.numb"></span></p>
						  </td>
						  <td width=568 colspan=14 style='width:425.75pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal><span lang=EN-US name="operationStandard.content">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationStandard.numb"></span></p>
						  </td>
						  <td width=568 colspan=14 style='width:425.75pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal><span lang=EN-US name="operationStandard.content">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=50 rowspan=4 style='width:37.4pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;layout-flow:vertical-ideographic;height:19.85pt'>
						  <p class=MsoNormal align=center style='margin-top:0cm;margin-right:5.65pt;
						  margin-bottom:0cm;margin-left:5.65pt;margin-bottom:.0001pt;text-align:center'><span
						  style='font-family:宋体;letter-spacing:2.0pt'>过程参数</span></p>
						  </td>
						  <td width=568 colspan=14 style='width:425.75pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal><span lang=EN-US name="processGuochengCanshu.content">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=568 colspan=14 style='width:425.75pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal><span lang=EN-US name="processGuochengCanshu.content">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=568 colspan=14 style='width:425.75pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal><span lang=EN-US name="processGuochengCanshu.content">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=568 colspan=14 style='width:425.75pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal><span lang=EN-US name="processGuochengCanshu.content">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>序号</span></p>
						  </td>
						  <td width=160 colspan=4 style='width:119.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>检测项目</span></p>
						  </td>
						  <td width=150 colspan=4 style='width:112.5pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>测量器具</span></p>
						  </td>
						  <td width=142 colspan=2 style='width:106.15pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>操作者测定频次</span></p>
						  </td>
						  <td width=116 colspan=4 style='width:87.15pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>巡检测定频次</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.numb">&nbsp;</span></p>
						  </td>
						  <td width=160 colspan=4 style='width:119.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=150 colspan=4 style='width:112.5pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=142 colspan=2 style='width:106.15pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.caozuoPinci">&nbsp;</span></p>
						  </td>
						  <td width=116 colspan=4 style='width:87.15pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.xunjianPinci">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.numb">&nbsp;</span></p>
						  </td>
						  <td width=160 colspan=4 style='width:119.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=150 colspan=4 style='width:112.5pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=142 colspan=2 style='width:106.15pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.caozuoPinci">&nbsp;</span></p>
						  </td>
						  <td width=116 colspan=4 style='width:87.15pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.xunjianPinci">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.numb">&nbsp;</span></p>
						  </td>
						  <td width=160 colspan=4 style='width:119.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=150 colspan=4 style='width:112.5pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=142 colspan=2 style='width:106.15pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.caozuoPinci">&nbsp;</span></p>
						  </td>
						  <td width=116 colspan=4 style='width:87.15pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.xunjianPinci">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.numb">&nbsp;</span></p>
						  </td>
						  <td width=160 colspan=4 style='width:119.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=150 colspan=4 style='width:112.5pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=142 colspan=2 style='width:106.15pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.caozuoPinci">&nbsp;</span></p>
						  </td>
						  <td width=116 colspan=4 style='width:87.15pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.xunjianPinci">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.numb">&nbsp;</span></p>
						  </td>
						  <td width=160 colspan=4 style='width:119.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=150 colspan=4 style='width:112.5pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=142 colspan=2 style='width:106.15pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.caozuoPinci">&nbsp;</span></p>
						  </td>
						  <td width=116 colspan=4 style='width:87.15pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.xunjianPinci">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.numb">&nbsp;</span></p>
						  </td>
						  <td width=160 colspan=4 style='width:119.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=150 colspan=4 style='width:112.5pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=142 colspan=2 style='width:106.15pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.caozuoPinci">&nbsp;</span></p>
						  </td>
						  <td width=116 colspan=4 style='width:87.15pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.xunjianPinci">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.numb">&nbsp;</span></p>
						  </td>
						  <td width=160 colspan=4 style='width:119.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=150 colspan=4 style='width:112.5pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=142 colspan=2 style='width:106.15pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.caozuoPinci">&nbsp;</span></p>
						  </td>
						  <td width=116 colspan=4 style='width:87.15pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.xunjianPinci">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=50 style='width:37.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.numb">&nbsp;</span></p>
						  </td>
						  <td width=160 colspan=4 style='width:119.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=150 colspan=4 style='width:112.5pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=142 colspan=2 style='width:106.15pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.caozuoPinci">&nbsp;</span></p>
						  </td>
						  <td width=116 colspan=4 style='width:87.15pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="detectionItem.xunjianPinci">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=38 rowspan=3 style='width:28.3pt;border:solid windowtext 1.0pt;
						  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;layout-flow:vertical-ideographic;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='margin-top:0cm;margin-right:5.65pt;
						  margin-bottom:0cm;margin-left:5.65pt;margin-bottom:.0001pt;text-align:center'><span
						  style='font-family:宋体;letter-spacing:2.0pt'>更改</span></p>
						  </td>
						  <td width=100 colspan=2 style='width:74.9pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>索引号</span></p>
						  </td>
						  <td width=90 colspan=2 style='width:67.4pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=90 colspan=2 style='width:67.4pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=90 style='width:67.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=90 colspan=2 style='width:67.45pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=90 style='width:67.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=90 colspan=2 style='width:67.4pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=90 style='width:67.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=90 style='width:67.45pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=80 colspan=2 style='width:60.0pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>编制</span></p>
						  </td>
						  <td width=91 colspan=2 style='width:68.35pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.bianzhiName}</span></p>
						  </td>
						  <td width=95 colspan=2 style='width:70.9pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US><s:date name="gongyiGuicheng.bianzhiDate"  format="yyyy-MM-dd"/></span></p>
						  </td>
						  <td width=174 colspan=4 style='width:130.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>批准</span></p>
						  </td>
						  <td width=91 colspan=2 style='width:67.9pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.pizhunName}</span></p>
						  </td>
						  <td width=88 colspan=3 style='width:65.7pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US><s:date name="gongyiGuicheng.pizhunDate"  format="yyyy-MM-dd"/></span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=100 colspan=2 style='width:74.9pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>更改单号</span></p>
						  </td>
						  <td width=90 colspan=2 style='width:67.4pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=90 colspan=2 style='width:67.4pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=90 style='width:67.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=90 colspan=2 style='width:67.45pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=90 style='width:67.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=90 colspan=2 style='width:67.4pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=90 style='width:67.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=90 style='width:67.45pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=80 colspan=2 style='width:60.0pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>校对</span></p>
						  </td>
						  <td width=91 colspan=2 style='width:68.35pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.jiaoduiName}</span></p>
						  </td>
						  <td width=95 colspan=2 style='width:70.9pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US><s:date name="gongyiGuicheng.jiaoduiDate"  format="yyyy-MM-dd"/></span></p>
						  </td>
						  <td width=28 rowspan=2 style='width:21.25pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>会签</span></p>
						  </td>
						  <td width=145 colspan=3 style='width:109.05pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>生产部加工</span></p>
						  </td>
						  <td width=91 colspan=2 style='width:67.9pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.jiagongName}</span></p>
						  </td>
						  <td width=88 colspan=3 style='width:65.7pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US><s:date name="gongyiGuicheng.jiagongDate"  format="yyyy-MM-dd"/></span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=100 colspan=2 style='width:74.9pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>签名日期</span></p>
						  </td>
						  <td width=90 colspan=2 style='width:67.4pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=90 colspan=2 style='width:67.4pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=90 style='width:67.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=90 colspan=2 style='width:67.45pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=90 style='width:67.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=90 colspan=2 style='width:67.4pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=90 style='width:67.4pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=90 style='width:67.45pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=80 colspan=2 style='width:60.0pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>审核</span></p>
						  </td>
						  <td width=91 colspan=2 style='width:68.35pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.shenheName}</span></p>
						  </td>
						  <td width=95 colspan=2 style='width:70.9pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US><s:date name="gongyiGuicheng.shenheDate"  format="yyyy-MM-dd"/></span></p>
						  </td>
						  <td width=145 colspan=3 style='width:109.05pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>技术质保部品质</span></p>
						  </td>
						  <td width=91 colspan=2 style='width:67.9pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.pinzhiName}</span></p>
						  </td>
						  <td width=88 colspan=3 style='width:65.7pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US><s:date name="gongyiGuicheng.pinzhiDate "  format="yyyy-MM-dd"/></span></p>
						  </td>
						 </tr>
						 <tr height=0>
						  <td width=38 style='border:none'></td>
						  <td width=29 style='border:none'></td>
						  <td width=71 style='border:none'></td>
						  <td width=6 style='border:none'></td>
						  <td width=84 style='border:none'></td>
						  <td width=61 style='border:none'></td>
						  <td width=29 style='border:none'></td>
						  <td width=90 style='border:none'></td>
						  <td width=31 style='border:none'></td>
						  <td width=59 style='border:none'></td>
						  <td width=90 style='border:none'></td>
						  <td width=1 style='border:none'></td>
						  <td width=89 style='border:none'></td>
						  <td width=90 style='border:none'></td>
						  <td width=90 style='border:none'></td>
						  <td width=50 style='border:none'></td>
						  <td width=30 style='border:none'></td>
						  <td width=89 style='border:none'></td>
						  <td width=2 style='border:none'></td>
						  <td width=39 style='border:none'></td>
						  <td width=56 style='border:none'></td>
						  <td width=28 style='border:none'></td>
						  <td width=11 style='border:none'></td>
						  <td width=55 style='border:none'></td>
						  <td width=80 style='border:none'></td>
						  <td width=62 style='border:none'></td>
						  <td width=29 style='border:none'></td>
						  <td width=9 style='border:none'></td>
						  <td width=39 style='border:none'></td>
						  <td width=39 style='border:none'></td>
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
				
				</form>
				<table style="width: 100%;text-align: center;border: 0px;">
					<tr>
						<td><input type="button" id="printButton" value="打印" class="input" onclick="pagePrint('printDiv')" /><input type="button" id="finishButton" value="完成工序编辑" class="input" style="width: 120px;"/></td>
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
				var $numb=$("span[name='operationStandard.numb']");
				var $content=$("span[name='operationStandard.content']");
				var operationStandardArr=data.data;
				$(operationStandardArr).each(function(i,n){
					$numb.eq(i).text(this.numb);
					$content.eq(i).text(this.content);
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
				var $idArr=$("span[name='detectionItem.id']");
				var $numbArr=$("span[name='detectionItem.numb']");
				var $xiangmuArr=$("span[name='detectionItem.xiangmu']");
				var $qijuArr=$("span[name='detectionItem.qiju']");
				var $caozuoPinciArr=$("span[name='detectionItem.caozuoPinci']");
				var $xunjianPinciArr=$("span[name='detectionItem.xunjianPinci']");
				var detectionItemArr=data.data;
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
				var $content=$("span[name='processGuochengCanshu.content']");
				$(processGuochengCanshuList).each(function(i,n){
					$content.eq(i).text(this.content);
				});
			}
		}
	});
});
</script>
