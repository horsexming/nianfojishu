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
		<h3 align="center">查看包装申请详情</h3>
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							名称
						</th>
						<th>
							规格
						</th>
						<th>
							数量
						</th>
						<th>
							条码
						</th>
						<th>
							添加时间
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="goodsBzsq_list" id="goodsBzsq" status="pageindex">
						<s:if test="#pageindex.index%2==1">
							<tr align="center" bgcolor="#e6f3fb" id="tr${pageindex.index}"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								id="tr${pageindex.index}" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td align="center">
							${goodsBzsq.name}
						</td>
						<td align="center">
							${goodsBzsq.format}
						</td>
						<td align="center">
							${goodsBzsq.count}
						</td>
						<td align="center">
							${goodsBzsq.barcode}
						</td>
						<td align="center">
							${goodsBzsq.addTime}
						</td>
						<td align="center">
							<a onclick="return window.confirm('您将删除数据，是否继续?')"
								href="goodsAction!deletexq.action?id=${goodsBzsq.id}">删除</a>
						</td>
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<script type="text/javascript">
</script>
</html>
