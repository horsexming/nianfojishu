<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
.s {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
	width: 980px;
}

.s th,.s td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}
</style>
	</head>
	<body bgcolor="#ffffff">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
			</div>
			
			<div align="center">
				<form action="BonusmoneyAction!conditionFindAll.action"
					method="post">
					<input type="hidden" name="pageStatus" value="${pageStatus}">
					<table align="center" class="table">
						<tr>
							<th colspan="6">
								<font size="5">奖金分配总金额</font>
							</th>
						</tr>
						<tr>
							<th>
								月份
							</th>
							<td>
								<input class="Wdate" type="text"
									name="bonusmoney.bonusmoneymonth"
									onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
							</td>
							<th>
								总金额
							</th>
							<td>
								<input type="text" onkeyup="if(isNaN(value))execCommand('undo')"
									onafterpaste="if(isNaN(value))execCommand('undo')"
									name="bonusmoney.bonusmoneytotalmoney" />
							</td>
							<th>
								查询班组
							</th>
							<td>
								<s:if test="pageStatus=='ptyh'">
									<input type="text" readonly="readonly"
										name="bonusmoney.bonusmoneyteam" value="${dept}" />
								</s:if>
								<s:else>
									<select name="bonusmoney.bonusmoneyteam">
										<option value=""></option>
										<option value="制消班1">
											制消班1
										</option>
										<option value="制消班2">
											制消班2
										</option>
										<option value="排气管班">
											排气管班
										</option>
										<option value="组件班">
											组件班
										</option>
										<option value="零件班">
											零件班
										</option>
									</select>
								</s:else>
						</tr>
						<tr>
							<td colspan="6">
								<input type="submit" value="确 定" />
								&nbsp;&nbsp;&nbsp;
								<a href="BonusmoneyAction.action?pageStatus=${pageStatus}">查看全部</a>
							</td>
						</tr>
					</table>
				</form>
				<table align="center" class="s">
					<tr>
						<th>
							月份
						</th>
						<th>
							总金额
						</th>
						<th>
							班组
						</th>
						<th>
							状态
						</th>
						<th>
							添加人姓名
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator id="bo" value="list" status="stauts">
						<s:if test="#stauts.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td align="center">
							${bo.bonusmoneymonth}
						</td>
						<td align="center">
							${bo.bonusmoneytotalmoney}
						</td>
						<td align="center">
							${bo.bonusmoneyteam}
						</td>
						<td align="center">
							${bo.bonusmoneystatus}
						</td>
						<td align="center">
							${bo.bonusmoneyname}
						</td>
						<td>
							<!-- 班长 -->
							<s:if test="pageStatus=='ptyh'">
								<form
									action="BonusmoneyAction!finddate.action?pageStatus=${pageStatus}"
									method="post" style="margin: 0px; padding: 0px;">
									<input type="hidden" name="yuefen"
										value="${bo.bonusmoneymonth}" />
									<input type="hidden" name="banzu" value="${bo.bonusmoneyteam}" />
									<input type="submit" value="查询详细" />
								</form>
								<s:if test="bonusmoneystatus=='加工经理打回'">
									<form
										action="BonusmoneyAction!auditDisagreeupdate.action?pageStatus=${pageStatus}"
										method="post">
										<input type="hidden" value="${bo.bonusmoneymonth}"
											name="yuefen" />
										<input type="hidden" value="${bonusmoneyteam}" name="banzu" />
										<input type="submit" value="修  改" />
									</form>
								</s:if>
								<s:elseif test="bonusmoneystatus=='生产副总打回'">
									<form
										action="BonusmoneyAction!auditDisagreeupdate.action?pageStatus=${pageStatus}"
										method="post">
										<input type="hidden" value="${bo.bonusmoneymonth}"
											name="yuefen" />
										<input type="hidden" value="${bonusmoneyteam}" name="banzu" />
										<input type="submit" value="修  改" />
									</form>
								</s:elseif>
								<s:elseif test="bonusmoneystatus=='总经理打回'">
									<form
										action="BonusmoneyAction!auditDisagreeupdate.action?pageStatus=${pageStatus}"
										method="post">
										<input type="hidden" value="${bo.bonusmoneymonth}"
											name="yuefen" />
										<input type="hidden" value="${bonusmoneyteam}" name="banzu" />
										<input type="submit" value="修  改" />
									</form>
								</s:elseif>
							</s:if>

							<!-- 加工经理 -->
							<s:if test="pageStatus=='jgjl'">
								<form
									action="BonusmoneyAction!finddate.action?pageStatus=${pageStatus}"
									method="post" style="margin: 0px; padding: 0px;">
									<input type="hidden" name="yuefen"
										value="${bo.bonusmoneymonth}" />
									<input type="hidden" name="banzu" value="${bo.bonusmoneyteam}" />
									<input type="submit" value="查询详细" />
									<br />
									<a
										href="BonusmoneyAction!findzongMoney.action?yuefen=${bo.bonusmoneymonth}&pageStatus=${pageStatus}">查看生产总额</a>
								</form>
								<s:if test="bonusmoneystatus=='审核中'">
									<a
										href="BonusmoneyAction!audit.action?id=${bo.id}&pageStatus=${pageStatus}">同意</a>
								</s:if>
								<s:elseif test="bonusmoneystatus=='加工经理同意'">
									<font color="gray">同意</font>
								</s:elseif>
								<s:if test="bonusmoneystatus=='审核中'">
									<a
										href="BonusmoneyAction!auditDisagree.action?id=${bo.id}&pageStatus=${pageStatus}">打回</a>
								</s:if>
							</s:if>

							<!-- 人事 -->
							<s:if test="pageStatus=='hr'">
								<form
									action="BonusmoneyAction!finddate.action?pageStatus=${pageStatus}"
									method="post" style="margin: 0px; padding: 0px;">
									<input type="hidden" name="yuefen"
										value="${bo.bonusmoneymonth}" />
									<input type="hidden" name="banzu" value="${bo.bonusmoneyteam}" />
									<input type="submit" value="查询详细" />
								</form>
								<s:if test="bonusmoneystatus=='总经理同意'">
									<form action="BonusmoneyAction!printPreview.action"
										method="post" style="margin: 0px; padding: 0px;">
										<input type="hidden" value="${bo.bonusmoneymonth}"
											name="yuefen">
										<input type="hidden" value="${bo.bonusmoneyteam}" name="banzu">
										<input type="submit" value="打印预览">
									</form>
								</s:if>
								<s:elseif test="bonusmoneystatus=='财务同意'">
									<form action="BonusmoneyAction!printPreview.action"
										method="post" style="margin: 0px; padding: 0px;">
										<input type="hidden" value="${bo.bonusmoneymonth}"
											name="yuefen">
										<input type="hidden" value="${bo.bonusmoneyteam}" name="banzu">
										<input type="submit" value="打印预览">
									</form>
								</s:elseif>
							</s:if>

							<!-- 生产副总 -->
							<s:if test="pageStatus=='scfz'">
								<form
									action="BonusmoneyAction!finddate.action?pageStatus=${pageStatus}"
									method="post" style="margin: 0px; padding: 0px;">
									<input type="hidden" name="yuefen"
										value="${bo.bonusmoneymonth}" />
									<input type="hidden" name="banzu" value="${bo.bonusmoneyteam}" />
									<input type="submit" value="查询详细" />
									<br />
									<a
										href="BonusmoneyAction!findzongMoney.action?yuefen=${bo.bonusmoneymonth}&pageStatus=${pageStatus}">查看生产总额</a>
								</form>
								<s:if test="bonusmoneystatus=='加工经理同意'">
									<a
										href="BonusmoneyAction!audit.action?id=${bo.id}&pageStatus=${pageStatus}">同意</a>
								</s:if>
								<s:elseif test="bonusmoneystatus=='生产副总同意'">
									<font color="gray">同意</font>
								</s:elseif>
								<s:if test="bonusmoneystatus=='加工经理同意'">
									<a
										href="BonusmoneyAction!auditDisagree.action?id=${bo.id}&pageStatus=${pageStatus}">打回</a>
								</s:if>
							</s:if>

							<!-- 总经理  -->
							<s:if test="pageStatus=='all'">
								<form
									action="BonusmoneyAction!finddate.action?pageStatus=${pageStatus}"
									method="post" style="margin: 0px; padding: 0px;">
									<input type="hidden" name="yuefen"
										value="${bo.bonusmoneymonth}" />
									<input type="hidden" name="banzu" value="${bo.bonusmoneyteam}" />
									<input type="submit" value="查询详细" />
									<br />
									<a
										href="BonusmoneyAction!findzongMoney.action?yuefen=${bo.bonusmoneymonth}&pageStatus=${pageStatus}">查看生产总额</a>
								</form>
								<s:if test="bonusmoneystatus=='审核中'">
									<a
										href="BonusmoneyAction!audit.action?id=${bo.id}&pageStatus=${pageStatus}">同意</a>
								</s:if>
								<s:elseif test="bonusmoneystatus=='总经理同意'">
									<font color="gray">同意</font>
								</s:elseif>
								<s:if test="bonusmoneystatus=='审核中'">
									<a
										href="BonusmoneyAction!auditDisagree.action?id=${bo.id}&pageStatus=${pageStatus}">打回</a>
								</s:if>
							</s:if>
							<!-- 财务 -->
							<s:if test="pageStatus=='cw'">
								<form
									action="BonusmoneyAction!finddate.action?pageStatus=${pageStatus}"
									method="post" style="margin: 0px; padding: 0px;">
									<input type="hidden" name="yuefen"
										value="${bo.bonusmoneymonth}" />
									<input type="hidden" name="banzu" value="${bo.bonusmoneyteam}" />
									<input type="submit" value="查询详细" />
									<br />
									<a
										href="BonusmoneyAction!findzongMoney.action?yuefen=${bo.bonusmoneymonth}&pageStatus=${pageStatus}">查看生产总额</a>
								</form>
							</s:if>
							<a href="CircuitRunAction_findAduitPage.action?id=${bo.epId}">审批动态</a>
						</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="16" align="right">
							第
							<font color="red"><s:property value="cpage" /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />
						</td>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
