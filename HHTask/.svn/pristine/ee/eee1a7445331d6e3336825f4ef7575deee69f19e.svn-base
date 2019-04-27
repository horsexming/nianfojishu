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
		<script language="javascript"
	src="${pageContext.request.contextPath}/javascript/xuanzekuang.js">
</script>
		<STYLE type="text/css">
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

.dhlabel {
	border-top: 1px solid #000;
	border-bottom: 1px solid #000;
	border-left: 1px solid #000;
	border-right: 1px solid #000;
	margin-left: 5px;
	margin-right: 5px;
	padding: 3px 5px;
	white-space: nowrap;
}

#fullbg1 {
	background-color: gray;
	left: 0;
	opacity: 0.5;
	position: absolute;
	top: 0;
	z-index: 3;
	filter: alpha(opacity =               50);
	-moz-opacity: 0.5;
	-khtml-opacity: 0.5;
}

#dialog1 {
	background-color: #fff;
	border: 5px solid rgba(0, 0, 0, 0.4);
	left: 50%;
	margin: -200px 0 0 -200px;
	padding: 1px;
	position: fixed !important; /* 浮动对话框 */
	position: absolute;
	top: 45%;
	width: 400px;
	height: 100px;
	z-index: 5;
	border-radius: 5px;
	display: none;
}

