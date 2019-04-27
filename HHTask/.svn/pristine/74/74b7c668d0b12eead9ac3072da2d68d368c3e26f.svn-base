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
		<script type="text/javascript" src="javascript/jquery-1.7.2.min.js"></script>
		<script type="text/javascript">
			$(function(){
				//给搜索按钮的表单提交为ajax
				$("#searchButton").click(function(){
					var params = $('#searchByMdelsFrom select').serialize(); //序列化表单的值，与prototype中的form.serialize()相同 
					$.ajax({ 
						url : 'Templet!searchByModelAll.action', //后台处理程序 
						type:'post', //数据发送方式 
						dataType:'json', //接受数据格式 
						data:params, //要传递的数据 
						success : function(msg){
							var obj = msg;
							if(obj.success){
								initSelect(obj.data);
							} else {
								alert(obj.message);
							}
						}  
					}); 
				});
			});
			
			//初始化select
			function initSelect(data){
				$('#productTemplet').html('');
				for(var i = 0; i<data.length; i++){
					document.getElementById("productTemplet").options.add(new Option(data[i].partsNumber,data[i].id));
				}
			}
			//给所有的文本框去除首尾空格
			function checkTrim(ele) {
				ele.value = ele.value.replace(/^\s+|\s+$/g, "");
			}
			function checkAll(){
				if(document.getElementById("quantity").value == ""){
					alert("请填写数量");
					return false;
				}
				if(document.getElementById("month").value == ""){
					alert("请选择月份");
					return false;
				}
				if(document.getElementById("detailNumber").value == ""){
					alert("请填写计划单编号");
					return false;
				}
				return true;
			}
			
		</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="javascript:location.reload();"
						style="color: #ffffff">刷新</a>
					<a
						href="Detail_listInput.action"
						style="color: #ffffff">查看所有计划</a>	
				</div>
			</div>
			
			<div align="center">
				<div><font color="red">${successMessage}${errorMessage}</font></div>
				<div>
					<form id="searchByMdelsFrom" action="" method="post">
						根据车型查找：
						<select name="templet.models">
							<s:iterator value="models" id="m">
								<option value="${m}">${m}</option>
							</s:iterator>
						</select>
						<input id="searchButton" type="button" value="查找">
					</form>
				</div>
				<form action="Detail_add.action" method="post">
			  		<table border="0" style="border-collapse: collapse;" >
			  			<tr><th>产&nbsp;品&nbsp;件&nbsp;号&nbsp;:</th>
			  			<td>
			  			<select id="productTemplet" name="templets.id" style="width: 155px">
						 	<s:iterator value="templets" id="t">
						 		 <option value ="${t.id}">${t.partsNumber}</option>
						 	</s:iterator>
						</select>
			  			
			  			</td></tr>
			  			<tr><th align="right">数量:</th><td><input id="quantity" style="width: 155px" name="detail.quantity" onblur="checkTrim(this);" onkeyup="if(isNaN(value))execCommand('undo')" onchange="if(isNaN(value))execCommand('undo')"  ></td></tr>
			  			<tr><th align="right">月份:</th><td><input id="month" style="width: 155px" class="Wdate" type="text" name="detail.month" 	onclick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" /></td></tr>
			  			<tr><th align="right">订单编号:</th><td><input id="detailNumber" style="width: 155px" name="detail.detailNumber" onblur="checkTrim(this);"  ></td></tr>
			  			<tr><th align="right">说明:</th><td><input id="explanation" style="width: 155px" name="detail.explanation" onblur="checkTrim(this);" ></td></tr>
			  			<tr><td align="center"><input style="height: 30px; width: 70px;" onclick="return checkAll();" type="submit" value="提交"></td><td align="center" ><input style="height: 30px; width: 70px;" type="reset" value="重置"><br /></td></tr>
			  		
			  		</table>
			  	</form>	
			  	
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>

</html>
