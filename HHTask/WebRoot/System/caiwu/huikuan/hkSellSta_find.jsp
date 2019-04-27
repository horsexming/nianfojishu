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
				<form action="HKSellStaAction!findHKSellSta.action" method="post">
					<table class="table">
						<tr>
							<th>
								客户：
							</th>
							<td>

								<select name="tahkSellSta.hkSellCumpanyName"
									id="hkSellCumpanyName" style="width: 140px;"
									onclick="createDept('hkSellCumpanyName','HKSellStaAction!selectItem.action?tag=hkSellCumpanyName')">
									<option value=""></option>
									<option value="${tahkSellSta.hkSellCumpanyName}"
										selected="selected">
										${tahkSellSta.hkSellCumpanyName}
									</option>
								</select>
							</td>
							<th>
								订单：
							</th>
							<td>
								<select name="tahkSellSta.hkSellOrderId" id="hkSellOrderId"
									style="width: 140px;"
									onclick="createDept('hkSellOrderId','HKSellStaAction!selectItem.action?tag=hkSellOrderId')">
									<option value=""></option>
									<option value="${tahkSellSta.hkSellOrderId}"
										selected="selected">
										${tahkSellSta.hkSellOrderId}
									</option>
								</select>
							</td>
							<th>
								件号：
							</th>
							<td>
								<select name="tahkSellSta.hkSellMarkId" id="hkSellMarkId"
									style="width: 140px;"
									onclick="createDept('hkSellMarkId','HKSellStaAction!selectItem.action?tag=hkSellMarkId')">
									<option value=""></option>
									<option value="${tahkSellSta.hkSellMarkId}" selected="selected">
										${tahkSellSta.hkSellMarkId}
									</option>
								</select>
							</td>
							<td rowspan="2" align="center">
								<input type="submit" style="width: 80px; height: 50px;"
									value="查询" />
								&nbsp;

							</td>
						</tr>
						<tr>
							<th>
								送货单号：
							</th>
							<td>
								<select name="tahkSellSta.hkSellSendId" id="hkSellSendId"
									style="width: 140px;"
									onclick="createDept('hkSellSendId','HKSellStaAction!selectItem.action?tag=hkSellSendId')">
									<option value=""></option>
									<option value="${tahkSellSta.hkSellSendId}" selected="selected">
										${tahkSellSta.hkSellSendId}
									</option>
								</select>
							</td>
							<th>
								日期从：
							</th>
							<td>
								<input class="Wdate" type="text" name="startDate"
									value="${ startDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />

							</td>
							<th>
								到：
							</th>
							<td>
								<input class="Wdate" type="text" name="endDate"
									value="${ endDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>


						</tr>
						</table>
						</form>
						
						<table class="table">
							<tr bgcolor="#c0dcf2" height="30px"
								style="border-collapse: separate;">
								<th align="center">
									序号
								</th>
								<th align="center">
									客户
								</th>
								<th align="center">
									零件号
								</th>
								<th align="center">
									发货数量
								</th>
								<th align="center">
									送货单号
								</th>
								<th align="center">
									订单号
								</th>
								<th align="center">
									日期
								</th>
								<th align="center">
									备注
								</th>

								<th align="center">
									操作
								</th>
							</tr>

							<s:if test="{list.size()>0}">
								<s:iterator value="list" status="se" id="pageProcard">
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
										${pageProcard.hkSellCumpanyName}
									</td>
									<td>
										${pageProcard.hkSellMarkId}
									</td>
									<td>
										${pageProcard.hkSellCount}
									</td>
									<td>
										${pageProcard.hkSellSendId}
									</td>
									<td>
										${pageProcard.hkSellOrderId}
									</td>
									<td>
										${pageProcard.hksellTime}
									</td>
									<td>
										${pageProcard.hkSellMore}
									</td>

									<td>
										<a
											href="HKSellStaAction!getHKSellSta.action?id=${id}&cpage='%{cpage}'">修改</a>&nbsp;&nbsp;&nbsp;
										<!--  -->
										<a onClick="return confirm('确定要删除该条记录吗？')"
											href="HKSellStaAction!deleteHKSellSta.action?id=${id}">删除</a>

									</td>
									</tr>
								</s:iterator>
								<tr>
									<td colspan="9" align="right">
										共
										<s:property value="total" />
										页 第
										<s:property value="cpage" />
										页
										<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
											styleClass="page" theme="number" />

									</td>
								</tr>
							</s:if>
							<s:else>
								<tr>
									<td style="font-size: 15px; color: red;">
										对不起，没有查到相关的送货信息
									</td>
								</tr>
							</s:else>
						</table>

						</div>
						<br>
						</div>
						<%@include file="/util/foot.jsp"%>
						</center>
						<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
