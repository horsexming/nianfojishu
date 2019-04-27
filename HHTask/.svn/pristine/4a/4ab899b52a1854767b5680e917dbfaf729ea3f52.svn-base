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
		<script type="text/javascript">
			var index = 1;
			function addLine(){
				var str = '<tr> <th>'+ (index+1) +'</th> <td><input name="files[' + index++ +'].type"/></td> <td> <input name="attachment" type="file"/> </td> </tr>';
				$('#mytable tr:last').before($(str));
			}
			
			function removeLine(){
				if(index == 1){
					alert("就剩一行了，你再删除就没了。");
					return ;
				}
				$('#mytable tr').eq(-2).remove();
				index--;
			}
		</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong" style="width: 100%; font-weight: bold; height: 50px; " align="left">
				<div style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;" align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;" align="right">
					<a href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<form action="screenFiles_upload.action" method="post" enctype="multipart/form-data">
					<input type="hidden" name="id" value="${id }"/>
					<table id="mytable" style="width: 70%" class="table">
						<tr>
							<th>序号</th>
							<th>文件类型</th>
							<th>文件</th>
						</tr>
						<tr>
							<th>1</th>
							<td><input name="files[0].type"/></td>
							<td>
								<input name="attachment" type="file"/>
								<input type="button" onclick="addLine();" value="追加"/>
								<input type="button" onclick="removeLine();" value="删除"/>
							</td>
						</tr>
						<tr>
							<th colspan="8">
								<input class="input" type="submit"/>
							</th>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
