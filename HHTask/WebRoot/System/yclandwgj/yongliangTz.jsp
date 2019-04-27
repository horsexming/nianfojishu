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
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在进行修改物料类别操作</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>

				</div>
			</div>
		</div>
		<div align="center">
			<h3>
				外购件管理
				<br />
			</h3>
			<form action="yuanclAndWaigjAction_showList.action" method="post" >
				<table class="table" align="center">
					<tr>
						<td align="center" colspan="4">
							<div class="zTreeDemoBackground left">
								<ul class="list">
									<li class="title">
										物料类别:
										<input id="wgType" type="text" readonly="readonly" value=""
											style="width: 120px;" name="yuanclAndWaigj.wgType" />
										<a id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a>
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
						<td align="center">
							件号：
							<input type="text" name="yuanclAndWaigj.markId"
								value="<s:property value="yuanclAndWaigj.markId"/>" />
						</td>
						<td align="center">
							名称：
							<input type="text" name="yuanclAndWaigj.name"
								value="<s:property value="yuanclAndWaigj.name"/>" />
						</td>
						<td align="center">
							规格：
							<input type="text" name="yuanclAndWaigj.specification"
								value="<s:property value="yuanclAndWaigj.specification"/>" />
						</td>
						<td align="center">
							材质：
							<input type="text" name="yuanclAndWaigj.caizhi"
								value="<s:property value="yuanclAndWaigj.caizhi"/>" />
						</td>
					</tr>
					<tr>
						<td align="center">
							版本号：
							<input type="text" name="yuanclAndWaigj.banbenhao"
								value="<s:property value="yuanclAndWaigj.banbenhao"/>" />
						</td>
						<td align="center">
							状态：
							<select id="status" name="yuanclAndWaigj.status"
								style="width: 155px;">
								<option></option>
								<option>
									使用
								</option>
								<option>
									已确认
								</option>
								<option>
									禁用
								</option>
							</select>
						</td>
						<td align="center">
							图号：
							<input id="caizhi" type="text" name="yuanclAndWaigj.tuhao"
								value="<s:property value="yuanclAndWaigj.tuhao"/>" />
						</td>
						<td align="center">
							总成号：
							<input id="zcMarkId" type="text" name="yuanclAndWaigj.zcMarkId"
								value="<s:property value="yuanclAndWaigj.zcMarkId"/>" />
						</td>
					</tr>
					<tr>
						<td align="center">
							BOM单位：
							<select id="unit" name="yuanclAndWaigj.unit"
								style="width: 155px;">
								<option></option>
							</select>
						</td>
						<td align="center">
							仓库单位：
							<select id="ckUnit" name="yuanclAndWaigj.ckUnit"
								style="width: 155px;">
								<option></option>
							</select>
						</td>
						<td align="center">
							产品类型：
							<select id="ckUnit" name="productStyle"
								style="width: 155px;">
								<option value="${productStyle}">
									<s:if test='productStyle=="pichan"'>
										批产
									</s:if>
									<s:elseif test='productStyle=="shizhi"'>
										试制
									</s:elseif>
								</option>
								<option value="">
								</option>
								<option value="shizhi">
									试制
								</option>
								<option value="pichan">
									批产
								</option>
							</select>
						</td>
						<td align="center">
							添加时间：
							<input name="yuanclAndWaigj.addTime" class="Wdate"
								value="${yuanclAndWaigj.addTime}"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
						</td>
						<%--						<td align="center">--%>
						<%--							类型：--%>
						<%--							<select id="ckUnit" name="yuanclAndWaigj.productStyle"--%>
						<%--								style="width: 155px;">--%>
						<%--								<s:if test="yuanclAndWaigj.productStyle!=null">--%>
						<%--								<option><s:property value="yuanclAndWaigj.productStyle"/></option>--%>
						<%--								</s:if>--%>
						<%--								<option></option>--%>
						<%--								<option>批产</option>--%>
						<%--								<option>试制</option>--%>
						<%--							</select>--%>
						<%--						</td>--%>
					</tr>
					<tr>
						<td align="center" colspan="4">
							<input type="hidden" value="${pageStatus}" name="pageStatus" />
							<input type="hidden" value="${tag}" name="tag" />
							<input type="submit" style="width: 100px; height: 40px;"
								value="查询(select)" />
							<s:if test="tag != 'xgwgType'">
								<input type="button" style="width: 100px; height: 40px;"
									value="添加(add)" onclick="add()" />
								<%--<input type="button" style="width: 100px; height: 40px;"
								value="更新已有" onclick="gethad()" />
							--%>
							</s:if>
						</td>
					</tr>
				</table>
			</form>
			<form action="yuanclAndWaigjAction_tzylPL.action" method="post" id="myform" onsubmit="setName()">
				<table class="table" >
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							<input type="checkbox" name="" onclick="chageAllCheck(this);editorAll(this)"
							>
						</td>
						<th>
							序号
						</th>
						<th>
							件号
						</th>
						<th>
							名称
						</th>
						<th>
							版本
						</th>
						<th>
							规格
						</th>
						<th>
							每公斤喷粉面积(调整前)
						</th>
						<th>
							每公斤喷粉面积(调整后)
						</th>
						<th>
							用量标准(调整前)
						</th>
						<th>
							用量标准(调整后)
						</th>
						<th>
							调整原因
						</th>
					</tr>
					<s:iterator value="YuanclAndWaigjList" id="yuanclAndWaigjPage"
						status="pageStatus0">
						<s:if test="#pageStatus0.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td align="center">
							<input type="checkbox" name="zhUserId" onclick="editor(this)"
									value="${yuanclAndWaigjPage.id}">
						</td>
						<td>
							<s:property value="#pageStatus0.index+1" />
						</td>
						<td>${yuanclAndWaigjPage.markId}</td>
						<td>${yuanclAndWaigjPage.name}</td>
						<td>${yuanclAndWaigjPage.banbenhao}</td>
						<td>${yuanclAndWaigjPage.specification}</td>
						<td>
							${yuanclAndWaigjPage.areakg}
						</td>
						<td>
							<input type="text" value="" name=""  onchange="numyanzheng(this)"
							disabled="disabled" style="width: 75px;" />
						</td>
						<td>
							<input type="text" value="" name=""  onchange="numyanzheng(this)"
							disabled="disabled" style="width: 75px;" />
						</td>
						<td>
							<input type="text" value="" name=""  onchange="numyanzheng(this)"
							disabled="disabled" style="width: 75px;" />
						</td>
						<td>
							<input type="text" value="" name=""  
							disabled="disabled" />
						</td>
					</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="28" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="28" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
				</table>
				<input type="submit" value="申请调整" class="input" id="sub"/>
			</form>
		</div>
		<br>
		</div>
		<%@include file="/util/foot.jsp"%>

		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function view(id) {
	window.location.href = "yuanclAndWaigjAction_yuanclAndWaigjLogView.action?yuanclAndWaigj.id="
			+ id;
}
function tanchu(id, status) {
	document.getElementById("xiugaiIframe").src = "yuanclAndWaigjAction_toupdate.action?yuanclAndWaigj.id="
			+ id + "&cpage=${cpage}&status=" + status + "&tag=${tag}";
	chageDiv('block');
}
function update(id,status) {
	window.location.href = "yuanclAndWaigjAction_toupdate.action?yuanclAndWaigj.id="
			+ id + "&cpage=${cpage}&productStyle=${productStyle}&tag=${tag}&status="+status;
}
function updateStatus(id) {
	if (confirm("确定要禁用该件号吗?")) {
		window.location.href = "yuanclAndWaigjAction_updateStatus.action?yuanclAndWaigj.id="
				+ id
				+ "&cpage=${cpage}&productStyle=${productStyle}&tag=${tag}";
	}
}
function todelete(id) {
	if (window.confirm("您将删除此数据,是否继续?")) {
		window.location.href = "yuanclAndWaigjAction_delete.action?yuanclAndWaigj.id="
				+ id
				+ "&cpage=${cpage}&productStyle=${productStyle}&tag=${tag}";
	}
}
function add() {
	window.location.href = "<%=path%>/System/yclandwgj/yuanclAndWaigj_add.jsp";
}
function gethad() {
	window.location.href = "yuanclAndWaigjAction_gethad.action";
}
$(function() {
	getUnit("ckUnit")
	getUnit("unit")
	getCaizhi("caizhi");
})

