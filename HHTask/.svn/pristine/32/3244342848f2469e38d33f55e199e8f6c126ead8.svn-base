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
						href="<%=basePath%>System/caiwu/budget/subMonthMoney_manage.jsp?rootId=${pageSmm.rootId}&pageStatus=${pageStatus}">填报</a>
				</div>
			</div>
			<div align="center">
				<div id="showMessage" align="center" style="color: red;">
				</div>
				<div id="title" align="center"
					style="border: 1px #000000 solid; font-size: 16px; font-weight: bolder;">
				</div>
				<div align="left">
					<!-- 显示科目树形 -->
					<div
						style="width: 100%; height: 100%; border-right: 1px solid #000000;"
						align="left">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//========================================zTree显示
var id, pId, name, rootId, belongLayer, subId, month;

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
	mfzTree();
});

var mfzTree = function() {
	$.ajax( {
		url : 'SubMonthMoneyAction!findSummByRootId.action',
		data : {
			id : "${param.rootId}",
			pageStatus : "${param.pageStatus}"
		},
		type : 'post',
		dataType : 'json',
		cache : true,
		success : function(data) {
			var zNodes = [];
			var doc = data.smmList;
			var smm = data.smMoney;
			//标题
			$("#title").html(smm.name + "总额查看");
			//填充属性结构
			$(doc).each(
					function() {
						zNodes
								.push( {
									id : $(this).attr('id'),
									name : $(this).attr('name') + "  金额:"
											+ $(this).attr('monthBudgetMoney')
											+ "元",
									hiddenName : $(this).attr('name'),
									subjectRate : $(this).attr('subjectRate'),
									monthBudgetMoney : $(this).attr(
											'monthBudgetMoney'),
									budgetMonth : $(this).attr('budgetMonth'),
									pId : $(this).attr('fatherId'),
									rootId : $(this).attr('rootId'),
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
	alert("科目名称: " + treeNode.hiddenName + "\n" + "所占比例: "
			+ treeNode.subjectRate + "%\n" + " 预算金额: "
			+ treeNode.monthBudgetMoney + "元");
}

//=================================== zTree显示结束
</script>
	</body>
</html>
