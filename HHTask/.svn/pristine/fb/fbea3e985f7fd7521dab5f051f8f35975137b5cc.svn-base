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
				<form id="yjForm">
				<input type="hidden" name="id" value="${id}">
				<select id="selectwork" name="id2" >
				</select>
				</form>
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
						$("<option value ='"+$(this).attr("id")+"'>"+ this.code + "-"+ this.name + "</option>").appendTo("#selectwork");
						n++;
					});
		}

	});
}

function selectOk() {
	$.ajax( {
		type : "POST",
		url : "procardTemplateSbAction_transferWork.action",
		data : $("#yjForm").serialize(),
		dataType : "json",
		cache : false,//防止数据缓存
		success : function(data) {
			if(data=="true"){
				alert("移交成功!");
				parent.location.href="procardTemplateGyAction_showSbApplyJd2.action?bbAply.id=${id}";//刷新父页面
			}else{
				alert(data);
			}
		}

	});
}

</script>
	</body>
</html>
