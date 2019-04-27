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
		<style type="text/css">
#gongneng {
	width: auto;
	overflow: auto;
}
</style>
	</head>
	<BODY>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div style="float: left;">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			<div style="clear: both; float: left; position: absolute; left: 55%;"
				align="center">
				<input type="hidden" value="${successMessage}" id="hid" />
				<font color="red"> ${successMessage}</font>
			</div>
			<div id="message"
				style="position: absolute; float: left; left: 55%; border: solid 0px #000000; display: none;">
				<span id="deptName"></span>
				<form action="" method="post">
					<input type="hidden" id="deptId" name="id" value="${category.id}">
					<input type="hidden" id="type" name="category.type"
						value="${category.type}">
					<table>
						<tr>
							<th align="right">
								名 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称:
							</th>
							<td>
								<input id="dept" type="text" name="category.name"
									value="${category.name}" onfocus="javascript:this.select()"
									class="search_shuru1" />
							</td>
						</tr>
						<tr>
							<td colspan="4" align="right">
								<input type="button" value="修改内容"
									onclick="chageDeptNumber('update',this.form)"
									style="width: 80px; height: 50px;" />
								<input type="button" value="添加下层"
									onclick="chageDeptNumber('add',this.form)"
									style="width: 80px; height: 50px;" />
								<input type="button" value="删除"
									onclick="chageDeptNumber('del',this.form)"
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>

		</div>
		<%@include file="/util/foot.jsp"%>

		<script type="text/javascript">

//========================================zTree显示
//自动组装树形结构
var setting = {
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		onClick : onClick
	}
};
//读取树形数据
$(document).ready(function() {
	parent.mfzTree();
});
parent.mfzTree = function() {
	$.ajax( {
		url : 'CategoryAction_findcyListByrootId.action',
		type : 'post',
		data : {
			status : parent.status,
			tag :parent.tag
		},
		dataType : 'json',
		cache : true,
		success : function(doc) {
			var zNodes = [];
			$(doc).each(
					function() {
						zNodes.push( {
							id : $(this).attr('id'),
							pId : $(this).attr('fatherId'),
							name : $(this).attr('name'),
							url : "CategoryAction_findcategoryById.action?id="
									+ $(this).attr('id'),
							target : "main",
							click : false
						});

					});
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.expandAll(true);
		},
		error : function() {
			alert("服务器异常!");
		}
	});
};
<%--function zTreeOnAsyncSuccess(event, treeId, treeNode, msg){--%>
<%--			--%>
<%--        var nodes = treeNode.children;--%>
<%--        for(var i=0;i<nodes.length;i++){--%>
<%--            treeObj.expandNode(nodes[i],true,false,true,true);--%>
<%--        }--%>
<%-- --%>
<%--    }--%>

//点击回调函数
function onClick(event, treeId, treeNode, clickFlag) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = zTree.getSelectedNodes();
	
	//折叠、展开操作
	for ( var i = 0, l = nodes.length; i < l; i++) {
		zTree.expandNode(nodes[i], null, null, null, true);
	}

	//treeNode.open = true;

	//var treeNode = zTree.getNodeByParam("id", 5);//查询父类节点
	//treeNode.open = true;
	//treeNode.name = "testedit";

}
//=================================== zTree显示结束

function chageDeptNumber(status, form) {
	if (status == "update") {
		form.action = "DeptNumberAction!updateDeptNumber.action";
	} else if (status == "add") {
		form.action = "DeptNumberAction!addDeptNumber.action";
	} else if (status == "del") {
		form.action = "DeptNumberAction!delDeptNumber.action";
	}
	form.submit();
}

$(function(){
	var deptId=$("#Id").val();
	if(deptId!=null&&deptId>0){
		$("#message").show();
		
	}
	var msg=$("#hid").val();
	
});

</script>
	</BODY>
</HTML>