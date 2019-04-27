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
			<style type="text/css">
.ztree li a {
	color: #fff;
}
</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<h2>添加供应商产品</h2>
			<font color="red" id="ziti">${errorMessage}</font>
				<form action="markIdAction!addgysjiepai.action" method="post" onsubmit="return check()">
					<table>
						<tr>
							<th>件号</th>
							<td>
								<input type="text" name="gysMarkIdjiepai.markId" id="markId" onchange="getAllNames(this)"/>
								<font color="red">*</font>
							</td>
							<th>名称</th>
							<td>
								<input type="text" name="gysMarkIdjiepai.proName" id="proName"/><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<th>卡片类型</th>
							<td>
								<input type="text" name="gysMarkIdjiepai.procardStyle" id="procardStyle" value="外购">
							</td>
							<th>产品类型</th>
							<td>
								<input type="radio" value="试制" name="gysMarkIdjiepai.productStyle"/>试制
								<input type="radio" value="批产" name="gysMarkIdjiepai.productStyle" checked="checked"/>批产
							</td>
						</tr>
						<tr>
							<th valign="middle">供应商</th>
							<td valign="middle">
									<select name="gys" id="gys_3" class="cxselect">
									<option selected="selected" value="">
										选择供应商
									</option>
									<s:iterator id="zh" value="zhuserList">
										<option value="${zh.usercode}">
											${zh.usercode}_${zh.name}
										</option>
									</s:iterator>
								</select>
							</td>
							<th valign="middle">物料类别</th>
							<td valign="middle">
								<div class="zTreeDemoBackground left">
									<ul class="list">
										<li class="title">
											<input id="wgType" type="text" readonly="readonly" value=""
												style="width: 120px;" name="gysMarkIdjiepai.wgType" />
											<a id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a><font color="red">*</font>
										</li>
									</ul>
								</div>
								<div id="menuContent" class="menuContent"
									style="display: none; position: absolute;">
									<ul id="treeDemo" class="ztree"
										style="margin-top: 0; width: 160px;"></ul>
								</div>
							</td>
						</tr>
						<tr>
							<th>配额</th>
							<td>
								<input type="text" name="gysMarkIdjiepai.cgbl" id="cgbl" onchange="numyanzheng(this,'zhengshu');daxiao(this)"/>
								<font color="red">*</font>
							</td>
							<th>供料属性</th>
							<td>
								<select name="gysMarkIdjiepai.kgliao" id="kgliao">
											<option value="TK">
												自购(TK)
											</option>
											<option value="TK AVL">
												指定供应商(TK AVL)
											</option>
											<option value="CS">
												客供(CS)
											</option>
											<option value="TK Price">
												完全指定(TK Price)
											</option>
								</select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<th>
								版本号
							</th>
							<td>
								<input type="text" name="gysMarkIdjiepai.banBenNumber" id="banBenNumber"/>
							</td>
							<th>
								备注
							</th>
							<td>
								<input type="text" name="gysMarkIdjiepai.remark" id="remark"/>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
							
								<input type="submit" value="提交" class="input" id="sub" />
							</td>
						</tr>
					</table>
				</form>
				<input type="hidden" id="rebeack" value='${errorMessage}' />
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">

$(document).ready(function() {
	var rebeack = $("#rebeack").val();
	if (rebeack == "添加成功!") {
		alert(rebeack);
		parent.chageDiv('none');
		parent.window.location.reload();
	}
})


function check(){
	var markId = $("#markId").val();
	var proName = $("#proName").val();
	var gys = $("#gys").val();
	var wgType = $("#wgType").val();
	var cgbl = $("#cgbl").val();
	var kgliao = $("#kgliao").val();
	if(markId == ""){
		$("#ziti").html("请输入件号");
		$("#markId").focus();
		return false;
	}else if(proName == ""){
		$("#ziti").html("请输入名称");
		$("#proName").focus();
		return false;
	}else if(gys == ""){
		$("#ziti").html("请选择供应商");
		$("#gys").focus();
		return false;
	}else if(wgType == ""){
		$("#ziti").html("请选择物料类别");
		$("#wgType").focus();
		return false;
	}else if (cgbl == ""){
		$("#ziti").html("请输入配额");
		$("#cgbl").focus();
		return false;
	}
	document.getElementById("sub").disabled="disabled";
}

function daxiao(obj){
	if(obj!=null && obj.value!=""){
		var num = parseInt(obj.value);
		if(num<0 || num>100){
			obj.value = 0;
			$("#ziti").html("请输入0~100之间的整数");
		}
	}
}


//树形下来框
var mfzTree;
var addzTree;
var delzTree;
var updatezTree;

var id;
var pId;
var name;
var setting = { 
view: { 
dblClickExpand: false 
}, 
data: { 
simpleData: { 
enable: true 
} 
}, 
callback: { 
beforeClick: beforeClick, 
onClick: onClick 
} 
}; 
//读取树形数据
$(document).ready(function() {
	parent.mfzTree();
});
var zNodes = [];
parent.mfzTree = function() {
	$.ajax( {
		url : 'CategoryAction_findcyListByrootId.action',
		type : 'post',
		data :{status:'物料类别'},
		dataType : 'json',
		cache : true,
		success : function(doc) {
			$(doc).each(
					function() {
						zNodes.push( {
							id : $(this).attr('id'),
							pId : $(this).attr('fatherId'),
							name : $(this).attr('name'),
							target : "main",
							click : false
						});

					});
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.expandAll(true);
		},
		error : function() {
			alert("服务器异常!");
		}
	});
};

function beforeClick(treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);
	if (!check)
		alert("只能选择最底层");
	return check;
}

function onClick(e, treeId, treeNode) { 
var zTree = $.fn.zTree.getZTreeObj("treeDemo"), 
nodes = zTree.getSelectedNodes(), 
v = ""; 
nodes.sort(function compare(a,b){return a.id-b.id;}); 
for (var i=0, l=nodes.length; i<l; i++) { 
v = nodes[i].name ; 
} 
//if (v.length > 0 ) v = v.substring(0, v.length-1); 
 cityObj = $("#wgType").val(v); 

} 

function showMenu() { 
var cityObj = $("#wgType"); 
var cityOffset = $("#wgType").offset(); 
$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast"); 

$("body").bind("mousedown", onBodyDown); 
} 
function hideMenu() { 
$("#menuContent").fadeOut("fast"); 
$("body").unbind("mousedown", onBodyDown); 
} 
function onBodyDown(event) { 
if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) { 
hideMenu(); 
} 
} 
function getAllNames(obj){
	if(obj!=null && obj.value!=''){
		$.ajax( {
		type : "POST",
		url : "PriceAction!getAllNames.action",
		data : {
					'yuanclAndWaigj.trademark' : obj.value,
					'yuanclAndWaigj.markId' : obj.value,
					'yuanclAndWaigj.clClass' : '外购件'
				},
		dataType : "json",
		cache : false,//防止数据缓存
		success : function(data) {
			if(data!=null){
				$("#proName").val(data.name);
				$("#wgType").val(data.wgType);
			}
					
		},
	});
	}
}

</SCRIPT>
	</body>
</html>
