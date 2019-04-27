<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<div align="center">
			<form
				action="checkTypeAction_${checkType.id==null?'add':'update'}.action"
				method="post" onsubmit="">
				<table align="center" class="table">
					<tr>
						<th colspan="4">
							<font size="5">${checkType.id==null?'添加':'修改'}检验类型</font>
						</th>

					</tr>
					
					<tr>
						<th align="right">
							检查内容
						</th>
						<td>
							<input type="hidden" name="checkType.id"
								value="${checkType.id}" />
							<input id="name" name="checkType.name"
								value="${checkType.name}"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							所属类型：
						</th>
						<td>
						<select name="checkType.type" id="type">
							<option value="${checkType.type}">${checkType.type}</option>
    						<option value="生产现场">生产现场</option>
							<option value="办公环境">办公环境</option>
							<option value="公共卫生">公共卫生</option>
							<option value="系统异常">系统异常</option>
    					</select>
						</td>
					</tr>
					<tr>
						<th align="right">
							最大扣分数:
						</th>
						<td>
							<input id="maxScore" name="checkType.maxScore"
								value="${checkType.maxScore}"/>
						</td>
					</tr>
					
					<tr>
						<td align="center" colspan="4">
							<input type="submit"
								value="${checkType.id==null?'添加':'修改'}" class="input" />
							<input type="reset" value="重置" class="input" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<SCRIPT type="text/javascript">  
		</SCRIPT>
	</body>
</html>