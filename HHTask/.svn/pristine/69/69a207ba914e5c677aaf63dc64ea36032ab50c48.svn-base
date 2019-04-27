<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<head>
		<%@include file="/util/sonHead.jsp"%>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/zTree/js/jquery.ztree.excheck-3.5.js">
</script>
</head>
	<BODY>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<form id="sendform" 
				onsubmit="return validate();" style="width: 100%" method="post">
				<div align="center" style="width: 30%; float: left;border-right:1px solid #000">
					<table style="width: 100%">
						<TD style="PADDING-LEFT: 50px; background: no-repeat;">
							<input type="hidden" id="deptIds" name="deptIds" value="0"  />
							<A onclick="showDeptSub()" href="javascript:void(0);">选择部门</A>
						</TD>
						<tr>
							<TD align="left" colspan="3">
								<ul id="treeDemo" style="width:30%" class="ztree"></ul>
							</TD>
						</TR>
					</table>
				</div>
				<div align="center" style="width: 30%; float: left;border-right:1px solid #000">
					<table style="width: 60%">
						<tr>
							<th>
								请选择参与人
								<br/>
								<input type="button" value="添加选择人" onclick="addPlayers()" style="height:30px;"/>
							</th>
						</tr>
						<tr>
							<td>
								<input type="checkbox" id="all" onchange="chageAllCheck()">
								全选
							</td>
						</tr>
					</table>
					<table style="width: 100%" id="usertable"></table>
					
					
				</div>
				<div align="center" style="width:30%;float:left;">
					<table style="width: 60%">
						<tr>
							<th>
								已选择参与人<br/>
								<input type="button" onclick="cancelPlayer()" value="取消已选择人" style="height:30px;"/>
							</th>
						</tr>
						<table  id="alreadyBinder">
							<s:iterator value="screenUserList" id="list" >
								<tr id="tr_${list.usersId}">
									<td>
										<input type='checkbox' id='players' name='payersName' value='${list.usersId}' />
										${list.usersName}
									</td>
								</tr>
							</s:iterator>
					</table>
				</div>
			</form>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	
<script type="text/javascript">
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
});

var zNodes = [];
function showDeptSub() {
	$.ajax( {
		type : 'post',
		url : 'projectPoolAction_searchDept.action',
		data : {
			rootIdStr : 0
		},
		dataType : 'json',
		cache : true,
		success : function(doc) {
			$(doc).each(function() {
				zNodes.push( {
					//rootId : $(this).attr(1),
					id : $(this).attr('id'),
					pId : $(this).attr('fatherId'),
					name : $(this).attr('dept'),
					belongLayer : $(this).attr('belongLayer'),
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
};

//点击回调函数
function onClick(event, treeId, treeNode, clickFlag) {
	$("#usertable").empty();
}

//选中、取消选中回调函数
function onCheck(e, treeId, treeNode) {
	if(!treeNode.isParent){
		var checkedNodes =  $.fn.zTree.getZTreeObj("treeDemo").getCheckedNodes();
		var deptIds=[];
		var allDeptLength=0;
		for(var i=0;i<checkedNodes.length;i++){
			allDeptLength++;
//				if(!checkedNodes[i].isParent){
				deptIds.push(checkedNodes[i].id);
//				}
		}
// 		if(treeNode.checked){//选中
			if(checkedNodes.length==allDeptLength){
				getUsers(deptIds);
			}
// 		}else{
			
// 		}
	}
	
	
}

//选中函数
function beforeCheck(treeId, treeNode) {
}


function getUsers(deptIds) {
	$("#usertable").empty();
	for(var i=0;i<deptIds.length;i++){
		$.ajax( {
			type : "post",
			url : "projectPoolAction_getusers.action",
			data : {
				id : deptIds[i]
			},
			dataType : "json",
			cache:false, 
       		async:false, 
			success : function(allusers) {
				$(allusers).each(function(i, n) {
					if(n.selected==true){
						$("<tr ><td class='text-left'><input  type='checkbox' id='single' name='receiver' "
							+ "value='" + n.id+ "' onchange='chageNum()' checked='checked' /> "
							+ n.name + "</td> </tr>").appendTo("#usertable");
					}else{
						$("<tr ><td class='text-left'><input  type='checkbox' id='single' name='receiver' "
							+ "value='" + n.id+ "' onchange='chageNum()' /> "
							+ n.name + "</td> </tr>").appendTo("#usertable");
					}
				});
					
			}
		});
	}
	//alert(deptIds);
	
}

//全选
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
//选择单个
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

function addPlayers(){
	var flag = true;
	var detail = "";
	$("input[name='receiver']:checked").each(function(){ 
		var existsId = [];
		var userId = $(this).val();
		var userName = $(this).parent().text();
		
		$("input[name='payersName']").each(function(){ 
			existsId.push($(this).val());
		});
		
		for(var i=0;i<existsId.length;i++) {
		    if(existsId[i] ==userId) {
		        return;
		    }
		}
		if(flag){
			$.ajax({
				type:"post",
				url:"inDoorScreenAction!bindScreenUsers.action",
				dataType:'json',
				cache:false, 
	       		async:false, 
				data:{
					"usersId":userId,
					id:"${id}"
				},
				success:function(data){
					if("success"==data){
						$("#alreadyBinder").append("<tr id='tr_"+userId+"'><td  class='text-left'>"+
						"<input type='checkbox' id='players' name='payersName' value='"+userId+"' />"+userName+"</td></tr>");
						$(this).parent().parent().remove();
					}else{
						flag = false;
					}
				},error:function(){
					flag=false;
					detail = "不好意思，出错了，刷新重试一下吧";
				}
			});
		}
	}); 
	if(!flag){
		alert("绑定人员异常");
	}
}

function cancelPlayer(){
	var flag = true;
	var detail = "";
	$("input[name='payersName']:checked").each(function(){ 
		var usersId = $(this).val();
		if(flag){
			$.ajax({
				type:"post",
				url:"inDoorScreenAction!cancelScreenUsers.action",
				dataType:'json',
				cache:false, 
	       		async:false, 
				data:{
					"usersId":usersId,
					id:"${id}"
				},
				success:function(data){
					detail = data;
					if("success"==data){
						$("#tr_"+usersId).remove();
					}else{
						flag= false;
					}
				},
				error:function(){
					flag= false;
					detail = "不好意思，出错了，刷新重试一下吧";
				}
			});
		}
	}); 
	if(!flag){
		alert(detail);
	}
}


//全选已选择的
function chageAllExistsCheck(){
	var checkAll=document.getElementById("allExists");
	var checkboxs=document.getElementsByName("payersName");
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
</script>
	</BODY>
</HTML>