#xiugaiIframe1 {
	background-color: #fff;
	height: 75px;
	line-height: 24px;
	width: 400px;
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
							<span id="title">您正在进行在制品报废申请操作</span>
							<font id="font" color="red" size="5"></font>
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
			<font style="font-size: 22px; font-weight: bolder;"> 库存质检管理 </font>
			<br />
			<br />
			<s:if test="pagestatus=='ydq'">
				<label style="background-color: gray;" class="dhlabel">
					已到期
				</label>
			</s:if>
			<s:else>
				<label style="background-color: #5cb85c;" onclick="toShowWW('ydq');"
					class="dhlabel">
					<font color="white">已到期</font>
				</label>
			</s:else>
			<s:if test="pagestatus=='jdq'">
				<label style="background-color: gray;" class="dhlabel">
					将到期
				</label>
			</s:if>
			<s:else>
				<label style="background-color: #5cb85c;" onclick="toShowWW('jdq');"
					class="dhlabel">
					<font color="white">将到期</font>
				</label>
			</s:else>
			<s:if test="pagestatus=='wdq'">
				<label style="background-color: gray;" class="dhlabel">
					未到期
				</label>
			</s:if>
			<s:else>
				<label style="background-color: #5cb85c;" onclick="toShowWW('wdq');"
					class="dhlabel">
					<font color="white">未到期</font>
				</label>
			</s:else>
			<font id="zhiti" color="red" size="5"></font>
			<s:if test="tag==null || tag!='pingmu'">
				<form action="goodsAction!goodsZJ.action" method="post">
					<table class="table" style="width: 95%;">
						<tr>
							<th align="right">
								批次
							</th>
							<td>
								<input type="text" name="goods.goodsLotId"
									value="${goods.goodsLotId }" />
							</td>
							<th align="right">
								件号
							</th>
							<td>
								<input type="text" name="goods.goodsMarkId"
									value="${goods.goodsMarkId }" />
							</td>
						</tr>
						<tr>
							<th align="right">
								仓区
							</th>
							<td>
								<select id="warehouseArea" name="goods.goodHouseName">
									<option value="${goods.goodHouseName}">
										${goods.goodHouseName}
									</option>
									<option value="">

									</option>
								</select>
								<input type="hidden" value="${goods.goodHouseName}" id="cangqu" />
							</td>
							<th align="right">
								状态
							</th>
							<td>
								<select name="tag">
									<option></option>
									<option value="已到期">
										已到期
									</option>
									<option value="将到期">
										将到期
									</option>
									<option value="未到期">
										未到期
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								供应商
							</th>
							<td>
								<SELECT id="gys" name="goods.goodsSupplier">
									<option></option>
								</SELECT>
							</td>
							<td></td>
							<td></td>
						</tr>
					</table>
					<input type="hidden" value="${role}" name="role" />
					<input type="hidden" value="${pagestatus}" name="pagestatus" />
					<input type="submit" value="查找" class="input" />
					<input type="button" value="导出" class="input"
						onclick="exportEXCELZj(this.form);todisabledone(this)" data="downData" />
					<input type="button" value="一键提醒" class="input"
						onclick="window.location.href='goodsAction!sendmsg.action'" />
				</form>
			</s:if>
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
						物料类别
					</th>
					<th align="center">
						供料属性
					</th>
					<th align="center">
						品名
					</th>
					<th align="center">
						工序名
					</th>
					<s:if test="tag==null || tag!='pingmu'">
						<th align="center">
							供应商
						</th>
					</s:if>
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
						转换数量
					</th>
					<th align="center">
						入库时间
					</th>
					<th align="center">
						质检周期(天)
					</th>
					<th align="center">
						上次质检时间
					</th>
					<th align="center">
						到期时间
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
						状态
					</th>
					<s:if test="tag==null || tag!='pingmu'">
						<th align="center">
							操作
						</th>
						<th align="center">
							检验提醒
						</th>
						<th align="center">
							检验记录
						</th>
					</s:if>
				</tr>
				<s:iterator value="ydqList" status="see" id="gs">
					<s:if test="#see.first">
						<tr bgcolor="red">
							<th colspan="25" align="center">
								<font color="#000000">质检已到期的库存记录</font>
							</th>
						</tr>
					</s:if>
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
					<td align="left">
						${gs.goodsMarkId}
					</td>
					<td align="left">
						${gs.wgType}
					</td>
					<td align="left">
						${gs.kgliao}
					</td>
					<td
						style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
						<font size="1">${gs.goodsFullName}</font>
						<ul class="qs_ul">
							<li>
								${gs.goodsFullName}
							</li>
						</ul>
					</td>
					<td align="left">
						${gs.processName}
					</td>
					<s:if test="tag==null || tag!='pingmu'">
						<td align="left">
							${gs.goodsSupplier}
						</td>
					</s:if>
					<td align="left"
						style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
						<font size="1">${gs.goodsFormat}</font>
						<ul class="qs_ul">
							<li>
								${gs.goodsFormat}
							</li>
						</ul>
					</td>
					<td>
						${gs.banBenNumber}
					</td>
					<td>
						${gs.goodsCurQuantity}
					</td>
					<td>
						${gs.goodsZhishu}
					</td>
					<td>
						${gs.goodsChangeTime}
					</td>
					<td>
						${gs.goodsround}
					</td>
					<td>
						${gs.goodslasttime}
					</td>
					<td>
						${gs.goodsnexttime}
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
						<s:if test="#gs.fcStatus=='封存'">
							<font color="red">封存</font>
						</s:if>
						<s:else>可用</s:else>
					</td>
					<s:if test="tag==null || tag!='pingmu'">
						<td>
							<a href="javaScript:;"
								onclick="tocheck(${gs.goodsId}
						,'${gs.goodsMarkId}','${gs.goodsLotId}')">发起检验</a>
						</td>
						<td>
							<a href="goodsAction!sendmsg.action?id=${gs.goodsId}">检验提醒</a>
						</td>
						<td>
							<a href="OsRecord_list.action?pageStatus=cq&record.markId=${gs.goodsMarkId}
							&record.jcpc=${gs.goodsLotId}">检验记录</a>
						</td>
					</s:if>
					</tr>
				</s:iterator>
				<s:if test="pagestatus!=null && pagestatus=='ydq' && total>0">
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
				</s:if>
				<s:iterator value="dqList" status="see" id="gs0">
					<s:if test="#see.first">
						<tr bgcolor="yellow">
							<th colspan="25" align="center">
								<font color="#000000">质检即将到期的库存记录</font>
							</th>
						</tr>
					</s:if>
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
						${gs0.goodsLotId}
					</td>
					<td align="left">
						${gs0.goodsMarkId}
					</td>
					<td align="left">
						${gs0.wgType}
					</td>
					<td align="left">
						${gs0.kgliao}
					</td>
					<td align="left"
						style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
						<font size="1">${gs0.goodsFullName}</font>
						<ul class="qs_ul">
							<li>
								${gs0.goodsFullName}
							</li>
						</ul>

					</td>
					<td align="left">
						${gs0.processName}
					</td>
					<s:if test="tag==null || tag!='pingmu'">
						<td align="left">
							${gs0.goodsSupplier}
						</td>
					</s:if>
					<td
						style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
						<font size="1">${gs0.goodsFormat}</font>
						<ul class="qs_ul">
							<li>
								${gs0.goodsFormat}
							</li>
						</ul>
					</td>
					<td>
						${gs0.banBenNumber}
					</td>
					<td>
						${gs0.goodsCurQuantity}
					</td>
					<td>
						${gs0.goodsZhishu}
					</td>
					<td>
						${gs0.goodsChangeTime}
					</td>
					<td>
						${gs0.goodsround}
					</td>
					<td>
						${gs0.goodslasttime}
					</td>
					<td>
						${gs0.goodsnexttime}
					</td>
					<td>
						${gs0.goodsClass}
					</td>
					<td>
						${gs0.goodHouseName}
					</td>
					<td>
						${gs0.goodsPosition}
					</td>
					<td>
						${gs0.goodsArtsCard}
					</td>
					<td>
						<s:if test="#gs0.fcStatus=='封存'">
							<font color="red">封存</font>
						</s:if>
						<s:else>可用</s:else>
					</td>
					<s:if test="tag==null || tag!='pingmu'">
						<td>
							<a href="javaScript:;"
								onclick="tocheck(${gs0.goodsId}
						,'${gs0.goodsMarkId}','${gs0.goodsLotId}')">发起检验</a>
						</td>
						<td>
							<a href="goodsAction!sendmsg.action?id=${gs0.goodsId}">检验提醒</a>
						</td>
						<td>
							<a href="OsRecord_list.action?pageStatus=cq&record.markId=${gs0.goodsMarkId}
							&record.jcpc=${gs0.goodsLotId}">检验记录</a>
						</td>
					</s:if>
					</tr>
				</s:iterator>
				<s:if test="{list.size()>0}">
					<s:iterator value="list" status="see" id="gs1">
						<s:if test="#see.first">
							<tr bgcolor="green">
								<th colspan="25" align="center">
									<font color="#000000">质检未到期</font>
								</th>
							</tr>
						</s:if>
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
							${gs1.goodsLotId}
						</td>
						<td align="left">
							${gs1.goodsMarkId}
						</td>
						<td align="left">
							${gs1.wgType}
						</td>
						<td align="left">
							${gs1.kgliao}
						</td>
						<td align="left"
							style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							<font size="1">${gs1.goodsFullName}</font>
							<ul class="qs_ul">
								<li>
									${gs1.goodsFullName}
								</li>
							</ul>
						</td>
						<td align="left">
							${gs1.processName}
						</td>
						<s:if test="tag==null || tag!='pingmu'">
							<td align="left">
								${gs1.goodsSupplier}
							</td>
						</s:if>
						<td align="left"
							style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							<font size="1">${gs1.goodsFormat}</font>
							<ul class="qs_ul">
								<li>
									${gs1.goodsFormat}
								</li>
							</ul>
						</td>
						<td>
							${gs1.banBenNumber}
						</td>
						<td>
							${gs1.goodsCurQuantity}
						</td>
						<td>
							${gs1.goodsZhishu}
						</td>
						<td>
							${gs1.goodsChangeTime}
						</td>
						<td>
							${gs1.goodsround}
						</td>
						<td>
							${gs1.goodslasttime}
						</td>
						<td>
							${gs1.goodsnexttime}
						</td>
						<td>
							${gs1.goodsClass}
						</td>
						<td>
							${gs1.goodHouseName}
						</td>
						<td>
							${gs1.goodsPosition}
						</td>
						<td>
							${gs1.goodsArtsCard}
						</td>
						<td>
							<s:if test="#gs1.fcStatus=='封存'">
								<font color="red">封存</font>
							</s:if>
							<s:else>可用</s:else>
						</td>
						<s:if test="tag==null || tag!='pingmu'">
							<td>
								<a href="javaScript:;"
									onclick="tocheck(${gs1.goodsId}
								,'${gs1.goodsMarkId}','${gs1.goodsLotId}')">发起检验</a>
							</td>
							<td>
							</td>
							<td>
							<a href="OsRecord_list.action?pageStatus=cq&record.markId=${gs1.goodsMarkId}
							&record.jcpc=${gs1.goodsLotId}">检验记录</a>
						</td>
						</s:if>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="30" align="right">
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
				<%--				<s:else>--%>
				<%--					<tr>--%>
				<%--						<td colspan="18" style="font-size: 15px; color: red;">--%>
				<%--							对不起，没有查到相关的库存信息--%>
				<%--						</td>--%>
				<%--					</tr>--%>
				<%--				</s:else>--%>

			</table>
		</div>
		<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function exportExcel(objForm) {
	objForm.action = "goodsAction!exportEXCEL.action?tag=goodsDetail";
	objForm.submit();
	window.confirm(arg0)
}
function tanchu(num) {
	document.getElementById("xiugaiIframe").src = "goodsAction!getOneGoods?id="
			+ num + "&tag=baofeishenqin";
	chageDiv('block')
}

