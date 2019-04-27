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
		<script src="<%=basePath%>/javascript/jquery.percentageloader-0.1.js">
</script>
		<script src="<%=basePath%>/javascript/radialIndicator.js">
</script>
		<script type="text/javascript"
			src="<%=basePath%>/javascript/popwin.js">
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div align="center">
				<form action="orderManager_queryOrderManagerByCondition.action"
					method="post">
					<input value="${flag}" name="flag" type="hidden">
					<input value="${status}" name="status" type="hidden">
					<table class="table">
						<tr>
							<th colspan="6" align="center">
								订单管理
								<s:if test="tag=='KH'">
									(客户)
								</s:if>
							</th>
						</tr>
						<tr>
							<th align="right">
								订单编号(内/外)：
							</th>
							<td>
								<input type="text" name="orderNum" value="${orderNum}" />
							</td>
							<th align="right">
								客户：
							</th>
							<td>
								<select name="customeId" class='cxselect' style="width: 155px">
									<option value="0" selected="selected">
										选择用户
									</option>
									<s:iterator id="c" value="clients">
										<s:if test="#c.id == customeId">
											<option value="${c.id}" selected="selected">
												${c.clientcompanyname}|${c.number}
											</option>
										</s:if>
										<s:else>
											<option value="${c.id}">
												${c.clientcompanyname}|${c.number}
											</option>
										</s:else>
									</s:iterator>
								</select>
							</td>
							<th align="right">
								跟单人：
							</th>
							<td>
								<input type="text" name="documentaryPeople"
									value="${documentaryPeople}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								月份：
							</th>
							<td>
								<input style="width: 155px" class="Wdate" type="text"
									name="paymentDate" value="${paymentDate}"
									onclick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
							</td>
							<th align="right">
								开始日期：
							</th>
							<td>
								<input style="width: 155px" class="Wdate" type="text"
									name="beginTime" value="${beginTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th align="right">
								结束日期：
							</th>
							<td>
								<input style="width: 155px" class="Wdate" type="text"
									name="endTime" value="${endTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<th align="right">
								件号：
							</th>
							<td>
								<input type="text" name="markId" value="${markId}" />
							</td>
							<th align="right">
								订单类型
							</th>
							<td>
								<SELECT name="om.orderType">
									<option value="${om.orderType}">
										${om.orderType}
									</option>
									<option value=""></option>
									<option value="正式">
										正式
									</option>
									<option value="备货">
										备货
									</option>
									<option value="预测">
										预测
									</option>
								</SELECT>
							</td>
							<th align="right">
							</th>
							<td>
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input type="hidden" name="errorMessage" value="all" />
								<input type="hidden" name="tag" value="${tag}" />
								<input type="submit" value="查询"
									style="width: 80px; height: 50px;" />
								<input type="button" value="导出" onclick="exprot(this.form);todisabledone(this)" data="downData"
									style="width: 80px; height: 50px;" />
								<s:if test="strList1!=null && strList1.size()>0 ">
									<input type="button" value="添加订单"
										style="width: 80px; height: 50px;"
										onclick="add('${flag}','${tag}','${status}')" />
								</s:if>

								<%--<a href="orderManager_updateOrder.action">更新数据</a>
							--%>
							</td>
						</tr>
					</table>
				</form>
				<form action="orderManager_pladdorder.action" method="post"
					enctype="multipart/form-data">
					选择导入文件:
					<input type="file" name="addorder">
					<a href="<%=basePath%>/upload/file/download/zaitudingdan.xls">导入模版下载</a>
					<a
						href="FileViewAction.action?FilePath=/upload/file/download/zaitudingdan.xls&Refresh=true">/预览</a>
					<input type="submit" value="批量导入" id="sub">
				</form>
				<table class="table">
					<tr>
						<th colspan="111">
							<font color="red">${successMessage}</font>
						</th>
					</tr>
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<th align="center">
							订单编号(内部)
							<hr />
							<font size="1px">业务件号</font>
						</th>
						<th align="center">
							订单编号(外部)
							<hr />
							<font size="1px">件号</font>
						</th>
						<th align="center">
							客户公司
							<hr />
							<font size="1px">订单数量</font>
						</th>
						<th align="center">
							下单人
							<hr />
							<font size="1px">转换数量</font>
						</th>
						<th align="center">
							审批状态
							<hr />
							<font size="1px">入库数量</font>
						</th>
						<th align="center" style="width: 60px;">
							采购进度
							<hr />
							<font size="1px">出库数量</font>
						</th>
						<th align="center" style="width: 60px;">
							生产进度
							<hr />
							<font size="1px">开票数量</font>
						</th>
						<th align="center" style="width: 60px;">
							入库率
							<hr />
							<font size="1px">单位</font>
						</th>
						<th align="center" style="width: 60px;">
							完成率
							<hr />
							<font size="1px">名称</font>
						</th>
						<th align="center" style="width: 60px;">
							及时率
						</th>
						<th align="center" style="width: 60px;">
							回款率
						</th>
						<th align="center" style="width: 60px;">
							下单时间
							<hr />
							交付日期
						</th>
						<s:if test="flag=='dj' || tag == 'sh' || tag == 'yc' ">
							<th></th>
						</s:if>
						<s:else>
							<th align="center" style="width: 60px;">
								成本动态
							</th>
						</s:else>
					</tr>
					<s:iterator value="list" id="pageList" status="pageindex">
						<s:if test="pageStatus!='zhuanhuan'">
							<s:if test="#pageindex.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')"
									onclick="clickBgcolor(this,'#D1EEEE');">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')"
									onclick="clickBgcolor(this,'#D1EEEE');">
							</s:else>
							<td rowspan="${pageList[24]}">
								<s:if test='tag=="yc"'>
									<input type="radio" value="${pageList[0]}" name="ycorder" id=""
										onclick="quedingyc(this,'${pageList[1]}')" />
								</s:if>
								<s:property value="#pageindex.index+1" />
							</td>
							<td>
								<a href="javascript:;"
									onclick="showOrderDetail(${pageList[0] },'${flag}','shenpi',null,'${tag}','${status}')">
									${pageList[1] }</a>

							</td>
							<td>
								${pageList[12] }
							</td>
							<td
								style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
								<a href="javascript:;" onclick="showKefu('${pageList[16] }')"
									title="123123"> ${pageList[3] }</a>
								<ul class="qs_ul">
									<li>
										公司名称:${pageList[3]}
									</li>
								</ul>
							</td>
							<td>
								<a href="javascript:;" onclick="showKefu('${pageList[16] }')">
									${pageList[6] }</a>
							</td>
							<td>
								<s:if test="#pageList[20]!=null">
									<a
										href="CircuitRunAction_findAduitPage.action?id=${pageList[20]}">
										${pageList[19] }</a>
								</s:if>
								<s:else>
										${pageList[19]}
									</s:else>
							</td>
							<td>
								<div class="cgjindu" style="cursor: pointer;"
									data="${pageList[26]}" onclick="jindu('${pageList[0] }')">
								</div>
							</td>
							<td>
								<div class="gxjindu" style="cursor: pointer;"
									data="${pageList[23]}" onclick="jindu('${pageList[0] }')">
								</div>
							</td>
							<td>
								<div class="rkjindu" style="cursor: pointer;"
									data="${pageList[18]}" onclick="jindu('${pageList[0] }')">
								</div>
							</td>
							<td>
								<div class="scjindu" style="cursor: pointer;"
									data="${pageList[8] }" onclick="jindu('${pageList[0] }')">
								</div>
							</td>
							<td>
								<div class="jsljindu" style="cursor: pointer;"
									data="${pageList[15] }" onclick="jindu('${pageList[0] }')">
								</div>
							</td>
							<td>
								<div class="hkjindu" style="cursor: pointer;"
									data="${pageList[9] }" onclick="hkjindu('${pageList[0] }')">
								</div>
							</td>
							<td align="left">
								${pageList[17]}
							</td>
							<s:if test="flag=='dj' || tag == 'sh' || tag == 'yc' ">
								<td>
									<s:if
										test="#pageList[19]=='未申请'||#pageList[19]==null||#pageList[19]=='打回'||tags=='admins'">
										<input type="button" value="订单明细"
											style="width: 70px; height: 30px;"
											onclick="detail(${pageList[0] },'${flag}',null,'${cpage}','${tag}','${status}')" />
									</s:if>
									<s:else>
										<input type="button" value="订单明细"
											style="width: 70px; height: 30px;"
											onclick="detail(${pageList[0] },'${flag}','shenpi',null,'${tag}','${status}')" />
									</s:else>
								</td>
							</s:if>
							<s:else>
								<td>
									<a href="orderManager_toTemplate.action?id=${pageList[0] }"
										target="_showChengben">实时统计</a>
								</td>
							</s:else>
							</tr>
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this,'#F0F8FF')"
								onmouseout="outBgcolor(this,'')"
								onclick="clickBgcolor(this,'#D1EEEE');">
								<td rowspan="1">
									<s:property value="#pageindex.index+1" />
								</td>
								<td>
									${pageList.orderNum }
								</td>
								<td>
									${pageList.outOrderNumber }
								</td>
								<td>
									<a href="javascript:;"
										onclick="showKefu('${pageList.custome.id }')">
										${pageList.custome.number }</a>
								</td>
								<td>
									<a href="javascript:;"
										onclick="showUser('${pageList.documentaryPeople }')">
										${pageList.documentaryPeople}</a>
								</td>
								<td>
									${pageList.ep_statuts }
								</td>
								<td>
									<div class="cgjindu" style="cursor: pointer;"
										data="${pageList.inputRate}"
										onclick="jindu('${pageList.id }')">
									</div>
								</td>
								<td>
									<div class="gxjindu" style="cursor: pointer;"
										data="${pageList.inputRate}"
										onclick="jindu('${pageList.id }')">
									</div>
								</td>
								<td>
									<div class="rkjindu" style="cursor: pointer;"
										data="${pageList.cgjindu}" onclick="jindu('${pageList.id}')">
									</div>
								</td>
								<td>
									<div class="scjindu" style="cursor: pointer;"
										data="${pageList.completionrate}"
										onclick="jindu('${pageList.id}')">
									</div>
								</td>
								<td>
									<div class="jsljindu" style="cursor: pointer;"
										data="${pageList.kprate}" onclick="jindu('${pageList.id}')">
									</div>
								</td>
								<td>
									<div class="hkjindu" style="cursor: pointer;"
										data="${pageList.hkrate}" onclick="jindu('${pageList.id }')">
									</div>
								</td>

								<%--								<s:if test="#pageList.totalAmount == 0.0">--%>
								<%--									<td>--%>
								<%--										0.00--%>
								<%--									</td>--%>
								<%--								</s:if>--%>
								<%--								<s:else>--%>
								<%--									<td>--%>
								<%--										${pageList.totalAmount }--%>
								<%--									</td>--%>
								<%--								</s:else>--%>
								<%--								<td>--%>
								<%--									${pageList.addTime }--%>
								<%--								</td>--%>
								<%--								<td>--%>
								<%--									${pageList.paymentDate }--%>
								<%--								</td>--%>
								<%--								<td>--%>
								<%--									${pageList.deliveryStatus }--%>
								<%--								</td>--%>
								<%--								<td>--%>
								<%--									<div class="hkjindu" style="cursor: pointer;"--%>
								<%--										data="${pageList.hkrate }"--%>
								<%--										onclick="hkjindu('${pageList.id }')">--%>
								<%--									</div>--%>
								<%--								</td>--%>
								<%--								<td>--%>
								<%--									<a--%>
								<%--										href="CircuitRunAction_findAduitPage.action?id=${pageList.epId }">审批动态</a>--%>
								<%----%>
								<%--								</td>--%>
								<s:if test="flag=='dj'">
									<td>
										<s:if
											test="#pageList.ep_statuts=='未审批'||#pageList[19]==null||#pageList[19]=='打回'">
											<input type="button" value="订单明细"
												style="width: 70px; height: 30px;"
												onclick="detail(${pageList.id },'${flag}',null,'${cpage}','${tag}','${status}')" />
										</s:if>
										<s:else>
											<input type="button" value="订单明细"
												style="width: 70px; height: 30px;"
												onclick="detail(${pageList.id },'${flag}','shenpi','${tag}')" />
										</s:else>
									</td>
								</s:if>
								<s:else>
									<a href="orderManager_toTemplate.action?id=${pageList.id}"
										target="_showChengben">实时统计</a>
								</s:else>
							</tr>
						</s:else>
						<s:iterator value="#pageList[25]" id="pagePm"
							status="pagepmStatus">
							<s:if test="#pageindex.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')"
									onclick="clickBgcolor(this,'#D1EEEE');">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')"
									onclick="clickBgcolor(this,'#D1EEEE');">
							</s:else>
								<td style="color: green">
									${pagePm.ywMarkId}
								</td>
								<td>
									${pagePm.pieceNumber}
								</td>
								<td>
									${pagePm.num}
								</td>
								<td>
									${pagePm.hasTurn}
								</td>
								<td>
									${pagePm.allocationsNum}
								</td>
								<td>
									${pagePm.sellCount}
								</td>
								<td>
									${pagePm.kpNumber}
								</td>
								<td>
									${pagePm.danwei}
								</td>
								<td colspan="3" style="font-size: 1px;">
									${pagePm.name}
								</td>
								<td colspan="1">
									${pagePm.paymentDate }
								</td>
								<s:if test='#pagePm.status=="取消"'>
									<td style="background-color: red" align="center">
								</s:if>
								<s:elseif test='#pagePm.status=="计划完善"'>
									<td style="background-color: yellow" align="center">
								</s:elseif>
								<s:elseif test='#pagePm.status=="已转完"'>
									<td style="background-color: #32CD32" align="center">
								</s:elseif>
								<s:elseif test='#pagePm.status=="未转完"'>
									<td style="background-color: #9AFF9A" align="center">
								</s:elseif>
								<s:else>
									<td align="center">
								</s:else>
									<s:if test="#pagePm.status=='取消'">
										${pagePm.status}(${pagePm.removeDate})
									</s:if>
									<s:else>
										${pagePm.status}
									</s:else>
								</td>

							</tr>
						</s:iterator>
						<s:iterator value="#pageList.pmList" id="pagePm"
							status="pagepmStatus">
							<tr style="background-color: #F0F8FF">
								<td></td>
								<td style="color: green">
									${pagePm.ywMarkId}
								</td>
								<td>
									${pagePm.pieceNumber}
								</td>
								<td>
									${pagePm.num}
								</td>
								<td>
									${pagePm.hasTurn}
								</td>
								<td>
									${pagePm.allocationsNum}
								</td>
								<td>
									${pagePm.sellCount}
								</td>
								<td>
									${pagePm.kpNumber}
								</td>
								<td>
									${pagePm.danwei}
								</td>
								<td colspan="15" style="font-size: 1px;">
									${pagePm.name}
								</td>
							</tr>
						</s:iterator>
					</s:iterator>

					<tr>
						<s:if test="errorMessage==null">
							<td colspan="19" align="right">
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
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function add(flag, tag, status) {
	window.location = "orderManager_initAdd.action?flag=" + flag + "&tag="
			+ tag + "&status=" + status;
}
function detail(id, flag, status, cpage, tag, sz) {
	if (status != null && status != "") {
		window.location = "orderManager_queryDetail.action?id=" + id
				+ "&status=" + status + "&flag=" + flag + "&tag=" + tag
				+ "&sz=" + sz + "&tags=${tags}";
	} else {
		window.location = "orderManager_queryDetail.action?id=" + id + "&flag="
				+ flag + "&cpage=" + cpage + "&tag=" + tag + "&sz=" + sz;
	}
}
function jindu(id) {
	window.open(
			"orderManager_showOrderDetils.action?tag=${tag}&flag=${flag}&id="
					+ id, '_showJindu')
}
function hkjindu(id) {
	window.open("orderManager_showOrderHKDetils.action?id=" + id, '_showJindu')
}

