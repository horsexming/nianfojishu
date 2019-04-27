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
td:hover .qs_ul {
	display: block;
}

.qs_ul {
	display: none;
	border: 1px solid #999;
	list-style: none;
	margin: 0;
	padding: 0;
	position: absolute;
	width: auto;
	background: #CCC;
	color: green;
}

.ztree li a {
	color: #fff;
}

.Freezing {
	z-index: 20;
	position: relative;
	top: expression(this.offsetParent.scrollTop);
	background: #fff;
}
</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="ChartNOSQAction_findAllccList.action" method="post">
					<table class="table">
						<tr>
							<th align="right">
								申请人
							</th>
							<td>
								<input type="text" value="${cc.sjsqUsers}"
									name="cc.sjsqUsers" />
							</td>
							<th align="right">
								编码类别
							</th>
							<td>
								<div class="zTreeDemoBackground left">
									<ul class="list">
										<li class="title">
											<input id="wgType" type="text" readonly="readonly" value=""
												style="width: 120px;" name="cc.type" />
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
						<tr>
							<th align="right">
								编码
							</th>
							<td>
								<input type="text" value="${cc.chartNO}" name="cc.chartNO" />
							</td>
							<th align="right">
								申请单号
							</th>
							<td>
								<input type="text" value="${cc.sqNo}" name="cc.sqNo" />
							</td>
						</tr>
						<tr>
							<th align="right">
								所属产品编码
							</th>
							<td>
								<input type="text" value="${cc.cpcode}" name="cc.cpcode" />
							</td>
							<th align="right">
								申请时间
							</th>
							<td>
								<input type="text" value="${pageList.chartnosq.addTime}"  class="Wdate"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" name="cc.chartnosq.addTime" />
							</td>
						</tr>
					</table>
					<input type="submit" value="查询" class="input">
					<input type="button" value="查询(所有)" class="input" onclick="cxAll(this.form)">
					<input type="button" value="导出" class="input" onclick="exportExcel(this.form);todisabledone(this)" data="downData">
				</form>
			<form action="ChartNOSQAction_disableccList.action" method="post" onsubmit="return check()">
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th>
							<input id="checkNotAll" type="checkbox" name="invest"
									value="checkbox" onClick="selectIt(this)">
								<label for="checkNotAll">
									全选
								</label>
						</th>
						<th>
							序号
						</th>
						<th>
							编码
						</th>
						<th>
							编码(不带点)
						</th>
						<th>
							编码类别
						</th>
						<th>
							状态
						</th>
						<th>
							实际申请人
						</th>
						<th>
							申请单号
						</th>
						<th>
							产品(名称)编码
						</th>
						<th>
							申请时间
						</th>
						<th>
							备注
						</th>
						<th>
							审批动态
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="ccList" id="pageList" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<input type="checkbox" name="arrayId" value="${pageList.id}">
						</td>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>
							${pageList.chartNO}
						</td>
						<td>
							${pageList.chartcode}
						</td>
						<td>
							${pageList.type}
						</td>
						<td>
							<s:if test="#pageList.isuse == 'YES'">
								使用
							</s:if>
							<s:else>
								
							</s:else>
						</td>
						<td>
							${pageList.sjsqUsers}
						</td>
						<td>
							${pageList.sqNo}
						</td>
						<td>
							${pageList.cpcode}
						</td>
						<td>
							${pageList.chartnosq.addTime}
						</td>
						<td class="lie8" align="left"
									style="max-width: 80px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis ; ">
									<font size="1">${pageList.remak}</font>
									<ul class="qs_ul">
										<li>
											${pageList.remak}
										</li>
									</ul>
								</td>
						<td>
							<a
								href="CircuitRunAction_findAduitPage.action?id=${pageList.chartnosq.epId}">审批动态
							</a>
						</td>
						<td>
							<s:if test="#pageList.isuse == 'YES'">
								<a href="ChartNOSQAction_disablecc.action?id=${pageList.id}" onclick="return confirm('确定要禁用吗?')">暂时停用</a>/
								<a href="ChartNOSQAction_findccById.action?id=${pageList.id}&cpage=${cpage}">修改</a>
							</s:if>
						</td>
						</tr>
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
				<input type="submit" value="暂时停用" class="input" id="sub"  onclick="todisabled(this)"/>
			</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function exportExcel(obj){
		obj.action = "ChartNOSQAction_exportExcel.action";
	 	obj.submit();
	  	obj.action = "ChartNOSQAction_findAllccList.action";
	 	}
function cxAll(obj){
		obj.action = "ChartNOSQAction_findAllccList.action?status=all";
	 	obj.submit();
	  	obj.action = "ChartNOSQAction_findAllccList.action";
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


function selectIt(obj) {
	var checkboxs = document.getElementsByName("arrayId");
	if(obj!=null && checkboxs!=null){
		if(obj.checked == true){
			for(var i=0;i<checkboxs.length; i++){
				checkboxs[i].checked = "checked";
			}
		}else if(obj.checked == false){
			for(var i=0;i<checkboxs.length; i++){
				checkboxs[i].checked = false;
			}
		}
	}
	
}
function check(){
	var checkboxs = document.getElementsByName("arrayId");
	var bool = true;
	for(var i=0;i<checkboxs.length; i++){
			if(checkboxs[i].checked == true){
				bool = false;
				break;
			}
		}
	if(bool){
		alert('请至少选择一个');
		return false;
	}
}

</SCRIPT>
	</body>
</html>
