<%@ page language="java" import="java.util.*,com.task.entity.*"
	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sd" uri="/struts-dojo-tags"%>
<%@include file="checkAdmin.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<LINK href="css/admin.css" type="text/css" rel="stylesheet">
		<%@include file="/util/inc.jsp"%>
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
	</HEAD>
	<BODY>
		<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%"
			style="background: url('images/menu_bg.jpg')" border=0>
			<TR>
				<TD vAlign=top align=middle>
					<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>

						<TR>
							<TD height=10></TD>
						</TR>
					</TABLE>
					<!-- =============================================模块功能管理============================== -->
					<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
						<TR height=22>
							<TD
								style="PADDING-LEFT: 50px; background: url('images/menu_bt.jpg') no-repeat;">
								<A class=menuParent onclick=expand(1) href="javascript:void(0);">模块功能管理</A>
							</TD>
						</TR>
						<TR height=4>
							<TD></TD>
						</TR>
					</TABLE>
					<TABLE id=child1 style="DISPLAY: none" cellSpacing=0 cellPadding=0
						width="100%" border=0>

						<TR height=20>
							<TD align="left" colspan="2">
								<ul id="treeDemo" class="ztree"></ul>
							</TD>
						</TR>

						<TR height=4>
							<TD colSpan=2></TD>
						</TR>
					</TABLE>
					<s:if test="#session.adminusers==null">
						<!-- =============================================系统设置============================== -->
						<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
							<TR height=22>
								<TD
									style="PADDING-LEFT: 50px; background: url('images/menu_bt.jpg') no-repeat;">
									<A class=menuParent onclick=expand(2)
										href="javascript:void(0);">系统设置</A>
								</TD>
							</TR>
							<TR height=4>
								<TD></TD>
							</TR>
						</TABLE>
						<TABLE id=child2 style="DISPLAY: none" cellSpacing=0 cellPadding=0
							width="100%" border=0>

							<TR height=20>
								<TD align=middle width=30>
									<IMG height=9 src="images/menu_icon.gif" width=9>
								</TD>
								<TD>
									<A style="" href="updatePassword.jsp" target=main>修改密码</A>
								</TD>

							</TR>
							<s:if test="#session.admin.type=='system'">
								<TR height=20>
									<TD align=middle width=30>
										<IMG height=9 src="images/menu_icon.gif" width=9>
									</TD>
									<TD>
										<A style=""
											href="companyInfoAction_showCompanyInfos.action?frommenu=1"
											target=main>公司信息管理</A>
									</TD>
								</TR>

								<TR height=20>
									<TD align=middle width=30>
										<IMG height=9 src="images/menu_icon.gif" width=9>
									</TD>
									<TD>
										<A style="" href="addhome.jsp" target=main>添加主页</A>
									</TD>
								</TR>
								<TR height=20>
									<TD align=middle width=30>
										<IMG height=9 src="images/menu_icon.gif" width=9>
									</TD>
									<TD>
										<A style="" href="AdminAction!showsuperadmin.action"
											target=main>超级管理员管理</A>
									</TD>
								</TR>
								<TR height=20>
									<TD align=middle width=30>
										<IMG height=9 src="images/menu_icon.gif" width=9>
									</TD>
									<TD>
										<A style=""
											href="ModuleFunctionAction!copyUsersMf_show.action"
											target=main>人员功能转移</A>
									</TD>
								</TR>
								<TR height=20>
									<TD align=middle width=30>
										<IMG height=9 src="images/menu_icon.gif" width=9>
									</TD>
									<TD>
										<A style="" href="<%=basePath%>/admin/delModulByUsers.jsp"
											target=main>人员功能去除</A>
									</TD>
								</TR>
							</s:if>
							<s:if test="#session.admin.type=='super'">
								<TR height=20>
									<TD align=middle width=30>
										<IMG height=9 src="images/menu_icon.gif" width=9>
									</TD>
									<TD>
										<A style=""
											href="companyInfoAction_showCompanyInfos.action?frommenu=1"
											target=main>公司信息管理</A>
									</TD>
								</TR>
								<TR height=20>
									<TD align=middle width=30>
										<IMG height=9 src="images/menu_icon.gif" width=9>
									</TD>
									<TD>
										<A style="" href="ModuleFunctionAction!showdispatcher.action"
											target=main>指派授权</A>
									</TD>
								</TR>
								<TR height=20>
									<TD align=middle width=30>
										<IMG height=9 src="images/menu_icon.gif" width=9>
									</TD>
									<TD>
										<A style=""
											href="ModuleFunctionAction!copyUsersMf_show.action"
											target=main>人员功能转移</A>
									</TD>
								</TR>
								<TR height=20>
									<TD align=middle width=30>
										<IMG height=9 src="images/menu_icon.gif" width=9>
									</TD>
									<TD>
										<A style="" href="<%=basePath%>/admin/delModulByUsers.jsp"
											target=main>人员功能去除</A>
									</TD>
								</TR>
							</s:if>
							<TR height=4>
								<TD colSpan=2></TD>
							</TR>
						</TABLE>
					</s:if>
				</TD>
				<TD width=1 bgColor=#d1e6f7></TD>
			</TR>
		</TABLE>
	</BODY>
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
	$
			.ajax( {
				url : 'ModuleFunctionAction!findAllMf.action',
				type : 'post',
				dataType : 'json',
				cache : true,
				success : function(doc) {
					var zNodes = [];
					$(doc)
							.each(function() {
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
</script>
</HTML>
