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
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>序号</th>
						<th>工序号</th>
						<th>工序名</th>
						<th>件号</th>
						<th>零件名称</th>
						<th>工序单价</th>
						<th>工序点数</th>
						<th>奖金系数</th>
						<th>修改人</th>
						<th>修改人工号</th>
						<th>修改时间</th>
					</tr>
					<s:iterator value="ppulList" id="pageList" status="pageStatus">
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
									<s:property value="#pageStatus.index+1" />
								</td>
								<td>${pageList.processNo}</td>
								<td>${pageList.processName}</td>
								<td>${pageList.markId}</td>
								<td>${pageList.proName}</td>
								<td>${pageList.processjjMoney}</td>
								<td>${pageList.procesdianshu}</td>
								<td>${pageList.jjratio}</td>
								<td>${pageList.userName}</td>
								<td>${pageList.usercode}</td>
								<td>${pageList.updateTime}</td>
								</tr>
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
