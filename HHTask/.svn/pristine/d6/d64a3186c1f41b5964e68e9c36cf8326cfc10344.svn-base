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
								<input type="button" value="添加选择人" onclick="addProjectPlayers()" style="height:30px;"/>
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
					<table style="width: 60%" id="alreadyBinder">
						<tr>
							<th>
								已选择参与人<br/>
								<input type="button" onclick="cancelPlayer()" value="取消已选择人" style="height:30px;"/>
							</th>
						</tr>
						<s:iterator value="erList" id="list" >
							<tr id="tr_${list.id}">
								<td>
									<input type="hidden" name="userId" value="${list.addUserId}"/>
									<input type='checkbox' id='players' name='payersName' value='${list.id}' />
									${list.addUserName}
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
			//父子关联关系(双向关联)
		//$.fn.zTree.getZTreeObj("treeDemo").setting.check.chkboxType = {};
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
			if(!checkedNodes[i].isParent){
				deptIds.push(checkedNodes[i].id);
			}
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
	
// 	var ids = document.getElementById("deptIds");
// 	//alert(ids);
// 	unique(ids.value);
// 	var index = ids.value.indexOf("," + treeNode.id);
// 	if (index > 0) {
// 		ids.value = ids.value.replace("," + treeNode.id, "");
// 	} else {
// 		ids.value = ids.value + "," + treeNode.id;
// 	}
// 	getUsers(ids.value);
}

// function unique(arr){
// 	// 遍历arr，把元素分别放入tmp数组(不存在才放)
// 	var tmp = new Array();
// 	for(var i in arr){
// 		//该元素在tmp内部不存在才允许追加
// 		if(tmp.indexOf(arr[i])==-1){
// 			tmp.push(arr[i]);
// 		}
// 	}
// 	return tmp;
// }

function getUsers(deptIds) {
	$("#usertable").empty();
	var ids = "";//已经选择的不显示
	$("input[name='userId']").each(function(){ 
		ids+= $(this).val()+",";
	});
	ids = ids.substring(0,ids.length-1);
// 	var id = deptIds.split(",");
	for(var i=0;i<deptIds.length;i++){
		$.ajax( {
			type : "post",
			url : "projectPoolAction_getusers.action",
			data : {
				id : deptIds[i],
				ids:ids
			},
			dataType : "json",
			cache:false, 
       		async:false, 
			success : function(allusers) {
				$(allusers).each(function(i, n) {
					if(n.selected==true){
						$("<tr ><td style='padding:0 20%'><input  type='checkbox' id='single' name='receiver' "
							+ "value='" + n.id+ "' onchange='chageNum()' checked='checked' /> "
							+ n.name + "</td> </tr>").appendTo("#usertable");
					}else{
						$("<tr ><td style='padding:0 20%'><input  type='checkbox' id='single' name='receiver' "
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

function addProjectPlayers(){
	var flag = true;
	var detail = "";
	$("input[name='receiver']:checked").each(function(){ 
		var userId = $(this).val();
		var userName = $(this).parent().text();
		if(flag){
			$.ajax({
				type:"post",
				url:"projectPoolAction_bindPlayers.action",
				dataType:'json',
				cache:false, 
	       		async:false, 
				data:{
					userId:userId,
					id:"${id}"
				},
				success:function(data){
					detail = data[0];
					if("success"==data[0]){
						$("#alreadyBinder").append("<tr style='padding:0 20%'><td>"+
						"<input type='hidden' name='userId' value='"+userId+"'/>"+
						"<input type='checkbox' id='players'"+
								"name='payersName' value='"+data[1]+"' />"+userName+"</td></tr>");
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
		alert(detail);
	}
}

function cancelPlayer(){
	var flag = true;
	var detail = "";
	$("input[name='payersName']:checked").each(function(){ 
		var erId = $(this).val();
		if(flag){
			$.ajax({
				type:"post",
				url:"projectPoolAction_cancelPlayers.action",
				dataType:'json',
				cache:false, 
	       		async:false, 
				data:{
					id2:erId,
					id:"${id}"
				},
				success:function(data){
					detail = data;
					if("success"==data){
						$("#tr_"+erId).remove();
						//$(this).parent().parent().remove();
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

</script>
	</BODY>
</HTML>