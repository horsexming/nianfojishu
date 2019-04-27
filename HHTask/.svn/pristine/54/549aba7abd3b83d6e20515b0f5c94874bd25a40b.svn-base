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
							<span id="title">您正在查看工序信息</span>
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
			(
			<b>${projectTime.className}录入填报说明</b>: 填报部门:
			<b>${projectTime.dept}</b> 填报人员:
			<b>${projectTime.userName}</b> 截止时间:
			<b>${projectTime.provisionTime}</b>
			<a
<%--				href="DownAction?fileName=${quotedPrice.fileName}&directory=/upload/file/project/">查看附件</a>)--%>
				href="FileViewAction.action?FilePath=/upload/file/project/${quotedPrice.fileName}">查看附件</a>)

			<div align="left">
				<!-- 显示工艺BOM -->
				<div style="width: 40%; height: 100%; float: left;" align="left">
					<div style="height: 100%;">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
				<!-- 添加工艺BOM -->
				<div style="border-left: 1px solid #000000; float: left; width: 58%">
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
//加载树形数据
$(document).ready(loadTree());
//生成
function loadTree() {
	$.ajax( {
		url : 'QuotedPrice_findAllForTree.action',
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
						if($(this).attr('id')==$(this).attr('rootId')){
							//点击下一步时用到的rootid
							$("#nextDiv").empty();
							 if($(this).attr('status')=="项目启动中"){
								$("#nextDiv").append("<font color='red' size='8px'>项目启动中</font>(<a onclick='cratesopBom()'><font color='red' size='8px'>创建试制BOM</font></a>)");
							}else if($(this).attr('status')=="项目跟踪"){
								$("#nextDiv").append("<font color='red' size='8px'>项目跟踪</font>(<a onclick='cratesopBom()'><font color='red' size='8px'>创建试制BOM</font></a>)");
							}
						}
						zNodes.push( {
							id : $(this).attr('id'),
							pId : $(this).attr('fatherId'),
							proStruts : $(this).attr('procardStyle'),
							rootId : $(this).attr('rootId'),
							belongLayer : $(this).attr('belongLayer'),
							xingbie : $(this).attr('xingbie'),
							name : $(this).attr('proName') + ' '
									+ $(this).attr('markId') + ' '
									+ $(this).attr('procardStyle'),
							click : false,
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
	//工序赋值
	$("#cardId").val(treeNode.id);
	showDiv();//页面内容清空
	//显示添加选项的页面
	$("#selectDiv").show();
	//工序赋值
	$("#gxingbie").val(treeNode.xingbie);
	//关闭已打开的功能Div
	if (oldObj != null) {
		$("#module1_" + oldObj2).hide();
	}
	//显示流水卡片明细
	$("#showCardTemplate").show();
	$("#showCardIframe").attr(
			"src",
			"QuotedPrice_findQpDetailForShow.action?pageStatus=${pageStatus}&id="
					+ treeNode.id);
}

//页面内容清空
function showDiv() {
	//清空工序table
	$("#ProcessTab").empty();
	//隐藏各个添加的详细页面
	$("#showCardTemplate").hide();
	$("#processDiv").hide();
	$("#lingDiv").hide();
	$("#yuanDiv").hide();
}

//显示工序详细
function updateProcess(id) {
	$("#showProcess").attr("src", "QuotedPrice_showProcess.action?id=" + id);
	chageDiv('block');

}


//查询所有工序
function getProcess() {
	$.ajax( {
		type : "POST",
		url : "",
		dataType : "json",
		success : function(msg) {
			if (msg.success) {
				$("#processName").empty();
				$("#processName").append("<option value=''></option>");
				$.each(msg.data, function(i, n) {
					$("#processName").append(
							"<option value='" + n.processName + "'>"
									+ n.processName + "</option>");
				});
			} else {
				alert(msg.message);
			}
		}
	});
}

//创建试制bom
function cratesopBom() {
	var b = window.confirm("您将把bom打入试制bom是否继续？");
	if (b) {
		var rootid = '${param.id}';
		$.ajax( {
			type : "POST",
			url : "QuotedPrice_buildBomtosop.action",
			dataType : "json",
			data : {
				'quotedPrice.rootId' : rootid
			},
			success : function(msg) {
				alert(msg.message);
			}
		});
	}
}

</script>
	</body>
</html>
