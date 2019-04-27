<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<%@include file="/util/inc.jsp"%>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/zTree/js/jquery.ztree.excheck-3.5.js">
</script>
		<base href="<%=basePath%>">
		<title>指派管理</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<style type="text/css">
.tag_1 {
	padding-top: 4px;
	width: 140px;
	height: 30px;
	background: url('<%=path%>/admin/images/tags1_1.jpg');
}

.tag_2 {
	padding-top: 4px;
	width: 140px;
	height: 30px;
	background: url('<%=path%>/admin/images/tags1_2_2.jpg');
}
</style>
<script type="text/javascript">
$(function() {
	//加载所有分组
	$.ajax( {
		type : "post",
		url : "GzstoreAction_getdept.action",
		dataType : "json",
		success : function(data) {
			//填充部门信息
			$(data).each(
					function() {
						$(
								"<option value='" + this.id + "'>" + this.dept
										+ "</option>").appendTo("#deptname");
					});

			//绑定选择分组
			$("#deptname").bind(
					"click",
					function() {
						$.ajax( {
							type : "post",
							url : "GzstoreAction_getusers.action",
							data : {
								id : $("#deptname").val()
							},
							dataType : "json",
							success : function(data) {
								$("#username").empty();
								$("<option value='0'>请选择人员</option>").appendTo(
										"#username");
								$(data).each(
										function() {
											$(
													"<option value='" + this.id
															+ "'>" + this.name
															+ "</option>")
													.appendTo("#username");
										});
							}
						});
					});
		}
	});
});
</script>
	


		<link rel="stylesheet" href="css/css.css" type="text/css"></link>
	</head>

	<body bgcolor="#ffffff" onload="createDept('dept')">
		<center>
			<div style="width: 1000px;">
				<div id="xitong" align="center"
					style="width: 100%; height: 31px; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; background: url('admin/images/admin_title.jpg');">
					<font color="#ffffff">${moduleFunction.functionName}</font>
				</div>
				
					<div>
						<div align="center">
							<font color="red">${successMessage}</font>
							<font color="red">${errorMessage}</font>
						</div>
						<div id="module1_1" align="center">
							<div id="bangding">
								<form action="ModuleFunctionAction!dispatchUsers.action"
									method="post" style="margin: 0px" onsubmit="return validate();">
									<input type="hidden" value="${moduleFunction.id}" name="id">
									<br>
									<table class="table">
										<tr>
							<td align="center">
							<input type="hidden" value="1" name="user.backStage">
								<select id="deptname" style="width: 100px;">
									<s:if test="users==null">
										<option value="0">
											请选择部门
										</option>
									</s:if>
									<s:else>
										<option value="<s:property value="users.deptId"/>">
											<s:property value="users.dept" />
										</option>
									</s:else>
								</select>

							</td>
							<td align="center">
							<input type="hidden" value="0" id="deptIds" name="deptIds">
								<select id="username" name="user.id" style="width: 100px;" onchange="showDeptSub()">
									<s:if test="users==null">
										<option value="0">
											请先选择部门
										</option>
									</s:if>
									<s:else>
										<option value="<s:property value="users.id"/>">
											<s:property value="users.name" />
										</option>
									</s:else>
								</select>
							</td>
							<TD
								style="PADDING-LEFT: 50px; background: no-repeat;">
								<A  onclick="showDeptSub()" href="javascript:void(0);">请选择权限部门</A>
							</TD>
						</tr>
						
						<tr>
							<TD align="left" colspan="3">
								<ul id="treeDemo" class="ztree"></ul>
							</TD>
						</TR>
						<tr>
							<td align="center" colspan="3">
								<input type="submit" style="width: 100px; height: 40px;"
									value="指派(dispatch)" />
							</td>
						</tr>
									</table>
								</form>
								<hr>
									<input type="hidden" name="id" value="${id}">
									<table style="width: 980px; border-collapse: collapse;"
										border="0" align="center">
										<tr align="center" bgcolor="#c0dcf2"
											style="height: 40px; font-weight: bold;">
											<td>
												序号
											</td>
											<td>
												工号
											</td>
											<td>
												卡号
											</td>
											<td>
												姓名
											</td>
											<td>
												部门
											</td>
											<td>
												管理部门
											</td>
											<td>
												职位
											</td>
											<td>操作</td>
										</tr>

										<s:iterator id="pageusers" value="userDeptList"
											status="ststusfunction">
											<s:if test="#ststusfunction.index%2==1">
												<tr align="center" bgcolor="#e6f3fb"
													onmouseover="chageBgcolor(this)"
													onmouseout="outBgcolor(this,'#e6f3fb')">
											</s:if>
											<s:else>
												<tr align="center" onmouseover="chageBgcolor(this)"
													onmouseout="outBgcolor(this,'')">
											</s:else>
											<td>
												<s:if test="#ststusfunction.index%2==1">
													<font>
												</s:if>
												<s:else>
													<font color="#c0dcf2">
												</s:else>
												<s:property value="#ststusfunction.index+1" />
												</font>
											</td>
											<td>
												${pageusers.code}
											</td>
											<td>
												${pageusers.cardId}
											</td>
											<td>
												${pageusers.userName}
											</td>
											<td>
												${pageusers.deptName}
											</td>
											<td>
												${pageusers.deptName2}
											</td>
											<td>
												${pageusers.duty}
											</td>
											<td>
												<a href="javascript:;"
										onclick="if(window.confirm('您将解除绑定数据是否继续？')){window.location.href = 'ModuleFunctionAction!dispatchUsers.action?user.id=<s:property value="#pageusers.userId"/>&user.backStage=0'};"
											target="main">解除绑定</a>
											<a href="javascript:;"
										onclick="if(window.confirm('您将修改绑定数据是否继续？')){updatedispatch('<s:property value="#pageusers.userId"/>','<s:property value="#pageusers.userName"/>');}"
											target="main">修改绑定</a>
											</td>
											</tr>
											</s:iterator>
									</table>
							</div>
						</div>
						
						
					<div align="center">
						<%
							request.getSession().removeAttribute("successMessage");
							request.getSession().removeAttribute("errorMessage");
						%>
						<br />
						<br />
						<br />
					</div>
				</div>
		</center>
	<script type="text/javascript">
	function updatedispatch(id,name){
		//var username=document.getElementById("username");
		$("#username").empty();
		$("#username").append("<option value='"+id+"'>"+name+"</option>");
		showDeptSub()
	}
