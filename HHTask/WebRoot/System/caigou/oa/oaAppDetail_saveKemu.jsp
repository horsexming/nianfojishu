<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
<%
	String path = request.getContextPath();
	String getRealPath("/"); = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@include file="/util/sonHead.jsp"%>
				<style type="text/css">
body {
	text-align: center;
}
.ztree li a {
	color: #fff;
}
</style>
		
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					${companyInfo.name}采购科目添加
				</h3>
				<div style="font-size: 13; color: red; font-weight: bold;">
					${message}
				</div>
				<form action="oaAppDetailAction!Addoadetail.action" method="post"
							enctype="multipart/form-data" onsubmit="return checktype()">
							选择导入文件:
							<input type="file" name="oadetailFile">
							<a href="<%=basePath%>/upload/file/download/OaAppDetail.xls">导入模版下载</a>
							<a href="FileViewAction.action?FilePath=/upload/file/download/OaAppDetail.xls&Refresh=true">/预览</a>
							<input type="submit" value="批量导入" id="sub">
						</form>
				<form action="oaAppDetailAction!saveKemu.action" method="post"	onsubmit="return check()">
					<table width="85%" class="table" >
						<tbody>
							<tr>
								<th >物品类别</th>
							<td>
								<div class="zTreeDemoBackground left">
								<ul class="list">
									<li class="title">
										<input id="wgType0" type="text" name="oadetail.detailChildClass"  
											style="width: 120px;"  />
											<input id="categoryId0" type="hidden"  onchange="getfirstNo('0')"/>
										<a id="menuBtn" href="#" color="#fff" onclick="showMenu('0'); return false;">选择</a>
									</li>
								</ul>
							</div>
							<div id="menuContent0" class="menuContent"
								style="display: none; position: absolute;">
								<ul id="treeDemo0" class="ztree"
									style="margin-top: 0; width: 160px;"></ul>
							</div>
							</td>
							<th >
									物品名称<br/>Item Name
								</th>
								<td>
									<input type="text" name="oadetail.detailAppName" style="width: 130px;" id="detailAppName">
									<input type="hidden" style="width: 130px;" id="gztypename0">
									
								</td>
							</tr>
							<tr>
								<th>
									物料编码<br/>Material coding
								</th>
								<td>
									<input	type="text" name="wlcode" id="wlcode" readonly="readonly">(<font color="red">自动生成</font>)
								</td>
								<th>
									物品规格<br/>Item Specifications
								</th>
								<td>
									<input type="text" name="oadetail.detailFormat" style="width: 130px;">
								</td>

							</tr>
							<tr>
								<th>
									计量单位<br/>Units of measurement units
								</th>
								<td>
<%--								<input type="text" name="oadetail.detailUnit" style="width: 130px;">--%>
								<select name="oadetail.detailUnit"  id="danwei" 	>
								</select>
								</td>
								<td></td>
								<td></td>
							</tr>
							
								<tr>
									<td colspan="4" style="font-size: 12px;">
									<input type="submit" value="添加(Add)" class="input">&nbsp;
									<input type="reset" value="取消(Cancel)" class="input">&nbsp;
									</td>
									</tr>
								<tr>
									<td colspan="4" style="font-size: 12px;">
										备注：
										<br>
										1、采购申请前提是有部门预算申请，且预算申请可用余额大于申购金额。
										<br/>2、手动添加是单条提交申购明细，批量上传为上传EXCEL标准格式的申报明细。
										<br/>3、批量上传的前提是要先选择预算月份和预算科目。
										<br/>4、处理后统一跳转到个人申报历史记录里。
										<br/>5、审批通过后采购进行打印
										<br/>6、采购入库后预算完成，入库单价和数量均不可超过申购的单价和数量。
										<br/>
										Notes:<br/>
 1, provided there is a purchase requisition department budget request, and the budget request is greater than the available balance of the purchase amount. 
