<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
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
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/css/mobiscroll.core-2.5.2.css">
		<script src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js"> </script> 
 		<script type="text/javascript"
 			src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js"> </script> 
<!--  		<script type="text/javascript" -->
<%--  			src="${pageContext.request.contextPath}/js/mobiscroll.core-2.5.2.js"> </script>  --%>
		<script type="text/javascript">
			// 现在window.$和window.jQuery是3.2.1版本:
			console.log($().jquery); // => '3.2.1'
			var $jq = jQuery.noConflict(true);
			// 现在window.$和window.jQuery被恢复成1.5版本:
			console.log($().jquery); // => '1.5.0'
		
		</script>
	</head>
	<body>
		<div style="float: left;width: 30%;height: 100%;overflow: scroll;">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
		<div style="float: left;width: 60%;height: 100%" >
		<jsp:include page=""></jsp:include>
			<iframe id="main" style="width: 100%;height: 100%" 
 				 marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"> </iframe>
<!-- 						sandbox="allow-scripts"好好纪念本属性 -->
		</div>
	</body>
	<script type="text/javascript">
	var mfzTree;
	var addzTree;
	var delzTree;
	var updatezTree;

	var id;
	var pId;
	var name;
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
			onClick : onClick
		}
	};
	//读取树形数据
	$(document).ready(function() {
		parent.mfzTree();
	});

	parent.mfzTree = function() {
		$.ajax( {
			url : 'productEBAction!getBanZuJieGou.action',
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
								name : $(this).attr('name'),
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

	//点击回调函数
	function onClick(event, treeId, treeNode, clickFlag) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = zTree.getSelectedNodes();
		//折叠、展开操作
		for ( var i = 0, l = nodes.length; i < l; i++) {
			zTree.expandNode(nodes[i], null, null, null, true);
		}
		
		var main =	$("#main").attr("src","${pageContext.request.contextPath}/productEBAction!getBanZuJieGetById.action?id="+treeNode.id);
	}
	//=================================== zTree显示结束

	</script>
</html>
