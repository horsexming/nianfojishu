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
<%--		<%@include file="/util/sonTop.jsp"%>--%>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/javascript/layer/theme/default/layer.css">
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/zTree/js/jquery.ztree.excheck-3.5.min.js">
			</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js">
			</script>
			<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/layer/layer.js">
			</script>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div align="center">
				

					<table class="table" style="width: 60%">
							<td align="left" colspan="2" id="persons">
							</td>
						</tr>

						<tr>
							<td id="test2">
								<%--<=================================树形结构 ========================>--%>
<%--								<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%"--%>
<%--									border=0>--%>
<%--									<!-- =============================================功能管理============================== -->--%>
<%--									<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>--%>
<%--										<TR height=22>--%>
<%--											<TD style="PADDING-LEFT: 50px;">--%>
<%--																										background: url('images/menu_bt.jpg') no-repeat;--%>
<%--												<A class=menuParent onclick=expand(1)--%>
<%--													href="javascript:void(0);">所有模块</A>--%>
<%--											</TD>--%>
<%--										</TR>--%>
<%--									</TABLE>--%>
									<TABLE id=child1 style="DISPLAY: none" cellSpacing=0
										cellPadding=0 width="100%" border=0>
										<tr>
											<td style="font-size: 12px;">[<a id="expandAllBtn" href="#" onclick="return false;">全部展开</a> ]
													&nbsp;[<a id="collapseAllBtn" href="#" onclick="return false;">全部折叠</a> ]</td>
										</tr>
										<TR height=20>
											<TD align="center" colspan="2">
												<ul id="treeDemo" class="ztree"></ul>
											</TD>
										</TR>
									</TABLE>
<%--									</TD>--%>
<%--									</TR>--%>
<%--								</TABLE>--%>
								<div id="mf"></div>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
							<button class="layui-btn layui-btn-primary" id="submitBtn">添加人员</button>
							</td>
						</tr>
					</table>
				<table>


				</table>

			</div>
			<br>

		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
///<<===================oldCode==================>>
function changeType(type) {
	if (type == "1") {
		$("#userName").val("${Users.name}");
		$("#userName").css("display", "block");
		$("#users").css("display", "none");
		$("#userName").attr("readonly", "readonly");
		$("#dept").empty();
		$("<option value='${Users.dept}'>${Users.dept}</option>").appendTo(
				"#dept");
	} else if (type == "2") {
		$("#userName").css("display", "none");
		$("#users").css("display", "block");
		$("#personCode").val("");
		//显示所有部门信息
		$.ajax( {
			url : 'DeptNumberAction!findAllDept.action',
			dataType : 'json',
			cache : false,//防止数据缓存
			success : function(allDdept) {
				$("#dept").empty();
				$("<option value=''>--请选择部门--</option>").appendTo("#dept");
				$(allDdept).each(
						function() {
							$(
									"<option value='" + this.dept + "'>"
											+ this.dept + "</option>")
									.appendTo("#dept");
						});
			}

		});
		//显示部门对应的员工信息
		$("#dept").bind(
				"change",
				function() {
					if ($("#dept").val() != "") {
						$.ajax( {
							url : "UsersAction!findUsersByDept.action",
							type : 'post',
							dataType : 'json',
							cache : false,//防止数据缓存
							data : {
								deptName : $("#dept").val()
							},
							success : function(useradsfa) {
								$("#users").empty();//清空
								$("<option></option>").appendTo("#users");
								$(useradsfa).each(
										function() {
											$(
													"<option value='"
															+ this.code + "|"
															+ this.id + "|"
															+ this.name + "'>"
															+ this.name
															+ "</option>")
													.appendTo("#users")
										});
							},
							error : function() {
								alert("服务器异常!");
							}
						});
					}

				});

	}
	$("#users").bind("change", function() {
		var users = $("#users").val();
		var usersData = users.split("|");
		var id = usersData[1];
		$("#userName").val(usersData[2]);
		$("#personCode").val(usersData[0]);
		$("#personid").val(id);
	});

}

//<<===================oldover ==================>>
				
$("#submitBtn").click(function() {
				index = 0;
				var par = document.getElementById("persons")
				var child = par.childNodes;
				for ( var i = child.length - 1; i >= 0; i--) {
					var x = par.removeChild(child[i]);
					if (x.nodeType == 1) {
						x = null;
					}
				}
				
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				var treeNode = zTree.getCheckedNodes(true);
				var id = null;
				var name = null;
				for ( var i = 0; i < treeNode.length; i++) {
					var id_now = treeNode[i].id;
					if(id==null){
						if(id_now<99999999){
						id = id_now;
						name = treeNode[i].name;
						}
					}else{
						if(treeNode[i].id<99999999){
						id += ";"+id_now;
						name += ";"+treeNode[i].name
						}
					}
					
				}
				 parent.$('#fid3').val(id);
				 parent.$('#tishiPerson3').val(name);
   				parent.layer.tips('可以查看的人员', '#tishiPerson3', {time: 5000});
   				//document.getElementById("submitBtn").disabled=true;
    			parent.layer.close(parent.layer.getFrameIndex(window.name));
				//$("#fid").val(id);
				//$("#tishiPerson").val(name);
				
				
})

