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
			$(function(){
				$("#addBtn").bind('click',function(){
					$($('#mytable tr')[$('#mytable tr').length - 1]).before("<tr> <td><input name='toolings[" + index+ "].project.id' type='hidden' value='${tooling.project.id}'> <input name='toolings[" + index + "].numb'/> </td> <td> <input name='toolings[" + index + "].name'/> </td> <td> <select name='toolings[" + index + "].state'> <option value='已有'>已有</option> <option value='请制'>请制</option> </select> </td> <td> <input name='toolings[" + index + "].amount'/> </td> <td> <input name='toolings[" + index + "].specification'/> </td><td> <input name='toolings[" + index + "].notes'/> </td> </tr>");
					index++;
				})
				$("#removeBtn").bind('click',function(){
					if(index == 1){
						return ;
					}
					$($('#mytable tr')[$('#mytable tr').length - 2]).remove();
					index--;
				})
			});
			
			
		</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<form action="ttooling_add.action" method="post">
					<table id="mytable" class="table" style="width: 80%">
						<tr>
							<th>工装号</th>
							<th>工装名称</th>
							<th>状态</th>
							<th>金额</th>
							<th>型号</th>
							<th>备注</th>
						</tr>
						<tr>
							<td>
								<input name="toolings[0].project.id" type="hidden" value="${tooling.project.id}">
								<input name="toolings[0].numb"/>
							</td>
							<td>
								<input name="toolings[0].name"/>
							</td>
							<td>
								<select  name="toolings[0].state">
									<option value="已有">已有</option>
									<option value="请制">请制</option>
								</select>
							</td>
							<td>
								<input  name="toolings[0].amount"/>
							</td>
							<td>
								<input name="toolings[0].specification"/>
							</td>
							<td>
								<input name="toolings[0].notes"/>
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input id="addBtn" type="button" value="追加" />&nbsp;
								<input id="removeBtn" type="button" value="删除" />&nbsp;
								<input type="submit" value="确定" />
							</td>
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
