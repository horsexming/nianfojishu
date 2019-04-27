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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
<style type="text/css">
@media print {
	.notprint {
		display: none;
	}
}
</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div class="notprint" align="right">	
					<input type=button value='打印' onClick="window.print();"
									style="border: 1px solid #000000; cursor: pointer;">
				</div>
				
					<table width="100%" border=0 align=center id="tabHeader"
							style="font-size: 1px;">
							<tr style="border: hidden;">
								<th colspan="13">
									<p style="font-size: 20px;">
										${companyInfo.name}
									</p>
									<p style="font-size: 15px;">
										${companyInfo.englishName}
									</p>
									<p style="font-size: 15px;">
										成品退货
										<font style="font-size: x-small;"> <!--ct--> </font>
									</p>
									<br />
								</th>
							</tr>
							<tr>
								<th colspan="10" align="left" style="border: hidden;">
									&nbsp;&nbsp;&nbsp;&nbsp;REV:00
								</th>
								<th colspan="3" align="left" style="border: hidden;">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;QP750500-Z
								</th>
							</tr>
							<tr>
								<th colspan="4" align="left">
										&nbsp;&nbsp;&nbsp;&nbsp;客户:${cptu.sell.sellCompanyName}
								</th>
								<th colspan="6" align="left" style="">
										&nbsp;&nbsp;&nbsp;&nbsp;送货单号:${cptu.shPlanNum}
								</th>
								<th colspan="3" align="left"
									style="border-right: hidden; border-left: hidden;">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期:${cptu.addTime}
								</th>
							</tr>
						</table>
						<table class="table" border=1
							style="border-collapse: collapse; font-size: 1px; line-height: 12px;"
							align=center cellpadding=3 id="tabDetail">
							<tr style="height: 20xp;">
								<th>
									件号
								</th>
								<th>
									业务件号
								</th>
								<th>
									生产批次
								</th>
								<th>
									产品名称
								</th>
								<th>
									单位
								</th>
								<th>
									数量
								</th>
								<th>
									外部订单号
								</th>
								<th class="cangqu">
									仓区
								</th>
								<th>
									内部订单号
								</th>
							</tr>
								<tr style="height: 30px;">
									<th>
										${cptu.markId}
									</th>
									<th>
										${cptu.ywmarkId}
									</th>
									<th>
										${cptu.selfCard}
									</th>
									<th>
										${cptu.proname}
									</th>
									<th>
										${cptu.sell.sellUnit}
									</th>
									<th>
										${cptu.sqNum}
									</th>
									<th>
										${cptu.sell.outOrderNumer}
									</th>
									<th class="cangqu" style="width: 50px;">
										${cptu.cangqu}
									</th>
									<th>
										${cptu.orderNo}
									</th>
								</tr>
						</table>
						<table width="100%" border=0 id="tabFooter" align="center"
							cellpadding=4 style="font-size: 1px; line-height: 12px;">
							<tr style="border-left: hidden; border-right: hidden;">
								<th align="left" colspan="13" style="border-left: hidden;">
									审核:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									业务员:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									主管:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									制单:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</th>
							</tr>
							<tr style="border: hidden;">
								<th colspan="13" align="left">
									第一白联仓库&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									文员&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									第二红联：财务&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									第二黄联：仓管
								</th>
							</tr>
						</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
