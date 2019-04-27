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
			<br />
			<form action="codeTranslationAction_importFile.action" method="post"
				enctype="multipart/form-data">
				选择导入文件:
				<input type="file" name="uploadFile" />
				<a href="<%=basePath%>/upload/file/download/codeTranslation.xls">导入模版下载</a>
				<a href="FileViewAction.action?FilePath=/upload/file/download/codeTranslation.xls&Refresh=true">/预览</a>
				<input type="submit" value="批量导入" id="sub" />
			</form>
			<br />
			<form
				action="codeTranslationAction_${codeTranslation.id==null?'add':'update'}.action"
				method="post" onsubmit="">
				<table align="center" class="table">
					<tr>
						<th colspan="4">
							<font size="5">${codeTranslation.id==null?'添加':'修改'}编码</font>
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
							编码：
						</th>

						<td>
							<input type="hidden" name="codeTranslation.id"
								value="${codeTranslation.id}" />
							<input id="keyCode" name="codeTranslation.keyCode"
								value="${codeTranslation.keyCode}" onchange="findSame()" />
							<label id="message"></label>
						</td>

						<th align="right">
							类型:
						</th>
						<td>
							<select name="codeTranslation.type" id="type">
								<s:if test="codeTranslation.type!=''">
								<option value="${codeTranslation.type}">
									${codeTranslation.type}
								</option>
								</s:if>
								<option value="编码">
									编码
								</option>
								<option value="国标">
									国标
								</option>
								<option value="技术规范">
									技术规范
								</option>
							</select>
						</td>
						<tr>
							<th align="right">
								件号:
							</th>
							<td>
								<input id="valueCode" name="codeTranslation.valueCode"
									value="${codeTranslation.valueCode}" />
							</td>
							<th align="right">
								名称:
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
                 alert("国标号已经存在");    
               } 
		}
	});
   } 
      
		</SCRIPT>
	</body>
</html>