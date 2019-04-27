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
		<div id="gongneng" style="width: 100%;">

			<div align="center">
				<h3>
					刷卡记录查看
				</h3>
				<form action="AttendanceTowAction_showListAttendance.action?tag=${tag}" method="post">
					<table class="table">
						<tr style="width: 100%">
							<th align="center" style="width: 25%">
								请输入姓名
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="attendanceTow.name"
									/>
							</td>
							<th align="center" style="width: 25%">
								请输入您要查询的日期
							</th>
							<td align="center" style="width: 25%">
								<input class="Wdate" type="text" name="carInOutType.updateTime" 
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
							</td>
						</tr>
						<tr>
							<th align="center" style="width: 25%">
								请输入卡号
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="attendanceTow.cardId"
									/>
							</td>
							<th align="center" style="width: 25%">
								请输入工号
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="attendanceTow.code"
									/>
							</td>
						</tr>
						<tr>
							<td align="center" style="width: 25%" colspan="4">
								<input type="submit" value="查询"
									style="width: 100px; height: 25px;" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							姓名
						</td>
						<td align="center">
							工号
						</td>
						<td align="center">
							部门
						</td>
						<td align="center">
							卡号
						</td>
						<td align="center">
							时间
						</td>
						<td align="center">
							状态
						</td>
						<td align="center">
							打卡类型
						</td>
						<td align="center">
							位置
						</td>
					</tr>
					<s:iterator value="attendanceTowList" id="samples"
						status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${samples.name}
						</td>
						<td align="center">
							${samples.code}
						</td>
						<td align="center">
							${samples.dept}
						</td>
						<td align="center">
							${samples.cardId}
						</td>
						<td align="center">
							${samples.dateTime} ${samples.time}
						</td>
						<td align="center">
							正常
						</td>
						<td align="center">
							${samples.outInDoor}
						</td>
						<td align="center">
							${samples.downAddress}
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="9" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="9" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

</script>
	</body>
</html>
