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
.pagetable {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	border: solid #000;
	border-width: 3px;
	width: 980px;
}

.pagetable th,.pagetable td {
	border: solid #000 3px;
	border-width: 2px;
	padding: 2px;
}
</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			
			<div align="center">

				<h2>
					打印外委工序工作单
				</h2>
				<input style="width: 80px; font-size: 18px;" onclick="printosw()"
					type="button" value="打印">
				<br />
				<br />

				<div id="printDiv" style="width: 365px;; height: 272">
					<table border="1" class="pagetable"
						style="font-size: 13px; font-weight: bold; width: 360px; height: 270"
						align="center">

						<tr>
							<td style="padding-top: 0px; margin-left: 2px; height: 60px;"
								align="center">
								<img alt="honghu" widtn="64px;" height="55px;"
									src="<%=basePath%>${companyInfo.logoOKjpg}" />
							</td>
							<td colspan="3" align="center"
								style="font-size: 16px; font-weight: bold; center; vertical-align: middle;">

								${companyInfo.shortName}
								<span style="width: 6px;"></span>工序外委工作单
							</td>
						</tr>
						<tr>
							<td colspan="4" style="height: 46px;">
								<img
									src="<%=request.getContextPath()%>/barcode.action?msg=${osWork.number}&type=code128"
									height="50px" width="360px" />
								</div>

							</td>
						</tr>
						<tr>
							<td style="width: 65px;" align="center">
								件号
							</td>
							<td style="width: 125px;">
								${osWork.markID }
							</td>
							<td style="width: 60px;" align="center">
								名称
							</td>
							<td style="width: 110px;">
								${osWork.partName }
							</td>
						</tr>
						<tr>
							<td align="center">
								批次
							</td>
							<td>
								${osWork.lotId }(版)
							</td>
							<td align="center">
								数量
							</td>
							<td>
								${osWork.outSourceCount } (${osWork.unit })
							</td>
						</tr>
						<tr>
							<td align="center">
								工序
							</td>
							<td colspan="3">
								<s:iterator value="list" id="os">
								${os.processNO};
								</s:iterator>
							</td>
						</tr>
						<tr>
							<td align="center">
								合同
							</td>
							<td>
								${osWork.contractNO }
							</td>
							<td align="center">
								供应商
							</td>
							<td>
								${osWork.outScourceComp }
							</td>
						</tr>
						<tr>
							<td align="center">
								日期
							</td>
							<td colspan="3">
								${osWork.outSourceTime }
							</td>
						</tr>

					</table>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		function printosw(){
			pagePrint('printDiv');
			
		}
		</SCRIPT>
	</body>
</html>
