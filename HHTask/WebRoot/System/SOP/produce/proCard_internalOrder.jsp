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
			<div align="center">
				<h3>
					内部计划管理
				</h3>
				<form action="internalOrder!findAllAgreeOrder.action" method="post">
					<table class="table">
						<tr>
							<th align="right">
								订单编号(内部)：
							</th>
							<td>
								<input type="text" name="io.orderNums" value="${io.orderNums}" />
							</td>
							<th align="right">
								内部计划编号：
							</th>
							<td>
								<input type="text" name="io.num" value="${io.num}" />
							</td>
							<th>
								件号(业务件号):
							</th>
							<td>
								<input type="text" name="io.pieceNumber" value="${tidai}"
									id="pieceNumber" onchange="changvalue(this)" />
								<input type="hidden" value="${tidai}" name="tidai" id="tidai" />
							</td>
							<td rowspan="3">
								<input type="hidden" name="errorMessage" value="all" />
								<input type="hidden" name="pageStatus" value="${pageStatus}" />
								<input type="hidden" name="tag" value="${tag}" />
								<input type="hidden" name="id" value="${id}" />
								<input type="submit" value="查询"
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
						<tr>
							<th align="right">
								客户：
							</th>
							<td>
								<select name="io.custome.id" style="width: 160px;">
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
							<th align="right">
								开始日期：
							</th>
							<td>
								<input style="width: 155px" class="Wdate" type="text"
									name="beginTime" value="${beginTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th align="right">
								结束日期:
							</th>
							<td>
								<input style="width: 155px" class="Wdate" type="text"
									name="endTime" value="${endTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<th align="right">
								跟单人:
							</th>
							<td>
								<input name="io.documentaryPeople"
									value="${io.documentaryPeople}" />
							</td>
							<th align="right">
								订单类型 :
							</th>
							<td>
								<input name="io.producttype" value="${io.producttype}" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
							<br />
							No.
						</th>
						<th align="center">
							订单编号(內部)
							<br />
							order Number
						</th>
						<th align="center">
							计划编号
							<br />
							Project Number
						</th>
						<th align="center">
							客户
							<br />
							Customers
						</th>
						<th align="center">
							产品(未转/总数)
							<br />
							Product
						</th>
						<th align="center">
							生产计划
							<br />
							Production Plan
						</th>
						<th align="center">
							下计划时间
							<br />
							Under the planned time
						</th>
						<th align="center">
							跟单人
							<br />
							With a single person
						</th>
						<th align="center">
							状态
							<br />
							State
						</th>
						<th align="center">
							是否转换
							<br />
							Whether conversion
						</th>
						<th align="center">
							订单类型
							<br />
							orderType
						</th>
						<th align="center">
							编制状态
							<br />
							updateStatus
						</th>
						<th align="center">
							最新设变时间
						</th>
						<th>
							操作
							<br />
							Operation
						</th>
					</tr>
					<tr bgcolor="red">
						<th colspan="15" align="center">
							<font color="#ffffff">待转换内部计划</font>
						</th>
					</tr>
					<s:iterator value="noStatusList" id="pageList" status="pageStatus">
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
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="left">
							${pageList.orderNums }
						</td>
						<td>
							${pageList.num }
						</td>
						<td>
							${pageList.custome.clientcompanyname}
						</td>
						<td align="left">
							${pageList.pieceNumber}
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
							${pageList.zhStatus}
						</td>
						<td>
							${pageList.producttype}
						</td>
						<td>
							${pageList.bzStatus}
						</td>
						<td>
							${pageList.shebeiDate}
						</td>
						<td>
							<%--<input type="button" value="明细"
								style="width: 60px; height: 30px;"
								onclick="detail(${pageList.id})" />
							--%>
							<s:if test="zhStatus=='已转完'">
								<input type="button" value="生产任务转换"
									style="width: 100px; height: 30px;" disabled="disabled" />
							</s:if>
							<s:else>
								<input type="button" value="生产任务转换"
									style="width: 100px; height: 30px;"
									onclick="addCard(${pageList.id})" />
							</s:else>
							<!--							<input type="button" value="设变信息"-->
							<!--									style="width: 100px; height: 30px;"-->
							<!--									onclick="sbmessage(${pageList.id})" />-->
						</td>
						</tr>
					</s:iterator>
					<tr bgcolor="green">
						<th colspan="15" align="center">
							<font color="#ffffff">已完成内部计划 </font>
						</th>
					</tr>
					<s:iterator value="innerLis" id="pageLists" status="pageStatus">
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
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>

							${pageLists.orderNums }
						</td>
						<td>
							${pageLists.num }
						</td>
						<td>
							${pageLists.custome.clientcompanyname}
						</td>
						<td align="left">
							${pageLists.pieceNumber}
						</td>
						<td>
							${pageLists.genertorDate}
						</td>
						<td>
							${pageLists.newDate }
						</td>
						<td>
							${pageLists.documentaryPeople}
						</td>
						<td>
							${pageLists.status}
						</td>
						<td>
							${pageLists.zhStatus}
						</td>
						<td>
							${pageLists.producttype}
						</td>
						<td>
							${pageLists.bzStatus}
						</td>
						<td>
							${pageLists.shebeiDate}
						</td>
						<td>
							<input type="button" value="明细"
								style="width: 60px; height: 30px;"
								onclick="detail(${pageLists.id})" />
							<%--<s:if test="zhStatus=='已转完'">
								<input type="button" value="生产任务转换"
									style="width: 100px; height: 30px;" disabled="disabled" />
							</s:if>
							<s:else>
								<input type="button" value="生产任务转换"
									style="width: 100px; height: 30px;"
									onclick="addCard(${pageLists.id})" />
							</s:else>
						--%>
						</td>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="15" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="15" align="center" style="color: red">
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
function detail(id) {
	window.location = "internalOrder_queryInternalOrderDetail.action?id=" + id
			+ "&pageStatus=${pageStatus}";
}
function addCard(id) {
	window.location = "internalOrder_queryInternalOrderDetail.action?id=" + id
			+ "&pageStatus=${pageStatus}&tag=${tag}";
}
function sbmessage(id) {
	window.location = "internalOrder_findPtoTbanbenApply.action?id=" + id;
}
function changvalue(obj) {
	$("#tidai").val(obj.value);
}
</script>
	</body>
</html>
