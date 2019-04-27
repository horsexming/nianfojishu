<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body bgcolor="#ffffff">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div align="center" id="findAllWage" style="display: block">
				<form action="SuspsomAction_jiangjingongzi.action" method="post"
					style="margin: 0px;">
					<input name="wage.code" value="${wage.code}" type="hidden" />
					<input name="pageStatus" value="${pageStatus}" type="hidden" />
					<table class="table">
						<tr>
							<th colspan="6">
								工资查询
							</th>
						</tr>
						<tr>
							<td align="right">
								月份:
							</td>
							<td align="left">
								<input class="Wdate" type="text" name="wage.mouth"
									onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input value="查询" type="submit"
									style="width: 100px; height: 50px">
								<input value="重置" type="reset"
									style="width: 100px; height: 50px">
								<input class="input" onclick="window.history.back();" type="button" value="返回" />
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							姓名
						</th>
						<th align="center">
							部门
						</th>
						<th align="center">
							岗位工资
						</th>
						<th align="center">
							保密津贴
						</th>
						<th align="center">
							绩效考核工资
						</th>
						<th align="center">
							发放月份
						</th>
						<th>
							应发工资
						</th>
						<th>
							实发工资
						</th>
						<th align="center">
							工资状态
						</th>
					</tr>
					<s:iterator value="wageList" id="pageWageList"
						status="pageListStatus">
						<s:if test="#pageListStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#pageListStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageListStatus.index+1" />
						</td>
						<td>
							${pageWageList.userName}
						</td>
						<td>
							${pageWageList.dept}
						</td>
						<td>
							${pageWageList.gangweigongzi}
						</td>
						<td>
							${pageWageList.baomijintie}
						</td>
						<td>
							${pageWageList.jixiaokaohegongzi}
						</td>
						<td>
							${pageWageList.mouth}
						</td>
						<td>
							${pageWageList.yingfagongzi}
						</td>
						<td>
							${pageWageList.shifagongzi}
						</td>
						<td>
							${pageWageList.wageStatus}
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
						</s:if>
						<s:else>
							<td colspan="10" align="center" style="color: red">
								${errorMessage}
						</s:else>
					</tr>
				</table>
				<s:if test="userMonthMoneyList.size()>0">
					<table  class="table">
						<tr >
							<th >月度奖金查看</th>
						</tr>
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								姓名
							</th>
							<th align="center">
								部门
							</th>
							<th align="center">
								月份
							</th>
							<th align="center">
								金额
							</th>
							<th align="center">
								状态
							</th>
						</tr>
						<s:iterator value="userMonthMoneyList" id="pageuserMonthMoneyList"
							status="pageListStatus">
							<s:if test="#pageListStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:if test="#pageListStatus.index%2==1">
									<font>
								</s:if>
								<s:else>
									<font color="#c0dcf2">
								</s:else>
								<s:property value="#pageListStatus.index+1" />
							</td>
							<td>
								${pageuserMonthMoneyList.userName}
							</td>
							<td>
								${pageuserMonthMoneyList.dept}
							</td>
							<td>
								${pageuserMonthMoneyList.gangweigongzi}
							</td>
							<td>
								${pageuserMonthMoneyList.baomijintie}
							</td>
							<td>
								${pageuserMonthMoneyList.jixiaokaohegongzi}
							</td>
						</s:iterator>
					</table>
				</s:if>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">
function back1() {
	window.location.href = "SuspsomAction_xuanzhe.action?ipAddress=${ipAddress}&tag=${tag}";
}
</script>
	</body>
</html>
