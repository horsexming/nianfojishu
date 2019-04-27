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
		<script type="text/javascript">
		function show(obj){
			alert(obj);
		}
</script>
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
						style="color: #ffffff">刷新<br/>(Refresh)</a>
				</div>
			</div>
			
			<div align="center">
				<form action="AlertMessagesAction!findUserUpdateLoggin.action?tag=${tag}"
					method="post">
					<table class="table">
						<tr>
							<th align="center" colspan="6">
								查询个人修改信息
							</th>
						</tr>
						<tr>
							<th align="right">
								操作类型:<br/>(Object Type)
							</th>
							<td>
								<select name="updateLogging.logTitle" id="logTitle"
									style="width: 156px;"
									onMouseOver="createDept('logTitle','AlertMessagesAction!findSelectType.action')">
									<option value="${updateLogging.logTitle}">
										${updateLogging.logTitle}
									</option>
									<option value="">
										请选择类型
									</option>
								</select>
							</td>
							<s:if test="tag=='all'">
								<th align="right">
									操作者:<br/>(Operator)
								</th>
								<td>
									<input name="updateLogging.userName" value="${updateLogging.userName}"/>
								</td>
							</s:if>
							<th align="right">
								操作类型:<br/>(Type of operation)
							</th>
							<td>
								<select name="updateLogging.status" style="width: 155px;">
									<option></option>
									<option value="添加">
										添加
									</option>
									<option value="删除">
										删除
									</option>
									<option value="修改">
										修改
									</option>
									<option value="下载">
										下载
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								开始时间:<br/>(Start Time)
							</th>
							<td>
								<input class="Wdate" type="text" name="date"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th align="right">
								结束时间:<br/>(End Time)
							</th>
							<td>
								<input class="Wdate" type="text" name="date"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
							</td>
							<th align="right">
								操作内容:<br/>(Operating content)
							</th>
							<td>
								<input name="updateLogging.more" value="${updateLogging.more}" />
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input type="submit" value="查询select" class="input" />
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号<br/>(Number)
						</th>
						<th align="center">
							类型<br/>(Object type)
						</th>
						<th align="center">
							操作状态<br/>(Operating status)
						</th>
						<th align="center">
							操作者<br/>(Operator)
						</th>
						<th align="center">
							所属部门<br/>(Department)
						</th>
						<th align="center">
							添加时间<br/>(Add Time)
						</th>
						<th align="center">
							操作<br/>(Operation)
						</th>
					</tr>
					<s:iterator value="updateLoggingList" id="pageLogging"
						status="pageStatus">
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
							${pageLogging.logTitle}
						</td>
						<td>
							${pageLogging.status}
						</td>
						<td>
							${pageLogging.userName}
						</td>
						<td>
							${pageLogging.userDept}
						</td>
						<td>
							${pageLogging.addTime}
						</td>
						<td>
							<a href="javascript:;" onclick="show('${pageLogging.more}')">查看明细</a>
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
						</s:else>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