function qrzj(id) {
	$.ajax( {
		type : "POST",
		url : "goodsAction!QRgoodsZJ.action",
		data : {
			id : id
		},
		dataType : "json",
		success : function(data) {
			if (data == "true") {
				window.location.reload();
				document.getElementById("zhiti").innerHTML = "确认成功!";
			} else {
				$("#zhiti").html(data);
			}
		}

	});

}

function sqbljy(id, markId, lotId) {
	if (confirm('你确定要申请检验该库存?此操作会将件号:' + markId + '批次:' + lotId
			+ '，下所有库存暂时转移到不合格品库。')) {
		window.location.href = "goodsAction!bljysq.action?id=" + id;
	}
}

$(function() {
	getCangqu();
	getgys();
})
function getCangqu() {

	var cangqu = $("#cangqu").val();
	$.ajax( {
		type : "POST",
		url : "WarehouseAreaAction_findwaListByNO.action",
		data : {
			wareHouseName : '外购件库'
		},
		dataType : "json",
		success : function(data) {
			$("#warehouseArea").empty();
			$("#warehouseArea").append(
					'<option value=' + cangqu + '>' + cangqu + '</option>')
			$("#warehouseArea").append('<option value="">请选择仓区</option>')
			if (data != null) {
				$(data)
						.each(
								function() {
									$("#warehouseArea").append(
											'<option value='
													+ this.goodHouseName + '>'
													+ this.goodHouseName
													+ '</option>');
								});
				$("#warehouseArea").tinyselect();
			}
		}
	});
}
function exportEXCELZj(obj) {
	$(obj).attr("action", "goodsAction!exportEXCELZj.action")
	$(obj).submit();
	$(obj).attr("action", "goodsAction!goodsZJ.action")
}
function toShowWW(pagestatus) {
	window.location.href = "goodsAction!goodsZJ.action?tag=${tag}&pagestatus="
			+ pagestatus+"&role=${role}";
}
function tocheck(id, markId, lotId){
	aa.id=id;
	aa.markId =markId;
	aa.lotId =lotId;
	aa.content = '该库存是否合格?';
	aa.buutonstr1='合格';
	aa.buutonstr2 ='不合格';
	aa.xuanzeOne = qrzj;
	aa.xuanzetwo =sqbljy;
	aa.tocheck();
}

function getgys(){
		$.ajax( {
		type : "POST",
		url : "PriceAction!findAllZhUser.action",
		dataType : "json",
		success : function(data) {
			$("#gys").empty();
			$("#gys").append("<option value=''>选择供应商</option>");
			if(data!=null){
				$(data).each(function(){
					$("#gys").append('<option value='+this.cmp+'>'+this.usercode+'_'+this.name+'</option>');
				})
			}
			$("#gys").tinyselect();
		}
	})
}
</script>
	</body>
</html>
