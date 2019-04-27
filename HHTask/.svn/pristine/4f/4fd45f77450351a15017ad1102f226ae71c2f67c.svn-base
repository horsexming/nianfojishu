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
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<h2>添加仓区</h2>
			<font id="zi_font" color="red" size="5"></font>
				<form action="WarehouseAreaAction_addwarehouseArea.action" method="post" onsubmit="return check()">
					<table id="mytable">
						<tr>
							<th>
								所属仓库:
							</th>
							<td>
								<select id="wareHouseName" name="wareHouseName" onchange="changvalue(this)">
									<option value="">--请选择--</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								仓区名称:
							</th>
							<td>
								<input type="text" name="waList[0].goodHouseName" id="name0"/>
								<input type="button" value="追加" onclick="addline()"/>
								<input type="button" value="删除" onclick="deline()">
							</td>
							<td id="error_name0">
							</td>
						</tr>
						<tr>
							<th>
								仓区总面积:(m2)
							</th>
							<td>
								<input type="text" name="waList[0].goodAllArea" id="all0">
							</td>
							<td id="error_all0">
							</td>
						</tr>
						<tr>
							<th>
								仓区已用面积:(m2)
							</th>
							<td>
								<input type="text" name="waList[0].goodIsUsedArea" id="isUsed0"  onchange="useChange(0)" value="0"/>
							</td>
							<td id="error_use0">
							</td>
						</tr>
						<tr id="lastTr">
							<td colspan="2" align="center">
								<input type="submit" id="sub" value="提交" class="input" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
$(function(){
	$.ajax( {
		type : "POST",
		url : "WarehouseAreaAction_getAllwarehouse.action",
		data : {
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$(data).each(function(){
						$("#wareHouseName").append('<option value='+this.name+'>'+this.name+'</option>');
					});
				$("#wareHouseName").tinyselect();
			}
		}
	})
})

var index = 1;
function addline(){
	var newline = '<tr><th>仓区名称:</th><td><input type="text" name=waList['+index+'].goodHouseName  id="name'+index+'" /></td></tr>';
	var newline1 = '<tr><th>仓库总面积:(m2)</th><td><input type="text" name=waList['+index+'].goodAllArea  id="all'+index+'"  /></td></tr>';
	var newline2 = '<tr><th>仓库已用面积:(m2)</th><td><input type="text" name=waList['+index+'].goodIsUsedArea id="isUsed'+index+'"  onchange="useChange('+index+')" value="0"/></td></tr>';
	
	$("#lastTr").before(newline);
	$("#lastTr").before(newline1);
	$("#lastTr").before(newline2);
	
	index++;
	
}
function deline(){
	var n = $('#mytable tr').length;
	if (index == 1) {
		alert("只剩最后一项了,再删真没了");
		return;
	}
	$($('#mytable tr')[n - 2]).remove();
	$($('#mytable tr')[n - 3]).remove();
	$($('#mytable tr')[n - 4]).remove();
	index--;
}


//已用验证
function useChange(index){
	var all=document.getElementById("all"+index).value.trim();
	var use=document.getElementById("isUsed"+index).value.trim();
	
	var useObj=document.getElementById("isUsed"+index);
	var flag1=numyanzheng(useObj,null);
	if(use<0){
		alert("已用面积不能为负数");
		$("#isUsed"+index).val("0");
		$("#isUsed"+index).focus();
		return ;
	}else if(use.length==0){
		alert("已用面积不能为空");
		$("#isUsed"+index).val("0");
		$("#isUsed"+index).focus();
		return ;
	}else if(flag1==false){
			$("#isUsed"+index).val("0");
			return;
	}else if(use>all){
		alert("已用面积不能大于总面积");
		$("#isUsed"+index).val("0");
		$("#isUsed"+index).focus();
		return ;
	}
}
function check(){
	var wareHouseName =	$("#wareHouseName").val();
	if(wareHouseName == ""){
		$("#zi_font").html("请选择所属仓库");
		$("#wareHouse").focus();
		$("#wareHouse").select();
		return false;
	}else{
		var goodNameFlag=true,allFlag=true;
		for(var i=0;i<=index;i++){
			var allVal=$("#all"+i).val();
			var goodNameVal=$("#name"+i).val();
			var useVal=$("#isUsed"+i).val();
			if(goodNameVal==""){
				alert("仓区名不能为空");
				$("#name"+i).focus();
				allFlag=false;
				break;	
			}else if(allVal==""){
				alert("总面积不能为空");
				$("#all"+i).focus();
				allFlag=false;
				break;
			}else if(allVal!=""){
				var allObj=document.getElementById("all"+i);
				allFlag=numyanzheng(allObj,null);
				if(!allFlag){
					break;
				}
			}
		}
	}
	
	return goodNameFlag&&allFlag;

	document.getElementById("sub").disabled="disabled"
	return true;
	
}
</SCRIPT>
	</body>
</html>
