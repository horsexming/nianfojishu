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
			<br />
			<form action="yuanclAndWaigjAction_daoruyuanclAndWaigj.action"
				method="post" enctype="multipart/form-data">
				选择导入文件:
				<input type="file" name="file" />
				<a href="<%=basePath%>/upload/file/download/yuanclAndWaigj.xls">导入模版下载</a>
				<a
					href="FileViewAction.action?FilePath=/upload/file/download/yuanclAndWaigj.xls&Refresh=true">/预览</a>
				<input type="submit" value="批量导入" id="sub" />
			</form>
			<br />
			<form action="yuanclAndWaigjAction_showList.action" method="post">
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
							<input type="hidden" name="pageStatus"
								value="<s:property value='pageStatus'/>" />
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
							<input type="hidden" value="${tag}" name="tag" />
							<input type="submit" style="width: 100px; height: 40px;"
								value="查询(select)" />
							<s:if test="tag != 'xgwgType'">
								<input type="button" style="width: 100px; height: 40px;"
									value="添加(add)" onclick="add()" />
								<%--<input type="button" style="width: 100px; height: 40px;"
								value="更新已有" onclick="gethad()" />
							--%>
								<input type="button" value="导出(export)"
									onclick="exportExcel(this.form);todisabledone(this)" data="downData"
									style="width: 100px; height: 40px;" />
							</s:if>
						</td>
					</tr>
				</table>
			</form>
			<form action="" method="post" id="myform">
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<s:if test='pageStatus == "audit"'>
							<td align="center">
								<input type="checkbox" name="" onclick="chageAllCheck(this)">
							</td>
						</s:if>
						<td align="center">
							序号
						</td>
						<td align="center">
							物料类别
						</td>
						<td align="center">
							件号
						</td>
						<td align="center">
							生产类型
						</td>
						<td align="center" style="max-width: 100px;">
							名称
						</td>
						<td align="center" style="max-width: 100px;">
							规格
						</td>
						<td align="center">
							版本号
						</td>
						<td align="center">
							供料属性
						</td>
						<td align="center">
							单位
						</td>
						<td align="center">
							重要性
						</td>
						<td align="center">
							材质
						</td>
						<td align="center">
							图号
						</td>
						<td align="center">
							单张重量
						</td>
						<td align="center">
							密度
						</td>
						<td align="center">
							每公斤喷粉面积(㎡/kg)
						</td>
						<td align="center">
							仓库单位
						</td>
						<td align="center">
							总成号
						</td>
						<%--
					<td align="center">
						材料类型
					</td>
					--%>
						<td align="center">
							质检周期
						</td>
						<td align="center">
							添加人
						</td>
						<td align="center">
							添加时间
						</td>
						<td align="center">
							备注
						</td>
						<td align="center">
							可用状态
						</td>
						<td align="center">
							物料状态
						</td>
						<td align="center" colspan="2">
							操作
						</td>
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
						<s:if test='pageStatus == "audit"'>
							<td align="center">
								<input type="checkbox" name="zhUserId"
									value="${yuanclAndWaigjPage.id}">
							</td>
						</s:if>
						<td>
							<s:property value="#pageStatus0.index+1" />
						</td>
						<td>
							<s:if test='pageStatus == "audit"'>
							${yuanclAndWaigjPage.wgType}==>${yuanclAndWaigjPage.newwgType}
						</s:if>
							<s:else>
							${yuanclAndWaigjPage.wgType}
						</s:else>

						</td>
						<td align="left">
							${yuanclAndWaigjPage.markId}
						</td>
						<td align="left">
							${yuanclAndWaigjPage.productStyle}
						</td>
						<td align="left" style="max-width: 100px;">
							${yuanclAndWaigjPage.name}
						</td>
						<td style="max-width: 100px;">
							${yuanclAndWaigjPage.specification}
						</td>
						<td>
							${yuanclAndWaigjPage.banbenhao}
						</td>
						<td>
							${yuanclAndWaigjPage.kgliao}
						</td>
						<td>
							${yuanclAndWaigjPage.unit}
						</td>
						<td>
							${yuanclAndWaigjPage.importance}
						</td>
						<td>
							${yuanclAndWaigjPage.caizhi}
						</td>
						<td>
							${yuanclAndWaigjPage.tuhao}
						</td>
						<td>
							${yuanclAndWaigjPage.bili}
						</td>
						<td>
							${yuanclAndWaigjPage.density}
						</td>
						<td>
							${yuanclAndWaigjPage.areakg}
						</td>
						<td>
							${yuanclAndWaigjPage.ckUnit}
						</td>
						<td>
							${yuanclAndWaigjPage.zcMarkId}
						</td>
						<%--
					<td>
						${yuanclAndWaigjPage.clClass}
					</td>
					--%>
						<td>
							${yuanclAndWaigjPage.round}
						</td>
						<td>
							${yuanclAndWaigjPage.addUserName}
						</td>
						<td>
							${yuanclAndWaigjPage.addTime}
						</td>
						<td>
							${yuanclAndWaigjPage.remark}
						</td>
						<td>
							<s:if test="#yuanclAndWaigjPage.status=='禁用'">禁用</s:if>
							<s:elseif test="#yuanclAndWaigjPage.status=='已确认'">已确认</s:elseif>
							<s:else>使用</s:else>
						</td>
						<td>
							${yuanclAndWaigjPage.pricestatus}
						</td>
						<td colspan="2">
							<s:if test="tag!='noUpdate'">
								<input type="button" value="修改"
									style="width: 60px; height: 30px;"
									onclick="update(${yuanclAndWaigjPage.id})" />
								<s:if test="#yuanclAndWaigjPage.wgType == '粉末'">
									<input type="button" value="调整粉末用量"
									style="width: 95px; height: 30px;"
									onclick="update(${yuanclAndWaigjPage.id},'tzfenmo')" />
								</s:if>
							</s:if>
							<s:if test="tag == 'xgwgType'">
								<input type="button" value="修改类别"
									style="width: 75px; height: 30px;"
									onclick="tanchu(${yuanclAndWaigjPage.id},'xgwgType')" />
							</s:if>
							<s:elseif test="tag == 'delete'">
								<input type="button" value="删除"
									style="width: 60px; height: 30px;"
									onclick="todelete(${yuanclAndWaigjPage.id})" />
								<input type="button" value="修改类别"
									style="width: 75px; height: 30px;"
									onclick="tanchu(${yuanclAndWaigjPage.id},'xgwgType')" />
								<input type="button" value="修改周期"
									style="width: 75px; height: 30px;"
									onclick="tanchu(${yuanclAndWaigjPage.id},'xgperiod')" />
							</s:elseif>
							<s:else>
								<s:if test="#yuanclAndWaigjPage.status=='禁用'">
									<s:if test="#yuanclAndWaigjPage.jystatus=='使用申请中'">
										<a
											href="CircuitRunAction_findAduitPage.action?id=${yuanclAndWaigjPage.jyepId}">使用申请中</a>
									</s:if>
									<s:else>
										<input type="button" value="解禁"
											style="width: 60px; height: 30px;"
											onclick="updateStatus(${yuanclAndWaigjPage.id})" />
									</s:else>
								</s:if>
								<s:elseif test="#yuanclAndWaigjPage.status=='已确认'">
									<s:if test="#yuanclAndWaigjPage.jystatus=='禁用申请中'">
										<a
											href="CircuitRunAction_findAduitPage.action?id=${yuanclAndWaigjPage.jyepId}">禁用申请中</a>
									</s:if>
									<s:else>
										<input type="button" value="禁用"
											style="width: 60px; height: 30px;"
											onclick="updateStatus(${yuanclAndWaigjPage.id})" />
									</s:else>
									<input type="button" value="修改类别"
										style="width: 75px; height: 30px;"
										onclick="tanchu(${yuanclAndWaigjPage.id},'xgwgType')" />
									<input type="button" value="修改周期"
										style="width: 75px; height: 30px;"
										onclick="tanchu(${yuanclAndWaigjPage.id},'xgperiod')" />
								</s:elseif>
								<s:else>
									<input type="button" value="确认"
										style="width: 60px; height: 30px;"
										onclick="updateStatus(${yuanclAndWaigjPage.id})" />
									<%--									两个修改？？？	--%>
									<%--									<input type="button" value="修改"--%>
									<%--										style="width: 60px; height: 30px;"--%>
									<%--										onclick="update(${yuanclAndWaigjPage.id})" />--%>
									<input type="button" value="修改类别"
										style="width: 75px; height: 30px;"
										onclick="tanchu(${yuanclAndWaigjPage.id},'xgwgType')" />
									<input type="button" value="修改周期"
										style="width: 75px; height: 30px;"
										onclick="tanchu(${yuanclAndWaigjPage.id},'xgperiod')" />
								</s:else>
							</s:else>
						</td>
					</s:iterator>
					</tr>
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

					<s:if test="successMessage!=null">
						<tr>
							<td colspan="28" align="center" style="color: red">
								${successMessage}

							</td>
						</tr>
					</s:if>
					<s:if test='pageStatus == "audit"'>
						<tr>
							<td colspan="28">
								<input type="button" onclick="tosubmit(1)" value="审批通过"
									class="input">
								<input type="button" onclick="tosubmit(2)" value="审批驳回"
									class="input">
							</td>
						</tr>
					</s:if>
				</table>

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
</script>
	</body>
</html>

