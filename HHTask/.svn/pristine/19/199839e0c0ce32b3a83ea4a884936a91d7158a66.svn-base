<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@include file="/util/sonHead.jsp"%>
		<SCRIPT type="text/javascript">
			var i = 2;
			function appendTr(){
				$($('#mytable tr')[$('#mytable tr').length - 2]).after('<tr><td align="center"><input name="details[' + i + '].xunhao" style="width: 50"/></td><td align="center"><input name="details[' + i + '].partNumber" /> </td><td align="center"><input name="details[' + i + '].name" style="width: 150"/> </td><td align="center"><input name="details[' + i + '].singleNumber" style="width: 20"/> </td><td align="center"><select name="details[' + i + '].unit" ><option>件</option><option>只</option><option>千克</option></select></td><td align="center"><select name="details[' + i + '].type"><option>组合件</option><option>外购件</option><option>自制件</option></select></td><td align="center"><input name="details[' + i + '].material" style="width: 100"></td><td align="center"><input name="details[' + i + '].standard" style="width: 100"></td><td align="center"><input name="details[' + i + '].specification" style="width: 100"></td></tr>');
				i++;
			}
			
			function removeTr(){
				if($('#mytable tr').length <= 5){
					return;
				}
				$('#mytable tr')[$('#mytable tr').length - 2].remove();
				i--;
			}
		</SCRIPT>
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
				<form action="tdetail_add.action" method="post">
					<table id="mytable" class="table" style="width: 95%">
						<tr>
							<th colspan="9">请添加总成</th>
						</tr>
						<tr>
							<th>总成名称</th>
							<td colspan="2">
								<input name="details[0].name" />
								<input name="details[0].type" type="hidden" value="总成"/>
								<input name="details[0].project.id" type="hidden" value="${detail.project.id}"/>
							</td>
							<th>总成件号</th>
							<td colspan="4"><input name="details[0].partNumber" /></td>
							<th>
								<a href="javascript:void(0)" onclick="appendTr();">添加</a> 
								<a href="javascript:void(0)" onclick="removeTr();">删除</a> 
							</th>
						</tr>
						<tr>
							<td align="center">序号</td>
							<td align="center">件号</td>
							<td align="center">名称</td>
							<td align="center">单台数量</td>
							<td align="center">单位</td>
							<td align="center">类型</td>
							<td align="center">材料</td>
							<td align="center">标准</td>
							<td align="center">规格</td>
						
						</tr>
						<tr>
							<td align="center"><input name="details[1].xunhao" style="width: 50"/></td>
							<td align="center"><input name="details[1].partNumber" /> </td>
							<td align="center"><input name="details[1].name" style="width: 150"/> </td>
							<td align="center"><input name="details[1].singleNumber" style="width: 20"/> </td>
							<td align="center">
								<select name="details[1].unit" >
									<option>件</option>
									<option>只</option>
									<option>千克</option>
								</select>
							</td>
							<td align="center">
								<select name="details[1].type">
									<option>组合件</option>
									<option>外购件</option>
									<option>自制件</option>
								</select>
							</td>
							<td align="center">
								<input name="details[1].material" style="width: 100">
							</td>
							<td align="center">
								<input name="details[1].standard" style="width: 100">
							</td>
							<td align="center">
								<input name="details[1].specification" style="width: 100">
							</td>
						</tr>
						<tr>
							<td colspan="9" align="center">
								<input type="submit" />
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
