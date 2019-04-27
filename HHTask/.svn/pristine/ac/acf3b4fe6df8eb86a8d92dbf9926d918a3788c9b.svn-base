<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	</head>
	<body onload="createDept('selectDept')">
		<div id="bodyDiv" align="center"
			style="filter: Alpha(Opacity =         75); display: none; position: absolute; width: 100%; background: #000; opacity: 1;">
		</div>

		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>

				</div>
			</div>
			
			
			<div align="center">

				<div align="center">
					<form action="EquipmentAction!findsum.action" method="post"
						style="margin: 0px;">

						<table align="center">
							<tr>
								<th colspan="8" style="color: red;">
									故障修复率
								</th>
							</tr>
							<tr>
								<td align="right">
									起始日期：
								</td>
								<td>
									<input class="Wdate" name="date1"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd ',skin:'whyGreen'})" />

								</td>
								<td align="right">
									结束日期：
								</td>
								<td>
									<input class="Wdate" name="date2"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd ',skin:'whyGreen'})" />

								</td>

								<td>

									<input type="submit" value="查询"
										style="width: 60px; height: 40px">

								</td>
							</tr>
						</table>
					</form>



					<table width="100%" border="0" style="border-collapse: collapse;"
						class="table">
						<tr bgcolor="#c0dcf2" height="30px">

							<th align="center">
								设备总故障次数
							</th>
							<th align="center">
								设备故障修复次数
							</th>
							<th align="center">
								设备故障未修复次数
							</th>
							<th align="center">
								设备故障修复率
							</th>

						</tr>
						<tr align="center"
							style="color: red; font-size: 20px; font-weight: bolder;">
							<td>
								${sum}
							</td>

							<td>
								${sumxiufu}
							</td>
							<td>
								${sum-sumxiufu}
							</td>
							<td style="color: green; font-size: 20px; font-weight: bolder;">
								${sumli}%

							</td>
						</tr>
					</table>



					<br>
				</div>
			</div>
			<%@include file="/util/foot.jsp"%>
			</center>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		</div>
	</body>
</html>
