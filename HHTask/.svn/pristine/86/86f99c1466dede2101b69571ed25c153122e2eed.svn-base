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
					<a href="zhaobiaoAction!listAll.action" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center" id="d1">
				
				<table class="table">
					<tr bgcolor="#c0dcf2">
						<td align="center">序号</td>
						<td align="center">工号</td>
						<td align="center">姓名</td>
						<td align="center">部门</td>
						<td align="center">开始加班</td>
						<td align="center">结束加班</td>
						<td align="center">加班时长/h</td>
						<td align="center">可用加班时长/h</td>
						<td align="center">备注(加工件号)</td>
					</tr>
					<s:iterator value="list" id="pageList" status="pageStatus">
							<tr  onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
					
						<td>
							<s:property value="#pageStatus.index+1" />
							
						</td>
							<td>${pageList.overtimeCode}</td>
							<td>${pageList.overtimeName}</td>
							<td>${pageList.overtimeDept}</td>
							
							<td>${pageList.startDate}</td>
							
							
							<td>${pageList.endDate}</td>
							<td>${pageList.overTimeLong}</td>
							<td>${pageList.usablehxTime}</td>
							<td>${pageList.markId}</td>
						</tr>
						</s:iterator>
						<tr>
						<s:if test="errorMessage==null">
							<th colspan="11" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<th colspan="11" align="center" style="color: red">
						</s:else>
						</th>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

	</body>
	<SCRIPT type="text/javascript">
	</SCRIPT>

</html>
