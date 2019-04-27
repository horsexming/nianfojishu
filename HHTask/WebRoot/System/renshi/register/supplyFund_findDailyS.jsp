<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
body {
	text-align: center;
}
</style>

		<%@include file="/util/sonHead.jsp"%>
	</head>

	<body onload="testList()">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
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

				<div id="printDiv" align="center">
					<br />
					<br />
					<h2>
						<span></span>
						<table>
							<tr>
								<th>
									<img width="64px;" height="55px;"
										src="${pageContext.request.contextPath}/${companyInfo.logoOKjpg}" />
								</th>
								<th>
									${companyInfo.name}当日充值记录
								</th>
							</tr>
						</table>
					</h2>
					<table width="85%" class="table">
						<tr>
							<th>
								序号
							</th>
							<th>
								姓名
							</th>
							<th>
								部门
							</th>
							<th>
								卡号
							</th>
							<th>
								充值日期
							</th>
							<th>
								充值份数
							</th>

						</tr>
						<s:iterator value="list" status="hk" id="supply">
							<tr align="center">
								<td>
									<s:property value="#hk.index+1" />
								</td>
								<td align="center">
									${supply[0]}
								</td>
								<td>
									${supply[1]}
								</td>
								<td>
									${supply[2]}
								</td>
								<td>
									${nowDate}
								</td>
								<td>
									${supply[4]}
								</td>

							</tr>
						</s:iterator>

					</table>

				</div>
			</div>
			<input style="width: 80px; font-size: 18px;"
				onclick="pagePrint('printDiv')" type="button" value="打印">

		</div>
		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">
function testList() {
	var lineCount = "<s:property value='list.size()'/>";
	if (lineCount < 1) {
		alert("当天充值未完成或正在充值中！");
	}
}
</script>
	</body>
</html>
