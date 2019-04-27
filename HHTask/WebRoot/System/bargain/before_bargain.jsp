<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
body {
	text-align: center;
}
</style>

		<%@include file="/util/sonHead.jsp"%>
	</head>
	<%
		Users user = (Users) session.getAttribute("Users");
	%>

	<body style="height: 4000px;">
		<form action="bargainAction_jumpaddBargain.action" method="post"
			onsubmit="return check()">
			<table align="center" class="table">
				<tr>
					<td align="center" colspan="2">
						<h1>
							询比议价缘由
						</h1>
					</td>
				</tr>
				<tr>
					<th>
						议价来源
					</th>
					<th align="left">
						<select id="source" name="bargain.bargain_source">
							<option value="">
								--请选择议价来源--
							</option>
							<option value="OA">
								项目编号
							</option>
							<option value="SB">
								设备维修
							</option>
							<option value="设备">
								设备
							</option>
							<option value="KVP">
								KVP
							</option>
							<%--    				<option value="XBYJ">询比议价</option>--%>
							<%--    				<option value="QT">其他</option>--%>
							<option value="紧急采购">
								紧急采购
							</option>
							<option value="零部件及工序外委采购">
								零部件及工序外委采购
							</option>
							<option value="原材料采购">
								原材料采购
							</option>
							<option value="外购工装">
								外购工装
							</option>
							<option value="行政事务采购">
								行政事务采购
							</option>
						</select>
					</th>
				</tr>
				<tr>
					<th>
						单号
					</th>
					<th align="left" id="dhTh1">
						<select id="number" name="bargain.bargain_num">
						</select>
					</th>
					<td align="left" id="dhTh2" style="display: none">
					</td>
				</tr>
				<br />
				<tr>
					<th colspan="2">
						<input type="submit" value=" 下一步">
					</th>
				</tr>
			</table>
		</form>
		<%@include file="/util/foot.jsp"%>
	</body>
	<script type="text/javascript">
$("#source").change(function() {
	getClass();
});
function getClass() {
	var source = $("#source").val();
	$.ajax( {
				url : "bargainAction_findbargainSource1.action",
				type : 'post',
				dataType : 'json',
				cache : false,//防止数据缓存
				data : {
					"source" : source
				},
				success : function(data) {
					$("#number").empty();//清空
					if(source=='零部件及工序外委采购'){
						$("#dhTh1").hide();
						$("#dhTh2").show();
					}else{
						$("#dhTh1").show();
						$("#dhTh2").hide();
					}
					var html="";
					$.each(data,function(i,n){
						if(source=='OA'){
							if(i==0){
								$("#dhTh1").show();
								$("#number").append("<option value=''>--请选择OA单号--</option>");
							}
							$("#number").append("<option value='" + n.detailSeqNum+ "'>"+n.detailSeqNum+"("+n.detailFormat+")</option>");
						}if(source=='SB'){
							if(i==0){
								$("#number").append("<option value=''>--请选择设备维修单号--</option>");
							}
							$("#number").append("<option value='" + n.barcode+ "'>"+ n.barcode +"</option>");
						}if(source=='KVP'){
							if(i==0){
								$("#number").append("<option value=''>--请选择KVP单号--</option>");
							}
							$("#number").append("<option value='"+n+"'>"+n+"</option>");
						}
						if(source=='设备'){
							if(i==0){
								$("#number").append("<option value=''>--请选择设备单号--</option>");
							}
							$("#number").append("<option value='"+n.no+"'>"+n.no+"</option>");
						}
						if(source=='XBYJ'){
							if(i==0){
								$("#number").append("<option value=''>--请选择询比议价单号--</option>");
							}
							$("#number").append("<option value='"+n.bargain_number+"'>"+n.bargain_number+"</option>");
						}if(source=='紧急采购'){
							$("#number").append("<option value='"+n+"'>"+n+"</option>");
						}
						if(source=='行政事务采购'){
							$("#number").append("<option value='"+n+"'>"+n+"</option>");
						}
						if(source=='原材料采购'){
							$("#number").append("<option value='"+n+"'>"+n+"</option>");
						}
						if(source=='零部件及工序外委采购'){
<%--							$("#number").append("<option value='"+n+"'>"+n+"</option>");--%>
                            
                        	 html +="<div style='width:220px;float:left;'><input type='checkbox' id='osaIds' name='ids' value='"+n.id+"'>"+n.markID+","+n.processNO+"&nbsp;</div>"
                         if((i+1)%5==0){
                        	 html +="<br/>"
                         }
						}
						if(source=='外购工装'){
							$("#number").append("<option value='"+n.id+"'>"+n.markId+"("+n.proName+")"+"</option>");
						}
					})
						$("#dhTh2").html(html);
				},
				error : function() {
					alert("服务器异常!");
				}
			});
		}
  	function check(){
  		var source = document.getElementById("source");
  		var number = document.getElementById("number");
  		if(source.value==""){
  			alert("请选择议价来源!");
  			source.focus();
  			return false;
  		}else if(source.value=='零部件及工序外委采购'){
  			if($("#osaIds").val()==null||$("#osaIds").val()==""){
  				alert("请选择零件或工序!");
  			return false;
  			}
  		}else if(number.value==""){
  			alert("请选择单号!");
  			number.focus();
  			return false;
  		}
  		
  	}
  	
  	</script>

</html>
