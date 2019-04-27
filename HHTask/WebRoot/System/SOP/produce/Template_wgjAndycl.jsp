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
		<div id="gongneng">
			<div align="center">
				<h3>
					请选择
				</h3>
				<form action="procardTemplateGyAction_showYclAndWgj.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<td colspan="2" align="center">
								<div class="zTreeDemoBackground left">
									<ul class="list">
										<li class="title">
											物料类别:
											<input id="wgType" type="text" readonly="readonly" value=""
												style="width: 120px;" name="yclAndWgj.wlType"/>
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
									<input type="hidden" name="type"
										value="<s:property value='type'/>" />
									件号：
									<input type="text" name="yclAndWgj.markId" value="${yclAndWgj.markId}" />
								</td>
								<td align="center">
									名称：
									<input type="text" name="yclAndWgj.name" />
								</td>
							</tr>
							<tr>
								<td align="center">
									材质类型：
									<input type="text" name="yclAndWgj.wgType" />
								</td>
								<td align="center">
									图号：
									<input type="text" name="yclAndWgj.tuhao" />
								</td>
							</tr>
							<tr>
								<td align="center">
									规格：
									<input type="text" name="yclAndWgj.specification" />
								</td>
								<td align="center">
									供料属性:
									<select name="yclAndWgj.kgliao" id="kgliao"
										style="width: 155px;">
										<option>
										</option>
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
								</td>
							</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
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
							名称
						</td>
						<%--					<td align="center">--%>
						<%--						牌号--%>
						<%--					</td>--%>
						<td align="center">
							规格
						</td>
						<td align="center">
							版本号
						</td>
						<td align="center">
							供料属性
						</td>
						<td align="center">
							材质
						</td>
						<td align="center">
							单位
						</td>
						<td align="center">
							图号
						</td>
						<td align="center">
							重要性
						</td>
						<td align="center">
							单张重量
						</td>
						<td align="center">
							仓库单位
						</td>
						<td align="center">
							总成号
						</td>
						<td align="center">
							状态
						</td>
						<td align="center">
							质检周期
						</td>
						<td align="center">
							备注
						</td>
						<td align="center" colspan="2">
							操作
							<br />
							(Operation)
						</td>
					</tr>
					<s:iterator value="list" id="yuanclAndWaigjPage"
						status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>
							${yuanclAndWaigjPage.wgType}
						</td>
						<td align="left">
							${yuanclAndWaigjPage.markId}
						</td>
						<td align="left">
							${yuanclAndWaigjPage.name}
						</td>
						<td>
							${yuanclAndWaigjPage.specification}
						</td>
						<td>
							${yuanclAndWaigjPage.banbenhao}
						</td>
						<td>
							${yuanclAndWaigjPage.kgliao}
						</td>
						<td>
							${yuanclAndWaigjPage.caizhi}
						</td>
						<td>
							${yuanclAndWaigjPage.unit}
						</td>
						<td>
							${yuanclAndWaigjPage.tuhao}
						</td>
						<td>
							${yuanclAndWaigjPage.importance}
						</td>
						<td>
							${yuanclAndWaigjPage.bili}
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
							<s:if test="#yuanclAndWaigjPage.status=='禁用'">禁用</s:if>
							<s:elseif test="#yuanclAndWaigjPage.status=='已确认'">已确认</s:elseif>
							<s:else>使用</s:else>
						</td>
						<td>
							${yuanclAndWaigjPage.round}
						</td>
						<td>
							${yuanclAndWaigjPage.remark}
						</td>
						<td colspan="2">
							<s:if test="type=='wgj'||type=='wgj2'">
								<input type="button" value="选择"
									style="width: 60px; height: 30px;"
									onclick="selectWgj('${yuanclAndWaigjPage.markId}',
									'${yuanclAndWaigjPage.name}','${yuanclAndWaigjPage.specification}',
									'${yuanclAndWaigjPage.unit}','${yuanclAndWaigjPage.banbenhao}',
									'${yuanclAndWaigjPage.zcMarkId}','${yuanclAndWaigjPage.wgType}',
									'${yuanclAndWaigjPage.tuhao}','${yuanclAndWaigjPage.kgliao}',
									'${yuanclAndWaigjPage.bili}','${yuanclAndWaigjPage.importance}','${yuanclAndWaigjPage.iszyl}')"/>
							</s:if>
							<s:if test="type=='zuhe'">
								<input type="button" value="选择"
									style="width: 60px; height: 30px;"
									onclick="selectzuhe('${yuanclAndWaigjPage.markId}','${yuanclAndWaigjPage.specification}','${yuanclAndWaigjPage.unit}','${yuanclAndWaigjPage.wgType}','${yuanclAndWaigjPage.tuhao}','${yuanclAndWaigjPage.bili}','${yuanclAndWaigjPage.name}')" />
							</s:if>
							<!-- 原材料 -->
							<s:if test="type=='ycl'">
								<input type="button" value="选择"
									style="width: 60px; height: 30px;"
									onclick="selectYcl('${yuanclAndWaigjPage.markId}','${yuanclAndWaigjPage.specification}','${yuanclAndWaigjPage.unit}','${yuanclAndWaigjPage.wgType}','${yuanclAndWaigjPage.tuhao}','${yuanclAndWaigjPage.bili}'),'${yuanclAndWaigjPage.name}'" />
							</s:if>
							<!-- 余料 -->
							<s:if test="type=='yl'">
								<input type="button" value="选择"
									style="width: 60px; height: 30px;"
									onclick="selectYl('${yuanclAndWaigjPage.markId}','${yuanclAndWaigjPage.specification}','${yuanclAndWaigjPage.unit}','${yuanclAndWaigjPage.wgType}'),'${yuanclAndWaigjPage.tuhao}','${yuanclAndWaigjPage.bili}','${yuanclAndWaigjPage.name}'" />
							</s:if>
						</td>
					</s:iterator>
					</tr>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="18" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="18" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>

					<s:if test="successMessage!=null">
						<tr>
							<td colspan="18" align="center" style="color: red">
								${successMessage}

							</td>
						</tr>
					</s:if>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>

		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function selectWgj(markId, proName, specification, unit, banbenhao, loadMarkId,
		wgType, tuhao, kgliao,bili,importance,iszyl) {
	parent.$("#wajmarkId").val(markId);
	parent.$("#wajproName").val(proName);
	parent.$("#wajspecification").val(specification);
	parent.$("#danwei2").val(unit);
	parent.$("#wajbanBenNumber").val(banbenhao);
	parent.$("#wajloadMarkId").val(loadMarkId);
	parent.$("#wajwgType").val(wgType);
	parent.$("#wajtuhao").val(tuhao);
	parent.$("#kgliao").val(kgliao);
	parent.$("#waibili").val(bili);
	parent.$("#wajisycl").val("yes");
	if(iszyl == '是'){
		parent.$("#sunhao").attr('disabled','disabled');
		parent.$("#zyl_msg").html('专用料无需设置损耗值!');
	}else{
		parent.$("#sunhao").removeAttr('disabled');
		parent.$("#zyl_msg").html('');
	}
	
	parent.$("#wajimportance").val(importance);
	parent.chageDiv('none');
}
//原材料
function selectYcl(markId, specification, unit, clType, tuhao, bili, yuanName) {
	parent.$("#wajmarkId").val(markId);
	parent.$("#wajspecification").val(specification);
	parent.$("#danwei2").val(unit);
	parent.$("#wajwgType").val(clType);
	parent.$("#wajtuhao").val(tuhao);
	parent.$("#waibili").val(bili);
	parent.$("#wajproName").val(yuanName);
	parent.chageDiv('none');
}
//余料
function selectYl(trademark, specification, unit, clType, tuhao, bili, yuanName) {
	parent.$("#newGoodsMarkId").val(trademark);
	parent.$("#newGoodsFormat").val(specification);
	parent.$("#newGoodsUnit").val(unit);
	parent.$("#newyuanName").val(yuanName);
	parent.chageDiv('none');
}
function selectzuhe(markId, specification, unit, clType, tuhao, bili, yuanName) {
	parent.$("#wajmarkId").val(markId);
	parent.$("#wajspecification").val(specification);
	parent.$("#danwei2").val(unit);
	parent.$("#wajwgType").val(clType);
	parent.$("#wajtuhao").val(tuhao);
	parent.$("#waibili").val(bili);
	parent.$("#wajproName").val(yuanName);
	parent.$("#wajisycl").val("yes");
	parent.chageDiv('none');
}
$(function() {
	var main = $(window.parent.document).find("#showProcess");//找到iframe对象
	if (main != null) {
		var thisheight = document.body.scrollHeight;
		var thisheight2 = parseFloat(thisheight);
		main.height(thisheight2 + 100);//为iframe高度赋值如果高度小于500，则等于500，反之不限高，自适应
	}
})


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
	parent.mfzTree('CategoryAction_findcyListByrootId.action');
});
var zNodes = [];
parent.mfzTree = function(url) {
	$.ajax( {
		url : url,
		type : 'post',
		data :{},
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

return true; 
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

</script>
	</body>
</html>

