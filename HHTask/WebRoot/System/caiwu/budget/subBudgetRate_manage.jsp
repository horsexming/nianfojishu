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
			<div align="center">
				<div>
					<form id="rootForm" style="margin: 0px; padding: 0px;">
						<input name="subBudgetRate.belongLayer" value="1" type="hidden" />
						<input name="subBudgetRate.fatherId" value="0" type="hidden" />
						<input name="subBudgetRate.subjectRate" value="0" type="hidden" />
						<table class="table">
							<tr>
								<th>
									添加一级科目
								</th>
							</tr>
							<tr>
								<th>
									一级科目名:
									<input name="subBudgetRate.name" />
									<input type="button" value="添加" class="input"
										onclick="submitForm('rootForm','add')" />
								</th>
							</tr>
						</table>
					</form>
				</div>
				<div id="showMessage" align="center" style="color: red;">

				</div>
				<br />
				<div align="center"
					style="border: 1px #000000 solid; font-size: 16px; font-weight: bolder;">
					科目管理
				</div>
				<div align="left">
					<!-- 显示科目树形 -->
					<div style="width: 40%; height: 100%; float: left;" align="left">
						<div style="height: 100%;">
							<ul id="treeDemo" class="ztree"></ul>
						</div>
					</div>
					<!--科目管理操作 -->
					<div
						style="border-left: 1px solid #000000; float: left; width: 58%">
						<form id="rootForm2" style="margin: 0px; padding: 0px;">
							<input type="hidden" id="id" name="id" />
							<input type="hidden" id="rootId" name="subBudgetRate.rootId" />
							<input type="hidden" id="fatherId" name="subBudgetRate.fatherId" />
							<input type="hidden" id="fatherName"
								name="subBudgetRate.fatherName" />
							<input type="hidden" id="belongLayer"
								name="subBudgetRate.belongLayer" />
							<table class="table" style="width: 100%;">
								<tr>
									<th align="center" colspan="2">
										科目管理
									</th>
								</tr>
								<tr>
									<th align="right">
										科目名:
									</th>
									<td>
										<input id="name" name="subBudgetRate.name" />
									</td>
								</tr>
								<tr>
									<th align="right">
										科目所占比例:
									</th>
									<td>
										<input id="subjectRate" name="subBudgetRate.subjectRate"
											onkeyup="value=value.replace(/[^\d\.]/g,'')" value="0" />
										%
									</td>
								</tr>
								<tr>
									<th align="center" colspan="2">
										<input type="button" value="添加下级" class="input"
											onclick="submitForm('rootForm2','add')" />
										<input type="button" value="修改" class="input"
											onclick="submitForm('rootForm2','update')" />
										<input type="button" value="删除" class="input"
											onclick="submitForm('rootForm2','del')" />
									</th>
								</tr>
							</table>
						</form>
					</div>
					<div style="clear: both;"></div>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//========================================zTree显示
var id, pId, name, subjectRate, rootId, belongLayer;

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
		url : 'SubjectBudgetAction!findAllSBRate.action',
		type : 'post',
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
	//var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	//var nodes = zTree.getSelectedNodes();
	//折叠、展开操作
	//for ( var i = 0, l = nodes.length; i < l; i++) {
	//	zTree.expandNode(nodes[i], null, null, null, true);
	//	}
	//赋值
	$("#id").val(treeNode.id);
	$("#rootId").val(treeNode.rootId);
	$("#fatherId").val(treeNode.id);
	$("#belongLayer").val(treeNode.belongLayer + 1);
	$("#name").val(treeNode.hiddenName);
	$("#fatherName").val(treeNode.hiddenName);
	$("#subjectRate").val(treeNode.subjectRate);
}

//添加节点
var addzTree = function() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var treeNode = zTree.getNodeByParam("id", pId);//查询父类节点
	//添加节点
	treeNode = zTree.addNodes(treeNode, {
		id : id,
		pId : pId,
		name : name + " " + subjectRate + "%",
		hiddenName : name,
		subjectRate : subjectRate,
		rootId : rootId,
		belongLayer : belongLayer,
		open : true,
		click : false
	});
};

//更新节点
var updatezTree = function() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
			.getSelectedNodes(), treeNode = nodes[0];
	if (nodes.length == 0) {
		alert("请先选择一个节点");
		return;
	}
	treeNode.name = name + " " + subjectRate + "%";
	treeNode.hiddenName = name;
	zTree.updateNode(treeNode, true);

};

//删除节点
var delzTree = function() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
			.getSelectedNodes(), treeNode = nodes[0];
	if (nodes.length == 0) {
		alert("请先选择一个节点");
		return;
	}
	zTree.removeNode(treeNode, true);

};

//添加表单
function submitForm(formId, status) {
	$("#showMessage").html("");
	if (status == "add") {
		$.ajax( {
			type : "POST",
			url : 'SubjectBudgetAction!addSubBudgetRate.action',
			dataType : 'json',
			data : $("#" + formId).serialize(),
			cache : false,//防止数据缓存
			success : function(mes) {
				if (mes.success) {
					$("#showMessage").html("添加科目" + mes.message + "成功!");
					var data = mes.data;
					id = data.id;
					pId = data.fatherId;
					name = data.name;
					subjectRate = data.subjectRate;
					rootId = data.rootId;
					belongLayer = data.belongLayer;
					addzTree();
				} else {
					$("#showMessage").html("添加科目" + mes.message + "失败!");
				}
			}
		});
	} else if (status == "update") {
		$.ajax( {
			type : "POST",
			url : 'SubjectBudgetAction!updateSubBudgetRate.action',
			dataType : 'json',
			data : $("#" + formId).serialize(),
			cache : false,//防止数据缓存
			success : function(mes) {
				if (mes.success) {
					$("#showMessage").html("修改科目成功!");
					var data = mes.data;
					name = data.name;
					subjectRate = data.subjectRate;
					updatezTree();
					$("#fatherName").val(data.name);
				} else {
					$("#showMessage").html("修改科目失败!");
				}
			}
		});
	} else if (status == "del") {
		$.ajax( {
			type : "POST",
			url : 'SubjectBudgetAction!delSubBudgetRate.action',
			dataType : 'json',
			data : $("#" + formId).serialize(),
			cache : false,//防止数据缓存
			success : function(mes) {
				if (mes.success) {
					$("#showMessage").html("删除科目" + mes.message + "成功!");
					var data = mes.data;
					if (data != null) {
						$("#id").val(data.id);
						$("#rootId").val(data.rootId);
						$("#fatherId").val(data.id);
						$("#belongLayer").val(data.belongLayer + 1);
						$("#name").val(data.name);
						$("#fatherName").val(data.name);
						$("#subjectRate").val(data.subjectRate);
					} else {
						$("#id").val("");
						$("#rootId").val("");
						$("#fatherId").val("");
						$("#belongLayer").val("");
						$("#name").val("");
						$("#fatherName").val("");
						$("#subjectRate").val("");
					}
					delzTree();
				} else {
					$("#showMessage").html("删除科目" + mes.message + "失败!");
				}
			}
		});
	}
	id = null;
	pId = null;
	name = null;
	subjectRate = null;
	rootId = null;
	belongLayer = null;
}
//=================================== zTree显示结束
</script>
	</body>
</html>
