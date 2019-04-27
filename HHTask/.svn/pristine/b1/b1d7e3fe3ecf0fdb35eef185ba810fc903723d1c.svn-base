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
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
					<a href="Detail_listInput.action" style="color: #ffffff">查看所有订单</a>
				</div>
			</div>
			
			<div align="center">
				<form action="Detail_update.action" method="post">
					<div> <s:property value="#parameters.successMessage" /> </div>
					<input type="hidden" name="detail.id" value="${detail.id}" />
			  		<table border="0" style="border-collapse: collapse;" >
			  			
			  			<tr><th>产品名称</th>
			  			<td>
			  			<select name="templets[0].id">
						 	<s:iterator value="templets" id="t">
						 		<s:if test="#t.id == detail.templet.id.intValue()">
						 			 <option value ="${t.id}" selected="selected">${t.name}</option>
						 		</s:if><s:else>
						 			 <option value ="${t.id}">${t.name}</option>
						 		</s:else>
						 	</s:iterator>
						</select>
			  			
			  			</td></tr>
			  			<tr><th>数量:</th><td><input id="quantity" name="detail.quantity" value="${detail.quantity}" onblur="checkTrim(this);"  onkeyup="if(isNaN(value))execCommand('undo')" onchange="if(isNaN(value))execCommand('undo')" ></td></tr>
			  			<tr><th>月份:</th><td><input id="month" class="Wdate" type="text" name="detail.month" value="${detail.month}"	onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" /></td></tr>
			  			<tr><th>备注:</th><td><input id="explanation" onblur="checkTrim(this);" name="detail.explanation" value="${detail.explanation}" ></td></tr>
			  			<tr><td align="center" colspan="2"><input style="width: 80px; height: 50px;" onclick="return checkAll();" type="submit" value="提交"><br /></td></tr>
			  		
			  		</table>
			  	</form>	
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>

</html>
