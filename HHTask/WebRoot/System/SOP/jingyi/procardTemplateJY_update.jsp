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

		<title>精益模板展示</title>

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
		url : 'procardTemplateJYAction_initTrial.action?id=${param.id}',
		singleSelect : true,
		toolbar : '#tb',
		idField : 'id',
		treeField : 'name',
		onClickRow : editNod,
		onAfterEdit : function sendData(myRow, changes) {
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
			title : '工序号',
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
		},{
			field : 'handlers',
			title : '操作',
			align : 'center',
			rowspan : 3,
			width : 65
		}], [ {
			field : 'oPLabourBeat',
			title : '人工节拍',
			align : 'center',
			width : 65
		}, {
			field : 'oPEquipmentBeat',
			title : '设备节拍',
			align : 'center',
			width : 65
		}, {
			field : 'pRLabourBeat',
			title : '人工节拍',
			align : 'center',
			width : 65
		}, {
			field : 'pRPrepareIndex',
			title : '准备次数',
			align : 'center',
			width : 65
		}, ] ]
	});
	$('#pro').datagrid( {
		url : 'procardTemplateJYAction_packagePro.action?id=${param.id}&code=1',
		singleSelect : true,
		idField : 'id',
		treeField : 'name',
		onClickRow : editNod2,
		columns : [ [ {
			field : 'id',
			title : 'id',
			align : 'left',
			hidden : true
		}, {
			field : 'name',
			title : '名称',
			align : 'left'
		}, {
			field : 'pieceNum',
			title : '件号',
			align : 'center'
		}, {
			field : 'productType',
			title : '型别',
			align : 'right'
		}, {
			field : 'carType',
			title : '车型',
			align : 'right'
		}
		] ]
	});
});
var editIndex = undefined;
var editIndex2 = undefined;

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

</script>
	</head>
	<body bgcolor="#ffffff">
		<div style="margin-top: 40px;"><input type="button" value="精益计算"  onclick="jingyijisuan(${param.id})">
		<input type="button" value="回归模板 " onclick="backToProcardTemplate(${param.id})">
		</div>
		<form id="test" action="">
			<table id="pro" title="产品"></table>
			<table id="tab" title="Reports using TreeGrid">
			</table>
		</form>
		<div id="tb" style="height: auto">
		工序显示
			<!--<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save',plain:true" onclick="accept()">填写完毕</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-search',plain:true"
				onclick="trial(${param.id})">试算</a>
			 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="send()">Test</a> -->
		</div>
	</body>
	<script type="text/javascript">
	function jingyijisuan(id){
		window.location.href="procardTemplateJYAction_jinyijisuan.action?id="+id;
	}
	function backToProcardTemplate(id){
		alert("回归模板需要较长时间请耐心等待！");
		$.ajax({
			type : "POST",
			url : "procardTemplateJYAction_backtoProcardTemplate.action",
			dataType : "json",
			data : {
			  id : id
			},
		    success : function(data){
			 if(data.success){
				 alert("成功回归模板");
			 }else{
				 alert(data.message);
			 }
		    }
		
		});
	}
	</script>
</html>
