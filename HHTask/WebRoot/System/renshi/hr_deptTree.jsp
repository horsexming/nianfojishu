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
			src="<%=basePath%>/javascript/zTree/js/jquery.ztree.excheck-3.5.js">
</script>
<script type="text/javascript"
			src="<%=basePath%>/javascript/zTree/js/jquery.ztree.exedit-3.5.js">
		</script>
<style type="text/css">
	#gongneng {width:auto;  overflow: auto;}
</style>
	</head>
	<BODY>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div style="float: left;">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			<div style="clear: both; float: left; position: absolute; left:55%; " align="center">
				<input type="hidden" value="${successMessage}"  id="hid"/>
				<font color="red"> ${successMessage}</font>
			</div>
			<div id="message" style="position: absolute; float:left; left:55%; border: solid 0px #000000; display: none; ">
				<span id="deptName"></span>
				<form action="" method="post">
					<input type="hidden" id="deptId" name="id" value="${deptNumber.id}">
					<table>
						<tr>
							<th align="right">
								名 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称:
							</th>
							<td>
								<input id="dept" type="text" name="deptNumber.dept" value="${deptNumber.dept}"
									onfocus="javascript:this.select()" class="search_shuru1" />
							</td>
						</tr>
						<tr>
							<th align="right">
								部门编号:
							</th>
							<td>
								<input id="deptNumber" type="text" name="deptNumber.deptNumber" value="${deptNumber.deptNumber}"
									class="search_shuru1" title="小部门时填写部门编号!">
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
	edit:{
		enable: true,
		drag:{
			prev: true,
            inner: true,
             next: true,
             isMove: true,
             isCopy: false
		},
		 showRemoveBtn: false,
          showRenameBtn: false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		onClick : onClick,
		beforeDrag: zTreeBeforeDrag,
		beforeDrop: zTreeBeforeDrop,
		onDrop: zTreeOnDrop,
		onDrag: zTreeOnDrag
	}
};
//读取树形数据
$(document).ready(function() {
	parent.mfzTree();
});

parent.mfzTree = function() {
	$.ajax( {
		url : 'DeptNumberAction!findAlldept.action',
		type : 'post',
		dataType : 'json',
		cache : true,
		success : function(doc) {
			var zNodes = [];
			$(doc).each(
					function() {
						zNodes.push( {
							id : $(this).attr('id'),
							pId : $(this).attr('fatherId'),
							name : $(this).attr('dept'),
							xuhao:$(this).attr('xuhao'),
							url :"DeptNumberAction!getdeptbyId.action?id="+$(this).attr('id'),
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

//拖拽前函数;
function zTreeBeforeDrag(treeId, treeNodes) {
	var bool = true;
	$.ajax( {
		type : "POST",
		url : "DeptNumberAction!istuozhuai.action",
		async:false,
		data : {
			id:treeNodes[0].id
		},
		dataType : "json",
		success : function(data) {
			bool = data;
		}
	})
	
    return bool;
};
//  moveType :prev 、next、inner ; (targetNode == null || (moveType != "inner" && !targetNode.parentTId))
function zTreeBeforeDrop(treeId, treeNodes, targetNode, moveType) {
	var bool = true;
	$.ajax( {
		type : "POST",
		url : "DeptNumberAction!istuozhuai.action",
		async:false,
		data : {
			id:treeNodes[0].id,
			id1:targetNode.id,
			moveType :moveType
		},
		dataType : "json",
		success : function(data) {
			bool = data;
		}
	})
    return bool;
};

function zTreeOnDrag(event, treeId, treeNodes) {
<%--    alert(treeNodes.length);--%>
};
//拖拽后函数;用于后台
function zTreeOnDrop(event, treeId, treeNodes, targetNode, moveType) {
<%--	alert(treeNodes[0].xuhao+"  "+targetNode.xuhao)--%>
		$.ajax( {
		type : "POST",
		url : "DeptNumberAction!tuozhuaiAfterUpdate.action",
		async:false,
		data : {
			id:treeNodes[0].id,
			id1:targetNode.id,
			moveType :moveType,
			xuhao1:treeNodes[0].xuhao,
			xuhao2:targetNode.xuhao
		},
		dataType : "json",
		success : function(data) {
			if(!"true" == data){
				alert("拖拽失败，请重试!");
				window.location.reload(true);
			}
		}
	})

};

//点击回调函数
function onClick(event, treeId, treeNode, clickFlag) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = zTree.getSelectedNodes();
	//折叠、展开操作
	for ( var i = 0, l = nodes.length; i < l; i++) {
		zTree.expandNode(nodes[i], null, null, null, true);
	}
	
	var main =	window.parent.document.getElementById("main");
	main.src="DeptNumberAction!getdeptbyId.action?id="+treeNode.id;
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
	var deptId=$("#deptId").val();
	if(deptId!=null&&deptId>0){
		$("#message").show();
		
	}
	var msg=$("#hid").val();
	
});

</script>
	</BODY>
</HTML>