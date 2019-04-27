<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE >
<HTML>
	<head>
		
	<%@include file="/util/sonHead.jsp"%>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/javascript/zTree/js/jquery.ztree.excheck-3.5.js"></script>
    	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js"></script>
	<script type="text/javascript"
	 	src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
	<script type="text/javascript">
// 现在window.$和window.jQuery是3.2.1版本:
console.log($().jquery); // => '3.2.1'
var $jq = jQuery.noConflict(true);
// 现在window.$和window.jQuery被恢复成1.8.3版本:
console.log($().jquery); // => 'jquery-1.8.3.js'

</script> 	
</head>
<BODY>
<div class="container">
	<div class="row">
		<div class="col-xs-4">
			<form  id="submitForm">
				<input type="hidden" value="${id }" name="id">
				<input type="hidden" id="ids" name="ids">
				<ul class="list-group">
					<li class="list-group-item">
						<input type="checkbox" id="checkAll" onchange="chageAllCheck(this)">全选
					</li>
					<s:iterator value="erList" id="ers" >
						<li  class="list-group-item">
							<input type="checkbox" name="erList.id" value="${ers.id}">${ers.addUserName}
						</li>
					</s:iterator>
				</ul>
			</form>
			</div>
			<div class="col-xs-8">
		    	<ul id="treeDemo" class="ztree"></ul>
		    	<input type="button" value="绑定" class="input" onclick="xqBind()">
			</div>
		
	</div>
</div>
	
<script type="text/javascript">

function xqBind(){
	var users = $("input[name='erList.id']");
	var checkedNodes =  $.fn.zTree.getZTreeObj("treeDemo").getCheckedNodes();
	if(users.length>1){
		if(!confirm("选择了多个人员，确定绑定相同权限吗？")){
			return;
		}	
	}
	if(checkedNodes==null || checkedNodes.length==0){
		if(!confirm("确定取消全部权限吗？")){
			return;
		}
	}
	
	if(users.length>0 && confirm("确定绑定权限吗？")){
		var erIds = "";
		for(var i = 0;i<users.length;i++){
			var erId = users[i].value;
			if(erIds==""){
				erIds=""+erId;
			}else{
				erIds+=","+erId;
			}
		}
		$jq("#ids").val(erIds);
		var form = new FormData(document.getElementById("submitForm"));
		$.ajax( {
			url:"projectPoolAction_xqBind.action",
			type : 'post',
			data:form,
			dataType:"json",
            processData:false,
            contentType:false,
            async : false, 
			success:function(obj){
				alert(obj);
			},
			error:function(XMLHttpRequest, textStatus, errorThrown) {
                alert("系统异常\n状态："+XMLHttpRequest.status+",状态值："+XMLHttpRequest.readyState+",异常信息:"+textStatus);
			}
		});
		
	}
}

var setting = {
	lines:true,
	animate:true,
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
	},
	view:{
		fontCss:getFont,
		nameIsHTML:true
	}
};
//加载树形数据
$(document).ready(loadTree());
function getFont(treeId,node){
	return node.font?node.font:{};
}
//生成
function loadTree() {
	$.ajax( {
		url : 'projectPoolAction_findProjectManageyfByRootId.action',
		type : 'post',
		dataType : 'json',
		data : {
			rootIdStr : '${id}'
		},
		cache : true,
		success : function(doc) {
			var zNodes = [];
			$(doc).each(function(){
				zNodes.push({
					id : $(this).attr('id'),
					pId : $(this).attr('fatherId'),
					rootId : $(this).attr('rootId'),
					belongLayer : $(this).attr('belongLayer'),
					name : $(this).attr('proName'),
					open : true,
					click : false
				});
			});
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		},
		error : function() {
			alert("服务器异常!");
		}
	});

}

//点击回调函数
function onClick(event, treeId, treeNode, clickFlag) {
}

// //选中、取消选中回调函数
function onCheck(e, treeId, treeNode) {
}

//选中函数
function beforeCheck(treeId, treeNode) {
}

//全选
function chageAllCheck(checkAll){
	var checkboxs=document.getElementsByName("erList.id");
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
//选择单个
function chageNum(){
	var checkAll=document.getElementById("checkAll");
	var checkboxs=document.getElementsByName("erList.id");
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


</script>
	</BODY>
</HTML>