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
					$($('#mytable tr')[$('#mytable tr').length - 1]).before('<tr><td><input name="equipments[' + index + '].project.id" type="hidden" value="${equipment.project.id}" /><input name="equipments[' + index + '].name" style="width: 100"/></td> <td><input name="equipments[' + index + '].equipmentType" style="width: 100"/></td> <td><input name="equipments[' + index + '].deviceNumber" style="width: 100"/></td> <td><input name="equipments[' + index + '].equipmentValue" style="width: 100"/></td> <td><input name="equipments[' + index + '].residual" style="width: 100"/></td> <td><input name="equipments[' + index + '].productionYear" readonly="readonly" class="Wdate" onClick="WdatePicker({dateFmt:\'yyyy-MM月\',skin:\'whyGreen\'})"  style="width: 100"/></td> <td><input name="equipments[' + index + '].location" style="width: 100"/></td> <td><input name="equipments[' + index + '].factory" style="width: 100"/></td> <td><input name="equipments[' + index + '].power" style="width: 100"/></td></tr>');
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
				<form action="tequipment_add.action" method="post">
					<table id="mytable" class="table">
						<tr>
							<th>设备名称</th>
							<th>设备型号</th>
							<th>设备编号</th>
							<th>设备价值</th>
							<th>设备残值</th>
							<th>生产年份</th>
							<th>所在地</th>
							<th>制造厂</th>
							<th>功率</th>
						</tr>
						<tr>
							<td>
							<input name="equipments[0].project.id" type="hidden" value="${equipment.project.id}" /> 
							<input name="equipments[0].name" style="width: 100"/></td>
							<td><input name="equipments[0].equipmentType" style="width: 100"/></td>
							<td><input name="equipments[0].deviceNumber" style="width: 100"/></td>
							<td><input name="equipments[0].equipmentValue" style="width: 100"/></td>
							<td><input name="equipments[0].residual" style="width: 100"/></td>

							<td><input name="equipments[0].productionYear" readonly="readonly" style="width: 100" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})" /></td>
							<td><input name="equipments[0].location" style="width: 100"/></td>
							<td><input name="equipments[0].factory" style="width: 100"/></td>
							<td><input name="equipments[0].power" style="width: 100"/></td>
						</tr>
						<tr>
							<td colspan="9" align="center">
								<input id="addBtn" type="button" value="追加" />&nbsp;
								<input id="removeBtn" type="button" value="删除" />&nbsp;
								<input type="submit" value="确定"/>
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
