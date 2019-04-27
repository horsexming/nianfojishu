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
<script type="text/javascript">
	var index = 1;
	function addLine(){
		$($('#mytable tr')[$('#mytable tr').length - 2]).clone().insertAfter($($('#mytable tr')[$('#mytable tr').length - 2]));
		
		var k = $($('#mytable tr')[$('#mytable tr').length - 2]).find('input');
		$(k[0]).attr('name','records[' + index + '].model');
		$(k[1]).attr('name','records[' + index + '].partNumber');
		$(k[2]).attr('name','records[' + index + '].name');
		$(k[3]).attr('name','records[' + index + '].productionDate');
		$(k[4]).attr('name','records[' + index + '].quantity');
		$(k[5]).attr('name','records[' + index + '].description');
		$(k[6]).attr('name','records[' + index + '].cause');
		$($($('#mytable tr')[$('#mytable tr').length - 2]).find('td')[0]).html(index+1);
		index++ ;
	}
	
	function removeLine(){
		if(index == 1){
			return ;
		}
		$($('#mytable tr')[$('#mytable tr').length - 2]).remove();
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
			<form action="tclaimform_add.action" method="post">
				<table class="table">
					<tr>
						<th colspan="6">索赔单</th>
					</tr>
					<tr>
						<th>已方负责人</th>
						<td> <input name="claimform.ourPerson" style="width: 70px"/> </td>
						<th>对方单位</th>
						<td> <input name="claimform.otherCompany"/> </td>
						<th>登记时间</th>
						<td> <input name="claimform.regDate" readonly="readonly" class="Wdate" onClick="WdatePicker()"/> </td>
					</tr>
					<tr>
						<th>索赔人</th>
						<td> <input name="claimform.otherPerson" style="width: 70px"/> </td>
						<th>联系电话</th>
						<td> <input name="claimform.otherPhone"/> </td>
						<th>索赔金额</th>
						<td> <input name="claimform.claimAmount" value="0"/> </td>
					</tr>
				</table>
				<br/>
				<table id="mytable" class="table">
					<tr>
						<th>序号</th>
						<th>车型</th>
						<th>零件号</th>
						<th>名称</th>
						<th>生产日期</th>
						<th>数量</th>
						<th>故障描述</th>
						<th>原因分析</th>
					</tr>
					<tr>
						<td align="center">1</td>
						<td> <input name="records[0].model" style="width: 70px" /></td>
						<td> <input name="records[0].partNumber" /> </td>
						<td> <input name="records[0].name"/> </td>
						<td> <input name="records[0].productionDate" readonly="readonly" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM'})" style="width: 100px"/> </td>
						<td> <input name="records[0].quantity" style="width: 50px"/> </td>
						<td> <input name="records[0].description"/> </td>
						<td> <input name="records[0].cause"/> </td>
					</tr>
					
					<tr>
						<td align="center" colspan="8">
							<input type="button" value="追加" onclick="addLine();" />
							<input type="button" value="删除" onclick="removeLine();" />
							<input type="submit" value="提交">
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
