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
			<div align="left" style="width: 40%;float: left;" >
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			<div style="width: 20% ;float: left;" align="left">
				<ul id="usersUl">
				</ul>
			</div>
			<div style="width: 20%;float: left;" align="center">
			<h3>选中人员</h3>（设变必须通知部门为：${remark}）
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
	var idname1 = "${idname1}";
	var idname3 = "${idname3}";
	var ids=parent.$("#"+idname1).val();
	var dapts=parent.$("#"+idname3).html()
	$("#selectUserId").val(ids);
	$("#selectUsers").html(dapts);
	showDeptSub();
});

function showDeptSub() {
	$.ajax( {
		type : 'post',
		url : 'procardTemplateGyAction_getDeptTreeVos.action',
		data : {
				ids : "${ids}"
		},
		dataType : 'json',
		cache : true,
		success : function(doc) {
			var zNodes = [];
			$(doc).each(
					function() {
						zNodes.push( {
							id : $(this).attr('id'),
							name : $(this).attr('dept'),
							pId : $(this).attr('fatherId'),
							belongLayer : $(this).attr('belongLayer'),
							//checked : $(this).attr('isHad'),
							nocheck :true,
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
	$.ajax( {
		type : "POST",
		url : 'GzstoreAction_getusers.action',
		data : {
					id : treeNode.id,
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
						$("<li><input id='checkboxs"+n+"' data='"+this.dept+ "-("+ this.code+")" + this.name +"' type='checkbox' name='checkboxs' "+checed+" value='"+$(this).attr("id")+"' onchange='selectUser("+n+")'>"+
					 + this.code + "-"+ this.name + "</li>").appendTo("#usersUl");
						n++;
					});
		}

	});
}

//选中回调函数
function onCheck(e, treeId, treeNode) {
	
}

//选中函数
function beforeCheck(treeId, treeNode) {
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
						$("<option value='" + this.id + "'>" + this.dept
										+ "</option>").appendTo("#dept");
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
