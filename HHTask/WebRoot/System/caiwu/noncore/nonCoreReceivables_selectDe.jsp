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
			.table10 {
				font-size: 14px;
				padding: 0px;
				margin: 0px;
				border-collapse: collapse;
				border: solid #999;
				border-width: 0px 0 0 0px;
				width: 99%;
			}
			
			.table10 th,.table10 td {
				height:80px;
				border: solid #000;
				border-width: 2 2px 2px 2;
				padding: 2px;
			}
		</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div id="gong" align="center" align="center" style="width: 765px; height: 1086px; border: 0px solid #000000;">
				<h3 style="width: 100%; margin-top: 10px;" align="center">
					<font size="5px""><B>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;应收明细信息</B></font><br>
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<table class="table10" style="font-size: 20px;">
					<tr>
						<th style="width: 200px;" align="right">
							承租方：
						</th>
						<td align="left">
							${nonCoreReceivablesDetail.nonCoreReceivables.chengzufang}
						</td>
					</tr>
					<tr>
						<th align="right">
							承租地址：
						</th>
						<td align="left">
							${nonCoreReceivablesDetail.nonCoreReceivables.chengzudizhi}
						</td>
					</tr>
					<tr>
						<th align="right">
							科目：
						</th>
						<td align="left">
							${nonCoreReceivablesDetail.kemu}
						</td>
					</tr>
					<tr>
						<th align="right">
							添加人：
						</th>
						<td align="left">
							${nonCoreReceivablesDetail.saveUser}
						</td>
					</tr>
					<tr>
						<th align="right">
							账单截止日期：
						</th>
						<td align="left">
							${nonCoreReceivablesDetail.jiluTime}
						</td>
					</tr>
					<tr>
						<th align="right">
							应收金额：
						</th>
						<td align="left">
							${nonCoreReceivablesDetail.yingfuJine}
						</td>
					</tr>
					<tr>
						<th align="right">
							实收金额：
						</th>
						<td align="left">
							${nonCoreReceivablesDetail.realfuJine}
						</td>
					</tr>
					<tr>
						<th align="right">
							收款人：
						</th>
						<td align="left">
							${nonCoreReceivablesDetail.queren}
						</td>
					</tr>
					<s:if test="tag=='caiwu'">
						<tr style="margin-right: 20px;">
							<th align="right" colspan="2" >
								<br/>
								<br/>
								<br/>
								<br/>
								<br/>
								<br/>
								财务印章:
							</th>
						</tr>
					</s:if>
				</table>
			</div>
			<s:if test="tag=='caiwu'">
				<div align="center">
					<input type="submit" value="打印" onclick="pagePrint('gong','sy')"
						style="width: 80px; height: 50px;" />
				</div>
			</s:if>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
</script>
	</body>
</html>
