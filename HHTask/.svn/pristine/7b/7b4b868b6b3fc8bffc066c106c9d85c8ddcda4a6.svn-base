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
			<div style="width: 100%" align="center">
				<font color="red"><h3>
						${successMessage}
					</h3> </font>
				<font color="red"><h3>
						${errorMessage}
					</h3> </font>
			</div>
			<form id="sendform" 
				onsubmit="return validate();" style="width: 100%" method="post">
				<div align="center" style="width: 20%; float: left;">
					<table style="width: 100%">
						<TD style="PADDING-LEFT: 50px; background: no-repeat;">
							<input type="hidden" id="deptIds" name="deptIds" value="0" />
							<A onclick="showDeptSub()" href="javascript:void(0);">选择部门</A>
						</TD>
						<tr>
							<TD align="left" colspan="3">
								<ul id="treeDemo" class="ztree"></ul>
							</TD>
						</TR>
					</table>
				</div>
				<div align="center" style="width: 15%; float: left;">
					<table style="width: 100%">
						<tr>
							<td>
								请选择接收人
							</td>
						</tr>
						<tr>
							<td>
								<input type="checkbox" id="all" onchange="chageAllCheck()">
								全选
							</td>
						</tr>
					</table>
					<%--						<s:iterator value="usersList" id="pageUsers">--%>
					<%--							<tr>--%>
					<%--								<td>--%>
					<%--									<input type="checkbox" id="single" name="receiver"--%>
					<%--										value="<s:property value="#pageUsers.code"/>"--%>
					<%--										onchange="chageNum()" />--%>
					<%--									<s:property value="#pageUsers.name" />--%>
					<%--								</td>--%>
					<%--							</tr>--%>
					<%--						</s:iterator>--%>
					<table style="width: 100%" id="usertable">
					</table>
				</div>
				<div align="left" style="width: 60%; float: left;">
					<p style="margin-left: 20px" align="left">
					</p>
					<table style="width: 100%">
<%--						<tr align="right">--%>
<%--							<th style="width: 20%" align="right">--%>
<%--								标题:--%>
<%--							</th>--%>
<%--							<th align="left">--%>
<%--								<input type="text" id="title" name="title" style="width: 303">--%>
<%--							</th>--%>
<%--						</tr>--%>
						<tr align="right">
							<th style="width: 20%" align="right">
								内容:
							</th>
							<th align="left">
								<textarea id="msgContext" name="msgContext" rows="10" cols="40"></textarea>
							</th>
						</tr>
						<tr align="center">
							<th align="center" colspan="2">
								<input type="button" value="发送" class="input"
									onclick="sendrtxmsg()">
							</th>
						</tr>
					</table>
				</div>

			</form>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		function validate(){
			var checkboxs=document.getElementsByName("receiver");
			var msgContext=document.getElementById("msgContext");
			if(msgContext==null||msgContext==""){
				alert("请输入发送内容");
			}
			var count=0;
			for(var i=0;i<checkboxs.length;i++){
				if(checkboxs[i].checked==true){
					count++;
				}
			}
			if(count==0){
				alert("请选择接收人");
				return false;
			}
			
		}
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
		function sendrtxmsg(){
			var sendform=$("#sendform").serialize();
			$.ajax({
				type : 'post',
		        url : 'rtxMsgAction_sendRtxMsg.action',
		        data : sendform,
		        dataType : 'json',
		        cache : true,
		        success : function(msg) {
			       alert(msg.message);
		       }
				
			});
		}
		</SCRIPT>
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

function showDeptSub() {
	$.ajax( {
		type : 'post',
		url : 'rtxMsgAction_findAllRtxDept.action',
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
					name : $(this).attr('deptName'),
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
			url : 'rtxMsgAction_getRtxUsers.action',
			dataType : 'json',
			data : {
				deptIds : deptIds
			},
			cache : false,//防止数据缓存
			success : function(allusers) {
				$("#usertable").empty();
				$(allusers).each(
						function(i, n) {
							$(
									"<tr><td><input type='checkbox' id='single' name='receiver' "
											+ "value='" + n.userName
											+ "' onchange='chageNum()' /> "
											+ n.name + "</td> </tr>").appendTo(
									"#usertable");
						});
			}

		});
	}

}
</script>
	</BODY>
</HTML>