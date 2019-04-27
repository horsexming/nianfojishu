<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
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
				style="position: relative;  background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<div id="collorProcessDiv" >
					<table style="width: 100%; margin-top: ">
						<tr>
							<td>
								您正在领取辅料:
							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									height="32" onclick="chageDiv('none')">
							</td>
						</tr>
					</table>
					<div id="operatingDiv"
						style="background-color: #ffffff; width: 100%;">
						<iframe id="showfl" src="" marginwidth="0" marginheight="0"
							hspace="0" vspace="0" frameborder="0" scrolling="yes"
							style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
					</div>
				</div>
			</div>
		</div>
		<div>
			<div align="center" style="border: 1px solid #00000;">
				<div id="rootTemplateDiv">
					<div>
						<table class="table">
							<tr bgcolor="#c0dcf2" height="50px">
								<th align="center">
									序号
									<br />
									No.
								</th>
								<th align="center">
									件号
									<br />
									Part No.
								</th>
								<th align="center">
									名称
									<br />
									Name
								</th>
								<th align="center">
									卡片类型
									<br />
									Card Type
								</th>
								<th align="center">
									产品类型
									<br />
									Product Type
								</th>
								<th align="center">
									批次
									<br />
									Batch
								</th>
								<th align="center">
									计划单号
									<br />
									Single number plan
								</th>
								<th align="center">
									制卡时间
									<br />
									Card time
								</th>
								<th align="center">
									数量
									<br />
									Quantity
								</th>
								<th align="center">
									状态
									<br />
									State
								</th>
								<th align="center">
									操作
									<br />
									Operation
								</th>
							</tr>
							<s:iterator value="procardList" id="pageProcard"
								status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										style="height: 50px;" onmouseout="outBgcolor(this,'')">
								</s:else>
								<td align="center">
									<s:property value="#pageStatus.index+1" />
								</td>
								<td>
									${pageProcard.markId}
								</td>
								<td>
									${pageProcard.proName}
								</td>
								<td>
									${pageProcard.procardStyle}
								</td>
								<td>
									${pageProcard.productStyle}
								</td>
								<td>
									${pageProcard.selfCard}
								</td>
								<td>
									${pageProcard.planOrderNum}
								</td>
								<td>
									${pageProcard.procardTime}
								</td>
								<td>
									${pageProcard.filnalCount}
								</td>
								<td>
									${pageProcard.status}
								</td>
								<td>
									<a onclick="getFlList(${pageProcard.rootId})">领取辅料</a>
								</td>
								</tr>
							</s:iterator>
						</table>
					</div>
				</div>
				<br>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<SCRIPT type="text/javascript">
			function getFlList(id){
				$("#showfl").attr("src","ProcardAction!getProcessInForFuLiao.action?id="+id+"&cardNumber=${cardNumber}");
				chageDiv('block');
			}
			</SCRIPT>
		</center>
	</body>
</html>
