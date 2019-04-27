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
		<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="AccessEquipmentAction_findAllXungengRecord.action" method="post">
					<table class="table">
						<tr>
							<th align="right">
								巡更人员
							</th>
							<td>
								<input type="text" value="${xungengRecord.userName}"
									name="xungengRecord.userName" />
							</td>
							<th align="right">
								人员工号
							</th>
							<td>
								<input type="text" value="${xungengRecord.userCode}"
									name="xungengRecord.userCode" />
							</td>
							<th align="right">
								状态
							</th>
							<td>
								<SELECT style="width: 152px;" name="xungengRecord.status">
									<option value="${xungengRecord.status}">${xungengRecord.status}</option>
									<option value=""></option>
									<option value="已巡更">已巡更</option>
									<option value="未巡更">未巡更</option>
								</SELECT>
							</td>
						</tr>
					</table>
					<input type="submit" value="查询" class="input" />
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							日期
						</th>
						<th>
							巡更人员
						</th>
						<th>
							人员工号
						</th>
						<th>
							巡更时间
						</th>
						<th>
							设备名称
						</th>
						<th>
							设备编号
						</th>
						<th>
							状态
						</th>
						<th>
							时段名
						</th>
					</tr>
					<s:iterator id="pageList" value="xungengRecordList"
						status="statussdf">
						<s:if test="#statussdf.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#statussdf.index+1" />
						</td>
						<td>
							${pageList.dateNow}
						</td>
						<td>
							${pageList.userName}
						</td>
						<td>
							${pageList.userCode}
						</td>
						<td>
							${pageList.dakaTime}
						</td>
						<td>
							${pageList.equipmentName}
						</td>
						<td>
							${pageList.equipmentNum}
						</td>
						<td>
							<s:if test='#pageList.status=="未巡更"'>
								<font color="red">${pageList.status}</font>
							</s:if>
							<s:else>
								${pageList.status}
							</s:else>
						</td>
						<td>
							${pageList.xungengTimeName}
						</td>
					</s:iterator>
					<tr>
						<td colspan="30" align="right">
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
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
