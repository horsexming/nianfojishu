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
				<!-- A4页面开始 -->
				<div  style="width:794px;height:1123px;border:1px solid #000000;">
					<!-- 页边距内开始 -->
					<!-- 打印页边距设定为 5mm 时，网页内最大元素的分辨率：756×1086   5mm/18.89-->
					<div id="printDiv" style="width:756px;border:0px solid #000000;position: relative;top:19px;"> 
					<input type="hidden" id="gygcId" value="${gongyiGuicheng.id}"/>
					<input type="hidden" id="processDataId" value="${processData.id}"/>
					<table class=MsoTableGrid border=1 cellspacing=0 cellpadding=0
					 style='border-collapse:collapse;border:none'>
					 <tr style='height:31.4pt'>
					  <td width=87 colspan=3 rowspan=2 style='width:65.1pt;border:solid windowtext 1.0pt;
					  height:31.4pt'>
					  <img style="width: 100%;height: 100%;" src="<%=basePath%>${companyInfo.shhhjpg}"/>
					  </td>
					  <td width=128 colspan=4 rowspan=2 style='width:96.25pt;border:solid windowtext 1.0pt;
					  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:31.4pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-size:16.0pt;font-family:宋体'>毛料图表<br>(${processData.maoliaoType})</span></p>
					  </td>
					  <td width=76 colspan=4 style='width:2.0cm;border:solid windowtext 1.0pt;
					  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:31.4pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>型别</span></p>
					  </td>
					  <td width=104 colspan=4 style='width:77.95pt;border:solid windowtext 1.0pt;
					  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:31.4pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>件号</span></p>
					  </td>
					  <td width=95 colspan=4 style='width:70.9pt;border:solid windowtext 1.0pt;
					  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:31.4pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>件名</span></p>
					  </td>
					  <td width=113 colspan=4 style='width:85.0pt;border:solid windowtext 1.0pt;
					  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:31.4pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>工序号</span></p>
					  </td>
					  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-left:
					  none;padding:0cm 5.4pt 0cm 5.4pt;height:31.4pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>第</span></p>
					  </td>
					  <td width=35 colspan=2 style='width:26.6pt;border:solid windowtext 1.0pt;
					  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:31.4pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>1</span></p>
					  </td>
					  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-left:
					  none;padding:0cm 5.4pt 0cm 5.4pt;height:31.4pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>页</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=76 colspan=4 style='width:2.0cm;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.xingbie}</span></p>
					  </td>
					  <td width=104 colspan=4 style='width:77.95pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.jianNumb}</span></p>
					  </td>
					  <td width=95 colspan=4 style='width:70.9pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.jianName}</span></p>
					  </td>
					  <td width=113 colspan=4 style='width:85.0pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${processData.gongxuNo}</span></p>
					  </td>
					  <td width=28 style='width:21.3pt;border-top:none;border-left:none;border-bottom:
					  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
					  height:19.85pt'>
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
					  <td width=36 rowspan=2 style='width:26.7pt;border:solid windowtext 1.0pt;
					  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>材<br/>料</span></p>
					  </td>
					  <td width=66 colspan=3 style='width:49.6pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>牌号</span></p>
					  </td>
					  <td width=161 colspan=5 style='width:120.5pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${processData.paihao}</span></p>
					  </td>
					  <td width=85 colspan=4 style='width:63.75pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>分类</span></p>
					  </td>
					  <td width=174 colspan=7 style='width:130.25pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${processData.fenlei}</span></p>
					  </td>
					  <td width=174 colspan=7 style='width:130.3pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>材料设备</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=66 colspan=3 style='width:49.6pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>厚度</span></p>
					  </td>
					  <td width=161 colspan=5 style='width:120.5pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${processData.houdu}</span></p>
					  </td>
					  <td width=85 colspan=4 style='width:63.75pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>技术条件</span></p>
					  </td>
					  <td width=174 colspan=7 style='width:130.25pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${processData.jishuTiaojian}</span></p>
					  </td>
					  <td width=174 colspan=7 style='width:130.3pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${processData.qieliaoShebei}</span></p>
					  </td>
					 </tr>
					 <tr style='height:292.8pt'>
					  <td width=695 colspan=27 style='width:521.1pt;border:solid windowtext 1.0pt;
					  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:292.8pt;text-align: center;'>
					  	<img id="processImg" style="height: 100%;" src="${processData.processImg}"/>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=58 colspan=2 style='width:43.4pt;border:solid windowtext 1.0pt;
					  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>项目</span></p>
					  </td>
					  <td width=280 colspan=10 style='width:210.1pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>内容</span></p>
					  </td>
					  <td width=94 colspan=5 style='width:70.85pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>单位</span></p>
					  </td>
					  <td width=100 colspan=4 style='width:74.9pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>数值</span></p>
					  </td>
					  <td width=162 colspan=6 style='width:121.85pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>备注</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=58 colspan=2 style='width:43.4pt;border:solid windowtext 1.0pt;
					  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.numb">&nbsp;</span></p>
					  </td>
					  <td width=280 colspan=10 style='width:210.1pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.content">&nbsp;</span></p>
					  </td>
					  <td width=94 colspan=5 style='width:70.85pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.danwei">&nbsp;</span></p>
					  </td>
					  <td width=100 colspan=4 style='width:74.9pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.shuzhi">&nbsp;</span></p>
					  </td>
					  <td width=162 colspan=6 style='width:121.85pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.remark">&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=58 colspan=2 style='width:43.4pt;border:solid windowtext 1.0pt;
					  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.numb">&nbsp;</span></p>
					  </td>
					  <td width=280 colspan=10 style='width:210.1pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.content">&nbsp;</span></p>
					  </td>
					  <td width=94 colspan=5 style='width:70.85pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.danwei">&nbsp;</span></p>
					  </td>
					  <td width=100 colspan=4 style='width:74.9pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.shuzhi">&nbsp;</span></p>
					  </td>
					  <td width=162 colspan=6 style='width:121.85pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.remark">&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=58 colspan=2 style='width:43.4pt;border:solid windowtext 1.0pt;
					  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.numb">&nbsp;</span></p>
					  </td>
					  <td width=280 colspan=10 style='width:210.1pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.content">&nbsp;</span></p>
					  </td>
					  <td width=94 colspan=5 style='width:70.85pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.danwei">&nbsp;</span></p>
					  </td>
					  <td width=100 colspan=4 style='width:74.9pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.shuzhi">&nbsp;</span></p>
					  </td>
					  <td width=162 colspan=6 style='width:121.85pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.remark">&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=58 colspan=2 style='width:43.4pt;border:solid windowtext 1.0pt;
					  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.numb">&nbsp;</span></p>
					  </td>
					  <td width=280 colspan=10 style='width:210.1pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.content">&nbsp;</span></p>
					  </td>
					  <td width=94 colspan=5 style='width:70.85pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.danwei">&nbsp;</span></p>
					  </td>
					  <td width=100 colspan=4 style='width:74.9pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.shuzhi">&nbsp;</span></p>
					  </td>
					  <td width=162 colspan=6 style='width:121.85pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.remark">&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=58 colspan=2 style='width:43.4pt;border:solid windowtext 1.0pt;
					  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.numb">&nbsp;</span></p>
					  </td>
					  <td width=280 colspan=10 style='width:210.1pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.content">&nbsp;</span></p>
					  </td>
					  <td width=94 colspan=5 style='width:70.85pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.danwei">&nbsp;</span></p>
					  </td>
					  <td width=100 colspan=4 style='width:74.9pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.shuzhi">&nbsp;</span></p>
					  </td>
					  <td width=162 colspan=6 style='width:121.85pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.remark">&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=58 colspan=2 style='width:43.4pt;border:solid windowtext 1.0pt;
					  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.numb">&nbsp;</span></p>
					  </td>
					  <td width=280 colspan=10 style='width:210.1pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.content">&nbsp;</span></p>
					  </td>
					  <td width=94 colspan=5 style='width:70.85pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.danwei">&nbsp;</span></p>
					  </td>
					  <td width=100 colspan=4 style='width:74.9pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.shuzhi">&nbsp;</span></p>
					  </td>
					  <td width=162 colspan=6 style='width:121.85pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.remark">&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=58 colspan=2 style='width:43.4pt;border:solid windowtext 1.0pt;
					  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.numb">&nbsp;</span></p>
					  </td>
					  <td width=280 colspan=10 style='width:210.1pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.content">&nbsp;</span></p>
					  </td>
					  <td width=94 colspan=5 style='width:70.85pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.danwei">&nbsp;</span></p>
					  </td>
					  <td width=100 colspan=4 style='width:74.9pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.shuzhi">&nbsp;</span></p>
					  </td>
					  <td width=162 colspan=6 style='width:121.85pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.remark">&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=58 colspan=2 style='width:43.4pt;border:solid windowtext 1.0pt;
					  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.numb">&nbsp;</span></p>
					  </td>
					  <td width=280 colspan=10 style='width:210.1pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.content">&nbsp;</span></p>
					  </td>
					  <td width=94 colspan=5 style='width:70.85pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.danwei">&nbsp;</span></p>
					  </td>
					  <td width=100 colspan=4 style='width:74.9pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.shuzhi">&nbsp;</span></p>
					  </td>
					  <td width=162 colspan=6 style='width:121.85pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.remark">&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=58 colspan=2 style='width:43.4pt;border:solid windowtext 1.0pt;
					  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.numb">&nbsp;</span></p>
					  </td>
					  <td width=280 colspan=10 style='width:210.1pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.content">&nbsp;</span></p>
					  </td>
					  <td width=94 colspan=5 style='width:70.85pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.danwei">&nbsp;</span></p>
					  </td>
					  <td width=100 colspan=4 style='width:74.9pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.shuzhi">&nbsp;</span></p>
					  </td>
					  <td width=162 colspan=6 style='width:121.85pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.remark">&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=58 colspan=2 style='width:43.4pt;border:solid windowtext 1.0pt;
					  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.numb">&nbsp;</span></p>
					  </td>
					  <td width=280 colspan=10 style='width:210.1pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.content">&nbsp;</span></p>
					  </td>
					  <td width=94 colspan=5 style='width:70.85pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.danwei">&nbsp;</span></p>
					  </td>
					  <td width=100 colspan=4 style='width:74.9pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.shuzhi">&nbsp;</span></p>
					  </td>
					  <td width=162 colspan=6 style='width:121.85pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.remark">&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=58 colspan=2 style='width:43.4pt;border:solid windowtext 1.0pt;
					  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.numb">&nbsp;</span></p>
					  </td>
					  <td width=280 colspan=10 style='width:210.1pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.content">&nbsp;</span></p>
					  </td>
					  <td width=94 colspan=5 style='width:70.85pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.danwei">&nbsp;</span></p>
					  </td>
					  <td width=100 colspan=4 style='width:74.9pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.shuzhi">&nbsp;</span></p>
					  </td>
					  <td width=162 colspan=6 style='width:121.85pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.remark">&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=58 colspan=2 style='width:43.4pt;border:solid windowtext 1.0pt;
					  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.numb">&nbsp;</span></p>
					  </td>
					  <td width=280 colspan=10 style='width:210.1pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.content">&nbsp;</span></p>
					  </td>
					  <td width=94 colspan=5 style='width:70.85pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.danwei">&nbsp;</span></p>
					  </td>
					  <td width=100 colspan=4 style='width:74.9pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.shuzhi">&nbsp;</span></p>
					  </td>
					  <td width=162 colspan=6 style='width:121.85pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoParam.remark">&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=36 rowspan=3 style='width:26.7pt;border:solid windowtext 1.0pt;
					  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>技<br/>术<br/>条<br/>件</span></p>
					  </td>
					  <td width=312 colspan=12 style='width:233.85pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoJishuTiaojian.content">&nbsp;</span></p>
					  </td>
					  <td width=38 rowspan=3 style='width:1.0cm;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>更<br/>改</span></p>
					  </td>
					  <td width=104 colspan=5 style='width:78.0pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>索引号</span></p>
					  </td>
					  <td width=206 colspan=8 style='width:154.2pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=312 colspan=12 style='width:233.85pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoJishuTiaojian.content">&nbsp;</span></p>
					  </td>
					  <td width=104 colspan=5 style='width:78.0pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>更改单号</span></p>
					  </td>
					  <td width=206 colspan=8 style='width:154.2pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
					  </td>
					 </tr>
					 <tr style='height:19.85pt'>
					  <td width=312 colspan=12 style='width:233.85pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="maoliaoJishuTiaojian.content">&nbsp;</span></p>
					  </td>
					  <td width=104 colspan=5 style='width:78.0pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>签名、日期</span></p>
					  </td>
					  <td width=206 colspan=8 style='width:154.2pt;border-top:none;border-left:
					  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
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
					  <td width=58 style='width:43.4pt;border-top:none;border-left:none;border-bottom:
					  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
					  height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US><s:date name="gongyiGuicheng.bianzhiDate"  format="yyyy-MM-dd"/></span></p>
					  </td>
					  <td width=58 colspan=2 style='width:43.45pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span
					  style='font-family:宋体'>校对</span></p>
					  </td>
					  <td width=58 colspan=2 style='width:43.4pt;border-top:none;border-left:none;
					  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
					  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
					  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.jiaoduiName}</span></p>
					  </td>
					  <td width=58 colspan=3 style='width:43.45pt;border-top:none;border-left:none;
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
					  <td width=58 colspan=2 style='width:43.4pt;border-top:none;border-left:none;
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
					  <td width=58 colspan=3 style='width:43.4pt;border-top:none;border-left:none;
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
					 <tr height=0>
					  <td width=36 style='border:none'></td>
					  <td width=22 style='border:none'></td>
					  <td width=29 style='border:none'></td>
					  <td width=15 style='border:none'></td>
					  <td width=14 style='border:none'></td>
					  <td width=58 style='border:none'></td>
					  <td width=41 style='border:none'></td>
					  <td width=16 style='border:none'></td>
					  <td width=31 style='border:none'></td>
					  <td width=27 style='border:none'></td>
					  <td width=1 style='border:none'></td>
					  <td width=47 style='border:none'></td>
					  <td width=9 style='border:none'></td>
					  <td width=38 style='border:none'></td>
					  <td width=9 style='border:none'></td>
					  <td width=11 style='border:none'></td>
					  <td width=27 style='border:none'></td>
					  <td width=31 style='border:none'></td>
					  <td width=26 style='border:none'></td>
					  <td width=32 style='border:none'></td>
					  <td width=11 style='border:none'></td>
					  <td width=47 style='border:none'></td>
					  <td width=24 style='border:none'></td>
					  <td width=28 style='border:none'></td>
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
				<form id="markingForm" method="post">
					<input type="hidden" name="gongyiGuichengScore.gongyiGuichengId" value="${gongyiGuicheng.id}"/>
					<input type="hidden" name="gongyiGuichengScore.model" value="gxtblmfqmx"/>
					<input type="hidden" name="gongyiGuichengScore.processDataId" value="${processData.id}"/>
				<!-- 工序图表栏目分区明细 -->
				
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
	var processDataId=$("#processDataId").val();
	var dengluId='${sessionScope.Users.id}';
	var status='';
	var bianzhiId='';
	var jiaoduiId='';
	var shenheId='';
	var pizhunId='';
	
	//初始化毛料参数
	$.ajax({
		type: "post",
		dataType: "json",
        url: "gongyiGuichengAction!getMaoliaoParamListByProcessDataId.action",
        data:{
			'maoliaoParam.processDataId': processDataId
		},
		async: false,
		success: function(data){
			var success=data.success;
			if(success){
				var maoliaoParamList=data.data;
				var $numbArr=$("span[name='maoliaoParam.numb']");
				var $contentArr=$("span[name='maoliaoParam.content']");
				var $danweiArr=$("span[name='maoliaoParam.danwei']");
				var $shuzhiArr=$("span[name='maoliaoParam.shuzhi']");
				var $remarkArr=$("span[name='maoliaoParam.remark']");
				$(maoliaoParamList).each(function(i,m){
					$numbArr.eq(i).text(this.numb);
					$contentArr.eq(i).text(this.content);
					$danweiArr.eq(i).text(this.danwei);
					$shuzhiArr.eq(i).text(this.shuzhi);
					$remarkArr.eq(i).text(this.remark);
				});
			}
		}
	});
	//初始化毛料技术条件
	$.ajax({
		type: "post",
		dataType: "json",
        url: "gongyiGuichengAction!getMaoliaoJishuTiaojianListByProcessDataId.action",
        data:{
			'maoliaoJishuTiaojian.processDataId': processDataId
		},
		async: false,
		success: function(data){
			var success=data.success;
			if(success){
				var maoliaoJishuTiaojianList=data.data;
				var $contentArr=$("span[name='maoliaoJishuTiaojian.content']");
				$(maoliaoJishuTiaojianList).each(function(i,m){
					$contentArr.eq(i).text(this.content);
				});
			}
		}
	});
	
	//*************************************下边初始化表格******************************************************
//*************************末尾***************************
});
</script>
