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
			<form action="YusuantianbaobiaoAction!updateYusuantianbaototal.action" method="post">
				<table class="table">
					<tr>
						<th colspan="4" align="center">添加预算报表</th>
					</tr>
					<tr>
						<th align="right">
							项目名称或者代码：
						</th>
						<td>
							<input type="text" name="yusuantianbaototal.xiangmumingda"
							value="${yusuantianbaototal.xiangmumingda}"/>
						</td>
						<th align="right">
							年度：
						</th>
						<td>
								<input type="text" name="yusuantianbaototal.niandu"
							 class="Wdate"
							onClick="WdatePicker({dateFmt:'yyyy',skin:'whyGreen'})"
							value="${yusuantianbaototal.niandu}"/>
							
							
							
							<input type="hidden" name="yusuantianbaototal.addtime"
							value="${yusuantianbaototal.addtime}"/>
							<input type="hidden" name="yusuantianbaototal.id"
							value="${yusuantianbaototal.id}"/>
						</td>
					</tr>
					<tr>	
						<td colspan="4" align="center">	
							<input type="submit" value="修改"/>
							<input type="reset" value="重置 "/>
							
						</td>					
					</tr>
				</table>
			</form>			
		</center>
		<script type="text/javascript">
</script>
	</body>
</html>