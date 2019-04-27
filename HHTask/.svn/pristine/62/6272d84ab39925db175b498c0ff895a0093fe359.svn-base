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
		<div id="gongneng">
			<div align="center">
				<h3>
					内部计划管理(Internal project management)
				</h3>
				<form action="internalOrder_queryInternalOrderByCondition.action"
					method="post">
					<table class="table">
						<tr>
							<td>
								订单编号(Order Number)：
								<input type="text" name="orderNum" value="${orderNum}" />
							</td>
							<td>
								客户(Customers)：
								<select name="customeId"
									style="margin-left: 30px; width: 160px;">
									<option value="0" selected="selected">
										选择用户
									</option>
									<s:iterator id="c" value="clients">
										<s:if test="#c.id == customeId">
											<option value="${c.id}" selected="selected">
												${c.clientcompanyname}
											</option>
										</s:if>
										<s:else>
											<option value="${c.id}">
												${c.clientcompanyname}
											</option>
										</s:else>
									</s:iterator>
								</select>
							</td>
							<td></td>
						</tr>
						<tr>
							<td>
								开始日期(Start date)：
								<input style="width: 155px" class="Wdate" type="text"
									name="beginTime" value="${beginTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td>
								结束日期(End Date)：
								<input style="width: 155px" class="Wdate" type="text"
									name="endTime" value="${endTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td>
								<input type="hidden" name="errorMessage" value="all" />
								<input type="hidden" name="pageStatus" value="${pageStatus}" />
								<input type="hidden" name="id" value="${id}" />
								<input type="submit" value="查询"
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
							<br />
							No.
						</td>
<%--						<td align="center">--%>
<%--							订单编号--%>
<%--							<br />--%>
<%--							order Number--%>
<%--						</td>--%>
						<td align="center">
							计划编号
							<br />
							Project Number
						</td>
						<td align="center">
							客户
							<br />
							Customers
						</td>
						<td align="center">
							生产计划
							<br />
							Production Plan
						</td>
						<td align="center">
							下计划时间
							<br />
							planned time
						</td>
						<td align="center">
							跟单人
							<br />
							With a single person
						</td>
						<td align="center">
							状态
							<br />
							State
						</td>
						<td></td>
					</tr>
					<s:iterator value="innerLis" id="pageList" status="pageStatus">
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
<%--						<td>--%>
<%--							${pageList.orderNums}--%>
<%--						</td>--%>
						<td>
							${pageList.num }
						</td>
						<td>
							${pageList.custome.clientcompanyname}
						</td>
						<td>
							${pageList.genertorDate}
						</td>
						<td>
							${pageList.newDate }
						</td>
						<td>
							${pageList.documentaryPeople}
						</td>
						<td>
							${pageList.status}
						</td>
						<td>
							<input type="button" value="明细"
								style="width: 60px; height: 30px;"
								onclick="detail(${pageList.id})" />
								<s:if test="#pageList.epId!=null">
							<input type="button" value="审批动态"
								style="width: 80px; height: 30px;"
								onclick="spdt(${pageList.epId})" />
								</s:if>
							<input type="button" value="查看订单"
								style="width: 70px; height: 30px;"
								onclick="order(${pageList.id})" />
							<input type="button" value="删除"
								style="width: 60px; height: 30px;"
								onclick="del(${pageList.id })" />
							<s:if test="bol==true">
								<input type="button" value="同意"
									style="width: 60px; height: 30px;"
									onclick="agree(${pageList.id })" />
								<input type="button" value="打回"
									style="width: 60px; height: 30px;"
									onclick="goBack(${pageList.id })" />
							</s:if>
						</td>
						</tr>
					</s:iterator>
					<s:if test="waitErrorMessage != null">
						<tr>
							<td colspan="11" align="center" style="color: red">
								${waitErrorMessage}
							</td>
						</tr>
					</s:if>
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center" colspan="13">
							审核通过
							<br />
							Audit by
						</td>
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
<%--						<td>--%>
<%--							${pageList.orderNums}--%>
<%--						</td>--%>
						<td>
							${pageList.num }
						</td>
						<td>
							${pageList.custome.clientcompanyname}
						</td>
						<td>
							${pageList.genertorDate}
						</td>
						<td>
							${pageList.newDate }
						</td>
						<td>
							${pageList.documentaryPeople}
						</td>
						<td>
							${pageList.status}
						</td>
						<td>
						<s:if test="#pageList.epId!=null">
						<input type="button" value="审批动态"
								style="width: 80px; height: 30px;"
								onclick="spdt(${pageList.epId})" />
								</s:if>
							<input type="button" value="明细"
								style="width: 60px; height: 30px;"
								onclick="detail(${pageList.id})" />
							<input type="button" value="查看订单"
								style="width: 70px; height: 30px;"
								onclick="order(${pageList.id})" />
							<s:if test="pageStatus=='sc'">
								<input type="button" value="打印"
									style="width: 60px; height: 30px;"
									onclick="print(${pageList.id })" />
								<input type="button" value="删除"
									style="width: 60px; height: 30px;"
									onclick="del(${pageList.id })" />
							</s:if>
						</td>
						</tr>
					</s:iterator>
					<tr>
						0
						<s:if test="errorMessage==null">
							<td colspan="12" align="right">
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
		<script type="text/javascript">
function spdt(id) {
	window.location = "CircuitRunAction_findAduitPage.action?id=" + id;
}
function detail(id) {
	window.location = "internalOrder_queryInternalOrderDetail.action?id=" + id
			+ "&pageStatus=${pageStatus}";
}
function del(id) {
	window.location = "internalOrder_del.action?id=" + id
			+ "&pageStatus=${pageStatus}";
}
function print(id) {
	window.location = "internalOrder_initPrint.action?id=" + id;
}
function order(id) {
	window.open("internalOrder_queryOrderManagerByInnerOrder.action?id=" + id
			+ "&pageStatus=zhuanhuan&flag=dj");
}
function agree(id) {
	window.location = "internalOrder_auditProcess.action?id=" + id
			+ "&ifAgree=" + true + "&pageStatus=${pageStatus}";
}
function goBack(id) {
	window.location = "internalOrder_auditProcess.action?id=" + id
			+ "&ifAgree=" + false + "&pageStatus=${pageStatus}";
}
</script>
	</body>
</html>
