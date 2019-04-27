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
			a{
				cursor: pointer;
			}
			.table{
				border:0px solid #999;
				width: 756px;	
				border-collapse:collapse;
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
					<input type="hidden" id="processTableId" />
					<table   class=MsoTableGrid border=1 cellspacing=0 cellpadding=0
						 style='border-collapse:collapse;border:none'>
						 <tr style='height:19.85pt'>
						  <td width=87 colspan=2 rowspan=2 style='width:65.1pt;border:solid windowtext 1.0pt;
						  height:39.7pt'>
						  	<img style="width: 100%;height: 100%;" src="<%=basePath%>${companyInfo.shhhjpg}"/>
						  </td>
						  <td width=157 colspan=3 rowspan=2 style='width:117.5pt;border:solid windowtext 1.0pt;
						  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-size:16.0pt;font-family:宋体'>工艺程序图表</span></p>
						  </td>
						  <td width=76 style='width:2.0cm;border:solid windowtext 1.0pt;border-left:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>型别</span></p>
						  </td>
						  <td width=151 colspan=2 style='width:4.0cm;border:solid windowtext 1.0pt;
						  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>件号</span></p>
						  </td>
						  <td width=132 colspan=3 style='width:99.2pt;border:solid windowtext 1.0pt;
						  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>件名</span></p>
						  </td>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-left:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>第</span></p>
						  </td>
						  <td width=35 style='width:26.6pt;border:solid windowtext 1.0pt;border-left:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>1</span></p>
						  </td>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-left:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>页</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=76 valign="middle" style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.xingbie}</span></p>
						  </td>
						  <td width=151 colspan=2 valign="middle" style='width:4.0cm;border-top:none;
						  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.jianNumb}</span></p>
						  </td>
						  <td width=132 colspan=3 valign="middle" style='width:99.2pt;border-top:none;
						  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.jianName}</span></p>
						  </td>
						  <td width=28 valign="middle" style='width:21.3pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span style='font-family:宋体'>共</span></p>
						  </td>
						  <td width=35 valign="middle" style='width:26.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>1</span></p>
						  </td>
						  <td width=28 valign="middle" style='width:21.3pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span style='font-family:宋体'>页</span></p>
						  </td>
						 </tr>
						 <tr>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-size:7.5pt;font-family:宋体'>工序号</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>工序名称</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>设备</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>工装</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>批次</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>备注</span></p>
						  </td>
						 </tr>
						 <!-- 1 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
						  style='font-size:7.5pt' name="processData.gongxuNo">&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongxuName">&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.shebeiName">&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongzhuangName">&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.pici">&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.remark">&nbsp;</span></p>
						  </td>
						 </tr>
						  <!-- 2 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						  <!-- 3 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
						  style='font-size:7.5pt' name="processData.gongxuNo">&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongxuName">&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.shebeiName">&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongzhuangName">&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.pici">&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.remark">&nbsp;</span></p>
						  </td>
						 </tr>
						  <!-- 4 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						  <!-- 5 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
						  style='font-size:7.5pt' name="processData.gongxuNo">&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongxuName">&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.shebeiName">&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongzhuangName">&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.pici">&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.remark">&nbsp;</span></p>
						  </td>
						 </tr>
						  <!-- 6 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						  <!-- 7 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
						  style='font-size:7.5pt' name="processData.gongxuNo">&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongxuName">&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.shebeiName">&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongzhuangName">&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.pici">&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.remark">&nbsp;</span></p>
						  </td>
						 </tr>
						  <!-- 8 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						  <!-- 9 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
						  style='font-size:7.5pt' name="processData.gongxuNo">&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongxuName">&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.shebeiName">&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongzhuangName">&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.pici">&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.remark">&nbsp;</span></p>
						  </td>
						 </tr>
						  <!-- 10 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						  <!-- 11 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
						  style='font-size:7.5pt' name="processData.gongxuNo">&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongxuName">&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.shebeiName">&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongzhuangName">&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.pici">&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.remark">&nbsp;</span></p>
						  </td>
						 </tr>
						  <!-- 12 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						  <!-- 13 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
						  style='font-size:7.5pt' name="processData.gongxuNo">&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongxuName">&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.shebeiName">&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongzhuangName">&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.pici">&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.remark">&nbsp;</span></p>
						  </td>
						 </tr>
						  <!-- 14 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						  <!-- 15 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
						  style='font-size:7.5pt' name="processData.gongxuNo">&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongxuName">&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.shebeiName">&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongzhuangName">&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.pici">&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.remark">&nbsp;</span></p>
						  </td>
						 </tr>
						  <!-- 16 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						  <!-- 17 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
						  style='font-size:7.5pt' name="processData.gongxuNo">&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongxuName">&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.shebeiName">&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongzhuangName">&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.pici">&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.remark">&nbsp;</span></p>
						  </td>
						 </tr>
						  <!-- 18 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						  <!-- 19 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
						  style='font-size:7.5pt' name="processData.gongxuNo">&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongxuName">&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.shebeiName">&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongzhuangName">&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.pici">&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.remark">&nbsp;</span></p>
						  </td>
						 </tr>
						  <!-- 20 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <!-- 19 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
						  style='font-size:7.5pt' name="processData.gongxuNo">&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongxuName">&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.shebeiName">&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongzhuangName">&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.pici">&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.remark">&nbsp;</span></p>
						  </td>
						 </tr>
						  <!-- 20 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <!-- 19 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
						  style='font-size:7.5pt' name="processData.gongxuNo">&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongxuName">&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.shebeiName">&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongzhuangName">&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.pici">&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.remark">&nbsp;</span></p>
						  </td>
						 </tr>
						  <!-- 20 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <!-- 19 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
						  style='font-size:7.5pt' name="processData.gongxuNo">&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongxuName">&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.shebeiName">&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongzhuangName">&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.pici">&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.remark">&nbsp;</span></p>
						  </td>
						 </tr>
						  <!-- 20 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <!-- 19 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
						  style='font-size:7.5pt' name="processData.gongxuNo">&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongxuName">&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.shebeiName">&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongzhuangName">&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.pici">&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.remark">&nbsp;</span></p>
						  </td>
						 </tr>
						  <!-- 20 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <!-- 19 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
						  style='font-size:7.5pt' name="processData.gongxuNo">&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongxuName">&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.shebeiName">&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongzhuangName">&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.pici">&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.remark">&nbsp;</span></p>
						  </td>
						 </tr>
						  <!-- 20 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <!-- 19 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
						  style='font-size:7.5pt' name="processData.gongxuNo">&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongxuName">&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.shebeiName">&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongzhuangName">&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.pici">&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.remark">&nbsp;</span></p>
						  </td>
						 </tr>
						  <!-- 20 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						
						  <!-- 21 -->
						 <tr style='height:19.85pt'>
						  <td width=28 style='width:21.3pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
						  style='font-size:7.5pt' name="processData.gongxuNo">&nbsp;</span></p>
						  </td>
						  <td width=177 colspan=3 style='width:132.95pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongxuName">&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.shebeiName">&nbsp;</span></p>
						  </td>
						  <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.gongzhuangName">&nbsp;</span></p>
						  </td>
						  <td width=57 style='width:42.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.pici">&nbsp;</span></p>
						  </td>
						  <td width=130 colspan=4 style='width:97.5pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="processData.remark">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=121 colspan=3 style='width:90.45pt;border:solid windowtext 1.0pt;
						  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>索引号</span></p>
						  </td>
						  <td width=85 style='width:63.8pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=95 style='width:70.9pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>编制</span></p>
						  </td>
						  <td width=94 colspan=2 style='width:70.85pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>校队</span></p>
						  </td>
						  <td width=94 colspan=2 style='width:70.85pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>审核</span></p>
						  </td>
						  <td width=92 colspan=3 style='width:69.2pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>批准</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=121 colspan=3 style='width:90.45pt;border:solid windowtext 1.0pt;
						  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>更改单号</span></p>
						  </td>
						  <td width=85 style='width:63.8pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=95 style='width:70.9pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.bianzhiName}</span></p>
						  </td>
						  <td width=94 colspan=2 style='width:70.85pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.jiaoduiName}</span></p>
						  </td>
						  <td width=94 colspan=2 style='width:70.85pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.shenheName}</span></p>
						  </td>
						  <td width=92 colspan=3 style='width:69.2pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.pizhunName}</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=121 colspan=3 style='width:90.45pt;border:solid windowtext 1.0pt;
						  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>签名日期</span></p>
						  </td>
						  <td width=85 style='width:63.8pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=95 style='width:70.9pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US><s:date name="gongyiGuicheng.bianzhiDate"  format="yyyy-MM-dd"/></span></p>
						  </td>
						  <td width=94 colspan=2 style='width:70.85pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US><s:date name="gongyiGuicheng.jiaoduiDate"  format="yyyy-MM-dd"/></span></p>
						  </td>
						  <td width=94 colspan=2 style='width:70.85pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US><s:date name="gongyiGuicheng.shenheDate"  format="yyyy-MM-dd"/></span></p>
						  </td>
						  <td width=92 colspan=3 style='width:69.2pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US><s:date name="gongyiGuicheng.pizhunDate"  format="yyyy-MM-dd"/></span></p>
						  </td>
						 </tr>
						 <tr height=0>
						  <td width=28 style='border:none'></td>
						  <td width=58 style='border:none'></td>
						  <td width=34 style='border:none'></td>
						  <td width=85 style='border:none'></td>
						  <td width=38 style='border:none'></td>
						  <td width=76 style='border:none'></td>
						  <td width=95 style='border:none'></td>
						  <td width=57 style='border:none'></td>
						  <td width=38 style='border:none'></td>
						  <td width=57 style='border:none'></td>
						  <td width=38 style='border:none'></td>
						  <td width=28 style='border:none'></td>
						  <td width=35 style='border:none'></td>
						  <td width=28 style='border:none'></td>
						 </tr>
						</table>
					</div>
					<!-- 页边距内结束-->
				</div>
				<!-- A4页面结束  -->
			</div>
			<div style="width: 794px;text-align: center;margin: 0 auto;">
				<form id="markingForm" method="post"
					style="">
					<input type="hidden" name="gongyiGuichengScore.gongyiGuichengId" value="${gongyiGuicheng.id}"/>
					<input type="hidden" name="gongyiGuichengScore.model" value="gycxtb"/>
				
				<!-- 工艺程序图 -->
				
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
	
	//出事化件号-----------------------
	//出事化工序数据列表--------------------------
	$.ajax({
		type: "post",
		dataType: "json",
        url: "gongyiGuichengAction!getProcessDataListBygongyiGuichengId.action",
        data:{
			'gongyiGuicheng.id': $('#gygcId').val()
		},
		async: false,
		success: function(data){
			var success=data.success;
			var html='';
			if(success){
				var processDataArr=data.data;
				if(processDataArr.length>0){
					var $gongxuNoArr=$("span[name='processData.gongxuNo']");
					var $gongxuNameArr=$("span[name='processData.gongxuName']");
					var $shebeiNameArr=$("span[name='processData.shebeiName']");
					var $gongzhuangNameArr=$("span[name='processData.gongzhuangName']");
					var $piciArr=$("span[name='processData.pici']");
					var $remarkArr=$("span[name='processData.remark']");
					$(processDataArr).each(function(i,n){
						var processData=n;
						var id=processData.id==null? '': processData.id;
						var gongxuNo=processData.gongxuNo==null? '': processData.gongxuNo;
						var gongxuName=processData.gongxuName==null? '': processData.gongxuName;
						var shebeiId=processData.shebeiId==null? '': processData.shebeiId;
						var shebeiNo=processData.shebeiNo==null? '': processData.shebeiNo;
						var shebeiName=processData.shebeiName==null? '': processData.shebeiName;
						var gongzhuangId=processData.gongzhuangId==null? '': processData.gongzhuangId;
						var gongzhuangNo=processData.gongzhuangNo==null? '': processData.gongzhuangNo;
						var gongzhuangName=processData.gongzhuangName==null? '': processData.gongzhuangName;
						var pici=processData.pici==null? '': processData.pici;
						var remark=processData.remark==null? '': processData.remark;
						var editStatus=processData.editStatus==null? '': processData.editStatus;
						$gongxuNoArr.eq(i).text(gongxuNo);
						$gongxuNameArr.eq(i).text(gongxuName);
						$shebeiNameArr.eq(i).text(shebeiName);
						$gongzhuangNameArr.eq(i).text(gongzhuangName);
						$piciArr.eq(i).text(pici);
						$remarkArr.eq(i).text(remark);
					});
				}
			}
		}
	});
	
});
</script>
