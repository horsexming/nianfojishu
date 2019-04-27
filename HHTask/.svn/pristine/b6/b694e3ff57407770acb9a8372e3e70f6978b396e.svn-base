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
		</div>

		<div align="center">
			<h2>
				库存管理
			</h2>
			<form action="goodsAction!showDtcGoodsList.action" method="post">
				<table class="table" style="width: 95%;">
					<tr>
						<th>
							送货批次:
						</th>
						<td>
							<input type="text" name="goods.goodsLotId"
								value="${goods.goodsLotId }" />
						</td>
						<th>
							件号:
						</th>
						<td>
							<input type="text" name="goods.goodsMarkId"
								value="${goods.goodsMarkId }" />
						</td>
						<th>
							品名:
						</th>
						<td>
							<input type="text" name="goods.goodsFullName"
								value="${goods.goodsFullName }" />
						</td>
						
					</tr>
					<tr>
						<th>
							库别：
						</th>
						<td>
							<select name="goods.goodsClass" id="goodsClass"
								onMouseOver="createDept('goodsClass','goodsAction!findSelectList.action?tag=goodsClass')">
								<option value="">
									选择库别
								</option>
								<option value="${goods.goodsClass }" selected="selected">
									${goods.goodsClass }
								</option>
							</select>
						</td>
						<th>
							仓区：
						</th>
						<td>
							<input type="text" name="goods.goodHouseName"
								value="${goods.goodHouseName}" />
						</td>
						<th>
							库位:
						</th>
						<td>
							<input type="text" name="goods.goodsPosition"
								value="${goods.goodsPosition}" />
						</td>
						
					</tr>
					<tr>
					<th>
							工艺卡号:
						</th>
						<td>
							<input type="text" name="goods.goodsArtsCard"
								value="${goods.goodsArtsCard }" />
						</td>
						<th>
							日期从
						</th>
						<td>
							<input class="Wdate" type="text" name="startDate"
								value="${startDate}" size="15"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
						</td>
						<th>
							到
						</th>
						<td>
							<input class="Wdate" type="text" name="endDate"
								value="${ endDate}" size="15"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
						</td>
					</tr>
					<th>
						规格：
					</th>
					<td>
						<input type="text" name="goods.goodsFormat"
							value="${goods.goodsFormat }" />
					</td>
					<th>
						入库类型
					</th>
					<td>
						<select name="goods.goodsStyle">
							<option></option>
							<option>
								${goods.goodsStyle}
							</option>
							<option>
								批量入库
							</option>
							<option>
								试制入库
							</option>
							<option>
								返修入库
							</option>
							<option>
								退货入库
							</option>
						</select>
					</td>
					<th>
						积压状态
					</th>
					<td>
						<select name="goods.more">
							<option></option>
							<option value="">
								否
							</option>
							<option value="O">
								是
							</option>
						</select>
					</td>
					</tr>
					<tr>
						<th>
							调仓类型:
						</th>
						<td>
							<input type="text" name="goods.dtcFlag"
								value="${goods.dtcFlag}" />
						</td>
						<th>
							供料属性:
						</th>
						<td>
							<select name="goods.kgliao" id="kgliao2">
											<option></option>
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
						<th>
							物料类别:
						</th>
						<td>
							<div class="zTreeDemoBackground left">
									<ul class="list">
										<li class="title">
											<input id="wgType" type="text" readonly="readonly" value="${goods.wgType}"
												style="width: 120px;" name="goods.wgType"/>
											<a id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a>((按住Ctrl建不松点击,可清空)
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
					<tr>
					<th>
							客户:
						</th>
						<td>
							<input type="text" name="goods.goodsCustomer"
								value="${goods.goodsCustomer }" />
						</td>
					</tr>
					<th colspan="6">
							<input type="submit" value="查找"
								style="width: 50px; height: 30px；; margin-top: 5px;" />
							&nbsp;
							<input type="button" value="导出" onclick="exportExcel(this.form);todisabledone(this)" data="downData"
								style="width: 45px; height: 30px；; margin-top: 5px;" />
							&nbsp;

						</th>
					</tr>
				</table>
			</form>
			<table class="table" style="width: 95%;">
				<tr bgcolor="#c0dcf2" height="30px"
					style="border-collapse: separate;">
					<th align="center">
						序号
					</th>
					<th>
						物料类别
					</th>
					<th align="center">
						内部订单号
					</th>
					<th align="center">
						业务件号
					</th>
					<th align="center">
						批次
					</th>
					<th align="center">
						件号
					</th>
					<th align="center">
						生产批次
					</th>
					<th align="center">
						品名
					</th>
					<th align="center">
						规格
					</th>
					<th align="center">
						版本
					</th>
					<th align="center">
						供料属性
					</th>
					<th align="center">
						数量
					</th>
					<th align="center">
						单位
					</th>
					<th align="center">
						客户
					</th>
					<th align="center">
						库别
					</th>
					<th align="center">
						仓区
					</th>
					<th align="center">
						库位
					</th>
					<th align="center">
						计划单号
					</th>
					<th align="center">
						入库时间
					</th>
					<th align="center">
						入库类型
					</th>
					<th align="center">
						转换数量
					</th>
					<th align="center">
						转换单位
					</th>
					<th align="center">
						状态
					</th>
					<th align="center">
						封存审批
					</th>
					<th align="center">
						操作
					</th>
				</tr>
				<s:if test="{list.size()>0}">
					<s:iterator value="list" status="see" id="gs">
						<s:if test="#see.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#see.index+1" />
						</td>
						<td>
							${gs.wgType}
						</td>
						<td>
							${gs.neiorderId}
						</td>
						<td>
							${gs.ywmarkId}
						</td>
						<td>
							${gs.goodsLotId}
						</td>
						<td>
							${gs.goodsMarkId}<s:if test="#gs.processNo!=null"><font color="red">(工序:${gs.processNo})</font></s:if>
						</td>
						<td>
							${gs.wxselfCard}
						</td>
						<td>
							${gs.goodsFullName}
						</td>
						<td>
							${gs.goodsFormat}
						</td>
						<td>
							${gs.banBenNumber}
						</td>
						<td>
												<s:if test="#gs.kgliao=='TK'">
										自购(TK)
										</s:if>
												<s:elseif test="#gs.kgliao=='TK AVL'">
										指定供应商(TK AVL)
										</s:elseif>
												<s:elseif test="#gs.kgliao=='CS'">
										客供(CS)
										</s:elseif>
												<s:elseif test="#gs.kgliao=='TK Price'">
										完全指定(TK Price)
										</s:elseif>
						</td>
						<td>
							${gs.goodsCurQuantity}
						</td>
						<td>
							${gs.goodsUnit}
						</td>
						<td>
							${gs.goodsCustomer}
						</td>
						<td>
							${gs.goodsClass}
						</td>
						<td>
							${gs.goodHouseName}
						</td>
						<td>
							${gs.goodsPosition}
						</td>
						<td>
							${gs.goodsArtsCard}
						</td>
						<td>
							${gs.goodsChangeTime}
						</td>
						<td>
							${gs.goodsStyle}
						</td>
						<td>
							${gs.goodsZhishu}
						</td>
						<td>
							${gs.goodsStoreZHUnit}
						</td>
						<td>
						   <s:if test="#gs.fcStatus=='封存'">
						    <font color="red">封存</font>
						   </s:if>
						   <s:else>可用</s:else>
						</td>
						<td>
						   <s:if test="#gs.fcApplyStatus!=null">
							${gs.fcApplyStatus}
						   </s:if>
						</td>
						<td>
							<s:if test="true==#gs.bout">
								<a href="goodsAction!getdtcGoods.action?id=${gs.goodsId}">调仓</a>
								</s:if>
							<s:if test="true==#gs.bedit">
								<a
									href="goodsAction!getOneGoods.action?id=${gs.goodsId}&tag=update">修改</a>
							</s:if>

							<a href="goodsAction!deletegs.action?goods.goodsId=${gs.goodsId}"
								onclick="return confirm('本次操作将会删除相关所有记录,确认执行删除操作?');">删除</a>
							<s:if test="#gs.fcApplyStatus!=null&&#gs.fcApplyStatus!=''">
							<a href="CircuitRunAction_findAduitPage.action?id=${gs.epId}">审批动态</a>
							</s:if>
							<s:if test="#gs.fcStatus=='封存'">
							 <s:if test="#gs.fcApplyStatus!='审批中'">
							  <a href="javaScript:;if(window.confirm('您将申请解封此库存?')){window.location.href ='goodsAction!goodsApplyFcStatus.action?id=${goodsId}'};">申请解封</a>
							 </s:if>
							</s:if>
							<s:else>
							<s:if test="#gs.fcApplyStatus!='审批中'">
							<a href="javaScript:;if(window.confirm('您将申请封存此库存?')){window.location.href ='goodsAction!goodsApplyFcStatus.action?id=${goodsId}'};">申请封存</a>
							 </s:if>
							</s:else>
						 </td>

						</tr>
					</s:iterator>
					<tr>
						<td colspan="26" align="right">
							共
				                			<s:property value="total" />
							页 第
							<s:property value="cpage" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />

						</td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td colspan="26" style="font-size: 15px; color: red;">
							对不起，没有查到相关的库存信息
						</td>
					</tr>
				</s:else>

			</table>
		</div>
		<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function exportExcel(objForm) {
	objForm.action = "goodsAction!exportEXCEL.action?tag=dtc";
	objForm.submit();
	objForm.action = "goodsAction!showDtcGoodsList.action";
	window.confirm(arg0)
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
