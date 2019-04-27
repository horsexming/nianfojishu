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
				action="CompetenceTypeAction_${competenceType.id==null?'add':'update'}.action"
				method="post" onsubmit="">
				<table align="center" class="table">
					<tr>
						<th colspan="4">
							<font size="5">${competenceType.id==null?'添加':'修改'}权限编码</font>
						</th>

					</tr>
					<tr>
						<th colspan="4">
							<center>

							</center>
						</th>
					</tr>
					<tr>
						<th align="right">
							权限名称：
						</th>

						<td>
							<input type="hidden" name="competenceType.id"
								value="${competenceType.id}" />
							<input id="name" name="competenceType.name"
								value="${competenceType.name}" />
							<label id="message"></label>
						</td>

						<th align="right">
							权限编码:
						</th>
						<td>
							<input id="code" name="competenceType.code"
								value="${competenceType.code}" />
						</td>
						
					</tr>

					<tr>
						<td align="center" colspan="4">
							<input type="submit"
								value="${competenceType.id==null?'添加':'修改'}" class="input" />
							<input type="reset" value=" 重置" class="input" />
						</td>
					</tr>
				</table>
			</form>
		</div>

		<SCRIPT type="text/javascript">
      $(function(){
    	  if('${successMessage}' =='添加成功'){
    		parent.window.location.reload();  
    	  }
    	  
      })
		</SCRIPT>
	</body>
</html>