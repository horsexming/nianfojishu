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
	<STYLE type="text/css">
		.input_a{width: 80px;}
	</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<font id="zi_font" color="red" size="5">${errorMessage}</font>
				<form action="IntelligentDiagnosisAction_updatepn.action" method="post"  onsubmit="return check()">
					<table id="mytable">
						<tr id="tr_0">
							<th>流程内容:</th>
							<td>
								<input type="text" name="pn.name" id="name" value="${pn.name}"/>
								<input type="hidden" value="${pn.id}" name="pn.id" >
							</td>
							<th>是否能节约成本:</th>
							<td>
								<s:if test="pn.iseconomize == 'yes'">
									<input type="radio" checked="checked" value="yes" name="pn.iseconomize" id="yes_0">是
									<input type="radio"  value="no" name="pn.iseconomize" id="no_0">否	
								</s:if>
								<s:else>
									<input type="radio"  value="yes" name="pn.iseconomize" id="yes_0">是
									<input type="radio"  value="no" name="pn.iseconomize" id="no_0" checked="checked"> 否
								</s:else>
							</td>
						</tr>
					</table>
						<input type="submit" value="修改" style="width: 75px;height: 35px;" />
						<input type="reset" value="重置" style="width: 75px;height: 35px;" />
				</form>
					<input type="hidden" id="rebeack" value='${errorMessage}'/>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
<SCRIPT type="text/javascript">
var index=1;
function addLine() {
	var newLine = '<tr id=tr_'+index+' align="center"> <th>流程内容:</th></td>  ' +
	' <input type="text" name="pnList['+index+'].name" id="name_'+index+'"/>' +
	'<input type=button onclick=delLine("tr_'+index+'") value=删除 />' +
	'</td></tr>' 
	$("#mytable").append(newLine);
	index++;
}

function delLine(obj) {
	if(index<=1){
		alert('再删就没有了哦。')
	}else{
	$("#"+obj).remove();
	index--;
	var n = $('#mytable tr').length;
	for(var i=0;i<index;i++){
		var id=	$('#mytable tr')[n-(i+1)].id 
		var num =id.split('_');
		if(num.length == 2){
			$("name_"+num[1]).attr('name','pnList['+(index-i-1)+'].name');			
		}
		
	}
	}
}
function changvalue(obj){
	if(obj!=null && obj.value!=""){
		var value = obj.value;
		$("#cp_span").html("<font color='red' size='5'>"+value+"</font>企业的在线智能诊断!");
	}
}





function check(){
	
		var name = document.getElementById('name');
		if(name!=null && name.value == ''){
			$("#zi_font").html('请填写流程内容');
			name.focus();
			return false;
		}
	document.getElementById('sub').disabled="disabled";
	return true;
	
}
$(document).ready(function(){
		var rebeack=$("#rebeack").val();
		if(rebeack =="修改成功"){
			alert(rebeack);
			parent.chageDiv('none');
			parent.window.location.reload();
		}
	})
</SCRIPT>
	</body>
</html>
