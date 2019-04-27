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
<center>
				<form action="zhaobiaoAction!addnianlilv.action" method="post"
					theme="simple">
					
					<table class="table" style="width: 70%;">
					<tr><th colspan="2" align="center">添加年利率</th></tr>
						<tr>
							<th align="right">
								年份：
							</th>
							<td  >
								<input class="Wdate" type="text" id="nianlilv.nianfen"
									name="nianlilv.nianfen"
									onClick="WdatePicker({dateFmt:'yyyy',skin:'whyGreen'})" />
							</td>
						</tr>
	
						<tr>
							<th align="right">
								年利率：
							</th>
							<td>
								<input type="text" id="nianlilv.lilv" name="nianlilv.lilv" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="保存" class="input" />
								<input type="button" name="Submit2" value="取消" class="input"
									class="right-buttons" onclick="window.history.go(-1);" />
							</td>
						</tr>
					</table>


				</form>
			</div>
			
			
			
			</center>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<script type="text/javascript">
	$(function(){
	var errorMessage = '${errorMessage}';
		if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
	
	})
	
	</script>


</html>
