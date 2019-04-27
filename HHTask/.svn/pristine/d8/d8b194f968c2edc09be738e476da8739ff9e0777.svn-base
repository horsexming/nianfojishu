<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'interiorMentioningAwardPrice.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css"
			href="javascript/jquery-easyui-1.3.1/themes/gray/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="javascript/jquery-easyui-1.3.1/themes/icon.css">
		<script type="text/javascript"
			src="javascript/jquery-easyui-1.3.1/jquery-1.8.0.min.js">
</script>
		<script type="text/javascript"
			src="javascript/jquery-easyui-1.3.1/jquery.easyui.min.js">
</script>
		<script>
var list = new Array();
$(function() {
	$('#tab').treegrid( {
		url : 'productPriceAction!initTrial.action?id=${param.id}',
		singleSelect : true,
		toolbar : '#tb',
		idField : 'id',
		treeField : 'name',
		onDblClickRow : editNod,
		onAfterEdit : function sendData(myRow, changes) {
			list.push(myRow.id);
			if (myRow.jobNum != "") {
				$.ajax( {
					url : "productPriceAction!getNameByNum.action",
					type : "post",
					dataType : 'json',
					data : {
						code : myRow.jobNum
					},
					success : function(d) {
						if (d) {
							myRow.handlers = d.name;
							$('#tab').treegrid('updateRow', {
								index : myRow.id,
								row : myRow
							});
						}
					}
				});
			}
		},
		fitColumns : true,
		frozenColumns : [ [ {
			field : 'name',
			title : '名称',
			align : 'left',
			rowspan : 3,
			width : 200
		} ] ],
		columns : [ [ {
			field : 'pieceNum',
			title : '件号',
			align : 'right',
			rowspan : 3,
			width : 65
		}, {
			field : 'stationNum',
			title : '工位号',
			align : 'center',
			rowspan : 3,
			width : 65
		}, {
			field : 'equipmentNum',
			title : '设备号',
			align : 'center',
			rowspan : 3,
			width : 65
		}, {
			field : '',
			title : '操作过程',
			align : 'right',
			colspan : 2,
			width : 175
		}, {
			field : '',
			title : '准备过程',
			align : 'right',
			colspan : 2,
			width : 175
		}, {
			field : 'jobNum',
			title : '工号',
			align : 'center',
			width : 10,
			rowspan : 3,
			editor : 'text',
			width : 65
		}, {
			field : 'handlers',
			title : '操作者',
			align : 'center',
			rowspan : 3,
			width : 10,
			width : 65
		}, {
			field : 'processUnitPrice',
			title : '工序单价',
			align : 'left',
			rowspan : 3,
			width : 65
		} ], [ {
			field : 'oPLabourBeat',
			title : '人工节拍',
			align : 'center',
			editor : {
				type : 'numberbox',
				options : {
					precision : 1
				}
			},
			width : 65
		}, {
			field : 'oPEquipmentBeat',
			title : '设备节拍',
			align : 'center',
			editor : {
				type : 'numberbox',
				options : {
					precision : 1
				}
			},
			width : 65
		}, {
			field : 'pRLabourBeat',
			title : '人工节拍',
			align : 'center',
			editor : {
				type : 'numberbox',
				options : {
					precision : 1
				}
			},
			width : 65
		}, {
			field : 'pRPrepareIndex',
			title : '准备次数',
			align : 'center',
			editor : {
				type : 'numberbox',
				options : {
					precision : 1
				}
			},
			width : 65
		}, ] ]
	});
	$('#pro').datagrid( {
		url : 'productPriceAction!packagePro.action?id=${param.id}&code=1',
		fitColumns : true,
		columns : [ [ {
			field : 'name',
			title : '名称',
			align : 'left'
		}, {
			field : 'pieceNum',
			title : '件号',
			align : 'right'
		}, {
			field : 'dayOutput',
			title : '日产量',
			align : 'right'
		}, {
			field : 'productType',
			title : '型别',
			align : 'right'
		}, {
			field : 'carType',
			title : '车型',
			align : 'right'
		}, {
			field : 'oilPaymentPrice',
			title : '提奖单价',
			align : 'right'
		} ] ]
	});
});
var editIndex = undefined;
function endEditing() {
	if (editIndex == undefined) {
		return true
	}
	if ($('#tab').treegrid('validateRow', editIndex)) {
		$('#tab').treegrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}
function onClickRow() {
	nod = $('#tab').treegrid('getSelected');
	if (editIndex != nod.id) {
		if (endEditing()) {
			$('#tab').treegrid("beginEdit", nod.id);
			editIndex = nod.id;
		}
	}
}
function accept() {
	if (endEditing()) {
		$('#tab').treegrid('acceptChanges');
	}
}
function editNod() {
	endEditing();
	nod = $('#tab').treegrid('getSelected');
	if (nod.type != "PR") {
		alert("请选择工序填写!");
		return true;
	}
	if (editIndex != nod.id) {
		endEditing();
		$('#tab').treegrid("beginEdit", nod.id);
		editIndex = nod.id;
	}
}

function trial(id) {
	var bol = ergodic();
	jump(id);
}
function jump(id) {
	window.location = "<%=basePath%>System/renshi/tiPrice/showTrial.jsp?id="
			+ id;
}
var index = 0;
function ergodic() {
	if (list) {
		if (index >= list.length) {
			return false;
		}
		var myRow = $('#tab').treegrid("find", list[index]);
		$.ajax( {
			url : "productPriceAction!saveData.action",
			type : "post",
			async : false,
			data : {
				id : myRow.realId,
				jobNum : myRow.jobNum,
				OPLabourBeat : myRow.oPLabourBeat,
				OPEquipmentBeat : myRow.oPEquipmentBeat,
				PRLabourBeat : myRow.pRLabourBeat,
				PRPrepareTIme : myRow.pRPrepareIndex
			}
		});
		index++;
		ergodic();
	} else {
		return false;
	}
}
function test() {
	var a = $('#tab').treegrid('getData');
	$.each(a, function(index, element) {
		if (element) {
			$.each(element.children, function(i, ele) {
				if (ele) {
					$.each(ele.children, function(ind, el) {
						if (el.type == 'PR'){
							
						}
					});
				}
			});
		}
	});
}
function send() {
	if (list) {
		for ( var i = 0; i < list.length; i++) {
			var myRow = $('#tab').treegrid("find", list[i]);
			$.ajax( {
				url : "productPriceAction!saveData.action",
				type : "post",
				data : {
					id : myRow.realId,
					jobNum : myRow.jobNum,
					OPLabourBeat : myRow.oPLabourBeat,
					OPEquipmentBeat : myRow.oPEquipmentBeat,
					PRLabourBeat : myRow.pRLabourBeat,
					PRPrepareTIme : myRow.pRPrepareIndex
				}
			});
		}
	}
}
</script>
	</head>

	<body>
		<table id="pro" title="产品"></table>
		<table id="tab" title="Reports using TreeGrid">
		</table>
		<div id="tb" style="height: auto">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save',plain:true" onclick="accept()">填写完毕</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-search',plain:true"
				onclick="trial(${param.id})">试算</a>
			<!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="send()">Test</a> -->
		</div>
	</body>
</html>
