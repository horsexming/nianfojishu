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
								<span id="title">开模申请单信息</span>
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
				<form action="MouldApplyOrderAction_findmaoList.action" method="post" >
					<table class="table">
						<tr>
							<th align="right">
								客户
							</th>
							<td>
								<input type="text" value="" name="mao.kehu"/>
							</td>
							<th align="right">
								业务件号
							</th>
							<td>
								<input type="text" value="" name="mao.ywMarkId"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								申请人
							</th>
							<td>
								<input type="text" value="" name="mao.addUserName"/>
							</td>
							<th align="right">
								申请单号
							</th>
							<td>
								<input type="text" value="" name="mao.planNumber"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								评估状态
							</th>
							<td>
								<SELECT name="mao.status">
									<option value="${mao.status}">${mao.status}</option>
									<option></option>
									<option value="市场需求评估">市场需求评估</option>
									<option value="产能需求评估">产能需求评估</option>
									<option value="工艺实现评估">工艺实现评估</option>
									<option value="评估完成">评估完成</option>
								</SELECT>
							</td>
							<th></th>
							<td></td>
						</tr>
					</table>
					<input type="submit" value="查询" class="input"/>
					<input type="button" value="添加" onclick="window.open('./System/SOP/muju/mao_add.jsp')" class="input"/>
				</form>
				<form action="WaigouwaiweiPlanAction!addmujuOrder.action"  method="post" >
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
					<s:if test="pageStatus == 'xiadan'">
						<th>
							<input type="checkbox"  onclick="chageAllCheck(this)"/>
						</th>
					</s:if>
						<th>序号</th>
						<th>单号</th>
						<th>客户名称</th>
						<th>业务件号</th>
						<th>订单数量</th>
						<th>产品交期</th>
						<th>需求日期</th>
						<th>制作方式</th>
						<th>申请人</th>
						<th>申请日期</th>
						<th>申请类型</th>
						<th>状态</th>
						<th>审批动态</th>
						<th>操作</th>
					</tr>
					<s:iterator value="maoList" id="pagemao" status="statussdf">
						<s:if test="#statussdf.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
							<s:if test="pageStatus == 'xiadan'">
								<td>
										<input type="checkbox"  value="${pagemao.id}" name="processIds" />
								</td>
							</s:if>
								<td>
									<s:property value="#statussdf.index+1" />
								</td>
								<td>${pagemao.planNumber}</td>
								<td>${pagemao.kehu}</td>
								<td>${pagemao.ywMarkId}</td>
								<td>${pagemao.orderNum}</td>
								<td>${pagemao.projiaoqiTime}</td>
								<td>${pagemao.xqTime}</td>
								<td>${pagemao.maketype}</td>
								<td>${pagemao.addUserName}</td>
								<td>${pagemao.adddate}</td>
								<td>${pagemao.applytype}</td>
								<td>${pagemao.status}</td>
								<td>
									<s:if test='#pagemao.epstatus!=null && #pagemao.epstatus!=""'>
										<a href="CircuitRunAction_findAduitPage.action?id=${pagemao.epId}">${pagemao.epstatus}</a>
									</s:if>
									<s:else>
										未申请
									</s:else>
								</td>
								<td>
									<a href="javascript:;"	onclick="window.open('MouldApplyOrderAction_findMoaAndmdList.action?id=${pagemao.id}&pageStatus=mingxi')" >详情</a>
									<s:if test='#pagemao.status!=null && #pagemao.status!="评估完成" && #session.Users.id == #pagemao.userId'>
										<s:if test='#pagemao.status=="市场需求评估"'>
											/<a href="javascript:;"	onclick="window.open('MouldApplyOrderAction_findMoaAndmdList.action?id=${pagemao.id}&pageStatus=pinggu')">市场需求评估 </a>
										</s:if>
										<s:elseif test='#pagemao.status=="产能需求评估" '>
											/<a  href="javascript:;"	onclick="window.open('MouldApplyOrderAction_findMoaAndmdList.action?id=${pagemao.id}&pageStatus=pinggu')">产能需求评估</a>
										</s:elseif>
										<s:elseif test='#pagemao.status=="工艺实现评估" '>
											/<a  href="javascript:;"	onclick="window.open('MouldApplyOrderAction_findMoaAndmdList.action?id=${pagemao.id}&pageStatus=pinggu')">工艺实现评估</a>
										</s:elseif>
									</s:if>
									<s:if test='#pagemao.epstatus!="同意" && #pagemao.epstatus!="打回"'>
										<s:if test="pageStatus=='wanshan'">
											/<a href="javascript:;"	onclick="window.open('MouldApplyOrderAction_findMoaAndmdList.action?id=${pagemao.id}&pageStatus=wanshan')">完善模具号</a>/
										</s:if>
										<s:else>
											/<a href="javascript:;"	onclick="window.open('MouldApplyOrderAction_findMoaAndmdList.action?id=${pagemao.id}&pageStatus=xiugai')">修改</a>/
										</s:else>
										<a href="MouldApplyOrderAction_delmao.action?mao.id=${pagemao.id}&cpage=${cpage}" onclick="return confirm('确定要删除吗?')">删除</a>
									</s:if>
									<s:if test="#pagemao.epstatus== '同意'">
										<s:if test="pageStatus == 'caigou'">
											/<a href="javascript:;"	onclick="window.open('MouldApplyOrderAction_findMoaAndmdList.action?id=${pagemao.id}&pageStatus=${pageStatus}')" >绑定供应商</a>
										</s:if>
										<s:else>
											/<a href="javascript:;"	onclick="window.open('MouldApplyOrderAction_findMoaAndmdList.action?id=${pagemao.id}&pageStatus=mjys')">模具验收</a>
										</s:else>
									</s:if>
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
				<s:if test="pageStatus == 'xiadan'">
					<input type="submit" value="下单" onclick="todisabled(this)" class="input"/>
				</s:if>
			</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function showmingxi(num) {
		document.getElementById("xiugaiIframe").src = "MouldApplyOrderAction_findMoaAndmdList.action?id="
				+ num+"&pageStatus=mingxi";
	chageDiv('block')
}

</SCRIPT>
	</body>
</html>
