<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	//response.setHeader("Cache-Control","no-store");	
	//response.setHeader("Pragrma","no-cache");
	//response.setDateHeader("Expires",0);
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
			.subTable tr,.subTable td{
				height: 20px;
				border-width: 1px;
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
					<div id="printDiv" style="width:1549px;height:1084px;border:0px solid #000000;position: relative;top:19px;"> 
					<input type="hidden" id="gygcId" value="${gongyiGuicheng.id}"/>
					<input type="hidden" id="processDataId" value="${processData.id}"/>
					<table class=MsoTableGrid border=1 cellspacing=0 cellpadding=0 width=1474
						 style='width:39.0cm;border-collapse:collapse;border:none'>
						 <tr style='height:39.2pt'>
						  <td width=66 colspan=2 style='width:49.75pt;border:solid windowtext 1.0pt;
						  height:39.2pt'>
						  <img style="width: 100%;height: 100%;" src="<%=basePath%>${companyInfo.shhhjpg}"/>
						  </td>
						  <td width=1408 colspan=19 style='width:1055.9pt;border:solid windowtext 1.0pt;
						  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:39.2pt'>
						  <p class=MsoNormal align=center style='text-align:center'><b><span
						  style='font-size:22.0pt;font-family:宋体;letter-spacing:12.0pt'>检验图表</span></b></p>
						  </td>
						  <td style='height:39.2pt;border:none' width=0 height=52></td>
						 </tr>
						 <tr style='height:20.85pt'>
						  <td width=158 colspan=3 style='width:118.8pt;border-top:none;border-left:
						  solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:20.85pt'>
						  <p class=MsoNormal align=right style='text-align:right'><b><span
						  style='font-size:12.0pt;font-family:宋体;letter-spacing:2.0pt'>检验指导简图：</span></b></p>
						  </td>
						  <td width=491 colspan=5 style='width:13.0cm;border:none;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:20.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=47 rowspan=2 style='width:35.45pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:20.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>操作序号</span></p>
						  </td>
						  <td width=104 colspan=2 rowspan=2 style='width:77.95pt;border-top:none;
						  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:20.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>操作顺序</span></p>
						  </td>
						  <td width=246 colspan=3 rowspan=2 style='width:184.3pt;border-top:none;
						  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:20.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>检查项目</span></p>
						  </td>
						  <td width=76 colspan=2 rowspan=2 style='width:2.0cm;border-top:none;
						  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:20.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>测量器具</span></p>
						  </td>
						  <td width=198 colspan=2 rowspan=2 style='width:148.85pt;border-top:none;
						  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:20.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>判定标准</span></p>
						  </td>
						  <td width=66 colspan=2 rowspan=2 style='width:49.6pt;border-top:none;
						  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:20.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>检验频次</span></p>
						  </td>
						  <td width=87 rowspan=2 style='width:65.45pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:20.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>异常处理检查要领</span></p>
						  </td>
						  <td style='height:20.85pt;border:none' width=0 height=28></td>
						 </tr>
						 <tr>
						  <td width=650 colspan=8 rowspan=31 style='width:487.35pt;border:solid windowtext 1.0pt;
						  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;text-align: center;'>
						  	<img style="width: 100%;" id="processImg" src="${processData.processImg}" alt="请上传图片"/>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <!-- 开始 1-->
						 <tr style='height:19.85pt'>
						  <td width=47 rowspan=6 style='width:35.45pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrder.numb">&nbsp;</span></p>
						  </td>
						  <td width=104 colspan=2 rowspan=6 style='width:77.95pt;border-top:none;
						  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrder.content">&nbsp;</span></p>
						  </td>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <td width=87 rowspan=30 valign=top style='width:65.45pt;border-top:none;
						  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <!--结束1 -->
						 <!-- 开始2 -->
						 <tr style='height:19.85pt'>
						  <td width=47 rowspan=6 style='width:35.45pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrder.numb">&nbsp;</span></p>
						  </td>
						  <td width=104 colspan=2 rowspan=6 style='width:77.95pt;border-top:none;
						  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrder.content">&nbsp;</span></p>
						  </td>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <!--结束2 -->
						 <!-- ------------------------------------------------------ -->
						 <!-- 开始3 -->
						 <tr style='height:19.85pt'>
						  <td width=47 rowspan=6 style='width:35.45pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrder.numb">&nbsp;</span></p>
						  </td>
						  <td width=104 colspan=2 rowspan=6 style='width:77.95pt;border-top:none;
						  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrder.content">&nbsp;</span></p>
						  </td>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <!-- 结束3 -->
						 <!-- ---------------------------------------- -->
						 <!-- 开始4 -->
						  <tr style='height:19.85pt'>
						  <td width=47 rowspan=6 style='width:35.45pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrder.numb">&nbsp;</span></p>
						  </td>
						  <td width=104 colspan=2 rowspan=6 style='width:77.95pt;border-top:none;
						  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrder.content">&nbsp;</span></p>
						  </td>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <!-- 结束4 -->
						 <!-- 开始5 -->
						  <tr style='height:19.85pt'>
						  <td width=47 rowspan=6 style='width:35.45pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrder.numb">&nbsp;</span></p>
						  </td>
						  <td width=104 colspan=2 rowspan=6 style='width:77.95pt;border-top:none;
						  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrder.content">&nbsp;</span></p>
						  </td>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=246 colspan=3 style='width:184.3pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.xiangmu">&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.qiju">&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=2 style='width:148.85pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.pandingBiaozhun">&nbsp;</span></p>
						  </td>
						  <td width=66 colspan=2 style='width:49.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US name="operationOrderItem.jianchaPinci">&nbsp;</span></p>
						  </td>
						  <span lang=EN-US style='font-size:10.5pt;font-family:"Calibri","sans-serif"'>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						  </span>
						 </tr>
						 <!-- 结束5 -->
						 <tr style='height:19.85pt'>
						  <td width=64 style='width:47.95pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>件名</span></p>
						  </td>
						  <td width=236 colspan=3 style='width:177.2pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.jianName}</span></p>
						  </td>
						  <td width=76 style='width:2.0cm;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>工序名称</span></p>
						  </td>
						  <td width=170 style='width:127.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${processData.gongxuName}</span></p>
						  </td>
						  <td width=85 style='width:63.8pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>型别</span></p>
						  </td>
						  <td width=106 colspan=3 style='width:79.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.xingbie}</span></p>
						  </td>
						  <td width=123 colspan=2 style='width:92.15pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>编制</span></p>
						  </td>
						  <td width=123 style='width:92.15pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>审核</span></p>
						  </td>
						  <td width=123 colspan=2 style='width:92.1pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>批准</span></p>
						  </td>
						  <td width=123 colspan=2 rowspan=2 style='width:92.15pt;border-top:none;
						  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>会签</span></p>
						  </td>
						  <td width=123 colspan=2 style='width:92.15pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>加工</span></p>
						  </td>
						  <td width=123 colspan=2 style='width:92.15pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>品质</span></p>
						  </td>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						 </tr>
						 <tr style='height:19.85pt'>
						  <td width=64 style='width:47.95pt;border:solid windowtext 1.0pt;border-top:
						  none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>件号</span></p>
						  </td>
						  <td width=236 colspan=3 style='width:177.2pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.jianNumb}</span></p>
						  </td>
						  <td width=76 style='width:2.0cm;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>工序号</span></p>
						  </td>
						  <td width=170 style='width:127.55pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${processData.gongxuNo}</span></p>
						  </td>
						  <td width=85 style='width:63.8pt;border-top:none;border-left:none;border-bottom:
						  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>工装编号</span></p>
						  </td>
						  <td width=106 colspan=3 style='width:79.6pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${processData.gongzhuangNo}</span></p>
						  </td>
						  <td width=123 colspan=2 style='width:92.15pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.bianzhiName}<s:date name="gongyiGuicheng.bianzhiDate"  format="yyyy-MM-dd"/></span></p>
						  </td>
						  <td width=123 style='width:92.15pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.shenheName}<s:date name="gongyiGuicheng.shenheDate"  format="yyyy-MM-dd"/></span></p>
						  </td>
						  <td width=123 colspan=2 style='width:92.1pt;border-top:none;border-left:none;
						  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.pizhunName}<s:date name="gongyiGuicheng.pizhunDate"  format="yyyy-MM-dd"/></span></p>
						  </td>
						  <td width=123 colspan=2 style='width:92.15pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.jiagongName}<s:date name="gongyiGuicheng.jiagongDate"  format="yyyy-MM-dd"/></span></p>
						  </td>
						  <td width=123 colspan=2 style='width:92.15pt;border-top:none;border-left:
						  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
						  padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>${gongyiGuicheng.pinzhiName}<s:date name="gongyiGuicheng.pinzhiDate"  format="yyyy-MM-dd"/></span></p>
						  </td>
						  <td style='height:19.85pt;border:none' width=0 height=26></td>
						 </tr>
						 <tr height=0>
						  <td width=64 style='border:none'></td>
						  <td width=2 style='border:none'></td>
						  <td width=92 style='border:none'></td>
						  <td width=142 style='border:none'></td>
						  <td width=76 style='border:none'></td>
						  <td width=170 style='border:none'></td>
						  <td width=85 style='border:none'></td>
						  <td width=19 style='border:none'></td>
						  <td width=47 style='border:none'></td>
						  <td width=40 style='border:none'></td>
						  <td width=64 style='border:none'></td>
						  <td width=59 style='border:none'></td>
						  <td width=123 style='border:none'></td>
						  <td width=64 style='border:none'></td>
						  <td width=59 style='border:none'></td>
						  <td width=17 style='border:none'></td>
						  <td width=106 style='border:none'></td>
						  <td width=92 style='border:none'></td>
						  <td width=31 style='border:none'></td>
						  <td width=36 style='border:none'></td>
						  <td width=87 style='border:none'></td>
						  <td style='border:none' width=0><p class='MsoNormal'>&nbsp;</td>
						 </tr>
						</table>
					</div>
				</div>
			</div>
			<div style="width: 794px;text-align: center;margin: 0 auto;">
				<form id="markingForm" method="post"
					style="">
					<input type="hidden" name="gongyiGuichengScore.gongyiGuichengId" value="${gongyiGuicheng.id}"/>
					<input type="hidden" name="gongyiGuichengScore.model" value="jyxmlq"/>
					<input type="hidden" name="gongyiGuichengScore.processDataId" value="${processData.id}"/>
				
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
	//****************************************初始化参数****************************************************
	//初始化操作顺序
	$.ajax({
		type: "post",
		dataType: "json",
        url: "gongyiGuichengAction!getOperationOrderListByprocessDataId.action",
        data:{
			'operationOrder.processDataId': $('#processDataId').val()
		},
		async: false,
		success: function(data){
			var success=data.success;
			if(success){
				var operationOrderArr=data.data;
				var $numbArr=$("span[name='operationOrder.numb']");
				var $contentArr=$("span[name='operationOrder.content']");
				
				var $xiangmuArr=$("span[name='operationOrderItem.xiangmu']");
				var $qijuArr=$("span[name='operationOrderItem.qiju']");
				var $pandingBiaozhunArr=$("span[name='operationOrderItem.pandingBiaozhun']");
				var $jianchaPinciArr=$("span[name='operationOrderItem.jianchaPinci']");
				$(operationOrderArr).each(function(i,n){
					var operationOrder=n;
					$numbArr.eq(i).text(operationOrder.numb);
					$contentArr.eq(i).text(operationOrder.content);
					var operationOrderItemList=operationOrder.operationOrderItemList;
					$(operationOrderItemList).each(function(j,m){
						var operationOrderItem=m;
						var start=i*6+j;
						$xiangmuArr.eq(start).text(operationOrderItem.xiangmu);
						$qijuArr.eq(start).text(operationOrderItem.qiju);
						$pandingBiaozhunArr.eq(start).text(operationOrderItem.pandingBiaozhun);
						$jianchaPinciArr.eq(start).text(operationOrderItem.jianchaPinci);
					});
				});
				
			}
		}
	});
});
</script>
