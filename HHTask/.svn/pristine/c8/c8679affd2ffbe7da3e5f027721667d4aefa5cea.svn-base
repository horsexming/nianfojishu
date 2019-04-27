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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<div align="center" id="image">
					
					<table style="BORDER-COLLAPSE: collapse" borderColor=#000000 
							 cellPadding=1 width="70%" align=center bgColor=#fffbec border=0>
						<tr>
							
							<td align="right" colspan="2">
								编号：
							${leaveInform.time}</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<font size='5' ><B>
								${leaveInform.username }
								</B></font></br>
								<font size='3' ><B>
										${leaveInform.code }
										</B></font>
							</td>
						</tr>
						
						<tr>
							<td width="600px" colspan="2" ></br>
								${leaveInform.fuck2} </br></br></td>
						</tr>
						<tr>
							<td align="right" colspan="2">
								通知部门：
							${leaveInform.dept } </td>
						</tr>
						<tr>
							<td align="right" colspan="2">
								通知人员：
							${leaveInform.reason }</td>
						</tr>
						</table>
					</div>
						<table>
						<tr>
							<td align="center" colspan="2">
								<input type="button" onclick="pagePrint('image')" class="input" 
							id="print"
							value="打 印"
									style="width: 50px; height: 50px;" />
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
