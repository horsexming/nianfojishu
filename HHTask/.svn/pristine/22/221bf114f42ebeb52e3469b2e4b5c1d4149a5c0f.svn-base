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
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			

			<div align="center">

				<table width="95%" class="table">
					<tr>
						<td colspan="13" align="center"
							style="font-size: 15px; font-weight: bold;">
							&nbsp;&nbsp;&nbsp;
							<a href="javascript:history.back()">返回</a>
						</td>
					</tr>
					<tr align="center" bgcolor="#c0dcf2"
						style="height: 40px; font-weight: bold;">
						<td>
							序号
						</td>
						<td>
							件号
						</td>
						<td>
							产品名称
						</td>
						<td>
							零件总节拍
						</td>
						<td>
							实际工费用
						</td>
						<td>
							录入时间
						</td>
						<td>
							备注
						</td>
						<td>
							操作
						</td>

					</tr>
					<s:iterator value="list" status="se" id="sell">
						<s:if test="#se.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#se.index+1" />
						</td>
						<td>
							<s:property value="spmarkId" />
						</td>
						<td>
							<s:property value="spgoodsName" />
						</td>
						<td>
							<s:property value="allWorkHours" />
						</td>
						<td>
							<s:property value="allLaborcost" />
						</td>
						<td>
							<s:property value="entryDate" />
						</td>
						<td>
							<s:property value="more" />
						</td>
						<td>
							<a
								href="productPriceAction!fingProductProcess.action?id=<s:property value='id' />&tag=queryProductProcee">查看工序</a>
						</td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
