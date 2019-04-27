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
				<h3>
					月度统计
				</h3>
				<br />
				<form action="store_queryMonthlyStatistics.action" method="post"
					name="myForm">
					<table class="table">
						<tr>
							<th align="right">
								名称：
							</th>
							<td align="left">
								<input type="text" name="vos.matetag" value="${vos.matetag}" />
							</td>
							<th align="right">
								类别：
							</th>
							<td>
								<input type="text" name="vos.parClass" value="${vos.parClass}" />
							</td>
							<Td rowspan="2">
								<input type="hidden" name="errorMessage" value="all" />
								<input type="submit" value="查询"
									style="width: 80px; height: 50px;" />
								<input type="button" value="导出Excel"
									style="width: 80px; height: 50px;" onclick="exportExcel()" />
							</Td>
						</tr>
						<tr>
							<th align="right">
								开始日期：
							</th>
							<td>
								<input style="width: 155px" class="Wdate" id="startTime"
									type="text" name="vos.startTime" value="${vos.startTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th align="right">
								结束日期：
							</th>
							<td>
								<input style="width: 155px" class="Wdate" id="endTime"
									type="text" name="vos.endTime" value="${vos.endTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							名称
						</th>
						<th align="center">
							规格
						</th>
						<th align="center">
							类别
						</th>
						<th align="center">
							价格(含税)
						</th>
						<th align="center">
							入库量
						</th>
						<th align="center">
							出库量
						</th>
						<th align="center">
							库存量
						</th>
						<th align="center">
							上月期初
						</th>
					</tr>
					<s:iterator value="list" id="pageList" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#pageStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						<td>
							${pageList.name}
						</td>
						<td>
							${pageList.format}
						</td>
						<td>
							${pageList.category}
						</td>
						<td>
							${pageList.price}
						</td>
						<td>
							${pageList.storageNum}
						</td>
						<td>
							${pageList.outNum}
						</td>
						<td>
							${pageList.currentNum}
						</td>
						<td>
							${pageList.currentNum+pageList.outNum-pageList.storageNum}
						</td>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="14" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="14" align="center" style="color: red">
								${errorMessage}
						</s:else>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function exportExcel() {
	document.forms.myForm.action = "store_exportStatistics.action";
	document.forms.myForm.submit();
	document.forms.myForm.action = "store_queryStoreByCondition.action";
}
</script>
	</body>
</html>
