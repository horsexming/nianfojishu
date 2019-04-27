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
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/zTree/js/jquery.ztree.excheck-3.5.js">
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center" style="width: 40%;float: left;" >
				<select id="selectgroup" onchange="changeGroup()">
					<option value="0">请选择</option>
					<s:iterator value="userGroupList" id="puserGroup" status="pstatus">
						<option value="<s:property value='#puserGroup.id'/>"><s:property value="#puserGroup.groupName"/></option>
					</s:iterator>
				</select>
			</div>
			<div style="width: 20% ;float: left;" align="left">
				<ul id="usersUl">
				</ul>
			</div>
			<div style="width: 20%;float: left;" align="center">
			<br/>
			<input type="hidden" id="selectUserId" >
			<label id="selectUsers"></label>
			<br/>
			<input type="button" value="确定" onclick="selectOk()" style="width: 80px;height: 60px;">
			</div>
			<div style="clear: both;">
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">


//点击回调函数
function changeGroup(event, treeId, treeNode, clickFlag) {
	$.ajax( {
		type : "POST",
		url : 'procardTemplateSbAction_getusersBygroup.action',
		data : {
					id : $("#selectgroup").val(),
					ids : $("#selectUserId").val()
		},
		dataType : 'json',
		cache : false,//防止数据缓存
		success : function(allDdept) {
			var n=0;
			$(allDdept).each(function() {
						if(n==0){
							$("#usersUl").empty();
						}
						var checed="";
						if($(this).attr("selected")){
							checed="checked='checked'";
						}
						$("<li><input id='checkboxs"+n+"' data='"+this.dept+ "-("+ this.code+")" + this.name +"' type='checkbox' name='checkboxs' "+checed+" value='"+$(this).attr("id")+"' onchange='selectUser("+n+")'>"
						 + this.code + "-"+ this.name + "</li>").appendTo("#usersUl");
						n++;
					});
		}

	});
}

function selectUser(n){
	var val=$("#checkboxs"+n).val();
	var val2=$("#checkboxs"+n).attr("data");
	var ids=$("#selectUserId").val();
	var dapts=$("#selectUsers").html();
	 if(ids==null||ids.length==0){
		$("#selectUserId").val(val);
		$("#selectUsers").html(val2);
		return ;
	}
	var idsValue=";"+ids+";";
	var daptsValue=";"+dapts+";";
	var index=idsValue.indexOf(";"+val);
	if(index>=0){
			idsValue=idsValue.replace(";"+val+";", ";");
			daptsValue=daptsValue.replace(";"+val2+";", ";");
		}else{
			idsValue=idsValue+val+";";	
			daptsValue=daptsValue+val2+";";	
		}
	if(idsValue.length>1){
		var newids = idsValue.substring(1,idsValue.length-1);
		$("#selectUserId").val(newids);
		var newdapts = daptsValue.substring(1,daptsValue.length-1);
		$("#selectUsers").html(newdapts);
	}else{
		$("#selectUserId").val("");
		$("#selectUsers").html("");
	}
}
function selectOk(){
	var idname1 = "${idname1}";
	var idname2 = "${idname2}";
	var idname3 = "${idname3}";
	parent.$("#"+idname1).val($("#selectUserId").val());
	parent.$("#"+idname2).val($("#selectUsers").html());
	parent.$("#"+idname3).html($("#selectUsers").html());
	parent.chageDiv('none');
}
</script>
	</body>
</html>
