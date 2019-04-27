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
		<script type="text/javascript" src="javascript/fullcalendar-3.7.0/lib/jquery-ui.min.js"></script>
		<style type="text/css">
.dhlabel {
	width: auto;
	height: 30px;
	border-radius: 5px 5px 0px 0px;
	line-height: 30px;
	text-align: center;
	background: #eaeaea;
	display: inline-block;
	color: #000000;
	font-family: "Lucida Grande", Arial, sans-serif;
	font-size: 12px;
	font-weight: bold;
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
							<span id="title">修改供应商</span>
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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				采购订单模式
				<a
					href="WaigouwaiweiPlanAction!findWgPlanList.action?pageStatus=${pageStatus}&tag=${tag}&noOperation=${noOperation}">采购明细模式</a>
				<div align="right">
					<s:if
						test="pageStatus!='gysall'&&pageStatus!='gysnew'&&pageStatus!='sbdqr'&&pageStatus!='gyssbdqr'">
						<s:if test="pageStatus=='dsq'">
							<label style="background-color: #5cb85c;" class="dhlabel">
								<s:if test="tag=='fl'">
					辅料待核对
				</s:if>
								<s:else>
					外购待核对
				</s:else>
							</label>
						</s:if>
						<s:else>
							<label onclick="toShowWg('dsq','${tag}','${noOperation}');" class="dhlabel">
								<font class="dhlabel"> <s:if test="tag=='fl'">
					辅料待核对
				</s:if> <s:else>
					外购待核对
				</s:else> </font>
							</label>
						</s:else>
						<s:if test="pageStatus=='dtz'">
							<label style="background-color: #5cb85c;" class="dhlabel">
								<s:if test="tag=='fl'">
					辅料待通知
				</s:if>
								<s:else>
					外购待通知
				</s:else>
							</label>
						</s:if>
						<s:else>
							<label onclick="toShowWg('dtz','${tag}','${noOperation}');" class="dhlabel">
								<font class="dhlabel"> <s:if test="tag=='fl'">
					辅料待通知
				</s:if> <s:else>
					外购待通知
				</s:else> </font>
							</label>
						</s:else>
						<s:if test="pageStatus=='dqr'">
							<label style="background-color: #5cb85c;" class="dhlabel">
								<s:if test="tag=='fl'">
					辅料待确认
				</s:if>
								<s:else>
					外购待确认
				</s:else>
							</label>
						</s:if>
						<s:else>
							<label onclick="toShowWg('dqr','${tag}','${noOperation}');" class="dhlabel">
								<font> <s:if test="tag=='fl'">
					辅料待确认
				</s:if> <s:else>
					外购待确认
				</s:else> </font>
							</label>
						</s:else>
						<s:if test="pageStatus=='audit'">
							<label style="background-color: #5cb85c;" class="dhlabel">
								<s:if test="tag=='fl'">
					辅料待审批
				</s:if>
								<s:else>
					外购待审批
				</s:else>
							</label>
						</s:if>
						<s:else>
							<label onclick="toShowWg('audit','${tag}','${noOperation}');" class="dhlabel">
								<font> <s:if test="tag=='fl'">
					辅料待审批
				</s:if> <s:else>
					外购待审批
				</s:else> </font>
							</label>
						</s:else>
						<s:if test="pageStatus=='findAllself'">
							<label style="background-color: #5cb85c;" class="dhlabel">
								所有(个人)
							</label>
						</s:if>
						<s:else>
							<label onclick="toShowWg('findAllself','${tag}','${noOperation}');"
								class="dhlabel">
								<font>所有(个人)</font>
							</label>
						</s:else>
						<s:if test="pageStatus=='findAll'">
							<label style="background-color: #5cb85c;" class="dhlabel">
								所有订单
							</label>
						</s:if>
						<s:else>
							<label onclick="toShowWg('findAll','${tag}','${noOperation}');" class="dhlabel">
								<font>所有订单</font>
							</label>
						</s:else>
					</s:if>
				</div>
				<form
					action="WaigouwaiweiPlanAction!findWgOrderList.action?pageStatus=${pageStatus}&tag=${tag}&noOperation=${noOperation}"
					method="post" id="form">
					<table class="table">
						<tr>
							<th>
								供应商编号:
							</th>
							<td>
								<input name="waigouOrder.userCode"
									value="${waigouOrder.userCode}" />
							</td>
							<th>
								供应商名称:
							</th>
							<td>
								<input name="waigouOrder.gysName" value="${waigouOrder.gysName}" />
							</td>
							<th>
								件号:
							</th>
							<td>
								<input name="waigouOrder.addUserYx"
									value="${waigouOrder.addUserYx}" />
							</td>
						</tr>
						<tr>
							<th>
								负责采购姓名:
							</th>
							<td>
								<input name="waigouOrder.addUserName"
									value="${waigouOrder.addUserName}" />
							</td>
							<th>
								负责采购工号:
							</th>
							<td>
								<input name="waigouOrder.addUserCode"
									value="${waigouOrder.addUserCode}" />
							</td>
							<th>
								状态:
							</th>
							<td>
								<SELECT name="waigouOrder.status">
									<option value="${waigouOrder.status}">
										${waigouOrder.status}
									</option>
									<option value="">

									</option>
									<option value="待核对">
										待核对
									</option>
									<option value="待审批">
										待审批
									</option>
									<option value="待通知">
										待通知
									</option>
									<option value="待确认">
										待确认
									</option>
									<option value="待审核">
										待审核
									</option>
									<option value="生产中">
										生产中
									</option>
									<option value="送货中">
										送货中
									</option>
									<option value="待入库">
										待入库
									</option>
									<option value="入库">
										入库
									</option>
								</SELECT>
							</td>
						</tr>
						<tr>
							<th>
								采购月份:
							</th>
							<td>
								<input name="waigouOrder.caigouMonth"
									value="${waigouOrder.caigouMonth}" class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})" />
							</td>
							<th>
								开始时间:
							</th>
							<td>
								<input name="waigouOrder.addTime" value="${waigouOrder.addTime}"
									class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
							</td>
							<th>
								结束时间:
							</th>
							<td>
								<input name="waigouOrder.querenTime"
									value="${waigouOrder.querenTime}" class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<th>
								订单编号:
							</th>
							<td>
								<input name="waigouOrder.planNumber"
									value="${waigouOrder.planNumber}" />
							</td>
							<th>
								打印状态
							</th>
							<td>
								<SELECT name="waigouOrder.isprint">
									<option></option>
									<option value="${waigouOrder.isprint}">
										${waigouOrder.isprint}
									</option>
									<option value="是">
										是
									</option>
									<option value="否">
										否
									</option>
								</SELECT>
							</td>
							<th>
							</th>
							<td>
							</td>
						</tr>
						<tr>
							<th colspan="6">
								<input type="submit" value="查询" class="input">
								<input type="button" value="导出" class="input"
									onclick="clicks();todisabledone(this)" data="downData">
							</th>
						</tr>
					</table>
				</form>
				<form id="cgOrderForm" method="post">
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
							<s:if test="noOperation!='noOperation'">
								<input type="checkbox" onclick="chageAllCheck(this)"
									id="checkAll">
								全选
							</s:if>
							<s:else>序号</s:else>
							</th>
							<th align="center">
								供应商
							</th>
							<th align="center">
								采购月份
							</th>
							<th align="center">
								订单编号
							</th>
							<th align="center">
								状态
							</th>
							<th align="center">
								添加日期
							</th>
							<th align="center">
								联系人
							</th>
							<th align="center">
								通知日期
							</th>
							<th align="center">
								确认日期
							</th>
							<th align="center">
								打印状态
							</th>
							<th align="center">
								审批状态
							</th>
							<th align="center">
								操作
							</th>
						</tr>
						<s:if test="pageStatus=='dsq'">
							<tr>
								<th colspan="15"
									style="height: 35px; color: #ffffff; background-color: red;">
									待申请采购订单
								</th>
							</tr>
						</s:if>
						<s:elseif test="pageStatus=='dtz'">
							<tr>
								<th colspan="15"
									style="height: 35px; color: #ffffff; background-color: red;">
									待通知采购订单
								</th>
							</tr>
						</s:elseif>
						<s:elseif test="pageStatus=='dqr'">
							<tr>
								<th colspan="15"
									style="height: 35px; color: #ffffff; background-color: red;">
									待确认采购订单
								</th>
							</tr>
						</s:elseif>
						<s:elseif test="pageStatus=='gysnew'">
							<tr>
								<th colspan="15"
									style="height: 35px; color: #ffffff; background-color: red;">
									待确认采购订单
								</th>
							</tr>
						</s:elseif>
						<s:elseif test="pageStatus=='sbdqr'||pageStatus=='gyssbdqr'">
							<tr>
								<th colspan="15"
									style="height: 35px; color: #ffffff; background-color: red;">
									设变待确认订单
								</th>
							</tr>
						</s:elseif>
						<s:elseif test="pageStatus=='audit'">
							<tr>
								<th colspan="15"
									style="height: 35px; color: #ffffff; background-color: red;">
									待审批采购订单
								</th>
							</tr>
						</s:elseif>
						<s:else>
							<tr>
								<th colspan="15"
									style="height: 35px; color: #ffffff; background-color: green;">
									历史采购计划
								</th>
							</tr>
						</s:else>
						<s:iterator value="list" id="pageWgww" status="pageStatus">
							<s:if test="#pageStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 50px;" onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#pageStatus.index+1" />
								<s:if test="noOperation!='noOperation'">
									<input type="checkbox" name="processIds" value="${pageWgww.id}"
										onclick="chageNum(this)">
									<input type="hidden" id="s_${pageWgww.id}" value="${pageWgww.addUserCode}"
									>
								</s:if>
							</td>
							<td align="left">
								${pageWgww.gysName}
								<br />
								(${pageWgww.userCode})
							</td>
							<td>

								${pageWgww.caigouMonth}
							</td>
							<td>
								${pageWgww.planNumber}
							</td>
							<td>
								${pageWgww.status}
							</td>
							<td>
								${pageWgww.addTime}
							</td>
							<td>
								<%--<s:if test='#pageWgww.status=="待通知"'>
									${pageWgww.addUserName}
									<br />
									(${pageWgww.addUserCode})
								</s:if>
								<s:else>
									${pageWgww.tzUserName}
									<br />
									(${pageWgww.tzUserCode})
								</s:else>
							--%>
								${pageWgww.addUserName}
								<br />
								(${pageWgww.addUserCode})
							</td>
							<td>
								${pageWgww.tongzhiTime}
							</td>
							<td>
								${pageWgww.querenTime}
							</td>
							<td>
								<s:if test='#pageWgww.isprint == "是"'>
									是
								</s:if>
								<s:else>
									否
								</s:else>
							</td>
							<td>
								<s:if test="noOperation!='noOperation'">
								<s:if
									test="#pageWgww.epId!=null&&pageStatus!='gysall'&&pageStatus!='gysnew'&&pageStatus!='gyssbdqr'&&pageStatus!='gys'">
									<a
										href="CircuitRunAction_findAduitPage.action?id=${pageWgww.epId}">${pageWgww.applystatus}</a>
								</s:if>
								<s:else>${pageWgww.applystatus}</s:else>
								</s:if>
								<s:else>${pageWgww.applystatus}</s:else>
								
							</td>
							<td>
								<s:if test="noOperation!='noOperation'">
								<s:if
									test='#session.Users.code==#pageWgww.addUserCode&&(#pageWgww.status=="待通知"||#pageWgww.status=="待确认" || #pageWgww.status=="生产中")'>
									<a href="javascript:;"
										onclick="backApply(${pageWgww.id},'${tag}')">反审</a>/
								</s:if>
								<s:if test="pageStatus=='dtz'">
									<a
										href="WaigouwaiweiPlanAction!orderToTzGys.action?processIds=${pageWgww.id}&tag=${tag}">发送提醒</a>
								</s:if>
								<s:elseif test="pageStatus=='dsq'">
