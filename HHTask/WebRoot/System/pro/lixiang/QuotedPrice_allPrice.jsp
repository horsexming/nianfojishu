<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			<div id="printDiv">
				<div align="center">
					<table class="table" style="line-height: 35px;">
						<tr>
							<th colspan="6">
								报价核算汇总 (
								<a target="_showQuoDetails"
									href="QuotedPrice_findQuotedPrice.action?id=${quotedPrice.id}&pageStatus=bom">Bom</a>、
								<s:if test="quotedPrice.fileName!=null">
									<a
<%--										href="DownAction?fileName=${quotedPrice.fileName}&directory=/upload/file/project/">附件</a>--%>
										href="FileViewAction.action?FilePath=/upload/file/project/${quotedPrice.fileName}">附件</a>
								</s:if>
								<s:else>
									<font color="gray">查看附件</font>
								</s:else>
								)
							</th>
						</tr>
						<tr>
							<th colspan="3" align="right" style="width: 260px;">
								询价单号:
							</th>
							<td colspan="3">
								${quotedPrice.quotedNumber}
							</td>
						</tr>
						<tr>
							<th colspan="3" align="right" style="width: 260px;">
								件号:
							</th>
							<td colspan="3">
								${quotedPrice.markId}
							</td>
						</tr>
						<tr>
							<th colspan="3" align="right" style="width: 260px;">
								名称:
							</th>
							<td colspan="3">
								${quotedPrice.proName}
							</td>
						</tr>
						<tr>
							<th>
								报价项目
							</th>
							<th>
								首件报价价格
							</th>
							<th>
								试制报价价格
							</th>
							<th>
								批产报价价格
							</th>
							<th>
								截止时间
							</th>
							<th>
								完成时间
							</th>
						</tr>
						<s:iterator value="list" id="pageProTime" status="pageSt">
							<tr>
								<th align="right" style="width: 260px;">
									<a target="_showQuoDetails" title="查看详细"
										href="QuotedPrice_findQuotedPrice.action?id=${quotedPrice.id}&pageStatus=${pageProTime.classNumber}">${pageProTime.className}费:</a>
								</th>
								<s:if test="#pageProTime.classNumber=='sb'||#pageProTime.classNumber=='gz'||#pageProTime.classNumber=='rg'||#pageProTime.classNumber=='gzf'">
								<td align="center" colspan="3">
									${pageProTime.money}
								</td>
								</s:if>
								<s:else>
								<td align="center">
									${pageProTime.moneysj}
								</td>
								<td align="center">
									${pageProTime.moneysz}
								</td>
								<td align="center">
									${pageProTime.money}
								</td>
								</s:else>
								<td align="center">
									${pageProTime.provisionTime}
								</td>
								<td align="center">
									${pageProTime.realTime}
								</td>
							</tr>
						</s:iterator>
						<tr>
							<th align="right">
								报价(含税):
							</th>
							<td colspan="1">
								${moneysj}
							</td>
							<td colspan="1">
								${moneysz}
							</td>
							<td colspan="1">
								${money}
							</td>
							<td colspan="1">
							</td>
							<td colspan="1">
							</td>
							<td colspan="1">
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div align="center">
			 <s:if test="quotedPrice.status=='项目启动中'||quotedPrice.status=='项目跟踪'||quotedPrice.status=='项目编制'||quotedPrice.status=='完成'">
				<input type="button" onclick="pagePrint('printDiv')" value="打印">
			 </s:if>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
