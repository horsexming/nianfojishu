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
		<div align="center">
			<div>
				<form action="ProcardTemplateAction!jingyiJisuan.action"
					method="post">
					<input name="id" type="hidden" value="${param.id}" />
					<br />
					请输入计算数据:
					<br />
					客户需求数:
					<input name="procardTemplate.needNumber" />
					客户需求周期:
					<input name="procardTemplate.needZhoueqi" />
					单班时长:
					<input name="procardTemplate.singleDuration" />
					<input type="submit" value="精益计算">
					<br />
					<br />
				</form>
			</div>
			<div align="left">
				<!-- 显示树形流水卡片模板 -->
				<div style="width: 40%; height: 100%; float: left;" align="left">
					<div style="height: 100%;">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
				<!-- 添加流水卡片模板操作 -->
				<div style="border-left: 1px solid #000000; float: left; width: 58%">
					<div id="showCardTemplate" style="display: none; height: 1000px;">
						<iframe id="showCardIframe" src="" marginwidth="0"
							marginheight="0" hspace="0" vspace="0" frameborder="0"
							scrolling="no"
							style="width: 98%; height: 3500px; margin: 0px; padding: 0px;"></iframe>
					</div>
				</div>
				<div style="clear: both;"></div>

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
	},
	view : {
		fontCss : getFont,
		nameIsHTML : true
	}
};
var moveid = 0;
function beforeDrag(treeId, treeNodes) {
	for ( var i = 0, l = treeNodes.length; i < l; i++) {
		if (treeNodes[i].drag === false) {
			return false;
		}
	}
	return true;
}

function beforeDrop(treeId, treeNodes, targetNode, moveType) {
	var b= targetNode ? targetNode.drop !== false : true;
	if(b){
	}
}
function setCheck() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), isCopy = true, isMove = true, prev = true, inner = true, next = true;
	zTree.setting.edit.drag.isCopy = isCopy;
	zTree.setting.edit.drag.isMove = isMove;

	zTree.setting.edit.drag.prev = prev;
	zTree.setting.edit.drag.inner = inner;
	zTree.setting.edit.drag.next = next;
}

//加载树形数据
$(document).ready(loadTree());
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
						var b=true;
						if($(this).attr('procardStyle')=="自制"
								||$(this).attr('procardStyle')=="外购"){
							b=false;
						}
						var noNum=$(this).attr('noNum');
						if(noNum!=null){
							noNum="<span style='color:red;margin-right:0px;'>"+noNum+"</span>";
						}else{
							noNum="";
						}
						zNodes.push( {
							id : $(this).attr('id'),
							pId : $(this).attr('fatherId'),
							proStruts : $(this).attr('procardStyle'),
							rootId : $(this).attr('rootId'),
							markId : $(this).attr('markId'),
							belongLayer : $(this).attr('belongLayer'),
<%--							name : 'i:'+$(this).attr('id')+"p:"+$(this).attr('fatherId'),--%>
							name : $(this).attr('proName') + ' '
									+ $(this).attr('markId') + ' '
									+ $(this).attr('procardStyle') + ' '+noNum,
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

function getFont(treeId, node) {
			return node.font ? node.font : {};
		}

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
		        url : "ProcardTemplateAction!moveProcardTemplate.action",
		        dataType : "json",
		        data : {
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
	//显示流水卡片明细
	$("#showCardTemplate").show();
	$("#showCardIframe")
			.attr(
					"src",
					"ProcardTemplateAction!findCardTemForShow.action?pageStatus=jy&id="
							+ treeNode.id);
}
//添加组件/原材料流水卡片
function submitForm(formId) {
	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!addProcardTemplate.action",
		dataType : "json",
		data : $("#" + formId).serialize(),
		success : function(msg) {
			if (msg) {
				alert("添加成功!");
				loadTree();
			} else {
				alert("添加失败!");
			}
		}
	});
}

