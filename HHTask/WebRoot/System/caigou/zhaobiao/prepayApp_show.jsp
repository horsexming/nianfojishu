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
	<body onload="gys()">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center">
				<h3>
					预付款申请单管理
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<div align="right">
					<s:if test="tag=='findAllself'">
						<label style="background-color: #5cb85c;" class="dhlabel">
							预付款(个人)
						</label>
					</s:if>
					<s:else>
						<label onclick="toShowWg('findAllself');" class="dhlabel">
							<font>预付款(个人)</font>
						</label>
					</s:else>
					<s:if test="tag=='findAll'">
						<label style="background-color: #5cb85c;" class="dhlabel">
							所有预付款
						</label>
					</s:if>
					<s:else>
						<label onclick="toShowWg('findAll');" class="dhlabel">
							<font>所有预付款</font>
						</label>
					</s:else>
				</div>
				<form action="zhaobiaoAction!showListYufu.action?tag=${tag}"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="right">
								申请人：
							</td>
							<td align="left">
								<input type="text" name="prepayApp.jbName" />
							</td>
							<td align="right">
								预付款申请单编号：
							</td>
							<td align="left">
								<input type="text" name="prepayApp.number" />
							</td>
							<td align="right">
								采购订单编号：
							</td>
							<td align="left">
								<input type="text" name="prepayApp.poNumber" />
							</td>
							<td align="right">
								供应商名称：
							</td>
							<td align="left">
								<input type="text" name="prepayApp.yyName" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="8">
								<input type="submit"
									style="width: 100px; height: 40px; margin-left: 70px;"
									value="查询(select)" />
								<input type="button"
									style="width: 100px; height: 40px; margin-left: 70px;"
									value="添加(add)" onclick="add()" />
							</td>
						</tr>
					</table>
				</form>

				<table class="table" style="border-collapse: collapse;">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							预付款单编号
						</td>
						<td align="center">
							采购单编号
						</td>
						<td align="center">
							项目名称
						</td>
						<td align="center">
							采购总额
						</td>
						<td align="center">
							预付金额
						</td>
						<td align="center">
							预付比例
						</td>
						<td align="center">
							预计报销日期
						</td>
						<td align="center">
							申请状态
						</td>
						<td align="center">
							添加时间
						</td>
						<td align="center">
							操作
						</td>
					</tr>
					<s:iterator value="prepayAppList" id="samples" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${samples.number}
						</td>
						<td
							style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							<font size="1">${samples.poNumber}</font>
							<ul class="qs_ul">
								<li>
									${samples.poNumber}
								</li>
							</ul>
						</td>
						<td
							style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							<font size="1">${samples.yyName}</font>
							<ul class="qs_ul">
								<li>
									${samples.yyName}
								</li>
							</ul>
						</td>
						<td align="center">
							${samples.allMoneys}
						</td>
						<td align="center">
							${samples.yfMoneys}
						</td>
						<td align="center">
							${samples.yfbl}%
						</td>
						<td align="center">
							${samples.expectedTime}
						</td>
						<td align="center">
							${samples.status}
						</td>
						<td align="center">
							${samples.addTime}
						</td>
						<td align="center">
							<a
								href="zhaobiaoAction!toselectyufu.action?prepayApp.id=${samples.id}">打印</a>
							<s:if test='#samples.status=="待完善"'>
								<a
									href="zhaobiaoAction!toupdateyufu.action?prepayApp.id=${samples.id}&cpage=${cpage}">/完善</a>
							</s:if>
							<s:elseif test='#samples.status=="未审批"'>
								<a
									href="zhaobiaoAction!toupdateyufu.action?prepayApp.id=${samples.id}&cpage=${cpage}">/修改</a>
							</s:elseif>
							<s:if test="tag=='admin'">
								<a onclick="return window.confirm('您将删除数据，是否继续?')"
									href="zhaobiaoAction!deleteyufu.action?id1=${samples.id}">/删除</a>
							</s:if>
							<s:if test='#samples.status!="同意" && #samples.status!="审批中" '>
								<a onclick="return window.confirm('您将删除数据，是否继续?')"
									href="zhaobiaoAction!deleteyufu.action?id1=${samples.id}">/删除</a>
							</s:if>
							<s:if test="#samples.epId!=null">
								<a
									href="CircuitRunAction_findAduitPage.action?id=${samples.epId}">/审批动态</a>
							</s:if>
							<a
								href="zhaobiaoAction!findMingxi.action?prepayApp.id=${samples.id}">查看明细</a>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="11" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function add() {
	window.location.href = "WaigouwaiweiPlanAction!findWgOrderListAppli.action?pageStatus=findAll&tag=yufukuan";
}
function toShowWg(tag) {
	window.location.href = "zhaobiaoAction!showListYufu.action?tag=" + tag;
}
</script>
	</body>
</html>
