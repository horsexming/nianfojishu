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
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="yuanclAndWaigjAction_toupdate.action?yuanclAndWaigj.id=<s:property value='yuanclAndWaigj.id'/>"
						style="color: #ffffff">刷新<br />(reflesh)</a>
				</div>
			</div>

			<div align="center">
				<h3>
					外购件明细
				</h3>
				<h3>
					<font color="red">${successMessage}</font>
				</h3>
				<form action="yuanclAndWaigjAction_update.action" method="post"
					onsubmit="return validate()">
					<input type="hidden" name="yuanclAndWaigj.id"
						value="<s:property value='yuanclAndWaigj.id'/>" />
					<input type="hidden" name="productStyle"
						value="<s:property value='productStyle'/>" />
					<input type="hidden" name="cpage"
						value="<s:property value='cpage'/>" />
					<table class="table" id="mytable" >
						<tr>
							<th align="right">
								物料类别
							</th>
							<td>
								<div class="zTreeDemoBackground left">
									<ul class="list">
										<li class="title">
											<input id="wgType" type="text" readonly="readonly"
												value="${yuanclAndWaigj.wgType}" style="width: 120px;"
												name="yuanclAndWaigj.wgType" />
											<a id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a><font
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
							<th align="right">
								将要改成的物料类别
							</th>
							<td>
								${yuanclAndWaigj.newwgType}
							</td>
						</tr>
						<%--					<tr>--%>
						<%--							<th align="right">--%>
						<%--								材料类型--%>
						<%--							</th>--%>
						<%--							<td>--%>
						<%--							<SELECT name="yuanclAndWaigj.clClass" id="clClass" onchange="changeClClass()">--%>
						<%--							<option><s:property value="yuanclAndWaigj.clClass"/>--%>
						<%--							</option>--%>
						<%--							 <option>外购件--%>
						<%--							 </option>--%>
						<%--							 <option>原材料--%>
						<%--							 </option>--%>
						<%--							 <option>辅料--%>
						<%--							 </option>--%>
						<%--							</SELECT>--%>
						<%--							</td>--%>
						<%--						</tr>--%>
						<tr id="jianhaoTr">
							<th align="right">
								件号
							</th>
							<td>
								<input type="text" name="yuanclAndWaigj.markId" id="markId"
									value="<s:property value='yuanclAndWaigj.markId'/>" />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<th align="right">
								名称
								<br />
							</th>
							<td>
								<input type="text" name="yuanclAndWaigj.name" id="name"
									value="<s:property value='yuanclAndWaigj.name'/>" />
							</td>
						</tr>
						<tr>
							<th align="right">
								规格
								<br />
							</th>
							<td>
								<input type="text" name="yuanclAndWaigj.specification"
									id="specification"
									value="<s:property value='yuanclAndWaigj.specification'/>" />
							</td>
						</tr>
						<tr>
							<th align="right">
								材质
							</th>
							<td>
								<input type="text" name="yuanclAndWaigj.caizhi" id="caizhi"
									value="<s:property value='yuanclAndWaigj.caizhi'/>" />
							</td>
						</tr>
						<tr id="biliTr">
							<th align="right">
								单张重量
							</th>
							<td>
								<input type="text" name="yuanclAndWaigj.bili" id="bili"
									onkeyup="mustBeNumber('bili')"
									value="<s:property value='yuanclAndWaigj.bili'/>" />
								<font color="red">★★★★★</font>
								<br />
								需要持续消耗的材料,比如一张铁板/一卷钢卷/一根钢管,请务必填写其单件的重量
							</td>
						</tr>
						<tr>
							<th align="right">
								版本号
							</th>
							<td>
								<input type="text" name="yuanclAndWaigj.banbenhao"
									id="banbenhao"
									value="<s:property value='yuanclAndWaigj.banbenhao'/>" />
							</td>
						</tr>
						<%--						<tr id="paihaoTr">--%>
						<%--							<th align="right">--%>
						<%--								牌号--%>
						<%--							</th>--%>
						<%--							<td>--%>
						<%--								<input type="text" name="yuanclAndWaigj.trademark" id="trademark" value="<s:property value='yuanclAndWaigj.trademark'/>"  />--%>
						<%--							</td>--%>
						<%--						</tr>--%>
						<tr>
							<th align="right">
								BOM单位
							</th>
							<td>
								<select id="unit" name="yuanclAndWaigj.unit"
									style="width: 155px;">
									<option>
										<s:property value="yuanclAndWaigj.unit" />
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								仓库单位
							</th>
							<td>
								<select id="ckUnit" name="yuanclAndWaigj.ckUnit"
									style="width: 155px;">
									<option>
										<s:property value="yuanclAndWaigj.ckUnit" />
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								供料属性:
							</th>
							<td>
								<select name="yuanclAndWaigj.kgliao" style="width: 155px;"
									id="kgliao">
									<option value="${yuanclAndWaigj.kgliao}">
										${yuanclAndWaigj.kgliao}
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
							<th align="right">
								产品类型
							</th>
							<td>
								<s:if test="yuanclAndWaigj.productStyle == '试制'">
									<input type="radio" value="试制"
										name="yuanclAndWaigj.productStyle" checked="checked" />试制
								<input type="radio" value="批产"
										name="yuanclAndWaigj.productStyle" />批产
								</s:if>
								<s:else>
									<input type="radio" value="试制"
										name="yuanclAndWaigj.productStyle" />试制
								<input type="radio" value="批产"
										name="yuanclAndWaigj.productStyle" checked="checked" />批产
								</s:else>

							</td>
						</tr>
						<tr>
							<th align="right">
								质检周期:
							</th>
							<td>
								<input type="text" name="yuanclAndWaigj.round"
									value="${yuanclAndWaigj.round}" onchange="numyanzhen(this)" />
							</td>
						</tr>
						<tr>
							<th align="right">
								每公斤喷粉面积:
							</th>
							<td>
								<input type="text" name="yuanclAndWaigj.areakg"
									onchange="numyanzhen(this)" value="${yuanclAndWaigj.areakg}" />(㎡/kg)
							</td>
						</tr>
						<tr>
							<th align="right">
								密度:
							</th>
							<td>
								<input type="text" name="yuanclAndWaigj.density"
									onchange="numyanzhen(this)" value="${yuanclAndWaigj.density}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								重要性:
							</th>
							<td>
								<input type="text" name="yuanclAndWaigj.importance"  value="${yuanclAndWaigj.importance}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								图号
								<br />
							</th>
							<td>
								<input type="text" name="yuanclAndWaigj.tuhao" id="tuhao"
									value="<s:property value='yuanclAndWaigj.tuhao'/>" />
							</td>
						</tr>
						<tr>
							<th align="right">
								总成号
							</th>
							<td>
								<input type="text" name="yuanclAndWaigj.zcMarkId" id="zcMarkId"
									value="<s:property value='yuanclAndWaigj.zcMarkId'/>" />
							</td>
						</tr>
						<tr>
							<th align="right">
								最小库存
							</th>
							<td>
								<input type="text" name="yuanclAndWaigj.minkc" id="minkc" value="${yuanclAndWaigj.minkc}" />
									低于此库存，将自动采购
							</td>
						</tr>
						<tr>
							<th align="right">
								采购量
							</th>
							<td>
								<input type="text" name="yuanclAndWaigj.cgcount" id="cgcount" value="${yuanclAndWaigj.cgcount}" onchange="numyanzheng(this)" />
								安全库存自动采购时采购量
							</td>
						</tr>
						<tr>
							<th align="right">
								采购周期
							</th>
							<td>
								<input type="text" name="yuanclAndWaigj.cgperiod" id="cgperiod" value="${yuanclAndWaigj.cgperiod}" onchange="numyanzheng(this)" />
							</td>
						</tr>
						<tr>
							<th align="right">
								LT等级
							</th>
							<td>
								<SELECT  name="" id="waigoult" onchange="chnaglt(this)">
									<option value="${yuanclAndWaigj.ltdengji};${yuanclAndWaigj.ltuse}">${yuanclAndWaigj.ltdengji}</option>
								</SELECT>
								<input type="hidden" value="" id="ltdengji" name="yuanclAndWaigj.ltdengji" >
								<input type="hidden" value="" id="ltuse" name="yuanclAndWaigj.ltuse" >
							</td>
						</tr>
						<tr>
							<th align="right">
								备注
							</th>
							<td>
								<textarea rows="4" cols="70" name="yuanclAndWaigj.remark"
									id="remark"><s:property value="yuanclAndWaigj.remark" /></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<font color="red">★★★★★</font>表示重点注意以及识别
								<br />
								<font color="red">*</font>表示必填项
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
	var markId = document.getElementById("markId").value;
	var unit = document.getElementById("unit").value;
	var name = document.getElementById("name").value;
	var wgType = document.getElementById("wgType").value;
	var kgliao = document.getElementById("kgliao").value;
	if (wgType == "") {
		alert("请选择 物料类别");
		return false;
	} else if (markId == "") {
		alert("请填写件号");
		return false;
	} else if (name == "") {
		alert("请填写名称");
		return false;
	} else if (kgliao == "") {
		alert("请选择供料属性  ");
		return false;
	}
	document.getElementById("sub").disabled = "disabled";
}
function changeClClass() {
	var clClass = $("#clClass").val();
if(clClass=="外购件"){
		$("#jianhaoTr").show();
		$("#markId").removeAttr("disabled");
		$("#kg").show();
		$("#kgliao").removeAttr("disabled");
<%--		$("#paihaoTr").hide();--%>
<%--		$("#trademark").attr("disabled","disabled");--%>
		$("#biliTr").hide();
		$("#bili").attr("disabled","disabled");
	}else if(clClass=="原材料"){
<%--		$("#paihaoTr").show();--%>
<%--		$("#trademark").removeAttr("disabled");--%>
<%--		$("#jianhaoTr").hide();--%>
<%--		$("#markId").attr("disabled","disabled");--%>
		$("#biliTr").show();
		$("#bili").removeAttr("disabled");
		$("#kg").hide();
		$("#kgliao").attr("disabled","disabled");
	}else if(clClass=="辅料"){
		$("#paihaoTr").hide();
		$("#trademark").attr("disabled","disabled");
		$("#jianhaoTr").show();
		$("#markId").removeAttr("disabled");
		$("#biliTr").hide();
		$("#bili").attr("disabled","disabled");
		$("#kg").hide();
		$("#kgliao").attr("disabled","disabled");
	}
}
$(document).ready(function(){
	$("#wgType").focus();
	getUnit("unit");
	getUnit("ckUnit");
	getCaizhi("wgType");
	changeClClass()
})
function numyanzhen(obj) {
	var ty1 = '^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$';
	var re = new RegExp(ty1);
	if (obj != null && obj.value.match(re) == null) {
		obj.value = "";
		obj.focus();
		obj.select();
	} 
}


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
</script>
	</body>
</html>
