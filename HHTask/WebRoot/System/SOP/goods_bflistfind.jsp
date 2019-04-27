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
/* 带复选框的下拉框 */
ul li {
	list-style: none;
	padding: 0px;
	margin: 0px;
}

.select_checkBox {
	border: 0px solid red;
	position: relative;
	display: inline-block;
}

.chartQuota {
	height: 23px;
	float: left;
	display: inline-block;
	border: 0px solid black;
	position: relative;
}

.chartOptionsFlowTrend {
	z-index: 300;
	background-color: white;
	border: 1px solid gray;
	display: none;
	position: absolute;
	left: 0px;
	top: 23px;
	width: 150px;
}

.chartOptionsFlowTrend ul {
	float: left;
	padding: 0px;
	margin: 5px;
}

.chartOptionsFlowTrend li { /* float:left; */
	display: block;
	position: relative;
	left: 0px;
	margin: 0px;
	clear: both;
}

.chartOptionsFlowTrend li * {
	float: left;
}

a:-webkit-any-link {
	color: -webkit-link;
	text-decoration: underline;
	cursor: auto;
}

.chartQuota p a {
	float: left;
	height: 21px;
	outline: 0 none;
	border: 1px solid #ccc;
	line-height: 22px;
	padding: 0 5px;
	overflow: hidden;
	background: #eaeaea;
	color: #313131;
	text-decoration: none;
}

.chartQuota p {
	margin: 0px;
	folat: left;
	overflow: hidden;
	height: 23px;
	line-height: 24px;
	display: inline-block;
}

.chartOptionsFlowTrend p {
	height: 23px;
	line-height: 23px;
	overflow: hidden;
	position: relative;
	z-index: 2;
	background: #fefbf7;
	padding-top: 0px;
	display: inline-block;
}

.chartOptionsFlowTrend p a {
	border: 1px solid #fff;
	margin-left: 15px;
	color: #2e91da;
}
</STYLE>
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
							<span id="title">您正在进行在制品报废申请操作</span>
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
		<div id="gongneng">
		</div>

		<div align="center">
			<h2>
				库存管理
			</h2>
			<form action="goodsAction!findGoods.action?" method="post">
				<table class="table" style="width: 95%;">
					<tr>
						<th>
							批次:
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
					</tr>
					<tr>
						<tr>
						<th>
							库别：
						</th>
						<td>
							<select name="goods.goodsClass" id="whView" style="width: 155px;"
								onchange="changvalue(this)">
								<option value="">
									选择库别
								</option>
								<option value="${goods.goodsClass }" selected="selected">
									${goods.goodsClass }
								</option>
							</select>
						</td>
						<th>
							仓区:
						</th>
						<td>
							<select id="goodHouseName" name="goods.goodHouseName"
								style="width: 155px;">
								<option>
									${goods.goodHouseName}
								</option>
								<option></option>
							</select>
						</td>
					</tr>
				</table>
				<input type="submit" value="查找"
								style="width: 50px; height: 30px；; margin-top: 5px;" />
							&nbsp;
							<input type="hidden" name="pagestatus" value="zz"/>
							<input type="button" value="导出" onclick="exportExcel(this.form);todisabledone(this)" data="downData"
								style="width: 45px; height: 30px；; margin-top: 5px;" />
							&nbsp;
			</form>
			<table class="table" style="width: 95%;">
				<tr bgcolor="#c0dcf2" height="30px"
					style="border-collapse: separate;">
					<th align="center">
						序号
					</th>
					<th align="center">
						批次
					</th>
					<th align="center">
						件号
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
						支数
					</th>
					<th align="center">
						状态
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
							${gs.goodsLotId}
						</td>
						<td>
							${gs.goodsMarkId}
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
						   <s:if test="#gs.fcStatus=='封存'">
						    <font color="red">封存</font>
						   </s:if>
						   <s:else>可用</s:else>
						</td>
						<td>
							<a href="javaScript:;" onclick="tanchu(${goodsId})">申请报废</a>
						</td>

						</tr>
					</s:iterator>
					<tr>
						<td colspan="17" align="right">
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
						<td colspan="18" style="font-size: 15px; color: red;">
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
$(function(){
	addSelect();
})
function exportExcel(objForm) {
	objForm.action = "goodsAction!exportEXCEL.action?tag=goodsDetail";
	objForm.submit();
	window.confirm(arg0)
}
function tanchu(num){
	document.getElementById("xiugaiIframe").src="goodsAction!getOneGoods.action?id="+num+"&tag=baofeishenqin";
		chageDiv('block')
}

function changvalue(obj) {
	if (obj != null && obj.value != "") {
		$.ajax( {
			type : "POST",
			url : "WarehouseAreaAction_findwaListByNO.action",
			data : {
				wareHouseName : obj.value
			},
			dataType : "json",
			success : function(data) {
				if (data != null && data.length > 0) {
					$("#goodHouseName").empty();
					$(data).each(
							function() {
								$("#goodHouseName").append(
										'<option value=' + this.goodHouseName
												+ '>' + this.goodHouseName
												+ '</option>');
							});
					var duoxuanselect = $(".duoxuaselect_div");
					if (duoxuanselect[1] != null) {
						$(duoxuanselect[1]).remove();
					}
					duoxuaSelect("goodHouseName");
				}
			}
		});
	}
}
function addSelect() {
	$
			.ajax( {
				type : "POST",
				url : "GoodsStoreAction!getViewAuth.action",
				data : {},
				dataType : "json",
				success : function(msg) {
					if (msg.success) {
						for (k in msg.data) {
							$('#whView').append(
									"<option>" + msg.data[k] + "</option>");
						}
					} else {
						alert(msg.message);
					}
					duoxuaSelect("whView", '${goods.goodsClass}');
				}
			});

}


</script>
	</body>
</html>
