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
				<table class="table">
					<tr>
						<td colspan="3" align="center"><h2>问卷名称:${qt.name }</h2></td>
					</tr>
					<tr >
						<td colspan="3" align="center" ><font color="#ff0000">问卷条列</font></td>
					</tr>
					<s:iterator value="qeList" id="pageList" status="pageStatus">
					<tr align="center">
						<td>
							第<s:property value="#pageStatus.index+1" />条            
						</td>
						<td> 
							<textarea rows="2" cols="80">${pageList.content}</textarea>
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
                                                                  