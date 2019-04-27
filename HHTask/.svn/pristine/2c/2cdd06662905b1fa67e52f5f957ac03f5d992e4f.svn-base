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
			<h2>
				修改粉末表处对照关系
			</h2>
			<form action="codeTranslationAction_add.action" method="post"
				onsubmit="">
				<table align="center" class="table">
					<tr>
						<th align="right">
							BOM表表处
						</th>

						<td>
							<input id="keyCode" name="codeTranslation.keyCode"
								value="${codeTranslation.keyCode}" onchange="findSame()" />
							<label id="message"></label>
						</td>
						<th align="right">
							外购件库表处
						</th>
						<td>
							<input id="valueCode" name="codeTranslation.valueCode"
								value="${codeTranslation.valueCode}" />
						</td>
					</tr>
					</tr>

					<tr>
						<td align="center" colspan="4">
							<input type="hidden" value="粉末" name="codeTranslation.type" />
							<input type="hidden" value="${tag}" name="tag" />
							<input type="hidden" value="${codeTranslation.id}" name="codeTranslation.id" />
							<input type="submit" value="修改" class="input" />
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
     function QueryCode(obj){
    	 obj.action="codeTranslationAction_QueryCode.action";
    	 obj.submit();
    	 obj.action="codeTranslationAction_add.action";
     } 
     
  function update(id,cpage,tag) {
	window.location.href = "codeTranslationAction_toAdd.action?codeTranslation.id=" + id+"&cpage="+cpage+"&tag="+tag;
}
function todelete(id,cpage,tag) {
	if(confirm('确定要删除吗?')){
		window.location.href = "codeTranslationAction_delete.action?codeTranslation.id="+ id+"&cpage="+cpage+"&tag="+tag;
	}
	
}
		</SCRIPT>
	</body>
</html>