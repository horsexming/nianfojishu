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
		url : 'ProcardTemplateAction!findProcardTemByRootId1.action?id=${param.id}',
		singleSelect : true,
		toolbar : '#tb',
		idField : 'id',
		treeField : 'name',
		onClickRow : editNod,
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
			} else {
				myRow.handlers = "";
				$('#tab').treegrid('updateRow', {
					index : myRow.id,
					row : myRow
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
			align : 'center'
		},{
			field : 'maxCount',
			title : '数量',
			align : 'center',
			rowspan : 3,
			width : 65
		},{
			field : 'corrCount',
			title : '权值',
			align : 'center',
			rowspan : 3,
			width : 65
		}, {
			field : 'procardStyle',
			title : '物料属性',
			align : 'center',
			rowspan : 3,
			width : 65
		}, {
			field : 'kgliao',
			title : '供料属性',
			align : 'center',
			rowspan : 3,
			width : 65
		},
			{
			field : 'clType',
			title : '材质',
			align : 'center',
			colspan : 2,
			width : 65
		}, {
			field : 'trademark',
			title : '牌号',
			align : 'center',
			colspan : 2,
			width : 65
		}, {
			field : 'specification',
			title : '规格',
			align : 'center',
			colspan : 2,
			width : 65
		},{
			field : 'yuanName',
			title : '原材料名称 ',
			align : 'center',
			colspan : 2,
			width : 65
		},{
			field : 'number',
			title : '编号 ',
			align : 'center',
			colspan : 2,
			width : 65
		},{
			field : 'luhao',
			title : '炉号 ',
			align : 'center',
			colspan : 2,
			width : 65
		},
			{
			field : 'processNO',
			title : '工序号',
			align : 'center',
			width : 10,
			rowspan : 3,
			editor : 'text',
			width : 65
		}, {
			field : 'processStatus',
			title : '是否并行',
			align : 'center',
			rowspan : 3,
			width : 10,
			width : 65
		} ,{
			field : 'unit',
			title : '单位',
			align : 'center',
			rowspan : 3,
			width : 65
		} ,  {
			field : 'tuhao',
			title : '图号',
			align : 'center',
			rowspan : 3,
			width : 65
		},  {
			field : 'allJiepai',
			title : '总节拍',
			align : 'center',
			rowspan : 3,
			width : 65
		
		},  {
			field : 'biaochu',
			title : '表处',
			align : 'center',
			rowspan : 3,
			width : 65
		},  {
			field : 'clType',
			title : '材料类型',
			align : 'center',
			rowspan : 3,
			width : 65
		
		},  {
			field : 'banBenNumber',
			title : '版本号',
			align : 'center',
			rowspan : 3,
			width : 65
		},  {
			field : 'loadMarkId',
			title : '初始总成',
			align : 'center',
			rowspan : 3,
			width : 65
		} 
		]  ]
	});
<%--	$('#pro').datagrid( {--%>
<%--		url : 'ProcardTemplateAction!packagePro.action?id=${param.id}&code=1',--%>
<%--		singleSelect : true,--%>
<%--		idField : 'id',--%>
<%--		treeField : 'name',--%>
<%--		onClickRow : editNod2,--%>
<%--		columns : [ [ {--%>
<%--			field : 'id',--%>
<%--			title : 'id',--%>
<%--			align : 'left',--%>
<%--			hidden : true--%>
<%--		}, {--%>
<%--			field : 'name',--%>
<%--			title : '名称',--%>
<%--			align : 'left'--%>
<%--		}, {--%>
<%--			field : 'pieceNum',--%>
<%--			title : '件号',--%>
<%--			align : 'center'--%>
<%--		}, {--%>
<%--			field : 'dayOutput',--%>
<%--			title : '日产量',--%>
<%--			align : 'center',--%>
<%--			editor : {--%>
<%--				type : 'numberbox',--%>
<%--				options : {--%>
<%--					precision : 1--%>
<%--				}--%>
<%--			},--%>
<%--			width : 100--%>
<%--		}, {--%>
<%--			field : 'productType',--%>
<%--			title : '型别',--%>
<%--			align : 'right'--%>
<%--		}, {--%>
<%--			field : 'carType',--%>
<%--			title : '车型',--%>
<%--			align : 'right'--%>
<%--		}, {--%>
<%--			field : 'oilPaymentPrice',--%>
<%--			title : '提奖单价',--%>
<%--			align : 'right'--%>
<%--		} ] ]--%>
<%--	});--%>
});
var editIndex = undefined;
var editIndex2 = undefined;

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
		return true;
	}
	if (editIndex != nod.id) {
		endEditing();
		$('#tab').treegrid("beginEdit", nod.id);
		editIndex = nod.id;
	}
}
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

function editNod2(index) {
	if (editIndex2 != index) {
		if (endEditing2()) {
			$('#pro').datagrid('selectRow', index).datagrid('beginEdit', index);
			editIndex2 = index;
		} else {
			$('#pro').datagrid('selectRow', editIndex2);
		}
	}
}
function endEditing2() {
	if (editIndex2 == undefined) {
		return true
	}
	if ($('#pro').datagrid('validateRow', editIndex2)) {
		var ed = $('#pro').datagrid('getEditor', {
			index : editIndex2,
			field : 'productid'
		});

		$('#pro').datagrid('endEdit', editIndex2);
		editIndex2 = undefined;
		return true;
	} else {
		return false;
	}
}

function trial(id) {
	var bol = ergodic();
	jump(id);
}
function jump(id) {
	//处理日产量
	var dayOutputVal;
	if ($('#pro').datagrid('validateRow', editIndex2)) {
		var dayOutput = $('#pro').datagrid('getEditor', {
			index : editIndex2,
			field : 'dayOutput'
		});
		if (dayOutput != null) {
			dayOutputVal = $(dayOutput.target).val();
			window.location = "<%=basePath%>System/SOP/produce/Bonus_showTrial.jsp?id="
					+ id + "&dayOutput=" + dayOutputVal;
		} else {
			window.location = "<%=basePath%>System/SOP/produce/Bonus_showTrial.jsp?id="
					+ id;
		}
	}
}
var index = 0;
function ergodic() {
	if (list) {
		if (index >= list.length) {
			return false;
		}
		var myRow = $('#tab').treegrid("find", list[index]);
		$.ajax( {
			url : "ProcardTemplateAction!saveData.action",
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
						if (el.type == 'PR') {
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
	<body bgcolor="#ffffff">
		<div style="margin-top: 40px;">
		</div>
		<form id="test" action="">
			<table id="pro" title="产品"></table>
			<table id="tab" title="Reports using TreeGrid">
			</table>
		</form>
	</body>
</html>
 