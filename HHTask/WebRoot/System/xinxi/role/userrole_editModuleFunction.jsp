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
			src="${pageContext.request.contextPath}/javascript/zTree/js/jquery.ztree.excheck-3.5.min.js">
</script>
		<script type="text/javascript">
var gongwei;
var index = 0;
var n = 1;
function submit() {
	chexkbox1();
	$.ajax( {
		type : "POST",
		url : "UserRoleAction_update.action",
		data : $('#myform').serialize(),
		dataType : "json",
		success : function(json) {
			alert(json.message);
            document.getElementById("submitBtn").disabled=false;
		}
	});
}

$(function() {
	$.ajax( {
		type : "POST",
		url : "ModuleFunctionAction!findAllname.action",
		data : {},
		dataType : "json",
		success : function(json) {
			gongwei = json;
			var selector = $('#mytable select:last');
			for ( var i = 0; i < json.length; i++) {
				$("#test").append(
						'<input type="checkbox"  id="' + json[i][0]
								+ '" value="' + json[i][0]
								+ '" name="checkbox1"/>' + json[i][1]
								+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				if (n % 6 == 0) {
					$("#test").append('<br/><br/>')
				}
				n++;
			}

		}
	});

	$('#submitBtn').bind(
			'click',
			function() {
				index = 0;
				var par = document.getElementById("mf")
				var child = par.childNodes;
				for ( var i = child.length - 1; i >= 0; i--) {
					var x = par.removeChild(child[i]);
					if (x.nodeType == 1) {
						x = null;
					}
				}

				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				var treeNode = zTree.getCheckedNodes(true);
				for ( var i = 0; i < treeNode.length; i++) {
					var id = treeNode[i].id;
					$("#mf").append(
							'<input type="hidden" class="mfsubmit" value="'
									+ id + '"name="moduleFunctions[' + index++
									+ '].id" >');
				}
				document.getElementById("submitBtn").disabled=true;
				submit();

			});
});

function chexkbox1() {
	var chexboxs = document.getElementsByName("checkbox1");
for(var i = 0; i < chexboxs.length; i++){
 		if(chexboxs[i].checked == true){
 			$("#mf").append('<input type="hidden" class="mfsubmit" value="'+chexboxs[i].value +'"name="moduleFunctions['+ index++ +'].id" >');
<%-- 			$("#test").append('<input type="hidden" value="'+chexboxs[i].value +'"name="moduleFunctionIdist['+ index++ +']">');--%>
 		}
 	}
}
function checkedbox(){
	$.ajax({
							type: "POST",
							url: "UserRoleAction_findbindingMFbyid.action",
							data: {
								id : ${userRole.id}
							},
							dataType : "json",
							success: function(obj){
								var zTree = $.fn.zTree.getZTreeObj("treeDemo");
								
								for(var i = 0; i <obj.length; i++){
									 zTree.checkNode(zTree.getNodeByParam("id", obj[i], null), true, false);
								}
							}
						});
}; 

		</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div align="center">
				<form id="myform">
					<input type="hidden" name="userRole.id" value="${userRole.id}" />
					
					角色名称:${userRole.name}<br/>
					角色描述:${userRole.description}
					
					<table id="mytable" class="table" style="width: 60%">
						<tr>
							<th>
								绑定功能
							</th>
							<%--test 全拉出--%>
							<td id="test2">
								<%--<=================================树形结构 ========================>--%>
								<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%"
									border=0>
									<!-- =============================================模块功能管理============================== -->
									<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
										<TR height=22>
											<TD style="PADDING-LEFT: 50px;">
												<%--														background: url('images/menu_bt.jpg') no-repeat;--%>
												<A class=menuParent onclick=expand(1)
													href="javascript:void(0);">所有模块</A>
											</TD>
										</TR>
									</TABLE>
									<TABLE id=child1 style="DISPLAY: none" cellSpacing=0
										cellPadding=0 width="100%" border=0>

										<TR height=20>
											<TD align="left" colspan="2">
												<ul id="treeDemo" class="ztree"></ul>
											</TD>
										</TR>
									</TABLE>
									</TD>
									</TR>
								</TABLE>
								<div id="mf"></div>
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<input id="submitBtn" type="button" value="确定submit" />
							</th>
						</tr>
					</table>

					<script language=javascript>
//选择事件
function expand(el) {
	childObj = document.getElementById("child" + el);
	if (childObj.style.display == 'none') {
		childObj.style.display = 'block';
	} else {
		childObj.style.display = 'none';
	}
	return;
}
</script>

					<script type="text/javascript">

//========================================zTree显示
//自动组装树形结构
var setting= {
	data : {
		simpleData : {
			enable : true
			
		},
	},
	callback : {
		onClick : onClick
	},
	check : {
		enable : true,
	},
};
//读取树形数据
$(document).ready(function() {
	parent.mfzTree();
});

parent.mfzTree = function() {
	$.ajax( {
		url : 'ModuleFunctionAction!findAllMf.action',
		type : 'post',
		dataType : 'json',
		cache : true,
		success : function(doc) {
			var zNodes = [];
			$(doc).each(
					function() {
								//子模块
									if ($(this).attr('functionLink') != null
											&& $(this).attr('functionLink').length > 0
											&& $(this).attr('isSubModule') == 'true') {
										zNodes
												.push( {
													id : $(this).attr('id'),
													pId : $(this).attr(
															'fatherId'),
													name : $(this).attr(
															'functionName'),
													url : "ModuleFunctionAction!findMfById.action?id="
															+ $(this)
																	.attr('id'),
													target : "main",
													click : false,
													icon : "${pageContext.request.contextPath}"
															+ "/javascript/zTree/css/zTreeStyle/img/diy/2.png"
												});
									} else {
										zNodes
												.push( {
													id : $(this).attr('id'),
													pId : $(this).attr(
															'fatherId'),
													name : $(this).attr(
															'functionName'),
													url : "ModuleFunctionAction!findMfById.action?id="
															+ $(this)
																	.attr('id'),
													target : "main",
													click : false
												});
									}

								});
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			checkedbox();
		},
		error : function() {
			alert("服务器异常!");
		}
	});
};
//添加节点
parent.addzTree = function() {
	var id = parent.id;
	var pId = parent.pId;
	var name = parent.name;
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var treeNode = zTree.getNodeByParam("id", pId);//查询父类节点
	//添加节点
	treeNode = zTree.addNodes(treeNode, {
		id : id,
		pId : pId,
		name : name,
		open : true,
		url : "ModuleFunctionAction!findMfById.action?id=" + id,
		target : "main",
		click : false
	});
};
//删除节点
parent.delzTree = function() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
			.getSelectedNodes(), treeNode = nodes[0];
	if (nodes.length == 0) {
		alert("请先选择一个节点");
		return;
	}
	zTree.removeNode(treeNode, true);

};
//更新节点
parent.updatezTree = function() {
	var name = parent.name;

	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
			.getSelectedNodes(), treeNode = nodes[0];
	if (nodes.length == 0) {
		alert("请先选择一个节点");
		return;
	}
	treeNode.name = name;
	zTree.updateNode(treeNode, true);

};

//点击回调函数
function onClick(event, treeId, treeNode, clickFlag) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = zTree.getSelectedNodes();
	//折叠、展开操作`
	for ( var i = 0, l = nodes.length; i < l; i++) {
		zTree.expandNode(nodes[i], null, null, null, true);
	}

	//treeNode.open = true;

	//var treeNode = zTree.getNodeByParam("id", 5);//查询父类节点
	//treeNode.open = true;
	//treeNode.name = "testedit";

}
expand(1);
//=================================== zTree显示结束
</script>
					<%--</HTML>--%>

					<%--</table>--%>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