function exportExcel(objForm) {
	objForm.action = "yuanclAndWaigjAction_explorExcel.action?&tag=${tag}";
	objForm.submit();
	objForm.action = "yuanclAndWaigjAction_showList.action?tag=${tag}";
}
var mfzTree;
var addzTree;
var delzTree;
var updatezTree;

var id;
var pId;
var name;
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
	parent.mfzTree();
});
var zNodes = [];
parent.mfzTree = function() {
	$.ajax( {
		url : 'CategoryAction_findcyListByrootId.action',
		type : 'post',
		data : {
			status : '物料'
		},
		dataType : 'json',
		cache : true,
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

	return true;
}

function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
			.getSelectedNodes(), v = "";
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	for ( var i = 0, l = nodes.length; i < l; i++) {
		v = nodes[i].name;
	}
	//if (v.length > 0 ) v = v.substring(0, v.length-1); 
	cityObj = $("#wgType").val(v);

}

function showMenu() {
	var cityObj = $("#wgType");
	var cityOffset = $("#wgType").offset();
	$("#menuContent").css( {
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
			event.target).parents("#menuContent").length > 0)) {
		hideMenu();
	}
}
function tosubmit(num) {
	$("#myform")
			.attr(
					'action',
					'yuanclAndWaigjAction_auditYclWgl.action?id=' + num
							+ "&tag=${tag}")
	$("#myform").submit();
}
function editor(obj){
	var tr =$(obj).parent().parent("tr");
	if(obj.checked){
		$(tr).find("input[type='text']").removeAttr("disabled");
	}else{
		$(tr).find("input[type='text']").attr("disabled","disabled");
	}
}
function editorAll(obj){
	if(obj.checked){
		$("#myform").find("input[type='text']").removeAttr("disabled");
	}else{
		$("#myform").find("input[type='text']").attr("disabled","disabled");
	}
}
function setName(){
	var checkboxs = document.getElementsByName("zhUserId");
	var index =0;
	for( i in checkboxs){
		if(checkboxs[i].checked){
			var tr =$(checkboxs[i]).parent().parent("tr");
			var input=$(tr).find("input[type='text']");
			$(input[0]).attr("name",'fmtzrList['+index+'].areakg1');
			$(input[1]).attr("name",'fmtzrList['+index+'].ylbz0');
			$(input[2]).attr("name",'fmtzrList['+index+'].ylbz1');
			$(input[3]).attr("name",'fmtzrList['+index+'].tzReason');
			index++;
		}
	}
	if(index == 0){
		alert("请至少选择一条数据");
		return false;
	}
	$("#sub").attr("disabled","disabled");
}

</script>
	</body>
</html>

