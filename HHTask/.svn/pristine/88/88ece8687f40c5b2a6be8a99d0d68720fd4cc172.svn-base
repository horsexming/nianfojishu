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
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="left">
				<!-- 显示树形流水卡片模板 -->
				<div style="width: 40%; height: 100%; float: left;" align="left">
					<div style="height: 100%;">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
				<!-- 添加流水卡片模板操作 -->
				<div style="border-left: 1px solid #000000; float: left; width: 58%">
					<div id="showCardTemplate" style="display: none; height: 100%;">
						<iframe id="showCardIframe" src="" marginwidth="0"
							marginheight="0" hspace="0" vspace="0" frameborder="0"
							style="width: 100%; height: 100%; margin: 0px; padding: 0px;"
							scrolling="yes"></iframe>
					</div>
				</div>
				<div style="clear: both;"></div>
			</div>
			<%@include file="/util/foot.jsp"%>
			</center>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
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
//页面初始加载树形数据
$(document).ready(loadTree());
//生成
function loadTree() {
	$.ajax( {
		url : 'ProcardAction!findProcardByRootId.action',
		type : 'post',
		dataType : 'json',
		data : {
			id : '${param.id}'
		},
		cache : true,
		success : function(doc) {
			var zNodes = [];
			$(doc).each(
					function() {
						zNodes.push( {
							id : $(this).attr('id'),
							pId : $(this).attr('fatherId'),
							proStruts : $(this).attr('procardStruts'),
							rootId : $(this).attr('rootId'),
							belongLayer : $(this).attr('belongLayer'),
							cardId : $(this).attr('cardId'),
							cardNum : $(this).attr('cardNum'),
							name : $(this).attr('proName') + ' '
									+ $(this).attr('markId') + ' '
									+ $(this).attr('procardStyle'),
							click : false,
							open : true
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
	//显示流水卡片明细
	$("#showCardTemplate").show();
	$("#showCardIframe").attr("src",
			"ProcardAction!findCardForShow.action?id=" + treeNode.id);
}
</script>
	</body>
</html>
