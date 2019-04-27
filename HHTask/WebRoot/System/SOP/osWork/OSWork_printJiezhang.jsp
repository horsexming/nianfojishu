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
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>

			<div align="center">

				<div id="printDiv" align="center">
					<br />
					<br />
					<h2>
						<span></span>
						<table>
							<tr>
								<th>
									<img widtn="64px;" height="55px;" src="${companyInfo.logoOKjpg}" />
								</th>
								<th align="center">
									${companyInfo.name} 外委工序结算单
								</th>
							</tr>
						</table>
					</h2>
					<table style="width: 95%" class="table">
						<tr bgcolor="#c0dcf2" height="30px"
							style="border-collapse: separate;">
							<th align="center">
								序号
							</th>
							<th align="center">
								批次
							</th>
							<th align="center">
								零件号
							</th>
							<th align="center">
								品名
							</th>
							<th align="center">
								外委数量
							</th>
							<th align="center">
								接收数量
							</th>
							<th align="center">
								外委厂家
							</th>
							<th align="center">
								合同号
							</th>
							<th align="center">
								外委工序
							</th>
							<s:iterator value="list" status="se" id="osw">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">

									<td>
										<s:property value="#se.index+1" />
									</td>
									<td>
										${osw.lotId}
									</td>
									<td>
										${osw.markID}
									</td>
									<td>
										${osw.partName}
									</td>
									<td>
										${osw.outSourceCount }
									</td>
									<td>
										${osw.receiveCount}
									</td>
									<td>
										${osw.outScourceComp}
									</td>
									<td>
										${osw.contractNO}
									</td>
									<td>
										<s:iterator value="#osw.processInforSet" id="os">
										${os.processNO};
										</s:iterator>
									</td>

								</tr>
							</s:iterator>
							<tr>
								<th colspan="9" align="right" style="height: 100px;">
									批准:________________ 供应商:________________ 采购:________________
									加工:________________
									<br />
									<br />
								</th>
							</tr>
					</table>
				</div>
				<form action="" id="osP">
					<s:iterator value="osJiesuan" id="o">
						<input type="hidden" name="osJiesuan" value="${o }" />
					</s:iterator>
				</form>
				<input style="width: 80px; font-size: 18px;" onclick="printosw()"
					type="button" value="打印">
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		function printosw(){
			pagePrint('printDiv');
			//处理状态
			$.ajax({
					type : "POST",
					url : "OSWorkAction!updateWaiweiList.action",
					data : $('#osP').serialize(),
					dataType : "json",
					success : function(msg) {
					}
				});	
		}
		</SCRIPT>
	</body>
</html>
