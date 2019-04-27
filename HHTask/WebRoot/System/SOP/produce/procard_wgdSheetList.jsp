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

td:hover .qs_ul {
	display: block;
}

.qs_ul {
	display: none;
	border: 1px solid #999;
	list-style: none;
	margin: 0;
	padding: 0;
	position: absolute;
	width: auto;
	background: #CCC;
	color: green;
}
</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="WaigouwaiweiPlanAction!findWgDSheetList.action"
					method="post">
					<table class="table">
						<tr>
							<th align="right">
								件号
							</th>
							<td>
								<input type="text" name="wgdSheet.markId"
									value="${wgdSheet.markId}" />
							</td>
							<th align="right">
								产品名称
							</th>
							<td>
								<input type="text" name="wgdSheet.proName"
									value="${wgdSheet.proName}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								检验批次
							</th>
							<td>
								<input type="text" name="wgdSheet.examineLot"
									value="${wgdSheet.examineLot}" />
							</td>
							<th align="right">
								来料日期
							</th>
							<td>
								<input type="text" name="wgdSheet.liaodate" class="Wdate"
									value="${wgdSheet.liaodate}"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<th align="right">
								采购订单号
							</th>
							<td>
								<input type="text" name="wgdSheet.cgOrderNum"
									value="${wgdSheet.cgOrderNum}" />
							</td>
							<th align="right">
								送货单号
							</th>
							<td>
								<input type="text" name="wgdSheet.shOrderNum"
									value="${wgdSheet.shOrderNum}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								添加日期从
							</th>
							<td>
								<input type="text" name="firsttime" class="Wdate"
									value="${firsttime}" class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
							</td>
							<th align="right">
								止
							</th>
							<td>
								<input type="text" name="endtime" class="Wdate"
									value="${endtime}"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<th align="right">
								供应商:
							</th>
							<td>
								<select name="wgdSheet.gysId" class="cxselect">
									<option></option>
									<s:iterator value="zhuserList" id="zhuser">
										<option value="${zhuser.id}">
											${zhuser.name}
										</option>
									</s:iterator>
								</select>
							</td>
							<th align="right">
								物料类别:
							</th>
							<td>
								<div class="zTreeDemoBackground left">
									<ul class="list">
										<li class="title">
											<input id="wgType" type="text" value="${wgdSheet.wgType}"
												style="width: 120px;" name="wgdSheet.wgType" />
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
						<th align="right">
								最终判定:
							</th>
							<td>
								 <select class="selectpicker show-tick form-control" name="wgdSheet.zzpd" data-live-search="true">
								 	<option value=""></option>
								 	<option value="wyzzpd">未有最终判定</option>
	    							<option value="合格">合格</option>
							   	 	<option value="不合格">不合格</option>
   							 </select>
							</td>
						</tr>
					</table>
					<input type="submit" value="查询" class="input" />
					<input type="button" value="导出" class="input"
						onclick="exportExcel(this.form);todisabledone(this)" data="downData" />
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							物料类别
						</th>
						<th>
							供应商
						</th>
						<th>
							件号
						</th>
						<th>
							送货单号
						</th>
						<th>
							采购订单号
						</th>
						<th>
							产品名称
						</th>
						<th>
							版本
						</th>
						<th>
							供料属性
						</th>
						<th>
							产品图号
						</th>
						<th>
							规格
							<th>
								来料数量
							</th>
							<th>
								检验批次
							</th>
							<th>
								来料报检日期
							</th>
							<th>
								初检人
							</th>
							<th>
								抽检时间
							</th>
							<th>
								抽检数量
							</th>
							<th>
								抽检合格数量
							</th>
							<th>
								抽检不合格数量
							</th>
							<th>
								确认时间
							</th>
							<th>
								确认人
							</th>
							<th>
								确认合格数量
							</th>
							<th>
								确认不合格数量
							</th>
							<th>
								最终判定
							</th>
							<th>
								操作
							</th>
					</tr>
					<s:iterator value="list" id="pagelist" status="statussdf">
						<s:if test="#statussdf.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#statussdf.index+1" />
						</td>
						<td>
							${pagelist.wgType}
						</td>
						<td>
							${pagelist.gysjc}
						</td>
						<td>
							${pagelist.markId}
						</td>
						<td
							style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							<font size="1">${pagelist.shOrderNum}</font>
							<ul class="qs_ul">
								<li>
									${pagelist.shOrderNum}
								</li>
							</ul>
						</td>
						<td
							style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							<font size="1">${pagelist.cgOrderNum}</font>
							<ul class="qs_ul">
								<li>
									${pagelist.cgOrderNum}
								</li>
							</ul>
						</td>
						<td>
							${pagelist.proName}
						</td>
						<td>
							${pagelist.banbenNumber}
						</td>
						<td>
							${pagelist.kgliao}
						</td>
						<td>
							${pagelist.tuhao}
						</td>
						<td
							style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							<font size="1">${pagelist.specification}</font>
							<ul class="qs_ul">
								<li>
									${pagelist.specification}
								</li>
							</ul>
						</td>
						<td>
							${pagelist.llNumber}
						</td>
						<td>
							${pagelist.examineLot}
						</td>
						<td>
							${pagelist.liaodate}
						</td>
						<td>
							${pagelist.cjUser}
						</td>
						<td>
							${pagelist.cjTime}
						</td>
						<td>
							${pagelist.cjNumber}
						</td>
						<td>
							${pagelist.cjhgNumber}
						</td>
						<td>
							${pagelist.cjbhgNumber}
						</td>
						<td>
							${pagelist.zjTime}
						</td>
						<td>
							${pagelist.zjUser}
						</td>
						<td>
							${pagelist.zjhgNumber}
						</td>
						<td>
							${pagelist.zjbhgNumber}
						</td>
						<td>
							${pagelist.zzpd}
						</td>
						<td>
							<a
								href="WaigouwaiweiPlanAction!gysTzview3.action?id=${pagelist.id}&pageStatus=noshow">图纸</a>/
							<a
								href="WaigouwaiweiPlanAction!findwgdSheetById.action?id=${pagelist.id}">明细</a>/
							<a
								href="WaigouwaiweiPlanAction!findwgdSheetById.action?id=${pagelist.id}&pageStatus=xg">修改</a>
						</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="30" align="right">
							第
							<font color="red"><s:property value="cpage" /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />

						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function exportExcel(obj) {
	obj.action = "WaigouwaiweiPlanAction!exportExcelwgdSheet.action";
	obj.submit();
	obj.action = "WaigouwaiweiPlanAction!findWgDSheetList.action";
}

//下拉树开始
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
			status : '物料类别'
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
//下拉树结束

</script>
	</body>
</html>
