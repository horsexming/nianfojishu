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
	<body onload="gys()">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; margin-top: 10px">
			<h3 style="width: 100%; margin-top: 10px;" align="center">
				打印扣款单
			</h3>
			<div id="koukuandan" align="center" style="width: 800px; height: 1086px; border: 0px solid #000000;">
				<table >
					<tr style="width: 100%">
						<th colspan="4">
							<font size="5px" style="font-family:'楷体','楷体_GB2312';"><B>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${companyInfo.name}</B></font><br>
							<font size="3px" style="font-family:'楷体','楷体_GB2312';"><B>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${companyInfo.englishName}</B></font><br/><br/>
						</th>
					</tr>
					<tr >
						<th colspan="1" align="left" style="width: 100px;" >
						<font size="4px" style="font-family:'Times New Roman';"><B>REV：01</B></font>
						</th>
						<th colspan="3" align="left" style="width: 500px;" >
							<font size="4px" style="font-family:'Times New Roman';"><B>QP740200-Q</B></font>
						</th>
					</tr>
					<tr >
						<th></th>
						<th colspan="3" align="left"  style="width: 200px;">
							编号:${chargebackNotice.number}
						</th>
					</tr>
					<tr>
						<th colspan="4">
							<font size="6px" style="font-family:'黑体';"><B>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;扣款通知单</B></font><br>
						</th>
					</tr>
					<tr>
						<th colspan="2" align="left" style="font-size: 20px;">
							<br/>
							TO：${chargebackNotice.zhUser_name}
							<br/><br/>
						</th>
						<td colspan="2">
						</td>
					</tr>
					<tr>
						<th colspan="2" align="left">
							<table class="table">
								<tr>
									<th colspan="3" align="left">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${chargebackNotice.kkCause}
									</th>
								</tr>
								<tr>
									<th align="left">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${chargebackNotice.description}
									</th>
								</tr>
							<s:if test="chargebackNotice.kkMoney!=null&&chargebackNotice.kkMoney>0">
								<tr align="left">
									<th>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;扣款金额:${chargebackNotice.kkMoney}元
									</th>
								</tr>
							</s:if>
							</table>
<%--							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${chargebackNotice.kkCause}<br/>--%>
<%--							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${chargebackNotice.description}<br/>--%>
<%--							<s:if test="chargebackNotice.kkMoney!=null&&chargebackNotice.kkMoney>0">--%>
<%--								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;扣款金额:${chargebackNotice.kkMoney}元--%>
<%--							</s:if>--%>
							<br/><br/><br/>
						</th>
						<td colspan="2">
						</td>
					</tr>
					<tr>
						<td colspan="2" align="left">
							说明：<br/>
							1、请在一个工作日内回传，否则视为默认；<br/>
							2、如有异议，请及时提供资料协商；<br/>
							3、相关工时单价等标准数据以我司标准为准；<br/><br/>
						</td>
					</tr>
					<tr >
						<th colspan="2">
						</th>
						<th colspan="2" align="left" >
						<font style="margin-left: -60px; font-size: 18px;">
						${companyInfo.name}
						</font><br><br>
						<font style="margin-left: -60px; font-size: 18px;">
						&nbsp;&nbsp;经办人：${chargebackNotice.jbName}
						</font><br><br>
						<font style="margin-left: -60px; font-size: 18px;">
						&nbsp;&nbsp;审核：
						</font><br><br>
						<font style="margin-left: -60px; font-size: 18px;">
						&nbsp;&nbsp;批准：
						</font><br><br>
						</th>
					</tr>
					<tr>
						<td colspan="4">
						―――――――――――――――――――――――――――――――――――<br/>
						</td>
					</tr>
					<tr>
						<th colspan="4" style="font-family:'黑体'; font-size: 20px;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;财务扣款通知存根
						</th>
					</tr>
					<tr>
						<th align="left" style="font-family:'黑体'; font-size: 16px;">
							<B>供应商名称：${chargebackNotice.zhUser_name}</B> <br/>
							<B>扣款事由：${chargebackNotice.kkCause}</B><br/>
							<B>扣款金额：${chargebackNotice.kkMoney}元</B><br/>
							<B>提报单位：${chargebackNotice.reportUnit}</B><br/><br/>
							<B>请财务按上述标准在供应商<u> ${chargebackNotice.kkMouth} </u>月份货款中扣除。</B>
						</th>
						<th colspan="2">
						</th>
					</tr>
				</table>
			</div>
			<div align="center">
				<input type="submit" value="打印" onclick="pagePrint('koukuandan','')"
					style="width: 80px; height: 50px;" />
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
</script>
	</body>
</html>