<!-- 									<input value="备注" type="button" style="font-size: 10px"  -->
										 
									<a href="#" id="remark${pageWgww.id}"
										onclick="addRemark(${pageWgww.id},this)">添加备注</a>
									<a
										href="WaigouwaiweiPlanAction!applyWWorder.action?id=${pageWgww.id}&cpage=${cpage}&tag=${tag}">申请审批</a>


								</s:elseif>
								<s:elseif test="pageStatus=='dqr'">
									<a
										href="WaigouwaiweiPlanAction!orderToTzGys.action?processIds=${pageWgww.id}&tag=${tag}">再次提醒</a>/
								<a
										href="WaigouwaiweiPlanAction!orderQueren.action?processIds=${pageWgww.id}&tag=${tag}">采购确认</a>/
								</s:elseif>
								<s:elseif test="pageStatus=='gys'">

								</s:elseif>
								<s:else>

								</s:else>
								<s:if test="#pageWgww.epId!=null">
									<%--								<a--%>
									<%--									href="WaigouwaiweiPlanAction!findWgPlanList.action?id=${pageWgww.id}&pageStatus=${pageStatus}">采购明细</a>/--%>
									<s:if test="pageStatus!='gysnew'">
										<a
											href="WaigouwaiweiPlanAction!gotoprint.action?processIds=${pageWgww.id}&pageStatus=waigou&tag=${pageStatus}">打印订单</a>
									/</s:if>
								</s:if>
								<a
									href="WaigouwaiweiPlanAction!findWgPlanList.action?id=${pageWgww.id}&pageStatus=${pageStatus}&tag=${tag}&noOperation=${noOperation}"><s:if
										test="pageStatus=='gysnew'">确认交期
									</s:if> <s:else>采购明细</s:else> </a>

								<s:if test="pageStatus=='dsq' && #pageWgww.applystatus!='同意'">
									<a onclick="return window.confirm('确定要删除该订单吗?')"
										href="WaigouwaiweiPlanAction!delWaigouOrder.action?waigouOrder.id=${pageWgww.id}&cpage=${cpage}&tag=${tag}&noOperation=${noOperation}">删除</a>
								</s:if>
								<s:if
									test='#pageWgww.applystatus == "打回" &&#pageWgww.addUserCode == #session.Users.code '>
									/<a
										href="WaigouwaiweiPlanAction!cxsqwaigouorder.action?id=${pageWgww.id}&tag=${tag}">重新申请</a>
								</s:if>
								</s:if>
								<s:else>
								<a
									href="WaigouwaiweiPlanAction!findWgPlanList.action?id=${pageWgww.id}&pageStatus=${pageStatus}&tag=${tag}&noOperation=${noOperation}">
									采购明细 </a>
								</s:else>
							</td>
							</tr>
						</s:iterator>
						
						<s:if test="noOperation!='noOperation'">
						<tr>
							<th>
								<input type="checkbox" onclick="chageAllCheck(this)"
									id="checkAll2">
								全选
							</th>
							<td colspan="19" align="left">
								<s:if test="pageStatus=='dtz'">
									<input id="yjtz" type="button"
										style="width: 100px; height: 50px;"
										onclick="toSubmit(this.form,'tz')" value="一键通知" />
								</s:if>
								<s:elseif test="pageStatus=='dqr'">
									<input id="yjqr" type="button"
										style="width: 100px; height: 50px;" value="一键确认"
										onclick="toSubmit(this.form,'qr')" />
								</s:elseif>
								<s:elseif test="pageStatus=='audit'">
									<input id="ok" class="input" style="width: 120px;" align="top"
										type="button" value="审批通过"
										onclick="toSubmit(this.form,'audit','ok')">
									<input id="ng" class="input" align="top" type="button"
										value="审批驳回" onclick="toSubmit(this.form,'audit','no')">
								</s:elseif>
								<s:elseif test="pageStatus=='dsq'">
									<input id="yjqr" type="button"
										style="width: 100px; height: 50px;" value="一键申请"
										onclick="toSubmit(this.form,'sq')" />
								</s:elseif>
								<input id="yjtz" type="button"
									style="width: 100px; height: 50px;"
									onclick="toSubmit(this.form,'print')" value="前往打印" />
							</td>
						</tr>
						</s:if>
						<tr>
							<td colspan="13" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</tr>
					</table>
				</form>
				
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function toSubmit(form, type, tag) {
	if (type == 'tz') {
		var b = true;//通知列表中只有自己的。
		var chekebox = document.getElementsByName("processIds");
		for ( var i = 0; i < chekebox.length; i++) {
			if (chekebox[i].checked == true) {
				var xb= chekebox[i].value;
				var ss =document.getElementById("s_" +xb).value;
				if(ss!='${Users.code}'){
					b = false;
				}
			}
		}
		if(b){
			form.action = "WaigouwaiweiPlanAction!orderToTzGys.action";
			form.submit();
		}else{
			if(confirm("所选订单中包括其他人添加的订单，请确认是否提交？")){
				form.action = "WaigouwaiweiPlanAction!orderToTzGys.action";
				form.submit();
			}else{
				return false;
			}
		}
	} else if (type == 'qr') {
		form.action = "WaigouwaiweiPlanAction!orderQueren.action?pageStatus=wg";
		form.submit();
	} else if (type == 'print') {
		form.action = "WaigouwaiweiPlanAction!gotoprint.action?pageStatus=waigou";
		form.submit();
	} else if (type == 'audit') {
		form.action = "WaigouwaiweiPlanAction!auditOrder.action?pageStatus=audit&tag="
				+ tag;
		form.submit();
	} else if (type == 'sq') {
		form.action = "WaigouwaiweiPlanAction!applyordersq.action?pageStatus=waigou";
		form.submit();
	} else {
		alert("非法操作!");
	}
	return true
}
function tanchu(id, gysId) {
	document.getElementById("xiugaiIframe").src = "WaigouwaiweiPlanAction!toxiugaigys.action?id="
			+ id + "&id2=" + gysId;
	chageDiv('block');

}
function clicks() {
	document.getElementById('form').action = "WaigouwaiweiPlanAction!exportWgOrderList.action?pageStatus=${pageStatus}&tag=${tag}";
	document.getElementById('form').submit();
	document.getElementById('form').action = "WaigouwaiweiPlanAction!findWgPlanList.action?pageStatus=${pageStatus}&tag=${tag}";
}
function toShowWg(pStatus, tag,noOperation) {
	window.location.href = "WaigouwaiweiPlanAction!findWgOrderList.action?pageStatus="
			+ pStatus + "&tag=" + tag+"&noOperation="+noOperation;
}
function backApply(id, tag) {
	if (window.confirm("确认反审此订单?")) {
		window.location.href = "WaigouwaiweiPlanAction!backApply.action?tag=wg&id="
				+ id + "&tag=" + tag;
	}
}

//添加备注
function addRemark(id,obj){
	$("#title").text("添加备注");
	document.getElementById("xiugaiIframe").src = "WaigouwaiweiPlanAction!findWaigouOrderById.action?id="+ id;
	chageDiv('block');
}
</script>
	</body>
</html>
