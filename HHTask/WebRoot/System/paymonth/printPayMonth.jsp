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
@media print {
	#yufukuan {
		width: 95%;
	}
	.table10 {
		font-size: 1px;
		text-indent: 6px;
		padding: 0px;
		margin: 0px;
		border-collapse: collapse;
		border: solid #999;
		border-width: 0px 0 0 0px;
		width: 99%;
	}
	.table10 th,.table10 td {
		border: solid #000;
		border-width: 2 2px 2px 2;
		font-size: 1mm;
		line-height: 11px;
	}
}

@media screen {
	.table10 {
		font-size: 14px;
		padding: 0px;
		margin: 0px;
		border-collapse: collapse;
		border: solid #999;
		border-width: 0px 0 0 0px;
		width: 99%;
	}
	.table10 th,.table10 td {
		border: solid #000;
		border-width: 2 2px 2px 2;
		padding: 2px;
	}
}
</style>
	</head>
	<body style="font-family: '黑体'">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div id="yufukuan" align="center" style="width: 95%;">
				<h3 style="width: 100%;" align="center">
					<font size="1" style="line-height: 11px;"><br />
						<p>
							${companyInfo.name}
						</p>
						<br>
						<p>
							请款凭单
						</p> 
					</font>
				</h3>
				<div align="left" style="width: 85%; font-size: 1px; line-height: 11px;">
					<div style="float: left;">
						&nbsp;&nbsp;
						<b>No：</b>${payMonth.recNumber}
					</div>
					<div style="float: right;">
						<b>申请日期：</b>${payMonth.saveTime}&nbsp;&nbsp;
					</div>
				</div>
				<table class="table10" align="center" style="width: 85%; ">
					<tr style="height: 75px;">
						<%--					line-height: 25px;border-right-width: 0px;--%>
						<td align="left" colspan="2" style="height: 50px;font-size: 15px;line-height: 20px;" >
						&nbsp;&nbsp;
							<b>请款项目：</b>
							<br>
						<div align="center">
							
							<br>
							&nbsp;&nbsp;
							<b>${payMonth.proName}</b>
							
						</div>
						</td>
						<%--						line-height: 25px;border-left-width: 0px;--%>
						<td align="left" colspan="3" style="height: 50px; font-size:15px;line-height:20px;">
						<br>
						附单据&nbsp;&nbsp;&nbsp;&nbsp;_____张
						<br/>发票金额&nbsp;&nbsp;&nbsp;&nbsp;_____
						<br/>发票№：&nbsp;&nbsp;&nbsp;&nbsp;${payMonth.taxprice}
						
						</td>
						
						<td align="left" colspan="3" style="height: 50px; font-size:15px;line-height:20px;">
							付款条件：&nbsp;&nbsp;&nbsp;&nbsp;<s:if test="payMonth.fukuanfs=='月结'">${payMonth.fkfs}</s:if><s:else>现金</s:else>
							<br/>货款到期日：&nbsp;&nbsp;&nbsp;&nbsp;
							<br/>预计支付日：&nbsp;&nbsp;&nbsp;&nbsp;
							<br/>出纳支付签字：&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td rowspan="10"
							style="width: 1px; border-width: 0px; font-size: 1mm;">
							<ul>
								<li>
									第
								</li>
								<li>
									一
								</li>
								<li>
									联
								</li>
								<li>
									付
								</li>
								<li>
									款
								</li>
								<li>
									依
								</li>
								<li>
									据
								</li>
								<li>
									︵
								</li>
								<li>
									白
								</li>
								<li>
									︶
								</li>
								<li>
									第
								</li>
								<li>
									二
								</li>
								<li>
									联
								</li>
								<li>
									二
								</li>
								<li>
									联
								</li>
								<li>
									二
								</li>
								<li>
									：
								</li>
								<li>
									二
								</li>
								<li>
									联
								</li>
								<li>
									请
								</li>
								<li>
									位
								</li>
								<li>
									︵
								</li>
								<li>
									红
								</li>
								<li>
									︶
								</li>
							</ul>
							<%--						<br/><br><br>--%>
							<%--						<br><br>联<br>：<br>联<br>请<br>单<br>位<br>︵<br>红<br>︶</td>--%>
					</tr>
					<tr style="height: 20px;">
						
						<th align="center" colspan="4" style="height: 20px;">
							费用摘要说明
						
						</th>
						<th align="center" colspan="2" style="height: 20px;">
							金额
						</th>
						<th align="center" colspan="2" style="height: 20px;">
							领款人
						</th>
						
					</tr>
					<s:iterator value="pmdList" id="pmd" status="pageStatus">
					<tr style="height: 20px;">
						<th align="center" colspan="4" style="height: 20px;">
							<b>${pmd.illustrate}</b>
						
						</th>
						<th align="right" colspan="2" style="height: 20px;">
							<b>${pmd.jine}</b>
						</th>
						<th align="center" colspan="2" style="height: 20px;;BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px;">
							<b></b>
						</th>
					</tr>
					</s:iterator>
					<tr style="height: 20px;">
					<th colspan="1" style="height: 20px;">
							合计(大写人民币)：
						</th>
						<td align="left" colspan="3" style="height: 20px;">
							${payMonth.jineOfChiness}
						</td>
						<td align="right" colspan="2" style="height: 20px;">
							<b>￥</b>&nbsp;&nbsp;<fmt:formatNumber pattern="0.00" value="${payMonth.jine}"></fmt:formatNumber>&nbsp;&nbsp;
						</td>
						<td align="left" colspan="2" style="height: 20px;BORDER-TOP-WIDTH: 0px;">
						</td>
					</tr>
					<tr style="font-size: 1mm;height: 20px;" >
						<th>
							总经理
						</th>
						<th>
							会计主管
						</th>
						<th>
							核算会计
						</th>
						<th>
							会计
						</th>
						<th>
							出纳
						</th>
						<th>
							主管副总
						</th>
						<th>
							部门主管
						</th>
						<th>
							制单人
						</th>
					</tr>
					<tr style="font-size: 1mm;height: 40px;">
						<td style=""></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<th>
							<font size="2px;">${Users.name}</font>
						</th>
					</tr>
				</table>
				<div align="left" style="width: 96%;">
					<b><font style="font-family: '楷体'; "  size="1">
							&nbsp;&nbsp;备注：签核时请详细核对，签名时请填写日期在签名处。<br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月结应付账：每张传票最少需由&nbsp; 制单人->主管副总->核算会计->会计主管->总经理<br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其他类：每张传票最少需由&nbsp;制单人->部门主管->主管副总->核算会计->会计主管->总经理
					</font>
					</b>
				</div>
			</div>
			<div align="center" style="margin: 75px 300px 0px 0px;">
				<input type="submit" value="打印" onclick="pagePrintOld('yufukuan')"
					style="width: 80px; height: 50px;" />
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
</script>
	</body>
</html>
