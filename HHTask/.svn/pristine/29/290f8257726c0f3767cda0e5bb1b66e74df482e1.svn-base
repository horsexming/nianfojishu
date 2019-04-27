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
		<%@include file="/util/sonHead.jsp"%>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/zTree/js/jquery.ztree.excheck-3.5.js">
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div align="center" style="width: 54%; float: left;">
					<table style="width: 100%">
						<td style="PADDING-LEFT: 50px; background: no-repeat;">
							<input type="hidden" id="deptIds" name="deptIds" value="0" />
							<A onclick="showDeptSub()" href="javascript:void(0);">选择部门</A>
						</td>
						<tr>
							<TD align="left" colspan="3">
								<ul id="treeDemo" class="ztree"></ul>
							</TD>
						</TR>
					</table>
				</div>
				<div align="center" style="width: 44%; float: left;">
					<table style="width: 100%">
						<tr>
							<td>
								请选择人人员
							</td>
						</tr>
						<tr>
							<td>
								<input type="checkbox" id="all" onchange="chageAllCheck()">
								全选
							</td>
						</tr>
					</table>
					<table style="width: 100%" id="usertable">
					</table>
				</div>
			</div>
			<div align="center">
				<input type="button" value="确定" style="width: 80px;height: 60px;" onclick="surePeople()">
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
function chageAllCheck(){
			var checkAll=document.getElementById("all");
			var checkboxs=document.getElementsByName("receiver");
			if(checkAll.checked==true){
				for(var i=0;i<checkboxs.length;i++){
					checkboxs[i].checked=true;
				}
			}else{
				for(var i=0;i<checkboxs.length;i++){
					checkboxs[i].checked=false;
				}
			}
			
}
function chageNum(){
			var checkAll=document.getElementById("all");
			var checkboxs=document.getElementsByName("receiver");
			var count=0;
			for(var i=0;i<checkboxs.length;i++){
				if(checkboxs[i].checked==false){
					checkAll.checked=false;
					return;
				}else{
					count++;
				}
			}
			if(count==checkboxs.length){
				checkAll.checked=true;
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
	showDeptSub();
	showDept();
});

function showDeptSub() {
	$.ajax( {
		type : 'post',
		url : 'DeptNumberAction!findAlldept.action',
		data : {
			id : 0
		},
		dataType : 'json',
		cache : true,
		success : function(doc) {
			var zNodes = [];
			$(doc).each(function() {
				zNodes.push( {
					id : $(this).attr('id'),
					name : $(this).attr('dept'),
					pId : $(this).attr('fatherId'),
					checked : false,
					open : true,
					click : false
				});
			});
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			//父子关联关系(双向关联)
		$.fn.zTree.getZTreeObj("treeDemo").setting.check.chkboxType = {};
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
	var ids = document.getElementById("deptIds");
	var index = ids.value.indexOf("," + treeNode.id);
	if (index > 0) {
		ids.value = ids.value.replace("," + treeNode.id, "");
	} else {
		ids.value = ids.value + "," + treeNode.id;
	}
	getUsers(ids.value);
}

function getUsers(deptIds) {
	if (deptIds == 0) {
		$("#usertable").empty();
	}
	{
		$.ajax( {
			type : 'post',
			url : 'goodsAction!getUsersByIds.action',
			dataType : 'json',
			data : {
				tag : deptIds
			},
			cache : false,//防止数据缓存
			success : function(allusers) {
				$("#usertable").empty();
				$(allusers).each(
						function(i, n) {
							$(
									"<tr><td><input type='checkbox' id='single' name='receiver' data1='"+ n.code+"' data2='"+ n.name+"' " 
											+ "value='" + n.id
											+ "' onchange='chageNum()' /> "
											+ n.name + "</td> </tr>").appendTo(
									"#usertable");
						});
			}

		});
	}

}

function surePeople(){
	var checkboxs=document.getElementsByName("receiver");
	var ids="";
	var codes="";
	var names="";
	for(var i=0;i<checkboxs.length;i++){
			if(checkboxs[i].checked==true){
				if(ids==""){
					ids+=checkboxs[i].value;
					codes+=checkboxs[i].getAttribute("data1");
					names+=checkboxs[i].getAttribute("data2");
				}else{
					ids+=";"+ checkboxs[i].value;
					codes+=";"+ checkboxs[i].getAttribute("data1");
					names+=";"+ checkboxs[i].getAttribute("data2");
				}
			}
		}
	parent.$("#zpry").val(ids);
	parent.$("#zprycode").val(codes);
	parent.$("#zpryname").val(names);
	parent.$("#showry").html(names);
	parent.chageDiv('none');
}
</script>
	</body>
</html>
