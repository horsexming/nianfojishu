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
				action="codeTranslationAction_${codeTranslation.id==null?'add':'update'}.action"
				method="post" onsubmit="">
				<table align="center" class="table">
					<tr>
						<th colspan="4">
							<font size="5">${codeTranslation.id==null?'添加':'修改'}系统设置</font>
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
							设置项：
						</th>

						<td>
							<input type="hidden" name="codeTranslation.id"
								value="${codeTranslation.id}" />
							<input id="keyCode" name="codeTranslation.keyCode"
								value="${codeTranslation.keyCode}" onblur="findSame()" />
							<label id="message"></label>
						</td>

						<th align="right">
							类型:
						</th>
						<td>
							<input  name="codeTranslation.type"
								value="${codeTranslation.type}"/>
							<input type="hidden" name="tag" value="sys">
						</td>
						<tr>
							<th align="right">
								设置内容:
							</th>
							<td>
								<input id="valueCode" name="codeTranslation.valueCode"
									value="${codeTranslation.valueCode}" />
							</td>
							<th align="right">
								简称:
							</th>
							<td>
								<input id="valueName" name="codeTranslation.valueName"
									value="${codeTranslation.valueName}" />
							</td>
						</tr>
					</tr>

					<tr>
						<td align="center" colspan="4">
							<input type="submit"
								value="${codeTranslation.id==null?'添加':'修改'}" class="input" />
							<input type="reset" value=" 重置" class="input" />
						</td>
					</tr>
				</table>
			</form>
		</div>

		<SCRIPT type="text/javascript">
		function findSame(){
		var code = document.getElementById("keyCode").value;
		$.ajax( {
		type : "POST",
		url:"codeTranslationAction_checkName.action",
		data : {
			code:code
		},
		dataType : "json",
		success : function(data) {
			 if(data=="err"){     
                 alert("设置项已经存在");    
               } 
		}
	});
   } 

</SCRIPT>
</body>
</html>