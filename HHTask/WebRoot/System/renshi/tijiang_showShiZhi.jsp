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
				<form action="TijingAction!queryShizhi.action" method="post">
					<table class="table">
						<tr>
							<td>
								试制产品件号:
								<input type="text" name="tijing.tjmarkId" />
							</td>
							<td>
								试制产品型别:
								<input type="text" name="tijing.tjstyle" />
							</td>
							<td>
								试制数量:
								<input type="text" name="tijing.tjcount" />
							</td>
							<tr>
								<td>
									试制奖金额:
									<input type="text" name="tijing.tjmoney" />
								</td>
								<td>
									奖金分配月份
									<input class="Wdate" type="text" name="tijing.tjmonth"
										size="15"
										onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})" />
								</td>
								<td>
									<input type="submit" value="查找" />
								</td>
							</tr>
					</table>
				</form>
				试制奖金管理
				<table class="table">
					<tr align="center">
						<td colspan="8">
							<a href="System/renshi/tijiang_addShiZhi.jsp">添加试制奖</a>
						</td>
					</tr>
					<tr>
						<td>
							序号
						</td>
						<td>
							试制产品件号
						</td>
						<td>
							试制型别
						</td>
						<td>
							试制数量
						</td>
						<td>
							试制奖金额
						</td>
						<td>
							奖金分配月份
						</td>
						<td>
							状态
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
							<s:property value="tjmarkId" />
						</td>
						<td>
							<s:property value="tjstyle" />
						</td>
						<td>
							<s:property value="tjcount" />
						</td>
						<td>
							<s:property value="tjmoney" />
						</td>
						<td>
							<s:property value="tjmonth" />
						</td>
						<td>
							<s:property value="tjmore" />
						</td>

						<td>
							<s:if test="%{pageStatus=='exam'}">
								<s:if test="%{tjmore=='审核'}">
									<a
										href="TijingAction!updateShizhi.action?id=<s:property value='tjid' />&pageStatus=exam&message=OK">同意</a>

									<a
										href="TijingAction!updateShizhi.action?id=<s:property value='tjid' />&pageStatus=exam&message=NO">不同意</a>
								</s:if>
							</s:if>
							<s:else>
								<s:if test="%{tjmore=='审核'}">
									<a
										href="TijingAction!getOneShizhi.action?id=<s:property value='tjid' />&pageStatus=update">修改</a>
									<a
										href="TijingAction!updateShizhi.action?id=<s:property value='tjid' />&pageStatus=delete">删除</a>

								</s:if>
								<s:else>修改  删除</s:else>
							</s:else>

						</td>


						</tr>
					</s:iterator>
					<tr>
						<td colspan="8" align="right">
							共
							<s:property value="total" />
							页 第
							<s:property value="cpage" />
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
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
