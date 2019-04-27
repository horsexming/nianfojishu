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
		<div id="gongneng">
			</div>
			
			<div align="left">
				<!-- 显示工艺BOM -->
				<div style="width: 40%; height: 100%; float: left;" align="left">
					<div style="height: 100%;">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
				<!-- 添加工艺BOM -->
					<div id="showCardTemplate" style=" height: 100%;border-left: 1px solid #000000; float: left; width: 58%"">
						<iframe id="showCardIframe" src="" marginwidth="0"
							marginheight="0" hspace="0" vspace="0" frameborder="0"
							scrolling="no"
							style="width: 98%; height: 600px; margin: 0px; padding: 0px;"></iframe>
					</div>
				</div>
				<div style="clear: both;"></div>

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


//点击回调函数
function onClick(event, treeId, treeNode, clickFlag) {
	//显示流水卡片明细
	$("#showCardTemplate").show();
	$("#showCardIframe").attr(
			"src",
			"QuotedPrice_findQpDetailForCost.action?pageStatus=${pageStatus}&id="
					+ treeNode.id);
}
//添加组件/原材料流水卡片


//删除工艺规范

//页面内容清空


</script>
	</body>
</html>
