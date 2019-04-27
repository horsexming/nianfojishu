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
				<h2>申请编码</h2>
				<font size="5" color="red">${errorMessage}</font>
				<form action="ChartNOSQAction_updatecc.action" method="post" onsubmit="">
					<table class="table" id = "mytable">
						<tr>
							<th align="right">编号类别</th>
							<td>
								${cc.type}
							</td>
							<th align="right">
								规则类型
							</th>
							<td>
								${cc.chartnosq.gztype}
							</td>
							<th align="right">
								编码
							</th>
							<td>
								${cc.chartNO}
							</td>
							<th align="right">
								<span id="spanziti_0">
									所属产品编码
								</span>
							</th>
							<td>
								<input type="text" value="${cc.cpcode}" id="cpcode0" name="cc.cpcode" />
							</td>
							<tr>
							</tr>
							<th align="right">
								备注
							</th>
							<td colspan="5">
								<textarea rows="" cols="100" name="cc.remak" >
									${cc.remak}
								</textarea>
							</td>
						</tr>
					</table>
						<input type="hidden" value="${cc.id}" name="cc.id"/>
						<input type="hidden" value="${cpage}" name="cpage"/>
						<input type="submit" value="修改" onclick="todisabled(this)" class="input"/>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
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
			status : '编码'
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
	changvalue(size)
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
	var sqNum = $("#sqNum"+num).val();
	var spanziti = $("#spanziti_"+num).html();
	var bool = true;
	if(spanziti == '产品名称'){
		if(sqNum>10){
			bool = false;
			alert("当申请产品编码时每次最多只能申请10个,请重新输入");
			$("#sqNum"+num).val('');
				$("#firstNo"+num).val('');
				$("#endNo"+num).val('');
				$("#firstNoValue"+num).val('');
				$("#endNoValue"+num).val('');
		}
	}
	if(categoryId!='' && gztypename!='' && sqNum!= '' && bool){
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
				var endNo = data.endNo;
				firstNo = firstNo.substring(0,(firstNo.length-4))+"****";
				endNo = endNo.substring(0,(endNo.length-4))+"****";
				$("#firstNo"+num).val(firstNo);
				$("#endNo"+num).val(endNo);
				$("#firstNoValue"+num).val(data.firstNo);
				$("#endNoValue"+num).val(data.endNo);
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
	}
	
}
var count = 1;
function add(){
	var str = '<tr><th align="right">编号类别</th><td><div class="zTreeDemoBackground left">' +
	'<ul class="list"><li class="title"><input id="wgType'+count+'" type="text" readonly="readonly" value="" ' +
	'style="width: 120px;" name="cqList['+count+'].type"  /><input id="categoryId'+count+'" type="hidden" value="" name="cqList['+count+'].categoryId" onchange="getfirstNo('+count+')"/>' +
	'<a id="menuBtn" href="#" onclick="showMenu('+count+'); return false;">选择</a></li></ul></div>' +
	'<div id="menuContent'+count+'" class="menuContent" style="display: none; position: absolute;">' +
	'<ul id="treeDemo'+count+'" class="ztree" style="margin-top: 0; width: 160px;"></ul> </div> </td>' +
	'<th align="right">规则类型</th><td><select id="gztypename'+count+'" ' +
	'name="cqList['+count+'].gztype" style="width: 150px"  ' +
	'onkeyup="getfirstNo('+count+')" onblur="getfirstNo('+count+')" ' +
	' >' +
	'<option value=""></option> <s:iterator value="cgtList" id="cgt">' +
	'<option value="${cgt.type}">${cgt.type}</option> </s:iterator> </select></td><th align="right">申请数量</th>' +
	'<td><input type="text" value="" id="sqNum'+count+'" name="cqList['+count+'].sqNum" ' +
	'onkeyup="numyanzheng(this,&apos;zhengshu&apos;);getfirstNo('+count+')" onblur="numyanzheng(this,&apos;zhengshu&apos;);getfirstNo('+count+')"/></td>' +
	'<td rowspan="3"></td></tr>' +
	'<tr><th align="right">申请编号从<td><input type="text" value="" id="firstNo'+count+'"  />' +
	'<input type="hidden" value="" id="firstNoValue'+count+'" name="cqList['+count+'].firstNo" /></td>' +
	'<th align="right">到<td><input type="text" value="" id="endNo'+count+'"  />' +
	'<input type="hidden" value="" id="endNoValue'+count+'" name="cqList['+count+'].endNo" />' +
	'<th align="right"><span id="spanziti_'+count+'">所属产品编码</span></th><td><input type="text" value="" id="cpcode'+count+'" name="cqList['+count+'].cpcode" /></td></tr>' +
	'<tr><th align="right">备注</th><td colspan="5"><textarea rows="" cols="100" name="cqList['+count+'].remak"></textarea></td></tr>';
								
								
								
							
								
							
	$("#mytable").append(str);
	count++;
}
function del(){
	var n = $('#mytable tr').length;
	if (count == 1) {
		alert("只剩最后一项了,再删真没了");
		return;
	}
		$($('#mytable tr')[n-1]).remove();
		$($('#mytable tr')[n-2]).remove();
		$($('#mytable tr')[n-3]).remove();
		count--;
}
function changvalue(num){
		var id =	$("#categoryId"+num).val();
		$.ajax( {
		type : "POST",
		url : "ChartNOSQAction_changStyle.action",
		data : {id:id},
		dataType : "json",
		success : function(data) {
			if(data!=null){
				var gzTypelist = data.gzTypelist;
				var groups =data.groups;
				if(gzTypelist!=null && gzTypelist.length>0){
					$("#gztypename"+num).empty();
					$(gzTypelist).each(function(){
						$("#gztypename"+num).append('<option value='+this.type+' >'+this.type+'</option>');
					})
				}
				if(groups == "产品编码"){
					$("#spanziti_"+num).html('产品名称');
				}else{
					$("#spanziti_"+num).html('所属产品编码');
				}
				
			}
		}
	})
}



</SCRIPT>
	</body>
</html>