<br/>2, manually add a single submission subscription details, bulk upload upload EXCEL standard format for reporting details. 
<br/>3, bulk upload of the prerequisite is to select the budget month and the budget items. 
<br/>4, post-processing unified Jump to declare personal history log. 
<br/>5, after approval by the Procurement print 
<br/>6, after the completion of the procurement budget warehousing, storage unit price and quantity may not exceed the purchase price and quantity.
									</td>
								</tr>
								<tbody>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
	</body>
	<script type="text/javascript">
	$(function(){
			getUnit("danwei");
		})
	//下拉树开始
var mfzTree;
var addzTree;
var delzTree;
var updatezTree;

var id;
var pId;
var name;
var size = 0;
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
//读取树形数据
$(document).ready(function() {
	//parent.mfzTree();
});
var zNodes = [];
parent.mfzTree = function() {
	$.ajax( {
		url : 'CategoryAction_findcyListByrootId.action',
		type : 'POST',
		data : {
			status : '编码',
			tag:'fl'
		},
		dataType : 'json',
		async : false,
		success : function(doc) {
			$(doc).each(function() {
				zNodes.push( {
					id : $(this).attr('id'),
					pId : $(this).attr('fatherId'),
					name : $(this).attr('name'),
					target : "main",
					click : false
				});

			});
			$.fn.zTree.init($("#treeDemo"+size), setting, zNodes);
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"+size);
			zTree.expandAll(true);
		},
		error : function() {
			alert("服务器异常!");
		}
	});
};
function beforeClick(treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);

	return true;
}

function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"+size), nodes = zTree
			.getSelectedNodes(), v = "";
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	var id = '';
	for ( var i = 0, l = nodes.length; i < l; i++) {
		$.ajax( {
		url : 'CategoryAction_islow.action',
		type : 'post',
		data : {
			id : nodes[i].id
		},
		dataType : 'json',
		cache : true,
		async : false,
		success : function(doc) {
			if(doc){
				v = nodes[i].name;
				id = nodes[i].id;
			}else{
				alert('请选择最底层');
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
	}
	//if (v.length > 0 ) v = v.substring(0, v.length-1); 
	cityObj = $("#wgType"+size).val(v);
	$("#categoryId"+size).val(id);
	getfirstNo('0');
}

function showMenu(num) {
	size = num;
	zNodes = [];
	parent.mfzTree();
	var cityObj = $("#wgType"+num);
	var cityOffset = $("#wgType"+num).offset();
	$("#menuContent"+num).css( {
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
}
function hideMenu(num) {
	$("#menuContent"+num).fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
	size = '';
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent"+size || $(
			event.target).parents("#menuContent"+size).length > 0)) {
		hideMenu(size);
	}
}
//下拉树结束


function getfirstNo(num){
	var categoryId  = $("#categoryId"+num).val();
	var gztypename = $("#gztypename"+num).val();
	var sqNum = 1;
	if(categoryId!='' && gztypename!='' && sqNum!= ''){
			$.ajax( {
		url : 'ChartNOSQAction_getfirstNo.action',
		type : 'POST',
		data : {
			'cq.categoryId':categoryId,
			'cq.gztype':gztypename,
			'cq.sqNum':sqNum
		},
		dataType : 'json',
		success : function(data) {
			if(data!=null){
				var firstNo = data.firstNo;
				$("#wlcode").val(firstNo);
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
	}
}

$(function(){
	$.ajax({
		url : 'ChartNOSQAction_getgzTypeList.action',
		type : 'POST',
		data : {
			status:'fl'
		},
		dataType : 'json',
		async : false,
		success : function(doc) {
			if(doc!=null){
				$(doc).each(function(){
					$("#gztypename0").val(this.type);
				});
			}
		},
	})
	
})

function check(){
	var wlcode = $("#wlcode").val();
	var detailAppName = $("#detailAppName").val();
	if(wlcode == ''){
		alert("请先输入物料编码!");
		$("#wlcode").focus();
		return false;
	}else if(detailAppName = ''){
		alert("请填写物品名称!");
		$("#detailAppName").focus();
		return false;
	}
	
	
}

</script>
</html>
