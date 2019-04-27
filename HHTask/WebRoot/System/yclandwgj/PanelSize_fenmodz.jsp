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
				粉末表处对照关系
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
							<input type="submit" value="添加" class="input" />
							<input type="button" value="查询" class="input"
								onclick="QueryCode(this.form)" />
						</td>
					</tr>
				</table>
			</form>
			<table class="table">
				<tr align="center" bgcolor="#c0dcf2" height="50px">
					<th>
						序号
					</th>
					<th>
						BOM表表处
					</th>
					<th>
						外购件库表处
					</th>
					<th>
						操作
					</th>
				</tr>
				<s:iterator value="codeTranslationList" id="cta" status="statussdf">
					<s:if test="#statussdf.index%2==1">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
					</s:if>
					<s:else>
						<tr align="center" onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'')">
					</s:else>
					<td>
						<s:property value="#statussdf.index+1" />
					</td>
					<td>${cta.keyCode}</td>
					<td>${cta.valueCode}</td>
					<td>
						<input type="button" value="修改"
								style="width: 60px; height: 30px;"
								onclick="update(${cta.id},'${cpage}','${tag}')" />
							<input type="button" value="删除"
								style="width: 60px; height: 30px;"
								onclick="todelete(${cta.id},'${cpage}','${tag}')" />
					</td>
				</s:iterator>
				<tr>
								<td colspan="30" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />

								</td>
							</tr>
			</table>

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