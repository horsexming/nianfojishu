<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@include file="/util/sonHead.jsp"%>
		<style type="text/css">
body {
	font-family: 宋体;
}

.table {
	border: 0px solid #999;
	width: 756px;
	border-collapse: collapse;
}

.table th,.table td {
	height: 24px;
	border-width: 0px;
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
			<div id="printDiv"
				style="width: 794px; height: 1123px; border: 1px solid #000000;padding-left: 38px;padding-top: 40px;">
				<!-- 页边距内开始 -->
				<!-- 打印页边距设定为 5mm 时，网页内最大元素的分辨率：756×1086   5mm/18.89-->
				<div
					style="width: 756px; height: 1086px; border: 1px solid #000000; position: relative; middle: 19px;">
					<input type="hidden" id="gygcId" value="${gongyiGuicheng.id}" />
					<table   class=MsoTableGrid border=0 cellspacing=0 cellpadding=0
						 style='border-collapse:collapse;border:none;'>
						 <tr style='height:17.2pt'>
						  <td width=470 colspan=13 valign=top style='width:352.7pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:17.2pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=225 colspan=4 valign=top style='width:168.4pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:17.2pt'>
						  <p class=MsoNormal><span style='font-size:14.0pt;font-family:宋体'>工艺规程编号</span><span
						  style='font-family:宋体'>：</span><span lang=EN-US>${gongyiGuicheng.numb}</span></p>
						  </td>
						 </tr>
						 <tr style='height:102.35pt'>
						  <td width=695 colspan=17 valign=top style='width:521.1pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:102.35pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:63.35pt'>
						  <td width=158 colspan=2 valign=top style='width:118.8pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:63.35pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=416 colspan=14 valign=top style='width:11.0cm;padding:0cm 5.4pt 0cm 5.4pt;
						  height:63.35pt'>
						  <p class=MsoNormal><span style='font-size:48.0pt;font-family:宋体;letter-spacing:
						  11.0pt'>工艺规程</span></p>
						  </td>
						  <td width=121 valign=top style='width:90.45pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:63.35pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:39.95pt'>
						  <td width=695 colspan=17 valign=top style='width:521.1pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:39.95pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr>
						  <td width=243 colspan=5 valign=top style='width:182.6pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=227 colspan=8 valign=top style='width:6.0cm;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span style='font-size:14.0pt;font-family:宋体'>型别：${gongyiGuicheng.xingbie}</span></p>
						  </td>
						  <td width=225 colspan=4 valign=top style='width:168.4pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr>
						  <td width=695 colspan=17 valign=top style='width:521.1pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr>
						  <td width=139 valign=top style='width:104.2pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=199 colspan=7 valign=top style='width:149.3pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span style='font-size:14.0pt;font-family:宋体'>件号：</span><span
						  lang=EN-US style='font-size:14.0pt'>${gongyiGuicheng.jianNumb}</span></p>
						  </td>
						  <td width=38 colspan=2 valign=top style='width:1.0cm;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=198 colspan=6 valign=top style='width:148.8pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span style='font-size:14.0pt;font-family:宋体'>件名：${gongyiGuicheng.jianName}</span></p>
						  </td>
						  <td width=121 valign=top style='width:90.45pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr>
						  <td width=695 colspan=17 valign=top style='width:521.1pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr>
						  <%--<td width=272 colspan=6 valign=top style='width:203.85pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=50 valign=top style='width:37.8pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-size:14.0pt;font-family:宋体'>共</span></p>
						  </td>
						  <td width=50 colspan=2 valign=top style='width:37.8pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
						  style='font-size:14.0pt'>${gongyiGuicheng.pageTotal}</span></p>
						  </td>
						  <td width=50 colspan=2 valign=top style='width:37.8pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-size:14.0pt;font-family:宋体'>页</span></p>
						  </td>
						  <td width=272 colspan=6 valign=top style='width:203.85pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  
						 --%>
						 	<td width=243 colspan=5 valign=top style='width:182.6pt;padding:0cm 5.4pt 0cm 5.4pt'>
							  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
							  </td>
							  <td width=227 colspan=8 valign=top style='width:6.0cm;padding:0cm 5.4pt 0cm 5.4pt'>
							  <p class=MsoNormal><span style='font-size:14.0pt;font-family:宋体'>共&nbsp;&nbsp;&nbsp;&nbsp;${gongyiGuicheng.pageTotal}&nbsp;&nbsp;&nbsp;&nbsp;页</span></p>
							  </td>
							  <td width=225 colspan=4 valign=top style='width:168.4pt;padding:0cm 5.4pt 0cm 5.4pt'>
							  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:54.4pt'>
						  <td width=695 colspan=17 valign=top style='width:521.1pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:54.4pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr>
						  <td width=139 valign=top style='width:104.2pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=95 colspan=3 valign=top style='width:71.3pt;padding:0cm 0pt 0cm 5.4pt'>
						  <p class=MsoNormal><span style='font-size:22.0pt;font-family:宋体'>编制：</span></p>
						  </td>
						  <td width=236 colspan=9 valign="bottom" style='width:177.2pt;border:none;
						  border-bottom:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US style='font-size:14.0pt'>${gongyiGuicheng.bianzhiName}&nbsp;&nbsp;<s:date name="gongyiGuicheng.bianzhiDate"  format="yyyy-MM-dd"/></span></p>
						  </td>
						  <td width=225 colspan=4 valign=top style='width:168.4pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr>
						  <td width=695 colspan=17 valign=top style='width:521.1pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr>
						  <td width=139 valign=top style='width:104.2pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=95 colspan=3 valign=top style='width:71.3pt;padding:0cm 0pt 0cm 5.4pt'>
						  <p class=MsoNormal><span style='font-size:22.0pt;font-family:宋体'>校对：</span></p>
						  </td>
						  <td width=236 colspan=9 valign="bottom" style='width:177.2pt;border:none;
						  border-bottom:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US style='font-size:14.0pt'>${gongyiGuicheng.jiaoduiName}&nbsp;&nbsp;<s:date name="gongyiGuicheng.jiaoduiDate"  format="yyyy-MM-dd"/></span></p>
						  </td>
						  <td width=225 colspan=4 valign=top style='width:168.4pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr>
						  <td width=695 colspan=17 valign=top style='width:521.1pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr>
						  <td width=139 valign=top style='width:104.2pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=95 colspan=3 valign=top style='width:71.3pt;padding:0cm 0pt 0cm 5.4pt'>
						  <p class=MsoNormal><span style='font-size:22.0pt;font-family:宋体'>审核：</span></p>
						  </td>
						  <td width=236 colspan=9 valign="bottom" style='width:177.2pt;border:none;
						  border-bottom:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US style='font-size:14.0pt'>${gongyiGuicheng.shenheName}&nbsp;&nbsp;<s:date name="gongyiGuicheng.shenheDate"  format="yyyy-MM-dd"/></span></p>
						  </td>
						  <td width=225 colspan=4 valign=top style='width:168.4pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr>
						  <td width=695 colspan=17 valign=top style='width:521.1pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr>
						  <td width=139 valign=top style='width:104.2pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=95 colspan=3 valign=top style='width:71.3pt;padding:0cm 0pt 0cm 5.4pt'>
						  <p class=MsoNormal><span style='font-size:22.0pt;font-family:宋体'>批准：</span></p>
						  </td>
						  <td width=236 colspan=9 valign="bottom" style='width:177.2pt;border:none;
						  border-bottom:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US style='font-size:14.0pt'>${gongyiGuicheng.pizhunName}&nbsp;&nbsp;<s:date name="gongyiGuicheng.pizhunDate"  format="yyyy-MM-dd"/></span></p>
						  </td>
						  <td width=225 colspan=4 valign=top style='width:168.4pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:34.45pt'>
						  <td width=695 colspan=17 valign=top style='width:521.1pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:34.45pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr>
						  <td width=168 colspan=3 valign=top style='width:125.9pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=378 colspan=12 valign=top style='width:10.0cm;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span style='font-size:22.0pt;font-family:宋体'>${companyInfo.name}</span></p>
						  </td>
						  <td width=149 colspan=2 valign=top style='width:111.7pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:31.15pt'>
						  <td width=695 colspan=17 valign=top style='width:521.1pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:31.15pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr>
						  <td width=234 colspan=4 valign=top style='width:175.5pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=236 colspan=9 valign=top style='width:177.2pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US style='font-size:15.0pt'><s:date name="gongyiGuicheng.fachuDate"  format="yyyy"/></span><span
						  style='font-size:15.0pt;font-family:宋体'>年</span><span lang=EN-US
						  style='font-size:15.0pt'><s:date name="gongyiGuicheng.fachuDate"  format="MM"/></span><span style='font-size:15.0pt;font-family:
						  宋体'>月</span><span lang=EN-US style='font-size:15.0pt'><s:date name="gongyiGuicheng.fachuDate"  format="dd"/></span><span
						  style='font-size:15.0pt;font-family:宋体'>日出发</span></p>
						  </td>
						  <td width=225 colspan=4 valign=top style='width:168.4pt;padding:0cm 5.4pt 0cm 5.4pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:19.65pt'>
						  <td width=695 colspan=17 valign=top style='width:521.1pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:19.65pt'>
						  <p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>
						  </td>
						 </tr>
						 <tr style='height:21.55pt'>
						  <td width=461 colspan=12 style='width:345.6pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:21.55pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US>&nbsp;</span></p>
						  </td>
						  <td width=76 colspan=2 style='width:2.0cm;padding:0cm 5.4pt 0cm 5.4pt;
						  height:21.55pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>版次</span> <span style='font-family:宋体'>${gongyiGuicheng.banci}</span></p>
						  </td>
						  <td width=158 colspan=3 style='width:118.8pt;padding:0cm 5.4pt 0cm 5.4pt;
						  height:21.55pt'>
						  <p class=MsoNormal align=center style='text-align:center'><span
						  style='font-family:宋体'>存图号</span><span lang=EN-US>${gongyiGuicheng.cuntuNumb}</span></p>
						  </td>
						 </tr>
						 <tr height=0>
						  <td width=139 style='border:none'></td>
						  <td width=19 style='border:none'></td>
						  <td width=9 style='border:none'></td>
						  <td width=66 style='border:none'></td>
						  <td width=9 style='border:none'></td>
						  <td width=28 style='border:none'></td>
						  <td width=50 style='border:none'></td>
						  <td width=16 style='border:none'></td>
						  <td width=35 style='border:none'></td>
						  <td width=3 style='border:none'></td>
						  <td width=47 style='border:none'></td>
						  <td width=38 style='border:none'></td>
						  <td width=9 style='border:none'></td>
						  <td width=66 style='border:none'></td>
						  <td width=9 style='border:none'></td>
						  <td width=28 style='border:none'></td>
						  <td width=121 style='border:none'></td>
						 </tr>
					</table>
									
					</div>
				<!-- 页边距内结束 -->
			</div>
			<!-- A4页面结束 -->
		</div>
		<div style="width: 794px; text-align: center; margin: 0 auto;">
			<form id="markingForm" method="post" style="">
				<input type="hidden" name="gongyiGuichengScore.gongyiGuichengId"
					value="${gongyiGuicheng.id}" />
				<input type="hidden" name="gongyiGuichengScore.model" value="gygc" />
				<!-- 首页 -->
				
			</form>
			<table style="width: 100%; text-align: center; border: 0px;">
				<tr>
					<td>
						<input type="button" id="printButton" value="打印" class="input"
							onclick="pagePrint('printDiv')" />
					</td>
				</tr>
			</table>
		</div>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
<script type="text/javascript">
$(function() {
	//全局工艺规程状态
	var dengluId = '${sessionScope.Users.id}';
	var status = '';
	var bianzhiId = '';
	var jiaoduiId = '';
	var shenheId = '';
	var pizhunId = '';
	//出事化件号
	//**************末尾************************	
});
</script>
