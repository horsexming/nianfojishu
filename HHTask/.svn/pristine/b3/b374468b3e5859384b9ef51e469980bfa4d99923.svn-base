<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<script src="http://pv.sohu.com/cityjson?ie=utf-8">
</script>
		<script type="text/javascript">
<%--if ('${strList1}'.indexOf('外部权限') < 0) {--%>
<%--	if (returnCitySN["cip"] != '${companyInfo.outIp}') {--%>
<%--		location.href = "<%=basePath%>/System/caiwu/findAllPrice_noWai.jsp";--%>
<%--	}--%>
<%--}--%>
</script>
		<style type="text/css">
table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
}

table th,table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}

.ztree li a {
	color: #fff;
}
</style>

	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center" class="transDiv" style="z-index: 2"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none; top: 20px;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<div id="submitProcessDiv" style="display: none;">
					<table style="width: 100%; margin-top: ">
						<tr>
							<td>
								您选择档案柜号:
							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									height="32" onclick="chageDiv('none');reload();">
							</td>
						</tr>
					</table>
					<div id="operatingDiv"
						style="background-color: #ffffff; width: 100%;">
						<form action="PriceAction!querenGuihao.action?statue=find&tags=${tags}" method="post" onsubmit="return addguihao()">
							<input type="hidden" id="wuId" name="price.id" />
							<table class="table" style="width: 40%" align="center">
								<tr>
									<th align="right">
										选择柜号:
									</th>
									<th align="left">
										<SELECT name="price.danganId" id="acceType" 
											style="width: 153px;">
											<option value="">--选择柜号--</option>
<%--												<s:iterator value="accessWebcamlist" id="accessWebcamtest">--%>
<%--													<option id="acceType"--%>
<%--														value="${accessWebcamtest.id}">--%>
<%--														${accessWebcamtest.cabinetNum}|${accessWebcamtest.cabinetType}--%>
<%--													</option>--%>
<%--												</s:iterator>--%>
<%--											</option>--%>
										</SELECT>
									</th>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<input type="submit" value="提交" style="width: 65px; height: 40px;" />
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;"
				align="left">
			</div>
			<div align="center">
				<div id="test" style="position: absolute; right: 5px; top: 5px;">
					<canvas id="myCanvas" width="200" height="100"></canvas>
				</div>
				<form
					action="PriceAction!findPriceWeiCun.action?statue=find&tags=${tags}"
						method="post" id="myform">
					<h3>
						未归档文件查询
					</h3>
					<table width="99%">
						<tr>
							<td align="right">
								件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:
								<br />
								(Item Number)
							</td>
							<td>
								<input type="text" style="width: 150px;" name="price.partNumber"
									value="${price.partNumber}">
							</td>
							<td align="right">
								名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称:
								<br />
								(Name)
							</td>
							<td>
								<input type="text" style="width: 150px;" name="price.name">
							</td>
							<td align="right">
								产品类别:
								<br />
								(Product Type)
							</td>
							<td>
								<select style="width: 150px;" name="price.productCategory">
									<option></option>
									<s:iterator value="strList1" id="str">
										<s:if test="#str == '总成价格'">
											<option>
												总成
											</option>
										</s:if>
										<s:elseif test="#str == '外购价格'">
											<option>
												外购
											</option>
										</s:elseif>
										<s:elseif test="#str == '外委价格'">
											<option>
												外委
											</option>
										</s:elseif>
										<s:elseif test="#str == '其他价格'">
											<option>
												其他
											</option>
										</s:elseif>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">
								合同编号
								<br>
								(Contract Number)
							</td>
							<td>
								<input type="text" style="width: 150px;"
									name="price.contractNumber" value="${price.contractNumber}">
							</td>
							<td align="right">
								档案号
								<br>
								(file Number)
							</td>
							<td>
								<input type="text" style="width: 150px;" name="price.fileNumber"
									value="${price.fileNumber}">
							</td>
							<td align="right">
								存档类型
								<br>
								(Archiving Type)
							</td>
							<td>
								<SELECT name="price.cunType" id="cunType">
									<option value="">
										--请选择存档类型--
									</option>
									<option value="电子档">
										电子档
									</option>
									<option value="原件">
										原件
									</option>
									<option value="复印件">
										复印件
									</option>
								</SELECT>
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input type="submit" value="查询select"
										style="width: 100px; height: 40px">
									<input type="reset" value="重置 submit"
										style="width: 100px; height: 40px">
							</td>
						</tr>
					</table>
				</form>
				<br>
				<center>
					<table width="99%">
						<tr align="center" bgcolor="#c0dcf2" height="50px">
							<td>
								序号
							</td>
							<td>
								类别
							</td>
							<td width="120px">
								名称
							</td>
							<td width="100px">
								签订方
							</td>
							<td>
								合同编号
							</td>
							<td width="120px">
								档案编号
							</td>
							<td>
								存档类型
							</td>
							<td>
								录入时间
							</td>
							<td>
								存档状态
							</td>
							<td>
								操作
							</td>
						</tr>
						<s:iterator id="unpriceList1" value="unpriceList"
							status="statussdf">
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
								${unpriceList1.produceType}
							</td>
							<td align="left" width="120px" style="font-size: 5px;">
								${unpriceList1.name}
							</td>
							<td align="left" width="80px">
								${unpriceList1.qidingfang}
							</td>
							<td>
								${unpriceList1.contractNumber}
							</td>
							<td width="120px">
								${unpriceList1.fileNumber}
							</td>
							<td>
								${unpriceList1.cunType}
							</td>
							<td>
								${unpriceList1.writeDate}
							</td>
							<td>
								${unpriceList1.cunRen} 
								${unpriceList1.danganWeizhi} 
								${unpriceList1.cunStatus}
							</td>
							<td>
								<s:if test='#unpriceList1.isCunType=="yes"&&#unpriceList1.cunStatus!="存档中"&&#unpriceList1.cunStatus!="已存档"'>
									<a onclick="submitqueren('${unpriceList1.id}')">选择存档柜号</a>
								</s:if>
								<s:elseif test='(#unpriceList1.cunType=="原件"||#unpriceList1.cunType=="复印件")&&(#unpriceList1.cunStatus=="存档中"||#unpriceList1.cunStatus=="已存档")'>
									${unpriceList1.cunStatus}
								</s:elseif>
								<s:elseif test='#unpriceList1.cunType!=null&&#unpriceList1.cunStatus!="存档中"'>
									待上传加章后文件
								</s:elseif>
