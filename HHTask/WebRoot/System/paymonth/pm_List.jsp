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
.dhlabel {
	width: auto;
	height: 30px;
	border-radius: 5px 5px 0px 0px;
	line-height: 30px;
	text-align: center;
	background: #eaeaea;
	display: inline-block;
	color: #000000;
	font-family: "Lucida Grande", Arial, sans-serif;
	font-size: 12px;
	font-weight: bold;
}

td:hover .qs_ul {
	display: block;
}

.qs_ul {
	display: none;
	border: 1px solid #999;
	list-style: none;
	margin: 0;
	padding: 0;
	position: absolute;
	width: auto;
	background: #CCC;
	color: green;
}
</style>
	</head>
	<body onload="gys()">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center">
				<h3>
					月度结算单管理
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="CorePayableAction!showpmList.action?tag=${tag}"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="right">
								申请人：
							</td>
							<td align="left">
								<input type="text" name="payMonth.saveUser" />
							</td>
							<td align="right">
								编号：
							</td>
							<td align="left">
								<input type="text" name="payMonth.poNumber" />
							</td>
							<td align="right">
								供应商名称：
							</td>
							<td align="left">
								<input type="text" name="payMonth.supplierName" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="6">
								<input type="submit"
									style="width: 100px; height: 40px; margin-left: 70px;"
									value="查询(select)" />
								<input type="button"
									style="width: 100px; height: 40px; margin-left: 70px;"
									value="添加(add)" onclick="add()" />
							</td>
						</tr>
					</table>
				</form>

				<table class="table" style="border-collapse: collapse;">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							编号
						</td>
						<td align="center">
							供应商
						</td>
						<td align="center">
							请款项目
						</td>
						<td align="center">
							添加时间
						</td>
						<td align="center">
							总额
						</td>
						<td align="center">
							操作
						</td>
					</tr>
					<s:iterator value="payMonthList" id="samples" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${samples.recNumber}
						</td>
						<td align="center">
							${samples.supplierName}
						</td>
						<td align="center">
							${samples.proName}
						</td>
						<td align="center">
							${samples.saveTime}
						</td>
						<td align="center">
							${samples.jine}
						</td>
						<td align="center">
							<a
								href="CorePayableAction!printOutpm.action?id=${samples.id}">打印</a>/
							<a
								href="CorePayableAction!pmmx.action?id=${samples.id}">查看明细</a>/
							<a onclick="return window.confirm('您将删除数据，是否继续?')"
									href="CorePayableAction!delcp.action?payMonth.id=${samples.id}">删除</a>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="11" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function add() {
	window.location.href = "System/paymonth/addPayMonth.jsp";
}
function toShowWg(tag) {
	window.location.href = "zhaobiaoAction!showListYufu.action?tag=" + tag;
}
</script>
	</body>
</html>
