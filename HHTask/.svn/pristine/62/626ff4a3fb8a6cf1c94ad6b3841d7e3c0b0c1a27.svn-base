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
		<style type="text/css">
.ztree li a {
	color: #fff;
}
</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="ChartNOSQAction_findAllcqList.action" method="post">
					<table class="table">
						<tr>
							<th>
								申请人
							</th>
							<td>
								<input type="text" value="${cq.addUser}" name="cq.addUser" />
							</td>
							<th>
								类别
							</th>
							<td>
								<div class="zTreeDemoBackground left">
									<ul class="list">
										<li class="title">
											<input id="wgType" type="text" readonly="readonly" value=""
												style="width: 120px;" name="cq.type" />
											<a id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a>
										</li>
									</ul>
								</div>
								<div id="menuContent" class="menuContent"
									style="display: none; position: absolute;">
									<ul id="treeDemo" class="ztree"
										style="margin-top: 0; width: 160px;"></ul>
								</div>
							</td>
						</tr>
					</table>
					<input type="submit" value="查询" class="input" />
					<input type="button" value="查询(所有)" class="input"
						onclick="cxAll(this.form)">
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							申请人
						</th>
						<th>
							申请数量
						</th>
						<th>
							实际申请数量
						</th>
						<th>
							返还数量
						</th>
						<th>
							申请编码从
						</th>
						<th>
							到
						</th>
						<th>
							产品(名称)编码
						</th>
						<th>
							申请单号
						</th>
						<th>
							申请时间
						</th>
						<th>
							审批动态
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="cqList" id="pageList" status="statussdf">
						<s:if test="#statussdf.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#statussdf.index+1" />
						</td>
						<td>
							${pageList.addUser}
						</td>
						<td>
							${pageList.sqNum}
						</td>
						<td>
							${pageList.sjsqNum}
						</td>
						<td>
							${pageList.hsNum}
						</td>
						<td>
							${pageList.firstNo}
						</td>
						<td>
							${pageList.endNo}
						</td>
						<td>
							${pageList.cpcode}
						</td>
						<td>
							${pageList.sqNo}
						</td>
						<td>
							${pageList.addTime}
						</td>
						<td>
							<a
								href="CircuitRunAction_findAduitPage.action?id=${pageList.epId}">${pageList.epstatus}</a>
						</td>
						<td>
							<s:if test='#pageList.epstatus=="同意"'>
								<a
									href="ChartNOSQAction_exportExcel.action?cc.sqNo=${pageList.sqNo}" data="downData" onclick="todisabledone(this)">导出</a>
							</s:if>
							<s:if test='#pageList.epstatus == "打回"'>
									/<a href="ChartNOSQAction_cxsq.action?cq.id=${pageList.id}">重新申请</a>
							</s:if>
						</td>
					</s:iterator>
					<tr>
						<td colspan="13" align="right">
							第
							<font color="red"><s:property value="cpage" /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />

						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
function cxAll(obj){
		obj.action = "ChartNOSQAction_findAllcqList.action?status=all";
	 	obj.submit();
	  	obj.action = "ChartNOSQAction_findAllcqList.action";
	 	}
$(document).ready(function() {
	loadzTree("CategoryAction_findcyListByrootId.action");
});
function loadzTree(needUrl) {
	$.ajax( {
		url : needUrl,
		type : 'post',
		dataType : 'json',
		data:{status:'编码'},
		cache : true,
		success : function(doc) {
			var zNodes = [];
			$(doc).each(function() {
				zNodes.push( {
					id : $(this).attr('id'),
					pId : $(this).attr('fatherId'),
					name : $(this).attr('name'),
					target : "main",
					click : false
				});
			});
			var setting = {
				view : {
					dblClickExpand : false
				},
				data : {
					simpleData : {
						enable : true
					}
				},
				callback : {
					beforeClick : beforeClick,
					onClick : onClick
				}
			};
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.expandAll(true);
		},
		error : function() {
			alert("服务器异常!");
		}
	});
};
function beforeClick(treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);
	if (!check)
		alert("只能选择最底层");
	return check;
}

function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
			.getSelectedNodes(), v = "";
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	for ( var i = 0, l = nodes.length; i < l; i++) {
		v = nodes[i].name;
	}
	//if (v.length > 0 ) v = v.substring(0, v.length-1); 
	cityObj = $("#wgType").val(v);
}

function showMenu() {
	var cityObj = $("#wgType");
	var cityOffset = $("#wgType").offset();
	$("#menuContent").css( {
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
			event.target).parents("#menuContent").length > 0)) {
		hideMenu();
	}
}
	
	
	</SCRIPT>
	</body>
</html>