<%--								<a--%>
<%--									href="PriceAction!findPriceById.action?id=${unpriceList1.id}&statue=${statue}">明细</a>--%>
<%--								<a--%>
<%--									href="PriceAction!deletePrice.action?id=${unpriceList1.id}&statue=${statue}">删除</a>--%>
								<br />
							</td>
						</s:iterator>
						<tr>
							<td colspan="11" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />

							</td>
						</tr>
					</table>
				</center>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<SCRIPT type="text/javascript">
var parentdocument =  window.parent.document;
var offset=0;
$(function(){
	var oDiv=document.getElementById("test"); 
	var  obj =window.parent.document.getElementById("showAll");
	if(obj!=null){
		offset = obj.offsetTop; 
	}
})
parentdocument.onscroll = function(){
	var oDiv=document.getElementById("test"); 
	var parenttop = parentdocument.body.scrollTop;
		parenttop = parenttop-offset;
		if(parenttop<0){
			parenttop = 10;
		}
	oDiv.style.top=parenttop;
}
window.onscroll=function(){ 
	var oDiv=document.getElementById("test"); 
	oDiv.style.top=document.body.scrollTop + 10;  //控制上下位置
} 
$(function(){
	var c=document.getElementById("myCanvas");
	var ctx=c.getContext("2d");
 	ctx.strokeStyle = 'red';
	ctx.font="30px Arial";
	ctx.strokeText("${Users.code} ${Users.name}",10,50);
})


function selectall(){
	var myform = document.getElementById("myform");
	myform.action = "PriceAction!findPriceByCondition.action?statue=ALL&tags=${tags}";
	myform.submit();
	myform.action = "PriceAction!findPriceByCondition.action?statue=find&tags=${tags}";
}
$(function(){
	if (/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
		$.ajax( {
		type : "POST",
		url : "PriceAction!findPhoneqx.action",
		data : {},
		dataType : "json",
		success : function(data) {
		if(data ==null || data.length == 0){
			window.location.href="<%=basePath%>/System/caiwu/price_noShow.jsp";
		}

		}
	})
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

//下载合同
	function xiazai(url){
			//对中文进行加密
			//var fileName1 = encodeURI(encodeURI("${list.priceUrl}"));
			var fileName1 = url;
<%--			location.href="<%=request.getContextPath()%>/DownAction.action?fileName="+fileName1;--%>
			location.href="<%=request.getContextPath()%>/FileViewAction.action?FilePath="+"/upload/file/"+fileName1;
	}
 function exportExcel(obj){
		obj.action = "PriceAction!exportExcel.action?statue=find";
	 	obj.submit();
	  	obj.action = "PriceAction!findPriceByCondition.action?statue=find";
	}
 function submitqueren(wuId) {
	$("#wuId").val(wuId);
	guihao($("#wuId").val());
	$("#submitProcessDiv").show();
	chageDiv("block");
	//单独设置弹出层的高度
<%--	var thisTopHeight = $(obj).offset().top;--%>
<%--	$('#contentDiv').css( {--%>
<%--		'top' : thisTopHeight + 'px'--%>
<%--	});--%>
}

function addguihao(){
	if (!validateText("acceType", "柜号不能为空")) {
		return false;
	}
}

function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}

function guihao(id){
	$.ajax( {
		url : "AccessEquipmentAction!findDescriptionById.action",
		type : 'post',
		dataType : 'json',
		cache : false,//防止数据缓存
		data : {
			id1 : id
		},
		success : function(useradsfa) {
			$("#acceType").empty();//清空
			if(useradsfa==null||useradsfa==''){
				$("<option value=''>无</option>").appendTo("#acceType");
			}else{
<%--				var se = useradsfa.split('|');--%>
				$(useradsfa).each(
					function() {
						$(
						"<option value='"
									+ this.id + "'>"
									+ this.cabinetNum + "|" 
									+ this.cabinetType
									+ "</option>")
						.appendTo("#acceType")
					}
<%--					function() {--%>
<%--						$(--%>
<%--							"<option value='"--%>
<%--								+ this + "'>"--%>
<%--								+ this--%>
<%--								+ "</option>")--%>
<%--							.appendTo("#acceType")--%>
<%--					}--%>
				);
				$("#acceType").tinyselect();
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
}
</SCRIPT>
		</center>
	</body>
</html>
