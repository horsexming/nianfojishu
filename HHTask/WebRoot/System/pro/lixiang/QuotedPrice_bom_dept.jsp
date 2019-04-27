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
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			<div align="center" style="margin-top: 8px;">
				当您的数据全部录入完成后请点击————>
				<a onclick="submitBom()"><font color="red" size="8px">提交</font>
				</a>
			</div>
			<hr />
			(
			<b>${projectTime.className} 录入填报说明</b>:
			填报部门:${projectTime.dept}填报人员:${projectTime.userName}
			截止时间:${projectTime.provisionTime}
			<font color="red">BOM录入人员:${bomprojectTime.userName}</font>)

			<div align="left">
				<!-- 显示工艺BOM -->
				<div style="width: 40%; height: 100%; float: left;" align="left">
					<div style="height: 100%;">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
				<!-- 各部门填报工艺BOM -->
				<div id="addBom"
					style="border-left: 1px solid #000000; float: left; width: 58%">
					<div>
						<iframe id="updateQpIframe" src="" marginwidth="0"
							marginheight="0" hspace="0" vspace="0" frameborder="0"
							scrolling="auto"
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
	var proStruts = treeNode.proStruts;// 卡片状态(总成，零组件，原材料)
	var pageStatus = "${pageStatus}";// 卡片状态(总成，零组件，原材料)
	//显示各个部门填报明细
	//显示流水卡片明细
	$("#updateQpIframe").show();
	$("#updateQpIframe").attr(
			"src",
			"QuotedPrice_findQpDetailForShow.action?pageStatus=${pageStatus}&id="
					+ treeNode.id);
	$("#addBom").show();
}
//添加组件/原材料流水卡片
function submitForm(formId) {
	$.ajax( {
		type : "POST",
		url : "QuotedPrice_addQuotedPriceTree.action",
		dataType : "json",
		data : $("#" + formId).serialize(),
		success : function(msg) {
			if (msg) {
				alert("添加成功!");
				loadTree();//重新加载树形
	} else {
		alert("添加失败!");
	}
}
	});
}

//添加工序
function submitForm2(formId) {
	if ($("#processName").val() == "") {
		alert("请填写工序名称!");
		$("#processName").select();
	} else {
		if ($("#processStatus").val() == "no") {
			$("#parallelId").val("");
		}
		$.ajax( {
			type : "POST",
			url : "QuotedPrice_addQpInfor.action",
			dataType : "json",
			data : $("#" + formId).serialize(),
			success : function(msg) {
				if (msg) {
					alert("添加成功!");
					showProcess();
					$("#processNO").val(parseFloat($("#processNO").val()) + 5);
					$("#processName").val("");
					$("#processName").select();

				} else {
					alert("添加失败!");
				}
			}
		});
	}
}

//删除 
function delProCard() {
	if (window.confirm('确定要删除件号吗?此操作将会删除该件号下的所有信息!')) {
		$.ajax( {
			type : "POST",
			url : "QuotedPrice_delQuotedPriceTree.action",
			dataType : "json",
			data : {
				id : $("#cardId").val()
			},
			success : function(msg) {
				if (msg.success) {
					alert(msg.message);
					showDiv();
					$("#selectDiv").hide();
					loadTree();
				} else {
					alert(msg.message);
				}
			}
		});
	}
}

//显示工序详细
function showProcessForSb(id) {
	$("#showProcess").attr("src", "QuotedPrice_showProcess.action?id=" + id);
	chageDiv('block');

}

function deleteProcess(id) {
	if (window.confirm('确定要删除本工序吗?')) {
		$.ajax( {
			type : "POST",
			url : "QuotedPrice_delQpInfor.action",
			dataType : "json",
			data : {
				id : id
			},
			success : function(msg) {
				if (msg.success) {
					alert(msg.message);
					showProcess();
					$("#processNO").val(parseFloat($("#processNO").val()) + 5);
					$("#processName").val("");
					$("#processName").select();
				} else {
					alert(msg.message);
				}
			}
		});
	}
}
function submitBom() {
	if (window.confirm('本操作将完成本次报价!确定数据都已经录入完毕?')) {
		var id = "${id}";
		var pageStatus = "${pageStatus}";
		$.ajax( {
			type : "POST",
			url : "ProjectManage_updateProTimeForFinal.action",
			dataType : "json",
			data : {
				id : id,
				pageStatus : pageStatus
			},
			success : function(data) {
			 if(data.success){
			   alert("提交成功！");
			   window.location.href="QuotedPrice_findQPByCondition.action?quotedPrice.belongLayer=0&pageStatus="+pageStatus;
			 }else{
			  alert(data);
			 }
			}
		});
	}
}
</script>
	</body>
</html>
