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
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/zTree/js/jquery.ztree.excheck-3.5.js">
</script>
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
			<div align="center">
				<div id="showMessage" align="center" style="color: red;">
				</div>
				<div align="center" style="font-size: 18px; font-weight: bolder;">
					绑定部门
				</div>
				<hr />
				<div align="left">
					<!-- 显示 部门-->
					<div style="width: 40%; height: 100%; float: left;">
						请选择部门:
						<br />
						<select id="dept" style="width: 200px; margin-left: 10px;"
							size="30" onchange="showDeptSub(this.value)">

						</select>
					</div>
					<!--科目树形 -->
					<div
						style="border-left: 1px solid #000000; float: left; width: 58%">
						请勾选科目:
						<br />
						<ul id="treeDemo" class="ztree"></ul>
					</div>
					<div style="clear: both;"></div>
				</div>
			</div>
			<br>
			<div id="ys"></div>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//========================================zTree显示
var deptId;

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
	showDept();
});

function showDeptSub(id) {
	deptId = id;
	$.ajax( {
		type : 'post',
		url : 'SubjectBudgetAction!findSBRateFroDept.action',
		data : {
			id : id
		},
		dataType : 'json',
		cache : true,
		success : function(doc) {

			var zNodes = [];
			$(doc).each(
					function() {
						zNodes.push( {
							id : $(this).attr('id'),
							name : $(this).attr('name') + " "
									+ $(this).attr('subjectRate') + "%",
							hiddenName : $(this).attr('name'),
							subjectRate : $(this).attr('subjectRate'),
							pId : $(this).attr('fatherId'),
							rootId : $(this).attr('rootId'),
							belongLayer : $(this).attr('belongLayer'),
							checked : $(this).attr('checked'),
							open : true,
							click : false
						});
					});
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			//父子关联关系(双向关联)
			$.fn.zTree.getZTreeObj("treeDemo").setting.check.chkboxType = {
				"Y" : "p",
				"N" : "p"
			};
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
	var subId = treeNode.id;
	$
			.ajax( {
				type : 'post',
				url : 'SubjectBudgetAction!updateSubDept.action?subBudgetRate.id=' + subId,
				data : {
					id : deptId
				},
				dataType : 'json',
				cache : true,
				success : function(bool) {
					if (bool == false) {
						alert("勾选失败!请检查数据有效性!");
					}
				},
				error : function() {
					alert("服务器异常!");
				}
			});
}

var showDept = function() {
	//查询所有的部门
	$.ajax( {
		url : 'DeptNumberAction!findAllDept.action',
		dataType : 'json',
		cache : false,//防止数据缓存
		success : function(allDdept) {
			$(allDdept).each(
					function() {
						$(
								"<option value='" + this.id + "'>" + this.dept
										+ "</option>").appendTo("#dept");
					});
		}

	});
}

//=================================== zTree显示结束
</script>
	</body>
</html>