function validate(){
	var ids=document.getElementById("deptIds").value;
	var username=document.getElementById("username").value;
	if(username==0||username==""){
		alert("请绑定人员");
		return false;
	}
	if(ids==0||ids==""){
		alert("请选择绑定部门");
		return false;
	}
	
}
//========================================zTree显示
//自动组装树形结构
var setting = {
	check : {//checkBox选择框
		enable : true,
		autoCheckTrigger : true
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {//回调函数
		onClick : onClick,
		beforeCheck : beforeCheck,
		onCheck : onCheck
	}
};
//读取树形数据
$(document).ready(function() {
	showDept();
});

function showDeptSub() {
	$.ajax( {
		type : 'post',
		url : 'ModuleFunctionAction!getDeptVos.action',
		data : {
				id : $("#username").val()
							},
		dataType : 'json',
		cache : true,
		success : function(doc) {
			var zNodes = [];
			$(doc).each(
					function() {
						if($(this).attr('isHad')){
							var ids=document.getElementById("deptIds");
	                        var idsValue=ids.value;
	                        ids.value =ids.value+","+$(this).attr('id');
						}
						zNodes.push( {
							id : $(this).attr('id'),
							name : $(this).attr('dept'),
							pId : $(this).attr('fatherId'),
							belongLayer : $(this).attr('belongLayer'),
							checked : $(this).attr('isHad'),
							open : true,
							click : false
						});
					});
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			//父子关联关系(双向关联)
			$.fn.zTree.getZTreeObj("treeDemo").setting.check.chkboxType = {
			};
		},
		error : function() {
			alert("服务器异常!");
		}
	});
};

//点击回调函数
function onClick(event, treeId, treeNode, clickFlag) {
	
}

//选中回调函数
function onCheck(e, treeId, treeNode) {
}

//选中函数
function beforeCheck(treeId, treeNode) {
	var ids=document.getElementById("deptIds");
	var idsValue=ids.value;
	var index=idsValue.indexOf(","+treeNode.id);
		if(index>=0){
			ids.value=idsValue.replace(","+treeNode.id, "");
		}else{
			ids.value =ids.value+","+treeNode.id;	
		}
}

var showDept = function() {
	//查询所有的部门
	$.ajax( {
		url : 'DeptNumberAction!findAllDept.action',
		dataType : 'json',
		cache : false,//防止数据缓存
		success : function(allDdept) {
			$(allDdept).each(
					function() {
						$(
								"<option value='" + this.id + "'>" + this.dept
										+ "</option>").appendTo("#dept");
					});
		}

	});
}
</script>
	</body>
</html>