$(function() {
	//入库率

	$(".cgjindu").each(function(i) {
		var hk_val = $(this).attr('data');
		$(this).radialIndicator( {
			barColor : ( {
				0 : '#FF0000',
				66 : '#FFFF00',
				100 : '#33CC33'
			}),
			barWidth : 3,
			radius : 22,
			initValue : hk_val,
			roundCorner : true,
			percentage : true
		});
	});

	$(".gxjindu").each(function(i) {
		var hk_val = $(this).attr('data');
		$(this).radialIndicator( {
			barColor : ( {
				0 : '#FF0000',
				66 : '#FFFF00',
				100 : '#33CC33'
			}),
			barWidth : 3,
			radius : 22,
			initValue : hk_val,
			roundCorner : true,
			percentage : true
		});
	});
	$(".rkjindu").each(function(i) {
		var hk_val = $(this).attr('data');
		$(this).radialIndicator( {
			barColor : ( {
				0 : '#FF0000',
				66 : '#FFFF00',
				100 : '#33CC33'
			}),
			barWidth : 3,
			radius : 22,
			initValue : hk_val,
			roundCorner : true,
			percentage : true
		});
	});
	//完成率
	$(".scjindu").each(function(i) {
		var hk_val = $(this).attr('data');
		$(this).radialIndicator( {
			barColor : ( {
				0 : '#FF0000',
				66 : '#FFFF00',
				100 : '#33CC33'
			}),
			barWidth : 3,
			radius : 22,
			initValue : hk_val,
			roundCorner : true,
			percentage : true
		});
	});
	//及时率
	$(".jsljindu").each(function(i) {
		var hk_val = $(this).attr('data');
		$(this).radialIndicator( {
			barColor : ( {
				0 : '#FF0000',
				66 : '#FFFF00',
				100 : '#33CC33'
			}),
			barWidth : 3,
			radius : 22,
			initValue : hk_val,
			roundCorner : true,
			percentage : true
		});
	});
	//回款率
	$(".hkjindu").each(function(i) {
		var hk_val = $(this).attr('data');
		$(this).radialIndicator( {
			barColor : ( {
				0 : '#FF0000',
				66 : '#FFFF00',
				100 : '#33CC33'
			}),
			barWidth : 3,
			radius : 22,
			initValue : hk_val,
			roundCorner : true,
			percentage : true
		});
	});

})