//<<===================自动组装树形结构==================>>
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
var zNodes = [];
var zNodes2 = [];
var DeptNameArr="";
var $j = jQuery.noConflict(); 
$j(document).ready(function() {
	parent.mfzTree();
	zNodes.extend(zNodes2);
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
});

parent.mfzTree = function() {
	var ids = parent.$('#fid3').val();
	var splitIds = ids.split(";");
	$.ajax( {
		url : 'DeptNumberAction!findAlldept.action',
		type : 'post',
		dataType : 'json',
		async: false,
		cache : true,
		success : function(doc) {
			$(doc).each(
					function() {
						var belongLayer = $(this).attr('belongLayer');
					isOpen=false;
					if(belongLayer==0||belongLayer==1){
						isOpen=true;
					}
						zNodes.push({
							id : 99999999+$(this).attr('id'),
							pId : 99999999+$(this).attr('fatherId'),
							name : $(this).attr('dept'),
<%--							+"---"+$(this).attr('id'),--%>
							target : "main",
							click : false,
							open:isOpen
						});
						var fatherid=99999999+$(this).attr('id'),
						DeptNameArr=($(this).attr('dept'));
						$.ajax( {
							url : "UserRoleAction!findUsersByDept.action",
							data:{
								DeptName:DeptNameArr
							},
							type : 'post',
							dataType : 'json',
							async: false,
							cache : true,//防止数据缓存
							success : function(obj) {
								var data=obj[0] 
								for(var i=0;i<obj[0].length;i++){
									var checked = false;
									for(var j=0;j<splitIds.length;j++){
										if(splitIds[j]==data[i][1]){
											checked=true;
											break;
										}
									}
									zNodes2.push( {
														id : data[i][1],
														pId : fatherid,
														name : data[i][2],
<%--														+"--"+data[i][0]+"---"+data[i][1],--%>
														target : "main",
														click : false,
														checked:checked
													});
								}
							},
							error : function() {
								alert("服务器异常!");
							}
						});
						
						
<%--						DeptNameArr+=($(this).attr('dept')+',');--%>
					});
<%--			$.ajax( {--%>
<%--							url : "UserRoleAction!findUsersByDept.action",--%>
<%--							data:{--%>
<%--								DeptName:DeptNameArr--%>
<%--							},--%>
<%--							type : 'post',--%>
<%--							dataType : 'json',--%>
<%--							async: false,--%>
<%--							cache : true,//防止数据缓存--%>
<%--							success : function(obj) {--%>
<%--								var data=obj[0] --%>
<%--								for(var i=0;i<obj[0].length;i++){--%>
<%--									zNodes2.push( {--%>
<%--														id : data[i][1],--%>
<%--														pId : 99999999+data[i][0],--%>
<%--														name : data[i][2]+"--"+data[i][0]+"---"+data[i][1],--%>
<%--														target : "main",--%>
<%--														click : false--%>
<%--													});--%>
<%--								}--%>
<%--							},--%>
<%--							error : function() {--%>
<%--								alert("服务器异常!");--%>
<%--							}--%>
<%--						});--%>
			
<%--			checkedbox();--%>
		},
		error : function() {
			alert("服务器异常!");
		}
	});
};

Array.prototype.extend = function (other_array) {
    /* you should include a test to check whether other_array really is an array */
    other_array.forEach(function(v) {this.push(v)}, this);    
}



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




$(function(){
	$("#expandAllBtn").bind("click", {
		type : "expandAll"
	}, expandNode);
	$("#collapseAllBtn").bind("click", {
		type : "collapseAll"
	}, expandNode);
})

function expandNode(e) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), type = e.data.type, nodes = zTree
			.getSelectedNodes();
	if (type.indexOf("All") < 0 && nodes.length == 0) {
		alert("请先选择一个父节点");
	}

	if (type == "expandAll") {
		zTree.expandAll(true);
	} else if (type == "collapseAll") {
		zTree.expandAll(false);
	} else {
		var callbackFlag = $("#callbackTrigger").attr("checked");
		for ( var i = 0, l = nodes.length; i < l; i++) {
			zTree.setting.view.fontCss = {};
			if (type == "expand") {
				zTree.expandNode(nodes[i], true, null, null, callbackFlag);
			} else if (type == "collapse") {
				zTree.expandNode(nodes[i], false, null, null, callbackFlag);
			} else if (type == "toggle") {
				zTree.expandNode(nodes[i], null, null, null, callbackFlag);
			} else if (type == "expandSon") {
				zTree.expandNode(nodes[i], true, true, null, callbackFlag);
			} else if (type == "collapseSon") {
				zTree.expandNode(nodes[i], false, true, null, callbackFlag);
			}
		}
	}
}
</script>
	</body>
</html>