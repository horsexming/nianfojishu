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
			<h2>选择件号</h2>
				<form action="PtCNFXAction_changnengfenxi.action" method="post" onsubmit="return check()" >
					<table id="mytable" class="table">
					</table>
					<div id="se_div"></div>
					<input type="submit" value="选择" class="input" id="sub"/>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
var n=1;
$(function(){
	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!getProcardTemplateZC.action",
		data : {
		},
		dataType : "json",
		success : function(data) {
			if(data!=null && data.length>0){
				var showTable="<tr>";
				$("#mytable").append();
				for(var i=0; i<data.length; i++){
					var pt = data[i];
					showTable+=
						'<td><input type="checkbox" value="' + pt[0]+'_'+pt[1]
								+ '" name="checkbox"/>' +pt[0]
							+ "</td>";
					 if(n%5==0){
						showTable+="</tr><tr>";
					}
					n++;
				}
				$("#mytable").append(showTable+"</tr>");
				
			}
			
		}
	})
	
})
function check(){
	var checkbox =	document.getElementsByName("checkbox");
	var bool = true;
	if(checkbox!=null && checkbox.length>0){
		for(var i=0; i<checkbox.length ; i++){
			if(checkbox[i].checked == true){
				
				bool = false;
				var value = checkbox[i].value;
				var array = value.split("_");
				
 			$("#se_div").append('<input type="hidden" value="'+array[0] +'" name="markIdS">' +
 			' <input type="hidden" value="'+array[1] +'" name="capacityS">');
 		}
		}
		
	}
	if(bool){
		alert('请至少选择一个件号');
		return false;
	}
	document.getElementById("sub").disabled="disabled"
}


</SCRIPT>
	</body>
</html>