//显示卡片已有工序
function showProcess() {
	$
			.ajax( {
				type : "POST",
				url : "ProcardTemplateAction!findProcessByFkId.action",
				dataType : "json",
				data : {
					id : $("#cardId").val()
				},
				success : function(msg) {
					$("#ProcessTab").empty();
					$("#ProcessTab")
							.append(
									"<tr><th colspan='7'>已有工序</th></tr>"
											+ "<tr><th>工序号</th><th>名称</th><th>总节拍(s)</th><th>生产类型</th>"
											+ "<th>是否并行</th><th>是否首检</th><th>操作</th></tr>");
					var maxProcessNO = 5;//最大工序号
					$(msg)
							.each(
									function() {
										$("#ProcessTab")
												.append(
														'<tr align="center"><td>'
																+ $(this)
																		.attr(
																				'processNO')
																+ '</td><td>'
																+ $(this)
																		.attr(
																				'processName')
																+ '</td><td>'
																+ ($(this)
																		.attr(
																				'opshebeijiepai') + $(
																		this)
																		.attr(
																				'opcaozuojiepai'))
																+ '</td><td>'
																+ $(this)
																		.attr(
																				'productStyle')
																+ '</td><td>'
																+ $(this)
																		.attr(
																				'processStatus')
																+ '</td><td>'
																+ $(this)
																		.attr(
																				'zjStatus')
																+ '</td><td><a href="javascript:;" onclick="showProcessForSb('
																+ $(this).attr(
																		'id')
																+ ');">修改</a>/<a href="javascript:;" onclick="deleteProcess('
																+ $(this).attr(
																		'id')
																+ ');">删除</a></td></tr>');
										var processStatus = $(this).attr(
												'processStatus');
										if (processStatus == "no") {
											$("#parallelId").val("");
										} else {
											$("#parallelId").val(
													$(this).attr('id'));
										}
										maxProcessNO = parseFloat($(this).attr(
												'processNO')) + 5;
									});
					$("#processNO").val(maxProcessNO);//计算下一个工序号是多少，方便填写
					$("#processName").select();
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
			url : "ProcardTemplateAction!addProcessTemplate.action",
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

//删除流水卡片
function delProCard() {
	if (window.confirm('确定要删除本卡片吗?此操作将会删除该流水卡片下属的所有信息!')) {
		$.ajax( {
			type : "POST",
			url : "ProcardTemplateAction!delProcard.action",
			dataType : "json",
			data : {
				id : $("#cardId").val()
			},
			success : function(msg) {
				if (msg) {
					alert("删除成功!");
					showDiv();//页面内容清空
			$("#selectDiv").hide();
			loadTree();//重新加载树形

		} else {
			alert("删除失败!");
		}
	}
		});
	}
}

//精益计算
function jingyiJisuan() {
	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!jingyiJisuan.action",
		dataType : "json",
		data : {
			id : $("#cardId").val()
		},
		success : function(msg) {
			alert(msg);
		}
	});
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
function showProcessForSb(id) {
	$("#showProcess").attr("src",
			"ProcardTemplateAction!showProcess.action?id=" + id);
	chageDiv('block');

}

function deleteProcess(id) {
	if (window.confirm('确定要删除本工序吗?')) {
		$.ajax( {
			type : "POST",
			url : "ProcardTemplateAction!deleteProcess.action",
			dataType : "json",
			data : {
				id : id
			},
			success : function(msg) {
				if (msg.success) {
					alert(msg.message);
					showProcess();//显示该流水卡片已有工序
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

$(function() {
	getUnit("danwei1");
	getUnit("danwei2");
	getUnit("danwei3");
	getUnit("danwei4");
})
</script>

		<script language="javascript">
//ajax获取所有的类似的全称
function getAllNames() {
	$
			.ajax( {
				type : "POST",
				url : "ProcardTemplateAction!getAllNames.action",
				dataType : "json",
				data : {
					markId : $("#shortname").val()
				},
				success : function(data) {
					$("#showAll").empty();
					$(data)
							.each(
									function() {
										var markid = $(this)
												.attr('markId')
												.replace(
														$("#shortname").val(),
														"<font color='red'>"
																+ $(
																		"#shortname")
																		.val()
																+ "</font>");
										$("#showAll")
												.append(
														"<div onmouseover='ondiv(this)' onmouseout='outdiv(this)' onclick='selectdiv(this)' align='left'>"
																+ markid
																+ ","
																+ $(this)
																		.attr(
																				'procardStyle')
																+ "<span>"
																+ $(this)
																		.attr(
																				'markId')
																+ "</span></div>");
									});
				}
			});
}

function copyProcard() {
	if ($("#shortname").val() == "") {
		alert("您选中的模板不存在，添加失败！");
		return;
	}
	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!copyProcard.action",
		dataType : "json",
		data : {
			id : $("#mfatherId").val(),
			markId : $("#shortname").val()
		},
		success : function(data) {
			if (data.success) {
				loadTree();
			}
			if (data.message != null) {
				alert(data.message);

			}

		}
	});
}
function updateProcard() {
	var id = $("#uId").val();
	if (id == "") {
		alert("您选中的模板不存在，添加失败！");
		return;
	}
	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!updateProcard.action",
		dataType : "json",
		data : {
			id : id
		},
		success : function(data) {
			if (data.message != null) {
				alert(data.message);
			}
		}
	});
}
//查询所有工序
function getProcess() {
	$.ajax( {
		type : "POST",
		url : "processGzstoreAction_getProcessGzstoreListAllForSelect.action",
		dataType : "json",
		success : function(msg) {
			if (msg.success) {
				//$("#processName").empty();
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
getProcess();
</script>
	</body>
</html>
