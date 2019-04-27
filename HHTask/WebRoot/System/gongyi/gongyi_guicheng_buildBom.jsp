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
	<body style="height: 800px;">
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
							<span id="title">您正在预览bom信息</span>
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
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div align="center" style="margin-top: 8px;" id="nextDiv">
			</div>
			<hr />
			<div align="left">
				<!-- 显示工艺BOM -->
				<div style="width: 30%; height: 100%; float: left;" align="left">
					<div style="height: 100%;">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
				<!-- 显示工艺规范 -->
				<div style="border-left: 1px solid #000000; float: left; width: 68%">
					<div id="selectDiv" style="display: none;" align="center">
					</div>
					<div id="showCardTemplate" style="display: none; height: 100%;">
					
						<iframe id="showCardIframe" src="" marginwidth="0"
							marginheight="0" hspace="0" vspace="0" frameborder="0"
							scrolling="auto"
							style="width: 98%; height: 800px; margin: 0px; padding: 0px;"></iframe>
					</div>
				</div>
				

			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
			<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/zTree/js/jquery.ztree.excheck-3.5.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/zTree/js/jquery.ztree.exedit-3.5.js">
</script>
		<script type="text/javascript">
//被点击选中的零件
var selectedId="${rootId}";
var rootId="${rootId}";
var selectedPocardStyle="总成";
var selectedMarkId="";

//========================================zTree显示
//自动组装树形结构
var setting = {
	edit : {
		enable : true,
		showRemoveBtn : false,
		showRenameBtn : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		onClick : onClick,
		beforeDrag : beforeDrag,
		beforeDrop : beforeDrop,
		onDrag : onDrag
	}
};

var moveId=0;
function onDrag(event, treeId, treeNodes){
	moveId=treeNodes[0].id;
}
function beforeDrag(treeId, treeNodes) {
			for (var i=0,l=treeNodes.length; i<l; i++) {
				if (treeNodes[i].drag === false) {
					return false;
				}
			}
			return true;
}
function beforeDrop(treeId, treeNodes, targetNode, moveType) {
			if(targetNode.id==null){
				return false;
			}
			var moveok= targetNode ? targetNode.drop !== false : true;
			if(!moveok){
				return moveok;
			}
			if(moveok){
				//alert(moveId);
				$.ajax( {
		        type : "POST",
		        url : "gongyiGuichengAction!moveBuildBomNode.action",
		        dataType : "json",
		        data : {
		            rootId:rootId,
					moveId:moveId,
					targetId:targetNode.id
		        },
		        success : function(msg) {
		        	 alert(msg.message);
		        	 loadTree();
			         return msg.success;
		             }
	             });
			}
}


//加载树形数据
$(document).ready(loadTree());
//生成
function loadTree() {
	$.ajax( {
		url : 'gongyiGuichengAction!loadBulidBomTree.action',
		type : 'post',
		dataType : 'json',
		data : {
			rootId : '${rootId}'
		},
		cache : true,
		success : function(doc) {
			var zNodes = [];
			$(doc).each(function() {
			            var b=true;
						if($(this).attr('procardStyle')=="自制"
								||$(this).attr('procardStyle')=="外购"){
							b=false;
						}
							zNodes.push( {
							id : $(this).attr('id'),
							pId : $(this).attr('fatherId'),
							proStruts : $(this).attr('procardStyle'),
							rootId : $(this).attr('rootId'),
							markId : $(this).attr('jianNumb'),
							belongLayer : $(this).attr('belongLayer'),
							name : $(this).attr('jianName') + ' '
									+ $(this).attr('jianNumb') + ' '
									+ $(this).attr('procardStyle'),
							click : false,
							drop : b,
							open : true
						});
					});
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		},
		error : function() {
			alert("服务器异常!");
		}
	});

}

var oldObj;
var oldObj2;
//切换添加类型
function chageModule(obj, obj2) {
	if (oldObj != null) {
		$("#module1_" + oldObj2).hide();
	}
	$("#module1_" + obj2).show('slow');
	oldObj = obj;
	oldObj2 = obj2;
}

//点击回调函数
function onClick(event, treeId, treeNode, clickFlag) {
	var proStruts = treeNode.proStruts;// 卡片状态(总成，零组件，原材料)
	selectedId=treeNode.id;
	selectedPocardStyle=treeNode.proStruts;
	selectedMarkId=treeNode.markId;
	showDiv();//页面内容清空
	//显示添加选项的页面
	$("#selectDiv").show();
	//关闭已打开的功能Div
	if (oldObj != null) {
		$("#module1_" + oldObj2).hide();
	}
	//显示工艺规程
	$("#showCardTemplate").show();
	$("#showCardIframe").attr(
			"src",
			"gongyiGuichengAction!findGongyiGuichengList.action");
	/*
	$.ajax({
		type : "POST",
		url : "gongyiGuichengAction!getGongyiGuichengByjianNumb.action",
		dataType : "json",
		data : {
		 'gongyiGuicheng.jianNumb' : treeNode.markId,
		 rootId : treeNode.rootId
		},
		success : function(data){
			if(data.success){
				alert
			}
		}
	});
	window.open("gongyiGuichengAction!getGongyiGuichengByjianNumb.action?gongyiGuicheng.jianNumb="
					+ treeNode.markId+"&rootId="+treeNode.rootId,"_blank");
	*/
}

//页面内容清空
function showDiv() {
	//隐藏各个添加的详细页面
	$("#showCardTemplate").hide();
}





</script>
	</body>
</html>
