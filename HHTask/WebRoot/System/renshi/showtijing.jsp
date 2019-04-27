<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
	<body bgcolor="#ffffff">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="" style="color: #ffffff">添加功能</a>
				</div>
			</div>
			
			<div align="center">

				<table border="1" style="width: 100%" align="center">
					<tr>
						<th colspan="2">
							<font size="5">提奖计价表中</font>
						</th>
					</tr>
					<tr>
						<td>
							<s:if test="sumList!=null">
								<a
									href="TijingpriceAction!generateEXCEL.action?setDate=${setDate}&endDate=${endDate}">生成EXCEL</a>&nbsp;&nbsp;
										<a
<%--									href="DownAction.action?fileName=${message}&directory=/upload/sheet/">导出EXCEL</a>--%>
									href="FileViewAction.action?FilePath=/upload/sheet/${message}">导出EXCEL</a>
									&nbsp;&nbsp;&nbsp;
									<a
									href="TijingAction.action?setDate=${setDate}&endDate=${endDate}">提奖</a>

							</s:if>
						</td>
					</tr>
				</table>

				<table align="center" width="100%" border="0"
					style="border-collapse: collapse;">
					<tr bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							件号
						</th>
						<th>
							型别
						</th>
						<th>
							数量
						</th>
						<th>
							总价格
						</th>
					</tr>
					<s:iterator id="s" value="sumList" status="stauts">
						<s:if test="#stauts.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							${stauts.index+1}
						</td>
						<td>
							${s[0]}
						</td>
						<td>
							${s[3]}
						</td>
						<td>
							${s[1]}
						</td>
						<td>
							${s[2]}
						</td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
</html>
