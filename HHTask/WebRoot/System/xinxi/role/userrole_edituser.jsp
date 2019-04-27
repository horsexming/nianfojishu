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
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/zTree/js/jquery.ztree.excheck-3.5.min.js">
</script>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div align="center">
				<form action="UserRoleAction_addusers.action" id="myform"
					method="post">

					<input type="hidden" name="userRole.id" value="${userRole.id}" />
					<table class="table" style="width: 60%">
						<tr>
							<td align="left" colspan="2" id="persons">
							</td>
						</tr>
						<tr>
							<td id="test2">
								<%--<=================================树形结构 ========================>--%>
								<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%"
									border=0>
									<!-- =============================================功能管理============================== -->
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
							<td align="center" colspan="2">
								<input type="button" id="submitBtn" value="绑定"
									style="width: 100px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
				<table>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
var index = 0;<%--changeType("2");--%>
function submit() {
	$.ajax( {
		type : "POST",
		url : "UserRoleAction_addusers.action",
		data : $('#myform').serialize(),
		dataType : "json",
		success : function(json) {
			alert(json.message);
            document.getElementById("submitBtn").disabled=false;
		}
	});
}
				
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
				for ( var i = 0; i < treeNode.length; i++) {
					var id = treeNode[i].id;
					if(id<99999999){
						$("#persons").append(
							'<input type="hidden" class="personsubmit" value="'
									+ id + '"name="users[' + index++
									+ '].id" >');
					}
					
				}
				document.getElementById("submitBtn").disabled=true;
				submit();
	
	

})

//<<===================填充已绑人员==================>>
function checkedbox(){
$.ajax({
		type: "POST",
		url: "UserRoleAction_findbindingUserbyid.action",
		data: {
			id : ${userRole.id}
		},
		dataType : "json",
    	// async: false,
		success: function(obj){
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			for(var i = 0; i <obj.length; i++){
				 zTree.checkNode(zTree.getNodeByParam("id", obj[i], null), true, true);
			}
		}
	});
};


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
$(document).ready(function() {
	parent.mfzTree();
	zNodes.extend(zNodes2);
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
});

parent.mfzTree = function() {
	$.ajax( {
		url : "DeptNumberAction!findAllDept.action",
		type : 'post',
		dataType : 'json',
		async: false,
		cache : true,
		success : function(doc) {
			$(doc).each(
					function() {
						
						zNodes.push({
							id : 99999999+$(this).attr('id'),
							pId : 99999999+$(this).attr('id'),
							name : $(this).attr('dept'),
<%--							+"---"+$(this).attr('id'),--%>
							target : "main",
							click : false
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
									zNodes2.push( {
														id : data[i][1],
														pId : fatherid,
														name : data[i][3]+"-"+data[i][2],
<%--														+"--"+data[i][0]+"---"+data[i][1],--%>
														target : "main",
														click : false
													});
								}
							},
							error : function() {
								alert("服务器异常!");
							}
						});

					});
			checkedbox();
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
	</body>
</html>