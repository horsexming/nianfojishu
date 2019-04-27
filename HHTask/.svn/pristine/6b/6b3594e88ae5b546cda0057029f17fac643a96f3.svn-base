<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<center>
			<form action="YusuantianbaobiaoAction!addZongBiao.action" method="post"
				onsubmit="return check()">
				<table class="table">
					<tr>
						<th colspan="4" align="center">添加预算报表</th>
					</tr>
					<tr>
						<th align="right">
							预算名称：
						</th>
						<td>
							<input type="text" name="yusuantianbaototal.xiangmumingda"
							id="xiangmumingda"/>
						</td>
						<th align="right">
							年度：
						</th>
						<td>
							<input type="text" name="yusuantianbaototal.niandu"
							class="Wdate"
							onClick="WdatePicker({dateFmt:'yyyy',skin:'whyGreen'})"
							id="niandu"/>
						</td>
					</tr>
					<tr>	
						<td colspan="4" align="center">	
							<input type="submit" value="确定"/>
							<input type="reset" value="重置 "/>
							
						</td>					
					</tr>
				</table>
			</form>			
		</center>
		<script type="text/javascript">
		function check(){
			var testValue1=document.getElementById("xiangmumingda").value;
 			if(testValue1==""){
 				alert("请填写预算名称!");
 				return false;
 			}
 			var testValue2=document.getElementById("niandu").value;
 			if(testValue2==""){
 				alert("请填写年度!");
 				return false;
 			}
		}
</script>
	</body>
</html>