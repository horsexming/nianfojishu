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
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">产品明细与维护</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<div>
			<div align="left" style="width: 100%">
				<!-- 显示树形流水卡片模板 -->
				<div style="float: left;width:30%" align="left">
					<div style="height: 100%;">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
				<!-- 添加流水卡片模板操作 -->
				<div align="right" id="showCardTemplate" style="float: left;display: none; height: 1000px;width: 68%">
					<iframe id="showCardIframe" src="" marginwidth="0"
							marginheight="0" hspace="0" vspace="0" frameborder="0"
							scrolling="no"
							style="width: 100%; height: 1500px; margin: 0px; padding: 0px;"></iframe>
							
				</div>
			</div>
				<div style="clear: both;"></div>
			</div>
			<br>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/zTree/js/jquery.ztree.excheck-3.5.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/zTree/js/jquery.ztree.exedit-3.5.js">
</script>
		<script type="text/javascript">
//========================================zTree显示
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
	},
	view : {
		fontCss : getFont,
		nameIsHTML : true,
		showTitle : true
	}
};

<%--function setCheck() {--%>
<%--	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), isCopy = true, isMove = true, prev = true, inner = true, next = true;--%>
<%--	zTree.setting.edit.drag.isCopy = isCopy;--%>
<%--	zTree.setting.edit.drag.isMove = isMove;--%>
<%----%>
<%--	zTree.setting.edit.drag.prev = prev;--%>
<%--	zTree.setting.edit.drag.inner = inner;--%>
<%--	zTree.setting.edit.drag.next = next;--%>
<%--}--%>

//加载树形数据
$(document).ready(loadTree());
var totalMaxCount = 0;
//生成
function loadTree() {
	$.ajax( {
		url : 'ProcardTemplateAction!findProcardTemByRootId.action',
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
						//var b = true;
									if ($(this).attr('procardStyle') == "自制"
											|| $(this).attr('procardStyle') == "外购") {
										//b = false;
									} else if ($(this).attr('procardStyle') == "总成") {
										totalMaxCount = $(this)
												.attr('maxCount');
									}
									//单交件状态
									var danjiaojian = $(this).attr(
											'danjiaojian');
									if (danjiaojian == null) {
										danjiaojian = "";
									}
									//半成品状态
									var needProcess = $(this).attr(
											'needProcess');
									if (needProcess == "yes") {
										needProcess = "(半成品)";
									} else {
										needProcess = "";
									}
									var bzStatus = $(this).attr('bzStatus');
									if (bzStatus == null || bzStatus == "") {
										bzStatus = "初始";
									}
									var checked=false;
									if (bzStatus != "已批准") {
										bzStatus = "<span style='color:red;margin-right:0px;'>"
												+ bzStatus + "</span>";
										checked=true;		
									}

									var hgstyle = "<span style='font-weight: bolder;font-size: 18px;'>--</span>";
						zNodes.push( {
							id : $(this).attr('id'),
						pId : $(this).attr('fatherId'),
						proStruts : $(this).attr('procardStyle'),
						rootId : $(this).attr('rootId'),
						markId : $(this).attr('markId'),
						danjiaojian : danjiaojian,
						productStyle : $(this).attr('productStyle'),
						belongLayer : $(this).attr('belongLayer'),
						name : "<span style='font-weight: bolder;font-size: 12px;'>"
														+ $(this).attr(
																'procardStyle')
														+ needProcess
														+ "</span>"
														+ hgstyle
														+ $(this)
																.attr('markId')
														+ hgstyle
														+ $(this).attr(
																'proName')
														+ danjiaojian
														+ " "
														+ bzStatus,
						title : $(this).attr('procardStyle') + '--'
								+ $(this).attr('markId') + '--'
								+ $(this).attr('proName') + danjiaojian
								+ needProcess,
							checked : false,
							chkDisabled : checked,
							open : true,
							click : false
						});
					});
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			//父子关联关系(双向关联)
			$.fn.zTree.getZTreeObj("treeDemo").setting.check.chkboxType = {
				"Y" : "",
				"N" : ""
			};
		},
		error : function() {
			alert("服务器异常!");
		}
	});

}
function getFont(treeId, node) {
	return node.font ? node.font : {};
}

//点击回调函数
function onClick(event, treeId, treeNode, clickFlag) {
	$("#showProcess").attr("src",
			"ProcardTemplateAction!findCardTemForShow.action?id="
							+ treeNode.id );
	chageDiv('block');
}
//选中回调函数
function onCheck(e, treeId, treeNode) {
}
var ids="";
//选中函数
function beforeCheck(treeId, treeNode) {
	var b=checkBzStatus(treeNode.id);
	if(b){
		if(ids.length==0){
			ids=""+treeNode.id;
		}else{
		    var idlist = ids.split(",");
		    var add=true;
		    for(var i=idlist.length-1;i>=0;i--){
		    	if((idlist[i]-treeNode.id)==0){
		    		add=false;
		    		idlist[i]=-1;
		    		break;
		    	}
		    }
		    ids="0";
		    for(var j=0;j<idlist.length;j++){
		    	if(idlist[j]>0){
		    		ids=ids+","+idlist[j];
		    	}
		    }
		    if(add){
		    	ids=ids+","+treeNode.id;
		    }
		}
		//alert(treeNode.id);
		//alert(ids);
	  $("#showCardTemplate").show();
	  $("#showCardIframe").attr("src","procardTemplateGyAction_showToUpdatebanben.action?ids="+ids);
	 return true;
	}else{
		return false;
	}
}
//页面内容清空
function showDiv() {
	//隐藏各个添加的详细页面
	$("#showCardTemplate").hide();
	$("#processDiv").hide();
}
function checkBzStatus(id){
	var b=false;
	$.ajax({
		url : 'procardTemplateGyAction_getBzStatus.action',
		type : 'post',
		dataType : 'json',
		async: false,
		data : {
			id : id
		},
		success : function(data) {
			if(data=="已批准"){
				b=true;;
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
	return b;
}
//显示工序详细
<%--function showProcessForSb(id) {--%>
<%--	$("#showProcess").attr("src",--%>
<%--			"ProcardTemplateAction!showProcess.action?id=" + id);--%>
<%--	chageDiv('block');--%>
<%----%>
<%--}--%>


</script>

	</body>
</html>
