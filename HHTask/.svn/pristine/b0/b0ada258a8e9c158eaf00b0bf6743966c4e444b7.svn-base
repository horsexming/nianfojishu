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
	<body bgcolor="#ffffff" onload="createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div align="center" id="findAllWage" style="display: block">
				<form action="WageAction!findWageByCondition.action" method="post"
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
						<th align="center">
							操作
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
							</font>
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
						<td>
							<a href="WageAction!printWage.action?id=${pageWageList.id}"
								target="_blank">打印工资单</a>
						</td>
						</tr>
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
	</body>
</html>
