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
			<div id="gongneng" style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
				<div align="center" id="d1">
					<form action="zhaobiaoAction!updatezhUser.action" method="post"
						theme="simple" onsubmit="return check()">
						<table align="center" class="table">
							<tr>
								<th>
									公司简称
								</th>
								<td>
									<s:hidden id="zhUser.id" name="zhUser.id" />
									<s:hidden id="zhUser.userid" name="zhUser.userid" />
									<s:hidden id="zhUser.uid" name="zhUser.uid" />

									<s:textfield id="zhUser.name" name="zhUser.name" />
								</td>
								<th>
									供应商全称
								</th>
								<td>
									<s:textfield id="zhUser.cmp" name="zhUser.cmp" />
								</td>
							</tr>
							<tr>
								<th>
									编号
								</th>
								<td>
									<s:textfield id="zhUser.usercode" name="zhUser.usercode" />
								</td>
								<th>
									物料类别
								</th>
								<td>
									<div class="zTreeDemoBackground left">
										<ul class="list">
											<li class="title">
												<input id="wgType" type="text" readonly="readonly"
													value="${zhUser.cclass}" name="zhUser.cclass" />
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
							<tr>
								<th>
									联系人
								</th>
								<td>
									<s:textfield id="zhUser.cperson" name="zhUser.cperson" />
								</td>
								<th>
									电话号
								</th>
								<td>
									<s:textfield id="zhUser.cfax" name="zhUser.cfax" />
								</td>
							</tr>
							<tr>
								<th>
									手机号
								</th>
								<td>
									<s:textfield id="zhUser.ctel" name="zhUser.ctel" />
								</td>
								<th>
									联系人电话
								</th>
								<td>
									<s:textfield id="zhUser.cmobile" name="zhUser.cmobile" />
								</td>
							</tr>
							<tr>
								<th>
									邮箱
								</th>
								<td>
									<s:textfield id="zhUser.yx" name="zhUser.yx" />
								</td>
								<th>
									公司地址
								</th>
								<td>
									<s:textfield id="zhUser.companydz" name="zhUser.companydz" />
								</td>
							</tr>
							<tr>
								<th>
									所能提供的商品
								</th>
								<td>
									<s:textfield id="zhUser.note" name="zhUser.note" />
								</td>
								<th>
									付款方式
								</th>
								<td>
									<s:textfield id="zhUser.fkfs" name="zhUser.fkfs" />
								</td>
							</tr>
							<tr>
								<th>
									增值税率(%)
								</th>
								<td>
									<s:textfield id="zhUser.zzsl" name="zhUser.zzsl" />
								</td>
								<th>
									税务登记号
								</th>
								<td>
									<s:textfield id="zhUser.djh" name="zhUser.djh" />
								</td>
							</tr>
							<tr>
								<th>
									营业执照
								</th>
								<td>
									<s:textfield id="zhUser.yyzz" name="zhUser.yyzz" />
								</td>
								<th>
									注册日期
								</th>
								<td>
									<s:textfield id="zhUser.time" name="zhUser.time" />
								</td>
							</tr>
							<tr>
								<th>
									失效日期
								</th>
								<td>
									<s:textfield id="zhUser.et" name="zhUser.et" />
								</td>
								<th>
									目前状况
								</th>
								<td>
									<s:textfield id="zhUser.blackliststauts"
										name="zhUser.blackliststauts" />
								</td>
							</tr>
							<tr>
								<th>
									最低起订量
								</th>
								<td>
									<input type="text" id="zhUser.zdqdl" value="${zhUser.zdqdl}"
										name="zhUser.zdqdl" />
								</td>
								<th>
									最低装箱量
								</th>
								<td>
									<input type="text" id="zhUser.zdzxl" value="${zhUser.zdzxl}"
										name="zhUser.zdzxl" />
								</td>
							</tr>
							<tr>
								<th>
									最低起送量
								</th>
								<td>
									<input type="text" id="zhUser.zdqsl" value="${zhUser.zdqsl}"
										name="zhUser.zdqsl" />
								</td>
								<th>
									ASL
								</th>
								<td>
									<select name="zhUser.asl" id="zhUser.asl">
											<option value="${zhUser.asl}">${zhUser.asl}</option>
											<option value="大批量">大批量</option>
											<option value="小批量">小批量</option>
											<option value="试制">试制</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									预付款比例
								</th>
								<td>
									<input type="text" id="yufuBiLi" name="zhUser.yufuBiLi" value="${zhUser.yufuBiLi}"/>
									<font style="color: red; size: 2px;" size="2px">*用于生成预付申请单</font>
								</td>
								<th>
									付款周期(票到)
								</th>
								<td>
									<input type="text" id="zhUser.fkZhouQi" name="zhUser.fkZhouQi"  value="${zhUser.fkZhouQi}"/>天
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
									<option value="${zhUser.isAuthentication}">${zhUser.isAuthentication}</option>
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
			</div>
			<%@include file="/util/foot.jsp"%>
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
