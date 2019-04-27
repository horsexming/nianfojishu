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
				<s:if test="status=='xgwgType'">
					<s:if
						test="yuanclAndWaigj.newwgType!=null&&yuanclAndWaigj.newwgType.length()>0&&yuanclAndWaigj.epId!=null
						&& yuanclAndWaigj.epStatus != '打回' ">
						<a
							href="CircuitRunAction_findAduitPage.action?id=${yuanclAndWaigj.epId}">审批动态</a>
					</s:if>
					<s:else>
						<form action="yuanclAndWaigjAction_xgWgType.action" method="post">
							<table>
								<tr>
									<th align="right">
										件号:
									</th>
									<td>
										<input type="text" value="${yuanclAndWaigj.markId}"
											readonly="readonly" />
									</td>
								</tr>
								<tr>
									<th align="right">
										物料类别:
									</th>
									<td>
										<div class="zTreeDemoBackground left">
											<ul class="list">
												<li class="title">
													<input id="wgType" type="text" readonly="readonly"
														value="${yuanclAndWaigj.wgType}" style="width: 120px;"
														name="yuanclAndWaigj.wgType" />
													<a id="menuBtn" href="#"
														onclick="showMenu(); return false;">选择</a><font
														color="red">*</font>
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
									<td colspan="2" align="center">
										<input type="hidden" value="${yuanclAndWaigj.id}"
											name="yuanclAndWaigj.id" />
										<input type="submit" value="申请修改" class="input">
									</td>
								</tr>
							</table>
						</form>
					</s:else>
				</s:if>
				<s:elseif test='status=="xgperiod"'>
					<form action="yuanclAndWaigjAction_daoruperiod.action"
						method="post" enctype="multipart/form-data"
						onsubmit="return validate();">
						<font size="4">导入:</font>&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="file" name="file">
						<a href="<%=basePath%>/upload/file/download/WaiGouJianLTDaoRu.xls">导入模版下载</a>
						<input id="scbtn" type="submit" value="上传"
							style="width: 80px; height: 20px;">
						<label id="lb" style="display: none;">
							正在导入中请耐心等待
						</label>
					</form>
					<form action="yuanclAndWaigjAction_xgperiod.action" method="post">
						<table class="table">
							<tr>
								<th align="right">
									件号:
								</th>
								<td>
									<input type="text" value="${yuanclAndWaigj.markId}"
										readonly="readonly" />
								</td>
							</tr>
							<tr>
								<th align="right">
									最小库存
								</th>
								<td>
									<input type="text" name="yuanclAndWaigj.minkc" id="minkc"
										value="${yuanclAndWaigj.minkc}" />
									低于此库存，将自动采购
								</td>
							</tr>
							<tr>
								<th align="right">
									采购量
								</th>
								<td>
									<input type="text" name="yuanclAndWaigj.cgcount" id="cgcount"
										value="${yuanclAndWaigj.cgcount}" onchange="numyanzheng(this)" />
									安全库存自动采购时采购量
								</td>
							</tr>
							<tr>
								<th align="right">
									最大库存
								</th>
								<td>
									<input type="text" name="yuanclAndWaigj.maxkc" id="maxkc"
										value="${yuanclAndWaigj.maxkc}" />
									库存中允许的最大库存数
								</td>
							</tr>
							<tr>
								<th align="right">
									采购周期
								</th>
								<td>
									<input type="text" name="yuanclAndWaigj.cgperiod" id="cgperiod"
										value="${yuanclAndWaigj.cgperiod}"
										onchange="numyanzheng(this)" />
								</td>
							</tr>
							<tr>
								<th align="right">
									LT等级
								</th>
								<td>
									<SELECT name="" id="waigoult" onchange="chnaglt(this)">
										<option
											value="${yuanclAndWaigj.ltdengji};${yuanclAndWaigj.ltuse}">
											${yuanclAndWaigj.ltdengji}
										</option>
									</SELECT>
									<input type="hidden" value="" id="ltdengji"
										name="yuanclAndWaigj.ltdengji">
									<input type="hidden" value="" id="ltuse"
										name="yuanclAndWaigj.ltuse">
								</td>
							</tr>
							<tr>
								<th align="right">
									损耗值类型
								</th>
								<td>
									<input type="radio" value="百分比"
										name="yuanclAndWaigj.sunhaoType" checked="checked" />
									百分比
									<input type="radio" value="固定值"
										name="yuanclAndWaigj.sunhaoType" />
									固定值
								</td>
							</tr>
							<tr>
								<th align="right">
									损耗值
								</th>
								<td>
									<input type="text" value="" name="yuanclAndWaigj.sunhao"
										onchange="numyanzheng(this)" />
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<input type="hidden" value="${yuanclAndWaigj.id}"
										name="yuanclAndWaigj.id" />
									<input type="submit" value="修改" class="input">
								</td>
							</tr>
						</table>
					</form>
				</s:elseif>
				<input type="hidden" id="rebeack" value='${errorMessage}' />
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">

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
if (!check) alert("只能选择最底层"); 
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


$(function(){
		$.ajax( {
		type : "POST",
		url : "yuanclAndWaigjAction_findALllt.action",
		data : {
			},
		dataType : "json",
		success : function(data) {
			$("#waigoult").empty();
			$("#waigoult").append("<option value='${yuanclAndWaigj.ltdengji};${yuanclAndWaigj.ltuse}'>${yuanclAndWaigj.ltdengji}</option>");
			$(data).each(function(){
				$("#waigoult").append("<option value="+this.ltdengji+";"+this.ltuse+">"+this.ltdengji+"</option>");
			})
		}
	})
})
function chnaglt(obj){
	if(obj!=null && obj.value!=''){
		var strs = obj.value.split(";");
		if(strs!=null && strs.length==2){
			$("#ltdengji").val(strs[0]);
			$("#ltuse").val(strs[1]);
		}
	}
}



$(document).ready(function() {
	var rebeack = $("#rebeack").val();
	if (rebeack == "申请成功!" || rebeack =="true") {
		if(rebeack =="true"){
			rebeack = "修改成功！";	
		}
		alert(rebeack);
		parent.chageDiv('none');
		parent.window.location.reload();
	}
})

</SCRIPT>
	</body>
</html>
