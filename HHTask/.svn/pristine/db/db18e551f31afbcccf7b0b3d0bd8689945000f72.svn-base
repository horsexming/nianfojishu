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
		
			<div align="center">
				<table style="width: 50%;">
					<tr>
						<th align="right">
							部门:
						</th>
						<td>
							<select id="deptname" style="width: 100px;"
								onchange="userlist(0)">
								<s:if test="user!=null">
									<option value="${deptId}_${user.dept}">
										${user.dept}
									</option>
								</s:if>
								<s:else>
									<option value="0">
										请选择部门
									</option>
								</s:else>
							</select>
						</td>
						<th align="right">
							名字:
						</th>
						<td id="userstd">
							<select id="username" style="width: 100px;"
								onchange="changvalue(this)">
								<s:if test="user!=null">
									<option value="${user.name}">
										${user.name}
									</option>
								</s:if>
								<s:else>
									<option value="0">
										请先选择部门
									</option>
								</s:else>
							</select>
							
						</td>
					</tr>
				</table>
				</div>
				<center><h3>请选择授权物料管理类别</h3></center>
				<div align="center">
				<input type="hidden" value="${user.id}" id="userId" name="id" />
					<div style="border-left: 1px solid #000000; float: center; width: 58%">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
					<div style="clear: both;"></div>	
				</div>
				
			<%@include file="/util/foot.jsp"%>
		</div>
	
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
var userId;
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


function showDeptSub() {
	$.ajax( {
		type : 'post',
		url : 'CategoryAction_findcylist1.action',
		data : {
			type : '物料',
			id : userId
		},
		dataType : 'json',
		cache : false,
		success : function(doc) {
			var zNodes = [];
			$(doc).each(
					function() {
						zNodes.push( {
							id : $(this).attr('id'),
							name : $(this).attr('name'),
							hiddenName : $(this).attr('name'),
<%--							subjectRate : $(this).attr('category'),--%>
							pId : $(this).attr('fatherId'),
							rootId : $(this).attr('rootId'),
							checked : $(this).attr('checked'),
							open : true,
							click : false
						});
					});
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			//父子关联关系(双向关联)
<%--			$.fn.zTree.getZTreeObj("treeDemo").setting.check.chkboxType = {"Y" : "ps", "N" : "ps" };--%>
			$.fn.zTree.getZTreeObj("treeDemo").setting.check.chkboxType = {
				"Y" : "p",
				"N" : "p"
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
		
	var cateId = treeNode.id;
	$
			.ajax( {
				type : 'post',
				url : 'CategoryAction_updateUserCate.action',
				data : {
					id : userId,
					cateId:cateId
				},
				dataType : 'json',
				cache : true,
				success : function(bool) {
					if (bool == false) {
						alert("勾选失败!请检查数据有效性!");
					}
				},
				error : function() {
					alert("服务器异常!");
				}
			});
}
//查询所有部门
$(function() {
	//加载所有分组
	$.ajax( {
		type : "post",
		url : "DeptNumberAction!findAllDept.action",
		dataType : "json",
		success : function(data) {
			//填充部门信息
			$(data).each(
					function() {
						$(
								"<option value='" + this.id+"_"+this.dept + "'>" + this.dept
										+ "</option>").appendTo("#deptname");
					});
			$("#deptname").tinyselect();
		}
	});
});
//选择人员
function userlist(flag) {//flag0表示是点击部门的时候flag1表示页面刷新的时候
	var obj = $("#deptname").val();
		obj	=	obj.split("_");
		var deptid = 0;
		var deptname = "";
		if(obj!=null && obj.length == 2){
			deptid = obj[0];
			$("#dept").val(obj[1]);
		}
	if (deptid == "0") {
		$("#username").empty();
			if("${user.name}!=null && ${user.name}!=''"){
			$("<option value='${user.name}'>${user.name}</option>").appendTo("#username");
		}else{
			$("<option value='0'>请先选择部门</option>").appendTo("#username");
		}
		$("#username").tinyselect();
	} else {
		$
				.ajax( {
					type : "post",
					url : "GzstoreAction_getusers.action",
					data : {
						id : deptid
					},
					dataType : "json",
					success : function(data) {
						if (flag == 0) {
							$("#username").empty();
							$("<option value='0'>请选择人员</option>").appendTo(
									"#username");

						}
						$(data).each(
								function() {
									
											$(
											"<option value='" + this.id +"_"+this.name+ "'>"
													+ this.name + "</option>")
											.appendTo("#username");
								});
						var tinyselect = $(".tinyselect");
						if (tinyselect[1] != null) {
							document.getElementById("userstd").removeChild(
									tinyselect[1]);
						}
						$("#username").tinyselect();
					}
				});
		
	}

}
$(document).ready(function() {
	userlist(1);
});

function changvalue(obj){
if(obj!=null && obj.value!="" && obj.value!="0"){
	var v = obj.value;
	var array = v.split("_");
	var id = array[0];
	userId =  id;
	if(array.length == 2){
		$("#userId").val(array[0]);
		}
	showDeptSub();
	}
}

</SCRIPT>
	</body>
</html>
