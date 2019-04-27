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
		<STYLE type="text/css">
.ztree li a {
	color: #fff;
}
</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">

			<div align="center" id="d1">
				<form action="zhaobiaoAction!addUser.action" method="post"
					theme="simple" onsubmit="return check()">
					<table align="center" class="table">

						<tr>
							<th>
								公司全称
							</th>
							<td>
								<input type="text" id="zhUser.cmp" name="zhUser.cmp" value="${supplierCertification.supplierName}" readonly="readonly"/>
							</td>
							<th>
								公司简称
							</th>
							<td>
								<input type="text" id="zhUser.name" name="zhUser.name" />
							</td>

						</tr>
						<tr>
							<th>
								编号
							</th>
							<td>
								<input type="text" id="zhUser.usercode" name="zhUser.usercode" />
							</td>
							<th>
								物料类别
							</th>
							<td>
								<div class="zTreeDemoBackground left">
									<ul class="list">
										<li class="title">
											<input id="wgType" type="text" readonly="readonly" value=""
												name="zhUser.cclass" />
											<a id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a>(按住Ctrl建不松点击,可多选)
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
						<%--
						<tr>
							<th>
								电话
							</th>
							<td>
								<input type="text" id="zhUser.cfax" name="zhUser.cfax" />
							</td>
						</tr>
						--%>
						<!--  <input type="text" id="zhUser.cclass" name="zhUser.cclass" /></td></tr> -->
						<tr>
							<%--<th>
								所能提供的商品
							</th>
							<td>
								<input type="text" id="zhUser.note" name="zhUser.note" />
							</td>
							--%>
							<th>
								联系人
							</th>
							<td>
								<input type="text" id="zhUser.cperson" name="zhUser.cperson" value="${supplierCertification.supplierTel}" readonly="readonly"/>
							</td>
							<th>
								联系人电话
							</th>
							<td>
								<input type="text" id="zhUser.cmobile" name="zhUser.cmobile" value="${supplierCertification.supplierContact}" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th>
								手机
							</th>
							<td>
								<input type="text" id="zhUser.ctel" name="zhUser.ctel" />
							</td>
							<th>
								邮箱
							</th>
							<td>
								<input type="text" id="zhUser.yx" name="zhUser.yx" />
							</td>
						</tr>
						<tr>
							<th>
								公司地址
							</th>
							<td colspan="3">
								<input type="text" id="zhUser.companydz" name="zhUser.companydz"
									style="width: 400px;" value="${supplierCertification.supplierAddress}" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th>
								付款方式
							</th>
							<td>
								<input type="text" id="zhUser.fkfs" name="zhUser.fkfs" />
							</td>
							<th>
								增值税率(%)
							</th>
							<td>
								<input type="text" id="zhUser.zzsl" name="zhUser.zzsl" />
							</td>
						</tr>
						<tr>
							<th>
								税务登记号
							</th>
							<td>
								<input type="text" id="zhUser.djh" name="zhUser.djh" />
							</td>
							<th>
								营业执照
							</th>
							<td>
								<input type="text" id="zhUser.yyzz" name="zhUser.yyzz" />
							</td>
						</tr>
						<tr>
							<th>
								注册日期
							</th>
							<td>
								<input type="text" id="zhUser.time" name="zhUser.time" />
							</td>
							<th>
								失效日期
							</th>
							<td>
								<input type="text" id="zhUser.et" name="zhUser.et" />
							</td>
						</tr>
						<tr>
							<th>
								最低起订量
							</th>
							<td>
								<input type="text" id="zhUser.zdqdl" value=""
									name="zhUser.zdqdl" />
							</td>
							<th>
								最低装箱量
							</th>
							<td>
								<input type="text" id="zhUser.zdzxl" value=""
									name="zhUser.zdzxl" />
							</td>
						</tr>
						<tr>
							<th>
								最低起送量
							</th>
							<td>
								<input type="text" id="zhUser.zdqsl" value=""
									name="zhUser.zdqsl" />
							</td>
							<th>
								供货率
							</th>
							<td>
								<input type="text" id="zhUser.gonghuolv" name="zhUser.gonghuolv" />
								<font style="color: red; size: 2px;" size="2px">*用于自动评标</font>
							</td>
						</tr>
						<tr>
							<th>
								合格率
							</th>
							<td>
								<input type="text" id="zhUser.zhiliang" name="zhUser.zhiliang" />
								<font style="color: red;" size="2px">*用于自动评标</font>
							</td>
							<th>
								ASL
							</th>
							<td>
								<select name="zhUser.asl" id="zhUser.asl">
									<option value="${zhUser.asl}">
										${zhUser.asl}
									</option>
									<option value="大批量">
										大批量
									</option>
									<option value="小批量">
										小批量
									</option>
									<option value="试制">
										试制
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								预付款比例
							</th>
							<td>
								<input type="text" id="yufuBiLi" name="zhUser.yufuBiLi" />
								<font style="color: red; size: 2px;" size="2px">*用于生成预付申请单</font>
							</td>
							<th>
								付款周期(票到)
							</th>
							<td>
								<input type="text" id="zhUser.fkZhouQi" name="zhUser.fkZhouQi" />天
							</td>
						</tr>
						<tr>
							<th>
								开票依据
							</th>
							<td>
								<SELECT id="kpYiJu" name="zhUser.kpYiJu" style="width: 173px;">
									<option value="">--请选择--</option>
									<option value="hgrk">合格入库</option>
									<option value="scwbl">生产无不良</option>
									<option value="cpck">成品出库</option>
								</SELECT>
								<font style="color: red; size: 2px;" size="2px">*</font>
							</td>
							<th>
								是否认证
							</th>
							<td>
							<SELECT id="isAuthentication" name="zhUser.isAuthentication" style="width: 173px;">
									<option value="">--请选择--</option>
									<option value="是">是</option>
									<option value="否">否</option>
								</SELECT>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<s:submit value="保存" />
								<input type="button" name="Submit2" value="取消"
									class="right-buttons" onclick="window.history.go(-1);" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<%@include file="/util/foot.jsp"%>
			</center>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
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
v += nodes[i].name+";" ; 
} 
if (v.length > 0 ) v = v.substring(0, v.length-1); 
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
	
function check(){
	var fkfs = $("#zhUser.fkfs").val();
	if(fkfs ==""){
		alert("请填写付款方式!~")
		return false;
	}
}	
	</SCRIPT>

</html>
