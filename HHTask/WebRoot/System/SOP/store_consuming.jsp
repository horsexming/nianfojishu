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
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
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

			<div align="center">
				<h3>
					领用管理
				</h3>
				<form action="store_queryLoanByCondition.action" method="post">
					<table>
						<tr>
							<td>
								编号：
								<input type="text" name="vos.number" value="${vos.number}" />
							</td>
							<td>
								名称：
								<input type="text" name="vos.matetag" value="${vos.matetag}" />
							</td>
							<td>
								规格：
								<input type="text" name="vos.format" value="${vos.format}" />
							</td>
							<td>
								<input type="hidden" name="vos.formUrl" value="consuming" />
								<input type="hidden" name="errorMessage" value="all" />
								<input type="submit" value="查询" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px" align="center">
						<td>
							序号
						</td>
						<td>
							编号
						</td>
						<td>
							名称
						</td>
						<td>
							规格
						</td>
						<td>
							单位
						</td>
						<td>
							可借量
						</td>
						<td>
							总数量
						</td>
						<td>
							仓库名
						</td>
						<td>
							类别
						</td>
						<td>
							库位
						</td>
						<td>
							入库时间
						</td>
						<!-- <td style="width: 160px;">备注</td> -->
						<td style="width: 100px;"></td>
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
							${pageList.number}
						</td>
						<td>
							${pageList.matetag}
						</td>
						<td>
							${pageList.format}
						</td>
						<td>
							${pageList.unit}
						</td>
						<td>
							${pageList.curAmount}
						</td>
						<td>
							${pageList.total}
						</td>
						<td>
							${pageList.storehouse}
						</td>
						<td>
							${pageList.parClass}
						</td>
						<td>
							${pageList.place}
						</td>
						<td>
							${pageList.startDate}
						</td>
						<!-- <td>${pageList.more}</td> -->
						<Td>
							<a
								href="store_initBorrowOrConsuming.action?cardNum=${cardNum}&id=${pageList.id}&vos.formUrl=consuming&pageStauts=zw">领用</a>/
							<a href="renew_initAdd.action?vore.id=${pageList.id}">以旧换新</a>
						</Td>
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
						</td>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