function showUser(usersName) {
	usersName = encodeURI(encodeURI(usersName));
	popWin.showWin("1024", "700", "您正在查看员工信息",
			"UsersAction!findUserByName.action?name=" + usersName
					+ "&pageStatus=show");
}

function showKefu(id) {
	popWin.showWin("1024", "700", "您正在查看客户信息", "clientManager_infor.action?id="
			+ id);
}

function showOrderDetail(id, flag, status, cpage, tag, sz) {
	if (status != null && status != "") {
		popWin.showWin("1024", "700", "您正在查看客户信息",
				"orderManager_queryDetail.action?id=" + id + "&status="
						+ status + "&flag=" + flag + "&tag=" + tag);
	} else {
		popWin.showWin("1024", "700", "您正在查看客户信息",
				"orderManager_queryDetail.action?id=" + id + "&flag=" + flag
						+ "&cpage=" + cpage + "&tag=" + tag + "&sz=" + sz);
	}
}

function quedingyc(obj, orderNum) {
	if (obj != null && obj.value != '') {
		$("#orderId", window.parent.document).val(obj.value);
		$("#ycorderNum", window.parent.document).val(orderNum);
		parent.window.chageDiv('none');
	}
}
function exprot(obj) {
	$(obj).attr('action', 'orderManager_export.action');
	$(obj).submit();
	$(obj)
			.attr('action',
					'orderManager_queryOrderManagerByCondition.action?flag=dj&status=${status}');
}
</script>
	</body>
</html>
