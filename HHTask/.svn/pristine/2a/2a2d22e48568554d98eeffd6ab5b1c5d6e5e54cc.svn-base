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
				<font color="red" size="5">${errorMessage}</font>
				<h2>${qp.usename}的${qp.qename }问卷明细查看</h2>
				<table class="table">
				<tr bgcolor="#c0dcf2" height="50px" align="center">
					<th>序号</th>
					<th>内容</th>
					<th>状态</th>
				</tr>
					<s:iterator value="qulist" id="pageList" status="pageStatus">
					<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td >
							第<s:property value="#pageStatus.index+1" />条            
						</td>
						<td> 
							<span>${pageList.content}</span>
						</td>  
						<td>
							<s:if test="#pageList.status=='yes'">
								是
							</s:if>
							<s:elseif test="#pageList.status=='no'">
								否
							</s:elseif>
						</td>                                                                                                                                                                                  
					</tr>
					</s:iterator>
				</table>    
		</div>  
	</div>                           
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->                                         
	</body>
</html>                                                                                                                 
                                                                  